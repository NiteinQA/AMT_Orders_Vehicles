package com.amt.testUtil;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.amt.testBase.TestBase;

public class HelperClass extends TestBase {
		
		public static void highlightElement(WebDriver driver,WebElement element)
		{
			
			JavascriptExecutor js= (JavascriptExecutor)driver;
			js.executeScript("arguments[0].setAttribute('style', 'background : yellow; border: 1px solid red;');",element);
			try {
				
				Thread.sleep(500);
				
				
			}
			
			catch  (InterruptedException e){
				
				System.out.println(e.getMessage());
				
			}
			
			js.executeScript("arguments[0].setAttribute('style',' border: 1px solid white;');",element);
			
			
		}

	}



