package com.amt.QuoteSummaryPages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.Difference;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;
import com.amt.testUtil.ReadExcelCalculation;
import com.amt.testUtil.RemoveComma;

public class QuoteSummaryOutrightPCHPage extends TestBase {
	
	ReadExcelCalculation obj_read_excel_calculation_page; 
	
	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//p[normalize-space()='Quote summary']")
	private WebElement quote_summary;
	
	@FindBy(xpath = "//*[@class='right-fix vechile-summery']/div/div[2]/div[2]/div[4]/span[2]")
	private WebElement quote_summary_ref_no;
	
	@FindBy(xpath = "//*[@class='row']//*[@id='headingTwo']/div/div/div[4]/div/p/strong")
	private WebElement quote_summary_cost_otr_price;
	
//	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-summary-quote[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[7]/div[1]/div[1]/p[1]/strong[1]")
//	private WebElement quote_summary_total_monthly_holding_cost;
	
	@FindBy(xpath = "//*[@class='row']//*[@id='headingHoldingCost']/div/div[8]/div/div/p/strong")
	private WebElement quote_summary_total_monthly_holding_cost;
	
	
	@FindBy(xpath = "//div[@id='headingHoldingCost']//div[7]//div[1]//div[1]//p[1]//strong[1]")
	private WebElement quote_summary_total_monthly_holding_cost_without_maintenance;
	
	@FindBy(xpath = "//*[@class='row']//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[4]/div/p/strong")
	private WebElement quote_summary_monthly_finance_rental;
	                 
//	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-summary-quote[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/app-acquisition-quote-summary[1]/div[1]/div[2]/app-hire-customer-quote-summary-header[1]/div[1]/div[5]/div[1]/p[1]/strong[1]")
//	private WebElement quote_summary_monthly_maintenance_rental;
	
	@FindBy(xpath = "//*[@class='row']//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[5]/div/p/strong")
	private WebElement quote_summary_monthly_maintenance_rental;
	
//	@FindBy(xpath = "//body[1]/app-root[1]/div[1]/div[2]/div[2]/div[1]/app-aquisition-generic[1]/form[1]/div[1]/div[1]/div[1]/app-acquisition-summary-quote[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/app-acquisition-quote-summary[1]/div[1]/div[2]/app-hire-customer-quote-summary-header[1]/div[1]/div[6]/div[1]/p[1]/strong[1]")
//	private WebElement quote_summary_monthly_total_rental;
	
	@FindBy(xpath = "//*[@class='row']//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[6]/div/p/strong")
	private WebElement quote_summary_monthly_total_rental;
	
	@FindBy(xpath = "//*[@class='row']//*[@id='headingHoldingCost']/div/div[1]/div/div/p/strong")
	private WebElement quote_summary_acq_contract_type;
	
	@FindBy(xpath = "//*[@class='row']//*[@id='headingCustomerQuote']/div[2]/app-hire-customer-quote-summary-header/div/div[1]/div/p/strong")
	private WebElement quote_summary_customer_contract_type;
	
	@FindBy(xpath = "//*[@id=\"headingTwo\"]/div/div/div[1]/div/p/strong")
	private WebElement quote_summary_cost_price_ex_vat_and_rfl;

	@FindBy(xpath = "//*[@id=\"headingTwo\"]/div/div/div[2]/div/p/strong")
	private WebElement quote_summary_otr_vat;
	
	
	@FindBy(xpath = "//*[@id=\"headingTwo\"]/div/div/div[3]/div/p/strong")
	private WebElement quote_summary_otr_rfl_and_frf;
	
	@FindBy(xpath = "//*[@id=\"headingHoldingCost\"]/div/div[2]/div/div/p/strong")
	private WebElement quote_summary_holding_cost_term;
	
	@FindBy(xpath = "//*[@id=\"headingHoldingCost\"]/div/div[3]/div/div/p/strong")
	private WebElement quote_summary_holding_cost_miles_per_annum;
	
