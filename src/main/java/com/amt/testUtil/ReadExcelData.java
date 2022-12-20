package com.amt.testUtil;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.amt.testBase.TestBase;

public class ReadExcelData extends TestBase {
	//public static String Test_Data_Sheet_Path=prop.getProperty("Test_Data_Sheet_Path");
	
	
	public static Object[][] getTestData(String sheetName) throws IOException
	{
		FileInputStream fis=new FileInputStream("D:\\StagingNew\\AMT_Automation\\src\\main\\java\\com\\amt\\testData\\NewTestData.xlsx");
		XSSFWorkbook wb  = new XSSFWorkbook(fis);
		 XSSFSheet sheet=wb.getSheet(sheetName);//selecting sheet with its name as a parameter
		int rowCount=sheet.getPhysicalNumberOfRows();//no. of rows
		 XSSFRow row =sheet.getRow(0);
		int colCount=row.getLastCellNum();//no. of colm
			
		//initialize object array to store data
		Object[][] data = new Object [rowCount-1][colCount];
		for(int x=0; x<rowCount-1; x++)
		{
			row =sheet.getRow(x+1);//read data from first row as 0th row contains header
			for(int y=0; y<colCount; y++)				
			{
				XSSFCell cell=row.getCell(y);// read data from first cell
				DataFormatter formatter=new DataFormatter();
				data[x][y] = formatter.formatCellValue(cell);
			}
			
		}
		return data;
		
	}
	
	
	public static Object[][] getTestDataForLeadOppoUnderwriting(String sheetName) throws IOException
	{
		FileInputStream fis=new FileInputStream("D:\\StagingNew\\AMT_Automation\\src\\main\\java\\com\\amt\\testData\\LeadOppoUndTestData.xlsx");
		XSSFWorkbook wb  = new XSSFWorkbook(fis);
		 XSSFSheet sheet=wb.getSheet(sheetName);//selecting sheet with its name as a parameter
		int rowCount=sheet.getPhysicalNumberOfRows();//no. of rows
		 XSSFRow row =sheet.getRow(0);
		int colCount=row.getLastCellNum();//no. of colm
			
		//initialize object array to store data
		Object[][] data = new Object [rowCount-1][colCount];
		for(int x=0; x<rowCount-1; x++)
		{
			row =sheet.getRow(x+1);//read data from first row as 0th row contains header
			for(int y=0; y<colCount; y++)				
			{
				XSSFCell cell=row.getCell(y);// read data from first cell
				DataFormatter formatter=new DataFormatter();
				data[x][y] = formatter.formatCellValue(cell);
			}
			
		}
		return data;
		
	}

}
