package com.amt.pages;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import com.amt.HoldingCostPages.HoldingCost_HPNR_HPRPage;
import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.CommonClass;
import com.amt.testUtil.Difference;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.JavaScriptExecutor;
import com.amt.testUtil.RemoveComma;

public class AssetFundingPage extends TestBase {

	JavascriptExecutor js;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details")
	private List<WebElement> stocking_plan_list;

	// Asset funding Element
	@FindBy(xpath = "//*[contains(text(),'Asset funding')]")
	private WebElement asset_funding;

	// Cash purchase Element
	@FindBy(xpath = "//*[contains(text(),' Cash purchase ')]")
	private WebElement status_cash_purchase;

	// Stocking plan Element
	@FindBy(xpath = "//*[contains(text(),' Stocking plan ')]")
	private WebElement status_stocking_plan;

	// Required Element
	@FindBy(xpath = "//*[contains(text(),' Required ')]")
	private WebElement status_required;

	// status Required
	@FindBy(xpath = "//*[contains(text(),'Required')]//ancestor::div[1]//div/span[2]")
	private WebElement status_Required;

	// status Pending Activation
	@FindBy(xpath = "//*[contains(text(),'Pending Activation')]//ancestor::div[1]//div/span[2]")
	private WebElement status_pending_activation;

	// status Finance Activated
	@FindBy(xpath = "//*[contains(text(),'Finance Activated')]//ancestor::div[1]//div/span[2]")
	private WebElement status_finance_activated;

	// status Finance Completed
	@FindBy(xpath = "//*[contains(text(),'Finance Completed')]//ancestor::div[1]//div/span[2]")
	private WebElement status_finance_completed;

	// funded Element
	@FindBy(xpath = "//*[contains(text(),' Funded ')]")
	private WebElement status_funded;
	


	// funded Element
	@FindBy(xpath = "//*[contains(text(),'Add Funder Quote')]")
	private WebElement add_funder_quote;

	// cash purchase pending
	@FindBy(xpath = "//*[contains(text(),'Cash Purchase Pending')]//ancestor::div[1]//div/span[2]")
	private WebElement status_cash_purchase_pending;

	// cash purchase pending
	@FindBy(xpath = "//*[contains(text(),'Cash Purchase Completed')]//ancestor::div[1]//div/span[2]")
	private WebElement status_cash_purchase_completed;

	// cash purchase pending
	@FindBy(xpath = "//*[contains(text(),'Complete Cash Purchase')]")
	private WebElement complete_cash_purchase;

	// complete funding
	@FindBy(xpath = "//*[contains(text(),'Complete funding')]")
	private WebElement complete_funding;	
	
	// complete funding
	@FindBy(xpath = "//*[text()='Completed']//ancestor::div[1]//div/span[2]")
	private WebElement status_completed;
	
	// cash purchase pending
	@FindBy(xpath = "//*[@id='complete-funding-alert']//*[contains(text(),' Confirm')]")
	private WebElement confirm_complete;

	@FindBy(xpath = "//*[@class='slider round sliderRed']")
	private WebElement maintenance_toggle_button;

	// *[contains(text(),'Status')]//ancestor::div[1]//div//div

	// Status of the stocking plan
	@FindBy(xpath = "//*[contains(text(),'Status')]//ancestor::div[1]//div//div")
	private WebElement status_of_stocking_plan;

	// Confirm button on add pop up
	@FindBy(xpath = "//*[@id='confirmBtn']")
	private WebElement confirm_add;

	// Status of the stocking plan
	@FindBy(xpath = "//*[contains(text(),'Status')]//ancestor::div[1]//div/div")
	private WebElement stocking_plan_status;

	// *[contains(text(),'Lombard Demo')]

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
	
	
	//Status - Quote **********************************************************

		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Quote']")
		private WebElement status_quote;

		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Quote Requested']")
		private WebElement status_quote_requested;

		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Quote Received']")
		private WebElement status_quote_received;
		
		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Quote Accepted']")
		private WebElement status_quote_accepted;
		
	//Status - Finance Documents **********************************************************	

		
		@FindBy(xpath = "//*[@title='Finance Documents']")
		private WebElement finance_documents;
		
		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Finance Documents']")
		private WebElement status_finance_documents;
		
		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Finance Docs Requested']")
		private WebElement status_finance_documents_requested;
		
		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Finance Docs Received']")
		private WebElement status_finance_documents_received;
		
		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Finance Docs Signed']")
		private WebElement status_finance_documents_signed;
		
		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Finance Docs Rejected']")
		private WebElement status_finance_documents_rejected;
		
		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Upload to funder']")
		private WebElement status_upload_to_funder;
		
		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Finance Activated']")
		private WebElement status_finance_document_finance_activated;
		
	//Status - Finance Documents **********************************************************	


		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Payment']")
		private WebElement status_payment;
		
		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='No Payments Required']")
		private WebElement status_no_payments_required;
		
	   @FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Payment Required']")
		private WebElement status_payment_required;
		
		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Payment Requested']")
		private WebElement status_payment_requested;
		
		@FindBy(xpath = "//*[@id='assetFundingContent']//*[text()='Payment Sent']")
		private WebElement status_payment_sent;
		
		
	//Input  - Finance Documents **********************************************************	


		@FindBy(xpath = "//*[@id='agreementRequested']")
		private WebElement date_agreement_requested;

		@FindBy(xpath = "//span[contains(@class, 'customTodayClass')]")
		private WebElement pick_a_date;
		
		@FindBy(xpath = "//*[@id='agreementSent']")
		private WebElement date_agreement_sent;
		
		@FindBy(xpath = "//*[@id='agreementReceived']")
		private WebElement date_agreement_received;
		
