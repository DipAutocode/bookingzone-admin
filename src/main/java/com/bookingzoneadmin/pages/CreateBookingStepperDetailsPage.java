package com.bookingzoneadmin.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzoneadmin.utils.ExtentReportManager;

public class CreateBookingStepperDetailsPage {

	private WebDriverWait wait;
	private WebDriver driver;

	@FindBy(xpath = "//span[@role='progressbar']//*[name()='svg']")
	private WebElement loader;

	@FindBy(xpath = "(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeEnd MuiIconButton-sizeMedium css-fth4mk-MuiButtonBase-root-MuiIconButton-root')])[2]")
	private WebElement calendarSymbol;

	@FindBy(xpath = "//button[@aria-label='Next month']")
	private WebElement nextArrow;

	@FindBy(xpath = "//button[text()='22']")
	private WebElement selectDateButton;


	@FindBy(xpath = "//div[contains(@class,'MuiGrid-root css-2w1nhr-MuiGrid-root')]//button[1]")
	private WebElement selectReservationSlot1;

	@FindBy(xpath = "//div[contains(@class,'MuiGrid-root css-2w1nhr-MuiGrid-root')]//button[2]")
	private WebElement selectReservationSlot2;
	
	@FindBy(xpath = "(//button[contains(@class, 'bz-button-plus') and .//span[text()='+']])[1]")
	private WebElement selectPackageButton;

	@FindBy(xpath = "(//button[contains(@class, 'bz-button-plus') and .//span[text()='+']])[3]")
	private WebElement selectAddGuestButton;

	@FindBy(xpath = "//div//input[@name='Additional Guest']")
	private WebElement enterAddGuest;

	@FindBy(xpath = "(//button[contains(@class, 'bz-button-plus')])[2]")
	private WebElement selectSaleItem;

	
	// lane-per person

	@FindBy(xpath = "//img[@alt='Testing_perPerson']")
	private WebElement toLanePlan;


	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root MuiButton-c')])[7]")
	private WebElement selectPerson;

	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root MuiButton-c')])[9]")
	private WebElement selectItem;


	@FindBy(xpath = "//button//span[contains(text(),'Proceed to Pay')]")
	private WebElement proceedToPayButton;

	@FindBy(xpath = "(//input[@id='combo-box-demo'])[1]")
	private WebElement changeTime;

	@FindBy(xpath = "(//input[@id='combo-box-demo'])[2]")
	private WebElement enterMinutes;

	@FindBy(xpath = "(//input[@id='combo-box-demo'])[3]")
	private WebElement selectNoon;

	@FindBy(xpath = "//img[@alt='per_Hour']")
	private WebElement selectPerHourPlan;

	@FindBy(xpath = "//button//span[text()='1 Hours']")
	private WebElement selectHour;

	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root MuiButton-c')])[8]")
	private WebElement selectGuest;

	//per event

	@FindBy(xpath = "//img[contains(@alt,'Party Event')]")
	private WebElement selectPlan;

	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root MuiButton-outlined ')])[1]")
	private WebElement event1;

	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root M')])[18]")
	private WebElement selectChild;


	@FindBy(xpath = "(//button[contains(@class,'MuiButton-root MuiButton-outlined ')])[2]")
	private WebElement event3;


