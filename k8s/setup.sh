#!/usr/bin/env bash

# Deploy Geode
kubectl apply -f geode

# Deploy Confluent 3 Kafka Brokers and 1 Zookeeper with Helm
helm repo add confluentinc https://confluentinc.github.io/cp-helm-charts/
helm install confluentinc/cp-helm-charts --name confluent --set cp-schema-registry.enabled=false,cp-kafka-rest.enabled=false,cp-kafka-connect.enabled=false,cp-ksql-server.enabled=false,cp-zookeeper.servers=1

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

#Deploy Server
kubectl apply -f src/kubernetes/server/server-roles.yaml
kubectl apply -f src/kubernetes/server/server-rolebinding.yaml
kubectl apply -f src/kubernetes/server/service-account.yaml

#Deploy Skipper
kubectl apply -f src/kubernetes/skipper/skipper-config-kafka.yaml
kubectl apply -f src/kubernetes/skipper/skipper-deployment.yaml
kubectl apply -f src/kubernetes/skipper/skipper-svc.yaml

#Finish deploying Server
kubectl apply -f src/kubernetes/server/server-config.yaml
kubectl apply -f src/kubernetes/server/server-svc.yaml
kubectl apply -f src/kubernetes/server/server-deployment.yaml