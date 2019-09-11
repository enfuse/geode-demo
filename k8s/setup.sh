#!/usr/bin/env bash

# Deploy Geode
kubectl apply -f geode

# Deploy Kafka
kubectl apply -f https://raw.githubusercontent.com/spring-cloud/spring-cloud-dataflow/master/src/kubernetes/kafka/kafka-deployment.yaml
kubectl apply -f https://raw.githubusercontent.com/spring-cloud/spring-cloud-dataflow/master/src/kubernetes/kafka/kafka-svc.yaml
kubectl apply -f https://raw.githubusercontent.com/spring-cloud/spring-cloud-dataflow/master/src/kubernetes/kafka/kafka-zk-deployment.yaml
kubectl apply -f https://raw.githubusercontent.com/spring-cloud/spring-cloud-dataflow/master/src/kubernetes/kafka/kafka-zk-svc.yaml

#Deploy Postgres
kubectl create -f postgres/postgres-config.yml
kubectl create -f postgres/postgres-service.yml
kubectl create -f postgres/postgres-storage.yml
kubectl create -f postgres/postgres-deployment.yml

# Deploy SCS applications
kubectl apply -f scdf-geode-stream.yml

#Deploy Prometheus
kubectl apply -f src/kubernetes/mysql/

kubectl apply -f src/kubernetes/prometheus/prometheus-clusterroles.yaml
kubectl apply -f src/kubernetes/prometheus/prometheus-clusterrolebinding.yaml
kubectl apply -f src/kubernetes/prometheus/prometheus-serviceaccount.yaml

kubectl apply -f src/kubernetes/prometheus/prometheus-configmap.yaml
kubectl apply -f src/kubernetes/prometheus/prometheus-deployment.yaml
kubectl apply -f src/kubernetes/prometheus/prometheus-service.yaml

#Deploy Grafana
kubectl apply -f src/kubernetes/grafana/

kubectl cp geode/data/1mil.gfd geode-server-0:/tmp/1mil.gfd