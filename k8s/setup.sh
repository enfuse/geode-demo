#!/usr/bin/env bash

# Deploy Geode
kubectl apply -f geode

# Deploy Kafka
kubectl apply -f src/kubernetes/kafka/

#Deploy Postgres
kubectl apply -f postgres

# Deploy SCS applications
kubectl apply -f scdf-geode-stream.yml

#Deploy Prometheus
kubectl apply -f src/kubernetes/mysql

kubectl apply -f src/kubernetes/prometheus

#Deploy Grafana
kubectl apply -f src/kubernetes/grafana

kubectl cp geode/data/1mil.gfd server-0:/tmp/1mil.gfd