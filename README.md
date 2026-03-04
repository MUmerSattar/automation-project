🚀 E-Commerce Automation Framework
Selenium + Java + TestNG + Cucumber (Hybrid Framework)

📌 Project Overview

This project is a Hybrid Test Automation Framework built to automate an E-Commerce web application using industry-standard best practices.

The framework combines:
Page Object Model (POM)
TestNG Framework
Cucumber BDD Integration
Data-Driven Testing (JSON)
Retry Mechanism
Extent HTML Reporting
Screenshot Capture on Failure
Maven Build Management

The project structure is designed to simulate real-world enterprise automation frameworks used in Agile teams.

🛠 Technology Stack
Technology	Purpose
Java:    	            Programming Language
Selenium:             WebDriver	UI Automation
TestNG:    	          Test Execution & Assertions
Cucumber:  	          Behavior Driven Development (BDD)
Maven:    	          Dependency & Build Management
WebDriverManager:	    Browser Driver Management
Extent Reports:  	    HTML Reporting
JSON (Jackson/Gson):	Data-Driven Testing
Git	Version Control

📂 Project Structure
src
 ├── main
 │    ├── java
 │    │     ├── pageobjects
 │    │     ├── AbstractComponents
 │    │     └── resources
 │
 ├── test
 │    ├── java
 │    │     ├── umerlearning (Test Classes)
 │    │     ├── stepDefinitions
 │    │     ├── TestComponents
 │    │     ├── Cucumber (Feature Files)
 │    │     └── data (JSON Test Data)
 │
reports
testSuites
pom.xml

🔹 Framework Design Explanation
1️⃣ BaseTest (Test Setup & Configuration)

Location: TestComponents/BaseTest.java

Responsibilities:
WebDriver initialization
Browser configuration
Reading GlobalData.properties
Launching application
Screenshot capture on failure
TearDown logic

Purpose:
Provides reusable setup and cleanup logic for all test classes.

2️⃣ Page Object Model (POM)

Location: pageobjects

Each webpage is represented as a separate class:
LandingPage
ProductCatalogue
CartPage
CheckOutPage
ConfirmationPage
OrderPage

Each class contains:
WebElements (locators)
Page actions (methods)
Navigation handling

Purpose:
Improves maintainability, readability, and scalability.

3️⃣ Abstract Component Layer

Location: AbstractComponents/AbstractComponent.java

This class contains reusable methods such as:
Waiting for elements
Handling dynamic elements
Navigation utilities

Purpose:
Avoids code duplication across page classes.

4️⃣ TestNG Integration

Test classes:
SubmitOrderTest
ErrorValidationsTest
RegisterUser

Features implemented:

@BeforeMethod / @AfterMethod
DataProvider (if applicable)
Parallel test support (via testng.xml)
Retry mechanism
Listener integration

5️⃣ Retry Mechanism

Location: Retry.java
Automatically re-runs failed test cases.

Purpose:
Handles flaky test scenarios in real-world environments.

6️⃣ Listeners & Reporting

Location: Listeners.java
Report Config: ExtentReporterNG.java

Extent Reports integration
Screenshot attachment on failure
Detailed execution logs
Pass/Fail status with stack trace

Report Location:
/reports/index.html

7️⃣ Cucumber BDD Integration

Feature File:
Cucumber/PurchaseOrder.feature

Step Definitions:
stepDefinitions/StepDefinitionImp.java

Implements:
Gherkin syntax
Scenario Outline
Parameterized steps
Integration with TestNG

Purpose:
Allows readable test scenarios for business stakeholders.

8️⃣ Data-Driven Testing (JSON)

Location:
data/PurchaseOrder.json

Test data is read dynamically from JSON files.

Benefits:
Separation of test logic and test data
Easy scalability
Real-world enterprise approach

▶️ How To Execute Tests
Method 1: Using Maven
mvn clean test
Method 2: Using TestNG Suite

Run:

testSuites/testng.xml
Method 3: Using Specific Suite

Purchase.xml

ErrorValidationtestng.xml

Method 4: Run Cucumber Runner

Execute the TestNG runner class configured for Cucumber.

📊 Reporting

After execution:

Navigate to:

/reports/index.html

Report includes:

Execution Summary

Pass/Fail status

Failure stack trace

Screenshot on failure

⚙️ Key Automation Features Implemented

✔ Hybrid Framework Design
✔ Page Object Model
✔ Abstract Component Reusability
✔ Data-Driven Testing (JSON)
✔ Cucumber BDD
✔ Retry Failed Tests
✔ Listener Integration
✔ Screenshot Capture
✔ Extent HTML Reporting
✔ Maven Build Lifecycle

📈 What This Project Demonstrates

Through this framework, the following automation capabilities are demonstrated:

Building scalable automation architecture
Implementing reusable test design patterns
Handling dynamic web elements
Integrating BDD with TestNG
Designing enterprise-ready test suites
Managing automation execution using Maven
Generating professional test execution reports

🔮 Future Enhancements

REST Assured API Automation Integration
Jenkins CI/CD Pipeline
Dockerized execution
Cloud execution (BrowserStack / LambdaTest)
Parallel execution optimization

👨‍💻 Author

Umer Sattar
Automation QA Engineer
umer.sattar309@gmail.com
