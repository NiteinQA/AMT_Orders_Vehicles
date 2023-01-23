package com.amt.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.HelperClass;
import com.amt.testUtil.Dropdown;

public class Leads extends TestBase {

	JavascriptExecutor js;

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

	
	//Communication log webelement 
	
	//1.Add Note
	@FindBy(xpath = "//span[contains(text() , 'Add note')]")
	private WebElement communication_add_note_panel;
	
	//2.Add Note input box
	@FindBy(xpath = "//textarea[@id='enteredLog']")
	private WebElement communication_add_note_input_box;
	
	//3.Post Button
	@FindBy(xpath = "//button[contains(text(),'Post')]")
	private WebElement communication_post_button;
	
	
	
	//---------Log a call -----
	
	// 4.Log a call 
	@FindBy(xpath =" //span[contains(text() , 'Log a call')]")
	private WebElement Communication_Log_a_call_panel;
	
	
	//Add Log a call input box
	@FindBy(xpath =" //textarea[@id='enteredLog']")
	private WebElement Communication_Log_a_call_input_box;
	
	
	//--------Send email 
	
	//5.Send email 
	
	
	@FindBy(xpath =" //span[contains(text(), 'Send email')]")
	private WebElement communication_send_email_panel;
	
	
    //6.Email subject
	
	
	@FindBy(xpath ="//input[@id='emailSubject']")
	private WebElement communication_send_email_subject;
	
	
      // 7.Send email button
   
	
	@FindBy(xpath ="//button[contains(text(),'Send email')]")
	private WebElement communication_send_email_button;

	  // 7.Send email button - yes button
	
	@FindBy(xpath ="//*[@id=\"save_email_popup1\"]/div/div//button[@class='btn btn-secondary float-right']")
	private WebElement communication_send_email_button_confirm_yes_button;
	
	
	
	
// Add new Vehicle request 
	
	
	@FindBy(xpath ="//*[@id=\"vehiclinfo2\"]/div/div/div/div[1]/div/ng-multiselect-dropdown/div/div[2]/ul[2]/li[1]/div")
	private WebElement channel_broker_value;
	
	// Ownbook value selection
	
	
	@FindBy(xpath ="//*[@id=\"vehiclinfo2\"]/div/div/div/div[1]/div/ng-multiselect-dropdown/div/div[2]/ul[2]/li[5]/div")
	private WebElement channel_ownbook_value;
	
	
	
	
	@FindBy(xpath ="//*[@id=\"LeadVehicleRequest\"]/div/div/div[3]/div/button[1]")
	private WebElement add_new_vehicle_save;
	

	
// 	 	Map_New_quote_icon

	@FindBy(xpath ="//*[@id=\"vehicleRequest2\"]/div/div/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div/table/tbody/tr/td[8]/div/a[3]/img")
	private WebElement Map_New_quote_icon;
	
	
	
	//*[@id="vehicleRequest2"]/div/div/div[2]/div/div/div/app-grid/div[2]/div/div[2]/div/table/tbody/tr/td[9]/div/a[3]/img
	
	////*[@id="MapNewQuote"]/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[1]/div/ng-multiselect-dropdown/div/div[1]/span/span[2]/span
	
	@FindBy(xpath ="//*[@id=\"MapNewQuote\"]/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[1]/div/ng-multiselect-dropdown/div/div[1]/span/span[2]/span")
	private WebElement acquisition_contract_type;
	
	//*[@id="MapNewQuote"]/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[1]/div/ng-multiselect-dropdown/div/div[1]/span/span[1]
	
	@FindBy(xpath ="//*[@id=\"MapNewQuote\"]/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[1]/div/ng-multiselect-dropdown/div/div[2]/ul[2]/li/div")
	private WebElement acquisition_contract_type_broker_value; 
	
	
	// acquisition_contract_type_ownbook_value
	
