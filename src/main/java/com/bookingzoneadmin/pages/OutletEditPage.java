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
 * This class represents the Edit Outlet functionality in the application.
 */
public class OutletEditPage {
	private WebDriverWait wait;
	private WebDriver driver;

   
    @FindBy(xpath = "//button//span[text()='Update Outlet']")
    private WebElement updateOutletBtn;
   
    /**
     * Constructor to initialize elements using PageFactory.
     * @param driver The WebDriver instance.
     */
    public OutletEditPage(WebDriver driver) {
    	this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
    }

   
   
    public void clickUpdateOutletBtn() throws InterruptedException {
   	 wait.until(ExpectedConditions.elementToBeClickable(updateOutletBtn)); 
   	updateOutletBtn.click();
   	ExtentReportManager.logInfo("Clicked on Update Outlet Button...!");
   	Thread.sleep(4000);
   }

}
