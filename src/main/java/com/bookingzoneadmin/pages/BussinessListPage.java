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
public class BussinessListPage {

	private WebDriverWait wait;
	private WebDriver driver;

    // WebElement for Add Business button
    @FindBy(xpath = "//div[@class='css-13wmrxa']")
    private WebElement addBusinessButton;
    
 // WebElement to entered Business in the input feild
    @FindBy(xpath = "//input[@type='text']")
    private WebElement enterBusiness;

// WebElement for search Business button
    @FindBy(xpath = "//button//span[text()='Search']")
    private WebElement searchBusinessButton;
 
    // WebElement for Arrow (Go inside the bussiness)
    @FindBy(xpath = "//tbody/tr[1]/td[7]/a[1]/span[1]/*[1]")
    private WebElement arrow;
    
    /**
     * Constructor to initialize PageFactory and the web elements.
     * @param driver The WebDriver instance used to interact with the browser.
     */
    public BussinessListPage(WebDriver driver) {
    	this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Initialize WebDriverWait with a timeout of 10 seconds
    }

    /**
     * Method to click on the Add Business button.
     * This method opens the form to add a new business.
     * @throws InterruptedException 
     */
    public void clickAddBusinessButton() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(addBusinessButton));
        addBusinessButton.click();
		ExtentReportManager.logInfo("Add Business button clicked");
        Thread.sleep(2000);
    }
    
    
    // Enter email into the search box
    public void enterMail(String mail) throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(enterBusiness));
    	enterBusiness.sendKeys(mail);
    	ExtentReportManager.logInfo("Entered mail and searched for business account");
        Thread.sleep(2000);
    }

    // Click on the search button
    public void clickSearchBtn() throws InterruptedException {
    	searchBusinessButton.click();
    	ExtentReportManager.logInfo("Clicked On Search Button");
        Thread.sleep(2000);
    }
     

    // Click on the arrow
    public void clickBussinessArrow() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(arrow));
        arrow.click();
        ExtentReportManager.logInfo("Clicked On Business Arrow");
        Thread.sleep(2000);
    }
}
