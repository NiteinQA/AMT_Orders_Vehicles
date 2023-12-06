package com.amt.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Difference;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.HelperClass;
import com.amt.testUtil.RemoveComma;
import com.amt.testUtil.Dropdown;

public class UnderwritingPopupPage extends TestBase {

	JavascriptExecutor js;
	AcquisitionListingPage obj_acq_listing_page;

	Properties prop;
	// 1.underwriting_menu_link
	@FindBy(xpath = "//span[contains(text(),'Underwriting')]")
	private WebElement underwriting_menu_link;

	// Underwriting popup code --

	// 1.underwriting_icon_link
	@FindBy(xpath = "(//img[@src='../../assets/images/opportunity/underwritting.svg'])[1]")
	private WebElement underwriting_icon_link;

	// 2.underwriting_popup_send_for_underwriting_button
	@FindBy(xpath = "(//button[normalize-space()='Send for underwriting'])[1]")
	private WebElement underwriting_send_for_underwriting_button;

	// 3.underwriting_popup_proposal_id
	@FindBy(xpath = "//*[@id='underwriting_popup']/div/div/div[1]/h4")
	private WebElement underwriting_popup_proposal_id;

	// 4.underwriting_popup_quote_ref_no
	@FindBy(xpath = "//*[normalize-space()='Quote reference no.:']//ancestor::div[1]")
	private WebElement underwriting_popup_quote_ref_no;

	// 5.underwriting_popup_cancel_button
	@FindBy(xpath = "(//button[@class='btn btn-outline-secondary mr-2'][normalize-space()='Cancel'])[3]")
	private WebElement underwriting_popup_cancel_button;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	// 6.underwriting_popup_download_contract_file
	@FindBy(xpath = "(//i[@class='btn-icon-contract-file'])[1]")
	private WebElement underwriting_popup_download_contract_file;

	// 7.underwriting_popup_download_proposal
	@FindBy(xpath = "(//button[normalize-space()='Download Proposal'])[1]")
	private WebElement underwriting_popup_download_proposal;

	// 8.underwriting_popup_close
	@FindBy(xpath = "//div[@class='statusandclose d-flex']//button[@type='button'][normalize-space()='×']")
	private WebElement underwriting_popup_close_icon;

	// 8.underwriting_popup_close button
	@FindBy(xpath = "(//button[@data-dismiss='modal'][normalize-space()='Close'])[1]")
	private WebElement underwriting_popup_close_button;
	


		// customer quote summary button
		@FindBy(xpath = "//*[normalize-space()='Customer quote summary']//ancestor::button")
		private WebElement underwriting_popup_customer_quote_summary_button;

