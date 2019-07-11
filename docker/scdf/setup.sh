#!/usr/bin/env sh
while ! nc -zw2 localhost 9393 &>/dev/null; do
    true
done

apk add --no-cache curl

#curl -Ss http://dataflow.localhost:9393/apps/task/tax-batch-deployer \
#  -d 'uri=maven://com.corelogic.idap.pipeline:tax-batch-deployer:0.0.1-SNAPSHOT'
#
#curl -Ss http://dataflow.localhost:9393/apps/source/cloud-storage-events \
#  -d 'uri=maven://com.corelogic.idap.pipeline:cloud-storage-events:0.0.1-SNAPSHOT'
#curl -Ss http://dataflow.localhost:9393/apps/processor/tax-job-planner \
#  -d 'uri=maven://com.corelogic.idap.pipeline:tax-job-planner:0.0.1-SNAPSHOT'
#curl -Ss http://dataflow.localhost:9393/apps/sink/task-launcher-local \
#  -d 'uri=maven://org.springframework.cloud.stream.app:task-launcher-local-sink-kafka:jar:2.0.1.RELEASE'
#
#curl -Ss http://dataflow.localhost:9393/tasks/definitions \
#  -d 'name=tax-batch-deployer' -d 'definition=tax-batch-deployer'
#
#curl -Ss http://dataflow.localhost:9393/streams/definitions \
#  -d 'name=tax' -d 'definition=cloud-storage-events | tax-job-planner | task-launcher-local'
