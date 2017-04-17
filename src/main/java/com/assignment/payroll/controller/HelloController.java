package com.assignment.payroll.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title", "Dear Learner");
		modal.addAttribute("message", "Welcome to Java Employee Payroll");
		return "hello";
	}
}
