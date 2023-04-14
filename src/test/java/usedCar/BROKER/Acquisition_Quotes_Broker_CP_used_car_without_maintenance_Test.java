package usedCar.BROKER;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePageBrokerCPPage;
import com.amt.QuoteSummaryPages.QuoteSummaryBrokerCPPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_Broker_CP_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;


@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_Broker_CP_used_car_without_maintenance_Test extends TestBase {
	
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_Broker_CP_Page obj_contract_types_and_OTR_page;
	CustomerQuotePageBrokerCPPage obj_customer_quote_page;
	QuoteSummaryBrokerCPPage  obj_quote_summary_page;
	
	
		
	@Test(priority=1, dataProvider="testData")
	public void aquisition_quotes_user_flow_broker_CP_OTR_calculation_without_maintenance_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, String vehicle_profit, String sales_price_percentage,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String optionalFinalPayment, String optionToPurchaseFee, 
			String rflIncluded, String pensePerExcessMileFinance, String apr, String commission, 
			String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			
		
		 obj_acq_listing_page = new AcquisitionListingPage();
		 obj_vehicle_selection_page = new VehicleSelectionPage();
	     obj_options_accessories = new OptionsAccessoriesPage();
	     obj_contract_types_and_OTR_page = new ContractTypesAndOTR_Broker_CP_Page();
				
			obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
			obj_vehicle_selection_page.select_vehicle_for_used_car_flow(registrationNumber, mileage);
			obj_options_accessories.options_And_Accessories_selection_for_used_car();
			boolean cost_price_ex_vat_and_options_and_preparation_cost = obj_contract_types_and_OTR_page
					.contractTypes_selection_and_OTR_calculation(sheet_name);
			Assert.assertTrue(cost_price_ex_vat_and_options_and_preparation_cost);	
	}
	
	@Test(priority=2, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_user_flow_broker_CP_OTR_calculation_without_maintenance_test" })

	
	public void aquisition_quotes_used_car_edit_cost_price_and_check_OTR_with_maintenance_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, String vehicle_profit, String sales_price_percentage,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String optionalFinalPayment, String optionToPurchaseFee, 
			String rflIncluded, String pensePerExcessMileFinance, String apr, String commission, 
			String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			
	
		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_Broker_CP_Page();
			

	
	}
	
	@Test(priority=3, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_used_car_edit_cost_price_and_check_OTR_with_maintenance_test" })

	public void aquisition_quotes_user_flow_broker_CP_vehicle_profit_check_without_maintenance_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, String vehicle_profit, String sales_price_percentage,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String optionalFinalPayment, String optionToPurchaseFee, 
			String rflIncluded, String pensePerExcessMileFinance, String apr, String commission, 
			String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			

		 obj_customer_quote_page = new CustomerQuotePageBrokerCPPage();
	
	
		
		boolean customer_quote_vehicle_profit_check=obj_customer_quote_page.customer_Quote_verification_broker_purchase_used_vehicle_without_maintenance(vehicle_profit,quoteRef,quoteExpiryDate, term, milesperannum,
				contractMileage,cahDeposit,noOfMonthlyPayments, monthlyFinancePayment, optionalFinalPayment, optionToPurchaseFee, 
			rflIncluded, pensePerExcessMileFinance,  apr,commission, partExchangeActual, partExchangeGiven, lessFinanceSettlemnet, sheet_name);	
		Assert.assertTrue(customer_quote_vehicle_profit_check);
		
		boolean monthly_total_payment_after_editing_vehicle_sales_price = obj_customer_quote_page.edit_otr_sales_price_and_verify_profit_broker_purchase_used_vehicle( sales_price_percentage, sheet_name);
		Assert.assertTrue(monthly_total_payment_after_editing_vehicle_sales_price);

		
	}
	
	@Test(priority=4, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_user_flow_broker_CP_vehicle_profit_check_without_maintenance_test" })

	public void aquisition_quotes_user_flow_broker_CP_balance_to_finance_check_without_maintenance_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, String vehicle_profit, String sales_price_percentage,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String optionalFinalPayment, String optionToPurchaseFee, 
			String rflIncluded, String pensePerExcessMileFinance, String apr, String commission, 
			String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			
	
		 obj_customer_quote_page = new CustomerQuotePageBrokerCPPage();
		
		boolean customer_quote_balance_to_finance_check=obj_customer_quote_page.customer_Quote_balance_to_finance_checking_broker_purchase_used_vehicle_without_maintenance(vehicle_profit,quoteRef,quoteExpiryDate, term, milesperannum,
				contractMileage,cahDeposit,noOfMonthlyPayments, monthlyFinancePayment, optionalFinalPayment, optionToPurchaseFee, 
			rflIncluded, pensePerExcessMileFinance,  apr,commission, partExchangeActual, partExchangeGiven, lessFinanceSettlemnet, sheet_name);	
		Assert.assertTrue(customer_quote_balance_to_finance_check);
		//this assertion is commented because this fails test as part exchange profit is not updated in runtime, when this will be solved then this assertion will be uncommented
				
	}
	
	@Test(priority=5, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_user_flow_broker_CP_balance_to_finance_check_without_maintenance_test" })

	public void aquisition_quotes_user_flow_broker_CP_quote_summary_values_verification_without_maintenance_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, String vehicle_profit, String sales_price_percentage,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String optionalFinalPayment, String optionToPurchaseFee, 
			String rflIncluded, String pensePerExcessMileFinance, String apr, String commission, 
			String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			

		 obj_quote_summary_page =new QuoteSummaryBrokerCPPage();		
		
		boolean quote_summary_page_status = obj_quote_summary_page.quote_summary_broker_purchase_for_used_vehicle(sheet_name);
		Assert.assertTrue(quote_summary_page_status);
		
	}
	
	
	
	@DataProvider(name="testData")
	public Object[][] getTestData() throws IOException {		
		Object[][] data=ReadExcelData.getTestData("BrokerCPwoMaint_Used_car");
		return data;
	}

}
