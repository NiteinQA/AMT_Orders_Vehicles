package com.amt.pages.ContractTypesAndOTRPages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Difference;
import com.amt.testUtil.Dropdown;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.ReadExcelCalculationForPurchaseAgreement;
import com.amt.testUtil.RemoveComma;

public class ContractTypesAndOTR_Outright_Outright_Page extends TestBase {
	
	ReadExcelCalculationForPurchaseAgreement obj_read_excel_calculation_page;
	
	
	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;
	
	@FindBy(xpath = "//*[@id ='acqOTRHeader']")
	private WebElement acq_contractTypes;
	
	@FindBy(xpath = "//p[contains(text(),'Broker')]")
	private WebElement acq_contractTypes_option_broker;
	
	@FindBy(xpath = "//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/div[7]/div[1]/div[1]/div[3]/div[1]/button[1]")
	private WebElement quote_alert;	
   

	@FindBy(xpath = "//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/p[1]")
	private WebElement acq_acq_contractTypes_outright;
	
	@FindBy(xpath = "//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/p[1]")
	private WebElement acq_cutomer_contractTypes_outright;
	
	@FindBy(xpath = "//*[normalize-space()='OTR for invoice:']//ancestor::div[1]//p")
	private WebElement acq_contractTypes_OTR_price;
	
	//other_support_type dropdown 
	
			@FindBy(xpath = "//*[@name='OtherSupportType']")
			private WebElement other_support_type;

			//remarks_text 
			
			@FindBy(xpath = "//*[@name='RemarksText']")
			private WebElement remarks_text;

			//other_support_value
			
			@FindBy(xpath = "//*[@name='OtherSupportValue']")
			private WebElement other_support_value;

			//add_other_support_button
			
			@FindBy(xpath = "//*[normalize-space()='Other support']//ancestor::div[1]//div[2]//div/div[4]/a")
			private WebElement add_other_support_button;
			
			//on_road_price_for_calculation

			@FindBy(xpath = "(//*[normalize-space()='On the road price for calculation']//ancestor::div[1]/div)[2]")
			private WebElement on_road_price_for_calculation;

			//delete_other_support button
			
			@FindBy(xpath = "//*[@src='/assets/images/delete.svg']")
			private WebElement delete_other_support;
			
			@FindBy(xpath = "//*[normalize-space()='Cost price ex. VAT & RFL']//ancestor::div[1]//div//strong")
			private WebElement contract_types_cost_price_ex_vat_and_rfl;

			@FindBy(xpath = "//*[normalize-space()='VAT']//ancestor::div[1]//div//strong")
			private WebElement contract_types_vat;

			@FindBy(xpath = "//*[normalize-space()='RFL & FRF']//ancestor::div[1]//div//strong")
			private WebElement contract_types_rfl_and_frf;

			@FindBy(xpath = "//*[normalize-space()='Cost OTR price']//ancestor::div[1]//div//strong")
			private WebElement contract_types_otr;

			@FindBy(xpath = "//input[@id='ListingPriceUsed']")
			private WebElement vehicle_cost_price_input;

			@FindBy(xpath = "//input[@id='roadTaxFirstYear']")
			private WebElement rfl_input;

			@FindBy(xpath = "//*[@id='preparationCost']")
			private WebElement options_cost_input;
	
	
	public ContractTypesAndOTR_Outright_Outright_Page() {
		PageFactory.initElements(driver, this);
	}

	public void contractTypes_and_OTR_selection_outright_outright() throws InterruptedException {
		
		Click.on(driver, acq_contractTypes, 50);
		Thread.sleep(2000);
		Click.on(driver, acq_acq_contractTypes_outright, 50);
		
		Thread.sleep(4000);		
	    
	   Actions act=new Actions(driver);
	   act.sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB).sendKeys(Keys.ENTER).build().perform();

	   Click.on(driver, acq_cutomer_contractTypes_outright, 50);   
	   
	   LO.print("Contract type option has been selected");		
	}
	

public boolean verify_other_support_calculations(String otherSupportValue, String sheet_name)
			throws IOException, InterruptedException {		

		
		LO.print("Verification of OTR price for calculation after adding other support value has been started");
		System.out
				.println("Verification of OTR price for calculation after adding other support value has been started");

		
       // Adding other support
		for (int i = 0; i <= 2; i++) {

			Dropdown.select(driver, other_support_type, i, 20);

			Click.sendKeys(driver, remarks_text, " Support " + i, 20);

			Click.sendKeys(driver, other_support_value, otherSupportValue, 20);

			JavascriptExecutor jse = (JavascriptExecutor) driver;

			ExplicitWait.visibleElement(driver, add_other_support_button, 30);

			jse.executeScript("arguments[0].click();", add_other_support_button);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		}

		Thread.sleep(3000);
		
		 // Deleting other support

		Click.on(driver, delete_other_support, 20);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		Click.on(driver, delete_other_support, 20);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		//reading OTR_for_calculation value from screen 
		
		
		ExplicitWait.visibleElement(driver, on_road_price_for_calculation, 30);

		double onRoadPriceForCalculationActual = Double
				.parseDouble(RemoveComma.of(on_road_price_for_calculation.getText().trim().substring(2)));

		
		
		double otherSupportConverted = Double.parseDouble(otherSupportValue);
		
		//writing other support values to Excel 
		
		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();		
		
		double OTRValueExpected = obj_read_excel_calculation_page.verify_OTR_for_calculation_after_adding_other_support_values_to_excel(otherSupportConverted, sheet_name);
					
		
		ExplicitWait.visibleElement(driver, acq_contractTypes_OTR_price, 30);
		double onRoadPriceorInvoice = Double
				.parseDouble(RemoveComma.of(acq_contractTypes_OTR_price.getText().trim().substring(2)));

		System.out.println("");
		
		boolean status = false;

		if (Difference.of_two_Double_Values(OTRValueExpected, onRoadPriceForCalculationActual) < 0.2) {
			status = true;

			LO.print("OTR price for calculation after adding other support found OK");
			System.out.println("OTR price for calculation after adding other support found OK");
		}

		else {
			LO.print("OTR price for calculation after adding other support found wrong");
			System.err.println("OTR price for calculation after adding other support found wrong");

		}

	 
		System.out.println("");

		LO.print("Verification of OTR price for calculation after adding other support value has been ended");
		System.out.println("Verification of OTR price for calculation after adding other support value has been ended");

		return status ;
		
	}





	
}
