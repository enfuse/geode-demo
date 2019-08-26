#!/usr/bin/env bash
docker cp ./data/100k.gfd server:/tmp/100k.gfd
docker cp ./tools/uploadGeodeData.gfsh locator:/tmp/
docker exec -ti locator sh -c 'gfsh run --file=/tmp/uploadGeodeData.gfsh'
