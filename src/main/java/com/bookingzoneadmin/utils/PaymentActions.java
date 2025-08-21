package com.bookingzoneadmin.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.bookingzoneadmin.pages.OrderSummaryPage;
import com.bookingzoneadmin.pages.StepperPaymentPage;
import com.bookingzoneadmin.pages.StepperSummaryPage;
import com.bookingzoneadmin.pages.YopMailPage;

public class PaymentActions {
	static Logger logger = (Logger) LogManager.getLogger(PaymentActions.class);

	private StepperPaymentPage stepperPaymentPage;
	private StepperSummaryPage stepperSummaryPage;
	private YopMailPage yopmail;
	OrderSummaryPage orderSummaryPage;

	public PaymentActions(StepperPaymentPage stepperPaymentPage, StepperSummaryPage stepperSummaryPage, YopMailPage yopmail,
			OrderSummaryPage orderSummaryPage) {
		this.stepperPaymentPage = stepperPaymentPage;
		this.stepperSummaryPage = stepperSummaryPage;
		this.yopmail=yopmail;
		this.orderSummaryPage=orderSummaryPage;
	}

	public void completePayment(WebDriver driver, String paymentMethod, String cardNumber, String expiryDate, String cvc) throws InterruptedException, IOException, TimeoutException {
		switch (paymentMethod.toLowerCase()) {
		case "mppg":
			handleCardPayment(driver, cardNumber, expiryDate, cvc);
			break;
		case "cash":
			handleCash_OtherPayment(paymentMethod);
			break;
		case "other":
			handleCash_OtherPayment(paymentMethod);
			break;

		case "email":
			handleEmailPayment(driver, cardNumber, expiryDate, cvc);
			break;

		default:
			ExtentReportManager.getTest().fail("Unsupported payment method: " + paymentMethod);
			throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
		}
	}


	private void handleCardPayment(WebDriver driver, String cardNumber, String expiryDate, String cvc) throws InterruptedException, IOException, TimeoutException {
		// Select payment method
		stepperPaymentPage.selectPaymentMethod(UtilityClass.getDataFromEs(1, "Card Pay", "Create_Booking"));
		stepperPaymentPage.clickTipCrossButton();

		// Check if the "Pay via Saved Card" option is available
		if (stepperPaymentPage.isPayViaSavedCardOptionAvailable())
		{

			stepperPaymentPage.clickPayViaSavedCard();
		} 
		else 
		{
			System.out.println("Else Condition");

			// Switch to iframe for entering card details
			driver.switchTo().frame("__teConnectSecureFrame");
			// Enter card details
			stepperPaymentPage.enterCardNumber(cardNumber);
			stepperPaymentPage.enterExpiryDate(expiryDate);
			stepperPaymentPage.enterCVC(cvc);

			// Switch back to the default content
			driver.switchTo().defaultContent();
		}


		// Click the pay button
		stepperPaymentPage.clickPayButton();
		Thread.sleep(15000);
	}

	public void handleCash_OtherPayment(String paymentMethod) throws InterruptedException, IOException {
		String paymentMethodData = "";

		// Conditional logic to fetch data based on payment method
		if (paymentMethod.equalsIgnoreCase("cash")) {
			paymentMethodData = UtilityClass.getDataFromEs(1, "Cash Pay", "Create_Booking");
		} else if (paymentMethod.equalsIgnoreCase("other")) {
			paymentMethodData = UtilityClass.getDataFromEs(1, "Other Pay", "Create_Booking");
		}

		// Select payment method based on the retrieved data
		stepperPaymentPage.selectPaymentMethod(paymentMethodData);

		// Perform the remaining common actions
		stepperPaymentPage.clickTipCrossButton();
		stepperPaymentPage.clickReceivedCheckBox();
		stepperPaymentPage.clickConfirmButton();
	}

	public void handleEmailPayment(WebDriver driver,String cardNumber, String expiryDate, String cvc) throws InterruptedException, IOException 
	{
		// Select payment method
		stepperPaymentPage.selectPaymentMethod(UtilityClass.getDataFromEs(1, "Email Pay", "Create_Booking"));
		stepperPaymentPage.clickTipCrossButton();
		stepperPaymentPage.clickSendLink();
		stepperPaymentPage.verifyEmailLinkSendMessage();
		yopmail.navigateToYopMail();
		yopmail.enterLoginEmail(UtilityClass.getDataFromEs(1, "Email", "Create_Booking"));
		yopmail.clickMailArrow();

		// To handle iframe element
		driver.switchTo().frame("ifmail");
		yopmail.clickLinkInMail();
		
		
		orderSummaryPage.verifyPayableAmountOnEmailSendedLink(2);
		
		// Switch to iframe for entering card details
		driver.switchTo().frame("__teConnectSecureFrame");
		// Enter card details
		stepperPaymentPage.enterCardNumber(cardNumber);
		stepperPaymentPage.enterExpiryDate(expiryDate);
		stepperPaymentPage.enterCVC(cvc);

		// Switch back to the default content
		driver.switchTo().defaultContent();
		orderSummaryPage.clickPayButton();
		Thread.sleep(5000);
	
	}

	

	public void verifyTotal_SubTotalAmount() throws InterruptedException {
		stepperPaymentPage.verifySubtotal();
		stepperPaymentPage.verifyTotalAmount();
	}


	public String getBookingId() 
	{
		String BookingId=stepperSummaryPage.getBookingId();
		System.out.println("Booking Id is:-" + BookingId);
		return BookingId;
	}

	

}
