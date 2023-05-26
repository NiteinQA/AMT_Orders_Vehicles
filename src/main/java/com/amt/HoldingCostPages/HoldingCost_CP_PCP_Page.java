package com.amt.HoldingCostPages;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Difference;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.ReadExcelCalculationForPurchaseAgreement;
import com.amt.testUtil.RemoveComma;

public class HoldingCost_CP_PCP_Page extends TestBase {
	ReadExcelCalculationForPurchaseAgreement obj_read_excel_calculation_page;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//*[@name='Terms']")
	private WebElement additional_terms;

	@FindBy(xpath = "//*[@name='MileagePerAnnum']")
	private WebElement additional_mileage;

	@FindBy(xpath = "//*[contains(text(),'CAP residual value')]//ancestor::div[1]//p//strong")
	private WebElement holding_cost_summary_residual_value_used;

	@FindBy(xpath = "//p[contains(text(),'Holding cost')]")
	private WebElement holding_cost;

	@FindBy(xpath = "//span[@class='slider round']")
	private WebElement maintenance_toggle_button;

	@FindBy(xpath = "//*[contains(text(),' Holding cost summary ')]")
	private WebElement holding_cost_summary;

	@FindBy(xpath = "//*[@id='ResidualValue']")
	private WebElement holding_cost_summary_residual_value_used_input_field;

	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/div/div[1]/div/p/strong")
	private WebElement holding_cost_summary_terms;

	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/div/div[2]/div/p/strong")
	private WebElement holding_cost_summary_mileage;

	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/div/div[3]/div/p/strong")
	private WebElement total_monthly_holding_cost;

	@FindBy(xpath = "//*[@class='slider round sliderRed']")
	private WebElement holding_cost_maintenance_toggle_button;

	@FindBy(xpath = "//*[@id='vehicleSummery']/div/div[2]/div[2]/div[6]/div[2]/div[10]/p")
	private WebElement holding_cost_maintenance_cost_used;

	@FindBy(xpath = "//*[@id='ResidualPercentage']")
	private WebElement holding_cost_percentage_cap_residual_value_used;

	@FindBy(xpath = "//input[@id='CapMaintenancePercentage']")
	private WebElement percentage_maintenance_cost_used;

	@FindBy(xpath = "//input[@id='ResidualValue']")
	private WebElement residual_value_used;

	@FindBy(xpath = "//input[@id='Maintenancevalue3']")
	private WebElement maintenance_cost_used;

	@FindBy(xpath = "//*[contains(text(),'Total CAP maint. value')]//ancestor::div[1]//p//strong")
	private WebElement total_cap_maintenance_value;

	@FindBy(xpath = "//button[@class='btn btn-outline-secondary px-4 mr-2']")
	private WebElement add_funder_quote;

	@FindBy(xpath = "//*[contains(text(),'Holding cost based on funder quote')]//label//span")
	private WebElement holding_cost_based_on_funder_quote_toggle_button;

	@FindBy(xpath = "//input[@role='combobox']")
	private WebElement funder;

	@FindBy(xpath = "//input[@id='quoteReferenceNo']")
	private WebElement quote_ref;

	@FindBy(xpath = "//input[@id='quoteExpiryDate']")
	private WebElement expiry_date;

	@FindBy(xpath = "//select[@name='acquisitionPaymentProfileId']")
	private WebElement payment_profile_dropdown;

	@FindBy(xpath = "//input[@id='duration']")
	private WebElement duration;

	@FindBy(xpath = "//input[@id='initialFinanceRental']")
	private WebElement initial_finance_rental;

	@FindBy(xpath = "//input[@id='totalInitialRental']")
	private WebElement total_initial_rental;

	@FindBy(xpath = "//input[@id='funderItemMileage']")
	private WebElement miles_per_annum;

	@FindBy(xpath = "//input[@id='contractMileage']")
	private WebElement contract_mileage;

	@FindBy(xpath = "//input[@id='cashDeposit']")
	private WebElement cash_deposit;

