package com.bookingzoneadmin.createplans.tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bookingzoneadmin.pages.BaseClass;
import com.bookingzoneadmin.pages.BusinessHomePage;
import com.bookingzoneadmin.pages.CreateBUY_NOWServicePlanPage;
import com.bookingzoneadmin.pages.CreateEnableSessionPlan;
import com.bookingzoneadmin.pages.CreatePackageSessionPlan;
import com.bookingzoneadmin.pages.CreatePackageTimePlan;
import com.bookingzoneadmin.pages.CreatePerEventPlan;
import com.bookingzoneadmin.pages.CreatePerPersonPlan;
import com.bookingzoneadmin.pages.OutletDetailsPage;
import com.bookingzoneadmin.pages.OutletListPage;
import com.bookingzoneadmin.utils.CreatePlansCommonAction;
import com.bookingzoneadmin.utils.ExtentReportManager;
import com.bookingzoneadmin.utils.UtilityClass;
// Class To Create Per Person Plan
public class TC007_CreatePerPersonPlan extends BaseClass
{
	BusinessHomePage businessHomePage;
	OutletListPage outletList;
	OutletDetailsPage outletDetails;
	CreateBUY_NOWServicePlanPage createBUY_NOWServicePlan;
	CreatePackageSessionPlan pkgSession;
	CreatePackageTimePlan pkgTime;
	CreatePerEventPlan perEvent;
	CreateEnableSessionPlan enableSession;
	CreatePerPersonPlan perPerson;
	CreatePlansCommonAction createPlansCommonAction;


	// Test data variables
	private String planName;
	private String imgPath;
	private String minTimeInAdvanced;
	private String maxTimeInAdvanced;
	private String from;
	private String to;
	private String maxEventAllowed;
	private String minGuestAllowed;
	private String maxGuestPerSlot;
	private String maxGuestsAllowed;
	private String purchaseItemsName;
	private String purchaseItemsprice;
	private String purchaseItemSaleTax;
	private String purchaseItemTimeFrame;
	private String startHourSlot1;
	private String startMinuteSlot1;
	private String startAmpmSlot1;
	private String endHourSlot1;
	private String endMinuteSlot1;
	private String endAmpmSlot1;
	private String refundType;
	private String refundAmount;
	private String processingFeeName;
	private String processingFeeAmount;
	private String personPriceSlot1;
	private String slotNo1;

	@BeforeClass
	public void initializeTest (ITestContext Context) throws InterruptedException, EncryptedDocumentException, IOException 
	{
		businessHomePage = new BusinessHomePage(driver);
		outletList = new OutletListPage(driver);
		outletDetails = new OutletDetailsPage(driver);
		createBUY_NOWServicePlan=new CreateBUY_NOWServicePlanPage(driver);
		pkgSession=new CreatePackageSessionPlan(driver);
		pkgTime=new CreatePackageTimePlan(driver);
		perEvent=new CreatePerEventPlan(driver);
		enableSession=new CreateEnableSessionPlan(driver);
		perPerson=new CreatePerPersonPlan(driver);
		createPlansCommonAction=new CreatePlansCommonAction(businessHomePage, outletList, outletDetails, createBUY_NOWServicePlan, pkgSession, pkgTime, perEvent);

		fetchTestData();
	}

	private void fetchTestData() throws IOException 
	{
		planName=UtilityClass.getDataFromEs(7, "Plan Name", "Create_Plans");
		imgPath=UtilityClass.getDataFromEs(7, "URL /Path of Img", "Create_Plans");
		minTimeInAdvanced=UtilityClass.getDataFromEs(7,"MinTimeInAdvanced", "Create_Plans");
		maxTimeInAdvanced=UtilityClass.getDataFromEs(7,"MaxTimeInAdvanced", "Create_Plans");
		from=UtilityClass.getDataFromEs(7,"From", "Create_Plans");
		to=UtilityClass.getDataFromEs(7,"To", "Create_Plans");
		maxEventAllowed=UtilityClass.getDataFromEs(7, "MaxPackages/Event Allowed", "Create_Plans");
		minGuestAllowed=UtilityClass.getDataFromEs(7, "MinGuestAllowed", "Create_Plans");
		maxGuestPerSlot=UtilityClass.getDataFromEs(7, "MaxGuestPerSlot", "Create_Plans");
		maxGuestsAllowed=UtilityClass.getDataFromEs(7, "MaxGuestsAllowed", "Create_Plans");

		purchaseItemsName=UtilityClass.getDataFromEs(7,"PurchaseItemsName", "Create_Plans");
		purchaseItemsprice=UtilityClass.getDataFromEs(7,"PurchaseItemsprice", "Create_Plans");
		purchaseItemSaleTax=UtilityClass.getDataFromEs(7,"PurchaseItemSaleTax", "Create_Plans");
		purchaseItemTimeFrame=UtilityClass.getDataFromEs(7,"PurchaseItemTimeFrame", "Create_Plans");
		//Slot1
		startHourSlot1=UtilityClass.getDataFromEs(7, "Slot1StartTimehour", "Create_Plans");	
		startMinuteSlot1=UtilityClass.getDataFromEs(7, "StartMinute", "Create_Plans");
		startAmpmSlot1=UtilityClass.getDataFromEs(7, "StartTimeAMPM", "Create_Plans");
		endHourSlot1=UtilityClass.getDataFromEs(7, "Slot1EndTimehour", "Create_Plans");	
		endMinuteSlot1=UtilityClass.getDataFromEs(7, "EndMinute", "Create_Plans");
		endAmpmSlot1=UtilityClass.getDataFromEs(7, "EndTimeAMPM", "Create_Plans");
		personPriceSlot1=UtilityClass.getDataFromEs(7, "PackageSlot1/Person/guest/slot/Event  price", "Create_Plans");
		refundType=UtilityClass.getDataFromEs(7, "Refund Type", "Create_Plans");
		refundAmount=UtilityClass.getDataFromEs(7, "Refund Amount", "Create_Plans");
		processingFeeName=UtilityClass.getDataFromEs(7, "ProcessingFeeName", "Create_Plans");
		processingFeeAmount=UtilityClass.getDataFromEs(7, "ProcessingFeeAmount", "Create_Plans");
		slotNo1=UtilityClass.getDataFromEs(7, "SlotNo1", "Create_Plans");

	}


