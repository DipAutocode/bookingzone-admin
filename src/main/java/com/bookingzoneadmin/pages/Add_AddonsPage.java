package com.bookingzoneadmin.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzoneadmin.utils.ExtentReportManager;

/**
 * This class represents the Edit_Addon functionality in the application.
 */
public class Add_AddonsPage {
    
	private WebDriverWait wait;
	private WebDriver driver;

    // WebElement for selecting an addon
    @FindBy(xpath = "(//button//span[text()='+'])[1]")
    private WebElement selectAddon;

   
    /**
     * Constructor to initialize elements using PageFactory.
     * @param driver The WebDriver instance.
     */
    public Add_AddonsPage(WebDriver driver) {
    	this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Method to select an addon.
     * @param driver The WebDriver instance.
     * @throws InterruptedException If the thread is interrupted.
     */
    public void selectAllAddons() throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        for (int i = 1; i <= 4; i++) {
            WebElement addon = driver.findElement(By.xpath("(//button//span[text()='+'])[" + i + "]"));
            
            for (int attempt = 0; attempt < 3; attempt++) {
                try {
                    executor.executeScript("arguments[0].scrollIntoView(true);", addon);
                    executor.executeScript("arguments[0].click();", addon);
                    ExtentReportManager.logInfo("Addon " + i + " selected...!");
                    Thread.sleep(500);  // Small delay for UI stability
                    break; // Exit retry loop if successful
                } catch (Exception e) {
                    Thread.sleep(300); // Retry delay
                }
            }
        }
    }



}
