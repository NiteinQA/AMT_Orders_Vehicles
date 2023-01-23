package com.amt.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.HelperClass;
import com.amt.testUtil.Dropdown;

public class UnderwritingPopupPage extends TestBase {
	
	JavascriptExecutor js;
	
Properties prop;
	//1.underwriting_menu_link
    @FindBy(xpath = "//span[contains(text(),'Underwriting')]")
	private WebElement underwriting_menu_link;
	
	
	
	
	//Underwriting popup code --

	    //1.underwriting_icon_link
	    @FindBy(xpath = "(//img[@src='../../assets/images/opportunity/underwritting.svg'])[1]")
		private WebElement underwriting_icon_link;
		
		
	   //2.underwriting_popup_send_for_underwriting_button
		@FindBy(xpath = "(//button[normalize-space()='Send for underwriting'])[1]")
		private WebElement underwriting_send_for_underwriting_button;
		

	   //3.underwriting_popup_proposal_id
		@FindBy(xpath = "//*[@id='underwriting_popup']/div/div/div[1]/h4")
		private WebElement underwriting_popup_proposal_id;
		
		
	  //4.underwriting_popup_quote_ref_no
		@FindBy(xpath = "//*[normalize-space()='Quote reference no.:']//ancestor::div[1]")
		private WebElement underwriting_popup_quote_ref_no;
		

		//5.underwriting_popup_cancel_button
		@FindBy(xpath = "(//button[@class='btn btn-outline-secondary mr-2'][normalize-space()='Cancel'])[3]")
		private WebElement underwriting_popup_cancel_button;
	
		@FindBy(xpath = "//img[@alt='Loading...']")
		private List<WebElement> loading_icon;
	
