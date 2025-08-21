package com.bookingzoneadmin.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.bookingzoneadmin.utils.ExtentReportManager;

public class StepperPaymentPage {
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "//select[@name='paymentMethod']")
	private WebElement paymentMethodDropdown;

	@FindBy(xpath = "//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1o')]")
	private WebElement tipCrossButton;

	@FindBy(name = "te-connect-secure-pan")
	private WebElement cardNumberInput;

	@FindBy(xpath = "//input[@name='te-connect-secure-expiration']")
	private WebElement expiryDateInput;

	@FindBy(xpath = "//input[@name='te-connect-secure-cvc']")
	private WebElement cvcInput;

	@FindBy(xpath = "(//button//span[contains(text(),'Pay ')])")
	private WebElement payButton;

	@FindBy(name = "paymentReceived")
	private WebElement receivedCheckBox;

	@FindBy(xpath = "//button//span[text()='Confirm']")
	private WebElement confirmButton;

	@FindBy(xpath = "(//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid-sm-4 MuiGrid-grid-md-4 css-u')])[2]")
	private WebElement totalAmount;

	@FindBy(xpath = "//button//span[text()='Send Link']")
	private WebElement sendLinkButton;

	// WebElement for Pay via saved card checkbox
	@FindBy(xpath = "//span//span[@class='MuiIconButton-label css-4jkopv-MuiIconButton-label']")
	private WebElement payViaSavedCardCheckbox;

	@FindBy(xpath = "//span[text()='Pay via saved card']")
	private WebElement payViaSavedCardOption;

	@FindBy(xpath = "//input[@name='fullPayment']")
	private WebElement fullPayCheckBox;

	//Payment amount
	@FindBy(xpath = "//tr[@class='MuiTableRow-root css-1ebb4xh-MuiTableRow-root']//td[5]")
	private WebElement packagePrice;   

	@FindBy(xpath = "//tbody/tr[2]/td[5]")
	private WebElement additionalGuestPrice;

	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-grid-sm-12 css-vskk84-MuiGrid-root']//div[2]")
	private WebElement subTotal;

	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-sm-4 MuiGrid-grid-md-4 css-uycaae-MuiGrid-root'][3]")
	private WebElement paidAmount;

	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-sm-4 MuiGrid-grid-md-4 css-uycaae-MuiGrid-root'][4]")
	private WebElement balanceAmount;
	
	@FindBy(xpath = "//div[@id='notistack-snackbar']")
	private WebElement successMessage;
	
	@FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-item css-10texkh-MuiGrid-root'])[2]")
	private WebElement refreshBtn;

	public StepperPaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}


	// Method to click on the tip cross button
	public void clickTipCrossButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tipCrossButton));
		tipCrossButton.click();
		ExtentReportManager.logInfo("Clicked on tip cross button...!");
		Thread.sleep(3000); // Introducing a wait for 2 seconds
	}

	/**
	 * Method to select payment method
	 * @throws InterruptedException when interrupted while waiting
	 */
	public void selectPaymentMethod(String method ) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymentMethodDropdown));
		Select paymentMethodSelect = new Select(paymentMethodDropdown);
		paymentMethodSelect.selectByValue(method);
		ExtentReportManager.logInfo("Selected Payment Method Is- " + method);
		Thread.sleep(2000);
	}

	// Method to enter card number into the input field
	public void enterCardNumber(String cardNumber) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(cardNumberInput));
		cardNumberInput.sendKeys(cardNumber);
		ExtentReportManager.logInfo("Entered Card Number: " + cardNumber);
		Thread.sleep(2000); // Introducing a wait for 2 seconds
	}



	// Method to enter expiry date into the input field
	public void enterExpiryDate(String expiryDate) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(expiryDateInput));
		expiryDateInput.sendKeys(expiryDate);
		ExtentReportManager.logInfo("Entered Expiry Date: " + expiryDate);
		Thread.sleep(2000); // Introducing a wait for 2 seconds
	}

	// Method to enter CVC number into the input field
	public void enterCVC(String cvc) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(cvcInput));
		cvcInput.sendKeys(cvc);
		ExtentReportManager.logInfo("Entered CVC: " + cvc);
		Thread.sleep(2000); // Introducing a wait for 2 seconds
	}


	// Method to click on the pay button
	public void clickPayButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(payButton));
		payButton.click();
		ExtentReportManager.logInfo("Clicked on Pay Button...!");
		Thread.sleep(2000); // Introducing a wait for 2 seconds
	}

	/**
	 * Method to click on the received checkbox
	 * @throws InterruptedException when interrupted while waiting
	 */
	public void clickReceivedCheckBox() throws InterruptedException {
		receivedCheckBox.click();
		ExtentReportManager.logInfo("Clicked on Payment Receive Check Box...!");
	}

	/**
	 * Method to click on the confirm button for payment
	 * @throws InterruptedException when interrupted while waiting
	 */
	public void clickConfirmButton() throws InterruptedException {
		confirmButton.click();
		ExtentReportManager.logInfo("Clicked on Confirm Button...!");
		Thread.sleep(5000);
	}

	
	/**
	 * Clicks the Send Link button to send the payment link.
	 * @param driver The WebDriver instance to use
	 */
	public void clickSendLink() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
		sendLinkButton.click();
		ExtentReportManager.logInfo("Clicked On Send Link Button...!");
	}

	public boolean isPayViaSavedCardOptionAvailable() throws TimeoutException {
		System.out.println("if Condition"); 
		java.util.List<WebElement> savedCardOption = driver.findElements(By.xpath("//span[text()='Pay via saved card']"));
		if (!savedCardOption.isEmpty()) {
			return wait.until(ExpectedConditions.visibilityOf(savedCardOption.get(0))).isDisplayed();
		}
		return false;
	}

	/**
	 * Method to click on the Pay via saved card checkbox.
	 */
	public void clickPayViaSavedCard() {
	    try {
	        wait.until(ExpectedConditions.elementToBeClickable(payViaSavedCardCheckbox));
	        payViaSavedCardCheckbox.click();
	        ExtentReportManager.logInfo("Selected payment via saved card...!");
	    } catch (StaleElementReferenceException e) {
	        System.out.println("Caught StaleElementReferenceException. Retrying...");
	        // Re-locate the element and retry
	        payViaSavedCardCheckbox = driver.findElement(By.xpath("//input[@id='savedCardCheckbox']")); // Adjust locator as needed
	        wait.until(ExpectedConditions.elementToBeClickable(payViaSavedCardCheckbox));
	        payViaSavedCardCheckbox.click();
	        ExtentReportManager.logInfo("Selected payment via saved card after retry...!");
	    }
	}

	public void clickfullPayCheckBox() throws InterruptedException {
		fullPayCheckBox.click();
		Thread.sleep(2000);
	}

	public String getSubtotal() {

		return subTotal.getText();
	}


	public String getTotalAmount() {
		return (totalAmount.getText());
	}

	// Method to calculate the sum of all entries (package, additional guest, addons, etc.)
	public double calculateEntriesSum() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[contains(@class,'MuiTable-root')]//tbody/tr")));

		// Locate all rows within the tbody of the table
		List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'MuiTable-root')]//tbody//tr"));
		double entriesSum = 0.0;

		// Debugging: Check if rows are found
		if (rows.isEmpty()) {
			System.out.println("No rows found in the table.");
			return entriesSum; // Return 0 if no rows found
		} else {
			System.out.println("Found " + rows.size() + " rows in the table.");
		}

		// Loop through each row in the tbody and extract the "Amount" column
		for (WebElement row : rows) {
			try {
				// Debugging: Print the text of the row
				System.out.println("Processing row: " + row.getText());

				// Extract the 'Amount' from the last column (5th column in this case)
				WebElement amountElement = row.findElement(By.xpath(".//td[last()]"));
				String amountText = amountElement.getText().trim();

				// Debugging: Print the extracted amount text
				System.out.println("Extracted amount text: " + amountText);

				// Skip rows where the amount is "Free"
				if (!amountText.equalsIgnoreCase("Free")) {
					// Remove the dollar sign and convert the string to a double
					double amount = Double.parseDouble(amountText.replace("$", "").trim());
					entriesSum += amount;

					// Debugging: Print the running sum
					System.out.println("Current entries sum: $" + entriesSum);
				} else {
					System.out.println("Skipping 'Free' entry in row: " + row.getText());
				}
			} catch (NoSuchElementException e) {
				// Log and skip rows where the 'Amount' column is not found
				System.out.println("Skipping row due to missing 'Amount' column: " + row.getText());
			} catch (NumberFormatException e) {
				// Handle invalid number format cases
				System.out.println("Skipping row due to invalid amount format: " + row.getText());
			} catch (Exception e) {
				// Handle any other unexpected errors
				System.out.println("Unexpected error while processing row: " + row.getText() + ". Error: " + e.getMessage());
			}
		}

		// Debugging: Print the final calculated sum
		System.out.println("Final calculated entries sum: $" + entriesSum);
		return entriesSum;
	}


	// Method to calculate and verify the subtotal amount
	public void verifySubtotal() {
		// Calculate the sum of all entries from the table
		double expectedSubTotalAmount = calculateEntriesSum();

		// Get the subtotal as a String, remove "$", and convert to double
		String subtotalString = getSubtotal(); // Assuming it returns a String
		double actualSubtotalAmount = Double.parseDouble(subtotalString.replace("$", ""));

		// Log the expected and actual values
		ExtentReportManager.logInfo("Verifying Subtotal. Expected: $" + expectedSubTotalAmount + ", Actual: $" + actualSubtotalAmount);
		// Apply assertion for subtotal comparison
		Assert.assertEquals(actualSubtotalAmount, expectedSubTotalAmount, 
				"Subtotal mismatch! Expected: $" + expectedSubTotalAmount + ", but found: $" + actualSubtotalAmount);

		// Log info if assertion passes
		ExtentReportManager.logPass("Verify SubTotal Assertion Is Passed...!");
	}

	// Method to calculate and verify the total amount
	public void verifyTotalAmount() {

		// Extract Subtotal Amount
		String subtotalString = getSubtotal(); // Assuming it returns a String
		double subtotalAmount = Double.parseDouble(subtotalString.replace("$", ""));
		System.out.println("Converted Subtotal to double: " + subtotalAmount);

		// Extract Tax Charges (if exists, else assume 0)
		double taxCharges = 0.0;
		List<WebElement> taxElements = driver.findElements(By.xpath("//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid-sm-4 MuiGrid-grid-md-4 css-s8wdk0-MuiGrid-root')]")); // Replace with your tax element's xpath
		if (!taxElements.isEmpty()) {
			String taxChargesText = taxElements.get(0).getText(); // Extract the text
			taxCharges = Double.parseDouble(taxChargesText.replace("$", ""));
			System.out.println("Converted Tax Charges to double: " + taxCharges);
		} else {
			ExtentReportManager.logInfo("Tax Charges not found, assuming 0.");
			System.out.println("Tax Charges not found, assuming 0.");
		}

		// Extract Processing Fees (if exists, else assume 0)
		double processingFees = 0.0;
		List<WebElement> processingFeeElements = driver.findElements(By.xpath("//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-sm-4 MuiGrid-grid-md-4 css-jib8ak-MuiGrid-root']")); 
		if (!processingFeeElements.isEmpty()) {
			String processingFeesText = processingFeeElements.get(0).getText(); // Extract the text
			processingFees = Double.parseDouble(processingFeesText.replace("$", ""));
			System.out.println("Converted Processing Fees to double: " + processingFees);
		} else {
			ExtentReportManager.logInfo("Processing Fees not found, assuming 0.");
			System.out.println("Processing Fees not found, assuming 0.");
		}

		// Extract Tip Amount (if exists, else assume 0)
		double tipAmount = 0.0;
		List<WebElement> tipElements = driver.findElements(By.xpath("(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-sm-4 MuiGrid-grid-md-4 css-s8wdk0-MuiGrid-root'])[2]")); 
		if (!tipElements.isEmpty()) {
			String tipAmountText = tipElements.get(0).getText(); // Extract the text
			tipAmount = Double.parseDouble(tipAmountText.replace("$", ""));
			System.out.println("Converted Tip Amount to double: " + tipAmount);
		} else {
			ExtentReportManager.logInfo("Tip Amount not found, assuming 0.");
			System.out.println("Tip Amount not found, assuming 0.");
		}

		// Sum the values
		double expectedTotalAmount = subtotalAmount + taxCharges + processingFees + tipAmount;
		System.out.println("Calculated Expected Total Amount: $" + expectedTotalAmount);

		// Extract the displayed Total Amount
		String totalamount = getTotalAmount(); // Assuming it returns a String
		double actualTotalAmount = Double.parseDouble(totalamount.replace("$", ""));
		System.out.println("Converted Displayed Total Amount to double: " + actualTotalAmount);

		// Log the expected and actual values for verification
		ExtentReportManager.logInfo("Verifying Total Amount. Expected: $" + expectedTotalAmount + ", Actual: $" + actualTotalAmount);

		 // Define a tolerance for floating-point comparison
	    double tolerance = 0.01; 
		
		// Apply assertion for total amount comparison
		Assert.assertEquals(actualTotalAmount, expectedTotalAmount, tolerance,
				"Total amount mismatch! Expected: $" + expectedTotalAmount + ", but found: $" + actualTotalAmount);

		// Log a message if the assertion passes
		ExtentReportManager.logPass("Total Amount Assertion Passed. Expected and actual total amounts match.");
	}

	public double getPaidAmount() {

		String amount=paidAmount.getText();
		double paidAmount = Double.parseDouble(amount.replace("$", ""));
		return paidAmount;
	}

	public double getBalanceAmount() {

		String amount= balanceAmount.getText();
		double balanceAmount = Double.parseDouble(amount.replace("$", ""));
		return balanceAmount;
	}
	
	public void verifyEmailLinkSendMessage() 
	{
		wait.until(ExpectedConditions.elementToBeClickable(successMessage));
		String  ActualResult=successMessage.getText();
		String ExpectedResult="Payment Details send successfully.";
		Assert.assertEquals(ActualResult, ExpectedResult);
		ExtentReportManager.logPass("Email link sended assetion is passed...!");
	}
	
	public void clickRefreshButton() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(refreshBtn));
		refreshBtn.click();
		Thread.sleep(2000);
		ExtentReportManager.logInfo("Clicked On Refresh Button...!");
	}
	
	public boolean isPaymentSectionVisible() {
	    try {
	    	wait.until(ExpectedConditions.elementToBeClickable(paymentMethodDropdown));
	        return paymentMethodDropdown.isDisplayed();
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}

}
