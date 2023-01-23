package com.amt.test.LOU;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.Leads;
import com.amt.pages.Opportunities;
import com.amt.pages.Proposal;
import com.amt.pages.UnderwritingPopupPage;
import com.amt.testBase.TestBase;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Underwrting_broker_business_flow extends TestBase {



	
    Leads obj_Leads_Page;
    Opportunities obj_Opportunities_Page;
    Proposal obj_Proposal_Page;
    UnderwritingPopupPage obj_UnderwritingPopupPage_Page;
    
   


	
	
	@Test( priority=1)
	public void broker_create_opportunity_business() throws Exception {
	
	
	
		 // Opportunity flow
		 
		obj_Opportunities_Page = new Opportunities();
		Thread.sleep(1000);
		obj_Opportunities_Page.opp_menu_link();
		
		
		obj_Proposal_Page = new Proposal();
		
		obj_UnderwritingPopupPage_Page  = new UnderwritingPopupPage();
		
		
		
		
//obj_Opportunities_Page.opp_search_textbox("KM-2823");
		
obj_UnderwritingPopupPage_Page.search_and_verify_underwriting_icon_is_availabale();

obj_UnderwritingPopupPage_Page.search_and_verify_underwriting_get_proposal_id();
obj_UnderwritingPopupPage_Page.search_and_verify_underwriting_get_quote_no();

obj_UnderwritingPopupPage_Page.search_and_verify_underwriting_download_contract_file();
obj_UnderwritingPopupPage_Page.search_and_verify_underwriting_download_proposal();

obj_UnderwritingPopupPage_Page.verify_send_for_underwriting_button();


//obj_UnderwritingPopupPage_Page.verify_underwriting_cancel_button();
		
	}
	
	/*
	 * @Test( priority=3)
	 * 
	 * public void
	 * broker_create_opportunity_business_currentstatus_before_sending_to_proposal()
	 * throws Exception {
	 * 
	 * 
	 * obj_Opportunities_Page = new Opportunities();
	 * 
	 * 
	 * boolean opp_CurrentStatus= obj_Opportunities_Page.
	 * verify_current_status_of_opportunity_before_sending_to_proposal();
	 * 
	 * Assert.assertTrue(opp_CurrentStatus);
	 * 
	 * 
	 * System.out.println( "Status Verified : Before sending the proposal ");
	 * LO.print("Status Verified : Before sending the proposa");
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Test( priority=4)
	 * 
	 * public void broker_create_opportunity_business_adding_proposal() throws
	 * Exception {
	 * 
	 * 
	 * obj_Opportunities_Page.opp_listing_detail_page();
	 * 
	 * obj_Opportunities_Page.opp_opp_fact_find();
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
	 * 
	 * }
	 * 
	 * @Test( priority=5)
	 * 
	 * public void
	 * broker_create_opportunity_business_currentstatus_after_sending_to_proposal()
	 * throws Exception { obj_Opportunities_Page = new Opportunities();
	 * 
	 * 
	 * // Opportunity listing screen - Proposal status boolean
	 * opp_AfterCurrentStatus =obj_Opportunities_Page.
	 * verify_current_status_of_opportunity_after_sending_to_proposal();
	 * 
	 * Assert.assertTrue(opp_AfterCurrentStatus);
	 * 
	 * 
	 * 
	 * System.out.println( "Status Verified : After sending the proposal ");
	 * LO.print("Status Verified : After sending the proposal");
	 * 
	 * }
	 * 
	 * 
	 * @Test( priority=6)
	 * 
	 * public void broker_create_opportunity_business_sending_to_contract() throws
	 * Exception { obj_Opportunities_Page = new Opportunities();
	 * 
	 * 
	 * // Opportunity listing screen - Proposal status
	 * //obj_Proposal_Page.Opp_listing_proposal_status();
	 * 
	 * 
	 * // Send Contract flow
	 * 
	 * // obj_Opportunities_Page.opp_Find_Channel_Status();
	 * 
	 * obj_Opportunities_Page.opp_find_send_contract_icon();
	 * 
	 * //obj_Opportunities_Page.opp_Find_Channel_Status();
	 * 
	 * Thread.sleep(4000);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Test( priority=7)
	 * 
	 * public void
	 * broker_create_opportunity_business_currentstatus_after_sending_to_contract()
	 * throws Exception { obj_Opportunities_Page = new Opportunities();
	 * 
	 * 
	 * 
	 * 
	 * // Opportunity listing screen - Proposal status boolean
	 * opp_AfterCurrentStatus_contract =obj_Opportunities_Page.
	 * verify_current_status_of_opportunity_after_sending_to_customer_contract();
	 * 
	 * Assert.assertTrue(opp_AfterCurrentStatus_contract);
	 * 
	 * 
	 * 
	 * System.out.println( "Status Verified : Sent to customer ");
	 * LO.print("Status Verified : Status Verified : Sent to customer");
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * @Test( priority=8)
	 * 
	 * public void broker_create_opportunity_business_listing_screen_icon() throws
	 * Exception { obj_Opportunities_Page = new Opportunities();
	 * 
	 * 
	 * 
	 * 
	 * // Opportunity listing screen - Proposal status
	 * obj_Opportunities_Page.opp_find_opp_listing_button();
	 * 
	 * 
	 * 
	 * 
	 * System.out.println( "Status Verified : Listing screen button ");
	 * LO.print("Status Verified : Listing screen button ");
	 * 
	 * }
	 * 
	 * 
	 */

	
		
		
		
		 
		 
	}
	
	
	
	
	
	
	
	

