package com.bookingzoneadmin.createbussiness_outlets.tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bookingzoneadmin.pages.AdminHomePage;
import com.bookingzoneadmin.pages.BaseClass;
import com.bookingzoneadmin.pages.BussinessListPage;
import com.bookingzoneadmin.pages.CreateNewBusinessPage;
import com.bookingzoneadmin.pages.CreatePasswordPage;
import com.bookingzoneadmin.pages.LogInPage;
import com.bookingzoneadmin.pages.LogoutPage;
import com.bookingzoneadmin.pages.YopMailPage;
import com.bookingzoneadmin.utils.CreateBookingCommonActions;
import com.bookingzoneadmin.utils.ExtentReportManager;
import com.bookingzoneadmin.utils.UtilityClass;

//Test class for creating a new business in the admin portal.

public class TC001CreateBussiness extends BaseClass
{
	private LogInPage login;
	private AdminHomePage adminHome;
	private BussinessListPage businessList;
	private CreateNewBusinessPage createNewBusiness;
	private YopMailPage yopmail;
	private CreatePasswordPage createPassword;
	private LogoutPage logout;

	//Test Data variables 
	private String businessname;
	private String firstname;
	private String lastname;
	private String email;
	private String flatno;
	private String city;
	private String country;
	private String state;
	private String zip;
	private String bussinesspassword;
	private String bussinessusername;

	/**
	 * Setup method to initialize the browser and page objects.
	 * 
	 * @param context The TestNG context
	 * @throws InterruptedException
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	@BeforeClass
	public void initializeTest (ITestContext Context) throws InterruptedException, EncryptedDocumentException, IOException 
	{
		adminHome=new AdminHomePage(driver);
		businessList=new BussinessListPage(driver);
		createNewBusiness=new CreateNewBusinessPage(driver);
		yopmail=new YopMailPage(driver);
		createPassword=new CreatePasswordPage(driver);
		logout=new LogoutPage(driver);
		login =new LogInPage(driver);

		fetchBussinessData();

	}

	public void fetchBussinessData() throws IOException 
	{

		businessname=UtilityClass.getDataFromEs(1,"Business Name ", "Business_Data" );
		firstname=UtilityClass.getDataFromEs(1,"First Name" , "Business_Data");
		lastname=UtilityClass.getDataFromEs(1,"Last Name", "Business_Data");
		email=UtilityClass.getDataFromEs(1,"Email", "Business_Data");
		flatno=UtilityClass.getDataFromEs(1,"Flat No", "Business_Data");
		city=UtilityClass.getDataFromEs(1,"City", "Business_Data");
		country=UtilityClass.getDataFromEs(1,"Country", "Business_Data");
		state=UtilityClass.getDataFromEs(1,"State", "Business_Data");
		zip=UtilityClass.getDataFromEs(1,"Zip", "Business_Data");
		bussinesspassword=UtilityClass.getDataFromEs(1,"Bussiness Password", "Business_Data");
		bussinessusername=UtilityClass.getDataFromEs(1, "Email", "Business_Data");

	}

	/**
	 * Test method to create a new business in the admin portal.
	 * 
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	@Test (testName = "testBusinessCreation")
	public void testBusinessCreation() throws EncryptedDocumentException, IOException, InterruptedException, AWTException 
	{ 
		try {

			navigateToBusinessCreation();
			enterBusinessDetails();
			createNewBusiness.clickCreateBusiness();

			handleBusinessCreationResult();

			createNewBusiness.clickNo();

			verifyEmailAndCreatePassword();
			loginWithNewBusinessCredentials();
			loginAsAdminUser();
			//handleWindowPopup(0);

		}
		catch(Exception e) {
			handleException(e);
		}
	}

	private void navigateToBusinessCreation() throws InterruptedException {
		adminHome.clickBusinessesLink();
		businessList.clickAddBusinessButton();
	}

	/**
	 * Method to enter business details.
	 * @throws InterruptedException 
	 */
	private void enterBusinessDetails() throws EncryptedDocumentException, IOException, InterruptedException {

		createNewBusiness.enterBusinessName(businessname);
		createNewBusiness.enterFirstName(firstname);
		createNewBusiness.enterLastName(lastname);
		createNewBusiness.enterEmail(email);
		createNewBusiness.enterFlatNo(flatno);
		createNewBusiness.enterCity(city);
		createNewBusiness.selectCountry(country);
		createNewBusiness.enterState(state);
		createNewBusiness.enterZip(zip);

	}

	/**
	 * Method to handle the result of business creation.
	 */
	private void handleBusinessCreationResult() {
		String pageSource = driver.getPageSource();

		if (pageSource.contains("Successfully Created")) {
			ExtentReportManager.logPass("Business has been created successfully...!");
		} else {
			String message = driver.getPageSource();
			handleFailureBasedOnError(message);
		}
	}

