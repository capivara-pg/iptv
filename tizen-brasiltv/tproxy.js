#!/usr/bin/env node
'use strict';

// Transparent HLS proxy — sits between the Android app and stream servers.
// Caches M3U8 playlists and TS segments so the browser can play them.
// The emulator's iptables DNAT redirects port 30813 traffic here.

const http = require('http');
const net = require('net');
const url = require('url');

const LISTEN_PORT = 30814;
const CACHE_MAX_AGE = 120000; // 2 minutes

// Cache: path → { body (Buffer), contentType, timestamp, host, port }
const responseCache = new Map();
// Latest M3U8 info for the browser
let latestM3U8 = null;
// All captured M3U8s
const m3u8Cache = new Map();

function stripBrackets(host) {
    return host.replace(/^\[|\]$/g, '');
}

const server = http.createServer((req, res) => {
    // The app's request arrives here due to iptables DNAT.
    // The Host header tells us where to forward.
    const hostHeader = req.headers['host'] || '';
    const hostPortMatch = hostHeader.match(/^(.+):(\d+)$/);
    let targetHost, targetPort;

    if (hostPortMatch) {
        targetHost = hostPortMatch[1];
        targetPort = parseInt(hostPortMatch[2]);
    } else {
        targetHost = hostHeader;
        targetPort = 30813;
    }

    const resolvedHost = stripBrackets(targetHost);
    const requestPath = req.url;
    const cookies = req.headers['cookie'] || '';
    const cacheKey = targetHost + ':' + targetPort + requestPath;

    console.log(`[TPROXY] ${req.method} http://${targetHost}:${targetPort}${requestPath}`);

    const options = {
        hostname: resolvedHost,
        port: targetPort,
        path: requestPath,
        method: req.method,
        headers: Object.assign({}, req.headers),
    };
    // Ensure Host header is correct
    options.headers['host'] = hostHeader;

    const proxyReq = http.request(options, (proxyRes) => {
        const chunks = [];
        proxyRes.on('data', (chunk) => {
            chunks.push(chunk);
            // Also pipe to the app in real-time
            res.write(chunk);
        });

        proxyRes.on('end', () => {
            const body = Buffer.concat(chunks);
            const contentType = proxyRes.headers['content-type'] || '';
            const bodyStr = body.toString('utf-8');

            // Cache the response
            const entry = {
                body: body,
                contentType: contentType,
                statusCode: proxyRes.statusCode,
                timestamp: Date.now(),
                host: targetHost,
                port: targetPort,
                path: requestPath,
                cookies: cookies,
            };
            responseCache.set(cacheKey, entry);

            // Detect M3U8 responses
            if (proxyRes.statusCode === 200 &&
                (contentType.includes('mpegurl') || bodyStr.includes('#EXTM3U'))) {
                console.log(`[TPROXY] Cached M3U8: ${cacheKey} (${body.length} bytes)`);
                const m3u8Entry = {
                    body: bodyStr,
                    host: targetHost,
                    port: targetPort,
                    path: requestPath,
                    cookies: cookies,
                    capturedAt: Date.now(),
                };
                m3u8Cache.set(cacheKey, m3u8Entry);
                latestM3U8 = m3u8Entry;
            } else if (proxyRes.statusCode === 200 && body.length > 0) {
                // Likely a TS segment
                console.log(`[TPROXY] Cached segment: ${requestPath.substring(0, 60)}... (${body.length} bytes)`);
            } else {
                console.log(`[TPROXY] <- ${proxyRes.statusCode} (${body.length} bytes, ${contentType})`);
            }

            res.end();
        });

        // Forward status and headers to the app
        const fwdHeaders = Object.assign({}, proxyRes.headers);
        res.writeHead(proxyRes.statusCode, fwdHeaders);
    });

    proxyReq.on('error', (err) => {
        console.error(`[TPROXY] Error: ${err.message}`);
        if (!res.headersSent) {
            res.writeHead(502);
            res.end('Bad Gateway');
        }
    });

    proxyReq.setTimeout(30000, () => {
        proxyReq.destroy();
        if (!res.headersSent) {
            res.writeHead(504);
            res.end('Gateway Timeout');
        }
    });

    // Forward request body if any
    req.pipe(proxyReq);
});

