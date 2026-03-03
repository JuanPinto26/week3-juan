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
- **dev** (active by default)
- **qa**
- **prod**

Each profile defines:
- `base.url`
- `env`
- `browser`

Profiles can be selected using the `-P` flag.

## Prerequisites
Make sure you have:
- Java 17 installed
- Maven installed
- Google Chrome installed (for Selenium tests)
- Node.js installed (for Newman execution)

You can verify versions with:
-java -version
-mvn -version
-node -v
-npm -v

## Setup
Clone the repository and run Maven:

-git clone https://github.com/JuanPinto26/week3-juan.git
cd week3-juan

> Note: In my local project, the Maven project is inside `maven-project/`.
> So locally I run:
-cd maven-project

## How to Run the Tests

### Run with default profile (dev)
mvn clean test

### Run with QA profile
mvn clean test -Pqa

### Run with PROD profile
mvn clean test -Pprod

## Configuration (config.properties)
The file `src/test/resources/config.properties` uses Maven variables with filtering enabled, for example:
- `base.url=${base.url}`
- `env=${env}`
- `browser=${browser}`

These values change automatically depending on the selected profile.

## ConfigReader Utility
A `ConfigReader` class was created to load `config.properties` from `src/test/resources` and provide helper methods like:
- `getBaseUrl()`
- `getEnv()`
- `getBrowser()`

At least one test reads values from `ConfigReader` to confirm profile-based configuration is working.

## Project Structure

src/test/java
├── pages
├── tests
└── utils

src/test/resources
├── config.properties
└── postman


- **pages** → Page Object classes (LoginPage, InventoryPage, etc.)
- **tests** → Test classes (LoginTest, InventoryTest)
- **utils** → DriverFactory and ConfigReader
- **resources** → Config files and Postman collection/environment

## Surefire Configuration
The `maven-surefire-plugin` is configured to:
- Run test files matching `*Test.java`
- Execute tests with the selected Maven profile
- Fail the build if tests fail (`testFailureIgnore=false`)

## Postman / Newman Integration (from Week 2)
This project also includes the Week 2 API test suite:
- Postman collection and environment stored in `src/test/resources/postman/`
- Newman is executed using Maven via the `exec-maven-plugin`

The HTML report is generated inside:
- `newman-reports/newman-report.html` (if enabled in your plugin config)

## .gitignore Notes
The `.gitignore` is configured to exclude common generated folders and IDE files, including:
- `target/`
- `.idea/`
- logs
- OS files (like `.DS_Store`)

This keeps the GitHub repository clean (no `target/` uploaded).

## Previous Work Integration (Week 1 & Week 2)
This Week 3 project builds on what I developed in the previous weeks:

- **Week 1:** Created the base Maven project structure and TestNG setup.
- **Week 2:** Implemented API testing using Postman + Newman and integrated it into the Maven lifecycle.
- **Week 3:** Added Maven profiles (dev/qa/prod), enabled resource filtering, and created ConfigReader to support environment-based configuration.

The GitHub repository was initialized using the existing project structure created in the earlier weeks, and then improved step-by-step with the new Week 3 requirements.

## Repository
Public GitHub repo:
https://github.com/JuanPinto26/week3-juan
