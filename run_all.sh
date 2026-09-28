#!/bin/bash
# Runs every solution (requires JDK 17+; uses the single-file source launcher).
cd "$(dirname "$0")/src"
for f in Q*.java; do
  echo "=================== $f ==================="
  java "$f"
done
