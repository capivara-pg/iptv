var Player = (function() {
    'use strict';

    var avPlayer = null;
    var html5Player = null;
    var currentUrl = null;
    var isPlaying = false;
    var useAVPlay = false;
    var osdTimer = null;
    var callbacks = {};

    function init() {
        html5Player = document.getElementById('html5-player');

        // Detect Samsung Tizen AVPlay API
        try {
            if (typeof webapis !== 'undefined' && webapis.avplay) {
                useAVPlay = true;
                avPlayer = document.getElementById('av-player');
                console.log('Using Samsung AVPlay for playback');
            }
        } catch (e) {
            console.log('AVPlay not available, falling back to HTML5 video');
        }

        if (!useAVPlay) {
            html5Player.style.display = 'block';
            html5Player.addEventListener('error', function(e) {
                console.error('Video error:', e);
                if (callbacks.onError) callbacks.onError(e);
            });
            html5Player.addEventListener('playing', function() {
                if (callbacks.onPlaying) callbacks.onPlaying();
            });
            html5Player.addEventListener('waiting', function() {
                if (callbacks.onBuffering) callbacks.onBuffering();
            });
        }
    }

    function play(url) {
        if (!url) {
            console.error('No stream URL provided');
            if (callbacks.onError) callbacks.onError(new Error('No stream URL'));
            return;
        }

        stop();
        currentUrl = url;

        if (useAVPlay) {
            playAVPlay(url);
        } else {
            playHTML5(url);
        }
    }

    function playAVPlay(url) {
        try {
            webapis.avplay.open(url);
            webapis.avplay.setDisplayRect(0, 0, 1920, 1080);
            webapis.avplay.setDisplayMethod('PLAYER_DISPLAY_MODE_FULL_SCREEN');

            var listener = {
                onbufferingstart: function() {
                    if (callbacks.onBuffering) callbacks.onBuffering();
                },
                onbufferingprogress: function(percent) {},
                onbufferingcomplete: function() {
                    if (callbacks.onPlaying) callbacks.onPlaying();
                },
                oncurrentplaytime: function(ms) {},
                onevent: function(eventType, eventData) {
                    console.log('AVPlay event:', eventType, eventData);
                },
                onerror: function(eventType) {
                    console.error('AVPlay error:', eventType);
                    if (callbacks.onError) callbacks.onError(new Error(eventType));
                },
                onsubtitlechange: function() {},
                onstreamcompleted: function() {
                    if (callbacks.onEnded) callbacks.onEnded();
                },
                ondrmevent: function() {}
            };

            webapis.avplay.setListener(listener);
            webapis.avplay.prepareAsync(function() {
                webapis.avplay.play();
                isPlaying = true;
            }, function(error) {
                console.error('AVPlay prepare error:', error);
                if (callbacks.onError) callbacks.onError(error);
            });
        } catch (e) {
            console.error('AVPlay exception:', e);
            if (callbacks.onError) callbacks.onError(e);
        }
    }

    var hlsInstance = null;

    function playHTML5(url) {
        // Clean up previous HLS instance
        if (hlsInstance) {
            hlsInstance.destroy();
            hlsInstance = null;
        }

        var isHLS = url.includes('.m3u8') || url.includes('mpegurl') || url.includes('/hls');

        if (isHLS && typeof Hls !== 'undefined' && Hls.isSupported()) {
            hlsInstance = new Hls({
                maxBufferLength: 10,
                maxMaxBufferLength: 30,
                liveSyncDuration: 3,
                liveMaxLatencyDuration: 15,
                liveDurationInfinity: true,
                levelLoadingMaxRetry: 10,
                fragLoadingMaxRetry: 5,
                manifestLoadingMaxRetry: 10,
                fragLoadingRetryDelay: 1000,
            });
            hlsInstance.loadSource(url);
            hlsInstance.attachMedia(html5Player);
            hlsInstance.on(Hls.Events.MANIFEST_PARSED, function() {
                html5Player.play().then(function() {
                    isPlaying = true;
                }).catch(function(e) {
                    console.error('HLS play error:', e);
                    if (callbacks.onError) callbacks.onError(e);
                });
            });
            hlsInstance.on(Hls.Events.ERROR, function(event, data) {
                console.error('HLS error:', data.type, data.details);
                if (data.fatal) {
                    if (callbacks.onError) callbacks.onError(new Error(data.details));
                }
            });
        } else if (isHLS && html5Player.canPlayType('application/vnd.apple.mpegurl')) {
            // Safari native HLS
            html5Player.src = url;
            html5Player.play().then(function() { isPlaying = true; })
                .catch(function(e) { if (callbacks.onError) callbacks.onError(e); });
        } else {
            html5Player.src = url;
            html5Player.play().then(function() { isPlaying = true; })
                .catch(function(e) {
                    console.error('HTML5 play error:', e);
                    if (callbacks.onError) callbacks.onError(e);
                });
        }
    }

    function stop() {
        isPlaying = false;
        currentUrl = null;

        if (useAVPlay) {
            try {
                var state = webapis.avplay.getState();
                if (state !== 'NONE' && state !== 'IDLE') {
                    webapis.avplay.stop();
                }
                webapis.avplay.close();
            } catch (e) {}
        } else {
            if (hlsInstance) {
                hlsInstance.destroy();
                hlsInstance = null;
            }
            html5Player.pause();
            html5Player.removeAttribute('src');
            html5Player.load();
        }
    }

    function showOSD() {
        var osd = document.getElementById('player-osd');
        osd.classList.remove('hidden');
        clearTimeout(osdTimer);
        osdTimer = setTimeout(hideOSD, Config.OSD_TIMEOUT);
    }

    function hideOSD() {
        document.getElementById('player-osd').classList.add('hidden');
    }

    function toggleOSD() {
        var osd = document.getElementById('player-osd');
        if (osd.classList.contains('hidden')) {
            showOSD();
        } else {
            hideOSD();
        }
    }

    function updateOSD(channelNum, channelName, program) {
        document.getElementById('osd-channel-num').textContent = channelNum || '';
        document.getElementById('osd-channel-name').textContent = channelName || '';
        document.getElementById('osd-clock').textContent = formatTime(new Date());

        if (program) {
            document.getElementById('osd-program-title').textContent = program.title || '';
            document.getElementById('osd-program-time').textContent =
                (program.start || '') + ' - ' + (program.stop || '');
            var progress = EPG.getProgramProgress(program);
            document.getElementById('osd-progress-bar').style.width = progress + '%';
        } else {
            document.getElementById('osd-program-title').textContent = '';
            document.getElementById('osd-program-time').textContent = '';
            document.getElementById('osd-progress-bar').style.width = '0%';
        }
    }

    function formatTime(date) {
        return String(date.getHours()).padStart(2, '0') + ':' +
               String(date.getMinutes()).padStart(2, '0');
    }

    function on(event, cb) {
        callbacks[event] = cb;
    }

    return {
        init: init,
        play: play,
        stop: stop,
        showOSD: showOSD,
        hideOSD: hideOSD,
        toggleOSD: toggleOSD,
        updateOSD: updateOSD,
        on: on,
        isPlaying: function() { return isPlaying; }
    };
})();
