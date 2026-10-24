# Brasil TV Mobile (bravmob.apk) — API Analysis

**Package:** `com.mobile.brasiltvmobile` | **Version:** 2.35.3 (code 23503), latest available: 2.36.0  
**Platform:** SXL/SWL IPTV | **HTTP Client:** OkHttp 3.12.12  
**Packer:** ijiami (爱加密) — runtime DEX encryption, anti-repackaging, anti-debug  
**Visitor Login:** Auto-assigns numeric ID (e.g. `575639528`)

---

## Architecture Overview

The app uses a **main/backup domain** pattern for every service. Domains are stored as Android string resources (`res/values/strings.xml`) and resolved at runtime. All API traffic is over **HTTPS** except the ad server and the upgrade/market server which use **HTTP cleartext**. The app **bypasses Android's system HTTP proxy** setting for its own API calls.

### Deep Link Scheme
`sxl://www.brasiltv4k.com`

### Video Player
IJKPlayer (FFmpeg-based) + HPPlay/Lebo SDK for DLNA casting

---

## Service Domains

| Service | Role | Domain | Protocol |
|---------|------|--------|----------|
| **Portal** | main | `mobile.solz1lf.com` | HTTPS |
| **Portal** | backup | `mbfel.lgesetd1l.com` | HTTPS |
| **EPG** | main | `vngzr.sqnmdtzgp.com` | HTTPS |
| **EPG** | backup | `crhqd.bugphtjkl.com` | HTTPS |
| **Upgrade** | main | `lamnu.vlgzhmtxe.com` | HTTPS |
| **Upgrade** | backup | `akz1.teerbr.com` | HTTPS |
| **Ad Server** | main | `mobiletv.ogy1lfw.com` | **HTTP** |
| **Ad Server** | backup | `mobiletv.terdlfw.com` | HTTP |
| **Notice** | main | `notice.terdlfw.com` | HTTP |
| **Notice** | backup | `notice.ogy1lfw.com` | HTTP |
| **Bigbee** (analytics) | main | `jxnili.dknsbvcya.com` | HTTPS |
| **Bigbee** (analytics) | backup | `hpmyon.irqgbdhck.com` | HTTPS |
| **H5** (webview) | main | `minxy.ldnqcxtvy.com` | HTTPS |
| **Market Server** | — | `ifgh.wofn3zkvcm.com` | **HTTP** |
| **Media CDN** | — | `miqe.sdxpkgyaq.com` | HTTP (port 8066 for images) |
| **APK Download** | main | `rwae.ukxnfjepq.com` | HTTP |
| **APK Download** | spare | `gdgx.macvwtgzj.com` | HTTP |
| **Download Site** | — | `www.brasil-tv.net/download` | HTTPS |
| **Streaming** | main | `bxvxjj.pzmsjhwvd.online` | HTTPS |
| **Stream Relay** | — | `172.245.211.162:30813` | TCP (direct IP) |
| **Unknown** | — | `awnagk.gviudclrb.com` | HTTPS |
| **Unknown** | — | `ds828ha02.mu7f534ce.com` | HTTPS |
| **Unknown** | — | `s23sdf56.45lc9mx79ab.com` | HTTPS |

### Third-Party SDKs (also contacted)

| Domain | Service |
|--------|---------|
| `*.umeng.com` (6 subdomains) | Umeng Analytics (Alibaba) |
| `umengacs.m.taobao.com` | Umeng config |
| `firebaseinappmessaging.googleapis.com` | Firebase In-App Messaging |
| `i.postimg.cc` | Image hosting |
| `enmvdm.epa3bkfqic.com` | Unknown app service |
| `uedfyj.uniagxe2mr.com` | Unknown app service |
| `yumao.puata.info` | Unknown app service |
| `report.appmetrica.yandex.net` | Yandex AppMetrica analytics |
| `android.googleapis.com` | Google Play services |
| `www.googleapis.com` | Google APIs (OAuth/login) |
| `youtubei.googleapis.com` | YouTube API (cast/embed?) |
| `*.google.com:5228` | GCM/FCM push notifications |

---

## Captured API Endpoints (with full request/response)

### 1. Ad Server — `POST /api/adserver/v2/get_content` (v2) and `v3/get_content` (v3)

**Host:** `mobiletv.ogy1lfw.com` (HTTP cleartext)

**Request:**
```
POST /api/adserver/v2/get_content HTTP/1.1
Content-Type: application/json;charset=utf-8
Host: mobiletv.ogy1lfw.com
User-Agent: okhttp/3.12.12
```

