#!/usr/bin/env bash

curl -H "Accept: application/json" -X POST "http://localhost:9393/apps/processor/transform/0.0.1-SNAPSHOT" -i \
    -d "uri=file://root/.m2/repository/io/enfuse/pipeline/transform/0.0.1-SNAPSHOT/transform-0.0.1-SNAPSHOT.jar" \
    -d "force=true"
