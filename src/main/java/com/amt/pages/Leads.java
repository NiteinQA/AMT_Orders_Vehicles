package com.amt.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Difference;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.HelperClass;
import com.amt.testUtil.RemoveComma;
import com.amt.testUtil.Dropdown;

public class Leads extends TestBase {

	JavascriptExecutor js;
	AcquisitionListingPage obj_acq_listing_page;
	
	Properties prop;

	@FindBy(xpath = "//*[contains(text(),'Leads')]")
	private WebElement leads;

	@FindBy(xpath = "//button[@title='Add Lead']")
	private WebElement add_lead;

	@FindBy(xpath = "//select[@id='SourceId']")
	private WebElement general_lead_source;

	@FindBy(xpath = "//select[@id='FormTypeId']")
	private WebElement general_entry_type;

	@FindBy(xpath = "//select[@id='StatusId']")
	private WebElement status;

	@FindBy(xpath = "//div[@class='form-group norequired col-md-12 col-lg-4 ng-star-inserted']//input[@id='customer']")
	private WebElement general_assigned_to;

	@FindBy(xpath = "//ul[@class='user-dd-list shadow ng-star-inserted']//li[@class='ng-star-inserted']")
	private WebElement general_assigned_to_option;

	@FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-add-leads[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[2]/div[1]/app-general-info[1]/div[1]/div[2]/div[1]/div[1]/textarea[1]")
	private WebElement general_notes;

	@FindBy(xpath = "//select[@id='CustomerTypeId']")
	private WebElement customer_type;

	@FindBy(xpath = "(//button[@type='button'][normalize-space()='Add new'])[1]")
	private WebElement customer_add_new_button;

	// Business user Add Contact person

	@FindBy(xpath = "(//button[@type='button'][normalize-space()='Add new'])[1]")
	private WebElement business_contact_add_new_button;

	@FindBy(xpath = "//div[@class='form-group only-inputgroup input norequired col-md-12 col-lg-4']//input[@id='customer']")
	private WebElement customer_name;

	@FindBy(xpath = "//ul[@class='user-dd-list shadow ng-star-inserted']//li[@class='ng-star-inserted']")
	private WebElement customer_name_option;

	@FindBy(xpath = "//button[contains(text(), 'Add new vehicle request')]")
	private WebElement add_new_vehicle_request;

	@FindBy(xpath = "//*[@id='vehiclinfo2']/div/div/div/div[1]/div/ng-multiselect-dropdown/div/div[1]/span/span[2]")
	private WebElement channel;

	@FindBy(xpath = "//*[@class='item2']")
	private WebElement channel_dropdown_list;

	@FindBy(xpath = "//*[@id='vehiclinfo2']/div/div/div/div[1]/div/ng-multiselect-dropdown/div/div[2]/ul[2]/li[1]/div")
	private WebElement broker_channel;

	@FindBy(xpath = "(//*[contains(text(),' Save ')])[3]")
	private WebElement save_vehicle_request;

	@FindBy(xpath = "//*[@id='enteredLog']")
	private WebElement add_note;

	@FindBy(xpath = "//*[contains(text(),'Post ')]")
	private WebElement post;

	@FindBy(xpath = "//*[@title='Map New Quote']")
	private WebElement map_new_quote;

	@FindBy(xpath = "(//*[contains(text(),'Ok')])[4]")
	private WebElement ok_button;

	@FindBy(xpath = "//button[normalize-space()='Save & Exit']")
	private WebElement save_and_exit_button;

	@FindBy(xpath = "//button[normalize-space()='Save & Convert']")
	private WebElement save_and_convert_button;

	@FindBy(xpath = "//*[@id='cWraper']/div/app-add-opportunities/div/div/div/div/form/div[1]/div[1]/div/div[2]/div[2]")
	private WebElement opportunity_ref_no;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//*[contains(text(),'Update & Exit')]")
	private WebElement update_and_exit;

	// Communication log webelement

	// 1.Add Note
	@FindBy(xpath = "//span[contains(text() , 'Add note')]")
	private WebElement communication_add_note_panel;

	// 2.Add Note input box
	@FindBy(xpath = "//textarea[@id='enteredLog']")
	private WebElement communication_add_note_input_box;

	// 3.Post Button
	@FindBy(xpath = "//button[contains(text(),'Post')]")
	private WebElement communication_post_button;

	// ---------Log a call -----

	// 4.Log a call
	@FindBy(xpath = " //span[contains(text() , 'Log a call')]")
	private WebElement Communication_Log_a_call_panel;

	// Add Log a call input box
	@FindBy(xpath = " //textarea[@id='enteredLog']")
	private WebElement Communication_Log_a_call_input_box;

	// --------Send email

	// 5.Send email

	@FindBy(xpath = " //span[contains(text(), 'Send email')]")
	private WebElement communication_send_email_panel;

	// 6.Email subject

	@FindBy(xpath = "//input[@id='emailSubject']")
	private WebElement communication_send_email_subject;

	// 7.Send email button

	@FindBy(xpath = "//button[contains(text(),'Send email')]")
	private WebElement communication_send_email_button;

	// 7.Send email button - yes button

	@FindBy(xpath = "//*[@id='save_email_popup1']/div/div//button[@class='btn btn-secondary float-right']")
	private WebElement communication_send_email_button_confirm_yes_button;

// Add new Vehicle request 

//	@FindBy(xpath = "//*[@id='vehiclinfo2']/div/div/div/div[1]/div/ng-multiselect-dropdown/div/div[2]/ul[2]/li[1]/div")
//	private WebElement channel_broker_value;
	
	@FindBy(xpath = "//*[text()='Broker']")
	private WebElement channel_broker_value;
	
	
	
	//retail_sale_channel
	
	@FindBy(xpath = "//*[text()='Retail Sales']")
	private WebElement channel_retail_sales;

	// Ownbook value selection

	@FindBy(xpath = "//*[text()='Own Book']")
	private WebElement channel_ownbook_value;

	@FindBy(xpath = "//*[@id='LeadVehicleRequest']/div/div/div[3]/div/button[1]")
	private WebElement add_new_vehicle_save;

// 	 	Map_New_quote_icon

	@FindBy(xpath = "//*[@id='vehicleRequest2']/div/div/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div/table/tbody/tr/td[8]/div/a[3]/img")
	private WebElement Map_New_quote_icon;

	// *[@id="vehicleRequest2"]/div/div/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div/table/tbody/tr/td[9]/div/a[3]/img

	//// *[@id="MapNewQuote"]/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[1]/div/ng-multiselect-dropdown/div/div[1]/span/span[2]/span

//	@FindBy(xpath = "//*[@id='MapNewQuote']/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[1]/div/ng-multiselect-dropdown/div/div[1]/span/span[2]/span")
//	private WebElement acquisition_contract_type;
	
	
	
	@FindBy(xpath = "//*[@placeholder='Select Acquisition contract']//span//span[1]")
	private WebElement acquisition_contract_type;

	
	@FindBy(xpath = "//*[@id='MapNewQuote']/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[1]/div/ng-multiselect-dropdown/div/div[2]/ul[2]/li/div")
	private WebElement acquisition_contract_type_broker_value;

	// acquisition_contract_type_ownbook_value

	@FindBy(xpath = "//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-add-leads[1]/app-map-new-quote[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/ng-multiselect-dropdown[1]/div[1]/div[2]/ul[2]/li[4]/div[1]")
	private WebElement acquisition_contract_type_ownbook_value;	
	
	
	@FindBy(xpath = "//*[@placeholder='Select Acquisition contract']//*[text()='Outright Purchase']")
	private WebElement acquisition_contract_type_outright_purchase;
	
	@FindBy(xpath = "//*[@placeholder='Select customer contract']//*[text()='Outright Purchase']")
	private WebElement customer_contract_type_outright_purchase;

//	@FindBy(xpath = "//*[@id='MapNewQuote']/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[2]/div/ng-multiselect-dropdown/div/div[1]/span/span[1]")
//	private WebElement customer_contract_type;	
	
	@FindBy(xpath = "//*[@placeholder='Select customer contract']//span//span[1]")
	private WebElement customer_contract_type;

	@FindBy(xpath = "//div[contains(text(),'Personal Contract Hire')]")
	private WebElement customer_contract_type_pch_value;
	
	@FindBy(xpath = "//div[contains(text(),'Personal Contract Purchase')]")
	private WebElement customer_contract_type_pcp_value;

	@FindBy(xpath = "//div[contains(text(),'Business Contract Hire')]")
	private WebElement customer_contract_type_bch_value;

	@FindBy(xpath = "//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-add-leads[1]/app-map-new-quote[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/ng-multiselect-dropdown[1]/div[1]/div[2]/ul[2]/li[1]/div[1]")
	private WebElement customer_business_contract_type_bch_value;

	@FindBy(xpath = "//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-add-leads[1]/app-map-new-quote[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/ng-multiselect-dropdown[1]/div[1]/div[2]/ul[2]/li[4]/div[1]")
	private WebElement customer_business_contract_type_hpnr_value;

	@FindBy(xpath = "//*[normalize-space()='Search']")
	private WebElement map_new_quote_search;

	@FindBy(xpath = "//th[contains(text(),'Created date')]")
	private WebElement map_new_quote_createddate_sorting;

	@FindBy(xpath = "//*[@id='MapNewQuote']/div/div/div[2]/div/div/div[2]/div/div[3]/div/div/table/tbody/tr[1]/td[1]")
	private WebElement select_new_quoted;

	@FindBy(xpath = "//*[@id='MapNewQuote']/div/div/div[3]/div/button[1]")
	private WebElement select_new_quoted_save;
	
	//*[normalize-space()='Ok']

	// lead update_convert into opportunity button
	@FindBy(xpath = "//*[normalize-space()='Save & Convert']")
	private WebElement lead_update_convert_into_opp_button;

	@FindBy(xpath = "//*[@id='cWraper']/div/app-add-opportunities/div/div/div/div/form/div[1]/div[1]/div/div[2]/div[2]")
	private WebElement lead_Opp_location;


	@FindBy(xpath = "//button[contains(text(),'Confirm')]")
	private WebElement lead_already_exits_quote_availbale;

	@FindBy(xpath = "//*[@id='new_map_copy_quote']/div/div/div[3]/div/button[1]")
	private WebElement lead_already_exits_quote_availbale_second_popup_visible;

	@FindBy(xpath = "//*[@id='MapNewQuote']/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[1]/div/ng-multiselect-dropdown/div/div[1]/span/span[2]/span")
	private WebElement acquisition_contract_type_ownbook;

	// env table elements list
	@FindAll({ @FindBy(xpath = "//*[@class='table mb-0 levelthreetr']/tbody/tr/td/div") })
	public List<WebElement> lead_page_table_elements_list;

	// list of quotes for mapping
	@FindAll({ @FindBy(xpath = "//tr//td") })
	public List<WebElement> quotes_for_mapping_list;
	
	@FindBy(xpath = "//*[@id='vehicleRequest2']//tbody//thead//th")
	private List<WebElement> list_of_header_elements_in_table;
	

	@FindBy(xpath = "//div[@data-coc='coc']//button[@class='btn btn-secondary float-right'][normalize-space()='Ok']")
	private WebElement map_quote_ok_button;
	
	Leads obj_leads_page;

	public Leads() {
		PageFactory.initElements(driver, this);

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

	}

	public String add_new_lead(String sheet_name) throws InterruptedException, IOException {
		Thread.sleep(12000);

		Click.on(driver, leads, 120);

		LO.print("Clicked on Leads page");
		System.out.println("Clicked on Leads page");

		ExplicitWait.visibleElement(driver, add_lead, 40);

		Click.on(driver, add_lead, 120);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Thread.sleep(2000);

		Dropdown.selectByVisibleText(driver, general_lead_source, " Instagram ", 120);

		Dropdown.selectByVisibleText(driver, general_entry_type, " Lease ", 120);

		Dropdown.selectByVisibleText(driver, status, " New/Open ", 120);

		Click.sendKeys(driver, general_assigned_to, "QA Sales", 120);

		LO.print("Lead is assigned");
		System.out.println("Lead is assigned");

		Thread.sleep(2000);

		Click.on(driver, general_assigned_to_option, 120);

		Dropdown.selectByVisibleText(driver, customer_type, " Individual ", 120);

		LO.print("Customer type - " + "Indivisual" + " selected");
		System.out.println("Customer type - " + "Indivisual" + " selected");

		js = (JavascriptExecutor) driver;

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Click.sendKeys(driver, customer_name, "QA ", 120);

		Thread.sleep(4000);

		Click.on(driver, customer_name_option, 20);

		Click.on(driver, add_new_vehicle_request, 120);

		LO.print("Clicked on Add new vehicle request");
		System.out.println("Clicked on Add new vehicle request");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Click.on(driver, channel, 120);

		Thread.sleep(2000);

		WebElement ele = driver.findElement(By.xpath("(//*[@class='item2']//*[contains(text(),'Broker')])[1]"));

		js.executeScript("arguments[0].click();", ele);

		LO.print("Channel type Broker has been selected");
		System.out.println("Channel type Broker has been selected");

		Click.on(driver, channel, 120);

		Click.on(driver, save_vehicle_request, 120);

		Click.sendKeys(driver, add_note, "Test Note", 120);

		Click.on(driver, post, 120);

		Click.on(driver, map_new_quote, 120);

		LO.print("Clicked on Map new quote");
		System.out.println("Clicked on Map new quote");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		String quote_no = GetExcelFormulaValue.get_cell_value(0, 0, sheet_name);

		List<WebElement> table_data = driver.findElements(By.xpath("(//tbody)[3]/tr/td"));

		for (int i = 0; i <= table_data.size() - 1; i++) {

			if (table_data.get(i).getText().equals(quote_no)) {
				ExplicitWait.clickableElement(driver, table_data.get(i - 1), 120);
				table_data.get(i - 1).click();
			}
		}

		LO.print("New Quote has been mapped with lead");
		System.out.println("New Quote has been mapped with lead");

		Click.on(driver, ok_button, 120);

		Thread.sleep(7000);

		ExplicitWait.clickableElement(driver, save_and_convert_button, 120);

		js.executeScript("arguments[0].click();", save_and_convert_button);

		LO.print("Clicked on save and convert button");
		System.out.println("Clicked on save and convert button");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, opportunity_ref_no, 120);

		String opportunityRefNo = opportunity_ref_no.getText();

		System.out.println(opportunityRefNo);

		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));

		XSSFWorkbook wb = new XSSFWorkbook(in);

		wb.getSheet(sheet_name).getRow(0).getCell(1).setCellValue(opportunityRefNo);

		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));

		wb.write(out);

		out.close();

		LO.print("Lead converted to opportuninty successfully");
		System.out.println("Lead converted to opportuninty successfully");

		Thread.sleep(4000);

		js.executeScript("arguments[0].click();", update_and_exit);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		return driver.getTitle();

	}

	// Adding the Individual customer

	public void lead_General_info() throws Exception

	{

		System.out.println("**********************Lead page will open***********************");

		System.out.println("*********************************************");
		System.out.println("*********************************************");
		System.out.println("*********************************************");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		Click.on(driver, leads, 20);
		HelperClass.highlightElement(driver, leads); // Click on Leads Menu Link
		//Click.on(driver, leads, 120);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		

		LO.print("Clicked on Leads on Side Menu");
		System.out.println("Clicked on Leads on Side Menu");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		// Add Lead Button
		ExplicitWait.visibleElement(driver, add_lead, 120);

		Click.on(driver, add_lead, 120);
		System.out.println("Clicked on Leads button");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		LO.print("Clicked on add lead");
		System.out.println("Clicked on add lead");

		// Thread.sleep(1000);

		HelperClass.highlightElement(driver, general_lead_source);
		Dropdown.selectByVisibleText(driver, general_lead_source, " Instagram ", 120);

		HelperClass.highlightElement(driver, general_entry_type);

		//Dropdown.selectByVisibleText(driver, general_entry_type, " Lease ", 120);

		HelperClass.highlightElement(driver, status);

		Dropdown.selectByVisibleText(driver, status, " New/Open ", 120);
		// Dropdown.selectByVisibleText(driver, status, " Closed ", 120);

		Click.sendKeys(driver, general_assigned_to, "arpita ", 120);
		general_assigned_to.sendKeys("thakkar");

		LO.print("Lead is assigned");
		System.out.println("Lead is assigned");

		// Thread.sleep(1000);

		Click.on(driver, general_assigned_to_option, 120);

		// Adding the General Notes

		HelperClass.highlightElement(driver, general_notes);
		Click.sendKeys(driver, general_notes, "TestNotes", 120);

		LO.print("Added data in General Notes");
		System.out.println("Added data in General Notes");

	}

	public void lead_Customer_info_individual() throws Exception

	{

		Dropdown.selectByVisibleText(driver, customer_type, " Personal ", 120);

		LO.print("Customer type - " + "Individual" + " selected");
		System.out.println("Customer type - " + "Individual" + " selected");

		// js = (JavascriptExecutor) driver;

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, customer_name, 30);
		Thread.sleep(3000);
		customer_name.sendKeys("A");
		Thread.sleep(500);
		customer_name.sendKeys("u");
		Thread.sleep(500);
		customer_name.sendKeys("t");
		Thread.sleep(500);
		customer_name.sendKeys("o");
		Thread.sleep(500);
		customer_name.sendKeys("m");
		Thread.sleep(500);
		customer_name.sendKeys("a");
		Thread.sleep(500);
		customer_name.sendKeys("t");
		Thread.sleep(500);
		customer_name.sendKeys("i");		
		Thread.sleep(500);
		customer_name.sendKeys("o");
		Thread.sleep(500);
		customer_name.sendKeys("n");
	    Thread.sleep(500);
		customer_name.sendKeys(" ");
	    Thread.sleep(500);
		customer_name.sendKeys("P");
	    Thread.sleep(500);
		customer_name.sendKeys("e");

		
   	// Thread.sleep(4000);

		Click.on(driver, customer_name_option, 20);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

	}

	// Customer info for business

	public void lead_Customer_info_business() throws Exception

	{
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Dropdown.selectByVisibleText(driver, customer_type, " Business ", 120);

		LO.print("Customer type - " + "Business" + " selected");
		System.out.println("Customer type - " + "Business" + " selected");

		// js = (JavascriptExecutor) driver;

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		Thread.sleep(4000);
		customer_name.sendKeys("A");
		Thread.sleep(500);
		customer_name.sendKeys("u");
		Thread.sleep(500);
		customer_name.sendKeys("t");
		Thread.sleep(500);
		customer_name.sendKeys("o");
		Thread.sleep(500);
		customer_name.sendKeys("m");
		Thread.sleep(500);
		customer_name.sendKeys("a");
		Thread.sleep(500);
		customer_name.sendKeys("t");
		Thread.sleep(500);
		customer_name.sendKeys("i");
		Thread.sleep(500);
		customer_name.sendKeys("o");
		Thread.sleep(500);
		customer_name.sendKeys("n");
		Thread.sleep(500);

		Click.on(driver, customer_name_option, 120);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);



	}

	public void lead_vehicle_request_broker() throws Exception

	{

		// Thread.sleep(4000);

		HelperClass.highlightElement(driver, add_new_vehicle_request);

		Click.on(driver, add_new_vehicle_request, 120);

		LO.print("Clicked on Add new vehicle request");
		System.out.println("Clicked on Add new vehicle request");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		// Thread.sleep(4000);

		HelperClass.highlightElement(driver, channel);

		Click.on(driver, channel, 120);

//	Thread.sleep(2000);

		Click.on(driver, channel_broker_value, 120);

		LO.print("Clicked on broker channel");
		System.out.println("Clicked on broker channel");

		// Thread.sleep(2000);
		// channel_broker_value.sendKeys(Keys.TAB);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Thread.sleep(5000);
		Click.on(driver, add_new_vehicle_save, 120);

		LO.print("Vehicle request for broker has been saved");
		System.out.println("Vehicle request for broker has been saved");

	}

	public void lead_vehicle_request_Outright_Purchase() throws Exception

	{

		 

		HelperClass.highlightElement(driver, add_new_vehicle_request);

		Click.on(driver, add_new_vehicle_request, 120);

		LO.print("Clicked on Add new vehicle request");
		System.out.println("Clicked on Add new vehicle request");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		 
		HelperClass.highlightElement(driver, channel);

		Click.on(driver, channel, 120);

        Thread.sleep(2000);

		Click.on(driver, channel_retail_sales, 120);

		LO.print("Channel- Retail Sales Selected");
		System.out.println("Channel- Retail Sales Selected");

		// Thread.sleep(2000);
		// channel_broker_value.sendKeys(Keys.TAB);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Thread.sleep(5000);
		Click.on(driver, add_new_vehicle_save, 120);

		LO.print("Vehicle request for broker has been saved");
		System.out.println("Vehicle request for broker has been saved");

	}

	
	public void lead_vehicle_request_ownbook() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Click.on(driver, add_new_vehicle_request, 120);

		LO.print("Clicked on Add new vehicle request");
		System.out.println("Clicked on Add new vehicle request");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		// Thread.sleep(4000);

		
		 

		Click.on(driver, channel, 120);

		// Thread.sleep(2000);

		Click.on(driver, channel_ownbook_value, 120);

		LO.print("Clicked on ownbook channel");
		System.out.println("Clicked on ownbook channel");

		// Thread.sleep(2000);
		// channel_broker_value.sendKeys(Keys.TAB);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Thread.sleep(5000);
		Click.on(driver, add_new_vehicle_save, 120);

		LO.print("Vehicle request for broker has been saved");
		System.out.println("Vehicle request for broker has been saved");

	}

	// Add Vehicle request for Business

	public void lead_vehicle_request_business() throws Exception

	{

		// Thread.sleep(4000);

		Click.on(driver, add_new_vehicle_request, 120);

		LO.print("Clicked on Add new vehicle request");
		System.out.println("Clicked on Add new vehicle request");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		// Thread.sleep(4000);

		HelperClass.highlightElement(driver, channel);

		Click.on(driver, channel, 120);

		// Thread.sleep(2000);

		Click.on(driver, channel_broker_value, 120);

		LO.print("Clicked on broker channel");
		System.out.println("Clicked on broker channel");

		// Thread.sleep(2000);
		// channel_broker_value.sendKeys(Keys.TAB);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		// Thread.sleep(5000);
		Click.on(driver, add_new_vehicle_save, 120);

		LO.print("Vehicle request for broker has been saved");
		System.out.println("Vehicle request for broker has been saved");

	}

	public void lead_map_new_quote_broker_individual() throws Exception

	{

//  Map New Quote 

		// Thread.sleep(5000);

		ExplicitWait.visibleElement(driver, Map_New_quote_icon, 20);
		HelperClass.highlightElement(driver, Map_New_quote_icon);

		LO.print("Click on Map New Quote icon");
		System.out.println("Click on Map New Quote icon");

		// Thread.sleep(5000);

		Click.on(driver, Map_New_quote_icon, 120);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Click.on(driver, acquisition_contract_type, 120);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		LO.print("Click on acquisition contract type icon");
		System.out.println("Click on acquisition contract type icon");

		// Thread.sleep(5000);
		Click.on(driver, acquisition_contract_type_broker_value, 120);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		// Thread.sleep(5000);
		Click.on(driver, customer_contract_type, 120);

		// Thread.sleep(5000);
		
		String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
			
		if (classOrMethodName.contains("individual_hire")) {
			Click.on(driver, customer_contract_type_pch_value, 120);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		}

		if (classOrMethodName.contains("individual_purchase")) {

			Click.on(driver, customer_contract_type_pcp_value, 120);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		}


		LO.print("Click on customer contract type icon");
		System.out.println("Click on customer contract type icon");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		// Thread.sleep(5000);
		Click.on(driver, map_new_quote_search, 120);

		LO.print("Click on search button");
		System.out.println("Click on search button");
//	
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		// Below code is written by Mehul

		Click.on(driver, map_new_quote_createddate_sorting, 120);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		LO.print("Click on created date button 1st ");
		System.out.println("Click on created date button 1st");

		Click.on(driver, map_new_quote_createddate_sorting, 120);

		LO.print("Click on created date button 2nd");
		System.out.println("Click on created date button 2nd");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		// Below code is written by nitein to map exact quote
		
		
///*********************
		obj_acq_listing_page = new AcquisitionListingPage();
		
		String sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);		 

		String quote_no = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);

		 ExplicitWait.waitForListOfVisibleElements(driver, lead_page_table_elements_list, 30);

		for (int i = 0; i <= quotes_for_mapping_list.size() - 1; i++) {

			if (quotes_for_mapping_list.get(i).getText().equals(quote_no)) {

				System.out.println(quotes_for_mapping_list.get(i).getText());

				Click.on(driver, quotes_for_mapping_list.get(i - 1), 60);

				LO.print("New Quote has been mapped with lead");
				System.out.println("New Quote has been mapped with lead");

				break;
			}

			else if(i == 10);
			{	LO.print("Data for given quote no."+quote_no+" is not available in the table");
				System.out.println("Data for given quote no."+quote_no+" is not available in the table");			
				
			}

		}

		// Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Click.on(driver, select_new_quoted_save, 120);

		

		try

		{

			if (lead_already_exits_quote_availbale.isEnabled())

			{
				Thread.sleep(5000);
				System.out.println("click on OK button for lead already existing quote  ");
				LO.print("click ok button on Map  new quote ");

				Click.on(driver, lead_already_exits_quote_availbale, 30);

				Thread.sleep(5000);

				System.out.println("click on OK button for copy quote  ");
				LO.print("click on OK button for copy quote ");

				Click.on(driver, lead_already_exits_quote_availbale_second_popup_visible, 30);

				System.out.println("New copy quote is mapped with this Lead ");
				LO.print("New copy quote is mapped with this Lead ");

			}
		}

		catch (Exception e) {
			// System.out.println( e );

		}

	}

	public void lead_map_new_quote_ownbook_individual() throws Exception

	{

//Map New Quote 

//Thread.sleep(5000);	
		HelperClass.highlightElement(driver, Map_New_quote_icon);

		LO.print("Click on Map New Quote icon");
		System.out.println("Click on Map New Quote icon");

//Thread.sleep(5000);
		Click.on(driver, Map_New_quote_icon, 120);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		Click.on(driver, acquisition_contract_type_ownbook, 120);

		LO.print("Click on acquisition contract type icon");
		System.out.println("Click on acquisition contract type icon");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
//Thread.sleep(5000);
		Click.on(driver, acquisition_contract_type_ownbook_value, 120);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

//Thread.sleep(5000);
		Click.on(driver, customer_contract_type, 120);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
//Thread.sleep(5000);
		Click.on(driver, customer_contract_type_pch_value, 120);

		LO.print("Click on customer contract type icon");
		System.out.println("Click on customer contract type icon");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		Thread.sleep(5000);
		Click.on(driver, map_new_quote_search, 120);

		LO.print("Click on search button");
		System.out.println("Click on search button");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
//Thread.sleep(5000);
		Click.on(driver, map_new_quote_createddate_sorting, 120);

		LO.print("Click on created date button 1st ");
		System.out.println("Click on created date button 1st");

		Click.on(driver, map_new_quote_createddate_sorting, 120);

		LO.print("Click on created date button 2nd");
		System.out.println("Click on created date button 2nd");

//Thread.sleep(5000);
		Click.on(driver, select_new_quoted, 120);

		Thread.sleep(5000);

		Click.on(driver, select_new_quoted_save, 120);

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

	public void lead_map_new_quote() throws Exception

	{

//Map New Quote 
		ExplicitWait.visibleElement(driver, Map_New_quote_icon, 30);

		HelperClass.highlightElement(driver, Map_New_quote_icon);

		LO.print("Click on Map New Quote icon");
		System.out.println("Click on Map New Quote icon");

		Click.on(driver, Map_New_quote_icon, 120);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Thread.sleep(7000);
		
		Click.on(driver, acquisition_contract_type, 120);

		LO.print("Click on acquisition contract type icon");
		System.out.println("Click on acquisition contract type icon");
		
		String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();

		if (classOrMethodName.contains("broker"))
		{			
		Click.on(driver, acquisition_contract_type_broker_value, 120);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		}
		
		if (classOrMethodName.contains("ownbook"))
		{			
		Click.on(driver, acquisition_contract_type_ownbook_value, 120);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		}
		
		if (classOrMethodName.contains("OP_OP"))
		{			
		Click.on(driver, acquisition_contract_type_outright_purchase, 120);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		}
		
		
		Click.on(driver, customer_contract_type, 120);
		LO.print("Click on customer contract type icon");
		System.out.println("Click on customer contract type icon");
		
		

		if (classOrMethodName.contains("business_hire")) {
			Click.on(driver, customer_business_contract_type_bch_value, 120);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		}

		if (classOrMethodName.contains("business_purchase")) {

			Click.on(driver, customer_business_contract_type_hpnr_value, 120);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		}
		
		if (classOrMethodName.contains("individual_hire")) {
			Click.on(driver, customer_contract_type_pch_value, 120);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		}
		
		if (classOrMethodName.contains("individual_purchase")) {
			
			Click.on(driver, customer_contract_type_pcp_value, 120);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		}
		
		if (classOrMethodName.contains("OP_OP")) {
			
			Click.on(driver, customer_contract_type_outright_purchase, 120);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		}

		Click.on(driver, map_new_quote_search, 120);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		LO.print("Click on search button");
		System.out.println("Click on search button");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Click.on(driver, map_new_quote_createddate_sorting, 120);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		LO.print("Click on created date button 1st ");
		System.out.println("Click on created date button 1st");

		Click.on(driver, map_new_quote_createddate_sorting, 120);

		LO.print("Click on created date button 2nd");
		System.out.println("Click on created date button 2nd");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

        obj_acq_listing_page = new AcquisitionListingPage();
		
		String sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);

		String quote_no = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);

		for (int i = 0; i <= quotes_for_mapping_list.size() - 1; i++) {

			if (quotes_for_mapping_list.get(i).getText().equals(quote_no)) {

				Click.on(driver, quotes_for_mapping_list.get(i - 1), 60);

				break;

			}
			
			else if (i == 50) {

				LO.print          ("The given quote "+quote_no+" is not available in pop up listing");
				System.err.println("The given quote "+quote_no+" is not available in pop up listing");
				
				break;
				
			}

		}

		LO.print("New Quote has been mapped with lead");
		System.out.println("New Quote has been mapped with lead");


		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Click.on(driver, select_new_quoted_save, 120);

		Thread.sleep(7000);

		try

		{

			if (lead_already_exits_quote_availbale.isEnabled())

			{

				System.out.println("click on OK button for lead already existing quote  ");
				LO.print("click ok button on Map  new quote ");

				Click.on(driver, lead_already_exits_quote_availbale, 10);

				Thread.sleep(5000);

				System.out.println("click on OK button for copy quote  ");
				LO.print("click on OK button for copy quote ");

				Click.on(driver, lead_already_exits_quote_availbale_second_popup_visible, 10);

				System.out.println("New copy quote is mapped with this opportunity ");
				// LO.print("New copy quote is mapped with this opportunity ");

			}
		}

		catch (Exception e) {
		}

	}

	public boolean verify_table_values_on_lead_page() throws IOException, InterruptedException, ClassNotFoundException {

		// part first -- getting actual values from screen --on lead page before saving
		// wait for table elements to be loaded
		ExplicitWait.waitForListOfVisibleElements(driver, lead_page_table_elements_list, 60);

		// get quote ref no.

		String quotRefNoActual = lead_page_table_elements_list.get(0).getText();
		HelperClass.highlightElement(driver, lead_page_table_elements_list.get(0));

		// get Vehicle name
		String vehicleNameActual = lead_page_table_elements_list.get(1).getText();
		HelperClass.highlightElement(driver, lead_page_table_elements_list.get(1));

		// get contract type
		String contractTypeActual = lead_page_table_elements_list.get(2).getText();
		HelperClass.highlightElement(driver, lead_page_table_elements_list.get(2));

		// get monthly payment
		String[] monthlyPayment = lead_page_table_elements_list.get(3).getText().split(" ");
		HelperClass.highlightElement(driver, lead_page_table_elements_list.get(3));
		double monthlyFinanceRentalActual = Double.parseDouble(RemoveComma.of(monthlyPayment[1]));

		// get mileage
		String[] mileage = lead_page_table_elements_list.get(4).getText().split(" ");
		HelperClass.highlightElement(driver, lead_page_table_elements_list.get(4));
		double mileageActual = Double.parseDouble(mileage[1]);

		// get terms
		String[] term = lead_page_table_elements_list.get(5).getText().split(" ");
		HelperClass.highlightElement(driver, lead_page_table_elements_list.get(5));
		double termActual = Double.parseDouble(term[1]);

		// get Expiry date
		String[] ExpiryDate = lead_page_table_elements_list.get(6).getText().split(" ");
		HelperClass.highlightElement(driver, lead_page_table_elements_list.get(6));
		String expiryDateActual = RemoveComma.of(ExpiryDate[0]);

		// part second -- Getting values from quote save excel sheet

		// define sheet name from which values to be taken

        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();

		obj_acq_listing_page = new AcquisitionListingPage();
		
		String sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);

		String quotRefNoExpected = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
		String vehicleNameExpected = GetExcelFormulaValue.get_cell_value(1, 10, sheetName);

		String contractTypeExpected = GetExcelFormulaValue.get_cell_value(4, 1, sheetName);
		double termExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(4, 3, sheetName));

		double mileageExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(6, 1, sheetName));
		double monthlyFinanceRentalExpected = Double.parseDouble(GetExcelFormulaValue.get_cell_value(6, 3, sheetName));


		String expiryDateExpected = "";
		
		String className = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();

		if (className.contains("broker_business_hire")) 
		{expiryDateExpected = GetExcelFormulaValue.get_cell_value(12, 1, sheetName);}

		
		if (className.contains("broker_individual_hire")) 
		{expiryDateExpected = GetExcelFormulaValue.get_cell_value(12, 1, sheetName);}

		
		if (className.contains("broker_business_purchase")) 
		{expiryDateExpected = GetExcelFormulaValue.get_cell_value(10, 1, sheetName);}

		
		if (className.contains("broker_individual_purchase")) 
		{expiryDateExpected = GetExcelFormulaValue.get_cell_value(12, 1, sheetName);}
	
		
		if (className.contains("ownbook")) 
		{
			LocalDate futureDate = LocalDate.now().plusMonths(1).minusDays(1);			
			String str1[] = String.valueOf(futureDate).split("-");				 
			
    		expiryDateExpected = str1[2].concat("/").concat(str1[1]).concat("/").concat(str1[0]);
			

			
		}
	
		
		if (className.contains("funder")) 
		{expiryDateExpected = GetExcelFormulaValue.get_cell_value(1, 5, sheetName);}
	



		// part third Comparing actual and expected

		System.out.println("");
		LO.print("");
		System.out.println("Started Verifying Table values");
		LO.print("Started Verifying Table values");

		int count = 0;

		// comparing quote no.
		if (quotRefNoActual.equals(quotRefNoExpected)) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(quotRefNoActual + " = " + quotRefNoExpected);
			LO.print(quotRefNoActual + " = " + quotRefNoExpected);
			System.out.println("Quote no. compared and found ok");
			LO.print("Quote no. compared and found ok");
		} else {
			System.out.println("");
			LO.print("");
			System.err.println(quotRefNoActual + " != " + quotRefNoExpected);
			LO.print(quotRefNoActual + " != " + quotRefNoExpected);
			System.err.println("Quote no. compared but found not ok");
			LO.print("Quote no. compared but found not ok");
		}

		// comparing vehicle name
		if (vehicleNameActual.equals(vehicleNameExpected)) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(vehicleNameActual + " = " + vehicleNameExpected);
			LO.print(vehicleNameActual + " = " + vehicleNameExpected);
			System.out.println("Vehicle name compared and found ok");
			LO.print("Vehicle name compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(vehicleNameActual + " != " + vehicleNameExpected);
			LO.print(vehicleNameActual + " != " + vehicleNameExpected);
			System.err.println("Vehicle name compared but found not ok");
			LO.print("Vehicle name compared but found not ok");

		}

		// comparing contract type
		if (contractTypeActual.equals(contractTypeExpected)) {
			count++;
			System.out.println("");
			LO.print("");
			System.out.println(contractTypeActual + " = " + contractTypeExpected);
			LO.print(contractTypeActual + " = " + contractTypeExpected);
			System.out.println("Contract type compared and found ok");
			LO.print("Contract type compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(contractTypeActual + " != " + contractTypeExpected);
			LO.print(contractTypeActual + " != " + contractTypeExpected);
			System.err.println("Contract type compared but found not ok");
			LO.print("Contract type compared but found not ok");
		}

		// comparing monthly finance payment
		if (monthlyFinanceRentalActual == monthlyFinanceRentalExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(monthlyFinanceRentalActual + " = " + monthlyFinanceRentalExpected);
			LO.print(monthlyFinanceRentalActual + " = " + monthlyFinanceRentalExpected);
			System.out.println("Monthly finance rental compared and found ok");
			LO.print("Monthly finance rental compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(monthlyFinanceRentalActual + " != " + monthlyFinanceRentalExpected);
			LO.print(monthlyFinanceRentalActual + " != " + monthlyFinanceRentalExpected);
			System.err.println("Monthly finance rental compared but found not ok");
			LO.print("Monthly finance rental compared but found not ok");

		}

		// comparing mileage
		if (mileageActual == mileageExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(mileageActual + " = " + mileageExpected);
			LO.print(mileageActual + " = " + mileageExpected);
			System.out.println("Mileage compared and found ok");
			LO.print("Mileage compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(mileageActual + " != " + mileageExpected);
			LO.print(mileageActual + " != " + mileageExpected);
			System.err.println("Mileage compared but found not ok");
			LO.print("Mileage compared but found not ok");

		}

		// comparing term
		if (termActual == termExpected) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(termActual + " = " + termExpected);
			LO.print(termActual + " = " + termExpected);
			System.out.println("Terms compared and found ok");
			LO.print("Terms compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(termActual + " != " + termExpected);
			LO.print(termActual + " != " + termExpected);
			System.err.println("Terms compared but found not ok");
			LO.print("Terms compared but found not ok");

		}

		// comparing expiry date
		if (expiryDateActual.equals(expiryDateExpected)) {
			count++;

			System.out.println("");
			LO.print("");
			System.out.println(expiryDateActual + " = " + expiryDateExpected);
			LO.print(expiryDateActual + " = " + expiryDateExpected);
			System.out.println("Expiry date compared and found ok");
			LO.print("Expiry date compared and found ok");

		} else {
			System.out.println("");
			LO.print("");
			System.err.println(expiryDateActual + " != " + expiryDateExpected);
			LO.print(expiryDateActual + " != " + expiryDateExpected);
			System.err.println("Expiry date compared but found not ok");
			LO.print("Expiry date compared but found not ok");

		}

		boolean status = false;
		if (count == 7)

		{
			status = true;
		}

		return status;

	}

	
public boolean verify_quote_no_and_sales_price_displayed_in_table() throws IOException, ClassNotFoundException {
		
		
					LO.print("Verifying Quote no. and Sales Price Displayed in the Table");
					System.out.println("Verifying Quote no. and Sales Price Displayed in the Table");
					
					obj_leads_page = new  Leads();			
					
					
					ExplicitWait.waitForListOfVisibleElements(driver, list_of_header_elements_in_table, 10);
					
					int noOfHeaderElements = list_of_header_elements_in_table.size();
					
					String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();

					String sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
					
					//Getting Expected values
					
					String quotRefNoExpected = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
					
					String salesPriceExpected = GetExcelFormulaValue.get_cell_value(1, 1, sheetName); 					
					
					//Getting Actual values from oppo table 
					
					boolean  quoteNo = obj_leads_page.verify_sales_price_displayed_in_table_with_the_help_of_header_text(noOfHeaderElements, "QUOTE REF", quotRefNoExpected);
					
					boolean  salesPrice = obj_leads_page.verify_sales_price_displayed_in_table_with_the_help_of_header_text(noOfHeaderElements, "MONTHLY PAYMENT", salesPriceExpected);

					if(quoteNo == true && salesPrice== true)
					{
						return true;	
					}
					else
					{
						return false;	
					}
					
							

	}

	
	
	public boolean verify_sales_price_displayed_in_table_with_the_help_of_header_text(int noOfHeaderElements, String headerElementText, String expectedValue) {
		
		
		boolean flag= false;
		
		for(int i=1; i<=noOfHeaderElements; i++)
		{
			String webElementText = driver.findElement(By.xpath("//*[@id='vehicleRequest2']//tbody//thead/tr/th["+i+"]")).getText();
			
			
		
			if(webElementText.equalsIgnoreCase(headerElementText))
        	{
				String valueText = driver.findElement(By.xpath("//*[@id='vehicleRequest2']//tbody//tbody/tr/td["+i+"]")).getText();
				
		     if(webElementText.contains("QUOTE"))
		     {
					if(valueText.equalsIgnoreCase(expectedValue))
		        	{
						LO.print("Expected "+expectedValue+" and Actual "+valueText+" are equal , Hence Passed");
						System.out.println("Expected "+expectedValue+" and Actual "+valueText+" are equal , Hence Passed");	
						
						flag =true ;
						break;
						
		        	}else
					{
						LO.print("Expected "+expectedValue+" and Actual "+valueText+" are not equal , Hence Failed");
						System.err.println("Expected "+expectedValue+" and Actual "+valueText+" are not equal , Hence Failed");	

					}
		     }
					else if(webElementText.contains("MONTHLY PAYMENT"))
					{
						
						String valueText1 = RemoveComma.of(valueText.substring(2));
												
						if(Difference.of_two_Double_Values(Double.parseDouble(valueText1), Double.parseDouble(expectedValue))<0.2)
						{
							LO.print("Expected "+expectedValue+" and Actual "+valueText+" are equal , Hence Passed");
							System.out.println("Expected "+expectedValue+" and Actual "+valueText+" are equal , Hence Passed");	
							flag =true ;
		                   break;
						}
						else
						{
							LO.print("Expected "+expectedValue+" and Actual "+valueText+" are not equal , Hence Failed");
							System.err.println("Expected "+expectedValue+" and Actual "+valueText+" are not equal , Hence Failed");	

						}
					}
					
					
	        	}		
				
        	}
		return flag;
	}

	
	public void lead_map_new_quote_broker_with_hpnr_business() throws Exception

	{

         //Map New Quote 

		HelperClass.highlightElement(driver, Map_New_quote_icon);

		LO.print("Click on Map New Quote icon");
		System.out.println("Click on Map New Quote icon");

		Click.on(driver, Map_New_quote_icon, 120);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Click.on(driver, acquisition_contract_type, 120);

		LO.print("Click on acquisition contract type icon");
		System.out.println("Click on acquisition contract type icon");

		Click.on(driver, acquisition_contract_type_broker_value, 120);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Click.on(driver, customer_contract_type, 120);

		LO.print("Click on customer contract type icon");
		System.out.println("Click on customer contract type icon");

		Click.on(driver, customer_business_contract_type_hpnr_value, 120);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		Click.on(driver, map_new_quote_search, 120);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		LO.print("Click on search button");
		System.out.println("Click on search button");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		Click.on(driver, map_new_quote_createddate_sorting, 120);

		LO.print("Click on created date button 1st ");
		System.out.println("Click on created date button 1st");

		Click.on(driver, map_new_quote_createddate_sorting, 120);

		LO.print("Click on created date button 2nd");
		System.out.println("Click on created date button 2nd");

		System.out.println("Select the checkbox for new quote ");
		LO.print("Select the checkbox for new quote ");
		Click.on(driver, select_new_quoted, 120);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		System.out.println("click ok button on Map  new quote ");
		LO.print("click ok button on Map  new quote ");

		Click.on(driver, select_new_quoted_save, 120);

		Thread.sleep(5000);

		try

		{

			if (lead_already_exits_quote_availbale.isEnabled())

			{

				System.out.println("click on OK button for lead already existing quote  ");
				LO.print("click ok button on Map  new quote ");

				Click.on(driver, lead_already_exits_quote_availbale, 20);

				ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
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

	public void save_and_convert_after_mapping_a_quote() throws Exception

	{

		System.out.println("**********************Saving  the Quote ***********************");

		System.out.println("*********************************************");
		System.out.println("*********************************************");

//Map New Quote 

		LO.print("Click on Lead save and Convert button");
		System.out.println("Click on Lead save and Convert button");

		Click.on(driver, lead_update_convert_into_opp_button, 120);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		System.out.println("**********************Quoted is saved successfully ***********************");

		System.out.println("*********************************************");
		System.out.println("*********************************************");

	}


	public void get_the_opportunity_no_after_converting_lead_into_opportunity() throws Exception

	{

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		String Lead_OpportunityId = lead_Opp_location.getText();

		System.out.println("");
		LO.print("");
		System.out.println("In Lead created Opportunity Id is =" + Lead_OpportunityId);
		LO.print("In Lead created Opportunity Id is =" + Lead_OpportunityId);
		System.out.println("");
		LO.print("");
		
		//Getting sheet name
        String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		obj_acq_listing_page = new AcquisitionListingPage();
		

	
		String sheetName = obj_acq_listing_page.quote_save_sheet_name_from_quote_save_excel_sheet(classOrMethodName);
		
		
		//writing oppo. id to excel
		

		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);
		wb.getSheet(sheetName).getRow(1).getCell(2).setCellValue(Lead_OpportunityId);

		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		wb.write(out);
		wb.close();
		
		
		FileInputStream in1 = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook wb1 = new XSSFWorkbook(in1);
		wb1.getSheet("Order_ID").getRow(4).getCell(0).setCellValue(Lead_OpportunityId);
		FileOutputStream out1 = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		wb1.write(out1);
		wb1.close();

	}



	public void lead_communication_log_Add_Note() throws Exception

	{

		HelperClass.highlightElement(driver, communication_add_note_panel);

		communication_add_note_panel.click();

		LO.print("Clicked on Communication_Add note");
		System.out.println("Clicked on Add note ");

		HelperClass.highlightElement(driver, communication_add_note_input_box);

		Click.sendKeys(driver, communication_add_note_input_box, "Test Communication add a note ", 10);

		Thread.sleep(2000);

		communication_post_button.click();

		WebElement add_note_addedtext_webelement = driver.findElement(By.xpath("//div[@class ='commentmsg pt-1']"));

		String GettingMessageforNote = add_note_addedtext_webelement.getText();

		System.out.println(" GettingMessageforNote =" + GettingMessageforNote);

		if (GettingMessageforNote.equalsIgnoreCase("Test Communication add a note"))
			;

		{

			System.out.println("Added note is matched with screen communication note ");
		}

	}

	public void lead_communication_log_log_a_call() throws Exception

	{

		HelperClass.highlightElement(driver, Communication_Log_a_call_panel);

		Communication_Log_a_call_panel.click();

		LO.print("Clicked on Communication_Log_a_call");
		System.out.println("Clicked on Communication_Log_a_call");

		Click.sendKeys(driver, Communication_Log_a_call_input_box, "Test Communication add a call", 10);

		Thread.sleep(2000);

		communication_post_button.click();

	}

	public void lead_communication_log_send_email() throws Exception

	{

		Thread.sleep(2000);
		HelperClass.highlightElement(driver, communication_send_email_panel);

		communication_send_email_panel.click();

		LO.print("Clicked on Communication_email panel");
		System.out.println("Clicked on Communication email panel");

		Thread.sleep(2000);

		Click.sendKeys(driver, communication_send_email_subject, "Test Email", 10);

		Thread.sleep(2000);

		communication_send_email_button.click();

		HelperClass.highlightElement(driver, communication_send_email_button_confirm_yes_button);

		communication_send_email_button_confirm_yes_button.click();

	}

}
