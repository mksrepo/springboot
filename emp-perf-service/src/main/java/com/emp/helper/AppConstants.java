package com.emp.helper;

import java.io.File;

/**
 * @author Khushboo Singh
 * @for this class is to define require constants.
 */
public class AppConstants {
	//file related
	public static final String PATH_SEPARATOR = File.separator;
	public static final String FILE_NAME = "timesheet.xlsx";
	public static final String FILE_LOCATION = new StringBuilder().append(System.getProperty("user.dir")).append(PATH_SEPARATOR).append("resources").toString();
	public static final String FILE_PATH = new StringBuilder().append(FILE_LOCATION).append(PATH_SEPARATOR).append(FILE_NAME).toString();

	// date related
	public static final String EXCEL_DATE_FORMAT = "dd.mm.yyyy";

	// service related
	public static final String SERVICE_NAME = "excelservice";
}
