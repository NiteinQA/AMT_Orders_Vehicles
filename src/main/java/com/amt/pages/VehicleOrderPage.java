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
	
	//Delivery - Delivered To dropdown
	@FindBy(xpath = "//*[contains(text(),'Delivered to')]//ancestor::div[1]//select")
	private WebElement delivered_to_dropdown;
	
	//Delivery - Delivered To dropdown option 1 AMT
	@FindBy(xpath = "//*[contains(text(),'Delivered to')]//ancestor::div[1]//select//option[contains(text(),'AMT')]")
	private WebElement delivered_to_AMT;
	
	//Delivery - Delivered To dropdown option 2
	@FindBy(xpath = "//*[contains(text(),'Delivered to')]//ancestor::div[1]//select//option[contains(text(),'Other')]")
	private WebElement delivered_to_non_AMT;
	
	//Check MOT
	@FindBy(xpath = "//*[contains(text(),'Check MOT')]")
	private WebElement check_MOT;
	
	
	//Statuses
	
	
	//Order
	@FindBy(xpath = "//*[text()='Order']")
	private WebElement order;
	
	//Order pending
	@FindBy(xpath = "//*[text()='Pending Order']")
	private WebElement status_order_pending;
	
	//Order Confirmed
	@FindBy(xpath = "//*[text()='Order Confirmed']")
	private WebElement status_order_confirmed;
	
	//Delivery
	@FindBy(xpath = "//*[text()='Delivery']")
	private WebElement status_delivery;
	
	
	//Delivery Default 
	@FindBy(xpath = "//*[contains(text(),'Delivery')]//ancestor::div[1]//div/span[2]")
	private WebElement status_delivery_default;
	
	//Awaiting Booking
	@FindBy(xpath = "//*[text()='Awaiting Booking']")
	private WebElement status_awaiting_booking;
	
	//Delivery Booked
	@FindBy(xpath = "//*[text()='Delivery Booked']")
	private WebElement status_delivery_Booked;
	
	//Delivered
	@FindBy(xpath = "//*[text()='Delivered']")
	private WebElement status_delivered;
	
	//Payments to dealer
	@FindBy(xpath = "//*[text()='Payments to dealer']")
	private WebElement status_payments_to_dealer;
	
	
	//Payment Required
	@FindBy(xpath = "//*[text()='Payment Required']")
	private WebElement status_payment_required;	
	
	//Payment Sent
	@FindBy(xpath = "//*[text()='Payment Sent']")
	private WebElement status_payment_sent;
	
	// Input - Payments to funder
	// **********************************************************

	@FindBy(xpath = "//button[normalize-space()='Generate invoice']")
	private WebElement button_generate_invoice;

	@FindBy(xpath = "//*[@id='confirmationModal']//*[text()=' Yes']")
	private WebElement button_generate_invoice_cofirm;
	
	@FindBy(xpath = "//*[text()='Order deposit']")
	private WebElement order_deposit;

	@FindBy(xpath = "(//label[normalize-space()='Payment requested']//ancestor::div[1]//input)[1]")
	private WebElement date_payment_requested_order_deposit;

	@FindBy(xpath = "(//label[normalize-space()='Payment sent']//ancestor::div[1]//input)[1]")
	private WebElement date_payment_sent_order_deposit;

	@FindBy(xpath = "(//label[normalize-space()='Payment requested']//ancestor::div[1]//input)[2]")
	private WebElement date_payment_requested_amount_due;

	@FindBy(xpath = "(//label[normalize-space()='Payment sent']//ancestor::div[1]//input)[2]")
	private WebElement date_payment_sent_amount_due;
	
	@FindBy(xpath = "//span[contains(@class, 'customTodayClass')]")
	private WebElement pick_a_date;
	
	//***************************
	
	@FindBy(xpath = "//*[@id='PaymentBy']")
	private WebElement payment_by_dropdown;	
	
	@FindBy(xpath = "//*[@id='PaymentBy']//option[text()='AMT']")
	private WebElement payment_by_option_AMT;
	
