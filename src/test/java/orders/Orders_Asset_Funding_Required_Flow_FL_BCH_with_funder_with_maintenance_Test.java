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
public class Orders_Asset_Funding_Required_Flow_FL_BCH_with_funder_with_maintenance_Test extends TestBase {


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
	public void verify_default_status_required_flow_test() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_default_status_for_required_flow_for_BCH());
		
	}
	
	
	@Test(priority = 3, dependsOnMethods = { "verify_default_status_required_flow_test" })
	public void verify_default_funder_is_not_deleted_test() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_default_funder_is_not_deleted());
		
	}
	
	
	@Test(priority = 4, dependsOnMethods = { "verify_default_funder_is_not_deleted_test" })
	public void verify_selected_funder_can_not_be_edited_test() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_selected_funder_can_not_be_edited());
		
	}
	
	@Test(priority = 5, dependsOnMethods = { "verify_selected_funder_can_not_be_edited_test" })
	public void verify_document_is_uploaded_in_funder_test() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_document_is_uploaded_in_funder());
		
	}
	
	@Test(priority = 6, dependsOnMethods = { "verify_document_is_uploaded_in_funder_test" })
	public void verify_funders_are_deleted_on_changing_contract_type_test() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_funders_are_deleted_on_changing_contract_type());
		
	}
	
	//In this test class flow covered are :
	// 1, 2, 11, 12

}
