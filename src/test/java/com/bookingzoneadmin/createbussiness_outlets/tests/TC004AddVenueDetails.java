package com.bookingzoneadmin.createbussiness_outlets.tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bookingzoneadmin.pages.AddVenueDetailsPage;
import com.bookingzoneadmin.pages.BaseClass;
import com.bookingzoneadmin.pages.BusinessHomePage;
import com.bookingzoneadmin.pages.OutletDetailsPage;
import com.bookingzoneadmin.pages.OutletListPage;
import com.bookingzoneadmin.utils.ExtentReportManager;
import com.bookingzoneadmin.utils.UtilityClass;

/**
 * Test class to verify adding venue details functionality.
 */
public class TC004AddVenueDetails extends BaseClass {

	private BusinessHomePage businessHomePage;
	private OutletListPage outletList;
	private OutletDetailsPage outletDetails;
	private AddVenueDetailsPage addVenueDetails;

	// Test data variables
	private String serviceCategory;
	private String venueType;
	private String serviceDescription;
	private String totalCount;
	private String from;
	private String to;
	private String bookingType;
	@BeforeClass
	public void initializeTest(ITestContext context) throws InterruptedException, EncryptedDocumentException, IOException {

		businessHomePage = new BusinessHomePage(driver);
		outletList = new OutletListPage(driver);
		outletDetails = new OutletDetailsPage(driver);
		addVenueDetails = new AddVenueDetailsPage(driver);

		//Test data
		fetchTestData();
	}


	private void fetchTestData() throws IOException {
		serviceCategory = UtilityClass.getDataFromEs(1, "Service Category", "Venue_Data");
		venueType = UtilityClass.getDataFromEs(1, "Venue Type", "Venue_Data");
		serviceDescription = UtilityClass.getDataFromEs(1, "Service Description", "Venue_Data");
		totalCount = UtilityClass.getDataFromEs(1, "Total Count", "Venue_Data");
		from = UtilityClass.getDataFromEs(1, "From", "Venue_Data");
		to = UtilityClass.getDataFromEs(1, "To", "Venue_Data");
		bookingType=UtilityClass.getDataFromEs(1, "Booking Type", "Venue_Data");
	}
	/**
	 * Test case to add venue details.
	 *
	 * @throws IOException            If an I/O error occurs.
	 * @throws InterruptedException   If the thread is interrupted.
	 */
	@Test(testName = "testAddVenueDetails")
	public void testAddVenueDetails() throws IOException, InterruptedException {
		try {

			navigateToVenueDetailsPage();
			verifyNoServiceAdded();
			enterVenueDetails();
			verifyVenueDetailsSaved();

		} catch (Exception e) {
			handleException(e);
		}
	}

	private void navigateToVenueDetailsPage() throws InterruptedException {
		businessHomePage.clickOutletsButton();
		outletList.clickArrow();
		outletDetails.clickVenueDetail();
	}

	private void verifyNoServiceAdded() {
		if (driver.getPageSource().contains("No Service Added. Please add a service first.")) {
			handleFailure("No Service Added. Please add a service first.");
		}
	}

	private void enterVenueDetails() throws IOException, InterruptedException {
		outletDetails.clickAddVenueDetails();
		addVenueDetails.clickEditBtnOfVenueCategory();
		addVenueDetails.enterServiceCategory(serviceCategory);
		addVenueDetails.enterVenueType(venueType);
		addVenueDetails.enterServiceDescription(serviceDescription);
		ExtentReportManager.logInfo("Venue Category Details Entered");

		addVenueDetails.clickEditBtnOfTotalCount();
		addVenueDetails.selectVenue(venueType);
		addVenueDetails.clearTotalCount();
		addVenueDetails.enterTotalCount(totalCount);
		ExtentReportManager.logInfo("Total Count Details Entered");

		addVenueDetails.clickEditBtnOfBookingType();
		addVenueDetails.selectService(serviceCategory);
		addVenueDetails.selectVenueType(venueType);
		addVenueDetails.enterFrom(from);
		addVenueDetails.enterTo(to);
		addVenueDetails.selectServiceDescription(serviceDescription);
		addVenueDetails.selectBookingType(bookingType);
		ExtentReportManager.logInfo("Booking Type Details Entered...!");

		addVenueDetails.clickSaveButton();

	}

	private void verifyVenueDetailsSaved() {
		if (driver.getPageSource().contains("Successfully Added")) {
			ExtentReportManager.logPass("Venue details added Successfully..!");
		} else if (driver.getPageSource().contains("Required")) {
			handleFailure("Some fields are required. Please fill required fields.");
		}
	}

	// Method to handle failure cases
	private void handleFailure(String message) {
		ExtentReportManager.logFail("Venue Details is not added due to " + message + "...!");
		System.out.println("Venue Details is not added due to " + message + "...!");
		Assert.fail(message);
	}

	/**
	 * Handles exceptions by printing stack trace and logging failure in extent reports.
	 *
	 * @param e The exception to handle.
	 */
	private void handleException(Exception e) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		System.out.println("Issue cause is: " + getCause);
		ExtentReportManager.logFail("Test case failed due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
