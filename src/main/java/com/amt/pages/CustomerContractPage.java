package com.amt.pages;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	CustomerContractPage obj_asset_funding;
	
	

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
	private WebElement select_invoice_button;
		
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement save_invoice_button;
	
	@FindBy(xpath = "//*[@id='0amountAllocated']")
	private WebElement amount_to_allocate_input;
	
	@FindBy(xpath = "//*[@id='0amountAllocated']/following::td[1]/div")
	private WebElement amount_due;
	
	
	@FindBy(xpath = "//*[text()='Save payment']")
	private WebElement button_save_payment;
	
	@FindBy(xpath = "//*[text()='Payment Required']")
	private WebElement status_payment_required;
	
	@FindBy(xpath = "//*[text()='Payment Received']")
	private WebElement status_payment_received;
	
	@FindBy(xpath = "//*[text()='Payments']")
	private WebElement status_payment;
	
	
	
//	@FindBy(xpath = "(//label[normalize-space()='Payment requested']//ancestor::div[1]//input)[1]")
//	private WebElement date_payment_requested_for_doc_fee;
//
//	@FindBy(xpath = "(//label[normalize-space()='Payment sent']//ancestor::div[1]//input)[1]")
//	private WebElement date_payment_sent_for_doc_fee;
//
//	@FindBy(xpath = "(//label[normalize-space()='Payment requested']//ancestor::div[1]//input)[2]")
//	private WebElement date_payment_requested_for_finance_deposit;
//
//	@FindBy(xpath = "(//label[normalize-space()='Payment sent']//ancestor::div[1]//input)[2]")
//	private WebElement date_payment_sent_for_finance_deposit;

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
	// default funder delete button
	@FindBy(xpath = "//*[text()=' Selected ']//ancestor::div[1]//button[2]")
	private WebElement default_funder_delete_button;

	// selected funder row
	@FindBy(xpath = "//*[text()=' Selected ']//ancestor::div[1]//button[2]|//*[text()=' Select ']//ancestor::div[1]//button[2]")
	private WebElement selected_funder;

	@FindBy(xpath = "//tbody//tr")
	private WebElement edit_funder;

	// selected button
	@FindBy(xpath = "//*[text()=' Selected ']|//*[text()=' Select ']")
	private WebElement selected_button;

	// toaster message
	@FindBy(xpath = "//*[contains(@class,'toast-message')]")
	private WebElement toaster;

	// upload input
	@FindBy(xpath = "//*[text()='Upload']//ancestor::div[1]//input")
	private WebElement funder_doc_upload_button;

	// doc preview
	@FindBy(xpath = "//*[@id='hcChpViewUploadedDoc']")
	private WebElement funder_doc_review_button;

	@FindBy(xpath = "//*[text()='Contract types & OTR']")
	private WebElement contract_types_and_otr_page;

	@FindBy(xpath = "//*[@id='contractType1']//div")
	private List<WebElement> contract_types_list;

	@FindBy(xpath = "//*[@id='contract_change_modal']//*[text()='Yes']")
	private WebElement change_contract_confirm_yes;

// Holding Cost Elements

	@FindBy(xpath = "//*[@name='Terms']")
	private WebElement additional_terms;

	@FindBy(xpath = "//*[@name='MileagePerAnnum']")
	private WebElement additional_mileage;

	@FindBy(xpath = "//p[contains(text(),'Holding cost')]")
	private WebElement holding_cost;

	@FindBy(xpath = "//*[contains(text(),' Holding cost summary ')]")
	private WebElement holding_cost_summary;

	@FindBy(xpath = "//*[contains(text(),'CAP residual value')]//ancestor::div[1]//p//strong")
	private WebElement holding_cost_summary_residual_value_used;

	@FindBy(xpath = "//*[@id='ResidualValue']")
	private WebElement holding_cost_summary_residual_value_used_input_field;

	@FindBy(xpath = "//*[contains(text(),'Term')]//ancestor::div[1]//p//strong")
	private WebElement holding_cost_summary_terms;

	@FindBy(xpath = "//*[contains(text(),'Miles per annum')]//ancestor::div[1]//p//strong")
	private WebElement holding_cost_summary_mileage;

	@FindBy(xpath = "//*[contains(text(),'Total monthly holding cost')]//ancestor::div[1]//p//strong")
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

	@FindBy(xpath = "//*[contains(text(),'Total CAP maint. value')]//ancestor::div[1]/p")
	private WebElement total_cap_maintenance_value;

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

	@FindBy(xpath = "//input[@id='finalBalloonPayment']")
	private WebElement final_balloon_payment;

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

	@FindBy(xpath = "//*[contains(text(),'CAP monthly maint. cost')]//ancestor::div[1]//p//strong")
	private WebElement cap_monthly_maint_cost;

	@FindBy(xpath = "//input[@id='monthlyMaintenanceRental']")
	private WebElement monthly_maintenance_rental;

	@FindBy(xpath = "//input[@id='pencePerExcessMileageMaintenance']")
	private WebElement pense_per_excess_mile_maintenance;

	@FindBy(xpath = "//input[@id='percentageOfSaleProceedToCustomer']")
	private WebElement percentage_of_sale_proceed_to_customer;

	@FindBy(xpath = "//input[@id='secondaryHirePeriodRental']")
	private WebElement secondary_hire_period_rental;

	@FindBy(xpath = "//i[@class='btn-icon-addAddress-white']")
	private WebElement add;

	@FindBy(xpath = "//*[normalize-space()='Total CAP maint. value']//ancestor::div[1]//p/strong")
	private WebElement total_cap_maintenance_cost;

	@FindBy(xpath = "//*[text()='Update']")
	private WebElement update_funder;

//	@FindBy(xpath = "//*[contains(@class, 'rTableCell')]")
//	private List<WebElement> ownbook_holding_cost_matrix;

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
	
	


	ReadExcelCalculation obj_read_excel_calculation_page;

	Properties prop;

	CustomerContractPage obj_asset_funding_page;

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

	public void pre_order_pass_check() throws InterruptedException
	{
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
		
		
		Click.on(driver, select_invoice_button, 10);
		
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


	
	

