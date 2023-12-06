package com.amt.QuoteSummaryPages;

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
import com.amt.testUtil.ReadExcelCalculation;
import com.amt.testUtil.RemoveComma;

public class QuoteSummary_OP_OP_Page extends TestBase {

	ReadExcelCalculation obj_read_excel_calculation_page;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//p[normalize-space()='Quote summary']")
	private WebElement quote_summary;

	@FindBy(xpath = "//*[normalize-space()='Quote reference no.:']//ancestor::div[1]//span[2]")
	private WebElement quote_summary_ref_no;

	@FindBy(xpath = "//*[normalize-space()='Cost OTR price']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_cost_otr_price;

	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-summary-quote[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[8]/div[1]/div[1]/p[1]/strong[1]")
	private WebElement quote_summary_total_monthly_holding_cost;

	@FindBy(xpath = "//*[@class='row']//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[4]/div/p/strong")
	private WebElement quote_summary_monthly_finance_rental;

	@FindBy(xpath = "//*[@class='row']//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[5]/div/p/strong")
	private WebElement quote_summary_monthly_maintenance_rental;

	@FindBy(xpath = "//*[@class='row']//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[6]/div/p/strong")
	private WebElement quote_summary_monthly_total_rental;

	@FindBy(xpath = "//*[@class='row']//*[@id='headingHoldingCost']/div/div[1]/div/div/p/strong")
	private WebElement quote_summary_acq_contract_type;

	@FindBy(xpath = "//*[@class='row']//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[1]/div/p/strong")
	private WebElement quote_summary_customer_contract_type;

	@FindBy(xpath = "//div[@id='headingHoldingCost']//div[7]//div[1]//div[1]//p[1]//strong[1]")
	private WebElement quote_summary_total_monthly_holding_cost_without_maintenance;

	@FindBy(xpath = "//*[@id='headingTwo']/div/div/div[1]/div/p/strong")
	private WebElement quote_summary_cost_price_ex_vat_and_rfl;

	@FindBy(xpath = "//*[normalize-space()='VAT']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_otr_vat;

	@FindBy(xpath = "//*[normalize-space()='RFL & FRF']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_otr_rfl_and_frf;
	
	@FindBy(xpath = "//*[@id='headingHoldingCost']/div/div[2]/div/div/p/strong")
	private WebElement quote_summary_holding_cost_term;

	@FindBy(xpath = "//*[@id='headingHoldingCost']/div/div[3]/div/div/p/strong")
	private WebElement quote_summary_holding_cost_miles_per_annum;

	@FindBy(xpath = "//*[@id='headingHoldingCost']/div/div[4]/div/div/p/strong")
	private WebElement quote_summary_holding_cost_monthly_finance_cost;

	@FindBy(xpath = "//*[@id='headingHoldingCost']/div/div[6]/div/div/p/strong")
	private WebElement quote_summary_holding_cost_monthly_maint_cost_used;

	@FindBy(xpath = "//*[@id='headingHoldingCost']/div/div[7]/div/div/p/strong")
	private WebElement quote_summary_holding_cost_CAP_monthly_maint_cost;

	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[1]/button/div")
	private WebElement quote_summary_customer_quote_summary_value_verification;

	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[2]/div/p/strong")
	private WebElement quote_summary_customer_quote_term;

	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[3]/div/p/strong")
	private WebElement quote_summary_customer_quote_miles;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[1]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_payment_profile;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[2]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_initial_finance_rental;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[3]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_initial_maint_rental;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[4]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_initial_total_rental;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[3]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_part_exchange_value;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[5]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_part_exchange_value_with_maint;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[4]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_followed_by;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[6]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_followed_by_with_maint;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[6]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_pence_per_excess_mile_finance;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[10]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_pence_per_excess_mile_finance_with_maint;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[11]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_pence_per_excess_mile_maintenance_with_maint;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[12]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_pence_per_excess_mile_total_with_maint;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[7]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_doc_fee;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[13]/div/div/p/strong")
	private WebElement quote_summary_customer_quote_doc_fee_with_maint;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[8]/div/div[1]/p/strong")
	private WebElement quote_summary_customer_quote_upsell;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[1]/div[14]/div/div[1]/p/strong")
	private WebElement quote_summary_customer_quote_upsell_with_maint;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[2]/div[2]/div[1]/div/p/strong")
	private WebElement quote_summary_customer_quote_default_finance_commission;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[2]/div[2]/div[2]/div/p/strong")
	private WebElement quote_summary_customer_quote_upsell_commission;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[2]/div[2]/div[3]/div/p/strong")
	private WebElement quote_summary_customer_quote_doc_fee_commission;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[2]/div[2]/div[4]/div/p/strong")
	private WebElement quote_summary_customer_quote_doc_fee_commission_with_maint;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[2]/div[2]/div[3]/div/p/strong")
	private WebElement quote_summary_customer_quote_maint_commission_with_maint;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[2]/div[2]/div[4]/div/p/strong")
	private WebElement quote_summary_customer_quote_total_commission;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[2]/div[2]/div[5]/div/p/strong")
	private WebElement quote_summary_customer_quote_total_commission_with_maint;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[2]/div[2]/div[5]/div/p/strong")
	private WebElement quote_summary_customer_quote_referrer_commission;

	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/div/form/div/app-hire-customer-quote-summary-detail/div[2]/div[2]/div[6]/div/p/strong")
	private WebElement quote_summary_customer_quote_referrer_commission_with_maint;

	@FindBy(xpath = "//*[@id='headingConfig']/button/div")
	private WebElement quote_summary_configuration;

	@FindBy(xpath = "//*[@id='headingConfig']/div/div/div[1]/div/p/strong")
	private WebElement quote_summary_base_interest_rate;

	@FindBy(xpath = "//*[@id='headingConfig']/div/div/div[2]/div/p/strong")
	private WebElement quote_summary_finance_margin;

	@FindBy(xpath = "//*[@id='headingConfig']/div/div/div[3]/div/p/strong")
	private WebElement quote_summary_deductions;

	@FindBy(xpath = "//*[@id='headingConfig']/div/div/div[4]/div/p/strong")
	private WebElement quote_summary_additional_margin;

	@FindBy(xpath = "//*[@id='headingConfig']/div/div/div[5]/div/p/strong")
	private WebElement quote_summary_total_margin;

	@FindBy(xpath = "//*[@id='collapseConfig']/div/div/div/div/div[4]/div[2]/div/div/label")
	private WebElement quote_summary_default_broker_margin_percentage;

	@FindBy(xpath = "//*[@id='collapseConfig']/div/div/div/div/div[4]/div[4]/div/label/strong")
	private WebElement quote_summary_broker_upsell_margin_percentage;

	@FindBy(xpath = "//*[@id='collapseConfig']/div/div/div/div/div[4]/div[5]/div/label/b")
	private WebElement quote_summary_broker_upsell_margin;

	@FindBy(xpath = "//*[@id='collapseConfig']/div/div/div/div/div[4]/div[7]/div/label/strong")
	private WebElement quote_summary_maintenance_margin;

	@FindBy(xpath = "//*[@id='collapseConfig']/div/div/div/div/div[4]/div[6]/div/label/b")
	private WebElement quote_summary_decument_fee_margin;

	@FindBy(xpath = "//*[@id='collapseConfig']/div/div/div/div/div[4]/div[8]/div/label/b")
	private WebElement quote_summary_decument_fee_margin_with_maint;

	@FindBy(xpath = "//*[@id='collapseConfig']/div/div/div/div/div[4]/div[7]/div/label/b")
	private WebElement quote_summary_refferer_margin;

	@FindBy(xpath = "//*[@id='collapseConfig']/div/div/div/div/div[4]/div[9]/div/label/b")
	private WebElement quote_summary_refferer_margin_with_maint;

	@FindBy(xpath = "//input[@id='baseIntRate']")
	private WebElement quote_summary_configuration_base_int_rate_input;

	@FindBy(xpath = "//input[@id='FinanceMargin']")
	private WebElement quote_summary_configuration_finance_margin_input;

	@FindBy(xpath = "//input[@id='MaintenanceMarginPer']")
	private WebElement quote_summary_configuration_maintenance_margin_input;

	@FindBy(xpath = "//div[@class='row acquisition-menu']//div[3]//button[1]")
	private WebElement quote_summary_save_button;
	

	//create order quote summary page button
		@FindBy(xpath = "//button[normalize-space()='Create order']")
		private WebElement quote_summary_create_order;	
		
	//Channel Dropdown	
		@FindBy(xpath = "//*[normalize-space()='Channel']//ancestor::div[1]//div//div//div//span[2]")
		private WebElement quote_summary_channel_dropdown;	
		
	//Channel option all	
		@FindBy(xpath = "(//*[normalize-space()='All']//li//input)[1]")
		private WebElement quote_summary_channel_option_all;	
		
	//Delivery date picker 	
		@FindBy(xpath = "(//*[normalize-space()='Requested delivery']//ancestor::div[1]//div//input)[5]")
		private WebElement quote_summary_requested_delivery_date_picker;
		
		
		//first enabled month
		@FindBy(xpath = "//table//td[@class='ng-star-inserted']")
		private WebElement quote_summary_requested_delivery_month;
		
		
		//List of month	
		@FindBy(xpath = "//bs-calendar-layout//table//tbody//tr//td")
		private List<WebElement> quote_summary_list_of_months;
	
	//Enter Supplier Name	
		@FindBy(xpath = "//label[normalize-space()='Name']//ancestor::div[1]//div//input")
		private WebElement quote_summary_input_supplier_name;	
		
	//Pick Supplier Name	
		@FindBy(xpath = "//*[@id='convert_to_order_modal']//*[normalize-space()='Dummy Company']//ancestor::div[1]//div//strong")
		private WebElement quote_summary_pick_supplier_name;	
		
	//Confirm Create order button	
		@FindBy(xpath = "//*[@class='modal-footer']//*[contains(text(), 'Create order')]")
		private WebElement quote_summary_create_order_pop_up_button;
		
		//Click on OK to confirm
		@FindBy(xpath = "//*[@id='convert_to_order_confrim']//*[contains(text(), 'Ok')]")
		private WebElement quote_summary_create_order_ok_button;
		
		//Order ID Number 
		@FindBy(xpath = "//*[contains(text(), 'New Order ID')]")
		private WebElement quote_summary_order_id_text;
		
		
		
		//Click on OK to confirm
		@FindBy(xpath = "//*[contains(text(), 'Ok')]")
		private WebElement quote_summary_ok_button_after_getting_order_id;
		
		@FindBy(xpath = "//*[text()='Vehicle sales price']//ancestor::div[1]//div//p//strong")
		private WebElement vehicle_sales_price;
		
		@FindBy(xpath = "//*[text()='Document fee']//ancestor::div[1]//div//p//strong")
		private WebElement doc_fee;
		
		@FindBy(xpath = "//*[text()='Deposit required']//ancestor::div[1]//div//p//strong")
		private WebElement order_deposit;

	
	Properties prop;
	
	public QuoteSummary_OP_OP_Page() {

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
	
	
	
public void write_vehicle_sales_price_to_excel(String sheet_name) throws InterruptedException, IOException {
					
		
		
	    LO.print          ("Writing Vehicle Sales Price to Excel");	    
		System.out.println("Writing Vehicle Sales Price to Excel");

		
		Click.on(driver, quote_summary, 90);		
		//First collect OTR elements		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);


		//save the record
		
		Click.on(driver, quote_summary_save_button, 20);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);
		
		// quote no.
	    String quote_ref_no = quote_summary_ref_no.getText();

		String vehicleSalesPrice = RemoveComma.of(vehicle_sales_price.getText().trim().substring(2));
		String docFee = RemoveComma.of(doc_fee.getText().trim().substring(2));
		String orderDeposit = RemoveComma.of(order_deposit.getText().trim().substring(2));
		
	    LO.print          ("Vehicle Sales Price from screen is "+vehicleSalesPrice);	    
		System.out.println("Vehicle Sales Price from screen is "+vehicleSalesPrice);
		
	    LO.print          ("Document Fee from screen is "+docFee);	    
		System.out.println("Document Fee from screen is "+docFee);
		
	    LO.print          ("Order Deposit from screen is "+orderDeposit);	    
		System.out.println("Order Deposit from screen is "+orderDeposit);



		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		String sheetname = prop.getProperty(sheet_name);
	   //quote ref no 
		
		wb.getSheet(sheetname).getRow(1).getCell(0).setCellValue(quote_ref_no);
		wb.getSheet(sheetname).getRow(1).getCell(1).setCellValue(vehicleSalesPrice); 
		wb.getSheet(sheetname).getRow(4).getCell(1).setCellValue(docFee);
		wb.getSheet(sheetname).getRow(7).getCell(1).setCellValue(orderDeposit);
			
		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		wb.write(out);
		wb.close();
		
		
		LO.print("Writing completed for Vehicle Sales Price to quote save excel sheet in the sheet "+sheetname);
		System.out.println("Writing completed for Vehicle Sales Price to quote save excel sheet in the sheet "+sheetname);

}


	
	public boolean quote_summary_OTR_calculation(String sheet_name) throws InterruptedException, IOException {

		LO.print("*************OTR Calulation on quote summary page has been started************");
		System.out.println("*************OTR Calulation on quote summary page has been started************");

		obj_read_excel_calculation_page = new ReadExcelCalculation();

		Thread.sleep(2000);

		Click.on(driver, quote_summary, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

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


		LO.print("Cost otr price from screen =" + OTR_calculation_cost_otr_price_from_screen_converted);
		System.out.println("Cost otr price from screen =" + OTR_calculation_cost_otr_price_from_screen_converted);

		LO.print("Cost price ex vat and rfl from screen ="+ OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted);
		System.out.println("Cost price ex vat and rfl from screen ="+ OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted);

		LO.print("OTR vat from screen =" + OTR_calculation_otr_vat_from_screen_converted);
		System.out.println("OTR vat from screen =" + OTR_calculation_otr_vat_from_screen_converted);


		double OTR_calculation_cost_price_ex_vat_and_rfl_expected = (OTR_calculation_cost_otr_price_from_screen_converted
				- OTR_calculation_otr_vat_from_screen_converted-OTR_calculation_otr_rfl_and_frf_from_screen_converted);

		double diff = Difference.of_two_Double_Values(OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted,
				OTR_calculation_cost_price_ex_vat_and_rfl_expected);

		int count = 0;
		boolean status = false;
		if (diff < 0.2) {
			LO.print("Cost price compared");
			System.out.println("Cost price compared");
			count++;
		} else {
			LO.print("Cost price found wrong");
			System.out.println("Cost price found wrong");
		}

		if (count == 1) {
			status = true;
		}
		
		ExplicitWait.visibleElement(driver, quote_summary_save_button, 30);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", quote_summary_save_button);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		
		ExplicitWait.visibleElement(driver, quote_summary_ref_no, 120);
		
		String quote_ref_no = quote_summary_ref_no.getText();

		
		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		String sheetname = prop.getProperty(sheet_name);
		
		System.out.println("Sheet Name Provided is "+sheetname);
		
				
	   //quote ref no 
		wb.getSheet(sheetname).getRow(1).getCell(0).setCellValue(quote_ref_no);
	    //quote ref no 
		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		wb.write(out);
		wb.close();	
		
		LO.print("Quote no. "+quote_ref_no+" is saved to Excel sheet named Quote Save, in the sheet "+sheetname);
		System.out.println("Quote no. "+quote_ref_no+" is saved to Excel sheet named Quote Save, in the sheet "+sheetname);


		return status;

	}

	public boolean quote_summary_OTR_calculation_for_used_car(String sheet_name) throws InterruptedException, IOException {

		LO.print("*************OTR Calulation on quote summary page has been started************");
		System.out.println("*************OTR Calulation on quote summary page has been started************");

		obj_read_excel_calculation_page = new ReadExcelCalculation();

		Thread.sleep(2000);

		Click.on(driver, quote_summary, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		ExplicitWait.visibleElement(driver, quote_summary_cost_otr_price, 120);

		ExplicitWait.visibleElement(driver, quote_summary_cost_price_ex_vat_and_rfl, 120);

		ExplicitWait.visibleElement(driver, quote_summary_otr_vat, 120);
		
		 

		LO.print("Reading values from OTR calculation -Quote Summary Page");
		System.out.println("Reading values from OTR calculation -Quote Summary Page");

		double cost_otr_price = Double
				.parseDouble(RemoveComma.of(quote_summary_cost_otr_price.getText().trim().substring(2)));

		double cost_price_ex_vat_and_rfl = Double
				.parseDouble(RemoveComma.of(quote_summary_cost_price_ex_vat_and_rfl.getText().trim().substring(2)));

		double vat = Double
				.parseDouble(RemoveComma.of(quote_summary_otr_vat.getText().trim().substring(2)));

		 

		LO.print("Cost otr price from screen =" + cost_otr_price);
		System.out.println("Cost otr price from screen =" + cost_otr_price);

		LO.print("Cost price ex vat and rfl from screen ="+ cost_price_ex_vat_and_rfl);
		System.out.println("Cost price ex vat and rfl from screen ="+ cost_price_ex_vat_and_rfl);

		LO.print("OTR vat from screen =" + vat);
		System.out.println("OTR vat from screen =" + vat);
		
	 

		double cost_price_ex_vat_and_rfl_expected = (cost_otr_price
				- vat);

		double diff = Difference.of_two_Double_Values(cost_price_ex_vat_and_rfl,
				cost_price_ex_vat_and_rfl_expected);

		int count = 0;
		boolean status = false;
		if (diff < 0.2) {
			LO.print("Cost price compared");
			System.out.println("Cost price compared");
			count++;
		} else {
			LO.print("Cost price found wrong");
			System.out.println("Cost price found wrong");
		}

		if (count == 1) {
			status = true;
		}
		
		ExplicitWait.visibleElement(driver, quote_summary_save_button, 30);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", quote_summary_save_button);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		
		ExplicitWait.visibleElement(driver, quote_summary_ref_no, 120);
		
		String quote_ref_no = quote_summary_ref_no.getText();

		
		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		String sheetname = prop.getProperty(sheet_name);
		
		System.out.println("Sheet Name Provided is "+sheetname);
		
				
	   //quote ref no 
		wb.getSheet(sheetname).getRow(1).getCell(0).setCellValue(quote_ref_no);
	    //quote ref no 
		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		wb.write(out);
		wb.close();	
		
		LO.print("Quote no. "+quote_ref_no+" is saved to Excel sheet named Quote Save, in the sheet "+sheetname);
		System.out.println("Quote no. "+quote_ref_no+" is saved to Excel sheet named Quote Save, in the sheet "+sheetname);

		return status;

	}

	
	public boolean quote_summary_holding_cost_calculation_without_maintenance(String sheet_name)
			throws InterruptedException, IOException {

		LO.print("*************Holding Cost Calulation on quote summary page has been started************");
		System.out.println("*************Holding Cost Calulation on quote summary page has been started************");

		obj_read_excel_calculation_page = new ReadExcelCalculation();

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_term, 30);

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_miles_per_annum, 30);

		ExplicitWait.visibleElement(driver, quote_summary_holding_cost_monthly_finance_cost, 30);

		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost_without_maintenance, 30);

		LO.print("Reading values from Holding Cost summary -Quote Summary Page");
		System.out.println("Reading values from Holding Cost summary -Quote Summary Page");

		double holding_cost_terms_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_holding_cost_term.getText().trim().substring(0, 2)));

		double holding_cost_miles_per_annum_from_screen_converted = Double
				.parseDouble(RemoveComma.of(quote_summary_holding_cost_miles_per_annum.getText().trim()));

		double holding_cost_monthly_finance_cost_from_screen_converted = Double.parseDouble(
				RemoveComma.of(quote_summary_holding_cost_monthly_finance_cost.getText().trim().substring(2)));

		double holding_cost_total_monthly_holding_cost_from_screen_converted = Double.parseDouble(RemoveComma
				.of(quote_summary_total_monthly_holding_cost_without_maintenance.getText().trim().substring(2)));

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

		double holding_cost_terms_from_excel = GetExcelFormulaValue.get_formula_value(51, 0, sheet_name);
		double holding_cost_miles_per_annum_from_excel = GetExcelFormulaValue.get_formula_value(50, 1, sheet_name);
		double holding_cost_monthly_finance_cost_from_excel = GetExcelFormulaValue.get_formula_value(35, 0, sheet_name);
		double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(51, 1,
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
			System.out.println("Found difference between terms actual  and terms expected ");
		}

		if (diff_miles_per_annum < 0.2) {
			LO.print("Miles per annum compared");
			System.out.println("Miles per annum compared");
			count++;
		} else {
			LO.print(
					"Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected ");
			System.out.println(
					"Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected");
		}

		if (diff_finance_cost < 0.2) {
			LO.print("Finance cost compared");
			System.out.println("Finance cost compared");
			count++;
		} else {
			LO.print("Found difference between Finance cost actual and Finance cost expected");
			System.out.println("Found difference between Finance cost actual and Finance cost expected");
		}

		if (diff_total_monthly_holding_cost < 0.2) {
			LO.print("Total Monthly Holding Cost compared");
			System.out.println("Total Monthly Holding Cost compared");
			count++;
		} else {
			LO.print(
					"Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");
			System.out.println(
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

		obj_read_excel_calculation_page = new ReadExcelCalculation();

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

		LO.print("holding_cost_terms_from_screen" + holding_cost_terms_from_screen_converted);
		System.out.println("holding_cost_terms_from_screen" + holding_cost_terms_from_screen_converted);

		LO.print("holding_cost_miles_per_annum_from_screen" + holding_cost_miles_per_annum_from_screen_converted);
		System.out.println(
				"holding_cost_miles_per_annum_from_screen" + holding_cost_miles_per_annum_from_screen_converted);

		LO.print("holding_cost_monthly_finance_cost_from_screen"
				+ holding_cost_monthly_finance_cost_from_screen_converted);
		System.out.println("holding_cost_monthly_finance_cost_from_screen"
				+ holding_cost_monthly_finance_cost_from_screen_converted);

		LO.print("holding_cost_monthly_maint_cost_used_from_screen"
				+ holding_cost_monthly_maint_cost_used_from_screen_converted);
		System.out.println("holding_cost_monthly_maint_cost_used_from_screen"
				+ holding_cost_monthly_maint_cost_used_from_screen_converted);

		LO.print("holding_cost_CAP_monthly_maint_cost_from_screen"
				+ holding_cost_CAP_monthly_maint_cost_from_screen_converted);
		System.out.println("holding_cost_CAP_monthly_maint_cost_from_screen"
				+ holding_cost_CAP_monthly_maint_cost_from_screen_converted);

		LO.print("holding_cost_total_monthly_holding_cost_from_screen ="
				+ holding_cost_total_monthly_holding_cost_from_screen_converted);
		System.out.println("holding_cost_total_monthly_holding_cost_from_screen ="
				+ holding_cost_total_monthly_holding_cost_from_screen_converted);

		double holding_cost_terms_from_excel = GetExcelFormulaValue.get_formula_value(51, 0, sheet_name);
		double holding_cost_miles_per_annum_from_excel = GetExcelFormulaValue.get_formula_value(50, 1, sheet_name);
		double holding_cost_monthly_finance_cost_from_excel = GetExcelFormulaValue.get_formula_value(35, 0, sheet_name);
		double holding_cost_monthly_maint_cost_used_from_excel = GetExcelFormulaValue.get_formula_value(39, 1,
				sheet_name);
		double holding_cost_CAP_monthly_maint_cost_from_excel = GetExcelFormulaValue.get_formula_value(35, 1,
				sheet_name);
		double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(51, 1,
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

		int count = 0;
		boolean status = false;
		if (diff_terms < 0.2) {
			LO.print("terms compared");
			System.out.println("terms compared");
			count++;
		} else {
			LO.print("Found difference between terms actual  and terms expected ");
			System.out.println("Found difference between terms actual  and terms expected ");
		}

		if (diff_miles_per_annum < 0.2) {
			LO.print("Miles per annum compared");
			System.out.println("Miles per annum compared");
			count++;
		} else {
			LO.print(
					"Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected ");
			System.out.println(
					"Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected");
		}

		if (diff_finance_cost < 0.2) {
			LO.print("Finance cost compared");
			System.out.println("Finance cost compared");
			count++;
		} else {
			LO.print("Found difference between Finance cost actual and Finance cost expected");
			System.out.println("Found difference between Finance cost actual and Finance cost expected");
		}

		if (diff_maint_cost < 0.2) {
			LO.print("Maint cost used  compared");
			System.out.println("Maint cost used compared");
			count++;
		} else {
			LO.print("Found difference between Maint cost used actual and Maint cost used expected");
			System.out.println("Found difference between Maint cost used actual and Maint cost used expected");
		}

		if (diff_CAP_maint < 0.2) {
			LO.print("CAP monthly cost compared");
			System.out.println("CAP monthly cost compared");
			count++;
		} else {
			LO.print("Found difference between CAP monthly cost actual and CAP monthly cost expected");
			System.out.println("Found difference between CAP monthly cost actual and CAP monthly cost expected");
		}

		if (diff_total_monthly_holding_cost < 0.2) {
			LO.print("Total Monthly Holding Cost compared");
			System.out.println("Total Monthly Holding Cost compared");
			count++;
		} else {
			LO.print(
					"Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");
			System.out.println(
					"Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");
		}

		if (count == 6) {
			status = true;
		}

		return status;

	}

	public boolean quote_summary_customer_quote_summary_value_verification_without_maintenance(String sheet_name)
			throws IOException {

		LO.print("*************Customer Quote Calulation on quote summary page has been started************");
		System.out.println("*************Customer Quote Calulation on quote summary page has been started************");

		Click.on(driver, quote_summary_customer_quote_summary_value_verification, 0);

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_term, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_miles, 20);
		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_initial_finance_rental, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_part_exchange_value, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_followed_by, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_pence_per_excess_mile_finance, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_doc_fee, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_upsell, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_default_finance_commission, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_upsell_commission, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_doc_fee_commission, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_total_commission, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_referrer_commission, 20);

		double customer_quote_summary_terms = Double
				.parseDouble(quote_summary_customer_quote_term.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double
				.parseDouble(RemoveComma.of(quote_summary_customer_quote_miles.getText().trim()));

		double customer_quote_summary_monthly_finance_rental = Double
				.parseDouble(RemoveComma.of(quote_summary_monthly_finance_rental.getText().trim().substring(2)));

		double customer_quote_initial_finance_rental = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_initial_finance_rental.getText().trim().substring(2)));

		double customer_quote_part_exchange_value = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_part_exchange_value.getText().trim().substring(2)));

