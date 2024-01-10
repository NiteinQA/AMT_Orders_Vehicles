package com.amt.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.api.RestClient;
import com.amt.api.pojo.OppStatusData;
import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Difference;
import com.amt.testUtil.Dropdown;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.HelperClass;
import com.amt.testUtil.ReadExcelCalculationForPurchaseAgreement;
import com.amt.testUtil.RemoveComma;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Opportunities extends TestBase {

	JavascriptExecutor js;
	
	AcquisitionListingPage obj_acq_listing_page;

	@FindBy(xpath = "//i[@class='icon-opportunity']")
	private WebElement opportunities;

	@FindBy(xpath = "//input[@placeholder='Search something here']")
	private WebElement search_bar;

	@FindBy(xpath = "//tr[@class='ng-star-inserted']//td[9]")
	private WebElement oppo_open_status;

	@FindBy(xpath = "//div[@class='status']")
	private WebElement oppo_proposal_status;

	@FindBy(xpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[8]/div/div/div/span")
	private WebElement channel_status;

	@FindBy(xpath = "(//td[@class='p-0']//tr[@tablerowclicked='thirdLevel'][1]/td[7])[1]")
	private WebElement contract_status;

	@FindBy(xpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr/td[1]/div/div")
	private WebElement quote_no;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//a[@title='Copy']//img")
	private WebElement copy_contract;

	@FindBy(xpath = "(//a[@title='Delete']//img)[4]")
	private WebElement delete_contract;

	@FindBy(xpath = "//*[@title='Send Contract']")
	private WebElement send_contract;

	@FindBy(xpath = "//*[@id='sendcontractmodal']/div/div/div[2]/div[2]/div[1]/div/div/div/label")
	private WebElement override;

	@FindBy(xpath = "//*[@id='EmailIDshare']")
	private WebElement email_input_field;

	@FindBy(xpath = "//*[@id='sendcontractmodal']/div/div/div[3]/div/button[2]")
	private WebElement send_email;

	@FindBy(xpath = "//input[@id='loginUsernameBox']")
	private WebElement customer_email_input_field;

	@FindBy(xpath = "//input[@id='loginPasswordBox']")
	private WebElement customer_password_input_field;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement email_login_button;

	@FindBy(xpath = "//*[@id='mail-section-controller']/div[1]/div[1]/div[1]/div[1]/ul/li[4]/a/span[1]")
	private WebElement junk_box_count;

	@FindBy(xpath = "//*[@id='mail-section-controller']/div[1]/div[1]/div[1]/div[1]/ul/li[4]/a")
	private WebElement email_Junk_box;

	@FindBy(xpath = "//*[@id='searchBoxText']")
	private WebElement email_search_field;

	@FindBy(xpath = "//*[@id='messageBody']/text()[5]")
	private WebElement email_doc_to_sign_link;

	@FindBy(xpath = "//i[@class='icon-opportunity']")
	private WebElement opportunity_menu_link;

	@FindBy(xpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[1]/div[1]/div[1]/div/div[1]/div/tag-input/div/div")
	private WebElement opportunity_search_text_box;

	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-opportunity-management[1]/div[2]/div[1]/div[1]/div[1]/app-grid[1]/div[2]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[7]")
	private WebElement opp_listing_double_click;

	@FindBy(xpath = "//input[@id='fleetCar']")
	private WebElement opp_opp_fact_find_no_of_cars;

	@FindBy(xpath = "//input[@id='fleetHgv']")
	private WebElement opp_opp_fact_find_no_of_hgv;

	@FindBy(xpath = "//input[@id='fleetLCV']")
	private WebElement opp_opp_fact_find_no_of_lcv;

	@FindBy(xpath = "//select[@id='carScheme']")
	private WebElement opp_opp_fact_find_no_of_carscheme;

	@FindBy(xpath = "//*[normalize-space()='Update']")
	private WebElement opp_opp_update_button;

	@FindBy(xpath = "//*[normalize-space()='Proposal']")
	private WebElement opp_proposal_button;
	
	@FindBy(xpath = "//*[normalize-space()='Documents']")
	private WebElement opp_documents_button;

	@FindBy(xpath = "//*[@id=\"cWraper\"]/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[8]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr/td[7]/div/div/div/span")
	private WebElement opp_find_channel_status;

	@FindBy(xpath = "//*[@id=\"cWraper\"]/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr/td[8]/div/a[4]")
	private WebElement opp_find_send_contract_icon;

	@FindBy(xpath = "//*[@id=\"sendcontractmodal\"]/div/div/div[3]/div/button[2]")
	private WebElement send_contract_to_customer_pop_up_send_button;

	// Channel data

	@FindBy(xpath = "//*[@id=\"cWraper\"]/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr/td")
	private List<WebElement> channel_data;

	// New status

	@FindBy(xpath = "//tr[@class='ng-star-inserted']//td[9]")
	private WebElement opp_current_status_open;

	@FindBy(xpath = "//*[@class='status']")
	private WebElement opp_current_status_proposal;

	@FindBy(xpath = "(//div[@class='ng-star-inserted']//span[contains(@class, 'btn')])[2]")
	private WebElement opp_current_status_channel;

	@FindBy(xpath = "(//div[@class='ng-star-inserted']//span[contains(@class, 'btn')])[2]")
	private WebElement opp_current_status_quoteref;

	// Add Appointment button

	@FindBy(xpath = "//button[normalize-space()='Add opportunity']")
	private WebElement opp_find_app_appointment_button;

	// Add customer

	@FindBy(xpath = "(//input[@id='customer'])[1]")
	private WebElement opp_general_assigned;

	// customer name

	@FindBy(xpath = "(//input[@id='customer'])[1]")
	private WebElement opp_general_cutomer;

	// copy code

	@FindBy(xpath = "//*[@id=\"cWraper\"]/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr/td[8]/div/a[1]")
	private WebElement opp_general_copy_code;

	// refresh
	@FindBy(xpath = "(//a[@title='Refresh'])[1]")
	private WebElement opp_general_refresh;

	// Share Quote
	@FindBy(xpath = "(//a[@title='Share Quote'])[1]")
	private WebElement opp_general_share_quote;

	// Delete
	@FindBy(xpath = "(//a[@title='Delete'])[3]")
	private WebElement opp_general_delete;

// Add Vehicle 

	@FindBy(xpath = "//*[@id=\"vehicleRequest2\"]/div/div/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div/table/tbody/tr/td[9]/div/a[3]/img")
	private WebElement add_new_vehicle_request_icon;

	// OPP Map New quote

	@FindBy(xpath = "//*[@id=\"vehicleRequest2\"]/div/div/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div/table/tbody/tr/td[9]/div/a[3]/img")
	private WebElement Map_New_quote_icon;
	// Map_New_quote_icon

	@FindBy(xpath = "//*[@id=\"MapNewQuote\"]/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[1]/div/ng-multiselect-dropdown/div/div[1]/span/span[1]")
	private WebElement acquisition_contract_type;

	@FindBy(xpath = "//*[@id=\"MapNewQuote\"]/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[1]/div/ng-multiselect-dropdown/div/div[2]/ul[2]/li")
	private WebElement acquisition_contract_type_broker_value;

	@FindBy(xpath = "//*[@id=\"MapNewQuote\"]/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[2]/div/ng-multiselect-dropdown/div/div[1]/span/span[1]")
	private WebElement customer_contract_type;

	@FindBy(xpath = "//*[@id=\"MapNewQuote\"]/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[2]/div/ng-multiselect-dropdown/div/div[2]/ul[2]/li[1]")
	private WebElement customer_business_contract_type_bch_value;

	@FindBy(xpath = "//*[@id=\"MapNewQuote\"]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/div/button[2]")
	private WebElement map_new_quote_search;

	@FindBy(xpath = "//th[contains(text(),'Created date')]")
	private WebElement map_new_quote_createddate_sorting;

	@FindBy(xpath = "//*[@id=\"MapNewQuote\"]/div/div/div[2]/div/div/div[2]/div/div[3]/div/div/table/tbody/tr[1]/td[1]")
	private WebElement select_new_quoted;

	@FindBy(xpath = "//*[@id=\"MapNewQuote\"]/div/div/div[3]/div/button[1]")
	private WebElement select_new_quoted_save;

	@FindBy(xpath = "//button[contains(text(),'Confirm')]")
	private WebElement lead_already_exits_quote_availbale;

	// *[@id="map_copy_quote"]/div/div/div[3]/div/button[1]

	// AlreadyexitsQuote = //button[normalize-space()='Confirm']

	@FindBy(xpath = "//*[@id=\"new_map_copy_quote\"]/div/div/div[3]/div/button[1]")
	private WebElement lead_already_exits_quote_availbale_second_popup_visible;

	@FindBy(xpath = "//*[@id=\"cWraper\"]/div/app-add-opportunities/app-opportunity-header/div/div[2]/div/button[3]")
	private WebElement opp_update_convert_into_opp_button;

	@FindBy(xpath = "//*[@id=\"cWraper\"]/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[1]/td[2]")
	private WebElement api_Opp_id_webelement;

	@FindBy(xpath = "//*[@id=\"cWraper\"]/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr/td[1]/div/div")
	private WebElement api_quote_ref_webelement;

	//

	@FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-opportunity-management[1]/app-underwriting-modal[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/app-uw-history[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/span[1]")
	private WebElement opp_underwriting_summary_accept;

	@FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-opportunity-management[1]/app-underwriting-modal[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/app-uw-history[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/span[1]")
	private WebElement opp_underwriting_summary_decline;

	@FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-opportunity-management[1]/app-underwriting-modal[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/app-uw-history[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[3]/td[2]/span[1]")
	private WebElement opp_underwriting_summary_refer;

	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-opportunity-management[1]/div[2]/div[1]/div[1]/div[1]/app-grid[1]/div[2]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/table[1]/tbody[1]/tr[2]/td[2]/table[1]/tbody[1]/tr[1]/td[8]/div[1]/a[6]")
	private WebElement underw_listing_create_order;

	// Opportunity Underwriting accept

	@FindBy(xpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[7]/div/div/div/span")
	private WebElement opp_current_status_quoteref1;

	@FindBy(xpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[7]/div/div/div/span")
	private WebElement opp_current_status_quoteref2;

	// API data - Need to add web element for Sent to cusotmer -- Decision - Accept
	// - Underwrinting with changes , so need to get value of sent to customer with
	// Array

	@FindBy(xpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[1]/div/div")
	private WebElement api_quote_ref_webelement2;

	// *[@id="cWraper"]/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[1]/div/div

	// Verification of Cancel flow

	// @FindBy(xpath
	// ="//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[24]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[")
	// private WebElement OwbbookStatusBeforexpath;

	// @FindBy(xpath ="]/td[7]/div/div/div/span")
	// private WebElement OwbbookStatusAfterxpath;

	@FindBy(xpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[4]/div/div")
	private WebElement new_quote_monthly_payment;

	@FindBy(xpath = "//p[contains(text(),'Holding cost')]")
	private WebElement underwriting_holding_cost_menu;

	@FindBy(xpath = "//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-holding-cost[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/app-acquisition-quote-summary[1]/div[1]/div[1]/button[1]/div[1]")
	private WebElement underwriting_holding_cost_summary;

	@FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-holding-cost[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/app-acquisition-quote-summary[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[5]/div[1]/p[1]/strong[1]")
	private WebElement total_cap_maint_value;

	@FindBy(xpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]")
	private WebElement underwriting_new_quote;

	@FindBy(xpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]")
	private WebElement underwriting_new_quote1;

	Properties prop;

	Opportunities obj_Opportunities_Page;

	public Opportunities() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"D:\\Orders_Vehicles\\AMT_Orders_Vehicles\\src\\main\\java\\configs\\excelValues.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PageFactory.initElements(driver, this);
	}

	/*
	 * Properties prop;
	 * 
	 * public Opportunities() { PageFactory.initElements(driver, this);
	 * 
	 * try { prop = new Properties(); FileInputStream ip = new FileInputStream(
	 * "D:\\StagingNew\\AMT_Automation\\src\\main\\java\\configs\\excelValues.properties"
	 * ); prop.load(ip); } catch (FileNotFoundException e) { e.printStackTrace(); }
	 * catch (IOException e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	public boolean search_and_verify_opportunity(String sheet_name) throws InterruptedException, IOException {
		Thread.sleep(3000);

		String oppoNo = GetExcelFormulaValue.get_cell_value(0, 1, sheet_name);

		Click.sendKeys(driver, search_bar, oppoNo, 30);

		Actions act = new Actions(driver);

		act.sendKeys(Keys.ENTER).build().perform();

		LO.print("Searched opportunity with Opportunity ref no.");
		System.out.println("Searched opportunity with Opportunity ref no.");

		ExplicitWait.visibleElement(driver, quote_no, 30);

		String quoteNoFromScreen = quote_no.getText().trim();

		String quoteNoFromExcel = GetExcelFormulaValue.get_cell_value(0, 0, sheet_name);

		boolean status = false;
		if (quoteNoFromScreen.equals(quoteNoFromExcel)) {
			status = true;
		}
		return status;
	}

	public boolean verify_current_status_of_opportunity_before_sending_to_proposal()

	{

		System.out.println(
				"*****************Before sending to proposal below is the current status ****************************");
		System.out.println("*********************************************");

		ExplicitWait.visibleElement(driver, opp_current_status_open, 20);
		ExplicitWait.visibleElement(driver, opp_current_status_proposal, 20);

		ExplicitWait.visibleElement(driver, opp_current_status_channel, 20);
		ExplicitWait.visibleElement(driver, opp_current_status_quoteref, 20);

		String OppCurrentStatusOpen = opp_current_status_open.getText().trim();

		String OppCurrentStatusProposal = opp_current_status_proposal.getText().trim();

		String oppCurrentStatusChannel = opp_current_status_channel.getText().trim();

		String oppCurrentStatusQuoteref = opp_current_status_quoteref.getText().trim();

		System.out.println("::::::::::::::::::::::::::::::Current Status :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		System.out.println("Current Status of Opportunity Status value is = " + OppCurrentStatusOpen);
		LO.print("Current Status of Opportunity Status value is = " + OppCurrentStatusOpen);

		System.out.println(" Current Status of Proposal value is = " + OppCurrentStatusProposal);
		LO.print("Current Status of Proposal value is = " + OppCurrentStatusProposal);

		System.out.println("Current Status of Channel value is =  " + oppCurrentStatusChannel);
		LO.print("Current Status of Channel value is =  " + oppCurrentStatusChannel);

		System.out.println(" Current Status of  Quote ref is = " + oppCurrentStatusQuoteref);
		LO.print("Current Status of  Quote ref is = " + oppCurrentStatusQuoteref);

		boolean CurrentStatusOpen = OppCurrentStatusOpen.equals("Open");

		boolean CurrentStatusProposal = OppCurrentStatusProposal.equals("Proposal Required");

		boolean CurrentStatusChannel = oppCurrentStatusChannel.equals("Draft");

		boolean CurrentStatusQuoteref = oppCurrentStatusQuoteref.equals("Draft");

		boolean flag = false;
		if (CurrentStatusOpen && CurrentStatusProposal && CurrentStatusChannel && CurrentStatusQuoteref)

		{

			flag = true;
		}
		System.out.println("********************************************");
		System.out.println("*********************************************");
		return flag;

	}

	public boolean verify_current_status_of_opportunity_after_sending_to_proposal()

	{

		System.out.println("*****************Opportunity after sending to proposal ****************************");
		System.out.println("*********************************************");

		ExplicitWait.visibleElement(driver, opp_current_status_open, 60);
		ExplicitWait.visibleElement(driver, opp_current_status_proposal, 60);

		ExplicitWait.visibleElement(driver, opp_current_status_channel, 60);
		ExplicitWait.visibleElement(driver, opp_current_status_quoteref, 60);

		String OppCurrentStatusOpen = opp_current_status_open.getText().trim();

		String OppCurrentStatusProposal = opp_current_status_proposal.getText().trim();

		String oppCurrentStatusChannel = opp_current_status_channel.getText().trim();

		String oppCurrentStatusQuoteref = opp_current_status_quoteref.getText().trim();

		System.out.println("::::::::::::::::::::::::::::::Current Status :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		System.out.println("Current Status of Opportunity Status value is = " + OppCurrentStatusOpen);
		LO.print("Current Status of Opportunity Status value is = " + OppCurrentStatusOpen);

		System.out.println(" Current Status of Proposal value is = " + OppCurrentStatusProposal);
		LO.print("Current Status of Proposal value is = " + OppCurrentStatusProposal);

		System.out.println("Current Status of Channel value is =  " + oppCurrentStatusChannel);
		LO.print("Current Status of Channel value is =  " + oppCurrentStatusChannel);

		System.out.println(" Current Status of  Quote ref is = " + oppCurrentStatusQuoteref);
		LO.print("Current Status of  Quote ref is = " + oppCurrentStatusQuoteref);

		boolean CurrentStatusOpen = OppCurrentStatusOpen.equals("Open");

		System.out.println(" CurrentStatusOpen = " + CurrentStatusOpen);

		boolean CurrentStatusProposal = OppCurrentStatusProposal.equals("Proposal Received");

		System.out.println(" CurrentStatusProposal = " + CurrentStatusProposal);

		boolean CurrentStatusChannel = oppCurrentStatusChannel.equals("Draft");

		System.out.println(" CurrentStatusChannel = " + CurrentStatusChannel);

		boolean CurrentStatusQuoteref = oppCurrentStatusQuoteref.equals("Draft");

		System.out.println(" CurrentStatusQuoteref = " + CurrentStatusQuoteref);

		boolean flag = false;
		if (CurrentStatusOpen && CurrentStatusProposal && CurrentStatusChannel && CurrentStatusQuoteref)

		{

			flag = true;
		}
		System.out.println("*********************************************");
		System.out.println("*********************************************");
		return flag;

	}

	public boolean verify_current_status_of_opportunity_after_sending_to_customer_contract() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		Thread.sleep(3000);

		// ExplicitWait.visibleElement(driver, opp_current_status_open, 20);
		ExplicitWait.visibleElement(driver, opp_current_status_proposal, 20);

		ExplicitWait.visibleElement(driver, opp_current_status_channel, 20);
		ExplicitWait.visibleElement(driver, opp_current_status_quoteref, 20);

		// String OppCurrentStatusOpen = opp_current_status_open.getText().trim();

		String OppCurrentStatusProposal = opp_current_status_proposal.getText().trim();

		String oppCurrentStatusChannel = opp_current_status_channel.getText().trim();

		String oppCurrentStatusQuoteref = opp_current_status_quoteref.getText().trim();

		System.out.println("::::::::::::::::::::::::::::::Current Status :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		// System.out.println("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);
		// LO.print("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);

		System.out.println(" Current Status of Proposal value is = " + OppCurrentStatusProposal);
		LO.print("Current Status of Proposal value is = " + OppCurrentStatusProposal);

		System.out.println("Current Status of Channel value is =  " + oppCurrentStatusChannel);
		LO.print("Current Status of Channel value is =  " + oppCurrentStatusChannel);

		System.out.println(" Current Status of  Quote ref is = " + oppCurrentStatusQuoteref);
		LO.print("Current Status of  Quote ref is = " + oppCurrentStatusQuoteref);

		// boolean CurrentStatusOpen = OppCurrentStatusOpen.equals(" ");

		// System.out.println(" CurrentStatusOpen = " + CurrentStatusOpen);

		boolean CurrentStatusProposal = OppCurrentStatusProposal.equals("Proposal Received");

		System.out.println(" CurrentStatusProposal = " + CurrentStatusProposal);

		boolean CurrentStatusChannel = oppCurrentStatusChannel.equals("Sent to customer");

		System.out.println(" CurrentStatusChannel = " + CurrentStatusChannel);

		boolean CurrentStatusQuoteref = oppCurrentStatusQuoteref.equals("Sent to customer");

		System.out.println(" CurrentStatusQuoteref = " + CurrentStatusQuoteref);

		boolean flag = false;
		if (CurrentStatusProposal && CurrentStatusChannel && CurrentStatusQuoteref)

		{

			flag = true;
		}

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

		return flag;

	}

	public boolean verify_status_of_opportunity() throws InterruptedException, IOException {
		ExplicitWait.visibleElement(driver, oppo_open_status, 30);

		ExplicitWait.visibleElement(driver, oppo_proposal_status, 30);

		ExplicitWait.visibleElement(driver, channel_status, 30);

		ExplicitWait.visibleElement(driver, contract_status, 30);

		String oppoOpenStatus = oppo_open_status.getText().trim();

		String oppoProposalStatus = oppo_proposal_status.getText().trim();

		String oppoChannelStatus = channel_status.getText().trim();

		String oppoContractStatus = contract_status.getText().trim();

		boolean openStatus = oppoOpenStatus.equals("Open");
		boolean proposalStatus = oppoProposalStatus.equals("Proposal Required");
		boolean channelStatus = oppoChannelStatus.equals("Draft");
		boolean contractStatus = oppoContractStatus.equals("Draft");

		boolean flag = false;
		if (openStatus && proposalStatus && channelStatus && contractStatus) {
			flag = true;
		}

		return flag;

	}

	public boolean verify_contract_is_copied(String sheet_name) throws InterruptedException, IOException

	{
		Click.on(driver, copy_contract, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		String oppoNo = GetExcelFormulaValue.get_cell_value(0, 1, sheet_name);

		Thread.sleep(3000);

		Click.sendKeys(driver, search_bar, oppoNo, 30);

		Actions act = new Actions(driver);

		act.sendKeys(Keys.ENTER).build().perform();

		Thread.sleep(3000);

		List<WebElement> copy_icons = driver.findElements(By.xpath("//a[@title='Copy']//img"));

		ExplicitWait.waitForListOfVisibleElements(driver, copy_icons, 20);

		System.out.println("Copy icons present on screen are " + copy_icons.size());

		boolean status = false;
		if (copy_icons.size() > 1) {
			status = true;
		}

		return status;

	}

	public boolean verify_copied_contract_is_deleted(String sheet_name) throws IOException, InterruptedException {
		Thread.sleep(3000);

		String oppoNo = GetExcelFormulaValue.get_cell_value(0, 1, sheet_name);

		Click.on(driver, delete_contract, 30);

		Actions act = new Actions(driver);

		Thread.sleep(3000);

		act.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER).build().perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.sendKeys(driver, search_bar, oppoNo, 30);

		act.sendKeys(Keys.ENTER).build().perform();

		List<WebElement> copy_icons = driver.findElements(By.xpath("//a[@title='Copy']//img"));

		boolean status = false;
		if (copy_icons.size() == 1) {
			status = true;
		}

		return status;

	}

	public boolean verify_contract_is_sent_to_mail(String sheet_name, String customer_email_id)
			throws InterruptedException {
		Click.on(driver, send_contract, 30);

		Thread.sleep(4000);

		js = (JavascriptExecutor) driver;

		ExplicitWait.clickableElement(driver, override, 20);

		js.executeScript("arguments[0].click();", override);

		ExplicitWait.clickableElement(driver, email_input_field, 20);

		email_input_field.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

		Click.sendKeys(driver, email_input_field, customer_email_id, 30);

		Click.on(driver, send_email, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		ExplicitWait.visibleElement(driver, channel_status, 30);

		ExplicitWait.visibleElement(driver, contract_status, 30);

		String oppoChannelStatus = channel_status.getText().trim();

		String oppoContractStatus = contract_status.getText().trim();

		boolean channelStatus = oppoChannelStatus.equals("Sent to customer");
		boolean contractStatus = oppoContractStatus.equals("Sent to customer");

		boolean flag = false;
		if (channelStatus && contractStatus) {
			flag = true;
		}

		return flag;

	}

	public void verify_mail_is_received(String sheet_name, String customer_email_id, String password)
			throws InterruptedException {

		WebDriver newEmailTab = driver.switchTo().newWindow(WindowType.TAB);

		newEmailTab.get("https://mail.thegatewaydigital.com/interface/root#/login");

		Set<String> handles = driver.getWindowHandles();

		Thread.sleep(3000);

		Click.sendKeys(newEmailTab, customer_email_input_field, customer_email_id, 30);

		Click.sendKeys(newEmailTab, customer_password_input_field, password, 30);

		Thread.sleep(6000);

		Click.on(newEmailTab, email_login_button, 30);

		Click.on(newEmailTab, email_Junk_box, 30);

		ExplicitWait.visibleElement(newEmailTab, junk_box_count, 30);

		double junkBoxCount = Double.parseDouble(junk_box_count.getText());
		double junkBoxCount1 = Double.parseDouble(junk_box_count.getText());

		while (junkBoxCount == junkBoxCount1) {
			newEmailTab.navigate().refresh();
			ExplicitWait.visibleElement(newEmailTab, junk_box_count, 30);
			Thread.sleep(2000);
			double junkBoxCount2 = Double.parseDouble(junk_box_count.getText());
			junkBoxCount = junkBoxCount2;
		}

		Thread.sleep(3000);

		Click.sendKeys(newEmailTab, email_search_field, "AMT Auto has sent you a document to sign", 30);

		Actions act = new Actions(driver);

		Thread.sleep(3000);

		act.sendKeys(Keys.ENTER).build().perform();

		ExplicitWait.visibleElement(newEmailTab, email_doc_to_sign_link, 30);

		String doc_sign_link = email_doc_to_sign_link.getText();

		WebDriver newDocTab = newEmailTab.switchTo().newWindow(WindowType.TAB);

		newDocTab.get(doc_sign_link);

	}

	////////////////////////////////// Assertion for Opportunity
	////////////////////////////////// pages///////////////////

	public void opp_menu_link() throws Exception

	{

		System.out.println("**********************Opportunity page will display ***********************");

		System.out.println("*********************************************");
		System.out.println("*********************************************");

		// Thread.sleep(3000);

		// Click on Search text box of Opportunity
		Click.on(driver, opportunity_menu_link, 30);

		LO.print("Clicked on Opportunity link from Menu ");
		System.out.println("Clicked on Opportunity link from Menu");
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		// Click.sendKeys(driver, Lead_OpportunityId, null, 10);

	}

	public void opp_search_textbox() throws Exception

	{

		System.out.println("**************Searching for Opportunity id in search text box*******************************");
		System.out.println("*********************************************");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		
		
		//Getting oppo ID from excel 		
        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();

        System.out.println(classOrMethodName);
        
		obj_acq_listing_page = new AcquisitionListingPage();
		
		String sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		

		String opportunityId = GetExcelFormulaValue.get_cell_value(1, 2, sheetName);		
		

		ExplicitWait.visibleElement(driver, search_bar, 30);	
		search_bar.sendKeys(opportunityId);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		
		System.out.println("Enter the Opportunity id in Search Text box");
		LO.print          ("Enter the Opportunity id in Search Text box");

		search_bar.sendKeys(Keys.ENTER);

		// Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	}

	
	public void search_opportunity_to_create_order() throws Exception

	{

		System.out.println("**************Searching for Opportunity id in search text box*******************************");
		System.out.println("*********************************************");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);


		
		String opportunityId = GetExcelFormulaValue.get_cell_value(4, 0, "Order_ID");		
		

		ExplicitWait.visibleElement(driver, search_bar, 50);	
		search_bar.sendKeys(opportunityId);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		
		System.out.println("Enter the Opportunity id in Search Text box");
		LO.print          ("Enter the Opportunity id in Search Text box");

		search_bar.sendKeys(Keys.ENTER);

		// Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	}

	
	public boolean opp_verify_monthly_payment() throws Exception {

		String GetOpportunityid = GetExcelFormulaValue.get_cell_value(1, 2, prop.getProperty("HPNR_HPNR_QuoteNo"));

		double monthlyPaymentExpected = GetExcelFormulaValue.get_string_value(1, 1,
				prop.getProperty("HPNR_HPNR_QuoteNo"));

		obj_Opportunities_Page = new Opportunities();

		obj_Opportunities_Page.opp_search_textbox();

		List<WebElement> li = driver.findElements(By.xpath("(//*[@class='table heading-hvr opportunitytable ng-star-inserted']//table)[2]//thead//th"));

		int index = li.indexOf(By.xpath("//th[normalize-space()='MONTHLY PAYMENT']"));

		double monthlyPaymentActual = Double.parseDouble(RemoveComma.of(driver.findElement(By.xpath("((//*[@class='table heading-hvr opportunitytable ng-star-inserted']//table)[2]//tbody//td)["
						+ index + "]")).getText()));

		boolean flag = false;

		if (Difference.of_two_Double_Values(monthlyPaymentExpected, monthlyPaymentActual) < 0.1) {
			flag = true;
		}

		return flag;
	}

	public String[] get_api_data_opp() throws InterruptedException

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		ExplicitWait.visibleElement(driver, api_Opp_id_webelement, 20);
		String opp_id_screen = api_Opp_id_webelement.getText();
		System.out.println("opp_id_screen" + opp_id_screen);

		ExplicitWait.visibleElement(driver, api_quote_ref_webelement, 20);
		String quote_ref_screen = api_quote_ref_webelement.getText();
		System.out.println("quote_id_screen" + quote_ref_screen);

		String[] DataAPI = new String[2];

		DataAPI[0] = opp_id_screen;
		DataAPI[1] = quote_ref_screen;
		return DataAPI;

	}

	public String[] get_api_data_opp_after_decision_underwriting_accpet_the_changes() throws InterruptedException

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		ExplicitWait.visibleElement(driver, api_Opp_id_webelement, 20);
		String opp_id_screen = api_Opp_id_webelement.getText();
		System.out.println("opp_id_screen" + opp_id_screen);

		// ExplicitWait.visibleElement(driver, api_quote_ref_webelement2, 20);

		// Same for quote ref sent to customer

		String OwbbookStatusBeforexpath = "//*[@id=\"cWraper\"]/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[";

		String OwbbookStatusAfterxpath = "]/td[7]/div/div/div/span";
		String QuoteRef_new = null;

		for (int i = 1; i <= 2; i++)

		{
			String StausValuefromscreen = driver
					.findElement(By.xpath(OwbbookStatusBeforexpath + i + OwbbookStatusAfterxpath)).getText();

			// String StausValuefromscreen = status.gettext();

			if (StausValuefromscreen.equals("Sent to customer"))

			{

				String QuoteBerofexpath = "//*[@id=\"cWraper\"]/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[";

				String QuoteAfterXpath = "]/td[1]";

				QuoteRef_new = driver.findElement(By.xpath(QuoteBerofexpath + i + QuoteAfterXpath)).getText();
				System.out.println("QuoteRef_new " + QuoteRef_new);

			}
		}

		String quote_ref_screen2 = api_quote_ref_webelement2.getText();
		System.out.println("quote_id_screen" + quote_ref_screen2);

		String[] DataAPI = new String[2];

		DataAPI[0] = opp_id_screen;
		DataAPI[1] = QuoteRef_new;
		return DataAPI;

	}

	public boolean get_new_quote_price_after_underwriting_accepted() throws InterruptedException, ClassNotFoundException, IOException

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		String OwbbookStatusBeforexpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[";

		String OwbbookStatusAfterxpath = "]/td[7]/div/div/div/span";

		String QuoteRef_new = null;
		String MonthlyPayment_new = null;

		String QuoteRef_cancelled = null;
		String MonthlyPayment_cancelled = null;

		for (int i = 1; i <= 2; i++)

		{
			String StausValuefromscreen = driver
					.findElement(By.xpath(OwbbookStatusBeforexpath + i + OwbbookStatusAfterxpath)).getText();

			// String StausValuefromscreen = status.gettext();

			if (StausValuefromscreen.equals("Underwriting accepted"))

			{

				String QuoteBerofexpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[";

				String QuoteAfterXpath = "]/td[1]";

				QuoteRef_new = driver.findElement(By.xpath(QuoteBerofexpath + i + QuoteAfterXpath)).getText();
				System.out.println("New Quote ref in Opportunity " + QuoteRef_new);
				LO.print("New Quote ref in Opportunity " + QuoteRef_new);

			}

			// Monthly Payment value from screen

			if (StausValuefromscreen.equals("Underwriting accepted"))

			{

				String MonthlyPriceBerofexpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[";

				String MonthlyPriceAfterxpath = "]/td[4]/div/div";

				MonthlyPayment_new = driver
						.findElement(By.xpath(MonthlyPriceBerofexpath + i + MonthlyPriceAfterxpath)).getText();
				System.out.println("New Quote Monthly Payment is  " + MonthlyPayment_new);

				LO.print("New Quote Monthly Payment is  = " + MonthlyPayment_new);

			}

			//

			if (StausValuefromscreen.equals("Cancelled"))

			{

				String QuoteBerofexpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[";

				String QuoteAfterXpath = "]/td[1]";

				QuoteRef_new = driver.findElement(By.xpath(QuoteBerofexpath + i + QuoteAfterXpath)).getText();
				System.out.println("Cancelled Quote ref in Opportunity is = " + QuoteRef_cancelled);

				LO.print("Cancelled Quote ref in Opportunity is = " + QuoteRef_cancelled);

			}

			if (StausValuefromscreen.equals("Cancelled"))

			{

				String MonthlyPriceBerofexpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[";

				String MonthlyPriceAfterxpath = "]/td[4]/div/div";

				MonthlyPayment_cancelled = driver
						.findElement(By.xpath(MonthlyPriceBerofexpath + i + MonthlyPriceAfterxpath)).getText();
				System.out.println(" Cancelled Monthly Payment value is = " + MonthlyPayment_cancelled);
				LO.print("Cancelled Monthly Payment value is = " + MonthlyPayment_cancelled);
			}

		}
		
		
        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		obj_acq_listing_page = new AcquisitionListingPage();		
		String sheetName = obj_acq_listing_page.calculation_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		double MonthlyPayment_new_Expected =0;
		
		if(classOrMethodName.contains("hire"))
		{
		MonthlyPayment_new_Expected = GetExcelFormulaValue.get_formula_value(90, 1, sheetName);
		}
		
		if(classOrMethodName.contains("hire_funder"))
		{
		MonthlyPayment_new_Expected = GetExcelFormulaValue.get_formula_value(96, 1, sheetName);
		}
		
		
		if(classOrMethodName.contains("purchase"))
		{
		MonthlyPayment_new_Expected = GetExcelFormulaValue.get_formula_value(95, 1, sheetName);
		}
		
		double MonthlyPayment_new_Actual = Double.parseDouble(RemoveComma.of(MonthlyPayment_new.trim().substring(2)).replace("ex. VAT", "").replace("inc. VAT", ""));
		
		boolean flag = false;
		if(Difference.of_two_Double_Values(MonthlyPayment_new_Expected, MonthlyPayment_new_Actual)<0.2)
		{
			flag = true;
			
	        // ANSI escape code for green color
	        String ansiGreen = "\u001B[32m";
	        
	        // ANSI escape code to reset the console color
	        String ansiReset = "\u001B[0m";
	        
			System.out.println(ansiGreen+"Monthly Payment verified (found ok) after accepting new quote, Actual value is "+MonthlyPayment_new_Actual+" and Expected value is "+MonthlyPayment_new_Expected+ansiReset);
			LO.print          (ansiGreen+"Monthly Payment verified (found ok) after accepting new quote, Actual value is "+MonthlyPayment_new_Actual+" and Expected value is "+MonthlyPayment_new_Expected+ansiReset);
			
		}
		else
		{
			System.err.println("Monthly Payment verified (found wrong) after accepting new quote, Actual value is "+MonthlyPayment_new_Actual+" and Expected value is "+MonthlyPayment_new_Expected);
			LO.print          ("Monthly Payment verified (found wrong) after accepting new quote, Actual value is "+MonthlyPayment_new_Actual+" and Expected value is "+MonthlyPayment_new_Expected);

		}
		
		return flag;
		
}

	public double fetching_the_new_quote_monthly_payment_sending_to_excel() throws IOException, InterruptedException {
		// Click.on(driver, new_quote_monthly_payment, 30);

		System.out.println("Page title of active tab: " + driver.getTitle());
		LO.print("Page title of active tab: " + driver.getTitle());

		// ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		ExplicitWait.visibleElement(driver, new_quote_monthly_payment, 30);

		String temp_new_quote_monthly_payment = new_quote_monthly_payment.getText().trim().substring(2);

		System.out.println(" new_quote_monthly_payment = " + temp_new_quote_monthly_payment);

		LO.print("new_quote_monthly_payment =" + temp_new_quote_monthly_payment);

		double screen_new_quote_monthly_payment_converted = Double.parseDouble(temp_new_quote_monthly_payment);

		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet("HPNR_HPNR_QuoteNo").getRow(0).getCell(3).setCellValue(screen_new_quote_monthly_payment_converted);

		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		wb.write(out);
		wb.close();
		return screen_new_quote_monthly_payment_converted;

	}

	public double set_the_orderdepositandcashdeposit_in_excelsheet(double screen_total_cap_maint_value1)
			throws IOException, InterruptedException {

		// Set the customer quote data in excel sheet

		LO.print("Writing screen values to Excel for customer quote calculation -started");
		System.out.println("Writing screen values to Excel for customer quote calculation -started");

		FileInputStream in = new FileInputStream(prop.getProperty("formula_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		Thread.sleep(2000);

		wb.getSheet("HirePurchaseNonRegulated").getRow(104).getCell(4).setCellValue("YES");
		wb.getSheet("HirePurchaseNonRegulated").getRow(110).getCell(1).setCellValue(5000);
		wb.getSheet("HirePurchaseNonRegulated").getRow(110).getCell(4).setCellValue(5000);

		Thread.sleep(2000);

		wb.getSheet("HirePurchaseNonRegulated").getRow(31).getCell(11).setCellValue(screen_total_cap_maint_value1);

		Thread.sleep(2000);
		FileOutputStream out = new FileOutputStream(prop.getProperty("formula_excel_path"));
		wb.write(out);
		out.close();

		LO.print("Writing screen values to Excel for customer quote calculation -completed");
		System.out.println("Writing screen values to Excel for customer quote calculation -completed");

		return GetExcelFormulaValue.get_formula_value(95, 1, "HirePurchaseNonRegulated");

	}

	public double open_the_underwriting_accepted_get_the_holdingcost_total_cap_maint_value()
			throws InterruptedException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 40);

		/*
		 * String OwbbookStatusBeforexpath =
		 * "//*[@id=\"cWraper\"]/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[";
		 * 
		 * String OwbbookStatusAfterxpath = "]/td[7]/div/div/div/span";
		 * 
		 * String QuoteRef_new = null; String MonthlyPayment_web_1 = null;
		 * 
		 * String QuoteRef_new = null; String MonthlyPayment_cancelled = null;
		 * 
		 * double screen_total_cap_maint_value =0;
		 * 
		 * for (int i = 1; i <= 2; i++)
		 * 
		 * { String StausValuefromscreen = driver
		 * .findElement(By.xpath(OwbbookStatusBeforexpath + i +
		 * OwbbookStatusAfterxpath)).getText();
		 * 
		 * // String StausValuefromscreen = status.gettext();
		 * 
		 * if (StausValuefromscreen.equals("Underwriting accepted"))
		 * 
		 * {
		 * 
		 * String QuoteBerofexpath =
		 * "//*[@id=\"cWraper\"]/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[";
		 * 
		 * String QuoteAfterXpath = "]";
		 * 
		 * //QuoteRef_new = driver.findElement(By.xpath(QuoteBerofexpath + i +
		 * QuoteAfterXpath)).getText();
		 * System.out.println("New Quote ref in Opportunity " + QuoteRef_new);
		 * LO.print("New Quote ref in Opportunity " + QuoteRef_new);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * WebElement Quoteref_web = driver.findElement(By.xpath(QuoteBerofexpath + i +
		 * QuoteAfterXpath));
		 */

		ExplicitWait.visibleElement(driver, underwriting_new_quote, 20);

		if (underwriting_new_quote.isEnabled()) {
			Actions ac = new Actions(driver);

			ac.doubleClick(underwriting_new_quote).build().perform();

			{

				ac.doubleClick(underwriting_new_quote1).build().perform();

			}

		}

		/*
		 * System.out.println("Click on underwriting page");
		 * 
		 * 
		 * 
		 * 
		 * //Click.on(driver, underwriting_new_quote, 20);
		 * 
		 * Actions ac = new Actions(driver);
		 * 
		 * ac.doubleClick(underwriting_new_quote).build().perform();
		 */
		ArrayList<String> browserwindow = new ArrayList<String>(driver.getWindowHandles());
		// switch to active tab
		driver.switchTo().window(browserwindow.get(1));

		System.out.println("Page title of active tab: " + driver.getTitle());
		LO.print("Page title of active tab: " + driver.getTitle());

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 40);

		Click.on(driver, underwriting_holding_cost_menu, 20);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 40);
		Click.on(driver, underwriting_holding_cost_summary, 20);

		ExplicitWait.visibleElement(driver, total_cap_maint_value, 30);

		//
		Thread.sleep(2000);

		String temp_total_cap_maint_value = RemoveComma.of(total_cap_maint_value.getText().trim().substring(2));
		;

		System.out.println(" temp_total_cap_maint_value = " + temp_total_cap_maint_value);

		LO.print("temp_total_cap_maint_value =" + temp_total_cap_maint_value);

		Thread.sleep(2000);

		double screen_total_cap_maint_value = Double.parseDouble(temp_total_cap_maint_value);

		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		Thread.sleep(2000);
		wb.getSheet("HPNR_HPNR_QuoteNo").getRow(0).getCell(4).setCellValue(screen_total_cap_maint_value);

		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		wb.write(out);
		wb.close();

		Thread.sleep(2000);
		/*
		 * ArrayList<String> browserwindow1 = new
		 * ArrayList<String>(driver.getWindowHandles()); //switch to active tab
		 * driver.switchTo().window(browserwindow1.get(0));
		 */

		return screen_total_cap_maint_value;

	}

	/*
	 * obj_read_excel_calculation_page = new
	 * ReadExcelCalculationForPurchaseAgreement();
	 * 
	 * double[]monthlyFinanceAndBalanceToFinance = obj_read_excel_calculation_page.
	 * get_monthly_total_payment_and_balance_to_finance_payment_after_editing_part_exchange_values
	 * (part_exchange_actual,part_exchange_given,less_finance_settlement,
	 * order_deposit,finance_deposit,document_fee_copied, sheet_name );
	 * 
	 * 
	 * double monthly_total_payment_expected = monthlyFinanceAndBalanceToFinance[0];
	 * double balance_to_finance_expected = monthlyFinanceAndBalanceToFinance[1];
	 * 
	 * double diff1
	 * =Difference.of_two_Double_Values(balance_to_finance_value_from_screen,
	 * balance_to_finance_expected); double diff2
	 * =Difference.of_two_Double_Values(monthly_total_payment_actual_from_screen,
	 * monthly_total_payment_expected);
	 * 
	 * boolean status=false; if(diff1<0.2 && diff2<0.2) { status=true; LO.
	 * print("Monthly total Payment verified after editing part exchange values and deposit values"
	 * ); System.out.
	 * println("Monthly total Payment verified after editing part exchange values and deposit values"
	 * ); }
	 * 
	 * return status;
	 */

	public int postAPITest(String QuoteRef, String OppID)
			throws JsonGenerationException, JsonMappingException, IOException, ParseException, InterruptedException {
		RestClient restClient;
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		// headerMap.put("Authorization", "Bearer
		// eyJhbGciOiJSUzI1NiIsImtpZCI6IkxVd2l4OU1oRFN0LWJoRl9mWlZHbEEiLCJ0eXAiOiJhdCtqd3QifQ.eyJuYmYiOjE2NzUyNTYyMzAsImV4cCI6MTY3NTQyOTAzMCwiaXNzIjoiaHR0cHM6Ly9zdGFnaW5nYW10aWRlbnRpdHlzZXJ2ZXJhcGkuYXp1cmV3ZWJzaXRlcy5uZXQiLCJhdWQiOiJGbGVldEFQSUdhdGV3YXkiLCJjbGllbnRfaWQiOiJGbGVldEFQSUdhdGV3YXkiLCJzdWIiOiJkODYwZWZjYS0yMmQ5LTQ3ZmQtODI0OS03OTFiYTYxYjA3YzciLCJhdXRoX3RpbWUiOjE2NzUyNTYyMjksImlkcCI6ImxvY2FsIiwiUGVybWlzc2lvbnMiOiJBY3F1aXNpdGlvbiBtYW5hZ2VyI1N1cGVyIEFkbWluI0FjcXVpc2l0aW9uIHVzZXIjU2FsZXMgdXNlciNTYWxlcyBtYW5hZ2VyIiwibmFtZSI6InN1cGVyYWRtaW5AYW10dWsuY28udWsiLCJzY29wZSI6WyJGbGVldEFQSUdhdGV3YXkiXSwiYW1yIjpbInB3ZCJdfQ.QOtKa2-zkBzzvozNI8BdBbbCiBJ6BH65cm3sQg9fn3e0C936Xqu_UlngUDwJjAAfx_v5LQowtLIOD23MQbO661WvLK3xtufvQ9y2-lH0ZvCho-ziPB-YML63nnQnyZcAxNPrwmVL9RnG-uLpZ1fFEZRqiGm3w1MbWKhtU5R1oldNEPTmDm_TdKFwiynjfzLZtxJRcaweCV64wGxEq7u1_sS_qSSJTNw1RyGAciZE-egQV3-rACp9XWAW6e1aZozE-1t8g-Mv-eDCpY7hcTAVUVD1I3xHy_BHYxAe-kfdSDeWiYNmwk1q0HjQhNI-WPne9JcFGFUKVCB0vVv66Da-Aw");

		// jackson API:
		ObjectMapper mapper = new ObjectMapper();
		OppStatusData OppStatus = new OppStatusData(QuoteRef, OppID); // expected users object

		// object to json file:
		mapper.writeValue(new File("D:\\LOU\\AMT_LOU\\src\\main\\java\\com\\amt\\api\\pojo\\OppStatusData.json"),
				OppStatus);

		// java object to json in String:
		String usersJsonString = mapper.writeValueAsString(OppStatus);
		System.out.println(usersJsonString);
		CloseableHttpResponse closebaleHttpResponse;
		closebaleHttpResponse = restClient.post(prop.getProperty("APIURL"), usersJsonString, headerMap); // call the API

		System.out.println(prop.getProperty("APIURL"));
		
		// validate response from API:
		// 1. status code:
		// int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();

		int statusCode = closebaleHttpResponse.getCode();

		Thread.sleep(5000);
		
		int i=0;
		while(i<4)
		{
			
		driver.navigate().refresh();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);		
		
		try {
		ExplicitWait.visibleElement(driver, opp_current_status_quoteref, 50);
		}catch(Exception e) 
		{
			driver.navigate().refresh();
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);
			ExplicitWait.visibleElement(driver, opp_current_status_quoteref, 50);
		}
		
		String oppCurrentStatusQuoteref = opp_current_status_quoteref.getText().trim();
		if(oppCurrentStatusQuoteref.equals("Contract signed")||oppCurrentStatusQuoteref.equals("Underwriting accepted"))
		{
			break;
		}		
		i++;
		}
		return statusCode;

	}

	public void opp_listing_detail_page() throws Exception

	{

		System.out.println("*****************Opportunity Lisiting page ****************************");
		System.out.println("*********************************************");
		
		obj_Opportunities_Page = new Opportunities() ;
		
//		obj_Opportunities_Page.opp_search_textbox();

		ExplicitWait.visibleElement(driver, opp_listing_double_click, 50);

		Actions act = new Actions(driver);

		// Double click on element

		act.doubleClick(opp_listing_double_click).build().perform();

		System.out.println("*************Entered into opportunity deatail page********************************");
		System.out.println("*********************************************");
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 50);

	}

	public void opp_listing_detail_update_individual() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_opp_update_button, 30);

		opp_opp_update_button.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// proposal link


		ExplicitWait.visibleElement(driver, opp_documents_button, 30); 
		
		opp_documents_button.click();
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		

		ExplicitWait.visibleElement(driver, opp_proposal_button, 20);

		opp_proposal_button.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		LO.print("click on Proposal icon");
		System.out.println("click on Proposal icon");

	}

	public void opp_opp_fact_find() throws Exception

	{

		ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_cars, 50);

		opp_opp_fact_find_no_of_cars.sendKeys("1");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 50);

		// Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_hgv, 50);

		opp_opp_fact_find_no_of_hgv.sendKeys("1");

		ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_lcv, 50);

		opp_opp_fact_find_no_of_lcv.sendKeys("1");

		// ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_carscheme, 10);

		Dropdown.selectByVisibleText(driver, opp_opp_fact_find_no_of_carscheme, " No ", 30);

		opp_opp_fact_find_no_of_carscheme.click();

		ExplicitWait.visibleElement(driver, opp_opp_update_button, 30);

		opp_opp_update_button.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// proposal link

		// Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_proposal_button, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		opp_proposal_button.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		LO.print("click on Proposal icon");
		System.out.println("click on Proposal icon");

	}

	public void opp_opp_fact_find_oppurtunity_flow() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		// Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_cars, 20);

		opp_opp_fact_find_no_of_cars.sendKeys("1");

		// Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_hgv, 30);

		opp_opp_fact_find_no_of_hgv.sendKeys("1");

		ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_lcv, 10);

		opp_opp_fact_find_no_of_lcv.sendKeys("1");

		// ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_carscheme, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Dropdown.selectByVisibleText(driver, opp_opp_fact_find_no_of_carscheme, " No ", 30);

		opp_opp_fact_find_no_of_carscheme.click();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

	}

	public void opp_find_channel_status() throws Exception

	{

		ExplicitWait.visibleElement(driver, opp_find_channel_status, 10);

		String Find_Channel_Status = opp_find_channel_status.getText();

		LO.print("Channel_Status =" + Find_Channel_Status);
		System.out.println("Channel_Status" + Find_Channel_Status);

	}

	public void opp_find_send_contract_icon() throws Exception

	{

		// Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		try{ExplicitWait.visibleElement(driver, opp_find_send_contract_icon, 10);}
		catch(Exception e) { driver.navigate().refresh();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		ExplicitWait.visibleElement(driver, opp_find_send_contract_icon, 10);
		}

		LO.print("click on Send_Contract icon");
		System.out.println("click on  Send_Contract icon");

		opp_find_send_contract_icon.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		ExplicitWait.visibleElement(driver, send_contract_to_customer_pop_up_send_button, 60);

		send_contract_to_customer_pop_up_send_button.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);



	}

	public void opp_verify_channel_data() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		ExplicitWait.waitForListOfVisibleElements(driver, channel_data, 20);

		String[] data = new String[channel_data.size()];

		System.out.println("size  =" + channel_data.size());

		for (int i = 0; i <= channel_data.size() - 1; i++)

		{

			data[i] = channel_data.get(i).getText();

			// System.out.println("Data =" + data[i]);

//	System.out.println("Data =" + data[i]);

		}

		System.out.println("Quote Ref => " + data[0]);
		LO.print("Quote Ref => " + data[0]);

		System.out.println("Vehicle => " + data[1]);
		LO.print("Vehicle => " + data[1]);

		System.out.println("Contract type => " + data[2]);
		LO.print("Contract type => " + data[2]);

		System.out.println("Mothly Payment => " + data[3]);
		LO.print("Mothly Payment => " + data[3]);

		System.out.println("Term/Mileage =>" + data[4]);
		LO.print("Term/Mileage =>" + data[4]);

		System.out.println("Expiry Date =>" + data[5]);
		LO.print("Expiry Date =>" + data[5]);

		System.out.println("Status =>" + data[6]);
		LO.print("Status =>" + data[6]);

	}

	// Add Appointment from

	public void opp_add_appoitment_button() throws Exception

	{
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_find_app_appointment_button, 10);

		LO.print("click on Add  Appointment icon");
		System.out.println("click on  Appointment icon");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		opp_find_app_appointment_button.click();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		// Thread.sleep(5000);

		// ExplicitWait.visibleElement(driver, opp_general_assigned, 10);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		Actions act = new Actions(driver);
		Click.sendKeys(driver, opp_general_assigned, "QA Sales", 30);
		// (Keys.ENTER);

		act.sendKeys(Keys.ENTER).build().perform();

		Click.on(driver, opp_general_assigned, 30);

		// Thread.sleep(2000);

		// Click.on(driver, opp_general_assigned, 30);

	}

	public void opp_find_listing_button() throws Exception

	{

		// Thread.sleep(5000);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		ExplicitWait.visibleElement(driver, opp_general_copy_code, 10);

		opp_general_copy_code.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		//// refresh
		// ExplicitWait.visibleElement(driver, opp_general_refresh, 10);
		// opp_general_refresh.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		// Share Quote

		ExplicitWait.visibleElement(driver, opp_general_share_quote, 10);

		opp_general_share_quote.click();

	}

	/*
	 * public void opp_vehicle_request_broker() throws Exception
	 * 
	 * {
	 * 
	 * 
	 * Thread.sleep(4000);
	 * 
	 * //HelperClass.highlightElement(driver, add_new_vehicle_request);
	 * 
	 * 
	 * Click.on(driver, add_new_vehicle_request, 30);
	 * 
	 * 
	 * 
	 * LO.print("Clicked on Add new vehicle request");
	 * System.out.println("Clicked on Add new vehicle request");
	 * 
	 * ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	 * Thread.sleep(4000);
	 * 
	 * 
	 * HelperClass.highlightElement(driver, channel);
	 * 
	 * Click.on(driver, channel, 30);
	 * 
	 * Thread.sleep(2000);
	 * 
	 * Click.on(driver, channel_broker_value, 10);
	 * 
	 * LO.print("Clicked on broker channel");
	 * System.out.println("Clicked on broker channel");
	 * 
	 * 
	 * Thread.sleep(2000); //channel_broker_value.sendKeys(Keys.TAB);
	 * 
	 * ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 10);
	 * 
	 * Thread.sleep(5000); Click.on(driver, add_new_vehicle_save, 10);
	 * 
	 * LO.print("Vehicle request for broker has been saved");
	 * System.out.println("Vehicle request for broker has been saved");
	 * 
	 * 
	 * }
	 */

	public void opp_map_new_quote_broker_business() throws Exception

	{

		// Map New Quote
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		// Thread.sleep(5000);
		HelperClass.highlightElement(driver, Map_New_quote_icon);

		LO.print("Click on Map New Quote icon");
		System.out.println("Click on Map New Quote icon");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, Map_New_quote_icon, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, acquisition_contract_type, 30);

		LO.print("Click on acquisition contract type icon");
		System.out.println("Click on acquisition contract type icon");
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// Thread.sleep(5000);
		Click.on(driver, acquisition_contract_type_broker_value, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// Thread.sleep(5000);
		Click.on(driver, customer_contract_type, 30);
		LO.print("Click on customer contract type icon");
		System.out.println("Click on customer contract type icon");
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		// Thread.sleep(5000);
		Click.on(driver, customer_business_contract_type_bch_value, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		// Thread.sleep(5000);
		Click.on(driver, map_new_quote_search, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		LO.print("Click on search button");
		System.out.println("Click on search button");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		Click.on(driver, map_new_quote_createddate_sorting, 30);

		LO.print("Click on created date button 1st ");
		System.out.println("Click on created date button 1st");

		Click.on(driver, map_new_quote_createddate_sorting, 30);

		LO.print("Click on created date button 2nd");
		System.out.println("Click on created date button 2nd");
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		Thread.sleep(2000);
		System.out.println("Select the checkbox for new quote ");
		LO.print("Select the checkbox for new quote ");
		Click.on(driver, select_new_quoted, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		Thread.sleep(5000);
		System.out.println("click ok button on Map  new quote ");
		LO.print("click ok button on Map  new quote ");

		Click.on(driver, select_new_quoted_save, 30);

		Thread.sleep(5000);

		try

		{

			if (lead_already_exits_quote_availbale.isEnabled())

			{

				System.out.println("click on OK button for lead already existing quote  ");
				LO.print("click ok button on Map  new quote ");

				Click.on(driver, lead_already_exits_quote_availbale, 20);

				Thread.sleep(2000);
				ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
				System.out.println("click on OK button for copy quote  ");
				LO.print("click on OK button for copy quote ");

				Click.on(driver, lead_already_exits_quote_availbale_second_popup_visible, 10);

				System.out.println("New copy quote is mapped with this opportunity ");
				// LO.print("New copy quote is mapped with this opportunity ");

			}
		}

		catch (Exception e) {
			// System.out.println( e );

		}

	}

	public void Opp_map_new_quote_broker_business_save_and_exit() throws Exception

	{

		// Map New Quote

		// Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		LO.print("Click on  save and exit button");
		System.out.println("Click on  save and exit button");

		// Thread.sleep(5000);
		Click.on(driver, opp_update_convert_into_opp_button, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

	}

	public boolean verify_current_status_of_opportunity_after_contract_signed() throws Exception

	{
		Thread.sleep(5000);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		Thread.sleep(2000);
		ExplicitWait.visibleElement(driver, opp_current_status_proposal, 40);

		ExplicitWait.visibleElement(driver, opp_current_status_channel, 40);
		ExplicitWait.visibleElement(driver, opp_current_status_quoteref, 40);

		// String OppCurrentStatusOpen = opp_current_status_open.getText().trim();

		String OppCurrentStatusProposal = opp_current_status_proposal.getText().trim();

		String oppCurrentStatusChannel = opp_current_status_channel.getText().trim();

		String oppCurrentStatusQuoteref = opp_current_status_quoteref.getText().trim();

		System.out.println("::::::::::::::::::::::::::::::Current Status :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		// System.out.println("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);
		// LO.print("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);

		System.out.println(" Current Status of Proposal value is = " + OppCurrentStatusProposal);
		LO.print("Current Status of Proposal value is = " + OppCurrentStatusProposal);

		System.out.println("Current Status of Channel value is =  " + oppCurrentStatusChannel);
		LO.print("Current Status of Channel value is =  " + oppCurrentStatusChannel);

		System.out.println(" Current Status of  Quote ref is = " + oppCurrentStatusQuoteref);
		LO.print("Current Status of  Quote ref is = " + oppCurrentStatusQuoteref);

		// boolean CurrentStatusOpen = OppCurrentStatusOpen.equals(" ");

		// System.out.println(" CurrentStatusOpen = " + CurrentStatusOpen);

		boolean CurrentStatusProposal = OppCurrentStatusProposal.equals("Proposal Received");

		System.out.println(" CurrentStatusProposal = " + CurrentStatusProposal);

		boolean CurrentStatusChannel = oppCurrentStatusChannel.equals("Contract signed");

		System.out.println(" CurrentStatusChannel = " + CurrentStatusChannel);

		boolean CurrentStatusQuoteref = oppCurrentStatusQuoteref.equals("Contract signed");

		System.out.println(" CurrentStatusQuoteref = " + CurrentStatusQuoteref);

		boolean flag = false;
		if (CurrentStatusProposal && CurrentStatusChannel && CurrentStatusQuoteref)

		{

			flag = true;
		}

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

		return flag;

	}

	public boolean verify_current_status_of_opportunity_after_contract_sending_to_underwriting() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// ExplicitWait.visibleElement(driver, opp_current_status_open, 20);
		ExplicitWait.visibleElement(driver, opp_current_status_proposal, 30);

		ExplicitWait.visibleElement(driver, opp_current_status_channel, 20);
		ExplicitWait.visibleElement(driver, opp_current_status_quoteref, 20);

		// String OppCurrentStatusOpen = opp_current_status_open.getText().trim();

		String OppCurrentStatusProposal = opp_current_status_proposal.getText().trim();

		String oppCurrentStatusChannel = opp_current_status_channel.getText().trim();

		String oppCurrentStatusQuoteref = opp_current_status_quoteref.getText().trim();

		System.out.println("::::::::::::::::::::::::::::::Current Status :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		// System.out.println("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);
		// LO.print("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);

		System.out.println(" Current Status of Proposal value is = " + OppCurrentStatusProposal);
		LO.print("Current Status of Proposal value is = " + OppCurrentStatusProposal);

		System.out.println("Current Status of Channel value is =  " + oppCurrentStatusChannel);
		LO.print("Current Status of Channel value is =  " + oppCurrentStatusChannel);

		System.out.println(" Current Status of  Quote ref is = " + oppCurrentStatusQuoteref);
		LO.print("Current Status of  Quote ref is = " + oppCurrentStatusQuoteref);

		// boolean CurrentStatusOpen = OppCurrentStatusOpen.equals(" ");

		// System.out.println(" CurrentStatusOpen = " + CurrentStatusOpen);

		boolean CurrentStatusProposal = OppCurrentStatusProposal.equals("Proposal Received");

		System.out.println(" CurrentStatusProposal = " + CurrentStatusProposal);

		boolean CurrentStatusChannel = oppCurrentStatusChannel.equals("Awaiting underwriting");

		System.out.println(" CurrentStatusChannel = " + CurrentStatusChannel);

		boolean CurrentStatusQuoteref = oppCurrentStatusQuoteref.equals("Awaiting underwriting");

		System.out.println(" CurrentStatusQuoteref = " + CurrentStatusQuoteref);

		boolean flag = false;
		if (CurrentStatusProposal && CurrentStatusChannel && CurrentStatusQuoteref)

		{

			flag = true;
		}

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

		return flag;

	}

	public boolean verify_current_status_of_opportunity_after_underwriting_accepted() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// ExplicitWait.visibleElement(driver, opp_current_status_open, 20);
		ExplicitWait.visibleElement(driver, opp_current_status_proposal, 30);

		ExplicitWait.visibleElement(driver, opp_current_status_channel, 20);
		ExplicitWait.visibleElement(driver, opp_current_status_quoteref, 20);

		// String OppCurrentStatusOpen = opp_current_status_open.getText().trim();

		String OppCurrentStatusProposal = opp_current_status_proposal.getText().trim();

		String oppCurrentStatusChannel = opp_current_status_channel.getText().trim();

		String oppCurrentStatusQuoteref = opp_current_status_quoteref.getText().trim();

		System.out.println("::::::::::::::::::::::::::::::Current Status :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		// System.out.println("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);
		// LO.print("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);

		System.out.println(" Current Status of Proposal value is = " + OppCurrentStatusProposal);
		LO.print("Current Status of Proposal value is = " + OppCurrentStatusProposal);

		System.out.println("Current Status of Channel value is =  " + oppCurrentStatusChannel);
		LO.print("Current Status of Channel value is =  " + oppCurrentStatusChannel);

		System.out.println(" Current Status of  Quote ref is = " + oppCurrentStatusQuoteref);
		LO.print("Current Status of  Quote ref is = " + oppCurrentStatusQuoteref);

		// boolean CurrentStatusOpen = OppCurrentStatusOpen.equals(" ");

		// System.out.println(" CurrentStatusOpen = " + CurrentStatusOpen);

		boolean CurrentStatusProposal = OppCurrentStatusProposal.equals("Proposal Received");

		System.out.println(" CurrentStatusProposal = " + CurrentStatusProposal);

		boolean CurrentStatusChannel = oppCurrentStatusChannel.equals("Underwriting accepted");

		System.out.println(" CurrentStatusChannel = " + CurrentStatusChannel);

		boolean CurrentStatusQuoteref = oppCurrentStatusQuoteref.equals("Underwriting accepted");

		System.out.println(" CurrentStatusQuoteref = " + CurrentStatusQuoteref);

		boolean flag = false;
		if (CurrentStatusProposal && CurrentStatusChannel && CurrentStatusQuoteref)

		{

			flag = true;
		}

		System.out.println(
				":::::::::::::::::::Verified underwriting accepted on Opportunity screen:::::::::::::::::::::::::::::::::::::::::::");

		return flag;

	}

	public boolean verify_underwriting_popup_summary_status_after_underwriting_accepted() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// ExplicitWait.visibleElement(driver, opp_current_status_open, 20);
		ExplicitWait.visibleElement(driver, opp_underwriting_summary_accept, 30);

		ExplicitWait.visibleElement(driver, opp_underwriting_summary_decline, 20);
		ExplicitWait.visibleElement(driver, opp_underwriting_summary_refer, 20);

		// String OppCurrentStatusOpen = opp_current_status_open.getText().trim();

		String oppUnderAccept = opp_underwriting_summary_accept.getText().trim();

		String oppUnderDecline = opp_underwriting_summary_decline.getText().trim();

		String oppUnderRefer = opp_underwriting_summary_refer.getText().trim();

		System.out.println("::::::::::::::::::::::::::::::Current Status :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		// System.out.println("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);
		// LO.print("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);

		System.out.println(" Latest Status of underwriting popup summary value is = " + oppUnderAccept);
		LO.print("Latest Status of underwriting popup opportunity summary  is = " + oppUnderAccept);

		System.out.println("Previuos Status of underwriting popup summary value is =  " + oppUnderDecline);
		LO.print("Latest Status of underwriting popup opportunity summary is =  " + oppUnderDecline);

		System.out.println(" Initial Status of underwriting popup summary value is = " + oppUnderRefer);
		LO.print("Initial Status of underwriting popup summary value is = " + oppUnderRefer);

		// boolean CurrentStatusOpen = OppCurrentStatusOpen.equals(" ");

		// System.out.println(" CurrentStatusOpen = " + CurrentStatusOpen);

		boolean LatestStatusUnderwritingSummaryvalue = oppUnderAccept.equals("Accepted");

		System.out.println(
				" Latest Status for Underwriting Summary value  is matched = " + LatestStatusUnderwritingSummaryvalue);

		boolean PreviousStatusUnderwritingSummaryvalue = oppUnderDecline.equals("Declined");

		System.out.println(" Previous Status for Underwriting Summary value  is matched = "
				+ PreviousStatusUnderwritingSummaryvalue);

		boolean InitialStatusQuoteref = oppUnderRefer.equals("Referred");

		System.out.println(" Intial Status for Underwriting Summary value  is matched = " + InitialStatusQuoteref);

		boolean flag = false;

		if (LatestStatusUnderwritingSummaryvalue && PreviousStatusUnderwritingSummaryvalue && InitialStatusQuoteref)

		{

			flag = true;
		}

		System.out.println(
				":::::::::::::::::::Verified Latest Status of underwriting popup summary value with 1.Referred 2.Declined 3.Accepted   ::::::::::::::::::::::::::::::::::::::::::");
		LO.print(
				":::::::::::::::::::Verified Latest Status of underwriting popup summary value with 1.Referred 2.Declined 3.Accepted   ::::::::::::::::::::::::::::::::::::::::::");
		return flag;

	}

	public void find_underwrting_create_order_icon() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		ExplicitWait.visibleElement(driver, underw_listing_create_order, 30);

		underw_listing_create_order.click();

	}

	public void verify_opportunity_ownbook_quote_search_text_box() throws InterruptedException, IOException, ClassNotFoundException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);


        ExplicitWait.visibleElement(driver, search_bar, 50);
		HelperClass.highlightElement(driver, search_bar);
		
		System.out.println("Click on Search text box on opportunity page");
		LO.print("Click on Search text box on Opportunity page");
		Thread.sleep(4000);

		
        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();

		obj_acq_listing_page = new AcquisitionListingPage();
		
		String sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		String OppoID = GetExcelFormulaValue.get_cell_value(1, 2, sheetName);
		
		ExplicitWait.visibleElement(driver, search_bar, 20);

		Click.sendKeys(driver, search_bar, OppoID, 20);

		System.out.println("Enter the opportunity ID in search text box =" + OppoID);
		LO.print("Enter the opportunity ID in search text box =" + OppoID);

		search_bar.sendKeys(Keys.ENTER);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

	}

	// Search Text for Ownbook_Purchase

	public void verify_opportunity_ownbook_purchase_quote_search_text_box() throws InterruptedException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 60);

		ExplicitWait.visibleElement(driver, search_bar, 60);

		HelperClass.highlightElement(driver, search_bar);
		System.out.println("Click on Search text box on opportunity page");
		LO.print("Click on Search text box on Opportunity page");
		Thread.sleep(4000);

		FileInputStream fis = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheet("HPNR_HPNR_QuoteNo");// selecting sheet with its name as a parameter
		Thread.sleep(3000);
		// System.out.println("Taking the sheet name is =" + fis);
		System.out.println("Taking the sheet name is  =" + sheet.getSheetName());

		XSSFRow row = sheet.getRow(0);// read data from first row as 0th row contains header
		XSSFCell cell = row.getCell(0);// read data from first cell

		// System.out.println(cell);
		System.out.println(sheet.getRow(0).getCell(0));
		Thread.sleep(1000);
		String UnderwritingQuoteId = cell.getStringCellValue();

		System.out.println("Getting the  Quote id from excel sheet  =" + UnderwritingQuoteId);
		LO.print("Getting the  quote id from excel sheet =" + UnderwritingQuoteId);

		Thread.sleep(2000);
