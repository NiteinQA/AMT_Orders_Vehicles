package orders;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.AdvertizingPage;
import com.amt.pages.AssetFundingPage;
import com.amt.pages.OrdersListPage;
import com.amt.pages.StocklistPage;
import com.amt.pages.VehicleOrderPage;
import com.amt.testBase.TestBase;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Orders_Asset_Funding_Required_Flow_HPNR_Add_Cap_Maint_Test extends TestBase {


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
		
		Assert.assertTrue(obj_asset_funding.verify_default_status_for_required_flow_for_BCH());
		
	}
	
	@Test(priority = 4, dependsOnMethods = { "verify_default_status_required_flow_test" })
	public void verify_holding_cost_after_adding_cap_maint_test() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();		
		
        obj_asset_funding.verify_holding_cost_after_adding_cap_maint_for_HPNR();
		
	}
	


}