	@FindBy(xpath = "//*[@id=\"headingHoldingCost\"]/div/div[4]/div/div/p/strong")
	private WebElement quote_summary_holding_cost_monthly_finance_cost;
	
	@FindBy(xpath = "//*[@id=\"headingHoldingCost\"]/div/div[6]/div/div/p/strong")
	private WebElement quote_summary_holding_cost_monthly_maint_cost_used;
	
	@FindBy(xpath = "//*[@id=\"headingHoldingCost\"]/div/div[7]/div/div/p/strong")
	private WebElement quote_summary_holding_cost_CAP_monthly_maint_cost;
	
	
	public QuoteSummaryOutrightPCHPage() {
		PageFactory.initElements(driver, this);
	}

public boolean quote_summary_OTR_calculation(String sheet_name) throws InterruptedException, IOException {
		
		LO.print("*************OTR Calulation on quote summary page has been started************");
		System.out.println("*************OTR Calulation on quote summary page has been started************");
		
		obj_read_excel_calculation_page =new ReadExcelCalculation();
		
		Click.on(driver, quote_summary, 60);
		
	
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);		
   	
		ExplicitWait.visibleElement(driver, quote_summary_cost_otr_price, 120);
	
		ExplicitWait.visibleElement(driver, quote_summary_cost_price_ex_vat_and_rfl, 120);
		
		ExplicitWait.visibleElement(driver, quote_summary_otr_vat, 120);
		
		ExplicitWait.visibleElement(driver, quote_summary_otr_rfl_and_frf, 120);
		
		LO.print("Reading values from OTR calculation -Quote Summary Page");
		System.out.println("Reading values from OTR calculation -Quote Summary Page");
		
	 
		double OTR_calculation_cost_otr_price_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_cost_otr_price.getText().trim().substring(2)));
 
		double OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_cost_price_ex_vat_and_rfl.getText().trim().substring(2)));

		double OTR_calculation_otr_vat_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_otr_vat.getText().trim().substring(2)));
		 
		double OTR_calculation_otr_rfl_and_frf_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_otr_rfl_and_frf.getText().trim().substring(2)));
		
		LO.print("OTR_calculation_cost_otr_price_from_screen ="+OTR_calculation_cost_otr_price_from_screen_converted);
		System.out.println("OTR_calculation_cost_otr_price_from_screen ="+OTR_calculation_cost_otr_price_from_screen_converted);
		
		LO.print("OTR_calculation_cost_price_ex_vat_and_rfl_from_screen ="+OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted);
		System.out.println("OTR_calculation_cost_price_ex_vat_and_rfl_from_screen ="+OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted);
		
		LO.print("OTR_calculation_otr_vat_from_screen ="+OTR_calculation_otr_vat_from_screen_converted);
		System.out.println("OTR_calculation_otr_vat_from_screen ="+OTR_calculation_otr_vat_from_screen_converted);
			
		LO.print("OTR_calculation_otr_rfl_and_frf_from_screen ="+OTR_calculation_otr_rfl_and_frf_from_screen_converted);
		System.out.println("OTR_calculation_otr_rfl_and_frf_from_screen ="+OTR_calculation_otr_rfl_and_frf_from_screen_converted);
		
		
		double OTR_calculation_cost_otr_price_from_excel = GetExcelFormulaValue.get_formula_value(14, 4, sheet_name);
		double OTR_calculation_cost_price_ex_vat_and_rfl_from_excel = GetExcelFormulaValue.get_formula_value(9, 9, sheet_name);
		double OTR_calculation_otr_vat_from_excel = GetExcelFormulaValue.get_formula_value(10, 4, sheet_name);
		double OTR_calculation_otr_rfl_and_frf_excel = GetExcelFormulaValue.get_formula_value(7, 9, sheet_name);
		
		double diff_otr =Difference.of_two_Double_Values(OTR_calculation_cost_otr_price_from_excel, OTR_calculation_cost_otr_price_from_screen_converted);
		double diff_cost_price =Difference.of_two_Double_Values(OTR_calculation_cost_price_ex_vat_and_rfl_from_excel, OTR_calculation_cost_price_ex_vat_and_rfl_from_screen_converted);
		double diff_otr_vat =Difference.of_two_Double_Values(OTR_calculation_otr_vat_from_excel, OTR_calculation_otr_vat_from_screen_converted);
		double diff_otr_rfl_and_frf =Difference.of_two_Double_Values(OTR_calculation_otr_rfl_and_frf_excel, OTR_calculation_otr_rfl_and_frf_from_screen_converted);

		int count = 0;
		boolean status = false ;
		if(diff_otr<0.2)
        {LO.print("OTR price compared");System.out.println("OTR price compared"); count++;}
		else {LO.print("Found difference between OTR actual price and OTR expected price on Quote Summary Page");System.out.println("Found difference between OTR actual price and OTR expected price on Quote Summary Page");}
		
		if(diff_cost_price<0.2)
        {LO.print("Cost price ex vat and rfl compared");System.out.println("Cost price ex vat and rfl compared"); count++;}
		else {LO.print("Found difference between (Cost price ex vat and rfl) actual and (Cost price ex vat and rfl) expected on Quote Summary Page");System.out.println("Found difference between (Cost price ex vat and rfl) actual and (Cost price ex vat and rfl) expected on Quote Summary Page");}
		
		if(diff_otr_vat<0.2)
        {LO.print("VAT compared");System.out.println("VAT compared"); count++;}
		else {LO.print("Found difference between VAT actual and VAT expected on Quote Summary Page");System.out.println("Found difference between VAT actual and VAT expected on Quote Summary Page");}
		
		if(diff_otr_rfl_and_frf<0.2)
        {LO.print("RFL & FRF compared");System.out.println("RFL & FRF compared"); count++;}
		else {LO.print("Found difference between RFL & FRF actual and RFL & FRF expected on Quote Summary Page");System.out.println("Found difference between RFL & FRF actual and RFL & FRF expected on Quote Summary Page");}
		
		
		if(count==4)
		{status=true;}
		
		return status ;

	}

