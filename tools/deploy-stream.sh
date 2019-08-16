#!/usr/bin/env bash

set -x
set -eu

curl -H "Accept: application/json" -X POST "http://localhost:9393/streams/definitions" -i \
    -d "name=geode-demo-pipeline" \
    -d "definition=file --server.port=20000 --mode=lines --file.directory=/tmp/foo/ |transform|log"

curl -H "Content-type: application/json" -X POST "http://localhost:9393/streams/deployments/geode-demo-pipeline" -i#
 \
    #-d "{\"app.*.--spring.profiles.active\":\"docker\"}"
