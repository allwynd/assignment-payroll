package com.assignment.payroll.view;

import java.io.Serializable;

/**
 * Base class representing the view.
 * 
 * @author Allwyn
 *
 */
public abstract class ViewBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2648963320565092198L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
