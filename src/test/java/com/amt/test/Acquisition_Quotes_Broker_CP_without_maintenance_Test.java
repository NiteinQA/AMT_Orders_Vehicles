package com.amt.test;

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
public class Acquisition_Quotes_Broker_CP_without_maintenance_Test extends TestBase {
	
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_Broker_CP_Page obj_contract_types_and_OTR_page;
	CustomerQuotePageBrokerCPPage obj_customer_quote_page;
	QuoteSummaryBrokerCPPage  obj_quote_summary_page;
	
	
		
	@Test(priority=1, dataProvider="testData")
	public void aquisition_quotes_user_flow_broker_CP_OTR_calculation_without_maintenance_test(String manufacturer, String model,String vehicleProfit,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String optionalFinalPayment, String optionToPurchaseFee, 
			String rflIncluded, String pensePerExcessMileFinance, String apr, String commission, 
			String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			
		
		 obj_acq_listing_page = new AcquisitionListingPage();
		 obj_vehicle_selection_page = new VehicleSelectionPage();
	     obj_options_accessories = new OptionsAccessoriesPage();
	     obj_contract_types_and_OTR_page = new ContractTypesAndOTR_Broker_CP_Page();
				
		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		boolean subtotal_after_discount=obj_contract_types_and_OTR_page.contractTypes_and_OTR_selection_broker_cp(sheet_name);
		Assert.assertTrue(subtotal_after_discount);	
	}
	
	@Test(priority=2, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_user_flow_broker_CP_OTR_calculation_without_maintenance_test" })

	
	public void aquisition_quotes_user_flow_broker_CP_after_discount_calculations_without_maintenance_test(String manufacturer, String model,String vehicleProfit,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String optionalFinalPayment, String optionToPurchaseFee, 
			String rflIncluded, String pensePerExcessMileFinance, String apr, String commission, 
			String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			
	
		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_Broker_CP_Page();
			
		boolean otr_price_check = obj_contract_types_and_OTR_page
				.verify_after_discount_calculations_contract_types_page(sheet_name);
		Assert.assertTrue(otr_price_check);
	
	}
	
	@Test(priority=3, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_user_flow_broker_CP_after_discount_calculations_without_maintenance_test" })

	public void aquisition_quotes_user_flow_broker_CP_vehicle_profit_check_without_maintenance_test(String manufacturer, String model,String vehicleProfit,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String optionalFinalPayment, String optionToPurchaseFee, 
			String rflIncluded, String pensePerExcessMileFinance, String apr, String commission, 
			String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			

		 obj_customer_quote_page = new CustomerQuotePageBrokerCPPage();
	
	
		
		boolean customer_quote_vehicle_profit_check=obj_customer_quote_page.customer_Quote_vehicle_profit_checking_broker_cp_without_maintenance(vehicleProfit,quoteRef,quoteExpiryDate, term, milesperannum,
				contractMileage,cahDeposit,noOfMonthlyPayments, monthlyFinancePayment, optionalFinalPayment, optionToPurchaseFee, 
			rflIncluded, pensePerExcessMileFinance,  apr,commission, partExchangeActual, partExchangeGiven, lessFinanceSettlemnet, sheet_name);	
		Assert.assertTrue(customer_quote_vehicle_profit_check);
		
	}
	
	@Test(priority=4, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_user_flow_broker_CP_vehicle_profit_check_without_maintenance_test" })

	public void aquisition_quotes_user_flow_broker_CP_balance_to_finance_check_without_maintenance_test(String manufacturer, String model,String vehicleProfit,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String optionalFinalPayment, String optionToPurchaseFee, 
			String rflIncluded, String pensePerExcessMileFinance, String apr, String commission, 
			String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			
	
		 obj_customer_quote_page = new CustomerQuotePageBrokerCPPage();
		
		boolean customer_quote_balance_to_finance_check=obj_customer_quote_page.customer_Quote_balance_to_finance_checking_broker_cp_without_maintenance(vehicleProfit,quoteRef,quoteExpiryDate, term, milesperannum,
				contractMileage,cahDeposit,noOfMonthlyPayments, monthlyFinancePayment, optionalFinalPayment, optionToPurchaseFee, 
			rflIncluded, pensePerExcessMileFinance,  apr,commission, partExchangeActual, partExchangeGiven, lessFinanceSettlemnet, sheet_name);	
		Assert.assertTrue(customer_quote_balance_to_finance_check);
		//this assertion is commented because this fails test as part exchange profit is not updated in runtime, when this will be solved then this assertion will be uncommented
				
	}
	
	@Test(priority=5, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_user_flow_broker_CP_balance_to_finance_check_without_maintenance_test" })

	public void aquisition_quotes_user_flow_broker_CP_quote_summary_values_verification_without_maintenance_test(String manufacturer, String model,String vehicleProfit,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String optionalFinalPayment, String optionToPurchaseFee, 
			String rflIncluded, String pensePerExcessMileFinance, String apr, String commission, 
			String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			

		 obj_quote_summary_page =new QuoteSummaryBrokerCPPage();		
		
		boolean quote_summary_page_status = obj_quote_summary_page.quote_summary_broker_CP_without_maintenance(sheet_name);
		Assert.assertTrue(quote_summary_page_status);
		
	}
	
	
	
	@DataProvider(name="testData")
	public Object[][] getTestData() throws IOException {		
		Object[][] data=ReadExcelData.getTestData("BrokerPCPwithoutMaintenance");
		return data;
	}

}
