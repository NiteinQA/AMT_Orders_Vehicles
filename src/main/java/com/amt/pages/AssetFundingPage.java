package com.amt.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.CommonClass;
import com.amt.testUtil.Dropdown;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.JavaScriptExecutor;

import java.awt.AWTException;
import java.awt.Robot;

public class AssetFundingPage extends TestBase {

	JavascriptExecutor js;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;	
	
	@FindBy(xpath = "//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details")
	private List<WebElement> stocking_plan_list;

	// Asset funding Element
	@FindBy(xpath = "//*[contains(text(),'Asset funding')]")
	private WebElement asset_funding;

	// Cash purchase Element
	@FindBy(xpath = "//*[contains(text(),' Cash purchase ')]")
	private WebElement status_cash_purchase;
	
	// Stocking plan Element
	@FindBy(xpath = "//*[contains(text(),' Stocking plan ')]")
	private WebElement status_stocking_plan;
	
	// Required Element
	@FindBy(xpath = "//*[contains(text(),' Required ')]")
	private WebElement status_required;
	
	// funded Element
	@FindBy(xpath = "//*[contains(text(),' Funded ')]")
	private WebElement status_funded;
	
	// funded Element
	@FindBy(xpath = "//*[contains(text(),'Add Funder Quote')]")
	private WebElement add_funder_quote;
	
	// cash purchase pending 
	@FindBy(xpath = "//*[contains(text(),'Cash Purchase Pending')]")
	private WebElement status_cash_purchase_pending;
	
	// cash purchase pending 
	@FindBy(xpath = "//*[contains(text(),'Cash Purchase Completed')]")
	private WebElement status_cash_purchase_completed;
	
	
	// cash purchase pending 
	@FindBy(xpath = "//*[contains(text(),'Complete Cash Purchase')]")
	private WebElement complete_cash_purchase;	
	
	// cash purchase pending 
	@FindBy(xpath = "//*[@id='complete-funding-alert']//*[contains(text(),' Confirm')]")
	private WebElement confirm_complete_cash_purchase;
	
	
	@FindBy(xpath = "//*[@class='slider round sliderRed']")
	private WebElement maintenance_toggle_button;
	
	//*[contains(text(),'Status')]//ancestor::div[1]//div//div
	
	//Status of the stocking plan 
	@FindBy(xpath = "//*[contains(text(),'Status')]//ancestor::div[1]//div//div")
	private WebElement status_of_stocking_plan;
	
	//Confirm button on add pop up
	@FindBy(xpath = "//*[@id='confirmBtn']")
	private WebElement confirm_add;
	
	

	Properties prop;

	Actions act;

	AssetFundingPage obj_vehicle_order_tab;

	public AssetFundingPage() {
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

	public boolean open_asset_funding_tab_and_complete_cash_purchase() throws InterruptedException
	{
		Click.on(driver, asset_funding, 30);
				
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		LO.print("Asset Funding Tab Opened");
		System.out.println("Asset Funding Tab Opened");
		
		Click.on(driver, complete_cash_purchase, 30);
		
		Click.on(driver, confirm_complete_cash_purchase, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);	
		
		LO.print("Clicked on Complete Cash purchase");
		System.out.println("Clicked on Complete Cash purchase");		
		
		
		if(!complete_cash_purchase.isEnabled())
		{
			LO.print("Cash Purchase Completed");
			System.out.println("Cash Purchase Completed");
			
			return true;			
		}
		else 
		{
			LO.print("Cash Purchase Not Completed");
			System.err.println("Cash Purchase Not Completed");
						
			return false;
		}
	 
	}
	
	public boolean stocking_plan() throws InterruptedException
	{
		
		Click.on(driver, asset_funding, 30);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);
		
		ExplicitWait.visibleElement(driver, status_stocking_plan, 30);
		
		Click.on(driver, status_stocking_plan, 20);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
		ExplicitWait.waitForListOfVisibleElements(driver, stocking_plan_list, 20);
		
		int noOfStockingPlan =  stocking_plan_list.size();
		
		boolean currentStatus = false;
		
		for(int i=1; i<=noOfStockingPlan; i++)
		{
			
			WebElement nameOfTheStockingPlan = driver.findElement(By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)["+i+"]//h5"));

			
			LO.print          ("Name of the "+i+" Stocking plan is "+nameOfTheStockingPlan.getText());
			System.out.println("Name of the "+i+" Stocking plan is "+nameOfTheStockingPlan.getText());
			
			WebElement statusOfTheStockingPlan = driver.findElement(By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)["+i+"]//*[contains(text(),'Status')]//ancestor::div[1]//div/div"));

			LO.print          ("Status of the "+i+" Stocking plan is "+statusOfTheStockingPlan.getText());
			System.out.println("Status of the "+i+" Stocking plan is "+statusOfTheStockingPlan.getText());
			

			

						
			if(statusOfTheStockingPlan.getText().equalsIgnoreCase("Eligible")==true)
			{

				LO.print          ("Choosing "+i+" Stocking plan for this order");
				System.out.println("Choosing "+i+" Stocking plan for this order");
				
				WebElement addButton = driver.findElement(By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)["+i+"]//*[contains(text(),'Add')]"));

				
				addButton.click();
				
				Thread.sleep(2000);
				
				confirm_add.click();
				
				ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
				
				WebElement statusOfTheStockingPlan2 = driver.findElement(By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)["+i+"]//*[contains(text(),'Status')]//ancestor::div[1]//div/div"));

				
				if(statusOfTheStockingPlan2.getText().equalsIgnoreCase("Pending Activation")==true)
				{
					currentStatus = true;
				}
				
				LO.print          ("Current Status of the "+i+" Stocking plan after adding is "+statusOfTheStockingPlan2.getText());
				System.out.println("Current Status of the "+i+" Stocking plan after adding is "+statusOfTheStockingPlan2.getText());
			
				
				break;
				
			}
		}
		return currentStatus;
	}
	
	
}
