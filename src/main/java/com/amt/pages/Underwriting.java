package com.amt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.HelperClass;

public class Underwriting extends TestBase {

	JavascriptExecutor js;


	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	
	@FindBy(xpath = "//i[@class='icon-opportunity']")
	private WebElement opportunities;

	//1.underwriting_menu_link
    @FindBy(xpath = "//span[contains(text(),'Underwriting')]")
	private WebElement underwriting_menu_link;
	
    
  //2a.underwriting_menu_link_broker;
  	@FindBy(xpath = "/html/body/app-root/div[1]/div[2]/div[1]/div/app-sidemenu/div/div[11]/ul/li[1]/a/span")
  	private WebElement underwriting_menu_link_broker;
  
  	
  	//3.underwriting_proposal_id
  	
	 @FindBy(xpath = "//*[@id='cWraper']/div/app-underwriting-management/div[2]/div/div/app-uw-listing/div[1]/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr[1]/td[1]")
private WebElement underwriting_proposal_id;

	 
	//4.underwriting_seach_text_box;
		
		
@FindBy(xpath = "//input[@placeholder='Search something here']")
private WebElement underwriting_Seach_text_box;




@FindBy(xpath = "//*[@id='cWraper']/div/app-underwriting-management/div[2]/div/div/app-uw-listing/div[1]/app-grid/div[2]/div/div[2]/div[1]/table/tbody/tr")
private WebElement proposal_detail_listing_data;



	@FindBy(xpath = "//p[contains(text(),'Quote')]")
private WebElement underwriting_tab_quote;


@FindBy(xpath = "//p[contains(text(),'Credit File')]")
private WebElement underwriting_tab_creditfile;


   @FindBy(xpath = "//p[contains(text(),'Documents')]")
private WebElement underwriting_tab_document;


   
  
   
   @FindBy(xpath = "(//p[normalize-space()='Decision'])[1]")
   private WebElement decision_tab_document;

   

//Accept_button
  @FindBy(xpath = "//button[normalize-space()='Accept']")
private WebElement Accept_button;

// Upload 
  @FindBy(xpath = "//button[normalize-space()='Upload']")
private WebElement accept_upload_button;


  
	
	// View Upload 
	  @FindBy(xpath = "//*[@src='/assets/images/view.svg']")
	private WebElement desicion_upload_view;
	
	
	// view close
	  
		
		  
		  @FindBy(xpath = "//*[@id='FileView']//*[@class='close']")
			private WebElement desicion_upload_view_close;
			
	  
	  
		// Save and exit 
	  @FindBy(xpath = "//button[normalize-space()='Save & Exit']")
	private WebElement desicion_save_exit_button;
	
	
	  
	  // Con - Yes button
	  
	  
	  @FindBy(xpath = "//*[@id='decisionConfirmation']//button[contains(text(),'Yes')]")
		private WebElement desicion_save_exit_button_confirm_yes;
	  
	  

		
		
	  
	  

	































Properties prop;

	public Underwriting() 
	{
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
	PageFactory.initElements(driver, this);
}	


		public void verify_underwriting_menulink()
		{
		
		ExplicitWait.visibleElement(driver, underwriting_menu_link, 20);
		
		HelperClass.highlightElement(driver, underwriting_menu_link);
		
		
		underwriting_menu_link.click();
		
		System.out.println("Click on Underwriting menu_link "); 
		LO.print("Click on Underwriting menu_link ");

		
		}
		
		
		
