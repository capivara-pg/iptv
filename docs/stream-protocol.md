# Protocolo de stream (descoberto em 2026-10-23)

## Resposta getLiveData (portal)
Por canal: channelCode, name, posterList (logos em dio3ka.lpsyujwj.xyz),
liveAddressList[] = {AVFormat:"ts", cdnType:"1|4", license, playCode, quality, tag}
- license = app_id=bratvmob&tag=<XXXXXX>&scheme=md5-01&media_code=<code>&expired=<unix>&token=<md5>
- cdnType=1 → icdn (main_addr=brlivemobvipslb.yohjs858f.click, spared=d3is2ozz9wzkyj.cloudfront.net)
- cdnType=4 → aws (main_addr=http://cphf407.gdfdvdbilhn.com/v3/youshi/, akakou.gdudfhl84f.xyz)
- CdnListBeanResult: cdn_id=br_live_mobilevip_local, gslb_params=a=1

## Reprodução real (mitm capture celular)
GET http://172.245.211.162:30813/{path-random-8-16chars} 
Cookie: d=<~1000chars>; s=<43chars>; t=<43chars>
→ 200 application/vnd.apple.mpegurl (#EXTM3U, segmentos .ts de ~500KB, 5s)
Segmentos: /{channelCode}/{channelCode}_{rand}_{seq}.ts
- Cookie e path JÁ existem na 1a request; NUNCA há Set-Cookie no HTTP cleartext
- → Emissor do path+cookie é o portal (HTTPS) ou código nativo (Titan/share.shareuvnet.com)

## Falta descobrir
Como license → {IP:porta, path, cookies d/s/t}
Pistas: TitanHelper, share.shareuvnet.com, titan_port, sbzhwr.pfjaklszx.com (S3 GSLB),
ProGuard: mobile.com.requestframe.utils.* (GetNearestMatchReqParams, CdnListBeanResult)

## Heaps disponíveis no Mac
~/heap3-6.hprof (BlueStacks), heap_extract.txt (extrações)
