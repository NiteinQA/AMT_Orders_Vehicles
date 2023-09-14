package acquisition;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.CustomerQuotePackage.CustomerQuotePageBrokerBCHPage;
import com.amt.QuoteSummaryPages.QuoteSummaryBrokerBCHPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_Broker_BCH_Page;
import com.amt.testBase.TestBase;
import com.amt.testUtil.ReadExcelData;


@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Acquisition_Quotes_Broker_BCH_without_maintenance_Test extends TestBase {
	
	 
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_Broker_BCH_Page obj_contract_types_and_OTR_page;
	CustomerQuotePageBrokerBCHPage obj_customer_quote_page;
	QuoteSummaryBrokerBCHPage  obj_quote_summary_page;
	
	
		
	@Test(priority=1, dataProvider="testData")
	public void aquisition_quotes_user_flow_broker_bch_OTR_calculation_without_maintenance_test(String manufacturer, String model, String quoteRef, String quoteExpiryDate, String term, String milesperannum, 
			String initialFinanceRental, String monthlyFinanceRental, String pensePerExcessMileFinance, String commission, String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet,
			String orderDeposit,String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
			
		
		 obj_acq_listing_page = new AcquisitionListingPage();
		 obj_vehicle_selection_page = new VehicleSelectionPage();
	     obj_options_accessories = new OptionsAccessoriesPage();
	     obj_contract_types_and_OTR_page = new ContractTypesAndOTR_Broker_BCH_Page();
	
		
		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		boolean subtotal_after_discount=obj_contract_types_and_OTR_page.contractTypes_and_OTR_selection_broker_bch(sheet_name);
		Assert.assertTrue(subtotal_after_discount);
	}
	
	@Test(priority=2, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_user_flow_broker_bch_OTR_calculation_without_maintenance_test" })

	public void aquisition_quotes_user_flow_broker_bch_after_discount_calculation_without_maintenance_test(String manufacturer, String model, String quoteRef, String quoteExpiryDate, String term, String milesperannum, 
			String initialFinanceRental, String monthlyFinanceRental, String pensePerExcessMileFinance, String commission, String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet,
			String orderDeposit,String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
				
		obj_contract_types_and_OTR_page = new ContractTypesAndOTR_Broker_BCH_Page();
			 
			boolean otr_price_check = obj_contract_types_and_OTR_page
					.verify_after_discount_calculations_contract_types_page(sheet_name);
			Assert.assertTrue(otr_price_check);
			
		}
	
	@Test(priority=3, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_user_flow_broker_bch_after_discount_calculation_without_maintenance_test" })

	public void aquisition_quotes_user_flow_broker_bch_funder_quote_addition_without_maintenance_test(String manufacturer, String model, String quoteRef, String quoteExpiryDate, String term, String milesperannum, 
			String initialFinanceRental, String monthlyFinanceRental, String pensePerExcessMileFinance, String commission, String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet,
			String orderDeposit,String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
				

			 obj_customer_quote_page = new CustomerQuotePageBrokerBCHPage();	
			
			boolean customer_quote_check=obj_customer_quote_page.customer_Quote_broker_bch_without_maintenance(quoteRef, quoteExpiryDate,term, milesperannum, 
					initialFinanceRental,monthlyFinanceRental, pensePerExcessMileFinance, commission);			
			Assert.assertTrue(customer_quote_check);
			
		}
	
	@Test(priority=4, dataProvider="testData", dependsOnMethods = { "aquisition_quotes_user_flow_broker_bch_funder_quote_addition_without_maintenance_test" })

	public void aquisition_quotes_user_flow_broker_bch_quote_summary_values_verification_without_maintenance_test(String manufacturer, String model, String quoteRef, String quoteExpiryDate, String term, String milesperannum, 
			String initialFinanceRental, String monthlyFinanceRental, String pensePerExcessMileFinance, String commission, String partExchangeActual, String partExchangeGiven, String lessFinanceSettlemnet,
			String orderDeposit,String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
				
			

			 obj_quote_summary_page =new QuoteSummaryBrokerBCHPage();	
			
	         obj_quote_summary_page.quote_summary_broker_BCH_without_maintenance(sheet_name);
				
		}	
	
	@DataProvider(name="testData")
	public Object[][] getTestData() throws IOException {		
		Object[][] data=ReadExcelData.getTestData("BrokerBCHwithoutMaintenance");
		return data;	
		
	}

}
