package com.amt.test;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePageBrokerHPRPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.AcquisitionBrokerPages.AcquisitionQuotesBrokerBCHPage;
import com.amt.pages.AcquisitionBrokerPages.AcquisitionQuotesBrokerHPRPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_Broker_HPR_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;


@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_Broker_HPR_Test extends TestBase {
	
	public AcquisitionQuotesBrokerBCHPage obj_aquisition_quotes_page_broker_bch;
	public AcquisitionQuotesBrokerHPRPage obj_aquisition_quotes_page_broker_hpr;
	LoginPage obj_Login_Page;
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_Broker_HPR_Page obj_contract_types_and_OTR_Broker_HPR_Page; 	 
	CustomerQuotePageBrokerHPRPage obj_customer_quote_page;
	
	
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		initialization(prop.getProperty("browser"));
		obj_Login_Page = new LoginPage();	
		obj_Login_Page.enter_credentials();
	}
	
	@AfterMethod public void tearDown() { driver.close(); }
	
	
	
	@Test(priority=1, dataProvider="testData")
	public void aquisition_quotes_user_flow_broker_hpr_test(String manufacturer, String model, String quoteRef, 
			                                                String quoteExpiryDate,String term,String milesperannum,
			                                                String cashdeposite,String financecharges,
			                                                String monthlyfinancepayments,
			                                                String finalballoonpayment, String optiontopurchasefee, String apr, 
			                                                String commissionvalue) throws InterruptedException {
		
		obj_acq_listing_page = new AcquisitionListingPage();
		obj_vehicle_selection_page = new VehicleSelectionPage();
	    obj_options_accessories = new OptionsAccessoriesPage();
	    obj_contract_types_and_OTR_Broker_HPR_Page = new ContractTypesAndOTR_Broker_HPR_Page();
	    obj_customer_quote_page = new CustomerQuotePageBrokerHPRPage();		
		
		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		obj_contract_types_and_OTR_Broker_HPR_Page.contractTypes_and_OTR_selection_broker_hpr();
		obj_customer_quote_page.customer_Quote_broker_hpr( manufacturer,  model,  quoteRef, 
                quoteExpiryDate, term, milesperannum,
                cashdeposite, financecharges,
                monthlyfinancepayments,
                finalballoonpayment,  optiontopurchasefee,  apr, 
                commissionvalue);
	}
	
	
	
	@DataProvider(name="testData")
	public Object[][] getTestData() throws IOException {		
		Object[][] data=ReadExcelData.getTestData("audibrokerHPR");
		return data;	
		
	}

}
