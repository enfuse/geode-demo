kubectl create -f src/kubernetes/kafka/
kubectl create -f src/kubernetes/mysql/
kubectl create -f src/kubernetes/redis/
kubectl create -f src/kubernetes/metrics/metrics-deployment-kafka.yaml
kubectl create -f src/kubernetes/metrics/metrics-svc.yaml
kubectl create -f src/kubernetes/skipper/skipper-deployment.yaml
kubectl create -f src/kubernetes/skipper/skipper-svc.yaml
kubectl apply -f geode

# Role bindings & Service Account
kubectl create -f src/kubernetes/server/server-roles.yaml
kubectl create -f src/kubernetes/server/server-rolebinding.yaml
kubectl create -f src/kubernetes/server/service-account.yaml

kubectl create -f src/kubernetes/server/server-config-kafka.yaml

# Server deployment
kubectl create -f src/kubernetes/server/server-svc.yaml
kubectl create -f src/kubernetes/server/server-deployment.yaml

#echo kubectl get svc scdf-server
# Get IP
echo minikube service --url scdf-server
