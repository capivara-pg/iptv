/**
 * Modo demo — streams HLS públicos para validar o player na TV.
 * Cada canal recebe um stream de teste ciclando a lista.
 * Quando o login/play do portal for resolvido (fase B),
 * este módulo é substituído pelo resolver real.
 */
var Demo = (function() {
    'use strict';

    var STREAMS = [
        'https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8',
        'https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8',
        'https://test-streams.mux.dev/pts_shift/master.m3u8',
        'https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8'
    ];

    function streamFor(channelNumber) {
        return STREAMS[channelNumber % STREAMS.length];
    }

    function isActive() {
        return localStorage.getItem('demo_mode') === '1';
    }

    function setActive(on) {
        localStorage.setItem('demo_mode', on ? '1' : '0');
    }

    return {
        streamFor: streamFor,
        isActive: isActive,
        setActive: setActive
    };
})();