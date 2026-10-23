#!/usr/bin/env node
'use strict';

const http = require('http');
const https = require('https');
const fs = require('fs');
const path = require('path');
const url = require('url');

const PORT = 8090;
const RELAY_PORT = parseInt(process.env.RELAY_PORT) || 8091;
const STATIC_DIR = __dirname;

const ALLOWED_HOSTS = {
    'vngzr.sqnmdtzgp.com': 'http',
    'crhqd.bugphtjkl.com': 'http',
    'mobile.solz1lf.com': 'https',
    'mbfel.lgesetd1l.com': 'https',
    'bxvxjj.pzmsjhwvd.online': 'https',
    'mobiletv.ogy1lfw.com': 'http',
    'mobiletv.terdlfw.com': 'http',
    'notice.terdlfw.com': 'http',
    'notice.ogy1lfw.com': 'http',
    'ifgh.wofn3zkvcm.com': 'http',
    'miqe.sdxpkgyaq.com': 'http',
};

const MIME_TYPES = {
    '.html': 'text/html',
    '.css': 'text/css',
    '.js': 'application/javascript',
    '.json': 'application/json',
    '.png': 'image/png',
    '.jpg': 'image/jpeg',
    '.svg': 'image/svg+xml',
    '.ico': 'image/x-icon',
};

function serveStatic(req, res) {
    const pathname = url.parse(req.url).pathname;
    let filePath = path.join(STATIC_DIR, pathname === '/' ? 'index.html' : pathname);
    filePath = path.normalize(filePath);

    if (!filePath.startsWith(STATIC_DIR)) {
        res.writeHead(403);
        res.end('Forbidden');
        return;
    }

    const ext = path.extname(filePath);
    const contentType = MIME_TYPES[ext] || 'application/octet-stream';

    fs.readFile(filePath, (err, data) => {
        if (err) {
            res.writeHead(404);
            res.end('Not Found');
            return;
        }
        res.writeHead(200, { 'Content-Type': contentType });
        res.end(data);
    });
}

function proxyRequest(req, res) {
    const parsed = url.parse(req.url, true);
    const targetHost = parsed.query.host;
    const targetPath = parsed.query.path;
    const targetPort = parsed.query.port;

    if (!targetHost || !targetPath) {
        res.writeHead(400, corsHeaders());
        res.end(JSON.stringify({ error: 'Missing host or path parameter' }));
        return;
    }

    if (!ALLOWED_HOSTS[targetHost]) {
        res.writeHead(403, corsHeaders());
        res.end(JSON.stringify({ error: 'Host not allowed' }));
        return;
    }

    const protocol = ALLOWED_HOSTS[targetHost];
    const httpModule = protocol === 'https' ? https : http;
    const port = targetPort || (protocol === 'https' ? 443 : 80);

    const headers = {
        'Content-Type': 'application/json;charset=utf-8',
        'apk': 'com.mobile.brasiltvmobile',
        'apkVer': '23503',
        'spkgVer': '14',
        'User-Agent': 'okhttp/3.12.12',
        'Accept-Encoding': 'gzip',
        'Connection': 'Keep-Alive',
        'Host': targetHost,
    };

    if (req.headers['x-custom-headers']) {
        try {
            const custom = JSON.parse(req.headers['x-custom-headers']);
            Object.assign(headers, custom);
        } catch (e) {}
    }

    let body = '';
    req.on('data', chunk => body += chunk);
    req.on('end', () => {
        const options = {
            hostname: targetHost,
            port: port,
            path: targetPath,
            method: req.method === 'OPTIONS' ? 'GET' : (parsed.query.method || req.method),
            headers: headers,
            rejectUnauthorized: false,
        };

        if (body && options.method !== 'GET') {
            headers['Content-Length'] = Buffer.byteLength(body);
        }

        console.log(`[PROXY] ${options.method} ${protocol}://${targetHost}:${port}${targetPath}`);

        const proxyReq = httpModule.request(options, proxyRes => {
            const chunks = [];
            proxyRes.on('data', chunk => chunks.push(chunk));
            proxyRes.on('end', () => {
                const responseBody = Buffer.concat(chunks);
                const responseHeaders = corsHeaders();
                responseHeaders['Content-Type'] = proxyRes.headers['content-type'] || 'application/octet-stream';
                responseHeaders['X-Proxy-Status'] = proxyRes.statusCode;

                res.writeHead(proxyRes.statusCode, responseHeaders);
                res.end(responseBody);

                console.log(`[PROXY] <- ${proxyRes.statusCode} (${responseBody.length} bytes)`);
            });
        });

        proxyReq.on('error', err => {
            console.error(`[PROXY] Error: ${err.message}`);
            if (!res.headersSent) {
                res.writeHead(502, corsHeaders());
                res.end(JSON.stringify({ error: err.message }));
            }
        });

        proxyReq.setTimeout(30000, () => {
            proxyReq.destroy();
            if (!res.headersSent) {
                res.writeHead(504, corsHeaders());
                res.end(JSON.stringify({ error: 'Gateway timeout' }));
            }
        });

        if (body && options.method !== 'GET') {
            proxyReq.write(body);
        }
        proxyReq.end();
    });
}

