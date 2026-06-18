# pricing-api
API for querying prices by brand, product and temporal range, by higuest priority.

## Requirements
Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation
To install the API client library to your local Maven repository, from project's root folder simply execute:

```shell
./mvnw clean package -DskipTests
```

## Clean Unused Images
Before build (or re-build), run this from terminal:
```shell
docker image prune --force
```

## Build
Generate the container with the JAR by executing:

```shell
docker build --no-cache -t pricing-api:latest .
```

## Execution
Run the microservice from container:

```shell
docker-compose up
```

## Test application from browser
Open this url in a browser: [http://localhost:8080/](http://localhost:8080/) and use Swagger interface to test.

Here are some use cases data to copy/paste:

#### Search OK
```shell
brand_id: 1
product_id: 35455
date: 2020-06-14T00:00:00
```

#### Price NOT_FOUND
```shell
brand_id: 2
product_id: 35455
date: 2020-06-14T00:00:00
```

#### Invalid brand_id
```shell
brand_id: -1
product_id: 35455
date: 2020-06-14T00:00:00
```

#### Invalid date (BAD_REQUEST)
```shell
brand_id: 1
product_id: 35455
date: 2020-06-14 00:00:00
```


#### Invalid Media-Type (NOT_ACCEPTABLE)
From terminal, run this:
```shell
curl -v -X 'GET' \
  'http://localhost:8080/v1/prices?brand_id=1&product_id=35455&application_datetime=2020-06-14T00%3A00%3A00' \
  -H 'accept: application/INVALID'
```


## Automated Tests and coverage with jacoco
The application can be tested with this command from terminal:
```shell
./mvnw clean test
```

## Jacoco coverage report
The report can be found on this directory:
[View jacoco report](./target/site/jacoco/index.html)

## Design desitions and future enhancements 

### BBDD
* A composed-index was added, optimized for the main query pattern (brand, product, temporal range and priority).

### Tests
* For E2E tests, the used syntax is `[Endpoint][Type]Test`. example: `GetPriceE2ETest`
* 

### Date and Time
* LocalDateTime was used in all layers (and TIMESTAMP in DB) because of simplicity, but in real systems should be:
  * DB: INSTANT or TIMESTAMP
  * DOMAIN: LocalDateTime
  * API: OffsetLocalDateTime

### Logging
* Some logging was used in some methods. A better approach could be to use AOP for logging input parameters, output response and process time, for more clean code in methods, observability and allow possible optimizations

### Exceptions
* Basic exception semantic was defined to separate domain, application and infrastructure, including an ErrorCode enum
* This can be enhanced with more data on each value for evolving the http error responses to RFC 7807 (Problem Details for HTTP APIs) and MDC (Mapped Diagnostic Context)
* Another evolution could be to setup Structured Logging, that can be used for ELK, Datadog and the like.