//System.out.println(cellval);

		// String UnderwritingPrposalId =cell.getStringCellValue();

		// System.out.println("proposal id before send to search text box =" +
		// UnderwritingPrposalId);

		// Obj_Underwriting_Page = new Underwriting();

		ExplicitWait.visibleElement(driver, search_bar, 20);

		Click.sendKeys(driver, search_bar, UnderwritingQuoteId, 20);

		System.out.println("Enter the proposal in search text box =" + UnderwritingQuoteId);
		LO.print("Enter the proposal in search text box =" + UnderwritingQuoteId);

		search_bar.sendKeys(Keys.ENTER);

	}

	//

	public void verify_opportunity_ownbook_quote_hire_search_text_box() throws InterruptedException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		ExplicitWait.visibleElement(driver, search_bar, 20);

		HelperClass.highlightElement(driver, search_bar);
		System.out.println("Click on Search text box on opportunity page");
		LO.print("Click on Search text box on Opportunity page");
		Thread.sleep(4000);

		FileInputStream fis = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheet("HPNRBCHQuoteNo");// selecting sheet with its name as a parameter
		Thread.sleep(3000);
		// System.out.println("Taking the sheet name is =" + fis);
		System.out.println("Taking the sheet name is  =" + sheet.getSheetName());

		XSSFRow row = sheet.getRow(0);// read data from first row as 0th row contains header
		XSSFCell cell = row.getCell(0);// read data from first cell

		// System.out.println(cell);
		System.out.println(sheet.getRow(0).getCell(0));
		Thread.sleep(1000);
		String UnderwritingQuoteId = cell.getStringCellValue();

		System.out.println("Getting the  Quote id from excel sheet  =" + UnderwritingQuoteId);
		LO.print("Getting the  quote id from excel sheet =" + UnderwritingQuoteId);

		Thread.sleep(2000);
