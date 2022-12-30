package com.amt.test.OP;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePageOutrightBCHPage;
import com.amt.HoldingCostPages.HoldingCostOutrightBCHPage;
import com.amt.QuoteSummaryPages.QuoteSummaryOutrightBCHPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_Outright_BCH_Ownbook_CalculationPage;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_Outright_BCH_without_maintenance_edited_Test extends TestBase {



	LoginPage obj_Login_Page;
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_Outright_BCH_Ownbook_CalculationPage obj_contract_types_and_OTR_page;
	HoldingCostOutrightBCHPage obj_holding_cost_Outright_BCH_page;
	CustomerQuotePageOutrightBCHPage obj_customer_quote_page;
	QuoteSummaryOutrightBCHPage obj_quote_summary_page;


	@Test(priority = 0, dataProvider = "testData")
	public void aquisition_quotes_outright_BCH_without_maintenance_edited_Test(String manufacturer, String model,
			String vehicleBasicPrice,String roadTaxForFirstYear, String otrPriceForINvoice, String percentage_cap_residual_value_used,
			String residual_value_used , String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String upsell, String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_acq_listing_page = new AcquisitionListingPage();
		obj_vehicle_selection_page = new VehicleSelectionPage();
		obj_options_accessories = new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_Outright_BCH_Ownbook_CalculationPage();
		obj_customer_quote_page = new CustomerQuotePageOutrightBCHPage();
		obj_holding_cost_Outright_BCH_page = new HoldingCostOutrightBCHPage();
		obj_quote_summary_page = new QuoteSummaryOutrightBCHPage();

		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		boolean subtotal_after_discount = obj_contract_types_and_OTR_page
				.contractTypes_and_OTR_selection_outright_bch_vehicle_price_edited(vehicleBasicPrice,sheet_name);
		Assert.assertTrue(subtotal_after_discount);
		boolean otr_price_check = obj_contract_types_and_OTR_page
				.verify_after_discount_calculations_contract_types_page_edited(roadTaxForFirstYear,sheet_name);
		Assert.assertTrue(otr_price_check);
		boolean holding_cost_without_maintenance_boolean = obj_holding_cost_Outright_BCH_page
				.verify_holding_cost_without_maintenance_edited(residual_value_used,sheet_name);
		Assert.assertTrue(holding_cost_without_maintenance_boolean);
		boolean customer_quote_for_payment_boolean = obj_customer_quote_page
				.customer_Quote_outright_BCH_for_one_payment_option_without_maintenance_calculation_edited(
						actual_part_exchange_value_from_excel, given_part_exchange_value_from_excel,
						less_finance_settlement_from_excel, order_deposit_from_excel, document_fee_from_excel, upsell,
						 maintenance_required, maintenance_margin, initial_payment,
						part_exchange_status, target_rental,sheet_name);
		Assert.assertTrue(customer_quote_for_payment_boolean);

//		boolean cutomer_quote_monthly_rental = obj_customer_quote_page
//				.customer_Quote_outright_BCH_for_all_payment_option_without_maintenance_calculation_edited(initial_payment,sheet_name);
//		Assert.assertTrue(cutomer_quote_monthly_rental);
//
//		boolean quote_summary_page_status = obj_quote_summary_page.quote_summary_outright_BCH_without_maintenance(sheet_name);
//		Assert.assertTrue(quote_summary_page_status);

	}
	
	

	@DataProvider(name = "testData")
	public Object[][] getTestData() throws IOException {
		Object[][] data = ReadExcelData.getTestData("OutrightBCHwithoutMaintenanceEd");
		return data;
	}

}
