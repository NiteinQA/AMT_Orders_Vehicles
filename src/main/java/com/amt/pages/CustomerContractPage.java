package com.amt.pages;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.amt.HoldingCostPages.HoldingCost_BCH_BCH_Page;
import com.amt.HoldingCostPages.HoldingCost_CP_BCH_Page;
import com.amt.HoldingCostPages.HoldingCost_FL_PCHPage;
import com.amt.HoldingCostPages.HoldingCost_HPNR_HPRPage;
import com.amt.QuoteSummaryPages.QuoteSummary_HPNR_HPRPage;
import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.CommonClass;
import com.amt.testUtil.Difference;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.JavaScriptExecutor;
import com.amt.testUtil.ReadExcelCalculation;
import com.amt.testUtil.RemoveComma;

public class CustomerContractPage extends TestBase {

	JavascriptExecutor js;
	
	AcquisitionListingPage obj_acq_listing_page;

		

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details")
	private List<WebElement> stocking_plan_list;

	// Asset funding Element
	@FindBy(xpath = "//*[contains(text(),'Customer contract')]")
	private WebElement customer_contract;

	// cash purchase pending
	@FindBy(xpath = "//*[@id='complete-funding-alert']//*[contains(text(),' Confirm')]")
	private WebElement confirm_complete;

	// Stocking plan - Lombard Demo
	@FindBy(xpath = "//*[contains(text(),'Lombard Demo')]")
	private WebElement stocking_plan_lombard_demo;

	// Stocking plan - Remove
	@FindBy(xpath = "//*[contains(text(),'Remove')]")
	private WebElement stocking_plan_remove;

	@FindBy(xpath = "//*[@id='removeConfirmBtn']")
	private WebElement confirm_remove_button;

	@FindBy(xpath = "//*[@id='reject']")
	private WebElement reject_checkbox;

	@FindBy(xpath = "//*[@id='rejectionReason']")
	private WebElement rejection_reason_input_field;

	@FindBy(xpath = "//*[@id='dropdownRole']")
	private WebElement roles_dropdown;

	@FindBy(xpath = "//*[contains(text(), 'Super Admin')]")
	private WebElement super_admin;

	// Search box input
	@FindBy(xpath = "//input[@placeholder='Search something here']")
	private WebElement stocking_plan_settings_search_bar;

	@FindBy(xpath = "//tbody//tr")
	private WebElement search_output;

//	// Confirm complete funding
//	@FindBy(xpath = "//*[@id='complete-funding-alert']//*[contains(text(),'Confirm')]")
//	private WebElement confirm_complete_funding;

	@FindBy(xpath = "//input[@id='creditLimit']")
	private WebElement setting_credit_limit;

	@FindBy(xpath = "//input[@id='maxVehicleAgeInMonths']")
	private WebElement setting_max_vehicle_age_in_months;

	@FindBy(xpath = "//input[@id='minimalPurchasePrice']")
	private WebElement setting_min_purchase_price;

	@FindBy(xpath = "//input[@id='maximumPurchasePrice']")
	private WebElement setting_max_purchase_price;

	@FindBy(xpath = "//input[@id='maximumVehicleMileage']")
	private WebElement setting_max_vehicel_mileage;

	// Status - Finance Documents
	// **********************************************************

	@FindBy(xpath = "//*[@title='Finance Documents']")
	private WebElement finance_documents;

	@FindBy(xpath = "//*[text()='Finance Documents']")
	private WebElement status_finance_documents;

	@FindBy(xpath = "//*[text()=' Finance Docs Requested ']")
	private WebElement status_finance_documents_requested;

	@FindBy(xpath = "//*[text()=' Finance Docs Sent ']")
	private WebElement status_finance_documents_sent;

	@FindBy(xpath = "//*[text()=' Finance Docs Received ']")
	private WebElement status_finance_documents_received;

	@FindBy(xpath = "//*[text()=' Finance Docs Signed ']")
	private WebElement status_finance_documents_signed;

	@FindBy(xpath = "//*[text()=' Finance Docs Rejected ']")
	private WebElement status_finance_documents_rejected;

	@FindBy(xpath = "//*[text()=' Uploaded to Funder ']")
	private WebElement status_upload_to_funder;

	@FindBy(xpath = "//*[text()=' Finance Activated ']")
	private WebElement status_finance_document_finance_activated;
	



	// Input - Finance Documents
	// **********************************************************

	@FindBy(xpath = "//*[@id='agreementRequested']")
	private WebElement date_agreement_requested;

	@FindBy(xpath = "//span[contains(@class, 'customTodayClass')]")
	private WebElement pick_a_date;

	@FindBy(xpath = "//*[@id='agreementSent']")
	private WebElement date_agreement_sent;

	@FindBy(xpath = "//*[@id='agreementReceived']")
	private WebElement date_agreement_received;

	@FindBy(xpath = "//*[text()='Upload document']//ancestor::div[1]//div//div//div//div//input")
	private WebElement upload_documents;

	@FindBy(xpath = "//*[@id='uploadedToFunder']")
	private WebElement date_uploaded_to_funder;

	@FindBy(xpath = "//*[@id='acceptedByFunder']")
	private WebElement date_accepted_by_funder;

	@FindBy(xpath = "//*[@id='financeactivated']")
	private WebElement date_finance_activated;

	// Input - Payments to funder
	// **********************************************************
	
	
	@FindBy(xpath = "//*[@id='paymentOne']//*[text()=' Generate invoice ']")
	private WebElement button_generate_invoice_in_payments;

	@FindBy(xpath = "//*[@id='confirmGenerateInvoiceDeliverySection']//*[text()='Yes']")
	private WebElement button_generate_invoice_cofirm;	
		
	@FindBy(xpath = "//*[text()=' New payment ']")
	private WebElement button_new_payment;
	
	
	@FindBy(xpath = "//*[@id='idPaymentInvoice']")
	private WebElement input_payment_amount;
	
	
	@FindBy(xpath = "//*[@id='paymentDate']")
	private WebElement date_payment;
	
	@FindBy(xpath = "//*[@id='idPaymentInvoice']")
	private WebElement pop_up_input_payment_amount;
	
	@FindBy(xpath = "//*[text()='Payment type']//ancestor::div[1]//ng-select")
	private WebElement payment_type;
	
	@FindBy(xpath = "//*[text()='Payment type']//ancestor::div[1]//ng-select//*[text()='Banking']")
	private WebElement payment_type_option_banking;
	
	@FindBy(xpath = "//*[text()='Receipt']//ancestor::div[1]//div//div//button")
	private WebElement payment_receipt_upload_button;
	
	@FindBy(xpath = "//*[text()='Add invoice/deposit ']")
	private WebElement add_invoice_button;
	
	@FindBy(xpath = "//button[text()=' Select ']")
	private List<WebElement> select_invoice_button;
		
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement save_invoice_button;
	
	@FindBy(xpath = "//*[@id='0amountAllocated']")
	private WebElement amount_to_allocate_input;
	
	@FindBy(xpath = "//*[@id='0amountAllocated']/following::td[1]/div")
	private WebElement amount_due;	
	
	@FindBy(xpath = "//*[contains(@id, 'amountAllocated')]/following::td[1]/div")
	private List<WebElement> list_of_amount_due_values_on_pop_up;
	
	@FindBy(xpath = "//label[text()='Invoiced total']//following::div[1]")
	private WebElement Invoiced_total;	
	
	@FindBy(xpath = "//label[text()='Amount allocated']//following::div[1]")
	private WebElement amount_allocated;
	
	@FindBy(xpath = "//*[text()='Save payment']")
	private WebElement button_save_payment;
	
	@FindBy(xpath = "//*[text()='Payment Required']")
	private WebElement status_payment_required;
	
	@FindBy(xpath = "//*[text()='Payment Received']")
	private WebElement status_payment_received;
	
	@FindBy(xpath = "//*[text()='Payments']")
	private WebElement status_payment;
	
	// Select a funder **********************************************************

	@FindBy(xpath = "//button[normalize-space()='Select']")
	private WebElement select_a_funder;

	@FindBy(xpath = "//*[text()=' Required ']")
	private WebElement asset_funding_status_required;

	@FindBy(xpath = "//*[text()=' Funded ']")
	private WebElement asset_funding_status_funded;

	@FindBy(xpath = "//*[@id='btnBack']")
	private WebElement back_button;

//**************************************************************************



	// toaster message
	@FindBy(xpath = "//*[contains(@class,'toast-message')]")
	private WebElement toaster;




// Holding Cost Elements


	@FindBy(xpath = "//*[text()='Quote ref:']//ancestor::div[1]//p")
	private WebElement quote_id_link;

	@FindBy(xpath = "//*[text()=' Save ']")
	private WebElement save_button;

	// Pre - order check marks

	@FindBy(xpath = "//*[text()=' Customer contract summary ']//ancestor::div[1]/div/div[2]")
	private WebElement customer_contract_summary_check_mark;

	@FindBy(xpath = "//*[text()=' OTR ']//ancestor::div[1]/div/div[2]")
	private WebElement OTR_check_mark;

	@FindBy(xpath = "//*[text()=' Funder quote ']//ancestor::div[1]/div/div[2]")
	private WebElement funder_quote_check_mark;

	@FindBy(xpath = "//*[text()=' Delivery ']//ancestor::div[1]/div/div[2]")
	private WebElement delivery_check_mark;
	

	@FindBy(xpath = "//*[text()=' Sales notes ']//ancestor::div[1]/div/div[2]")
	private WebElement sales_notes_check_mark;

	@FindBy(xpath = "//*[text()=' Pass ']")
	private WebElement pass_button;

	// **************************************
	
	@FindBy(xpath = "//*[@id='funderName']")
	private WebElement funder_name;
	
	//***********************
	//Delivery status
	
	//Status Payout pack uploaded 
	@FindBy(xpath = "//*[text()='Payout pack uploaded']")
	private WebElement status_payout_pack_uploaded;
	
	//Status Payout pack accepted
	@FindBy(xpath = "//*[text()='Payout pack accepted']")
	private WebElement status_payout_pack_accepted;
	
	//Date Payout pack uploaded
	@FindBy(xpath = "//*[normalize-space()='Payout pack uploaded']//input")
	private WebElement date_payout_pack_uploaded;

	//Date Payout pack accepted
	@FindBy(xpath = "//*[normalize-space()='Payout pack accepted']//input")
	private WebElement date_payout_pack_accepted;
	
	//*******************************

	//button Complete order 
	@FindBy(xpath = "//*[@id='paymentPackUploaded']")
	private WebElement complete_order;
	
	// Status Completed  
	@FindBy(xpath = "//*[text()='Completed']//ancestor::div[1]//div/span[2]")
	private WebElement status_completed;

	// Complete order confirmation
	@FindBy(xpath = "//*[@id='confirmPayout']//*[contains(text(),' Confirm')]")
	private WebElement confirm_complete_order;	
	
	//*********************************	
	// searched output
	@FindBy(xpath = "//tbody/tr")
	private WebElement searched_output;
	
	//List of header element
	
	@FindBy(xpath = "//thead/tr/th")
	private List<WebElement> list_of_header_elements_in_searched_output;
	
