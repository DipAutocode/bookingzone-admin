package com.bookingzoneadmin.createbussiness_outlets.tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bookingzoneadmin.pages.BaseClass;
import com.bookingzoneadmin.pages.BusinessHomePage;
import com.bookingzoneadmin.pages.OutletDetailsPage;
import com.bookingzoneadmin.pages.OutletListPage;
import com.bookingzoneadmin.utils.ExtentReportManager;

/**
 * Test class to verify adding service category functionality.
 */
public class TC003AddServiceCategory extends BaseClass {

	private BusinessHomePage businessHomePage;
	private OutletListPage outletList;
	private OutletDetailsPage outletDetails;

	@BeforeClass
	public void initializeTest(ITestContext context) throws InterruptedException, EncryptedDocumentException, IOException {

		businessHomePage = new BusinessHomePage(driver);
		outletList = new OutletListPage(driver);
		outletDetails = new OutletDetailsPage(driver);
	}

	/**
	 * Test case to add a service category.
	 *
	 * @throws IOException            If an I/O error occurs.
	 * @throws InterruptedException   If the thread is interrupted.
	 */
	@Test(testName = "testAddServiceCategory")
	public void testAddServiceCategory() throws IOException, InterruptedException {
		try {
			navigateToOutletPage();
			addServiceCategory();
			verifyServiceCategorySaved();
		} catch (Exception e) {
			handleException(e);
		}
	}

	private void navigateToOutletPage() throws InterruptedException {
		businessHomePage.clickOutletsButton();
		outletList.clickArrow();
	}

	private void addServiceCategory() throws InterruptedException {
		outletDetails.clickAddServiceButton();
		outletDetails.clickEntRadio();
		outletDetails.clickConfirmButton();
		outletDetails.selectUberOption();
		outletDetails.selectStatusOption();
		outletDetails.clickSaveButton();

	}

	private void verifyServiceCategorySaved() {
		if (driver.getPageSource().contains("Service configuration has been successfully saved")) {
			String successMessage = "Service category successfully saved..!";
			ExtentReportManager.logPass(successMessage);
		} else {
			ExtentReportManager.logFail("Service category not saved.");
		}
	}

	private void handleException(Exception e) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		System.out.println("Issue cause is: " + getCause);
		ExtentReportManager.logFail("testAddServiceCategory test case failed due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
