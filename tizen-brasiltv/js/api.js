var API = (function() {
    'use strict';

    var proxyBase = '';
    var isTizen = false;

    function init() {
        // Detect environment
        try {
            isTizen = typeof tizen !== 'undefined';
        } catch (e) {}

        if (isTizen && Config.PROXY_SERVER) {
            proxyBase = Config.PROXY_SERVER;
        } else if (!isTizen) {
            proxyBase = window.location.origin;
        }
    }

    function buildHeaders() {
        return {
            'Content-Type': 'application/json;charset=utf-8',
            'apk': Config.PACKAGE_NAME,
            'apkVer': Config.VERSION_CODE,
            'spkgVer': Config.SPKG_VERSION
        };
    }

    function request(url, options) {
        return new Promise(function(resolve, reject) {
            var xhr = new XMLHttpRequest();
            xhr.open(options.method || 'GET', url, true);

            var headers = options.headers || {};
            Object.keys(headers).forEach(function(key) {
                // Skip headers that browsers don't allow setting
                if (key.toLowerCase() === 'user-agent' || key.toLowerCase() === 'host') return;
                try { xhr.setRequestHeader(key, headers[key]); } catch(e) {}
            });

            xhr.timeout = options.timeout || 15000;
            xhr.onload = function() {
                if (xhr.status >= 200 && xhr.status < 300) {
                    try {
                        resolve(JSON.parse(xhr.responseText));
                    } catch (e) {
                        resolve(xhr.responseText);
                    }
                } else {
                    reject(new Error('HTTP ' + xhr.status));
                }
            };
            xhr.onerror = function() { reject(new Error('Network error')); };
            xhr.ontimeout = function() { reject(new Error('Timeout')); };
            xhr.send(options.body || null);
        });
    }

    function proxyUrl(host, path, port, method) {
        var url = proxyBase + '/proxy?host=' + encodeURIComponent(host) +
                  '&path=' + encodeURIComponent(path);
        if (port) url += '&port=' + port;
        if (method) url += '&method=' + method;
        return url;
    }

    function streamUrl(host, path, port) {
        return proxyBase + '/stream?host=' + encodeURIComponent(host) +
               '&path=' + encodeURIComponent(path) +
               '&port=' + (port || 80);
    }

    function fetchEPG(host) {
        var targetHost = host || Config.EPG_HOST;
        var url;

        if (proxyBase) {
            url = proxyUrl(targetHost, Config.EPG_PATH);
        } else {
            url = Config.EPG_PROTOCOL + '://' + targetHost + Config.EPG_PATH;
        }

        return request(url, {
            method: 'GET',
            headers: buildHeaders(),
            timeout: 30000
        });
    }

    function fetchEPGWithFallback() {
        return fetchEPG(Config.EPG_HOST).catch(function(err) {
            console.log('EPG main host failed (' + err.message + '), trying backup...');
            return fetchEPG(Config.EPG_BACKUP);
        });
    }

    function parseChannelCode(code) {
        if (!code) return null;
        var parts = code.split('_');
        if (parts.length < 3) return { prefix: '', id: code, quality: '' };
        var quality = parts[parts.length - 1];
        var prefix = parts[0];
        var id = parts.slice(1, -1).join('_');
        return { prefix: prefix, id: id, quality: quality, code: code };
    }

    function buildChannelList(epgData) {
        var channels = [];
        var seen = {};

        epgData.forEach(function(item) {
            var parsed = parseChannelCode(item.channelCode);
            if (!parsed || seen[item.channelCode]) return;
            seen[item.channelCode] = true;

            channels.push({
                code: item.channelCode,
                prefix: parsed.prefix,
                id: parsed.id,
                quality: parsed.quality,
                qualityLabel: Config.QUALITY_MAP[parsed.quality] || parsed.quality,
                name: '',
                programs: item.contents || [],
                streamUrl: null
            });
        });

        return channels;
    }

    function fetchStreamUrl(channelCode) {
        // Use the relay service to get captured stream URLs from the Android emulator.
        // The relay captures HLS requests the app makes when switching channels.
        return fetchRelayStream().catch(function(err) {
            console.warn('Relay stream fetch failed: ' + err.message);
            return null;
        });
    }

    function fetchRelayStream() {
        var url = proxyBase + '/relay?q=latest';
        return request(url, {
            method: 'GET',
            timeout: 5000
        }).then(function(data) {
            if (data && data.hlsProxyUrl) {
                return { url: proxyBase + data.hlsProxyUrl };
            }
            if (data && data.body) {
                // tproxy has cached M3U8 but no hlsProxyUrl — use /hls directly
                return { url: proxyBase + '/hls' };
            }
            return null;
        });
    }

    function fetchAllRelayStreams() {
        var url = proxyBase + '/relay?q=streams';
        return request(url, {
            method: 'GET',
            timeout: 5000
        }).then(function(data) {
            if (Array.isArray(data)) {
                return data.map(function(s) {
                    s.hlsProxyUrl = proxyBase + s.hlsProxyUrl;
                    return s;
                });
            }
            return [];
        }).catch(function() { return []; });
    }

    function fetchRelayStatus() {
        var url = proxyBase + '/relay?q=status';
        return request(url, { method: 'GET', timeout: 3000 })
            .catch(function() { return { active: 0, latest: null }; });
    }

    function getStreamProxyUrl(host, path, port) {
        if (proxyBase) {
            return streamUrl(host, path, port);
        }
        return 'http://' + host + ':' + port + path;
    }

    // Initialize on load
    init();

    return {
        fetchEPG: fetchEPGWithFallback,
        parseChannelCode: parseChannelCode,
        buildChannelList: buildChannelList,
        fetchStreamUrl: fetchStreamUrl,
        fetchRelayStream: fetchRelayStream,
        fetchAllRelayStreams: fetchAllRelayStreams,
        fetchRelayStatus: fetchRelayStatus,
        getStreamProxyUrl: getStreamProxyUrl,
        proxyUrl: proxyUrl,
        request: request
    };
})();
