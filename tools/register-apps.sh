#!/usr/bin/env bash

curl -H "Accept: application/json" -X POST "http://localhost:9393/apps/processor/geode-processor/0.0.1-SNAPSHOT" -i \
    -d "uri=file://root/.m2/repository/io/enfuse/pipeline/geode-processor/0.0.1-SNAPSHOT/geode-processor-0.0.1-SNAPSHOT.jar" \
    -d "force=true"

curl -H "Accept: application/json" -X POST "http://localhost:9393/apps/processor/postgres-processor/0.0.1-SNAPSHOT" -i \
    -d "uri=file://root/.m2/repository/io/enfuse/pipeline/postgres-processor/0.0.1-SNAPSHOT/postgres-processor-0.0.1-SNAPSHOT.jar" \
    -d "force=true"
