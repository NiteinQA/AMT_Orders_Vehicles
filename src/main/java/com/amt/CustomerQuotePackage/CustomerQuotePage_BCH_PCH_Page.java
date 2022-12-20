package com.amt.CustomerQuotePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
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

public class CustomerQuotePage_BCH_PCH_Page extends TestBase {
	
	CustomerQuotePage_BCH_PCH_Page obj_cust_quote_BCH_PCHPage;
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
	
	@FindBy(xpath = "//*[@id='upsell']")
	private WebElement matrix_upsell_input_field;
	
	@FindBy(xpath = "//label[@class='switch mr-2']//span[@class='slider round']")
	private WebElement part_exchange_toggle;
	
	@FindBy(xpath = "//*[@id='partExchange_2']/div/div/div[1]/div/div[1]/ul/li[3]/span[2]")
	private WebElement part_exchange_allowance;
	
	
	@FindBy(xpath = "//span[@class='balduevalue']")
	private WebElement balance_due_value;
	
	
	
	
	

	public CustomerQuotePage_BCH_PCH_Page() {
		PageFactory.initElements(driver, this);
	}

    
	public boolean customer_Quote_BCH_PCH_OTR_calculation() throws InterruptedException {

		Click.on(driver, customer_quote, 50);
		ExplicitWait.clickableElement(driver, save_button, 0);
		Click.on(driver, save_button, 60);
		
		String page_title_after_save=driver.getTitle();
		System.out.println(page_title_after_save);
		return page_title_after_save.contains("Customer Quote");
	}

