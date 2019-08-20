#!/usr/bin/env bash

(cd SCDF/geode-processor || return 1
./gradlew clean build install)

(cd SCDF/postgres-processor || return 1
./gradlew clean build install)

./deploy.sh
