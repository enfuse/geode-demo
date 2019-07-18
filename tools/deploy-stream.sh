#!/usr/bin/env bash

set -x
set -eu

curl -H "Accept: application/json" -X POST "http://localhost:9393/streams/definitions" -i \
    -d "name=geode-demo-pipeline" \
    -d "definition=http|transform --server.port=9997|log"

curl -H "Content-type: application/json" -X POST "http://localhost:9393/streams/deployments/geode-demo-pipeline" -i#
 \
    #-d "{\"app.*.--spring.profiles.active\":\"docker\"}"
