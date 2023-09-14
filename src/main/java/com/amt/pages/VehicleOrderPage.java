package com.amt.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.CommonClass;
import com.amt.testUtil.Dropdown;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.JavaScriptExecutor;

import java.awt.AWTException;
import java.awt.Robot;

public class VehicleOrderPage extends TestBase {

	JavascriptExecutor js;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	// Order summary Element
	@FindBy(xpath = "//*[contains(text(),'Order summary')]")
	private WebElement order_summary;

	// Order summary Element
	@FindBy(xpath = "//*[contains(text(),'Vehicle order')]")
	private WebElement vehicle_order;
	
	
	
	// Supplier name
	@FindBy(xpath = "//*[contains(text(),'Name')]//ancestor::div[1]//div//input")
	private WebElement supplier_name;
	
	// Supplier name displayed
	@FindBy(xpath = "//*[contains(text(),'Name')]//ancestor::div[1]//div//ul//li")
	private WebElement select_supplier_name;
	

	// register to drop down
	@FindBy(xpath = "//*[@id='registerTo']")
	private WebElement vehicle_order_register_to_dropdown;

	// upload order form
	@FindBy(xpath = "//*[normalize-space()='Order form']//ancestor::div[1]//button[normalize-space()='Upload']")
	private WebElement vehicle_order_upload_order_form;
	
	
	// upload HPI check
	@FindBy(xpath = "//*[@id='hpiClearanceDocFile']")
	private WebElement vehicle_order_upload_HPI_check;
	
	// upload V5
	@FindBy(xpath = "//*[normalize-space()='V5']//ancestor::div[1]//button[normalize-space()='Upload']")
	private WebElement vehicle_order_upload_V5;	
	

	// Vehicle in stock
	@FindBy(xpath = "//*[normalize-space()='Vehicle in stock']//ancestor::div[1]//label[@for='optionsYes']")
	private WebElement vehicle_order_vehicle_in_stock_yes_option;

	// Expected Delivery date picker
	@FindBy(xpath = "//*[normalize-space()='Expected delivery']//input")
	private WebElement vehicle_order_expected_delivery_date_picker;

	// first enabled month
	@FindBy(xpath = "//table//td[@class='ng-star-inserted']")
	private WebElement vehicle_order_requested_delivery_month;

	// reg no.
	@FindBy(xpath = "//*[normalize-space()='Registration']//div//input")
	private WebElement vehicle_order_reg_number;

	// vin no.
	@FindBy(xpath = "//*[normalize-space()='VIN']//div//input")
	private WebElement vehicle_order_vin_number;

	// First Reg Date input
	@FindBy(xpath = "//*[normalize-space()='First registration date']//input")
	private WebElement vehicle_order_first_reg_date_input;

	// pick reg date
	@FindBy(xpath = "//span[contains(@class, 'customTodayClass')]")
	private WebElement vehicle_order_first_reg_date_pick;
	
	
	// outstanding finance
	@FindBy(xpath = "//*[contains(text(),' Outstanding finance')]")
	private WebElement outstanding_finance;	
	
	
	// outstanding finance NO
	@FindBy(xpath = "//*[contains(text(),' Outstanding finance')]//ancestor::div[2]//div[2]//div//div//div//*[contains(text(),'No')]")
	private WebElement outstanding_finance_no;
	
	
	//Pre Purchase Check
	@FindBy(xpath = "//*[contains(text(),'Pre-Purchase check')]")
	private WebElement pre_purchase_check;
	
	// Date offered input
	@FindBy(xpath = "//*[normalize-space()='Date offered']//input")
	private WebElement vehicle_order_date_offered_input;
	
	// pick date offered date
	@FindBy(xpath = "//span[contains(@class, 'customTodayClass')]")
	private WebElement vehicle_order_date_offered;
	
	//  confirmed delivery date
	@FindBy(xpath = "//*[normalize-space()='Confirmed delivery date']//input")
	private WebElement vehicle_order_confirmed_delivery_date;
	
	// pick confirmed delivery date
	@FindBy(xpath = "//span[contains(@class, 'customTodayClass')]")
	private WebElement vehicle_order_pick_confirmed_delivery_date;
	
	// upload order form
	@FindBy(xpath = "//*[@id='deliverynoteFile']")
	private WebElement vehicle_order_upload_delivery_note;
	
	//save button
	@FindBy(xpath = "//*[normalize-space()='Save & Exit']")
	private WebElement vehicle_order_save_button;
	
	
	
	//save button
	@FindBy(xpath = "//app-vehicle-order-status//*[contains(@title,'Delivery')]/ul/li")
	private WebElement vehicle_order_delivery_status;
	
	//Purchase order Register To dropdown
	@FindBy(xpath = "//*[contains(text(),'Register to')]//ancestor::div[1]//select")
	private WebElement register_to_dropdown;
	