//System.out.println(cellval);

		// String UnderwritingPrposalId =cell.getStringCellValue();

		// System.out.println("proposal id before send to search text box =" +
		// UnderwritingPrposalId);

		// Obj_Underwriting_Page = new Underwriting();

		ExplicitWait.visibleElement(driver, search_bar, 20);

		Click.sendKeys(driver, search_bar, UnderwritingQuoteId, 20);

		System.out.println("Enter the proposal in search text box =" + UnderwritingQuoteId);
		LO.print("Enter the proposal in search text box =" + UnderwritingQuoteId);

		search_bar.sendKeys(Keys.ENTER);

	}

	public void verify_opportunity_broker_business_quote_search_text_box() throws InterruptedException, IOException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		ExplicitWait.visibleElement(driver, search_bar, 20);

		HelperClass.highlightElement(driver, search_bar);
		System.out.println("Click on Search text box on opportunity page");
		LO.print("Click on Search text box on Opportunity page");
		Thread.sleep(4000);

		FileInputStream fis = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheet("BrokerBCHQuoteNo");// selecting sheet with its name as a parameter
		Thread.sleep(3000);
		// System.out.println("Taking the sheet name is =" + fis);
		System.out.println("Taking the sheet name is  =" + sheet.getSheetName());

		XSSFRow row = sheet.getRow(0);// read data from first row as 0th row contains header
		XSSFCell cell = row.getCell(0);// read data from first cell

		// System.out.println(cell);
		System.out.println(sheet.getRow(0).getCell(0));
		Thread.sleep(1000);
		String UnderwritingQuoteId = cell.getStringCellValue();

		System.out.println("Getting the  Quote id from excel sheet  =" + UnderwritingQuoteId);
		LO.print("Getting the  quote id from excel sheet =" + UnderwritingQuoteId);

		Thread.sleep(2000);
