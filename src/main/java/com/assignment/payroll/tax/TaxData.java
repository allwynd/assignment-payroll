package  com.assignment.payroll.tax;

/**
 * @author Allwyn
 *
 */
public final class TaxData 
{
	private final Double max;
	private final Double min;
	private final Float additionCent;
	private final Double addition;
	
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
