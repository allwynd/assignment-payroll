package com.assignment.common.data.loader;

/**
 * Common exception class to capture all data loading exception.
 * 
 * @author allwyn.dsouza
 *
 */
public class DataLoadException extends Exception 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1275550206609929480L;

	/**
	 * default constructor
	 */
	public DataLoadException() 
	{
	}

	/**
	 * 
	 * @param message
	 */
	public DataLoadException(String message) 
	{
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public DataLoadException(Throwable cause) 
	{
		super(cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public DataLoadException(String message, Throwable cause) 
	{
		super(message, cause);
	}	
}
