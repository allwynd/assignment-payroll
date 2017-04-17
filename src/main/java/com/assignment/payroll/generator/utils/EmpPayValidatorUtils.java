package com.assignment.payroll.generator.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.assignment.common.data.loader.DataValidationException;

/**
 * Validator utility class for employee pay.
 * 
 * @author Allwyn
 *
 */
public final class EmpPayValidatorUtils 
{
	public static final String DATE_MMM_YYYY_FORMAT = "MMM-YYYY";
	
	public static Float validateSuperRate(Object superRateValue) throws DataValidationException
	{
		Float superRate = null;
		try
		{
			// expected super rate - integer value between 0% - 50$ incl.
			superRate = Float.parseFloat(superRateValue.toString());
		}
		catch (Exception e)
		{
			throw new DataValidationException("Invalid Super Rate - Expected numeric between 0 and 50 incl.", e);
		}
		
		// check rate range
		if (superRate < 0 || superRate > 50)
			throw new DataValidationException("Invalid Super Rate - Expected: between 0 and 50 incl.");
		
		return superRate;
	}
	
	public static DateTime validatePaymentStartDate(String payStartDateValue) throws DataValidationException
	{
		DateTime payStartDate = null;
		try
		{
			// expected paymentStartDate in the given format
			DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_MMM_YYYY_FORMAT);
			payStartDate = DateTime.parse(payStartDateValue, formatter);
		}
		catch (Exception e)
		{
			throw new DataValidationException("Invalid Payment Start Date - Expected format. [Mon-Year] ", e);
		}
		
		return payStartDate;
	}
	
	public static Double validateAnnualSalary(Object annualSalaryStr) throws DataValidationException
	{
		Double value = null;
		try
		{
			value = Double.parseDouble(annualSalaryStr.toString());
			if (value < 0)
				throw new DataValidationException("Invalid Annual Salary - Expected positive number ");
		}
		catch (Exception e)
		{
			throw new DataValidationException("Invalid Annual Salary - Expected positive number ", e);
		}
		
		return value;
	}
	
/*	public static void validateIfEmpty(String value) throws DataValidationException
	{
		if (StringUtils.isEmpty(value)) 
			throw new DataValidationException("Value cannot be empty!");
	}*/
	
}
