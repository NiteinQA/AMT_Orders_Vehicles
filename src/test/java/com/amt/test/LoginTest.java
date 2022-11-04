package com.amt.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.HomePage;
import com.amt.pages.LoginPage;
import com.amt.pages.AcquisitionBrokerPages.AcquisitionQuotesBrokerBCHPage;
import com.amt.testBase.TestBase;
@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class LoginTest extends TestBase {
	
	public LoginPage obj_Login_Page;
	public HomePage obj_Homepage;
	public AcquisitionQuotesBrokerBCHPage obj_aquisition_quotes_page;
	
	/*
	 * public LoginTest() { super(); }
	 */
	 
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		initialization(prop.getProperty("browser"));
		obj_Login_Page = new LoginPage();	
		obj_Login_Page.enter_credentials();
	}
	
	@AfterMethod public void tearDown() { driver.quit(); }
	 
	
	@Test(priority=1)
	public void login_Test() throws InterruptedException  
	{
		
		boolean flag=obj_Login_Page.verify_Login();
		Assert.assertTrue(flag);		
	}
	
	@Test(priority=2)
	public void pageTitle_Test()
	{
		 
		String loginpageTitle=obj_Login_Page.pageTitle_validation();
		Assert.assertEquals(loginpageTitle,"Login-AMT");		
	}
	
	@Test(priority=3)
	public void amt_Logo_display_Test()
	{
		boolean logoBoolean=obj_Login_Page.logoDispaly();
		Assert.assertTrue(logoBoolean);
	}
	
	
	
	

}
