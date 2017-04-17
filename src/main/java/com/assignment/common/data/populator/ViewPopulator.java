package com.assignment.common.data.populator;

import com.assignment.common.data.loader.DataLoadException;

/**
 * This is a generic interface that allows concrete classes to map the 
 * data fields to the view of a given type {@link T}
 * 
 * @author allwyn.dsouza
 *
 * @param <T>
 */
public interface ViewPopulator<T> 
{
	
	/**
	 * Method populates the view with the given fields data.
	 * @param <E>
	 * @param result The result to be read to create a view
	 * part of returning view
	 * @return Returns the view 
	 * @throws DataLoadException
	 */
	<E> T populate(E result) throws DataLoadException;
}
