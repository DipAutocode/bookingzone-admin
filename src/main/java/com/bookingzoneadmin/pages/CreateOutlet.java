package com.bookingzoneadmin.pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzoneadmin.utils.ExtentReportManager;

public class CreateOutlet {
	Logger logger = (Logger) LogManager.getLogger(this.getClass());
	private WebDriverWait wait;
	private WebDriver driver;

	@FindBy(name = "name")
	private WebElement outletName;

	@FindBy(name = "email")
	private WebElement outletEmail;

	@FindBy(name = "address.street")
	private WebElement flatNo;

	@FindBy(name = "address.state")
	private WebElement state;

	@FindBy(xpath = "//li[@id='mui-52436-option-0']")
	private WebElement newYork;

	@FindBy(name = "address.city")
	private WebElement city;

	@FindBy(name = "address.zip")
	private WebElement zipCode;

	@FindBy(name = "region")
	private WebElement region;

	@FindBy(xpath = "//div[contains(@class,'MuiAutocomplete-popper')]")
	private List<WebElement>  listOptionsState;

	@FindBy(css = ".MuiAutocomplete-option") // Update this selector to match the dropdown options
	private List<WebElement> listOptionsCity;


	@FindBy(name = "payment.custCode")
	private WebElement custumerCode;

	@FindBy(name = "payment.username")
	private WebElement username;

	@FindBy(name = "payment.password")
	private WebElement password;

	@FindBy(name = "payment.base64")
	private WebElement authHeader;

	@FindBy(name = "payment.appleMerchantId")
	private WebElement appleMerchantId;

	@FindBy(name = "payment.googlePayId")
	private WebElement googlePayId;


	@FindBy(xpath = "//span[contains(text(),'Create')]")
	private WebElement createOutlet;

	@FindBy(xpath = "//span[text()='no']")
	private WebElement addAnotherOutletButton;


	public CreateOutlet(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	// Enter outlet name
	public void enterOutletName(String name) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(outletName));
		outletName.sendKeys(name);
		ExtentReportManager.logInfo("Outlet Name Entered...!");
		Thread.sleep(2000);
	}

	// Enter outlet email
	public void enterOutletEmail(String email) throws InterruptedException {
		outletEmail.sendKeys(email);
		ExtentReportManager.logInfo("Outlet Email Entered...!");
		Thread.sleep(2000);
	}

	// Enter flat number
	public void enterFlatNo(String number) throws InterruptedException {
		flatNo.sendKeys(number);
		ExtentReportManager.logInfo("Flat Number Entered...!");
		Thread.sleep(3000);
	}

	// enter state
	public void enterStateName(String stateName) throws InterruptedException {
		state.sendKeys(stateName);
		ExtentReportManager.logInfo("State Name Entered...!");
		Thread.sleep(2000);
	}

	// select state
	public void selectState(String stateName, WebDriver driver) throws InterruptedException {
		state.click(); // Click to open the dropdown
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(listOptionsState)); // Wait until all options are visible

		System.out.println("Auto Suggest List ::" + listOptionsState.size());
		for (WebElement option : listOptionsState) {
			System.out.println(option.getText());
			if (option.getText().equals(stateName)) {
				option.click();
				break;
			}
		}
		ExtentReportManager.logInfo("State Name Entered...!");
		Thread.sleep(2000);
		System.out.println(stateName + " StateName Selected...!");
	}



	// Select city

	public void selectCity(WebDriver driver, String cityName) throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(city).click().perform();
		actions.sendKeys(cityName).perform();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(listOptionsCity));

		for (WebElement option : listOptionsCity) {
			if (option.getText().equals(cityName)) {
				option.click();
				break;
			}
		}
		ExtentReportManager.logInfo("City Name Selected...!");
		Thread.sleep(2000);
		System.out.println(cityName + " city Selected...!");
	}  


	// Enter ZIP code
	public void enterZipCode(String zip) throws InterruptedException {
		zipCode.sendKeys(zip);
		ExtentReportManager.logInfo("Zip Code Entered...!");
		Thread.sleep(2000);
	}

	// Select region
	public void selectRegion(String regionName) throws InterruptedException {
		Select select = new Select(region);
		select.selectByVisibleText(regionName);
		ExtentReportManager.logInfo("Region Selected...!");

		Thread.sleep(2000);
	}

	/**
	 * Method to enter customer code.
	 * @param driver The WebDriver instance.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public void enterCustomerCode(String code) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(custumerCode)); 
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", custumerCode);
		custumerCode.sendKeys(code);
		ExtentReportManager.logInfo("Customer Code Entered...!");
	}

	/**
	 * Method to enter username.
	 * @param driver The WebDriver instance.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public void enterUserName(String name) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(username)); 
		username.sendKeys(name);
		ExtentReportManager.logInfo("Username Entered...!");
	}

	/**
	 * Method to enter password.
	 * @param driver The WebDriver instance.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public void enterPassword(String pws) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(password)); 
		password.sendKeys(pws);
		ExtentReportManager.logInfo("Password Entered...!");
	}
	/**
	 * Method to enter authHeader.
	 * @param driver The WebDriver instance.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public void enterAuthHeader(String header) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(authHeader)); 
		authHeader.sendKeys(header);
		ExtentReportManager.logInfo("AuthHeader Entered...!");
	}
	/**
	 * Method to enter appleMerchantId.
	 * @param driver The WebDriver instance.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public void enterAppleMerchantId(String id) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(appleMerchantId)); 
		appleMerchantId.sendKeys(id);
		ExtentReportManager.logInfo("AppleMerchantId Entered...!");
	}
	/**
	 * Method to enter googlePayId.
	 * @param driver The WebDriver instance.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public void enterGooglePayId(String id) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(googlePayId)); 
		googlePayId.sendKeys(id);
		ExtentReportManager.logInfo("GooglePayId Entered...!");
		Thread.sleep(2000);
	}

	// Click on the create business button
	public void clickCreateOutlet() throws InterruptedException {
		createOutlet.click();
		ExtentReportManager.logInfo("Clicked Create Button...!");
		Thread.sleep(3000);
	}
	// Click on the 'No' button
	public void clickNo() throws InterruptedException {
		addAnotherOutletButton.click();
		ExtentReportManager.logInfo("Clicked On No Button...!");
		Thread.sleep(2000);
	}


}
