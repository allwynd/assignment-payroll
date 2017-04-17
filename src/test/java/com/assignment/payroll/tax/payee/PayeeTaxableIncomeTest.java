package com.assignment.payroll.tax.payee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.payroll.tax.TaxCalculationException;
import com.assignment.payroll.tax.TaxData;
import com.assignment.payroll.tax.TaxableIncome;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayeeTaxableIncomeTest 
{
	@Autowired
	TaxableIncome payeeTaxableIncome;
	
	@Test
	public void shouldGetTaxableIncomeForLowerThresholdIncome() throws TaxCalculationException 
	{
		TaxData taxRange = null;
		taxRange = payeeTaxableIncome.getTaxableIncome(15_378.56D);
		
		assertNotNull(taxRange);
		assertEquals(Double.valueOf(0), taxRange.getAddition());
		assertEquals(Float.valueOf(0), taxRange.getAdditionCent());
		assertEquals(Double.valueOf(0), taxRange.getMin());
		assertEquals(Double.valueOf(18_200), taxRange.getMax());
	}

	@Test
	public void shouldGetTaxableIncomeForHigherThresholdIncome() throws TaxCalculationException
	{
		TaxData taxRange = null;
		taxRange = payeeTaxableIncome.getTaxableIncome(100_000.00D);

		assertNotNull(taxRange);
		assertEquals(Double.valueOf(17_547D), taxRange.getAddition());
		assertEquals(Float.valueOf(0.37F), taxRange.getAdditionCent());
		assertEquals(Double.valueOf(80_000D), taxRange.getMin());
		assertEquals(Double.valueOf(180_000D), taxRange.getMax());
	}	
	
	@Test
	public void shouldGetTaxableIncomeInBetweenThresholdIncome() throws TaxCalculationException
	{
		TaxData taxRange = null;
		taxRange = payeeTaxableIncome.getTaxableIncome(75000D);
		
		assertNotNull(taxRange);
		assertEquals(Double.valueOf(3_572D), taxRange.getAddition());
		assertEquals(Float.valueOf(0.325f), taxRange.getAdditionCent());
		assertEquals(Double.valueOf(37_000D), taxRange.getMin());
		assertEquals(Double.valueOf(80_000D), taxRange.getMax());
	}	
	
	@Test
	public void shouldGetTaxableIncomeForHighestThresholdIncome() throws TaxCalculationException
	{
		TaxData taxRange = null;
		taxRange = payeeTaxableIncome.getTaxableIncome(200_000D);

		assertNotNull(taxRange);
		assertEquals(Double.valueOf(54_547D), taxRange.getAddition());
		assertEquals(Float.valueOf(0.45F), taxRange.getAdditionCent());
		assertEquals(Double.valueOf(180_000D), taxRange.getMin());
		assertEquals(Double.valueOf(Double.MAX_VALUE), taxRange.getMax());
	}			
}