		public void verify_underwriting_menulink_broker() throws InterruptedException
		{
		
			ExplicitWait.visibleElement(driver, underwriting_menu_link_broker, 20);
			
			HelperClass.highlightElement(driver, underwriting_menu_link_broker);
			
			underwriting_menu_link_broker.click();
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
			
			System.out.println("Click on Underwriting - broker tab "); 
			LO.print("Click on Underwriting - broker tab " );
		
		}
		
		
		/*
		 * public String verify_underwriting_proposal_id() throws InterruptedException {
		 * 
		 * ExplicitWait.visibleElement(driver, underwriting_proposal_id, 20);
		 * 
		 * HelperClass.highlightElement(driver, underwriting_proposal_id);
		 * 
		 * String proposalID = underwriting_proposal_id.getText(); return proposalID;
		 * 
		 * 
		 * 
		 * 
		 * }
		 */

		
		
		
		
		
		public void verify_underwriting_proposal_search_text_box() throws InterruptedException, IOException
		{
		
			ExplicitWait.visibleElement(driver, underwriting_Seach_text_box, 20);
			
			HelperClass.highlightElement(driver, underwriting_Seach_text_box);
			System.out.println("Click on Search text box - broker tab"); 
			LO.print("Click on Search text box - broker tab" );
			Thread.sleep(6000);
			
			
		
			
		FileInputStream fis = new FileInputStream(prop.getProperty("quote_save_excel_path"));
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheet("BrokerBCHQuoteNo");// selecting sheet with its name as a parameter
		Thread.sleep(1000);
		//System.out.println("Taking the sheet name is  =" + fis); 
		System.out.println("Taking the sheet name is  =" + sheet.getSheetName()); 
		
		XSSFRow row = sheet.getRow(0);// read data from first row as 0th row contains header
		XSSFCell cell = row.getCell(1);// read data from first cell
		
       // System.out.println(cell);
System.out.println(sheet.getRow(0).getCell(1));
Thread.sleep(1000);
String UnderwritingPrposalId = cell.getStringCellValue();

System.out.println("Getting the  proposal id from excel sheet  =" + UnderwritingPrposalId); 
LO.print("Getting the  proposal id from excel sheet =" + UnderwritingPrposalId);

Thread.sleep(1000);
//System.out.println(cellval);
		
		//String UnderwritingPrposalId =cell.getStringCellValue();
		
		//System.out.println("proposal id before send to search text box   =" + UnderwritingPrposalId); 
			
			//Obj_Underwriting_Page = new	Underwriting();
			
ExplicitWait.visibleElement(driver, underwriting_Seach_text_box, 20);			

			Click.sendKeys(driver, underwriting_Seach_text_box, UnderwritingPrposalId, 20);
			
			System.out.println("Enter the proposal in search text box =" + UnderwritingPrposalId); 
			LO.print("Enter the proposal in search text box =" + UnderwritingPrposalId);
			
			
			
			
		
			
			
			underwriting_Seach_text_box.sendKeys(Keys.ENTER);
	
		
			
		
		}
		
		
		



		
		
		
		
		

		
		public void find_underwriting_listing_detail_for_proposal() throws InterruptedException
			{
			
			ExplicitWait.visibleElement(driver, proposal_detail_listing_data, 20);
			
			HelperClass.highlightElement(driver, proposal_detail_listing_data);
			
			Thread.sleep(2000);
			
			
			Actions actions = new Actions(driver);
	
			actions.doubleClick(proposal_detail_listing_data).perform();
			
			
			
		//	Click.on(driver, proposal_detail_listing_data, 30);
			
			
			
			System.out.println("Click on Underwriting detail page for proposal"); 
			LO.print("Click on Underwriting detail page for proposal");

			
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
			
			}
			
			
			
			public void find_underwriting_tab_quote_page() throws InterruptedException
			{
				
				Thread.sleep(7000);
			
			ExplicitWait.visibleElement(driver, underwriting_tab_quote, 30);
			
			//HelperClass.highlightElement(driver, underwriting_tab_quote);
			
			Click.on(driver, underwriting_tab_quote, 30);
		
			
			System.out.println("Click on Underwriting detail page for proposal"); 
			LO.print("Click on Underwriting detail page for proposal");

			
			}
			
			
			
