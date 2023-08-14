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
import com.amt.testUtil.ReadExcelCalculationForPurchaseAgreement;
import com.amt.testUtil.RemoveComma;

public class CustomerQuotePage_OP_OP_Page extends TestBase {

	CustomerQuotePage_OP_OP_Page obj_cust_quote_outright_bchPage;
	ReadExcelCalculationForPurchaseAgreement obj_read_excel_calculation_page;

	Clipboard clipboard;

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

	@FindBy(xpath = "//*[@id='lessFinanceSettlement']")
	private WebElement less_finance_Settlement;

	@FindBy(xpath = "//input[@id='depositRequired']")
	private WebElement deposit_required;

	@FindBy(xpath = "//*[contains(text(),' Holding cost summary ')]")
	private WebElement holding_cost_summary;

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

	@FindBy(xpath = "//input[@id='VehicleProfit']")
	private WebElement vehicle_profit_input;

	@FindBy(xpath = "(//*[normalize-space()='Vehicle sales price'])[2]//ancestor::div[2]//div[3]")
	private WebElement vehicle_sales_price;

	@FindBy(xpath = "//*[normalize-space()='On the road price']//ancestor::div[1]//div[2]//p//strong")
	private WebElement vehicle_otr_price;

	@FindBy(xpath = "//*[contains(text(),' Part exchange & additional payments ')]")
	private WebElement part_exchange_and_additional_payment_button;

	@FindBy(xpath = "//*[@id='collapseFirst']/div/div/div[5]/label/span")
	private WebElement balloon_payment_toggle;

	@FindBy(xpath = "//app-purchase-customer-quote-summary-header/div/div[6]/div/p/strong")
	private WebElement total_monthly_payment;

	@FindBy(xpath = "//input[@id='offInvoiceSupport']")
	private WebElement rebate_input_field;

	@FindBy(xpath = "//input[@id='salesTotal']|//input[@id='OnTheRoadPrice']")
	private WebElement vehicle_sale_price_used_vehicle;

	@FindBy(xpath = "//*[@id='registrationNumber']")
	private WebElement registration_number;

	@FindBy(xpath = "//*[normalize-space()='Search']")
	private WebElement search_button;

	@FindBy(xpath = "//*[@id='mileage']")
	private WebElement mileage;

	@FindBy(xpath = "//*[@id='partExchange']|//*[@id='partExchnage']")
	private WebElement given_part_exchange_value;

	@FindBy(xpath = "//*[normalize-space()='Part exchange & additional payments']")
	private WebElement part_exchange_payment;

	@FindBy(xpath = "//*[@id='otrPartExchange']")
	private WebElement actual_part_exchange_value;

	@FindBy(xpath = "//*[@id='lessFinanceSettlement']")
	private WebElement less_finance_settlement;

	@FindBy(xpath = "//*[@name='depositRequired']")
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

	@FindBy(xpath = "//*[normalize-space()='Net part exchange allowance']//ancestor::div[1]//p//strong")
	private WebElement net_part_exchange_allowance;

	@FindBy(xpath = "//*[normalize-space()='Pending amount']//ancestor::div[1]//p//strong")
	private WebElement pending_amount;

	Properties prop;


	public CustomerQuotePage_OP_OP_Page() {
		
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

		return status;
	}

	public boolean edit_vehicle_profit_and_check_monthly_total_payment(String vehicle_profit, String sheet_name)
			throws InterruptedException, UnsupportedFlavorException, IOException {

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
		double vehicleSalesPriceFromScreen = Double
				.parseDouble(RemoveComma.of(vehicle_sales_price.getText().trim().substring(2)));
		double vehicleOTRPriceFromScreen = Double
				.parseDouble(RemoveComma.of(vehicle_otr_price.getText().trim().substring(2)));
		double vehicleProfit = Double.parseDouble(vehicle_profit);
		double vehicleSalesPriceFromActual = vehicleOTRPriceFromScreen + (vehicleProfit * 1.2);

		double diff = Difference.of_two_Double_Values(vehicleSalesPriceFromScreen, vehicleSalesPriceFromActual);

		boolean status = true;

		if (diff < 0.2) {
			status = true;

			LO.print("Vehicle profit " + vehicleProfit + " added to OTR price " + vehicleOTRPriceFromScreen
					+ "and sales price updated is " + vehicleSalesPriceFromActual + " i.e. true");
			System.out.println("Vehicle profit " + vehicleProfit + " added to OTR price " + vehicleOTRPriceFromScreen
					+ "and sales price updated is " + vehicleSalesPriceFromActual + " i.e. true");
		}

		return status;
	}

	public boolean edit_vehicle_profit_and_sales_price_for_used_vehicle(String vehicle_profit, String sheet_name)
			throws InterruptedException, UnsupportedFlavorException, IOException {

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

		ExplicitWait.visibleElement(driver, vehicle_sale_price_used_vehicle, 20);
		vehicle_sale_price_used_vehicle.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String vehicle_sales_price_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		double vehicle_sales_price_from_screen_converted = Double.parseDouble(vehicle_sales_price_copied);

		double vehicleOTRPriceFromScreen = Double
				.parseDouble(RemoveComma.of(vehicle_otr_price.getText().trim().substring(2)));
		double vehicleProfit = Double.parseDouble(vehicle_profit);
		double vehicleSalesPriceFromActual = vehicleOTRPriceFromScreen + (vehicleProfit * 1.2);

		double diff = Difference.of_two_Double_Values(vehicle_sales_price_from_screen_converted,
				vehicleSalesPriceFromActual);

		boolean status = true;

		if (diff < 0.2) {
			status = true;

			LO.print("Vehicle profit " + vehicleProfit + " added to OTR price " + vehicleOTRPriceFromScreen
					+ "and sales price updated is " + vehicleSalesPriceFromActual + " i.e. true");
			System.out.println("Vehicle profit " + vehicleProfit + " added to OTR price " + vehicleOTRPriceFromScreen
					+ "and sales price updated is " + vehicleSalesPriceFromActual + " i.e. true");
		}

		return status;
	}

