package com.bookingzoneadmin.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzoneadmin.utils.ExtentReportManager;

/**
 * This class represents the functionality related to adding a service in the admin interface.
 */
public class OutletListPage {

	private WebDriverWait wait;
    private WebDriver driver;

    // WebElement for Click Arrow button
    @FindBy(xpath = "(//button[contains(@class,'MuiButton')])[5]")
    private WebElement clickArrow;

   
    public OutletListPage(WebDriver driver) {
    	this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Initialize WebDriverWait with a timeout of 10 seconds
    }

    // Method to wait until the page is fully loaded
    public void waitForPageLoad() {
       wait.until(
            webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Method to click on Click Arrow button using JavaScriptExecutor.
     * This method clicks the arrow button using JavaScriptExecutor for better compatibility.
     */
    public void clickArrow() throws InterruptedException {
    	waitForPageLoad();
    	wait.until(ExpectedConditions.elementToBeClickable(clickArrow));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", clickArrow);
        ExtentReportManager.logInfo("Clicked On Outlets Arrow...!");
        Thread.sleep(5000); // Pause to allow actions to take effect
    }

    
}
