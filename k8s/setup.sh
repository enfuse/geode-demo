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

#Deploy Server
#kubectl apply -f src/kubernetes/server/server-roles.yaml
#kubectl apply -f src/kubernetes/server/server-rolebinding.yaml
#kubectl apply -f src/kubernetes/server/service-account.yaml
#
##Deploy Skipper
#kubectl apply -f src/kubernetes/skipper/skipper-config-kafka.yaml
#kubectl apply -f src/kubernetes/skipper/skipper-deployment.yaml
#kubectl apply -f src/kubernetes/skipper/skipper-svc.yaml
#
##Finish deploying Server
#kubectl apply -f src/kubernetes/server/server-config.yaml
#kubectl apply -f src/kubernetes/server/server-svc.yaml
#kubectl apply -f src/kubernetes/server/server-deployment.yaml