**Request Body (v2 — no active user):**
```json
{
  "ad_version": "1.0",
  "apk_versioncode": "23204",
  "os_version": "14",
  "osd_language": "en",
  "pic_suport": "jpg/png/bmp",
  "pkg": "com.mobile.brasiltvmobile",
  "platform": "android",
  "sn": "",
  "video_suport": "mp4/rmvb/flv"
}
```

**Request Body (v3 — with active user, from phone capture):**
```json
{
  "ad_version": "1.0",
  "apk_versioncode": "23600",
  "channel": "default",
  "os_version": "16",
  "osd_language": "en",
  "pic_suport": "jpg/png/bmp",
  "pkg": "com.mobile.brasiltvmobile",
  "platform": "android",
  "sn": "10e4cf2b72f761dc01a87824dfbfdb5d",
  "user_id": "100785824",
  "user_name": "",
  "video_suport": "mp4/rmvb/flv"
}
```

**Response (v2, no user — empty ads):**
```json
{
  "ad_positions": null,
  "expires_time": -1,
  "reporting_interval": 60,
  "timestamp": 1792666206889,
  "user_group_id": ""
}
```

**Response (v3, with user — has ads):**
```json
{
  "ad_positions": [{
    "mode": "special",
    "update_time": "2026-07-02T01:25:18Z",
    "ads": [{
      "show_frequency": 0,
      "ad_id": 2005,
      "ad_name": "Bra TV Mob--ADGF",
      "is_dealer": "default",
      "url": "http://miqe.sdxpkgyaq.com/media/adsys/6db17564-b161-4cef-b8aa-b3e734833964.jpg",
      "action_type": "1",
      "action": "https://www.goalflowai.com",
      "show_frequency_v2": {
        "identity_1": -1, "identity_2": -1, "identity_3": -1,
        "identity_4": -1, "identity_5": -1
      },
      "media_type": "picture",
      "show_adsflag": 0,
      "ad_style": "normal",
      "buttons": []
    }],
    "ad_type": "home_page_ad_1"
  }],
  "expires_time": -1,
  "reporting_interval": 60,
  "timestamp": 1792663005049,
  "user_group_id": ""
}
```

**Key Parameters:**
- `sn` — Device serial number / hardware ID (MD5 hash, 32 chars). Empty when no user session.
- `user_id` — Numeric user ID (e.g. `100785824`). Absent in v2 when not logged in.
- `apk_versioncode` — APK version code as string
- `channel` — Distribution channel (v3 only, "default")
- `platform` — Always "android"

---

### 2. Ad Reporting — `POST /api/adserver/report`

**Host:** `mobiletv.ogy1lfw.com` (HTTP cleartext)

**Request Body:**
```json
{
  "report_content": [{
    "ad_id": "2005",
    "ad_name": "Bra TV Mob--ADGF",
    "ad_type": "home_page_ad_1",
    "apk_version": 23600,
    "app_id": "com.mobile.brasiltvmobile",
    "click_times": 0,
    "display_times": 1,
    "game_stay_time": 0,
    "last_update_timestamp": 1792662958047,
    "media_type": "picture",
    "sn": "10e4cf2b72f761dc01a87824dfbfdb5d",
    "user_id": "81606300",
    "user_name": "912528684"
  }]
}
```

**Response:**
```json
{"message": "success", "returnCode": "0"}
```

---

### 3. Notice API — `GET /notice/api/get_notice`

**Host:** `notice.terdlfw.com` (HTTP cleartext)

**URL:**
```
GET /notice/api/get_notice?pkg=com.mobile.brasiltvmobile&v=23600&language=en&sn=81606300&userId=100785824
```

**Parameters:**
| Param | Example | Description |
|-------|---------|-------------|
| `pkg` | `com.mobile.brasiltvmobile` | Package name |
| `v` | `23600` | Version code |
| `language` | `en` | Device language |
| `sn` | `81606300` | Short device SN |
| `userId` | `100785824` | User ID |

**Response:**
```json
{
  "status": 0,
  "info": "",
  "showModel": null,
  "package": "com.mobile.brasiltvmobile",
  "inner": [],
  "duration": 0,
  "type": 0
}
```

---

### 4. Upgrade/Market Server — `GET /MarketServer/update`

**Host:** `ifgh.wofn3zkvcm.com` (HTTP cleartext)

**URL:**
```
GET /MarketServer/update?action=checkUpdate&packagenamesAndVersioncodes=com.mobile.brasiltvmobile%2C23204&language=en&sn=vDedjnhT2IA%3D&userId=unknown
```

**Parameters:**
| Param | Example | Description |
|-------|---------|-------------|
| `action` | `checkUpdate` | Action type |
| `packagenamesAndVersioncodes` | `com.mobile.brasiltvmobile,23204` | Package + version (URL-encoded comma) |
| `language` | `en` | Device language |
| `sn` | `vDedjnhT2IA=` | Base64-encoded device serial |
| `userId` | `unknown` | User ID (or "unknown" when not logged in) |

