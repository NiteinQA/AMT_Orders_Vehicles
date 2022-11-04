package com.amt.pages.AcquisitionOutrightPage;


import com.amt.CustomerQuotePackage.CustomerQuotePageOutrightOutrightPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_Outright_Outright_Page;
import com.amt.testBase.TestBase;



public class AcquisitionQuotesOutrightOutrightPage extends TestBase {
	
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage   obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_Outright_Outright_Page obj_contract_types_and_OTR_Outright_Outrightpage;
	CustomerQuotePageOutrightOutrightPage obj_customer_quote_page;
	
	public void acquisition_quote_geneartion_outright_outright(String manufacturer, String model) throws InterruptedException {
		
		obj_acq_listing_page=new AcquisitionListingPage();
		obj_vehicle_selection_page=new VehicleSelectionPage();
		obj_options_accessories=new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_Outright_Outrightpage = new ContractTypesAndOTR_Outright_Outright_Page();
		obj_customer_quote_page =new CustomerQuotePageOutrightOutrightPage();
		
		
		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		obj_contract_types_and_OTR_Outright_Outrightpage.contractTypes_and_OTR_selection_outright_outright();
		obj_customer_quote_page.customer_Quote_outright_outright();
	}
	

	
	}



