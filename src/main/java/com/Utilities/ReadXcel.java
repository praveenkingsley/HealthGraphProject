package com.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXcel {
	 ArrayList<HashMap<String, String>> data=new ArrayList<>();
	
	
	public  ArrayList<HashMap<String, String>> data(String filePath,String SheetName) {
		
		
		try {
			File file =new File(filePath);
			FileInputStream input = new FileInputStream(file);
			XSSFWorkbook workbook=new XSSFWorkbook(input);
			XSSFSheet sheet= workbook.getSheet(SheetName);
			
			
			int noOfRows= sheet.getPhysicalNumberOfRows();
			int noOfColumns= sheet.getRow(0).getLastCellNum();
			
			XSSFRow headerRow= sheet.getRow(0);
			
			for(int i=1;i<noOfRows;i++) {
				HashMap<String, String> currentHash=new HashMap<>();
				for(int j=0;j<noOfColumns;j++) {
					String value=sheet.getRow(i).getCell(j).getStringCellValue();
					currentHash.put(headerRow.getCell(j).getStringCellValue(), value);
				}
				data.add(currentHash);
			}
			workbook.close();
			
		} catch (IOException e) {
			
		}
		
		return data;
	}

}
