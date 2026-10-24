# Protocolo de Stream — Brasil TV Mobile (descobertas de 2026-10-23)

## Resumo executivo

- Stream = **HLS ao vivo** (m3u8 + segmentos .ts de 5s) via HTTP cleartext em IP:porta dedicado
- Autenticação do stream = **path randomizado + cookies `d`/`s`/`t`** por sessão
- O path e os cookies **já existem na primeira request** — o emissor é o portal (HTTPS)
  ou o código nativo (Titan). Nenhum `Set-Cookie` trafega em HTTP cleartext.
- HLS roda nativo no AVPlay da Tizen → port não precisa de proxy de vídeo

## 1. getLiveData — lista de canais (portal, HTTPS)

Resposta completa (extraída do heap dump, UTF-16, heap6.hprof):

```json
{
  "channelCode": "pt_TDMwn2WGBHS0CUpsbzhNfuo5_480p",
  "alias": "Record HD",
  "name": "Record HD",
  "posterList": [
    {"fileType": "icon",   "fileUrl": "https://dio3ka.lpsyujwj.xyz/public/images/<uuid>.png"},
    {"fileType": "poster", "fileUrl": "https://dio3ka.lpsyujwj.xyz/public/images/<uuid>.png"}
  ],
  "quality": "1",
  "restricted": "0",
  "supportBusiness": "LIVE",
  "supportVideoType": "h264",
  "liveAddressList": [
    {
      "AVFormat": "ts",
      "cdnType": "4",
      "license": "app_id=bratvmob&tag=EDEBC8&scheme=md5-01&media_code=pt_XXX_480p&expired=1793397892&token=<md5>",
      "playCode": "pt_XXX_480p",
      "quality": "1",
      "tag": "EDEBC83132827D7DF9B368C3DB8F273C"
    }
  ]
}
