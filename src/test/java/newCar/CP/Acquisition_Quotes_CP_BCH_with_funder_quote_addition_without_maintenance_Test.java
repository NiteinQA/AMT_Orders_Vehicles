package newCar.CP;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.apache.poi.ss.formula.FormulaParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePage_CP_BCH_Page;
import com.amt.HoldingCostPages.HoldingCost_CP_BCH_Page;
import com.amt.QuoteSummaryPages.QuoteSummary_CP_BCH_Page;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_CP_BCH_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_CP_BCH_with_funder_quote_addition_without_maintenance_Test extends TestBase {



	LoginPage obj_Login_Page;
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_CP_BCH_Page obj_contract_types_and_OTR_page;
	HoldingCost_CP_BCH_Page obj_holding_cost_CP_BCH_page;
	CustomerQuotePage_CP_BCH_Page obj_customer_quote_page;
	QuoteSummary_CP_BCH_Page obj_quote_summary_page;


	@Test(priority = 1, dataProvider = "testData")
	public void aquisition_quotes_CP_BCH_OTR_calculation_without_maintenance_test(String manufacturer, String model,
			String quoteRef, String expiryDate,String term,String milesPerAnnum,String cashDeposit,
			String documentFee, String monthlyPayment,String optionalFinalPayment,String optionToPurchaseFee, String pencePerExcessMileFinance,
		    String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			 String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage, String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_acq_listing_page = new AcquisitionListingPage();
		obj_vehicle_selection_page = new VehicleSelectionPage();
		obj_options_accessories = new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_CP_BCH_Page();
		

		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		boolean subtotal_after_discount = obj_contract_types_and_OTR_page
				.contractTypes_and_OTR_selection_CP_BCH_Ownbook_calculation(sheet_name);
		Assert.assertTrue(subtotal_after_discount);
	}
	
	@Test(priority=2, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_CP_BCH_OTR_calculation_without_maintenance_test" })

	public void aquisition_quotes_CP_BCH_after_discount_calculations_without_maintenance_test(String manufacturer, String model,
			String quoteRef, String expiryDate,String term,String milesPerAnnum,String cashDeposit,
			String documentFee, String monthlyPayment,String optionalFinalPayment,String optionToPurchaseFee,String pencePerExcessMileFinance,
		    String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			 String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage, String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_CP_BCH_Page();

		boolean otr_price_check = obj_contract_types_and_OTR_page
				.verify_after_discount_calculations_contract_types_page(sheet_name);
		Assert.assertTrue(otr_price_check);

	}
	
	@Test(priority=3, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_CP_BCH_after_discount_calculations_without_maintenance_test" })

	public void aquisition_quotes_CP_BCH_holding_cost_calculations_without_maintenance_test(String manufacturer, String model,
			String quoteRef, String expiryDate,String term,String milesPerAnnum,String cashDeposit,
			String documentFee, String monthlyPayment,String optionalFinalPayment,String optionToPurchaseFee,String pencePerExcessMileFinance,
		    String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			 String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage, String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_holding_cost_CP_BCH_page = new HoldingCost_CP_BCH_Page();
	
		boolean holding_cost_without_maintenance_boolean = obj_holding_cost_CP_BCH_page
				.verify_holding_cost_after_adding_funder_quote_without_maintenance( quoteRef,  expiryDate , term, milesPerAnnum, cashDeposit,
						  documentFee,  monthlyPayment , optionalFinalPayment ,optionToPurchaseFee, pencePerExcessMileFinance , sheet_name);
		Assert.assertTrue(holding_cost_without_maintenance_boolean);
		
	}
	
	@Test(priority=4, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_CP_BCH_holding_cost_calculations_without_maintenance_test" })

	public void aquisition_quotes_CP_BCH_customer_quote_payment_profile_calculations_without_maintenance_test(String manufacturer, String model,
			String quoteRef, String expiryDate,String term,String milesPerAnnum,String cashDeposit,
			String documentFee, String monthlyPayment,String optionalFinalPayment,String optionToPurchaseFee,String pencePerExcessMileFinance,
		    String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			 String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage, String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException, ClassNotFoundException, FormulaParseException, IllegalStateException {


		obj_customer_quote_page = new CustomerQuotePage_CP_BCH_Page();

		boolean customer_quote_for_payment_boolean = obj_customer_quote_page
				.customer_Quote_CP_BCH_for_one_payment_option_for_funder_quote_addition_without_maintenance_calculation(
						actual_part_exchange_value_from_excel, given_part_exchange_value_from_excel,
						less_finance_settlement_from_excel, order_deposit_from_excel, document_fee_from_excel, matrix_upsell,
						 maintenance_required, maintenance_margin, initial_payment,
						part_exchange_status, target_rental,sheet_name);
		Assert.assertTrue(customer_quote_for_payment_boolean);
		
		
		boolean cust_quote_for_upsell_values_boolean_status = obj_customer_quote_page
		.check_monthly_payments_on_adding_upsell_values_without_maintenance(security_deposit, matrix_upsell,
				referrer_upsell, add_terms, add_mileage, sheet_name);

        Assert.assertTrue(cust_quote_for_upsell_values_boolean_status);

        System.out.println("");
        System.out.println("");
		
		boolean monthly_finance_rental =obj_customer_quote_page.customer_quote_monthly_finance_rental_value_verification_with_part_exchange_without_maintenance(actual_part_exchange_value_from_excel,
				given_part_exchange_value_from_excel, less_finance_settlement_from_excel, order_deposit_from_excel, document_fee_from_excel, matrix_upsell, part_exchange_status, target_rental, sheet_name);
		
		Assert.assertTrue(monthly_finance_rental);
		
		
		boolean balance_due_value = obj_customer_quote_page.verify_balance_due_value(sheet_name);
		
		Assert.assertTrue(balance_due_value);
		
		boolean monthly_rental_values_on_updating_upsell_value =obj_customer_quote_page.check_monthly_payments_on_updating_customer_quote_summary_upsell_value_without_maintenance(matrix_upsell, sheet_name);

		Assert.assertTrue(monthly_rental_values_on_updating_upsell_value);

		
		
		boolean cutomer_quote_monthly_rental = obj_customer_quote_page
				.customer_Quote_CP_BCH_for_all_payment_option_for_funder_quote_addition_without_maintenance_calculation(initial_payment,sheet_name);
		Assert.assertTrue(cutomer_quote_monthly_rental);
	}
	
	@Test(priority=5, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_CP_BCH_customer_quote_payment_profile_calculations_without_maintenance_test" })

	public void aquisition_quotes_CP_BCH_quote_summary_values_verification_without_maintenance_test(String manufacturer, String model,
			String quoteRef, String expiryDate,String term,String milesPerAnnum,String cashDeposit,
			String documentFee, String monthlyPayment,String optionalFinalPayment,String optionToPurchaseFee,String pencePerExcessMileFinance,
		    String actual_part_exchange_value_from_excel, String given_part_exchange_value_from_excel,
			String less_finance_settlement_from_excel, String order_deposit_from_excel, String document_fee_from_excel,
			 String security_deposit, String matrix_upsell, String referrer_upsell, String add_terms, String add_mileage, String maintenance_required, String maintenance_margin, String initial_payment,
			String part_exchange_status, String target_rental, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException, ClassNotFoundException {

		obj_quote_summary_page = new QuoteSummary_CP_BCH_Page();


		boolean quote_summary_OTR_calculation = obj_quote_summary_page.quote_summary_OTR_calculation(sheet_name);
		Assert.assertTrue(quote_summary_OTR_calculation);
		
		boolean quote_summary_holding_cost_calculation = obj_quote_summary_page.quote_summary_holding_cost_calculation_without_maintenance_for_funder(sheet_name);
		Assert.assertTrue(quote_summary_holding_cost_calculation);
		
		boolean balance_due_value = obj_quote_summary_page.verify_balance_due_value(sheet_name);
		
		Assert.assertTrue(balance_due_value);
		
		boolean quote_summary_customer_quote_calculation= obj_quote_summary_page.quote_summary_customer_quote_summary_value_verification_without_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_customer_quote_calculation);
		
		boolean quote_summary_configuration_value_check = obj_quote_summary_page.quote_summary_configuration_value_verification_without_maintenance_for_funder(sheet_name);
		Assert.assertTrue(quote_summary_configuration_value_check);
		
		obj_quote_summary_page.save_quote();
		
		boolean quote_summary_OTR_calculation1 = obj_quote_summary_page.quote_summary_OTR_calculation(sheet_name);
		Assert.assertTrue(quote_summary_OTR_calculation1);
		
		boolean quote_summary_holding_cost_calculation1 = obj_quote_summary_page.quote_summary_holding_cost_calculation_without_maintenance_for_funder(sheet_name);
		Assert.assertTrue(quote_summary_holding_cost_calculation1);
		
		boolean balance_due_value1 = obj_quote_summary_page.verify_balance_due_value(sheet_name);
		
		Assert.assertTrue(balance_due_value1);
		
		boolean quote_summary_customer_quote_calculation1= obj_quote_summary_page.quote_summary_customer_quote_summary_value_verification_without_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_customer_quote_calculation1);
		
		boolean quote_summary_configuration_value_check1 = obj_quote_summary_page.quote_summary_configuration_value_verification_without_maintenance_for_funder(sheet_name);
		Assert.assertTrue(quote_summary_configuration_value_check1);
		
		boolean value_check_after_Finance_margin_change =obj_quote_summary_page.quote_summary_edit_finance_margin_value_verification_for_funder(sheet_name);
		Assert.assertTrue(value_check_after_Finance_margin_change);		
		

	}
	

	@DataProvider(name = "testData")
	public Object[][] getTestData() throws IOException {
		Object[][] data = ReadExcelData.getTestData("CP_BCH_funder_withoutMaintena");
		return data;
	}

}
