package com.pdf.bootpdfserice;

import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.pdf.bootpdfserice.util.Constants;
import com.pdf.bootpdfserice.util.PDFUtil;

public class PdfSericeApplication {

	public static void main(String[] args) {
		try {
			PDFUtil.writePDF(Constants.FILE_NAME, Constants.FILE_PATH, Constants.PATH_SEPARATOR);
			System.out.println(PDFUtil.readPDF(Constants.FILE_NAME, Constants.FILE_PATH, Constants.PATH_SEPARATOR));
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}
}
