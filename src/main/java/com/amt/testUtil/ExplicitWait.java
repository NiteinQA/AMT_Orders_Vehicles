package com.amt.testUtil;

import java.time.Duration;

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

}
