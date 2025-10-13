#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/../../.." && pwd)"
SHARED_DIR="$ROOT_DIR/backend/shared"

if [ ! -d "$SHARED_DIR" ]; then
  echo "[ERROR] Shared module not found at $SHARED_DIR" >&2
  exit 1
fi

pushd "$SHARED_DIR" >/dev/null

if [ ! -x "./mvnw" ]; then
  chmod +x ./mvnw
fi

./mvnw install -DskipTests

popd >/dev/null
