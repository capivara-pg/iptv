var App = (function() {
    'use strict';

    var currentScreen = 'splash';
    var channels = [];
    var filteredChannels = [];
    var categories = [];
    var currentCategory = 'Channel List';
    var currentChannelIndex = -1;
    var channelNameMap = {};
    var clockTimer = null;

    function init() {
        Player.init();
        Navigation.init();
        startClock();
        loadData();
    }

    function loadData() {
        setSplashStatus('Carregando guia de programação...');

        EPG.loadEPG().then(function(epgData) {
            setSplashStatus('Processando canais...');
            channels = API.buildChannelList(epgData);
            enrichChannelNames(channels);
            buildCategories(channels);
            renderCategories();

            currentCategory = categories.length > 0 ? categories[0] : 'Channel List';
            filterChannels(currentCategory);
            renderChannels();

            showScreen('main');
            Navigation.updateFocusUI();
        }).catch(function(err) {
            console.error('Failed to load EPG:', err);
            setSplashStatus('Erro ao carregar dados. Tentando novamente...');
            setTimeout(loadData, 5000);
        });
    }

    function enrichChannelNames(channelList) {
        channelList.forEach(function(ch, idx) {
            ch.number = idx + 1;
            if (!ch.name) {
                ch.name = deriveChannelName(ch.code);
            }
        });
    }

    var KNOWN_NAMES = {
        'HBOFAMILYHD': 'HBO Family HD', 'HBOMAXHD': 'HBO Max HD',
        'HBOXTREMEUHD': 'HBO Xtreme UHD', 'MAXPRIMEHD': 'Max Prime HD',
        'MAX2HD': 'Max 2 HD', 'IDHD': 'ID HD',
        'GLOBOSPHD': 'Globo SP HD', 'GLOBONATALHD': 'Globo Natal HD',
        'CNNBRASILHD': 'CNN Brasil HD',
        'NSCTV': 'NSC TV', 'RICTVCURITIBA': 'RIC TV Curitiba',
        'REDEMINAS': 'Rede Minas', 'TVMAR': 'TV Mar',
        'TVWA': 'TV WA', 'TVE': 'TVE',
        'ESPNBRASILHD': 'ESPN Brasil HD', 'ESPNHDPY': 'ESPN HD',
        'WARNERCHANNELHD': 'Warner Channel HD',
        'SBTNORDESTE': 'SBT Nordeste',
        'GLOBORECIFE': 'Globo Recife',
        'GLOBOTVTRIBUNASANTOS': 'Globo TV Tribuna Santos',
        'TVTRIBUNASANTOS': 'TV Tribuna Santos',
        'SPORTVHD': 'SporTV HD', 'SPORTV2': 'SporTV 2',
        'SPORTV4': 'SporTV 4',
        'TVVANGUARDA': 'TV Vanguarda', 'TVTEMBAURUHD': 'TV Tembauru HD',
        'TVAPARECIDAHD': 'TV Aparecida HD', 'TVREDEGOSPEL': 'TV Rede Gospel',
        'FASHIONJUNIOR': 'Fashion Junior', 'RETROCARTOON': 'Retro Cartoon',
        'AGROMAIS': 'Agro Mais', 'CANALDOBOIHD': 'Canal do Boi HD',
        'EPTVSULDEMINAS': 'EPTV Sul de Minas', 'EPTVCAMPINAS': 'EPTV Campinas',
        'FOXSPORTSPREMIUMARGHD': 'Fox Sports Premium Arg HD',
        'FOXSPORTSHD': 'Fox Sports HD',
        'GLOBOBAHIA': 'Globo Bahia', 'TVLIBERAL': 'TV Liberal',
        'REDEVIDA4K': 'Rede Vida 4K', 'REDEVIDAHD': 'Rede Vida HD',
        'BLOOMBERG': 'Bloomberg', 'TVRECORDMINAS': 'TV Record Minas',
        'TVMORENA': 'TV Morena', 'TVANHANGUERA': 'TV Anhanguera',
        'TVCENTROAMERICA': 'TV Centro América',
        'TVCIDADEVERDE': 'TV Cidade Verde', 'TVOPOVO': 'TV O Povo',
        'TVSERGIPE': 'TV Sergipe',
        'RECORDTVRS': 'Record TV RS', 'RECORDTVRIO': 'Record TV Rio',
        'RECORDTVBELEM': 'Record TV Belém',
        'RECORDTVBRASILIA': 'Record TV Brasília',
        'RECORDTVITAPOAN': 'Record TV Itapoã',
        'RECORDTVGOIAS': 'Record TV Goiás',
        'RPCFOZDOIGUACU': 'RPC Foz do Iguaçu',
        'RPCPONTAGROSSA': 'RPC Ponta Grossa',
        'BANDNATALHD': 'Band Natal HD',
        'HBOPOPHDCYX': 'HBO Pop HD',
        'DISCOVERYKIDSHD': 'Discovery Kids HD',
        'WARNERHD': 'Warner HD',
    };

    function deriveChannelName(code) {
        if (!code) return 'Canal';
        var s = code;

        // Strip quality suffixes
        s = s.replace(/[_ ]?(480p|720p|1080p|UHD|4K)([_ ]?(2[Mm]|264))?$/i, '');
        s = s.replace(/_264$/, '');
        s = s.replace(/_480$/, '');

        // Strip known prefixes
        s = s.replace(/^(cyp-etv-|cyx-|cyp-|cyp_|pt_|puyu_|yc_|fq)/, '');

        // Check known name mappings
        var normalized = s.replace(/[_ +\-]/g, '').toUpperCase();
        if (KNOWN_NAMES[normalized]) return KNOWN_NAMES[normalized];

        // If what remains looks like a random ID, use fallback
        if (/^[0-9a-f]{8,}$/i.test(s) || /^[0-9A-F]{8,}[-0-9A-F]*$/.test(s)) {
            return 'Canal';
        }
        if (/^[A-Za-z0-9]{12,}$/.test(s)) {
            var upper = (s.match(/[A-Z]/g) || []).length;
            var lower = (s.match(/[a-z]/g) || []).length;
            var digit = (s.match(/[0-9]/g) || []).length;
            if (upper > 0 && lower > 0 && digit > 0) {
                return 'Canal';
            }
            if (s.length >= 16 && !/(TV|ESPN|HBO|SBT|CNN|Globo|Record|Band|Rede|Fox|Sport|Warner|Cartoon|Discovery|Nick|Disney|EPTV|RPC|NSC|RIC)/i.test(s)) {
                return 'Canal';
            }
        }

        // Clean up separators
        s = s.replace(/[_]+/g, ' ').trim();

        // Insert spaces in CamelCase
        s = s.replace(/([a-z])([A-Z])/g, '$1 $2');
        s = s.replace(/([A-Z]+)([A-Z][a-z])/g, '$1 $2');

        // Separate "TV" from adjacent words
        s = s.replace(/\bTV([A-Z])/g, 'TV $1');
        s = s.replace(/([a-z])(TV)\b/g, '$1 $2');

        s = s.replace(/\s+/g, ' ').trim();
        s = s.replace(/[+\-]+$/, '').trim();

        if (!s || s.length < 2) return 'Canal';
        return s;
    }

    var categoryPrefixMap = {};

    function buildCategories(channelList) {
        // Count channels per normalized prefix
        var counts = {};
        channelList.forEach(function(ch) {
            var key = normalizeCategoryPrefix(ch.prefix);
            counts[key] = (counts[key] || 0) + 1;
        });

        categories = ['Todos'];
        categoryPrefixMap = {};
        var seen = {};

        // Only create categories for prefixes with 3+ channels
        var majorPrefixes = ['pt', 'cyp', ''];
        channelList.forEach(function(ch) {
            var key = normalizeCategoryPrefix(ch.prefix);
            if (counts[key] < 3 && majorPrefixes.indexOf(key) < 0) {
                key = '';
            }
            if (!seen[key]) {
                seen[key] = true;
                var label = categoryLabel(key);
                categories.push(label);
                categoryPrefixMap[label] = key;
            }
        });
    }

    function normalizeCategoryPrefix(prefix) {
        if (!prefix) return '';
        var p = prefix.toLowerCase();
        if (p === 'cyp' || p === 'cyp-etv' || p === 'cyx') return 'cyp';
        return p;
    }

    function categoryLabel(normalizedPrefix) {
        var labels = {
            'pt': 'Brasil',
            'cyp': 'Premium',
            'puyu': 'Religioso',
            'yc': 'Internacional',
            'test': 'Teste',
            '': 'Outros'
        };
        return labels[normalizedPrefix] || normalizedPrefix.toUpperCase();
    }

    function filterChannels(category) {
        currentCategory = category;
        if (category === 'Todos') {
            filteredChannels = channels.slice();
        } else {
            var targetKey = categoryPrefixMap[category];
            filteredChannels = channels.filter(function(ch) {
                var key = normalizeCategoryPrefix(ch.prefix);
                // Channels from minor prefixes fall into 'Outros'
                if (targetKey === '' && categoryPrefixMap[categoryLabel(key)] === undefined) {
                    return true;
                }
                return key === targetKey;
            });
        }
    }

    function renderCategories() {
        var container = document.getElementById('category-list');
        container.innerHTML = '';
        categories.forEach(function(cat, i) {
            var el = document.createElement('div');
            el.className = 'category-item' + (cat === currentCategory ? ' selected' : '');
            el.textContent = cat;
            el.setAttribute('data-index', i);
            el.addEventListener('click', (function(catIdx) {
                return function() { selectCategory(catIdx); };
            })(i));
            container.appendChild(el);
        });
    }

    function renderChannels() {
        var container = document.getElementById('channel-list');
        container.innerHTML = '';

        filteredChannels.forEach(function(ch, i) {
            var program = EPG.getCurrentProgram(ch.code);
            var el = document.createElement('div');
            el.className = 'channel-item';
            if (currentChannelIndex >= 0 && channels[currentChannelIndex] &&
                channels[currentChannelIndex].code === ch.code) {
                el.className += ' playing';
            }

            var qualityClass = (ch.quality === '720p' || ch.quality === '1080p') ? ' hd' : '';

            el.innerHTML =
                '<div class="channel-num">' + String(ch.number).padStart(3, '0') + '</div>' +
                '<div class="channel-logo">' + ch.prefix.toUpperCase().substring(0, 3) + '</div>' +
                '<div class="channel-info">' +
                    '<div class="channel-name">' + escapeHtml(ch.name) + '</div>' +
                    '<div class="channel-program">' +
                        (program ? escapeHtml(program.title) : 'Sem informação') +
                    '</div>' +
                '</div>' +
                '<div class="channel-quality' + qualityClass + '">' + ch.qualityLabel + '</div>';

            el.setAttribute('data-index', i);
            el.addEventListener('click', (function(channelIdx) {
                return function() {
                    var ch = filteredChannels[channelIdx];
                    if (!ch) return;
                    var globalIdx = channels.indexOf(ch);
                    if (globalIdx < 0) globalIdx = channelIdx;
                    playChannel(globalIdx);
                };
            })(i));
            container.appendChild(el);
        });
    }

    function updateEPGPanel(channelIdx) {
        var ch = filteredChannels[channelIdx];
        if (!ch) return;

        var nameEl = document.getElementById('epg-channel-name');
        nameEl.textContent = ch.name + ' (' + ch.qualityLabel + ')';

        var programsEl = document.getElementById('epg-programs');
        var todayPrograms = EPG.getTodayPrograms(ch.code);

        if (todayPrograms.length === 0) {
            programsEl.innerHTML = '<div style="padding:20px;color:var(--text-muted);">' +
                'Sem programação disponível</div>';
            return;
        }

        var now = new Date();
        var currentTime = String(now.getHours()).padStart(2, '0') + ':' +
            String(now.getMinutes()).padStart(2, '0');

        var html = '';
        todayPrograms.forEach(function(p) {
            var isCurrent = p.start <= currentTime && p.stop > currentTime;
            html += '<div class="epg-program-item' + (isCurrent ? ' current' : '') + '">' +
                '<div class="epg-time">' + p.start + ' - ' + p.stop + '</div>' +
                '<div class="epg-title">' + escapeHtml(p.title || 'Sem título') + '</div>' +
                (p.desc ? '<div class="epg-desc">' + escapeHtml(p.desc) + '</div>' : '') +
                '</div>';
        });

        programsEl.innerHTML = html;

        // Scroll to current program
        var currentItem = programsEl.querySelector('.current');
        if (currentItem) {
            currentItem.scrollIntoView({ block: 'center', behavior: 'smooth' });
        }
    }

    function playSelectedChannel() {
        var idx = Navigation.getFocusedChannelIndex();
        var ch = filteredChannels[idx];
        if (!ch) return;

        // Find global index
        var globalIdx = channels.indexOf(ch);
        if (globalIdx < 0) globalIdx = idx;

        playChannel(globalIdx);
    }

    function playChannel(globalIndex) {
        var ch = channels[globalIndex];
        if (!ch) return;

        currentChannelIndex = globalIndex;
        showScreen('player');

        var program = EPG.getCurrentProgram(ch.code);
        Player.updateOSD(
            String(ch.number).padStart(3, '0'),
            ch.name,
            program
        );
        Player.showOSD();

        // Try to get stream URL
        if (ch.streamUrl) {
            Player.play(ch.streamUrl);
        } else {
            resolveAndPlay(ch);
        }
    }

    function resolveAndPlay(channel) {
        showError('Buscando stream via relay...');
        API.fetchRelayStream().then(function(result) {
            if (result && result.url) {
                channel.streamUrl = result.url;
                Player.play(result.url);
            } else {
                showError('Nenhum stream capturado. Troque de canal no app Android.');
                setTimeout(function() { showScreen('main'); }, 5000);
            }
        }).catch(function(err) {
            showError('Relay indisponível: ' + err.message);
            setTimeout(function() { showScreen('main'); }, 5000);
        });
    }

    function channelUp() {
        if (currentChannelIndex < 0) return;
        var next = (currentChannelIndex + 1) % channels.length;
        playChannel(next);
    }

    function channelDown() {
        if (currentChannelIndex < 0) return;
        var prev = currentChannelIndex - 1;
        if (prev < 0) prev = channels.length - 1;
        playChannel(prev);
    }

    function switchToChannel(number) {
        var target = channels.find(function(ch) { return ch.number === number; });
        if (target) {
            var idx = channels.indexOf(target);
            playChannel(idx);
        }
    }

    function selectCategory(catIndex) {
        var cat = categories[catIndex];
        if (!cat) return;
        filterChannels(cat);
        Navigation.resetChannelFocus();
        renderChannels();
        renderCategories();
    }

    function showScreen(name) {
        document.querySelectorAll('.screen').forEach(function(el) {
            el.classList.remove('active');
        });
        var screen = document.getElementById(name + '-screen');
        if (screen) {
            screen.classList.add('active');
            currentScreen = name;
        }
    }

    function getCurrentScreen() {
        return currentScreen;
    }

    function showSettings() {
        showScreen('settings');
    }

    function toggleSetting(index) {
        // Placeholder for settings toggling
        console.log('Toggle setting:', index);
    }

    function showError(message) {
        var toast = document.querySelector('.error-toast');
        if (!toast) {
            toast = document.createElement('div');
            toast.className = 'error-toast';
            document.body.appendChild(toast);
        }
        toast.textContent = message;
        toast.classList.add('visible');
        setTimeout(function() { toast.classList.remove('visible'); }, 4000);
    }

    function setSplashStatus(text) {
        var el = document.querySelector('.splash-status');
        if (el) el.textContent = text;
    }

    function startClock() {
        function update() {
            var now = new Date();
            var time = String(now.getHours()).padStart(2, '0') + ':' +
                       String(now.getMinutes()).padStart(2, '0');
            var dateStr = String(now.getDate()).padStart(2, '0') + '/' +
                          String(now.getMonth() + 1).padStart(2, '0');
            var display = dateStr + ' ' + time;

            var clockEl = document.getElementById('clock');
            if (clockEl) clockEl.textContent = display;
            var osdClockEl = document.getElementById('osd-clock');
            if (osdClockEl) osdClockEl.textContent = time;
        }
        update();
        clockTimer = setInterval(update, 30000);
    }

    function exit() {
        try {
            if (typeof tizen !== 'undefined') {
                tizen.application.getCurrentApplication().exit();
            }
        } catch(e) {
            console.log('Exit not available in this environment');
        }
    }

    function escapeHtml(str) {
        if (!str) return '';
        return str.replace(/&/g, '&amp;').replace(/</g, '&lt;')
                  .replace(/>/g, '&gt;').replace(/"/g, '&quot;');
    }

    // Auto-init when DOM is ready
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', init);
    } else {
        init();
    }

    return {
        getCurrentScreen: getCurrentScreen,
        showScreen: showScreen,
        playSelectedChannel: playSelectedChannel,
        channelUp: channelUp,
        channelDown: channelDown,
        switchToChannel: switchToChannel,
        selectCategory: selectCategory,
        updateEPGPanel: updateEPGPanel,
        showSettings: showSettings,
        toggleSetting: toggleSetting,
        exit: exit
    };
})();
