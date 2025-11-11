package opportunity;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.Leads;
import com.amt.pages.Opportunities;
import com.amt.pages.Proposal;
import com.amt.pages.Underwriting;
import com.amt.pages.UnderwritingPopupPage;
import com.amt.testBase.TestBase;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class LOU_used_car_broker_business_hire_accept_flow_Test extends TestBase {

	Leads obj_Leads_Page;
	Opportunities obj_Opportunities_Page;
	Proposal obj_Proposal_Page;

	// UnderwritingPopupPage obj_Underwriting_page;

	UnderwritingPopupPage obj_Underwriting_Popup_Page;

	Underwriting obj_Underwriting_page;

	@Test(priority = 1)
	public void L1_create_lead_for_broker_business() throws Exception {

		System.out.println("Test 1");
		
		obj_Leads_Page = new Leads();

		obj_Leads_Page.lead_General_info();

		obj_Leads_Page.lead_Customer_info_business();

		obj_Leads_Page.lead_vehicle_request_broker();

		// Business = Broker + BCH
		obj_Leads_Page.lead_map_new_quote();

		// verifying table values with Quote save excel sheet values before saving

		boolean table_verification_before_save = obj_Leads_Page.verify_table_values_on_lead_page();

		// save lead and convert it to opportunity
		obj_Leads_Page.save_and_convert_after_mapping_a_quote();

		// verifying table values with Quote save excel sheet values after saving
		boolean table_verification_after_save = obj_Leads_Page.verify_table_values_on_lead_page();

		boolean flag = false;
		
		if (table_verification_before_save && table_verification_after_save)

		{
			flag = true;
		}

		// Assert.assertTrue(flag);

		// Getting oppo ID and Saving to excel sheet and printing to console
		
		obj_Leads_Page.get_the_opportunity_no_after_converting_lead_into_opportunity();

	}

	@Test(priority = 2)

	public void L2_broker_business_current_status_before_sending_to_proposal() throws Exception {

		
		System.out.println("Test 2");
		// Opportunity flow
		obj_Opportunities_Page = new Opportunities();

		obj_Opportunities_Page.opp_menu_link();

		obj_Opportunities_Page.opp_search_textbox();

		boolean opp_CurrentStatus = obj_Opportunities_Page
				.verify_current_status_of_opportunity_before_sending_to_proposal();

		Assert.assertTrue(opp_CurrentStatus);

		System.out.println("Status Verified : Before sending the proposal ");
		LO.print          ("Status Verified : Before sending the proposal");

	}
	
	@Test(priority = 3)

	public void L3_verify_table_values_on_opportunity_page_before_sending_proposal() throws Exception {

		
		System.out.println("Test 3");
		obj_Leads_Page = new Leads();
		
		boolean table_verification_before_save = obj_Leads_Page.verify_table_values_on_lead_page();
		
		//Assert.assertTrue(table_verification_before_save);
		
		
		System.out.println("");
		LO.print          ("");
		System.out.println("Table values verified Successfully");
		LO.print          ("Table values verified Successfully");

	}

	@Test(priority = 4)
	public void L4_verify_current_status_on_opportunity_page_before_sending_to_proposal_for_broker_business_flow() throws Exception {

		System.out.println("Test 4");
		
		obj_Opportunities_Page = new Opportunities();
		
		boolean opp_CurrentStatus = obj_Opportunities_Page
				.verify_current_status_of_opportunity_before_sending_to_proposal();

		Assert.assertTrue(opp_CurrentStatus);

		System.out.println("Status Verified : Before sending the proposal ");
		LO.print("Status Verified : Before sending the proposal");

	}


	@Test(priority = 5)

	public void L5_adding_proposal_on_opportunity_page_broker_business_flow() throws Exception {

		System.out.println("Test 5");
		
		obj_Opportunities_Page = new Opportunities();

		obj_Opportunities_Page.opp_listing_detail_page();

		obj_Opportunities_Page.opp_opp_fact_find();

		// Proposal page for adding data in opportunity - Customer info, Additional info
		// , Bank detail
		
		obj_Proposal_Page = new Proposal();

		obj_Proposal_Page.proposal_Add_Customer_info();

		obj_Proposal_Page.Opp_listing_proposal_fill_form_manually();

		System.out.println("");
		LO.print          ("");
		System.out.println("Proposal Received Successfully");
		LO.print          ("Proposal Received Successfully");

	}

	@Test(priority = 6)

	public void L6_verify_current_status_on_opportunity_page_after_sending_to_proposal_for_broker_business_flow() throws Exception {
		
		System.out.println("Test 6");
		
		obj_Opportunities_Page = new Opportunities();

		// Opportunity listing screen - Proposal status
		boolean opp_AfterCurrentStatus = obj_Opportunities_Page
				.verify_current_status_of_opportunity_after_sending_to_proposal();

		Assert.assertTrue(opp_AfterCurrentStatus);

		System.out.println("Status Verified : After sending the proposal ");
		LO.print          ("Status Verified : After sending the proposal");

	}

	

	@Test(priority = 7)

	public void broker_business_sending_contract() throws Exception {
		
		System.out.println("Test 7");
		
		obj_Opportunities_Page = new Opportunities();

		obj_Opportunities_Page.opp_find_send_contract_icon();
	

	}


//	@Test(priority = 8)
//
//	public void L8_verify_current_status_on_opportunity_page_after_sending_contract_for_broker_business_flow() throws Exception {
//
//		System.out.println("Test 8");
//		
//		obj_Opportunities_Page = new Opportunities();
//
//		// Opportunity listing screen - Proposal status
//		boolean opp_AfterCurrentStatus_contract = obj_Opportunities_Page
//				.verify_current_status_of_opportunity_after_sending_to_customer_contract();
//
//		Assert.assertTrue(opp_AfterCurrentStatus_contract);
//
//		System.out.println("Status Verified : Sent to customer ");
//		LO.print("Status Verified : Status Verified : Sent to customer");
//
//
//
//	}
//
//	@Test(priority = 9)
//	public void L9_verify_signed_contract_status_with_api_call() throws Exception
//
//	{
//
//		
//		System.out.println("Test 9");
//		
//		obj_Opportunities_Page = new Opportunities();
//
//		String[] OppDATA = obj_Opportunities_Page.get_api_data_opp();
//
//		String opp_id_screen = OppDATA[0];
//		String quote_ref_screen = OppDATA[1];
//
//		System.out.println("opp_id_screen" + opp_id_screen);
//
//		System.out.println("quote_ref_screen" + quote_ref_screen);
//
//		int statuscode = obj_Opportunities_Page.postAPITest(quote_ref_screen, opp_id_screen);
//
//		Assert.assertEquals(statuscode, 200);
//
//		LO.print          ("Status code "+statuscode+" received ");
//		System.out.println("Status code "+statuscode+" received ");
//
//		obj_Opportunities_Page.opp_search_textbox();
//
//	}

	
	  @Test(priority = 10)
	  
	  public void L10_verify_status_after_contract_signed() throws Exception {
	  
			System.out.println("Test 10");
		  
	  obj_Opportunities_Page = new Opportunities();
	  
	  boolean CurrentStatusafterContractSigned = obj_Opportunities_Page
	  .verify_current_status_of_opportunity_after_contract_signed();
	  Assert.assertTrue(CurrentStatusafterContractSigned);
	  
		System.out.println("Status Verified : Contract Signed ");
		LO.print          ("Status Verified : Contract Signed ");
	  
	  }
	 

	@Test(priority = 11)

	public void UW1_verify_underwriting_flow_ownbook_with_status() throws Exception {

		System.out.println("Test 11");
		
		obj_Underwriting_Popup_Page = new UnderwritingPopupPage();

		obj_Underwriting_Popup_Page.search_and_verify_underwriting_icon_is_availabale();

		boolean underwriting_pop_up_value_verification = obj_Underwriting_Popup_Page.verify_underwriting_pop_up_summary_values_for_broker_hire_flow();

		//Assert.assertTrue(underwriting_pop_up_value_verification);
	
		obj_Underwriting_Popup_Page.verify_send_for_underwriting_button();

	}

	@Test(priority = 12)

	public void UW2_verify_status_after_sending_to_underwriting() throws Exception {

		System.out.println("Test 12");
		
		obj_Opportunities_Page = new Opportunities();

		boolean CurrentStatusafterSendingToUnderwriting = obj_Opportunities_Page
				.verify_current_status_of_opportunity_after_contract_sending_to_underwriting();
		Assert.assertTrue(CurrentStatusafterSendingToUnderwriting);
		
		System.out.println("Status Verified : Awaiting Underwriting ");
		LO.print          ("Status Verified : Awaiting Underwriting ");

	}

	@Test(priority = 13)

	public void UW3_verify_underwriting_lisitig_page() throws Exception {
		
		System.out.println("Test 13");

		obj_Underwriting_page = new Underwriting();

		obj_Underwriting_page.verify_underwriting_menulink();

		obj_Underwriting_page.verify_underwriting_menulink_broker();

		obj_Underwriting_page.verify_underwriting_proposal_search_text_box();

	}

	@Test(priority = 14)

	public void UW4_verify_underwriting_proposal_page_flow() throws Exception {

		System.out.println("Test 14");

		obj_Underwriting_page = new Underwriting();


		obj_Underwriting_page.find_underwriting_listing_detail_for_proposal();

		boolean status = obj_Underwriting_page.verify_quote_tab_on_underwriting_page_for_broker_hire_flow();

	//	Assert.assertTrue(status);
		
		obj_Underwriting_page.find_underwriting_tab_decision_page();

	}

	/*
	 * @Test(priority = 14)
	 * 
	 * public void UW6_verify_ownbook_underwriting_proposal_decision_with_declined()
	 * throws Exception {
	 * 
	 * obj_Underwriting_page = new Underwriting();
	 * 
	 * //obj_Underwriting_page.find_underwriting_listing_detail_for_proposal();
	 * obj_Underwriting_page.find_underwriting_tab_decision_page();
	 * 
	 * obj_Underwriting_page.find_decision_decline(); obj_Underwriting_page.
	 * verification_underwriting_tab_decision_page_saveandexit_button();
	 * 
	 * boolean statusofdecline = obj_Underwriting_page
	 * .verify_current_status_of_underwriting_after_sending_to_decline();
	 * Assert.assertTrue(statusofdecline);
	 * 
	 * }
	 */

	@Test(priority = 15)

	public void UW5_verify_underwriting_proposal_decision_with_accept() throws Exception {

		obj_Underwriting_page = new Underwriting();

		// obj_Underwriting_page.find_underwriting_listing_detail_for_proposal();

		// obj_Underwriting_page.find_underwriting_listing_detail_listing_yes_option();

		// obj_Underwriting_page.find_underwriting_tab_decision_page();
		obj_Underwriting_page.find_underwriting_tab_decision_page_accept_button();

		obj_Underwriting_page.find_underwriting_tab_decision_page_accept_upload();
		// //
		// obj_Underwriting_page.verification_underwriting_tab_decision_page_view_icon();

		obj_Underwriting_page.verification_underwriting_tab_decision_page_save_and_exit_button();

		
		 boolean statusofaccept =obj_Underwriting_page.verify_current_status_of_underwriting_after_sending_to_accept();
		  Assert.assertTrue(statusofaccept);
		  
			System.out.println("Underwriting Status Verified : Accepted");
			LO.print          ("Underwriting Status Verified : Accepted");
		 

	}

	/*
	 * @Test(priority = 13)
	 * 
	 * public void
	 * UW7_verify_ownbook_underwriting_proposal_decision_accept_with_changes
	 * ()throws Exception {
	 * 
	 * obj_Underwriting_page = new Underwriting();
	 * 
	 * //obj_Underwriting_page.find_underwriting_listing_detail_for_proposal();
	 * 
	 * 
	 * 
	 * //obj_Underwriting_page.find_underwriting_tab_decision_page();
	 * obj_Underwriting_page.find_underwriting_tab_decision_page_accept_button();
	 * obj_Underwriting_page.ownbook_accept_with_change_the_data_quote();
	 * 
	 * 
	 * obj_Underwriting_page.
	 * verification_underwriting_tab_decision_page_saveandexit_button();
	 * 
	 * // obj_Underwriting_page.find_underwriting_decision_yes_option();
	 * 
	 * 
	 * // Assert for Accept condition boolean statusofaccept =
	 * obj_Underwriting_page.
	 * verify_current_status_of_underwriting_after_sending_to_accept_with_changes();
	 * Assert.assertTrue(statusofaccept);
	 * 
	 * }
	 */

//	@Test(priority = 14)
//
//	public void UW6_verify_ownbook_opportunity_search_text_box_accept_with_changes() throws Exception {
//
//		obj_Opportunities_Page = new Opportunities();
//		obj_Underwriting_Popup_Page = new UnderwritingPopupPage();
//
//		obj_Opportunities_Page.opp_menu_link();
//
//		obj_Opportunities_Page.verify_opportunity_broker_business_quote_search_text_box();
//
//	}
//
//	@Test(priority = 15)
//
//	public void UW7_verify_ownbook_opportunity_accept_with_changes_underwriting_pop_up() throws Exception
//
//	{
//		obj_Underwriting_Popup_Page = new UnderwritingPopupPage();
//
//		obj_Underwriting_Popup_Page.search_and_verify_underwriting_icon_is_availabale();
//
////obj_Underwriting_Popup_Page.verify_ownbook_underwriting_popup_accept_with_change_flow();
//
//	}
}
