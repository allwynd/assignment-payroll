package com.assignment.payroll.generator.data.populator;

import static com.assignment.payroll.generator.utils.EmpPayValidatorUtils.DATE_MMM_YYYY_FORMAT;



import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.payroll.view.payslip.EmployeeBean;

/**
 * Implementation clas that populates the CSV file data to type {@link EmployeeBean}.
 * 
 * @author Allwyn
 *
 */
public class EmployeeCsvDataFilePopulator extends AbstractCSVFilePopulator<EmployeeBean>
{
	@Override
	protected EmployeeBean populateCsvData(String[] result) throws DataLoadException 
	{
		// populate data from into the emp bean
		return  new EmployeeBean(
			result[0], // first name
			result[1], // last name
			getPayStartDate(result[2]), 	// payment start date
			Double.valueOf(result[3]), 	// gross annual salary
			Float.valueOf(result[4]));	// super rate 
	}
	
	protected DateTime getPayStartDate(String date)
	{
		DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_MMM_YYYY_FORMAT);
		return DateTime.parse(date, formatter);
	}

}
