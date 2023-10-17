package com.amt.CustomerQuotePackage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Difference;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.ReadExcelCalculationForPurchaseAgreement;
import com.amt.testUtil.RemoveComma;

public class CustomerQuotePage_CP_PCP_Page extends TestBase {

	CustomerQuotePage_CP_PCP_Page obj_cust_quote_outright_bchPage;
	ReadExcelCalculationForPurchaseAgreement obj_read_excel_calculation_page;

	

	JavascriptExecutor jse;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

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
	
	// quote summary
	@FindBy(xpath = "//p[normalize-space()='Quote summary']")
	private WebElement quote_summary;

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

	@FindBy(xpath = "//*[@id='otrPartExchange']")
	private WebElement actual_part_exchange_value;

	@FindBy(xpath = "//*[@id='partExchange']|//*[@id='partExchnage']")
	private WebElement given_part_exchange_value;

	@FindBy(xpath = "//*[@id='lessFinanceSettlement']")
	private WebElement less_finance_Settlement;

	@FindBy(xpath = "//div[@class='partex-value']//input[@name='orderDeposit']")
	private WebElement order_Deposit;

	@FindBy(xpath = "//div[@class='partex-value']//input[@name='financeDeposit']")
	private WebElement finance_Deposit;

	@FindBy(xpath = "//div[@class='partex-col docfee-center']//input[@name='DocumentFee']")
	private WebElement document_fee;

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

	@FindBy(xpath = "//app-purchase-customer-quote-summary-header/div/div[6]/div/p/strong")
	private WebElement total_monthly_payment;

	@FindBy(xpath = "((//*[normalize-space()='On the road price']//ancestor::div[1])[1])//div[2]")
	private WebElement otr_cost_price;

	@FindBy(xpath = "//*[@name='salesTotal']")
	private WebElement sales_total_input;

	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[1]/button/div")
	private WebElement customer_quote_summary;

	@FindBy(xpath = "//*[normalize-space()='Basic cash price']//ancestor::div[1]//div//p//strong")
	private WebElement customer_quote_summary_basic_cash_price;

	@FindBy(xpath = "//*[contains(text(),' Holding cost summary ')]")
	private WebElement holding_cost_summary;

	@FindBy(xpath = "//*[normalize-space()='Total CAP maint. value (ex. VAT):']//ancestor::div[1]//p|//*[normalize-space()='Total CAP maint. value (ex. VAT) :']//ancestor::div[1]//p")
	private WebElement total_cap_maintenance_value;

	@FindBy(xpath = "//*[normalize-space()='Matrix Credit type']//ancestor::div[1]//div//ng-select")
	private WebElement matrix_credit_type_dropdown;

	@FindBy(xpath = "//*[normalize-space()='Term']//ancestor::div[1]//div//p//strong")
	private WebElement customer_quote_summary_terms;


	
	public CustomerQuotePage_CP_PCP_Page() {
		
		

		PageFactory.initElements(driver, this);

	}

