package com.amt.QuoteSummaryPages;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class QuoteSummary_HPNR_CP_Page extends TestBase {

	ReadExcelCalculationForPurchaseAgreement obj_read_excel_calculation_page;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//p[normalize-space()='Quote summary']")
	private WebElement quote_summary;

	@FindBy(xpath = "//*[normalize-space()='Quote reference no.:']//ancestor::div[1]//span[2]")
	private WebElement quote_summary_ref_no;

	@FindBy(xpath = "//*[normalize-space()='Cost OTR price']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_cost_otr_price;

	@FindBy(xpath = "//*[normalize-space()='Monthly finance rental']//ancestor::div[1]//div//strong|//*[normalize-space()='Monthly finance payment']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_monthly_finance_rental;

	@FindBy(xpath = "//*[normalize-space()='Monthly maint. rental']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_monthly_maintenance_rental;

	@FindBy(xpath = "//*[normalize-space()='Total monthly rental']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_monthly_total_rental;

	@FindBy(xpath = "//*[@id='headingHoldingCost']//*[normalize-space()='Contract type']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_acq_contract_type;

	@FindBy(xpath = "//*[@id='headingCustomerQuote']//*[normalize-space()='Contract type']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_contract_type;

	// ***********OTR Elements***********

	// cost price ex vat and rfl
	@FindBy(xpath = "//*[normalize-space()='Cost price ex. VAT & RFL']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_cost_price_ex_vat_and_rfl;

	// otr vat
	@FindBy(xpath = "(//*[normalize-space()='VAT']//ancestor::div[1]//div//strong)[1]")
	private WebElement quote_summary_otr_vat;

	// RFL & FRF

	@FindBy(xpath = "//*[normalize-space()='RFL & FRF']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_otr_rfl_and_frf;

	// *************Holding Cost Elements**************

	// Terms

	@FindBy(xpath = "(//*[normalize-space()='Term']//ancestor::div[1]//div//strong)[1]")
	private WebElement quote_summary_holding_cost_term;

	// Miles per annum

	@FindBy(xpath = "(//*[normalize-space()='Miles per annum']//ancestor::div[1]//div//strong)[1]")
	private WebElement quote_summary_holding_cost_miles_per_annum;

	// Monthly finance cost

	@FindBy(xpath = "//*[normalize-space()='Monthly finance cost']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_holding_cost_monthly_finance_cost;

	// Monthly maint. cost used

	@FindBy(xpath = "(//*[normalize-space()='Monthly maint. cost used']//ancestor::div[1]//div//strong)[1]")
	private WebElement quote_summary_holding_cost_monthly_maint_cost_used;

	// CAP monthly maint. cost

	@FindBy(xpath = "(//*[normalize-space()='CAP monthly maint. cost']//ancestor::div[1]//div//strong)[1]")
	private WebElement quote_summary_holding_cost_CAP_monthly_maint_cost;

	@FindBy(xpath = "//*[normalize-space()='Total monthly holding cost']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_total_monthly_holding_cost;

	// **********customer quote summary elements**************

	// customer quote summary pannel

	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[1]/button/div")
	private WebElement quote_summary_customer_quote_summary_value_verification;
	// terms
	@FindBy(xpath = "//*[normalize-space()='Term']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_terms;

	// Miles per annum
	@FindBy(xpath = "//*[normalize-space()='Miles per annum']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_miles_per_annum;

	// basic cash price
	@FindBy(xpath = "//*[normalize-space()='Basic cash price']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_basic_cash_price;

	// customer_quote_summary vat
	@FindBy(xpath = "(//*[normalize-space()='VAT']//ancestor::div[1]//div//strong)[2]")
	private WebElement quote_summary_customer_quote_summary_vat;

	// non vat items
	@FindBy(xpath = "//*[normalize-space()='Non-VAT items']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_non_vat_items;

	// Total cash price
	@FindBy(xpath = "//*[normalize-space()='Total cash price']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_total_cash_price;

	// Order deposit
	@FindBy(xpath = "//app-purchase-customer-quote-summary-detail//*[normalize-space()='Order deposit']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_order_deposit;

	// Finance deposit
	@FindBy(xpath = "//app-purchase-customer-quote-summary-detail//*[normalize-space()='Finance deposit']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_finance_deposit;

	// Total deposit
	@FindBy(xpath = "//app-purchase-customer-quote-summary-detail//*[normalize-space()='Total deposit']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_total_deposit;

	@FindBy(xpath = "//app-purchase-customer-quote-summary-detail//*[normalize-space()='Part exchange value']//ancestor::div[1]//div/p/strong")
	private WebElement quote_summary_customer_quote_summary_part_exchange_value;

	// Balance to finance
	@FindBy(xpath = "//*[normalize-space()='Balance to finance']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_balance_to_finance;

	// Finance charges
	@FindBy(xpath = "//*[normalize-space()='Finance charges']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_finance_charges;

	// Document fee
	@FindBy(xpath = "//*[normalize-space()='Document fee']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_document_fee;

	// Balance payable
	@FindBy(xpath = "//*[normalize-space()='Balance payable']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_balance_payable;

	// Option to purchase fee
	@FindBy(xpath = "//*[normalize-space()='Option to purchase fee']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_option_to_purchase_fee;

	// Initial cash payment
	@FindBy(xpath = "//*[normalize-space()='Initial cash payment']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_initial_cash_payment;

	// Followed by
	@FindBy(xpath = "//*[normalize-space()='Followed by']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_followed_by;

	// Monthly finance payment
	@FindBy(xpath = "//*[normalize-space()='Monthly finance payment']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_monthly_finance_payment;

	// Monthly maint payment
	@FindBy(xpath = "(//*[normalize-space()='Monthly maint. payment']//ancestor::div[1]//div//strong)[2]")
	private WebElement quote_summary_customer_quote_summary_monthly_maint_payment;

	// Total Monthly payment
	@FindBy(xpath = "(//*[normalize-space()='Total monthly payment']//ancestor::div[1]//div//strong)[2]")
	private WebElement quote_summary_customer_quote_summary_total_monthly_payment;

	// Balloon
	@FindBy(xpath = "//*[normalize-space()='Guaranteed future value']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_guaranteed_future_value;

	// Final payment (inc. option to purchase fee)
	@FindBy(xpath = "//*[normalize-space()='Optional final payment (inc. option to purchase fee)']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee;

	// Pence per excess mile - finance
	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - finance']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_pence_per_excess_mile_finance;

	// Pence per excess mile - maint.
	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - maint.']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_pence_per_excess_mile_maint;

	// Pence per excess mile - maint.
	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - total']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_pence_per_excess_mile_total;

	// Credit type
	@FindBy(xpath = "//*[normalize-space()='Credit type']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_credit_type;

	// Vehicle comm.
	@FindBy(xpath = "//*[normalize-space()='Vehicle comm.']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_vehicle_comm;

	// Default finance comm.
	@FindBy(xpath = "//*[normalize-space()='Default finance comm.']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_default_finance_comm;

	// Maintenance commision
	@FindBy(xpath = "//*[normalize-space()='Maint. comm.']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_maintenance_commision;

	// Document fee comm.
	@FindBy(xpath = "//*[normalize-space()='Document fee comm.']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_document_fee_comm;

	// Total comm.
	@FindBy(xpath = "//*[normalize-space()='Total comm.']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_total_comm;

	// **********Configuration Elements**************

	// customer base over rate

	@FindBy(xpath = "//*[@id='customerBaseOverRate']")
	private WebElement quote_summary_configuration_customer_base_over_rate;

	// Customer interest rate
	@FindBy(xpath = "(//*[normalize-space()='Customer interest rate']//ancestor::div[1]//div//strong)[2]")
	private WebElement quote_summary_configuration_customer_interest_rate;

	// pannel opening
	@FindBy(xpath = "//*[@id='headingConfig']/button/div")
	private WebElement quote_summary_configuration;

	// Base Interest Rate
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Base interest rate']//ancestor::div[1]//p/strong")
	private WebElement quote_summary_base_interest_rate;

	// Finance margin
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Finance margin']//ancestor::div[1]//p/strong")
	private WebElement quote_summary_finance_margin;

	// Deductions
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Deductions']//ancestor::div[1]//p/strong")
	private WebElement quote_summary_deductions;

	// Additional Margin
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Additional margin']//ancestor::div[1]//p/strong")
	private WebElement quote_summary_additional_margin;

	// Total Margin
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Total margin']//ancestor::div[1]//p/strong")
	private WebElement quote_summary_total_margin;

	// Margin Percentage
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Default broker margin']//ancestor::div[1]//div/div/label")
	private WebElement quote_summary_default_broker_margin_percentage;

	// Maintenance Margin
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Maint. margin']//ancestor::div[1]//div//label/b")
	private WebElement quote_summary_maintenance_margin;

	// Document Fee Margin
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Document fee margin']//ancestor::div[1]//div//label/b")
	private WebElement quote_summary_decument_fee_margin;

	// Base Input Rate Input
	@FindBy(xpath = "//input[@id='baseIntRate']")
	private WebElement quote_summary_configuration_base_int_rate_input;

	// Maintenance Margin Input
	@FindBy(xpath = "//input[@id='MaintenanceMarginPer']")
	private WebElement quote_summary_configuration_maintenance_margin_input;

	// default broker Margin Input
	@FindBy(xpath = "//input[@id='defaultBrokerMargin']")
	private WebElement quote_summary_configuration_default_broker_margin_input;

	// Total tracker cost
	@FindBy(xpath = "//*[normalize-space()='Total tracker cost']//ancestor::div[1]//div//label/b")
	private WebElement quote_summary_configuration_total_tracker_cost;

	// Insurance tax
	@FindBy(xpath = "//*[normalize-space()='Insurance tax']//ancestor::div[1]//div//label/b")
	private WebElement quote_summary_configuration_insurance_tax;

	// Contingency insurance value
	@FindBy(xpath = "//*[normalize-space()='Contingency insurance value']//ancestor::div[1]//div//label/b")
	private WebElement quote_summary_configuration_contingency_insurance_value;

	@FindBy(xpath = "//*[normalize-space()='Save']")
	private WebElement quote_summary_save_button;

	Properties prop;
	
	public QuoteSummary_HPNR_CP_Page() {
		
	

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

	public boolean quote_summary_OTR_calculation_for_used_vehicle(String sheet_name)
			throws InterruptedException, IOException {

		LO.print("*************OTR Calulation on quote summary page has been started************");
		System.out.println("*************OTR Calulation on quote summary page has been started************");

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		Thread.sleep(2000);

		Click.on(driver, quote_summary, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);

		ExplicitWait.visibleElement(driver, quote_summary_cost_otr_price, 120);

		ExplicitWait.visibleElement(driver, quote_summary_cost_price_ex_vat_and_rfl, 120);

		ExplicitWait.visibleElement(driver, quote_summary_otr_vat, 120);

		LO.print("Reading values from OTR calculation -Quote Summary Page");
		System.out.println("Reading values from OTR calculation -Quote Summary Page");

		double OTR_calculation_cost_otr_price_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_cost_otr_price.getText().trim().substring(2)));

		double OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_cost_price_ex_vat_and_rfl.getText().trim().substring(2)));

		double OTR_calculation_otr_vat_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_otr_vat.getText().trim().substring(2)));

		LO.print("Cost otr price from screen is " + OTR_calculation_cost_otr_price_from_screen_converted);
		System.out.println("Cost otr price from screen is " + OTR_calculation_cost_otr_price_from_screen_converted);

		LO.print("Cost price ex vat and rfl from screen is "
				+ OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted);
		System.out.println("Cost price ex vat and rfl from screen is "
				+ OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted);

		LO.print("Otr Vat from screen is " + OTR_calculation_otr_vat_from_screen_converted);
		System.out.println("Otr Vat from screen is " + OTR_calculation_otr_vat_from_screen_converted);

		double OTR_calculation_cost_otr_price_from_excel = GetExcelFormulaValue.get_formula_value(3, 1, sheet_name);
		double OTR_calculation_cost_price_ex_vat_and_rfl_from_excel = GetExcelFormulaValue.get_formula_value(1, 1,
				sheet_name);
		double OTR_calculation_otr_vat_from_excel = GetExcelFormulaValue.get_formula_value(1, 3, sheet_name);

		LO.print("Cost otr price from excel is " + OTR_calculation_cost_otr_price_from_excel);
		System.out.println("Cost otr price from excel is " + OTR_calculation_cost_otr_price_from_excel);

		LO.print("Cost price ex vat and rfl from excel is " + OTR_calculation_cost_price_ex_vat_and_rfl_from_excel);
		System.out.println(
				"Cost price ex vat and rfl from excel is " + OTR_calculation_cost_price_ex_vat_and_rfl_from_excel);

		LO.print("Otr Vat from excel is " + OTR_calculation_otr_vat_from_excel);
		System.out.println("Otr Vat from excel is " + OTR_calculation_otr_vat_from_excel);

		double diff_otr = Difference.of_two_Double_Values(OTR_calculation_cost_otr_price_from_excel,
				OTR_calculation_cost_otr_price_from_screen_converted);
		double diff_cost_price = Difference.of_two_Double_Values(OTR_calculation_cost_price_ex_vat_and_rfl_from_excel,
				OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted);
		double diff_otr_vat = Difference.of_two_Double_Values(OTR_calculation_otr_vat_from_excel,
				OTR_calculation_otr_vat_from_screen_converted);

		int count = 0;
		boolean status = false;
		if (diff_otr < 0.2) {
			LO.print("OTR price compared");
			System.out.println("OTR price compared");
			count++;
		} else {
			LO.print("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
			System.err
					.println("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
		}

		if (diff_cost_price < 0.2) {
			LO.print("Cost price ex vat and rfl compared");
			System.out.println("Cost price ex vat and rfl compared");
			count++;
		} else {
			LO.print(
					"Found difference between (Cost price ex vat and rfl) actual and (Cost price ex vat and rfl) expected on Quote Summary Page");
			System.err.println(
					"Found difference between (Cost price ex vat and rfl) actual and (Cost price ex vat and rfl) expected on Quote Summary Page");
		}

		if (diff_otr_vat < 0.2) {
			LO.print("VAT compared");
			System.out.println("VAT compared");
			count++;
		} else {
			LO.print("Found difference between VAT actual and VAT expected on Quote Summary Page");
			System.err.println("Found difference between VAT actual and VAT expected on Quote Summary Page");
		}

		if (count == 3) {
			status = true;
		}

		return status;

	}

	public boolean quote_summary_OTR_calculation(String sheet_name) throws InterruptedException, IOException {

		LO.print("*************OTR Calulation on quote summary page has been started************");
		System.out.println("*************OTR Calulation on quote summary page has been started************");

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		Thread.sleep(2000);

		Click.on(driver, quote_summary, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);

		ExplicitWait.visibleElement(driver, quote_summary_cost_otr_price, 120);

		ExplicitWait.visibleElement(driver, quote_summary_cost_price_ex_vat_and_rfl, 120);

		ExplicitWait.visibleElement(driver, quote_summary_otr_vat, 120);

		ExplicitWait.visibleElement(driver, quote_summary_otr_rfl_and_frf, 120);

		LO.print("Reading values from OTR calculation -Quote Summary Page");
		System.out.println("Reading values from OTR calculation -Quote Summary Page");

		double OTR_calculation_cost_otr_price_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_cost_otr_price.getText().trim().substring(2)));

		double OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_cost_price_ex_vat_and_rfl.getText().trim().substring(2)));

		double OTR_calculation_otr_vat_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_otr_vat.getText().trim().substring(2)));

		double OTR_calculation_otr_rfl_and_frf_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_otr_rfl_and_frf.getText().trim().substring(2)));

		LO.print("");
		System.out.println("");

		LO.print("Cost otr price from screen is " + OTR_calculation_cost_otr_price_from_screen_converted);
		System.out.println("Cost otr price from screen is " + OTR_calculation_cost_otr_price_from_screen_converted);

		LO.print("Cost price ex vat and rfl from screen is "
				+ OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted);
		System.out.println("Cost price ex vat and rfl from screen is "
				+ OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted);

		LO.print("Otr Vat from screen is " + OTR_calculation_otr_vat_from_screen_converted);
		System.out.println("Otr Vat from screen is " + OTR_calculation_otr_vat_from_screen_converted);

		LO.print("Otr rfl and frf from screen is " + OTR_calculation_otr_rfl_and_frf_from_screen_converted);
		System.out.println("Otr rfl and frf from screen is " + OTR_calculation_otr_rfl_and_frf_from_screen_converted);

		LO.print("");
		System.out.println("");

		double OTR_calculation_cost_otr_price_from_excel = GetExcelFormulaValue.get_formula_value(14, 7, sheet_name);
		double OTR_calculation_cost_price_ex_vat_and_rfl_from_excel = GetExcelFormulaValue.get_formula_value(9, 12,
				sheet_name);
		double OTR_calculation_otr_vat_from_excel = GetExcelFormulaValue.get_formula_value(10, 7, sheet_name);
		double OTR_calculation_otr_rfl_and_frf_excel = GetExcelFormulaValue.get_formula_value(7, 12, sheet_name);

		LO.print("");
		System.out.println("");

		LO.print("Cost otr price from excel is " + OTR_calculation_cost_otr_price_from_excel);
		System.out.println("Cost otr price from excel is " + OTR_calculation_cost_otr_price_from_excel);

		LO.print("Cost price ex vat and rfl from excel is " + OTR_calculation_cost_price_ex_vat_and_rfl_from_excel);
		System.out.println(
				"Cost price ex vat and rfl from excel is " + OTR_calculation_cost_price_ex_vat_and_rfl_from_excel);

		LO.print("Otr Vat from excel is " + OTR_calculation_otr_vat_from_excel);
		System.out.println("Otr Vat from excel is " + OTR_calculation_otr_vat_from_excel);

		LO.print("Otr rfl and frf from excel is " + OTR_calculation_otr_rfl_and_frf_excel);
		System.out.println("Otr rfl and frf from excel is " + OTR_calculation_otr_rfl_and_frf_excel);

		LO.print("");
		System.out.println("");

		double diff_otr = Difference.of_two_Double_Values(OTR_calculation_cost_otr_price_from_excel,
				OTR_calculation_cost_otr_price_from_screen_converted);
		double diff_cost_price = Difference.of_two_Double_Values(OTR_calculation_cost_price_ex_vat_and_rfl_from_excel,
				OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted);
		double diff_otr_vat = Difference.of_two_Double_Values(OTR_calculation_otr_vat_from_excel,
				OTR_calculation_otr_vat_from_screen_converted);
		double diff_otr_rfl_and_frf = Difference.of_two_Double_Values(OTR_calculation_otr_rfl_and_frf_excel,
				OTR_calculation_otr_rfl_and_frf_from_screen_converted);

		int count = 0;
		boolean status = false;
		if (diff_otr < 0.2) {
			LO.print("OTR price compared");
			System.out.println("OTR price compared");
			count++;
		} else {
			LO.print("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
			System.err
					.println("Found difference between OTR actual price and OTR expected price on Quote Summary Page");
		}

		if (diff_cost_price < 0.2) {
			LO.print("Cost price ex vat and rfl compared");
			System.out.println("Cost price ex vat and rfl compared");
			count++;
		} else {
			LO.print(
					"Found difference between (Cost price ex vat and rfl) actual and (Cost price ex vat and rfl) expected on Quote Summary Page");
			System.err.println(
					"Found difference between (Cost price ex vat and rfl) actual and (Cost price ex vat and rfl) expected on Quote Summary Page");
		}

		if (diff_otr_vat < 0.2) {
			LO.print("VAT compared");
			System.out.println("VAT compared");
			count++;
		} else {
			LO.print("Found difference between VAT actual and VAT expected on Quote Summary Page");
			System.err.println("Found difference between VAT actual and VAT expected on Quote Summary Page");
		}

		if (diff_otr_rfl_and_frf < 0.2) {
			LO.print("RFL & FRF compared");
			System.out.println("RFL & FRF compared");
			count++;
		} else {
			LO.print("Found difference between RFL & FRF actual and RFL & FRF expected on Quote Summary Page");
			System.err
					.println("Found difference between RFL & FRF actual and RFL & FRF expected on Quote Summary Page");
		}

		if (count == 4) {
			status = true;
		}

		return status;

	}

	public boolean quote_summary_holding_cost_calculation_without_maintenance(String sheet_name)
			throws InterruptedException, IOException {

		LO.print("*************Holding Cost Calulation on quote summary page has been started************");
		System.out.println("*************Holding Cost Calulation on quote summary page has been started************");

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_term, 30);

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_miles_per_annum, 30);

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_monthly_finance_cost, 30);

		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 30);

		LO.print("Reading values from Holding Cost summary -Quote Summary Page");
		System.out.println("Reading values from Holding Cost summary -Quote Summary Page");

		double holding_cost_terms_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_holding_cost_term.getText().trim().substring(0, 2)));

		double holding_cost_miles_per_annum_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_holding_cost_miles_per_annum.getText().trim()));

		double holding_cost_monthly_finance_cost_from_screen_converted = Double.parseDouble(
				RemoveComma.of(quote_summary_holding_cost_monthly_finance_cost.getText().trim().substring(2)));

		double holding_cost_total_monthly_holding_cost_from_screen_converted = Double.parseDouble(RemoveComma
				.of(quote_summary_total_monthly_holding_cost.getText().trim().substring(2)));

		LO.print("");
		System.out.println("");

		LO.print("Holding cost Terms from screen" + holding_cost_terms_from_screen_converted);
		System.out.println("Holding cost Terms from screen is " + holding_cost_terms_from_screen_converted);

		LO.print("Holding cost miles per annum from screen is " + holding_cost_miles_per_annum_from_screen_converted);
		System.out.println(
				"Holding cost miles per annum from screen is " + holding_cost_miles_per_annum_from_screen_converted);

		LO.print("Holding cost monthly finance cost from screen is "
				+ holding_cost_monthly_finance_cost_from_screen_converted);
		System.out.println("Holding cost monthly finance cost from screen is "
				+ holding_cost_monthly_finance_cost_from_screen_converted);

		LO.print("Holding cost total monthly holding cost from screen is "
				+ holding_cost_total_monthly_holding_cost_from_screen_converted);
		System.out.println("Holding cost total monthly holding cost from screen is "
				+ holding_cost_total_monthly_holding_cost_from_screen_converted);

		double holding_cost_terms_from_excel = GetExcelFormulaValue.get_formula_value(51, 0, sheet_name);
		double holding_cost_miles_per_annum_from_excel = GetExcelFormulaValue.get_formula_value(50, 1, sheet_name);
		double holding_cost_monthly_finance_cost_from_excel = GetExcelFormulaValue.get_formula_value(35, 0, sheet_name);
		double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(51, 1,
				sheet_name);

		LO.print("");
		System.out.println("");

		LO.print("Holding cost Terms from excel is " + holding_cost_terms_from_excel);
		System.out.println("Holding cost Terms from excel is " + holding_cost_terms_from_excel);

		LO.print("Holding cost miles per annum from excel is " + holding_cost_miles_per_annum_from_excel);
		System.out.println("Holding cost miles per annum from excel is " + holding_cost_miles_per_annum_from_excel);

		LO.print("Holding cost monthly finance cost from excel is " + holding_cost_monthly_finance_cost_from_excel);
		System.out.println(
				"Holding cost monthly finance cost from excel is " + holding_cost_monthly_finance_cost_from_excel);

		LO.print("Holding cost total monthly holding cost from excel is "
				+ holding_cost_total_monthly_holding_cost_from_excel);
		System.out.println("Holding cost total monthly holding cost from excel is "
				+ holding_cost_total_monthly_holding_cost_from_excel);

		double diff_terms = Difference.of_two_Double_Values(holding_cost_terms_from_excel,
				holding_cost_terms_from_screen_converted);
		double diff_miles_per_annum = Difference.of_two_Double_Values(holding_cost_miles_per_annum_from_excel,
				holding_cost_miles_per_annum_from_screen_converted);
		double diff_finance_cost = Difference.of_two_Double_Values(holding_cost_monthly_finance_cost_from_excel,
				holding_cost_monthly_finance_cost_from_screen_converted);
		double diff_total_monthly_holding_cost = Difference.of_two_Double_Values(
				holding_cost_total_monthly_holding_cost_from_excel,
				holding_cost_total_monthly_holding_cost_from_screen_converted);

		int count = 0;
		boolean status = false;
		if (diff_terms < 0.2) {
			LO.print("terms compared");
			System.out.println("terms compared");
			count++;
		} else {
			LO.print("Found difference between terms actual  and terms expected ");
			System.err.println("Found difference between terms actual  and terms expected ");
		}

		if (diff_miles_per_annum < 0.2) {
			LO.print("Miles per annum compared");
			System.out.println("Miles per annum compared");
			count++;
		} else {
			LO.print(
					"Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected ");
			System.err.println(
					"Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected");
		}

		if (diff_finance_cost < 0.2) {
			LO.print("Finance cost compared");
			System.out.println("Finance cost compared");
			count++;
		} else {
			LO.print("Found difference between Finance cost actual and Finance cost expected");
			System.err.println("Found difference between Finance cost actual and Finance cost expected");
		}

		if (diff_total_monthly_holding_cost < 0.2) {
			LO.print("Total Monthly Holding Cost compared");
			System.out.println("Total Monthly Holding Cost compared");
			count++;
		} else {
			LO.print(
					"Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");
			System.err.println(
					"Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");
		}

		if (count == 4) {
			status = true;
		}

		return status;

	}

	public boolean quote_summary_holding_cost_calculation_with_maintenance(String sheet_name)
			throws InterruptedException, IOException {

		LO.print("*************Holding Cost Calulation on quote summary page has been started************");
		System.out.println("*************Holding Cost Calulation on quote summary page has been started************");

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_term, 30);

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_miles_per_annum, 30);

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_monthly_finance_cost, 30);

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_monthly_maint_cost_used, 30);

		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 30);

		LO.print("Reading values from Holding Cost summary -Quote Summary Page");
		System.out.println("Reading values from Holding Cost summary -Quote Summary Page");

		double holding_cost_terms_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_holding_cost_term.getText().trim().substring(0, 2)));

		double holding_cost_miles_per_annum_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_holding_cost_miles_per_annum.getText().trim()));

		double holding_cost_monthly_finance_cost_from_screen_converted = Double.parseDouble(
				RemoveComma.of(quote_summary_holding_cost_monthly_finance_cost.getText().trim().substring(2)));

		double holding_cost_monthly_maint_cost_used_from_screen_converted = Double.parseDouble(
				RemoveComma.of(quote_summary_holding_cost_monthly_maint_cost_used.getText().trim().substring(2)));

		double holding_cost_total_monthly_holding_cost_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_total_monthly_holding_cost.getText().trim().substring(2)));

		LO.print("");
		System.out.println("");

		LO.print("Holding cost Terms from screen" + holding_cost_terms_from_screen_converted);
		System.out.println("Holding cost Terms from screen is " + holding_cost_terms_from_screen_converted);

		LO.print("Holding cost miles per annum from screen is " + holding_cost_miles_per_annum_from_screen_converted);
		System.out.println(
				"Holding cost miles per annum from screen is " + holding_cost_miles_per_annum_from_screen_converted);

		LO.print("Holding cost monthly finance cost from screen is "
				+ holding_cost_monthly_finance_cost_from_screen_converted);
		System.out.println("Holding cost monthly finance cost from screen is "
				+ holding_cost_monthly_finance_cost_from_screen_converted);

		LO.print("Holding cost monthly maint cost used from screen is "
				+ holding_cost_monthly_maint_cost_used_from_screen_converted);
		System.out.println("Holding cost monthly maint cost used from screen is "
				+ holding_cost_monthly_maint_cost_used_from_screen_converted);

		LO.print("Holding cost total monthly holding cost from screen is "
				+ holding_cost_total_monthly_holding_cost_from_screen_converted);
		System.out.println("Holding cost total monthly holding cost from screen is "
				+ holding_cost_total_monthly_holding_cost_from_screen_converted);

		double holding_cost_terms_from_excel = GetExcelFormulaValue.get_formula_value(51, 0, sheet_name);
		double holding_cost_miles_per_annum_from_excel = GetExcelFormulaValue.get_formula_value(50, 1, sheet_name);
		double holding_cost_monthly_finance_cost_from_excel = GetExcelFormulaValue.get_formula_value(35, 0, sheet_name);
		double holding_cost_monthly_maint_cost_used_from_excel = GetExcelFormulaValue.get_formula_value(39, 1,
				sheet_name);
		double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(51, 1,
				sheet_name);

		LO.print("");
		System.out.println("");

		LO.print("Holding cost Terms from excel is " + holding_cost_terms_from_excel);
		System.out.println("Holding cost Terms from excel is " + holding_cost_terms_from_excel);

		LO.print("Holding cost miles per annum from excel is " + holding_cost_miles_per_annum_from_excel);
		System.out.println("Holding cost miles per annum from excel is " + holding_cost_miles_per_annum_from_excel);

		LO.print("Holding cost monthly finance cost from excel is " + holding_cost_monthly_finance_cost_from_excel);
		System.out.println(
				"Holding cost monthly finance cost from excel is " + holding_cost_monthly_finance_cost_from_excel);

		LO.print("Holding cost monthly maint cost used from excel is "
				+ holding_cost_monthly_maint_cost_used_from_excel);
		System.out.println("Holding cost monthly maint cost used from excel is "
				+ holding_cost_monthly_maint_cost_used_from_excel);

		LO.print("Holding cost total monthly holding cost from excel is "
				+ holding_cost_total_monthly_holding_cost_from_excel);
		System.out.println("Holding cost total monthly holding cost from excel is "
				+ holding_cost_total_monthly_holding_cost_from_excel);

		double diff_terms = Difference.of_two_Double_Values(holding_cost_terms_from_excel,
				holding_cost_terms_from_screen_converted);
		double diff_miles_per_annum = Difference.of_two_Double_Values(holding_cost_miles_per_annum_from_excel,
				holding_cost_miles_per_annum_from_screen_converted);
		double diff_finance_cost = Difference.of_two_Double_Values(holding_cost_monthly_finance_cost_from_excel,
				holding_cost_monthly_finance_cost_from_screen_converted);
		double diff_maint_cost = Difference.of_two_Double_Values(holding_cost_monthly_maint_cost_used_from_excel,
				holding_cost_monthly_maint_cost_used_from_screen_converted);
		double diff_total_monthly_holding_cost = Difference.of_two_Double_Values(
				holding_cost_total_monthly_holding_cost_from_excel,
				holding_cost_total_monthly_holding_cost_from_screen_converted);

		int count = 0;
		boolean status = false;
		if (diff_terms < 0.2) {
			LO.print("terms compared");
			System.out.println("terms compared");
			count++;
		} else {
			LO.print("Found difference between terms actual  and terms expected ");
			System.err.println("Found difference between terms actual  and terms expected ");
		}

		if (diff_miles_per_annum < 0.2) {
			LO.print("Miles per annum compared");
			System.out.println("Miles per annum compared");
			count++;
		} else {
			LO.print(
					"Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected ");
			System.err.println(
					"Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected");
		}

		if (diff_finance_cost < 0.2) {
			LO.print("Finance cost compared");
			System.out.println("Finance cost compared");
			count++;
		} else {
			LO.print("Found difference between Finance cost actual and Finance cost expected");
			System.err.println("Found difference between Finance cost actual and Finance cost expected");
		}

		if (diff_maint_cost < 0.2) {
			LO.print("Maint cost used  compared");
			System.out.println("Maint cost used compared");
			count++;
		} else {
			LO.print("Found difference between Maint cost used actual and Maint cost used expected");
			System.err.println("Found difference between Maint cost used actual and Maint cost used expected");
		}

		if (diff_total_monthly_holding_cost < 0.2) {
			LO.print("Total Monthly Holding Cost compared");
			System.out.println("Total Monthly Holding Cost compared");
			count++;
		} else {
			LO.print(
					"Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");
			System.err.println(
					"Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");
		}

		if (count == 5) {
			status = true;
		}

		return status;

	}

	public boolean quote_summary_customer_quote_summary_value_verification_without_maintenance(String sheet_name)
			throws IOException {

		LO.print("*************Customer Quote Calulation on quote summary page has been started************");
		System.out.println("*************Customer Quote Calulation on quote summary page has been started************");

		// Clicking on Customer quote pannel
		Click.on(driver, quote_summary_customer_quote_summary_value_verification, 20);

		// waiting for elements


		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_terms, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_miles_per_annum, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_basic_cash_price, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_vat, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_non_vat_items, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_cash_price, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_order_deposit, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_deposit, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_deposit, 20);
		try {
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_part_exchange_value, 20);
		} catch (Exception e1) {
		}
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_to_finance, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_charges, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_document_fee, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_payable, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_option_to_purchase_fee, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_initial_cash_payment, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_followed_by, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_monthly_finance_payment, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_guaranteed_future_value, 20);
		ExplicitWait.visibleElement(driver,
				quote_summary_customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_finance, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_vehicle_comm, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_default_finance_comm, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_document_fee_comm, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_comm, 20);

		// getting text from elements

		double customer_quote_summary_terms = Double
				.parseDouble(quote_summary_customer_quote_summary_terms.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double
				.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_miles_per_annum.getText().trim()));

		double customer_quote_summary_basic_cash_price = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_vat = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_vat.getText().trim().substring(2)));

		double customer_quote_summary_non_vat_items = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_non_vat_items.getText().trim().substring(2)));

		double customer_quote_summary_total_cash_price = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_total_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_order_deposit = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_order_deposit.getText().trim().substring(2)));

		double customer_quote_summary_finance_deposit = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_finance_deposit.getText().trim().substring(2)));

		double customer_quote_summary_total_deposit = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_total_deposit.getText().trim().substring(2)));

		double customer_quote_part_exchange_value = 0;
		try {
			customer_quote_part_exchange_value = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_part_exchange_value.getText().trim().substring(2)));
		} catch (Exception e1) {
		}

		double customer_quote_summary_balance_to_finance = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));

		double customer_quote_summary_finance_charges = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_finance_charges.getText().trim().substring(2)));

		double customer_quote_summary_document_fee = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_document_fee.getText().trim().substring(2)));

		double customer_quote_summary_balance_payable = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_balance_payable.getText().trim().substring(2)));

		double customer_quote_summary_option_to_purchase_fee = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));

		double customer_quote_summary_initial_cash_payment = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_initial_cash_payment.getText().trim().substring(2)));

		double customer_payment_followed_by = Double
				.parseDouble(quote_summary_customer_quote_summary_followed_by.getText().substring(0, 2));

		double customer_quote_summary_monthly_finance_payment = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));

		double customer_quote_summary_guaranteed_future_value = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_guaranteed_future_value.getText().trim().substring(2)));

		double customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee = Double
				.parseDouble(RemoveComma
						.of(quote_summary_customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee
								.getText().trim().substring(2)));

		double customer_quote_summary_pence_per_excess_mile_finance = Double
				.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_pence_per_excess_mile_finance
						.getText().trim().substring(0, 4)));

		double customer_quote_summary_vehicle_comm = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_vehicle_comm.getText().trim().substring(2)));

		double customer_quote_summary_default_finance_comm = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_default_finance_comm.getText().trim().substring(2)));

		double customer_quote_summary_document_fee_comm = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_document_fee_comm.getText().trim().substring(2)));

		double customer_quote_summary_total_commission = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_total_comm.getText().trim().substring(2)));

		// getting values from excel

		double terms = GetExcelFormulaValue.get_formula_value(208, 1, sheet_name);
		double miles = GetExcelFormulaValue.get_formula_value(208, 4, sheet_name);

		double basicCashPrice = GetExcelFormulaValue.get_formula_value(214, 0, sheet_name);
		double vat = GetExcelFormulaValue.get_formula_value(214, 1, sheet_name);
		double nonVATItems = GetExcelFormulaValue.get_formula_value(214, 4, sheet_name);

		double totalCashPrice = GetExcelFormulaValue.get_formula_value(217, 0, sheet_name);
		double orderDeposit = GetExcelFormulaValue.get_formula_value(217, 1, sheet_name);
		double financeDeposit = GetExcelFormulaValue.get_formula_value(217, 4, sheet_name);

		double totalDeposit = GetExcelFormulaValue.get_formula_value(220, 0, sheet_name);
		double partExchangeValue = GetExcelFormulaValue.get_formula_value(220, 1, sheet_name);
		double balanceToFinance = GetExcelFormulaValue.get_formula_value(220, 4, sheet_name);

		double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheet_name);
		double documentFee = GetExcelFormulaValue.get_string_value(223, 1, sheet_name);
		double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheet_name);

		double optionToPurchaseFee = GetExcelFormulaValue.get_formula_value(226, 0, sheet_name);
		double initialCashPayment = GetExcelFormulaValue.get_formula_value(226, 1, sheet_name);
		double followedBy = GetExcelFormulaValue.get_formula_value(226, 4, sheet_name);

		double monthlyFinancePayment = GetExcelFormulaValue.get_formula_value(229, 0, sheet_name);

		double guaranteedFutureValue = GetExcelFormulaValue.get_formula_value(232, 0, sheet_name);
		double optionalFinalPayment = GetExcelFormulaValue.get_formula_value(232, 1, sheet_name);

		double pencePerExcessMileFinance = GetExcelFormulaValue.get_formula_value(232, 4, sheet_name);

		double vehicleCommission = GetExcelFormulaValue.get_formula_value(239, 0, sheet_name);
		double defaultFinanceCommission = GetExcelFormulaValue.get_formula_value(239, 1, sheet_name);

		double docFeeCommission = GetExcelFormulaValue.get_formula_value(242, 0, sheet_name);
		double totalCommission = GetExcelFormulaValue.get_formula_value(242, 1, sheet_name);

		// comparing actul and expected values

		boolean status = false;

		int count = 0;

		// 1
		if (terms == customer_quote_summary_terms) {
			LO.print("Terms found OK");
			System.out.println("Terms found OK");
			count++;
		} else {
			LO.print("Terms found wrong");
			System.err.println("Terms found wrong");
		}

		// 2
		if (miles == customer_quote_summary_miles) {
			LO.print("miles found OK");
			System.out.println("miles found OK");
			count++;
		} else {
			LO.print("miles found wrong");
			System.err.println("miles found wrong");
		}

		// 3
		if ((Difference.of_two_Double_Values(basicCashPrice, customer_quote_summary_basic_cash_price)) < 0.2) {
			LO.print("Basic Cash Price found OK");
			System.out.println("Basic Cash Price found OK");
			count++;
		} else {
			LO.print("Basic Cash Price found wrong");
			System.err.println("Basic Cash Price found wrong");
		}

		// 4
		if ((Difference.of_two_Double_Values(vat, customer_quote_summary_vat)) < 0.2) {
			LO.print("VAT found OK");
			System.out.println("VAT found OK");
			count++;
		} else {
			LO.print("VAT found wrong");
			System.err.println("VAT found wrong");
		}

		// 5
		if ((Difference.of_two_Double_Values(nonVATItems, customer_quote_summary_non_vat_items)) < 0.2) {
			LO.print("Non VAT Items Value found OK");
			System.out.println("Non VAT Items Value found OK");
			count++;
		} else {
			LO.print("Non VAT Items Value found wrong");
			System.err.println("Non VAT Items Value found wrong");
		}

		// 6
		if ((Difference.of_two_Double_Values(totalCashPrice, customer_quote_summary_total_cash_price)) < 0.2) {
			LO.print("Total Cash Price found OK");
			System.out.println("Total Cash Price found OK");
			count++;
		} else {
			LO.print("Total Cash Price found wrong");
			System.err.println("Total Cash Price found wrong");
		}

		// 7
		if ((Difference.of_two_Double_Values(orderDeposit, customer_quote_summary_order_deposit)) < 0.2) {
			LO.print("Order Deposit found OK");
			System.out.println("Order Deposit found OK");
			count++;
		} else {
			LO.print("Order Deposit found wrong");
			System.err.println("Order Deposit found wrong");
		}

		// 8
		if ((Difference.of_two_Double_Values(financeDeposit, customer_quote_summary_finance_deposit)) < 0.2) {
			LO.print("Finance Deposit found OK");
			System.out.println("Finance Deposit found OK");
			count++;
		} else {
			LO.print("Finance Deposit found wrong");
			System.err.println("Finance Deposit found wrong");
		}

		// 9
		if ((Difference.of_two_Double_Values(totalDeposit, customer_quote_summary_total_deposit)) < 0.2) {
			LO.print("Total Deposit found OK");
			System.out.println("Total Deposit found OK");
			count++;
		} else {
			LO.print("Total Deposit found wrong");
			System.err.println("Total Deposit found wrong");
		}

		// 10
		try {
			if (partExchangeValue == customer_quote_part_exchange_value) {
				LO.print("Part Exchange Value - found OK");
				System.out.println("Part Exchange Value - found OK");
				count++;
			} else {
				LO.print("Part Exchange Value - found wrong");
				System.err.println("Part Exchange Value - found wrong");
			}
		} catch (Exception e1) {
		}

		// 11
		if ((Difference.of_two_Double_Values(balanceToFinance, customer_quote_summary_balance_to_finance)) < 0.2) {
			LO.print("Balance to Finance found OK");
			System.out.println("Balance to Finance found OK");
			count++;
		} else {
			LO.print("Balance to Finance found wrong");
			System.err.println("Balance to Finance found wrong");
		}

		// 12
		if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
			LO.print("Finance Charges - found OK");
			System.out.println("Finance Charges - found OK");
			count++;
		} else {
			LO.print("Finance Charges - found wrong");
			System.err.println("Finance Charges - found wrong");
		}

		// 13
		if ((Difference.of_two_Double_Values(documentFee, customer_quote_summary_document_fee)) < 0.2) {
			LO.print("Document Fee - found OK");
			System.out.println("Document Fee - found OK");
			count++;
		} else {
			LO.print("Document Fee - found wrong");
			System.err.println("Document Fee - found wrong");
		}

		// 14
		if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
			LO.print("Balance Payable - found OK");
			System.out.println("Balance Payable - found OK");
			count++;
		} else {
			LO.print("Balance Payable - found wrong");
			System.err.println("Balance Payable - found wrong");
		}

		// 15
		if ((Difference.of_two_Double_Values(optionToPurchaseFee,
				customer_quote_summary_option_to_purchase_fee)) < 0.2) {
			LO.print("Option To Purchase Fee - found OK");
			System.out.println("Option To Purchase Fee - found OK");
			count++;
		} else {
			LO.print("Option To Purchase Fee - found wrong");
			System.err.println("Option To Purchase Fee - found wrong");
		}

		// 16
		if ((Difference.of_two_Double_Values(initialCashPayment,
				customer_quote_summary_initial_cash_payment)) < 0.2) {
			LO.print("Initial Cash Payment - found OK");
			System.out.println("Initial Cash Payment - found OK");
			count++;
		} else {
			LO.print("Initial Cash Payment - found wrong");
			System.err.println("Initial Cash Payment - found wrong");
		}

		// 17
		if (followedBy == customer_payment_followed_by) {
			LO.print("Followed By months - found OK");
			System.out.println("Followed By months - found OK");
			count++;
		} else {
			LO.print("Followed By months - found wrong");
			System.err.println("Followed By months - found wrong");
		}

		// 18
		if ((Difference.of_two_Double_Values(monthlyFinancePayment,
				customer_quote_summary_monthly_finance_payment)) < 0.2) {
			LO.print("Monthly Finance Payment - found OK");
			System.out.println("Monthly Finance Payment - found OK");
			count++;
		} else {
			LO.print("Monthly Finance Payment - found wrong");
			System.err.println("Monthly Finance Payment - found wrong");
		}

		// 19
		if ((Difference.of_two_Double_Values(guaranteedFutureValue,
				customer_quote_summary_guaranteed_future_value)) < 0.2) {
			LO.print("Guaranteed Future Value - found OK");
			System.out.println("Guaranteed Future Value - found OK");
			count++;
		} else {
			LO.print("Guaranteed Future Value - found wrong");
			System.err.println("Guaranteed Future Value - found wrong");
		}

		// 20
		if ((Difference.of_two_Double_Values(optionalFinalPayment,
				customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee)) < 0.2) {
			LO.print("Optional Final Payment - found OK");
			System.out.println("Optional Final Payment - found OK");
			count++;
		} else {
			LO.print("Optional Final Payment - found wrong");
			System.err.println("Optional Final Payment - found wrong");
		}

		// 21
		if ((Difference.of_two_Double_Values(pencePerExcessMileFinance,
				customer_quote_summary_pence_per_excess_mile_finance)) < 0.2) {
			LO.print("Pence Per Excess Mile Finance - found OK");
			System.out.println("Pence Per Excess Mile Finance - found OK");
			count++;
		} else {
			LO.print("Pence Per Excess Mile Finance - found wrong");
			System.err.println("Pence Per Excess Mile Finance - found wrong");
		}

		// 22
		if ((Difference.of_two_Double_Values(vehicleCommission, customer_quote_summary_vehicle_comm)) < 0.2) {
			LO.print("Vehicle Commission - found OK");
			System.out.println("Vehicle Commission - found OK");
			count++;
		} else {
			LO.print("Vehicle Commission - found wrong");
			System.err.println("Vehicle Commission - found wrong");
		}

		// 23
		if ((Difference.of_two_Double_Values(defaultFinanceCommission,
				customer_quote_summary_default_finance_comm)) < 0.2) {
			LO.print("Default Finance Commission - found OK");
			System.out.println("Default Finance Commission - found OK");
			count++;
		} else {
			LO.print("Default Finance Commission - found wrong");
			System.err.println("Default Finance Commission - found wrong");
		}

		// 24
		if ((Difference.of_two_Double_Values(docFeeCommission, customer_quote_summary_document_fee_comm)) < 0.2) {
			LO.print("Document Fee Commission - found OK");
			System.out.println("Document Fee Commission - found OK");
			count++;
		} else {
			LO.print("Document Fee Commission - found wrong");
			System.err.println("Document Fee Commission - found wrong");
		}

		// 25
		if ((Difference.of_two_Double_Values(totalCommission, customer_quote_summary_total_commission)) < 0.2) {
			LO.print("Total Commission - found OK");
			System.out.println("Total Commission - found OK");
			count++;
		} else {
			LO.print("Total Commission - found wrong");
			System.err.println("Total Commission - found wrong");
		}

		if (count == 25) {
			status = true;
		}
		return status;
	}

	public boolean quote_summary_customer_quote_summary_value_verification_with_maintenance(String sheet_name)
			throws IOException {

		LO.print("*************Customer Quote Calulation on quote summary page has been started************");
		System.out.println("*************Customer Quote Calulation on quote summary page has been started************");

		// Clicking on Customer quote pannel
		Click.on(driver, quote_summary_customer_quote_summary_value_verification, 20);

		// waiting for elements
		try {
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_terms, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_miles_per_annum, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_basic_cash_price, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_vat, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_non_vat_items, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_cash_price, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_order_deposit, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_deposit, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_deposit, 20);
			try {
				ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_part_exchange_value, 20);
			} catch (Exception e) {
			}
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_to_finance, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_charges, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_document_fee, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_payable, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_option_to_purchase_fee, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_initial_cash_payment, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_followed_by, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_monthly_finance_payment, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_monthly_maint_payment, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_monthly_payment, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_guaranteed_future_value, 20);
			ExplicitWait.visibleElement(driver,
					quote_summary_customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_finance, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_maint, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_total, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_vehicle_comm, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_default_finance_comm, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_maintenance_commision, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_document_fee_comm, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_comm, 20);

			// getting text from elements

			double customer_quote_summary_terms = Double
					.parseDouble(quote_summary_customer_quote_summary_terms.getText().trim().substring(0, 2));

			double customer_quote_summary_miles = Double
					.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_miles_per_annum.getText().trim()));

			double customer_quote_summary_basic_cash_price = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

			double customer_quote_summary_vat = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_vat.getText().trim().substring(2)));

			double customer_quote_summary_non_vat_items = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_non_vat_items.getText().trim().substring(2)));

			double customer_quote_summary_total_cash_price = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_total_cash_price.getText().trim().substring(2)));

			double customer_quote_summary_order_deposit = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_order_deposit.getText().trim().substring(2)));

			double customer_quote_summary_finance_deposit = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_finance_deposit.getText().trim().substring(2)));

			double customer_quote_summary_total_deposit = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_total_deposit.getText().trim().substring(2)));

			double customer_quote_part_exchange_value = 0;
			try {
				customer_quote_part_exchange_value = Double.parseDouble(RemoveComma
						.of(quote_summary_customer_quote_summary_part_exchange_value.getText().trim().substring(2)));
			} catch (Exception e) {
			}

			double customer_quote_summary_balance_to_finance = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));

			double customer_quote_summary_finance_charges = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_finance_charges.getText().trim().substring(2)));

			double customer_quote_summary_document_fee = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_document_fee.getText().trim().substring(2)));

			double customer_quote_summary_balance_payable = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_balance_payable.getText().trim().substring(2)));

			double customer_quote_summary_option_to_purchase_fee = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));

			double customer_quote_summary_initial_cash_payment = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_initial_cash_payment.getText().trim().substring(2)));

			double customer_payment_followed_by = Double
					.parseDouble(quote_summary_customer_quote_summary_followed_by.getText().substring(0, 2));

			double customer_quote_summary_monthly_finance_payment = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));

			double customer_quote_summary_monthly_maint_payment = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_monthly_maint_payment.getText().trim().substring(2)));

			double customer_quote_summary_total_monthly_payment = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_total_monthly_payment.getText().trim().substring(2)));

			double customer_quote_summary_guaranteed_future_value = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_guaranteed_future_value.getText().trim().substring(2)));

			double customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee = Double
					.parseDouble(RemoveComma
							.of(quote_summary_customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee
									.getText().trim().substring(2)));

			double customer_quote_summary_pence_per_excess_mile_finance = Double
					.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_pence_per_excess_mile_finance
							.getText().trim().substring(0, 4)));

			double customer_quote_summary_pence_per_excess_mile_maint = Double.parseDouble(RemoveComma.of(
					quote_summary_customer_quote_summary_pence_per_excess_mile_maint.getText().trim().substring(0, 4)));

			double customer_quote_summary_pence_per_excess_mile_total = Double.parseDouble(RemoveComma.of(
					quote_summary_customer_quote_summary_pence_per_excess_mile_total.getText().trim().substring(0, 4)));

			double customer_quote_summary_vehicle_comm = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_vehicle_comm.getText().trim().substring(2)));

			double customer_quote_summary_default_finance_comm = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_default_finance_comm.getText().trim().substring(2)));

			double customer_quote_summary_maintenance_commision = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_maintenance_commision.getText().trim().substring(2)));

			double customer_quote_summary_document_fee_comm = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_document_fee_comm.getText().trim().substring(2)));

			double customer_quote_summary_total_commission = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_total_comm.getText().trim().substring(2)));

			// getting values from excel

			double terms = GetExcelFormulaValue.get_formula_value(208, 1, sheet_name);
			double miles = GetExcelFormulaValue.get_formula_value(208, 4, sheet_name);

			double basicCashPrice = GetExcelFormulaValue.get_formula_value(214, 0, sheet_name);
			double vat = GetExcelFormulaValue.get_formula_value(214, 1, sheet_name);
			double nonVATItems = GetExcelFormulaValue.get_formula_value(214, 4, sheet_name);

			double totalCashPrice = GetExcelFormulaValue.get_formula_value(217, 0, sheet_name);
			double orderDeposit = GetExcelFormulaValue.get_formula_value(217, 1, sheet_name);
			double financeDeposit = GetExcelFormulaValue.get_formula_value(217, 4, sheet_name);

			double totalDeposit = GetExcelFormulaValue.get_formula_value(220, 0, sheet_name);
			double partExchangeValue = GetExcelFormulaValue.get_formula_value(220, 1, sheet_name);
			double balanceToFinance = GetExcelFormulaValue.get_formula_value(220, 4, sheet_name);

			double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheet_name);
			double documentFee = GetExcelFormulaValue.get_string_value(223, 1, sheet_name);
			double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheet_name);

			double optionToPurchaseFee = GetExcelFormulaValue.get_formula_value(226, 0, sheet_name);
			double initialCashPayment = GetExcelFormulaValue.get_formula_value(226, 1, sheet_name);
			double followedBy = GetExcelFormulaValue.get_formula_value(226, 4, sheet_name);

			double monthlyFinancePayment = GetExcelFormulaValue.get_formula_value(229, 0, sheet_name);
			double monthlyMaintenancePayment = GetExcelFormulaValue.get_formula_value(229, 1, sheet_name);
			double totalMonthlyPayment = GetExcelFormulaValue.get_formula_value(229, 4, sheet_name);

			double guaranteedFutureValue = GetExcelFormulaValue.get_formula_value(232, 0, sheet_name);
			double optionalFinalPayment = GetExcelFormulaValue.get_formula_value(232, 1, sheet_name);
			double pencePerExcessMileFinance = GetExcelFormulaValue.get_formula_value(232, 4, sheet_name);
			double pencePerExcessMileMaintenance = GetExcelFormulaValue.get_formula_value(235, 0, sheet_name);
			double pencePerExcessMileTotal = GetExcelFormulaValue.get_formula_value(235, 1, sheet_name);

			double vehicleCommission = GetExcelFormulaValue.get_formula_value(239, 0, sheet_name);
			double defaultFinanceCommission = GetExcelFormulaValue.get_formula_value(239, 1, sheet_name);
			double maintCommission = GetExcelFormulaValue.get_formula_value(239, 4, sheet_name);

			double docFeeCommission = GetExcelFormulaValue.get_formula_value(242, 0, sheet_name);
			double totalCommission = GetExcelFormulaValue.get_formula_value(242, 1, sheet_name);

			// comparing actul and expected values

			boolean status = false;

			int count = 0;

			// 1
			if (terms == customer_quote_summary_terms) {
				LO.print("Terms found OK");
				System.out.println("Terms found OK");
				count++;
			} else {
				LO.print("Terms found wrong");
				System.err.println("Terms found wrong");
			}

			// 2
			if (miles == customer_quote_summary_miles) {
				LO.print("miles found OK");
				System.out.println("miles found OK");
				count++;
			} else {
				LO.print("miles found wrong");
				System.err.println("miles found wrong");
			}

			// 3
			if ((Difference.of_two_Double_Values(basicCashPrice, customer_quote_summary_basic_cash_price)) < 0.2) {
				LO.print("Basic Cash Price found OK");
				System.out.println("Basic Cash Price found OK");
				count++;
			} else {
				LO.print("Basic Cash Price found wrong");
				System.err.println("Basic Cash Price found wrong");
			}

			// 4
			if ((Difference.of_two_Double_Values(vat, customer_quote_summary_vat)) < 0.2) {
				LO.print("VAT found OK");
				System.out.println("VAT found OK");
				count++;
			} else {
				LO.print("VAT found wrong");
				System.err.println("VAT found wrong");
			}

			// 5
			if ((Difference.of_two_Double_Values(nonVATItems, customer_quote_summary_non_vat_items)) < 0.2) {
				LO.print("Non VAT Items Value found OK");
				System.out.println("Non VAT Items Value found OK");
				count++;
			} else {
				LO.print("Non VAT Items Value found wrong");
				System.err.println("Non VAT Items Value found wrong");
			}

			// 6
			if ((Difference.of_two_Double_Values(totalCashPrice, customer_quote_summary_total_cash_price)) < 0.2) {
				LO.print("Total Cash Price found OK");
				System.out.println("Total Cash Price found OK");
				count++;
			} else {
				LO.print("Total Cash Price found wrong");
				System.err.println("Total Cash Price found wrong");
			}

			// 7
			if ((Difference.of_two_Double_Values(orderDeposit, customer_quote_summary_order_deposit)) < 0.2) {
				LO.print("Order Deposit found OK");
				System.out.println("Order Deposit found OK");
				count++;
			} else {
				LO.print("Order Deposit found wrong");
				System.err.println("Order Deposit found wrong");
			}

			// 8
			if ((Difference.of_two_Double_Values(financeDeposit, customer_quote_summary_finance_deposit)) < 0.2) {
				LO.print("Finance Deposit found OK");
				System.out.println("Finance Deposit found OK");
				count++;
			} else {
				LO.print("Finance Deposit found wrong");
				System.err.println("Finance Deposit found wrong");
			}

			// 9
			if ((Difference.of_two_Double_Values(totalDeposit, customer_quote_summary_total_deposit)) < 0.2) {
				LO.print("Total Deposit found OK");
				System.out.println("Total Deposit found OK");
				count++;
			} else {
				LO.print("Total Deposit found wrong");
				System.err.println("Total Deposit found wrong");
			}

			// 10
			try {
				if (partExchangeValue == customer_quote_part_exchange_value) {
					LO.print("Part Exchange Value - found OK");
					System.out.println("Part Exchange Value - found OK");
					count++;
				} else {
					LO.print("Part Exchange Value - found wrong");
					System.err.println("Part Exchange Value - found wrong");
				}
			} catch (Exception e) {
			}

			// 11
			if ((Difference.of_two_Double_Values(balanceToFinance, customer_quote_summary_balance_to_finance)) < 0.2) {
				LO.print("Balance to Finance found OK");
				System.out.println("Balance to Finance found OK");
				count++;
			} else {
				LO.print("Balance to Finance found wrong");
				System.err.println("Balance to Finance found wrong");
			}

			// 12
			if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
				LO.print("Finance Charges - found OK");
				System.out.println("Finance Charges - found OK");
				count++;
			} else {
				LO.print("Finance Charges - found wrong");
				System.err.println("Finance Charges - found wrong");
			}

			// 13
			if ((Difference.of_two_Double_Values(documentFee, customer_quote_summary_document_fee)) < 0.2) {
				LO.print("Document Fee - found OK");
				System.out.println("Document Fee - found OK");
				count++;
			} else {
				LO.print("Document Fee - found wrong");
				System.err.println("Document Fee - found wrong");
			}

			// 14
			if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
				LO.print("Balance Payable - found OK");
				System.out.println("Balance Payable - found OK");
				count++;
			} else {
				LO.print("Balance Payable - found wrong");
				System.err.println("Balance Payable - found wrong");
			}

			// 15
			if ((Difference.of_two_Double_Values(optionToPurchaseFee,
					customer_quote_summary_option_to_purchase_fee)) < 0.2) {
				LO.print("Option To Purchase Fee - found OK");
				System.out.println("Option To Purchase Fee - found OK");
				count++;
			} else {
				LO.print("Option To Purchase Fee - found wrong");
				System.err.println("Option To Purchase Fee - found wrong");
			}

			// 16
			if ((Difference.of_two_Double_Values(initialCashPayment,
					customer_quote_summary_initial_cash_payment)) < 0.2) {
				LO.print("Initial Cash Payment - found OK");
				System.out.println("Initial Cash Payment - found OK");
				count++;
			} else {
				LO.print("Initial Cash Payment - found wrong");
				System.err.println("Initial Cash Payment - found wrong");
			}

			// 17
			if (followedBy == customer_payment_followed_by) {
				LO.print("Followed By months - found OK");
				System.out.println("Followed By months - found OK");
				count++;
			} else {
				LO.print("Followed By months - found wrong");
				System.err.println("Followed By months - found wrong");
			}

			// 18
			if ((Difference.of_two_Double_Values(monthlyFinancePayment,
					customer_quote_summary_monthly_finance_payment)) < 0.2) {
				LO.print("Monthly Finance Payment - found OK");
				System.out.println("Monthly Finance Payment - found OK");
				count++;
			} else {
				LO.print("Monthly Finance Payment - found wrong");
				System.err.println("Monthly Finance Payment - found wrong");
			}

			// 19
			if ((Difference.of_two_Double_Values(monthlyMaintenancePayment,
					customer_quote_summary_monthly_maint_payment)) < 0.2) {
				LO.print("Monthly Maint Payment - found OK");
				System.out.println("Monthly Maint Payment - found OK");
				count++;
			} else {
				LO.print("Monthly Maint Payment - found wrong");
				System.err.println("Monthly Maint Payment - found wrong");
			}

			// 20
			if ((Difference.of_two_Double_Values(totalMonthlyPayment,
					customer_quote_summary_total_monthly_payment)) < 0.2) {
				LO.print("Monthly Total Payment - found OK");
				System.out.println("Monthly Total Payment - found OK");
				count++;
			} else {
				LO.print("Monthly Total Payment - found wrong");
				System.err.println("Monthly Total Payment - found wrong");
			}

			// 21
			if ((Difference.of_two_Double_Values(guaranteedFutureValue,
					customer_quote_summary_guaranteed_future_value)) < 0.2) {
				LO.print("Guaranteed Future Value - found OK");
				System.out.println("Guaranteed Future Value - found OK");
				count++;
			} else {
				LO.print("Guaranteed Future Value - found wrong");
				System.err.println("Guaranteed Future Value - found wrong");
			}

			// 22
			if ((Difference.of_two_Double_Values(optionalFinalPayment,
					customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee)) < 0.2) {
				LO.print("Optional Final Payment - found OK");
				System.out.println("Optional Final Payment - found OK");
				count++;
			} else {
				LO.print("Optional Final Payment - found wrong");
				System.err.println("Optional Final Payment - found wrong");
			}

			// 23
			if ((Difference.of_two_Double_Values(pencePerExcessMileFinance,
					customer_quote_summary_pence_per_excess_mile_finance)) < 0.2) {
				LO.print("Pence Per Excess Mile Finance - found OK");
				System.out.println("Pence Per Excess Mile Finance - found OK");
				count++;
			} else {
				LO.print("Pence Per Excess Mile Finance - found wrong");
				System.err.println("Pence Per Excess Mile Finance - found wrong");
			}

			// 24
			if ((Difference.of_two_Double_Values(pencePerExcessMileMaintenance,
					customer_quote_summary_pence_per_excess_mile_maint)) < 0.2) {
				LO.print("Pence Per Excess Mile Maintenance - found OK");
				System.out.println("Pence Per Excess Mile Maintenance - found OK");
				count++;
			} else {
				LO.print("Pence Per Excess Mile Maintenance - found wrong");
				System.err.println("Pence Per Excess Mile Maintenance - found wrong");
			}

			// 25
			if ((Difference.of_two_Double_Values(pencePerExcessMileTotal,
					customer_quote_summary_pence_per_excess_mile_total)) < 0.2) {
				LO.print("Pence Per Excess Mile Total - found OK");
				System.out.println("Pence Per Excess Mile Total - found OK");
				count++;
			} else {
				LO.print("Pence Per Excess Mile Total - found wrong");
				System.err.println("Pence Per Excess Mile Total - found wrong");
			}

			// 26
			if ((Difference.of_two_Double_Values(vehicleCommission, customer_quote_summary_vehicle_comm)) < 0.2) {
				LO.print("Vehicle Commission - found OK");
				System.out.println("Vehicle Commission - found OK");
				count++;
			} else {
				LO.print("Vehicle Commission - found wrong");
				System.err.println("Vehicle Commission - found wrong");
			}

			// 27
			if ((Difference.of_two_Double_Values(defaultFinanceCommission,
					customer_quote_summary_default_finance_comm)) < 0.2) {
				LO.print("Default Finance Commission - found OK");
				System.out.println("Default Finance Commission - found OK");
				count++;
			} else {
				LO.print("Default Finance Commission - found wrong");
				System.err.println("Default Finance Commission - found wrong");
			}

			// 28
			if ((Difference.of_two_Double_Values(maintCommission,
					customer_quote_summary_maintenance_commision)) < 0.2) {
				LO.print("Maintenance Commission - found OK");
				System.out.println("Maintenance Commission - found OK");
				count++;
			} else {
				LO.print("Maintenance Commission - found wrong");
				System.err.println("Maintenance Commission - found wrong");
			}

			// 29
			if ((Difference.of_two_Double_Values(docFeeCommission, customer_quote_summary_document_fee_comm)) < 0.2) {
				LO.print("Document Fee Commission - found OK");
				System.out.println("Document Fee Commission - found OK");
				count++;
			} else {
				LO.print("Document Fee Commission - found wrong");
				System.err.println("Document Fee Commission - found wrong");
			}

			// 30
			if ((Difference.of_two_Double_Values(totalCommission, customer_quote_summary_total_commission)) < 0.2) {
				LO.print("Total Commission - found OK");
				System.out.println("Total Commission - found OK");
				count++;
			} else {
				LO.print("Total Commission - found wrong");
				System.err.println("Total Commission - found wrong");
			}

			if (count == 30) {
				status = true;
			}
			return status;
		} catch (Exception e) {
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_terms, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_miles_per_annum, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_basic_cash_price, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_vat, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_non_vat_items, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_cash_price, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_order_deposit, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_deposit, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_deposit, 20);
			try {
				ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_part_exchange_value, 20);
			} catch (Exception e1) {
			}
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_to_finance, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_charges, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_document_fee, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_payable, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_option_to_purchase_fee, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_initial_cash_payment, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_followed_by, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_monthly_finance_payment, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_guaranteed_future_value, 20);
			ExplicitWait.visibleElement(driver,
					quote_summary_customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_finance, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_vehicle_comm, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_default_finance_comm, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_document_fee_comm, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_comm, 20);

			// getting text from elements

			double customer_quote_summary_terms = Double
					.parseDouble(quote_summary_customer_quote_summary_terms.getText().trim().substring(0, 2));

			double customer_quote_summary_miles = Double
					.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_miles_per_annum.getText().trim()));

			double customer_quote_summary_basic_cash_price = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

			double customer_quote_summary_vat = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_vat.getText().trim().substring(2)));

			double customer_quote_summary_non_vat_items = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_non_vat_items.getText().trim().substring(2)));

			double customer_quote_summary_total_cash_price = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_total_cash_price.getText().trim().substring(2)));

			double customer_quote_summary_order_deposit = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_order_deposit.getText().trim().substring(2)));

			double customer_quote_summary_finance_deposit = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_finance_deposit.getText().trim().substring(2)));

			double customer_quote_summary_total_deposit = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_total_deposit.getText().trim().substring(2)));

			double customer_quote_part_exchange_value = 0;
			try {
				customer_quote_part_exchange_value = Double.parseDouble(RemoveComma
						.of(quote_summary_customer_quote_summary_part_exchange_value.getText().trim().substring(2)));
			} catch (Exception e1) {
			}

			double customer_quote_summary_balance_to_finance = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));

			double customer_quote_summary_finance_charges = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_finance_charges.getText().trim().substring(2)));

			double customer_quote_summary_document_fee = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_document_fee.getText().trim().substring(2)));

			double customer_quote_summary_balance_payable = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_balance_payable.getText().trim().substring(2)));

			double customer_quote_summary_option_to_purchase_fee = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));

			double customer_quote_summary_initial_cash_payment = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_initial_cash_payment.getText().trim().substring(2)));

			double customer_payment_followed_by = Double
					.parseDouble(quote_summary_customer_quote_summary_followed_by.getText().substring(0, 2));

			double customer_quote_summary_monthly_finance_payment = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));

			double customer_quote_summary_guaranteed_future_value = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_guaranteed_future_value.getText().trim().substring(2)));

			double customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee = Double
					.parseDouble(RemoveComma
							.of(quote_summary_customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee
									.getText().trim().substring(2)));

			double customer_quote_summary_pence_per_excess_mile_finance = Double
					.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_pence_per_excess_mile_finance
							.getText().trim().substring(0, 4)));

			double customer_quote_summary_vehicle_comm = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_vehicle_comm.getText().trim().substring(2)));

			double customer_quote_summary_default_finance_comm = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_default_finance_comm.getText().trim().substring(2)));

			double customer_quote_summary_document_fee_comm = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_document_fee_comm.getText().trim().substring(2)));

			double customer_quote_summary_total_commission = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_total_comm.getText().trim().substring(2)));

			// getting values from excel

			double terms = GetExcelFormulaValue.get_formula_value(208, 1, sheet_name);
			double miles = GetExcelFormulaValue.get_formula_value(208, 4, sheet_name);

			double basicCashPrice = GetExcelFormulaValue.get_formula_value(214, 0, sheet_name);
			double vat = GetExcelFormulaValue.get_formula_value(214, 1, sheet_name);
			double nonVATItems = GetExcelFormulaValue.get_formula_value(214, 4, sheet_name);

			double totalCashPrice = GetExcelFormulaValue.get_formula_value(217, 0, sheet_name);
			double orderDeposit = GetExcelFormulaValue.get_formula_value(217, 1, sheet_name);
			double financeDeposit = GetExcelFormulaValue.get_formula_value(217, 4, sheet_name);

			double totalDeposit = GetExcelFormulaValue.get_formula_value(220, 0, sheet_name);
			double partExchangeValue = GetExcelFormulaValue.get_formula_value(220, 1, sheet_name);
			double balanceToFinance = GetExcelFormulaValue.get_formula_value(220, 4, sheet_name);

			double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheet_name);
			double documentFee = GetExcelFormulaValue.get_string_value(223, 1, sheet_name);
			double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheet_name);

			double optionToPurchaseFee = GetExcelFormulaValue.get_formula_value(226, 0, sheet_name);
			double initialCashPayment = GetExcelFormulaValue.get_formula_value(226, 1, sheet_name);
			double followedBy = GetExcelFormulaValue.get_formula_value(226, 4, sheet_name);

			double monthlyFinancePayment = GetExcelFormulaValue.get_formula_value(229, 0, sheet_name);

			double guaranteedFutureValue = GetExcelFormulaValue.get_formula_value(232, 0, sheet_name);
			double optionalFinalPayment = GetExcelFormulaValue.get_formula_value(232, 1, sheet_name);

			double pencePerExcessMileFinance = GetExcelFormulaValue.get_formula_value(232, 4, sheet_name);

			double vehicleCommission = GetExcelFormulaValue.get_formula_value(239, 0, sheet_name);
			double defaultFinanceCommission = GetExcelFormulaValue.get_formula_value(239, 1, sheet_name);

			double docFeeCommission = GetExcelFormulaValue.get_formula_value(242, 0, sheet_name);
			double totalCommission = GetExcelFormulaValue.get_formula_value(242, 1, sheet_name);

			// comparing actul and expected values

			boolean status = false;

			int count = 0;

			// 1
			if (terms == customer_quote_summary_terms) {
				LO.print("Terms found OK");
				System.out.println("Terms found OK");
				count++;
			} else {
				LO.print("Terms found wrong");
				System.err.println("Terms found wrong");
			}

			// 2
			if (miles == customer_quote_summary_miles) {
				LO.print("miles found OK");
				System.out.println("miles found OK");
				count++;
			} else {
				LO.print("miles found wrong");
				System.err.println("miles found wrong");
			}

			// 3
			if ((Difference.of_two_Double_Values(basicCashPrice, customer_quote_summary_basic_cash_price)) < 0.2) {
				LO.print("Basic Cash Price found OK");
				System.out.println("Basic Cash Price found OK");
				count++;
			} else {
				LO.print("Basic Cash Price found wrong");
				System.err.println("Basic Cash Price found wrong");
			}

			// 4
			if ((Difference.of_two_Double_Values(vat, customer_quote_summary_vat)) < 0.2) {
				LO.print("VAT found OK");
				System.out.println("VAT found OK");
				count++;
			} else {
				LO.print("VAT found wrong");
				System.err.println("VAT found wrong");
			}

			// 5
			if ((Difference.of_two_Double_Values(nonVATItems, customer_quote_summary_non_vat_items)) < 0.2) {
				LO.print("Non VAT Items Value found OK");
				System.out.println("Non VAT Items Value found OK");
				count++;
			} else {
				LO.print("Non VAT Items Value found wrong");
				System.err.println("Non VAT Items Value found wrong");
			}

			// 6
			if ((Difference.of_two_Double_Values(totalCashPrice, customer_quote_summary_total_cash_price)) < 0.2) {
				LO.print("Total Cash Price found OK");
				System.out.println("Total Cash Price found OK");
				count++;
			} else {
				LO.print("Total Cash Price found wrong");
				System.err.println("Total Cash Price found wrong");
			}

			// 7
			if ((Difference.of_two_Double_Values(orderDeposit, customer_quote_summary_order_deposit)) < 0.2) {
				LO.print("Order Deposit found OK");
				System.out.println("Order Deposit found OK");
				count++;
			} else {
				LO.print("Order Deposit found wrong");
				System.err.println("Order Deposit found wrong");
			}

			// 8
			if ((Difference.of_two_Double_Values(financeDeposit, customer_quote_summary_finance_deposit)) < 0.2) {
				LO.print("Finance Deposit found OK");
				System.out.println("Finance Deposit found OK");
				count++;
			} else {
				LO.print("Finance Deposit found wrong");
				System.err.println("Finance Deposit found wrong");
			}

			// 9
			if ((Difference.of_two_Double_Values(totalDeposit, customer_quote_summary_total_deposit)) < 0.2) {
				LO.print("Total Deposit found OK");
				System.out.println("Total Deposit found OK");
				count++;
			} else {
				LO.print("Total Deposit found wrong");
				System.err.println("Total Deposit found wrong");
			}

			// 10
			try {
				if (partExchangeValue == customer_quote_part_exchange_value) {
					LO.print("Part Exchange Value - found OK");
					System.out.println("Part Exchange Value - found OK");
					count++;
				} else {
					LO.print("Part Exchange Value - found wrong");
					System.err.println("Part Exchange Value - found wrong");
				}
			} catch (Exception e1) {
			}

			// 11
			if ((Difference.of_two_Double_Values(balanceToFinance, customer_quote_summary_balance_to_finance)) < 0.2) {
				LO.print("Balance to Finance found OK");
				System.out.println("Balance to Finance found OK");
				count++;
			} else {
				LO.print("Balance to Finance found wrong");
				System.err.println("Balance to Finance found wrong");
			}

			// 12
			if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
				LO.print("Finance Charges - found OK");
				System.out.println("Finance Charges - found OK");
				count++;
			} else {
				LO.print("Finance Charges - found wrong");
				System.err.println("Finance Charges - found wrong");
			}

			// 13
			if ((Difference.of_two_Double_Values(documentFee, customer_quote_summary_document_fee)) < 0.2) {
				LO.print("Document Fee - found OK");
				System.out.println("Document Fee - found OK");
				count++;
			} else {
				LO.print("Document Fee - found wrong");
				System.err.println("Document Fee - found wrong");
			}

			// 14
			if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
				LO.print("Balance Payable - found OK");
				System.out.println("Balance Payable - found OK");
				count++;
			} else {
				LO.print("Balance Payable - found wrong");
				System.err.println("Balance Payable - found wrong");
			}

			// 15
			if ((Difference.of_two_Double_Values(optionToPurchaseFee,
					customer_quote_summary_option_to_purchase_fee)) < 0.2) {
				LO.print("Option To Purchase Fee - found OK");
				System.out.println("Option To Purchase Fee - found OK");
				count++;
			} else {
				LO.print("Option To Purchase Fee - found wrong");
				System.err.println("Option To Purchase Fee - found wrong");
			}

			// 16
			if ((Difference.of_two_Double_Values(initialCashPayment,
					customer_quote_summary_initial_cash_payment)) < 0.2) {
				LO.print("Initial Cash Payment - found OK");
				System.out.println("Initial Cash Payment - found OK");
				count++;
			} else {
				LO.print("Initial Cash Payment - found wrong");
				System.err.println("Initial Cash Payment - found wrong");
			}

			// 17
			if (followedBy == customer_payment_followed_by) {
				LO.print("Followed By months - found OK");
				System.out.println("Followed By months - found OK");
				count++;
			} else {
				LO.print("Followed By months - found wrong");
				System.err.println("Followed By months - found wrong");
			}

			// 18
			if ((Difference.of_two_Double_Values(monthlyFinancePayment,
					customer_quote_summary_monthly_finance_payment)) < 0.2) {
				LO.print("Monthly Finance Payment - found OK");
				System.out.println("Monthly Finance Payment - found OK");
				count++;
			} else {
				LO.print("Monthly Finance Payment - found wrong");
				System.err.println("Monthly Finance Payment - found wrong");
			}

			// 19
			if ((Difference.of_two_Double_Values(guaranteedFutureValue,
					customer_quote_summary_guaranteed_future_value)) < 0.2) {
				LO.print("Guaranteed Future Value - found OK");
				System.out.println("Guaranteed Future Value - found OK");
				count++;
			} else {
				LO.print("Guaranteed Future Value - found wrong");
				System.err.println("Guaranteed Future Value - found wrong");
			}

			// 20
			if ((Difference.of_two_Double_Values(optionalFinalPayment,
					customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee)) < 0.2) {
				LO.print("Optional Final Payment - found OK");
				System.out.println("Optional Final Payment - found OK");
				count++;
			} else {
				LO.print("Optional Final Payment - found wrong");
				System.err.println("Optional Final Payment - found wrong");
			}

			// 21
			if ((Difference.of_two_Double_Values(pencePerExcessMileFinance,
					customer_quote_summary_pence_per_excess_mile_finance)) < 0.2) {
				LO.print("Pence Per Excess Mile Finance - found OK");
				System.out.println("Pence Per Excess Mile Finance - found OK");
				count++;
			} else {
				LO.print("Pence Per Excess Mile Finance - found wrong");
				System.err.println("Pence Per Excess Mile Finance - found wrong");
			}

			// 22
			if ((Difference.of_two_Double_Values(vehicleCommission, customer_quote_summary_vehicle_comm)) < 0.2) {
				LO.print("Vehicle Commission - found OK");
				System.out.println("Vehicle Commission - found OK");
				count++;
			} else {
				LO.print("Vehicle Commission - found wrong");
				System.err.println("Vehicle Commission - found wrong");
			}

			// 23
			if ((Difference.of_two_Double_Values(defaultFinanceCommission,
					customer_quote_summary_default_finance_comm)) < 0.2) {
				LO.print("Default Finance Commission - found OK");
				System.out.println("Default Finance Commission - found OK");
				count++;
			} else {
				LO.print("Default Finance Commission - found wrong");
				System.err.println("Default Finance Commission - found wrong");
			}

			// 24
			if ((Difference.of_two_Double_Values(docFeeCommission, customer_quote_summary_document_fee_comm)) < 0.2) {
				LO.print("Document Fee Commission - found OK");
				System.out.println("Document Fee Commission - found OK");
				count++;
			} else {
				LO.print("Document Fee Commission - found wrong");
				System.err.println("Document Fee Commission - found wrong");
			}

			// 25
			if ((Difference.of_two_Double_Values(totalCommission, customer_quote_summary_total_commission)) < 0.2) {
				LO.print("Total Commission - found OK");
				System.out.println("Total Commission - found OK");
				count++;
			} else {
				LO.print("Total Commission - found wrong");
				System.err.println("Total Commission - found wrong");
			}

			if (count == 25) {
				status = true;
			}
			return status;
		}

	}

	public boolean quote_summary_configuration_value_verification_without_maintenance(String sheet_name)
			throws IOException, UnsupportedFlavorException {

		LO.print("*************Configuration Values Verification on quote summary page has been started************");
		System.out.println(
				"*************Configuration Values Verification on quote summary page has been started************");

		Click.on(driver, quote_summary_configuration, 30);

		// reading configuration values from screen

		ExplicitWait.visibleElement(driver, quote_summary_base_interest_rate, 20);
		double baseInterestRateFromScreen = Double
				.parseDouble(quote_summary_base_interest_rate.getText().trim().substring(0, 5));

		ExplicitWait.visibleElement(driver, quote_summary_finance_margin, 20);
		double financeMarginFromScreen = Double
				.parseDouble(RemoveComma.of(quote_summary_finance_margin.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_deductions, 20);
		double deductionsFromScreen = Double
				.parseDouble(RemoveComma.of(quote_summary_deductions.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_additional_margin, 20);
		double additionalMarginFromScreen = Double
				.parseDouble(RemoveComma.of(quote_summary_additional_margin.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_total_margin, 20);
		double totalMarginFromScreen = Double
				.parseDouble(RemoveComma.of(quote_summary_total_margin.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_default_broker_margin_percentage, 20);
		double defaultBrokerMarginPercentageFromScreen = Double
				.parseDouble(quote_summary_default_broker_margin_percentage.getText().trim().substring(0, 4));

		ExplicitWait.visibleElement(driver, quote_summary_configuration_customer_interest_rate, 20);
		double customerInterestRateFromScreen = Double
				.parseDouble(quote_summary_configuration_customer_interest_rate.getText().trim().substring(0, 5));

		ExplicitWait.visibleElement(driver, quote_summary_decument_fee_margin, 20);
		double documentFeeMarginFromScreen = Double
				.parseDouble(RemoveComma.of(quote_summary_decument_fee_margin.getText().trim().substring(2)));

		// copying default broker margin from input field

		ExplicitWait.visibleElement(driver, quote_summary_configuration_default_broker_margin_input, 30);
		quote_summary_configuration_default_broker_margin_input.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String temp_default_broker_margin_copied = (String) clipboard.getData(DataFlavor.stringFlavor);
		double default_broker_margin_copied = Double.parseDouble(temp_default_broker_margin_copied);

//		ExplicitWait.visibleElement(driver, quote_summary_configuration_total_tracker_cost, 20);
//		double trackerCostFromScreen = Double.parseDouble(RemoveComma.of(quote_summary_configuration_total_tracker_cost.getText().trim().substring(2)));
//
//		ExplicitWait.visibleElement(driver, quote_summary_configuration_insurance_tax, 20);
//		double insuranceTaxFromScreen = Double.parseDouble(RemoveComma.of(quote_summary_configuration_insurance_tax.getText().trim().substring(0,4)));
//	
//		ExplicitWait.visibleElement(driver, quote_summary_configuration_contingency_insurance_value, 20);
//		double contingencyInsuranceValueFromScreen = Double.parseDouble(RemoveComma.of(quote_summary_configuration_contingency_insurance_value.getText().trim().substring(2)));
//	

		// getting values from excel

		double tempbaseInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(256, 01, sheet_name);

		double baseInterestRateFromExcel = (tempbaseInterestRateFromExcel * 100);

		double financeMarginFromExcel = GetExcelFormulaValue.get_formula_value(259, 5, sheet_name);

		double deductionsFromExcel = GetExcelFormulaValue.get_formula_value(253, 5, sheet_name);

		double additionalMarginFromExcel = GetExcelFormulaValue.get_formula_value(254, 1, sheet_name);

		double totalMarginFromExcel = GetExcelFormulaValue.get_formula_value(254, 5, sheet_name);

		double tempdefaualtBrokerMarginPercentageFromExcel = GetExcelFormulaValue.get_formula_value(260, 1, sheet_name);

		double defaualtBrokerMarginPercentageFromExcel = (tempdefaualtBrokerMarginPercentageFromExcel * 100);

		double tempcustomerInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(259, 1, sheet_name);
		double customerInterestRateFromExcel = (tempcustomerInterestRateFromExcel * 100);

		double documentFeeMarginFromExcel = GetExcelFormulaValue.get_formula_value(262, 1, sheet_name);

		double defaultBrokerMarginFromExcel = GetExcelFormulaValue.get_formula_value(260, 5, sheet_name);

//	  double trackerCostFromExcel = GetExcelFormulaValue.get_formula_value(0,
//	  0, sheet_name);
//	  
//	  double insuranceTaxFromExcel = GetExcelFormulaValue.get_formula_value(0,
//	  0, sheet_name);
//	  
//	  double contingencyInsuranceValueFromExcel = GetExcelFormulaValue.get_formula_value(0,
//	  0, sheet_name);
//	  
//	  
		// verifying actual and expected values

		int count = 0;
		boolean status = false;
		// 1
		if (baseInterestRateFromExcel == baseInterestRateFromScreen) {
			LO.print("Base Interest Rate found OK");
			System.out.println("Base Interest Rate found OK");
			count++;
		} else {
			LO.print("Base Interest Rate found wrong");
			System.err.println("Base Interest Rate found wrong");
		}

		// 2
		if (Difference.of_two_Double_Values(financeMarginFromScreen, financeMarginFromExcel) < 0.2) {
			LO.print("Finance Margin found OK");
			System.out.println("Finance Margin found OK");
			count++;
		} else {
			LO.print("Finance Margin found wrong");
			System.err.println("Finance Margin found wrong");
		}

		// 3
		if (Difference.of_two_Double_Values(deductionsFromScreen, deductionsFromExcel) < 0.2) {
			LO.print("Deductions found OK");
			System.out.println("Deductions found OK");
			count++;
		} else {
			LO.print("Deductions found wrong");
			System.err.println("Deductions found wrong");
		}

		// 4
		if (Difference.of_two_Double_Values(additionalMarginFromScreen, additionalMarginFromExcel) < 0.2) {
			LO.print("Additional Margin found OK");
			System.out.println("Additional Margin found OK");
			count++;
		} else {
			LO.print("Additional Margin found wrong");
			System.err.println("Additional Margin found wrong");
		}

		// 5
		if (Difference.of_two_Double_Values(totalMarginFromScreen, totalMarginFromExcel) < 0.2) {
			LO.print("Total Margin found OK");
			System.out.println("Total Margin found OK");
			count++;
		} else {
			LO.print("Total Margin found wrong");
			System.err.println("Total Margin found wrong");
		}

		// 6
		if (Difference.of_two_Double_Values(defaualtBrokerMarginPercentageFromExcel,
				defaultBrokerMarginPercentageFromScreen) < 0.2) {
			LO.print("Default Broker Margin percentage found OK");
			System.out.println("Default Broker Margin percentage found OK");
			count++;
		} else {
			LO.print("Default Broker Margin percentage found wrong");
			System.err.println("Default Broker Margin percentage found wrong");
		}

		// 7
		if (Difference.of_two_Double_Values(customerInterestRateFromScreen, customerInterestRateFromExcel) < 0.2) {
			LO.print("Customer Interest Rate found OK");
			System.out.println("Customer Interest Rate found OK");
			count++;
		} else {
			LO.print("Customer Interest Rate found wrong");
			System.err.println("Customer Interest Rate found wrong");
		}

		// 8
		if (Difference.of_two_Double_Values(documentFeeMarginFromScreen, documentFeeMarginFromExcel) < 0.2) {
			LO.print("Document Fee Margin found OK");
			System.out.println("Document Fee Margin found OK");
			count++;
		} else {
			LO.print("Document Fee Margin  found wrong");
			System.err.println("Document Fee Margin  found wrong");
		}
		// 9
		if ((Difference.of_two_Double_Values(default_broker_margin_copied, defaultBrokerMarginFromExcel) < 0.2)) {
			LO.print("Default Broker Margin found OK");
			System.out.println("Default Broker Margin found OK");
			count++;
		} else {
			LO.print("Default Broker Margin  found wrong");
			System.err.println("Default Broker Margin  found wrong");
		}

//        //9
//		if (trackerCostFromScreen==trackerCostFromExcel) {
//			LO.print("Tracker Cost found OK");
//			System.out.println("Tracker Cost found OK");
//			count++;
//		} else {
//			LO.print("Tracker Cost  found wrong");
//			System.err.println("Tracker Cost  found wrong");
//		}
//		
//		//10
//		if (insuranceTaxFromScreen==insuranceTaxFromExcel) {
//			LO.print("Insurance Tax found OK");
//			System.out.println("Insurance Tax found OK");
//			count++;
//		} else {
//			LO.print("Insurance Tax found wrong");
//			System.err.println("Insurance Tax found wrong");
//		}
//		
//		//11
//		if (contingencyInsuranceValueFromScreen==contingencyInsuranceValueFromExcel) {
//			LO.print("Contingency Insurance Value found OK");
//			System.out.println("Contingency Insurance Value found OK");
//			count++;
//		} else {
//			LO.print("Contingency Insurance Value found wrong");
//			System.err.println("Contingency Insurance Value found wrong");
//		}

		if (count == 9) {
			status = true;
		}
		return status;
	}

	public boolean quote_summary_configuration_value_verification_with_maintenance(String sheet_name)
			throws IOException, UnsupportedFlavorException {
		LO.print("*************Configuration Values Verification on quote summary page has been started************");
		System.out.println(
				"*************Configuration Values Verification on quote summary page has been started************");

		Click.on(driver, quote_summary_configuration, 30);

		// reading configuration values from screen

		try {

			ExplicitWait.visibleElement(driver, quote_summary_base_interest_rate, 20);
			double baseInterestRateFromScreen = Double
					.parseDouble(quote_summary_base_interest_rate.getText().trim().substring(0, 5));

			ExplicitWait.visibleElement(driver, quote_summary_finance_margin, 20);
			double financeMarginFromScreen = Double
					.parseDouble(RemoveComma.of(quote_summary_finance_margin.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_deductions, 20);
			double deductionsFromScreen = Double
					.parseDouble(RemoveComma.of(quote_summary_deductions.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_additional_margin, 20);
			double additionalMarginFromScreen = Double
					.parseDouble(RemoveComma.of(quote_summary_additional_margin.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_total_margin, 20);
			double totalMarginFromScreen = Double
					.parseDouble(RemoveComma.of(quote_summary_total_margin.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_default_broker_margin_percentage, 20);
			double defaultBrokerMarginPercentageFromScreen = Double
					.parseDouble(quote_summary_default_broker_margin_percentage.getText().trim().substring(0, 4));

			ExplicitWait.visibleElement(driver, quote_summary_configuration_customer_interest_rate, 20);
			double customerInterestRateFromScreen = Double
					.parseDouble(quote_summary_configuration_customer_interest_rate.getText().trim().substring(0, 4));

			ExplicitWait.visibleElement(driver, quote_summary_maintenance_margin, 20);
			double maintenanceMarginFromScreen = Double
					.parseDouble(quote_summary_maintenance_margin.getText().trim().substring(2));

			ExplicitWait.visibleElement(driver, quote_summary_decument_fee_margin, 20);
			double documentFeeMarginFromScreen = Double
					.parseDouble(RemoveComma.of(quote_summary_decument_fee_margin.getText().trim().substring(2)));

			// copying default broker margin from input field

			ExplicitWait.visibleElement(driver, quote_summary_configuration_default_broker_margin_input, 30);
			quote_summary_configuration_default_broker_margin_input.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			String temp_default_broker_margin_copied = (String) clipboard.getData(DataFlavor.stringFlavor);
			double default_broker_margin_copied = Double.parseDouble(temp_default_broker_margin_copied);

//					ExplicitWait.visibleElement(driver, quote_summary_configuration_total_tracker_cost, 20);
//					double trackerCostFromScreen = Double.parseDouble(RemoveComma.of(quote_summary_configuration_total_tracker_cost.getText().trim().substring(2)));
//
//					ExplicitWait.visibleElement(driver, quote_summary_configuration_insurance_tax, 20);
//					double insuranceTaxFromScreen = Double.parseDouble(RemoveComma.of(quote_summary_configuration_insurance_tax.getText().trim().substring(0,4)));
//				
//					ExplicitWait.visibleElement(driver, quote_summary_configuration_contingency_insurance_value, 20);
//					double contingencyInsuranceValueFromScreen = Double.parseDouble(RemoveComma.of(quote_summary_configuration_contingency_insurance_value.getText().trim().substring(2)));
//				

			// getting values from excel

			double tempbaseInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(256, 1, sheet_name);

			double baseInterestRateFromExcel = (tempbaseInterestRateFromExcel * 100);

			double financeMarginFromExcel = GetExcelFormulaValue.get_formula_value(259, 5, sheet_name);

			double deductionsFromExcel = GetExcelFormulaValue.get_formula_value(253, 5, sheet_name);

			double additionalMarginFromExcel = GetExcelFormulaValue.get_formula_value(254, 1, sheet_name);

			double totalMarginFromExcel = GetExcelFormulaValue.get_formula_value(254, 5, sheet_name);

			double tempdefaualtBrokerMarginPercentageFromExcel = GetExcelFormulaValue.get_formula_value(260, 1,
					sheet_name);

			double defaualtBrokerMarginPercentageFromExcel = (tempdefaualtBrokerMarginPercentageFromExcel * 100);

			double tempcustomerInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(259, 1, sheet_name);
			double customerInterestRateFromExcel = (tempcustomerInterestRateFromExcel * 100);

			double maintenanceMarginFromExcel = GetExcelFormulaValue.get_formula_value(261, 5, sheet_name);

			double documentFeeMarginFromExcel = GetExcelFormulaValue.get_formula_value(262, 1, sheet_name);

			double defaultBrokerMarginFromExcel = GetExcelFormulaValue.get_formula_value(260, 5, sheet_name);

//				  
//				  double trackerCostFromExcel = GetExcelFormulaValue.get_formula_value(266,5, sheet_name);
//				  
//				  double insuranceTaxFromExcel = GetExcelFormulaValue.get_formula_value(267,5, sheet_name);
//				  
//				  double contingencyInsuranceValueFromExcel = GetExcelFormulaValue.get_formula_value(268,5, sheet_name);
//				  

			// verifying actual and expected values

			int count = 0;
			boolean status = false;
			// 1
			if (baseInterestRateFromExcel == baseInterestRateFromScreen) {
				LO.print("Base Interest Rate found OK");
				System.out.println("Base Interest Rate found OK");
				count++;
			} else {
				LO.print("Base Interest Rate found wrong");
				System.err.println("Base Interest Rate found wrong");
			}

			// 2
			if (Difference.of_two_Double_Values(financeMarginFromScreen, financeMarginFromExcel) < 0.2) {
				LO.print("Finance Margin found OK");
				System.out.println("Finance Margin found OK");
				count++;
			} else {
				LO.print("Finance Margin found wrong");
				System.err.println("Finance Margin found wrong");
			}

			// 3
			if (Difference.of_two_Double_Values(deductionsFromScreen, deductionsFromExcel) < 0.2) {
				LO.print("Deductions found OK");
				System.out.println("Deductions found OK");
				count++;
			} else {
				LO.print("Deductions found wrong");
				System.err.println("Deductions found wrong");
			}

			// 4
			if (Difference.of_two_Double_Values(additionalMarginFromScreen, additionalMarginFromExcel) < 0.2) {
				LO.print("Additional Margin found OK");
				System.out.println("Additional Margin found OK");
				count++;
			} else {
				LO.print("Additional Margin found wrong");
				System.err.println("Additional Margin found wrong");
			}

			// 5
			if (Difference.of_two_Double_Values(totalMarginFromScreen, totalMarginFromExcel) < 0.2) {
				LO.print("Total Margin found OK");
				System.out.println("Total Margin found OK");
				count++;
			} else {
				LO.print("Total Margin found wrong");
				System.err.println("Total Margin found wrong");
			}

			// 6
			if (Difference.of_two_Double_Values(defaualtBrokerMarginPercentageFromExcel,
					defaultBrokerMarginPercentageFromScreen) < 0.2) {
				LO.print("Default Broker Margin percentage found OK");
				System.out.println("Default Broker Margin percentage found OK");
				count++;
			} else {
				LO.print("Default Broker Margin percentage found wrong");
				System.err.println("Default Broker Margin percentage found wrong");
			}

			// 7
			if (Difference.of_two_Double_Values(customerInterestRateFromScreen, customerInterestRateFromExcel) < 0.2) {
				LO.print("Customer Interest Rate found OK");
				System.out.println("Customer Interest Rate found OK");
				count++;
			} else {
				LO.print("Customer Interest Rate found wrong");
				System.err.println("Customer Interest Rate found wrong");
			}

			// 8
			if ((Difference.of_two_Double_Values(maintenanceMarginFromScreen, maintenanceMarginFromExcel)) < 0.2) {
				LO.print("Maintenance Margin found OK");
				System.out.println("Maintenance Margin found OK");
				count++;
			} else {
				LO.print("Maintenance Margin found wrong");
				System.err.println("Maintenance Margin  found wrong");
			}
			// 9
			if ((Difference.of_two_Double_Values(documentFeeMarginFromScreen, documentFeeMarginFromExcel) < 0.2)) {
				LO.print("Document Fee Margin found OK");
				System.out.println("Document Fee Margin found OK");
				count++;
			} else {
				LO.print("Document Fee Margin  found wrong");
				System.err.println("Document Fee Margin  found wrong");
			}

			// 10
			if ((Difference.of_two_Double_Values(default_broker_margin_copied, defaultBrokerMarginFromExcel) < 0.2)) {
				LO.print("Default Broker Margin found OK");
				System.out.println("Default Broker Margin found OK");
				count++;
			} else {
				LO.print("Default Broker Margin  found wrong");
				System.err.println("Default Broker Margin  found wrong");
			}

//			        //10
//					if (trackerCostFromScreen==trackerCostFromExcel) {
//						LO.print("Tracker Cost found OK");
//						System.out.println("Tracker Cost found OK");
//						count++;
//					} else {
//						LO.print("Tracker Cost  found wrong");
//						System.err.println("Tracker Cost  found wrong");
//					}
//					
//					//11
//					if (insuranceTaxFromScreen==insuranceTaxFromExcel) {
//						LO.print("Insurance Tax found OK");
//						System.out.println("Insurance Tax found OK");
//						count++;
//					} else {
//						LO.print("Insurance Tax found wrong");
//						System.err.println("Insurance Tax found wrong");
//					}
//					
//					//12
//					if (contingencyInsuranceValueFromScreen==contingencyInsuranceValueFromExcel) {
//						LO.print("Contingency Insurance Value found OK");
//						System.out.println("Contingency Insurance Value found OK");
//						count++;
//					} else {
//						LO.print("Contingency Insurance Value found wrong");
//						System.err.println("Contingency Insurance Value found wrong");
//					}

			if (count == 10) {
				status = true;
			}
			return status;
		} catch (Exception e) {
			ExplicitWait.visibleElement(driver, quote_summary_base_interest_rate, 20);
			double baseInterestRateFromScreen = Double
					.parseDouble(quote_summary_base_interest_rate.getText().trim().substring(0, 5));

			ExplicitWait.visibleElement(driver, quote_summary_finance_margin, 20);
			double financeMarginFromScreen = Double
					.parseDouble(RemoveComma.of(quote_summary_finance_margin.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_deductions, 20);
			double deductionsFromScreen = Double
					.parseDouble(RemoveComma.of(quote_summary_deductions.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_additional_margin, 20);
			double additionalMarginFromScreen = Double
					.parseDouble(RemoveComma.of(quote_summary_additional_margin.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_total_margin, 20);
			double totalMarginFromScreen = Double
					.parseDouble(RemoveComma.of(quote_summary_total_margin.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_default_broker_margin_percentage, 20);
			double defaultBrokerMarginPercentageFromScreen = Double
					.parseDouble(quote_summary_default_broker_margin_percentage.getText().trim().substring(0, 4));

			ExplicitWait.visibleElement(driver, quote_summary_configuration_customer_interest_rate, 20);
			double customerInterestRateFromScreen = Double
					.parseDouble(quote_summary_configuration_customer_interest_rate.getText().trim().substring(0, 5));

			ExplicitWait.visibleElement(driver, quote_summary_decument_fee_margin, 20);
			double documentFeeMarginFromScreen = Double
					.parseDouble(RemoveComma.of(quote_summary_decument_fee_margin.getText().trim().substring(2)));

			// copying default broker margin from input field

			ExplicitWait.visibleElement(driver, quote_summary_configuration_default_broker_margin_input, 30);
			quote_summary_configuration_default_broker_margin_input.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			String temp_default_broker_margin_copied = (String) clipboard.getData(DataFlavor.stringFlavor);
			double default_broker_margin_copied = Double.parseDouble(temp_default_broker_margin_copied);

//			ExplicitWait.visibleElement(driver, quote_summary_configuration_total_tracker_cost, 20);
//			double trackerCostFromScreen = Double.parseDouble(RemoveComma.of(quote_summary_configuration_total_tracker_cost.getText().trim().substring(2)));
			//
//			ExplicitWait.visibleElement(driver, quote_summary_configuration_insurance_tax, 20);
//			double insuranceTaxFromScreen = Double.parseDouble(RemoveComma.of(quote_summary_configuration_insurance_tax.getText().trim().substring(0,4)));
			//
//			ExplicitWait.visibleElement(driver, quote_summary_configuration_contingency_insurance_value, 20);
//			double contingencyInsuranceValueFromScreen = Double.parseDouble(RemoveComma.of(quote_summary_configuration_contingency_insurance_value.getText().trim().substring(2)));
			//

			// getting values from excel

			double tempbaseInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(256, 01, sheet_name);

			double baseInterestRateFromExcel = (tempbaseInterestRateFromExcel * 100);

			double financeMarginFromExcel = GetExcelFormulaValue.get_formula_value(259, 5, sheet_name);

			double deductionsFromExcel = GetExcelFormulaValue.get_formula_value(253, 5, sheet_name);

			double additionalMarginFromExcel = GetExcelFormulaValue.get_formula_value(254, 1, sheet_name);

			double totalMarginFromExcel = GetExcelFormulaValue.get_formula_value(254, 5, sheet_name);

			double tempdefaualtBrokerMarginPercentageFromExcel = GetExcelFormulaValue.get_formula_value(260, 1,
					sheet_name);

			double defaualtBrokerMarginPercentageFromExcel = (tempdefaualtBrokerMarginPercentageFromExcel * 100);

			double tempcustomerInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(259, 1, sheet_name);
			double customerInterestRateFromExcel = (tempcustomerInterestRateFromExcel * 100);

			double documentFeeMarginFromExcel = GetExcelFormulaValue.get_formula_value(262, 1, sheet_name);

			double defaultBrokerMarginFromExcel = GetExcelFormulaValue.get_formula_value(260, 5, sheet_name);

//		  double trackerCostFromExcel = GetExcelFormulaValue.get_formula_value(0,
//		  0, sheet_name);
//		  
//		  double insuranceTaxFromExcel = GetExcelFormulaValue.get_formula_value(0,
//		  0, sheet_name);
//		  
//		  double contingencyInsuranceValueFromExcel = GetExcelFormulaValue.get_formula_value(0,
//		  0, sheet_name);
//		  
//		  
			// verifying actual and expected values

			int count = 0;
			boolean status = false;
			// 1
			if (baseInterestRateFromExcel == baseInterestRateFromScreen) {
				LO.print("Base Interest Rate found OK");
				System.out.println("Base Interest Rate found OK");
				count++;
			} else {
				LO.print("Base Interest Rate found wrong");
				System.err.println("Base Interest Rate found wrong");
			}

			// 2
			if (Difference.of_two_Double_Values(financeMarginFromScreen, financeMarginFromExcel) < 0.2) {
				LO.print("Finance Margin found OK");
				System.out.println("Finance Margin found OK");
				count++;
			} else {
				LO.print("Finance Margin found wrong");
				System.err.println("Finance Margin found wrong");
			}

			// 3
			if (Difference.of_two_Double_Values(deductionsFromScreen, deductionsFromExcel) < 0.2) {
				LO.print("Deductions found OK");
				System.out.println("Deductions found OK");
				count++;
			} else {
				LO.print("Deductions found wrong");
				System.err.println("Deductions found wrong");
			}

			// 4
			if (Difference.of_two_Double_Values(additionalMarginFromScreen, additionalMarginFromExcel) < 0.2) {
				LO.print("Additional Margin found OK");
				System.out.println("Additional Margin found OK");
				count++;
			} else {
				LO.print("Additional Margin found wrong");
				System.err.println("Additional Margin found wrong");
			}

			// 5
			if (Difference.of_two_Double_Values(totalMarginFromScreen, totalMarginFromExcel) < 0.2) {
				LO.print("Total Margin found OK");
				System.out.println("Total Margin found OK");
				count++;
			} else {
				LO.print("Total Margin found wrong");
				System.err.println("Total Margin found wrong");
			}

			// 6
			if (Difference.of_two_Double_Values(defaualtBrokerMarginPercentageFromExcel,
					defaultBrokerMarginPercentageFromScreen) < 0.2) {
				LO.print("Default Broker Margin percentage found OK");
				System.out.println("Default Broker Margin percentage found OK");
				count++;
			} else {
				LO.print("Default Broker Margin percentage found wrong");
				System.err.println("Default Broker Margin percentage found wrong");
			}

			// 7
			if (Difference.of_two_Double_Values(customerInterestRateFromScreen, customerInterestRateFromExcel) < 0.2) {
				LO.print("Customer Interest Rate found OK");
				System.out.println("Customer Interest Rate found OK");
				count++;
			} else {
				LO.print("Customer Interest Rate found wrong");
				System.err.println("Customer Interest Rate found wrong");
			}

			// 8
			if (Difference.of_two_Double_Values(documentFeeMarginFromScreen, documentFeeMarginFromExcel) < 0.2) {
				LO.print("Document Fee Margin found OK");
				System.out.println("Document Fee Margin found OK");
				count++;
			} else {
				LO.print("Document Fee Margin  found wrong");
				System.err.println("Document Fee Margin  found wrong");
			}
			// 9
			if ((Difference.of_two_Double_Values(default_broker_margin_copied, defaultBrokerMarginFromExcel) < 0.2)) {
				LO.print("Default Broker Margin found OK");
				System.out.println("Default Broker Margin found OK");
				count++;
			} else {
				LO.print("Default Broker Margin  found wrong");
				System.err.println("Default Broker Margin  found wrong");
			}

//	        //9
//			if (trackerCostFromScreen==trackerCostFromExcel) {
//				LO.print("Tracker Cost found OK");
//				System.out.println("Tracker Cost found OK");
//				count++;
//			} else {
//				LO.print("Tracker Cost  found wrong");
//				System.err.println("Tracker Cost  found wrong");
//			}
//			
//			//10
//			if (insuranceTaxFromScreen==insuranceTaxFromExcel) {
//				LO.print("Insurance Tax found OK");
//				System.out.println("Insurance Tax found OK");
//				count++;
//			} else {
//				LO.print("Insurance Tax found wrong");
//				System.err.println("Insurance Tax found wrong");
//			}
//			
//			//11
//			if (contingencyInsuranceValueFromScreen==contingencyInsuranceValueFromExcel) {
//				LO.print("Contingency Insurance Value found OK");
//				System.out.println("Contingency Insurance Value found OK");
//				count++;
//			} else {
//				LO.print("Contingency Insurance Value found wrong");
//				System.err.println("Contingency Insurance Value found wrong");
//			}

			if (count == 9) {
				status = true;
			}
			return status;

		}
	}

	public boolean quote_summary_edit_base_int_rate_value_verification_without_maintenance(String sheet_name)
			throws IOException, InterruptedException {

		LO.print(
				"*************Editing and Verifying Configuration Values on quote summary page has been started************");
		System.out.println(
				"*************Editing and Verifying Configuration Values on quote summary page has been started************");

		// Edit base interest rate configuration values from screen
		ExplicitWait.visibleElement(driver, quote_summary_configuration_base_int_rate_input, 30);
		quote_summary_configuration_base_int_rate_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		quote_summary_configuration_base_int_rate_input.sendKeys("7.0");

		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		LO.print("Base Interest Rate changed to 7.0 %");
		System.out.println("Base Interest Rate changed to 7.0 %");

		// Getting values from screen

		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 30);

		double holding_cost_total_monthly_holding_cost_from_screen = Double.parseDouble(RemoveComma
				.of(quote_summary_total_monthly_holding_cost.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 30);

		double customer_quote_summary_monthly_finance_rental_from_screen = Double
				.parseDouble(RemoveComma.of(quote_summary_monthly_finance_rental.getText().trim().substring(2)));
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_charges, 20);

		double customer_quote_summary_finance_charges = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_finance_charges.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_payable, 20);
		double customer_quote_summary_balance_payable = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_balance_payable.getText().trim().substring(2)));

		// writing values to excel

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(34).getCell(10).setCellValue(0.07);
		wb.getSheet(sheet_name).getRow(62).getCell(1).setCellValue(0.07);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		// getting values from excel

		double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(51, 1,
				sheet_name);

		double monthlyFinanceRental = GetExcelFormulaValue.get_formula_value(166, 0, sheet_name);

		double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheet_name);

		double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheet_name);

		// verifying actual and expected values

		int count = 0;

		boolean status = false;
		// 1
		if (Difference.of_two_Double_Values(holding_cost_total_monthly_holding_cost_from_screen,
				holding_cost_total_monthly_holding_cost_from_excel) < 0.2) {
			LO.print("Holding Cost after changing Base Int. Rate -  found OK");
			System.out.println("Holding Cost after changing Base Int. Rate -  found OK");
			count++;
		} else {
			LO.print("Holding Cost after changing Base Int. Rate -  found wrong");
			System.err.println("Holding Cost after changing Base Int. Rate -  found wrong");
		}
		// 2
		if (Difference.of_two_Double_Values(customer_quote_summary_monthly_finance_rental_from_screen,
				monthlyFinanceRental) < 0.2) {
			LO.print("Monthly Finance Rental after changing Base Int. Rate -  found OK");
			System.out.println("Monthly Finance Rental after changing Base Int. Rate -  found OK");
			count++;
		} else {
			LO.print("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
			System.err.println("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
		}
		// 3
		if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
			LO.print("Finance Charges - found OK");
			System.out.println("Finance Charges - found OK");
			count++;
		} else {
			LO.print("Finance Charges - found wrong");
			System.err.println("Finance Charges - found wrong");
		}

		// 4
		if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
			LO.print("Balance Payable - found OK");
			System.out.println("Balance Payable - found OK");
			count++;
		} else {
			LO.print("Balance Payable - found wrong");
			System.err.println("Balance Payable - found wrong");
		}

		if (count == 4) {
			status = true;
		}

		ExplicitWait.visibleElement(driver, quote_summary_configuration_base_int_rate_input, 30);
		quote_summary_configuration_base_int_rate_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		String default_base_rate =  String.valueOf((Double.parseDouble(prop.getProperty("base_rate"))*100));
		quote_summary_configuration_base_int_rate_input.sendKeys(default_base_rate);

		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		LO.print("Base Interest Rate changed to default");
		System.out.println("Base Interest Rate changed to default");

		// writing values to excel

		FileInputStream in1 = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb1 = new XSSFWorkbook(in1);

		wb1.getSheet(sheet_name).getRow(34).getCell(10).setCellValue(Double.parseDouble(prop.getProperty("base_rate")));
		wb1.getSheet(sheet_name).getRow(62).getCell(1).setCellValue(Double.parseDouble(prop.getProperty("base_rate")));

		FileOutputStream out1 = new FileOutputStream(prop.getProperty("formula_excel_path"));

		wb1.write(out1);

		return status;
	}

	public boolean quote_summary_edit_base_int_rate_value_verification_with_maintenance(String sheet_name)
			throws IOException, InterruptedException {

		LO.print(
				"*************Editing Base Interest Rate and Verifying  Values on quote summary page has been started************");
		System.out.println(
				"*************Editing Base Interest Rate and Verifying  Values on quote summary page has been started************");

		// Edit base interest rate configuration values from screen
		ExplicitWait.visibleElement(driver, quote_summary_configuration_base_int_rate_input, 30);
		quote_summary_configuration_base_int_rate_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		quote_summary_configuration_base_int_rate_input.sendKeys("7.0");

		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		LO.print("Base Interest Rate changed to 7.0 %");
		System.out.println("Base Interest Rate changed to 7.0 %");

		// Getting values from screen

		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 30);

		double holding_cost_total_monthly_holding_cost_from_screen = Double
				.parseDouble(RemoveComma.of(quote_summary_total_monthly_holding_cost.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 30);
		double customer_quote_summary_monthly_finance_rental_from_screen = Double
				.parseDouble(RemoveComma.of(quote_summary_monthly_finance_rental.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_monthly_maint_payment, 30);
		double customer_quote_summary_monthly_maint_rental_from_screen = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_monthly_maint_payment.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_charges, 20);

		double customer_quote_summary_finance_charges = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_finance_charges.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_payable, 20);
		double customer_quote_summary_balance_payable = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_balance_payable.getText().trim().substring(2)));

		// writing values to excel

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(34).getCell(10).setCellValue(0.07);
		wb.getSheet(sheet_name).getRow(62).getCell(1).setCellValue(0.07);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		// getting values from excel

		double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(51, 1,
				sheet_name);

		double monthlyFinanceRental = GetExcelFormulaValue.get_formula_value(166, 0, sheet_name);

		double monthlyMaintRental = GetExcelFormulaValue.get_formula_value(166, 1, sheet_name);

		double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheet_name);

		double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheet_name);

		// verifying actual and expected values

		int count = 0;

		boolean status = false;

		// 1
		if (Difference.of_two_Double_Values(holding_cost_total_monthly_holding_cost_from_screen,
				holding_cost_total_monthly_holding_cost_from_excel) < 0.2) {
			LO.print("Holding Cost after changing Base Int. Rate -  found OK");
			System.out.println("Holding Cost after changing Base Int. Rate -  found OK");
			count++;
		} else {
			LO.print("Holding Cost after changing Base Int. Rate -  found wrong");
			System.err.println("Holding Cost after changing Base Int. Rate -  found wrong");
		}
		// 2
		if (Difference.of_two_Double_Values(customer_quote_summary_monthly_finance_rental_from_screen,
				monthlyFinanceRental) < 0.2) {
			LO.print("Monthly Finance Rental after changing Base Int. Rate -  found OK");
			System.out.println("Monthly Finance Rental after changing Base Int. Rate -  found OK");
			count++;
		} else {
			LO.print("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
			System.err.println("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
		}
		// 3
		if (Difference.of_two_Double_Values(customer_quote_summary_monthly_maint_rental_from_screen,
				monthlyMaintRental) < 0.2) {
			LO.print("Monthly Maint Rental after changing Base Int. Rate -  found OK");
			System.out.println("Monthly Maint Rental after changing Base Int. Rate -  found OK");
			count++;
		} else {
			LO.print("Monthly Maint Rental after changing Base Int. Rate -  found wrong");
			System.err.println("Monthly Maint Rental after changing Base Int. Rate -  found wrong");
		}
		// 4
		if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
			LO.print("Finance Charges - found OK");
			System.out.println("Finance Charges - found OK");
			count++;
		} else {
			LO.print("Finance Charges - found wrong");
			System.err.println("Finance Charges - found wrong");
		}

		// 5
		if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
			LO.print("Balance Payable - found OK");
			System.out.println("Balance Payable - found OK");
			count++;
		} else {
			LO.print("Balance Payable - found wrong");
			System.err.println("Balance Payable - found wrong");
		}

		if (count == 5) {
			status = true;
		}

		ExplicitWait.visibleElement(driver, quote_summary_configuration_base_int_rate_input, 30);
		quote_summary_configuration_base_int_rate_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		String default_base_rate =  String.valueOf((Double.parseDouble(prop.getProperty("base_rate"))*100));
		quote_summary_configuration_base_int_rate_input.sendKeys(default_base_rate);

		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		LO.print("Base Interest Rate changed to 6.5 %");
		System.out.println("Base Interest Rate changed to 6.5 %");

		// writing values to excel

		FileInputStream in1 = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb1 = new XSSFWorkbook(in1);

		wb1.getSheet(sheet_name).getRow(34).getCell(10).setCellValue(Double.parseDouble(prop.getProperty("base_rate")));
		wb1.getSheet(sheet_name).getRow(62).getCell(1).setCellValue(Double.parseDouble(prop.getProperty("base_rate"))*100);

		FileOutputStream out1 = new FileOutputStream(prop.getProperty("formula_excel_path"));

		wb1.write(out1);

		return status;
	}

	public boolean quote_summary_edit_customer_rate_over_base_value_verification_for_funder(String sheet_name)
			throws IOException, InterruptedException {

		LO.print(
				"*************Editing Customer Rate Over Base and Verifying Values on quote summary page has been started************");
		System.out.println(
				"*************Editing Customer Rate Over Base and Verifying Values on quote summary page has been started************");

		// Edit finance margin configuration values from screen

		ExplicitWait.visibleElement(driver, quote_summary_configuration_customer_base_over_rate, 30);
		quote_summary_configuration_customer_base_over_rate.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		quote_summary_configuration_customer_base_over_rate.sendKeys("5.0");

		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		LO.print("Customer Base Over Rate changed to 5.0 %");
		System.out.println("Customer Base Over Rate changed to 5.0 %");

		// Getting values from screen Thread.sleep(2000);

		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 30);

		double customer_quote_summary_monthly_finance_rental_from_screen = Double
				.parseDouble(RemoveComma.of(quote_summary_monthly_finance_rental.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_charges, 20);

		double customer_quote_summary_finance_charges = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_finance_charges.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_payable, 20);
		double customer_quote_summary_balance_payable = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_balance_payable.getText().trim().substring(2)));

		// writing values to excel

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(72).getCell(1).setCellValue(0.05);
		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		// getting values from excel

		double monthlyFinanceRental = GetExcelFormulaValue.get_formula_value(175, 0, sheet_name);

		double financeCharges = GetExcelFormulaValue.get_formula_value(232, 0, sheet_name);

		double balancePayable = GetExcelFormulaValue.get_formula_value(232, 4, sheet_name);

		// verifying actual and expected values

		int count = 0;

		boolean status = false;
		// 1
		if (Difference.of_two_Double_Values(customer_quote_summary_monthly_finance_rental_from_screen,
				monthlyFinanceRental) < 0.2) {
			LO.print("Monthly Finance Rental after changing Customer Base Over Rate -  found OK");
			System.out.println("Monthly Finance Rental after changing Customer Base Over Rate -  found OK");
			count++;
		} else {
			LO.print("Monthly Finance Rental after changing Customer Base Over Rate -  found wrong");
			System.err.println("Monthly Finance Rental after changing Customer Base Over Rate -  found wrong");
		}
		// 2
		if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
			LO.print("Finance Charges - found OK");
			System.out.println("Finance Charges - found OK");
			count++;
		} else {
			LO.print("Finance Charges - found wrong");
			System.err.println("Finance Charges - found wrong");
		}

		// 3
		if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
			LO.print("Balance Payable - found OK");
			System.out.println("Balance Payable - found OK");
			count++;
		} else {
			LO.print("Balance Payable - found wrong");
			System.err.println("Balance Payable - found wrong");
		}

		if (count == 3) {
			status = true;
		}

		return status;
	}

	public boolean quote_summary_edit_customer_rate_over_base_value_verification(String sheet_name)
			throws IOException, InterruptedException {

		LO.print(
				"*************Editing Customer Rate Over Base and Verifying Values on quote summary page has been started************");
		System.out.println(
				"*************Editing Customer Rate Over Base and Verifying Values on quote summary page has been started************");

		// Edit finance margin configuration values from screen

		ExplicitWait.visibleElement(driver, quote_summary_configuration_customer_base_over_rate, 30);
		quote_summary_configuration_customer_base_over_rate.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		quote_summary_configuration_customer_base_over_rate.sendKeys("5.0");

		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		LO.print("Customer Base Over Rate changed to 5.0 %");
		System.out.println("Customer Base Over Rate changed to 5.0 %");

		// Getting values from screen Thread.sleep(2000);

		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 30);

		double customer_quote_summary_monthly_finance_rental_from_screen = Double
				.parseDouble(RemoveComma.of(quote_summary_monthly_finance_rental.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_charges, 20);

		double customer_quote_summary_finance_charges = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_finance_charges.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_payable, 20);
		double customer_quote_summary_balance_payable = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_balance_payable.getText().trim().substring(2)));

		// writing values to excel

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(63).getCell(1).setCellValue(0.05);
		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		// getting values from excel

		double monthlyFinanceRental = GetExcelFormulaValue.get_formula_value(166, 0, sheet_name);

		double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheet_name);

		double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheet_name);

		// verifying actual and expected values

		int count = 0;

		boolean status = false;
		// 1
		if (Difference.of_two_Double_Values(customer_quote_summary_monthly_finance_rental_from_screen,
				monthlyFinanceRental) < 0.2) {
			LO.print("Monthly Finance Rental after changing Customer Base Over Rate -  found OK");
			System.out.println("Monthly Finance Rental after changing Customer Base Over Rate -  found OK");
			count++;
		} else {
			LO.print("Monthly Finance Rental after changing Customer Base Over Rate -  found wrong");
			System.err.println("Monthly Finance Rental after changing Customer Base Over Rate -  found wrong");
		}
		// 2
		if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
			LO.print("Finance Charges - found OK");
			System.out.println("Finance Charges - found OK");
			count++;
		} else {
			LO.print("Finance Charges - found wrong");
			System.err.println("Finance Charges - found wrong");
		}

		// 3
		if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
			LO.print("Balance Payable - found OK");
			System.out.println("Balance Payable - found OK");
			count++;
		} else {
			LO.print("Balance Payable - found wrong");
			System.err.println("Balance Payable - found wrong");
		}

		if (count == 3) {
			status = true;
		}

		return status;
	}

	public boolean quote_summary_edit_maintenance_margin_value_verification(String sheet_name)
			throws IOException, InterruptedException {

		LO.print(
				"*************Editing Maintenance Margin and Verifying Values on quote summary page has been started************");
		System.out.println(
				"*************Editing Maintenance Margin and Verifying Values on quote summary page has been started************");

		try {
			// Edit finance margin configuration values from screen

			ExplicitWait.visibleElement(driver, quote_summary_configuration_maintenance_margin_input, 30);
			quote_summary_configuration_maintenance_margin_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			quote_summary_configuration_maintenance_margin_input.sendKeys("30");

			Actions act = new Actions(driver);
			act.sendKeys(Keys.TAB).build().perform();

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			LO.print("Maintenance margin changed to 30 %");
			System.out.println("Maintenance margin changed to 30 %");

			// Getting values from screen Thread.sleep(2000);

			ExplicitWait.visibleElement(driver, quote_summary_monthly_maintenance_rental, 30);

			double customer_quote_summary_monthly_maint_rental_from_screen = Double.parseDouble(
					RemoveComma.of(quote_summary_monthly_maintenance_rental.getText().trim().substring(2)));

			// writing values to excel

			FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
			XSSFWorkbook wb = new XSSFWorkbook(in);
			wb.getSheet(sheet_name).getRow(107).getCell(0).setCellValue(0.3);
			FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
			wb.write(out);

			// getting values from excel

			double monthlyMaintenanceRental = GetExcelFormulaValue.get_formula_value(166, 1, sheet_name);

			// verifying actual and expected values

			int count = 0;

			boolean status = false;

			if (Difference.of_two_Double_Values(customer_quote_summary_monthly_maint_rental_from_screen,
					monthlyMaintenanceRental) < 0.2) {
				LO.print("Monthly Maint Rental after changing Maintenance margin -  found OK");
				System.out.println("Monthly Maint Rental after changing Maintenance margin -  found OK");
				count++;
			} else {
				LO.print("Monthly Maint Rental after changing Maintenance margin -  found wrong");
				System.err.println("Monthly Maint Rental after changing Maintenance margin -  found wrong");
			}

			if (count == 1) {
				status = true;
			}

			return status;
		} catch (Exception e) {

			LO.print("");
			System.err.println("");

			LO.print("Maintenance margin can not be changed as there is no maintennace for this vehicle");
			System.err.println("Maintenance margin can not be changed as there is no maintennace for this vehicle");

			LO.print("");
			System.err.println("");

			return true;
		}
	}

	public void save_quote() throws InterruptedException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		Click.on(driver, quote_summary_save_button, 50);
		
