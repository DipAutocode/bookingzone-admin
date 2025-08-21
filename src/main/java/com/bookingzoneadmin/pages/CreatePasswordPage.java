package com.bookingzoneadmin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.bookingzoneadmin.utils.ExtentReportManager;

public class CreatePasswordPage {

    // WebElements declaration using @FindBy annotation with proper naming conventions
   
    @FindBy(xpath = "//div//input[@name='password']")
    private WebElement txtPassword;

    @FindBy(xpath = "//input[@name='passwordConfirm']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//button/span[text()='Create Password']")
    private WebElement createPassword;


    public CreatePasswordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
// Enter password into the input field
    public void enterPassword(String password) throws InterruptedException {
        txtPassword.sendKeys(password);
        ExtentReportManager.logInfo("Password entered for newly created bussiness...!");
        Thread.sleep(2000);
    }

    // Enter confirm password into the input field
    public void enterConfirmPassword(String confirmPasswordValue) throws InterruptedException {
        confirmPassword.sendKeys(confirmPasswordValue);
        ExtentReportManager.logInfo("Confirm password entered for newly created bussiness...!");
        Thread.sleep(2000);
    }

    // Click on the create password button
    public void clickCreatePassword() throws InterruptedException {
        createPassword.click();
        ExtentReportManager.logInfo("Clicked on create password btn...!");
        Thread.sleep(5000);
    }

	
}
