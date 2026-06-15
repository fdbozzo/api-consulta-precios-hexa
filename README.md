# pricing-api

API for querying prices by brand, product and temporal range, by higuest priority.

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, from project's root folder simply execute:

```shell
mvn clean install
```

## Build

Generate the container with the JAR by executing:

```shell
docker build -t pricing-api:latest .
```

## Execution

Run the microservice from container:

```shell
docker-compose up
```


## About some design desitions 

### BBDD
* A composed-index was added, optimized for the main query pattern (brand, product, temporal range and priority).

### Tests
* For E2E tests, the used syntax is `[Endpoint][Scenario][Type]Test`. example: `GetPriceSuccessE2ETest`
* 
