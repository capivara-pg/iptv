#!/usr/bin/env python3
# carve_dex.py — extrai DEXs válidos de dumps binários (memória, hprof, containers)
import struct, sys, glob, os

def carve(path, outdir="dexout"):
    os.makedirs(outdir, exist_ok=True)
    with open(path, "rb") as f:
        data = f.read()
    found = 0
    i = 0
    base = os.path.splitext(os.path.basename(path))[0]
    while True:
        i = data.find(b"dex\n", i)
        if i < 0 or i + 112 > len(data):
            break
        # header DEX: magic(8) checksum(4) sha1(20) file_size(4 @ 0x20)
        size = struct.unpack_from("<I", data, i + 32)[0]
        if 0x70 < size <= len(data) - i:
            dex = data[i:i + size]
            # sanity: string_ids em 0x38 deve ser plausível
            nstr = struct.unpack_from("<I", dex, 0x38)[0]
            if 0 < nstr < size:
                out = os.path.join(outdir, f"{base}_{i}.dex")
                with open(out, "wb") as g:
                    g.write(dex)
                found += 1
                print(f"  DEX em offset {i} (0x{i:x}), tamanho {size/1e6:.1f} MB")
        i += 4
    print(f"{path}: {found} DEX(s)")
    return found

if __name__ == "__main__":
    files = sys.argv[1:] or ["heap.hprof"]
    total = 0
    for f in files:
        total += carve(f)
    print(f"Total: {total} DEX(s) em dexout/")