package com.bookingzoneadmin.FullPayment;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bookingzoneadmin.pages.BaseClass;
import com.bookingzoneadmin.pages.BookingDetailsPage;
import com.bookingzoneadmin.pages.BusinessHomePage;
import com.bookingzoneadmin.pages.CalendarReservationPage;
import com.bookingzoneadmin.pages.OutletDetailsPage;
import com.bookingzoneadmin.pages.OutletListPage;
import com.bookingzoneadmin.pages.SearchBookingPage;
import com.bookingzoneadmin.pages.StepperSummaryPage;
import com.bookingzoneadmin.utils.BookingIdStore;
import com.bookingzoneadmin.utils.EditBookingAction;
import com.bookingzoneadmin.utils.ExtentReportManager;

public class TC005EditFullPayVia_PaymentURL extends BaseClass {

	StepperSummaryPage stepperSummaryPage;
	SearchBookingPage searchBookingPage;
	EditBookingAction editBookingAction;
	BookingDetailsPage bookingDetailsPage;
	BusinessHomePage businessHomePage;
	OutletListPage outletListPage;
	OutletDetailsPage outletDetailsPage;

	@BeforeClass
	public void initializeTest(ITestContext context) throws InterruptedException, IOException {

		stepperSummaryPage = new StepperSummaryPage(driver);
		searchBookingPage = new SearchBookingPage(driver);
		bookingDetailsPage=new BookingDetailsPage(driver);
		businessHomePage=new BusinessHomePage (driver);
		outletListPage=new OutletListPage(driver);
		outletDetailsPage=new OutletDetailsPage(driver);

		editBookingAction = new EditBookingAction(
				stepperSummaryPage,
				new CalendarReservationPage(driver),
				searchBookingPage);

	}


	@Test(testName = "testChangeBookingStatusToCancel", priority = 2, dependsOnMethods = "com.bookingzoneadmin.FullPayment.TC005FullPayVia_PaymentURL.testCreateBookingWithPaymentURL")
	public void testChangeBookingStatusToCancel() throws IOException, InterruptedException {
		try {
			String bookingId = BookingIdStore.getBookingId();
			if (stepperSummaryPage.isValidBookingId(bookingId)) {
				ExtentReportManager.logInfo("Editing booking with ID: " + bookingId);
				performChangeBookingStatus(bookingId);
			}
		} catch (Exception e) {
			handleException(e, "testChangeBookingStatusToCancel");
		}
	}

	public void performChangeBookingStatus(String bookingId) throws IOException, InterruptedException {
		editBookingAction.searchBooking(bookingId);
		stepperSummaryPage.clickCancelBtn();
		stepperSummaryPage.clickCancelConfirmationBtn();
        Thread.sleep(2000);
		// Locate the success message element on the page
		String expectedMessage = "Booking has been successfully cancelled.";
		WebElement successMessageElement = driver.findElement(By.xpath("//div[@id='notistack-snackbar']"));
		
		// Assert that the success message is displayed
		Assert.assertTrue(successMessageElement.isDisplayed(), "Success message is not displayed on the page!");

		// Log the success message in the Extent Report
		ExtentReportManager.logPass(expectedMessage);
	}


	private void handleException(Exception e, String testCaseName) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		ExtentReportManager.logFail(testCaseName + " test case failed due to " + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
