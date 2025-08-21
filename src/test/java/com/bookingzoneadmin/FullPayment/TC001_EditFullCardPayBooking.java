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

public class TC001_EditFullCardPayBooking extends BaseClass {

	BookingActions bookingActions;
	PaymentActions paymentActions;
	StepperSummaryPage stepperSummaryPage;
	SearchBookingPage searchBookingPage;
	StepperPaymentPage stepperPaymentPage;
	StepperEditPage stepperEditPage;
	CreateBookingStepperDetailsPage createBookingStepperDetailsPage;
	CalendarReservationPage calendarReservationPage;
	EditBookingAction editBookingAction;
	BookingDetailsPage bookingDetailsPage;

	// Test Data
	private String statusInProgress;
	private String statusSuccess;
	private String paymentMethod;
	private String refundAmount;

	// Class-level variables to store paid and balance amounts
	private double paidAmount;
	private double balanceAmount;

	@BeforeClass
	public void initializeTest(ITestContext context) throws InterruptedException, IOException {
		stepperSummaryPage = new StepperSummaryPage(driver);
		searchBookingPage = new SearchBookingPage(driver);
		stepperEditPage = new StepperEditPage(driver);
		stepperPaymentPage=new StepperPaymentPage(driver);
		createBookingStepperDetailsPage = new CreateBookingStepperDetailsPage(driver);
		bookingDetailsPage=new BookingDetailsPage(driver);

		bookingActions = new BookingActions(
				new BusinessHomePage(driver), 
				new CalendarReservationPage(driver),
				createBookingStepperDetailsPage
				);

		paymentActions = new PaymentActions(
				stepperPaymentPage,
				stepperSummaryPage, null, null
				);

		editBookingAction = new EditBookingAction(
				stepperSummaryPage,
				new CalendarReservationPage(driver),
				searchBookingPage);

		fetchTestData();
	}

	private void fetchTestData() throws IOException {

		statusInProgress = UtilityClass.getDataFromEs(1, "Status_In Progress", "EditBooking");
		statusSuccess = UtilityClass.getDataFromEs(1, "Status_Successful", "EditBooking");
		paymentMethod = UtilityClass.getDataFromEs(1, "Card Pay", "Create_Booking");
		refundAmount=UtilityClass.getDataFromEs(1, "Full Refund", "Refund_Details");
	}

