package com.assignment.payroll.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.common.data.loader.DataLoader;
import com.assignment.common.data.loader.DataValidationException;
import com.assignment.common.template.Data;
import com.assignment.payroll.generator.PayslipGeneratorException;
import com.assignment.payroll.generator.utils.EmpPayValidatorUtils;
import com.assignment.payroll.model.Payslip;
import com.assignment.payroll.tax.TaxCalculationException;
import com.assignment.payroll.tax.TaxCalculator;
import com.assignment.payroll.view.EmployeeInfo;
import com.assignment.payroll.view.payslip.EmpPayslip;
import com.assignment.payroll.view.payslip.EmployeeBean;
import com.assignment.payroll.view.payslip.PayPeriodBean;


@Service("employeePayslip")
public class EmployeePayslipServiceImpl implements EmployeePayslipService {
	
	@Autowired
	DataLoader empPayJsonDataFileLoader;
	
	@Autowired
	TaxCalculator<Payslip, EmployeeBean> payeeTaxCalc;
	
	@Value(value = "classpath:input/json")
	private Resource jsonInputResource;

	@Override
	public List<EmployeeBean> retrieveEmployeesPay() throws DataLoadException, DataValidationException {
		return loadEmpPay();
	}
	
	@Override
	public EmpPayslip generatePayslip(EmployeeBean empBean)
			throws DataValidationException, TaxCalculationException, PayslipGeneratorException {
		
		// validate input
		EmpPayValidatorUtils.validateAnnualSalary(empBean.getAnnualSalary());
		EmpPayValidatorUtils.validateAnnualSalary(empBean.getSuperRate());

		if (empBean.getPaymentStartDate() == null)
			empBean.setPaymentStartDate(DateTime.now());
		
		// calculate payslip
		Payslip payslip = payeeTaxCalc.calculate(empBean);
		return getPayslipBean(payslip, empBean);
	}	
	

	@Override
	public List<EmpPayslip> generatePayslip(EmployeeInfo employeeInfo) throws DataValidationException, TaxCalculationException, PayslipGeneratorException {
		if (employeeInfo == null || employeeInfo.getEmployees().isEmpty())
			throw new PayslipGeneratorException("Cannot generate payslip as the given object is null or empty");
		
		List<EmpPayslip> payslips = new ArrayList<>();
		for (EmployeeBean empBean : employeeInfo.getEmployees())
		{
			payslips.add(generatePayslip(empBean));
		}
		
		return payslips;		
	}	
	
	private List<EmployeeBean> loadEmpPay() throws DataLoadException, DataValidationException {
		String jsonInputLoc = null;
		try {
			jsonInputLoc = System.getProperty("json.input.home", jsonInputResource.getURL().getPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Data<EmployeeInfo> empPayDetails = empPayJsonDataFileLoader.load(jsonInputLoc + File.separator + "employee-pay.json");
		return empPayDetails.getData().get(0).getEmployees();
	}
	
	private EmpPayslip getPayslipBean(Payslip payslip, EmployeeBean employee)
	{
		EmpPayslip empPayslip = new EmpPayslip();
		
		empPayslip.setEmployee(employee);
		empPayslip.setPayPeriod(new PayPeriodBean(payslip.getPayPeriod().getStart(), payslip.getPayPeriod().getEnd()));
		empPayslip.setGrossIncome(payslip.getGrossIncome().longValue());
		empPayslip.setIncomeTax(payslip.getIncomeTax().longValue());
		empPayslip.setNetIncome(payslip.getNetIncome().longValue());
		empPayslip.setSuperAmount(payslip.getSuperAmount().longValue());
		
		return empPayslip;
	}


}
