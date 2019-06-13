#!/usr/bin/env bash

# Deploy Geode
kubectl apply -f geode

# Deploy MongoDB Replica Set with Helm
helm repo add stable https://kubernetes-charts.storage.googleapis.com/
helm install stable/mongodb-replicaset --name mongo
# Or...
#kubectl apply -f mongodb-replicaset

# Deploy Confluent 3 Kafka Brokers and 1 Zookeeper with Helm
helm repo add confluentinc https://confluentinc.github.io/cp-helm-charts/
helm install confluentinc/cp-helm-charts --name confluent --set cp-schema-registry.enabled=false,cp-kafka-rest.enabled=false,cp-kafka-connect.enabled=false,cp-ksql-server.enabled=false,cp-zookeeper.servers=1