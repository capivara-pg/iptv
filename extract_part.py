#!/usr/bin/env python3
# extract_part.py — extrai partição (offset 1 MiB) de imagem raw de forma esparsa
OFFSET = 1048576          # setor 2048 × 512
CHUNK = 4 * 1024 * 1024   # blocos de 4 MB

src = open('data.raw', 'rb')
src.seek(0, 2)
size = src.tell() - OFFSET

dst = open('part1.ext4', 'wb')
dst.truncate(size)        # cria arquivo esparso de 128 GB sem ocupar disco

src.seek(OFFSET)
pos = 0
while pos < size:
    data = src.read(min(CHUNK, size - pos))
    if any(data):         # só materializa blocos com conteúdo
        dst.seek(pos)
        dst.write(data)
    pos += len(data)
    if pos % (1024*1024*1024) < CHUNK:
        print(f'{pos/1e9:.1f}/{size/1e9:.1f} GB')
print('Pronto:', size, 'bytes (lógicos)')