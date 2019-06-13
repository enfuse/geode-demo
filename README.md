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

## Local Docker
To spin up a local docker network with Geode and MongoDB installed:

```bash
$ docker-compose up
```

#### Geode
**To interact with Geode:**

1. Get a list of your running containers; one of them should have the name `locator`

	```bash
	$ docker ps -a
	```

2. Ssh into the geode locator container
	
	```bash
	$ docker exec -ti locator gfsh
	```

3. Use `gfsh` to connect as a discovery client to the locator service:

	```bash
	gfsh> connect --locator=locator[10334]
	```

4. List members

	```bash
	gfsh> list members
	```

#### MongoDB

You can interact with the MongoDB via localhost:27017. 

Because MongoDB does not offer an http interface, I'd recommend using Robo 3T, which you can install with `$ brew cask install robo-3t`, then connect to localhost:27017.

#### Kafka and Zookeeper

To read from a Kafka topic

```bash
$ kubectl exec -c cp-kafka-broker -it {KAFKA_BROKER_POD_NAME} -- /bin/bash  /usr/bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic {TOPIC_NAME} --from-beginning
```

or use Control Center at `localhost:9021`

## Local k8s with Minikube

> If you've never installed minikube before, go to the [Kubernetes docs for instructions on installing Minikube](https://kubernetes.io/docs/tasks/tools/install-minikube/). Note that you'll need to install a hypervisor and `kubectl`, the Kubernetes command line tool. 

> If on a mac, you can install the `hyperkit` hypervisor with `brew install hyperkit`. Then, get the most recent version of minikube's fork of the hyperkit driver: `curl -LO https://storage.googleapis.com/minikube/releases/latest/docker-machine-driver-hyperkit \&& sudo install -o root -g wheel -m 4755 docker-machine-driver-hyperkit /usr/local/bin/`

Start minikube:

```bash
$ minikube start --cpus 4 --memory 6096 --vm-driver=hyperkit
```

> On starting minikube, your kubectl context should be set to `minikube`. You can verify this by running `$ kubectl config current-context`.

### To start up all dependencies
```bash
$ cd k8s
$ ./setup.sh 
```

### To start up individual dependencies

#### Geode

```bash
$ kubectl apply -f k8s/geode
$ kubectl get all,pv,pvc -o wide
```

#### MongoDB
To start up MongoDB with Helm:

```bash
$ helm repo add stable https://kubernetes-charts.storage.googleapis.com/
$ helm install --name mongo stable/mongodb-replicaset
```

or without Helm:

```bash
$ kubectl apply -f k8s/mongodb-replicaset
$ kubectl get all,pv,pvc -o wide
```

#### Confluent Kafka & Zookeeper
```bash
$ helm repo add confluentinc https://confluentinc.github.io/cp-helm-charts/
$ helm install confluentinc/cp-helm-charts --name confluent --set cp-schema-registry.enabled=false,cp-kafka-rest.enabled=false,cp-kafka-connect.enabled=false,cp-ksql-server.enabled=false,cp-zookeeper.servers=1
```