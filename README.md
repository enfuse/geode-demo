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

