package com.amt.pages;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Dropdown;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.JavaScriptExecutor;

public class AcquisitionListingPage extends TestBase {

	
	

		@FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-list[1]/div[2]/div[1]/div[1]/div[1]/div[2]/ag-grid-angular[1]/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/span[1]")
		private WebElement aquisition_quotes_Listinglink;
		
		@FindBy(linkText = "Acquisition quotes")
		private WebElement aquisition_quotes_button;
		
		@FindBy(xpath = "//img[@alt='Loading...']")
		private List<WebElement> loading_icon;	

		
		@FindBy(xpath = "//span[normalize-space()='New quote']")
		private WebElement new_quote_button;
		
		
		@FindBy(xpath = "//*[@id='dropdownRole']")
		private WebElement roles_dropdown;	
		
		
		@FindBy(xpath = "//*[contains(text(), 'Super Admin')]")
		private WebElement super_admin;
		
		@FindBy(xpath = "//body//app-root//app-header//div[@role='dialog']//div//div//div//div//button[contains(text(),'Yes')]")
		private WebElement pop_up_yes;
		
		// acquisition_quote_search_bar
	     @FindBy(xpath = "//*[@ref='eCenterContainer']")
		public WebElement quote_at_first_position;
	     
	 	// acquisition_quote_search_bar

	 	@FindBy(xpath = "// *[@id='vehicleSearchInp']")
	 	private WebElement acquisition_quote_search_bar;  
			
	 	Properties prop; 
	 	
