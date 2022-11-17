package com.amt.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;

public class OptionsAccessoriesPage extends TestBase {

	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//*[@id='child2_0016']/div/div[1]/div[1]/label")
	private WebElement paint;  
	//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-detail[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/app-aquisition-optional-extras[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/label[1]
	
	
	@FindBy(linkText = "Interior")
	private WebElement acq_interior;

	@FindBy(xpath = "//*[@id=\"child2_1014\"]/div[1]/div[1]/div[1]/label")
	private WebElement acq_interior_trim; 
	
	//body/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-aquisition-detail[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/app-aquisition-optional-extras[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]
	
	public OptionsAccessoriesPage() {
		PageFactory.initElements(driver, this);
	}

	public void options_And_Accessories_selection() throws InterruptedException {
		
		

		LO.print("Paint option has been selected");
		System.out.println("Paint option has been selected");

		Thread.sleep(8000);

		js.executeScript("arguments[0].click();", paint);

		

		Thread.sleep(5000);

		// HelperClass.highlightElement(driver, acq_interior);
		try {
			Click.on(driver, acq_interior, 40);
			js.executeScript("arguments[0].click();", acq_interior_trim);
			
			LO.print("Interior option has been selected");
			System.out.println("Interior option has been selected");
			
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(5000);

		
	}

}