	//****************
	//status acceptance condition	
	
	@FindBy(xpath = "//*[text()='Acceptance conditions']")
	private WebElement status_acceptance_condition;
	
	@FindBy(xpath = "//*[text()='Satisfied']")
	private WebElement status_acceptance_condition_satisfied;
	
	@FindBy(xpath = "//*[text()='Not Required']")
	private WebElement status_acceptance_condition_not_required;
	
	//********************
	
	
	//Doc Fee	
	@FindBy(xpath = "//*[@id='adminFee']")
	private WebElement doc_fee_value;
	
	//Vehicle Invoice Value	
	@FindBy(xpath = "//*[@id='VehicleInvoicePaymnet']")
	private WebElement vehicle_invoice_value;
	
	//Order Deposit	
	@FindBy(xpath = "//*[@id='OrderDeposit']")
	private WebElement order_deposit_value;
	
	//Doc fee generate invoice
	@FindBy(xpath = "//*[text()=' Document fee ex. VAT ']//ancestor::div[2]//div[2]//*[text()=' Generate invoice ']")
	private WebElement doc_fee_generate_invoice_button;
	
	//Doc fee generate invoice
	@FindBy(xpath = "//*[text()='Vehicle invoice ex. VAT']//ancestor::div[2]//div[2]//*[text()=' Generate invoice ']")
	private WebElement vehicle_invoice_generate_invoice_button;	
	
	//Generate invoice confirm yes
	@FindBy(xpath = "//*[@id='confirmGenerateInvoiceDeliverySection']//*[text()='Yes']")
	private WebElement generate_invoice_confirm_button;
	
	
//  vehicle details
	@FindBy(xpath = "//*[@class='heading ng-star-inserted']")
	private WebElement customer_contract_tab_vehicle_heading;

	// customer quote summary button
	@FindBy(xpath = "//*[normalize-space()='Quote summary']//ancestor::button")
	private WebElement customer_contract_tab_customer_quote_summary_button;

