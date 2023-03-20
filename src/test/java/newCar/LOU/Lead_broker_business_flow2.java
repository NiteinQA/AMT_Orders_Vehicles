package newCar.LOU;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
//import org.json.JSONObject;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.Leads;
import com.amt.pages.Opportunities;
import com.amt.pages.Proposal;
import com.amt.pages.Underwriting;
import com.amt.pages.UnderwritingPopupPage;
import com.amt.testBase.TestBase;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Lead_broker_business_flow2 extends TestBase {

	String url;
	String serviceURL;
	String apiURL;


	 
	 Leads obj_Leads_Page;
	 
 Opportunities obj_Opportunities_Page;
 
 
 Proposal obj_Proposal_Page;
 
 UnderwritingPopupPage obj_Underwriting_Popup_Page;

  Underwriting obj_Underwriting_page;
 
 
 //obj_Opportunities_Page.opp_Listing_detail_page
	

	//CloseableHttpResponse closeableHttpResponse;

	
	  @Test(priority=1) public void broker_create_lead_business( ) throws Exception
	  {
	  
	  obj_Leads_Page = new Leads();
	  
	  
	  obj_Leads_Page.lead_General_info();
	  
	  obj_Leads_Page.lead_Customer_info_business();
	  
	  obj_Leads_Page.lead_vehicle_request_broker();
	  
	  //Business = Broker + BCH
	  obj_Leads_Page.lead_map_new_quote_broker_business();
	  
	  obj_Leads_Page.lead_map_new_quote_broker_business_save_and_Convert();
	  
	  
	  
	  }
	 

	
	  @Test( priority=2) public void broker_create_opportunity_business() throws
	  Exception {
	  
	  
	  
	  String GetOpportunityid =  obj_Leads_Page.lead_map_new_quote_broker_business_getting_the_opportunityno();
	  Thread.sleep(5000);
	  
	  // Opportunity flow
	  
	  obj_Opportunities_Page = new Opportunities();
	  Thread.sleep(1000);
	  obj_Opportunities_Page.opp_menu_link();
	  
	  
	  obj_Proposal_Page = new Proposal(); 
	  // Opportunity listing screen - Proposal status 
	  obj_Proposal_Page.Opp_listing_proposal_status();
	  
	  obj_Opportunities_Page.opp_search_textbox(GetOpportunityid);
	  
	  
	  
	  }
	  
	  @Test( priority=3)
	  
	  public void
	  broker_create_opportunity_business_currentstatus_before_sending_to_proposal()
	  throws Exception {
	  
	  
	  obj_Opportunities_Page = new Opportunities();
	  
	  
	  boolean opp_CurrentStatus= obj_Opportunities_Page.
	  verify_current_status_of_opportunity_before_sending_to_proposal();
	  
	  Assert.assertTrue(opp_CurrentStatus);
	  
	  
	  System.out.println( "Status Verified : Before sending the proposal ");
	  LO.print("Status Verified : Before sending the proposa");
	  
	  
	  }
	  
	  
	  
	  @Test( priority=4)
	  
	  public void broker_create_opportunity_business_adding_proposal() throws
	  Exception {
	  
	  
	  obj_Opportunities_Page.opp_listing_detail_page();
	  
	  obj_Opportunities_Page.opp_opp_fact_find();
	  
	  
	  //Proposal page for adding data in opportunity - Customer info, Additional info , Bank detail
	  
	  
	  obj_Proposal_Page.proposal_Add_Customer_info();
	  
	  obj_Proposal_Page.Opp_listing_proposal_fill_form_manually();
	  
	  
	  
	  }
	  
	  @Test( priority=5)
	  
	  public void
	  broker_create_opportunity_business_currentstatus_after_sending_to_proposal()
	  throws Exception { obj_Opportunities_Page = new Opportunities();
	  
	  
	  // Opportunity listing screen - Proposal status
	  boolean opp_AfterCurrentStatus =obj_Opportunities_Page.verify_current_status_of_opportunity_after_sending_to_proposal();
	  
	  Assert.assertTrue(opp_AfterCurrentStatus);
	  
	  
	  
	  System.out.println( "Status Verified : After sending the proposal ");
	  LO.print("Status Verified : After sending the proposal");
	  
	  }
	  
	  
	  @Test( priority=6)
	  
	  public void broker_create_opportunity_business_sending_to_contract() throws
	  Exception { 
		  
		  
		  obj_Opportunities_Page = new Opportunities();
	  
	  
	  // Opportunity listing screen - Proposal status
	  //obj_Proposal_Page.Opp_listing_proposal_status();
	  
	  
	  // Send Contract flow
	  
	  // obj_Opportunities_Page.opp_Find_Channel_Status();
	  
	  obj_Opportunities_Page.opp_find_send_contract_icon();
	  
	  //obj_Opportunities_Page.opp_Find_Channel_Status();
	  
	  Thread.sleep(4000);
	  
	  
	  }
	  
	  
	  @Test( priority=7)
	  
	  public void
	  broker_create_opportunity_business_currentstatus_after_sending_to_contract()
	  throws Exception {
		  
		  obj_Opportunities_Page = new Opportunities();
	  
	  
	  
	  
	  // Opportunity listing screen - Proposal status
		  boolean opp_AfterCurrentStatus_contract =obj_Opportunities_Page.
	  verify_current_status_of_opportunity_after_sending_to_customer_contract();
	  
	  Assert.assertTrue(opp_AfterCurrentStatus_contract);
	  
	  
	  
	  System.out.println( "Status Verified : Sent to customer ");
	  LO.print("Status Verified : Status Verified : Sent to customer");
	  
	  
	 // String GetOpportunityid =  obj_Leads_Page.lead_map_new_quote_broker_business_getting_the_opportunityno();
	  
	//  obj_Opportunities_Page.opp_search_textbox(GetOpportunityid);
	  
	  
	  
	
	  
	  
	  
	  }
	  
	  @Test( priority=8)
	  public void verify_signed_contract_status_with_api_call() throws Exception
	  
	  {
		  
		  obj_Opportunities_Page = new Opportunities();
	 
		 String [] OppDATA =  obj_Opportunities_Page.get_api_data_opp();
		 
		 String opp_id_screen =  OppDATA [0];
		 String quote_ref_screen =  OppDATA [1];
		  
		
		 int statuscode = obj_Opportunities_Page.postAPITest(quote_ref_screen, opp_id_screen);
		  
	Assert.assertEquals(statuscode, 200);
	
	System.out.println("Status code 200 received ");
		  
	 obj_Opportunities_Page.opp_search_textbox(opp_id_screen);
	
	  }
	  
	 

	  
	  
	  @Test(priority=9)

	  public void verify_status_after_contract_signed () throws Exception
	  {
		  
		  
		  obj_Opportunities_Page = new Opportunities();
		  
		 
		  boolean CurrentStatusafterContractSigned = obj_Opportunities_Page.verify_current_status_of_opportunity_after_contract_signed();
		  Assert.assertTrue(CurrentStatusafterContractSigned);
		  
		  
	  }
	  
	  
	  @Test(priority=10)

	  public void verify_underwriting_flow_broker_with_status () throws Exception
	  {
	
		  
		  obj_Underwriting_Popup_Page = new UnderwritingPopupPage();
		  
		  obj_Underwriting_Popup_Page.search_and_verify_underwriting_icon_is_availabale();

		  obj_Underwriting_Popup_Page.search_and_verify_underwriting_get_proposal_id();
		  obj_Underwriting_Popup_Page.search_and_verify_underwriting_get_quote_no();
		  
		  

		//  obj_Underwriting_Popup_Page.search_and_verify_underwriting_download_contract_file();
		 // obj_Underwriting_Popup_Page.search_and_verify_underwriting_download_proposal();

		  obj_Underwriting_Popup_Page.verify_send_for_underwriting_button();

		  
	  }
		
	  
	  @Test(priority=11)

	  public void verify_status_after_sending_to_underwriting() throws Exception
	  {
		  
		
		  
        obj_Opportunities_Page = new Opportunities();
		  
		  
		  boolean CurrentStatusafterSendingToUnderwriting=  obj_Opportunities_Page.verify_current_status_of_opportunity_after_contract_sending_to_underwriting();
		  Assert.assertTrue(CurrentStatusafterSendingToUnderwriting);
		  
		  
	  }
	  
	  
	  
		
		  @Test(priority=12 )
		  
		  public void verify_underwriting_lisitig_page()throws Exception {
		  
		//  obj_Underwriting_Popup_Page = new UnderwritingPopupPage();
		  
		  obj_Underwriting_page = new Underwriting();
		  
		  
		  obj_Underwriting_page.verify_underwriting_menulink();
		  
		  obj_Underwriting_page.verify_underwriting_menulink_broker();
		  
		  
	obj_Underwriting_page.verify_underwriting_proposal_search_text_box();
		  
		  
		  
		  }
		 
		
		  
		  
		  
 @Test(priority=13 )
		  
		  public void verify_underwriting_proposal_page_flow() throws InterruptedException, AWTException
		  {
		  
		//  obj_Underwriting_Popup_Page = new UnderwritingPopupPage();
		  
		  obj_Underwriting_page = new Underwriting();
		  
		  
		  obj_Underwriting_page.find_underwriting_listing_detail_for_proposal();
		  
		  obj_Underwriting_page.find_underwriting_tab_quote_page();
		  
		  
		  
	obj_Underwriting_page.find_underwriting_tab_creditfile_page();
		  
	obj_Underwriting_page.find_underwriting_tab_document_page();
	obj_Underwriting_page.find_underwriting_tab_decision_page();
	
	obj_Underwriting_page.find_underwriting_tab_decision_page_accept_button();
	
	obj_Underwriting_page.find_underwriting_tab_decision_page_accept_upload();
	
	obj_Underwriting_page.verification_underwriting_tab_decision_page_view_icon();
	
	obj_Underwriting_page.verification_underwriting_tab_decision_page_saveandexit_button();
	
		  
		  }
		 
		
		
		
		
	
		
		
		
		
		
		

	}
	

