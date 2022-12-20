package com.amt.test;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.HomePage;
import com.amt.pages.LoginPage;
import com.amt.testBase.TestBase;
@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class LoginTest extends TestBase {
	
	public LoginPage obj_Login_Page;
	public HomePage obj_Homepage;
	
	 public static Properties prop;
	
 
	
	@Test(priority=1)
	public void login_Test() throws InterruptedException  
	{
		obj_Login_Page = new LoginPage();
		boolean flag=obj_Login_Page.verify_Login();
		Assert.assertTrue(flag);		
	}
	
	@Test(priority=2)
	public void pageTitle_Test() throws InterruptedException
	{
		obj_Login_Page = new LoginPage();
	    boolean  loginpageTitle=obj_Login_Page.pageTitle_validation();
		Assert.assertTrue(loginpageTitle);
		 
	}
	
	@Test(priority=3)
	public void amt_Logo_display_Test()
	{
		obj_Login_Page = new LoginPage();
		boolean logoBoolean=obj_Login_Page.logoDisplay();
		Assert.assertTrue(logoBoolean);
	}
		

}
