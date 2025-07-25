#!/bin/sh
# wait-for.sh

set -e

hostport="$1"
shift
cmd="$@"

host=$(echo "$hostport" | cut -d: -f1)
port=$(echo "$hostport" | cut -d: -f2)

echo "Waiting for $host:$port..."

until nc -z "$host" "$port"; do
  echo "Still waiting for $host:$port..."
  sleep 2
done

echo "$host:$port is available - executing command"
exec $cmd
