package com.assignment.payroll.view.payslip;

import com.assignment.payroll.generator.utils.CustomDateTimeSerializer;
import com.assignment.payroll.view.ViewBean;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.joda.time.DateTime;
/**
 * View representing an employee.
 * 
 * @author Allwyn
 *
 */

public class EmployeeBean extends ViewBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3278155213957508504L;
	
	private String firstName;
	private String lastName;
	
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	private DateTime paymentStartDate;
	private Double annualSalary;
	private Float superRate;
	
	public EmployeeBean()
	{
		
	}
	
	public EmployeeBean(String firstName, String lastName, DateTime paymentStartDate, Double annualSalary,
			Float superRate) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.paymentStartDate = paymentStartDate;
		this.annualSalary = annualSalary;
		this.superRate = superRate;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPaymentStartDate(DateTime paymentStartDate) {
		this.paymentStartDate = paymentStartDate;
	}

	public void setAnnualSalary(Double annualSalary) {
		this.annualSalary = annualSalary;
	}

	public void setSuperRate(Float superRate) {
		this.superRate = superRate;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}


	public DateTime getPaymentStartDate() 
	{
		return paymentStartDate;
	}	

	public Double getAnnualSalary() 
	{
		return annualSalary;
	}

	public Float getSuperRate() 
	{
		return superRate;
	}
		
}
