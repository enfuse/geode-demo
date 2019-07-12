#!/usr/bin/env bash


curl -H "Accept: application/json" -X POST "http://localhost:9393/apps/processor/transform" -i \
    -d "uri=maven://io.enfuse.pipeline:transform:0.0.1-SNAPSHOT" \
    -d "force=true"
