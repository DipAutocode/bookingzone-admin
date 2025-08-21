package com.bookingzoneadmin.FullPayment;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bookingzoneadmin.pages.BaseClass;
import com.bookingzoneadmin.pages.BookingDetailsPage;
import com.bookingzoneadmin.pages.BusinessHomePage;
import com.bookingzoneadmin.pages.CalendarReservationPage;
import com.bookingzoneadmin.pages.CreateBookingStepperDetailsPage;
import com.bookingzoneadmin.pages.SearchBookingPage;
import com.bookingzoneadmin.pages.StepperEditPage;
import com.bookingzoneadmin.pages.StepperPaymentPage;
import com.bookingzoneadmin.pages.StepperSummaryPage;
import com.bookingzoneadmin.utils.BookingActions;
import com.bookingzoneadmin.utils.BookingIdStore;
import com.bookingzoneadmin.utils.EditBookingAction;
import com.bookingzoneadmin.utils.ExtentReportManager;
import com.bookingzoneadmin.utils.PaymentActions;
import com.bookingzoneadmin.utils.UtilityClass;

public class TC003EditFullOtherPayBooking extends BaseClass {


	BookingActions bookingActions;
	PaymentActions paymentActions;
	StepperSummaryPage stepperSummaryPage;
	SearchBookingPage searchBookingPage;
	EditBookingAction editBookingAction;
	StepperEditPage stepperEditPage;
	CreateBookingStepperDetailsPage createBookingStepperDetailsPage;
	StepperPaymentPage stepperPaymentPage;
	BookingDetailsPage bookingDetailsPage;

	// Test data variables
	private String statusDone;
	private String statusSuccess;
	private String paymentMethod;
	private String paymentMethod1;
	
	// Class-level variables to store paid and balance amounts
	private double paidAmount;
	private double balanceAmount;
	@BeforeClass
	public void initializeTest(ITestContext context) throws InterruptedException, IOException {

		stepperSummaryPage = new StepperSummaryPage(driver);
		searchBookingPage = new SearchBookingPage(driver);
		stepperEditPage = new StepperEditPage(driver);
		createBookingStepperDetailsPage = new CreateBookingStepperDetailsPage(driver);
		stepperPaymentPage=new StepperPaymentPage(driver);
		bookingDetailsPage=new BookingDetailsPage(driver);

		bookingActions = new BookingActions(
				new BusinessHomePage(driver), 
				new CalendarReservationPage(driver),
				new CreateBookingStepperDetailsPage(driver)
				);

		paymentActions = new PaymentActions(
				stepperPaymentPage,
				stepperSummaryPage, null, null
				);

		editBookingAction = new EditBookingAction(
				stepperSummaryPage,
				new CalendarReservationPage(driver),
				searchBookingPage);

		fetchBookingAndPaymentData();
	}

	private void fetchBookingAndPaymentData() throws IOException {
		statusDone=UtilityClass.getDataFromEs(1, "Status_Done", "EditBooking");
		statusSuccess = UtilityClass.getDataFromEs(1, "Status_Successful", "EditBooking");
		paymentMethod = UtilityClass.getDataFromEs(1, "Cash Pay", "Create_Booking");
		paymentMethod1 = UtilityClass.getDataFromEs(1, "Other Pay", "Create_Booking");
	}


	@Test(testName = "testChangeBookingStatusToDone", priority = 2, dependsOnMethods = "com.bookingzoneadmin.FullPayment.TC003FullOtherPayment.testCreateBookingWithOtherPayment")
	public void testChangeBookingStatusToDone() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (stepperSummaryPage.isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Editing booking with ID: " + bookingId);
				editBookingAction.performChangeBookingStatus(bookingId, statusDone);
			}
		} catch (Exception e) {
			handleException(e, "testChangeBookingStatusToDone");
		}
	}

	@Test(testName = "testChangeReservationSlot", priority = 3, dependsOnMethods = "com.bookingzoneadmin.FullPayment.TC003FullOtherPayment.testCreateBookingWithOtherPayment")
	public void testChangeReservationSlot() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (stepperSummaryPage.isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Editing booking with ID: " + bookingId);
				performEditReservationSlot(bookingId);
			}
		} catch (Exception e) {
			handleException(e, "testChangeReservationSlot");
		}
	}

	@Test(testName = "testVerifyPaymentMethod_PaidAmountInTrasactionHistory", priority = 4, dependsOnMethods = "com.bookingzoneadmin.FullPayment.TC003FullOtherPayment.testCreateBookingWithOtherPayment")
	public void testVerifyPaymentMethod_PaidAmountInTrasactionHistory() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (stepperSummaryPage.isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Editing booking with ID: " + bookingId);
				editBookingAction.searchBooking(bookingId);
				verifyTransactionHistory(paymentMethod1);
			}
		} catch (Exception e) {
			handleException(e, "testVerifyPaymentMethod_PaidAmountInTrasactionHistory");
		}
	}


	public void performEditReservationSlot(String bookingId) throws IOException, InterruptedException, TimeoutException {
		editBookingAction.searchBooking(bookingId);
		verifyEditedBookingStatus();

		editReservationSlot();
		stepperEditPage.clickUpdateBookingButton();
		stepperEditPage.clickConfirmEditButton();

		// Check if the "Choose a Payment Method" is present
		if (stepperPaymentPage.isPaymentSectionVisible()) {
			ExtentReportManager.logInfo("Payment section detected, proceeding with payment...");

			// Perform payment actions if the "Choose a Payment Method" is displayed
			paymentActions.verifyTotal_SubTotalAmount();

			// Capture paid and balance amounts
			paidAmount = stepperPaymentPage.getPaidAmount();
			balanceAmount = stepperPaymentPage.getBalanceAmount();
			ExtentReportManager.logInfo("The Paid Amount is: " + paidAmount + " and the Balance Amount is: " + balanceAmount);

			// Complete the payment process
			paymentActions.completePayment(driver, paymentMethod, null, null, null);
		} 
		else 
		{
			ExtentReportManager.logInfo("Payment not required, directly submitting booking...");
			System.out.println("Payment Not required...!");
		}

		verifyBookingStatus(statusSuccess);
		submitBooking();
	}

	public void verifyEditedBookingStatus() 
	{
		boolean isDisabled=stepperSummaryPage.isStatusDropdownIsDisabled();
		Assert.assertTrue(isDisabled, "The 'Done' option is disabled.");
		ExtentReportManager.logPass("Booking Done Assertion Is Passed...!");
	}

	private void editReservationSlot() throws InterruptedException {
		stepperEditPage.clickEditButton();
		createBookingStepperDetailsPage.selectReservationSlot2();

	}


	private void verifyBookingStatus(String status) {
		stepperSummaryPage.verifyBookingStatus(status);
	}

	private void submitBooking() throws InterruptedException {
		stepperSummaryPage.clickSubmitButton();
		ExtentReportManager.logInfo("Booking updated successfully...!");
	}

	private void verifyTransactionHistory(String PayMethod1) throws InterruptedException {
		stepperSummaryPage.clickDetailsButton();
		bookingDetailsPage.clicktransactionHistory();       //Payment method of created booking
		bookingDetailsPage.verifyPaymentMethodAndPaidAmount(PayMethod1 , paymentMethod, paidAmount,balanceAmount );

	}
	

	private void handleException(Exception e, String testCaseName) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		ExtentReportManager.logFail(testCaseName + " test case failed due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
