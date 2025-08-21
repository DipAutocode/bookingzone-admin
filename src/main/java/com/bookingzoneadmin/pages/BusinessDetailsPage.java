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
public class BusinessDetailsPage {
	private WebDriverWait wait;
	private WebDriver driver;
	
    // WebElement for Add new outlet Button
	 @FindBy(xpath = "//a[contains(@class, 'MuiButton-root MuiButton-c')]")
	    private WebElement addNewOutletButton;
	 
	// WebElement for Click Arrow button
	    @FindBy(xpath = "(//button[contains(@class,'MuiButtonBase-root MuiI')])[3]")
	    private WebElement clickArrow;

    /**
     * Constructor to initialize PageFactory and the web elements.
     * @param driver The WebDriver instance used to interact with the browser.
     */
    public BusinessDetailsPage(WebDriver driver) {
    	this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    
    
 // Click on the add outlet button
    public void clickAddNewOutletButton() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(addNewOutletButton));
    	addNewOutletButton.click();
    	ExtentReportManager.logInfo("Clicked On Add Outlet Button");
        Thread.sleep(2000);
    }
    
    public void clickOutletArrow() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(clickArrow));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", clickArrow);
        ExtentReportManager.logInfo("Clicked On Outlet Arrow...!");
        Thread.sleep(5000); // Pause to allow actions to take effect
    }

}