//	@FindBy(xpath = "(//*[@id='vehicleinvoiceFile'])[1]")
//	private WebElement dealer_invoice_input;
		
	@FindBy(xpath = "//*[text()='Dealer invoice']//ancestor::div[1]//div/div/div/div/input")
	private WebElement dealer_invoice_input;
	
	@FindBy(xpath = "//*[@id='btnBack']")
	private WebElement back_button;
	
	//direct to customer toggle button 
	
	@FindBy(xpath = "//*[text()='Direct to customer']//ancestor::div[1]//span")
	private WebElement direct_to_customer_toggle_button ;
	
	
	

	Properties prop;

	Actions act;

	VehicleOrderPage obj_vehicle_order_tab;
	
	CustomerContractPage obj_customer_contract_tab;

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
			
			obj_vehicle_order_tab.fill_supplier_information();
			
			obj_vehicle_order_tab.fill_purchase_order_information_for_used_car();	
			
			
		}
//		else
//		{
//			obj_vehicle_order_tab.fill_supplier_information();
//			// fill purchase order info under purchase order section
//			obj_vehicle_order_tab.fill_up_purchase_order_info_on_vehicle_order_tab();
//		}
//		
			
			}
			catch(Exception e1)
			{
				obj_vehicle_order_tab.fill_supplier_information();
				// fill purchase order info under purchase order section
				obj_vehicle_order_tab.fill_up_purchase_order_info_on_vehicle_order_tab();
			}
		
	
		//below lines of code for pre-purchase check
		
		try {			
			obj_vehicle_order_tab.fill_pre_purchase_check_information();
						
		} catch(Exception e)
		{

		}	
		String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		
		String delivery_status="";
		
		if(classOrMethodName.contains("With_Customer_Flow"))
		{
		}
		else {			
		//fill delivery info  
		obj_vehicle_order_tab.fill_up_delivery_info_on_vehicle_order_tab();
		
		
		//fill post delivery info
		obj_vehicle_order_tab.fill_up_post_delivery_info_on_vehicle_order_tab();
		
		delivery_status =  obj_vehicle_order_tab.get_status_text(vehicle_order_delivery_status);
		
		}
		
		Thread.sleep(4000);
		
				
		
		//save and Exit
		
		
		if(classOrMethodName.contains("Stocklist"))
		{
			obj_vehicle_order_tab.save_and_exit_order();
		}
		
			

		return delivery_status;
		
	}
	
	
	
	
public boolean verify_default_status_of_order_in_vehicle_order_tab() throws InterruptedException {
		
		obj_vehicle_order_tab = new VehicleOrderPage();

		// open vehicle order tab
		obj_vehicle_order_tab.open_vehicle_order_tab();
		
		
		Click.on(driver, order, 20);
		
		Thread.sleep(2000);
		
		
		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_order_pending, 10);

		elementColor = status_order_pending.getCssValue("color");

		if (elementColor.equals("rgba(199, 92, 0, 1)")) {

			LO.print("Order -- Default Status - Found OK i.e Order Pending ");
			System.out.println("Order -- Default Status - Found OK i.e Order Pending ");
			return true;

		} else {
			LO.print("Order -- Default Status- Found Wrong");
			System.err.println("Order -- Default Status- Found Wrong");
			return false;
		}		
		
	}
	


       public boolean verify_payment_status_should_be_reversed_to_payment_required_after_selecting_payment_by_AMT_on_vehicle_tab() throws InterruptedException
       {
    	   
			LO.print("");
			System.out.println("");
			
			LO.print("Click on a payments by dropdown and Select option AMT");
			System.out.println("Click on a payments by dropdown and Select option AMT");
	
            Click.on(driver, payment_by_dropdown, 10);
            
            Thread.sleep(2000);
            
            Click.on(driver, payment_by_option_AMT, 10);
            
            Thread.sleep(2000);

            obj_vehicle_order_tab = new VehicleOrderPage();
            
               	   
	   return obj_vehicle_order_tab.verify_default_payment_status_on_vehicle_tab();
	
       }

	public boolean verify_default_payment_status_on_vehicle_tab() throws InterruptedException {

		LO.print("");
		System.out.println("");
		
        Click.on(driver, payment_by_dropdown, 10);
        
        Thread.sleep(2000);
        
        Click.on(driver, payment_by_option_AMT, 10);
        
        Thread.sleep(2000);
			
		JavaScriptExecutor.click(driver, status_payments_to_dealer);
		
		Thread.sleep(2000);
		
		
		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_payment_required, 10);

		elementColor = status_payment_required.getCssValue("color");

		if (elementColor.equals("rgba(199, 92, 0, 1)")) {

			LO.print("Payments to dealer -- Default Status - Found OK i.e Payment Required");
			System.out.println("Payments to dealer -- Default Status - Found OK i.e Payment Required");
			return true;

		} else {
			LO.print("Payments to dealer -- Default Status - Found Wrong");
			System.err.println("Payments to dealer -- Default Status - Found Wrong");
			return false;
		}		
		
	}
	
	
	
