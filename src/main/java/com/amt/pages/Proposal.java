package com.amt.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Dropdown;
import com.amt.testUtil.ExplicitWait;

public class Proposal extends TestBase {

	JavascriptExecutor js;
	
	Opportunities obj_Opportunities_Page;

	
	
@FindBy(xpath = "//img[@alt='Loading...']")
private List<WebElement> loading_icon;


	@FindBy(xpath = "//input[@id='traddingName']")
	private WebElement proposal_traddingName;

	@FindBy(xpath = "//select[@id='legalEntity']")
	private WebElement proposal_legalEntity;
	
	
	@FindBy(xpath = "//input[@id='registrationNumber']")
	private WebElement proposal_registration_number;
	
	
	
	
	
	
	
	
	@FindBy(xpath = "//input[@id='yearEstablished']")
	private WebElement  proposal_year;

	
	@FindBy(xpath = "//input[@id='postalCode0']")
	private WebElement proposal_postcode ;


	@FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-add-opportunities[1]/app-uw-proposal[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/app-proposal-customer-details[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/ul[1]/li[1]/span[1]")
	private WebElement 	proposal_postcode_value ;
	
	
	
	@FindBy(xpath = "//input[@id='currentTimeAtAddYears0']")
	private WebElement 	proposal_time_at_address_year ;

	
	@FindBy(xpath = "//input[@id='currentTimeAtAddMonths0']")
	private WebElement 	proposal_time_address_month ;
	
	
	
	@FindBy(xpath = "//label[normalize-space()='Same as above']")
	private WebElement 	proposal_trading_sameasabove ;
	 
	
	
	
	// Trading address WebElement 
	
	
	
	@FindBy(xpath = "//input[@id='custTelephoneNumber']")
	private WebElement 	proposal_trading_main_telephone_no ;
	

	
	@FindBy(xpath = "//input[@id='mobileNumber']")
	private WebElement 	proposal_trading_mobile_no;
	
	 
	
	@FindBy(xpath = "//input[@id='mainContactName']")
	private WebElement 	proposal_trading_main_contact_name ;
	
	
	
	@FindBy(xpath = "//*[@id=\"emailAddress\"]")
	private WebElement 	proposal_trading_email_address;
	
	
	
	@FindBy(xpath = "//*[@id=\"websiteAddress\"]")
	private WebElement 	proposal_trading_website_address;
	
		
	@FindBy(xpath = "//*[@id=\"noOfPartners\"]")
	private WebElement 	proposal_trading_no_of_directors;
	
	
	@FindBy(xpath = "//*[@id=\"natureOfBusiness\"]")
	private WebElement 	proposal_trading_nature_of_business;
		
	@FindBy(xpath = "//*[@id=\"noOfCars\"]")
	private WebElement 	proposal_trading_No_of_cars_in_fleet;
	
	 
	
	@FindBy(xpath = "//*[@id=\"noOfVans\"]")
	private WebElement 	proposal_trading_Number_of_vans_in_fleet ;
	
	 
	
	@FindBy(xpath = "//*[@id=\"noOfTrucks\"]")
	private WebElement 	proposal_trading_Number_of_trucks ;
	
	 
			

			
//  Director WebElement 
	
			
	@FindBy(xpath = "//*[@id=\"title1\"]")
	private WebElement 	proposal_director_title ;
	
		
	@FindBy(xpath = "//*[@id=\"firstName\"]")
	private WebElement 	proposal_director_first_name;

	
		
	@FindBy(xpath = "//input[@id='surname']")
	private WebElement 	proposal_director_surname ;

		
	
	@FindBy(xpath = "//input[@id='maidenName']")
	private WebElement 	proposal_director_previous_name ;

	
	@FindBy(xpath = "//select[@id='maritalStatus1']")
	private WebElement 	proposal_director_marital_status ;

	@FindBy(xpath = "//input[@id='dateOfBirth1']")
	private WebElement 	proposal_director_dob ;

	@FindBy(xpath = "//input[@id='noOfDependents']")
	private WebElement 	proposal_director_No_of_dependents;

	@FindBy(xpath = "//input[@id='partnerTelephoneNumber1']")
	private WebElement 	proposal_director_Home_number ;

	@FindBy(xpath = "//input[@id='partnerMobileNumber1']")
	private WebElement 	proposal_director_mobile_number;

	@FindBy(xpath = "//div[@class='DirectorPartnerfirstblockcover']//input[@id='emailAddress']")
	private WebElement 	proposal_director_email_address ;

	@FindBy(xpath = "//input[@id='currentAddPostalCode0']")
	private WebElement 	proposal_director_post_code ;

	@FindBy(xpath = "//input[@id='currentTimeAtAddYears10']")
	private WebElement 	proposal_director_Time_at_address_years ;

	@FindBy(xpath = "//input[@id='currentTimeAtAddMonths10']")
	private WebElement 	proposal_director_Time_at_address_months ;


	////label[normalize-space()='Owner']
	
	@FindBy(xpath = "//label[normalize-space()='Owner']")
	private WebElement 	proposal_director_Owner ;
	
	@FindBy(xpath = "//*[@id=\"positionInCompany\"]")
	private WebElement 	proposal_director_position_in_company ;
	
	@FindBy(xpath = "//label[@for='ta-no1']")
	private WebElement 	proposal_director_authorised_signatory ;
	
	
	//Bank Detail

	
	@FindBy(xpath = "//input[@id='accountName']")
	private WebElement 	proposal_bankdetail_exact_account_name ;
	
	
	@FindBy(xpath = "//input[@id='sortCode']")
	private WebElement 	proposal_bankdetail_sort_code ;
			
	
	@FindBy(xpath = "//input[@id='accountNumber']")
	private WebElement 	proposal_bankdetail_account_number;
	
	@FindBy(xpath = "//input[@id='timeWithBankYears']")
	private WebElement 	proposal_bankdetail_years ;
			
	@FindBy(xpath = "//input[@id='timeWithBankMonths']")
	private WebElement 	proposal_bankdetail_months ;
			
	
	@FindBy(xpath = "//input[@id='bankName']")
	private WebElement 	proposal_bankdetail_bank_name ;
			
	@FindBy(xpath = "//input[@id='bankAddress']")
	private WebElement 	proposal_bankdetail_bank_address ;
			
	
	//Additional info
	
	@FindBy(xpath = "//*[@id=\"SupportDesc\"]")
	private WebElement 	proposal_additionalinfo_textbox ;
	
	
//	@FindBy(xpath = "//*[@class='col-md-12 m-0']//button[normalize-space()='Save']")
//	private WebElement 	proposal_additionalinfo_save_button ;
	
	@FindBy(xpath = "//*[@id='AICOne1']/div[3]/div/button[1]")
	private WebElement 	proposal_additionalinfo_save_button ;
	
	
	
	@FindBy(xpath = "//button[@class='btn btn-secondary py-3 px-4 text-nowrap d-flex ml-auto']")
	private WebElement 	proposal_additionalinfo_cancel_button ;
	
	@FindBy(xpath = "//*[@id=\"cWraper\"]/div/app-add-opportunities/app-opportunity-header/div/div[2]/div/button[2]")
	private WebElement 	proposal_update_button ;
	
	
	@FindBy(xpath = "//button[normalize-space()='Update & Exit']")
	private WebElement 	proposal_update_and_exit_button ;
	

			

		//////////////////////////////// Proposal listing ////////////////////////
	
	
	@FindBy(xpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[1]/td[10]/div/div/div/div")
	private WebElement 	opp_proposal_status ;
	
	
	
	
	@FindBy(xpath = "//*[@title='Send Proposal']")
	private WebElement 	Opp_send_proposal_to_customer_icon ;
	

	
	@FindBy(xpath = "//label[normalize-space()='Yes']")
	private WebElement 	send_proposal_to_customer_pop_up_yes ;
	
	
	@FindBy(xpath = "//*[@id='sendproposalmodal']/div/div/div[2]/div[3]/a")
	private WebElement 	click_manually_submit_behalf_of_customer_link ;
	
	

	@FindBy(xpath = "//input[@id='privacypolicy']")
	private WebElement 	proposal_page_privacy_policy_checkbox;

	@FindBy(xpath = "//input[@id='creditsearches']")
	private WebElement 	proposal_page_credit_search_checkbox ;
	
	
	@FindBy(xpath = "//button[@aria-hidden='true'][normalize-space()='Yes']")
	private WebElement 	proposal_page_final_approval_confirmation ;
	
	
	

	@FindBy(xpath = "//button[normalize-space()='Submit']")
	
	private WebElement 	proposal_page_submit ;
	
	
	
	
	
	
	////////////////////individual//////////////////////
	
	

@FindBy(xpath = "//input[@id='firstName']")
private WebElement proposal_indvidual_first;
	
	

@FindBy(xpath = "//input[@id='surname']")
private WebElement proposal_indvidual_surname;
			
			
			

@FindBy(xpath = "//input[@id='maidenName']")
private WebElement proposal_indvidual_previous_name;

			
@FindBy(xpath = "//label[normalize-space()='Male']")
private WebElement proposal_indvidual_gender;



@FindBy(xpath = "//select[@id='nationality']")
private WebElement proposal_indvidual_nationality;


@FindBy(xpath = "//div[@id='personaldetailscollapseOne1']//div[@class='row']//div[3]//div[1]")
private WebElement proposal_indvidual_british_passport_holder;

			
@FindBy(xpath = "//input[@id='dateOfBirth']")
private WebElement proposal_indvidual_dob;


@FindBy(xpath = "//select[@id='maritalStatus']")
private WebElement proposal_indvidual_Marital_status;

			 
			
@FindBy(xpath = "//input[@id='noOfDependents']")
private WebElement proposal_indvidual_No_of_dependents;


@FindBy(xpath = "//input[@id='homeTelephoneNumber']")
private WebElement proposal_indvidual_home_number;
	


@FindBy(xpath = "//input[@id='workTelephoneNumber']")
private WebElement proposal_indvidual_work_number;

	
@FindBy(xpath = "//input[@id='mobileNumber']")
private WebElement proposal_indvidual_mobile_number;


@FindBy(xpath = "//input[@id='emailAddress']")
private WebElement proposal_indvidual_email_address;


@FindBy(xpath = "//input[@id='drivingLicenceNo']")
private WebElement proposal_indvidual_driving_licence_number;


@FindBy(xpath = "//input[@id='noOfAdults']")
private WebElement proposal_indvidual_no_of_adult;


@FindBy(xpath = "//*[@id=\"currentAddPostalCode0\"]")
private WebElement proposal_indvidual_postcode ;



@FindBy(xpath = "//*[@id=\"personaldetailscollapseOne1\"]/app-proposal-customer-details-personal/div/div/div/div/div[7]/div[1]/ul/li/span")
private WebElement 	proposal_indvidual_value ;

@FindBy(xpath = "//input[@id='currentTimeAtAddYears0']")
private WebElement proposal_indvidual_year ;



@FindBy(xpath = "//input[@id='currentTimeAtAddMonths0']")
private WebElement proposal_indvidual_month ;


@FindBy(xpath = "//label[normalize-space()='Owner']")
private WebElement proposal_indvidual_owner ;



////Emp Detail ////

@FindBy(xpath = "//input[@id='retiredyear']")
private WebElement proposal_indvidual_emp_detail_year; 



@FindBy(xpath = "//input[@id='retiredmonth']")
private WebElement proposal_indvidual_emp_detail_month ;


@FindBy(xpath = "//label[normalize-space()='Retired']")
	private WebElement proposal_indvidual_retired ;


// 3 Income


@FindBy(xpath = "//*[@id=\"incomeexpensecollapseOne1\"]/app-proposal-income-expenditure-personal/div/div/div/div/div[1]/div[1]/div/div/input")
private WebElement gross_monthly_salary ;



@FindBy(xpath = " //*[@id=\"incomeexpensecollapseOne1\"]/app-proposal-income-expenditure-personal/div/div/div/div/div[1]/div[2]/div/div/input")
	private WebElement net_monthly_salary ;
		
	
	

@FindBy(xpath = "//*[@id=\"incomeexpensecollapseOne1\"]/app-proposal-income-expenditure-personal/div/div/div/div/div[2]/div[1]/div/div/input")
private WebElement monthly_rent ;
	
	
	
//4 Bank Detail

@FindBy(xpath = "//input[@id='accountName']")
private WebElement exact_account_name ;



@FindBy(xpath = "//input[@id='sortCode']")
	private WebElement sort_code ;


@FindBy(xpath = " //input[@id='accountNumber']")
	private WebElement account_no ;
	

@FindBy(xpath = "//input[@id='timeWithBankYears']")
	private WebElement years ;
		
@FindBy(xpath = "//input[@id='timeWithBankMonths']")
private WebElement months ;


@FindBy(xpath = "//input[@id='bankName']")
private WebElement bank_name ;


@FindBy(xpath = "//input[@id='bankAddress']")
	private WebElement bank_address ;


@FindBy(xpath = "//textarea[@id='SupportDesc']")
private WebElement additional_info ;


 @FindBy(xpath = "//button[@type='button'][normalize-space()='Save']")
private WebElement proposal_save ;

 
 @FindBy(xpath = "//button[normalize-space()='Update & Exit']")
	private WebElement  proposal_udpate_and_exit ;
	
	


	
	
	
	
	
	
	
	
	
	
	public Proposal() {
		PageFactory.initElements(driver, this);

		

	}

	
	  
	 	 

	// Adding the data in customer Detail 

	public void proposal_Add_Customer_info() throws Exception

	{
	
		

		LO.print("Proposal page will display ");
		System.out.println("Proposal page will display");
		
		 ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
		
		 Thread.sleep(4000);
		
    	ExplicitWait.visibleElement(driver, proposal_traddingName, 40);
		
		proposal_traddingName.clear();
		
		proposal_traddingName.sendKeys("User2");
		
		
	
		
		/*
		 * Dropdown.selectByVisibleText(driver, proposal_legalEntity,
		 * " Private Limited Company (Ltd) " , 30);
		 * 
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_registration_number, 30);
		 * proposal_registration_number.clear();
		 * 
		 * Thread.sleep(3000);
		 * 
		 * proposal_registration_number.sendKeys("1234567890");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_year, 10);
		 * proposal_year.clear();
		 * 
		 * Thread.sleep(3000);
		 * 
		 * proposal_year.sendKeys("2020");
		 * 
		 * 
		 * proposal_year.sendKeys(Keys.TAB);
		 * 
		 * 
		 * 
		 * 
		 * Thread.sleep(4000);
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_postcode, 10);
		 * 
		 * proposal_postcode.clear(); Thread.sleep(1000);
		 * 
		 * proposal_postcode.sendKeys("LS122QH");
		 * 
		 * proposal_postcode.click();
		 * 
		 * //proposal_postcode.sendKeys(Keys.TAB);
		 * 
		 * Thread.sleep(2000); ExplicitWait.visibleElement(driver,
		 * proposal_postcode_value, 10);
		 * 
		 * Thread.sleep(1000); proposal_postcode_value.click();
		 * 
		 * 
		 * Thread.sleep(4000);
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_time_at_address_year, 10);
		 * 
		 * proposal_time_at_address_year.sendKeys("3");
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_time_address_month, 10);
		 * 
		 * proposal_time_address_month.sendKeys("0");
		 * 
		 * 
		 * 
		 * //Trading address Labels
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_trading_sameasabove, 10);
		 * 
		 * proposal_trading_sameasabove.click();
		 * 
		 * 
		 * /////////////////////////////////////////////////////////////////////////////
		 * //////////////////////
		 * 
		 * //1 ExplicitWait.visibleElement(driver, proposal_trading_main_telephone_no,
		 * 10);
		 * 
		 * proposal_trading_main_telephone_no.clear();
		 * proposal_trading_main_telephone_no.sendKeys("12345678901");
		 * 
		 * 
		 * //2
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_trading_mobile_no, 10);
		 * 
		 * proposal_trading_mobile_no.clear();
		 * proposal_trading_mobile_no.sendKeys("98989898989");
		 * 
		 * 
		 * //3
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_trading_main_contact_name, 10);
		 * 
		 * proposal_trading_main_contact_name.clear();
		 * proposal_trading_main_contact_name.sendKeys("12345678901");
		 * 
		 * 
		 * 
		 * //4 ExplicitWait.visibleElement(driver, proposal_trading_email_address, 10);
		 * 
		 * proposal_trading_email_address.clear();
		 * proposal_trading_email_address.sendKeys("mehul.nagar@autofacets.in");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * // 7 ExplicitWait.visibleElement(driver, proposal_trading_website_address,
		 * 10);
		 * 
		 * proposal_trading_website_address.clear();
		 * proposal_trading_website_address.sendKeys("www.test.com");
		 * 
		 * 
		 * // 7 ExplicitWait.visibleElement(driver, proposal_trading_no_of_directors,
		 * 10);
		 * 
		 * proposal_trading_no_of_directors.clear();
		 * proposal_trading_no_of_directors.sendKeys("1");
		 * 
		 * 
		 * 
		 * // 8 ExplicitWait.visibleElement(driver, proposal_trading_nature_of_business,
		 * 10);
		 * 
		 * proposal_trading_nature_of_business.clear();
		 * proposal_trading_nature_of_business.sendKeys("Testing");
		 * 
		 * 
		 * 
		 * // 9 ExplicitWait.visibleElement(driver,
		 * proposal_trading_No_of_cars_in_fleet, 10);
		 * 
		 * proposal_trading_No_of_cars_in_fleet.clear();
		 * proposal_trading_No_of_cars_in_fleet.sendKeys("1");
		 * 
		 * 
		 * 
		 * // 10 ExplicitWait.visibleElement(driver,
		 * proposal_trading_Number_of_vans_in_fleet, 10);
		 * 
		 * proposal_trading_Number_of_vans_in_fleet.clear();
		 * proposal_trading_Number_of_vans_in_fleet.sendKeys("1");
		 * 
		 * // 11 ExplicitWait.visibleElement(driver, proposal_trading_Number_of_trucks,
		 * 10);
		 * 
		 * proposal_trading_Number_of_trucks.clear();
		 * proposal_trading_Number_of_trucks.sendKeys("1");
		 * 
		 * 
		 * ////////////////////////////////////////////////////////Director/////////////
		 * ///////////////
		 * 
		 * // 1 ExplicitWait.visibleElement(driver, proposal_director_title, 10);
		 * 
		 * //proposal_trading_Number_of_trucks.clear();
		 * //proposal_trading_Number_of_trucks.sendKeys("1");
		 * 
		 * 
		 * 
		 * 
		 * // 2 ExplicitWait.visibleElement(driver, proposal_director_first_name, 10);
		 * 
		 * proposal_director_first_name.clear();
		 * proposal_director_first_name.sendKeys("QA comp2");
		 * 
		 * 
		 * 
		 * // 3 ExplicitWait.visibleElement(driver, proposal_director_surname, 10);
		 * 
		 * proposal_director_surname.clear();
		 * proposal_director_surname.sendKeys("Test");
		 * 
		 * 
		 * // 4 ExplicitWait.visibleElement(driver, proposal_director_previous_name,
		 * 10);
		 * 
		 * proposal_director_previous_name.clear();
		 * proposal_director_previous_name.sendKeys("Test");
		 * 
		 * 
		 * 
		 * // 5 ExplicitWait.visibleElement(driver, proposal_director_marital_status,
		 * 10);
		 * 
		 * // proposal_director_marital_status.clear(); //
		 * proposal_director_marital_status.sendKeys("Test");
		 * 
		 * // 6 ExplicitWait.visibleElement(driver, proposal_director_dob, 10);
		 * 
		 * // proposal_director_dob.clear(); // proposal_director_dob.sendKeys("Test");
		 * 
		 * 
		 * // 7 ExplicitWait.visibleElement(driver, proposal_director_No_of_dependents,
		 * 10);
		 * 
		 * proposal_director_No_of_dependents.clear();
		 * proposal_director_No_of_dependents.sendKeys("0");
		 * 
		 * 
		 * // 8 ExplicitWait.visibleElement(driver, proposal_director_Home_number, 10);
		 * 
		 * proposal_director_Home_number.clear();
		 * proposal_director_Home_number.sendKeys("12345678901");
		 * 
		 * // 9 ExplicitWait.visibleElement(driver, proposal_director_mobile_number,
		 * 10);
		 * 
		 * proposal_director_mobile_number.clear();
		 * proposal_director_mobile_number.sendKeys("12345678901");
		 * 
		 * // 10 ExplicitWait.visibleElement(driver, proposal_director_email_address,
		 * 10);
		 * 
		 * proposal_director_email_address.clear();
		 * proposal_director_email_address.sendKeys("mehul.nagar@autofacets.in");
		 * 
		 * 
		 * // 11 // ExplicitWait.visibleElement(driver, proposal_director_post_code,
		 * 10);
		 * 
		 * //proposal_director_post_code.clear();
		 * //proposal_director_post_code.sendKeys("LS122QH");
		 * 
		 * 
		 * // 12 ExplicitWait.visibleElement(driver,
		 * proposal_director_Time_at_address_years, 10);
		 * 
		 * proposal_director_Time_at_address_years.clear();
		 * proposal_director_Time_at_address_years.sendKeys("3");
		 * 
		 * 
		 * // 13 ExplicitWait.visibleElement(driver,
		 * proposal_director_Time_at_address_months, 10);
		 * 
		 * proposal_director_Time_at_address_months.clear();
		 * proposal_director_Time_at_address_months.sendKeys("0");
		 * 
		 * 
		 * // 14 ExplicitWait.visibleElement(driver, proposal_director_Owner, 10);
		 * 
		 * //proposal_director_Owner.clear(); proposal_director_Owner.click();
		 * 
		 * 
		 * 
		 * // 16 ExplicitWait.visibleElement(driver,
		 * proposal_director_position_in_company, 10);
		 * 
		 * proposal_director_position_in_company.clear();
		 * proposal_director_position_in_company.sendKeys("CEO");
		 * 
		 * 
		 * 
		 * // 17 ExplicitWait.visibleElement(driver,
		 * proposal_director_position_in_company, 10);
		 * 
		 * proposal_director_position_in_company.clear();
		 * proposal_director_position_in_company.sendKeys("CEO");
		 * 
		 * 
		 * // 18 ExplicitWait.visibleElement(driver,
		 * proposal_director_authorised_signatory, 10);
		 * 
		 * proposal_director_authorised_signatory.click();
		 * 
		 * 
		 * // Bank Detail
		 * 
		 * //1 ExplicitWait.visibleElement(driver,
		 * proposal_bankdetail_exact_account_name, 10);
		 * proposal_bankdetail_exact_account_name.clear();
		 * proposal_bankdetail_exact_account_name.sendKeys("123456AA");
		 * 
		 * 
		 * //2 ExplicitWait.visibleElement(driver, proposal_bankdetail_sort_code, 10);
		 * proposal_bankdetail_sort_code.clear();
		 * proposal_bankdetail_sort_code.sendKeys("22-22-22");
		 * 
		 * 
		 * //3 ExplicitWait.visibleElement(driver, proposal_bankdetail_account_number,
		 * 10); proposal_bankdetail_account_number.clear();
		 * proposal_bankdetail_account_number.sendKeys("12345678");
		 * 
		 * 
		 * //4 ExplicitWait.visibleElement(driver, proposal_bankdetail_years, 10);
		 * proposal_bankdetail_years.clear(); proposal_bankdetail_years.sendKeys("3");
		 * 
		 * //5 ExplicitWait.visibleElement(driver, proposal_bankdetail_months, 10);
		 * proposal_bankdetail_months.clear(); proposal_bankdetail_months.sendKeys("1");
		 * 
		 * //6 ExplicitWait.visibleElement(driver, proposal_bankdetail_bank_name, 10);
		 * proposal_bankdetail_bank_name.clear();
		 * proposal_bankdetail_bank_name.sendKeys("TestAddress");
		 * 
		 * 
		 * 
		 * /////////////////////////Additional info////////////////////
		 * 
		 * //1 ExplicitWait.visibleElement(driver, proposal_additionalinfo_textbox, 10);
		 * proposal_additionalinfo_textbox.clear();
		 * proposal_additionalinfo_textbox.sendKeys("Test Automation  ");
		 */
		 ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
					 
					//2
					 ExplicitWait.visibleElement(driver, proposal_additionalinfo_save_button, 60);
					 proposal_additionalinfo_save_button.click();
					 
					 
					 ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
					 
					//3
					// ExplicitWait.visibleElement(driver, proposal_update_button, 10);
					// proposal_update_button.click();
			
					
					//4
					 
					 Thread.sleep(5000);
				  ExplicitWait.visibleElement(driver, proposal_update_and_exit_button, 60);
					 proposal_update_and_exit_button.click();
				
					 
					 
					 ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);
					 
					
					//5
					// ExplicitWait.visibleElement(driver, proposal_additionalinfo_cancel_button, 10);
					// proposal_additionalinfo_cancel_button.clear();
					
					
	
					 
					 
		 
		
	}
	
	
	
	
	
	public void proposal_Add_Customer_info_get_screen_value() throws Exception

	{
	
		

		LO.print("Proposal page will display ");
		System.out.println("Proposal page will display");
		
		Thread.sleep(3000);

		ExplicitWait.visibleElement(driver, proposal_traddingName, 20);
		
		proposal_traddingName.clear();
		
		proposal_traddingName.sendKeys("User2");
		
		
		Thread.sleep(3000);
		
		ExplicitWait.visibleElement(driver, proposal_legalEntity, 10);
		
		
		
		
		
	Dropdown.selectByVisibleText(driver, proposal_legalEntity, " Private Limited Company (Ltd) " , 10);
		
	
	
	
	ExplicitWait.visibleElement(driver, proposal_registration_number, 10);
	proposal_registration_number.clear();
	
	Thread.sleep(3000);
	
	proposal_registration_number.sendKeys("1234567890");
	
	
	String Co_registration_number  = proposal_registration_number.getText();
	
	
	
	
	
	
		
		
		ExplicitWait.visibleElement(driver, proposal_year, 10);
		proposal_year.clear();
		
		Thread.sleep(3000);
		
		proposal_year.sendKeys("2020");
		
		
		proposal_year.sendKeys(Keys.TAB);
		
		
		
		
		Thread.sleep(4000);
		
		ExplicitWait.visibleElement(driver, proposal_postcode, 10);
		
		proposal_postcode.clear();
		Thread.sleep(1000);
		
		proposal_postcode.sendKeys("LS122QH");
		
		proposal_postcode.click();
		
		//proposal_postcode.sendKeys(Keys.TAB);
		
		//Thread.sleep(2000);
	ExplicitWait.visibleElement(driver, proposal_postcode_value, 20);
		
		//Thread.sleep(1000);
		proposal_postcode_value.click();
		
		
		Thread.sleep(4000);
		
		ExplicitWait.visibleElement(driver, proposal_time_at_address_year, 10);
		
		proposal_time_at_address_year.sendKeys("3");
		
		
		ExplicitWait.visibleElement(driver, proposal_time_address_month, 10);
		
		 proposal_time_address_month.sendKeys("0");
				 
				 
		
		//Trading address Labels 
		
		 
		 ExplicitWait.visibleElement(driver, proposal_trading_sameasabove, 10);
			
		 proposal_trading_sameasabove.click();
		 
		 
		 ///////////////////////////////////////////////////////////////////////////////////////////////////
		 
		 //1
		 ExplicitWait.visibleElement(driver, proposal_trading_main_telephone_no, 10);
			
		 proposal_trading_main_telephone_no.clear();
		 proposal_trading_main_telephone_no.sendKeys("12345678901");
		 
		 
		 //2
		 
		 ExplicitWait.visibleElement(driver, proposal_trading_mobile_no, 10);
			
		 proposal_trading_mobile_no.clear();
		 proposal_trading_mobile_no.sendKeys("98989898989");
		 
		 
		 //3
		 
		 ExplicitWait.visibleElement(driver, proposal_trading_main_contact_name, 10);
			
		 proposal_trading_main_contact_name.clear();
		 proposal_trading_main_contact_name.sendKeys("12345678901");
		 
		 
		 
		 //4
		 ExplicitWait.visibleElement(driver, proposal_trading_email_address, 10);
			
		 proposal_trading_email_address.clear();
		 proposal_trading_email_address.sendKeys("mehul.nagar@autofacets.in");
		
		
		 
		
		 
		 
		 // 7
		 ExplicitWait.visibleElement(driver, proposal_trading_website_address, 10);
			
		 proposal_trading_website_address.clear();
		 proposal_trading_website_address.sendKeys("www.test.com");
		 
		 
		 // 7
		 ExplicitWait.visibleElement(driver, proposal_trading_no_of_directors, 10);
			
		 proposal_trading_no_of_directors.clear();
		 proposal_trading_no_of_directors.sendKeys("1");
		 
		 
		 
		 // 8
		 ExplicitWait.visibleElement(driver, proposal_trading_nature_of_business, 10);
			
		 proposal_trading_nature_of_business.clear();
		 proposal_trading_nature_of_business.sendKeys("Testing");
		 
		 
		 
		// 9
				 ExplicitWait.visibleElement(driver, proposal_trading_No_of_cars_in_fleet, 10);
					
				 proposal_trading_No_of_cars_in_fleet.clear();
				 proposal_trading_No_of_cars_in_fleet.sendKeys("1");
				 
				 
				 
				 // 10
				 ExplicitWait.visibleElement(driver, proposal_trading_Number_of_vans_in_fleet, 10);
					
				 proposal_trading_Number_of_vans_in_fleet.clear();
				 proposal_trading_Number_of_vans_in_fleet.sendKeys("1");
				 
				 // 11
				 ExplicitWait.visibleElement(driver, proposal_trading_Number_of_trucks, 10);
					
				 proposal_trading_Number_of_trucks.clear();
				 proposal_trading_Number_of_trucks.sendKeys("1");
				 
				 
				 ////////////////////////////////////////////////////////Director////////////////////////////
				 
				// 1
				 ExplicitWait.visibleElement(driver, proposal_director_title, 10);
					
				 //proposal_trading_Number_of_trucks.clear();
				 //proposal_trading_Number_of_trucks.sendKeys("1");
				 
				 
				 
				 
				// 2
				 ExplicitWait.visibleElement(driver, proposal_director_first_name, 10);
					
				 proposal_director_first_name.clear();
				 proposal_director_first_name.sendKeys("QA comp2");
				 
				 
				 
					// 3
					 ExplicitWait.visibleElement(driver, proposal_director_surname, 10);
						
					 proposal_director_surname.clear();
					 proposal_director_surname.sendKeys("Test");
					 
					 
					// 4
					 ExplicitWait.visibleElement(driver, proposal_director_previous_name, 10);
						
					 proposal_director_previous_name.clear();
					 proposal_director_previous_name.sendKeys("Test");
					 
				 
				 
					// 5
					 ExplicitWait.visibleElement(driver, proposal_director_marital_status, 10);
						
					// proposal_director_marital_status.clear();
					// proposal_director_marital_status.sendKeys("Test");
					 
					// 6
					 ExplicitWait.visibleElement(driver, proposal_director_dob, 10);
						
				//	 proposal_director_dob.clear();
					// proposal_director_dob.sendKeys("Test");
					 
					 
					// 7
					 ExplicitWait.visibleElement(driver, proposal_director_No_of_dependents, 10);
						
					 proposal_director_No_of_dependents.clear();
					 proposal_director_No_of_dependents.sendKeys("0");
					 
					 
					// 8
					 ExplicitWait.visibleElement(driver, proposal_director_Home_number, 10);
						
					 proposal_director_Home_number.clear();
					 proposal_director_Home_number.sendKeys("12345678901");
					 
					// 9
					 ExplicitWait.visibleElement(driver, proposal_director_mobile_number, 10);
						
					 proposal_director_mobile_number.clear();
					 proposal_director_mobile_number.sendKeys("12345678901");
					
					 // 10
					 ExplicitWait.visibleElement(driver, proposal_director_email_address, 10);
						
					 proposal_director_email_address.clear();
					 proposal_director_email_address.sendKeys("mehul.nagar@autofacets.in");
					
					 
					 // 11
					// ExplicitWait.visibleElement(driver, proposal_director_post_code, 10);
						
					 //proposal_director_post_code.clear();
					 //proposal_director_post_code.sendKeys("LS122QH");
					 
					 
					// 12
					 ExplicitWait.visibleElement(driver, proposal_director_Time_at_address_years, 10);
						
					 proposal_director_Time_at_address_years.clear();
					 proposal_director_Time_at_address_years.sendKeys("3");
					
					 
					 // 13
					 ExplicitWait.visibleElement(driver, proposal_director_Time_at_address_months, 10);
						
					 proposal_director_Time_at_address_months.clear();
					 proposal_director_Time_at_address_months.sendKeys("0");
					
					 
					// 14
					 ExplicitWait.visibleElement(driver, proposal_director_Owner, 10);
						
					 //proposal_director_Owner.clear();
					 proposal_director_Owner.click();
					 
					 
					 
					// 16
					 ExplicitWait.visibleElement(driver, proposal_director_position_in_company, 10);
						
					 proposal_director_position_in_company.clear();
					 proposal_director_position_in_company.sendKeys("CEO");
					 
				 
					 
					// 17
					 ExplicitWait.visibleElement(driver, proposal_director_position_in_company, 10);
						
					 proposal_director_position_in_company.clear();
					 proposal_director_position_in_company.sendKeys("CEO");
	
					 
					// 18
					 ExplicitWait.visibleElement(driver, proposal_director_authorised_signatory, 10);
						
					 proposal_director_authorised_signatory.click();
					 
					 
					 // Bank Detail 
					 
					 //1
					 ExplicitWait.visibleElement(driver, proposal_bankdetail_exact_account_name, 10);
					 proposal_bankdetail_exact_account_name.clear();
					 proposal_bankdetail_exact_account_name.sendKeys("123456AA");
					 
					 
					//2
					 ExplicitWait.visibleElement(driver, proposal_bankdetail_sort_code, 10);
					 proposal_bankdetail_sort_code.clear();
					 proposal_bankdetail_sort_code.sendKeys("22-22-22");
					 
					 
					//3
					 ExplicitWait.visibleElement(driver, proposal_bankdetail_account_number, 10);
					 proposal_bankdetail_account_number.clear();
					 proposal_bankdetail_account_number.sendKeys("12345678");
					 
					 
					//4
					 ExplicitWait.visibleElement(driver, proposal_bankdetail_years, 10);
					 proposal_bankdetail_years.clear();
					 proposal_bankdetail_years.sendKeys("3");
					 
					//5
					 ExplicitWait.visibleElement(driver, proposal_bankdetail_months, 10);
					 proposal_bankdetail_months.clear();
					 proposal_bankdetail_months.sendKeys("1");
					 
					//6
					 ExplicitWait.visibleElement(driver, proposal_bankdetail_bank_name, 10);
					 proposal_bankdetail_bank_name.clear();
					 proposal_bankdetail_bank_name.sendKeys("TestAddress");
					
					 
					 
					 /////////////////////////Additional info////////////////////
					 
					//1
					 ExplicitWait.visibleElement(driver, proposal_additionalinfo_textbox, 10);
					 proposal_additionalinfo_textbox.clear();
					 proposal_additionalinfo_textbox.sendKeys("Test Automation  ");
					
					 
					//2
					 ExplicitWait.visibleElement(driver, proposal_additionalinfo_save_button, 10);
					 proposal_additionalinfo_save_button.click();
					 
					 
					 ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
					 
					//3
					// ExplicitWait.visibleElement(driver, proposal_update_button, 10);
					// proposal_update_button.click();
			
					
					//4
				  ExplicitWait.visibleElement(driver, proposal_update_and_exit_button, 10);
					 proposal_update_and_exit_button.click();
				
					 
					 
					 ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 80);
					 
					
					//5
					// ExplicitWait.visibleElement(driver, proposal_additionalinfo_cancel_button, 10);
					// proposal_additionalinfo_cancel_button.clear();
					
					
	
					 
					 
		 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public void Opp_listing_proposal_status() throws Exception

	{
	
		ExplicitWait.visibleElement(driver, opp_proposal_status, 60);
		
		
		String listing_proposal_status = opp_proposal_status.getText();
		
		  
         System.out.println("listing_proposal_status is = " +listing_proposal_status);
		LO.print("listing_proposal_status is = " +listing_proposal_status );
		
		
			
	}
	
	
	
	public void Opp_listing_proposal_fill_form_manually() throws Exception

	{
	
//		obj_Opportunities_Page = new Opportunities();
//		
//	
//		obj_Opportunities_Page.opp_menu_link();
//
//		obj_Opportunities_Page.opp_search_textbox();
		
		
		System.out.println("*****************Proposal Lisiting page will display ****************************");
		System.out.println("*********************************************");
		
		ExplicitWait.visibleElement(driver, Opp_send_proposal_to_customer_icon, 50);
		
		
		Opp_send_proposal_to_customer_icon.click();
		
		System.out.println("Send proposal to customer pop up  will open");
		LO.print("Send proposal to customer pop up  will open" );
		
		
		// Send proposal to customer will open 
		
		  
      ExplicitWait.visibleElement(driver, send_proposal_to_customer_pop_up_yes, 50);
		
		
     send_proposal_to_customer_pop_up_yes.click();
    
	System.out.println("Click on Manual fill radio button - Yes");
	LO.print("Click on Manual fill radio button - Yes" );
	
	
	// Click on manually submit behalf of customer link
	
	 ExplicitWait.visibleElement(driver, click_manually_submit_behalf_of_customer_link, 50);
	 click_manually_submit_behalf_of_customer_link.click();
    
	 System.out.println("Click on manually submit behalf of customer link");
		LO.print("Click on manually submit behalf of customer link" );
    
		
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 50);
		
		
		// Proposal page will open in new existing tab
		
		 ExplicitWait.visibleElement(driver, proposal_page_privacy_policy_checkbox, 30);
		 proposal_page_privacy_policy_checkbox.click();
		 
		 
		 ExplicitWait.visibleElement(driver, proposal_page_credit_search_checkbox, 30);
		 proposal_page_credit_search_checkbox.click();
		 
		
		 
		 
		 
		 
		 // click on submit button
		 
		 ExplicitWait.visibleElement(driver, proposal_page_submit, 30);
		 proposal_page_submit.click();
		 
		 //final approval  - yes - pls confirm ! pop up
		 ExplicitWait.visibleElement(driver, proposal_page_final_approval_confirmation, 30);
		 proposal_page_final_approval_confirmation.click();
		 
		 
		 System.out.println("*****************Proposal updated successfully****************************");
			System.out.println("*********************************************");
		 ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
			
	}
	
	
	
	public void Opp_listing_proposal_fill_form_manually_gettingvalue_from_screen() throws Exception

	{
	
		ExplicitWait.visibleElement(driver, Opp_send_proposal_to_customer_icon, 30);
		
		
		Opp_send_proposal_to_customer_icon.click();
		
		System.out.println("Send proposal to customer pop up  will open");
		LO.print("Send proposal to customer pop up  will open" );
		
		
		// Send proposal to customer will open 
		
		  
      ExplicitWait.visibleElement(driver, send_proposal_to_customer_pop_up_yes, 30);
		
		
     send_proposal_to_customer_pop_up_yes.click();
    
	System.out.println("Click on Manual fill radio button - Yes");
	LO.print("Click on Manual fill radio button - Yes" );
	
	
	// Click on manually submit behalf of customer link
	
	 ExplicitWait.visibleElement(driver, click_manually_submit_behalf_of_customer_link, 30);
	 click_manually_submit_behalf_of_customer_link.click();
    
	 System.out.println("Click on manually submit behalf of customer link");
		LO.print("Click on manually submit behalf of customer link" );
    
		
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		
		// Proposal page will open in new existing tab
		
		 ExplicitWait.visibleElement(driver, proposal_page_privacy_policy_checkbox, 30);
		 proposal_page_privacy_policy_checkbox.click();
		 
		 
		 ExplicitWait.visibleElement(driver, proposal_page_credit_search_checkbox, 30);
		 proposal_page_credit_search_checkbox.click();
		 
		
		 
		 
		 
		 
		 // click on submit button
		 
		 ExplicitWait.visibleElement(driver, proposal_page_submit, 30);
		 proposal_page_submit.click();
		 
		 //final approval  - yes - pls confirm ! pop up
		 ExplicitWait.visibleElement(driver, proposal_page_final_approval_confirmation, 30);
		 proposal_page_final_approval_confirmation.click();
		 
		 ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
			
	}
	
	
	
	
	public void Opp_listing_proposal_get_the_value_from_screen() throws Exception

	{
	
		ExplicitWait.visibleElement(driver, Opp_send_proposal_to_customer_icon, 30);
		
		
		Opp_send_proposal_to_customer_icon.click();
	
	}
	
	
	public void proposal_Add_Individual_info() throws Exception

	{

		
		Thread.sleep(3000);
		//1.proposal_indvidual_first_name 
		ExplicitWait.visibleElement(driver, proposal_indvidual_first, 60);
		
		proposal_indvidual_first.clear();
		proposal_indvidual_first.sendKeys("QA");

	
		
		/*
		 * //2.proposal_indvidual_surname
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_surname, 20);
		 * proposal_indvidual_surname.clear();
		 * proposal_indvidual_surname.sendKeys("Test");
		 * 
		 * 
		 * 
		 * //3.Previous/maiden name
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_previous_name, 20);
		 * proposal_indvidual_previous_name.clear();
		 * proposal_indvidual_previous_name.sendKeys("Test");
		 * 
		 * 
		 * 
		 * // 4.Gender
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_gender, 20);
		 * //proposal_indvidual_gender.clear(); proposal_indvidual_gender.click();
		 * 
		 * 
		 * 
		 * // 5.Nationality
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_nationality, 20);
		 * Dropdown.selectByVisibleText(driver, proposal_indvidual_nationality,
		 * "British", 10);
		 * 
		 * 
		 * 
		 * 
		 * //6.British passport holder
		 * 
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver,
		 * proposal_indvidual_british_passport_holder, 20);
		 * proposal_indvidual_british_passport_holder.click();
		 * 
		 * 
		 * 
		 * //7.DOB
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_dob, 20);
		 * 
		 * proposal_indvidual_dob.click();
		 * proposal_indvidual_dob.sendKeys("01/01/2000");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //8.Marital Status
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_Marital_status, 20);
		 * Dropdown.selectByVisibleText(driver, proposal_indvidual_Marital_status,
		 * "Single", 10);
		 * 
		 * 
		 * 
		 * 
		 * //9.No of dependents
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_No_of_dependents, 20);
		 * proposal_indvidual_No_of_dependents.clear();
		 * proposal_indvidual_No_of_dependents.sendKeys("0");
		 * 
		 * 
		 * //10.Home number
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_home_number, 20);
		 * proposal_indvidual_home_number.clear();
		 * proposal_indvidual_home_number.sendKeys("09687002161");
		 * 
		 * 
		 * 
		 * 
		 * //11.Work number
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_work_number, 20);
		 * 
		 * proposal_indvidual_work_number.clear();
		 * proposal_indvidual_work_number.sendKeys("09687002161");
		 * 
		 * 
		 * 
		 * //13.Mobile number
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_mobile_number, 20);
		 * 
		 * 
		 * proposal_indvidual_mobile_number.clear();
		 * 
		 * proposal_indvidual_mobile_number.sendKeys("09687022161");
		 * 
		 * 
		 * 
		 * //14.Email address
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_email_address, 20);
		 * proposal_indvidual_email_address.clear();
		 * proposal_indvidual_email_address.sendKeys("mehul.nagar@autofacets.in");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //15.Driving licence number
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver,
		 * proposal_indvidual_driving_licence_number, 20);
		 * proposal_indvidual_driving_licence_number.clear();
		 * proposal_indvidual_driving_licence_number.sendKeys("1234456677888822");
		 * 
		 * 
		 * 
		 * 
		 * //16.No of adults in household
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_no_of_adult, 20);
		 * proposal_indvidual_no_of_adult.clear();
		 * proposal_indvidual_no_of_adult.sendKeys("1");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //17.Post code
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_postcode, 30);
		 * 
		 * proposal_indvidual_postcode.clear(); Thread.sleep(1000);
		 * 
		 * proposal_indvidual_postcode.sendKeys("LS122QH");
		 * 
		 * proposal_indvidual_postcode.click();
		 * 
		 * //proposal_postcode.sendKeys(Keys.TAB);
		 * 
		 * Thread.sleep(2000); ExplicitWait.visibleElement(driver,
		 * proposal_indvidual_value, 30);
		 * 
		 * Thread.sleep(1000); proposal_indvidual_value.click();
		 * 
		 * 
		 * //18 Years
		 * 
		 * 
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_year, 10);
		 * proposal_indvidual_year.clear();
		 * 
		 * proposal_indvidual_year.sendKeys("3");
		 * 
		 * 
		 * //19 month
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_month, 10);
		 * 
		 * proposal_indvidual_month.clear(); proposal_indvidual_month.sendKeys("1");
		 * 
		 * 
		 * 
		 * //20 owner
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_owner, 10);
		 * //proposal_indvidual_owner.clear();
		 * 
		 * proposal_indvidual_owner.click();
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //EmpDetail
		 * 
		 * //1 EmpDetail_year
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_emp_detail_year, 10);
		 * 
		 * proposal_indvidual_emp_detail_year.clear();
		 * 
		 * proposal_indvidual_emp_detail_year.sendKeys("1");
		 * 
		 * 
		 * 
		 * //2 EmpDetail_month
		 * 
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_emp_detail_month, 10);
		 * 
		 * proposal_indvidual_emp_detail_month.clear();
		 * proposal_indvidual_emp_detail_month.sendKeys("1");
		 * 
		 * 
		 * // 3 EmpDetail_retired
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, proposal_indvidual_retired, 10);
		 * //proposal_indvidual_retired.clear(); proposal_indvidual_retired.click();
		 * 
		 * 
		 * 
		 * 
		 * // 3 Income
		 * 
		 * //1.Regular gross monthly salary
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, gross_monthly_salary, 10);
		 * 
		 * gross_monthly_salary.clear(); gross_monthly_salary.sendKeys("5000");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * // 2.Regular net monthly salary
		 * 
		 * ;
		 * 
		 * ExplicitWait.visibleElement(driver, net_monthly_salary, 10);
		 * net_monthly_salary.clear();
		 * 
		 * net_monthly_salary.sendKeys("3000");
		 * 
		 * 
		 * //3. Monthly mortgage/rent
		 * 
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, monthly_rent, 10); monthly_rent.clear();
		 * monthly_rent.sendKeys("2000");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //4 Bank Detail
		 * 
		 * 
		 * 
		 * 
		 * //1.Account
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, exact_account_name, 10);
		 * 
		 * exact_account_name.clear(); exact_account_name .sendKeys("101010");
		 * 
		 * 
		 * 
		 * //2.Sort code
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, sort_code, 10); sort_code.clear();
		 * sort_code.sendKeys("101010");
		 * 
		 * 
		 * //3.Account number
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, account_no, 10); account_no.clear();
		 * account_no.sendKeys("10101010");
		 * 
		 * 
		 * //4. Years
		 * 
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, years, 10); years.clear();
		 * years.sendKeys("1");
		 * 
		 * 
		 * //5.Months
		 * 
		 * 
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, months, 10); months.clear();
		 * months.sendKeys("1");
		 * 
		 * 
		 * //6.Bank Name
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, bank_name, 10); bank_name.clear();
		 * bank_name.sendKeys("Test Bank");
		 * 
		 * 
		 * //7 .Bank address
		 * 
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, bank_address, 10); bank_address.clear();
		 * bank_address.sendKeys("Bank Add");
		 * 
		 * 
		 * 
		 * //8.Additional info
		 * 
		 * 
		 * 
		 * 
		 * ExplicitWait.visibleElement(driver, additional_info, 10);
		 * additional_info.clear(); additional_info.sendKeys("Test");
		 * 
		 * 
		 * 
		 * //9.Save
		 * 
		 */	 
	  
	
		
		
			ExplicitWait.visibleElement(driver, proposal_save, 30);
			
			 proposal_save.click();
			 
			 ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
			 
			 //10.
			 
		
			    Thread.sleep(3000);
		
			ExplicitWait.visibleElement(driver,  proposal_udpate_and_exit , 30);
			
			  proposal_udpate_and_exit.click();	
		
			  ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
	}




}
