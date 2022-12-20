package com.amt.HoldingCostPages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.ReadExcelCalculation;
import com.amt.testUtil.ReadExcelCalculationForPurchaseAgreement;

public class HoldingCost_CP_CP_Page extends TestBase {
	ReadExcelCalculationForPurchaseAgreement obj_read_excel_calculation_page;
		
	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;
	
	@FindBy(xpath = "//p[contains(text(),'Holding cost')]")
	private WebElement holding_cost;	
	
	@FindBy(xpath = "//span[@class='slider round sliderRed']")
	private WebElement maintenance_toggle_button;
	
	@FindBy(xpath = "//div[@class='acc-head havebtns']")
	private WebElement holding_cost_summary;
	
	@FindBy(xpath = "//div[@class='vehicleDetails ownBookHolding']//div[7]//p[1]")
	private WebElement holding_cost_summary_residual_value_used;
	
	@FindBy(xpath = "//*[@id='ResidualValue']")
	private WebElement holding_cost_summary_residual_value_used_input_field;
	
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/div/div[1]/div/p/strong")
	private WebElement holding_cost_summary_terms;
	
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/div/div[2]/div/p/strong")
	private WebElement holding_cost_summary_mileage;	
	
	@FindBy(xpath = "//*[@id='headingCustomerQuote']/div[2]/div/div[3]/div/p/strong")
	private WebElement total_monthly_holding_cost;
	
	@FindBy(xpath = "//*[@class='slider round sliderRed']")
	private WebElement holding_cost_maintenance_toggle_button;
	
	@FindBy(xpath = "//*[@id='vehicleSummery']/div/div[2]/div[2]/div[6]/div[2]/div[10]/p")
	private WebElement holding_cost_maintenance_cost_used;
	
	@FindBy(xpath = "//*[@id='ResidualPercentage']")
	private WebElement holding_cost_percentage_cap_residual_value_used;		
	
	@FindBy(xpath = "//input[@id='CapMaintenancePercentage']")
	private WebElement percentage_maintenance_cost_used;
	
	@FindBy(xpath = "//input[@id='ResidualValue']")
	private WebElement residual_value_used;
	
	@FindBy(xpath = "//input[@id='Maintenancevalue3']")
	private WebElement maintenance_cost_used;	
	
	@FindBy(xpath = "//*[@id='collapseCustomerQuote']/div/div/div/div/div/form/div/div/div[5]/div/p/strong")
	private WebElement total_cap_maintenance_value;
	
	
	public HoldingCost_CP_CP_Page() {
		PageFactory.initElements(driver, this);
	}
	
	

	public boolean verify_holding_cost_without_maintenance(String sheet_name) throws IOException, InterruptedException {
		Click.on(driver, holding_cost, 30);
		
		LO.print("***********Entered in holding cost page ***********");
		System.out.println("***********Entered in holding cost page ***********");
		Thread.sleep(8000);
		try {
		Click.on(driver, holding_cost_summary, 30);
		}
		catch(Exception e)
		{
			Thread.sleep(4000);
			Click.on(driver, holding_cost_summary, 30);			
		}
		Thread.sleep(3000);
		 LO.print("Clicked on holding cost summary");
		  System.out.println("Clicked on holding cost summary");
		
		obj_read_excel_calculation_page =new ReadExcelCalculationForPurchaseAgreement();
		return obj_read_excel_calculation_page.verify_holding_cost_without_maintenance(driver,
				holding_cost_summary_terms, holding_cost_summary_mileage, 
				holding_cost_summary_residual_value_used, total_monthly_holding_cost, 
				sheet_name);
			}
	
	public boolean verify_holding_cost_without_maintenance_edited(String residual_value_used,String sheet_name) throws IOException, InterruptedException {
		
		Click.on(driver, holding_cost, 30);
		
		LO.print("***********Entered in holding cost page ***********");
		System.out.println("***********Entered in holding cost page ***********");
		Thread.sleep(8000);
		try {
		Click.on(driver, holding_cost_summary, 30);
		}
		catch(Exception e)
		{
			Thread.sleep(4000);
			Click.on(driver, holding_cost_summary, 30);			
		}
		Thread.sleep(3000);
		 LO.print("Clicked on holding cost summary");
		  System.out.println("Clicked on holding cost summary");
		  
		  ExplicitWait.visibleElement(driver, holding_cost_summary_residual_value_used_input_field, 20);
		  
		  System.out.println("residual_value_used from test data "+residual_value_used);
		  holding_cost_summary_residual_value_used_input_field.click();
		  holding_cost_summary_residual_value_used_input_field.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		  Click.sendKeys(driver, holding_cost_summary_residual_value_used_input_field, residual_value_used , 30);
		 Actions act = new Actions(driver);
		 act.sendKeys(Keys.TAB).build().perform();
		 
		 Thread.sleep(5000);
		  
		
		obj_read_excel_calculation_page =new ReadExcelCalculationForPurchaseAgreement();
		return obj_read_excel_calculation_page.verify_holding_cost_without_maintenance(driver,
				holding_cost_summary_terms, holding_cost_summary_mileage, 
				holding_cost_summary_residual_value_used, total_monthly_holding_cost, 
				sheet_name);
			}



	public boolean verify_holding_cost_with_maintenance(String percentage_maintenance_cost_used_from_excel, 
			String residual_value_used_from_excel, String maintenance_cost_used_from_excel,String percentage_cap_residual_value_used,
			String maintenance_required, String target_rental, String sheet_name ) throws IOException, InterruptedException {
		Actions act = new Actions(driver);
		
		Click.on(driver, holding_cost, 30);
		
		  LO.print("***********Entered in holding cost page ***********");
		  System.out.println("***********Entered in holding cost page ***********");
		  Thread.sleep(8000);
			try {
				Click.on(driver, holding_cost_summary, 30);
				}
				catch(Exception e)
				{
					Thread.sleep(4000);
					Click.on(driver, holding_cost_summary, 30);			
				}
		Thread.sleep(3000);
		  LO.print("Clicked on holding cost summary");
		  System.out.println("Clicked on holding cost summary");
		  
		Click.on(driver, holding_cost_maintenance_toggle_button,30);
		
		LO.print("Clicked on holding_cost_maintenance_toggle_button");
		  System.out.println("Clicked on holding_cost_maintenance_toggle_button");
		  percentage_maintenance_cost_used.clear();
		  
		  ExplicitWait.visibleElement(driver, holding_cost_percentage_cap_residual_value_used, 20);
		  ExplicitWait.visibleElement(driver, percentage_maintenance_cost_used, 20);		  
		  ExplicitWait.visibleElement(driver, residual_value_used, 20);
		  
		   
		obj_read_excel_calculation_page =new ReadExcelCalculationForPurchaseAgreement();
		return  obj_read_excel_calculation_page.verify_holding_cost_with_maintenance(driver,
				holding_cost_summary_terms, holding_cost_summary_mileage, 
				holding_cost_summary_residual_value_used, total_monthly_holding_cost, holding_cost_maintenance_cost_used,
				holding_cost_percentage_cap_residual_value_used,total_cap_maintenance_value,  maintenance_required,target_rental,
				residual_value_used_from_excel,maintenance_cost_used_from_excel, percentage_cap_residual_value_used, percentage_maintenance_cost_used_from_excel,
				sheet_name);
		
		
		
		
	}
}
