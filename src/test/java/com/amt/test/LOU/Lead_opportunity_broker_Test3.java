package com.amt.test.LOU;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.Leads;
import com.amt.pages.Opportunities;
import com.amt.pages.Proposal;
import com.amt.testBase.TestBase;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Lead_opportunity_broker_Test3 extends TestBase {



	
    Leads obj_Leads_Page;
    Opportunities obj_Opportunities_Page;
    Proposal obj_Proposal_Page;
    
    
   


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
		
		 obj_Leads_Page.lead_map_new_quote_broker_business_save_and_Convert();
		 
			
		 String GetOpportunityid = obj_Leads_Page.lead_map_new_quote_broker_business_getting_the_opportunityno();
		 Thread.sleep(5000);
		 
		 // Opportunity flow
		 
		obj_Opportunities_Page = new Opportunities();
		Thread.sleep(1000);
		obj_Opportunities_Page.opp_menu_link();
		
		obj_Proposal_Page = new Proposal();
		// Opportunity listing screen - Proposal status  
		obj_Proposal_Page.Opp_listing_proposal_status();
		
		obj_Opportunities_Page.opp_search_textbox(GetOpportunityid);
		
		obj_Opportunities_Page.opp_listing_detail_page();
		
		
		obj_Opportunities_Page.opp_listing_detail_update_indiviual();
		//obj_Opportunities_Page.opp_opp_fact_find_Find();
		
		
		//Proposal page for adding data in opportunity -  Customer info, Additional info , Bank detail
		
		
		//obj_Proposal_Page.proposal_Add_Customer_info();
		
		
		obj_Proposal_Page.proposal_Add_Individual_info();
		
		obj_Proposal_Page.Opp_listing_proposal_fill_form_manually();
	
		
		// Opportunity listing screen - Proposal status  
		obj_Proposal_Page.Opp_listing_proposal_status();
		 
		// Send Contract flow 
		
		obj_Opportunities_Page.opp_find_channel_status();
		
		obj_Opportunities_Page.opp_find_send_contract_icon();
		
		obj_Opportunities_Page.opp_find_channel_status();
		 
		 
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
		obj_Opportunities_Page.opp_menu_link();
		
		obj_Proposal_Page = new Proposal();
		// Opportunity listing screen - Proposal status  
		obj_Proposal_Page.Opp_listing_proposal_status();
		
		obj_Opportunities_Page.opp_search_textbox(GetOpportunityid);
		
		obj_Opportunities_Page.opp_listing_detail_page();
		
		obj_Opportunities_Page.opp_opp_fact_find();
		
		
		//Proposal page for adding data in opportunity -  Customer info, Additional info , Bank detail
		
		
		obj_Proposal_Page.proposal_Add_Customer_info();
		
		obj_Proposal_Page.Opp_listing_proposal_fill_form_manually();
	
		
		// Opportunity listing screen - Proposal status  
		obj_Proposal_Page.Opp_listing_proposal_status();
		
		
		// Send Contract flow 
		
		obj_Opportunities_Page.opp_find_channel_status();
		
		obj_Opportunities_Page.opp_find_send_contract_icon();
		
		obj_Opportunities_Page.opp_find_channel_status();
		
		
		
		
	
		
		
		
		 
		 
	}
	
	
	@Test(priority=3)
	public void ownbook_create_lead_individual() throws Exception 
	{

		 obj_Leads_Page = new  Leads();
		 
		 obj_Leads_Page.lead_General_info();
		 obj_Leads_Page.lead_Customer_info_individual();
		  obj_Leads_Page.lead_vehicle_request_ownbook();
		  obj_Leads_Page.lead_map_new_quote_ownbook_individual();
		  
		  
		  
		  // Individual = Broker + PCH
			 
			// obj_Leads_Page.lead_map_new_quote_broker_individual();
			
			 obj_Leads_Page.lead_map_new_quote_broker_business_save_and_Convert();
			 
				
			 String GetOpportunityid = obj_Leads_Page.lead_map_new_quote_broker_business_getting_the_opportunityno();
			 Thread.sleep(5000);
			 
			 // Opportunity flow
			 
			obj_Opportunities_Page = new Opportunities();
			Thread.sleep(1000);
			obj_Opportunities_Page.opp_menu_link();
			
			obj_Proposal_Page = new Proposal();
			// Opportunity listing screen - Proposal status  
			obj_Proposal_Page.Opp_listing_proposal_status();
			
			obj_Opportunities_Page.opp_search_textbox(GetOpportunityid);
			
			obj_Opportunities_Page.opp_listing_detail_page();
			
			
			obj_Opportunities_Page.opp_listing_detail_update_indiviual();
			//obj_Opportunities_Page.opp_opp_fact_find_Find();
			
			
			//Proposal page for adding data in opportunity -  Customer info, Additional info , Bank detail
			
			
			//obj_Proposal_Page.proposal_Add_Customer_info();
			
			
			obj_Proposal_Page.proposal_Add_Individual_info();
			
			obj_Proposal_Page.Opp_listing_proposal_fill_form_manually();
		
			
			// Opportunity listing screen - Proposal status  
			obj_Proposal_Page.Opp_listing_proposal_status();
			
			// Send Contract flow 
			
			obj_Opportunities_Page.opp_find_channel_status();
			
			obj_Opportunities_Page.opp_find_send_contract_icon();
			
			obj_Opportunities_Page.opp_find_channel_status();
			
			
			
			
			
			
			 
		  
		  
		  
		  
		  
		  
		  
		  
		  		
	}
	
	
	@Test(priority=4)
	public void ownbook_create_lead_business() throws Exception {

		 obj_Leads_Page = new  Leads();
		 obj_Leads_Page.lead_General_info();
		 obj_Leads_Page.lead_Customer_info_business();
		 obj_Leads_Page.lead_vehicle_request_ownbook();
		 
		 
		 //Business = HPNR + BCH
		 obj_Leads_Page.lead_map_new_quote_owbook_business();
		
		 obj_Leads_Page.lead_map_new_quote_broker_business_save_and_Convert();
		
		String GetOpportunityid = obj_Leads_Page.lead_map_new_quote_broker_business_getting_the_opportunityno();
		Thread.sleep(5000);
		 
		 // Opportunity flow
		 
		obj_Opportunities_Page = new Opportunities();
		Thread.sleep(1000);
		obj_Opportunities_Page.opp_menu_link();
		
		obj_Proposal_Page = new Proposal();
		// Opportunity listing screen - Proposal status  
		obj_Proposal_Page.Opp_listing_proposal_status();
		
		obj_Opportunities_Page.opp_search_textbox(GetOpportunityid);
		
		obj_Opportunities_Page.opp_listing_detail_page();
		
		obj_Opportunities_Page.opp_opp_fact_find();
		
		
		//Proposal page for adding data in opportunity -  Customer info, Additional info , Bank detail
		
		
		obj_Proposal_Page.proposal_Add_Customer_info();
		
		obj_Proposal_Page.Opp_listing_proposal_fill_form_manually();
	
		
		// Opportunity listing screen - Proposal status  
		obj_Proposal_Page.Opp_listing_proposal_status();
		
		// Send Contract flow 
		
					obj_Opportunities_Page.opp_find_channel_status();
					
					obj_Opportunities_Page.opp_find_send_contract_icon();
					
					obj_Opportunities_Page.opp_find_channel_status();
					
					obj_Opportunities_Page.opp_verify_channel_data();
		 
		 
	}
	
	
	
	
	@Test(priority=5)
	public void ownbook_getting_opportunity_listing() throws Exception {

		/*
		 * obj_Leads_Page = new Leads(); obj_Leads_Page.lead_General_info();
		 * obj_Leads_Page.lead_Customer_info_business();
		 * obj_Leads_Page.lead_vehicle_request_ownbook();
		 * 
		 * 
		 * //Business = HPNR + BCH obj_Leads_Page.lead_map_new_quote_owbook_business();
		 * 
		 * obj_Leads_Page.lead_map_new_quote_broker_business_save_and_Convert();
		 * 
		 * String GetOpportunityid =
		 * obj_Leads_Page.lead_map_new_quote_broker_business_getting_the_opportunityno()
		 * ; Thread.sleep(5000);
		 */
		 
		 // Opportunity flow
		 
		obj_Opportunities_Page = new Opportunities();
		Thread.sleep(1000);
		obj_Opportunities_Page.opp_menu_link();
		
	
		
		obj_Opportunities_Page.opp_verify_channel_data();
		
		/*
		 * obj_Proposal_Page = new Proposal(); // Opportunity listing screen - Proposal
		 * status obj_Proposal_Page.Opp_listing_proposal_status();
		 * 
		 * obj_Opportunities_Page.opp_Search_Textbox(GetOpportunityid);
		 * 
		 * obj_Opportunities_Page.opp_Listing_detail_page();
		 * 
		 * obj_Opportunities_Page.opp_opp_fact_find_Find();
		 * 
		 * 
		 * //Proposal page for adding data in opportunity - Customer info, Additional
		 * info , Bank detail
		 * 
		 * 
		 * obj_Proposal_Page.proposal_Add_Customer_info();
		 * 
		 * obj_Proposal_Page.Opp_listing_proposal_fill_form_manually();
		 * 
		 * 
		 * // Opportunity listing screen - Proposal status
		 * obj_Proposal_Page.Opp_listing_proposal_status();
		 * 
		 * // Send Contract flow
		 * 
		 * obj_Opportunities_Page.opp_Find_Channel_Status();
		 * 
		 * obj_Opportunities_Page.opp_Find_Send_Contract_icon();
		 * 
		 * obj_Opportunities_Page.opp_Find_Channel_Status();
		 * 
		 * obj_Opportunities_Page.opp_Verify_Channel_data();
		 */
		 
		 
	}
	
	
	
	
}
