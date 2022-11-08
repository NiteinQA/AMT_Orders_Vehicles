package com.amt.pages.AcquisitionOutrightPage;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.amt.CustomerQuotePackage.CustomerQuotePageOutrightBCHPage;
import com.amt.HoldingCostPages.HoldingCostOutrightBCHPage;
import com.amt.pages.AcquisitionListingPage;
import com.amt.pages.ContractTypesAndOTRPage;
import com.amt.pages.OptionsAccessoriesPage;
import com.amt.pages.VehicleSelectionPage;
import com.amt.pages.ContractTypesAndOTRPages.ContractTypesAndOTR_Outright_BCH_Ownbook_CalculationPage;
import com.amt.testBase.TestBase;



public class AcquisitionQuoteOutrightBCHownbookCalculationPage extends TestBase {
	
	AcquisitionListingPage obj_acq_listing_page;
	VehicleSelectionPage   obj_vehicle_selection_page;
	OptionsAccessoriesPage obj_options_accessories;
	ContractTypesAndOTR_Outright_BCH_Ownbook_CalculationPage obj_contract_types_and_OTR_Outright_BCH_page;
	HoldingCostOutrightBCHPage obj_holding_cost_Outright_BCH_page;
	CustomerQuotePageOutrightBCHPage obj_customer_quote_page;
	
	public void acquisition_quote_geneartion_outright_BCH_OwnbookCalculation(String manufacturer, String model, String sheet_name) throws InterruptedException, IOException, UnsupportedFlavorException {
		
		obj_acq_listing_page=new AcquisitionListingPage();
		obj_vehicle_selection_page=new VehicleSelectionPage();
		obj_options_accessories=new OptionsAccessoriesPage();
		obj_contract_types_and_OTR_Outright_BCH_page = new ContractTypesAndOTR_Outright_BCH_Ownbook_CalculationPage();
		obj_customer_quote_page =new CustomerQuotePageOutrightBCHPage();
		obj_holding_cost_Outright_BCH_page=new HoldingCostOutrightBCHPage();
		
		obj_acq_listing_page.aquisition_Listingpage_AddnewQuote();
		obj_vehicle_selection_page.select_vehicle(manufacturer, model);
		obj_options_accessories.options_And_Accessories_selection();
		obj_contract_types_and_OTR_Outright_BCH_page.contractTypes_and_OTR_selection_outright_BCH_Ownbook_calculation(sheet_name);
		//obj_holding_cost_Outright_BCH_page.verify_holding_cost();
		//obj_customer_quote_page.customer_Quote_outright_outright_BCH_Ownbook_calculation();
	}
	
	}



