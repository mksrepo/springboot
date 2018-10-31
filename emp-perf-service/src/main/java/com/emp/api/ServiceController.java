package com.emp.api;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.helper.AppConstants;
import com.emp.model.Employee;
import com.emp.service.ExcelServiceSchema;

/**
 * @author Khusboo Singh
 * @for this class is for REST service
 *
 */
@RestController
@RequestMapping("/excel-service")
public class ServiceController {

	@Autowired
	@Qualifier("excelservice")
	ExcelServiceSchema excelService;

	/**
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 * @for this request is to return all Employee details as JSON
	 */
	@GetMapping("/get")
	public List<Employee> greeting() throws IOException, ParseException {
		List<Employee> employees = excelService.read(AppConstants.FILE_PATH);
		return employees;
	}
}
