package com.amt.testUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;

public class CommonClass extends TestBase {

	static Actions actions;

	Properties prop;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	// create order quote summary page button
	@FindBy(xpath = "//button[normalize-space()='Create order']|(//*[@title='Create order'])[2]|//*[@src='../../assets/images/opportunity/shopping-cart.svg']")
	private WebElement create_order;

	// Channel Dropdown
	@FindBy(xpath = "//*[normalize-space()='Channel']//ancestor::div[1]//div//div//div//span[2]")
	private WebElement quote_summary_channel_dropdown;

	// Channel option all
	@FindBy(xpath = "(//*[normalize-space()='All']//li//input)[1]")
	private WebElement quote_summary_channel_option_all;
	
	
	// Channel Internal Use
	@FindBy(xpath = "//*[normalize-space()='Internal Use']/input")
	private WebElement quote_summary_channel_option_internal_use;
	
	// Channel Retail Sales
	@FindBy(xpath = "//*[normalize-space()='Retail Sales']/input")
	private WebElement quote_summary_channel_option_retail_sales;
	
	// Channel Ownbook
	@FindBy(xpath = "//*[normalize-space()='Own Book']/input")
	private WebElement quote_summary_channel_option_ownbook;

	// Delivery date picker
	@FindBy(xpath = "(//*[normalize-space()='Requested delivery']//ancestor::div[1]//div//input)[5]")
	private WebElement quote_summary_requested_delivery_date_picker1;
	
	@FindBy(xpath = "(//*[normalize-space()='Requested delivery']//ancestor::div[1]//div//input)[6]|(//*[normalize-space()='Requested delivery']//ancestor::div[1]//div//input)[1]")
	private WebElement quote_summary_requested_delivery_date_picker2;
	
			
	@FindBy(xpath = "//*[@id='requestedDelivery']")
	private WebElement quote_summary_requested_delivery_date_picker3;
	


	// first enabled month
	@FindBy(xpath = "//table//td[@class='ng-star-inserted']")
	private WebElement quote_summary_requested_delivery_month;
	
	
	
	// first enabled month
	@FindBy(xpath = "//table[@class='months']//td[@class='ng-star-inserted']")
	private WebElement quote_summary_requested_delivery_month1;
	
	//*[@id='requestedDelivery']

	// List of month
	@FindBy(xpath = "//bs-calendar-layout//table//tbody//tr//td")
	private List<WebElement> quote_summary_list_of_months;

	// Enter Supplier Name
	@FindBy(xpath = "//label[normalize-space()='Name']//ancestor::div[1]//div//input")
	private WebElement quote_summary_input_supplier_name;

	// Pick Supplier Name
	@FindBy(xpath = "//*[@id='convert_to_order_modal']//*[normalize-space()='Dummy Company']//ancestor::div[1]//div//strong|//*[normalize-space()='Name']//ancestor::div[1]//div//strong")
	private WebElement quote_summary_pick_supplier_name;

	// Confirm Create order button
	@FindBy(xpath = "//*[@class='modal-footer']//*[contains(text(), 'Create order')]")
	private WebElement quote_summary_create_order_pop_up_button;

	// Click on OK to confirm
	@FindBy(xpath = "//*[@id='convert_to_order_confrim']//*[contains(text(), 'Ok')]")
	private WebElement quote_summary_create_order_ok_button;

	// Order ID Number
	@FindBy(xpath = "//*[contains(text(), 'New Order ID')]")
	private WebElement quote_summary_order_id_text_quote_summary;
	
	// Order ID Number
	@FindBy(xpath = "//*[@id='convert_to_order_number']//*[contains(text(), 'order')]")
	private WebElement quote_summary_order_id_text_opportunity;

	// Click on OK to confirm
	@FindBy(xpath = "//*[contains(text(), 'Ok')]")
	private WebElement quote_summary_ok_button_after_getting_order_id;
	
	
	
	// Click on OK to confirm
	@FindBy(xpath = "//*[@id='convert_to_order_number']//*[contains(text(), 'Ok')]")
	private WebElement quote_summary_ok_button_after_getting_order_id1;

