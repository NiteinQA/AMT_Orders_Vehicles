package com.amt.HoldingCostPages;

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
import com.amt.testUtil.Dropdown;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.ReadExcelCalculation;
import com.amt.testUtil.RemoveComma;

public class HoldingCost_BCH_BCH_Page extends TestBase {
	ReadExcelCalculation obj_read_excel_calculation_page;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//p[contains(text(),'Holding cost')]")
	private WebElement holding_cost;

	@FindBy(xpath = "//span[@class='slider round sliderRed']")
	private WebElement maintenance_toggle_button;

	@FindBy(xpath = "//*[contains(text(),'Term')]//ancestor::div[1]//p//strong")
	private WebElement holding_cost_summary_terms;

	@FindBy(xpath = "//*[contains(text(),'Miles per annum')]//ancestor::div[1]//p//strong")
	private WebElement holding_cost_summary_mileage;

	@FindBy(xpath = "//*[contains(text(),'Total monthly holding cost')]//ancestor::div[1]//p//strong")
	private WebElement total_monthly_holding_cost;

	@FindBy(xpath = "//*[contains(text(),'CAP residual value')]//ancestor::div[1]//p//strong")
	private WebElement holding_cost_summary_residual_value_used;

	@FindBy(xpath = "//input[@id='CapMaintenancePercentage']")
	private WebElement percentage_maintenance_cost_used;

	@FindBy(xpath = "//input[@id='ResidualValue']")
	private WebElement residual_value_used;

	@FindBy(xpath = "//input[@id='Maintenancevalue3']")
	private WebElement maintenance_cost_used;

	@FindBy(xpath = "//button[@class='btn btn-outline-secondary px-4 mr-2']")
	private WebElement add_funder_button;

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

	@FindBy(xpath = "//input[@id='monthlyFinanceRental']")
	private WebElement monthly_finance_rental;

	@FindBy(xpath = "//input[@id='documentFee']")
	private WebElement document_fee;

	@FindBy(xpath = "//input[@id='pencePerExcessMileageFinance']")
	private WebElement pense_per_excess_mile_finance;

	@FindBy(xpath = "//span[@class='slider round']")
	private WebElement funder_maintenance_toggle;

	@FindBy(xpath = "//span[@class='slider round sliderRed']")
	private WebElement common_maintenance_toggle;

	@FindBy(xpath = "//input[@id='monthlyMaintenanceRental']")
	private WebElement monthly_maintenance_rental;

	@FindBy(xpath = "//input[@id='pencePerExcessMileageMaintenance']")
	private WebElement pense_per_excess_mile_maintenance;

	@FindBy(xpath = "//i[@class='btn-icon-addAddress-white']")
	private WebElement add;

	public HoldingCost_BCH_BCH_Page() {
		PageFactory.initElements(driver, this);
	}

