package com.assignment.payroll.generator.utils;

import static com.assignment.payroll.generator.utils.EmpPayValidatorUtils.DATE_MMM_YYYY_FORMAT;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateTimeDeserializer extends JsonDeserializer<DateTime> {
	 
	private static DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_MMM_YYYY_FORMAT);
 
    @Override
    public DateTime deserialize(JsonParser jsonparser, DeserializationContext context)
      throws IOException, JsonProcessingException 
    {
        String date = jsonparser.getText();
        return formatter.parseDateTime(date);

    }
}
