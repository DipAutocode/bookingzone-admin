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

public class CreatePerEventPlan {
	private WebDriverWait wait;

    @FindBy(xpath="//input[@name='schedules.0.dateRange.0.perGame.visible']")
    private WebElement pricePerEvent;

    @FindBy(xpath="//input[@name='schedules.0.dateRange.0.maxGame']")
    private WebElement maxEventAllowed;
    
    @FindBy(xpath="//input[@name='schedules.0.dateRange.0.minGuest']")
    private WebElement minGuestsAllowed;
   
    @FindBy(xpath="//input[@name='schedules.0.dateRange.0.reservationSlots.0.pricePerGame.0.price']")
    private WebElement priceInputFeild;
    
    

    public CreatePerEventPlan(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Initialize WebDriverWait with a timeout of 10 seconds
    }

    // Method to click the per event checkbox
    public void clickPricePerEventPerPersonCheckBox() throws InterruptedException {
    	//wait.until(ExpectedConditions.elementToBeClickable(pricePerEvent));
    	pricePerEvent.click();
    	ExtentReportManager.logInfo("Clicked Price per event per person checkBox...!");
    	Thread.sleep(2000);
    }

    // enter max event allowed
    public void enterMaxEventAllowed(String events) throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(maxEventAllowed));
    	maxEventAllowed.click();
    	maxEventAllowed.sendKeys(events);
    	ExtentReportManager.logInfo("Entered MaxEventAllowed...!");
    	Thread.sleep(3000);
    }
    
    // enter min guest allowed
    public void enterMinGuestAllowed(String guests) throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(minGuestsAllowed));
    	minGuestsAllowed.click();
    	minGuestsAllowed.sendKeys(guests);
    	ExtentReportManager.logInfo("Entered MinGuestAllowed...!");
    	Thread.sleep(2000);
    }
    
    // enter per event price 
    public void enterPerEventPrice(String price) throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(priceInputFeild));
    	priceInputFeild.click();
		priceInputFeild.sendKeys(price);
		ExtentReportManager.logInfo("Entered Event Price...!");
    	Thread.sleep(2000);
    }
    
   
    
    
	
}
