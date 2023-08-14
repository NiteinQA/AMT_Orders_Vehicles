package com.amt.CustomerQuotePackage;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.QuoteSummaryPages.QuoteSummaryOutrightHPNRPage;
import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Difference;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.ReadExcelCalculationForPurchaseAgreement;
import com.amt.testUtil.RemoveComma;

public class CustomerQuotePage_HPNR_HPRPage extends TestBase {

	CustomerQuotePage_HPNR_HPRPage obj_cust_quote_outright_bchPage;
	ReadExcelCalculationForPurchaseAgreement obj_read_excel_calculation_page;

	Clipboard clipboard;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;
	
	//quote summary
	@FindBy(xpath = "//p[normalize-space()='Quote summary']")
	private WebElement quote_summary;

	// vehicle_discount_cost_price
	@FindBy(xpath = "//*[@id='bdiscount']//ancestor::div[3]//div//p")
	private WebElement vehicle_discount_cost_price;

	// vehicle_additional_discount_cost_price
	@FindBy(xpath = "(//*[@id='bdiscountvalue']//ancestor::div[3]//div)[1]")
	private WebElement vehicle_additional_discount_cost_price;

	// paint_discount_cost_price
	@FindBy(xpath = "(//*[@id='pdiscountper']//ancestor::div[3]//div)[1]")
	private WebElement paint_discount_cost_price;

	// paint_additional_discount_cost_price
	@FindBy(xpath = "(//*[@id='pdiscountvalue']//ancestor::div[3]//div)[1]")
	private WebElement paint_additional_discount_cost_price;

	// options_discount_cost_price
	@FindBy(xpath = "(//*[@id='odiscount']//ancestor::div[3]//div)[1]")
	private WebElement options_discount_cost_price;

	// options_additional_discount_cost_price
	@FindBy(xpath = "(//*[@id='odiscountvalue']//ancestor::div[3]//div)[1]")
	private WebElement options_additional_discount_cost_price;

