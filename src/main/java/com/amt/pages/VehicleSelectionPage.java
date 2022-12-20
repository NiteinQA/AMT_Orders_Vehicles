package com.amt.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.ExplicitWait;

public class VehicleSelectionPage extends TestBase {

	
	@FindBy(xpath = "//ng-select[@id='Make']//input[@role='combobox']")
	private WebElement select_manufacturer_button;
    
	@FindBy(xpath = "//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-fleet-selector[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/ng-select[1]/div[1]/div[1]/div[3]/input[1]")
	private WebElement select_model_range;

//	@FindBy(xpath = "//*[@class='ng-option ng-option-marked']")
//	private WebElement select_model_range_option;

	@FindBy(xpath = "//*[@placeholder='Select model']")
	private WebElement select_model;
	
	
	@FindBy(xpath = "//*[@id=\"80451\"]")
	private WebElement select_model_option;
	
		
	@FindBy(xpath = "//input[@placeholder='Select derivative']")
	private WebElement select_derivative;
	
	@FindBy(xpath = "//*[@id=\"divVehicleTable\"]/div[1]/div/div[1]/div/div[1]/div[2]")
	private WebElement vehile_table_option;
	
	@FindBy(xpath = "//*[@id='divVehicleSummary']/div/div/div/div[2]/div/div[1]/div/div[3]/div/div[2]/button")
	private WebElement advance_search;
	
	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;
	
	
	
	public VehicleSelectionPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void select_vehicle(String manufacturer, String model) throws InterruptedException {
		
		Actions act=new Actions(driver);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		
		Click.sendKeys(driver, select_manufacturer_button, manufacturer, 40);
		

		LO.print("Manufacture ="+manufacturer+" has been selected");
		System.out.println("Manufacture ="+manufacturer+" has been selected");
		
		Thread.sleep(4000);
		act.sendKeys(Keys.ENTER).perform();
	

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		
		Click.sendKeys(driver, select_model_range, model , 40);	
		 
		LO.print("Model range ="+model+" has been selected");
		System.out.println("Model range ="+model+" has been selected");
		
		Thread.sleep(4000);
		act.sendKeys(Keys.ENTER).perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		
		Click.on(driver, select_model, 30);
		
		Thread.sleep(3000);
		
		act.sendKeys(Keys.ARROW_DOWN).build().perform();
		act.sendKeys(Keys.ENTER).perform();
		
	
		act.doubleClick(vehile_table_option).build().perform();
		
		LO.print("Vehicle has been selected");
		System.out.println("Vehicle has been selected");
		
	}
	
	
	
	
	
}