		double customer_payment_followed_by = Double
				.parseDouble(quote_summary_customer_quote_followed_by.getText().substring(0, 2));

		double customer_quote_pence_per_excess_mile_finance = Double.parseDouble(
				quote_summary_customer_quote_pence_per_excess_mile_finance.getText().trim().substring(0, 4));

		double customer_quote_summary_doc_fee = Double
				.parseDouble(RemoveComma.of(quote_summary_customer_quote_doc_fee.getText().trim().substring(2)));

		double customer_quote_summary_upsell = Double
				.parseDouble(RemoveComma.of(quote_summary_customer_quote_upsell.getText().trim().substring(2)));

		double customer_quote_summary_default_finance_commission = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_default_finance_commission.getText().trim().substring(2)));

		double customer_quote_summary_upsell_commission = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_upsell_commission.getText().trim().substring(2)));

		double customer_quote_summary_doc_fee_commission = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_doc_fee_commission.getText().trim().substring(2)));

		double customer_quote_summary_total_commision = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_total_commission.getText().trim().substring(2)));

		double customer_quote_summary_referrer_commision = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_referrer_commission.getText().trim().substring(2)));

		double terms = GetExcelFormulaValue.get_formula_value(173, 1, sheet_name);
		double miles = GetExcelFormulaValue.get_formula_value(173, 3, sheet_name);
		double monthlyFinanceRental = GetExcelFormulaValue.get_formula_value(176, 0, sheet_name);
		double initialFinanceRental = GetExcelFormulaValue.get_formula_value(179, 1, sheet_name);
		double partExchangeValue = GetExcelFormulaValue.get_formula_value(113, 4, sheet_name);
		double followedBy = GetExcelFormulaValue.get_formula_value(182, 3, sheet_name);
		double pencePerExcessMileFinance = GetExcelFormulaValue.get_formula_value(188, 0, sheet_name);
		double documentFee = GetExcelFormulaValue.get_formula_value(191, 1, sheet_name);
		double upsell = GetExcelFormulaValue.get_formula_value(191, 3, sheet_name);
		double defaultFinanceCommission = GetExcelFormulaValue.get_formula_value(196, 0, sheet_name);
		double upsellCommission = GetExcelFormulaValue.get_formula_value(196, 1, sheet_name);
		double docFeeCommission = GetExcelFormulaValue.get_formula_value(199, 0, sheet_name);
		double totalCommission = GetExcelFormulaValue.get_formula_value(200, 3, sheet_name);
		double referrerCommission = GetExcelFormulaValue.get_formula_value(202, 0, sheet_name);

		boolean status = false;

		int count = 0;

		if (terms == customer_quote_summary_terms) {
			LO.print("Terms found OK");
			System.out.println("Terms found OK");
			count++;
		} else {
			LO.print("Terms found wrong");
			System.out.println("Terms found wrong");
		}

		if (miles == customer_quote_summary_miles) {
			LO.print("miles found OK");
			System.out.println("miles found OK");
			count++;
		} else {
			LO.print("miles found wrong");
			System.out.println("miles found wrong");
		}

		if ((Difference.of_two_Double_Values(monthlyFinanceRental,
				customer_quote_summary_monthly_finance_rental)) < 0.2) {
			LO.print("Monthly Finance Rental found OK");
			System.out.println("Monthly Finance Rental found OK");
			count++;
		} else {
			LO.print("Monthly Finance Rental found wrong");
			System.out.println("Monthly Finance Rental found wrong");
		}

		if ((Difference.of_two_Double_Values(initialFinanceRental, customer_quote_initial_finance_rental)) < 0.2) {
			LO.print("Initial Finance Rental found OK");
			System.out.println("Initial Finance Rental found OK");
			count++;
		} else {
			LO.print("Initial Finance Rental found wrong");
			System.out.println("Initial Finance Rental found wrong");
		}

		if (followedBy == customer_payment_followed_by) {
			LO.print("Followed By months - found OK");
			System.out.println("Followed By months - found OK");
			count++;
		} else {
			LO.print("Followed By months - found wrong");
			System.out.println("Followed By months - found wrong");
		}

		if (partExchangeValue == customer_quote_part_exchange_value) {
			LO.print("Part Exchange Value - found OK");
			System.out.println("Part Exchange Value - found OK");
			count++;
		} else {
			LO.print("Part Exchange Value - found wrong");
			System.out.println("Part Exchange Value - found wrong");
		}

		if ((Difference.of_two_Double_Values(pencePerExcessMileFinance,
				customer_quote_pence_per_excess_mile_finance)) < 0.2) {
			LO.print("Pence per excess mile finance - found OK");
			System.out.println("Pence per excess mile finance - found OK");
			count++;
		} else {
			LO.print("Pence per excess mile finance - found wrong");
			System.out.println("Pence per excess mile finance - found wrong");
		}

		if ((Difference.of_two_Double_Values(documentFee, customer_quote_summary_doc_fee)) < 0.2) {
			LO.print("Document Fee - found OK");
			System.out.println("Document Fee - found OK");
			count++;
		} else {
			LO.print("Document Fee - found wrong");
			System.out.println("Document Fee - found wrong");
		}

		if (upsell == customer_quote_summary_upsell) {
			LO.print("Upsell - found OK");
			System.out.println("Upsell - found OK");
			count++;
		} else {
			LO.print("Upsell - found wrong");
			System.out.println("Upsell - found wrong");
		}

		if ((Difference.of_two_Double_Values(defaultFinanceCommission,
				customer_quote_summary_default_finance_commission)) < 0.2) {
			LO.print("Default Finance Commission - found OK");
			System.out.println("Default Finance Commission - found OK");
			count++;
		} else {
			LO.print("Default Finance Commission - found wrong");
			System.out.println("Default Finance Commission - found wrong");
		}

		if (upsellCommission == customer_quote_summary_upsell_commission) {
			LO.print("Upsell Commission - found OK");
			System.out.println("Upsell Commission - found OK");
			count++;
		} else {
			LO.print("Upsell Commission - found wrong");
			System.out.println("Upsell Commission - found wrong");
		}

		if ((Difference.of_two_Double_Values(docFeeCommission, customer_quote_summary_doc_fee_commission)) < 0.2) {
			LO.print("Document Fee Commission - found OK");
			System.out.println("Document Fee Commission - found OK");
			count++;
		} else {
			LO.print("Document Fee Commission - found wrong");
			System.out.println("Document Fee Commission - found wrong");
		}

		if ((Difference.of_two_Double_Values(totalCommission, customer_quote_summary_total_commision)) < 0.2) {
			LO.print("Total Commission - found OK");
			System.out.println("Total Commission - found OK");
			count++;
		} else {
			LO.print("Total Commission - found wrong");
			System.out.println("Total Commission - found wrong");
		}

		if ((Difference.of_two_Double_Values(referrerCommission, customer_quote_summary_referrer_commision)) < 0.2) {
			LO.print("Referrer Commission - found OK");
			System.out.println("Referrer Commission - found OK");
			count++;
		} else {
			LO.print("Referrer Commission - found wrong");
			System.out.println("Referrer Commission - found wrong");
		}

		if (count == 14) {
			status = true;
		}
		return status;

	}

	public boolean quote_summary_customer_quote_summary_value_verification_with_maintenance(String sheet_name)
			throws IOException {

		LO.print("*************Customer Quote Calulation on quote summary page has been started************");
		System.out.println("*************Customer Quote Calulation on quote summary page has been started************");

		Click.on(driver, quote_summary_customer_quote_summary_value_verification, 0);

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_term, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_miles, 20);
		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 20);

		ExplicitWait.visibleElement(driver, quote_summary_monthly_maintenance_rental, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_initial_finance_rental, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_initial_maint_rental, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_initial_total_rental, 20);

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_part_exchange_value_with_maint, 20);

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_followed_by_with_maint, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_pence_per_excess_mile_finance_with_maint, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_pence_per_excess_mile_maintenance_with_maint,
				20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_pence_per_excess_mile_total_with_maint, 20);

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_doc_fee_with_maint, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_upsell_with_maint, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_default_finance_commission, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_upsell_commission, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_maint_commission_with_maint, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_doc_fee_commission_with_maint, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_total_commission_with_maint, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_referrer_commission_with_maint, 20);

		double customer_quote_summary_terms = Double
				.parseDouble(quote_summary_customer_quote_term.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double
				.parseDouble(RemoveComma.of(quote_summary_customer_quote_miles.getText().trim()));

		double customer_quote_summary_monthly_finance_rental = Double
				.parseDouble(RemoveComma.of(quote_summary_monthly_finance_rental.getText().trim().substring(2)));

		double customer_quote_summary_monthly_maint_rental = Double
				.parseDouble(RemoveComma.of(quote_summary_monthly_maintenance_rental.getText().trim().substring(2)));

		double customer_quote_initial_finance_rental = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_initial_finance_rental.getText().trim().substring(2)));

		double customer_quote_initial_maint_rental = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_initial_maint_rental.getText().trim().substring(2)));

		double customer_quote_initial_total_rental = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_initial_total_rental.getText().trim().substring(2)));

		double customer_quote_part_exchange_value = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_part_exchange_value_with_maint.getText().trim().substring(2)));

		double customer_payment_followed_by = Double
				.parseDouble(quote_summary_customer_quote_followed_by_with_maint.getText().substring(0, 2));

		double customer_quote_pence_per_excess_mile_finance = Double.parseDouble(
				quote_summary_customer_quote_pence_per_excess_mile_finance_with_maint.getText().trim().substring(0, 4));

		double customer_quote_pence_per_excess_mile_maint = Double
				.parseDouble(quote_summary_customer_quote_pence_per_excess_mile_maintenance_with_maint.getText().trim()
						.substring(0, 4));

		double customer_quote_pence_per_excess_mile_total = Double.parseDouble(
				quote_summary_customer_quote_pence_per_excess_mile_total_with_maint.getText().trim().substring(0, 4));

		double customer_quote_summary_doc_fee = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_doc_fee_with_maint.getText().trim().substring(2)));

		double customer_quote_summary_upsell = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_upsell_with_maint.getText().trim().substring(2)));

		double customer_quote_summary_default_finance_commission = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_default_finance_commission.getText().trim().substring(2)));

		double customer_quote_summary_upsell_commission = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_upsell_commission.getText().trim().substring(2)));

		double customer_quote_summary_maint_commission = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_maint_commission_with_maint.getText().trim().substring(2)));

		double customer_quote_summary_doc_fee_commission = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_doc_fee_commission_with_maint.getText().trim().substring(2)));

		double customer_quote_summary_total_commision = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_total_commission_with_maint.getText().trim().substring(2)));

		double customer_quote_summary_referrer_commision = Double.parseDouble(RemoveComma
				.of(quote_summary_customer_quote_referrer_commission_with_maint.getText().trim().substring(2)));

		double terms = GetExcelFormulaValue.get_formula_value(173, 1, sheet_name);
		double miles = GetExcelFormulaValue.get_formula_value(173, 3, sheet_name);
		double monthlyFinanceRental = GetExcelFormulaValue.get_formula_value(176, 0, sheet_name);
		double monthlyMaintRental = GetExcelFormulaValue.get_formula_value(176, 1, sheet_name);

		double initialFinanceRental = GetExcelFormulaValue.get_formula_value(179, 1, sheet_name);
		double initialMaintRental = GetExcelFormulaValue.get_formula_value(179, 3, sheet_name);
		double initialTotalRental = GetExcelFormulaValue.get_formula_value(182, 0, sheet_name);

		double partExchangeValue = GetExcelFormulaValue.get_formula_value(113, 4, sheet_name);
		double followedBy = GetExcelFormulaValue.get_formula_value(182, 3, sheet_name);
		double pencePerExcessMileFinance = GetExcelFormulaValue.get_formula_value(188, 0, sheet_name);
		double pencePerExcessMileMaint = GetExcelFormulaValue.get_formula_value(188, 1, sheet_name);
		double pencePerExcessMileTotal = GetExcelFormulaValue.get_formula_value(188, 3, sheet_name);

		double documentFee = GetExcelFormulaValue.get_formula_value(191, 1, sheet_name);
		double upsell = GetExcelFormulaValue.get_formula_value(191, 3, sheet_name);
		double defaultFinanceCommission = GetExcelFormulaValue.get_formula_value(196, 0, sheet_name);
		double upsellCommission = GetExcelFormulaValue.get_formula_value(196, 1, sheet_name);
		double maintCommission = GetExcelFormulaValue.get_formula_value(196, 3, sheet_name);
		double docFeeCommission = GetExcelFormulaValue.get_formula_value(199, 0, sheet_name);
		double totalCommission = GetExcelFormulaValue.get_formula_value(200, 3, sheet_name);
		double referrerCommission = GetExcelFormulaValue.get_formula_value(202, 0, sheet_name);

		boolean status = false;

		int count = 0;

		if (terms == customer_quote_summary_terms) {
			LO.print("Terms found OK");
			System.out.println("Terms found OK");
			count++;
		} else {
			LO.print("Terms found wrong");
			System.out.println("Terms found wrong");
		}

		if (miles == customer_quote_summary_miles) {
			LO.print("miles found OK");
			System.out.println("miles found OK");
			count++;
		} else {
			LO.print("miles found wrong");
			System.out.println("miles found wrong");
		}

		if ((Difference.of_two_Double_Values(monthlyFinanceRental,
				customer_quote_summary_monthly_finance_rental)) < 0.2) {
			LO.print("Monthly Finance Rental found OK");
			System.out.println("Monthly Finance Rental found OK");
			count++;
		} else {
			LO.print("Monthly Finance Rental found wrong");
			System.out.println("Monthly Finance Rental found wrong");
		}

		if ((Difference.of_two_Double_Values(monthlyMaintRental, customer_quote_summary_monthly_maint_rental)) < 0.2) {
			LO.print("Monthly Maintenance Rental found OK");
			System.out.println("Monthly Maintenance Rental found OK");
			count++;
		} else {
			LO.print("Monthly Maintenance Rental found wrong");
			System.out.println("Monthly Maintenance Rental found wrong");
		}

		if ((Difference.of_two_Double_Values(initialFinanceRental, customer_quote_initial_finance_rental)) < 0.2) {
			LO.print("Initial Finance Rental found OK");
			System.out.println("Initial Finance Rental found OK");
			count++;
		} else {
			LO.print("Initial Finance Rental found wrong");
			System.out.println("Initial Finance Rental found wrong");
		}

		if ((Difference.of_two_Double_Values(initialMaintRental, customer_quote_initial_maint_rental)) < 0.2) {
			LO.print("Initial Maint Rental found OK");
			System.out.println("Initial Maint Rental found OK");
			count++;
		} else {
			LO.print("Initial Maint Rental found wrong");
			System.out.println("Initial Maint Rental found wrong");
		}

		if ((Difference.of_two_Double_Values(initialTotalRental, customer_quote_initial_total_rental)) < 0.2) {
			LO.print("Initial Total Rental found OK");
			System.out.println("Initial Total Rental found OK");
			count++;
		} else {
			LO.print("Initial Total Rental found wrong");
			System.out.println("Initial Total Rental found wrong");
		}

		if (partExchangeValue == customer_quote_part_exchange_value) {
			LO.print("Part Exchange Value - found OK");
			System.out.println("Part Exchange Value - found OK");
			count++;
		} else {
			LO.print("Part Exchange Value - found wrong");
			System.out.println("Part Exchange Value - found wrong");
		}

		if (followedBy == customer_payment_followed_by) {
			LO.print("Followed By months - found OK");
			System.out.println("Followed By months - found OK");
			count++;
		} else {
			LO.print("Followed By months - found wrong");
			System.out.println("Followed By months - found wrong");
		}

		if ((Difference.of_two_Double_Values(pencePerExcessMileFinance,
				customer_quote_pence_per_excess_mile_finance)) < 0.2) {
			LO.print("Pence per excess mile finance - found OK");
			System.out.println("Pence per excess mile finance - found OK");
			count++;
		} else {
			LO.print("Pence per excess mile finance - found wrong");
			System.out.println("Pence per excess mile finance - found wrong");
		}

		if ((Difference.of_two_Double_Values(pencePerExcessMileMaint,
				customer_quote_pence_per_excess_mile_maint)) < 0.2) {
			LO.print("Pence per excess mile Maintenance - found OK");
			System.out.println("Pence per excess mile Maintenance - found OK");
			count++;
		} else {
			LO.print("Pence per excess mile Maintenance - found wrong");
			System.out.println("Pence per excess mile Maintenance - found wrong");
		}

		if ((Difference.of_two_Double_Values(pencePerExcessMileTotal,
				customer_quote_pence_per_excess_mile_total)) < 0.2) {
			LO.print("Pence per excess mile total - found OK");
			System.out.println("Pence per excess mile total - found OK");
			count++;
		} else {
			LO.print("Pence per excess mile total - found wrong");
			System.out.println("Pence per excess mile total - found wrong");
		}

		if ((Difference.of_two_Double_Values(documentFee, customer_quote_summary_doc_fee)) < 0.2) {
			LO.print("Document Fee - found OK");
			System.out.println("Document Fee - found OK");
			count++;
		} else {
			LO.print("Document Fee - found wrong");
			System.out.println("Document Fee - found wrong");
		}

		if (upsell == customer_quote_summary_upsell) {
			LO.print("Upsell - found OK");
			System.out.println("Upsell - found OK");
			count++;
		} else {
			LO.print("Upsell - found wrong");
			System.out.println("Upsell - found wrong");
		}

		if ((Difference.of_two_Double_Values(defaultFinanceCommission,
				customer_quote_summary_default_finance_commission)) < 0.2) {
			LO.print("Default Finance Commission - found OK");
			System.out.println("Default Finance Commission - found OK");
			count++;
		} else {
			LO.print("Default Finance Commission - found wrong");
			System.out.println("Default Finance Commission - found wrong");
		}

		if (upsellCommission == customer_quote_summary_upsell_commission) {
			LO.print("Upsell Commission - found OK");
			System.out.println("Upsell Commission - found OK");
			count++;
		} else {
			LO.print("Upsell Commission - found wrong");
			System.out.println("Upsell Commission - found wrong");
		}

		if (maintCommission == customer_quote_summary_maint_commission) {
			LO.print("Maintenance Commission - found OK");
			System.out.println("Maintenance Commission - found OK");
			count++;
		} else {
			LO.print("Maintenance Commission - found wrong");
			System.out.println("Maintenance Commission - found wrong");
		}

		if ((Difference.of_two_Double_Values(docFeeCommission, customer_quote_summary_doc_fee_commission)) < 0.2) {
			LO.print("Document Fee Commission - found OK");
			System.out.println("Document Fee Commission - found OK");
			count++;
		} else {
			LO.print("Document Fee Commission - found wrong");
			System.out.println("Document Fee Commission - found wrong");
		}

		if ((Difference.of_two_Double_Values(totalCommission, customer_quote_summary_total_commision)) < 0.2) {
			LO.print("Total Commission - found OK");
			System.out.println("Total Commission - found OK");
			count++;
		} else {
			LO.print("Total Commission - found wrong");
			System.out.println("Total Commission - found wrong");
		}

		if ((Difference.of_two_Double_Values(referrerCommission, customer_quote_summary_referrer_commision)) < 0.2) {
			LO.print("Referrer Commission - found OK");
			System.out.println("Referrer Commission - found OK");
			count++;
		} else {
			LO.print("Referrer Commission - found wrong");
			System.out.println("Referrer Commission - found wrong");
		}

		if (count == 20) {
			status = true;
		}
		return status;

	}

	public boolean quote_summary_configuration_value_verification_without_maintenance(String sheet_name)
			throws IOException {

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

		ExplicitWait.visibleElement(driver, quote_summary_broker_upsell_margin_percentage, 20);
		double brokerUpsellMarginPercentageFromScreen = Double
				.parseDouble(quote_summary_broker_upsell_margin_percentage.getText().trim().substring(0, 4));

		ExplicitWait.visibleElement(driver, quote_summary_broker_upsell_margin, 20);
		double brokerUpsellMarginFromScreen = Double
				.parseDouble(RemoveComma.of(quote_summary_broker_upsell_margin.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_decument_fee_margin, 20);
		double documentFeeMarginFromScreen = Double
				.parseDouble(RemoveComma.of(quote_summary_decument_fee_margin.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_refferer_margin, 20);
		double reffererMarginFromScreen = Double
				.parseDouble(RemoveComma.of(quote_summary_refferer_margin.getText().trim().substring(2)));

		// getting values from excel

		double tempbaseInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(208, 1, sheet_name);

		double baseInterestRateFromExcel = (tempbaseInterestRateFromExcel * 100);

		double financeMarginFromExcel = GetExcelFormulaValue.get_formula_value(208, 3, sheet_name);

		double deductionsFromExcel = GetExcelFormulaValue.get_formula_value(210, 1, sheet_name);

		double additionalMarginFromExcel = GetExcelFormulaValue.get_formula_value(210, 3, sheet_name);

		double totalMarginFromExcel = GetExcelFormulaValue.get_formula_value(212, 1, sheet_name);

		double tempdefaualtBrokerMarginPercentageFromExcel = GetExcelFormulaValue.get_formula_value(216, 4, sheet_name);

		double defaualtBrokerMarginPercentageFromExcel = (tempdefaualtBrokerMarginPercentageFromExcel * 100);

		double tempbrokerUpsellMarginPercentageFromExcel = GetExcelFormulaValue.get_formula_value(218, 4, sheet_name);

		double brokerUpsellMarginPercentageFromExcel = (tempbrokerUpsellMarginPercentageFromExcel * 100);

		double BrokerUpsellMarginFromExcel = GetExcelFormulaValue.get_formula_value(220, 1, sheet_name);

		double documentFeeMarginFromExcel = GetExcelFormulaValue.get_formula_value(220, 4, sheet_name);

		double reffererMarginFromExcel = GetExcelFormulaValue.get_formula_value(222, 1, sheet_name);

		// verifying actual and expected values

		int count = 0;
		boolean status = false;
		if (baseInterestRateFromExcel == baseInterestRateFromScreen) {
			LO.print("Base Interest Rate found OK");
			System.out.println("Base Interest Rate found OK");
			count++;
		} else {
			LO.print("Base Interest Rate found wrong");
			System.out.println("Base Interest Rate found wrong");
		}

		if (Difference.of_two_Double_Values(financeMarginFromScreen, financeMarginFromExcel) < 0.2) {
			LO.print("Finance Margin found OK");
			System.out.println("Finance Margin found OK");
			count++;
		} else {
			LO.print("Finance Margin found wrong");
			System.out.println("Finance Margin found wrong");
		}

		if (Difference.of_two_Double_Values(financeMarginFromScreen, financeMarginFromExcel) < 0.2) {
			LO.print("Finance Margin found OK");
			System.out.println("Finance Margin found OK");
			count++;
		} else {
			LO.print("Finance Margin found wrong");
			System.out.println("Finance Margin found wrong");
		}

		if (Difference.of_two_Double_Values(deductionsFromScreen, deductionsFromExcel) < 0.2) {
			LO.print("Deductions found OK");
			System.out.println("Deductions found OK");
			count++;
		} else {
			LO.print("Deductions found wrong");
			System.out.println("Deductions found wrong");
		}

		if (Difference.of_two_Double_Values(additionalMarginFromScreen, additionalMarginFromExcel) < 0.2) {
			LO.print("Additional Margin found OK");
			System.out.println("Additional Margin found OK");
			count++;
		} else {
			LO.print("Additional Margin found wrong");
			System.out.println("Additional Margin found wrong");
		}

		if (Difference.of_two_Double_Values(totalMarginFromScreen, totalMarginFromExcel) < 0.2) {
			LO.print("Total Margin found OK");
			System.out.println("Total Margin found OK");
			count++;
		} else {
			LO.print("Total Margin found wrong");
			System.out.println("Total Margin found wrong");
		}

		if (defaualtBrokerMarginPercentageFromExcel == defaultBrokerMarginPercentageFromScreen) {
			LO.print("Default Broker Margin percentage found OK");
			System.out.println("Default Broker Margin percentage found OK");
			count++;
		} else {
			LO.print("Default Broker Margin percentage found wrong");
			System.out.println("Default Broker Margin percentage found wrong");
		}

		if (brokerUpsellMarginPercentageFromScreen == brokerUpsellMarginPercentageFromExcel) {
			LO.print("Broker Upsell Margin percentage found OK");
			System.out.println("Broker Upsell Margin percentage found OK");
			count++;
		} else {
			LO.print("Broker Upsell Margin percentage found wrong");
			System.out.println("Broker Upsell Margin percentage found wrong");
		}

		if (Difference.of_two_Double_Values(brokerUpsellMarginFromScreen, BrokerUpsellMarginFromExcel) < 0.2) {
			LO.print("Broker Upsell Margin  found OK");
			System.out.println("Broker Upsell Margin  found OK");
			count++;
		} else {
			LO.print("Broker Upsell Margin  found wrong");
			System.out.println("Broker Upsell Margin  found wrong");
		}

		if (Difference.of_two_Double_Values(documentFeeMarginFromScreen, documentFeeMarginFromExcel) < 0.2) {
			LO.print("Document Fee Margin  found OK");
			System.out.println("Document Fee Margin  found OK");
			count++;
		} else {
			LO.print("Document Fee Margin  found wrong");
			System.out.println("Document Fee Margin  found wrong");
		}

		if (Difference.of_two_Double_Values(reffererMarginFromScreen, reffererMarginFromExcel) < 0.2) {
			LO.print("Refferer Margin  found OK");
			System.out.println("Refferer Margin  found OK");
			count++;
		} else {
			LO.print("Refferer  Margin  found wrong");
			System.out.println("Refferer Margin  found wrong");
		}

		if (count == 11) {
			status = true;
		}
		return status;
	}

	public boolean quote_summary_configuration_value_verification_with_maintenance(String sheet_name)
			throws IOException {

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

		ExplicitWait.visibleElement(driver, quote_summary_broker_upsell_margin_percentage, 20);
		double brokerUpsellMarginPercentageFromScreen = Double
				.parseDouble(quote_summary_broker_upsell_margin_percentage.getText().trim().substring(0, 4));

		ExplicitWait.visibleElement(driver, quote_summary_broker_upsell_margin, 20);
		double brokerUpsellMarginFromScreen = Double
				.parseDouble(RemoveComma.of(quote_summary_broker_upsell_margin.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_maintenance_margin, 20);
		double maintMarginFromScreen = Double
				.parseDouble(RemoveComma.of(quote_summary_maintenance_margin.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_decument_fee_margin_with_maint, 20);
		double documentFeeMarginFromScreen = Double.parseDouble(
				RemoveComma.of(quote_summary_decument_fee_margin_with_maint.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_refferer_margin_with_maint, 20);
		double reffererMarginFromScreen = Double
				.parseDouble(RemoveComma.of(quote_summary_refferer_margin_with_maint.getText().trim().substring(2)));

		// getting values from excel

		double tempbaseInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(208, 1, sheet_name);

		double baseInterestRateFromExcel = (tempbaseInterestRateFromExcel * 100);

		double financeMarginFromExcel = GetExcelFormulaValue.get_formula_value(208, 3, sheet_name);

		double deductionsFromExcel = GetExcelFormulaValue.get_formula_value(210, 1, sheet_name);

		double additionalMarginFromExcel = GetExcelFormulaValue.get_formula_value(210, 3, sheet_name);

		double totalMarginFromExcel = GetExcelFormulaValue.get_formula_value(212, 1, sheet_name);

		double tempdefaualtBrokerMarginPercentageFromExcel = GetExcelFormulaValue.get_formula_value(216, 4, sheet_name);

		double defaualtBrokerMarginPercentageFromExcel = (tempdefaualtBrokerMarginPercentageFromExcel * 100);

		double tempbrokerUpsellMarginPercentageFromExcel = GetExcelFormulaValue.get_formula_value(218, 4, sheet_name);

		double brokerUpsellMarginPercentageFromExcel = (tempbrokerUpsellMarginPercentageFromExcel * 100);

		double brokerUpsellMarginFromExcel = GetExcelFormulaValue.get_formula_value(220, 1, sheet_name);

		double maintMarginFromExcel = GetExcelFormulaValue.get_formula_value(224, 1, sheet_name);

		double documentFeeMarginFromExcel = GetExcelFormulaValue.get_formula_value(220, 4, sheet_name);

		double reffererMarginFromExcel = GetExcelFormulaValue.get_formula_value(222, 1, sheet_name);

		// verifying actual and expected values

		int count = 0;

		boolean status = false;
		if (baseInterestRateFromExcel == baseInterestRateFromScreen) {
			LO.print("Base Interest Rate found OK");
			System.out.println("Base Interest Rate found OK");
			count++;
		} else {
			LO.print("Base Interest Rate found wrong");
			System.out.println("Base Interest Rate found wrong");
		}

		if (Difference.of_two_Double_Values(financeMarginFromScreen, financeMarginFromExcel) < 0.2) {
			LO.print("Finance Margin found OK");
			System.out.println("Finance Margin found OK");
			count++;
		} else {
			LO.print("Finance Margin found wrong");
			System.out.println("Finance Margin found wrong");
		}

		if (Difference.of_two_Double_Values(financeMarginFromScreen, financeMarginFromExcel) < 0.2) {
			LO.print("Finance Margin found OK");
			System.out.println("Finance Margin found OK");
			count++;
		} else {
			LO.print("Finance Margin found wrong");
			System.out.println("Finance Margin found wrong");
		}

		if (Difference.of_two_Double_Values(deductionsFromScreen, deductionsFromExcel) < 0.2) {
			LO.print("Deductions found OK");
			System.out.println("Deductions found OK");
			count++;
		} else {
			LO.print("Deductions found wrong");
			System.out.println("Deductions found wrong");
		}

		if (Difference.of_two_Double_Values(additionalMarginFromScreen, additionalMarginFromExcel) < 0.2) {
			LO.print("Additional Margin found OK");
			System.out.println("Additional Margin found OK");
			count++;
		} else {
			LO.print("Additional Margin found wrong");
			System.out.println("Additional Margin found wrong");
		}

		if (Difference.of_two_Double_Values(totalMarginFromScreen, totalMarginFromExcel) < 0.2) {
			LO.print("Total Margin found OK");
			System.out.println("Total Margin found OK");
			count++;
		} else {
			LO.print("Total Margin found wrong");
			System.out.println("Total Margin found wrong");
		}

		if (defaualtBrokerMarginPercentageFromExcel == defaultBrokerMarginPercentageFromScreen) {
			LO.print("Default Broker Margin percentage found OK");
			System.out.println("Default Broker Margin percentage found OK");
			count++;
		} else {
			LO.print("Default Broker Margin percentage found wrong");
			System.out.println("Default Broker Margin percentage found wrong");
		}

		if (brokerUpsellMarginPercentageFromScreen == brokerUpsellMarginPercentageFromExcel) {
			LO.print("Broker Upsell Margin percentage found OK");
			System.out.println("Broker Upsell Margin percentage found OK");
			count++;
		} else {
			LO.print("Broker Upsell Margin percentage found wrong");
			System.out.println("Broker Upsell Margin percentage found wrong");
		}

		if (Difference.of_two_Double_Values(brokerUpsellMarginFromScreen, brokerUpsellMarginFromExcel) < 0.2) {
			LO.print("Broker Upsell Margin  found OK");
			System.out.println("Broker Upsell Margin  found OK");
			count++;
		} else {
			LO.print("Broker Upsell Margin  found wrong");
			System.out.println("Broker Upsell Margin  found wrong");
		}

		if (Difference.of_two_Double_Values(maintMarginFromScreen, maintMarginFromExcel) < 0.2) {
			LO.print("Maintenance Margin  found OK");
			System.out.println("Maintenance Margin  found OK");
			count++;
		} else {
			LO.print("Maintenance Margin  found wrong");
			System.out.println("Maintenance Margin  found wrong");
		}

		if (Difference.of_two_Double_Values(documentFeeMarginFromScreen, documentFeeMarginFromExcel) < 0.2) {
			LO.print("Document Fee Margin  found OK");
			System.out.println("Document Fee Margin  found OK");
			count++;
		} else {
			LO.print("Document Fee Margin  found wrong");
			System.out.println("Document Fee Margin  found wrong");
		}

		if (Difference.of_two_Double_Values(reffererMarginFromScreen, reffererMarginFromExcel) < 0.2) {
			LO.print("Refferer Margin  found OK");
			System.out.println("Refferer Margin  found OK");
			count++;
		} else {
			LO.print("Refferer  Margin  found wrong");
			System.out.println("Refferer Margin  found wrong");
		}

		if (count == 12) {
			status = true;
		}
		return status;
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

		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost_without_maintenance, 30);

		double holding_cost_total_monthly_holding_cost_from_screen = Double.parseDouble(RemoveComma
				.of(quote_summary_total_monthly_holding_cost_without_maintenance.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 30);

		double customer_quote_summary_monthly_finance_rental_from_screen = Double
				.parseDouble(RemoveComma.of(quote_summary_monthly_finance_rental.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_initial_finance_rental, 30);

		double customer_quote_initial_finance_rental = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_initial_finance_rental.getText().trim().substring(2)));

		// writing values to excel

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(34).getCell(7).setCellValue(0.07);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		// getting values from excel

		double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(51, 1,
				sheet_name);

		double monthlyFinanceRental = GetExcelFormulaValue.get_formula_value(176, 0, sheet_name);

		double initialFinanceRental = GetExcelFormulaValue.get_formula_value(179, 1, sheet_name);

		// verifying actual and expected values

		int count = 0;

		boolean status = false;
		if (Difference.of_two_Double_Values(holding_cost_total_monthly_holding_cost_from_screen,
				holding_cost_total_monthly_holding_cost_from_excel) < 0.2) {
			LO.print("Holding Cost after changing Base Int. Rate -  found OK");
			System.out.println("Holding Cost after changing Base Int. Rate -  found OK");
			count++;
		} else {
			LO.print("Holding Cost after changing Base Int. Rate -  found wrong");
			System.out.println("Holding Cost after changing Base Int. Rate -  found wrong");
		}

		if (Difference.of_two_Double_Values(customer_quote_summary_monthly_finance_rental_from_screen,
				monthlyFinanceRental) < 0.2) {
			LO.print("Monthly Finance Rental after changing Base Int. Rate -  found OK");
			System.out.println("Monthly Finance Rental after changing Base Int. Rate -  found OK");
			count++;
		} else {
			LO.print("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
			System.out.println("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
		}

		if ((Difference.of_two_Double_Values(initialFinanceRental, customer_quote_initial_finance_rental)) < 0.2) {
			LO.print("Initial Finance Rental found OK");
			System.out.println("Initial Finance Rental found OK");
			count++;
		} else {
			LO.print("Initial Finance Rental found wrong");
			System.out.println("Initial Finance Rental found wrong");
		}

		if (count == 3) {
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

		wb1.getSheet(sheet_name).getRow(34).getCell(7).setCellValue(0.065);

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

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_initial_finance_rental, 30);

		double customer_quote_initial_finance_rental = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_initial_finance_rental.getText().trim().substring(2)));

		// writing values to excel

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(34).getCell(7).setCellValue(0.07);

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		// getting values from excel

		double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(51, 1,
				sheet_name);

		double monthlyFinanceRental = GetExcelFormulaValue.get_formula_value(176, 0, sheet_name);

		double initialFinanceRental = GetExcelFormulaValue.get_formula_value(179, 1, sheet_name);

		// verifying actual and expected values

		int count = 0;

		boolean status = false;
		if (Difference.of_two_Double_Values(holding_cost_total_monthly_holding_cost_from_screen,
				holding_cost_total_monthly_holding_cost_from_excel) < 0.2) {
			LO.print("Holding Cost after changing Base Int. Rate -  found OK");
			System.out.println("Holding Cost after changing Base Int. Rate -  found OK");
			count++;
		} else {
			LO.print("Holding Cost after changing Base Int. Rate -  found wrong");
			System.out.println("Holding Cost after changing Base Int. Rate -  found wrong");
		}

		if (Difference.of_two_Double_Values(customer_quote_summary_monthly_finance_rental_from_screen,
				monthlyFinanceRental) < 0.2) {
			LO.print("Monthly Finance Rental after changing Base Int. Rate -  found OK");
			System.out.println("Monthly Finance Rental after changing Base Int. Rate -  found OK");
			count++;
		} else {
			LO.print("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
			System.out.println("Monthly Finance Rental after changing Base Int. Rate -  found wrong");
		}

		if ((Difference.of_two_Double_Values(initialFinanceRental, customer_quote_initial_finance_rental)) < 0.2) {
			LO.print("Initial Finance Rental found OK");
			System.out.println("Initial Finance Rental found OK");
			count++;
		} else {
			LO.print("Initial Finance Rental found wrong");
			System.out.println("Initial Finance Rental found wrong");
		}

		if (count == 3) {
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

		wb1.getSheet(sheet_name).getRow(34).getCell(7).setCellValue(0.065);

		FileOutputStream out1 = new FileOutputStream(prop.getProperty("formula_excel_path"));

		wb1.write(out1);

		return status;
	}

	public boolean quote_summary_edit_finance_margin_value_verification(String sheet_name)
			throws IOException, InterruptedException {

		LO.print(
				"*************Editing Fianance Margin and Verifying Values on quote summary page has been started************");
		System.out.println(
				"*************Editing Fianance Margin and Verifying Values on quote summary page has been started************");

		// Edit finance margin configuration values from screen

		ExplicitWait.visibleElement(driver, quote_summary_configuration_finance_margin_input, 30);
		quote_summary_configuration_finance_margin_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		quote_summary_configuration_finance_margin_input.sendKeys("10000");

		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		LO.print("Finance margin changed to 10000");
		System.out.println("Finance margin changed to 10000");

		// Getting values from screen
		Thread.sleep(2000);

		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 30);

		double customer_quote_summary_monthly_finance_rental_from_screen = Double
				.parseDouble(RemoveComma.of(quote_summary_monthly_finance_rental.getText().trim().substring(2)));

		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_initial_finance_rental, 30);

		double customer_quote_initial_finance_rental = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_initial_finance_rental.getText().trim().substring(2)));

		// writing values to excel

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(63).getCell(2).setCellValue(10000);
		wb.getSheet(sheet_name).getRow(63).getCell(1).setCellFormula("C64");
		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		// getting values from excel

		double monthlyFinanceRental = GetExcelFormulaValue.get_formula_value(176, 0, sheet_name);

		double initialFinanceRental = GetExcelFormulaValue.get_formula_value(179, 1, sheet_name);

		// verifying actual and expected values

		int count = 0;

		boolean status = false;

		if (Difference.of_two_Double_Values(customer_quote_summary_monthly_finance_rental_from_screen,
				monthlyFinanceRental) < 0.2) {
			LO.print("Monthly Finance Rental after changing finance margin -  found OK");
			System.out.println("Monthly Finance Rental after changing finance margin -  found OK");
			count++;
		} else {
			LO.print("Monthly Finance Rental after changing finance margin -  found wrong");
			System.out.println("Monthly Finance Rental after changing finance margin -  found wrong");
		}

		if ((Difference.of_two_Double_Values(initialFinanceRental, customer_quote_initial_finance_rental)) < 0.2) {
			LO.print("Initial Finance Rental found OK");
			System.out.println("Initial Finance Rental found OK");
			count++;
		} else {
			LO.print("Initial Finance Rental found wrong");
			System.out.println("Initial Finance Rental found wrong");
		}

		if (count == 2) {
			status = true;
		}

		// writing values to excel

		FileInputStream in1 = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb1 = new XSSFWorkbook(in1);

		wb1.getSheet(sheet_name).getRow(63).getCell(1).setCellFormula("B61*B63");

		FileOutputStream out1 = new FileOutputStream(prop.getProperty("formula_excel_path"));

		wb1.write(out1);

		return status;
	}

	public boolean quote_summary_edit_maintenance_margin_value_verification(String sheet_name)
			throws IOException, InterruptedException {

		LO.print(
				"*************Editing Maintenance Margin and Verifying Values on quote summary page has been started************");
		System.out.println(
				"*************Editing Maintenance Margin and Verifying Values on quote summary page has been started************");

		// Edit finance margin configuration values from screen

		ExplicitWait.visibleElement(driver, quote_summary_configuration_maintenance_margin_input, 30);
		quote_summary_configuration_maintenance_margin_input.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		quote_summary_configuration_maintenance_margin_input.sendKeys("30");

		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		LO.print("Finance margin changed to 30 %");
		System.out.println("Finance margin changed to 30 %");

		// Getting values from screen
		Thread.sleep(2000);

		ExplicitWait.visibleElement(driver, quote_summary_monthly_maintenance_rental, 30);

		double customer_quote_summary_monthly_maint_rental_from_screen = Double
				.parseDouble(RemoveComma.of(quote_summary_monthly_maintenance_rental.getText().trim().substring(2)));

		double customer_quote_initial_maint_rental = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_initial_maint_rental.getText().trim().substring(2)));

		// writing values to excel

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheet_name).getRow(104).getCell(1).setCellValue(0.3);
		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);

		// getting values from excel

		double monthlyMaintenanceRental = GetExcelFormulaValue.get_formula_value(176, 1, sheet_name);

		double initialMaintRental = GetExcelFormulaValue.get_formula_value(179, 3, sheet_name);

		// verifying actual and expected values

		int count = 0;

		boolean status = false;

		if (Difference.of_two_Double_Values(customer_quote_summary_monthly_maint_rental_from_screen,
				monthlyMaintenanceRental) < 0.2) {
			LO.print("Monthly Finance Rental after changing finance margin -  found OK");
			System.out.println("Monthly Finance Rental after changing finance margin -  found OK");
			count++;
		} else {
			LO.print("Monthly Finance Rental after changing finance margin -  found wrong");
			System.out.println("Monthly Finance Rental after changing finance margin -  found wrong");
		}

		if ((Difference.of_two_Double_Values(initialMaintRental, customer_quote_initial_maint_rental)) < 0.2) {
			LO.print("Initial Maint Rental found OK");
			System.out.println("Initial Maint Rental found OK");
			count++;
		} else {
			LO.print("Initial Maint Rental found wrong");
			System.out.println("Initial Maint Rental found wrong");
		}

		if (count == 2) {
			status = true;
		}

		return status;
	}

