package com.amt.testUtil;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.amt.testBase.TestBase;

public class JavaScriptExecutor extends TestBase {

	public static void click(WebDriver driver, WebElement element) {
		
		
		ExplicitWait.visibleElement(driver, element, 30);

		HelperClass.highlightElement(driver, element);
		
		try {
			if (element.getText().isBlank()) {
			} else {
				System.out.println("Clicking on " + element.getText());
				LO.print("Clicking on " + element.getText());
			}
		} catch (Exception e) {

		}

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", element);

	}
	
	public static void scroll_down_to_bottom(WebDriver driver) {
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}
	
	public static void scroll_up_to_top(WebDriver driver) {
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0, 0);");

	}

	
	
	
	
	public static void scroll_in_to_view(WebDriver driver, WebElement element) {
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		ExplicitWait.visibleElement(driver, element, 50);

		js.executeScript("arguments[0].scrollIntoView();", element);

	}
	
	

}
