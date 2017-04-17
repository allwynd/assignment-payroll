package com.assignment.payroll.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.common.data.loader.DataValidationException;
import com.assignment.payroll.generator.PayslipGeneratorException;
import com.assignment.payroll.model.Payslip;
import com.assignment.payroll.tax.TaxCalculationException;
import com.assignment.payroll.tax.TaxCalculator;
import com.assignment.payroll.view.EmployeeInfo;
import com.assignment.payroll.view.payslip.EmpPayslip;
import com.assignment.payroll.view.payslip.EmployeeBean;

/**
 * 
 * @author allwyn.dsouza
 * Test class for {@link EmployeePayslipService} implementation.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeePayslipServiceTest {

	@Autowired
	EmployeePayslipService employeePayslip;
	
	@Autowired
	TaxCalculator<Payslip, EmployeeBean> payeeTaxCalc;
	
	@Test
	public void shouldRetrieveAllEmployeesPayInfo() throws DataLoadException, DataValidationException
	{
		List<EmployeeBean> empPayList = employeePayslip.retrieveEmployeesPay();
		for (EmployeeBean empPay : empPayList) 
		{
			assertThat(empPay, is(notNullValue()));
			assertThat(empPay.getAnnualSalary(), is(notNullValue()));
			assertThat(empPay.getSuperRate(), is(notNullValue()));
		}
	}

	@Test	
	public void shouldGenerateAllEmpPayslips() throws TaxCalculationException, DataLoadException, DataValidationException, PayslipGeneratorException 
	{
		EmployeeInfo empInfo = new EmployeeInfo();		
		
		EmployeeBean empBean1 = new EmployeeBean();
		empBean1.setAnnualSalary(Double.valueOf(100000));
		empBean1.setSuperRate(Float.valueOf(6));
		empInfo.addEmployee(empBean1);
				
		EmployeeBean empBean2 = new EmployeeBean();
		empBean2.setAnnualSalary(Double.valueOf(200000));
		empBean2.setSuperRate(Float.valueOf(12));
		empInfo.addEmployee(empBean2);		

		List<EmpPayslip> empPayslips = employeePayslip.generatePayslip(empInfo);
		
		// Assert that empPayslips is generated successfully
		for (EmpPayslip empPayslip : empPayslips) 
			assertEmployeePayslipGenerated(empPayslip);	
	}	
	
	@Test
	public void shouldGeneratePayslipBasedEmpPayInfo() throws TaxCalculationException, DataLoadException, DataValidationException, PayslipGeneratorException 
	{
		EmployeeBean empBean = new EmployeeBean();
		empBean.setAnnualSalary(Double.valueOf(100000));
		empBean.setSuperRate(Float.valueOf(6));
		EmpPayslip empPayslip = employeePayslip.generatePayslip(empBean);
		
		// Assert that empPayslip is generated successfully
		assertEmployeePayslipGenerated(empPayslip);	
	}
	
	private void assertEmployeePayslipGenerated(EmpPayslip empPayslip) 
	{
		assertThat(empPayslip, 					is(notNullValue()));	
		assertThat(empPayslip.getEmployee(), 	is(notNullValue()));	
		assertThat(empPayslip.getGrossIncome(), is(notNullValue()));
		assertThat(empPayslip.getPayPeriod(), 	is(notNullValue()));
		assertThat(empPayslip.getIncomeTax(), 	is(notNullValue()));
		assertThat(empPayslip.getNetIncome(), 	is(notNullValue()));
		assertThat(empPayslip.getSuperAmount(), is(notNullValue()));
	}
	
}
