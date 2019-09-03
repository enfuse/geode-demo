# Kubernetes Setup

## search for 'tbenfuse' in gradlew and k8s yaml and replace with your username

## Start minikube
```shell script
brew cask install minikube
minikube start --cpus 4 --memory 8096 --vm-driver=hyperkit
```

## build & push geode-processor docker image
``` 
./SCDF/geode-processor/gradlew dockerBuildImage
docker push username/geode-processor:latest
```

## deploy kafka, geode, spring cloud stream apps
```
./setup.sh
```


## build application pods
```
kubectl apply -f k8s/scdf-geode-stream.yml
```

## delete pods
```
kubectl delete pod --all
```

## take down minikube
```
minikube delete
```


## explore k8s cluster
```
brew install k9s
k9s
```
k9s: https://github.com/derailed/k9s
