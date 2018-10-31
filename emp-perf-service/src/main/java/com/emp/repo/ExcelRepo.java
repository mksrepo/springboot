package com.emp.repo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.context.annotation.Configuration;

import com.emp.helper.AppConstants;

/**
 * @author Khsuboo Singh
 * @for this class is to create the excel repository
 *
 */
@Configuration
public class ExcelRepo {
	public static FileInputStream excelFile = null;

	/**
	 * @throws FileNotFoundException
	 * @for this constructor is to initialize Excel IO
	 */
	public ExcelRepo() throws FileNotFoundException {
		excelFile = new FileInputStream(new File(AppConstants.FILE_PATH));
	}
}