**Response (XML):**
```xml
<?xml version="1.0" encoding="utf-8"?>
<ApkInfo>
  <list rows="1">
    <App>
      <id>1952</id>
      <name>Brasil TV(mobile)</name>
      <appDownloadCount>1372</appDownloadCount>
      <packageName>com.mobile.brasiltvmobile</packageName>
      <icon>http://rwae.ukxnfjepq.com:8066//error.jpg</icon>
      <versionName>2.35.3</versionName>
      <versionCode>23503</versionCode>
      <img>http://rwae.ukxnfjepq.com:8066//error.jpg</img>
      <url>http://rwae.ukxnfjepq.com/marketdatas/apk/bravMob/2.35.3/bravMob_2.35.3.apk</url>
      <spareUrl>http://gdgx.macvwtgzj.com/marketdatas/apk/bravMob/2.35.3/bravMob_2.35.3.apk</spareUrl>
      <note>1. Make sure you have enough memory space before updating
2.Optimized user experience.
3. Fixed other minor issues.</note>
      <forceUpdate>1</forceUpdate>
      <md5Value>8213c87bbf1a0d0d2fd80bdb815a8109</md5Value>
      <defaultDownloadURL>https://www.brasil-tv.net/download</defaultDownloadURL>
    </App>
  </list>
</ApkInfo>
```

**Key findings:**
- Latest version is **2.35.3** (code 23503), vs installed 2.32.4 (23204)
- `forceUpdate=1` — forces the update dialog
- APK download URLs: `rwae.ukxnfjepq.com` (main) and `gdgx.macvwtgzj.com` (spare)
- Image server: `rwae.ukxnfjepq.com:8066`

---

### 5. EPG API — `GET /epg/live/app/20`

**Host:** `vngzr.sqnmdtzgp.com` (**HTTP cleartext** despite HTTPS capability)

**Request:**
```
GET /epg/live/app/20 HTTP/1.1
Content-Type: application/json;charset=utf-8
apk: com.mobile.brasiltvmobile
apkVer: 23503
spkgVer: 14
Host: vngzr.sqnmdtzgp.com
Connection: Keep-Alive
Accept-Encoding: gzip
User-Agent: okhttp/3.12.12
```

**Custom Headers:**
| Header | Value | Description |
|--------|-------|-------------|
| `apk` | `com.mobile.brasiltvmobile` | Package name |
| `apkVer` | `23503` | APK version code |
| `spkgVer` | `14` | Likely "server package version" or API version |

**Response:** 4.5MB JSON array — complete program guide for all channels

**Response Structure:**
```json
[
  {
    "channelCode": "cyp_EzQIvhr4w2UUk5HoaRXy_480p",
    "contents": [
      {
        "date": "2026-10-20",
        "start": "00:00",
        "stop": "01:00",
        "title": "A Tumba Secreta de Cleópatra",
        "desc": "Para consolidar seu poder, Cleópatra sela uma poderosa aliança..."
      }
    ]
  }
]
```

**Key findings:**
- The URL path `/epg/live/app/20` likely contains a region or group ID (`20`)
- `channelCode` uses a format: `{prefix}_{base64-like-id}_{quality}` (e.g. `cyp_EzQIvhr4w2UUk5HoaRXy_480p`)
- Each channel has a `contents` array of program entries with `date`, `start`, `stop`, `title`, `desc`
- Some channels have empty `contents: []`
- Response is served via **Cloudflare + CloudFront** CDN with 24h cache (`Cache-Control: max-age=86400`)
- Infrastructure: `X-Cache: Miss from cloudfront`, `X-Amz-Cf-Pop: MIA50-P6` (Miami CloudFront PoP)

---

## API Endpoints NOT Yet Captured (HTTPS-only)

These endpoints use HTTPS and their request/response bodies could not be captured due to ijiami's protection blocking all interception methods. Their domains were confirmed active via TLS SNI capture.

### Portal API — `mobile.solz1lf.com` (HTTPS, confirmed active)
- **Confirmed via TLS SNI** during visitor login and Home screen load
- Handles: login (visitor auto-login observed with ID:575639528), channel listing, VOD content, user profile, search
- Uses custom headers similar to EPG endpoint (`apk`, `apkVer`, etc.)

### Bigbee — `jxnili.dknsbvcya.com`
- Analytics / user behavior tracking (app-specific, not Umeng)
- Not observed in this capture session

### Streaming Metadata — `bxvxjj.pzmsjhwvd.online` (HTTPS, confirmed active)
- Confirmed via TLS SNI — likely serves channel stream URLs or playlist metadata

---

