package com.bookingzoneadmin.pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.bookingzoneadmin.utils.ExtentReportManager;

public class BookingSummaryPage {

	private WebDriverWait wait;
	private WebDriver driver;

	@FindBy(xpath = "(//div[@class='jsx-2606033956'])[2]")
	private WebElement bookingId;

	

	public BookingSummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public String getBookingId() 
	{ 
		wait.until(ExpectedConditions.visibilityOf(bookingId));
		// Get the full text of the element (e.g., "Booking ID: yfv3pmbPWW")
		String fullText = bookingId.getText();

		// Extract only the booking ID part (after "Booking ID: ")
		String bookingId = fullText.replace("Booking ID: ", "").trim();
		System.out.println("The Booking Id Is-" + bookingId );
        return bookingId;
	}

	
	
}
