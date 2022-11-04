package com.amt.CustomerQuotePackage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Dropdown;
import com.amt.testUtil.ExplicitWait;

public class CustomerQuotePageBrokerHPRPage extends TestBase {

	@FindBy(xpath = "//p[normalize-space()='Customer Quote']")
	private WebElement customer_quote;

	@FindBy(xpath = "//div[@class='ng-select-container']//input[@role='combobox']")
	private WebElement customer_quote_funder;

	@FindBy(xpath = "//span[normalize-space()='Popular Fiat']")
	private WebElement customer_quote_funder_option;

	@FindBy(xpath = "//input[@id='quoteReferenceNo']")
	private WebElement quote_reference;

	@FindBy(xpath = "//input[@placeholder='dd/mm/yyyy']")
	private WebElement expiry_date;

	@FindBy(xpath = "//select[@name='acquisitionPaymentProfileId']")
	private WebElement payment_profile_dropdown;

	@FindBy(xpath = "//input[@id='duration']")
	private WebElement term_period;

	@FindBy(xpath = "//input[@id='funderItemMileage']")
	private WebElement miles_per_annum;

	@FindBy(xpath = "//input[@id='contractMileage']")
	private WebElement contract_miles;

	@FindBy(xpath = "//input[@id='monthlyFinanceRental']")
	private WebElement monthly_finance_rental;

	@FindBy(xpath = "//input[@id='pencePerExcessMileageFinance']")
	private WebElement pence_Per_ExcessMileage_Finance;

	@FindBy(xpath = "//input[@id='commission']")
	private WebElement commission;

	@FindBy(xpath = "//i[@class='btn-icon-addAddress-white']")
	private WebElement add;

	@FindBy(xpath = "//div[@class='row acquisition-menu']//div[3]//button[1]")
	private WebElement save_button;

	@FindBy(xpath = "//input[@id='cashDeposit']")
	private WebElement cash_deposite;

	@FindBy(xpath = "//input[@id='financeCharges']")
	private WebElement finance_charges;

	@FindBy(xpath = "//input[@id='numberOfMonthlyPayments']")
	private WebElement no_of_monthly_payment;

	@FindBy(xpath = "//input[@id='monthlyPayment']")
	private WebElement monthly_finance_payment;

	@FindBy(xpath = "//input[@id='finalBalloonPayment']")
	private WebElement final_balloon_payment;

	@FindBy(xpath = "//input[@id='optionToPurchaseFee']")
	private WebElement option_to_purchase_fee;

	@FindBy(xpath = "//input[@id='apr']")
	private WebElement apr_rate;
	
	@FindBy(xpath = "//*[@id=\"vehicleSummery\"]/div/div[2]/div[2]/div[3]/span[2]")
	                 
	private WebElement quote_ref_no;
	

	public CustomerQuotePageBrokerHPRPage() {
		PageFactory.initElements(driver, this);
	}

	
	public void customer_Quote_broker_hpr(String manufacturer, String model, String quoteRef, 
            String quoteExpiryDate,String term,String milesperannum,
            String cashdeposite,String financecharges,
            String monthlyfinancepayments,
            String finalballoonpayment, String optiontopurchasefee, String apr, 
            String commissionvalue) throws InterruptedException {
		Click.on(driver, customer_quote, 25);

		Thread.sleep(8000);

		Click.on(driver, customer_quote_funder, 30);

		LO.print("Customer quote option has been selected");

		Click.on(driver, customer_quote_funder_option, 25);

		Click.sendKeys(driver, quote_reference, quoteRef, 25);

		Click.sendKeys(driver, expiry_date,quoteExpiryDate, 25);

		Thread.sleep(5000);

		Actions Ac = new Actions(driver);

		Ac.sendKeys(Keys.ENTER).build().perform();

		Click.sendKeys(driver, term_period, term, 25);

		Click.sendKeys(driver, miles_per_annum, milesperannum, 25);

		Click.on(driver, contract_miles, 25);

		Click.sendKeys(driver, cash_deposite, cashdeposite, 50);

		Click.sendKeys(driver, finance_charges, financecharges, 40);

		Click.on(driver, no_of_monthly_payment, 40);

		Click.sendKeys(driver, monthly_finance_payment, monthlyfinancepayments, 40);

		final_balloon_payment.clear();

		Click.sendKeys(driver, final_balloon_payment, finalballoonpayment, 40);

		Click.sendKeys(driver, option_to_purchase_fee, optiontopurchasefee, 40);

		Click.sendKeys(driver, apr_rate, apr, 40);

		Click.sendKeys(driver, commission, commissionvalue, 40);

		Click.on(driver, add, 40);
		
		LO.print("Customer quote added successfully");
		
		Click.on(driver, save_button, 25);
		
		LO.print("Customer quote saved successfully");
		
        ExplicitWait.visibleElement(driver, quote_ref_no, 25);
		
		String quoteRefNo=quote_ref_no.getText();
		System.out.println(quoteRefNo);
		
		LO.print("Quote Refno. Generated successfully is"+quoteRefNo);

	}
	
}
