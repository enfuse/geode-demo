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
# Install k9s to manage your Kubernetes cluster
https://github.com/derailed/k9s


> Note that you will need to have a hypervisor installed; if you don't, check out the kubernetes guide to [installing a hypervisor](https://kubernetes.io/docs/tasks/tools/install-minikube/#install-a-hypervisor).

# Start minikube
```
minikube start --cpus 4 --memory 8096 --vm-driver=hyperkit
```

 # explore k8s cluster
 ```
 k9s
 ```
> Note that selecting a container in k9s and pressing 's' will ssh you into that container. 'l' will display logs.

# deploy kafka, geode, pipelines, prometheus and grafana
Navigate to the k8s folder

After each step, confirm that the container is deployed
> This may take a while as each container needs to be downloaded
### Deploy Geode
```
kubectl apply -f geode
```
### Deploy Kafka
```
kubectl apply -f kafka
```

### Deploy Geode Pipeline
Deploying the file source, geode processor and log sink
```
kubectl apply -f geode-stream.yml
```

### Deploy Prometheus
Containers that scrape data from the containers in the pipeline
```
kubectl apply -f mysql
kubectl apply -f prometheus
```

### Deploy Grafana
Graphing tool to utilize the information scraped from Prometheus
```

kubectl apply -f grafana

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
> Connecting to the locator and creating telemtryRegion so we can insert/fetch data
```bash
gfsh > connect --locator=locator-0[10334] --jmx-manager=locator-0[1099]
gfsh > create region --name=telemetryRegion --type=REPLICATE
```

#### import data
```bash
gfsh > import data --region=telemetryRegion --file=/tmp/1mil.gfd --member=server-0
```
> file has to be on the member you're pointing to, in this case, /tmp/1mil.gfd is on geode-server-0

#### Quick query check to confirm data has been inserted
```bash
gfsh > query --query='select count(*) from /telemetryRegion'
```

#### Deploying file to file-source
copy file into file-source

```bash
kubectl cp geode/data/1mil_telemetry.txt {{geode file source pod name}}:/tmp/foo/1.txt
```

You can view the data going through the processor and sink by looking at the logs

## Grafana

### Accessing Grafana
Find the assigned name in k9s for grafana
Port forward grafana
```bash
kubectl port-forward {{grafana pod name}} 3000
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