public void verify_payment_status_after_selecting_payment_sent_date_on_vehicle_tab() throws InterruptedException {
	try {
	// Click on Payment Requested
	Click.on(driver, date_payment_requested_order_deposit, 10);
	Thread.sleep(8000);
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

	// pick date
	CommonClass.move_courser();
	Click.on(driver, pick_a_date, 20);
	Thread.sleep(5000);
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

	// Click on Payment Sent
	Click.on(driver, date_payment_sent_order_deposit, 20);
	Thread.sleep(8000);
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

	// pick date
	CommonClass.move_courser();
	Click.on(driver, pick_a_date, 20);
	Thread.sleep(5000);
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
	}catch(Exception e)
	{
		
	}
	
	
//	String elementColor = "";
//
//	ExplicitWait.visibleElement(driver, status_payment_sent, 10);
//
//	elementColor = status_payment_sent.getCssValue("color");
//
//	if (elementColor.equals("rgba(91, 158, 63, 1)")) {
//
//		LO.print("Payments to Supplier Status after selecting payment sent date - Found OK i.e Payment Sent");
//		System.out.println("Payments to Supplier Status after selecting payment sent date - Found OK i.e Payment Sent");
//		return true;
//
//	} else {
//		LO.print("Payments to Supplier Status after selecting payment sent date - Found Wrong");
//		System.err.println("Payments to Supplier Status after selecting payment sent date - Found Wrong");
//		return false;
//	}		
	
}


 public boolean verify_payment_status_after_selecting_payment_sent_date_for_amount_due_if_payment_by_AMT() throws InterruptedException
 {
		JavaScriptExecutor.scroll_in_to_view(driver, date_payment_requested_order_deposit);
		// Click on generate Invoice
		Click.on(driver, button_generate_invoice, 30);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		// Confirm pop up
		Click.on(driver, button_generate_invoice_cofirm, 30);
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon,300);

		Thread.sleep(10000);
		
		JavaScriptExecutor.scroll_in_to_view(driver, order_deposit);
		
		Thread.sleep(5000);		
		
		
		dealer_invoice_input.sendKeys(prop.getProperty("test_image_path"));
			
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);
		
		
		// Click on Payment Requested
		Click.on(driver, date_payment_requested_amount_due, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date
		CommonClass.move_courser();
		Click.on(driver, pick_a_date, 20);
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// Click on Payment Sent
		Click.on(driver, date_payment_sent_amount_due, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick date
		CommonClass.move_courser();
		Click.on(driver, pick_a_date, 20);
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);	
		
		
		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_payment_sent, 10);

		elementColor = status_payment_sent.getCssValue("color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Payments to Supplier Status after selecting payment sent date - Found OK i.e Payment Sent");
			System.out.println("Payments to Supplier Status after selecting payment sent date - Found OK i.e Payment Sent");
			return true;

		} else {
			LO.print("Payments to Supplier Status after selecting payment sent date - Found Wrong");
			System.err.println("Payments to Supplier Status after selecting payment sent date - Found Wrong");
			return false;
		}		


 }

