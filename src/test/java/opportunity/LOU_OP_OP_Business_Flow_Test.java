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
public class LOU_OP_OP_Business_Flow_Test extends TestBase {

	Leads obj_Leads_Page;
	Opportunities obj_Opportunities_Page;
	Proposal obj_Proposal_Page;

	// UnderwritingPopupPage obj_Underwriting_page;

	UnderwritingPopupPage obj_Underwriting_Popup_Page;

	Underwriting obj_Underwriting_page;

	@Test(priority = 1)
	public void L1_create_lead_for_Outright_Purchase_business() throws Exception {

		System.out.println("Test 1");
		
		obj_Leads_Page = new Leads();

		obj_Leads_Page.lead_General_info();

		obj_Leads_Page.lead_Customer_info_business();

		obj_Leads_Page.lead_vehicle_request_Outright_Purchase();

		// Business = Broker + BCH
		obj_Leads_Page.lead_map_new_quote();

		// verifying table values with Quote save excel sheet values before saving

		boolean table_verification_before_save = obj_Leads_Page.verify_quote_no_and_sales_price_displayed_in_table();

		// save lead and convert it to opportunity
		obj_Leads_Page.save_and_convert_after_mapping_a_quote();

		// verifying table values with Quote save excel sheet values after saving
		boolean table_verification_after_save = obj_Leads_Page.verify_quote_no_and_sales_price_displayed_in_table();

		boolean flag = false;
		
		if (table_verification_before_save && table_verification_after_save)

		{
			flag = true;
		}

		// Assert.assertTrue(flag);

		// Getting oppo ID and Saving to excel sheet and printing to console
		
		obj_Leads_Page.get_the_opportunity_no_after_converting_lead_into_opportunity();

	}

	@Test(priority = 2,  dependsOnMethods = { "L1_create_lead_for_Outright_Purchase_business" })

	public void L2_current_status_before_sending_to_proposal() throws Exception {

		
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
	
	@Test(priority = 3, dependsOnMethods = { "L2_current_status_before_sending_to_proposal" })

	public void L3_fill_up_the_required_fields_for_sending_a_proposal()
			throws Exception {

		System.out.println("Test 3");

		obj_Opportunities_Page.opp_listing_detail_page();
		
		obj_Proposal_Page = new Proposal();

		obj_Proposal_Page.proposal_Add_Customer_info();

		obj_Proposal_Page.Opp_listing_proposal_fill_form_manually();

	}

	@Test(priority = 4, dependsOnMethods = {
			"L3_fill_up_the_required_fields_for_sending_a_proposal" })

	public void L4_ownbook_create_opportunity_business_current_status_after_sending_to_proposal() throws Exception {

		System.out.println("Test 4");

		obj_Opportunities_Page = new Opportunities();

		// Opportunity listing screen - Proposal status
		boolean opp_AfterCurrentStatus = obj_Opportunities_Page
				.verify_current_status_of_opportunity_after_sending_to_proposal();

		Assert.assertTrue(opp_AfterCurrentStatus);

	}


	@Test(priority = 5, dependsOnMethods = {
			"L4_ownbook_create_opportunity_business_current_status_after_sending_to_proposal" })	

	public void L5_sending_contract() throws Exception {
		
		System.out.println("Test 5");
		
		obj_Opportunities_Page = new Opportunities();

		obj_Opportunities_Page.opp_find_send_contract_icon();
	

	}


	@Test(priority = 6 , dependsOnMethods = {"L5_sending_contract" })

	public void L6_verify_current_status_on_opportunity_page_after_sending_contract_for_broker_business_flow() throws Exception {

		System.out.println("Test 6");
		
		obj_Opportunities_Page = new Opportunities();

		// Opportunity listing screen - Proposal status
		boolean opp_AfterCurrentStatus_contract = obj_Opportunities_Page
				.verify_current_status_of_opportunity_after_sending_to_customer_contract();

		Assert.assertTrue(opp_AfterCurrentStatus_contract);

		System.out.println("Status Verified : Sent to customer ");
		LO.print("Status Verified : Status Verified : Sent to customer");



	}

	@Test(priority =7 , dependsOnMethods = {"L6_verify_current_status_on_opportunity_page_after_sending_contract_for_broker_business_flow" })
	public void L7_verify_signed_contract_status_with_api_call() throws Exception

	{

		
		System.out.println("Test 7");
		
		obj_Opportunities_Page = new Opportunities();

		String[] OppDATA = obj_Opportunities_Page.get_api_data_opp();

		String opp_id_screen = OppDATA[0];
		String quote_ref_screen = OppDATA[1];

		System.out.println("opp_id_screen" + opp_id_screen);

		System.out.println("quote_ref_screen" + quote_ref_screen);

		int statuscode = obj_Opportunities_Page.postAPITest(quote_ref_screen, opp_id_screen);

		Assert.assertEquals(statuscode, 200);

		LO.print          ("Status code "+statuscode+" received ");
		System.out.println("Status code "+statuscode+" received ");

		obj_Opportunities_Page.opp_search_textbox();

	}

	
	  @Test(priority = 8 , dependsOnMethods = {"L7_verify_signed_contract_status_with_api_call" })
	  
	  public void L8_verify_status_after_contract_signed() throws Exception {
	  
			System.out.println("Test 8");
		  
	  obj_Opportunities_Page = new Opportunities();
	  
	  boolean CurrentStatusafterContractSigned = obj_Opportunities_Page
	  .verify_current_status_of_opportunity_after_contract_signed();
	  Assert.assertTrue(CurrentStatusafterContractSigned);
	  
		System.out.println("Status Verified : Contract Signed ");
		LO.print          ("Status Verified : Contract Signed ");
	  
	  }
	 

}
