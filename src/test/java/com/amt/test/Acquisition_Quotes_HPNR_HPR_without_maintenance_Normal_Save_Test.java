package com.amt.test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePage_HPNR_HPRPage;
import com.amt.HoldingCostPages.HoldingCost_HPNR_HPRPage;
import com.amt.QuoteSummaryPages.QuoteSummary_HPNR_HPRPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_HPNR_HPR_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_HPNR_HPR_without_maintenance_Normal_Save_Test extends TestBase {



	LoginPage obj_Login_Page;
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_HPNR_HPR_Page obj_contract_types_and_OTR_page ;
	HoldingCost_HPNR_HPRPage obj_holding_cost;
	CustomerQuotePage_HPNR_HPRPage obj_customer_quote_page;
	QuoteSummary_HPNR_HPRPage obj_quote_summary_page;


	@Test(priority = 1, dataProvider = "testData")
	public void aquisition_quotes_HPNR_HPR_OTR_calculation_without_maintenance_test(String manufacturer, String model
			, String Vehicle_Basic_price, String  road_tax_for_first_year, String percentage_cap_residual_value, String residual_value_used, String additional_terms, String additional_mileage,
			String vehicle_profit ,String  maintenance_status, String matrix_credit_type, String security_deposit, String balloon_payment_status, String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			String  order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_acq_listing_page = new AcquisitionListingPage();
		obj_vehicle_selection_page = new VehicleSelectionPage();
		obj_options_accessories = new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_HPNR_HPR_Page();
		

		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		boolean subtotal_after_discount = obj_contract_types_and_OTR_page
				.contractTypes_and_OTR_selection_HPNR_HPR_Ownbook_calculation(sheet_name);
		Assert.assertTrue(subtotal_after_discount);

	}
	
	@Test(priority=2, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_HPNR_HPR_OTR_calculation_without_maintenance_test" })

	public void aquisition_quotes_HPNR_HPR_after_discount_calculations_without_maintenance_test(String manufacturer, String model
			, String Vehicle_Basic_price, String  road_tax_for_first_year, String percentage_cap_residual_value, String residual_value_used, String additional_terms, String additional_mileage,
			String vehicle_profit ,String  maintenance_status, String matrix_credit_type, String security_deposit, String balloon_payment_status, String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			String  order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_HPNR_HPR_Page();

		boolean otr_price_check = obj_contract_types_and_OTR_page
				.verify_after_discount_calculations_contract_types_page(sheet_name);
		Assert.assertTrue(otr_price_check);

	}
	
	@Test(priority=3, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_HPNR_HPR_after_discount_calculations_without_maintenance_test" })

	public void aquisition_quotes_HPNR_HPR_holding_cost_calculations_without_maintenance_test(String manufacturer, String model
			, String Vehicle_Basic_price, String  road_tax_for_first_year, String percentage_cap_residual_value, String residual_value_used, String additional_terms, String additional_mileage,
			String vehicle_profit ,String  maintenance_status, String matrix_credit_type, String security_deposit, String balloon_payment_status, String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			String  order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_holding_cost = new HoldingCost_HPNR_HPRPage();
	
		boolean holding_cost_without_maintenance_boolean = obj_holding_cost
				.verify_holding_cost_without_maintenance(sheet_name);
		Assert.assertTrue(holding_cost_without_maintenance_boolean);

	}
	
	@Test(priority=4, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_HPNR_HPR_holding_cost_calculations_without_maintenance_test" })

	public void aquisition_quotes_HPNR_HPR_customer_quote_calculations_check_monthly_finance_payment_without_maintenance_test(String manufacturer, String model
			, String Vehicle_Basic_price, String  road_tax_for_first_year, String percentage_cap_residual_value, String residual_value_used, String additional_terms, String additional_mileage,
			String vehicle_profit ,String  maintenance_status, String matrix_credit_type, String security_deposit, String balloon_payment_status, String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			String  order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {


		obj_customer_quote_page = new CustomerQuotePage_HPNR_HPRPage();
		
		boolean monthly_finance_payment_check =obj_customer_quote_page.check_monthly_finance_payment_on_customer_quote(driver,maintenance_status, matrix_credit_type, balloon_payment_status,order_deposit, finance_deposit,document_fee, sheet_name);
		Assert.assertTrue(monthly_finance_payment_check);
	}

	
	
	@Test(priority=5, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_HPNR_HPR_customer_quote_calculations_check_monthly_finance_payment_without_maintenance_test" })

	public void aquisition_quotes_HPNR_HPR_quote_summary_values_verification_without_maintenance_test(String manufacturer, String model
			, String Vehicle_Basic_price, String  road_tax_for_first_year, String percentage_cap_residual_value, String residual_value_used, String additional_terms, String additional_mileage,
			String vehicle_profit ,String  maintenance_status, String matrix_credit_type, String security_deposit, String balloon_payment_status, String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			String  order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {


		obj_quote_summary_page = new QuoteSummary_HPNR_HPRPage();
		
		boolean quote_summary_value_check =obj_quote_summary_page.quote_summary_HPNR_HPR_without_maintenance(sheet_name);		
        
		Assert.assertTrue(quote_summary_value_check);
       
	}	

	@DataProvider(name = "testData")
	public Object[][] getTestData() throws IOException {
		Object[][] data = ReadExcelData.getTestData("HPNR_HPR_withoutMaintenance");
		return data;
	}

}
