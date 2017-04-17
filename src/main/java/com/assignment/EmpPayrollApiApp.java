package com.assignment;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.common.data.loader.DataLoader;
import com.assignment.common.data.loader.DataValidationException;
import com.assignment.common.template.Data;
import com.assignment.payroll.generator.PayslipGeneratorException;
import com.assignment.payroll.generator.render.PayslipRenderer;
import com.assignment.payroll.generator.render.PayslipRendererException;
import com.assignment.payroll.generator.render.RenderType;
import com.assignment.payroll.generator.render.UnsupportedRenditionException;
import com.assignment.payroll.generator.utils.EmpPayValidatorUtils;
import com.assignment.payroll.service.EmployeePayslipService;
import com.assignment.payroll.tax.TaxCalculationException;
import com.assignment.payroll.view.EmployeeInfo;
import com.assignment.payroll.view.payslip.EmpPayslip;
import com.assignment.payroll.view.payslip.EmployeeBean;


@SpringBootApplication(scanBasePackages={"com.assignment.payroll"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class EmpPayrollApiApp extends SpringBootServletInitializer {
	
	private static Logger logger = LoggerFactory.getLogger(EmpPayrollApiApp.class);
	
	@Autowired
	EmployeePayslipService employeePayslip;
	
	@Autowired
	PayslipRenderer employeePayslipRenderer;
	
	@Autowired
	DataLoader empPayJsonDataFileLoader;
	
	@Value(value = "classpath:input/json")
	private Resource jsonInputResource;
	
	@Bean
	   public static PropertySourcesPlaceholderConfigurer
	     propertySourcesPlaceholderConfigurer() {
	      return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Component
	public class GenerateEmpPayrollApp implements CommandLineRunner {

	    public void run(String... args) throws 
	    	DataLoadException, DataValidationException, UnsupportedRenditionException, 
	    	TaxCalculationException, PayslipGeneratorException, PayslipRendererException {
	    	
	    	logger.info("** In {} Class.", GenerateEmpPayrollApp.class.getSimpleName());	    	
			
	    	if (args.length == 0)			
				loadInputJsonFileAndGenerateOutputJsonToFile();
			else if (args.length > 1)
			{
				logger.error("***$$$$$$*** Invalid number of arguments. Expected zero or one parameter: [INPUT] ***$$$$$$***");
				System.exit(0);
			}
			else
			{
				// If Valid parameter
				if (!StringUtils.equalsIgnoreCase("INPUT", args[0]))	
				{
					logger.error("***$$$$$$*** Invalid Parameter. Expected: [INPUT] ***$$$$$$***");
					System.exit(0);			
				}
				acceptUserInput();
			}			
			//System.exit(0);
	    }

	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EmpPayrollApiApp.class);
    }	

	public static void main(String[] args) throws 
		DataLoadException, DataValidationException, UnsupportedRenditionException, TaxCalculationException, PayslipGeneratorException {
		SpringApplication.run(EmpPayrollApiApp.class, args);
	}
	
	private void acceptUserInput() throws 
		DataValidationException, TaxCalculationException, PayslipGeneratorException, PayslipRendererException, UnsupportedRenditionException 
	{
		// Accept user input: First Name, Last Name, annual salary(positive integer) 
		// and super rate(0% - 50% inclusive), payment start date.
		Scanner reader = new Scanner(System.in);
		logger.info("******* Employee Pay Information - User Input **********\n");
		
		// Accept user input and store the validated input in the emp bean
		EmployeeBean employeeBean = new EmployeeBean(
				acceptFirstName(reader),
				acceptLastName(reader),
				acceptPayStartDate(reader),
				acceptAnnualSalary(reader),
				acceptSuperRate(reader));	
			
		// close reader input
		reader.close();
		
		// Generate and render Employee Payslip - (to console by default)
		logger.info("Calculate Tax and generation payslip ...");
		EmpPayslip payslip = employeePayslip.generatePayslip(employeeBean);
		employeePayslipRenderer.render(Arrays.asList(payslip), RenderType.CONSOLE);
	}
	
	private void loadInputJsonFileAndGenerateOutputJsonToFile() throws 
		DataLoadException, DataValidationException, PayslipRendererException, UnsupportedRenditionException, 
		TaxCalculationException, PayslipGeneratorException 
	{
		String inputJsonFile;
		try {
			String inputJsonFilePath = System.getProperty("json.input.home", jsonInputResource.getURL().getPath());
			inputJsonFile = inputJsonFilePath + "/employee-pay.json";
			logger.info("Input Json File Path {}", inputJsonFile);
		} catch (IOException e) {
			throw new DataLoadException("Cannot load internal Employee Pay Resource file.");
		}
		
		
		//final String inputJsonFile = "/input/json/employee-pay.json";
		logger.info("Loading Employee Pay Data from Input file {}", inputJsonFile);		
		Data<EmployeeInfo> empPayDetails = empPayJsonDataFileLoader.load(inputJsonFile);		
		logger.info("+++====+++ Calculate Tax and generation payslips +++====++++");	
		List<EmpPayslip> payslips = employeePayslip.generatePayslip(empPayDetails.getData().iterator().next());
		employeePayslipRenderer.render(payslips, RenderType.JSON_FILE);
	}		

	private static String acceptFirstName(Scanner reader)
	{
		logger.info("Enter First Name:");
		String firstname = reader.nextLine(); 
		return firstname;
	}

	private static String acceptLastName(Scanner reader)
	{
		logger.info("Enter Last Name:");
		String lastname = reader.nextLine(); 
		return lastname;
	}
	
	private static DateTime acceptPayStartDate(Scanner reader) throws DataValidationException 
	{
		DateTime payStartDate = null;
		boolean validInput = false;		
		
		do {
			logger.info("Enter Payment Start Date (e.g. Feb-2017). If left blank, assumes current Month:");
			try	{				
				String value = reader.nextLine();
				if (StringUtils.isEmpty(value)) break;
				
				// validate date if entered
				payStartDate = EmpPayValidatorUtils.validatePaymentStartDate(value);
				validInput= true;
			} catch (DataValidationException e)	{
				logger.error("Input Validation Error - Payment Start Date", e.getMessage());
			}
		} while(!validInput);

		return payStartDate;
	}

	private static Double acceptAnnualSalary(Scanner reader) 
	{
		Double annualSal = null;
		boolean validInput = false;
		do
		{
			logger.info("Enter Annual Salary:");
			try	{
				annualSal = EmpPayValidatorUtils.validateAnnualSalary(reader.nextLine());
				validInput= true;
			} catch (DataValidationException e)	{
				logger.error("Input Validation Error - Annual Salary", e.getMessage());
			}
		} while(!validInput);
		
		return annualSal;
	}

	private static Float acceptSuperRate(Scanner reader) 
	{
		Float superRate = null;
		boolean validInput = false;
		do
		{
			logger.error("Enter Super Rate (between 0 - 50 incl.):");
			try
			{
				superRate = EmpPayValidatorUtils.validateSuperRate(reader.nextLine());
				validInput= true;
			} catch (DataValidationException e)	{
				logger.error("Input Validation Error - Super Rate", e.getMessage());
			}
		} while(!validInput);		
		
		return superRate;
	}


}
