package com.bookingzoneadmin.pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;

import com.bookingzoneadmin.utils.ExtentReportManager;

public class BookingDetailsPage {

	private WebDriverWait wait;
	private WebDriver driver;

	@FindBy(xpath = "//button//span[text()='Transaction History']")
	private WebElement transactionHistory;

	@FindBy(xpath = "(//table[@class='MuiTable-root css-ku5hnq-MuiTable-root']//tbody)[2]")
	private WebElement transactionTableBody;

	@FindBy(xpath = "//button//span[text()='Booking Detail']")
	private WebElement bookingDetail;

	@FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-sm-4 MuiGrid-grid-md-4 css-uycaae-MuiGrid-root'])[5]")
	private WebElement totalAmountPaid;

	@FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-sm-4 MuiGrid-grid-md-4 css-uycaae-MuiGrid-root'])[9]")
	private WebElement refundableAmount;

	@FindBy(xpath = "(//button//span[@class='MuiIconButton-label css-4jkopv-MuiIconButton-label'])[12]")
	private WebElement refundIcon;

	@FindBy(xpath = "//select[@name='refundMethod']")
	private WebElement refundMethodDropDown;

	@FindBy(id = "refund-symbol")
	private WebElement refundType;

	@FindBy(xpath = "//ul//li[text()='%']")
	private WebElement percentageSymbol;

	@FindBy(xpath = "//div//input[@name='refundAmount']")
	private WebElement refundAmount;

	@FindBy(xpath = "//button//span[text()='Save']")
	private WebElement saveBtn;

	@FindBy(xpath = "//button//span[contains(text(),'Refund')]")
	private WebElement refundBtn;

	@FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-sm-4 MuiGrid-grid-md-4 css-uycaae-MuiGrid-root'])[8]")
	private WebElement refundedAmount;

	@FindBy(xpath = "//div[@id='notistack-snackbar']")
	private WebElement refundInitiateddMsg;

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-6z1s8t-MuiButtonBase-root-MuiIconButton-root']")
	private WebElement crossBtnToCloseBookingDetails;

	@FindBy(xpath = "(//button//span[@class='MuiIconButton-label css-4jkopv-MuiIconButton-label'])[6]")
	private WebElement crossBtnToCloseBooking;

	@FindBy(xpath = "//div//p[@class='MuiTypography-root MuiTypography-body1 css-1wu95qb-MuiTypography-root']")
	private WebElement paymentUrl;

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-6z1s8t-MuiButtonBase-root-MuiIconButton-root']")
	private WebElement crossBtnBookingDetailsPage;

	@FindBy(xpath = "(//div//span[text()='cancelled'])[2]")
	private WebElement cancelStatus;

	public BookingDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// Method to click on the calendar symbol button after ensuring the page is fully loaded
	public void clicktransactionHistory() throws InterruptedException {
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

		// Wait until the calendar symbol is clickable
		wait.until(ExpectedConditions.elementToBeClickable(transactionHistory));

		transactionHistory.click();
		ExtentReportManager.logInfo("Clicked On Transaction History...!");
		Thread.sleep(2000);
	}

	public void verifyPaymentMethodAndPaidAmount(String payMethod1, String payMethod2, double paidAmount, double balanceAmount) {
		wait.until(ExpectedConditions.visibilityOf(transactionTableBody));

		// Get all rows in the table body
		List<WebElement> rows = transactionTableBody.findElements(By.xpath(".//tr"));

		// If no payment has been done, handle gracefully
		if (rows.isEmpty()) {
			ExtentReportManager.logInfo("No payment entries found in the transaction history. Payment may not have been done.");
			System.out.println("No payment entries found.");
			return;  // Exit the method if there are no rows
		}

		// Expected values for payment methods and amounts
		List<String> expectedPaymentMethods = Arrays.asList(payMethod1, payMethod2);
		List<Double> expectedAmounts = Arrays.asList(paidAmount, balanceAmount);

		// Check if the number of rows matches the expected data size
		Assert.assertEquals(rows.size(), expectedPaymentMethods.size(), "Number of payments does not match the expected count.");

		// Loop through each row and compare it with the expected values
		for (int i = 0; i < rows.size(); i++) {
			WebElement row = rows.get(i);

			// Declare the variable outside the if-else block
			String actualPaymentMethod;

			// Fetch the payment method (2nd column)
			String paymentMethod = row.findElement(By.xpath(".//td[2]")).getText();

			// Check if the payment method starts with "other"
			if (paymentMethod.startsWith("other")) {
				actualPaymentMethod = paymentMethod.substring(0, 5);  // Extract "other"
			} else {
				actualPaymentMethod = paymentMethod.substring(0, 4);  // Extract "mppg", "cash", etc.
			}

			System.out.println("Actual Payment Method Is- " + actualPaymentMethod);

			// Fetch the amount (5th column, align right)
			String amountText = row.findElement(By.xpath(".//td[5]")).getText();
			double actualAmount = Double.parseDouble(amountText.replace("$", "").replace(",", ""));

			// Assertions for payment method and amount
			Assert.assertEquals(actualPaymentMethod, expectedPaymentMethods.get(i), "Payment method did not match for row " + (i + 1));
			Assert.assertEquals(actualAmount, expectedAmounts.get(i), "Amount did not match for row " + (i + 1));

			ExtentReportManager.logInfo("Expected Payment Method For Row " + (i + 1) + " Is- " + expectedPaymentMethods.get(i) +
					" and Expected Payment Amount For Row " + (i + 1) + " Is- " + expectedAmounts.get(i));

			ExtentReportManager.logInfo("Actual Payment Method For Row " + (i + 1) + " Is- " + actualPaymentMethod +
					" and Actual Payment Amount For Row " + (i + 1) + " Is- " + actualAmount);

			// Optionally, print out the values for debugging or logging
			System.out.println("Row " + (i + 1) + ": Payment Method: " + actualPaymentMethod + " | Amount: $" + actualAmount);
		}
	}