## Captured Streaming Endpoints

### 6. Live TV Streams — `172.245.211.162:30813` (HTTP cleartext, MPEG-TS)

**Server:** `nginx/1.29.0` on direct IP `172.245.211.162`, port `30813`

**Request pattern:**
```
GET /{randomized-path} HTTP/1.1
Host: 172.245.211.162:30813
Connection: Keep-Alive
Content-Type: application/octet-stream
User-Agent: okhttp/3.12.12
```

**Captured stream paths:**
- `/uqz/wukwlta`
- `/aeuypdunmuepkou`
- `/xvbyoxal1`

**Response:**
```
HTTP/1.1 200 OK
Content-Type: video/MP2T
Server: nginx/1.29.0
Content-Length: 423564
```

**Key findings:**
- Streams are delivered as **MPEG Transport Stream** (`video/MP2T`) over HTTP
- Stream paths appear to be **randomized/obfuscated** strings (not channel names)
- The response body contains an **index header** with the channel code embedded: `pt_jqwkent234kb23nL1_480p` (matches the `channelCode` format from the EPG)
- Served from a dedicated server at `172.245.211.162` (BuyVM/Frantech, Las Vegas), not behind Cloudflare
- Multiple concurrent streams are opened (different paths for different channels)

---

## Common API Parameters

| Parameter | Description | Example Values |
|-----------|-------------|----------------|
| `pkg` | Android package name | `com.mobile.brasiltvmobile` |
| `apk_versioncode` / `v` | APK version code | `23204`, `23600` |
| `platform` | OS platform | `android` |
| `os_version` | Android API level | `14`, `16` |
| `osd_language` / `language` | Device language | `en`, `pt` |
| `sn` | Device serial / hardware ID | MD5 hash (32 chars), base64, or numeric |
| `user_id` / `userId` | Numeric user ID | `100785824`, `unknown` |
| `user_name` | Username or display name | `912528684` |
| `channel` | Distribution channel | `default` |

---

## Login Methods (from UI strings)

The app supports multiple login methods:
- **Visitor login** (anonymous)
- **Email login**
- **Google login** (OAuth)
- **Mobile/phone login** (SMS verification)
- **Scan code login** (QR code from TV app)
- **Account login** (username/password)

---

## App Protection (ijiami)

The app is packed with **ijiami (爱加密)** commercial protection:

1. **Runtime DEX encryption** — Real code decrypted from `assets/ijiami.dat` by native loader
2. **Anti-repackaging** — Detects modified APK signatures, kills the process
3. **Anti-Frida** — `libranger-jni.so` detects Frida via direct syscalls (not hookable from libc):
   - `/proc/self/maps` scanning for frida-agent
   - Port scanning for Frida's default port (27042)
   - Process self-termination via SIGKILL
4. **Anti-emulator** — Reads `/proc/diskstats`, `/proc/net/*`, `/proc/loadavg`
5. **Proxy bypass** — OkHttp client ignores Android system proxy settings

### Interception methods tried and results:
| Method | Result |
|--------|--------|
| APK repackaging with custom `network_security_config.xml` | Detected, app killed |
| Frida attach (ptrace) | Detected, process killed (signal 9) |
| Frida spawn with anti-debug hooks | Still detected (direct syscalls) |
| HTTP proxy settings | Bypassed by the app |
| iptables DNAT on emulator | Routing issues |
| mitmproxy WireGuard mode | Requires pfctl root on macOS |

---

## Infrastructure Notes

- All domains use **Cloudflare** CDN (CF-RAY headers, CWB = Curitiba PoP)
- EPG data also served via **AWS CloudFront** CDN (`X-Amz-Cf-Pop: MIA50-P6` Miami PoP) behind Cloudflare
- API responses include `x-envoy-upstream-service-time` → **Envoy proxy** behind Cloudflare
- Media CDN: `sdxpkgyaq.com`
- All app-specific domains use **randomized names** (e.g. `solz1lf.com`) — likely to evade domain blocking
- DNS resolution uses multiple DoH providers: Alibaba (`dns.alidns.com` / `223.5.5.5`), Google (`8.8.8.8`), Cloudflare (`1.1.1.1`), Quad9 (`9.9.9.10`)
- Live TV streams delivered as **MPEG-TS over HTTP** from dedicated servers on non-standard ports (e.g. `172.245.211.162:30813`, nginx/1.29.0, BuyVM/Frantech)
- Stream URLs use randomized paths that map to channel codes
- Streaming metadata served from `.online` TLD domains (e.g. `bxvxjj.pzmsjhwvd.online`)
- Push notifications via **Google FCM** (port 5228)
- Analytics: Umeng (Alibaba), Yandex AppMetrica, and custom Bigbee
