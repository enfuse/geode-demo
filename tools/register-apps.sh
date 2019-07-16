#!/usr/bin/env bash

curl -H "Accept: application/json" -X POST "http://localhost:9393/apps/source/http" -i \
    -d "uri=maven://org.springframework.cloud.stream.app:http-source-kafka:2.1.0.RELEASE" \
    -d "force=true"

curl -H "Accept: application/json" -X POST "http://localhost:9393/apps/processor/transform/0.0.1-SNAPSHOT" -i \
    -d "uri=file://root/.m2/repository/io/enfuse/pipeline/transform/0.0.1-SNAPSHOT/transform-0.0.1-SNAPSHOT.jar" \
    -d "force=true"

curl -H "Accept: application/json" -X POST "http://localhost:9393/apps/sink/log" -i \
    -d "uri=maven://org.springframework.cloud.stream.app:log-sink-kafka:2.1.1.RELEASE" \
    -d "force=true"