	@FindBy(xpath ="//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-add-leads[1]/app-map-new-quote[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/ng-multiselect-dropdown[1]/div[1]/div[2]/ul[2]/li[4]/div[1]")
	private WebElement acquisition_contract_type_ownbook_value; 
	
	
	
	
	@FindBy(xpath ="//*[@id=\"MapNewQuote\"]/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[2]/div/ng-multiselect-dropdown/div/div[1]/span/span[1]")
	private WebElement customer_contract_type;

	@FindBy(xpath ="//div[contains(text(),'Personal Contract Hire')]")
	private WebElement customer_contract_type_pch_value;
			
	
	@FindBy(xpath ="//div[contains(text(),'Business Contract Hire')]")
	private WebElement customer_contract_type_bch_value;
			
	

	@FindBy(xpath ="//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-add-leads[1]/app-map-new-quote[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/ng-multiselect-dropdown[1]/div[1]/div[2]/ul[2]/li[1]/div[1]")
	private WebElement customer_business_contract_type_bch_value;
	
	
	
	
	
	
	
	@FindBy(xpath ="//*[@id=\"MapNewQuote\"]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/div/button[2]")
	private WebElement map_new_quote_search;
			
	
	@FindBy(xpath ="//th[contains(text(),'Created date')]")
	private WebElement map_new_quote_createddate_sorting;

 
	
	
	
	@FindBy(xpath ="//*[@id=\"MapNewQuote\"]/div/div/div[2]/div/div/div[2]/div/div[3]/div/div/table/tbody/tr[1]/td[1]")
	private WebElement select_new_quoted;
				
	
	@FindBy(xpath ="//*[@id=\"MapNewQuote\"]/div/div/div[3]/div/button[1]")
	private WebElement select_new_quoted_save;
	
	
		// lead update_convert into opportunity button
	@FindBy(xpath ="//*[@id=\"cWraper\"]/div/app-add-leads/app-lead-header/div/div[2]/div/button[3]")
	private WebElement lead_update_convert_into_opp_button;
		  	
	
	
	@FindBy(xpath ="//*[@id=\"cWraper\"]/div/app-add-opportunities/div/div/div/div/form/div[1]/div[1]/div/div[2]/div[2]")
	private WebElement lead_Opp_location;
		  	
	
	//*[@id="cWraper"]/div/app-add-opportunities/div/div/div/div/form/div[1]/div[1]/div/div[2]/div[2]
	
	@FindBy(xpath ="//button[contains(text(),'Confirm')]")
	private WebElement lead_already_exits_quote_availbale;
	
	//*[@id="map_copy_quote"]/div/div/div[3]/div/button[1]
	