//public boolean verify_payments_to_funder_statuses() throws InterruptedException, IOException {
//
//	LO.print("");
//	System.out.println("");
//
//	LO.print("Sterted Verifying Payments to Funder Statuses for required flow");
//	System.out.println("Sterted Verifying Payments to Funder Statuses for required flow");
//
//	JavaScriptExecutor.click(driver, status_payment);
//	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
//
//	int statusVerificationCount = 0;
//
//	String elementColor = "";
//
//	ExplicitWait.visibleElement(driver, status_payment_required, 10);
//
//	elementColor = status_payment_required.getCssValue("color");
//
//	if (elementColor.equals("rgba(199, 92, 0, 1)")) {
//
//		LO.print("Payment to funder -- Default Status - Found OK i.e Payment Required");
//		System.out.println("Payment to funder -- Default Status - Found OK i.e Payment Required");
//		statusVerificationCount++;
//
//	} else {
//		LO.print("Payment to funder -- Default Status- Found Wrong");
//		System.err.println("Payment to funder -- Default Status- Found Wrong");
//
//	}
//
//	JavaScriptExecutor.scroll_in_to_view(driver, back_button);
//	// Click on generate Invoice
//	Click.on(driver, button_generate_invoice, 30);
//	Thread.sleep(2000);
//	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
//
//	// Confirm pop up
//	Click.on(driver, button_generate_invoice_cofirm, 30);
//	Thread.sleep(2000);
//	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
//
//	// Click on Payment Requested
//	Click.on(driver, date_payment_requested_for_doc_fee, 20);
//	Thread.sleep(8000);
//	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
//
//	// pick date
//	CommonClass.move_courser();
//	Click.on(driver, pick_a_date, 20);
//	Thread.sleep(5000);
//	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
//
//	// Click on Payment Sent
//	Click.on(driver, date_payment_sent_for_doc_fee, 20);
//	Thread.sleep(8000);
//	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
//
//	// pick date
//	CommonClass.move_courser();
//	Click.on(driver, pick_a_date, 20);
//	Thread.sleep(5000);
//	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
//
//	// Click on generate Invoice
//	Click.on(driver, button_generate_invoice, 30);
//	Thread.sleep(2000);
//	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
//
//	// Confirm pop up
//	Click.on(driver, button_generate_invoice_cofirm, 30);
//	Thread.sleep(2000);
//	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
//
//	// Click on Payment Requested
//	Click.on(driver, date_payment_requested_for_finance_deposit, 20);
//	Thread.sleep(8000);
//	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
//
//	// pick date
//	CommonClass.move_courser();
//	Click.on(driver, pick_a_date, 20);
//	Thread.sleep(5000);
//	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
//
//	// Verify Status Payment requested
//
//	ExplicitWait.visibleElement(driver, status_payment_requested, 10);
//
//	elementColor = status_payment_requested.getCssValue("color");
//
//	if (elementColor.equals("rgba(199, 92, 0, 1)")) {
//
//		LO.print("Status for Payment after selecting payment requested date- Found OK i.e Payment Requested");
//		System.out.println(
//				"Status for Payment after selecting payment requested date- Found OK i.e Payment Requested");
//		statusVerificationCount++;
//
//	} else {
//		LO.print("Status for Payment after selecting payment requested date- Found Wrong");
//		System.err.println("Status for Payment after selecting payment requested date- Found Wrong");
//
//	}
//
//	// Click on Payment Sent
//	Click.on(driver, date_payment_sent_for_finance_deposit, 20);
//	Thread.sleep(8000);
//	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
//
//	// pick date
//	CommonClass.move_courser();
//	Click.on(driver, pick_a_date, 20);
//	Thread.sleep(5000);
//	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
//
//	ExplicitWait.visibleElement(driver, status_payment_sent, 10);
//
//	elementColor = status_payment_sent.getCssValue("color");
//
//	if (elementColor.equals("rgba(91, 158, 63, 1)")) {
//
//		LO.print("Status for Payment after selecting payment sent date- Found OK i.e Payment Sent");
//		System.out.println("Status for Payment after selecting payment sent date- Found OK i.e Payment Sent");
//		statusVerificationCount++;
//
//	} else {
//		LO.print("Status for Payment after selecting payment sent date- Found Wrong");
//		System.err.println("Status for Payment after selecting payment sent date- Found Wrong");
//
//	}
//
//	if (statusVerificationCount == 3) {
//		return true;
//	} else {
//		return false;
//	}
//
//}


	public boolean verify_order_status_in_vehicle_order_tab_after_uploading_order_form() throws InterruptedException, IOException, AWTException {
		
		obj_vehicle_order_tab = new VehicleOrderPage();
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
			obj_vehicle_order_tab.fill_supplier_information();
			// fill purchase order info under purchase order section
			obj_vehicle_order_tab.fill_up_purchase_order_info_on_vehicle_order_tab();
		}
		
		try {			
			obj_vehicle_order_tab.fill_pre_purchase_check_information();
						
		} catch(Exception e)
		{

		}	


		
	
		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_order_confirmed, 10);

		elementColor = status_order_confirmed.getCssValue("color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Order Status after uploading order form - Found OK i.e Order Confirmed ");
			System.out.println("Order Status after uploading order form - Found OK i.e Order Confirmed ");
			return true;

		} else {
			LO.print("Order Status after uploading order form - Found Wrong");
			System.err.println("Order Status after uploading order form - Found Wrong");
			return false;
		}		
		
	}

	
	
	public boolean verify_delivery_status_after_selecting_date_offered_in_delivery_section_of_vehicle_tab() throws InterruptedException, IOException, AWTException, ClassNotFoundException {
		
		obj_vehicle_order_tab = new VehicleOrderPage();
		
		Click.on(driver, delivered_to_dropdown,30);	
	
		Thread.sleep(500);		
	
		Click.on(driver, delivered_to_AMT,30);		
	
		Thread.sleep(10000);
		
		obj_vehicle_order_tab.select_date_offered_in_delivery_section_on_vehicle_order_tab();
		
		
		
		JavaScriptExecutor.click(driver, status_delivery);
//	    Click.on(driver, status_delivery, 10);
	    
	    Thread.sleep(2000);
		
		
		String elementBackgroundColor = "";

		ExplicitWait.visibleElement(driver, status_awaiting_booking, 10);

		elementBackgroundColor = status_awaiting_booking.getCssValue("background-color");

		if (elementBackgroundColor.equals("rgba(51, 65, 78, 1)")) {

			LO.print(
					"Delivery Status after selecting delivery date offered - Found OK i.e Awaiting Booking");
			System.out.println(
					"Delivery Status after selecting delivery date offered - Found OK i.e Awaiting Booking");
			 return true;

		} else {
			LO.print("Delivery Status after selecting delivery date offered - Found Wrong");
			System.err.println("Delivery Status after selecting delivery date offered - Found Wrong");
			return false;
		}
	}
	
	
	
	