	@FindBy(xpath = "//*[@id='collapseOne']/div/app-contract-and-hp/form/div/div[1]/div[2]/div/div/div[1]/div/div[7]/div/label")
	private WebElement total_cash_price;

	@FindBy(xpath = "//input[@id='financeCharges']")
	private WebElement finance_charges;

	@FindBy(xpath = "//input[@id='monthlyFinanceRental']")
	private WebElement monthly_finance_rental;

	@FindBy(xpath = "//input[@id='optionalFinalPayment']")
	private WebElement optional_final_payment;

	@FindBy(xpath = "//input[@id='optionToPurchaseFee']")
	private WebElement option_to_purchase_fee;

	@FindBy(xpath = "//input[@id='financeRental']")
	private WebElement monthly_payment;

	@FindBy(xpath = "//input[@id='documentFee']")
	private WebElement document_fee;

	@FindBy(xpath = "//input[@id='pencePerExcessMileageFinance']")
	private WebElement pense_per_excess_mile_finance;

	@FindBy(xpath = "//span[@class='slider round']")
	private WebElement funder_maintenance_toggle;

	@FindBy(xpath = "//span[@class='slider round sliderRed']")
	private WebElement common_maintenance_toggle;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/form/div/div/div[4]/div/p/strong")
	private WebElement cap_monthly_maint_cost;

	@FindBy(xpath = "//input[@id='monthlyMaintenancePayment']")
	private WebElement monthly_maintenance_rental;

	@FindBy(xpath = "//input[@id='pencePerExcessMileageMaintenance']")
	private WebElement pense_per_excess_mile_maintenance;

	@FindBy(xpath = "//input[@id='percentageOfSaleProceedToCustomer']")
	private WebElement percentage_of_sale_proceed_to_customer;

	@FindBy(xpath = "//input[@id='secondaryHirePeriodRental']")
	private WebElement secondary_hire_period_rental;

	@FindBy(xpath = "//i[@class='btn-icon-addAddress-white']")
	private WebElement add;

	public HoldingCost_CP_PCP_Page() {
		PageFactory.initElements(driver, this);
	}

