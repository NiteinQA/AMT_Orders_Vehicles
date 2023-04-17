package com.amt.testUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.amt.testBase.TestBase;

public class Dropdown extends TestBase{
	
 

	public static void select(WebDriver driver, WebElement element, int index,int timeout)
	{
		ExplicitWait.clickableElement(driver, element, timeout);
		Select s = new Select(element);
		s.selectByIndex(index);
	}
	
	public static void selectByVisibleText(WebDriver driver, WebElement element, String visibleText,int timeout)
	{
		ExplicitWait.clickableElement(driver, element, timeout);
		Select s = new Select(element);
		s.selectByVisibleText(visibleText);
	}
}
