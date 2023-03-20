package newCar.LOU;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.Leads;
import com.amt.pages.Opportunities;
import com.amt.pages.Proposal;
import com.amt.testBase.TestBase;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Lead_ownbook_individual_flow extends TestBase {



	
    Leads obj_Leads_Page;
    Opportunities obj_Opportunities_Page;
    Proposal obj_Proposal_Page;
    
    
   


	
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
	
	
	
	
	
	
	
	
	
	
}
