package orders;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.AssetFundingPage;
import com.amt.pages.CustomerContractPage;
import com.amt.pages.OrdersListPage;
import com.amt.pages.VehicleOrderPage;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Orders_Ownbook_Hire_With_Customer_Flow_Test extends TestBase {


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
	
	@Test(priority = 3, dependsOnMethods = { "open_order_created_in_acquisition_test" })
	public void deliver_vehicle_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		
		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		obj_vehicle_order_tab.deliver_vehicle();
	}
	
	
	
//	@Test(priority =4, dependsOnMethods = { "deliver_vehicle_tab_test" })
//	public void verify_finance_documents_statuses_for_required_flow_test() throws IOException, InterruptedException, AWTException {
//		
//	     System.out.println("");
//
//	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
//
//
//	     obj_customer_contract =new CustomerContractPage();
//		
//		
//		Assert.assertTrue(obj_customer_contract.verify_finance_documents_statuses());
//		
//	}
//	
//	
//	@Test(priority =5, dependsOnMethods = { "verify_finance_documents_statuses_for_required_flow_test" })
//	public void verify_payment_statuses_for_required_flow_test() throws IOException, InterruptedException, AWTException {
//		
//	     System.out.println("");
//
//	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
//
//
//	     obj_customer_contract =new CustomerContractPage();
//		
//		
//		Assert.assertTrue(obj_customer_contract.verify_payments_statuses());
//		
//	}
//	
//	
//	@Test(priority =6, dependsOnMethods = { "verify_payment_statuses_for_required_flow_test" })
//	public void verify_delivery_status_on_customer_contract_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
//		
//	     System.out.println("");
//
//	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
//
//
//	     obj_customer_contract =new CustomerContractPage();
//		
//		
//		Assert.assertTrue(obj_customer_contract.verify_delivery_status_on_customer_contract_tab());
//		
//	}
//	
//	
//	@Test(priority =7, dependsOnMethods = { "verify_delivery_status_on_customer_contract_tab_test" })
//	public void verify_post_delivery_status_on_customer_contract_tab_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
//		
//	     System.out.println("");
//
//	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
//
//
//	     obj_customer_contract =new CustomerContractPage();
//		
//		
//		Assert.assertTrue(obj_customer_contract.verify_post_delivery_status_on_customer_contract_tab());
//		
//	}
	
	
//	@Test(priority =8, dependsOnMethods = { "open_order_created_in_acquisition_test" })
//	public void verify_the_vehicle_is_seen_in_sold_section_on_completing_the_order_on_customer_contract_page_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
//		
//	     System.out.println("");
//
//	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
//
//
//	     obj_customer_contract =new CustomerContractPage();
//		
//		
//		Assert.assertTrue(obj_customer_contract.verify_the_vehicle_is_seen_in_sold_vehicles_section_on_completing_the_order_on_customer_contract_page());
//		
//	}
//	
//
//	@Test(priority =9, dependsOnMethods = { "verify_the_vehicle_is_seen_in_sold_section_on_completing_the_order_on_customer_contract_page_test" })
//	public void verify_statuses_on_order_completed_test() throws IOException, InterruptedException, AWTException, ClassNotFoundException {
//		
//	     System.out.println("");
//
//	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
//
//
//	     obj_customer_contract =new CustomerContractPage();
//		
//		
//		Assert.assertTrue(obj_customer_contract.verify_statuses_on_searched_output_in_sold_vehicles_for_order_completed_record());
//		
//	}




	
	
}
