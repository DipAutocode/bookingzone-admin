package com.bookingzoneadmin.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzoneadmin.utils.ExtentReportManager;

public class SearchBookingPage 
{
	private WebDriverWait wait;
	
	@FindBy(xpath = "(//div//input[contains(@class, 'MuiOutlinedInput-input MuiInputBase-input Mui')])[2]")
	private WebElement textBookingIdInput;

	@FindBy(xpath="(//button//span[contains(@class,'MuiIconB')])[8]")
	private WebElement BookingArrow;
	
	@FindBy(xpath = "//span[@role='progressbar']//*[name()='svg']")
	private WebElement loader;

	public SearchBookingPage (WebDriver driver)
	{
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}


	// Method to enter booking ID into the input field
	public void enterBookingId(String id) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(textBookingIdInput));
		textBookingIdInput.sendKeys(id);
		Thread.sleep(2000);
		ExtentReportManager.logInfo("Booking Id Entered...!");
	}	 

	public  void clickBookingArrow() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(BookingArrow));
		BookingArrow.click();
		ExtentReportManager.logInfo("Clicked On Booking Arrow...!");
	}


}