public boolean verify_delivery_status_after_selecting_confirmed_delivery_date_in_delivery_section_of_vehicle_tab() throws InterruptedException, IOException, AWTException, ClassNotFoundException {
		
		obj_vehicle_order_tab = new VehicleOrderPage();
		
		obj_vehicle_order_tab.select_confirmed_delivery_date_in_delivery_section_on_vehicle_order_tab();		

		
		String elementBackgroundColor = "";

		ExplicitWait.visibleElement(driver, status_delivery_Booked, 10);

		elementBackgroundColor = status_delivery_Booked.getCssValue("background-color");

		if (elementBackgroundColor.equals("rgba(51, 65, 78, 1)")) {

			LO.print(
					"Delivery Status after selecting confirmed delivery date - Found OK i.e Delivery Booked");
			System.out.println(
					"Delivery Status after selecting confirmed delivery date - Found OK i.e Delivery Booked");
			 return true;

		} else {
			LO.print("Delivery Status after selecting confirmed delivery date - Found Wrong");
			System.err.println("Delivery Status after selecting confirmed delivery date - Found Wrong");
			return false;
		}
	}



public boolean verify_delivery_status_after_uploading_delivery_note_in_post_delivery_section_on_vehicle_order_tab() throws InterruptedException, IOException, AWTException, ClassNotFoundException {
	
	obj_vehicle_order_tab = new VehicleOrderPage();
	
	obj_vehicle_order_tab.fill_up_post_delivery_info_on_vehicle_order_tab();	

	
	String elementBackgroundColor = "";

	ExplicitWait.visibleElement(driver, status_delivered, 50);

	elementBackgroundColor = status_delivered.getCssValue("background-color");

	if (elementBackgroundColor.equals("rgba(51, 65, 78, 1)")) {

		LO.print(
				"Delivery Status after uploading the delivery note - Found OK i.e Delivered");
		System.out.println(
				"Delivery Status after uploading the delivery note - Found OK i.e Delivered");
		 return true;

	} else {
		LO.print("Delivery Status after uploading the delivery note - Found Wrong");
		System.err.println("Delivery Status after uploading the delivery note - Found Wrong");
		return false;
	}
}

