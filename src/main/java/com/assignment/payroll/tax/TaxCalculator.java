package  com.assignment.payroll.tax;

/**
 * Interface that encapsulates the algorithm for calculating tax amount.
 * 
 * @author Allwyn
 * 
 * @param <O> The output object.
 * @param <I> The input object
 *
 */
public interface TaxCalculator<O, I>
{
	/**
	 * 
	 * @param input The input objecct
	 * @return O output
	 * @throws TaxCalculationException
	 */
	O calculate(I input) throws TaxCalculationException;

}