	public boolean put_part_exchange_values_and_check_pending_amount(String part_exchange_actual,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_Deposit_from_excel, String finance_deposit_from_excel, String document_fee_from_excel,
			String sheet_name) throws UnsupportedFlavorException, IOException, InterruptedException {

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

		ExplicitWait.visibleElement(driver, less_finance_Settlement, 20);
		less_finance_Settlement.clear();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.sendKeys(driver, less_finance_Settlement, less_finance_settlement_from_excel, 20);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, order_Deposit, 20);
		order_Deposit.clear();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.sendKeys(driver, order_Deposit, order_Deposit_from_excel, 30);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, document_fee, 30);
		document_fee.clear();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, document_fee, 30);
		document_fee.clear();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.sendKeys(driver, document_fee, document_fee_from_excel, 30);
		act.sendKeys(Keys.TAB).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		double vehicleSalesPriceFromScreen = Double
				.parseDouble(RemoveComma.of(vehicle_sales_price.getText().trim().substring(2)));

		double netPartExchangeAllowance = Double
				.parseDouble(RemoveComma.of(net_part_exchange_allowance.getText().trim().substring(2)));

		double orderDeposit = Double.parseDouble(order_Deposit_from_excel);

		double documentfee = Double.parseDouble(document_fee_from_excel);

		double pendingAmountExpected = vehicleSalesPriceFromScreen - orderDeposit - netPartExchangeAllowance
				+ (documentfee * 1.2);

		double pendingAmountFromScreen = Double
				.parseDouble(RemoveComma.of(pending_amount.getText().trim().substring(2)));

		double diff = Difference.of_two_Double_Values(pendingAmountExpected, pendingAmountFromScreen);

		boolean status = false;
		if (diff < 0.2) {
			status = true;
			LO.print("Pending Amount from screen " + pendingAmountFromScreen + " Verified Successfully "
					+ "with applied formula " + pendingAmountExpected + " i.e. true");
			System.out.println("Pending Amount from screen " + pendingAmountFromScreen + " Verified Successfully "
					+ "with applied formula " + pendingAmountExpected + " i.e. true");
		}else
		{
			LO.print("Pending Amount from screen is " + pendingAmountFromScreen
					+ "and Pending Amount Expected is " + pendingAmountExpected + " found wrong");
			System.out.println("Pending Amount from screen is " + pendingAmountFromScreen
					+ "and Pending Amount Expected is " + pendingAmountExpected + " found wrong");

		}

		return status;

	}

	public boolean put_part_exchange_values_and_check_pending_amount_for_used_vehicle(String part_exchange_actual,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel, String deposit, String documentFee,
			String sheet_name) throws UnsupportedFlavorException, IOException, InterruptedException {

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
		
		
		

		Click.on(driver, deposit_required, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.sendKeys(driver, deposit_required, deposit, 30);

		Click.on(driver, document_fee, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		Click.sendKeys(driver, document_fee, documentFee, 30);
		
		
		ExplicitWait.visibleElement(driver, vehicle_sale_price_used_vehicle, 20);
		vehicle_sale_price_used_vehicle.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String vehicle_sales_price_copied = (String) clipboard.getData(DataFlavor.stringFlavor);

		double vehicleSalesPriceFromScreen = Double.parseDouble(vehicle_sales_price_copied);
		
		System.out.println("Sale price "+vehicleSalesPriceFromScreen);

//		double vehicleSalesPriceFromScreen = Double
//				.parseDouble(RemoveComma.of(vehicle_sales_price.getText().trim().substring(2)));

		double netPartExchangeAllowance = Double
				.parseDouble(RemoveComma.of(net_part_exchange_allowance.getText().trim().substring(2)));

		double orderDeposit = Double.parseDouble(deposit);

		double documentfee = Double.parseDouble(documentFee);

		double pendingAmountExpected = vehicleSalesPriceFromScreen - orderDeposit - netPartExchangeAllowance
				+ (documentfee * 1.2);

		double pendingAmountFromScreen = Double
				.parseDouble(RemoveComma.of(pending_amount.getText().trim().substring(2)));

		double diff = Difference.of_two_Double_Values(pendingAmountExpected, pendingAmountFromScreen);

		boolean status = false;
		if (diff < 0.2) {
			status = true;
			LO.print("Pending Amount from screen " + pendingAmountFromScreen + " Verified Successfully "
					+ "with applied formula " + pendingAmountExpected + " i.e. true");
			System.out.println("Pending Amount from screen " + pendingAmountFromScreen + " Verified Successfully "
					+ "with applied formula " + pendingAmountExpected + " i.e. true");
		}

		return status;

	}

}
