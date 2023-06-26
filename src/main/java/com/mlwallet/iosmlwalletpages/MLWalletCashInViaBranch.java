package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class MLWalletCashInViaBranch {
	
	public static By objCashInMenu=By.xpath("//*[@name='FF293B']");
	
	public static By objCashInPage=By.xpath("//*[@name='Cash In ' or @name='Cash In']");
	
	public static By objCashInOptionPage=By.xpath("//*[@name='XUTT8U']");
	
	public static By objBranchName=By.xpath("(//*[@name='TNKQDN'])[2]");
	
	public static By objBranchCashIn = By.xpath("//*[@name='8EW8RL']");
	
	public static By objUserName = By.xpath("//*[@name='9OVAK5']");

	public static By objUserNumber = By.xpath("//*[@name='8676Z5']");
	
	public static By objAmountTextField=By.xpath("//*[@name='0.00']/XCUIElementTypeTextField");
	
	public static By objNextButton=By.xpath("//*[@name='IV8HD6']");
	
	public static By objWarningPopup=By.xpath("(//*[@name='Are you sure you want to Cashin ₱ 100 pesos? Continue Cancel'])[3]");
	
	public static By objContinueButton=By.xpath("//*[@label='Continue']");
	
	public static By objPopUpContinueButton=By.xpath("//*[XCUIElementTypeOther]/child::XCUIElementTypeOther/descendant::XCUIElementTypeOther[@name='Continue']");
	
	public static By objkptnId=By.xpath("//*[@resource-id='transaction-code']");
	
	public static By objCrossBtn=By.xpath("//*[@name='']");
	
	public static By objCancelTransactionBtn=By.xpath("//*[@label='Cancel Transaction']");
	
	public static By objPopUpCancelTransactionBtn=By.xpath("(//*[@name='Cancel Transaction Go Back']/descendant::XCUIElementTypeOther)[2]");
	
	public static By objCancelTransactionPopup=By.xpath("//*[@name='Would you like cancel Transaction?']");
	
	public static By objCancelBtn1=By.xpath("//*[@text='Cancel Transaction']");
	
	public static By objTransactionCancelSuccessfulMsg=By.xpath("//*[@resource-id='badge-text']");

	public static By objInvalidAmountMsg = By.xpath("//*[@name='The amount should not be less than 1']");
	
	public static By objDecimalAmountErrMsg = By.xpath("//*[@name='The amount must be limited to 2 decimal places']");
	
	public static By objAmountRErrMsg = By.xpath("//*[@name='Amount is required']");

	public static By objBankMaxLimitTxt = By.xpath("//*[@name='The maximum Branch Cash-in per transaction set for your verification level is P50,000.00. Please try again.']");

	public static By objBankMaxLimitTxt1 = By.xpath("//*[@name='The maximum Branch Cash-in per transaction set for your verification level is P20,000.00. Please try again.']");

	//Device Location
	public static By objLocationPopup=By.xpath("//*[@text='Allow ML Wallet to access this device’s location?']");
	public static By objAllowButton=By.xpath("//*[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']");

	public static By objCashInBranchBackBtn = By.xpath("//*[@name='']");
	
	public static By objBackToHomeBtn = By.xpath("//*[@name='68VSNB']");
	
	public static By objCashInToBranch = By.xpath("//*[@name='GLNT33']");
	
	public static By objQRCode = By.xpath("//*[@name='66HVSU']");
	
	public static By objPHP = By.xpath("//*[@name='08HQ6G']");
	
	public static By objCreatedDate = By.xpath("//*[@name='DR09AX']");
	
	public static By objTransactionNo = By.xpath("//*[@name='0QMRD2']");
	
	public static By objStatus = By.xpath("//*[@name='S2JHWK']");
	
	public static By objGoBackBtn = By.xpath("//*[XCUIElementTypeOther]/child::XCUIElementTypeOther/descendant::XCUIElementTypeOther[@name='Go Back']");
	
	public static By objMaxLimitTxt = By.xpath("//*[@name='Branch Cash-in not allowed. Please upgrade to a higher verification level to add more balance.']");
	
	public static By objUpgradeNowBtn = By.xpath("//*[@name='Upgrade Now']");
	
	public static By objTwentyFourHrsNoteMsg = By.xpath("//*[@name='UGJ6H5']");
	
	public static By objAppInfo = By.xpath("//*[@text='App info']");
	
	public static By objCashInConfirmationPopup = By.xpath("(//*[@name='Are you sure you want to Cashin ₱ 100 pesos? Continue Cancel'])[3]");

	public static By objInvalidPINMsg = By.xpath("//*[@name='You have entered an invalid PIN. Please try again.']");

	

}