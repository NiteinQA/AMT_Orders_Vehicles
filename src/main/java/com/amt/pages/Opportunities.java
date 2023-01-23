package com.amt.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ParseException;
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
import com.amt.testUtil.Dropdown;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.HelperClass;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;








public class Opportunities extends TestBase {

	JavascriptExecutor js;

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

	@FindBy(xpath = "//app-opportunity-header/div[1]/div[2]/div[1]/button[2]/i[1]")
	private WebElement opp_opp_update_button;

	@FindBy(xpath = "//*[@id='cWraper']/div/app-add-opportunities/app-opportunity-header/div/div[1]/div/ul/li[3]/a/p")
	private WebElement opp_proposal_button;

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

	@FindBy(xpath = "//tr[@class='ng-star-inserted']//td[10]")
	private WebElement opp_current_status_proposal;

	@FindBy(xpath = "//tr[@class='border ng-star-inserted']//td[8]")
	private WebElement opp_current_status_channel;

	@FindBy(xpath = "//table[@class='table mb-0 levelthreetr']//td[7]")
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
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public Opportunities() {
		PageFactory.initElements(driver, this);

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("D:\\StagingNew\\AMT_Automation\\src\\main\\java\\configs\\excelValues.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

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

		return flag;

	}

	public boolean verify_current_status_of_opportunity_after_sending_to_proposal()

	{

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

		return flag;

	}

	public boolean verify_current_status_of_opportunity_after_sending_to_customer_contract() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

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
		Thread.sleep(3000);

		// Click on Search text box of Opportunity
		Click.on(driver, opportunity_menu_link, 30);

		LO.print("Clicked on Opportunity link from Menu ");
		System.out.println("Clicked on Opportunity link form Menu");
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// Click.sendKeys(driver, Lead_OpportunityId, null, 10);

	}

	public void opp_search_textbox(String GetOpportunityid1) throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		ExplicitWait.visibleElement(driver, search_bar, 20);

		Thread.sleep(5000);

		search_bar.sendKeys(GetOpportunityid1);

		LO.print("Enter the Opportunity id in Search Text box");
		System.out.println("Enter the Opportunity id in Search Text box");

		search_bar.sendKeys(Keys.ENTER);

