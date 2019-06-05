#!/bin/bash

mkdir -p /data/$HOSTNAME

gfsh start server --name=$HOSTNAME --start-rest-api=true --cache-xml-file=/docker/geode/cache.xml --locators=locator.localhost[10334] --dir=/data/$HOSTNAME/ "$@"

while true; do
    sleep 10
done