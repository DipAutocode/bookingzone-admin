package com.bookingzoneadmin.FullPayment;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bookingzoneadmin.pages.BaseClass;
import com.bookingzoneadmin.pages.BookingDetailsPage;
import com.bookingzoneadmin.pages.BookingSummaryPage;
import com.bookingzoneadmin.pages.BusinessHomePage;
import com.bookingzoneadmin.pages.CalendarReservationPage;
import com.bookingzoneadmin.pages.CreateBookingStepperDetailsPage;
import com.bookingzoneadmin.pages.OrderSummaryPage;
import com.bookingzoneadmin.pages.SearchBookingPage;
import com.bookingzoneadmin.pages.StepperPaymentPage;
import com.bookingzoneadmin.pages.StepperSummaryPage;
import com.bookingzoneadmin.pages.YopMailPage;
import com.bookingzoneadmin.utils.BookingActions;
import com.bookingzoneadmin.utils.BookingIdStore;
import com.bookingzoneadmin.utils.EditBookingAction;
import com.bookingzoneadmin.utils.ExtentReportManager;
import com.bookingzoneadmin.utils.PaymentActions;
import com.bookingzoneadmin.utils.UtilityClass;

public class TC005FullPayVia_PaymentURL extends BaseClass {


	BookingActions bookingActions;
	PaymentActions paymentActions;
	StepperSummaryPage stepperSummaryPage;
	YopMailPage yopmail;
	OrderSummaryPage orderSummaryPage;
	BookingSummaryPage bookingSummaryPage;
	StepperPaymentPage stepperPaymentPage;
	BookingDetailsPage bookingDetailsPage;
	EditBookingAction editBookingAction;
	CalendarReservationPage calendarReservationPage;
	SearchBookingPage searchBookingPage;

	// Test data variables
	private String outletName;
	private String bookingName;
	private String customerEmail;
	private String planName;
	private String hour;
	private String minute;
	private String noon;
	private String cardNumber;
	private String expiryDate;
	private String cvc;
	private String status;

	@BeforeClass
	public void initializeTest(ITestContext context) throws InterruptedException, IOException {
		stepperSummaryPage = new StepperSummaryPage(driver);
		bookingSummaryPage=new BookingSummaryPage(driver);
		orderSummaryPage =new OrderSummaryPage(driver);
		stepperPaymentPage=new StepperPaymentPage(driver);
		bookingDetailsPage=new BookingDetailsPage(driver);
		calendarReservationPage=new CalendarReservationPage (driver);
		searchBookingPage=new SearchBookingPage (driver);

		bookingActions = new BookingActions(
				new BusinessHomePage(driver), 
				new CalendarReservationPage(driver),
				new CreateBookingStepperDetailsPage(driver)
				);

		paymentActions = new PaymentActions(
				stepperPaymentPage,
				stepperSummaryPage, 
				new YopMailPage(driver),
				orderSummaryPage
				);

		editBookingAction=new EditBookingAction(
				stepperSummaryPage,
				calendarReservationPage, 
				searchBookingPage
				);

		fetchBookingAndPaymentData();
	}

	private void fetchBookingAndPaymentData() throws IOException {
		outletName = UtilityClass.getDataFromEs(1, "Outlet Name", "Create_Booking");
		bookingName = UtilityClass.getDataFromEs(1, "Booking Name", "Create_Booking");
		// Call utility method for timestamp
		String timeStamp = UtilityClass.getFormattedTimestamp();
		// Add class name + readable timestamp for uniqueness
		bookingName = bookingName + "_" + this.getClass().getSimpleName() + "_" + timeStamp;

		customerEmail = UtilityClass.getDataFromEs(1, "Email", "Create_Booking");
		planName = UtilityClass.getDataFromEs(5, "Plan Name", "Create_Plans");
		hour=UtilityClass.getDataFromEs(5, "Slot1StartTimehour", "Create_Plans");	
		minute=UtilityClass.getDataFromEs(5, "StartMinute", "Create_Plans");
		noon=UtilityClass.getDataFromEs(5, "StartTimeAMPM", "Create_Plans");

		cardNumber = UtilityClass.getDataFromEs(1, "Card No", "Create_Booking");
		expiryDate = UtilityClass.getDataFromEs(1, "Expiry Date", "Create_Booking");
		cvc = UtilityClass.getDataFromEs(1, "CVC", "Create_Booking");
		status=UtilityClass.getDataFromEs(1, "Status_Successful", "EditBooking");
	}

