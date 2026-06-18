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

## Test
The application can be tested with this command from terminal:
```shell
./mvnw clean test
```

## Jacoco coverage report
The report can be found on this directory:
`target/site/jacoco/index.html`

## Design desitions and future enhancements 

### BBDD
* A composed-index was added, optimized for the main query pattern (brand, product, temporal range and priority).

### Tests
* For E2E tests, the used syntax is `[Endpoint][Scenario][Type]Test`. example: `GetPriceSuccessE2ETest`
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
