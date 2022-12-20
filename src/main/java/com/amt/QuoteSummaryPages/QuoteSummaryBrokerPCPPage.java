package com.amt.QuoteSummaryPages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.ReadExcelCalculation;
import com.amt.testUtil.RemoveComma;

public class QuoteSummaryBrokerPCPPage extends TestBase {
	
	
	
	ReadExcelCalculation obj_read_excel_calculation_page; 
	
	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//p[normalize-space()='Quote summary']")
	private WebElement quote_summary;
	
//	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-summary-quote[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/app-vehicle-details[1]/div[1]/div[1]/div[2]/div[1]/div[2]/app-vehicle-summery[1]/div[1]/div[1]/div[2]/div[2]/div[3]/span[2]")
//	private WebElement quote_summary_ref_no;
	
	@FindBy(xpath = "//*[@class='right-fix vechile-summery']/div/div[2]/div[2]/div[4]/span[2]")
	private WebElement quote_summary_ref_no;
	
	@FindBy(xpath = "//*[@class='row']//*[@id='headingTwo']/div/div/div[4]/div/p/strong")
	private WebElement quote_summary_cost_otr_price;
	
	
	
	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-summary-quote[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[8]/div[1]/div[1]/p[1]/strong[1]")
	private WebElement quote_summary_total_monthly_holding_cost;
	
//	@FindBy(xpath = "//*[@class='row']//*[@id='headingHoldingCost']/div/div[8]/div/div/p/strong")
//	private WebElement quote_summary_total_monthly_holding_cost;
	
//	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-summary-quote[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/app-acquisition-quote-summary[1]/div[1]/div[2]/app-hire-customer-quote-summary-header[1]/div[1]/div[4]/div[1]/p[1]/strong[1]")
//	private WebElement quote_summary_monthly_finance_rental;
	
	@FindBy(xpath = "//*[@id=\"headingBchSummary\"]/div/app-broker-bch-customer-quote-summary-header/div/div[4]/div/p/strong")
	private WebElement quote_summary_monthly_finance_rental;
	
//  @FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-summary-quote[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/app-acquisition-quote-summary[1]/div[1]/div[2]/app-hire-customer-quote-summary-header[1]/div[1]/div[5]/div[1]/p[1]/strong[1]")
//	private WebElement quote_summary_monthly_maintenance_rental;
	
	@FindBy(xpath = "//*[@class='row']//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[5]/div/p/strong")
	private WebElement quote_summary_monthly_maintenance_rental;
	
//	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-summary-quote[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/app-acquisition-quote-summary[1]/div[1]/div[2]/app-hire-customer-quote-summary-header[1]/div[1]/div[6]/div[1]/p[1]/strong[1]")
//	private WebElement quote_summary_monthly_total_rental;
	
	@FindBy(xpath = "//*[@class='row']//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[6]/div/p/strong")
	private WebElement quote_summary_monthly_total_rental;
	
	@FindBy(xpath = "//*[@class='row']//*[@id='headingHoldingCost']/div/div[1]/div/div/p/strong")
	private WebElement quote_summary_acq_contract_type;
	
	@FindBy(xpath = "//strong[normalize-space()='Personal Contract Purchase']")
	private WebElement quote_summary_customer_contract_type;
	
	@FindBy(xpath = "//div[@id='headingHoldingCost']//div[7]//div[1]//div[1]//p[1]//strong[1]")
	private WebElement quote_summary_total_monthly_holding_cost_without_maintenance;
	
	@FindBy(xpath = "//app-broker-pcp-customer-quote-summary-header/div/div[4]/div/p/strong")
	private WebElement quote_summary_monthly_finance_payment;
	
	@FindBy(xpath = "//app-broker-pcp-customer-quote-summary-header/div/div[5]/div/p/strong")
	private WebElement quote_summary_monthly_maintenance_payment;
	 
	@FindBy(xpath = "//app-broker-pcp-customer-quote-summary-header/div/div[6]/div/p/strong")
	private WebElement quote_summary_total_monthly_payment;
	 

	
	
	public QuoteSummaryBrokerPCPPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean quote_summary_broker_PCP_without_maintenance(String sheet_name) throws InterruptedException, IOException {
		
		LO.print("*************Calculations for Quote Summary page gas been started************");
		System.out.println("*************Calculations for Quote Summary page gas been started************");
		
		obj_read_excel_calculation_page =new ReadExcelCalculation();
		
		Click.on(driver, quote_summary, 90);		
	
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.ENTER).build().perform();
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);
		
	    ExplicitWait.visibleElement(driver, quote_summary_ref_no, 30);
		ExplicitWait.visibleElement(driver, quote_summary_cost_otr_price, 60);
		ExplicitWait.visibleElement(driver, quote_summary_customer_contract_type, 60);
		
		LO.print("Reading values from sceen -Quote Summary Page");
		System.out.println("Reading values from sceen -Quote Summary Page");
		
	    String quote_ref_no = quote_summary_ref_no.getText();
		String temp_quote_summary_cost_otr_price=quote_summary_cost_otr_price.getText().trim().substring(2);
		String customer_contract_type=quote_summary_customer_contract_type.getText();
		
		
		LO.print("Getting values from screen");
		System.out.println("Getting values from screen");
		
		LO.print("Quote_summary_cost_otr_price ="+temp_quote_summary_cost_otr_price);
		System.out.println("Quote_summary_cost_otr_price ="+temp_quote_summary_cost_otr_price);
			
