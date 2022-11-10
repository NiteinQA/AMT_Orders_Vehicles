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
	
	
	public static double get_formula_value(int rounum, int columnnum, String sheet_name) throws IOException {
	
		
		prop=new Properties();
		FileInputStream ip = new FileInputStream("D:\\newWorkspaceStaging\\AutomationStaging\\src\\main\\java\\configs\\excelValues.properties");
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
	return cell.getNumericCellValue();

}
}
