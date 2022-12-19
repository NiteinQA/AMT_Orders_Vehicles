package com.amt.test.lou;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePage_BCH_BCH_Page;
import com.amt.HoldingCostPages.HoldingCost_BCH_BCH_Page;
import com.amt.QuoteSummaryPages.QuoteSummary_BCH_BCH_Page;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.Leads;
import com.amt.pages.LoginPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_BCH_BCH_Page;
import com.amt.test.Acquisition_Quotes_Broker_BCH_without_maintenance_Test;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Lead_opportunity_broker_Test extends TestBase {



	
    Leads obj_Leads_Page;
    Acquisition_Quotes_Broker_BCH_without_maintenance_Test obj_Broker_BCH_without_maintenance ;
    
    


	@Test(priority = 1)
	public void Lead_opportunity_broker_Test() throws InterruptedException, IOException, UnsupportedFlavorException {

		 obj_Leads_Page = new  Leads();
		 
		 obj_Leads_Page.add_new_lead("Broker");
	}
	
//	@Test(priority = 2)
//	public void Lead_opportunity_broker_Test() throws InterruptedException, IOException, UnsupportedFlavorException {
//
//		 obj_Leads_Page = new  Leads();
//		 
//		 obj_Leads_Page.add_new_lead("Broker");
//	}
	

	
	

//	@DataProvider(name = "testData")
//	public Object[][] getTestData() throws IOException {
//		Object[][] data = ReadExcelData.getTestData("BCH_BCH_withMaintenance");
//		return data;
//	}

}
