package com.emp.helper;

import java.io.File;

/**
 * @author Khushboo Singh
 * @for this class is to define require constants.
 */
public class AppConstatns {
	public static final String PATH_SEPARATOR = File.separator;
	public static final String EXT_EXCEL_FILE_NAME = "timesheet.xlsx";
	public static final String EXCEL_FILE_LOCATION = new StringBuilder().append(PATH_SEPARATOR).append("emp-perf-service").append(PATH_SEPARATOR).append("resources").toString();
	public static final String EXCEL_FILE_PATH = new StringBuilder().append(EXCEL_FILE_LOCATION).append(PATH_SEPARATOR).append(EXT_EXCEL_FILE_NAME).toString();

}
