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
public class Orders_Asset_Funding_Verify_Stocking_Plan_Lombard_Demo_Is_Available_If_Channel_Is_Internal_Use_Test extends TestBase {


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
	
	@Test(priority = 2, dependsOnMethods = { "open_order_created_in_acquisition_test" })
	public void deliver_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		Assert.assertEquals(obj_vehicle_order_tab.deliver_vehicle(), "Delivered");
	}

	
	@Test(priority = 3, dependsOnMethods = { "deliver_vehicle_tab_test" })
	public void verify_stocking_plan_Lombard_Demo_is_available_for_channel_internal_use_test() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_stocking_plan_Lombard_Demo_is_available_if_channel_is_internal_use());
		
	}
	
	

}
