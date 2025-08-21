package com.bookingzoneadmin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateBookingFromTraffic {

    @FindBy(xpath = "//span[normalize-space()='1 Hours']")
    private WebElement oneHour;

    @FindBy(xpath = "(//div[@class='fc-timeline-events fc-scrollgrid-sync-inner'])[2]/div[1]")
    private WebElement clickBookingToEditTime;

    @FindBy(xpath = "((//div[@class='fc-timeline-events fc-scrollgrid-sync-inner'])[1]//div)[6]")
    private WebElement clickBookingToEditLane;

    @FindBy(xpath = "(//button[contains(@class,'MuiButtonBase-root Mu')])[10]")
    private WebElement clickCalendar;

    @FindBy(xpath = "(//button[contains(@class,'MuiButtonBase-root MuiIco')])[8]")
    private WebElement nextArrow;

    public UpdateBookingFromTraffic(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to select one hour.
     *
     * @throws InterruptedException if the thread sleep is interrupted
     */
    public void selectOneHour() throws InterruptedException {
        oneHour.click();
        Thread.sleep(2000);
    }

    /**
     * Method to click the booking to edit time.
     *
     * @throws InterruptedException if the thread sleep is interrupted
     */
    public void clickBookingToEditTime() throws InterruptedException {
        clickBookingToEditTime.click();
        Thread.sleep(2000);
    }

    /**
     * Method to click the booking to edit lane.
     *
     * @throws InterruptedException if the thread sleep is interrupted
     */
    public void clickBookingToEditLane() throws InterruptedException {
        clickBookingToEditLane.click();
        Thread.sleep(2000);
    }

    /**
     * Method to click the calendar symbol on the homepage.
     *
     * @throws InterruptedException if the thread sleep is interrupted
     */
    public void clickCalendarSymbolHomepage() throws InterruptedException {
        clickCalendar.click();
        Thread.sleep(2000);
        nextArrow.click();
        Thread.sleep(2000);
    }
}
