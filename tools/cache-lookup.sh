#!/usr/bin/env bash

# ##################################################
# cacheLookup.sh
# Generates & executes curl commands in order to test Geode cache reads.
# A json payload is sent with an id value, the result is processed by stream and logged to
# SCDF log sink.
#
# Usage:
#   $ ./cacheLookup.sh [START_INDEX] [NUMBER_TO_GENERATE]
#
#   * START_INDEX (optional): Start index to increment from (default 1)
#   * NUMBER_TO_GENERATE (optional): total number of payloads to generate & send. Default 100
# ##################################################

# $1 Start index (default 1)
START_INDEX=${1:-1}

# $2 Number of entity POSTS to generate (default 100)
NUMBER_TO_GENERATE=${2:-100}

# base curl command to build on
CURL_STRING="curl -sS -i -H 'Content-Type: application/json' -X POST localhost:20000"

# createCurlCommand()
#
# Builds & executes a curl POST command against SCDF HTTP sink
#
# -----------------------------------
# $1    Unique id used to generate entity id to lookup in cache
# -----------------------------------
createCurlCommand() {
  ID=$1

  # use id to create a key
  ID_STRING=${ID} # -$((ID + 100))

  # use id to create simple json payload
  #PAYLOAD="{\\\"id\\\":\\\"$ID_STRING\\\"}"
  PAYLOAD="{\\\"Latitude\\\":\\\"49.033503\\\",\\\"VehicleId\\\":\\\"$ID_STRING\\\",\\\"Speed\\\":\\\"65\\\",\\\"Longitude\\\":\\\"-122.270857\\\"}"

  # evaluate to execute curl with PAYLOAD
  eval ${CURL_STRING} -d \"${PAYLOAD}\"
  #> /dev/null
  eval echo ${PAYLOAD}
   #echo ${CURL_COMMAND} -d \"${PAYLOAD}\"
}

# calculate end of range
END=$((START_INDEX + NUMBER_TO_GENERATE - 1))

# generate NUMBER_TO_GENERATE number of curl commands
for index in $(seq ${START_INDEX} ${END}); do
  createCurlCommand ${index}
done
