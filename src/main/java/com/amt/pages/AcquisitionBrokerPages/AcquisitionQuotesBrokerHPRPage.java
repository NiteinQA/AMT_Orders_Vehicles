package com.amt.pages.AcquisitionBrokerPages;


import com.amt.CustomerQuotePackage.CustomerQuotePageBrokerHPRPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.ContractTypesAndOTRPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_Broker_HPR_Page;
import com.amt.testBase.TestBase;



public class AcquisitionQuotesBrokerHPRPage extends TestBase {
	
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage   obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_Broker_HPR_Page obj_contract_types_and_OTR_Broker_HPR_Page;
	CustomerQuotePageBrokerHPRPage obj_customer_quote_page;
	

	
public void acquisition_quote_geneartion_broker_hpr(String manufacturer, String model, String quoteRef, 
        String quoteExpiryDate,String term,String milesperannum,
        String cashdeposite,String financecharges,
        String monthlyfinancepayments,
        String finalballoonpayment, String optiontopurchasefee, String apr, 
        String commissionvalue) throws InterruptedException {
		
		obj_acq_listing_page=new AcquisitionListingPage();
		obj_vehicle_selection_page=new VehicleSelectionPage();
		obj_options_accessories=new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_Broker_HPR_Page=new ContractTypesAndOTR_Broker_HPR_Page();
		obj_customer_quote_page =new CustomerQuotePageBrokerHPRPage();		
		
		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		obj_contract_types_and_OTR_Broker_HPR_Page.contractTypes_and_OTR_selection_broker_hpr();
		obj_customer_quote_page.customer_Quote_broker_hpr( manufacturer,  model,  quoteRef, 
                quoteExpiryDate, term, milesperannum,
                cashdeposite, financecharges,
                monthlyfinancepayments,
                finalballoonpayment,  optiontopurchasefee,  apr, 
                commissionvalue);
	}
	
}

