package com.assignment.payroll.generator.data.populator;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.common.data.populator.ViewPopulator;

/**
 * Abstract CSV file populator class that populates type {@link T} from the result.
 * 
 * <p> Allows subclasses to handle the logic of populating the data for the type.
 * @author Allwyn
 *
 * @param <T>
 */
public abstract class AbstractCSVFilePopulator<T> implements ViewPopulator<T>
{
	
	@Override
	public T populate(Object result) throws DataLoadException 
	{
		String[] fields = (String[]) result;	
		return populateCsvData(fields);
	}
	
	/**
	 * Populates the csv data of type {@link T} from the given <code>fields</code> arrays.
	 * @param fields
	 * @return
	 * @throws DataLoadException
	 */
	protected abstract T populateCsvData(String[] fields) throws DataLoadException;
}
