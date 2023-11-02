package orders;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.AssetFundingPage;
import com.amt.pages.OrdersListPage;
import com.amt.pages.VehicleOrderPage;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Orders_Asset_Funding_Required_Flow_HPNR_Test extends TestBase {


 OrdersListPage   obj_orders_list;
AssetFundingPage obj_asset_funding;
VehicleOrderPage obj_vehicle_order_tab;
 

	@Test(priority = 1)
	public void open_order_created_in_acquisition_test() throws IOException, InterruptedException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_orders_list = new OrdersListPage();
		
		obj_orders_list.search_order_in_list();
		
		obj_orders_list.open_searched_output();
		
		Assert.assertEquals(obj_orders_list.validate_selected_order_opened(), "Order summary");

	}
	
	@Test(priority = 2, dependsOnMethods = { "open_order_created_in_acquisition_test" })
	public void deliver_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		Assert.assertEquals(obj_vehicle_order_tab.deliver_vehicle(), "Delivered");
	}
	
	
	@Test(priority = 3, dependsOnMethods = { "deliver_vehicle_tab_test" })
	public void verify_default_status_required_flow_test() throws IOException, InterruptedException, AWTException {
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_default_status_for_required_flow_if_no_funder_is_added_in_a_quote());
		
	}
	
	@Test(priority = 4, dependsOnMethods = { "verify_default_status_required_flow_test" })
	public void verify_holding_cost_shown_test() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_holding_cost_shown());
		
	}
	
	@Test(priority = 5,dataProvider="testData", dependsOnMethods = { "verify_holding_cost_shown_test" })
	public void verify_holding_cost_on_editing_percentage_cap_residual_test(String percentage_cap_residual_value, String residual_value_used, String additional_terms,
			String additional_mileage, String maintenance_status, String sheet_name) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException {
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_holding_cost_on_editing_percentage_cap_residual_value(percentage_cap_residual_value, residual_value_used, additional_terms,
				additional_mileage, maintenance_status,sheet_name));
		
	}
	
	@Test(priority = 6,dataProvider="testData", dependsOnMethods = { "verify_holding_cost_on_editing_percentage_cap_residual_test" })
	public void verify_holding_cost_on_editing_residual_value_test(String percentage_cap_residual_value, String residual_value_used, String additional_terms,
			String additional_mileage, String maintenance_status, String sheet_name) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException {
		

	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_holding_cost_on_editing_residual_value(percentage_cap_residual_value, residual_value_used, additional_terms,
				additional_mileage, maintenance_status,sheet_name));
		
	}
	
	@Test(priority = 7,dataProvider="testData", dependsOnMethods = { "verify_holding_cost_on_editing_residual_value_test" })
	public void verify_holding_cost_on_editing_add_term_and_mileage_test(String percentage_cap_residual_value, String residual_value_used, String additional_terms,
			String additional_mileage, String maintenance_status, String sheet_name) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException {
		
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_holding_cost_on_editing_additional_term_and_mileage(percentage_cap_residual_value, residual_value_used, additional_terms,
				additional_mileage, maintenance_status,sheet_name));
		
	}
	
	
	@Test(priority = 8,dataProvider="testData1", dependsOnMethods = { "verify_holding_cost_on_editing_add_term_and_mileage_test" })
	public void add_funder(String quoteRef, String expiryDate,
			String term, String milesPerAnnum, String cashDeposit, String financeCharges, String documentFee,
			String monthlyPayment, String finalBalloonPayment, String optionToPurchaseFee, String sheet_name) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException {
		
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		obj_asset_funding = new AssetFundingPage();		
		
		obj_asset_funding.add_funder(quoteRef, expiryDate,
				term, milesPerAnnum, cashDeposit,  financeCharges,  documentFee,
				 monthlyPayment,  finalBalloonPayment,  optionToPurchaseFee,  sheet_name);
		
	}
	
	@Test(priority = 9, dependsOnMethods = { "add_funder" })
	public void verify_status_after_adding_funder_required_flow_test() throws IOException, InterruptedException, AWTException {
		
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_status_after_adding_funder_required_flow());
		
	}
	
	@Test(priority = 10, dependsOnMethods = { "verify_status_after_adding_funder_required_flow_test" })
	public void verify_status_after_selecting_the_added_funder_in_required_flow_test() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_status_after_selecting_funder_required_flow());
		
	}
	
	@Test(priority = 11,dataProvider="testData1", dependsOnMethods = { "verify_status_after_selecting_the_added_funder_in_required_flow_test" })
	public void verify_holding_cost_on_adding_funder(String quoteRef, String expiryDate,
			String term, String milesPerAnnum, String cashDeposit, String financeCharges, String documentFee,
			String monthlyPayment, String finalBalloonPayment, String optionToPurchaseFee, String sheet_name) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();		
		
		obj_asset_funding.verify_holding_cost_on_adding_funder(quoteRef, expiryDate,
				term, milesPerAnnum, cashDeposit,  financeCharges,  documentFee,
				 monthlyPayment,  finalBalloonPayment,  optionToPurchaseFee,  sheet_name);
		
	}
	
	@Test(priority =12, dependsOnMethods = { "verify_holding_cost_on_adding_funder" })
	public void verify_finance_documents_statuses_for_required_flow_test() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_finance_documents_statuses());
		
	}
	
	@Test(priority = 13, dependsOnMethods = { "verify_finance_documents_statuses_for_required_flow_test" })
	public void verify_payments_to_funder_statuses_for_required_flow_test() throws IOException, InterruptedException, AWTException {
		
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_payments_to_funder_statuses());
		
	}
	
	@Test(priority = 14, dependsOnMethods = { "verify_payments_to_funder_statuses_for_required_flow_test" })
	public void verify_completed_status_for_required_flow_test() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_completed_status());
		
	}

	//In this test class flow covered are :
	// b2.1

	
	@DataProvider(name = "testData")
	public Object[][] getTestData() throws IOException {
		Object[][] data = ReadExcelData.getTestData("HPNR_HPNR_woMaint_orders");
		return data;
	}
	
	@DataProvider(name = "testData1")
	public Object[][] getTestData1() throws IOException {
		Object[][] data = ReadExcelData.getTestData("HPNR_HPNR_funder_woMaint_Orders");
		return data;
	}

	
	
}
