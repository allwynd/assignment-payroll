package com.assignment.common.data.loader;

/**
 * Validation exception class that can be thrown for bad data.
 * 
 * @author allwyn.dsouza
 *
 */
public class DataValidationException extends Exception 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1275550206609929480L;

	/**
	 * default constructor
	 */
	public DataValidationException() 
	{
	}

	/**
	 * 
	 * @param message
	 */
	public DataValidationException(String message) 
	{
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public DataValidationException(Throwable cause) 
	{
		super(cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public DataValidationException(String message, Throwable cause) 
	{
		super(message, cause);
	}	
}
