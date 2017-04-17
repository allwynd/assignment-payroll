package com.assignment.payroll.generator.data.populator;

import java.io.Reader;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.common.data.populator.ViewPopulator;
import com.assignment.payroll.generator.utils.CustomDateTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Abstract JSON file populator class that populates type {@link T} from the result.
 * 
 * <p> Allows subclasses to define the type of JSON object needed. 
 * 
 * @author Allwyn
 *
 * @param <T>
 */
public abstract class AbstractJSONFilePopulator<T> implements ViewPopulator<T>
{
	
	@Override
	public T populate(Object result) throws DataLoadException 
	{
		Reader reader = (Reader) result;
		try 
		{
			ObjectMapper objectMapper = new ObjectMapper();
			// register custom datetime module
			objectMapper.registerModule(new CustomDateTimeModule());	
			return objectMapper.readValue(reader, getJsonObject());
		}
		catch (Exception e) 
		{
			throw new DataLoadException("Error populating JSON data into EmployeeInfo - " + e.getMessage(), e);
		} 		
	}
	
	/**
	 * The Json object of type {@link T} that will be populated.

	 * @return T The json object populated with the data.
	 * @throws DataLoadException
	 */
	protected abstract Class<T> getJsonObject() throws DataLoadException;
}
