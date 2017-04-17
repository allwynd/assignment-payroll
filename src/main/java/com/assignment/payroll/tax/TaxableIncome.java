package  com.assignment.payroll.tax;

/**
 * 
 * @author Allwyn
 *
 */
public interface TaxableIncome 
{
	/**
	 * 
	 * Specifies the various tax types.
	 *
	 */
	enum TaxType
	{
		EMPLOYMENT;
	}
	
	
	/**
	 * Gets the taxable income for the given amount.
	 * @param amount The given amount.
	 * @return TaxRange
	 * @throws TaxCalculationException Can be thrown if the given <code>amount</code> does not match the tax range.
	 */
	TaxData getTaxableIncome(Double amount) throws TaxCalculationException;
}
