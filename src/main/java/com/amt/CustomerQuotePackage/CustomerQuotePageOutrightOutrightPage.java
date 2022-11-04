package com.amt.CustomerQuotePackage;

import java.sql.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Dropdown;
import com.amt.testUtil.ExplicitWait;

public class CustomerQuotePageOutrightOutrightPage extends TestBase {



	@FindBy(xpath = "//div[@class='row acquisition-menu']//div[3]//button[1]")
	private WebElement save_button;


	
	@FindBy(xpath = "//*[@id=\"vehicleSummery\"]/div/div[2]/div[2]/div[3]/span[2]")
	                 
	private WebElement quote_ref_no;
	

	public CustomerQuotePageOutrightOutrightPage() {
		PageFactory.initElements(driver, this);
	}

	
	public void customer_Quote_outright_outright() throws InterruptedException {
		
		Click.on(driver, save_button, 25);
		
		LO.print("Customer quote saved successfully");
		
       ExplicitWait.visibleElement(driver, quote_ref_no, 25);
		
		String quoteRefNo=quote_ref_no.getText();
		System.out.println(quoteRefNo);
		
		LO.print("Quote Refno. Generated successfully is"+quoteRefNo);

	}

	
}
