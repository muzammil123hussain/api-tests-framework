
---

# microservices-api-tests

## Overview
- **Framework Name:** microservices-api-tests.
- **Purpose:** Efficient API automation and testing with easy maintenance.
- **Key Technologies:** Java, RestAssured, Lombok, Cucumber.
- **Architecture:** Modular Service Based.
- **Principles:** Behavior-Driven Development (BDD).

## Features
- **Modular Architecture:** Easily extendable and maintainable.
- **RestAssured Integration:** Simplifies RESTful API testing.
- **Lombok Annotations:** Reduces boilerplate code for data binding.
- **Cucumber for BDD:** Writing test scenarios in plain language.
- **Reporting:** Support for Cucumber & Allure RestAssured Report.
- **Environment-Specific Configuration:** Managed through Maven POM profiles.
- **Reusable Components:** Common Service, Command Step Definition, Expectations, and Scenario classes.

## Technology Stack
- **Java:** Core programming language.
- **Junit:** For Assertions and Testing framework.
- **RestAssured:** For RESTful API testing.
- **Lombok:** To minimize boilerplate code for Data structure(Using MODEL and MAPPER approach).
- **Jackson Data Bind:** To create the JSON based payloads.
- **Cucumber:** For implementing BDD.
- **Maven:** Build and dependency management with Profile based support for Environment.
- **Allure:** Generate and Publish detailed report. 

## Getting Started

### Prerequisites
- **JDK:** Java Development Kit (JDK) 21 or higher
- **Maven:** Maven 3.6.0 or higher
- **Editor:** IntelliJ IDEA Community Edition
- **Plugins for Editor:** Cucumber, Lombok
 
### Installation
1. **Clone the Repository**
   ```bash
   git clone https://github.com/muzammil123hussain/api-tests-framework.git
   cd api-tests-framework
   ```
2. **Install Dependencies**
   ```bash
   mvn clean install
   ```

### Running Tests with Profile
To execute tests, use the following command:
```bash
mvn test -P<profile-name>
```
**Note:** Replace `<profile-name>` with the desired environment profile (e.g., qa, test, prod).

### Running Tests with Tags
To execute tests with tags, use the following command:
```bash
 mvn clean test -D"cucumber.filter.tags=@tag-name1 or @tag-name2"
```
### Running Tests with Tags & Profile
To execute tests with tags & Profile, use the following command:
```bash
  mvn test -P<profile-name> -D"cucumber.filter.tags=@tag-name"
```
### Running Tests with Tags & Profile with Allure Report
To execute tests with tags & Profile with Allure Report, use the following command:
```bash
 mvn test -P<profile-name> -D"cucumber.filter.tags=@tag-name" allure:report
 mvn allure:serve
```

## Directory Structure
```
microservices-api-tests/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.muzammil/
│   │   │   │   ├── cucumber/
│   │   │   │   ├── dataproviders/
│   │   │   │   ├── enums/
│   │   │   │   ├── exceptions/
│   │   │   │   ├── mappers/
│   │   │   │   ├── models/
│   │   │   │   ├── services/
│   │   │   │   ├── utils/
│   │   ├── resources/
│   ├── test/
│   │   ├── java/
│   │   │   ├── com.muzammil/
│   │   │   │   ├── runner/
│   │   │   │   ├── stepdefinition/
│   │   ├── resources/
│   │   │   ├── features/
├── pom.xml
```

## Key Components
- **Data Provider Class:** Handles environment-specific variables.
- **Expectations Class:** Defines expected outcomes for validation.
- **Scenario Class (using Enums):** Standardizes test scenario definitions.
- **Common Service Class:** Provides shared functionalities for API interactions.
- **Command Step Definition Class:** Implements reusable test steps.
- **Cucumber Feature Files:** Define test scenarios in plain language.

## Contributing
We welcome contributions to enhance this framework. Please follow these steps:
1. Create a feature branch.
2. Commit your changes.
3. Push to the branch.
4. Create a Pull Request.

## Author
- **Name:** Muzammil Hussain
- **Position:** Senior Automation Engineer

## Contact
For questions or suggestions, please contact:
- **Email:** muzammilchuhan.lb@gmail.com

---