	// terms
	@FindBy(xpath = "//*[normalize-space()='Term']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_terms;

	// Miles per annum
	@FindBy(xpath = "//*[normalize-space()='Miles per annum']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_miles_per_annum;

	// Funder name
	@FindBy(xpath = "//*[normalize-space()='Funder']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_funder_name;

	// quote ref no.
	@FindBy(xpath = "//*[normalize-space()='Quote reference']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_ref_number;

	// quote exp date
	@FindBy(xpath = "//*[normalize-space()='Quote expiry date']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_exp_date;

	// payment profile
	@FindBy(xpath = "//*[normalize-space()='Payment profile']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_payment_profile;

	// contract mileage
	@FindBy(xpath = "//*[normalize-space()='Contract mileage']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_contract_mileage;

	// Initial finance rental
	@FindBy(xpath = "//*[normalize-space()='Initial finance rental']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_initial_finance_rental;

	// initial maint rental
	@FindBy(xpath = "//*[normalize-space()='Initial maint. rental']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_initial_maint_rental;

	// Total initial rental
	@FindBy(xpath = "//*[normalize-space()='Total initial rental']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_total_initial_rental;

	// Pence per excess mile - finance
	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - finance']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_pence_per_excess_mile_finance;

	// Pence per excess mile - maint.
	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - maint.']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_pence_per_excess_mile_maint;

	// Pence per excess mile - total
	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - total']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_pence_per_excess_mile_total;

	@FindBy(xpath = "//*[normalize-space()='Contract type']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_contract_type;

	@FindBy(xpath = "//*[normalize-space()='Cost OTR price']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_cost_otr_price;

	@FindBy(xpath = "//*[normalize-space()='Cost price ex. VAT & RFL']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_cost_price_ex_vat_and_rfl;

	@FindBy(xpath = "//*[normalize-space()='VAT']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_otr_vat;

	@FindBy(xpath = "//*[normalize-space()='RFL & FRF']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_otr_rfl_and_frf;

	@FindBy(xpath = "//*[normalize-space()='Quote ref:']//ancestor::div[1]//p")
	private WebElement customer_contract_tab_ref_no;

	// Total cash price
	@FindBy(xpath = "//*[normalize-space()='Total cash price']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_total_cash_price;

	// Cash deposit
	@FindBy(xpath = "//*[normalize-space()='Cash deposit']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_cash_deposit;

	// Balance to finance
	@FindBy(xpath = "//*[normalize-space()='Balance to finance']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_balance_to_finance;

	// Finance charges
	@FindBy(xpath = "//*[normalize-space()='Finance charges']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_finance_charges;

	// Balance payable
	@FindBy(xpath = "//*[normalize-space()='Balance payable']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_balance_payable;

	// Initial cash payment inc. document fee
	@FindBy(xpath = "//*[normalize-space()='Initial cash payment inc. document fee']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_initial_cash_payment_inc_document_fee;

	// No. of monthly payments
	@FindBy(xpath = "//*[normalize-space()='No. of monthly payments']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_no_of_monthly_payments;

	// Final balloon payment
	@FindBy(xpath = "//*[normalize-space()='Final balloon payment']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_final_balloon_payment;

	// Option to purchase fee
	@FindBy(xpath = "//*[normalize-space()='Option to purchase fee']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_option_to_purchase_fee;

	// RFL included?
	@FindBy(xpath = "//*[normalize-space()='RFL included?']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_RFL_included;

	// APR
	@FindBy(xpath = "//*[normalize-space()='APR']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_APR;

	// commission
	@FindBy(xpath = "//*[normalize-space()='Commission']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_commission;

	// Monthly finance payment
	@FindBy(xpath = "//*[normalize-space()='Monthly finance payment']//ancestor::div[1]//div//strong|//*[normalize-space()='Monthly finance rental']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_monthly_finance_payment;
	
	// Edit underwriting pop up
	@FindBy(xpath = "(//button[normalize-space()='Yes'])[3]")
	private WebElement edit_underwriting_pop_up;
	
	//Optional final payment	
	@FindBy(xpath = "//*[normalize-space()='Optional final payment']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_optional_final_payment	;

	//Customer Quote heading
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[1]/button/div")//*[normalize-space()='Customer quote summary']//ancestor::button
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_value_verification;

	@FindBy(xpath = "//*[normalize-space()='Term']//ancestor::div[1]//div/p")
	private WebElement customer_contract_tab_customer_quote_term;

	@FindBy(xpath = "//*[normalize-space()='Miles per annum']//ancestor::div[1]//div/p")
	private WebElement customer_contract_tab_customer_quote_miles;

	@FindBy(xpath = "//*[normalize-space()='Payment profile']//ancestor::div[1]//div/p")
	private WebElement customer_contract_tab_customer_quote_payment_profile;

	@FindBy(xpath = "//*[normalize-space()='Initial finance rental']//ancestor::div[1]//div/p/strong|//*[normalize-space()='Initial cash payment']//ancestor::div[1]//div/p/strong")
	private WebElement customer_contract_tab_customer_quote_initial_finance_rental;  

	@FindBy(xpath = "//*[normalize-space()='Monthly finance rental']//ancestor::div[1]//div/p/strong|//*[normalize-space()='Monthly finance payment']//ancestor::div[1]//div/p/strong")
	private WebElement customer_contract_tab_customer_quote_monthly_finance_rental;
	
	@FindBy(xpath = "//*[normalize-space()='Initial maint. rental']//ancestor::div[1]//div/p/strong")
	private WebElement customer_contract_tab_customer_quote_initial_maint_rental;

	@FindBy(xpath = "//*[normalize-space()='Total initial rental']//ancestor::div[1]//div/p/strong")
	private WebElement customer_contract_tab_customer_quote_initial_total_rental;

	@FindBy(xpath = "//*[normalize-space()='Part exchange value']//ancestor::div[1]//div/p/strong")
	private WebElement customer_contract_tab_customer_quote_part_exchange_value;

	@FindBy(xpath = "//*[normalize-space()='Followed by']//ancestor::div[1]//div/p/strong")
	private WebElement customer_contract_tab_customer_quote_followed_by;

	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - finance']//ancestor::div[1]//div/p/strong")
	private WebElement customer_contract_tab_customer_quote_pence_per_excess_mile_finance;

	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - maint.']//ancestor::div[1]//div/p/strong")
	private WebElement customer_contract_tab_customer_quote_pence_per_excess_mile_maintenance;

	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - total']//ancestor::div[1]//div/p/strong")
	private WebElement customer_contract_tab_customer_quote_pence_per_excess_mile_total;

	@FindBy(xpath = "//*[normalize-space()='Document fee']//ancestor::div[1]//div/p/strong")
	private WebElement customer_contract_tab_customer_quote_doc_fee;

//	@FindBy(xpath = "//*[normalize-space()='Upsell']//ancestor::div[1]//p/strong")
//	private WebElement customer_contract_tab_customer_quote_upsell;
	
	@FindBy(xpath = "//*[@id='Upsell']")
	private WebElement customer_contract_tab_customer_quote_upsell;

	@FindBy(xpath = "//*[normalize-space()='Default finance comm.']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_customer_quote_default_finance_commission;

	@FindBy(xpath = "//*[normalize-space()='Upsell comm.']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_customer_quote_upsell_commission;

	@FindBy(xpath = "//*[normalize-space()='Document fee comm.']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_customer_quote_doc_fee_commission;

	@FindBy(xpath = "//*[normalize-space()='Maint. comm.']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_customer_quote_maint_commission;

	@FindBy(xpath = "//*[normalize-space()='Total comm.']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_customer_quote_total_commission;

	@FindBy(xpath = "//*[normalize-space()='Referrer comm.']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_customer_quote_referrer_commission;
	
	@FindBy(xpath = "//*[normalize-space()='Configuration']//ancestor::button")
	private WebElement customer_contract_tab_configuration_heading_button;

	@FindBy(xpath = "//*[normalize-space()='Base interest rate']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_base_interest_rate;

	@FindBy(xpath = "//*[normalize-space()='Finance margin']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_finance_margin;

	@FindBy(xpath = "//*[normalize-space()='Deductions']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_deductions;

	@FindBy(xpath = "//*[normalize-space()='Additional margin']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_additional_margin;

	@FindBy(xpath = "//*[normalize-space()='Total margin']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_total_margin;

	@FindBy(xpath = "//*[normalize-space()='Default broker margin']//ancestor::div[1]//div/div/label")
	private WebElement customer_contract_tab_default_broker_margin_percentage;
	
			
	@FindBy(xpath = "(//*[normalize-space()='Default broker margin'])[2]//ancestor::div[1]//div/div/label")
	private WebElement customer_contract_tab_default_broker_margin_value;

	@FindBy(xpath = "(//*[normalize-space()='Broker upsell margin']//ancestor::div[1]//div//label)[1]")
	private WebElement customer_contract_tab_broker_upsell_margin_percentage;

	@FindBy(xpath = "(//*[normalize-space()='Broker upsell margin']//ancestor::div[1]//div//label)[2]//b")
	private WebElement customer_contract_tab_broker_upsell_margin;

	@FindBy(xpath = "//*[normalize-space()='Maint. margin']//ancestor::div[1]//div//label/strong")
	private WebElement customer_contract_tab_maintenance_margin;

	@FindBy(xpath = "//*[normalize-space()='Document fee margin']//ancestor::div[1]//div//label/b")
	private WebElement customer_contract_tab_decument_fee_margin;

	@FindBy(xpath = "//*[normalize-space()='Referrer margin']//ancestor::div[1]//div//label/b")
	private WebElement customer_contract_tab_refferer_margin;
	
	@FindAll({ @FindBy(xpath = "//*[@class='ng-dropdown-panel-items scroll-host']//div//ancestor::div[1]//div//div") })
	public List<WebElement> payment_profiles_options_list;	
	
	@FindBy(xpath = "//*[@id='Maintainedcontract']")
	private WebElement maint_contract_checkbox;
	
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[1]/button/div")
	private WebElement customer_contract_tab_customer_quote_summary_value_verification;
	// terms
	@FindBy(xpath = "//*[normalize-space()='Term']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_terms;

	// Miles per annum
	@FindBy(xpath = "//*[normalize-space()='Miles per annum']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_miles_per_annum;

	// basic cash price
	@FindBy(xpath = "//*[normalize-space()='Basic cash price']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_basic_cash_price;

	// customer_quote_summary vat
	@FindBy(xpath = "//*[normalize-space()='VAT']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_vat;

	// non vat items
	@FindBy(xpath = "//*[normalize-space()='Non-VAT items']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_non_vat_items;

	// Total cash price
	@FindBy(xpath = "//*[normalize-space()='Total cash price']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_total_cash_price;

	// Order deposit
	@FindBy(xpath = "//app-purchase-customer-quote-summary-detail//*[normalize-space()='Order deposit']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_order_deposit;

	// Finance deposit
	@FindBy(xpath = "//app-purchase-customer-quote-summary-detail//*[normalize-space()='Finance deposit']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_finance_deposit;

	// Total deposit
	@FindBy(xpath = "//app-purchase-customer-quote-summary-detail//*[normalize-space()='Total deposit']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_total_deposit;

	@FindBy(xpath = "//app-purchase-customer-quote-summary-detail//*[normalize-space()='Part exchange value']//ancestor::div[1]//div/p/strong")
	private WebElement customer_contract_tab_customer_quote_summary_part_exchange_value;

	// Balance to finance
	@FindBy(xpath = "//*[normalize-space()='Balance to finance']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_balance_to_finance;

	// Finance charges
	@FindBy(xpath = "//*[normalize-space()='Finance charges']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_finance_charges;

	// Document fee
	@FindBy(xpath = "//*[normalize-space()='Document fee']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_document_fee;

	// Balance payable
	@FindBy(xpath = "//*[normalize-space()='Balance payable']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_balance_payable;

	// Option to purchase fee
	@FindBy(xpath = "//*[normalize-space()='Option to purchase fee']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_option_to_purchase_fee;

	// Initial cash payment
	@FindBy(xpath = "//*[normalize-space()='Initial cash payment']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_initial_cash_payment;

	// Followed by
	@FindBy(xpath = "//*[normalize-space()='Followed by']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_followed_by;

	// Monthly finance payment
	@FindBy(xpath = "//*[normalize-space()='Monthly finance payment']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_monthly_finance_payment;
	
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
	private WebElement customer_contract_tab_customer_quote_summary_monthly_maint_payment;

	// Total Monthly payment
	@FindBy(xpath = "(//*[normalize-space()='Total monthly payment']//ancestor::div[1]//div//strong)[2]")
	private WebElement customer_contract_tab_customer_quote_summary_total_monthly_payment;

	// Balloon
	@FindBy(xpath = "//*[normalize-space()='Balloon']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_balloon;

	// Final payment (inc. option to purchase fee)
	@FindBy(xpath = "//*[normalize-space()='Final payment (inc. option to purchase fee)']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_final_payment_inc_option_to_purchase_fee;

	// Pence per excess mile - maint.
	@FindBy(xpath = "//*[normalize-space()='Pence per excess mile - maint.']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_pence_per_excess_mile_maint;

	// Credit type
	@FindBy(xpath = "//*[normalize-space()='Credit type']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_underwriting_quote_tab_credit_type;

	// Vehicle comm.
	@FindBy(xpath = "//*[normalize-space()='Vehicle comm.']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_vehicle_comm;

	// Default finance comm.
	@FindBy(xpath = "//*[normalize-space()='Default finance comm.']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_default_finance_comm;

	// Maintenance commision
	@FindBy(xpath = "//*[normalize-space()='Maint. comm.']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_maintenance_commision;

	// Document fee comm.
	@FindBy(xpath = "//*[normalize-space()='Document fee comm.']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_document_fee_comm;

	// Total comm.
	@FindBy(xpath = "//*[normalize-space()='Total comm.']//ancestor::div[1]//div//strong")
	private WebElement customer_contract_tab_customer_quote_summary_total_comm;
	
	// customer base over rate

	@FindBy(xpath = "//*[@id='customerBaseOverRate']")
	private WebElement customer_contract_tab_configuration_configuration_customer_base_over_rate;

	// Customer interest rate
	@FindBy(xpath = "(//*[normalize-space()='Customer interest rate']//ancestor::div[1]//div//strong)[2]")
	private WebElement customer_contract_tab_configuration_configuration_customer_interest_rate;



	// Base Interest Rate
	@FindBy(xpath = "//*[normalize-space()='Base interest rate']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_configuration_base_interest_rate;

	// Finance margin
	@FindBy(xpath = "//*[normalize-space()='Finance margin']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_configuration_finance_margin;

	// Deductions
	@FindBy(xpath = "//*[normalize-space()='Deductions']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_configuration_deductions;

	// Additional Margin
	@FindBy(xpath = "//*[normalize-space()='Additional margin']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_configuration_additional_margin;

	// Total Margin
	@FindBy(xpath = "//*[normalize-space()='Total margin']//ancestor::div[1]//p/strong")
	private WebElement customer_contract_tab_configuration_total_margin;

	// Margin Percentage
	@FindBy(xpath = "//*[normalize-space()='Default broker margin']//ancestor::div[1]//div/div/label")
	private WebElement customer_contract_tab_configuration_default_broker_margin_percentage;

	// Maintenance Margin
	@FindBy(xpath = "//*[normalize-space()='Maint. margin']//ancestor::div[1]//div//label/b")
	private WebElement customer_contract_tab_configuration_maintenance_margin;

	// Document Fee Margin
	@FindBy(xpath = "//*[normalize-space()='Document fee margin']//ancestor::div[1]//div//label/b")
	private WebElement customer_contract_tab_configuration_decument_fee_margin;

	// Base Input Rate Input
	@FindBy(xpath = "//input[@id='baseIntRate']")
	private WebElement customer_contract_tab_configuration_configuration_base_int_rate_input;

	// Maintenance Margin Input
	@FindBy(xpath = "//input[@id='MaintenanceMarginPer']")
	private WebElement customer_contract_tab_configuration_configuration_maintenance_margin_input;

	
	// default broker Margin Input
	@FindBy(xpath = "//*[normalize-space()='Default broker margin']//ancestor::div[1]//div//label/b")
	private WebElement customer_contract_tab_configuration_configuration_default_broker_margin;

	// Total tracker cost
	@FindBy(xpath = "//*[normalize-space()='Total tracker cost']//ancestor::div[1]//div//label/b")
	private WebElement customer_contract_tab_configuration_configuration_total_tracker_cost;

	// Insurance tax
	@FindBy(xpath = "//*[normalize-space()='Insurance tax']//ancestor::div[1]//div//label/b")
	private WebElement customer_contract_tab_configuration_configuration_insurance_tax;

	// Contingency insurance value
	@FindBy(xpath = "//*[normalize-space()='Contingency insurance value']//ancestor::div[1]//div//label/b")
	private WebElement customer_contract_tab_configuration_configuration_contingency_insurance_value;


	ReadExcelCalculation obj_read_excel_calculation_page;

	Properties prop;

	
	Actions act;

	CustomerContractPage obj_customer_contract_tab;
	
	VehicleOrderPage obj_vehicle_order_tab;

	HoldingCost_HPNR_HPRPage obj_holding_cost_page;

	HoldingCost_CP_BCH_Page obj_holding_cost_CP_BCH_page;

	QuoteSummary_HPNR_HPRPage obj_quote_summary_page;

	HoldingCost_FL_PCHPage obj_holding_cost_FL_PCH_page;

	HoldingCost_BCH_BCH_Page obj_holding_cost_BCH_BCH_page;
	
    OrdersListPage  obj_orderlist_page;
	 
	public CustomerContractPage() {
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

	public boolean verify_default_status_for_acceptance_condition_on_customer_contract_tab() throws InterruptedException, IOException, AWTException {
		
		
		
		LO.print("");
		System.out.println("");
		
		Thread.sleep(8000);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
		Click.on(driver, customer_contract, 20);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
				
		LO.print("Opening Customer Contract Tab");
		System.out.println("Opening Customer Contract Tab");
	
		
		LO.print("Started verifying acceptance conditions default status");
		System.out.println("Started verifying acceptance conditions default status");

		
		ExplicitWait.visibleElement(driver, status_acceptance_condition_satisfied, 10);

		String elementColor = "";
		elementColor = status_acceptance_condition_satisfied.getCssValue("color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Default status for acceptance condition  - Found OK i.e Satisfied");
			System.out.println("Default status for acceptance condition  - Found OK i.e Satisfied");
			return true;

		} else {
			LO.print("Default status for acceptance condition - Found Wrong");
			System.err.println("Default status for acceptance condition - Found Wrong");
			return false;
		}

	}
	

	public boolean verify_default_status_for_acceptance_condition_on_customer_contract_tab_for_ownbook() throws InterruptedException, IOException, AWTException {
		
		
		
		LO.print("");
		System.out.println("");
		
		Thread.sleep(8000);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
		Click.on(driver, customer_contract, 20);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
				
		LO.print("Opening Customer Contract Tab");
		System.out.println("Opening Customer Contract Tab");
	
		
		LO.print("Started verifying acceptance conditions default status");
		System.out.println("Started verifying acceptance conditions default status");

		
		ExplicitWait.visibleElement(driver, status_acceptance_condition_not_required, 10);

		String elementColor = "";
		elementColor = status_acceptance_condition_not_required.getCssValue("color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Default status for acceptance condition for ownbook - Found OK i.e Not Required");
			System.out.println("Default status for acceptance condition for ownbook - Found OK i.eNot Required");
			return true;

		} else {
			LO.print("Default status for acceptance condition for ownbook - Found Wrong");
			System.err.println("Default status for acceptance condition for ownbook - Found Wrong");
			return false;
		}

	}
	
	
	public boolean verify_payment_values_shown_in_payment_section() throws InterruptedException, IOException, AWTException, ClassNotFoundException {
		
		LO.print("");
		System.out.println("");
		
		LO.print("Started verifying payment values shown under payment section");
		System.out.println("Started verifying payment values shown under payment section");
		
		//wait for elements
        ExplicitWait.visibleElement(driver, doc_fee_value, 20);
        ExplicitWait.visibleElement(driver, vehicle_invoice_value, 20);
        ExplicitWait.visibleElement(driver, order_deposit_value, 20);
        
		//get values from screen
        double vehicleInvoiceActual = Double.parseDouble(RemoveComma.of(vehicle_invoice_value.getAttribute("value")));
        double docFeeActual         = Double.parseDouble(RemoveComma.of(doc_fee_value.getAttribute("value")));
		double orderDepositActual   = Double.parseDouble(RemoveComma.of(order_deposit_value.getAttribute("value")));
		
		//get values from excel
		
		String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();

		obj_acq_listing_page = new AcquisitionListingPage();
				
		String sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		double vehicleInvoiceExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(10, 1, sheetName));
		double docFeeExpected         = Double.parseDouble(GetExcelFormulaValue.get_cell_value(4, 1, sheetName));
		double orderDepositExpected   = Double.parseDouble(GetExcelFormulaValue.get_cell_value(7, 1, sheetName));
		
		
		//Printing Actual and Expected Values on console
		LO.print          ("");
		System.out.println("");			
		LO.print          ("Vehicle Invoice Actual = "+vehicleInvoiceActual);
		System.out.println("Vehicle Invoice Actual = "+vehicleInvoiceActual);
	
		LO.print          ("Vehicle Invoice Expected = "+vehicleInvoiceExpected);
		System.out.println("Vehicle Invoice Expected = "+vehicleInvoiceExpected);
					
		LO.print          ("");
		System.out.println("");			
		LO.print          ("Document Fee Actual = "+docFeeActual);
		System.out.println("Document Fee Actual = "+docFeeActual);
	
		LO.print          ("Document Fee Expected = "+docFeeExpected);
		System.out.println("Document Fee Expected = "+docFeeExpected);


		LO.print          ("");
		System.out.println("");			
		LO.print          ("Order Deposit Actual = "+orderDepositActual);
		System.out.println("Order Deposit Actual = "+orderDepositActual);

		LO.print          ("Order Deposit Expected = "+orderDepositExpected);
		System.out.println("Order Deposit Expected = "+orderDepositExpected);
		
		
		
		if(vehicleInvoiceActual==vehicleInvoiceExpected && docFeeActual==docFeeExpected && orderDepositActual==orderDepositExpected)
			
		{
			LO.print          ("Payment values shown under payment section have been verified and found OK");
			System.out.println("Payment values shown under payment section have been verified and found OK");

		return true;
		
		}
		else
		{
			LO.print          ("Payment values shown under payment section have been verified but found Wrong");
			System.err.println("Payment values shown under payment section have been verified but found Wrong");

		return false;

		}
	}

	
	public boolean verify_quote_summary_and_configuration_values_on_customer_contract_tab_for_ownbook_hire_flow()
			throws InterruptedException, ClassNotFoundException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		System.out.println("Started Verifying Quote Summary and Configuration Values");
		LO.print          ("Started Verifying Quote Summary and Configuration Values");
		
		ExplicitWait.visibleElement(driver, customer_contract_tab_vehicle_heading, 60);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_contract_type, 60);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_button, 120);

		// Cliking on cust quote summary section
		Click.on(driver, customer_contract_tab_customer_quote_summary_button, 60);

		// waiting for summary section elements
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_term, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_miles, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_monthly_finance_rental, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_initial_finance_rental, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_followed_by, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_pence_per_excess_mile_finance, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_doc_fee, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_upsell, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_default_finance_commission, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_upsell_commission, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_doc_fee_commission, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_total_commission, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_referrer_commission, 20);

		Thread.sleep(7000);
		
		Click.on(driver, customer_contract_tab_configuration_heading_button, 30);
		
		//waiting for Configuration elements 
		ExplicitWait.visibleElement(driver, customer_contract_tab_base_interest_rate, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_finance_margin, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_deductions, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_additional_margin, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_total_margin, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_default_broker_margin_percentage, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_broker_upsell_margin_percentage, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_broker_upsell_margin, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_decument_fee_margin, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_refferer_margin, 20);
		
		
		
	
		//reading customer quote summary values from screen 
		
		double customer_quote_summary_terms = Double
				.parseDouble(customer_contract_tab_customer_quote_term.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double
				.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_miles.getText().trim()));

		double customer_quote_summary_monthly_finance_rental = Double
				.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_monthly_finance_rental.getText().trim().substring(2)));

		double customer_quote_initial_finance_rental = Double.parseDouble(
				RemoveComma.of(customer_contract_tab_customer_quote_initial_finance_rental.getText().trim().substring(2)));


		double customer_payment_followed_by = Double
				.parseDouble(customer_contract_tab_customer_quote_followed_by.getText().substring(0, 2));

		double customer_quote_pence_per_excess_mile_finance = Double.parseDouble(
				customer_contract_tab_customer_quote_pence_per_excess_mile_finance.getText().trim().substring(0, 4));

		double customer_quote_summary_doc_fee = Double
				.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_doc_fee.getText().trim().substring(2)));

		double customer_quote_summary_upsell = Double
				.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_upsell.getAttribute("value")));

		double customer_quote_summary_default_finance_commission = Double.parseDouble(
				RemoveComma.of(customer_contract_tab_customer_quote_default_finance_commission.getText().trim().substring(2)));

		double customer_quote_summary_upsell_commission = Double.parseDouble(
				RemoveComma.of(customer_contract_tab_customer_quote_upsell_commission.getText().trim().substring(2)));

		double customer_quote_summary_doc_fee_commission = Double.parseDouble(
				RemoveComma.of(customer_contract_tab_customer_quote_doc_fee_commission.getText().trim().substring(2)));

		double customer_quote_summary_total_commision = Double.parseDouble(
				RemoveComma.of(customer_contract_tab_customer_quote_total_commission.getText().trim().substring(2)));

		double customer_quote_summary_referrer_commision = Double.parseDouble(
				RemoveComma.of(customer_contract_tab_customer_quote_referrer_commission.getText().trim().substring(2)));


		// reading configuration values from screen
		
		double baseInterestRateFromScreen = Double.parseDouble(customer_contract_tab_base_interest_rate.getText().trim().substring(0, 5));
		
		double financeMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_finance_margin.getText().trim().substring(2)));
		
		double deductionsFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_deductions.getText().trim().substring(2)));

		double additionalMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_additional_margin.getText().trim().substring(2)));

		double totalMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_total_margin.getText().trim().substring(2)));

		double defaultBrokerMarginPercentageFromScreen = Double.parseDouble(customer_contract_tab_default_broker_margin_percentage.getText().trim().substring(0, 4));

		double brokerUpsellMarginPercentageFromScreen = Double.parseDouble(customer_contract_tab_broker_upsell_margin_percentage.getText().trim().substring(0, 4));
	
		double brokerUpsellMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_broker_upsell_margin.getText().trim().substring(2)));

		double documentFeeMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_decument_fee_margin.getText().trim().substring(2)));

		double reffererMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_refferer_margin.getText().trim().substring(2)));

		
		// Vehicle details
		String vehicleNameActual = customer_contract_tab_vehicle_heading.getText().trim();

		ExplicitWait.visibleElement(driver, customer_contract_tab_ref_no, 30);

		String quotRefNoActual = customer_contract_tab_ref_no.getText();
		
		String contractTypeActual = customer_contract_tab_customer_contract_type.getText();
		
		
//*************************************************		
		
		
		//Getting calculation sheet name 
		
        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		obj_acq_listing_page = new AcquisitionListingPage();		
		String sheetName = obj_acq_listing_page.calculation_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		//Getting expected values from calculation sheet
	
		
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

	    DecimalFormat decimalFormat = new DecimalFormat("#.##");	    
	    double baseInterestRateFromExcel = Double.parseDouble(decimalFormat.format(tempbaseInterestRateFromExcel * 100));

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
		
		// 6.comparing monthly finance rental
		
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

		
		//7.Comparing Initial Finance rental
		
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
		
		
		//8.Comparing followed By
		
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

		
		//9.Comparing Pence per excess mile finance
		
		
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
		
		
		// 10.Comparing Document Fee

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
		
		//11.Comparing Upsell

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

		
		//12.Comparing Default Finance Commission
		
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
		
		
		//13.Comparing Upsell Commission

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
		
		
		//14.Comparing Document Fee Commission
		
		
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
		
		
		//15.Comparing Total Commission	

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
		
		//16.Comparing Referrer Commission	

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
		
		//17. Comparing Base Int Rate
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

		//18. Comparing Finance Margin
		
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

		
		//19. Comparing Deductions
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

		//20. Comparing Additional Margin
		
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

		
		//21. Comparing Total Margin
		
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

		//22. Comparing Default Broker Margin percentage
		
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
		
		
		//23. Comparing Broker Upsell Margin percentage

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
		
		//24. Comparing Broker Upsell Margin 

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
		
		
		//25. Comparing Document Fee Margin

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

		//26. Comparing Broker Upsell Margin
		
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
		if (count == 26)

		{
			status = true;
		}

		return status;

	}


	public boolean verify_quote_summary_and_configuration_values_on_customer_contract_tab_for_ownbook_purchase_flow()
			throws InterruptedException, ClassNotFoundException, IOException, UnsupportedFlavorException {

	
	
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		System.out.println("Started Verifying Quote Summary and Configuration Values");
		LO.print          ("Started Verifying Quote Summary and Configuration Values");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		ExplicitWait.visibleElement(driver, customer_contract_tab_vehicle_heading, 120);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_contract_type, 60);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_button, 120);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_heading_button, 120);
		
		// Cliking on cust quote summary section
		Click.on(driver, customer_contract_tab_customer_quote_summary_button, 60);

		// waiting for cust quote summary section elements
		
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_terms, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_miles_per_annum, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_basic_cash_price, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_vat, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_non_vat_items, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_total_cash_price, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_order_deposit, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_finance_deposit, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_total_deposit, 20);
		try{ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_part_exchange_value, 20);}catch(Exception e) {}
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_balance_to_finance, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_finance_charges, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_document_fee, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_balance_payable, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_option_to_purchase_fee, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_initial_cash_payment, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_followed_by, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_monthly_finance_payment, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_balloon, 20);
		ExplicitWait.visibleElement(driver,	quote_summary_customer_quote_summary_final_payment_inc_option_to_purchase_fee, 20);
//		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_finance, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_vehicle_comm, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_default_finance_comm, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_document_fee_comm, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_total_comm, 20);

		Thread.sleep(5000);
		
		Click.on(driver, customer_contract_tab_configuration_heading_button, 30);
		
		//waiting for Configuration elements 
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_base_interest_rate, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_finance_margin, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_deductions, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_additional_margin, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_total_margin, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_default_broker_margin_percentage, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_configuration_customer_interest_rate, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_decument_fee_margin, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_configuration_default_broker_margin, 30);		
		
		
	
		//reading customer quote summary values from screen 
		
		double customer_quote_summary_terms = Double.parseDouble(customer_contract_tab_customer_quote_summary_terms.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_miles_per_annum.getText().trim()));

		double customer_quote_summary_basic_cash_price = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_vat = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_vat.getText().trim().substring(2)));

		double customer_quote_summary_non_vat_items = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_non_vat_items.getText().trim().substring(2)));

		double customer_quote_summary_total_cash_price = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_total_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_order_deposit = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_order_deposit.getText().trim().substring(2)));

		double customer_quote_summary_finance_deposit = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_finance_deposit.getText().trim().substring(2)));

		double customer_quote_summary_total_deposit = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_total_deposit.getText().trim().substring(2)));

		double  customer_quote_summary_part_exchange_value =0;
		try{customer_quote_summary_part_exchange_value = Double.parseDouble(
				RemoveComma.of(customer_contract_tab_customer_quote_summary_part_exchange_value.getText().trim().substring(2)));}catch(Exception e) {}
 
		double customer_quote_summary_balance_to_finance = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));

		double customer_quote_summary_finance_charges = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_finance_charges.getText().trim().substring(2)));

		double customer_quote_summary_document_fee = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_document_fee.getText().trim().substring(2)));

		double customer_quote_summary_balance_payable = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_balance_payable.getText().trim().substring(2)));

		double customer_quote_summary_option_to_purchase_fee = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));

		double customer_quote_summary_initial_cash_payment = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_initial_cash_payment.getText().trim().substring(2)));

		double customer_payment_followed_by = Double.parseDouble(customer_contract_tab_customer_quote_summary_followed_by.getText().substring(0, 2));

		double customer_quote_summary_monthly_finance_payment = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));

		double customer_quote_summary_balloon = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_balloon.getText().trim().substring(2)));

        double customer_quote_summary_final_payment_inc_option_to_purchase_fee = Double.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_final_payment_inc_option_to_purchase_fee.getText()
				.trim().substring(2)));

    //    double customer_quote_summary_pence_per_excess_mile_finance = Double.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_pence_per_excess_mile_finance.getText().trim().substring(0, 4)));
		
				

		double customer_quote_summary_vehicle_comm = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_vehicle_comm.getText().trim().substring(2)));

		double customer_quote_summary_default_finance_comm = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_default_finance_comm.getText().trim().substring(2)));

		double customer_quote_summary_document_fee_comm = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_document_fee_comm.getText().trim().substring(2)));

		double customer_quote_summary_total_commission = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_total_comm.getText().trim().substring(2)));


		// reading configuration values from screen
		
    	double baseInterestRateFromScreen = Double.parseDouble(customer_contract_tab_configuration_base_interest_rate.getText().trim().substring(0, 5));
		
		double financeMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_configuration_finance_margin.getText().trim().substring(2)));
		
		double deductionsFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_configuration_deductions.getText().trim().substring(2)));
		
		double additionalMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_configuration_additional_margin.getText().trim().substring(2)));
		
		double totalMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_configuration_total_margin.getText().trim().substring(2)));
		
		double defaultBrokerMarginPercentageFromScreen = Double.parseDouble(customer_contract_tab_configuration_default_broker_margin_percentage.getText().trim().substring(0, 4));
		
		double customerInterestRateFromScreen = Double.parseDouble(customer_contract_tab_configuration_configuration_customer_interest_rate.getText().trim().substring(0, 5));
		
		double documentFeeMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_configuration_decument_fee_margin.getText().trim().substring(2)));

		// copying default broker margin from input field	
		double default_broker_margin_copied = Double.parseDouble(RemoveComma.of(customer_contract_tab_configuration_configuration_default_broker_margin.getText().trim().substring(2)));

    	// Vehicle details
		String vehicleNameActual = customer_contract_tab_vehicle_heading.getText().trim();

		ExplicitWait.visibleElement(driver, customer_contract_tab_ref_no, 30);

		String quotRefNoActual = customer_contract_tab_ref_no.getText();
		
		String contractTypeActual = customer_contract_tab_customer_contract_type.getText();
		
		
        //*************************************************		
		
		
		//Getting calculation sheet name 
		
        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		obj_acq_listing_page = new AcquisitionListingPage();		
		String sheetName = obj_acq_listing_page.calculation_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		//Getting expected values from calculation sheet

		//getting expected values for cust quote summary section elements
		
			
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
		
		double pencePerExcessMileFinance = GetExcelFormulaValue.get_formula_value(232, 4, sheetName);

		double vehicleCommission = GetExcelFormulaValue.get_formula_value(239, 0, sheetName);
		double defaultFinanceCommission = GetExcelFormulaValue.get_formula_value(239, 1, sheetName);

		double docFeeCommission = GetExcelFormulaValue.get_formula_value(242, 0, sheetName);
		double totalCommission = GetExcelFormulaValue.get_formula_value(242, 1, sheetName);

		//getting expected values for config section elements
		
		double tempbaseInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(256, 01, sheetName);
	    DecimalFormat decimalFormat = new DecimalFormat("#.##");	    
	    double baseInterestRateFromExcel = Double.parseDouble(decimalFormat.format(tempbaseInterestRateFromExcel * 100));
	    
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
        { expcount=35;} else {expcount=36;}

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
			LO.print          (ansiGreen+"Quote Summary and config values on customer contract tab verified successfully"+ansiReset);
			System.out.println(ansiGreen+"Quote Summary and config values on customer contract tab verified successfully"+ansiReset);
			System.out.println("");
			LO.print          ("");
			
		}else
		{
			System.out.println("");
			LO.print          ("");
			LO.print          ("One or More than One values on customer contract tab may be wrong , please check all above values printed on console");
			System.err.println("One or More than One values on customer contract tab may be wrong , please check all above values printed on console");
			System.out.println("");
			LO.print          ("");
		}

		return status;

	}

	public boolean verify_quote_summary_and_configuration_values_on_customer_contract_tab_for_ownbook_purchase_funder_flow()
			throws InterruptedException, ClassNotFoundException, IOException, UnsupportedFlavorException {


		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		System.out.println("Started Verifying Quote Summary and Configuration Values");
		LO.print          ("Started Verifying Quote Summary and Configuration Values");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		ExplicitWait.visibleElement(driver, customer_contract_tab_vehicle_heading, 120);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_contract_type, 60);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_button, 120);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_heading_button, 120);
		


		// Cliking on cust quote summary section
		Click.on(driver, customer_contract_tab_customer_quote_summary_button, 60);

		// waiting for cust quote summary section elements
		
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_terms, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_miles_per_annum, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_basic_cash_price, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_vat, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_non_vat_items, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_total_cash_price, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_order_deposit, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_finance_deposit, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_total_deposit, 20);
		try{ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_part_exchange_value, 20);}catch(Exception e) {}
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_balance_to_finance, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_finance_charges, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_document_fee, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_balance_payable, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_option_to_purchase_fee, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_initial_cash_payment, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_followed_by, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_monthly_finance_payment, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_balloon, 20);
		ExplicitWait.visibleElement(driver,	quote_summary_customer_quote_summary_final_payment_inc_option_to_purchase_fee, 20);
