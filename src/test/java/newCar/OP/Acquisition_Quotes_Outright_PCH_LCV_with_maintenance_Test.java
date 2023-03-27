package newCar.OP;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePageOutrightPCHPage;
import com.amt.HoldingCostPages.HoldingCostOutrightPCHPage;
import com.amt.QuoteSummaryPages.QuoteSummaryOutrightPCHPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_HPNR_PCH_Page;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_Outright_PCH_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_Outright_PCH_LCV_with_maintenance_Test extends TestBase {

	LoginPage obj_Login_Page;
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_Outright_PCH_Page obj_contract_types_and_OTR_page;
	HoldingCostOutrightPCHPage obj_holding_cost_page;
	CustomerQuotePageOutrightPCHPage obj_customer_quote_page;
	QuoteSummaryOutrightPCHPage obj_quote_summary_page;

	@Test(priority = 1, dataProvider = "testData")
	public void aquisition_quotes_outright_PCH_OTR_calculation_with_maintenance_test(String manufacturer, String model,
			String road_tax_for_first_year, String on_road_price_for_invoice, String other_support_value,
			String percentage_cap_residual_value_used, String percentage_cap_maintenance_cost_used,
			String residual_value_used, String main_cost_used, String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String document_fee_from_excel, String security_deposit,
			String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_acq_listing_page = new AcquisitionListingPage();
		obj_vehicle_selection_page = new VehicleSelectionPage();
		obj_options_accessories = new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_Outright_PCH_Page();

		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_LCV_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();

		System.out.println("");
		System.out.println("");

		boolean subtotal_after_discount = obj_contract_types_and_OTR_page
				.contractTypes_and_OTR_selection_outright_PCH_Ownbook_calculation(sheet_name);
		Assert.assertTrue(subtotal_after_discount);

		System.out.println("");
		System.out.println("");
	}

	@Test(priority = 2, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_outright_PCH_OTR_calculation_with_maintenance_test" })

	public void aquisition_quotes_outright_PCH_after_discount_calculations_with_maintenance_test(String manufacturer,
			String model, String road_tax_for_first_year, String on_road_price_for_invoice, String other_support_value,
			String percentage_cap_residual_value_used, String percentage_cap_maintenance_cost_used,
			String residual_value_used, String main_cost_used, String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String document_fee_from_excel, String security_deposit,
			String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_Outright_PCH_Page();

		System.out.println("");
		System.out.println("");

		boolean otr_price_check = obj_contract_types_and_OTR_page
				.verify_after_discount_calculations_contract_types_page(sheet_name);
		Assert.assertTrue(otr_price_check);

		System.out.println("");
		System.out.println("");
	}

	@Test(priority = 3, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_outright_PCH_after_discount_calculations_with_maintenance_test" })

	public void aquisition_quotes_outright_PCH_other_support_check_with_maintenance_test(String manufacturer,
			String model, String road_tax_for_first_year, String on_road_price_for_invoice, String other_support_value,
			String percentage_cap_residual_value_used, String percentage_cap_maintenance_cost_used,
			String residual_value_used, String main_cost_used, String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String document_fee_from_excel, String security_deposit,
			String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_Outright_PCH_Page();

		System.out.println("");
		System.out.println("");

		boolean otr_price_check = obj_contract_types_and_OTR_page.verify_other_support_calculations(other_support_value,
				sheet_name);

		Assert.assertTrue(otr_price_check);

		System.out.println("");
		System.out.println("");

	}

	@Test(priority = 4, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_outright_PCH_other_support_check_with_maintenance_test" })

	public void aquisition_quotes_outright_PCH_holding_cost_calculations_with_maintenance_test(String manufacturer,
			String model, String road_tax_for_first_year, String on_road_price_for_invoice, String other_support_value,
			String percentage_cap_residual_value_used, String percentage_cap_maintenance_cost_used,
			String residual_value_used, String main_cost_used, String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String document_fee_from_excel, String security_deposit,
			String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException, ClassNotFoundException {

		obj_holding_cost_page = new HoldingCostOutrightPCHPage();

		System.out.println("");
		System.out.println("");
		boolean holding_cost_before_editing_percentage_values = obj_holding_cost_page
				.verify_holding_cost_before_editing_cap_data_with_maintenance(percentage_cap_maintenance_cost_used,
						residual_value_used, main_cost_used, percentage_cap_residual_value_used, maintenance_required,
						target_rental, sheet_name);
		Assert.assertTrue(holding_cost_before_editing_percentage_values);

		boolean holding_cost_after_editing_percentage_values = obj_holding_cost_page
				.edit_percentage_residual_and_maint_cost_then_verify_holding_cost_with_maintenance(
						percentage_cap_maintenance_cost_used, residual_value_used, main_cost_used,
						percentage_cap_residual_value_used, maintenance_required, target_rental, sheet_name);
		Assert.assertTrue(holding_cost_after_editing_percentage_values);

		boolean holding_cost_after_editing_residual_and_maint_cost = obj_holding_cost_page
				.edit_residual_value_and_maint_cost_then_verify_holding_cost_with_maintenance(
						percentage_cap_maintenance_cost_used, residual_value_used, main_cost_used,
						percentage_cap_residual_value_used, maintenance_required, target_rental, sheet_name);
		Assert.assertTrue(holding_cost_after_editing_residual_and_maint_cost);
		
		boolean holding_cost_after_editing_additional_terms_and_mileage = obj_holding_cost_page
				.edit_additional_term_and_mileage_then_verify_holding_cost_with_maintenance(add_terms, add_mileage,
						maintenance_required, target_rental, sheet_name);
		Assert.assertTrue(holding_cost_after_editing_additional_terms_and_mileage);


		System.out.println("");
		System.out.println("");
	}

	@Test(priority = 5, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_outright_PCH_holding_cost_calculations_with_maintenance_test" })

	public void aquisition_quotes_outright_PCH_customer_quote_payment_profile_calculations_with_maintenance_test(
			String manufacturer, String model, String road_tax_for_first_year, String on_road_price_for_invoice,
			String other_support_value, String percentage_cap_residual_value_used,
			String percentage_cap_maintenance_cost_used, String residual_value_used, String main_cost_used,
			String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException, NumberFormatException, ClassNotFoundException {

		obj_customer_quote_page = new CustomerQuotePageOutrightPCHPage();

		System.out.println("");
		System.out.println("");

		boolean cust_quote_for_one_payment_boolean_status = obj_customer_quote_page
				.customer_Quote_outright_PCH_for_one_payment_option_with_maintenance_calculation(
						actual_part_exchange_value_from_excel, given_part_exchange_value_from_excel,
						less_finance_settlement_from_excel, order_deposit_from_excel, document_fee_from_excel,
						matrix_upsell, maintenance_required, maintenance_margin, initial_payment, part_exchange_status,
						target_rental, sheet_name);

		Assert.assertTrue(cust_quote_for_one_payment_boolean_status);

		System.out.println("");
		System.out.println("");

		boolean cust_quote_for_upsell_values_boolean_status = obj_customer_quote_page
				.check_monthly_payments_on_adding_upsell_values_with_maintenance(security_deposit, matrix_upsell,
						referrer_upsell, add_terms, add_mileage, sheet_name);

		Assert.assertTrue(cust_quote_for_upsell_values_boolean_status);

		System.out.println("");
		System.out.println("");

		boolean finance_rental_with_part_exchange = obj_customer_quote_page
				.check_monthly_finance_rental_with_part_exchange_toggle_on_with_maintenance(
						actual_part_exchange_value_from_excel, given_part_exchange_value_from_excel,
						less_finance_settlement_from_excel, order_deposit_from_excel, document_fee_from_excel,
						sheet_name);

		Assert.assertTrue(finance_rental_with_part_exchange);

		System.out.println("");
		System.out.println("");
		
		boolean monthly_rental_values_on_updating_upsell_value =obj_customer_quote_page.check_monthly_payments_on_updating_customer_quote_summary_upsell_value_with_maintenance(matrix_upsell, sheet_name);

		Assert.assertTrue(monthly_rental_values_on_updating_upsell_value);

		System.out.println("");
		System.out.println("");

		boolean cust_quote_for_all_payment_boolean_status = obj_customer_quote_page
				.customer_Quote_outright_PCH_for_all_payment_option_with_maintenance_calculation(initial_payment,
						sheet_name);
		Assert.assertTrue(cust_quote_for_all_payment_boolean_status);

		System.out.println("");
		System.out.println("");
	}

	@Test(priority = 6, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_outright_PCH_customer_quote_payment_profile_calculations_with_maintenance_test" })

	public void aquisition_quotes_outright_PCH_quote_summary_values_verification_with_maintenance_test(
			String manufacturer, String model, String road_tax_for_first_year, String on_road_price_for_invoice,
			String other_support_value, String percentage_cap_residual_value_used,
			String percentage_cap_maintenance_cost_used, String residual_value_used, String main_cost_used,
			String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage,
			String maintenance_required, String maintenance_margin, String initial_payment, String part_exchange_status,
			String target_rental, String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_quote_summary_page = new QuoteSummaryOutrightPCHPage();

		System.out.println("");
		System.out.println("");
		boolean quote_summary_OTR_calculation = obj_quote_summary_page.quote_summary_OTR_calculation(sheet_name);
		Assert.assertTrue(quote_summary_OTR_calculation);

		System.out.println("");
		System.out.println("");

		boolean quote_summary_holding_cost_calculation = obj_quote_summary_page
				.quote_summary_holding_cost_calculation_with_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_holding_cost_calculation);

		System.out.println("");
		System.out.println("");

		boolean quote_summary_customer_quote_calculation = obj_quote_summary_page
				.quote_summary_customer_quote_summary_value_verification_with_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_customer_quote_calculation);

		System.out.println("");
		System.out.println("");

		boolean quote_summary_configuration_value_check = obj_quote_summary_page
				.quote_summary_configuration_value_verification_with_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_configuration_value_check);

		System.out.println("");
		System.out.println("");

		obj_quote_summary_page.save_quote();

		boolean quote_summary_OTR_calculation1 = obj_quote_summary_page.quote_summary_OTR_calculation(sheet_name);
		Assert.assertTrue(quote_summary_OTR_calculation1);

		System.out.println("");
		System.out.println("");

		boolean quote_summary_holding_cost_calculation1 = obj_quote_summary_page
				.quote_summary_holding_cost_calculation_with_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_holding_cost_calculation1);

		System.out.println("");
		System.out.println("");

		boolean quote_summary_customer_quote_calculation1 = obj_quote_summary_page
				.quote_summary_customer_quote_summary_value_verification_with_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_customer_quote_calculation);

		System.out.println("");
		System.out.println("");

		boolean quote_summary_configuration_value_check1 = obj_quote_summary_page
				.quote_summary_configuration_value_verification_with_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_configuration_value_check1);

		System.out.println("");
		System.out.println("");

		boolean value_check_after_Base_Int_change = obj_quote_summary_page
				.quote_summary_edit_base_int_rate_value_verification_with_maintenance(sheet_name);
		Assert.assertTrue(value_check_after_Base_Int_change);

		System.out.println("");
		System.out.println("");

		boolean value_check_after_Finance_margin_change = obj_quote_summary_page
				.quote_summary_edit_finance_margin_value_verification(sheet_name);
		Assert.assertTrue(value_check_after_Finance_margin_change);

		System.out.println("");
		System.out.println("");

		boolean value_check_after_maint_margin_change = obj_quote_summary_page
				.quote_summary_edit_maintenance_margin_value_verification(sheet_name);
		Assert.assertTrue(value_check_after_maint_margin_change);

		System.out.println("");
		System.out.println("");

	}

	@DataProvider(name = "testData")
	public Object[][] getTestData() throws IOException {
		Object[][] data = ReadExcelData.getTestData("OutrightPCHwithMaint_LCV");
		return data;
	}

}
