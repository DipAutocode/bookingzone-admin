package com.bookingzoneadmin.addADDONS.tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bookingzoneadmin.pages.BaseClass;
import com.bookingzoneadmin.pages.BusinessHomePage;
import com.bookingzoneadmin.pages.CreateAddonsPage;
import com.bookingzoneadmin.pages.OutletDetailsPage;
import com.bookingzoneadmin.pages.OutletListPage;
import com.bookingzoneadmin.utils.AddAddonsCommonActions;
import com.bookingzoneadmin.utils.ExtentReportManager;
import com.bookingzoneadmin.utils.UtilityClass;


/**
 * Test class to verify adding ADD-ONS without tax functionality.
 */
public class TC001AddAddonsWithoutTax extends BaseClass {

	private BusinessHomePage businessHomePage;
	private OutletListPage outletList;
	private OutletDetailsPage outletDetails;
	private CreateAddonsPage createAddon;

	//Test Data variables 
	private String addonname;
	private String tagname;
	private String quantityinstock;
	private String baseprice;
	private String flattax;
	private String pertax;

	@BeforeClass
	public void initializeTest(ITestContext context) throws InterruptedException, EncryptedDocumentException, IOException {

		businessHomePage = new BusinessHomePage(driver);
		outletList = new OutletListPage(driver);
		outletDetails = new OutletDetailsPage(driver);
		createAddon=new CreateAddonsPage(driver);
		
		fetchAddonDeatilsData();
	}

	public void fetchAddonDeatilsData() throws IOException 
	{
		addonname=UtilityClass.getDataFromEs(1,"Addon Name", "ADD-ONS");
		tagname=UtilityClass.getDataFromEs(1, "Tag Name", "ADD-ONS");
		quantityinstock=UtilityClass.getDataFromEs(1,"Quantity In Stock", "ADD-ONS");
		baseprice=UtilityClass.getDataFromEs(1,"Base Price", "ADD-ONS");
		flattax=null;
		pertax=null;

	}

	/**
	 * Test case to add a ADD-ONS.
	 *
	 * @throws IOException            If an I/O error occurs.
	 * @throws InterruptedException   If the thread is interrupted.
	 */
	@Test(testName = "testAddAddonsWithoutTax")
	public void testAddAddonsWithoutTax() throws IOException, InterruptedException {
		try {

			businessHomePage.clickOutletsButton();

			AddAddonsCommonActions.navigateToAddons(outletList, outletDetails, true);
			AddAddonsCommonActions.enterAddonDetails(createAddon, addonname, tagname, quantityinstock, baseprice, flattax, pertax);

			verifySuccessOrHandleFailure();

		} catch (Exception e) {
			handleException(e);
		}
	}

	private void verifySuccessOrHandleFailure() {
		String pageSource = driver.getPageSource();
		if (pageSource.contains("Successfully Added")) {
			ExtentReportManager.logPass("ADD-ON Successfully Added..!");
		} else {
			String message = driver.getPageSource();
			handleFailureBasedOnPageSource(message);
		}
	}

	private void handleFailureBasedOnPageSource(String pageSource) {
		if (pageSource.contains("Name is required")) {
			handleFailure("Name is required. Please provide a valid name.");
		} else if (pageSource.contains("Tag is required")) {
			handleFailure("Tag is required. Please provide a valid Tag.");
		} else if (pageSource.contains("Quantity is required")) {
			handleFailure("Quantity is required. Please provide a valid quantity.");
		} else if (pageSource.contains("Price is required")) {
			handleFailure("Price is required. Please provide a valid price.");
		}
	}

	/**
	 * Method to handle test failures.
	 * 
	 * @param message The failure message
	 */
	private void handleFailure(String message) {
		ExtentReportManager.logFail("Addon is not added due to " + message + "...!");
		System.out.println("Addon is not added due to " + message + "...!");
		Assert.fail(message);
	}

	private void handleException(Exception e) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		System.out.println("Issue cause is: " + getCause);
		ExtentReportManager.logFail("testAddAddonsWithoutTax test case failed due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
	
	
	
}
