package usedLCV.OP;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePage_OP_OP_Page;
import com.amt.QuoteSummaryPages.QuoteSummaryOutrightCPPage;
import com.amt.QuoteSummaryPages.QuoteSummary_OP_OP_Page;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_OP_OP_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_OP_OP_used_LCV_Test extends TestBase {



	LoginPage obj_Login_Page;
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_OP_OP_Page obj_contract_types_and_OTR_page;	 
	CustomerQuotePage_OP_OP_Page obj_customer_quote_page;
	QuoteSummary_OP_OP_Page obj_quote_summary_page;


	@Test(priority = 1, dataProvider = "testData")
	public void aquisition_quotes_OP_OP_OTR_calculation_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, 
			String vehicle_Basic_sales_price_percentage, String vehicle_otr_sales_price_percentage,
			String vehicle_profit , String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			 String deposit,String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {

		obj_acq_listing_page = new AcquisitionListingPage();
		obj_vehicle_selection_page = new VehicleSelectionPage();
		obj_options_accessories = new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_OP_OP_Page();
		

		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle_for_used_car_flow(registrationNumber, mileage);
		obj_options_accessories.options_And_Accessories_selection_for_used_car();


		boolean cost_price_ex_vat_and_options_and_preparation_cost = obj_contract_types_and_OTR_page
				.contractTypes_selection_and_OTR_calculation(sheet_name);
		Assert.assertTrue(cost_price_ex_vat_and_options_and_preparation_cost);

	}
	
		
	
	@Test(priority=2, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_OP_OP_OTR_calculation_test" })

	public void aquisition_quotes_OP_OP_customer_quote_vehicle_profit_edit_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, 
			String vehicle_Basic_sales_price_percentage, String vehicle_otr_sales_price_percentage,
			String vehicle_profit , String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			 String deposit,String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {


		obj_customer_quote_page = new CustomerQuotePage_OP_OP_Page();
		
		boolean monthly_total_payment_after_editing_vehicle_profit =obj_customer_quote_page.edit_vehicle_profit_and_sales_price_for_used_vehicle(vehicle_profit, sheet_name);
		Assert.assertTrue(monthly_total_payment_after_editing_vehicle_profit);
		
		
		

	}
	
	
	@Test(priority=4, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_OP_OP_customer_quote_vehicle_profit_edit_test" })

	public void aquisition_quotes_OP_OP_customer_quote_part_exchange_value_edit_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, 
			String vehicle_Basic_sales_price_percentage, String vehicle_otr_sales_price_percentage,
			String vehicle_profit , String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			 String deposit,String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {


		obj_customer_quote_page = new CustomerQuotePage_OP_OP_Page();
		
		
		boolean monthlyTotalandBalanceToFinanceCheck = obj_customer_quote_page.put_part_exchange_values_and_check_pending_amount_for_used_vehicle(part_exchange_actual, part_exchange_given, less_finance_settlement, deposit, document_fee , sheet_name);
	Assert.assertTrue(monthlyTotalandBalanceToFinanceCheck);

	}
	
	
	@Test(priority=5, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_OP_OP_customer_quote_part_exchange_value_edit_test" })

	public void aquisition_quotes_OP_OP_quote_summary_values_verification_with_maintenance_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, 
			String vehicle_Basic_sales_price_percentage, String vehicle_otr_sales_price_percentage,
			String vehicle_profit , String part_exchange_actual, String part_exchange_given, String less_finance_settlement,
			 String deposit,String document_fee, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {


		obj_quote_summary_page = new QuoteSummary_OP_OP_Page();
		
		boolean quote_summary_value_check =obj_quote_summary_page.quote_summary_OTR_calculation(sheet_name);
		Assert.assertTrue(quote_summary_value_check);       
	}
	
	

	@DataProvider(name = "testData")
	public Object[][] getTestData() throws IOException {
		Object[][] data = ReadExcelData.getTestData("OP_OP_used_LCV");
		return data;
	}

}
