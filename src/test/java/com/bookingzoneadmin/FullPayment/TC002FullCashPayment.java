package com.bookingzoneadmin.FullPayment;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bookingzoneadmin.pages.BaseClass;
import com.bookingzoneadmin.pages.BusinessHomePage;
import com.bookingzoneadmin.pages.CalendarReservationPage;
import com.bookingzoneadmin.pages.CreateBookingStepperDetailsPage;
import com.bookingzoneadmin.pages.StepperPaymentPage;
import com.bookingzoneadmin.pages.StepperSummaryPage;
import com.bookingzoneadmin.utils.BookingActions;
import com.bookingzoneadmin.utils.BookingIdStore;
import com.bookingzoneadmin.utils.ExtentReportManager;
import com.bookingzoneadmin.utils.PaymentActions;
import com.bookingzoneadmin.utils.UtilityClass;

public class TC002FullCashPayment extends BaseClass {


	BookingActions bookingActions;
	PaymentActions paymentActions;
	StepperSummaryPage stepperSummaryPage;

	// Test data variables
	private String outletName;
	private String bookingName;
	private String customerEmail;
	private String hour;
	private String minute;
	private String noon;
	private String planName;
	private String paymentMethod;
	private String status;

	@BeforeClass
	public void initializeTest(ITestContext context) throws InterruptedException, IOException {

		stepperSummaryPage = new StepperSummaryPage(driver);

		bookingActions = new BookingActions(
				new BusinessHomePage(driver), 
				new CalendarReservationPage(driver),
				new CreateBookingStepperDetailsPage(driver)
				);

		paymentActions = new PaymentActions(
				new StepperPaymentPage(driver),
				new StepperSummaryPage(driver), null, null
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
		planName = UtilityClass.getDataFromEs(2, "Plan Name", "Create_Plans");
		hour=UtilityClass.getDataFromEs(2, "Slot1StartTimehour", "Create_Plans");	
		minute=UtilityClass.getDataFromEs(2, "StartMinute", "Create_Plans");
		noon=UtilityClass.getDataFromEs(2, "StartTimeAMPM", "Create_Plans");
		paymentMethod = UtilityClass.getDataFromEs(1, "Cash Pay", "Create_Booking");
		status=UtilityClass.getDataFromEs(1, "Status_Successful", "EditBooking");
	}

	@Test(testName = "testCreateBookingWithCashPayment", priority = 1)
	public void testCreateBookingWithCashPayment() throws IOException, InterruptedException {
		try {
			bookingActions.selectBookingOutlet(outletName);
			bookingActions.configure_EnterBookingDetails(bookingName, customerEmail);
			bookingActions.enterBookingTime(hour, minute, noon);
			bookingActions.proceedWithBookingDetails(planName);
			paymentActions.verifyTotal_SubTotalAmount();
			paymentActions.completePayment(driver, paymentMethod, null, null, null);

			String bookingId = paymentActions.getBookingId();
			// Store the bookingId using the Singleton class
			BookingIdStore.setBookingId(bookingId);
			ExtentReportManager.logInfo("Booking created successfully with ID: " + bookingId);

			verifyBookingSuccess();
		} catch (Exception e) {
			handleException(e, "testCreateBookingWithCashPayment");
		}
	}

	private void verifyBookingSuccess() throws IOException, InterruptedException {
		if (driver.getPageSource().contains("There is some error in receiver's bank or outlet is not taking online payment.")) {
			ExtentReportManager.logFail("There is some error in receiver's bank or outlet is not taking online payment.");
			Assert.fail("There is some error in receiver's bank or outlet is not taking online payment.");
		} else {
			stepperSummaryPage.verifyBookingStatus(status);
			stepperSummaryPage.clickSubmitButton();
			ExtentReportManager.logInfo("Booking created successfully by Cash payment.");
		}
	}

	private void handleException(Exception e, String testCaseName) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		ExtentReportManager.logFail(testCaseName + " test case failed due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