public boolean quote_summary_holding_cost_calculation_without_maintenance(String sheet_name) throws InterruptedException, IOException {
	
	LO.print("*************Holding Cost Calulation on quote summary page has been started************");
	System.out.println("*************Holding Cost Calulation on quote summary page has been started************");
	
	obj_read_excel_calculation_page =new ReadExcelCalculation();
	
		
	
	ExplicitWait.visibleElement(driver, quote_summary_holding_cost_term, 30);

	ExplicitWait.visibleElement(driver, quote_summary_holding_cost_miles_per_annum, 30);
	
	ExplicitWait.visibleElement(driver, quote_summary_holding_cost_monthly_finance_cost, 30);
	
	ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost_without_maintenance, 30);
	
	LO.print("Reading values from Holding Cost summary -Quote Summary Page");
	System.out.println("Reading values from Holding Cost summary -Quote Summary Page");
	
 
	double holding_cost_terms_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_holding_cost_term.getText().trim().substring(0,2)));

	double holding_cost_miles_per_annum_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_holding_cost_miles_per_annum.getText().trim()));

	double holding_cost_monthly_finance_cost_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_holding_cost_monthly_finance_cost.getText().trim().substring(2)));
	 
	double holding_cost_total_monthly_holding_cost_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_total_monthly_holding_cost_without_maintenance.getText().trim().substring(2)));
	
	LO.print("holding_cost_terms_from_screen"+holding_cost_terms_from_screen_converted);
	System.out.println("holding_cost_terms_from_screen"+holding_cost_terms_from_screen_converted);
	
	LO.print("holding_cost_miles_per_annum_from_screen"+holding_cost_miles_per_annum_from_screen_converted);
	System.out.println("holding_cost_miles_per_annum_from_screen"+holding_cost_miles_per_annum_from_screen_converted);
	
	LO.print("holding_cost_monthly_finance_cost_from_screen"+holding_cost_monthly_finance_cost_from_screen_converted);
	System.out.println("holding_cost_monthly_finance_cost_from_screen"+holding_cost_monthly_finance_cost_from_screen_converted);
		
	LO.print("holding_cost_total_monthly_holding_cost_from_screen ="+holding_cost_total_monthly_holding_cost_from_screen_converted);
	System.out.println("holding_cost_total_monthly_holding_cost_from_screen ="+holding_cost_total_monthly_holding_cost_from_screen_converted);
	
	
	double holding_cost_terms_from_excel = GetExcelFormulaValue.get_formula_value(51, 0, sheet_name);
	double holding_cost_miles_per_annum_from_excel = GetExcelFormulaValue.get_formula_value(50, 1, sheet_name);
	double holding_cost_monthly_finance_cost_from_excel = GetExcelFormulaValue.get_formula_value(35, 0, sheet_name);
	double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);
	
	double diff_terms =Difference.of_two_Double_Values(holding_cost_terms_from_excel, holding_cost_terms_from_screen_converted);
	double diff_miles_per_annum =Difference.of_two_Double_Values(holding_cost_miles_per_annum_from_excel, holding_cost_miles_per_annum_from_screen_converted);
	double diff_finance_cost =Difference.of_two_Double_Values(holding_cost_monthly_finance_cost_from_excel, holding_cost_monthly_finance_cost_from_screen_converted);
	double diff_total_monthly_holding_cost =Difference.of_two_Double_Values(holding_cost_total_monthly_holding_cost_from_excel, holding_cost_total_monthly_holding_cost_from_screen_converted);

	int count = 0;
	boolean status = false ;
	if(diff_terms<0.2)
    {LO.print("terms compared");System.out.println("terms compared"); count++;}
	else {LO.print("Found difference between terms actual  and terms expected ");System.out.println("Found difference between terms actual  and terms expected ");}
	
	if(diff_miles_per_annum<0.2)
    {LO.print("Miles per annum compared");System.out.println("Miles per annum compared"); count++;}
	else {LO.print("Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected ");System.out.println("Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected");}
	
	if(diff_finance_cost<0.2)
    {LO.print("Finance cost compared");System.out.println("Finance cost compared"); count++;}
	else {LO.print("Found difference between Finance cost actual and Finance cost expected");System.out.println("Found difference between Finance cost actual and Finance cost expected");}
	
	if(diff_total_monthly_holding_cost<0.2)
    {LO.print("Total Monthly Holding Cost compared");System.out.println("Total Monthly Holding Cost compared"); count++;}
	else {LO.print("Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");System.out.println("Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");}
	
	
	if(count==4)
	{status=true;}
	
	return status ;

}

