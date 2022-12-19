package com.amt.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.Dropdown;

public class Leads extends TestBase {
	
	JavascriptExecutor js;
	
	@FindBy(xpath = "//*[contains(text(),'Leads')]")
	private WebElement leads;
	
	
	@FindBy(xpath = "//button[@title='Add Lead']")
	private WebElement add_lead;
	
	
	@FindBy(xpath = "//select[@id='SourceId']")
	private WebElement general_lead_source;
	
	@FindBy(xpath = "//select[@id='FormTypeId']")
	private WebElement general_entry_type;
	
	@FindBy(xpath = "//select[@id='StatusId']")
	private WebElement status;
	
	@FindBy(xpath = "//div[@class='form-group norequired col-md-12 col-lg-4 ng-star-inserted']//input[@id='customer']")
	private WebElement general_assigned_to;
	
	
	@FindBy(xpath = "//ul[@class='user-dd-list shadow ng-star-inserted']//li[@class='ng-star-inserted']")
	private WebElement general_assigned_to_option;
		
	@FindBy(xpath = "//select[@id='CustomerTypeId']")
	private WebElement customer_type;	
	
	@FindBy(xpath = "//div[@class='form-group only-inputgroup input norequired col-md-12 col-lg-4']//input[@id='customer']")
	private WebElement customer_name;	
	
	@FindBy(xpath = "//ul[@class='user-dd-list shadow ng-star-inserted']//li[@class='ng-star-inserted']")
	private WebElement customer_name_option;
	
	@FindBy(xpath = "//button[normalize-space()='Add new vehicle request']")
	private WebElement add_new_vehicle_request;
	
	
	@FindBy(xpath = "//*[@id='vehiclinfo2']/div/div/div/div[1]/div/ng-multiselect-dropdown/div/div[1]/span/span[2]")
	private WebElement channel;
	
	@FindBy(xpath = "//*[@class='item2']")
	private WebElement channel_dropdown_list;
	
	
	@FindBy(xpath = "//*[@id='vehiclinfo2']/div/div/div/div[1]/div/ng-multiselect-dropdown/div/div[2]/ul[2]/li[1]/div")
	private WebElement broker_channel;
	
	@FindBy(xpath = "(//*[contains(text(),' Save ')])[3]")
	private WebElement save_vehicle_request;
	
	
	@FindBy(xpath = "//*[@id='enteredLog']")
	private WebElement add_note;
	
	
	@FindBy(xpath = "//*[contains(text(),'Post ')]")
	private WebElement post;
	
	@FindBy(xpath = "//*[@title='Map New Quote']")
	private WebElement map_new_quote;
	
	@FindBy(xpath = "(//*[contains(text(),'Ok')])[4]")
	private WebElement ok_button;
	
	@FindBy(xpath = "//button[normalize-space()='Save & Exit']")
	private WebElement save_and_exit_button;
	
	
	@FindBy(xpath = "//button[normalize-space()='Save & Convert']")
	private WebElement save_and_convert_button;
	
	
	@FindBy(xpath = "//*[@id='cWraper']/div/app-add-opportunities/div/div/div/div/form/div[1]/div[1]/div/div[2]/div[2]")
	private WebElement opportunity_ref_no;
	
		
	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;
	

	
	public Leads() {
		PageFactory.initElements(driver, this);
		
		try
    	{
    		prop=new Properties();
    		FileInputStream ip = new FileInputStream("D:\\StagingNew\\AMT_Automation\\src\\main\\java\\configs\\excelValues.properties");
    		prop.load(ip);                            
    	}
    	catch(FileNotFoundException e)
    	{
    		e.printStackTrace();
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
		
    	}
	
	public void add_new_lead(String channelOptions) throws InterruptedException, IOException
	{
		Thread.sleep(3000);
		
		Click.on(driver, leads, 30);
		
   		LO.print("Clicked on Leads page");
   		System.out.println("Clicked on Leads page");
		
		ExplicitWait.visibleElement(driver, add_lead, 40);
		
		Click.on(driver, add_lead, 30);
		
   		LO.print("Clicked on add lead");
   		System.out.println("Clicked on add lead");
		
		Thread.sleep(2000);
		
		Dropdown.selectByVisibleText(driver, general_lead_source, " Instagram ", 30);
		
		Dropdown.selectByVisibleText(driver, general_entry_type, " Lease ", 30);
		
		Dropdown.selectByVisibleText(driver, status, " New/Open ", 30);
		
		Click.sendKeys(driver, general_assigned_to, "QA Sales", 30);
		
   		LO.print("Lead is assigned");
   		System.out.println("Lead is assigned");

		Thread.sleep(2000);
		
		Click.on(driver, general_assigned_to_option, 30);
		
		Dropdown.selectByVisibleText(driver, customer_type, " Individual ", 30);
		
   		LO.print("Customer type - "+"Indivisual"+" selected");
   		System.out.println("Customer type - "+"Indivisual"+" selected");
		
		 
   		
   		js = (JavascriptExecutor)driver;
        
   		
   		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);	
   		  
   		Click.sendKeys(driver, customer_name, "QA ", 30);   		
	    
	    
	    Thread.sleep(4000);
	    
	    Click.on(driver, customer_name_option, 20);
	    
	    Click.on(driver, add_new_vehicle_request, 30);
	    
   		LO.print("Clicked on Add new vehicle request");
   		System.out.println("Clicked on Add new vehicle request");
	    
	    
   		
   		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);	
   			
