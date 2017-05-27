package com.assignment.payroll.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DBConnect {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public DBConnect(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
