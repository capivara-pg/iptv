var EPG = (function() {
    'use strict';

    var epgCache = null;
    var epgCacheTime = 0;
    var channelEpgMap = {};

    function loadEPG() {
        var now = Date.now();
        if (epgCache && (now - epgCacheTime) < Config.EPG_CACHE_DURATION) {
            return Promise.resolve(epgCache);
        }

        return API.fetchEPG().then(function(data) {
            epgCache = data;
            epgCacheTime = now;
            indexEPG(data);
            return data;
        });
    }

    function indexEPG(data) {
        channelEpgMap = {};
        data.forEach(function(item) {
            channelEpgMap[item.channelCode] = item.contents || [];
        });
    }

    function getPrograms(channelCode) {
        return channelEpgMap[channelCode] || [];
    }

    function getLatestDate() {
        var dates = {};
        Object.keys(channelEpgMap).forEach(function(code) {
            channelEpgMap[code].forEach(function(p) {
                dates[p.date] = true;
            });
        });
        var sorted = Object.keys(dates).sort();
        return sorted.length > 0 ? sorted[sorted.length - 1] : null;
    }

    function getCurrentProgram(channelCode) {
        var programs = getPrograms(channelCode);
        if (!programs.length) return null;

        var now = new Date();
        var todayStr = now.getFullYear() + '-' +
            String(now.getMonth() + 1).padStart(2, '0') + '-' +
            String(now.getDate()).padStart(2, '0');
        var currentTime = String(now.getHours()).padStart(2, '0') + ':' +
            String(now.getMinutes()).padStart(2, '0');

        // Try today first
        for (var i = 0; i < programs.length; i++) {
            var p = programs[i];
            if (p.date === todayStr && p.start <= currentTime && p.stop > currentTime) {
                return p;
            }
        }

        // Fall back to the latest available date
        var latestDate = getLatestDate();
        if (latestDate && latestDate !== todayStr) {
            for (var j = 0; j < programs.length; j++) {
                var q = programs[j];
                if (q.date === latestDate && q.start <= currentTime && q.stop > currentTime) {
                    return q;
                }
            }
        }

        return null;
    }

    function getTodayPrograms(channelCode) {
        var programs = getPrograms(channelCode);
        var now = new Date();
        var todayStr = now.getFullYear() + '-' +
            String(now.getMonth() + 1).padStart(2, '0') + '-' +
            String(now.getDate()).padStart(2, '0');

        var result = programs.filter(function(p) { return p.date === todayStr; });

        // Fall back to latest available date
        if (result.length === 0) {
            var latestDate = getLatestDate();
            if (latestDate) {
                result = programs.filter(function(p) { return p.date === latestDate; });
            }
        }

        return result;
    }

    function getProgramProgress(program) {
        if (!program) return 0;
        var now = new Date();
        var startParts = program.start.split(':');
        var stopParts = program.stop.split(':');
        var startMin = parseInt(startParts[0]) * 60 + parseInt(startParts[1]);
        var stopMin = parseInt(stopParts[0]) * 60 + parseInt(stopParts[1]);
        var nowMin = now.getHours() * 60 + now.getMinutes();

        if (stopMin <= startMin) stopMin += 1440;
        if (nowMin < startMin) nowMin += 1440;

        var total = stopMin - startMin;
        if (total <= 0) return 0;
        return Math.min(100, Math.max(0, ((nowMin - startMin) / total) * 100));
    }

    function getAllChannelCodes() {
        return Object.keys(channelEpgMap);
    }

    return {
        loadEPG: loadEPG,
        getPrograms: getPrograms,
        getCurrentProgram: getCurrentProgram,
        getTodayPrograms: getTodayPrograms,
        getProgramProgress: getProgramProgress,
        getAllChannelCodes: getAllChannelCodes
    };
})();
