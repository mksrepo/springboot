package com.emp.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.emp.helper.AppConstants;

/**
 * @author Khushboo Singh
 * @for this class is for general functionality across the application
 *
 */
public class ExcelUtil {
	@SuppressWarnings("resource")
	public static Iterator<Row> getExcelRows(final String FILE_PATH) throws IOException {
		// initial configuration of Excel resources
		Workbook workbook = new XSSFWorkbook(FILE_PATH);
		Sheet datatypeSheet = workbook.getSheetAt(0);
		return datatypeSheet.iterator();
	}

	public static Date stringToDate(final String DATE_VALUE) throws ParseException {
		return new SimpleDateFormat(AppConstants.EXCEL_DATE_FORMAT).parse(DATE_VALUE);
	}
}