	/**
	 * Method to handle different error cases in business creation.
	 */
	private void handleFailureBasedOnError(String pageSource) {
		if (pageSource.contains("First Name is required")) {
			handleFailure("First name is required. Please provide a valid First name.");
		} else if (pageSource.contains("Last Name is required")) {
			handleFailure("Last name is required. Please provide a valid Last name.");
		} else if (pageSource.contains("Email is required")) {
			handleFailure("Email is required. Please provide a valid Email.");
		} else if (pageSource.contains("Flat/House no. is required")) {
			handleFailure("Flat/House no is required. Please provide a valid Flat/House no.");
		} else if (pageSource.contains("City is required")) {
			handleFailure("City is required. Please provide a valid City name.");
		} else if (pageSource.contains("State is required")) {
			handleFailure("State is required. Please provide a valid State.");
		} else if (pageSource.contains("Zip Code is required")) {
			handleFailure("Zip Code is required. Please provide a valid Zip Code.");
		} else if (pageSource.contains("Business name is required")) {
			handleFailure("Business name is required. Please provide a valid Business name.");
		} else if (pageSource.contains("Business already registered, please try with other email")) {
			handleFailure("Business already registered, please try with other email");
		}
	}

	/**
	 * Method to verify email and create a password for the new business.
	 */
	private void verifyEmailAndCreatePassword() throws InterruptedException, IOException {
		yopmail.navigateToYopMail();
		yopmail.enterLoginEmail(email);
		yopmail.clickMailArrow();

		driver.switchTo().frame("ifmail");
		createNewBusiness.clickVerificationButton();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().parentFrame();

		handleWindowPopup(2);
		createPasswordForNewBusiness();

	}

	/**
	 * Handle window popup during email verification.
	 */
	private void handleWindowPopup(int index) {
		Set<String> allIds = driver.getWindowHandles();
		ArrayList<String> ar = new ArrayList<String>(allIds);
		String windowPopId = ar.get(index);
		driver.switchTo().window(windowPopId);
	}

	/**
	 * Method to create a password for the newly created business.
	 * @throws InterruptedException 
	 */
	private void createPasswordForNewBusiness() throws EncryptedDocumentException, IOException, InterruptedException {
		//Create password for created Business acount
		createPassword.enterPassword(bussinesspassword);
		createPassword.enterConfirmPassword(bussinesspassword);
		createPassword.clickCreatePassword();

		logoutAndVerify();
	}

	/**
	 * Method to log out and verify the new business login.
	 * @throws InterruptedException 
	 */
	private void logoutAndVerify() throws EncryptedDocumentException, IOException, InterruptedException {
		logout.clickProfileIcon();
		logout.clickLogoutButton();
		ExtentReportManager.logInfo("Existing user logout successfully...!");
	}

	/**
	 * Method to login with new business credentials.
	 */
	private void loginWithNewBusinessCredentials() throws InterruptedException, IOException {
		login.clickBusinessRadioButton();
		CreateBookingCommonActions.performLogin("business", driver, bussinessusername, bussinesspassword);
		ExtentReportManager.getTest().info("Login with newly created business credentials");

		verifyLoginSuccess();

	}

	/**
	 * Method to login as admin user as only admin user can create outlet.
	 */
	private void loginAsAdminUser() throws InterruptedException, IOException {
		logoutAndVerify();
		CreateBookingCommonActions.performLogin("admin", driver, UtilityClass.getDataFromPF("Admin_UserName"),UtilityClass.getDataFromPF("Admin_Password"));
		ExtentReportManager.getTest().info("Login admin user.");

	}

	/**
	 * Method to verify login success.
	 */
	private void verifyLoginSuccess() {
		WebElement successMessage = driver.findElement(By.xpath("//img[@alt='Logo']"));
		boolean isLoginSuccessful = (successMessage != null) && successMessage.isDisplayed();

		if (isLoginSuccessful) {
			ExtentReportManager.logPass("User login for newly created Business account.");
		} else {
			handleFailure("User login failed.");
		}
	}


	/**
	 * Method to handle test failures.
	 * 
	 * @param message The failure message
	 */
	private void handleFailure(String message) {
		ExtentReportManager.logFail("Business is not created due to " + message + "...!");
		System.out.println("Business is not created due to " + message + "...!");
		Assert.fail(message);
	}

	/**
	 * Method to handle exceptions.
	 * 
	 * @param e The exception object
	 */
	private void handleException(Exception e) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		System.out.println("Issue cause is: " + getCause);
		ExtentReportManager.logFail("testBusinessCreation Test case failed due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}

}


