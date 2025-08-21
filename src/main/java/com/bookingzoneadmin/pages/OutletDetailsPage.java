package com.bookingzoneadmin.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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
public class OutletDetailsPage {
	private WebDriverWait wait;
	private WebDriver driver;

	// WebElement for Add Service button
	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root Mui')])[7]")
	private WebElement addServiceButton;

	// WebElement for Entertainment Radio button
	@FindBy(name = "BOWLING")
	private WebElement entRadio;

	// WebElement for Confirm button
	@FindBy(xpath = "//span[text()='Confirm']")
	private WebElement confirmButton;

	// WebElement for Uber dropdown
	@FindBy(xpath = "(//select[contains(@class,'MuiSelect')])[1]")
	private WebElement uberDropdown;

	// WebElement for Status dropdown
	@FindBy(xpath = "(//select[contains(@class,'MuiSelect')])[2]")
	private WebElement statusDropdown;

	// WebElement for Save button
	@FindBy(xpath = "//button[contains(@class,'MuiButton-root MuiButton-cont')]")
	private WebElement saveButton;

	// WebElement for the venue detail
	@FindBy(xpath = "//span[text()='Venue Detail']")
	private WebElement venueDetail; 

	// WebElement for the add venue details
	@FindBy(xpath = "//span[text()='Add Venue Detail']")
	private WebElement addVenueDetails; 

	@FindBy(xpath="//button//span//span[@class='MuiButton-startIcon MuiButton-iconSizeSmall css-y6rp3m-MuiButton-startIcon']")
	private WebElement   editbtnOfServiceCategory;

	@FindBy (xpath="(//button//span[@class='MuiButton-label css-8xplcm-MuiButton-label'])[5]")
	private WebElement  editBtnOfOutlet;

	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/button[1]/span[1]")
	private WebElement   addMoretbtn;

	@FindBy (xpath="//div//span[text()='Add ons']")
	private WebElement  addonsBtn;

	@FindBy (xpath="//div//span[text()='Add Items']")
	private WebElement addItemsBtn ;

	@FindBy (xpath="//div//span[text()='Update Items']")
	private WebElement updateItemsBtn ;

	@FindBy (xpath="//button//span[text()='Bookings']")
	private WebElement bookingBtn ;
	
	@FindBy (xpath="//input[contains(@class,'MuiOutlinedInput-input MuiInputBase-input MuiInputBase-inputAdornedSta')]")
	private WebElement searchBooking ;
	
	@FindBy (xpath="(//button[contains(@class,'MuiButton')])[20]")
	private WebElement bookingArrow ;

