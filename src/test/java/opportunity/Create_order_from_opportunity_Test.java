package opportunity;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amt.pages.Opportunities;
import com.amt.testBase.TestBase;
import com.amt.testUtil.CommonClass;

@Listeners(com.amt.testUtil.ScreenshotListener.class)
public class Create_order_from_opportunity_Test extends TestBase {



		CommonClass obj_common_class;
		
		Opportunities obj_Opportunities_Page;
		
		@Test(priority = 1)
		public void create_order_from_opportunity_test() throws Exception {
			
			obj_common_class= new CommonClass();
			
			obj_Opportunities_Page = new Opportunities();

			obj_Opportunities_Page.opp_menu_link();

			obj_Opportunities_Page.search_opportunity_to_create_order();
			
			obj_common_class.create_order_from_opportunity("Order_ID");
			
			

		}




}
