package newCar.HPNR;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePage_HPNR_HPRPage;
import com.amt.HoldingCostPages.HoldingCost_HPNR_CP_Page;
import com.amt.QuoteSummaryPages.QuoteSummary_HPNR_CP_Page;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_HPNR_CP_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_HPNR_CP_with_maintenance_Test extends TestBase {



	LoginPage obj_Login_Page;
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_HPNR_CP_Page obj_contract_types_and_OTR_page;
	HoldingCost_HPNR_CP_Page obj_holding_cost_page;
	CustomerQuotePage_HPNR_HPRPage obj_customer_quote_page;
	QuoteSummary_HPNR_CP_Page obj_quote_summary_page;


	@Test(priority = 1, dataProvider = "testData")
	public void aquisition_quotes_HPNR_CP_OTR_calculation_with_maintenance_test(String manufacturer, String model, 
			String Vehicle_Basic_price, String  road_tax_for_first_year, String percentage_cap_residual_value, String percentage_cap_maint_value,
			String residual_value_used, String maint_cost_used, String target_rental, String additional_terms, String additional_mileage,
			String vehicle_profit, String sales_price_percentage,String  maintenance_status,String matrix_credit_type, String security_deposit, 
			String balloon_payment_status, String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			String  order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_acq_listing_page = new AcquisitionListingPage();
		obj_vehicle_selection_page = new VehicleSelectionPage();
		obj_options_accessories = new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_HPNR_CP_Page();
		

		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		boolean subtotal_after_discount = obj_contract_types_and_OTR_page
				.contractTypes_and_OTR_selection_HPNR_CP_Ownbook_calculation(sheet_name);
		Assert.assertTrue(subtotal_after_discount);

	}
	
	@Test(priority=2, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_HPNR_CP_OTR_calculation_with_maintenance_test" })

	public void aquisition_quotes_HPNR_CP_after_discount_calculations_with_maintenance_test(String manufacturer, String model, 
			String Vehicle_Basic_price, String  road_tax_for_first_year, String percentage_cap_residual_value, String percentage_cap_maint_value,
			String residual_value_used, String maint_cost_used, String target_rental, String additional_terms, String additional_mileage,
			String vehicle_profit, String sales_price_percentage,String  maintenance_status,String matrix_credit_type, String security_deposit, 
			String balloon_payment_status, String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			String  order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_HPNR_CP_Page();

		boolean otr_price_check = obj_contract_types_and_OTR_page
				.verify_after_discount_calculations_contract_types_page(sheet_name);
		Assert.assertTrue(otr_price_check);


	}
	
	@Test(priority=3, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_HPNR_CP_after_discount_calculations_with_maintenance_test" })

	public void aquisition_quotes_HPNR_CP_holding_cost_calculations_with_maintenance_test(String manufacturer, String model, 
			String Vehicle_Basic_price, String  road_tax_for_first_year, String percentage_cap_residual_value, String percentage_cap_maint_value,
			String residual_value_used, String maint_cost_used, String target_rental, String additional_terms, String additional_mileage,
			String vehicle_profit, String sales_price_percentage,String  maintenance_status,String matrix_credit_type, String security_deposit, 
			String balloon_payment_status, String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			String  order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException, ClassNotFoundException {

		obj_holding_cost_page = new HoldingCost_HPNR_CP_Page();
	
		boolean holding_cost_before_editing_percentage_values = obj_holding_cost_page.verify_holding_cost_before_editing_cap_data_with_maintenance(
				percentage_cap_maint_value, residual_value_used, maint_cost_used,
				percentage_cap_residual_value, maintenance_status, target_rental, sheet_name);
		Assert.assertTrue(holding_cost_before_editing_percentage_values);
		
		
		boolean holding_cost_after_editing_percentage_values  = obj_holding_cost_page.edit_percentage_residual_and_maint_cost_then_verify_holding_cost_with_maintenance(
				percentage_cap_maint_value, residual_value_used, maint_cost_used,
				percentage_cap_residual_value, maintenance_status, target_rental, sheet_name);
		Assert.assertTrue(holding_cost_after_editing_percentage_values );
		
		boolean holding_cost_after_editing_residual_and_maint_cost  = obj_holding_cost_page.edit_residual_value_and_maint_cost_then_verify_holding_cost_with_maintenance(
				percentage_cap_maint_value, residual_value_used, maint_cost_used,
				percentage_cap_residual_value, maintenance_status, target_rental, sheet_name);
		Assert.assertTrue(holding_cost_after_editing_residual_and_maint_cost );
	
		boolean holding_cost_after_editing_additional_terms_and_mileage = obj_holding_cost_page
				.edit_additional_term_and_mileage_then_verify_holding_cost_with_maintenance(additional_terms, additional_mileage,
						maintenance_status, target_rental, sheet_name);
		Assert.assertTrue(holding_cost_after_editing_additional_terms_and_mileage);

		

	}
	
	@Test(priority=4, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_HPNR_CP_holding_cost_calculations_with_maintenance_test" })

	public void aquisition_quotes_HPNR_CP_customer_quote_calculations_check_monthly_finance_payment_with_maintenance_test(String manufacturer, String model, 
			String Vehicle_Basic_price, String  road_tax_for_first_year, String percentage_cap_residual_value, String percentage_cap_maint_value,
			String residual_value_used, String maint_cost_used, String target_rental, String additional_terms, String additional_mileage,
			String vehicle_profit, String sales_price_percentage,String  maintenance_status,String matrix_credit_type, String security_deposit, 
			String balloon_payment_status, String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			String  order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException, NumberFormatException, ClassNotFoundException {


		obj_customer_quote_page = new CustomerQuotePage_HPNR_HPRPage();
		
		boolean monthly_total_payment_check =obj_customer_quote_page.check_monthly_payment_on_customer_quote_with_maintenance(driver,maintenance_status, matrix_credit_type, balloon_payment_status,order_deposit, finance_deposit,document_fee, sheet_name);
		Assert.assertTrue(monthly_total_payment_check);
		
		boolean sales_discount_price_check =obj_customer_quote_page.enter_sales_price_discount_greater_than_cost_price_discount(driver , sheet_name);
		Assert.assertTrue(sales_discount_price_check);
		
		boolean sales_discount_price_update_check =obj_customer_quote_page.update_sales_price_discount_and_verify_sales_price_vehicle_profit_monthly_rental_with_maintenance(driver, sheet_name);
		Assert.assertTrue(sales_discount_price_update_check);	
		

		
	}
	
	@Test(priority=5, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_HPNR_CP_customer_quote_calculations_check_monthly_finance_payment_with_maintenance_test" })

	public void aquisition_quotes_HPNR_CP_customer_quote_vehicle_profit_edit_check_with_maintenance_test(String manufacturer, String model, 
			String Vehicle_Basic_price, String  road_tax_for_first_year, String percentage_cap_residual_value, String percentage_cap_maint_value,
			String residual_value_used, String maint_cost_used, String target_rental, String additional_terms, String additional_mileage,
			String vehicle_profit, String sales_price_percentage,String  maintenance_status,String matrix_credit_type, String security_deposit, 
			String balloon_payment_status, String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			String  order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {


		obj_customer_quote_page = new CustomerQuotePage_HPNR_HPRPage();
		
	
		boolean monthly_total_payment_after_editing_vehicle_profit =obj_customer_quote_page.edit_vehicle_profit_and_check_monthly_total_payment_with_maintenance(vehicle_profit, sheet_name);
		Assert.assertTrue(monthly_total_payment_after_editing_vehicle_profit);
		
		boolean monthly_total_payment_after_editing_vehicle_sales_price = obj_customer_quote_page.edit_otr_sales_price_and_check_monthly_total_payment_with_maintenance( sales_price_percentage, sheet_name);
		Assert.assertTrue(monthly_total_payment_after_editing_vehicle_sales_price);

		
	}
	
	@Test(priority=6, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_HPNR_CP_customer_quote_vehicle_profit_edit_check_with_maintenance_test" })

	public void aquisition_quotes_HPNR_CP_customer_quote_part_exchange_value_edit_check_with_maintenance_test(String manufacturer, String model, 
			String Vehicle_Basic_price, String  road_tax_for_first_year, String percentage_cap_residual_value, String percentage_cap_maint_value,
			String residual_value_used, String maint_cost_used, String target_rental, String additional_terms, String additional_mileage,
			String vehicle_profit, String sales_price_percentage,String  maintenance_status,String matrix_credit_type, String security_deposit, 
			String balloon_payment_status, String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			String  order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {


		obj_customer_quote_page = new CustomerQuotePage_HPNR_HPRPage();
		

		
		boolean monthlyTotalandBalanceToFinanceCheck = obj_customer_quote_page.put_part_exchange_values_and_check_monthly_total_payment_with_maintenance(part_exchange_actual, part_exchange_given, less_finance_settlement, order_deposit, finance_deposit, sheet_name);
	Assert.assertTrue(monthlyTotalandBalanceToFinanceCheck);

	}
		
	@Test(priority=7, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_HPNR_CP_customer_quote_part_exchange_value_edit_check_with_maintenance_test" })

	public void aquisition_quotes_HPNR_CP_quote_summary_values_verification_with_maintenance_test(String manufacturer, String model, 
			String Vehicle_Basic_price, String  road_tax_for_first_year, String percentage_cap_residual_value, String percentage_cap_maint_value,
			String residual_value_used, String maint_cost_used, String target_rental, String additional_terms, String additional_mileage,
			String vehicle_profit, String sales_price_percentage,String  maintenance_status,String matrix_credit_type, String security_deposit, 
			String balloon_payment_status, String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			String  order_deposit, String finance_deposit, String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {


		obj_quote_summary_page = new QuoteSummary_HPNR_CP_Page();
		
		boolean quote_summary_OTR_calculation = obj_quote_summary_page.quote_summary_OTR_calculation(sheet_name);
		Assert.assertTrue(quote_summary_OTR_calculation);

		boolean quote_summary_holding_cost_calculation = obj_quote_summary_page
				.quote_summary_holding_cost_calculation_with_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_holding_cost_calculation);

		boolean quote_summary_customer_quote_calculation = obj_quote_summary_page
				.quote_summary_customer_quote_summary_value_verification_with_maintenance(sheet_name);
		// Assert.assertTrue(quote_summary_customer_quote_calculation);

		boolean quote_summary_configuration_value_check = obj_quote_summary_page
				.quote_summary_configuration_value_verification_with_maintenance(sheet_name);
		// Assert.assertTrue(quote_summary_configuration_value_check1);

		obj_quote_summary_page.save_quote();
		
		boolean quote_summary_OTR_calculation1 = obj_quote_summary_page.quote_summary_OTR_calculation(sheet_name);
		Assert.assertTrue(quote_summary_OTR_calculation1);

		boolean quote_summary_holding_cost_calculation1 = obj_quote_summary_page
				.quote_summary_holding_cost_calculation_with_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_holding_cost_calculation1);

		boolean quote_summary_customer_quote_calculation1 = obj_quote_summary_page
				.quote_summary_customer_quote_summary_value_verification_with_maintenance(sheet_name);
		// Assert.assertTrue(quote_summary_customer_quote_calculation1);

		boolean quote_summary_configuration_value_check1 = obj_quote_summary_page
				.quote_summary_configuration_value_verification_with_maintenance(sheet_name);
		// Assert.assertTrue(quote_summary_configuration_value_check1);		
		
		
		boolean value_check_after_Base_Int_change = obj_quote_summary_page
				.quote_summary_edit_base_int_rate_value_verification_with_maintenance(sheet_name);
		// Assert.assertTrue(value_check_after_Base_Int_change);

		boolean value_check_after_customer_rate_over_base_change = obj_quote_summary_page
				.quote_summary_edit_customer_rate_over_base_value_verification(sheet_name);
		// Assert.assertTrue(value_check_after_customer_rate_over_base_change);

		boolean value_check_after_maint_margin_change = obj_quote_summary_page
				.quote_summary_edit_maintenance_margin_value_verification(sheet_name);
		// Assert.assertTrue(value_check_after_maint_margin_change);

	}
	
	

	@DataProvider(name = "testData")
	public Object[][] getTestData() throws IOException {
		Object[][] data = ReadExcelData.getTestData("HPNR_CP_withMaintenance");
		return data;
	}

}
