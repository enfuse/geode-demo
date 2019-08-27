#!/bin/bash

mkdir -p /data/$HOSTNAME

gfsh start server --name=server1 --group=group1 --server-port=40404 --start-rest-api=true --cache-xml-file=/docker/geode/cache.xml  --locators=locator.localhost[10334] --dir=/data/server1/ "$@"
gfsh start server --name=server2 --group=group1 --server-port=40405 --start-rest-api=true --cache-xml-file=/docker/geode/cache.xml  --locators=locator.localhost[10334] --dir=/data/server2/ "$@"
gfsh start server --name=server3 --group=group1 --server-port=40406 --start-rest-api=true --cache-xml-file=/docker/geode/cache.xml  --locators=locator.localhost[10334] --dir=/data/server3/ "$@"

gfsh run --file=/docker/geode/importGeodeData.gfsh

while true; do
    sleep 10
  done
done