	//AlreadyexitsQuote = //button[normalize-space()='Confirm']
	
	
	@FindBy(xpath ="//*[@id=\"new_map_copy_quote\"]/div/div/div[3]/div/button[1]")
	private WebElement lead_already_exits_quote_availbale_second_popup_visible;
	
	  
	@FindBy(xpath ="//*[@id=\"MapNewQuote\"]/div/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div[1]/div/ng-multiselect-dropdown/div/div[1]/span/span[2]/span")
	private WebElement acquisition_contract_type_ownbook;


		 
	
	
	
	
	
	
	
	
	public Leads() {
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

	
	  public String add_new_lead(String sheet_name) throws InterruptedException,
	  IOException { Thread.sleep(3000);
	  
	  Click.on(driver, leads, 30);
	  
	  LO.print("Clicked on Leads page");
	  System.out.println("Clicked on Leads page");
	  
	  ExplicitWait.visibleElement(driver, add_lead, 40);
	  
	  Click.on(driver, add_lead, 30);
	  
	  ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	  
	  Thread.sleep(2000);
	  
	  Dropdown.selectByVisibleText(driver, general_lead_source, " Instagram ", 30);
	  
	  Dropdown.selectByVisibleText(driver, general_entry_type, " Lease ", 30);
	  
	  Dropdown.selectByVisibleText(driver, status, " New/Open ", 30);
	  
	  Click.sendKeys(driver, general_assigned_to, "QA Sales", 30);
	  
	  LO.print("Lead is assigned"); System.out.println("Lead is assigned");
	  
	  Thread.sleep(2000);
	  
	  Click.on(driver, general_assigned_to_option, 30);
	  
	  Dropdown.selectByVisibleText(driver, customer_type, " Individual ", 30);
	  
	  LO.print("Customer type - " + "Indivisual" + " selected");
	  System.out.println("Customer type - " + "Indivisual" + " selected");
	  
	  js = (JavascriptExecutor) driver;
	  
	  ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	  
	  Click.sendKeys(driver, customer_name, "QA ", 30);
	  
	  Thread.sleep(4000);
	  
	  Click.on(driver, customer_name_option, 20);
	  
	  Click.on(driver, add_new_vehicle_request, 30);
	  
	  LO.print("Clicked on Add new vehicle request");
	  System.out.println("Clicked on Add new vehicle request");
	  
	  ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	  
	  Click.on(driver, channel, 30);
	  
	  Thread.sleep(2000);
	  
	  WebElement ele = driver.findElement(By.xpath(
	  "(//*[@class='item2']//*[contains(text(),'Broker')])[1]"));
	  
	  js.executeScript("arguments[0].click();", ele);
	  
	  LO.print("Channel type Broker has been selected");
	  System.out.println("Channel type Broker has been selected");
	  
	  Click.on(driver, channel, 30);
	  
	  Click.on(driver, save_vehicle_request, 30);
	  
	  Click.sendKeys(driver, add_note, "Test Note", 30);
	  
	  Click.on(driver, post, 30);
	  
	  Click.on(driver, map_new_quote, 30);
	  
	  LO.print("Clicked on Map new quote");
	  System.out.println("Clicked on Map new quote");
	  
	  ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	  
	  String quote_no = GetExcelFormulaValue.get_cell_value(0, 0, sheet_name);
	  
	  List<WebElement> table_data =
	  driver.findElements(By.xpath("(//tbody)[3]/tr/td"));
	  
	  for (int i = 0; i <= table_data.size() - 1; i++) {
	  
	  if (table_data.get(i).getText().equals(quote_no)) {
	  ExplicitWait.clickableElement(driver, table_data.get(i - 1), 30);
	  table_data.get(i - 1).click(); } }
	  
	  LO.print("New Quote has been mapped with lead");
	  System.out.println("New Quote has been mapped with lead");
	  
	  Click.on(driver, ok_button, 30);
	  
	  Thread.sleep(7000);
	  
	  ExplicitWait.clickableElement(driver, save_and_convert_button, 30);
	  
	  js.executeScript("arguments[0].click();", save_and_convert_button);
	  
	  LO.print("Clicked on save and convert button");
	  System.out.println("Clicked on save and convert button");
	  
	  ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	  
	  ExplicitWait.visibleElement(driver, opportunity_ref_no, 30);
	  
	  String opportunityRefNo = opportunity_ref_no.getText();
	  
	  System.out.println(opportunityRefNo);
	  
	  FileInputStream in = new
	  FileInputStream(prop.getProperty("quote_save_excel_path"));
	  
	  XSSFWorkbook wb = new XSSFWorkbook(in);
	  
	  wb.getSheet(sheet_name).getRow(0).getCell(1).setCellValue(opportunityRefNo);
	  
	  FileOutputStream out = new
	  FileOutputStream(prop.getProperty("quote_save_excel_path"));
	  
	  wb.write(out);
	  
	  out.close();
	  
	  LO.print("Lead converted to opportuninty successfully");
	  System.out.println("Lead converted to opportuninty successfully");
	  
	  Thread.sleep(4000);
	  
	  js.executeScript("arguments[0].click();", update_and_exit);
	  
	  ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	  
	  return driver.getTitle();
	  
	  }
	 	 

	// Adding the Individual customer

	public void lead_General_info() throws Exception

	{
		Thread.sleep(3000);

		// HelperClass.highlightElement(driver, leads); //Click on Leads Menu Link
		Click.on(driver, leads, 30);

		LO.print("Clicked on Leads on Side Menu");
		System.out.println("Clicked on Leads om Side Menu");

		// Add Lead Button
		ExplicitWait.visibleElement(driver, add_lead, 40);

		Click.on(driver, add_lead, 30);
		System.out.println("Clicked on Leads button");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		LO.print("Clicked on add lead");
		System.out.println("Clicked on add lead");

		Thread.sleep(2000);

		HelperClass.highlightElement(driver, general_lead_source);
		Dropdown.selectByVisibleText(driver, general_lead_source, " Instagram ", 30);

		HelperClass.highlightElement(driver, general_entry_type);

		Dropdown.selectByVisibleText(driver, general_entry_type, " Lease ", 30);

		HelperClass.highlightElement(driver, status);

		Dropdown.selectByVisibleText(driver, status, " New/Open ", 30);
		//Dropdown.selectByVisibleText(driver, status, " Closed ", 30);

		Click.sendKeys(driver, general_assigned_to, "QA Sales", 30);

		LO.print("Lead is assigned");
		System.out.println("Lead is assigned");

		Thread.sleep(2000);

		Click.on(driver, general_assigned_to_option, 30);

		// Adding the General Notes

		HelperClass.highlightElement(driver, general_notes);
		Click.sendKeys(driver, general_notes, "TestNotes", 10);

		LO.print("Added data in General Notes");
		System.out.println("Added data in General Notes");

	}

	public void lead_Customer_info_individual() throws Exception

	{

		Dropdown.selectByVisibleText(driver, customer_type, " Individual ", 30);

		LO.print("Customer type - " + "Individual" + " selected");
		System.out.println("Customer type - " + "Individual" + " selected");

		//js = (JavascriptExecutor) driver;

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.sendKeys(driver, customer_name, "QA ", 30);

		Thread.sleep(4000);

		Click.on(driver, customer_name_option, 20);

		

	}
	
	
	
	
	
	
	// Customer info for business 
	
	public void lead_Customer_info_business() throws Exception

	{
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		Dropdown.selectByVisibleText(driver, customer_type, " Business ", 30);

		LO.print("Customer type - " + "Business" + " selected");
		System.out.println("Customer type - " + "Business" + " selected");

		//js = (JavascriptExecutor) driver;

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		Click.sendKeys(driver, customer_name, "Comp QA2", 30);

		Click.on(driver, customer_name_option, 20);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		Thread.sleep(4000);

		//Click.on(driver, customer_name_option, 20);

		//ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

	}
	

	
	
	
	
	
	
	
	
	
	
	public void lead_vehicle_request_broker() throws Exception
	
	{
		
		
		Thread.sleep(4000);

		HelperClass.highlightElement(driver, add_new_vehicle_request);
		
		
	Click.on(driver, add_new_vehicle_request, 30);

	LO.print("Clicked on Add new vehicle request");
	System.out.println("Clicked on Add new vehicle request");

	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	Thread.sleep(4000);

	
	HelperClass.highlightElement(driver, channel);

	Click.on(driver, channel, 30);

	Thread.sleep(2000);
	
	Click.on(driver, channel_broker_value, 10);

	LO.print("Clicked on broker channel");
	System.out.println("Clicked on broker channel");
	
	
	Thread.sleep(2000);
	//channel_broker_value.sendKeys(Keys.TAB);
	
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 10);
	
	Thread.sleep(5000);	
	Click.on(driver, add_new_vehicle_save, 10);
	
	LO.print("Vehicle request for broker has been saved");
	System.out.println("Vehicle request for broker has been saved");
	
	
	}
	
	
	
	
public void lead_vehicle_request_ownbook() throws Exception
	
