package com.assignment.payroll.generator.data.loader.json;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.common.data.loader.DataLoader;
import com.assignment.common.data.loader.DataValidationException;
import com.assignment.common.template.Data;
import com.assignment.payroll.view.EmployeeInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeePayJSONDataFileLoaderTest {

	@Autowired
	DataLoader empPayJsonDataFileLoader;
	
	@Value(value = "classpath:input/json")
	private Resource jsonInputResource;
	
	@Before
	public void setup() {
		assertThat(empPayJsonDataFileLoader, is(notNullValue()));
	}
	
	@Test
	public void shouldLoadEmployeePayDetailsFromJsonFile() throws DataLoadException, DataValidationException, IOException 
	{
		//Data<EmployeeInfo> empPayDetails = empPayJsonDataFileLoader.load("/input/json/employee-pay.json");
		Data<EmployeeInfo> empPayDetails = empPayJsonDataFileLoader.
				load(jsonInputResource.getURL().getPath() + File.separator + "employee-pay.json");
	
		// Assert that emp pay details object is not null and numbers employees loaded is 2
		assertThat(empPayDetails, is(notNullValue()));	
		assertThat(empPayDetails.getData().iterator().next(), is(notNullValue()));	
		assertThat(empPayDetails.getData().iterator().next().getEmployees(), hasSize(2));
	}	
	
	@Test(expected = DataLoadException.class)
	public void shouldHandleExceptionWhenJsonFileDoesNotExists() throws DataLoadException, DataValidationException 
	{
		// Handle DataLoadException when file does not exists.
		empPayJsonDataFileLoader.load("/input/json/xxxx.json");
	}		
	
	@Test(expected = DataValidationException.class)
	public void shouldHandleValidationExceptionForRequiredFields() throws DataLoadException, DataValidationException, IOException 
	{
		empPayJsonDataFileLoader.load(jsonInputResource.getURL().getPath() + File.separator + "employee-pay-missingdata.json");
	}	
}
