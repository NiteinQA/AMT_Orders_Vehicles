package com.amt.testUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetExcelFormulaValue extends ReadExcelCalculation {
	
public	static Properties prop;
	public static double  get_formula_value(int rounum, int columnnum, String sheet_name) throws IOException {
	
		
		prop=new Properties();
		FileInputStream ip = new FileInputStream("D:\\Orders_Vehicles\\AMT_Orders_Vehicles\\src\\main\\java\\configs\\excelValues.properties");
		prop.load(ip);
	
	FileInputStream fis = new FileInputStream(prop.getProperty("formula_excel_path"));
	XSSFWorkbook book = new XSSFWorkbook(fis);
	XSSFSheet sheet = book.getSheet(sheet_name);// selecting sheet with its name as a parameter
	
	
	XSSFRow row = sheet.getRow(rounum);// read data from first row as 0th row contains header
	XSSFCell cell = row.getCell(columnnum);// read data from first cell
	FormulaEvaluator evaluator = book.getCreationHelper().createFormulaEvaluator();		
	XSSFFormulaEvaluator.evaluateAllFormulaCells(book);// existing Sheet, Row, and Cell setup		
	if (cell.getCellType() == CellType.FORMULA) {
		evaluator.evaluateFormulaCell(cell);
		
	}	
	 
	   
			
			try{return cell.getNumericCellValue(); }catch(Exception e) {return Double.parseDouble(cell.getStringCellValue());}
		
}
	
public static double get_string_value(int rounum, int columnnum, String sheet_name) throws IOException {
	
		
		prop=new Properties();
		FileInputStream ip = new FileInputStream("D:\\Orders_Vehicles\\AMT_Orders_Vehicles\\src\\main\\java\\configs\\excelValues.properties");
		prop.load(ip);
	
	FileInputStream fis = new FileInputStream(prop.getProperty("formula_excel_path"));
	XSSFWorkbook book = new XSSFWorkbook(fis);
	XSSFSheet sheet = book.getSheet(sheet_name);// selecting sheet with its name as a parameter
	
	
	XSSFRow row = sheet.getRow(rounum);// read data from first row as 0th row contains header
	XSSFCell cell = row.getCell(columnnum);// read data from first cell
	FormulaEvaluator evaluator = book.getCreationHelper().createFormulaEvaluator();		
	XSSFFormulaEvaluator.evaluateAllFormulaCells(book);// existing Sheet, Row, and Cell setup		
	if (cell.getCellType() == CellType.STRING) {
		evaluator.evaluateFormulaCell(cell);			
	}
	return Double.parseDouble(cell.getStringCellValue());

}
	
	
	public static String get_cell_value(int rounum, int columnnum, String sheet_name) throws IOException {
	
		
		prop=new Properties();
		FileInputStream ip = new FileInputStream("D:\\Orders_Vehicles\\AMT_Orders_Vehicles\\src\\main\\java\\configs\\excelValues.properties");
		prop.load(ip);
	
	FileInputStream fis = new FileInputStream(prop.getProperty("quote_save_excel_path"));
	XSSFWorkbook book = new XSSFWorkbook(fis);
	XSSFSheet sheet = book.getSheet(sheet_name);// selecting sheet with its name as a parameter
	
	
	XSSFRow row = sheet.getRow(rounum);// read data from first row as 0th row contains header
	XSSFCell cell = row.getCell(columnnum);// read data from first cell
	FormulaEvaluator evaluator = book.getCreationHelper().createFormulaEvaluator();		
	XSSFFormulaEvaluator.evaluateAllFormulaCells(book);// existing Sheet, Row, and Cell setup		
	if (cell.getCellType() == CellType.FORMULA) {
		evaluator.evaluateFormulaCell(cell);			
	}
	return cell.getStringCellValue();

}
}