//		@FindBy(xpath = "//*[normalize-space()='Signed documents']//ancestor::div[1]//button[normalize-space()='Upload']")
//		private WebElement upload_signed_documents;
		
		@FindBy(xpath = "//*[text()='Signed documents']//ancestor::div[1]//div//div//input[@id='hcChpUploadBtn']")
		private WebElement upload_signed_documents;
		
		
		
		@FindBy(xpath = "//*[@id='uploadedToFunder']")
		private WebElement date_uploaded_to_funder;
		
		@FindBy(xpath = "//*[@id='acceptedByFunder']")
		private WebElement date_accepted_by_funder;
		
		@FindBy(xpath = "//*[@id='financeactivated']")
		private WebElement date_finance_activated;	
		
		
	//Input  - Payments to funder  **********************************************************		
		
		@FindBy(xpath = "//button[normalize-space()='Generate invoice']")
		private WebElement button_generate_invoice;
		
		@FindBy(xpath = "//*[@id='generate-invoice-confirm']//*[text()=' Confirm']")
		private WebElement button_generate_invoice_cofirm;
		
		@FindBy(xpath = "(//label[normalize-space()='Payment requested']//ancestor::div[1]//input)[1]")
		private WebElement date_payment_requested_for_doc_fee;
		
		@FindBy(xpath = "(//label[normalize-space()='Payment sent']//ancestor::div[1]//input)[1]")
		private WebElement date_payment_sent_for_doc_fee;
		
		@FindBy(xpath = "(//label[normalize-space()='Payment requested']//ancestor::div[1]//input)[2]")
		private WebElement date_payment_requested_for_finance_deposit;
		
		@FindBy(xpath = "(//label[normalize-space()='Payment sent']//ancestor::div[1]//input)[2]")
		private WebElement date_payment_sent_for_finance_deposit;
		
		
		
	//Select a funder  **********************************************************	
		
		
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

		@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/form/div/div/div[4]/div/p/strong")
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

	

	Properties prop;
	
	AssetFundingPage obj_asset_funding_page;

	Actions act;

	AssetFundingPage obj_vehicle_order_tab;
	
	HoldingCost_HPNR_HPRPage obj_holding_cost_page;

	public AssetFundingPage() {
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

	public boolean open_asset_funding_tab_and_complete_cash_purchase() throws InterruptedException {

		Click.on(driver, complete_cash_purchase, 30);

		Click.on(driver, confirm_complete, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		LO.print("Clicked on Complete Cash purchase");
		System.out.println("Clicked on Complete Cash purchase");

		if (!complete_cash_purchase.isEnabled()) {
			LO.print("Cash Purchase Completed");
			System.out.println("Cash Purchase Completed");

			return true;
		} else {
			LO.print("Cash Purchase Not Completed");
			System.err.println("Cash Purchase Not Completed");

			return false;
		}

	}

	public boolean verify_stocking_plan_is_not_enabled_for_delivery_location_non_AMT() throws InterruptedException {

		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, status_stocking_plan, 30);

		String className = status_stocking_plan.getAttribute("class");

		if (className.contains("disabled")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean verify_stocking_plan_Lombard_Demo_is_available_if_channel_is_internal_use()
			throws InterruptedException {

		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, status_stocking_plan, 30);

		Click.on(driver, status_stocking_plan, 20);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		// Check default status is required

		ExplicitWait.waitForListOfVisibleElements(driver, stocking_plan_list, 20);

		int noOfStockingPlan = stocking_plan_list.size();
		boolean nameMatching = false;
		try {

			ExplicitWait.visibleElement(driver, stocking_plan_lombard_demo, 10);

			String nameOfStockingPlan = stocking_plan_lombard_demo.getText();

			LO.print("Name of the Stocking plan is " + nameOfStockingPlan);
			System.out.println("Name of the Stocking plan is " + nameOfStockingPlan);

			LO.print("Number of the Stocking plan available is/are " + noOfStockingPlan);
			System.out.println("Number of the Stocking plan available is/are " + noOfStockingPlan);

			if (noOfStockingPlan == 1 && nameOfStockingPlan.contains("Lombard Demo")) {
				nameMatching = true;

				LO.print("For Channel Selection - Internal use , there should be only One Stocking plan available i.e. "
						+ nameOfStockingPlan);
				System.out.println(
						"For Channel Selection - Internal use , there should be only One Stocking plan available i.e. "
								+ nameOfStockingPlan);

				LO.print(
						"Hence it is clear that for Channel Internal Use - Right Stocking plan is shown as per condition");
				System.out.println(
						"Hence it is clear that for Channel Internal Use - Right Stocking plan is shown as per condition");

			}

		} catch (Exception e) {
			nameMatching = false;

			LO.print("Stocking plan shown is wrong for channel Internal Use");
			System.err.println("Stocking plan shown is wrong for channel Internal Use");
		}

		return nameMatching;
	}

	public boolean stocking_pan_Lombard_and_Black_Horse_Are_Available_If_Channel_Is_Ownbook()
			throws InterruptedException {

		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, status_stocking_plan, 30);

		Click.on(driver, status_stocking_plan, 20);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		// Check default status is required
		boolean nameMatching = false;
		try {
			ExplicitWait.waitForListOfVisibleElements(driver, stocking_plan_list, 20);

			int noOfStockingPlan = stocking_plan_list.size();

			for (int i = 1; i <= noOfStockingPlan; i++) {

				WebElement stockingPlan = driver.findElement(By.xpath(
						"(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i + "]//h5"));

				String nameOfStockingPlan = stockingPlan.getText();

				LO.print("Name of the " + i + " Stocking plan is " + nameOfStockingPlan);
				System.out.println("Name of the " + i + " Stocking plan is " + nameOfStockingPlan);

				if (!nameOfStockingPlan.contains("Demo")) {
					nameMatching = true;
					LO.print("For Single Cannel Selection i.e. OwnBook -- there would not be Lombard Demo So "
							+ nameOfStockingPlan + " is not a Lombard Demo, Hence Test Case Passed");
					System.out.println("For Single Cannel Selection i.e. OwnBook -- there would not be Lombard Demo So "
							+ nameOfStockingPlan + " is not a Lombard Demo, Hence Test Case Passed");
				}
			}
		} catch (Exception e) {

		}
		return nameMatching;
	}

	public boolean remove_stocking_plan_after_finance_activated() throws InterruptedException, ClassNotFoundException {

		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, status_stocking_plan, 30);

		Click.on(driver, status_stocking_plan, 20);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		// Check default status is required

		int statusVerificationCount = 0;

		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_Required, 10);

		elementColor = status_Required.getCssValue("background-color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Default Status for Stocking Plan found OK i.e.Required");
			System.out.println("Default Status for Stocking Plan found OK i.e.Required");
			statusVerificationCount++;

		} else {
			LO.print("Default Status for Stocking Plan found wrong");
			System.err.println("Default Status for Stocking Plan found wrong");

		}

		ExplicitWait.waitForListOfVisibleElements(driver, stocking_plan_list, 20);

		int noOfStockingPlan = stocking_plan_list.size();

		boolean currentStatus = false;

		for (int i = 1; i <= noOfStockingPlan; i++) {

			WebElement nameOfTheStockingPlan = driver.findElement(By.xpath(
					"(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i + "]//h5"));

			LO.print("Name of the " + i + " Stocking plan is " + nameOfTheStockingPlan.getText());
			System.out.println("Name of the " + i + " Stocking plan is " + nameOfTheStockingPlan.getText());

			WebElement statusOfTheStockingPlan = driver
					.findElement(By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)["
							+ i + "]//*[contains(text(),'Status')]//ancestor::div[1]//div/div"));

			LO.print("Status of the " + i + " Stocking plan is " + statusOfTheStockingPlan.getText());
			System.out.println("Status of the " + i + " Stocking plan is " + statusOfTheStockingPlan.getText());

			if (statusOfTheStockingPlan.getText().equalsIgnoreCase("Eligible") == true) {

				LO.print("Choosing " + i + " Stocking plan for this order");
				System.out.println("Choosing " + i + " Stocking plan for this order");

				WebElement addButton = driver.findElement(
						By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i
								+ "]//*[contains(text(),'Add')]"));

				addButton.click();

				Thread.sleep(2000);

				confirm_add.click();

				ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

				// check status Pending Activation

				ExplicitWait.visibleElement(driver, status_pending_activation, 10);

				elementColor = status_pending_activation.getCssValue("background-color");

				if (elementColor.equals("rgba(91, 158, 63, 1)")) {

					LO.print("Status for Stocking Plan after adding - found OK i.e.Pending Activation");
					System.out.println("Status for Stocking Plan after adding - found OK i.e.Pending Activation");
					statusVerificationCount++;

				} else {
					LO.print("Status for Stocking Plan after adding - found Wrong");
					System.err.println("Status for Stocking Plan after adding - found Wrong");

				}

				WebElement statusOfTheStockingPlan2 = driver.findElement(
						By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i
								+ "]//*[contains(text(),'Status')]//ancestor::div[1]//div/div"));

				LO.print("Current Status of the " + i + " Stocking plan after adding is "
						+ statusOfTheStockingPlan2.getText());
				System.out.println("Current Status of the " + i + " Stocking plan after adding is "
						+ statusOfTheStockingPlan2.getText());

				break;

			}
		}

		int i = 0;
		while (i < 10) {

			driver.navigate().refresh();
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

			Click.on(driver, asset_funding, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

			ExplicitWait.visibleElement(driver, stocking_plan_status, 10);

			String statusOfStockingPlan = stocking_plan_status.getText().trim();

			if (statusOfStockingPlan.contains("Activated")) {
				currentStatus = true;
				break;
			}
			Thread.sleep(10000);
			i++;
		}

		// checking status after activating Finance
		ExplicitWait.visibleElement(driver, status_finance_activated, 10);
		elementColor = status_finance_activated.getCssValue("background-color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Status for Stocking Plan after Activating Finance - found OK i.e.Finance Activated");
			System.out.println("Status for Stocking Plan after Activating Finance - found OK i.e.Finance Activated");
			statusVerificationCount++;

		} else {
			LO.print("Status for Stocking Plan after Activating Finance - found Wrong");
			System.err.println("Status for Stocking Plan after Activating Finance - found Wrong");

		}

		Click.on(driver, stocking_plan_remove, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

		String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();

		if (classOrMethodName.contains("Reject")) {

			JavaScriptExecutor.click(driver, reject_checkbox);

			Click.sendKeys(driver, rejection_reason_input_field, "Test Reject", 20);

			Click.on(driver, confirm_remove_button, 10);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

			// checking status after completing funding
			ExplicitWait.visibleElement(driver, status_pending_activation, 10);
			ExplicitWait.visibleElement(driver, stocking_plan_status, 10);

			String currentStatusStockingPlan = stocking_plan_status.getText();

			elementColor = status_pending_activation.getCssValue("background-color");

			if (!elementColor.equals("rgba(91, 158, 63, 1)")
					&& currentStatusStockingPlan.equalsIgnoreCase("Rejected")) {

				LO.print("Status for Stocking Plan after Removing Stocking Plan - found OK i.e.Settled");
				System.out.println("Status for Stocking Plan after Removing Stocking Plan - found OK i.e.Settled");
				statusVerificationCount++;

			} else {
				LO.print("Status for Stocking Plan after Removing Stocking Plan - found Wrong");
				System.err.println("Status for Stocking Plan after Removing Stocking Plan - found Wrong");

			}

		} else {
			Click.on(driver, confirm_remove_button, 10);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

			// checking status after completing funding
			ExplicitWait.visibleElement(driver, status_pending_activation, 10);
			ExplicitWait.visibleElement(driver, stocking_plan_status, 10);

			String currentStatusStockingPlan = stocking_plan_status.getText();

			elementColor = status_pending_activation.getCssValue("background-color");

			if (!elementColor.equals("rgba(91, 158, 63, 1)") && currentStatusStockingPlan.equalsIgnoreCase("Settled")) {

				LO.print("Status for Stocking Plan after Removing Stocking Plan - found OK i.e.Settled");
				System.out.println("Status for Stocking Plan after Removing Stocking Plan - found OK i.e.Settled");
				statusVerificationCount++;

			} else {
				LO.print("Status for Stocking Plan after Removing Stocking Plan - found Wrong");
				System.err.println("Status for Stocking Plan after Removing Stocking Plan - found Wrong");

			}
		}
		if (statusVerificationCount == 4) {
			return true;
		} else {
			return false;
		}

	}
	
	public boolean verify_default_status_for_required_flow_for_BCH() throws InterruptedException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		Thread.sleep(2000);
		
		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		LO.print          ("");
		System.out.println("");

		
		LO.print("Started verifying Default Status for BCH required flow");
		System.out.println("Started verifying Default Status for BCH required flow");


		Click.on(driver, status_quote, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	
		

		// Check default status is required

		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_quote_accepted, 10);

		elementColor = status_quote_accepted.getCssValue("color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Default Status for BCH required flow Found OK i.e Quote Accepted because Funder is already added from acquisition quote");
			System.out.println("Default Status for BCH required flow Found OK i.e Quote Accepted because Funder is already added from acquisition quote");
			return true;

		} else {
			LO.print("Default Status for BCH required flow Found Wrong");
			System.err.println("Default Status for BCH required flow Found Wrong");
			return false;
		}
		

	
	}

	
	public boolean verify_status_after_adding_funder_required_flow() throws InterruptedException, IOException {


		
		LO.print          ("");
		System.out.println("");

		
		LO.print("Started verifying Quote Status after adding a funder in asset funding tab");
		System.out.println("Started verifying Quote Status after adding a funder in asset funding tab");


		Click.on(driver, status_quote, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	
		

		// Check default status is required

		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_quote_received, 10);

		elementColor = status_quote_received.getCssValue("color");

		if (elementColor.equals("rgba(199, 92, 0, 1)")) {

			LO.print("Quote Status after adding funder in required flow Found OK i.e Quote Received");
			System.out.println("Quote Status after adding funder in required flow Found OK i.e Quote Received");
			return true;

		} else {
			LO.print("Quote Status after adding funder in required flow-Found Wrong");
			System.err.println("Quote Status after adding funder in required flow-Found Wrong");
			return false;
		}
		

	
	}

	
	public boolean verify_status_after_selecting_funder_required_flow() throws InterruptedException, IOException {


		
		LO.print          ("");
		System.out.println("");
		
		LO.print("Select a funder");
		System.out.println("Select a funder");
	
        Click.on(driver, select_a_funder, 30);
        ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	
		
		LO.print("Started verifying Quote Status after selecting a added funder in asset funding tab");
		System.out.println("Started verifying Quote Status after selecting a added funder in asset funding tab");


		Click.on(driver, status_quote, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	
		

		// Check default status is required

		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_quote_accepted, 10);

		elementColor = status_quote_accepted.getCssValue("color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Status after selecting an added funder Found OK i.e Quote Accepted");
			System.out.println("Status after selecting an added funder Found OK i.e Quote Accepted");
			return true;

		} else {
			LO.print("Status after selecting an added funder Found Wrong");
			System.err.println("Status after selecting an added funder Found Wrong");
			return false;
		}
		

	
	}

	
	public boolean verify_default_status_for_required_flow_if_no_funder_is_added_in_a_quote() throws InterruptedException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		Thread.sleep(2000);
		
		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		LO.print          ("");
		System.out.println("");

		
		LO.print("Started verifying Default Status for HPNR required flow");
		System.out.println("Started verifying Default Status for HPNR required flow");


		Click.on(driver, status_quote, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	
		

		// Check default status is required

		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_quote_requested, 10);

		elementColor = status_quote_requested.getCssValue("color");
		
		System.out.println(elementColor);

		if (elementColor.equals("rgba(199, 92, 0, 1)")) {

			LO.print("Default Status for HPNR required flow Found OK i.e Quote Requested because Funder is not added in the acquisition quote");
			System.out.println("Default Status for HPNR required flow Found OK i.e Quote Requested because Funder is not added in the acquisition quote");
			return true;

		} else {
			LO.print("Default Status for HPNR required flow Found Wrong");
			System.err.println("Default Status for HPNR required flow Found Wrong");
			return false;
		}
		

	
	}

	
	public boolean verify_holding_cost_shown() throws InterruptedException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		Thread.sleep(2000);
		
		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		LO.print          ("");
		System.out.println("");

		
		LO.print("Started verifying Holding Cost Shown");
		System.out.println("Started verifying Holding Cost Shown");


		LO.print("Reading Monthly Holding Cost value from excel has been started");
		System.out.println("Reading Monthly Holding Cost value from excel has been started");
		
		String sheet_name =GetExcelFormulaValue.get_cell_value(1, 2, "Order_ID");		

		double monthly_holding_cost_expected = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);

		LO.print("Reading Monthly Holding Cost value from excel has been completed");
		System.out.println("Reading Monthly Holding Cost value from excel has been completed");

		String total_monthly_holding_cost_from_screen = RemoveComma
				.of(total_monthly_holding_cost.getText().substring(2));

		LO.print("Total_monthly_holding_cost_from_screen =" + total_monthly_holding_cost_from_screen);
		System.out.println("Total_monthly_holding_cost_from_screen " + total_monthly_holding_cost_from_screen);

		LO.print("Total_monthly_holding_cost_from_excel =" + monthly_holding_cost_expected);
		System.out.println("Total_monthly_holding_cost_from_excel " + monthly_holding_cost_expected);

		double total_monthly_holding_cost_actual1 = Double.parseDouble(total_monthly_holding_cost_from_screen);
		double diff = Difference.of_two_Double_Values(total_monthly_holding_cost_actual1,
				monthly_holding_cost_expected);
		boolean flag = false;
		if (diff < 0.2) {
			flag = true;
			LO.print("Total Monthly Holding Cost based on CAP data is Verified and Found OK");
			System.out.println("Total Monthly Holding Cost based on CAP data is Verified and Found OK");

		} else {
			LO.print("Total Monthly Holding Cost based on CAP data is Verified and Found Wrong");
			System.err.println("Total Monthly Holding Cost based on CAP data is Verified and Found Wrong");

		}

		return flag;
		
	}

	
	
	public boolean verify_holding_cost_on_editing_percentage_cap_residual_value(String percentage_cap_residual_value, String residual_value_used, String additional_terms,
			String additional_mileage, String maintenance_status, String sheet_name) throws InterruptedException, IOException, ClassNotFoundException, UnsupportedFlavorException {
		
		LO.print          ("");
		System.out.println("");
		
    	LO.print("Reading Monthly Holding Cost value from excel has been started");
		System.out.println("Reading Monthly Holding Cost value from excel has been started");
		
		Click.on(driver, holding_cost_summary, 20);
		
		obj_holding_cost_page = new HoldingCost_HPNR_HPRPage();
		
		return  obj_holding_cost_page.edit_percentage_residual_verify_holding_cost_without_maintenance(residual_value_used, percentage_cap_residual_value,
						maintenance_status,  sheet_name);				
	}

	
	public boolean verify_holding_cost_on_editing_residual_value(String percentage_cap_residual_value, String residual_value_used, String additional_terms,
			String additional_mileage, String maintenance_status, String sheet_name) throws InterruptedException, IOException, ClassNotFoundException, UnsupportedFlavorException {
		
		LO.print          ("");
		System.out.println("");
		
    			
		obj_holding_cost_page = new HoldingCost_HPNR_HPRPage();
		
		return  obj_holding_cost_page.edit_residual_value_used_then_verify_holding_cost_without_maintenance(residual_value_used, percentage_cap_residual_value,
						maintenance_status,  sheet_name);				
	}

	

	public boolean verify_holding_cost_on_editing_additional_term_and_mileage(String percentage_cap_residual_value, String residual_value_used, String additional_terms,
			String additional_mileage, String maintenance_status, String sheet_name) throws InterruptedException, IOException, ClassNotFoundException, UnsupportedFlavorException {
		
		LO.print          ("");
		System.out.println("");
		
    			
		obj_holding_cost_page = new HoldingCost_HPNR_HPRPage();
		
		return  obj_holding_cost_page.edit_additional_term_and_mileage_then_verify_holding_cost_without_maintenance(additional_terms, additional_mileage,
						maintenance_status,  sheet_name);				
	}

	public void add_funder(String quoteRef, String expiryDate,
			String term, String milesPerAnnum, String cashDeposit, String financeCharges, String documentFee,
			String monthlyPayment, String finalBalloonPayment, String optionToPurchaseFee, String sheet_name) throws InterruptedException, IOException, ClassNotFoundException, UnsupportedFlavorException {
		
		LO.print          ("");
		System.out.println("");
		
    			
		obj_holding_cost_page = new HoldingCost_HPNR_HPRPage();
		
		  obj_holding_cost_page.verify_holding_cost_after_adding_funder_quote_without_maintenance(quoteRef, expiryDate,
				term, milesPerAnnum, cashDeposit,  financeCharges,  documentFee,
				 monthlyPayment,  finalBalloonPayment,  optionToPurchaseFee,  sheet_name);	
		
		
	}

	
	public void verify_holding_cost_on_adding_funder(String quoteRef, String expiryDate,
			String term, String milesPerAnnum, String cashDeposit, String financeCharges, String documentFee,
			String monthlyPayment, String finalBalloonPayment, String optionToPurchaseFee, String sheet_name) throws InterruptedException, IOException, ClassNotFoundException, UnsupportedFlavorException {
		
		LO.print          ("");
		System.out.println("");
		
    			
		obj_holding_cost_page = new HoldingCost_HPNR_HPRPage();
		
		  obj_holding_cost_page.verify_holding_cost_after_adding_funder_quote_without_maintenance_for_asset_funding_tab(sheet_name);	
		
		
	}

	
	
	public boolean verify_default_funder_is_not_deleted() throws InterruptedException, IOException {

		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		LO.print          ("");
		System.out.println("");

		// try to delete funder
		
		LO.print          ("Trying to delete funder");
		System.out.println("Trying to delete funder");
		
		
		ExplicitWait.visibleElement(driver, default_funder_delete_button, 30);
		
		if(!default_funder_delete_button.isEnabled())
		{
			LO.print          ("Default funder can not be deleted as Delete button for selected funder is not enabled");
			System.out.println("Default funder can not be deleted as Delete button for selected funder is not enabled");
			return true;
			
		}
		else
		{
			LO.print          ("Warning : Delete button for selected funder is enabled");
			System.err.println("Warning : Delete button for selected funder is enabled");
			return false;
		}
		
	}
	
	
	public boolean verify_selected_funder_can_not_be_edited() throws InterruptedException, IOException {

		
		LO.print          ("");
		System.out.println("");

		// try to delete funder
		
		LO.print          ("Trying to edit the selected funder");
		System.out.println("Trying to edit the selected funder");
		
		
		ExplicitWait.visibleElement(driver, selected_funder, 30);
		
		Actions act = new Actions(driver);
		
		act.doubleClick(selected_funder).perform();
		
		ExplicitWait.visibleElement(driver, toaster, 20);
		
		String toasterMessage = toaster.getText();
		
		LO.print          ("Double Clicked on selected funder and we got warning message : "+toasterMessage);
		System.out.println("Double Clicked on selected funder and we got warning message : "+toasterMessage);

		
		String expectedToasterMessage = "A selected funder cannot be edited. Please remove funder selection before editing";
		
		if(toasterMessage.equalsIgnoreCase(expectedToasterMessage))
		{
			LO.print          ("Selected funder can not be edited");
			System.out.println("Selected funder can not be edited");
			

			return true;
			
		}
		else
		{
			LO.print          ("Warning : Selected funder enabled for edited");
			System.err.println("Warning : Selected funder enabled for edited");
			return false;
		}
		
	}

	
