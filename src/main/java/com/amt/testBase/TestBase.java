package com.amt.testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.amt.pages.LoginPage;
import com.amt.testUtil.Logger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static Logger LO;
	LoginPage obj_Login_Page;
	

	ExtentReports extent;
	// helps to generate the logs in the test report.
	ExtentTest test;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"D:\\Acquisition\\AMT_Automation\\src\\main\\java\\configs\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Acquisition
	
	public static void initialization(String browser) throws InterruptedException {

		if (browser.equalsIgnoreCase("chrome")) {

//		System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("force-device-scale-factor=0.67");
			options.addArguments("high-dpi-support=0.67");
			options.addArguments("disable-infobars");

			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		// driver.manage().deleteAllCookies();
		// driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));

		driver.get(prop.getProperty("url"));
	}

	@BeforeClass
	public void setup() throws InterruptedException, IOException {

		prop = new Properties();
		FileInputStream ip = new FileInputStream(
				"D:\\Acquisition\\AMT_Automation\\src\\main\\java\\configs\\config.properties");
		prop.load(ip);

		initialization(prop.getProperty("browser"));

		obj_Login_Page = new LoginPage();
		obj_Login_Page.enter_credentials();

	}



//	@AfterClass
	public void tearDown() {
		driver.close();
	}



}
