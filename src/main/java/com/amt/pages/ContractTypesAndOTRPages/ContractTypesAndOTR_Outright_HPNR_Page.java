package com.amt.pages.ContractTypesAndOTRPages;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
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
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.ReadExcelCalculationForPurchaseAgreement;
import com.amt.testUtil.RemoveComma;

public class ContractTypesAndOTR_Outright_HPNR_Page extends TestBase {
	ContractTypesAndOTR_Outright_HPNR_Page obj_contract_types_outright_bch_ownbook_calculation_page;
	ReadExcelCalculationForPurchaseAgreement obj_read_excel_calculation_page;
	Actions act;

	
	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;
	

	@FindBy(xpath = "//*[@id ='acqOTRHeader']")
	private WebElement acq_contractTypes;

	@FindBy(xpath = "(//p[contains(text(),'Outright Purchase')])[1]")
	private WebElement acq_acq_contractTypes_outright;

	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/app-acquisition-common-otr-calculations[1]/form[1]/div[1]/div[1]/div[2]")
	private WebElement acq_contractTypes_calculation_table_basic_price;

	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/app-acquisition-common-otr-calculations[1]/form[1]/div[1]/div[1]/div[3]")
	private WebElement acq_contractTypes_calculation_table_discount;

	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/app-acquisition-common-otr-calculations[1]/form[1]/div[1]/div[1]/div[4]")
	private WebElement acq_contractTypes_calculation_table_additional_discount;

	@FindBy(xpath = "(//p[contains(text(),'Hire Purchase Non-Regulated')])[2]")
	private WebElement acq_contractTypes_customer_contract_HPNR;

	@FindBy(xpath = "//*[normalize-space()='Subtotal after discounts']//ancestor::div[1]//div[2]")
	private WebElement acq_contractTypes_subtotal_after_discounts;

	@FindBy(xpath = "//*[normalize-space()='RFL:']//ancestor::div[1]//p")
	private WebElement acq_contractTypes_road_tax_first_year;
	
	@FindBy(xpath = "//*[@id='roadTaxFirstYear']")
	private WebElement acq_contractTypes_road_tax_first_year_input;

	@FindBy(xpath = "//app-acquisition-common-otr-calculations//*[normalize-space()='Manufacturer delivery charges']//ancestor::div[1]//div[2]")
	private WebElement acq_contractTypes_manufacturer_delivery_charges;

	@FindBy(xpath = "//app-acquisition-common-otr-calculations//*[normalize-space()='First registration fee']//ancestor::div[1]//div[2]")
	private WebElement acq_contractTypes_first_registration_fee;

	@FindBy(xpath = "//app-acquisition-common-otr-calculations//*[normalize-space()='Rebate']//ancestor::div[1]//div[2]")
	private WebElement acq_contractTypes_rebate;

	
	@FindBy(xpath = "//*[normalize-space()='OTR for invoice:']//ancestor::div[1]//p")
	private WebElement acq_contractTypes_OTR_price;
	  
	@FindBy(xpath = "//*[@id='ListingPriceNew']")
	private WebElement acq_contractTypes_table_calculation_basic_vehicle_price;
	
	@FindBy(xpath = "//*[@id='collapseTwo']/app-acquisition-common-otr-calculations/form/div[1]/div/div[2]/div[3]")
	private WebElement acq_contractTypes_table_calculation_basic_paint_price;
	
	@FindBy(xpath = "//*[@id='collapseTwo']/app-acquisition-common-otr-calculations/form/div[1]/div/div[2]/div[4]")
	private WebElement acq_contractTypes_table_calculation_basic_options_price;
	
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
	

