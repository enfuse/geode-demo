#!/usr/bin/env bash
docker exec -ti locator sh -c 'gfsh run --file=/docker/geode/importGeodeData.gfsh'
