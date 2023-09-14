package com.amt.testUtil;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amt.testBase.TestBase;

public class Click extends TestBase {
	
	public static void on(WebDriver driver, WebElement element , int timeout )
	{
		new WebDriverWait(driver , Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
		
		
      //  HelperClass.highlightElement(driver, element);

		element.click();
//		
//		try {
//			if(element.getText().isBlank()) {}else 
//			{
//		System.out.println("Clicked "+element.getText());
//		LO.print("Clicked "+element.getText());
//			}
//		}
//		catch(Exception e)
//		{
//			
//		}
		
		
	}
	
	public static void doubleClick(WebDriver driver, WebElement element , int timeout )
	{
		new WebDriverWait(driver , Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
		
		
      //  HelperClass.highlightElement(driver, element);

		element.click();
		
		Actions act = new Actions(driver);
		
		act.doubleClick(element);	
		
	}

	
	public static void sendKeys(WebDriver driver, WebElement element ,String values, int timeout)
	{
		new WebDriverWait(driver , Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
		HelperClass.highlightElement(driver, element);
		element.clear();
		
		element.sendKeys(values);
	}
	
	public static void sendKeysint(WebDriver driver, WebElement element ,int d, int timeout)
	{
		new WebDriverWait(driver , Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
		HelperClass.highlightElement(driver, element);	
		element.sendKeys(String.valueOf(d));
	}
	
	public static void sendKeysdouble(WebDriver driver, WebElement element ,double d, int timeout)
	{
		new WebDriverWait(driver , Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
		HelperClass.highlightElement(driver, element); 	
		element.sendKeys(String.valueOf(d));
	}


}
 