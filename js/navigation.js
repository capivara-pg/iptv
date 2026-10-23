var Navigation = (function() {
    'use strict';

    var KEY = {
        LEFT: 37, UP: 38, RIGHT: 39, DOWN: 40,
        ENTER: 13, BACK: 10009, ESC: 27,
        RED: 403, GREEN: 404, YELLOW: 405, BLUE: 406,
        CH_UP: 427, CH_DOWN: 428,
        PLAY: 415, PAUSE: 19, STOP: 413,
        INFO: 457,
        ZERO: 48, ONE: 49, TWO: 50, THREE: 51, FOUR: 52,
        FIVE: 53, SIX: 54, SEVEN: 55, EIGHT: 56, NINE: 57
    };

    var focusZone = 'channels';
    var focusIndex = { categories: 0, channels: 0, settings: 0 };
    var channelSwitchBuffer = '';
    var channelSwitchTimer = null;

    function init() {
        document.addEventListener('keydown', handleKeyDown);

        // Register Samsung TV keys
        try {
            if (typeof tizen !== 'undefined') {
                var keys = ['MediaPlay', 'MediaPause', 'MediaStop',
                           'ColorF0Red', 'ColorF1Green', 'ColorF2Yellow', 'ColorF3Blue',
                           'ChannelUp', 'ChannelDown', 'Info',
                           '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
                keys.forEach(function(k) {
                    try { tizen.tvinputdevice.registerKey(k); } catch(e) {}
                });
            }
        } catch(e) {
            console.log('Not running on Tizen TV, using keyboard controls');
        }
    }

    function handleKeyDown(e) {
        var keyCode = e.keyCode;

        // Map ESC to BACK for desktop testing
        if (keyCode === KEY.ESC) keyCode = KEY.BACK;

        // Map keyboard Enter
        if (keyCode === 13) keyCode = KEY.ENTER;

        e.preventDefault();

        var screen = App.getCurrentScreen();

        if (screen === 'player') {
            handlePlayerKeys(keyCode);
        } else if (screen === 'main') {
            handleMainKeys(keyCode);
        } else if (screen === 'settings') {
            handleSettingsKeys(keyCode);
        }
    }

    function handleMainKeys(keyCode) {
        switch (keyCode) {
            case KEY.UP:
                moveFocus(-1);
                break;
            case KEY.DOWN:
                moveFocus(1);
                break;
            case KEY.LEFT:
                if (focusZone === 'channels') {
                    focusZone = 'categories';
                    updateFocusUI();
                }
                break;
            case KEY.RIGHT:
                if (focusZone === 'categories') {
                    focusZone = 'channels';
                    updateFocusUI();
                }
                break;
            case KEY.ENTER:
                if (focusZone === 'channels') {
                    App.playSelectedChannel();
                } else if (focusZone === 'categories') {
                    App.selectCategory(focusIndex.categories);
                    focusZone = 'channels';
                    focusIndex.channels = 0;
                    updateFocusUI();
                }
                break;
            case KEY.BACK:
                if (focusZone === 'channels') {
                    focusZone = 'categories';
                    updateFocusUI();
                } else {
                    App.exit();
                }
                break;
            case KEY.RED:
                App.showSettings();
                break;
        }
    }

    function handlePlayerKeys(keyCode) {
        // Number keys for direct channel entry
        if (keyCode >= KEY.ZERO && keyCode <= KEY.NINE) {
            var digit = String(keyCode - KEY.ZERO);
            channelSwitchBuffer += digit;
            showChannelSwitch(channelSwitchBuffer);
            clearTimeout(channelSwitchTimer);
            channelSwitchTimer = setTimeout(function() {
                var num = parseInt(channelSwitchBuffer);
                channelSwitchBuffer = '';
                hideChannelSwitch();
                App.switchToChannel(num);
            }, Config.CHANNEL_SWITCH_TIMEOUT);
            return;
        }

        switch (keyCode) {
            case KEY.UP:
            case KEY.CH_UP:
                App.channelUp();
                Player.showOSD();
                break;
            case KEY.DOWN:
            case KEY.CH_DOWN:
                App.channelDown();
                Player.showOSD();
                break;
            case KEY.ENTER:
            case KEY.INFO:
                Player.toggleOSD();
                break;
            case KEY.BACK:
                Player.stop();
                App.showScreen('main');
                break;
            case KEY.STOP:
                Player.stop();
                App.showScreen('main');
                break;
        }
    }

    function handleSettingsKeys(keyCode) {
        switch (keyCode) {
            case KEY.UP:
                focusIndex.settings = Math.max(0, focusIndex.settings - 1);
                updateSettingsFocus();
                break;
            case KEY.DOWN:
                var items = document.querySelectorAll('.setting-item');
                focusIndex.settings = Math.min(items.length - 1, focusIndex.settings + 1);
                updateSettingsFocus();
                break;
            case KEY.ENTER:
                App.toggleSetting(focusIndex.settings);
                break;
            case KEY.BACK:
                App.showScreen('main');
                break;
        }
    }

    function moveFocus(delta) {
        var zone = focusZone;
        var items;

        if (zone === 'categories') {
            items = document.querySelectorAll('.category-item');
            focusIndex.categories = clamp(focusIndex.categories + delta, 0, items.length - 1);
        } else if (zone === 'channels') {
            items = document.querySelectorAll('.channel-item');
            focusIndex.channels = clamp(focusIndex.channels + delta, 0, items.length - 1);
        }

        updateFocusUI();
    }

    function updateFocusUI() {
        // Categories
        var cats = document.querySelectorAll('.category-item');
        cats.forEach(function(el, i) {
            el.classList.toggle('focused', focusZone === 'categories' && i === focusIndex.categories);
        });

        // Channels
        var chans = document.querySelectorAll('.channel-item');
        chans.forEach(function(el, i) {
            el.classList.toggle('focused', focusZone === 'channels' && i === focusIndex.channels);
        });

        // Scroll focused item into view
        var focused;
        if (focusZone === 'channels') {
            focused = chans[focusIndex.channels];
            if (focused) {
                focused.scrollIntoView({ block: 'nearest', behavior: 'smooth' });
            }
            App.updateEPGPanel(focusIndex.channels);
        } else {
            focused = cats[focusIndex.categories];
            if (focused) {
                focused.scrollIntoView({ block: 'nearest', behavior: 'smooth' });
            }
        }
    }

    function updateSettingsFocus() {
        var items = document.querySelectorAll('.setting-item');
        items.forEach(function(el, i) {
            el.classList.toggle('focused', i === focusIndex.settings);
        });
    }

    function showChannelSwitch(text) {
        var el = document.getElementById('channel-switch');
        document.getElementById('switch-number').textContent = text;
        el.classList.remove('hidden');
    }

    function hideChannelSwitch() {
        document.getElementById('channel-switch').classList.add('hidden');
    }

    function getFocusedChannelIndex() {
        return focusIndex.channels;
    }

    function setFocusZone(zone) {
        focusZone = zone;
        updateFocusUI();
    }

    function resetChannelFocus() {
        focusIndex.channels = 0;
    }

    function clamp(val, min, max) {
        return Math.max(min, Math.min(max, val));
    }

    return {
        init: init,
        updateFocusUI: updateFocusUI,
        getFocusedChannelIndex: getFocusedChannelIndex,
        setFocusZone: setFocusZone,
        resetChannelFocus: resetChannelFocus
    };
})();
