#!/bin/sh

cat < telemetry.txt | kafkacat -b localhost:9092 -t geode-demo-pipeline.file -P -v