	public boolean verify_holding_cost_without_maintenance(String quoteReference, String quoteExpiryDate, String terms,
			String milesPerAnnum, String maintenance_required, String monthlyFinanceRental, String documentFee,
			String penceperExcessMileFinance, String sheet_name) throws IOException, InterruptedException {
		Click.on(driver, holding_cost, 30);

		LO.print("***********Entered in holding cost page ***********");
		System.out.println("***********Entered in holding cost page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, add_funder_button, 30);
		Thread.sleep(2000);
		Click.on(driver, funder, 30);

		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();

		Click.sendKeys(driver, quote_ref, quoteReference, 20);

		Click.sendKeys(driver, expiry_date, quoteExpiryDate, 20);

		Dropdown.select(driver, payment_profile_dropdown, 1, 20);

		Click.sendKeys(driver, duration, terms, 30);

		Click.sendKeys(driver, miles_per_annum, milesPerAnnum, 30);

		Click.on(driver, contract_mileage, 30);

		Click.sendKeys(driver, monthly_finance_rental, monthlyFinanceRental, 30);

		Click.sendKeys(driver, document_fee, documentFee, 30);

		Click.sendKeys(driver, pense_per_excess_mile_finance, penceperExcessMileFinance, 30);

		Thread.sleep(2000);

		Click.on(driver, add, 30);

		LO.print("Funder added successfully");
		System.out.println("Funder added successfully");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		ExplicitWait.visibleElement(driver, total_monthly_holding_cost, 30);
		double holding_cost_from_screen = Double
				.parseDouble(RemoveComma.of(total_monthly_holding_cost.getText().trim().substring(2)));
		double holding_cost_from_test_data = Double.parseDouble(monthlyFinanceRental);
		double document_fee_from_test_data = Double.parseDouble(documentFee);

		obj_read_excel_calculation_page = new ReadExcelCalculation();

		obj_read_excel_calculation_page.write_test_data_values_to_excel_for_bch_flow_without_maintenance(quoteReference,
				quoteExpiryDate, terms, milesPerAnnum, maintenance_required, monthlyFinanceRental, documentFee,
				penceperExcessMileFinance, sheet_name);

		double holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		double diff = Difference.of_two_Double_Values(holding_cost_from_screen, holding_cost_from_excel);
		boolean status = false;
		if (diff < 0.2) {
			status = true;
			LO.print("Holding cost verified successfully");
			System.out.println("Holding cost verified successfully");
		}

		return status;

	}

	public boolean verify_holding_cost_with_maintenance(String quoteReference, String quoteExpiryDate, String terms,
			String milesPerAnnum, String maintenance_required, String monthlyFinanceRental,
			String monthlyMaintenanceRental, String documentFee, String penceperExcessMileFinance,
			String penceperExcessMileMaintenance, String sheet_name) throws IOException, InterruptedException {
		Click.on(driver, holding_cost, 30);

		LO.print("***********Entered in holding cost page ***********");
		System.out.println("***********Entered in holding cost page ***********");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, add_funder_button, 30);
		Thread.sleep(2000);

		Click.on(driver, funder_maintenance_toggle, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, common_maintenance_toggle, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, funder, 30);

		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();

		Click.sendKeys(driver, quote_ref, quoteReference, 20);

		Click.sendKeys(driver, expiry_date, quoteExpiryDate, 20);

		Dropdown.select(driver, payment_profile_dropdown, 1, 20);

		Click.sendKeys(driver, duration, terms, 30);

		Click.sendKeys(driver, miles_per_annum, milesPerAnnum, 30);

		Click.on(driver, contract_mileage, 30);

		Click.sendKeys(driver, monthly_finance_rental, monthlyFinanceRental, 30);

		Click.sendKeys(driver, document_fee, documentFee, 30);

		Click.sendKeys(driver, pense_per_excess_mile_finance, penceperExcessMileFinance, 30);

		Click.sendKeys(driver, monthly_maintenance_rental, monthlyMaintenanceRental, 30);

		Click.sendKeys(driver, pense_per_excess_mile_maintenance, penceperExcessMileMaintenance, 30);

		Thread.sleep(2000);

		Click.on(driver, add, 30);

		LO.print("Funder added successfully");
		System.out.println("Funder added successfully");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		ExplicitWait.visibleElement(driver, total_monthly_holding_cost, 30);
		double holding_cost_from_screen = Double
				.parseDouble(RemoveComma.of(total_monthly_holding_cost.getText().trim().substring(2)));
		double holding_cost_from_test_data = Double.parseDouble(monthlyFinanceRental);
		double document_fee_from_test_data = Double.parseDouble(documentFee);

		obj_read_excel_calculation_page = new ReadExcelCalculation();

		obj_read_excel_calculation_page.write_test_data_values_to_excel_for_bch_flow_with_maintenance(quoteReference,
				quoteExpiryDate, terms, milesPerAnnum, maintenance_required, monthlyFinanceRental,
				monthlyMaintenanceRental, documentFee, penceperExcessMileFinance, penceperExcessMileMaintenance,
				sheet_name);

		double holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		double diff = Difference.of_two_Double_Values(holding_cost_from_screen, holding_cost_from_excel);

		System.out.println("holding_cost_from_screen" + holding_cost_from_screen);

		System.out.println("holding_cost_from_excel" + holding_cost_from_excel);

		boolean status = false;
		if (diff < 0.2) {
			status = true;
			LO.print("Holding cost verified successfully");
			System.out.println("Holding cost verified successfully");
		}

		return status;

	}
}
