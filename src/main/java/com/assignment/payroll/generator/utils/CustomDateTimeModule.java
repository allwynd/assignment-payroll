package com.assignment.payroll.generator.utils;

import org.joda.time.DateTime;

import com.fasterxml.jackson.datatype.joda.JodaModule;

public class CustomDateTimeModule extends JodaModule
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -588304365169295776L;

	public CustomDateTimeModule()
	{
		super();
		addSerializer(DateTime.class, new CustomDateTimeSerializer());
		addDeserializer(DateTime.class, new CustomDateTimeDeserializer());		
	}
}
