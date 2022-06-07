package com.vh1ne.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * vh
 */

public class ExcelReader {
	//public static final String SAMPLE_XLSX_FILE_PATH = "E:\\Downloads\\Chrome Downloads\\Sample_900K.xlsx";
	public static String method(String path) throws IOException, InvalidFormatException {
		Instant start = Instant.now();
		try {

			Workbook workbook = WorkbookFactory.create(new File(path));
			Sheet sheet = workbook.getSheetAt(0);
			DataFormatter dataFormatter = new DataFormatter();
			System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
			for (Row row : sheet) {
				for (Cell cell : row) {
					String cellValue = dataFormatter.formatCellValue(cell);
					System.out.print(cellValue + "\t");
				}
				System.out.println();
			}
			((PrintStream) workbook).close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Instant end = Instant.now();
		System.out.println(printExecutionTime(start, end));
		return printExecutionTime(start, end);
	}

	public static String printExecutionTime(Instant start, Instant end) {
		return "Program  executed  in " + (float) Duration.between(start, end).toMillis() / 1000 + " seconds.";
	}

}
