#!/usr/bin/env node
'use strict';

// Stream Relay v2 — captures HLS stream URLs AND M3U8 response bodies
// from the Android emulator via tcpdump.
//
// The stream server uses one-time tokens — replaying a captured URL returns 409.
// So we capture both the request (for cookies) and the response body (for M3U8 content).
// For TS segments, we re-fetch them from the host using captured cookies.
//
// Flow:
//   1. User switches channel on Android
//   2. tcpdump captures the M3U8 request (URL + cookies) AND response body (#EXTM3U...)
//   3. Browser gets cached M3U8 with rewritten segment URLs
//   4. For each TS segment, proxy fetches from stream server using captured cookies

const { spawn } = require('child_process');
const http = require('http');

const ADB_SERIAL = process.env.ADB_SERIAL || 'emulator-5554';

// Latest captured M3U8 with full body
let latestM3U8 = null;
// Last known M3U8 request path (separate from cookie paths which get overwritten by TS requests)
let lastM3U8RequestPath = null;
// Active stream cookies per host:port (for TS segment fetching)
const streamCookies = new Map();

function startCapture() {
    console.log('[RELAY] Starting tcpdump capture on', ADB_SERIAL);

    const tcpdump = spawn('adb', [
        '-s', ADB_SERIAL, 'shell',
        'su', '0', 'tcpdump', '-i', 'any',
        '-A', '-s', '0',
        'tcp port 80 or tcp port 30813',
        '-l'
    ]);

    let buffer = '';

    tcpdump.stdout.on('data', (data) => {
        buffer += data.toString();
        processBuffer();
    });

    tcpdump.stderr.on('data', (data) => {
        const msg = data.toString().trim();
        if (msg && !msg.includes('listening on')) {
            console.error('[RELAY] tcpdump stderr:', msg);
        }
    });

    tcpdump.on('close', (code) => {
        console.log('[RELAY] tcpdump exited with code', code, '— restarting in 3s');
        setTimeout(startCapture, 3000);
    });

    function processBuffer() {
        const packets = buffer.split(/(?=\d{2}:\d{2}:\d{2}\.\d+)/);
        buffer = packets.pop() || '';
        for (const pkt of packets) {
            parsePacket(pkt);
        }
    }
}

function parsePacket(raw) {
    // Check for HTTP request (GET)
    const reqMatch = raw.match(/GET\s+(\/[^\s]*)\s+HTTP\/1\.[01]/);
    if (reqMatch) {
        parseRequest(raw, reqMatch[1]);
        return;
    }

    // Check for HTTP response containing M3U8
    if (raw.includes('#EXTM3U')) {
        parseM3U8Response(raw);
    }
}

function parseRequest(raw, path) {
    const hostMatch = raw.match(/Host:\s*([^\s\r\n]+)/i);
    const hostRaw = hostMatch ? hostMatch[1] : null;
    if (!hostRaw) return;

    const hostPortMatch = hostRaw.match(/^(.+):(\d+)$/);
    const host = hostPortMatch ? hostPortMatch[1] : hostRaw;
    const hostPort = hostPortMatch ? parseInt(hostPortMatch[2]) : 80;

    const cookieMatch = raw.match(/Cookie:\s*([^\r\n]+)/i);
    const cookies = cookieMatch ? cookieMatch[1].trim() : '';

    // Filter noise
    if (host === 'localhost' || host === '127.0.0.1' || host === '10.0.2.2') return;
    if (path.includes('/epg/')) return;

    // Store cookies for this host:port (needed for TS segment fetching)
    if (cookies && cookies.includes('d=')) {
        const key = host + ':' + hostPort;
        streamCookies.set(key, {
            host, port: hostPort, cookies, path,
            capturedAt: Date.now()
        });
        console.log(`[RELAY] Captured cookies for ${key}: ${cookies.substring(0, 60)}...`);

        // Track non-TS request paths as likely M3U8 playlist paths
        if (!path.endsWith('.ts') && hostPort === 30813) {
            lastM3U8RequestPath = { host, port: hostPort, path, cookies, capturedAt: Date.now() };
            console.log(`[RELAY] Detected M3U8 request path: ${path}`);
        }
    }
}

