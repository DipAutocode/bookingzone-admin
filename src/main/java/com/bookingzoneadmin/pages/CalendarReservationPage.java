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

public class CalendarReservationPage {
	private WebDriverWait wait;
	private WebDriver driver;

	 @FindBy(xpath = "(//select[contains(@class,'MuiSelect-root MuiSelect-selec')])[1]")
	private WebElement outletDropDown;

	@FindBy(xpath = "//input[@name='name']")
	private WebElement nameInput;

	@FindBy(name = "bookingEmail")
	private WebElement emailInput;

	@FindBy(xpath = "(//button[contains(@class,'MuiButton-')])[2]")
	private WebElement createReservationButton;

	@FindBy(xpath = "(//div//td[contains(@class,'fc-timeline-slot fc-timeline-s')])[19]")
	private WebElement scrollToBooking;

	@FindBy(xpath="//tbody//tr//td[19]")
	private WebElement   cellClick;


	@FindBy(xpath="//span[normalize-space()='View Reservations']")
	private WebElement  viewReservationButton ;

	@FindBy(xpath = "//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid-sm-1 ')]")
	private WebElement refreshButton;


	public CalendarReservationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));

	}
	
	 // Method to wait until the page is fully loaded
    public void waitForPageLoad() {
       wait.until(
            webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

	// Method to select an outlet from the dropdown
	public void selectOutlet(String outletName) throws InterruptedException {
		
		waitForPageLoad();
		wait.until(ExpectedConditions.elementToBeClickable(outletDropDown));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", outletDropDown);
		Thread.sleep(2000);
		Select select = new Select(outletDropDown);
		select.selectByVisibleText(outletName);
		ExtentReportManager.logInfo("Selected Outlet: " + outletName);
	}

	// Method to enter customer name into the input field
	public void enterCustomerName(String customerName) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(nameInput));
		nameInput.sendKeys(customerName);
		ExtentReportManager.logInfo("Entered Customer Name: " + customerName);
		Thread.sleep(2000); // Introducing a wait for 2 seconds
		
	}

	// Method to enter email into the input field
	public void enterEmail(String email) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(emailInput));
		emailInput.sendKeys(email);
		ExtentReportManager.logInfo("Entered Customer Email: " + email);
		Thread.sleep(2000); // Introducing a wait for 2 seconds
	}

	// Method to click on the create reservation button
	public void clickCreateReservationButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(nameInput));
		createReservationButton.click();
		ExtentReportManager.logInfo("Clicked on create reservation button...!");
		Thread.sleep(2000); // Introducing a wait for 2 seconds
	}


	public void scrollFor9AM(WebDriver driver) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", scrollToBooking);
		Thread.sleep(2000);
	}

	// for create booking by cell click on admin 
	public void clickCalendarCell() throws InterruptedException 
	{
		cellClick.click();
		Thread.sleep(2000);
	}


	public void clickViewReservationButton() throws InterruptedException 
	{
		viewReservationButton.click();
		ExtentReportManager.logInfo("Clicked on View Reservation Button...!");
	}

	/**
	 * Click the refresh button.
	 *
	 * @param driver the WebDriver instance
	 */
	public void clickRefreshButton(WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", refreshButton);
	}


}