public boolean verify_delivery_status_on_customer_contract_tab_and_vehicle_order_tab_are_same_when_deliver_to_customer_toggle_button_in_on() throws InterruptedException, IOException, AWTException, ClassNotFoundException {
		
		

		ExplicitWait.visibleElement(driver, status_delivered, 10);

		String elementBackgroundColorOnCustomerContratctTab = status_delivered.getCssValue("background-color");
		
		obj_vehicle_order_tab = new VehicleOrderPage();
		
		Thread.sleep(2000);
		
		Click.on(driver, vehicle_order, 20);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
		
		ExplicitWait.visibleElement(driver, status_delivered, 10);

		String elementBackgroundColorOnVehicleOrderTab = status_delivered.getCssValue("background-color");
		
				
		if (elementBackgroundColorOnCustomerContratctTab.equals(elementBackgroundColorOnVehicleOrderTab)) {

			LO.print("Delivery Status are running in parallel for Customer Contract Tab and Vehicle Order Tab , Found OK");
			System.out.println("Delivery Status are running in parallel for Customer Contract Tab and Vehicle Order Tab , Found OK");
			 return true;

		} else {
			LO.print("Delivery Status are not running in parallel for Customer Contract Tab and Vehicle Order Tab , Found Wrong");
			System.err.println("Delivery Status are not running in parallel for Customer Contract Tab and Vehicle Order Tab , Found Wrong");
			return false;
		}
	}



