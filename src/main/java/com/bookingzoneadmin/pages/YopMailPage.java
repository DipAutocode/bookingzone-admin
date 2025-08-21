package com.bookingzoneadmin.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzoneadmin.utils.ExtentReportManager;

public class YopMailPage {
	
	private WebDriver driver;
	private WebDriverWait wait;

    @FindBy(name = "login")
    private WebElement enterLoginName;

    @FindBy(xpath = "//button[@class='md']")
    private WebElement mailArrowButton;

    @FindBy(xpath = "(//button[@class='lm'])[1]")
    private WebElement clickMailButton;

    @FindBy(xpath = "//a[text()='Link']")
    private WebElement linkInMailButton;

    /**
     * Initializes the web elements using the PageFactory.
     * @param driver The WebDriver instance to use
     */
    public YopMailPage(WebDriver driver) {
    	this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Navigates to the YopMail website for checking emails.
     * @param driver The WebDriver instance to use
     * @throws InterruptedException when interrupted while waiting
     */
    public void navigateToYopMail() throws InterruptedException {
        // Open a new tab using JavaScript Executor
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank', '_blank');");

        // Switch to the newly opened tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Navigate to the desired URL in the new tab
        driver.navigate().to("https://yopmail.com/");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        ExtentReportManager.logInfo("Navigated to yopmail.com in a new tab...!");
    }


    /**
     * Enters the login name in the email login field.
     * @param name The name to enter
     * @throws InterruptedException when interrupted while waiting
     */
    public void enterLoginEmail(String name) throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(enterLoginName));
    	enterLoginName.sendKeys(name);
        ExtentReportManager.logInfo("Entered Login Email Name...!");
    }

    /**
     * Clicks the mail arrow button to access the email inbox.
     * @throws InterruptedException when interrupted while waiting
     */
    public void clickMailArrow() throws InterruptedException {
        mailArrowButton.click();
        ExtentReportManager.logInfo("Logged in into yop mail account...!");
    }

    /**
     * Clicks the email in the inbox for viewing the content.
     * @throws InterruptedException when interrupted while waiting
     */
    public void clickMail() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(clickMailButton));
    	clickMailButton.click();

    }

    /**
     * Clicks the link inside the email for proceeding with the payment.
     * @throws InterruptedException when interrupted while waiting
     */
    public void clickLinkInMail() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(linkInMailButton));
       linkInMailButton.click();
    }
}
