package com.amt.CustomerQuotePackage;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Difference;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.ReadExcelCalculation;
import com.amt.testUtil.ReadExcelCalculationForPurchaseAgreement;
import com.amt.testUtil.RemoveComma;
import com.paulhammant.ngwebdriver.ByAngularBinding;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class CustomerQuotePageOutrightHPNRPage extends TestBase {
	
	CustomerQuotePageOutrightHPNRPage obj_cust_quote_outright_bchPage;
	ReadExcelCalculationForPurchaseAgreement obj_read_excel_calculation_page; 
	
	 Clipboard clipboard;
	
	JavascriptExecutor jse;
    NgWebDriver ngDriver;
    
	@FindBy(xpath = "//p[normalize-space()='Customer Quote']")
	private WebElement customer_quote;
	
	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-all-customer-quotes[1]/div[1]/app-aquisition-hire-agreement[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[6]/div[4]")
	private WebElement customer_quote_matrix_default_cell;
    
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/app-purchase-customer-quote-summary-header/div/div[4]/div/p/strong")
	private WebElement customer_quote_monthly_finance_rental;
	
	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/app-aquisition-header[1]/div[1]/div[2]/div[3]/button[1]")
	private WebElement save_button;
    
	@FindBy(xpath = "//select[@name='acquisitionPaymentProfileId']")
	private WebElement customer_quote_payment_profile_dropdown;
	
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
	
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[5]/div/p/strong")
	private WebElement customer_quote_monthly_maintenance_rental;
	
	@FindBy(xpath = "//input[@name='monetaryAmount']")
	private WebElement initial_payment_input_field;
	
	@FindBy(xpath = "//*[@id='upsell']")
	private WebElement matrix_upsell_input_field;
	
	
	@FindBy(xpath = "//*[@id='headingThree']/button")
	private WebElement part_exchange_and_additional_payment;
	
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/app-purchase-customer-quote-summary-header/div/div[2]/div/p/strong")
	private WebElement terms;
	
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/app-purchase-customer-quote-summary-header/div/div[3]/div/p/strong")
	private WebElement miles_per_annum;
	
	   
	@FindBy(xpath = "//*[@id='bdiscount']")
	private WebElement vehicle_discount;
	
	@FindBy(xpath = "//*[@id='pdiscountper']")
	private WebElement paint_discount;
	
	@FindBy(xpath = "//*[@id='odiscount']")
	private WebElement options_discount;
	
	@FindBy(xpath = "//*[@id='bdiscountvalue']")
	private WebElement vehicle_additional_discount;
	
	@FindBy(xpath = "//*[@id='pdiscountvalue']")
	private WebElement paint_additional_discount;
	
	@FindBy(xpath = "//*[@id='odiscountvalue']")
	private WebElement options_additional_discount;
	
	@FindBy(xpath = "//input[@id='profit']")
	private WebElement vehicle_profit_input;
	
	


	
	
	
	

	public CustomerQuotePageOutrightHPNRPage() {
		PageFactory.initElements(driver, this);
	
		jse =(JavascriptExecutor)driver;
		
		ngDriver = new NgWebDriver(jse);	
	
	
	}

	
	public boolean check_monthly_finance_payment_on_customer_quote(WebDriver driver,String maintenance_status,String matrix_credit_type, String balloon_payment_status, String order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException
	{
		
		Thread.sleep(2000);	
		
		Click.on(driver, customer_quote, 30);
		
		LO.print("Clicked on Customer Quote Page" );
		System.out.println("Clicked on Customer Quote Page" );
		
		Thread.sleep(8000);
		 
        Actions act = new Actions(driver);
        
        act.sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB
        		,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.ENTER).build().perform();
        
        Thread.sleep(3000);
        try {
		List <WebElement> list =driver.findElements(By.xpath("//*[@class='ng-dropdown-panel ng-select-bottom']/div/div/div"));
         
			
		for(WebElement e: list)
		{

			if(e.getText().equalsIgnoreCase(matrix_credit_type))
			{
				Click.on(driver, e, 20);
				 
				Thread.sleep(3000);
				break;
			}
		}
        }
        catch(Exception e){
        	e.printStackTrace();       	
        }
        
        LO.print("Matrix credit type "+matrix_credit_type+" has been selected" );
		System.out.println("Matrix credit type "+matrix_credit_type+" has been selected" );
         
        obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
        
        obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement(matrix_credit_type ,sheet_name);
        
        ExplicitWait.visibleElement(driver, vehicle_discount,30);
        
       
        clipboard =Toolkit.getDefaultToolkit().getSystemClipboard();
        
        
        vehicle_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
        
        String vehicle_discount_copied =(String) clipboard.getData(DataFlavor.stringFlavor);
               
        paint_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
        
        String paint_discount_copied =(String) clipboard.getData(DataFlavor.stringFlavor);
        
        options_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
        
        String options_discount_copied =(String) clipboard.getData(DataFlavor.stringFlavor);
        
        vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
        
        String  vehicle_additional_copied =(String) clipboard.getData(DataFlavor.stringFlavor);  
        
        paint_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
        
        String  paint_additional_copied =(String) clipboard.getData(DataFlavor.stringFlavor);
       
        options_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
        
        String  options_additional_copied =(String) clipboard.getData(DataFlavor.stringFlavor);
        
       ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);
       
       double monthly_finance_payment_actual_from_screen=Double.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));
       
       LO.print(" Actual Monthly Maintenance Rental from screen is "+monthly_finance_payment_actual_from_screen);
       System.out.println("Actual Monthly Maintenance Rental from screen is "+monthly_finance_payment_actual_from_screen);
       
        double monthly_finance_payment_expected_from_excel  =obj_read_excel_calculation_page.get_monthly_finanace_payment_from_excel(maintenance_status,
        		matrix_credit_type, balloon_payment_status,order_deposit,finance_deposit, document_fee,vehicle_discount_copied, 
        		paint_discount_copied,options_discount_copied,vehicle_additional_copied,paint_additional_copied,options_additional_copied,sheet_name);
       
        LO.print("Expected Monthly Maintenance Rental from excel is "+monthly_finance_payment_expected_from_excel);
        System.out.println("Expected Monthly Maintenance Rental from excel is "+monthly_finance_payment_expected_from_excel);
        
               
        double diff= Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen, monthly_finance_payment_expected_from_excel) ;    
       boolean status = false; 
       if (diff<0.2)
       {
    	   status = true; 
       }
       return status;
	}
	
	public boolean edit_vehicle_profit_and_check_monthly_finance_payment(String vehicle_profit, String sheet_name) throws InterruptedException, UnsupportedFlavorException, IOException
	{
		
		ExplicitWait.visibleElement(driver, vehicle_profit_input, 30);
		vehicle_profit_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeys(driver, vehicle_profit_input, vehicle_profit, 30);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		Thread.sleep(2000);
		clipboard =Toolkit.getDefaultToolkit().getSystemClipboard();
		ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));        
        String vehicle_additional_discount_copied =(String) clipboard.getData(DataFlavor.stringFlavor);
        obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
        double monthly_finance_payment_expected_from_excel= obj_read_excel_calculation_page.get_monthly_finance_payment_after_editing_vehicle_profit(vehicle_additional_discount_copied, sheet_name);
        double monthly_finance_payment_actual_from_screen=Double.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));
		double diff =Difference.of_two_Double_Values(monthly_finance_payment_expected_from_excel, monthly_finance_payment_actual_from_screen);
		boolean status =false;
		if(diff<0.2)
		{
			status=true;
		}
		return status;
	}
    

	


	
		


	

	
}
