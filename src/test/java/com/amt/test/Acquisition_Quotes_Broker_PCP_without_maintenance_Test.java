package com.amt.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePageBrokerBCHPage;
import com.amt.CustomerQuotePackage.CustomerQuotePageBrokerPCPPage;
import com.amt.QuoteSummaryPages.QuoteSummaryBrokerBCHPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.AcquisitionBrokerPages.AcquisitionQuotesBrokerBCHPage;
import com.amt.pages.AcquisitionBrokerPages.AcquisitionQuotesBrokerHPRPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_Broker_BCH_Page;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_Broker_PCP_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;


@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_Broker_PCP_without_maintenance_Test extends TestBase {
	
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_Broker_PCP_Page obj_contract_types_and_OTR_Broker_PCP;
	CustomerQuotePageBrokerPCPPage obj_customer_quote_page;
	QuoteSummaryBrokerBCHPage  obj_quote_summary_page;
	
	
		
	@Test(priority=0, dataProvider="testData")
	public void aquisition_quotes_user_flow_broker_PCP_without_maintenance_test(String manufacturer, String model,String vehicleProfit,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String optionalFinalPayment, String optionToPurchaseFee, 
			String rflIncluded, String pensePerExcessMileFinance, String apr, String commission, 
			String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException {
			
		
		 obj_acq_listing_page = new AcquisitionListingPage();
		 obj_vehicle_selection_page = new VehicleSelectionPage();
	     obj_options_accessories = new OptionsAccessoriesPage();
		 obj_contract_types_and_OTR_Broker_PCP = new ContractTypesAndOTR_Broker_PCP_Page();
		 obj_customer_quote_page = new CustomerQuotePageBrokerPCPPage();
		 obj_quote_summary_page =new QuoteSummaryBrokerBCHPage();
		
		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		boolean subtotal_after_discount=obj_contract_types_and_OTR_Broker_PCP.contractTypes_and_OTR_selection_broker_pcp(sheet_name);
		Assert.assertTrue(subtotal_after_discount);
		
		boolean otr_price_check = obj_contract_types_and_OTR_Broker_PCP
				.verify_after_discount_calculations_contract_types_page(sheet_name);
		Assert.assertTrue(otr_price_check);
		
		boolean customer_quote_check=obj_customer_quote_page.customer_Quote_broker_pcp_without_maintenance(vehicleProfit,quoteRef,quoteExpiryDate, term, milesperannum,
				contractMileage,cahDeposit,noOfMonthlyPayments, monthlyFinancePayment, optionalFinalPayment, optionToPurchaseFee, 
			rflIncluded, pensePerExcessMileFinance,  apr,commission, partExchangeActual, partExchangeGiven, lessFinanceSettlemnet, sheet_name);	
		Assert.assertTrue(customer_quote_check);
//		
//		boolean quote_summary_page_status = obj_quote_summary_page.quote_summary_broker_BCH_without_maintenance(sheet_name);
//		Assert.assertTrue(quote_summary_page_status);
		
	}
	
	
	
	@DataProvider(name="testData")
	public Object[][] getTestData() throws IOException {		
		Object[][] data=ReadExcelData.getTestData("BrokerPCPwithoutMaintenance");
		return data;	
		
	}

}
