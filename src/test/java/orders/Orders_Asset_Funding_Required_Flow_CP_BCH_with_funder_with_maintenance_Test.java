package orders;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.AdvertizingPage;
import com.amt.pages.AssetFundingPage;
import com.amt.pages.OrdersListPage;
import com.amt.pages.StocklistPage;
import com.amt.pages.VehicleOrderPage;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Orders_Asset_Funding_Required_Flow_CP_BCH_with_funder_with_maintenance_Test extends TestBase {


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
	public void verify_default_status_required_flow_test() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_default_status_for_required_flow_for_BCH());
		
	}
	
	@Test(priority = 3,dataProvider="testData", dependsOnMethods = { "verify_default_status_required_flow_test" })
	public void verify_funder_with_same_term_and_mileage_can_not_be_added_test(String quoteRef, String expiryDate,
			String term, String milesPerAnnum, String cashDeposit, String financeCharges, String documentFee,
			String monthlyPayment, String optionalFinalPayment, String optionToPurchaseFee, String monthlyMaintPayment,
			String pencePerExcessMileFinance, String pencePerExcessMileMaintenance, String sheet_name) throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();	
		
		Assert.assertTrue(obj_asset_funding.verify_funder_with_same_term_and_mileage_can_not_be_added( quoteRef,  expiryDate,
				 term,  milesPerAnnum,  cashDeposit,  financeCharges,  documentFee,
				 monthlyPayment,  optionalFinalPayment,  optionToPurchaseFee,  monthlyMaintPayment,
				 pencePerExcessMileFinance,  pencePerExcessMileMaintenance,  sheet_name));				
	}
	
	@Test(priority = 4,dataProvider="testData", dependsOnMethods = { "verify_funder_with_same_term_and_mileage_can_not_be_added_test" })
	public void verify_funder_with_different_term_and_mileage_can_be_added_test(String quoteRef, String expiryDate,
			String term, String milesPerAnnum, String cashDeposit, String financeCharges, String documentFee,
			String monthlyPayment, String optionalFinalPayment, String optionToPurchaseFee, String monthlyMaintPayment,
			String pencePerExcessMileFinance, String pencePerExcessMileMaintenance, String sheet_name) throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();	
		
		Assert.assertTrue(obj_asset_funding.verify_funder_with_different_term_and_mileage_can_added( quoteRef,  expiryDate,
				 term,  milesPerAnnum,  cashDeposit,  financeCharges,  documentFee,
				 monthlyPayment,  optionalFinalPayment,  optionToPurchaseFee,  monthlyMaintPayment,
				 pencePerExcessMileFinance,  pencePerExcessMileMaintenance,  sheet_name));				
	}
	
	@Test(priority = 5,dataProvider="testData", dependsOnMethods = { "verify_funder_with_different_term_and_mileage_can_be_added_test" })
	public void verify_holding_cost_after_selecting_newly_added_funder_test(String quoteRef, String expiryDate,
			String term, String milesPerAnnum, String cashDeposit, String financeCharges, String documentFee,
			String monthlyPayment, String optionalFinalPayment, String optionToPurchaseFee, String monthlyMaintPayment,
			String pencePerExcessMileFinance, String pencePerExcessMileMaintenance, String sheet_name) throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();	
		
		Assert.assertTrue(obj_asset_funding.verify_holding_cost_after_selecting_newly_added_funder( quoteRef,  expiryDate,
				 term,  milesPerAnnum,  cashDeposit,  financeCharges,  documentFee,
				 monthlyPayment,  optionalFinalPayment,  optionToPurchaseFee,  monthlyMaintPayment,
				 pencePerExcessMileFinance,  pencePerExcessMileMaintenance,  sheet_name));				
	}
	
	
	@Test(priority = 6, dependsOnMethods = { "verify_holding_cost_after_selecting_newly_added_funder_test" })
	public void verify_residualValue_and_maintCost_fields_are_freezed_after_selecting_a_funder_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_asset_funding = new AssetFundingPage();	
		
		Assert.assertTrue(obj_asset_funding.verify_residualValue_maintCost_fields_are_freezed_after_selecting_a_funder());				
	}


	@Test(priority = 7, dependsOnMethods = { "verify_holding_cost_after_selecting_newly_added_funder_test" })
	public void verify_holding_cost_doesnt_change_if_funder_quote_is_selected_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
		obj_asset_funding = new AssetFundingPage();	
		
		Assert.assertTrue(obj_asset_funding.verify_holding_cost_doesnt_change_if_funder_quote_is_selected());				
	}

	
	
	
	
	@DataProvider(name = "testData")
	public Object[][] getTestData() throws IOException {
		Object[][] data = ReadExcelData.getTestData("CP_BCH_funder_Maint_Orders");
		return data;
	}

	
	
	//In this test class flow covered are :
	// 3,4,7

}
