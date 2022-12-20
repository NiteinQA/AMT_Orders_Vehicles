package com.amt.CustomerQuotePackage;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.ReadExcelCalculation;

public class CustomerQuotePage_CP_PCH_Page extends TestBase {
	
	CustomerQuotePage_CP_PCH_Page obj_cust_quote_CP_PCHPage;
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

	
	

	public CustomerQuotePage_CP_PCH_Page() {
		PageFactory.initElements(driver, this);
	}

    
	public boolean customer_Quote_CP_PCH_OTR_calculation() throws InterruptedException {

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

	
	public boolean customer_Quote_CP_PCH_for_one_payment_option_without_maintenance_calculation(String actual_part_exchange_value_from_excel,
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

	public boolean customer_Quote_CP_PCH_for_all_payment_option_without_maintenance_calculation(String initial_payment,
			String sheet_name) throws IOException, InterruptedException {
			 
		return obj_read_excel_calculation_page.
		verify_customer_quote_calculations_for_all_payment_options_without_maintenance(driver, customer_quote_payment_profile_dropdown,
				 customer_quote_monthly_finance_rental, initial_payment_input_field, initial_payment, sheet_name);
	}
	
	
	public boolean customer_Quote_CP_PCH_for_one_payment_option_with_maintenance_calculation(String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String document_fee_from_excel,String upsell,
			String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws IOException, InterruptedException {
		obj_read_excel_calculation_page =new ReadExcelCalculation();	
		Click.on(driver, customer_quote, 50);
		Thread.sleep(5000);
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
 		


	public boolean customer_Quote_CP_PCH_for_all_payment_option_with_maintenance_calculation(String initial_payment,String sheet_name) throws IOException, InterruptedException {
		
		return obj_read_excel_calculation_page.
				verify_customer_quote_calculations_for_all_payment_options_with_maintenance(driver, customer_quote_payment_profile_dropdown, customer_quote_monthly_finance_rental, customer_quote_monthly_maintenance_rental, initial_payment_input_field, initial_payment, sheet_name);
	}                                                                                        

	
	
}
