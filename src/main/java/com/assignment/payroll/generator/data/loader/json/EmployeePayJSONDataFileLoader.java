package com.assignment.payroll.generator.data.loader.json;

import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.common.data.loader.DataValidationException;
import com.assignment.common.template.Data;
import com.assignment.payroll.generator.data.populator.EmployeeJsonFilePopulator;
import com.assignment.payroll.generator.utils.EmpPayValidatorUtils;
import com.assignment.payroll.view.EmployeeInfo;
import com.assignment.payroll.view.payslip.EmployeeBean;

@Component("empPayJsonDataFileLoader")
public class EmployeePayJSONDataFileLoader extends AbstractJSONDataFileLoader
{

	@SuppressWarnings("unchecked")
	@Override
	public Data<EmployeeInfo> load(Object input) throws DataLoadException, DataValidationException 
	{
		// load json data from the given json file and class to populate the loaded data
		return super.getJsonData(
			input, 
			new EmployeeJsonFilePopulator());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void validate(Object result) throws DataValidationException 
	{
		Data<EmployeeInfo> jsondata = (Data<EmployeeInfo>) result;
		
		if (jsondata.getData().isEmpty() || jsondata.getData().get(0) == null)
		{
			throw new DataValidationException("No data found for employees");
		}
		
		List<EmployeeBean> employees = jsondata.getData().get(0).getEmployees();
		long rowCtr = 1;
		for (EmployeeBean emp : employees)
		{
			emp.setId(rowCtr);
			try
			{				
				// validate paymentStartDate
				// expected paymentStartDate in the given format
				DateTimeFormatter formatter = DateTimeFormat.forPattern(EmpPayValidatorUtils.DATE_MMM_YYYY_FORMAT);
				formatter.print(emp.getPaymentStartDate());
				//EmpPayValidatorUtils.validatePaymentStartDate(emp.getPaymentStartDate());
				
				// validate annual salary
				EmpPayValidatorUtils.validateAnnualSalary(emp.getAnnualSalary());
				
				// validate super rate
				EmpPayValidatorUtils.validateSuperRate(emp.getSuperRate());
			}
			catch (DataValidationException e)
			{
				throw new DataValidationException("Row: " + rowCtr, e);
			}
			rowCtr++;
		}

		
	}



}
