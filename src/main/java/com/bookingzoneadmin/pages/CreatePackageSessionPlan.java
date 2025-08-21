package com.bookingzoneadmin.pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzoneadmin.utils.ExtentReportManager;

public class CreatePackageSessionPlan {

	private WebDriverWait wait;
	WebDriver driver;

	@FindBy(xpath="//input[@name='packageStatus']")
	private WebElement enablePackageCheckBox;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.perHour.visible']")
	private WebElement pricePerSlotPerHour;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.maxGuest']")
	private WebElement maxGuestPerSlot;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.guestPerPackage']")
	private WebElement guestPerPackage;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.maxPackageAllowed']")
	private WebElement maxPackagesAllowed;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.maxGuestAllowed']")
	private WebElement maxGuestsAllowed;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.reservationSlots.0.pricePerHours.0.price']")
	private WebElement packageSlot1Price;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.reservationSlots.1.pricePerHours.0.price']")
	private WebElement packageSlot2Price;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.reservationSlots.0.pricePerHours.1.price']")
	private WebElement additionalGuestSlot1Price;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.reservationSlots.1.pricePerHours.1.price']")
	private WebElement additionalGuestSlot2Price;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.reservationSlots.0.pricePerHours.0.tax']")
	private WebElement packageSlot1SaleTax;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.reservationSlots.0.pricePerHours.1.tax']")
	private WebElement additionalGuestSlot1SaleTax;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.reservationSlots.1.pricePerHours.0.tax']")
	private WebElement packageSlot2SaleTax;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.reservationSlots.1.pricePerHours.1.tax']")
	private WebElement additionalGuestSlot2SaleTax;

	public CreatePackageSessionPlan(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Initialize WebDriverWait with a timeout of 10 seconds
	}

	// Method to click the enable package checkbox
	public void clickEnablePackageCheckBox() throws InterruptedException {
		enablePackageCheckBox.click();
		ExtentReportManager.logInfo("Enable Package Check Box Selected...!");
		Thread.sleep(2000);
	}

	// Method to click the price per slot per hour check box
	public void clickPricePerSlotPerHourCheckBox() throws InterruptedException {
		pricePerSlotPerHour.click();
		ExtentReportManager.logInfo("Price Per Slot Per Hour Check Box Selected...!");
		Thread.sleep(2000);
	}

	// Method to enter the max guest per slot
	public void enterMaxGuestPerSlot(String numberOfSlots) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(maxGuestPerSlot));
		maxGuestPerSlot.sendKeys(numberOfSlots);
		ExtentReportManager.logInfo("Entered MaxGuestPerSlot...!");
		Thread.sleep(2000);
	}

	// Method to enter the guest per package
	public void enterGuestPerPackage(String numberOfGuests) {
		guestPerPackage.clear();
		guestPerPackage.click();
		ExtentReportManager.logInfo("Entered GuestPerPackage...!");
		guestPerPackage.sendKeys(numberOfGuests);
	}

	// Method to enter the max packages allowed
	public void enterMaxPackagesAllowed(String numberOfPackages) {
		maxPackagesAllowed.clear();
		maxPackagesAllowed.click();
		maxPackagesAllowed.sendKeys(numberOfPackages);
		ExtentReportManager.logInfo("Entered MaxPackagesAllowed...!");
	}

	// Method to enter the max guests allowed
	public void enterMaxGuestsAllowed(String numberOfGuests) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(maxGuestsAllowed));
		maxGuestsAllowed.sendKeys(numberOfGuests);
		ExtentReportManager.logInfo("Max Guest Allowed Entered...!");
		Thread.sleep(2000);
	}

	// Common method to enter package price, additional guest price, and sales tax based on slot number
	public void enterSlotPriceDetails(String slotNumber, String packagePrice, String additionalGuestPrice, String packageSaleTax, String additionalGuestSaleTax) {
		 int number = Integer.parseInt(slotNumber);
		    System.out.println("Slot no is- "+ number);
		
		WebElement packageSlotPrice = (number == 1) ? packageSlot1Price : packageSlot2Price;
		WebElement additionalGuestPriceField = (number == 1) ? additionalGuestSlot1Price : additionalGuestSlot2Price;
		WebElement packageSaleTaxField = (number == 1) ? packageSlot1SaleTax : packageSlot2SaleTax;
		WebElement additionalGuestSaleTaxField = (number == 1) ? additionalGuestSlot1SaleTax : additionalGuestSlot2SaleTax;

		if (packageSlotPrice != null) {
			packageSlotPrice.sendKeys(packagePrice);
			ExtentReportManager.logInfo("Package Price Entered...!");
		}
		if (additionalGuestPriceField != null) {
			additionalGuestPriceField.sendKeys(additionalGuestPrice);
			ExtentReportManager.logInfo("Additional Guest Price Entered...!");
		}
		if (packageSaleTaxField != null) {
			packageSaleTaxField.sendKeys(packageSaleTax);
			ExtentReportManager.logInfo("Package Sale Tax Price Entered...!");
		}
		if (additionalGuestSaleTaxField != null) {
			additionalGuestSaleTaxField.sendKeys(additionalGuestSaleTax);
			ExtentReportManager.logInfo("Additional Guest Sale Tax Price Entered...!");
		}
	}


}
