package com.amt.test.LOU;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.Leads;
import com.amt.pages.Opportunities;
import com.amt.pages.Proposal;
import com.amt.pages.Underwriting;
import com.amt.pages.UnderwritingPopupPage;
import com.amt.testBase.TestBase;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Underwrting_flow extends TestBase {



	
    Leads obj_Leads_Page;
    Opportunities obj_Opportunities_Page;
    Proposal obj_Proposal_Page;
    UnderwritingPopupPage obj_UnderwritingPopupPage_Page;
    
    Underwriting obj_Underwriting;


	
	
	@Test( priority=1)
	public void underwriting_find_menu_link() throws Exception {
	
	
		
	 obj_Underwriting = new Underwriting();
	
		/*
		 * obj_Underwriting.verify_underwriting_menulink();
		 * 
		 * obj_Underwriting.verify_underwriting_menulink_broker();
		 * 
		 * obj_Underwriting.verify_underwriting_proposal_search_text_box();
		 */
	 
	
		 
	}
	
}
	
	
	
	
	
	

