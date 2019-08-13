#!/usr/bin/env sh

apk add curl

GEODE_SERVER_URL=${GEODE_SERVER_URL:-http://server.localhost:7070}

while ! nc -zw2 server.localhost 7070 &>/dev/null; do
    true
done
