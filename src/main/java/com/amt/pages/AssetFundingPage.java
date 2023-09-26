package com.amt.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amt.testBase.TestBase;
import com.amt.testUtil.Click;
import com.amt.testUtil.ExplicitWait;
import com.amt.testUtil.JavaScriptExecutor;

public class AssetFundingPage extends TestBase {

	JavascriptExecutor js;

	@FindBy(xpath = "//img[@alt='Loading...']")
	private List<WebElement> loading_icon;

	@FindBy(xpath = "//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details")
	private List<WebElement> stocking_plan_list;

	// Asset funding Element
	@FindBy(xpath = "//*[contains(text(),'Asset funding')]")
	private WebElement asset_funding;

	// Cash purchase Element
	@FindBy(xpath = "//*[contains(text(),' Cash purchase ')]")
	private WebElement status_cash_purchase;

	// Stocking plan Element
	@FindBy(xpath = "//*[contains(text(),' Stocking plan ')]")
	private WebElement status_stocking_plan;

//	// Required Element
//	@FindBy(xpath = "//*[contains(text(),' Required ')]")
//	private WebElement status_required;

	// status Required
	@FindBy(xpath = "//*[contains(text(),'Required')]//ancestor::div[1]//div/span[2]")
	private WebElement status_Required;

	// status Pending Activation
	@FindBy(xpath = "//*[contains(text(),'Pending Activation')]//ancestor::div[1]//div/span[2]")
	private WebElement status_pending_activation;

	// status Finance Activated
	@FindBy(xpath = "//*[contains(text(),'Finance Activated')]//ancestor::div[1]//div/span[2]")
	private WebElement status_finance_activated;

	// status Finance Completed
	@FindBy(xpath = "//*[contains(text(),'Finance Completed')]//ancestor::div[1]//div/span[2]")
	private WebElement status_finance_completed;

	// funded Element
	@FindBy(xpath = "//*[contains(text(),' Funded ')]")
	private WebElement status_funded;

	// funded Element
	@FindBy(xpath = "//*[contains(text(),'Add Funder Quote')]")
	private WebElement add_funder_quote;

	// cash purchase pending
	@FindBy(xpath = "//*[contains(text(),'Cash Purchase Pending')]//ancestor::div[1]//div/span[2]")
	private WebElement status_cash_purchase_pending;

	// cash purchase pending
	@FindBy(xpath = "//*[contains(text(),'Cash Purchase Completed')]//ancestor::div[1]//div/span[2]")
	private WebElement status_cash_purchase_completed;

	// cash purchase pending
	@FindBy(xpath = "//*[contains(text(),'Complete Cash Purchase')]")
	private WebElement complete_cash_purchase;

	// complete funding
	@FindBy(xpath = "//*[contains(text(),'Complete funding')]")
	private WebElement complete_funding;

	// cash purchase pending
	@FindBy(xpath = "//*[@id='complete-funding-alert']//*[contains(text(),' Confirm')]")
	private WebElement confirm_complete;

	@FindBy(xpath = "//*[@class='slider round sliderRed']")
	private WebElement maintenance_toggle_button;

	// *[contains(text(),'Status')]//ancestor::div[1]//div//div

	// Status of the stocking plan
	@FindBy(xpath = "//*[contains(text(),'Status')]//ancestor::div[1]//div//div")
	private WebElement status_of_stocking_plan;

	// Confirm button on add pop up
	@FindBy(xpath = "//*[@id='confirmBtn']")
	private WebElement confirm_add;

	// Status of the stocking plan
	@FindBy(xpath = "//*[contains(text(),'Status')]//ancestor::div[1]//div/div")
	private WebElement stocking_plan_status;

	// *[contains(text(),'Lombard Demo')]

	// Stocking plan - Lombard Demo
	@FindBy(xpath = "//*[contains(text(),'Lombard Demo')]")
	private WebElement stocking_plan_lombard_demo;
	
	// Stocking plan - Remove
	@FindBy(xpath = "//*[contains(text(),'Remove')]")
	private WebElement stocking_plan_remove;
	
	@FindBy(xpath = "//*[@id='removeConfirmBtn']")
	private WebElement confirm_remove_button;


	@FindBy(xpath = "//*[@id='reject']")
	private WebElement reject_checkbox;