	@Test(testName = "testChangeBookingStatusToInProgress", priority = 2, dependsOnMethods = "com.bookingzoneadmin.FullPayment.TC001FullCardPayment.testCreateBookingWithCardPayment")
	public void testChangeBookingStatusToInProgress() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (stepperSummaryPage.isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Editing booking with ID: " + bookingId);
				editBookingAction.performChangeBookingStatus(bookingId, statusInProgress);
			}
		} catch (Exception e) {
			handleException(e, "testChangeBookingStatusToInProgress");
		}
	}

	@Test(testName = "testAddPackage_AdditionalGuest", priority = 3, dependsOnMethods = "com.bookingzoneadmin.FullPayment.TC001FullCardPayment.testCreateBookingWithCardPayment")
	public void testAddPackage_AdditionalGuest() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (stepperSummaryPage.isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Editing booking with ID: " + bookingId);
				performAddPackageAndGuest(bookingId);
			}
		} catch (Exception e) {
			handleException(e, "testAddPackage_AdditionalGuest");
		}
	}


	@Test(testName = "testVerifyPaymentMethod_PaidAmountInTrasactionHistory", priority = 4, dependsOnMethods = "com.bookingzoneadmin.FullPayment.TC001FullCardPayment.testCreateBookingWithCardPayment")
	public void testVerifyPaymentMethod_PaidAmountInTrasactionHistory() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (stepperSummaryPage.isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Verifying booking with ID: " + bookingId);
				editBookingAction.searchBooking(bookingId);
				verifyTransactionHistory();
			}
		} catch (Exception e) {
			handleException(e, "testVerifyPaymentMethod_PaidAmountInTrasactionHistory");
		}
	}


	@Test(testName = "testVerifyForceRefund", priority = 5, dependsOnMethods = "com.bookingzoneadmin.FullPayment.TC001FullCardPayment.testCreateBookingWithCardPayment")
	public void testVerifyForceRefund() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (stepperSummaryPage.isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Verifying booking with ID: " + bookingId);
				forceRefund();
				verifyForceRefundWithTotalAmount();
			}
		} catch (Exception e) {
			handleException(e, "testVerifyForceRefund");
		}
	}


	public void performAddPackageAndGuest(String bookingId) throws IOException, InterruptedException, TimeoutException {
		editBookingAction.searchBooking(bookingId);
		verifyBookingStatus(statusInProgress);

		editBookingToAddGuest();
		stepperEditPage.clickUpdateBookingButton();
		stepperEditPage.clickConfirmEditButton();
		paymentActions.verifyTotal_SubTotalAmount();

		paidAmount=stepperPaymentPage.getPaidAmount();
		balanceAmount =stepperPaymentPage.getBalanceAmount();
		ExtentReportManager.logInfo("The Paid Amount Is- "+ paidAmount +" and"+ " Blanace Amount Is- "+ balanceAmount);

		paymentActions.completePayment(driver, paymentMethod, null, null, null);
		verifyBookingStatus(statusSuccess);
		submitBooking();
	}

	private void editBookingToAddGuest() throws InterruptedException {
		stepperEditPage.clickEditButton();
		createBookingStepperDetailsPage.selectPackage();
		createBookingStepperDetailsPage.selectAddGuest();
	}

	private void verifyBookingStatus(String status) {
		stepperSummaryPage.verifyBookingStatus(status);
	}

	private void submitBooking() throws InterruptedException {
		stepperSummaryPage.clickSubmitButton();
		ExtentReportManager.logInfo("Booking updated successfully...!");
	}

	private void verifyTransactionHistory() throws InterruptedException {
		stepperSummaryPage.clickDetailsButton();
		bookingDetailsPage.clicktransactionHistory();
		bookingDetailsPage.verifyPaymentMethodAndPaidAmount(paymentMethod, paymentMethod, paidAmount,balanceAmount );

	}

	private void forceRefund() throws InterruptedException {

		bookingDetailsPage.clickbookingDetail();
		bookingDetailsPage.clickRefundIcon();
		bookingDetailsPage.selectRefundMethod(paymentMethod);
		bookingDetailsPage.selectRefundType();
		bookingDetailsPage.enterRefundAmount(refundAmount);
		bookingDetailsPage.clickSaveButton();
		bookingDetailsPage.clickRefundButton();

		bookingDetailsPage.verifyRefundSuccessfulMessage();
	}

	private void verifyForceRefundWithTotalAmount() {

		double totalPaidAmount = bookingDetailsPage.getTotalAmountPaid();
		double refundedAmount = bookingDetailsPage.getRefundedAmount();

		// Parse refundAmount to double for calculation
		double refundAmt = Double.parseDouble(refundAmount);

		if (refundAmt == 100) {
			// Assert if the full amount is refunded
			Assert.assertEquals(totalPaidAmount, refundedAmount, "Full refund of the booking is incorrect");
			ExtentReportManager.logInfo("Total Paid Amount Is- " +totalPaidAmount+ " Refunded Amount Is- "+ refundedAmount);
		} else if (refundAmt == 50) {
			// Assert if half of the amount is refunded
			Assert.assertEquals(totalPaidAmount / 2, refundedAmount, "50% refund of the booking is incorrect");
			ExtentReportManager.logInfo("Total Paid Amount Is- " +totalPaidAmount+ " Refunded Amount Is- "+ refundedAmount);
		} else {
			// You can add more conditions for other refund percentages if needed
			Assert.fail("Unexpected refund amount: " + refundAmt);
		}
		ExtentReportManager.logPass("Verify Booking Refund With Total Amount Assertion Is Passed...!");
	}


	private void handleException(Exception e, String testCaseName) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		ExtentReportManager.logFail(testCaseName + " test case failed due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
