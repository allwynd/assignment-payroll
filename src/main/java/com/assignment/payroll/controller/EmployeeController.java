package com.assignment.payroll.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.common.data.loader.DataValidationException;
import com.assignment.payroll.service.EmployeePayslipService;
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
            return new ResponseEntity("No Employee Pay infor found.", HttpStatus.NO_CONTENT);
        }
		
        return new ResponseEntity<List<EmployeeBean>>(empPayList, HttpStatus.OK);		
	}
}