	{
		
		
		Thread.sleep(4000);

		//HelperClass.highlightElement(driver, add_new_vehicle_request);
		
		
	Click.on(driver, add_new_vehicle_request, 30);

	LO.print("Clicked on Add new vehicle request");
	System.out.println("Clicked on Add new vehicle request");

	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	Thread.sleep(4000);

	
	HelperClass.highlightElement(driver, channel);

	Click.on(driver, channel, 30);

	Thread.sleep(2000);
	
	Click.on(driver, channel_ownbook_value, 10);

	LO.print("Clicked on ownbook channel");
	System.out.println("Clicked on ownbook channel");
	
	
	Thread.sleep(2000);
	//channel_broker_value.sendKeys(Keys.TAB);
	
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 10);
	
	Thread.sleep(5000);	
	Click.on(driver, add_new_vehicle_save, 10);
	
	LO.print("Vehicle request for broker has been saved");
	System.out.println("Vehicle request for broker has been saved");
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Add Vehicle request for Business 
	
public void lead_vehicle_request_business() throws Exception
	
	{
		
		
		Thread.sleep(4000);

		

		
	Click.on(driver, add_new_vehicle_request, 30);

	LO.print("Clicked on Add new vehicle request");
	System.out.println("Clicked on Add new vehicle request");

	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	Thread.sleep(4000);

	
	HelperClass.highlightElement(driver, channel);

	Click.on(driver, channel, 30);

	Thread.sleep(2000);
	
	Click.on(driver, channel_broker_value, 10);

	LO.print("Clicked on broker channel");
	System.out.println("Clicked on broker channel");
	
	
	Thread.sleep(2000);
	//channel_broker_value.sendKeys(Keys.TAB);
	
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 10);
	