	public boolean verify_cutomer_quote_matrix_value() {
		
		Click.on(driver, customer_quote, 30);
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

	
	public boolean customer_Quote_BCH_PCH_for_one_payment_option_without_maintenance_calculation(
			String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String upsell, String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws IOException, InterruptedException {
		obj_read_excel_calculation_page =new ReadExcelCalculation();	
		Click.on(driver, customer_quote, 50);
		obj_read_excel_calculation_page.set_global_variables_to_excel_for_bch_pch_scenario(sheet_name);
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
	
	public boolean customer_quote_part_balance_due_value_verification(String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String document_fee_from_excel,String upsell,String part_exchange_status, String target_rental, String sheet_name) throws InterruptedException, IOException
	{
		
   		LO.print("Started verifying Balance Due value");
   		System.out.println("Started verifying Balance Due value");   		
   		
		ExplicitWait.visibleElement(driver, part_exchange_allowance,30);
		
		ExplicitWait.visibleElement(driver, balance_due_value,30);
		
		Thread.sleep(5000);
		
        double partExchangeAllowanceFromScreen = Double.parseDouble(RemoveComma.of(part_exchange_allowance.getText().trim().substring(2)));
        
        Thread.sleep(5000);
		
		double balanceDueFromScreen = Double.parseDouble(RemoveComma.of(balance_due_value.getText().trim().substring(2)));

        double orderDepositeFromExcel = Double.parseDouble(order_deposit_from_excel);
        
        double documentFeeFromExcel = Double.parseDouble(document_fee_from_excel);
        
        double balance_due_expected = ((orderDepositeFromExcel+documentFeeFromExcel) - partExchangeAllowanceFromScreen);
        
        System.out.println("partExchangeAllowanceFromScreen "+partExchangeAllowanceFromScreen);
        System.out.println("balanceDueFromScreen "+balanceDueFromScreen);
        System.out.println("orderDepositeFromExcel "+orderDepositeFromExcel);
        System.out.println("documentFeeFromExcel "+documentFeeFromExcel);
        System.out.println("partExchangeAllowanceFromScreen "+partExchangeAllowanceFromScreen);
        System.out.println("balance due expected "+balance_due_expected);
        
        double diff =   Difference.of_two_Double_Values(balanceDueFromScreen, balance_due_expected);
        
        System.out.println("Difference between expected and actual ="+diff);
        
        boolean balance_value_when_part_exchange_toggle_off = false;
       if(diff<0.3) 
       {
    	   balance_value_when_part_exchange_toggle_off = true ;    	   
   		LO.print("Balance due value (when part exchange toggle is OFF) is verified and found ok");
   		System.out.println("Balance due value (when part exchange toggle is off) is verified and found ok");
       }else{
    	    
   		LO.print("Balance due value (when part exchange toggle is OFF) found wrong");
   		System.out.println("Balance due value (when part exchange toggle is off) found wrong");
       }
        
		Click.on(driver, part_exchange_toggle, 30);
		
		Thread.sleep(5000);	
		
		double partExchangeAllowanceFromScreen1 = Double.parseDouble(RemoveComma.of(part_exchange_allowance.getText().trim().substring(2)));
		
		Thread.sleep(5000);	
		double balanceDueFromScreen1 = Double.parseDouble(RemoveComma.of(balance_due_value.getText().trim().substring(2)));

          double balance_due_expected1 = orderDepositeFromExcel+documentFeeFromExcel;
          
          System.out.println("partExchangeAllowanceFromScreen "+partExchangeAllowanceFromScreen1);
          System.out.println("balanceDueFromScreen1 "+balanceDueFromScreen1);
          System.out.println("balance due expected1 "+balance_due_expected1);
          
        
        double diff1 =   Difference.of_two_Double_Values(balanceDueFromScreen, balance_due_expected);
        
        System.out.println("Difference between expected and actual ="+diff1);
		       
        boolean balance_value_when_part_exchange_toggle_off1 = false;
       if(diff1<0.3) 
       {
    	   balance_value_when_part_exchange_toggle_off1 = true ;
    	   LO.print("Balance due value (when part exchange toggle is ON) is verified and found ok");
      		System.out.println("Balance due value (when part exchange toggle is ON) is verified and found ok");
    	   
       } else{   	    
      		LO.print("Balance due value (when part exchange toggle is ON) found wrong");
      		System.out.println("Balance due value (when part exchange toggle is ON) found wrong");
          }
       boolean status = false ;
       if(balance_value_when_part_exchange_toggle_off && balance_value_when_part_exchange_toggle_off1)
       {
    	   status =true;
       }
       
       return status;
       
       
	}	
	
	
	
	
	public boolean customer_quote_monthly_finance_rental_value_verification_when_part_exchange_toggle_on(String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String document_fee_from_excel,String upsell,String part_exchange_status, String target_rental, String sheet_name) throws InterruptedException, IOException
	{
		
		
		
		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(109).getCell(1).setCellValue(part_exchange_status);
		wb.getSheet(sheet_name).getRow(111).getCell(3).setCellValue(Double.parseDouble(actual_part_exchange_value_from_excel));
		wb.getSheet(sheet_name).getRow(111).getCell(4).setCellValue(Double.parseDouble(given_part_exchange_value_from_excel));
		wb.getSheet(sheet_name).getRow(112).getCell(4).setCellValue(Double.parseDouble(less_finance_settlement_from_excel));
		wb.getSheet(sheet_name).getRow(123).getCell(1).setCellValue(Double.parseDouble(target_rental));
	    
		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
	 	wb.write(out);
	 	
	 	double monthly_finance_rental_expected_with_part_exchange=GetExcelFormulaValue.get_formula_value(89, 1, sheet_name);
	 	
	 	ExplicitWait.clickableElement(driver, customer_quote_monthly_finance_rental, 30);
	 	
	 	Thread.sleep(8000);
	 		 	
	 	double monthlyFinanceRentalFromScreen = Double.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));
	 	
	 	double diff = Difference.of_two_Double_Values(monthly_finance_rental_expected_with_part_exchange, monthlyFinanceRentalFromScreen);
	 	
	 	System.out.println("monthly_finance_rental_expected_with_part_exchange "+monthly_finance_rental_expected_with_part_exchange );
	 	
	 	System.out.println("monthlyFinanceRentalFromScreen "+monthlyFinanceRentalFromScreen );

	 	 	
	 	boolean monthlyFinanceRentalExpected =false ;
	 	
	 	if(diff<0.3)
	 	{
	 		monthlyFinanceRentalExpected =true ;
	 	}
	 	return monthlyFinanceRentalExpected;
	}
	
	
	
	
	
	

	public boolean customer_Quote_BCH_PCH_for_all_payment_option_without_maintenance_calculation(String initial_payment,String sheet_name) throws IOException, InterruptedException {
			 
		return obj_read_excel_calculation_page.
		verify_customer_quote_calculations_for_all_payment_options_without_maintenance(driver, customer_quote_payment_profile_dropdown,
				 customer_quote_monthly_finance_rental,initial_payment_input_field, initial_payment,sheet_name);
}
	
	public boolean customer_Quote_BCH_PCH_for_one_payment_option_without_maintenance_calculation_edited(String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String document_fee_from_excel,String upsell,
			String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws IOException {
		obj_read_excel_calculation_page =new ReadExcelCalculation();	
		Click.on(driver, customer_quote, 50);
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
	
	
	
	public boolean customer_Quote_BCH_PCH_for_all_payment_option_without_maintenance_calculation_edited(String initial_payment,String sheet_name) throws IOException, InterruptedException {
		 
		return obj_read_excel_calculation_page.
		verify_customer_quote_calculations_for_all_payment_options_without_maintenance_edited(driver, customer_quote_payment_profile_dropdown,
				 customer_quote_monthly_finance_rental,initial_payment_input_field, initial_payment,sheet_name);
}
	
	
	public boolean customer_Quote_BCH_PCH_for_one_payment_option_with_maintenance_calculation(String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String document_fee_from_excel,String upsell,
			String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws IOException, InterruptedException {
		obj_read_excel_calculation_page =new ReadExcelCalculation();
		Thread.sleep(4000);
		Click.on(driver, customer_quote, 50);
		Thread.sleep(4000);
		//Click.on(driver, customer_quote_maintenance_toggle_button, 40);
		obj_read_excel_calculation_page.set_global_variables_to_excel_for_bch_pch_scenario(sheet_name);
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
 		


	public boolean customer_Quote_BCH_PCH_for_all_payment_option_with_maintenance_calculation(String initial_payment,String sheet_name) throws IOException, InterruptedException {
		
		return obj_read_excel_calculation_page.
				verify_customer_quote_calculations_for_all_payment_options_with_maintenance(driver, customer_quote_payment_profile_dropdown,  customer_quote_monthly_finance_rental, customer_quote_monthly_maintenance_rental,initial_payment_input_field,initial_payment, sheet_name);
	}


	
	


	
		


	

	
}
