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

public class CustomerQuotePage_OP_OP_Page extends TestBase {
	
	CustomerQuotePage_OP_OP_Page obj_cust_quote_outright_bchPage;
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
	
		
	@FindBy(xpath = "//input[@id='depositRequired']")
	private WebElement deposit_required;
	
	@FindBy(xpath = "//div[@class='partex-value']//input[@name='financeDeposit']")
	private WebElement finance_Deposit;
	
	
	@FindBy(xpath = "//div[@class='partex-col docfee-center']//input[@name='DocumentFee']")
	private WebElement document_fee;
	
	
	@FindBy(xpath = "//p[@class='font-14 m-0 bold text-right value-70']")
	private WebElement net_part_exchange_allowance;
	
	
	@FindBy(xpath = "//*[@id=\"collapseThree\"]/div/div/div/div/div[2]/div/div[3]/div/span")
	private WebElement pending_amount;
	
	
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
	
	@FindBy(xpath = "//input[@id='VehicleProfit']")
	private WebElement vehicle_profit_input;
	
	
	@FindBy(xpath = "//div[11]//div[3]//p[1]")
	private WebElement vehicle_sales_price;
	
	
	@FindBy(xpath = "//*[@id=\"collapseTwo\"]/div/div/div/div[2]/div[11]/div[2]/p")
	private WebElement vehicle_otr_price;
	
	
	
	@FindBy(xpath = "//*[contains(text(),' Part exchange & additional payments ')]")
	private WebElement part_exchange_and_additional_payment_button;
	
	
	@FindBy(xpath = "//*[@id='collapseFirst']/div/div/div[5]/label/span")
	private WebElement balloon_payment_toggle;
	
	@FindBy(xpath = "//app-purchase-customer-quote-summary-header/div/div[6]/div/p/strong")
	private WebElement total_monthly_payment;
	
	@FindBy(xpath = "//input[@id='offInvoiceSupport']")
	private WebElement rebate_input_field;
	
	
	
	

	public CustomerQuotePage_OP_OP_Page() {
		PageFactory.initElements(driver, this);
	
		}

		
	
	
	public boolean edit_vehicle_profit_and_check_monthly_total_payment(String vehicle_profit, String sheet_name) throws InterruptedException, UnsupportedFlavorException, IOException
	{
		
	Click.on(driver, customer_quote, 30);
		
		LO.print("***********Entered in Customer Quote page ***********");
		System.out.println("***********Entered in Customer Quote page ***********");
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		ExplicitWait.visibleElement(driver, vehicle_profit_input, 30);
		vehicle_profit_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeys(driver, vehicle_profit_input, vehicle_profit, 30);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);	
		ExplicitWait.visibleElement(driver, vehicle_sales_price, 30);
		double vehicleSalesPriceFromScreen =Double.parseDouble(RemoveComma.of(vehicle_sales_price.getText().trim().substring(2)));
		double vehicleOTRPriceFromScreen =Double.parseDouble(RemoveComma.of(vehicle_otr_price.getText().trim().substring(2)));
		double vehicleProfit = Double.parseDouble(vehicle_profit);
		double vehicleSalesPriceFromActual = vehicleOTRPriceFromScreen +(vehicleProfit * 1.2 ) ;
		
		double diff = Difference.of_two_Double_Values(vehicleSalesPriceFromScreen, vehicleSalesPriceFromActual);
		
		boolean status=true;
		
		if(diff<0.2)
		{
			status=true;
			
			LO.print("Vehicle profit "+vehicleProfit+" added to OTR price "+ vehicleOTRPriceFromScreen +"and sales price updated is "+vehicleSalesPriceFromActual+" i.e. true");
			System.out.println("Vehicle profit "+vehicleProfit+" added to OTR price "+ vehicleOTRPriceFromScreen +"and sales price updated is "+vehicleSalesPriceFromActual+" i.e. true");			
		}	
		
		return status;
	}
	
	
		
	
	public boolean put_part_exchange_values_and_check_monthly_finance_payment(String part_exchange_actual, String part_exchange_given, String less_finance_settlement, String  order_deposit, String  finance_deposit , String documentFee , String sheet_name) throws UnsupportedFlavorException, IOException, InterruptedException
	{
		
		
		Click.sendKeys(driver, actual_part_exchange_value, part_exchange_actual , 30);
		
		Click.on(driver, given_part_exchange_value, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.sendKeys(driver, given_part_exchange_value, part_exchange_given , 30);
		
		Click.on(driver, less_finance_Settlement, 30);		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.sendKeys(driver, less_finance_Settlement, less_finance_settlement, 30);
		
		Click.on(driver, deposit_required, 30);		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.sendKeys(driver, deposit_required, order_deposit, 30);
		
		Click.on(driver, document_fee , 30);
         ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.sendKeys(driver, document_fee, documentFee, 30);
		
		double vehicleSalesPriceFromScreen =Double.parseDouble(RemoveComma.of(vehicle_sales_price.getText().trim().substring(2)));
		
		double netPartExchangeAllowance =Double.parseDouble(RemoveComma.of(net_part_exchange_allowance.getText().trim().substring(2)));
		
		double orderDeposit = Double.parseDouble(order_deposit);
		
		double documentfee = Double.parseDouble(documentFee);
		
		double pendingAmountExpected = vehicleSalesPriceFromScreen - orderDeposit - netPartExchangeAllowance + (documentfee*1.2) ;
		
		double pendingAmountFromScreen =Double.parseDouble(RemoveComma.of(pending_amount.getText().trim().substring(2)));

		double diff = Difference.of_two_Double_Values(pendingAmountExpected, pendingAmountFromScreen);
		
						
		boolean status=false;
		if(diff<0.2)
		{
			status=true;
			LO.print("Pending Amount from screen "+pendingAmountFromScreen+" Verified Successfully "+"with applied formula "+pendingAmountExpected +" i.e. true");
			System.out.println("Pending Amount from screen "+pendingAmountFromScreen+" Verified Successfully "+"with applied formula "+pendingAmountExpected+" i.e. true");
		}
	
		return status;		
		
	}
	
	
}