	public ContractTypesAndOTR_Outright_HPNR_Page() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean contractTypes_selection_and_OTR_calculation(String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {

		Click.on(driver, acq_contractTypes, 50);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		Thread.sleep(4000);
		
		Click.on(driver, acq_acq_contractTypes_outright, 50);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		LO.print(" Acquisition Contract type option = Outright Purchase (OP) has been selected");
		System.out.println("Acquisition Contract type option = Outright Purchase (OP) has been selected");

		Click.on(driver, acq_contractTypes_customer_contract_HPNR, 30);

		LO.print(" Customer Contract type option = HPNR has been selected");
		System.out.println(" Customer Contract type option = HPNR has been selected");
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, contract_types_cost_price_ex_vat_and_rfl, 20);
		ExplicitWait.visibleElement(driver, contract_types_vat, 20);
		//ExplicitWait.visibleElement(driver, contract_types_rfl_and_frf, 20);
		ExplicitWait.visibleElement(driver, contract_types_otr, 20);

		double cost_price_ex_vat_and_rfl_from_screen = Double
				.parseDouble(RemoveComma.of(contract_types_cost_price_ex_vat_and_rfl.getText().substring(2)));
		double vat = Double.parseDouble(RemoveComma.of(contract_types_vat.getText().substring(2)));
	//	double rfl_and_frf = Double.parseDouble(RemoveComma.of(contract_types_rfl_and_frf.getText().substring(2)));
		double otr = Double.parseDouble(RemoveComma.of(contract_types_otr.getText().substring(2)));

		LO.print("Cost Price ex VAT and RFL from screen is " + cost_price_ex_vat_and_rfl_from_screen);
		System.out.println("Cost Price ex VAT and RFL from screen is " + cost_price_ex_vat_and_rfl_from_screen);

		LO.print("VAT from screen is " + vat);
		System.out.println("VAT from screen is " + vat);

//		LO.print("RFL AND FRF from screen is " + rfl_and_frf);
//		System.out.println("RFL AND FRF from screen is " + rfl_and_frf);

		//double cost_price_ex_vat_and_rfl_expected = (otr - (rfl_and_frf + vat));

		obj_read_excel_calculation_page = new ReadExcelCalculationForPurchaseAgreement();

		obj_read_excel_calculation_page.write_vehicle_cost_Price_to_excel_for_used_car(
				cost_price_ex_vat_and_rfl_from_screen, 0, sheet_name);

		double cost_price_ex_vat_and_rfl_expected = GetExcelFormulaValue.get_formula_value(1, 1, sheet_name);

		
		
		LO.print("Cost Price ex VAT and RFL calculated is " + cost_price_ex_vat_and_rfl_expected);
		System.out.println("Cost Price ex VAT and RFL calculated is " + cost_price_ex_vat_and_rfl_expected);

		boolean status = false;
		if (Difference.of_two_Double_Values(cost_price_ex_vat_and_rfl_from_screen,
				cost_price_ex_vat_and_rfl_expected) < 0.2) {
			status = true;
			LO.print("Cost Price ex VAT and RFL verified and found OK");
			System.out.println("Cost Price ex VAT and RFL verified and found OK");
		} else {
			LO.print("Cost Price ex VAT and RFL found Wrong");
			System.err.println("Cost Price ex VAT and RFL found Wrong");
		}

		return status;

	}


