# Calkoo: The VAT Calculator
This is a repository for the VAT Calculator project. It uses Java, Selenium, Cucumber, JUnit and Maven as a build automation tool.

## Documentation
If you would like to check out the documentation on manual testing, please visit the link below:<br/>
***GitHub:*** <https://github.com/rcardosopereira/Calkoo/tree/main/Documentation> <br/>
***Google Drive:*** <https://drive.google.com/drive/folders/14LuqiA3qRhJs_XH7yQNgIz2oda2mqvT2> <br/>

## Setup
To run the project, you will need to have the following installed on your computer:<br/>
-Java (Version 20.0.1 2023-04-18);<br/>
-Selenium (Version 4.9.0);<br/>
-Cucumber (Version 7.12.0);<br/>
-JUnit (Version 5.9.3);<br/>
-Google Chrome Browser (Version 113.0.5672.127 - Official Build 64-bit);<br/>   
-Maven (build automation tool);<br/>

## Running the project (Installation)
To run the project, follow the steps below.

***Clone the repository: <br/>*** 
git clone <https://github.com/rcardosopereira/Calkoo> <br/>
***Navigate to the project directory (root of the project) in your terminal or command prompt: <br />***
cd Calkoo <br/>
***Run the following command to install dependencies:<br />***
"mvn clean install" or "mvn clean test" to execute the tests. <br/>
***Optionally, run the command: <br/>***
"mvn verify -DskipTests" to generate the test report.<br />


### Testing(features) and WebDriver (ChromeDriver 113.0.5672.63) 
To run the tests, follow the instructions above for running the project. <br />
The tests use Cucumber and are located in the **src/test/resources/features** directory.<br />
The Webdriver is located in the **src/test/resources/driver** directory.<br />

## Design decisions
The project was designed to follow the page object model, separating the packages (pages, runner and steps) and pages of the application into individual classes, and using them to interact with the application. The tests themselves use the Cucumber BDD style, allowing for clear, human-readable tests.
