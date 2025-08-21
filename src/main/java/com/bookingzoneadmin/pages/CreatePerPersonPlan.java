package com.bookingzoneadmin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatePerPersonPlan {

    // WebElements declaration using @FindBy annotation with proper naming conventions
	@FindBy(xpath="//select[@name='schedules.0.dateRange.0.reservationSlots.0.pricePerPerson.0.timeFrame.0.time']")
	private WebElement   person1TimeFrame;


    public CreatePerPersonPlan(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void selectPerson1TimeFrame(int input) throws InterruptedException 
	{
		Select s1=new Select(person1TimeFrame);
		s1.selectByIndex(input);
		Thread.sleep(2000);
	}

   
}
