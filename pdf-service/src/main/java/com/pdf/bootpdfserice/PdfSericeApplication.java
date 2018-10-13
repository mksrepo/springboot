package com.pdf.bootpdfserice;

import java.io.IOException;

import com.pdf.bootpdfserice.util.Constants;
import com.pdf.bootpdfserice.util.PDFUtil;

public class PdfSericeApplication {

	public static void main(String[] args) {
		try {
			System.out
					.println(PDFUtil.readPDFFields(Constants.FILE_NAME, Constants.FILE_PATH, Constants.PATH_SEPARATOR));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