function parseM3U8Response(raw) {
    // Extract the M3U8 body from the packet
    // Look for #EXTM3U and capture everything from there
    const m3u8Start = raw.indexOf('#EXTM3U');
    if (m3u8Start < 0) return;

    let m3u8Body = raw.substring(m3u8Start);
    // Clean up: remove any trailing tcpdump noise (timestamp lines)
    m3u8Body = m3u8Body.replace(/\n\d{2}:\d{2}:\d{2}\.\d+.*/s, '');
    // Remove trailing whitespace
    m3u8Body = m3u8Body.trimEnd();

    // Validate it looks like a real M3U8
    if (!m3u8Body.includes('#EXTINF') && !m3u8Body.includes('#EXT-X-')) {
        return;
    }

    // Try to determine which host this response came from
    // Look for HTTP response header before the body
    const httpMatch = raw.match(/HTTP\/1\.[01]\s+200/);
    if (!httpMatch) return;

    // Find the most recently captured request cookies
    let bestEntry = null;
    let bestTime = 0;
    for (const [key, entry] of streamCookies) {
        if (entry.capturedAt > bestTime) {
            bestTime = entry.capturedAt;
            bestEntry = entry;
        }
    }

    if (!bestEntry) {
        console.log('[RELAY] Got M3U8 response but no request cookies captured yet');
        return;
    }

    // Prefer the tracked M3U8 request path over the generic cookie path
    const m3u8Path = lastM3U8RequestPath ? lastM3U8RequestPath.path : bestEntry.path;
    const m3u8Host = lastM3U8RequestPath ? lastM3U8RequestPath.host : bestEntry.host;
    const m3u8Port = lastM3U8RequestPath ? lastM3U8RequestPath.port : bestEntry.port;

    console.log(`[RELAY] Captured M3U8 response body (${m3u8Body.length} bytes)`);
    console.log(`[RELAY]   Lines: ${m3u8Body.split('\n').length}`);
    console.log(`[RELAY]   Host: ${m3u8Host}:${m3u8Port}`);
    console.log(`[RELAY]   Path: ${m3u8Path}`);

    latestM3U8 = {
        body: m3u8Body,
        host: m3u8Host,
        port: m3u8Port,
        path: m3u8Path,
        cookies: bestEntry.cookies,
        capturedAt: Date.now(),
    };
}

// HTTP API
function startAPI(port) {
    const server = http.createServer((req, res) => {
        const headers = {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
        };

        if (req.url === '/status') {
            res.writeHead(200, headers);
            res.end(JSON.stringify({
                hasM3U8: !!latestM3U8,
                cookieEntries: streamCookies.size,
                latest: latestM3U8 ? {
                    host: latestM3U8.host,
                    port: latestM3U8.port,
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

        // Serve the raw M3U8 body (for direct HLS playback)
        if (req.url === '/m3u8') {
            if (!latestM3U8) {
                res.writeHead(404, headers);
                res.end(JSON.stringify({ error: 'No M3U8 captured yet' }));
                return;
            }
            res.writeHead(200, {
                'Content-Type': 'application/vnd.apple.mpegurl',
                'Access-Control-Allow-Origin': '*',
            });
            res.end(latestM3U8.body);
            return;
        }

        // Return cookies for a given host:port (for TS segment proxy)
        const urlParts = require('url').parse(req.url, true);
        if (urlParts.pathname === '/cookies') {
            const host = urlParts.query.host;
            const port = urlParts.query.port || '30813';
            const key = host + ':' + port;
            const entry = streamCookies.get(key);
            if (entry) {
                res.writeHead(200, headers);
                res.end(JSON.stringify({ cookies: entry.cookies }));
            } else {
                // Return latest cookies as fallback
                if (latestM3U8 && latestM3U8.cookies) {
                    res.writeHead(200, headers);
                    res.end(JSON.stringify({ cookies: latestM3U8.cookies }));
                } else {
                    res.writeHead(404, headers);
                    res.end(JSON.stringify({ error: 'No cookies for ' + key }));
                }
            }
            return;
        }

        if (req.url.startsWith('/clear')) {
            latestM3U8 = null;
            streamCookies.clear();
            res.writeHead(200, headers);
            res.end(JSON.stringify({ cleared: true }));
            return;
        }

        res.writeHead(404, headers);
        res.end(JSON.stringify({ error: 'Not found' }));
    });

    server.listen(port, '0.0.0.0', () => {
        console.log(`[RELAY] API server running on http://0.0.0.0:${port}`);
        console.log('[RELAY] Endpoints:');
        console.log('  GET /status  — capture status');
        console.log('  GET /latest  — latest M3U8 (JSON with body)');
        console.log('  GET /m3u8    — raw M3U8 content');
        console.log('  GET /cookies?host=...&port=... — cookies for TS fetching');
        console.log('  GET /clear   — reset cache');
    });
}

const API_PORT = parseInt(process.env.RELAY_PORT) || 8091;
startAPI(API_PORT);
startCapture();
