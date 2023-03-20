package usedCar.FL;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePage_HPNR_BCHPage;
import com.amt.HoldingCostPages.HoldingCost_HPNR_BCHPage;
import com.amt.QuoteSummaryPages.QuoteSummary_HPNR_BCHPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_HPNR_PCH_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_FL_PCH_used_car_without_maintenance_Test extends TestBase {

	LoginPage obj_Login_Page;
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_HPNR_PCH_Page obj_contract_types_and_OTR_page;
	HoldingCost_HPNR_BCHPage obj_holding_cost_page;
	CustomerQuotePage_HPNR_BCHPage obj_customer_quote_page;
	QuoteSummary_HPNR_BCHPage obj_quote_summary_page;

	@Test(priority = 1, dataProvider = "testData")
	public void aquisition_quotes_used_car_OTR_calculation_without_maintenance_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost,
			String percentage_cap_residual_value_used, String residual_value_used,
			String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_acq_listing_page = new AcquisitionListingPage();
		obj_vehicle_selection_page = new VehicleSelectionPage();
		obj_options_accessories = new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_HPNR_PCH_Page();

		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle_for_used_vehicle_flow(registrationNumber, mileage);
		obj_options_accessories.options_And_Accessories_selection_for_used_car();

		boolean cost_price_ex_vat_and_options_and_preparation_cost = obj_contract_types_and_OTR_page
				.contractTypes_selection_and_OTR_calculation(sheet_name);
		Assert.assertTrue(cost_price_ex_vat_and_options_and_preparation_cost);

	}

	@Test(priority = 2, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_used_car_OTR_calculation_without_maintenance_test" })
	public void aquisition_quotes_used_car_edit_cost_price_and_check_OTR_without_maintenance_test(
			String registrationNumber, String mileage, String vehicelCostPrice, String options_and_preparation_cost,
			String percentage_cap_residual_value_used, String residual_value_used,
			String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {

//		boolean cost_price_ex_vat_and_options_and_preparation_cost = obj_contract_types_and_OTR_page
//				.edit_vehicle_cost_price_and_check_OTR_price(vehicelCostPrice, options_and_preparation_cost, sheet_name);
//		Assert.assertTrue(cost_price_ex_vat_and_options_and_preparation_cost);

	}

	@Test(priority = 3, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_used_car_edit_cost_price_and_check_OTR_without_maintenance_test" })

	public void aquisition_quotes_used_car_holding_cost_calculations_without_maintenance_test(
			String registrationNumber, String mileage, String vehicelCostPrice, String options_and_preparation_cost,
			String percentage_cap_residual_value_used, String residual_value_used,
			String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_holding_cost_page = new HoldingCost_HPNR_BCHPage();

		boolean holding_cost_with_maintenance_boolean = obj_holding_cost_page
				.verify_holding_cost_for_used_car_without_maintenance(residual_value_used,
						percentage_cap_residual_value_used, maintenance_required, target_rental, sheet_name);
		Assert.assertTrue(holding_cost_with_maintenance_boolean);

		boolean holding_cost_after_editing_percentage_value = obj_holding_cost_page
				.edit_percentage_residual_verify_holding_cost_without_maintenance(residual_value_used,
						percentage_cap_residual_value_used, maintenance_required, target_rental, sheet_name);
		Assert.assertTrue(holding_cost_after_editing_percentage_value);

		boolean holding_cost_after_editing_residual_value = obj_holding_cost_page
				.edit_residual_value_used_then_verify_holding_cost_without_maintenance(residual_value_used,
						percentage_cap_residual_value_used, maintenance_required, target_rental, sheet_name);
		Assert.assertTrue(holding_cost_after_editing_residual_value);
		
		boolean holding_cost_after_editing_additional_terms_and_mileage = obj_holding_cost_page
				.edit_additional_term_and_mileage_then_verify_holding_cost_without_maintenance(add_terms, add_mileage,
						maintenance_required, target_rental, sheet_name);
		Assert.assertTrue(holding_cost_after_editing_additional_terms_and_mileage);
//
//		

	}

	@Test(priority = 4, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_used_car_holding_cost_calculations_without_maintenance_test" })

	public void aquisition_quotes_customer_quote_payment_profile_calculations_without_maintenance_test(
			String registrationNumber, String mileage, String vehicelCostPrice, String options_and_preparation_cost,
			String percentage_cap_residual_value_used, String residual_value_used,
			String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_customer_quote_page = new CustomerQuotePage_HPNR_BCHPage();

		boolean cust_quote_for_one_payment_boolean_status = obj_customer_quote_page
				.customer_Quote_used_car_for_one_payment_option_without_maintenance_calculation(
						actual_part_exchange_value_from_excel, given_part_exchange_value_from_excel,
						less_finance_settlement_from_excel, order_deposit_from_excel, document_fee_from_excel,
						matrix_upsell, maintenance_required, maintenance_margin, initial_payment, part_exchange_status,
						target_rental, sheet_name);

		Assert.assertTrue(cust_quote_for_one_payment_boolean_status);

		boolean cust_quote_for_upsell_values_boolean_status = obj_customer_quote_page
				.check_monthly_payments_on_adding_upsell_values_without_maintenance(security_deposit, matrix_upsell,
						referrer_upsell, add_terms, add_mileage, sheet_name);

		Assert.assertTrue(cust_quote_for_upsell_values_boolean_status);

		boolean monthlyFinanceAndMaintenanceWithPartExchange = obj_customer_quote_page
				.check_monthly_finance_rental_with_part_exchange_toggle_on_without_maintenance(
						actual_part_exchange_value_from_excel, given_part_exchange_value_from_excel,
						less_finance_settlement_from_excel, order_deposit_from_excel, document_fee_from_excel,
						sheet_name);

		Assert.assertTrue(monthlyFinanceAndMaintenanceWithPartExchange);

		boolean monthly_rental_values_on_updating_upsell_value = obj_customer_quote_page
				.check_monthly_payments_on_updating_customer_quote_summary_upsell_value_without_maintenance(
						matrix_upsell, sheet_name);

		Assert.assertTrue(monthly_rental_values_on_updating_upsell_value);

//		boolean cust_quote_for_all_payment_boolean_status = obj_customer_quote_page
//				.customer_Quote_HPNR_BCH_for_all_payment_option_with_maintenance_calculation(initial_payment,
//						sheet_name);
//		Assert.assertTrue(cust_quote_for_all_payment_boolean_status);

	}

	@Test(priority = 5, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_customer_quote_payment_profile_calculations_without_maintenance_test" })

	public void aquisition_quotes_quote_summary_values_verification_without_maintenance_test(
			String registrationNumber, String mileage, String vehicelCostPrice, String options_and_preparation_cost,
			String percentage_cap_residual_value_used, String residual_value_used,
			String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_quote_summary_page = new QuoteSummary_HPNR_BCHPage();

		boolean quote_summary_OTR_calculation = obj_quote_summary_page
				.quote_summary_OTR_calculation_for_used_car(sheet_name);
		Assert.assertTrue(quote_summary_OTR_calculation);

		boolean quote_summary_holding_cost_calculation = obj_quote_summary_page
				.quote_summary_holding_cost_calculation_without_maintenance(sheet_name);
		// Assert.assertTrue(quote_summary_holding_cost_calculation);

		boolean quote_summary_customer_quote_calculation = obj_quote_summary_page
				.quote_summary_customer_quote_summary_value_verification_without_maintenance(sheet_name);
		// Assert.assertTrue(quote_summary_customer_quote_calculation);

		boolean quote_summary_configuration_value_check = obj_quote_summary_page
				.quote_summary_configuration_value_verification_without_maintenance(sheet_name);
		// Assert.assertTrue(quote_summary_configuration_value_check);

		obj_quote_summary_page.save_quote();

		boolean quote_summary_OTR_calculation1 = obj_quote_summary_page
				.quote_summary_OTR_calculation_for_used_car(sheet_name);
		Assert.assertTrue(quote_summary_OTR_calculation1);

		boolean quote_summary_holding_cost_calculation1 = obj_quote_summary_page
				.quote_summary_holding_cost_calculation_without_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_holding_cost_calculation1);

		boolean quote_summary_customer_quote_calculation1 = obj_quote_summary_page
				.quote_summary_customer_quote_summary_value_verification_without_maintenance(sheet_name); // Assert.assertTrue(quote_summary_customer_quote_calculation);

		boolean quote_summary_configuration_value_check1 = obj_quote_summary_page
				.quote_summary_configuration_value_verification_without_maintenance(sheet_name);
		// Assert.assertTrue(quote_summary_configuration_value_check1);

		boolean value_check_after_Base_Int_change = obj_quote_summary_page
				.quote_summary_edit_base_int_rate_value_verification_without_maintenance(sheet_name);
		Assert.assertTrue(value_check_after_Base_Int_change);

		boolean value_check_after_Finance_margin_change = obj_quote_summary_page
				.quote_summary_edit_finance_margin_value_verification(sheet_name);
		// Assert.assertTrue(value_check_after_Finance_margin_change);

	}

	@DataProvider(name = "testData")
	public Object[][] getTestData() throws IOException {
		Object[][] data = ReadExcelData.getTestData("FL_PCH_withoutMaint_used_car");
		return data;
	}

}
