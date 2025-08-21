package com.bookingzoneadmin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BlockSingleSlotCreateBooking {
	
	@FindBy(xpath="//img[@alt='Slot party']") 
	private WebElement selectPlan;  // WebElement for selecting the plan

	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[3]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/div[1]/div[1]/div[1]")
	private WebElement blockSlot;  // WebElement for blocking the slot

	public BlockSingleSlotCreateBooking(WebDriver driver) { 
		PageFactory.initElements(driver, this);
	}
	
	// Method to click on the select plan button
	public void selectPlan() {
		selectPlan.click();
	}

	// Method to click on the block slot button
	public void clickBlockSlot() {
		blockSlot.click();
	}
}
