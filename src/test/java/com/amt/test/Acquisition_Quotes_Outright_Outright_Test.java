package com.amt.test;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePageOutrightOutrightPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.AcquisitionOutrightPage.AcquisitionQuotesOutrightOutrightPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_Outright_Outright_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;


@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_Outright_Outright_Test extends TestBase {
	
	public AcquisitionQuotesOutrightOutrightPage obj_aquisition_quotes_page_outright_outright;
	
	LoginPage obj_Login_Page;
	
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage   obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_Outright_Outright_Page obj_contract_types_and_OTR_Outright_Outrightpage;
	CustomerQuotePageOutrightOutrightPage obj_customer_quote_page;
	
	 	 
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		initialization(prop.getProperty("browser"));
		obj_Login_Page = new LoginPage();	
		obj_Login_Page.enter_credentials();
	}
	
	@AfterMethod public void tearDown() { driver.close(); }
	
		
	@Test(priority=0, dataProvider="testData")
	public void aquisition_quotes_user_outright_outright_test(String manufacturer, String model) throws InterruptedException {
		
		
		
		obj_acq_listing_page=new AcquisitionListingPage();
		obj_vehicle_selection_page=new VehicleSelectionPage();
		obj_options_accessories=new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_Outright_Outrightpage = new ContractTypesAndOTR_Outright_Outright_Page();
		obj_customer_quote_page =new CustomerQuotePageOutrightOutrightPage();
		
		
		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		obj_contract_types_and_OTR_Outright_Outrightpage.contractTypes_and_OTR_selection_outright_outright();
		obj_customer_quote_page.customer_Quote_outright_outright();
	}
	
	
	
	@DataProvider(name="testData")
	public Object[][] getTestData() throws IOException {		
		Object[][] data=ReadExcelData.getTestData("audiOutrightOutRight");
		return data;	
		
	}

}
