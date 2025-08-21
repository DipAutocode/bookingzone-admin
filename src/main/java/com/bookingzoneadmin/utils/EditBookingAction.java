package com.bookingzoneadmin.utils;

import java.io.IOException;

import com.bookingzoneadmin.pages.CalendarReservationPage;
import com.bookingzoneadmin.pages.SearchBookingPage;
import com.bookingzoneadmin.pages.StepperSummaryPage;

public class EditBookingAction {


	private CalendarReservationPage calendarReservationPage;
	private StepperSummaryPage stepperSummaryPage;
	private SearchBookingPage searchBookingPage;



	public EditBookingAction(StepperSummaryPage stepperSummaryPage, CalendarReservationPage calendarReservationPage,
			SearchBookingPage searchBookingPage) {

		this.calendarReservationPage = calendarReservationPage;
		this.stepperSummaryPage=stepperSummaryPage;
		this.searchBookingPage= searchBookingPage;
	}

	public void searchBooking(String BookingId) throws InterruptedException 
	{
		calendarReservationPage.clickViewReservationButton();
		searchBookingPage.enterBookingId(BookingId);
		searchBookingPage.clickBookingArrow();
	}

	public void performChangeBookingStatus(String bookingId, String status) throws IOException, InterruptedException {
		searchBooking(bookingId);
		stepperSummaryPage.changeStatus(status);
		stepperSummaryPage.clickSubmitButton();
		if (status.equals("In Progress")) 
		{
			stepperSummaryPage.clickstatusConfirmationButton();
		}

		ExtentReportManager.logInfo("Booking status changed to: " + status);
		
	}


}
