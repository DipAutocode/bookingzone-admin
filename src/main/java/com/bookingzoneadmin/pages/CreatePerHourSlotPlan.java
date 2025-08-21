package com.bookingzoneadmin.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bookingzoneadmin.utils.ExtentReportManager;

public class CreatePerHourSlotPlan {

	WebDriver driver;

    // WebElements declaration using @FindBy annotation with proper naming conventions
    @FindBy(xpath = "//input[@value='slot']")
    private WebElement slotRadioBtn;
    
    @FindBy(xpath = "//input[@name='schedules.0.dateRange.0.reservationSlots.0.pricePerHours.0.price']")
    private WebElement guestPrice;


    public CreatePerHourSlotPlan(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    
    

    // Click the time range radio button using JavaScriptExecutor
    public void clickSlotRadiobtn() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", slotRadioBtn);
        ExtentReportManager.logInfo("Clicked On Slot Radio Button...!");
    }

    
    public void enterGuestPriceSlot1(String price) throws InterruptedException 
    {
    	guestPrice.sendKeys(price);
    	ExtentReportManager.logInfo("Guest Price Entered...!");
    	Thread.sleep(2000);
    }
 
}
