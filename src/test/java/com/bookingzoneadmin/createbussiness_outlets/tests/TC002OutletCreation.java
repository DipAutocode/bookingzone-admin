package com.bookingzoneadmin.createbussiness_outlets.tests;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bookingzoneadmin.pages.AdminHomePage;
import com.bookingzoneadmin.pages.BaseClass;
import com.bookingzoneadmin.pages.BusinessDetailsPage;
import com.bookingzoneadmin.pages.BussinessListPage;
import com.bookingzoneadmin.pages.CreateOutlet;
import com.bookingzoneadmin.utils.ExtentReportManager;
import com.bookingzoneadmin.utils.UtilityClass;

// Class to test the functionality of creating an outlet
public class TC002OutletCreation extends BaseClass {

	// Declaring page objects for different pages
	private AdminHomePage adminHomePage;
	private BussinessListPage businessList;
	private BusinessDetailsPage businessDetails;
	private CreateOutlet createOutlet;

	// Test data variables
	private String outletName;
	private String outletEmail;
	private String flatNo;
	private String state;
	private String city;
	private String zip;
	private String region;
	private String businessEmail;
	private String userName;
	private String password;
	private String customerCode;
	private String authHeader;
	private String appleMerchantId;
	private String googlePayId;


	// Method to set up the test environment before executing test cases
	@BeforeClass
	public void setUp(ITestContext context) throws InterruptedException, EncryptedDocumentException, IOException {

		adminHomePage = new AdminHomePage(driver);
		businessList=new BussinessListPage(driver);
		businessDetails=new BusinessDetailsPage(driver);
		createOutlet = new CreateOutlet(driver);

		fetchOutletData();
	}

	// Method to fetch and initialize outlet data
	private void fetchOutletData() throws EncryptedDocumentException, IOException {
		outletName = UtilityClass.getDataFromEs(1, "Outlet Name", "Outlet_Data");
		outletEmail = UtilityClass.getDataFromEs(1, "Email", "Business_Data");
		flatNo = UtilityClass.getDataFromEs(1, "Flat No", "Outlet_Data");
		state = UtilityClass.getDataFromEs(1, "State", "Outlet_Data");
		city = UtilityClass.getDataFromEs(1, "City", "Outlet_Data");
		zip = UtilityClass.getDataFromEs(1, "Zip", "Outlet_Data");
		region = UtilityClass.getDataFromEs(1, "Region", "Outlet_Data");
		businessEmail = UtilityClass.getDataFromEs(1, "Email", "Business_Data");
		userName=UtilityClass.getDataFromEs(1,"username", "Add Payment Details");
		password=UtilityClass.getDataFromEs(1,"password", "Add Payment Details");
		customerCode = UtilityClass.getDataFromEs(1, "custumerCode", "Add Payment Details");
		authHeader = UtilityClass.getDataFromEs(1, "authHeader", "Add Payment Details");
		appleMerchantId = UtilityClass.getDataFromEs(1, "appleMerchantId", "Add Payment Details");
		googlePayId = UtilityClass.getDataFromEs(1, "googlePayId", "Add Payment Details");
	}

	// Method to enter outlet details
	private void enterOutletDetails() throws InterruptedException {
		createOutlet.enterOutletName(outletName);
		createOutlet.enterOutletEmail(outletEmail);
		createOutlet.enterFlatNo(flatNo);
		createOutlet.enterStateName(state);
		createOutlet.selectState(state, driver);
		createOutlet.selectCity(driver, city);
		createOutlet.enterZipCode(zip);
		createOutlet.selectRegion(region);
	}

	// Test method to create an outlet
	@Test(testName = "testOutletCreation")
	public void testOutletCreation() throws EncryptedDocumentException, IOException, InterruptedException, InvocationTargetException {
		try {

			adminHomePage.clickBusinessesLink();
			searchForBusiness();
			addNewOutlet();
			validateOutletCreation();

		} catch (Exception e) {
			handleException(e);
		}
	}

	// Method to search for a business
	private void searchForBusiness() throws IOException, InterruptedException {
		businessList.enterMail(businessEmail);
		businessList.clickSearchBtn();
		businessList.clickBussinessArrow();

	}
	// Method to add a new outlet
	private void addNewOutlet() throws IOException, InterruptedException {
		businessDetails.clickAddNewOutletButton();
		enterOutletDetails();
		updatePaymentDetails();
		createOutlet.clickCreateOutlet();
		createOutlet.clickNo();
		Thread.sleep(2000);
	}


	// Update payment details for the outlet
	private void updatePaymentDetails() throws InterruptedException {
		createOutlet.enterCustomerCode(customerCode);
		createOutlet.enterUserName(userName);
		createOutlet.enterPassword(password);
		createOutlet.enterAuthHeader(authHeader);
		createOutlet.enterAppleMerchantId(appleMerchantId);
		createOutlet.enterGooglePayId(googlePayId);
	}


	// Method to validate outlet creation
	private void validateOutletCreation() {
		String pageSource = driver.getPageSource();
		if (pageSource.contains("Successfully Created")) {
			ExtentReportManager.logPass("Outlet has been successfully created..!");
		} else {
			String message = driver.getPageSource();
			checkFailureMessages(message);
		}
	}

	// Method to check for specific failure messages
	private void checkFailureMessages(String pageSource) {
		if (pageSource.contains("Name is required")) {
			handleFailure("Name is required. Please provide a valid Name.");
		} else if (pageSource.contains("Flat/House no. is required")) {
			handleFailure("Flat/House no. is required. Please provide a valid Flat/House no..");
		} else if (pageSource.contains("City is required")) {
			handleFailure("City is required. Please provide a valid City.");
		} else if (pageSource.contains("State is required")) {
			handleFailure("State is required. Please provide a valid State.");
		} else if (pageSource.contains("Zip Code is required")) {
			handleFailure("Zip Code is required. Please provide a valid Zip Code.");
		} else if (pageSource.contains("Region is Required")) {
			handleFailure("Region is required. Please provide a valid Region.");
		} 
	}

	// Method to handle failure cases
	private void handleFailure(String message) {
		ExtentReportManager.logFail("Outlet is not created due to " + message + "...!");
		System.out.println("Outlet is not created due to " + message + "...!");
		Assert.fail(message);
	}

	// Method to handle exceptions
	private void handleException(Exception e) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		System.out.println("Issue cause is: " + getCause);
		ExtentReportManager.logFail("testOutletCreation Test case is Fail due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
