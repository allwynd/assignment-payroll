
# Assignment - Payroll Java Application

## Overview:

Application generates employee payslip by accepting the following Employee Pay Details:
- First Name (Optional)
- Last Name  (Optional) 
- Pay Start Date [e.g. given format: either Jan-2017 or January-2016] - Optional, if left blank the current period is taken.
- Annual Salary [positive value] - Required 
- Super Rate [value between 0 and 50 incl.] - Required

The application is designed to accept:
- An Employee Pay details as User Input - Via command line or via REST client. The output Payslip is either generated on the command line (as text) or via REST API (in JSON format). 
- Multiple Employee Pay details is accepted as a File Input (json) - Via command line. The input file can be pointed to an external input folder. The output Payslip is generated in the output json folder.  
- Preferred choice of input/output is JSON and CONSOLE output. However, the application is extensible in future to accept other inputs and generate other output with minimal code changes. 

(More information on how to run the application - below)

## Assumptions:

The application makes the following assumptions:

- User input is accepted from CLI or via REST client.
- Application can load employee pay details from a file from an external input location.
- Application can generate external output as TEXT/JSON via CLI or in JSON format via REST Client.
- Input Employee Pay information - as a JSON file, expected in a format (loaded at start up) and field order ("input/json/employee-pay.json").
- User Date input in MMM-YYYY (e.g. dec-2016, December-2016 or DEC-2016 are valid inputs. 2015-Dec or 12-2015 is not valid)
- Output files - Payslip is generated in JSON format (for bulk load) to the output directory specified at the time of start up ("output/json/emppayslip.json). 
- The taxable income data sheet is currently is stored in code and statically loaded in-memory to keep it simple and faster access. However it can be re-factored to load from other means with minimal code changes.
- The Employee Payroll application is designed, built and tested based on a set of predefined User stories and Acceptance Criteria. 
- The application runs a series of automated test using JUnit when the application is compiled and packaged.

## User Stories and Acceptance Criteria:
#### User Story 1: 
As an HR Manager, I want to load all employee pay details from a source, so that this information can be used to generate payslips 
and sent to employees.

##### AC1:
Given: One or more employee pay details is stored to a file in JSON format
When: The system loads the file in the specified format during startup
Then: Employees payslips is generated to the specified output folder ("output\json\") in JSON format.

##### AC2:
Given: One or more employee pay details is stored to a file in JSON format
When: The system cannot find the specified input location during startup
Then: A system error is thrown during startup stating it cannot find the file for the given location and terminate.

##### AC3:
Given: One or more employee pay details is stored to a file in JSON format
When: The employee pay details are missing (either, Annual Salary is not specified and not numeric or Super Rate is null or not between 0 - 50 incl.) 
Then: A business validation is thrown during startup stating the validation error and terminate.

#### User Story 2: 
As an HR Manager, I want to calculate Taxable Income for different income thresholds, so that this information can be used to generate employee payslip.

##### AC1: 
Given: A taxable income is calculated for lowest income threshold
When: Annual Income is equal to or below the taxable income threshold
Then: Income Tax calculated should be zero (e.g. 0 - $18,200 Nil)

##### AC2: 
Given: A taxable income is calculated for highest income threshold
When: Annual Income is equal to or above the taxable income threshold
Then: Income Tax calculated should be calculated based on the criteria of highest taxable income (e.g. $54,547 plus 45c for each $1 over $180,000). 

##### AC3: 
Given: A taxable income is calculated between lower and higher income threshold
When: Annual Income is between the higer and lower taxable income threshold
Then: Income Tax calculated should be calculated based on the taxable income that meets the criteria from the list. (e.g. $17,547 plus 37c for each $1 over $80,000).


#### User Story 3: 
As an Employee, I want to input my pay details, so that i can view my payslip

##### AC1: 
Given: A Employee pay details entered
When:  Employee first name (optional), last name (optional), pay start date (if null current month taken),
annual salary (required), and super rate (required) is received
Then: The employee payslip is generated and displayed.

##### AC2: 
Given: A Employee pay details entered
When:  Employee Annual salary is not positive or null
Then: A validation error is displayed saying the annual salary has to be a positive value.

##### AC3: 
Given: A Employee pay details entered
When:  Employee Super Rate value is not within expected range (between 0 and 50 incl.)
Then: A validation error is displayed saying the Super Rate has to be within the given range.

## Running the Payroll Java Application:

The payroll application is bundled as a maven project and the entire application can be run from the CLI. The entire
source code along with the application war file (EmpPayroll-1.0.0.war) is available part of the source code and comes with an embedded web container.

### Pre-requisites

- Ensure that you have Maven 3.3.x and JAVA 8 installed - (download maven from https://maven.apache.org/download.cgi and JAVA 8 from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
- Setup maven home and java home to the classpath
- To check if Maven and Java is setup correctly, at the command prompt type: ```mvn --version```. This should display the maven and java version along with the environment variables printed.
- Download and extract the the Source code (assignment-payroll-master.zip) from the provided link.

```NOTE: You may not need to install and run maven if you 

### How to Run the Java Payroll Application:

- Open the command prompt and go the the extracted directory (under XXX/assignment-payroll-master/JEmployeePayroll). Where XXX is the path to the extracted directory.
- List out files under the directory. Ensure, "input" and "output" directory exists. The application will read and write files (csv o json) to this directory.
- Run Maven clean: ```mvn clean```. This should successfully cleanup up anything target directory data (if any)
- Run Maven Install: ```mvn install``` (this should successfully compile and download any dependencies and then run the Test Suite). You are now ready to execute the payroll app.

#### Options (presently there is an "input/csv/employee-pay.csv" that exists. Also sample input Json - "input/json/employee-pay.json"):

- Accept User Input and calculates the employee tax and prints payslip to the console.
```
mvn exec:java -Dexec.mainClass="assignment.payroll.GenerateEmpPayslipApp"
```

- Input csv file, Output: CSV - (input from the "input" directory. Outputs CSV payslip to "output\csv" directory).
```
mvn exec:java -Dexec.mainClass="assignment.payroll.GenerateEmpPayslipApp" -Dexec.args="employee-pay.csv CSV"
```

- Input csv file, Output: JSON - (input from the "input" directory. Outputs JSON payslip to "output\json" directory).
```
mvn exec:java -Dexec.mainClass="assignment.payroll.GenerateEmpPayslipApp" -Dexec.args="employee-pay.csv JSON"
```

- Input json file, Output: CSV - (input from the "input\json" directory. Outputs CSV payslip to "output\csv" directory).
```
mvn exec:java -Dexec.mainClass="assignment.payroll.GenerateEmpPayslipApp" -Dexec.args="employee-pay.json CSV"
```
- Input csv or json file, Output: console file
```
mvn exec:java -Dexec.mainClass="assignment.payroll.GenerateEmpPayslipApp" -Dexec.args="employee-pay.json CONSOLE"
mvn exec:java -Dexec.mainClass="assignment.payroll.GenerateEmpPayslipApp" -Dexec.args="employee-pay.csv CONSOLE"
```