	public boolean contractTypes_and_OTR_selection_outright_HPNR_Ownbook_calculation(String sheet_name)
			throws InterruptedException, IOException, UnsupportedFlavorException {
		Click.on(driver, acq_contractTypes, 50);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		Click.on(driver, acq_acq_contractTypes_outright, 50);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

		LO.print(" Acquisition Contract type option = Outright has been selected");
		System.out.println("Acquisition Contract type option = Outright has been selected");
		
		Click.on(driver, acq_contractTypes_customer_contract_HPNR, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		LO.print(" Customer Contract type option = Hire Purchase Non regulated (HPNR) has been selected");		 
		System.out.println(" Customer Contract type option = Hire Purchase Non regulated (HPNR) has been selected");
		
			
         
		ExplicitWait.visibleElement(driver, acq_contractTypes_table_calculation_basic_vehicle_price, 30);
		   acq_contractTypes_table_calculation_basic_vehicle_price.click();
	       
		   acq_contractTypes_table_calculation_basic_vehicle_price.sendKeys(Keys.chord(Keys.CONTROL, "a", "c"));

	       Clipboard clipboard =Toolkit.getDefaultToolkit().getSystemClipboard();
	       String vehicle_price_copied =(String) clipboard.getData(DataFlavor.stringFlavor);      
	           
	       
		   obj_read_excel_calculation_page =new ReadExcelCalculationForPurchaseAgreement();
		   
		   double subtotal_after_discount_excel= obj_read_excel_calculation_page.verify_table_calculations_contract_types_page(driver, vehicle_price_copied, acq_contractTypes_table_calculation_basic_paint_price,acq_contractTypes_table_calculation_basic_options_price, acq_contractTypes_calculation_table_discount, acq_contractTypes_calculation_table_additional_discount, sheet_name);	

		String subtotal_after_discount_actual = acq_contractTypes_subtotal_after_discounts.getText();
		
		LO.print("Subtotal after discount actual value from screen ="+subtotal_after_discount_actual);		 
		System.out.println("Subtotal after discount actual value from screen ="+subtotal_after_discount_actual);
		
		 
		String str = subtotal_after_discount_actual.substring(2);
		 
		String subtotal_after_discount_actual_converted=RemoveComma.of(str);
        
		
		double subtotal_after_discount_actual_from_screen=Double.parseDouble(subtotal_after_discount_actual_converted);
		boolean flag=false;
		double diff=Difference.of_two_Double_Values(subtotal_after_discount_excel, subtotal_after_discount_actual_from_screen);
		if(diff<0.2)
          {
	       flag =true;
          }

		return flag;
	}
	
	
	public boolean contractTypes_and_OTR_selection_outright_hpnr_vehicle_price_edited(String vehicleBasicPrice,
			String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
            
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		    
			Click.on(driver, acq_contractTypes, 40);
			
			Thread.sleep(2000);

			Click.on(driver, acq_acq_contractTypes_outright, 50);
		   
		   Thread.sleep(5000);
		   
		   act = new Actions(driver);
//		   act.sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.ENTER).build().perform();
		    
		   Click.on(driver, acq_contractTypes_customer_contract_HPNR , 50);
		   
		   LO.print("Contract type option has been selected");
		   
		   ExplicitWait.visibleElement(driver, acq_contractTypes_table_calculation_basic_vehicle_price, 30);
		   acq_contractTypes_table_calculation_basic_vehicle_price.click();
	       
		   acq_contractTypes_table_calculation_basic_vehicle_price.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	        
		   acq_contractTypes_table_calculation_basic_vehicle_price.sendKeys(vehicleBasicPrice);
		   
		   act.sendKeys(Keys.TAB).build().perform();
	       
		   obj_read_excel_calculation_page =new ReadExcelCalculationForPurchaseAgreement();
		   
		   double subtotal_after_discount_excel= obj_read_excel_calculation_page.verify_table_calculations_contract_types_page_edited(driver, vehicleBasicPrice, acq_contractTypes_table_calculation_basic_paint_price,acq_contractTypes_table_calculation_basic_options_price, acq_contractTypes_calculation_table_discount, acq_contractTypes_calculation_table_additional_discount, sheet_name);
		   
		   String subtotal_after_discount_actual = acq_contractTypes_subtotal_after_discounts.getText();
			
			LO.print("Subtotal after discount actual value from screen ="+subtotal_after_discount_actual);		 
			System.out.println("Subtotal after discount actual value from screen ="+subtotal_after_discount_actual);
			
			 
			String str = subtotal_after_discount_actual.substring(2);
			 
			String subtotal_after_discount_actual_converted=RemoveComma.of(str);
	       
			
			double subtotal_after_discount_actual_from_screen=Double.parseDouble(subtotal_after_discount_actual_converted);
			boolean flag=false;
			double diff=Difference.of_two_Double_Values(subtotal_after_discount_excel, subtotal_after_discount_actual_from_screen);
			if(diff<0.2)
	         {
		       flag =true;
	         }

			return flag;
			
		}
		
		public boolean verify_after_discount_calculations_contract_types_page_edited(String roadTaxForFirstYear,String sheet_name) throws IOException {
			
			 
			Click.sendKeys(driver, acq_contractTypes_road_tax_first_year_input, roadTaxForFirstYear, 30);
			act=new Actions(driver);
			act.sendKeys(Keys.TAB).build().perform();
			
			obj_read_excel_calculation_page =new ReadExcelCalculationForPurchaseAgreement();		
			return obj_read_excel_calculation_page.verify_after_discount_calculations_contract_types_page_edited(driver, 
				 
					acq_contractTypes_manufacturer_delivery_charges, 
					roadTaxForFirstYear,
					acq_contractTypes_first_registration_fee,
					acq_contractTypes_rebate, acq_contractTypes_OTR_price, 
					sheet_name);		
		}
		
	
	

	

	public boolean verify_after_discount_calculations_contract_types_page(String sheet_name) throws IOException {
		
		obj_read_excel_calculation_page =new ReadExcelCalculationForPurchaseAgreement();		
		return obj_read_excel_calculation_page.verify_after_discount_calculations_contract_types_page(driver, 
				acq_contractTypes_calculation_table_basic_price, 
				acq_contractTypes_calculation_table_discount,
				acq_contractTypes_calculation_table_additional_discount, 
				acq_contractTypes_manufacturer_delivery_charges, 
				acq_contractTypes_road_tax_first_year,
				acq_contractTypes_first_registration_fee,
				acq_contractTypes_rebate, acq_contractTypes_OTR_price, 
				sheet_name);		
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
