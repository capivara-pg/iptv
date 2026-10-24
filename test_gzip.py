#!/usr/bin/env python3
# test_gzip.py — tenta descomprimir a partir de cada offset suspeito
import zlib

data = open('/tmp/brasiltv.dex','rb').read()
offsets = [0xd41c, 0x620300, 0x6f6b14, 0x89013c, 0x987d6c]

for off in offsets:
    for skip in (0, 4, 8, 16):
        start = off + skip
        try:
            d = zlib.decompressobj(wbits=31)   # gzip
            out = d.decompress(data[start:start+2_000_000])
            if len(out) > 1000:
                ok = 'DEX!' if out[:3] == b'dex' else 'ok'
                print(f'offset=0x{off:x} skip={skip}: {len(out)} bytes [{ok}]')
                if out[:3] == b'dex':
                    open(f'/tmp/found_{off:x}.dex','wb').write(out)
                break
        except Exception as e:
            pass
    else:
        print(f'offset=0x{off:x}: falhou')