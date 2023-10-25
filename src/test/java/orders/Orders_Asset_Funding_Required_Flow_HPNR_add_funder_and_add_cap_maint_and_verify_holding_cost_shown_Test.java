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
public class Orders_Asset_Funding_Required_Flow_HPNR_add_funder_and_add_cap_maint_and_verify_holding_cost_shown_Test extends TestBase {


 OrdersListPage   obj_orders_list;
AssetFundingPage obj_asset_funding;
VehicleOrderPage obj_vehicle_order_tab;
 

	@Test(priority = 1)
	public void open_order_created_in_acquisition_test() throws IOException, InterruptedException {
		
		obj_orders_list = new OrdersListPage();
		
		obj_orders_list.search_order_in_list();
		
		obj_orders_list.open_searched_output();
		
		Assert.assertEquals(obj_orders_list.validate_selected_order_opened(), "Order summary");

	}
//	
//	@Test(priority = 2, dependsOnMethods = { "open_order_created_in_acquisition_test" })
//	public void deliver_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
//		
//		obj_vehicle_order_tab = new VehicleOrderPage();		
//		
//		Assert.assertEquals(obj_vehicle_order_tab.deliver_vehicle(), "Delivered");
//	}
	
	
	@Test(priority = 3, dependsOnMethods = { "open_order_created_in_acquisition_test" })
	public void verify_default_status_required_flow_test() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_default_status_for_required_flow_if_no_funder_is_added_in_a_quote());
		
	}
	
	@Test(priority = 4, dependsOnMethods = { "verify_default_status_required_flow_test" })
	public void verify_holding_cost_shown_test() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_holding_cost_shown());
		
	}
	
	
	
	@Test(priority = 5,dataProvider="testData1", dependsOnMethods = { "verify_holding_cost_shown_test" })
	public void add_funder(String quoteRef, String expiryDate,
			String term, String milesPerAnnum, String cashDeposit, String financeCharges, String documentFee,
			String monthlyPayment, String finalBalloonPayment, String optionToPurchaseFee, String sheet_name) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException {
		
		obj_asset_funding = new AssetFundingPage();		
		
		obj_asset_funding.add_funder(quoteRef, expiryDate,
				term, milesPerAnnum, cashDeposit,  financeCharges,  documentFee,
				 monthlyPayment,  finalBalloonPayment,  optionToPurchaseFee,  sheet_name);
		
	}
	
	@Test(priority = 6, dependsOnMethods = { "add_funder" })
	public void verify_status_after_adding_funder_required_flow_test() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_status_after_adding_funder_required_flow());
		
	}
	
	@Test(priority = 7, dependsOnMethods = { "verify_status_after_adding_funder_required_flow_test" })
	public void verify_status_after_selecting_the_added_funder_in_required_flow_test() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_status_after_selecting_funder_required_flow());
		
	}
	
	@Test(priority = 8,dataProvider="testData1", dependsOnMethods = { "verify_status_after_selecting_the_added_funder_in_required_flow_test" })
	public void verify_holding_cost_on_adding_funder_test(String quoteRef, String expiryDate,
			String term, String milesPerAnnum, String cashDeposit, String financeCharges, String documentFee,
			String monthlyPayment, String finalBalloonPayment, String optionToPurchaseFee, String sheet_name) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException {
		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_holding_cost_on_adding_funder(quoteRef, expiryDate,
				term, milesPerAnnum, cashDeposit,  financeCharges,  documentFee,
				 monthlyPayment,  finalBalloonPayment,  optionToPurchaseFee,  sheet_name));
		
	}
	
	@Test(priority = 9,dataProvider="testData1", dependsOnMethods = { "verify_holding_cost_on_adding_funder_test" })
	public void verify_holding_cost_on_adding_maintenance_test(String quoteRef, String expiryDate,
			String term, String milesPerAnnum, String cashDeposit, String financeCharges, String documentFee,
			String monthlyPayment, String finalBalloonPayment, String optionToPurchaseFee, String sheet_name) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException {
		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_holding_cost_on_adding_maintenance(sheet_name));
		
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