public void save_quote() throws InterruptedException {

		ExplicitWait.visibleElement(driver, quote_summary_save_button, 30);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", quote_summary_save_button);
		
//		Actions act = new Actions(driver);
//		act.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);

		ExplicitWait.visibleElement(driver, quote_summary_ref_no, 120);

		String quote_ref_no = quote_summary_ref_no.getText();

		LO.print("*********Customer Quote generated successfully and Quote_ref_no is=" + quote_ref_no);
		System.out.println("*********Customer Quote generated successfully and Quote_ref_no is=" + quote_ref_no);

	}

	public boolean quote_summary_outright_BCH_without_maintenance(String sheet_name)
			throws InterruptedException, IOException {

		LO.print("*************Calculations for Quote Summary page has been started************");
		System.out.println("*************Calculations for Quote Summary page has been started************");

		obj_read_excel_calculation_page = new ReadExcelCalculation();

		Click.on(driver, quote_summary, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);

		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);

		ExplicitWait.visibleElement(driver, quote_summary_ref_no, 120);
		ExplicitWait.visibleElement(driver, quote_summary_cost_otr_price, 60);
		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost_without_maintenance, 60);
		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 60);
		ExplicitWait.visibleElement(driver, quote_summary_acq_contract_type, 60);
		ExplicitWait.visibleElement(driver, quote_summary_customer_contract_type, 60);

		LO.print("Reading values from sceen -Quote Summary Page");
		System.out.println("Reading values from sceen -Quote Summary Page");

		String quote_ref_no = quote_summary_ref_no.getText();
		String temp_quote_summary_cost_otr_price = quote_summary_cost_otr_price.getText().trim().substring(2);
		String temp_quote_summary_total_monthly_holding_cost = quote_summary_total_monthly_holding_cost_without_maintenance
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

