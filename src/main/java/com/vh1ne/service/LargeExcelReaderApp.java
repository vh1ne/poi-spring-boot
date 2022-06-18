package com.vh1ne.service;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class LargeExcelReaderApp {

	public static void main(String[] args) throws Exception {
		String fileName = "E:\\Downloads\\Chrome Downloads\\Sample_900K.xlsx";
		//""E:\\Downloads\\Chrome Downloads\\Sample10K.xlsx";
		// readLargeExcelFile(fileName);

		SaxEventUserModel saxEventUserModel = new SaxEventUserModel();
		saxEventUserModel.processSheets(fileName);
	}

	// The following method will give error - OutOfMemoryError
	public static void readLargeExcelFile(final String fileName) throws EncryptedDocumentException, IOException, InvalidFormatException {
		Instant start = Instant.now();
		Workbook wb = WorkbookFactory.create(new File(fileName));

		XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(0);
		DataFormatter dataFormatter = new DataFormatter();
		for (Row row: sheet) {
			for(Cell cell: row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				System.out.print(cellValue + "\t");
			}
			System.out.println();
		}
		Instant end = Instant.now();
		System.out.println(printExecutionTime(start,end));
	}


	public static String printExecutionTime(Instant start, Instant end)
	{
		return "Program  executed  in "+  (float) Duration.between(start, end).toMillis() / 1000  + " seconds." ;
	}
}
