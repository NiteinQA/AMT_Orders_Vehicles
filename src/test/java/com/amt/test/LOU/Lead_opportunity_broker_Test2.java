package com.amt.test.LOU;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.Leads;
import com.amt.pages.Opportunities;
import com.amt.testBase.TestBase;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Lead_opportunity_broker_Test2 extends TestBase {



	
    Leads obj_Leads_Page;
    Opportunities obj_Opportunities_Page;
    
   


	@Test()
	public void broker_create_lead_individual() throws Exception {

		 obj_Leads_Page = new  Leads();
		 
		 obj_Leads_Page.lead_General_info();
		 obj_Leads_Page.lead_Customer_info_individual();
		 obj_Leads_Page.lead_communication_log_Add_Note();
	
		 obj_Leads_Page.lead_communication_log_log_a_call();
		  obj_Leads_Page.lead_vehicle_request_broker();
		 
		 // Individual = Broker + PCH
		 
		 obj_Leads_Page.lead_map_new_quote_broker_individual();
		
		 
		 
			
	}
	
	
	

	@Test(priority=2)
	public void broker_create_lead_business( ) throws Exception {

		 obj_Leads_Page = new Leads();
		 
		 obj_Leads_Page.lead_General_info();
		 
		 obj_Leads_Page.lead_Customer_info_business();
		 
		 obj_Leads_Page.lead_vehicle_request_broker();
		 
		 //Business = Broker + BCH
		 obj_Leads_Page.lead_map_new_quote_broker_business();
		 
		 obj_Leads_Page.lead_map_new_quote_broker_business_save_and_Convert();
		
		String GetOpportunityid = obj_Leads_Page.lead_map_new_quote_broker_business_getting_the_opportunityno();
		 Thread.sleep(5000);
		 
		 // Opportunity flow
		 
		obj_Opportunities_Page = new Opportunities();
		Thread.sleep(1000);
		obj_Opportunities_Page.opp_Menu_link();
		
		
		obj_Opportunities_Page.opp_Search_Textbox(GetOpportunityid);
		
		obj_Opportunities_Page.opp_Listing_detail_page();
		
		obj_Opportunities_Page.opp_opp_fact_find_Find();
		
		//Proposal flow 
		
		
		//obj_Opportunities_Page.opp_opp_fact_find_Find();
		 
		 
	}
	
	
	@Test(priority=3)
	public void ownbook_create_lead_individual() throws Exception 
	{

		 obj_Leads_Page = new  Leads();
		 
		 obj_Leads_Page.lead_General_info();
		 obj_Leads_Page.lead_Customer_info_individual();
		  obj_Leads_Page.lead_vehicle_request_ownbook();
		  obj_Leads_Page.lead_map_new_quote_ownbook_individual();
		  		
	}
	
	
	@Test(priority=4)
	public void ownbook_create_lead_business() throws Exception {

		 obj_Leads_Page = new  Leads();
		 obj_Leads_Page.lead_General_info();
		 obj_Leads_Page.lead_Customer_info_business();
		 obj_Leads_Page.lead_vehicle_request_ownbook();
		 
		 obj_Leads_Page.lead_map_new_quote_owbook_business();
		 

		 
		 
	}
	
	
	
	
	
	
	
}
