package com.bookingzoneadmin.pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzoneadmin.utils.ExtentReportManager;

public class CreateNewBusinessPage {

	private WebDriverWait wait;

	@FindBy(xpath = "//span[text()='Upload Image']")
	private WebElement uploadImage;

	@FindBy(name = "businessName")
	private WebElement businessName;

	@FindBy(name = "firstName")
	private WebElement firstName;

	@FindBy(name = "lastName")
	private WebElement lastName;

	@FindBy(name = "email")
	private WebElement email;

	@FindBy(name = "address.street")
	private WebElement street;

	@FindBy(name = "address.city")
	private WebElement city;

	@FindBy(name = "address.state")
	private WebElement state;

	@FindBy(name = "address.zip")
	private WebElement zip;

	@FindBy(xpath = "//span[contains(text(),'Create')]")
	private WebElement createBusiness;

	@FindBy(name = "address.country")
	private WebElement stateDropDown;

	@FindBy(xpath = "//span[text()='No']")
	private WebElement addAnotherBusinessButton;

	@FindBy(xpath = "//a[text()='VERIFICATION']")
	private WebElement btnVerification;



	public CreateNewBusinessPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	}

	// Click on the upload image button
	public void clickUploadImage() {
		uploadImage.click();
	}

	// Enter business name into the input field
	public void enterBusinessName(String name) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(businessName));
		businessName.sendKeys(name);
		ExtentReportManager.logInfo("Bussiness name entered...!");
		Thread.sleep(2000);
	}

	// Enter first name into the input field
	public void enterFirstName(String firstNameValue) throws InterruptedException {
		firstName.sendKeys(firstNameValue);
		ExtentReportManager.logInfo("First name entered...!");
		Thread.sleep(2000);
	}

	// Enter last name into the input field
	public void enterLastName(String lastNameValue) throws InterruptedException {
		lastName.sendKeys(lastNameValue);
		ExtentReportManager.logInfo("Last name entered...!");
		Thread.sleep(2000);
	}

	// Enter email into the input field
	public void enterEmail(String emailValue) throws InterruptedException {
		email.sendKeys(emailValue);
		ExtentReportManager.logInfo("Email entered...!");
		Thread.sleep(2000);
	}

	// Enter street address into the input field
	public void enterFlatNo(String address) throws InterruptedException {
		street.sendKeys(address);
		ExtentReportManager.logInfo("Street entered...!");
		Thread.sleep(2000);
	}

	// Enter city into the input field
	public void enterCity(String cityName) throws InterruptedException {
		city.sendKeys(cityName);
		ExtentReportManager.logInfo("City name entered...!");
		Thread.sleep(2000);
	}

	// Enter state into the input field
	public void enterState(String stateName) throws InterruptedException {
		state.sendKeys(stateName);
		ExtentReportManager.logInfo("State entered...!");
		Thread.sleep(2000);
	}

	// Select country from the dropdown
	public void selectCountry(String value) {
		Select select = new Select(stateDropDown);
		select.selectByVisibleText(value);
		ExtentReportManager.logInfo("Country name entered...!");
	}

	// Enter ZIP code into the input field
	public void enterZip(String zipCode) throws InterruptedException {
		zip.sendKeys(zipCode);
		ExtentReportManager.logInfo("Zip Code entered...!");
		Thread.sleep(5000);
	}

	// Click on the create business button
	public void clickCreateBusiness() throws InterruptedException {
		createBusiness.click();
		ExtentReportManager.logInfo("Clicked On Create Bussiness Button...!");
		Thread.sleep(3000);
	}

	// Click on the 'No' button
	public void clickNo() throws InterruptedException {
		addAnotherBusinessButton.click();
		ExtentReportManager.logInfo("Clicked On No Button...!");
		Thread.sleep(2000);
	}

	// Click on the verification button
	public void clickVerificationButton() {
		btnVerification.click();
		ExtentReportManager.logInfo("Clicked on verification button...!");
	}

}