//		LO.print("Customer Quote generated successfully and Quote_ref_no ="+quote_ref_no);
//		System.out.println("Customer Quote generated successfully and Quote_ref_no ="+quote_ref_no);

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

	public boolean quote_summary_outright_BCH_with_maintenance(String sheet_name)
			throws InterruptedException, IOException {

		LO.print("*************Calculations for Quote Summary page has been started************");
		System.out.println("*************Calculations for Quote Summary page has been started************");

		obj_read_excel_calculation_page = new ReadExcelCalculation();
		Click.on(driver, quote_summary, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);

		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);

		ExplicitWait.visibleElement(driver, quote_summary_ref_no, 120);
		ExplicitWait.visibleElement(driver, quote_summary_cost_otr_price, 120);
		ExplicitWait.visibleElement(driver, quote_summary_monthly_maintenance_rental, 120);
		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 120);
		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 120);
//		ExplicitWait.visibleElement(driver, quote_summary_monthly_maintenance_rental, 120);
		ExplicitWait.visibleElement(driver, quote_summary_acq_contract_type, 120);
		ExplicitWait.visibleElement(driver, quote_summary_customer_contract_type, 120);

		LO.print("Reading values from sceen -Quote Summary Page");
		System.out.println("Reading values from sceen -Quote Summary Page");

		String quote_ref_no = quote_summary_ref_no.getText();
		String temp_quote_summary_cost_otr_price = quote_summary_cost_otr_price.getText().trim().substring(2);
		String temp_quote_summary_total_monthly_holding_cost = quote_summary_total_monthly_holding_cost.getText().trim()
				.substring(2);
		String temp_quote_summary_monthly_finance_rental = quote_summary_monthly_finance_rental.getText().trim()
				.substring(2);
		String temp_quote_summary_monthly_maintenance_rental = quote_summary_monthly_maintenance_rental.getText().trim()
				.substring(2);
		String temp_quote_summary_monthly_total_rental = quote_summary_monthly_total_rental.getText().trim()
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

		LO.print("Quote_summary_monthly_finance_rental =" + temp_quote_summary_monthly_maintenance_rental);
		System.out.println("Quote_summary_monthly_finance_rental =" + temp_quote_summary_monthly_maintenance_rental);

		LO.print("Quote_summary_monthly_finance_rental =" + temp_quote_summary_monthly_total_rental);
		System.out.println("Quote_summary_monthly_finance_rental =" + temp_quote_summary_monthly_total_rental);

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
		String quote_summary_monthly_maintenance_rental_from_screen = RemoveComma
				.of(temp_quote_summary_monthly_maintenance_rental);
		String quote_summary_monthly_total_rental_from_screen = RemoveComma.of(temp_quote_summary_monthly_total_rental);

		double quote_summary_cost_otr_price_from_screen_converted = Double
				.parseDouble(quote_summary_cost_otr_price_from_screen);
		double quote_summary_total_monthly_holding_cost_from_screen_converted = Double
				.parseDouble(quote_summary_total_monthly_holding_cost_from_screen);
		double quote_summary_monthly_finance_rental_from_screen_converted = Double
				.parseDouble(quote_summary_monthly_finance_rental_from_screen);
		double quote_summary_monthly_maintenance_rental_from_screen_converted = Double
				.parseDouble(quote_summary_monthly_maintenance_rental_from_screen);
		double quote_summary_monthly_total_rental_from_screen_converted = Double
				.parseDouble(quote_summary_monthly_total_rental_from_screen);

		return obj_read_excel_calculation_page.verify_quote_summary_values_from_excel_with_maintenance(
				quote_summary_cost_otr_price_from_screen_converted,
				quote_summary_total_monthly_holding_cost_from_screen_converted,
				quote_summary_monthly_finance_rental_from_screen_converted,
				quote_summary_monthly_maintenance_rental_from_screen_converted,
				quote_summary_monthly_total_rental_from_screen_converted, sheet_name);

	}

}