//System.out.println(cellval);

		// String UnderwritingPrposalId =cell.getStringCellValue();

		// System.out.println("proposal id before send to search text box =" +
		// UnderwritingPrposalId);

		// Obj_Underwriting_Page = new Underwriting();

		ExplicitWait.visibleElement(driver, search_bar, 20);

		Click.sendKeys(driver, search_bar, UnderwritingQuoteId, 20);

		System.out.println("Enter the proposal in search text box =" + UnderwritingQuoteId);
		LO.print("Enter the proposal in search text box =" + UnderwritingQuoteId);

		search_bar.sendKeys(Keys.ENTER);

	}

	// CurrentStatusafterAccptingTheNewChangesFromUnderwritingPopup

	public boolean verify_current_status_after_accept_new_changes_from_underwriting_pop_up() throws Exception

	{
		

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);
		
		Thread.sleep(5000);

		// ExplicitWait.visibleElement(driver, opp_current_status_open, 20);
		ExplicitWait.visibleElement(driver, opp_current_status_proposal, 60);

		ExplicitWait.visibleElement(driver, opp_current_status_channel, 60);
		ExplicitWait.visibleElement(driver, opp_current_status_quoteref1, 60);
		ExplicitWait.visibleElement(driver, opp_current_status_quoteref2, 60);

		// String OppCurrentStatusOpen = opp_current_status_open.getText().trim();

		String OppCurrentStatusProposal = opp_current_status_proposal.getText().trim();

		String oppCurrentStatusChannel = opp_current_status_channel.getText().trim();

		String oppCurrentStatusQuoteref1 = opp_current_status_quoteref1.getText().trim();
		String oppCurrentStatusQuoteref2 = opp_current_status_quoteref2.getText().trim();

		System.out.println("::::::::::::::::::::::::::::::Current Status :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		// System.out.println("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);
		// LO.print("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);

		System.out.println(" Current Status of Proposal value is = " + OppCurrentStatusProposal);
		LO.print("Current Status of Proposal value is = " + OppCurrentStatusProposal);

		System.out.println("Current Status of Channel value is =  " + oppCurrentStatusChannel);
		LO.print("Current Status of Channel value is =  " + oppCurrentStatusChannel);

		System.out.println(" Current Status of  Quote ref1 is = " + oppCurrentStatusQuoteref1);
		LO.print("Current Status of  Quote ref is = " + oppCurrentStatusQuoteref1);

		System.out.println(" Current Status of  Quote ref 2 is = " + oppCurrentStatusQuoteref2);
		LO.print("Current Status of  Quote ref is = " + oppCurrentStatusQuoteref2);

		// boolean CurrentStatusOpen = OppCurrentStatusOpen.equals(" ");

		// System.out.println(" CurrentStatusOpen = " + CurrentStatusOpen);

		boolean CurrentStatusProposal = OppCurrentStatusProposal.equals("Proposal Received");

		System.out.println(" CurrentStatusProposal = " + CurrentStatusProposal);

		boolean CurrentStatusChannel = oppCurrentStatusChannel.equals("Sent to customer");

		System.out.println(" CurrentStatusChannel = " + CurrentStatusChannel);

		boolean CurrentStatusQuoteref1 = oppCurrentStatusQuoteref1.equals("Sent to customer")
				|| oppCurrentStatusQuoteref1.equals("Cancelled");

		System.out.println(" CurrentStatusQuoteref1 = " + CurrentStatusQuoteref1);

		boolean CurrentStatusQuoteref2 = oppCurrentStatusQuoteref2.equals("Cancelled")
				|| oppCurrentStatusQuoteref2.equals("Sent to customer");

		System.out.println(" CurrentStatusQuoteref2 = " + CurrentStatusQuoteref2);

		boolean flag = false;
		if (CurrentStatusProposal && CurrentStatusChannel && CurrentStatusQuoteref1 && CurrentStatusQuoteref2)

		{

			flag = true;
		}

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

		return flag;

	}

	public boolean verify_ownbook_when_underwrting_refer_changes_accepted() throws Exception

	{
		Thread.sleep(3000);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// ExplicitWait.visibleElement(driver, opp_current_status_open, 20);
		ExplicitWait.visibleElement(driver, opp_current_status_proposal, 30);

		ExplicitWait.visibleElement(driver, opp_current_status_channel, 20);
		ExplicitWait.visibleElement(driver, opp_current_status_quoteref1, 20);
		// ExplicitWait.visibleElement(driver, opp_current_status_quoteref2, 20);

		// String OppCurrentStatusOpen = opp_current_status_open.getText().trim();

		String OppCurrentStatusProposal = opp_current_status_proposal.getText().trim();

		String oppCurrentStatusChannel = opp_current_status_channel.getText().trim();

		String oppCurrentStatusQuoteref1 = opp_current_status_quoteref1.getText().trim();
		// String oppCurrentStatusQuoteref2 =
		// opp_current_status_quoteref2.getText().trim();

		System.out.println("::::::::::::::::::::::::::::::Current Status :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		// System.out.println("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);
		// LO.print("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);

		System.out.println(" Current Status of Proposal value is = " + OppCurrentStatusProposal);
		LO.print("Current Status of Proposal value is = " + OppCurrentStatusProposal);

		System.out.println("Current Status of Channel value is =  " + oppCurrentStatusChannel);
		LO.print("Current Status of Channel value is =  " + oppCurrentStatusChannel);

		System.out.println(" Current Status of  Quote ref1 is = " + oppCurrentStatusQuoteref1);
		LO.print("Current Status of  Quote ref is = " + oppCurrentStatusQuoteref1);

		// System.out.println(" Current Status of Quote ref 2 is = " +
		// oppCurrentStatusQuoteref2);
		// LO.print("Current Status of Quote ref is = " + oppCurrentStatusQuoteref2);

		// boolean CurrentStatusOpen = OppCurrentStatusOpen.equals(" ");

		// System.out.println(" CurrentStatusOpen = " + CurrentStatusOpen);

		boolean CurrentStatusProposal = OppCurrentStatusProposal.equals("Proposal Received");

		System.out.println(" CurrentStatusProposal = " + CurrentStatusProposal);

		boolean CurrentStatusChannel = oppCurrentStatusChannel.equals("Response received");

		System.out.println(" CurrentStatusChannel = " + CurrentStatusChannel);

		boolean CurrentStatusQuoteref1 = oppCurrentStatusQuoteref1.equals("Response received");

		boolean flag = false;
		if (CurrentStatusProposal && CurrentStatusChannel && CurrentStatusQuoteref1)

		{

			flag = true;
		}

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

		return flag;

	}

	public boolean verify_ownbook_opportunity_staus_when_underwrting_accepted_refer_changes_after_response_received()
			throws Exception

	{
		Thread.sleep(3000);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// ExplicitWait.visibleElement(driver, opp_current_status_open, 20);
		ExplicitWait.visibleElement(driver, opp_current_status_proposal, 30);

		ExplicitWait.visibleElement(driver, opp_current_status_channel, 20);
		ExplicitWait.visibleElement(driver, opp_current_status_quoteref1, 20);
		// ExplicitWait.visibleElement(driver, opp_current_status_quoteref2, 20);

		// String OppCurrentStatusOpen = opp_current_status_open.getText().trim();

		String OppCurrentStatusProposal = opp_current_status_proposal.getText().trim();

		String oppCurrentStatusChannel = opp_current_status_channel.getText().trim();

		String oppCurrentStatusQuoteref1 = opp_current_status_quoteref1.getText().trim();
		// String oppCurrentStatusQuoteref2 =
		// opp_current_status_quoteref2.getText().trim();

		System.out.println("::::::::::::::::::::::::::::::Current Status :::::::::::::::::::::::::::::::::::::");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

		// System.out.println("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);
		// LO.print("Current Status of Opportunity Status value is = " +
		// OppCurrentStatusOpen);

		System.out.println(" Current Status of Proposal value is = " + OppCurrentStatusProposal);
		LO.print("Current Status of Proposal value is = " + OppCurrentStatusProposal);

		System.out.println("Current Status of Channel value is =  " + oppCurrentStatusChannel);
		LO.print("Current Status of Channel value is =  " + oppCurrentStatusChannel);

		System.out.println(" Current Status of  Quote ref1 is = " + oppCurrentStatusQuoteref1);
		LO.print("Current Status of  Quote ref is = " + oppCurrentStatusQuoteref1);

		// System.out.println(" Current Status of Quote ref 2 is = " +
		// oppCurrentStatusQuoteref2);
		// LO.print("Current Status of Quote ref is = " + oppCurrentStatusQuoteref2);

		// boolean CurrentStatusOpen = OppCurrentStatusOpen.equals(" ");

		// System.out.println(" CurrentStatusOpen = " + CurrentStatusOpen);

		boolean CurrentStatusProposal = OppCurrentStatusProposal.equals("Proposal Received");

		System.out.println(" CurrentStatusProposal = " + CurrentStatusProposal);

		boolean CurrentStatusChannel = oppCurrentStatusChannel.equals("Underwriting accepted");

		System.out.println(" CurrentStatusChannel = " + CurrentStatusChannel);

		boolean CurrentStatusQuoteref1 = oppCurrentStatusQuoteref1.equals("Underwriting accepted");

		boolean flag = false;
		if (CurrentStatusProposal && CurrentStatusChannel && CurrentStatusQuoteref1)

		{

			flag = true;
		}

		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

		return flag;

	}

	public String verify_ownbook_opportunity_underwriting_accept_with_changes_cancel_flow_verification()
			throws InterruptedException

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		String OwbbookStatusBeforexpath = "//*[@id=\"cWraper\"]/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[24]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[";

		String OwbbookStatusAfterxpath = "]/td[7]/div/div/div/span";

		String QuoteRef_new = "";
		for (int i = 1; i <= 2; i++)

		{
			String StausValuefromscreen = driver
					.findElement(By.xpath(OwbbookStatusBeforexpath + i + OwbbookStatusAfterxpath)).getText();

			// String StausValuefromscreen = status.gettext();

			if (StausValuefromscreen.equals("Sent to customer"))

			{

				String QuoteBerofexpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[24]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[";

				String QuoteAfterXpath = "]/td[1]/div/div";

				QuoteRef_new = driver.findElement(By.xpath(QuoteBerofexpath + i + QuoteAfterXpath)).getText();
				System.out.println("QuoteRef_new " + QuoteRef_new);

			}
		}
		return QuoteRef_new;

	}

	public void search_and_verify_underwriting_icon_is_availabale_after_cancel_flow() throws InterruptedException {

		String UnderwitingWeblementBeforexpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[";

		String UnderwitingWeblementAfterxpath = "]/td[7]/div/div/div/span";
		
		
		                                         

		for (int i = 1; i <= 2; i++)

		{
			String UnderwitingValuefromscreen = driver
					.findElement(By.xpath(UnderwitingWeblementBeforexpath + i + UnderwitingWeblementAfterxpath))
					.getText();

			// String StausValuefromscreen = status.gettext();

			if (UnderwitingValuefromscreen.equals("Underwriting accepted"))

			{

				String QuoteBerofexpath = "//*[@id='cWraper']/div/app-opportunity-management/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[";

				String QuoteAfterXpath = "]/td[8]/div/a[5]";

				driver.findElement(By.xpath(QuoteBerofexpath + i + QuoteAfterXpath)).click();
				System.out.println("Click on underwirting icon ");

				ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
			}
		}

	}

}
