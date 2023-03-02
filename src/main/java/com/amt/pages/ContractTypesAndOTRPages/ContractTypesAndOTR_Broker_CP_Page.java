package com.amt.pages.ContractTypesAndOTRPages;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
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
import com.amt.testUtil.ReadExcelCalculation;
import com.amt.testUtil.RemoveComma;

public class ContractTypesAndOTR_Broker_CP_Page extends TestBase {

	ReadExcelCalculation obj_read_excel_calculation_page;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//*[@id ='acqOTRHeader']")
	private WebElement acq_contractTypes;

	@FindBy(xpath = "//p[contains(text(),'Broker')]")
	private WebElement acq_contractTypes_option_broker;

	@FindBy(xpath = "//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/div[7]/div[1]/div[1]/div[3]/div[1]/button[1]")
	private WebElement quote_alert;

	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/app-acquisition-common-otr-calculations[1]/form[1]/div[1]/div[1]/div[2]")
	private WebElement acq_contractTypes_calculation_table_basic_price;

	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/app-acquisition-common-otr-calculations[1]/form[1]/div[1]/div[1]/div[3]")
	private WebElement acq_contractTypes_calculation_table_discount;

	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/app-acquisition-common-otr-calculations[1]/form[1]/div[1]/div[1]/div[4]")
	private WebElement acq_contractTypes_calculation_table_additional_discount;

	@FindBy(xpath = "//*[normalize-space()='Subtotal after discounts']//ancestor::div[1]//div[2]")
	private WebElement acq_contractTypes_subtotal_after_discounts;

	@FindBy(xpath = "//*[normalize-space()='RFL:']//ancestor::div[1]//p")
	private WebElement acq_contractTypes_road_tax_first_year;

	@FindBy(xpath = "//*[@id='roadTaxFirstYear']")
	private WebElement acq_contractTypes_road_tax_first_year_input;

	@FindBy(xpath = "//app-acquisition-common-otr-calculations//*[normalize-space()='Manufacturer delivery charges']//ancestor::div[1]//div[2]")
	private WebElement acq_contractTypes_manufacturer_delivery_charges;

	@FindBy(xpath = "//app-acquisition-common-otr-calculations//*[normalize-space()='First registration fee']//ancestor::div[1]//div[2]")
	private WebElement acq_contractTypes_first_registration_fee;

	@FindBy(xpath = "//app-acquisition-common-otr-calculations//*[normalize-space()='Rebate']//ancestor::div[1]//div[2]")
	private WebElement acq_contractTypes_rebate;

	@FindBy(xpath = "//*[normalize-space()='OTR for invoice:']//ancestor::div[1]//p")
	private WebElement acq_contractTypes_OTR_price;

	@FindBy(xpath = "//*[@id='ListingPriceNew']")
	private WebElement acq_contractTypes_table_calculation_basic_vehicle_price;

	@FindBy(xpath = "//*[@id='collapseTwo']/app-acquisition-common-otr-calculations/form/div[1]/div/div[2]/div[3]")
	private WebElement acq_contractTypes_table_calculation_basic_paint_price;

	@FindBy(xpath = "//*[@id='collapseTwo']/app-acquisition-common-otr-calculations/form/div[1]/div/div[2]/div[4]")
	private WebElement acq_contractTypes_table_calculation_basic_options_price;

	@FindBy(xpath = "(//p[contains(text(),'Contract Purchase')])[2]")
	private WebElement acq_contractTypes_customer_contract_CP;

	// other_support_type dropdown

	@FindBy(xpath = "//*[@name='OtherSupportType']")
	private WebElement other_support_type;

	// remarks_text

	@FindBy(xpath = "//*[@name='RemarksText']")
	private WebElement remarks_text;

	// other_support_value

	@FindBy(xpath = "//*[@name='OtherSupportValue']")
	private WebElement other_support_value;

	// add_other_support_button

	@FindBy(xpath = "//*[@class='hand-cursor addContractBtn minwidth40px']")
	private WebElement add_other_support_button;

	// on_road_price_for_calculation

	@FindBy(xpath = "(//*[normalize-space()='On the road price for calculation']//ancestor::div[1]/div)[2]")
	private WebElement on_road_price_for_calculation;

	// delete_other_support button

	@FindBy(xpath = "//*[@src='/assets/images/delete.svg']")
	private WebElement delete_other_support;

	public ContractTypesAndOTR_Broker_CP_Page() {
		PageFactory.initElements(driver, this);
	}