		LO.print("Customer contract_type ="+customer_contract_type);
		System.out.println("Customer contract_type ="+customer_contract_type);	
		
//		LO.print("Customer Quote generated successfully and Quote_ref_no ="+quote_ref_no);
//		System.out.println("Customer Quote generated successfully and Quote_ref_no ="+quote_ref_no);
		
		String quote_summary_cost_otr_price_from_screen=RemoveComma.of(temp_quote_summary_cost_otr_price);
			
		double quote_summary_cost_otr_price_from_screen_converted =Double.parseDouble(quote_summary_cost_otr_price_from_screen);

		
		return obj_read_excel_calculation_page.verify_quote_summary_values_for_broker_pcp_cp_from_excel_without_maintenance(quote_summary_cost_otr_price_from_screen_converted, 
		 sheet_name);	

	}

	public boolean quote_summary_broker_PCP_with_maintenance(String sheet_name) throws InterruptedException, IOException {
		
		LO.print("*************Calculations for Quote Summary page gas been started************");
		System.out.println("*************Calculations for Quote Summary page gas been started************");
		
		obj_read_excel_calculation_page =new ReadExcelCalculation();
		Click.on(driver, quote_summary, 60);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);
		
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.ENTER).build().perform();		
	
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);
		
		
		ExplicitWait.visibleElement(driver, quote_summary_ref_no, 120);
		ExplicitWait.visibleElement(driver, quote_summary_cost_otr_price, 120);
		ExplicitWait.visibleElement(driver, quote_summary_customer_contract_type, 120);
		
		LO.print("Reading values from sceen -Quote Summary Page");
		System.out.println("Reading values from sceen -Quote Summary Page");
		
	    String quote_ref_no = quote_summary_ref_no.getText();
		String temp_quote_summary_cost_otr_price=quote_summary_cost_otr_price.getText().trim().substring(2);
		String customer_contract_type=quote_summary_customer_contract_type.getText();
		
		
		LO.print("Getting values from screen");
		System.out.println("Getting values from screen");
		
		LO.print("Quote_summary_cost_otr_price ="+temp_quote_summary_cost_otr_price);
		System.out.println("Quote_summary_cost_otr_price ="+temp_quote_summary_cost_otr_price);
		
		LO.print("Customer contract_type ="+customer_contract_type);
		System.out.println("Customer contract_type ="+customer_contract_type);	
		
		LO.print("Customer Quote generated successfully and Quote_ref_no ="+quote_ref_no);
		System.out.println("Customer Quote generated successfully and Quote_ref_no ="+quote_ref_no);
		
		
		String quote_summary_cost_otr_price_from_screen=RemoveComma.of(temp_quote_summary_cost_otr_price);
		
		
		double quote_summary_cost_otr_price_from_screen_converted =Double.parseDouble(quote_summary_cost_otr_price_from_screen);

		
		return obj_read_excel_calculation_page.verify_quote_summary_values_for_broker_pcp_cp_from_excel_with_maintenance(quote_summary_cost_otr_price_from_screen_converted, 
                                                 sheet_name);	

	}	
	
public boolean quote_summary_broker_PCP_check_maintenance_values_displayed(String monthlyFinancePayment, String monthlyMaintenancePayment,String sheet_name) throws InterruptedException, IOException {
		
		 
	    Thread.sleep(5000);	 		
	    ExplicitWait.visibleElement(driver, quote_summary_monthly_maintenance_payment, 120);
	    ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_payment, 120);
	    ExplicitWait.visibleElement(driver, quote_summary_total_monthly_payment, 120);		
		
		
		LO.print("Reading values from sceen -Quote Summary Page");
		System.out.println("Reading values from sceen -Quote Summary Page");
		
	   
		String temp_quote_summary_monthly_finance_payment=quote_summary_monthly_finance_payment.getText().trim().substring(2);
		String temp_quote_summary_monthly_maintenanace_payment =quote_summary_monthly_maintenance_payment.getText().trim().substring(2);
		String temp_quote_summary_total_monthly_payment=quote_summary_total_monthly_payment.getText().trim().substring(2);

		
		
		LO.print("Getting values from screen");
		System.out.println("Getting values from screen");
		
	   double monthly_finance_payment_from_screen =Double.parseDouble(RemoveComma.of(temp_quote_summary_monthly_finance_payment));
	   double monthly_maintenance_payment_from_screen =Double.parseDouble(RemoveComma.of(temp_quote_summary_monthly_maintenanace_payment));
	   double total_monthly_payment_from_screen =Double.parseDouble(RemoveComma.of(temp_quote_summary_total_monthly_payment));
	
	   double monthlyFinancePaymantFromTestData=Double.parseDouble(monthlyFinancePayment);
	   double monthlyMaintenancePaymantFromTestData=Double.parseDouble(monthlyMaintenancePayment);
	   
	   boolean status=false;
	   if(((monthly_finance_payment_from_screen-monthlyFinancePaymantFromTestData)==0)&&((monthly_finance_payment_from_screen+monthly_maintenance_payment_from_screen)==total_monthly_payment_from_screen))
	   {
		   status=true; LO.print(" Verified --Maintenance values from sceen -Quote Summary Page");
			            System.out.println("Verified --Maintenance values from sceen -Quote Summary Page");
	   } 
	   else
	   {
		   LO.print(" xxx -- Please check Maintenance values from sceen -Quote Summary Page");
           System.out.println("xxx --Please check Maintenance values from sceen -Quote Summary Page");
	   }	     
	   return status;			
	}

}
