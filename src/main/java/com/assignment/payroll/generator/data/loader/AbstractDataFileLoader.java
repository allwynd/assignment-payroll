package com.assignment.payroll.generator.data.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.common.data.loader.DataLoader;
import com.assignment.common.data.loader.DataValidationException;

public abstract class AbstractDataFileLoader implements DataLoader
{
	/**
	 * Validates the given result.
	 * 
	 * @param data
	 */
	protected abstract void validate(Object result) throws DataValidationException;
	
	/**
	 * 
	 * @param resourceFilename
	 * @return
	 * @throws DataLoadException
	 */
	protected Reader loadFile(String resourceFilename) throws DataLoadException 
	{
		BufferedReader br = null;
		try 
		{
			//File file = new File("." + resourceFilename);
			File file = new File(resourceFilename);
			FileReader fr = new FileReader(file.getAbsoluteFile());		
			br = new BufferedReader(fr);

		}
		catch (Exception e)
		{
			throw new DataLoadException("Cannot open resource " + resourceFilename, e);
		}
		// reader
		return br;
	}
	
}
