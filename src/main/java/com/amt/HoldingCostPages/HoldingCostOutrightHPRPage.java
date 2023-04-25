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
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.ReadExcelCalculationForPurchaseAgreement;

public class HoldingCostOutrightHPRPage extends TestBase {
	ReadExcelCalculationForPurchaseAgreement obj_read_excel_calculation_page;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//*[@name='Terms']")
	private WebElement additional_terms;

	@FindBy(xpath = "//*[@name='MileagePerAnnum']")
	private WebElement additional_mileage;

	@FindBy(xpath = "//p[contains(text(),'Holding cost')]")
	private WebElement holding_cost;

	@FindBy(xpath = "//span[@class='slider round sliderRed']")
	private WebElement maintenance_toggle_button;

	@FindBy(xpath = "//*[contains(text(),' Holding cost summary ')]")
	private WebElement holding_cost_summary;

	@FindBy(xpath = "//*[contains(text(),'CAP residual value')]//ancestor::div[1]//p//strong")
	private WebElement holding_cost_summary_residual_value_used;

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

	public HoldingCostOutrightHPRPage() {
		PageFactory.initElements(driver, this);
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

}