	/**
	 * Constructor to initialize PageFactory and the web elements.
	 * @param driver The WebDriver instance used to interact with the browser.
	 */
	public OutletDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
	}

	/**
	 * Method to click on Add Service button.
	 * This method opens the form to add a new service.
	 */
	public void clickAddServiceButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(addServiceButton));
		addServiceButton.click();
		ExtentReportManager.logInfo("Clicked on Add Service Button...!");
		Thread.sleep(2000); // Pause to allow actions to take effect
	}


	/**
	 * Method to click on Entertainment Radio button.
	 * This method selects the entertainment radio button.
	 */
	public void clickEntRadio() throws InterruptedException {
		entRadio.click();
		ExtentReportManager.logInfo("Clicked on Service Radio Button...!");
		Thread.sleep(2000); // Pause to allow actions to take effect
	}

	/**
	 * Method to click on Confirm button.
	 * This method confirms the action.
	 */
	public void clickConfirmButton() throws InterruptedException {
		confirmButton.click();
		ExtentReportManager.logInfo("Clicked on Confirm...!");
		Thread.sleep(2000); // Pause to allow actions to take effect
	}

	/**
	 * Method to select an option from Uber dropdown.
	 * This method selects an option from the Uber dropdown menu.
	 */
	public void selectUberOption() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(uberDropdown));
		uberDropdown.click(); // Click to open the dropdown
		Select selectUber = new Select(uberDropdown);
		selectUber.selectByVisibleText("Active"); // Selecting 'Active' option
		ExtentReportManager.logInfo("Uber selected...!");
		Thread.sleep(5000); // Pause to allow actions to take effect
	}

	/**
	 * Method to select an option from Status dropdown.
	 * This method selects an option from the Status dropdown menu.
	 */
	public void selectStatusOption() throws InterruptedException {
		statusDropdown.click(); // Click to open the dropdown
		Select selectStatus = new Select(statusDropdown);
		selectStatus.selectByVisibleText("Active"); // Selecting 'Active' option
		ExtentReportManager.logInfo("Status active selected...!");
		Thread.sleep(5000); // Pause to allow actions to take effect
	}

	/**
	 * Method to click on Save button.
	 * This method saves the changes made.
	 */
	public void clickSaveButton() throws InterruptedException {
		saveButton.click();
		ExtentReportManager.logInfo("Clicked on Save...!");
		Thread.sleep(2000); // Pause to allow actions to take effect
	}

	/**
	 * Method to click on venue detail.
	 * @throws InterruptedException if the thread is interrupted while waiting
	 */
	public void clickVenueDetail() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(venueDetail));
		venueDetail.click();
		ExtentReportManager.logInfo("Clicked on Venue Details tab...!");
		Thread.sleep(2000); // Pause to allow page loading (for demonstration purposes)
	}

	/**
	 * Method to click on add venue details.
	 * @throws InterruptedException if the thread is interrupted while waiting
	 */
	public void clickAddVenueDetails() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(addVenueDetails));
		addVenueDetails.click();
		ExtentReportManager.logInfo("Clicked on Add Venue Details...!");
		Thread.sleep(2000); // Pause to allow page loading (for demonstration purposes)
	}
	// Method to wait until the page is fully loaded
	public void waitForPageLoad() {
		wait.until(
				webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	public void clickEditBtnOfServiceCategory() throws InterruptedException 
	{    waitForPageLoad();
	wait.until(ExpectedConditions.elementToBeClickable(editbtnOfServiceCategory));
	editbtnOfServiceCategory.click();
	ExtentReportManager.logInfo("Clicked on Edit Button...!");
	Thread.sleep(2000);
	}

	public void clickEditBtnOfOutlet() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(editBtnOfOutlet));
		editBtnOfOutlet.click();
		ExtentReportManager.logInfo("Clicked On Edit button to Edit Outlet...!");
		Thread.sleep(5000);
	}


	public void clickAddMoreBtn() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(addMoretbtn));
		addMoretbtn.click();
		ExtentReportManager.logInfo("Clicked on AddMore Button...!");
		Thread.sleep(2000);
	}

	public boolean getvenueDetailsNotAdddedText( ) {
		try {
			// Check if the pop-up element is present in the DOM
			List<WebElement> elements = driver.findElements(By.xpath("//h5[contains(text(), 'Venue Details not added')]"));
			if (elements.isEmpty()) {
				// Element is not present
				System.out.println("Venue Details not added pop-up is not present.");
				return false;
			} else {
				// Element is present, now check if it is displayed
				boolean value = elements.get(0).isDisplayed();
				System.out.println("value is-" + value);
				return value;
			}
		} catch (Exception e) {
			// Handle any other unexpected exceptions
			System.out.println("An unexpected error occurred: " + e.getMessage());
			return false;
		}
	}

	public void clickAddonsBtn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(addonsBtn));	
		addonsBtn.click();
		ExtentReportManager.logInfo("Clicked on Addons Button...!");
		Thread.sleep(2000); // Pause to allow page loading (for demonstration purposes)
	}

	public void clickAddIemsBtn() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(addItemsBtn));
		addItemsBtn.click();
		ExtentReportManager.logInfo("Clicked on Add Items Button...!");
		Thread.sleep(5000);
	}

	public void clickUpdateIemsBtn() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(updateItemsBtn));
		updateItemsBtn.click();
		ExtentReportManager.logInfo("Clicked on UpdateItems Button...!");
		Thread.sleep(5000);
	}
	
	public void clickBookingBtn() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(bookingBtn));
		bookingBtn.click();
		ExtentReportManager.logInfo("Clicked on Bookings Button...!");
		
	}
	
	public void enterBookingId(String bookingId) throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(searchBooking));
		searchBooking.sendKeys(bookingId);
		ExtentReportManager.logInfo("Booking Id Entered...!");
		Thread.sleep(2000);
	}
	
	public void clickBookingArrow() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(bookingArrow));
		bookingArrow.click();
		ExtentReportManager.logInfo("Clicked on Booking Arrow...!");
		Thread.sleep(5000);
	}
}
