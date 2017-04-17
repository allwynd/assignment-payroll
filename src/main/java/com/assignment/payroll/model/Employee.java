package com.assignment.payroll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Data model class that represent a employee.
 * 
 * @author Allwyn
 *
 */
public class Employee extends Entity 
{
	protected String firstName;
	protected String lastName;
	protected Payslip payslip;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonIgnore
	public Payslip getPayslip() {
		return payslip;
	}

	public void setPayslip(Payslip payslip) {
		this.payslip = payslip;
	}

}
