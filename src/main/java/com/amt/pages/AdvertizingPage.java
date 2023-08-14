package com.amt.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;

public class AdvertizingPage extends TestBase {

	

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	// Advertizing
	@FindBy(xpath = "//*[contains(text(),'Advertising')]")
	private WebElement advertising_tab;
	
	
	
	// AMT Website
	@FindBy(xpath = "//*[contains(text(),'AMT W')]")
	private WebElement amt_website;
	
	

	// AMT Website
	@FindBy(xpath = "//*[@class='d-flex align-items-center justify-content-between']//*[contains(text(),'Advertise')]")
	private WebElement amt_website_advertise_button;
	

	Properties prop;

	Actions act;
	
	OrdersListPage obj_orderlist_page;

	public AdvertizingPage() {
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

	public void open_advertizing_tab() throws IOException, InterruptedException {
		// Click on orders menu and enter to orders list page
		Click.on(driver, advertising_tab, 60);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
	}
	
	public void publish_vehicle_to_amt_website() throws IOException, InterruptedException {
		
		Click.on(driver, amt_website, 60);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		
		Click.on(driver, amt_website_advertise_button, 60);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
	}

	

	
	

}
