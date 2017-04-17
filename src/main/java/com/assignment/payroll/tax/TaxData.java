package  com.assignment.payroll.tax;

/**
 * Tax holder for taxable income.
 * 
 * @author Allwyn
 *
 */
public final class TaxData 
{
	private final Double max;
	private final Double min;
	private final Float additionCent;
	private final Double addition;
	
	/**
	 * 
	 * @param max Maximum income threshold
	 * @param min Mininum income threshold
	 * @param additionCent addition cents
	 * @param addition	addition amount
	 */
	public TaxData(Double max, Double min, Float additionCent, Double addition) 
	{
		this.max = max;
		this.min = min;
		this.additionCent = additionCent;
		this.addition = addition;
	}

	public Double getMax() {
		return max;
	}	

	public Double getMin() {
		return min;
	}

	public Float getAdditionCent() {
		return additionCent;
	}

	public Double getAddition() {
		return addition;
	}

}
