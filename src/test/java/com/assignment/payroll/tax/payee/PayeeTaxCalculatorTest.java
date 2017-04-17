package com.assignment.payroll.tax.payee;

import static com.assignment.payroll.generator.utils.EmpPayValidatorUtils.DATE_MMM_YYYY_FORMAT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.payroll.model.Employee;
import com.assignment.payroll.model.PayPeriod;
import com.assignment.payroll.model.Payslip;
import com.assignment.payroll.tax.TaxCalculationException;
import com.assignment.payroll.tax.TaxCalculator;
import com.assignment.payroll.view.payslip.EmployeeBean;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayeeTaxCalculatorTest 
{
	@Autowired
	TaxCalculator<Payslip, EmployeeBean> payeeTaxCalc;
	
	@Test
	public void shouldCalculatePayeeIncomeTaxForLowerIncome() throws TaxCalculationException
	{
		// Set paystart date - March-2016
		DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_MMM_YYYY_FORMAT);
		DateTime payStartDate = DateTime.parse("March-2016", formatter);
		
		// Input
		EmployeeBean employeeBean = new EmployeeBean(
				"David", "Rudd", payStartDate, Double.valueOf(60050), Float.valueOf(9));
		
		// Expected: Payslip:
		Payslip expectedPayslip = new Payslip();
		Employee employee = new Employee();
		employee.setFirstName(employeeBean.getFirstName());
		employee.setLastName(employeeBean.getLastName());
		expectedPayslip.setEmployee(employee);
		expectedPayslip.setPayPeriod(new PayPeriod("1 Mar", "31 Mar"));
		expectedPayslip.setGrossIncome(BigDecimal.valueOf(5004));
		expectedPayslip.setIncomeTax(BigDecimal.valueOf(922));
		expectedPayslip.setNetIncome(BigDecimal.valueOf(4082));
		expectedPayslip.setSuperAmount(BigDecimal.valueOf(450));
		
		// Calculate employee - payslip
		Payslip actualPayslip = payeeTaxCalc.calculate(employeeBean);
		assertEmployeePayslip(expectedPayslip, actualPayslip);		
	}
	
	@Test
	public void shouldCalculatePayeeIncomeTaxForHigherIncome() throws TaxCalculationException
	{		
		// Set paystart date - March-2016
		DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_MMM_YYYY_FORMAT);
		DateTime payStartDate = DateTime.parse("Mar-2016", formatter);
		
		// Input
		EmployeeBean employeeBean = new EmployeeBean(
				"Ryan", "Chen", payStartDate, Double.valueOf(120_000), Float.valueOf(10));
		
		// Expected: Payslip:
		Payslip expectedPayslip = new Payslip();
		Employee employee = new Employee();
		employee.setFirstName(employeeBean.getFirstName());
		employee.setLastName(employeeBean.getLastName());
		expectedPayslip.setEmployee(employee);
		expectedPayslip.setPayPeriod(new PayPeriod("1 Mar", "31 Mar"));
		expectedPayslip.setGrossIncome(BigDecimal.valueOf(10_000));
		expectedPayslip.setIncomeTax(BigDecimal.valueOf(2696));
		expectedPayslip.setNetIncome(BigDecimal.valueOf(7304));
		expectedPayslip.setSuperAmount(BigDecimal.valueOf(1000));
		
		// Calculate employee - payslip
		Payslip actualPayslip = payeeTaxCalc.calculate(employeeBean);		
		assertEmployeePayslip(expectedPayslip, actualPayslip);	
	}
	
	private void assertEmployeePayslip(Payslip expectedPayslip, Payslip actualPayslip) 
	{
		assertThat(actualPayslip, 								is(notNullValue()));	
		assertThat(actualPayslip.getEmployee().getFirstName(),	equalTo(expectedPayslip.getEmployee().getFirstName()));	
		assertThat(actualPayslip.getEmployee().getLastName(),	equalTo(expectedPayslip.getEmployee().getLastName()));	
		assertThat(actualPayslip.getPayPeriod().getStart(),		equalTo(expectedPayslip.getPayPeriod().getStart()));	
		assertThat(actualPayslip.getPayPeriod().getEnd(),		equalTo(expectedPayslip.getPayPeriod().getEnd()));
		assertThat(actualPayslip.getGrossIncome(),				equalTo(expectedPayslip.getGrossIncome()));	
		assertThat(actualPayslip.getIncomeTax(),				equalTo(expectedPayslip.getIncomeTax()));
		assertThat(actualPayslip.getNetIncome(),				equalTo(expectedPayslip.getNetIncome()));
		assertThat(actualPayslip.getSuperAmount(),				equalTo(expectedPayslip.getSuperAmount()));

	}}
