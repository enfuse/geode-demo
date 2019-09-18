# SCDF, Geode and Kafka Demo
Demo pipeline using Spring Cloud Data Flow, Apache Geode and Apache Kafka

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

> Note that you will need to have a hypervisor installed; if you don't, check out the kubernetes guide to [installing a hypervisor](https://kubernetes.io/docs/tasks/tools/install-minikube/#install-a-hypervisor).


# search for 'tbenfuse' in build.gradle and k8s yaml and replace with your username

# Start minikube
```
minikube start --cpus 4 --memory 8096 --vm-driver=hyperkit
```
# install this app to manage your local k8s cluster
https://github.com/derailed/k9s

#did this command to see if minikube needed to hook into local docker. dont think it did anything
```
eval $(minikube docker-env)
```

# deploy kafka & geode
Navigate to the k8s folder
```
./setup.sh
```

# explore k8s cluster
```
k9s
```

## to tear down applications
# delete pods
```
kubectl delete pod --all
```

#take down minikube
```
minikube delete
```

#Create and Populate the Geode nodes
Copy over database snapshot
```bash
kubectl cp 1mil.gfd server-0:/tmp/1mil.gfd
```

connect to geode-locator-0

Run the gemfire shell
```bash
gfsh
```

connect to the locator and set up the region
```bash
gfsh > connect --locator=locator-0[10334] --jmx-manager=locator-0[1099]
gfsh > create region --name=telemetryRegion --type=REPLICATE
gfsh > create region --name=telemetryRegion --type=PARTITION
```

$import data
file has to be on the member you're pointing to, in this case, /tmp/1mil.gfd is on geode-server-0
```bash
gfsh > import data --region=telemetryRegion --file=/tmp/1mil.gfd --member=server-0

```

rebalance Geode nodes 
```bash
gfsh > rebalance
```

Quick Query check to see how many entries in region
```bash
gfsh > query --query='select count(*) from /telemetryRegion'
```
Result should be 1 million rows.
If it is at 100590 rows, there is a 5mb limit on the terminal that needs to be removed.

Login to grafana with admin/password

#Deploying file to file-source
sh into file-source container and make /tmp/foo/ directory
copy file into file-source
```bash
kubectl cp geode/data/telemetry.txt postgres-file-source:/tmp/foo/1.txt
kubectl cp geode/data/telemetry-0.txt geode-file-source-0:/tmp/foo/1.txt
kubectl cp geode/data/telemetry-1.txt geode-file-source-1:/tmp/foo/1.txt
kubectl cp geode/data/telemetry-2.txt geode-file-source-2:/tmp/foo/1.txt
kubectl cp geode/data/telemetry-3.txt geode-file-source-3:/tmp/foo/1.txt
```