	@FindBy(xpath = "//*[@id='rejectionReason']")
	private WebElement rejection_reason_input_field;
	
	
//	// Confirm complete funding
//	@FindBy(xpath = "//*[@id='complete-funding-alert']//*[contains(text(),'Confirm')]")
//	private WebElement confirm_complete_funding;

	Properties prop;

	Actions act;

	AssetFundingPage obj_vehicle_order_tab;

	public AssetFundingPage() {
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

	public boolean open_asset_funding_tab_and_complete_cash_purchase() throws InterruptedException {

		Click.on(driver, complete_cash_purchase, 30);

		Click.on(driver, confirm_complete, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		LO.print("Clicked on Complete Cash purchase");
		System.out.println("Clicked on Complete Cash purchase");

		if (!complete_cash_purchase.isEnabled()) {
			LO.print("Cash Purchase Completed");
			System.out.println("Cash Purchase Completed");

			return true;
		} else {
			LO.print("Cash Purchase Not Completed");
			System.err.println("Cash Purchase Not Completed");

			return false;
		}

	}

	public boolean verify_stocking_plan_is_not_enabled_for_delivery_location_non_AMT() throws InterruptedException {

		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, status_stocking_plan, 30);

		String className = status_stocking_plan.getAttribute("class");

		if (className.contains("disabled")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean verify_stocking_plan_Lombard_Demo_is_available_if_channel_is_internal_use()
			throws InterruptedException {

		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, status_stocking_plan, 30);

		Click.on(driver, status_stocking_plan, 20);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		// Check default status is required

		ExplicitWait.waitForListOfVisibleElements(driver, stocking_plan_list, 20);

		int noOfStockingPlan = stocking_plan_list.size();
		boolean nameMatching = false;
		try {

			ExplicitWait.visibleElement(driver, stocking_plan_lombard_demo, 10);

			String nameOfStockingPlan = stocking_plan_lombard_demo.getText();

			LO.print("Name of the Stocking plan is " + nameOfStockingPlan);
			System.out.println("Name of the Stocking plan is " + nameOfStockingPlan);

			LO.print("Number of the Stocking plan available is/are " + noOfStockingPlan);
			System.out.println("Number of the Stocking plan available is/are " + noOfStockingPlan);

			if (noOfStockingPlan == 1 && nameOfStockingPlan.contains("Lombard Demo")) {
				nameMatching = true;

				LO.print("For Channel Selection - Internal use , there should be only One Stocking plan available i.e. "
						+ nameOfStockingPlan);
				System.out.println(
						"For Channel Selection - Internal use , there should be only One Stocking plan available i.e. "
								+ nameOfStockingPlan);

				LO.print(
						"Hence it is clear that for Channel Internal Use - Right Stocking plan is shown as per condition");
				System.out.println(
						"Hence it is clear that for Channel Internal Use - Right Stocking plan is shown as per condition");

			}

		} catch (Exception e) {
			nameMatching = false;

			LO.print("Stocking plan shown is wrong for channel Internal Use");
			System.err.println("Stocking plan shown is wrong for channel Internal Use");
		}

		return nameMatching;
	}

	public boolean stocking_pan_Lombard_and_Black_Horse_Are_Available_If_Channel_Is_Ownbook() throws InterruptedException {

		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, status_stocking_plan, 30);

		Click.on(driver, status_stocking_plan, 20);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		// Check default status is required
		boolean nameMatching = false;
		try {
			ExplicitWait.waitForListOfVisibleElements(driver, stocking_plan_list, 20);

			int noOfStockingPlan = stocking_plan_list.size();

			for (int i = 1; i <= noOfStockingPlan; i++) {

				WebElement stockingPlan = driver.findElement(By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i + "]//h5"));

				String nameOfStockingPlan = stockingPlan.getText();

				LO.print          ("Name of the " + i + " Stocking plan is " + nameOfStockingPlan);
				System.out.println("Name of the " + i + " Stocking plan is " + nameOfStockingPlan);

				if (!nameOfStockingPlan.contains("Demo")) {
					nameMatching = true;
					LO.print          ("For Single Cannel Selection i.e. OwnBook -- there would not be Lombard Demo So "+nameOfStockingPlan+" is not a Lombard Demo, Hence Test Case Passed");
					System.out.println("For Single Cannel Selection i.e. OwnBook -- there would not be Lombard Demo So "+nameOfStockingPlan+" is not a Lombard Demo, Hence Test Case Passed");
				}
			}
		} catch (Exception e) {

		}
		return nameMatching;
	}
	
	
	public boolean remove_stocking_plan_after_finance_activated() throws InterruptedException, ClassNotFoundException {

		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, status_stocking_plan, 30);

