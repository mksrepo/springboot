package com.emp.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.emp.helper.AppConstants;
import com.emp.model.Employee;
import com.emp.util.ExcelUtil;

/**
 * @author Khshboo Singh
 * @for this class is to implemnt the Excel service
 *
 */
@Service(AppConstants.SERVICE_NAME)
public class ExcelService implements ExcelServiceSchema {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.emp.service.ExcelServiceSchema#read(java.lang.String)
	 * 
	 * @for this method is to implement Excel read logic
	 */
	@Override
	public List<Employee> read(final String FILE_PATH) throws IOException, ParseException {

		List<Employee> employees = new ArrayList<Employee>();
		// iterate over row
		Iterator<Row> iterator = ExcelUtil.getExcelRows(FILE_PATH);
		while (iterator.hasNext()) {
			Row currentRow = iterator.next();
			Employee employee = new Employee();
			// iterate over cell
			Iterator<Cell> cellIterator = currentRow.iterator();
			while (cellIterator.hasNext()) {
				// getting cell value
				Cell currentCell = cellIterator.next();
				switch (currentCell.getColumnIndex()) {
				case 0:
					employee.setName(currentCell.getStringCellValue());
					break;
				case 1:
					employee.setDate(ExcelUtil.stringToDate(currentCell.getStringCellValue()));
					break;
				/*case 2:
					employee.setProductiveCode(currentCell.getNumericCellValue());
					break;
				case 3:
					System.out.println("====>"+currentCell.getNumericCellValue());
					employee.setSwipeIn(ExcelUtil.stringToDate(currentCell.getStringCellValue()));
					break;
				case 4:
					employee.setSwipeOut(ExcelUtil.stringToDate(currentCell.getStringCellValue()));
					break;
				case 5:
					employee.setProductiveCode(currentCell.getNumericCellValue());
					break;*/
				}
			}
			// populating employee list
			employees.add(employee);
			System.out.println();
		}
		return employees;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.emp.service.ExcelServiceSchema#create(java.util.List)
	 * 
	 * @for this method is to implement Excel create logic
	 */
	@Override
	public String create(List<Employee> employees) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.emp.service.ExcelServiceSchema#append(com.emp.model.Employee)
	 * 
	 * @for this method is to append Excel
	 */
	@Override
	public String append(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

}
