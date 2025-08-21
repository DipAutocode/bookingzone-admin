package com.bookingzoneadmin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the functionality related to logging out from the admin interface.
 */
public class LogoutPage {

    @FindBy(xpath="//button[@class='MuiButtonBase-root css-1lk0u3-MuiButtonBase-root']")
    private WebElement clickProfileIcon; // WebElement for clicking the profile icon

    @FindBy(xpath="//button/span[text()='Logout']")
    private WebElement logoutButton; // WebElement for the logout button

    // Constructor to initialize PageFactory
    public LogoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to click the profile icon.
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public void clickProfileIcon() throws InterruptedException {
        clickProfileIcon.click();
        Thread.sleep(5000); // Pause to allow for page loading (for demonstration purposes)
    }

    /**
     * Method to click the logout button.
     * @throws InterruptedException 
     */
    public void clickLogoutButton() throws InterruptedException {
        logoutButton.click();
        Thread.sleep(2000);
    }

}