		Click.on(driver, status_stocking_plan, 20);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		// Check default status is required

		int statusVerificationCount = 0;

		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_Required, 10);

		elementColor = status_Required.getCssValue("background-color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Default Status for Stocking Plan found OK i.e.Required");
			System.out.println("Default Status for Stocking Plan found OK i.e.Required");
			statusVerificationCount++;

		} else {
			LO.print("Default Status for Stocking Plan found wrong");
			System.err.println("Default Status for Stocking Plan found wrong");

		}

		ExplicitWait.waitForListOfVisibleElements(driver, stocking_plan_list, 20);

		int noOfStockingPlan = stocking_plan_list.size();

		boolean currentStatus = false;

		for (int i = 1; i <= noOfStockingPlan; i++) {

			WebElement nameOfTheStockingPlan = driver.findElement(By.xpath(
					"(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i + "]//h5"));

			LO.print("Name of the " + i + " Stocking plan is " + nameOfTheStockingPlan.getText());
			System.out.println("Name of the " + i + " Stocking plan is " + nameOfTheStockingPlan.getText());

			WebElement statusOfTheStockingPlan = driver
					.findElement(By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)["
							+ i + "]//*[contains(text(),'Status')]//ancestor::div[1]//div/div"));

			LO.print("Status of the " + i + " Stocking plan is " + statusOfTheStockingPlan.getText());
			System.out.println("Status of the " + i + " Stocking plan is " + statusOfTheStockingPlan.getText());

			if (statusOfTheStockingPlan.getText().equalsIgnoreCase("Eligible") == true) {

				LO.print("Choosing " + i + " Stocking plan for this order");
				System.out.println("Choosing " + i + " Stocking plan for this order");

				WebElement addButton = driver.findElement(
						By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i
								+ "]//*[contains(text(),'Add')]"));

				addButton.click();

				Thread.sleep(2000);

				confirm_add.click();

				ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

				// check status Pending Activation

				ExplicitWait.visibleElement(driver, status_pending_activation, 10);

				elementColor = status_pending_activation.getCssValue("background-color");

				if (elementColor.equals("rgba(91, 158, 63, 1)")) {

					LO.print("Status for Stocking Plan after adding - found OK i.e.Pending Activation");
					System.out.println("Status for Stocking Plan after adding - found OK i.e.Pending Activation");
					statusVerificationCount++;

				} else {
					LO.print("Status for Stocking Plan after adding - found Wrong");
					System.err.println("Status for Stocking Plan after adding - found Wrong");

				}

				WebElement statusOfTheStockingPlan2 = driver.findElement(
						By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i
								+ "]//*[contains(text(),'Status')]//ancestor::div[1]//div/div"));

				LO.print("Current Status of the " + i + " Stocking plan after adding is "
						+ statusOfTheStockingPlan2.getText());
				System.out.println("Current Status of the " + i + " Stocking plan after adding is "
						+ statusOfTheStockingPlan2.getText());

				break;

			}
		}

		int i = 0;
		while (i < 10) {

			driver.navigate().refresh();
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

			Click.on(driver, asset_funding, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

			ExplicitWait.visibleElement(driver, stocking_plan_status, 10);

			String statusOfStockingPlan = stocking_plan_status.getText().trim();

			if (statusOfStockingPlan.contains("Activated")) {
				currentStatus = true;
				break;
			}
			Thread.sleep(10000);
			i++;
		}

		// checking status after activating Finance
		ExplicitWait.visibleElement(driver, status_finance_activated, 10);
		elementColor = status_finance_activated.getCssValue("background-color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Status for Stocking Plan after Activating Finance - found OK i.e.Finance Activated");
			System.out.println("Status for Stocking Plan after Activating Finance - found OK i.e.Finance Activated");
			statusVerificationCount++;

		} else {
			LO.print("Status for Stocking Plan after Activating Finance - found Wrong");
			System.err.println("Status for Stocking Plan after Activating Finance - found Wrong");

		}
		
		

		Click.on(driver, stocking_plan_remove, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

		String classOrMethodName = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getName();
		
		if(classOrMethodName.contains("Reject"))
		{
			
			JavaScriptExecutor.click(driver, reject_checkbox);
			
			Click.sendKeys(driver, rejection_reason_input_field, "Test Reject", 20);
			
			Click.on(driver, confirm_remove_button, 10);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

			// checking status after completing funding
			ExplicitWait.visibleElement(driver, status_pending_activation, 10);
			ExplicitWait.visibleElement(driver, stocking_plan_status, 10);
			
			String currentStatusStockingPlan = stocking_plan_status.getText();		
			

			elementColor = status_pending_activation.getCssValue("background-color");

			if (!elementColor.equals("rgba(91, 158, 63, 1)") && currentStatusStockingPlan.equalsIgnoreCase("Rejected")) {

				LO.print("Status for Stocking Plan after Removing Stocking Plan - found OK i.e.Settled");
				System.out.println("Status for Stocking Plan after Removing Stocking Plan - found OK i.e.Settled");
				statusVerificationCount++;

			} else {
				LO.print("Status for Stocking Plan after Removing Stocking Plan - found Wrong");
				System.err.println("Status for Stocking Plan after Removing Stocking Plan - found Wrong");

			}

		}else
		{
		Click.on(driver, confirm_remove_button, 10);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

		// checking status after completing funding
		ExplicitWait.visibleElement(driver, status_pending_activation, 10);
		ExplicitWait.visibleElement(driver, stocking_plan_status, 10);
		
		String currentStatusStockingPlan = stocking_plan_status.getText();		
		

		elementColor = status_pending_activation.getCssValue("background-color");

		if (!elementColor.equals("rgba(91, 158, 63, 1)") && currentStatusStockingPlan.equalsIgnoreCase("Settled")) {

			LO.print("Status for Stocking Plan after Removing Stocking Plan - found OK i.e.Settled");
			System.out.println("Status for Stocking Plan after Removing Stocking Plan - found OK i.e.Settled");
			statusVerificationCount++;

		} else {
			LO.print("Status for Stocking Plan after Removing Stocking Plan - found Wrong");
			System.err.println("Status for Stocking Plan after Removing Stocking Plan - found Wrong");

		}
		}
		if (statusVerificationCount == 4) {
			return true;
		} else {
			return false;
		}

	}


	public boolean stocking_plan() throws InterruptedException {

		Click.on(driver, asset_funding, 30);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		ExplicitWait.visibleElement(driver, status_stocking_plan, 30);

		Click.on(driver, status_stocking_plan, 20);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

		// Check default status is required

		int statusVerificationCount = 0;

		String elementColor = "";

		ExplicitWait.visibleElement(driver, status_Required, 10);

		elementColor = status_Required.getCssValue("background-color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Default Status for Stocking Plan found OK i.e.Required");
			System.out.println("Default Status for Stocking Plan found OK i.e.Required");
			statusVerificationCount++;

		} else {
			LO.print("Default Status for Stocking Plan found wrong");
			System.err.println("Default Status for Stocking Plan found wrong");

		}

		ExplicitWait.waitForListOfVisibleElements(driver, stocking_plan_list, 20);

		int noOfStockingPlan = stocking_plan_list.size();

		boolean currentStatus = false;

		for (int i = 1; i <= noOfStockingPlan; i++) {

			WebElement nameOfTheStockingPlan = driver.findElement(By.xpath(
					"(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i + "]//h5"));

			LO.print("Name of the " + i + " Stocking plan is " + nameOfTheStockingPlan.getText());
			System.out.println("Name of the " + i + " Stocking plan is " + nameOfTheStockingPlan.getText());

			WebElement statusOfTheStockingPlan = driver
					.findElement(By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)["
							+ i + "]//*[contains(text(),'Status')]//ancestor::div[1]//div/div"));

			LO.print("Status of the " + i + " Stocking plan is " + statusOfTheStockingPlan.getText());
			System.out.println("Status of the " + i + " Stocking plan is " + statusOfTheStockingPlan.getText());

			if (statusOfTheStockingPlan.getText().equalsIgnoreCase("Eligible") == true) {

				LO.print("Choosing " + i + " Stocking plan for this order");
				System.out.println("Choosing " + i + " Stocking plan for this order");

				WebElement addButton = driver.findElement(
						By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i
								+ "]//*[contains(text(),'Add')]"));

				addButton.click();

				Thread.sleep(2000);

				confirm_add.click();

				ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 200);

				// check status Pending Activation

				ExplicitWait.visibleElement(driver, status_pending_activation, 10);

				elementColor = status_pending_activation.getCssValue("background-color");

				if (elementColor.equals("rgba(91, 158, 63, 1)")) {

					LO.print("Status for Stocking Plan after adding - found OK i.e.Pending Activation");
					System.out.println("Status for Stocking Plan after adding - found OK i.e.Pending Activation");
					statusVerificationCount++;

				} else {
					LO.print("Status for Stocking Plan after adding - found Wrong");
					System.err.println("Status for Stocking Plan after adding - found Wrong");

				}

				WebElement statusOfTheStockingPlan2 = driver.findElement(
						By.xpath("(//*[@id='accStockingPlanSection']//div//div//app-om-stocking-plan-details)[" + i
								+ "]//*[contains(text(),'Status')]//ancestor::div[1]//div/div"));

				LO.print("Current Status of the " + i + " Stocking plan after adding is "
						+ statusOfTheStockingPlan2.getText());
				System.out.println("Current Status of the " + i + " Stocking plan after adding is "
						+ statusOfTheStockingPlan2.getText());

				break;

			}
		}

		int i = 0;
		while (i < 10) {

			driver.navigate().refresh();
			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

			Click.on(driver, asset_funding, 30);

			ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

			ExplicitWait.visibleElement(driver, stocking_plan_status, 10);

			String statusOfStockingPlan = stocking_plan_status.getText().trim();

			if (statusOfStockingPlan.contains("Activated")) {
				currentStatus = true;
				break;
			}
			Thread.sleep(10000);
			i++;
		}

		// checking status after activating Finance
		ExplicitWait.visibleElement(driver, status_finance_activated, 10);
		elementColor = status_finance_activated.getCssValue("background-color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Status for Stocking Plan after Activating Finance - found OK i.e.Finance Activated");
			System.out.println("Status for Stocking Plan after Activating Finance - found OK i.e.Finance Activated");
			statusVerificationCount++;

		} else {
			LO.print("Status for Stocking Plan after Activating Finance - found Wrong");
			System.err.println("Status for Stocking Plan after Activating Finance - found Wrong");

		}

		Click.on(driver, complete_funding, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

		Click.on(driver, confirm_complete, 10);

		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 300);

		// checking status after completing funding
		ExplicitWait.visibleElement(driver, status_finance_completed, 10);

		elementColor = status_finance_completed.getCssValue("background-color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Status for Stocking Plan after completing funding - found OK i.e.Finance Completed");
			System.out.println("Status for Stocking Plan after completing funding - found OK i.e.Finance Completed");
			statusVerificationCount++;

		} else {
			LO.print("Status for Stocking Plan after completing funding - found Wrong");
			System.err.println("Status for Stocking Plan after completing funding - found Wrong");

		}

		if (statusVerificationCount == 4) {
			return true;
		} else {
			return false;
		}

	}

	public boolean check_status_cash_purchase_pending() throws InterruptedException

	{
		Thread.sleep(2000);
		Click.on(driver, asset_funding, 30);
		ExplicitWait.waitTillLoadingIconDisappears(driver, loading_icon, 120);

		LO.print("Asset Funding Tab Opened");
		System.out.println("Asset Funding Tab Opened");

		String elementColor = status_cash_purchase_pending.getCssValue("background-color");

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print("Default Status for Cash Purchase Verified and found OK i.e.Cash Purchase Pending");
			System.out.println("Default Status for Cash Purchase Verified and found OK i.e.Cash Purchase Pending");
			return true;

		} else {
			LO.print("Default Status for Cash Purchase Verified but found Wrong");
			System.err.println("Default Status for Cash Purchase Verified but found Wrong");
			return false;
		}

	}

	public boolean check_status_cash_purchase_completed() throws InterruptedException

	{

		String elementColor = status_cash_purchase_completed.getCssValue("background-color");
		System.out.println("Element background color: " + elementColor);

		if (elementColor.equals("rgba(91, 158, 63, 1)")) {

			LO.print(
					"Status for Cash Purchase After Completing Cash Purchase Verified and found OK i.e.Cash Purchase Completed");
			System.out.println(
					"Status for Cash Purchase After Completing Cash Purchase Verified and found OK i.e.Cash Purchase Completed");
			return true;

		} else {
			LO.print("Status for Cash Purchase After Completing Cash Purchase Verified but found Wrong");
			System.err.println("Status for Cash Purchase After Completing Cash Purchase Verified but found Wrong");
			return false;
		}

	}

}
