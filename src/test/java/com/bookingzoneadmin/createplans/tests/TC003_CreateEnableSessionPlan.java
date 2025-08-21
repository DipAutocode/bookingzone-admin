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
import com.bookingzoneadmin.pages.OutletDetailsPage;
import com.bookingzoneadmin.pages.OutletListPage;
import com.bookingzoneadmin.utils.CreatePlansCommonAction;
import com.bookingzoneadmin.utils.ExtentReportManager;
import com.bookingzoneadmin.utils.UtilityClass;

//// Class to Enable session plan 
public class TC003_CreateEnableSessionPlan extends BaseClass
{
	BusinessHomePage businessHomePage;
	OutletListPage outletList;
	OutletDetailsPage outletDetails;
	CreateBUY_NOWServicePlanPage createBUY_NOWServicePlan;
	CreatePackageSessionPlan pkgSession;
	CreateEnableSessionPlan enableSession;
	CreatePlansCommonAction createPlansCommonAction;

	// Test data variables
	private String planName;
	private String imgPath;
	private String minTimeInAdvanced;
	private String maxTimeInAdvanced;
	private String from;
	private String to;
	private String maxGuestAllowed;
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
	private String startHourSlot2;
	private String startMinuteSlot2;
	private String startAmpmSlot2;
	private String endHourSlot2;
	private String endMinuteSlot2;
	private String endAmpmSlot2;
	private String refundType;
	private String refundAmount;
	private String processingFeeName;
	private String processingFeeAmount;
	private String personPriceSlot1;
	private String personPriceSlot2;
	private String slotNo1;
	private String slotNo2;

	@BeforeClass
	public void initializeTest (ITestContext Context) throws InterruptedException, EncryptedDocumentException, IOException 
	{
		businessHomePage = new BusinessHomePage(driver);
		outletList = new OutletListPage(driver);
		outletDetails = new OutletDetailsPage(driver);
		createBUY_NOWServicePlan=new CreateBUY_NOWServicePlanPage(driver);
		pkgSession=new CreatePackageSessionPlan(driver);
		enableSession=new CreateEnableSessionPlan(driver);
		createPlansCommonAction=new CreatePlansCommonAction(businessHomePage, outletList, outletDetails, createBUY_NOWServicePlan, pkgSession, null, null);

		fetchTestData();
	}

	private void fetchTestData() throws IOException 
	{
		planName=UtilityClass.getDataFromEs(3, "Plan Name", "Create_Plans");
		imgPath=UtilityClass.getDataFromEs(3, "URL /Path of Img", "Create_Plans");
		minTimeInAdvanced=UtilityClass.getDataFromEs(3,"MinTimeInAdvanced", "Create_Plans");
		maxTimeInAdvanced=UtilityClass.getDataFromEs(3,"MaxTimeInAdvanced", "Create_Plans");
		from=UtilityClass.getDataFromEs(3,"From", "Create_Plans");
		to=UtilityClass.getDataFromEs(3,"To", "Create_Plans");
		maxGuestAllowed=UtilityClass.getDataFromEs(3, "MaxGuestsAllowed", "Create_Plans");
		purchaseItemsName=UtilityClass.getDataFromEs(3,"PurchaseItemsName", "Create_Plans");
		purchaseItemsprice=UtilityClass.getDataFromEs(3,"PurchaseItemsprice", "Create_Plans");
		purchaseItemSaleTax=UtilityClass.getDataFromEs(3,"PurchaseItemSaleTax", "Create_Plans");
		purchaseItemTimeFrame=UtilityClass.getDataFromEs(3,"PurchaseItemTimeFrame", "Create_Plans");
		//Slot1
		startHourSlot1=UtilityClass.getDataFromEs(3, "Slot1StartTimehour", "Create_Plans");	
		startMinuteSlot1=UtilityClass.getDataFromEs(3, "StartMinute", "Create_Plans");
		startAmpmSlot1=UtilityClass.getDataFromEs(3, "StartTimeAMPM", "Create_Plans");
		endHourSlot1=UtilityClass.getDataFromEs(3, "Slot1EndTimehour", "Create_Plans");	
		endMinuteSlot1=UtilityClass.getDataFromEs(3, "EndMinute", "Create_Plans");
		endAmpmSlot1=UtilityClass.getDataFromEs(3, "EndTimeAMPM", "Create_Plans");
		//slot2
		startHourSlot2=UtilityClass.getDataFromEs(3, "Slot2StartTimehour", "Create_Plans");	
		startMinuteSlot2=UtilityClass.getDataFromEs(3, "StartMinute", "Create_Plans");
		startAmpmSlot2=UtilityClass.getDataFromEs(3, "StartTimeAMPM", "Create_Plans");
		endHourSlot2=UtilityClass.getDataFromEs(3, "Slot2EndTimehour", "Create_Plans");	
		endMinuteSlot2=UtilityClass.getDataFromEs(3, "EndMinute", "Create_Plans");
		endAmpmSlot2=UtilityClass.getDataFromEs(3, "EndTimeAMPM", "Create_Plans");
		
		personPriceSlot1=UtilityClass.getDataFromEs(3, "PackageSlot1/Person/guest/slot/Event  price", "Create_Plans");
		personPriceSlot2=UtilityClass.getDataFromEs(3, "PackageSlot2/Person/guest/slotPrice", "Create_Plans");
		
		refundType=UtilityClass.getDataFromEs(3, "Refund Type", "Create_Plans");
		refundAmount=UtilityClass.getDataFromEs(3, "Refund Amount", "Create_Plans");
		processingFeeName=UtilityClass.getDataFromEs(3, "ProcessingFeeName", "Create_Plans");
		processingFeeAmount=UtilityClass.getDataFromEs(3, "ProcessingFeeAmount", "Create_Plans");
		slotNo1=UtilityClass.getDataFromEs(3, "SlotNo1", "Create_Plans");
		slotNo2=UtilityClass.getDataFromEs(3, "SlotNo2", "Create_Plans");
	}

