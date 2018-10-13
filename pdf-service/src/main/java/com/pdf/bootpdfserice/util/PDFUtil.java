package com.pdf.bootpdfserice.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class PDFUtil {

	public static void writePDF(final String FILE_NAME, final String FILE_PATH, final String PATH_SEPARATOR)
			throws FileNotFoundException, DocumentException {

		Document document = new Document();

		PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_PATH + PATH_SEPARATOR + FILE_NAME)));

		// open
		document.open();

		Paragraph p = new Paragraph();
		p.add("This is my paragraph 1");
		p.setAlignment(Element.ALIGN_CENTER);

		document.add(p);

		Paragraph p2 = new Paragraph();
		p2.add("This is my paragraph 2"); // no alignment

		document.add(p2);

		Font f = new Font();
		f.setStyle(Font.BOLD);
		f.setSize(8);

		document.add(new Paragraph("This is my paragraph 3", f));

		// close
		document.close();
	}

	public static String readPDF(final String FILE_NAME, final String FILE_PATH, final String PATH_SEPARATOR)
			throws IOException {
		PdfReader reader = new PdfReader(FILE_PATH + PATH_SEPARATOR + FILE_NAME);

		// pageNumber = 1
		String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);

		reader.close();

		return textFromPage;
	}
}