public boolean quote_summary_holding_cost_calculation_with_maintenance(String sheet_name) throws InterruptedException, IOException {
	
	LO.print("*************Holding Cost Calulation on quote summary page has been started************");
	System.out.println("*************Holding Cost Calulation on quote summary page has been started************");
	
	obj_read_excel_calculation_page =new ReadExcelCalculation();
	
		
	
	ExplicitWait.visibleElement(driver, quote_summary_holding_cost_term, 30);

	ExplicitWait.visibleElement(driver, quote_summary_holding_cost_miles_per_annum, 30);
	
	ExplicitWait.visibleElement(driver, quote_summary_holding_cost_monthly_finance_cost, 30);
	
	ExplicitWait.visibleElement(driver, quote_summary_holding_cost_monthly_maint_cost_used, 30);
	
	ExplicitWait.visibleElement(driver, quote_summary_holding_cost_CAP_monthly_maint_cost, 30);
	
	ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 30);
	
	LO.print("Reading values from Holding Cost summary -Quote Summary Page");
	System.out.println("Reading values from Holding Cost summary -Quote Summary Page");
	
 
	double holding_cost_terms_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_holding_cost_term.getText().trim().substring(0,2)));

	double holding_cost_miles_per_annum_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_holding_cost_miles_per_annum.getText().trim()));

	double holding_cost_monthly_finance_cost_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_holding_cost_monthly_finance_cost.getText().trim().substring(2)));
	 
	double holding_cost_monthly_maint_cost_used_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_holding_cost_monthly_maint_cost_used.getText().trim().substring(2)));

	double holding_cost_CAP_monthly_maint_cost_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_holding_cost_CAP_monthly_maint_cost.getText().trim().substring(2)));
	
	double holding_cost_total_monthly_holding_cost_from_screen_converted = Double.parseDouble(RemoveComma.of(quote_summary_total_monthly_holding_cost.getText().trim().substring(2)));
	
	LO.print("holding_cost_terms_from_screen"+holding_cost_terms_from_screen_converted);
	System.out.println("holding_cost_terms_from_screen"+holding_cost_terms_from_screen_converted);
	
	LO.print("holding_cost_miles_per_annum_from_screen"+holding_cost_miles_per_annum_from_screen_converted);
	System.out.println("holding_cost_miles_per_annum_from_screen"+holding_cost_miles_per_annum_from_screen_converted);
	
	LO.print("holding_cost_monthly_finance_cost_from_screen"+holding_cost_monthly_finance_cost_from_screen_converted);
	System.out.println("holding_cost_monthly_finance_cost_from_screen"+holding_cost_monthly_finance_cost_from_screen_converted);
	
	LO.print("holding_cost_monthly_maint_cost_used_from_screen"+holding_cost_monthly_maint_cost_used_from_screen_converted);
	System.out.println("holding_cost_monthly_maint_cost_used_from_screen"+holding_cost_monthly_maint_cost_used_from_screen_converted);
	
	LO.print("holding_cost_CAP_monthly_maint_cost_from_screen"+holding_cost_CAP_monthly_maint_cost_from_screen_converted);
	System.out.println("holding_cost_CAP_monthly_maint_cost_from_screen"+holding_cost_CAP_monthly_maint_cost_from_screen_converted);
	
		
	LO.print("holding_cost_total_monthly_holding_cost_from_screen ="+holding_cost_total_monthly_holding_cost_from_screen_converted);
	System.out.println("holding_cost_total_monthly_holding_cost_from_screen ="+holding_cost_total_monthly_holding_cost_from_screen_converted);
	
	
	double holding_cost_terms_from_excel = GetExcelFormulaValue.get_formula_value(51, 0, sheet_name);
	double holding_cost_miles_per_annum_from_excel = GetExcelFormulaValue.get_formula_value(50, 1, sheet_name);
	double holding_cost_monthly_finance_cost_from_excel = GetExcelFormulaValue.get_formula_value(35, 0, sheet_name);
	double holding_cost_monthly_maint_cost_used_from_excel = GetExcelFormulaValue.get_formula_value(39, 1, sheet_name);
	double holding_cost_CAP_monthly_maint_cost_from_excel = GetExcelFormulaValue.get_formula_value(35, 1, sheet_name);
	double holding_cost_total_monthly_holding_cost_from_excel = GetExcelFormulaValue.get_formula_value(51, 1, sheet_name);
	
	double diff_terms =Difference.of_two_Double_Values(holding_cost_terms_from_excel, holding_cost_terms_from_screen_converted);
	double diff_miles_per_annum =Difference.of_two_Double_Values(holding_cost_miles_per_annum_from_excel, holding_cost_miles_per_annum_from_screen_converted);
	double diff_finance_cost =Difference.of_two_Double_Values(holding_cost_monthly_finance_cost_from_excel, holding_cost_monthly_finance_cost_from_screen_converted);
	double diff_maint_cost =Difference.of_two_Double_Values(holding_cost_monthly_maint_cost_used_from_excel, holding_cost_monthly_maint_cost_used_from_screen_converted);
	double diff_CAP_maint =Difference.of_two_Double_Values(holding_cost_CAP_monthly_maint_cost_from_excel, holding_cost_CAP_monthly_maint_cost_from_screen_converted);
	double diff_total_monthly_holding_cost =Difference.of_two_Double_Values(holding_cost_total_monthly_holding_cost_from_excel, holding_cost_total_monthly_holding_cost_from_screen_converted);

	int count = 0;
	boolean status = false ;
	if(diff_terms<0.2)
    {LO.print("terms compared");System.out.println("terms compared"); count++;}
	else {LO.print("Found difference between terms actual  and terms expected ");System.out.println("Found difference between terms actual  and terms expected ");}
	
	if(diff_miles_per_annum<0.2)
    {LO.print("Miles per annum compared");System.out.println("Miles per annum compared"); count++;}
	else {LO.print("Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected ");System.out.println("Found difference between (Miles per annum compared) actual and (Miles per annum compared) expected");}
	
	if(diff_finance_cost<0.2)
    {LO.print("Finance cost compared");System.out.println("Finance cost compared"); count++;}
	else {LO.print("Found difference between Finance cost actual and Finance cost expected");System.out.println("Found difference between Finance cost actual and Finance cost expected");}
	
	if(diff_maint_cost<0.2)
    {LO.print("Maint cost used  compared");System.out.println("Maint cost used compared"); count++;}
	else {LO.print("Found difference between Maint cost used actual and Maint cost used expected");System.out.println("Found difference between Maint cost used actual and Maint cost used expected");}
	
	if(diff_CAP_maint<0.2)
    {LO.print("CAP monthly cost compared");System.out.println("CAP monthly cost compared"); count++;}
	else {LO.print("Found difference between CAP monthly cost actual and CAP monthly cost expected");System.out.println("Found difference between CAP monthly cost actual and CAP monthly cost expected");}
		
	
	if(diff_total_monthly_holding_cost<0.2)
    {LO.print("Total Monthly Holding Cost compared");System.out.println("Total Monthly Holding Cost compared"); count++;}
	else {LO.print("Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");System.out.println("Found difference between Total Monthly Holding Cost actual and Total Monthly Holding Cost expected on Quote Summary Page");}
	
	
	if(count==6)
	{status=true;}
	
	return status ;

}

	public boolean quote_summary_outright_PCH_without_maintenance(String sheet_name) throws InterruptedException, IOException {
		
		LO.print("*************Calculations for Quote Summary page gas been started************");
		System.out.println("*************Calculations for Quote Summary page gas been started************");
		
		obj_read_excel_calculation_page =new ReadExcelCalculation();
		Click.on(driver, quote_summary, 60);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);
		
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.ENTER).build().perform();		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		
		ExplicitWait.visibleElement(driver, quote_summary_ref_no, 120);
		ExplicitWait.visibleElement(driver, quote_summary_cost_otr_price, 120);
		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost_without_maintenance, 120);
		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 120);		
		ExplicitWait.visibleElement(driver, quote_summary_acq_contract_type, 120);
		ExplicitWait.visibleElement(driver, quote_summary_customer_contract_type, 120);
		
		LO.print("Reading values from sceen -Quote Summary Page");
		System.out.println("Reading values from sceen -Quote Summary Page");
		
	    String quote_ref_no = quote_summary_ref_no.getText();
		String temp_quote_summary_cost_otr_price=quote_summary_cost_otr_price.getText().trim().substring(2);
		String temp_quote_summary_total_monthly_holding_cost=quote_summary_total_monthly_holding_cost_without_maintenance.getText().trim().substring(2);
		String temp_quote_summary_monthly_finance_rental=quote_summary_monthly_finance_rental.getText().trim().substring(2);
		String acq_contract_type=quote_summary_acq_contract_type.getText();
		String customer_contract_type=quote_summary_customer_contract_type.getText();
		
		
		LO.print("Getting values from screen");
		System.out.println("Getting values from screen");
		
		LO.print("Quote_summary_cost_otr_price ="+temp_quote_summary_cost_otr_price);
		System.out.println("Quote_summary_cost_otr_price ="+temp_quote_summary_cost_otr_price);
		
		LO.print("Quote_summary_total_monthly_holding_cost ="+temp_quote_summary_total_monthly_holding_cost);
		System.out.println("Quote_summary_total_monthly_holding_cost ="+temp_quote_summary_total_monthly_holding_cost);
		
		LO.print("Quote_summary_monthly_finance_rental ="+temp_quote_summary_monthly_finance_rental);
		System.out.println("Quote_summary_monthly_finance_rental ="+temp_quote_summary_monthly_finance_rental);
				
		LO.print("Acquisition contract_type ="+acq_contract_type);
		System.out.println("Acquisition contract_type ="+acq_contract_type);
		
		LO.print("Customer contract_type ="+customer_contract_type);
		System.out.println("Customer contract_type ="+customer_contract_type);	
		
		//LO.print("Customer Quote generated successfully and Quote_ref_no ="+quote_ref_no);
		//System.out.println("Customer Quote generated successfully and Quote_ref_no ="+quote_ref_no);
		
		String quote_summary_cost_otr_price_from_screen=RemoveComma.of(temp_quote_summary_cost_otr_price);
		String quote_summary_total_monthly_holding_cost_from_screen=RemoveComma.of(temp_quote_summary_total_monthly_holding_cost);
		String quote_summary_monthly_finance_rental_from_screen=RemoveComma.of(temp_quote_summary_monthly_finance_rental);
				
		double quote_summary_cost_otr_price_from_screen_converted =Double.parseDouble(quote_summary_cost_otr_price_from_screen);
		double quote_summary_total_monthly_holding_cost_from_screen_converted =Double.parseDouble(quote_summary_total_monthly_holding_cost_from_screen);
		double quote_summary_monthly_finance_rental_from_screen_converted =Double.parseDouble(quote_summary_monthly_finance_rental_from_screen);

		
		return obj_read_excel_calculation_page.verify_quote_summary_values_from_excel_without_maintenance(quote_summary_cost_otr_price_from_screen_converted, 
				quote_summary_total_monthly_holding_cost_from_screen_converted, 
				quote_summary_monthly_finance_rental_from_screen_converted, sheet_name);	

	}

	public boolean quote_summary_outright_PCH_with_maintenance(String sheet_name) throws InterruptedException, IOException {
		
		LO.print("*************Calculations for Quote Summary page gas been started************");
		System.out.println("*************Calculations for Quote Summary page gas been started************");
		
		obj_read_excel_calculation_page =new ReadExcelCalculation();
		Click.on(driver, quote_summary, 60);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);
		
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.ENTER).build().perform();		
	
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 35);
		
		ExplicitWait.visibleElement(driver, quote_summary_ref_no, 120);
		ExplicitWait.visibleElement(driver, quote_summary_cost_otr_price, 120);
		ExplicitWait.visibleElement(driver, quote_summary_total_monthly_holding_cost, 120);
		ExplicitWait.visibleElement(driver, quote_summary_monthly_finance_rental, 120);
		ExplicitWait.visibleElement(driver, quote_summary_monthly_maintenance_rental, 120);
		ExplicitWait.visibleElement(driver, quote_summary_monthly_total_rental, 120);
		ExplicitWait.visibleElement(driver, quote_summary_acq_contract_type, 120);
		ExplicitWait.visibleElement(driver, quote_summary_customer_contract_type, 120);
		
		LO.print("Reading values from sceen -Quote Summary Page");
		System.out.println("Reading values from sceen -Quote Summary Page");
		
	    String quote_ref_no = quote_summary_ref_no.getText();
		String temp_quote_summary_cost_otr_price=quote_summary_cost_otr_price.getText().trim().substring(2);
		String temp_quote_summary_total_monthly_holding_cost=quote_summary_total_monthly_holding_cost.getText().trim().substring(2);
		String temp_quote_summary_monthly_finance_rental=quote_summary_monthly_finance_rental.getText().trim().substring(2);
		String temp_quote_summary_monthly_maintenance_rental=quote_summary_monthly_maintenance_rental.getText().trim().substring(2);
		String temp_quote_summary_monthly_total_rental=quote_summary_monthly_total_rental.getText().trim().substring(2);
		String acq_contract_type=quote_summary_acq_contract_type.getText();
		String customer_contract_type=quote_summary_customer_contract_type.getText();
		
		
		LO.print("Getting values from screen");
		System.out.println("Getting values from screen");
		
		LO.print("Quote_summary_cost_otr_price ="+temp_quote_summary_cost_otr_price);
		System.out.println("Quote_summary_cost_otr_price ="+temp_quote_summary_cost_otr_price);
		
		LO.print("Quote_summary_total_monthly_holding_cost ="+temp_quote_summary_total_monthly_holding_cost);
		System.out.println("Quote_summary_total_monthly_holding_cost ="+temp_quote_summary_total_monthly_holding_cost);
		
		LO.print("Quote_summary_monthly_finance_rental ="+temp_quote_summary_monthly_finance_rental);
		System.out.println("Quote_summary_monthly_finance_rental ="+temp_quote_summary_monthly_finance_rental);
		
		LO.print("Quote_summary_monthly_maintenance_rental ="+temp_quote_summary_monthly_maintenance_rental);
		System.out.println("Quote_summary_monthly_maintenance_rental ="+temp_quote_summary_monthly_maintenance_rental);
		
		LO.print("Quote_summary_monthly_total_rental ="+temp_quote_summary_monthly_total_rental);
		System.out.println("Quote_summary_monthly_total_rental ="+temp_quote_summary_monthly_total_rental);
		
		LO.print("Acquisition contract_type ="+acq_contract_type);
		System.out.println("Acquisition contract_type ="+acq_contract_type);
		
		LO.print("Customer contract_type ="+customer_contract_type);
		System.out.println("Customer contract_type ="+customer_contract_type);	
		
