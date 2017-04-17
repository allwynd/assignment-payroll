package com.assignment.payroll.generator.data.populator;

import com.assignment.common.data.loader.DataLoadException;
import com.assignment.payroll.view.EmployeeInfo;

/**
 * Implementation clas that populates the JSON file data to type {@link EmployeeInfo}.
 * 
 * @author Allwyn
 *
 */
public class EmployeeJsonFilePopulator extends AbstractJSONFilePopulator<EmployeeInfo>
{

	@Override
	protected Class<EmployeeInfo> getJsonObject() throws DataLoadException 
	{
		return EmployeeInfo.class;
	}

}