		// terms
		@FindBy(xpath = "//*[normalize-space()='Term']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_terms;

		// Miles per annum
		@FindBy(xpath = "//*[normalize-space()='Miles per annum']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_underwriting_popup_miles_per_annum;

		// Funder name
		@FindBy(xpath = "//*[normalize-space()='Funder']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_funder_name;

		// quote ref no.
		@FindBy(xpath = "//*[normalize-space()='Quote reference']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_quote_ref_number;

		// quote exp date
		@FindBy(xpath = "//*[normalize-space()='Quote expiry date']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_quote_exp_date;

		// payment profile
		@FindBy(xpath = "//*[normalize-space()='Payment profile']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_payment_profile;

		// contract mileage
		@FindBy(xpath = "//*[normalize-space()='Contract mileage']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_contract_mileage;	

		// Initial finance rental
		@FindBy(xpath = "//*[normalize-space()='Initial finance rental']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_initial_finance_rental;

		// initial maint rental
		@FindBy(xpath = "//*[normalize-space()='Initial maint. rental']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_initial_maint_rental;

		// Total initial rental
		@FindBy(xpath = "//*[normalize-space()='Total initial rental']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_total_initial_rental;

		// Pence per excess mile - finance
		@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - finance']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_pence_per_excess_mile_finance;

		// Pence per excess mile - maint.
		@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - maint.']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_pence_per_excess_mile_maint;

		// Pence per excess mile - total
		@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - total']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_pence_per_excess_mile_total;

		
//		@FindBy(xpath = "//*[normalize-space()='Contract type']//ancestor::div[1]//div//strong")
//		private WebElement underwriting_popup_customer_contract_type;

	// Ownbook - pop up values for accept

	//// ownbook_underwriting_popup_accept (//button[normalize-space()='Accept'])[1]
	// ownbook_underwriting_popup_reject - (//button[normalize-space()='Reject'])[1]

	@FindBy(xpath = "//*[@id=\"underwriting_popup\"]/div/div/div[3]/div[2]/div/button[1]")
	private WebElement ownbook_underwriting_popup_accept;

	@FindBy(xpath = "(//button[normalize-space()='Reject'])[1]")
	private WebElement ownbook_underwriting_popup_reject;

	@FindBy(xpath = "//*[@id=\"confirm-sales-user-decision\"]/div/div/div[3]/div/button[2]")
	private WebElement ownbook_underwriting_popup_accept_ok_button;
	
	
	//Total cash price
		@FindBy(xpath = "//*[normalize-space()='Total cash price']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_total_cash_price;
		
		//Cash deposit
		@FindBy(xpath = "//*[normalize-space()='Cash deposit']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_cash_deposit;
		
		//Balance to finance
		@FindBy(xpath = "//*[normalize-space()='Balance to finance']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_balance_to_finance;
		
		//Finance charges
		@FindBy(xpath = "//*[normalize-space()='Finance charges']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_finance_charges;
		
		//Balance payable
		@FindBy(xpath = "//*[normalize-space()='Balance payable']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_balance_payable;
		
		//Initial cash payment inc. document fee
		@FindBy(xpath = "//*[normalize-space()='Initial cash payment inc. document fee']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_initial_cash_payment_inc_document_fee;

		//No. of monthly payments	
		@FindBy(xpath = "//*[normalize-space()='No. of monthly payments']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_no_of_monthly_payments	;
		
		//Final balloon payment	
		@FindBy(xpath = "//*[normalize-space()='Final balloon payment']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_final_balloon_payment	;
		
		//Option to purchase fee	
		@FindBy(xpath = "//*[normalize-space()='Option to purchase fee']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_option_to_purchase_fee	;
		
		//RFL included?	
		@FindBy(xpath = "//*[normalize-space()='RFL included?']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_RFL_included;
		
		//APR
		@FindBy(xpath = "//*[normalize-space()='APR']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_APR;
		
		// commission
		@FindBy(xpath = "//*[normalize-space()='Commission']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_commission;
		
		//Monthly finance payment
		@FindBy(xpath = "//*[normalize-space()='Monthly finance payment']//ancestor::div[1]//div//strong|//*[normalize-space()='Monthly finance rental']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_monthly_finance_payment;
		
		// Guaranteed future value
		@FindBy(xpath = "//*[normalize-space()='Guaranteed future value']//ancestor::div[1]//div//strong")
		private WebElement quote_summary_customer_quote_summary_guaranteed_future_value;

		// Final payment (inc. option to purchase fee)
		@FindBy(xpath = "//*[normalize-space()='Final payment (inc. option to purchase fee)']//ancestor::div[1]//div//strong")
		private WebElement quote_summary_customer_quote_summary_final_payment_inc_option_to_purchase_fee;
		
		// Pence per excess mile - finance
		@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - finance']//ancestor::div[1]//div//strong")
		private WebElement quote_summary_customer_quote_summary_pence_per_excess_mile_finance;
		
		//Optional final payment	
		@FindBy(xpath = "//*[normalize-space()='Optional final payment']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_optional_final_payment	;
		

		@FindBy(xpath = "//p[normalize-space()='Quote summary']")
		private WebElement quote_summary;

		@FindBy(xpath = "//*[normalize-space()='Quote reference no.:']//ancestor::div[1]//span[2]")
		private WebElement quote_summary_ref_no;

		@FindBy(xpath = "//*[normalize-space()='Cost OTR price']//ancestor::div[1]//div//strong")
		private WebElement quote_summary_cost_otr_price;

		@FindBy(xpath = "//*[normalize-space()='Total monthly holding cost']//ancestor::div[1]//div//strong")
		private WebElement quote_summary_total_monthly_holding_cost;

		@FindBy(xpath = "//*[normalize-space()='Monthly finance rental']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_monthly_finance_rental;

		@FindBy(xpath = "//*[normalize-space()='Monthly maint. rental']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_monthly_maintenance_rental;

		@FindBy(xpath = "//*[normalize-space()='Total monthly rental']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_monthly_total_rental;

		@FindBy(xpath = "//*[@id='headingHoldingCost']//*[normalize-space()='Contract type']//ancestor::div[1]//div//strong")
		private WebElement quote_summary_acq_contract_type;

		@FindBy(xpath = "//*[normalize-space()='Contract type']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_contract_type;

		@FindBy(xpath = "//div[@id='headingHoldingCost']//div[7]//div[1]//div[1]//p[1]//strong[1]")
		private WebElement quote_summary_total_monthly_holding_cost_without_maintenance;

		@FindBy(xpath = "//*[normalize-space()='Cost price ex. VAT & RFL']//ancestor::div[1]//div//strong")
		private WebElement quote_summary_cost_price_ex_vat_and_rfl;

		@FindBy(xpath = "//*[normalize-space()='VAT']//ancestor::div[1]//div//strong")
		private WebElement quote_summary_otr_vat;

		@FindBy(xpath = "//*[normalize-space()='RFL & FRF']//ancestor::div[1]//div//strong")
		private WebElement quote_summary_otr_rfl_and_frf;

		@FindBy(xpath = "(//*[normalize-space()='Term']//ancestor::div[1]//div//strong)[1]")
		private WebElement quote_summary_holding_cost_term;

		@FindBy(xpath = "(//*[normalize-space()='Miles per annum']//ancestor::div[1]//div//strong)[1]")
		private WebElement quote_summary_holding_cost_miles_per_annum;

		@FindBy(xpath = "//*[normalize-space()='Monthly finance cost']//ancestor::div[1]//div//strong")
		private WebElement quote_summary_holding_cost_monthly_finance_cost;

		@FindBy(xpath = "(//*[normalize-space()='Monthly maint. cost used']//ancestor::div[1]//div//strong)[1]")
		private WebElement quote_summary_holding_cost_monthly_maint_cost_used;

		@FindBy(xpath = "(//*[normalize-space()='CAP monthly maint. cost']//ancestor::div[1]//div//strong)[1]")
		private WebElement quote_summary_holding_cost_CAP_monthly_maint_cost;

		@FindBy(xpath = "//*[normalize-space()='Customer quote summary']")
		private WebElement underwriting_popup_customer_quote_summary_value_verification;
		
		

		@FindBy(xpath = "//app-hire-customer-quote-summary-header//*[normalize-space()='Term']//ancestor::div[1]//div/p")
		private WebElement underwriting_popup_customer_quote_term;

		@FindBy(xpath = "//app-hire-customer-quote-summary-header//*[normalize-space()='Miles per annum']//ancestor::div[1]//div/p")
		private WebElement underwriting_popup_customer_quote_miles;

		@FindBy(xpath = "//*[normalize-space()='Payment profile']//ancestor::div[1]//div/p")
		private WebElement underwriting_popup_customer_quote_payment_profile;

		@FindBy(xpath = "//*[normalize-space()='Initial finance rental']//ancestor::div[1]//div/p/strong")
		private WebElement underwriting_popup_customer_quote_initial_finance_rental;

		@FindBy(xpath = "//*[normalize-space()='Initial maint. rental']//ancestor::div[1]//div/p/strong")
		private WebElement underwriting_popup_customer_quote_initial_maint_rental;

		@FindBy(xpath = "//*[normalize-space()='Total initial rental']//ancestor::div[1]//div/p/strong")
		private WebElement underwriting_popup_customer_quote_initial_total_rental;

		@FindBy(xpath = "//*[normalize-space()='Part exchange value']//ancestor::div[1]//div/p/strong")
		private WebElement underwriting_popup_customer_quote_part_exchange_value;

		@FindBy(xpath = "//*[normalize-space()='Followed by']//ancestor::div[1]//div/p/strong")
		private WebElement underwriting_popup_customer_quote_followed_by;

		@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - finance']//ancestor::div[1]//div/p/strong")
		private WebElement underwriting_popup_customer_quote_pence_per_excess_mile_finance;

		@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - maint.']//ancestor::div[1]//div/p/strong")
		private WebElement underwriting_popup_customer_quote_pence_per_excess_mile_maintenance;

		@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - total']//ancestor::div[1]//div/p/strong")
		private WebElement underwriting_popup_customer_quote_pence_per_excess_mile_total;

		@FindBy(xpath = "//*[normalize-space()='Document fee']//ancestor::div[1]//div/p/strong")
		private WebElement underwriting_popup_customer_quote_doc_fee;

		@FindBy(xpath = "//*[normalize-space()='Upsell']//ancestor::div[1]//p/strong")
		private WebElement underwriting_popup_customer_quote_upsell;

		@FindBy(xpath = "//*[normalize-space()='Default finance comm.']//ancestor::div[1]//p/strong")
		private WebElement underwriting_popup_customer_quote_default_finance_commission;

		@FindBy(xpath = "//*[normalize-space()='Upsell comm.']//ancestor::div[1]//p/strong")
		private WebElement underwriting_popup_customer_quote_upsell_commission;

		@FindBy(xpath = "//*[normalize-space()='Document fee comm.']//ancestor::div[1]//p/strong")
		private WebElement underwriting_popup_customer_quote_doc_fee_commission;

		@FindBy(xpath = "//*[normalize-space()='Maint. comm.']//ancestor::div[1]//p/strong")
		private WebElement underwriting_popup_customer_quote_maint_commission;

		@FindBy(xpath = "//*[normalize-space()='Total comm.']//ancestor::div[1]//p/strong")
		private WebElement underwriting_popup_customer_quote_total_commission;

		@FindBy(xpath = "//*[normalize-space()='Referrer comm.']//ancestor::div[1]//p/strong")
		private WebElement underwriting_popup_customer_quote_referrer_commission;

		@FindBy(xpath = "//*[@id='headingConfig']/button/div")
		private WebElement quote_summary_configuration;

		@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Base interest rate']//ancestor::div[1]//p/strong")
		private WebElement quote_summary_base_interest_rate;

		@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Finance margin']//ancestor::div[1]//p/strong")
		private WebElement quote_summary_finance_margin;

		@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Deductions']//ancestor::div[1]//p/strong")
		private WebElement quote_summary_deductions;

		@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Additional margin']//ancestor::div[1]//p/strong")
		private WebElement quote_summary_additional_margin;

		@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Total margin']//ancestor::div[1]//p/strong")
		private WebElement quote_summary_total_margin;

		@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Default broker margin']//ancestor::div[1]//div/div/label")
		private WebElement quote_summary_default_broker_margin_percentage;

		@FindBy(xpath = "(//app-acquisition-summary-configuration//*[normalize-space()='Broker upsell margin']//ancestor::div[1]//div//label)[1]")
		private WebElement quote_summary_broker_upsell_margin_percentage;

		@FindBy(xpath = "(//app-acquisition-summary-configuration//*[normalize-space()='Broker upsell margin']//ancestor::div[1]//div//label)[2]//b")
		private WebElement quote_summary_broker_upsell_margin;

		@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Maint. margin']//ancestor::div[1]//div//label/strong")
		private WebElement underwriting_popup_maintenance_margin;

		@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Document fee margin']//ancestor::div[1]//div//label/b")
		private WebElement quote_summary_decument_fee_margin;

		@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Referrer margin']//ancestor::div[1]//div//label/b")
		private WebElement quote_summary_refferer_margin;

		@FindBy(xpath = "//input[@id='baseIntRate']")
		private WebElement quote_summary_configuration_base_int_rate_input;

		@FindBy(xpath = "//input[@id='FinanceMargin']")
		private WebElement quote_summary_configuration_finance_margin_input;

		@FindBy(xpath = "//input[@id='MaintenanceMarginPer']")
		private WebElement quote_summary_configuration_maintenance_margin_input;

		@FindBy(xpath = "//div[@class='row acquisition-menu']//div[3]//button[1]")
		private WebElement quote_summary_save_button;

		@FindBy(xpath = "//*[normalize-space()='Balance due']//ancestor::div[1]//p//strong")
		private WebElement balance_due_value;
		
		// vehicle details
		@FindBy(xpath = "//*[@class='heading ng-star-inserted']")
		private WebElement quote_summary_vehicle_heading;
		
		//Elements for purchase agreements
		

		// terms


		// Miles per annum
		@FindBy(xpath = "//*[normalize-space()='Miles per annum']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_miles_per_annum;

		// basic cash price
		@FindBy(xpath = "//*[normalize-space()='Basic cash price']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_basic_cash_price;

		// customer_quote_summary vat
		@FindBy(xpath = "//*[normalize-space()='VAT']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_vat;

		// non vat items
		@FindBy(xpath = "//*[normalize-space()='Non-VAT items']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_non_vat_items;


		// Order deposit
		@FindBy(xpath = "//*[normalize-space()='Order deposit']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_order_deposit;

		// Finance deposit
		@FindBy(xpath = "//*[normalize-space()='Finance deposit']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_finance_deposit;

		// Total deposit
		@FindBy(xpath = "//*[normalize-space()='Total deposit']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_total_deposit;

		@FindBy(xpath = "//*[normalize-space()='Part exchange value']//ancestor::div[1]//div/p/strong")
		private WebElement underwriting_popup_customer_quote_summary_part_exchange_value;


		// Document fee
		@FindBy(xpath = "//*[normalize-space()='Document fee']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_document_fee;



		// Initial cash payment
		@FindBy(xpath = "//*[normalize-space()='Initial cash payment']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_initial_cash_payment;

		// Followed by
		@FindBy(xpath = "//*[normalize-space()='Followed by']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_followed_by;


		// Monthly maint payment
		@FindBy(xpath = "(//*[normalize-space()='Monthly maint. payment']//ancestor::div[1]//div//strong)[2]")
		private WebElement underwriting_popup_customer_quote_summary_monthly_maint_payment;

		// Total Monthly payment
		@FindBy(xpath = "(//*[normalize-space()='Total monthly payment']//ancestor::div[1]//div//strong)[2]")
		private WebElement underwriting_popup_customer_quote_summary_total_monthly_payment;

		// Balloon
		@FindBy(xpath = "//*[normalize-space()='Balloon']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_balloon;

		// Final payment (inc. option to purchase fee)
		@FindBy(xpath = "//*[normalize-space()='Final payment (inc. option to purchase fee)']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_final_payment_inc_option_to_purchase_fee;


		// Credit type
		@FindBy(xpath = "//*[normalize-space()='Credit type']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_underwriting_popup_credit_type;

		// Vehicle comm.
		@FindBy(xpath = "//*[normalize-space()='Vehicle comm.']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_vehicle_comm;

		// Default finance comm.
		@FindBy(xpath = "//*[normalize-space()='Default finance comm.']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_default_finance_comm;

		// Maintenance commision
		@FindBy(xpath = "//*[normalize-space()='Maint. comm.']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_maintenance_commision;

		// Document fee comm.
		@FindBy(xpath = "//*[normalize-space()='Document fee comm.']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_document_fee_comm;

		// Total comm.
		@FindBy(xpath = "//*[normalize-space()='Total comm.']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_quote_summary_total_comm;
		

		
		// Monthly finance rental
		@FindBy(xpath = "//*[normalize-space()='Monthly finance rental']//ancestor::div[1]//div//strong|//*[normalize-space()='Monthly finance payment']//ancestor::div[1]//div//strong")
		private WebElement underwriting_popup_customer_underwriting_popup_monthly_finance_rental;



	public UnderwritingPopupPage() {

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

	/*
	 * public void underwriting_menu_link_availbale () throws InterruptedException {
	 * 
	 * ExplicitWait.visibleElement(driver, underwriting_menu_link, 30);
	 * 
	 * 
	 * 
	 * underwriting_menu_link.click();
	 * System.out.println("Click on  underwriting icon is available");
	 * LO.print("Click on  underwriting icon is available ");
	 * 
	 * ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

	public void search_and_verify_underwriting_icon_is_availabale() throws InterruptedException {

		ExplicitWait.visibleElement(driver, underwriting_icon_link, 30);

		if (underwriting_icon_link.isEnabled()) {

			underwriting_icon_link.click();
		}
		System.out.println("Underwriting icon is available");
		LO.print(" Underwriting icon is available ");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

	}

	public void search_and_verify_underwriting_icon_is_close() throws InterruptedException {

		ExplicitWait.visibleElement(driver, underwriting_popup_close_icon, 30);

		underwriting_popup_close_icon.click();

		System.out.println("Underwriting pop up will close");
		LO.print(" Underwriting pop up will close");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

	}

	public void search_and_verify_underwriting_pop_up_close_button() throws InterruptedException {

		ExplicitWait.visibleElement(driver, underwriting_popup_close_button, 30);

		underwriting_popup_close_button.click();

		System.out.println("Underwriting pop up will close");
		LO.print(" Underwriting pop up will close");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

	}

	// 3.underwriting_popup_proposal_id


	public boolean verify_underwriting_pop_up_summary_values_for_broker_hire_flow()
			throws InterruptedException, IOException, ClassNotFoundException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		
		//underwriting pop ........

		ExplicitWait.visibleElement(driver, quote_summary_vehicle_heading, 120);
		
		
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_contract_type, 60);
	
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_button, 120);

		// Cliking on cust quote summary section
		Click.on(driver, underwriting_popup_customer_quote_summary_button, 30);

		// waiting for summary section elements
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_terms, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_underwriting_popup_miles_per_annum, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_initial_finance_rental, 120);		
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_funder_name, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_quote_ref_number, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_quote_exp_date, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_payment_profile, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_contract_mileage, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_initial_finance_rental, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_pence_per_excess_mile_finance, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_commission, 120);

		// Vehicle details
		String vehicleNameActual = quote_summary_vehicle_heading.getText().trim();

		ExplicitWait.visibleElement(driver, underwriting_popup_quote_ref_no, 30);

		String quotRefNoActual = underwriting_popup_quote_ref_no.getText();
		
		
		// customer quote section
		// getting text from elements

		String contractTypeActual = underwriting_popup_customer_contract_type.getText();
		
		double termActual = Double.parseDouble(underwriting_popup_customer_quote_summary_terms.getText().trim().substring(0,2));

		double mileageActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_underwriting_popup_miles_per_annum.getText().trim()));

		double monthlyFinanceRentalActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));

		String funderNameActual = underwriting_popup_customer_quote_summary_funder_name.getText().trim();

		String funderQuoteRefNumberActual = underwriting_popup_customer_quote_summary_quote_ref_number.getText().trim();

		String expiryDateActual = underwriting_popup_customer_quote_summary_quote_exp_date.getText().trim();

		String paymentProfileActual = underwriting_popup_customer_quote_summary_payment_profile.getText().trim();
		
		double contractMileageActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_contract_mileage.getText().trim()));

		double initialFinanceRentalActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_initial_finance_rental.getText().trim().substring(2)));

		String pencePerExcessMileFinanceActual = underwriting_popup_customer_quote_summary_pence_per_excess_mile_finance.getText().trim();

		double commissionActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_commission.getText().trim().substring(2)));
		


		System.out.println("Underwriting pop up - quote ref no  =>" + quotRefNoActual);
		LO.print          ("Underwriting pop up -quote ref no => " + quotRefNoActual);
		
		
		ExplicitWait.visibleElement(driver, underwriting_popup_proposal_id, 30);
		String UnderwritingPopupProposalId = underwriting_popup_proposal_id.getText();
		Thread.sleep(2000);
		String UnderwritingPopupProposalIdFromScreen = UnderwritingPopupProposalId.substring(14, 21);
		Thread.sleep(2000);
		System.out.println("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);
		LO.print("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);

		String sheetName = "";
		
		if (Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName().contains("broker_business_hire")) {
			sheetName = prop.getProperty("BrokerBCHQuoteNo");
		}

		if (Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName().contains("broker_individual_hire")) {
			sheetName = prop.getProperty("BrokerPCHQuoteNo");
		}

		Thread.sleep(1000);
		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		XSSFWorkbook wb = new XSSFWorkbook(in);
		Thread.sleep(1000);

		wb.getSheet(sheetName).getRow(1).getCell(3).setCellValue(UnderwritingPopupProposalIdFromScreen);

		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		wb.write(out);
		
		
		
		String quotRefNoExpected            = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
		String vehicleNameExpected          = GetExcelFormulaValue.get_cell_value(1, 10, sheetName);
		
		String contractTypeExpected         = GetExcelFormulaValue.get_cell_value(4, 1, sheetName);
		double termExpected                 = Double.parseDouble(GetExcelFormulaValue.get_cell_value(4, 3, sheetName));
		
		double mileageExpected              = Double.parseDouble(GetExcelFormulaValue.get_cell_value(6, 1, sheetName));
		double monthlyFinanceRentalExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(6, 3, sheetName));
		
//		GetExcelFormulaValue.get_string_value(8, 1, sheetName);
//		GetExcelFormulaValue.get_string_value(8, 3, sheetName);
		
		String funderNameExpected                = GetExcelFormulaValue.get_cell_value(10, 1, sheetName);
		String funderQuoteRefNumberExpected      = GetExcelFormulaValue.get_cell_value(10, 3, sheetName);
		
		String expiryDateExpected                = GetExcelFormulaValue.get_cell_value(12, 1, sheetName);
		String paymentProfileExpected            = GetExcelFormulaValue.get_cell_value(12, 3, sheetName);
		
		double contractMileageExpected           = Double.parseDouble(GetExcelFormulaValue.get_cell_value(14, 1, sheetName));
		double initialFinanceRentalExpected      = Double.parseDouble(GetExcelFormulaValue.get_cell_value(14, 3, sheetName));
	
//		GetExcelFormulaValue.get_string_value(16, 1, sheetName);
//		GetExcelFormulaValue.get_string_value(16, 3, sheetName);
	
		String pencePerExcessMileFinanceExpected = GetExcelFormulaValue.get_cell_value(18, 1, sheetName);
//		GetExcelFormulaValue.get_string_value(18, 3, sheetName);
	
//		GetExcelFormulaValue.get_string_value(20, 1, sheetName);
		double commissionExpected                = Double.parseDouble(GetExcelFormulaValue.get_cell_value(20, 3, sheetName));
		
		//********************************
		
		System.out.println("");
	    LO.print          ("");
		System.out.println("Started Verifying Summary values");
	    LO.print          ("Started Verifying Summary values");

	    //*******************************
	    
		int count =0;
		
		// comparing quote no.
		if (quotRefNoActual.equals(quotRefNoExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(quotRefNoActual + " = " + quotRefNoExpected);
			LO.print          (quotRefNoActual + " = " + quotRefNoExpected);
			System.out.println("Quote no. compared and found ok");
			LO.print          ("Quote no. compared and found ok");
		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(quotRefNoActual + " != " + quotRefNoExpected);
			LO.print          (quotRefNoActual + " != " + quotRefNoExpected);
			System.err.println("Quote no. compared but found not ok");
			LO.print          ("Quote no. compared but found not ok");
		}

		// comparing vehicle name
		if (vehicleNameActual.equals(vehicleNameExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(vehicleNameActual + " = " + vehicleNameExpected);
			LO.print          (vehicleNameActual + " = " + vehicleNameExpected);
			System.out.println("Vehicle name compared and found ok");
			LO.print          ("Vehicle name compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(vehicleNameActual + " != " + vehicleNameExpected);
			LO.print          (vehicleNameActual + " != " + vehicleNameExpected);
			System.err.println("Vehicle name compared but found not ok");
			LO.print          ("Vehicle name compared but found not ok");

		}

		// comparing contract type
		if (contractTypeActual.equals(contractTypeExpected)) {
			count++;
			System.out.println("");
			LO.print          ("");
			System.out.println(contractTypeActual + " = " + contractTypeExpected);
			LO.print          (contractTypeActual + " = " + contractTypeExpected);
			System.out.println("Contract type compared and found ok");
			LO.print          ("Contract type compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(contractTypeActual + " != " + contractTypeExpected);
			LO.print          (contractTypeActual + " != " + contractTypeExpected);
			System.err.println("Contract type compared but found not ok");
			LO.print          ("Contract type compared but found not ok");
		}

		// comparing monthly finance payment
		if (monthlyFinanceRentalActual == monthlyFinanceRentalExpected) {
			
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(monthlyFinanceRentalActual + " = " + monthlyFinanceRentalExpected);
			LO.print          (monthlyFinanceRentalActual + " = " + monthlyFinanceRentalExpected);
			System.out.println("Monthly finance rental compared and found ok");
			LO.print          ("Monthly finance rental compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(monthlyFinanceRentalActual + " != " + monthlyFinanceRentalExpected);
			LO.print          (monthlyFinanceRentalActual + " != " + monthlyFinanceRentalExpected);
			System.err.println("Monthly finance rental compared but found not ok");
			LO.print          ("Monthly finance rental compared but found not ok");

		}

		// comparing mileage
		if (mileageActual == mileageExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(mileageActual + " = " + mileageExpected);
			LO.print          (mileageActual + " = " + mileageExpected);
			System.out.println("Mileage compared and found ok");
			LO.print          ("Mileage compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(mileageActual + " != " + mileageExpected);
			LO.print          (mileageActual + " != " + mileageExpected);
			System.err.println("Mileage compared but found not ok");
			LO.print          ("Mileage compared but found not ok");

		}

		// comparing term
		if (termActual == termExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(termActual + " = " + termExpected);
			LO.print          (termActual + " = " + termExpected);
			System.out.println("Terms compared and found ok");
			LO.print          ("Terms compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(termActual + " != " + termExpected);
			LO.print          (termActual + " != " + termExpected);
			System.err.println("Terms compared but found not ok");
			LO.print          ("Terms compared but found not ok");

		}

		// comparing expiry date
		if (expiryDateActual.equals(expiryDateExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(expiryDateActual + " = " + expiryDateExpected);
			LO.print          (expiryDateActual + " = " + expiryDateExpected);
			System.out.println("Expiry date compared and found ok");
			LO.print          ("Expiry date compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(expiryDateActual + " != " + expiryDateExpected);
			LO.print          (expiryDateActual + " != " + expiryDateExpected);
			System.err.println("Expiry date compared but found not ok");
			LO.print          ("Expiry date compared but found not ok");

		}

		// comparing funder name
		if (funderNameActual.equals(funderNameExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(funderNameActual + " = " + funderNameExpected);
			LO.print          (funderNameActual + " = " + funderNameExpected);
			System.out.println("Funder name compared and found ok");
			LO.print          ("Funder name compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(funderNameActual + " != " + funderNameExpected);
			LO.print          (funderNameActual + " != " + funderNameExpected);
			System.err.println("Funder name compared but found not ok");
			LO.print          ("Funder name compared but found not ok");

		}

		// comparing funder quote ref no.
		if (funderQuoteRefNumberActual.equals(funderQuoteRefNumberExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(funderQuoteRefNumberActual + " = " + funderQuoteRefNumberExpected);
			LO.print          (funderQuoteRefNumberActual + " = " + funderQuoteRefNumberExpected);
			System.out.println("Funder quote ref no. compared and found ok");
			LO.print          ("Funder quote ref no. compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(funderQuoteRefNumberActual + " != " + funderQuoteRefNumberExpected);
			LO.print          (funderQuoteRefNumberActual + " != " + funderQuoteRefNumberExpected);
			System.err.println("Funder quote ref no. compared but found not ok");
			LO.print          ("Funder quote ref no. compared but found not ok");

		}

		// comparing payment profile
		if (paymentProfileActual.equals(paymentProfileExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(paymentProfileActual + " = " + paymentProfileExpected);
			LO.print          (paymentProfileActual + " = " + paymentProfileExpected);
			System.out.println("Payment profile compared and found ok");
			LO.print          ("Payment profile compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(paymentProfileActual + " != " + paymentProfileExpected);
			LO.print          (paymentProfileActual + " != " + paymentProfileExpected);
			System.err.println("Payment profile compared but found not ok");
			LO.print          ("Payment profile compared but found not ok");

		}

		// comparing contract mileage
		if (contractMileageActual == contractMileageExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(contractMileageActual + " = " + contractMileageExpected);
			LO.print          (contractMileageActual + " = " + contractMileageExpected);
			System.out.println("Contract mileage compared and found ok");
			LO.print          ("Contract mileage compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(contractMileageActual + " != " + contractMileageExpected);
			LO.print          (contractMileageActual + " != " + contractMileageExpected);
			System.err.println("Contract mileage compared but found not ok");
			LO.print          ("Contract mileage compared but found not ok");

		}

		// comparing Initial finance rental
		if (initialFinanceRentalActual == initialFinanceRentalExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(initialFinanceRentalActual + " = " + initialFinanceRentalExpected);
			LO.print          (initialFinanceRentalActual + " = " + initialFinanceRentalExpected);
			System.out.println("Initial finance rental compared and found ok");
			LO.print          ("Initial finance rental compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(initialFinanceRentalActual + " != " + initialFinanceRentalExpected);
			LO.print          (initialFinanceRentalActual + " != " + initialFinanceRentalExpected);
			System.err.println("Initial finance rental compared but found not ok");
			LO.print          ("Initial finance rental compared but found not ok");

		}

		// comparing pense per excess mile finance
		if (pencePerExcessMileFinanceActual.equals(pencePerExcessMileFinanceExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(pencePerExcessMileFinanceActual + " = " + pencePerExcessMileFinanceExpected);
			LO.print          (pencePerExcessMileFinanceActual + " = " + pencePerExcessMileFinanceExpected);
			System.out.println("Pense per excess mile finance compared and found ok");
			LO.print          ("Pense per excess mile finance compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(pencePerExcessMileFinanceActual + " != " + pencePerExcessMileFinanceExpected);
			LO.print          (pencePerExcessMileFinanceActual + " != " + pencePerExcessMileFinanceExpected);
			System.err.println("Pense per excess mile finance compared but found not ok");
			LO.print          ("Pense per excess mile finance compared but found not ok");

		}

		// comparing commission
		if (commissionActual == commissionExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(commissionActual + " = " + commissionExpected);
			LO.print          (commissionActual + " = " + commissionExpected);
			System.out.println("commission compared and found ok");
			LO.print          ("commission compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(commissionActual + " != " + commissionExpected);
			LO.print          (commissionActual + " != " + commissionExpected);
			System.err.println("commission compared but found not ok");
			LO.print          ("commission compared but found not ok");

		}
								
		
		boolean status = false;
		if (count==14)
			
		{
			status = true;
		}
		
		return status;
		
		
		

	}

	
	public boolean verify_underwriting_pop_up_summary_values_for_ownbook_hire_flow()
			throws InterruptedException, IOException, ClassNotFoundException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		
		//underwriting pop ........

		ExplicitWait.visibleElement(driver, quote_summary_vehicle_heading, 120);
		
		
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_contract_type, 60);
	
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_button, 120);

		// Cliking on cust quote summary section
		Click.on(driver, underwriting_popup_customer_quote_summary_button, 30);

		// waiting for summary section elements

		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_term, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_miles, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_monthly_finance_rental, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_initial_finance_rental, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_followed_by, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_pence_per_excess_mile_finance, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_doc_fee, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_upsell, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_default_finance_commission, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_upsell_commission, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_doc_fee_commission, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_total_commission, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_referrer_commission, 20);

		// Vehicle details
		String vehicleNameActual = quote_summary_vehicle_heading.getText().trim();

		ExplicitWait.visibleElement(driver, underwriting_popup_quote_ref_no, 30);

		String quotRefNoActual = underwriting_popup_quote_ref_no.getText();
		
		
		// customer quote section
		// getting text from elements

		String contractTypeActual = underwriting_popup_customer_contract_type.getText();
		
		double customer_quote_summary_terms = Double
				.parseDouble(underwriting_popup_customer_quote_term.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double
				.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_miles.getText().trim()));

		double customer_quote_summary_monthly_finance_rental = Double
				.parseDouble(RemoveComma.of(underwriting_popup_monthly_finance_rental.getText().trim().substring(2)));

		double customer_quote_initial_finance_rental = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_initial_finance_rental.getText().trim().substring(2)));


		double customer_payment_followed_by = Double
				.parseDouble(underwriting_popup_customer_quote_followed_by.getText().substring(0, 2));

		double customer_quote_pence_per_excess_mile_finance = Double.parseDouble(
				underwriting_popup_customer_quote_pence_per_excess_mile_finance.getText().trim().substring(0, 4));

		double customer_quote_summary_doc_fee = Double
				.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_doc_fee.getText().trim().substring(2)));

		double customer_quote_summary_upsell = Double
				.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_upsell.getText().trim().substring(2)));

		double customer_quote_summary_default_finance_commission = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_default_finance_commission.getText().trim().substring(2)));

		double customer_quote_summary_upsell_commission = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_upsell_commission.getText().trim().substring(2)));

		double customer_quote_summary_doc_fee_commission = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_doc_fee_commission.getText().trim().substring(2)));

		double customer_quote_summary_total_commision = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_total_commission.getText().trim().substring(2)));

		double customer_quote_summary_referrer_commision = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_referrer_commission.getText().trim().substring(2)));



        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();

		obj_acq_listing_page = new AcquisitionListingPage();
		
		String sheetName = obj_acq_listing_page.calculation_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		
		
		double terms = GetExcelFormulaValue.get_formula_value(173, 1, sheetName);
		double miles = GetExcelFormulaValue.get_formula_value(173, 3, sheetName);
		double monthlyFinanceRental = GetExcelFormulaValue.get_formula_value(176, 0, sheetName);
		double initialFinanceRental = GetExcelFormulaValue.get_formula_value(179, 1, sheetName);
		double followedBy = GetExcelFormulaValue.get_formula_value(182, 3, sheetName);
		double pencePerExcessMileFinance = GetExcelFormulaValue.get_formula_value(188, 0, sheetName);
		double documentFee = GetExcelFormulaValue.get_formula_value(191, 1, sheetName);
		double upsell = GetExcelFormulaValue.get_formula_value(191, 3, sheetName);
		double defaultFinanceCommission = GetExcelFormulaValue.get_formula_value(196, 0, sheetName);
		double upsellCommission = GetExcelFormulaValue.get_formula_value(196, 1, sheetName);
		double docFeeCommission = GetExcelFormulaValue.get_formula_value(199, 0, sheetName);
		double totalCommission = GetExcelFormulaValue.get_formula_value(199, 3, sheetName);
		double referrerCommission = GetExcelFormulaValue.get_formula_value(202, 0, sheetName);
		


		System.out.println("Underwriting pop up - quote ref no  =>" + quotRefNoActual);
		LO.print          ("Underwriting pop up -quote ref no => " + quotRefNoActual);
		
		
		ExplicitWait.visibleElement(driver, underwriting_popup_proposal_id, 30);
		String UnderwritingPopupProposalId = underwriting_popup_proposal_id.getText();
		Thread.sleep(2000);
		String UnderwritingPopupProposalIdFromScreen = UnderwritingPopupProposalId.substring(14, 21);
		Thread.sleep(2000);
		System.out.println("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);
		LO.print("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);


	    sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		

		Thread.sleep(1000);
		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		XSSFWorkbook wb = new XSSFWorkbook(in);
		Thread.sleep(1000);

		wb.getSheet(sheetName).getRow(1).getCell(3).setCellValue(UnderwritingPopupProposalIdFromScreen);

		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		wb.write(out);
		
		
		String quotRefNoExpected                             = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
		String vehicleNameExpected                           = GetExcelFormulaValue.get_cell_value(1, 10, sheetName);
		
		String contractTypeExpected                           = GetExcelFormulaValue.get_cell_value(4, 1, sheetName);		
		
		
		//********************************
		
		System.out.println("");
	    LO.print          ("");
		System.out.println("Started Verifying Summary values");
	    LO.print          ("Started Verifying Summary values");

	    //*******************************
	    
		int count =0;
		
		// comparing quote no.
		if (quotRefNoActual.equals(quotRefNoExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(quotRefNoActual + " = " + quotRefNoExpected);
			LO.print          (quotRefNoActual + " = " + quotRefNoExpected);
			System.out.println("Quote no. compared and found ok");
			LO.print          ("Quote no. compared and found ok");
		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(quotRefNoActual + " != " + quotRefNoExpected);
			LO.print          (quotRefNoActual + " != " + quotRefNoExpected);
			System.err.println("Quote no. compared but found not ok");
			LO.print          ("Quote no. compared but found not ok");
		}

		// comparing vehicle name
		if (vehicleNameActual.equals(vehicleNameExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(vehicleNameActual + " = " + vehicleNameExpected);
			LO.print          (vehicleNameActual + " = " + vehicleNameExpected);
			System.out.println("Vehicle name compared and found ok");
			LO.print          ("Vehicle name compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(vehicleNameActual + " != " + vehicleNameExpected);
			LO.print          (vehicleNameActual + " != " + vehicleNameExpected);
			System.err.println("Vehicle name compared but found not ok");
			LO.print          ("Vehicle name compared but found not ok");

		}

		// comparing contract type
		if (contractTypeActual.equals(contractTypeExpected)) {
			count++;
			System.out.println("");
			LO.print          ("");
			System.out.println(contractTypeActual + " = " + contractTypeExpected);
			LO.print          (contractTypeActual + " = " + contractTypeExpected);
			System.out.println("Contract type compared and found ok");
			LO.print          ("Contract type compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(contractTypeActual + " != " + contractTypeExpected);
			LO.print          (contractTypeActual + " != " + contractTypeExpected);
			System.err.println("Contract type compared but found not ok");
			LO.print          ("Contract type compared but found not ok");
		}

		
		// comparing term
		if (customer_quote_summary_terms == terms) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_terms + " = " + terms);
			LO.print          (customer_quote_summary_terms + " = " + terms);
			System.out.println("Terms compared and found ok");
			LO.print          ("Terms compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_terms + " != " + terms);
			LO.print          (customer_quote_summary_terms + " != " + terms);
			System.err.println("Terms compared but found not ok");
			LO.print          ("Terms compared but found not ok");

		}

		
		// comparing mileage
		if (customer_quote_summary_miles == miles) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_miles + " = " + miles);
			LO.print          (customer_quote_summary_miles + " = " + miles);
			System.out.println("Mileage compared and found ok");
			LO.print          ("Mileage compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_miles + " != " + miles);
			LO.print          (customer_quote_summary_miles + " != " + miles);
			System.err.println("Mileage compared but found not ok");
			LO.print          ("Mileage compared but found not ok");

		}	
		
		// comparing monthly finance rental
		
		if ((Difference.of_two_Double_Values(monthlyFinanceRental,
				customer_quote_summary_monthly_finance_rental)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_monthly_finance_rental + " = " + monthlyFinanceRental);
			LO.print          (customer_quote_summary_monthly_finance_rental + " = " + monthlyFinanceRental);
			System.out.println("Monthly Finance Rental compared and found ok");
			LO.print          ("Monthly Finance Rental compared and found ok");
		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_monthly_finance_rental + " != " + monthlyFinanceRental);
			LO.print          (customer_quote_summary_monthly_finance_rental + " != " + monthlyFinanceRental);
			
			System.err.println("Monthly Finance Rental found wrong");
			LO.print          ("Monthly Finance Rental found wrong");
		}

		
		//Comparing Initial Finance rental
		
		if ((Difference.of_two_Double_Values(initialFinanceRental, customer_quote_initial_finance_rental)) < 0.2) {
			
			count++;
			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_initial_finance_rental + " = " + initialFinanceRental);
			LO.print          (customer_quote_initial_finance_rental + " = " + initialFinanceRental);
			
			LO.print("Initial Finance Rental found OK");
			System.out.println("Initial Finance Rental found OK");
			
		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_initial_finance_rental + " != " + initialFinanceRental);
			LO.print          (customer_quote_initial_finance_rental + " != " + initialFinanceRental);

			LO.print("Initial Finance Rental found wrong");
			System.err.println("Initial Finance Rental found wrong");
		}
		
		
		//Comparing followed By
		
		if (followedBy == customer_payment_followed_by) {
			
			count++;
			System.out.println("");
			LO.print          ("");
			System.out.println(customer_payment_followed_by + " = " + followedBy);
			LO.print          (customer_payment_followed_by + " = " + followedBy);
			
			
			LO.print("Followed By months - found OK");
			System.out.println("Followed By months - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_payment_followed_by + " != " + followedBy);
			LO.print          (customer_payment_followed_by + " != " + followedBy);
		
			LO.print          ("Followed By months - found wrong");
			System.err.println("Followed By months - found wrong");
		}

		
		//Comparing Pence per excess mile finance
		
		
		if ((Difference.of_two_Double_Values(pencePerExcessMileFinance,
				customer_quote_pence_per_excess_mile_finance)) < 0.2) {
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_pence_per_excess_mile_finance + " = " + pencePerExcessMileFinance);
			LO.print          (customer_quote_pence_per_excess_mile_finance + " = " + pencePerExcessMileFinance);
			
			LO.print          ("Pence per excess mile finance - found OK");
			System.out.println("Pence per excess mile finance - found OK");
		
	
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_pence_per_excess_mile_finance + " != " + pencePerExcessMileFinance);
			LO.print          (customer_quote_pence_per_excess_mile_finance + " != " + pencePerExcessMileFinance);
	
			LO.print          ("Pence per excess mile finance - found wrong");
			System.err.println("Pence per excess mile finance - found wrong");
		}
		
		
		// Comparing Document Fee

		if ((Difference.of_two_Double_Values(documentFee, customer_quote_summary_doc_fee)) < 0.2) {
			count++;
	
			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_doc_fee + " = " + documentFee);
			LO.print          (customer_quote_summary_doc_fee + " = " + documentFee);
	
			
			LO.print          ("Document Fee - found OK");
			System.out.println("Document Fee - found OK");
			
		} else {

			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_doc_fee + " != " + documentFee);
			LO.print          (customer_quote_summary_doc_fee + " != " + documentFee);

			
			LO.print          ("Document Fee - found wrong");
			System.err.println("Document Fee - found wrong");
		}
		
		//Comparing Upsell

		if (Difference.of_two_Double_Values(upsell, customer_quote_summary_upsell) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_upsell + " = " + upsell);
			LO.print          (customer_quote_summary_upsell + " = " + upsell);
				
			
			LO.print          ("Upsell - found OK");
			System.out.println("Upsell - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_upsell + " != " + upsell);
			LO.print          (customer_quote_summary_upsell + " != " + upsell);			
			
			LO.print          ("Upsell - found wrong");
			System.err.println("Upsell - found wrong");
		}

		
		//Comparing Default Finance Commission
		
		if ((Difference.of_two_Double_Values(defaultFinanceCommission,
				customer_quote_summary_default_finance_commission)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_default_finance_commission + " = " + defaultFinanceCommission);
			LO.print          (customer_quote_summary_default_finance_commission + " = " + defaultFinanceCommission);
			
			LO.print          ("Default Finance Commission - found OK");
			System.out.println("Default Finance Commission - found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_default_finance_commission + " != " + defaultFinanceCommission);
			LO.print          (customer_quote_summary_default_finance_commission + " != " + defaultFinanceCommission);			

			LO.print          ("Default Finance Commission - found wrong");
			System.err.println("Default Finance Commission - found wrong");
		}
		
		
		//Comparing Upsell Commission

		if (Difference.of_two_Double_Values(upsellCommission, customer_quote_summary_upsell_commission) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_upsell_commission + " = " + upsellCommission);
			LO.print          (customer_quote_summary_upsell_commission + " = " + upsellCommission);
					
					
			LO.print          ("Upsell Commission - found OK");
			System.out.println("Upsell Commission - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_upsell_commission + " != " + upsellCommission);
			LO.print          (customer_quote_summary_upsell_commission + " != " + upsellCommission);			
			
			LO.print          ("Upsell Commission - found wrong");
			System.err.println("Upsell Commission - found wrong");
		}
		
		
		//Comparing Document Fee Commission
		
		
		if ((Difference.of_two_Double_Values(docFeeCommission, customer_quote_summary_doc_fee_commission)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_doc_fee_commission + " = " + docFeeCommission);
			LO.print          (customer_quote_summary_doc_fee_commission + " = " + docFeeCommission);
		
			LO.print          ("Document Fee Commission - found OK");
			System.out.println("Document Fee Commission - found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_doc_fee_commission + " != " + docFeeCommission);
			LO.print          (customer_quote_summary_doc_fee_commission + " != " + docFeeCommission);			
			
			
			LO.print          ("Document Fee Commission - found wrong");
			System.err.println("Document Fee Commission - found wrong");
		}
		
		
		//Comparing Total Commission	

		if ((Difference.of_two_Double_Values(totalCommission, customer_quote_summary_total_commision)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_total_commision + " = " + totalCommission);
			LO.print          (customer_quote_summary_total_commision + " = " + totalCommission);

			
			LO.print          ("Total Commission - found OK");
			System.out.println("Total Commission - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_total_commision + " != " + totalCommission);
			LO.print          (customer_quote_summary_total_commision + " != " + totalCommission);			
	
			
			LO.print          ("Total Commission - found wrong");
			System.err.println("Total Commission - found wrong");
		}
		
		//Comparing Referrer Commission	

		if ((Difference.of_two_Double_Values(referrerCommission, customer_quote_summary_referrer_commision)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_referrer_commision + " = " + referrerCommission);
			LO.print          (customer_quote_summary_referrer_commision + " = " + referrerCommission);
			
			
			LO.print          ("Referrer Commission - found OK");
			System.out.println("Referrer Commission - found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_referrer_commision + " != " + referrerCommission);
			LO.print          (customer_quote_summary_referrer_commision + " != " + referrerCommission);			
				
			
			LO.print          ("Referrer Commission - found wrong");
			System.err.println("Referrer Commission - found wrong");
		}
		
		boolean status = false;
		if (count==16)
			
		{
			status = true;
		}
		
		return status;
		
		
		

	}

	
	public boolean verify_underwriting_pop_up_summary_values_for_ownbook_business_purchase_flow()
			throws InterruptedException, IOException, ClassNotFoundException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		
		//underwriting pop ........

		ExplicitWait.visibleElement(driver, quote_summary_vehicle_heading, 120);
		
		
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_contract_type, 60);
	
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_button, 120);

		// Cliking on cust quote summary section
		Click.on(driver, underwriting_popup_customer_quote_summary_button, 30);

		// waiting for summary section elements

		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_terms, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_miles_per_annum, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_basic_cash_price, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_vat, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_non_vat_items, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_total_cash_price, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_order_deposit, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_finance_deposit, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_total_deposit, 20);
		try{ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_part_exchange_value, 20);}catch(Exception e) {}
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_balance_to_finance, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_finance_charges, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_document_fee, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_balance_payable, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_option_to_purchase_fee, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_initial_cash_payment, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_followed_by, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_monthly_finance_payment, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_balloon, 20);
		ExplicitWait.visibleElement(driver,	underwriting_popup_customer_quote_summary_final_payment_inc_option_to_purchase_fee, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_vehicle_comm, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_default_finance_comm, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_document_fee_comm, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_total_comm, 20);
		// Vehicle details
		String vehicleNameActual = quote_summary_vehicle_heading.getText().trim();

		ExplicitWait.visibleElement(driver, underwriting_popup_quote_ref_no, 30);

		String quotRefNoActual = underwriting_popup_quote_ref_no.getText();
		
		
		// customer quote section
		// getting text from elements

		String contractTypeActual = underwriting_popup_customer_contract_type.getText();
		
		double customer_quote_summary_terms = Double
				.parseDouble(underwriting_popup_customer_quote_summary_terms.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double
				.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_miles_per_annum.getText().trim()));

		double customer_quote_summary_basic_cash_price = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_vat = Double
				.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_vat.getText().trim().substring(2)));

		double customer_quote_summary_non_vat_items = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_non_vat_items.getText().trim().substring(2)));

		double customer_quote_summary_total_cash_price = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_total_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_order_deposit = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_order_deposit.getText().trim().substring(2)));

		double customer_quote_summary_finance_deposit = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_finance_deposit.getText().trim().substring(2)));

		double customer_quote_summary_total_deposit = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_total_deposit.getText().trim().substring(2)));
		
		double  customer_quote_summary_part_exchange_value =0;
		try{customer_quote_summary_part_exchange_value = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_part_exchange_value.getText().trim().substring(2)));}catch(Exception e) {}
                

		double customer_quote_summary_balance_to_finance = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));

		double customer_quote_summary_finance_charges = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_finance_charges.getText().trim().substring(2)));

		double customer_quote_summary_document_fee = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_document_fee.getText().trim().substring(2)));

		double customer_quote_summary_balance_payable = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_balance_payable.getText().trim().substring(2)));

		double customer_quote_summary_option_to_purchase_fee = Double.parseDouble(RemoveComma
				.of(underwriting_popup_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));

		double customer_quote_summary_initial_cash_payment = Double.parseDouble(RemoveComma
				.of(underwriting_popup_customer_quote_summary_initial_cash_payment.getText().trim().substring(2)));

		double customer_payment_followed_by = Double
				.parseDouble(underwriting_popup_customer_quote_summary_followed_by.getText().substring(0, 2));

		double customer_quote_summary_monthly_finance_payment = Double.parseDouble(RemoveComma
				.of(underwriting_popup_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));

		double customer_quote_summary_balloon = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_balloon.getText().trim().substring(2)));

		double customer_quote_summary_final_payment_inc_option_to_purchase_fee = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_final_payment_inc_option_to_purchase_fee.getText()
						.trim().substring(2)));

		double customer_quote_summary_vehicle_comm = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_vehicle_comm.getText().trim().substring(2)));

		double customer_quote_summary_default_finance_comm = Double.parseDouble(RemoveComma
				.of(underwriting_popup_customer_quote_summary_default_finance_comm.getText().trim().substring(2)));

		double customer_quote_summary_document_fee_comm = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_document_fee_comm.getText().trim().substring(2)));

		double customer_quote_summary_total_commission = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_total_comm.getText().trim().substring(2)));



        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();

		obj_acq_listing_page = new AcquisitionListingPage();
		
		String sheetName = obj_acq_listing_page.calculation_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		
		
		// getting values from excel
		
		double terms =0;
		try {
		 terms = GetExcelFormulaValue.get_formula_value(208, 1, sheetName);
        }catch(Exception e)
        {
   		 terms = GetExcelFormulaValue.get_string_value(208, 1, sheetName);	
        }
		
		double miles =0;
		try {
			 miles = GetExcelFormulaValue.get_formula_value(208, 4, sheetName);
        }catch(Exception e)
        {
        	 miles = GetExcelFormulaValue.get_string_value(208, 4, sheetName);
        }

		double basicCashPrice = GetExcelFormulaValue.get_formula_value(214, 0, sheetName);
		double vat = GetExcelFormulaValue.get_formula_value(214, 1, sheetName);
		double nonVATItems = GetExcelFormulaValue.get_formula_value(214, 4, sheetName);

		double totalCashPrice = GetExcelFormulaValue.get_formula_value(217, 0, sheetName);
		double orderDeposit = GetExcelFormulaValue.get_formula_value(217, 1, sheetName);
		double financeDeposit = GetExcelFormulaValue.get_formula_value(217, 4, sheetName);

		double totalDeposit = GetExcelFormulaValue.get_formula_value(220, 0, sheetName);
		double partExchangeValue = GetExcelFormulaValue.get_formula_value(220, 1, sheetName);
		double balanceToFinance = GetExcelFormulaValue.get_formula_value(220, 4, sheetName);

		double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheetName);
		double documentFee = GetExcelFormulaValue.get_string_value(223, 1, sheetName);
		double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheetName);

		double optionToPurchaseFee = GetExcelFormulaValue.get_formula_value(226, 0, sheetName);
		double initialCashPayment = GetExcelFormulaValue.get_formula_value(226, 1, sheetName);
		double followedBy = GetExcelFormulaValue.get_formula_value(226, 4, sheetName);

		double monthlyFinancePayment = GetExcelFormulaValue.get_formula_value(229, 0, sheetName);

		double balloon = GetExcelFormulaValue.get_formula_value(232, 0, sheetName);
		double finalPayment = GetExcelFormulaValue.get_formula_value(232, 1, sheetName);

		double vehicleCommission = GetExcelFormulaValue.get_formula_value(239, 0, sheetName);
		double defaultFinanceCommission = GetExcelFormulaValue.get_formula_value(239, 1, sheetName);

		double docFeeCommission = GetExcelFormulaValue.get_formula_value(242, 0, sheetName);
		double totalCommission = GetExcelFormulaValue.get_formula_value(242, 1, sheetName);
	


		System.out.println("Underwriting pop up - quote ref no  =>" + quotRefNoActual);
		LO.print          ("Underwriting pop up -quote ref no => " + quotRefNoActual);
		
		
		ExplicitWait.visibleElement(driver, underwriting_popup_proposal_id, 30);
		String UnderwritingPopupProposalId = underwriting_popup_proposal_id.getText();
		Thread.sleep(2000);
		String UnderwritingPopupProposalIdFromScreen = UnderwritingPopupProposalId.substring(14, 21);
		Thread.sleep(2000);
		System.out.println("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);
		LO.print("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);


	    sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		

		Thread.sleep(1000);
		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		XSSFWorkbook wb = new XSSFWorkbook(in);
		Thread.sleep(1000);

		wb.getSheet(sheetName).getRow(1).getCell(3).setCellValue(UnderwritingPopupProposalIdFromScreen);

		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		wb.write(out);
		
		
		String quotRefNoExpected                             = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
		String vehicleNameExpected                           = GetExcelFormulaValue.get_cell_value(1, 10, sheetName);
		
		String contractTypeExpected                           = GetExcelFormulaValue.get_cell_value(4, 1, sheetName);		
		
		
		//********************************
		
		System.out.println("");
	    LO.print          ("");
		System.out.println("Started Verifying Summary values");
	    LO.print          ("Started Verifying Summary values");

	    //*******************************
	    
		int count =0;
		
		// 1. comparing quote no.
		if (quotRefNoActual.equals(quotRefNoExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(quotRefNoActual + " = " + quotRefNoExpected);
			LO.print          (quotRefNoActual + " = " + quotRefNoExpected);
			System.out.println("Quote no. compared and found ok");
			LO.print          ("Quote no. compared and found ok");
		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(quotRefNoActual + " != " + quotRefNoExpected);
			LO.print          (quotRefNoActual + " != " + quotRefNoExpected);
			System.err.println("Quote no. compared but found not ok");
			LO.print          ("Quote no. compared but found not ok");
		}

		// 2.comparing vehicle name
		if (vehicleNameActual.equals(vehicleNameExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(vehicleNameActual + " = " + vehicleNameExpected);
			LO.print          (vehicleNameActual + " = " + vehicleNameExpected);
			System.out.println("Vehicle name compared and found ok");
			LO.print          ("Vehicle name compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(vehicleNameActual + " != " + vehicleNameExpected);
			LO.print          (vehicleNameActual + " != " + vehicleNameExpected);
			System.err.println("Vehicle name compared but found not ok");
			LO.print          ("Vehicle name compared but found not ok");

		}

		// 3.comparing contract type
		if (contractTypeActual.equals(contractTypeExpected)) {
			count++;
			System.out.println("");
			LO.print          ("");
			System.out.println(contractTypeActual + " = " + contractTypeExpected);
			LO.print          (contractTypeActual + " = " + contractTypeExpected);
			System.out.println("Contract type compared and found ok");
			LO.print          ("Contract type compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(contractTypeActual + " != " + contractTypeExpected);
			LO.print          (contractTypeActual + " != " + contractTypeExpected);
			System.err.println("Contract type compared but found not ok");
			LO.print          ("Contract type compared but found not ok");
		}

		
		// 4.comparing term
		if (customer_quote_summary_terms == terms) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_terms + " = " + terms);
			LO.print          (customer_quote_summary_terms + " = " + terms);
			System.out.println("Terms compared and found ok");
			LO.print          ("Terms compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_terms + " != " + terms);
			LO.print          (customer_quote_summary_terms + " != " + terms);
			System.err.println("Terms compared but found not ok");
			LO.print          ("Terms compared but found not ok");

		}

		
		// 5.comparing mileage
		if (customer_quote_summary_miles == miles) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_miles + " = " + miles);
			LO.print          (customer_quote_summary_miles + " = " + miles);
			System.out.println("Mileage compared and found ok");
			LO.print          ("Mileage compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_miles + " != " + miles);
			LO.print          (customer_quote_summary_miles + " != " + miles);
			System.err.println("Mileage compared but found not ok");
			LO.print          ("Mileage compared but found not ok");

		}	
		
		// 6.comparing Basic Cash Price
		
		
		if ((Difference.of_two_Double_Values(basicCashPrice, customer_quote_summary_basic_cash_price)) < 0.2) {
			
			count++;
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_basic_cash_price + " = " + basicCashPrice);
			LO.print          (customer_quote_summary_basic_cash_price + " = " + basicCashPrice);		
			
			LO.print          ("Basic Cash Price found OK");
			System.out.println("Basic Cash Price found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_basic_cash_price + " != " + basicCashPrice);
			LO.print          (customer_quote_summary_basic_cash_price + " != " + basicCashPrice);		

			
			LO.print          ("Basic Cash Price found wrong");
			System.err.println("Basic Cash Price found wrong");
		}

		// 7.comparing VAT
		
		if ((Difference.of_two_Double_Values(vat, customer_quote_summary_vat)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_vat + " = " + vat);
			LO.print          (customer_quote_summary_vat + " = " + vat);		

			
			LO.print          ("VAT found OK");
			System.out.println("VAT found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_vat + " != " + vat);
			LO.print          (customer_quote_summary_vat + " != " + vat);		

			LO.print          ("VAT found wrong");
			System.err.println("VAT found wrong");
		}

		// 8.comparing non vat items
		
		if ((Difference.of_two_Double_Values(nonVATItems, customer_quote_summary_non_vat_items)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_non_vat_items + " = " + nonVATItems);
			LO.print          (customer_quote_summary_non_vat_items + " = " + nonVATItems);		

			
			LO.print          ("Non VAT Items Value found OK");
			System.out.println("Non VAT Items Value found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_non_vat_items + " != " + nonVATItems);
			LO.print          (customer_quote_summary_non_vat_items + " != " + nonVATItems);		
	
			
			LO.print          ("Non VAT Items Value found wrong");
			System.err.println("Non VAT Items Value found wrong");
		}

		// 9.comparing Total Cash Price
		
		if ((Difference.of_two_Double_Values(totalCashPrice, customer_quote_summary_total_cash_price)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");

			System.out.println(customer_quote_summary_total_cash_price + " = " + totalCashPrice);
			LO.print          (customer_quote_summary_total_cash_price + " = " + totalCashPrice);		
			
			
			LO.print          ("Total Cash Price found OK");
			System.out.println("Total Cash Price found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_total_cash_price + " != " + totalCashPrice);
			LO.print          (customer_quote_summary_total_cash_price + " != " + totalCashPrice);		

			
			LO.print          ("Total Cash Price found wrong");
			System.err.println("Total Cash Price found wrong");
		}

		// 10.comparing Order Deposit
		if ((Difference.of_two_Double_Values(orderDeposit, customer_quote_summary_order_deposit)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_order_deposit + " = " + orderDeposit);
			LO.print          (customer_quote_summary_order_deposit + " = " + orderDeposit);		

			
			LO.print          ("Order Deposit found OK");
			System.out.println("Order Deposit found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_order_deposit + " != " + orderDeposit);
			LO.print          (customer_quote_summary_order_deposit + " != " + orderDeposit);		

			LO.print          ("Order Deposit found wrong");
			System.err.println("Order Deposit found wrong");
		}

		// 11.comparing Finance Deposit
		if ((Difference.of_two_Double_Values(financeDeposit, customer_quote_summary_finance_deposit)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_finance_deposit + " = " + financeDeposit);
			LO.print          (customer_quote_summary_finance_deposit + " = " + financeDeposit);		
			
			
			LO.print("Finance Deposit found OK");
			System.out.println("Finance Deposit found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_finance_deposit + " != " + financeDeposit);
			LO.print          (customer_quote_summary_finance_deposit + " != " + financeDeposit);			
			
			LO.print("Finance Deposit found wrong");
			System.err.println("Finance Deposit found wrong");
		}

		// 12.comparing Total Deposit
		if ((Difference.of_two_Double_Values(totalDeposit, customer_quote_summary_total_deposit)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_total_deposit + " = " + totalDeposit);
			LO.print          (customer_quote_summary_total_deposit + " = " + totalDeposit);		
			
			
			LO.print          ("Total Deposit found OK");
			System.out.println("Total Deposit found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_total_deposit + " != " + totalDeposit);
			LO.print          (customer_quote_summary_total_deposit + " != " + totalDeposit);			
			
			
			LO.print          ("Total Deposit found wrong");
			System.err.println("Total Deposit found wrong");
		}

		// 13.comparing Part Exchange Value
		
		
		if(Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName().contains("funder")) {
		}
		else {

		if (partExchangeValue == customer_quote_summary_part_exchange_value) {
			
			count++;
		
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_part_exchange_value + " = " + partExchangeValue);
			LO.print          (customer_quote_summary_part_exchange_value + " = " + partExchangeValue);			
			
			LO.print          ("Part Exchange Value - found OK");
			System.out.println("Part Exchange Value - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_part_exchange_value + " != " + partExchangeValue);
			LO.print          (customer_quote_summary_part_exchange_value + " != " + partExchangeValue);			

			
			LO.print          ("Part Exchange Value - found wrong");
			System.err.println("Part Exchange Value - found wrong");
		}
		}

		// 14.comparing Balance to Finance
		if ((Difference.of_two_Double_Values(balanceToFinance, customer_quote_summary_balance_to_finance)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_balance_to_finance + " = " + balanceToFinance);
			LO.print          (customer_quote_summary_balance_to_finance + " = " + balanceToFinance);			
			
			LO.print          ("Balance to Finance found OK");
			System.out.println("Balance to Finance found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_balance_to_finance + " != " + balanceToFinance);
			LO.print          (customer_quote_summary_balance_to_finance + " != " + balanceToFinance);			

			LO.print          ("Balance to Finance found wrong");
			System.err.println("Balance to Finance found wrong");
		}

		// 15.comparing Finance Charges	
		
		if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_finance_charges + " = " + financeCharges);
			LO.print          (customer_quote_summary_finance_charges + " = " + financeCharges);			
			
			
			LO.print          ("Finance Charges - found OK");
			System.out.println("Finance Charges - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");

			System.err.println(customer_quote_summary_finance_charges + " != " + financeCharges);
			LO.print          (customer_quote_summary_finance_charges + " != " + financeCharges);	
			
			LO.print          ("Finance Charges - found wrong");
			System.err.println("Finance Charges - found wrong");
		}

		// 16.comparing Document Fee
		if ((Difference.of_two_Double_Values(documentFee, customer_quote_summary_document_fee)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_document_fee + " = " + documentFee);
			LO.print          (customer_quote_summary_document_fee + " = " + documentFee);			
			
			LO.print          ("Document Fee - found OK");
			System.out.println("Document Fee - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");

			System.err.println(customer_quote_summary_document_fee + " != " + documentFee);
			LO.print          (customer_quote_summary_document_fee + " != " + documentFee);	
			
			LO.print          ("Document Fee - found wrong");
			System.err.println("Document Fee - found wrong");
		}

		// 17.comparing Balance Payable
		if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_balance_payable + " = " + balancePayable);
			LO.print          (customer_quote_summary_balance_payable + " = " + balancePayable);		
			
			LO.print          ("Balance Payable - found OK");
			System.out.println("Balance Payable - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_balance_payable + " != " + balancePayable);
			LO.print          (customer_quote_summary_balance_payable + " != " + balancePayable);				
			
			LO.print          ("Balance Payable - found wrong");
			System.err.println("Balance Payable - found wrong");
		}

		// 18.comparing Option To Purchase Fee
		if ((Difference.of_two_Double_Values(optionToPurchaseFee,
				customer_quote_summary_option_to_purchase_fee)) < 0.2) {
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_option_to_purchase_fee + " = " + optionToPurchaseFee);
			LO.print          (customer_quote_summary_option_to_purchase_fee + " = " + optionToPurchaseFee);			
			
			LO.print          ("Option To Purchase Fee - found OK");
			System.out.println("Option To Purchase Fee - found OK");
	
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_option_to_purchase_fee + " != " + optionToPurchaseFee);
			LO.print          (customer_quote_summary_option_to_purchase_fee + " != " + optionToPurchaseFee);		
			
			LO.print          ("Option To Purchase Fee - found wrong");
			System.err.println("Option To Purchase Fee - found wrong");
		}

		// 19.comparing Initial Cash Payment
		if ((Difference.of_two_Double_Values(initialCashPayment, customer_quote_summary_initial_cash_payment)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_initial_cash_payment + " = " + initialCashPayment);
			LO.print          (customer_quote_summary_initial_cash_payment + " = " + initialCashPayment);			
			
			LO.print          ("Initial Cash Payment - found OK");
			System.out.println("Initial Cash Payment - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_initial_cash_payment + " != " + initialCashPayment);
			LO.print          (customer_quote_summary_initial_cash_payment + " != " + initialCashPayment);			
			
			LO.print          ("Initial Cash Payment - found wrong");
			System.err.println("Initial Cash Payment - found wrong");
		}

		// 20.comparing Followed By months
		if (followedBy == customer_payment_followed_by) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");

			System.out.println(customer_payment_followed_by + " = " + followedBy);
			LO.print          (customer_payment_followed_by + " = " + followedBy);			
			
			LO.print          ("Followed By months - found OK");
			System.out.println("Followed By months - found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");

			System.err.println(customer_payment_followed_by + " != " + followedBy);
			LO.print          (customer_payment_followed_by + " != " + followedBy);			
			
			LO.print          ("Followed By months - found wrong");
			System.err.println("Followed By months - found wrong");
		}

		// 21.comparing Monthly Finance Payment
		if ((Difference.of_two_Double_Values(monthlyFinancePayment,customer_quote_summary_monthly_finance_payment)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");	
			
			System.out.println(customer_quote_summary_monthly_finance_payment + " = " + monthlyFinancePayment);
			LO.print          (customer_quote_summary_monthly_finance_payment + " = " + monthlyFinancePayment);			

			
			LO.print          ("Monthly Finance Payment - found OK");
			System.out.println("Monthly Finance Payment - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_monthly_finance_payment + " != " + monthlyFinancePayment);
			LO.print          (customer_quote_summary_monthly_finance_payment + " != " + monthlyFinancePayment);			
			
			
			LO.print          ("Monthly Finance Payment - found wrong");
			System.err.println("Monthly Finance Payment - found wrong");
		}

		// 22.comparing Balloon Value
		if ((Difference.of_two_Double_Values(balloon, customer_quote_summary_balloon)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_balloon + " = " + balloon);
			LO.print          (customer_quote_summary_balloon + " = " + balloon);			
			
			LO.print          ("Balloon Value - found OK");
			System.out.println("Balloon Value - found OK");
			

		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_balloon + " != " + balloon);
			LO.print          (customer_quote_summary_balloon + " != " + balloon);			
				
			LO.print          ("Balloon Value - found wrong");
			System.err.println("Balloon Value - found wrong");
		}

		// 23.comparing Final Payment
		if ((Difference.of_two_Double_Values(finalPayment,
				customer_quote_summary_final_payment_inc_option_to_purchase_fee)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");	
			
			System.out.println(customer_quote_summary_final_payment_inc_option_to_purchase_fee + " = " + finalPayment);
			LO.print          (customer_quote_summary_final_payment_inc_option_to_purchase_fee + " = " + finalPayment);			
			
			LO.print          ("Final Payment - found OK");
			System.out.println("Final Payment - found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");	
			
			System.err.println(customer_quote_summary_final_payment_inc_option_to_purchase_fee + " != " + finalPayment);
			LO.print          (customer_quote_summary_final_payment_inc_option_to_purchase_fee + " != " + finalPayment);			
					
			LO.print          ("Final Payment - found wrong");
			System.err.println("Final Payment - found wrong");
		}

		// 24.comparing Vehicle Commission	
		
		if ((Difference.of_two_Double_Values(vehicleCommission, customer_quote_summary_vehicle_comm)) < 0.2) {
			
			count++;
	
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_vehicle_comm + " = " + vehicleCommission);
			LO.print          (customer_quote_summary_vehicle_comm + " = " + vehicleCommission);			
			
			LO.print          ("Vehicle Commission - found OK");
			System.out.println("Vehicle Commission - found OK");
			
		} else {

			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_vehicle_comm + " != " + vehicleCommission);
			LO.print          (customer_quote_summary_vehicle_comm + " != " + vehicleCommission);			
			
			LO.print          ("Vehicle Commission - found wrong");
			System.err.println("Vehicle Commission - found wrong");
		}

		// 25.comparing Default Finance Commission
		if ((Difference.of_two_Double_Values(defaultFinanceCommission,customer_quote_summary_default_finance_comm)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");

			System.out.println(customer_quote_summary_default_finance_comm + " = " + defaultFinanceCommission);
			LO.print          (customer_quote_summary_default_finance_comm + " = " + defaultFinanceCommission);			
			
			LO.print          ("Default Finance Commission - found OK");
			System.out.println("Default Finance Commission - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_default_finance_comm + " != " + defaultFinanceCommission);
			LO.print          (customer_quote_summary_default_finance_comm + " != " + defaultFinanceCommission);
			
			LO.print          ("Default Finance Commission - found wrong");
			System.err.println("Default Finance Commission - found wrong");
		}

		// 26.comparing Document Fee Commission
		if ((Difference.of_two_Double_Values(docFeeCommission, customer_quote_summary_document_fee_comm)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_document_fee_comm + " = " + docFeeCommission);
			LO.print          (customer_quote_summary_document_fee_comm + " = " + docFeeCommission);			
				
			LO.print          ("Document Fee Commission - found OK");
			System.out.println("Document Fee Commission - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_document_fee_comm + " != " + docFeeCommission);
			LO.print          (customer_quote_summary_document_fee_comm + " != " + docFeeCommission);
			
			LO.print          ("Document Fee Commission - found wrong");
			System.err.println("Document Fee Commission - found wrong");
		}

		// 27.comparing Total Commission
		if ((Difference.of_two_Double_Values(totalCommission, customer_quote_summary_total_commission)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_total_commission + " = " + totalCommission);
			LO.print          (customer_quote_summary_total_commission + " = " + totalCommission);
			
			LO.print          ("Total Commission - found OK");
			System.out.println("Total Commission - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_total_commission + " != " + totalCommission);
			LO.print          (customer_quote_summary_total_commission + " != " + totalCommission);
			
			LO.print          ("Total Commission - found wrong");
			System.err.println("Total Commission - found wrong");
			
			System.out.println("");
			LO.print          ("");
		}

		
		boolean status = false;
		
		int expcount=0;
		if(Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName().contains("funder"))
		{ expcount=26;} else {expcount=27;}
		
		
		if (count==expcount)
			
		{
			status = true;
	        // ANSI escape code for green color
	        String ansiGreen = "\u001B[32m";	        
	        // ANSI escape code to reset the console color
	        String ansiReset = "\u001B[0m";
	        
			System.out.println("");
			LO.print          ("");
			LO.print          (ansiGreen+"All values on underwriting pop up verified successfully"+ansiReset);
			System.out.println(ansiGreen+"All values on underwriting pop up verified successfully"+ansiReset);
			System.out.println("");
			LO.print          ("");

		}else
		{
			System.out.println("");
			LO.print          ("");
			LO.print          ("One or More than One values on underwriting pop up may be wrong , please check all above values printed on console");
			System.err.println("One or More than One values on underwriting pop up may be wrong , please check all above values printed on console");
			System.out.println("");
			LO.print          ("");
		}
		
		return status;
		

	}

	public boolean verify_underwriting_pop_up_summary_values_for_ownbook_individual_purchase_flow()
			throws InterruptedException, IOException, ClassNotFoundException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		
		//underwriting pop ........

		ExplicitWait.visibleElement(driver, quote_summary_vehicle_heading, 120);
		
		
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_contract_type, 60);
	
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_button, 120);

		// Cliking on cust quote summary section
		Click.on(driver, underwriting_popup_customer_quote_summary_button, 30);

		// waiting for summary section elements

		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_terms, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_miles_per_annum, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_basic_cash_price, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_vat, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_non_vat_items, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_total_cash_price, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_order_deposit, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_finance_deposit, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_total_deposit, 20);
		try{ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_part_exchange_value, 20);}catch(Exception e) {}
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_balance_to_finance, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_finance_charges, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_document_fee, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_balance_payable, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_option_to_purchase_fee, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_initial_cash_payment, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_followed_by, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_monthly_finance_payment, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_guaranteed_future_value, 20);
		ExplicitWait.visibleElement(driver,	quote_summary_customer_quote_summary_final_payment_inc_option_to_purchase_fee, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_finance, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_vehicle_comm, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_default_finance_comm, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_document_fee_comm, 20);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_total_comm, 20);
		// Vehicle details
		String vehicleNameActual = quote_summary_vehicle_heading.getText().trim();

		ExplicitWait.visibleElement(driver, underwriting_popup_quote_ref_no, 30);

		String quotRefNoActual = underwriting_popup_quote_ref_no.getText();
		
		
		// customer quote section
		// getting text from elements

		String contractTypeActual = underwriting_popup_customer_contract_type.getText();
		
		double customer_quote_summary_terms = Double
				.parseDouble(underwriting_popup_customer_quote_summary_terms.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double
				.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_miles_per_annum.getText().trim()));

		double customer_quote_summary_basic_cash_price = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_vat = Double
				.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_vat.getText().trim().substring(2)));

		double customer_quote_summary_non_vat_items = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_non_vat_items.getText().trim().substring(2)));

		double customer_quote_summary_total_cash_price = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_total_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_order_deposit = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_order_deposit.getText().trim().substring(2)));

		double customer_quote_summary_finance_deposit = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_finance_deposit.getText().trim().substring(2)));

		double customer_quote_summary_total_deposit = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_total_deposit.getText().trim().substring(2)));

		double  customer_quote_summary_part_exchange_value =0;
		try{customer_quote_summary_part_exchange_value = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_part_exchange_value.getText().trim().substring(2)));}catch(Exception e) {}
  
		double customer_quote_summary_balance_to_finance = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));

		double customer_quote_summary_finance_charges = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_finance_charges.getText().trim().substring(2)));

		double customer_quote_summary_document_fee = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_document_fee.getText().trim().substring(2)));

		double customer_quote_summary_balance_payable = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_balance_payable.getText().trim().substring(2)));

		double customer_quote_summary_option_to_purchase_fee = Double.parseDouble(RemoveComma
				.of(underwriting_popup_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));

		double customer_quote_summary_initial_cash_payment = Double.parseDouble(RemoveComma
				.of(underwriting_popup_customer_quote_summary_initial_cash_payment.getText().trim().substring(2)));

		double customer_payment_followed_by = Double
				.parseDouble(underwriting_popup_customer_quote_summary_followed_by.getText().substring(0, 2));

		double customer_quote_summary_monthly_finance_payment = Double.parseDouble(RemoveComma
				.of(underwriting_popup_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));


		double customer_quote_summary_balloon = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_guaranteed_future_value.getText().trim().substring(2)));

		double customer_quote_summary_final_payment_inc_option_to_purchase_fee = Double.parseDouble(
				RemoveComma.of(quote_summary_customer_quote_summary_final_payment_inc_option_to_purchase_fee.getText()
						.trim().substring(2)));
		
		double customer_quote_summary_pence_per_excess_mile_finance = Double.parseDouble(RemoveComma.of(
				quote_summary_customer_quote_summary_pence_per_excess_mile_finance.getText().trim().substring(0, 4)));

		double customer_quote_summary_vehicle_comm = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_vehicle_comm.getText().trim().substring(2)));

		double customer_quote_summary_default_finance_comm = Double.parseDouble(RemoveComma
				.of(underwriting_popup_customer_quote_summary_default_finance_comm.getText().trim().substring(2)));

		double customer_quote_summary_document_fee_comm = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_document_fee_comm.getText().trim().substring(2)));

		double customer_quote_summary_total_commission = Double.parseDouble(
				RemoveComma.of(underwriting_popup_customer_quote_summary_total_comm.getText().trim().substring(2)));



        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();

		obj_acq_listing_page = new AcquisitionListingPage();
		
		String sheetName = obj_acq_listing_page.calculation_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		
		
		// getting values from excel

		double terms =0;
		double miles =0;
		
		try {
		terms = GetExcelFormulaValue.get_formula_value(208, 1, sheetName);
		 miles = GetExcelFormulaValue.get_formula_value(208, 4, sheetName);
		}catch(Exception e) 
		{
			 terms = GetExcelFormulaValue.get_string_value(208, 1, sheetName);
			 miles = GetExcelFormulaValue.get_string_value(208, 4, sheetName);
		}
		double basicCashPrice = GetExcelFormulaValue.get_formula_value(214, 0, sheetName);
		double vat = GetExcelFormulaValue.get_formula_value(214, 1, sheetName);
		double nonVATItems = GetExcelFormulaValue.get_formula_value(214, 4, sheetName);

		double totalCashPrice = GetExcelFormulaValue.get_formula_value(217, 0, sheetName);
		double orderDeposit = GetExcelFormulaValue.get_formula_value(217, 1, sheetName);
		double financeDeposit = GetExcelFormulaValue.get_formula_value(217, 4, sheetName);

		double totalDeposit = GetExcelFormulaValue.get_formula_value(220, 0, sheetName);
		double partExchangeValue = GetExcelFormulaValue.get_formula_value(220, 1, sheetName);
		double balanceToFinance = GetExcelFormulaValue.get_formula_value(220, 4, sheetName);

		double financeCharges = GetExcelFormulaValue.get_formula_value(223, 0, sheetName);
		double documentFee = GetExcelFormulaValue.get_string_value(223, 1, sheetName);
		double balancePayable = GetExcelFormulaValue.get_formula_value(223, 4, sheetName);

		double optionToPurchaseFee = GetExcelFormulaValue.get_formula_value(226, 0, sheetName);
		double initialCashPayment = GetExcelFormulaValue.get_formula_value(226, 1, sheetName);
		double followedBy = GetExcelFormulaValue.get_formula_value(226, 4, sheetName);

		double monthlyFinancePayment = GetExcelFormulaValue.get_formula_value(229, 0, sheetName);

		double balloon = GetExcelFormulaValue.get_formula_value(232, 0, sheetName);
		double finalPayment = GetExcelFormulaValue.get_formula_value(232, 1, sheetName);
		double pencePerExcessMileFinance = GetExcelFormulaValue.get_formula_value(232, 4, sheetName);

		double vehicleCommission = GetExcelFormulaValue.get_formula_value(239, 0, sheetName);
		double defaultFinanceCommission = GetExcelFormulaValue.get_formula_value(239, 1, sheetName);

		double docFeeCommission = GetExcelFormulaValue.get_formula_value(242, 0, sheetName);
		double totalCommission = GetExcelFormulaValue.get_formula_value(242, 1, sheetName);
	


		System.out.println("Underwriting pop up - quote ref no  =>" + quotRefNoActual);
		LO.print          ("Underwriting pop up -quote ref no => " + quotRefNoActual);
		
		
		ExplicitWait.visibleElement(driver, underwriting_popup_proposal_id, 30);
		String UnderwritingPopupProposalId = underwriting_popup_proposal_id.getText();
		Thread.sleep(2000);
		String UnderwritingPopupProposalIdFromScreen = UnderwritingPopupProposalId.substring(14, 21);
		Thread.sleep(2000);
		System.out.println("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);
		LO.print("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);


	    sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		

		Thread.sleep(1000);
		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		XSSFWorkbook wb = new XSSFWorkbook(in);
		Thread.sleep(1000);

		wb.getSheet(sheetName).getRow(1).getCell(3).setCellValue(UnderwritingPopupProposalIdFromScreen);

		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		wb.write(out);
		
		
		String quotRefNoExpected                             = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
		String vehicleNameExpected                           = GetExcelFormulaValue.get_cell_value(1, 10, sheetName);
		
		String contractTypeExpected                           = GetExcelFormulaValue.get_cell_value(4, 1, sheetName);		
		
		
		//********************************
		
		System.out.println("");
	    LO.print          ("");
		System.out.println("Started Verifying Summary values");
	    LO.print          ("Started Verifying Summary values");

	    //*******************************
	    
		int count =0;
		
		// 1. comparing quote no.
		if (quotRefNoActual.equals(quotRefNoExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(quotRefNoActual + " = " + quotRefNoExpected);
			LO.print          (quotRefNoActual + " = " + quotRefNoExpected);
			System.out.println("Quote no. compared and found ok");
			LO.print          ("Quote no. compared and found ok");
		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(quotRefNoActual + " != " + quotRefNoExpected);
			LO.print          (quotRefNoActual + " != " + quotRefNoExpected);
			System.err.println("Quote no. compared but found not ok");
			LO.print          ("Quote no. compared but found not ok");
		}

		// 2.comparing vehicle name
		if (vehicleNameActual.equals(vehicleNameExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(vehicleNameActual + " = " + vehicleNameExpected);
			LO.print          (vehicleNameActual + " = " + vehicleNameExpected);
			System.out.println("Vehicle name compared and found ok");
			LO.print          ("Vehicle name compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(vehicleNameActual + " != " + vehicleNameExpected);
			LO.print          (vehicleNameActual + " != " + vehicleNameExpected);
			System.err.println("Vehicle name compared but found not ok");
			LO.print          ("Vehicle name compared but found not ok");

		}

		// 3.comparing contract type
		if (contractTypeActual.equals(contractTypeExpected)) {
			count++;
			System.out.println("");
			LO.print          ("");
			System.out.println(contractTypeActual + " = " + contractTypeExpected);
			LO.print          (contractTypeActual + " = " + contractTypeExpected);
			System.out.println("Contract type compared and found ok");
			LO.print          ("Contract type compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(contractTypeActual + " != " + contractTypeExpected);
			LO.print          (contractTypeActual + " != " + contractTypeExpected);
			System.err.println("Contract type compared but found not ok");
			LO.print          ("Contract type compared but found not ok");
		}

		
		// 4.comparing term
		if (customer_quote_summary_terms == terms) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_terms + " = " + terms);
			LO.print          (customer_quote_summary_terms + " = " + terms);
			System.out.println("Terms compared and found ok");
			LO.print          ("Terms compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_terms + " != " + terms);
			LO.print          (customer_quote_summary_terms + " != " + terms);
			System.err.println("Terms compared but found not ok");
			LO.print          ("Terms compared but found not ok");

		}

		
		// 5.comparing mileage
		if (customer_quote_summary_miles == miles) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(customer_quote_summary_miles + " = " + miles);
			LO.print          (customer_quote_summary_miles + " = " + miles);
			System.out.println("Mileage compared and found ok");
			LO.print          ("Mileage compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(customer_quote_summary_miles + " != " + miles);
			LO.print          (customer_quote_summary_miles + " != " + miles);
			System.err.println("Mileage compared but found not ok");
			LO.print          ("Mileage compared but found not ok");

		}	
		
		// 6.comparing Basic Cash Price
		
		
		if ((Difference.of_two_Double_Values(basicCashPrice, customer_quote_summary_basic_cash_price)) < 0.2) {
			
			count++;
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_basic_cash_price + " = " + basicCashPrice);
			LO.print          (customer_quote_summary_basic_cash_price + " = " + basicCashPrice);		
			
			LO.print          ("Basic Cash Price found OK");
			System.out.println("Basic Cash Price found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_basic_cash_price + " != " + basicCashPrice);
			LO.print          (customer_quote_summary_basic_cash_price + " != " + basicCashPrice);		

			
			LO.print          ("Basic Cash Price found wrong");
			System.err.println("Basic Cash Price found wrong");
		}

		// 7.comparing VAT
		
		if ((Difference.of_two_Double_Values(vat, customer_quote_summary_vat)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_vat + " = " + vat);
			LO.print          (customer_quote_summary_vat + " = " + vat);		

			
			LO.print          ("VAT found OK");
			System.out.println("VAT found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_vat + " != " + vat);
			LO.print          (customer_quote_summary_vat + " != " + vat);		

			LO.print          ("VAT found wrong");
			System.err.println("VAT found wrong");
		}

		// 8.comparing non vat items
		
		if ((Difference.of_two_Double_Values(nonVATItems, customer_quote_summary_non_vat_items)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_non_vat_items + " = " + nonVATItems);
			LO.print          (customer_quote_summary_non_vat_items + " = " + nonVATItems);		

			
			LO.print          ("Non VAT Items Value found OK");
			System.out.println("Non VAT Items Value found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_non_vat_items + " != " + nonVATItems);
			LO.print          (customer_quote_summary_non_vat_items + " != " + nonVATItems);		
	
			
			LO.print          ("Non VAT Items Value found wrong");
			System.err.println("Non VAT Items Value found wrong");
		}

		// 9.comparing Total Cash Price
		
		if ((Difference.of_two_Double_Values(totalCashPrice, customer_quote_summary_total_cash_price)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");

			System.out.println(customer_quote_summary_total_cash_price + " = " + totalCashPrice);
			LO.print          (customer_quote_summary_total_cash_price + " = " + totalCashPrice);		
			
			
			LO.print          ("Total Cash Price found OK");
			System.out.println("Total Cash Price found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_total_cash_price + " != " + totalCashPrice);
			LO.print          (customer_quote_summary_total_cash_price + " != " + totalCashPrice);		

			
			LO.print          ("Total Cash Price found wrong");
			System.err.println("Total Cash Price found wrong");
		}

		// 10.comparing Order Deposit
		if ((Difference.of_two_Double_Values(orderDeposit, customer_quote_summary_order_deposit)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_order_deposit + " = " + orderDeposit);
			LO.print          (customer_quote_summary_order_deposit + " = " + orderDeposit);		

			
			LO.print          ("Order Deposit found OK");
			System.out.println("Order Deposit found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_order_deposit + " != " + orderDeposit);
			LO.print          (customer_quote_summary_order_deposit + " != " + orderDeposit);		

			LO.print          ("Order Deposit found wrong");
			System.err.println("Order Deposit found wrong");
		}

		// 11.comparing Finance Deposit
		if ((Difference.of_two_Double_Values(financeDeposit, customer_quote_summary_finance_deposit)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_finance_deposit + " = " + financeDeposit);
			LO.print          (customer_quote_summary_finance_deposit + " = " + financeDeposit);		
			
			
			LO.print("Finance Deposit found OK");
			System.out.println("Finance Deposit found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_finance_deposit + " != " + financeDeposit);
			LO.print          (customer_quote_summary_finance_deposit + " != " + financeDeposit);			
			
			LO.print("Finance Deposit found wrong");
			System.err.println("Finance Deposit found wrong");
		}

		// 12.comparing Total Deposit
		if ((Difference.of_two_Double_Values(totalDeposit, customer_quote_summary_total_deposit)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_total_deposit + " = " + totalDeposit);
			LO.print          (customer_quote_summary_total_deposit + " = " + totalDeposit);		
			
			
			LO.print          ("Total Deposit found OK");
			System.out.println("Total Deposit found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_total_deposit + " != " + totalDeposit);
			LO.print          (customer_quote_summary_total_deposit + " != " + totalDeposit);			
			
			
			LO.print          ("Total Deposit found wrong");
			System.err.println("Total Deposit found wrong");
		}

		// 13.comparing Part Exchange Value
		
		if(Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName().contains("funder")) {
		}
		else
		{
			if (partExchangeValue == customer_quote_summary_part_exchange_value) {
				
				count++;
			
				System.out.println("");
				LO.print          ("");
				
				System.out.println(customer_quote_summary_part_exchange_value + " = " + partExchangeValue);
				LO.print          (customer_quote_summary_part_exchange_value + " = " + partExchangeValue);			
				
				LO.print          ("Part Exchange Value - found OK");
				System.out.println("Part Exchange Value - found OK");
				
			} else {
				
				System.out.println("");
				LO.print          ("");
				
				System.err.println(customer_quote_summary_part_exchange_value + " != " + partExchangeValue);
				LO.print          (customer_quote_summary_part_exchange_value + " != " + partExchangeValue);			

				
				LO.print          ("Part Exchange Value - found wrong");
				System.err.println("Part Exchange Value - found wrong");
			}
		}

		// 14.comparing Balance to Finance
		if ((Difference.of_two_Double_Values(balanceToFinance, customer_quote_summary_balance_to_finance)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_balance_to_finance + " = " + balanceToFinance);
			LO.print          (customer_quote_summary_balance_to_finance + " = " + balanceToFinance);			
			
			LO.print          ("Balance to Finance found OK");
			System.out.println("Balance to Finance found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_balance_to_finance + " != " + balanceToFinance);
			LO.print          (customer_quote_summary_balance_to_finance + " != " + balanceToFinance);			

			LO.print          ("Balance to Finance found wrong");
			System.err.println("Balance to Finance found wrong");
		}

		// 15.comparing Finance Charges	
		
		if ((Difference.of_two_Double_Values(financeCharges, customer_quote_summary_finance_charges)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_finance_charges + " = " + financeCharges);
			LO.print          (customer_quote_summary_finance_charges + " = " + financeCharges);			
			
			
			LO.print          ("Finance Charges - found OK");
			System.out.println("Finance Charges - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");

			System.err.println(customer_quote_summary_finance_charges + " != " + financeCharges);
			LO.print          (customer_quote_summary_finance_charges + " != " + financeCharges);	
			
			LO.print          ("Finance Charges - found wrong");
			System.err.println("Finance Charges - found wrong");
		}

		// 16.comparing Document Fee
		if ((Difference.of_two_Double_Values(documentFee, customer_quote_summary_document_fee)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_document_fee + " = " + documentFee);
			LO.print          (customer_quote_summary_document_fee + " = " + documentFee);			
			
			LO.print          ("Document Fee - found OK");
			System.out.println("Document Fee - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");

			System.err.println(customer_quote_summary_document_fee + " != " + documentFee);
			LO.print          (customer_quote_summary_document_fee + " != " + documentFee);	
			
			LO.print          ("Document Fee - found wrong");
			System.err.println("Document Fee - found wrong");
		}

		// 17.comparing Balance Payable
		if ((Difference.of_two_Double_Values(balancePayable, customer_quote_summary_balance_payable)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_balance_payable + " = " + balancePayable);
			LO.print          (customer_quote_summary_balance_payable + " = " + balancePayable);		
			
			LO.print          ("Balance Payable - found OK");
			System.out.println("Balance Payable - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_balance_payable + " != " + balancePayable);
			LO.print          (customer_quote_summary_balance_payable + " != " + balancePayable);				
			
			LO.print          ("Balance Payable - found wrong");
			System.err.println("Balance Payable - found wrong");
		}

		// 18.comparing Option To Purchase Fee
		if ((Difference.of_two_Double_Values(optionToPurchaseFee,
				customer_quote_summary_option_to_purchase_fee)) < 0.2) {
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_option_to_purchase_fee + " = " + optionToPurchaseFee);
			LO.print          (customer_quote_summary_option_to_purchase_fee + " = " + optionToPurchaseFee);			
			
			LO.print          ("Option To Purchase Fee - found OK");
			System.out.println("Option To Purchase Fee - found OK");
	
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_option_to_purchase_fee + " != " + optionToPurchaseFee);
			LO.print          (customer_quote_summary_option_to_purchase_fee + " != " + optionToPurchaseFee);		
			
			LO.print          ("Option To Purchase Fee - found wrong");
			System.err.println("Option To Purchase Fee - found wrong");
		}

		// 19.comparing Initial Cash Payment
		if ((Difference.of_two_Double_Values(initialCashPayment, customer_quote_summary_initial_cash_payment)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_initial_cash_payment + " = " + initialCashPayment);
			LO.print          (customer_quote_summary_initial_cash_payment + " = " + initialCashPayment);			
			
			LO.print          ("Initial Cash Payment - found OK");
			System.out.println("Initial Cash Payment - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_initial_cash_payment + " != " + initialCashPayment);
			LO.print          (customer_quote_summary_initial_cash_payment + " != " + initialCashPayment);			
			
			LO.print          ("Initial Cash Payment - found wrong");
			System.err.println("Initial Cash Payment - found wrong");
		}

		// 20.comparing Followed By months
		if (followedBy == customer_payment_followed_by) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");

			System.out.println(customer_payment_followed_by + " = " + followedBy);
			LO.print          (customer_payment_followed_by + " = " + followedBy);			
			
			LO.print          ("Followed By months - found OK");
			System.out.println("Followed By months - found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");

			System.err.println(customer_payment_followed_by + " != " + followedBy);
			LO.print          (customer_payment_followed_by + " != " + followedBy);			
			
			LO.print          ("Followed By months - found wrong");
			System.err.println("Followed By months - found wrong");
		}

		// 21.comparing Monthly Finance Payment
		if ((Difference.of_two_Double_Values(monthlyFinancePayment,customer_quote_summary_monthly_finance_payment)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");	
			
			System.out.println(customer_quote_summary_monthly_finance_payment + " = " + monthlyFinancePayment);
			LO.print          (customer_quote_summary_monthly_finance_payment + " = " + monthlyFinancePayment);			

			
			LO.print          ("Monthly Finance Payment - found OK");
			System.out.println("Monthly Finance Payment - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_monthly_finance_payment + " != " + monthlyFinancePayment);
			LO.print          (customer_quote_summary_monthly_finance_payment + " != " + monthlyFinancePayment);			
			
			
			LO.print          ("Monthly Finance Payment - found wrong");
			System.err.println("Monthly Finance Payment - found wrong");
		}

		// 22.comparing Balloon Value
		if ((Difference.of_two_Double_Values(balloon, customer_quote_summary_balloon)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_balloon + " = " + balloon);
			LO.print          (customer_quote_summary_balloon + " = " + balloon);			
			
			LO.print          ("Balloon Value - found OK");
			System.out.println("Balloon Value - found OK");
			

		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_balloon + " != " + balloon);
			LO.print          (customer_quote_summary_balloon + " != " + balloon);			
				
			LO.print          ("Balloon Value - found wrong");
			System.err.println("Balloon Value - found wrong");
		}

		// 23.comparing Final Payment
		if ((Difference.of_two_Double_Values(finalPayment,
				customer_quote_summary_final_payment_inc_option_to_purchase_fee)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");	
			
			System.out.println(customer_quote_summary_final_payment_inc_option_to_purchase_fee + " = " + finalPayment);
			LO.print          (customer_quote_summary_final_payment_inc_option_to_purchase_fee + " = " + finalPayment);			
			
			LO.print          ("Final Payment - found OK");
			System.out.println("Final Payment - found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");	
			
			System.err.println(customer_quote_summary_final_payment_inc_option_to_purchase_fee + " != " + finalPayment);
			LO.print          (customer_quote_summary_final_payment_inc_option_to_purchase_fee + " != " + finalPayment);			
					
			LO.print          ("Final Payment - found wrong");
			System.err.println("Final Payment - found wrong");
		}
		
		
		// 24.comparing Final Payment
		if ((Difference.of_two_Double_Values(pencePerExcessMileFinance,
				customer_quote_summary_pence_per_excess_mile_finance)) < 0.2) {
			count++;
			
			System.out.println("");
			LO.print          ("");	
			
			System.out.println(customer_quote_summary_pence_per_excess_mile_finance + " = " + pencePerExcessMileFinance);
			LO.print          (customer_quote_summary_pence_per_excess_mile_finance + " = " + pencePerExcessMileFinance);			

			LO.print          ("Pence Per Excess Mile Finance - found OK");
			System.out.println("Pence Per Excess Mile Finance - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");	

			System.err.println(customer_quote_summary_pence_per_excess_mile_finance + " != " + pencePerExcessMileFinance);
			LO.print          (customer_quote_summary_pence_per_excess_mile_finance + " != " + pencePerExcessMileFinance);			

			LO.print          ("Pence Per Excess Mile Finance - found wrong");
			System.err.println("Pence Per Excess Mile Finance - found wrong");
		}

		// 25.comparing Vehicle Commission	
		
		if ((Difference.of_two_Double_Values(vehicleCommission, customer_quote_summary_vehicle_comm)) < 0.2) {
			
			count++;
	
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_vehicle_comm + " = " + vehicleCommission);
			LO.print          (customer_quote_summary_vehicle_comm + " = " + vehicleCommission);			
			
			LO.print          ("Vehicle Commission - found OK");
			System.out.println("Vehicle Commission - found OK");
			
		} else {

			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_vehicle_comm + " != " + vehicleCommission);
			LO.print          (customer_quote_summary_vehicle_comm + " != " + vehicleCommission);			
			
			LO.print          ("Vehicle Commission - found wrong");
			System.err.println("Vehicle Commission - found wrong");
		}

		// 26.comparing Default Finance Commission
		if ((Difference.of_two_Double_Values(defaultFinanceCommission,customer_quote_summary_default_finance_comm)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");

			System.out.println(customer_quote_summary_default_finance_comm + " = " + defaultFinanceCommission);
			LO.print          (customer_quote_summary_default_finance_comm + " = " + defaultFinanceCommission);			
			
			LO.print          ("Default Finance Commission - found OK");
			System.out.println("Default Finance Commission - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_default_finance_comm + " != " + defaultFinanceCommission);
			LO.print          (customer_quote_summary_default_finance_comm + " != " + defaultFinanceCommission);
			
			LO.print          ("Default Finance Commission - found wrong");
			System.err.println("Default Finance Commission - found wrong");
		}

		// 27.comparing Document Fee Commission
		if ((Difference.of_two_Double_Values(docFeeCommission, customer_quote_summary_document_fee_comm)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_document_fee_comm + " = " + docFeeCommission);
			LO.print          (customer_quote_summary_document_fee_comm + " = " + docFeeCommission);			
				
			LO.print          ("Document Fee Commission - found OK");
			System.out.println("Document Fee Commission - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_document_fee_comm + " != " + docFeeCommission);
			LO.print          (customer_quote_summary_document_fee_comm + " != " + docFeeCommission);
			
			LO.print          ("Document Fee Commission - found wrong");
			System.err.println("Document Fee Commission - found wrong");
		}

		// 28.comparing Total Commission
		if ((Difference.of_two_Double_Values(totalCommission, customer_quote_summary_total_commission)) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customer_quote_summary_total_commission + " = " + totalCommission);
			LO.print          (customer_quote_summary_total_commission + " = " + totalCommission);
			
			LO.print          ("Total Commission - found OK");
			System.out.println("Total Commission - found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customer_quote_summary_total_commission + " != " + totalCommission);
			LO.print          (customer_quote_summary_total_commission + " != " + totalCommission);
			
			LO.print          ("Total Commission - found wrong");
			System.err.println("Total Commission - found wrong");
			
			System.out.println("");
			LO.print          ("");
		}

		
		int expcount=0;
		if(Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName().contains("funder"))
		{ expcount=27;} else {expcount=28;}
		
		boolean status = false;
		if (count==expcount)


		{
			status = true;
	        // ANSI escape code for green color
	        String ansiGreen = "\u001B[32m";	        
	        // ANSI escape code to reset the console color
	        String ansiReset = "\u001B[0m";
	        
			System.out.println("");
			LO.print          ("");
			LO.print          (ansiGreen+"All values on underwriting pop up verified successfully"+ansiReset);
			System.out.println(ansiGreen+"All values on underwriting pop up verified successfully"+ansiReset);
			System.out.println("");
			LO.print          ("");

		}else
		{
			System.out.println("");
			LO.print          ("");
			LO.print          ("One or More than One values on underwriting pop up may be wrong , please check all above values printed on console");
			System.err.println("One or More than One values on underwriting pop up may be wrong , please check all above values printed on console");
			System.out.println("");
			LO.print          ("");
		}
		
		return status;
		

	}

	
	public boolean verify_underwriting_pop_up_summary_values_for_broker_business_purchase_flow()
			throws InterruptedException, IOException, ClassNotFoundException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		
		//underwriting pop ........

		ExplicitWait.visibleElement(driver, quote_summary_vehicle_heading, 120);		
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_contract_type, 60);	
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_button, 120);

		// Cliking on cust quote summary section
		Click.on(driver, underwriting_popup_customer_quote_summary_button, 30);

		// waiting for summary section elements
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_terms, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_underwriting_popup_miles_per_annum, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_monthly_finance_payment, 120);		
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_funder_name, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_quote_ref_number, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_quote_exp_date, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_contract_mileage, 120);
		
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_total_cash_price, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_cash_deposit, 120);	
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_balance_to_finance, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_finance_charges, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_balance_payable, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_initial_cash_payment_inc_document_fee, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_no_of_monthly_payments, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_final_balloon_payment, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_option_to_purchase_fee, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_RFL_included, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_APR, 120);
       	ExplicitWait.visibleElement(driver, underwriting_popup_quote_ref_no, 30);
		
       	
       	// Vehicle details
		//1
		String vehicleNameActual = quote_summary_vehicle_heading.getText().trim();

		//2
		String quotRefNoActual = underwriting_popup_quote_ref_no.getText();
		
		
		// customer quote section
		// getting text from elements
        //3
		String contractTypeActual = underwriting_popup_customer_contract_type.getText();
	    //4	
		double termActual = Double.parseDouble(underwriting_popup_customer_quote_summary_terms.getText().trim().substring(0,2));
		//5
		double mileageActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_underwriting_popup_miles_per_annum.getText().trim()));
		//6
		double monthlyFinancePaymentActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));
		//7
		String funderNameActual = underwriting_popup_customer_quote_summary_funder_name.getText().trim();
		//8
		String funderQuoteRefNumberActual = underwriting_popup_customer_quote_summary_quote_ref_number.getText().trim();
		//9
		String expiryDateActual = underwriting_popup_customer_quote_summary_quote_exp_date.getText().trim();
		//10
		double contractMileageActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_contract_mileage.getText().trim()));
		//11
		double totalCashPriceActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_total_cash_price.getText().trim().substring(2)));
		//12
		double cashDepositActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_cash_deposit.getText().trim().substring(2)));
		//13
		double balanceToFinanceActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));
		//14
		double financeChargesActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_finance_charges.getText().trim().substring(2)));
		//15
		double balancePayableActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_balance_payable.getText().trim().substring(2)));
		//16
		double initialCashPaymentIncDocumentFeeActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_initial_cash_payment_inc_document_fee.getText().trim().substring(2)));
		//17
		double noOfMonthlyPaymentsActual  = Double.parseDouble(underwriting_popup_customer_quote_summary_no_of_monthly_payments.getText().trim());
		//18
		double finalBalloonPaymentActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_final_balloon_payment.getText().trim().substring(2)));
		//19
		double optionToPurchaseFeeActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));
		//20
		String rFLIncludedActual = underwriting_popup_customer_quote_summary_RFL_included.getText().trim();
		//21
		String [] APR = underwriting_popup_customer_quote_summary_APR.getText().trim().split(" ");
		
		double aPRActual = Double.parseDouble(APR[0]);

		//22
		double commissionActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_commission.getText().trim().substring(2)));
		


		System.out.println("Underwriting pop up - quote ref no  =>" + quotRefNoActual);
		LO.print          ("Underwriting pop up - quote ref no  =>" + quotRefNoActual);
		
		
		ExplicitWait.visibleElement(driver, underwriting_popup_proposal_id, 30);
		String UnderwritingPopupProposalId = underwriting_popup_proposal_id.getText();
		Thread.sleep(2000);
		String UnderwritingPopupProposalIdFromScreen = UnderwritingPopupProposalId.substring(14, 21);
		Thread.sleep(2000);
		System.out.println("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);
		LO.print("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);

		String sheetName = "";

		if (Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName().contains("broker_business_purchase")) {
			sheetName = prop.getProperty("BrokerHPNRQuoteNo");
		}

		if (Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName().contains("broker_individual_purchase")) {
			sheetName = prop.getProperty("BrokerPCPQuoteNo");
		}

		Thread.sleep(1000);
		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		XSSFWorkbook wb = new XSSFWorkbook(in);
		Thread.sleep(1000);

		wb.getSheet(sheetName).getRow(1).getCell(3).setCellValue(UnderwritingPopupProposalIdFromScreen);

		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		wb.write(out);
		
		// Getting Elements from excel sheet 
		
		String quotRefNoExpected                             = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
		String vehicleNameExpected                           = GetExcelFormulaValue.get_cell_value(1, 10, sheetName);
		
		String contractTypeExpected                          = GetExcelFormulaValue.get_cell_value(4, 1, sheetName);
		double termExpected                                  = Double.parseDouble(GetExcelFormulaValue.get_cell_value(4, 3, sheetName));
		
		double mileageExpected                               = Double.parseDouble(GetExcelFormulaValue.get_cell_value(6, 1, sheetName));
		double monthlyFinancePaymentExpected                 = Double.parseDouble(GetExcelFormulaValue.get_cell_value(6, 3, sheetName));
		
		String funderNameExpected                            = GetExcelFormulaValue.get_cell_value(8, 1, sheetName);
		String funderQuoteRefNumberExpected                  = GetExcelFormulaValue.get_cell_value(8, 3, sheetName);
		  
		String expiryDateExpected                               = GetExcelFormulaValue.get_cell_value(10, 1, sheetName);
		double contractMileageExpected                          = Double.parseDouble(GetExcelFormulaValue.get_cell_value(10, 3, sheetName));
		
		double totalCashPriceExpected                           = Double.parseDouble(GetExcelFormulaValue.get_cell_value(12, 1, sheetName));
		double cashDepositExpected                              = Double.parseDouble(GetExcelFormulaValue.get_cell_value(12, 3, sheetName));
		
		double balanceToFinanceExpected                          = Double.parseDouble(GetExcelFormulaValue.get_cell_value(14, 1, sheetName));
		double financeChargesExpected                            = Double.parseDouble(GetExcelFormulaValue.get_cell_value(14, 3, sheetName));	
		
		double balancePayableExpected                             = Double.parseDouble(GetExcelFormulaValue.get_cell_value(16, 1, sheetName));
		double initialCashPaymentIncDocumentFeeExpected           = Double.parseDouble(GetExcelFormulaValue.get_cell_value(16, 3, sheetName));
		
		double noOfMonthlyPaymentsExpected                        = Double.parseDouble(GetExcelFormulaValue.get_cell_value(18, 1, sheetName));
		double finalBalloonPaymentExpected                        = Double.parseDouble(GetExcelFormulaValue.get_cell_value(18, 3, sheetName));
		
		double optionToPurchaseFeeExpected                        = Double.parseDouble(GetExcelFormulaValue.get_cell_value(20, 1, sheetName));
		String rFLIncludedExpected                                = GetExcelFormulaValue.get_cell_value(20, 3, sheetName);

		double aPRExpected                                        = Double.parseDouble(GetExcelFormulaValue.get_cell_value(22, 1, sheetName));
		double commissionExpected                                 = Double.parseDouble(GetExcelFormulaValue.get_cell_value(22, 3, sheetName));
		
		//********************************
		
		System.out.println("");
	    LO.print          ("");
		System.out.println("Started Verifying Summary values");
	    LO.print          ("Started Verifying Summary values");

	    //*******************************
	    
		int count =0;
		
		// 1.comparing quote no.
		if (quotRefNoActual.equals(quotRefNoExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(quotRefNoActual + " = " + quotRefNoExpected);
			LO.print          (quotRefNoActual + " = " + quotRefNoExpected);
			System.out.println("Quote no. compared and found ok");
			LO.print          ("Quote no. compared and found ok");
		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(quotRefNoActual + " != " + quotRefNoExpected);
			LO.print          (quotRefNoActual + " != " + quotRefNoExpected);
			System.err.println("Quote no. compared but found not ok");
			LO.print          ("Quote no. compared but found not ok");
		}

		// 2.comparing vehicle name
		if (vehicleNameActual.equals(vehicleNameExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(vehicleNameActual + " = " + vehicleNameExpected);
			LO.print          (vehicleNameActual + " = " + vehicleNameExpected);
			System.out.println("Vehicle name compared and found ok");
			LO.print          ("Vehicle name compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(vehicleNameActual + " != " + vehicleNameExpected);
			LO.print          (vehicleNameActual + " != " + vehicleNameExpected);
			System.err.println("Vehicle name compared but found not ok");
			LO.print          ("Vehicle name compared but found not ok");

		}

		// 3.comparing contract type
		if (contractTypeActual.equals(contractTypeExpected)) {
			count++;
			System.out.println("");
			LO.print          ("");
			System.out.println(contractTypeActual + " = " + contractTypeExpected);
			LO.print          (contractTypeActual + " = " + contractTypeExpected);
			System.out.println("Contract type compared and found ok");
			LO.print          ("Contract type compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(contractTypeActual + " != " + contractTypeExpected);
			LO.print          (contractTypeActual + " != " + contractTypeExpected);
			System.err.println("Contract type compared but found not ok");
			LO.print          ("Contract type compared but found not ok");
		}

		// 4.comparing monthly finance payment
		if (monthlyFinancePaymentActual == monthlyFinancePaymentExpected) {
			
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(monthlyFinancePaymentActual + " = " + monthlyFinancePaymentExpected);
			LO.print          (monthlyFinancePaymentActual + " = " + monthlyFinancePaymentExpected);
			System.out.println("Monthly finance rental compared and found ok");
			LO.print          ("Monthly finance rental compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(monthlyFinancePaymentActual + " != " + monthlyFinancePaymentExpected);
			LO.print          (monthlyFinancePaymentActual + " != " + monthlyFinancePaymentExpected);
			System.err.println("Monthly finance rental compared but found not ok");
			LO.print          ("Monthly finance rental compared but found not ok");

		}

		// 5.comparing mileage
		if (mileageActual == mileageExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(mileageActual + " = " + mileageExpected);
			LO.print          (mileageActual + " = " + mileageExpected);
			System.out.println("Mileage compared and found ok");
			LO.print          ("Mileage compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(mileageActual + " != " + mileageExpected);
			LO.print          (mileageActual + " != " + mileageExpected);
			System.err.println("Mileage compared but found not ok");
			LO.print          ("Mileage compared but found not ok");

		}

		// 6.comparing term
		if (termActual == termExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(termActual + " = " + termExpected);
			LO.print          (termActual + " = " + termExpected);
			System.out.println("Terms compared and found ok");
			LO.print          ("Terms compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(termActual + " != " + termExpected);
			LO.print          (termActual + " != " + termExpected);
			System.err.println("Terms compared but found not ok");
			LO.print          ("Terms compared but found not ok");

		}

		// 7.comparing funder name
		if (funderNameActual.equals(funderNameExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(funderNameActual + " = " + funderNameExpected);
			LO.print          (funderNameActual + " = " + funderNameExpected);
			System.out.println("Funder name compared and found ok");
			LO.print          ("Funder name compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(funderNameActual + " != " + funderNameExpected);
			LO.print          (funderNameActual + " != " + funderNameExpected);
			System.err.println("Funder name compared but found not ok");
			LO.print          ("Funder name compared but found not ok");

		}

		// 8.comparing funder quote ref no.
		if (funderQuoteRefNumberActual.equals(funderQuoteRefNumberExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(funderQuoteRefNumberActual + " = " + funderQuoteRefNumberExpected);
			LO.print          (funderQuoteRefNumberActual + " = " + funderQuoteRefNumberExpected);
			System.out.println("Funder quote ref no. compared and found ok");
			LO.print          ("Funder quote ref no. compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(funderQuoteRefNumberActual + " != " + funderQuoteRefNumberExpected);
			LO.print          (funderQuoteRefNumberActual + " != " + funderQuoteRefNumberExpected);
			System.err.println("Funder quote ref no. compared but found not ok");
			LO.print          ("Funder quote ref no. compared but found not ok");

		}

		// 9.comparing Expiry Date
		if (expiryDateActual.equals(expiryDateExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(expiryDateActual + " = " + expiryDateExpected);
			LO.print          (expiryDateActual + " = " + expiryDateExpected);
			System.out.println("Expiry Date compared and found ok");
			LO.print          ("Expiry Date compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(expiryDateActual + " != " + expiryDateExpected);
			LO.print          (expiryDateActual + " != " + expiryDateExpected);
			System.err.println("Expiry Date compared but found not ok");
			LO.print          ("Expiry Date compared but found not ok");

		}

		// 10.comparing contract mileage
		if (contractMileageActual == contractMileageExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(contractMileageActual + " = " + contractMileageExpected);
			LO.print          (contractMileageActual + " = " + contractMileageExpected);
			System.out.println("Contract mileage compared and found ok");
			LO.print          ("Contract mileage compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(contractMileageActual + " != " + contractMileageExpected);
			LO.print          (contractMileageActual + " != " + contractMileageExpected);
			System.err.println("Contract mileage compared but found not ok");
			LO.print          ("Contract mileage compared but found not ok");

		}

		// 11.comparing Total Cash Price
		if (totalCashPriceActual == totalCashPriceExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(totalCashPriceActual + " = " + totalCashPriceExpected);
			LO.print          (totalCashPriceActual + " = " + totalCashPriceExpected);
			System.out.println("Total Cash Price compared and found ok");
			LO.print          ("Total Cash Price compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(totalCashPriceActual + " != " + totalCashPriceExpected);
			LO.print          (totalCashPriceActual + " != " + totalCashPriceExpected);
			System.err.println("Total Cash Price compared but found not ok");
			LO.print          ("Total Cash Price compared but found not ok");

		}

	

		// 12.comparing  Cash Deposit
		if (cashDepositActual == cashDepositExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(cashDepositActual + " = " + cashDepositExpected);
			LO.print          (cashDepositActual + " = " + cashDepositExpected);
			System.out.println("Cash Deposit compared and found ok");
			LO.print          ("Cash Deposit compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(cashDepositActual + " != " + cashDepositExpected);
			LO.print          (cashDepositActual + " != " + cashDepositExpected);
			System.err.println("Cash Deposit compared but found not ok");
			LO.print          ("Cash Deposit compared but found not ok");

		}
		
		
		// 13.comparing  Balance To Finance
		if (balanceToFinanceActual == balanceToFinanceExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(balanceToFinanceActual + " = " + balanceToFinanceExpected);
			LO.print          (balanceToFinanceActual + " = " + balanceToFinanceExpected);
			System.out.println("Balance To Finance compared and found ok");
			LO.print          ("Balance To Finance compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(balanceToFinanceActual + " != " + balanceToFinanceExpected);
			LO.print          (balanceToFinanceActual + " != " + balanceToFinanceExpected);
			System.err.println("Balance To Finance compared but found not ok");
			LO.print          ("Balance To Finance compared but found not ok");

		}


		// 14.comparing  Finance Charges
		if (financeChargesActual == financeChargesExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(financeChargesActual + " = " + financeChargesExpected);
			LO.print          (financeChargesActual + " = " + financeChargesExpected);
			System.out.println("Finance Charges compared and found ok");
			LO.print          ("Finance Charges compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(financeChargesActual + " != " + financeChargesExpected);
			LO.print          (financeChargesActual + " != " + financeChargesExpected);
			System.err.println("Finance Charges compared but found not ok");
			LO.print          ("Finance Charges compared but found not ok");

		}

		
		// 15.comparing  Balance Payable Expected
		if (balancePayableActual == balancePayableExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(balancePayableActual + " = " + balancePayableExpected);
			LO.print          (balancePayableActual + " = " + balancePayableExpected);
			System.out.println("Balance Payable compared and found ok");
			LO.print          ("Balance Payable compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(balancePayableActual + " != " + balancePayableExpected);
			LO.print          (balancePayableActual + " != " + balancePayableExpected);
			System.err.println("Balance Payable compared but found not ok");
			LO.print          ("Balance Payable compared but found not ok");

		}

		// 16.comparing  initial Cash Payment Inc Document Fee 
		if (initialCashPaymentIncDocumentFeeActual == initialCashPaymentIncDocumentFeeExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(initialCashPaymentIncDocumentFeeActual + " = " + initialCashPaymentIncDocumentFeeExpected);
			LO.print          (initialCashPaymentIncDocumentFeeActual + " = " + initialCashPaymentIncDocumentFeeExpected);
			System.out.println("Initial Cash Payment Inc Document Fee compared and found ok");
			LO.print          ("Initial Cash Payment Inc Document Fee compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(initialCashPaymentIncDocumentFeeActual + " != " + initialCashPaymentIncDocumentFeeExpected);
			LO.print          (initialCashPaymentIncDocumentFeeActual + " != " + initialCashPaymentIncDocumentFeeExpected);
			System.err.println("Initial Cash Payment Inc Document Fee compared but found not ok");
			LO.print          ("Initial Cash Payment Inc Document Fee compared but found not ok");

		}
		
		
		// 17.comparing  No. of Monthly Payments 
		if (noOfMonthlyPaymentsActual == noOfMonthlyPaymentsExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(noOfMonthlyPaymentsActual + " = " + noOfMonthlyPaymentsExpected);
			LO.print          (noOfMonthlyPaymentsActual + " = " + noOfMonthlyPaymentsExpected);
			System.out.println("No. of Monthly Payments compared and found ok");
			LO.print          ("No. of Monthly Payments compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(noOfMonthlyPaymentsActual + " != " + noOfMonthlyPaymentsExpected);
			LO.print          (noOfMonthlyPaymentsActual + " != " + noOfMonthlyPaymentsExpected);
			System.err.println("No. of Monthly Payments compared but found not ok");
			LO.print          ("No. of Monthly Payments compared but found not ok");

		}
		
		// 18.comparing  Final Balloon Payment 
		if (finalBalloonPaymentActual == finalBalloonPaymentExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(finalBalloonPaymentActual + " = " + finalBalloonPaymentExpected);
			LO.print          (finalBalloonPaymentActual + " = " + finalBalloonPaymentExpected);
			System.out.println("Final Balloon Payment compared and found ok");
			LO.print          ("Final Balloon Payment compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(finalBalloonPaymentActual + " != " + finalBalloonPaymentExpected);
			LO.print          (finalBalloonPaymentActual + " != " + finalBalloonPaymentExpected);
			System.err.println("Final Balloon Payment compared but found not ok");
			LO.print          ("Final Balloon Payment compared but found not ok");

		}

		
		// 19.comparing  Option To Purchase Fee  
		if (optionToPurchaseFeeActual == optionToPurchaseFeeExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(optionToPurchaseFeeActual + " = " + optionToPurchaseFeeExpected);
			LO.print          (optionToPurchaseFeeActual + " = " + optionToPurchaseFeeExpected);
			System.out.println("Option To Purchase Fee compared and found ok");
			LO.print          ("Option To Purchase Fee compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(optionToPurchaseFeeActual + " != " + optionToPurchaseFeeExpected);
			LO.print          (optionToPurchaseFeeActual + " != " + optionToPurchaseFeeExpected);
			System.err.println("Option To Purchase Fee compared but found not ok");
			LO.print          ("Option To Purchase Fee compared but found not ok");

		}


		// 20.comparing RFL included ?
		
		if (rFLIncludedActual.equals(rFLIncludedExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(rFLIncludedActual + " = " + rFLIncludedExpected);
			LO.print          (rFLIncludedActual + " = " + rFLIncludedExpected);
			System.out.println("RFL included ? - compared and found ok");
			LO.print          ("RFL included ? - compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(rFLIncludedActual + " != " + rFLIncludedExpected);
			LO.print          (rFLIncludedActual + " != " + rFLIncludedExpected);
			System.err.println("RFL included ? - compared but found not ok");
			LO.print          ("RFL included ? - compared but found not ok");

		}

		
	// 21.comparing APR
		
		if (aPRActual==aPRExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(aPRActual + " = " + aPRExpected);
			LO.print          (aPRActual + " = " + aPRExpected);
			System.out.println("APR compared and found ok");
			LO.print          ("APR compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(aPRActual + " != " + aPRExpected);
			LO.print          (aPRActual + " != " + aPRExpected);
			System.err.println("APR compared but found not ok");
			LO.print          ("APR compared but found not ok");

		}

		
		// 22.comparing commission
		if (commissionActual == commissionExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(commissionActual + " = " + commissionExpected);
			LO.print          (commissionActual + " = " + commissionExpected);
			System.out.println("commission compared and found ok");
			LO.print          ("commission compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(commissionActual + " != " + commissionExpected);
			LO.print          (commissionActual + " != " + commissionExpected);
			System.err.println("commission compared but found not ok");
			LO.print          ("commission compared but found not ok");

		}
								
		
		boolean status = false;
		if (count==22)
			
		{
			status = true;
		}
		
		return status;	

	}

	public boolean verify_underwriting_pop_up_summary_values_for_broker_individual_purchase_flow()
			throws InterruptedException, IOException, ClassNotFoundException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		
		//underwriting pop ........

		ExplicitWait.visibleElement(driver, quote_summary_vehicle_heading, 120);		
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_contract_type, 60);	
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_button, 120);

		// Cliking on cust quote summary section
		Click.on(driver, underwriting_popup_customer_quote_summary_button, 30);

		// waiting for summary section elements
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_terms, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_underwriting_popup_miles_per_annum, 120);
		
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_monthly_finance_payment, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_monthly_finance_payment, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_monthly_finance_payment, 120);
		
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_funder_name, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_quote_ref_number, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_quote_exp_date, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_contract_mileage, 120);	
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_total_cash_price, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_cash_deposit, 120);	
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_balance_to_finance, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_no_of_monthly_payments, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_optional_final_payment, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_option_to_purchase_fee, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_RFL_included, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_pence_per_excess_mile_finance, 120);
//		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_pence_per_excess_mile_maint, 120);
//		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_pence_per_excess_mile_total, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_APR, 120);
		ExplicitWait.visibleElement(driver, underwriting_popup_customer_quote_summary_commission, 120);
	
       	
       	// Vehicle details
		//1
		String vehicleNameActual = quote_summary_vehicle_heading.getText().trim();

		//2
		String quotRefNoActual = underwriting_popup_quote_ref_no.getText();
		
		
		// customer quote section
		// getting text from elements
        //3
		String contractTypeActual = underwriting_popup_customer_contract_type.getText();
	    //4	
		double termActual = Double.parseDouble(underwriting_popup_customer_quote_summary_terms.getText().trim().substring(0,2));
		//5
		double mileageActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_underwriting_popup_miles_per_annum.getText().trim()));
		//6
		double monthlyFinancePaymentActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));
		//7
		String funderNameActual = underwriting_popup_customer_quote_summary_funder_name.getText().trim();
		//8
		String funderQuoteRefNumberActual = underwriting_popup_customer_quote_summary_quote_ref_number.getText().trim();
		//9
		String expiryDateActual = underwriting_popup_customer_quote_summary_quote_exp_date.getText().trim();
		//10
		double contractMileageActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_contract_mileage.getText().trim()));
		//11
		double totalCashPriceActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_total_cash_price.getText().trim().substring(2)));
		//12
		double cashDepositActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_cash_deposit.getText().trim().substring(2)));
		//13
		double balanceToFinanceActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));
		//14
		double noOfMonthlyPaymentsActual  = Double.parseDouble(underwriting_popup_customer_quote_summary_no_of_monthly_payments.getText().trim());
		//15
		double optionalFinalPaymentActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_optional_final_payment.getText().trim().substring(2)));
		//16
		double optionToPurchaseFeeActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));
		//17
		String rFLIncludedActual = underwriting_popup_customer_quote_summary_RFL_included.getText().trim();
		//18
		String pencePerExcessMileFinanceActual = RemoveComma.of(underwriting_popup_customer_quote_summary_pence_per_excess_mile_finance.getText().trim());
    	//19
		String [] APR = underwriting_popup_customer_quote_summary_APR.getText().trim().split(" ");
		double aPRActual = Double.parseDouble(APR[0]);
		//20
		double commissionActual = Double.parseDouble(RemoveComma.of(underwriting_popup_customer_quote_summary_commission.getText().trim().substring(2)));
		


		System.out.println("Underwriting pop up - quote ref no  =>" + quotRefNoActual);
		LO.print          ("Underwriting pop up - quote ref no  =>" + quotRefNoActual);
		
		
		ExplicitWait.visibleElement(driver, underwriting_popup_proposal_id, 30);
		String UnderwritingPopupProposalId = underwriting_popup_proposal_id.getText();
		Thread.sleep(2000);
		String UnderwritingPopupProposalIdFromScreen = UnderwritingPopupProposalId.substring(14, 21);
		Thread.sleep(2000);
		System.out.println("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);
		LO.print("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);

		String sheetName = "";

		if (Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName().contains("business_purchase")) {
			sheetName = prop.getProperty("BrokerHPNRQuoteNo");
		}

		if (Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName().contains("individual_purchase")) {
			sheetName = prop.getProperty("BrokerPCPQuoteNo");
		}

		Thread.sleep(1000);
		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		XSSFWorkbook wb = new XSSFWorkbook(in);
		Thread.sleep(1000);

		wb.getSheet(sheetName).getRow(1).getCell(3).setCellValue(UnderwritingPopupProposalIdFromScreen);

		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		wb.write(out);
		
		// Getting Elements from excel sheet 
		
		String quotRefNoExpected                             = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
		String vehicleNameExpected                           = GetExcelFormulaValue.get_cell_value(1, 10, sheetName);
		
		String contractTypeExpected                          = GetExcelFormulaValue.get_cell_value(4, 1, sheetName);
		double termExpected                                  = Double.parseDouble(GetExcelFormulaValue.get_cell_value(4, 3, sheetName));
		
		double mileageExpected                               = Double.parseDouble(GetExcelFormulaValue.get_cell_value(6, 1, sheetName));
		double monthlyFinancePaymentExpected                 = Double.parseDouble(GetExcelFormulaValue.get_cell_value(6, 3, sheetName));
		
		String funderNameExpected                            = GetExcelFormulaValue.get_cell_value(10, 1, sheetName);
		String funderQuoteRefNumberExpected                  = GetExcelFormulaValue.get_cell_value(10, 3, sheetName);
		  
		String expiryDateExpected                            = GetExcelFormulaValue.get_cell_value(12, 1, sheetName);
		double contractMileageExpected                       = Double.parseDouble(GetExcelFormulaValue.get_cell_value(12, 3, sheetName));
		
		double totalCashPriceExpected                        = Double.parseDouble(GetExcelFormulaValue.get_cell_value(14, 1, sheetName));
		double cashDepositExpected                           = Double.parseDouble(GetExcelFormulaValue.get_cell_value(14, 3, sheetName));
		
		double balanceToFinanceExpected                      = Double.parseDouble(GetExcelFormulaValue.get_cell_value(16, 1, sheetName));
		double noOfMonthlyPaymentsExpected                   = Double.parseDouble(GetExcelFormulaValue.get_cell_value(16, 3, sheetName));	
		
		double optionalFinalPaymentExpected                  = Double.parseDouble(GetExcelFormulaValue.get_cell_value(18, 1, sheetName));
		double optionToPurchaseFeeExpected                   = Double.parseDouble(GetExcelFormulaValue.get_cell_value(18, 3, sheetName));
		
		String rFLIncludedExpected                           = GetExcelFormulaValue.get_cell_value(20, 1, sheetName);
		String pencePerExcessMileFinanceExpected             = GetExcelFormulaValue.get_cell_value(20, 3, sheetName);

		double aPRExpected                                   = Double.parseDouble(GetExcelFormulaValue.get_cell_value(24, 1, sheetName));
		double commissionExpected                            = Double.parseDouble(GetExcelFormulaValue.get_cell_value(24, 3, sheetName));
		
		//********************************
		
		System.out.println("");
	    LO.print          ("");
		System.out.println("Started Verifying Summary values");
	    LO.print          ("Started Verifying Summary values");

	    //*******************************
	    
		int count =0;
		
		// 1.comparing quote no.
		if (quotRefNoActual.equals(quotRefNoExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(quotRefNoActual + " = " + quotRefNoExpected);
			LO.print          (quotRefNoActual + " = " + quotRefNoExpected);
			System.out.println("Quote no. compared and found ok");
			LO.print          ("Quote no. compared and found ok");
		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(quotRefNoActual + " != " + quotRefNoExpected);
			LO.print          (quotRefNoActual + " != " + quotRefNoExpected);
			System.err.println("Quote no. compared but found not ok");
			LO.print          ("Quote no. compared but found not ok");
		}

		// 2.comparing vehicle name
		if (vehicleNameActual.equals(vehicleNameExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(vehicleNameActual + " = " + vehicleNameExpected);
			LO.print          (vehicleNameActual + " = " + vehicleNameExpected);
			System.out.println("Vehicle name compared and found ok");
			LO.print          ("Vehicle name compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(vehicleNameActual + " != " + vehicleNameExpected);
			LO.print          (vehicleNameActual + " != " + vehicleNameExpected);
			System.err.println("Vehicle name compared but found not ok");
			LO.print          ("Vehicle name compared but found not ok");

		}

		// 3.comparing contract type
		if (contractTypeActual.equals(contractTypeExpected)) {
			count++;
			System.out.println("");
			LO.print          ("");
			System.out.println(contractTypeActual + " = " + contractTypeExpected);
			LO.print          (contractTypeActual + " = " + contractTypeExpected);
			System.out.println("Contract type compared and found ok");
			LO.print          ("Contract type compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(contractTypeActual + " != " + contractTypeExpected);
			LO.print          (contractTypeActual + " != " + contractTypeExpected);
			System.err.println("Contract type compared but found not ok");
			LO.print          ("Contract type compared but found not ok");
		}

		// 4.comparing monthly finance payment
		if (monthlyFinancePaymentActual == monthlyFinancePaymentExpected) {
			
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(monthlyFinancePaymentActual + " = " + monthlyFinancePaymentExpected);
			LO.print          (monthlyFinancePaymentActual + " = " + monthlyFinancePaymentExpected);
			System.out.println("Monthly finance rental compared and found ok");
			LO.print          ("Monthly finance rental compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(monthlyFinancePaymentActual + " != " + monthlyFinancePaymentExpected);
			LO.print          (monthlyFinancePaymentActual + " != " + monthlyFinancePaymentExpected);
			System.err.println("Monthly finance rental compared but found not ok");
			LO.print          ("Monthly finance rental compared but found not ok");

		}

		// 5.comparing mileage
		if (mileageActual == mileageExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(mileageActual + " = " + mileageExpected);
			LO.print          (mileageActual + " = " + mileageExpected);
			System.out.println("Mileage compared and found ok");
			LO.print          ("Mileage compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(mileageActual + " != " + mileageExpected);
			LO.print          (mileageActual + " != " + mileageExpected);
			System.err.println("Mileage compared but found not ok");
			LO.print          ("Mileage compared but found not ok");

		}

		// 6.comparing term
		if (termActual == termExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(termActual + " = " + termExpected);
			LO.print          (termActual + " = " + termExpected);
			System.out.println("Terms compared and found ok");
			LO.print          ("Terms compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(termActual + " != " + termExpected);
			LO.print          (termActual + " != " + termExpected);
			System.err.println("Terms compared but found not ok");
			LO.print          ("Terms compared but found not ok");

		}

		// 7.comparing funder name
		if (funderNameActual.equals(funderNameExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(funderNameActual + " = " + funderNameExpected);
			LO.print          (funderNameActual + " = " + funderNameExpected);
			System.out.println("Funder name compared and found ok");
			LO.print          ("Funder name compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(funderNameActual + " != " + funderNameExpected);
			LO.print          (funderNameActual + " != " + funderNameExpected);
			System.err.println("Funder name compared but found not ok");
			LO.print          ("Funder name compared but found not ok");

		}

		// 8.comparing funder quote ref no.
		if (funderQuoteRefNumberActual.equals(funderQuoteRefNumberExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(funderQuoteRefNumberActual + " = " + funderQuoteRefNumberExpected);
			LO.print          (funderQuoteRefNumberActual + " = " + funderQuoteRefNumberExpected);
			System.out.println("Funder quote ref no. compared and found ok");
			LO.print          ("Funder quote ref no. compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(funderQuoteRefNumberActual + " != " + funderQuoteRefNumberExpected);
			LO.print          (funderQuoteRefNumberActual + " != " + funderQuoteRefNumberExpected);
			System.err.println("Funder quote ref no. compared but found not ok");
			LO.print          ("Funder quote ref no. compared but found not ok");

		}

		// 9.comparing Expiry Date
		if (expiryDateActual.equals(expiryDateExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(expiryDateActual + " = " + expiryDateExpected);
			LO.print          (expiryDateActual + " = " + expiryDateExpected);
			System.out.println("Expiry Date compared and found ok");
			LO.print          ("Expiry Date compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(expiryDateActual + " != " + expiryDateExpected);
			LO.print          (expiryDateActual + " != " + expiryDateExpected);
			System.err.println("Expiry Date compared but found not ok");
			LO.print          ("Expiry Date compared but found not ok");

		}

		// 10.comparing contract mileage
		if (contractMileageActual == contractMileageExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(contractMileageActual + " = " + contractMileageExpected);
			LO.print          (contractMileageActual + " = " + contractMileageExpected);
			System.out.println("Contract mileage compared and found ok");
			LO.print          ("Contract mileage compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(contractMileageActual + " != " + contractMileageExpected);
			LO.print          (contractMileageActual + " != " + contractMileageExpected);
			System.err.println("Contract mileage compared but found not ok");
			LO.print          ("Contract mileage compared but found not ok");

		}

		// 11.comparing Total Cash Price
		if (totalCashPriceActual == totalCashPriceExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(totalCashPriceActual + " = " + totalCashPriceExpected);
			LO.print          (totalCashPriceActual + " = " + totalCashPriceExpected);
			System.out.println("Total Cash Price compared and found ok");
			LO.print          ("Total Cash Price compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(totalCashPriceActual + " != " + totalCashPriceExpected);
			LO.print          (totalCashPriceActual + " != " + totalCashPriceExpected);
			System.err.println("Total Cash Price compared but found not ok");
			LO.print          ("Total Cash Price compared but found not ok");

		}

	

		// 12.comparing  Cash Deposit
		if (cashDepositActual == cashDepositExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(cashDepositActual + " = " + cashDepositExpected);
			LO.print          (cashDepositActual + " = " + cashDepositExpected);
			System.out.println("Cash Deposit compared and found ok");
			LO.print          ("Cash Deposit compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(cashDepositActual + " != " + cashDepositExpected);
			LO.print          (cashDepositActual + " != " + cashDepositExpected);
			System.err.println("Cash Deposit compared but found not ok");
			LO.print          ("Cash Deposit compared but found not ok");

		}
		
		
		// 13.comparing  Balance To Finance
		if (balanceToFinanceActual == balanceToFinanceExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(balanceToFinanceActual + " = " + balanceToFinanceExpected);
			LO.print          (balanceToFinanceActual + " = " + balanceToFinanceExpected);
			System.out.println("Balance To Finance compared and found ok");
			LO.print          ("Balance To Finance compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(balanceToFinanceActual + " != " + balanceToFinanceExpected);
			LO.print          (balanceToFinanceActual + " != " + balanceToFinanceExpected);
			System.err.println("Balance To Finance compared but found not ok");
			LO.print          ("Balance To Finance compared but found not ok");

		}

		// 14.comparing  No. of Monthly Payments 
		if (noOfMonthlyPaymentsActual == noOfMonthlyPaymentsExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(noOfMonthlyPaymentsActual + " = " + noOfMonthlyPaymentsExpected);
			LO.print          (noOfMonthlyPaymentsActual + " = " + noOfMonthlyPaymentsExpected);
			System.out.println("No. of Monthly Payments compared and found ok");
			LO.print          ("No. of Monthly Payments compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(noOfMonthlyPaymentsActual + " != " + noOfMonthlyPaymentsExpected);
			LO.print          (noOfMonthlyPaymentsActual + " != " + noOfMonthlyPaymentsExpected);
			System.err.println("No. of Monthly Payments compared but found not ok");
			LO.print          ("No. of Monthly Payments compared but found not ok");

		}


		
		// 15.comparing  Optional Final Payment
		if (optionalFinalPaymentActual == optionalFinalPaymentExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(optionalFinalPaymentActual + " = " + optionalFinalPaymentExpected);
			LO.print          (optionalFinalPaymentActual + " = " + optionalFinalPaymentExpected);
			System.out.println("Optional Final Payment compared and found ok");
			LO.print          ("Optional Final Payment compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(optionalFinalPaymentActual + " != " + optionalFinalPaymentExpected);
			LO.print          (optionalFinalPaymentActual + " != " + optionalFinalPaymentExpected);
			System.err.println("Optional Final Payment compared but found not ok");
			LO.print          ("Optional Final Payment compared but found not ok");

		}

		
		// 16.comparing  Option To Purchase Fee 
		if (optionToPurchaseFeeActual == optionToPurchaseFeeExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(optionToPurchaseFeeActual + " = " + optionToPurchaseFeeExpected);
			LO.print          (optionToPurchaseFeeActual + " = " + optionToPurchaseFeeExpected);
			System.out.println("Option To Purchase Fee compared and found ok");
			LO.print          ("Option To Purchase Fee compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(optionToPurchaseFeeActual + " != " + optionToPurchaseFeeExpected);
			LO.print          (optionToPurchaseFeeActual + " != " + optionToPurchaseFeeExpected);
			System.err.println("Option To Purchase Fee compared but found not ok");
			LO.print          ("Option To Purchase Fee compared but found not ok");

		}


		// 17.comparing RFL included ?
		
		if (rFLIncludedActual.equals(rFLIncludedExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(rFLIncludedActual + " = " + rFLIncludedExpected);
			LO.print          (rFLIncludedActual + " = " + rFLIncludedExpected);
			System.out.println("RFL included ? - compared and found ok");
			LO.print          ("RFL included ? - compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(rFLIncludedActual + " != " + rFLIncludedExpected);
			LO.print          (rFLIncludedActual + " != " + rFLIncludedExpected);
			System.err.println("RFL included ? - compared but found not ok");
			LO.print          ("RFL included ? - compared but found not ok");

		}

		// 18.comparing Pence Per Excess Mile Finance
		
		if (pencePerExcessMileFinanceActual.equals(pencePerExcessMileFinanceExpected)) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(pencePerExcessMileFinanceActual + " = " + pencePerExcessMileFinanceExpected);
			LO.print          (pencePerExcessMileFinanceActual + " = " + pencePerExcessMileFinanceExpected);
			System.out.println("Pence Per Excess Mile Finance compared and found ok");
			LO.print          ("Pence Per Excess Mile Finance compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(pencePerExcessMileFinanceActual + " != " + pencePerExcessMileFinanceExpected);
			LO.print          (pencePerExcessMileFinanceActual + " != " + pencePerExcessMileFinanceExpected);
			System.err.println("Pence Per Excess Mile Finance compared but found not ok");
			LO.print          ("Pence Per Excess Mile Finance compared but found not ok");

		}


	// 19.comparing APR
		
		if (aPRActual==aPRExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(aPRActual + " = " + aPRExpected);
			LO.print          (aPRActual + " = " + aPRExpected);
			System.out.println("APR compared and found ok");
			LO.print          ("APR compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(aPRActual + " != " + aPRExpected);
			LO.print          (aPRActual + " != " + aPRExpected);
			System.err.println("APR compared but found not ok");
			LO.print          ("APR compared but found not ok");

		}

		
		// 20.comparing commission
		if (commissionActual == commissionExpected) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(commissionActual + " = " + commissionExpected);
			LO.print          (commissionActual + " = " + commissionExpected);
			System.out.println("commission compared and found ok");
			LO.print          ("commission compared and found ok");

		} else {
			System.out.println("");
			LO.print          ("");
			System.err.println(commissionActual + " != " + commissionExpected);
			LO.print          (commissionActual + " != " + commissionExpected);
			System.err.println("commission compared but found not ok");
			LO.print          ("commission compared but found not ok");

		}
								
		
		boolean status = false;
		if (count==20)
			
		{
			status = true;
		}
		
		return status;	

	}

	
	public void search_and_verify_ownbook_underwriting_get_proposal_id() throws InterruptedException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		ExplicitWait.visibleElement(driver, underwriting_popup_proposal_id, 30);

		Thread.sleep(2000);

		String UnderwritingPopupProposalId = underwriting_popup_proposal_id.getText();
		Thread.sleep(2000);
		String UnderwritingPopupProposalIdFromScreen = UnderwritingPopupProposalId.substring(14, 21);
		Thread.sleep(2000);
		System.out.println("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);
		LO.print("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);

		Thread.sleep(1000);
		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		XSSFWorkbook wb = new XSSFWorkbook(in);

		Thread.sleep(1000);
		wb.getSheet("HPNRBCHQuoteNo").getRow(0).getCell(1).setCellValue(UnderwritingPopupProposalIdFromScreen);
		// wb.getSheet("BrokerBCHQuoteNo").getRow(0).getCell(2).setCellValue(UnderwritingPopupProposalId);
		Thread.sleep(1000);
		Thread.sleep(1000);
		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		wb.write(out);

	}

	public void search_and_verify_ownbook_hire_underwriting_get_proposal_id() throws InterruptedException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		ExplicitWait.visibleElement(driver, underwriting_popup_proposal_id, 30);

		Thread.sleep(2000);

		String UnderwritingPopupProposalId = underwriting_popup_proposal_id.getText();
		Thread.sleep(2000);
		String UnderwritingPopupProposalIdFromScreen = UnderwritingPopupProposalId.substring(14, 21);
		Thread.sleep(2000);
		System.out.println("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);
		LO.print("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);

		Thread.sleep(1000);
		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		XSSFWorkbook wb = new XSSFWorkbook(in);

		Thread.sleep(1000);
		wb.getSheet("HPNRBCHQuoteNo").getRow(0).getCell(1).setCellValue(UnderwritingPopupProposalIdFromScreen);
		// wb.getSheet("BrokerBCHQuoteNo").getRow(0).getCell(2).setCellValue(UnderwritingPopupProposalId);
		Thread.sleep(1000);
		Thread.sleep(1000);
		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		wb.write(out);

	}

	public void search_and_verify_ownbook_purchase_underwriting_get_proposal_id()
			throws InterruptedException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		ExplicitWait.visibleElement(driver, underwriting_popup_proposal_id, 30);

		Thread.sleep(2000);

		String UnderwritingPopupProposalId = underwriting_popup_proposal_id.getText();
		Thread.sleep(2000);
		String UnderwritingPopupProposalIdFromScreen = UnderwritingPopupProposalId.substring(14, 21);

		System.out.println("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);
		LO.print("Underwriting Popup Proposal Id is =" + UnderwritingPopupProposalIdFromScreen);

		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		XSSFWorkbook wb = new XSSFWorkbook(in);

		Thread.sleep(1000);
		wb.getSheet(prop.getProperty("HPNR_HPNR_QuoteNo")).getRow(1).getCell(3)
				.setCellValue(UnderwritingPopupProposalIdFromScreen);

		Thread.sleep(1000);
		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		wb.write(out);

	}

	public void search_and_verify_underwriting_send_for_underwriting_button() throws InterruptedException {

		ExplicitWait.visibleElement(driver, underwriting_send_for_underwriting_button, 30);
		underwriting_send_for_underwriting_button.click();

		System.out.println("Click on Send for Underwriting button");
		LO.print("Click on Send for Underwriting button");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

	}

	public void search_and_verify_underwriting_get_quote_no()

	{

		ExplicitWait.visibleElement(driver, underwriting_popup_quote_ref_no, 30);

		String underwriting_quote_ref_no = underwriting_popup_quote_ref_no.getText();

		System.out.println("Underwriting pop up - quote ref no  =>" + underwriting_quote_ref_no);
		LO.print("Underwriting pop up -quote ref no => " + underwriting_quote_ref_no);

	}

	public void search_and_verify_underwriting_download_contract_file() throws InterruptedException {

		ExplicitWait.visibleElement(driver, underwriting_popup_download_contract_file, 30);

		underwriting_popup_download_contract_file.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		System.out.println("Click on underwriting pop up download contract file");
		LO.print("Click on underwriting pop up download contract file ");

	}

	public void search_and_verify_underwriting_download_proposal() throws InterruptedException {

		ExplicitWait.visibleElement(driver, underwriting_popup_download_proposal, 30);

		underwriting_popup_download_proposal.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		System.out.println("Click on underwriting pop up download proposal");
		LO.print("Click on underwriting pop up download proposal ");

		ArrayList<String> browserwindow = new ArrayList<String>(driver.getWindowHandles());
		// switch to active tab
		driver.switchTo().window(browserwindow.get(1));

		System.out.println("Page title of active tab: " + driver.getTitle());
		LO.print("Page title of active tab: " + driver.getTitle());
		// switch to parent
		// driver.wait(2000);
		driver.switchTo().window(browserwindow.get(0));
		System.out.println("Page title of parent window: " + driver.getTitle());
		LO.print("Page title of parent window: " + driver.getTitle());
		// driver.quit();

	}

	public void verify_underwriting_cancel_button() {

		ExplicitWait.visibleElement(driver, underwriting_popup_cancel_button, 30);

		underwriting_popup_cancel_button.click();

		System.out.println("click on underwriting pop up cancel button");
		LO.print("click on underwriting pop up cancel button ");
	}

	public void verify_send_for_underwriting_button() throws InterruptedException {

		ExplicitWait.visibleElement(driver, underwriting_send_for_underwriting_button, 60);

		underwriting_send_for_underwriting_button.click();
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 250);

		System.out.println("click on underwriting_send_for_underwriting  button");
		LO.print("click on underwriting_send_for_underwriting  button ");
	}

	public void verify_ownbook_underwriting_popup_accept_with_change_flow() throws InterruptedException {

		ExplicitWait.visibleElement(driver, ownbook_underwriting_popup_accept, 30);

		ownbook_underwriting_popup_accept.click();

		ExplicitWait.visibleElement(driver, ownbook_underwriting_popup_accept_ok_button, 30);

		ownbook_underwriting_popup_accept_ok_button.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

		System.out.println("click on underwriting_send_for_underwriting  button");
		LO.print("click on underwriting_send_for_underwriting  button ");
	}
	// ownbook_underwriting_popup_accept (//button[normalize-space()='Accept'])[1]
	// ownbook_underwriting_popup_reject - (//button[normalize-space()='Reject'])[1]

}
