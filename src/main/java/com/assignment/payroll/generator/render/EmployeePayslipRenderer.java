package com.assignment.payroll.generator.render;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.assignment.payroll.generator.utils.CustomDateTimeModule;
import com.assignment.payroll.view.payslip.EmpPayslip;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class responsible to generate employee payslip based on the {@link RenderType} types.
 * 
 * @author Allwyn
 *
 */
@Component("employeePayslipRenderer")
public class EmployeePayslipRenderer implements PayslipRenderer
{	
	private static Logger logger = LoggerFactory.getLogger(EmployeePayslipRenderer.class);

	@Value(value = "classpath:output/json")
	private Resource jsonOutputResource;
	
	@Override
	public void render(List<EmpPayslip> payslips, RenderType renderType) throws UnsupportedRenditionException, PayslipRendererException {
		switch (renderType) 
		{
			case CONSOLE:
				renderOutputToConsole(payslips);
				break;
			case JSON_FILE:
				renderOutputToJsonFile(payslips);
				break;				
			default:
				throw new UnsupportedRenditionException("Unsupported Render Type: " + renderType);
		}
	}
	
	public void renderOutputToConsole(List<EmpPayslip> payslips) throws PayslipRendererException
	{
		if (payslips == null || payslips.isEmpty())
			throw new PayslipRendererException("Cannot Render Employee Payslip(s)as object is null or empty.");
		
		for (EmpPayslip payslip : payslips) 
		{
			logger.info("Pay Period [{} - {}]", payslip.getPayPeriod().getStart(), payslip.getPayPeriod().getEnd());
			logger.info("\tName: \t{} {}", payslip.getEmployee().getFirstName(), payslip.getEmployee().getLastName());
			logger.info("\tGross Income: \t{}", payslip.getGrossIncome());
			logger.info("\tIncome Tax: {}", payslip.getIncomeTax());
			logger.info("\tNet Income: \t{}", payslip.getNetIncome());
			logger.info("\tSuper Amount: \t{}", payslip.getSuperAmount());
			logger.info("\n");
		}	
	}
	
	public void renderOutputToJsonFile(List<EmpPayslip> payslips) throws PayslipRendererException 
	{
		if (payslips == null || payslips.isEmpty())
			throw new PayslipRendererException("Cannot Render Employee Payslip(s)as object is null or empty.");
		
		// generate output based on the given render type
		logger.info("===******=== Rendering Payslip to File as JSON ===******==="); 
		
		//final String location = "./output/json/";
		String outputLoc = null;
		try {
			outputLoc = System.getProperty("json.output.home", jsonOutputResource.getURL().getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final String fileName = EmpPayslip.class.getSimpleName().toLowerCase();		
		writeObjectToJsonFile(payslips, outputLoc, fileName);	
	}

	private static <T> void writeObjectToJsonFile(T objectToWrite, String location, String fname) throws PayslipRendererException 
	{
		FileWriter fw = null;
		BufferedWriter bw = null;		
		
		try {
			// convert to json message
			File file = new File(location + File.separator, fname + ".json");
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			ObjectMapper mapper = new ObjectMapper();			
			mapper.registerModule(new CustomDateTimeModule());
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.writer().withoutAttribute("payStartDate");
			mapper.writeValue(bw, objectToWrite);
			logger.info("*** Generated OUTPUT to: {}.json", location + File.separator + fname);
		} 
		catch (Exception e) 
		{
			throw new PayslipRendererException("Error while rendering output to JSON file", e);
		} 
		finally 
		{
			if (bw != null)
			try 
			{
				fw.close();
			} 
			catch (IOException e) {}
		}		
	}
	
}
