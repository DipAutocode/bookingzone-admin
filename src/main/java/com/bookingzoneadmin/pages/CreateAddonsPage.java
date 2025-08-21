package com.bookingzoneadmin.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class represents the Create Addon functionality in the application.
 */
public class CreateAddonsPage {
	private WebDriverWait wait;
	private WebDriver driver;


	@FindBy(xpath = "//div//span[text()='Add Item']")
	private WebElement addItemBtn;

	@FindBy (xpath="//input[@name='itemName']")
	private WebElement itemName ;

	@FindBy (xpath="//select[@name='tags']")
	private WebElement tag ; 
	

	@FindBy (xpath="//input[@name='stock']")
	private WebElement quantityInStock ;

	@FindBy (xpath="//input[@name='basePrice']")
	private WebElement basePrice ;

	@FindBy (xpath="//input[@name='taxFlat']")
	private WebElement flatTax ;

	@FindBy (xpath="//input[@name='taxPer']")
	private WebElement perTax ;

	@FindBy (xpath="//button//span[text()='Save']")
	private WebElement saveBtn ;

	/**
	 * Constructor to initialize elements using PageFactory.
	 * @param driver The WebDriver instance.
	 */
	public CreateAddonsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
	}

	/**
	 * @param driver The WebDriver instance.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public void clickAddItemBtn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(addItemBtn));
		addItemBtn.click();
		Thread.sleep(2000);
	}

	public void enterItemName(String Name) throws InterruptedException 
	{
		wait.until(ExpectedConditions.elementToBeClickable(itemName));
		itemName.sendKeys(Name);
		Thread.sleep(2000);
	}

	public void selectTag(String text) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(tag));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tag);

	    // Find and click the desired option
	    WebElement desiredOption = driver.findElement(By.xpath("//select//option[text()='"+text+"']"));
	    wait.until(ExpectedConditions.elementToBeClickable(desiredOption));
	    desiredOption.click();
		Thread.sleep(2000); // Pause to allow actions to take effect
	}

	public void enterQuantityInStock(String quantity) throws InterruptedException 
	{
		quantityInStock.sendKeys(quantity);
		Thread.sleep(2000);
	}

	public void enterBasePrice(String price) throws InterruptedException 
	{
		basePrice.sendKeys(price);
		Thread.sleep(2000);
	}

	public void enterFlatTax(String tax) throws InterruptedException 
	{
		flatTax.sendKeys(tax);
		Thread.sleep(2000);
	}

	public void enterPerTax(String tax) throws InterruptedException 
	{
		perTax.sendKeys(tax);
		Thread.sleep(2000);
	}

	public void clickSaveBtn() throws InterruptedException 
	{
		saveBtn.click();
		Thread.sleep(2000);
	}

}
