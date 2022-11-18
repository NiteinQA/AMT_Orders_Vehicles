package com.amt.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.HelperClass;

public class LoginPage extends TestBase {

	@FindBy(xpath="//input[@placeholder='Email']") 
	private WebElement email;
	
	@FindBy(xpath="//input[@placeholder='Password']") 
	private WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement submit;
	
	@FindBy(xpath="//body[1]/app-root[1]/app-header[1]/div[1]/div[1]/div[1]/a[1]/div[1]")
	private WebElement logoImage;
	
	@FindBy(xpath="//a[@id='dropdownMenuButton']")
	private WebElement profile_icon;
	
	
	public LoginPage(){
		 
		PageFactory.initElements(driver, this);		
		}
	
	
	
 
	public void enter_credentials() throws InterruptedException {
		
		Thread.sleep(2000);
		
		HelperClass.highlightElement(driver,email);
		email.sendKeys(prop.getProperty("username"));			
		
		HelperClass.highlightElement(driver,password);
		password.sendKeys(prop.getProperty("password"));
		
		
		submit.click();
		 
	}
	
	public boolean pageTitle_validation() throws InterruptedException
	{
		
		Thread.sleep(5000);
		String login_Page_Tittle=driver.getTitle();
		LO.print("Page Title After login is "+login_Page_Tittle);
        
		
		boolean flag = false;
		if(login_Page_Tittle.contains("AMT"))
		{
			flag = true;
		}
		return flag;
		
	}
	
	public boolean logoDisplay()
	{
		ExplicitWait.visibleElement(driver, logoImage, 30);
		boolean flag=logoImage.isDisplayed();
		LO.print("AMT-Logo is displayed");
		System.out.println("logo display boolean "+flag);
		return flag;
		
	}
	
	public boolean verify_Login() {
		LO.print("Login screen is being displayed");
		LO.print("User has entered Username ");
		LO.print("User has entered Password ");
		
		ExplicitWait.visibleElement(driver, profile_icon, 50);
		
		
		return profile_icon.isDisplayed();
	}




	
}