	//Purchase order Register To option
	@FindBy(xpath = "//*[contains(text(),'Register to')]//ancestor::div[1]//select//option[2]")
	private WebElement register_to_option;
	
	//Purchase order Invoice To dropdown
	@FindBy(xpath = "//*[contains(text(),'Invoice to')]//ancestor::div[1]//select")
	private WebElement invoice_to_dropdown;
	
	//Purchase order Invoice To option
	@FindBy(xpath = "//*[contains(text(),'Invoice to')]//ancestor::div[1]//select//option[2]")
	private WebElement invoice_to_option;
	
	//Purchase order Invoice To dropdown
	@FindBy(xpath = "//*[contains(text(),'Delivered to')]//ancestor::div[1]//select")
	private WebElement delivered_to_dropdown;
	
	//Purchase order Invoice To option
	@FindBy(xpath = "//*[contains(text(),'Delivered to')]//ancestor::div[1]//select//option[1]")
	private WebElement delivered_to_option;
	
	//Check MOT
	@FindBy(xpath = "//*[contains(text(),'Check MOT')]")
	private WebElement check_MOT;
	
	

	Properties prop;

	Actions act;

	VehicleOrderPage obj_vehicle_order_tab;

	public VehicleOrderPage() {
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

	
	
	public String deliver_vehicle() throws IOException, InterruptedException, AWTException, ClassNotFoundException {

		obj_vehicle_order_tab = new VehicleOrderPage();

		// open vehicle order tab
		obj_vehicle_order_tab.open_vehicle_order_tab();
		
		
		try {			
		ExplicitWait.visibleElement(driver, outstanding_finance, 10);
		
		boolean outstanding_finance_visibility =  outstanding_finance.isDisplayed();
		
		if(outstanding_finance_visibility==true)
		{
			Click.on(driver, outstanding_finance_no, 10);
		}
		
		obj_vehicle_order_tab.fill_supplier_information();
		
		obj_vehicle_order_tab.fill_purchase_order_information_for_used_car();		
		
							
		} catch(Exception e)
		{
			// fill purchase order info under purchase order section
			obj_vehicle_order_tab.fill_up_purchase_order_info_on_vehicle_order_tab();
		}

	
		
		//below lines of code for pre-purchase check
		
		try {			
			obj_vehicle_order_tab.fill_pre_purchase_check_information();
						
		} catch(Exception e)
		{

		}	

		//fill delivery info  
		obj_vehicle_order_tab.fill_up_delivery_info_on_vehicle_order_tab();
		
		
		//fill post delivery info
		obj_vehicle_order_tab.fill_up_post_delivery_info_on_vehicle_order_tab();
		
		String delivery_status =  obj_vehicle_order_tab.get_status_text(vehicle_order_delivery_status);		
		
		//save and Exit
		String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		
		if(classOrMethodName.contains("Stocklist"))
		{
			obj_vehicle_order_tab.save_and_exit_order();
		}
		
			

		return delivery_status;
		
	}
	
	public void fill_supplier_information() throws InterruptedException
	{
		Click.sendKeys(driver, supplier_name, "a", 30);
		Thread.sleep(500);
		supplier_name.sendKeys("r");
		Thread.sleep(500);
		supplier_name.sendKeys("p");
		Thread.sleep(500);
		supplier_name.sendKeys("i");
		Thread.sleep(500);
		supplier_name.sendKeys("t");
		Thread.sleep(500);
		supplier_name.sendKeys("a");
		
		Click.on(driver, select_supplier_name,30);		
		
	}
	
	
	public void fill_purchase_order_information_for_used_car() throws InterruptedException, AWTException
	{
		
		Click.on(driver, register_to_dropdown,30);	
		Thread.sleep(500);
		Click.on(driver, register_to_option,30);
		
		Click.on(driver, invoice_to_dropdown,30);	
		Thread.sleep(500);
		Click.on(driver, invoice_to_option,30);
		
		if(vehicle_order_vin_number.isEnabled())
		{
			Click.sendKeys(driver, vehicle_order_vin_number, "VIN010101", 30);
		}
		
		obj_vehicle_order_tab = new VehicleOrderPage();
		
		Click.on(driver, vehicle_order_upload_order_form, 60);
		
		obj_vehicle_order_tab.upload_file(vehicle_order_upload_order_form, prop.getProperty("test_image_path"));
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
	}

	public	void fill_pre_purchase_check_information() throws AWTException, InterruptedException
	{
		obj_vehicle_order_tab = new VehicleOrderPage();
		
		
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", vehicle_order_upload_HPI_check);
		
		Thread.sleep(10000);	
		
		vehicle_order_upload_HPI_check.sendKeys(prop.getProperty("test_image_path"));     
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
		Thread.sleep(10000);
		
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", vehicle_order_upload_V5);
//		
//		//JavaScriptExecutor.click(driver, vehicle_order_upload_V5);
//		
//		vehicle_order_upload_V5.sendKeys(prop.getProperty("test_image_path"));
//		
//		obj_vehicle_order_tab.upload_file(vehicle_order_upload_V5, prop.getProperty("test_image_path"));
//		
//		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);	
//		
//		Thread.sleep(10000);
		
		Click.on(driver, check_MOT, 30);
		
				
	}
	
 	public void save_and_exit_order() throws IOException, InterruptedException, AWTException {

		
		JavaScriptExecutor.click(driver, vehicle_order_save_button);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 250);

	}
	
