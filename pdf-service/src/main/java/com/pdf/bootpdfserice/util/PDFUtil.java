package com.pdf.bootpdfserice.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class PDFUtil {

	public static Map<String, String> readPDFFields(final String FILE_NAME, final String FILE_PATH,
			final String PATH_SEPARATOR) throws IOException {

		Map<String, String> fieldMap = null;
		PdfReader reader = new PdfReader(FILE_PATH + PATH_SEPARATOR + FILE_NAME);
		AcroFields fields = reader.getAcroFields();

		fieldMap = new HashMap<String, String>();
		Set<String> fieldKeys = fields.getFields().keySet();
		for (String key : fieldKeys)
			fieldMap.put(key, fields.getField(key));

		reader.close();
		return fieldMap;
	}

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