// HLS proxy — serves cached M3U8 from relay with rewritten segment URLs.
// The stream server validates client IP so we can't fetch M3U8 directly;
// the relay captures fresh M3U8 bodies from tcpdump every few seconds.
function hlsProxy(req, res) {
    const relayReq = http.request({
        hostname: '127.0.0.1',
        port: RELAY_PORT,
        path: '/latest',
        method: 'GET',
        timeout: 3000,
    }, relayRes => {
        const chunks = [];
        relayRes.on('data', chunk => chunks.push(chunk));
        relayRes.on('end', () => {
            const rawBody = Buffer.concat(chunks).toString();
            let data;
            try { data = JSON.parse(rawBody); } catch(e) {
                res.writeHead(502, corsHeaders());
                res.end(JSON.stringify({ error: 'Invalid relay response' }));
                return;
            }

            if (!data.body) {
                res.writeHead(404, corsHeaders());
                res.end(JSON.stringify({ error: 'No M3U8 captured yet. Switch a channel on the Android app.' }));
                return;
            }

            const port = data.port || 30813;
            let m3u8 = rewriteM3U8(data.body, data.host, port);

            const responseHeaders = corsHeaders();
            responseHeaders['Content-Type'] = 'application/vnd.apple.mpegurl';
            responseHeaders['Cache-Control'] = 'no-cache, no-store';
            res.writeHead(200, responseHeaders);
            res.end(m3u8);
        });
    });

    relayReq.on('error', err => {
        console.error(`[HLS] Relay error: ${err.message}`);
        if (!res.headersSent) {
            res.writeHead(502, corsHeaders());
            res.end(JSON.stringify({ error: err.message }));
        }
    });

    relayReq.end();
}