		public AcquisitionListingPage() {
			
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
		
		
		public void select_role_super_admin() throws InterruptedException {
			
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			
			
			Click.on(driver, roles_dropdown, 60);
			
			 Thread.sleep(1000);
			
			Click.on(driver, super_admin, 60);
			
						
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
			
		}
		
		public void go_for_a_new_test() throws InterruptedException
		{
			
			Thread.sleep(2000);
			
			Click.on(driver, aquisition_quotes_button, 50);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
			
			Thread.sleep(2000);			

			Click.on(driver, pop_up_yes, 60);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
			
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			
			LO.print          ("");
			LO.print          ("");
			LO.print          ("");
			LO.print          ("");
		}
		
		public void aquisition_Listingpage_AddnewQuote() throws InterruptedException {
			
			
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			
			 Thread.sleep(25000);
			 
			Click.on(driver, roles_dropdown, 60);
			
			 Thread.sleep(1000);
			
			Click.on(driver, super_admin, 60);
			
						
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
			
		   Thread.sleep(10000);	
			
			Click.on(driver, aquisition_quotes_button, 50);	
			
			Thread.sleep(5000);	
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
			
			Thread.sleep(10000);	
			
			//Click.on(driver, new_quote_button, 50);
			
			JavaScriptExecutor.click(driver, new_quote_button);
			
			Thread.sleep(2000);	
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
			
			 
			LO.print("Clicked on Quote button ");
			System.out.println("Clicked on Quote button ");

			
			
		}
		
	public void open_saved_quote_to_edit(String sheetName) throws InterruptedException, IOException {
			
			
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			
			
			Click.on(driver, roles_dropdown, 60);
			
			 Thread.sleep(1000);
			
			Click.on(driver, super_admin, 60);
			
     		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
			
			String quote_no = GetExcelFormulaValue.get_cell_value(1, 0, sheetName);
			
			System.out.println("Using Quote number "+quote_no);

			// ***************Part 2 - Copying the quote to make new
			// quote***************************
			Thread.sleep(2000);
			
			Click.on(driver, aquisition_quotes_button, 60);
			
			Thread.sleep(5000);
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
			
			Thread.sleep(2000);
			
			Click.sendKeys(driver, acquisition_quote_search_bar, quote_no, 60);		
			
			Thread.sleep(8000);
			
	     	ExplicitWait.visibleElement(driver, quote_at_first_position, 30);
	     	
	     	Thread.sleep(2000); 
	     	
	        Actions act = new Actions(driver);
	     
	        act.doubleClick(quote_at_first_position).perform();	
	        
	        Thread.sleep(2000);
	        
	        act.doubleClick(quote_at_first_position).perform();	

	    	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
			
			
		}
	
	public String quote_save_sheet_name_from_quote_save_excel_sheet(String classOrMethodName) {
		String sheetName = "";
		
		if (classOrMethodName.contains("broker_business_hire")) {
			sheetName = prop.getProperty("BrokerBCHQuoteNo");
		}

		if (classOrMethodName.contains("broker_individual_hire")) {
			sheetName = prop.getProperty("BrokerPCHQuoteNo");
		}

		if (classOrMethodName.contains("broker_business_purchase")) {
			sheetName = prop.getProperty("BrokerHPNRQuoteNo");
		}

		if (classOrMethodName.contains("broker_individual_purchase")) {
			sheetName = prop.getProperty("BrokerPCPQuoteNo");
		}
		
		if (classOrMethodName.contains("ownbook_business_hire")) {
			
			if (classOrMethodName.contains("ownbook_business_hire_accept_with_changes_for_quotes_of_maint_exists"))
			{				
				sheetName = prop.getProperty("HPNRBCH_withMaint");					
			}else
			{
				sheetName = prop.getProperty("HPNRBCHQuoteNo");
			}
		}

		if (classOrMethodName.contains("ownbook_individual_hire")) {
			sheetName = prop.getProperty("HPNRPCHQuoteNo");
		}

		if (classOrMethodName.contains("ownbook_business_purchase")) {
			sheetName = prop.getProperty("HPNRHPNRQuoteNo");
		}

		if (classOrMethodName.contains("ownbook_individual_purchase")) {
			sheetName = prop.getProperty("HPNRPCPQuoteNo");
		}
		
		if (classOrMethodName.contains("ownbook_business_hire_funder")) {
			sheetName = prop.getProperty("HPNRBCHFunderQuoteNo");
		}
		
		if (classOrMethodName.contains("ownbook_individual_hire_funder")) {
			sheetName = prop.getProperty("HPNRPCHFunderQuoteNo");
		}
		
		if (classOrMethodName.contains("ownbook_business_purchase_funder")) {
			sheetName = prop.getProperty("HPNRHPNRFunderQuoteNo");
		}
		
		if (classOrMethodName.contains("ownbook_individual_purchase_funder")) {
			sheetName = prop.getProperty("HPNRPCPFunderQuoteNo");
		}
		
		if (classOrMethodName.contains("OP_OP")) {
			sheetName = prop.getProperty("OP-OP");
		}	
		
		
		return sheetName;
	}




public String calculation_sheet_name_from_quote_save_excel_sheet(String classOrMethodName) {
	String sheetName = "";
	
	
	if (classOrMethodName.contains("ownbook_business_hire")) {
		sheetName = prop.getProperty("calculation_sheet_ownbook_business_hire");
	}

	if (classOrMethodName.contains("ownbook_individual_hire")) {
		sheetName = prop.getProperty("calculation_sheet_ownbook_individual_hire");
	}

	if (classOrMethodName.contains("ownbook_business_purchase")) {
		sheetName = prop.getProperty("calculation_sheet_ownbook_business_purchase");
	}

	if (classOrMethodName.contains("ownbook_individual_purchase")) {
		sheetName = prop.getProperty("calculation_sheet_ownbook_individual_purchase");
	}
	
	
	if (classOrMethodName.contains("ownbook_business_hire_funder")) {
		sheetName = prop.getProperty("calculation_sheet_ownbook_business_hire_funder");
	}
	
	if (classOrMethodName.contains("ownbook_individual_hire_funder")) {
		sheetName = prop.getProperty("calculation_sheet_ownbook_individual_hire_funder");
	}
	
	if (classOrMethodName.contains("ownbook_business_purchase_funder")) {
		sheetName = prop.getProperty("calculation_sheet_ownbook_business_purchase_funder");
	}
	
	if (classOrMethodName.contains("ownbook_individual_purchase_funder")) {
		sheetName = prop.getProperty("calculation_sheet_ownbook_individual_purchase_funder");
	}
	
	return sheetName;
}
}


