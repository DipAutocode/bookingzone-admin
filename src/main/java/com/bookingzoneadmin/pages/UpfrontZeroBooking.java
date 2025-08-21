package com.bookingzoneadmin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UpfrontZeroBooking {

    @FindBy(xpath = "//img[@alt='Upfront 0']")
    private WebElement upfrontZeroPlan;

    @FindBy(xpath = "//input[@name='fullPayment']")
    private WebElement uncheckFullPay;

  
    public UpfrontZeroBooking(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to select the upfront zero plan.
     */
    public void selectPlan() {
        upfrontZeroPlan.click();
    }

    /**
     * Method to uncheck the full payment option.
     *
     * @throws InterruptedException if the thread sleep is interrupted
     */
    public void uncheckFullPayment() throws InterruptedException {
        uncheckFullPay.click();
    }

    
}
