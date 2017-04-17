package com.assignment.payroll.generator.render;

public class PayslipRendererException extends Exception 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3027779385317279161L;

	/**
	 * default constructor
	 */
	public PayslipRendererException() 
	{
	}

	/**
	 * 
	 * @param message
	 */
	public PayslipRendererException(String message) 
	{
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public PayslipRendererException(Throwable cause) 
	{
		super(cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public PayslipRendererException(String message, Throwable cause) 
	{
		super(message, cause);
	}	
	
}
