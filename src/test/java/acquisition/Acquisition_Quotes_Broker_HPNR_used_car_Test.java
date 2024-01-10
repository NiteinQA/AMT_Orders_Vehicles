package acquisition;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePageBrokerHPNRPage;
import com.amt.QuoteSummaryPages.QuoteSummaryBrokerHPNRPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_Broker_HPNR_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;


@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_Broker_HPNR_used_car_Test extends TestBase {
	
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_Broker_HPNR_Page obj_contract_types_and_OTR_page;
	CustomerQuotePageBrokerHPNRPage obj_customer_quote_page;
	QuoteSummaryBrokerHPNRPage  obj_quote_summary_page;
	
	
		
	@Test(priority=1, dataProvider="testData")
	public void aquisition_quotes_user_flow_broker_HPNR_OTR_calculation_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, String vehicle_profit, String sales_price_percentage,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit,String financeCharges, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String finalBallonPayment, String optionToPurchaseFee, 
			String rflIncluded, String apr, String commission,String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			
		
		 obj_acq_listing_page = new AcquisitionListingPage();
		 obj_vehicle_selection_page = new VehicleSelectionPage();
	     obj_options_accessories = new OptionsAccessoriesPage();
	     obj_contract_types_and_OTR_page = new ContractTypesAndOTR_Broker_HPNR_Page();
				
		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle_for_used_car_flow(registrationNumber, mileage);
		obj_options_accessories.options_And_Accessories_selection_for_used_car();
		boolean cost_price_ex_vat_and_options_and_preparation_cost = obj_contract_types_and_OTR_page
				.contractTypes_selection_and_OTR_calculation(sheet_name);
		Assert.assertTrue(cost_price_ex_vat_and_options_and_preparation_cost);	
	}
	
	@Test(priority=2, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_user_flow_broker_HPNR_OTR_calculation_test" })

	
	public void aquisition_quotes_used_car_edit_cost_price_and_check_OTR_with_maintenance_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, String vehicle_profit, String sales_price_percentage,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit,String financeCharges, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String finalBallonPayment, String optionToPurchaseFee, 
			String rflIncluded, String apr, String commission,String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			
	
		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_Broker_HPNR_Page();
			
//		boolean cost_price_ex_vat_and_options_and_preparation_cost = obj_contract_types_and_OTR_page
//		.edit_vehicle_cost_price_and_check_OTR_price(vehicelCostPrice, options_and_preparation_cost, sheet_name);
//Assert.assertTrue(cost_price_ex_vat_and_options_and_preparation_cost);
	
	}
	
	@Test(priority=3, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_used_car_edit_cost_price_and_check_OTR_with_maintenance_test" })

	public void aquisition_quotes_user_flow_broker_HPNR_vehicle_profit_check_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, String vehicle_profit, String sales_price_percentage,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit,String financeCharges, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String finalBallonPayment, String optionToPurchaseFee, 
			String rflIncluded, String apr, String commission,String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			

		 obj_customer_quote_page = new CustomerQuotePageBrokerHPNRPage();
	
	
		
		boolean customer_quote_vehicle_profit_check=obj_customer_quote_page.customer_Quote_page_verification_broker_purchase_used_vehicle(vehicle_profit,quoteRef,quoteExpiryDate, term, milesperannum,
				contractMileage,cahDeposit, noOfMonthlyPayments, monthlyFinancePayment, finalBallonPayment, optionToPurchaseFee, 
			rflIncluded,  apr,commission, partExchangeActual, partExchangeGiven, lessFinanceSettlemnet, sheet_name);	
		Assert.assertTrue(customer_quote_vehicle_profit_check);
		
		boolean monthly_total_payment_after_editing_vehicle_sales_price = obj_customer_quote_page.edit_otr_sales_price_and_verify_profit_broker_purchase_used_vehicle( sales_price_percentage, sheet_name);
		Assert.assertTrue(monthly_total_payment_after_editing_vehicle_sales_price);

		
	}
	
	@Test(priority=4, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_user_flow_broker_HPNR_vehicle_profit_check_test" })

	public void aquisition_quotes_user_flow_broker_HPNR_balance_to_finance_check_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, String vehicle_profit, String sales_price_percentage,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit,String financeCharges, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String finalBallonPayment, String optionToPurchaseFee, 
			String rflIncluded, String apr, String commission,String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			
	
		 obj_customer_quote_page = new CustomerQuotePageBrokerHPNRPage();
		
		boolean customer_quote_balance_to_finance_check=obj_customer_quote_page.customer_Quote_balance_to_finance_checking_broker_purchase_used_vehicle(vehicle_profit,quoteRef,quoteExpiryDate, term, milesperannum,
				contractMileage,cahDeposit, financeCharges,noOfMonthlyPayments, monthlyFinancePayment, finalBallonPayment, optionToPurchaseFee, 
			rflIncluded,  apr,commission, partExchangeActual, partExchangeGiven, lessFinanceSettlemnet, sheet_name);	
		Assert.assertTrue(customer_quote_balance_to_finance_check);
				
	}
	
	@Test(priority=5, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_user_flow_broker_HPNR_balance_to_finance_check_test" })

	public void aquisition_quotes_user_flow_broker_HPNR_quote_summary_values_verification_test(String registrationNumber,
			String mileage, String vehicelCostPrice, String options_and_preparation_cost, String vehicle_profit, String sales_price_percentage,
			String quoteRef,String quoteExpiryDate, String term, String milesperannum,String contractMileage,String cahDeposit,String financeCharges, 
			String noOfMonthlyPayments, String monthlyFinancePayment, String finalBallonPayment, String optionToPurchaseFee, 
			String rflIncluded, String apr, String commission,String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			

		 obj_quote_summary_page =new QuoteSummaryBrokerHPNRPage();		
		
		
			
		 obj_quote_summary_page.quote_summary_broker_HPNR_used_car(sheet_name);
		
	}	
	
	@DataProvider(name="testData")
	public Object[][] getTestData() throws IOException {		
		Object[][] data=ReadExcelData.getTestData("BrokerHPNR_used_car");
		return data;
	}

}
