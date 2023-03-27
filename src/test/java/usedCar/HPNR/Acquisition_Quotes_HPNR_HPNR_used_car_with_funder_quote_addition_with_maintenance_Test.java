package usedCar.HPNR;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePage_HPNR_HPNRPage;
import com.amt.HoldingCostPages.HoldingCost_HPNR_HPNRPage;
import com.amt.QuoteSummaryPages.QuoteSummary_HPNR_HPNRPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_HPNR_HPNR_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_HPNR_HPNR_used_car_with_funder_quote_addition_with_maintenance_Test extends TestBase {

	LoginPage obj_Login_Page;
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_HPNR_HPNR_Page obj_contract_types_and_OTR_page;
	HoldingCost_HPNR_HPNRPage obj_holding_cost_HPNR_HPNR_page;
	CustomerQuotePage_HPNR_HPNRPage obj_customer_quote_page;
	QuoteSummary_HPNR_HPNRPage obj_quote_summary_page;

	@Test(priority = 1, dataProvider = "testData")
	public void aquisition_quotes_OTR_calculation_with_maintenance_test(String registrationNumber, String mileage, String vehicelCostPrice, String options_and_preparation_cost,
			String quoteRef, String expiryDate, String term, String milesPerAnnum, String cashDeposit,
			String financeCharges, String documentFee, String monthlyPayment, String finalBallonPayment,
			String optionToPurchaseFee, String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String finance_deposit,String document_fee_from_excel, String upsell, String maintenance_required,
			String maintenance_margin, String initial_payment, String part_exchange_status, String target_rental,String matrix_credit_type,
			String balloon_payment_status, 
			String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_acq_listing_page = new AcquisitionListingPage();
		obj_vehicle_selection_page = new VehicleSelectionPage();
		obj_options_accessories = new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_HPNR_HPNR_Page();

		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle_for_used_vehicle_flow(registrationNumber, mileage);
		obj_options_accessories.options_And_Accessories_selection_for_used_car();

		boolean cost_price_ex_vat_and_options_and_preparation_cost = obj_contract_types_and_OTR_page
				.contractTypes_selection_and_OTR_calculation(sheet_name);
		Assert.assertTrue(cost_price_ex_vat_and_options_and_preparation_cost);
	}

	@Test(priority = 2, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_OTR_calculation_with_maintenance_test" })

	public void aquisition_quotes_used_car_edit_cost_price_and_check_OTR_with_maintenance_test(String registrationNumber, String mileage, String vehicelCostPrice, String options_and_preparation_cost,
			String quoteRef, String expiryDate, String term, String milesPerAnnum, String cashDeposit,
			String financeCharges, String documentFee, String monthlyPayment, String finalBallonPayment,
			String optionToPurchaseFee, String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String finance_deposit,String document_fee_from_excel, String upsell, String maintenance_required,
			String maintenance_margin, String initial_payment, String part_exchange_status, String target_rental,String matrix_credit_type,
			String balloon_payment_status, 
			String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_HPNR_HPNR_Page();

		//boolean cost_price_ex_vat_and_options_and_preparation_cost = obj_contract_types_and_OTR_page
//		.edit_vehicle_cost_price_and_check_OTR_price(vehicelCostPrice, options_and_preparation_cost, sheet_name);
//Assert.assertTrue(cost_price_ex_vat_and_options_and_preparation_cost);

	}

	@Test(priority = 3, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_used_car_edit_cost_price_and_check_OTR_with_maintenance_test" })

	public void aquisition_quotes_holding_cost_calculations_with_maintenance_test(String registrationNumber, String mileage, String vehicelCostPrice, String options_and_preparation_cost,
			String quoteRef, String expiryDate, String term, String milesPerAnnum, String cashDeposit,
			String financeCharges, String documentFee, String monthlyPayment, String finalBallonPayment,
			String optionToPurchaseFee, String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String finance_deposit,String document_fee_from_excel, String upsell, String maintenance_required,
			String maintenance_margin, String initial_payment, String part_exchange_status, String target_rental,String matrix_credit_type,
			String balloon_payment_status, 
			String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_holding_cost_HPNR_HPNR_page = new HoldingCost_HPNR_HPNRPage();

		boolean holding_cost_with_maintenance_boolean = obj_holding_cost_HPNR_HPNR_page
				.verify_holding_cost_after_adding_funder_quote_with_maintenance(quoteRef, expiryDate, term,
						milesPerAnnum, cashDeposit, financeCharges, documentFee, monthlyPayment, finalBallonPayment,
						optionToPurchaseFee, sheet_name);
		Assert.assertTrue(holding_cost_with_maintenance_boolean);

	}

	@Test(priority = 4, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_holding_cost_calculations_with_maintenance_test" })

	public void aquisition_quotes_customer_quote_calculations_check_monthly_finance_payment_with_maintenance_test(String registrationNumber, String mileage, String vehicelCostPrice, String options_and_preparation_cost,
			String quoteRef, String expiryDate, String term, String milesPerAnnum, String cashDeposit,
			String financeCharges, String documentFee, String monthlyPayment, String finalBallonPayment,
			String optionToPurchaseFee, String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String finance_deposit,String document_fee_from_excel, String upsell, String maintenance_required,
			String maintenance_margin, String initial_payment, String part_exchange_status, String target_rental,String matrix_credit_type,
			String balloon_payment_status, 
			String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException, NumberFormatException, ClassNotFoundException {

		obj_customer_quote_page = new CustomerQuotePage_HPNR_HPNRPage();

		boolean monthly_finance_payment_check = obj_customer_quote_page
				.check_monthly_finance_payment_on_customer_quote_for_used_car_with_funder_quote_addition_with_maintenance(driver, maintenance_required,
						matrix_credit_type, balloon_payment_status, order_deposit_from_excel, finance_deposit, document_fee_from_excel,
						sheet_name);
		Assert.assertTrue(monthly_finance_payment_check);

	}

	@Test(priority = 7, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_customer_quote_calculations_check_monthly_finance_payment_with_maintenance_test" })

	public void aquisition_quotes_customer_quote_monthly_total_payment_after_balloon_payment_off_with_maintenance_test(String registrationNumber, String mileage, String vehicelCostPrice, String options_and_preparation_cost,
			String quoteRef, String expiryDate, String term, String milesPerAnnum, String cashDeposit,
			String financeCharges, String documentFee, String monthlyPayment, String finalBallonPayment,
			String optionToPurchaseFee, String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String finance_deposit,String document_fee_from_excel, String upsell, String maintenance_required,
			String maintenance_margin, String initial_payment, String part_exchange_status, String target_rental,String matrix_credit_type,
			String balloon_payment_status, 
			String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_customer_quote_page = new CustomerQuotePage_HPNR_HPNRPage();

		boolean monthlyFinancePaymentCheckAfterBalloonPaymentOff = obj_customer_quote_page
				.check_monthly_total_payment_after_making_balloon_payment_off_with_maintenance(sheet_name);

		Assert.assertTrue(monthlyFinancePaymentCheckAfterBalloonPaymentOff);
	}

	@Test(priority = 5, dataProvider = "testData", dependsOnMethods = {
			"aquisition_quotes_customer_quote_monthly_total_payment_after_balloon_payment_off_with_maintenance_test" })

	public void aquisition_quotes_quote_summary_values_verification_with_maintenance_test(String registrationNumber, String mileage, String vehicelCostPrice, String options_and_preparation_cost,
			String quoteRef, String expiryDate, String term, String milesPerAnnum, String cashDeposit,
			String financeCharges, String documentFee, String monthlyPayment, String finalBallonPayment,
			String optionToPurchaseFee, String actual_part_exchange_value_from_excel,
			String given_part_exchange_value_from_excel, String less_finance_settlement_from_excel,
			String order_deposit_from_excel, String finance_deposit,String document_fee_from_excel, String upsell, String maintenance_required,
			String maintenance_margin, String initial_payment, String part_exchange_status, String target_rental,String matrix_credit_type,
			String balloon_payment_status, 
			String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_quote_summary_page = new QuoteSummary_HPNR_HPNRPage();

		boolean quote_summary_OTR_calculation = obj_quote_summary_page.quote_summary_OTR_calculation_for_used_car(sheet_name);
		Assert.assertTrue(quote_summary_OTR_calculation);

		boolean quote_summary_holding_cost_calculation = obj_quote_summary_page
				.quote_summary_holding_cost_calculation_for_funder_with_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_holding_cost_calculation);

		boolean quote_summary_customer_quote_calculation = obj_quote_summary_page
				.quote_summary_customer_quote_summary_value_verification_for_funder_with_maintenance(sheet_name);
		// Assert.assertTrue(quote_summary_customer_quote_calculation);

		boolean quote_summary_configuration_value_check = obj_quote_summary_page
				.quote_summary_configuration_value_verification_with_maintenance(sheet_name);
		// Assert.assertTrue(quote_summary_configuration_value_check);

		obj_quote_summary_page.save_quote();

		boolean quote_summary_OTR_calculation1 = obj_quote_summary_page.quote_summary_OTR_calculation_for_used_car(sheet_name);
		Assert.assertTrue(quote_summary_OTR_calculation1);

		boolean quote_summary_holding_cost_calculation1 = obj_quote_summary_page
				.quote_summary_holding_cost_calculation_for_funder_with_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_holding_cost_calculation1);

		boolean quote_summary_customer_quote_calculation1 = obj_quote_summary_page
				.quote_summary_customer_quote_summary_value_verification_for_funder_with_maintenance(sheet_name); // Assert.assertTrue(quote_summary_customer_quote_calculation);

		boolean quote_summary_configuration_value_check1 = obj_quote_summary_page
				.quote_summary_configuration_value_verification_with_maintenance(sheet_name);
		// Assert.assertTrue(quote_summary_configuration_value_check1);

		boolean value_check_after_Base_Int_change = obj_quote_summary_page
				.quote_summary_edit_base_int_rate_value_verification_for_funder_with_maintenance(sheet_name);
		// Assert.assertTrue(value_check_after_Base_Int_change);

		boolean value_check_after_customer_rate_over_base_change = obj_quote_summary_page
				.quote_summary_edit_customer_rate_over_base_value_verification(sheet_name);
		// Assert.assertTrue(value_check_after_customer_rate_over_base_change);

		boolean value_check_after_maint_margin_change = obj_quote_summary_page
				.quote_summary_edit_maintenance_margin_value_verification(sheet_name);
		Assert.assertTrue(value_check_after_maint_margin_change);
		
		
		}

	@DataProvider(name = "testData")
	public Object[][] getTestData() throws IOException {
		Object[][] data = ReadExcelData.getTestData("HPNR_HPNR_funder_Maint_used_car");
		return data;
	}

}