// API server for the browser proxy to query cached data
const apiServer = http.createServer((req, res) => {
    const headers = {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
    };

    if (req.url === '/status') {
        res.writeHead(200, headers);
        res.end(JSON.stringify({
            cached: responseCache.size,
            m3u8s: m3u8Cache.size,
            latest: latestM3U8 ? {
                host: latestM3U8.host,
                port: latestM3U8.port,
                path: latestM3U8.path,
                bodyLength: latestM3U8.body.length,
                age: Math.round((Date.now() - latestM3U8.capturedAt) / 1000),
            } : null,
        }));
        return;
    }

    if (req.url === '/latest') {
        if (!latestM3U8) {
            res.writeHead(404, headers);
            res.end(JSON.stringify({ error: 'No M3U8 captured yet' }));
            return;
        }
        res.writeHead(200, headers);
        res.end(JSON.stringify(latestM3U8));
        return;
    }

    // Serve cached content by path
    const parsed = url.parse(req.url, true);
    if (parsed.pathname === '/cached') {
        const key = parsed.query.key;
        if (key && responseCache.has(key)) {
            const entry = responseCache.get(key);
            res.writeHead(200, {
                'Content-Type': entry.contentType,
                'Access-Control-Allow-Origin': '*',
            });
            res.end(entry.body);
            return;
        }
        res.writeHead(404, headers);
        res.end(JSON.stringify({ error: 'Not in cache' }));
        return;
    }

    // Serve cached TS segment by host+path
    if (parsed.pathname === '/segment') {
        const host = parsed.query.host;
        const path = parsed.query.path;
        const port = parsed.query.port || '30813';
        const key = host + ':' + port + path;

        if (responseCache.has(key)) {
            const entry = responseCache.get(key);
            if (entry.statusCode === 200) {
                res.writeHead(200, {
                    'Content-Type': entry.contentType || 'video/MP2T',
                    'Content-Length': entry.body.length,
                    'Access-Control-Allow-Origin': '*',
                });
                res.end(entry.body);
                return;
            }
        }
        // Not cached yet — return 202 (the app may not have fetched it yet)
        res.writeHead(202, headers);
        res.end(JSON.stringify({ error: 'Segment not cached yet', key: key }));
        return;
    }

    if (req.url.startsWith('/clear')) {
        responseCache.clear();
        m3u8Cache.clear();
        latestM3U8 = null;
        res.writeHead(200, headers);
        res.end(JSON.stringify({ cleared: true }));
        return;
    }

    res.writeHead(404, headers);
    res.end(JSON.stringify({ error: 'Not found' }));
});

// Clean up old cache entries periodically
setInterval(() => {
    const now = Date.now();
    for (const [key, entry] of responseCache) {
        if (now - entry.timestamp > CACHE_MAX_AGE) {
            responseCache.delete(key);
        }
    }
}, 30000);

server.listen(LISTEN_PORT, '0.0.0.0', () => {
    console.log(`[TPROXY] Transparent proxy listening on port ${LISTEN_PORT}`);
    console.log('[TPROXY] Set up iptables DNAT to redirect app traffic here:');
    console.log(`  adb shell su 0 iptables -t nat -A OUTPUT -p tcp --dport 30813 -m owner --uid-owner 10193 -j DNAT --to-destination 10.0.2.2:${LISTEN_PORT}`);
});

const API_PORT = 8091;
apiServer.listen(API_PORT, '0.0.0.0', () => {
    console.log(`[TPROXY] API server on port ${API_PORT}`);
    console.log('[TPROXY] Endpoints:');
    console.log('  GET /status   — cache status');
    console.log('  GET /latest   — latest M3U8');
    console.log('  GET /segment?host=...&path=...&port=... — cached segment');
    console.log('  GET /clear    — reset cache');
});
