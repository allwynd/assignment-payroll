package com.assignment.common.template;

import java.util.ArrayList;
import java.util.List;

/**
 * The data holder class to store the loaded data of a given type T
 */
public class Data<T> 
{
	/**
	 * The type of data to be stored identified by T.
	 */
	private List<T> data;

	public List<T> getData() 
	{
		return data;
	}

	public void setData(List<T> data) 
	{
		this.data = data;
	}
	
	/**
	 * Data to be stored identified T
	 * @param value
	 */
	public void addData(T value) {
		if (data == null) 
			data = new ArrayList<T>();
		
		data.add(value);
	}
	
}
