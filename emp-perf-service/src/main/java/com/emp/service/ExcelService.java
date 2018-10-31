package com.emp.service;

import java.util.List;

import com.emp.model.Employee;

/**
 * @author Khushboo Singh
 * @for this is to declare various service related to Excel manipulation
 *
 */
public interface ExcelService {
	/**
	 * @param FILE_PATH
	 * @return void
	 * @for this method is to read an Excel file for a given path and return all
	 *      Employee details
	 */
	public abstract List<Employee> read(final String FILE_PATH);

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
