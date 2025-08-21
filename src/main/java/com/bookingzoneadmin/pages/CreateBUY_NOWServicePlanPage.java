package com.bookingzoneadmin.pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzoneadmin.utils.ExtentReportManager;

public class CreateBUY_NOWServicePlanPage {
	Logger logger = (Logger) LogManager.getLogger(this.getClass());

	private WebDriverWait wait;
	private WebDriver driver;

	@FindBy(xpath="//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/span[1]")
	private WebElement   uploadImg;


	@FindBy(xpath="//span//input[@name='Image']")
	private WebElement   uploadImgRadiobtn;


	@FindBy(xpath="(//span[text()='Browse'])[1]")
	private WebElement   browsebtn;


	@FindBy(xpath="//button//span[text()='Upload']")
	private WebElement   uploadbtn;

	@FindBy(xpath="//input[@name='title']")
	private WebElement   enterPlanName;

	@FindBy(xpath="//input[@name='minimumTimeAdvance.value']")
	private WebElement   minTimeInAdvanced;

	@FindBy(xpath="//input[@name='maximumTimeAdvance.value']")
	private WebElement   maxTimeInAdvanced;

	@FindBy(xpath="//input[@id='days']")
	private WebElement   selectDays;

	@FindBy(xpath="//li[contains(@class, 'MuiAutocomplete-option')]")
	private List<WebElement> dropDownOptions;

	@FindBy(xpath="//select[@name='schedules.0.dateRange.0.venueDetail.0.serviceCategory']")
	private WebElement   selectServiceCategory;

	@FindBy(xpath="//select[@name='schedules.0.dateRange.0.venueDetail.0.venueType']")
	private WebElement   selectVenueType;

	@FindBy(xpath="//select[@name='schedules.0.dateRange.0.venueDetail.0.serviceDescription']")
	private WebElement   selectServiceDescription;

	@FindBy(xpath="//select[@name='schedules.0.dateRange.0.venueDetail.0.bookingType']")
	private WebElement   selectBookingType;

	@FindBy(xpath="//select[@name='schedules.0.dateRange.0.venueDetail.0.from']")
	private WebElement   selectFrom;

	@FindBy(xpath="//select[@name='schedules.0.dateRange.0.venueDetail.0.to']")
	private WebElement   selectTo;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.purchaseItems.0.name']")
	private WebElement   purchaseItemsName;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.purchaseItems.0.price']")
	private WebElement   purchaseItemsprice;

	@FindBy(xpath="//input[@name='schedules.0.dateRange.0.purchaseItems.0.tax']")
	private WebElement   purchaseItemSaleTax;

	@FindBy(xpath="//select[@name='schedules.0.dateRange.0.purchaseItems.0.timeFrame']")
	private WebElement   purchaseItemTimeFrame;

	@FindBy (xpath="(//input[@id='combo-box-demo'])[1]")
	private WebElement slot1StartTimehour ;

	@FindBy (xpath="(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium MuiAutocomplete-c')])[1]")
	private WebElement clearButton ;

	@FindBy (xpath="(//input[@id='combo-box-demo'])[2]")
	private WebElement slot1StartTimeMinute ;

	@FindBy (xpath="(//input[@id='combo-box-demo'])[3]")
	private WebElement slot1StartTimeampmDropdown ;

	@FindBy (xpath="(//input[@id='combo-box-demo'])[4]")
	private WebElement slot1EndTimehour ;

	@FindBy (xpath="(//input[@id='combo-box-demo'])[5]")
	private WebElement slot1EndTimeMinute ;

	@FindBy (xpath="(//input[@id='combo-box-demo'])[6]")
	private WebElement slot1EndTimeampmDropdown ;

	@FindBy (xpath="(//button[.//span[contains(text(), 'Add More')]])[3]")
	private WebElement addMoreReservationSlot ;

	@FindBy (xpath="(//input[@id='combo-box-demo'])[7]")
	private WebElement slot2StartTimehour ;

	@FindBy (xpath="(//input[@id='combo-box-demo'])[8]")
	private WebElement slot2StartTimeMinute ;

	@FindBy (xpath="(//input[@id='combo-box-demo'])[9]")
	private WebElement slot2StartTimeampmDropdown ;

	@FindBy (xpath="(//input[@id='combo-box-demo'])[10]")
	private WebElement slot2EndTimehour ;

