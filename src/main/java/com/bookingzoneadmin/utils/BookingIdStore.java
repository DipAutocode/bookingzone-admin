package com.bookingzoneadmin.utils;

public class BookingIdStore {
	
	 private static String bookingId;

	    // Method to store the bookingId
	    public static void setBookingId(String id) {
	        bookingId = id;
	    }

	    // Method to retrieve the last created bookingId
	    public static String getBookingId() {
	        return bookingId;
	    }

}
