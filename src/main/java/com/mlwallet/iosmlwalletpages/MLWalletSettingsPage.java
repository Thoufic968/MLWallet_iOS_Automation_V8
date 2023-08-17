package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class MLWalletSettingsPage {
	
	public static By objProfileIcon=By.xpath("(//*[android.widget.ImageView]/ancestor::android.view.ViewGroup/descendant::android.view.ViewGroup/child::android.view.ViewGroup/following-sibling::android.view.ViewGroup)[1]");
	
	public static By objProfileIcon1=By.xpath("(//*[@name=''])[1]/*[3]/XCUIElementTypeOther");
	
	public static By objSettings = By.xpath("//*[@name='Settings']");
	
	public static By objProfileSettings = By.xpath("//*[@name='Profile Settings']");
	
	public static By objNotification = By.xpath("(//*[@name='Notification '])[2]");
	
	public static By objKeyword = By.xpath("//*[@resource-id='803512']");
	
	public static By objMLPinEditField = By.xpath("//*[@name='847305']/descendant::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeStaticText");
	
	public static By objDeleteAccount = By.xpath("(//*[@name='Delete Account'])[2]");
	
	public static By objDeleteAccountBtn = By.xpath("(//XCUIElementTypeOther[@name='Delete Account'])[2]");
	
	public static By objIWantToStay = By.xpath("//*[@name='I Want To Stay']");
	
	public static By objDeleteAccountBackBtn = By.xpath("//*[@name='']");
	
	public static By objWarningHeader = By.xpath("//*[contains(@name,'Before you go make sure you don’t leave anything behind!')]");
	
	public static By objWarningHeaderOptions = By.xpath("//*[@name='Before you go make sure you don’t leave anything behind!']/parent::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeStaticText");
	
	public static By objProceedBtn = By.xpath("//*[@label='Proceed']");
	
	public static By objDeleteAccountMLPin = By.xpath("//*[@label='Delete Account ML Pin']");
	
	public static By objEnterCurrentMLPinHeader = By.xpath("//*[@label='Enter Current ML PIN']");
	
	
	public static By objInvalidMLPinMsg = By.xpath("//*[@label='You have entered an invalid PIN. Please try again.']");
	
	
	public static By objAccountDetails=By.xpath("(//*[@name='Account Details '])[2]");
	
	public static By objAccountDetailsPage=By.xpath("//*[@name='Account Details']");
	
	public static By objBiometricsLogin = By.xpath("//*[@text='Biometrics Login']");
	
	public static By objBiometricsSwitch = By.xpath("//*[@class='android.widget.Switch']");
	
	public static By objNameOfAccountHolder=By.xpath("//*[@name='HL6BT0']");
	
	public static By objMailAddressOfAccountHolder=By.xpath("//*[@name='K2BR1F']");
	
	public static By objMobileNoOfAccountHolder=By.xpath("//*[@name='0LN68G']");
	
	public static By objChangeMLPinTab=By.xpath("(//*[@name='Change ML PIN '])[2]");
	
	public static By objChangeMLPinPage=By.xpath("//*[@name='Change ML PIN']");
	
	public static By objEnterCurrentMLPin = By.xpath("//*[@name='Enter Current ML PIN']");
	public static By objEnterMpinVal (String mPin)
	{
		return By.xpath("//*[@name='"+mPin+"']");
	}   
	
	//public static By objEnterMpinVal =By.xpath("//*[@name='847305']/descendant::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeStaticText");
	public static By objInvalaidMpinPopUp=By.xpath("//*[@label='You have entered an invalid PIN. Please try again.']");
	
	public static By objEnterNewMMLpinText=By.xpath("//*[@name='Enter New ML PIN']");
	
	public static By objReEnterNewMMLpinText=By.xpath("//*[@name='Re-Enter New ML PIN']");
	
	public static By objOKBtn=By.xpath("//*[@label='Ok']");
	
	public static By objMLPinChangedPopup=By.xpath("//*[@name='ML Pin Successfully Changed']");
	
	public static By objGotItBtn=By.xpath("//*[@label='Got It']");
	
	public static By objSettingsBackBtn = By.xpath("//*[@name='']");
	
	public static By objChangeMLPinBackBtn = By.xpath("//*[@name='']");
	
	public static By objAccountDetailsBackBtn = By.xpath("//*[@name='']");
	
	public static By objAccountRecovery=By.xpath("(//*[@name='Account Recovery '])[2]");
	
	public static By objTroubleSigningInBackBtn = By.xpath("//*[@name='']");
	
	public static By objBiometricsLoginBackBtn = By.xpath("//*[@text='Biometrics Login']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup");
	
	public static By objMlWalletSupportPage=By.xpath("//*[@name='ML Wallet Support']");
	
	public static By objFullNameField=By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeOther/child::XCUIElementTypeTextField)[1]");
	    
	public static By objRegisteredEmail=By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeOther/child::XCUIElementTypeTextField)[2]");
	    
	public static By objMobileNumber=By.xpath("(//*[XCUIElementTypeOther]/descendant::XCUIElementTypeOther/child::XCUIElementTypeTextField)[3]");
	
	public static By objNatureOfReqRadioBtn=By.xpath("//*[@name='Report a Lost/Stolen Device']");
	
	public static By objNextBtn=By.xpath("//*[@label='Next']");
	
	public static By objYourAnswer=By.xpath("(//*[@name='Lost/Stolen Mobile Number Required question'])[2]");
	
	public static By objNewMobNo=By.xpath("(//*[@name='New Mobile Number Required question'])[2]");
	
	public static By objFacebookMessangerName=By.xpath("(//*[@name='Facebook Messenger Name Required question'])[2]");
	
	public static By objSumbitBtn=By.xpath("//*[@label='Submit']");
	
	public static By objStolenPage=By.xpath("//*[@label='Reporting a Lost/Stolen Device']");
	
	public static By objReviewPage=By.xpath("(//*[@name='ML Wallet Support']/descendant::XCUIElementTypeStaticText)[2]");
	public static By objActivateBiometricsLogin = By.xpath("//*[@text='Activate Biometrics Login']");
	
	public static By objTroubleSigningIn = By.xpath("//*[@label='Trouble Signing In']");
	
	public static By objMLWalletSupport = By.xpath("//*[@label='ML Wallet Support']");
	//(//XCUIElementTypeOther[@name='Nature of your request Required question'])[2]/XCUIElementTypeOther/child::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeStaticText
	public static By objNatureOfRequests = By.xpath("(//XCUIElementTypeOther[@name='Nature of your request Required question'])[2]/XCUIElementTypeOther/child::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeStaticText");
	//(//XCUIElementTypeOther[@name='Nature of your request Required question'])[2]/XCUIElementTypeOther/child::XCUIElementTypeOther
	public static By objDeleteConfirmationPopUp = By.xpath("//*[@label='Are you sure you would like to delete your account?']");
	
	public static By objDeleteConfirmationPopUpTwo = By.xpath("//*[@label='If you would like to proceed, you will need to enter your ML Pin.']");
	
	
	
	public static By objCancelBtn = By.xpath("//*[@label='Cancel']");
	
	public static By objBiometrics = By.xpath("//*[@text='Biometrics']");
	public static By objNatureOfRequest = By.xpath("(//*[@class='android.widget.RadioGroup']/child::android.view.View/child::android.widget.TextView)[1]");


}