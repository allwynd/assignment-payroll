package com.assignment.payroll.view.payslip;

/**
 * 
 * @author t823201
 *
 */
public class EmpPayslip {

	private EmployeeBean employee;
	private PayPeriodBean payPeriod;
	private Long grossIncome;
	private Long incomeTax;
	private Long netIncome;
	private Long superAmount;
	
	public EmployeeBean getEmployee() {
		return employee;
	}
	
	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}
	
	public PayPeriodBean getPayPeriod() {
		return payPeriod;
	}
	
	public void setPayPeriod(PayPeriodBean payPeriod) {
		this.payPeriod = payPeriod;
	}
	
	public Long getGrossIncome() {
		return grossIncome;
	}
	
	public void setGrossIncome(Long grossIncome) {
		this.grossIncome = grossIncome;
	}
	
	public Long getIncomeTax() {
		return incomeTax;
	}
	
	public void setIncomeTax(Long incomeTax) {
		this.incomeTax = incomeTax;
	}
	
	public Long getNetIncome() {
		return netIncome;
	}
	
	public void setNetIncome(Long netIncome) {
		this.netIncome = netIncome;
	}
	
	public Long getSuperAmount() {
		return superAmount;
	}
	
	public void setSuperAmount(Long superAmount) {
		this.superAmount = superAmount;
	}	
	
}
