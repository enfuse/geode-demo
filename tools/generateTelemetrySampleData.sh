#!/usr/bin/env bash

# $1 Start index (default 1)
START_INDEX=${1:-1}

# $2 Number of entity POSTS to generate (default 100)
NUMBER_TO_GENERATE=${2:-100}

END=$((START_INDEX + NUMBER_TO_GENERATE - 1))
> telemetry.txt
for index in $(seq ${START_INDEX} ${END}); do
  PAYLOAD="{\"Latitude\":\"101.01\",\"VehicleId\":\"$index\",\"Speed\":\"35\",\"Longitude\":\"-101.01\"}"
#  {"Latitude":"49.00001","VehicleId":"1","Speed":"35","Longitude":"-49.00001"}
  echo $PAYLOAD >> telemetry.txt
done
