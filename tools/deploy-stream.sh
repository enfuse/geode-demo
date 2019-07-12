#!/usr/bin/env bash

set -x
set -eu

curl -H "Accept: application/json" -X POST "http://localhost:9393/streams/definitions" -i \
    -d "name=scdf-geode-kafka-demo" \
    -d "definition=http --server.port=9998|transform --server.port=9997|log"

curl -H "Content-type: application/json" -X POST "http://localhost:9393/streams/deployments/scdf-geode-kafka-demo" -i#
 \
    #-d "{\"app.*.--spring.profiles.active\":\"docker\"}"
