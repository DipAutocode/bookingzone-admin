package com.bookingzoneadmin.utils;

import java.awt.AWTException;
import java.io.IOException;

import com.bookingzoneadmin.pages.BusinessHomePage;
import com.bookingzoneadmin.pages.CreateBUY_NOWServicePlanPage;
import com.bookingzoneadmin.pages.CreatePackageSessionPlan;
import com.bookingzoneadmin.pages.CreatePackageTimePlan;
import com.bookingzoneadmin.pages.CreatePerEventPlan;
import com.bookingzoneadmin.pages.OutletDetailsPage;
import com.bookingzoneadmin.pages.OutletListPage;

public class CreatePlansCommonAction 
{
	private BusinessHomePage businessHomePage;
	private OutletListPage outletList;
	private OutletDetailsPage outletDetails;
	private CreateBUY_NOWServicePlanPage createBUY_NOWServicePlan;
	private CreatePackageSessionPlan pkgSession;
	private CreatePackageTimePlan pkgTime;
	CreatePerEventPlan perEvent;



	public CreatePlansCommonAction(BusinessHomePage businessHomePage, OutletListPage outletList, 
			OutletDetailsPage outletDetails, CreateBUY_NOWServicePlanPage createBUY_NOWServicePlan, 
			CreatePackageSessionPlan pkgSession, CreatePackageTimePlan pkgTime, CreatePerEventPlan perEvent ) {
		this.businessHomePage = businessHomePage;
		this.outletList = outletList;
		this.outletDetails = outletDetails;
		this.createBUY_NOWServicePlan = createBUY_NOWServicePlan;
		this.pkgSession = pkgSession;
		this.pkgTime=pkgTime;
		this.perEvent=perEvent;
	}

	public   void navigateToCreatePlan() throws InterruptedException {
		businessHomePage.clickOutletsButton();
		outletList.clickArrow();
		outletDetails.clickEditBtnOfServiceCategory();
		outletDetails.clickAddMoreBtn();

	}

	public void handleVenueDetailsIfNotAdded() {
		if (outletDetails.getvenueDetailsNotAdddedText()) {
			ExtentReportManager.logFail("Test case is fail due toVenue Details not added");
		}
	}

	public void enterNameandUploadImage(String planName, String imgPath) throws InterruptedException, AWTException, IOException {
		createBUY_NOWServicePlan.enterPlanName(planName);
		createBUY_NOWServicePlan.clickUploadImgbtn();
		createBUY_NOWServicePlan.clickUploadImgRadiobtn();
		createBUY_NOWServicePlan.clickBrowsebtn();
		UtilityClass.uploadImage(imgPath);
		createBUY_NOWServicePlan.clickUploadbtn();
		ExtentReportManager.logInfo("Plan Image Uploaded...!");
	}


	public void configurePlanDetails(String minTimeInAdvanced, String maxTimeInAdvanced, String from, String to) throws InterruptedException, IOException {

		createBUY_NOWServicePlan.enterMinTimeInAdvanced(minTimeInAdvanced);
		createBUY_NOWServicePlan.enterMaxTimeInAdvanced(maxTimeInAdvanced);
		createBUY_NOWServicePlan.selectDays();
		createBUY_NOWServicePlan.selectServiceCategory();
		createBUY_NOWServicePlan.selectVenueType();
		createBUY_NOWServicePlan.selectServiceDescription();
		createBUY_NOWServicePlan.selectBookingType();
		createBUY_NOWServicePlan.selectFrom(from);
		createBUY_NOWServicePlan.selectTo(to);

	}

	public void enterGuestDetailsForPackagePlan(String maxGuestPerSlot, String guestPerPackage , String maxPackages, String maxGuestsAllowed,
			boolean selectTimeRange) throws InterruptedException {

		if (selectTimeRange) {
			pkgTime.clickTimeRangeRadiobtn();
			pkgTime.selectBookingDuration();

		}
		pkgSession.enterMaxGuestPerSlot(maxGuestPerSlot);
		pkgSession.enterGuestPerPackage(guestPerPackage);
		pkgSession.enterMaxPackagesAllowed(maxPackages);
		pkgSession.enterMaxGuestsAllowed(maxGuestsAllowed);
		ExtentReportManager.logInfo("Guest Details Are Entered...!");

	}


	public void enterGuestDetailsForEventPlan(String maxEventAllowed, String minGuestAllowed , String maxGuestPerSlot, String maxGuestsAllowed
			,boolean perEventplan   ) throws InterruptedException {

		if (perEventplan) 
		{
			perEvent.enterMaxEventAllowed(maxEventAllowed);
		}
		pkgSession.enterMaxGuestPerSlot(maxGuestPerSlot);
		perEvent.enterMinGuestAllowed(minGuestAllowed);
		pkgSession.enterMaxGuestsAllowed(maxGuestsAllowed);
		ExtentReportManager.logInfo("Guest Details Are Entered...!");

	}

	public void enterSaleItemDetails(String purchaseItemsName, String purchaseItemsprice, String purchaseItemSaleTax, String purchaseItemTimeFrame) throws InterruptedException {
		createBUY_NOWServicePlan.enterPurchaseItemsName(purchaseItemsName);
		createBUY_NOWServicePlan.enterPurchaseItemsprice(purchaseItemsprice);
		createBUY_NOWServicePlan.enterPurchaseItemSaleTax(purchaseItemSaleTax);
		
		// Check if the TimeFrame dropdown is enabled
		if (createBUY_NOWServicePlan.isPurchaseItemTimeFrameDropdownEnabled()) 
		{
			createBUY_NOWServicePlan.selectPurchaseItemTimeFrame(purchaseItemTimeFrame);
		
		} 
		else {
			ExtentReportManager.logInfo("Purchase Item TimeFrame dropdown is disabled, skipping selection...!");
		}

		ExtentReportManager.logInfo("Purchase Item Details Are Entered...!");
	}



	public void enterRefundDetails(String refundType, String refundAmount) throws InterruptedException, IOException {

		createBUY_NOWServicePlan.clickRefundYes();
		createBUY_NOWServicePlan.selectRefundType(refundType);

		// Check if the refund type is "partial"
		if ("Partial".equalsIgnoreCase(refundType)) {
			// Enter the refund amount
			createBUY_NOWServicePlan.enterRefundAmount(refundAmount);
			
		}
	}

	public void enterProcessingFees(String processingFeeName, String processingFeeAmount) throws InterruptedException, IOException {
		createBUY_NOWServicePlan.selectProcessingFeeCheckBox();
		createBUY_NOWServicePlan.selectFeePer();
		createBUY_NOWServicePlan.selectBasedOn();
		createBUY_NOWServicePlan.enterProcessingFeeName(processingFeeName);
		createBUY_NOWServicePlan.enterProcessingFeeAmount(processingFeeAmount);
		ExtentReportManager.logInfo("Entered Processing fees details...!");
	}

	public void finalizePlanCreation() throws InterruptedException {
		createBUY_NOWServicePlan.clickCreatebtn();
		createBUY_NOWServicePlan.clickYesBtn();
	}


}
