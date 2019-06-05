#!/usr/bin/env sh

apk add curl

GEODE_SERVER_URL=${GEODE_SERVER_URL:-http://server.localhost:7070}

while ! nc -zw2 server.localhost 7070 &>/dev/null; do
    true
done

curl -i -H 'Content-Type: application/json' -X PUT ${GEODE_SERVER_URL}/gemfire-api/v1/exampleRegion/1 \
    -d "{\"id\":\"1\",\"value\":\"Example Value One\"}"

curl -i -H 'Content-Type: application/json' -X PUT ${GEODE_SERVER_URL}/gemfire-api/v1/exampleRegion/2 \
    -d "{\"id\":\"2\",\"value\":\"Example Value Two\"}"

curl -i -H 'Content-Type: application/json' -X PUT ${GEODE_SERVER_URL}/gemfire-api/v1/exampleRegion/3 \
    -d "{\"id\":\"3\",\"value\":\"Example Value Three\"}"

curl -i -H 'Content-Type: application/json' -X PUT ${GEODE_SERVER_URL}/gemfire-api/v1/exampleRegion/4 \
    -d "{\"id\":\"4\",\"value\":\"Example Value Four\"}"

curl -i -H 'Content-Type: application/json' -X PUT ${GEODE_SERVER_URL}/gemfire-api/v1/exampleRegion/5 \
    -d "{\"id\":\"5\",\"value\":\"Example Value Five\"}"
