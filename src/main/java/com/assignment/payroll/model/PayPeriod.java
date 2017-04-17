package com.assignment.payroll.model;

public class PayPeriod
{
	private final String start;
	private final String end;
	
	public PayPeriod(String start, String end) 
	{
		
		this.start = start;
		this.end = end;
	}

	public String getStart() 
	{
		return start;
	}

	public String getEnd() 
	{
		return end;
	}
	
	
	
	
}
