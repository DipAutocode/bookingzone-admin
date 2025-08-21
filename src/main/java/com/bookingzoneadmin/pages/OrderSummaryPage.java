package com.bookingzoneadmin.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.bookingzoneadmin.utils.ExtentReportManager;

/**
 * This class represents the functionality related to making payments via email in the admin section.
 */
public class OrderSummaryPage {

	private WebDriver driver;
	private WebDriverWait wait;
	
    @FindBy(xpath = "//div//p[@class='jsx-2926317546 fs_14 float-right m-0']")
    private WebElement payableAmount;
    
    @FindBy(xpath = "//div//p[@class='jsx-2926317546 fs_14 float-right m-0 text_secondary']")
    private WebElement totalAmount;
    
    @FindBy(xpath = "//button[contains(text(),'Pay')]")
    private WebElement payButton;
    
    

    /**
     * Initializes the web elements using the PageFactory.
     * @param driver The WebDriver instance to use
     */
    public OrderSummaryPage(WebDriver driver) {
    	this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

   /**
     * Gets the payable amount displayed on the order summary page.
     * @return The payable amount as a string
     */
    public String getPayableAmountOnOrderSummaryPage() {
    	wait.until(ExpectedConditions.visibilityOf(payableAmount));
        String payableAmountText = payableAmount.getText();
        System.out.println("Payable amount on order summary page is: " + payableAmountText);
        ExtentReportManager.logInfo("Payable amount on order summary page is: " + payableAmountText);
        return payableAmountText;
    }
    
    public String getTotalAmountOnOrderSummaryPage() {
    	wait.until(ExpectedConditions.visibilityOf(totalAmount));
        String totalAmountText = totalAmount.getText();
        System.out.println("Total amount on order summary page is: " + totalAmountText);
     ExtentReportManager.logInfo("Total amount on order summary page is: " + totalAmountText);
        return totalAmountText;
    }
    /**
     * Method to click the pay button.
     *
     * @throws InterruptedException if the thread sleep is interrupted
     */
    public void clickPayButton() throws InterruptedException {
        payButton.click();
        ExtentReportManager.logInfo("Click On Pay Button Of Order Summary Page...!");
        Thread.sleep(2000);
    }
    
    public void handleWindowPopUp(int popupId) 
    {
    	// need to handle window pop-up
    			Set<String>  AllIds=driver.getWindowHandles();
    			ArrayList<String> ar=new  ArrayList<String>(AllIds);
    			String WindowPopId=ar.get(popupId);//focus on oreder summarry screen
    			// To switch focus window pop-up
    			driver.switchTo().window(WindowPopId);
    }

    public void verifyPayableAmountOnEmailSendedLink(int pageId) throws InterruptedException 
	{
		//To switch focus of selenium on main page
		driver.switchTo().defaultContent();

		handleWindowPopUp(pageId);// to switch focus to the order summary screen on PWA site
		
		//get text of total and payable amount on order summarry
		String totalAmount=getTotalAmountOnOrderSummaryPage();
		String PayableAmount=getPayableAmountOnOrderSummaryPage();

		//apply assertion to verify upfront amount(of pay page)== balance amount()
		Assert.assertEquals(totalAmount, PayableAmount, "Payable amount is");
		ExtentReportManager.logPass("Verify Payable Amount Assertion Is Passed...!");

		clickPayButton();
	}
    
    public void verifyBookingStatus() 
	{
		String  ActualResult1=driver.findElement(By.xpath("//p[contains(@class,'p-0 BookingStatus_bill__status__njOUp BookingStatus_success__19dxa')]")).getText();
		String ExpectedResult1="Booking Successful";
		Assert.assertEquals(ActualResult1, ExpectedResult1);
	}


}
