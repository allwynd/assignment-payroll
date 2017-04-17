package com.assignment.payroll.generator.data.loader.json;

import java.io.Reader;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.common.data.loader.DataValidationException;
import com.assignment.common.data.populator.ViewPopulator;
import com.assignment.common.template.Data;
import com.assignment.payroll.generator.data.loader.AbstractDataFileLoader;
import com.assignment.payroll.view.payslip.EmployeeBean;

/**
 * An abstract class that loads data from a file as json. Define common method for loading json file
 * allowing subclasses to use.
 * 
 * @author Allwyn
 *
 */
public abstract class AbstractJSONDataFileLoader extends AbstractDataFileLoader
{
	protected Reader reader;
	
	/**
	 * 
	 * Method loads the data from the given <code>input</code> json file name and populates the view. 
	 * @param input The location and name of the json input file 
	 * @param viewPopulator The view populator that takes the responsibility to populate the data.
	 * @return Data of the type {@link EmployeeBean}
	 * 
	 * @throws DataLoadException
	 * @throws DataValidationException
	 */
	protected <T> Data<T> getJsonData(Object input, ViewPopulator<T> viewPopulator) throws DataLoadException, DataValidationException
	{
		preLoad(input);
		return loadData(viewPopulator);
	}
	
	void preLoad(Object filename) throws DataLoadException 
	{
		// load file
		try 
		{
			reader = super.loadFile((String)filename);
		} 
		catch (DataLoadException e) 
		{
			throw new DataLoadException("Error reading JSON resources: " + filename, e);
		}
		
	}

	<T> Data<T> loadData(ViewPopulator<T> viewPopulator) throws DataLoadException, DataValidationException 
	{
		Data<T> jsondata = new Data<T>();		
		// populate the view
		jsondata.addData(viewPopulator.populate(reader));	
		// validate
		validate(jsondata);
		
		return jsondata;
	}	

}
