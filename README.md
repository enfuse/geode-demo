# SCDF, Geode and Kafka Demo
Demo pipeline using Spring Cloud Data Flow, Apache Geode and Apache Kafka

## Setup

### Local Docker
To spin up a local docker network with Geode installed:

```bash
$ docker-compose up
```

#### Geode
To interact with Geode:

Get a list of your running containers; one of them should have the name `locator`
```bash
$ docker ps -a
```

Ssh into the geode locator container
```bash
$ docker exec -ti locator gfsh
```

Use `gfsh` to connect as a discovery client to the locator service:

```
gfsh> connect --locator=locator[10334]
```

List members:
```
gfsh> list members
```

#### MongoDB

You can interact with the MongoDB via the MongoExpress UI at http://localhost:8081 or connect directly to Mongo via http://localhost:27017. 
For more advanced search query capability, I'd recommend using Robo 3T, which you can install with `$ brew cask install robo-3t`, then connect to localhost:27017.


### Local k8s with Minikube

> If you've never installed minikube before, go to the [Kubernetes docs for instructions on installing Minikube](https://kubernetes.io/docs/tasks/tools/install-minikube/). Note that you'll need to install a hypervisor and `kubectl`, the Kubernetes command line tool. 

> If on a mac, you can install the `hyperkit` hypervisor with `brew install hyperkit`. Then, to get the most recent version of minikube's fork of the hyperkit driver: `curl -LO https://storage.googleapis.com/minikube/releases/latest/docker-machine-driver-hyperkit \&& sudo install -o root -g wheel -m 4755 docker-machine-driver-hyperkit /usr/local/bin/`

Start minikube:
```bash
$ minikube start --cpus 4 --memory 6096 --vm-driver=hyperkit
```

> On starting minikube, your kubectl context should be set to `minikube`. You can verify this by running `$ kubectl config current-context`.

#### Geode
To start up Geode:

```bash
$ kubectl apply -f k8s/geode
$ kubectl get all,pv,pvc -o wide
```

