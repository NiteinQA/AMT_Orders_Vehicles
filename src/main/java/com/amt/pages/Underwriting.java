package com.amt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.openqa.selenium.support.FindAll;
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

public class Underwriting extends TestBase {

	JavascriptExecutor js;
	
	AcquisitionListingPage  obj_acq_listing_page;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//i[@class='icon-opportunity']")
	private WebElement opportunities;

	// 1.underwriting_menu_link
	@FindBy(xpath = "//span[contains(text(),'Underwriting')]")
	private WebElement underwriting_menu_link;

	// 2a.underwriting_menu_link_broker;
	@FindBy(xpath = "//span[normalize-space()='Broker']")
	private WebElement underwriting_menu_link_broker;

	// 2a.underwriting_menu_link_ownbook;
	@FindBy(xpath = "//span[normalize-space()='Own Book']")
	private WebElement underwriting_menu_link_ownbook;

	// 3.underwriting_proposal_id

	@FindBy(xpath = "//*[@id='cWraper']/div/app-underwriting-management/div[2]/div/div/app-uw-listing/div[1]/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[1]/td[1]")
	private WebElement underwriting_proposal_id;

	// 4.underwriting_seach_text_box;

	@FindBy(xpath = "//input[@placeholder='Search something here']")
	private WebElement underwriting_Seach_text_box;

	@FindBy(xpath = "//*[@id='cWraper']/div/app-underwriting-management/div[2]/div/div/app-uw-listing/div[1]/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr")
	private WebElement proposal_detail_listing_data;

	@FindBy(xpath = "//p[contains(text(),'Quote')]")
	private WebElement underwriting_tab_quote;

	@FindBy(xpath = "//p[contains(text(),'Credit File')]")
	private WebElement underwriting_tab_creditfile;

	@FindBy(xpath = "//p[contains(text(),'Documents')]")
	private WebElement underwriting_tab_document;

	@FindBy(xpath = "(//p[normalize-space()='Decision'])[1]")
	private WebElement decision_tab_document;

//Accept_button
	@FindBy(xpath = "//button[normalize-space()='Accept']")
	private WebElement Accept_button;

	// Please confirm
	@FindBy(xpath = "//div[@id='changeConfirmationModal']//button[@class='btn btn-secondary float-right'][normalize-space()='Yes']")
	private WebElement confirm_button;

// Upload 
	@FindBy(xpath = "//button[normalize-space()='Upload']")
	private WebElement accept_upload_button;

	// accept_security_deposit_required
	@FindBy(xpath = "//input[@id='securityDeposit']")
	private WebElement accept_security_deposit_required;

//accept_increased_order_deposit
	@FindBy(xpath = "(//input[@id='increasedOrderDeposit'])[1]")
	private WebElement accept_increased_order_deposit;
	
	//accept_increased_finance_deposit
	@FindBy(xpath = "//input[@id='increasedFinanceDeposit']")
	private WebElement accept_increased_finance_deposit;

	// accept_Maintained contract required

	@FindBy(xpath = "//label[contains(text(),'Required')]")
	private WebElement accept_maintained_contract_required;

// finance deposit

	@FindBy(xpath = "//input[@id='increasedFinanceDeposit']")
	private WebElement accept_finance_deposit;

	///

	@FindBy(xpath = "//label[contains(text(),'Thatcham approved tracker must be fitted')]")
	private WebElement accept_tracker_must_be_fitted;

	@FindBy(xpath = "//label[normalize-space()='Manufacturer tracker must be activated by customer']")
	private WebElement Manufacturer_tracker_must_be_activated_by_customer;

	@FindBy(xpath = "//label[normalize-space()='AMT tracker required']")
	private WebElement AMT_tracker_required;

	@FindBy(xpath = "//label[normalize-space()='Satisfied']")
	private WebElement StatisfyCheckbox;

	@FindBy(xpath = "//label[normalize-space()='Contract must include insurance']")
	private WebElement Contract_must_include_insurance;

	@FindBy(xpath = "//label[normalize-space()='Other Conditions']")
	private WebElement Other_conditions;

	@FindBy(xpath = "//input[@placeholder='Other']")
	private WebElement Other_conditions_textbox;

	@FindBy(xpath = "//textarea[@placeholder='Enter special remarks']")
	private WebElement Notestoapplicant;

	// Accept condition

	// Driving licence for Accept

	@FindBy(xpath = "//ng-select[@name='drivingLicenceFor']//input[@role='combobox']")
	private WebElement Driving_licence_for_Accept;

	// Proof of address for

	@FindBy(xpath = "//ng-select[@name='addressProofFor']//input[@role='combobox']")
	private WebElement Proof_of_address_for;

	// Director's guarantee in the name(s) of

	@FindBy(xpath = "//ng-select[@name='directorGuarantee']//input[@role='combobox']")
	private WebElement Director_guarantee_in_the_name;

	// Cross company guarantee in the name of
	@FindBy(xpath = "//input[@id='crosscompanyguarantee']")
	private WebElement Cross_company_guarantee_in_the_name_of;

	//

//accept_select_Payment_profile
//	@FindBy(xpath = "(//span[@class='ng-arrow-wrapper'])[4]")
//	private WebElement accept_payment_profile;
	
	@FindBy(xpath = "//*[@name='paymentProfile']")
	private WebElement accept_payment_profile;
	
	

//accept_Initial payment amount
	@FindBy(xpath = "//*[@id='initialPayments']")
	private WebElement Initial_payment_amount;

	// Initial_payment_amount

	// underw_listing_decision_yes_option

//accept_increased_order_deposit
	@FindBy(xpath = "(//button[@class='btn btn-secondary'][normalize-space()='Yes'])[1]")
	private WebElement underw_listing_decision_yes_option;

	// View Upload
	@FindBy(xpath = "//*[@src='/assets/images/view.svg']")
	private WebElement desicion_upload_view;

	// view close

	@FindBy(xpath = "//*[@id='FileView']//*[@class='close']")
	private WebElement desicion_upload_view_close;

	// Save and exit
	@FindBy(xpath = "//button[normalize-space()='Save & Exit']")
	private WebElement desicion_save_exit_button;
	


	// Con - Yes button

	@FindBy(xpath = "//*[@id='decisionConfirmation']//button[contains(text(),'Yes')]")
	private WebElement desicion_save_exit_button_confirm_yes;

	@FindBy(xpath = "//button[normalize-space()='Refer']")
	private WebElement refer_button;

	@FindBy(xpath = "//div[@class='custom-control custom-checkbox withoutlabel text-center']//input[@id='Payslips']")
	private WebElement refer_check_box_icon;

	@FindBy(xpath = "//textarea[@class='ng-valid ng-dirty ng-touched'] ")
	private WebElement refer_notes_textbox;

// underwriting current status 

	@FindBy(xpath = "(//td[@class='ng-star-inserted'])[9]")
	private WebElement underw_current_status;

	@FindBy(xpath = "//div[@id='confirmationModal']//button[@class='btn btn-secondary float-right'][normalize-space()='Yes']")
	private WebElement underw_listing_yes_option;

// decision_decline_tab
	@FindBy(xpath = "//button[normalize-space()='Decline']")
	private WebElement underw_decision_decline_tab;
	
	// decision_refer_tab
	@FindBy(xpath = "//button[normalize-space()='Refer']")
	private WebElement underw_decision_refer_tab;

//

	@FindBy(xpath = "(//span[normalize-space()='Spread rentals initial payment'])[1]")
	private WebElement underw_accept_spread_rental_payment_value;

//  vehicle details
	@FindBy(xpath = "//*[@class='heading ng-star-inserted']")
	private WebElement underwriting_quote_tab_vehicle_heading;

	// customer quote summary button
	@FindBy(xpath = "//*[normalize-space()='Customer quote summary']//ancestor::button")
	private WebElement underwriting_quote_tab_customer_quote_summary_button;

