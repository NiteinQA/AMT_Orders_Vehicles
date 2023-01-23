package com.amt.api.pojo;

import com.amt.testBase.TestBase;

public class OppStatusData extends TestBase {

	


		String QuoteRef;
		String OpportunityId;
		
		
		

		public  OppStatusData() {

		}
		
		public OppStatusData(String QuoteRef, String OpportunityId) {
			
			this.QuoteRef = QuoteRef;
			this.OpportunityId = OpportunityId;
		}

		
		

		public void StatusData(String QuoteRef, String OpportunityId) {
			this.QuoteRef = QuoteRef;
			this.OpportunityId = OpportunityId;
		}

		
		//getters and setters methods:
		public String getQuoteRef() {
			return QuoteRef;
		}

		public void setQuoteRef(String QuoteRef) {
			this.QuoteRef = QuoteRef;
		}

		public String getOpportunityId() {
			return OpportunityId;
		}

		public void setOpportunityId(String OpportunityId) {
			this.OpportunityId = OpportunityId;
		}

		
	
}
		
		
		
		
		
		

	
