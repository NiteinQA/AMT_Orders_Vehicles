package com.amt.extentreports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;

import com.amt.testBase.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.Markup;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportListener  extends TestBase  implements ITestListener  {
	
	private ExtentHtmlReporter extentReporter;
	private ExtentReports extentReports;
	public static ExtentTest extentTest;
	 public static String failText = "";
	
	//public static WebDriver driver;
	

	@Override
	public void onTestStart(ITestResult result) {
	
	// extentTest = extentReports.startTest("AMT Automation", "Acquisition Module");
		extentTest =extentReports.createTest(result.getMethod().getMethodName());
		 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		 
	//	ExtentTest test = extentReports.startTest("Test Name", "Sample description");
	//	extentTest.log(LogStatus.PASS, "PASS");
		extentTest.pass(result.getName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//extentTest.log(LogStatus.FAIL, "FAIL");

		String feature = result.getMethod().getRealClass().getName() + "  :  " + result.getMethod().getMethodName();
		extentTest.fail(feature);
		
		

		
		if(result.getStatus() == ITestResult.FAILURE){
			
		
		//	extentTest.log(LogStatus.FAIL, "Test Case Failed is         "+result.getName());
		//	extentTest.log(LogStatus.FAIL, "Test Case Failed is         "+result.getThrowable());
//			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
//                        //We do pass the path captured by this mehtod in to the extent reports using "extentTest.addScreenCapture" method. 			
                        String screenshotPath = null;
						try {
							 
							screenshotPath = ExtentReportListener.getScreenshot(driver, result.getName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			//				extentTest.log(Status.FAIL,  extentTest.addScreenCaptureFromPath(screenshotPath));
//			extentTest.log(Status.FAIL, screenshotPath);
			
            try {
				extentTest.fail(result.getClass().getName()).addScreenCaptureFromPath(screenshotPath);
							
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	extentTest.log(LogStatus.INFO, "Snapshot below: " + extentTest.addScreenCapture(screenshotPath));
			
		}else if(result.getStatus() == ITestResult.SKIP){
			
			
			//extentTest.log(LogStatus.SKIP, "Test Case Skipped is    "+result.getName());
			
			 extentTest.log(Status.SKIP, "Test Case Skipped is    "+result.getName());
		}
		
	
		// ending test
		//endTest(extentTest) : It ends the current test and prepares to create HTML report
		
		
		
	}		
		
	

	@Override
	public void onTestSkipped(ITestResult result) {
		
		//extentTest.log(LogStatus.SKIP, "SKIP");
		
		extentTest.skip(result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		 
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		 
	}

	@Override
	public void onStart(ITestContext context) {
		
		
		File file = new File (System.getProperty("user.dir") + "/ExtentReport/ExtentReport.html");	
		extentReporter = new ExtentHtmlReporter(file);
		extentReporter.config().setDocumentTitle("AMT Automation Report");
		extentReporter.config().setReportName("Acquisition");
//		extentReports = new ExtentReports(System.getProperty("user.dir") + "/ExtentReport/ExtentReport.html", false);
		extentReports = new ExtentReports();

		extentReports.attachReporter(extentReporter);
        
		//loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
        //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
			 
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReports.flush();
	
		 
	}	
	
	@AfterSuite
	
	public void over() {
		// TODO Auto-generated method stub
		//extentReports.flush();
	}
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/ExtentReport/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	
	
	

}
