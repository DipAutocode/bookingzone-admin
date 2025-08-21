package com.bookingzoneadmin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the business login functionality.
 */
public class LogInPage {

    @FindBy(xpath = "(//input[contains(@class,'Pr')])[2]")
    private WebElement businessRadioButton;
    
    @FindBy(xpath = "(//input[contains(@class,'Pr')])[3]")
    private WebElement managerRadioButton;

    @FindBy(name = "email")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//span[text()='Login']")
    private WebElement loginButton;

    /**
     * Initializes the web elements using the PageFactory.
     * @param driver The WebDriver instance to use
     */
    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
 // Method to check if Business radio button is selected
    public boolean isBusinessRadioButtonSelected() {
        return businessRadioButton.isSelected();
    }

    
    // Method to check if Manager radio button is selected
    public boolean isManagerRadioButtonSelected() {
        return managerRadioButton.isSelected();
    }

    /**
     * Clicks the radio button.
     * @throws InterruptedException when interrupted while waiting
     */
    public void clickBusinessRadioButton() throws InterruptedException {
    	businessRadioButton.click();
    }
    
    public void clickManagerRadioButton() throws InterruptedException {
    	managerRadioButton.click();
    }

    /**
     * Enters the username.
     * @param username The username to enter
     * @throws InterruptedException when interrupted while waiting
     */
    public void enterUsername(String username) throws InterruptedException {
        usernameInput.sendKeys(username);

    }

    /**
     * Enters the password.
     * @param password The password to enter
     * @throws InterruptedException when interrupted while waiting
     */
    public void enterPassword(String password) throws InterruptedException {
        passwordInput.sendKeys(password);

    }

    /**
     * Clicks the login button.
     * @throws InterruptedException when interrupted while waiting
     */
    public void clickLoginButton() throws InterruptedException {
        loginButton.click();
    }
}