	public boolean verify_holding_cost_after_adding_funder_quote_without_maintenance(String quoteRef, String expiryDate,
			String term, String milesPerAnnum, String cashDeposit, String financeCharges, String documentFee,
			String monthlyPayment, String optionalFinalPayment, String optionToPurchaseFee,
			String pencePerExcessMileFinance, String sheet_name) throws InterruptedException, IOException {
		Click.on(driver, holding_cost, 30);

		LO.print("***********Entered in holding cost page ***********");
		System.out.println("***********Entered in holding cost page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, add_funder_quote, 30);

		Thread.sleep(3000);

		Click.on(driver, holding_cost_based_on_funder_quote_toggle_button, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, funder, 30);

		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();

		Click.sendKeys(driver, quote_ref, quoteRef, 30);

		Click.sendKeys(driver, expiry_date, expiryDate, 30);

		Click.sendKeys(driver, duration, term, 30);

		Click.sendKeys(driver, miles_per_annum, milesPerAnnum, 30);

		Click.on(driver, contract_mileage, 30);

		Click.sendKeys(driver, cash_deposit, cashDeposit, 30);

		Click.sendKeys(driver, document_fee, documentFee, 30);

		Click.sendKeys(driver, monthly_payment, monthlyPayment, 30);

		Click.sendKeys(driver, optional_final_payment, optionalFinalPayment, 30);

		Click.sendKeys(driver, option_to_purchase_fee, optionToPurchaseFee, 30);

		Click.sendKeys(driver, pense_per_excess_mile_finance, pencePerExcessMileFinance, 30);

		Thread.sleep(2000);

		Click.on(driver, add, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		double monthly_holding_cost_expected = obj_read_excel_calculation_page
				.verify_holding_cost_after_adding_funder_without_maintenance_for_cp_purchase(cashDeposit, term,
						milesPerAnnum, monthlyPayment, optionalFinalPayment, optionToPurchaseFee, documentFee,
						sheet_name);

		ExplicitWait.visibleElement(driver, total_monthly_holding_cost, 50);
		String monthly_holding_cost = total_monthly_holding_cost.getText().substring(2);

		String total_monthly_holding_cost_from_screen = RemoveComma.of(monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_screen =" + monthly_holding_cost);
		System.out.println("Total_monthly_holding_cost_from_screen " + monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_excel =" + monthly_holding_cost_expected);
		System.out.println("Total_monthly_holding_cost_from_excel " + monthly_holding_cost_expected);

		double total_monthly_holding_cost_actual1 = Double.parseDouble(total_monthly_holding_cost_from_screen);
		double diff = Difference.of_two_Double_Values(total_monthly_holding_cost_actual1,
				monthly_holding_cost_expected);
		boolean flag = false;
		if (diff < 0.2) {
			flag = true;
		}

		return flag;

	}

	public boolean verify_holding_cost_before_editing_cap_values_without_maintenance(
			String residual_value_used_from_excel, String percentage_cap_residual_value_used,
			String maintenance_required, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {
		Click.on(driver, holding_cost, 30);

		LO.print("***********Entered in holding cost page ***********");
		System.out.println("***********Entered in holding cost page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		Click.on(driver, holding_cost_summary, 30);

		Thread.sleep(3000);

		LO.print("Clicked on holding cost summary");
		System.out.println("Clicked on holding cost summary");

		// code for checking default holding cost based on CAP data

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		return obj_read_excel_calculation_page.verify_holding_cost_before_editing_cap_values_without_maintenance(driver,
				holding_cost_summary_terms, holding_cost_summary_mileage, holding_cost_summary_residual_value_used,
				total_monthly_holding_cost, residual_value_used_from_excel, percentage_cap_residual_value_used,
				sheet_name);
	}

	public boolean edit_percentage_residual_verify_holding_cost_without_maintenance(
			String residual_value_used_from_excel, String percentage_cap_residual_value_used,
			String maintenance_required, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {

		// Code for Edit percentage cap residual value

		ExplicitWait.visibleElement(driver, holding_cost_percentage_cap_residual_value_used, 20);

		holding_cost_percentage_cap_residual_value_used.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		Click.sendKeys(driver, holding_cost_percentage_cap_residual_value_used, percentage_cap_residual_value_used, 20);

		Actions act = new Actions(driver);

		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		return obj_read_excel_calculation_page.edit_percentage_residual_verify_holding_cost_without_maintenance(driver,
				holding_cost_summary_terms, holding_cost_summary_mileage, holding_cost_summary_residual_value_used,
				total_monthly_holding_cost, residual_value_used_from_excel, percentage_cap_residual_value_used,
				sheet_name);
	}

	public boolean edit_residual_value_used_then_verify_holding_cost_without_maintenance(
			String residual_value_used_from_excel, String percentage_cap_residual_value_used,
			String maintenance_required, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {

		Actions act = new Actions(driver);
		// code for editing residual value used and maint cost used

		ExplicitWait.visibleElement(driver, residual_value_used, 30);

		residual_value_used.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		Click.sendKeys(driver, residual_value_used, residual_value_used_from_excel, 20);

		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		return obj_read_excel_calculation_page.edit_residual_value_used_then_verify_holding_cost_without_maintenance(
				driver, holding_cost_summary_terms, holding_cost_summary_mileage,
				holding_cost_summary_residual_value_used, total_monthly_holding_cost, residual_value_used_from_excel,
				percentage_cap_residual_value_used, sheet_name);

	}

	public boolean verify_holding_cost_without_maintenance_edited(String residual_value_used, String sheet_name)
			throws IOException, InterruptedException {

		Click.on(driver, holding_cost, 30);

		LO.print("***********Entered in holding cost page ***********");
		System.out.println("***********Entered in holding cost page ***********");
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		Click.on(driver, holding_cost_summary, 30);

		Thread.sleep(3000);
		LO.print("Clicked on holding cost summary");
		System.out.println("Clicked on holding cost summary");

		ExplicitWait.visibleElement(driver, holding_cost_summary_residual_value_used_input_field, 20);

		System.out.println("residual_value_used from test data " + residual_value_used);
		holding_cost_summary_residual_value_used_input_field.click();
		holding_cost_summary_residual_value_used_input_field.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Click.sendKeys(driver, holding_cost_summary_residual_value_used_input_field, residual_value_used, 30);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();

		Thread.sleep(5000);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		return obj_read_excel_calculation_page.verify_holding_cost_without_maintenance(driver,
				holding_cost_summary_terms, holding_cost_summary_mileage, holding_cost_summary_residual_value_used,
				total_monthly_holding_cost, sheet_name);
	}

	public boolean verify_holding_cost_before_editing_cap_data_with_maintenance(
			String percentage_maintenance_cost_used_from_excel, String residual_value_used_from_excel,
			String maintenance_cost_used_from_excel, String percentage_cap_residual_value_used,
			String maintenance_required, String target_rental, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {
		Actions act = new Actions(driver);

		Click.on(driver, holding_cost, 30);

		LO.print("***********Entered in holding cost page ***********");
		System.out.println("***********Entered in holding cost page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		Click.on(driver, holding_cost_summary, 30);

		Thread.sleep(3000);

		LO.print("Clicked on holding cost summary");
		System.out.println("Clicked on holding cost summary");

		Click.on(driver, holding_cost_maintenance_toggle_button, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		LO.print("Clicked on holding_cost_maintenance_toggle_button");
		System.out.println("Clicked on holding_cost_maintenance_toggle_button");

		// code for checking holding cost without editing any value

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		return obj_read_excel_calculation_page.verify_holding_cost_before_editing_cap_data_with_maintenance(driver,
				holding_cost_summary_terms, holding_cost_summary_mileage, holding_cost_summary_residual_value_used,
				total_monthly_holding_cost, holding_cost_maintenance_cost_used,
				holding_cost_percentage_cap_residual_value_used, total_cap_maintenance_value, maintenance_required,
				target_rental, residual_value_used_from_excel, maintenance_cost_used_from_excel,
				percentage_cap_residual_value_used, percentage_maintenance_cost_used_from_excel, sheet_name);
	}

	public boolean edit_percentage_residual_and_maint_cost_then_verify_holding_cost_with_maintenance(
			String percentage_maintenance_cost_used_from_excel, String residual_value_used_from_excel,
			String maintenance_cost_used_from_excel, String percentage_cap_residual_value_used,
			String maintenance_required, String target_rental, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {
		Actions act = new Actions(driver);

		// Code for Edit percentage cap residual value and maint on screen

		ExplicitWait.visibleElement(driver, holding_cost_percentage_cap_residual_value_used, 20);
		ExplicitWait.visibleElement(driver, percentage_maintenance_cost_used, 20);

		holding_cost_percentage_cap_residual_value_used.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		Click.sendKeys(driver, holding_cost_percentage_cap_residual_value_used, percentage_cap_residual_value_used, 20);

		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		percentage_maintenance_cost_used.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		Click.sendKeys(driver, percentage_maintenance_cost_used, percentage_maintenance_cost_used_from_excel, 20);

		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		return obj_read_excel_calculation_page
				.edit_percentage_residual_and_maint_cost_then_verify_holding_cost_with_maintenance(driver,
						holding_cost_summary_terms, holding_cost_summary_mileage,
						holding_cost_summary_residual_value_used, total_monthly_holding_cost,
						holding_cost_maintenance_cost_used, holding_cost_percentage_cap_residual_value_used,
						total_cap_maintenance_value, maintenance_required, target_rental,
						residual_value_used_from_excel, maintenance_cost_used_from_excel,
						percentage_cap_residual_value_used, percentage_maintenance_cost_used_from_excel, sheet_name);

	}

	public boolean edit_residual_value_and_maint_cost_then_verify_holding_cost_with_maintenance(
			String percentage_maintenance_cost_used_from_excel, String residual_value_used_from_excel,
			String maintenance_cost_used_from_excel, String percentage_cap_residual_value_used,
			String maintenance_required, String target_rental, String sheet_name)
			throws IOException, InterruptedException, ClassNotFoundException {
		Actions act = new Actions(driver);

		// code for editing residual value used and maint cost used

		ExplicitWait.visibleElement(driver, residual_value_used, 20);
		ExplicitWait.visibleElement(driver, maintenance_cost_used, 20);

		residual_value_used.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		Click.sendKeys(driver, residual_value_used, residual_value_used_from_excel, 20);

		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		maintenance_cost_used.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		Click.sendKeys(driver, maintenance_cost_used, maintenance_cost_used_from_excel, 20);

		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		return obj_read_excel_calculation_page
				.edit_residual_value_and_maint_cost_then_verify_holding_cost_with_maintenance(driver,
						holding_cost_summary_terms, holding_cost_summary_mileage,
						holding_cost_summary_residual_value_used, total_monthly_holding_cost,
						holding_cost_maintenance_cost_used, holding_cost_percentage_cap_residual_value_used,
						total_cap_maintenance_value, maintenance_required, target_rental,
						residual_value_used_from_excel, maintenance_cost_used_from_excel,
						percentage_cap_residual_value_used, percentage_maintenance_cost_used_from_excel, sheet_name);
	}

	public boolean edit_additional_term_and_mileage_then_verify_holding_cost_with_maintenance(
			String additional_terms_from_excel, String additional_mileage_from_excel, String maintenance_required,
			String target_rental, String sheet_name)
			throws IOException, InterruptedException, UnsupportedFlavorException, ClassNotFoundException {

		Actions act = new Actions(driver);

		// code for editing additional_term_and_mileage

		// send additional terms
		Click.sendKeys(driver, additional_terms, additional_terms_from_excel, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// send additional mileage
		Click.sendKeys(driver, additional_mileage, additional_mileage_from_excel, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// Taking updated values of residual value and maint cost from screen
		ExplicitWait.visibleElement(driver, residual_value_used, 20);
		ExplicitWait.visibleElement(driver, maintenance_cost_used, 20);

		residual_value_used.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String residual_value_used_from_screen = (String) clipboard.getData(DataFlavor.stringFlavor);

		maintenance_cost_used.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		String maint_cost_used_from_screen = (String) clipboard.getData(DataFlavor.stringFlavor);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		return obj_read_excel_calculation_page
				.edit_additional_terms_and_mileage_then_verify_holding_cost_with_maintenance(driver,
						holding_cost_summary_terms, holding_cost_summary_mileage, total_monthly_holding_cost,
						maintenance_required, target_rental, residual_value_used_from_screen,
						maint_cost_used_from_screen, sheet_name);
	}

	public boolean edit_additional_term_and_mileage_then_verify_holding_cost_without_maintenance(
			String additional_terms_from_excel, String additional_mileage_from_excel, String maintenance_required,
			String sheet_name)
			throws IOException, InterruptedException, UnsupportedFlavorException, ClassNotFoundException {

		Actions act = new Actions(driver);

		// code for editing additional_term_and_mileage

		// send additional terms
		Click.sendKeys(driver, additional_terms, additional_terms_from_excel, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// send additional mileage
		Click.sendKeys(driver, additional_mileage, additional_mileage_from_excel, 20);
		act.sendKeys(Keys.TAB).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// Taking updated values of residual value and maint cost from screen
		ExplicitWait.visibleElement(driver, residual_value_used, 20);

		residual_value_used.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String residual_value_used_from_screen = (String) clipboard.getData(DataFlavor.stringFlavor);

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		return obj_read_excel_calculation_page
				.edit_additional_terms_and_mileage_then_verify_holding_cost_without_maintenance(driver,
						holding_cost_summary_terms, holding_cost_summary_mileage, total_monthly_holding_cost,
						maintenance_required, residual_value_used_from_screen, sheet_name);
	}

	public boolean verify_holding_cost_after_adding_funder_quote_with_maintenance(String quoteRef, String expiryDate,
			String term, String milesPerAnnum, String cashDeposit, String financeCharges, String documentFee,
			String monthlyPayment, String optionalFinalPayment, String optionToPurchaseFee, String monthlyMaintPayment,
			String pencePerExcessMileFinance, String pencePerExcessMileMaintenance, String sheet_name)
			throws InterruptedException, IOException {
		Click.on(driver, holding_cost, 30);

		LO.print("***********Entered in holding cost page ***********");
		System.out.println("***********Entered in holding cost page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, add_funder_quote, 30);

		Thread.sleep(3000);

		Click.on(driver, holding_cost_based_on_funder_quote_toggle_button, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Thread.sleep(2000);

		Click.on(driver, maintenance_toggle_button, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, funder, 30);

		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();

		Click.sendKeys(driver, quote_ref, quoteRef, 30);

		Click.sendKeys(driver, expiry_date, expiryDate, 30);

		Click.sendKeys(driver, duration, term, 30);

		Click.sendKeys(driver, miles_per_annum, milesPerAnnum, 30);

		Click.on(driver, contract_mileage, 30);

		Click.sendKeys(driver, cash_deposit, cashDeposit, 30);

		Click.sendKeys(driver, document_fee, documentFee, 30);

		Click.sendKeys(driver, monthly_payment, monthlyPayment, 30);

		Click.sendKeys(driver, optional_final_payment, optionalFinalPayment, 30);

		Click.sendKeys(driver, option_to_purchase_fee, optionToPurchaseFee, 30);

		Click.sendKeys(driver, monthly_maintenance_rental, monthlyMaintPayment, 30);

		Click.sendKeys(driver, pense_per_excess_mile_finance, pencePerExcessMileFinance, 30);

		Click.sendKeys(driver, pense_per_excess_mile_maintenance, pencePerExcessMileMaintenance, 30);

		Thread.sleep(2000);

		Click.on(driver, add, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

//		Click.on(driver, holding_cost_maintenance_toggle_button, 30);
//
//		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, holding_cost_summary, 30);

		ExplicitWait.visibleElement(driver, total_cap_maintenance_value, 30);

		String totalCapMaintenanceValue = RemoveComma.of(total_cap_maintenance_value.getText().trim().substring(2));

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		double monthly_holding_cost_expected = obj_read_excel_calculation_page
				.verify_holding_cost_after_adding_funder_with_maintenance_for_cp_purchase(cashDeposit, term,
						milesPerAnnum, monthlyPayment, optionalFinalPayment, optionToPurchaseFee, monthlyMaintPayment,
						documentFee, totalCapMaintenanceValue, sheet_name);

		ExplicitWait.visibleElement(driver, total_monthly_holding_cost, 50);
		String monthly_holding_cost = total_monthly_holding_cost.getText().substring(2);

		String total_monthly_holding_cost_from_screen = RemoveComma.of(monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_screen =" + monthly_holding_cost);
		System.out.println("Total_monthly_holding_cost_from_screen " + monthly_holding_cost);

		LO.print("Total_monthly_holding_cost_from_excel =" + monthly_holding_cost_expected);
		System.out.println("Total_monthly_holding_cost_from_excel " + monthly_holding_cost_expected);

		double total_monthly_holding_cost_actual1 = Double.parseDouble(total_monthly_holding_cost_from_screen);
		double diff = Difference.of_two_Double_Values(total_monthly_holding_cost_actual1,
				monthly_holding_cost_expected);
		boolean flag = false;
		if (diff < 0.2) {
			flag = true;
		}

		return flag;

	}

}
