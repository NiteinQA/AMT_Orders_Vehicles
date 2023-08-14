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

}