	@FindBy (xpath="(//input[@id='combo-box-demo'])[11]")
	private WebElement slot2EndTimeMinute ;

	@FindBy (xpath="(//input[@id='combo-box-demo'])[12]")
	private WebElement slot2EndTimeampmDropdown ;

	@FindBy (xpath="(//input[@name='schedules.0.dateRange.0.bookingDepositDetails.refundStatus'])[1]")
	private WebElement refundRadiobtn ;

	@FindBy (xpath="//select[@name='schedules.0.dateRange.0.bookingDepositDetails.refundType']")
	private WebElement refundType ;

	@FindBy (name="schedules.0.dateRange.0.bookingDepositDetails.refundAmount")
	private WebElement refundAmount ;

	@FindBy (xpath="//input[@name='processingFeeDetails.details.0.status']")
	private WebElement processingFeeCheckBox ;

	@FindBy (xpath="//select[@name='processingFeeDetails.details.0.type']")
	private WebElement processingFeePer ;

	@FindBy (xpath="//input[@id='taxapplied']")
	private WebElement bookingTotalDropdown ;

	@FindBy (xpath="//li[contains(text(),'Total')]")
	private WebElement totalOption ;

	@FindBy (xpath="//input[@name='processingFeeDetails.details.0.name']")
	private WebElement processingFeeName ;

	@FindBy (xpath="//input[@name='processingFeeDetails.details.0.amountPer']")
	private WebElement processingFeeAmount ;

	@FindBy (xpath="//button//span[text()='Yes']")
	private WebElement yesBtn ;

	@FindBy (xpath="//button//span[text()='Create']")
	private WebElement createbtn ;



	public CreateBUY_NOWServicePlanPage (WebDriver driver)
	{this.driver = driver;
	PageFactory.initElements(driver, this);
	this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Initialize WebDriverWait with a timeout of 10 seconds
	}