	public boolean check_monthly_finance_payment_on_customer_quote_for_used_car_with_funder_quote_addition_with_maintenance(
			WebDriver driver, String maintenance_status, String matrix_credit_type, String balloon_payment_status,
			String order_deposit, String finance_deposit, String document_fee, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException, NumberFormatException,
			ClassNotFoundException {

		Thread.sleep(2000);

		Click.on(driver, holding_cost_summary, 120);

		ExplicitWait.visibleElement(driver, total_cap_maintenance_value, 30);

		double totalCapMaintenanceValue = Double
				.parseDouble(RemoveComma.of(total_cap_maintenance_value.getText().trim().substring(2)));

		System.out.println(totalCapMaintenanceValue);

		Click.on(driver, customer_quote, 120);

		LO.print("***********Entered in Customer Quote page ***********");
		System.out.println("***********Entered in Customer Quote page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Actions act = new Actions(driver);

		if (totalCapMaintenanceValue == 0) {

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

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			Click.on(driver, customer_quote_summary, 40);

			ExplicitWait.visibleElement(driver, customer_quote_summary_basic_cash_price, 20);

			Thread.sleep(5000);

			double basic_cash_price_from_screen = Double
					.parseDouble(RemoveComma.of(customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

			obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

			obj_read_excel_calculation_page
					.write_basic_cash_price_to_excel_for_cp_used_car_funder(basic_cash_price_from_screen, sheet_name);

			obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement_cp_for_funder_addition(
					document_fee, matrix_credit_type, sheet_name);
		
		     Click.on(driver, quote_summary, 30);
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

			Click.on(driver, customer_quote, 30);
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

			
			ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

			Thread.sleep(3000);

			double monthly_finance_payment_actual_from_screen = Double
					.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

			LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
			System.out.println(
					"Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

			double monthly_finance_payment_expected_from_excel = obj_read_excel_calculation_page
					.get_monthly_finance_payment_from_excel_for_funder_addition(maintenance_status, matrix_credit_type,
							balloon_payment_status, order_deposit, finance_deposit, document_fee, sheet_name);

			LO.print("Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);
			System.out.println(
					"Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);

			double diff1 = Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen,
					monthly_finance_payment_expected_from_excel);

			boolean status = false;
			if (diff1 < 0.2) {
				status = true;
			}
			return status;
		}

		else {

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

			Click.on(driver, customer_quote_summary, 40);

			ExplicitWait.visibleElement(driver, customer_quote_summary_basic_cash_price, 20);

			Thread.sleep(5000);

			double basic_cash_price_from_screen = Double
					.parseDouble(RemoveComma.of(customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

			obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

			obj_read_excel_calculation_page
					.write_basic_cash_price_to_excel_for_cp_used_car_funder(basic_cash_price_from_screen, sheet_name);

			obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement_cp_for_funder_addition(
					document_fee, matrix_credit_type, sheet_name);
	
	    	Click.on(driver, quote_summary, 30);
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

			Click.on(driver, customer_quote, 30);
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

			
			ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

			ExplicitWait.visibleElement(driver, customer_quote_monthly_maintenance_rental, 30);

			Thread.sleep(3000);

			double monthly_finance_payment_actual_from_screen = Double
					.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

			double monthly_maintenance_payment_actual_from_screen = Double.parseDouble(
					RemoveComma.of(customer_quote_monthly_maintenance_rental.getText().trim().substring(2)));

			LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
			System.out.println(
					"Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

			double monthly_finance_payment_expected_from_excel = obj_read_excel_calculation_page
					.get_monthly_finance_payment_from_excel_for_funder_addition(maintenance_status, matrix_credit_type,
							balloon_payment_status, order_deposit, finance_deposit, document_fee, sheet_name);

			LO.print("Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);
			System.out.println(
					"Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);

			LO.print("Actual Monthly Maintenance Payment from screen is "
					+ monthly_maintenance_payment_actual_from_screen);
			System.out.println("Actual Monthly Maintenance Payment from screen is "
					+ monthly_maintenance_payment_actual_from_screen);

			double monthly_Maintenance_payment_expected_from_excel = obj_read_excel_calculation_page
					.get_monthly_maintenance_payment_from_excel_for_funder_addition(sheet_name);

			LO.print("Expected Monthly Maintenance Rental from excel is "
					+ monthly_Maintenance_payment_expected_from_excel);
			System.out.println("Expected Monthly Maintenance Rental from excel is "
					+ monthly_Maintenance_payment_expected_from_excel);

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

	}

	public boolean check_monthly_finance_payment_on_customer_quote_for_used_car_with_funder_quote_addition_without_maintenance(
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

		Click.on(driver, customer_quote_summary, 40);

		ExplicitWait.visibleElement(driver, customer_quote_summary_basic_cash_price, 20);

		Thread.sleep(5000);

		double basic_cash_price_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page
				.write_basic_cash_price_to_excel_for_cp_used_car_funder(basic_cash_price_from_screen, sheet_name);

		obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement_cp_for_funder_addition(
				document_fee, matrix_credit_type, sheet_name);

		
		Click.on(driver, quote_summary, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		Click.on(driver, customer_quote, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		
		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

		Thread.sleep(3000);

		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
		System.out
				.println("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

		double monthly_finance_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_finance_payment_from_excel_for_funder_addition_for_cp_pcp(maintenance_status,
						matrix_credit_type, balloon_payment_status, order_deposit, finance_deposit, document_fee,
						sheet_name);

		LO.print("Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);
		System.out.println(
				"Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);

		double diff1 = Difference.of_two_Double_Values(monthly_finance_payment_actual_from_screen,
				monthly_finance_payment_expected_from_excel);

		boolean status = false;
		if (diff1 < 0.2) {
			status = true;
		}
		return status;
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
			double vehicel_profit_actual = Double.parseDouble(vehicle_profit_input.getAttribute("value"));

			double diff1 = Difference.of_two_Double_Values(vehicel_profit_expected, vehicel_profit_actual);

			ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
			String vehicle_additional_discount_copied = vehicle_additional_discount.getAttribute("value");

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		double monthly_total_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_total_payment_after_editing_vehicle_profit(vehicle_additional_discount_copied, sheet_name);
		ExplicitWait.visibleElement(driver, total_monthly_payment, 30);

		Thread.sleep(4000);
		double monthly_total_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(total_monthly_payment.getText().trim().substring(2)));
		double diff = Difference.of_two_Double_Values(monthly_total_payment_expected_from_excel,
				monthly_total_payment_actual_from_screen);

		boolean status = false;

		if (diff < 0.2 && diff1 < 0.2) {
			status = true;

			LO.print("Vehicle profit and Total Monthly Payment verified on editing Vehicle Sales Price");
			System.out.println("Vehicle profit and Total Monthly Payment verified on editing Vehicle Sales Price");
		}

		return status;
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
			double vehicel_profit_actual = Double.parseDouble(vehicle_profit_input.getAttribute("value"));

			double diff1 = Difference.of_two_Double_Values(vehicel_profit_expected, vehicel_profit_actual);

			ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
			String vehicle_additional_discount_copied = vehicle_additional_discount.getAttribute("value");

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		double monthly_total_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_finance_payment_after_editing_vehicle_profit(vehicle_additional_discount_copied,
						sheet_name);
		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

		Thread.sleep(4000);
		double monthly_total_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));
		double diff = Difference.of_two_Double_Values(monthly_total_payment_expected_from_excel,
				monthly_total_payment_actual_from_screen);

		boolean status = false;

		if (diff < 0.2 && diff1 < 0.2) {
			status = true;

			LO.print("Vehicle profit and Total Monthly Payment verified on editing Vehicle Sales Price");
			System.out.println("Vehicle profit and Total Monthly Payment verified on editing Vehicle Sales Price");
		}

		return status;
	}

	public boolean check_monthly_finance_payment_on_customer_quote_with_funder_quote_addition_with_maintenance(
			WebDriver driver, String maintenance_status, String matrix_credit_type, String balloon_payment_status,
			String order_deposit, String finance_deposit, String document_fee, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException, NumberFormatException,
			ClassNotFoundException {

		Thread.sleep(2000);

//		Click.on(driver, holding_cost_summary, 30);
//
//		ExplicitWait.visibleElement(driver, total_cap_maintenance_value, 30);
//
//		double totalCapMaintenanceValue = Double
//				.parseDouble(RemoveComma.of(total_cap_maintenance_value.getText().trim().substring(2)));

		Click.on(driver, customer_quote, 30);

		LO.print("***********Entered in Customer Quote page ***********");
		System.out.println("***********Entered in Customer Quote page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);


		Click.on(driver, matrix_credit_type_dropdown, 50);

		Thread.sleep(10000);
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

		LO.print("Matrix credit type " + matrix_credit_type + " has been selected");
		System.out.println("Matrix credit type " + matrix_credit_type + " has been selected");

		Click.on(driver, customer_quote_maintenance_toggle_button, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement_cp_for_funder_addition(
				document_fee, matrix_credit_type, sheet_name);

		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

		ExplicitWait.visibleElement(driver, customer_quote_monthly_maintenance_rental, 30);

		Thread.sleep(3000);

		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		double monthly_maintenance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_maintenance_rental.getText().trim().substring(2)));

		LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
		System.out
				.println("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

		// double monthly_finance_payment_expected_from_excel =
		// GetExcelFormulaValue.get_formula_value(103, 1, sheet_name);

		double monthly_finance_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_finance_payment_from_excel_for_funder_addition_for_cp_pcp(maintenance_status,
						matrix_credit_type, balloon_payment_status, order_deposit, finance_deposit, document_fee,
						sheet_name);

		LO.print("Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);
		System.out.println(
				"Expected Monthly Finannce Rental from excel is " + monthly_finance_payment_expected_from_excel);

		LO.print("Actual Monthly Maintenance Payment from screen is " + monthly_maintenance_payment_actual_from_screen);
		System.out.println(
				"Actual Monthly Maintenance Payment from screen is " + monthly_maintenance_payment_actual_from_screen);

		// double monthly_Maintenance_payment_expected_from_excel =
		// GetExcelFormulaValue.get_formula_value(102, 1, sheet_name);

		double monthly_Maintenance_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_maintenance_payment_from_excel_for_funder_addition_cp_pcp(maintenance_status,
						balloon_payment_status, sheet_name);

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

		Thread.sleep(5000);
		try {
			List<WebElement> list = driver
					.findElements(By.xpath("//*[@class='ng-dropdown-panel-items scroll-host']/div/div/span"));

			Thread.sleep(3000);

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

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement_cp_for_funder_addition(
				document_fee, matrix_credit_type, sheet_name);

		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

		Thread.sleep(4000);

		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
		System.out
				.println("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

		double monthly_finance_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_finance_payment_from_excel_for_funder_addition_for_cp_pcp(maintenance_status,
						matrix_credit_type, balloon_payment_status, order_deposit, finance_deposit, document_fee,
						sheet_name);

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

		act.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB,
				Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB,
				Keys.TAB, Keys.ENTER).build().perform();

		Thread.sleep(3000);
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
    	String vehicle_discount_copied = vehicle_discount.getAttribute("value");
		
		ExplicitWait.visibleElement(driver, paint_discount, 30);
    	String paint_discount_copied = paint_discount.getAttribute("value");
	
		
		ExplicitWait.visibleElement(driver, options_discount, 30);
    	String options_discount_copied = options_discount.getAttribute("value");
	

		ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
    	String vehicle_additional_copied = vehicle_additional_discount.getAttribute("value");
	

    	ExplicitWait.visibleElement(driver, paint_additional_discount, 30);
    	String paint_additional_copied = paint_additional_discount.getAttribute("value");
	
		
		ExplicitWait.visibleElement(driver, options_additional_discount, 30);
    	String options_additional_copied = options_additional_discount.getAttribute("value");
		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);

		Thread.sleep(4000);
		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
		System.out
				.println("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

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

		paint_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, paint_discount, paintDiscountCostPrice + 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		options_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, options_discount, optionsDiscountCostPrice + 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		vehicle_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, vehicle_additional_discount, vehicleAdditionalDiscountCostPrice + 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		paint_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, paint_additional_discount, paintAdditionalDiscountCostPrice + 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		options_additional_discount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeysdouble(driver, options_additional_discount, optionsAdditionalDiscountCostPrice + 1, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		// getting vehicle Discount Sales Price
		double vehicleDiscountSalesPrice =  Double.parseDouble(vehicle_discount.getAttribute("value"));
		// getting paint Discount Sales Price
		double paintDiscountSalesPrice =  Double.parseDouble(paint_discount.getAttribute("value"));
		// getting options Discount Sales Price
    	double optionsDiscountSalesPrice =  Double.parseDouble(options_discount.getAttribute("value"));
		// getting vehicle additional Discount Sales Price
		double vehicleAdditionalDiscountSalesPrice =  Double.parseDouble(vehicle_additional_discount.getAttribute("value"));
		// getting paint additional Discount Sales Price
		double paintAdditionalDiscountSalesPrice =  Double.parseDouble(paint_additional_discount.getAttribute("value"));
		// getting otions additional Discount Sales Price
		double optionsAdditionalDiscountSalesPrice =  Double.parseDouble(options_additional_discount.getAttribute("value"));

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

		// getting vehicle Discount Sales Price
		double vehicleDiscountSalesPrice =  Double.parseDouble(vehicle_discount.getAttribute("value"));
		// getting paint Discount Sales Price
		double paintDiscountSalesPrice =  Double.parseDouble(paint_discount.getAttribute("value"));
		// getting options Discount Sales Price
    	double optionsDiscountSalesPrice =  Double.parseDouble(options_discount.getAttribute("value"));
		// getting vehicle additional Discount Sales Price
		double vehicleAdditionalDiscountSalesPrice =  Double.parseDouble(vehicle_additional_discount.getAttribute("value"));
		// getting paint additional Discount Sales Price
		double paintAdditionalDiscountSalesPrice =  Double.parseDouble(paint_additional_discount.getAttribute("value"));
		// getting otions additional Discount Sales Price
		double optionsAdditionalDiscountSalesPrice =  Double.parseDouble(options_additional_discount.getAttribute("value"));

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

		double salesPriceActualFromSCreen =  Double.parseDouble(sales_total_input.getAttribute("value"));

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

		   ExplicitWait.visibleElement(driver, vehicle_profit_input, 30);
			double vehicleProfitActualFromSCreen = Double.parseDouble(vehicle_profit_input.getAttribute("value"));

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

		// getting monthly finance payment actual from screen

		Thread.sleep(4000);
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

		// getting vehicle Discount Sales Price
		double vehicleDiscountSalesPrice =  Double.parseDouble(vehicle_discount.getAttribute("value"));
		// getting paint Discount Sales Price
		double paintDiscountSalesPrice =  Double.parseDouble(paint_discount.getAttribute("value"));
		// getting options Discount Sales Price
    	double optionsDiscountSalesPrice =  Double.parseDouble(options_discount.getAttribute("value"));
		// getting vehicle additional Discount Sales Price
		double vehicleAdditionalDiscountSalesPrice =  Double.parseDouble(vehicle_additional_discount.getAttribute("value"));
		// getting paint additional Discount Sales Price
		double paintAdditionalDiscountSalesPrice =  Double.parseDouble(paint_additional_discount.getAttribute("value"));
		// getting otions additional Discount Sales Price
		double optionsAdditionalDiscountSalesPrice =  Double.parseDouble(options_additional_discount.getAttribute("value"));

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

		double salesPriceActualFromSCreen =  Double.parseDouble(sales_total_input.getAttribute("value"));

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

		   ExplicitWait.visibleElement(driver, vehicle_profit_input, 30);
			double vehicleProfitActualFromSCreen = Double.parseDouble(vehicle_profit_input.getAttribute("value"));

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

		// getting monthly finance payment actual from screen

		Thread.sleep(4000);
		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		LO.print("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);
		System.out
				.println("Actual Monthly Finance Payment from screen is " + monthly_finance_payment_actual_from_screen);

		// getting monthly finance payment expected from excel

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

		act.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB,
				Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB,
				Keys.TAB, Keys.ENTER).build().perform();

		Thread.sleep(3000);
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

		Click.on(driver, customer_quote_maintenance_toggle_button, 40);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, customer_quote_summary_terms, 20);

		String term = customer_quote_summary_terms.getText().trim().substring(0, 1);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page.set_global_variables_to_excel_for_purchase_agreement(term, matrix_credit_type,
				sheet_name);

		ExplicitWait.visibleElement(driver, vehicle_discount, 30);
    	String vehicle_discount_copied = vehicle_discount.getAttribute("value");
		
		ExplicitWait.visibleElement(driver, paint_discount, 30);
    	String paint_discount_copied = paint_discount.getAttribute("value");
	
		
		ExplicitWait.visibleElement(driver, options_discount, 30);
    	String options_discount_copied = options_discount.getAttribute("value");
	

		ExplicitWait.visibleElement(driver, vehicle_additional_discount, 30);
    	String vehicle_additional_copied = vehicle_additional_discount.getAttribute("value");
	

    	ExplicitWait.visibleElement(driver, paint_additional_discount, 30);
    	String paint_additional_copied = paint_additional_discount.getAttribute("value");
	
		
		ExplicitWait.visibleElement(driver, options_additional_discount, 30);
    	String options_additional_copied = options_additional_discount.getAttribute("value");
		ExplicitWait.visibleElement(driver, total_monthly_payment, 30);

		Thread.sleep(4000);

		double monthly_total_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(total_monthly_payment.getText().trim().substring(2)));

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

	public boolean edit_vehicle_profit_and_check_monthly_finance_payment(String vehicle_profit, String sheet_name)
			throws InterruptedException, UnsupportedFlavorException, IOException {

		ExplicitWait.visibleElement(driver, vehicle_profit_input, 30);
		vehicle_profit_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeys(driver, vehicle_profit_input, vehicle_profit, 30);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		String vehicle_additional_discount_copied = vehicle_additional_discount.getAttribute("value");
		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		double monthly_finance_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_finance_payment_after_editing_vehicle_profit(vehicle_additional_discount_copied,
						sheet_name);

		Thread.sleep(4000);
		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));
		double diff = Difference.of_two_Double_Values(monthly_finance_payment_expected_from_excel,
				monthly_finance_payment_actual_from_screen);
		boolean status = false;
		if (diff < 0.2) {
			status = true;

			LO.print("Monthly Finance Payment verified after editing vehicle profit");
			System.out.println("Monthly Finance Payment verified after editing vehicle profit");
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
		String vehicle_additional_discount_copied = vehicle_additional_discount.getAttribute("value");
		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		double monthly_total_payment_expected_from_excel = obj_read_excel_calculation_page
				.get_monthly_total_payment_after_editing_vehicle_profit(vehicle_additional_discount_copied, sheet_name);
		ExplicitWait.visibleElement(driver, total_monthly_payment, 30);
		Thread.sleep(4000);

		double monthly_total_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(total_monthly_payment.getText().trim().substring(2)));
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

	public boolean put_part_exchange_values_and_check_monthly_finance_payment(String part_exchange_actual,
			String part_exchange_given, String less_finance_settlement, String order_deposit, String finance_deposit,
			String sheet_name) throws UnsupportedFlavorException, IOException, InterruptedException {

		Actions act = new Actions(driver);

		Click.on(driver, part_exchange_and_additional_payment_button, 30);
		Thread.sleep(1000);
		Click.sendKeys(driver, actual_part_exchange_value, part_exchange_actual, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		Thread.sleep(1000);
		Click.sendKeys(driver, given_part_exchange_value, part_exchange_given, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		Thread.sleep(1000);
		Click.sendKeys(driver, less_finance_Settlement, less_finance_settlement, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		Thread.sleep(1000);
		Click.sendKeys(driver, order_Deposit, order_deposit, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		Thread.sleep(1000);
		Click.sendKeys(driver, finance_Deposit, finance_deposit, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		Thread.sleep(1000);

		   ExplicitWait.visibleElement(driver, document_fee, 30);		
		   String document_fee_copied = document_fee.getAttribute("value");
		Thread.sleep(3000);

		ExplicitWait.visibleElement(driver, balance_to_finance_value, 30);

		double balance_to_finance_value_from_screen = Double
				.parseDouble(RemoveComma.of(balance_to_finance_value.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, customer_quote_monthly_finance_rental, 30);
		Thread.sleep(4000);

		double monthly_finance_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(customer_quote_monthly_finance_rental.getText().trim().substring(2)));
		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		double[] monthlyFinanceAndBalanceToFinance = obj_read_excel_calculation_page
				.get_monthly_finance_payment_and_balance_to_finance_payment_after_editing_part_exchange_values(
						part_exchange_actual, part_exchange_given, less_finance_settlement, order_deposit,
						finance_deposit, document_fee_copied, sheet_name);

		double monthly_finance_payment_expected = monthlyFinanceAndBalanceToFinance[0];
		double balance_to_finance_expected = monthlyFinanceAndBalanceToFinance[1];

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
			String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			String order_deposit, String finance_deposit, String sheet_name)
			throws InterruptedException, UnsupportedFlavorException, IOException {
		Actions act = new Actions(driver);

		Click.on(driver, part_exchange_and_additional_payment_button, 30);
		Thread.sleep(1000);
		Click.sendKeys(driver, actual_part_exchange_value, part_exchange_actual, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		Thread.sleep(1000);
		Click.sendKeys(driver, given_part_exchange_value, part_exchange_given, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		Thread.sleep(1000);
		Click.sendKeys(driver, less_finance_Settlement, less_finance_settlement, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		Thread.sleep(1000);
		Click.sendKeys(driver, order_Deposit, order_deposit, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Thread.sleep(4000);

		Click.sendKeys(driver, finance_Deposit, finance_deposit, 30);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);


		   ExplicitWait.visibleElement(driver, document_fee, 30);		
		   String document_fee_copied = document_fee.getAttribute("value");
		Thread.sleep(3000);

		ExplicitWait.visibleElement(driver, balance_to_finance_value, 30);

		Thread.sleep(4000);

		double balance_to_finance_value_from_screen = Double
				.parseDouble(RemoveComma.of(balance_to_finance_value.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, total_monthly_payment, 30);
		double monthly_total_payment_actual_from_screen = Double
				.parseDouble(RemoveComma.of(total_monthly_payment.getText().trim().substring(2)));
		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		double[] monthlyFinanceAndBalanceToFinance = obj_read_excel_calculation_page
				.get_monthly_total_payment_and_balance_to_finance_payment_after_editing_part_exchange_values(
						part_exchange_actual, part_exchange_given, less_finance_settlement, order_deposit,
						finance_deposit, document_fee_copied, sheet_name);

		double monthly_total_payment_expected = monthlyFinanceAndBalanceToFinance[0];
		double balance_to_finance_expected = monthlyFinanceAndBalanceToFinance[1];

		double diff1 = Difference.of_two_Double_Values(balance_to_finance_value_from_screen,
				balance_to_finance_expected);
		double diff2 = Difference.of_two_Double_Values(monthly_total_payment_actual_from_screen,
				monthly_total_payment_expected);

		boolean status = false;
		if (diff1 < 0.2 && diff2 < 0.2) {
			status = true;
			LO.print("Monthly total Payment verified after editing part exchange values and deposit values");
			System.out.println("Monthly total Payment verified after editing part exchange values and deposit values");
		}

		return status;

	}

}
