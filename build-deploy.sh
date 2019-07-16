#!/usr/bin/env bash

(cd SCDF/source
./gradlew clean build install)

(cd SCDF/transform
./gradlew clean build install)

./deploy.sh
