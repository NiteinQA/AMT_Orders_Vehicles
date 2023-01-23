package com.amt.test.LOU;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.Leads;
import com.amt.pages.Opportunities;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Lead_opportunity_broker_Test extends TestBase {



	
    Leads obj_Leads_Page;
    Opportunities obj_Opportunities_Page ; 
    
    


	@Test(priority = 1 , dataProvider="LeadOppoUnderwritingtestData")
	public void create_lead(String sheet_name , String customer_email_id, String password) throws InterruptedException, IOException, UnsupportedFlavorException {

		 obj_Leads_Page = new  Leads();
		 
		 String pageTitleAfterUpdatingOpportunity = obj_Leads_Page.add_new_lead(sheet_name);
		 
		Assert.assertEquals(pageTitleAfterUpdatingOpportunity, "Opportunity List"); 	 
		 
	}
	
	@Test(priority = 2, dataProvider="LeadOppoUnderwritingtestData",dependsOnMethods = {"create_lead"})
	public void search_and_verify_opportunity_in_opportunity_page(String sheet_name , String customer_email_id, String password) throws InterruptedException, IOException, UnsupportedFlavorException {

	  obj_Opportunities_Page = new Opportunities();
	  
	 boolean search_oppo = obj_Opportunities_Page.search_and_verify_opportunity(sheet_name);  		 
	 
	 Assert.assertTrue(search_oppo);	 
	}
	
	@Test(priority = 3, dataProvider="LeadOppoUnderwritingtestData",dependsOnMethods = {"search_and_verify_opportunity_in_opportunity_page"})
	public void verify_status_of_opportunity_in_opportunity_page(String sheet_name , String customer_email_id, String password) throws InterruptedException, IOException, UnsupportedFlavorException {

	  obj_Opportunities_Page = new Opportunities();
	  
	  boolean status =  obj_Opportunities_Page.verify_status_of_opportunity();
	  
	  Assert.assertTrue(status);  
	 
	}
	
	@Test(priority = 4, dataProvider="LeadOppoUnderwritingtestData",dependsOnMethods = {"verify_status_of_opportunity_in_opportunity_page"})
	public void verify_contract_is_copied(String sheet_name , String customer_email_id, String password) throws InterruptedException, IOException, UnsupportedFlavorException {

	  obj_Opportunities_Page = new Opportunities();
	  
	  boolean copy_contract = obj_Opportunities_Page.verify_contract_is_copied(sheet_name);
	  
	  Assert.assertTrue(copy_contract); 
	 
	}
	
	@Test(priority = 5, dataProvider="LeadOppoUnderwritingtestData",dependsOnMethods = {"verify_contract_is_copied"})
	public void verify_copied_contract_is_deleted(String sheet_name , String customer_email_id, String password) throws InterruptedException, IOException, UnsupportedFlavorException {

	  obj_Opportunities_Page = new Opportunities();
	  
	  boolean delete_contract = obj_Opportunities_Page.verify_copied_contract_is_deleted(sheet_name);
	  
	  Assert.assertTrue(delete_contract); 
	 
	}
	
	@Test(priority = 5, dataProvider="LeadOppoUnderwritingtestData",dependsOnMethods = {"verify_copied_contract_is_deleted"})
	public void verify_contract_is_sent_to_email_id(String sheet_name , String customer_email_id, String password) throws InterruptedException, IOException, UnsupportedFlavorException {

	  obj_Opportunities_Page = new Opportunities();
	  
	  obj_Opportunities_Page.verify_contract_is_sent_to_mail(sheet_name, customer_email_id);
	 	  
	}	
	
	@Test(priority = 5, dataProvider="LeadOppoUnderwritingtestData",dependsOnMethods = {"verify_contract_is_sent_to_email_id"})
	public void verify_contract_email_is_received(String sheet_name , String customer_email_id, String password) throws InterruptedException, IOException, UnsupportedFlavorException {

	  obj_Opportunities_Page = new Opportunities();
	  
	  obj_Opportunities_Page.verify_mail_is_received(sheet_name, customer_email_id, password);
	 	  
	}

	@DataProvider(name = "LeadOppoUnderwritingtestData")
	public Object[][] getTestData() throws IOException {
		Object[][] data = ReadExcelData.getTestDataForLeadOppoUnderwriting("BrokerPCH");
		return data;
	}

}
