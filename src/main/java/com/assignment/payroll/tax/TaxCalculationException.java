package  com.assignment.payroll.tax;

/**
 * Exception class for managing tax calculation errors.
 * 
 * @author allwyn.dsouza
 *
 */
public class TaxCalculationException extends Exception 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaxCalculationException(String message) 
	{
		super(message);
	}

}
