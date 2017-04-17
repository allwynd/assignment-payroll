package com.assignment.payroll.generator;

public class PayslipGeneratorException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3114632059630991258L;

	/**
	 * default constructor
	 */
	public PayslipGeneratorException() 
	{
	}

	/**
	 * 
	 * @param message
	 */
	public PayslipGeneratorException(String message) 
	{
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public PayslipGeneratorException(Throwable cause) 
	{
		super(cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public PayslipGeneratorException(String message, Throwable cause) 
	{
		super(message, cause);
	}	
	
}
