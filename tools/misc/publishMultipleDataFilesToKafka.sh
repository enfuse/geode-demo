#!/bin/sh

for index in $(seq 1 1000); do
  ./publishSampleDataToKafka.sh && echo "$index"
done
