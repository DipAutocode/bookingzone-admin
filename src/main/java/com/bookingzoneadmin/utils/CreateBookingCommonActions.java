package com.bookingzoneadmin.utils;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.bookingzoneadmin.pages.LogInPage;

public class CreateBookingCommonActions {
	static Logger logger = (Logger) LogManager.getLogger(CreateBookingCommonActions.class);

	/**
	 * Performs the login action with the given parameters.
	 * 
	 * @param driver The WebDriver instance.
	 * @param username The username to log in.
	 * @param password The password to log in.
	 * @throws IOException If there is an issue with accessing the data.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public static void performLogin(String userType,WebDriver driver, String username, String password) throws IOException, InterruptedException {
		LogInPage login = new LogInPage(driver);
		
		switch(userType.toLowerCase()) {
		case "business":
			if (!login.isBusinessRadioButtonSelected()) {
				login.clickBusinessRadioButton();
				ExtentReportManager.logInfo("Selected Business radio button");
			}
			break;
		case "admin":
			// Assuming the Admin radio button is selected by default, no action is needed here.
			ExtentReportManager.logInfo("Admin user detected, no need to select radio button");
			break;
		case "manager":
			if (!login.isManagerRadioButtonSelected()) {
				login.clickManagerRadioButton();
				ExtentReportManager.logInfo("Selected Manager radio button");
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid user type provided: " + userType);
		}

		login.enterUsername(username);
		login.enterPassword(password);
		login.clickLoginButton();
		ExtentReportManager.logInfo("Login successful");
	}
	
	

}
