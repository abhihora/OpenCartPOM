package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtil {
	
	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testData/register.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	
	public static Object[][] getTestData(String sheetName) {
		
		//initialize the 2D object array
		Object data[][]= null;
		
		try {
			FileInputStream ip= new FileInputStream(TEST_DATA_SHEET_PATH); 
			//workbookfactory will interact with the excel sheet
			book = WorkbookFactory.create(ip);
			//now we are in the register sheet, or the sheet path
			sheet =	book.getSheet(sheetName);
			
			//to count the last row number in the excel sheet
			//cell number is denoting the last column count
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		//we have to iltearate 2 for loops cz row , column
		for(int i=0; i<sheet.getLastRowNum();i++) {
			for(int j=0; j<sheet.getRow(0).getLastCellNum();j++) {
				//have to ilterate from the 1 row
				data[i][j]=	sheet.getRow(i+1).getCell(j).toString();
				
			}
		}
		
		
		}
		//if file not found
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
		
	}

}
