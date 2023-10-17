package com.amt.CustomerQuotePackage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Difference;
import com.amt.testUtil.Dropdown;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.RemoveComma;

public class CustomerQuotePageBrokerPCHPage extends TestBase {

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//p[normalize-space()='Customer Quote']")
	private WebElement customer_quote;

	@FindBy(xpath = "//*[@class='ng-select-container']//*[@class='ng-arrow-wrapper']")
	private WebElement customer_quote_funder;

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

	@FindBy(xpath = "//*[@name='initialFinanceRental']")
	private WebElement initial_finance_rental;

	@FindBy(xpath = "//input[@id='initialMaintenanceRental']")
	private WebElement initial_maintenance_rental;

	@FindBy(xpath = "//input[@id='monthlyFinanceRental']")
	private WebElement monthly_finance_rental;

	@FindBy(xpath = "//input[@id='monthlyMaintenanceRental']")
	private WebElement monthly_maintenance_rental;

	@FindBy(xpath = "//input[@id='pencePerExcessMileageFinance']")
	private WebElement pence_Per_ExcessMileage_Finance;

	@FindBy(xpath = "//input[@id='pencePerExcessMileageMaintenance']")
	private WebElement pence_Per_ExcessMileage_maintenance;

	@FindBy(xpath = "//input[@id='commission']")
	private WebElement commission;

	@FindBy(xpath = "//i[@class='btn-icon-addAddress-white']")
	private WebElement add;

	@FindBy(xpath = "//i[@class='btn-icon-reset-black']")
	private WebElement reset;

	@FindBy(xpath = "//div[@class='row acquisition-menu']//div[3]//button[1]")
	private WebElement save_button;

	@FindBy(xpath = "//span[@class='slider round']")
	private WebElement maintenance_toggle_button;

	// security deposit
	@FindBy(xpath = "//*[@id='securityDeposit']")
	private WebElement security_deposit_input_field;

	// matrix upsell
	@FindBy(xpath = "//*[@id='upsell']")
	private WebElement matrix_upsell_input_field;

	// referrer commission
	@FindBy(xpath = "//*[@id='FinanceCommission']")
	private WebElement referrer_upsell_input_field;

	// part exchange actual
	@FindBy(xpath = "//input[@id='otrPartExchange']")
	private WebElement partExchangeactual;

	// part exchange given
	@FindBy(xpath = "//input[@id='partExchange']")
	private WebElement partExchangegiven;

	// less finance settlement
	@FindBy(xpath = "//input[@id='lessFinanceSettlement']")
	private WebElement lessFinancesettlement;

	// order deposit
	@FindBy(xpath = "//input[@name='orderDeposit']")
	private WebElement order_deposit;

	// Document Fee
	@FindBy(xpath = "//input[@name='documentFee']")
	private WebElement documentFee;

	// Part Exchange value
	@FindBy(xpath = "//*[normalize-space()='Net part exchange allowance']//ancestor::div[1]//p//strong")
	private WebElement part_exchange_allowance;

	// Balance due
	@FindBy(xpath = "//*[normalize-space()='Balance due']//ancestor::div[1]//div//span")
	private WebElement balance_due;

	@FindBy(xpath = "//*[@id='registrationNumber']")
	private WebElement registration_number;

	@FindBy(xpath = "//*[normalize-space()='Search']")
	private WebElement search_button;

	@FindBy(xpath = "//*[@id='mileage']")
	private WebElement mileage;

	@FindBy(xpath = "//*[@id='partExchange']|//*[@id='partExchnage']")
	private WebElement given_part_exchange_value;

	@FindBy(xpath = "//*[@id='partExchange_1']/button/div")
	private WebElement part_exchange_payment;

	@FindBy(xpath = "//*[@id='otrPartExchange']")
	private WebElement actual_part_exchange_value;

	@FindBy(xpath = "//*[@id='lessFinanceSettlement']")
	private WebElement less_finance_settlement;

	@FindBy(xpath = "//*[@name='orderDeposit']")
	private WebElement order_Deposit;

	@FindBy(xpath = "//*[@name='financeDeposit']")
	private WebElement finance_Deposit;

	@FindBy(xpath = "//*[@id='DocumentFee']")
	private WebElement document_fee;

	@FindBy(xpath = "//*[@name='FunderName']")
	private WebElement funder_name;

	@FindBy(xpath = "//*[@name='agreementName']")
	private WebElement agreement_number;

	@FindBy(xpath = "//*[@id='settlementExpiredDate']")
	private WebElement settlement_expiry_date;

