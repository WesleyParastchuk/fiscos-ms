#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ROOT_DIR="$(cd "$SCRIPT_DIR/../../.." && pwd)"
COMPOSE_FILE="$ROOT_DIR/docker-compose.dev.yml"
ENV_FILE="$ROOT_DIR/.env"

"$SCRIPT_DIR/build-shared.sh"

if [ ! -f "$ENV_FILE" ]; then
  echo "[ERROR] .env not found at $ROOT_DIR. Copy .env.example to .env before running." >&2
  exit 1
fi

if [ ! -f "$COMPOSE_FILE" ]; then
  echo "[ERROR] docker-compose.dev.yml not found at $ROOT_DIR" >&2
  exit 1
fi

if [ $# -gt 0 ]; then
  SERVICES=("$@")
else
  SERVICES=(
    auth-service
    category-service
    invoice-service
    product-service
    saga-service
    user-service
  )
fi

docker compose -f "$COMPOSE_FILE" restart "${SERVICES[@]}"
