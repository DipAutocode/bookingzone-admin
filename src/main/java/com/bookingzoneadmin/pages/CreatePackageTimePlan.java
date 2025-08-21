package com.bookingzoneadmin.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzoneadmin.utils.ExtentReportManager;

public class CreatePackageTimePlan {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor  js;
	
	// WebElements declaration using @FindBy annotation with proper naming conventions
	@FindBy(xpath = "//input[@value='range']")
	private WebElement timeRange;

	@FindBy(xpath = "//input[@id='bookingduration']")
	private WebElement bookingDuration;

	@FindBy(xpath = "//ul[contains(@class, 'MuiAutocomplete-listbox')]//li[contains(@class, 'MuiAutocomplete-option')]")
	private List<WebElement> bookingDurationOptions;

	@FindBy(xpath = "//select[@name='schedules.0.dateRange.0.reservationSlots.0.increment']")
	private WebElement Slot1TimeIncrement;
	
	@FindBy(xpath = "//select[@name='schedules.0.dateRange.0.reservationSlots.1.increment']")
	private WebElement Slot2TimeIncrement;

	public CreatePackageTimePlan(WebDriver driver) {
		PageFactory.initElements(driver, this);
		 this.driver = driver;
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		 this.js = (JavascriptExecutor) driver;
	}

	// Click the time range radio button using JavaScriptExecutor
	public void clickTimeRangeRadiobtn() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", timeRange);
		ExtentReportManager.logInfo("Click TimeRange radiobtn...!");
		Thread.sleep(2000);
	}

	// Select booking duration by clicking all available options
	public void selectBookingDuration() throws InterruptedException {
		 bookingDuration.click();
		 System.out.println("Number of options found: " + bookingDurationOptions.size());
		    System.out.println("click on booking duration dropdown");
		    List<WebElement> options = bookingDurationOptions; // Refresh list
		    wait.until(ExpectedConditions.visibilityOfAllElements(bookingDurationOptions));

		    for (int i = 0; i < options.size(); i++) {
		        WebElement option = options.get(i);
		        System.out.println("Option " + i + ": " + option.getText());
		        System.out.println("Displayed: " + option.isDisplayed());
		        System.out.println("Enabled: " + option.isEnabled());

		        wait.until(ExpectedConditions.elementToBeClickable(option));
		        option.click();
		        System.out.println("Clicked option " + i);
		        Thread.sleep(200);
		    }
		    ExtentReportManager.logInfo("Booking Durations Selected...!");
		    Thread.sleep(2000);
	}

	// Select time frame from the dropdown using Select class
	public void selectSlot1TimeIncrement( int index) throws InterruptedException {
		Select select = new Select(Slot1TimeIncrement);
		select.selectByIndex(index);
		ExtentReportManager.logInfo("Slot1 Time Increment Seelected...!");
		Thread.sleep(2000);
	}
	
	// Select time frame from the dropdown using Select class
		public void selectSlot2TimeIncrement( int index) throws InterruptedException {
			Select select = new Select(Slot2TimeIncrement);
			select.selectByIndex(index);
			ExtentReportManager.logInfo("Slot2 Time Increment Seelected...!");
			Thread.sleep(2000);
		}
	}
