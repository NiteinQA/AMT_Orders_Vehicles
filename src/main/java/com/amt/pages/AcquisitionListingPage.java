package com.amt.pages;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Dropdown;
import com.amt.testUtil.ExplicitWait;

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
			
				
		public AcquisitionListingPage() {
			PageFactory.initElements(driver, this);
		}		
		
		
		public void aquisition_Listingpage_AddnewQuote() throws InterruptedException {
			
			
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			
			
			Click.on(driver, roles_dropdown, 60);
			
			 Thread.sleep(1000);
			
			Click.on(driver, super_admin, 60);
			
						
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
			
		   Thread.sleep(2000);	
			
			Click.on(driver, aquisition_quotes_button, 50);
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
			
			Click.on(driver, new_quote_button, 50);	
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
			
			 
			WebElement advance_search =driver.findElement(By.xpath("//*[@id='divVehicleSummary']/div/div/div/div[2]/div/div[1]/div/div[3]/div/div[2]/button")); 
			ExplicitWait.clickableElement(driver, advance_search, 30);
			
			
			LO.print("Clicked on Quote button ");
			System.out.println("Clicked on Quote button ");

			
			
		}
		



}
