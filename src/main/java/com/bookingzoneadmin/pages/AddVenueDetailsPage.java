package com.bookingzoneadmin.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingzoneadmin.utils.ExtentReportManager;

/**
 * This class represents the functionality related to adding venue details in the admin interface.
 */
public class AddVenueDetailsPage {
	private WebDriverWait wait;
    // WebElements using FindBy annotation to locate elements in the web page

    @FindBy(xpath = "(//button[contains(@class,'MuiButton-root ')])[1]")
    private WebElement clickBack; // WebElement for the back button

    @FindBy(xpath = "(//button[contains(@class,'MuiButtonBase-')])[7]")
    private WebElement clickEditCategory; // WebElement for clicking edit category

    @FindBy(xpath = "(//input[contains(@class,'MuiOut')])[1]")
    private WebElement serviceCategory; // WebElement for service category input

    @FindBy(xpath = "(//input[contains(@class,'MuiOut')])[2]")
    private WebElement venueType; // WebElement for venue type input

    @FindBy(xpath = "(//input[contains(@class,'MuiOut')])[3]")
    private WebElement serviceDescription; // WebElement for service description input

    @FindBy(xpath = "(//button[contains(@class,'MuiButtonBase-r')])[9]")
    private WebElement clickEditTotalCount; // WebElement for clicking edit total count

    @FindBy(name = "totalCount.0.venue")
    private WebElement selectVenue; // WebElement for selecting venue

    @FindBy(name = "totalCount.0.count")
    private WebElement totalCount; // WebElement for total count input

    @FindBy(xpath = "(//button[contains(@class,'MuiButtonBase-r')])[11]")
    private WebElement clickEditBookingType; // WebElement for clicking edit booking type

    @FindBy(name = "bookingType.0.serviceCategory")
    private WebElement selectService; // WebElement for selecting service

    @FindBy(name = "bookingType.0.venueType")
    private WebElement selectVenueType; // WebElement for selecting venue type

    @FindBy(name = "bookingType.0.serviceDescription")
    private WebElement selectServiceDescription; // WebElement for selecting service description

    @FindBy(name = "bookingType.0.bookingType")
    private WebElement selectBookingType; // WebElement for selecting booking type

    @FindBy(name = "bookingType.0.from")
    private WebElement from; // WebElement for from input

    @FindBy(name = "bookingType.0.to")
    private WebElement to; // WebElement for to input

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement save; // WebElement for save button