//		ExplicitWait.visibleElement(driver, quote_summary_customer_quote_summary_pence_per_excess_mile_finance, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_vehicle_comm, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_default_finance_comm, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_document_fee_comm, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_customer_quote_summary_total_comm, 20);

		Thread.sleep(5000);
		
		Click.on(driver, customer_contract_tab_configuration_heading_button, 30);
		
		//waiting for Configuration elements 
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_base_interest_rate, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_finance_margin, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_deductions, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_additional_margin, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_total_margin, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_default_broker_margin_percentage, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_configuration_customer_interest_rate, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_decument_fee_margin, 20);
		ExplicitWait.visibleElement(driver, customer_contract_tab_configuration_configuration_default_broker_margin, 30);		
		
		
		//reading customer quote summary values from screen 
		
		double customer_quote_summary_terms = Double.parseDouble(customer_contract_tab_customer_quote_summary_terms.getText().trim().substring(0, 2));

		double customer_quote_summary_miles = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_miles_per_annum.getText().trim()));

		double customer_quote_summary_basic_cash_price = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_basic_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_vat = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_vat.getText().trim().substring(2)));

		double customer_quote_summary_non_vat_items = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_non_vat_items.getText().trim().substring(2)));

		double customer_quote_summary_total_cash_price = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_total_cash_price.getText().trim().substring(2)));

		double customer_quote_summary_order_deposit = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_order_deposit.getText().trim().substring(2)));

		double customer_quote_summary_finance_deposit = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_finance_deposit.getText().trim().substring(2)));

		double customer_quote_summary_total_deposit = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_total_deposit.getText().trim().substring(2)));

		double  customer_quote_summary_part_exchange_value =0;
		try{customer_quote_summary_part_exchange_value = Double.parseDouble(
				RemoveComma.of(customer_contract_tab_customer_quote_summary_part_exchange_value.getText().trim().substring(2)));}catch(Exception e) {}
 
		double customer_quote_summary_balance_to_finance = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_balance_to_finance.getText().trim().substring(2)));

		double customer_quote_summary_finance_charges = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_finance_charges.getText().trim().substring(2)));

		double customer_quote_summary_document_fee = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_document_fee.getText().trim().substring(2)));

		double customer_quote_summary_balance_payable = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_balance_payable.getText().trim().substring(2)));

		double customer_quote_summary_option_to_purchase_fee = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_option_to_purchase_fee.getText().trim().substring(2)));

		double customer_quote_summary_initial_cash_payment = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_initial_cash_payment.getText().trim().substring(2)));

		double customer_payment_followed_by = Double.parseDouble(customer_contract_tab_customer_quote_summary_followed_by.getText().substring(0, 2));

		double customer_quote_summary_monthly_finance_payment = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_monthly_finance_payment.getText().trim().substring(2)));

		double customer_quote_summary_balloon = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_balloon.getText().trim().substring(2)));

        double customer_quote_summary_final_payment_inc_option_to_purchase_fee = Double.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_final_payment_inc_option_to_purchase_fee.getText()
				.trim().substring(2)));

    //    double customer_quote_summary_pence_per_excess_mile_finance = Double.parseDouble(RemoveComma.of(quote_summary_customer_quote_summary_pence_per_excess_mile_finance.getText().trim().substring(0, 4)));
		
				

		double customer_quote_summary_vehicle_comm = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_vehicle_comm.getText().trim().substring(2)));

		double customer_quote_summary_default_finance_comm = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_default_finance_comm.getText().trim().substring(2)));

		double customer_quote_summary_document_fee_comm = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_document_fee_comm.getText().trim().substring(2)));

		double customer_quote_summary_total_commission = Double.parseDouble(RemoveComma.of(customer_contract_tab_customer_quote_summary_total_comm.getText().trim().substring(2)));


		// reading configuration values from screen
		
    	double baseInterestRateFromScreen = Double.parseDouble(customer_contract_tab_configuration_base_interest_rate.getText().trim().substring(0, 5));
		
		double financeMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_configuration_finance_margin.getText().trim().substring(2)));
		
		double deductionsFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_configuration_deductions.getText().trim().substring(2)));
		
		double additionalMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_configuration_additional_margin.getText().trim().substring(2)));
		
		double totalMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_configuration_total_margin.getText().trim().substring(2)));
		
		double defaultBrokerMarginPercentageFromScreen = Double.parseDouble(customer_contract_tab_configuration_default_broker_margin_percentage.getText().trim().substring(0, 4));
		
		double customerInterestRateFromScreen = Double.parseDouble(customer_contract_tab_configuration_configuration_customer_interest_rate.getText().trim().substring(0, 5));
		
		double documentFeeMarginFromScreen = Double.parseDouble(RemoveComma.of(customer_contract_tab_configuration_decument_fee_margin.getText().trim().substring(2)));

		// copying default broker margin from input field	
		double default_broker_margin_copied = Double.parseDouble(RemoveComma.of(customer_contract_tab_configuration_configuration_default_broker_margin.getText().trim().substring(2)));

    	// Vehicle details
		String vehicleNameActual = customer_contract_tab_vehicle_heading.getText().trim();

		ExplicitWait.visibleElement(driver, customer_contract_tab_ref_no, 30);

		String quotRefNoActual = customer_contract_tab_ref_no.getText();
		
		String contractTypeActual = customer_contract_tab_customer_contract_type.getText();
		
		
        //*************************************************		
		
		
		//Getting calculation sheet name 
		
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
		
		double pencePerExcessMileFinance = GetExcelFormulaValue.get_formula_value(232, 4, sheetName);

		double vehicleCommission = GetExcelFormulaValue.get_formula_value(239, 0, sheetName);
		double defaultFinanceCommission = GetExcelFormulaValue.get_formula_value(239, 1, sheetName);

		double docFeeCommission = GetExcelFormulaValue.get_formula_value(242, 0, sheetName);
		double totalCommission = GetExcelFormulaValue.get_formula_value(242, 1, sheetName);

		//getting expected values for config section elements
		
		double tempbaseInterestRateFromExcel = GetExcelFormulaValue.get_formula_value(256, 01, sheetName);
				
	    DecimalFormat decimalFormat = new DecimalFormat("#.##");
	    
	    double baseInterestRateFromExcel = Double.parseDouble(decimalFormat.format(tempbaseInterestRateFromExcel * 100));

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
{ expcount=35;} else {expcount=36;}

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
			LO.print          (ansiGreen+"Quote Summary and Configuration Values on Customer Contract tab verified successfully"+ansiReset);
			System.out.println(ansiGreen+"Quote Summary and Configuration Values on Customer Contract tab verified successfully"+ansiReset);
			System.out.println("");
			LO.print          ("");
			
		}else
		{
			System.out.println("");
			LO.print          ("");
			LO.print          ("One or More than One values in Quote Summary and Configuration Values on Customer Contract tab may be wrong , please check all above values printed on console");
			System.err.println("One or More than One values in Quote Summary and Configuration Values on Customer Contract tab may be wrong , please check all above values printed on console");
			System.out.println("");
			LO.print          ("");
		}

		return status;

	}

	
	public boolean verify_payment_default_status_on_customer_contract_tab() throws InterruptedException {
		
		LO.print("");
		System.out.println("");

		LO.print("Started Verifying Payments  Statuses");
		System.out.println("Started Verifying Payments Statuses");

		JavaScriptExecutor.click(driver, status_payment);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		
		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_payment_required, 10);

		elementColor = status_payment_required.getCssValue("color");

		if (elementColor.equals("rgba(199, 92, 0, 1)")) {

			LO.print          ("Payment -- Default Status - Found OK i.e Payment Required");
			System.out.println("Payment -- Default Status - Found OK i.e Payment Required");
			 return true;

		} else {
			LO.print          ("Payment -- Default Status- Found Wrong");
			System.err.println("Payment -- Default Status- Found Wrong");
            return false;
		}
		
	}
	
	
	public void pre_order_pass_check() throws InterruptedException
	{
		
		try {
		LO.print("");
		System.out.println("");
		
		LO.print("Checking pre-order checks");
		System.out.println("Checking pre-order checks");
		
		Thread.sleep(7000);
		
		Click.on(driver, customer_contract_summary_check_mark, 10);
		
		Thread.sleep(1000);
		
		Click.on(driver, OTR_check_mark, 10);
		
		Thread.sleep(1000);
		try {
        Click.on(driver, funder_quote_check_mark, 10);
		}catch(Exception e)
		{
			
		}
		
		Thread.sleep(1000);		
		
        Click.on(driver, delivery_check_mark, 10);
		
		Thread.sleep(1000);
		
        Click.on(driver, sales_notes_check_mark, 10);
		
		Thread.sleep(1000);
		
		Click.on(driver, pass_button, 10);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		}catch(Exception e)
		{
			
		}


	}

	public boolean verify_finance_documents_statuses() throws InterruptedException, IOException {

		Click.on(driver, customer_contract, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		LO.print("");
		System.out.println("");

		LO.print("Started Verifying Finance Document Statuses for required flow");
		System.out.println("Started Verifying Finance Document Statuses for required flow");

		Click.on(driver, status_finance_documents, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		JavaScriptExecutor.scroll_in_to_view(driver, funder_name);
		// Click on Date agreement Received
		Click.on(driver, date_agreement_requested, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date
		CommonClass.move_courser();
		Click.on(driver, pick_a_date, 20);
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// Check default status is required

		int statusVerificationCount = 0;

		String elementBackgroundColor = "";

		ExplicitWait.visibleElement(driver, status_finance_documents_requested, 10);

		elementBackgroundColor = status_finance_documents_requested.getCssValue("color");

		if (elementBackgroundColor.equals("rgba(199, 92, 0, 1)")) {

			LO.print(
					"Finance Document Status after selecting agreement requested date- Found OK i.e Finance docs Requested");
			System.out.println(
					"Finance Document Status after selecting agreement requested date- Found OK i.e Finance docs Requested");
			statusVerificationCount++;

		} else {
			LO.print("Finance Document Status after selecting agreement requested date- Found Wrong");
			System.err.println("Finance Document Status after selecting agreement requested date- Found Wrong");

		}

		JavaScriptExecutor.scroll_in_to_view(driver, funder_name);

		// Click on Date agreement Sent
		Click.on(driver, date_agreement_sent, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date
		CommonClass.move_courser();
		Click.on(driver, pick_a_date, 20);
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		ExplicitWait.visibleElement(driver, status_finance_documents_sent, 10);

		elementBackgroundColor = status_finance_documents_sent.getCssValue("color");

		if (elementBackgroundColor.equals("rgba(199, 92, 0, 1)")) {

			LO.print("Finance Document Status after selecting agreement sent date- Found OK i.e Finance Docs sent");
			System.out.println(
					"Finance Document Status after selecting agreement sent date- Found OK i.e Finance Docs sent");
			statusVerificationCount++;

		} else {
			LO.print("Finance Document Status after selecting agreement sent date- Found Wrong");
			System.err.println("Finance Document Status after selecting sent received date- Found Wrong");

		}

		// Click on Date agreement received
		Click.on(driver, date_agreement_received, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date
		CommonClass.move_courser();
		Click.on(driver, pick_a_date, 20);
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		ExplicitWait.visibleElement(driver, status_finance_documents_signed, 10);

		elementBackgroundColor = status_finance_documents_signed.getCssValue("color");

		if (elementBackgroundColor.equals("rgba(199, 92, 0, 1)")) {

			LO.print(
					"Finance Document Status after selecting agreement received date- Found OK i.e Finance Docs signed");
			System.out.println(
					"Finance Document Status after selecting agreement received date- Found OK i.e Finance Docs signed");
			statusVerificationCount++;

		} else {
			LO.print("Finance Document Status after selecting agreement received date- Found Wrong");
			System.err.println("Finance Document Status after selecting received received date- Found Wrong");

		}

		JavaScriptExecutor.scroll_in_to_view(driver, funder_name);
		// Click on uploaded to funder
		Click.on(driver, date_uploaded_to_funder, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date
		CommonClass.move_courser();
		Click.on(driver, pick_a_date, 20);
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		ExplicitWait.visibleElement(driver, status_upload_to_funder, 10);

		elementBackgroundColor = status_upload_to_funder.getCssValue("color");

		if (elementBackgroundColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Finance Document Status after selecting uploaded to funder date- Found OK i.e Upload to Funder");
			System.out.println(
					"Finance Document Status after selecting uploaded to funder date- Found OK i.e Upload to Funder");
			statusVerificationCount++;

		} else {
			LO.print("Finance Document Status after selecting uploaded to funder date- Found Wrong");
			System.err.println("Finance Document Status after selecting uploaded to funder date- Found Wrong");

		}

		JavaScriptExecutor.scroll_in_to_view(driver, funder_name);
		// Click on uploaded to funder
		Click.on(driver, date_accepted_by_funder, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date
		CommonClass.move_courser();
		Click.on(driver, pick_a_date, 20);
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		
		Thread.sleep(3000);
		
		upload_documents.sendKeys(prop.getProperty("test_image_path"));
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		if (statusVerificationCount == 4) {
			return true;
		} else {
			return false;
		}
		
		

	}

	public boolean verify_payments_statuses() throws InterruptedException, IOException, AWTException {

		LO.print("");
		System.out.println("");

		LO.print("Started Verifying Payments  Statuses");
		System.out.println("Started Verifying Payments Statuses");

		JavaScriptExecutor.click(driver, status_payment);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		int statusVerificationCount = 0;

		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_payment_required, 10);

		elementColor = status_payment_required.getCssValue("color");

		if (elementColor.equals("rgba(199, 92, 0, 1)")) {

			LO.print("Payment -- Default Status - Found OK i.e Payment Required");
			System.out.println("Payment -- Default Status - Found OK i.e Payment Required");
			statusVerificationCount++;

		} else {
			LO.print("Payment -- Default Status- Found Wrong");
			System.err.println("Payment -- Default Status- Found Wrong");

		}

		JavaScriptExecutor.scroll_in_to_view(driver, date_accepted_by_funder);
		// Click on generate Invoice
		Thread.sleep(2000);
		Click.on(driver, button_generate_invoice_in_payments, 30);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		// Confirm pop up
		Click.on(driver, button_generate_invoice_cofirm, 30);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		
		obj_customer_contract_tab = new CustomerContractPage() ;
         
		obj_customer_contract_tab.make_payment_for_broker_flow(); 
		
		
		ExplicitWait.visibleElement(driver, status_payment_received, 10);

		elementColor = status_payment_received.getCssValue("color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Status for Payment after making payment - Found OK i.e Payment Received");
			System.out.println("Status for Payment after making payment - Found OK i.e Payment Received");
			statusVerificationCount++;

		} else {
			LO.print("Status for Payment after making payment - Found Wrong");
			System.err.println("Status for Payment after making payment - Found Wrong");

		}

		if (statusVerificationCount == 2) {
			return true;
		} else {
			return false;
		}

	}

	public void make_payment_for_broker_flow() throws InterruptedException, NumberFormatException, AWTException {
		
		
		   LO.print("");
		   System.err.println("");

		   LO.print("Generating Invoices");
		   System.err.println("Generating Invoices");
		   
		   Click.on(driver, doc_fee_generate_invoice_button, 10);
	       Thread.sleep(2000);
	       
		   Click.on(driver, generate_invoice_confirm_button, 10);
	       Thread.sleep(2000);
		   
	       ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
	       
	       Click.on(driver, vehicle_invoice_generate_invoice_button, 10);
	       Thread.sleep(2000);
	       
		   Click.on(driver, generate_invoice_confirm_button, 10);
	       Thread.sleep(2000);
	       
	       ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
		   Click.on(driver, button_new_payment, 10);
		   
		   Thread.sleep(3000);
		   
			 
			// Click on uploaded to funder
			Click.on(driver, date_payment, 20);
			Thread.sleep(8000);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

			// pick date
			CommonClass.move_courser();
			Click.on(driver, pick_a_date, 20);
			Thread.sleep(5000);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

			
			Click.on(driver, payment_type, 10);
			Thread.sleep(2000);
			Click.on(driver, payment_type_option_banking, 10);
			
			

			Click.on(driver, add_invoice_button, 10);
			Thread.sleep(2000);
			
			
				
			for(WebElement e: select_invoice_button)
			{
				Click.on(driver, e, 10);
				
				Thread.sleep(2000);
			}
			
			Click.on(driver, save_invoice_button, 10);
			
			ExplicitWait.visibleElement(driver, amount_due, 10);
			
			double amountDue = Double.parseDouble(RemoveComma.of(amount_due.getText().substring(2)));
			
			Actions act = new Actions(driver);
			
			ExplicitWait.visibleElement(driver, input_payment_amount, 10);
			
			input_payment_amount.sendKeys(Keys.chord(Keys.CONTROL, "a" , Keys.DELETE));
			
			Click.sendKeysdouble(driver, input_payment_amount, amountDue, 10);
			
			act.sendKeys(Keys.TAB).build().perform();
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
			
			
			
			ExplicitWait.visibleElement(driver, amount_to_allocate_input, 10);
			
			amount_to_allocate_input.sendKeys(Keys.chord(Keys.CONTROL, "a" , Keys.DELETE));
			
			Click.sendKeysdouble(driver, amount_to_allocate_input, amountDue, 10);
			
			act.sendKeys(Keys.TAB).build().perform();
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
			
			Thread.sleep(2000);
			
			
//		ExplicitWait.visibleElement(driver, payment_receipt_upload_button, 10);
//		payment_receipt_upload_button.sendKeys(prop.getProperty("test_image_path"));
//		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
			
			obj_vehicle_order_tab = new VehicleOrderPage();
			
			Click.on(driver, payment_receipt_upload_button, 30);
			
			obj_vehicle_order_tab.upload_file(payment_receipt_upload_button, prop.getProperty("test_image_path"));
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
			
			Click.on(driver, button_save_payment, 10);		
		
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
	}

	
	public void make_payment_for_outright_purchase() throws InterruptedException, NumberFormatException, AWTException {
		
		
		   LO.print("");
		   System.err.println("");
		   
		   
          try {
		   LO.print("Generating Invoices");
		   System.out.println("Generating Invoices");
		   
		   
		   Click.on(driver, doc_fee_generate_invoice_button, 5);
	       Thread.sleep(2000);
	       
		   Click.on(driver, generate_invoice_confirm_button, 5);
	       Thread.sleep(2000);
		   
	       ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
	       
	       Click.on(driver, vehicle_invoice_generate_invoice_button, 5);
	       Thread.sleep(2000);
	       
		   Click.on(driver, generate_invoice_confirm_button, 5);
	       Thread.sleep(2000);
	       
	       ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
          }catch(Exception e)
          {
        	  
          }
		
		   Click.on(driver, button_new_payment, 10);
		   
		   Thread.sleep(5000);
		   
			 
			// Click on uploaded to funder
			Click.on(driver, date_payment, 20);
			Thread.sleep(8000);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

			// pick date
			CommonClass.move_courser();
			Click.on(driver, pick_a_date, 20);
			Thread.sleep(5000);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

			
			Click.on(driver, payment_type, 10);
			Thread.sleep(2000);
			Click.on(driver, payment_type_option_banking, 10);
			
			

			Click.on(driver, add_invoice_button, 10);
			Thread.sleep(2000);
			
			
				
			for(WebElement e: select_invoice_button)
			{
				Click.on(driver, e, 10);
				
				Thread.sleep(2000);
			}
			
			Click.on(driver, save_invoice_button, 10); 
			
			
			
            ExplicitWait.visibleElement(driver, Invoiced_total, 10);
			
			double totalInvoiceValue = Double.parseDouble(RemoveComma.of(Invoiced_total.getText().substring(2)));
			
			Actions act = new Actions(driver);
			
			ExplicitWait.visibleElement(driver, input_payment_amount, 10);
			
			input_payment_amount.sendKeys(Keys.chord(Keys.CONTROL, "a" , Keys.DELETE));
			
			Click.sendKeysdouble(driver, input_payment_amount, totalInvoiceValue, 10);
			
			act.sendKeys(Keys.TAB).build().perform();
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
			
			
			obj_customer_contract_tab = new CustomerContractPage() ;
			
			obj_customer_contract_tab.get_value_for_amount_due_and_write_in_amount_to_allocate_field(0);
			obj_customer_contract_tab.get_value_for_amount_due_and_write_in_amount_to_allocate_field(1);
			obj_customer_contract_tab.get_value_for_amount_due_and_write_in_amount_to_allocate_field(2);
			
			
			
			
			
			
	
//		ExplicitWait.visibleElement(driver, payment_receipt_upload_button, 10);
//		payment_receipt_upload_button.sendKeys(prop.getProperty("test_image_path"));
//		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
			
			obj_vehicle_order_tab = new VehicleOrderPage();
			
			Click.on(driver, payment_receipt_upload_button, 30);
			
			obj_vehicle_order_tab.upload_file(payment_receipt_upload_button, prop.getProperty("test_image_path"));
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
			
			
			
			 ExplicitWait.visibleElement(driver, amount_allocated, 10);
				
				double amountAllocated = Double.parseDouble(RemoveComma.of(amount_allocated.getText().substring(2)));
				
							
				ExplicitWait.visibleElement(driver, input_payment_amount, 10);
				
				input_payment_amount.sendKeys(Keys.chord(Keys.CONTROL, "a" , Keys.DELETE));
				
				Click.sendKeysdouble(driver, input_payment_amount, amountAllocated+1, 10);
				
				act.sendKeys(Keys.TAB).build().perform();
				
				ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
			
			
			Click.on(driver, button_save_payment, 10);		
		
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
	}

	public void make_payment_for_ownbook_hire() throws InterruptedException, NumberFormatException, AWTException {
		
		
		   LO.print("");
		   System.err.println("");
		   
		   
       try {
		   LO.print("Generating Invoices");
		   System.out.println("Generating Invoices");
		   
		   
		   Click.on(driver, doc_fee_generate_invoice_button, 5);
	       Thread.sleep(2000);
	       
		   Click.on(driver, generate_invoice_confirm_button, 5);
	       Thread.sleep(2000);
	       
	       ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
	       
       }catch(Exception e)
       {
     	  
       }
		
		   Click.on(driver, button_new_payment, 10);
		   
		   Thread.sleep(5000);
		   
			 
			// Click on uploaded to funder
			Click.on(driver, date_payment, 20);
			Thread.sleep(8000);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

			// pick date
			CommonClass.move_courser();
			Click.on(driver, pick_a_date, 20);
			Thread.sleep(5000);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

			
			Click.on(driver, payment_type, 10);
			Thread.sleep(2000);
			Click.on(driver, payment_type_option_banking, 10);
			
			

			Click.on(driver, add_invoice_button, 10);
			Thread.sleep(2000);
			
			
				
			for(WebElement e: select_invoice_button)
			{
				Click.on(driver, e, 10);
				
				Thread.sleep(2000);
			}
			
			Click.on(driver, save_invoice_button, 10); 
			
			
			
         ExplicitWait.visibleElement(driver, Invoiced_total, 10);
			
			double totalInvoiceValue = Double.parseDouble(RemoveComma.of(Invoiced_total.getText().substring(2)));
			
			Actions act = new Actions(driver);
			
			ExplicitWait.visibleElement(driver, input_payment_amount, 10);
			
			input_payment_amount.sendKeys(Keys.chord(Keys.CONTROL, "a" , Keys.DELETE));
			
			Click.sendKeysdouble(driver, input_payment_amount, totalInvoiceValue, 10);
			
			act.sendKeys(Keys.TAB).build().perform();
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
			
			
			obj_customer_contract_tab = new CustomerContractPage() ;
			
			obj_customer_contract_tab.get_value_for_amount_due_and_write_in_amount_to_allocate_field(0);
			obj_customer_contract_tab.get_value_for_amount_due_and_write_in_amount_to_allocate_field(1);
			obj_customer_contract_tab.get_value_for_amount_due_and_write_in_amount_to_allocate_field(2);
			
			
			
	
			obj_vehicle_order_tab = new VehicleOrderPage();
			
			Click.on(driver, payment_receipt_upload_button, 30);
			
			obj_vehicle_order_tab.upload_file(payment_receipt_upload_button, prop.getProperty("test_image_path"));
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
			
			
			
			 ExplicitWait.visibleElement(driver, amount_allocated, 10);
				
				double amountAllocated = Double.parseDouble(RemoveComma.of(amount_allocated.getText().substring(2)));
				
							
				ExplicitWait.visibleElement(driver, input_payment_amount, 10);
				
				input_payment_amount.sendKeys(Keys.chord(Keys.CONTROL, "a" , Keys.DELETE));
				
				Click.sendKeysdouble(driver, input_payment_amount, amountAllocated+1, 10);
				
				act.sendKeys(Keys.TAB).build().perform();
				
				ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
			
			
			Click.on(driver, button_save_payment, 10);		
		
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
	}

	
	public void get_value_for_amount_due_and_write_in_amount_to_allocate_field(int indexOfElement) throws InterruptedException {		
		
		        String amountDueValueXpath = "//*[contains(@id, '" + indexOfElement + "amountAllocated')]/following::td[1]/div";
			     
		        double value = Double.parseDouble(RemoveComma.of(driver.findElement(By.xpath(amountDueValueXpath)).getText().substring(2)));
		        
		        System.out.println(value);
		        
		        String amountToAllocateValueXpath = "//*[contains(@id, '" + indexOfElement + "amountAllocated')]"; 
			    
		        WebElement element =  driver.findElement(By.xpath(amountToAllocateValueXpath));		        
		        
				ExplicitWait.visibleElement(driver, element, 10);
				
				element.sendKeys(Keys.chord(Keys.CONTROL, "a" , Keys.DELETE));
				
				Click.sendKeysdouble(driver, element, value, 10);
				
				Actions act = new Actions(driver);
				
				act.sendKeys(Keys.TAB).build().perform();
				
				ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);        	
		
	}
	
	
	public boolean verify_payments_status_after_making_a_payment() throws InterruptedException, IOException, AWTException {

		LO.print("");
		System.out.println("");

		String 	elementColor = "";
		
		ExplicitWait.visibleElement(driver, status_payment_received, 10);

		elementColor = status_payment_received.getCssValue("color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Status for Payment after making payment - Found OK i.e Payment Received");
			System.out.println("Status for Payment after making payment - Found OK i.e Payment Received");
			
			return true;

		} else {
			LO.print("Status for Payment after making payment - Found Wrong");
			System.err.println("Status for Payment after making payment - Found Wrong");
            return false;
		}


	}

	
	public boolean verify_delivery_status_on_customer_contract_tab() throws InterruptedException, IOException, AWTException, ClassNotFoundException {

		LO.print("");
		System.out.println("");

		LO.print("Started Verifying Delivery Statuses");
		System.out.println("Started Verifying Payments Statuses");
		
		obj_vehicle_order_tab = new VehicleOrderPage();
		
		boolean delivery_status_after_selecting_date_offered = obj_vehicle_order_tab.verify_delivery_status_after_selecting_date_offered_in_delivery_section_of_vehicle_tab();	
		
		boolean delivery_status_after_selecting_confirmed_delivery_date = obj_vehicle_order_tab.verify_delivery_status_after_selecting_confirmed_delivery_date_in_delivery_section_of_vehicle_tab();		
		
		if(delivery_status_after_selecting_date_offered==true && delivery_status_after_selecting_confirmed_delivery_date==true )
		{
			return true;
		}
		else
		{
			return false;
		}		
		
	}

	
	public boolean verify_post_delivery_status_on_customer_contract_tab() throws InterruptedException, IOException, AWTException, ClassNotFoundException {

		LO.print("");
		System.out.println("");

		LO.print("Started Verifying Post Delivery Statuses");
		System.out.println("Started Verifying Post Delivery Statuses");
		
		obj_vehicle_order_tab = new VehicleOrderPage();
		
		boolean delivery_status_after_uploading_delivery_note =  obj_vehicle_order_tab.verify_delivery_status_after_uploading_delivery_note_in_post_delivery_section_on_vehicle_order_tab();	
		 
		 obj_customer_contract_tab = new CustomerContractPage();
		 
		 boolean delivery_status_after_selecting_date_payout_pack_uploaded = obj_customer_contract_tab.verify_post_delivery_status_after_selecting_date_payout_pack_uploaded_in_post_delivery_section_of_customer_contract_tab();
		 
		 boolean delivery_status_after_selecting_date_payout_pack_accepted = obj_customer_contract_tab.verify_post_delivery_status_after_selecting_date_payout_pack_accepted_in_post_delivery_section_of_customer_contract_tab();
		 
		
			if(delivery_status_after_uploading_delivery_note==true && delivery_status_after_selecting_date_payout_pack_uploaded==true && delivery_status_after_selecting_date_payout_pack_accepted==true )
			{
				return true;
			}
			else
			{
				return false;
			}		

	}
	
	
    public boolean verify_post_delivery_status_after_selecting_date_payout_pack_uploaded_in_post_delivery_section_of_customer_contract_tab() throws InterruptedException, IOException, AWTException, ClassNotFoundException {
		
		obj_customer_contract_tab = new CustomerContractPage();
		
		obj_customer_contract_tab.select_date_payout_pack_uploaded_in_post_delivery_section_on_customer_order_tab();
		
		String elementBackgroundColor = "";

		ExplicitWait.visibleElement(driver, status_payout_pack_uploaded, 10);

		elementBackgroundColor = status_payout_pack_uploaded.getCssValue("background-color");

		if (elementBackgroundColor.equals("rgba(51, 65, 78, 1)")) {

			LO.print("Delivery Status after selecting date payout pack uploaded - Found OK i.e Payout pack uploaded");
			System.out.println("Delivery Status after selecting date payout pack uploaded - Found OK i.e Payout pack uploaded");
			 return true;

		} else {
			LO.print("Delivery Status after selecting date payout pack uploaded - Found Wrong");
			System.err.println("Delivery Status after selecting date payout pack uploaded - Found Wrong");
			return false;
		}
	}

    public boolean verify_post_delivery_status_after_selecting_date_payout_pack_accepted_in_post_delivery_section_of_customer_contract_tab() throws InterruptedException, IOException, AWTException, ClassNotFoundException {
	
	obj_customer_contract_tab = new CustomerContractPage();
	
	obj_customer_contract_tab.select_date_payout_pack_accepted_in_post_delivery_section_on_customer_order_tab();
	
	String elementBackgroundColor = "";

	ExplicitWait.visibleElement(driver, status_payout_pack_accepted, 10);

	elementBackgroundColor = status_payout_pack_accepted.getCssValue("background-color");

	if (elementBackgroundColor.equals("rgba(51, 65, 78, 1)")) {

		LO.print("Delivery Status after selecting date payout pack accepted - Found OK i.e Payout pack accepted");
		System.out.println("Delivery Status after selecting date payout pack accepted - Found OK i.e Payout pack accepted");
		 return true;

	} else {
		LO.print("Delivery Status after selecting date payout pack accepted - Found Wrong");
		System.err.println("Delivery Status after selecting date payout pack accepted - Found Wrong");
		return false;
	}
}

	
	public void select_date_payout_pack_uploaded_in_post_delivery_section_on_customer_order_tab()
			throws IOException, InterruptedException, AWTException, ClassNotFoundException {

		Thread.sleep(5000);
		// Click on  Date offered input
		Click.on(driver, date_payout_pack_uploaded, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date offered
		CommonClass.move_courser();
		Thread.sleep(5000);
		Click.on(driver, pick_a_date, 20);	
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);		

	}
	
	public void select_date_payout_pack_accepted_in_post_delivery_section_on_customer_order_tab()
			throws IOException, InterruptedException, AWTException, ClassNotFoundException {

		Thread.sleep(5000);
		// Click on  Date offered input
		Click.on(driver, date_payout_pack_accepted, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date offered
		CommonClass.move_courser();
		Thread.sleep(5000);
		Click.on(driver, pick_a_date, 20);	
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);		

	}
	
	
	public boolean verify_payout_pack_statuses() throws ClassNotFoundException, InterruptedException, IOException, AWTException {
		
		
		 obj_customer_contract_tab = new CustomerContractPage();
		 
		 Click.on(driver, customer_contract, 10);
		 
		 
		 ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		 
		 boolean flag = false;
		 try {
		 
		 boolean delivery_status_after_selecting_date_payout_pack_uploaded = obj_customer_contract_tab.verify_post_delivery_status_after_selecting_date_payout_pack_uploaded_in_post_delivery_section_of_customer_contract_tab();
		 
		 boolean delivery_status_after_selecting_date_payout_pack_accepted = obj_customer_contract_tab.verify_post_delivery_status_after_selecting_date_payout_pack_accepted_in_post_delivery_section_of_customer_contract_tab();

		 
		 if(delivery_status_after_selecting_date_payout_pack_uploaded==true && delivery_status_after_selecting_date_payout_pack_accepted==true)
		 {
			 
			 flag = true;
		 }else
		 {
			 flag = false;
		 }
		 }
		 catch(Exception e )
		 {
				LO.print("There are no Payout pack Statuses for this order as this is used car order");
				System.out.println("There are no Payout pack Statuses for this order as this is used car order");

		 }
		return flag;
		
	}

	
	public boolean verify_the_vehicle_is_seen_in_sold_vehicles_section_on_completing_the_order_on_customer_contract_page() throws InterruptedException, IOException {
		
		
		Click.on(driver, customer_contract, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
//		
//		LO.print("");
//		System.out.println("");
//
//		LO.print("Completing the Order");
//		System.out.println("Completing the Order");
//		
//		JavaScriptExecutor.scroll_in_to_view(driver, complete_order);
//		
//		ExplicitWait.clickableElement(driver, complete_order, 0);	
//		Click.on(driver, complete_order, 20);
//		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
//		
//		Thread.sleep(3000);
//		
//		Click.on(driver, confirm_complete_order, 20);		
//		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
//		
		
		LO.print("Opening Sold Vehicle Listing in New Tab");
		System.out.println("Opening Sold Vehicle Listing in New Tab");

		
		String ordersTab = driver.getWindowHandle();

		prop = new Properties();
		FileInputStream ip = new FileInputStream(
				"D:\\Orders_Vehicles\\AMT_Orders_Vehicles\\src\\main\\java\\configs\\config.properties");
		prop.load(ip);

		String targetURL = prop.getProperty("url") + "vehicle/vehicle-management/sold-vehicle";

		// Open New Window
		((JavascriptExecutor) driver).executeScript("window.open('" + targetURL + "', '_blank');");

		// Switch to the new tab
		
		 boolean status=false;
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(ordersTab)) {

				// Switch to New Window Opened
				driver.switchTo().window(handle);
				
				LO.print("Searching order in the listing");
				System.out.println("Searching order in the listing");

			    obj_orderlist_page = new OrdersListPage();

			    obj_orderlist_page.search_in_search_bar();		   
			    
			    try
			    {
			    if(searched_output.isDisplayed())
			    {
			    	status=true;
			    	
					LO.print("Order seen in sold vehicles section, Hence Test passed");
					System.out.println("Order seen in sold vehicles section, Hence Test passed");
			    }
			    }
			    catch(Exception e)
			    {
			    	
			    }				
			}		
	}
		return status;

	}

	
	
	public boolean verify_statuses_on_searched_output_in_sold_vehicles_for_order_completed_record() throws InterruptedException, IOException {
		
		
		LO.print("");
		System.out.println("");

		LO.print("Verifying statuses displayed on searched output");
		System.out.println("Verifying statuses displayed on searched output");
		
		ExplicitWait.waitForListOfVisibleElements(driver, list_of_header_elements_in_searched_output, 10);
		
		int noOfHeaderElements = list_of_header_elements_in_searched_output.size();
		
		obj_customer_contract_tab = new CustomerContractPage(); 
		
		boolean vehicleStatus = obj_customer_contract_tab.verify_statuses_on_searched_output(noOfHeaderElements, "VEHICLE STATUS", "Sold");
		

		boolean customerStatus = obj_customer_contract_tab.verify_statuses_on_searched_output(noOfHeaderElements, "CUSTOMER STATUS", "Completed");

		boolean acquisitionStatus = obj_customer_contract_tab.verify_statuses_on_searched_output(noOfHeaderElements, "ACQUISTION STATUS", "Completed");

		
		if(vehicleStatus==true && customerStatus==true && acquisitionStatus==true)
		{
			return true;
		}else
		{
			return false;
		}

	}

	public boolean verify_statuses_on_searched_output(int noOfHeaderElements, String headerElementText, String expectedStatusForHeaderElement) {
		
		
		boolean flag= false;
		
		for(int i=1; i<=noOfHeaderElements; i++)
		{
			String webElementText = driver.findElement(By.xpath("//thead/tr/th["+i+"]")).getText();
			
			
		
			if(webElementText.equalsIgnoreCase(headerElementText))
        	{
				String statusText = driver.findElement(By.xpath("//tbody/tr/td["+i+"]")).getText();
				
		
					if(statusText.equalsIgnoreCase(expectedStatusForHeaderElement))
		        	{
						LO.print("Status for "+webElementText+" is "+statusText+" Hence Test Passed");
						System.out.println("Status for "+webElementText+" is "+statusText+" Hence Test Passed");	
						
						flag =true ;
						
		        	}		
					
	        	}		
				
        	}
		return flag;
	}
	
}


	
	

