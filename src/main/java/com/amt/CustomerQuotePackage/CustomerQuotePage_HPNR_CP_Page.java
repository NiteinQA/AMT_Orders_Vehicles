package com.amt.CustomerQuotePackage;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class CustomerQuotePage_HPNR_CP_Page extends TestBase {
	
	CustomerQuotePage_HPNR_CP_Page obj_cust_quote_outright_bchPage;
	ReadExcelCalculationForPurchaseAgreement obj_read_excel_calculation_page; 
	
	 Clipboard clipboard;
	
	JavascriptExecutor jse;
    NgWebDriver ngDriver;
    
	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;
	
    
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

	
	@FindBy(xpath = "//*[@id='otrPartExchange']")
	private WebElement actual_part_exchange_value;
	
	@FindBy(xpath = "//*[@id='partExchnage']")
	private WebElement given_part_exchange_value;
	
	@FindBy(xpath = "//*[@id='lessFinanceSettlement']")
	private WebElement less_finance_Settlement;
	
		
	@FindBy(xpath = "//div[@class='partex-value']//input[@name='orderDeposit']")
	private WebElement order_Deposit;
	
	@FindBy(xpath = "//div[@class='partex-value']//input[@name='financeDeposit']")
	private WebElement finance_Deposit;
	
	
	@FindBy(xpath = "//div[@class='partex-col docfee-center']//input[@name='DocumentFee']")
	private WebElement document_fee;
	
	
	@FindBy(xpath = "//div[@class='bal-finance']/span")
	private WebElement balance_to_finance_value;
	
	@FindBy(xpath = "//*[@id='collapseFirst']/div/div/div[1]/label")
	private WebElement customer_quote_maintenance_toggle_button;
	
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[5]/div/p/strong")
	private WebElement customer_quote_monthly_maintenance_rental;
	
	@FindBy(xpath = "//input[@name='monetaryAmount']")
	private WebElement initial_payment_input_field;
	
	@FindBy(xpath = "//*[@id='upsell']")
	private WebElement matrix_upsell_input_field;
		
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
	
	@FindBy(xpath = "//*[contains(text(),' Part exchange & additional payments ')]")
	private WebElement part_exchange_and_additional_payment_button;
	
	
	@FindBy(xpath = "//*[@id='collapseFirst']/div/div/div[5]/label/span")
	private WebElement balloon_payment_toggle;
	
	@FindBy(xpath = "//app-purchase-customer-quote-summary-header/div/div[6]/div/p/strong")
	private WebElement total_monthly_payment;
	
	
	

	public CustomerQuotePage_HPNR_CP_Page() {
		PageFactory.initElements(driver, this);
	
		}

	
	public boolean check_monthly_finance_payment_on_customer_quote(WebDriver driver,String maintenance_status,String matrix_credit_type, String balloon_payment_status, String order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException
	{
		
		Thread.sleep(2000);	
		
		Click.on(driver, customer_quote, 30);
		
		LO.print("***********Entered in Customer Quote page ***********");
		System.out.println("***********Entered in Customer Quote page ***********");
		
		Thread.sleep(20000);
		 
       Actions act = new Actions(driver);
        
       act.sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB
       		,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.ENTER).build().perform();
        
		
//		WebElement creditDropdown = driver.findElement(By.xpath("//*[@id='collapseFirst']/div/div/div[2]/div/div/div/ng-select/div/div/div[1]"));
//		new WebDriverWait(driver , Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(creditDropdown));
//		jse.executeScript("arguments[0].click();", creditDropdown);
		
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
       
       LO.print("Actual Monthly Finance Payment from screen is "+monthly_finance_payment_actual_from_screen);
       System.out.println("Actual Monthly Finance Payment from screen is "+monthly_finance_payment_actual_from_screen);
       
        double monthly_finance_payment_expected_from_excel  =obj_read_excel_calculation_page.get_monthly_finanace_payment_from_excel(maintenance_status,
        		matrix_credit_type, balloon_payment_status,order_deposit,finance_deposit, document_fee,vehicle_discount_copied, 
        		paint_discount_copied,options_discount_copied,vehicle_additional_copied,paint_additional_copied,options_additional_copied,sheet_name);
       
        LO.print("Expected Monthly Finannce Rental from excel is "+monthly_finance_payment_expected_from_excel);
        System.out.println("Expected Monthly Finannce Rental from excel is "+monthly_finance_payment_expected_from_excel);
        
               
        double diff= Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen, monthly_finance_payment_expected_from_excel) ;    
       boolean status = false; 
       if (diff<0.2)
       {
    	   status = true; 
       }
       return status;
	}
	
	public boolean check_monthly_payment_on_customer_quote_with_maintenance(WebDriver driver,String maintenance_status, String matrix_credit_type, String balloon_payment_status, String order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException
	{
		
		Thread.sleep(2000);	
		
		Click.on(driver, customer_quote, 30);
		
		LO.print("***********Entered in Customer Quote page ***********");
		System.out.println("***********Entered in Customer Quote page ***********");
		
		Thread.sleep(20000);
		 
        Actions act = new Actions(driver);
        
        act.sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB
        		,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.ENTER).build().perform();
        
//		WebElement creditDropdown = driver.findElement(By.xpath("//*[@id='collapseFirst']/div/div/div[2]/div/div/div/ng-select/div/div/div[1]"));
//		new WebDriverWait(driver , Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(creditDropdown));
//		jse = (JavascriptExecutor)driver;
//		jse.executeScript("arguments[0].click();", creditDropdown);
		
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
		
		Thread.sleep(12000);
		
	         
	    Click.on(driver, customer_quote_maintenance_toggle_button, 40); 
	    
	    
		Thread.sleep(10000);
         
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
        
       ExplicitWait.visibleElement(driver, total_monthly_payment, 30);
       
       double monthly_total_payment_actual_from_screen=Double.parseDouble(RemoveComma.of(total_monthly_payment.getText().trim().substring(2)));
       
       LO.print("Actual Monthly total Payment from screen is "+monthly_total_payment_actual_from_screen);
       System.out.println("Actual Monthly total Payment from screen is "+monthly_total_payment_actual_from_screen);
       
        double monthly_total_payment_expected_from_excel  =obj_read_excel_calculation_page.get_monthly_total_payment_from_excel(maintenance_status,
        		matrix_credit_type, balloon_payment_status,order_deposit,finance_deposit, document_fee,vehicle_discount_copied, 
        		paint_discount_copied,options_discount_copied,vehicle_additional_copied,paint_additional_copied,options_additional_copied,sheet_name);
       
        LO.print("Expected Monthly Total Rental from excel is "+monthly_total_payment_expected_from_excel);
        System.out.println("Expected Monthly Total Rental from excel is "+monthly_total_payment_expected_from_excel);
        
               
        double diff= Difference.of_two_Double_Values(monthly_total_payment_actual_from_screen, monthly_total_payment_expected_from_excel) ;    
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
		Thread.sleep(12000);		
		ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		clipboard =Toolkit.getDefaultToolkit().getSystemClipboard();
        String vehicle_additional_discount_copied =(String) clipboard.getData(DataFlavor.stringFlavor);
        obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
        double monthly_finance_payment_expected_from_excel= obj_read_excel_calculation_page.get_monthly_finance_payment_after_editing_vehicle_profit(vehicle_additional_discount_copied, sheet_name);
        double monthly_finance_payment_actual_from_screen=Double.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));
		double diff =Difference.of_two_Double_Values(monthly_finance_payment_expected_from_excel, monthly_finance_payment_actual_from_screen);
		boolean status =false;
		if(diff<0.2)
		{
			status=true;
			
			LO.print("Monthly Finance Payment verified after editing vehicle profit");
			System.out.println("Monthly Finance Payment verified after editing vehicle profit");			
		}
		
		
		return status;
	}
	
	
	public boolean edit_vehicle_profit_and_check_monthly_total_payment_with_maintenance(String vehicle_profit, String sheet_name) throws InterruptedException, UnsupportedFlavorException, IOException
	{
		
		ExplicitWait.visibleElement(driver, vehicle_profit_input, 30);
		vehicle_profit_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeys(driver, vehicle_profit_input, vehicle_profit, 30);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		Thread.sleep(12000);		
		ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		clipboard =Toolkit.getDefaultToolkit().getSystemClipboard();
        String vehicle_additional_discount_copied =(String) clipboard.getData(DataFlavor.stringFlavor);
        obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
        double monthly_total_payment_expected_from_excel= obj_read_excel_calculation_page.get_monthly_total_payment_after_editing_vehicle_profit(vehicle_additional_discount_copied, sheet_name);
        ExplicitWait.visibleElement(driver, total_monthly_payment, 30);
        double monthly_total_payment_actual_from_screen=Double.parseDouble(RemoveComma.of(total_monthly_payment.getText().trim().substring(2)));
		double diff =Difference.of_two_Double_Values(monthly_total_payment_expected_from_excel, monthly_total_payment_actual_from_screen);
		boolean status =false;
		if(diff<0.2)
		{
			status=true;
			
			LO.print("Monthly Total Payment verified after editing vehicle profit");
			System.out.println("Monthly Total Payment verified after editing vehicle profit");			
		}
		
		
		return status;
	}
	
	
	public boolean put_part_exchange_values_and_check_monthly_finance_payment(String part_exchange_actual, String part_exchange_given, String less_finance_settlement, String  order_deposit, String  finance_deposit , String sheet_name) throws UnsupportedFlavorException, IOException, InterruptedException
	{
		Click.on(driver, part_exchange_and_additional_payment_button, 30);
		
		Click.sendKeys(driver, actual_part_exchange_value, part_exchange_actual , 30);
		
		Click.on(driver, given_part_exchange_value, 30);
		Thread.sleep(12000);
		
		Click.sendKeys(driver, given_part_exchange_value, part_exchange_given , 30);
		
		Click.on(driver, less_finance_Settlement, 30);		
		Thread.sleep(12000);
		
		Click.sendKeys(driver, less_finance_Settlement, less_finance_settlement, 30);
		
		Click.on(driver, order_Deposit, 30);		
		Thread.sleep(8000);
		
		Click.sendKeys(driver, order_Deposit, order_deposit, 30);
		
		Click.on(driver, finance_Deposit, 30);
		Thread.sleep(12000);
		
		Click.sendKeys(driver, finance_Deposit ,finance_deposit, 30);
		
		Click.on(driver, document_fee, 30);
		Thread.sleep(12000);
		
		ExplicitWait.visibleElement(driver, document_fee, 30);
		
		document_fee.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		
			
		clipboard =Toolkit.getDefaultToolkit().getSystemClipboard(); 
        String document_fee_copied =(String) clipboard.getData(DataFlavor.stringFlavor);
		
        Thread.sleep(3000);
				
		ExplicitWait.visibleElement(driver, balance_to_finance_value, 30);
		
		double balance_to_finance_value_from_screen =Double.parseDouble(RemoveComma.of(balance_to_finance_value.getText().trim().substring(2)));
		
		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);
		double monthly_finance_payment_actual_from_screen=Double.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));
		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		
		double[]monthlyFinanceAndBalanceToFinance = obj_read_excel_calculation_page.get_monthly_finance_payment_and_balance_to_finance_payment_after_editing_part_exchange_values(part_exchange_actual,part_exchange_given,less_finance_settlement,order_deposit,finance_deposit,document_fee_copied, sheet_name );
		
		
		double monthly_finance_payment_expected = monthlyFinanceAndBalanceToFinance[0];
		double balance_to_finance_expected = monthlyFinanceAndBalanceToFinance[1];
		
		double diff1 =Difference.of_two_Double_Values(balance_to_finance_value_from_screen, balance_to_finance_expected);
		double diff2 =Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen, monthly_finance_payment_expected);
		
		boolean status=false;
		if(diff1<0.2 && diff2<0.2)
		{
			status=true;
			LO.print("Monthly Finance Payment verified after editing part exchange values and deposit values");
			System.out.println("Monthly Finance Payment verified after editing part exchange values and deposit values");
		}
	
		return status;		
		
	}
	
	public boolean put_part_exchange_values_and_check_monthly_total_payment_with_maintenance(String part_exchange_actual, String part_exchange_given, String less_finance_settlement, String  order_deposit, String  finance_deposit , String sheet_name) throws UnsupportedFlavorException, IOException, InterruptedException
	{
		Click.on(driver, part_exchange_and_additional_payment_button, 30);
		
		Click.sendKeys(driver, actual_part_exchange_value, part_exchange_actual , 30);
		
		Click.on(driver, given_part_exchange_value, 30);
		Thread.sleep(12000);
		
		Click.sendKeys(driver, given_part_exchange_value, part_exchange_given , 30);
		
		Click.on(driver, less_finance_Settlement, 30);		
		Thread.sleep(12000);
		
		Click.sendKeys(driver, less_finance_Settlement, less_finance_settlement, 30);
		
		Click.on(driver, order_Deposit, 30);		
		Thread.sleep(8000);
		
		Click.sendKeys(driver, order_Deposit, order_deposit, 30);
		
		Click.on(driver, finance_Deposit, 30);
		Thread.sleep(12000);
		
		Click.sendKeys(driver, finance_Deposit ,finance_deposit, 30);
		
		Click.on(driver, document_fee, 30);
		Thread.sleep(12000);
		
		ExplicitWait.visibleElement(driver, document_fee, 30);
		
		document_fee.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		
			
		clipboard =Toolkit.getDefaultToolkit().getSystemClipboard(); 
        String document_fee_copied =(String) clipboard.getData(DataFlavor.stringFlavor);
		
        Thread.sleep(3000);
				
		ExplicitWait.visibleElement(driver, balance_to_finance_value, 30);
		
		double balance_to_finance_value_from_screen =Double.parseDouble(RemoveComma.of(balance_to_finance_value.getText().trim().substring(2)));
		
		ExplicitWait.visibleElement(driver, total_monthly_payment, 30);
		double monthly_total_payment_actual_from_screen=Double.parseDouble(RemoveComma.of(total_monthly_payment.getText().trim().substring(2)));
		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		
		double[]monthlyFinanceAndBalanceToFinance = obj_read_excel_calculation_page.get_monthly_total_payment_and_balance_to_finance_payment_after_editing_part_exchange_values(part_exchange_actual,part_exchange_given,less_finance_settlement,order_deposit,finance_deposit,document_fee_copied, sheet_name );
		
		
		double monthly_total_payment_expected = monthlyFinanceAndBalanceToFinance[0];
		double balance_to_finance_expected = monthlyFinanceAndBalanceToFinance[1];
		
		double diff1 =Difference.of_two_Double_Values(balance_to_finance_value_from_screen, balance_to_finance_expected);
		double diff2 =Difference.of_two_Double_Values(monthly_total_payment_actual_from_screen, monthly_total_payment_expected);
		
		boolean status=false;
		if(diff1<0.2 && diff2<0.2)
		{
			status=true;
			LO.print("Monthly total Payment verified after editing part exchange values and deposit values");
			System.out.println("Monthly total Payment verified after editing part exchange values and deposit values");
		}
	
		return status;		
		
	}

	
}