	@Test(testName = "testCreateBookingWithPaymentURL", priority = 1)
	public void testCreateBookingWithPaymentURL() throws IOException, InterruptedException {
		try {
			bookingActions.selectBookingOutlet(outletName);
			bookingActions.configure_EnterBookingDetails(bookingName, customerEmail);
			bookingActions.enterBookingTime(hour, minute, noon);
			bookingActions.proceedWithBookingDetails(planName);
			paymentActions.verifyTotal_SubTotalAmount();
			linkPayment();

			String bookingId = bookingSummaryPage.getBookingId();
			// Store the bookingId using the Singleton class
			BookingIdStore.setBookingId(bookingId);
			ExtentReportManager.logInfo("Booking created successfully with ID: " + bookingId);

			verifyBookingSuccessOnPWASide();
		} catch (Exception e) {
			handleException(e, "testCreateBookingWithPaymentURL");
		}
	}

	@Test(testName = "testVerifyBookingStatusInAdminPanel", priority = 2, dependsOnMethods = "com.bookingzone.admin.FullPayment.TC004FullEmailPayment.testCreateBookingWithPaymentURL")
	public void testVerifyBookingStatusInAdminPanel() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (stepperSummaryPage.isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Verifying booking with ID: " + bookingId);
				verifyBookingSuccessOnAdminSide(bookingId);
			}

		} catch (Exception e) {
			handleException(e, "testVerifyBookingStatusInAdminPanel");
		}
	}

	private void linkPayment() throws InterruptedException 
	{
		Thread.sleep(3000);
		stepperSummaryPage.clickDetailsButton();
		bookingDetailsPage.copyPaymentUrlAndHitOnNewTab();
		orderSummaryPage.verifyPayableAmountOnEmailSendedLink(1);

		// Switch to iframe for entering card details
		driver.switchTo().frame("__teConnectSecureFrame");
		// Enter card details
		stepperPaymentPage.enterCardNumber(cardNumber);
		stepperPaymentPage.enterExpiryDate(expiryDate);
		stepperPaymentPage.enterCVC(cvc);

		// Switch back to the default content
		driver.switchTo().defaultContent();
		orderSummaryPage.clickPayButton();
		Thread.sleep(5000);
	}

	private void verifyBookingSuccessOnAdminSide(String bookingId) throws IOException, InterruptedException {

		orderSummaryPage.handleWindowPopUp(0);// again switch focus to the Admin site
		bookingDetailsPage.clickCrossBtn();
		stepperSummaryPage.clickSubmitButton();
		editBookingAction.searchBooking(bookingId);
		stepperSummaryPage.clickSummaryButton();
		stepperSummaryPage.verifyBookingStatus(status);
		stepperSummaryPage.clickSubmitButton();
	}

	private void verifyBookingSuccessOnPWASide() throws IOException, InterruptedException {
		if (driver.getPageSource().contains("There is some error in receiver's bank or outlet is not taking online payment.")) {
			ExtentReportManager.logFail("There is some error in receiver's bank or outlet is not taking online payment.");
			Assert.fail("There is some error in receiver's bank or outlet is not taking online payment.");
		} else {
			orderSummaryPage.verifyBookingStatus();
			ExtentReportManager.logInfo("Booking created successfully by Email payment.");
		}
	}

	private void handleException(Exception e, String testCaseName) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		ExtentReportManager.logFail(testCaseName + " test case failed due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
