package com.assignment.payroll.view;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.assignment.payroll.view.payslip.EmployeeBean;

/**
 * A placeholder class that stores a list of employees.
 * 
 * @author Allwyn
 *
 */
public class EmployeeInfo extends ViewBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7748821908946721682L;
	@JsonProperty(value = "employees")
	private List<EmployeeBean> employees;
	
	public EmployeeInfo()
	{
		employees = new ArrayList<EmployeeBean>();
	}

	public List<EmployeeBean> getEmployees() 
	{
		return employees;
	}

	public void setEmployees(List<EmployeeBean> employees) 
	{
		this.employees = employees;
	}

	public void addEmployee(EmployeeBean employeeBean) 
	{
		employees.add(employeeBean);		
	}		
	
}	