		Thread.sleep(5000);

	}
	
	
	
	public String []  get_api_data_opp () throws InterruptedException
	
	{
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	    ExplicitWait.visibleElement(driver, api_Opp_id_webelement, 20);
		String opp_id_screen =  api_Opp_id_webelement.getText();
		System.out.println("opp_id_screen" + opp_id_screen);
		
		
		
		
		
		ExplicitWait.visibleElement(driver, api_quote_ref_webelement, 20);
		String quote_ref_screen = api_quote_ref_webelement.getText();
		System.out.println("quote_id_screen" + quote_ref_screen);
		
		String [] DataAPI  = new String[2];
		
		DataAPI [0]  = opp_id_screen;
		DataAPI [1]  = quote_ref_screen;
		return DataAPI;
	
		
		
		
		
		
		
	}
	
	
	
	public int postAPITest(String QuoteRef , String OppID) throws JsonGenerationException, JsonMappingException, IOException, ParseException, InterruptedException{
		RestClient restClient;
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		//headerMap.put("Authorization", "Bearer  eyJhbGciOiJSUzI1NiIsImtpZCI6IkxVd2l4OU1oRFN0LWJoRl9mWlZHbEEiLCJ0eXAiOiJhdCtqd3QifQ.eyJuYmYiOjE2NzM4NzAxMzEsImV4cCI6MTY3NDA0MjkzMSwiaXNzIjoiaHR0cHM6Ly9zdGFnaW5nYW10aWRlbnRpdHlzZXJ2ZXJhcGkuYXp1cmV3ZWJzaXRlcy5uZXQiLCJhdWQiOiJGbGVldEFQSUdhdGV3YXkiLCJjbGllbnRfaWQiOiJGbGVldEFQSUdhdGV3YXkiLCJzdWIiOiJkODYwZWZjYS0yMmQ5LTQ3ZmQtODI0OS03OTFiYTYxYjA3YzciLCJhdXRoX3RpbWUiOjE2NzM4NzAxMzEsImlkcCI6ImxvY2FsIiwiUGVybWlzc2lvbnMiOiJBY3F1aXNpdGlvbiBtYW5hZ2VyI1N1cGVyIEFkbWluI0FjcXVpc2l0aW9uIHVzZXIjU2FsZXMgdXNlciNTYWxlcyBtYW5hZ2VyIiwibmFtZSI6InN1cGVyYWRtaW5AYW10dWsuY28udWsiLCJzY29wZSI6WyJGbGVldEFQSUdhdGV3YXkiXSwiYW1yIjpbInB3ZCJdfQ.QiMm_yk0yK9oYtNMwjjvB1Cz085xcEqviK1OQLPgVu58YTDGsC10uPFzo54A6CTfJp4S4CXB0ulSwTu06JanDog0hmxwlb_dvv5AF6ZvS6m2Q7eD7PXRgufOXQF0y7U5hLM4rRrf4gU2frbbKwOzmeIPDWh3qjqminc-myUF4c9nb-k6pEIjKSuwlejqWZvOTdnOOe6lFdQOYPztHet8EEqhYibpSH3trNDm41Zrnb-F175BP_uArkaN5iv4K73wdwDxWAFP6kBQf-R8CvrOExhMLlnfAtuQdNK_fdFiE0dppllz3TN_Ej4Tk7KfWxV4aZKlfES9wABoIXFTaVzKOw");
		
		//jackson API:
		ObjectMapper mapper = new ObjectMapper();
		OppStatusData OppStatus = new OppStatusData(QuoteRef, OppID); //expected users object
		
		//object to json file:
		mapper.writeValue(new File("D:\\newWorkspaceStaging\\AutomationStaging\\src\\main\\java\\com\\amt\\api\\pojo\\OppStatusData.json"), OppStatus);
		
		//java object to json in String:
		String usersJsonString = mapper.writeValueAsString(OppStatus);
		System.out.println(usersJsonString);
		 CloseableHttpResponse closebaleHttpResponse;
		closebaleHttpResponse = restClient.post(prop.getProperty("APIURL"), usersJsonString, headerMap); //call the API
		
		//validate response from API:
		//1. status code:
		//int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		
		int statusCode = closebaleHttpResponse.getCode();
		 
		
		 
		Thread.sleep(5000);
		  driver.navigate().refresh();
		
		return statusCode;
		
		
		

		
		
	}
	
	
	
	
	
	
	
	

	public void opp_listing_detail_page() throws Exception

	{

		ExplicitWait.visibleElement(driver, opp_listing_double_click, 10);

		Actions act = new Actions(driver);

		// Double click on element

		act.doubleClick(opp_listing_double_click).perform();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

	}

	public void opp_listing_detail_update_indiviual() throws Exception

	{

		Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_opp_update_button, 10);

		opp_opp_update_button.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// proposal link

		Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_proposal_button, 20);

		opp_proposal_button.click();

		LO.print("click on Proposal icon");
		System.out.println("click on Proposal icon");

	}

	public void opp_opp_fact_find() throws Exception

	{

		Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_cars, 10);

		opp_opp_fact_find_no_of_cars.sendKeys("1");

		Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_hgv, 10);

		opp_opp_fact_find_no_of_hgv.sendKeys("1");

		ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_lcv, 10);

		opp_opp_fact_find_no_of_lcv.sendKeys("1");

		// ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_carscheme, 10);

		Dropdown.selectByVisibleText(driver, opp_opp_fact_find_no_of_carscheme, " No ", 10);

		opp_opp_fact_find_no_of_carscheme.click();

		ExplicitWait.visibleElement(driver, opp_opp_update_button, 10);

		opp_opp_update_button.click();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// proposal link

		Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_proposal_button, 20);

		opp_proposal_button.click();

		LO.print("click on Proposal icon");
		System.out.println("click on Proposal icon");


		 

	}

	public void opp_opp_fact_find_oppurtunity_flow() throws Exception

	{

		Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_cars, 20);

		opp_opp_fact_find_no_of_cars.sendKeys("1");

		Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_hgv, 10);

		opp_opp_fact_find_no_of_hgv.sendKeys("1");

		ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_lcv, 10);

		opp_opp_fact_find_no_of_lcv.sendKeys("1");

		// ExplicitWait.visibleElement(driver, opp_opp_fact_find_no_of_carscheme, 10);

		Dropdown.selectByVisibleText(driver, opp_opp_fact_find_no_of_carscheme, " No ", 10);

		opp_opp_fact_find_no_of_carscheme.click();

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

		Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_find_send_contract_icon, 10);

		LO.print("click on Send_Contract icon");
		System.out.println("click on  Send_Contract icon");

		opp_find_send_contract_icon.click();

		ExplicitWait.visibleElement(driver, send_contract_to_customer_pop_up_send_button, 10);

		send_contract_to_customer_pop_up_send_button.click();
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		// *[@id="sendcontractmodal"]/div/div/div[3]/div/button[2]

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

		Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, opp_find_app_appointment_button, 10);

		LO.print("click on Add  Appointment icon");
		System.out.println("click on  Appointment icon");

		opp_find_app_appointment_button.click();
		Thread.sleep(5000);

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

		Thread.sleep(5000);

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

		Thread.sleep(5000);
		HelperClass.highlightElement(driver, Map_New_quote_icon);

		LO.print("Click on Map New Quote icon");
		System.out.println("Click on Map New Quote icon");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, Map_New_quote_icon, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.on(driver, acquisition_contract_type, 30);

		LO.print("Click on acquisition contract type icon");
		System.out.println("Click on acquisition contract type icon");

		Thread.sleep(5000);
		Click.on(driver, acquisition_contract_type_broker_value, 30);

		Thread.sleep(5000);
		Click.on(driver, customer_contract_type, 30);
		LO.print("Click on customer contract type icon");
		System.out.println("Click on customer contract type icon");

		Thread.sleep(5000);
		Click.on(driver, customer_business_contract_type_bch_value, 30);

		Thread.sleep(5000);
		Click.on(driver, map_new_quote_search, 30);

		LO.print("Click on search button");
		System.out.println("Click on search button");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		Click.on(driver, map_new_quote_createddate_sorting, 30);

		LO.print("Click on created date button 1st ");
		System.out.println("Click on created date button 1st");

		Click.on(driver, map_new_quote_createddate_sorting, 30);

		LO.print("Click on created date button 2nd");
		System.out.println("Click on created date button 2nd");

		Thread.sleep(5000);
		System.out.println("Select the checkbox for new quote ");
		LO.print("Select the checkbox for new quote ");
		Click.on(driver, select_new_quoted, 30);

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

				Thread.sleep(5000);

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

		Thread.sleep(5000);

		LO.print("Click on  save and exit button");
		System.out.println("Click on  save and exit button");

		Thread.sleep(5000);
		Click.on(driver, opp_update_convert_into_opp_button, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

	}

	public boolean verify_current_status_of_opportunity_after_contract_signed() throws Exception

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
	
	
	
	
}
