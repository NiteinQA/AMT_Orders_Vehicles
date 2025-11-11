package com.amt.testUtil;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
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
	@FindBy(xpath = "//button[normalize-space()='Create order']|//*[@title='Create order']|//*[@src='../../assets/images/opportunity/shopping-cart.svg']")
	private List<WebElement> create_order;
	
	
	
	@FindBy(xpath = "//*[text()=' Previous ']")
	private WebElement previous_button;
	
	//*[@src='../../assets/images/opportunity/shopping-cart.svg']

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
	
	// **********Discount elements

	@FindBy(xpath = "//*[@id='addManfacturerDiscountBtn']")
	private WebElement add_manufacturer_discount_button;

	@FindBy(xpath = "//*[@id='addDealerDiscountBtn']")
	private WebElement add_dealer_discount_button;

	@FindBy(xpath = "//*[@id='DiscountOnVehiclePercentageCustomDiscount']")
	private WebElement manufacturer_discount_on_vehicle_percentage_input;

	@FindBy(xpath = "(//*[@id='DiscountOnVehiclePercentageCustomDiscount'])[2]")
	private WebElement dealer_discount_on_vehicle_percentage_input;

	@FindBy(xpath = "//*[@id='DiscountOnVehicleValueCustomDiscount']")
	private WebElement manufaturer_additional_discount_vehicle_input;

	@FindBy(xpath = "//*[@id='DiscountOnVehicleValueCustomDiscountForManufacturer']")
	private WebElement dealer_additional_discount_vehicle_input;

	@FindBy(xpath = "//*[@id='applyDiscountToOptionAndPaint']")
	private WebElement apply_same_discount_to_paint_and_option_checkbox;

	@FindBy(xpath = "//*[@id='DiscountOnOptionPercentageCustomDiscountMan']")
	private WebElement manufacturer_discount_on_options_percentage_input;

	@FindBy(xpath = "//*[@id='DiscountOnOptionPercentageCustomDiscount']")
	private WebElement dealer_discount_on_options_percentage_input;

	@FindBy(xpath = "//*[@id='DiscountOnPaintPercentageCustomDiscountMan']")
	private WebElement manufacturer_discount_on_paints_percentage_input;

	@FindBy(xpath = "//*[@id='DiscountOnPaintPercentageCustomDiscount']")
	private WebElement dealer_discount_on_paints_percentage_input;

	@FindBy(xpath = "//*[@id='DiscountOnOptionValueCustomDiscount']")
	private WebElement manufacturer_additional_discount_options_input;

	@FindBy(xpath = "//*[@id='DiscountOnPaintValueCustomDiscount']")
	private WebElement manufacturer_additional_discount_paint_input;

	@FindBy(xpath = "//*[@id='DiscountOnOptionValueCustomDiscountForManufacturer']")
	private WebElement dealer_additional_discount_options_input;

	@FindBy(xpath = "//*[@id='DiscountOnPaintValueCustomDiscountForManufacturer']")
	private WebElement dealer_additional_discount_paint_input;

	@FindBy(xpath = "//*[@id='RebateCustom']")
	private WebElement manufacturer_rebate_input;

	@FindBy(xpath = "//*[@id='MarketingBonusCustom']")
	private WebElement manufacturer_marketing_bonus_input;

	@FindBy(xpath = "//*[@id='MakeRoadWorthyCustomDiscount']")
	private WebElement manufacturer_manufacturing_delivery_charges_input;

	@FindBy(xpath = "//*[@id='RebateCustomForManufacturer']")
	private WebElement dealer_rebate_input;

	@FindBy(xpath = "//*[@id='MarketingBonusCustomForManufacturer']")
	private WebElement dealer_marketing_bonus_input;

	@FindBy(xpath = "//*[@id='MakeRoadWorthyCustomDiscountForManufacturer']")
	private WebElement dealer_manufacturing_delivery_charges_input;

	@FindBy(xpath = "//*[normalize-space()='Remarks']//div//textarea")
	private WebElement manufacturer_remarks_input;

	@FindBy(xpath = "(//*[normalize-space()='Remarks']//div//textarea)[2]")
	private WebElement dealer_remarks_input;

	@FindBy(xpath = "//button[normalize-space()='Add']")
	private WebElement add_discount;	
	
	@FindBy(xpath = "//*[@id='ListingPriceNew']")
	private WebElement vehicle_basic_price;
	
	
	
	// Click on OK to confirm
	@FindBy(xpath = "//*[@id='convert_to_order_number']//*[contains(text(), 'Ok')]")
	private WebElement quote_summary_ok_button_after_getting_order_id1;

	public CommonClass() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(ConfigConstants.EXCEL_VALUES_PROPERTY_FILE_PATH);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PageFactory.initElements(driver, this);
	}
	
	
	
	public double get_the_default_broker_margin_value_from_excel_based_on_configurations_for_hire_contract_types(double terms , String sheet_name) throws IOException
	{
       return  get_broker_margin(0  , terms, sheet_name); 		
	}
	
	
	
	public double get_the_default_broker_margin_value_from_excel_based_on_configurations_for_purchase_contract_types(double terms , String credit_type , String sheet_name) throws IOException
	{

		double broker_margin = 0 ;
	
		
		if(credit_type.equals(GetExcelFormulaValue.get_string_cell_value(10, 0, sheet_name)))
		{
			
			broker_margin = get_broker_margin(10 , terms, sheet_name);
		}
		
		if(credit_type.equals(GetExcelFormulaValue.get_string_cell_value(18, 0, sheet_name)))
		{
			
			broker_margin =  get_broker_margin(18 , terms, sheet_name);
		}
		
		if(credit_type.equals(GetExcelFormulaValue.get_string_cell_value(26, 0, sheet_name)))
		{
			
			broker_margin = get_broker_margin(26 , terms, sheet_name);
		}
	
		System.out.println(broker_margin);
		
        return broker_margin;   
				
	}


	public double get_broker_margin( int rowNum , double terms, String sheet_name) throws IOException {
		//min 1 and max 1
		
	
				
        double min1 =  GetExcelFormulaValue.get_formula_value((rowNum+2), 0, sheet_name);
        double max1 =  GetExcelFormulaValue.get_formula_value((rowNum+2), 1,sheet_name);
        
		//min 2 and max 2
        double min2 =  GetExcelFormulaValue.get_formula_value((rowNum+3), 0, sheet_name);
        double max2 =  GetExcelFormulaValue.get_formula_value((rowNum+3), 1, sheet_name);
        
		//min 3 and max 3
        double min3 =  GetExcelFormulaValue.get_formula_value((rowNum+4), 0, sheet_name);
        double max3 =  GetExcelFormulaValue.get_formula_value((rowNum+4), 1, sheet_name);
        
		//min 4 and max 4
        double min4 =  GetExcelFormulaValue.get_formula_value((rowNum+5), 0, sheet_name);
        double max4 =  GetExcelFormulaValue.get_formula_value((rowNum+5), 1, sheet_name);        
        
       
        
        double[][]ranges = {{min1, max1}, {min2, max2}, {min3, max3}, {min4, max4}};
        
        double minimum = 0;
        double maximum = 0;
        
        int count=0;
        int whichRange =0 ;
        
        for (int i = 0; i < ranges.length; i++) {
        	
        	count++;
        	
            if (terms >= ranges[i][0] && terms <= ranges[i][1])
            {

            	whichRange=count;
            	
            	System.out.println(whichRange);
            	
            	break;
            	
            }
        }
        
        double broker_margin = 0 ;
        
        if(whichRange==1) { broker_margin = GetExcelFormulaValue.get_formula_value((rowNum+2), 2, sheet_name);}
        if(whichRange==2) { broker_margin = GetExcelFormulaValue.get_formula_value((rowNum+3), 2, sheet_name);}
        if(whichRange==3) { broker_margin = GetExcelFormulaValue.get_formula_value((rowNum+4), 2, sheet_name);}
        if(whichRange==4) { broker_margin = GetExcelFormulaValue.get_formula_value((rowNum+5), 2, sheet_name);}
        
        System.out.println(broker_margin);
        
		return broker_margin;
	}

	
	

	public String validate_selected_order(WebElement element) {
		ExplicitWait.visibleElement(driver, element, 60);

		return element.getText().trim();
	}

	public static void move_courser() {
		
		try {
		actions = new Actions(driver);

		// Define the coordinates
		int x1 = 100; // Starting X-coordinate
		int y1 = 100; // Starting Y-coordinate
		int x2 = 200; // Ending X-coordinate
		int y2 = 200; // Ending Y-coordinate

		// Move the mouse cursor from (x1, y1) to (x2, y2)
		actions.moveByOffset(x1, y1).moveByOffset(x2 - x1, y2 - y1).perform();
		}catch(Exception e)
		{
			
		}
	}
	
	public static void move_courser1() {
		
		try {
		actions = new Actions(driver);

		// Define the coordinates
		int x1 = 150; // Starting X-coordinate
		int y1 = 150; // Starting Y-coordinate
		int x2 = 250; // Ending X-coordinate
		int y2 = 250; // Ending Y-coordinate

		// Move the mouse cursor from (x1, y1) to (x2, y2)
		actions.moveByOffset(x1, y1).moveByOffset(x2 - x1, y2 - y1).perform();
		}catch(Exception e)
		{
			
		}
	}


	public void mouse_hover_to_element(WebElement element) {

		actions = new Actions(driver);

		actions.moveToElement(element).perform();

	}

	public void create_order(String sheet_name, String calculation_sheet) throws InterruptedException, IOException, ClassNotFoundException {
		LO.print(" ");
		System.out.println(" ");

		LO.print("*****Creating Order Started*****");
		System.out.println("*****Creating Order Started*****");

		// Click on create order
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		
		Thread.sleep(2000);
		
		try {
		ExplicitWait.visibleElement(driver, previous_button, 20); 
		JavaScriptExecutor.scroll_in_to_view(driver, previous_button);
		}
		catch(Exception e)
		{
			
		}
		
			try {	
		ExplicitWait.waitForListOfVisibleElements(driver, create_order, 10);
		
		for(WebElement webelement_create_order : create_order )
		{
			if(webelement_create_order.isEnabled())
			{
				webelement_create_order.click();
			}
		}
		
			}
			catch(Exception e)
			{
				try {
				driver.findElement(By.xpath("//button[normalize-space()='Create order']")).click();
				}catch(Exception e1)
				{
					driver.findElement(By.xpath("//button[normalize-space()='Create order']")).click();
				}
			}
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
		try {
		// Send Input to Name
		Click.on(driver, quote_summary_input_supplier_name, 30);
		Thread.sleep(2000);
		Click.sendKeys(driver, quote_summary_input_supplier_name, "s", 60);
		Thread.sleep(2000);
		quote_summary_input_supplier_name.sendKeys("a");
		Thread.sleep(500);
		quote_summary_input_supplier_name.sendKeys("n");
		Thread.sleep(500);
		quote_summary_input_supplier_name.sendKeys("d");
		Thread.sleep(500);

		// pick supplier name
		Click.on(driver, quote_summary_pick_supplier_name, 20);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		}catch(Exception e)
		{}
		
		
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
		
		wb.getSheet(sheetname).getRow(1).getCell(2).setCellValue(calculation_sheet);
		
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
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);	
		
		

		Thread.sleep(2000);
		
		
		for(WebElement webelement_create_order : create_order )
		{
			try {
				
				
				String styleAttributeValue = webelement_create_order.getAttribute("style");
				
				if(styleAttributeValue.contains("cursor: not-allowed;"))
				{
					
				}
				else
				{
					webelement_create_order.click();
				}
				
				
			}
			catch(Exception e)
			{
				
			}
			
		}
	

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
	
	public void add_manufacturer_discount(String vehicle_percentage_discount, String additional_discount_vehicle,
			String options_percentage_discount, String additional_discount_options, String paint_percentage_discount,
			String additional_discount_paint, String rebate, String marketing_bonus,
			String manufacturer_delivery_charges) throws InterruptedException {

		LO.print("Adding Manufacturer Discount On OTR page");
		System.out.println("Adding Manufacturer Discount On OTR page");

		// open pop up on clicking add discount button
		Click.on(driver, add_manufacturer_discount_button, 20);

		Thread.sleep(5000);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", apply_same_discount_to_paint_and_option_checkbox);

		Thread.sleep(2000);

		// add vehicle discount
		Click.sendKeys(driver, manufacturer_discount_on_vehicle_percentage_input, vehicle_percentage_discount, 20);

		Click.sendKeys(driver, manufaturer_additional_discount_vehicle_input, additional_discount_vehicle, 20);

		// add options discount
		Click.sendKeys(driver, manufacturer_discount_on_options_percentage_input, options_percentage_discount, 20);

		Click.sendKeys(driver, manufacturer_additional_discount_options_input, additional_discount_options, 20);

		// add paint discount
		Click.sendKeys(driver, manufacturer_discount_on_paints_percentage_input, paint_percentage_discount, 20);

		Click.sendKeys(driver, manufacturer_additional_discount_paint_input, additional_discount_paint, 20);

		// add rebate
		Click.sendKeys(driver, manufacturer_rebate_input, rebate, 20);

		// add marketing bonus
		Click.sendKeys(driver, manufacturer_marketing_bonus_input, marketing_bonus, 20);

		// add manufacturing del charges bonus
		Click.sendKeys(driver, manufacturer_manufacturing_delivery_charges_input, manufacturer_delivery_charges, 20);

		// add remarks
		Click.sendKeys(driver, manufacturer_remarks_input, "Man Discount Test Remark", 20);

		Click.on(driver, add_discount, 20);

		LO.print("Manufacturer Discount Added On OTR page");
		System.out.println("Manufacturer Discount Added On OTR page");

	}

	public void add_dealer_discount(String vehicle_percentage_discount, String additional_discount_vehicle,
			String options_percentage_discount, String additional_discount_options, String paint_percentage_discount,
			String additional_discount_paint, String rebate, String marketing_bonus,
			String manufacturer_delivery_charges) throws InterruptedException {


		LO.print("Adding Dealer Discount On OTR page");
		System.out.println("Adding Dealer Discount On OTR page");

		Thread.sleep(5000);
		
//open pop up on clicking add discount button
		Click.on(driver, add_dealer_discount_button, 20);

		Thread.sleep(5000);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", apply_same_discount_to_paint_and_option_checkbox);

		Thread.sleep(2000);

//add vehicle discount 		
		Click.sendKeys(driver, dealer_discount_on_vehicle_percentage_input, vehicle_percentage_discount, 20);

		Click.sendKeys(driver, dealer_additional_discount_vehicle_input, additional_discount_vehicle, 20);

//add options discount 		
		Click.sendKeys(driver, dealer_discount_on_options_percentage_input, options_percentage_discount, 20);

		Click.sendKeys(driver, dealer_additional_discount_options_input, additional_discount_options, 20);

//add paint discount 		
		Click.sendKeys(driver, dealer_discount_on_paints_percentage_input, paint_percentage_discount, 20);

		Click.sendKeys(driver, dealer_additional_discount_paint_input, additional_discount_paint, 20);

//add rebate		
		Click.sendKeys(driver, dealer_rebate_input, rebate, 20);

//add marketing bonus		
		Click.sendKeys(driver, dealer_marketing_bonus_input, marketing_bonus, 20);

//add manufacturing del charges bonus		
		Click.sendKeys(driver, dealer_manufacturing_delivery_charges_input, manufacturer_delivery_charges, 20);

//add remarks	
		Click.sendKeys(driver, dealer_remarks_input, "Man Discount Test Remark", 20);

		Click.on(driver, add_discount, 20);

		LO.print("Dealer Discount Added On OTR page");
		System.out.println("Dealer Discount Added On OTR page");

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
		
		Thread.sleep(2000);

		// release Contol+V for pasting
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		
		Thread.sleep(2000);

		// for pressing and releasing Enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(3000);

		System.out.println("File is Uploaded Successfully");
		LO.print("File is Uploaded Successfully");

	}
	
	
	public double[] get_doc_fee_and_commission_for_hire(String sheet_name) throws IOException
	{
		double docFee =  GetExcelFormulaValue.get_formula_value(1, 0, sheet_name);
		 
		double docFeeCommission = GetExcelFormulaValue.get_formula_value(1, 1, sheet_name);	
		
		
		double [] values = {docFee , docFeeCommission};
		
		return values ;
		
	}
	
	public double[] get_doc_fee_and_commission_for_purchase(String sheet_name) throws IOException
	{
		double docFee =  GetExcelFormulaValue.get_formula_value(4, 0, sheet_name);
		 
		double docFeeCommission = GetExcelFormulaValue.get_formula_value(4, 1, sheet_name);	 
		 
		double [] values = {docFee , docFeeCommission};
		
		return values;
	}



	

}