	@FindBy(xpath = "//p[normalize-space()='Customer Quote']")
	private WebElement customer_quote;

	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-all-customer-quotes[1]/div[1]/app-aquisition-hire-agreement[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[6]/div[4]")
	private WebElement customer_quote_matrix_default_cell;

	@FindBy(xpath = "//*[normalize-space()='Monthly finance payment']//ancestor::div[1]//div//p//strong|//*[normalize-space()='Monthly finance rental']//ancestor::div[1]//div//p//strong")
	private WebElement customer_quote_monthly_finance_rental;

	@FindBy(xpath = "//*[normalize-space()='Monthly maint. payment']//ancestor::div[1]//div//p//strong|//*[normalize-space()='Monthly maint. rental']//ancestor::div[1]//div//p//strong")
	private WebElement customer_quote_monthly_maintenance_rental;

	@FindBy(xpath = "//*[normalize-space()='Total monthly payment']//ancestor::div[1]//div//p//strong|//*[normalize-space()='Total monthly rental']//ancestor::div[1]//div//p//strong")
	private WebElement customer_quote_monthly_total_rental;

	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/app-aquisition-header[1]/div[1]/div[2]/div[3]/button[1]")
	private WebElement save_button;

	@FindBy(xpath = "//select[@name='acquisitionPaymentProfileId']")
	private WebElement customer_quote_payment_profile_dropdown;

	@FindBy(xpath = "//*[normalize-space()='Balance to finance']//ancestor::div[1]//div//p//strong")
	private WebElement balance_to_finance_value;

	@FindBy(xpath = "//*[@id='collapseFirst']/div/div/div[1]/label")
	private WebElement customer_quote_maintenance_toggle_button;

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

	@FindBy(xpath = "((//*[normalize-space()='On the road price']//ancestor::div[1])[1])//div[2]")
	private WebElement otr_cost_price;

	@FindBy(xpath = "//*[@name='salesTotal']")
	private WebElement sales_total_input;

	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[1]/button/div")
	private WebElement customer_quote_summary;

	@FindBy(xpath = "//*[normalize-space()='Basic cash price']//ancestor::div[1]//div//p//strong")
	private WebElement customer_quote_summary_basic_cash_price;

	@FindBy(xpath = "//*[normalize-space()='Term']//ancestor::div[1]//div//p//strong")
	private WebElement customer_quote_summary_terms;

	@FindBy(xpath = "//p[contains(text(),'Holding cost')]")
	private WebElement holding_cost;

	@FindBy(xpath = "//*[contains(text(),' Holding cost summary ')]")
	private WebElement holding_cost_summary;

	@FindBy(xpath = "//*[contains(text(),'CAP residual value')]//ancestor::div[1]//p//strong")
	private WebElement holding_cost_summary_residual_value_used;

	@FindBy(xpath = "//*[normalize-space()='Total CAP maint. value (ex. VAT):']//ancestor::div[1]//p|//*[normalize-space()='Total CAP maint. value (ex. VAT) :']//ancestor::div[1]//p")
	private WebElement total_cap_maintenance_value;

	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/div/div[1]/div/p/strong")
	private WebElement holding_cost_summary_terms;

	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/div/div[2]/div/p/strong")
	private WebElement holding_cost_summary_mileage;

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

	@FindBy(xpath = "//*[normalize-space()='Matrix Credit type']//ancestor::div[1]//div//ng-select")
	private WebElement matrix_credit_type_dropdown;

	@FindBy(xpath = "//*[@id='ResidualPercentage']")
	private WebElement holding_cost_percentage_cap_residual_value_used;

	@FindBy(xpath = "//input[@id='CapMaintenancePercentage']")
	private WebElement holding_cost_percentage_maintenance_cost_used;

	@FindBy(xpath = "//input[@id='ResidualValue']")
	private WebElement residual_value_used;

	@FindBy(xpath = "//input[@id='Maintenancevalue3']")
	private WebElement maintenance_cost_used;

	Properties prop;
	
	public CustomerQuotePage_HPNR_HPRPage() {
		
				try {
			 prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"D:\\Acquisition\\AMT_Automation_Acquisition\\src\\main\\java\\configs\\excelValues.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PageFactory.initElements(driver, this);

	}

	public void save_changes_and_back_to_customer_quote_page() throws InterruptedException {

		Thread.sleep(2000);

		QuoteSummaryOutrightHPNRPage obj_save_method = new QuoteSummaryOutrightHPNRPage();

		obj_save_method.save_changes();

		Click.on(driver, customer_quote, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(2000);

	}

	public boolean edit_otr_sales_price_and_check_monthly_total_payment_with_maintenance(String sales_price_percentage,
			String sheet_name) throws InterruptedException, UnsupportedFlavorException, IOException {

		LO.print("");
		System.out.println("");

		LO.print("Verifying Vehicle profit and Total Monthly Payment on editing Vehicle Sales Price");
		System.out.println("Verifying Vehicle profit and Total Monthly Payment on editing Vehicle Sales Price");

		// getting screen otr price
		ExplicitWait.visibleElement(driver, otr_cost_price, 30);
		double otrCostPrice = Double.parseDouble(RemoveComma.of(otr_cost_price.getText().trim().substring(2)));

		// code for sending input to sales total input
		ExplicitWait.visibleElement(driver, sales_total_input, 30);
		sales_total_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Thread.sleep(1000);

		double salesPricePercentage = Double.parseDouble(sales_price_percentage);
		double salesPrice = (((otrCostPrice * salesPricePercentage) / 100) + otrCostPrice);
		Click.sendKeys(driver, sales_total_input, String.valueOf(salesPrice), 20);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		LO.print("Sending " + salesPrice + " to sales total input field");
		System.out.println("Sending " + salesPrice + " to sales total input field");

		double vehicel_profit_expected = (salesPrice - otrCostPrice) / 1.2;

		ExplicitWait.visibleElement(driver, vehicle_profit_input, 30);
		Thread.sleep(1000);

		vehicle_profit_input.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String vehicle_profit_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		double vehicel_profit_actual = Double.parseDouble(vehicle_profit_copied);

		double diff1 = Difference.of_two_Double_Values(vehicel_profit_expected, vehicel_profit_actual);

		LO.print("Vehicle profit Expected is = " + vehicel_profit_expected);
		System.out.println("Vehicle profit Expected is = " + vehicel_profit_expected);

		LO.print("Vehicle profit Actual is = " + vehicel_profit_actual);
		System.out.println("Vehicle profit Actual is = " + vehicel_profit_actual);

		ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String vehicle_additional_discount_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

	

		// waiting till url responds 200
//		try {
//			HttpURLConnection c = (HttpURLConnection) new URL(prop.getProperty("calculate_purchase_ownBook"))
//					.openConnection();
//			c.setRequestMethod("HEAD");
//			c.connect();
//			int r = c.getResponseCode();
//
//			int i = 0;
//			while (i <= 15 && r != 200 || r != 405) {
//				Thread.sleep(1000);
//				r = c.getResponseCode();
//				i++;
//			}
//		} catch (Exception e) {
//		}
		
		
		Click.on(driver, quote_summary, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.on(driver, customer_quote, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		
		try {
			
		
		ExplicitWait.visibleElement(driver, customer_quote_monthly_total_rental, 30);

		double monthly_total_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_total_rental.getText().trim().substring(2)));
		
		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		double monthly_total_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_total_payment_after_editing_vehicle_profit(vehicle_additional_discount_copied, sheet_name);
		
		double diff = Difference.of_two_Double_Values(monthly_total_payment_expected_from_excel,
				monthly_total_payment_actual_from_screen);

		LO.print("Monthly Total Payment Expected is = " + monthly_total_payment_expected_from_excel);
		System.out.println("Monthly Total Payment Expected is = " + monthly_total_payment_expected_from_excel);

		LO.print("Monthly Total Payment Actual is = " + monthly_total_payment_actual_from_screen);
		System.out.println("Monthly Total Payment Actual is = " + monthly_total_payment_actual_from_screen);

		boolean status = false;

		if (diff < 0.2 && diff1 < 0.2) {
			status = true;

			LO.print("Vehicle profit and Total Monthly Payment verified on editing Vehicle Sales Price");
			System.out.println("Vehicle profit and Total Monthly Payment verified on editing Vehicle Sales Price");
		}

		return status;
		}catch(Exception e)
		{
			ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

			double monthly_total_payment_actual_from_screen = Double
					.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

			double monthly_total_payment_expected_from_excel = obj_read_excel_calculation_page
					.get_monthly_finance_payment_after_editing_vehicle_profit(vehicle_additional_discount_copied,
							sheet_name);
			
			double diff = Difference.of_two_Double_Values(monthly_total_payment_expected_from_excel,
					monthly_total_payment_actual_from_screen);

			LO.print("");
			System.out.println("");

			LO.print("Vehicle Profit Expected is " + vehicel_profit_expected);
			System.out.println("Vehicle Profit Expected is " + vehicel_profit_expected);

			LO.print("Vehicle Profit Actual is " + vehicel_profit_actual);
			System.out.println("Vehicle Profit Actual is " + vehicel_profit_actual);

			LO.print("");
			System.out.println("");

			LO.print("Monthly Total Payment Expected is " + monthly_total_payment_expected_from_excel);
			System.out.println("Monthly Total Payment Expected is " + monthly_total_payment_expected_from_excel);

			LO.print("Monthly Total Payment Actual is " + monthly_total_payment_actual_from_screen);
			System.out.println("Monthly Total Payment Actual is " + monthly_total_payment_actual_from_screen);

			LO.print("");
			System.out.println("");

			boolean status = false;

			if (diff < 0.2 && diff1 < 0.2) {
				status = true;

				LO.print("Vehicle profit and Total Monthly Payment verified on editing Vehicle Sales Price");
				System.out.println("Vehicle profit and Total Monthly Payment verified on editing Vehicle Sales Price");
			}

			return status;
		
		}
	}

	public boolean edit_otr_sales_price_and_check_monthly_total_payment_without_maintenance(
			String sales_price_percentage, String sheet_name)
			throws InterruptedException, UnsupportedFlavorException, IOException {

		LO.print("");
		System.out.println("");

		LO.print("Verifying Vehicle profit and Total Monthly Payment on editing Vehicle Sales Price");
		System.out.println("Verifying Vehicle profit and Total Monthly Payment on editing Vehicle Sales Price");

		// getting screen otr price
		ExplicitWait.visibleElement(driver, otr_cost_price, 30);
		double otrCostPrice = Double.parseDouble(RemoveComma.of(otr_cost_price.getText().trim().substring(2)));

		// code for sending input to sales total input
		ExplicitWait.visibleElement(driver, sales_total_input, 30);
		sales_total_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		double salesPricePercentage = Double.parseDouble(sales_price_percentage);
		double salesPrice = (((otrCostPrice * salesPricePercentage) / 100) + otrCostPrice);
		Click.sendKeys(driver, sales_total_input, String.valueOf(salesPrice), 20);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		LO.print("Sending " + salesPrice + " to sales total input field");
		System.out.println("Sending " + salesPrice + " to sales total input field");

		double vehicel_profit_expected = (salesPrice - otrCostPrice) / 1.2;

		ExplicitWait.visibleElement(driver, vehicle_profit_input, 30);
		vehicle_profit_input.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String vehicle_profit_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		double vehicel_profit_actual = Double.parseDouble(vehicle_profit_copied);

		double diff1 = Difference.of_two_Double_Values(vehicel_profit_expected, vehicel_profit_actual);

		ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String vehicle_additional_discount_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		double monthly_total_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_finance_payment_after_editing_vehicle_profit(vehicle_additional_discount_copied,
						sheet_name);
		

//		//calling save method
//		CustomerQuotePage_HPNR_HPRPage obj_cust_quote_class = new CustomerQuotePage_HPNR_HPRPage();		
//		obj_cust_quote_class.save_changes_and_back_to_customer_quote_page();

//		try {
//			HttpURLConnection c = (HttpURLConnection) new URL(prop.getProperty("calculate_purchase_ownBook"))
//					.openConnection();
//			c.setRequestMethod("HEAD");
//			c.connect();
//			int r = c.getResponseCode();
//
//			int i = 0;
//			while (i <= 15 && r != 200 || r != 405) {
//				Thread.sleep(1000);
//				r = c.getResponseCode();
//				i++;
//			}
//		} catch (Exception e) {
//		}
		
		Click.on(driver, quote_summary, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.on(driver, customer_quote, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

		double monthly_total_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		double diff = Difference.of_two_Double_Values(monthly_total_payment_expected_from_excel,
				monthly_total_payment_actual_from_screen);

		LO.print("");
		System.out.println("");

		LO.print("Vehicle Profit Expected is " + vehicel_profit_expected);
		System.out.println("Vehicle Profit Expected is " + vehicel_profit_expected);

		LO.print("Vehicle Profit Actual is " + vehicel_profit_actual);
		System.out.println("Vehicle Profit Actual is " + vehicel_profit_actual);

		LO.print("");
		System.out.println("");

		LO.print("Monthly Total Payment Expected is " + monthly_total_payment_expected_from_excel);
		System.out.println("Monthly Total Payment Expected is " + monthly_total_payment_expected_from_excel);

		LO.print("Monthly Total Payment Actual is " + monthly_total_payment_actual_from_screen);
		System.out.println("Monthly Total Payment Actual is " + monthly_total_payment_actual_from_screen);

		LO.print("");
		System.out.println("");

		boolean status = false;

		if (diff < 0.2 && diff1 < 0.2) {
			status = true;

			LO.print("Vehicle profit and Total Monthly Payment verified on editing Vehicle Sales Price");
			System.out.println("Vehicle profit and Total Monthly Payment verified on editing Vehicle Sales Price");
		}

		return status;
	}

	public boolean check_monthly_finance_payment_on_customer_quote(WebDriver driver, String maintenance_status,
			String matrix_credit_type, String balloon_payment_status, String order_deposit, String finance_deposit,
			String document_fee, String sheet_name) throws InterruptedException, IOException,
			UnsupportedFlavorException, NumberFormatException, ClassNotFoundException {

		Thread.sleep(2000);

		Click.on(driver, customer_quote, 30);

		LO.print("***********Entered in Customer Quote page ***********");
		System.out.println("***********Entered in Customer Quote page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Actions act = new Actions(driver);

		Click.on(driver, matrix_credit_type_dropdown, 50);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(5000);
		try {
			List<WebElement> list = driver
					.findElements(By.xpath("//*[@class='ng-dropdown-panel-items scroll-host']/div/div/span"));

			for (WebElement e : list) {

				if (e.getText().equalsIgnoreCase(matrix_credit_type)) {
					Click.on(driver, e, 20);

					ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		LO.print("Matrix credit type " + matrix_credit_type + " has been selected");
		System.out.println("Matrix credit type " + matrix_credit_type + " has been selected");

		ExplicitWait.visibleElement(driver, customer_quote_summary_terms, 20);

		String term = customer_quote_summary_terms.getText().trim().substring(0, 1);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement(term, matrix_credit_type,
				sheet_name);

		ExplicitWait.visibleElement(driver, vehicle_discount, 30);

		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		vehicle_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		String vehicle_discount_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		paint_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		String paint_discount_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		options_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		String options_discount_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		String vehicle_additional_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		paint_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		String paint_additional_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		options_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		String options_additional_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

//		CustomerQuotePage_HPNR_HPRPage obj_cust_quote_class = new CustomerQuotePage_HPNR_HPRPage();
//		
//		obj_cust_quote_class.save_changes_and_back_to_customer_quote_page();

//		try {
//			HttpURLConnection c = (HttpURLConnection) new URL(prop.getProperty("calculate_purchase_ownBook"))
//					.openConnection();
//			c.setRequestMethod("HEAD");
//			c.connect();
//			int r = c.getResponseCode();
//
//			int i = 0;
//			while (i <= 15 && r != 200 || r != 405) {
//				Thread.sleep(1000);
//				r = c.getResponseCode();
//				i++;
//			}
//		} catch (Exception e) {
//		}
		
		Click.on(driver, quote_summary, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.on(driver, customer_quote, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
		System.out
				.println("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		double monthly_finance_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_finanace_payment_from_excel(maintenance_status, matrix_credit_type, balloon_payment_status,
						order_deposit, finance_deposit, document_fee, vehicle_discount_copied, paint_discount_copied,
						options_discount_copied, vehicle_additional_copied, paint_additional_copied,
						options_additional_copied, sheet_name);

		LO.print("Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);
		System.out.println(
				"Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);

		double diff = Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen,
				monthly_finance_payment_expected_from_excel);
		boolean status = false;
		if (diff < 0.2) {
			status = true;
		}
		return status;
	}

	public boolean check_monthly_finance_payment_on_customer_quote_for_used_vehicle(WebDriver driver,
			String maintenance_status, String matrix_credit_type, String balloon_payment_status, String order_deposit,
			String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException,
			UnsupportedFlavorException, NumberFormatException, ClassNotFoundException {

		Thread.sleep(2000);

		Click.on(driver, customer_quote, 30);

		LO.print("***********Entered in Customer Quote page ***********");
		System.out.println("***********Entered in Customer Quote page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Actions act = new Actions(driver);

		Click.on(driver, matrix_credit_type_dropdown, 50);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(3000);
		try {
			List<WebElement> list = driver
					.findElements(By.xpath("//*[@class='ng-dropdown-panel-items scroll-host']/div/div"));

			for (WebElement e : list) {

				if (e.getText().equalsIgnoreCase(matrix_credit_type)) {
					// Click.on(driver, e, 20);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", e);
					ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		LO.print("Matrix credit type " + matrix_credit_type + " has been selected");
		System.out.println("Matrix credit type " + matrix_credit_type + " has been selected");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.on(driver, customer_quote_summary, 40);

		ExplicitWait.visibleElement(driver, customer_quote_summary_basic_cash_price, 20);

		Thread.sleep(5000);

		double basic_cash_price_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

		Click.on(driver, holding_cost, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.on(driver, holding_cost_summary, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, holding_cost_summary_terms, 30);

		double duration = Double.parseDouble(holding_cost_summary_terms.getText().substring(0, 2));

		ExplicitWait.visibleElement(driver, holding_cost_summary_mileage, 30);

		double annual_mileage = Double.parseDouble(RemoveComma.of(holding_cost_summary_mileage.getText()));

		ExplicitWait.visibleElement(driver, holding_cost_percentage_cap_residual_value_used, 30);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		holding_cost_percentage_cap_residual_value_used.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double percentage_cap_residual_value = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

		ExplicitWait.visibleElement(driver, residual_value_used, 30);

		residual_value_used.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		double used_residual_value = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

		ExplicitWait.visibleElement(driver, customer_quote_summary_terms, 20);

		String term = customer_quote_summary_terms.getText().trim().substring(0, 2);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement(term, matrix_credit_type,
				sheet_name);

		obj_read_excel_calculation_page.write_basic_cash_price_to_excel_without_maintenance_for_used_car(
				basic_cash_price_from_screen, duration, annual_mileage, used_residual_value,
				percentage_cap_residual_value, sheet_name);

		Click.on(driver, quote_summary, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.on(driver, customer_quote, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);		
		

		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
		System.out
				.println("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

		double monthly_finance_payment_expected_from_excel = GetExcelFormulaValue.get_formula_value(94, 1, sheet_name);

		LO.print("Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);
		System.out.println(
				"Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);

		double diff = Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen,
				monthly_finance_payment_expected_from_excel);
		boolean status = false;
		if (diff < 0.2) {
			status = true;
		}
		return status;
	}

	public boolean check_monthly_finance_payment_on_customer_quote_with_funder_quote_addition(WebDriver driver,
			String maintenance_status, String matrix_credit_type, String balloon_payment_status, String order_deposit,
			String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException,
			UnsupportedFlavorException, NumberFormatException, ClassNotFoundException {

		Thread.sleep(2000);

		Click.on(driver, customer_quote, 30);

		LO.print("***********Entered in Customer Quote page ***********");
		System.out.println("***********Entered in Customer Quote page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Actions act = new Actions(driver);

		Click.on(driver, matrix_credit_type_dropdown, 50);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(5000);
		try {
			List<WebElement> list = driver
					.findElements(By.xpath("//*[@class='ng-dropdown-panel-items scroll-host']/div/div/span"));

			Thread.sleep(3000);

			for (WebElement e : list) {

				if (e.getText().equalsIgnoreCase(matrix_credit_type)) {

					Click.on(driver, e, 20);
					Thread.sleep(3000);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		LO.print("Matrix credit type " + matrix_credit_type + " has been selected");
		System.out.println("Matrix credit type " + matrix_credit_type + " has been selected");

		ExplicitWait.visibleElement(driver, customer_quote_summary_terms, 20);

		String term = customer_quote_summary_terms.getText().trim().substring(0, 1);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement_for_funder_addition(term,
				document_fee, matrix_credit_type, sheet_name);

	

		Thread.sleep(3000);
		
		Click.on(driver, quote_summary, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.on(driver, customer_quote, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
		System.out
				.println("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

		double monthly_finance_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_finance_payment_from_excel_for_funder_addition(maintenance_status, matrix_credit_type,
						balloon_payment_status, order_deposit, finance_deposit, document_fee, sheet_name);

		LO.print("Expected Monthly Finance Rental from excel is " + monthly_finance_payment_expected_from_excel);
		System.out.println(
				"Expected Monthly Finance Rental from excel is " + monthly_finance_payment_expected_from_excel);

		double diff = Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen,
				monthly_finance_payment_expected_from_excel);
		boolean status = false;
		if (diff < 0.2) {
			status = true;
		}
		return status;
	}

	public boolean check_monthly_finance_payment_on_customer_quote_with_funder_quote_addition_with_miantenance(
			WebDriver driver, String maintenance_status, String matrix_credit_type, String balloon_payment_status,
			String order_deposit, String finance_deposit, String document_fee, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException, NumberFormatException,
			ClassNotFoundException {

		Thread.sleep(2000);

		Click.on(driver, customer_quote, 30);

		LO.print("***********Entered in Customer Quote page ***********");
		System.out.println("***********Entered in Customer Quote page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Actions act = new Actions(driver);

		Click.on(driver, matrix_credit_type_dropdown, 50);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(5000);
		try {
			List<WebElement> list = driver
					.findElements(By.xpath("//*[@class='ng-dropdown-panel-items scroll-host']/div/div/span"));

			Thread.sleep(3000);

			for (WebElement e : list) {

				if (e.getText().equalsIgnoreCase(matrix_credit_type)) {

					Click.on(driver, e, 20);
					Thread.sleep(3000);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		LO.print("Matrix credit type " + matrix_credit_type + " has been selected");
		System.out.println("Matrix credit type " + matrix_credit_type + " has been selected");

		Click.on(driver, customer_quote_maintenance_toggle_button, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, customer_quote_summary_terms, 20);

		String term = customer_quote_summary_terms.getText().trim().substring(0, 1);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement_for_funder_addition(term,
				document_fee, matrix_credit_type, sheet_name);



		Thread.sleep(5000);
		
		Click.on(driver, quote_summary, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.on(driver, customer_quote, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		
		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

		ExplicitWait.visibleElement(driver, customer_quote_monthly_maintenance_rental, 30);

		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		double monthly_maintenance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_maintenance_rental.getText().trim().substring(2)));

		LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
		System.out
				.println("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

		double monthly_finance_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_finance_payment_from_excel_for_funder_addition(maintenance_status, matrix_credit_type,
						balloon_payment_status, order_deposit, finance_deposit, document_fee, sheet_name);

		LO.print("Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);
		System.out.println(
				"Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);

		LO.print("Actual Monthly Maintenance Payment from screen is " + monthly_maintenance_payment_actual_from_screen);
		System.out.println(
				"Actual Monthly Maintenance Payment from screen is " + monthly_maintenance_payment_actual_from_screen);

		double monthly_Maintenance_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_maintenance_payment_from_excel_for_funder_addition(sheet_name);

		LO.print(
				"Expected Monthly Maintenance Rental from excel is " + monthly_Maintenance_payment_expected_from_excel);
		System.out.println(
				"Expected Monthly Maintenance Rental from excel is " + monthly_Maintenance_payment_expected_from_excel);

		double diff1 = Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen,
				monthly_finance_payment_expected_from_excel);

		double diff2 = Difference.of_two_Double_Values(monthly_maintenance_payment_actual_from_screen,
				monthly_Maintenance_payment_expected_from_excel);

		boolean status = false;
		if (diff1 < 0.2 && diff2 < 0.2) {
			status = true;
		}
		return status;
	}

	public boolean enter_sales_price_discount_greater_than_cost_price_discount(WebDriver driver, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {

		LO.print("");
		System.out.println("");

		LO.print("Started Verifying Sales price discount not to be greater than cost price discount");
		System.out.println("Started Verifying Sales price discount not to be greater than cost price discount");

		Actions act = new Actions(driver);

		// waiting for cost price elements
		ExplicitWait.visibleElement(driver, vehicle_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, vehicle_additional_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, paint_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, paint_additional_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, options_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, options_additional_discount_cost_price, 30);

		// waiting for sales price elements

		ExplicitWait.visibleElement(driver, vehicle_discount, 30);
		ExplicitWait.visibleElement(driver, paint_discount, 30);
		ExplicitWait.visibleElement(driver, options_discount, 30);
		ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
		ExplicitWait.visibleElement(driver, paint_additional_discount, 30);
		ExplicitWait.visibleElement(driver, options_additional_discount, 30);

		// getting text of cost price elements

		double vehicleDiscountCostPrice = Double
				.parseDouble(vehicle_discount_cost_price.getText().trim().substring(0, 4));
		double paintDiscountCostPrice = Double.parseDouble(paint_discount_cost_price.getText().trim().substring(0, 4));
		double optionsDiscountCostPrice = Double
				.parseDouble(options_discount_cost_price.getText().trim().substring(0, 4));

		double vehicleAdditionalDiscountCostPrice = Double
				.parseDouble(vehicle_additional_discount_cost_price.getText().trim().substring(2));
		double paintAdditionalDiscountCostPrice = Double
				.parseDouble(paint_additional_discount_cost_price.getText().trim().substring(2));
		double optionsAdditionalDiscountCostPrice = Double
				.parseDouble(options_additional_discount_cost_price.getText().trim().substring(2));

		// getting sales price elements

		// started entering sales discount values greater than cost price discount
		// values

		vehicle_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, vehicle_discount, vehicleDiscountCostPrice + 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		Thread.sleep(1000);

		paint_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, paint_discount, paintDiscountCostPrice + 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		Thread.sleep(1000);

		options_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, options_discount, optionsDiscountCostPrice + 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		Thread.sleep(1000);

		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, vehicle_additional_discount, vehicleAdditionalDiscountCostPrice + 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		Thread.sleep(1000);

		paint_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, paint_additional_discount, paintAdditionalDiscountCostPrice + 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		Thread.sleep(1000);

		options_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, options_additional_discount, optionsAdditionalDiscountCostPrice + 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		Thread.sleep(1000);

		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		// getting updated sales discount prices from input fields

		// getting vehicle Discount Sales Price
		vehicle_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double vehicleDiscountSalesPrice = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));
		Thread.sleep(1000);

		// getting paint Discount Sales Price
		paint_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double paintDiscountSalesPrice = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));
		Thread.sleep(1000);

		// getting options Discount Sales Price
		options_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double optionsDiscountSalesPrice = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));
		Thread.sleep(1000);

		// getting vehicle additional Discount Sales Price
		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double vehicleAdditionalDiscountSalesPrice = Double
				.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));
		Thread.sleep(1000);

		// getting paint additional Discount Sales Price
		paint_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double paintAdditionalDiscountSalesPrice = Double
				.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));
		Thread.sleep(1000);

		// getting otions additional Discount Sales Price
		options_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double optionsAdditionalDiscountSalesPrice = Double
				.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));
		Thread.sleep(1000);

		int count = 0;

		if (vehicleDiscountCostPrice == vehicleDiscountSalesPrice) {
			count++;
		}
		if (paintDiscountCostPrice == paintDiscountSalesPrice) {
			count++;
		}
		if (optionsDiscountCostPrice == optionsDiscountSalesPrice) {
			count++;
		}
		if (vehicleAdditionalDiscountCostPrice == vehicleAdditionalDiscountSalesPrice) {
			count++;
		}
		if (paintAdditionalDiscountCostPrice == paintAdditionalDiscountSalesPrice) {
			count++;
		}
		if (optionsAdditionalDiscountCostPrice == optionsAdditionalDiscountSalesPrice) {
			count++;
		}

		boolean status = false;
		if (count == 6) {

			status = true;
			LO.print(
					"Sales discount price entered greater than Cost discount prices and verified that sales discount input field doesn't accept greater values than cost discount prices");
			System.out.println(
					"Sales discount price entered greater than Cost discount prices and verified that sales discount input field doesn't accept greater values than cost discount prices");
		} else {
			LO.print(
					"Sales discount price entered greater than Cost discount prices and verified that sales discount input field does accept greater values than cost discount prices");
			System.err.println(
					"Sales discount price entered greater than Cost discount prices and verified that sales discount input field does accept greater values than cost discount prices");

		}

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page.write_sales_discount_values_to_excel(vehicleDiscountSalesPrice,
				paintDiscountSalesPrice, optionsDiscountSalesPrice, vehicleAdditionalDiscountSalesPrice,
				paintAdditionalDiscountSalesPrice, optionsAdditionalDiscountSalesPrice, sheet_name);

		return status;
	}

	public boolean update_sales_price_discount_and_verify_sales_price_vehicle_profit_monthly_rental_with_maintenance(
			WebDriver driver, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		LO.print("");
		System.out.println("");

		LO.print("Started Updating Sales price discount");
		System.out.println("Started Updating Sales price discount");

		Actions act = new Actions(driver);

		// waiting for cost price elements
		ExplicitWait.visibleElement(driver, vehicle_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, vehicle_additional_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, paint_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, paint_additional_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, options_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, options_additional_discount_cost_price, 30);

		// waiting for sales price elements

		ExplicitWait.visibleElement(driver, vehicle_discount, 30);
		ExplicitWait.visibleElement(driver, paint_discount, 30);
		ExplicitWait.visibleElement(driver, options_discount, 30);
		ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
		ExplicitWait.visibleElement(driver, paint_additional_discount, 30);
		ExplicitWait.visibleElement(driver, options_additional_discount, 30);

		// getting text of cost price elements

		double vehicleDiscountCostPrice = Double
				.parseDouble(vehicle_discount_cost_price.getText().trim().substring(0, 4));
		double paintDiscountCostPrice = Double.parseDouble(paint_discount_cost_price.getText().trim().substring(0, 4));
		double optionsDiscountCostPrice = Double
				.parseDouble(options_discount_cost_price.getText().trim().substring(0, 4));

		double vehicleAdditionalDiscountCostPrice = Double
				.parseDouble(vehicle_additional_discount_cost_price.getText().trim().substring(2));
		double paintAdditionalDiscountCostPrice = Double
				.parseDouble(paint_additional_discount_cost_price.getText().trim().substring(2));
		double optionsAdditionalDiscountCostPrice = Double
				.parseDouble(options_additional_discount_cost_price.getText().trim().substring(2));

		// getting sales price elements

		// started entering sales discount values greater than cost price discount
		// values

		vehicle_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, vehicle_discount, vehicleDiscountCostPrice - 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		Thread.sleep(1000);

		paint_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, paint_discount, paintDiscountCostPrice - 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		Thread.sleep(1000);

		options_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, options_discount, optionsDiscountCostPrice - 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		Thread.sleep(1000);

		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, vehicle_additional_discount, vehicleAdditionalDiscountCostPrice - 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		Thread.sleep(1000);

		paint_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, paint_additional_discount, paintAdditionalDiscountCostPrice - 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		Thread.sleep(1000);

		options_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, options_additional_discount, optionsAdditionalDiscountCostPrice - 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		Thread.sleep(1000);

		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		// getting updated sales discount prices from input fields

		// getting vehicle Discount Sales Price
		vehicle_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double vehicleDiscountSalesPrice = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));
		Thread.sleep(1000);

		// getting paint Discount Sales Price
		paint_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double paintDiscountSalesPrice = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));
		Thread.sleep(1000);

		// getting options Discount Sales Price
		options_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double optionsDiscountSalesPrice = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));
		Thread.sleep(1000);

		// getting vehicle additional Discount Sales Price
		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double vehicleAdditionalDiscountSalesPrice = Double
				.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));
		Thread.sleep(1000);

		// getting paint additional Discount Sales Price
		paint_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double paintAdditionalDiscountSalesPrice = Double
				.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));
		Thread.sleep(1000);

		// getting otions additional Discount Sales Price
		options_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double optionsAdditionalDiscountSalesPrice = Double
				.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

		// writing values to excel

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page.write_sales_discount_values_to_excel(vehicleDiscountSalesPrice,
				paintDiscountSalesPrice, optionsDiscountSalesPrice, vehicleAdditionalDiscountSalesPrice,
				paintAdditionalDiscountSalesPrice, optionsAdditionalDiscountSalesPrice, sheet_name);

		// waiting for elements

		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);
		ExplicitWait.visibleElement(driver, customer_quote_monthly_maintenance_rental, 30);
		ExplicitWait.visibleElement(driver, sales_total_input, 30);
		ExplicitWait.visibleElement(driver, vehicle_profit_input, 30);

		// getting sales price from screen

		sales_total_input.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double salesPriceActualFromSCreen = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

		LO.print("Actual Sales Total Price from screen (on updating sales discount prices) is "
				+ salesPriceActualFromSCreen);
		System.out.println("Actual Sales Total Price from screen (on updating sales discount prices) is "
				+ salesPriceActualFromSCreen);

		// getting sales price from excel

		double salesPriceExpectedFromExcel = GetExcelFormulaValue.get_formula_value(146, 7, sheet_name);

		LO.print("Expected Sales Total Price from excel (on updating sales discount prices) is "
				+ salesPriceExpectedFromExcel);
		System.out.println("Actual Sales Total Price from excel (on updating sales discount prices) is "
				+ salesPriceExpectedFromExcel);

		// getting sales price from screen

		vehicle_profit_input.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double vehicleProfitActualFromSCreen = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

		LO.print("Actual Vehicle Profit from screen (on updating sales discount prices) is "
				+ vehicleProfitActualFromSCreen);
		System.out.println("Actual Vehicle Profit from screen (on updating sales discount prices) is "
				+ vehicleProfitActualFromSCreen);

		// getting sales price from excel

		double vehicleProfitExpectedFromExcel = GetExcelFormulaValue.get_formula_value(147, 7, sheet_name);

		LO.print("Expected Vehicle Profit from excel (on updating sales discount prices) is "
				+ vehicleProfitExpectedFromExcel);
		System.out.println("Expected Vehicle Profit from excel (on updating sales discount prices) is "
				+ vehicleProfitExpectedFromExcel);

		// waiting till the api responds 200

//		try {
//			HttpURLConnection c = (HttpURLConnection) new URL(prop.getProperty("calculate_purchase_ownBook"))
//					.openConnection();
//			c.setRequestMethod("HEAD");
//			c.connect();
//			int r = c.getResponseCode();
//
//			int i = 0;
//			while (i <= 15 && r != 200 || r != 405) {
//				Thread.sleep(1000);
//				r = c.getResponseCode();
//				i++;
//			}
//		} catch (Exception e) {
//		}
		
		
		Click.on(driver, quote_summary, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.on(driver, customer_quote, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		// getting monthly finance payment actual from screen
		
		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
		System.out
				.println("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

		// getting monthly finance payment expected from screen

		double monthly_finance_payment_expected_from_excel = GetExcelFormulaValue.get_formula_value(94, 1, sheet_name);

		LO.print("Expected Monthly Finance Payment from excel is " + monthly_finance_payment_expected_from_excel);
		System.out.println(
				"Expected Monthly Finance Payment from excel is " + monthly_finance_payment_expected_from_excel);

		// getting monthly maintenance payment actual from screen
		double monthly_maintenance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_maintenance_rental.getText().trim().substring(2)));

		LO.print("Actual Monthly Maintenance Payment from screen is " + monthly_maintenance_payment_actual_from_screen);
		System.out.println(
				"Actual Monthly Maintenance Payment from screen is " + monthly_maintenance_payment_actual_from_screen);

		// getting monthly maintenance payment expected from screen
		double monthly_maintenance_payment_expected_from_excel = GetExcelFormulaValue.get_formula_value(93, 1,
				sheet_name);

		LO.print("Expected Monthly Maintenance Payment from excel is "
				+ monthly_maintenance_payment_expected_from_excel);
		System.out.println("Expected Monthly Maintenance Payment from excel is "
				+ monthly_maintenance_payment_expected_from_excel);

		int count = 0;
		if (Difference.of_two_Double_Values(salesPriceActualFromSCreen, salesPriceExpectedFromExcel) < 0.2) {
			count++;
		}
		if (Difference.of_two_Double_Values(vehicleProfitActualFromSCreen, vehicleProfitExpectedFromExcel) < 0.2) {
			count++;
		}
		if (Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen,
				monthly_finance_payment_expected_from_excel) < 0.2) {
			count++;
		}
		if (Difference.of_two_Double_Values(monthly_maintenance_payment_actual_from_screen,
				monthly_maintenance_payment_expected_from_excel) < 0.2) {
			count++;
		}
		boolean status = false;
		if (count == 4) {
			status = true;

			LO.print(
					"1. Sales Total Price , 2.Vehicel Profit , 3.Monthly Finance Rental , 4.Monthly Maintenance Rental , Verified on updating sales discount and found OK");
			System.out.println(
					"1. Sales Total Price , 2.Vehicel Profit , 3.Monthly Finance Rental , 4.Monthly Maintenance Rental , Verified on updating sales discount and found OK");
		} else {
			LO.print(
					"1. Sales Total Price , 2.Vehicel Profit , 3.Monthly Finance Rental , 4.Monthly Maintenance Rental , Verified on updating sales discount but found Wrong");
			System.err.println(
					"1. Sales Total Price , 2.Vehicel Profit , 3.Monthly Finance Rental , 4.Monthly Maintenance Rental , Verified on updating sales discount but found Wrong");

		}

		return status;
	}

	public boolean update_sales_price_discount_and_verify_sales_price_vehicle_profit_monthly_rental_without_maintenance(
			WebDriver driver, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		LO.print("");
		System.out.println("");

		LO.print("Started Updating Sales price discount");
		System.out.println("Started Updating Sales price discount");

		Actions act = new Actions(driver);

		// waiting for cost price elements
		ExplicitWait.visibleElement(driver, vehicle_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, vehicle_additional_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, paint_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, paint_additional_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, options_discount_cost_price, 30);
		ExplicitWait.visibleElement(driver, options_additional_discount_cost_price, 30);

		// waiting for sales price elements

		ExplicitWait.visibleElement(driver, vehicle_discount, 30);
		ExplicitWait.visibleElement(driver, paint_discount, 30);
		ExplicitWait.visibleElement(driver, options_discount, 30);
		ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
		ExplicitWait.visibleElement(driver, paint_additional_discount, 30);
		ExplicitWait.visibleElement(driver, options_additional_discount, 30);

		// getting text of cost price elements

		double vehicleDiscountCostPrice = Double
				.parseDouble(vehicle_discount_cost_price.getText().trim().substring(0, 4));
		double paintDiscountCostPrice = Double.parseDouble(paint_discount_cost_price.getText().trim().substring(0, 4));
		double optionsDiscountCostPrice = Double
				.parseDouble(options_discount_cost_price.getText().trim().substring(0, 4));

		double vehicleAdditionalDiscountCostPrice = Double
				.parseDouble(vehicle_additional_discount_cost_price.getText().trim().substring(2));
		double paintAdditionalDiscountCostPrice = Double
				.parseDouble(paint_additional_discount_cost_price.getText().trim().substring(2));
		double optionsAdditionalDiscountCostPrice = Double
				.parseDouble(options_additional_discount_cost_price.getText().trim().substring(2));

		// getting sales price elements

		// started entering sales discount values greater than cost price discount
		// values

		vehicle_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, vehicle_discount, vehicleDiscountCostPrice - 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		paint_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, paint_discount, paintDiscountCostPrice - 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		options_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, options_discount, optionsDiscountCostPrice - 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, vehicle_additional_discount, vehicleAdditionalDiscountCostPrice - 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		paint_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, paint_additional_discount, paintAdditionalDiscountCostPrice - 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		options_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, options_additional_discount, optionsAdditionalDiscountCostPrice - 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		// getting updated sales discount prices from input fields

		// getting vehicle Discount Sales Price
		vehicle_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double vehicleDiscountSalesPrice = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

		// getting paint Discount Sales Price
		paint_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double paintDiscountSalesPrice = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

		// getting options Discount Sales Price
		options_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double optionsDiscountSalesPrice = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

		// getting vehicle additional Discount Sales Price
		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double vehicleAdditionalDiscountSalesPrice = Double
				.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

		// getting paint additional Discount Sales Price
		paint_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double paintAdditionalDiscountSalesPrice = Double
				.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

		// getting otions additional Discount Sales Price
		options_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double optionsAdditionalDiscountSalesPrice = Double
				.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

		// writing values to excel

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page.write_sales_discount_values_to_excel(vehicleDiscountSalesPrice,
				paintDiscountSalesPrice, optionsDiscountSalesPrice, vehicleAdditionalDiscountSalesPrice,
				paintAdditionalDiscountSalesPrice, optionsAdditionalDiscountSalesPrice, sheet_name);

		// waiting for elements
		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);
		ExplicitWait.visibleElement(driver, sales_total_input, 30);
		ExplicitWait.visibleElement(driver, vehicle_profit_input, 30);

		// getting sales price from screen

		sales_total_input.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double salesPriceActualFromSCreen = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

		LO.print("Actual Sales Total Price from screen (on updating sales discount prices) is "
				+ salesPriceActualFromSCreen);
		System.out.println("Actual Sales Total Price from screen (on updating sales discount prices) is "
				+ salesPriceActualFromSCreen);

		// getting sales price from excel

		double salesPriceExpectedFromExcel = GetExcelFormulaValue.get_formula_value(146, 7, sheet_name);

		LO.print("Expected Sales Total Price from excel (on updating sales discount prices) is "
				+ salesPriceExpectedFromExcel);
		System.out.println("Actual Sales Total Price from excel (on updating sales discount prices) is "
				+ salesPriceExpectedFromExcel);

		// getting sales price from screen

		vehicle_profit_input.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		double vehicleProfitActualFromSCreen = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

		LO.print("Actual Vehicle Profit from screen (on updating sales discount prices) is "
				+ vehicleProfitActualFromSCreen);
		System.out.println("Actual Vehicle Profit from screen (on updating sales discount prices) is "
				+ vehicleProfitActualFromSCreen);

		// getting sales price from excel

		double vehicleProfitExpectedFromExcel = GetExcelFormulaValue.get_formula_value(147, 7, sheet_name);

		LO.print("Expected Vehicle Profit from excel (on updating sales discount prices) is "
				+ vehicleProfitExpectedFromExcel);
		System.out.println("Expected Vehicle Profit from excel (on updating sales discount prices) is "
				+ vehicleProfitExpectedFromExcel);

//		//saving quote
//		CustomerQuotePage_HPNR_HPRPage obj_cust_quote_class = new CustomerQuotePage_HPNR_HPRPage();		
//        obj_cust_quote_class.save_changes_and_back_to_customer_quote_page();

//		try {
//			HttpURLConnection c = (HttpURLConnection) new URL(prop.getProperty("calculate_purchase_ownBook"))
//					.openConnection();
//			c.setRequestMethod("HEAD");
//			c.connect();
//			int r = c.getResponseCode();
//
//			int i = 0;
//			while (i <= 15 && r != 200 || r != 405) {
//				Thread.sleep(1000);
//				r = c.getResponseCode();
//				i++;
//			}
//		} catch (Exception e) {
//		}
		
		Click.on(driver, quote_summary, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.on(driver, customer_quote, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		// getting monthly finance payment actual from screen
		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
		System.out
				.println("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

		// getting monthly finance payment expected from screen

		double monthly_finance_payment_expected_from_excel = GetExcelFormulaValue.get_formula_value(94, 1, sheet_name);

		LO.print("Expected Monthly Finance Payment from excel is " + monthly_finance_payment_expected_from_excel);
		System.out.println(
				"Expected Monthly Finance Payment from excel is " + monthly_finance_payment_expected_from_excel);

		int count = 0;
		if (Difference.of_two_Double_Values(salesPriceActualFromSCreen, salesPriceExpectedFromExcel) < 0.2) {
			count++;
		}
		if (Difference.of_two_Double_Values(vehicleProfitActualFromSCreen, vehicleProfitExpectedFromExcel) < 0.2) {
			count++;
		}
		if (Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen,
				monthly_finance_payment_expected_from_excel) < 0.2) {
			count++;
		}

		boolean status = false;
		if (count == 3) {
			status = true;

			LO.print(
					"1. Sales Total Price , 2.Vehicel Profit , 3.Monthly Finance Rental , Verified on updating sales discount and found OK");
			System.out.println(
					"1. Sales Total Price , 2.Vehicel Profit , 3.Monthly Finance Rental ,  Verified on updating sales discount and found OK");
		} else {
			LO.print(
					"1. Sales Total Price , 2.Vehicel Profit , 3.Monthly Finance Rental , Verified on updating sales discount but found Wrong");
			System.err.println(
					"1. Sales Total Price , 2.Vehicel Profit , 3.Monthly Finance Rental , Verified on updating sales discount but found Wrong");

		}

		return status;
	}

	public boolean check_monthly_payment_on_customer_quote_with_maintenance(WebDriver driver, String maintenance_status,
			String matrix_credit_type, String balloon_payment_status, String order_deposit, String finance_deposit,
			String document_fee, String sheet_name) throws InterruptedException, IOException,
			UnsupportedFlavorException, NumberFormatException, ClassNotFoundException {

		Thread.sleep(2000);

		Click.on(driver, customer_quote, 30);

		LO.print("***********Entered in Customer Quote page ***********");
		System.out.println("***********Entered in Customer Quote page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Actions act = new Actions(driver);

		Click.on(driver, matrix_credit_type_dropdown, 50);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(5000);
		try {
			List<WebElement> list = driver
					.findElements(By.xpath("//*[@class='ng-dropdown-panel-items scroll-host']/div/div/span"));

			for (WebElement e : list) {

				if (e.getText().equalsIgnoreCase(matrix_credit_type)) {
					Click.on(driver, e, 20);

					ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		LO.print("Matrix credit type " + matrix_credit_type + " has been selected");
		System.out.println("Matrix credit type " + matrix_credit_type + " has been selected");

		Thread.sleep(5000);

		Click.on(driver, customer_quote_maintenance_toggle_button, 40);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, customer_quote_summary_terms, 20);

		String term = customer_quote_summary_terms.getText().trim().substring(0, 1);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement(term, matrix_credit_type,
				sheet_name);

		ExplicitWait.visibleElement(driver, vehicle_discount, 30);

		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		vehicle_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		String vehicle_discount_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		paint_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		String paint_discount_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		options_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		String options_discount_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		String vehicle_additional_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		paint_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		String paint_additional_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		options_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		String options_additional_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		

		Thread.sleep(3000);

//		try {
//			HttpURLConnection c = (HttpURLConnection) new URL(prop.getProperty("calculate_purchase_ownBook"))
//					.openConnection();
//			c.setRequestMethod("HEAD");
//			c.connect();
//			int r = c.getResponseCode();
//
//			int i = 0;
//			while (i <= 15 && r != 200 || r != 405) {
//				Thread.sleep(1000);
//				r = c.getResponseCode();
//				i++;
//			}
//		} catch (Exception e) {
//		}
		
		Click.on(driver, quote_summary, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.on(driver, customer_quote, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Thread.sleep(5000);
		
		ExplicitWait.visibleElement(driver, customer_quote_monthly_total_rental, 30);

		double monthly_total_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_total_rental.getText().trim().substring(2)));

		LO.print("Actual Monthly total Payment from screen is " + monthly_total_payment_actual_from_screen);
		System.out.println("Actual Monthly total Payment from screen is " + monthly_total_payment_actual_from_screen);

		double monthly_total_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_total_payment_from_excel(maintenance_status, matrix_credit_type, balloon_payment_status,
						order_deposit, finance_deposit, document_fee, vehicle_discount_copied, paint_discount_copied,
						options_discount_copied, vehicle_additional_copied, paint_additional_copied,
						options_additional_copied, sheet_name);

		LO.print("Expected Monthly Total Rental from excel is " + monthly_total_payment_expected_from_excel);
		System.out.println("Expected Monthly Total Rental from excel is " + monthly_total_payment_expected_from_excel);

		double diff = Difference.of_two_Double_Values(monthly_total_payment_actual_from_screen,
				monthly_total_payment_expected_from_excel);
		boolean status = false;
		if (diff < 0.2) {
			status = true;
		}
		return status;
	}

	public boolean check_monthly_payment_on_customer_quote_with_maintenance_for_used_vehicle(WebDriver driver,
			String maintenance_status, String matrix_credit_type, String balloon_payment_status, String order_deposit,
			String finance_deposit, String document_fee, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException, ClassNotFoundException {

		Thread.sleep(2000);

		Click.on(driver, holding_cost_summary, 30);

		ExplicitWait.visibleElement(driver, total_cap_maintenance_value, 30);

		double totalCapMaintenanceValue = Double
				.parseDouble(RemoveComma.of(total_cap_maintenance_value.getText().trim().substring(2)));

		Click.on(driver, customer_quote, 30);

		LO.print("***********Entered in Customer Quote page ***********");
		System.out.println("***********Entered in Customer Quote page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.on(driver, matrix_credit_type_dropdown, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(3000);

		try {
			List<WebElement> list = driver.findElements(By.xpath(
					"//*[normalize-space()='Matrix Credit type']//ancestor::div[1]//div//ng-select//ng-dropdown-panel//div//div//span"));

			for (WebElement e : list) {

				if (e.getText().equalsIgnoreCase(matrix_credit_type)) {
					Click.on(driver, e, 20);

					ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		LO.print("Matrix credit type " + matrix_credit_type + " has been selected");
		System.out.println("Matrix credit type " + matrix_credit_type + " has been selected");

		Thread.sleep(5000);
		try {
			Click.on(driver, customer_quote_maintenance_toggle_button, 40);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			Click.on(driver, customer_quote_summary, 40);

			ExplicitWait.visibleElement(driver, customer_quote_summary_basic_cash_price, 20);

			Thread.sleep(7000);

			double basic_cash_price_from_screen = Double
					.parseDouble(RemoveComma.of(customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

			Click.on(driver, holding_cost, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			Click.on(driver, holding_cost_summary, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			ExplicitWait.visibleElement(driver, holding_cost_summary_terms, 30);

			double duration = Double.parseDouble(holding_cost_summary_terms.getText().substring(0, 2));

			ExplicitWait.visibleElement(driver, holding_cost_summary_mileage, 30);

			double annual_mileage = Double.parseDouble(RemoveComma.of(holding_cost_summary_mileage.getText()));

			ExplicitWait.visibleElement(driver, residual_value_used, 30);
			ExplicitWait.visibleElement(driver, maintenance_cost_used, 30);

			residual_value_used.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			double used_residual_value = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

			maintenance_cost_used.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
			double total_cap_maintenance_value_converted = Double
					.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

			ExplicitWait.visibleElement(driver, holding_cost_percentage_cap_residual_value_used, 30);

			ExplicitWait.visibleElement(driver, holding_cost_percentage_maintenance_cost_used, 30);

			holding_cost_percentage_cap_residual_value_used.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
			double percentage_cap_residual_value = Double
					.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

			holding_cost_percentage_maintenance_cost_used.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
			double percentage_maintenance_cost = Double
					.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

			Click.on(driver, quote_summary, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
			
			Click.on(driver, customer_quote, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			ExplicitWait.visibleElement(driver, customer_quote_summary_terms, 20);

			String term = customer_quote_summary_terms.getText().trim().substring(0, 2);

			obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

			obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement(term,
					matrix_credit_type, sheet_name);

			obj_read_excel_calculation_page.write_basic_cash_price_to_excel_with_maintenance_for_used_car(
					basic_cash_price_from_screen, duration, annual_mileage, used_residual_value,
					total_cap_maintenance_value_converted, percentage_cap_residual_value, percentage_maintenance_cost,
					sheet_name);

			Thread.sleep(5000);

			double monthly_total_payment_actual_from_screen = Double
					.parseDouble(RemoveComma.of(customer_quote_monthly_total_rental.getText().trim().substring(2)));

			LO.print("Actual Monthly total Payment from screen is " + monthly_total_payment_actual_from_screen);
			System.out
					.println("Actual Monthly total Payment from screen is " + monthly_total_payment_actual_from_screen);

			double monthly_total_payment_expected_from_excel = GetExcelFormulaValue.get_formula_value(95, 1,
					sheet_name);

			LO.print("Expected Monthly Total Rental from excel is " + monthly_total_payment_expected_from_excel);
			System.out.println(
					"Expected Monthly Total Rental from excel is " + monthly_total_payment_expected_from_excel);

			double diff = Difference.of_two_Double_Values(monthly_total_payment_actual_from_screen,
					monthly_total_payment_expected_from_excel);
			boolean status = false;
			if (diff < 0.2) {
				status = true;
			}
			return status;
		} catch (Exception e) {

			Click.on(driver, customer_quote_summary, 40);

			ExplicitWait.visibleElement(driver, customer_quote_summary_basic_cash_price, 20);

			Thread.sleep(5000);

			double basic_cash_price_from_screen = Double
					.parseDouble(RemoveComma.of(customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

			Click.on(driver, holding_cost, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			Click.on(driver, holding_cost_summary, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			ExplicitWait.visibleElement(driver, holding_cost_summary_terms, 30);

			double duration = Double.parseDouble(holding_cost_summary_terms.getText().substring(0, 2));

			ExplicitWait.visibleElement(driver, holding_cost_summary_mileage, 30);

			double annual_mileage = Double.parseDouble(RemoveComma.of(holding_cost_summary_mileage.getText()));

			ExplicitWait.visibleElement(driver, residual_value_used, 30);

			residual_value_used.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			double used_residual_value = Double.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

			ExplicitWait.visibleElement(driver, holding_cost_percentage_cap_residual_value_used, 30);

			holding_cost_percentage_cap_residual_value_used.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
			double percentage_cap_residual_value = Double
					.parseDouble((String) clipboard.getData(DataFlavor.stringFlavor));

			ExplicitWait.visibleElement(driver, customer_quote_summary_terms, 20);

			String term = customer_quote_summary_terms.getText().trim().substring(0, 2);

			obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

			obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement(term,
					matrix_credit_type, sheet_name);

			obj_read_excel_calculation_page.write_basic_cash_price_to_excel_without_maintenance_for_used_car(
					basic_cash_price_from_screen, duration, annual_mileage, used_residual_value,
					percentage_cap_residual_value, sheet_name);

			Click.on(driver, customer_quote, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			double monthly_finance_payment_actual_from_screen = Double
					.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

			LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
			System.out.println(
					"Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

			double monthly_finance_payment_expected_from_excel = GetExcelFormulaValue.get_formula_value(94, 1,
					sheet_name);

			LO.print("Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);
			System.out.println(
					"Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);

			double diff = Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen,
					monthly_finance_payment_expected_from_excel);
			boolean status = false;
			if (diff < 0.2) {
				status = true;
			}
			return status;
		}
	}

	public boolean edit_vehicle_profit_and_check_monthly_finance_payment(String vehicle_profit, String sheet_name)
			throws InterruptedException, UnsupportedFlavorException, IOException {

		//enter vehicle profit 
		ExplicitWait.visibleElement(driver, vehicle_profit_input, 30);	
		Thread.sleep(1000);
		vehicle_profit_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));		
		Click.sendKeys(driver, vehicle_profit_input, vehicle_profit, 30);
		
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
		Thread.sleep(1000);
		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String vehicle_additional_discount_copied = (String) clipboard.getData(DataFlavor.stringFlavor);
		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		
		//verify monthly finance payment 
		double monthly_finance_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_finance_payment_after_editing_vehicle_profit(vehicle_additional_discount_copied,
						sheet_name);

		Click.on(driver, quote_summary, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.on(driver, customer_quote, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Thread.sleep(4000);
		
		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));
		double diff = Difference.of_two_Double_Values(monthly_finance_payment_expected_from_excel,
				monthly_finance_payment_actual_from_screen);
		
		LO.print("");
		System.out.println("");
		
		LO.print("Actual Monthly Finance Payment after editing vehicle profit = "+monthly_finance_payment_actual_from_screen);
		System.out.println("Actual Monthly Finance Payment after editing vehicle profit = "+monthly_finance_payment_actual_from_screen);
		
		LO.print("Expected  Monthly Finance Payment after editing vehicle profit = "+monthly_finance_payment_expected_from_excel);
		System.out.println("Expected Monthly Finance Payment after editing vehicle profit = "+monthly_finance_payment_expected_from_excel);
		
		LO.print("");
		System.out.println("");
		
		boolean status = false;
		if (diff < 0.2) {
			status = true;

			LO.print("Monthly Finance Payment verified after editing vehicle profit");
			System.out.println("Monthly Finance Payment verified after editing vehicle profit");
		}
		
		else {
			
			LO.print("Monthly Finance Payment verified after editing vehicle profit but found wrong");
			System.err.println("Monthly Finance Payment verified after editing vehicle profit but found wrong");
		}

		return status;
	}

	public boolean edit_vehicle_profit_and_check_monthly_total_payment_with_maintenance(String vehicle_profit,
			String sheet_name) throws InterruptedException, UnsupportedFlavorException, IOException {

		ExplicitWait.visibleElement(driver, vehicle_profit_input, 30);
		vehicle_profit_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeys(driver, vehicle_profit_input, vehicle_profit, 30);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
		Thread.sleep(1000);

		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String vehicle_additional_discount_copied = (String) clipboard.getData(DataFlavor.stringFlavor);
		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		double monthly_total_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_total_payment_after_editing_vehicle_profit(vehicle_additional_discount_copied, sheet_name);
		

		// waiting till url responds 200
//		try {
//			HttpURLConnection c = (HttpURLConnection) new URL(prop.getProperty("calculate_purchase_ownBook"))
//					.openConnection();
//			c.setRequestMethod("HEAD");
//			c.connect();
//			int r = c.getResponseCode();
//
//			int i = 0;
//			while (i <= 15 && r != 200 || r != 405) {
//				Thread.sleep(1000);
//				r = c.getResponseCode();
//				i++;
//			}
//		} catch (Exception e) {
//		}

		
		Click.on(driver, quote_summary, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.on(driver, customer_quote, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		
		ExplicitWait.visibleElement(driver, customer_quote_monthly_total_rental, 30);
		
		
		// taking value from screen
		double monthly_total_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_total_rental.getText().trim().substring(2)));
		double diff = Difference.of_two_Double_Values(monthly_total_payment_expected_from_excel,
				monthly_total_payment_actual_from_screen);
		boolean status = false;
		if (diff < 0.2) {
			status = true;

			LO.print("Monthly Total Payment verified after editing vehicle profit");
			System.out.println("Monthly Total Payment verified after editing vehicle profit");
		}

		return status;
	}

	public boolean put_part_exchange_values_and_check_monthly_finance_payment(String part_exchange_actual_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String finance_deposit_from_excel, String sheet_name)
			throws UnsupportedFlavorException, IOException, InterruptedException {
		Actions act = new Actions(driver);

		Click.on(driver, part_exchange_and_additional_payment_button, 30);
		Thread.sleep(1000);

		LO.print("Clicked on Part Exchange panel");
		System.out.println("Clicked on Part Exchange panel");

		Click.on(driver, given_part_exchange_value, 20);

		given_part_exchange_value.clear();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(2000);
		Click.sendKeys(driver, given_part_exchange_value, given_part_exchange_value_from_excel, 30);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("arguments[0].click();", check_box_outstanding_finance, 20);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

	

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(2000);
		Click.sendKeys(driver, funder_name, "Funder X", 20);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(2000);
		Click.sendKeys(driver, agreement_number, "123", 20);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(2000);
		ExplicitWait.visibleElement(driver, less_finance_settlement, 20);
		less_finance_settlement.clear();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(2000);
		Click.sendKeys(driver, less_finance_settlement, less_finance_settlement_from_excel, 20);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(2000);
		ExplicitWait.visibleElement(driver, order_Deposit, 20);
		order_Deposit.clear();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(2000);
		Click.sendKeys(driver, order_Deposit, order_deposit_from_excel, 30);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(2000);
		ExplicitWait.visibleElement(driver, finance_Deposit, 20);
		finance_Deposit.clear();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(2000);
		Click.sendKeys(driver, finance_Deposit, finance_deposit_from_excel, 30);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, document_fee, 30);
		Thread.sleep(2000);
		document_fee.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String document_fee_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		act.sendKeys(Keys.TAB).perform();

//		//calling save method
//		CustomerQuotePage_HPNR_HPRPage obj_cust_quote_class = new CustomerQuotePage_HPNR_HPRPage();		
//		obj_cust_quote_class.save_changes_and_back_to_customer_quote_page();

//		try {
//			HttpURLConnection c = (HttpURLConnection) new URL(prop.getProperty("calculate_purchase_ownBook"))
//					.openConnection();
//			c.setRequestMethod("HEAD");
//			c.connect();
//			int r = c.getResponseCode();
//
//			int i = 0;
//			while (i <= 15 && r != 200 || r != 405) {
//				Thread.sleep(1000);
//				r = c.getResponseCode();
//				i++;
//			}
//		} catch (Exception e) {
//		}
		
		Click.on(driver, quote_summary, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		Click.on(driver, customer_quote, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, balance_to_finance_value, 30);
		
		Thread.sleep(4000);

		double balance_to_finance_value_from_screen = Double
				.parseDouble(RemoveComma.of(balance_to_finance_value.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));
		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		double[] monthlyFinanceAndBalanceToFinance = obj_read_excel_calculation_page
				.get_monthly_finance_payment_and_balance_to_finance_payment_after_editing_part_exchange_values(
						part_exchange_actual_from_excel, given_part_exchange_value_from_excel,
						less_finance_settlement_from_excel, order_deposit_from_excel, finance_deposit_from_excel,
						document_fee_copied, sheet_name);

		double monthly_finance_payment_expected = monthlyFinanceAndBalanceToFinance[0];
		double balance_to_finance_expected = monthlyFinanceAndBalanceToFinance[1];

		LO.print("");
		System.out.println("");

		LO.print("Balance To Finance Expected is " + balance_to_finance_expected);
		System.out.println("Balance To Finance Expected is " + balance_to_finance_expected);

		LO.print("Balance To Finance Actual is " + balance_to_finance_value_from_screen);
		System.out.println("Balance To Finance Actual is " + balance_to_finance_value_from_screen);

		LO.print("");
		System.out.println("");

		LO.print("Monthly Finance Payment Expected is " + monthly_finance_payment_expected);
		System.out.println("Monthly Finance Payment Expected is " + monthly_finance_payment_expected);

		LO.print("Monthly Finance Payment Actual is " + monthly_finance_payment_actual_from_screen);
		System.out.println("Monthly Finance Payment Actual is " + monthly_finance_payment_actual_from_screen);

		LO.print("");
		System.out.println("");

		double diff1 = Difference.of_two_Double_Values(balance_to_finance_value_from_screen,
				balance_to_finance_expected);
		double diff2 = Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen,
				monthly_finance_payment_expected);

		boolean status = false;
		if (diff1 < 0.2 && diff2 < 0.2) {
			status = true;
			LO.print("Monthly Finance Payment verified after editing part exchange values and deposit values");
			System.out
					.println("Monthly Finance Payment verified after editing part exchange values and deposit values");
		}

		return status;

	}

	public boolean put_part_exchange_values_and_check_monthly_total_payment_with_maintenance(
			String part_exchange_actual_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel,
			String finance_deposit_from_excel, String sheet_name)
			throws UnsupportedFlavorException, IOException, InterruptedException {
		ExplicitWait.clickableElement(driver, part_exchange_and_additional_payment_button, 50);
		Thread.sleep(4000);
		Click.on(driver, part_exchange_and_additional_payment_button, 70);
		LO.print("Clicked on Part Exchange panel");
		System.out.println("Clicked on Part Exchange panel");
		Actions act = new Actions(driver);

		Click.on(driver, given_part_exchange_value, 20);

		given_part_exchange_value.clear();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.sendKeys(driver, given_part_exchange_value, given_part_exchange_value_from_excel, 30);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("arguments[0].click();", check_box_outstanding_finance, 20);

	

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

		ExplicitWait.visibleElement(driver, finance_Deposit, 20);
		finance_Deposit.clear();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.sendKeys(driver, finance_Deposit, finance_deposit_from_excel, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, document_fee, 30);

		document_fee.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String document_fee_copied = (String) clipboard.getData(DataFlavor.stringFlavor);
		act.sendKeys(Keys.TAB).perform();

		// driver.findElement(By.xpath("//*[@id='collapseThree']/div/div")).click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

//		// waiting till url responds 200
//		try {
//			HttpURLConnection c = (HttpURLConnection) new URL(prop.getProperty("calculate_purchase_ownBook"))
//					.openConnection();
//			c.setRequestMethod("HEAD");
//			c.connect();
//			int r = c.getResponseCode();
//
//			int i = 0;
//			while (i <= 15 && r != 200 || r != 405) {
//				Thread.sleep(1000);
//				r = c.getResponseCode();
//				i++;
//			}
//		} catch (Exception e) {
//		}

		try {
			
			Click.on(driver, quote_summary, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
			
			Click.on(driver, customer_quote, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
			

			ExplicitWait.visibleElement(driver, balance_to_finance_value, 30);

			double balance_to_finance_value_from_screen = Double
					.parseDouble(RemoveComma.of(balance_to_finance_value.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, customer_quote_monthly_total_rental, 30);

			double monthly_total_payment_actual_from_screen = Double
					.parseDouble(RemoveComma.of(customer_quote_monthly_total_rental.getText().trim().substring(2)));
			obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

			double[] monthlyFinanceAndBalanceToFinance = obj_read_excel_calculation_page
					.get_monthly_total_payment_and_balance_to_finance_payment_after_editing_part_exchange_values(
							part_exchange_actual_value_from_excel, given_part_exchange_value_from_excel,
							less_finance_settlement_from_excel, order_deposit_from_excel, finance_deposit_from_excel,
							document_fee_copied, sheet_name);

			double monthly_total_payment_expected = monthlyFinanceAndBalanceToFinance[0];
			double balance_to_finance_expected = monthlyFinanceAndBalanceToFinance[1];

			LO.print("");
			System.out.println("");

			LO.print("Actual Balance to finance after editing part exchange values and deposit values is = "
					+ balance_to_finance_value_from_screen);
			System.out.println("Actual Balance to finance after editing part exchange values and deposit values is = "
					+ balance_to_finance_value_from_screen);

			LO.print("Expected Balance to finance after editing part exchange values and deposit values is = "
					+ balance_to_finance_expected);
			System.out.println("Expected Balance to finance after editing part exchange values and deposit values is = "
					+ balance_to_finance_expected);

			LO.print("");
			System.out.println("");

			LO.print("Actual Monthly total Payment after editing part exchange values and deposit values is = "
					+ monthly_total_payment_actual_from_screen);
			System.out
					.println("Actual Monthly total Payment after editing part exchange values and deposit values is = "
							+ monthly_total_payment_actual_from_screen);

			LO.print("Expected Monthly total Payment after editing part exchange values and deposit values is = "
					+ monthly_total_payment_expected);
			System.out.println(
					"Expected Monthly total Payment after editing part exchange values and deposit values is = "
							+ monthly_total_payment_expected);

			LO.print("");
			System.out.println("");

			double diff1 = Difference.of_two_Double_Values(balance_to_finance_value_from_screen,
					balance_to_finance_expected);
			double diff2 = Difference.of_two_Double_Values(monthly_total_payment_actual_from_screen,
					monthly_total_payment_expected);

			boolean status = false;
			if (diff1 < 0.2 && diff2 < 0.2) {
				status = true;
				LO.print("Monthly total Payment verified after editing part exchange values and deposit values");
				System.out.println(
						"Monthly total Payment verified after editing part exchange values and deposit values");
			} else {
				LO.print("Monthly total Payment found wrong after editing part exchange values and deposit values");
				System.out.println(
						"Monthly total Payment found wrong after editing part exchange values and deposit values");

			}

			return status;

		} catch (Exception e) {
			
			
			Click.on(driver, quote_summary, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
			
			Click.on(driver, customer_quote, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
			
			ExplicitWait.visibleElement(driver, balance_to_finance_value, 30);

			double balance_to_finance_value_from_screen = Double
					.parseDouble(RemoveComma.of(balance_to_finance_value.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

			double monthly_finance_payment_actual_from_screen = Double
					.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

			obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

			double[] monthlyFinanceAndBalanceToFinance = obj_read_excel_calculation_page
					.get_monthly_finance_payment_and_balance_to_finance_payment_after_editing_part_exchange_values(
							part_exchange_actual_value_from_excel, given_part_exchange_value_from_excel,
							less_finance_settlement_from_excel, order_deposit_from_excel, finance_deposit_from_excel,
							document_fee_copied, sheet_name);

			double monthly_finance_payment_expected = monthlyFinanceAndBalanceToFinance[0];
			double balance_to_finance_expected = monthlyFinanceAndBalanceToFinance[1];

			LO.print("");
			System.out.println("");

			LO.print("Balance To Finance Expected is " + balance_to_finance_expected);
			System.out.println("Balance To Finance Expected is " + balance_to_finance_expected);

			LO.print("Balance To Finance Actual is " + balance_to_finance_value_from_screen);
			System.out.println("Balance To Finance Actual is " + balance_to_finance_value_from_screen);

			LO.print("");
			System.out.println("");

			LO.print("Monthly Finance Payment Expected is " + monthly_finance_payment_expected);
			System.out.println("Monthly Finance Payment Expected is " + monthly_finance_payment_expected);

			LO.print("Monthly Finance Payment Actual is " + monthly_finance_payment_actual_from_screen);
			System.out.println("Monthly Finance Payment Actual is " + monthly_finance_payment_actual_from_screen);

			LO.print("");
			System.out.println("");

			double diff1 = Difference.of_two_Double_Values(balance_to_finance_value_from_screen,
					balance_to_finance_expected);
			double diff2 = Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen,
					monthly_finance_payment_expected);

			boolean status = false;
			if (diff1 < 0.2 && diff2 < 0.2) {
				status = true;
				LO.print("Monthly Finance Payment verified after editing part exchange values and deposit values");
				System.out.println(
						"Monthly Finance Payment verified after editing part exchange values and deposit values");
			}

			return status;
		}

	}

	public boolean check_monthly_total_payment_after_making_balloon_payment_off_with_maintenance(String sheet_name)
			throws InterruptedException, IOException {
		Click.on(driver, balloon_payment_toggle, 40);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		try {

			ExplicitWait.visibleElement(driver, customer_quote_monthly_total_rental, 40);

			Thread.sleep(4000);

			double monthly_total_payment_actual_from_screen = Double
					.parseDouble(RemoveComma.of(customer_quote_monthly_total_rental.getText().trim().substring(2)));

			obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

			double monthly_total_payment_expected_from_excel = obj_read_excel_calculation_page
					.get_monthly_total_payment_after_making_balloon_payment_off(sheet_name);

			LO.print("");
			System.out.println("");

			LO.print("Monthly Total Payment Expected (with balloon payment toggle button off) is "
					+ monthly_total_payment_expected_from_excel);
			System.out.println("Monthly Total Payment Expected (with balloon payment toggle button off) is "
					+ monthly_total_payment_expected_from_excel);

			LO.print("Monthly Total Payment Actual (with balloon payment toggle button off) is "
					+ monthly_total_payment_actual_from_screen);
			System.out.println("Monthly Total Payment Actual (with balloon payment toggle button off) is "
					+ monthly_total_payment_actual_from_screen);

			LO.print("");
			System.out.println("");

			double diff = Difference.of_two_Double_Values(monthly_total_payment_actual_from_screen,
					monthly_total_payment_expected_from_excel);

			boolean status = false;
			if (diff < 0.2) {
				status = true;

				LO.print("Monthly Total Payment verified after making balloon payment toggle button off");
				System.out.println("Monthly Total Payment verified after making balloon payment toggle button off");
			}

			Click.on(driver, balloon_payment_toggle, 40);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			return status;
		} catch (Exception e) {

			Thread.sleep(4000);

			double monthly_finance_payment_actual_from_screen = Double
					.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

			obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

			double monthly_finance_payment_expected_from_excel = obj_read_excel_calculation_page
					.get_monthly_finance_payment_after_making_balloon_payment_off(sheet_name);

			LO.print("");
			System.out.println("");

			LO.print("Monthly Finance Payment Expected (with balloon payment toggle button off) is "
					+ monthly_finance_payment_expected_from_excel);
			System.out.println("Monthly Finance Payment Expected (with balloon payment toggle button off) is "
					+ monthly_finance_payment_expected_from_excel);

			LO.print("Monthly Finance Payment Actual (with balloon payment toggle button off) is "
					+ monthly_finance_payment_actual_from_screen);
			System.out.println("Monthly Finance Payment Actual (with balloon payment toggle button off) is "
					+ monthly_finance_payment_actual_from_screen);

			LO.print("");
			System.out.println("");

			double diff = Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen,
					monthly_finance_payment_expected_from_excel);

			boolean status = false;
			if (diff < 0.2) {
				status = true;

				LO.print("Monthly Finance Payment verified after making balloon payment toggle button off");
				System.out.println("Monthly Finance Payment verified after making balloon payment toggle button off");
			}

			Click.on(driver, balloon_payment_toggle, 40);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			return status;

		}
	}

	public boolean check_monthly_finance_payment_after_making_balloon_payment_off(String sheet_name)
			throws InterruptedException, IOException {
		Click.on(driver, balloon_payment_toggle, 40);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

//		try {
//			HttpURLConnection c = (HttpURLConnection) new URL(prop.getProperty("calculate_purchase_ownBook"))
//					.openConnection();
//			c.setRequestMethod("HEAD");
//			c.connect();
//			int r = c.getResponseCode();
//
//			int i = 0;
//			while (i <= 15 && r != 200 || r != 405) {
//				Thread.sleep(1000);
//				r = c.getResponseCode();
//				i++;
//			}
//		} catch (Exception e) {
//		}

		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		double monthly_finance_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_finance_payment_after_making_balloon_payment_off(sheet_name);

		LO.print("");
		System.out.println("");

		LO.print("Monthly Finance Payment Expected (with balloon payment toggle button off) is "
				+ monthly_finance_payment_expected_from_excel);
		System.out.println("Monthly Finance Payment Expected (with balloon payment toggle button off) is "
				+ monthly_finance_payment_expected_from_excel);

		LO.print("Monthly Finance Payment Actual (with balloon payment toggle button off) is "
				+ monthly_finance_payment_actual_from_screen);
		System.out.println("Monthly Finance Payment Actual (with balloon payment toggle button off) is "
				+ monthly_finance_payment_actual_from_screen);

		LO.print("");
		System.out.println("");

		double diff = Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen,
				monthly_finance_payment_expected_from_excel);

		boolean status = false;
		if (diff < 0.2) {
			status = true;

			LO.print("Monthly Finance Payment verified after making balloon payment toggle button off");
			System.out.println("Monthly Finance Payment verified after making balloon payment toggle button off");
		}

		Click.on(driver, balloon_payment_toggle, 40);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		return status;
	}

}
