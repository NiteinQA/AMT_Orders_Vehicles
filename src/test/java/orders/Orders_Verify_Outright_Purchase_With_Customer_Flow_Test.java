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
	public void T1_open_order_created_in_acquisition_test() throws IOException, InterruptedException {
		

	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_orders_list = new OrdersListPage();
		
		obj_orders_list.search_order_in_list();
		
		obj_orders_list.open_searched_output();
		
		Assert.assertEquals(obj_orders_list.validate_selected_order_opened(), "Order summary");

	}
	
	
	@Test(priority =2, dependsOnMethods = { "open_order_created_in_acquisition_test" })
	public void T2_pass_the_pre_order_check_test() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());


	     obj_customer_contract =new CustomerContractPage();
		
		
		obj_customer_contract.pre_order_pass_check();
		
	}
	
	@Test(priority = 3, dependsOnMethods = { "pass_the_pre_order_check_test" })
	public void T3_verify_default_order_status_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_default_status_of_order_in_vehicle_order_tab());
		
		
	}

	
	@Test(priority = 4, dependsOnMethods = { "verify_default_order_status_vehicle_tab_test" })
	public void T4_verify_order_status_after_uploading_order_form_in_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_order_status_in_vehicle_order_tab_after_uploading_order_form());
		
		
	}
	
	
	@Test(priority = 5, dependsOnMethods = { "verify_order_status_after_uploading_order_form_in_vehicle_tab_test" })
	public void T5_verify_default_payment_status_on_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();	 	
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_default_payment_status_on_vehicle_tab());
		
		
	}
	
	@Test(priority = 6, dependsOnMethods = { "verify_default_payment_status_on_vehicle_tab_test" })
	public void T6_verify_payment_status_after_selecting_payment_sent_date_on_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();	 	
		
        obj_vehicle_order_tab.verify_payment_status_after_selecting_payment_sent_date_on_vehicle_tab();		
		
	}

	
	@Test(priority = 7, dependsOnMethods = { "verify_payment_status_after_selecting_payment_sent_date_on_vehicle_tab_test" })
	public void T7_verify_payment_status_should_be_reversed_to_payment_required_after_selecting_payment_by_AMT_on_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();	 	
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_payment_status_should_be_reversed_to_payment_required_after_selecting_payment_by_AMT_on_vehicle_tab());		
		
	}
	
	@Test(priority = 8, dependsOnMethods = { "verify_payment_status_should_be_reversed_to_payment_required_after_selecting_payment_by_AMT_on_vehicle_tab_test" })
	public void T8_verify_payment_status_after_selecting_payment_sent_date_for_amount_due_on_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();	 //verify_payment_status_after_selecting_payment_sent_date_for_amount_due_if_payment_by_AMT	
		
		obj_vehicle_order_tab.verify_payment_status_after_selecting_payment_sent_date_for_amount_due_if_payment_by_AMT();
		
	}

	
	
	
	@Test(priority = 9, dependsOnMethods = { "verify_payment_status_after_selecting_payment_sent_date_for_amount_due_on_vehicle_tab_test" })
	public void T9_open_asset_funding_tab_and_verify_default_status_cash_purchase_pending() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_asset_funding = new AssetFundingPage();
		
		 assertTrue(obj_asset_funding.check_status_cash_purchase_pending());
	}

	
	@Test(priority = 10, dependsOnMethods = { "open_asset_funding_tab_and_verify_default_status_cash_purchase_pending" })
	public void T10_complete_cash_purchase() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_asset_funding = new AssetFundingPage();
		
	   assertTrue(obj_asset_funding.open_asset_funding_tab_and_complete_cash_purchase());
		
	    
	}
	
	@Test(priority = 11, dependsOnMethods = { "complete_cash_purchase" })
	public void T11_verify_status_cash_purchase_completed() throws IOException, InterruptedException, AWTException {
		
		obj_asset_funding = new AssetFundingPage();
		
		 assertTrue( obj_asset_funding.check_status_cash_purchase_completed());
	}
	
	
	@Test(priority = 12, dependsOnMethods = { "verify_status_cash_purchase_completed" })
	public void T12_verify_default_status_acceptance_conditions_for_outright_purchase_flow() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_customer_contract = new CustomerContractPage();
		
		assertTrue(obj_customer_contract.verify_default_status_for_acceptance_condition_on_customer_contract_tab());
		
	}

	@Test(priority = 13, dependsOnMethods = { "verify_default_status_acceptance_conditions_for_outright_purchase_flow" })
	public void T13_verify_payment_section_values() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_customer_contract = new CustomerContractPage();
		
		assertTrue(obj_customer_contract.verify_payment_values_shown_in_payment_section());
		
	}

	@Test(priority = 14, dependsOnMethods = { "verify_payment_section_values" })
	public void T14_verify_payment_default_status() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_customer_contract = new CustomerContractPage();
		
		assertTrue(obj_customer_contract.verify_payment_default_status_on_customer_contract_tab());
		
	}

	@Test(priority = 15, dependsOnMethods = { "verify_payment_default_status" })
	public void T15_make_payment_with_new_payment() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_customer_contract = new CustomerContractPage();
		
		obj_customer_contract.make_payment_for_outright_purchase();
		
	}
	
	@Test(priority = 16, dependsOnMethods = { "make_payment_with_new_payment" })
	public void T16_verify_payment_status_after_making_a_payment_on_customer_contract_tab() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_customer_contract = new CustomerContractPage();
		
		obj_customer_contract.verify_payments_status_after_making_a_payment();
		
	}


	@Test(priority = 17, dependsOnMethods = { "verify_payment_status_after_making_a_payment_on_customer_contract_tab" })
	public void T17_verify_delivery_status_after_selecting_date_offered_in_delivery_section_of_customer_contract_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_delivery_status_after_selecting_date_offered_in_delivery_section_of_vehicle_tab());
		
		
	}

	@Test(priority = 18, dependsOnMethods = { "verify_delivery_status_after_selecting_date_offered_in_delivery_section_of_customer_contract_tab_test" })
	public void T18_verify_delivery_status_after_selecting_confirmed_delivery_date_in_delivery_section_of_customer_contract_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_delivery_status_after_selecting_confirmed_delivery_date_in_delivery_section_of_vehicle_tab());
		
		
	}
	
	
	@Test(priority = 19, dependsOnMethods = { "verify_delivery_status_after_selecting_confirmed_delivery_date_in_delivery_section_of_vehicle_tab_test" })
	public void T19_verify_delivery_status_after_uploading_delivery_note_in_post_delivery_section_on_customer_contract_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_delivery_status_after_uploading_delivery_note_in_post_delivery_section_on_vehicle_order_tab());
		
		
	}
	
	@Test(priority = 20, dependsOnMethods = { "verify_delivery_status_after_selecting_confirmed_delivery_date_in_delivery_section_of_vehicle_tab_test" })
	public void T20_verify_delivery_status_on_customer_contract_tab_and_vehicle_order_tab_are_same_when_deliver_to_customer_toggle_button_in_on_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_delivery_status_on_customer_contract_tab_and_vehicle_order_tab_are_same_when_deliver_to_customer_toggle_button_in_on());
		
		
	}
	
	@Test(priority = 21, dependsOnMethods = { "open_order_created_in_acquisition_test" })
	public void T21_verify_payout_pack_statuses_on_customer_contract_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		
	     obj_customer_contract = new CustomerContractPage();
	     
	     
	     obj_customer_contract.verify_payout_pack_statuses();
		
	}
	
	
	@Test(priority = 22, dependsOnMethods = { "verify_payout_pack_statuses_on_customer_contract_tab_test" })
	public void T22_verify_delivery_section_gets_enabled_when_deliver_to_customer_toggle_button_is_made_off_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
        obj_vehicle_order_tab.verify_delivery_section_gets_enabled_and_delivery_status_is_reversed_to_default_when_deliver_to_customer_toggle_button_is_made_off();
		
		
	}
	
	
	@Test(priority = 23, dependsOnMethods = { "verify_delivery_section_gets_enabled_when_deliver_to_customer_toggle_button_is_made_off_test" })
	public void T23_verify_delivery_status_after_selecting_date_offered_in_delivery_section_of_vehicle_order_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_delivery_status_after_selecting_date_offered_in_delivery_section_of_vehicle_tab());
		
		
	}

	@Test(priority = 24, dependsOnMethods = { "verify_delivery_status_after_selecting_date_offered_in_delivery_section_of_customer_contract_tab_test" })
	public void T24_verify_delivery_status_after_selecting_confirmed_delivery_date_in_delivery_section_of_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_delivery_status_after_selecting_confirmed_delivery_date_in_delivery_section_of_vehicle_tab());
		
		
	}
	
	
	@Test(priority = 25, dependsOnMethods = { "verify_delivery_status_after_selecting_confirmed_delivery_date_in_delivery_section_of_vehicle_tab_test" })
	public void T25_verify_delivery_status_after_uploading_delivery_note_in_post_delivery_section_on_vehicle_order_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		Assert.assertTrue(obj_vehicle_order_tab.verify_delivery_status_after_uploading_delivery_note_in_post_delivery_section_on_vehicle_order_tab());
		
		
	}
	

}