	public CreateBookingStepperDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
	}

	// Method to click on the calendar symbol button after ensuring the page is fully loaded
	public void clickCalendarSymbol() throws InterruptedException {
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

		wait.until(ExpectedConditions.invisibilityOf(loader));

		// Wait until the calendar symbol is clickable
		wait.until(ExpectedConditions.elementToBeClickable(calendarSymbol));

		// Click the calendar symbol
		calendarSymbol.click();

		// Log the action to Extent Report
		ExtentReportManager.logInfo("Clicked on calendar symbol...!");

		
	}


	// Method to click on the next arrow button
	   public void clickNextArrow() throws InterruptedException {
	    wait.until(webDriver -> ((JavascriptExecutor) webDriver)
	        .executeScript("return document.readyState").equals("complete"));
	    
	    wait.until(ExpectedConditions.elementToBeClickable(nextArrow));
	    
	    try {
	    	nextArrow.click();
	    } catch (ElementClickInterceptedException e) {
	        ExtentReportManager.logInfo("Standard click failed. Trying JS click...");
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextArrow);
	    }

	    ExtentReportManager.logInfo("Clicked on next month arrow on calendar...!");
	    Thread.sleep(2000);
	}


	// Method to select a date from the calendar
	public void selectDate(String date) throws InterruptedException {
		WebElement dateXpath=driver.findElement(By.xpath("//button[text()='" + date + "']"));
		wait.until(ExpectedConditions.elementToBeClickable(dateXpath));
		dateXpath.click();
		ExtentReportManager.logInfo("Selected Booking Date: " + date);
		Thread.sleep(7000); // Introducing a wait for 7 seconds
	}

	// Method to select a plan from the plan list
	public void selectPlan(String plan) throws InterruptedException {
		try {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='css-mmwbni']")));
		 String planXpathStr = "//div//p[text()='" + plan + "']";
	        WebElement planElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(planXpathStr)));

	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", planElement);
	        ExtentReportManager.logInfo("Selected Plan: " + plan);
	    } catch (Exception e) {
	        ExtentReportManager.logFail("Failed to select plan: " + plan + " - " + e.getMessage());
	        throw e;
	    }
}


	// Method to select duration of the booking
	public void selectReservationSlot1() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectReservationSlot1));
		selectReservationSlot1.click();
		ExtentReportManager.logInfo("First Reservation Slot Selected...!");
		Thread.sleep(5000); 
	}
	
	public void selectReservationSlot2() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectReservationSlot2));
		selectReservationSlot2.click();
		ExtentReportManager.logInfo("Second Reservation Slot Selected...!");
		Thread.sleep(5000);
	}


	public void selectPackage() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOf(selectPackageButton));
			wait.until(ExpectedConditions.elementToBeClickable(selectPackageButton));

			// Ensure element is displayed and enabled
			if (selectPackageButton.isDisplayed() && selectPackageButton.isEnabled()) {
				try {
					// Scroll the element into view
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectPackageButton);

					// Try clicking the package button
					selectPackageButton.click();
					ExtentReportManager.logInfo("Package Selected...!");
				} catch (Exception e) {
					// If standard click fails, try clicking using JavaScript
					ExtentReportManager.logInfo("Standard click failed. Trying with JavaScript click.");
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectPackageButton);
				}
			} else {
				ExtentReportManager.logFail("Package button is either not displayed or not enabled.");
			}

		} catch (Exception e) {
			// Log the exception and handle it
			ExtentReportManager.logFail("Failed to select package due to exception: " + e.getMessage());
			throw new RuntimeException("Failed to select package", e);
		}
		Thread.sleep(3000);
	}


	// Method to select add guest option
	public void selectAddGuest() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOf(selectAddGuestButton));
			wait.until(ExpectedConditions.elementToBeClickable(selectAddGuestButton));

			// Ensure element is displayed and enabled
			if (selectAddGuestButton.isDisplayed() && selectAddGuestButton.isEnabled()) {
				try {
					// Scroll the element into view
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectAddGuestButton);

					// Try clicking the package button
					selectAddGuestButton.click();
					ExtentReportManager.logInfo("Additional Guest Selected...!");
				} catch (Exception e) {
					// If standard click fails, try clicking using JavaScript
					ExtentReportManager.logInfo("Standard click failed. Trying with JavaScript click...!");
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectAddGuestButton);
				}
			} else {
				ExtentReportManager.logFail("Package button is either not displayed or not enabled.");
			}

		} catch (Exception e) {
			// Log the exception and handle it
			ExtentReportManager.logFail("Failed to select package due to exception: " + e.getMessage());
			throw new RuntimeException("Failed to select package", e);
		}
		Thread.sleep(5000);
	}

	public void enterAddtionalGuestCount(String count) throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(enterAddGuest));

		// Use JavaScript to clear the field
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='';", enterAddGuest); 
		System.out.println("Additional Guest Input Field Cleared with JavaScript...!");

		enterAddGuest.sendKeys(count);
		ExtentReportManager.logInfo("Additional Guest Count Entered...!");
		Thread.sleep(2000);
	}
	
	public void selectSaleItem() throws InterruptedException 
	{
		wait.until(ExpectedConditions.visibilityOf(selectSaleItem));
		wait.until(ExpectedConditions.elementToBeClickable(selectSaleItem));
		selectSaleItem.click();
		ExtentReportManager.logInfo("Sale Item Selected...!");
		Thread.sleep(3000);
	}

	//per person
	public void selectToLanePlan() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(toLanePlan));
		toLanePlan.click();
		Thread.sleep(2000);
	}


	public void selectPersonCount(WebDriver driver) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectPerson));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", selectPerson);
		selectPerson.click();
		Thread.sleep(2000);
	}

	public void selectSaleItem(WebDriver driver) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectItem));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", selectItem);
		selectItem.click();
		Thread.sleep(2000);
	}

	// Method to click on proceed to pay button
	public void clickProceedToPayButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(proceedToPayButton));
		proceedToPayButton.click();
		ExtentReportManager.logInfo("Proceeded to payment...!");
		Thread.sleep(5000); // Introducing a wait for 2 seconds
	}

	//per hour 

	/**
	 * Edit the time using the given time string.
	 *
	 * @param driver the WebDriver instance
	 * @param time   the time to be edited
	 * @throws InterruptedException 
	 */
	public void enterBookingTime(String time) throws InterruptedException {
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		wait.until(ExpectedConditions.invisibilityOf(loader));

		wait.until(ExpectedConditions.elementToBeClickable(changeTime));
		Actions actions = new Actions(driver);
		actions.click(changeTime).sendKeys(time).sendKeys(Keys.ENTER).perform();
		ExtentReportManager.logInfo("Booking Hour Entered...!");
		Thread.sleep(2000);
	}


	/**
	 * Select the previous time using the given time string.
	 *
	 * @param driver the WebDriver instance
	 * @param time   the time to be selected
	 */
	public void selectPreviousTime(String time) {
		Actions actions = new Actions(driver);
		actions.click(changeTime).sendKeys(time).sendKeys(Keys.ENTER).perform();
	}

	// Enter minutes in the input field
	public void enterMinutes(String minutes) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(enterMinutes));	
		Actions actions = new Actions(driver);
		actions.click(enterMinutes).perform();
		actions.sendKeys(minutes).perform();
		actions.sendKeys(Keys.ENTER).perform();
		ExtentReportManager.logInfo("Booking Minute Entered...!");

	}



	/**
	 * Select the per hour plan.
	 *
	 * @throws InterruptedException if the thread sleep is interrupted
	 */
	public void selectPerHourPlan() throws InterruptedException {
		selectPerHourPlan.click();
	}

	/**
	 * Select 1 hour.
	 *
	 * @throws InterruptedException if the thread sleep is interrupted
	 */
	public void select1Hour() throws InterruptedException {
		selectHour.click();
		Thread.sleep(2000);
	}

	/**
	 * Select guest and scroll to view if needed.
	 *
	 * @param driver the WebDriver instance
	 * @throws InterruptedException if the thread sleep is interrupted
	 */
	public void selectGuest(WebDriver driver) throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", selectGuest);
		selectGuest.click();
	}

	// Select AM start time
	public void selectNoon(String time) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectNoon));
		Actions actions = new Actions(driver);
		actions.click(selectNoon).perform();
		actions.sendKeys(time).perform();
		actions.sendKeys(Keys.ARROW_DOWN).perform();
		actions.sendKeys(Keys.ENTER).perform();
		ExtentReportManager.logInfo("Booking Noon Entered...!");
	}


	// per event 


	// Method to click on the 'Select Plan' button
	//    public void selectPerEventPlan() throws InterruptedException {
	//        planName.click();
	//        Thread.sleep(2000);
	//    }

	// Method to click on 'Event 1'
	public void clickEvent1() throws InterruptedException {
		event1.click();
	}

	// Method to scroll and select a child element
	public void selectChild(WebDriver driver) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", selectChild);
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", selectChild);
		selectChild.click();
	}


	// Method to click on 'Event 3'
	public void clickEvent3() throws InterruptedException {
		event3.click();
		Thread.sleep(2000);
	}


}