	public String get_status_text(WebElement element) throws IOException, InterruptedException, AWTException {

		ExplicitWait.visibleElement(driver, element, 60);
		
		System.out.println("Status updated is "+element.getText());
		LO.print          ("Status updated is "+element.getText());
		
		return element.getText();
		
		}
	
	
	public void fill_up_post_delivery_info_on_vehicle_order_tab()
			throws IOException, InterruptedException, AWTException {

		obj_vehicle_order_tab = new VehicleOrderPage();		
		
		// JavaScriptExecutor.click(driver, vehicle_order_upload_delivery_note);
		 
		 vehicle_order_upload_delivery_note.sendKeys(prop.getProperty("test_image_path"));
		
		//obj_vehicle_order_tab.upload_file(vehicle_order_upload_delivery_note, prop.getProperty("test_image_path"));
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		

	}

	
	public void fill_up_delivery_info_on_vehicle_order_tab()
			throws IOException, InterruptedException, AWTException {

		obj_vehicle_order_tab = new VehicleOrderPage();

		
		Click.on(driver, delivered_to_dropdown,30);	
		Thread.sleep(500);
		Click.on(driver, delivered_to_option,30);		
		
		Thread.sleep(10000);
		// Click on  Date offered input
		Click.on(driver, vehicle_order_date_offered_input, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date offered
		CommonClass.move_courser();
		Click.on(driver, vehicle_order_date_offered, 20);	
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		
		
		// Click on confirmed delivery date 
		Click.on(driver, vehicle_order_confirmed_delivery_date, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick confirmed delivery date
		CommonClass.move_courser();
		Click.on(driver, vehicle_order_pick_confirmed_delivery_date, 20);

		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);	

	}


	public void fill_up_purchase_order_info_on_vehicle_order_tab()
			throws IOException, InterruptedException, AWTException {

		obj_vehicle_order_tab = new VehicleOrderPage();

		// Dropdown.selectByVisibleText(driver, vehicle_order_register_to_dropdown,
		// "AMT", 60);
		
		Click.on(driver, vehicle_order_upload_order_form, 30);

		obj_vehicle_order_tab.upload_file(vehicle_order_upload_order_form, prop.getProperty("test_image_path"));

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		
		Thread.sleep(8000);

		Click.on(driver, vehicle_order_vehicle_in_stock_yes_option, 20);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// Click on Month input
		Click.on(driver, vehicle_order_expected_delivery_date_picker, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// select month
		Click.on(driver, vehicle_order_requested_delivery_month, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		String registationNumber = ("REG-" + obj_vehicle_order_tab.today_date());
		String vinNumber = ("VIN-" + obj_vehicle_order_tab.today_date());

		// Enter Reg No.
		Click.on(driver, vehicle_order_reg_number, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		Click.sendKeys(driver, vehicle_order_reg_number, registationNumber, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// Enter VIN no.
		Click.on(driver, vehicle_order_vin_number, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		Click.sendKeys(driver, vehicle_order_vin_number, vinNumber, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// Click on First Reg Date input
		Click.on(driver, vehicle_order_first_reg_date_input, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick first Reg Date
		CommonClass.move_courser();
		Click.on(driver, vehicle_order_first_reg_date_pick, 20);
		
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

	}

	public void open_vehicle_order_tab() throws InterruptedException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		Click.on(driver, vehicle_order, 60);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

	}

	public void upload_file(WebElement e, String filepath) throws AWTException, InterruptedException {


		
	     Thread.sleep(10000);

		Robot rb = new Robot();//
		// copying File path to Clipboard

		StringSelection str = new StringSelection(filepath);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		// press Contol+V for pasting
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		// release Contol+V for pasting
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		// for pressing and releasing Enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(3000);

		System.out.println("File is Uploaded Successfully");
		LO.print("File is Uploaded Successfully");

	}

	public String today_date() {
		LocalDate futureDate = LocalDate.now();
		String str1[] = String.valueOf(futureDate).split("-");
		String raw = str1[2].concat("/").concat(str1[1]).concat("/").concat(str1[0]);
		return raw.replace("/", "");
	}

}
