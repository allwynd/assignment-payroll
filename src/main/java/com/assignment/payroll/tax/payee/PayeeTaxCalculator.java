package com.assignment.payroll.tax.payee;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.assignment.payroll.model.Employee;
import com.assignment.payroll.model.PayPeriod;
import com.assignment.payroll.model.Payslip;
import com.assignment.payroll.tax.TaxCalculator;
import com.assignment.payroll.tax.TaxData;
import com.assignment.payroll.tax.TaxableIncome;
import com.assignment.payroll.tax.TaxCalculationException;
import com.assignment.payroll.view.payslip.EmployeeBean;

/**
 * Class to calculate employee payee income.
 *  
 * @author Allwyn
 *
 */
@Component("payeeTaxCalc")
public class PayeeTaxCalculator implements TaxCalculator<Payslip, EmployeeBean>
{

	@Override
	public Payslip calculate(EmployeeBean employeeBean) throws TaxCalculationException 
	{
		Payslip payslip = new Payslip();
		
		// Set display - pay period
		PayPeriod payPeriod = new PayPeriod(
				getDisplayDate(employeeBean.getPaymentStartDate(), true),
				getDisplayDate(employeeBean.getPaymentStartDate(), false));
		payslip.setPayPeriod(payPeriod);
		
		// gross Mthly income
		BigDecimal grossMthlyIncome = BigDecimal.valueOf(employeeBean.getAnnualSalary())
				.divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP);
		payslip.setGrossIncome(grossMthlyIncome);
		
		// Income Tax Amount
		BigDecimal incomeTax  = getCalculatedIncomeTaxAmount(
				employeeBean.getAnnualSalary(), employeeBean.getAnnualSalary());
		payslip.setIncomeTax(incomeTax);
		
		// net income
		BigDecimal netIncome = grossMthlyIncome.subtract(incomeTax);
		payslip.setNetIncome(netIncome);
		
		// super (gross mthly income * super rate (%) 
		BigDecimal superValue = grossMthlyIncome
				.multiply(BigDecimal.valueOf(employeeBean.getSuperRate()))
					.divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP);
		payslip.setSuperAmount(superValue);

		// employee info 
		Employee employee = new Employee();
		employee.setFirstName(employeeBean.getFirstName());
		employee.setLastName(employeeBean.getLastName());
		payslip.setEmployee(employee);
		
		return payslip;
	}
	
	protected BigDecimal getCalculatedIncomeTaxAmount(Double grossIncome, Double annualSalary) throws TaxCalculationException
	{
		// calculate income tax
		TaxableIncome taxableIncome = new PayeeTaxableIncome();
		TaxData taxData = taxableIncome.getTaxableIncome(annualSalary);
		
		// (Addition + (Gross Amt - Min Amount) * addCent) / 12 and round up
		return BigDecimal.valueOf(taxData.getAddition()).add(
				BigDecimal.valueOf(grossIncome).subtract(BigDecimal.valueOf(taxData.getMin()))
					.multiply(BigDecimal.valueOf(taxData.getAdditionCent())))
		.divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP);	
	}
	
	private String getDisplayDate(DateTime date, boolean start)
	{
		String displayDate;
		if (start)
		{
			displayDate =  date.dayOfMonth().getMinimumValue() + " " + date.toString("MMM");
		}
		else
		{
			displayDate =  date.dayOfMonth().getMaximumValue() + " " + date.toString("MMM");
		}
		
		return displayDate;
	}


}
