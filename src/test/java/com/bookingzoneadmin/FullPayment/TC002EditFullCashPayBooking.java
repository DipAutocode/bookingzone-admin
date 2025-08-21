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

public class TC002EditFullCashPayBooking extends BaseClass {


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
	private String statusCheckIn;
	private String statusSuccess;
	private String addGuestCount;
	private String paymentMethod;
	private String cardNumber;
	private String expiryDate;
	private String cvc;

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
		statusCheckIn=UtilityClass.getDataFromEs(1, "Status_CheckIn", "EditBooking");
		statusSuccess = UtilityClass.getDataFromEs(1, "Status_Successful", "EditBooking");
		addGuestCount = UtilityClass.getDataFromEs(1, "Additonal Guest Count", "Create_Booking");
		paymentMethod = UtilityClass.getDataFromEs(1, "Card Pay", "Create_Booking");
		cardNumber = UtilityClass.getDataFromEs(1, "Card No", "Create_Booking");
		expiryDate = UtilityClass.getDataFromEs(1, "Expiry Date", "Create_Booking");
		cvc = UtilityClass.getDataFromEs(1, "CVC", "Create_Booking");
	}


	@Test(testName = "testChangeBookingStatusToCheckIn", priority = 2, dependsOnMethods = "com.bookingzoneadmin.FullPayment.TC002FullCashPayment.testCreateBookingWithCashPayment")
	public void testChangeBookingStatusToCheckIn() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (stepperSummaryPage.isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Editing booking with ID: " + bookingId);
				editBookingAction.performChangeBookingStatus(bookingId, statusCheckIn);
			}
		} catch (Exception e) {
			handleException(e, "testChangeBookingStatusToCheckIn");
		}
	}

	@Test(testName = "testAddAdditionalGuestByCount", priority = 3, dependsOnMethods = "com.bookingzoneadmin.FullPayment.TC002FullCashPayment.testCreateBookingWithCashPayment")
	public void testAddPackage_AdditionalGuest() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (stepperSummaryPage.isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Editing booking with ID: " + bookingId);
				performAddPackageAndGuest(bookingId);
			}
		} catch (Exception e) {
			handleException(e, "testAddAdditionalGuestByCount");
		}
	}

	@Test(testName = "testVerifyPaymentMethod_PaidAmountInTrasactionHistory", priority = 4, dependsOnMethods = "com.bookingzoneadmin.FullPayment.TC002FullCashPayment.testCreateBookingWithCashPayment")
	public void testVerifyPaymentMethod_PaidAmountInTrasactionHistory() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (stepperSummaryPage.isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Editing booking with ID: " + bookingId);
				editBookingAction.searchBooking(bookingId);
				verifyTransactionHistory();
				
			}
		} catch (Exception e) {
			handleException(e, "testVerifyPaymentMethod_PaidAmountInTrasactionHistory");
		}

	}
	

	public void performAddPackageAndGuest(String bookingId) throws IOException, InterruptedException, TimeoutException {
		editBookingAction.searchBooking(bookingId);
		verifyEditedBookingStatus();

		editBookingToAddGuest();
		stepperEditPage.clickUpdateBookingButton();
		stepperEditPage.clickConfirmEditButton();

		// Verify payment and finalize
		paymentActions.verifyTotal_SubTotalAmount();

		paidAmount=stepperPaymentPage.getPaidAmount();
		balanceAmount =stepperPaymentPage.getBalanceAmount();
		ExtentReportManager.logInfo("The Paid Amount Is- "+ paidAmount +" and"+ " Blanace Amount Is- "+ balanceAmount);

		paymentActions.completePayment(driver, paymentMethod, cardNumber, expiryDate, cvc);
		verifyBookingStatus(statusSuccess);
		submitBooking();
	}

	public void verifyEditedBookingStatus() 
	{
		boolean isDisabled=stepperSummaryPage.isStatusOptionIsDisabled();
		Assert.assertTrue(isDisabled, "The 'Check In' option is disabled.");
		ExtentReportManager.logPass("Booking CheckIn Assertion Is Passed...!");
	}

	private void editBookingToAddGuest() throws InterruptedException {
		stepperEditPage.clickEditButton();
		createBookingStepperDetailsPage.selectPackage();
		createBookingStepperDetailsPage.enterAddtionalGuestCount(addGuestCount);
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
		bookingDetailsPage.verifyPaymentMethodAndPaidAmount("cash" , paymentMethod, paidAmount,balanceAmount );

	}
	

	private void handleException(Exception e, String testCaseName) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		ExtentReportManager.logFail(testCaseName + " test case failed due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
