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
import com.amt.pages.CustomerContractPage;
import com.amt.pages.OrdersListPage;
import com.amt.pages.StocklistPage;
import com.amt.pages.VehicleOrderPage;
import com.amt.testBase.TestBase;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Orders_Verify_Outright_Purchase_With_Customer_Flow_Test extends TestBase {


 OrdersListPage   obj_orders_list;
AssetFundingPage obj_asset_funding;
VehicleOrderPage obj_vehicle_order_tab;
CustomerContractPage obj_customer_contract;
 

	@Test(priority = 1)
	public void open_order_created_in_acquisition_test() throws IOException, InterruptedException {
		

	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_orders_list = new OrdersListPage();
		
		obj_orders_list.search_order_in_list();
		
		obj_orders_list.open_searched_output();
		
		Assert.assertEquals(obj_orders_list.validate_selected_order_opened(), "Order summary");

	}
	
	
	@Test(priority =2, dependsOnMethods = { "open_order_created_in_acquisition_test" })
	public void pass_the_pre_order_check_test() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());


	     obj_customer_contract =new CustomerContractPage();
		
		
		obj_customer_contract.pre_order_pass_check();
		
	}
	
	@Test(priority = 3, dependsOnMethods = { "pass_the_pre_order_check_test" })
	public void verify_default_order_status_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_default_status_of_order_in_vehicle_order_tab());
		
		
	}

	
	@Test(priority = 4, dependsOnMethods = { "verify_default_order_status_vehicle_tab_test" })
	public void verify_order_status_after_uploading_order_form_in_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_order_status_in_vehicle_order_tab_after_uploading_order_form());
		
		
	}
	
	
	@Test(priority = 5, dependsOnMethods = { "verify_order_status_after_uploading_order_form_in_vehicle_tab_test" })
	public void verify_default_payment_status_on_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();	 	
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_default_payment_status_on_vehicle_tab());
		
		
	}
	
	@Test(priority = 6, dependsOnMethods = { "verify_default_payment_status_on_vehicle_tab_test" })
	public void verify_payment_status_after_selecting_payment_sent_date_on_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();	 	
		
        obj_vehicle_order_tab.verify_payment_status_after_selecting_payment_sent_date_on_vehicle_tab();		
		
	}

	
	@Test(priority = 7, dependsOnMethods = { "verify_payment_status_after_selecting_payment_sent_date_on_vehicle_tab_test" })
	public void verify_payment_status_should_be_reversed_to_payment_required_after_selecting_payment_by_AMT_on_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();	 	
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_payment_status_should_be_reversed_to_payment_required_after_selecting_payment_by_AMT_on_vehicle_tab());		
		
	}
	
	@Test(priority = 8, dependsOnMethods = { "verify_payment_status_should_be_reversed_to_payment_required_after_selecting_payment_by_AMT_on_vehicle_tab_test" })
	public void verify_payment_status_after_selecting_payment_sent_date_for_amount_due_on_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();	 //verify_payment_status_after_selecting_payment_sent_date_for_amount_due_if_payment_by_AMT	
		
		obj_vehicle_order_tab.verify_payment_status_after_selecting_payment_sent_date_for_amount_due_if_payment_by_AMT();
		
	}

	
	
	
	@Test(priority = 9, dependsOnMethods = { "verify_payment_status_after_selecting_payment_sent_date_for_amount_due_on_vehicle_tab_test" })
	public void open_asset_funding_tab_and_verify_default_status_cash_purchase_pending() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_asset_funding = new AssetFundingPage();
		
		 assertTrue(obj_asset_funding.check_status_cash_purchase_pending());
	}

	
	@Test(priority = 10, dependsOnMethods = { "open_asset_funding_tab_and_verify_default_status_cash_purchase_pending" })
	public void complete_cash_purchase() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_asset_funding = new AssetFundingPage();
		
	   assertTrue(obj_asset_funding.open_asset_funding_tab_and_complete_cash_purchase());
		
	    
	}
	
	@Test(priority = 11, dependsOnMethods = { "complete_cash_purchase" })
	public void verify_status_cash_purchase_completed() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();
		
		 assertTrue( obj_asset_funding.check_status_cash_purchase_completed());
	}
	
	
	@Test(priority = 12, dependsOnMethods = { "verify_status_cash_purchase_completed" })
	public void verify_default_status_acceptance_conditions_for_outright_purchase_flow() throws IOException, InterruptedException, AWTException {
		
		obj_customer_contract = new CustomerContractPage();
		
		assertTrue(obj_customer_contract.verify_default_status_for_acceptance_condition());
		
	}

	@Test(priority = 13, dependsOnMethods = { "verify_default_status_acceptance_conditions_for_outright_purchase_flow" })
	public void verify_payment_section_values() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
		obj_customer_contract = new CustomerContractPage();
		
		obj_customer_contract.verify_payment_values_shown_in_payment_section();
		
	}

	
	
	
	
	
	
//	
//	@Test(priority = 7, dependsOnMethods = { "verify_payment_status_after_selecting_payment_sent_date_for_amount_due_on_vehicle_tab_test" })
//	public void verify_delivery_status_after_selecting_date_offered_in_delivery_section_of_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
//		
//	     System.out.println("");
//
//	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
//
//		
//		obj_vehicle_order_tab = new VehicleOrderPage();		
//		
//		Assert.assertTrue(obj_vehicle_order_tab.verify_delivery_status_after_selecting_date_offered_in_delivery_section_of_vehicle_tab());
//		
//		
//	}
//
//	@Test(priority = 8, dependsOnMethods = { "verify_delivery_status_after_selecting_date_offered_in_delivery_section_of_vehicle_tab_test" })
//	public void verify_delivery_status_after_selecting_confirmed_delivery_date_in_delivery_section_of_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
//		
//	     System.out.println("");
//
//	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
//
//		
//		obj_vehicle_order_tab = new VehicleOrderPage();		
//		
//		Assert.assertTrue(obj_vehicle_order_tab.verify_delivery_status_after_selecting_confirmed_delivery_date_in_delivery_section_of_vehicle_tab());
//		
//		
//	}
//	
//	
//	@Test(priority = 9, dependsOnMethods = { "verify_delivery_status_after_selecting_confirmed_delivery_date_in_delivery_section_of_vehicle_tab_test" })
//	public void verify_delivery_status_after_uploading_delivery_note_in_post_delivery_section_on_vehicle_order_tab() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
//		
//	     System.out.println("");
//
//	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
//
//		
//		obj_vehicle_order_tab = new VehicleOrderPage();		
//		
//		Assert.assertTrue(obj_vehicle_order_tab.verify_delivery_status_after_uploading_delivery_note_in_post_delivery_section_on_vehicle_order_tab());
//		
//		
//	}
//
//
	

}