		//6.underwriting_popup_download_contract_file
		@FindBy(xpath = "(//i[@class='btn-icon-contract-file'])[1]")
		private WebElement underwriting_popup_download_contract_file;
		
		
		//7.underwriting_popup_download_proposal
		@FindBy(xpath = "(//button[normalize-space()='Download Proposal'])[1]")
		private WebElement underwriting_popup_download_proposal;
		
		
		
	
	
	
	
	
	public UnderwritingPopupPage() {
		
	
	    	try
	    	{
	    		prop=new Properties();
	    		FileInputStream ip = new FileInputStream("D:\\newWorkspaceStaging\\AutomationStaging\\src\\main\\java\\configs\\excelValues.properties");
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
	    	PageFactory.initElements(driver, this);
	    }
		
		
    	
    		
		
    	
	
	
	
		/*
		 * public void underwriting_menu_link_availbale () throws InterruptedException {
		 * 
		 * ExplicitWait.visibleElement(driver, underwriting_menu_link, 30);
		 * 
		 * 
		 * 
		 * underwriting_menu_link.click();
		 * System.out.println("Click on  underwriting icon is available");
		 * LO.print("Click on  underwriting icon is available ");
		 * 
		 * ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		 * 
		 * 
		 * 
		 * 
		 * }
		 */
	
	
	
	
	
	
	
	public void search_and_verify_underwriting_icon_is_availabale ()
	{
		
   		ExplicitWait.visibleElement(driver, underwriting_icon_link, 30);
   		
   		if(underwriting_icon_link.isEnabled())
   		{
   			
   			underwriting_icon_link.click();
   		}
   		System.out.println("Underwriting icon is available"); 
   		LO.print(" Underwriting icon is available ");
		
   			
   		}
   		
   		
   		

   	
	
	//3.underwriting_popup_proposal_id


public void search_and_verify_underwriting_get_proposal_id() throws InterruptedException, IOException
{
	
	
	ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
	ExplicitWait.visibleElement(driver, underwriting_popup_proposal_id, 30);
		
	Thread.sleep(2000);
	String UnderwritingPopupProposalId= underwriting_popup_proposal_id.getText();
	Thread.sleep(2000);
	String UnderwritingPopupProposalIdFromScreen= UnderwritingPopupProposalId.substring(14, 21);
	Thread.sleep(2000);
	System.out.println("Underwriting Popup Proposal Id is =" +UnderwritingPopupProposalIdFromScreen); 
		      LO.print("Underwriting Popup Proposal Id is =" +UnderwritingPopupProposalIdFromScreen);
		
		
		Thread.sleep(1000);
		FileInputStream in = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		XSSFWorkbook wb = new XSSFWorkbook(in);
		
		Thread.sleep(1000);
		wb.getSheet("BrokerBCHQuoteNo").getRow(0).getCell(1).setCellValue(UnderwritingPopupProposalIdFromScreen);
		//wb.getSheet("BrokerBCHQuoteNo").getRow(0).getCell(2).setCellValue(UnderwritingPopupProposalId);
		Thread.sleep(1000);
		Thread.sleep(1000);
		FileOutputStream out = new FileOutputStream(prop.getProperty("quote_save_excel_path"));
		Thread.sleep(1000);
		wb.write(out);
		

		
		

}









	public void search_and_verify_underwriting_send_for_underwriting_button() throws InterruptedException
	{
		
		ExplicitWait.visibleElement(driver, underwriting_send_for_underwriting_button, 30);
   		underwriting_send_for_underwriting_button.click();
   		
   		System.out.println("Click on Send for Underwriting button"); 
		LO.print("Click on Send for Underwriting button");
	
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
	}
	
	
	
	public void search_and_verify_underwriting_get_quote_no()
	
	{
		
		ExplicitWait.visibleElement(driver, underwriting_popup_quote_ref_no, 30);
   		
		String underwriting_quote_ref_no = underwriting_popup_quote_ref_no.getText();
		
		
		System.out.println("Underwriting quote ref no =>" + underwriting_quote_ref_no);
		LO.print("Underwriting quote ref no => " + underwriting_quote_ref_no );
		
	}
	
	

	
	
	
	public void search_and_verify_underwriting_download_contract_file () throws InterruptedException
	{
		
		ExplicitWait.visibleElement(driver, underwriting_popup_download_contract_file, 30);
   		
		underwriting_popup_download_contract_file.click();
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		System.out.println("Click on underwriting pop up download contract file");
		LO.print("Click on underwriting pop up download contract file ");
		
	}
	
	
	
	public void search_and_verify_underwriting_download_proposal () throws InterruptedException
	{
		
		ExplicitWait.visibleElement(driver, underwriting_popup_download_proposal, 30);
   		
		underwriting_popup_download_proposal.click();
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		System.out.println("Click on underwriting pop up download proposal");
		LO.print("Click on underwriting pop up download proposal ");
		
		ArrayList<String> browserwindow = new ArrayList<String>(driver.getWindowHandles());
	      //switch to active tab
	      driver.switchTo().window(browserwindow.get(1));
	      
	      System.out.println("Page title of active tab: " + driver.getTitle());
	      LO.print("Page title of active tab: " + driver.getTitle());
	      //switch to parent
	      //driver.wait(2000);
	      driver.switchTo().window(browserwindow.get(0));
	      System.out.println("Page title of parent window: " + driver.getTitle());
	      LO.print("Page title of parent window: " + driver.getTitle());
	  //    driver.quit();

		
	}
	
	
	
	
	
	public void verify_underwriting_cancel_button ()
	{
		
		ExplicitWait.visibleElement(driver, underwriting_popup_cancel_button, 30);
   		
		underwriting_popup_cancel_button.click();
		
		
		System.out.println("click on underwriting pop up cancel button");
		LO.print("click on underwriting pop up cancel button ");
	}
	
	
	
	public void verify_send_for_underwriting_button () throws InterruptedException
	{
		
		ExplicitWait.visibleElement(driver, underwriting_send_for_underwriting_button, 30);
   		
		underwriting_send_for_underwriting_button.click();
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
		
		System.out.println("click on underwriting_send_for_underwriting  button");
		LO.print("click on underwriting_send_for_underwriting  button ");
	}
	
	
	
	
	
		
	
	
	
	

}		 
		
		