package com.pdf.bootpdfserice;

import java.io.IOException;

import com.pdf.bootpdfserice.util.Constants;
import com.pdf.bootpdfserice.util.PDFUtil;

public class PdfSericeApplication {

	public static void main(String[] args) {
		try {
			PDFUtil.readPDFFields(Constants.FILE_NAME, Constants.FILE_PATH, Constants.PATH_SEPARATOR);
			System.out.println(PDFUtil.readPDF(Constants.FILE_NAME, Constants.FILE_PATH, Constants.PATH_SEPARATOR));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
