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

	// register to drop down
	@FindBy(xpath = "//*[@id='registerTo']")
	private WebElement vehicle_order_register_to_dropdown;

	// upload order form
	@FindBy(xpath = "//*[normalize-space()='Order form']//ancestor::div[1]//button[normalize-space()='Upload']")
	private WebElement vehicle_order_upload_order_form;

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
	@FindBy(xpath = "//*[normalize-space()='Delivery note']//ancestor::div[1]//button[normalize-space()='Upload']")
	private WebElement vehicle_order_upload_delivery_note;
	
	//save button
	@FindBy(xpath = "//*[normalize-space()='Save & Exit']")
	private WebElement vehicle_order_save_button;
	
	
	
	//save button
	@FindBy(xpath = "//app-vehicle-order-status//*[contains(@title,'Delivery')]/ul/li")
	private WebElement vehicle_order_delivery_status;

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

	
	
	public String deliver_vehicle_to_stcklist() throws IOException, InterruptedException, AWTException {

		obj_vehicle_order_tab = new VehicleOrderPage();

		// open vehicle order tab
		obj_vehicle_order_tab.open_vehicle_order_tab();

		// fill purchase order info under purchase order section
		obj_vehicle_order_tab.fill_up_purchase_order_info_on_vehicle_order_tab();
		
		//fill delivery info  
		obj_vehicle_order_tab.fill_up_delivery_info_on_vehicle_order_tab();
		
		//fill post delivery info
		obj_vehicle_order_tab.fill_up_post_delivery_info_on_vehicle_order_tab();
		
		String delivery_status =  obj_vehicle_order_tab.get_status_text(vehicle_order_delivery_status);		
		
		//save and Exit
		obj_vehicle_order_tab.save_and_exit_order();
		

		return delivery_status;
		
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
		

		obj_vehicle_order_tab.upload_file(vehicle_order_upload_delivery_note, prop.getProperty("test_image_path"));
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		

	}

	
	public void fill_up_delivery_info_on_vehicle_order_tab()
			throws IOException, InterruptedException, AWTException {

		obj_vehicle_order_tab = new VehicleOrderPage();

		// Click on  Date offered input
		Click.on(driver, vehicle_order_date_offered_input, 20);
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date offered
		Click.on(driver, vehicle_order_date_offered, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		
		
		// Click on confirmed delivery date 
		Click.on(driver, vehicle_order_confirmed_delivery_date, 20);
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick confirmed delivery date 
		Click.on(driver, vehicle_order_pick_confirmed_delivery_date, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		
		
		
		

	}


	public void fill_up_purchase_order_info_on_vehicle_order_tab()
			throws IOException, InterruptedException, AWTException {

		obj_vehicle_order_tab = new VehicleOrderPage();

		// Dropdown.selectByVisibleText(driver, vehicle_order_register_to_dropdown,
		// "AMT", 60);

		obj_vehicle_order_tab.upload_file(vehicle_order_upload_order_form, prop.getProperty("test_image_path"));

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		
		Thread.sleep(2000);

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
		Click.on(driver, vehicle_order_first_reg_date_pick, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

	}

	public void open_vehicle_order_tab() throws InterruptedException {

		Click.on(driver, vehicle_order, 60);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

	}

	public void upload_file(WebElement e, String filepath) throws AWTException, InterruptedException {

		try {
	     Click.on(driver, e, 60);		
		
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
		}catch(Exception e1)
		{
		    // Click.on(driver, e, 60);

			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	        jsExecutor.executeScript("arguments[0].click();", e);
		    
	        Thread.sleep(6000);

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
		}

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
