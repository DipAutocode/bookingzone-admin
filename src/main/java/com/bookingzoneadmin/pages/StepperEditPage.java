package com.bookingzoneadmin.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzoneadmin.utils.ExtentReportManager;

/**
 * This class represents the Edit_Date functionality in the application.
 */
public class StepperEditPage {
	private WebDriverWait wait;

    // WebElement for Edit button
    @FindBy(xpath = "//button//span[text()='Edit']")
    private WebElement editButton;

    // WebElement for editing the date in the calendar
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[3]/div[2]/form[1]/div[1]/form[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/button[1]/span[1]/*[name()='svg'][1]")
    private WebElement calendarEdit;

    // WebElement for selecting a date for editing
    @FindBy(xpath = "//button[text()='12']")
    private WebElement editDate;
    
    @FindBy(xpath = "(//div[contains(@class,'MuiButtonBase-ro')])[1]")
    private WebElement deselectLane;

    @FindBy(xpath = "(//div[contains(@class,'MuiButtonBase-ro')])[2]")
    private WebElement updateLane;

 // WebElement for Update Add-on button
    @FindBy(xpath = "//button//span[text()='Update Add-on']")
    private WebElement updateAddonButton;
    
    @FindBy(xpath = "(//button[contains(@class,'MuiButton-root MuiButton-o')])[1]")
    private WebElement newSlot;
   
    // WebElement for Update Booking button
    @FindBy(xpath = "//button//span[text()='Update Booking']")
    private WebElement updateBookingButton;

    // WebElement for Confirm button after editing
    @FindBy(xpath = "//button//span[text()='Confirm']")
    private WebElement confirmEditButton;

    

    /**
     * Constructor to initialize elements using PageFactory.
     * @param driver The WebDriver instance.
     */
    public StepperEditPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
    }


    /**
     * Method to click the Edit button.
     * @throws InterruptedException If the thread is interrupted.
     */
    public void clickEditButton() throws InterruptedException {
    	  wait.until(ExpectedConditions.elementToBeClickable(editButton));
    	editButton.click();
    	Thread.sleep(2000);
    	ExtentReportManager.logInfo("Click Edit Button...!");
      
    }

    /**
     * Method to click the Calendar Edit button.
     * @param driver The WebDriver instance.
     * @throws InterruptedException If the thread is interrupted.
     */
    public void clickCalendarEdit(WebDriver driver) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(calendarEdit));
        calendarEdit.click();

        Thread.sleep(2000);
    }

    /**
     * Method to click on a date for editing.
     * @throws InterruptedException If the thread is interrupted.
     */
    public void editDate() throws InterruptedException {
        editDate.click();
        Thread.sleep(2000);
    }
    
    public void unClickDefaultLane() throws InterruptedException {
        deselectLane.click();
        Thread.sleep(3000);
    }

    public void selectNewLane() throws InterruptedException {
        updateLane.click();
        Thread.sleep(3000);
    }
    
    /**
     * Method to click on the Update Add-on button.
     * @throws InterruptedException If the thread is interrupted.
     */
    public void clickUpdateAddonButton() throws InterruptedException {
        updateAddonButton.click();
        Thread.sleep(3000);
        ExtentReportManager.logInfo("Clicked On Update Addon Button...!");
    }
    
    // Method to click on the new reservation slot
    public void clickNewReservationSlot() {
        newSlot.click();
    }


    /**
     * Method to click the Update Booking button.
     * @throws InterruptedException If the thread is interrupted.
     */
    public void clickUpdateBookingButton() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(updateBookingButton));
    	updateBookingButton.click();
    	ExtentReportManager.logInfo("Clicked On Update Booking Button...!");
    }

    /**
     * Method to click the Confirm button after editing.
     * @throws InterruptedException If the thread is interrupted.
     */
    public void clickConfirmEditButton() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(confirmEditButton));  
    	confirmEditButton.click();
    	ExtentReportManager.logInfo("Clicked On Confirm Edit Button...!");
    }

  
}
