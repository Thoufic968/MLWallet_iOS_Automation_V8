package com.mlwallet.iosmlwalletpages;

import org.openqa.selenium.By;

public class MLWalletLoginPage {
	
	public static By objMobileNumberTextField=By.xpath("//XCUIElementTypeOther[@name='9XX XXX XXXX']/XCUIElementTypeTextField");
	
	public static By objMobileNumberTextField1=By.xpath( "//XCUIElementTypeOther[@name=\"+63\"]/XCUIElementTypeOther/XCUIElementTypeTextField");

	public static By objLoginBtn=By.xpath("//*[@name='208465' or @label='Login']");
	
	public static By objOtpTextField=By.xpath("//*[@name='94NMBE-0 94NMBE-1 94NMBE-2 94NMBE-3 94NMBE-4 94NMBE-5']/descendant::XCUIElementTypeOther/child::XCUIElementTypeTextField");
	
	public static By objResendCode = By.xpath("(//*[@name='Did not receive OTP?']/following::XCUIElementTypeStaticText)[2]");

	public static By objAvailableBalance = By.xpath("//*[@name='UTJ9TN']");

	public static By objInvalidMobNumberTxt = By.xpath("//*[@name='Mobile number is invalid']");

	public static By objOneTimePin = By.xpath("//*[@name='textInput' or @label='One Time Pin']"); 
	
	public static By objOTP = By.xpath("((//XCUIElementTypeStaticText[@name='One Time Pin'])[3]/ancestor::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeStaticText)[2]");
	
	public static By objSeconds = By.xpath("((//XCUIElementTypeStaticText[@name='One Time Pin'])[3]/ancestor::XCUIElementTypeOther/descendant::XCUIElementTypeOther/child::XCUIElementTypeStaticText)[3]");
	
	public static By objOneTimePin1 = By.xpath("(//*[@name='textInput'])[1]");
	
	public static By objMobileNoReqErrorMsg = By.xpath("//*[@name='Mobile number is required']");

	public static By objMLPin = By.xpath("//*[@name='ML Pin']");

	public static By objMLPinEditField(int i) {
		return  By.xpath("((//*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]/*/*/*[@class='android.view.ViewGroup' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.ScrollView']]])[1]/*[@class='android.view.ViewGroup'])["+i+"]");
	}

	public static By objOneBtn = By.xpath("//*[@name='1']");
   
	public static By objLocationPopup=By.xpath("//XCUIElementTypeAlert[@name=\"Allow “ML Wallet” to use your location?\"]");
	
	public static By objChangeNumber=By.xpath("//XCUIElementTypeStaticText[@name=\"724506\"]");
	
	public static By objTroubleSigningIn=By.xpath("//XCUIElementTypeOther[@name=\"Trouble signing in?\"]");
	
	public static By objHaveAnAccountMsg=By.xpath("//XCUIElementTypeStaticText[@name=\"Don’t have an account?\"]");
	
	public static By objCreateOneBtn=By.xpath("//XCUIElementTypeOther[@name=\" Create one\"]");
	
	public static By objBranchLocator=By.xpath("//*[@label='Branch Locator']");
	
	public static By objAppVersion=By.xpath("//XCUIElementTypeStaticText[@name=\"346187\"]");
	
	public static By objTroubleSigningPage=By.xpath("//XCUIElementTypeStaticText[@name=\"Trouble Signing In\"]");
	
	public static By objMLWalletSupport=By.xpath("//XCUIElementTypeStaticText[@name=\"ML Wallet Support\"]");
	
	public static By objRegistrationNumber=By.xpath("//XCUIElementTypeStaticText[@name=\"Registration Number\"]");
	
	public static By objOk=By.xpath("//*[@name='OK']");
	
	public static By objgmail=By.xpath("(//XCUIElementTypeStaticText[@name=\"Search or type URL\"])[1]");
	
	public static By objaddress=By.xpath("//XCUIElementTypeTextField[@name=\"Address\"]\n"
			+ "");
	
	public static By objGoogle=By.xpath("//XCUIElementTypeStaticText[@name=\"Google\"]");
	
	public static By objGoogleClear=By.xpath("//XCUIElementTypeStaticText[@name=\"google.com\"]");
	
	public static By objCollaberaBuild=By.xpath("//XCUIElementTypeStaticText[@name=\"collabera build\"]\n"
			+ "");
	
	public static By objInstallBtn=By.xpath("//XCUIElementTypeButton[@name=\"INSTALL\"]");
	
	public static By objInstallPopUp=By.xpath("//XCUIElementTypeButton[@name=\"Install\"]");
	
	public static By objGoogleBackBtn=By.xpath("//XCUIElementTypeButton[@name=\"kTabGridEditButtonIdentifier\"]");
	
	public static By objAppVersionChangeNumber = By.xpath("//*[@name='346187' or @name='216094']");
	
	//New OTP Handle
	
	public static By objOtpContineBtn = By.xpath("(//*[@label='CONTINUE'])[3]");
	
	public static By objCancelBtn = By.xpath("(//*[@name='CANCEL'])[3]");
	
	public static By objCameraPopup = By.xpath("//XCUIElementTypeStaticText[@name='“ML Wallet” Would Like to Access the Camera']");
	
	public static By objCamPopUpOKBtn = By.xpath("//XCUIElementTypeButton[@name='OK']");
	
	public static By objDntAllowBtn = By.xpath("//XCUIElementTypeButton[@name='Don’t Allow']");
	
	public static By objCurrentLocPopUp = By.xpath("//XCUIElementTypeStaticText[@name='“https://mlhuillier.com” Would Like To Use Your Current Location']");

}