	public boolean contractTypes_and_OTR_selection_broker_cp(String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {

		Click.on(driver, acq_contractTypes, 40);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, acq_contractTypes_option_broker, 50);
		
		  Thread.sleep(3000);

		Actions act = new Actions(driver);

		act.sendKeys(Keys.TAB, Keys.TAB, Keys.ENTER).build().perform();

		LO.print("Acquisition Contract type option selected = Broker ");
		System.out.println("Acquisition Contract type option selected = Broker ");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

//	   Actions act = new Actions(driver);
//	   act.sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.ENTER).build().perform();

		Click.on(driver, acq_contractTypes_customer_contract_CP, 50);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		LO.print("Customer Contract type option selected = Contract Purchase(CP)");
		System.out.println("Customer Contract type option selected = Contract Purchase(CP)");

		ExplicitWait.visibleElement(driver, acq_contractTypes_table_calculation_basic_vehicle_price, 30);
		acq_contractTypes_table_calculation_basic_vehicle_price.click();

		acq_contractTypes_table_calculation_basic_vehicle_price.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String vehicle_price_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		obj_read_excel_calculation_page = new ReadExcelCalculation();

		double subtotal_after_discount_excel = obj_read_excel_calculation_page
				.verify_table_calculations_contract_types_page(driver, vehicle_price_copied,
						acq_contractTypes_table_calculation_basic_paint_price,
						acq_contractTypes_table_calculation_basic_options_price,
						acq_contractTypes_calculation_table_discount,
						acq_contractTypes_calculation_table_additional_discount, sheet_name);
		String subtotal_after_discount_actual = acq_contractTypes_subtotal_after_discounts.getText();

		LO.print("Subtotal after discount actual value from screen =" + subtotal_after_discount_actual);
		System.out.println("Subtotal after discount actual value from screen =" + subtotal_after_discount_actual);

		String str = subtotal_after_discount_actual.substring(2);

		String subtotal_after_discount_actual_converted = RemoveComma.of(str);

		double subtotal_after_discount_actual_from_screen = Double
				.parseDouble(subtotal_after_discount_actual_converted);
		boolean flag = false;
		double diff = Difference.of_two_Double_Values(subtotal_after_discount_excel,
				subtotal_after_discount_actual_from_screen);
		if (diff < 0.2) {
			flag = true;
		}

		return flag;

	}

	public boolean verify_after_discount_calculations_contract_types_page(String sheet_name) throws IOException {

		obj_read_excel_calculation_page = new ReadExcelCalculation();
		return obj_read_excel_calculation_page.verify_after_discount_calculations_contract_types_page(driver,
				acq_contractTypes_calculation_table_basic_price, acq_contractTypes_calculation_table_discount,
				acq_contractTypes_calculation_table_additional_discount,
				acq_contractTypes_manufacturer_delivery_charges, acq_contractTypes_road_tax_first_year,
				acq_contractTypes_first_registration_fee, acq_contractTypes_rebate, acq_contractTypes_OTR_price,
				sheet_name);
	}

	public boolean verify_other_support_calculations(String otherSupportValue, String sheet_name)
			throws IOException, InterruptedException {

		LO.print("Verification of OTR price for calculation after adding other support value has been started");
		System.out
				.println("Verification of OTR price for calculation after adding other support value has been started");

		// Adding other support
		for (int i = 0; i <= 2; i++) {

			Dropdown.select(driver, other_support_type, i, 20);

			Click.sendKeys(driver, remarks_text, " Support " + i, 20);

			Click.sendKeys(driver, other_support_value, otherSupportValue, 20);

			JavascriptExecutor jse = (JavascriptExecutor) driver;

			ExplicitWait.visibleElement(driver, add_other_support_button, 30);

			jse.executeScript("arguments[0].click();", add_other_support_button);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		}

		Thread.sleep(3000);

		// Deleting other support

		Click.on(driver, delete_other_support, 20);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		Click.on(driver, delete_other_support, 20);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		// reading OTR_for_calculation value from screen

		ExplicitWait.visibleElement(driver, on_road_price_for_calculation, 30);

		double onRoadPriceForCalculationActual = Double
				.parseDouble(RemoveComma.of(on_road_price_for_calculation.getText().trim().substring(2)));

		double otherSupportConverted = Double.parseDouble(otherSupportValue);

		// writing other support values to Excel

		obj_read_excel_calculation_page = new ReadExcelCalculation();

		double OTRValueExpected = obj_read_excel_calculation_page
				.verify_OTR_for_calculation_after_adding_other_support_values_to_excel(otherSupportConverted,
						sheet_name);

		ExplicitWait.visibleElement(driver, acq_contractTypes_OTR_price, 30);
		double onRoadPriceorInvoice = Double
				.parseDouble(RemoveComma.of(acq_contractTypes_OTR_price.getText().trim().substring(2)));

		System.out.println("");

		boolean status = false;

		if (Difference.of_two_Double_Values(OTRValueExpected, onRoadPriceForCalculationActual) < 0.2) {
			status = true;

			LO.print("OTR price for calculation after adding other support found OK");
			System.out.println("OTR price for calculation after adding other support found OK");
		}

		else {
			LO.print("OTR price for calculation after adding other support found wrong");
			System.err.println("OTR price for calculation after adding other support found wrong");

		}

		System.out.println("");

		LO.print("Verification of OTR price for calculation after adding other support value has been ended");
		System.out.println("Verification of OTR price for calculation after adding other support value has been ended");

		return status;

	}

}
