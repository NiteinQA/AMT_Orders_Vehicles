package com.amt.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.ExplicitWait;

public class OptionsAccessoriesPage extends TestBase {

	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//*[contains(text(),'Paint -')]//ancestor::div[1]//div/div/div/div/label")
	private WebElement paint;
	
	
	
	@FindBy(xpath = "//input[@value='Ok']")
	private WebElement ok_pop_up;
	
	
	@FindBy(linkText = "Interior")
	private WebElement acq_interior;

	@FindBy(xpath = "//*[contains(text(),'Trim')]//ancestor::div[1]//div/div/div/div/label")
	private WebElement acq_interior_trim; 
	
	@FindBy(xpath = "//*[contains(text(),'Trim')]//ancestor::div[1]//div/div/div/div/label")
	private WebElement acq_interior_trim_used_car; 
	
	@FindBy(xpath = "//*[contains(text(),'Trim')]//ancestor::div[1]//div/div/div/div/label")
	private WebElement acq_interior_trim_used_LCV; 
	
	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;
	
//	@FindBy(xpath = "//*[@id='Rule_Modal']/div/div/div[3]/div/input")
//	private WebElement popup_yes;
	
	
	@FindBy(css = "input[value='Ok']")
	private WebElement popup_yes;
	
	
	
	
	
	public OptionsAccessoriesPage() {
		PageFactory.initElements(driver, this);
	}

	public void options_And_Accessories_selection() throws InterruptedException {
		
		

		LO.print("Paint option has been selected");
		System.out.println("Paint option has been selected");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		
		Thread.sleep(3000);
		


		js.executeScript("arguments[0].click();", paint);		

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		
	    try {
		Click.on(driver, ok_pop_up, 30);
	    }
	    catch(Exception e)
	    {
	    	
	    }
		
		
		
//		try {
//
//			ExplicitWait.clickableElement(driver, paint, 30);
//			js.executeScript("arguments[0].click();", paint);		
//
//			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
//			 
//			Click.on(driver, acq_interior, 40);
//		 
//
//		} catch (Exception e) {
//			
//			try{
//				Click.on(driver, popup_yes, 50);
//				Thread.sleep(2000);	}catch (Exception e1)
//			{
//					
//			}
//			
//
//		}

		


		Click.on(driver, acq_interior, 40);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		
		js.executeScript("arguments[0].click();", acq_interior_trim);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
			
		LO.print("Interior option has been selected");
			System.out.println("Interior option has been selected");
			
	
		
		
	}

	
public void options_And_Accessories_selection_for_used_car() throws InterruptedException {

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		
		Thread.sleep(2000);

		js.executeScript("arguments[0].click();", paint);

		LO.print("Paint option has been selected");
		System.out.println("Paint option has been selected");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);


		Click.on(driver, acq_interior, 40);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		
		js.executeScript("arguments[0].click();", acq_interior_trim_used_LCV);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
			
		LO.print("Interior option has been selected");
			System.out.println("Interior option has been selected");			
		
		
	}

public void options_And_Accessories_selection_for_used_LCV() throws InterruptedException {

	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
	
	Thread.sleep(2000);

	js.executeScript("arguments[0].click();", paint);

	LO.print("Paint option has been selected");
	System.out.println("Paint option has been selected");

	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);


	Click.on(driver, acq_interior, 40);
	
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
	
	js.executeScript("arguments[0].click();", acq_interior_trim_used_LCV);
	
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		
	LO.print("Interior option has been selected");
		System.out.println("Interior option has been selected");			
	
	
}


}
