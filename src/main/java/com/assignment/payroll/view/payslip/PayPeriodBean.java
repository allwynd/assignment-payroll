package com.assignment.payroll.view.payslip;


public class PayPeriodBean
{

	private final String start;
	private final String end;
	
	public PayPeriodBean(String start, String end) 
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

