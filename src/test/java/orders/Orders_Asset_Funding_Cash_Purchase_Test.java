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
public class Orders_Asset_Funding_Cash_Purchase_Test extends TestBase {


 OrdersListPage   obj_orders_list;
AssetFundingPage obj_asset_funding;
 

	@Test(priority = 1)
	public void open_order_created_in_acquisition_test() throws IOException, InterruptedException {
		
		obj_orders_list = new OrdersListPage();
		
		obj_orders_list.search_order_in_list();
		
		obj_orders_list.open_searched_output();
		
		Assert.assertEquals(obj_orders_list.validate_selected_order_opened(), "Order summary");

	}
	
	
	@Test(priority = 2, dependsOnMethods = { "open_order_created_in_acquisition_test" })
	public void open_asset_funding_tab_and_verify_default_status_cash_purchase_pending() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();
		
		 assertTrue(obj_asset_funding.check_status_cash_purchase_pending());
	}

	
	@Test(priority = 3, dependsOnMethods = { "open_asset_funding_tab_and_verify_default_status_cash_purchase_pending" })
	public void complete_cash_purchase() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();
		
	   assertTrue(obj_asset_funding.open_asset_funding_tab_and_complete_cash_purchase());
		
	    
	}
	
	@Test(priority = 4, dependsOnMethods = { "complete_cash_purchase" })
	public void verify_status_cash_purchase_completed() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();
		
		 assertTrue( obj_asset_funding.check_status_cash_purchase_completed());
	}
	
	

}