	Thread.sleep(5000);	
	Click.on(driver, add_new_vehicle_save, 10);
	
	LO.print("Vehicle request for broker has been saved");
	System.out.println("Vehicle request for broker has been saved");
	
	
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
public void lead_map_new_quote_broker_individual() throws Exception
	
	{
	
//  Map New Quote 
	
	Thread.sleep(5000);	
	HelperClass.highlightElement(driver, Map_New_quote_icon);
	
	LO.print("Click on Map New Quote icon");
	System.out.println("Click on Map New Quote icon");
	
	
	
	Thread.sleep(5000);
	
	
	Click.on(driver, Map_New_quote_icon, 30);

	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	
	Click.on(driver, acquisition_contract_type, 30);
	
	LO.print("Click on acquisition contract type icon");
	System.out.println("Click on acquisition contract type icon");
	

	Thread.sleep(5000);
	Click.on(driver, acquisition_contract_type_broker_value, 30);
	
	
	
	Thread.sleep(5000);
	Click.on(driver, customer_contract_type, 30);
			
	Thread.sleep(5000);
	Click.on(driver, customer_contract_type_pch_value, 30);
	
	LO.print("Click on customer contract type icon");
	System.out.println("Click on customer contract type icon");
	
	Thread.sleep(5000);
	Click.on(driver, map_new_quote_search, 30);
	
	LO.print("Click on search button");
	System.out.println("Click on search button");
	
	Thread.sleep(5000);
	Click.on(driver, map_new_quote_createddate_sorting, 30);
	
	LO.print("Click on created date button 1st ");
	System.out.println("Click on created date button 1st");
	
	
	Click.on(driver, map_new_quote_createddate_sorting, 30);
	
	LO.print("Click on created date button 2nd");
	System.out.println("Click on created date button 2nd");
	
	
	Thread.sleep(5000);
	Click.on(driver, select_new_quoted, 30);	
	
	Thread.sleep(5000);
	
	Click.on(driver, select_new_quoted_save, 30);
	
	
	
	Thread.sleep(5000);


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
	 

	  }}
		 
	 catch (Exception e)
		{
		      //System.out.println( e );
	 

	 
		}
	
	
	

	
}
	





public void lead_map_new_quote_ownbook_individual() throws Exception

