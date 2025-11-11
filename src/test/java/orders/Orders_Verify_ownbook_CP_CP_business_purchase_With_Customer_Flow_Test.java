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
import com.amt.pages.CustomerContractPage;
import com.amt.pages.OrdersListPage;
import com.amt.pages.StocklistPage;
import com.amt.pages.VehicleOrderPage;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Orders_Verify_ownbook_CP_CP_business_purchase_With_Customer_Flow_Test extends TestBase {


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
	
	
	@Test(priority =2, dependsOnMethods = { "T1_open_order_created_in_acquisition_test" })
	public void T2_pre_order_pass_check_in_customer_contract_tab_test() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());


	     obj_customer_contract =new CustomerContractPage();
		
		
		obj_customer_contract.pre_order_pass_check();
		
	}
	
	
	@Test(priority = 3, dependsOnMethods = { "T2_pre_order_pass_check_in_customer_contract_tab_test" })
	public void T3_verify_summary_section_values() throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_customer_contract = new CustomerContractPage();
		
		//assertTrue(
				obj_customer_contract.verify_quote_summary_and_configuration_values_on_customer_contract_tab_for_ownbook_purchase_cp_cp_flow();
		//		);
		
	}
	
	
	
	
	
	@Test(priority = 4, dependsOnMethods = { "T3_verify_summary_section_values" })
	public void T4_verify_that_editing_the_basic_cash_price_does_not_affect_the_monthly_payment_on_customer_contract_tab_test() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_customer_contract = new CustomerContractPage();
		
		assertTrue(obj_customer_contract.verify_that_editing_the_basic_cash_price_does_not_affect_the_monthly_payment_on_customer_contract_tab());
		
	}
	
	
	
	@Test(priority = 5, dependsOnMethods = { "T4_verify_that_editing_the_basic_cash_price_does_not_affect_the_monthly_payment_on_customer_contract_tab_test" })
	public void T5_verify_that_adding_other_support_does_not_affect_the_monthly_payment_on_customer_contract_tab_test() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_customer_contract = new CustomerContractPage();
		
		assertTrue(obj_customer_contract.verify_that_updating_the_other_support_does_not_affect_the_monthly_payment_on_customer_contract_tab());
		
	}
	
	
	@Test(priority = 6,dataProvider="testData1", dependsOnMethods = { "T5_verify_that_adding_other_support_does_not_affect_the_monthly_payment_on_customer_contract_tab_test" })
	public void T6_verify_that_adding_discount_does_not_affect_the_monthly_payment_on_customer_contract_tab_test(String vehicle_percentage_discount , String additional_discount_vehicle ,
            String options_percentage_discount , String additional_discount_options , 
            String paint_percentage_discount ,   String additional_discount_paint ,
            String rebate ,                      String marketing_bonus , String manufacturer_delivery_charges, 
            String basic_price,                  String otr_value ,       String  other_support,
            String quoteRef,                     String expiryDate,       String term,            
            String milesPerAnnum,                String cashDeposit,      String documentFee, 
            String monthlyPayment,               String monthlyMaintenance,        String finalBallonPayment,
			String optionToPurchaseFee,          String pencePerExcessMileFinance, String pencePerExcessMileMaintenance) throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_customer_contract = new CustomerContractPage();
		
		assertTrue(obj_customer_contract.verify_that_adding_the_discounts_does_not_affect_the_monthly_payment_on_customer_contract_tab(vehicle_percentage_discount , additional_discount_vehicle ,
                options_percentage_discount , additional_discount_options , 
                paint_percentage_discount ,   additional_discount_paint ,
                rebate ,                      marketing_bonus ,
                manufacturer_delivery_charges));
		
	}
	
	@Test(priority = 7, dependsOnMethods = { "T6_verify_that_adding_discount_does_not_affect_the_monthly_payment_on_customer_contract_tab_test" })
	public void T7_verify_that_increasing_the_otr_price_does_not_affect_the_monthly_payment_on_customer_contract_tab_test() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_customer_contract = new CustomerContractPage();
		
		assertTrue(obj_customer_contract.verify_that_increasing_the_OTR_price_does_not_affect_the_monthly_payment_on_customer_contract_tab());
		
	}
	
	
	@Test(priority = 8, dependsOnMethods = { "T7_verify_that_increasing_the_otr_price_does_not_affect_the_monthly_payment_on_customer_contract_tab_test" })
	public void T8_verify_that_decreasing_the_otr_price_does_not_affect_the_monthly_payment_on_customer_contract_tab_test() throws IOException, InterruptedException, AWTException {
		
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		obj_customer_contract = new CustomerContractPage();
		
		assertTrue(obj_customer_contract.verify_that_decreasing_the_OTR_price_does_not_affect_the_monthly_payment_on_customer_contract_tab());
		
	}


	@Test(priority = 9, dataProvider="testData1", dependsOnMethods = { "T8_verify_that_decreasing_the_otr_price_does_not_affect_the_monthly_payment_on_customer_contract_tab_test" })
	public void T9_verify_adding_the_ownbookside_funder_with_diff_term_and_mileage_with_maint_does_not_affect_the_monthly_payment_with_customer_flow_test(String vehicle_percentage_discount , String additional_discount_vehicle ,
            String options_percentage_discount , String additional_discount_options , 
            String paint_percentage_discount ,   String additional_discount_paint ,
            String rebate ,                      String marketing_bonus , String manufacturer_delivery_charges, 
            String basic_price,                  String otr_value ,       String  other_support,
            String quoteRef,                     String expiryDate,       String term,            
            String milesPerAnnum,                String cashDeposit,      String documentFee, 
            String monthlyPayment,               String monthlyMaintenance,        String finalBallonPayment,
			String optionToPurchaseFee,          String pencePerExcessMileFinance, String pencePerExcessMileMaintenance) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException, NoSuchMethodException, SecurityException {
		
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_adding_the_ownbookside_funder_with_diff_term_and_mileage_with_maint_does_not_affect_the_monthly_payment_with_customer_flow(quoteRef, expiryDate, term,
				milesPerAnnum, cashDeposit, documentFee, monthlyPayment, finalBallonPayment,
				optionToPurchaseFee, monthlyMaintenance, pencePerExcessMileFinance, pencePerExcessMileMaintenance));	
	
	}
	
	@Test(priority = 10, dataProvider="testData1", dependsOnMethods = { "T9_verify_adding_the_ownbookside_funder_with_diff_term_and_mileage_with_maint_does_not_affect_the_monthly_payment_with_customer_flow_test" })
	public void T10_verify_adding_the_ownbookside_funder_with_diff_term_and_mileage_without_maint_does_not_affect_the_monthly_payment_with_customer_flow_test(String vehicle_percentage_discount , String additional_discount_vehicle ,
            String options_percentage_discount , String additional_discount_options , 
            String paint_percentage_discount ,   String additional_discount_paint ,
            String rebate ,                      String marketing_bonus , String manufacturer_delivery_charges, 
            String basic_price,                  String otr_value ,       String  other_support,
            String quoteRef,                     String expiryDate,       String term,            
            String milesPerAnnum,                String cashDeposit,      String documentFee, 
            String monthlyPayment,               String monthlyMaintenance,        String finalBallonPayment,
			String optionToPurchaseFee,          String pencePerExcessMileFinance, String pencePerExcessMileMaintenance) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException, NoSuchMethodException, SecurityException {
		
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_adding_the_ownbookside_funder_with_diff_term_and_mileage_without_maint_does_not_affect_the_monthly_payment_with_customer_flow(quoteRef, expiryDate, term,
				milesPerAnnum, cashDeposit, documentFee, monthlyPayment, finalBallonPayment,
				optionToPurchaseFee, monthlyMaintenance, pencePerExcessMileFinance, pencePerExcessMileMaintenance));	
	
	}
	
	
	@Test(priority = 11, dataProvider="testData1", dependsOnMethods = { "T10_verify_adding_the_ownbookside_funder_with_diff_term_and_mileage_without_maint_does_not_affect_the_monthly_payment_with_customer_flow_test" })
	public void T11_verify_adding_the_ownbookside_funder_with_same_term_and_mileage_without_maint_does_not_affect_the_monthly_payment_with_customer_flow_test(String vehicle_percentage_discount , String additional_discount_vehicle ,
            String options_percentage_discount , String additional_discount_options , 
            String paint_percentage_discount ,   String additional_discount_paint ,
            String rebate ,                      String marketing_bonus , String manufacturer_delivery_charges, 
            String basic_price,                  String otr_value ,       String  other_support,
            String quoteRef,                     String expiryDate,       String term,            
            String milesPerAnnum,                String cashDeposit,      String documentFee, 
            String monthlyPayment,               String monthlyMaintenance,        String finalBallonPayment,
			String optionToPurchaseFee,          String pencePerExcessMileFinance, String pencePerExcessMileMaintenance) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException, NoSuchMethodException, SecurityException {
		
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_adding_the_ownbookside_funder_with_same_term_and_mileage_without_maint_does_not_affect_the_monthly_payment_with_customer_flow(quoteRef, expiryDate, term,
				milesPerAnnum, cashDeposit, documentFee, monthlyPayment, finalBallonPayment,
				optionToPurchaseFee, monthlyMaintenance, pencePerExcessMileFinance, pencePerExcessMileMaintenance));	
	
	}
	
	@Test(priority = 12, dataProvider="testData1", dependsOnMethods = { "T11_verify_adding_the_ownbookside_funder_with_same_term_and_mileage_without_maint_does_not_affect_the_monthly_payment_with_customer_flow_test" })
	public void T12_verify_adding_the_ownbookside_funder_with_same_term_and_mileage_with_maint_does_not_affect_the_monthly_payment_with_customer_flow_test(String vehicle_percentage_discount , String additional_discount_vehicle ,
            String options_percentage_discount , String additional_discount_options , 
            String paint_percentage_discount ,   String additional_discount_paint ,
            String rebate ,                      String marketing_bonus , String manufacturer_delivery_charges, 
            String basic_price,                  String otr_value ,       String  other_support,
            String quoteRef,                     String expiryDate,       String term,            
            String milesPerAnnum,                String cashDeposit,      String documentFee, 
            String monthlyPayment,               String monthlyMaintenance,        String finalBallonPayment,
			String optionToPurchaseFee,          String pencePerExcessMileFinance, String pencePerExcessMileMaintenance) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException, NoSuchMethodException, SecurityException {
		
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_adding_the_ownbookside_funder_with_same_term_and_mileage_with_maint_does_not_affect_the_monthly_payment_with_customer_flow(quoteRef, expiryDate, term,
				milesPerAnnum, cashDeposit, documentFee, monthlyPayment, finalBallonPayment,
				optionToPurchaseFee, monthlyMaintenance, pencePerExcessMileFinance, pencePerExcessMileMaintenance));	
	
	}

	
	@Test(priority = 13, dataProvider="testData1", dependsOnMethods = { "T12_verify_adding_the_ownbookside_funder_with_same_term_and_mileage_with_maint_does_not_affect_the_monthly_payment_with_customer_flow_test" })
	public void T13_verify_adding_the_funder_side_funder_with_same_term_and_mileage_without_maint_does_not_affect_the_monthly_payment_with_customer_flow_test(String vehicle_percentage_discount , String additional_discount_vehicle ,
            String options_percentage_discount , String additional_discount_options , 
            String paint_percentage_discount ,   String additional_discount_paint ,
            String rebate ,                      String marketing_bonus , String manufacturer_delivery_charges, 
            String basic_price,                  String otr_value ,       String  other_support,
            String quoteRef,                     String expiryDate,       String term,            
            String milesPerAnnum,                String cashDeposit,      String documentFee, 
            String monthlyPayment,               String monthlyMaintenance,        String finalBallonPayment,
			String optionToPurchaseFee,          String pencePerExcessMileFinance, String pencePerExcessMileMaintenance) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException, NoSuchMethodException, SecurityException {
		
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_adding_the_funder_side_funder_with_same_term_and_mileage_without_maint_does_not_affect_the_monthly_payment_with_customer_flow(quoteRef, expiryDate, term,
				milesPerAnnum, cashDeposit, documentFee, monthlyPayment, finalBallonPayment,
				optionToPurchaseFee, monthlyMaintenance, pencePerExcessMileFinance, pencePerExcessMileMaintenance));	
	
	}
	
	@Test(priority = 14, dataProvider="testData1", dependsOnMethods = { "T13_verify_adding_the_funder_side_funder_with_same_term_and_mileage_without_maint_does_not_affect_the_monthly_payment_with_customer_flow_test" })
	public void T14_verify_adding_the_funder_side_funder_with_same_term_and_mileage_with_maint_does_not_affect_the_monthly_payment_with_customer_flow_test(String vehicle_percentage_discount , String additional_discount_vehicle ,
            String options_percentage_discount , String additional_discount_options , 
            String paint_percentage_discount ,   String additional_discount_paint ,
            String rebate ,                      String marketing_bonus , String manufacturer_delivery_charges, 
            String basic_price,                  String otr_value ,       String  other_support,
            String quoteRef,                     String expiryDate,       String term,            
            String milesPerAnnum,                String cashDeposit,      String documentFee, 
            String monthlyPayment,               String monthlyMaintenance,        String finalBallonPayment,
			String optionToPurchaseFee,          String pencePerExcessMileFinance, String pencePerExcessMileMaintenance) throws IOException, InterruptedException, AWTException, ClassNotFoundException, UnsupportedFlavorException, NoSuchMethodException, SecurityException {
		
	
	     System.out.println("");

	     System.out.println("Running the Test : " + Thread.currentThread().getStackTrace()[1].getMethodName());

		obj_asset_funding = new AssetFundingPage();		
		
		Assert.assertTrue(obj_asset_funding.verify_adding_the_funder_side_funder_with_same_term_and_mileage_with_maint_does_not_affect_the_monthly_payment_with_customer_flow(quoteRef, expiryDate, term,
				milesPerAnnum, cashDeposit, documentFee, monthlyPayment, finalBallonPayment,
				optionToPurchaseFee, monthlyMaintenance, pencePerExcessMileFinance, pencePerExcessMileMaintenance));	
	
	}


	


	
	
	@DataProvider(name = "testData1")
	public Object[][] getTestData1() throws IOException {
		Object[][] data = ReadExcelData.getTestData("CP_CP_funder");
		return data;
	}
}
