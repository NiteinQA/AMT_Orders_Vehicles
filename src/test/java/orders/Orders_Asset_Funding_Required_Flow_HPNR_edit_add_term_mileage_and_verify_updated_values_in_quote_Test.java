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
public class Orders_Asset_Funding_Required_Flow_HPNR_edit_add_term_mileage_and_verify_updated_values_in_quote_Test extends TestBase {


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
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_default_status_for_required_flow_if_no_funder_is_added_in_a_quote());
		
	}
	
//	@Test(priority = 4, dependsOnMethods = { "verify_default_status_required_flow_test" })
//	public void verify_holding_cost_shown_test() throws IOException, InterruptedException, AWTException {
//		
//		obj_asset_funding = new AssetFundingPage();		
//		
//		Assert.assertTrue(obj_asset_funding.verify_holding_cost_shown());
//		
//	}
	
	
	
	@Test(priority = 5,dataProvider="testData", dependsOnMethods = { "verify_default_status_required_flow_test" })
	public void verify_holding_cost_on_editing_add_term_and_mileage_test(String percentage_cap_residual_value, String residual_value_used, String additional_terms,
			String additional_mileage, String maintenance_status, String sheet_name) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException {
		
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_holding_cost_on_editing_additional_term_and_mileage(percentage_cap_residual_value, residual_value_used, additional_terms,
				additional_mileage, maintenance_status,sheet_name));
		
	}
	
	@Test(priority = 6,dataProvider="testData", dependsOnMethods = { "verify_holding_cost_on_editing_add_term_and_mileage_test" })
	public void verify_quote_summary_in_quote_on_editing_add_term_and_mileage_test(String percentage_cap_residual_value, String residual_value_used, String additional_terms,
			String additional_mileage, String maintenance_status, String sheet_name) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();		
		
		obj_asset_funding.verify_quote_summary_values_in_quote_on_editing_additional_term_and_mileage(sheet_name);
		
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