{

//Map New Quote 

Thread.sleep(5000);	
HelperClass.highlightElement(driver, Map_New_quote_icon);

LO.print("Click on Map New Quote icon");
System.out.println("Click on Map New Quote icon");



Thread.sleep(5000);
Click.on(driver, Map_New_quote_icon, 30);

ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
Click.on(driver, acquisition_contract_type_ownbook, 30);

LO.print("Click on acquisition contract type icon");
System.out.println("Click on acquisition contract type icon");


Thread.sleep(5000);
Click.on(driver, acquisition_contract_type_ownbook_value, 30);



Thread.sleep(5000);
Click.on(driver, customer_contract_type, 30);
		
Thread.sleep(5000);
Click.on(driver, customer_contract_type_pch_value, 30);

LO.print("Click on customer contract type icon");
System.out.println("Click on customer contract type icon");

Thread.sleep(5000);
Click.on(driver, map_new_quote_search, 30);

LO.print("Click on search button");
System.out.println("Click on search button");

Thread.sleep(5000);
Click.on(driver, map_new_quote_createddate_sorting, 30);

LO.print("Click on created date button 1st ");
System.out.println("Click on created date button 1st");


Click.on(driver, map_new_quote_createddate_sorting, 30);

LO.print("Click on created date button 2nd");
System.out.println("Click on created date button 2nd");


Thread.sleep(5000);
Click.on(driver, select_new_quoted, 30);	

Thread.sleep(5000);

Click.on(driver, select_new_quoted_save, 30);

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
 

  }}
	 
 catch (Exception e)
	{
	      //System.out.println( e );
 

 
	}










}










public void lead_map_new_quote_broker_business() throws Exception

{

//Map New Quote 

Thread.sleep(5000);	
HelperClass.highlightElement(driver, Map_New_quote_icon);

LO.print("Click on Map New Quote icon");
System.out.println("Click on Map New Quote icon");



Thread.sleep(5000);
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
 

  }}
	 
 catch (Exception e)
	{
	      //System.out.println( e );
 

 
	}

}



public void lead_map_new_quote_broker_business_save_and_Convert () throws Exception

{

//Map New Quote 

Thread.sleep(5000);	

LO.print("Click on Lead save and Convert button");
System.out.println("Click on Lead save and Convert button");

Thread.sleep(5000);	
Click.on(driver, lead_update_convert_into_opp_button, 30);


ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

}


public String lead_map_new_quote_broker_business_getting_the_opportunityno ( ) throws Exception

{




Thread.sleep(5000);



String Lead_OpportunityId=  lead_Opp_location.getText() ;



System.out.println("In Lead created Opportunity Id is =" + Lead_OpportunityId);

LO.print("In Lead created Opportunity Id is =" + Lead_OpportunityId);
return Lead_OpportunityId;
}











public void lead_map_new_quote_owbook_business() throws Exception

{

//Map New Quote 

Thread.sleep(5000);	
HelperClass.highlightElement(driver, Map_New_quote_icon);

LO.print("Click on Map New Quote icon");
System.out.println("Click on Map New Quote icon");



Thread.sleep(5000);
Click.on(driver, Map_New_quote_icon, 30);

ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
Click.on(driver, acquisition_contract_type, 30);

LO.print("Click on acquisition contract type icon");
System.out.println("Click on acquisition contract type icon");


Thread.sleep(5000);
Click.on(driver, acquisition_contract_type_ownbook_value, 30);



Thread.sleep(5000);
Click.on(driver, customer_contract_type, 30);
		
Thread.sleep(5000);
Click.on(driver, customer_business_contract_type_bch_value, 30);

LO.print("Click on customer contract type icon");
System.out.println("Click on customer contract type icon");

Thread.sleep(5000);
Click.on(driver, map_new_quote_search, 30);

LO.print("Click on search button");
System.out.println("Click on search button");

Thread.sleep(5000);
Click.on(driver, map_new_quote_createddate_sorting, 30);

LO.print("Click on created date button 1st ");
System.out.println("Click on created date button 1st");


Click.on(driver, map_new_quote_createddate_sorting, 30);

LO.print("Click on created date button 2nd");
System.out.println("Click on created date button 2nd");


Thread.sleep(5000);
Click.on(driver, select_new_quoted, 30);	

Thread.sleep(5000);

Click.on(driver, select_new_quoted_save, 30);

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
 

  }}
	 
 catch (Exception e)
	{
	      //System.out.println( e );
 

 
	}

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
		
		
		if (GettingMessageforNote.equalsIgnoreCase("Test Communication add a note"));
			
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
