package com.amt.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;

public class ContractTypesAndOTRPage extends TestBase {
	
	@FindBy(xpath = "/html[1]/body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/app-aquisition-header[1]/div[1]/div[1]/div[1]/ul[1]/li[4]/a[1]/p[1]")
	private WebElement acq_contractTypes;
	
	@FindBy(xpath = "//p[contains(text(),'Broker')]")
	private WebElement acq_contractTypes_option_broker;
	
	@FindBy(xpath = "//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/div[7]/div[1]/div[1]/div[3]/div[1]/button[1]")
	private WebElement quote_alert;	
   
	//@FindBy(xpath = "//*[@id=\"customer_contract_change_supplier\"]//div[@class='modal-dialog modal-dialog-centered']//div//div/button[contains(text(),'Yes')]")
	//private WebElement quote_alert;
	
	@FindBy(xpath = "//p[contains(text(),'Hire Purchase Regulated')]")
	private WebElement acq_contractTypes_cust_HPR;	
	

	@FindBy(xpath = "//p[contains(text(),'Personal Contract Hire')]")
	private WebElement acq_customer_contractTypes_option_personal_hire;
	
	@FindBy(xpath = "//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/p[1]")
	private WebElement acq_acq_contractTypes_outright;
	
	@FindBy(xpath = "//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-otr[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/p[1]")
	private WebElement acq_cutomer_contractTypes_outright;
	
	
	
	
	public ContractTypesAndOTRPage() {
		PageFactory.initElements(driver, this);
	}

	
	
	
	public void contractTypes_and_OTR_selection_broker_bch() throws InterruptedException {

		Click.on(driver, acq_contractTypes, 40);
		
		Thread.sleep(2000);

	   Click.on(driver, acq_contractTypes_option_broker, 50);
	   
	   Thread.sleep(5000);
	   
	    Click.on(driver, quote_alert, 25);
	   
	   LO.print("Contract type option has been selected");

		
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
	public void contractTypes_and_OTR_selection_hpnr_bch() throws InterruptedException {

		Click.on(driver, acq_contractTypes, 40);
		
		Thread.sleep(2000);	
		LO.print("Contract type option has been selected");
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




	public void contractTypes_and_OTR_selection_outright_BCH_Ownbook_calculation() throws InterruptedException {
		Click.on(driver, acq_contractTypes, 50);
		Thread.sleep(2000);
		Click.on(driver, acq_acq_contractTypes_outright, 50);
		
		Thread.sleep(4000);		
	    
	   Actions act=new Actions(driver);
	   act.sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB).sendKeys(Keys.ENTER).build().perform();

	  LO.print("Contract type option has been selected");
		
	}

}
