package com.amt.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Dropdown;
import com.amt.testUtil.ExplicitWait;

public class VehicleSelectionPage extends TestBase {

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;
	
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
	
	@FindBy(xpath = "//select[@name='assetTypeId']")
	private WebElement asset_type;
	
	
	@FindBy(xpath = "//span[normalize-space()='Used vehicle']")
	private WebElement used_vehicle;

	@FindBy(xpath = "//input[@name='registrationNumber']")
	private WebElement registration_no;
	
	@FindBy(xpath = "//input[@name='usedVehicleMileage']")
	private WebElement used_vehicle_mileage;
	
	@FindBy(xpath = "//button[contains(text(),'Search')]")
	private WebElement search_button;
	
	@FindBy(xpath = "//*[@class='acquisitionouter']//div[@tabindex='0']")
	private WebElement vehicle_option_used_car;
	
	@FindBy(xpath = "//*[@name='assetTypeId']")
	private WebElement dropdown_asset_type;
	
	
	
	
	public VehicleSelectionPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void select_vehicle(String manufacturer, String model) throws InterruptedException {
		
		Actions act=new Actions(driver);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		Click.sendKeys(driver, select_manufacturer_button, manufacturer, 120);
		

		LO.print("Manufacture ="+manufacturer+" has been selected");
		System.out.println("Manufacture ="+manufacturer+" has been selected");
		
		Thread.sleep(4000);
		act.sendKeys(Keys.ENTER).perform();
	

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		
		Click.sendKeys(driver, select_model_range, model , 120);	
		 
		LO.print("Model range ="+model+" has been selected");
		System.out.println("Model range ="+model+" has been selected");
		
		Thread.sleep(4000);
		act.sendKeys(Keys.ENTER).perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		
//		Click.on(driver, select_model, 120);
//		
//		Thread.sleep(3000);
//		
//		act.sendKeys(Keys.ARROW_DOWN).build().perform();
//		act.sendKeys(Keys.ENTER).perform();
//		
//	
		act.doubleClick(vehile_table_option).build().perform();
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		LO.print("Vehicle has been selected");
		System.out.println("Vehicle has been selected");
		
	}
	
public void select_LCV_vehicle(String manufacturer, String model) throws InterruptedException {
		
		Actions act=new Actions(driver);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		Dropdown.selectByVisibleText(driver, dropdown_asset_type, " LCV", 120);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
	
		
		Click.sendKeys(driver, select_manufacturer_button, manufacturer, 120);
		

		LO.print("Manufacture ="+manufacturer+" has been selected");
		System.out.println("Manufacture ="+manufacturer+" has been selected");
		
		Thread.sleep(4000);
		act.sendKeys(Keys.ENTER).perform();
	

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		
		Click.sendKeys(driver, select_model_range, model ,120);	
		 
		LO.print("Model range ="+model+" has been selected");
		System.out.println("Model range ="+model+" has been selected");
		
		Thread.sleep(4000);
		act.sendKeys(Keys.ENTER).perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		
		Click.on(driver, select_model, 120);
		
		Thread.sleep(3000);
		
		act.sendKeys(Keys.ARROW_DOWN).build().perform();
		act.sendKeys(Keys.ENTER).perform();
		
	
		act.doubleClick(vehile_table_option).build().perform();
		
		LO.print("Vehicle has been selected");
		System.out.println("Vehicle has been selected");
		
	}
	
	
public void select_vehicle_lcv(String manufacturer, String model) throws InterruptedException {
		
	
	
	    Dropdown.selectByVisibleText(driver, asset_type, " LCV", 120);
	    
	    ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon,120);
	    
	
		Actions act=new Actions(driver);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		Click.sendKeys(driver, select_manufacturer_button, manufacturer, 120);
		

		LO.print("Manufacture ="+manufacturer+" has been selected");
		System.out.println("Manufacture ="+manufacturer+" has been selected");
		
		Thread.sleep(4000);
		act.sendKeys(Keys.ENTER).perform();
	

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		
		Click.sendKeys(driver, select_model_range, model , 120);	
		 
		LO.print("Model range ="+model+" has been selected");
		System.out.println("Model range ="+model+" has been selected");
		
		Thread.sleep(4000);
		act.sendKeys(Keys.ENTER).perform();

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		
		Click.on(driver, select_model, 120);
		
		Thread.sleep(3000);
		
		act.sendKeys(Keys.ARROW_DOWN).build().perform();
		act.sendKeys(Keys.ENTER).perform();
		
	
		act.doubleClick(vehile_table_option).build().perform();
		
		LO.print("Vehicle has been selected");
		System.out.println("Vehicle has been selected");
		
	}
	

public void select_vehicle_for_used_car_flow(String regitrationNumber , String mileage) throws InterruptedException {
	
	LO.print("Selecting used vehicle with registration no. = "+regitrationNumber+" and mileage = "+mileage);
	System.out.println("Selecting used vehicle with registration no. = "+regitrationNumber+" and mileage = "+mileage);
	
	
	Click.on(driver, used_vehicle, 120);   
	 
    Click.sendKeys(driver, registration_no, regitrationNumber, 120);
    
    Click.sendKeys(driver, used_vehicle_mileage, mileage, 120);
    
    Click.on(driver, search_button, 120);
    
    ExplicitWait.visibleElement(driver, vehicle_option_used_car, 120);
    
    
    Actions act = new Actions(driver);
    
    act.doubleClick(vehicle_option_used_car).build().perform();
    
      
	LO.print("Vehicle selected");
	System.out.println("Vehicle selected");    
    
   
}

public void select_vehicle_for_used_LCV(String regitrationNumber , String mileage) throws InterruptedException {
	
	LO.print("Selecting used vehicle with registration no. = "+regitrationNumber+" and mileage = "+mileage);
	System.out.println("Selecting used vehicle with registration no. = "+regitrationNumber+" and mileage = "+mileage);
	
	
	Click.on(driver, used_vehicle, 30); 
	
	Thread.sleep(2000);
	
	 Dropdown.selectByVisibleText(driver, asset_type, " LCV", 20);
	    
	 ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);	
	
	 
    Click.sendKeys(driver, registration_no, regitrationNumber, 30);
    
    Click.sendKeys(driver, used_vehicle_mileage, mileage, 30);
    
    Click.on(driver, search_button, 120);
    
    ExplicitWait.visibleElement(driver, vehicle_option_used_car, 30);
    
    
    Actions act = new Actions(driver);
    
    act.doubleClick(vehicle_option_used_car).build().perform();
    
      
	LO.print("Vehicle selected");
	System.out.println("Vehicle selected");    
    
   
}


	
	
	
	
}
