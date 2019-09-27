# Geode and Kafka Demo
Demo pipeline using Apache Geode and Apache Kafka

# Setup
In this demo we will set up a local cluster with minikube and deploy a pipeline that grabs from a file source and enriches the payload with data from geode and grab metrics on the throughput.
## Dependencies
You will need the following installed on your local workstation:

#### Docker
For [mac installations](https://docs.docker.com/docker-for-mac/install/).

#### Kubernetes & Minikube
On macs: 

```bash
$ brew install kubernetes-cli
$ brew cask install minikube
```
# install k9s to manage your Kubernetes cluster
https://github.com/derailed/k9s


> Note that you will need to have a hypervisor installed; if you don't, check out the kubernetes guide to [installing a hypervisor](https://kubernetes.io/docs/tasks/tools/install-minikube/#install-a-hypervisor).

#### search for 'tbenfuse' in build.gradle, geode-stream.yml and sql-stream.yml and replace with your docker username

# Start minikube
```
minikube start --cpus 4 --memory 8096 --vm-driver=hyperkit
```

## build & push geode-processor docker image
``` 
./SCDF/geode-processor/gradlew dockerBuildImage
docker push [username]/geode-processor:latest
```

# deploy kafka, geode, pipelines, prometheus and grafana
Navigate to the k8s folder
```
./setup.sh
```

# explore k8s cluster
```
k9s
```


# Create and Populate the Geode nodes
#### Copy over database snapshot
```bash
kubectl cp geode/data/1mil.gfd server-0:/tmp/1mil.gfd
```

#### SSH into locator-0

#### Run the gemfire shell
```bash
gfsh
```

#### connect to the locator and set up the region
```bash
gfsh > connect --locator=locator-0[10334] --jmx-manager=locator-0[1099]
gfsh > create region --name=telemetryRegion --type=REPLICATE
```

#### import data
```bash
gfsh > import data --region=telemetryRegion --file=/tmp/1mil.gfd --member=server-0
```
> file has to be on the member you're pointing to, in this case, /tmp/1mil.gfd is on geode-server-0

#### rebalance Geode nodes 
```bash
gfsh > rebalance
```

#### Quick query check to see how many entries in region
```bash
gfsh > query --query='select count(*) from /telemetryRegion'
```

#### Deploying file to file-source
copy file into file-source and watch it run through Grafana
```bash
kubectl cp geode/data/telemetry.txt postgres-file-source:/tmp/foo/1.txt
kubectl cp geode/data/telemetry.txt geode-file-source-0:/tmp/foo/1.txt
```

## Grafana

### Accessing Grafana
Find the assigned name in k9s for grafana
Port forward grafana
```bash
kubectl port-forward grafana-tenCharSeq-5Char 3000
```
Now you can access grafana on `http://localhost:3000/login`

credentials:
```bash
user:       admin
password:   password
```

A sample json dashboard is included in /k8s/grafana/dashboards/ 

## to tear down applications
# delete pods
```
kubectl delete pod --all
```

# take down minikube
```
minikube delete
```
