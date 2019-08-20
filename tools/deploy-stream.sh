#!/usr/bin/env bash

set -x
set -eu

curl -H "Accept: application/json" -X POST "http://localhost:9393/streams/definitions" -i \
    -d "name=geode-demo-pipeline" \
    -d "definition=file --server.port=20000 --mode=lines --file.directory=/tmp/foo/ |geode-processor|log"

curl -H "Content-type: application/json" -X POST "http://localhost:9393/streams/deployments/geode-demo-pipeline" -i#


curl -H "Accept: application/json" -X POST "http://localhost:9393/streams/definitions" -i \
    -d "name=geode-demo-pipeline-postgres" \
    -d "definition=file --server.port=20001 --mode=lines --file.directory=/tmp/foo/ |postgres-processor --spring.datasource.driver-class-name=org.postgresql.Driver --spring.datasource.password=app --spring.datasource.url=jdbc:postgresql://postgres.localhost:5432/app --spring.datasource.username=app --spring.datasource.initialization-mode=always --spring.jpa.hibernate.ddl-auto=create|log"

curl -H "Content-type: application/json" -X POST "http://localhost:9393/streams/deployments/geode-demo-pipeline-postgres" -i#

 \
    #-d "{\"app.*.--spring.profiles.active\":\"docker\"}"
