# Spring 5 Functional Web Framework Sample

This repository contains a sample application that uses the Spring Web Flux introduced in Spring 5 with running with spring boot application.
It consists of the following types:

| Class                     | Description                                        |
| --------------------------| ---------------------------------------------------|
| `Company`                 | POJO representing a company                        |
| `CompanyRepository`       | Reactive repository for `Company`                  |
| `DummyCompanyRepository`  | Dummy implementation of `CompanyRepository`        |
| `CompanyHandler`          | Web handler that exposes a `PersonRepository`      |
| `Application`             | Contains a `main` method to start the Application  |

### Running the Spring Boot Application
 - Build using maven
 - Run the `com.wex.felix.reactive.Application` class
 
### Sample curl commands

Here are some sample `curl` commands that access resources exposed by this sample:

```sh
curl -v 'http://localhost:8080/company'
curl -v 'http://localhost:8080/company/1'
curl -d '{"name":"User company","cnpj":"27734838000165"}' -H 'Content-Type: application/json' -v 'http://localhost:8080/company'
```

Or you can test through your favorite browser Rest extension.