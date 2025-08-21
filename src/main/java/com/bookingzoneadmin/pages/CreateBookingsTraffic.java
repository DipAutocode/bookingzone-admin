package com.bookingzoneadmin.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateBookingsTraffic {

    // Web elements declaration using @FindBy annotation
    @FindBy(xpath="//img[@alt='Per Hour Slot']")
    private WebElement perHourSlot;

    @FindBy(xpath="//span[normalize-space()='2 Hours']")
    private WebElement twoHours;

    @FindBy(xpath="(//input[@id='combo-box-demo'])[2]")
    private WebElement enterMinutes;

    @FindBy(xpath="(//input[@id='combo-box-demo'])[3]")
    private WebElement selectAMStartTime;

    @FindBy(xpath="//img[@alt='Birthday Package']")
    private WebElement selectBirthdayPackagePlan;

    @FindBy(xpath="(//div[@class='css-4ysqot']//button)[14]")
    private WebElement selectPackage;

    @FindBy(xpath="(//div[@class='css-4ysqot']//button)[18]")
    private WebElement selectAddPackage;

    @FindBy(xpath="(//button[contains(@class,'MuiButtonBase-root MuiIc')])[13]")
    private WebElement calendarSymbol;

    @FindBy(xpath="(//button[contains(@class,'MuiButtonBase-root MuiIco')])[15]")
    private WebElement nextArrow;

    // Constructor to initialize the PageFactory
    public CreateBookingsTraffic(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Method to click on the calendar symbol
    public void clickCalendarSymbol() throws InterruptedException {
        calendarSymbol.click();
        Thread.sleep(2000); // Introducing a wait for 2 seconds
    }

    // Method to click on the next arrow
    public void clickNextArrow() throws InterruptedException {
        nextArrow.click();
        Thread.sleep(2000); // Introducing a wait for 2 seconds
    }

    // Method to select the per hour slot
    public void selectPerHourSlot() throws InterruptedException {
        perHourSlot.click();
        Thread.sleep(2000); // Introducing a wait for 2 seconds
    }

    // Method to select the two hours slot
    public void selectTwoHours() throws InterruptedException {
        twoHours.click();
        Thread.sleep(2000); // Introducing a wait for 2 seconds
    }

    // Method to enter minutes in the input field
    public void enterMinutes(WebDriver driver, String minutes) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.click(enterMinutes).sendKeys(minutes).sendKeys(Keys.ENTER).perform();
    }

    // Method to select the noon start time
    public void selectNoonStartTime(WebDriver driver, String time) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.click(selectAMStartTime).sendKeys(time).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000); // Introducing a wait for 2 seconds
    }

    // Method to select the birthday package plan
    public void selectBirthdayPackagePlan() {
        selectBirthdayPackagePlan.click();
    }

    // Method to select a package
    public void selectPackage(WebDriver driver) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", selectPackage);
        Thread.sleep(20000); // Introducing a longer wait for 20 seconds

        if(selectPackage.isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selectPackage));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    // Method to select and add a guest
    public void selectAddGuest(WebDriver driver) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", selectAddPackage);
        selectAddPackage.click();
        Thread.sleep(2000); // Introducing a wait for 2 seconds
    }
}
