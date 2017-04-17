package com.assignment.payroll.service;

import java.util.List;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.common.data.loader.DataValidationException;
import com.assignment.payroll.generator.PayslipGeneratorException;
import com.assignment.payroll.tax.TaxCalculationException;
import com.assignment.payroll.view.EmployeeInfo;
import com.assignment.payroll.view.payslip.EmpPayslip;
import com.assignment.payroll.view.payslip.EmployeeBean;

/**
 * Business interface to generate employee payslip.
 * 
 * @author allwyn.dsouza
 *
 */
public interface EmployeePayslipService 
{

	/**
	 * Retrieves all employee pay details.
	 * 
	 * @return
	 */
	List<EmployeeBean> retrieveEmployeesPay() throws DataLoadException, DataValidationException;
	
	/**
	 * GeneratePayslip based on employee pay details
	 * @param empBean
	 * @return
	 * @throws TaxCalculationException
	 * @throws PayslipGeneratorException
	 */
	EmpPayslip generatePayslip(EmployeeBean empBean) throws DataValidationException, TaxCalculationException, PayslipGeneratorException;

	/**
	 * Generates a list of employee pay wrapped in the employee info object.
	 * 
	 * @param employeeInfo A wrapper to the employee pay list
	 * @return
	 * @throws DataValidationException
	 * @throws TaxCalculationException
	 * @throws PayslipGeneratorException
	 */
	public List<EmpPayslip> generatePayslip(EmployeeInfo employeeInfo) throws DataValidationException, TaxCalculationException, PayslipGeneratorException;	
}
