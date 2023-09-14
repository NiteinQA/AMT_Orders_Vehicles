package orders;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.AdvertizingPage;
import com.amt.pages.OrdersListPage;
import com.amt.pages.StocklistPage;
import com.amt.pages.VehicleOrderPage;
import com.amt.testBase.TestBase;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Orders_and_Stocklist_Publish_Test extends TestBase {


 OrdersListPage   obj_orders_list;
 VehicleOrderPage obj_vehicle_order_tab;
 StocklistPage    obj_stocklist;
 AdvertizingPage obj_advertising_tab;
 

	@Test(priority = 1)
	public void open_order_created_in_acquisition_test() throws IOException, InterruptedException {
		
		obj_orders_list = new OrdersListPage();
		
		obj_orders_list.search_order_in_list();
		
		obj_orders_list.open_searched_output();
		
		Assert.assertEquals(obj_orders_list.validate_selected_order_opened(), "Order summary");

	}
	
	@Test(priority = 2, dependsOnMethods = { "open_order_created_in_acquisition_test" })
	public void open_vehicle_order_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
		obj_vehicle_order_tab = new VehicleOrderPage();
		
		
		Assert.assertEquals(obj_vehicle_order_tab.deliver_vehicle(), "Delivered");
	}
	
	@Test(priority = 3, dependsOnMethods = { "open_vehicle_order_tab_test" } )
	public void open_order_in_stocklist_after_delivering_vehicel_from_orders() throws IOException, InterruptedException {
		
		obj_stocklist = new StocklistPage();
		
		obj_stocklist.search_order_in_list();
		
		obj_orders_list = new OrdersListPage();
		
		obj_orders_list.open_searched_output();
		
		Assert.assertEquals(obj_stocklist.validate_selected_order_opened(), "ORDER STATUS");

	}

	@Test(priority = 4, dependsOnMethods = { "open_order_in_stocklist_after_delivering_vehicel_from_orders" } )
	public void publish_vehicle_to_front_office() throws IOException, InterruptedException {
		
		obj_advertising_tab = new AdvertizingPage();
		
		
		obj_advertising_tab.open_advertizing_tab();
		
		obj_advertising_tab.publish_vehicle_to_amt_website();
		

	}
	

}