	// terms
	@FindBy(xpath = "//*[normalize-space()='Term']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_terms;

	// Miles per annum
	@FindBy(xpath = "//*[normalize-space()='Miles per annum']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_miles_per_annum;

	// Funder name
	@FindBy(xpath = "//*[normalize-space()='Funder']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_funder_name;

	// quote ref no.
	@FindBy(xpath = "//*[normalize-space()='Quote reference']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_ref_number;

	// quote exp date
	@FindBy(xpath = "//*[normalize-space()='Quote expiry date']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_exp_date;

	// payment profile
	@FindBy(xpath = "//*[normalize-space()='Payment profile']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_payment_profile;

	// contract mileage
	@FindBy(xpath = "//*[normalize-space()='Contract mileage']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_contract_mileage;

	// Initial finance rental
	@FindBy(xpath = "//*[normalize-space()='Initial finance rental']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_initial_finance_rental;

	// initial maint rental
	@FindBy(xpath = "//*[normalize-space()='Initial maint. rental']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_initial_maint_rental;

	// Total initial rental
	@FindBy(xpath = "//*[normalize-space()='Total initial rental']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_total_initial_rental;

	// Pence per excess mile - finance
	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - finance']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_pence_per_excess_mile_finance;

	// Pence per excess mile - maint.
	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - maint.']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_pence_per_excess_mile_maint;

	// Pence per excess mile - total
	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - total']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_pence_per_excess_mile_total;

	@FindBy(xpath = "//*[normalize-space()='Contract type']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_contract_type;

	@FindBy(xpath = "//*[normalize-space()='Cost OTR price']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_cost_otr_price;

	@FindBy(xpath = "//*[normalize-space()='Cost price ex. VAT & RFL']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_cost_price_ex_vat_and_rfl;

	@FindBy(xpath = "//*[normalize-space()='VAT']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_otr_vat;

	@FindBy(xpath = "//*[normalize-space()='RFL & FRF']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_otr_rfl_and_frf;

	@FindBy(xpath = "//*[normalize-space()='Quote reference no.:']//ancestor::div[1]//span[2]")
	private WebElement underwriting_quote_tab_quote_ref_no;

	@FindBy(xpath = "//*[normalize-space()='Quote reference no.:']//ancestor::div[1]//span[2]")
	private WebElement underwriting_quote_tab_ref_no;

	// Total cash price
	@FindBy(xpath = "//*[normalize-space()='Total cash price']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_total_cash_price;

	// Cash deposit
	@FindBy(xpath = "//*[normalize-space()='Cash deposit']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_cash_deposit;

	// Balance to finance
	@FindBy(xpath = "//*[normalize-space()='Balance to finance']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_balance_to_finance;

	// Finance charges
	@FindBy(xpath = "//*[normalize-space()='Finance charges']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_finance_charges;

	// Balance payable
	@FindBy(xpath = "//*[normalize-space()='Balance payable']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_balance_payable;

	// Initial cash payment inc. document fee
	@FindBy(xpath = "//*[normalize-space()='Initial cash payment inc. document fee']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_initial_cash_payment_inc_document_fee;

	// No. of monthly payments
	@FindBy(xpath = "//*[normalize-space()='No. of monthly payments']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_no_of_monthly_payments;

	// Final balloon payment
	@FindBy(xpath = "//*[normalize-space()='Final balloon payment']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_final_balloon_payment;

	// Option to purchase fee
	@FindBy(xpath = "//*[normalize-space()='Option to purchase fee']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_option_to_purchase_fee;

	// RFL included?
	@FindBy(xpath = "//*[normalize-space()='RFL included?']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_RFL_included;

	// APR
	@FindBy(xpath = "//*[normalize-space()='APR']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_APR;

	// commission
	@FindBy(xpath = "//*[normalize-space()='Commission']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_commission;

	// Monthly finance payment
	@FindBy(xpath = "//*[normalize-space()='Monthly finance payment']//ancestor::div[1]//div//strong|//*[normalize-space()='Monthly finance rental']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_monthly_finance_payment;
	
	// Edit underwriting pop up
	@FindBy(xpath = "(//button[normalize-space()='Yes'])[3]")
	private WebElement edit_underwriting_pop_up;
	
	//Optional final payment	
	@FindBy(xpath = "//*[normalize-space()='Optional final payment']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_optional_final_payment	;

	//Customer Quote heading
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[1]/button/div")//*[normalize-space()='Customer quote summary']//ancestor::button
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_value_verification;

	@FindBy(xpath = "//*[normalize-space()='Term']//ancestor::div[1]//div/p")
	private WebElement underwriting_quote_tab_customer_quote_term;

	@FindBy(xpath = "//*[normalize-space()='Miles per annum']//ancestor::div[1]//div/p")
	private WebElement underwriting_quote_tab_customer_quote_miles;

	@FindBy(xpath = "//*[normalize-space()='Payment profile']//ancestor::div[1]//div/p")
	private WebElement underwriting_quote_tab_customer_quote_payment_profile;

	@FindBy(xpath = "//*[normalize-space()='Initial finance rental']//ancestor::div[1]//div/p/strong")
	private WebElement underwriting_quote_tab_customer_quote_initial_finance_rental;

	@FindBy(xpath = "//*[normalize-space()='Monthly finance rental']//ancestor::div[1]//div/p/strong|//*[normalize-space()='Monthly finance payment']//ancestor::div[1]//div/p/strong")
	private WebElement underwriting_quote_tab_customer_quote_monthly_finance_rental;
	
	@FindBy(xpath = "//*[normalize-space()='Initial maint. rental']//ancestor::div[1]//div/p/strong")
	private WebElement underwriting_quote_tab_customer_quote_initial_maint_rental;

	@FindBy(xpath = "//*[normalize-space()='Total initial rental']//ancestor::div[1]//div/p/strong")
	private WebElement underwriting_quote_tab_customer_quote_initial_total_rental;

	@FindBy(xpath = "//*[normalize-space()='Part exchange value']//ancestor::div[1]//div/p/strong")
	private WebElement underwriting_quote_tab_customer_quote_part_exchange_value;

	@FindBy(xpath = "//*[normalize-space()='Followed by']//ancestor::div[1]//div/p/strong")
	private WebElement underwriting_quote_tab_customer_quote_followed_by;

	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - finance']//ancestor::div[1]//div/p/strong")
	private WebElement underwriting_quote_tab_customer_quote_pence_per_excess_mile_finance;

	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - maint.']//ancestor::div[1]//div/p/strong")
	private WebElement underwriting_quote_tab_customer_quote_pence_per_excess_mile_maintenance;

	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - total']//ancestor::div[1]//div/p/strong")
	private WebElement underwriting_quote_tab_customer_quote_pence_per_excess_mile_total;

	@FindBy(xpath = "//*[normalize-space()='Document fee']//ancestor::div[1]//div/p/strong")
	private WebElement underwriting_quote_tab_customer_quote_doc_fee;

	@FindBy(xpath = "//*[normalize-space()='Upsell']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_customer_quote_upsell;

	@FindBy(xpath = "//*[normalize-space()='Default finance comm.']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_customer_quote_default_finance_commission;

	@FindBy(xpath = "//*[normalize-space()='Upsell comm.']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_customer_quote_upsell_commission;

	@FindBy(xpath = "//*[normalize-space()='Document fee comm.']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_customer_quote_doc_fee_commission;

	@FindBy(xpath = "//*[normalize-space()='Maint. comm.']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_customer_quote_maint_commission;

	@FindBy(xpath = "//*[normalize-space()='Total comm.']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_customer_quote_total_commission;

	@FindBy(xpath = "//*[normalize-space()='Referrer comm.']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_customer_quote_referrer_commission;
	
	@FindBy(xpath = "//*[normalize-space()='Configuration']//ancestor::button")
	private WebElement underwriting_quote_tab_configuration_heading_button;

	@FindBy(xpath = "//*[normalize-space()='Base interest rate']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_base_interest_rate;

	@FindBy(xpath = "//*[normalize-space()='Finance margin']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_finance_margin;

	@FindBy(xpath = "//*[normalize-space()='Deductions']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_deductions;

	@FindBy(xpath = "//*[normalize-space()='Additional margin']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_additional_margin;

	@FindBy(xpath = "//*[normalize-space()='Total margin']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_total_margin;

	@FindBy(xpath = "//*[normalize-space()='Default broker margin']//ancestor::div[1]//div/div/label")
	private WebElement underwriting_quote_tab_default_broker_margin_percentage;
	
			
	@FindBy(xpath = "(//*[normalize-space()='Default broker margin'])[2]//ancestor::div[1]//div/div/label")
	private WebElement underwriting_quote_tab_default_broker_margin_value;

	@FindBy(xpath = "(//*[normalize-space()='Broker upsell margin']//ancestor::div[1]//div//label)[1]")
	private WebElement underwriting_quote_tab_broker_upsell_margin_percentage;

	@FindBy(xpath = "(//*[normalize-space()='Broker upsell margin']//ancestor::div[1]//div//label)[2]//b")
	private WebElement underwriting_quote_tab_broker_upsell_margin;

	@FindBy(xpath = "//*[normalize-space()='Maint. margin']//ancestor::div[1]//div//label/strong")
	private WebElement underwriting_quote_tab_maintenance_margin;

	@FindBy(xpath = "//*[normalize-space()='Document fee margin']//ancestor::div[1]//div//label/b")
	private WebElement underwriting_quote_tab_decument_fee_margin;

	@FindBy(xpath = "//*[normalize-space()='Referrer margin']//ancestor::div[1]//div//label/b")
	private WebElement underwriting_quote_tab_refferer_margin;
	
	@FindAll({ @FindBy(xpath = "//*[@class='ng-dropdown-panel-items scroll-host']//div//ancestor::div[1]//div//div") })
	public List<WebElement> payment_profiles_options_list;	
	
	@FindBy(xpath = "//*[@id='Maintainedcontract']")
	private WebElement maint_contract_checkbox;
	
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[1]/button/div")
	private WebElement underwriting_quote_tab_customer_quote_summary_value_verification;
	// terms
	@FindBy(xpath = "//*[normalize-space()='Term']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_terms;

	// Miles per annum
	@FindBy(xpath = "//*[normalize-space()='Miles per annum']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_miles_per_annum;

	// basic cash price
	@FindBy(xpath = "//*[normalize-space()='Basic cash price']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_basic_cash_price;

	// customer_quote_summary vat
	@FindBy(xpath = "(//*[normalize-space()='VAT']//ancestor::div[1]//div//strong)[2]")
	private WebElement underwriting_quote_tab_customer_quote_summary_vat;

	// non vat items
	@FindBy(xpath = "//*[normalize-space()='Non-VAT items']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_non_vat_items;

	// Total cash price
	@FindBy(xpath = "//*[normalize-space()='Total cash price']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_total_cash_price;

	// Order deposit
	@FindBy(xpath = "//app-purchase-customer-quote-summary-detail//*[normalize-space()='Order deposit']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_order_deposit;

	// Finance deposit
	@FindBy(xpath = "//app-purchase-customer-quote-summary-detail//*[normalize-space()='Finance deposit']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_finance_deposit;

	// Total deposit
	@FindBy(xpath = "//app-purchase-customer-quote-summary-detail//*[normalize-space()='Total deposit']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_total_deposit;

	@FindBy(xpath = "//app-purchase-customer-quote-summary-detail//*[normalize-space()='Part exchange value']//ancestor::div[1]//div/p/strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_part_exchange_value;

	// Balance to finance
	@FindBy(xpath = "//*[normalize-space()='Balance to finance']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_balance_to_finance;

	// Finance charges
	@FindBy(xpath = "//*[normalize-space()='Finance charges']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_finance_charges;

	// Document fee
	@FindBy(xpath = "//*[normalize-space()='Document fee']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_document_fee;

	// Balance payable
	@FindBy(xpath = "//*[normalize-space()='Balance payable']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_balance_payable;

	// Option to purchase fee
	@FindBy(xpath = "//*[normalize-space()='Option to purchase fee']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_option_to_purchase_fee;

	// Initial cash payment
	@FindBy(xpath = "//*[normalize-space()='Initial cash payment']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_initial_cash_payment;

	// Followed by
	@FindBy(xpath = "//*[normalize-space()='Followed by']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_followed_by;

	// Monthly finance payment
	@FindBy(xpath = "//*[normalize-space()='Monthly finance payment']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_monthly_finance_payment;
	
	// Balloon
	@FindBy(xpath = "//*[normalize-space()='Guaranteed future value']//ancestor::div[1]//div//strong|//*[normalize-space()='Balloon']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_guaranteed_future_value;

	// Final payment (inc. option to purchase fee)
	@FindBy(xpath = "//*[normalize-space()='Final payment (inc. option to purchase fee)']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_final_payment_inc_option_to_purchase_fee;

	// Pence per excess mile - finance
	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - finance']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_customer_quote_summary_pence_per_excess_mile_finance;

	// Monthly maint payment
	@FindBy(xpath = "(//*[normalize-space()='Monthly maint. payment']//ancestor::div[1]//div//strong)[2]")
	private WebElement underwriting_quote_tab_customer_quote_summary_monthly_maint_payment;

	// Total Monthly payment
	@FindBy(xpath = "(//*[normalize-space()='Total monthly payment']//ancestor::div[1]//div//strong)[2]")
	private WebElement underwriting_quote_tab_customer_quote_summary_total_monthly_payment;

	// Balloon
	@FindBy(xpath = "//*[normalize-space()='Balloon']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_balloon;

	// Final payment (inc. option to purchase fee)
	@FindBy(xpath = "//*[normalize-space()='Final payment (inc. option to purchase fee)']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_final_payment_inc_option_to_purchase_fee;

	// Pence per excess mile - maint.
	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - maint.']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_pence_per_excess_mile_maint;

	// Credit type
	@FindBy(xpath = "//*[normalize-space()='Credit type']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_underwriting_quote_tab_credit_type;

	// Vehicle comm.
	@FindBy(xpath = "//*[normalize-space()='Vehicle comm.']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_vehicle_comm;

	// Default finance comm.
	@FindBy(xpath = "//*[normalize-space()='Default finance comm.']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_default_finance_comm;

	// Maintenance commision
	@FindBy(xpath = "//*[normalize-space()='Maint. comm.']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_maintenance_commision;

	// Document fee comm.
	@FindBy(xpath = "//*[normalize-space()='Document fee comm.']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_document_fee_comm;

	// Total comm.
	@FindBy(xpath = "//*[normalize-space()='Total comm.']//ancestor::div[1]//div//strong")
	private WebElement underwriting_quote_tab_customer_quote_summary_total_comm;
	
	// customer base over rate

	@FindBy(xpath = "//*[@id='customerBaseOverRate']")
	private WebElement underwriting_quote_tab_configuration_configuration_customer_base_over_rate;

	// Customer interest rate
	@FindBy(xpath = "(//*[normalize-space()='Customer interest rate']//ancestor::div[1]//div//strong)[2]")
	private WebElement underwriting_quote_tab_configuration_configuration_customer_interest_rate;



	// Base Interest Rate
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Base interest rate']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_configuration_base_interest_rate;

	// Finance margin
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Finance margin']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_configuration_finance_margin;

	// Deductions
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Deductions']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_configuration_deductions;

	// Additional Margin
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Additional margin']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_configuration_additional_margin;

	// Total Margin
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Total margin']//ancestor::div[1]//p/strong")
	private WebElement underwriting_quote_tab_configuration_total_margin;

	// Margin Percentage
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Default broker margin']//ancestor::div[1]//div/div/label")
	private WebElement underwriting_quote_tab_configuration_default_broker_margin_percentage;

	// Maintenance Margin
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Maint. margin']//ancestor::div[1]//div//label/b")
	private WebElement underwriting_quote_tab_configuration_maintenance_margin;

	// Document Fee Margin
	@FindBy(xpath = "//app-acquisition-summary-configuration//*[normalize-space()='Document fee margin']//ancestor::div[1]//div//label/b")
	private WebElement underwriting_quote_tab_configuration_decument_fee_margin;

	// Base Input Rate Input
	@FindBy(xpath = "//input[@id='baseIntRate']")
	private WebElement underwriting_quote_tab_configuration_configuration_base_int_rate_input;

	// Maintenance Margin Input
	@FindBy(xpath = "//input[@id='MaintenanceMarginPer']")
	private WebElement underwriting_quote_tab_configuration_configuration_maintenance_margin_input;

	
	// default broker Margin Input
	@FindBy(xpath = "//input[@id='defaultBrokerMargin']")
	private WebElement underwriting_quote_tab_configuration_configuration_default_broker_margin;

	// Total tracker cost
	@FindBy(xpath = "//*[normalize-space()='Total tracker cost']//ancestor::div[1]//div//label/b")
	private WebElement underwriting_quote_tab_configuration_configuration_total_tracker_cost;

	// Insurance tax
	@FindBy(xpath = "//*[normalize-space()='Insurance tax']//ancestor::div[1]//div//label/b")
	private WebElement underwriting_quote_tab_configuration_configuration_insurance_tax;

	// Contingency insurance value
	@FindBy(xpath = "//*[normalize-space()='Contingency insurance value']//ancestor::div[1]//div//label/b")
	private WebElement underwriting_quote_tab_configuration_configuration_contingency_insurance_value;

	@FindBy(xpath = "//div[@class='row acquisition-menu']//div[3]//button[1]")
	private WebElement underwriting_quote_tab_configuration_save_button;



	Properties prop;

	public Underwriting() {
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

	public void verify_underwriting_menulink() throws InterruptedException {

		ExplicitWait.visibleElement(driver, underwriting_menu_link, 60);

		HelperClass.highlightElement(driver, underwriting_menu_link);

		underwriting_menu_link.click();
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		System.out.println("Click on Underwriting menu_link ");
		LO.print("Click on Underwriting menu_link ");

	}

	public void verify_underwriting_menulink_broker() throws InterruptedException {

		ExplicitWait.visibleElement(driver, underwriting_menu_link_broker, 120);

		HelperClass.highlightElement(driver, underwriting_menu_link_broker);
		
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].click();", underwriting_menu_link_broker);

		//underwriting_menu_link_broker.click();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		System.out.println("Click on Underwriting - broker tab ");
		LO.print("Click on Underwriting - broker tab ");

	}

	public void verify_underwriting_menulink_ownbook() throws InterruptedException {

		ExplicitWait.visibleElement(driver, underwriting_menu_link_ownbook, 60);

		// HelperClass.highlightElement(driver, underwriting_menu_link_ownbook);

		// underwriting_menu_link_ownbookclick();
		
		
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].click();", underwriting_menu_link_ownbook);
		
	//	underwriting_menu_link_ownbook.click();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		System.out.println("Click on Underwriting - ownbook tab ");
		LO.print("Click on Underwriting - ownbook tab ");

	}

	/*
	 * public String verify_underwriting_proposal_id() throws InterruptedException {
	 * 
	 * ExplicitWait.visibleElement(driver, underwriting_proposal_id, 20);
	 * 
	 * HelperClass.highlightElement(driver, underwriting_proposal_id);
	 * 
	 * String proposalID = underwriting_proposal_id.getText(); return proposalID;
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

	public void verify_underwriting_proposal_search_text_box()
			throws InterruptedException, IOException, ClassNotFoundException {

		ExplicitWait.visibleElement(driver, underwriting_Seach_text_box, 20);

		HelperClass.highlightElement(driver, underwriting_Seach_text_box);
		System.out.println("Click on Search text box - broker tab");
		LO.print("Click on Search text box - broker tab");
		Thread.sleep(6000);

		String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();

		obj_acq_listing_page = new AcquisitionListingPage();
		
		String sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);

		
		String UnderwritingPrposalId = GetExcelFormulaValue.get_cell_value(1, 3, sheetName);

		System.out.println("Getting the  proposal id from excel sheet  =" + UnderwritingPrposalId);
		LO.print("Getting the  proposal id from excel sheet =" + UnderwritingPrposalId);

		Thread.sleep(1000);

		ExplicitWait.visibleElement(driver, underwriting_Seach_text_box, 20);

		Click.sendKeys(driver, underwriting_Seach_text_box, UnderwritingPrposalId, 20);

		System.out.println("Enter the proposal in search text box =" + UnderwritingPrposalId);
		LO.print("Enter the proposal in search text box =" + UnderwritingPrposalId);

		underwriting_Seach_text_box.sendKeys(Keys.ENTER);

	}

	public void verify_underwriting_proposal_ownbook_hire_search_text_box() throws InterruptedException, IOException {

		ExplicitWait.visibleElement(driver, underwriting_Seach_text_box, 20);

		HelperClass.highlightElement(driver, underwriting_Seach_text_box);
		System.out.println("Click on Search text box - broker tab");
		LO.print("Click on Search text box - broker tab");
		Thread.sleep(6000);

		FileInputStream fis = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheet("HPNRBCHQuoteNo");// selecting sheet with its name as a parameter
		Thread.sleep(1000);
		// System.out.println("Taking the sheet name is =" + fis);
		System.out.println("Taking the sheet name is  =" + sheet.getSheetName());

		XSSFRow row = sheet.getRow(0);// read data from first row as 0th row contains header
		XSSFCell cell = row.getCell(1);// read data from first cell

		// System.out.println(cell);
		System.out.println(sheet.getRow(0).getCell(1));
		Thread.sleep(1000);
		String UnderwritingPrposalId = cell.getStringCellValue();

		System.out.println("Getting the  proposal id from excel sheet  =" + UnderwritingPrposalId);
		LO.print("Getting the  proposal id from excel sheet =" + UnderwritingPrposalId);

		Thread.sleep(1000);
//System.out.println(cellval);

		// String UnderwritingPrposalId =cell.getStringCellValue();

		// System.out.println("proposal id before send to search text box =" +
		// UnderwritingPrposalId);

		// Obj_Underwriting_Page = new Underwriting();

		ExplicitWait.visibleElement(driver, underwriting_Seach_text_box, 20);

		Click.sendKeys(driver, underwriting_Seach_text_box, UnderwritingPrposalId, 20);

		System.out.println("Enter the proposal in search text box =" + UnderwritingPrposalId);
		LO.print("Enter the proposal in search text box =" + UnderwritingPrposalId);

		underwriting_Seach_text_box.sendKeys(Keys.ENTER);

	}

	public void verify_underwriting_proposal_ownbook_purchase_search_text_box()
			throws InterruptedException, IOException {

		ExplicitWait.visibleElement(driver, underwriting_Seach_text_box, 20);

		HelperClass.highlightElement(driver, underwriting_Seach_text_box);
		System.out.println("Click on Search text box - broker tab");
		LO.print("Click on Search text box - broker tab");
		Thread.sleep(6000);

		FileInputStream fis = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheet("HPNR_HPNR_QuoteNo");// selecting sheet with its name as a parameter
		Thread.sleep(1000);
		// System.out.println("Taking the sheet name is =" + fis);
		System.out.println("Taking the sheet name is  =" + sheet.getSheetName());

		XSSFRow row = sheet.getRow(0);// read data from first row as 0th row contains header
		XSSFCell cell = row.getCell(1);// read data from first cell

		// System.out.println(cell);
		System.out.println(sheet.getRow(0).getCell(1));
		Thread.sleep(1000);
		String UnderwritingPrposalId = cell.getStringCellValue();

		System.out.println("Getting the  proposal id from excel sheet  =" + UnderwritingPrposalId);
		LO.print("Getting the  proposal id from excel sheet =" + UnderwritingPrposalId);

		Thread.sleep(1000);
//System.out.println(cellval);

		// String UnderwritingPrposalId =cell.getStringCellValue();

		// System.out.println("proposal id before send to search text box =" +
		// UnderwritingPrposalId);

		// Obj_Underwriting_Page = new Underwriting();

		ExplicitWait.visibleElement(driver, underwriting_Seach_text_box, 20);

		Click.sendKeys(driver, underwriting_Seach_text_box, UnderwritingPrposalId, 20);

		System.out.println("Enter the proposal in search text box =" + UnderwritingPrposalId);
		LO.print("Enter the proposal in search text box =" + UnderwritingPrposalId);

		underwriting_Seach_text_box.sendKeys(Keys.ENTER);

	}

	// Ownbook - search with Quote id - common in both Underwriting & Opp

	public void find_underwriting_listing_detail_listing_yes_option() throws InterruptedException {

		ExplicitWait.visibleElement(driver, underw_listing_yes_option, 20);

		HelperClass.highlightElement(driver, underw_listing_yes_option);

		Thread.sleep(2000);

		underw_listing_yes_option.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

	}

	public void find_underwriting_decision_yes_option() throws InterruptedException {

		ExplicitWait.visibleElement(driver, underw_listing_decision_yes_option, 20);

		HelperClass.highlightElement(driver, underw_listing_decision_yes_option);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		underw_listing_decision_yes_option.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

	}

	public void find_underwriting_listing_detail_for_proposal() throws InterruptedException {

		ExplicitWait.visibleElement(driver, proposal_detail_listing_data, 20);

		HelperClass.highlightElement(driver, proposal_detail_listing_data);

		Thread.sleep(2000);

		Actions actions = new Actions(driver);

		actions.doubleClick(proposal_detail_listing_data).perform();

		// Click.on(driver, proposal_detail_listing_data, 60);

		System.out.println("Click on Underwriting detail page for proposal");
		LO.print("Click on Underwriting detail page for proposal");
		
		Thread.sleep(2000);
		
		try {Click.on(driver, edit_underwriting_pop_up, 10);}
		catch(Exception e) {}
		

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

	}

	public boolean verify_quote_tab_on_underwriting_page_for_broker_hire_flow()
			throws InterruptedException, ClassNotFoundException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, underwriting_tab_quote, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		// HelperClass.highlightElement(driver, underwriting_tab_quote);

		Click.on(driver, underwriting_tab_quote, 60);

		System.out.println("Clicked on Underwriting quote page");
		LO.print("Clicked on Underwriting quote page");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_vehicle_heading, 120);

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_contract_type, 60);

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_button, 120);

		// Cliking on cust quote summary section
		Click.on(driver, underwriting_quote_tab_customer_quote_summary_button, 60);

		// waiting for otr section elements
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_otr_price, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_price_ex_vat_and_rfl, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_vat, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_rfl_and_frf, 120);

		// waiting for summary section elements
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_terms, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_miles_per_annum, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_monthly_finance_payment, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_funder_name, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_ref_number, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_exp_date, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_payment_profile, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_contract_mileage, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_initial_finance_rental, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_pence_per_excess_mile_finance, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_commission, 120);

		// getting otr section elements text

		double costPriceExVatAndRflActual = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_price_ex_vat_and_rfl.getText().trim().substring(2)));

		double otrVatActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_vat.getText().trim().substring(2)));

		double otrRflAndFrfActual = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_rfl_and_frf.getText().trim().substring(2)));

		double costOtrPriceActual = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_otr_price.getText().trim().substring(2)));

		// Vehicle details
		String vehicleNameActual = underwriting_quote_tab_vehicle_heading.getText().trim();

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_otr_price, 60);

		String quotRefNoActual = underwriting_quote_tab_ref_no.getText();

		// customer quote section
		// getting text from elements

		String contractTypeActual = underwriting_quote_tab_customer_contract_type.getText();

		double termActual = Double
				.parseDouble(underwriting_quote_tab_customer_underwriting_quote_tab_terms.getText().trim().substring(0, 2));

		double mileageActual = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_miles_per_annum.getText().trim()));

		double monthlyFinanceRentalActual = Double.parseDouble(RemoveComma
				.of(underwriting_quote_tab_customer_underwriting_quote_tab_initial_finance_rental.getText().trim().substring(2)));

		String funderNameActual = underwriting_quote_tab_customer_underwriting_quote_tab_funder_name.getText().trim();

		String funderQuoteRefNumberActual = underwriting_quote_tab_ref_number.getText().trim();

		String expiryDateActual = underwriting_quote_tab_exp_date.getText().trim();

		String paymentProfileActual = underwriting_quote_tab_customer_underwriting_quote_tab_payment_profile.getText().trim();

		double contractMileageActual = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_contract_mileage.getText().trim()));

		double initialFinanceRentalActual = Double.parseDouble(RemoveComma
				.of(underwriting_quote_tab_customer_underwriting_quote_tab_initial_finance_rental.getText().trim().substring(2)));

		String pencePerExcessMileFinanceActual = underwriting_quote_tab_customer_underwriting_quote_tab_pence_per_excess_mile_finance
				.getText().trim();

		double commissionActual = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_commission.getText().trim().substring(2)));

		String sheetName = "";

		if (Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName()
				.contains("business_hire")) {
			sheetName = prop.getProperty("BrokerBCHQuoteNo");
		}

		if (Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName()
				.contains("individual_hire")) {
			sheetName = prop.getProperty("BrokerPCHQuoteNo");
		}

		double costPriceExVatAndRflExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(1, 6, sheetName));
		double otrVatExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(1, 8, sheetName));
		double otrRflAndFrfExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(3, 6, sheetName));
		double costOtrPriceExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(3, 8, sheetName));

		String quotRefNoExpected = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
		String vehicleNameExpected = GetExcelFormulaValue.get_cell_value(1, 10, sheetName);

		String contractTypeExpected = GetExcelFormulaValue.get_cell_value(4, 1, sheetName);
		double termExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(4, 3, sheetName));

		double mileageExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(6, 1, sheetName));
		double monthlyFinanceRentalExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(6, 3, sheetName));

//		GetExcelFormulaValue.get_string_value(8, 1, sheetName);
//		GetExcelFormulaValue.get_string_value(8, 3, sheetName);

		String funderNameExpected = GetExcelFormulaValue.get_cell_value(10, 1, sheetName);
		String funderQuoteRefNumberExpected = GetExcelFormulaValue.get_cell_value(10, 3, sheetName);

		String expiryDateExpected = GetExcelFormulaValue.get_cell_value(12, 1, sheetName);
		String paymentProfileExpected = GetExcelFormulaValue.get_cell_value(12, 3, sheetName);

		double contractMileageExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(14, 1, sheetName));
		double initialFinanceRentalExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(14, 3, sheetName));

//		GetExcelFormulaValue.get_string_value(16, 1, sheetName);
//		GetExcelFormulaValue.get_string_value(16, 3, sheetName);

		String pencePerExcessMileFinanceExpected = GetExcelFormulaValue.get_cell_value(18, 1, sheetName);
//		GetExcelFormulaValue.get_string_value(18, 3, sheetName);

//		GetExcelFormulaValue.get_string_value(20, 1, sheetName);
		double commissionExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(20, 3, sheetName));

		// *******************************

		int count = 0;

		// comparing cost OTR price
		if (costOtrPriceActual == costOtrPriceExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costOtrPriceActual + " = " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " = " + costOtrPriceExpected);
			System.out.println("Cost Otr Price compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costOtrPriceActual + " != " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " != " + costOtrPriceExpected);
			System.err.println("Cost Otr Price compared but found not ok");
			LO.print("Cost Otr Price compared but found not ok");

		}

		// comparing cost Price Ex Vat And Rfl
		if (costPriceExVatAndRflActual == costPriceExVatAndRflExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			System.out.println("Cost Price Ex Vat And Rfl compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			System.err.println("Cost Price Ex Vat And Rfl compared but found not ok");
			LO.print("Cost Price Ex Vat And Rfl compared but found not ok");

		}

		// comparing cost Price Ex Vat And Rfl
		if (otrVatActual == otrVatExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrVatActual + " = " + otrVatExpected);
			LO.print(otrVatActual + " = " + otrVatExpected);
			System.out.println("Otr Vat compared and found ok");
			LO.print("Otr Vat compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrVatActual + " != " + otrVatExpected);
			LO.print(otrVatActual + " != " + otrVatExpected);
			System.err.println("Otr Vat compared but found not ok");
			LO.print("Otr Vat compared but found not ok");

		}

		// comparing Otr Rfl And Frf
		if (otrRflAndFrfActual == otrRflAndFrfExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			System.out.println("Otr Rfl And Frf compared and found ok");
			LO.print("Otr Rfl And Frf compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			System.err.println("Otr Rfl And Frf compared but found not ok");
			LO.print("Otr Rfl And Frf compared but found not ok");

		}

		// comparing quote no.
		if (quotRefNoActual.equals(quotRefNoExpected)) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(quotRefNoActual + " = " + quotRefNoExpected);
			LO.print(quotRefNoActual + " = " + quotRefNoExpected);
			System.out.println("Quote no. compared and found ok");
			LO.print("Quote no. compared and found ok");
		} else {
			System.out.println("");
			LO.print("");
			System.err.println(quotRefNoActual + " != " + quotRefNoExpected);
			LO.print(quotRefNoActual + " != " + quotRefNoExpected);
			System.err.println("Quote no. compared but found not ok");
			LO.print("Quote no. compared but found not ok");
		}

		// comparing vehicle name
		if (vehicleNameActual.equals(vehicleNameExpected)) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(vehicleNameActual + " = " + vehicleNameExpected);
			LO.print(vehicleNameActual + " = " + vehicleNameExpected);
			System.out.println("Vehicle name compared and found ok");
			LO.print("Vehicle name compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(vehicleNameActual + " != " + vehicleNameExpected);
			LO.print(vehicleNameActual + " != " + vehicleNameExpected);
			System.err.println("Vehicle name compared but found not ok");
			LO.print("Vehicle name compared but found not ok");

		}

		// comparing contract type
		if (contractTypeActual.equals(contractTypeExpected)) {
			count++;
			System.out.println("");
			LO.print("");
			System.out.println(contractTypeActual + " = " + contractTypeExpected);
			LO.print(contractTypeActual + " = " + contractTypeExpected);
			System.out.println("Contract type compared and found ok");
			LO.print("Contract type compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(contractTypeActual + " != " + contractTypeExpected);
			LO.print(contractTypeActual + " != " + contractTypeExpected);
			System.err.println("Contract type compared but found not ok");
			LO.print("Contract type compared but found not ok");
		}

		// comparing monthly finance payment
		if (monthlyFinanceRentalActual == monthlyFinanceRentalExpected) {

			count++;

			System.out.println("");
			LO.print("");
			System.out.println(monthlyFinanceRentalActual + " = " + monthlyFinanceRentalExpected);
			LO.print(monthlyFinanceRentalActual + " = " + monthlyFinanceRentalExpected);
			System.out.println("Monthly finance rental compared and found ok");
			LO.print("Monthly finance rental compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(monthlyFinanceRentalActual + " != " + monthlyFinanceRentalExpected);
			LO.print(monthlyFinanceRentalActual + " != " + monthlyFinanceRentalExpected);
			System.err.println("Monthly finance rental compared but found not ok");
			LO.print("Monthly finance rental compared but found not ok");

		}

		// comparing mileage
		if (mileageActual == mileageExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(mileageActual + " = " + mileageExpected);
			LO.print(mileageActual + " = " + mileageExpected);
			System.out.println("Mileage compared and found ok");
			LO.print("Mileage compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(mileageActual + " != " + mileageExpected);
			LO.print(mileageActual + " != " + mileageExpected);
			System.err.println("Mileage compared but found not ok");
			LO.print("Mileage compared but found not ok");

		}

		// comparing term
		if (termActual == termExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(termActual + " = " + termExpected);
			LO.print(termActual + " = " + termExpected);
			System.out.println("Terms compared and found ok");
			LO.print("Terms compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(termActual + " != " + termExpected);
			LO.print(termActual + " != " + termExpected);
			System.err.println("Terms compared but found not ok");
			LO.print("Terms compared but found not ok");

		}

		// comparing expiry date
		if (expiryDateActual.equals(expiryDateExpected)) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(expiryDateActual + " = " + expiryDateExpected);
			LO.print(expiryDateActual + " = " + expiryDateExpected);
			System.out.println("Expiry date compared and found ok");
			LO.print("Expiry date compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(expiryDateActual + " != " + expiryDateExpected);
			LO.print(expiryDateActual + " != " + expiryDateExpected);
			System.err.println("Expiry date compared but found not ok");
			LO.print("Expiry date compared but found not ok");

		}

		// comparing funder name
		if (funderNameActual.equals(funderNameExpected)) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(funderNameActual + " = " + funderNameExpected);
			LO.print(funderNameActual + " = " + funderNameExpected);
			System.out.println("Funder name compared and found ok");
			LO.print("Funder name compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(funderNameActual + " != " + funderNameExpected);
			LO.print(funderNameActual + " != " + funderNameExpected);
			System.err.println("Funder name compared but found not ok");
			LO.print("Funder name compared but found not ok");

		}

		// comparing funder quote ref no.
		if (funderQuoteRefNumberActual.equals(funderQuoteRefNumberExpected)) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(funderQuoteRefNumberActual + " = " + funderQuoteRefNumberExpected);
			LO.print(funderQuoteRefNumberActual + " = " + funderQuoteRefNumberExpected);
			System.out.println("Funder quote ref no. compared and found ok");
			LO.print("Funder quote ref no. compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(funderQuoteRefNumberActual + " != " + funderQuoteRefNumberExpected);
			LO.print(funderQuoteRefNumberActual + " != " + funderQuoteRefNumberExpected);
			System.err.println("Funder quote ref no. compared but found not ok");
			LO.print("Funder quote ref no. compared but found not ok");

		}

		// comparing payment profile
		if (paymentProfileActual.equals(paymentProfileExpected)) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(paymentProfileActual + " = " + paymentProfileExpected);
			LO.print(paymentProfileActual + " = " + paymentProfileExpected);
			System.out.println("Payment profile compared and found ok");
			LO.print("Payment profile compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(paymentProfileActual + " != " + paymentProfileExpected);
			LO.print(paymentProfileActual + " != " + paymentProfileExpected);
			System.err.println("Payment profile compared but found not ok");
			LO.print("Payment profile compared but found not ok");

		}

		// comparing contract mileage
		if (contractMileageActual == contractMileageExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(contractMileageActual + " = " + contractMileageExpected);
			LO.print(contractMileageActual + " = " + contractMileageExpected);
			System.out.println("Contract mileage compared and found ok");
			LO.print("Contract mileage compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(contractMileageActual + " != " + contractMileageExpected);
			LO.print(contractMileageActual + " != " + contractMileageExpected);
			System.err.println("Contract mileage compared but found not ok");
			LO.print("Contract mileage compared but found not ok");

		}

		// comparing Initial finance rental
		if (initialFinanceRentalActual == initialFinanceRentalExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(initialFinanceRentalActual + " = " + initialFinanceRentalExpected);
			LO.print(initialFinanceRentalActual + " = " + initialFinanceRentalExpected);
			System.out.println("Initial finance rental compared and found ok");
			LO.print("Initial finance rental compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(initialFinanceRentalActual + " != " + initialFinanceRentalExpected);
			LO.print(initialFinanceRentalActual + " != " + initialFinanceRentalExpected);
			System.err.println("Initial finance rental compared but found not ok");
			LO.print("Initial finance rental compared but found not ok");

		}

		// comparing pense per excess mile finance
		if (pencePerExcessMileFinanceActual.equals(pencePerExcessMileFinanceExpected)) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(pencePerExcessMileFinanceActual + " = " + pencePerExcessMileFinanceExpected);
			LO.print(pencePerExcessMileFinanceActual + " = " + pencePerExcessMileFinanceExpected);
			System.out.println("Pense per excess mile finance compared and found ok");
			LO.print("Pense per excess mile finance compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(pencePerExcessMileFinanceActual + " != " + pencePerExcessMileFinanceExpected);
			LO.print(pencePerExcessMileFinanceActual + " != " + pencePerExcessMileFinanceExpected);
			System.err.println("Pense per excess mile finance compared but found not ok");
			LO.print("Pense per excess mile finance compared but found not ok");

		}

		// comparing commission
		if (commissionActual == commissionExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(commissionActual + " = " + commissionExpected);
			LO.print(commissionActual + " = " + commissionExpected);
			System.out.println("commission compared and found ok");
			LO.print("commission compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(commissionActual + " != " + commissionExpected);
			LO.print(commissionActual + " != " + commissionExpected);
			System.err.println("commission compared but found not ok");
			LO.print("commission compared but found not ok");

		}

		boolean status = false;
		if (count == 14)

		{
			status = true;
		}

		return status;

	}

	
	
	public boolean verify_quote_tab_on_underwriting_page_for_ownbook_hire_funder_flow()
			throws InterruptedException, ClassNotFoundException, IOException {

		

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		// HelperClass.highlightElement(driver, underwriting_tab_quote);

		Click.on(driver, underwriting_tab_quote, 60);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		System.out.println("Clicked on Underwriting quote page");
		LO.print("Clicked on Underwriting quote page");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_vehicle_heading, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_contract_type, 60);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_button, 120);
		
		// waiting for otr section elements
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_otr_price, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_price_ex_vat_and_rfl, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_vat, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_rfl_and_frf, 120);		

		// Cliking on cust quote summary section
		Click.on(driver, underwriting_quote_tab_customer_quote_summary_button, 60);

		// waiting for summary section elements
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_term, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_miles, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_monthly_finance_rental, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_initial_finance_rental, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_followed_by, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_pence_per_excess_mile_finance, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_doc_fee, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_upsell, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_default_finance_commission, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_upsell_commission, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_doc_fee_commission, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_total_commission, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_referrer_commission, 20);

		Thread.sleep(3000);
		
		Click.on(driver, underwriting_quote_tab_configuration_heading_button, 30);
		
		//waiting for Configuration elements 
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_finance_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_deductions, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_additional_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_total_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_default_broker_margin_percentage, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_default_broker_margin_value, 20);		
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_broker_upsell_margin_percentage, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_broker_upsell_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_decument_fee_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_refferer_margin, 20);
		
		
		
		// getting otr section elements text

		double costPriceExVatAndRflActual = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_price_ex_vat_and_rfl.getText().trim().substring(2)));

		double otrVatActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_vat.getText().trim().substring(2)));

		double otrRflAndFrfActual = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_rfl_and_frf.getText().trim().substring(2)));

		double costOtrPriceActual = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_otr_price.getText().trim().substring(2)));
		
		//reading customer quote summary values from screen 
		
		double customer_quote_summary_terms = Double
				.parseDouble(underwriting_quote_tab_customer_quote_term.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_miles.getText().trim()));

		double customer_quote_summary_monthly_finance_rental = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		double customer_quote_initial_finance_rental = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_initial_finance_rental.getText().trim().substring(2)));


		double customer_payment_followed_by = Double
				.parseDouble(underwriting_quote_tab_customer_quote_followed_by.getText().substring(0, 2));

		double customer_quote_pence_per_excess_mile_finance = Double.parseDouble(
				underwriting_quote_tab_customer_quote_pence_per_excess_mile_finance.getText().trim().substring(0, 4));

		double customer_quote_summary_doc_fee = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_doc_fee.getText().trim().substring(2)));

		double customer_quote_summary_upsell = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_upsell.getText().trim().substring(2)));

		double customer_quote_summary_default_finance_commission = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_default_finance_commission.getText().trim().substring(2)));

		double customer_quote_summary_upsell_commission = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_upsell_commission.getText().trim().substring(2)));

		double customer_quote_summary_doc_fee_commission = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_doc_fee_commission.getText().trim().substring(2)));

		double customer_quote_summary_total_commision = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_total_commission.getText().trim().substring(2)));

		double customer_quote_summary_referrer_commision = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_referrer_commission.getText().trim().substring(2)));


		// reading configuration values from screen
		
		
		double financeMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_finance_margin.getText().trim().substring(2)));
		
		double deductionsFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_deductions.getText().trim().substring(2)));

		double additionalMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_additional_margin.getText().trim().substring(2)));

		double totalMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_total_margin.getText().trim().substring(2)));

		double defaultBrokerMarginPercentageFromScreen = Double.parseDouble(underwriting_quote_tab_default_broker_margin_percentage.getText().trim().substring(0, 4));

		double defaultBrokerMarginValueFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_default_broker_margin_value.getText().trim().substring(2)));
		
		double brokerUpsellMarginPercentageFromScreen = Double.parseDouble(underwriting_quote_tab_broker_upsell_margin_percentage.getText().trim().substring(0, 4));
	
		double brokerUpsellMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_broker_upsell_margin.getText().trim().substring(2)));

		double documentFeeMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_decument_fee_margin.getText().trim().substring(2)));

		double reffererMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_refferer_margin.getText().trim().substring(2)));

		
		// Vehicle details
		String vehicleNameActual = underwriting_quote_tab_vehicle_heading.getText().trim();

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_quote_ref_no, 30);

		String quotRefNoActual = underwriting_quote_tab_ref_no.getText();
		
		String contractTypeActual = underwriting_quote_tab_customer_contract_type.getText();
		
		
//*************************************************		
		
		
		//Getting calculation sheet name 
		
        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		obj_acq_listing_page = new AcquisitionListingPage();		
		String sheetName = obj_acq_listing_page.calculation_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		//Getting expected values from calculation sheet
		//OTR section elements		
		double costOtrPriceExpected = GetExcelFormulaValue.get_formula_value(14, 4, sheetName);
		double costPriceExVatAndRflExpected = GetExcelFormulaValue.get_formula_value(9, 9,
				sheetName);
		double otrVatExpected = GetExcelFormulaValue.get_formula_value(10, 4, sheetName);
		double otrRflAndFrfExpected = GetExcelFormulaValue.get_formula_value(7, 9, sheetName);		
		
		//getting expected values for cust quote summary section elements
		
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
	
		//getting expected values for config section elements
		
	
		double financeMarginFromExcel = GetExcelFormulaValue.get_formula_value(208, 3, sheetName);

		double deductionsFromExcel = GetExcelFormulaValue.get_formula_value(210, 1, sheetName);

		double additionalMarginFromExcel = GetExcelFormulaValue.get_formula_value(210, 3, sheetName);

		double totalMarginFromExcel = GetExcelFormulaValue.get_formula_value(212, 1, sheetName);

		double tempdefaultBrokerMarginPercentageFromExcel = GetExcelFormulaValue.get_formula_value(216, 4, sheetName);

		double defaultBrokerMarginPercentageFromExcel = (tempdefaultBrokerMarginPercentageFromExcel * 100);

		double defaultBrokerMarginValueFromExcel = GetExcelFormulaValue.get_formula_value(218, 1, sheetName);
		
		double tempbrokerUpsellMarginPercentageFromExcel = GetExcelFormulaValue.get_formula_value(218, 4, sheetName);

		double brokerUpsellMarginPercentageFromExcel = (tempbrokerUpsellMarginPercentageFromExcel * 100);

		double BrokerUpsellMarginFromExcel = GetExcelFormulaValue.get_formula_value(220, 1, sheetName);

		double documentFeeMarginFromExcel = GetExcelFormulaValue.get_formula_value(220, 4, sheetName);

		double reffererMarginFromExcel = GetExcelFormulaValue.get_formula_value(222, 1, sheetName);

		
		sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		String quotRefNoExpected = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
		String vehicleNameExpected = GetExcelFormulaValue.get_cell_value(1, 10, sheetName);

		String contractTypeExpected = GetExcelFormulaValue.get_cell_value(4, 1, sheetName);
		
		

		// *******************************

		int count = 0;

		// 1. comparing cost OTR price
		if (costOtrPriceActual == costOtrPriceExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costOtrPriceActual + " = " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " = " + costOtrPriceExpected);
			System.out.println("Cost Otr Price compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costOtrPriceActual + " != " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " != " + costOtrPriceExpected);
			System.err.println("Cost Otr Price compared but found not ok");
			LO.print("Cost Otr Price compared but found not ok");

		}

		// 2.comparing cost Price Ex Vat And Rfl
		if (costPriceExVatAndRflActual == costPriceExVatAndRflExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			System.out.println("Cost Price Ex Vat And Rfl compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			System.err.println("Cost Price Ex Vat And Rfl compared but found not ok");
			LO.print("Cost Price Ex Vat And Rfl compared but found not ok");

		}

		// 3.comparing cost Price Ex Vat And Rfl
		if (otrVatActual == otrVatExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrVatActual + " = " + otrVatExpected);
			LO.print(otrVatActual + " = " + otrVatExpected);
			System.out.println("Otr Vat compared and found ok");
			LO.print("Otr Vat compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrVatActual + " != " + otrVatExpected);
			LO.print(otrVatActual + " != " + otrVatExpected);
			System.err.println("Otr Vat compared but found not ok");
			LO.print("Otr Vat compared but found not ok");

		}

		// 4.comparing Otr Rfl And Frf
		if (otrRflAndFrfActual == otrRflAndFrfExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			System.out.println("Otr Rfl And Frf compared and found ok");
			LO.print("Otr Rfl And Frf compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			System.err.println("Otr Rfl And Frf compared but found not ok");
			LO.print("Otr Rfl And Frf compared but found not ok");

		}

	
		// 5.comparing quote no.
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

		// 6.comparing vehicle name
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

		// 7.comparing contract type
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

		
		// 8.comparing term
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

		
		// 9.comparing mileage
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
		
		// 10.comparing monthly finance rental
		
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

		
		//11.Comparing Initial Finance rental
		
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
		
		
		//12.Comparing followed By
		
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

		
		//13.Comparing Pence per excess mile finance
		
		
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
		
		
		// 14.Comparing Document Fee

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
		
		//15.Comparing Upsell

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

		
		//16.Comparing Default Finance Commission
		
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
		
		
		//17.Comparing Upsell Commission

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
		
		
		//18.Comparing Document Fee Commission
		
		
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
		
		
		//19.Comparing Total Commission	

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
		
		//20.Comparing Referrer Commission	

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
		

		//21. Comparing Finance Margin
		
		if (Difference.of_two_Double_Values(financeMarginFromScreen, financeMarginFromExcel) < 0.2) {
			count++;
	
			System.out.println("");
			LO.print          ("");
			System.out.println(financeMarginFromScreen + " = " + financeMarginFromExcel);
			LO.print          (financeMarginFromScreen + " = " + financeMarginFromExcel);

			LO.print("Finance Margin found OK");
			System.out.println("Finance Margin found OK");
			
		} else {

			System.out.println("");
			LO.print          ("");
			System.err.println(financeMarginFromScreen + " != " + financeMarginFromExcel);
			LO.print          (financeMarginFromScreen + " != " + financeMarginFromExcel);			

			
			LO.print("Finance Margin found wrong");
			System.err.println("Finance Margin found wrong");
		}

		
		//22. Comparing Deductions
		if (Difference.of_two_Double_Values(deductionsFromScreen, deductionsFromExcel) < 0.2) {
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(deductionsFromScreen + " = " + deductionsFromExcel);
			LO.print          (deductionsFromScreen + " = " + deductionsFromExcel);

			
			LO.print("Deductions found OK");
			System.out.println("Deductions found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(deductionsFromScreen + " != " + deductionsFromExcel);
			LO.print          (deductionsFromScreen + " != " + deductionsFromExcel);			
			
			LO.print("Deductions found wrong");
			System.err.println("Deductions found wrong");
		}

		//23. Comparing Additional Margin
		
		if (Difference.of_two_Double_Values(additionalMarginFromScreen, additionalMarginFromExcel) < 0.2) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(additionalMarginFromScreen + " = " + additionalMarginFromExcel);
			LO.print          (additionalMarginFromScreen + " = " + additionalMarginFromExcel);

			LO.print("Additional Margin found OK");
			System.out.println("Additional Margin found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(additionalMarginFromScreen + " != " + additionalMarginFromExcel);
			LO.print          (additionalMarginFromScreen + " != " + additionalMarginFromExcel);			
		
			LO.print("Additional Margin found wrong");
			System.err.println("Additional Margin found wrong");
		}

		
		//24. Comparing Total Margin
		
		if (Difference.of_two_Double_Values(totalMarginFromScreen, totalMarginFromExcel) < 0.2) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(totalMarginFromScreen + " = " + totalMarginFromExcel);
			LO.print          (totalMarginFromScreen + " = " + totalMarginFromExcel);

			
			LO.print("Total Margin found OK");
			System.out.println("Total Margin found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(totalMarginFromScreen + " != " + totalMarginFromExcel);
			LO.print          (totalMarginFromScreen + " != " + totalMarginFromExcel);			
	
			LO.print("Total Margin found wrong");
			System.err.println("Total Margin found wrong");
		}

		//25. Comparing Default Broker Margin percentage
		
		if (Difference.of_two_Double_Values(defaultBrokerMarginPercentageFromExcel,
				defaultBrokerMarginPercentageFromScreen) < 0.01) {
			
			count++;
		
			System.out.println("");
			LO.print          ("");
			System.out.println(defaultBrokerMarginPercentageFromScreen + " = " + defaultBrokerMarginPercentageFromExcel);
			LO.print          (defaultBrokerMarginPercentageFromScreen + " = " + defaultBrokerMarginPercentageFromExcel);
			
			
			LO.print("Default Broker Margin percentage found OK");
			System.out.println("Default Broker Margin percentage found OK");
			
		} else {
			
			
			System.out.println("");
			LO.print          ("");
			System.err.println(defaultBrokerMarginPercentageFromScreen + " != " + defaultBrokerMarginPercentageFromExcel);
			LO.print          (defaultBrokerMarginPercentageFromScreen + " != " + defaultBrokerMarginPercentageFromExcel);			
	
			LO.print("Default Broker Margin percentage found wrong");
			System.err.println("Default Broker Margin percentage found wrong");
		}

		
		//26. Comparing Default Broker Margin value
		
		if (Difference.of_two_Double_Values(defaultBrokerMarginValueFromExcel,
				defaultBrokerMarginValueFromScreen) < 0.2) {
			
			count++;
		
			System.out.println("");
			LO.print          ("");
			System.out.println(defaultBrokerMarginValueFromScreen + " = " + defaultBrokerMarginValueFromExcel);
			LO.print          (defaultBrokerMarginValueFromScreen + " = " + defaultBrokerMarginValueFromExcel);
			
			
			LO.print          ("Default Broker Margin Value found OK");
			System.out.println("Default Broker Margin Value found OK");
			
		} else {
			
			
			System.out.println("");
			LO.print          ("");
			System.err.println(defaultBrokerMarginValueFromScreen + " != " + defaultBrokerMarginValueFromExcel);
			LO.print          (defaultBrokerMarginValueFromScreen + " != " + defaultBrokerMarginValueFromExcel);			
	
			LO.print          ("Default Broker Margin Value found wrong");
			System.err.println("Default Broker Margin Value found wrong");
		}

		
		
		//27. Comparing Broker Upsell Margin percentage

		if (Difference.of_two_Double_Values(brokerUpsellMarginPercentageFromScreen,
				brokerUpsellMarginPercentageFromExcel) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(brokerUpsellMarginPercentageFromScreen + " = " + brokerUpsellMarginPercentageFromExcel);
			LO.print          (brokerUpsellMarginPercentageFromScreen + " = " + brokerUpsellMarginPercentageFromExcel);
	
			
			LO.print("Broker Upsell Margin percentage found OK");
			System.out.println("Broker Upsell Margin percentage found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(brokerUpsellMarginPercentageFromScreen + " != " + brokerUpsellMarginPercentageFromExcel);
			LO.print          (brokerUpsellMarginPercentageFromScreen + " != " + brokerUpsellMarginPercentageFromExcel);			
	
			LO.print("Broker Upsell Margin percentage found wrong");
			System.err.println("Broker Upsell Margin percentage found wrong");
		}
		
		//28. Comparing Broker Upsell Margin 

		if (Difference.of_two_Double_Values(brokerUpsellMarginFromScreen, BrokerUpsellMarginFromExcel) < 0.2) {
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(brokerUpsellMarginFromScreen + " = " + BrokerUpsellMarginFromExcel);
			LO.print          (brokerUpsellMarginFromScreen + " = " + BrokerUpsellMarginFromExcel);
				
			
			LO.print("Broker Upsell Margin  found OK");
			System.out.println("Broker Upsell Margin  found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(brokerUpsellMarginFromScreen + " != " + BrokerUpsellMarginFromExcel);
			LO.print          (brokerUpsellMarginFromScreen + " != " + BrokerUpsellMarginFromExcel);			
	
			LO.print("Broker Upsell Margin  found wrong");
			System.err.println("Broker Upsell Margin  found wrong");
		}
		
		
		//29. Comparing Document Fee Margin

		if (Difference.of_two_Double_Values(documentFeeMarginFromScreen, documentFeeMarginFromExcel) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(documentFeeMarginFromScreen + " = " + documentFeeMarginFromExcel);
			LO.print          (documentFeeMarginFromScreen + " = " + documentFeeMarginFromExcel);
		
			
			LO.print("Document Fee Margin  found OK");
			System.out.println("Document Fee Margin  found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(documentFeeMarginFromScreen + " != " + documentFeeMarginFromExcel);
			LO.print          (documentFeeMarginFromScreen + " != " + documentFeeMarginFromExcel);			
		
			LO.print("Document Fee Margin  found wrong");
			System.err.println("Document Fee Margin  found wrong");
		}

		//30. Comparing Broker Upsell Margin
		
		if (Difference.of_two_Double_Values(reffererMarginFromScreen, reffererMarginFromExcel) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(reffererMarginFromScreen + " = " + reffererMarginFromExcel);
			LO.print          (reffererMarginFromScreen + " = " + reffererMarginFromExcel);

			
			LO.print("Refferer Margin  found OK");
			System.out.println("Refferer Margin  found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(reffererMarginFromScreen + " != " + reffererMarginFromExcel);
			LO.print          (reffererMarginFromScreen + " != " + reffererMarginFromExcel);			
		
			
			LO.print("Refferer  Margin  found wrong");
			System.err.println("Refferer Margin  found wrong");
		}

		
		
		

		boolean status = false;
		if (count == 30)

		{
			status = true;
		}

		return status;

	}
	
	
	public boolean verify_quote_tab_on_underwriting_page_for_ownbook_hire_flow()
			throws InterruptedException, ClassNotFoundException, IOException {

	

		

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		// HelperClass.highlightElement(driver, underwriting_tab_quote);

		Click.on(driver, underwriting_tab_quote, 60);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		System.out.println("Clicked on Underwriting quote page");
		LO.print("Clicked on Underwriting quote page");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_vehicle_heading, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_contract_type, 60);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_button, 120);
		
		// waiting for otr section elements
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_otr_price, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_price_ex_vat_and_rfl, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_vat, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_rfl_and_frf, 120);		

		// Cliking on cust quote summary section
		Click.on(driver, underwriting_quote_tab_customer_quote_summary_button, 60);

		// waiting for summary section elements
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_term, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_miles, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_monthly_finance_rental, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_initial_finance_rental, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_followed_by, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_pence_per_excess_mile_finance, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_doc_fee, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_upsell, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_default_finance_commission, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_upsell_commission, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_doc_fee_commission, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_total_commission, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_referrer_commission, 20);

		Thread.sleep(10000);
		
		Click.on(driver, underwriting_quote_tab_configuration_heading_button, 30);
		
		//waiting for Configuration elements 
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_base_interest_rate, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_finance_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_deductions, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_additional_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_total_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_default_broker_margin_percentage, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_broker_upsell_margin_percentage, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_broker_upsell_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_decument_fee_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_refferer_margin, 20);
		
		
		
		// getting otr section elements text

		double costPriceExVatAndRflActual = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_price_ex_vat_and_rfl.getText().trim().substring(2)));

		double otrVatActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_vat.getText().trim().substring(2)));

		double otrRflAndFrfActual = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_rfl_and_frf.getText().trim().substring(2)));

		double costOtrPriceActual = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_otr_price.getText().trim().substring(2)));
		
		//reading customer quote summary values from screen 
		
		double customer_quote_summary_terms = Double
				.parseDouble(underwriting_quote_tab_customer_quote_term.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_miles.getText().trim()));

		double customer_quote_summary_monthly_finance_rental = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		double customer_quote_initial_finance_rental = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_initial_finance_rental.getText().trim().substring(2)));


		double customer_payment_followed_by = Double
				.parseDouble(underwriting_quote_tab_customer_quote_followed_by.getText().substring(0, 2));

		double customer_quote_pence_per_excess_mile_finance = Double.parseDouble(
				underwriting_quote_tab_customer_quote_pence_per_excess_mile_finance.getText().trim().substring(0, 4));

		double customer_quote_summary_doc_fee = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_doc_fee.getText().trim().substring(2)));

		double customer_quote_summary_upsell = Double
				.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_upsell.getText().trim().substring(2)));

		double customer_quote_summary_default_finance_commission = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_default_finance_commission.getText().trim().substring(2)));

		double customer_quote_summary_upsell_commission = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_upsell_commission.getText().trim().substring(2)));

		double customer_quote_summary_doc_fee_commission = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_doc_fee_commission.getText().trim().substring(2)));

		double customer_quote_summary_total_commision = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_total_commission.getText().trim().substring(2)));

		double customer_quote_summary_referrer_commision = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_referrer_commission.getText().trim().substring(2)));


		// reading configuration values from screen
		
		double baseInterestRateFromScreen = Double.parseDouble(underwriting_quote_tab_base_interest_rate.getText().trim().substring(0, 5));
		
		double financeMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_finance_margin.getText().trim().substring(2)));
		
		double deductionsFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_deductions.getText().trim().substring(2)));

		double additionalMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_additional_margin.getText().trim().substring(2)));

		double totalMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_total_margin.getText().trim().substring(2)));

		double defaultBrokerMarginPercentageFromScreen = Double.parseDouble(underwriting_quote_tab_default_broker_margin_percentage.getText().trim().substring(0, 4));

		double brokerUpsellMarginPercentageFromScreen = Double.parseDouble(underwriting_quote_tab_broker_upsell_margin_percentage.getText().trim().substring(0, 4));
	
		double brokerUpsellMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_broker_upsell_margin.getText().trim().substring(2)));

		double documentFeeMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_decument_fee_margin.getText().trim().substring(2)));

		double reffererMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_refferer_margin.getText().trim().substring(2)));

		
		// Vehicle details
		String vehicleNameActual = underwriting_quote_tab_vehicle_heading.getText().trim();

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_quote_ref_no, 30);

		String quotRefNoActual = underwriting_quote_tab_ref_no.getText();
		
		String contractTypeActual = underwriting_quote_tab_customer_contract_type.getText();
		
		
//*************************************************		
		
		
		//Getting calculation sheet name 
		
        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		obj_acq_listing_page = new AcquisitionListingPage();		
		String sheetName = obj_acq_listing_page.calculation_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		//Getting expected values from calculation sheet
		//OTR section elements		
		double costOtrPriceExpected = GetExcelFormulaValue.get_formula_value(14, 4, sheetName);
		double costPriceExVatAndRflExpected = GetExcelFormulaValue.get_formula_value(9, 9,
				sheetName);
		double otrVatExpected = GetExcelFormulaValue.get_formula_value(10, 4, sheetName);
		double otrRflAndFrfExpected = GetExcelFormulaValue.get_formula_value(7, 9, sheetName);		
		
		//getting expected values for cust quote summary section elements
		
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
	
		//getting expected values for config section elements
		
		double tempbaseInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(208, 1, sheetName);

		double baseInterestRateFromExcel = (tempbaseInterestRateFromExcel * 100);

		double financeMarginFromExcel = GetExcelFormulaValue.get_formula_value(208, 3, sheetName);

		double deductionsFromExcel = GetExcelFormulaValue.get_formula_value(210, 1, sheetName);

		double additionalMarginFromExcel = GetExcelFormulaValue.get_formula_value(210, 3, sheetName);

		double totalMarginFromExcel = GetExcelFormulaValue.get_formula_value(212, 1, sheetName);

		double tempdefaultBrokerMarginPercentageFromExcel = GetExcelFormulaValue.get_formula_value(216, 4, sheetName);

		double defaultBrokerMarginPercentageFromExcel = (tempdefaultBrokerMarginPercentageFromExcel * 100);
		
		double tempbrokerUpsellMarginPercentageFromExcel = GetExcelFormulaValue.get_formula_value(218, 4, sheetName);

		double brokerUpsellMarginPercentageFromExcel = (tempbrokerUpsellMarginPercentageFromExcel * 100);

		double BrokerUpsellMarginFromExcel = GetExcelFormulaValue.get_formula_value(220, 1, sheetName);

		double documentFeeMarginFromExcel = GetExcelFormulaValue.get_formula_value(220, 4, sheetName);

		double reffererMarginFromExcel = GetExcelFormulaValue.get_formula_value(222, 1, sheetName);

		
		sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		String quotRefNoExpected = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
		String vehicleNameExpected = GetExcelFormulaValue.get_cell_value(1, 10, sheetName);

		String contractTypeExpected = GetExcelFormulaValue.get_cell_value(4, 1, sheetName);
		
		

		// *******************************

		int count = 0;

		// 1. comparing cost OTR price
		if (costOtrPriceActual == costOtrPriceExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costOtrPriceActual + " = " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " = " + costOtrPriceExpected);
			System.out.println("Cost Otr Price compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costOtrPriceActual + " != " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " != " + costOtrPriceExpected);
			System.err.println("Cost Otr Price compared but found not ok");
			LO.print("Cost Otr Price compared but found not ok");

		}

		// 2.comparing cost Price Ex Vat And Rfl
		if (costPriceExVatAndRflActual == costPriceExVatAndRflExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			System.out.println("Cost Price Ex Vat And Rfl compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			System.err.println("Cost Price Ex Vat And Rfl compared but found not ok");
			LO.print("Cost Price Ex Vat And Rfl compared but found not ok");

		}

		// 3.comparing cost Price Ex Vat And Rfl
		if (otrVatActual == otrVatExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrVatActual + " = " + otrVatExpected);
			LO.print(otrVatActual + " = " + otrVatExpected);
			System.out.println("Otr Vat compared and found ok");
			LO.print("Otr Vat compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrVatActual + " != " + otrVatExpected);
			LO.print(otrVatActual + " != " + otrVatExpected);
			System.err.println("Otr Vat compared but found not ok");
			LO.print("Otr Vat compared but found not ok");

		}

		// 4.comparing Otr Rfl And Frf
		if (otrRflAndFrfActual == otrRflAndFrfExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			System.out.println("Otr Rfl And Frf compared and found ok");
			LO.print("Otr Rfl And Frf compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			System.err.println("Otr Rfl And Frf compared but found not ok");
			LO.print("Otr Rfl And Frf compared but found not ok");

		}

	
		// 5.comparing quote no.
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

		// 6.comparing vehicle name
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

		// 7.comparing contract type
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

		
		// 8.comparing term
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

		
		// 9.comparing mileage
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
		
		// 10.comparing monthly finance rental
		
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

		
		//11.Comparing Initial Finance rental
		
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
		
		
		//12.Comparing followed By
		
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

		
		//13.Comparing Pence per excess mile finance
		
		
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
		
		
		// 14.Comparing Document Fee

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
		
		//15.Comparing Upsell

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

		
		//16.Comparing Default Finance Commission
		
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
		
		
		//17.Comparing Upsell Commission

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
		
		
		//18.Comparing Document Fee Commission
		
		
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
		
		
		//19.Comparing Total Commission	

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
		
		//20.Comparing Referrer Commission	

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
		
		//21. Comparing Base Int Rate
		if (baseInterestRateFromExcel == baseInterestRateFromScreen) {
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(baseInterestRateFromScreen + " = " + baseInterestRateFromExcel);
			LO.print          (baseInterestRateFromScreen + " = " + baseInterestRateFromExcel);
				
			LO.print("Base Interest Rate found OK");
			System.out.println("Base Interest Rate found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(baseInterestRateFromScreen + " != " + baseInterestRateFromExcel);
			LO.print          (baseInterestRateFromScreen + " != " + baseInterestRateFromExcel);			

			LO.print("Base Interest Rate found wrong");
			System.err.println("Base Interest Rate found wrong");
		}

		//22. Comparing Finance Margin
		
		if (Difference.of_two_Double_Values(financeMarginFromScreen, financeMarginFromExcel) < 0.2) {
			count++;
	
			System.out.println("");
			LO.print          ("");
			System.out.println(financeMarginFromScreen + " = " + financeMarginFromExcel);
			LO.print          (financeMarginFromScreen + " = " + financeMarginFromExcel);

			LO.print("Finance Margin found OK");
			System.out.println("Finance Margin found OK");
			
		} else {

			System.out.println("");
			LO.print          ("");
			System.err.println(financeMarginFromScreen + " != " + financeMarginFromExcel);
			LO.print          (financeMarginFromScreen + " != " + financeMarginFromExcel);			

			
			LO.print("Finance Margin found wrong");
			System.err.println("Finance Margin found wrong");
		}

		
		//23. Comparing Deductions
		if (Difference.of_two_Double_Values(deductionsFromScreen, deductionsFromExcel) < 0.2) {
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(deductionsFromScreen + " = " + deductionsFromExcel);
			LO.print          (deductionsFromScreen + " = " + deductionsFromExcel);

			
			LO.print("Deductions found OK");
			System.out.println("Deductions found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(deductionsFromScreen + " != " + deductionsFromExcel);
			LO.print          (deductionsFromScreen + " != " + deductionsFromExcel);			
			
			LO.print("Deductions found wrong");
			System.err.println("Deductions found wrong");
		}

		//24. Comparing Additional Margin
		
		if (Difference.of_two_Double_Values(additionalMarginFromScreen, additionalMarginFromExcel) < 0.2) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(additionalMarginFromScreen + " = " + additionalMarginFromExcel);
			LO.print          (additionalMarginFromScreen + " = " + additionalMarginFromExcel);

			LO.print("Additional Margin found OK");
			System.out.println("Additional Margin found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(additionalMarginFromScreen + " != " + additionalMarginFromExcel);
			LO.print          (additionalMarginFromScreen + " != " + additionalMarginFromExcel);			
		
			LO.print("Additional Margin found wrong");
			System.err.println("Additional Margin found wrong");
		}

		
		//25. Comparing Total Margin
		
		if (Difference.of_two_Double_Values(totalMarginFromScreen, totalMarginFromExcel) < 0.2) {
			count++;

			System.out.println("");
			LO.print          ("");
			System.out.println(totalMarginFromScreen + " = " + totalMarginFromExcel);
			LO.print          (totalMarginFromScreen + " = " + totalMarginFromExcel);

			
			LO.print("Total Margin found OK");
			System.out.println("Total Margin found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(totalMarginFromScreen + " != " + totalMarginFromExcel);
			LO.print          (totalMarginFromScreen + " != " + totalMarginFromExcel);			
	
			LO.print("Total Margin found wrong");
			System.err.println("Total Margin found wrong");
		}

		//26. Comparing Default Broker Margin percentage
		
		if (Difference.of_two_Double_Values(defaultBrokerMarginPercentageFromExcel,
				defaultBrokerMarginPercentageFromScreen) < 0.01) {
			
			count++;
		
			System.out.println("");
			LO.print          ("");
			System.out.println(defaultBrokerMarginPercentageFromScreen + " = " + defaultBrokerMarginPercentageFromExcel);
			LO.print          (defaultBrokerMarginPercentageFromScreen + " = " + defaultBrokerMarginPercentageFromExcel);
			
			
			LO.print("Default Broker Margin percentage found OK");
			System.out.println("Default Broker Margin percentage found OK");
			
		} else {
			
			
			System.out.println("");
			LO.print          ("");
			System.err.println(defaultBrokerMarginPercentageFromScreen + " != " + defaultBrokerMarginPercentageFromExcel);
			LO.print          (defaultBrokerMarginPercentageFromScreen + " != " + defaultBrokerMarginPercentageFromExcel);			
	
			LO.print("Default Broker Margin percentage found wrong");
			System.err.println("Default Broker Margin percentage found wrong");
		}
		
		
		//27. Comparing Broker Upsell Margin percentage

		if (Difference.of_two_Double_Values(brokerUpsellMarginPercentageFromScreen,
				brokerUpsellMarginPercentageFromExcel) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(brokerUpsellMarginPercentageFromScreen + " = " + brokerUpsellMarginPercentageFromExcel);
			LO.print          (brokerUpsellMarginPercentageFromScreen + " = " + brokerUpsellMarginPercentageFromExcel);
	
			
			LO.print("Broker Upsell Margin percentage found OK");
			System.out.println("Broker Upsell Margin percentage found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(brokerUpsellMarginPercentageFromScreen + " != " + brokerUpsellMarginPercentageFromExcel);
			LO.print          (brokerUpsellMarginPercentageFromScreen + " != " + brokerUpsellMarginPercentageFromExcel);			
	
			LO.print("Broker Upsell Margin percentage found wrong");
			System.err.println("Broker Upsell Margin percentage found wrong");
		}
		
		//28. Comparing Broker Upsell Margin 

		if (Difference.of_two_Double_Values(brokerUpsellMarginFromScreen, BrokerUpsellMarginFromExcel) < 0.2) {
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(brokerUpsellMarginFromScreen + " = " + BrokerUpsellMarginFromExcel);
			LO.print          (brokerUpsellMarginFromScreen + " = " + BrokerUpsellMarginFromExcel);
				
			
			LO.print("Broker Upsell Margin  found OK");
			System.out.println("Broker Upsell Margin  found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(brokerUpsellMarginFromScreen + " != " + BrokerUpsellMarginFromExcel);
			LO.print          (brokerUpsellMarginFromScreen + " != " + BrokerUpsellMarginFromExcel);			
	
			LO.print("Broker Upsell Margin  found wrong");
			System.err.println("Broker Upsell Margin  found wrong");
		}
		
		
		//29. Comparing Document Fee Margin

		if (Difference.of_two_Double_Values(documentFeeMarginFromScreen, documentFeeMarginFromExcel) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(documentFeeMarginFromScreen + " = " + documentFeeMarginFromExcel);
			LO.print          (documentFeeMarginFromScreen + " = " + documentFeeMarginFromExcel);
		
			
			LO.print("Document Fee Margin  found OK");
			System.out.println("Document Fee Margin  found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(documentFeeMarginFromScreen + " != " + documentFeeMarginFromExcel);
			LO.print          (documentFeeMarginFromScreen + " != " + documentFeeMarginFromExcel);			
		
			LO.print("Document Fee Margin  found wrong");
			System.err.println("Document Fee Margin  found wrong");
		}

		//30. Comparing Broker Upsell Margin
		
		if (Difference.of_two_Double_Values(reffererMarginFromScreen, reffererMarginFromExcel) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			System.out.println(reffererMarginFromScreen + " = " + reffererMarginFromExcel);
			LO.print          (reffererMarginFromScreen + " = " + reffererMarginFromExcel);

			
			LO.print("Refferer Margin  found OK");
			System.out.println("Refferer Margin  found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			System.err.println(reffererMarginFromScreen + " != " + reffererMarginFromExcel);
			LO.print          (reffererMarginFromScreen + " != " + reffererMarginFromExcel);			
		
			
			LO.print("Refferer  Margin  found wrong");
			System.err.println("Refferer Margin  found wrong");
		}

		
		
		

		boolean status = false;
		if (count == 30)

		{
			status = true;
		}

		return status;

	}

	public boolean verify_quote_tab_on_underwriting_page_for_ownbook_business_purchase_flow()
			throws InterruptedException, ClassNotFoundException, IOException, UnsupportedFlavorException {

	

		

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		// HelperClass.highlightElement(driver, underwriting_tab_quote);

		Click.on(driver, underwriting_tab_quote, 60);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		System.out.println("Clicked on Underwriting quote page");
		LO.print("Clicked on Underwriting quote page");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_vehicle_heading, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_contract_type, 60);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_button, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_heading_button, 120);
		
		// waiting for otr section elements
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_otr_price, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_price_ex_vat_and_rfl, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_vat, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_rfl_and_frf, 120);		

		// Cliking on cust quote summary section
		Click.on(driver, underwriting_quote_tab_customer_quote_summary_button, 60);

		// waiting for cust quote summary section elements
		
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_terms, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_miles_per_annum, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_basic_cash_price, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_vat, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_non_vat_items, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_total_cash_price, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_order_deposit, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_finance_deposit, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_total_deposit, 20);
		try{ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_part_exchange_value, 20);}catch(Exception e) {}
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_balance_to_finance, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_finance_charges, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_document_fee, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_balance_payable, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_option_to_purchase_fee, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_initial_cash_payment, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_followed_by, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_monthly_finance_payment, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_balloon, 20);
		ExplicitWait.visibleElement(driver,	quote_summary_customer_quote_summary_final_payment_inc_option_to_purchase_fee, 20);
//		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_finance, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_vehicle_comm, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_default_finance_comm, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_document_fee_comm, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_total_comm, 20);

		Thread.sleep(5000);
		
		Click.on(driver, underwriting_quote_tab_configuration_heading_button, 30);
		
		//waiting for Configuration elements 
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_base_interest_rate, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_finance_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_deductions, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_additional_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_total_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_default_broker_margin_percentage, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_configuration_customer_interest_rate, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_decument_fee_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_configuration_default_broker_margin, 30);		
		
		
		// getting otr section elements text

		double costPriceExVatAndRflActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_price_ex_vat_and_rfl.getText().trim().substring(2)));

		double otrVatActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_vat.getText().trim().substring(2)));

		double otrRflAndFrfActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_rfl_and_frf.getText().trim().substring(2)));

		double costOtrPriceActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_otr_price.getText().trim().substring(2)));
		
		//reading customer quote summary values from screen 
		
		double customer_quote_summary_terms = Double.parseDouble(underwriting_quote_tab_customer_quote_summary_terms.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_miles_per_annum.getText().trim()));

		double customer_quote_summary_basic_cash_price = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_vat = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_vat.getText().trim().substring(2)));

		double customer_quote_summary_non_vat_items = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_non_vat_items.getText().trim().substring(2)));

		double customer_quote_summary_total_cash_price = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_total_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_order_deposit = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_order_deposit.getText().trim().substring(2)));

		double customer_quote_summary_finance_deposit = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_finance_deposit.getText().trim().substring(2)));

		double customer_quote_summary_total_deposit = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_total_deposit.getText().trim().substring(2)));

		double  customer_quote_summary_part_exchange_value =0;
		try{customer_quote_summary_part_exchange_value = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_summary_part_exchange_value.getText().trim().substring(2)));}catch(Exception e) {}
 
		double customer_quote_summary_balance_to_finance = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));

		double customer_quote_summary_finance_charges = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_finance_charges.getText().trim().substring(2)));

		double customer_quote_summary_document_fee = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_document_fee.getText().trim().substring(2)));

		double customer_quote_summary_balance_payable = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_balance_payable.getText().trim().substring(2)));

		double customer_quote_summary_option_to_purchase_fee = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));

		double customer_quote_summary_initial_cash_payment = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_initial_cash_payment.getText().trim().substring(2)));

		double customer_payment_followed_by = Double.parseDouble(underwriting_quote_tab_customer_quote_summary_followed_by.getText().substring(0, 2));

		double customer_quote_summary_monthly_finance_payment = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));

		double customer_quote_summary_balloon = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_balloon.getText().trim().substring(2)));

        double customer_quote_summary_final_payment_inc_option_to_purchase_fee = Double.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_final_payment_inc_option_to_purchase_fee.getText()
				.trim().substring(2)));

    //    double customer_quote_summary_pence_per_excess_mile_finance = Double.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_pence_per_excess_mile_finance.getText().trim().substring(0, 4)));
		
				

		double customer_quote_summary_vehicle_comm = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_vehicle_comm.getText().trim().substring(2)));

		double customer_quote_summary_default_finance_comm = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_default_finance_comm.getText().trim().substring(2)));

		double customer_quote_summary_document_fee_comm = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_document_fee_comm.getText().trim().substring(2)));

		double customer_quote_summary_total_commission = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_total_comm.getText().trim().substring(2)));


		// reading configuration values from screen
		
    	double baseInterestRateFromScreen = Double.parseDouble(underwriting_quote_tab_configuration_base_interest_rate.getText().trim().substring(0, 5));
		
		double financeMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_configuration_finance_margin.getText().trim().substring(2)));
		
		double deductionsFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_configuration_deductions.getText().trim().substring(2)));
		
		double additionalMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_configuration_additional_margin.getText().trim().substring(2)));
		
		double totalMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_configuration_total_margin.getText().trim().substring(2)));
		
		double defaultBrokerMarginPercentageFromScreen = Double.parseDouble(underwriting_quote_tab_configuration_default_broker_margin_percentage.getText().trim().substring(0, 4));
		
		double customerInterestRateFromScreen = Double.parseDouble(underwriting_quote_tab_configuration_configuration_customer_interest_rate.getText().trim().substring(0, 5));
		
		double documentFeeMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_configuration_decument_fee_margin.getText().trim().substring(2)));

		// copying default broker margin from input field	
		double default_broker_margin_copied = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_configuration_configuration_default_broker_margin.getAttribute("value")));

    	// Vehicle details
		String vehicleNameActual = underwriting_quote_tab_vehicle_heading.getText().trim();

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_quote_ref_no, 30);

		String quotRefNoActual = underwriting_quote_tab_ref_no.getText();
		
		String contractTypeActual = underwriting_quote_tab_customer_contract_type.getText();
		
		
        //*************************************************		
		
		
		//Getting calculation sheet name 
		
        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		obj_acq_listing_page = new AcquisitionListingPage();		
		String sheetName = obj_acq_listing_page.calculation_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		//Getting expected values from calculation sheet
		//OTR section elements		
		double costOtrPriceExpected = GetExcelFormulaValue.get_formula_value(14, 7, sheetName);
		double costPriceExVatAndRflExpected = GetExcelFormulaValue.get_formula_value(9, 12,
				sheetName);
		double otrVatExpected = GetExcelFormulaValue.get_formula_value(10, 7, sheetName);
		double otrRflAndFrfExpected = GetExcelFormulaValue.get_formula_value(7, 12, sheetName);	
		
	
		//getting expected values for cust quote summary section elements
		
			
		// getting values from excel

		double terms = GetExcelFormulaValue.get_formula_value(208, 1, sheetName);
		double miles = GetExcelFormulaValue.get_formula_value(208, 4, sheetName);

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

		//getting expected values for config section elements
		
		double tempbaseInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(256, 01, sheetName);
		double baseInterestRateFromExcel = (tempbaseInterestRateFromExcel * 100);

		double financeMarginFromExcel = GetExcelFormulaValue.get_formula_value(259, 5, sheetName);

		double deductionsFromExcel = GetExcelFormulaValue.get_formula_value(253, 5, sheetName);

		double additionalMarginFromExcel = GetExcelFormulaValue.get_formula_value(254, 1, sheetName);
		double totalMarginFromExcel = GetExcelFormulaValue.get_formula_value(254, 5, sheetName);

		double tempdefaultBrokerMarginPercentageFromExcel = GetExcelFormulaValue.get_formula_value(260, 1, sheetName);

		double defaultBrokerMarginPercentageFromExcel = (tempdefaultBrokerMarginPercentageFromExcel * 100);

		double tempcustomerInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(259, 1, sheetName);
		double customerInterestRateFromExcel = (tempcustomerInterestRateFromExcel * 100);

		double documentFeeMarginFromExcel = GetExcelFormulaValue.get_formula_value(262, 1, sheetName);

		double defaultBrokerMarginFromExcel = GetExcelFormulaValue.get_formula_value(260, 5, sheetName);

		
		sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		String quotRefNoExpected = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
		String vehicleNameExpected = GetExcelFormulaValue.get_cell_value(1, 10, sheetName);

		String contractTypeExpected = GetExcelFormulaValue.get_cell_value(4, 1, sheetName);
		
		

		// *******************************

		int count = 0;

		// 1. comparing cost OTR price
		if (costOtrPriceActual == costOtrPriceExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costOtrPriceActual + " = " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " = " + costOtrPriceExpected);
			System.out.println("Cost Otr Price compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costOtrPriceActual + " != " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " != " + costOtrPriceExpected);
			System.err.println("Cost Otr Price compared but found not ok");
			LO.print("Cost Otr Price compared but found not ok");

		}

		// 2.comparing cost Price Ex Vat And Rfl
		if (costPriceExVatAndRflActual == costPriceExVatAndRflExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			System.out.println("Cost Price Ex Vat And Rfl compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			System.err.println("Cost Price Ex Vat And Rfl compared but found not ok");
			LO.print("Cost Price Ex Vat And Rfl compared but found not ok");

		}

		// 3.comparing cost Price Ex Vat And Rfl
		if (otrVatActual == otrVatExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrVatActual + " = " + otrVatExpected);
			LO.print(otrVatActual + " = " + otrVatExpected);
			System.out.println("Otr Vat compared and found ok");
			LO.print("Otr Vat compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrVatActual + " != " + otrVatExpected);
			LO.print(otrVatActual + " != " + otrVatExpected);
			System.err.println("Otr Vat compared but found not ok");
			LO.print("Otr Vat compared but found not ok");

		}

		// 4.comparing Otr Rfl And Frf
		if (otrRflAndFrfActual == otrRflAndFrfExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			System.out.println("Otr Rfl And Frf compared and found ok");
			LO.print("Otr Rfl And Frf compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			System.err.println("Otr Rfl And Frf compared but found not ok");
			LO.print("Otr Rfl And Frf compared but found not ok");

		}

	
		// 5.comparing quote no.
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

		// 6.comparing vehicle name
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

		// 7.comparing contract type
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

		
		// 8.comparing term
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

		
		// 9.comparing mileage
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
		
	// 10.comparing Basic Cash Price
		
		
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

		// 11.comparing VAT
		
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

		// 12.comparing non vat items
		
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

		// 13.comparing Total Cash Price
		
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

		// 14.comparing Order Deposit
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

		// 15.comparing Finance Deposit
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

		// 16.comparing Total Deposit
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

		// 17.comparing Part Exchange Value
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

		// 18.comparing Balance to Finance
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

		// 19.comparing Finance Charges	
		
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

		// 20.comparing Document Fee
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

		// 21.comparing Balance Payable
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

		// 22.comparing Option To Purchase Fee
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

		// 23.comparing Initial Cash Payment
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

		// 24.comparing Followed By months
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

		// 25.comparing Monthly Finance Payment
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

		// 26.comparing Balloon Value
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

		// 27.comparing Final Payment
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
		

		// 28.comparing Vehicle Commission	
		
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

		// 29.comparing Default Finance Commission
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

		// 30.comparing Document Fee Commission
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

		// 31.comparing Total Commission
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
		
		
		// 32.comparing Base Interest Rate
		if (Difference.of_two_Double_Values(baseInterestRateFromExcel , baseInterestRateFromScreen)<0.05) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");	
			
			System.out.println(baseInterestRateFromScreen + " = " + baseInterestRateFromExcel);
			LO.print          (baseInterestRateFromScreen + " = " + baseInterestRateFromExcel);	
			
			LO.print          ("Base Interest Rate found OK");
			System.out.println("Base Interest Rate found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(baseInterestRateFromScreen + " != " + baseInterestRateFromExcel);
			LO.print          (baseInterestRateFromScreen + " != " + baseInterestRateFromExcel);
		
			
			LO.print          ("Base Interest Rate found wrong");
			System.err.println("Base Interest Rate found wrong");
		}

		// 33.comparing Finance Margin
		if (Difference.of_two_Double_Values(financeMarginFromScreen, financeMarginFromExcel) < 0.2) {
			
			count++;

			System.out.println("");
			LO.print          ("");	
			
			System.out.println(financeMarginFromScreen + " = " + financeMarginFromExcel);
			LO.print          (financeMarginFromScreen + " = " + financeMarginFromExcel);	
			
			LO.print          ("Finance Margin found OK");
			System.out.println("Finance Margin found OK");
			
		} else {
			System.out.println("");
			LO.print          ("");	
			
			System.err.println(financeMarginFromScreen + " != " + financeMarginFromExcel);
			LO.print          (financeMarginFromScreen + " != " + financeMarginFromExcel);
			
			LO.print          ("Finance Margin found wrong");
			System.err.println("Finance Margin found wrong");
		}

		// 34.comparing Deductions
		if (Difference.of_two_Double_Values(deductionsFromScreen, deductionsFromExcel) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(deductionsFromScreen + " = " + deductionsFromExcel);
			LO.print          (deductionsFromScreen + " = " + deductionsFromExcel);		
			
			LO.print          ("Deductions found OK");
			System.out.println("Deductions found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(deductionsFromScreen + " != " + deductionsFromExcel);
			LO.print          (deductionsFromScreen + " != " + deductionsFromExcel);
			
			LO.print          ("Deductions found wrong");
			System.err.println("Deductions found wrong");
		}

		// 35.comparing Additional Margin
		if (Difference.of_two_Double_Values(additionalMarginFromScreen, additionalMarginFromExcel) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(additionalMarginFromScreen + " = " + additionalMarginFromExcel);
			LO.print          (additionalMarginFromScreen + " = " + additionalMarginFromExcel);		
		
			
			LO.print          ("Additional Margin found OK");
			System.out.println("Additional Margin found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(additionalMarginFromScreen + " != " + additionalMarginFromExcel);
			LO.print          (additionalMarginFromScreen + " != " + additionalMarginFromExcel);
			
			LO.print          ("Additional Margin found wrong");
			System.err.println("Additional Margin found wrong");
		}

		// 36.comparing Total Margin
		if (Difference.of_two_Double_Values(totalMarginFromScreen, totalMarginFromExcel) < 0.2) {
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(totalMarginFromScreen + " = " + totalMarginFromExcel);
			LO.print          (totalMarginFromScreen + " = " + totalMarginFromExcel);
			
			LO.print          ("Total Margin found OK");
			System.out.println("Total Margin found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");

			System.err.println(totalMarginFromScreen + " != " + totalMarginFromExcel);
			LO.print          (totalMarginFromScreen + " != " + totalMarginFromExcel);

			
			LO.print          ("Total Margin found wrong");
			System.err.println("Total Margin found wrong");
		}

		// 37.comparing Default Broker Margin percentage
		if (Difference.of_two_Double_Values(defaultBrokerMarginPercentageFromExcel , defaultBrokerMarginPercentageFromScreen)<0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(defaultBrokerMarginPercentageFromScreen + " = " + defaultBrokerMarginPercentageFromExcel);
			LO.print          (defaultBrokerMarginPercentageFromScreen + " = " + defaultBrokerMarginPercentageFromExcel);
			
			LO.print          ("Default Broker Margin percentage found OK");
			System.out.println("Default Broker Margin percentage found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(defaultBrokerMarginPercentageFromScreen + " != " + defaultBrokerMarginPercentageFromExcel);
			LO.print          (defaultBrokerMarginPercentageFromScreen + " != " + defaultBrokerMarginPercentageFromExcel);
			
			LO.print          ("Default Broker Margin percentage found wrong");
			System.err.println("Default Broker Margin percentage found wrong");
		}

		// 38.comparing Customer Interest Rate
		if (Difference.of_two_Double_Values(customerInterestRateFromScreen , customerInterestRateFromExcel)<0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customerInterestRateFromScreen + " = " + customerInterestRateFromExcel);
			LO.print          (customerInterestRateFromScreen + " = " + customerInterestRateFromExcel);
		
			LO.print          ("Customer Interest Rate found OK");
			System.out.println("Customer Interest Rate found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customerInterestRateFromScreen + " != " + customerInterestRateFromExcel);
			LO.print          (customerInterestRateFromScreen + " != " + customerInterestRateFromExcel);

			
			LO.print          ("Customer Interest Rate found wrong");
			System.err.println("Customer Interest Rate found wrong");
		}

		// 39.comparing Document Fee
		if (Difference.of_two_Double_Values(documentFeeMarginFromScreen, documentFeeMarginFromExcel) < 0.2) {
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(documentFeeMarginFromScreen + " = " + documentFeeMarginFromExcel);
			LO.print          (documentFeeMarginFromScreen + " = " + documentFeeMarginFromExcel);
				
			LO.print          ("Document Fee Margin found OK");
			System.out.println("Document Fee Margin found OK");
			
		} else {
			System.out.println("");
			LO.print          ("");
			
			System.err.println(documentFeeMarginFromScreen + " != " + documentFeeMarginFromExcel);
			LO.print          (documentFeeMarginFromScreen + " != " + documentFeeMarginFromExcel);

			
			LO.print          ("Document Fee Margin  found wrong");
			System.err.println("Document Fee Margin  found wrong");
		}
		// 40.comparing Default Broker Margin
		if ((Difference.of_two_Double_Values(default_broker_margin_copied, defaultBrokerMarginFromExcel) < 0.2)) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(default_broker_margin_copied + " = " + defaultBrokerMarginFromExcel);
			LO.print          (default_broker_margin_copied + " = " + defaultBrokerMarginFromExcel);
			
			LO.print          ("Default Broker Margin found OK");
			System.out.println("Default Broker Margin found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(default_broker_margin_copied + " != " + defaultBrokerMarginFromExcel);
			LO.print          (default_broker_margin_copied + " != " + defaultBrokerMarginFromExcel);
			
			LO.print          ("Default Broker Margin  found wrong");
			System.err.println("Default Broker Margin  found wrong");
		}
		
		
		
		int expcount=0;
if(Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName().contains("funder"))
{ expcount=39;} else {expcount=40;}

		boolean status = false;
		if (count == expcount)

		{
			status = true;
			
	        // ANSI escape code for green color
	        String ansiGreen = "\u001B[32m";
	        
	        // ANSI escape code to reset the console color
	        String ansiReset = "\u001B[0m";
			
			System.out.println("");
			LO.print          ("");
			LO.print          (ansiGreen+"All values on underwriting quote tab verified successfully"+ansiReset);
			System.out.println(ansiGreen+"All values on underwriting quote tab verified successfully"+ansiReset);
			System.out.println("");
			LO.print          ("");
			
		}else
		{
			System.out.println("");
			LO.print          ("");
			LO.print          ("One or More than One values on underwriting quote tab may be wrong , please check all above values printed on console");
			System.err.println("One or More than One values on underwriting quote tab may be wrong , please check all above values printed on console");
			System.out.println("");
			LO.print          ("");
		}

		return status;

	}

	public boolean verify_quote_tab_on_underwriting_page_for_ownbook_individual_purchase_flow()
			throws InterruptedException, ClassNotFoundException, IOException, UnsupportedFlavorException {

	

		

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		// HelperClass.highlightElement(driver, underwriting_tab_quote);

		Click.on(driver, underwriting_tab_quote, 60);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		System.out.println("Clicked on Underwriting quote page");
		LO.print("Clicked on Underwriting quote page");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_vehicle_heading, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_contract_type, 60);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_button, 120);
		
		// waiting for otr section elements
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_otr_price, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_price_ex_vat_and_rfl, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_vat, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_rfl_and_frf, 120);		

		// Cliking on cust quote summary section
		Click.on(driver, underwriting_quote_tab_customer_quote_summary_button, 60);

		// waiting for cust quote summary section elements
		
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_terms, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_miles_per_annum, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_basic_cash_price, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_vat, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_non_vat_items, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_total_cash_price, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_order_deposit, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_finance_deposit, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_total_deposit, 20);
		try{ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_part_exchange_value, 20);}catch(Exception e) {}
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_balance_to_finance, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_finance_charges, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_document_fee, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_balance_payable, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_option_to_purchase_fee, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_initial_cash_payment, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_followed_by, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_monthly_finance_payment, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_guaranteed_future_value, 20);
		ExplicitWait.visibleElement(driver,	quote_summary_customer_quote_summary_final_payment_inc_option_to_purchase_fee, 20);
		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_finance, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_vehicle_comm, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_default_finance_comm, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_document_fee_comm, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_total_comm, 20);

		Thread.sleep(5000);
		
		Click.on(driver, underwriting_quote_tab_configuration_heading_button, 30);
		
		//waiting for Configuration elements 
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_base_interest_rate, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_finance_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_deductions, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_additional_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_total_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_default_broker_margin_percentage, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_configuration_customer_interest_rate, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_decument_fee_margin, 20);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_configuration_configuration_default_broker_margin, 30);		
		
		
		// getting otr section elements text

		double costPriceExVatAndRflActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_price_ex_vat_and_rfl.getText().trim().substring(2)));

		double otrVatActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_vat.getText().trim().substring(2)));

		double otrRflAndFrfActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_rfl_and_frf.getText().trim().substring(2)));

		double costOtrPriceActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_otr_price.getText().trim().substring(2)));
		
		//reading customer quote summary values from screen 
		
		double customer_quote_summary_terms = Double.parseDouble(underwriting_quote_tab_customer_quote_summary_terms.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_miles_per_annum.getText().trim()));

		double customer_quote_summary_basic_cash_price = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_vat = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_vat.getText().trim().substring(2)));

		double customer_quote_summary_non_vat_items = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_non_vat_items.getText().trim().substring(2)));

		double customer_quote_summary_total_cash_price = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_total_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_order_deposit = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_order_deposit.getText().trim().substring(2)));

		double customer_quote_summary_finance_deposit = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_finance_deposit.getText().trim().substring(2)));

		double customer_quote_summary_total_deposit = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_total_deposit.getText().trim().substring(2)));

		double  customer_quote_summary_part_exchange_value =0;
		try{customer_quote_summary_part_exchange_value = Double.parseDouble(
				RemoveComma.of(underwriting_quote_tab_customer_quote_summary_part_exchange_value.getText().trim().substring(2)));}catch(Exception e) {}
 
		double customer_quote_summary_balance_to_finance = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));

		double customer_quote_summary_finance_charges = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_finance_charges.getText().trim().substring(2)));

		double customer_quote_summary_document_fee = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_document_fee.getText().trim().substring(2)));

		double customer_quote_summary_balance_payable = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_balance_payable.getText().trim().substring(2)));

		double customer_quote_summary_option_to_purchase_fee = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));

		double customer_quote_summary_initial_cash_payment = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_initial_cash_payment.getText().trim().substring(2)));

		double customer_payment_followed_by = Double.parseDouble(underwriting_quote_tab_customer_quote_summary_followed_by.getText().substring(0, 2));

		double customer_quote_summary_monthly_finance_payment = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));

		double customer_quote_summary_balloon = Double.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_guaranteed_future_value.getText().trim().substring(2)));

        double customer_quote_summary_final_payment_inc_option_to_purchase_fee = Double.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_final_payment_inc_option_to_purchase_fee.getText()
				.trim().substring(2)));

        double customer_quote_summary_pence_per_excess_mile_finance = Double.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_pence_per_excess_mile_finance.getText().trim().substring(0, 4)));
		
				

		double customer_quote_summary_vehicle_comm = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_vehicle_comm.getText().trim().substring(2)));

		double customer_quote_summary_default_finance_comm = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_default_finance_comm.getText().trim().substring(2)));

		double customer_quote_summary_document_fee_comm = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_document_fee_comm.getText().trim().substring(2)));

		double customer_quote_summary_total_commission = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_quote_summary_total_comm.getText().trim().substring(2)));


		// reading configuration values from screen
		
    	double baseInterestRateFromScreen = Double.parseDouble(underwriting_quote_tab_configuration_base_interest_rate.getText().trim().substring(0, 5));
		
		double financeMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_configuration_finance_margin.getText().trim().substring(2)));
		
		double deductionsFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_configuration_deductions.getText().trim().substring(2)));
		
		double additionalMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_configuration_additional_margin.getText().trim().substring(2)));
		
		double totalMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_configuration_total_margin.getText().trim().substring(2)));
		
		double defaultBrokerMarginPercentageFromScreen = Double.parseDouble(underwriting_quote_tab_configuration_default_broker_margin_percentage.getText().trim().substring(0, 4));
		
		double customerInterestRateFromScreen = Double.parseDouble(underwriting_quote_tab_configuration_configuration_customer_interest_rate.getText().trim().substring(0, 5));
		
		double documentFeeMarginFromScreen = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_configuration_decument_fee_margin.getText().trim().substring(2)));

		// copying default broker margin from input field	
		double default_broker_margin_copied = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_configuration_configuration_default_broker_margin.getAttribute("value")));

    	// Vehicle details
		String vehicleNameActual = underwriting_quote_tab_vehicle_heading.getText().trim();

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_quote_ref_no, 30);

		String quotRefNoActual = underwriting_quote_tab_ref_no.getText();
		
		String contractTypeActual = underwriting_quote_tab_customer_contract_type.getText();
		
		
        //*************************************************		
		
		
		//Getting calculation sheet name 
		
        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		obj_acq_listing_page = new AcquisitionListingPage();		
		String sheetName = obj_acq_listing_page.calculation_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		//Getting expected values from calculation sheet
		//OTR section elements		
		double costOtrPriceExpected = GetExcelFormulaValue.get_formula_value(14, 7, sheetName);
		double costPriceExVatAndRflExpected = GetExcelFormulaValue.get_formula_value(9, 12,
				sheetName);
		double otrVatExpected = GetExcelFormulaValue.get_formula_value(10, 7, sheetName);
		double otrRflAndFrfExpected = GetExcelFormulaValue.get_formula_value(7, 12, sheetName);	
		
	
		//getting expected values for cust quote summary section elements
		
			
		// getting values from excel

		double terms = GetExcelFormulaValue.get_formula_value(208, 1, sheetName);
		double miles = GetExcelFormulaValue.get_formula_value(208, 4, sheetName);

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

		//getting expected values for config section elements
		
		double tempbaseInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(256, 01, sheetName);
		double baseInterestRateFromExcel = (tempbaseInterestRateFromExcel * 100);

		double financeMarginFromExcel = GetExcelFormulaValue.get_formula_value(259, 5, sheetName);

		double deductionsFromExcel = GetExcelFormulaValue.get_formula_value(253, 5, sheetName);

		double additionalMarginFromExcel = GetExcelFormulaValue.get_formula_value(254, 1, sheetName);
		double totalMarginFromExcel = GetExcelFormulaValue.get_formula_value(254, 5, sheetName);

		double tempdefaultBrokerMarginPercentageFromExcel = GetExcelFormulaValue.get_formula_value(260, 1, sheetName);

		double defaultBrokerMarginPercentageFromExcel = (tempdefaultBrokerMarginPercentageFromExcel * 100);

		double tempcustomerInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(259, 1, sheetName);
		double customerInterestRateFromExcel = (tempcustomerInterestRateFromExcel * 100);

		double documentFeeMarginFromExcel = GetExcelFormulaValue.get_formula_value(262, 1, sheetName);

		double defaultBrokerMarginFromExcel = GetExcelFormulaValue.get_formula_value(260, 5, sheetName);

		
		sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		String quotRefNoExpected = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
		String vehicleNameExpected = GetExcelFormulaValue.get_cell_value(1, 10, sheetName);

		String contractTypeExpected = GetExcelFormulaValue.get_cell_value(4, 1, sheetName);
		
		

		// *******************************

		int count = 0;

		// 1. comparing cost OTR price
		if (costOtrPriceActual == costOtrPriceExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costOtrPriceActual + " = " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " = " + costOtrPriceExpected);
			System.out.println("Cost Otr Price compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costOtrPriceActual + " != " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " != " + costOtrPriceExpected);
			System.err.println("Cost Otr Price compared but found not ok");
			LO.print("Cost Otr Price compared but found not ok");

		}

		// 2.comparing cost Price Ex Vat And Rfl
		if (costPriceExVatAndRflActual == costPriceExVatAndRflExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			System.out.println("Cost Price Ex Vat And Rfl compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			System.err.println("Cost Price Ex Vat And Rfl compared but found not ok");
			LO.print("Cost Price Ex Vat And Rfl compared but found not ok");

		}

		// 3.comparing cost Price Ex Vat And Rfl
		if (otrVatActual == otrVatExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrVatActual + " = " + otrVatExpected);
			LO.print(otrVatActual + " = " + otrVatExpected);
			System.out.println("Otr Vat compared and found ok");
			LO.print("Otr Vat compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrVatActual + " != " + otrVatExpected);
			LO.print(otrVatActual + " != " + otrVatExpected);
			System.err.println("Otr Vat compared but found not ok");
			LO.print("Otr Vat compared but found not ok");

		}

		// 4.comparing Otr Rfl And Frf
		if (otrRflAndFrfActual == otrRflAndFrfExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			System.out.println("Otr Rfl And Frf compared and found ok");
			LO.print("Otr Rfl And Frf compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			System.err.println("Otr Rfl And Frf compared but found not ok");
			LO.print("Otr Rfl And Frf compared but found not ok");

		}

	
		// 5.comparing quote no.
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

		// 6.comparing vehicle name
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

		// 7.comparing contract type
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

		
		// 8.comparing term
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

		
		// 9.comparing mileage
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
		
	// 10.comparing Basic Cash Price
		
		
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

		// 11.comparing VAT
		
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

		// 12.comparing non vat items
		
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

		// 13.comparing Total Cash Price
		
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

		// 14.comparing Order Deposit
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

		// 15.comparing Finance Deposit
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

		// 16.comparing Total Deposit
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

		// 17.comparing Part Exchange Value
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

		// 18.comparing Balance to Finance
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

		// 19.comparing Finance Charges	
		
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

		// 20.comparing Document Fee
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

		// 21.comparing Balance Payable
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

		// 22.comparing Option To Purchase Fee
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

		// 23.comparing Initial Cash Payment
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

		// 24.comparing Followed By months
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

		// 25.comparing Monthly Finance Payment
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

		// 26.comparing Balloon Value
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

		// 27.comparing Final Payment
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
		
		// 28.comparing Final Payment
        if ((Difference.of_two_Double_Values(pencePerExcessMileFinance, customer_quote_summary_pence_per_excess_mile_finance)) < 0.2) {
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

		// 29.comparing Vehicle Commission	
		
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

		// 30.comparing Default Finance Commission
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

		// 31.comparing Document Fee Commission
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

		// 32.comparing Total Commission
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
		
		
		// 33.comparing Base Interest Rate
		if (Difference.of_two_Double_Values(baseInterestRateFromExcel , baseInterestRateFromScreen)<0.05) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");	
			
			System.out.println(baseInterestRateFromScreen + " = " + baseInterestRateFromExcel);
			LO.print          (baseInterestRateFromScreen + " = " + baseInterestRateFromExcel);	
			
			LO.print          ("Base Interest Rate found OK");
			System.out.println("Base Interest Rate found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(baseInterestRateFromScreen + " != " + baseInterestRateFromExcel);
			LO.print          (baseInterestRateFromScreen + " != " + baseInterestRateFromExcel);
		
			
			LO.print          ("Base Interest Rate found wrong");
			System.err.println("Base Interest Rate found wrong");
		}

		// 34.comparing Finance Margin
		if (Difference.of_two_Double_Values(financeMarginFromScreen, financeMarginFromExcel) < 0.2) {
			
			count++;

			System.out.println("");
			LO.print          ("");	
			
			System.out.println(financeMarginFromScreen + " = " + financeMarginFromExcel);
			LO.print          (financeMarginFromScreen + " = " + financeMarginFromExcel);	
			
			LO.print          ("Finance Margin found OK");
			System.out.println("Finance Margin found OK");
			
		} else {
			System.out.println("");
			LO.print          ("");	
			
			System.err.println(baseInterestRateFromScreen + " != " + baseInterestRateFromExcel);
			LO.print          (baseInterestRateFromScreen + " != " + baseInterestRateFromExcel);
			
			LO.print          ("Finance Margin found wrong");
			System.err.println("Finance Margin found wrong");
		}

		// 35.comparing Deductions
		if (Difference.of_two_Double_Values(deductionsFromScreen, deductionsFromExcel) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(deductionsFromScreen + " = " + deductionsFromExcel);
			LO.print          (deductionsFromScreen + " = " + deductionsFromExcel);		
			
			LO.print          ("Deductions found OK");
			System.out.println("Deductions found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(deductionsFromScreen + " != " + deductionsFromExcel);
			LO.print          (deductionsFromScreen + " != " + deductionsFromExcel);
			
			LO.print          ("Deductions found wrong");
			System.err.println("Deductions found wrong");
		}

		// 36.comparing Additional Margin
		if (Difference.of_two_Double_Values(additionalMarginFromScreen, additionalMarginFromExcel) < 0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(additionalMarginFromScreen + " = " + additionalMarginFromExcel);
			LO.print          (additionalMarginFromScreen + " = " + additionalMarginFromExcel);		
		
			
			LO.print          ("Additional Margin found OK");
			System.out.println("Additional Margin found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(additionalMarginFromScreen + " != " + additionalMarginFromExcel);
			LO.print          (additionalMarginFromScreen + " != " + additionalMarginFromExcel);
			
			LO.print          ("Additional Margin found wrong");
			System.err.println("Additional Margin found wrong");
		}

		// 37.comparing Total Margin
		if (Difference.of_two_Double_Values(totalMarginFromScreen, totalMarginFromExcel) < 0.2) {
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(totalMarginFromScreen + " = " + totalMarginFromExcel);
			LO.print          (totalMarginFromScreen + " = " + totalMarginFromExcel);
			
			LO.print          ("Total Margin found OK");
			System.out.println("Total Margin found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");

			System.err.println(totalMarginFromScreen + " != " + totalMarginFromExcel);
			LO.print          (totalMarginFromScreen + " != " + totalMarginFromExcel);

			
			LO.print          ("Total Margin found wrong");
			System.err.println("Total Margin found wrong");
		}

		// 38.comparing Default Broker Margin percentage
		if (Difference.of_two_Double_Values(defaultBrokerMarginPercentageFromExcel , defaultBrokerMarginPercentageFromScreen)<0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(defaultBrokerMarginPercentageFromScreen + " = " + defaultBrokerMarginPercentageFromExcel);
			LO.print          (defaultBrokerMarginPercentageFromScreen + " = " + defaultBrokerMarginPercentageFromExcel);
			
			LO.print          ("Default Broker Margin percentage found OK");
			System.out.println("Default Broker Margin percentage found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(defaultBrokerMarginPercentageFromScreen + " != " + defaultBrokerMarginPercentageFromExcel);
			LO.print          (defaultBrokerMarginPercentageFromScreen + " != " + defaultBrokerMarginPercentageFromExcel);
			
			LO.print          ("Default Broker Margin percentage found wrong");
			System.err.println("Default Broker Margin percentage found wrong");
		}

		// 39.comparing Customer Interest Rate
		if (Difference.of_two_Double_Values(customerInterestRateFromScreen , customerInterestRateFromExcel)<0.2) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(customerInterestRateFromScreen + " = " + customerInterestRateFromExcel);
			LO.print          (customerInterestRateFromScreen + " = " + customerInterestRateFromExcel);
		
			LO.print          ("Customer Interest Rate found OK");
			System.out.println("Customer Interest Rate found OK");
			
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(customerInterestRateFromScreen + " != " + customerInterestRateFromExcel);
			LO.print          (customerInterestRateFromScreen + " != " + customerInterestRateFromExcel);

			
			LO.print          ("Customer Interest Rate found wrong");
			System.err.println("Customer Interest Rate found wrong");
		}

		// 40.comparing Document Fee
		if (Difference.of_two_Double_Values(documentFeeMarginFromScreen, documentFeeMarginFromExcel) < 0.2) {
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(documentFeeMarginFromScreen + " = " + documentFeeMarginFromExcel);
			LO.print          (documentFeeMarginFromScreen + " = " + documentFeeMarginFromExcel);
				
			LO.print          ("Document Fee Margin found OK");
			System.out.println("Document Fee Margin found OK");
			
		} else {
			System.out.println("");
			LO.print          ("");
			
			System.err.println(documentFeeMarginFromScreen + " != " + documentFeeMarginFromExcel);
			LO.print          (documentFeeMarginFromScreen + " != " + documentFeeMarginFromExcel);

			
			LO.print          ("Document Fee Margin  found wrong");
			System.err.println("Document Fee Margin  found wrong");
		}
		// 41.comparing Default Broker Margin
		if ((Difference.of_two_Double_Values(default_broker_margin_copied, defaultBrokerMarginFromExcel) < 0.2)) {
			
			count++;
			
			System.out.println("");
			LO.print          ("");
			
			System.out.println(default_broker_margin_copied + " = " + defaultBrokerMarginFromExcel);
			LO.print          (default_broker_margin_copied + " = " + defaultBrokerMarginFromExcel);
			
			LO.print          ("Default Broker Margin found OK");
			System.out.println("Default Broker Margin found OK");
		
		} else {
			
			System.out.println("");
			LO.print          ("");
			
			System.err.println(default_broker_margin_copied + " != " + defaultBrokerMarginFromExcel);
			LO.print          (default_broker_margin_copied + " != " + defaultBrokerMarginFromExcel);
			
			LO.print          ("Default Broker Margin  found wrong");
			System.err.println("Default Broker Margin  found wrong");
		}
		
		
		
		int expcount=0;
if(Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName().contains("funder"))
{ expcount=40;} else {expcount=41;}

		boolean status = false;
		if (count == expcount)

		{
			status = true;
			
	        // ANSI escape code for green color
	        String ansiGreen = "\u001B[32m";
	        
	        // ANSI escape code to reset the console color
	        String ansiReset = "\u001B[0m";
			
			System.out.println("");
			LO.print          ("");
			LO.print          (ansiGreen+"All values on underwriting quote tab verified successfully"+ansiReset);
			System.out.println(ansiGreen+"All values on underwriting quote tab verified successfully"+ansiReset);
			System.out.println("");
			LO.print          ("");
			
		}else
		{
			System.out.println("");
			LO.print          ("");
			LO.print          ("One or More than One values on underwriting quote tab may be wrong , please check all above values printed on console");
			System.err.println("One or More than One values on underwriting quote tab may be wrong , please check all above values printed on console");
			System.out.println("");
			LO.print          ("");
		}

		return status;

	}

	
	
	public boolean verify_quote_tab_on_underwriting_page_for_broker_business_purchase_flow()
			throws InterruptedException, ClassNotFoundException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, underwriting_tab_quote, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		// HelperClass.highlightElement(driver, underwriting_tab_quote);

		Click.on(driver, underwriting_tab_quote, 60);

		System.out.println("Clicked on Underwriting quote page");
		LO.print("Clicked on Underwriting quote page");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_vehicle_heading, 120);

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_contract_type, 60);

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_button, 120);

		// Cliking on cust quote summary section
		Click.on(driver, underwriting_quote_tab_customer_quote_summary_button, 60);

		// waiting for otr section elements
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_otr_price, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_price_ex_vat_and_rfl, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_vat, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_rfl_and_frf, 120);

		// waiting for summary section elements
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_terms, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_miles_per_annum, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_monthly_finance_payment, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_funder_name, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_ref_number, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_exp_date, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_contract_mileage, 120);

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_total_cash_price, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_cash_deposit, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_balance_to_finance, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_finance_charges, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_balance_payable, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_initial_cash_payment_inc_document_fee,
				120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_no_of_monthly_payments, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_final_balloon_payment, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_option_to_purchase_fee, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_RFL_included, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_APR, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_quote_ref_no, 60);


	  	// Vehicle details
			//1
			String vehicleNameActual = underwriting_quote_tab_vehicle_heading.getText().trim();

			//2
			String quotRefNoActual = underwriting_quote_tab_quote_ref_no.getText();
			
			
			// customer quote section
			// getting text from elements
	        //3
			String contractTypeActual = underwriting_quote_tab_customer_contract_type.getText();
		    //4	
			double termActual = Double.parseDouble(underwriting_quote_tab_customer_underwriting_quote_tab_terms.getText().trim().substring(0,2));
			//5
			double mileageActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_miles_per_annum.getText().trim()));
			//6
			double monthlyFinancePaymentActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_monthly_finance_payment.getText().trim().substring(2)));
			//7
			String funderNameActual = underwriting_quote_tab_customer_underwriting_quote_tab_funder_name.getText().trim();
			//8
			String funderQuoteRefNumberActual = underwriting_quote_tab_ref_number.getText().trim();
			//9
			String expiryDateActual = underwriting_quote_tab_exp_date.getText().trim();
			//10
			double contractMileageActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_contract_mileage.getText().trim()));
			//11
			double totalCashPriceActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_total_cash_price.getText().trim().substring(2)));
			//12
			double cashDepositActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_cash_deposit.getText().trim().substring(2)));
			//13
			double balanceToFinanceActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_balance_to_finance.getText().trim().substring(2)));
			//14
			double financeChargesActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_finance_charges.getText().trim().substring(2)));
			//15
			double balancePayableActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_balance_payable.getText().trim().substring(2)));
			//16
			double initialCashPaymentIncDocumentFeeActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_initial_cash_payment_inc_document_fee.getText().trim().substring(2)));
			//17
			double noOfMonthlyPaymentsActual  = Double.parseDouble(underwriting_quote_tab_customer_underwriting_quote_tab_no_of_monthly_payments.getText().trim());
			//18
			double finalBalloonPaymentActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_final_balloon_payment.getText().trim().substring(2)));
			//19
			double optionToPurchaseFeeActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_option_to_purchase_fee.getText().trim().substring(2)));
			//20
			String rFLIncludedActual = underwriting_quote_tab_customer_underwriting_quote_tab_RFL_included.getText().trim();
			//21
			String [] APR = underwriting_quote_tab_customer_underwriting_quote_tab_APR.getText().trim().split(" ");
			
			double aPRActual = Double.parseDouble(APR[0]);

			//22
			double commissionActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_commission.getText().trim().substring(2)));
			
			// getting otr section elements text
			//23
			double costPriceExVatAndRflActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_price_ex_vat_and_rfl.getText().trim().substring(2)));
			//24
			double otrVatActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_vat.getText().trim().substring(2)));
			//25
			double otrRflAndFrfActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_rfl_and_frf.getText().trim().substring(2)));
			//26
			double costOtrPriceActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_otr_price.getText().trim().substring(2)));


		String sheetName = "";

		if (Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName()
				.contains("business_purchase")) {
			sheetName = prop.getProperty("BrokerHPNRQuoteNo");
		}

		if (Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName()
				.contains("individual_purchase")) {
			sheetName = prop.getProperty("BrokerPCPQuoteNo");
		}

		
		// Getting Elements from excel sheet 
		double costPriceExVatAndRflExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(1, 6, sheetName));
		double otrVatExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(1, 8, sheetName));
		double otrRflAndFrfExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(3, 6, sheetName));
		double costOtrPriceExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(3, 8, sheetName));

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

		// *******************************

		int count = 0;


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
								
		
		
		// 23.comparing cost OTR price
		if (costOtrPriceActual == costOtrPriceExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costOtrPriceActual + " = " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " = " + costOtrPriceExpected);
			System.out.println("Cost Otr Price compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costOtrPriceActual + " != " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " != " + costOtrPriceExpected);
			System.err.println("Cost Otr Price compared but found not ok");
			LO.print("Cost Otr Price compared but found not ok");

		}

		// 24.comparing cost Price Ex Vat And Rfl
		if (costPriceExVatAndRflActual == costPriceExVatAndRflExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			System.out.println("Cost Price Ex Vat And Rfl compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			System.err.println("Cost Price Ex Vat And Rfl compared but found not ok");
			LO.print("Cost Price Ex Vat And Rfl compared but found not ok");

		}

		// 25.comparing cost Price Ex Vat And Rfl
		if (otrVatActual == otrVatExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrVatActual + " = " + otrVatExpected);
			LO.print(otrVatActual + " = " + otrVatExpected);
			System.out.println("Otr Vat compared and found ok");
			LO.print("Otr Vat compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrVatActual + " != " + otrVatExpected);
			LO.print(otrVatActual + " != " + otrVatExpected);
			System.err.println("Otr Vat compared but found not ok");
			LO.print("Otr Vat compared but found not ok");

		}

		// 26.comparing Otr Rfl And Frf
		if (otrRflAndFrfActual == otrRflAndFrfExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			System.out.println("Otr Rfl And Frf compared and found ok");
			LO.print("Otr Rfl And Frf compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			System.err.println("Otr Rfl And Frf compared but found not ok");
			LO.print("Otr Rfl And Frf compared but found not ok");

		}


		boolean status = false;
		if (count==26)
			
		{
			status = true;
		}
		
		return status;

	}

	public boolean verify_quote_tab_on_underwriting_page_for_broker_individual_purchase_flow()
			throws InterruptedException, ClassNotFoundException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, underwriting_tab_quote, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		// HelperClass.highlightElement(driver, underwriting_tab_quote);

		Click.on(driver, underwriting_tab_quote, 60);

		System.out.println("Clicked on Underwriting quote page");
		LO.print("Clicked on Underwriting quote page");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_vehicle_heading, 120);

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_contract_type, 60);

		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_quote_summary_button, 120);

		// Cliking on cust quote summary section
		Click.on(driver, underwriting_quote_tab_customer_quote_summary_button, 60);

		// waiting for otr section elements
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_otr_price, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_cost_price_ex_vat_and_rfl, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_vat, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_otr_rfl_and_frf, 120);

		// waiting for summary section elements
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_terms, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_miles_per_annum, 120);
		
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_monthly_finance_payment, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_monthly_finance_payment, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_monthly_finance_payment, 120);
		
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_funder_name, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_ref_number, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_exp_date, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_contract_mileage, 120);	
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_total_cash_price, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_cash_deposit, 120);	
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_balance_to_finance, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_no_of_monthly_payments, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_optional_final_payment, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_option_to_purchase_fee, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_RFL_included, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_pence_per_excess_mile_finance, 120);
//		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_pence_per_excess_mile_maint, 120);
//		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_pence_per_excess_mile_total, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_APR, 120);
		ExplicitWait.visibleElement(driver, underwriting_quote_tab_customer_underwriting_quote_tab_commission, 120);
	


     	// Vehicle details
		//1
		String vehicleNameActual = underwriting_quote_tab_vehicle_heading.getText().trim();

		//2
		String quotRefNoActual = underwriting_quote_tab_quote_ref_no.getText();
		
		
		// customer quote section
		// getting text from elements
        //3
		String contractTypeActual = underwriting_quote_tab_customer_contract_type.getText();
	    //4	
		double termActual = Double.parseDouble(underwriting_quote_tab_customer_underwriting_quote_tab_terms.getText().trim().substring(0,2));
		//5
		double mileageActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_miles_per_annum.getText().trim()));
		//6
		double monthlyFinancePaymentActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_monthly_finance_payment.getText().trim().substring(2)));
		//7
		String funderNameActual = underwriting_quote_tab_customer_underwriting_quote_tab_funder_name.getText().trim();
		//8
		String funderQuoteRefNumberActual = underwriting_quote_tab_ref_number.getText().trim();
		//9
		String expiryDateActual = underwriting_quote_tab_exp_date.getText().trim();
		//10
		double contractMileageActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_contract_mileage.getText().trim()));
		//11
		double totalCashPriceActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_total_cash_price.getText().trim().substring(2)));
		//12
		double cashDepositActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_cash_deposit.getText().trim().substring(2)));
		//13
		double balanceToFinanceActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_balance_to_finance.getText().trim().substring(2)));
		//14
		double noOfMonthlyPaymentsActual  = Double.parseDouble(underwriting_quote_tab_customer_underwriting_quote_tab_no_of_monthly_payments.getText().trim());
		//15
		double optionalFinalPaymentActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_optional_final_payment.getText().trim().substring(2)));
		//16
		double optionToPurchaseFeeActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_option_to_purchase_fee.getText().trim().substring(2)));
		//17
		String rFLIncludedActual = underwriting_quote_tab_customer_underwriting_quote_tab_RFL_included.getText().trim();
		//18
		String pencePerExcessMileFinanceActual = RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_pence_per_excess_mile_finance.getText().trim());
    	//19
		String [] APR = underwriting_quote_tab_customer_underwriting_quote_tab_APR.getText().trim().split(" ");
		double aPRActual = Double.parseDouble(APR[0]);
		//20
		double commissionActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_customer_underwriting_quote_tab_commission.getText().trim().substring(2)));
		
			// getting otr section elements text
			//21
			double costPriceExVatAndRflActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_price_ex_vat_and_rfl.getText().trim().substring(2)));
			//22
			double otrVatActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_vat.getText().trim().substring(2)));
			//23
			double otrRflAndFrfActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_otr_rfl_and_frf.getText().trim().substring(2)));
			//24
			double costOtrPriceActual = Double.parseDouble(RemoveComma.of(underwriting_quote_tab_cost_otr_price.getText().trim().substring(2)));


		String sheetName = "";

		if (Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName()
				.contains("business_purchase")) {
			sheetName = prop.getProperty("BrokerHPNRQuoteNo");
		}

		if (Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName()
				.contains("individual_purchase")) {
			sheetName = prop.getProperty("BrokerPCPQuoteNo");
		}

		
		// Getting Elements from excel sheet 
		double costPriceExVatAndRflExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(1, 6, sheetName));
		double otrVatExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(1, 8, sheetName));
		double otrRflAndFrfExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(3, 6, sheetName));
		double costOtrPriceExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(3, 8, sheetName));

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

		// *******************************

		int count = 0;


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
								
		
		
		// 21.comparing cost OTR price
		if (costOtrPriceActual == costOtrPriceExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costOtrPriceActual + " = " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " = " + costOtrPriceExpected);
			System.out.println("Cost Otr Price compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costOtrPriceActual + " != " + costOtrPriceExpected);
			LO.print(costOtrPriceActual + " != " + costOtrPriceExpected);
			System.err.println("Cost Otr Price compared but found not ok");
			LO.print("Cost Otr Price compared but found not ok");

		}

		// 22.comparing cost Price Ex Vat And Rfl
		if (costPriceExVatAndRflActual == costPriceExVatAndRflExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " = " + costPriceExVatAndRflExpected);
			System.out.println("Cost Price Ex Vat And Rfl compared and found ok");
			LO.print("Cost Otr Price compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			LO.print(costPriceExVatAndRflActual + " != " + costPriceExVatAndRflExpected);
			System.err.println("Cost Price Ex Vat And Rfl compared but found not ok");
			LO.print("Cost Price Ex Vat And Rfl compared but found not ok");

		}

		// 23.comparing cost Price Ex Vat And Rfl
		if (otrVatActual == otrVatExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrVatActual + " = " + otrVatExpected);
			LO.print(otrVatActual + " = " + otrVatExpected);
			System.out.println("Otr Vat compared and found ok");
			LO.print("Otr Vat compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrVatActual + " != " + otrVatExpected);
			LO.print(otrVatActual + " != " + otrVatExpected);
			System.err.println("Otr Vat compared but found not ok");
			LO.print("Otr Vat compared but found not ok");

		}

		// 24.comparing Otr Rfl And Frf
		if (otrRflAndFrfActual == otrRflAndFrfExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " = " + otrRflAndFrfExpected);
			System.out.println("Otr Rfl And Frf compared and found ok");
			LO.print("Otr Rfl And Frf compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			LO.print(otrRflAndFrfActual + " != " + otrRflAndFrfExpected);
			System.err.println("Otr Rfl And Frf compared but found not ok");
			LO.print("Otr Rfl And Frf compared but found not ok");

		}


		boolean status = false;
		if (count==24)
			
		{
			status = true;
		}
		
		return status;

	}

	
	public void find_underwriting_tab_creditfile_page() throws InterruptedException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		Thread.sleep(4000);
		ExplicitWait.visibleElement(driver, underwriting_tab_creditfile, 60);

		HelperClass.highlightElement(driver, underwriting_tab_creditfile);

		Click.on(driver, underwriting_tab_creditfile, 60);

		System.out.println("Click on credit file tab page ");
		LO.print("Click on credit file page ");

	}

	public void find_underwriting_tab_document_page() throws InterruptedException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		Thread.sleep(4000);

		ExplicitWait.visibleElement(driver, underwriting_tab_document, 60);

		HelperClass.highlightElement(driver, underwriting_tab_document);

		Click.on(driver, underwriting_tab_document, 60);

		System.out.println("Click on document file tab page ");
		LO.print("Click on document file page ");

	}

	public void find_underwriting_tab_decision_page() throws InterruptedException {

		Thread.sleep(4000);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, decision_tab_document, 20);

		HelperClass.highlightElement(driver, decision_tab_document);

		Click.on(driver, decision_tab_document, 60);

		System.out.println("Click on decision tab page");
		LO.print          ("Click on decision tab page");

	}

	public void find_underwriting_tab_decision_page_accept_button() throws InterruptedException {

		Thread.sleep(4000);
		ExplicitWait.visibleElement(driver, Accept_button, 20);

		HelperClass.highlightElement(driver, Accept_button);

		Click.on(driver, Accept_button, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		System.out.println("Click on decision tab Accept button ");
		LO.print("Click on decision Accept button");

	}

	public void onchanging_refer_to_accept_underwriting_please_confirm_button() throws InterruptedException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, confirm_button, 60);

		// HelperClass.highlightElement(driver, confirm_button);

		Click.on(driver, confirm_button, 60);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		System.out.println("On changing refer to accept button - Please confirm pop will display ");
		LO.print("On changing refer to accept button - Please confirm pop will display ");

	}

	public void ownbook_hire_decision_accept_and_change_the_quote_data() throws Exception

	{
		//Identifying which sheet name to be used
        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		obj_acq_listing_page = new AcquisitionListingPage();		
		String sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		//Getting data values from quote save excel sheet 
		//1. Sec Deposity
		String securityDeposit = GetExcelFormulaValue.get_cell_value(4, 5, sheetName);
		
		//2. Order Deposity
		String orderDeposit = GetExcelFormulaValue.get_cell_value(4, 6, sheetName);
		
		//3. Payment Profile
		String paymentProfile = GetExcelFormulaValue.get_cell_value(4, 7, sheetName);
				
		//4. Initial payment
		String initialpayment = GetExcelFormulaValue.get_cell_value(4, 8, sheetName);
		
		//5. Initial payment
		String maintRequired = GetExcelFormulaValue.get_cell_value(4, 9, sheetName);
		
		//6. Cap maint cost annual
		String capMaintCost = GetExcelFormulaValue.get_cell_value(4, 10, sheetName);		
		
		LO.print          ("Entering values to input field");
		System.out.println("Entering values to input field");
		
		//putting values to input field		
		// 1.sending security deposit
		ExplicitWait.visibleElement(driver, accept_security_deposit_required, 20);
		Click.sendKeys(driver, accept_security_deposit_required, securityDeposit, 60);

		
		// 2.sending order deposit
		ExplicitWait.visibleElement(driver, accept_increased_order_deposit, 120);
		Click.sendKeys(driver, accept_increased_order_deposit, orderDeposit, 60);

		LO.print          ("Now selecting the payment profile option ");
		System.out.println("Now selecting the payment profile option ");		

	    ExplicitWait.visibleElement(driver, accept_payment_profile, 60);
		HelperClass.highlightElement(driver, accept_payment_profile);		
		accept_payment_profile.click();
		Thread.sleep(2000);
		
		
		ExplicitWait.waitForListOfVisibleElements(driver, payment_profiles_options_list, 60);
		
		for(WebElement e : payment_profiles_options_list)
		{
			
			System.out.println("Elements Text : "+e.getText());
			
			if(e.getText().equalsIgnoreCase(paymentProfile))
			{
				e.click();
				
				if(paymentProfile.contains("Spread rentals initial payment"))
				{
			     	ExplicitWait.visibleElement(driver, Initial_payment_amount, 60);
					Thread.sleep(2000);
					Click.sendKeys(driver, Initial_payment_amount, initialpayment, 60);
					Thread.sleep(2000);
					
					break;
				}
			}			
				
		}
		
		try{ ExplicitWait.visibleElement(driver, maint_contract_checkbox, 10);}catch(Exception e1) {}
		System.out.println("Maint Req "+maintRequired);	
		System.out.println("Checkbox status "+maint_contract_checkbox.isEnabled());	
		
		if(maintRequired.equalsIgnoreCase("Yes")&&maint_contract_checkbox.isEnabled())
		{	
			LO.print          ("");
			System.out.println("");
			System.out.println("Maint Req "+maintRequired);		
			js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", maint_contract_checkbox);				
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
			LO.print          ("");
			System.out.println("");
			
		}	
		
		if(maintRequired.equalsIgnoreCase("No"))
		{	
			System.out.println("Maint Req "+maintRequired);		
					
			if(maint_contract_checkbox.isEnabled())
			{	
			}
			else
			{
				LO.print          ("");
				System.out.println("");	
				LO.print          ("Maintenance checkbox is not enabled as Maintenance is already present for the mapped quote");
				System.out.println("Maintenance checkbox is not enabled as Maintenance is already present for the mapped quote");	
				LO.print          ("");
				System.out.println("");	
			}
		}
			
			
				

		
		//write all these values to calculation sheet as below
		
		    
		obj_acq_listing_page = new AcquisitionListingPage();		
		sheetName = obj_acq_listing_page.calculation_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		Underwriting uw = new  Underwriting();	
		if(sheetName.contains("Formula1"))
		{
		if(maintRequired.contains("Yes")) {
		uw.write_edit_data_to_excel_for_ownboook_hire_contract(sheetName, orderDeposit, maintRequired, capMaintCost, paymentProfile, initialpayment);
		}
		
		if(maintRequired.contains("No")) {
		uw.write_edit_data_to_excel_for_ownboook_hire_contract_with_maintenance(sheetName, orderDeposit,  paymentProfile, initialpayment);
		}
			
		}
		if(sheetName.contains("Formula 3"))
		{
			uw.write_edit_data_to_excel_for_ownboook_hire_funder_contract(sheetName, orderDeposit, maintRequired, capMaintCost, paymentProfile, initialpayment);	
		}
	}

	
	public void ownbook_purchase_decision_accept_and_change_the_quote_data() throws Exception

	{
		//Identifying which sheet name to be used
        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		obj_acq_listing_page = new AcquisitionListingPage();		
		String sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		//Getting data values from quote save excel sheet 
		//1. Sec Deposity
		String securityDeposit = GetExcelFormulaValue.get_cell_value(4, 5, sheetName);
		
		//2. Finance Deposit
		String financeDeposit = GetExcelFormulaValue.get_cell_value(4, 6, sheetName);
		
		//3. Order Deposit
		String orderDeposit = GetExcelFormulaValue.get_cell_value(4, 7, sheetName);
		
		//5. maint required 
		String maintRequired = GetExcelFormulaValue.get_cell_value(4, 8, sheetName);
		
		
		//4. Cap maint cost annual
		String capMaintCost = GetExcelFormulaValue.get_cell_value(4, 9, sheetName);		
		
		LO.print          ("Entering values to input field");
		System.out.println("Entering values to input field");
		
		//putting values to input field		
		// 1.sending security deposit
		ExplicitWait.visibleElement(driver, accept_security_deposit_required, 20);
		Click.sendKeys(driver, accept_security_deposit_required, securityDeposit, 60);

		
		// 2.sending finance deposit
		ExplicitWait.visibleElement(driver, accept_increased_finance_deposit, 120);
		Click.sendKeys(driver, accept_increased_finance_deposit, financeDeposit, 60);
		
		// 3.sending order deposit
		ExplicitWait.visibleElement(driver, accept_increased_order_deposit, 120);
		Click.sendKeys(driver, accept_increased_order_deposit, orderDeposit, 60);

		
		try{ ExplicitWait.visibleElement(driver, maint_contract_checkbox, 10);}catch(Exception e1) {}
		
		if(maintRequired.equalsIgnoreCase("Yes")&&maint_contract_checkbox.isEnabled())
		{	
			
			js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", maint_contract_checkbox);				
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
			
		}		
		
		//write all these values to calculation sheet as below
		     
		obj_acq_listing_page = new AcquisitionListingPage();		
		sheetName = obj_acq_listing_page.calculation_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		Underwriting uw = new  Underwriting();	
		
		if(sheetName.contains("Formula 3"))
		{
			uw.write_edit_data_to_excel_for_ownboook_purchase_funder_contract(sheetName, orderDeposit, financeDeposit, maintRequired, capMaintCost);

		}
		else
		{
		uw.write_edit_data_to_excel_for_ownboook_purchase_contract(sheetName, orderDeposit,financeDeposit, maintRequired, capMaintCost);
		}
	}

	
	public void write_edit_data_to_excel_for_ownboook_hire_contract_with_maintenance(String sheetName, String orderDeposit,
			String paymentProfile, String initialpayment) throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheetName).getRow(98).getCell(1).setCellValue(" "+paymentProfile+" ");
		wb.getSheet(sheetName).getRow(98).getCell(3).setCellValue(Double.parseDouble(orderDeposit));
		wb.getSheet(sheetName).getRow(104).getCell(3).setCellValue(Double.parseDouble(initialpayment));

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		out.close();
	}
	
	public void write_edit_data_to_excel_for_ownboook_hire_contract(String sheetName, String orderDeposit,
			String maintRequired, String capMaintCost, String paymentProfile, String initialpayment) throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		
		wb.getSheet(sheetName).getRow(25).getCell(1).setCellValue(maintRequired);
		wb.getSheet(sheetName).getRow(31).getCell(8).setCellValue(Double.parseDouble(capMaintCost));
		wb.getSheet(sheetName).getRow(98).getCell(1).setCellValue(" "+paymentProfile+" ");
		wb.getSheet(sheetName).getRow(98).getCell(3).setCellValue(Double.parseDouble(orderDeposit));
		wb.getSheet(sheetName).getRow(104).getCell(0).setCellValue(maintRequired);
		wb.getSheet(sheetName).getRow(104).getCell(1).setCellValue(0.2);
		wb.getSheet(sheetName).getRow(104).getCell(3).setCellValue(Double.parseDouble(initialpayment));

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		out.close();
	}
	
	
	public void write_edit_data_to_excel_for_ownboook_hire_funder_contract(String sheetName, String orderDeposit,
			String maintRequired, String capMaintCost, String paymentProfile, String initialpayment) throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		
		wb.getSheet(sheetName).getRow(37).getCell(1).setCellValue(maintRequired);
		wb.getSheet(sheetName).getRow(40).getCell(0).setCellValue(Double.parseDouble(capMaintCost));
		wb.getSheet(sheetName).getRow(104).getCell(1).setCellValue(" "+paymentProfile+" ");
		wb.getSheet(sheetName).getRow(104).getCell(3).setCellValue(Double.parseDouble(orderDeposit));
		wb.getSheet(sheetName).getRow(110).getCell(0).setCellValue(maintRequired);
		wb.getSheet(sheetName).getRow(110).getCell(1).setCellValue(20);
		wb.getSheet(sheetName).getRow(110).getCell(3).setCellValue(Double.parseDouble(initialpayment));

		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		out.close();
	}

	
	
	public void write_edit_data_to_excel_for_ownboook_purchase_contract(String sheetName, String orderDeposit,String financeDeposit,
			String maintRequired, String capMaintCost) throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		
		wb.getSheet(sheetName).getRow(25).getCell(1).setCellValue(maintRequired);
		wb.getSheet(sheetName).getRow(31).getCell(10).setCellValue(Double.parseDouble(capMaintCost));	
		wb.getSheet(sheetName).getRow(104).getCell(4).setCellValue(maintRequired);
		wb.getSheet(sheetName).getRow(110).getCell(1).setCellValue(Double.parseDouble(orderDeposit));
		wb.getSheet(sheetName).getRow(110).getCell(4).setCellValue(Double.parseDouble(financeDeposit));	
		wb.getSheet(sheetName).getRow(107).getCell(0).setCellValue(0.2);	


		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		out.close();
	}

	
	
	public void write_edit_data_to_excel_for_ownboook_purchase_funder_contract(String sheetName, String orderDeposit,String financeDeposit,
			String maintRequired, String capMaintCost) throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		
		wb.getSheet(sheetName).getRow(37).getCell(1).setCellValue(maintRequired);
		wb.getSheet(sheetName).getRow(35).getCell(7).setCellValue(Double.parseDouble(capMaintCost));	
		wb.getSheet(sheetName).getRow(104).getCell(4).setCellValue(maintRequired);
		wb.getSheet(sheetName).getRow(110).getCell(1).setCellValue(Double.parseDouble(orderDeposit));
		wb.getSheet(sheetName).getRow(110).getCell(4).setCellValue(Double.parseDouble(financeDeposit));	
		wb.getSheet(sheetName).getRow(107).getCell(0).setCellValue(0.2);	


		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		out.close();
	}


	public void ownbook_hire_accept_with_change_the_data_quote() throws Exception

	{
		// input[@id='securityDeposit']

		// 1.proposal_indvidual_first_name
		ExplicitWait.visibleElement(driver, accept_security_deposit_required, 20);

		Click.sendKeys(driver, accept_security_deposit_required, "5000", 60);

		// (//input[@id='increasedOrderDeposit'])[1]
		// 2.proposal_indvidual_first_name
		ExplicitWait.visibleElement(driver, accept_increased_order_deposit, 20);

		Click.sendKeys(driver, accept_increased_order_deposit, "5000", 60);

		System.out.println("Now selecting the payment profile option ");

		// ExplicitWait.visibleElement(driver, accept_payment_profile, 60);

		HelperClass.highlightElement(driver, accept_payment_profile);

		Thread.sleep(2000);
		accept_payment_profile.click();

		underw_accept_spread_rental_payment_value.click();
		Thread.sleep(2000);

		// accept_payment_profile.sendKeys("Spread rentals initial payment");

		// Thread.sleep(5000);

		// Click.sendKeys(driver, accept_payment_profile, "Spread rentals initial
		// payment", 60);

		ExplicitWait.visibleElement(driver, Initial_payment_amount, 60);
		Thread.sleep(5000);
		Click.sendKeys(driver, Initial_payment_amount, "500", 60);
		Thread.sleep(5000);

	}

	public void ownbook_purchase_accept_with_change_the_data_quote() throws Exception

	{

		ExplicitWait.visibleElement(driver, accept_security_deposit_required, 20);
		Click.sendKeys(driver, accept_security_deposit_required, "5000", 60);

		ExplicitWait.visibleElement(driver, accept_increased_order_deposit, 20);
		Click.sendKeys(driver, accept_increased_order_deposit, "5000", 60);

		ExplicitWait.visibleElement(driver, accept_finance_deposit, 20);
		Click.sendKeys(driver, accept_finance_deposit, "5000", 60);

		ExplicitWait.visibleElement(driver, accept_maintained_contract_required, 20);
		accept_maintained_contract_required.click();

		///////////////////

		ExplicitWait.visibleElement(driver, accept_tracker_must_be_fitted, 20);
		accept_tracker_must_be_fitted.click();

		ExplicitWait.visibleElement(driver, Manufacturer_tracker_must_be_activated_by_customer, 20);
		Manufacturer_tracker_must_be_activated_by_customer.click();

		ExplicitWait.visibleElement(driver, AMT_tracker_required, 20);
		AMT_tracker_required.click();

		ExplicitWait.visibleElement(driver, StatisfyCheckbox, 20);
		StatisfyCheckbox.click();

		ExplicitWait.visibleElement(driver, Contract_must_include_insurance, 20);
		Contract_must_include_insurance.click();

		ExplicitWait.visibleElement(driver, Other_conditions, 20);
		Other_conditions.click();

		ExplicitWait.visibleElement(driver, Other_conditions_textbox, 20);
		Click.sendKeys(driver, Other_conditions_textbox, "Test", 60);

		ExplicitWait.visibleElement(driver, Notestoapplicant, 20);
		Click.sendKeys(driver, Notestoapplicant, "Test Notes", 60);

		////////////////

		ExplicitWait.visibleElement(driver, Driving_licence_for_Accept, 20);

		Click.sendKeys(driver, Driving_licence_for_Accept, "QA comp2Test", 60);

		Thread.sleep(2000);
		Driving_licence_for_Accept.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		ExplicitWait.visibleElement(driver, Proof_of_address_for, 20);
		Click.sendKeys(driver, Proof_of_address_for, "QA comp2Test", 60);

		Thread.sleep(2000);
		Proof_of_address_for.sendKeys(Keys.ENTER);

		ExplicitWait.visibleElement(driver, Director_guarantee_in_the_name, 20);
		Click.sendKeys(driver, Director_guarantee_in_the_name, "QA comp2Test", 60);

		Thread.sleep(2000);
		Director_guarantee_in_the_name.sendKeys(Keys.ENTER);

		ExplicitWait.visibleElement(driver, Cross_company_guarantee_in_the_name_of, 20);
		Click.sendKeys(driver, Driving_licence_for_Accept, "QA comp2 Test", 60);
		Thread.sleep(2000);
		Driving_licence_for_Accept.sendKeys(Keys.ENTER);

	}

	public void find_underwriting_tab_decision_page_accept_upload() throws InterruptedException, AWTException {
		Thread.sleep(4000);

		ExplicitWait.visibleElement(driver, accept_upload_button, 20);

		HelperClass.highlightElement(driver, accept_upload_button);

		Click.on(driver, accept_upload_button, 60);

		Thread.sleep(4000);

		Robot rb = new Robot();

		// copying File path to Clipboard
		StringSelection str = new StringSelection(prop.getProperty("Underwriting_accept_upload_doc_path"));

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		// press Contol+V for pasting
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		// release Contol+V for pasting
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		// for pressing and releasing Enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		System.out.println("File is Uploaded Successfully");

	}

	public void find_underwriting_tab_decision_page_refer_button() throws InterruptedException {

		Thread.sleep(4000);
		ExplicitWait.visibleElement(driver, refer_button, 20);

		HelperClass.highlightElement(driver, refer_button);

		Click.on(driver, refer_button, 60);

		System.out.println("Click on decision tab Refer button ");
		LO.print("Click on decision tab Refer button");

	}

	public void edit_underwriting_tab_decision_page_refer_button() throws InterruptedException {

		Thread.sleep(4000);
		// ExplicitWait.visibleElement(driver, refer_check_box_icon, 20);

		HelperClass.highlightElement(driver, refer_check_box_icon);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", refer_check_box_icon);

		// js.executeScript(refer_check_box_icon);

		System.out.println("Click on checkbox value in refer page");
		LO.print("Click on checkbox value in refer page");

	}

	public void find_underwriting_tab_decision_page_refer_notes_textbox() throws InterruptedException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		// Thread.sleep(4000);
		ExplicitWait.visibleElement(driver, refer_notes_textbox, 20);

		HelperClass.highlightElement(driver, refer_notes_textbox);

		Click.on(driver, refer_notes_textbox, 60);

		System.out.println("Enter the refer notes in text box button");
		LO.print("Enter the refer notes in text box button");

		// ExplicitWait

		Click.sendKeys(driver, refer_notes_textbox, "Need to update document", 60);

	}

	public void verification_underwriting_tab_decision_page_view_icon() throws InterruptedException {

		ExplicitWait.visibleElement(driver, desicion_upload_view, 60);

		HelperClass.highlightElement(driver, desicion_upload_view);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		// Thread.sleep(4000);
		Click.on(driver, desicion_upload_view, 60);

		Thread.sleep(4000);

		System.out.println("click on view icon ");

		System.out.println("Document is visible");

		Click.on(driver, desicion_upload_view_close, 60);

	}

	public void verification_underwriting_tab_decision_page_save_and_exit_button() throws InterruptedException {

		ExplicitWait.visibleElement(driver, desicion_save_exit_button, 60);

		HelperClass.highlightElement(driver, desicion_save_exit_button);

		Click.on(driver, desicion_save_exit_button, 60);

		System.out.println("Click on save and exit button");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		// Yes button on confirmation

		try
		{		
		Click.on(driver, desicion_save_exit_button_confirm_yes, 10);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		}catch(Exception e) {}

	}

	public boolean verify_current_status_of_underwriting_after_sending_to_refer() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, underw_current_status, 60);

		String ScreenValueforRefer = underw_current_status.getText();

		System.out.println(
				"::::::::::::::::::::::::::::::Current Status for underwriting for refer :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");

		System.out.println(" Current Status of refer screen value is = " + ScreenValueforRefer);
		LO.print("Current Status of refer value is = " + ScreenValueforRefer);

		boolean CurrentStatusofUndewriting = ScreenValueforRefer.equals("Referred");

		System.out.println(
				"Mapping the current Status of Referred in Underwriting page is =" + CurrentStatusofUndewriting);

		boolean flag = false;
		if (CurrentStatusofUndewriting)

		{

			flag = true;
		}

		System.out.println(
				":::::::::::::::::::Referred status is verified on underwriting listing page:: ::::::::::::::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		return flag;

	}

	public boolean verify_current_status_of_underwriting_after_sending_to_accept() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, underw_current_status, 60);

		String ScreenValueforAccept = underw_current_status.getText();

		System.out.println(
				"::::::::::::::::::::::::::::::Current Status for underwriting for Accept  :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		System.out.println(" Current Status of accept screen value is = " + ScreenValueforAccept);
		LO.print("Current Status of accept value is = " + ScreenValueforAccept);

		boolean CurrentStatusofUndewriting = ScreenValueforAccept.equals("Accepted");

		System.out.println(
				" Mapping the current Status of Accept in Underwriting page is   = " + CurrentStatusofUndewriting);

		boolean flag = false;
		if (CurrentStatusofUndewriting)

		{

			flag = true;
		}

		System.out.println(
				":::::::::::::::::::::::Accept status is verified on underwriting listing page:::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println(" ");
		System.out.println(" ");

		return flag;

	}

	public boolean verify_current_status_of_underwriting_after_sending_to_accept_with_changes() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, underw_current_status, 60);

		String ScreenValueforAccept = underw_current_status.getText();

		System.out.println(
				"::::::::::::::::::::::::::::::Current Status for underwriting for Accept  :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		System.out.println(" Current Status of accept screen value is = " + ScreenValueforAccept);
		LO.print("Current Status of accept value is = " + ScreenValueforAccept);

		boolean CurrentStatusofUndewriting = ScreenValueforAccept.equals("Conditional acceptance");

		System.out.println(
				" Mapping the current Status of Accept in Underwriting page is   = " + CurrentStatusofUndewriting);

		boolean flag = false;
		if (CurrentStatusofUndewriting)

		{

			flag = true;
		}

		System.out.println(
				":::::::::::::::::::::::Accept status is verified on underwriting listing page:::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println(" ");
		System.out.println(" ");

		return flag;

	}

	public boolean verify_current_status_of_underwriting_after_sending_to_direct_accept() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, underw_current_status, 60);

		String ScreenValueforAccept = underw_current_status.getText();

		System.out.println(
				"::::::::::::::::::::::::::::::Current Status for underwriting for  direct Accept  :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		System.out.println(" Current Status of accept screen value is = " + ScreenValueforAccept);
		LO.print("Current Status of accept value is = " + ScreenValueforAccept);

		boolean CurrentStatusofUndewriting = ScreenValueforAccept.equals("Accepted");

		System.out.println(
				" Mapping the current Status of Accept in Underwriting page is   = " + CurrentStatusofUndewriting);

		boolean flag = false;
		if (CurrentStatusofUndewriting)

		{

			flag = true;
		}

		System.out.println(
				":::::::::::::::::::::::Accept status is verified on underwriting listing page:::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println(" ");
		System.out.println(" ");

		return flag;

	}

	public void find_decision_decline() throws InterruptedException

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, underw_decision_decline_tab, 60);
		underw_decision_decline_tab.click();

	}
	
	public void find_decision_refer() throws InterruptedException

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, underw_decision_refer_tab, 60);
		underw_decision_refer_tab.click();
		

	}


	public boolean verify_current_status_of_underwriting_after_sending_to_decline() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, underw_current_status, 60);

		String ScreenValueforDecline = underw_current_status.getText();

		System.out.println(
				"::::::::::::::::::::::::::::::Current Status for decline  :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");

		System.out.println(" Current Status of decline screen value is = " + ScreenValueforDecline);
		LO.print("Current Status of decline value is = " + ScreenValueforDecline);

		boolean CurrentStatusofUndewriting = ScreenValueforDecline.equals("Declined");

		System.out.println(
				" Mapping the current Status of decline in Underwriting page is    = " + CurrentStatusofUndewriting);

		boolean flag = false;
		if (CurrentStatusofUndewriting)

		{

			flag = true;
		}

		System.out.println(
				":::::::::::::::::::::::Declined status is verified on underwriting listing page:::::::::::::::::::::::::::::::::::::::::::");
		System.out.println(" ");
		System.out.println(" ");
		return flag;

	}

}
