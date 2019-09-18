# Geode and Kafka Demo
Demo pipeline using Apache Geode and Apache Kafka

# Setup

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


#### search for 'tbenfuse' in build.gradle and k8s yaml and replace with your username

If you're using minikube as your cluster, you can skip the GCP section and go to [minikube](#start-minikube)

# Adding GCP config to kubectl config

```bash
gcloud config set project my-project
gcloud config set compute/zone my-zone
gcloud container clusters get-credentials my-project
```

# See project views registered to kubectl
```bash
kubectl config view
```

# Point Kubectl to use a cluster
```bash
kubectl config use-context my-project-view-name
```

# Start GCP cluster
```bash
gcloud container clusters resize my-project --num-nodes=8 --zone=my-zone
```

# Start minikube
```
minikube start --cpus 4 --memory 8096 --vm-driver=hyperkit
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


#Create and Populate the Geode nodes
####Copy over database snapshot
```bash
kubectl cp 1mil.gfd server-0:/tmp/1mil.gfd
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
gfsh > create region --name=telemetryRegion --type=PARTITION
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
> Result should be 1 million rows.


#### Deploying file to file-source
copy file into file-source and watch it run through Grafana
```bash
kubectl cp geode/data/telemetry.txt postgres-file-source:/tmp/foo/1.txt
kubectl cp geode/data/telemetry-0.txt geode-file-source-0:/tmp/foo/1.txt
kubectl cp geode/data/telemetry-1.txt geode-file-source-1:/tmp/foo/1.txt
kubectl cp geode/data/telemetry-2.txt geode-file-source-2:/tmp/foo/1.txt
kubectl cp geode/data/telemetry-3.txt geode-file-source-3:/tmp/foo/1.txt
```

## Grafana

### Accessing Grafana
Port forward grafana
```bash
kubectl port-forward 
```
default username/password is admin/password

## to tear down applications
# delete pods
```
kubectl delete pod --all
```

# take down minikube
```
minikube delete
```
