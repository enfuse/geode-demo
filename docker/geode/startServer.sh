#!/bin/bash

mkdir -p /data/$HOSTNAME

gfsh start server --name=$HOSTNAME --server-port=40404 --start-rest-api=true --cache-xml-file=/docker/geode/cache.xml  --locators=locator.localhost[10334] --dir=/data/$HOSTNAME/ "$@"

while true; do
    sleep 10
  done
done
