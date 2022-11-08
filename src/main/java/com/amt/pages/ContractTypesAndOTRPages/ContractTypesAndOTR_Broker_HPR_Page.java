package com.amt.pages.ContractTypesAndOTRPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;

public class ContractTypesAndOTR_Broker_HPR_Page extends TestBase {
	
	@FindBy(xpath = "//*[@id ='acqOTRHeader']")
	private WebElement acq_contractTypes;
	
	@FindBy(xpath = "//p[contains(text(),'Broker')]")
	private WebElement acq_contractTypes_option_broker;
	
	@FindBy(xpath = "//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/div[7]/div[1]/div[1]/div[3]/div[1]/button[1]")
	private WebElement quote_alert;	
   
	@FindBy(xpath = "//p[contains(text(),'Hire Purchase Regulated')]")
	private WebElement acq_contractTypes_cust_HPR;	
	
	@FindBy(xpath = "//p[@class='text-left text-muted pr-1']")
	private WebElement acq_contractTypes_OTR_price;
	  
	@FindBy(xpath = "//*[@id=\"ListingPriceNew\"]")
	private WebElement acq_contractTypes_table_calculation_basic_vehicle_price;
	
	@FindBy(xpath = "//*[@id=\"collapseTwo\"]/app-acquisition-common-otr-calculations/form/div[1]/div/div[2]/div[3]")
	private WebElement acq_contractTypes_table_calculation_basic_paint_price;
	
	@FindBy(xpath = "//*[@id=\"collapseTwo\"]/app-acquisition-common-otr-calculations/form/div[1]/div/div[2]/div[4]")
	private WebElement acq_contractTypes_table_calculation_basic_options_price;
	
	
	public ContractTypesAndOTR_Broker_HPR_Page() {
		PageFactory.initElements(driver, this);
	}

		
	
	
	public void contractTypes_and_OTR_selection_broker_hpr() throws InterruptedException {

		Click.on(driver, acq_contractTypes, 40);
		
		Thread.sleep(2000);

	   Click.on(driver, acq_contractTypes_option_broker, 50);
	   
	   Thread.sleep(2000);
	   
	   Click.on(driver, quote_alert, 25);
	   
	   Click.on(driver, acq_contractTypes_cust_HPR, 0);
	   LO.print("Contract type option has been selected");
		
	}
	

}
