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

public class OrdersListPage extends TestBase {

	JavascriptExecutor js;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	// orders_menu
	@FindBy(xpath = "//span[contains(text(),'Orders')]")
	private WebElement orders_menu;

	// Search box input
	@FindBy(xpath = "//input[@placeholder='Search something here']")
	private WebElement orders_search_bar;

	// Searched Order
	@FindBy(xpath = "//table//tbody//tr")
	private WebElement orders_lists_search_output;
	
	// Order summary Element
	@FindBy(xpath = "//*[contains(text(),'Order summary')]")
	private WebElement order_summary;

	Properties prop;

	Actions act;
	
	OrdersListPage obj_orderlist_page;
	
	CommonClass obj_common_class_page;

	public OrdersListPage() {
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

		// Click on orders menu and enter to orders list page
		Click.on(driver, orders_menu, 60);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

	    obj_orderlist_page = new OrdersListPage();

	    obj_orderlist_page.search_in_search_bar();

	}

	public void search_in_search_bar() throws IOException, InterruptedException {
		// Getting Order number from associated sheet
		String sheetName = prop.getProperty("OP-OP-Orders");
		String orderNumber = GetExcelFormulaValue.get_cell_value(1, 1, sheetName);
		
		System.out.println("Searching for Order ID "+orderNumber);
		LO.print("Searching for Order ID "+orderNumber);

		// Enter this order no. to search bar and press enter
		Click.sendKeys(driver, orders_search_bar, orderNumber, 60);
		orders_search_bar.sendKeys(Keys.ENTER);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);
	}

	public void open_searched_output() throws InterruptedException {

		// double click o output and land to order summary page
		act = new Actions(driver);
		act.doubleClick(orders_lists_search_output).perform();
		
		Thread.sleep(5000);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);
	}
	
	public String validate_selected_order_opened() throws InterruptedException {

		obj_common_class_page = new CommonClass();
		
		return obj_common_class_page.validate_selected_order(order_summary);
		
	}


	
	

}
