package com.amt.CustomerQuotePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Difference;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.ReadExcelCalculation;
import com.amt.testUtil.RemoveComma;

public class CustomerQuotePageOutrightBCHPage extends TestBase {
	
	CustomerQuotePageOutrightBCHPage obj_cust_quote_outright_bchPage;
	ReadExcelCalculation obj_read_excel_calculation_page; 
	
	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;
	

	@FindBy(xpath = "//p[normalize-space()='Customer Quote']")
	private WebElement customer_quote;
	
	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-all-customer-quotes[1]/div[1]/app-aquisition-hire-agreement[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[6]/div[4]")
	private WebElement customer_quote_matrix_default_cell;
    
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[4]/div/p/strong")
	private WebElement customer_quote_monthly_finance_reantal;
	
	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/app-aquisition-header[1]/div[1]/div[2]/div[3]/button[1]")
	private WebElement save_button;
    
	@FindBy(xpath = "//select[@name='acquisitionPaymentProfileId']")
	private WebElement customer_quote_payment_profile_dropdown;
	
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[4]/div/p/strong")
	private WebElement customer_quote_monthly_finance_rental;
	
	@FindBy(xpath = "//*[@id='partExchange_1']/button/div")
	private WebElement part_exchange_payment;
	
	@FindBy(xpath = "//*[@id='otrPartExchange']")
	private WebElement actual_part_exchange_value;
	
	@FindBy(xpath = "//*[@id='partExchange']")
	private WebElement given_part_exchange_value;
	
	@FindBy(xpath = "//*[@id='lessFinanceSettlement']")
	private WebElement less_finance_settlement;
	
	@FindBy(xpath = "//*[@id='partExchange_2']/div/div/div[2]/ul/li[1]/div/input")
	private WebElement order_deposit;
	
	@FindBy(xpath = "//*[@id='DocumentFee']")
	private WebElement document_fee;
	
	
	
	@FindBy(xpath = "//label[@class='switch mr-1 ml-1']//span[@class='slider round']")
	private WebElement customer_quote_maintenance_toggle_button;
	
	@FindBy(xpath = "//*[@id=\"headingCustomerQuote\"]/div[2]/app-hire-customer-quote-summary-header/div/div[5]/div/p/strong")
	private WebElement customer_quote_monthly_maintenance_rental;
	
	@FindBy(xpath = "//input[@name='monetaryAmount']")
	private WebElement initial_payment_input_field;
	
	@FindBy(xpath = "//label[@class='switch mr-2']//span[@class='slider round']")
	private WebElement part_exchange_toggle;
	
	@FindBy(xpath = "//*[@id='securityDeposit']")
	private WebElement security_deposit_input_field;
	
	@FindBy(xpath = "//*[@id='upsell']")
	private WebElement matrix_upsell_input_field;
	
	@FindBy(xpath = "//*[@id='FinanceCommission']")
	private WebElement referrer_upsell_input_field;
	

	public CustomerQuotePageOutrightBCHPage() {
		PageFactory.initElements(driver, this);
	}

    
	public boolean customer_Quote_outright_BCH_OTR_calculation() throws InterruptedException {

		Click.on(driver, customer_quote, 50);
		ExplicitWait.clickableElement(driver, save_button, 0);
		Click.on(driver, save_button, 60);
		
		String page_title_after_save=driver.getTitle();
		System.out.println(page_title_after_save);
		return page_title_after_save.contains("Customer Quote");
	}

	public boolean verify_cutomer_quote_matrix_value() throws InterruptedException {
		
		Click.on(driver, customer_quote, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		ExplicitWait.visibleElement(driver, customer_quote_matrix_default_cell, 30);
		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_reantal, 30);
		String customer_quote_matrix_value=customer_quote_matrix_default_cell.getText();
		String monthly_finance_rental=customer_quote_monthly_finance_reantal.getText();
		boolean status =false;
		if(customer_quote_matrix_value.equals(monthly_finance_rental))
		{
			status =true;
		}
		return status;
	}

	
	public boolean customer_Quote_outright_BCH_for_one_payment_option_without_maintenance_calculation(String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String document_fee_from_excel,String upsell,
			String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws IOException, InterruptedException {
		obj_read_excel_calculation_page =new ReadExcelCalculation();	
		Click.on(driver, customer_quote, 50);
		obj_read_excel_calculation_page.set_global_variables_to_excel(sheet_name);
		return obj_read_excel_calculation_page.verify_customer_quote_calculations_for_one_payment_options_without_maintenance(driver, 
				customer_quote_payment_profile_dropdown, part_exchange_payment,
				actual_part_exchange_value,actual_part_exchange_value_from_excel,
				given_part_exchange_value, given_part_exchange_value_from_excel,
				less_finance_settlement, less_finance_settlement_from_excel,
				order_deposit, order_deposit_from_excel,
				document_fee, document_fee_from_excel, upsell,
				customer_quote_monthly_finance_rental, 
				maintenance_required, maintenance_margin , initial_payment, part_exchange_status, target_rental,sheet_name);
 		}
	
	public boolean check_monthly_finance_rental_with_part_exchange_toggle_on_without_maintenance(String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel , String less_finance_settlement_from_excel , 
			String order_deposit_from_excel , String document_fee_from_excel, String sheet_name) throws InterruptedException, IOException {
		
		Click.on(driver, part_exchange_payment, 70);
		LO.print("Clicked on Part Exchange panel and putting part exchange values" );
		System.out.println("Clicked on Part Exchange panel and putting part exchange values" );
	
		Click.sendKeys(driver, actual_part_exchange_value, actual_part_exchange_value_from_excel, 30);
		Click.sendKeys(driver, given_part_exchange_value, given_part_exchange_value_from_excel, 30);
		Click.sendKeys(driver, less_finance_settlement, less_finance_settlement_from_excel, 30);
		Click.sendKeys(driver, order_deposit, order_deposit_from_excel, 30);
		ExplicitWait.visibleElement(driver, document_fee, 30);
		document_fee.clear();
		Click.sendKeys(driver, document_fee, document_fee_from_excel, 30);
		Actions act = new Actions (driver);
		act.sendKeys(Keys.TAB).perform();
		
		Click.on(driver, part_exchange_toggle, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		ExplicitWait.clickableElement(driver, customer_quote_monthly_finance_rental, 30);
		String monthly_finance_rental =   customer_quote_monthly_finance_rental.getText().substring(2);
		String monthly_finance_rental_actual=RemoveComma.of(monthly_finance_rental);
		double monthly_finance_rental_actual_converted = Double.parseDouble(monthly_finance_rental_actual);
		LO.print("Monthly Finance Rental from screen (after making part exchange toggle on) is "+monthly_finance_rental_actual_converted);
	    System.out.println("Monthly Finance Rental from screen (after making part exchange toggle on) is "+monthly_finance_rental_actual_converted);
	    
		LO.print("Writing part exchange values to excel" );
		System.out.println("Writing part exchange values to excel" );
		
		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(111).getCell(3).setCellValue(Double.parseDouble(actual_part_exchange_value_from_excel));
		wb.getSheet(sheet_name).getRow(111).getCell(4).setCellValue(Double.parseDouble(given_part_exchange_value_from_excel));
		wb.getSheet(sheet_name).getRow(112).getCell(4).setCellValue(Double.parseDouble(less_finance_settlement_from_excel));		
		wb.getSheet(sheet_name).getRow(109).getCell(1).setCellValue("YES");	
		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		
		double monthlyFinanceRentalFromExcel = GetExcelFormulaValue.get_formula_value(89, 1, sheet_name);
		
		LO.print("Monthly Finance Rental from Excel (after making part exchange toggle on) is "+monthlyFinanceRentalFromExcel);
	    System.out.println("Monthly Finance Rental from Excel (after making part exchange toggle on) is "+monthlyFinanceRentalFromExcel);
	    
		
		boolean flag = false ;
		if((Difference.of_two_Double_Values(monthly_finance_rental_actual_converted, monthlyFinanceRentalFromExcel)<0.2))
				{
			    flag = true;
			     LO.print("Monthly finance rental (after makking part exchage toggle on) is found OK" );
			     System.out.println("Monthly finance rental (after makking part exchage toggle on) is found OK" );
				}
		else  {
		     LO.print("Monthly finance rental (after making part exchage toggle on) is found wrong" );
		     System.out.println("Monthly finance rental (after making part exchage toggle on) is found wrong" );
		      }
		
		Click.on(driver, part_exchange_toggle, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);


		FileInputStream in1 = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb1 = new XSSFWorkbook(in1);

		
		wb1.getSheet(sheet_name).getRow(109).getCell(1).setCellValue("NO");	
		FileOutputStream out1 = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb1.write(out1);		
		
		return flag ;
	}

	
	public boolean check_monthly_finance_rental_with_part_exchange_toggle_on_with_maintenance(String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel , String less_finance_settlement_from_excel , 
			String order_deposit_from_excel , String document_fee_from_excel, String sheet_name) throws InterruptedException, IOException {

		
		ExplicitWait.clickableElement(driver, part_exchange_payment, 50);
		Thread.sleep(4000);
		Click.on(driver, part_exchange_payment, 70);
		LO.print("Clicked on Part Exchange panel" );
		System.out.println("Clicked on Part Exchange panel" );
	
		Click.sendKeys(driver, actual_part_exchange_value, actual_part_exchange_value_from_excel, 30);
		Click.sendKeys(driver, given_part_exchange_value, given_part_exchange_value_from_excel, 30);
		Click.sendKeys(driver, less_finance_settlement, less_finance_settlement_from_excel, 30);
		Click.sendKeys(driver, order_deposit, order_deposit_from_excel, 30);
		ExplicitWait.visibleElement(driver, document_fee, 30);
		document_fee.clear();
		Click.sendKeys(driver, document_fee, document_fee_from_excel, 30);
		Actions act = new Actions (driver);
		act.sendKeys(Keys.TAB).perform();
		
		Click.on(driver, part_exchange_toggle, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);
		ExplicitWait.visibleElement(driver, customer_quote_monthly_maintenance_rental, 30);

		double monthly_finance_rental_actual_converted = Double.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().substring(2)));
		double monthly_maintenance_rental_actual_converted = Double.parseDouble(RemoveComma.of(customer_quote_monthly_maintenance_rental.getText().substring(2)));

		LO.print("Monthly Finance Rental from screen (after making part exchange toggle on) is "+monthly_finance_rental_actual_converted);
	    System.out.println("Monthly Finance Rental from screen (after making part exchange toggle on) is "+monthly_finance_rental_actual_converted);
	    
		LO.print("Monthly Mainte. Rental from screen (after making part exchange toggle on) is "+monthly_maintenance_rental_actual_converted);
	    System.out.println("Monthly Mainte. Rental from screen (after making part exchange toggle on) is "+monthly_maintenance_rental_actual_converted);
	    
		LO.print("Writing part exchange values to excel" );
		System.out.println("Writing part exchange values to excel" );
		
		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(111).getCell(3).setCellValue(Double.parseDouble(actual_part_exchange_value_from_excel));
		wb.getSheet(sheet_name).getRow(111).getCell(4).setCellValue(Double.parseDouble(given_part_exchange_value_from_excel));
		wb.getSheet(sheet_name).getRow(112).getCell(4).setCellValue(Double.parseDouble(less_finance_settlement_from_excel));		
		wb.getSheet(sheet_name).getRow(109).getCell(1).setCellValue("YES");	
		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		
		double monthlyFinanceRentalFromExcel = GetExcelFormulaValue.get_formula_value(89, 1, sheet_name);
		
		double monthlyMainteRentalFromExcel = GetExcelFormulaValue.get_formula_value(88, 1, sheet_name);
		
		LO.print("Monthly Finance Rental from Excel (after making part exchange toggle on) is "+monthlyFinanceRentalFromExcel);
	    System.out.println("Monthly Finance Rental from Excel (after making part exchange toggle on) is "+monthlyFinanceRentalFromExcel);
	    
		LO.print("Monthly Mainte. Rental from Excel (after making part exchange toggle on) is "+monthlyMainteRentalFromExcel);
	    System.out.println("Monthly Mainte. Rental from Excel (after making part exchange toggle on) is "+monthlyMainteRentalFromExcel);
	    
		
		double diff1 =Difference.of_two_Double_Values(monthly_finance_rental_actual_converted, monthlyFinanceRentalFromExcel);
		
		double diff2 =Difference.of_two_Double_Values(monthly_maintenance_rental_actual_converted, monthlyMainteRentalFromExcel);

		
		boolean flag = false ;
		if(diff1<0.2 && diff2<0.2)
				{
			    flag = true;
			     LO.print("Monthly finance and maint. rental (after makking part exchage toggle on) is found OK" );
			     System.out.println("Monthly finance and maint. rental (after makking part exchage toggle on) is found OK" );
				}
		else  {
		     LO.print("Monthly finance and maint. rental (after makking part exchage toggle on) is found wrong" );
		     System.out.println("Monthly finance and maint. rental (after makking part exchage toggle on) is found wrong" );
		      }
		
		Click.on(driver, part_exchange_toggle, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);


		FileInputStream in1 = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb1 = new XSSFWorkbook(in1);

		
		wb1.getSheet(sheet_name).getRow(109).getCell(1).setCellValue("NO");	
		FileOutputStream out1 = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb1.write(out1);		
		
		return flag ;
	}

	public boolean customer_Quote_outright_BCH_for_all_payment_option_without_maintenance_calculation(String initial_payment,String sheet_name) throws IOException, InterruptedException {
			 
		return obj_read_excel_calculation_page.
		verify_customer_quote_calculations_for_all_payment_options_without_maintenance(driver, customer_quote_payment_profile_dropdown,
				 customer_quote_monthly_finance_rental,initial_payment_input_field, initial_payment,sheet_name);
}
	
	public boolean customer_Quote_outright_BCH_for_one_payment_option_without_maintenance_calculation_edited(String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String document_fee_from_excel,String upsell,
			String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws IOException, InterruptedException {
		obj_read_excel_calculation_page =new ReadExcelCalculation();	
		Click.on(driver, customer_quote, 50);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		obj_read_excel_calculation_page.set_global_variables_to_excel(sheet_name);
		return obj_read_excel_calculation_page.verify_customer_quote_calculations_for_one_payment_options_without_maintenance_edited(driver, 
				customer_quote_payment_profile_dropdown, part_exchange_payment,
				actual_part_exchange_value,actual_part_exchange_value_from_excel,
				given_part_exchange_value, given_part_exchange_value_from_excel,
				less_finance_settlement, less_finance_settlement_from_excel,
				order_deposit, order_deposit_from_excel,
				document_fee, document_fee_from_excel, matrix_upsell_input_field, upsell,
				customer_quote_monthly_finance_rental, 
				maintenance_required, maintenance_margin , initial_payment, part_exchange_status, target_rental,sheet_name);
 		}
	
	public boolean customer_Quote_outright_BCH_for_all_payment_option_without_maintenance_calculation_edited(String initial_payment,String sheet_name) throws IOException, InterruptedException {
		 
		return obj_read_excel_calculation_page.
		verify_customer_quote_calculations_for_all_payment_options_without_maintenance_edited(driver, customer_quote_payment_profile_dropdown,
				 customer_quote_monthly_finance_rental,initial_payment_input_field, initial_payment,sheet_name);
}
	
	
	public boolean customer_Quote_outright_BCH_for_one_payment_option_with_maintenance_calculation(String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String document_fee_from_excel,String upsell,
			String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws IOException, InterruptedException {
		obj_read_excel_calculation_page =new ReadExcelCalculation();
		Thread.sleep(4000);
		Click.on(driver, customer_quote, 50);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		Click.on(driver, customer_quote_maintenance_toggle_button, 40);
		obj_read_excel_calculation_page.set_global_variables_to_excel(sheet_name);
		return obj_read_excel_calculation_page.verify_customer_quote_calculations_for_one_payment_options_with_maintenance(driver, 
				customer_quote_payment_profile_dropdown, part_exchange_payment,
				actual_part_exchange_value,actual_part_exchange_value_from_excel,
				given_part_exchange_value, given_part_exchange_value_from_excel,
				less_finance_settlement, less_finance_settlement_from_excel,
				order_deposit, order_deposit_from_excel,
				document_fee, document_fee_from_excel, upsell,
				customer_quote_monthly_finance_rental, customer_quote_monthly_maintenance_rental,
				maintenance_required, maintenance_margin , initial_payment, part_exchange_status, target_rental,sheet_name);
	}
	
	public boolean check_monthly_payments_on_adding_upsell_values_with_maintenance(String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String  add_mileage, 
			String sheet_name) throws IOException, InterruptedException {
		
		Actions act = new Actions(driver);
		
		ExplicitWait.visibleElement(driver, security_deposit_input_field, 30);
		
		ExplicitWait.visibleElement(driver, matrix_upsell_input_field, 30);

		ExplicitWait.visibleElement(driver, referrer_upsell_input_field, 30);
		
				
		security_deposit_input_field.sendKeys(Keys.chord(Keys.CONTROL, "a",Keys.DELETE));
		Click.sendKeys(driver, security_deposit_input_field, security_deposit, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		matrix_upsell_input_field.sendKeys(Keys.chord(Keys.CONTROL, "a",Keys.DELETE));
		Click.sendKeys(driver, matrix_upsell_input_field, matrix_upsell, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		referrer_upsell_input_field.sendKeys(Keys.chord(Keys.CONTROL, "a",Keys.DELETE));
		Click.sendKeys(driver, referrer_upsell_input_field , referrer_upsell, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		
		
		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);
		ExplicitWait.visibleElement(driver, customer_quote_monthly_maintenance_rental, 30);

		double monthly_finance_rental_actual_converted = Double.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().substring(2)));
		double monthly_maintenance_rental_actual_converted = Double.parseDouble(RemoveComma.of(customer_quote_monthly_maintenance_rental.getText().substring(2)));

		LO.print("Monthly Finance Rental from screen (after submitting upsell values) is "+monthly_finance_rental_actual_converted);
	    System.out.println("Monthly Finance Rental from screen (after submitting upsell values) is "+monthly_finance_rental_actual_converted);
	    
		LO.print("Monthly Mainte. Rental from screen ((after submitting upsell values) is "+monthly_maintenance_rental_actual_converted);
	    System.out.println("Monthly Mainte. Rental from screen ((after submitting upsell values) is "+monthly_maintenance_rental_actual_converted);
	    
		LO.print("Writing upsell values to excel" );
		System.out.println("Writing upsell values to excel" );
		
		obj_read_excel_calculation_page = new ReadExcelCalculation();
		
		obj_read_excel_calculation_page.put_upsell_values_to_excel(matrix_upsell , referrer_upsell ,sheet_name);
		
		double monthlyFinanceRentalFromExcel = GetExcelFormulaValue.get_formula_value(89, 1, sheet_name);
		
		double monthlyMainteRentalFromExcel = GetExcelFormulaValue.get_formula_value(88, 1, sheet_name);
		
		
		LO.print("Monthly Finance Rental from Excel (after submitting upsell values) is "+monthlyFinanceRentalFromExcel);
	    System.out.println("Monthly Finance Rental from Excel (after submitting upsell values) is "+monthlyFinanceRentalFromExcel);
	    
		LO.print("Monthly Mainte. Rental from Excel ((after submitting upsell values) is "+monthlyMainteRentalFromExcel);
	    System.out.println("Monthly Mainte. Rental from Excel ((after submitting upsell values) is "+monthlyMainteRentalFromExcel);
	    
		
		
		double diff1 =Difference.of_two_Double_Values(monthly_finance_rental_actual_converted, monthlyFinanceRentalFromExcel);
		
		double diff2 =Difference.of_two_Double_Values(monthly_maintenance_rental_actual_converted, monthlyMainteRentalFromExcel);

		
		boolean flag = false ;
		if(diff1<0.2 && diff2<0.2)
				{
			    flag = true;
			     LO.print("Monthly finance and maint. rental (after submitting upsell values) is found OK" );
			     System.out.println("Monthly finance and maint. rental (after submitting upsell values) is found OK" );
				}
		else  {
		     LO.print("Monthly finance and maint. rental (after submitting upsell values) is found wrong" );
		     System.out.println("Monthly finance and maint. rental (after submitting upsell values) is found wrong" );
		      }
	
		return flag ;
		
	}
 		

	public boolean check_monthly_payments_on_adding_upsell_values_without_maintenance(String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String  add_mileage, 
			String sheet_name) throws IOException, InterruptedException {
		
		Actions act = new Actions(driver);
		
ExplicitWait.visibleElement(driver, security_deposit_input_field, 30);
		
		ExplicitWait.visibleElement(driver, matrix_upsell_input_field, 30);

		ExplicitWait.visibleElement(driver, referrer_upsell_input_field, 30);
		
				
		security_deposit_input_field.sendKeys(Keys.chord(Keys.CONTROL, "a",Keys.DELETE));
		Click.sendKeys(driver, security_deposit_input_field, security_deposit, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		matrix_upsell_input_field.sendKeys(Keys.chord(Keys.CONTROL, "a",Keys.DELETE));
		Click.sendKeys(driver, matrix_upsell_input_field, matrix_upsell, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		referrer_upsell_input_field.sendKeys(Keys.chord(Keys.CONTROL, "a",Keys.DELETE));
		Click.sendKeys(driver, referrer_upsell_input_field , referrer_upsell, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	
		
		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

		double monthly_finance_rental_actual_converted = Double.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().substring(2)));

		LO.print("Monthly Finance Rental from screen (after submitting upsell values) is "+monthly_finance_rental_actual_converted);
	    System.out.println("Monthly Finance Rental from screen (after submitting upsell values) is "+monthly_finance_rental_actual_converted);
	    
	    LO.print("Writing upsell values to excel" );
		System.out.println("Writing upsell values to excel" );
		
		obj_read_excel_calculation_page = new ReadExcelCalculation();
		
		obj_read_excel_calculation_page.put_upsell_values_to_excel(matrix_upsell , referrer_upsell ,sheet_name);
			
		double monthlyFinanceRentalFromExcel = GetExcelFormulaValue.get_formula_value(89, 1, sheet_name);
		
		
		LO.print("Monthly Finance Rental from Excel (after submitting upsell values) is "+monthlyFinanceRentalFromExcel);
	    System.out.println("Monthly Finance Rental from Excel (after submitting upsell values) is "+monthlyFinanceRentalFromExcel);
	  
		
		double diff1 =Difference.of_two_Double_Values(monthly_finance_rental_actual_converted, monthlyFinanceRentalFromExcel);
	
		
		boolean flag = false ;
		if(diff1<0.2 )
				{
			    flag = true;
			     LO.print("Monthly finance (after submitting upsell values) is found OK" );
			     System.out.println("Monthly finance (after submitting upsell values) is found OK" );
				}
		else  {
		     LO.print("Monthly finance (after submitting upsell values) is found wrong" );
		     System.out.println("Monthly  (after submitting upsell values) is found wrong" );
		      }
	
		return flag ;
		
	}
 	

	public boolean customer_Quote_outright_BCH_for_all_payment_option_with_maintenance_calculation(String initial_payment,String sheet_name) throws IOException, InterruptedException {
		
		return obj_read_excel_calculation_page.
				verify_customer_quote_calculations_for_all_payment_options_with_maintenance(driver, customer_quote_payment_profile_dropdown,  customer_quote_monthly_finance_rental, customer_quote_monthly_maintenance_rental,initial_payment_input_field,initial_payment, sheet_name);
	}
	
	


	
		


	

	
}
