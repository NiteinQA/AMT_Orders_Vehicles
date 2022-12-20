package com.amt.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.ExplicitWait;

public class OptionsAccessoriesPage extends TestBase {

	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//*[@id='child2_0016']/div/div[1]/div[1]/label")
	private WebElement paint;  
	
	
	@FindBy(linkText = "Interior")
	private WebElement acq_interior;

	@FindBy(xpath = "//*[@id=\"child2_1014\"]/div[1]/div[1]/div[1]/label")
	private WebElement acq_interior_trim; 
	
	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;
	
	
	public OptionsAccessoriesPage() {
		PageFactory.initElements(driver, this);
	}

	public void options_And_Accessories_selection() throws InterruptedException {
		
		

		LO.print("Paint option has been selected");
		System.out.println("Paint option has been selected");

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);

		js.executeScript("arguments[0].click();", paint);

		

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);


		Click.on(driver, acq_interior, 40);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
		
		js.executeScript("arguments[0].click();", acq_interior_trim);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 20);
			
		LO.print("Interior option has been selected");
			System.out.println("Interior option has been selected");
			
	
		
		
	}

}
