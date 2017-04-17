package com.assignment.payroll.generator.render;

import java.util.List;

import com.assignment.payroll.view.payslip.EmpPayslip;

/***
 * Common interface that allows implementation classes to render a bean to the given render type.
 * 
 * @author Allwyn
 *
 */
public interface PayslipRenderer 
{
	void render(List<EmpPayslip> payslips, RenderType renderType) throws PayslipRendererException, UnsupportedRenditionException;

}