	// create Per Person plan
	@Test (testName = "testCreatePerPersonPlan")
	public void testCreatePerPersonPlan() throws IOException, InterruptedException 
	{
		try
		{ 
			createPlansCommonAction.enterNameandUploadImage(planName, imgPath);

			createPlansCommonAction.configurePlanDetails(minTimeInAdvanced, maxTimeInAdvanced, from, to);
			enableSession.clickPricePerPersonCheckBox();

			createPlansCommonAction.enterGuestDetailsForEventPlan(maxEventAllowed, minGuestAllowed, maxGuestPerSlot, maxGuestsAllowed, false);
			createPlansCommonAction.enterSaleItemDetails(purchaseItemsName, purchaseItemsprice, purchaseItemSaleTax, purchaseItemTimeFrame);
			createBUY_NOWServicePlan.selectSlotTime(slotNo1, startHourSlot1, startMinuteSlot1, startAmpmSlot1, endHourSlot1, endMinuteSlot1, endAmpmSlot1);//slot1
			pkgTime.selectSlot1TimeIncrement(3);

			enableSession.enterPerPersonPriceSlot1(personPriceSlot1);
			perPerson.selectPerson1TimeFrame(2);

			createPlansCommonAction.enterRefundDetails(refundType, refundAmount);
			createPlansCommonAction.enterProcessingFees(processingFeeName, processingFeeAmount);
			createPlansCommonAction.finalizePlanCreation();
			validatePlanCreation();
		}

		catch(Exception e) 
		{
			handleException(e);

		}

	}

	// Method to validate outlet creation
	private void validatePlanCreation() {

		String pageSource = driver.getPageSource();

		if (pageSource.contains("Service item has been successfully saved..!")) {
			ExtentReportManager.logPass("Per Person plan created successfully...!");
		} else {
			String message = driver.getPageSource();
			checkFailureMessages(message);
		}
	}

	// Method to check for specific failure messages
	private void checkFailureMessages(String pageSource) {
		if(pageSource.contains("Title is required"))
		{
			handleFailure("Tital is required. Please provide a valid tital.");
		}
		else if(pageSource.contains("Days is required"))
		{
			handleFailure("Days is required is required. Please provide a Days.");
		}
		else if(pageSource.contains("Required"))
		{
			handleFailure("Some feilds are required. Please provide a required feilds.");
		}
		else if(pageSource.contains("Atleast 1 booking frequency is required."))
		{
			handleFailure("Atleast 1 booking frequency is required.");
		}

		else if(pageSource.contains("Max Guest is required"))
		{
			handleFailure("Max Guest is required. Please provide a max guest count.");
		}	
		else if(pageSource.contains("Time Inrcement is required"))
		{
			handleFailure("Time Inrcement is required. Please provide a time increment.");
		}
		else if(pageSource.contains("Refund Amount is required"))
		{
			handleFailure("Refund Amount is required. Please provide a refund amount.");
		}
		else if(pageSource.contains("Fee Name is required"))
		{
			handleFailure("Fee Name is required. Please provide a fee name.");
		}
	}
	/**
	 * Method to handle test failures.
	 * 
	 * @param message The failure message
	 */
	private void handleFailure(String message) {
		ExtentReportManager.logFail("Per Person plan is not created due to " + message + "...!");
		System.out.println("Per Person Slot plan is not created due to " + message + "...!");
		Assert.fail(message);
	}
	// Method to handle exceptions and log error messages
	public void handleException(Exception e) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		System.out.println("issue cause is :"+getCause);
		ExtentReportManager.logFail("testCreatePerPersonPlan test case is fail due to" + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}

}