	public void clearInputField(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].value = '';", element);
	}
	

	public void enterPlanName(String name) throws InterruptedException 
	{
		wait.until(ExpectedConditions.visibilityOf(enterPlanName));
		enterPlanName.sendKeys(name);
		ExtentReportManager.logInfo("Plan Name Entered...!");
		Thread.sleep(2000);
	}

	public void clickUploadImgbtn() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(uploadImg));
		uploadImg.click();
		ExtentReportManager.logInfo("Clicked On Upload Image...!");
		Thread.sleep(2000);
	}

	public void clickUploadImgRadiobtn() throws InterruptedException 
	{
		uploadImgRadiobtn.click();
		ExtentReportManager.logInfo("Clicked On Upload Image Radio Button...!");
		Thread.sleep(2000);
	}

	public void clickBrowsebtn() throws InterruptedException 
	{
		browsebtn.click();
		ExtentReportManager.logInfo("Click On Browse Button...!");
		Thread.sleep(2000);
	}

	public void clickUploadbtn() throws InterruptedException 
	{
		uploadbtn.click();
		ExtentReportManager.logInfo("Clicked On Upload Button...!");
		Thread.sleep(2000);
	}

	public void enterMinTimeInAdvanced(String time) throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(minTimeInAdvanced));
		minTimeInAdvanced.click();
		clearInputField(minTimeInAdvanced);
		minTimeInAdvanced.sendKeys(time);
		ExtentReportManager.logInfo("Entered Min Time In Advanced...!");
		Thread.sleep(2000);
	}

	public void enterMaxTimeInAdvanced(String Time) throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(maxTimeInAdvanced));
		maxTimeInAdvanced.click();
		clearInputField(maxTimeInAdvanced);
		maxTimeInAdvanced.sendKeys(Time);
		ExtentReportManager.logInfo("Entered Max Time In Advanced...!");
		Thread.sleep(2000);
	}

	public void selectDays() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(selectDays));
		selectDays.click();

		for (WebElement option : dropDownOptions) {
			option.click();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ExtentReportManager.logInfo("Days Are Selected...!");
		Thread.sleep(5000);
	}

	public void selectServiceCategory() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(selectServiceCategory));
		Select s1=new Select(selectServiceCategory);
		s1.selectByIndex(1);
		ExtentReportManager.logInfo("Service Category Is Selected...!");
		Thread.sleep(2000);
	}

	public void selectVenueType() throws InterruptedException 
	{
		wait.until(ExpectedConditions.visibilityOf(selectVenueType));
		Select s1=new Select(selectVenueType);
		s1.selectByIndex(1);
		ExtentReportManager.logInfo("Venue Type Is Selected...!");
		Thread.sleep(2000);
	}

	public void selectServiceDescription() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(selectServiceDescription));
		Select s1=new Select(selectServiceDescription);
		s1.selectByIndex(1);
		ExtentReportManager.logInfo("Service Description is Selected...!");
		Thread.sleep(2000);
	}


	public void selectBookingType() throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(selectBookingType));
		Select s1=new Select(selectBookingType);
		s1.selectByIndex(1);
		ExtentReportManager.logInfo("Booking Type Selected...!");
		Thread.sleep(2000);
	}

	public void selectFrom(String text) throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(selectFrom));
		Select s1=new Select(selectFrom);
		s1.selectByVisibleText(text);
		ExtentReportManager.logInfo("From Selected...!");
		Thread.sleep(2000);
	}

	public void selectTo(String text) throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(selectTo));
		Select s1=new Select(selectTo);
		s1.selectByVisibleText(text);
		ExtentReportManager.logInfo("To Selected...!");
		Thread.sleep(2000);
	}


	public void enterPurchaseItemsName(String ItemName) throws InterruptedException 
	{
		wait.until(ExpectedConditions.visibilityOf(purchaseItemsName));
		purchaseItemsName.sendKeys(ItemName);
		ExtentReportManager.logInfo("Entered Purchase Items Name...!");
		Thread.sleep(2000);
	}

	public void enterPurchaseItemsprice(String ItemPrice) throws InterruptedException 
	{   wait.until(ExpectedConditions.visibilityOf(purchaseItemsprice));
	purchaseItemsprice.sendKeys(ItemPrice);
	ExtentReportManager.logInfo("Entered Purchase Items Price...!");
	Thread.sleep(2000);
	}

	public void enterPurchaseItemSaleTax(String ItemTax) throws InterruptedException 
	{
		wait.until(ExpectedConditions.visibilityOf(purchaseItemSaleTax));
		purchaseItemSaleTax.sendKeys(ItemTax);
		ExtentReportManager.logInfo("Entered Purchase Item Sale Tax...!");
		Thread.sleep(2000);
	}
	
	public boolean isPurchaseItemTimeFrameDropdownEnabled() {
	   	    return purchaseItemTimeFrame.isEnabled();
	}


	public void selectPurchaseItemTimeFrame(String value) throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(purchaseItemTimeFrame));
		Select s1=new Select(purchaseItemTimeFrame);
		s1.selectByVisibleText(value);
		ExtentReportManager.logInfo("Purchase Item TimeFrame is selected...!");
		Thread.sleep(2000);
	}

	public void selectTime(WebElement hourElement, WebElement minuteElement, WebElement ampmElement, String hour, String minute, String ampm) throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.click(hourElement).perform();
		actions.sendKeys(hour).sendKeys(Keys.ENTER).perform();

		actions.click(minuteElement).perform();
		actions.sendKeys(minute).sendKeys(Keys.ENTER).perform();
		
		actions.click(ampmElement).perform();
		clearInputField(ampmElement);
		actions.sendKeys(ampm).perform();
		actions.sendKeys(Keys.ARROW_DOWN).perform();
		actions.sendKeys(Keys.ENTER).perform();

		Thread.sleep(3000);  // You might replace this with a more dynamic wait if possible
	}
	
	public void selectSlotTime(String slotNumber, String startHour, String startMinute, String startAmpm, String endHhour, String endMinute, String endAmpm) throws InterruptedException {
		
		    int number = Integer.parseInt(slotNumber);
		    System.out.println("Slot no is- "+ number);
	
		// Determine the correct elements based on the slot number
		WebElement startHourElement = driver.findElement(By.xpath("(//input[@id='combo-box-demo'])[" + ((number - 1) * 6 + 1) + "]"));
		WebElement startMinuteElement = driver.findElement(By.xpath("(//input[@id='combo-box-demo'])[" + ((number - 1) * 6 + 2) + "]"));
		WebElement startAmPmElement = driver.findElement(By.xpath("(//input[@id='combo-box-demo'])[" + ((number - 1) * 6 + 3) + "]"));

		WebElement endHourElement = driver.findElement(By.xpath("(//input[@id='combo-box-demo'])[" + ((number - 1) * 6 + 4) + "]"));
		WebElement endMinuteElement = driver.findElement(By.xpath("(//input[@id='combo-box-demo'])[" + ((number - 1) * 6 + 5) + "]"));
		WebElement endAmPmElement = driver.findElement(By.xpath("(//input[@id='combo-box-demo'])[" + ((number - 1) * 6 + 6) + "]"));

		// Set the start time
		selectTime(startHourElement, startMinuteElement, startAmPmElement, startHour, startMinute, startAmpm);
		ExtentReportManager.logInfo("Srart Time Selected...!");
		// Set the end time
		selectTime(endHourElement, endMinuteElement, endAmPmElement, endHhour, endMinute, endAmpm);
		ExtentReportManager.logInfo("End Time Selected");
		Thread.sleep(2000);
		
	}
	
	public void addMoreReservationSlot() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(addMoreReservationSlot));
		addMoreReservationSlot.click();
		ExtentReportManager.logInfo("Clicked On AddMore Reservation Button...!");
		Thread.sleep(3000);
	}


	public void clickRefundYes() throws InterruptedException 
	{
		//wait.until(ExpectedConditions.elementToBeClickable(refundRadiobtn));	
		refundRadiobtn.click();
		ExtentReportManager.logInfo("Refund Yes Radio Button Selected...!");
		Thread.sleep(2000);
	}


	public void selectRefundType(String text) throws InterruptedException 
	{ 
		wait.until(ExpectedConditions.elementToBeClickable(refundType));
		Select s1=new Select(refundType);
		s1.selectByVisibleText(text);
		ExtentReportManager.logInfo("Refund Type Selected...!");
		Thread.sleep(2000);
	}

	public void enterRefundAmount(String amount) throws InterruptedException 
	{
		//wait.until(ExpectedConditions.visibilityOf(processingFeeName));
		refundAmount.sendKeys(amount);
		ExtentReportManager.logInfo("Entered refund amount...!");
		Thread.sleep(2000);
	}


	public void selectProcessingFeeCheckBox() throws InterruptedException 
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", processingFeeCheckBox);
		processingFeeCheckBox.click();
		System.out.println("Executed SelectProcessingFeeCheckBox method ");
		Thread.sleep(2000);
	}

	public void selectFeePer() throws InterruptedException 
	{
		//wait.until(ExpectedConditions.elementToBeClickable(processingFeePer));
		Select s1=new Select(processingFeePer);
		s1.selectByIndex(1);
		ExtentReportManager.logInfo("Selected Processing Fee Per...!");
		Thread.sleep(2000);
	}

	public void selectBasedOn() throws InterruptedException 
	{
		//wait.until(ExpectedConditions.elementToBeClickable(bookingTotalDropdown));
		bookingTotalDropdown.click();
		wait.until(ExpectedConditions.elementToBeClickable(totalOption));
		totalOption.click();
		ExtentReportManager.logInfo("Processing Fees Based On Selected...!");
		Thread.sleep(2000);
	}

	public void enterProcessingFeeName(String name) throws InterruptedException 
	{
		//wait.until(ExpectedConditions.visibilityOf(processingFeeName));
		processingFeeName.sendKeys(name);
		ExtentReportManager.logInfo("Processing Fee Name Entered...!");
		Thread.sleep(2000);
	}

	public void enterProcessingFeeAmount(String Fees) throws InterruptedException 
	{
		//wait.until(ExpectedConditions.elementToBeClickable(processingFeeAmount));
		processingFeeAmount.click();
		clearInputField(processingFeeAmount);
		wait.until(ExpectedConditions.visibilityOf(processingFeeAmount));
		processingFeeAmount.sendKeys(Fees);
		ExtentReportManager.logInfo("Processing Fee Amount Entered...!");
		Thread.sleep(2000);
	}


	public void clickCreatebtn() throws InterruptedException 
	{
		createbtn.click();
		ExtentReportManager.logInfo("Clicked On Created Button...!");
		Thread.sleep(5000);
	}

	public void clickYesBtn() throws InterruptedException 
	{ 
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(yesBtn));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		ExtentReportManager.logInfo("Clicked On No Button...!");
	}
}
