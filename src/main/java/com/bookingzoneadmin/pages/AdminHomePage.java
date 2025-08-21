package com.bookingzoneadmin.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzoneadmin.utils.ExtentReportManager;

/**
 * This class represents the functionality related to adding a business in the admin interface.
 */
public class AdminHomePage {
	private WebDriverWait wait;

    // WebElement for Businesses link
    @FindBy(xpath = "//div[text()='Businesses']")
    private WebElement businessesLink;


    /**
     * Constructor to initialize PageFactory and the web elements.
     * @param driver The WebDriver instance used to interact with the browser.
     */
    public AdminHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Initialize WebDriverWait with a timeout of 10 seconds
    }

    /**
     * Method to click on the Businesses link.
     * This method navigates to the Businesses page.
     * @throws InterruptedException 
     */
    public void clickBusinessesLink() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(businessesLink));
        businessesLink.click();
		ExtentReportManager.logInfo("Business button clicked");
        Thread.sleep(2000);
    }

   
}