			public void find_underwriting_tab_creditfile_page() throws InterruptedException
			{
			
				Thread.sleep(4000);
			ExplicitWait.visibleElement(driver, underwriting_tab_creditfile, 30);
			
			HelperClass.highlightElement(driver, underwriting_tab_creditfile);
			
			Click.on(driver, underwriting_tab_creditfile, 30);

			
			System.out.println("Click on credit file tab page "); 
			LO.print("Click on credit file page ");
			

			
		}
		


		
			public void find_underwriting_tab_document_page() throws InterruptedException
			{
				Thread.sleep(4000);
				
			
			ExplicitWait.visibleElement(driver, underwriting_tab_document, 30);
			
			HelperClass.highlightElement(driver, underwriting_tab_document);
			
			Click.on(driver, underwriting_tab_document, 30);

			
			System.out.println("Click on document file tab page "); 
			LO.print("Click on document file page ");
			

			
		}
		
		
		public void find_underwriting_tab_decision_page() throws InterruptedException
			{
			Thread.sleep(4000);
			
			ExplicitWait.visibleElement(driver, decision_tab_document, 20);
			
			HelperClass.highlightElement(driver, decision_tab_document);
			
			Click.on(driver, decision_tab_document, 30);
		
			
			System.out.println("Click on decision tab page"); 
			LO.print("Click on decision tab page");
			

			
		}
		
		public void find_underwriting_tab_decision_page_accept_button() throws InterruptedException
			{
			
			Thread.sleep(4000);
			ExplicitWait.visibleElement(driver, Accept_button, 20);
			
			HelperClass.highlightElement(driver, Accept_button);
			
			Click.on(driver, Accept_button, 30);
	
			
			System.out.println("Click on decision tab Accept button "); 
			LO.print("Click on decision Accept button");
			

			
		}
		
		
		public void find_underwriting_tab_decision_page_accept_upload() throws InterruptedException, AWTException
			{
			Thread.sleep(4000);
			
			ExplicitWait.visibleElement(driver, accept_upload_button, 20);
			
			HelperClass.highlightElement(driver, accept_upload_button);
			
			Click.on(driver, accept_upload_button, 30);
			
			Thread.sleep(4000);

			 Robot rb = new Robot();
			 
			    // copying File path to Clipboard
			    StringSelection str = new StringSelection("C:\\Users\\mehul.nagar\\Desktop\\test 2 .pdf");
			    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
			 
			     // press Contol+V for pasting
			     rb.keyPress(KeyEvent.VK_CONTROL);
			     rb.keyPress(KeyEvent.VK_V);
			 
			    // release Contol+V for pasting
			    rb.keyRelease(KeyEvent.VK_CONTROL);
			    rb.keyRelease(KeyEvent.VK_V);
			 
			    // for pressing and releasing Enter
			    rb.keyPress(KeyEvent.VK_ENTER);
			    rb.keyRelease(KeyEvent.VK_ENTER);
			    
				System.out.println("File is Uploaded Successfully");
			   }
			
	
	

		

		
		

		
		
		
		
		public void verification_underwriting_tab_decision_page_view_icon() throws InterruptedException
			{
			
			ExplicitWait.visibleElement(driver, desicion_upload_view, 30);
			
			HelperClass.highlightElement(driver, desicion_upload_view);
			
			
			Thread.sleep(4000);
			Click.on(driver, desicion_upload_view, 30);
			
			Thread.sleep(4000);
			
		System.out.println("click on view icon ");

		
	
		
		
		Click.on(driver, desicion_upload_view_close, 30); 
		//*[@id="FileView"]//*[@class="close"]
		
		Thread.sleep(4000);
		System.out.println("click on view icon close icon ");
		
		}
		
		
		
		public void verification_underwriting_tab_decision_page_saveandexit_button() throws InterruptedException
			{
			
			ExplicitWait.visibleElement(driver, desicion_save_exit_button, 30);
			
			HelperClass.highlightElement(driver, desicion_save_exit_button);
			
			
			Click.on(driver, desicion_save_exit_button, 30);
			
			
			System.out.println("Click on save and exit button");
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);

			
			// Yes button on confirmation  
			
			
			
			Click.on(driver, desicion_save_exit_button_confirm_yes, 30);
			
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 30);
			
			
			
		}
		
		
		
		
		
		
		
		
		
			
		}
		
		
		
	
		
		
		
		
		
		

	

	

	

	