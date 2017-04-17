package com.assignment.payroll.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.common.data.loader.DataValidationException;
import com.assignment.payroll.generator.PayslipGeneratorException;
import com.assignment.payroll.generator.utils.EmpPayValidatorUtils;
import com.assignment.payroll.service.EmployeePayslipService;
import com.assignment.payroll.tax.TaxCalculationException;
import com.assignment.payroll.view.payslip.EmpPayslip;
import com.assignment.payroll.view.payslip.EmployeeBean;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeePayslipService employeePayslip;	
	
	@RequestMapping(value = "/payInfo", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeBean>> listAllEmployeePayInfo() throws DataLoadException, DataValidationException {
		List<EmployeeBean> empPayList = empPayList = employeePayslip.retrieveEmployeesPay();

		if (empPayList.isEmpty()) {
            return new ResponseEntity("No Employee Pay details found.", HttpStatus.NO_CONTENT);
        }
		
        return new ResponseEntity<List<EmployeeBean>>(empPayList, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/payslip", method = RequestMethod.PUT)
    public ResponseEntity<EmpPayslip> generatePayslip(@RequestBody EmployeeBean employeeBean) 
    	throws DataValidationException, TaxCalculationException, PayslipGeneratorException {
		
		logger.info("Employee Pay Info {}", employeeBean);
		
		// Set the payment date value - if entered.
		if (StringUtils.isNotEmpty(employeeBean.getPayStartDate()))
			employeeBean.setPaymentStartDate(EmpPayValidatorUtils.validatePaymentStartDate(employeeBean.getPayStartDate()));
			
		EmpPayslip empPayslip = employeePayslip.generatePayslip(employeeBean);
		return new ResponseEntity<EmpPayslip>(empPayslip, HttpStatus.OK);	
	}	
}
