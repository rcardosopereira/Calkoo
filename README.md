 # Calkoo: The VAT Calculator
This is a repository for the VAT Calculator project. It uses Java, Selenium, Cucumber, JUnit and Maven as a build automation tool.

## Documentation
If you would like to check out the documentation on manual testing, please visit this folder below in project:<br/>
..\Calkoo\Documentation <br/>

## Setup
To run the project, you will need to have the following installed on your computer:<br/>
-Java (Version 21.0.2 2024-01-16 LTS);<br/>
-Selenium (Version 4.28.0);<br/>
-Cucumber (Version 7.12.0);<br/>
-JUnit (Version 5.11.4);<br/>
-Maven (build automation tool);<br/>
-Google Chrome Browser (Version 132.0.6834.160 (Official Build) (64-bit));<br/>
- https://storage.googleapis.com/chrome-for-testing-public/132.0.6834.159/win64/chromedriver-win64.zip <br/>

## Running the project (Installation)
To run the project, follow the steps below.
![TheVatCalculator.gif](TheVatCalculator.gif)

***Clone the repository: <br/>*** 
git clone <https://github.com/rcardosopereira/Calkoo> <br/>
***Navigate to the project directory (root of the project) in your terminal or command prompt: <br />***
cd Calkoo <br/>
<br/>
***Run the tests using the TestRunner class or via Maven: <br />***
"***mvn test***" or "***mvn clean test***" <br/>

***Generate the Allure Report: <br />***
After running the tests, generate the report with the following command:<br/>
***mvn allure:serve*** <br/>
This will automatically open the report in the browser. <br/>
![img.png](img.png)

***Generate the Report for Later Viewing: <br />***
If you want to generate the report without opening the browser automatically, use: <br/>
***mvn allure:report*** <br/>
The report will be generated in the target/allure-report folder. You can open the index.html file manually in the browser. <br/>

***If necessary, run the command to install dependencies: <br />***
"mvn clean install" <br/>

## Explanation of Each Folder and File<br/>
***src/main/java/com/calkoo/utils/ConfigManager.java<br/>***
Contains the ConfigManager class for reading configuration properties from config.properties.<br/>

***src/test/java/com/calkoo/pages/HomePage.java<br/>***
Contains the HomePage class with methods for interacting with the VAT Calculator page.<br/>

***src/test/java/com/calkoo/steps/Steps.java<br/>***
Contains the Steps class with Cucumber step definitions.<br/>

***src/test/java/com/calkoo/runner/TestRunner.java<br/>***
Contains the TestRunner class to execute Cucumber tests.<br/>

***src/test/resources/features/vat_calculator.feature<br/>***
Contains the Gherkin feature file with test scenarios.<br/>

***src/test/resources/driver/chromedriver.exe<br/>***
Contains the ChromeDriver executable for Selenium.<br/>

***src/test/resources/config.properties<br/>***
Contains configuration properties like browser type, URL, and driver paths.<br/>

***src/test/resources/log4j2.xml<br/>***
Contains Log4j2 configuration for logging.<br/>

***pom.xml<br/>***
Maven configuration file for dependencies and plugins (e.g., Cucumber, WebDriverManager, Log4j).<br/>

***README.md<br/>***
Project documentation with instructions for setup and execution.<br/>

## Design decisions
The project was designed to follow the page object model, separating the packages (pages, runner and steps) and pages of the application into individual classes, and using them to interact with the application. The tests themselves use the Cucumber BDD style, allowing for clear, human-readable tests.
