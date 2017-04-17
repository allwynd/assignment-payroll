package com.assignment.payroll.model;

import java.math.BigDecimal;

/**
 * Data model class that represents a payslip.
 * @author Allwyn
 *
 */
public class Payslip extends Entity
{
	private Employee employee;
	private PayPeriod payPeriod;
	private BigDecimal grossIncome;
	private BigDecimal incomeTax;
	private BigDecimal netIncome;
	private BigDecimal superAmount;
	
	public PayPeriod getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(PayPeriod payPeriod) {
		this.payPeriod = payPeriod;
	}

	public BigDecimal getGrossIncome() {
		return grossIncome;
	}

	public void setGrossIncome(BigDecimal grossIncome) {
		this.grossIncome = grossIncome;
	}

	public BigDecimal getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}

	public BigDecimal getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(BigDecimal netIncome) {
		this.netIncome = netIncome;
	}

	public BigDecimal getSuperAmount() {
		return superAmount;
	}

	public void setSuperAmount(BigDecimal superAmount) {
		this.superAmount = superAmount;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}	
	
	
}