public boolean verify_delivery_section_gets_enabled_and_delivery_status_is_reversed_to_default_when_deliver_to_customer_toggle_button_is_made_off() throws InterruptedException, IOException, AWTException, ClassNotFoundException {
	
	
	LO.print("");
	System.out.println("");
	
	LO.print("Verifying delivery section gets enabled and delivery status is reversed to default when deliver to customer toggle button is made off");
	System.out.println("Verifying delivery section gets enabled and delivery status is reversed to default when deliver to customer toggle button is made off");	

	
	Click.on(driver, vehicle_order, 20);
	
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
	
		
	JavaScriptExecutor.click(driver, direct_to_customer_toggle_button);
	
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
	
	boolean deliverySection = false;
	
	try {ExplicitWait.visibleElement(driver, delivered_to_dropdown, 20);
	
	if(delivered_to_dropdown.isDisplayed())
	{
		deliverySection = true;
		
		LO.print("Delivery section is visible when direct to customer toggle button is made off");
		System.out.println("Delivery section is visible when direct to customer toggle button is made off");

	}	
	}
	catch(Exception e){
		
		LO.print("Delivery section is not visible when direct to customer toggle button is made off");
		System.err.println("Delivery section is not visible when direct to customer toggle button is made off");

	}
	
	ExplicitWait.visibleElement(driver, status_delivery_default, 20);
	
	String elementColor = status_delivery_default.getCssValue("background-color");
	
	boolean defaultDeliveryStatus = false ; 
	

	if (elementColor.equals("rgba(230, 171, 43, 1)")) {

		LO.print(
				"Delivery Status (after making direct to customer toggle off) gets reversed to default");
		System.out.println(
				"Delivery Status (after making direct to customer toggle off) gets reversed to default");
		defaultDeliveryStatus = true;

	} else {
		LO.print("Delivery Status (after making direct to customer toggle off) not reversed to default");
		System.err.println("Delivery Status (after making direct to customer toggle off) not reversed to default");
		defaultDeliveryStatus =  false;
	}

	if(deliverySection==true&&defaultDeliveryStatus==true)
		
	{
		return true;
	}
	else
	{
		return false;
	}
	
}


	public void fill_supplier_information() throws InterruptedException
	{
		Click.sendKeys(driver, supplier_name, "d", 30);
		Thread.sleep(500);
		supplier_name.sendKeys("u");
		Thread.sleep(500);
		supplier_name.sendKeys("m");
		Thread.sleep(500);
		supplier_name.sendKeys("m");
		Thread.sleep(500);
		supplier_name.sendKeys("y");
		Thread.sleep(500);
		supplier_name.sendKeys(" ");
		
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
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
		Thread.sleep(2000);
		
				
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
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);
		

	}

	
	public void fill_up_delivery_info_on_vehicle_order_tab()
			throws IOException, InterruptedException, AWTException, ClassNotFoundException {

		obj_vehicle_order_tab = new VehicleOrderPage();

		
		Click.on(driver, delivered_to_dropdown,30);	
		Thread.sleep(500);
		
		String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getName();
		
		
		if(classOrMethodName.contains("non_AMT_location"))
		{			
			Click.on(driver, delivered_to_non_AMT,30);		
		}else
		{			
		Click.on(driver, delivered_to_AMT,30);		
		}
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

	public void select_date_offered_in_delivery_section_on_vehicle_order_tab()
			throws IOException, InterruptedException, AWTException, ClassNotFoundException {

		obj_vehicle_order_tab = new VehicleOrderPage();

		
		Click.on(driver, delivered_to_dropdown,30);	
		Thread.sleep(500);
		
		Click.on(driver, delivered_to_AMT,30);		

		Thread.sleep(10000);
		
		JavaScriptExecutor.scroll_in_to_view(driver, delivered_to_dropdown);	
		// Click on  Date offered input
		Click.on(driver, vehicle_order_date_offered_input, 20);
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);
		
			

		// pick date offered
		Thread.sleep(8000);
		CommonClass.move_courser();
		Thread.sleep(2000);
		CommonClass.move_courser();
		Click.on(driver, vehicle_order_date_offered, 20);	
		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

	}

	
	public void select_confirmed_delivery_date_in_delivery_section_on_vehicle_order_tab()
			throws IOException, InterruptedException, AWTException, ClassNotFoundException {

		JavaScriptExecutor.scroll_in_to_view(driver, delivered_to_dropdown);	
		// Click on confirmed delivery date 
		Click.on(driver, vehicle_order_confirmed_delivery_date, 20);
		Thread.sleep(8000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);

		// pick confirmed delivery date
		CommonClass.move_courser();
		CommonClass.move_courser1();
		Click.on(driver, vehicle_order_pick_confirmed_delivery_date, 20);

		Thread.sleep(2000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 150);	
		
		 JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

	        // Use JavaScript to scroll to the bottom of the page
	        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		 

	}

	

	public void fill_up_purchase_order_info_on_vehicle_order_tab()
			throws IOException, InterruptedException, AWTException {

		obj_vehicle_order_tab = new VehicleOrderPage();

		// Dropdown.selectByVisibleText(driver, vehicle_order_register_to_dropdown,
		// "AMT", 60);
		
		Click.on(driver, vehicle_order_upload_order_form, 30);

		obj_vehicle_order_tab.upload_file(vehicle_order_upload_order_form, prop.getProperty("test_image_path"));

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
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