	public CommonClass() {

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

	public String validate_selected_order(WebElement element) {
		ExplicitWait.visibleElement(driver, element, 60);

		return element.getText().trim();
	}

	public static void move_courser() {
		actions = new Actions(driver);

		// Define the coordinates
		int x1 = 100; // Starting X-coordinate
		int y1 = 100; // Starting Y-coordinate
		int x2 = 200; // Ending X-coordinate
		int y2 = 200; // Ending Y-coordinate

		// Move the mouse cursor from (x1, y1) to (x2, y2)
		actions.moveByOffset(x1, y1).moveByOffset(x2 - x1, y2 - y1).perform();
	}

	public void mouse_hover_to_element(WebElement element) {

		actions = new Actions(driver);

		actions.moveToElement(element).perform();

	}

	public void create_order(String sheet_name) throws InterruptedException, IOException, ClassNotFoundException {
		LO.print(" ");
		System.out.println(" ");

		LO.print("*****Creating Order Started*****");
		System.out.println("*****Creating Order Started*****");

		// Click on create order
		Click.on(driver, create_order, 20);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// Select Channel
		Click.on(driver, quote_summary_channel_dropdown, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// Click on first option
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		
		if(classOrMethodName.contains("channel_internal_use"))
		{
			ExplicitWait.visibleElement(driver, quote_summary_channel_option_internal_use, 60);
			jse.executeScript("arguments[0].click();", quote_summary_channel_option_internal_use);
			
		}else if(classOrMethodName.contains("channel_retail_sales"))
		{
			ExplicitWait.visibleElement(driver, quote_summary_channel_option_retail_sales, 60);
			jse.executeScript("arguments[0].click();", quote_summary_channel_option_retail_sales);
			
		}else if(classOrMethodName.contains("Outright_BCH"))
		{
			ExplicitWait.visibleElement(driver, quote_summary_channel_option_ownbook, 60);
			jse.executeScript("arguments[0].click();", quote_summary_channel_option_ownbook);
			
		}else
		{
			ExplicitWait.visibleElement(driver, quote_summary_channel_option_all, 60);
			jse.executeScript("arguments[0].click();", quote_summary_channel_option_all);	
		}
		
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// Send Input to Name
		Click.on(driver, quote_summary_input_supplier_name, 30);
		Thread.sleep(2000);
		Click.sendKeys(driver, quote_summary_input_supplier_name, "D", 60);
		Thread.sleep(2000);
		quote_summary_input_supplier_name.sendKeys("u");
		Thread.sleep(500);
		quote_summary_input_supplier_name.sendKeys("m");
		Thread.sleep(500);
		quote_summary_input_supplier_name.sendKeys("m");
		Thread.sleep(500);

		// pick supplier name
		Click.on(driver, quote_summary_pick_supplier_name, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		
		
		// Click on Month input
		try {
		Click.on(driver, quote_summary_requested_delivery_date_picker1, 20);
		}catch(Exception e)
		{
		Click.on(driver, quote_summary_requested_delivery_date_picker2, 20);
		}
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// select month
		Click.on(driver, quote_summary_requested_delivery_month, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// Finally create Order
		Click.on(driver, quote_summary_create_order_pop_up_button, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// Click on OK button to confirm
		Click.on(driver, quote_summary_create_order_ok_button, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		ExplicitWait.visibleElement(driver, quote_summary_order_id_text_quote_summary, 60);
		String text = quote_summary_order_id_text_quote_summary.getText();

		// Find the index where the last 8 characters start
		int startIndex = text.length() - 8;

		// Use substring to extract the last 8 characters
		String orderID = text.substring(startIndex);

		// Click on OK button after getting order id
		Click.on(driver, quote_summary_ok_button_after_getting_order_id, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		String sheetname = prop.getProperty(sheet_name);

		// quote ref no
		wb.getSheet(sheetname).getRow(1).getCell(1).setCellValue(orderID);
		// quote ref no
		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		wb.write(out);
		wb.close();

		LO.print(" ");
		System.out.println(" ");

		LO.print("***** Order Created Successfully *****");
		System.out.println("***** Order Created Successfully *****");

		LO.print("Order ID " + orderID + " is saved to Excel sheet named Quote Save, in the sheet " + sheetname);
		System.out.println(
				"Order ID " + orderID + " is saved to Excel sheet named Quote Save, in the sheet " + sheetname);

		LO.print("*****xxxxxxxxxxxxxxxxxxxxxxxxxxxxx*****");
		System.out.println("*****xxxxxxxxxxxxxxxxxxxxxxxxxxxxx*****");

	}

	
	public void create_order_from_opportunity(String sheet_name) throws InterruptedException, IOException {
		LO.print(" ");
		System.out.println(" ");

		LO.print("*****Creating Order Started*****");
		System.out.println("*****Creating Order Started*****");

		// Click on create order
		Click.on(driver, create_order, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);	
		
		
      	 Click.on(driver, quote_summary_requested_delivery_date_picker3, 20);

		
		
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// select month
		try {Click.on(driver, quote_summary_requested_delivery_month, 20);}
		catch(Exception e) {Click.on(driver, quote_summary_requested_delivery_month1, 20);}		
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// Finally create Order
		Click.on(driver, quote_summary_create_order_pop_up_button, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// Click on OK button to confirm
		Click.on(driver, quote_summary_create_order_ok_button, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		ExplicitWait.visibleElement(driver, quote_summary_order_id_text_opportunity, 60);
		String text = quote_summary_order_id_text_opportunity.getText();

		// Find the index where the last 8 characters start
		int startIndex = text.length() - 8;

		// Use substring to extract the last 8 characters
		String orderID = text.substring(startIndex);

		// Click on OK button after getting order id
		Click.on(driver, quote_summary_ok_button_after_getting_order_id1, 20);
		
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook wb = new XSSFWorkbook(in);

		String sheetname = prop.getProperty(sheet_name);

		// quote ref no
		wb.getSheet(sheetname).getRow(1).getCell(1).setCellValue(orderID);
		// quote ref no
		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		wb.write(out);
		wb.close();

		LO.print(" ");
		System.out.println(" ");

		LO.print("***** Order Created Successfully *****");
		System.out.println("***** Order Created Successfully *****");

		LO.print("Order ID " + orderID + " is saved to Excel sheet named Quote Save, in the sheet " + sheetname);
		System.out.println(
				"Order ID " + orderID + " is saved to Excel sheet named Quote Save, in the sheet " + sheetname);

		LO.print("*****xxxxxxxxxxxxxxxxxxxxxxxxxxxxx*****");
		System.out.println("*****xxxxxxxxxxxxxxxxxxxxxxxxxxxxx*****");

	}

}
