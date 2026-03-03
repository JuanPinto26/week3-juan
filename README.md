# Week 3 – Maven Profiles Configuration

## Description
This project is a test automation framework built using Selenium, TestNG, and Maven.  
The objective of this assignment is to configure Maven profiles (dev, qa, prod), enable resource filtering, and push the project to a public GitHub repository.

The framework also integrates Newman to execute Postman API collections.

## Technologies Used
- Java 17
- Maven 3+
- Selenium 4
- TestNG 7
- Postman + Newman
- Git & GitHub

## Maven Profiles

The project includes three profiles:
- dev (active by default)
- qa
- prod

Each profile defines:
- base.url
- environment name
- browser

Profiles can be selected using the `-P` flag.

## How to Run the Tests

### Run with default profile (dev)

```bash
mvn clean test

Run with QA profile
mvn clean test -Pqa

Run with PROD profile
mvn clean test -Pprod

Project Structure
src/test/java
    ├── pages
    ├── tests
    └── utils

src/test/resources
    ├── config.properties
    └── postman

- pages → Page Object classes
- tests → Test classes
- utils → DriverFactory and ConfigReader
- resources → Configuration files

Surefire Configuration
The maven-surefire-plugin is configured to:
- Run test files matching *Test.java
- Execute tests with the selected Maven profile
- Fail the build if tests fail

Notes
The .gitignore excludes:
- target/
- .idea/
- newman-reports/
Repository is public and ready for future CI/CD configuration.


```bash
mvn clean test