public boolean verify_document_is_uploaded_in_funder() throws InterruptedException, IOException {

		
		LO.print          ("");
		System.out.println("");

		// try to delete funder
		
		LO.print          ("Trying to upload doc in funder");
		System.out.println("Trying to upload doc in funder");
		
		
		ExplicitWait.visibleElement(driver, selected_funder, 30);
		
		Click.on(driver, selected_button, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
		Actions act = new Actions(driver);
		
		act.doubleClick(selected_funder).perform();
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
		Thread.sleep(5000);
		
			
		funder_doc_upload_button.sendKeys(prop.getProperty("test_doc_path"));
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
		boolean docUploaded=false;
		
        try {
        	ExplicitWait.visibleElement(driver, funder_doc_review_button, 30);
        	docUploaded=true;
        }catch(Exception e)
        {
        	docUploaded=false;     }		
		
		if(docUploaded=true)
		{
			LO.print          ("Document in funder uploaded successfuly");
			System.out.println("Document in funder uploaded successfuly");
			

			return true;
			
		}
		else
		{
			LO.print          ("Warning : Document in funder is not uploaded");
			System.err.println("Warning : Document in funder is not uploaded");
			
			return false;
		}
		
	}



public boolean verify_funders_are_deleted_on_changing_contract_type() throws InterruptedException, IOException {

	
	LO.print          ("");
	System.out.println("");

	// try to delete funder
	
	LO.print          ("Changing Contract type in Contract type tab");
	System.out.println("Changing Contract type in Contract type tab");
	
	
	ExplicitWait.visibleElement(driver, contract_types_and_otr_page, 30);
	
	Click.on(driver, contract_types_and_otr_page, 30);
	
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
	
	ExplicitWait.waitForListOfVisibleElements(driver, contract_types_list, 30);
	
		
	for(WebElement e: contract_types_list)
	{
		ExplicitWait.visibleElement(driver, e, 10);
		
		if(e.isEnabled() && ! e.isSelected( ) && !e.getText().trim().equalsIgnoreCase("Outright Purchase"))
		{
			Thread.sleep(3000);
			
			System.out.println(e.getText());
			
			
			JavaScriptExecutor.click(driver, e);
			
			//e.click();
			
			Thread.sleep(3000);
			
			Click.on(driver, change_contract_confirm_yes, 20);			
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
			
			LO.print          ("Contract Type Changed Successfuly");
			System.out.println("Contract Type Changed Successfuly");
			
			LO.print          ("Changed Contract Type is : "+e.getText());
			System.out.println("Changed Contract Type is : "+e.getText());
			
			break;
		}
	}
	
	
	LO.print          ("Now Moving back to Asset Funding tab to check all funders are deleted or not");
	System.out.println("Now Moving back to Asset Funding tab to check all funders are deleted or not");

	
	Click.on(driver, asset_funding, 10);
	
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);	
	
	boolean funderDeleteButton = false;
    try {
    	ExplicitWait.visibleElement(driver, selected_button, 30);
    	
		LO.print          ("Funder in the Asset Funding Tab is removed after changing contract type");
		System.out.println("Funder in the Asset Funding Tab is removed after changing contract type");
    	 
    }catch(Exception e)
    {
    	funderDeleteButton =  true;
    	
    }
	return funderDeleteButton;		
	

}

	
	public boolean verify_finance_documents_statuses() throws InterruptedException, IOException {

		LO.print          ("");
		System.out.println("");
 
		LO.print          ("Sterted Verifying Finance Document Statuses for required flow");
		System.out.println("Sterted Verifying Finance Document Statuses for required flow");
		
		Click.on(driver, status_finance_documents, 30);	
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	
		
		JavaScriptExecutor.scroll_in_to_view(driver, back_button);
		// Click on  Date agreement Received
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

		elementBackgroundColor = status_finance_documents_requested.getCssValue("background-color");
		
		
		if (elementBackgroundColor.equals("rgba(51, 65, 78, 1)")) {

			LO.print("Finance Document Status after selecting agreement requested date- Found OK i.e Finance docs Requested");
			System.out.println("Finance Document Status after selecting agreement requested date- Found OK i.e Finance docs Requested");
			statusVerificationCount++;

		} else {
			LO.print("Finance Document Status after selecting agreement requested date- Found Wrong");
			System.err.println("Finance Document Status after selecting agreement requested date- Found Wrong");
			 
		}
		
		
		JavaScriptExecutor.scroll_in_to_view(driver, back_button);
		
		
		// Click on  Date agreement Sent
		Click.on(driver, date_agreement_sent, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date 
		CommonClass.move_courser();
		Click.on(driver, pick_a_date, 20);	
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		
		
		
		// Click on  Date agreement received
		Click.on(driver, date_agreement_received, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date 
		CommonClass.move_courser();
		Click.on(driver, pick_a_date, 20);	
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		
		
		ExplicitWait.visibleElement(driver, status_finance_documents_received, 10);

		elementBackgroundColor = status_finance_documents_received.getCssValue("background-color");
		
		
		if (elementBackgroundColor.equals("rgba(51, 65, 78, 1)")) {

			LO.print("Finance Document Status after selecting agreement received date- Found OK i.e Finance Docs Received");
			System.out.println("Finance Document Status after selecting agreement received date- Found OK i.e Finance Docs Received");
			statusVerificationCount++;

		} else {
			LO.print("Finance Document Status after selecting agreement received date- Found Wrong");
			System.err.println("Finance Document Status after selecting agreement received date- Found Wrong");
			 
		}

		JavaScriptExecutor.scroll_in_to_view(driver, back_button);
		//Upload signed Documents
		
		 upload_signed_documents.sendKeys(prop.getProperty("test_image_path"));
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);		 
		 
		 ExplicitWait.visibleElement(driver, status_finance_documents_signed, 100);
		 
			elementBackgroundColor = status_finance_documents_signed.getCssValue("background-color");
			
			
			if (elementBackgroundColor.equals("rgba(51, 65, 78, 1)")) {

				LO.print("Finance Document Status after uploading signed documents - Found OK i.e Finance Docs Signed");
				System.out.println("Finance Document Status after uploading signed documents - Found OK i.e Finance Docs Signed");
				statusVerificationCount++;

			} else {
				LO.print("Finance Document Status after uploading signed documents- Found Wrong");
				System.err.println("Finance Document Status after uploading signed documents- Found Wrong");
				 
			}

			JavaScriptExecutor.scroll_in_to_view(driver, back_button);
			// Click on  uploaded to funder
			Click.on(driver, date_uploaded_to_funder, 20);
			Thread.sleep(8000);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

			// pick date 
			CommonClass.move_courser();
			Click.on(driver, pick_a_date, 20);	
			Thread.sleep(5000);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
			
			
			ExplicitWait.visibleElement(driver, status_upload_to_funder, 10);

			elementBackgroundColor = status_upload_to_funder.getCssValue("background-color");
			
			
			if (elementBackgroundColor.equals("rgba(51, 65, 78, 1)")) {

				LO.print("Finance Document Status after selecting uploaded to funder date- Found OK i.e Upload to Funder");
				System.out.println("Finance Document Status after selecting uploaded to funder date- Found OK i.e Upload to Funder");
				statusVerificationCount++;

			} else {
				LO.print("Finance Document Status after selecting uploaded to funder date- Found Wrong");
				System.err.println("Finance Document Status after selecting uploaded to funder date- Found Wrong");
				 
			}
	
			JavaScriptExecutor.scroll_in_to_view(driver, back_button);
			// Click on  uploaded to funder
			Click.on(driver, date_accepted_by_funder, 20);
			Thread.sleep(8000);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

			// pick date 
			CommonClass.move_courser();
			Click.on(driver, pick_a_date, 20);	
			Thread.sleep(5000);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

			
			
			// Click on  finance activated
			Click.on(driver, date_finance_activated, 20);
			Thread.sleep(8000);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

			// pick date 
			CommonClass.move_courser();
			Click.on(driver, pick_a_date, 20);	
			Thread.sleep(5000);
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

			
			
			ExplicitWait.visibleElement(driver, status_finance_document_finance_activated, 20);

			elementBackgroundColor = status_finance_document_finance_activated.getCssValue("background-color");
			
			
			if (elementBackgroundColor.equals("rgba(51, 65, 78, 1)")) {

				LO.print("Finance Document Status after selecting activation date- Found OK i.e Finance Activated");
				System.out.println("Finance Document Status after selecting activation date- Found OK i.e Finance Activated");
				statusVerificationCount++;

			} else {
				LO.print("Finance Document Status after selecting activation date- Found Wrong");
				System.err.println("Finance Document Status after selecting activation date- Found Wrong");
				 
			}

			
       if(statusVerificationCount==5)
       {
    	   return true;
       }else
       {
    	   return false;
       }
		
	
	}

	
	
	public boolean verify_payments_to_funder_statuses() throws InterruptedException, IOException {

		LO.print          ("");
		System.out.println("");
	

		LO.print          ("Sterted Verifying Payments to Funder Statuses for required flow");
		System.out.println("Sterted Verifying Payments to Funder Statuses for required flow");
		
		JavaScriptExecutor.click(driver, status_payment);		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	
		
		
		int statusVerificationCount = 0;
		
		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_payment_required, 10);

		elementColor = status_payment_required.getCssValue("color");
		
				
		if (elementColor.equals("rgba(199, 92, 0, 1)")) {

			LO.print("Payment to funder -- Default Status - Found OK i.e Payment Required");
			System.out.println("Payment to funder -- Default Status - Found OK i.e Payment Required");
			statusVerificationCount++;

		} else {
			LO.print("Payment to funder -- Default Status- Found Wrong");
			System.err.println("Payment to funder -- Default Status- Found Wrong");
			 
		}
			
		JavaScriptExecutor.scroll_in_to_view(driver, back_button);
		//Click on generate Invoice
		Click.on(driver, button_generate_invoice, 30);	
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	
		
		//Confirm pop up 
		Click.on(driver, button_generate_invoice_cofirm, 30);	
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	

		
		// Click on  Payment Requested
		Click.on(driver, date_payment_requested_for_doc_fee, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date 
		CommonClass.move_courser();
		Click.on(driver, pick_a_date, 20);	
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		
		// Click on  Payment Sent
		Click.on(driver, date_payment_sent_for_doc_fee, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date 
		CommonClass.move_courser();
		Click.on(driver, pick_a_date, 20);	
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		//Click on generate Invoice
		Click.on(driver, button_generate_invoice, 30);	
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	
		
		//Confirm pop up 
		Click.on(driver, button_generate_invoice_cofirm, 30);	
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	

		
		
		// Click on  Payment Requested
		Click.on(driver, date_payment_requested_for_finance_deposit, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date 
		CommonClass.move_courser();
		Click.on(driver, pick_a_date, 20);	
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		//Verify Status Payment requested
		
		ExplicitWait.visibleElement(driver, status_payment_requested, 10);
		
		elementColor = status_payment_requested.getCssValue("color");
		
		
		if (elementColor.equals("rgba(199, 92, 0, 1)")) {

			LO.print("Status for Payment after selecting payment requested date- Found OK i.e Payment Requested");
			System.out.println("Status for Payment after selecting payment requested date- Found OK i.e Payment Requested");
			statusVerificationCount++;

		} else {
			LO.print("Status for Payment after selecting payment requested date- Found Wrong");
			System.err.println("Status for Payment after selecting payment requested date- Found Wrong");
			 
		}			
		
		
		// Click on  Payment Sent
		Click.on(driver, date_payment_sent_for_finance_deposit, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date 
		CommonClass.move_courser();
		Click.on(driver, pick_a_date, 20);	
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		ExplicitWait.visibleElement(driver, status_payment_sent, 10);
		
		elementColor = status_payment_sent.getCssValue("color");
		
		
		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Status for Payment after selecting payment sent date- Found OK i.e Payment Sent");
			System.out.println("Status for Payment after selecting payment sent date- Found OK i.e Payment Sent");
			statusVerificationCount++;

		} else {
			LO.print("Status for Payment after selecting payment sent date- Found Wrong");
			System.err.println("Status for Payment after selecting payment sent date- Found Wrong");
			 
		}			

		
		
			
       if(statusVerificationCount==3)
       {
    	   return true;
       }else
       {
    	   return false;
       }
		
	
	}

	public boolean verify_completed_status() throws InterruptedException, IOException {


		LO.print          ("");
		System.out.println("");
		
		LO.print          ("Click on complete funding");
		System.out.println("Click on complete funding");
		
		Click.on(driver, complete_funding, 10);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	
		
		
		Click.on(driver, confirm_complete, 10);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	
		
		String elementbackgroundColor = "";

		ExplicitWait.visibleElement(driver, status_completed, 10);

		elementbackgroundColor = status_completed.getCssValue("background-color");

		if (elementbackgroundColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Status for Asset Funding after completing funding found OK i.e.Completed");
			System.out.println("Status for Asset Funding after completing funding found OK i.e.Completed");
			return true;

		} else {
			LO.print("Status for Asset Funding after completing funding found wrong");
			System.err.println("Status for Asset Funding after completing funding found wrong");
			return false;
		}	
		
	
	}


	public boolean stocking_plan() throws InterruptedException, IOException {

		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, status_stocking_plan, 30);

		Click.on(driver, status_stocking_plan, 20);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
		obj_asset_funding_page = new AssetFundingPage();
		
		obj_asset_funding_page.verify_stocking_plan_details_before_adding_stocking_plan();
		

		// Check default status is required

		int statusVerificationCount = 0;

		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_Required, 10);

		elementColor = status_Required.getCssValue("background-color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Default Status for Stocking Plan found OK i.e.Required");
			System.out.println("Default Status for Stocking Plan found OK i.e.Required");
			statusVerificationCount++;

		} else {
			LO.print("Default Status for Stocking Plan found wrong");
			System.err.println("Default Status for Stocking Plan found wrong");

		}

		ExplicitWait.waitForListOfVisibleElements(driver, stocking_plan_list, 20);

		int noOfStockingPlan = stocking_plan_list.size();

		boolean currentStatus = false;

		for (int i = 1; i <= noOfStockingPlan; i++) {

			WebElement nameOfTheStockingPlan = driver.findElement(By.xpath(
					"(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i + "]//h5"));

			LO.print("Name of the " + i + " Stocking plan is " + nameOfTheStockingPlan.getText());
			System.out.println("Name of the " + i + " Stocking plan is " + nameOfTheStockingPlan.getText());

			WebElement statusOfTheStockingPlan = driver
					.findElement(By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)["
							+ i + "]//*[contains(text(),'Status')]//ancestor::div[1]//div/div"));

			LO.print("Status of the " + i + " Stocking plan is " + statusOfTheStockingPlan.getText());
			System.out.println("Status of the " + i + " Stocking plan is " + statusOfTheStockingPlan.getText());

			if (statusOfTheStockingPlan.getText().equalsIgnoreCase("Eligible") == true) {

				LO.print("Choosing " + i + " Stocking plan for this order");
				System.out.println("Choosing " + i + " Stocking plan for this order");

				WebElement addButton = driver.findElement(
						By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i
								+ "]//*[contains(text(),'Add')]"));

				addButton.click();

				Thread.sleep(2000);

				confirm_add.click();

				ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

				// check status Pending Activation

				ExplicitWait.visibleElement(driver, status_pending_activation, 10);

				elementColor = status_pending_activation.getCssValue("background-color");

				if (elementColor.equals("rgba(91, 158, 63, 1)")) {

					LO.print("Status for Stocking Plan after adding - found OK i.e.Pending Activation");
					System.out.println("Status for Stocking Plan after adding - found OK i.e.Pending Activation");
					statusVerificationCount++;

				} else {
					LO.print("Status for Stocking Plan after adding - found Wrong");
					System.err.println("Status for Stocking Plan after adding - found Wrong");

				}

				WebElement statusOfTheStockingPlan2 = driver.findElement(
						By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i
								+ "]//*[contains(text(),'Status')]//ancestor::div[1]//div/div"));

				LO.print("Current Status of the " + i + " Stocking plan after adding is "
						+ statusOfTheStockingPlan2.getText());
				System.out.println("Current Status of the " + i + " Stocking plan after adding is "
						+ statusOfTheStockingPlan2.getText());

				break;

			}
		}

		int i = 0;
		while (i < 10) {

			driver.navigate().refresh();
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

			Click.on(driver, asset_funding, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

			ExplicitWait.visibleElement(driver, stocking_plan_status, 10);

			String statusOfStockingPlan = stocking_plan_status.getText().trim();

			if (statusOfStockingPlan.contains("Activated")) {
				currentStatus = true;
				break;
			}
			Thread.sleep(10000);
			i++;
		}

		// checking status after activating Finance
		ExplicitWait.visibleElement(driver, status_finance_activated, 10);
		elementColor = status_finance_activated.getCssValue("background-color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Status for Stocking Plan after Activating Finance - found OK i.e.Finance Activated");
			System.out.println("Status for Stocking Plan after Activating Finance - found OK i.e.Finance Activated");
			statusVerificationCount++;

		} else {
			LO.print("Status for Stocking Plan after Activating Finance - found Wrong");
			System.err.println("Status for Stocking Plan after Activating Finance - found Wrong");

		}

		Click.on(driver, complete_funding, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

		Click.on(driver, confirm_complete, 10);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

		// checking status after completing funding
		ExplicitWait.visibleElement(driver, status_finance_completed, 10);

		elementColor = status_finance_completed.getCssValue("background-color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Status for Stocking Plan after completing funding - found OK i.e.Finance Completed");
			System.out.println("Status for Stocking Plan after completing funding - found OK i.e.Finance Completed");
			statusVerificationCount++;

		} else {
			LO.print("Status for Stocking Plan after completing funding - found Wrong");
			System.err.println("Status for Stocking Plan after completing funding - found Wrong");

		}

		if (statusVerificationCount == 4) {
			return true;
		} else {
			return false;
		}

	}

	public void verify_stocking_plan_details_before_adding_stocking_plan() throws InterruptedException, IOException {

		LO.print("Started Verifying Values Displayed in Stocking Plan Section");
		System.out.println("Started Verifying Values Displayed in Stocking Plan Section");

		int noOfStockingPlan = stocking_plan_list.size();

		for (int i = 1; i <= noOfStockingPlan; i++) {

			WebElement stockingPlan = driver.findElement(By.xpath(
					"(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i + "]//h5"));

			LO.print("Name of the " + i + " Stocking plan is " + stockingPlan.getText());
			System.out.println("Name of the " + i + " Stocking plan is " + stockingPlan.getText());

			WebElement credit_limit = driver
					.findElement(By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)["
							+ i + "]//*[contains(text(),'Credit Limit')]//ancestor::div[1]//div[2]"));

			double creditLimit = Double.parseDouble(RemoveComma.of(credit_limit.getText().trim().substring(2)));

			WebElement credit_utilised = driver
					.findElement(By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)["
							+ i + "]//*[contains(text(),'Credit Utilised')]//ancestor::div[1]//div[2]"));

			double creditUtilised = Double.parseDouble(RemoveComma.of(credit_utilised.getText().trim().substring(2)));

			WebElement amount_to_fund = driver
					.findElement(By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)["
							+ i + "]//*[contains(text(),'Amount to fund')]//ancestor::div[1]//div[2]"));

			double amountToFund = Double.parseDouble(RemoveComma.of(amount_to_fund.getText().trim().substring(2)));

			LO.print("Started Verifying Values Displayed in Stocking Plan Section");
			System.out.println("Started Verifying Values Displayed in Stocking Plan Section");

			LO.print("");
			System.out.println("");
			
			String nameOftheStockingPlan = stockingPlan.getText();
			
			LO.print("Name of the " + i + " Stocking plan is " + nameOftheStockingPlan);
			System.out.println("Name of the " + i + " Stocking plan is " + nameOftheStockingPlan);

			LO.print("");
			System.out.println("");


			LO.print("Credit Limit of the " + i + " Stocking plan from the screen is " + creditLimit);
			System.out.println("Credit Limit of the " + i + " Stocking plan from the screen is " + creditLimit);

			LO.print("");
			System.out.println("");

			LO.print("Credit Utilised for the " + i + " Stocking plan from the screen is " + creditUtilised);
			System.out.println("Credit Utilised for the " + i + " Stocking plan from the screen is " + creditUtilised);

			LO.print("");
			System.out.println("");

			LO.print("Amount to fund shown in the " + i + " Stocking plan from the screen is " + amountToFund);
			System.out.println("Amount to fund shown in the " + i + " Stocking plan from the screen is " + amountToFund);

			// Store Orders Tab Window ID
			String ordersTab = driver.getWindowHandle();

			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"D:\\Orders_Vehicles\\AMT_Orders_Vehicles\\src\\main\\java\\configs\\config.properties");
			prop.load(ip);
			
			String targetURL = prop.getProperty("url")+"stockingplan/setting";

			// Open New Window
			((JavascriptExecutor) driver).executeScript("window.open('" + targetURL + "', '_blank');");

			// Switch to the new tab
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(ordersTab)) {

					// Switch to New Window Opened 
					driver.switchTo().window(handle);
					
					 Thread.sleep(1000);
					Click.on(driver, roles_dropdown, 60);					
					 Thread.sleep(1000);					
					Click.on(driver, super_admin, 60);								
					ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
					
					Click.sendKeys(driver, stocking_plan_settings_search_bar, nameOftheStockingPlan, 60);
					stocking_plan_settings_search_bar.sendKeys(Keys.ENTER);
					ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
			
					Actions act = new Actions(driver);
					Thread.sleep(2000);
					act.doubleClick(search_output).perform();					
					Thread.sleep(5000);
					ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);
					
					
					double stockingPlanCreditLimitFromSetting =  Double.parseDouble(setting_credit_limit.getAttribute("value"));
					
					//this method is incomplete , we are comparing stocking plan values with setting
					
					

					

				}
			}
			// Close Seeting Window
			//driver.close();

			// Switch back to the original tab
			//driver.switchTo().window(ordersTab);

		}

	}

	public boolean check_status_cash_purchase_pending() throws InterruptedException

	{
		Thread.sleep(2000);
		Click.on(driver, asset_funding, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		LO.print("Asset Funding Tab Opened");
		System.out.println("Asset Funding Tab Opened");

		String elementColor = status_cash_purchase_pending.getCssValue("background-color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Default Status for Cash Purchase Verified and found OK i.e.Cash Purchase Pending");
			System.out.println("Default Status for Cash Purchase Verified and found OK i.e.Cash Purchase Pending");
			return true;

		} else {
			LO.print("Default Status for Cash Purchase Verified but found Wrong");
			System.err.println("Default Status for Cash Purchase Verified but found Wrong");
			return false;
		}

	}

	public boolean check_status_cash_purchase_completed() throws InterruptedException

	{

		String elementColor = status_cash_purchase_completed.getCssValue("background-color");
		System.out.println("Element background color: " + elementColor);

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print(
					"Status for Cash Purchase After Completing Cash Purchase Verified and found OK i.e.Cash Purchase Completed");
			System.out.println(
					"Status for Cash Purchase After Completing Cash Purchase Verified and found OK i.e.Cash Purchase Completed");
			return true;

		} else {
			LO.print("Status for Cash Purchase After Completing Cash Purchase Verified but found Wrong");
			System.err.println("Status for Cash Purchase After Completing Cash Purchase Verified but found Wrong");
			return false;
		}

	}

}