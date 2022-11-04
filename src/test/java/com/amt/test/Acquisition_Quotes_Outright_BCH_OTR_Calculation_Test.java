package com.amt.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePageOutrightBCHPage;
import com.amt.HoldingCostPages.HoldingCostOutrightBCHPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.AcquisitionBrokerPages.AcquisitionQuotesBrokerBCHPage;
import com.amt.pages.AcquisitionBrokerPages.AcquisitionQuotesBrokerHPRPage;
import com.amt.pages.AcquisitionOutrightPage.AcquisitionQuoteOutrightBCHownbookCalculationPage;
import com.amt.pages.AcquisitionOutrightPage.AcquisitionQuotesOutrightOutrightPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_Outright_BCH_Ownbook_CalculationPage;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelCalculation;
import com.amt.testUtil.ReadExcelData;


@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_Outright_BCH_OTR_Calculation_Test extends TestBase {
	
	AcquisitionQuoteOutrightBCHownbookCalculationPage obj_aquisition_quotes_outright_BCH_ownbookCalculationPage;
	
	LoginPage obj_Login_Page;
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage   obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_Outright_BCH_Ownbook_CalculationPage obj_contract_types_and_OTR_Outright_BCH_page;
	HoldingCostOutrightBCHPage obj_holding_cost_Outright_BCH_page;
	CustomerQuotePageOutrightBCHPage obj_customer_quote_page;
	
	
	 	 
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		initialization(prop.getProperty("browser"));
		obj_Login_Page = new LoginPage();	
		obj_Login_Page.enter_credentials();
	}
	
	//@AfterMethod public void tearDown() { driver.close(); }
	
		
	@Test(priority=0, dataProvider="testData")
	public void aquisition_quotes_outright_BCH_OTR_Calcution_test(String manufacturer, String model, String sheet_name) throws InterruptedException, IOException {
		
		
		obj_acq_listing_page=new AcquisitionListingPage();
		obj_vehicle_selection_page=new VehicleSelectionPage();
		obj_options_accessories=new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_Outright_BCH_page = new ContractTypesAndOTR_Outright_BCH_Ownbook_CalculationPage();
		obj_customer_quote_page =new CustomerQuotePageOutrightBCHPage();
		obj_holding_cost_Outright_BCH_page=new HoldingCostOutrightBCHPage();
		
		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		boolean subtotal_after_discount=obj_contract_types_and_OTR_Outright_BCH_page.contractTypes_and_OTR_selection_outright_BCH_Ownbook_calculation(sheet_name);
		Assert.assertTrue(subtotal_after_discount);
		boolean otr_price_check=obj_contract_types_and_OTR_Outright_BCH_page.verify_after_discount_calculations_contract_types_page(sheet_name);
		Assert.assertTrue(otr_price_check);
		boolean quote_summary_after_saving= obj_customer_quote_page.customer_Quote_outright_BCH_OTR_calculation();				
		Assert.assertTrue(quote_summary_after_saving); 
	}
	
	@DataProvider(name="testData")
	public Object[][] getTestData() throws IOException {		
		Object[][] data=ReadExcelData.getTestData("audiOutrightOutRight");
		return data;	
		}

}