//		ExplicitWait.clickableElement(driver, quote_summary_save_button, 30);
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//
//		js.executeScript("arguments[0].click();", quote_summary_save_button);
		
//		Actions act = new Actions(driver);
//		act.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, quote_summary_ref_no, 120);

		String quote_ref_no = quote_summary_ref_no.getText();

		LO.print("*********Customer Quote generated successfully and Quote_ref_no is=" + quote_ref_no);
		System.out.println("*********Customer Quote generated successfully and Quote_ref_no is=" + quote_ref_no);

	}

	public boolean quote_summary_holding_cost_calculation_for_funder_without_maintenance(String sheet_name)
			throws InterruptedException, IOException {

		LO.print("*************Holding Cost Calulation on quote summary page has been started************");
		System.out.println("*************Holding Cost Calulation on quote summary page has been started************");

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_term, 30);

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_miles_per_annum, 30);

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_monthly_finance_cost, 30);

		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 30);

		LO.print("Reading values from Holding Cost summary -Quote Summary Page");
		System.out.println("Reading values from Holding Cost summary -Quote Summary Page");

		double holding_cost_terms_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_holding_cost_term.getText().trim().substring(0, 2)));

		double holding_cost_miles_per_annum_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_holding_cost_miles_per_annum.getText().trim()));

		double holding_cost_monthly_finance_cost_from_screen_converted = Double.parseDouble(
				RemoveComma.of(quote_summary_holding_cost_monthly_finance_cost.getText().trim().substring(2)));

		double holding_cost_total_monthly_holding_cost_from_screen_converted = Double.parseDouble(RemoveComma
				.of(quote_summary_total_monthly_holding_cost.getText().trim().substring(2)));

		LO.print("holding_cost_terms_from_screen" + holding_cost_terms_from_screen_converted);
		System.out.println("holding_cost_terms_from_screen" + holding_cost_terms_from_screen_converted);

		LO.print("holding_cost_miles_per_annum_from_screen" + holding_cost_miles_per_annum_from_screen_converted);
		System.out.println(
				"holding_cost_miles_per_annum_from_screen" + holding_cost_miles_per_annum_from_screen_converted);

		LO.print("holding_cost_monthly_finance_cost_from_screen"
				+ holding_cost_monthly_finance_cost_from_screen_converted);
		System.out.println("holding_cost_monthly_finance_cost_from_screen"
				+ holding_cost_monthly_finance_cost_from_screen_converted);

		LO.print("holding_cost_total_monthly_holding_cost_from_screen ="
				+ holding_cost_total_monthly_holding_cost_from_screen_converted);
		System.out.println("holding_cost_total_monthly_holding_cost_from_screen ="
				+ holding_cost_total_monthly_holding_cost_from_screen_converted);

		double holding_cost_terms_from_excel = GetExcelFormulaValue.get_string_value(52, 0, sheet_name);
		double holding_cost_miles_per_annum_from_excel = GetExcelFormulaValue.get_string_value(51, 1, sheet_name);
		double holding_cost_monthly_finance_cost_from_excel = GetExcelFormulaValue.get_formula_value(45, 7, sheet_name);
		double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(52, 1,
				sheet_name);

		double diff_terms = Difference.of_two_Double_Values(holding_cost_terms_from_excel,
				holding_cost_terms_from_screen_converted);
		double diff_miles_per_annum = Difference.of_two_Double_Values(holding_cost_miles_per_annum_from_excel,
				holding_cost_miles_per_annum_from_screen_converted);
		double diff_finance_cost = Difference.of_two_Double_Values(holding_cost_monthly_finance_cost_from_excel,
				holding_cost_monthly_finance_cost_from_screen_converted);
		double diff_total_monthly_holding_cost = Difference.of_two_Double_Values(
				holding_cost_total_monthly_holding_cost_from_excel,
				holding_cost_total_monthly_holding_cost_from_screen_converted);

		int count = 0;
		boolean status = false;
		if (diff_terms < 0.2) {
			LO.print("terms compared");
			System.out.println("terms compared");
			count++;
		} else {
			LO.print("Found difference between terms actual  and terms expected ");
			System.err.println("Found difference between terms actual  and terms expected ");
		}

		if (diff_miles_per_annum < 0.2) {
			LO.print("Miles per annum compared");
			System.out.println("Miles per annum compared");
			count++;
		} else {
			LO.print(
					"Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected ");
			System.err.println(
					"Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected");
		}

		if (diff_finance_cost < 0.2) {
			LO.print("Finance cost compared");
			System.out.println("Finance cost compared");
			count++;
		} else {
			LO.print("Found difference between Finance cost actual and Finance cost expected");
			System.err.println("Found difference between Finance cost actual and Finance cost expected");
		}

		if (diff_total_monthly_holding_cost < 0.2) {
			LO.print("Total Monthly Holding Cost compared");
			System.out.println("Total Monthly Holding Cost compared");
			count++;
		} else {
			LO.print(
					"Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");
			System.err.println(
					"Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");
		}

		if (count == 4) {
			status = true;
		}

		return status;

	}

	public boolean quote_summary_holding_cost_calculation_for_funder_with_maintenance(String sheet_name)
			throws InterruptedException, IOException {

		LO.print("*************Holding Cost Calulation on quote summary page has been started************");
		System.out.println("*************Holding Cost Calulation on quote summary page has been started************");

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_term, 30);

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_miles_per_annum, 30);

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_monthly_finance_cost, 30);

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_monthly_maint_cost_used, 30);

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_CAP_monthly_maint_cost, 30);

		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 30);

		LO.print("Reading values from Holding Cost summary -Quote Summary Page");
		System.out.println("Reading values from Holding Cost summary -Quote Summary Page");

		double holding_cost_terms_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_holding_cost_term.getText().trim().substring(0, 2)));

		double holding_cost_miles_per_annum_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_holding_cost_miles_per_annum.getText().trim()));

		double holding_cost_monthly_finance_cost_from_screen_converted = Double.parseDouble(
				RemoveComma.of(quote_summary_holding_cost_monthly_finance_cost.getText().trim().substring(2)));

		double holding_cost_monthly_maint_cost_used_from_screen_converted = Double.parseDouble(
				RemoveComma.of(quote_summary_holding_cost_monthly_maint_cost_used.getText().trim().substring(2)));

		double holding_cost_CAP_monthly_maint_cost_from_screen_converted = Double.parseDouble(
				RemoveComma.of(quote_summary_holding_cost_CAP_monthly_maint_cost.getText().trim().substring(2)));

		double holding_cost_total_monthly_holding_cost_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_total_monthly_holding_cost.getText().trim().substring(2)));

		double holding_cost_terms_from_excel = GetExcelFormulaValue.get_string_value(52, 0, sheet_name);
		double holding_cost_miles_per_annum_from_excel = GetExcelFormulaValue.get_string_value(51, 1, sheet_name);
		double holding_cost_monthly_finance_cost_from_excel = GetExcelFormulaValue.get_formula_value(45, 7, sheet_name);
		double holding_cost_monthly_maint_cost_used_from_excel = GetExcelFormulaValue.get_formula_value(40, 0,
				sheet_name);
		double holding_cost_CAP_monthly_maint_cost_from_excel = GetExcelFormulaValue.get_formula_value(40, 0,
				sheet_name);
		double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(52, 1,
				sheet_name);

		double diff_terms = Difference.of_two_Double_Values(holding_cost_terms_from_excel,
				holding_cost_terms_from_screen_converted);
		double diff_miles_per_annum = Difference.of_two_Double_Values(holding_cost_miles_per_annum_from_excel,
				holding_cost_miles_per_annum_from_screen_converted);
		double diff_finance_cost = Difference.of_two_Double_Values(holding_cost_monthly_finance_cost_from_excel,
				holding_cost_monthly_finance_cost_from_screen_converted);
		double diff_maint_cost = Difference.of_two_Double_Values(holding_cost_monthly_maint_cost_used_from_excel,
				holding_cost_monthly_maint_cost_used_from_screen_converted);
		double diff_CAP_maint = Difference.of_two_Double_Values(holding_cost_CAP_monthly_maint_cost_from_excel,
				holding_cost_CAP_monthly_maint_cost_from_screen_converted);
		double diff_total_monthly_holding_cost = Difference.of_two_Double_Values(
				holding_cost_total_monthly_holding_cost_from_excel,
				holding_cost_total_monthly_holding_cost_from_screen_converted);

		LO.print("");
		System.out.println("");

		LO.print("Holding cost Terms from screen" + holding_cost_terms_from_screen_converted);
		System.out.println("Holding cost Terms from screen is " + holding_cost_terms_from_screen_converted);

		LO.print("Holding cost miles per annum from screen is " + holding_cost_miles_per_annum_from_screen_converted);
		System.out.println(
				"Holding cost miles per annum from screen is " + holding_cost_miles_per_annum_from_screen_converted);

		LO.print("Holding cost monthly finance cost from screen is "
				+ holding_cost_monthly_finance_cost_from_screen_converted);
		System.out.println("Holding cost monthly finance cost from screen is "
				+ holding_cost_monthly_finance_cost_from_screen_converted);

		LO.print("Holding cost monthly maint cost used from screen is "
				+ holding_cost_monthly_maint_cost_used_from_screen_converted);
		System.out.println("Holding cost monthly maint cost used from screen is "
				+ holding_cost_monthly_maint_cost_used_from_screen_converted);

		LO.print("Holding cost total monthly holding cost from screen is "
				+ holding_cost_total_monthly_holding_cost_from_screen_converted);
		System.out.println("Holding cost total monthly holding cost from screen is "
				+ holding_cost_total_monthly_holding_cost_from_screen_converted);

		LO.print("");
		System.out.println("");

		LO.print("Holding cost Terms from excel is " + holding_cost_terms_from_excel);
		System.out.println("Holding cost Terms from excel is " + holding_cost_terms_from_excel);

		LO.print("Holding cost miles per annum from excel is " + holding_cost_miles_per_annum_from_excel);
		System.out.println("Holding cost miles per annum from excel is " + holding_cost_miles_per_annum_from_excel);

		LO.print("Holding cost monthly finance cost from excel is " + holding_cost_monthly_finance_cost_from_excel);
		System.out.println(
				"Holding cost monthly finance cost from excel is " + holding_cost_monthly_finance_cost_from_excel);

		LO.print("Holding cost monthly maint cost used from excel is "
				+ holding_cost_monthly_maint_cost_used_from_excel);
		System.out.println("Holding cost monthly maint cost used from excel is "
				+ holding_cost_monthly_maint_cost_used_from_excel);

		LO.print("Holding cost total monthly holding cost from excel is "
				+ holding_cost_total_monthly_holding_cost_from_excel);
		System.out.println("Holding cost total monthly holding cost from excel is "
				+ holding_cost_total_monthly_holding_cost_from_excel);

		int count = 0;
		boolean status = false;
		if (diff_terms < 0.2) {
			LO.print("terms compared");
			System.out.println("terms compared");
			count++;
		} else {
			LO.print("Found difference between terms actual  and terms expected ");
			System.err.println("Found difference between terms actual  and terms expected ");
		}

		if (diff_miles_per_annum < 0.2) {
			LO.print("Miles per annum compared");
			System.out.println("Miles per annum compared");
			count++;
		} else {
			LO.print(
					"Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected ");
			System.err.println(
					"Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected");
		}

		if (diff_finance_cost < 0.2) {
			LO.print("Finance cost compared");
			System.out.println("Finance cost compared");
			count++;
		} else {
			LO.print("Found difference between Finance cost actual and Finance cost expected");
			System.err.println("Found difference between Finance cost actual and Finance cost expected");
		}

		if (diff_maint_cost < 0.2) {
			LO.print("Maint cost used  compared");
			System.out.println("Maint cost used compared");
			count++;
		} else {
			LO.print("Found difference between Maint cost used actual and Maint cost used expected");
			System.err.println("Found difference between Maint cost used actual and Maint cost used expected");
		}

		if (diff_CAP_maint < 0.2) {
			LO.print("CAP monthly cost compared");
			System.out.println("CAP monthly cost compared");
			count++;
		} else {
			LO.print("Found difference between CAP monthly cost actual and CAP monthly cost expected");
			System.err.println("Found difference between CAP monthly cost actual and CAP monthly cost expected");
		}

		if (diff_total_monthly_holding_cost < 0.2) {
			LO.print("Total Monthly Holding Cost compared");
			System.out.println("Total Monthly Holding Cost compared");
			count++;
		} else {
			LO.print(
					"Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");
			System.err.println(
					"Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");
		}

		if (count == 6) {
			status = true;
		}

		return status;

	}

	public boolean quote_summary_customer_quote_summary_value_verification_for_funder_with_maintenance(
			String sheet_name) throws IOException, Exception {

		LO.print("*************Customer Quote Calulation on quote summary page has been started************");
		System.out.println("*************Customer Quote Calulation on quote summary page has been started************");

		// Clicking on Customer quote pannel
		Click.on(driver, quote_summary_customer_quote_summary_value_verification, 20);

		try {
			// waiting for elements
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_terms, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_miles_per_annum, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_basic_cash_price, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_vat, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_non_vat_items, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_cash_price, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_order_deposit, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_deposit, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_deposit, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_to_finance, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_charges, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_document_fee, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_payable, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_option_to_purchase_fee, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_initial_cash_payment, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_followed_by, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_monthly_finance_payment, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_monthly_maint_payment, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_monthly_payment, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_guaranteed_future_value, 20);
			ExplicitWait.visibleElement(driver,
					quote_summary_customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_finance, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_maint, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_total, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_vehicle_comm, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_default_finance_comm, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_maintenance_commision, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_document_fee_comm, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_comm, 20);

			// getting text from elements

			double customer_quote_summary_terms = Double
					.parseDouble(quote_summary_customer_quote_summary_terms.getText().trim().substring(0, 2));

			double customer_quote_summary_miles = Double
					.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_miles_per_annum.getText().trim()));

			double customer_quote_summary_basic_cash_price = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

			double customer_quote_summary_vat = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_vat.getText().trim().substring(2)));

			double customer_quote_summary_non_vat_items = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_non_vat_items.getText().trim().substring(2)));

			double customer_quote_summary_total_cash_price = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_total_cash_price.getText().trim().substring(2)));

			double customer_quote_summary_order_deposit = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_order_deposit.getText().trim().substring(2)));

			double customer_quote_summary_finance_deposit = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_finance_deposit.getText().trim().substring(2)));

			double customer_quote_summary_total_deposit = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_total_deposit.getText().trim().substring(2)));

			double customer_quote_summary_balance_to_finance = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));

			double customer_quote_summary_finance_charges = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_finance_charges.getText().trim().substring(2)));

			double customer_quote_summary_document_fee = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_document_fee.getText().trim().substring(2)));

			double customer_quote_summary_balance_payable = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_balance_payable.getText().trim().substring(2)));

			double customer_quote_summary_option_to_purchase_fee = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));

			double customer_quote_summary_initial_cash_payment = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_initial_cash_payment.getText().trim().substring(2)));

			double customer_payment_followed_by = Double
					.parseDouble(quote_summary_customer_quote_summary_followed_by.getText().substring(0, 2));

			double customer_quote_summary_monthly_finance_payment = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));

			double customer_quote_summary_monthly_maint_payment = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_monthly_maint_payment.getText().trim().substring(2)));

			double customer_quote_summary_total_monthly_payment = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_total_monthly_payment.getText().trim().substring(2)));
			double customer_quote_summary_guaranteed_future_value = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_guaranteed_future_value.getText().trim().substring(2)));

			double customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee = Double
					.parseDouble(RemoveComma
							.of(quote_summary_customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee
									.getText().trim().substring(2)));

			double customer_quote_summary_pence_per_excess_mile_finance = Double
					.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_pence_per_excess_mile_finance
							.getText().trim().substring(0, 4)));

			double customer_quote_summary_pence_per_excess_mile_maint = Double.parseDouble(RemoveComma.of(
					quote_summary_customer_quote_summary_pence_per_excess_mile_maint.getText().trim().substring(0, 4)));

			double customer_quote_summary_pence_per_excess_mile_total = Double.parseDouble(RemoveComma.of(
					quote_summary_customer_quote_summary_pence_per_excess_mile_total.getText().trim().substring(0, 4)));

			double customer_quote_summary_vehicle_comm = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_vehicle_comm.getText().trim().substring(2)));

			double customer_quote_summary_default_finance_comm = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_default_finance_comm.getText().trim().substring(2)));

			double customer_quote_summary_maintenance_commision = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_maintenance_commision.getText().trim().substring(2)));

			double customer_quote_summary_document_fee_comm = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_document_fee_comm.getText().trim().substring(2)));

			double customer_quote_summary_total_commission = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_total_comm.getText().trim().substring(2)));

			// writing balloon value to excel

			FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
			XSSFWorkbook wb = new XSSFWorkbook(in);
			wb.getSheet(sheet_name).getRow(169).getCell(4).setCellValue(customer_quote_summary_guaranteed_future_value);
			FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
			wb.write(out);

			// getting values from excel

			double terms = GetExcelFormulaValue.get_string_value(208, 1, sheet_name);
			double miles = GetExcelFormulaValue.get_string_value(208, 4, sheet_name);

			double basicCashPrice = GetExcelFormulaValue.get_formula_value(214, 0, sheet_name);
			double vat = GetExcelFormulaValue.get_formula_value(214, 1, sheet_name);
			double nonVATItems = GetExcelFormulaValue.get_formula_value(214, 4, sheet_name);

			double totalCashPrice = GetExcelFormulaValue.get_formula_value(217, 0, sheet_name);
			double orderDeposit = GetExcelFormulaValue.get_formula_value(217, 1, sheet_name);
			double financeDeposit = GetExcelFormulaValue.get_formula_value(217, 4, sheet_name);

			double totalDeposit = GetExcelFormulaValue.get_formula_value(220, 0, sheet_name);
			double balanceToFinance = GetExcelFormulaValue.get_formula_value(220, 4, sheet_name);

			double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheet_name);
			double documentFee = GetExcelFormulaValue.get_string_value(223, 1, sheet_name);
			double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheet_name);

			double optionToPurchaseFee = GetExcelFormulaValue.get_formula_value(226, 0, sheet_name);
			double initialCashPayment = GetExcelFormulaValue.get_formula_value(226, 1, sheet_name);
			double followedBy = GetExcelFormulaValue.get_formula_value(226, 4, sheet_name);

			double monthlyFinancePayment = GetExcelFormulaValue.get_formula_value(229, 0, sheet_name);
			double monthlyMaintenancePayment = GetExcelFormulaValue.get_formula_value(229, 1, sheet_name);
			double totalMonthlyPayment = GetExcelFormulaValue.get_formula_value(229, 4, sheet_name);

			double guaranteedFutureValue = GetExcelFormulaValue.get_formula_value(232, 0, sheet_name);
			double optionalFinalPayment = GetExcelFormulaValue.get_formula_value(232, 1, sheet_name);
			double pencePerExcessMileFinance = GetExcelFormulaValue.get_formula_value(232, 4, sheet_name);
			double pencePerExcessMileMaintenance = GetExcelFormulaValue.get_formula_value(235, 0, sheet_name);
			double pencePerExcessMileTotal = GetExcelFormulaValue.get_formula_value(235, 1, sheet_name);

			double vehicleCommission = GetExcelFormulaValue.get_formula_value(239, 0, sheet_name);
			double defaultFinanceCommission = GetExcelFormulaValue.get_formula_value(239, 1, sheet_name);
			double maintCommission = GetExcelFormulaValue.get_formula_value(239, 4, sheet_name);

			double docFeeCommission = GetExcelFormulaValue.get_formula_value(242, 0, sheet_name);
			double totalCommission = GetExcelFormulaValue.get_formula_value(242, 1, sheet_name);

			// comparing actul and expected values

			boolean status = false;

			int count = 0;

			// 1
			if (terms == customer_quote_summary_terms) {
				LO.print("Terms found OK");
				System.out.println("Terms found OK");
				count++;
			} else {
				LO.print("Terms found wrong");
				System.err.println("Terms found wrong");
			}

			// 2
			if (miles == customer_quote_summary_miles) {
				LO.print("miles found OK");
				System.out.println("miles found OK");
				count++;
			} else {
				LO.print("miles found wrong");
				System.err.println("miles found wrong");
			}

			// 3
			if ((Difference.of_two_Double_Values(basicCashPrice, customer_quote_summary_basic_cash_price)) < 0.2) {
				LO.print("Basic Cash Price found OK");
				System.out.println("Basic Cash Price found OK");
				count++;
			} else {
				LO.print("Basic Cash Price found wrong");
				System.err.println("Basic Cash Price found wrong");
			}

			// 4
			if ((Difference.of_two_Double_Values(vat, customer_quote_summary_vat)) < 0.2) {
				LO.print("VAT found OK");
				System.out.println("VAT found OK");
				count++;
			} else {
				LO.print("VAT found wrong");
				System.err.println("VAT found wrong");
			}

			// 5
			if ((Difference.of_two_Double_Values(nonVATItems, customer_quote_summary_non_vat_items)) < 0.2) {
				LO.print("Non VAT Items Value found OK");
				System.out.println("Non VAT Items Value found OK");
				count++;
			} else {
				LO.print("Non VAT Items Value found wrong");
				System.err.println("Non VAT Items Value found wrong");
			}

			// 6
			if ((Difference.of_two_Double_Values(totalCashPrice, customer_quote_summary_total_cash_price)) < 0.2) {
				LO.print("Total Cash Price found OK");
				System.out.println("Total Cash Price found OK");
				count++;
			} else {
				LO.print("Total Cash Price found wrong");
				System.err.println("Total Cash Price found wrong");
			}

			// 7
			if ((Difference.of_two_Double_Values(orderDeposit, customer_quote_summary_order_deposit)) < 0.2) {
				LO.print("Order Deposit found OK");
				System.out.println("Order Deposit found OK");
				count++;
			} else {
				LO.print("Order Deposit found wrong");
				System.err.println("Order Deposit found wrong");
			}

			// 8
			if ((Difference.of_two_Double_Values(financeDeposit, customer_quote_summary_finance_deposit)) < 0.2) {
				LO.print("Finance Deposit found OK");
				System.out.println("Finance Deposit found OK");
				count++;
			} else {
				LO.print("Finance Deposit found wrong");
				System.err.println("Finance Deposit found wrong");
			}

			// 9
			if ((Difference.of_two_Double_Values(totalDeposit, customer_quote_summary_total_deposit)) < 0.2) {
				LO.print("Total Deposit found OK");
				System.out.println("Total Deposit found OK");
				count++;
			} else {
				LO.print("Total Deposit found wrong");
				System.err.println("Total Deposit found wrong");
			}

			// 10
			if ((Difference.of_two_Double_Values(balanceToFinance, customer_quote_summary_balance_to_finance)) < 0.2) {
				LO.print("Balance to Finance found OK");
				System.out.println("Balance to Finance found OK");
				count++;
			} else {
				LO.print("Balance to Finance found wrong");
				System.err.println("Balance to Finance found wrong");
			}

			// 11
			if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
				LO.print("Finance Charges - found OK");
				System.out.println("Finance Charges - found OK");
				count++;
			} else {
				LO.print("Finance Charges - found wrong");
				System.err.println("Finance Charges - found wrong");
			}

			// 12
			if ((Difference.of_two_Double_Values(documentFee, customer_quote_summary_document_fee)) < 0.2) {
				LO.print("Document Fee - found OK");
				System.out.println("Document Fee - found OK");
				count++;
			} else {
				LO.print("Document Fee - found wrong");
				System.err.println("Document Fee - found wrong");
			}

			// 13
			if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
				LO.print("Balance Payable - found OK");
				System.out.println("Balance Payable - found OK");
				count++;
			} else {
				LO.print("Balance Payable - found wrong");
				System.err.println("Balance Payable - found wrong");
			}

			// 14
			if ((Difference.of_two_Double_Values(optionToPurchaseFee,
					customer_quote_summary_option_to_purchase_fee)) < 0.2) {
				LO.print("Option To Purchase Fee - found OK");
				System.out.println("Option To Purchase Fee - found OK");
				count++;
			} else {
				LO.print("Option To Purchase Fee - found wrong");
				System.err.println("Option To Purchase Fee - found wrong");
			}

			// 15
			if ((Difference.of_two_Double_Values(initialCashPayment,
					customer_quote_summary_initial_cash_payment)) < 0.2) {
				LO.print("Initial Cash Payment - found OK");
				System.out.println("Initial Cash Payment - found OK");
				count++;
			} else {
				LO.print("Initial Cash Payment - found wrong");
				System.err.println("Initial Cash Payment - found wrong");
			}

			// 16
			if (followedBy == customer_payment_followed_by) {
				LO.print("Followed By months - found OK");
				System.out.println("Followed By months - found OK");
				count++;
			} else {
				LO.print("Followed By months - found wrong");
				System.err.println("Followed By months - found wrong");
			}

			// 17
			if ((Difference.of_two_Double_Values(monthlyFinancePayment,
					customer_quote_summary_monthly_finance_payment)) < 0.2) {
				LO.print("Monthly Finance Payment - found OK");
				System.out.println("Monthly Finance Payment - found OK");
				count++;
			} else {
				LO.print("Monthly Finance Payment - found wrong");
				System.err.println("Monthly Finance Payment - found wrong");
			}

			// 18
			if ((Difference.of_two_Double_Values(monthlyMaintenancePayment,
					customer_quote_summary_monthly_maint_payment)) < 0.2) {
				LO.print("Monthly Maint Payment - found OK");
				System.out.println("Monthly Maint Payment - found OK");
				count++;
			} else {
				LO.print("Monthly Maint Payment - found wrong");
				System.err.println("Monthly Maint Payment - found wrong");
			}

			// 19
			if ((Difference.of_two_Double_Values(totalMonthlyPayment,
					customer_quote_summary_total_monthly_payment)) < 0.2) {
				LO.print("Monthly Total Payment - found OK");
				System.out.println("Monthly Total Payment - found OK");
				count++;
			} else {
				LO.print("Monthly Total Payment - found wrong");
				System.err.println("Monthly Total Payment - found wrong");
			}

			// 20
			if ((Difference.of_two_Double_Values(guaranteedFutureValue,
					customer_quote_summary_guaranteed_future_value)) < 0.2) {
				LO.print("Balloon Value - found OK");
				System.out.println("Balloon Value - found OK");
				count++;
			} else {
				LO.print("Balloon Value - found wrong");
				System.err.println("Balloon Value - found wrong");
			}

			// 21
			if ((Difference.of_two_Double_Values(optionalFinalPayment,
					customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee)) < 0.2) {
				LO.print("Final Payment - found OK");
				System.out.println("Final Payment - found OK");
				count++;
			} else {
				LO.print("Final Payment - found wrong");
				System.err.println("Final Payment - found wrong");
			}

			// 22
			if ((Difference.of_two_Double_Values(pencePerExcessMileFinance,
					customer_quote_summary_pence_per_excess_mile_finance)) < 0.2) {
				LO.print("Pence Per Excess Mile Finance - found OK");
				System.out.println("Pence Per Excess Mile Finance - found OK");
				count++;
			} else {
				LO.print("Pence Per Excess Mile Finance - found wrong");
				System.err.println("Pence Per Excess Mile Finance - found wrong");
			}

			// 23
			if ((Difference.of_two_Double_Values(pencePerExcessMileMaintenance,
					customer_quote_summary_pence_per_excess_mile_maint)) < 0.2) {
				LO.print("Pence Per Excess Mile Maintenance - found OK");
				System.out.println("Pence Per Excess Mile Maintenance - found OK");
				count++;
			} else {
				LO.print("Pence Per Excess Mile Maintenance - found wrong");
				System.err.println("Pence Per Excess Mile Maintenance - found wrong");
			}

			// 24
			if ((Difference.of_two_Double_Values(pencePerExcessMileTotal,
					customer_quote_summary_pence_per_excess_mile_total)) < 0.2) {
				LO.print("Pence Per Excess Mile Total - found OK");
				System.out.println("Pence Per Excess Mile Total - found OK");
				count++;
			} else {
				LO.print("Pence Per Excess Mile Total - found wrong");
				System.err.println("Pence Per Excess Mile Total - found wrong");
			}

			// 25
			if ((Difference.of_two_Double_Values(vehicleCommission, customer_quote_summary_vehicle_comm)) < 0.2) {
				LO.print("Vehicle Commission - found OK");
				System.out.println("Vehicle Commission - found OK");
				count++;
			} else {
				LO.print("Vehicle Commission - found wrong");
				System.err.println("Vehicle Commission - found wrong");
			}

			// 26
			if ((Difference.of_two_Double_Values(defaultFinanceCommission,
					customer_quote_summary_default_finance_comm)) < 0.2) {
				LO.print("Default Finance Commission - found OK");
				System.out.println("Default Finance Commission - found OK");
				count++;
			} else {
				LO.print("Default Finance Commission - found wrong");
				System.err.println("Default Finance Commission - found wrong");
			}

			// 27
			if ((Difference.of_two_Double_Values(maintCommission,
					customer_quote_summary_maintenance_commision)) < 0.2) {
				LO.print("Maintenance Commission - found OK");
				System.out.println("Maintenance Commission - found OK");
				count++;
			} else {
				LO.print("Maintenance Commission - found wrong");
				System.err.println("Maintenance Commission - found wrong");
			}

			// 28
			if ((Difference.of_two_Double_Values(docFeeCommission, customer_quote_summary_document_fee_comm)) < 0.2) {
				LO.print("Document Fee Commission - found OK");
				System.out.println("Document Fee Commission - found OK");
				count++;
			} else {
				LO.print("Document Fee Commission - found wrong");
				System.err.println("Document Fee Commission - found wrong");
			}

			// 29
			if ((Difference.of_two_Double_Values(totalCommission, customer_quote_summary_total_commission)) < 0.2) {
				LO.print("Total Commission - found OK");
				System.out.println("Total Commission - found OK");
				count++;
			} else {
				LO.print("Total Commission - found wrong");
				System.err.println("Total Commission - found wrong");
			}

			if (count == 29) {
				status = true;
			}
			return status;
		} catch (Exception e) {

			// waiting for elements
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_terms, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_miles_per_annum, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_basic_cash_price, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_vat, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_non_vat_items, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_cash_price, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_order_deposit, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_deposit, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_deposit, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_to_finance, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_charges, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_document_fee, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_payable, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_option_to_purchase_fee, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_initial_cash_payment, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_followed_by, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_monthly_finance_payment, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_guaranteed_future_value, 20);
			ExplicitWait.visibleElement(driver,
					quote_summary_customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_finance, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_vehicle_comm, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_default_finance_comm, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_document_fee_comm, 20);
			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_comm, 20);

			// getting text from elements

			double customer_quote_summary_terms = Double
					.parseDouble(quote_summary_customer_quote_summary_terms.getText().trim().substring(0, 2));

			double customer_quote_summary_miles = Double
					.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_miles_per_annum.getText().trim()));

			double customer_quote_summary_basic_cash_price = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

			double customer_quote_summary_vat = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_vat.getText().trim().substring(2)));

			double customer_quote_summary_non_vat_items = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_non_vat_items.getText().trim().substring(2)));

			double customer_quote_summary_total_cash_price = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_total_cash_price.getText().trim().substring(2)));

			double customer_quote_summary_order_deposit = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_order_deposit.getText().trim().substring(2)));

			double customer_quote_summary_finance_deposit = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_finance_deposit.getText().trim().substring(2)));

			double customer_quote_summary_total_deposit = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_total_deposit.getText().trim().substring(2)));

			double customer_quote_summary_balance_to_finance = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));

			double customer_quote_summary_finance_charges = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_finance_charges.getText().trim().substring(2)));

			double customer_quote_summary_document_fee = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_document_fee.getText().trim().substring(2)));

			double customer_quote_summary_balance_payable = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_balance_payable.getText().trim().substring(2)));

			double customer_quote_summary_option_to_purchase_fee = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));

			double customer_quote_summary_initial_cash_payment = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_initial_cash_payment.getText().trim().substring(2)));

			double customer_payment_followed_by = Double
					.parseDouble(quote_summary_customer_quote_summary_followed_by.getText().substring(0, 2));

			double customer_quote_summary_monthly_finance_payment = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));

			double customer_quote_summary_guaranteed_future_value = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_guaranteed_future_value.getText().trim().substring(2)));

			double customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee = Double
					.parseDouble(RemoveComma
							.of(quote_summary_customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee
									.getText().trim().substring(2)));

			double customer_quote_summary_pence_per_excess_mile_finance = Double
					.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_pence_per_excess_mile_finance
							.getText().trim().substring(0, 4)));

			double customer_quote_summary_vehicle_comm = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_vehicle_comm.getText().trim().substring(2)));

			double customer_quote_summary_default_finance_comm = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_default_finance_comm.getText().trim().substring(2)));

			double customer_quote_summary_document_fee_comm = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_document_fee_comm.getText().trim().substring(2)));

			double customer_quote_summary_total_commission = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_total_comm.getText().trim().substring(2)));

			// writing balloon value to excel

			FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
			XSSFWorkbook wb = new XSSFWorkbook(in);
			wb.getSheet(sheet_name).getRow(169).getCell(4).setCellValue(customer_quote_summary_guaranteed_future_value);
			FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
			wb.write(out);

			// getting values from excel

			double terms = GetExcelFormulaValue.get_string_value(208, 1, sheet_name);
			double miles = GetExcelFormulaValue.get_string_value(208, 4, sheet_name);

			double basicCashPrice = GetExcelFormulaValue.get_formula_value(214, 0, sheet_name);
			double vat = GetExcelFormulaValue.get_formula_value(214, 1, sheet_name);
			double nonVATItems = GetExcelFormulaValue.get_formula_value(214, 4, sheet_name);

			double totalCashPrice = GetExcelFormulaValue.get_formula_value(217, 0, sheet_name);
			double orderDeposit = GetExcelFormulaValue.get_formula_value(217, 1, sheet_name);
			double financeDeposit = GetExcelFormulaValue.get_formula_value(217, 4, sheet_name);

			double totalDeposit = GetExcelFormulaValue.get_formula_value(220, 0, sheet_name);
			double balanceToFinance = GetExcelFormulaValue.get_formula_value(220, 4, sheet_name);

			double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheet_name);
			double documentFee = GetExcelFormulaValue.get_string_value(223, 1, sheet_name);
			double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheet_name);

			double optionToPurchaseFee = GetExcelFormulaValue.get_formula_value(226, 0, sheet_name);
			double initialCashPayment = GetExcelFormulaValue.get_formula_value(226, 1, sheet_name);
			double followedBy = GetExcelFormulaValue.get_formula_value(226, 4, sheet_name);

			double monthlyFinancePayment = GetExcelFormulaValue.get_formula_value(229, 0, sheet_name);

			double guaranteedFutureValue = GetExcelFormulaValue.get_formula_value(232, 0, sheet_name);
			double optionalFinalPayment = GetExcelFormulaValue.get_formula_value(232, 1, sheet_name);
			double pencePerExcessMileFinance = GetExcelFormulaValue.get_formula_value(232, 4, sheet_name);

			double vehicleCommission = GetExcelFormulaValue.get_formula_value(239, 0, sheet_name);
			double defaultFinanceCommission = GetExcelFormulaValue.get_formula_value(239, 1, sheet_name);

			double docFeeCommission = GetExcelFormulaValue.get_formula_value(242, 0, sheet_name);
			double totalCommission = GetExcelFormulaValue.get_formula_value(242, 1, sheet_name);

			// comparing actul and expected values

			boolean status = false;

			int count = 0;

			// 1
			if (terms == customer_quote_summary_terms) {
				LO.print("Terms found OK");
				System.out.println("Terms found OK");
				count++;
			} else {
				LO.print("Terms found wrong");
				System.err.println("Terms found wrong");
			}

			// 2
			if (miles == customer_quote_summary_miles) {
				LO.print("miles found OK");
				System.out.println("miles found OK");
				count++;
			} else {
				LO.print("miles found wrong");
				System.err.println("miles found wrong");
			}

			// 3
			if ((Difference.of_two_Double_Values(basicCashPrice, customer_quote_summary_basic_cash_price)) < 0.2) {
				LO.print("Basic Cash Price found OK");
				System.out.println("Basic Cash Price found OK");
				count++;
			} else {
				LO.print("Basic Cash Price found wrong");
				System.err.println("Basic Cash Price found wrong");
			}

			// 4
			if ((Difference.of_two_Double_Values(vat, customer_quote_summary_vat)) < 0.2) {
				LO.print("VAT found OK");
				System.out.println("VAT found OK");
				count++;
			} else {
				LO.print("VAT found wrong");
				System.err.println("VAT found wrong");
			}

			// 5
			if ((Difference.of_two_Double_Values(nonVATItems, customer_quote_summary_non_vat_items)) < 0.2) {
				LO.print("Non VAT Items Value found OK");
				System.out.println("Non VAT Items Value found OK");
				count++;
			} else {
				LO.print("Non VAT Items Value found wrong");
				System.err.println("Non VAT Items Value found wrong");
			}

			// 6
			if ((Difference.of_two_Double_Values(totalCashPrice, customer_quote_summary_total_cash_price)) < 0.2) {
				LO.print("Total Cash Price found OK");
				System.out.println("Total Cash Price found OK");
				count++;
			} else {
				LO.print("Total Cash Price found wrong");
				System.err.println("Total Cash Price found wrong");
			}

			// 7
			if ((Difference.of_two_Double_Values(orderDeposit, customer_quote_summary_order_deposit)) < 0.2) {
				LO.print("Order Deposit found OK");
				System.out.println("Order Deposit found OK");
				count++;
			} else {
				LO.print("Order Deposit found wrong");
				System.err.println("Order Deposit found wrong");
			}

			// 8
			if ((Difference.of_two_Double_Values(financeDeposit, customer_quote_summary_finance_deposit)) < 0.2) {
				LO.print("Finance Deposit found OK");
				System.out.println("Finance Deposit found OK");
				count++;
			} else {
				LO.print("Finance Deposit found wrong");
				System.err.println("Finance Deposit found wrong");
			}

			// 9
			if ((Difference.of_two_Double_Values(totalDeposit, customer_quote_summary_total_deposit)) < 0.2) {
				LO.print("Total Deposit found OK");
				System.out.println("Total Deposit found OK");
				count++;
			} else {
				LO.print("Total Deposit found wrong");
				System.err.println("Total Deposit found wrong");
			}

			// 10
			if ((Difference.of_two_Double_Values(balanceToFinance, customer_quote_summary_balance_to_finance)) < 0.2) {
				LO.print("Balance to Finance found OK");
				System.out.println("Balance to Finance found OK");
				count++;
			} else {
				LO.print("Balance to Finance found wrong");
				System.err.println("Balance to Finance found wrong");
			}

			// 11
			if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
				LO.print("Finance Charges - found OK");
				System.out.println("Finance Charges - found OK");
				count++;
			} else {
				LO.print("Finance Charges - found wrong");
				System.err.println("Finance Charges - found wrong");
			}

			// 12
			if ((Difference.of_two_Double_Values(documentFee, customer_quote_summary_document_fee)) < 0.2) {
				LO.print("Document Fee - found OK");
				System.out.println("Document Fee - found OK");
				count++;
			} else {
				LO.print("Document Fee - found wrong");
				System.err.println("Document Fee - found wrong");
			}

			// 13
			if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
				LO.print("Balance Payable - found OK");
				System.out.println("Balance Payable - found OK");
				count++;
			} else {
				LO.print("Balance Payable - found wrong");
				System.err.println("Balance Payable - found wrong");
			}

			// 14
			if ((Difference.of_two_Double_Values(optionToPurchaseFee,
					customer_quote_summary_option_to_purchase_fee)) < 0.2) {
				LO.print("Option To Purchase Fee - found OK");
				System.out.println("Option To Purchase Fee - found OK");
				count++;
			} else {
				LO.print("Option To Purchase Fee - found wrong");
				System.err.println("Option To Purchase Fee - found wrong");
			}

			// 15
			if ((Difference.of_two_Double_Values(initialCashPayment,
					customer_quote_summary_initial_cash_payment)) < 0.2) {
				LO.print("Initial Cash Payment - found OK");
				System.out.println("Initial Cash Payment - found OK");
				count++;
			} else {
				LO.print("Initial Cash Payment - found wrong");
				System.err.println("Initial Cash Payment - found wrong");
			}

			// 16
			if (followedBy == customer_payment_followed_by) {
				LO.print("Followed By months - found OK");
				System.out.println("Followed By months - found OK");
				count++;
			} else {
				LO.print("Followed By months - found wrong");
				System.err.println("Followed By months - found wrong");
			}

			// 17
			if ((Difference.of_two_Double_Values(monthlyFinancePayment,
					customer_quote_summary_monthly_finance_payment)) < 0.2) {
				LO.print("Monthly Finance Payment - found OK");
				System.out.println("Monthly Finance Payment - found OK");
				count++;
			} else {
				LO.print("Monthly Finance Payment - found wrong");
				System.err.println("Monthly Finance Payment - found wrong");
			}

			// 18
			if ((Difference.of_two_Double_Values(guaranteedFutureValue,
					customer_quote_summary_guaranteed_future_value)) < 0.2) {
				LO.print("Balloon Value - found OK");
				System.out.println("Balloon Value - found OK");
				count++;
			} else {
				LO.print("Balloon Value - found wrong");
				System.err.println("Balloon Value - found wrong");
			}

			// 19
			if ((Difference.of_two_Double_Values(optionalFinalPayment,
					customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee)) < 0.2) {
				LO.print("Final Payment - found OK");
				System.out.println("Final Payment - found OK");
				count++;
			} else {
				LO.print("Final Payment - found wrong");
				System.err.println("Final Payment - found wrong");
			}

			// 20
			if ((Difference.of_two_Double_Values(pencePerExcessMileFinance,
					customer_quote_summary_pence_per_excess_mile_finance)) < 0.2) {
				LO.print("Pence Per Excess Mile Finance - found OK");
				System.out.println("Pence Per Excess Mile Finance - found OK");
				count++;
			} else {
				LO.print("Pence Per Excess Mile Finance - found wrong");
				System.err.println("Pence Per Excess Mile Finance - found wrong");
			}

			// 21
			if ((Difference.of_two_Double_Values(vehicleCommission, customer_quote_summary_vehicle_comm)) < 0.2) {
				LO.print("Vehicle Commission - found OK");
				System.out.println("Vehicle Commission - found OK");
				count++;
			} else {
				LO.print("Vehicle Commission - found wrong");
				System.err.println("Vehicle Commission - found wrong");
			}

			// 22
			if ((Difference.of_two_Double_Values(defaultFinanceCommission,
					customer_quote_summary_default_finance_comm)) < 0.2) {
				LO.print("Default Finance Commission - found OK");
				System.out.println("Default Finance Commission - found OK");
				count++;
			} else {
				LO.print("Default Finance Commission - found wrong");
				System.err.println("Default Finance Commission - found wrong");
			}

			// 23
			if ((Difference.of_two_Double_Values(docFeeCommission, customer_quote_summary_document_fee_comm)) < 0.2) {
				LO.print("Document Fee Commission - found OK");
				System.out.println("Document Fee Commission - found OK");
				count++;
			} else {
				LO.print("Document Fee Commission - found wrong");
				System.err.println("Document Fee Commission - found wrong");
			}

			// 24
			if ((Difference.of_two_Double_Values(totalCommission, customer_quote_summary_total_commission)) < 0.2) {
				LO.print("Total Commission - found OK");
				System.out.println("Total Commission - found OK");
				count++;
			} else {
				LO.print("Total Commission - found wrong");
				System.err.println("Total Commission - found wrong");
			}

			if (count == 24) {
				status = true;
			}
			return status;

		}

	}

	public boolean quote_summary_customer_quote_summary_value_verification_for_funder_without_maintenance(
			String sheet_name) throws IOException {

		LO.print("*************Customer Quote Calulation on quote summary page has been started************");
		System.out.println("*************Customer Quote Calulation on quote summary page has been started************");

		// Clicking on Customer quote pannel
		Click.on(driver, quote_summary_customer_quote_summary_value_verification, 20);

		// waiting for elements
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_terms, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_miles_per_annum, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_basic_cash_price, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_vat, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_non_vat_items, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_cash_price, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_order_deposit, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_deposit, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_deposit, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_to_finance, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_charges, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_document_fee, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_payable, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_option_to_purchase_fee, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_initial_cash_payment, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_followed_by, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_monthly_finance_payment, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_guaranteed_future_value, 20);
		ExplicitWait.visibleElement(driver,
				quote_summary_customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_finance, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_vehicle_comm, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_default_finance_comm, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_document_fee_comm, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_total_comm, 20);

		// getting text from elements

		double customer_quote_summary_terms = Double
				.parseDouble(quote_summary_customer_quote_summary_terms.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double
				.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_miles_per_annum.getText().trim()));

		double customer_quote_summary_basic_cash_price = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_vat = Double
				.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_vat.getText().trim().substring(2)));

		double customer_quote_summary_non_vat_items = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_non_vat_items.getText().trim().substring(2)));

		double customer_quote_summary_total_cash_price = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_total_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_order_deposit = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_order_deposit.getText().trim().substring(2)));

		double customer_quote_summary_finance_deposit = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_finance_deposit.getText().trim().substring(2)));

		double customer_quote_summary_total_deposit = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_total_deposit.getText().trim().substring(2)));

		double customer_quote_summary_balance_to_finance = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));

		double customer_quote_summary_finance_charges = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_finance_charges.getText().trim().substring(2)));

		double customer_quote_summary_document_fee = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_document_fee.getText().trim().substring(2)));

		double customer_quote_summary_balance_payable = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_balance_payable.getText().trim().substring(2)));

		System.out.println("customer_quote_summary_balance_payable" + customer_quote_summary_balance_payable);

		double customer_quote_summary_option_to_purchase_fee = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));

		double customer_quote_summary_initial_cash_payment = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_initial_cash_payment.getText().trim().substring(2)));

		double customer_payment_followed_by = Double
				.parseDouble(quote_summary_customer_quote_summary_followed_by.getText().substring(0, 2));

		double customer_quote_summary_monthly_finance_payment = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));

		double customer_quote_summary_guaranteed_future_value = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_guaranteed_future_value.getText().trim().substring(2)));

		double customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee
						.getText().trim().substring(2)));

		double customer_quote_summary_pence_per_excess_mile_finance = Double.parseDouble(RemoveComma.of(
				quote_summary_customer_quote_summary_pence_per_excess_mile_finance.getText().trim().substring(0, 4)));

		double customer_quote_summary_vehicle_comm = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_vehicle_comm.getText().trim().substring(2)));

		double customer_quote_summary_default_finance_comm = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_summary_default_finance_comm.getText().trim().substring(2)));

		double customer_quote_summary_document_fee_comm = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_document_fee_comm.getText().trim().substring(2)));

		double customer_quote_summary_total_commission = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_total_comm.getText().trim().substring(2)));

		// writing balloon value to excel

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(169).getCell(4).setCellValue(customer_quote_summary_guaranteed_future_value);
		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		// getting values from excel

		double terms = GetExcelFormulaValue.get_string_value(208, 1, sheet_name);
		double miles = GetExcelFormulaValue.get_string_value(208, 4, sheet_name);

		double basicCashPrice = GetExcelFormulaValue.get_formula_value(214, 0, sheet_name);
		double vat = GetExcelFormulaValue.get_formula_value(214, 1, sheet_name);
		double nonVATItems = GetExcelFormulaValue.get_formula_value(214, 4, sheet_name);

		double totalCashPrice = GetExcelFormulaValue.get_formula_value(217, 0, sheet_name);
		double orderDeposit = GetExcelFormulaValue.get_formula_value(217, 1, sheet_name);
		double financeDeposit = GetExcelFormulaValue.get_formula_value(217, 4, sheet_name);

		double totalDeposit = GetExcelFormulaValue.get_formula_value(220, 0, sheet_name);
		double balanceToFinance = GetExcelFormulaValue.get_formula_value(220, 4, sheet_name);

		double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheet_name);
		double documentFee = GetExcelFormulaValue.get_string_value(223, 1, sheet_name);
		double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheet_name);

		double optionToPurchaseFee = GetExcelFormulaValue.get_formula_value(226, 0, sheet_name);
		double initialCashPayment = GetExcelFormulaValue.get_formula_value(226, 1, sheet_name);
		double followedBy = GetExcelFormulaValue.get_formula_value(226, 4, sheet_name);

		double monthlyFinancePayment = GetExcelFormulaValue.get_formula_value(229, 0, sheet_name);

		double guaranteedFutureValue = GetExcelFormulaValue.get_formula_value(232, 0, sheet_name);
		double optionalFinalPayment = GetExcelFormulaValue.get_formula_value(232, 1, sheet_name);
		double pencePerExcessMileFinance = GetExcelFormulaValue.get_formula_value(232, 4, sheet_name);

		double vehicleCommission = GetExcelFormulaValue.get_formula_value(239, 0, sheet_name);
		double defaultFinanceCommission = GetExcelFormulaValue.get_formula_value(239, 1, sheet_name);

		double docFeeCommission = GetExcelFormulaValue.get_formula_value(242, 0, sheet_name);
		double totalCommission = GetExcelFormulaValue.get_formula_value(242, 1, sheet_name);

		// comparing actul and expected values

		boolean status = false;

		int count = 0;

		// 1
		if (terms == customer_quote_summary_terms) {
			LO.print("Terms found OK");
			System.out.println("Terms found OK");
			count++;
		} else {
			LO.print("Terms found wrong");
			System.err.println("Terms found wrong");
		}

		// 2
		if (miles == customer_quote_summary_miles) {
			LO.print("miles found OK");
			System.out.println("miles found OK");
			count++;
		} else {
			LO.print("miles found wrong");
			System.err.println("miles found wrong");
		}

		// 3
		if ((Difference.of_two_Double_Values(basicCashPrice, customer_quote_summary_basic_cash_price)) < 0.2) {
			LO.print("Basic Cash Price found OK");
			System.out.println("Basic Cash Price found OK");
			count++;
		} else {
			LO.print("Basic Cash Price found wrong");
			System.err.println("Basic Cash Price found wrong");
		}

		// 4
		if ((Difference.of_two_Double_Values(vat, customer_quote_summary_vat)) < 0.2) {
			LO.print("VAT found OK");
			System.out.println("VAT found OK");
			count++;
		} else {
			LO.print("VAT found wrong");
			System.err.println("VAT found wrong");
		}

		// 5
		if ((Difference.of_two_Double_Values(nonVATItems, customer_quote_summary_non_vat_items)) < 0.2) {
			LO.print("Non VAT Items Value found OK");
			System.out.println("Non VAT Items Value found OK");
			count++;
		} else {
			LO.print("Non VAT Items Value found wrong");
			System.err.println("Non VAT Items Value found wrong");
		}

		// 6
		if ((Difference.of_two_Double_Values(totalCashPrice, customer_quote_summary_total_cash_price)) < 0.2) {
			LO.print("Total Cash Price found OK");
			System.out.println("Total Cash Price found OK");
			count++;
		} else {
			LO.print("Total Cash Price found wrong");
			System.err.println("Total Cash Price found wrong");
		}

		// 7
		if ((Difference.of_two_Double_Values(orderDeposit, customer_quote_summary_order_deposit)) < 0.2) {
			LO.print("Order Deposit found OK");
			System.out.println("Order Deposit found OK");
			count++;
		} else {
			LO.print("Order Deposit found wrong");
			System.err.println("Order Deposit found wrong");
		}

		// 8
		if ((Difference.of_two_Double_Values(financeDeposit, customer_quote_summary_finance_deposit)) < 0.2) {
			LO.print("Finance Deposit found OK");
			System.out.println("Finance Deposit found OK");
			count++;
		} else {
			LO.print("Finance Deposit found wrong");
			System.err.println("Finance Deposit found wrong");
		}

		// 9
		if ((Difference.of_two_Double_Values(totalDeposit, customer_quote_summary_total_deposit)) < 0.2) {
			LO.print("Total Deposit found OK");
			System.out.println("Total Deposit found OK");
			count++;
		} else {
			LO.print("Total Deposit found wrong");
			System.err.println("Total Deposit found wrong");
		}

		// 10
		if ((Difference.of_two_Double_Values(balanceToFinance, customer_quote_summary_balance_to_finance)) < 0.2) {
			LO.print("Balance to Finance found OK");
			System.out.println("Balance to Finance found OK");
			count++;
		} else {
			LO.print("Balance to Finance found wrong");
			System.err.println("Balance to Finance found wrong");
		}

		// 11
		if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
			LO.print("Finance Charges - found OK");
			System.out.println("Finance Charges - found OK");
			count++;
		} else {
			LO.print("Finance Charges - found wrong");
			System.err.println("Finance Charges - found wrong");
		}

		// 12
		if ((Difference.of_two_Double_Values(documentFee, customer_quote_summary_document_fee)) < 0.2) {
			LO.print("Document Fee - found OK");
			System.out.println("Document Fee - found OK");
			count++;
		} else {
			LO.print("Document Fee - found wrong");
			System.err.println("Document Fee - found wrong");
		}

		// 13
		if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
			LO.print("Balance Payable - found OK");
			System.out.println("Balance Payable - found OK");
			count++;
		} else {
			LO.print("Balance Payable - found wrong");
			System.err.println("Balance Payable - found wrong");
		}

		// 14
		if ((Difference.of_two_Double_Values(optionToPurchaseFee,
				customer_quote_summary_option_to_purchase_fee)) < 0.2) {
			LO.print("Option To Purchase Fee - found OK");
			System.out.println("Option To Purchase Fee - found OK");
			count++;
		} else {
			LO.print("Option To Purchase Fee - found wrong");
			System.err.println("Option To Purchase Fee - found wrong");
		}

		// 15
		if ((Difference.of_two_Double_Values(initialCashPayment, customer_quote_summary_initial_cash_payment)) < 0.2) {
			LO.print("Initial Cash Payment - found OK");
			System.out.println("Initial Cash Payment - found OK");
			count++;
		} else {
			LO.print("Initial Cash Payment - found wrong");
			System.err.println("Initial Cash Payment - found wrong");
		}

		// 16
		if (followedBy == customer_payment_followed_by) {
			LO.print("Followed By months - found OK");
			System.out.println("Followed By months - found OK");
			count++;
		} else {
			LO.print("Followed By months - found wrong");
			System.err.println("Followed By months - found wrong");
		}

		// 17
		if ((Difference.of_two_Double_Values(monthlyFinancePayment,
				customer_quote_summary_monthly_finance_payment)) < 0.2) {
			LO.print("Monthly Finance Payment - found OK");
			System.out.println("Monthly Finance Payment - found OK");
			count++;
		} else {
			LO.print("Monthly Finance Payment - found wrong");
			System.err.println("Monthly Finance Payment - found wrong");
		}

		// 18
		if ((Difference.of_two_Double_Values(guaranteedFutureValue,
				customer_quote_summary_guaranteed_future_value)) < 0.2) {
			LO.print("Guaranteed Future Value - found OK");
			System.out.println("Guaranteed Future Value - found OK");
			count++;
		} else {
			LO.print("Guaranteed Future Value - found wrong");
			System.err.println("Guaranteed Future Value - found wrong");
		}

		// 19
		if ((Difference.of_two_Double_Values(optionalFinalPayment,
				customer_quote_summary_optional_final_payment_inc_option_to_purchase_fee)) < 0.2) {
			LO.print("Optional Final Payment - found OK");
			System.out.println("Optional Final Payment - found OK");
			count++;
		} else {
			LO.print("Optional Final Payment - found wrong");
			System.err.println("Optional Final Payment - found wrong");
		}

		// 20
		if ((Difference.of_two_Double_Values(pencePerExcessMileFinance,
				customer_quote_summary_pence_per_excess_mile_finance)) < 0.2) {
			LO.print("Pence Per Excess Mile Finance - found OK");
			System.out.println("Pence Per Excess Mile Finance - found OK");
			count++;
		} else {
			LO.print("Pence Per Excess Mile Finance - found wrong");
			System.err.println("Pence Per Excess Mile Finance - found wrong");
		}

		// 21
		if ((Difference.of_two_Double_Values(vehicleCommission, customer_quote_summary_vehicle_comm)) < 0.2) {
			LO.print("Vehicle Commission - found OK");
			System.out.println("Vehicle Commission - found OK");
			count++;
		} else {
			LO.print("Vehicle Commission - found wrong");
			System.err.println("Vehicle Commission - found wrong");
		}

		// 22
		if ((Difference.of_two_Double_Values(defaultFinanceCommission,
				customer_quote_summary_default_finance_comm)) < 0.2) {
			LO.print("Default Finance Commission - found OK");
			System.out.println("Default Finance Commission - found OK");
			count++;
		} else {
			LO.print("Default Finance Commission - found wrong");
			System.err.println("Default Finance Commission - found wrong");
		}

		// 23
		if ((Difference.of_two_Double_Values(docFeeCommission, customer_quote_summary_document_fee_comm)) < 0.2) {
			LO.print("Document Fee Commission - found OK");
			System.out.println("Document Fee Commission - found OK");
			count++;
		} else {
			LO.print("Document Fee Commission - found wrong");
			System.err.println("Document Fee Commission - found wrong");
		}

		// 24
		if ((Difference.of_two_Double_Values(totalCommission, customer_quote_summary_total_commission)) < 0.2) {
			LO.print("Total Commission - found OK");
			System.out.println("Total Commission - found OK");
			count++;
		} else {
			LO.print("Total Commission - found wrong");
			System.err.println("Total Commission - found wrong");
		}

		if (count == 24) {
			status = true;
		}
		return status;

	}

	public boolean quote_summary_edit_base_int_rate_value_verification_for_funder_without_maintenance(String sheet_name)
			throws IOException, InterruptedException {

		LO.print(
				"*************Editing Base Interest Rate and Verifying  Values on quote summary page has been started************");
		System.out.println(
				"*************Editing Base Interest Rate and Verifying  Values on quote summary page has been started************");

		// Edit base interest rate configuration values from screen
		ExplicitWait.visibleElement(driver, quote_summary_configuration_base_int_rate_input, 30);
		quote_summary_configuration_base_int_rate_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		quote_summary_configuration_base_int_rate_input.sendKeys("7.0");

		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		LO.print("Base Interest Rate changed to 7.0 %");
		System.out.println("Base Interest Rate changed to 7.0 %");

		// Getting values from screen

		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 30);

		double holding_cost_total_monthly_holding_cost_from_screen = Double
				.parseDouble(RemoveComma.of(quote_summary_total_monthly_holding_cost.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 30);
		double customer_quote_summary_monthly_finance_rental_from_screen = Double
				.parseDouble(RemoveComma.of(quote_summary_monthly_finance_rental.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_charges, 20);

		double customer_quote_summary_finance_charges = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_finance_charges.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_payable, 20);
		double customer_quote_summary_balance_payable = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_balance_payable.getText().trim().substring(2)));

		// writing values to excel

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(34).getCell(10).setCellValue(0.07);
		wb.getSheet(sheet_name).getRow(62).getCell(1).setCellValue(0.07);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		// getting values from excel

		double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(52, 1,
				sheet_name);

		double monthlyFinanceRental = GetExcelFormulaValue.get_formula_value(166, 0, sheet_name);

		double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheet_name);

		double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheet_name);

		// verifying actual and expected values

		int count = 0;

		boolean status = false;

		// 1
		if (Difference.of_two_Double_Values(holding_cost_total_monthly_holding_cost_from_screen,
				holding_cost_total_monthly_holding_cost_from_excel) < 0.2) {
			LO.print("Holding Cost after changing Base Int. Rate -  found OK");
			System.out.println("Holding Cost after changing Base Int. Rate -  found OK");
			count++;
		} else {
			LO.print("Holding Cost after changing Base Int. Rate -  found wrong");
			System.err.println("Holding Cost after changing Base Int. Rate -  found wrong");
		}
		// 2
		if (Difference.of_two_Double_Values(customer_quote_summary_monthly_finance_rental_from_screen,
				monthlyFinanceRental) < 0.2) {
			LO.print("Monthly Finance Rental after changing Base Int. Rate -  found OK");
			System.out.println("Monthly Finance Rental after changing Base Int. Rate -  found OK");
			count++;
		} else {
			LO.print("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
			System.err.println("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
		}
		// 3

		if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
			LO.print("Finance Charges - found OK");
			System.out.println("Finance Charges - found OK");
			count++;
		} else {
			LO.print("Finance Charges - found wrong");
			System.err.println("Finance Charges - found wrong");
		}

		// 4
		if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
			LO.print("Balance Payable - found OK");
			System.out.println("Balance Payable - found OK");
			count++;
		} else {
			LO.print("Balance Payable - found wrong");
			System.err.println("Balance Payable - found wrong");
		}

		if (count == 4) {
			status = true;
		}

		ExplicitWait.visibleElement(driver, quote_summary_configuration_base_int_rate_input, 30);
		quote_summary_configuration_base_int_rate_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		quote_summary_configuration_base_int_rate_input.sendKeys("6.5");

		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		LO.print("Base Interest Rate changed to 6.5 %");
		System.out.println("Base Interest Rate changed to 6.5 %");

		// writing values to excel

		FileInputStream in1 = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb1 = new XSSFWorkbook(in1);

		wb1.getSheet(sheet_name).getRow(34).getCell(10).setCellValue(0.065);
		wb1.getSheet(sheet_name).getRow(62).getCell(1).setCellValue(0.065);

		FileOutputStream out1 = new FileOutputStream(prop.getProperty("formula_excel_path"));

		wb1.write(out1);

		return status;
	}

	public boolean quote_summary_edit_base_int_rate_value_verification_for_funder_with_maintenance(String sheet_name)
			throws IOException, InterruptedException {

		LO.print(
				"*************Editing Base Interest Rate and Verifying  Values on quote summary page has been started************");
		System.out.println(
				"*************Editing Base Interest Rate and Verifying  Values on quote summary page has been started************");

		try {
			// Edit base interest rate configuration values from screen
			ExplicitWait.visibleElement(driver, quote_summary_configuration_base_int_rate_input, 30);
			quote_summary_configuration_base_int_rate_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			quote_summary_configuration_base_int_rate_input.sendKeys("7.0");

			Actions act = new Actions(driver);
			act.sendKeys(Keys.TAB).build().perform();

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			LO.print("Base Interest Rate changed to 7.0 %");
			System.out.println("Base Interest Rate changed to 7.0 %");

			// Getting values from screen

			ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 30);

			double holding_cost_total_monthly_holding_cost_from_screen = Double.parseDouble(
					RemoveComma.of(quote_summary_total_monthly_holding_cost.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 30);
			double customer_quote_summary_monthly_finance_rental_from_screen = Double
					.parseDouble(RemoveComma.of(quote_summary_monthly_finance_rental.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_monthly_maint_payment, 30);
			double customer_quote_summary_monthly_maint_rental_from_screen = Double.parseDouble(RemoveComma
					.of(quote_summary_customer_quote_summary_monthly_maint_payment.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_charges, 20);

			double customer_quote_summary_finance_charges = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_finance_charges.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_payable, 20);
			double customer_quote_summary_balance_payable = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_balance_payable.getText().trim().substring(2)));

			// writing values to excel

			FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
			XSSFWorkbook wb = new XSSFWorkbook(in);

			wb.getSheet(sheet_name).getRow(34).getCell(10).setCellValue(0.07);
			wb.getSheet(sheet_name).getRow(62).getCell(1).setCellValue(0.07);

			FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
			wb.write(out);

			// getting values from excel

			double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(52, 1,
					sheet_name);

			double monthlyFinanceRental = GetExcelFormulaValue.get_formula_value(166, 0, sheet_name);

			double monthlyMaintRental = GetExcelFormulaValue.get_formula_value(166, 1, sheet_name);

			double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheet_name);

			double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheet_name);

			// verifying actual and expected values

			int count = 0;

			boolean status = false;

			// 1
			if (Difference.of_two_Double_Values(holding_cost_total_monthly_holding_cost_from_screen,
					holding_cost_total_monthly_holding_cost_from_excel) < 0.2) {
				LO.print("Holding Cost after changing Base Int. Rate -  found OK");
				System.out.println("Holding Cost after changing Base Int. Rate -  found OK");
				count++;
			} else {
				LO.print("Holding Cost after changing Base Int. Rate -  found wrong");
				System.err.println("Holding Cost after changing Base Int. Rate -  found wrong");
			}
			// 2
			if (Difference.of_two_Double_Values(customer_quote_summary_monthly_finance_rental_from_screen,
					monthlyFinanceRental) < 0.2) {
				LO.print("Monthly Finance Rental after changing Base Int. Rate -  found OK");
				System.out.println("Monthly Finance Rental after changing Base Int. Rate -  found OK");
				count++;
			} else {
				LO.print("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
				System.err.println("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
			}
			// 3
			if (Difference.of_two_Double_Values(customer_quote_summary_monthly_maint_rental_from_screen,
					monthlyMaintRental) < 0.2) {
				LO.print("Monthly Maint Rental after changing Base Int. Rate -  found OK");
				System.out.println("Monthly Maint Rental after changing Base Int. Rate -  found OK");
				count++;
			} else {
				LO.print("Monthly Maint Rental after changing Base Int. Rate -  found wrong");
				System.err.println("Monthly Maint Rental after changing Base Int. Rate -  found wrong");
			}
			// 4
			if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
				LO.print("Finance Charges - found OK");
				System.out.println("Finance Charges - found OK");
				count++;
			} else {
				LO.print("Finance Charges - found wrong");
				System.err.println("Finance Charges - found wrong");
			}

			// 5
			if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
				LO.print("Balance Payable - found OK");
				System.out.println("Balance Payable - found OK");
				count++;
			} else {
				LO.print("Balance Payable - found wrong");
				System.err.println("Balance Payable - found wrong");
			}

			if (count == 5) {
				status = true;
			}

			ExplicitWait.visibleElement(driver, quote_summary_configuration_base_int_rate_input, 30);
			quote_summary_configuration_base_int_rate_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			quote_summary_configuration_base_int_rate_input.sendKeys("6.5");

			act.sendKeys(Keys.TAB).build().perform();

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			LO.print("Base Interest Rate changed to 6.5 %");
			System.out.println("Base Interest Rate changed to 6.5 %");

			// writing values to excel

			FileInputStream in1 = new FileInputStream(prop.getProperty("formula_excel_path"));
			XSSFWorkbook wb1 = new XSSFWorkbook(in1);

			wb1.getSheet(sheet_name).getRow(34).getCell(10).setCellValue(0.065);
			wb1.getSheet(sheet_name).getRow(62).getCell(1).setCellValue(0.065);

			FileOutputStream out1 = new FileOutputStream(prop.getProperty("formula_excel_path"));

			wb1.write(out1);

			return status;
		} catch (Exception e) {

			// Edit base interest rate configuration values from screen
			ExplicitWait.visibleElement(driver, quote_summary_configuration_base_int_rate_input, 30);
			quote_summary_configuration_base_int_rate_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			quote_summary_configuration_base_int_rate_input.sendKeys("7.0");

			Actions act = new Actions(driver);
			act.sendKeys(Keys.TAB).build().perform();

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			LO.print("Base Interest Rate changed to 7.0 %");
			System.out.println("Base Interest Rate changed to 7.0 %");

			// Getting values from screen

			ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 30);

			double holding_cost_total_monthly_holding_cost_from_screen = Double.parseDouble(
					RemoveComma.of(quote_summary_total_monthly_holding_cost.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 30);
			double customer_quote_summary_monthly_finance_rental_from_screen = Double
					.parseDouble(RemoveComma.of(quote_summary_monthly_finance_rental.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_finance_charges, 20);

			double customer_quote_summary_finance_charges = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_finance_charges.getText().trim().substring(2)));

			ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_balance_payable, 20);
			double customer_quote_summary_balance_payable = Double.parseDouble(
					RemoveComma.of(quote_summary_customer_quote_summary_balance_payable.getText().trim().substring(2)));

			// writing values to excel

			FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
			XSSFWorkbook wb = new XSSFWorkbook(in);

			wb.getSheet(sheet_name).getRow(34).getCell(10).setCellValue(0.07);
			wb.getSheet(sheet_name).getRow(62).getCell(1).setCellValue(0.07);

			FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
			wb.write(out);

			// getting values from excel

			double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(52, 1,
					sheet_name);

			double monthlyFinanceRental = GetExcelFormulaValue.get_formula_value(166, 0, sheet_name);

			double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheet_name);

			double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheet_name);

			// verifying actual and expected values

			int count = 0;

			boolean status = false;

			// 1
			if (Difference.of_two_Double_Values(holding_cost_total_monthly_holding_cost_from_screen,
					holding_cost_total_monthly_holding_cost_from_excel) < 0.2) {
				LO.print("Holding Cost after changing Base Int. Rate -  found OK");
				System.out.println("Holding Cost after changing Base Int. Rate -  found OK");
				count++;
			} else {
				LO.print("Holding Cost after changing Base Int. Rate -  found wrong");
				System.err.println("Holding Cost after changing Base Int. Rate -  found wrong");
			}
			// 2
			if (Difference.of_two_Double_Values(customer_quote_summary_monthly_finance_rental_from_screen,
					monthlyFinanceRental) < 0.2) {
				LO.print("Monthly Finance Rental after changing Base Int. Rate -  found OK");
				System.out.println("Monthly Finance Rental after changing Base Int. Rate -  found OK");
				count++;
			} else {
				LO.print("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
				System.err.println("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
			}
			// 3

			if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
				LO.print("Finance Charges - found OK");
				System.out.println("Finance Charges - found OK");
				count++;
			} else {
				LO.print("Finance Charges - found wrong");
				System.err.println("Finance Charges - found wrong");
			}

			// 4
			if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
				LO.print("Balance Payable - found OK");
				System.out.println("Balance Payable - found OK");
				count++;
			} else {
				LO.print("Balance Payable - found wrong");
				System.err.println("Balance Payable - found wrong");
			}

			if (count == 4) {
				status = true;
			}

			ExplicitWait.visibleElement(driver, quote_summary_configuration_base_int_rate_input, 30);
			quote_summary_configuration_base_int_rate_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			quote_summary_configuration_base_int_rate_input.sendKeys("6.5");

			act.sendKeys(Keys.TAB).build().perform();

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

			LO.print("Base Interest Rate changed to 6.5 %");
			System.out.println("Base Interest Rate changed to 6.5 %");

			// writing values to excel

			FileInputStream in1 = new FileInputStream(prop.getProperty("formula_excel_path"));
			XSSFWorkbook wb1 = new XSSFWorkbook(in1);

			wb1.getSheet(sheet_name).getRow(34).getCell(10).setCellValue(0.065);
			wb1.getSheet(sheet_name).getRow(62).getCell(1).setCellValue(0.065);

			FileOutputStream out1 = new FileOutputStream(prop.getProperty("formula_excel_path"));

			wb1.write(out1);

			return status;

		}
	}

	public boolean quote_summary_HPNR_CP_for_funder_quote_without_maintenance(String sheet_name)
			throws InterruptedException, IOException {

		LO.print("*************Calculations for Quote Summary page has been started************");
		System.out.println("*************Calculations for Quote Summary page has been started************");

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		Thread.sleep(2000);

		Click.on(driver, quote_summary, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		ExplicitWait.visibleElement(driver, quote_summary_ref_no, 120);
		ExplicitWait.visibleElement(driver, quote_summary_cost_otr_price, 60);
		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 60);
		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 60);
		ExplicitWait.visibleElement(driver, quote_summary_acq_contract_type, 60);
		ExplicitWait.visibleElement(driver, quote_summary_customer_contract_type, 60);

		LO.print("Reading values from sceen -Quote Summary Page");
		System.out.println("Reading values from sceen -Quote Summary Page");

		String quote_ref_no = quote_summary_ref_no.getText();
		String temp_quote_summary_cost_otr_price = quote_summary_cost_otr_price.getText().trim().substring(2);
		String temp_quote_summary_total_monthly_holding_cost = quote_summary_total_monthly_holding_cost
				.getText().trim().substring(2);
		String temp_quote_summary_monthly_finance_rental = quote_summary_monthly_finance_rental.getText().trim()
				.substring(2);
		String acq_contract_type = quote_summary_acq_contract_type.getText();
		String customer_contract_type = quote_summary_customer_contract_type.getText();

		LO.print("Getting values from screen");
		System.out.println("Getting values from screen");

		LO.print("Quote_summary_cost_otr_price =" + temp_quote_summary_cost_otr_price);
		System.out.println("Quote_summary_cost_otr_price =" + temp_quote_summary_cost_otr_price);

		LO.print("Quote_summary_total_monthly_holding_cost =" + temp_quote_summary_total_monthly_holding_cost);
		System.out
				.println("Quote_summary_total_monthly_holding_cost =" + temp_quote_summary_total_monthly_holding_cost);

		LO.print("Quote_summary_monthly_finance_rental =" + temp_quote_summary_monthly_finance_rental);
		System.out.println("Quote_summary_monthly_finance_rental =" + temp_quote_summary_monthly_finance_rental);

		LO.print("Acquisition contract_type =" + acq_contract_type);
		System.out.println("Acquisition contract_type =" + acq_contract_type);

		LO.print("Customer contract_type =" + customer_contract_type);
		System.out.println("Customer contract_type =" + customer_contract_type);

		LO.print("Customer Quote generated successfully and Quote_ref_no =" + quote_ref_no);
		System.out.println("Customer Quote generated successfully and Quote_ref_no =" + quote_ref_no);

		String quote_summary_cost_otr_price_from_screen = RemoveComma.of(temp_quote_summary_cost_otr_price);
		String quote_summary_total_monthly_holding_cost_from_screen = RemoveComma
				.of(temp_quote_summary_total_monthly_holding_cost);
		String quote_summary_monthly_finance_rental_from_screen = RemoveComma
				.of(temp_quote_summary_monthly_finance_rental);

		double quote_summary_cost_otr_price_from_screen_converted = Double
				.parseDouble(quote_summary_cost_otr_price_from_screen);
		double quote_summary_total_monthly_holding_cost_from_screen_converted = Double
				.parseDouble(quote_summary_total_monthly_holding_cost_from_screen);
		double quote_summary_monthly_finance_rental_from_screen_converted = Double
				.parseDouble(quote_summary_monthly_finance_rental_from_screen);

		return obj_read_excel_calculation_page
				.verify_quote_summary_values_from_excel_for_funder_quote_addition_without_maintenance(
						quote_summary_cost_otr_price_from_screen_converted,
						quote_summary_total_monthly_holding_cost_from_screen_converted,
						quote_summary_monthly_finance_rental_from_screen_converted, sheet_name);

	}


	public boolean quote_summary_HPNR_CP_without_maintenance(String sheet_name)
			throws InterruptedException, IOException {

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();
		Click.on(driver, quote_summary, 60);

		LO.print("*************Calculations for Quote Summary page has been started************");
		System.out.println("*************Calculations for Quote Summary page has been started************");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER).build().perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);
		ExplicitWait.visibleElement(driver, quote_summary_ref_no, 120);

		ExplicitWait.visibleElement(driver, quote_summary_cost_otr_price, 60);
		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 60);
		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 60);
		ExplicitWait.visibleElement(driver, quote_summary_acq_contract_type, 60);
		ExplicitWait.visibleElement(driver, quote_summary_customer_contract_type, 60);

		LO.print("Reading values from sceen -Quote Summary Page");
		System.out.println("Reading values from sceen -Quote Summary Page");

		String quote_ref_no = quote_summary_ref_no.getText();
		String temp_quote_summary_cost_otr_price = quote_summary_cost_otr_price.getText().trim().substring(2);
		String temp_quote_summary_total_monthly_holding_cost = quote_summary_total_monthly_holding_cost
				.getText().trim().substring(2);
		String temp_quote_summary_monthly_finance_rental = quote_summary_monthly_finance_rental.getText().trim()
				.substring(2);
		String acq_contract_type = quote_summary_acq_contract_type.getText();
		String customer_contract_type = quote_summary_customer_contract_type.getText();

		LO.print("Getting values from screen");
		System.out.println("Getting values from screen");

		LO.print("Quote_summary_cost_otr_price =" + temp_quote_summary_cost_otr_price);
		System.out.println("Quote_summary_cost_otr_price =" + temp_quote_summary_cost_otr_price);

		LO.print("Quote_summary_total_monthly_holding_cost =" + temp_quote_summary_total_monthly_holding_cost);
		System.out
				.println("Quote_summary_total_monthly_holding_cost =" + temp_quote_summary_total_monthly_holding_cost);

		LO.print("Quote_summary_monthly_finance_rental =" + temp_quote_summary_monthly_finance_rental);
		System.out.println("Quote_summary_monthly_finance_rental =" + temp_quote_summary_monthly_finance_rental);

		LO.print("Acquisition contract_type =" + acq_contract_type);
		System.out.println("Acquisition contract_type =" + acq_contract_type);

		LO.print("Customer contract_type =" + customer_contract_type);
		System.out.println("Customer contract_type =" + customer_contract_type);

		LO.print("Customer Quote generated successfully and Quote_ref_no =" + quote_ref_no);
		System.out.println("Customer Quote generated successfully and Quote_ref_no =" + quote_ref_no);

		String quote_summary_cost_otr_price_from_screen = RemoveComma.of(temp_quote_summary_cost_otr_price);
		String quote_summary_total_monthly_holding_cost_from_screen = RemoveComma
				.of(temp_quote_summary_total_monthly_holding_cost);
		String quote_summary_monthly_finance_rental_from_screen = RemoveComma
				.of(temp_quote_summary_monthly_finance_rental);

		double quote_summary_cost_otr_price_from_screen_converted = Double
				.parseDouble(quote_summary_cost_otr_price_from_screen);
		double quote_summary_total_monthly_holding_cost_from_screen_converted = Double
				.parseDouble(quote_summary_total_monthly_holding_cost_from_screen);
		double quote_summary_monthly_finance_rental_from_screen_converted = Double
				.parseDouble(quote_summary_monthly_finance_rental_from_screen);

		return obj_read_excel_calculation_page.verify_quote_summary_values_from_excel_without_maintenance(
				quote_summary_cost_otr_price_from_screen_converted,
				quote_summary_total_monthly_holding_cost_from_screen_converted,
				quote_summary_monthly_finance_rental_from_screen_converted, sheet_name);

	}


}
