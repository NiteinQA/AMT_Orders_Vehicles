package com.amt.testUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.amt.testBase.TestBase;

public class ReadExcelCalculation extends TestBase {
	
	Properties prop;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	public ReadExcelCalculation() {
		try {
			// Properties class object initialization
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"D:\\Orders_Vehicles\\AMT_Orders_Vehicles\\src\\main\\java\\configs\\excelValues.properties");
			// load property file
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		PageFactory.initElements(driver, this);
	}

	public ReadExcelCalculation obj_read_excel_calculation_page;
	
	
	public void write_monthly_maint_cost_to_excel_BCH(String maintenance_required,
			String monthlyMaintenanceRental, String sheet_name)
			throws IOException {

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);	
		wb.getSheet(sheet_name).getRow(37).getCell(1).setCellValue(maintenance_required);
	
		wb.getSheet(sheet_name).getRow(40).getCell(0).setCellValue(Double.parseDouble(monthlyMaintenanceRental));
	
		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

	}

	
	public void write_monthly_maint_cost_to_excel_HPNR(String maintenance_required,
			String monthlyMaintenanceRental, String sheet_name)
			throws IOException {

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);	
		wb.getSheet(sheet_name).getRow(37).getCell(1).setCellValue(maintenance_required);
	
		wb.getSheet(sheet_name).getRow(40).getCell(0).setCellValue(Double.parseDouble(monthlyMaintenanceRental));
	
		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

	}


	public void write_holding_cost_cap_values_to_excel_with_maintenance(double terms_from_screen,
			double annual_mileage, double used_residual_value, double total_cap_maintenance_value_converted,
			double percentage_cap_residual_value, double percentage_cap_maintenance_cost, String sheet_name)
			throws IOException, ClassNotFoundException {

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(28).getCell(7).setCellValue(terms_from_screen);
		wb.getSheet(sheet_name).getRow(29).getCell(7).setCellValue(annual_mileage);
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {	
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value*1.2*100*(1/percentage_cap_residual_value));
		}
		else
		{
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value*100*(1/percentage_cap_residual_value));
		}

		wb.getSheet(sheet_name).getRow(31).getCell(8).setCellValue(total_cap_maintenance_value_converted*terms_from_screen*100*(1/percentage_cap_maintenance_cost));
		wb.getSheet(sheet_name).getRow(44).getCell(0).setCellValue(percentage_cap_residual_value);
		wb.getSheet(sheet_name).getRow(44).getCell(2).setCellValue(percentage_cap_maintenance_cost);

		wb.getSheet(sheet_name).getRow(25).getCell(1).setCellValue("YES");
		wb.getSheet(sheet_name).getRow(104).getCell(0).setCellValue("YES");

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
	}

	public double verify_OTR_for_calculation_after_adding_other_support_values_to_excel(double otherSupportValue,
			String sheet_name) throws IOException {

		LO.print("Writing other support value to Excel has been started");
		System.out.println("Writing other support value to Excel has been started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(19).getCell(4).setCellValue(otherSupportValue);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));

		wb.write(out);

		LO.print("Writing other support value to Excel has been ended");
		System.out.println("Writing other support value to Excel has been ended");

		// Getting OTR_for_calculation price

		return GetExcelFormulaValue.get_formula_value(20, 4, sheet_name);

	}

	public double verify_table_calculations_contract_types_page(WebDriver driver, String vehicle_price_copied,
			WebElement acq_contractTypes_table_calculation_basic_paint_price,
			WebElement acq_contractTypes_table_calculation_basic_options_price,
			WebElement acq_contractTypes_calculation_table_discount,
			WebElement acq_contractTypes_calculation_table_additional_discount, String sheet_name) throws IOException {

		ExplicitWait.visibleElement(driver, acq_contractTypes_table_calculation_basic_paint_price, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_table_calculation_basic_options_price, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_calculation_table_discount, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_calculation_table_additional_discount, 30);
		String calculation_table_dicount_data = acq_contractTypes_calculation_table_discount.getText();
		String calculation_table_basic_add_discount_data = acq_contractTypes_calculation_table_additional_discount
				.getText();

		String discount[] = calculation_table_dicount_data.split("\\s+");
		String additional_discount[] = calculation_table_basic_add_discount_data.split("\\s+");

//**************First (Basic price) row data fetching and variable assigning  code***********************************************			 

		String newString2 = RemoveComma
				.of(acq_contractTypes_table_calculation_basic_paint_price.getText().trim().substring(2));

		String newString3 = RemoveComma
				.of(acq_contractTypes_table_calculation_basic_options_price.getText().trim().substring(2));

		double vehicle_basic_price = Double.parseDouble(vehicle_price_copied);
		LO.print("Getting Basic Price- Vehicle value is  =" + vehicle_price_copied);
		System.out.println("Getting Basic Price -Vehicle value is =" + vehicle_price_copied);
		double paint_basic_price = Double.parseDouble(newString2);
		LO.print("Getting Basic Price - Paint  value is  =" + newString2);
		System.out.println("GettingPrice - Paint value is  =" + newString2);
		double options_basic_price = Double.parseDouble(newString3);
		LO.print("Getting  Basic Price - Options value is   =" + newString3);
		System.out.println("Getting  Basic Price - Options  value is   =" + newString3);

//**************Second (Discount) row data fetching and variable assigning code**************************************************			
		ArrayList<String> arrDiscount = new ArrayList<String>();

		for (int i = 0; i < discount.length; i++) {
			if (discount[i].contains("Discount") || discount[i].contains("%")) {

			} else {
				arrDiscount.add(discount[i]);
			}
		}
		String fourth_cell = arrDiscount.get(0);
		String newString4 = RemoveComma.of(fourth_cell);

		String fifth_cell = arrDiscount.get(1);
		String newString5 = RemoveComma.of(fifth_cell);

		String sixth_cell = arrDiscount.get(2);
		String newString6 = RemoveComma.of(sixth_cell);

		double vehicle_discount = Double.parseDouble(newString4);

		LO.print("Getting Discount- Vehicle value is  =" + newString4);
		System.out.println("Getting Discount -Vehicle value is  =" + newString4);

		double paint_discount = Double.parseDouble(newString5);
		LO.print("Getting   Discount - Paint value is   =" + newString5);
		System.out.println("Getting  Discount - Paint value is  =" + newString5);

		double options_discount = Double.parseDouble(newString6);
		LO.print("Getting  Discount- Options value is   =" + newString6);
		System.out.println("Getting  Discount - Options value is  =" + newString6);

//***************Third (Additional Discount) row data fetching and variable assigning code**************************************************	
		ArrayList<String> arrAdditionalDiscount = new ArrayList<String>();

		for (int i = 0; i < additional_discount.length; i++) {
			if (additional_discount[i].contains("Additional") || additional_discount[i].contains("discount")
					|| additional_discount[i].contains("£")) {

			} else {
				arrAdditionalDiscount.add(additional_discount[i]);
			}
		}

		String seventh_cell = arrAdditionalDiscount.get(0);
		String newString7 = RemoveComma.of(seventh_cell);

		String eighth_cell = arrAdditionalDiscount.get(1);
		String newString8 = RemoveComma.of(eighth_cell);

		String nineth_cell = arrAdditionalDiscount.get(2);
		String newString9 = RemoveComma.of(nineth_cell);

		double vehicle_additional_discount = Double.parseDouble(newString7);
		LO.print("Getting Additional Discount- Vehicle value is  =" + newString7);
		System.out.println("Getting Additional Discount -Vehicle value is  =" + newString7);

		double paint_additional_discount = Double.parseDouble(newString8);
		LO.print("Getting  Additional Discount - paint value is  =" + newString8);
		System.out.println("Getting  Additional Discount - paint value is   =" + newString8);

		double options_additional_discount = Double.parseDouble(newString9);
		LO.print("Getting  Additional Discount - Options value is  =" + newString9);
		System.out.println("Getting  Additional Discount - Options value is  =" + newString9);

//****************** data fetching and variable assigning code Completed **************************************************	

//**************write this data to excel formula for calculating OTR calculation****************	

		LO.print("Writing OTR table values to excel sheet -Started ");
		System.out.println("Writing OTR table values to excel sheet -Started ");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(2).getCell(1).setCellValue(vehicle_basic_price);
		wb.getSheet(sheet_name).getRow(2).getCell(2).setCellValue(paint_basic_price);
		wb.getSheet(sheet_name).getRow(2).getCell(3).setCellValue(options_basic_price);
		wb.getSheet(sheet_name).getRow(3).getCell(1).setCellValue(vehicle_discount);
		wb.getSheet(sheet_name).getRow(3).getCell(2).setCellValue(paint_discount);
		wb.getSheet(sheet_name).getRow(3).getCell(3).setCellValue(options_discount);
		wb.getSheet(sheet_name).getRow(4).getCell(1).setCellValue(vehicle_additional_discount);
		wb.getSheet(sheet_name).getRow(4).getCell(2).setCellValue(paint_additional_discount);
		wb.getSheet(sheet_name).getRow(4).getCell(3).setCellValue(options_additional_discount);

		if (sheet_name.contains("Formula1") || sheet_name.contains("BCH (Formula 3)")) {
			wb.getSheet(sheet_name).getRow(101).getCell(1).setCellValue(0);
			wb.getSheet(sheet_name).getRow(101).getCell(3).setCellValue(0);
		} else {
			wb.getSheet(sheet_name).getRow(107).getCell(1).setCellValue(0);
			wb.getSheet(sheet_name).getRow(107).getCell(3).setCellValue(0);
		}

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		LO.print("Writing OTR table values to excel sheet -Completed ");
		System.out.println("Writing OTR table values to excel sheet -Completed ");

		LO.print("Reading Subtotal After Discount from excel sheet -Started ");
		System.out.println("Reading Subtotal After Discount from excel sheet -Started ");

		double subtotal_after_discount_excel = GetExcelFormulaValue.get_formula_value(8, 4, sheet_name);

		System.out.println("subtotal_after_discount_excel = " + subtotal_after_discount_excel);
		wb.close();
		out.close();

		LO.print("Reading Subtotal After Discount from excel sheet -Started ");
		System.out.println("Reading Subtotal After Discount from excel sheet -Started ");

		LO.print("Subtotal After Discount from excel sheet is =" + subtotal_after_discount_excel);
		System.out.println("Subtotal After Discount from excel sheet is =" + subtotal_after_discount_excel);
		return subtotal_after_discount_excel;

	}

	public void write_road_tax_for_first_year_to_calculation_excel(String road_tax_for_first_year , String calculation_excel_sheet_name ) throws IOException {

	
		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(calculation_excel_sheet_name).getRow(12).getCell(4).setCellValue(Double.parseDouble(road_tax_for_first_year));

	
		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		wb.close();
		out.close();
		
	}

	
	
	public double verify_table_calculations_contract_types_page_cp_hire(WebDriver driver, String vehicle_price_copied,
			WebElement acq_contractTypes_table_calculation_basic_paint_price,
			WebElement acq_contractTypes_table_calculation_basic_options_price,
			WebElement acq_contractTypes_calculation_table_discount,
			WebElement acq_contractTypes_calculation_table_additional_discount, String sheet_name) throws IOException {

		ExplicitWait.visibleElement(driver, acq_contractTypes_table_calculation_basic_paint_price, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_table_calculation_basic_options_price, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_calculation_table_discount, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_calculation_table_additional_discount, 30);
		String calculation_table_dicount_data = acq_contractTypes_calculation_table_discount.getText();
		String calculation_table_basic_add_discount_data = acq_contractTypes_calculation_table_additional_discount
				.getText();

		String discount[] = calculation_table_dicount_data.split("\\s+");
		String additional_discount[] = calculation_table_basic_add_discount_data.split("\\s+");

//**************First (Basic price) row data fetching and variable assigning  code***********************************************			 

		String newString2 = RemoveComma
				.of(acq_contractTypes_table_calculation_basic_paint_price.getText().trim().substring(2));

		String newString3 = RemoveComma
				.of(acq_contractTypes_table_calculation_basic_options_price.getText().trim().substring(2));

		double vehicle_basic_price = Double.parseDouble(vehicle_price_copied);
		LO.print("Getting Basic Price- Vehicle value is  =" + vehicle_price_copied);
		System.out.println("Getting Basic Price -Vehicle value is =" + vehicle_price_copied);
		double paint_basic_price = Double.parseDouble(newString2);
		LO.print("Getting Basic Price - Paint  value is  =" + newString2);
		System.out.println("GettingPrice - Paint value is  =" + newString2);
		double options_basic_price = Double.parseDouble(newString3);
		LO.print("Getting  Basic Price - Options value is   =" + newString3);
		System.out.println("Getting  Basic Price - Options  value is   =" + newString3);

//**************Second (Discount) row data fetching and variable assigning code**************************************************			
		ArrayList<String> arrDiscount = new ArrayList<String>();

		for (int i = 0; i < discount.length; i++) {
			if (discount[i].contains("Discount") || discount[i].contains("%")) {

			} else {
				arrDiscount.add(discount[i]);
			}
		}
		String fourth_cell = arrDiscount.get(0);
		String newString4 = RemoveComma.of(fourth_cell);

		String fifth_cell = arrDiscount.get(1);
		String newString5 = RemoveComma.of(fifth_cell);

		String sixth_cell = arrDiscount.get(2);
		String newString6 = RemoveComma.of(sixth_cell);

		double vehicle_discount = Double.parseDouble(newString4);

		LO.print("Getting Discount- Vehicle value is  =" + newString4);
		System.out.println("Getting Discount -Vehicle value is  =" + newString4);

		double paint_discount = Double.parseDouble(newString5);
		LO.print("Getting   Discount - Paint value is   =" + newString5);
		System.out.println("Getting  Discount - Paint value is  =" + newString5);

		double options_discount = Double.parseDouble(newString6);
		LO.print("Getting  Discount- Options value is   =" + newString6);
		System.out.println("Getting  Discount - Options value is  =" + newString6);

//***************Third (Additional Discount) row data fetching and variable assigning code**************************************************	
		ArrayList<String> arrAdditionalDiscount = new ArrayList<String>();

		for (int i = 0; i < additional_discount.length; i++) {
			if (additional_discount[i].contains("Additional") || additional_discount[i].contains("discount")
					|| additional_discount[i].contains("£")) {

			} else {
				arrAdditionalDiscount.add(additional_discount[i]);
			}
		}

		String seventh_cell = arrAdditionalDiscount.get(0);
		String newString7 = RemoveComma.of(seventh_cell);

		String eighth_cell = arrAdditionalDiscount.get(1);
		String newString8 = RemoveComma.of(eighth_cell);

		String nineth_cell = arrAdditionalDiscount.get(2);
		String newString9 = RemoveComma.of(nineth_cell);

		double vehicle_additional_discount = Double.parseDouble(newString7);
		LO.print("Getting Additional Discount- Vehicle value is  =" + newString7);
		System.out.println("Getting Additional Discount -Vehicle value is  =" + newString7);

		double paint_additional_discount = Double.parseDouble(newString8);
		LO.print("Getting  Additional Discount - paint value is  =" + newString8);
		System.out.println("Getting  Additional Discount - paint value is   =" + newString8);

		double options_additional_discount = Double.parseDouble(newString9);
		LO.print("Getting  Additional Discount - Options value is  =" + newString9);
		System.out.println("Getting  Additional Discount - Options value is  =" + newString9);

//****************** data fetching and variable assigning code Completed **************************************************	

//**************write this data to excel formula for calculating OTR calculation****************	

		LO.print("Writing OTR table values to excel sheet -Started ");
		System.out.println("Writing OTR table values to excel sheet -Started ");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(2).getCell(1).setCellValue(vehicle_basic_price);
		wb.getSheet(sheet_name).getRow(2).getCell(3).setCellValue(paint_basic_price);
		wb.getSheet(sheet_name).getRow(2).getCell(5).setCellValue(options_basic_price);
		wb.getSheet(sheet_name).getRow(3).getCell(1).setCellValue(vehicle_discount);
		wb.getSheet(sheet_name).getRow(3).getCell(3).setCellValue(paint_discount);
		wb.getSheet(sheet_name).getRow(3).getCell(5).setCellValue(options_discount);
		wb.getSheet(sheet_name).getRow(4).getCell(1).setCellValue(vehicle_additional_discount);
		wb.getSheet(sheet_name).getRow(4).getCell(3).setCellValue(paint_additional_discount);
		wb.getSheet(sheet_name).getRow(4).getCell(5).setCellValue(options_additional_discount);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		LO.print("Writing OTR table values to excel sheet -Completed ");
		System.out.println("Writing OTR table values to excel sheet -Completed ");

		LO.print("Reading Subtotal After Discount from excel sheet -Started ");
		System.out.println("Reading Subtotal After Discount from excel sheet -Started ");

		double subtotal_after_discount_excel = GetExcelFormulaValue.get_formula_value(8, 7, sheet_name);

		System.out.println("subtotal_after_discount_excel = " + subtotal_after_discount_excel);
		wb.close();
		out.close();

		LO.print("Reading Subtotal After Discount from excel sheet -Started ");
		System.out.println("Reading Subtotal After Discount from excel sheet -Started ");

		LO.print("Subtotal After Discount from excel sheet is =" + subtotal_after_discount_excel);
		System.out.println("Subtotal After Discount from excel sheet is =" + subtotal_after_discount_excel);
		return subtotal_after_discount_excel;

	}

	public double verify_table_calculations_contract_types_page_edited(WebDriver driver, String vehicle_price_copied,
			WebElement acq_contractTypes_table_calculation_basic_paint_price,
			WebElement acq_contractTypes_table_calculation_basic_options_price,
			WebElement acq_contractTypes_calculation_table_discount,
			WebElement acq_contractTypes_calculation_table_additional_discount, String sheet_name) throws IOException {

		ExplicitWait.visibleElement(driver, acq_contractTypes_table_calculation_basic_paint_price, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_table_calculation_basic_options_price, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_calculation_table_discount, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_calculation_table_additional_discount, 30);
		String calculation_table_dicount_data = acq_contractTypes_calculation_table_discount.getText();
		String calculation_table_basic_add_dicount_data = acq_contractTypes_calculation_table_additional_discount
				.getText();

		String discount[] = calculation_table_dicount_data.split("\\s+");
		String additional_discount[] = calculation_table_basic_add_dicount_data.split("\\s+");

//**************First (Basic price) row data fetching and variable assigning  code***********************************************			 

		String newString2 = RemoveComma
				.of(acq_contractTypes_table_calculation_basic_paint_price.getText().trim().substring(2));

		String newString3 = RemoveComma
				.of(acq_contractTypes_table_calculation_basic_options_price.getText().trim().substring(2));

		double vehicle_basic_price = Double.parseDouble(vehicle_price_copied);
		LO.print("Getting Basic Price- Vehicle value is  =" + vehicle_price_copied);
		System.out.println("Getting Basic Price -Vehicle value is =" + vehicle_price_copied);
		double paint_basic_price = Double.parseDouble(newString2);
		LO.print("Getting Basic Price - Paint  value is  =" + newString2);
		System.out.println("GettingPrice - Paint value is  =" + newString2);
		double options_basic_price = Double.parseDouble(newString3);
		LO.print("Getting  Basic Price - Options value is   =" + newString3);
		System.out.println("Getting  Basic Price - Options  value is   =" + newString3);

//**************Second (Discount) row data fetching and variable assigning code**************************************************			
		ArrayList<String> arrDiscount = new ArrayList<String>();

		for (int i = 0; i < discount.length; i++) {
			if (discount[i].contains("Discount") || discount[i].contains("%")) {

			} else {
				arrDiscount.add(discount[i]);
			}
		}
		String fourth_cell = arrDiscount.get(0);
		String newString4 = RemoveComma.of(fourth_cell);

		String fifth_cell = arrDiscount.get(1);
		String newString5 = RemoveComma.of(fifth_cell);

		String sixth_cell = arrDiscount.get(2);
		String newString6 = RemoveComma.of(sixth_cell);

		double vehicle_discount = Double.parseDouble(newString4);

		LO.print("Getting Discount- Vehicle value is  =" + newString4);
		System.out.println("Getting Discount -Vehicle value is  =" + newString4);

		double paint_discount = Double.parseDouble(newString5);
		LO.print("Getting   Discount - Paint value is   =" + newString5);
		System.out.println("Getting  Discount - Paint value is  =" + newString5);

		double options_discount = Double.parseDouble(newString6);
		LO.print("Getting  Discount- Options value is   =" + newString6);
		System.out.println("Getting  Discount - Options value is  =" + newString6);

//***************Third (Additional Discount) row data fetching and variable assigning code**************************************************	
		ArrayList<String> arrAdditionalDiscount = new ArrayList<String>();

		for (int i = 0; i < additional_discount.length; i++) {
			if (additional_discount[i].contains("Additional") || additional_discount[i].contains("discount")
					|| additional_discount[i].contains("£")) {

			} else {
				arrAdditionalDiscount.add(additional_discount[i]);
			}
		}

		String seventh_cell = arrAdditionalDiscount.get(0);
		String newString7 = RemoveComma.of(seventh_cell);

		String eighth_cell = arrAdditionalDiscount.get(1);
		String newString8 = RemoveComma.of(eighth_cell);

		String nineth_cell = arrAdditionalDiscount.get(2);
		String newString9 = RemoveComma.of(nineth_cell);

		double vehicle_additional_discount = Double.parseDouble(newString7);
		LO.print("Getting Additional Discount- Vehicle value is  =" + newString7);
		System.out.println("Getting Additional Discount -Vehicle value is  =" + newString7);

		double paint_additional_discount = Double.parseDouble(newString8);
		LO.print("Getting  Additional Discount - paint value is  =" + newString8);
		System.out.println("Getting  Additional Discount - paint value is   =" + newString8);

		double options_additional_discount = Double.parseDouble(newString9);
		LO.print("Getting  Additional Discount - Options value is  =" + newString9);
		System.out.println("Getting  Additional Discount - Options value is  =" + newString9);

//****************** data fetching and variable assigning code Completed **************************************************	

//**************write this data to excel formula for calculating OTR calculation****************	

		LO.print("Writing OTR table values to excel sheet -Started ");
		System.out.println("Writing OTR table values to excel sheet -Started ");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(2).getCell(1).setCellValue(vehicle_basic_price);
		wb.getSheet(sheet_name).getRow(2).getCell(2).setCellValue(paint_basic_price);
		wb.getSheet(sheet_name).getRow(2).getCell(3).setCellValue(options_basic_price);
		wb.getSheet(sheet_name).getRow(3).getCell(1).setCellValue(vehicle_discount);
		wb.getSheet(sheet_name).getRow(3).getCell(2).setCellValue(paint_discount);
		wb.getSheet(sheet_name).getRow(3).getCell(3).setCellValue(options_discount);
		wb.getSheet(sheet_name).getRow(4).getCell(1).setCellValue(vehicle_additional_discount);
		wb.getSheet(sheet_name).getRow(4).getCell(2).setCellValue(paint_additional_discount);
		wb.getSheet(sheet_name).getRow(4).getCell(3).setCellValue(options_additional_discount);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		LO.print("Writing OTR table values to excel sheet -Completed ");
		System.out.println("Writing OTR table values to excel sheet -Completed ");

		LO.print("Reading Subtotal After Discount from excel sheet -Started ");
		System.out.println("Reading Subtotal After Discount from excel sheet -Started ");

		double subtotal_after_discount_excel = GetExcelFormulaValue.get_formula_value(8, 4, sheet_name);

		System.out.println("subtotal_after_discount_excel = " + subtotal_after_discount_excel);
		wb.close();
		out.close();

		LO.print("Reading Subtotal After Discount from excel sheet -Started ");
		System.out.println("Reading Subtotal After Discount from excel sheet -Started ");

		LO.print("Subtotal After Discount from excel sheet is =" + subtotal_after_discount_excel);
		System.out.println("Subtotal After Discount from excel sheet is =" + subtotal_after_discount_excel);
		return subtotal_after_discount_excel;

	}

	public boolean verify_after_discount_calculations_contract_types_page(WebDriver driver,
			WebElement acq_contractTypes_calculation_table_basic_price,
			WebElement acq_contractTypes_calculation_table_discount,
			WebElement acq_contractTypes_calculation_table_additional_discount,
			WebElement acq_contractTypes_manufacturer_delivery_charges,
			WebElement acq_contractTypes_road_tax_first_year, WebElement acq_contractTypes_first_registration_fee,
			WebElement acq_contractTypes_rebate, WebElement acq_contractTypes_OTR_price, String sheet_name)
			throws IOException {

		obj_read_excel_calculation_page = new ReadExcelCalculation();

		ExplicitWait.visibleElement(driver, acq_contractTypes_manufacturer_delivery_charges, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_road_tax_first_year, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_first_registration_fee, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_rebate, 30);

		// get text from elements

		LO.print("*********Calculations for On Road Price has been started***********");
		System.out.println("Calculations for On Road Price has been started");

		LO.print("Started getting On screen values from after discount table ");
		System.out.println("Started getting On screen values from after discount table ");

		// converting String to double

		double manufacture_delivery_charges_converted = Double.parseDouble(
				RemoveComma.of(acq_contractTypes_manufacturer_delivery_charges.getText().trim().substring(2)));
		double road_tax_first_year_converted = Double
				.parseDouble(acq_contractTypes_road_tax_first_year.getText().trim().substring(2));
		double first_registration_fee_converted = Double
				.parseDouble(acq_contractTypes_first_registration_fee.getText().trim().substring(2));
		double rebate_converted = Double.parseDouble(acq_contractTypes_rebate.getText().trim().substring(2));

		LO.print("Manufacture_delivery_charges =" + manufacture_delivery_charges_converted);
		System.out.println("Manufacture_delivery_charges =" + manufacture_delivery_charges_converted);

		LO.print("Road_tax_first_year =" + road_tax_first_year_converted);
		System.out.println("Road_tax_first_year =" + road_tax_first_year_converted);

		LO.print("First_registration_fee =" + first_registration_fee_converted);
		System.out.println("First_registration_fee =" + first_registration_fee_converted);

		LO.print("Rebate =" + rebate_converted);
		System.out.println("Rebate =" + rebate_converted);

		// excel code for setting up values to excel for calculation
		LO.print("Writing After discount values to excel has been started");
		System.out.println("Writing After discount values to excel has been started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		// wb.getSheet(sheet_name).getRow(8).getCell(4).setCellValue(subtotal_price);
		wb.getSheet(sheet_name).getRow(9).getCell(4).setCellValue(manufacture_delivery_charges_converted);
		wb.getSheet(sheet_name).getRow(12).getCell(4).setCellValue(road_tax_first_year_converted);
		wb.getSheet(sheet_name).getRow(13).getCell(4).setCellValue(first_registration_fee_converted);
		wb.getSheet(sheet_name).getRow(15).getCell(4).setCellValue(rebate_converted);
		wb.getSheet(sheet_name).getRow(19).getCell(4).setCellValue(0);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing After discount values to excel has been completed");
		System.out.println("Writing After discount values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading On Road Price for invoice from excel has been Started");
		System.out.println("Reading On Road Price for invoice from excel has been Started");

		double on_the_road_price_for_invoice = GetExcelFormulaValue.get_formula_value(14, 4, sheet_name);

		LO.print("Reading On Road Price for invoice from excel has been completed");
		System.out.println("Reading On Road Price for invoice from excel has been completed");

		double expected_on_the_road_price_for_invoice = Math.round(on_the_road_price_for_invoice * 100.0) / 100.0;

		LO.print("Expected on the road price from excel is =" + expected_on_the_road_price_for_invoice);
		System.out.println("Expected on the road price from excel is =" + expected_on_the_road_price_for_invoice);

		ExplicitWait.visibleElement(driver, acq_contractTypes_OTR_price, 40);
		String otr_price = acq_contractTypes_OTR_price.getText().trim().substring(2);

		String otr_price_actual = RemoveComma.of(otr_price);

		LO.print("Actual on the road price from screen is =" + otr_price_actual);
		System.out.println("Actual on the road price from screen is =" + otr_price_actual);

		double otr_price_actual_converted = Double.parseDouble(otr_price_actual);
		boolean flag = false;
		double diff = Difference.of_two_Double_Values(expected_on_the_road_price_for_invoice,
				otr_price_actual_converted);
		if (diff < 0.2) {
			flag = true;
		}
//		String expected_on_the_road_price_for_invoice_converted= String.valueOf(expected_on_the_road_price_for_invoice);
//		boolean flag=otr_price_actual.equals(expected_on_the_road_price_for_invoice_converted);

		return flag;
	}

	public boolean verify_after_discount_calculations_contract_types_page_cp_hire(WebDriver driver,
			WebElement acq_contractTypes_calculation_table_basic_price,
			WebElement acq_contractTypes_calculation_table_discount,
			WebElement acq_contractTypes_calculation_table_additional_discount,
			WebElement acq_contractTypes_manufacturer_delivery_charges,
			WebElement acq_contractTypes_road_tax_first_year, WebElement acq_contractTypes_first_registration_fee,
			WebElement acq_contractTypes_rebate, WebElement acq_contractTypes_OTR_price, String sheet_name)
			throws IOException {

		obj_read_excel_calculation_page = new ReadExcelCalculation();

		ExplicitWait.visibleElement(driver, acq_contractTypes_manufacturer_delivery_charges, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_road_tax_first_year, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_first_registration_fee, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_rebate, 30);

		// get text from elements

		LO.print("*********Calculations for On Road Price has been started***********");
		System.out.println("Calculations for On Road Price has been started");

		LO.print("Started getting On screen values from after discount table ");
		System.out.println("Started getting On screen values from after discount table ");

		String manufacture_delivery_charges = acq_contractTypes_manufacturer_delivery_charges.getText().trim()
				.substring(2);

		String road_tax_first_year = acq_contractTypes_road_tax_first_year.getText().trim().substring(2);

		String first_registration_fee = acq_contractTypes_first_registration_fee.getText().trim().substring(2);
		String rebate = acq_contractTypes_rebate.getText().trim().substring(2);

		double manufacture_delivery_charges_converted = Double.parseDouble(manufacture_delivery_charges);
		double road_tax_first_year_converted = Double.parseDouble(road_tax_first_year);
		double first_registration_fee_converted = Double.parseDouble(first_registration_fee);
		double rebate_converted = Double.parseDouble(rebate);

		LO.print("Manufacture_delivery_charges =" + manufacture_delivery_charges);
		System.out.println("Manufacture_delivery_charges =" + manufacture_delivery_charges);

		LO.print("Road_tax_first_year =" + road_tax_first_year);
		System.out.println("Road_tax_first_year =" + road_tax_first_year);

		LO.print("First_registration_fee =" + first_registration_fee);
		System.out.println("First_registration_fee =" + first_registration_fee);

		LO.print("Rebate =" + rebate);
		System.out.println("Rebate =" + rebate);

		// excel code for setting up values to excel for calculation
		LO.print("Writing After discount values to excel has been started");
		System.out.println("Writing After discount values to excel has been started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		// wb.getSheet(sheet_name).getRow(8).getCell(4).setCellValue(subtotal_price);
		wb.getSheet(sheet_name).getRow(9).getCell(7).setCellValue(manufacture_delivery_charges_converted);
		wb.getSheet(sheet_name).getRow(12).getCell(7).setCellValue(road_tax_first_year_converted);
		wb.getSheet(sheet_name).getRow(13).getCell(7).setCellValue(first_registration_fee_converted);
		wb.getSheet(sheet_name).getRow(15).getCell(7).setCellValue(rebate_converted);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing After discount values to excel has been completed");
		System.out.println("Writing After discount values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading On Road Price for invoice from excel has been Started");
		System.out.println("Reading On Road Price for invoice from excel has been Started");

		double on_the_road_price_for_invoice = GetExcelFormulaValue.get_formula_value(14, 7, sheet_name);

		LO.print("Reading On Road Price for invoice from excel has been completed");
		System.out.println("Reading On Road Price for invoice from excel has been completed");

		double expected_on_the_road_price_for_invoice = Math.round(on_the_road_price_for_invoice * 100.0) / 100.0;

		LO.print("Expected on the road price from excel is =" + expected_on_the_road_price_for_invoice);
		System.out.println("Expected on the road price from excel is =" + expected_on_the_road_price_for_invoice);

		ExplicitWait.visibleElement(driver, acq_contractTypes_OTR_price, 40);
		String otr_price = acq_contractTypes_OTR_price.getText().trim().substring(2);

		String otr_price_actual = RemoveComma.of(otr_price);

		LO.print("Actual on the road price from screen is =" + otr_price_actual);
		System.out.println("Actual on the road price from screen is =" + otr_price_actual);

		double otr_price_actual_converted = Double.parseDouble(otr_price_actual);
		boolean flag = false;
		double diff = Difference.of_two_Double_Values(expected_on_the_road_price_for_invoice,
				otr_price_actual_converted);
		if (diff < 0.2) {
			flag = true;
		}
//		String expected_on_the_road_price_for_invoice_converted= String.valueOf(expected_on_the_road_price_for_invoice);
//		boolean flag=otr_price_actual.equals(expected_on_the_road_price_for_invoice_converted);

		return flag;
	}

	public void write_vehicle_cost_Price_to_excel_for_used_car(double vehicle_cost_price,
			double rfl, String sheet_name) throws IOException {

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(8).getCell(1).setCellValue(vehicle_cost_price);
		wb.getSheet(sheet_name).getRow(10).getCell(1).setCellValue(0);
		wb.getSheet(sheet_name).getRow(12).getCell(1).setCellValue(rfl);

		if (sheet_name.contains("Formula1") || sheet_name.contains("BCH (Formula 3)")) {
			wb.getSheet(sheet_name).getRow(101).getCell(1).setCellValue(0);
			wb.getSheet(sheet_name).getRow(101).getCell(3).setCellValue(0);
		} else {
			wb.getSheet(sheet_name).getRow(107).getCell(1).setCellValue(0);
			wb.getSheet(sheet_name).getRow(107).getCell(3).setCellValue(0);
		}

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
	}

	public boolean verify_after_discount_calculations_contract_types_page_edited(WebDriver driver,
			WebElement acq_contractTypes_manufacturer_delivery_charges, String roadTaxForFirstYear,
			WebElement acq_contractTypes_first_registration_fee, WebElement acq_contractTypes_rebate,
			WebElement acq_contractTypes_OTR_price, String sheet_name) throws IOException {

		obj_read_excel_calculation_page = new ReadExcelCalculation();

		ExplicitWait.visibleElement(driver, acq_contractTypes_manufacturer_delivery_charges, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_first_registration_fee, 30);
		ExplicitWait.visibleElement(driver, acq_contractTypes_rebate, 30);

		// get text from elements

		LO.print("*********Calculations for On Road Price has been started***********");
		System.out.println("Calculations for On Road Price has been started");

		LO.print("Started getting On screen values from after discount table ");
		System.out.println("Started getting On screen values from after discount table ");

		String manufacture_delivery_charges = acq_contractTypes_manufacturer_delivery_charges.getText().trim()
				.substring(2);

		String first_registration_fee = acq_contractTypes_first_registration_fee.getText().trim().substring(2);
		String rebate = acq_contractTypes_rebate.getText().trim().substring(2);

		double manufacture_delivery_charges_converted = Double.parseDouble(manufacture_delivery_charges);

		double first_registration_fee_converted = Double.parseDouble(first_registration_fee);
		double rebate_converted = Double.parseDouble(rebate);

		LO.print("Manufacture_delivery_charges =" + manufacture_delivery_charges);
		System.out.println("Manufacture_delivery_charges =" + manufacture_delivery_charges);

		LO.print("Road_tax_first_year_from_test_data =" + roadTaxForFirstYear);
		System.out.println("Road_tax_first_year_from_test_data =" + roadTaxForFirstYear);

		LO.print("First_registration_fee =" + first_registration_fee);
		System.out.println("First_registration_fee =" + first_registration_fee);

		LO.print("Rebate =" + rebate);
		System.out.println("Rebate =" + rebate);

		// excel code for setting up values to excel for calculation
		LO.print("Writing After discount values to excel has been started");
		System.out.println("Writing After discount values to excel has been started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		// wb.getSheet(sheet_name).getRow(8).getCell(4).setCellValue(subtotal_price);
		wb.getSheet(sheet_name).getRow(9).getCell(4).setCellValue(manufacture_delivery_charges_converted);
		wb.getSheet(sheet_name).getRow(12).getCell(4).setCellValue(roadTaxForFirstYear);
		wb.getSheet(sheet_name).getRow(13).getCell(4).setCellValue(first_registration_fee_converted);
		wb.getSheet(sheet_name).getRow(15).getCell(4).setCellValue(rebate_converted);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing After discount values to excel has been completed");
		System.out.println("Writing After discount values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading On Road Price for invoice from excel has been Started");
		System.out.println("Reading On Road Price for invoice from excel has been Started");

		double on_the_road_price_for_invoice = GetExcelFormulaValue.get_formula_value(14, 4, sheet_name);

		LO.print("Reading On Road Price for invoice from excel has been completed");
		System.out.println("Reading On Road Price for invoice from excel has been completed");

		double expected_on_the_road_price_for_invoice = Math.round(on_the_road_price_for_invoice * 100.0) / 100.0;

		LO.print("Expected on the road price from excel is =" + expected_on_the_road_price_for_invoice);
		System.out.println("Expected on the road price from excel is =" + expected_on_the_road_price_for_invoice);

		ExplicitWait.visibleElement(driver, acq_contractTypes_OTR_price, 40);
		String otr_price = acq_contractTypes_OTR_price.getText().trim().substring(2);

		String otr_price_actual = RemoveComma.of(otr_price);

		LO.print("Actual on the road price from screen is =" + otr_price_actual);
		System.out.println("Actual on the road price from screen is =" + otr_price_actual);

		double otr_price_actual_converted = Double.parseDouble(otr_price_actual);
		boolean flag = false;
		double diff = Difference.of_two_Double_Values(expected_on_the_road_price_for_invoice,
				otr_price_actual_converted);
		if (diff < 0.2) {
			flag = true;
		}
//		String expected_on_the_road_price_for_invoice_converted= String.valueOf(expected_on_the_road_price_for_invoice);
//		boolean flag=otr_price_actual.equals(expected_on_the_road_price_for_invoice_converted);

		return flag;
	}

	public boolean verify_holding_cost_before_editing_cap_values_without_maintenance(WebDriver driver,
			WebElement holding_cost_summary_terms, WebElement holding_cost_summary_mileage,
			WebElement holding_cost_summary_residual_value_used, WebElement total_monthly_holding_cost,
			String residual_value_used, String percentage_cap_residual_value_used, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {

		LO.print("");
		System.out.println("");

		LO.print("Verifying Holding Cost Based on CAP data");
		System.out.println("Verifying Holding Cost Based on CAP data");

		ExplicitWait.visibleElement(driver, holding_cost_summary_terms, 60);
		double duration = Double.parseDouble(holding_cost_summary_terms.getText().substring(0, 2));
		ExplicitWait.visibleElement(driver, holding_cost_summary_mileage, 60);

		double annual_mileage = Double.parseDouble(RemoveComma.of(holding_cost_summary_mileage.getText()));

		ExplicitWait.visibleElement(driver, holding_cost_summary_residual_value_used, 50);

		double used_residual_value = Double
				.parseDouble(RemoveComma.of(holding_cost_summary_residual_value_used.getText().substring(2)));

		LO.print("Getting on screen values from Holding Cost Page");
		System.out.println("Getting on screen values from Holding Cost Page");

		LO.print("Duration(Terms) =" + duration);
		System.out.println("Duration(Terms) =" + duration);

		LO.print("Annual_mileage =" + annual_mileage);
		System.out.println("Annual_mileage =" + annual_mileage);

		LO.print("Cap_residual_value_from_screen =" + used_residual_value);
		System.out.println("Cap_residual_value_from_screen =" + used_residual_value);

		LO.print("Writing Holding Cost Summary values to excel has been started");
		System.out.println("Writing Holding Cost Summary values to excel has been started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(25).getCell(1).setCellValue("NO");
		wb.getSheet(sheet_name).getRow(28).getCell(7).setCellValue(duration);
		wb.getSheet(sheet_name).getRow(29).getCell(7).setCellValue(annual_mileage);
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value * 1.2);
		} else {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value);

		}
		wb.getSheet(sheet_name).getRow(31).getCell(8).setCellValue(0);
		wb.getSheet(sheet_name).getRow(34).getCell(7).setCellValue(Double.parseDouble(prop.getProperty("base_rate")));
//		wb.getSheet(sheet_name).getRow(39).getCell(0).setCellValue(used_residual_value);
		wb.getSheet(sheet_name).getRow(44).getCell(0).setCellValue(100);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel has been started");
		System.out.println("Reading Monthly Holding Cost value from excel has been started");

		double monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		LO.print("Reading Monthly Holding Cost value from excel has been completed");
		System.out.println("Reading Monthly Holding Cost value from excel has been completed");

		String total_monthly_holding_cost_from_screen = RemoveComma
				.of(total_monthly_holding_cost.getText().substring(2));

		LO.print("Total_monthly_holding_cost_from_screen =" + total_monthly_holding_cost_from_screen);
		System.out.println("Total_monthly_holding_cost_from_screen " + total_monthly_holding_cost_from_screen);

		LO.print("Total_monthly_holding_cost_from_excel =" + monthly_holding_cost_expected);
		System.out.println("Total_monthly_holding_cost_from_excel " + monthly_holding_cost_expected);

		double total_monthly_holding_cost_actual1 = Double.parseDouble(total_monthly_holding_cost_from_screen);
		double diff = Difference.of_two_Double_Values(total_monthly_holding_cost_actual1,
				monthly_holding_cost_expected);
		boolean flag = false;
		if (diff < 0.2) {
			flag = true;
			LO.print("Total Monthly Holding Cost based on CAP data is Verified and Found OK");
			System.out.println("Total Monthly Holding Cost based on CAP data is Verified and Found OK");

		} else {
			LO.print("Total Monthly Holding Cost based on CAP data is Verified and Found Wrong");
			System.err.println("Total Monthly Holding Cost based on CAP data is Verified and Found Wrong");

		}

		return flag;
	}

	public boolean edit_percentage_residual_verify_holding_cost_without_maintenance(WebDriver driver,
			WebElement holding_cost_summary_terms, WebElement holding_cost_summary_mileage,
			WebElement holding_cost_summary_residual_value_used, WebElement total_monthly_holding_cost,
			String residual_value_used, String percentage_cap_residual_value_used, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {

		LO.print("");
		System.out.println("");

		LO.print("Editing % CAP Residual Value used");
		System.out.println("Editing % CAP Residual Value used");

		ExplicitWait.visibleElement(driver, holding_cost_summary_terms, 60);
		double duration = Double.parseDouble(holding_cost_summary_terms.getText().substring(0, 2));
		ExplicitWait.visibleElement(driver, holding_cost_summary_mileage, 60);

		double annual_mileage = Double.parseDouble(RemoveComma.of(holding_cost_summary_mileage.getText()));

		ExplicitWait.visibleElement(driver, holding_cost_summary_residual_value_used, 50);

		double used_residual_value = Double
				.parseDouble(RemoveComma.of(holding_cost_summary_residual_value_used.getText().substring(2)));

		LO.print("Changing % CAP Residual Value (from test  data) to " + percentage_cap_residual_value_used);
		System.out.println("Changing % CAP Residual Value (from test  data) to " + percentage_cap_residual_value_used);

		LO.print("Writing Holding Cost Summary values to excel has been started");
		System.out.println("Writing Holding Cost Summary values to excel has been started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(25).getCell(1).setCellValue("NO");
		wb.getSheet(sheet_name).getRow(28).getCell(7).setCellValue(duration);
		wb.getSheet(sheet_name).getRow(29).getCell(7).setCellValue(annual_mileage);
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value * 1.2);
		} else {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value);

		}
		wb.getSheet(sheet_name).getRow(31).getCell(8).setCellValue(0);
//		wb.getSheet(sheet_name).getRow(39).getCell(0).setCellValue(used_residual_value);
		wb.getSheet(sheet_name).getRow(44).getCell(0).setCellValue(percentage_cap_residual_value_used);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel has been started");
		System.out.println("Reading Monthly Holding Cost value from excel has been started");

		double monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		LO.print("Reading Monthly Holding Cost value from excel has been completed");
		System.out.println("Reading Monthly Holding Cost value from excel has been completed");

		String total_monthly_holding_cost_from_screen = RemoveComma
				.of(total_monthly_holding_cost.getText().substring(2));

		LO.print("Total_monthly_holding_cost_from_screen =" + total_monthly_holding_cost_from_screen);
		System.out.println("Total_monthly_holding_cost_from_screen " + total_monthly_holding_cost_from_screen);

		LO.print("Total_monthly_holding_cost_from_excel =" + monthly_holding_cost_expected);
		System.out.println("Total_monthly_holding_cost_from_excel " + monthly_holding_cost_expected);

		double total_monthly_holding_cost_actual1 = Double.parseDouble(total_monthly_holding_cost_from_screen);
		double diff = Difference.of_two_Double_Values(total_monthly_holding_cost_actual1,
				monthly_holding_cost_expected);
		boolean flag = false;
		if (diff < 0.2) {
			flag = true;

			LO.print("Total Monthly Holding Cost After Editing % Residual Value is Verified and Found OK");
			System.out.println("Total Monthly Holding Cost After Editing % Residual Value is Verified and Found OK");
		}

		else {
			LO.print("Total Monthly Holding Cost After Editing % Residual Value is Verified and Found Wrong");
			System.err.println("Total Monthly Holding Cost After Editing % Residual Value is Verified and Found Wrong");

		}

		return flag;
	}

	public boolean edit_residual_value_used_then_verify_holding_cost_without_maintenance(WebDriver driver,
			WebElement holding_cost_summary_terms, WebElement holding_cost_summary_mileage,
			WebElement holding_cost_summary_residual_value_used, WebElement total_monthly_holding_cost,
			String residual_value_used_from_excel, String percentage_cap_residual_value_used, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {

		LO.print("");
		System.out.println("");

		LO.print("Editing Residual value used");
		System.out.println("Editing Residual value used");

		ExplicitWait.visibleElement(driver, holding_cost_summary_terms, 60);
		double duration = Double.parseDouble(holding_cost_summary_terms.getText().substring(0, 2));
		ExplicitWait.visibleElement(driver, holding_cost_summary_mileage, 60);

		double annual_mileage = Double.parseDouble(RemoveComma.of(holding_cost_summary_mileage.getText()));

//		ExplicitWait.visibleElement(driver, holding_cost_summary_residual_value_used, 50);
//
//		double used_residual_value = Double
//				.parseDouble(RemoveComma.of(holding_cost_summary_residual_value_used.getText().substring(2)));

		double residual_value_test_data = Double.parseDouble(residual_value_used_from_excel);

		LO.print("Changing Residual value used to =" + residual_value_used_from_excel);
		System.out.println("Changing Residual value used to =" + residual_value_used_from_excel);

		LO.print("Writing Holding Cost Summary values to excel has been started");
		System.out.println("Writing Holding Cost Summary values to excel has been started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(25).getCell(1).setCellValue("NO");
		wb.getSheet(sheet_name).getRow(28).getCell(7).setCellValue(duration);
		wb.getSheet(sheet_name).getRow(29).getCell(7).setCellValue(annual_mileage);

		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(residual_value_test_data * 1.2);
		} else {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(residual_value_test_data);

		}

		wb.getSheet(sheet_name).getRow(31).getCell(8).setCellValue(0);
		wb.getSheet(sheet_name).getRow(44).getCell(0).setCellValue(100);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel has been started");
		System.out.println("Reading Monthly Holding Cost value from excel has been started");

		double monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		LO.print("Reading Monthly Holding Cost value from excel has been completed");
		System.out.println("Reading Monthly Holding Cost value from excel has been completed");

		String total_monthly_holding_cost_from_screen = RemoveComma
				.of(total_monthly_holding_cost.getText().substring(2));

		LO.print("Total_monthly_holding_cost_from_screen =" + total_monthly_holding_cost_from_screen);
		System.out.println("Total_monthly_holding_cost_from_screen " + total_monthly_holding_cost_from_screen);

		LO.print("Total_monthly_holding_cost_from_excel =" + monthly_holding_cost_expected);
		System.out.println("Total_monthly_holding_cost_from_excel " + monthly_holding_cost_expected);

		double total_monthly_holding_cost_actual1 = Double.parseDouble(total_monthly_holding_cost_from_screen);
		double diff = Difference.of_two_Double_Values(total_monthly_holding_cost_actual1,
				monthly_holding_cost_expected);
		boolean flag = false;
		if (diff < 0.2) {
			flag = true;

			LO.print("Total Monthly Holding Cost After Editing Residual Value is Verified and Found OK");
			System.out.println("Total Monthly Holding Cost After Editing Residual Value is Verified and Found OK");
		}

		else {
			LO.print("Total Monthly Holding Cost After Editing Residual Value is Verified and Found Wrong");
			System.err.println("Total Monthly Holding Cost After Editing Residual Value is Verified and Found Wrong");

		}

		return flag;
	}

	public double verify_holding_cost_after_adding_funder_without_maintenance(String term, String milesPerAnnum,
			String monthlyFinanceRental, String monthlyMaintenanceRental, String finalBallonPayment, String documentFee,
			String pencePerExcessMileFinance, String pencePerExcessMileMaintenance,
			String percentageOfSaleProceedToCustomer, String secondaryHirePeriodRental, String sheet_name)
			throws IOException, InterruptedException {

		LO.print("***********Holding Cost Calculations has been Started*************");
		System.out.println("***********Holding Cost Calculations has been Started*************");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(31).getCell(0).setCellValue(" Monthly in advance ");
		wb.getSheet(sheet_name).getRow(34).getCell(1).setCellValue(Double.parseDouble(term));
		wb.getSheet(sheet_name).getRow(34).getCell(3).setCellValue(Double.parseDouble(milesPerAnnum));
		wb.getSheet(sheet_name).getRow(37).getCell(1).setCellValue("NO");
		wb.getSheet(sheet_name).getRow(37).getCell(3).setCellValue(monthlyFinanceRental);
		wb.getSheet(sheet_name).getRow(40).getCell(0).setCellValue(monthlyMaintenanceRental);
		wb.getSheet(sheet_name).getRow(40).getCell(3).setCellValue(finalBallonPayment);
		wb.getSheet(sheet_name).getRow(43).getCell(0).setCellValue(documentFee);
		wb.getSheet(sheet_name).getRow(43).getCell(1).setCellValue(pencePerExcessMileFinance);
		wb.getSheet(sheet_name).getRow(43).getCell(3).setCellValue(pencePerExcessMileMaintenance);
		wb.getSheet(sheet_name).getRow(46).getCell(1).setCellValue(percentageOfSaleProceedToCustomer);
		wb.getSheet(sheet_name).getRow(46).getCell(3).setCellValue(secondaryHirePeriodRental);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel");
		System.out.println("Reading Monthly Holding Cost value from excel");

		return GetExcelFormulaValue.get_formula_value(57, 1, sheet_name);

	}

	public double verify_holding_cost_after_adding_funder_without_maintenance_for_hpnr_bch_pch(String term,
			String milesPerAnnum, String monthlyPayment, String finalBalloonPayment, String documentFee,
			String sheet_name) throws IOException, InterruptedException {

		LO.print("***********Holding Cost Calculations has been Started*************");
		System.out.println("***********Holding Cost Calculations has been Started*************");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(31).getCell(0).setCellValue(" Monthly in advance ");
		wb.getSheet(sheet_name).getRow(34).getCell(1).setCellValue(Double.parseDouble(term));
		wb.getSheet(sheet_name).getRow(34).getCell(3).setCellValue(Double.parseDouble(milesPerAnnum));
		wb.getSheet(sheet_name).getRow(37).getCell(1).setCellValue("NO");
		wb.getSheet(sheet_name).getRow(37).getCell(3).setCellValue(monthlyPayment);
		wb.getSheet(sheet_name).getRow(40).getCell(0).setCellValue(0);
		wb.getSheet(sheet_name).getRow(43).getCell(0).setCellValue(documentFee);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel");
		System.out.println("Reading Monthly Holding Cost value from excel");

		return GetExcelFormulaValue.get_formula_value(57, 1, sheet_name);

	}

	public double verify_holding_cost_after_adding_funder_without_maintenance_for_cp_bch_pch(String term,
			String milesPerAnnum, String cashDeposit, String monthlyPayment, String optionalFinalPayment,
			String optionToPurchaseFee, String pencePerExcessMileFinance, String documentFee, String sheet_name)
			throws IOException, InterruptedException {

		LO.print("***********Holding Cost Calculations has been Started*************");
		System.out.println("***********Holding Cost Calculations has been Started*************");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(31).getCell(0).setCellValue(Double.parseDouble(term));
		wb.getSheet(sheet_name).getRow(31).getCell(1).setCellValue(Double.parseDouble(milesPerAnnum));
		wb.getSheet(sheet_name).getRow(34).getCell(1).setCellValue(cashDeposit);
		wb.getSheet(sheet_name).getRow(37).getCell(0).setCellValue(documentFee);
		wb.getSheet(sheet_name).getRow(37).getCell(3).setCellValue(monthlyPayment);
		wb.getSheet(sheet_name).getRow(40).getCell(0).setCellValue(optionalFinalPayment);
		wb.getSheet(sheet_name).getRow(40).getCell(1).setCellValue(optionToPurchaseFee);
		wb.getSheet(sheet_name).getRow(43).getCell(0).setCellValue(0);
		wb.getSheet(sheet_name).getRow(43).getCell(1).setCellValue(pencePerExcessMileFinance);
		wb.getSheet(sheet_name).getRow(43).getCell(3).setCellValue(0);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel");
		System.out.println("Reading Monthly Holding Cost value from excel");

		return GetExcelFormulaValue.get_formula_value(57, 1, sheet_name);

	}

	public double verify_holding_cost_after_adding_funder_with_maintenance_for_cp_bch_pch(String term,
			String milesPerAnnum, String cashDeposit, String monthlyPayment, String monthlyMaintenancePayment,
			String optionalFinalPayment, String optionToPurchaseFee, String pencePerExcessMileFinance,
			String pencePerExcessMileMaintenance, String documentFee, String sheet_name)
			throws IOException, InterruptedException {

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(31).getCell(0).setCellValue(Double.parseDouble(term));
		wb.getSheet(sheet_name).getRow(31).getCell(1).setCellValue(Double.parseDouble(milesPerAnnum));
		wb.getSheet(sheet_name).getRow(34).getCell(1).setCellValue(cashDeposit);
		wb.getSheet(sheet_name).getRow(37).getCell(0).setCellValue(documentFee);
		wb.getSheet(sheet_name).getRow(37).getCell(3).setCellValue(monthlyPayment);
		wb.getSheet(sheet_name).getRow(40).getCell(0).setCellValue(optionalFinalPayment);
		wb.getSheet(sheet_name).getRow(40).getCell(1).setCellValue(optionToPurchaseFee);
		wb.getSheet(sheet_name).getRow(43).getCell(0).setCellValue(monthlyMaintenancePayment);
		wb.getSheet(sheet_name).getRow(43).getCell(1).setCellValue(pencePerExcessMileFinance);
		wb.getSheet(sheet_name).getRow(43).getCell(3).setCellValue(pencePerExcessMileMaintenance);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel");
		System.out.println("Reading Monthly Holding Cost value from excel");

		return GetExcelFormulaValue.get_formula_value(57, 1, sheet_name);

	}

	public double verify_holding_cost_after_adding_funder_with_maintenance_for_hpnr_bch_pch(String term,
			String milesPerAnnum, String monthlyPayment, String capMonthlyMaintValue, String finalBalloonPayment,
			String documentFee, String sheet_name) throws IOException, InterruptedException {

		LO.print("***********Holding Cost Calculations has been Started*************");
		System.out.println("***********Holding Cost Calculations has been Started*************");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(31).getCell(0).setCellValue(" Monthly in advance ");
		wb.getSheet(sheet_name).getRow(34).getCell(1).setCellValue(Double.parseDouble(term));
		wb.getSheet(sheet_name).getRow(34).getCell(3).setCellValue(Double.parseDouble(milesPerAnnum));
		wb.getSheet(sheet_name).getRow(37).getCell(1).setCellValue("YES");
		wb.getSheet(sheet_name).getRow(37).getCell(3).setCellValue(monthlyPayment);
		wb.getSheet(sheet_name).getRow(40).getCell(0).setCellValue(capMonthlyMaintValue);
		wb.getSheet(sheet_name).getRow(43).getCell(0).setCellValue(documentFee);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel");
		System.out.println("Reading Monthly Holding Cost value from excel");

		return GetExcelFormulaValue.get_formula_value(57, 1, sheet_name);

	}

	public double verify_holding_cost_after_adding_funder_with_maintenance(String term, String milesPerAnnum,
			String monthlyFinanceRental, String monthlyMaintenanceRental, String finalBallonPayment, String documentFee,
			String pencePerExcessMileFinance, String pencePerExcessMileMaintenance,
			String percentageOfSaleProceedToCustomer, String secondaryHirePeriodRental, String sheet_name)
			throws IOException, InterruptedException {

		LO.print("***********Holding Cost Calculations has been Started*************");
		System.out.println("***********Holding Cost Calculations has been Started*************");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(31).getCell(0).setCellValue(" Monthly in advance ");
		wb.getSheet(sheet_name).getRow(34).getCell(1).setCellValue(Double.parseDouble(term));
		wb.getSheet(sheet_name).getRow(34).getCell(3).setCellValue(Double.parseDouble(milesPerAnnum));
		wb.getSheet(sheet_name).getRow(37).getCell(1).setCellValue("YES");
		wb.getSheet(sheet_name).getRow(37).getCell(3).setCellValue(monthlyFinanceRental);
		wb.getSheet(sheet_name).getRow(40).getCell(0).setCellValue(monthlyMaintenanceRental);
		wb.getSheet(sheet_name).getRow(40).getCell(3).setCellValue(finalBallonPayment);
		wb.getSheet(sheet_name).getRow(43).getCell(0).setCellValue(documentFee);
		wb.getSheet(sheet_name).getRow(43).getCell(1).setCellValue(pencePerExcessMileFinance);
		wb.getSheet(sheet_name).getRow(43).getCell(3).setCellValue(pencePerExcessMileMaintenance);
		wb.getSheet(sheet_name).getRow(46).getCell(1).setCellValue(percentageOfSaleProceedToCustomer);
		wb.getSheet(sheet_name).getRow(46).getCell(3).setCellValue(secondaryHirePeriodRental);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel");
		System.out.println("Reading Monthly Holding Cost value from excel");

		return GetExcelFormulaValue.get_formula_value(57, 1, sheet_name);

	}

	
	public void write_holding_cost_with_maint_value_to_quote_save_excel_sheet(String holding_cost, String sheet_name)
			throws IOException, InterruptedException {

		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(7).getCell(1).setCellValue(holding_cost);

		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		wb.write(out);

		}
	
	
	public void write_holding_cost_without_maint_value_to_quote_save_excel_sheet(String holding_cost, String sheet_name)
			throws IOException, InterruptedException {

		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(7).getCell(0).setCellValue(holding_cost);

		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		wb.write(out);

		}


	
	public double verify_holding_cost_after_adding_funder_with_maintenance_for_hpnr_bch_pch(String term,
			String milesPerAnnum, String monthlyFinanceRental, String monthlyMaintenanceRental,
			String finalBallonPayment, String documentFee, String pencePerExcessMileFinance,
			String pencePerExcessMileMaintenance, String percentageOfSaleProceedToCustomer,
			String secondaryHirePeriodRental, String sheet_name) throws IOException, InterruptedException {

		LO.print("***********Holding Cost Calculations has been Started*************");
		System.out.println("***********Holding Cost Calculations has been Started*************");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(31).getCell(0).setCellValue(" Monthly in advance ");
		wb.getSheet(sheet_name).getRow(34).getCell(1).setCellValue(term);
		wb.getSheet(sheet_name).getRow(34).getCell(3).setCellValue(milesPerAnnum);
		wb.getSheet(sheet_name).getRow(37).getCell(1).setCellValue("YES");
		wb.getSheet(sheet_name).getRow(37).getCell(3).setCellValue(monthlyFinanceRental);
		wb.getSheet(sheet_name).getRow(40).getCell(0).setCellValue(monthlyMaintenanceRental);
		wb.getSheet(sheet_name).getRow(43).getCell(0).setCellValue(documentFee);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel");
		System.out.println("Reading Monthly Holding Cost value from excel");

		return GetExcelFormulaValue.get_formula_value(57, 1, sheet_name);

	}

	public void set_global_variables_to_excel(String sheet_name)
			throws IOException, NumberFormatException, ClassNotFoundException {
		// write / take global variables and set to excel sheet for calculation

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -started");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		// rfl values fakt on hotya baki sarv comment hotya

		wb.getSheet(sheet_name).getRow(61).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage")));
		
		
		if (sheet_name.contains("Use")) {

			wb.getSheet(sheet_name).getRow(63).getCell(1).setCellFormula("B60*B63");
			wb.getSheet(sheet_name).getRow(66).getCell(1).setCellFormula("B60*B66");

		} else {
			wb.getSheet(sheet_name).getRow(63).getCell(1).setCellFormula("B61*B63");
			wb.getSheet(sheet_name).getRow(66).getCell(1).setCellFormula("B61*B66");
	}
		wb.getSheet(sheet_name).getRow(64).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage_for_broker_vrb")));
		wb.getSheet(sheet_name).getRow(67).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("contingency_insurance_multiplier_holding_cost")));
		wb.getSheet(sheet_name).getRow(68).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("insurance_premium_tax")));
		wb.getSheet(sheet_name).getRow(70).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_cost_ex_vat")));
		wb.getSheet(sheet_name).getRow(71).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_subs_per_month_ex_vat")));

		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {

			

			wb.getSheet(sheet_name).getRow(73).getCell(1)
					.setCellValue(Double.parseDouble(prop.getProperty("additional_rfl_per_annum_LCV")));
			wb.getSheet(sheet_name).getRow(74).getCell(1).setCellValue(
					Double.parseDouble(prop.getProperty("additional_rfl_premium_vehicle_over_40k_per_annum_LCV")));
		} else {
			
			wb.getSheet(sheet_name).getRow(73).getCell(1)
					.setCellValue(Double.parseDouble(prop.getProperty("additional_rfl_per_annum")));
			wb.getSheet(sheet_name).getRow(74).getCell(1).setCellValue(
					Double.parseDouble(prop.getProperty("additional_rfl_premium_vehicle_over_40k_per_annum")));
		}

		wb.getSheet(sheet_name).getRow(78).getCell(1)

				.setCellValue(Double.parseDouble(prop.getProperty("disposal_costs")));
		wb.getSheet(sheet_name).getRow(79).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("delivery_and_collection_ex_vat")));

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		out.close();

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -completed");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -completed");
	}

	public void set_global_variables_for_used_car_to_excel(String sheet_name) throws IOException, NumberFormatException, ClassNotFoundException {
		// write / take global variables and set to excel sheet for calculation

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -started");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		// rfl values fakt on hotya baki sarv comment hotya

		wb.getSheet(sheet_name).getRow(61).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage")));
	
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("used")) {
			wb.getSheet(sheet_name).getRow(63).getCell(1).setCellFormula("B60*B63");
			wb.getSheet(sheet_name).getRow(66).getCell(1).setCellFormula("B60*B66");
		}
		else
		{
			wb.getSheet(sheet_name).getRow(63).getCell(1).setCellFormula("B61*B63");
			wb.getSheet(sheet_name).getRow(66).getCell(1).setCellFormula("B61*B66");

		}
		
		wb.getSheet(sheet_name).getRow(64).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage_for_broker_vrb")));
		wb.getSheet(sheet_name).getRow(67).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("contingency_insurance_multiplier_holding_cost")));
		wb.getSheet(sheet_name).getRow(68).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("insurance_premium_tax")));
		wb.getSheet(sheet_name).getRow(70).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_cost_ex_vat")));
		wb.getSheet(sheet_name).getRow(71).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_subs_per_month_ex_vat")));
		
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("used")) {
		
			if (sheet_name.contains("Formula1-FL_Used")) {wb.getSheet(sheet_name).getRow(73).getCell(1).setCellValue(0);}
			else{
				if(sheet_name.contains("Used_LCV"))
				{
				wb.getSheet(sheet_name).getRow(73).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("additional_rfl_per_annum_used_LCV")));
				}
				else
				{
					wb.getSheet(sheet_name).getRow(73).getCell(1)
					.setCellValue(Double.parseDouble(prop.getProperty("additional_rfl_per_annum")));
				}
			}				
		}
		else {
			
			wb.getSheet(sheet_name).getRow(73).getCell(1)
			.setCellValue(Double.parseDouble(prop.getProperty("additional_rfl_per_annum")));
			
		}
		
		
		wb.getSheet(sheet_name).getRow(74).getCell(1).setCellValue(
				Double.parseDouble(prop.getProperty("additional_rfl_premium_vehicle_over_40k_per_annum_used_car")));
		wb.getSheet(sheet_name).getRow(78).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("disposal_costs")));
		wb.getSheet(sheet_name).getRow(79).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("delivery_and_collection_ex_vat")));

		if (sheet_name.contains("Formula1") || sheet_name.contains("BCH (Formula 3)")) {
			wb.getSheet(sheet_name).getRow(101).getCell(1).setCellValue(0);
			wb.getSheet(sheet_name).getRow(101).getCell(3).setCellValue(0);
			wb.getSheet(sheet_name).getRow(98).getCell(3).setCellValue(0);
			wb.getSheet(sheet_name).getRow(111).getCell(4).setCellValue(0);
			wb.getSheet(sheet_name).getRow(112).getCell(4).setCellValue(0);
			
		} else {
			wb.getSheet(sheet_name).getRow(107).getCell(1).setCellValue(0);
			wb.getSheet(sheet_name).getRow(107).getCell(3).setCellValue(0);
			wb.getSheet(sheet_name).getRow(104).getCell(3).setCellValue(0);
			wb.getSheet(sheet_name).getRow(117).getCell(4).setCellValue(0);
			wb.getSheet(sheet_name).getRow(118).getCell(4).setCellValue(0);
		}

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		out.close();

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -completed");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -completed");
	}

	public void set_global_variables_to_excel_for_bch_pch_scenario(String sheet_name)
			throws IOException, ClassNotFoundException, FormulaParseException, IllegalStateException {
		// write / take global variables and set to excel sheet for calculation

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -started");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		// rfl values fakt on hotya baki sarv comment hotya

		wb.getSheet(sheet_name).getRow(61).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage")));
		wb.getSheet(sheet_name).getRow(64).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage_for_broker_vrb")));

		
	
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {

			wb.getSheet(sheet_name).getRow(66).getCell(1).setCellFormula("E15*B66");
			
			if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("used")) 
			{
				wb.getSheet(sheet_name).getRow(66).getCell(1).setCellFormula("B60*B66");
			}


		} else {
			wb.getSheet(sheet_name).getRow(66).getCell(1).setCellFormula("B61*B66");

		}
		
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("used")) 
		{
			wb.getSheet(sheet_name).getRow(63).getCell(1).setCellFormula("B60*B63");
			wb.getSheet(sheet_name).getRow(66).getCell(1).setCellFormula("B60*B66");
		}else {
			wb.getSheet(sheet_name).getRow(63).getCell(1).setCellFormula("B61*B63");
			wb.getSheet(sheet_name).getRow(66).getCell(1).setCellFormula("B61*B66");

		}

		wb.getSheet(sheet_name).getRow(67).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("contingency_insurance_multiplier_holding_cost")));
		wb.getSheet(sheet_name).getRow(68).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("insurance_premium_tax")));
		wb.getSheet(sheet_name).getRow(70).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_cost_ex_vat")));
		wb.getSheet(sheet_name).getRow(71).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_subs_per_month_ex_vat")));
		wb.getSheet(sheet_name).getRow(73).getCell(1).setCellValue(0);
		wb.getSheet(sheet_name).getRow(74).getCell(1).setCellValue(0);
		wb.getSheet(sheet_name).getRow(78).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("disposal_costs")));
		wb.getSheet(sheet_name).getRow(79).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("delivery_and_collection_ex_vat")));

		if (sheet_name.contains("Formula1") || sheet_name.contains("BCH (Formula 3)")
				|| sheet_name.contains("BCH-F3")) {
			wb.getSheet(sheet_name).getRow(101).getCell(1).setCellValue(0);
			wb.getSheet(sheet_name).getRow(101).getCell(3).setCellValue(0);
		} else {
			wb.getSheet(sheet_name).getRow(107).getCell(1).setCellValue(0);
			wb.getSheet(sheet_name).getRow(107).getCell(3).setCellValue(0);
		}

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		out.close();

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -completed");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -completed");
	}

	public void set_global_variables_to_excel_for_fl_bch_pch_scenario_with_funder_quote_addition(String sheet_name)
			throws IOException, ClassNotFoundException {
		// write / take global variables and set to excel sheet for calculation

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -started");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		// rfl values fakt on hotya baki sarv comment hotya

		wb.getSheet(sheet_name).getRow(67).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage")));
		wb.getSheet(sheet_name).getRow(70).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage_for_broker_vrb")));
		
		
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("CP_")) {
			wb.getSheet(sheet_name).getRow(69).getCell(1).setCellFormula("B66*B69");
		}
		
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {

			wb.getSheet(sheet_name).getRow(72).getCell(1).setCellFormula("B67*B72");
			
			if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("used_LCV")) {
				wb.getSheet(sheet_name).getRow(72).getCell(1).setCellFormula("B66*B72");
			}
			
					
			
          }
		else if(Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("used_car"))
		{
			wb.getSheet(sheet_name).getRow(72).getCell(1).setCellFormula("B66*B72");
		}
		
		else 
		{
			wb.getSheet(sheet_name).getRow(72).getCell(1).setCellFormula("B67*B72");
		}
		
		wb.getSheet(sheet_name).getRow(73).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("contingency_insurance_multiplier_holding_cost")));
		wb.getSheet(sheet_name).getRow(74).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("insurance_premium_tax")));
		wb.getSheet(sheet_name).getRow(76).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_cost_ex_vat")));
		wb.getSheet(sheet_name).getRow(77).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_subs_per_month_ex_vat")));
		
		

		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {

			wb.getSheet(sheet_name).getRow(79).getCell(1).setCellValue(0);

			wb.getSheet(sheet_name).getRow(80).getCell(1).setCellValue(0);
			
			
			if (sheet_name.contains("Used_LCV")) {
				
				wb.getSheet(sheet_name).getRow(79).getCell(1).setCellValue(
						Double.parseDouble(prop.getProperty("additional_rfl_per_annum_used_LCV")));

				
				wb.getSheet(sheet_name).getRow(80).getCell(1).setCellValue(
						Double.parseDouble(prop.getProperty("additional_rfl_premium_vehicle_over_40k_per_annum_used_LCV")));
			}

		} else {
			
			if (sheet_name.contains("Used")) {
				wb.getSheet(sheet_name).getRow(80).getCell(1).setCellValue(
						Double.parseDouble(prop.getProperty("additional_rfl_premium_vehicle_over_40k_per_annum_used_car")));
				wb.getSheet(sheet_name).getRow(79).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("additional_rfl_per_annum")));
			
			
			} else {

				wb.getSheet(sheet_name).getRow(79).getCell(1)
						.setCellValue(Double.parseDouble(prop.getProperty("additional_rfl_per_annum")));

				wb.getSheet(sheet_name).getRow(80).getCell(1).setCellValue(
						Double.parseDouble(prop.getProperty("additional_rfl_premium_vehicle_over_40k_per_annum")));
			}

		}

		

		wb.getSheet(sheet_name).getRow(84).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("disposal_costs")));
		wb.getSheet(sheet_name).getRow(85).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("delivery_and_collection_ex_vat")));

		wb.getSheet(sheet_name).getRow(107).getCell(1).setCellValue(0);
		wb.getSheet(sheet_name).getRow(107).getCell(3).setCellValue(0);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		out.close();

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -completed");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -completed");
	}

	public void set_global_variables_to_excel_for_cp_bch_pch_scenario_with_funder_quote_addition(
			String maintenance_margin, String sheet_name) throws IOException, ClassNotFoundException, FormulaParseException, IllegalStateException {
		// write / take global variables and set to excel sheet for calculation

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -started");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		// rfl values fakt on hotya baki sarv comment hotya

		wb.getSheet(sheet_name).getRow(67).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage")));
		wb.getSheet(sheet_name).getRow(70).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage_for_broker_vrb")));
		wb.getSheet(sheet_name).getRow(73).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("contingency_insurance_multiplier_holding_cost")));
		wb.getSheet(sheet_name).getRow(74).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("insurance_premium_tax")));
		wb.getSheet(sheet_name).getRow(76).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_cost_ex_vat")));
		wb.getSheet(sheet_name).getRow(77).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_subs_per_month_ex_vat")));
		
		
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("used")) {

			wb.getSheet(sheet_name).getRow(72).getCell(1).setCellFormula("E15*B72");
			wb.getSheet(sheet_name).getRow(69).getCell(1).setCellFormula("E15*B69");
          }
		else
		{
			wb.getSheet(sheet_name).getRow(72).getCell(1).setCellFormula("B67*B72");
			wb.getSheet(sheet_name).getRow(69).getCell(1).setCellFormula("B67*B69");
		}
			
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {

			wb.getSheet(sheet_name).getRow(79).getCell(1).setCellValue(0);

			wb.getSheet(sheet_name).getRow(80).getCell(1).setCellValue(0);

		} else {
			
			wb.getSheet(sheet_name).getRow(79).getCell(1)
			.setCellValue(Double.parseDouble(prop.getProperty("additional_rfl_per_annum")));
	        wb.getSheet(sheet_name).getRow(80).getCell(1).setCellValue(
			Double.parseDouble(prop.getProperty("additional_rfl_premium_vehicle_over_40k_per_annum")));
		}	

		
		wb.getSheet(sheet_name).getRow(84).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("disposal_costs")));
		wb.getSheet(sheet_name).getRow(85).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("delivery_and_collection_ex_vat")));
		wb.getSheet(sheet_name).getRow(110).getCell(1).setCellValue(maintenance_margin);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		out.close();

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -completed");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -completed");
	}

	public void set_global_variables_to_excel_for_finance_lease(String sheet_name)
			throws IOException, ClassNotFoundException, FormulaParseException, IllegalStateException {
		// write / take global variables and set to excel sheet for calculation

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -started");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(61).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage")));
		wb.getSheet(sheet_name).getRow(64).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage_for_broker_vrb")));

		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("used")) {
			wb.getSheet(sheet_name).getRow(66).getCell(1).setCellFormula("B60*B66");
		} else {
			wb.getSheet(sheet_name).getRow(66).getCell(1).setCellFormula("B61*B66");
		}

		wb.getSheet(sheet_name).getRow(67).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("contingency_insurance_multiplier_holding_cost")));
		wb.getSheet(sheet_name).getRow(68).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("insurance_premium_tax")));
		wb.getSheet(sheet_name).getRow(70).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_cost_ex_vat")));
		wb.getSheet(sheet_name).getRow(71).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_subs_per_month_ex_vat")));
		wb.getSheet(sheet_name).getRow(73).getCell(1).setCellValue(0);// these values are hard coded because in finance
																		// lease rfl values are not considered
		wb.getSheet(sheet_name).getRow(74).getCell(1).setCellValue(0);
		// wb.getSheet(sheet_name).getRow(76).getCell(1).setCellValue(Double.parseDouble(prop.getProperty("upsell")));
		wb.getSheet(sheet_name).getRow(78).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("disposal_costs")));
		wb.getSheet(sheet_name).getRow(79).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("delivery_and_collection_ex_vat")));

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));

		wb.write(out);
		out.close();

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -completed");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -completed");
	}

	public void set_global_variables_to_excel_for_finance_lease_for_funder_quote_addition(String sheet_name)
			throws IOException, ClassNotFoundException, FormulaParseException, IllegalStateException {
		// write / take global variables and set to excel sheet for calculation

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -started");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(67).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage")));
		wb.getSheet(sheet_name).getRow(70).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("minimum_margin_percentage_for_broker_vrb")));
		
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("used")) {
				wb.getSheet(sheet_name).getRow(72).getCell(1).setCellFormula("B66*B72");
			}          
		else
		{
			wb.getSheet(sheet_name).getRow(72).getCell(1).setCellFormula("B67*B72");
		}		
		wb.getSheet(sheet_name).getRow(73).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("contingency_insurance_multiplier_holding_cost")));
		wb.getSheet(sheet_name).getRow(74).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("insurance_premium_tax")));
		wb.getSheet(sheet_name).getRow(76).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_cost_ex_vat")));
		wb.getSheet(sheet_name).getRow(77).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("tracker_subs_per_month_ex_vat")));
		wb.getSheet(sheet_name).getRow(79).getCell(1).setCellValue(0);// these values are hard coded because in finance
																		// lease rfl values are not considered
		wb.getSheet(sheet_name).getRow(80).getCell(1).setCellValue(0);
		// wb.getSheet(sheet_name).getRow(76).getCell(1).setCellValue(Double.parseDouble(prop.getProperty("upsell")));
		wb.getSheet(sheet_name).getRow(84).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("disposal_costs")));
		wb.getSheet(sheet_name).getRow(85).getCell(1)
				.setCellValue(Double.parseDouble(prop.getProperty("delivery_and_collection_ex_vat")));

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		out.close();

		LO.print("Writing configuration values from property file to Excel for customer quote calculation -completed");
		System.out.println(
				"Writing configuration values from property file to Excel for customer quote calculation -completed");
	}

	public boolean verify_customer_quote_calculations_for_one_payment_options_without_maintenance(WebDriver driver,
			WebElement customer_quote_payment_profile_dropdown, WebElement part_exchange_payment,
			WebElement actual_part_exchange_value, String actual_part_exchange_value_from_excel,
			WebElement given_part_exchange_value, String given_part_exchange_value_from_excel,
			WebElement less_finance_settlement, String less_finance_settlement_from_excel, WebElement order_deposit,
			String order_deposit_from_excel, WebElement document_fee, String document_fee_from_excel, String upsell,
			WebElement customer_quote_monthly_finance_rental, String maintenance_required, String maintenance_margin,
			String initial_payment, String part_exchange_status, String target_rental, String sheet_name)
			throws IOException, InterruptedException {

		LO.print("************Calculations for Customer Quote Page has been started***********");
		System.out.println("************Calculations for Customer Quote Page has been started***********");

		Thread.sleep(3000);
		ExplicitWait.clickableElement(driver, customer_quote_payment_profile_dropdown, 30);
		Select select = new Select(customer_quote_payment_profile_dropdown);

		select.selectByIndex(0);

		LO.print("Payment Profile Monthly in Advance option has been selected");
		System.out.println("Payment Profile Monthly in Advance option has been selected");

		List<WebElement> list_dropdown_options = select.getOptions();
		String dropdown_option = list_dropdown_options.get(0).getText();

		Thread.sleep(3000);

		ExplicitWait.clickableElement(driver, part_exchange_payment, 50);
		Thread.sleep(3000);

		LO.print("Writing values to Excel for customer quote calculation -started");
		System.out.println("Writing values to Excel for customer quote calculation -started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(98).getCell(1).setCellValue(" " + dropdown_option + " ");
		wb.getSheet(sheet_name).getRow(101).getCell(0).setCellValue(Double.parseDouble(document_fee_from_excel));
		wb.getSheet(sheet_name).getRow(104).getCell(0).setCellValue(maintenance_required);
		wb.getSheet(sheet_name).getRow(104).getCell(1).setCellValue(Double.parseDouble(maintenance_margin));
		wb.getSheet(sheet_name).getRow(104).getCell(3).setCellValue(Double.parseDouble(initial_payment));
		wb.getSheet(sheet_name).getRow(109).getCell(1).setCellValue("NO");
		wb.getSheet(sheet_name).getRow(123).getCell(1).setCellValue(Double.parseDouble(target_rental));

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing values to Excel for customer quote calculation -completed");
		System.out.println("Writing values to Excel for customer quote calculation -completed");

		LO.print("Reading  Monthly Finance Rental from  Excel   -started");
		System.out.println("Reading  Monthly Finance Rental from  Excel   -started");

		double monthly_finance_rental_expected = GetExcelFormulaValue.get_formula_value(89, 1, sheet_name);

		LO.print("Reading  Monthly Finance Rental from  Excel   -completed");
		System.out.println("Reading  Monthly Finance Rental from  Excel   -completed");

		LO.print("Monthly Finance Rental from Excel is =" + monthly_finance_rental_expected);
		System.out.println("Monthly Finance Rental from Excel is =" + monthly_finance_rental_expected);

		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);
		String monthly_finance_rental = customer_quote_monthly_finance_rental.getText().substring(2);
		String monthly_finance_rental_actual = RemoveComma.of(monthly_finance_rental);
		double monthly_finance_rental_actual_converted = Double.parseDouble(monthly_finance_rental_actual);

		LO.print("Monthly Finance Rental from screen is " + monthly_finance_rental_actual_converted);

		System.out.println("Monthly Finance Rental from screen is " + monthly_finance_rental_actual_converted);

		boolean flag = false;

		double diff = Difference.of_two_Double_Values(monthly_finance_rental_expected,
				monthly_finance_rental_actual_converted);

		if (diff < 0.3) {
			flag = true;
		}
		return flag;

	}

	public boolean verify_customer_quote_calculations_for_one_payment_options_for_funder_quote_addition_without_maintenance(
			WebDriver driver, WebElement customer_quote_payment_profile_dropdown, WebElement part_exchange_payment,
			WebElement actual_part_exchange_value, String actual_part_exchange_value_from_excel,
			WebElement given_part_exchange_value, String given_part_exchange_value_from_excel,
			WebElement less_finance_settlement, String less_finance_settlement_from_excel, WebElement order_deposit,
			String order_deposit_from_excel, WebElement document_fee, String document_fee_from_excel, String upsell,
			WebElement customer_quote_monthly_finance_rental, String maintenance_required, String maintenance_margin,
			String initial_payment, String part_exchange_status, String target_rental, String sheet_name)
			throws IOException, InterruptedException {

		LO.print("************Calculations for Customer Quote Page has been started***********");
		System.out.println("************Calculations for Customer Quote Page has been started***********");

		Thread.sleep(3000);
		ExplicitWait.clickableElement(driver, customer_quote_payment_profile_dropdown, 30);
		Select select = new Select(customer_quote_payment_profile_dropdown);

		select.selectByIndex(0);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		LO.print("Payment Profile Monthly in Advance option has been selected");
		System.out.println("Payment Profile Monthly in Advance option has been selected");

		List<WebElement> list_dropdown_options = select.getOptions();
		String dropdown_option = list_dropdown_options.get(0).getText();

		Thread.sleep(3000);

//		ExplicitWait.clickableElement(driver, part_exchange_payment, 50);
//	
//		Click.on(driver, part_exchange_payment, 70);
//		LO.print("Clicked on Part Exchange panel" );
//		System.out.println("Clicked on Part Exchange panel" );
//	
//		Click.sendKeys(driver, actual_part_exchange_value, actual_part_exchange_value_from_excel, 30);
//		Click.sendKeys(driver, given_part_exchange_value, given_part_exchange_value_from_excel, 30);
//		Click.sendKeys(driver, less_finance_settlement, less_finance_settlement_from_excel, 30);
//		Click.sendKeys(driver, order_deposit, order_deposit_from_excel, 30);
//		ExplicitWait.visibleElement(driver, document_fee, 30);
//		document_fee.clear();
//		Click.sendKeys(driver, document_fee, document_fee_from_excel, 30);
//		Actions act = new Actions (driver);
//		act.sendKeys(Keys.TAB).perform();
//		
		LO.print("Writing values to Excel for customer quote calculation -started");
		System.out.println("Writing values to Excel for customer quote calculation -started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(104).getCell(1).setCellValue(" " + dropdown_option + " ");
		wb.getSheet(sheet_name).getRow(104).getCell(3).setCellValue(Double.parseDouble(order_deposit_from_excel));
		wb.getSheet(sheet_name).getRow(107).getCell(0).setCellValue(Double.parseDouble(document_fee_from_excel));
		wb.getSheet(sheet_name).getRow(110).getCell(0).setCellValue("NO");
		wb.getSheet(sheet_name).getRow(110).getCell(1).setCellValue(Double.parseDouble(maintenance_margin));
		wb.getSheet(sheet_name).getRow(110).getCell(3).setCellValue(Double.parseDouble(initial_payment));
		wb.getSheet(sheet_name).getRow(115).getCell(1).setCellValue("NO");
		wb.getSheet(sheet_name).getRow(129).getCell(1).setCellValue(Double.parseDouble(target_rental));

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing values to Excel for customer quote calculation -completed");
		System.out.println("Writing values to Excel for customer quote calculation -completed");

		LO.print("Reading  Monthly Finance Rental from  Excel   -started");
		System.out.println("Reading  Monthly Finance Rental from  Excel   -started");

		double monthly_finance_rental_expected = GetExcelFormulaValue.get_formula_value(95, 1, sheet_name);

		LO.print("Reading  Monthly Finance Rental from  Excel   -completed");
		System.out.println("Reading  Monthly Finance Rental from  Excel   -completed");

		LO.print("Monthly Finance Rental from Excel is =" + monthly_finance_rental_expected);
		System.out.println("Monthly Finance Rental from Excel is =" + monthly_finance_rental_expected);

		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);
		String monthly_finance_rental = customer_quote_monthly_finance_rental.getText().substring(2);
		String monthly_finance_rental_actual = RemoveComma.of(monthly_finance_rental);
		double monthly_finance_rental_actual_converted = Double.parseDouble(monthly_finance_rental_actual);

		LO.print("Monthly Finance Rental from screen is " + monthly_finance_rental_actual_converted);

		System.out.println("Monthly Finance Rental from screen is " + monthly_finance_rental_actual_converted);

		boolean flag = false;

		double diff = Difference.of_two_Double_Values(monthly_finance_rental_expected,
				monthly_finance_rental_actual_converted);

		if (diff < 0.3) {
			flag = true;
		}

		return flag;

	}

	public boolean verify_customer_quote_calculations_for_one_payment_options_for_funder_quote_addition_with_maintenance(
			WebDriver driver, WebElement customer_quote_payment_profile_dropdown, WebElement part_exchange_payment,
			WebElement actual_part_exchange_value, String actual_part_exchange_value_from_excel,
			WebElement given_part_exchange_value, String given_part_exchange_value_from_excel,
			WebElement less_finance_settlement, String less_finance_settlement_from_excel, WebElement order_deposit,
			String order_deposit_from_excel, WebElement document_fee, String document_fee_from_excel, String upsell,
			WebElement customer_quote_monthly_finance_rental, WebElement customer_quote_monthly_maintenance_rental,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name) throws IOException, InterruptedException {

		LO.print("************Calculations for Customer Quote Page has been started***********");
		System.out.println("************Calculations for Customer Quote Page has been started***********");

		ExplicitWait.clickableElement(driver, customer_quote_payment_profile_dropdown, 30);
		Select select = new Select(customer_quote_payment_profile_dropdown);

		select.selectByIndex(0);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		LO.print("Payment Profile Monthly in Advance option has been selected");
		System.out.println("Payment Profile Monthly in Advance option has been selected");

		List<WebElement> list_dropdown_options = select.getOptions();
		String dropdown_option = list_dropdown_options.get(0).getText();

		Thread.sleep(1000);

		LO.print("Writing values to Excel for customer quote calculation -started");
		System.out.println("Writing values to Excel for customer quote calculation -started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(104).getCell(1).setCellValue(" " + dropdown_option + " ");
		wb.getSheet(sheet_name).getRow(104).getCell(3).setCellValue(Double.parseDouble(order_deposit_from_excel));
		wb.getSheet(sheet_name).getRow(107).getCell(0).setCellValue(Double.parseDouble(document_fee_from_excel));
		wb.getSheet(sheet_name).getRow(110).getCell(0).setCellValue("YES");
		wb.getSheet(sheet_name).getRow(110).getCell(1).setCellValue(Double.parseDouble(maintenance_margin));
		wb.getSheet(sheet_name).getRow(110).getCell(3).setCellValue(Double.parseDouble(initial_payment));
		wb.getSheet(sheet_name).getRow(115).getCell(1).setCellValue("NO");
		wb.getSheet(sheet_name).getRow(129).getCell(1).setCellValue(Double.parseDouble(target_rental));

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing values to Excel for customer quote calculation -completed");
		System.out.println("Writing values to Excel for customer quote calculation -completed");

		LO.print("Reading  Monthly Finance and Maintenance Rental from  Excel   -started");
		System.out.println("Reading  Monthly Finance and Maintenance Rental from  Excel   -started");

		double monthly_finance_rental_expected = GetExcelFormulaValue.get_formula_value(95, 1, sheet_name);

		double monthly_maintenance_rental_expected = GetExcelFormulaValue.get_formula_value(94, 1, sheet_name);

		LO.print("Reading  Monthly Finance and Maintenance Rental from  Excel   -completed");
		System.out.println("Reading  Monthly Finance and Maintenance Rental from  Excel   -completed");

		LO.print("Monthly Finance Rental from Excel is =" + monthly_finance_rental_expected);
		System.out.println("Monthly Finance Rental from Excel is =" + monthly_finance_rental_expected);

		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);
		String monthly_finance_rental = customer_quote_monthly_finance_rental.getText().substring(2);
		String monthly_finance_rental_actual = RemoveComma.of(monthly_finance_rental);
		double monthly_finance_rental_actual_converted = Double.parseDouble(monthly_finance_rental_actual);

		LO.print("Monthly Finance Rental from screen is " + monthly_finance_rental_actual_converted);
		System.out.println("Monthly Finance Rental from screen is " + monthly_finance_rental_actual_converted);

		ExplicitWait.visibleElement(driver, customer_quote_monthly_maintenance_rental, 30);

		String monthly_mainte_rental = customer_quote_monthly_maintenance_rental.getText().substring(2);
		String monthly_mainte_rental_actual = RemoveComma.of(monthly_mainte_rental);
		double monthly_mainte_rental_actual_converted = Double.parseDouble(monthly_mainte_rental_actual);

		LO.print("Monthly Maintenance Rental from Excel is =" + monthly_maintenance_rental_expected);
		System.out.println("Monthly Maintenance Rental from Excel is =" + monthly_maintenance_rental_expected);

		LO.print("Monthly Maintenance Rental from screen is " + monthly_mainte_rental_actual_converted);
		System.out.println("Monthly Maintenance Rental from screen is " + monthly_mainte_rental_actual_converted);

		boolean flag = false;

		double diff1 = Difference.of_two_Double_Values(monthly_finance_rental_expected,
				monthly_finance_rental_actual_converted);
		double diff2 = Difference.of_two_Double_Values(monthly_maintenance_rental_expected,
				monthly_mainte_rental_actual_converted);

		if (diff1 < 0.3 && diff2 < 0.3) {
			flag = true;
		}
		return flag;

	}

	public boolean verify_customer_quote_calculations_for_one_payment_options_without_maintenance_edited(
			WebDriver driver, WebElement customer_quote_payment_profile_dropdown, WebElement part_exchange_payment,
			WebElement actual_part_exchange_value, String actual_part_exchange_value_from_excel,
			WebElement given_part_exchange_value, String given_part_exchange_value_from_excel,
			WebElement less_finance_settlement, String less_finance_settlement_from_excel, WebElement order_deposit,
			String order_deposit_from_excel, WebElement document_fee, String document_fee_from_excel,
			WebElement matrix_upsell_input_field, String upsell, WebElement customer_quote_monthly_finance_rental,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name) throws IOException {

		LO.print("************Calculations for Customer Quote Page has been started***********");
		System.out.println("************Calculations for Customer Quote Page has been started***********");

		Actions act = new Actions(driver);

		ExplicitWait.clickableElement(driver, customer_quote_payment_profile_dropdown, 30);
		Select select = new Select(customer_quote_payment_profile_dropdown);
		select.selectByIndex(0);

		LO.print("Payment Profile Monthly in Advance option has been selected");
		System.out.println("Payment Profile Monthly in Advance option has been selected");

		List<WebElement> list_dropdown_options = select.getOptions();
		String dropdown_option = list_dropdown_options.get(0).getText();

		ExplicitWait.visibleElement(driver, matrix_upsell_input_field, 30);
		matrix_upsell_input_field.sendKeys((Keys.chord(Keys.CONTROL, "a", Keys.DELETE)));
		Click.sendKeys(driver, matrix_upsell_input_field, upsell, 50);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.clickableElement(driver, part_exchange_payment, 50);
		Click.on(driver, part_exchange_payment, 70);
		LO.print("Clicked on Part Exchange panel");
		System.out.println("Clicked on Part Exchange panel");
		Click.sendKeys(driver, actual_part_exchange_value, actual_part_exchange_value_from_excel, 30);
		Click.sendKeys(driver, given_part_exchange_value, given_part_exchange_value_from_excel, 30);
		Click.sendKeys(driver, less_finance_settlement, less_finance_settlement_from_excel, 30);
		Click.sendKeys(driver, order_deposit, order_deposit_from_excel, 30);
		ExplicitWait.visibleElement(driver, document_fee, 30);
		document_fee.clear();
		Click.sendKeys(driver, document_fee, document_fee_from_excel, 30);

		act.sendKeys(Keys.TAB).build().perform();

		LO.print("Writing values to Excel for customer quote calculation -started");
		System.out.println("Writing values to Excel for customer quote calculation -started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(98).getCell(1).setCellValue(" " + dropdown_option + " ");
		wb.getSheet(sheet_name).getRow(98).getCell(3).setCellValue(Double.parseDouble(order_deposit_from_excel));
		wb.getSheet(sheet_name).getRow(101).getCell(0).setCellValue(Double.parseDouble(document_fee_from_excel));
		wb.getSheet(sheet_name).getRow(101).getCell(1).setCellValue(Double.parseDouble(upsell));
		wb.getSheet(sheet_name).getRow(104).getCell(0).setCellValue(maintenance_required);
		wb.getSheet(sheet_name).getRow(104).getCell(1).setCellValue(Double.parseDouble(maintenance_margin));
		wb.getSheet(sheet_name).getRow(104).getCell(3).setCellValue(Double.parseDouble(initial_payment));
		wb.getSheet(sheet_name).getRow(109).getCell(1).setCellValue("NO");

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing values to Excel for customer quote calculation -completed");
		System.out.println("Writing values to Excel for customer quote calculation -completed");

		LO.print("Reading  Monthly Finance Rental from  Excel   -started");
		System.out.println("Reading  Monthly Finance Rental from  Excel   -started");

		double monthly_finance_rental_expected = GetExcelFormulaValue.get_formula_value(89, 1, sheet_name);

		LO.print("Reading  Monthly Finance Rental from  Excel   -completed");
		System.out.println("Reading  Monthly Finance Rental from  Excel   -completed");

		LO.print("Monthly Finance Rental from Excel is =" + monthly_finance_rental_expected);
		System.out.println("Monthly Finance Rental from Excel is =" + monthly_finance_rental_expected);

		ExplicitWait.clickableElement(driver, customer_quote_monthly_finance_rental, 30);
		String monthly_finance_rental = customer_quote_monthly_finance_rental.getText().substring(2);
		String monthly_finance_rental_actual = RemoveComma.of(monthly_finance_rental);
		double monthly_finance_rental_actual_converted = Double.parseDouble(monthly_finance_rental_actual);
		System.out.println("Monthly Finance Rental from screen is " + monthly_finance_rental_actual_converted);

		boolean flag = false;

		double diff = Difference.of_two_Double_Values(monthly_finance_rental_expected,
				monthly_finance_rental_actual_converted);

		if (diff < 0.3) {
			flag = true;
		}
		return flag;

	}

	public boolean verify_customer_quote_calculations_for_all_payment_options_without_maintenance(WebDriver driver,
			WebElement customer_quote_payment_profile_dropdown, WebElement customer_quote_monthly_finance_rental,
			WebElement initial_payment_input_field, String initial_payment_from_test_data, String sheet_name)
			throws IOException, InterruptedException {

		LO.print("Calculations with different payment profiles for customer quote has been started");
		System.out.println("Calculations with different payment profiles for customer quote has been started");

		ExplicitWait.clickableElement(driver, customer_quote_payment_profile_dropdown, 50);

		Select select = new Select(customer_quote_payment_profile_dropdown);
		List<WebElement> list_dropdown_options = select.getOptions();
		int dropdown_options_number = list_dropdown_options.size();
		int count = 0;
		boolean flag = false;
		for (int i = 0; i < list_dropdown_options.size(); i++) {
			select.selectByIndex(i);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
			String dropdown_option = list_dropdown_options.get(i).getText();
			if (i == 1) {
				Thread.sleep(4000);
				Click.on(driver, initial_payment_input_field, 40);
				initial_payment_input_field.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
				Click.sendKeys(driver, initial_payment_input_field, initial_payment_from_test_data, 20);

				Actions act = new Actions(driver);
				act.sendKeys(Keys.TAB).build().perform();
				Thread.sleep(5000);
			}

			ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 50);
			Thread.sleep(4000);
			String monthly_finance_rental = customer_quote_monthly_finance_rental.getText().substring(2);

			String monthly_finance_rental_actual = RemoveComma.of(monthly_finance_rental);

			System.out.println("dropdown option " + dropdown_option);

			System.out.println("Monthly finance rental actual " + monthly_finance_rental_actual);

			double monthly_finance_rental_actual_converted = Double.parseDouble(monthly_finance_rental_actual);

			FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
			XSSFWorkbook wb = new XSSFWorkbook(in);

			wb.getSheet(sheet_name).getRow(98).getCell(1).setCellValue(" " + dropdown_option + " ");
			if (i == 1) {
				wb.getSheet(sheet_name).getRow(104).getCell(3).setCellValue(initial_payment_from_test_data);
			}
			FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
			wb.write(out);

			double monthly_finance_rental_expected = GetExcelFormulaValue.get_formula_value(89, 1, sheet_name);

			System.out.println("Monthly finance rental expected " + monthly_finance_rental_expected);

			double diff = Difference.of_two_Double_Values(monthly_finance_rental_actual_converted,
					monthly_finance_rental_expected);
			System.out.println("Difference is" + diff);
			if (diff < 0.3) {
				count++;
			}
			if (count == dropdown_options_number) {
				flag = true;
			}

		}

		LO.print("Calculations with different payment profiles for customer quote has been ended");
		System.out.println("Calculations with different payment profiles for customer quote has been ended");
		return flag;
	}

	public boolean verify_customer_quote_calculations_for_all_payment_options_for_funder_quote_addition_without_maintenance(
			WebDriver driver, WebElement customer_quote_payment_profile_dropdown,
			WebElement customer_quote_monthly_finance_rental, WebElement initial_payment_input_field,
			String initial_payment_from_test_data, String sheet_name) throws IOException, InterruptedException {

		LO.print("Calculations with different payment profiles for customer quote has been started");
		System.out.println("Calculations with different payment profiles for customer quote has been started");

		ExplicitWait.clickableElement(driver, customer_quote_payment_profile_dropdown, 50);

		Select select = new Select(customer_quote_payment_profile_dropdown);
		List<WebElement> list_dropdown_options = select.getOptions();
		int dropdown_options_number = list_dropdown_options.size();
		int count = 0;
		boolean flag = false;
		for (int i = 0; i < list_dropdown_options.size(); i++) {
			select.selectByIndex(i);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
			Thread.sleep(3000);

			String dropdown_option = list_dropdown_options.get(i).getText();
			if (i == 1) {
				Thread.sleep(4000);
				Click.on(driver, initial_payment_input_field, 40);
				initial_payment_input_field.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
				Click.sendKeys(driver, initial_payment_input_field, initial_payment_from_test_data, 20);

				Actions act = new Actions(driver);
				act.sendKeys(Keys.TAB).build().perform();
				Thread.sleep(5000);
			}

			ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 50);
			Thread.sleep(4000);
			String monthly_finance_rental = customer_quote_monthly_finance_rental.getText().substring(2);

			String monthly_finance_rental_actual = RemoveComma.of(monthly_finance_rental);

			System.out.println("dropdown option " + dropdown_option);

			System.out.println("Monthly finance rental actual " + monthly_finance_rental_actual);

			double monthly_finance_rental_actual_converted = Double.parseDouble(monthly_finance_rental_actual);

			FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
			XSSFWorkbook wb = new XSSFWorkbook(in);

			wb.getSheet(sheet_name).getRow(104).getCell(1).setCellValue(" " + dropdown_option + " ");
			if (i == 1) {
				wb.getSheet(sheet_name).getRow(110).getCell(3).setCellValue(initial_payment_from_test_data);
			}
			FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
			wb.write(out);

			double monthly_finance_rental_expected = GetExcelFormulaValue.get_formula_value(96, 1, sheet_name);

			System.out.println("Monthly finance rental expected " + monthly_finance_rental_expected);

			double diff = Difference.of_two_Double_Values(monthly_finance_rental_actual_converted,
					monthly_finance_rental_expected);
			System.out.println("Difference is" + diff);
			if (diff < 0.3) {
				count++;
			}
			if (count == dropdown_options_number) {
				flag = true;
			}

		}

		LO.print("Calculations with different payment profiles for customer quote has been ended");
		System.out.println("Calculations with different payment profiles for customer quote has been ended");
		return flag;
	}

	public boolean verify_customer_quote_calculations_for_all_payment_options_for_funder_quote_addition_with_maintenance(
			WebDriver driver, WebElement customer_quote_payment_profile_dropdown,
			WebElement customer_quote_monthly_finance_rental, WebElement customer_quote_monthly_maintenance_rental,
			WebElement initial_payment_input_field, String initial_payment_from_test_data, String sheet_name)
			throws IOException, InterruptedException {

		LO.print("Calculations with different payment profiles for customer quote has been started");
		System.out.println("Calculations with different payment profiles for customer quote has been started");

		ExplicitWait.clickableElement(driver, customer_quote_payment_profile_dropdown, 50);

		Select select = new Select(customer_quote_payment_profile_dropdown);
		List<WebElement> list_dropdown_options = select.getOptions();
		int dropdown_options_number = list_dropdown_options.size();
		int count = 0;
		boolean flag = false;
		for (int i = 0; i < list_dropdown_options.size(); i++) {
			select.selectByIndex(i);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
			Thread.sleep(7000);
			String dropdown_option = list_dropdown_options.get(i).getText();
			if (i == 1) {
				Thread.sleep(2000);
				Click.on(driver, initial_payment_input_field, 40);
				initial_payment_input_field.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
				Click.sendKeys(driver, initial_payment_input_field, initial_payment_from_test_data, 20);

				Actions act = new Actions(driver);
				act.sendKeys(Keys.TAB).build().perform();

				ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
				Thread.sleep(3000);
			}

			ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 50);

			String monthly_finance_rental = customer_quote_monthly_finance_rental.getText().substring(2);

			String monthly_finance_rental_actual = RemoveComma.of(monthly_finance_rental);

			System.out.println("dropdown option " + dropdown_option);

			double monthly_finance_rental_actual_converted = Double.parseDouble(monthly_finance_rental_actual);

			ExplicitWait.visibleElement(driver, customer_quote_monthly_maintenance_rental, 50);

			String monthly_maint_rental = customer_quote_monthly_maintenance_rental.getText().substring(2);

			String monthly_maint_rental_actual = RemoveComma.of(monthly_maint_rental);

			double monthly_mainte_rental_actual_converted = Double.parseDouble(monthly_maint_rental_actual);
			FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
			XSSFWorkbook wb = new XSSFWorkbook(in);

			wb.getSheet(sheet_name).getRow(104).getCell(1).setCellValue(" " + dropdown_option + " ");
			if (i == 1) {
				wb.getSheet(sheet_name).getRow(110).getCell(3).setCellValue(initial_payment_from_test_data);
			}
			FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
			wb.write(out);

			double monthly_finance_rental_expected = GetExcelFormulaValue.get_formula_value(95, 1, sheet_name);

			double monthly_maint_rental_expected = GetExcelFormulaValue.get_formula_value(94, 1, sheet_name);

			System.out.println("Monthly finance rental actual " + monthly_finance_rental_actual);
			System.out.println("Monthly finance rental expected " + monthly_finance_rental_expected);
			System.out.println("Monthly Maintenance rental actual " + monthly_maint_rental_actual);
			System.out.println("Monthly Maintenance rental expected " + monthly_maint_rental_expected);

			double diff1 = Difference.of_two_Double_Values(monthly_finance_rental_actual_converted,
					monthly_finance_rental_expected);

			double diff2 = Difference.of_two_Double_Values(monthly_maint_rental_expected,
					monthly_mainte_rental_actual_converted);

			System.out.println("Difference1 is" + diff1);

			System.out.println("Difference2 is" + diff2);

			if (diff1 < 0.3 && diff2 < 0.3) {
				count++;
			}
			if (count == dropdown_options_number) {
				flag = true;
			}

		}

		LO.print("Calculations with different payment profiles for customer quote has been ended");
		System.out.println("Calculations with different payment profiles for customer quote has been ended");
		return flag;
	}

	public boolean verify_customer_quote_calculations_for_all_payment_options_without_maintenance_edited(
			WebDriver driver, WebElement customer_quote_payment_profile_dropdown,
			WebElement customer_quote_monthly_finance_rental, WebElement initial_payment_input_field,
			String initial_payment_from_test_data, String sheet_name) throws IOException, InterruptedException {

		LO.print("Calculations with different payment profiles for customer quote has been started");
		System.out.println("Calculations with different payment profiles for customer quote has been started");

		ExplicitWait.clickableElement(driver, customer_quote_payment_profile_dropdown, 50);

		Select select = new Select(customer_quote_payment_profile_dropdown);
		List<WebElement> list_dropdown_options = select.getOptions();
		int dropdown_options_number = list_dropdown_options.size();
		int count = 0;
		boolean flag = false;
		for (int i = 0; i < list_dropdown_options.size(); i++) {
			select.selectByIndex(i);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
			String dropdown_option = list_dropdown_options.get(i).getText();
			if (i == 1) {
				Click.sendKeys(driver, initial_payment_input_field, initial_payment_from_test_data, 40);
				Actions act = new Actions(driver);
				act.sendKeys(Keys.TAB).build().perform();
				Thread.sleep(5000);
			}

			ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 50);
			Thread.sleep(5000);
			String monthly_finance_rental = customer_quote_monthly_finance_rental.getText().substring(2);

			String monthly_finance_rental_actual = RemoveComma.of(monthly_finance_rental);

			// System.out.println("Monthly finance rental actual
			// "+monthly_finance_rental_actual);

			double monthly_finance_rental_actual_converted = Double.parseDouble(monthly_finance_rental_actual);

			FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
			XSSFWorkbook wb = new XSSFWorkbook(in);

			wb.getSheet(sheet_name).getRow(98).getCell(1).setCellValue(" " + dropdown_option + " ");
			if (i == 1) {
				wb.getSheet(sheet_name).getRow(104).getCell(3).setCellValue(initial_payment_from_test_data);
			}
			FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
			wb.write(out);

			double monthly_finance_rental_expected = GetExcelFormulaValue.get_formula_value(90, 1, sheet_name);

			double diff = Difference.of_two_Double_Values(monthly_finance_rental_actual_converted,
					monthly_finance_rental_expected);
			// System.out.println(diff);
			if (diff < 0.2) {
				count++;
			}
			if (count == dropdown_options_number) {
				flag = true;
			}

		}

		LO.print("Calculations with different payment profiles for customer quote has been ended");
		System.out.println("Calculations with different payment profiles for customer quote has been ended");
		return flag;
	}

	public boolean verify_quote_summary_values_from_excel_without_maintenance(
			double quote_summary_cost_otr_price_from_screen_converted,
			double quote_summary_total_monthly_holding_cost_from_screen_converted,
			double quote_summary_monthly_finance_rental_from_screen_converted, String sheet_name) throws IOException {

		LO.print("Reading values from excel sheet to compare it with quote summary on screen values");
		System.out.println("Reading values from excel sheet to compare it with quote summary on screen values");

		double otr_price_expected = GetExcelFormulaValue.get_formula_value(14, 4, sheet_name);

		double total_monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		double finance_rental_expected = GetExcelFormulaValue.get_formula_value(89, 1, sheet_name);

		LO.print("Comparing excel values with actual values on screen");
		System.out.println("Comparing excel values with actual values on screen");

		int count = 0;
		boolean flag = false;

		double diff1 = Difference.of_two_Double_Values(otr_price_expected,
				quote_summary_cost_otr_price_from_screen_converted);

		if (diff1 < 0.3) {
			LO.print("OTR price compared");
			System.out.println("OTR price compared");
			count++;
		} else {
			LO.print("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
			System.out
					.println("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
		}

		double diff2 = Difference.of_two_Double_Values(total_monthly_holding_cost_expected,
				quote_summary_total_monthly_holding_cost_from_screen_converted);

		if (diff2 < 0.3) {
			LO.print("Total monthly holding cost compared");
			System.out.println("Total monthly holding cost compared");
			count++;
		} else {
			LO.print(
					"Found difference between Monthly Holding Cost actual and Monthly Holding Cost expected on Quote Summary Page");
			System.out.println(
					"Found difference between Monthly Holding Cost actual and Monthly Holding Cost expected on Quote Summary Page");
		}

		double diff3 = Difference.of_two_Double_Values(finance_rental_expected,
				quote_summary_monthly_finance_rental_from_screen_converted);

		if (diff3 < 0.3) {
			LO.print("Finance Rental compared");
			System.out.println("Finance Rental compared");
			count++;
		} else {
			LO.print(
					"Found difference between Finance Rental actual and Finance Rental expected on Quote Summary Page");
			System.out.println(
					"Found difference between Finance Rental actual and Finance Rental expected on Quote Summary Page");
		}

		if (count == 3) {
			flag = true;
		}

		return flag;
	}

	public boolean verify_quote_summary_values_from_excel_for_funder_quote_addition_without_maintenance(
			double quote_summary_cost_otr_price_from_screen_converted,
			double quote_summary_total_monthly_holding_cost_from_screen_converted,
			double quote_summary_monthly_finance_rental_from_screen_converted, String sheet_name) throws IOException {

		LO.print("Reading values from excel sheet to compare it with quote summary on screen values");
		System.out.println("Reading values from excel sheet to compare it with quote summary on screen values");

		double otr_price_expected = GetExcelFormulaValue.get_formula_value(14, 4, sheet_name);

		double total_monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(57, 1, sheet_name);

		double finance_rental_expected = GetExcelFormulaValue.get_formula_value(96, 1, sheet_name);

		LO.print("Comparing excel values with actual values on screen");
		System.out.println("Comparing excel values with actual values on screen");

		int count = 0;
		boolean flag = false;

		double diff1 = Difference.of_two_Double_Values(otr_price_expected,
				quote_summary_cost_otr_price_from_screen_converted);

		if (diff1 < 0.3) {
			LO.print("OTR price compared");
			System.out.println("OTR price compared");
			count++;
		} else {
			LO.print("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
			System.out
					.println("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
		}

		double diff2 = Difference.of_two_Double_Values(total_monthly_holding_cost_expected,
				quote_summary_total_monthly_holding_cost_from_screen_converted);

		if (diff2 < 0.3) {
			LO.print("Total monthly holding cost compared");
			System.out.println("Total monthly holding cost compared");
			count++;
		} else {
			LO.print(
					"Found difference between Monthly Holding Cost actual and Monthly Holding Cost expected on Quote Summary Page");
			System.out.println(
					"Found difference between Monthly Holding Cost actual and Monthly Holding Cost expected on Quote Summary Page");
		}

		double diff3 = Difference.of_two_Double_Values(finance_rental_expected,
				quote_summary_monthly_finance_rental_from_screen_converted);

		if (diff3 < 0.3) {
			LO.print("Finance Rental compared");
			System.out.println("Finance Rental compared");
			count++;
		} else {
			LO.print(
					"Found difference between Finance Rental actual and Finance Rental expected on Quote Summary Page");
			System.out.println(
					"Found difference between Finance Rental actual and Finance Rental expected on Quote Summary Page");
		}

		if (count == 3) {
			flag = true;
		}

		return flag;
	}

	public boolean verify_quote_summary_values_from_excel_for_funder_quote_addition_with_maintenance(
			double quote_summary_cost_otr_price_from_screen_converted,
			double quote_summary_total_monthly_holding_cost_from_screen_converted,
			double quote_summary_monthly_finance_rental_from_screen_converted,
			double quote_summary_monthly_maint_rental_from_screen_converted, String sheet_name) throws IOException {

		LO.print("Reading values from excel sheet to compare it with quote summary on screen values");
		System.out.println("Reading values from excel sheet to compare it with quote summary on screen values");

		double otr_price_expected = GetExcelFormulaValue.get_formula_value(14, 4, sheet_name);

		double total_monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(57, 1, sheet_name);

		double finance_rental_expected = GetExcelFormulaValue.get_formula_value(95, 1, sheet_name);

		double maint_rental_expected = GetExcelFormulaValue.get_formula_value(94, 1, sheet_name);

		LO.print("Comparing excel values with actual values on screen");
		System.out.println("Comparing excel values with actual values on screen");

		int count = 0;
		boolean flag = false;

		double diff1 = Difference.of_two_Double_Values(otr_price_expected,
				quote_summary_cost_otr_price_from_screen_converted);

		if (diff1 < 0.3) {
			LO.print("OTR price compared");
			System.out.println("OTR price compared");
			count++;
		} else {
			LO.print("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
			System.err
					.println("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
		}

		double diff2 = Difference.of_two_Double_Values(total_monthly_holding_cost_expected,
				quote_summary_total_monthly_holding_cost_from_screen_converted);

		if (diff2 < 0.3) {
			LO.print("Total monthly holding cost compared");
			System.out.println("Total monthly holding cost compared");
			count++;
		} else {
			LO.print(
					"Found difference between Monthly Holding Cost actual and Monthly Holding Cost expected on Quote Summary Page");
			System.err.println(
					"Found difference between Monthly Holding Cost actual and Monthly Holding Cost expected on Quote Summary Page");
		}

		double diff3 = Difference.of_two_Double_Values(finance_rental_expected,
				quote_summary_monthly_finance_rental_from_screen_converted);

		if (diff3 < 0.3) {
			LO.print("Finance Rental compared");
			System.out.println("Finance Rental compared");
			count++;
		} else {
			LO.print(
					"Found difference between Finance Rental actual and Finance Rental expected on Quote Summary Page");
			System.err.println(
					"Found difference between Finance Rental actual and Finance Rental expected on Quote Summary Page");
		}

		double diff4 = Difference.of_two_Double_Values(maint_rental_expected,
				quote_summary_monthly_maint_rental_from_screen_converted);

		if (diff4 < 0.3) {
			LO.print("Maintenance Rental compared");
			System.out.println("Maintenance Rental compared");
			count++;
		} else {
			LO.print(
					"Found difference between Maintenance Rental actual and Maintenance Rental expected on Quote Summary Page");
			System.err.println(
					"Found difference between Maintenance Rental actual and Maintenance Rental expected on Quote Summary Page");
		}

		if (count == 4) {
			flag = true;
		}

		return flag;
	}

	public boolean verify_quote_summary_values_for_broker_bch_pch_fl_from_excel_without_maintenance(
			double quote_summary_cost_otr_price_from_screen_converted, String sheet_name) throws IOException {

		LO.print("Reading values from excel sheet to compare it with quote summary on screen values");
		System.out.println("Reading values from excel sheet to compare it with quote summary on screen values");

		double otr_price_expected = GetExcelFormulaValue.get_formula_value(14, 4, sheet_name);

		LO.print("Comparing excel values with actual values on screen");
		System.out.println("Comparing excel values with actual values on screen");

		boolean flag = false;
		double diff1 = Difference.of_two_Double_Values(otr_price_expected,
				quote_summary_cost_otr_price_from_screen_converted);
		if (diff1 < 0.2) {
			LO.print("OTR price compared");
			System.out.println("OTR price compared");
			flag = true;
		} else {
			LO.print("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
			System.out
					.println("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
		}

		return flag;
	}
	
	public boolean verify_quote_summary_values_for_broker_pcp_cp_from_excel_without_maintenance(
			double quote_summary_cost_otr_price_from_screen_converted, String sheet_name) throws IOException {

		LO.print("Reading values from excel sheet to compare it with quote summary on screen values");
		System.out.println("Reading values from excel sheet to compare it with quote summary on screen values");

		double otr_price_expected = GetExcelFormulaValue.get_formula_value(14, 4, sheet_name);

		LO.print("Comparing excel values with actual values on screen");
		System.out.println("Comparing excel values with actual values on screen");

		boolean flag = false;
		double diff1 = Difference.of_two_Double_Values(otr_price_expected,
				quote_summary_cost_otr_price_from_screen_converted);
		if (diff1 < 0.2) {
			LO.print("OTR price compared");
			System.out.println("OTR price compared");
			flag = true;
		} else {
			LO.print("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
			System.out
					.println("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
		}

		return flag;
	}


	public boolean verify_quote_summary_values_for_broker_purchase_from_excel_for_used_vehicle(
			double quote_summary_cost_otr_price_from_screen_converted, String sheet_name) throws IOException {

		LO.print("Reading values from excel sheet to compare it with quote summary on screen values");
		System.out.println("Reading values from excel sheet to compare it with quote summary on screen values");

		double otr_price_expected = GetExcelFormulaValue.get_formula_value(18, 4, sheet_name);

		LO.print("Comparing excel values with actual values on screen");
		System.out.println("Comparing excel values with actual values on screen");

		boolean flag = false;
		double diff1 = Difference.of_two_Double_Values(otr_price_expected,
				quote_summary_cost_otr_price_from_screen_converted);
		if (diff1 < 0.2) {
			LO.print("OTR price compared");
			System.out.println("OTR price compared");
			flag = true;
		} else {
			LO.print("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
			System.out
					.println("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
		}

		return flag;
	}

	public boolean verify_holding_cost_before_editing_cap_data_with_maintenance(WebDriver driver,
			WebElement holding_cost_summary_terms, WebElement holding_cost_summary_mileage,
			WebElement holding_cost_summary_residual_value_used, WebElement total_monthly_holding_cost,
			WebElement holding_cost_maintenance_cost_used, WebElement holding_cost_percentage_cap_residual_value_used,
			WebElement total_cap_maintenance_value, String maintenance_required, String target_rental,
			String residual_value_used_from_excel, String maintenance_cost_used_from_excel,
			String percentage_cap_residual_value_used, String percentage_cap_maintenance_cost_used, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {

		LO.print("");
		System.out.println("");

		LO.print("***Verifying Holding Cost before editing CAP data*** ");
		System.out.println("***Verifying Holding Cost before editing CAP data*** ");
		Thread.sleep(3000);
		ExplicitWait.visibleElement(driver, holding_cost_summary_terms, 40);

		double duration = Double.parseDouble(holding_cost_summary_terms.getText().substring(0, 2));

		ExplicitWait.visibleElement(driver, holding_cost_summary_mileage, 30);

		double annual_mileage = Double.parseDouble(RemoveComma.of(holding_cost_summary_mileage.getText()));

		ExplicitWait.visibleElement(driver, holding_cost_summary_residual_value_used, 30);

		double used_residual_value = Double
				.parseDouble(RemoveComma.of(holding_cost_summary_residual_value_used.getText().substring(2)));

		ExplicitWait.visibleElement(driver, total_cap_maintenance_value, 30);

		double total_cap_maintenance_value_annual_converted_double = Double
				.parseDouble(RemoveComma.of(total_cap_maintenance_value.getText().substring(2)));

		LO.print("Getting on screen values from Holding Cost Page");
		System.out.println("Getting on screen values from Holding Cost Page");

		LO.print("Duration(Terms) =" + duration);
		System.out.println("Duration(Terms) =" + duration);

		LO.print("Annual_mileage =" + annual_mileage);
		System.out.println("Annual_mileage =" + annual_mileage);

		LO.print("Residual_value_from_screen =" + used_residual_value);
		System.out.println("Residual_value_from_screen =" + used_residual_value);

		LO.print("Writing Holding Cost Summary values to excel has been started");
		System.out.println("Writing Holding Cost Summary values to excel has been started");

		double residual_value_used_from_excel_converted = Double.parseDouble(residual_value_used_from_excel);
		double maintenance_cost_used_from_excel_converted = Double.parseDouble(maintenance_cost_used_from_excel);

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(25).getCell(1).setCellValue(maintenance_required);
		wb.getSheet(sheet_name).getRow(28).getCell(7).setCellValue(duration);
		wb.getSheet(sheet_name).getRow(29).getCell(7).setCellValue(annual_mileage);

		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value * 1.2);
		} else {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value);

		}
		wb.getSheet(sheet_name).getRow(31).getCell(8).setCellValue(total_cap_maintenance_value_annual_converted_double);
		wb.getSheet(sheet_name).getRow(34).getCell(7).setCellValue(Double.parseDouble(prop.getProperty("base_rate")));
		wb.getSheet(sheet_name).getRow(29).getCell(1).setCellValue(target_rental);
		wb.getSheet(sheet_name).getRow(44).getCell(0).setCellValue(100);
		wb.getSheet(sheet_name).getRow(44).getCell(2).setCellValue(100);

		if (sheet_name.equals("Formula1-FL")) {
			wb.getSheet(sheet_name).getRow(106).getCell(2).setCellValue("YES");
		}

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel has been started");
		System.out.println("Reading Monthly Holding Cost value from excel has been started");

		double monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		LO.print("Reading Monthly Holding Cost value from excel has been completed");
		System.out.println("Reading Monthly Holding Cost value from excel has been completed");

		String monthly_holding_cost = total_monthly_holding_cost.getText().substring(2);

		String total_monthly_holding_cost_from_screen = RemoveComma.of(monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_screen =" + monthly_holding_cost);
		System.out.println("Total_monthly_holding_cost_from_screen " + monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_excel =" + monthly_holding_cost_expected);
		System.out.println("Total_monthly_holding_cost_from_excel " + monthly_holding_cost_expected);

		double total_monthly_holding_cost_actual1 = Double.parseDouble(total_monthly_holding_cost_from_screen);
		double diff = Difference.of_two_Double_Values(monthly_holding_cost_expected,
				total_monthly_holding_cost_actual1);
		boolean flag = false;
		if (diff < 0.2) {
			flag = true;

			LO.print("Total Monthly Holding Cost based on CAP data is Verified and Found OK");
			System.out.println("Total Monthly Holding Cost based on CAP data is Verified and Found OK");

		} else {
			LO.print("Total Monthly Holding Cost based on CAP data is Verified but Found Wrong");
			System.err.println("Total Monthly Holding Cost based on CAP data is Verified but Found Wrong");

		}
		return flag;

	}

	public boolean edit_percentage_residual_and_maint_cost_then_verify_holding_cost_with_maintenance(WebDriver driver,
			WebElement holding_cost_summary_terms, WebElement holding_cost_summary_mileage,
			WebElement holding_cost_summary_residual_value_used, WebElement total_monthly_holding_cost,
			WebElement holding_cost_maintenance_cost_used, WebElement holding_cost_percentage_cap_residual_value_used,
			WebElement total_cap_maintenance_value, String maintenance_required, String target_rental,
			String residual_value_used_from_excel, String maintenance_cost_used_from_excel,
			String percentage_cap_residual_value_used, String percentage_cap_maintenance_cost_used, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {

		LO.print("");
		System.out.println("");

		LO.print("Editing % CAP Residual Value and % CAP Maintenance cost used");
		System.out.println("Editing % CAP Residual Value and % CAP Maintenance cost used");

		ExplicitWait.visibleElement(driver, holding_cost_summary_terms, 40);

		double duration = Double.parseDouble(holding_cost_summary_terms.getText().substring(0, 2));

		ExplicitWait.visibleElement(driver, holding_cost_summary_mileage, 30);

		double annual_mileage = Double.parseDouble(RemoveComma.of(holding_cost_summary_mileage.getText()));

		ExplicitWait.visibleElement(driver, holding_cost_summary_residual_value_used, 30);

		double used_residual_value = Double
				.parseDouble(RemoveComma.of(holding_cost_summary_residual_value_used.getText().substring(2)));

		ExplicitWait.visibleElement(driver, total_cap_maintenance_value, 30);

		double total_cap_maintenance_value_annual_converted_double = Double
				.parseDouble(RemoveComma.of(total_cap_maintenance_value.getText().substring(2)));

		LO.print("Changing % CAP Residual Value (from test  data) to " + percentage_cap_residual_value_used);
		System.out.println("Changing % CAP Residual Value (from test  data) to " + percentage_cap_residual_value_used);

		LO.print("Changing % CAP Maintenance cost (from test  data) to " + percentage_cap_maintenance_cost_used);
		System.out.println(
				"Changing % CAP Maintenance cost(from test  data)  to " + percentage_cap_maintenance_cost_used);

		LO.print("Writing Holding Cost Summary values to excel has been started");
		System.out.println("Writing Holding Cost Summary values to excel has been started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(25).getCell(1).setCellValue(maintenance_required);
		wb.getSheet(sheet_name).getRow(28).getCell(7).setCellValue(duration);
		wb.getSheet(sheet_name).getRow(29).getCell(7).setCellValue(annual_mileage);
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value * 1.2);
		} else {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value);

		}

		wb.getSheet(sheet_name).getRow(31).getCell(8).setCellValue(total_cap_maintenance_value_annual_converted_double);
		wb.getSheet(sheet_name).getRow(29).getCell(1).setCellValue(target_rental);
		wb.getSheet(sheet_name).getRow(44).getCell(0).setCellValue(percentage_cap_residual_value_used);
		wb.getSheet(sheet_name).getRow(44).getCell(2).setCellValue(percentage_cap_maintenance_cost_used);

		if (sheet_name.equals("Formula1-FL")) {
			wb.getSheet(sheet_name).getRow(106).getCell(2).setCellValue("YES");
		}

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel has been started");
		System.out.println("Reading Monthly Holding Cost value from excel has been started");

		double monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		LO.print("Reading Monthly Holding Cost value from excel has been completed");
		System.out.println("Reading Monthly Holding Cost value from excel has been completed");

		String monthly_holding_cost = total_monthly_holding_cost.getText().substring(2);

		String total_monthly_holding_cost_from_screen = RemoveComma.of(monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_screen =" + monthly_holding_cost);
		System.out.println("Total_monthly_holding_cost_from_screen " + monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_excel =" + monthly_holding_cost_expected);
		System.out.println("Total_monthly_holding_cost_from_excel " + monthly_holding_cost_expected);

		double total_monthly_holding_cost_actual1 = Double.parseDouble(total_monthly_holding_cost_from_screen);
		double diff = Difference.of_two_Double_Values(monthly_holding_cost_expected,
				total_monthly_holding_cost_actual1);
		boolean flag = false;
		if (diff < 0.2) {
			flag = true;
			LO.print(
					"Total Monthly Holding Cost After Editing % Residual Value and % Maintenance Cost is Verified and Found OK");
			System.out.println(
					"Total Monthly Holding Cost After Editing % Residual Value and % Maintenance Cost is Verified and Found OK");

		} else {
			LO.print(
					"Total Monthly Holding Cost After Editing % Residual Value and % Maintenance Cost is Verified but Found Wrong");
			System.err.println(
					"Total Monthly Holding Cost After Editing % Residual Value and % Maintenance Cost is Verified but Found Wrong");

		}

		return flag;

	}

	public boolean edit_residual_value_and_maint_cost_then_verify_holding_cost_with_maintenance(WebDriver driver,
			WebElement holding_cost_summary_terms, WebElement holding_cost_summary_mileage,
			WebElement holding_cost_summary_residual_value_used, WebElement total_monthly_holding_cost,
			WebElement holding_cost_maintenance_cost_used, WebElement holding_cost_percentage_cap_residual_value_used,
			WebElement total_cap_maintenance_value, String maintenance_required, String target_rental,
			String residual_value_used_from_excel, String maintenance_cost_used_from_excel,
			String percentage_cap_residual_value_used, String percentage_cap_maintenance_cost_used, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {

		LO.print("");
		System.out.println("");

		LO.print("Editing Residual value and Maintenance cost used");
		System.out.println("Editing Residual value and Maintenance cost used");

		ExplicitWait.visibleElement(driver, holding_cost_summary_terms, 40);

		double duration = Double.parseDouble(holding_cost_summary_terms.getText().substring(0, 2));

		ExplicitWait.visibleElement(driver, holding_cost_summary_mileage, 30);

		double annual_mileage = Double.parseDouble(RemoveComma.of(holding_cost_summary_mileage.getText()));

		LO.print("Changing Residual Value (from test data) to  =" + residual_value_used_from_excel);
		System.out.println("Changing Residual Value (from test data) to  =" + residual_value_used_from_excel);

		LO.print("Changing Maintenance Cost used (from test data) to =" + maintenance_cost_used_from_excel);
		System.out.println("Changing Maintenance Cost used (from test data) to =" + maintenance_cost_used_from_excel);

		LO.print("Writing Holding Cost Summary values to excel has been started");
		System.out.println("Writing Holding Cost Summary values to excel has been started");

		double residual_value_used_from_excel_converted = Double.parseDouble(residual_value_used_from_excel);
		double maintenance_cost_used_from_excel_converted = Double.parseDouble(maintenance_cost_used_from_excel);

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(25).getCell(1).setCellValue(maintenance_required);
		wb.getSheet(sheet_name).getRow(28).getCell(7).setCellValue(duration);
		wb.getSheet(sheet_name).getRow(29).getCell(7).setCellValue(annual_mileage);

		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(residual_value_used_from_excel_converted * 1.2);
		} else {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(residual_value_used_from_excel_converted);

		}

		wb.getSheet(sheet_name).getRow(31).getCell(8)
				.setCellValue(maintenance_cost_used_from_excel_converted * duration);

		wb.getSheet(sheet_name).getRow(29).getCell(1).setCellValue(target_rental);
		wb.getSheet(sheet_name).getRow(44).getCell(0).setCellValue(100);
		wb.getSheet(sheet_name).getRow(44).getCell(2).setCellValue(100);

		if (sheet_name.equals("Formula1-FL")) {
			wb.getSheet(sheet_name).getRow(106).getCell(2).setCellValue("YES");
		}

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel has been started");
		System.out.println("Reading Monthly Holding Cost value from excel has been started");

		double monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		LO.print("Reading Monthly Holding Cost value from excel has been completed");
		System.out.println("Reading Monthly Holding Cost value from excel has been completed");

		String monthly_holding_cost = total_monthly_holding_cost.getText().substring(2);

		String total_monthly_holding_cost_from_screen = RemoveComma.of(monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_screen =" + monthly_holding_cost);
		System.out.println("Total_monthly_holding_cost_from_screen " + monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_excel =" + monthly_holding_cost_expected);
		System.out.println("Total_monthly_holding_cost_from_excel " + monthly_holding_cost_expected);

		double total_monthly_holding_cost_actual1 = Double.parseDouble(total_monthly_holding_cost_from_screen);
		double diff = Difference.of_two_Double_Values(monthly_holding_cost_expected,
				total_monthly_holding_cost_actual1);
		boolean flag = false;
		if (diff < 0.2) {
			flag = true;
			LO.print(
					"Total Monthly Holding Cost After Editing Residual Value and Maintenance Cost is Verified and Found OK");
			System.out.println(
					"Total Monthly Holding Cost After Editing Residual Value and Maintenance Cost is Verified and Found OK");
		}

		else {
			LO.print(
					"Total Monthly Holding Cost After Editing Residual Value and Maintenance Cost is Verified but Found Wrong");
			System.err.println(
					"Total Monthly Holding Cost After Editing Residual Value and Maintenance Cost is Verified but Found Wrong");

		}
		return flag;

	}

	public boolean edit_additional_terms_and_mileage_then_verify_holding_cost_with_maintenance(WebDriver driver,
			WebElement holding_cost_summary_terms, WebElement holding_cost_summary_mileage,
			WebElement total_monthly_holding_cost, String maintenance_required, String target_rental,
			String residual_value_used_from_screen, String maint_cost_used_from_screen, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {

		LO.print("");
		System.out.println("");

		LO.print("Editing additional terms and mileage");
		System.out.println("Editing additional terms and mileage");

		ExplicitWait.visibleElement(driver, holding_cost_summary_terms, 40);

		double duration = Double.parseDouble(holding_cost_summary_terms.getText().substring(0, 2));

		ExplicitWait.visibleElement(driver, holding_cost_summary_mileage, 30);

		double annual_mileage = Double.parseDouble(RemoveComma.of(holding_cost_summary_mileage.getText()));

		LO.print("Updated Residual Value from screen  (to be sent to calculation excel) is ="
				+ residual_value_used_from_screen);
		System.out.println("Updated Residual Value from screen  (to be sent to calculation excel) is ="
				+ residual_value_used_from_screen);

		LO.print("Updated Maintenance Cost from screen  (to be sent to calculation excel) is ="
				+ maint_cost_used_from_screen);
		System.out.println(
				"Updated Maintenance Cost (to be sent to calculation excel) is =" + maint_cost_used_from_screen);

		LO.print("Writing Holding Cost Summary values to excel has been started");
		System.out.println("Writing Holding Cost Summary values to excel has been started");

		double residual_value_used = Double.parseDouble(residual_value_used_from_screen);
		double maintenance_cost_used = Double.parseDouble(maint_cost_used_from_screen);

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(25).getCell(1).setCellValue(maintenance_required);
		wb.getSheet(sheet_name).getRow(28).getCell(7).setCellValue(duration);
		wb.getSheet(sheet_name).getRow(29).getCell(7).setCellValue(annual_mileage);
		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(residual_value_used * 1.2);
		} else {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(residual_value_used);

		}

		wb.getSheet(sheet_name).getRow(31).getCell(8).setCellValue(maintenance_cost_used * duration);

		wb.getSheet(sheet_name).getRow(29).getCell(1).setCellValue(target_rental);
		wb.getSheet(sheet_name).getRow(44).getCell(0).setCellValue(100);
		wb.getSheet(sheet_name).getRow(44).getCell(2).setCellValue(100);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel has been started");
		System.out.println("Reading Monthly Holding Cost value from excel has been started");

		double monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		LO.print("Reading Monthly Holding Cost value from excel has been completed");
		System.out.println("Reading Monthly Holding Cost value from excel has been completed");

		String monthly_holding_cost = total_monthly_holding_cost.getText().substring(2);

		String total_monthly_holding_cost_from_screen = RemoveComma.of(monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_screen =" + monthly_holding_cost);
		System.out.println("Total_monthly_holding_cost_from_screen " + monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_excel =" + monthly_holding_cost_expected);
		System.out.println("Total_monthly_holding_cost_from_excel " + monthly_holding_cost_expected);

		double total_monthly_holding_cost_actual1 = Double.parseDouble(total_monthly_holding_cost_from_screen);
		double diff = Difference.of_two_Double_Values(monthly_holding_cost_expected,
				total_monthly_holding_cost_actual1);
		boolean flag = false;
		if (diff < 0.2) {
			flag = true;
			LO.print("Total Monthly Holding Cost After Editing Additional terms and mileage is Verified and Found OK");
			System.out.println(
					"Total Monthly Holding Cost After Editing Additional terms and mileage is Verified and Found OK");
		}

		else {
			LO.print(
					"Total Monthly Holding Cost After Editing Additional terms and mileage is Verified but Found Wrong");
			System.err.println(
					"Total Monthly Holding Cost After Editing Additional terms and mileage is Verified but Found Wrong");

		}
		return flag;

	}

	public boolean edit_additional_terms_and_mileage_then_verify_holding_cost_without_maintenance(WebDriver driver,
			WebElement holding_cost_summary_terms, WebElement holding_cost_summary_mileage,
			WebElement total_monthly_holding_cost, String maintenance_required, String target_rental,
			String residual_value_used_from_screen, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {

		LO.print("");
		System.out.println("");

		LO.print("Editing additional terms and mileage");
		System.out.println("Editing additional terms and mileage");

		ExplicitWait.visibleElement(driver, holding_cost_summary_terms, 40);

		double duration = Double.parseDouble(holding_cost_summary_terms.getText().substring(0, 2));

		ExplicitWait.visibleElement(driver, holding_cost_summary_mileage, 30);

		double annual_mileage = Double.parseDouble(RemoveComma.of(holding_cost_summary_mileage.getText()));

		LO.print("Updated Residual Value from screen  (to be sent to calculation excel) is ="
				+ residual_value_used_from_screen);
		System.out.println("Updated Residual Value from screen  (to be sent to calculation excel) is ="
				+ residual_value_used_from_screen);

		LO.print("Writing Holding Cost Summary values to excel has been started");
		System.out.println("Writing Holding Cost Summary values to excel has been started");

		double residual_value_used = Double.parseDouble(residual_value_used_from_screen);

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(25).getCell(1).setCellValue(maintenance_required);
		wb.getSheet(sheet_name).getRow(28).getCell(7).setCellValue(duration);
		wb.getSheet(sheet_name).getRow(29).getCell(7).setCellValue(annual_mileage);

		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("LCV")) {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(residual_value_used * 1.2);
		} else {
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(residual_value_used);

		}

		wb.getSheet(sheet_name).getRow(29).getCell(1).setCellValue(target_rental);
		wb.getSheet(sheet_name).getRow(44).getCell(0).setCellValue(100);
		wb.getSheet(sheet_name).getRow(44).getCell(2).setCellValue(100);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel has been started");
		System.out.println("Reading Monthly Holding Cost value from excel has been started");

		double monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		LO.print("Reading Monthly Holding Cost value from excel has been completed");
		System.out.println("Reading Monthly Holding Cost value from excel has been completed");

		String monthly_holding_cost = total_monthly_holding_cost.getText().substring(2);

		String total_monthly_holding_cost_from_screen = RemoveComma.of(monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_screen =" + monthly_holding_cost);
		System.out.println("Total_monthly_holding_cost_from_screen " + monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_excel =" + monthly_holding_cost_expected);
		System.out.println("Total_monthly_holding_cost_from_excel " + monthly_holding_cost_expected);

		double total_monthly_holding_cost_actual1 = Double.parseDouble(total_monthly_holding_cost_from_screen);
		double diff = Difference.of_two_Double_Values(monthly_holding_cost_expected,
				total_monthly_holding_cost_actual1);
		boolean flag = false;
		if (diff < 0.2) {
			flag = true;
			LO.print("Total Monthly Holding Cost After Editing Additional terms and mileage is Verified and Found OK");
			System.out.println(
					"Total Monthly Holding Cost After Editing Additional terms and mileage is Verified and Found OK");
		}

		else {
			LO.print(
					"Total Monthly Holding Cost After Editing Additional terms and mileage is Verified but Found Wrong");
			System.err.println(
					"Total Monthly Holding Cost After Editing Additional terms and mileage is Verified but Found Wrong");

		}
		return flag;

	}

	public boolean verify_holding_cost_for_used_car_with_maintenance(WebDriver driver,
			WebElement holding_cost_summary_terms, WebElement holding_cost_summary_mileage,
			WebElement holding_cost_summary_residual_value_used, WebElement total_monthly_holding_cost,
			WebElement holding_cost_maintenance_cost_used, WebElement holding_cost_percentage_cap_residual_value_used,
			WebElement total_cap_maintenance_value, String maintenance_required, String target_rental,
			String residual_value_used_from_excel, String maintenance_cost_used_from_excel,
			String percentage_cap_residual_value_used, String percentage_cap_maintenance_cost_used, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {

		LO.print("***********Holding Cost Calculations has been Started*************");
		System.out.println("***********Holding Cost Calculations has been Started*************");
		Thread.sleep(3000);
		ExplicitWait.visibleElement(driver, holding_cost_summary_terms, 40);

		double duration = Double.parseDouble(holding_cost_summary_terms.getText().substring(0, 2));

		ExplicitWait.visibleElement(driver, holding_cost_summary_mileage, 30);
		String mileage = holding_cost_summary_mileage.getText();

		String holding_cost_annual_mileage = RemoveComma.of(mileage);

		double annual_mileage = Double.parseDouble(holding_cost_annual_mileage);

		ExplicitWait.visibleElement(driver, holding_cost_summary_residual_value_used, 30);
		String residual_value_used = holding_cost_summary_residual_value_used.getText().substring(2);

		String reidual_value_used_converted = RemoveComma.of(residual_value_used);

		double used_residual_value = Double.parseDouble(reidual_value_used_converted);

		String total_cap_maintenance_value_annual = total_cap_maintenance_value.getText().substring(2);

		String total_cap_maintenance_value_annual_converted = RemoveComma.of(total_cap_maintenance_value_annual);

		double total_cap_maintenance_value_annual_converted_double = Double
				.parseDouble(total_cap_maintenance_value_annual_converted);

		LO.print("Getting on screen values from Holding Cost Page");
		System.out.println("Getting on screen values from Holding Cost Page");

		LO.print("Duration(Terms) =" + duration);
		System.out.println("Duration(Terms) =" + duration);

		LO.print("Annual_mileage =" + annual_mileage);
		System.out.println("Annual_mileage =" + annual_mileage);

		LO.print("Cap_residual_value =" + used_residual_value);
		System.out.println("Cap_residual_value =" + used_residual_value);

//		LO.print("used_residual_value_from_test_data ="+residual_value_used_from_excel);
//		System.out.println("used_residual_value_from_test_data ="+residual_value_used_from_excel);
//		
//		LO.print("maintenance_cost_used_test_data ="+maintenance_cost_used_from_excel);
//		System.out.println("maintenance_cost_used_test_data ="+maintenance_cost_used_from_excel);

		LO.print("Writing Holding Cost Summary values to excel has been started");
		System.out.println("Writing Holding Cost Summary values to excel has been started");

		double residual_value_used_from_excel_converted = Double.parseDouble(residual_value_used_from_excel);
		double maintenance_cost_used_from_excel_converted = Double.parseDouble(maintenance_cost_used_from_excel);

		LO.print("maintenance_cost_used_test_data converted =" + maintenance_cost_used_from_excel_converted);
		System.out.println("maintenance_cost_used_test_data converted =" + maintenance_cost_used_from_excel_converted);

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(25).getCell(1).setCellValue(maintenance_required);
		wb.getSheet(sheet_name).getRow(28).getCell(7).setCellValue(duration);
		wb.getSheet(sheet_name).getRow(29).getCell(7).setCellValue(annual_mileage);

		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("used_LCV")) {		
		wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value*1.2);
		}
		else
		{
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value);

		}
		wb.getSheet(sheet_name).getRow(31).getCell(8).setCellValue(total_cap_maintenance_value_annual_converted_double);

		wb.getSheet(sheet_name).getRow(29).getCell(1).setCellValue(target_rental);
		wb.getSheet(sheet_name).getRow(44).getCell(0).setCellValue(100);
		wb.getSheet(sheet_name).getRow(44).getCell(2).setCellValue(100);
		// wb.getSheet(sheet_name).getRow(39).getCell(7).setCellFormula("A40");

		if (sheet_name.equals("Formula1-FL")) {
			wb.getSheet(sheet_name).getRow(106).getCell(2).setCellValue("YES");
		}

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel has been started");
		System.out.println("Reading Monthly Holding Cost value from excel has been started");

		double monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		LO.print("Reading Monthly Holding Cost value from excel has been completed");
		System.out.println("Reading Monthly Holding Cost value from excel has been completed");

		String monthly_holding_cost = total_monthly_holding_cost.getText().substring(2);

		String total_monthly_holding_cost_from_screen = RemoveComma.of(monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_screen =" + monthly_holding_cost);
		System.out.println("Total_monthly_holding_cost_from_screen " + monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_excel =" + monthly_holding_cost_expected);
		System.out.println("Total_monthly_holding_cost_from_excel " + monthly_holding_cost_expected);

		double total_monthly_holding_cost_actual1 = Double.parseDouble(total_monthly_holding_cost_from_screen);
		double diff = Difference.of_two_Double_Values(monthly_holding_cost_expected,
				total_monthly_holding_cost_actual1);
		boolean flag = false;
		if (diff < 0.2) {
			flag = true;
		}

		// writing values to excel

//		FileInputStream in1 = new FileInputStream(prop.getProperty("formula_excel_path"));
//		XSSFWorkbook wb1 = new XSSFWorkbook(in1);
//
//		wb1.getSheet(sheet_name).getRow(39).getCell(7).setCellFormula("A40/1.2");
//
//		FileOutputStream out1 = new FileOutputStream(prop.getProperty("formula_excel_path"));
//
//		wb1.write(out1);

		return flag;

	}

	public boolean verify_holding_cost_for_used_car_without_maintenance(WebDriver driver,
			WebElement holding_cost_summary_terms, WebElement holding_cost_summary_mileage,
			WebElement holding_cost_summary_residual_value_used, WebElement total_monthly_holding_cost,
			WebElement holding_cost_percentage_cap_residual_value_used, String maintenance_required,
			String target_rental, String residual_value_used_from_excel, String percentage_cap_residual_value_used,
			String sheet_name) throws IOException, InterruptedException, ClassNotFoundException {

		LO.print("***********Holding Cost Calculations has been Started*************");
		System.out.println("***********Holding Cost Calculations has been Started*************");
		Thread.sleep(3000);
		ExplicitWait.visibleElement(driver, holding_cost_summary_terms, 40);

		double duration = Double.parseDouble(holding_cost_summary_terms.getText().substring(0, 2));

		ExplicitWait.visibleElement(driver, holding_cost_summary_mileage, 30);
		String mileage = holding_cost_summary_mileage.getText();

		String holding_cost_annual_mileage = RemoveComma.of(mileage);

		double annual_mileage = Double.parseDouble(holding_cost_annual_mileage);

		ExplicitWait.visibleElement(driver, holding_cost_summary_residual_value_used, 30);
		String residual_value_used = holding_cost_summary_residual_value_used.getText().substring(2);

		String reidual_value_used_converted = RemoveComma.of(residual_value_used);

		double used_residual_value = Double.parseDouble(reidual_value_used_converted);

		LO.print("Getting on screen values from Holding Cost Page");
		System.out.println("Getting on screen values from Holding Cost Page");

		LO.print("Duration(Terms) =" + duration);
		System.out.println("Duration(Terms) =" + duration);

		LO.print("Annual_mileage =" + annual_mileage);
		System.out.println("Annual_mileage =" + annual_mileage);

		LO.print("Cap_residual_value =" + used_residual_value);
		System.out.println("Cap_residual_value =" + used_residual_value);

//		LO.print("used_residual_value_from_test_data ="+residual_value_used_from_excel);
//		System.out.println("used_residual_value_from_test_data ="+residual_value_used_from_excel);
//		
//		LO.print("maintenance_cost_used_test_data ="+maintenance_cost_used_from_excel);
//		System.out.println("maintenance_cost_used_test_data ="+maintenance_cost_used_from_excel);

		LO.print("Writing Holding Cost Summary values to excel has been started");
		System.out.println("Writing Holding Cost Summary values to excel has been started");

		double residual_value_used_from_excel_converted = Double.parseDouble(residual_value_used_from_excel);

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(25).getCell(1).setCellValue(maintenance_required);
		wb.getSheet(sheet_name).getRow(28).getCell(7).setCellValue(duration);
		wb.getSheet(sheet_name).getRow(29).getCell(7).setCellValue(annual_mileage);

		if (Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName().contains("used_LCV")) {		
			wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value*1.2);
			}
			else
			{
				wb.getSheet(sheet_name).getRow(30).getCell(7).setCellValue(used_residual_value);

			}

		wb.getSheet(sheet_name).getRow(31).getCell(8).setCellValue(0);

		wb.getSheet(sheet_name).getRow(29).getCell(1).setCellValue(target_rental);
		wb.getSheet(sheet_name).getRow(44).getCell(0).setCellValue(percentage_cap_residual_value_used);
		// wb.getSheet(sheet_name).getRow(39).getCell(7).setCellFormula("A40");

		if (sheet_name.equals("Formula1-FL")) {
			wb.getSheet(sheet_name).getRow(106).getCell(2).setCellValue("YES");
		}

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing Holding Cost Summary values to excel has been completed");
		System.out.println("Writing Holding Cost Summary values to excel has been completed");

		// excel code for reading calculated values from excel sheet

		LO.print("Reading Monthly Holding Cost value from excel has been started");
		System.out.println("Reading Monthly Holding Cost value from excel has been started");

		double monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		LO.print("Reading Monthly Holding Cost value from excel has been completed");
		System.out.println("Reading Monthly Holding Cost value from excel has been completed");

		String monthly_holding_cost = total_monthly_holding_cost.getText().substring(2);

		String total_monthly_holding_cost_from_screen = RemoveComma.of(monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_screen =" + monthly_holding_cost);
		System.out.println("Total_monthly_holding_cost_from_screen " + monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_excel =" + monthly_holding_cost_expected);
		System.out.println("Total_monthly_holding_cost_from_excel " + monthly_holding_cost_expected);

		double total_monthly_holding_cost_actual1 = Double.parseDouble(total_monthly_holding_cost_from_screen);
		double diff = Difference.of_two_Double_Values(monthly_holding_cost_expected,
				total_monthly_holding_cost_actual1);
		boolean flag = false;
		if (diff < 0.2) {
			flag = true;
		}

		// writing values to excel

//		FileInputStream in1 = new FileInputStream(prop.getProperty("formula_excel_path"));
//		XSSFWorkbook wb1 = new XSSFWorkbook(in1);
//
//		wb1.getSheet(sheet_name).getRow(39).getCell(7).setCellFormula("A40/1.2");
//
//		FileOutputStream out1 = new FileOutputStream(prop.getProperty("formula_excel_path"));
//
//		wb1.write(out1);

		return flag;

	}

	public boolean verify_customer_quote_calculations_for_one_payment_options_with_maintenance(WebDriver driver,
			WebElement customer_quote_payment_profile_dropdown, WebElement part_exchange_payment,
			WebElement actual_part_exchange_value, String actual_part_exchange_value_from_excel,
			WebElement given_part_exchange_value, String given_part_exchange_value_from_excel,
			WebElement less_finance_settlement, String less_finance_settlement_from_excel, WebElement order_deposit,
			String order_deposit_from_excel, WebElement document_fee, String document_fee_from_excel, String upsell,
			WebElement customer_quote_monthly_finance_rental, WebElement customer_quote_monthly_maintenance_rental,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name) throws IOException, InterruptedException {

		LO.print("************Calculations for Customer Quote Page has been started***********");
		System.out.println("************Calculations for Customer Quote Page has been started***********");

		ExplicitWait.clickableElement(driver, customer_quote_payment_profile_dropdown, 30);
		Select select = new Select(customer_quote_payment_profile_dropdown);
		Thread.sleep(5000);
		select.selectByIndex(0);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		LO.print("Payment Profile Monthly in Advance option has been selected");
		System.out.println("Payment Profile Monthly in Advance option has been selected");

		List<WebElement> list_dropdown_options = select.getOptions();
		String dropdown_option = list_dropdown_options.get(0).getText();

		int dropdown_options_number = list_dropdown_options.size();

		LO.print("Writing values to Excel for customer quote calculation -started");
		System.out.println("Writing values to Excel for customer quote calculation -started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(98).getCell(1).setCellValue(" " + dropdown_option + " ");
		wb.getSheet(sheet_name).getRow(101).getCell(0).setCellValue(Double.parseDouble(document_fee_from_excel));
		wb.getSheet(sheet_name).getRow(104).getCell(0).setCellValue(maintenance_required);
		wb.getSheet(sheet_name).getRow(104).getCell(1).setCellValue(Double.parseDouble(maintenance_margin));
		wb.getSheet(sheet_name).getRow(104).getCell(3).setCellValue(Double.parseDouble(initial_payment));
		wb.getSheet(sheet_name).getRow(109).getCell(1).setCellValue("NO");
		wb.getSheet(sheet_name).getRow(123).getCell(1).setCellValue(Double.parseDouble(target_rental));
		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		LO.print("Writing values to Excel for customer quote calculation -completed");
		System.out.println("Writing values to Excel for customer quote calculation -completed");

		LO.print("Reading  Monthly Finance Rental and monthly maintenance cost from  Excel   -started");
		System.out.println("Reading  Monthly Finance Rental and monthly maintenance cost from  Excel   -started");

		double monthly_finance_rental_expected = GetExcelFormulaValue.get_formula_value(89, 1, sheet_name);

		double monthly_maintenance_rental_expected = GetExcelFormulaValue.get_formula_value(88, 1, sheet_name);

		LO.print("Reading  Monthly Finance Rental and monthly maintenance cost from  Excel  -completed");
		System.out.println("Reading  Monthly Finance Rental and monthly maintenance cost from  Excel   -completed");

		LO.print("Monthly Finance Rental from Excel is =" + monthly_finance_rental_expected);
		System.out.println("Monthly Finance Rental from Excel is =" + monthly_finance_rental_expected);

		LO.print("Monthly maintenance Rental from Excel is =" + monthly_maintenance_rental_expected);
		System.out.println("Monthly maintenance Rental from Excel is =" + monthly_maintenance_rental_expected);

		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);
		Thread.sleep(4000);
		String monthly_finance_rental = customer_quote_monthly_finance_rental.getText().substring(2);

		String monthly_finance_rental_actual = RemoveComma.of(monthly_finance_rental);

		double monthly_finance_rental_actual_converted = Double.parseDouble(monthly_finance_rental_actual);

		ExplicitWait.visibleElement(driver, customer_quote_monthly_maintenance_rental, 60);
		Thread.sleep(4000);
		String monthly_maintenance_rental = customer_quote_monthly_maintenance_rental.getText().substring(2);

		String monthly_maintenance_rental_actual = RemoveComma.of(monthly_maintenance_rental);

		double monthly_mainte_rental_converted_actual = Double.parseDouble(monthly_maintenance_rental_actual);

		LO.print("Monthly Finance Rental from screen is =" + monthly_finance_rental_actual_converted);
		System.out.println("Monthly Finance Rental from screen is =" + monthly_finance_rental_actual_converted);

		LO.print("Monthly maintenance Rental from screen is =" + monthly_mainte_rental_converted_actual);
		System.out.println("Monthly maintenance Rental from screen is =" + monthly_mainte_rental_converted_actual);

		double diff1 = Difference.of_two_Double_Values(monthly_finance_rental_expected,
				monthly_finance_rental_actual_converted);
		double diff2 = Difference.of_two_Double_Values(monthly_maintenance_rental_expected,
				monthly_mainte_rental_converted_actual);
		int count = 0;
		boolean flag = false;
		if ((diff1 < 0.3) && (diff2 < 0.3)) {
			flag = true;
		}

		return flag;

	}

	public boolean verify_customer_quote_calculations_for_all_payment_options_with_maintenance(WebDriver driver,
			WebElement customer_quote_payment_profile_dropdown, WebElement customer_quote_monthly_finance_rental,
			WebElement customer_quote_monthly_maintenance_rental, WebElement initial_payment_input_field,
			String initial_payment_from_test_data, String sheet_name) throws IOException, InterruptedException {

		LO.print("Calculations with different payment profiles for customer quote has been started");
		System.out.println("Calculations with different payment profiles for customer quote has been started");

		ExplicitWait.clickableElement(driver, customer_quote_payment_profile_dropdown, 30);

		Select select = new Select(customer_quote_payment_profile_dropdown);
		List<WebElement> list_dropdown_options = select.getOptions();
		int dropdown_options_number = list_dropdown_options.size();
		int count = 0;
		boolean flag = false;
		for (int i = 0; i < list_dropdown_options.size(); i++) {
			select.selectByIndex(i);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

			String dropdown_option = list_dropdown_options.get(i).getText();
			if (i == 1) {
				Thread.sleep(2000);
				Click.on(driver, initial_payment_input_field, 20);
				Actions act = new Actions(driver);
				initial_payment_input_field.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

				Click.sendKeys(driver, initial_payment_input_field, initial_payment_from_test_data, 40);

				act.sendKeys(Keys.TAB).build().perform();
				Thread.sleep(5000);
			}

			ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);
			Thread.sleep(5000);
			String monthly_finance_rental = customer_quote_monthly_finance_rental.getText().substring(2);

			String monthly_finance_rental_actual = RemoveComma.of(monthly_finance_rental);

			double monthly_finance_rental_actual_converted = Double.parseDouble(monthly_finance_rental_actual);

			ExplicitWait.visibleElement(driver, customer_quote_monthly_maintenance_rental, 30);
			Thread.sleep(5000);
			String monthly_maintenance_rental = customer_quote_monthly_maintenance_rental.getText().substring(2);

			String monthly_maintenance_rental_actual = RemoveComma.of(monthly_maintenance_rental);

			double monthly_mainte_rental_converted_actual = Double.parseDouble(monthly_maintenance_rental_actual);

			FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
			XSSFWorkbook wb = new XSSFWorkbook(in);

			wb.getSheet(sheet_name).getRow(98).getCell(1).setCellValue(" " + dropdown_option + " ");
			if (i == 1) {
				wb.getSheet(sheet_name).getRow(104).getCell(3).setCellValue(initial_payment_from_test_data);
			}
			FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
			wb.write(out);

			double temp_monthly_finance_rental_expected = GetExcelFormulaValue.get_formula_value(89, 1, sheet_name);
			double temp_monthly_maintenance_rental_expected = GetExcelFormulaValue.get_formula_value(88, 1, sheet_name);

			double monthly_finance_rental_expected = Math.round(temp_monthly_finance_rental_expected * 100.0) / 100.0;
			double monthly_maintenance_rental_expected = Math.round(temp_monthly_maintenance_rental_expected * 100.0)
					/ 100.0;

			System.out.println("monthly_finance_rental_actual_converted =" + monthly_finance_rental_actual_converted);
			System.out.println("monthly finance rental expected =" + monthly_finance_rental_expected);

			System.out.println("monthly_mainte_rental_converted_actual =" + monthly_mainte_rental_converted_actual);
			System.out.println("monthly_maintenance_rental_expected =" + monthly_maintenance_rental_expected);

			double diff1 = Difference.of_two_Double_Values(monthly_finance_rental_expected,
					monthly_finance_rental_actual_converted);
			double diff2 = Difference.of_two_Double_Values(monthly_maintenance_rental_expected,
					monthly_mainte_rental_converted_actual);
			System.out.println("diff1 " + diff1);
			System.out.println("diff2 " + diff2);
			if ((diff1 < 0.3) && (diff2 < 0.3)) {
				count++;
			}
			if (count == dropdown_options_number) {
				flag = true;
			}

		}

		LO.print("Calculations with different payment profiles for customer quote has been ended");
		System.out.println("Calculations with different payment profiles for customer quote has been ended");
		return flag;

	}

	public boolean verify_quote_summary_values_from_excel_with_maintenance(
			double quote_summary_cost_otr_price_from_screen_converted,
			double quote_summary_total_monthly_holding_cost_from_screen_converted,
			double quote_summary_monthly_finance_rental_from_screen_converted,
			double quote_summary_monthly_maintenance_rental_from_screen_converted,
			double quote_summary_monthly_total_rental_from_screen_converted, String sheet_name) throws IOException {
		LO.print("Reading values from excel sheet to compare it with quote summary on screen values");
		System.out.println("Reading values from excel sheet to compare it with quote summary on screen values");

		double otr_price_expected = GetExcelFormulaValue.get_formula_value(14, 4, sheet_name);
		double total_monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);
		double finance_rental_expected = GetExcelFormulaValue.get_formula_value(89, 1, sheet_name);
		double maintenance_rental_expected = GetExcelFormulaValue.get_formula_value(88, 1, sheet_name);
		double total_rental_expected = GetExcelFormulaValue.get_formula_value(90, 1, sheet_name);

		LO.print("Comparing excel values with actual values on screen");
		System.out.println("Comparing excel values with actual values on screen");

		double diff1 = Difference.of_two_Double_Values(otr_price_expected,
				quote_summary_cost_otr_price_from_screen_converted);
		double diff2 = Difference.of_two_Double_Values(total_monthly_holding_cost_expected,
				quote_summary_total_monthly_holding_cost_from_screen_converted);
		double diff3 = Difference.of_two_Double_Values(finance_rental_expected,
				quote_summary_monthly_finance_rental_from_screen_converted);
		double diff4 = Difference.of_two_Double_Values(maintenance_rental_expected,
				quote_summary_monthly_maintenance_rental_from_screen_converted);
		double diff5 = Difference.of_two_Double_Values(total_rental_expected,
				quote_summary_monthly_total_rental_from_screen_converted);

		int count = 0;
		boolean flag = false;
		if (diff1 < 0.2) {
			LO.print("OTR price compared");
			System.out.println("OTR price compared");
			count++;
		} else {
			System.out
					.println("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
		}

		if (diff2 < 0.2) {
			LO.print("Total monthly holding cost compared");
			System.out.println("Total monthly holding cost compared");
			count++;
		} else {
			System.out.println(
					"Found difference between Monthly Holding Cost actual and Monthly Holding Cost expected on Quote Summary Page");
		}

		if (diff3 < 0.2) {
			LO.print("Finance Rental compared");
			System.out.println("Finance Rental compared");
			count++;
		} else {
			System.out.println(
					"Found difference between Finance Rental actual and Finance Rental expected on Quote Summary Page");
		}

		if (diff4 < 0.2) {
			LO.print("Maintenance Rental compared");
			System.out.println("Maintenance Rental compared");
			count++;
		} else {
			System.out.println(
					"Found difference between Maintenance Rental actual and Maintenance Rental expected on Quote Summary Page");
		}

		if (diff5 < 0.2) {
			LO.print("total Rental compared");
			System.out.println("total Rental compared");
			count++;
		} else {
			System.out.println(
					"Found difference between total Rental actual and total Rental expected on Quote Summary Page");
		}

		if (count == 5) {
			flag = true;
		}

		return flag;
	}

	public boolean verify_quote_summary_values_for_broker_bch_pch_fl_from_excel_with_maintenance(
			double quote_summary_cost_otr_price_from_screen_converted, String sheet_name) throws IOException {
		LO.print("Reading values from excel sheet to compare it with quote summary on screen values");
		System.out.println("Reading values from excel sheet to compare it with quote summary on screen values");

		double otr_price_expected = GetExcelFormulaValue.get_formula_value(14, 4, sheet_name);

		LO.print("Comparing excel values with actual values on screen");
		System.out.println("Comparing excel values with actual values on screen");

		boolean flag = false;
		double diff1 = Difference.of_two_Double_Values(otr_price_expected,
				quote_summary_cost_otr_price_from_screen_converted);
		if (diff1 < 0.2) {
			LO.print("OTR price compared");
			System.out.println("OTR price compared");
			flag = true;
		} else {
			LO.print("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
			System.out
					.println("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
		}

		return flag;

	}

	public boolean verify_quote_summary_values_for_broker_pcp_cp_from_excel_with_maintenance(
			double quote_summary_cost_otr_price_from_screen_converted, String sheet_name) throws IOException {
		LO.print("Reading values from excel sheet to compare it with quote summary on screen values");
		System.out.println("Reading values from excel sheet to compare it with quote summary on screen values");

		double otr_price_expected = GetExcelFormulaValue.get_formula_value(14, 4, sheet_name);

		LO.print("Comparing excel values with actual values on screen");
		System.out.println("Comparing excel values with actual values on screen");

		boolean flag = false;
		double diff1 = Difference.of_two_Double_Values(otr_price_expected,
				quote_summary_cost_otr_price_from_screen_converted);
		if (diff1 < 0.2) {
			LO.print("OTR price compared");
			System.out.println("OTR price compared");
			flag = true;
		} else {
			LO.print("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
			System.out
					.println("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
		}

		return flag;

	}

	public boolean verify_quote_summary_values_for_broker_pcp_cp_from_excel_for_used_vehicle(
			double quote_summary_cost_otr_price_from_screen_converted, String sheet_name) throws IOException {
		LO.print("Reading values from excel sheet to compare it with quote summary on screen values");
		System.out.println("Reading values from excel sheet to compare it with quote summary on screen values");

		double otr_price_expected = GetExcelFormulaValue.get_formula_value(18, 4, sheet_name);

		LO.print("Comparing excel values with actual values on screen");
		System.out.println("Comparing excel values with actual values on screen");

		boolean flag = false;
		double diff1 = Difference.of_two_Double_Values(otr_price_expected,
				quote_summary_cost_otr_price_from_screen_converted);
		if (diff1 < 0.2) {
			LO.print("OTR price compared");
			System.out.println("OTR price compared");
			flag = true;
		} else {
			LO.print("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
			System.out
					.println("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
		}

		return flag;

	}

	
	public void write_test_data_values_to_excel_for_bch_flow_without_maintenance(String quoteReference,
			String quoteExpiryDate, String terms, String milesPerAnnum, String maintenance_required,
			String monthlyFinanceRental, String documentFee, String penceperExcessMileFinance, String sheet_name)
			throws IOException {

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(31).getCell(0).setCellValue(" Monthly in advance ");
		wb.getSheet(sheet_name).getRow(34).getCell(1).setCellValue(Double.parseDouble(terms));
		wb.getSheet(sheet_name).getRow(34).getCell(3).setCellValue(Double.parseDouble(milesPerAnnum));
		wb.getSheet(sheet_name).getRow(37).getCell(1).setCellValue(maintenance_required);
		wb.getSheet(sheet_name).getRow(37).getCell(3).setCellValue(Double.parseDouble(monthlyFinanceRental));
		wb.getSheet(sheet_name).getRow(40).getCell(0).setCellValue(0);
		wb.getSheet(sheet_name).getRow(40).getCell(3).setCellValue(Double.parseDouble(documentFee));
		wb.getSheet(sheet_name).getRow(43).getCell(0).setCellValue(Double.parseDouble(penceperExcessMileFinance));
		wb.getSheet(sheet_name).getRow(43).getCell(1).setCellValue(0);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

	}

	public void write_test_data_values_to_excel_for_bch_flow_with_maintenance(String quoteReference,
			String quoteExpiryDate, String terms, String milesPerAnnum, String maintenance_required,
			String monthlyFinanceRental, String monthlyMaintenanceRental, String documentFee,
			String penceperExcessMileFinance, String penceperExcessMileMaintenance, String sheet_name)
			throws IOException {

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(31).getCell(0).setCellValue(" Monthly in advance ");
		wb.getSheet(sheet_name).getRow(34).getCell(1).setCellValue(Double.parseDouble(terms));
		wb.getSheet(sheet_name).getRow(34).getCell(3).setCellValue(Double.parseDouble(milesPerAnnum));
		wb.getSheet(sheet_name).getRow(37).getCell(1).setCellValue(maintenance_required);
		wb.getSheet(sheet_name).getRow(37).getCell(3).setCellValue(Double.parseDouble(monthlyFinanceRental));
		wb.getSheet(sheet_name).getRow(40).getCell(0).setCellValue(Double.parseDouble(monthlyMaintenanceRental));
		wb.getSheet(sheet_name).getRow(40).getCell(3).setCellValue(Double.parseDouble(documentFee));
		wb.getSheet(sheet_name).getRow(43).getCell(0).setCellValue(Double.parseDouble(penceperExcessMileFinance));
		wb.getSheet(sheet_name).getRow(43).getCell(1).setCellValue(Double.parseDouble(penceperExcessMileMaintenance));

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

	}

	public void put_upsell_values_to_excel(String matrix_upsell, String referrer_upsell, String sheet_name)
			throws IOException {

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		if (sheet_name.contains("Formula1") || sheet_name.contains("BCH (Formula 3)")
				|| sheet_name.contains("BCH-F3")) {
			wb.getSheet(sheet_name).getRow(101).getCell(1).setCellValue(Double.parseDouble(matrix_upsell));
			wb.getSheet(sheet_name).getRow(101).getCell(3).setCellValue(Double.parseDouble(referrer_upsell));
		} else {
			wb.getSheet(sheet_name).getRow(107).getCell(1).setCellValue(Double.parseDouble(matrix_upsell));
			wb.getSheet(sheet_name).getRow(107).getCell(3).setCellValue(Double.parseDouble(referrer_upsell));
		}

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

	}

	public void put_customer_quote_summary_upsell_value_to_excel(double upsell, String sheet_name) throws IOException {

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		if (sheet_name.contains("Formula1") || sheet_name.contains("BCH (Formula 3)")
				|| sheet_name.contains("BCH-F3")) {
			wb.getSheet(sheet_name).getRow(101).getCell(1).setCellValue(upsell + upsell);
		} else {
			wb.getSheet(sheet_name).getRow(107).getCell(1).setCellValue(upsell + upsell);
		}

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

	}

	public void put_customer_quote_summary_final_balloon_payment_to_excel(double final_balloon_payment,
			String sheet_name) throws IOException {

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(39).getCell(9).setCellValue(final_balloon_payment + final_balloon_payment);

		wb.getSheet(sheet_name).getRow(39).getCell(7).setCellFormula("J40");

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

	}

	public void reset_final_balloon_payment_formula_to_excel(String sheet_name) throws IOException {

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(39).getCell(7).setCellFormula("A40/1.2");

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

	}

}
