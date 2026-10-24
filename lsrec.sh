#!/bin/bash
# lsrec.sh — listagem recursiva no debugfs
# uso: ./lsrec.sh /data/data/com.mobile.brasiltvmobile part1.ext4

for d in files code_cache shared_prefs databases cache; do
  echo "=== $d"
  /opt/homebrew/opt/e2fsprogs/sbin/debugfs -R "ls -l /data/data/com.mobile.brasiltvmobile/$d" part1.ext4 2>/dev/null
done