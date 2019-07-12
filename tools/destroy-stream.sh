#!/usr/bin/env bash

set -x
set -eu

curl 'http://localhost:9393/streams/definitions' -i -X DELETE