	// create Enable session plan
	@Test (testName = "testCreateEnableSessionPlan")
	public void testCreateEnableSessionPlan() throws IOException, InterruptedException 
	{
		try
		{
			createPlansCommonAction.enterNameandUploadImage(planName, imgPath);
			enableSession.clickEnableSessionCheckBox();

			createPlansCommonAction.configurePlanDetails(minTimeInAdvanced, maxTimeInAdvanced, from, to);
			enableSession.clickPricePerPersonCheckBox();
			pkgSession.enterMaxGuestsAllowed(maxGuestAllowed);

			createPlansCommonAction.enterSaleItemDetails(purchaseItemsName, purchaseItemsprice, purchaseItemSaleTax, purchaseItemTimeFrame);

			createBUY_NOWServicePlan.selectSlotTime(slotNo1, startHourSlot1, startMinuteSlot1, startAmpmSlot1, endHourSlot1, endMinuteSlot1, endAmpmSlot1);//slot1
			enableSession.enterPerPersonPriceSlot1(personPriceSlot1);
			
			createBUY_NOWServicePlan.addMoreReservationSlot();
			createBUY_NOWServicePlan.selectSlotTime(slotNo2, startHourSlot2, startMinuteSlot2, startAmpmSlot2, endHourSlot2, endMinuteSlot2, endAmpmSlot2);//slot2
			enableSession.enterPerPersonPriceSlot2(personPriceSlot2);
			
			// Enter Pricing and Refund Details
			createPlansCommonAction.enterRefundDetails(refundType, refundAmount);

			// Enter Processing Fees
			createPlansCommonAction.enterProcessingFees(processingFeeName, processingFeeAmount);

			// Finalize and Assert
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
			ExtentReportManager.logPass("Enable Session plan created successfully...!");
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
		ExtentReportManager.logFail("Enable Session plan is not created due to " + message + "...!");
		System.out.println("Enable Session plan is not created due to " + message + "...!");
		Assert.fail(message);
	}

	// Method to handle exceptions and log error messages
	public void handleException(Exception e) {
		e.printStackTrace();
		String getCause = e.getLocalizedMessage();
		System.out.println("issue cause is :"+getCause);
		ExtentReportManager.logFail("testCreateEnableSessionPlan test case is fail due to" + getCause);
		Assert.fail("Test case failed due to exception: " + getCause);
	}
}
