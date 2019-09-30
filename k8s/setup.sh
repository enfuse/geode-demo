#!/usr/bin/env bash

# Deploy Geode
kubectl apply -f geode

# Deploy Kafka
kubectl apply -f kafka

# Deploy Postgres
kubectl apply -f postgres

# Deploy SCS applications
kubectl apply -f geode-stream.yml
kubectl apply -f sql-stream.yml

# Deploy Prometheus
kubectl apply -f mysql

kubectl apply -f prometheus

# Deploy Grafana
kubectl apply -f grafana
