package com.assignment.common.data.loader;

import com.assignment.common.template.Data;

/**
 * Common interface that determines the data loading contract which concrete
 * classes can implement.
 * 
 * The data can be loaded using different resource (e.g file system or any other source).
 * 
 * @author Allwyn
 *
 */
public interface DataLoader 
{
	public static enum DataLoaderType
	{
		CSV_DATA_LOADER,
		JSON_DATA_LOADER;
	}
	
	/**
	 * Load the data based on the given input instructions.
	 * @param <T> Data of type T
	 * @param input This can be a source file path or the source object, depending on the
	 * implementation method.
	 * @return Object
	 * @throws DataLoadException
	 * @throws DataValidationException
	 */
	public <T> Data<T> load(Object input) throws DataLoadException, DataValidationException;
}
