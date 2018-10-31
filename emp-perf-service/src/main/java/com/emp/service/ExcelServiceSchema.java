package com.emp.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.emp.model.Employee;

/**
 * @author Khushboo Singh
 * @for this is to declare various service related to Excel manipulation
 *
 */
public interface ExcelServiceSchema {
	/**
	 * @param FILE_PATH
	 * @return void
	 * @throws IOException
	 * @throws ParseException
	 * @for this method is to read an Excel file for a given path and return all
	 *      Employee details
	 */
	public abstract List<Employee> read(final String FILE_PATH) throws IOException, ParseException;

	/**
	 * @param employees
	 * @return operation message
	 * @for it will create Excel file by given all Employee details
	 */
	public abstract String create(List<Employee> employees);

	/**
	 * @param employee
	 * @return operation message
	 * @for it will append Excel file by a given single Employee details
	 */
	public abstract String append(Employee employee);
}
