package com.bookingzoneadmin.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Block_slots
{
	@FindBy(xpath="//span[normalize-space()='View Blocked Slots']")
	private WebElement   ViewBlockSlots;

	@FindBy(xpath="(//input[@name='action'])[2]")
	private WebElement   BlockMultipleSlots;

	@FindBy(xpath="//select[@name='blockedSlots.0.serviceCategory']")
	private WebElement   SelectServiceslot1;

	@FindBy(xpath="//select[@name='blockedSlots.0.resource']")
	private WebElement   SelectResoarceSlot1;

	@FindBy(xpath="//select[@name='blockedSlots.0.from']")
	private WebElement   SelectResorceFromSlot1;

	@FindBy(xpath="//select[@name='blockedSlots.0.to']")
	private  WebElement   SelectResorceToSlot1;

	@FindBy(xpath="//select[@name='blockedSlots.1.serviceCategory']")
	private WebElement   SelectServiceslot2;

	@FindBy(xpath="//select[@name='blockedSlots.1.resource']")
	private WebElement   SelectResoarceSlot2;

	@FindBy(xpath="//select[@name='blockedSlots.1.from']")
	private WebElement   SelectResorceFromSlot2;

	@FindBy(xpath="//select[@name='blockedSlots.1.to']")
	private WebElement   SelectResorceToSlot2;

	@FindBy(xpath="//select[@name='blockedSlots.2.serviceCategory']")
	private WebElement   SelectServiceslot3;

	@FindBy(xpath="//select[@name='blockedSlots.2.resource']")
	private WebElement   SelectResoarceSlot3;

	@FindBy(xpath="//select[@name='blockedSlots.2.from']")
	private WebElement   SelectResorceFromSlot3;

	@FindBy(xpath="//select[@name='blockedSlots.2.to']")
	private WebElement   SelectResorceToSlot3;

	@FindBy(xpath="//select[@name='blockedSlots.3.serviceCategory']")
	private WebElement   SelectServiceslot4;

	@FindBy(xpath="//select[@name='blockedSlots.3.resource']")
	private WebElement   SelectResoarceSlot4;

	@FindBy(xpath="//select[@name='blockedSlots.3.from']") 
	private WebElement   SelectResorceFromSlot4;

	@FindBy(xpath="//select[@name='blockedSlots.3.to']") 
	private WebElement   SelectResorceToSlot4;


	@FindBy(xpath="(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-e')])[2]")
	private WebElement   ClickCalandersymbolSlot1;

	@FindBy(xpath="(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-e')])[3]")
	private WebElement   ClickCalandersymbolSlot2;

	@FindBy(xpath="(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-e')])[4]")
	private WebElement   ClickCalandersymbolSlot3;

	@FindBy(xpath="(//button[contains(@class,'MuiButtonBase-root MuiIconButton-root MuiIconButton-e')])[5]")
	private WebElement   ClickCalandersymbolSlot4;

	//common
	@FindBy(xpath="//button[@title='Next month']") 
	private WebElement   NextMonth;

	//select date
	@FindBy(xpath="//button[text()='15']")
	private WebElement   SelectDate;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[7]")
	private WebElement   EnterStartTimeSlot2;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[13]")
	private WebElement   EnterStartTimeSlot3;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[19]") 
	private WebElement   EnterStartTimeSlot4;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[4]")
	private WebElement   EnterEndTimeSlot1;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[10]") 
	private WebElement   EnterEndTimeSlot2;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[16]")
	private WebElement   EnterEndTimeSlot3;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[22]")
	private WebElement   EnterEndTimeSlot4;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[8]")
	private WebElement   EnterMinutes_StartTimeSlot2;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[14]")
	private WebElement   EnterMinutes_StartTimeSlot3;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[20]")
	private WebElement   EnterMinutes_StartTimeSlot4;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[5]") 
	private WebElement   EnterMinutes_EntTimeSlot1;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[11]")
	private WebElement   EnterMinutes_EntTimeSlot2;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[17]") 
	private WebElement   EnterMinutes_EntTimeSlot3;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[23]")
	private  WebElement   EnterMinutes_EntTimeSlot4;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[9]")
	private WebElement   SelectAMStartTimeSlot2;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[15]")
	private WebElement   SelectAMStartTimeSlot3;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[21]")
	private WebElement   SelectAMStartTimeSlot4;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[6]")
	private WebElement   SelectAMEndTimeSlot1;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[12]")
	private WebElement   SelectAMEndTimeSlot2;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[18]")
	private WebElement   SelectAMEndTimeSlot3;

	@FindBy(xpath="(//input[@id='combo-box-demo'])[24]")
	private WebElement   SelectAMEndTimeSlot4;

	//common
	@FindBy(xpath="//button//span[text()='Add More']") 
	private WebElement   AddMore;

	@FindBy(xpath="//button//span[text()='Save']")
	private WebElement   SaveButton;

	@FindBy(xpath="(//span[contains(@class,'MuiButton-st')])[15]")
	private WebElement   ClickCross;



	public Block_slots (WebDriver driver)
	{ 
		PageFactory.initElements(driver, this);
	}


	public void Click_ViewBlockSlots ()  
	{
		ViewBlockSlots.click();

	}

	public void Select_BlockMultipleSlots() 
	{
		BlockMultipleSlots.click();
	}
	// method for first slots
	public void Select_Service_slot1() 
	{
		Select S1=new Select(SelectServiceslot1);
		S1.selectByVisibleText("Entertainment");
	}

	public void Select_Resource_Slot1() 
	{
		Select S1=new Select(SelectResoarceSlot1);
		S1.selectByVisibleText("GameZone");
	}

	public void Select_ResourceFrom_Slot1() 
	{
		Select S1=new Select(SelectResorceFromSlot1);
		S1.selectByVisibleText("1");
	}

	public void Select_ResourceTo_Slot1() 
	{
		Select S1=new Select(SelectResorceToSlot1);
		S1.selectByVisibleText("1");
	}
	public void Click_Calandersymbol_Slot1() 
	{
		ClickCalandersymbolSlot1.click();
	}
	public void Click_NextMonth() 
	{
		NextMonth.click();
	}

	public void Select_Date() 
	{
		SelectDate.click();
	}

	public void Enter_EndTime_Slot1(WebDriver driver ,String time) 
	{
		Actions A1=new Actions(driver);

		A1.click(EnterEndTimeSlot1).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ENTER).perform();
	}

	public void EnterMinutes_EntTime_Slot1(WebDriver driver, String time) 
	{ Actions A1=new Actions(driver);

	A1.click(EnterMinutes_EntTimeSlot1).perform();
	A1.sendKeys(time).perform();
	A1.sendKeys(Keys.ENTER).perform();
	}

	public void Select_AMEndTime_Slot1(WebDriver driver, String Time) throws InterruptedException 
	{
		Actions A1=new Actions(driver);

		A1.click(SelectAMEndTimeSlot1).perform();
		A1.sendKeys(Time).perform();
		A1.sendKeys(Keys.ARROW_DOWN).perform();

		A1.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);

	}


	// method for second slots
	public void Select_Service_slot2() 
	{
		Select S1=new Select(SelectServiceslot2);
		S1.selectByVisibleText("Entertainment");
	}

	public void Select_Resource_Slot2() 
	{
		Select S1=new Select(SelectResoarceSlot2);
		S1.selectByVisibleText("GameZone");
	}

	public void Select_ResourceFrom_Slot2() 
	{
		Select S1=new Select(SelectResorceFromSlot2);
		S1.selectByVisibleText("3");
	}

	public void Select_ResourceTo_Slot2() 
	{
		Select S1=new Select(SelectResorceToSlot2);
		S1.selectByVisibleText("3");
	}
	public void Click_Calandersymbol_Slot2() 
	{
		ClickCalandersymbolSlot2.click();
	}

	public void Enter_StartTime_Slot2(WebDriver driver, String time) throws InterruptedException 
	{
		Actions A1=new Actions(driver);

		A1.click(EnterStartTimeSlot2).perform();
		A1.sendKeys(time).perform();

		A1.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
	}

	public void EnterMinutes_StartTime_Slot2(WebDriver driver, String time) 
	{
		Actions A1=new Actions(driver);

		A1.click(EnterMinutes_StartTimeSlot2).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ENTER).perform();
	}

	public void SelectAM_StartTime_Slot2(WebDriver driver, String time) throws InterruptedException 
	{
		Actions A1=new Actions(driver);

		A1.click(SelectAMStartTimeSlot2).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ARROW_DOWN).perform();
		A1.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
	}

	public void Enter_EndTime_Slot2(WebDriver driver ,String time) 
	{
		Actions A1=new Actions(driver);

		A1.click(EnterEndTimeSlot2).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ENTER).perform();
	}

	public void EnterMinutes_EntTime_Slot2(WebDriver driver, String time) 
	{ Actions A1=new Actions(driver);

	A1.click(EnterMinutes_EntTimeSlot2).perform();
	A1.sendKeys(time).perform();

	A1.sendKeys(Keys.ENTER).perform();
	}

	public void SelectAM_EndTime_Slot2(WebDriver driver, String Time) throws InterruptedException 
	{
		Actions A1=new Actions(driver);

		A1.click(SelectAMEndTimeSlot2).perform();
		A1.sendKeys(Time).perform();
		A1.sendKeys(Keys.ARROW_DOWN).perform();

		A1.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);

	}

	// methods to block third slot

	public void Select_Service_slot3() 
	{
		Select S1=new Select(SelectServiceslot3);
		S1.selectByVisibleText("Entertainment");
	}

	public void Select_Resource_Slot3() 
	{
		Select S1=new Select(SelectResoarceSlot3);
		S1.selectByVisibleText("GameZone");
	}

	public void Select_ResourceFrom_Slot3() 
	{
		Select S1=new Select(SelectResorceFromSlot3);
		S1.selectByVisibleText("3");
	}

	public void Select_ResourceTo_Slot3() 
	{
		Select S1=new Select(SelectResorceToSlot3);
		S1.selectByVisibleText("3");
	}
	public void Click_Calandersymbol_Slot3() 
	{
		ClickCalandersymbolSlot3.click();
	}

	public void Enter_StartTime_Slot3(WebDriver driver, String time) 
	{
		Actions A1=new Actions(driver);

		A1.click(EnterStartTimeSlot3).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ENTER).perform();
	}

	public void EnterMinutes_StartTime_Slot3(WebDriver driver, String time) 
	{
		Actions A1=new Actions(driver);

		A1.click(EnterMinutes_StartTimeSlot3).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ENTER).perform();
	}

	public void SelectAM_StartTime_Slot3(WebDriver driver, String time) throws InterruptedException 
	{
		Actions A1=new Actions(driver);

		A1.click(SelectAMStartTimeSlot3).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ARROW_DOWN).perform();
		A1.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
	}

	public void Enter_EndTime_Slot3(WebDriver driver ,String time) 
	{
		Actions A1=new Actions(driver);

		A1.click(EnterEndTimeSlot3).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ENTER).perform();
	}

	public void EnterMinutes_EntTime_Slot3(WebDriver driver, String time) 
	{ Actions A1=new Actions(driver);

	A1.click(EnterMinutes_EntTimeSlot3).perform();
	A1.sendKeys(time).perform();
	A1.sendKeys(Keys.ENTER).perform();
	}

	public void SelectAM_EndTime_Slot3(WebDriver driver, String Time) throws InterruptedException 
	{
		Actions A1=new Actions(driver);

		A1.click(SelectAMEndTimeSlot3).perform();
		A1.sendKeys(Time).perform();
		A1.sendKeys(Keys.ARROW_DOWN).perform();

		A1.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);

	}

	//Block fourth slot


	public void Select_Service_slot4() 
	{
		Select S1=new Select(SelectServiceslot4);
		S1.selectByVisibleText("Entertainment");
	}

	public void Select_Resource_Slot4() 
	{
		Select S1=new Select(SelectResoarceSlot4);
		S1.selectByVisibleText("GameZone");
	}

	public void Select_ResourceFrom_Slot4() 
	{
		Select S1=new Select(SelectResorceFromSlot4);
		S1.selectByVisibleText("5");
	}

	public void Select_ResourceTo_Slot4() 
	{
		Select S1=new Select(SelectResorceToSlot4);
		S1.selectByVisibleText("5");
	}
	public void Click_Calandersymbol_Slot4() 
	{
		ClickCalandersymbolSlot4.click();
	}

	public void Enter_StartTime_Slot4(WebDriver driver, String time) 
	{
		Actions A1=new Actions(driver);

		A1.click(EnterStartTimeSlot4).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ENTER).perform();
	}

	public void EnterMinutes_StartTimeSlot4(WebDriver driver, String time) 
	{
		Actions A1=new Actions(driver);

		A1.click(EnterMinutes_StartTimeSlot4).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ENTER).perform();
	}

	public void SelectAMStartTimeSlot4(WebDriver driver, String time) throws InterruptedException 
	{
		Actions A1=new Actions(driver);

		A1.click(SelectAMStartTimeSlot4).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ARROW_DOWN).perform();
		A1.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
	}

	public void Enter_EndTime_Slot4(WebDriver driver ,String time) 
	{
		Actions A1=new Actions(driver);

		A1.click(EnterEndTimeSlot4).perform();
		A1.sendKeys(time).perform();
		A1.sendKeys(Keys.ENTER).perform();
	}

	public void EnterMinutes_EntTime_Slot4(WebDriver driver, String time) 
	{ Actions A1=new Actions(driver);

	A1.click(EnterMinutes_EntTimeSlot4).perform();
	A1.sendKeys(time).perform();
	A1.sendKeys(Keys.ENTER).perform();
	}

	public void SelectAM_EndTime_Slot4(WebDriver driver, String Time) throws InterruptedException 
	{
		Actions A1=new Actions(driver);

		A1.click(SelectAMEndTimeSlot4).perform();
		A1.sendKeys(Time).perform();
		A1.sendKeys(Keys.ARROW_DOWN).perform();

		A1.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);

	}

	public void Click_AddMore() 
	{
		AddMore.click();
	}

	public void Click_SaveButton() 
	{
		SaveButton.click();
	}


	public void Click_CrossSign(WebDriver driver) 
	{
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", ClickCross);    
		ClickCross.click();
	}


}
