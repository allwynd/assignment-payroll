package com.assignment.payroll.tax.payee;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.assignment.payroll.tax.TaxCalculationException;
import com.assignment.payroll.tax.TaxData;
import com.assignment.payroll.tax.TaxableIncome;

/**
 * 
 * @author Allwyn
 *
 */
@Component("payeeTaxableIncome")
public class PayeeTaxableIncome implements TaxableIncome 
{
	/** Mapping stores taxable income - Key holds the highest gross income and Tax data as value.  **/
	private static final NavigableMap<Double, TaxData> taxableIncome = new TreeMap<Double, TaxData>();
	static
	{
		taxableIncome.put(18_200D, new TaxData(18_200D, 0D, 0.00F, 0D));
		taxableIncome.put(37_000D, new TaxData(37_000D, 18_200D, 0.19F, 0D));
		taxableIncome.put(80_000D, new TaxData(80_000D, 37_000D, 0.325F, 3_572D));
		taxableIncome.put(180_000D, new TaxData(180_000D, 80_000D, 0.37F, 17_547D));
		taxableIncome.put(Double.MAX_VALUE, new TaxData(Double.MAX_VALUE, 180_000D, 0.45F, 54_547D));
	}
	
	@Override
	public TaxData getTaxableIncome(Double amount) throws TaxCalculationException
	{
		TaxData taxableRange = null;
		Map.Entry<Double,TaxData> entry = taxableIncome.ceilingEntry(amount);
		if (entry == null) 
		{
		    throw new TaxCalculationException("The given value is to low. Cannot get a taxable value.");
		} 
		else if (amount <= entry.getValue().getMax()) 
		{
			taxableRange = entry.getValue();
		} 
		else 
		{
			throw new TaxCalculationException("The given value is too high. Cannot get a taxable value.");
		}
		
		return taxableRange;
	}

}