 	    Click.on(driver, channel, 30); 
   			    
	    Thread.sleep(2000);
	    
	    
	    
	    WebElement ele =driver.findElement(By.xpath("(//*[@class='item2']//*[contains(text(),'Broker')])[1]"));
	    
	    js.executeScript("arguments[0].click();", ele);   
	    
   		LO.print("Channel type Broker has been selected");
   		System.out.println("Channel type Broker has been selected");
    
	    
	    Click.on(driver, channel, 30); 
	    
	    Click.on(driver, save_vehicle_request, 30);
	    
	    Click.sendKeys(driver, add_note, "Test Note", 30);
	    
	    Click.on(driver, post, 30);
	    
	    
	    Click.on(driver, map_new_quote, 30);
	    
   		LO.print("Clicked on Map new quote");
   		System.out.println("Clicked on Map new quote");
	    
   		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
   		
   		
   		
	    
	    FileInputStream fis = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheet("BrokerPCHQuoteNo");// selecting sheet with its name as a parameter
		
		
		XSSFRow row = sheet.getRow(0);// read data from first row as 0th row contains header
		XSSFCell cell = row.getCell(0);// read data from first cell
	    
		String quote_no=cell.getStringCellValue();
		
		
		
	   List<WebElement> table_data = driver.findElements(By.xpath("(//tbody)[3]/tr/td"));
	    
	   for(int i=0; i<=table_data.size()-1;i++) 
	   {
		   		   
		   if(table_data.get(i).getText().equals(quote_no))
		   {
			   ExplicitWait.clickableElement(driver, table_data.get(i-1), 30);
		       table_data.get(i-1).click();
		   }
	   }
	   
  		LO.print("New Quote has been mapped with lead");
  		System.out.println("New Quote has been mapped with lead");
	   
	   Click.on(driver, ok_button, 30);
	   
	   Thread.sleep(7000);
	   
	   ExplicitWait.clickableElement(driver, save_and_convert_button, 30);
	    
	   js.executeScript("arguments[0].click();", save_and_convert_button);
	   
 		LO.print("Clicked on save and convert button");
 		System.out.println("Clicked on save and convert button");
 		
 		
 		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
 		
 		ExplicitWait.visibleElement(driver, opportunity_ref_no, 30);
 		
 		String opportunityRefNo = opportunity_ref_no.getText();
 		
 		
 		System.out.println(opportunityRefNo);
 		

 		
 		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
 		
		XSSFWorkbook wb = new XSSFWorkbook(in);
		
		wb.getSheet("BrokerPCHQuoteNo").createRow(0).createCell(1).setCellValue(opportunityRefNo);
		
		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		
		wb.write(out);
		
		out.close();
		
 		LO.print("Lead converted to opportuninty successfully");
 		System.out.println("Lead converted to opportuninty successfully"); 		
 		
	   	
	}

}


//
//List<WebElement> channel_options = driver.findElements(By.xpath("//*[@id='vehiclinfo2']/div/div/div/div[1]/div/ng-multiselect-dropdown/div/div[2]/ul[2]"));
//	
//for(int i=0; i<=channel_options.size()-1;i++)
//{
//
//System.out.println(channel_options.get(i).getText());
//    	
//String temp_channel_option =channel_options.get(i).getText();
//
//Thread.sleep(5000);
//
//System.out.println(temp_channel_option.equals(channelOptions));
//
//	
//
//if(temp_channel_option.equals(channelOptions)) 
//{	
//	ExplicitWait.clickableElement(driver, channel_options.get(i), 30);
//	channel_options.get(i).click();
//	System.out.println("option selected");
//	break;	    		
//}
//}
