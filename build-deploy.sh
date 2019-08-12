#!/usr/bin/env bash

(cd SCDF/transform
./gradlew clean build install)

./deploy.sh
