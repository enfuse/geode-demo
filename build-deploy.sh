#!/usr/bin/env bash

(cd SCDF/transform
./gradlew clean build install -x test)

./deploy.sh