	@FindBy(xpath = "//*[@id='vatQualifying']")
	private WebElement check_box_vat_qualifying;

	@FindBy(xpath = "//*[@id='OutstandingFinance']")
	private WebElement check_box_outstanding_finance;

	@FindBy(xpath = "//*[@id='SupplierSettingFinance']")
	private WebElement check_box_supplier_setting_finance;
	
	Properties prop;

	public CustomerQuotePageBrokerPCHPage() {
		
		

		try {
			 prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"D:\\Orders_Vehicles\\AMT_Orders_Vehicles\\src\\main\\java\\configs\\excelValues.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PageFactory.initElements(driver, this);
	}

	public boolean customer_Quote_broker_pch_with_maintenance(String quoteRef, String quoteExpiryDate, String term,
			String milesperannum, String initialFinanceRental, String initialMaintenanceRental,
			String monthlyFinanceRental, String monthlyMaintenanceRental, String pensePerExcessMileFinance,
			String pensePerExcessMileMaintenance, String commission2) throws InterruptedException {

		Click.on(driver, customer_quote, 25);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		int count = 0;

		for (int i = 1; i <= 1; i++) {

			Thread.sleep(3000);

			Click.on(driver, maintenance_toggle_button, 20);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			Click.on(driver, customer_quote_funder, 60);

			Thread.sleep(2000);

			Actions act = new Actions(driver);
			act.sendKeys(Keys.ENTER).build().perform();

			// LO.print("Customer quote option has been selected");

			Click.sendKeys(driver, quote_reference, quoteRef, 60);

			Click.sendKeys(driver, expiry_date, quoteExpiryDate, 60);

//        try {
//			   WebElement dropdown = new  WebDriverWait(driver , Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//select[@name='acquisitionPaymentProfileId']"))));
//			   Dropdown.select(driver, dropdown, i , 60);
//			}
//        catch(Exception e)
//        {
//        	 WebElement dropdown = new  WebDriverWait(driver , Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//select[@name='acquisitionPaymentProfileId']"))));
//			  Dropdown.select(driver, dropdown, i , 60);
//        }
			Dropdown.select(driver, payment_profile_dropdown, i, 60);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			int term_converted = Integer.parseInt(term);

			Click.sendKeysint(driver, term_period, (term_converted + i), 60);

			Click.sendKeys(driver, miles_per_annum, milesperannum, 60);

			Click.on(driver, contract_miles, 60);

			if (i == 2) {
				Click.sendKeys(driver, initial_finance_rental, initialFinanceRental, 60);
				Click.sendKeys(driver, initial_maintenance_rental, initialMaintenanceRental, 60);
			}

			Click.sendKeys(driver, monthly_finance_rental, monthlyFinanceRental, 60);

			Click.sendKeys(driver, monthly_maintenance_rental, monthlyMaintenanceRental, 60);

			Click.sendKeys(driver, pence_Per_ExcessMileage_Finance, pensePerExcessMileFinance, 60);

			Click.sendKeys(driver, pence_Per_ExcessMileage_maintenance, pensePerExcessMileMaintenance, 60);

			Click.sendKeys(driver, commission, commission2, 60);

			Click.on(driver, add, 60);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			count++;
		}

		System.out.println("Funder quote added successfully");
		LO.print("Funder quote added successfully");

		boolean flag = false;
		if (count == 1 && save_button.isEnabled()) {
			flag = true;
		}
		return flag;
	}

	public boolean put_part_exchange_and_verify_balance_due(String partExchangeActual,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel) throws UnsupportedFlavorException, IOException, InterruptedException {

		LO.print("");
		System.out.println("");

		LO.print("Started verifying Balance Due Value");
		System.out.println("Started verifying Balance Due Value");

		Actions act = new Actions(driver);

		ExplicitWait.clickableElement(driver, part_exchange_payment, 50);
		Thread.sleep(4000);
		// Click.on(driver, part_exchange_payment, 70);
		LO.print("Clicked on Part Exchange panel");
		System.out.println("Clicked on Part Exchange panel");

		Click.on(driver, given_part_exchange_value, 20);

		given_part_exchange_value.clear();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.sendKeys(driver, given_part_exchange_value, given_part_exchange_value_from_excel, 30);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		// Clicking on outstanding finance and suppliersettling finance checkbox

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		// ExplicitWait.clickableElement(driver, check_box_outstanding_finance, 20);

		jse.executeScript("arguments[0].click();", check_box_outstanding_finance, 20);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		// ExplicitWait.clickableElement(driver, check_box_supplier_setting_finance,
		// 20);

	

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.sendKeys(driver, funder_name, "Funder X", 20);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.sendKeys(driver, agreement_number, "123", 20);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, less_finance_settlement, 20);
		less_finance_settlement.clear();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.sendKeys(driver, less_finance_settlement, less_finance_settlement_from_excel, 20);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, order_Deposit, 20);
		order_Deposit.clear();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.sendKeys(driver, order_Deposit, order_deposit_from_excel, 30);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);


		   ExplicitWait.visibleElement(driver, document_fee, 30);		
		   String document_fee_copied = document_fee.getAttribute("value");
		double balanceDueDefault = (Double.parseDouble(document_fee_copied));

		double orderDepositConverted = (Double.parseDouble(order_deposit_from_excel));

		ExplicitWait.visibleElement(driver, part_exchange_allowance, 30);

		double part_exchange_value_from_screen = Double
				.parseDouble(RemoveComma.of(part_exchange_allowance.getText().trim().substring(2)));

		double balanceDueExpected = (balanceDueDefault - part_exchange_value_from_screen + orderDepositConverted);

		LO.print("Balance Due Value Expected = " + balanceDueExpected);
		System.out.println("Balance Due Value Expected = " + balanceDueExpected);

		ExplicitWait.visibleElement(driver, balance_due, 30);

		double balanceDueFromScreen = Double.parseDouble(RemoveComma.of(balance_due.getText().trim().substring(2)));

		LO.print("Balance Due Value Actual from screen = " + balanceDueFromScreen);
		System.out.println("Balance Due Value Actual from screen = " + balanceDueFromScreen);

		boolean flag = false;
		if (Difference.of_two_Double_Values(balanceDueExpected, balanceDueFromScreen) < 0.2) {
			flag = true;

			LO.print("Balance Due Value verified and found OK");
			System.out.println("Balance Due Value verified and found OK");

		}

		else {
			LO.print("Balance Due Value verified but found wrong");
			System.err.println("Balance Due Value verified but found wrong");

		}
		return flag;

	}

	public boolean customer_Quote_broker_pch_without_maintenance(String quoteRef, String quoteExpiryDate, String term,
			String milesperannum, String initialFinanceRental, String monthlyFinanceRental,
			String pensePerExcessMileFinance, String commission2) throws InterruptedException {

		Click.on(driver, customer_quote, 25);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

//		Select sl = new Select(payment_profile_dropdown);
//		
//		List<WebElement> list =sl.getOptions();
//		
//		int dropdown_lenth=list.size();

		int count = 0;

		for (int i = 1; i <= 1; i++) {

			Thread.sleep(3000);

			Click.on(driver, customer_quote_funder, 60);

			Thread.sleep(3000);

			Actions act = new Actions(driver);
			act.sendKeys(Keys.ENTER).build().perform();

			// LO.print("Customer quote option has been selected");

			Click.sendKeys(driver, quote_reference, quoteRef, 60);

			Click.sendKeys(driver, expiry_date, quoteExpiryDate, 60);

			try {
				WebElement dropdown = new WebDriverWait(driver, Duration.ofSeconds(60))
						.until(ExpectedConditions.elementToBeClickable(
								driver.findElement(By.xpath("//select[@name='acquisitionPaymentProfileId']"))));
				Dropdown.select(driver, dropdown, i, 60);
			} catch (Exception e) {
				WebElement dropdown = new WebDriverWait(driver, Duration.ofSeconds(60))
						.until(ExpectedConditions.elementToBeClickable(
								driver.findElement(By.xpath("//select[@name='acquisitionPaymentProfileId']"))));
				Dropdown.select(driver, dropdown, i, 60);
			}

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			int term_converted = Integer.parseInt(term);

			Click.sendKeysint(driver, term_period, (term_converted + i), 60);

			Click.sendKeys(driver, miles_per_annum, milesperannum, 60);

			Click.on(driver, contract_miles, 60);

			if (i == 2) {
				Click.sendKeys(driver, initial_finance_rental, initialFinanceRental, 60);
			}

			Click.sendKeys(driver, monthly_finance_rental, monthlyFinanceRental, 60);

			Click.sendKeys(driver, pence_Per_ExcessMileage_Finance, pensePerExcessMileFinance, 60);

			Click.sendKeys(driver, commission, commission2, 60);

			Click.on(driver, add, 60);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			count++;
		}

		System.out.println("Funder quote added successfully");
		LO.print("Funder quote added successfully");

		boolean flag = false;
		if (count == 1 && save_button.isEnabled()) {
			flag = true;
		}
		return flag;
	}

}
