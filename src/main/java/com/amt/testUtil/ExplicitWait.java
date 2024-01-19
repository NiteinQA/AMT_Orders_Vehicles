package com.amt.testUtil;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amt.testBase.TestBase;

public class ExplicitWait extends TestBase {
	
	public static void clickableElement(WebDriver driver, WebElement element , int timeout )
	{
		new WebDriverWait(driver , Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	public static void visibleElement(WebDriver driver, WebElement element , int timeout )
	{
		new WebDriverWait(driver , Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public static void waitTillLoadingIconDisappears(WebDriver driver, List<WebElement> elementList , int timeout) throws InterruptedException
	{
	 
	 try {
   		int count = 0;
   		while(elementList.size()!=0 && count <timeout)
   		{
   		Thread.sleep(1000);
   		count++;
   		}
   		Thread.sleep(2000);
 
   		
   		int count1 = 0;
   		while(elementList.size()!=0 && count1 <timeout)
   		{
   		Thread.sleep(1000);
   		count1++;
   		}
   		Thread.sleep(2000);

   		
   		
   		
   		
	 }
	 catch(Exception e) 
	 {
		
	 }
	
	
	
	
	}
	
	public static void waitForListOfVisibleElements(WebDriver driver, List<WebElement> element , int timeout )
	{
		new WebDriverWait(driver , Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfAllElements(element));
		
	}
	
	
}
