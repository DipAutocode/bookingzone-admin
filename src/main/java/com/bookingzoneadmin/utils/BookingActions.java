package com.bookingzoneadmin.utils;

import java.io.IOException;

import com.bookingzoneadmin.pages.BusinessHomePage;
import com.bookingzoneadmin.pages.CalendarReservationPage;
import com.bookingzoneadmin.pages.CreateBookingStepperDetailsPage;

public class BookingActions {

	private BusinessHomePage businessHomePage;
	private CalendarReservationPage calendarReservationPage;
	private CreateBookingStepperDetailsPage createBkgStepperDetailsPage;



	public BookingActions(BusinessHomePage businessHomePage, CalendarReservationPage calendarReservationPage,
			CreateBookingStepperDetailsPage createBkgStepperDetailsPage) {
		this.businessHomePage = businessHomePage;
		this.calendarReservationPage = calendarReservationPage;
		this.createBkgStepperDetailsPage = createBkgStepperDetailsPage;
	}
	
	public void selectBookingOutlet(String outletName) throws InterruptedException 
	{
		businessHomePage.clickCalendarButton();
		calendarReservationPage.selectOutlet(outletName);
	}

	public void configure_EnterBookingDetails( String bookingName, String email) throws InterruptedException
	{
		calendarReservationPage.enterCustomerName(bookingName);
		calendarReservationPage.enterEmail(email);
		calendarReservationPage.clickCreateReservationButton();

	}


	public void enterBookingTime(String time, String min, String noon) throws InterruptedException, IOException {
		createBkgStepperDetailsPage.enterBookingTime(time);
		createBkgStepperDetailsPage.enterMinutes(min);
		createBkgStepperDetailsPage.selectNoon(noon);
		ExtentReportManager.logInfo("Booking Time is-"+ time+": " + min +noon );

	}
	

	public void selectBookingDate(String bookingDate) throws InterruptedException {
		createBkgStepperDetailsPage.clickCalendarSymbol();
		createBkgStepperDetailsPage.clickNextArrow();
		createBkgStepperDetailsPage.selectDate(bookingDate);

	}



	public void proceedWithBookingDetails(String planName) throws InterruptedException {
		createBkgStepperDetailsPage.selectPlan(planName);
		createBkgStepperDetailsPage.selectReservationSlot1();
		createBkgStepperDetailsPage.selectPackage();
		
		 // Conditional logic for package-type plan vs session-type plan
	    if (isPackagePlan(planName)) {
	        // For package-type plans, select additional guest
	        createBkgStepperDetailsPage.selectAddGuest();
	    } else {
	        // For session-type plans, select session-specific items instead of guests
	        createBkgStepperDetailsPage.selectSaleItem();
	    }
	
		createBkgStepperDetailsPage.clickProceedToPayButton();

	}

	// Helper method to check if it is a package-type plan
	private boolean isPackagePlan(String planName) {
	   
	    return planName.contains("Package"); 
	}

	// Helper method to check if it is a session-type plan
	private boolean isSessionPlan(String planName) {
	    // Define conditions for identifying session-type plan
	    return planName.contains("Session"); // Adjust as necessary
	}


}
