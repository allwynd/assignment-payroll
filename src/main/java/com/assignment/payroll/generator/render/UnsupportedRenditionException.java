package  com.assignment.payroll.generator.render;

public class UnsupportedRenditionException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2880468041083554078L;

	/**
	 * default constructor
	 */
	public UnsupportedRenditionException() 
	{
	}

	/**
	 * 
	 * @param message
	 */
	public UnsupportedRenditionException(String message) 
	{
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public UnsupportedRenditionException(Throwable cause) 
	{
		super(cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public UnsupportedRenditionException(String message, Throwable cause) 
	{
		super(message, cause);
	}	
}
