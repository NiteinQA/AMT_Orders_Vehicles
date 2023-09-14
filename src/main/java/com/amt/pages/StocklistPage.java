package com.amt.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.CommonClass;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.GetExcelFormulaValue;

public class StocklistPage extends TestBase {

	

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	// orders_menu
	@FindBy(xpath = "//span[contains(text(),'Vehicles')]")
	private WebElement vehicles_menu;
	
	
	
	@FindBy(xpath = "//span[contains(text(),'Stocklist')]")
	private WebElement stocklist;

	// General Tab Element
	@FindBy(xpath = "//*[contains(text(),'Order Status')]")
	private WebElement order_status;

	Properties prop;

	Actions act;
	
	OrdersListPage obj_orderlist_page;
	CommonClass obj_common_class_page;

	public StocklistPage() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"D:\\Orders_Vehicles\\AMT_Orders_Vehicles\\src\\main\\java\\configs\\excelValues.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PageFactory.initElements(driver, this);
	}

	public void search_order_in_list() throws IOException, InterruptedException {

		// Click on vehicle menu and enter to orders list page
//		Click.on(driver, vehicles_menu, 60);
		
		obj_common_class_page = new CommonClass();
		
		obj_common_class_page.mouse_hover_to_element(vehicles_menu);
		
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
		
		Thread.sleep(1000);
		
		Click.on(driver,stocklist , 60);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

	    obj_orderlist_page = new OrdersListPage();

	    obj_orderlist_page.search_in_search_bar();

	}

	
	public String validate_selected_order_opened() throws InterruptedException {
		
		obj_common_class_page = new CommonClass();

		return obj_common_class_page.validate_selected_order(order_status);
	}
	
	

}