//		LO.print("Customer Quote generated successfully and Quote_ref_no ="+quote_ref_no);
//		System.out.println("Customer Quote generated successfully and Quote_ref_no ="+quote_ref_no);
		
		String quote_summary_cost_otr_price_from_screen=RemoveComma.of(temp_quote_summary_cost_otr_price);
		String quote_summary_total_monthly_holding_cost_from_screen=RemoveComma.of(temp_quote_summary_total_monthly_holding_cost);
		String quote_summary_monthly_finance_rental_from_screen=RemoveComma.of(temp_quote_summary_monthly_finance_rental);
		String quote_summary_monthly_maintenance_rental_from_screen=RemoveComma.of(temp_quote_summary_monthly_maintenance_rental);
		String quote_summary_monthly_total_rental_from_screen=RemoveComma.of(temp_quote_summary_monthly_total_rental);

	
		double quote_summary_cost_otr_price_from_screen_converted =Double.parseDouble(quote_summary_cost_otr_price_from_screen);
		double quote_summary_total_monthly_holding_cost_from_screen_converted =Double.parseDouble(quote_summary_total_monthly_holding_cost_from_screen);
		double quote_summary_monthly_finance_rental_from_screen_converted =Double.parseDouble(quote_summary_monthly_finance_rental_from_screen);
		double quote_summary_monthly_maintenance_rental_from_screen_converted=Double.parseDouble(quote_summary_monthly_maintenance_rental_from_screen);
		double quote_summary_monthly_total_rental_from_screen_converted=Double.parseDouble(quote_summary_monthly_total_rental_from_screen);
		
		return obj_read_excel_calculation_page.verify_quote_summary_values_from_excel_with_maintenance(quote_summary_cost_otr_price_from_screen_converted, 
				quote_summary_total_monthly_holding_cost_from_screen_converted, 
				quote_summary_monthly_finance_rental_from_screen_converted, 
				quote_summary_monthly_maintenance_rental_from_screen_converted, 
				quote_summary_monthly_total_rental_from_screen_converted, sheet_name);	

	}

	

}
