
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
**Given**: One or more employee pay details is stored to a file in JSON format
**When**: The system loads the file in the specified format during startup
**Then**: Employees payslips is generated to the specified output folder ("output\json\") in JSON format.

##### AC2:
**Given**: One or more employee pay details is stored to a file in JSON format
**When**: The system cannot find the specified input location during startup
**Then**: A system error is thrown during startup stating it cannot find the file for the given location and terminate.

##### AC3:
**Given**: One or more employee pay details is stored to a file in JSON format
**When**: The employee pay details are missing (either, Annual Salary is not specified and not numeric or Super Rate is null or not between 0 - 50 incl.) 
**Then**: A business validation is thrown during startup stating the validation error and terminate.

#### User Story 2: 
As an HR Manager, I want to calculate Taxable Income for different income thresholds, so that this information can be used to generate employee payslip.

##### AC1: 
**Given**: A taxable income is calculated for lowest income threshold
**When**: Annual Income is equal to or below the taxable income threshold
**Then**: Income Tax calculated should be zero (e.g. 0 - $18,200 Nil)

##### AC2: 
**Given**: A taxable income is calculated for highest income threshold
**When**: Annual Income is equal to or above the taxable income threshold
**Then**: Income Tax calculated should be calculated based on the criteria of highest taxable income (e.g. $54,547 plus 45c for each $1 over $180,000). 

##### AC3: 
**Given**: A taxable income is calculated between lower and higher income threshold
**When**: Annual Income is between the higer and lower taxable income threshold
**Then**: Income Tax calculated should be calculated based on the taxable income that meets the criteria from the list. (e.g. $17,547 plus 37c for each $1 over $80,000).


#### User Story 3: 
As an Employee, I want to input my pay details, so that i can view my payslip

##### AC1: 
**Given**: A Employee pay details entered
**When**:  Employee first name (optional), last name (optional), pay start date (if null current month taken),
annual salary (required), and super rate (required) is received
**Then**: The employee payslip is generated and displayed.

##### AC2: 
**Given**: A Employee pay details entered
**When**:  Employee Annual salary is not positive or null
**Then**: A validation error is displayed saying the annual salary has to be a positive value.

##### AC3: 
**Given**: A Employee pay details entered
**When**:  Employee Super Rate value is not within expected range (between 0 and 50 incl.)
**Then**: A validation error is displayed saying the Super Rate has to be within the given range.

## Running the Payroll Java Application:

The payroll application is bundled as a maven project and the entire application can be run from the CLI. The source along with the application war file (EmpPayroll-1.0.0.war) is available on the github project. 

The application runs on the CLI with in an embedded tomcat container on port 11222. Ensure this port is free.

### Pre-requisites

1. Ensure that you have JAVA 8 installed - (JAVA 8 from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
2. Setup java home to the PATH environment variable.
3. To verify if Java 8 is installed, run ```java -version``` at the command prompt. It should display the version.
4. Download and extract the the Source code (assignment-payroll-master.zip) from the provided GitHub link.
5. The application runs using external file locations to load employee pay details (input json file) and to generate payslips (output json file) at application start up. Copy the the ''myob'' folder from the root of the application folder (e.g. "assignment-payroll-master\myob"). This folder contains the input and the output locations for files. This folder can be copied to any location where the application can be pointed during start up. 

```NOTE: If you're looking to build the project, you may need to install maven set the maven home and java home to the PATH environment variable .  ```

### Run the Application:

1. Open the command prompt and go the "target" folder of the extracted directory (e.g. under extracted folder '''assignment-payroll-master\target''')
2. List out files under the "target" directory. Ensure, the 	"EmpPayroll-1.0.0.war" exists.
3 Start up the application using the following command: 
```
java -Djson.input.home=<path-to-the-input-json-folder> -Djson.output.home=<path-to-the-output-json-folder> -jar EmpPayroll-1.0.0.war
```

Where **<path-to-the-input-json-folder>** and **<path-to-the-output-json-folder>** are paths to the external input json folder and output folder where the application loads the employee pay details and generates the payslips respectively (as per Pt. 5 in the ___Pre-requisites___ step above).
   
4. You should see the application starting up displaying the Spring Boot log along with other application logging.

#### Bulk load Employee Pay Information
- Once the application starts up successfully, the key information to look for in the logs is location where the employee pay details input file is read and the generated employee payslip to the output folder as configured during startup. To verify, you can locate *emppayslip.json* file under the **myob\output\json** folder. You can view this in json editor to view the generated paylips along with the taxable income. 
 

#### Accept Single Employee Details as input from CLI
**_You can terminate the existing running application by using CRTL + C on the command prompt**_.

To accept Employee Pay Details and calculates the employee tax and print payslip on the CLI, run the following command from the same **target** folder:

```
java -jar EmpPayroll-1.0.0.war input
```

This will start up the application and wait for user input. Once all details are entered, the payslip will be generated on the console.

#### View all Employee Pay Information via Rest Client (e.g. via Postman client)
**_You can terminate the existing running application by using CRTL + C on the command prompt**_.

1. To view all employee pay details loaded from the json file, run the following command from the **target** folder:
```
java -Djson.input.home=<path-to-the-input-json-folder> -Djson.output.home=<path-to-the-output-json-folder> -jar EmpPayroll-1.0.0.war
```
2. Open the rest client using **GET** request (assuming the application runs on localhost and port 11222):
- URL: http://localhost:11222/EmpPayroll/employee/payInfo

Should return a JSON result of all employee pay details from the json file. 


#### Generate Employee Payslip based on the employee pay information entered (e.g. via Postman client)

1. Open the rest client set the following JSON input in the body as **PUT** (url: http://localhost:11222/EmpPayroll/employee/payslip):

```
e.g.
  {
      "firstName": "Allwyn",
      "lastName": "Dsouza",
      "payStartDate": "Mar-2015",
      "annualSalary": 100000,
      "superRate": 9
    }
 ```   

2. Should generate and return employee payslip as below:
 ``` 
{
  "employee": {
    "id": null,
    "firstName": "Jck",
    "lastName": "Tye",
    "paymentStartDate": "Mar-2016",
    "annualSalary": 100000,
    "superRate": 9,
    "payStartDate": "Mar-2016"
  },
  "payPeriod": {
    "start": "1 Mar",
    "end": "31 Mar"
  },
  "grossIncome": 8333,
  "incomeTax": 2079,
  "netIncome": 6254,
  "superAmount": 750
}
 ```  