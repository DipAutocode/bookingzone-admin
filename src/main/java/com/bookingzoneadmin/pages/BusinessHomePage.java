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
 * This class represents the functionality related to adding a service in the admin interface.
 */
public class BusinessHomePage {
	private WebDriverWait wait;
	 private WebDriver driver;

    // WebElement for Outlets link
    @FindBy(xpath = "//div[text()='Outlets']")
    private WebElement outletsLink;

 // Web elements declaration using @FindBy annotation
    @FindBy(xpath = "//div[normalize-space()='Calendar']")
    private WebElement calendarButton;

    /**
     * Constructor to initialize PageFactory and the web elements.
     */
    public BusinessHomePage(WebDriver driver) {
    	this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Initialize WebDriverWait with a timeout of 10 seconds
    }

    /**
     * Method to click on Outlets link.
     * This method navigates to the Outlets page.
     */
    public void clickOutletsButton() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(outletsLink));
        outletsLink.click();
        Thread.sleep(5000); // Pause to allow page loading (for demonstration purposes)
        ExtentReportManager.logInfo("Clicked On Outlets Button...!");
    }
    
    
    // Method to click on the calendar button
    public void clickCalendarButton() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(calendarButton));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", calendarButton);
        calendarButton.click();
        Thread.sleep(7000); // Introducing a wait for 2 seconds
        ExtentReportManager.logInfo("Clicked On Calendar Button...!");
    }
   
}