	public void clickbookingDetail() throws InterruptedException 
	{
		bookingDetail.click();
		ExtentReportManager.logInfo("Clicked On Booking Detail...!");
		Thread.sleep(2000);
	}

	public double getTotalAmountPaid() {
		wait.until(ExpectedConditions.visibilityOf(totalAmountPaid));
		String amount=totalAmountPaid.getText();
		double paidAmount = Double.parseDouble(amount.replace("$", ""));
		return paidAmount;
	}

	public double getRefundableAmount() {
		wait.until(ExpectedConditions.visibilityOf(refundableAmount));
		String amount=refundableAmount.getText();
		double refundableAmount = Double.parseDouble(amount.replace("$", ""));
		return refundableAmount;
	}

	public void clickRefundIcon() throws InterruptedException 
	{
		wait.until(ExpectedConditions.visibilityOf(refundIcon));
		refundIcon.click();
		ExtentReportManager.logInfo("Clicked On Refund Icon...!");
		Thread.sleep(2000);
	}

	public void selectRefundMethod(String refundMethod) throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(refundMethodDropDown));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", refundMethodDropDown);
		Thread.sleep(2000);
		Select select = new Select(refundMethodDropDown);
		select.selectByValue(refundMethod);
		ExtentReportManager.logInfo("Refund Method Selected Is- : " + refundMethod);
	}

	public void selectRefundType() throws InterruptedException 
	{
		refundType.click();
		ExtentReportManager.logInfo("Clicked On Refund Type DropDown...!");
		wait.until(ExpectedConditions.elementToBeClickable(percentageSymbol));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", percentageSymbol);
		ExtentReportManager.logInfo("Clicked On Percentage Symbol...!");
		Thread.sleep(2000);
	}

	public void enterRefundAmount(String amount) 
	{
		refundAmount.sendKeys(amount);
		ExtentReportManager.logInfo("Refund Amount Entered...!");
	}

	public void clickSaveButton() throws InterruptedException 
	{
		saveBtn.click();
		ExtentReportManager.logInfo("Clicked On Save Button...!");
		Thread.sleep(2000);
	}

	public void clickRefundButton() throws InterruptedException 
	{
		refundBtn.click();
		ExtentReportManager.logInfo("Clicked On Refund Button...!");
		Thread.sleep(2000);	
	}

	public void verifyRefundSuccessfulMessage() throws InterruptedException 
	{
		try {

			wait.until(ExpectedConditions.visibilityOf(refundInitiateddMsg));

			if (refundInitiateddMsg.isDisplayed()) {
				ExtentReportManager.logPass("Refund success notification is visible. Refund operation successful...!");
			}
		} catch (TimeoutException e) {
			// Handle the case where the element is not found or not visible within the timeout
			ExtentReportManager.logFail("Refund success notification did not appear. Refund operation failed.");
			Assert.fail("Refund operation failed due to notification not being displayed.");
		}

	}

	public double getRefundedAmount() {
		wait.until(ExpectedConditions.visibilityOf(refundedAmount));
		String amount = refundedAmount.getText();

		amount = amount.replace("%", "").replace("$", "").replace(",", "").trim();

		// Now safely parse the cleaned-up string to double
		double refundedAmount = Double.parseDouble(amount);
		return refundedAmount;
	}

	public void closeBookingDetailsPage() throws InterruptedException {
		try {

			// Wait until the element is clickable
			wait.until(ExpectedConditions.elementToBeClickable(crossBtnToCloseBookingDetails));

			crossBtnToCloseBookingDetails.click();
			ExtentReportManager.logInfo("Booking Details Page Is Closed...!");

		} catch (ElementClickInterceptedException e) {
			// If the element is intercepted, using JavaScript Executor to click
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", crossBtnToCloseBookingDetails);
			ExtentReportManager.logInfo("Booking Details Page Is Closed via JS Click...!");

		} finally {
			Thread.sleep(2000); // Sleep after closing to ensure page is fully updated
		}
	}


	public void closeBooking() throws InterruptedException {
		try {
			// Wait until the element is clickable
			wait.until(ExpectedConditions.elementToBeClickable(crossBtnToCloseBooking));

			crossBtnToCloseBooking.click();
			ExtentReportManager.logInfo("Booking Is Closed...!");

		} catch (ElementClickInterceptedException e) {
			// If the element is intercepted, click using JavaScript Executor
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", crossBtnToCloseBooking);
			ExtentReportManager.logInfo("Booking Is Closed via JS Click...!");

		} finally {
			// Add a slight delay after closing the booking to ensure page update
			Thread.sleep(2000);
		}
	}

	//Close booking on test method failure
	public void closeOnTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentReportManager.logInfo("Test failed: " + result.getName() + ". Checking for booking popup/stepper...");

			try {
				if (isBookingDetailsPopupVisible()) {
					closeBookingDetailsPage();
					ExtentReportManager.logInfo("Closed booking details popup after failure.");
				} else if (isBookingStepperSectionVisible()) {
					closeBooking();
					ExtentReportManager.logInfo("Closed booking stepper after failure.");
				}
			} catch (Exception e) {
				ExtentReportManager.logFail("Error while closing booking after failure: " + e.getMessage());
			}
		}
	}

	//Close booking after each class
	public void closeAfterClass() {
		ExtentReportManager.logInfo("Running @AfterClass cleanup...");

		try {
			if (isBookingDetailsPopupVisible()) {
				closeBookingDetailsPage();
				ExtentReportManager.logInfo("Closed booking details popup in @AfterClass.");
				
				  // After closing booking details, check if booking stepper is still open
	            if (isBookingStepperSectionVisible()) {
	                closeBooking();
	                ExtentReportManager.logInfo("Closed booking stepper after closing details popup in @AfterClass.");
	            }
	            
			} else if (isBookingStepperSectionVisible()) {
				closeBooking();
				ExtentReportManager.logInfo("Closed booking stepper in @AfterClass.");
			}
		} catch (Exception e) {
			ExtentReportManager.logFail("Error while closing booking in @AfterClass: " + e.getMessage());
		}
	}

	private boolean isBookingDetailsPopupVisible() {
		try {
			return crossBtnToCloseBookingDetails.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	private boolean isBookingStepperSectionVisible() {
		try {
			return crossBtnToCloseBooking.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void copyPaymentUrlAndHitOnNewTab() 
	{
		try {
			wait.until(ExpectedConditions.visibilityOf(paymentUrl));
			// Extract the URL text
			String paymentUrlText = paymentUrl.getText().replace("Payment Url: ", "");

			// Open a new tab
			driver.switchTo().newWindow(WindowType.TAB);

			// Navigate to the extracted URL
			driver.get(paymentUrlText);

			// Perform further actions as needed on the new tab
			System.out.println("Navigated to the Payment URL: " + driver.getCurrentUrl());
			ExtentReportManager.logInfo("Navigated to the Payment URL: " + driver.getCurrentUrl());

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void clickCrossBtn() 
	{
		wait.until(ExpectedConditions.elementToBeClickable(crossBtnBookingDetailsPage));
		crossBtnBookingDetailsPage.click();
	}

	public void verifyCancelStatus() 
	{
		wait.until(ExpectedConditions.visibilityOf(cancelStatus));
		// Get the text of the cancelStatus element
		String actualStatusText = cancelStatus.getText().trim();

		// Define the expected text
		String expectedStatusText = "Cancelled";

		// Assert that the actual text matches the expected text
		Assert.assertEquals(actualStatusText, expectedStatusText, 
				"The cancel status text does not match the expected value!");

		// Log the success message
		ExtentReportManager.logPass("Cancel status verified successfully: " + actualStatusText);


	}



}
