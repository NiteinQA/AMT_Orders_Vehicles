package com.amt.testUtil;

import org.openqa.selenium.WebElement;

import com.amt.testBase.TestBase;

public class CommonClass extends  TestBase {
	
	public String validate_selected_order(WebElement element) {
		ExplicitWait.visibleElement(driver, element, 60);
		
		return element.getText().trim();
	}
	

	
	

}
