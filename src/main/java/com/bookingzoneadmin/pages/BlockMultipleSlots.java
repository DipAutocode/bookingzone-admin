package com.bookingzoneadmin.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BlockMultipleSlots
{
	 // WebElements for View Blocked Slots and Block Multiple Slots
	@FindBy(xpath="//span[normalize-space()='View Blocked Slots']")
	WebElement   viewBlockedSlotsButton ;
	@FindBy(xpath="(//input[@name='action'])[2]")
	WebElement   BlockMultipleSlotsButton;
	
	 // WebElements for Slot 1
	@FindBy(xpath="//select[@name='blockedSlots.0.serviceCategory']")
	WebElement   serviceSlot1Dropdown;
	@FindBy(xpath="//select[@name='blockedSlots.0.resource']")
	WebElement   resourceSlot1Dropdown;
	@FindBy(xpath="//select[@name='blockedSlots.0.from']") 
	WebElement   resorceFromSlot1Dropdown;
	@FindBy(xpath="//select[@name='blockedSlots.0.to']") 
	WebElement   resorceToSlot1Dropdown;
	
	 // WebElements for Slot 2
	@FindBy(xpath="//select[@name='blockedSlots.1.serviceCategory']")
	WebElement   serviceslot2Dropdown;
	@FindBy(xpath="//select[@name='blockedSlots.1.resource']")
	WebElement   resoarceSlot2Dropdown;
	@FindBy(xpath="//select[@name='blockedSlots.1.from']")
	WebElement   resorceFromSlot2Dropdown;
	@FindBy(xpath="//select[@name='blockedSlots.1.to']")
	WebElement   resorceToSlot2Dropdown;
	
	 // WebElements for Slot 3
	@FindBy(xpath="//select[@name='blockedSlots.2.serviceCategory']")
	WebElement   serviceslot3Dropdown;
	@FindBy(xpath="//select[@name='blockedSlots.2.resource']") 
	WebElement   resoarceSlot3Dropdown;
	@FindBy(xpath="//select[@name='blockedSlots.2.from']")
	WebElement  resorceFromSlot3Dropdown;
	@FindBy(xpath="//select[@name='blockedSlots.2.to']")
	WebElement   resorceToSlot3Dropdown;
	
	 // WebElements for Slot 4
	@FindBy(xpath="//select[@name='blockedSlots.3.serviceCategory']") 
	WebElement   serviceslot4Dropdown;
	@FindBy(xpath="//select[@name='blockedSlots.3.resource']")
	WebElement   resoarceSlot4Dropdown;
	@FindBy(xpath="//select[@name='blockedSlots.3.from']")
	WebElement   resorceFromSlot4Dropdown;
	@FindBy(xpath="//select[@name='blockedSlots.3.to']")
    WebElement   resorceToSlot4Dropdown;
	
	// WebElements for calander symbol
	@FindBy(xpath="(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-e')])[2]")
	WebElement   calandersymbolSlot1Button;
	@FindBy(xpath="(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-e')])[3]")
	WebElement   calandersymbolSlot2Button;
	@FindBy(xpath="(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-e')])[4]")
	WebElement calandersymbolSlot3Button;
	@FindBy(xpath="(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-e')])[5]")
	WebElement  calandersymbolSlot4Button;
	
	//common
	@FindBy(xpath="//button[@title='Next month']") 
	WebElement   nextMonthButton;
	//select date
	@FindBy(xpath="//button[text()='20']")
	WebElement   selectDate;
	
	@FindBy(xpath="(//input[@id='combo-box-demo'])[7]")
	WebElement   enterStartTimeSlot2;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[13]")
	WebElement   enterStartTimeSlot3;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[19]")
	WebElement   enterStartTimeSlot4;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[4]")
	WebElement   enterEndTimeSlot1;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[10]")
	WebElement   enterEndTimeSlot2;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[16]")
	WebElement   enterEndTimeSlot3;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[22]")
	WebElement   enterEndTimeSlot4;
	
	@FindBy(xpath="(//input[@id='combo-box-demo'])[8]")
	WebElement   enterMinutes_StartTimeSlot2;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[14]")
	WebElement   enterMinutes_StartTimeSlot3;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[20]")
	WebElement   enterMinutes_StartTimeSlot4;
	
	@FindBy(xpath="(//input[@id='combo-box-demo'])[5]") 
	WebElement   enterMinutes_EntTimeSlot1;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[11]")
	WebElement   enterMinutes_EntTimeSlot2;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[17]")
	WebElement   enterMinutes_EntTimeSlot3;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[23]") 
	WebElement   enterMinutes_EntTimeSlot4;
	
	@FindBy(xpath="(//input[@id='combo-box-demo'])[9]") 
	WebElement   aMStartTimeSlot2;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[15]")
	WebElement  aMStartTimeSlot3;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[21]")
	WebElement   aMStartTimeSlot4;
	
	@FindBy(xpath="(//input[@id='combo-box-demo'])[6]")
	WebElement   aMEndTimeSlot1;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[12]") 
	WebElement   aMEndTimeSlot2;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[18]")
	WebElement   aMEndTimeSlot3;
	@FindBy(xpath="(//input[@id='combo-box-demo'])[24]") 
	WebElement   aMEndTimeSlot4;
	
	 // WebElements for common actions like Add More, Save, and Close
	@FindBy(xpath="//button//span[text()='Add More']") WebElement   addMoreButton;
	@FindBy(xpath="//button//span[text()='Save']") WebElement   saveButton;
	@FindBy(xpath="(//span[contains(@class,'MuiButton-st')])[15]") WebElement   crossButton;



	public BlockMultipleSlots (WebDriver driver)
	{ 
		PageFactory.initElements(driver, this);
	}


	public void Click_ViewBlockSlots ()  
	{
		viewBlockedSlotsButton .click();

	}

	public void Select_BlockMultipleSlots() 
	{
		BlockMultipleSlotsButton.click();
	}
// method for first slots
	public void Select_Service_slot1() 
	{
		Select S1=new Select(serviceSlot1Dropdown);
		S1.selectByVisibleText("Entertainment");
	}

	public void Select_Resource_Slot1() 
	{
		Select S1=new Select(resourceSlot1Dropdown);
		S1.selectByVisibleText("GameZone");
	}

	public void Select_ResourceFrom_Slot1() 
	{
		Select S1=new Select(resorceFromSlot1Dropdown);
		S1.selectByVisibleText("1");
	}

	public void Select_ResourceTo_Slot1() 
	{
		Select S1=new Select(resorceToSlot1Dropdown);
		S1.selectByVisibleText("1");
	}
	public void Click_Calandersymbol_Slot1() 
	{
		calandersymbolSlot1Button.click();
	}
	public void Click_NextMonth() 
	{
		nextMonthButton.click();
	}

	public void Select_Date() 
	{
		selectDate.click();
	}

	public void Enter_EndTime_Slot1(WebDriver driver ,String time) 
	{
		Actions A1=new Actions(driver);

		A1.click(enterEndTimeSlot1).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ENTER).perform();
	}

	public void EnterMinutes_EntTime_Slot1(WebDriver driver, String time) 
	{ Actions A1=new Actions(driver);

	A1.click(enterMinutes_EntTimeSlot1).perform();
	A1.sendKeys(time).perform();
	A1.sendKeys(Keys.ENTER).perform();
	}
	
	public void Select_AMEndTime_Slot1(WebDriver driver, String Time) throws InterruptedException 
	{
		Actions A1=new Actions(driver);

		A1.click(aMEndTimeSlot1).perform();
		A1.sendKeys(Time).perform();
		A1.sendKeys(Keys.ARROW_DOWN).perform();

		A1.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);

	}
	
	
	// method for second slots
		public void Select_Service_slot2() 
		{
			Select S1=new Select(serviceslot2Dropdown);
			S1.selectByVisibleText("Entertainment");
		}

		public void Select_Resource_Slot2() 
		{
			Select S1=new Select(resoarceSlot2Dropdown);
			S1.selectByVisibleText("GameZone");
		}

		public void Select_ResourceFrom_Slot2() 
		{
			Select S1=new Select(resorceFromSlot2Dropdown);
			S1.selectByVisibleText("3");
		}

		public void Select_ResourceTo_Slot2() 
		{
			Select S1=new Select(resorceToSlot2Dropdown);
			S1.selectByVisibleText("3");
		}
		public void Click_Calandersymbol_Slot2() 
		{
			calandersymbolSlot2Button.click();
		}
	
		public void Enter_StartTime_Slot2(WebDriver driver, String time) throws InterruptedException 
		{
			Actions A1=new Actions(driver);

			A1.click(enterStartTimeSlot2).perform();
			A1.sendKeys(time).perform();
			
			A1.sendKeys(Keys.ENTER).perform();
			Thread.sleep(2000);
		}
		
		public void EnterMinutes_StartTime_Slot2(WebDriver driver, String time) 
		{
			Actions A1=new Actions(driver);

			A1.click(enterMinutes_StartTimeSlot2).perform();
			A1.sendKeys(time).perform();
			A1.sendKeys(Keys.ENTER).perform();
		}
		
		public void SelectAM_StartTime_Slot2(WebDriver driver, String time) throws InterruptedException 
		{
			Actions A1=new Actions(driver);

			A1.click(aMStartTimeSlot2).perform();
			A1.sendKeys(time).perform();
			A1.sendKeys(Keys.ARROW_DOWN).perform();
			A1.sendKeys(Keys.ENTER).perform();
			Thread.sleep(2000);
		}
		
		public void Enter_EndTime_Slot2(WebDriver driver ,String time) 
		{
			Actions A1=new Actions(driver);

			A1.click(enterEndTimeSlot2).perform();
			A1.sendKeys(time).perform();
			A1.sendKeys(Keys.ENTER).perform();
		}

		public void EnterMinutes_EntTime_Slot2(WebDriver driver, String time) 
		{ Actions A1=new Actions(driver);

		A1.click(enterMinutes_EntTimeSlot2).perform();
		A1.sendKeys(time).perform();
		
		A1.sendKeys(Keys.ENTER).perform();
		}
		
		public void SelectAM_EndTime_Slot2(WebDriver driver, String Time) throws InterruptedException 
		{
			Actions A1=new Actions(driver);

			A1.click(aMEndTimeSlot2).perform();
			A1.sendKeys(Time).perform();
			A1.sendKeys(Keys.ARROW_DOWN).perform();

			A1.sendKeys(Keys.ENTER).perform();
			Thread.sleep(2000);

		}
		
	// methods to block third slot
		
		public void Select_Service_slot3() 
		{
			Select S1=new Select(serviceslot3Dropdown);
			S1.selectByVisibleText("Entertainment");
		}

		public void Select_Resource_Slot3() 
		{
			Select S1=new Select(resoarceSlot3Dropdown);
			S1.selectByVisibleText("GameZone");
		}

		public void Select_ResourceFrom_Slot3() 
		{
			Select S1=new Select(resorceFromSlot3Dropdown);
			S1.selectByVisibleText("3");
		}

		public void Select_ResourceTo_Slot3() 
		{
			Select S1=new Select(resorceToSlot3Dropdown);
			S1.selectByVisibleText("3");
		}
		public void Click_Calandersymbol_Slot3() 
		{
			calandersymbolSlot3Button.click();
		}
	
		public void Enter_StartTime_Slot3(WebDriver driver, String time) 
		{
			Actions A1=new Actions(driver);

			A1.click(enterStartTimeSlot3).perform();
			A1.sendKeys(time).perform();
			A1.sendKeys(Keys.ENTER).perform();
		}
		
		public void EnterMinutes_StartTime_Slot3(WebDriver driver, String time) 
		{
			Actions A1=new Actions(driver);

			A1.click(enterMinutes_StartTimeSlot3).perform();
			A1.sendKeys(time).perform();
			A1.sendKeys(Keys.ENTER).perform();
		}
		
		public void SelectAM_StartTime_Slot3(WebDriver driver, String time) throws InterruptedException 
		{
			Actions A1=new Actions(driver);

			A1.click(aMStartTimeSlot3).perform();
			A1.sendKeys(time).perform();
			A1.sendKeys(Keys.ARROW_DOWN).perform();
			A1.sendKeys(Keys.ENTER).perform();
			Thread.sleep(2000);
		}
		
		public void Enter_EndTime_Slot3(WebDriver driver ,String time) 
		{
			Actions A1=new Actions(driver);

			A1.click(enterEndTimeSlot3).perform();
			A1.sendKeys(time).perform();
			A1.sendKeys(Keys.ENTER).perform();
		}

		public void EnterMinutes_EntTime_Slot3(WebDriver driver, String time) 
		{ Actions A1=new Actions(driver);

		A1.click(enterMinutes_EntTimeSlot3).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ENTER).perform();
		}
		
		public void SelectAM_EndTime_Slot3(WebDriver driver, String Time) throws InterruptedException 
		{
			Actions A1=new Actions(driver);

			A1.click(aMEndTimeSlot3).perform();
			A1.sendKeys(Time).perform();
			A1.sendKeys(Keys.ARROW_DOWN).perform();

			A1.sendKeys(Keys.ENTER).perform();
			Thread.sleep(2000);

		}
	
	//Block fourth slot
		

		public void Select_Service_slot4() 
		{
			Select S1=new Select(serviceslot4Dropdown);
			S1.selectByVisibleText("Entertainment");
		}

		public void Select_Resource_Slot4() 
		{
			Select S1=new Select(resoarceSlot4Dropdown);
			S1.selectByVisibleText("GameZone");
		}

		public void Select_ResourceFrom_Slot4() 
		{
			Select S1=new Select(resorceFromSlot4Dropdown);
			S1.selectByVisibleText("5");
		}

		public void Select_ResourceTo_Slot4() 
		{
			Select S1=new Select(resorceToSlot4Dropdown);
			S1.selectByVisibleText("5");
		}
		public void Click_Calandersymbol_Slot4() 
		{
			calandersymbolSlot4Button.click();
		}
	
		public void Enter_StartTime_Slot4(WebDriver driver, String time) 
		{
			Actions A1=new Actions(driver);

			A1.click(enterStartTimeSlot4).perform();
			A1.sendKeys(time).perform();
			A1.sendKeys(Keys.ENTER).perform();
		}
		
		public void EnterMinutes_StartTimeSlot4(WebDriver driver, String time) 
		{
			Actions A1=new Actions(driver);

			A1.click(enterMinutes_StartTimeSlot4).perform();
			A1.sendKeys(time).perform();
			A1.sendKeys(Keys.ENTER).perform();
		}
		
		public void SelectAMStartTimeSlot4(WebDriver driver, String time) throws InterruptedException 
		{
			Actions A1=new Actions(driver);

			A1.click(aMStartTimeSlot4).perform();
			A1.sendKeys(time).perform();
			A1.sendKeys(Keys.ARROW_DOWN).perform();
			A1.sendKeys(Keys.ENTER).perform();
			Thread.sleep(2000);
		}
		
		public void Enter_EndTime_Slot4(WebDriver driver ,String time) 
		{
			Actions A1=new Actions(driver);

			A1.click(enterEndTimeSlot4).perform();
			A1.sendKeys(time).perform();
			A1.sendKeys(Keys.ENTER).perform();
		}

		public void EnterMinutes_EntTime_Slot4(WebDriver driver, String time) 
		{ Actions A1=new Actions(driver);

		A1.click(enterMinutes_EntTimeSlot4).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ENTER).perform();
		}
		
		public void SelectAM_EndTime_Slot4(WebDriver driver, String Time) throws InterruptedException 
		{
			Actions A1=new Actions(driver);

			A1.click(aMEndTimeSlot4).perform();
			A1.sendKeys(Time).perform();
			A1.sendKeys(Keys.ARROW_DOWN).perform();

			A1.sendKeys(Keys.ENTER).perform();
			Thread.sleep(2000);

		}

	public void Click_AddMore() 
	{
		addMoreButton.click();
	}

	public void Click_SaveButton() 
	{
		saveButton.click();
	}


	public void Click_CrossSign() 
	{
		crossButton.click();
	}


}
