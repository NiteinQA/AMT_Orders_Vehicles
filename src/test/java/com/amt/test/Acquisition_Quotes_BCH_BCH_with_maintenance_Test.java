package com.amt.test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePage_BCH_BCH_Page;
import com.amt.HoldingCostPages.HoldingCost_BCH_BCH_Page;
import com.amt.QuoteSummaryPages.QuoteSummary_BCH_BCH_Page;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_BCH_BCH_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_BCH_BCH_with_maintenance_Test extends TestBase {



	LoginPage obj_Login_Page;
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_BCH_BCH_Page obj_contract_types_and_OTR_page;
	HoldingCost_BCH_BCH_Page obj_holding_cost_BCH_BCH_page;
	CustomerQuotePage_BCH_BCH_Page obj_customer_quote_page;
	QuoteSummary_BCH_BCH_Page obj_quote_summary_page;


	@Test(priority = 1, dataProvider = "testData")
	public void aquisition_quotes_BCH_BCH_OTR_calculation_with_maintenance_test(String manufacturer, String model,
			String quoteReference, String quoteExpiryDate,String terms,String milesPerAnnum, String monthlyFinanceRental,String monthlyMaintenanceRental, String documentFee, 
			String penceperExcessMileFinance, String penceperExcessMileMaintenance, String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String upsell, String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_acq_listing_page = new AcquisitionListingPage();
		obj_vehicle_selection_page = new VehicleSelectionPage();
		obj_options_accessories = new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_BCH_BCH_Page();
		

		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		boolean subtotal_after_discount = obj_contract_types_and_OTR_page
				.contractTypes_and_OTR_selection_BCH_BCH_Ownbook_calculation(sheet_name);
		Assert.assertTrue(subtotal_after_discount);

	}
	
	@Test(priority=2, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_BCH_BCH_OTR_calculation_with_maintenance_test" })

	public void aquisition_quotes_BCH_BCH_after_discount_calculations_with_maintenance_test(String manufacturer, String model,
			String quoteReference, String quoteExpiryDate,String terms,String milesPerAnnum, String monthlyFinanceRental,String monthlyMaintenanceRental, String documentFee, 
			String penceperExcessMileFinance, String penceperExcessMileMaintenance, String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String upsell, String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_BCH_BCH_Page();

		boolean otr_price_check = obj_contract_types_and_OTR_page
				.verify_after_discount_calculations_contract_types_page(sheet_name);
		Assert.assertTrue(otr_price_check);
		
		
		


	}
	
	@Test(priority=3, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_BCH_BCH_after_discount_calculations_with_maintenance_test" })

	public void aquisition_quotes_BCH_BCH_holding_cost_calculations_with_maintenance_test(String manufacturer, String model,
			String quoteReference, String quoteExpiryDate,String terms,String milesPerAnnum, String monthlyFinanceRental,String monthlyMaintenanceRental, String documentFee, 
			String penceperExcessMileFinance, String penceperExcessMileMaintenance, String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String upsell, String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_holding_cost_BCH_BCH_page = new HoldingCost_BCH_BCH_Page();
	
		boolean holding_cost_with_maintenance_boolean = obj_holding_cost_BCH_BCH_page
				.verify_holding_cost_with_maintenance( quoteReference,  quoteExpiryDate, terms, milesPerAnnum,maintenance_required,  monthlyFinanceRental,monthlyMaintenanceRental,  documentFee,
						penceperExcessMileFinance, penceperExcessMileMaintenance,  sheet_name);
		Assert.assertTrue(holding_cost_with_maintenance_boolean);	

	}
	
	@Test(priority=4, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_BCH_BCH_holding_cost_calculations_with_maintenance_test" })

	public void aquisition_quotes_BCH_BCH_customer_quote_payment_profile_calculations_with_maintenance_test(String manufacturer, String model,
			String quoteReference, String quoteExpiryDate,String terms,String milesPerAnnum, String monthlyFinanceRental,String monthlyMaintenanceRental, String documentFee, 
			String penceperExcessMileFinance, String penceperExcessMileMaintenance, String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String upsell, String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {


		obj_customer_quote_page = new CustomerQuotePage_BCH_BCH_Page();

		boolean customer_quote_for_payment_boolean = obj_customer_quote_page
				.customer_Quote_BCH_BCH_for_one_payment_option_with_maintenance_calculation(
						actual_part_exchange_value_from_excel, given_part_exchange_value_from_excel,
						less_finance_settlement_from_excel, order_deposit_from_excel, document_fee_from_excel, upsell,
						 maintenance_required, maintenance_margin, initial_payment,
						part_exchange_status, target_rental,sheet_name);
		Assert.assertTrue(customer_quote_for_payment_boolean);
		
		boolean balance_due_value = obj_customer_quote_page.customer_quote_part_balance_due_value_verification(actual_part_exchange_value_from_excel, given_part_exchange_value_from_excel, less_finance_settlement_from_excel,
				order_deposit_from_excel, document_fee_from_excel, upsell, part_exchange_status, target_rental, sheet_name);
		
		Assert.assertTrue(balance_due_value);
		
		boolean monthly_finance_rental =obj_customer_quote_page.customer_quote_monthly_finance_rental_value_verification_when_part_exchange_toggle_on(actual_part_exchange_value_from_excel, given_part_exchange_value_from_excel, less_finance_settlement_from_excel, order_deposit_from_excel,
				document_fee_from_excel, upsell, part_exchange_status, target_rental, sheet_name);
		
		Assert.assertTrue(monthly_finance_rental);
		
		

		boolean cutomer_quote_monthly_rental = obj_customer_quote_page
				.customer_Quote_BCH_BCH_for_all_payment_option_with_maintenance_calculation(initial_payment,sheet_name);
		Assert.assertTrue(cutomer_quote_monthly_rental);
	
	}
	
	
	
	@Test(priority=5, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_BCH_BCH_customer_quote_payment_profile_calculations_with_maintenance_test" })

	public void aquisition_quotes_BCH_BCH_quote_summary_values_verification_with_maintenance_test(String manufacturer, String model,
			String quoteReference, String quoteExpiryDate,String terms,String milesPerAnnum, String monthlyFinanceRental,String monthlyMaintenanceRental, String documentFee, 
			String penceperExcessMileFinance, String penceperExcessMileMaintenance, String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String upsell, String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_quote_summary_page = new QuoteSummary_BCH_BCH_Page();


		boolean quote_summary_page_status = obj_quote_summary_page.quote_summary_BCH_BCH_with_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_page_status);

	}
	
	

	@DataProvider(name = "testData")
	public Object[][] getTestData() throws IOException {
		Object[][] data = ReadExcelData.getTestData("BCH_BCH_withMaintenance");
		return data;
	}

}