function rewriteM3U8(m3u8Body, baseHost, basePort) {
    // Rewrite absolute URLs
    let result = m3u8Body.replace(/^(http:\/\/)([^/]+)(\/.*\.ts.*)$/gm, (match, proto, host, pathAndRest) => {
        const hostPort = host.split(':');
        const hostname = hostPort[0];
        const port = hostPort[1] || '80';
        return `/stream?host=${encodeURIComponent(hostname)}&path=${encodeURIComponent(pathAndRest)}&port=${port}`;
    });
    // Rewrite relative TS paths (lines not starting with # or /)
    result = result.replace(/^([^#/\s][^\r\n]*\.ts[^\r\n]*)$/gm, (match, relativePath) => {
        return `/stream?host=${encodeURIComponent(baseHost)}&path=${encodeURIComponent('/' + relativePath)}&port=${basePort}`;
    });
    return result;
}

// Stream proxy — fetches TS segments from stream server using captured cookies
function streamProxy(req, res) {
    const parsed = url.parse(req.url, true);
    const targetHost = parsed.query.host;
    const targetPath = parsed.query.path;
    const targetPort = parseInt(parsed.query.port) || 30813;

    if (!targetHost || !targetPath) {
        res.writeHead(400, corsHeaders());
        res.end(JSON.stringify({ error: 'Missing host or path' }));
        return;
    }

    // First, get cookies from the relay
    const cookiePath = '/cookies?host=' + encodeURIComponent(targetHost) +
        '&port=' + targetPort;

    const cookieReq = http.request({
        hostname: '127.0.0.1',
        port: RELAY_PORT,
        path: cookiePath,
        method: 'GET',
        timeout: 3000,
    }, cookieRes => {
        const chunks = [];
        cookieRes.on('data', c => chunks.push(c));
        cookieRes.on('end', () => {
            let cookies = '';
            try {
                const data = JSON.parse(Buffer.concat(chunks).toString());
                cookies = data.cookies || '';
            } catch(e) {}

            // Now fetch the TS segment from the stream server with cookies
            const resolvedHost = stripBrackets(targetHost);
            console.log(`[STREAM] GET http://${targetHost}:${targetPort}${targetPath.substring(0, 60)}`);

            const options = {
                hostname: resolvedHost,
                port: targetPort,
                path: targetPath,
                method: 'GET',
                headers: {
                    'User-Agent': 'okhttp/3.12.12',
                    'Connection': 'Keep-Alive',
                    'Host': `${targetHost}:${targetPort}`,
                },
            };

            if (cookies) {
                options.headers['Cookie'] = cookies;
            }

            if (req.headers['range']) {
                options.headers['Range'] = req.headers['range'];
            }

            const proxyReq = http.request(options, proxyRes => {
                const responseHeaders = corsHeaders();
                responseHeaders['Content-Type'] = proxyRes.headers['content-type'] || 'video/MP2T';
                if (proxyRes.headers['content-length']) {
                    responseHeaders['Content-Length'] = proxyRes.headers['content-length'];
                }
                if (proxyRes.headers['content-range']) {
                    responseHeaders['Content-Range'] = proxyRes.headers['content-range'];
                }

                res.writeHead(proxyRes.statusCode, responseHeaders);
                proxyRes.pipe(res);

                proxyRes.on('end', () => {
                    console.log(`[STREAM] <- ${proxyRes.statusCode}`);
                });
            });

            proxyReq.on('error', err => {
                console.error(`[STREAM] Error: ${err.message}`);
                if (!res.headersSent) {
                    res.writeHead(502, corsHeaders());
                    res.end(JSON.stringify({ error: err.message }));
                }
            });

            proxyReq.setTimeout(30000, () => {
                proxyReq.destroy();
                if (!res.headersSent) {
                    res.writeHead(504, corsHeaders());
                    res.end(JSON.stringify({ error: 'Stream timeout' }));
                }
            });

            proxyReq.end();
        });
    });

    cookieReq.on('error', () => {
        // No cookies available, try without
        fetchSegmentWithoutCookies(targetHost, targetPath, targetPort, req, res);
    });

    cookieReq.end();
}

function fetchSegmentWithoutCookies(targetHost, targetPath, targetPort, req, res) {
    const resolvedHost = stripBrackets(targetHost);
    console.log(`[STREAM] GET (no cookies) http://${targetHost}:${targetPort}${targetPath.substring(0, 60)}`);

    const options = {
        hostname: resolvedHost,
        port: targetPort,
        path: targetPath,
        method: 'GET',
        headers: {
            'User-Agent': 'okhttp/3.12.12',
            'Connection': 'Keep-Alive',
            'Host': `${targetHost}:${targetPort}`,
        },
    };

    const proxyReq = http.request(options, proxyRes => {
        const responseHeaders = corsHeaders();
        responseHeaders['Content-Type'] = proxyRes.headers['content-type'] || 'video/MP2T';
        if (proxyRes.headers['content-length']) {
            responseHeaders['Content-Length'] = proxyRes.headers['content-length'];
        }
        res.writeHead(proxyRes.statusCode, responseHeaders);
        proxyRes.pipe(res);
    });

    proxyReq.on('error', err => {
        if (!res.headersSent) {
            res.writeHead(502, corsHeaders());
            res.end(JSON.stringify({ error: err.message }));
        }
    });

    proxyReq.setTimeout(30000, () => {
        proxyReq.destroy();
        if (!res.headersSent) {
            res.writeHead(504, corsHeaders());
            res.end(JSON.stringify({ error: 'Stream timeout' }));
        }
    });

    proxyReq.end();
}

function stripBrackets(host) {
    return host.replace(/^\[|\]$/g, '');
}

function corsHeaders() {
    return {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET, POST, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type, x-custom-headers, Range',
    };
}

// Relay query — asks the relay for captured stream data
function relayQuery(req, res) {
    const parsed = url.parse(req.url, true);
    const endpoint = parsed.query.q || 'latest';

    const relayReq = http.request({
        hostname: '127.0.0.1',
        port: RELAY_PORT,
        path: '/' + endpoint,
        method: 'GET',
        timeout: 5000,
    }, relayRes => {
        const chunks = [];
        relayRes.on('data', chunk => chunks.push(chunk));
        relayRes.on('end', () => {
            const body = Buffer.concat(chunks).toString();
            let data;
            try { data = JSON.parse(body); } catch(e) {
                res.writeHead(502, corsHeaders());
                res.end(JSON.stringify({ error: 'Invalid relay response' }));
                return;
            }

            // If we got a cached M3U8 body, build an HLS proxy URL
            if (endpoint === 'latest' && data.body) {
                data.hlsProxyUrl = '/hls';
            }

            const responseHeaders = corsHeaders();
            responseHeaders['Content-Type'] = 'application/json';
            res.writeHead(relayRes.statusCode, responseHeaders);
            res.end(JSON.stringify(data));
        });
    });

    relayReq.on('error', err => {
        console.error('[RELAY-PROXY] Error connecting to relay:', err.message);
        res.writeHead(503, corsHeaders());
        res.end(JSON.stringify({
            error: 'Relay service unavailable. Start relay.js first.',
            hint: 'Run: node relay.js'
        }));
    });

    relayReq.end();
}

const server = http.createServer((req, res) => {
    if (req.method === 'OPTIONS') {
        res.writeHead(204, corsHeaders());
        res.end();
        return;
    }

    const parsed = url.parse(req.url);

    if (parsed.pathname === '/proxy') {
        proxyRequest(req, res);
    } else if (parsed.pathname === '/hls') {
        hlsProxy(req, res);
    } else if (parsed.pathname === '/stream') {
        streamProxy(req, res);
    } else if (parsed.pathname === '/relay') {
        relayQuery(req, res);
    } else {
        serveStatic(req, res);
    }
});

server.listen(PORT, '0.0.0.0', () => {
    console.log(`Brasil TV proxy server running on http://0.0.0.0:${PORT}`);
    console.log('Endpoints:');
    console.log('  Static files: http://localhost:' + PORT + '/');
    console.log('  API proxy:    http://localhost:' + PORT + '/proxy?host=...&path=...');
    console.log('  HLS proxy:    http://localhost:' + PORT + '/hls?host=...&path=...&cookie=...');
    console.log('  Stream proxy: http://localhost:' + PORT + '/stream?host=...&path=...&port=...');
});
