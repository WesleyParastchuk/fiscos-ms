#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/../../.." && pwd)"
COMPOSE_FILE="$ROOT_DIR/docker-compose.dev.yml"
ENV_FILE="$ROOT_DIR/.env"

if [ ! -f "$ENV_FILE" ]; then
  echo "[ERROR] .env not found at $ROOT_DIR. Copy .env.example to .env before running." >&2
  exit 1
fi

if [ $# -eq 0 ]; then
  set -- up --build
fi

docker compose -f "$COMPOSE_FILE" "$@"
