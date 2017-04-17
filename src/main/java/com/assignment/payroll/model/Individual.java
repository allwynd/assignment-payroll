package com.assignment.payroll.model;

import org.joda.time.DateTime;

public class Individual extends Entity
{
	protected final String firstName;
	protected final String lastName;
	protected final DateTime paymentStartDate;
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param paymentStartDate
	 */
	public Individual(String firstName, String lastName, DateTime paymentStartDate) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.paymentStartDate = paymentStartDate;
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

}
