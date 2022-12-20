package com.amt.pages.ContractTypesAndOTRPages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;

public class ContractTypesAndOTR_Outright_Outright_Page extends TestBase {
	
	
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




	
}