    // Constructor to initialize PageFactory and WebDriver
    public AddVenueDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
    }

    /**
     * Method to click the back button.
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public void clickBackButton() throws InterruptedException {
        clickBack.click();
        Thread.sleep(2000); // Pause to allow page loading (for demonstration purposes)
    }


    /**
     * Method to click on edit category.
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public void clickEditBtnOfVenueCategory() throws InterruptedException {
    	 wait.until(ExpectedConditions.elementToBeClickable(clickEditCategory));
        clickEditCategory.click();
		ExtentReportManager.logInfo("Clicked on Edit Button Of Venue Category...!");
        Thread.sleep(5000); // Pause to allow page loading (for demonstration purposes)
    }

    /**
     * Method to enter service category.
     * @param serviceCat the service category to enter
     * @throws InterruptedException 
     */
    public void enterServiceCategory(String serviceCat) throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(serviceCategory));
    	serviceCategory.sendKeys(serviceCat);
		ExtentReportManager.logInfo("Entered Service Category...!");
        Thread.sleep(2000);
    }

    /**
     * Method to enter venue type.
     * @param vType the venue type to enter
     * @throws InterruptedException 
     */
    public void enterVenueType(String vType) throws InterruptedException {
        venueType.sendKeys(vType);
		ExtentReportManager.logInfo("Entered Venue Type...!");
        Thread.sleep(2000);
    }

    /**
     * Method to enter service description.
     * @param sDescription the service description to enter
     * @throws InterruptedException 
     */
    public void enterServiceDescription(String sDescription) throws InterruptedException {
        serviceDescription.sendKeys(sDescription);
		ExtentReportManager.logInfo("Entered Service Description...!");
        Thread.sleep(2000);
    }

    /**
     * Method to click on edit total count.
     * @throws InterruptedException 
     */
    public void clickEditBtnOfTotalCount() throws InterruptedException {
        clickEditTotalCount.click();
		ExtentReportManager.logInfo("Clicked on Edit Button Of Total Count...!");
        Thread.sleep(3000);
    }

    /**
     * Method to select venue.
     * @throws InterruptedException 
     */
    public void selectVenue(String value) throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(selectVenue));
    	Select venue = new Select(selectVenue);
        venue.selectByVisibleText(value);
		ExtentReportManager.logInfo("CVenue Type Selected...!");
        Thread.sleep(2000);
    }

    /**
     * Method to clear total count field.
     * @throws InterruptedException 
     */
    public void clearTotalCount() throws InterruptedException {
        totalCount.clear();
		ExtentReportManager.logInfo("Cleared Total Count...!");
        Thread.sleep(2000);
    }

    /**
     * Method to enter total count.
     * @param count the total count to enter
     * @throws InterruptedException 
     */
    public void enterTotalCount(String count) throws InterruptedException {
        totalCount.sendKeys(count);
		ExtentReportManager.logInfo("Entered Total Count...!");
        Thread.sleep(2000);
    }

    /**
     * Method to click on edit booking type.
     * @throws InterruptedException 
     */
    public void clickEditBtnOfBookingType() throws InterruptedException {
        clickEditBookingType.click();
		ExtentReportManager.logInfo("Clicked on Edit Button Of Booking Type...!");
        Thread.sleep(3000);
    }

    /**
     * Method to select service.
     * @throws InterruptedException 
     */
    public void selectService(String value) throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(selectService));
    	Select service = new Select(selectService);
		ExtentReportManager.logInfo("Service Selected...!");
        service.selectByVisibleText(value);
        Thread.sleep(2000);
    }

    /**
     * Method to select venue type.
     * @throws InterruptedException 
     */
    public void selectVenueType(String value) throws InterruptedException {
        Select venueType = new Select(selectVenueType);
        venueType.selectByVisibleText(value);
		ExtentReportManager.logInfo("Venue Type Selected...!");
        Thread.sleep(2000);
    }

    /**
     * Method to enter from value.
     * @param no the from value to enter
     * @throws InterruptedException 
     */
    public void enterFrom(String no) throws InterruptedException {
        from.sendKeys(no);
		ExtentReportManager.logInfo("Entered From...!");
        Thread.sleep(2000);
    }

    /**
     * Method to enter to value.
     * @param toNo the to value to enter
     * @throws InterruptedException 
     */
    public void enterTo(String toNo) throws InterruptedException {
        to.sendKeys(toNo);
		ExtentReportManager.logInfo("Entered To...!");
        Thread.sleep(2000);
    }

    /**
     * Method to select service description.
     * @throws InterruptedException 
     */
    public void selectServiceDescription(String value) throws InterruptedException {
        Select serviceDesc = new Select(selectServiceDescription);
        serviceDesc.selectByVisibleText(value);
		ExtentReportManager.logInfo("Service Description Selected...!");
        Thread.sleep(2000);
    }

    /**
     * Method to select booking type.
     * @throws InterruptedException 
     */
    public void selectBookingType(String value) throws InterruptedException {
        Select bookingType = new Select(selectBookingType);
        bookingType.selectByVisibleText(value);
        ExtentReportManager.logInfo("Booking Type Selected...!");
        Thread.sleep(2000);
    }

    /**
     * Method to click on save button.
     * @throws InterruptedException 
     */
    public void clickSaveButton() throws InterruptedException {
        save.click();
    	ExtentReportManager.logInfo("Clicked on Save Button...!");
        Thread.sleep(2000);
    }

	
}
