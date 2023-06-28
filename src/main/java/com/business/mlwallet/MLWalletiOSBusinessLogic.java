package com.business.mlwallet;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import java.awt.Robot;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;

import com.driverInstance.DriverInstance;
import com.driverInstance.Drivertools;
import com.emailReport.GmailInbox;
import com.extent.ExtentReporter;
import com.mlwallet.iosmlwalletpages.MLWalletLoginPage;
import com.mlwallet.iosmlwalletpages.MLWalletPayBillsPage;
import com.mlwallet.iosmlwalletpages.MLWalletRegistration;
import com.mlwallet.iosmlwalletpages.MLWalletSettingsPage;
import com.mlwallet.iosmlwalletpages.MLWalletShopItemsPage;
import com.mlwallet.iosmlwalletpages.MLWalletTransactionHistoryPage;
import com.mlwallet.iosmlwalletpages.MLWalletTroubleSigningInPage;
import com.mlwallet.ios_scripts.MLWalletBuyEload;
import com.mlwallet.ios_scripts.MLWalletLoginScripts;
import com.mlwallet.ios_scripts.MLWalletSendTransferToAnyMLBranchScripts;
import com.mlwallet.ios_scripts.MLWalletSendTransferToMLWalletUserScripts;
import com.mlwallet.iosmlwalletpages.MLWalletBranchLocatorPage;
import com.mlwallet.iosmlwalletpages.MLWalletCashInBank;
import com.mlwallet.iosmlwalletpages.MLWalletCashInViaBranch;
import com.mlwallet.iosmlwalletpages.MLWalletCashOutPage;
import com.mlwallet.iosmlwalletpages.MLWalletEloadPage;
import com.mlwallet.iosmlwalletpages.MLWalletHomePage;
import com.mlwallet.iosmlwalletpages.MLWalletLogOutPage;
import com.mlwallet.iosmlwalletpages.SendTransferPage;

import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import jline.internal.Log;


@SuppressWarnings("static-access")
public class MLWalletiOSBusinessLogic extends Utilities {

	public Date date;

	Drivertools tools;

	public String availableAmt;
	public String amtText;
	public String youAreAboutToPay;
	public String availBalance;
	public String totalSavingBalance;
	public String enterAmount;

	@SuppressWarnings("unused")
	private static final String Expectedresult = null;

	public static String iosModel;
	public static String iosVersion;

	public MLWalletiOSBusinessLogic(String Application) throws InterruptedException {
		new DriverInstance(Application);
		init();
	

	}

	private int timeout;

	/** Retry Count */
	private int retryCount;
	ExtentReporter extent = new ExtentReporter();

	/** The Constant logger. */
	// final static Logger logger = Logger.getLogger("rootLogger");
	static LoggingUtils logger = new LoggingUtils();
	                                                              //  \\properties\\testdata.properties
	public static PropertyFileReader prop = new PropertyFileReader("//Users//admin//Desktop/MLWallet//properties//testdata.properties");
	
	@SuppressWarnings("rawtypes")
	public TouchAction touchAction;

	/** The iOS driver. */
	public IOSDriver<WebElement> iOSDriver;

	@Override
	public int getTimeout() {
		return timeout;
	}

	Robot robo;

	String pUserType = getParameterFromXML("userType");

	/** Array of App */
	static ArrayList<String> AppMyProfile = new ArrayList<String>();
	static HashSet<String> contentsInWatchList = new HashSet<String>();
	static HashSet<String> contentsInReminders = new HashSet<String>();
	static ArrayList<String> AppSubscription = new ArrayList<String>();
	static ArrayList<String> AppTransaction = new ArrayList<String>();

	@Override
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public int getRetryCount() {
		return retryCount;
	}

	@Override
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	GmailInbox gmail = new GmailInbox();

	String SVODEpisode = getParameterFromXML("SVODEpisode");

	String profileID = null;
	String profileText = null;

	String ProfileFirstName = null;
	String ProfileLastName = null;
	String DefinedHandleName = null;

	String NotificationUsername = null;
	String NotificationPassword;

	String fun = null;

	String effectName = null;
	int CommentTitleCount1;

	public void init() {
		if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName()
				.equals("iOSWeb")) {
			System.out.println("Platform : iOS Web");
			iosModel = (String) getDriver().getCapabilities().getCapability("device.model");
			System.out.println(iosModel + "--- Model Name ");
			iosVersion = (String) getDriver().getCapabilities().getCapability("device.version");
			System.out.println(iosVersion + "--- Version ");
		} else if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName()
				.equals("MLWallet_iOS")) {
			iosModel = (String) getDriver().getCapabilities().getCapability("device.model");
			System.out.println(iosModel + "--- Model Name ");
			iosVersion = (String) getDriver().getCapabilities().getCapability("device.version");
			System.out.println(iosVersion + "--- Version ");
		} else if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getSuite().getName()
				.equals("Web")) {
			System.out.println("Platform : Web");
		}
		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
	}

	public void devicedetails() {
		HeaderChildNode("Device Details");

		iosModel = (String) getDriver().getCapabilities().getCapability("device.model");

		iosVersion = (String) getDriver().getCapabilities().getCapability("device.version");

		extent.extentLogger("", "Model Name :" + iosModel);
		extent.extentLogger("", "Version :" + iosVersion);
	}

	public void tearDown() {
		getDriver().quit();
	}

	/**
	 * @handle Popups method
	 * @param userType
	 * @throws Exception
	 */
	
	//================================= Change Number Page ==========================================//
	public void changeNumberPage() throws Exception {
		try {

			if (verifyElementPresent(MLWalletLoginPage.objChangeNumber,getTextVal(MLWalletLoginPage.objChangeNumber, "Page"))) {
				waitTime(2000);
				click(MLWalletLoginPage.objChangeNumber, "Change Number Field");
			}
		} catch (Exception e) {
           logger.info("Change number page is not displayed");
           extentLogger("Change Number Page", "Change number page is not displayed");
		}
	}
	
	//================================== Enter OTP ===================================================//
	
/*	public void enterOTP(String OTP) throws Exception {
		explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 20);
		waitTime(2000);
		verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"));
		verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
		waitTime(2000);
		type(MLWalletLoginPage.objOtpTextField, OTP, "OTP Text Field");
		waitTime(3000);
	}*/
	
	public void enterOTP(String OTP) throws Exception {
		waitTime(10000);
		if(verifyElementPresent(MLWalletLoginPage.objOtpContineBtn,  "Continue Button Pop Up"))
		{
			click(MLWalletLoginPage.objOtpContineBtn, "Clicked On OTP Continue Button");
		}else if(verifyElementPresent(MLWalletLoginPage.objOneTimePin, "One Time Pin Page"))
		{
			explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 20);
			waitTime(2000);
			verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"));
			verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
			waitTime(2000);
			type(MLWalletLoginPage.objOtpTextField, OTP, "OTP Text Field");
			waitTime(3000);
		}else
		{
			handleMpin("1111", "Entered");
			waitTime(8000);
		}
		
	}
		
	public void enterOTP1(String OTP) throws Exception {
		explicitWaitVisible(MLWalletLoginPage.objOneTimePin1, 5);
		waitTime(2000);
		verifyElementPresent(MLWalletLoginPage.objOneTimePin1, getTextVal(MLWalletLoginPage.objOneTimePin1, "Page"));
		verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
		waitTime(2000);
		type(MLWalletLoginPage.objOtpTextField, OTP, "OTP Text Field");
		waitTime(3000);
	}
	
	
	//================================ LOG IN==============================================//
		public void mlWalletLogin(String sTier) throws Exception {
			HeaderChildNode("MLWallet_Login_Scenario");
			explicitWaitVisible(MLWalletLoginPage.objMobileNumberTextField, 10);
//			if(verifyElementPresent(MLWalletLoginPage.objMobileNumberTextField, getTextVal(MLWalletLoginPage.objMobileNumberTextField, "Page")));
//			{
//				click(MLWalletLoginPage.objMobileNumberTextField, "Mobile Number Text Field");
//			}
			type(MLWalletLoginPage.objMobileNumberTextField, sTier, "Mobile Number Text Field");
			click(MLWalletLoginPage.objLoginBtn, "Login Button");
			enterOTP("111111");
			explicitWaitVisible(MLWalletLoginPage.objAvailableBalance, 10);
			if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
				logger.info("Application Logged In Successfully");
			} else {
				logger.info("Application not get Logged In Successfully");
			}
		}
		
		//===================================LOG OUT=============================================================//
		public void mlWalletLogout() throws Exception {
			if (verifyElementPresent(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu")) {
				click(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
				waitTime(3000);
				click(MLWalletLogOutPage.objLogoutBtn, "Logout Button");
				waitTime(4000);
				click(MLWalletLogOutPage.objPopUpLogoutBtn,  "Logout Button");
				waitTime(3000);
				click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
			}
			waitTime(3000);
			if (verifyElementPresent(MLWalletLoginPage.objLoginBtn, getTextVal(MLWalletLoginPage.objLoginBtn, "Link"))) {
				logger.info("Application Logged Out Successfully");
			} else {
				logger.info("Application not get Logged Out Successfully");
			}

		}
		
		
		public void mlWalletLogout1() throws Exception {
			if (verifyElementPresent(MLWalletLogOutPage.objHamburgerMenu1, "Hamburger Menu")) {
				click(MLWalletLogOutPage.objHamburgerMenu1, "Hamburger Menu");
				waitTime(2000);
				click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
				waitTime(4000);
				click(MLWalletLogOutPage.objPopUpLogoutBtn, getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn, "Button"));
				waitTime(3000);
				click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
			}
			waitTime(3000);
			if (verifyElementPresent(MLWalletLoginPage.objLoginBtn, getTextVal(MLWalletLoginPage.objLoginBtn, "Link"))) {
				logger.info("Application Logged Out Successfully");
			} else {
				logger.info("Application not get Logged Out Successfully");
			}

		}
	//================================== Back Arrow Button ===================================================//

		public void backArrowBtn(int nNumber) throws Exception {
			for (int i = 1; i <= nNumber; i++) {
				waitTime(3000);
				click(SendTransferPage.objBackArrow, "Back Arrow Button");
				waitTime(2000);
			}
		}

		public void backArrowBtn1(int nNumber) throws Exception {
			for (int i = 1; i <= nNumber; i++) {
				click(SendTransferPage.objBackArrow1, "Back Arrow Button");
				waitTime(2000);
			}
		}

		//================================== Enter ML Pin =============================================================//
		
		public void enterMLPin () throws Exception {
			explicitWaitVisible(MLWalletLoginPage.objMLPin,5);
			verifyElementPresent(MLWalletLoginPage.objMLPin,getTextVal(MLWalletLoginPage.objMLPin,"Page"));
			Thread.sleep(3000);
			click(MLWalletLoginPage.objOneBtn,getTextVal(MLWalletLoginPage.objOneBtn,"Button"));
			click(MLWalletLoginPage.objOneBtn,getTextVal(MLWalletLoginPage.objOneBtn,"Button"));
			click(MLWalletLoginPage.objOneBtn,getTextVal(MLWalletLoginPage.objOneBtn,"Button"));
			click(MLWalletLoginPage.objOneBtn,getTextVal(MLWalletLoginPage.objOneBtn,"Button"));
		}

		//================================== Enable Location Pop up ===================================================//
		
		public void enableLocation_PopUp() throws Exception {
			waitTime(5000);
			String loc = getText(MLWalletLoginPage.objLocationPopup);
			if (loc.contains("Allow")) {
				logger.info(loc + " Pop Up is Displayed");
				extentLoggerPass("pop up", loc + " Pop Up is Displayed");
				click(MLWalletCashOutPage.objLocationPermission1, "Allow Button");
			} else {
				logger.info(" Location Pop Up is not Displayed");
				extentLoggerPass("pop up", "Location Pop Up is not Displayed");
			}
		}
		
		
		//=================================================== LOGIN SCENARIOS======================================//
		
		
		public void gmail() throws Exception
		{
			HeaderChildNode("Gmail");
			 Thread.sleep(4000);
			 click(MLWalletLoginPage.objgmail, "Gmail Click");
			// Thread.sleep(2000);
			 //clearField(MLWalletLoginPage.objGoogleClear, "Gmail Cleared");
			 type(MLWalletLoginPage.objaddress, MLWalletLoginScripts.link, "search bar");
			 Thread.sleep(5000);
			 WebElement ele=findElement(MLWalletLoginPage.objaddress);
			 ele.sendKeys(Keys.ENTER);
			 
			 Thread.sleep(10000);
			 click(MLWalletLoginPage.objGoogle, "Google");
			 Thread.sleep(6000);
			 click(MLWalletLoginPage.objCollaberaBuild, "Collabera Build");
			 Thread.sleep(8000);
			 click(MLWalletLoginPage.objInstallBtn, "Install Button");
			 Thread.sleep(3000);
			 click(MLWalletLoginPage.objInstallPopUp, "Install Popup");
			 
			 click(MLWalletLoginPage.objGoogleBackBtn, "Back Button");
			 click(MLWalletLoginPage.objGoogleBackBtn, "Back Button");
			 click(MLWalletLoginPage.objGoogleBackBtn, "Back Button");
			 click(MLWalletLoginPage.objGoogleBackBtn, "Back Button");
			 
			 
			 Thread.sleep(20000);
			 getDriver().activateApp("com.mlhuillier.mlwallet");
			// getDriver().getKeyboard().sendKeys(Keys.ENTER);
//			 WebElement ele=getDriver().findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"Search or type URL\"])[2]"));
//			 ele.click();
//			 ele.sendKeys(MLWalletLoginScripts.link);
//			 Thread.sleep(2000);
//			 ele.sendKeys(Keys.ENTER);
//
////			String enter="adb shell input keyevent KEYCODE_ENTER";
////			 Runtime.getRuntime().exec(enter);
//
//			 getDriver().findElement(By.xpath("//*[@name='Google']")).click();
//			Thread.sleep(3000);
//			getDriver().findElement(By.xpath("//*[@name='collabera ml']")).click();
//			getDriver().findElement(By.xpath("//*[@name='collabera ml']")).click();
//
//			Thread.sleep(7000);
//			getDriver().findElement(By.xpath("//*[@name='DOWNLOAD']")).click();

		}

		
		
		public void LogInScenarioWithValidMobNumber_Lgn_TC_01() throws Exception {
			HeaderChildNode("Login Scenarios With Valid Mobile Number");
			changeNumberPage();
			mlWalletLogin(prop.getproperty("ios_BranchVerified"));
			if(verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
				logger.info("Lgn_TC_01, Logged In Successfully and redirected to Dashboard");
				extentLoggerPass("Lgn_TC_01", "Lgn_TC_01, Logged In Successfully and redirected to Dashboard");
				mlWalletLogout();
			//	changeNumberPage();
				System.out.println("-----------------------------------------------------------");

			}
		}

		public void LogInScenarioWithInvalidMobNumber_Lgn_TC_02() throws Exception {
			HeaderChildNode("Login Scenarios With Invalid Mobile Number");
			waitTime(3000);
			click(MLWalletLoginPage.objMobileNumberTextField1, "Mobile Number Text Field");
			clearField(MLWalletLoginPage.objMobileNumberTextField1, "Mobile Number Text Field");
		    type(MLWalletLoginPage.objMobileNumberTextField, prop.getproperty("Invalid_MobileNumber"), "Mobile Number Text Field");
			click(MLWalletLoginPage.objLoginBtn, "Login Button");
			if (verifyElementPresent(MLWalletLoginPage.objInvalidMobNumberTxt, getTextVal(MLWalletLoginPage.objInvalidMobNumberTxt, "Error Message"))) {
				logger.info("Lgn_TC_02, Mobile number is Invalid Error Message is Displayed");
				extentLoggerPass("Lgn_TC_02", "Lgn_TC_02, Mobile number is Invalid Error Message is Displayed");			
				System.out.println("-----------------------------------------------------------");
			}
		}

		public void LogInScenarioWithValidOTP_Lgn_TC_03() throws Exception {
			HeaderChildNode("Login Scenarios With Valid OTP");
			click(MLWalletLoginPage.objMobileNumberTextField1, "Mobile Number Text Field");
			clearField(MLWalletLoginPage.objMobileNumberTextField1, "Mobile Number Text Field");
			mlWalletLogin(prop.getproperty("Branch_Verified"));
			if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
				logger.info("Lgn_TC_03, Logged In Successfully and redirected to Dashboard");
				extentLoggerPass("Lgn_TC_03", "Lgn_TC_03, Logged In Successfully and redirected to Dashboard");
				System.out.println("-----------------------------------------------------------");
			}
			mlWalletLogout();		
		}

		public void LogInScenarioWithInValidOTP() throws Exception {
			HeaderChildNode("Login Scenarios With InValid OTP");
			changeNumberPage();
			explicitWaitVisibility(MLWalletLoginPage.objMobileNumberTextField, 10);
			click(MLWalletLoginPage.objMobileNumberTextField, "Mobile Number Text Field");
			type(MLWalletLoginPage.objMobileNumberTextField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
			click(MLWalletLoginPage.objLoginBtn, "Login Button");
			type(MLWalletLoginPage.objOtpTextField, prop.getproperty("InValid_OTP"), "OTP Text Field");
		}
		
		
		//========================================== Phase 2==========================================//

		public void appLaunch_Lgn_TC_05() throws Exception {
			HeaderChildNode("App Launch");
			if(verifyElementPresent(MLWalletLoginPage.objLoginBtn,"Login Button")){
				logger.info("Lgn_TC_05, Application Launched Successfully");
				extentLoggerPass("Lgn_TC_05", "Lgn_TC_05, Application Launched Successfully");
				System.out.println("-----------------------------------------------------------");
			}
		}

		public void loginPageUIValidation_Lgn_TC_06() throws Exception {
			HeaderChildNode("Login Page UI Validation");
			if(verifyElementPresent(MLWalletLoginPage.objLoginBtn,"Login Button")){
				verifyElementPresent(MLWalletLoginPage.objMobileNumberTextField,  "Mobile Number Text Field");
				verifyElementPresent(MLWalletLoginPage.objTroubleSigningIn,getTextVal(MLWalletLoginPage.objTroubleSigningIn,"Button"));
				verifyElementPresent(MLWalletLoginPage.objHaveAnAccountMsg,getTextVal(MLWalletLoginPage.objHaveAnAccountMsg,"Message"));
				verifyElementPresent(MLWalletLoginPage.objCreateOneBtn,getTextVal(MLWalletLoginPage.objCreateOneBtn,"Button"));
				verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Button"));
				verifyElementPresent(MLWalletLoginPage.objAppVersion,getTextVal(MLWalletLoginPage.objAppVersion,"App Version"));
				logger.info("Lgn_TC_06, Login Page UI Validated");
				extentLoggerPass("Lgn_TC_06", "Lgn_TC_06,  Login Page UI Validated");
				System.out.println("-----------------------------------------------------------");
			}
		}

		public void loginTroubleSigningIn_Lgn_TC_07() throws Exception {
			HeaderChildNode("LogIn Trouble Signing In Validation");
			waitTime(2000);
			if(verifyElementPresentAndClick(MLWalletLoginPage.objTroubleSigningIn,getTextVal(MLWalletLoginPage.objTroubleSigningIn,"Button"))){
				waitTime(2000);
				verifyElementPresent(MLWalletLoginPage.objTroubleSigningPage,getTextVal(MLWalletLoginPage.objTroubleSigningPage,"Page"));
				verifyElementPresent(MLWalletLoginPage.objMLWalletSupport,getTextVal(MLWalletLoginPage.objMLWalletSupport,"Header"));
				logger.info("Lgn_TC_07, Navigated to Trouble Signing In Page");
				extentLoggerPass("Lgn_TC_07", "Lgn_TC_07,  Navigated to Trouble Signing In Page");
				System.out.println("-----------------------------------------------------------");
			}
			backArrowBtn(1);
		} 

		public void loginCreateOne_Lgn_TC_08() throws Exception {
			HeaderChildNode("LogIn Create One");
			waitTime(2000);
			
			if(verifyElementPresentAndClick(MLWalletLoginPage.objCreateOneBtn,getTextVal(MLWalletLoginPage.objCreateOneBtn,"Button"))){
				explicitWaitVisibility(MLWalletLoginPage.objRegistrationNumber, 10);
				verifyElementPresent(MLWalletLoginPage.objRegistrationNumber,getTextVal(MLWalletLoginPage.objRegistrationNumber,"Page"));
				logger.info("Lgn_TC_08, Navigated to Create One Page");
				extentLoggerPass("Lgn_TC_08", "Lgn_TC_08, Navigated to Create One Page");
				System.out.println("-----------------------------------------------------------");
			}
			backArrowBtn(1);
		}

		public void loginBranchLocator_Lgn_TC_09() throws Exception {
			HeaderChildNode("LogIn Branch Locator");
			waitTime(2000);	
			if(verifyElementPresentAndClick(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Button"))){
				waitTime(3000);
				verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Page"));
				logger.info("Lgn_TC_09, Navigated to Branch Locator Page");
				extentLoggerPass("Lgn_TC_09", "Lgn_TC_09, Navigated to Branch Locator Page");
				System.out.println("-----------------------------------------------------------");
			}
			backArrowBtn(1);
		}

		public void loginOTPPageUIValidation_Lgn_TC_10() throws Exception {
			HeaderChildNode("LogIn OTP Page UI Validation");			
			waitTime(2000);
			clearField();			
			type(MLWalletLoginPage.objMobileNumberTextField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
			click(MLWalletLoginPage.objLoginBtn, "Login Button");
			waitTime(3000);
			if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
				verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
				//verifyElementPresent(MLWalletLoginPage.objResendCode, getTextVal(MLWalletCashOutPage.objResendCode, "Seconds"));
				//click(MLWalletLoginPage.objOk, "OK Button");
				logger.info("Lgn_TC_10, LogIn OTP Page UI Validated");
				extentLoggerPass("Lgn_TC_10", "Lgn_TC_10, LogIn OTP Page UI Validated");
				System.out.println("-----------------------------------------------------------");
			}
			backArrowBtn(1);
		}
		
		
		public void loginWithExistingMobileNumber_Lgn_TC_17() throws Exception {
			HeaderChildNode("Login With Existing Mobile Number");
			waitTime(2000);
			changeNumberPage();
			clearField();	
			mlWalletLogin(prop.getproperty("Branch_Verified"));
			verifyElementPresent(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
			click(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
			waitTime(3000);
			click(MLWalletLogOutPage.objLogoutBtn, "Logout Button");
			waitTime(4000);
			click(MLWalletLogOutPage.objPopUpLogoutBtn,  "Logout Button");
			waitTime(3000);
			verifyElementPresent(MLWalletLogOutPage.objChangeNumber,"Mpin Page");
			handleMpin("1234", "Entered Mpin");
			explicitWaitVisible(MLWalletLoginPage.objAvailableBalance, 10);
			if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
				logger.info("Lgn_TC_17, Application Logged In Successfully");
				extentLoggerPass("Lgn_TC_17", "Lgn_TC_17, Application Logged In Successfully");
				System.out.println("-----------------------------------------------------------");
			} else {
				logger.info("Application not get Logged In Successfully");
			}
			getDriver().resetApp();
		}

		public void loginMPinPageUIValidation_Lgn_TC_18() throws Exception {
			HeaderChildNode("Login Mpin Page UI Validation");
			waitTime(2000);
			changeNumberPage();
			clearField();	
			mlWalletLogin(prop.getproperty("Branch_Verified"));
			verifyElementPresent(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
			click(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
			waitTime(3000);
			click(MLWalletLogOutPage.objLogoutBtn, "Logout Button");
			waitTime(4000);
			click(MLWalletLogOutPage.objPopUpLogoutBtn,  "Logout Button");
			waitTime(3000);
			if(verifyElementPresent(MLWalletLogOutPage.objChangeNumber,"Mpin Page")){
				verifyElementPresent(MLWalletLogOutPage.objChangeNumber,getTextVal(MLWalletLogOutPage.objChangeNumber,"button"));
				verifyElementPresent(MLWalletLoginPage.objTroubleSigningIn,getTextVal(MLWalletLoginPage.objTroubleSigningIn,"Button"));
				verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Button"));
				verifyElementPresent(MLWalletLoginPage.objAppVersionChangeNumber,getTextVal(MLWalletLoginPage.objAppVersionChangeNumber,"App Version"));
				logger.info("Lgn_TC_18, Application Logged In Successfully");
				extentLoggerPass("Lgn_TC_18", "Lgn_TC_18, Application Logged In Successfully");
				System.out.println("-----------------------------------------------------------");
			}
			getDriver().resetApp();
		}

	

		public void loginInAppOTPNavigation_Lgn_TC_22() throws Exception {
			HeaderChildNode("LogIn In App OTP Navigation");
			waitTime(2000);
			changeNumberPage();
			clearField();
			click(MLWalletLoginPage.objMobileNumberTextField, "Mobile Number Text Field");
			type(MLWalletLoginPage.objMobileNumberTextField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
			waitTime(3000);
			click(MLWalletLoginPage.objLoginBtn, "Login Button");
			waitTime(10000);
			if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
				verifyElementPresent(MLWalletLoginPage.objOTP,getTextVal(MLWalletLoginPage.objOTP,"One Time Pin"));
				logger.info("Lgn_TC_22, LogIn In App OTP Navigation validated");
				extentLoggerPass("Lgn_TC_22", "Lgn_TC_22, LogIn In App OTP Navigation validated");
				System.out.println("-----------------------------------------------------------");
			}
			getDriver().resetApp();
		}

		public void loginInAppOTPPageUIValidation_Lgn_TC_23() throws Exception {
			HeaderChildNode("Login InApp OTP Page UI Validation");
			waitTime(2000);
			changeNumberPage();
			clearField();
			click(MLWalletLoginPage.objMobileNumberTextField, "Mobile Number Text Field");
			type(MLWalletLoginPage.objMobileNumberTextField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
			click(MLWalletLoginPage.objLoginBtn, "Login Button");
			waitTime(3000);
			if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
				verifyElementPresent(MLWalletLoginPage.objOTP,getTextVal(MLWalletLoginPage.objOTP,"One Time Pin"));
				verifyElementPresent(MLWalletLoginPage.objOtpContineBtn,getTextVal(MLWalletLoginPage.objOtpContineBtn,"Button"));
				verifyElementPresent(MLWalletLoginPage.objCancelBtn,getTextVal(MLWalletLoginPage.objCancelBtn,"Button"));
				logger.info("Lgn_TC_23, LogIn In App OTP Navigation validated");
				extentLoggerPass("Lgn_TC_23", "Lgn_TC_23, LogIn In App OTP Navigation validated");
				System.out.println("-----------------------------------------------------------");
			}
			getDriver().resetApp();
		}

		public void loginNewOTPAfterSixtySecondsValidation_Lgn_TC_24() throws Exception {
			HeaderChildNode("LogIn New OTP After Sixty Seconds Validation");
			waitTime(2000);
			changeNumberPage();
			clearField();
			click(MLWalletLoginPage.objMobileNumberTextField, "Mobile Number Text Field");
			type(MLWalletLoginPage.objMobileNumberTextField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
			click(MLWalletLoginPage.objLoginBtn, "Login Button");
			waitTime(2000);
			verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"));
			if(verifyElementPresent(MLWalletLoginPage.objOTP,getTextVal(MLWalletLoginPage.objOTP,"One Time Pin"))){
				String sGeneratedOTP = getText(MLWalletLoginPage.objOTP);
				waitTime(70000);
				String sNewlyGeneratedOTPAfterSixtySeconds = getText(MLWalletLoginPage.objOTP);
				assertNotEquals(sGeneratedOTP,sNewlyGeneratedOTPAfterSixtySeconds);
				logger.info("Lgn_TC_24, LogIn, After Sixty Seconds New OTP got generated is validated");
				extentLoggerPass("Lgn_TC_24", "Lgn_TC_24, LogIn, After Sixty Seconds New OTP got generated is validated");
				System.out.println("-----------------------------------------------------------");
			}
		    getDriver().resetApp();
		}

		public void loginOTPCancelBtnFunctionality_Lgn_TC_25() throws Exception {
			HeaderChildNode("LogIn OTP Cancel Button Functionality");
			waitTime(2000);
			changeNumberPage();
			clearField();
			click(MLWalletLoginPage.objMobileNumberTextField, "Mobile Number Text Field");
			type(MLWalletLoginPage.objMobileNumberTextField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
			click(MLWalletLoginPage.objLoginBtn, "Login Button");
			explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
			verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"));
			verifyElementPresentAndClick(MLWalletLoginPage.objCancelBtn,getTextVal(MLWalletLoginPage.objCancelBtn,"Button"));
			waitTime(2000);
			if(verifyElementPresent(MLWalletLoginPage.objLoginBtn,getTextVal(MLWalletLoginPage.objLoginBtn,"Button"))){
				logger.info("Lgn_TC_25, LogIn, After clicking on Cancel in One time pin popup App navigates to login Page validated");
				extentLoggerPass("Lgn_TC_25", "Lgn_TC_25, LogIn, After clicking on Cancel in One time pin popup App navigates to login Page validated");
				System.out.println("-----------------------------------------------------------");
			}
			 getDriver().resetApp();
		}


		public void loginOTPContinueBtnFunctionality_Lgn_TC_26() throws Exception {
			HeaderChildNode("LogIn OTP Continue Button Functionality");
			waitTime(2000);
			changeNumberPage();
			clearField();
			click(MLWalletLoginPage.objMobileNumberTextField, "Mobile Number Text Field");
			type(MLWalletLoginPage.objMobileNumberTextField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
			click(MLWalletLoginPage.objLoginBtn, "Login Button");
			explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
			verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"));
			verifyElementPresentAndClick(MLWalletLoginPage.objOtpContineBtn,getTextVal(MLWalletLoginPage.objOtpContineBtn,"Button"));
			waitTime(2000);
			if(verifyElementPresent(MLWalletLoginPage.objAvailableBalance,getTextVal(MLWalletLoginPage.objAvailableBalance,"Button"))){
				logger.info("Lgn_TC_26, LogIn, After clicking on Continue in One time pin popup App navigates to Home Page validated");
				extentLoggerPass("Lgn_TC_26", "Lgn_TC_26, LogIn, After clicking on Continue in One time pin popup App navigates to Home Page validated");
				System.out.println("-----------------------------------------------------------");
			}
			getDriver().resetApp();
		}

		
		
		
		//========================================CASH OUT / WITHDRAW===============================================//
		//======================================= Generalized methods =============================================//


		public void enterBankDetails(String sAccountNumber) throws Exception {
				waitTime(2000);
				if (verifyElementPresent(MLWalletCashOutPage.objBankInformation, getTextVal(MLWalletCashOutPage.objBankInformation, "Page"))) {
					type(MLWalletCashOutPage.objAccountNumberField, sAccountNumber, "Account Number Field");
					type(MLWalletCashOutPage.objFirstname, prop.getproperty("First_Name"), "Account Holder First Name");
					type(MLWalletCashOutPage.objMiddleName, prop.getproperty("Middle_Name"), "Account Holder Middle Name");
					click(MLWalletCashOutPage.objCheckBox, "Check Box");
					type(MLWalletCashOutPage.objLastName, prop.getproperty("Last_Name"), "Account Holder Last Name");
					Swipe("UP", 1);
					type(MLWalletCashOutPage.objEmailAddress, prop.getproperty("Email"), "Account Holder Email Address");
					waitTime(2000);
					click(MLWalletCashOutPage.objdntHaveMiddleNameText, getTextVal(MLWalletCashOutPage.objdntHaveMiddleNameText, "Text"));			
					Swipe("UP", 1);
					waitTime(2000);
					click(MLWalletCashOutPage.objConfirmBtn, "Confirm Button");
				}

			}

		 public void enterAmountMLBranch(String nAmount) throws Exception {
				if (verifyElementPresent(MLWalletCashOutPage.objToAnyMLBranch, getTextVal(MLWalletCashOutPage.objToAnyMLBranch, "Button"))) {
					click(MLWalletCashOutPage.objToAnyMLBranch, getTextVal(MLWalletCashOutPage.objToAnyMLBranch, "Button"));
					waitTime(2000);
					verifyElementPresent(MLWalletCashOutPage.objCashOutToBranch,getTextVal(MLWalletCashOutPage.objCashOutToBranch,"Page"));
					waitTime(2000);
					type(MLWalletCashOutPage.objAmountField, nAmount, "Amount to Send");
					click(MLWalletCashOutPage.objNextBtn, getTextVal(MLWalletCashOutPage.objNextBtn, "Button"));
					click(MLWalletCashOutPage.objNextBtn, getTextVal(MLWalletCashOutPage.objNextBtn, "Button"));
					waitTime(2000);
					if(!verifyElementPresent(MLWalletCashOutPage.objContinueBtn, getTextVal(MLWalletCashOutPage.objContinueBtn, "Button")))
					{
						click(MLWalletCashOutPage.objNextBtn, getTextVal(MLWalletCashOutPage.objNextBtn, "Button"));
					}
					waitTime(4000);
					click(MLWalletCashOutPage.objContinueBtn, getTextVal(MLWalletCashOutPage.objContinueBtn, "Button"));
					waitTime(3000);
				}
			}
		 
		 public void verifyRecentTransaction() throws Exception
		 {
			 mlWalletLogout();	
			 click(MLWalletLoginPage.objLoginBtn, "Login Button");
			 enterOTP("111111");
			 waitTime(5000);
		 }
		 
		 public void verifyRecentTransaction2(String sTier) throws Exception
		 {
			 mlWalletLogout();	
			 click(MLWalletLoginPage.objLoginBtn, "Login Button");
			 if(verifyElementPresent(MLWalletLoginPage.objMobileNoReqErrorMsg, "Mobile Number is Required Error Message"))
			 {
				 click(MLWalletLoginPage.objLoginBtn, "Login Button");
				 type(MLWalletLoginPage.objMobileNumberTextField, sTier, "Mobile Number Text Field");
				 click(MLWalletLoginPage.objLoginBtn, "Login Button");
			 }
			 enterOTP("111111");
			 waitTime(5000);
		 }
		 
		 public void verifyRecentTransaction3(String sTier) throws Exception
		 {
			 mlWalletLogout1();
			 click(MLWalletLoginPage.objLoginBtn, "Login Button");
			 if(verifyElementPresent(MLWalletLoginPage.objMobileNoReqErrorMsg, "Mobile Number is Required Error Message"))
			 {
				 click(MLWalletLoginPage.objLoginBtn, "Login Button");
				 type(MLWalletLoginPage.objMobileNumberTextField, sTier, "Mobile Number Text Field");
				 click(MLWalletLoginPage.objLoginBtn, "Login Button");
			 }
			 enterOTP("111111");
			 waitTime(5000);
		 }
		 
		 public void verifyRecentTransaction1() throws Exception
		 {
			 mlWalletLogout1();
			 click(MLWalletLoginPage.objLoginBtn, "Login Button");
			 enterOTP("111111");
			 waitTime(4000);
		 }

		public void clearField() throws Exception
		{
			if(verifyElementPresent(MLWalletLoginPage.objMobileNumberTextField1, "Mobile Number Text Field"))
			{
			clearField(MLWalletLoginPage.objMobileNumberTextField1, "Mobile Number Text Field");
			}
		}
			
		//===================================================================================================================/Pass1
		public void cashOutWithdrawBank_WM_TC_01(String Amount) throws Exception {
				HeaderChildNode("Cash Out Withdraw Branch");
				changeNumberPage();
				clearField();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				if (verifyElementPresent(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button")) {
					click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
					waitTime(4000);
					if (verifyElementPresent(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"))) {
						click(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"));
						waitTime(2000);
						click(MLWalletCashOutPage.BogusBank, getTextVal(MLWalletCashOutPage.BogusBank, "Bank"));
						waitTime(2000);
						enterBankDetails(prop.getproperty("AccountNumber"));
						waitTime(3000);
						type(MLWalletCashOutPage.objAmountField, Amount, "Amount to Send");
						waitTime(2000);
						click(MLWalletCashOutPage.objNextBtnThree, getTextVal(MLWalletCashOutPage.objNextBtnThree, "Button"));
						click(MLWalletCashOutPage.objNextBtnThree, getTextVal(MLWalletCashOutPage.objNextBtnThree, "Button"));
						waitTime(3000);
						click(MLWalletCashOutPage.objContinueBtn, getTextVal(MLWalletCashOutPage.objContinueBtn, "Button"));
						Swipe("UP", 2);
						click(MLWalletCashOutPage.objNextBtnThree, getTextVal(MLWalletCashOutPage.objNextBtnThree, "Button"));
						enterOTP(prop.getproperty("Valid_OTP"));
						waitTime(10000);
						if (verifyElementPresent(MLWalletCashOutPage.objTransactionReceipt, getTextVal(MLWalletCashOutPage.objTransactionReceipt, "Text"))) {
							verifyElementPresent(MLWalletCashOutPage.objTransactionSuccessMessage, getTextVal(MLWalletCashOutPage.objTransactionSuccessMessage, "Message"));
							String sTransactionSuccess = getText(MLWalletCashOutPage.objTransactionSuccessMessage);
							assertionValidation(sTransactionSuccess, "Transaction Successful");
							verifyElementPresent(MLWalletCashOutPage.objTransactionNo, getTextVal(MLWalletCashOutPage.objTransactionNo, "Transaction Number"));
							String sTransactionNumber = getText(MLWalletCashOutPage.objTransactionNo);
							Swipe("UP", 2);
							click(MLWalletCashOutPage.objBackToHomeBtn, getTextVal(MLWalletCashOutPage.objBackToHomeBtn, "Button"));
							verifyRecentTransaction();
							verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
							click(MLWalletHomePage.objCashOutButton, getTextVal(MLWalletHomePage.objCashOutButton, "Text"));
							waitTime(2000);
							if (verifyElementPresent(MLWalletCashOutPage.objTransactionDetails, getTextVal(MLWalletCashOutPage.objTransactionDetails, "Page"))) {
								String sReferenceNumberInCashOut = getText(MLWalletCashOutPage.objReferenceNumberInCashOut1);
								assertionValidation(sReferenceNumberInCashOut, sTransactionNumber);
								logger.info("WM_TC_01, Successfully Withdraw Money to Bank");
								extentLoggerPass("WM_TC_01", "WM_TC_01, Successfully Withdraw Money to Bank");
								System.out.println("-----------------------------------------------------------");
							}
						}
					}
				}
				backArrowBtn(1);
				mlWalletLogout();
			}

			
			public void cashOutWithInvalidAccNumber_WM_TC_02() throws Exception {  //Pass2
				HeaderChildNode("cashOut With Invalid Account Number");
				waitTime(2000);
				changeNumberPage();
				clearField();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
				waitTime(2000);
				if (verifyElementPresent(MLWalletCashOutPage.objToAnyBank,getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"))) {
					click(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"));
					waitTime(2000);
					click(MLWalletCashOutPage.BogusBank, getTextVal(MLWalletCashOutPage.BogusBank, "Bank"));
					enterBankDetails(prop.getproperty("Invalid_AccountNumber"));
					waitTime(3000);
					if (verifyElementPresent(MLWalletCashOutPage.objAccInvalidErrorMsg,getTextVal(MLWalletCashOutPage.objAccInvalidErrorMsg, "Text Message"))) 
					{
						String sInvalidAccTxt = getText(MLWalletCashOutPage.objAccInvalidErrorMsg);
						String ExpectedTxt = "Bank Account provided not valid. Please check the account details and try again.";
						assertionValidation(sInvalidAccTxt, ExpectedTxt);
						click(MLWalletCashOutPage.objOKBtnOne, "OK Button");
						logger.info("WM_TC_02, Bank Account provided not valid. Error Message is Validated");
						extentLoggerPass("WM_TC_02","WM_TC_02, Bank Account provided not valid. Error Message is Validated");
						System.out.println("-----------------------------------------------------------");
					}
				}
				backArrowBtn1(1);
				backArrowBtn(2);
				mlWalletLogout();
			}


			public void cashOutWithdrawBankMaxAmount_WM_TC_03(String Amount) throws Exception { //pass 3
				HeaderChildNode("Cash Out Withdraw Branch Max Amount");
				waitTime(2000);
				changeNumberPage();
				clearField();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
				waitTime(2000);
				if (verifyElementPresent(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"))) {
					click(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"));
					waitTime(5000);
					click(MLWalletCashOutPage.BogusBank, getTextVal(MLWalletCashOutPage.BogusBank, "Bank"));
					enterBankDetails(prop.getproperty("AccountNumber"));
					waitTime(3000);
					type(MLWalletCashOutPage.objAmountField, Amount, "Amount to Send");
					click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
					click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
					click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
					waitTime(2000);
					String sDragonPopUpMsg = getText(MLWalletCashOutPage.objDragonPayPopUpMsg);
					System.out.println(sDragonPopUpMsg);
					String sExpectedMsg = "Dragon Pay charges a fee of 35.00 pesos for this transaction. Do you wish to continue with your transaction?";
					assertionValidation(sDragonPopUpMsg, sExpectedMsg);
					click(MLWalletCashOutPage.objContinueBtn, getTextVal(MLWalletCashOutPage.objContinueBtn, "Button"));
					Swipe("UP", 2);
					waitTime(2000);
					click(MLWalletCashOutPage.objNextBtn1, getTextVal(MLWalletCashOutPage.objNextBtn1, "Button"));
					click(MLWalletCashOutPage.objNextBtn1, getTextVal(MLWalletCashOutPage.objNextBtn1, "Button"));
					click(MLWalletCashOutPage.objNextBtn1, getTextVal(MLWalletCashOutPage.objNextBtn1, "Button"));
					waitTime(5000);
					String sErrorMsg = getText(MLWalletCashOutPage.objBankMaxLimitTxt);
					String sExpectedErrorMsg = "The maximum Bank Cash-out per transaction set for your verification level is P50,000.00. Please try again.";
					assertionValidation(sErrorMsg, sExpectedErrorMsg);
					click(MLWalletCashOutPage.objOKBtnOne, "Ok Button");
					logger.info("WM_TC_03, The Maximum Bank Cash-out per transaction Msg is Validated");
					extentLoggerPass("WM_TC_03", "WM_TC_03, The Maximum Bank Cash-out per transaction Msg is Validated");
					System.out.println("-----------------------------------------------------------");
				}
				backArrowBtn(3);
				waitTime(2000);
				backArrowBtn1(1);
				waitTime(2000);
				backArrowBtn(2);
				mlWalletLogout();
			}

			public void cashOutWithdrawMinTransactionLimit_WM_TC_04(String Amount) throws Exception {//pass 4
				HeaderChildNode("Cash Out Withdraw Branch Max Amount");
				waitTime(2000);
				changeNumberPage();
				clearField();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
				waitTime(2000);
				if (verifyElementPresent(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"))) {
					click(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"));
					waitTime(5000);
					click(MLWalletCashOutPage.BogusBank, getTextVal(MLWalletCashOutPage.BogusBank, "Bank"));
					enterBankDetails(prop.getproperty("AccountNumber"));
					waitTime(3000);
					click(MLWalletCashOutPage.objAmountField,  "Amount to Send");
					type(MLWalletCashOutPage.objAmountField, Amount, "Amount to Send");
					click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
					click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
					//click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
					waitTime(3000);
					String sMinimumTransactionErrorMsg = getText(MLWalletCashOutPage.objMinimumTransactionErrorMsg);
					String sExpectedMsg = "The supplied amount is less than the required minimum transaction limit";
					assertionValidation(sMinimumTransactionErrorMsg, sExpectedMsg);
					click(MLWalletCashOutPage.objOKBtnOne, "OK Button");
					logger.info("WM_TC_04, The supplied amount is less than the required minimum transaction limit Error Msg is validated");
					extentLoggerPass("WM_TC_04", "WM_TC_04, The supplied amount is less than the required minimum transaction limit Error Msg is validated");
					System.out.println("-----------------------------------------------------------");
				}
				backArrowBtn(3);
				waitTime(2000);
				backArrowBtn1(1);
				backArrowBtn(2);
				mlWalletLogout();
			}


			public void cashOutWithdrawBranch_WM_TC_05() throws Exception {
				HeaderChildNode("Cash Out Withdraw Branch");
				waitTime(2000);
				changeNumberPage();
				clearField();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
				waitTime(5000);
				enterAmountMLBranch("10");
				enterOTP(prop.getproperty("Valid_OTP"));
				waitTime(5000);
				if (verifyElementPresent(MLWalletCashOutPage.objCashOutToBranch, getTextVal(MLWalletCashOutPage.objCashOutToBranch, "Page"))) {
					verifyElementPresent(MLWalletCashOutPage.objCreatedDate, getTextVal(MLWalletCashOutPage.objCreatedDate, "Date"));
					verifyElementPresent(MLWalletCashOutPage.objReferenceNumber, getTextVal(MLWalletCashOutPage.objReferenceNumber, "Reference Number"));
					String nReference = getText(MLWalletCashOutPage.objReferenceNumber);
					String sReferenceNumber = nReference.substring(5, 16);
					click(MLWalletCashOutPage.objBackToHomeBtn, getTextVal(MLWalletCashOutPage.objBackToHomeBtn, "Button"));
					verifyRecentTransaction();
					verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
					click(MLWalletHomePage.objCashOutButton, getTextVal(MLWalletHomePage.objCashOutButton, "Text"));
					waitTime(2000);
					if (verifyElementPresent(MLWalletCashOutPage.objTransactionDetails, getTextVal(MLWalletCashOutPage.objTransactionDetails, "Page"))) {				
						String sReferenceNumberInCashOut = getText(MLWalletCashOutPage.objReferenceNumberInCashOut1);
						System.out.println(sReferenceNumberInCashOut);
						assertionValidation(sReferenceNumberInCashOut, sReferenceNumber);
						logger.info("Reference Number is matching with recent Transaction");
						logger.info("WM_TC_05, Successfully Withdraw Money to ML Branch");
						extentLoggerPass("WM_TC_05", "WM_TC_05, Successfully Withdraw Money to ML Branch");
						System.out.println("-----------------------------------------------------------");
					}
				}
				backArrowBtn(1);
				mlWalletLogout();
			}
			
			public void cashOutMaxLimit_WM_TC_06() throws Exception {
				HeaderChildNode("Cash Out Withdraw Branch");
				waitTime(2000);
				changeNumberPage();
				clearField();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
				waitTime(2000);
				enterAmountMLBranch("100000");
				explicitWaitVisibility(MLWalletCashOutPage.objMaxLimitTxt, 10);
				if (verifyElementPresent(MLWalletCashOutPage.objMaxLimitTxt, getTextVal(MLWalletCashOutPage.objMaxLimitTxt, "Text Message"))) {
					String sMaxLimitTxt = getText(MLWalletCashOutPage.objMaxLimitTxt);
					String ExpectedTxt = "The maximum Branch Cash-out per transaction set for your verification level is P40,000.00. Please try again.";
					assertionValidation(sMaxLimitTxt, ExpectedTxt);
					click(MLWalletCashOutPage.objUpgradeNow, "Upgrade Now Button");
					logger.info("WM_TC_06, The supplied amount us less than the required minimum transaction limit. Error Message is Validated");
					extentLoggerPass("WM_TC_06", "WM_TC_06, The supplied amount us less than the required minimum transaction limit. Error Message is Validated");
					System.out.println("-----------------------------------------------------------");
				}
				backArrowBtn(3);
				mlWalletLogout();
			}

			public void cashOutInsufficientBalance_WM_TC_07() throws Exception {
				HeaderChildNode("Cash Out Insufficient Balance");
				waitTime(2000);
				changeNumberPage();
				clearField();
				mlWalletLogin(prop.getproperty("ios_BranchVerifiedTier"));
				click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
				waitTime(2000);
				enterAmountMLBranch("35000");
				explicitWaitVisibility(MLWalletCashOutPage.objInsufficientBalance, 20);
				if (verifyElementPresent(MLWalletCashOutPage.objInsufficientBalance, getTextVal(MLWalletCashOutPage.objInsufficientBalance, "Text Message"))) {
					String sInsufficientBalancePopupTxt = getText(MLWalletCashOutPage.objInsufficientBalance);
					String ExpectedTxt = "There is insufficient balance to proceed with this transaction. Please try again.";
					assertionValidation(sInsufficientBalancePopupTxt, ExpectedTxt);
					click(MLWalletCashOutPage.objOkBtn, "OK Button");
					logger.info("WM_TC_07, Insufficient Balance pop up validated");
					extentLoggerPass("WM_TC_07", "WM_TC_07, Insufficient Balance pop up validated");
					System.out.println("-----------------------------------------------------------");
				}
				backArrowBtn(1);
				mlWalletLogout();

			}
			public void cashOutBuyerTierLevelAcc_WM_TC_09() throws Exception {
				HeaderChildNode("Cash Out Withdraw Branch");
				waitTime(2000);
				changeNumberPage();
				mlWalletLogin(prop.getproperty("Buyer_Tier"));
				explicitWaitVisibility(MLWalletCashOutPage.objCashOut, 20);
				click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
				waitTime(2000);
				enterAmountMLBranch("100");
				explicitWaitVisibility(MLWalletCashOutPage.objMaxLimitTxt, 20);
				if (verifyElementPresent(MLWalletCashOutPage.objMaxLimitTxt, getTextVal(MLWalletCashOutPage.objMaxLimitTxt, "Text Message"))) {
					String sErrorMessage = getText(MLWalletCashOutPage.objMaxLimitTxt);
					String ExpectedTxt = "Branch Cash-out is not allowed for customers at this verification level. Please upgrade your account to use this service.";
					assertionValidation(sErrorMessage, ExpectedTxt);
					click(MLWalletCashOutPage.objUpgradeNow, "Upgrade Now Button");
					logger.info("WM_TC_09, Branch Cash-out is not allowed for customers at this verification level. Error Message is Validated");
					extentLoggerPass("WM_TC_09", "WM_TC_08, Branch Cash-out is not allowed for customers at this verification level. Error Message is Validated");
					System.out.println("-----------------------------------------------------------");
				}
				backArrowBtn(3);
				mlWalletLogout();
			}

			
			
			//=================================== Cash Out Phase2==================================================//

			public void cashOutSelectBank(String sBank) throws Exception {
				if (verifyElementPresent(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button")) {
					click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
					waitTime(2000);
					if (verifyElementPresent(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"))) {
						click(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"));
						type(MLWalletCashOutPage.objSearchBank, sBank, "Search Bank Text Field");
						waitTime(2000);
						click(MLWalletCashOutPage.objBogusBank1, getTextVal(MLWalletCashOutPage.objBogusBank1, "Bank"));
						click(MLWalletCashOutPage.objBogusBank1, getTextVal(MLWalletCashOutPage.objBogusBank1, "Bank"));
					}
				}
			}
			
			
			public void cashOutInvalidBank_WM_TC_10() throws Exception { //Pass 10
				HeaderChildNode("Cash Out Invalid Bank Details");
				waitTime(2000);
				changeNumberPage();
				mlWalletLogin(prop.getproperty("Branch_Verified"));

				if (verifyElementPresent(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button")) {
					click(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
					waitTime(3000);
					if (verifyElementPresent(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"))) {
						click(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"));
						type(MLWalletCashOutPage.objSearchBank, prop.getproperty("Invalid_BankName"), "Search Bank Text Field");
						waitTime(2000);
						if (verifyElementPresent(MLWalletCashOutPage.objNoRecentTransactionTxt, getTextVal(MLWalletCashOutPage.objNoRecentTransactionTxt, "Text"))) {
							logger.info("WM_TC_10, No Recent Transaction message Validated after entering invalid Bank Name");
							extentLoggerPass("WM_TC_10", "WM_TC_10, No Recent Transaction message Validated after entering invalid Bank Name");
							System.out.println("-----------------------------------------------------------");
						}
					}
				}
				backArrowBtn(2);
				mlWalletLogout();
			}

			public void searchAndSelectBank_WM_TC_11() throws Exception {
				HeaderChildNode("Search And Select Bank");
				waitTime(2000);
				changeNumberPage();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				cashOutSelectBank(prop.getproperty("Valid_BankName"));
				waitTime(2000);
				if (verifyElementPresent(MLWalletCashOutPage.objBankInformation, getTextVal(MLWalletCashOutPage.objBankInformation, "Page"))) {
					verifyElementPresent(MLWalletCashOutPage.objBogusbank2, getTextVal(MLWalletCashOutPage.objBogusbank2, "Bank Name"));
					logger.info("WM_TC_11, Bank Name auto-displayed after searching and selecting the particular Bank");
					extentLoggerPass("WM_TC_11", "WM_TC_11, Bank Name auto-displayed after searching and selecting the particular Bank");
					System.out.println("-----------------------------------------------------------");
				}
				backArrowBtn1(1);
				backArrowBtn(2);
				mlWalletLogout();
			}

			public void cashOutInvalidAmount_WM_TC_12() throws Exception {
				HeaderChildNode("Search And Select Bank");
				waitTime(2000);
				changeNumberPage();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				cashOutSelectBank(prop.getproperty("Valid_BankName"));
				enterBankDetails(prop.getproperty("AccountNumber"));
				waitTime(5000);
				click(MLWalletCashOutPage.objAmountField,  "Amount to Send");
				type(MLWalletCashOutPage.objAmountField, "", "Amount to Send");
				click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
				click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
				if (verifyElementPresent(MLWalletCashOutPage.objAmountRequiredErrorMsg, getTextVal(MLWalletCashOutPage.objAmountRequiredErrorMsg, "Error Message"))) {
					String sAmountRequiredErrorMsg = getText(MLWalletCashOutPage.objAmountRequiredErrorMsg);
					String sExceptedErrorMsg = "Amount is required";
					assertionValidation(sAmountRequiredErrorMsg, sExceptedErrorMsg);
					logger.info("WM_TC_12, Amount is required - Error message is Validated");
					extentLoggerPass("WM_TC_12", "WM_TC_12, Amount is required - Error message is Validated");
					System.out.println("-----------------------------------------------------------");
				}
				backArrowBtn(1);
				backArrowBtn1(1);
				backArrowBtn(2);
				mlWalletLogout();
			}

			public void cashOutSaveRecipient_WM_TC_13(String sAmount) throws Exception {
				HeaderChildNode("Cash out Save Recipient");
				waitTime(2000);
				changeNumberPage();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				cashOutSelectBank(prop.getproperty("Valid_BankName"));
				enterBankDetails("5"+RandomIntegerGenerator(9));
				waitTime(3000);
				type(MLWalletCashOutPage.objAmountField, sAmount, "Amount to Send");
				click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
				click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
				click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
				waitTime(2000);
				click(MLWalletCashOutPage.objContinueBtn, getTextVal(MLWalletCashOutPage.objContinueBtn, "Button"));
				Swipe("UP", 2);
				click(MLWalletCashOutPage.objNextBtn1, getTextVal(MLWalletCashOutPage.objNextBtn1, "Button"));
				click(MLWalletCashOutPage.objNextBtn1, getTextVal(MLWalletCashOutPage.objNextBtn1, "Button"));
				waitTime(2000);
				enterOTP(prop.getproperty("Valid_OTP"));
				waitTime(4000);
				Swipe("UP", 2);
				type(MLWalletCashOutPage.objNickName, RandomStringGenerator(5), "Nick Name Input Text Field");
				click(MLWalletCashOutPage.objSaveRecipientBtn, getTextVal(MLWalletCashOutPage.objSaveRecipientBtn, "Button"));
				click(MLWalletCashOutPage.objSaveRecipientBtn, getTextVal(MLWalletCashOutPage.objSaveRecipientBtn, "Button"));
				waitTime(2000);
				verifyElementPresentAndClick(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Button"));
				waitTime(4000);
				click(MLWalletCashOutPage.BogusBank, getTextVal(MLWalletCashOutPage.BogusBank, "Bank"));
				waitTime(4000);
				verifyElementPresent(MLWalletCashOutPage.objSavedBackAccount, getTextVal(MLWalletCashOutPage.objSavedBackAccount, "Page"));
				if (verifyElementPresent(MLWalletCashOutPage.objNickNameInSavedBankAcc, getTextVal(MLWalletCashOutPage.objNickNameInSavedBankAcc, "Nick Name for Saved Bank Account"))) {
					logger.info("WM_TC_13, Saved Recipient is displayed under Saved Bank Account");
					extentLoggerPass("WM_TC_13", "WM_TC_13, Saved Recipient is displayed under Saved Bank Account");
					System.out.println("-----------------------------------------------------------");
				}
				backArrowBtn1(1);
				backArrowBtn(2);
				mlWalletLogout();
			}
			

			public void cashOutRecipientDuplicate_WM_TC_14(String sAmount) throws Exception {
				HeaderChildNode("Cash out Recipient Duplicate");
				waitTime(2000);
				changeNumberPage();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				cashOutSelectBank(prop.getproperty("Valid_BankName"));
				enterBankDetails(prop.getproperty("AccountNumber"));
				waitTime(3000);
				type(MLWalletCashOutPage.objAmountField, sAmount, "Amount to Send");
				explicitWaitVisibility(MLWalletCashOutPage.objNextBtnThree, 10);
				click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
				click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
				explicitWaitVisibility(MLWalletCashOutPage.objContinueBtn, 10);
				click(MLWalletCashOutPage.objContinueBtn, getTextVal(MLWalletCashOutPage.objContinueBtn, "Button"));
				Swipe("UP", 2);
				click(MLWalletCashOutPage.objNextBtn1, getTextVal(MLWalletCashOutPage.objNextBtn1, "Button"));
				click(MLWalletCashOutPage.objNextBtn1, getTextVal(MLWalletCashOutPage.objNextBtn1, "Button"));
				waitTime(3000);
				enterOTP(prop.getproperty("Valid_OTP"));
				Swipe("UP", 2);
				type(MLWalletCashOutPage.objNickName, prop.getproperty("Nick_Name"), "Nick Name Input Text Field");
				click(MLWalletCashOutPage.objSaveRecipientBtn, getTextVal(MLWalletCashOutPage.objSaveRecipientBtn, "Button"));
				click(MLWalletCashOutPage.objSaveRecipientBtn, getTextVal(MLWalletCashOutPage.objSaveRecipientBtn, "Button"));
				waitTime(3000);
				if (verifyElementPresent(MLWalletCashOutPage.objRecipientExistMsg, getTextVal(MLWalletCashOutPage.objRecipientExistMsg, "Popup Message"))) {
					String sRecipientExistMsg = getText(MLWalletCashOutPage.objRecipientExistMsg);
					String sExpectedMsg = "Recipient already exists.";
					assertionValidation(sRecipientExistMsg, sExpectedMsg);
					click(MLWalletCashOutPage.objOKBtnOne, "Ok Button");
					click(MLWalletCashOutPage.objBackToHomeBtn, "Back To Home Button");
					logger.info("WM_TC_14, Recipient already exists pop up message Validated");
					extentLoggerPass("WM_TC_14", "WM_TC_14, Recipient already exists pop up message Validated");
					System.out.println("-----------------------------------------------------------");
				}
				mlWalletLogout();
			}

			public void cashOutUIValidation_WM_TC_16() throws Exception {
				HeaderChildNode("Cash Out Page UI Validation");
				waitTime(2000);
				changeNumberPage();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				verifyElementPresentAndClick(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
				waitTime(2000);
				if (verifyElementPresent(MLWalletCashOutPage.objCashOutWithdraw, getTextVal(MLWalletCashOutPage.objCashOutWithdraw, "Page"))) {
					verifyElementPresent(MLWalletCashOutPage.objCashOutOptions, getTextVal(MLWalletCashOutPage.objCashOutOptions, "Header"));
					verifyElementPresent(MLWalletCashOutPage.objToAnyBank, getTextVal(MLWalletCashOutPage.objToAnyBank, "Option"));
					verifyElementPresent(MLWalletCashOutPage.objToAnyMLBranch, getTextVal(MLWalletCashOutPage.objToAnyMLBranch, "Option"));
					verifyElementPresent(MLWalletCashOutPage.objCashOutOngoingTransaction, getTextVal(MLWalletCashOutPage.objCashOutOngoingTransaction, "Header"));
					logger.info("WM_TC_16, Cash Out Page UI Validation");
					extentLoggerPass("WM_TC_16", "WM_TC_16, Cash Out Page UI Validation");
					System.out.println("-----------------------------------------------------------");
				}
				backArrowBtn(1);
				mlWalletLogout();
			}

			public void cashOutWithdrawBackBtnValidation_WM_TC_17() throws Exception {
				HeaderChildNode("Cash Out Page back arrow Button Validation");
				waitTime(2000);
				changeNumberPage();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				verifyElementPresentAndClick(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
				waitTime(2000);
				backArrowBtn(1);
				waitTime(2000);
				if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
					logger.info("WM_TC_17, Cash Out Page back arrow Button Validation");
					extentLoggerPass("WM_TC_17", "WM_TC_17, Cash Out Page back arrow Button Validation");
					System.out.println("-----------------------------------------------------------");
				}
				mlWalletLogout();
			}

			public void cashOutToBranchUIValidation_WM_TC_18() throws Exception {
				HeaderChildNode("Cash Out To Branch UI Validation");
				waitTime(2000);
				changeNumberPage();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				verifyElementPresentAndClick(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
				waitTime(2000);
				verifyElementPresentAndClick(MLWalletCashOutPage.objToAnyMLBranch, getTextVal(MLWalletCashOutPage.objToAnyMLBranch, "Button"));
				waitTime(2000);
				if (verifyElementPresent(MLWalletCashOutPage.objCashOutPage, getTextVal(MLWalletCashOutPage.objCashOutPage, "Page"))) {
					verifyElementPresent(MLWalletCashOutPage.ObjCashOutToBranch, getTextVal(MLWalletCashOutPage.objCashOutToBranch, "Header"));
					verifyElementPresent(MLWalletCashOutPage.objUserName, getTextVal(MLWalletCashOutPage.objUserName, "User Name"));
					verifyElementPresent(MLWalletCashOutPage.objUserMobileNumber, getTextVal(MLWalletCashOutPage.objUserMobileNumber, "User Mobile Number"));
					verifyElementPresent(MLWalletCashOutPage.objMLWalletBalance, getTextVal(MLWalletCashOutPage.objMLWalletBalance, "Balance"));
					//verifyElementPresent(MLWalletCashOutPage.objCashOutInstructions, "Instructions Icon");
					logger.info("WM_TC_18, Cash Out to Branch Page Validation");
					extentLoggerPass("WM_TC_18", "WM_TC_18, Cash Out to Branch Page Validation");
					System.out.println("-----------------------------------------------------------");
				}
				backArrowBtn(2);
				mlWalletLogout();
			}

			public void cashOutToBranchBackBtnValidation_WM_TC_19() throws Exception {
				HeaderChildNode("Cash Out To Branch Page back arrow Button Validation");
				waitTime(2000);
				changeNumberPage();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				verifyElementPresentAndClick(MLWalletCashOutPage.objCashOut, "CashOut / Withdraw Button");
				waitTime(2000);
				verifyElementPresentAndClick(MLWalletCashOutPage.objToAnyMLBranch, getTextVal(MLWalletCashOutPage.objToAnyMLBranch, "Button"));
				waitTime(2000);
				backArrowBtn(1);
				if (verifyElementPresent(MLWalletCashOutPage.objCashOutWithdraw, getTextVal(MLWalletCashOutPage.objCashOutWithdraw, "Page"))) {
					logger.info("WM_TC_19, Cash Out To Branch Page back arrow Button Validation");
					extentLoggerPass("WM_TC_19", "WM_TC_19, Cash Out To Branch Page back arrow Button Validation");
					System.out.println("-----------------------------------------------------------");
				}
				backArrowBtn(1);
				mlWalletLogout();
			}


			public void cashOutOTPPageUIValidation_WM_TC_20(String sAmount) throws Exception {
				HeaderChildNode("Cash Out OTP Page UI Validation");
				waitTime(2000);
				changeNumberPage();
				mlWalletLogin(prop.getproperty("Branch_Verified"));
				cashOutSelectBank(prop.getproperty("Valid_BankName"));
				enterBankDetails(prop.getproperty("AccountNumber"));
				waitTime(2000);
				type(MLWalletCashOutPage.objAmountField, sAmount, "Amount to Send");
				click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
				click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
				click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
				explicitWaitVisibility(MLWalletCashOutPage.objContinueBtn, 20);
				click(MLWalletCashOutPage.objContinueBtn, getTextVal(MLWalletCashOutPage.objContinueBtn, "Button"));
				Swipe("UP", 2);
				click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
				click(MLWalletCashOutPage.objNextBtnFour, getTextVal(MLWalletCashOutPage.objNextBtnFour, "Button"));
				waitTime(7000);
				click(MLWalletLoginPage.objOtpContineBtn, "Clicked On OTP Continue Button");
				if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
					verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
					waitTime(5000);
					Swipe("Up", 1);
					click(MLWalletCashOutPage.objBackToHomeBtn, "Back To Home Button");
					logger.info("WM_TC_20, One Time Pin page UI Validation");
					extentLoggerPass("WM_TC_20", "WM_TC_20, One Time Pin page UI Validation");
					System.out.println("-----------------------------------------------------------");
				}
				mlWalletLogout();
			}

			public void cashOutOTPPageBackBtnValidation_WM_TC_21(String sAmount) throws Exception {
				HeaderChildNode("Cash Out OTP Page Back Button Validation");
				waitTime(2000);
				changeNumberPage();
				click(MLWalletLoginPage.objMobileNumberTextField1, "Mobile Number Text Field");
				clearField();
				type(MLWalletLoginPage.objMobileNumberTextField, "9999999996", "Mobile Number Text Field");
				click(MLWalletLoginPage.objLoginBtn, "Login Button");
				verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
				backArrowBtn(1);
				logger.info("WM_TC_21, OTP page back arrow Button Validation");
				extentLoggerPass("WM_TC_21", "WM_TC_21, OTP page back arrow Button Validation");
				System.out.println("-----------------------------------------------------------");
				}


			
			
			
			//================================ Send/Transfer To any ML Branch ============================================//
			//=============================== General methods For send transfer ============================================//

				public void sendMoneyToAnyMLBranch() throws Exception {
					mlWalletLogin(prop.getproperty("New_Branch_Verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					explicitWaitVisibility(SendTransferPage.objSendMoney, 10);
					verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
					if (verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
						click(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
					}
				}
				
				public void sendMoneyToAnyMLBranch(String sTier) throws Exception {
					mlWalletLogin(sTier);
                    waitTime(2000);
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					waitTime(2000);
					verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
					waitTime(2000);
					if (verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
						click(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
					}
				}

				public void enterMLBranchDetails() throws Exception {
					explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
					if (verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"))) {
						verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
						type(SendTransferPage.objFirstname, prop.getproperty("First_Name"), "First Name Text Field");
						type(SendTransferPage.objMiddleName, prop.getproperty("Middle_Name"), "Middle Name Text Field");
						click(SendTransferPage.objCheckBox, "Check Box");
						waitTime(3000);
						type(SendTransferPage.objLastName, prop.getproperty("Last_Name"), "Last Name Text Field");
						type(SendTransferPage.objMobileNumber, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
						Swipe("Down",2);
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
//						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
					}
				}

				public void enterAmountToKwartaPadala(String nAmount) throws Exception {
					explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
					verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
					type(SendTransferPage.objAmountTxtField, nAmount, "Amount text Field");
					Swipe("UP", 1);
					click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
					click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
					waitTime(3000);
					verifyElementPresent(SendTransferPage.objSelectPaymentMethod, getTextVal(SendTransferPage.objSelectPaymentMethod, "Page"));
					waitTime(2000);
					click(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
					waitTime(6000);
					verifyElementPresent(SendTransferPage.objConfirmDetails, getTextVal(SendTransferPage.objConfirmDetails, "Page"));
					click(SendTransferPage.objConfirmBtn, getTextVal(SendTransferPage.objConfirmBtn, "Button"));
					waitTime(2000);
				}

				public void selectSavedRecipient() throws Exception {
					explicitWaitVisible(SendTransferPage.objKwartaPadala, 10);
					if (verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"))) {
						click(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"));
						explicitWaitVisible(SendTransferPage.objSavedRecipients, 10);
						click(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Page"));
						waitTime(2000);
						type(SendTransferPage.objSearchRecipient, prop.getproperty("Last_Name"), "Search Recipient Text Field");
						verifyElementPresent(SendTransferPage.objSelectLastName, getTextVal(SendTransferPage.objSelectLastName, "Recipient"));
						click(SendTransferPage.objSelectLastName, getTextVal(SendTransferPage.objSelectLastName, "Recipient"));
						click(SendTransferPage.objSelectLastName, getTextVal(SendTransferPage.objSelectLastName, "Recipient"));
						waitTime(3000);
					}
				}

				public void addRecipient() throws Exception {
					if (verifyElementPresent(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"))) {
						waitTime(2000);
						click(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"));
						waitTime(2000);
						click(SendTransferPage.objAddRecipient, getTextVal(SendTransferPage.objAddRecipient, "Button"));
						explicitWaitVisible(SendTransferPage.objAddRecipient, 20);
						type(SendTransferPage.objFirstname, prop.getproperty("First_Name"), "First Name Text Field");
						type(SendTransferPage.objMiddleName, prop.getproperty("Middle_Name"), "Middle Name Text Field");
						click(SendTransferPage.objCheckBox, "Check Box");
						type(SendTransferPage.objLastName, prop.getproperty("Last_Name"), "Last Name Text Field");
						type(SendTransferPage.objMobileNumber, prop.getproperty("Branch_Verified"), "Last Name Text Field");
						type(SendTransferPage.objNickName, prop.getproperty("Nick_Name"), "Nick Name Text Field");
						click(SendTransferPage.ObjSaveRecipient, getTextVal(SendTransferPage.ObjSaveRecipient, "Button"));
						click(SendTransferPage.ObjSaveRecipient, getTextVal(SendTransferPage.ObjSaveRecipient, "Button"));
					}
				}
				
				public void sendMoneyToMLBranchRatesValidation(String sAmount) throws Exception {
					sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
					enterMLBranchDetails();
					waitTime(5000);
					verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
					type(SendTransferPage.objAmountTxtField, sAmount, "Amount text Field");
					click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
					click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
					waitTime(2000);
					verifyElementPresent(SendTransferPage.objSelectPaymentMethod, getTextVal(SendTransferPage.objSelectPaymentMethod, "Page"));
					waitTime(2000);
					click(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
					waitTime(5000);
					verifyElementPresent(SendTransferPage.objConfirmDetails, getTextVal(SendTransferPage.objConfirmDetails, "Page"));
				}


				//===============================================================================================================//
				public void sendMoneyToMLBranch_STB_TC_01(String enable) throws Exception {
					HeaderChildNode("Send Money to any ML Branch");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToAnyMLBranch();
					enterMLBranchDetails();
					enterAmountToKwartaPadala("100");
					enterOTP(prop.getproperty("Valid_OTP"));
					waitTime(7000);
					if(verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
						verifyElementPresent(SendTransferPage.objPHPAmount, getTextVal(SendTransferPage.objPHPAmount, "Amount"));
						verifyElementPresent(SendTransferPage.objDate, getTextVal(SendTransferPage.objDate, "Date"));
						verifyElementPresent(SendTransferPage.objReferenceNumber, getTextVal(SendTransferPage.objReferenceNumber, "Reference Number"));
						String sReference = getText(SendTransferPage.objReferenceNumber);
						System.out.println(sReference);
						String sReferenceNumber = sReference.substring(9, 20);
						System.out.println(sReferenceNumber);
						//Swipe("UP", 2);
						scroll_To_Text(SendTransferPage.objBackToHomeBtn, "name", "Back To Home");
						click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
						verifyRecentTransaction();
						verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
						click(MLWalletHomePage.objKwartaPadala, getTextVal(MLWalletHomePage.objKwartaPadala, "Text"));
						waitTime(3000);
						if (verifyElementPresent(SendTransferPage.objReferenceNumberInTransactionDetails, getTextVal(SendTransferPage.objReferenceNumberInTransactionDetails, "Page"))) {
							String sReferenceNumberInCashOut = getText(SendTransferPage.objReferenceNumberInTransactionDetails);
							System.out.println(sReferenceNumberInCashOut);
							assertionValidation(sReferenceNumberInCashOut, sReferenceNumber);
							logger.info("STB_TC_01, Successfully sent Amount to ML Branch and Transaction Details is validated");
							extentLoggerPass("STB_TC_01", "STB_TC_01, Successfully sent Amount to ML Branch and Transaction Details is validated");
							System.out.println("-----------------------------------------------------------");
						}
					}
					if(enable.equals("true"))
					{
						backArrowBtn(1);
						mlWalletLogout();
					}					
				}
				
				
				public void sendMoneyToSavedRecipient_STB_TC_02() throws Exception {
					HeaderChildNode("Send Money to any ML Branch");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("New_Branch_Verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
					if (verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
						click(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
						selectSavedRecipient();
						waitTime(3000);;
						click(SendTransferPage.objSelectRecipient, getTextVal(SendTransferPage.objSelectRecipient, "Button"));
						Swipe("UP", 1);
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						waitTime(5000);
						enterAmountToKwartaPadala("100");
						enterOTP(prop.getproperty("Valid_OTP"));
						waitTime(4000);
						if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
							verifyElementPresent(SendTransferPage.objPHPAmount, getTextVal(SendTransferPage.objPHPAmount, "Amount"));
							verifyElementPresent(SendTransferPage.objDate, getTextVal(SendTransferPage.objDate, "Date"));
							verifyElementPresent(SendTransferPage.objReferenceNumber, getTextVal(SendTransferPage.objReferenceNumber, "Reference Number"));
							String sReference = getText(SendTransferPage.objReferenceNumber);
							System.out.println(sReference);
							String sReferenceNumber = sReference.substring(9, 20);
							Swipe("UP", 2);
							click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
							verifyRecentTransaction();
							verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
							click(MLWalletHomePage.objKwartaPadala, getTextVal(MLWalletHomePage.objKwartaPadala, "Text"));
							waitTime(2000);
							if (verifyElementPresent(SendTransferPage.objReferenceNumberInTransactionDetails, getTextVal(SendTransferPage.objReferenceNumberInTransactionDetails, "Page"))) {
								String sReferenceNumberInCashOut = getText(SendTransferPage.objReferenceNumberInTransactionDetails);
								System.out.println(sReferenceNumberInCashOut);
								assertionValidation(sReferenceNumberInCashOut, sReferenceNumber);
								logger.info("STB_TC_02, Successfully sent Amount to saved Recipient and Transaction Details is validated");
								extentLoggerPass("STB_TC_02", "STB_TC_02, Successfully sent Amount to saved Recipient and Transaction Details is validated");
								System.out.println("-----------------------------------------------------------");
							}
						}
					}
					backArrowBtn(1);
					mlWalletLogout();
				}
				
				public void sendMoneyAddRecipient_STB_TC_03() throws Exception {
					HeaderChildNode("Send Money to any ML Branch");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("New_Branch_Verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					explicitWaitVisibility(SendTransferPage.objSendMoney, 10);
					verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
					if (verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
						click(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
						explicitWaitVisible(SendTransferPage.objKwartaPadala, 10);
						verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
						addRecipient();
						type(SendTransferPage.objSearchRecipient, prop.getproperty("Last_Name"), "Search Recipient Text Field");
						if (verifyElementPresent(SendTransferPage.objSelectLastName,  getTextVal(SendTransferPage.objSelectLastName, "Recipient"))) {
							logger.info("STB_TC_03, The Added Recipient is displayed in Saved Recipient Page");
							extentLoggerPass("STB_TC_03", "STB_TC_03, The Added Recipient is displayed in Saved Recipient Page");
							System.out.println("-----------------------------------------------------------");
						}
					}
					backArrowBtn(3);
					mlWalletLogout();
				}

				
				
				public void sendMoneyContactDuplicate_STB_TC_04() throws Exception {
					HeaderChildNode("Send Money Contact Duplicate");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("New_Branch_Verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					explicitWaitVisibility(SendTransferPage.objSendMoney, 10);
					verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
					explicitWaitVisibility(SendTransferPage.objToAnyMLBranch, 10);
					if (verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
						click(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
						explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
						verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
						addRecipient();
						waitTime(2000);
						if (verifyElementPresent(SendTransferPage.objContactAlreadyExistMsg, getTextVal(SendTransferPage.objContactAlreadyExistMsg, "Error Message"))) {
							String sContactDuplicatePopupMsg = getText(SendTransferPage.objContactAlreadyExistMsg);
							String sExpectedPopupMsg = "Contact already exists.";
							assertionValidation(sContactDuplicatePopupMsg, sExpectedPopupMsg);
							click(MLWalletCashOutPage.objOKBtnOne,"Ok Button");
							logger.info("STB_TC_04, Contact already exists popup message Validated");
							extentLoggerPass("STB_TC_04", "STB_TC_04, Contact already exists popup message Validated");
							System.out.println("-----------------------------------------------------------");
						}
					}
					backArrowBtn(4);
					mlWalletLogout();
				}
				
				
				
				public void sendMoneyDeleteRecipient_STB_TC_05() throws Exception {
					HeaderChildNode("Send Money to any ML Branch");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("New_Branch_Verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
					explicitWaitVisibility(SendTransferPage.objSendMoney, 10);
					if (verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
						click(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
						selectSavedRecipient();
						click(SendTransferPage.objDeleteRecipient, getTextVal(SendTransferPage.objDeleteRecipient, "Button"));
						explicitWaitVisibility(SendTransferPage.objPopupMsg, 20);
						verifyElementPresent(SendTransferPage.objPopupMsg, getTextVal(SendTransferPage.objPopupMsg, "Pop Up message"));
						String sDeleteConfirmationPopup = getText(SendTransferPage.objPopupMsg);
						String sExceptedMsg = "Are you sure you want to remove this saved recipient?";
						assertionValidation(sDeleteConfirmationPopup, sExceptedMsg);
						click(SendTransferPage.objRemoveBtn, getTextVal(SendTransferPage.objRemoveBtn, "Button"));
						waitTime(3000);
						if (verifyElementNotPresent(SendTransferPage.objSelectLastName, 5)) {
							logger.info("STB_TC_05, Saved Recipient from Saved Recipients page not got deleted Successfully");
						} else {
							logger.info("STB_TC_05, Saved Recipient from Saved Recipients page deleted Successfully");
							extentLoggerPass("STB_TC_05", "STB_TC_05, Saved Recipient from Saved Recipients page deleted Successfully");
							System.out.println("-----------------------------------------------------------");
						}

					}
					backArrowBtn(3);
					mlWalletLogout();

				}
				
				
				public void sendMoneyEditRecipient_STB_TC_06() throws Exception {
					HeaderChildNode("Send Money to any ML Branch");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("New_Branch_Verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
					explicitWaitVisibility(SendTransferPage.objToAnyMLBranch, 10);
					if (verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
						click(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
						selectSavedRecipient();
						click(SendTransferPage.objEditRecipient, getTextVal(SendTransferPage.objEditRecipient, "Button"));
						type(SendTransferPage.objEditRecipientLastName, prop.getproperty("Edited_Last_name"), "Last Name Text Field");
						click(SendTransferPage.ObjSaveRecipient, getTextVal(SendTransferPage.ObjSaveRecipient, "Button"));
						
						type(SendTransferPage.objSearchRecipient, prop.getproperty("Edited_Last_name"), "Search Recipient Text Field");
						if (verifyElementPresent(SendTransferPage.objSelectLastName, getTextVal(SendTransferPage.objSelectLastName, "Recipient"))) {
							logger.info("STB_TC_06, Successfully edited the Saved Recipient");
							extentLoggerPass("STB_TC_06", "STB_TC_06, Successfully edited the Saved Recipient");
							System.out.println("-----------------------------------------------------------");
						}
					}
					backArrowBtn(3);
					mlWalletLogout();
				}
				
				
				
				public void sendMoneyInvalidDetails_STB_TC_07() throws Exception {
					HeaderChildNode("Send Money Invalid Bank Details");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("New_Branch_Verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
					if (verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
						click(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
						explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
						verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
						type(SendTransferPage.objFirstname, prop.getproperty("Invalid_First_Name"), "First Name Text Field");
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						if (verifyElementPresent(SendTransferPage.objFirstNameErrorMsg, getTextVal(SendTransferPage.objFirstNameErrorMsg, "Error Message"))) {
							String sFirstNameErrorMsg = getText(SendTransferPage.objFirstNameErrorMsg);
							String sExpectedMsg = "First name must only contain letters and spaces";
							assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
						}
						
						
						click(SendTransferPage.objFirstname1, "First Name Text Field");
						clearField(SendTransferPage.objFirstname1, "First Name Text Field");
						type(SendTransferPage.objFirstname, prop.getproperty("First_Name"), "First Name Text Field");
						

						type(SendTransferPage.objMiddleName, prop.getproperty("Invalid_Middle_Name"), "Middle Name Text Field");
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						if (verifyElementPresent(SendTransferPage.objMiddleNameErrorMsg, getTextVal(SendTransferPage.objMiddleNameErrorMsg, "Error Message"))) {
							String sFirstNameErrorMsg = getText(SendTransferPage.objMiddleNameErrorMsg);
							String sExpectedMsg = "Middle name must only contain letters and spaces";
							assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
						}
						click(SendTransferPage.objCheckBox, "Check Box");
						Swipe("UP", 1);

						type(SendTransferPage.objLastName, prop.getproperty("Invalid_Last_Name"), "Last Name Text Field");
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						if (verifyElementPresent(SendTransferPage.objLastNameErrorMsg, getTextVal(SendTransferPage.objLastNameErrorMsg, "Error Message"))) {
							String sFirstNameErrorMsg = getText(SendTransferPage.objLastNameErrorMsg);
							String sExpectedMsg = "Last name must only contain letters and spaces";
							assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
						}
						
						
						click(SendTransferPage.objLastName1, "Last Name Text Field");
						clearField(SendTransferPage.objLastName1, "Last Name Text Field");
						type(SendTransferPage.objLastName, prop.getproperty("Last_Name"), "Last Name Text Field");


						type(SendTransferPage.objMobileNumber, prop.getproperty("Invalid_MobileNumber"), "Mobile Number Text Field");
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						if (verifyElementPresent(SendTransferPage.objMobileNumberErrorMsg, getTextVal(SendTransferPage.objMobileNumberErrorMsg, "Error Message"))) {
							String sFirstNameErrorMsg = getText(SendTransferPage.objMobileNumberErrorMsg);
							String sExpectedMsg = "Mobile number is invalid";
							assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
						}
						
						click(SendTransferPage.objMobileNumber1, "Last Name Text Field");
						clearField(SendTransferPage.objMobileNumber1, "Mobile Number Text Field");
						type(SendTransferPage.objMobileNumber, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));

						explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
						if (verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "page"))) {
							logger.info("STB_TC_07, Prompt msg for Receiver's Details Invalid is validated");
							extentLoggerPass("STB_TC_07", "STB_TC_07, Prompt msg for Receiver's Details Invalid is validated");
							System.out.println("-----------------------------------------------------------");
						}
					}
					backArrowBtn(3);
					mlWalletLogout();
				}
				
				
				
				

				public void sendMoneyRequiredDetails_STB_TC_08() throws Exception {
					HeaderChildNode("Send Money Invalid Bank Details");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("New_Branch_Verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					explicitWaitVisibility(SendTransferPage.objSendMoney, 10);
					verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
					if (verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
						click(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
						explicitWaitVisible(SendTransferPage.objKwartaPadala, 10);
						verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						explicitWaitVisible(SendTransferPage.objFirstNameRequiredMsg, 10);
						if (verifyElementPresent(SendTransferPage.objFirstNameRequiredMsg, getTextVal(SendTransferPage.objFirstNameRequiredMsg, "Error Message"))) {
							String sFirstNameErrorMsg = getText(SendTransferPage.objFirstNameRequiredMsg);
							String sExpectedMsg = "First name is required";
							assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
						}
						hideKeyboard();
						type(SendTransferPage.objFirstname, prop.getproperty("First_Name"), "First Name Text Field");
						//type(SendTransferPage.objFirstname, prop.getproperty("First_Name"), "First Name Text Field");
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						if (verifyElementPresent(SendTransferPage.objMiddleNameRequiredMsg, getTextVal(SendTransferPage.objMiddleNameRequiredMsg, "Error Message"))) {
							String sMiddleNameRequiredMsg = getText(SendTransferPage.objMiddleNameRequiredMsg);
							String sExpectedMsg = "Middle name is required";
							assertionValidation(sMiddleNameRequiredMsg, sExpectedMsg);
						}
						waitTime(3000);
						type(SendTransferPage.objMiddleName, prop.getproperty("Middle_Name"), "Middle Name Text Field");
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						if (verifyElementPresent(SendTransferPage.objLastNameRequiredMsg, getTextVal(SendTransferPage.objLastNameRequiredMsg, "Error Message"))) {
							String sLastNameRequiredMsg = getText(SendTransferPage.objLastNameRequiredMsg);
							String sExpectedMsg = "Last name is required";
							assertionValidation(sLastNameRequiredMsg, sExpectedMsg);
						}
						waitTime(3000);
						type(SendTransferPage.objLastName, prop.getproperty("Last_Name"), "Last Name Text Field");
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						if (verifyElementPresent(SendTransferPage.objMobileNumberRequiredMsg, getTextVal(SendTransferPage.objMobileNumberRequiredMsg, "Error Message"))) {
							String sMobileNumberRequiredMsg = getText(SendTransferPage.objMobileNumberRequiredMsg);
							String sExpectedMsg = "Mobile number is required";
							assertionValidation(sMobileNumberRequiredMsg, sExpectedMsg);
						}
						type(SendTransferPage.objMobileNumber, prop.getproperty("Branch_Verified"), "Last Name Text Field");
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
						if (verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "page"))) {
							logger.info("STB_TC_08, Prompt msg for Receiver's Details required is validated");
							extentLoggerPass("STB_TC_08", "STB_TC_08, Prompt msg for Receiver's Details required is validated");
							System.out.println("-----------------------------------------------------------");
						}
					}
					backArrowBtn(3);
					mlWalletLogout();
				}

			
				public void sendMoneyInvalidAmount_STB_TC_09(String Amount) throws Exception {
					HeaderChildNode("Send Money to any ML Branch");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("New_Branch_Verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					explicitWaitVisibility(SendTransferPage.objSendMoney, 10);
					verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
					if (verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
						click(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
						enterMLBranchDetails();
						explicitWaitVisible(SendTransferPage.objKwartaPadala, 10);
						verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
						type(SendTransferPage.objAmountTxtField, Amount, "Amount text Field");
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						explicitWaitVisibility(SendTransferPage.objInvalidAmountMsg, 10);
						if (verifyElementPresent(SendTransferPage.objInvalidAmountMsg, getTextVal(SendTransferPage.objInvalidAmountMsg, "Error Message"))) {
							String sInvalidAmountErrorMsg = getText(SendTransferPage.objInvalidAmountMsg);
							String sExpectedErrorMsg = "The amount should not be less than 1";
							assertionValidation(sInvalidAmountErrorMsg, sExpectedErrorMsg);
							logger.info("STB_TC_09, The amount should not be less than 1 - Error Message is validated");
							extentLoggerPass("STB_TC_09", "STB_TC_09, The amount should not be less than 1 - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
					}
					backArrowBtn(3);
					mlWalletLogout();
				}

				public void sendMoneyInsufficientAmount_STB_TC_10() throws Exception {
					HeaderChildNode("Send Money to any ML Branch");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("Fully_Verified"));
					click(SendTransferPage.objSendTransferBtn,
							getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					explicitWaitVisibility(SendTransferPage.objSendMoney, 10);
					verifyElementPresent(SendTransferPage.objSendMoney,
							getTextVal(SendTransferPage.objSendMoney, "Page"));
					if (verifyElementPresent(SendTransferPage.objToAnyMLBranch,
							getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
						click(SendTransferPage.objToAnyMLBranch,
								getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
						enterMLBranchDetails();
						enterAmountToKwartaPadala("35000");
						waitTime(4000);
						if (verifyElementPresent(SendTransferPage.objInsufficientAmountMsg,
								getTextVal(SendTransferPage.objInsufficientAmountMsg, "Error Message"))) {
							String sInsufficientBalanceErrorMsg = getText(SendTransferPage.objInsufficientAmountMsg);
							String sExpectedErrorMsg = "There is insufficient balance to proceed with this transaction. Please try again.";
							assertionValidation(sInsufficientBalanceErrorMsg, sExpectedErrorMsg);
							click(MLWalletCashOutPage.objOKBtnOne, "OK Button");
							logger.info("STB_TC_10, Insufficient Balance - Error Message is validated");
							extentLoggerPass("STB_TC_10",
									"STB_TC_10, Insufficient Balance - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
					}
					backArrowBtn(4);
					mlWalletLogout1();
				}


				public void sendMoneyMaximumAmount_STB_TC_12() throws Exception {
					HeaderChildNode("Send Money to any ML Branch");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("Fully_verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					explicitWaitVisibility(SendTransferPage.objSendMoney, 10);
					verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
					if (verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
						click(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
						enterMLBranchDetails();
						enterAmountToKwartaPadala("100000");
						waitTime(2000);
						if (verifyElementPresent(SendTransferPage.objMaxLimitErrorMsg, getTextVal(SendTransferPage.objMaxLimitErrorMsg, "Error Message"))) {
							String sMaximumLimitErrorMsg = getText(SendTransferPage.objMaxLimitErrorMsg);
							String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
							assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
							click(MLWalletCashOutPage.objOKBtnOne, "OK Button");
							logger.info("STB_TC_12, The maximum send money per transaction - Error Message is validated");
							extentLoggerPass("STB_TC_12", "STB_TC_12, The maximum send money per transaction - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
					}
					backArrowBtn(4);
					mlWalletLogout1();
				}

			//================================= Phase 2 ==================================================================//


		 	public void sendTransferUIValidation_STB_TC_13() throws Exception {
					HeaderChildNode("Send/Transfer page UI Validation");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("Branch_Verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					explicitWaitVisibility(SendTransferPage.objSendMoney, 20);
					if (verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"))) {
						verifyElementPresent(SendTransferPage.objSendWalletOptions, getTextVal(SendTransferPage.objSendWalletOptions, "Header"));
						verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "option"));
						verifyElementPresent(SendTransferPage.objToAMLWalletUser, getTextVal(SendTransferPage.objToAMLWalletUser, "option"));
						logger.info("STB_TC_13, Send/Transfer page UI Validated");
						extentLoggerPass("STB_TC_13", "STB_TC_13, Send/Transfer page UI Validated");
						System.out.println("-----------------------------------------------------------");
					}
					backArrowBtn(1);
					mlWalletLogout();
				}

				public void sendMoneyToBranchUIValidation_STB_TC_14() throws Exception {
					HeaderChildNode("Send Money to ML Branch page UI Validation");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("Branch_Verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					explicitWaitVisibility(SendTransferPage.objToAnyMLBranch, 10);
					verifyElementPresentAndClick(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Option"));
					explicitWaitVisible(SendTransferPage.objKwartaPadala, 10);
					if (verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"))) {
						verifyElementPresent(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"));
						verifyElementPresent(SendTransferPage.objFirstname, "First Name Input Field");
						verifyElementPresent(SendTransferPage.objMiddleName, "Middle Name Input Field");
						verifyElementPresent(SendTransferPage.objCheckBox, "Check box to Receiver legally does not have middle Name");
						verifyElementPresent(SendTransferPage.objLastName, "Last Name Input Field");
						verifyElementPresent(SendTransferPage.objMobileNumber, "Mobile Number Input Field");
						verifyElementPresent(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						logger.info("STB_TC_14, Send Money to ML Branch page UI Validation");
						extentLoggerPass("STB_TC_14", "STB_TC_14, Send Money to ML Branch page UI Validation");
						System.out.println("-----------------------------------------------------------");
					}
					backArrowBtn(2);
					mlWalletLogout();
				}

					public void sendMoneyToBranchSaveRecipientPageUIValidation_STB_TC_15() throws Exception {
					HeaderChildNode("Send Money To Branch Save Recipient Page UI Validation");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("Branch_Verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					explicitWaitVisibility(SendTransferPage.objToAnyMLBranch, 10);
					verifyElementPresentAndClick(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Option"));
					explicitWaitVisible(SendTransferPage.objKwartaPadala, 10);
					verifyElementPresentAndClick(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"));
					if (verifyElementPresent(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Page"))) {
						verifyElementPresent(SendTransferPage.objAddRecipient, getTextVal(SendTransferPage.objAddRecipient, "Button"));
						verifyElementPresent(SendTransferPage.objSelectRecipient, getTextVal(SendTransferPage.objSelectRecipient, "Header"));
						verifyElementPresent(SendTransferPage.objSearchRecipient, "Search Recipient Input Field");
						waitTime(2000);
						if (verifyElementDisplayed(SendTransferPage.objSavedRecipientsList)) {
							List<WebElement> values = findElements(SendTransferPage.objSavedRecipientsList);
							for (int i = 0; i < values.size(); i++) {
								String savedRecipientName = values.get(i).getText();
								logger.info("Saved Recipient : " + savedRecipientName + " is displayed");
								extentLogger(" ", "Saved Recipient : " + savedRecipientName + " is displayed");
							}
						} else if (verifyElementPresent(SendTransferPage.objNoRecentTransactionTxt, getTextVal(SendTransferPage.objNoRecentTransactionTxt, "Text"))) {
							logger.info("No Saved Recipients are present");
						}
						logger.info("STB_TC_15, Send Money To Branch Save Recipient Page UI Validated");
						extentLoggerPass("STB_TC_15", "STB_TC_15, Send Money To Branch Save Recipient Page UI Validated");
						System.out.println("-----------------------------------------------------------");
					}
					backArrowBtn(3);
					mlWalletLogout();
				}


				public void sendMoneyToBranchSuccessUIValidation_STB_TC_16() throws Exception {
					HeaderChildNode("Send Money To Branch Success UI Validation");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("Branch_Verified"));
					click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
					explicitWaitVisibility(SendTransferPage.objSendMoney, 10);
					verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
					if (verifyElementPresent(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"))) {
						click(SendTransferPage.objToAnyMLBranch, getTextVal(SendTransferPage.objToAnyMLBranch, "Button"));
						enterMLBranchDetails();
						enterAmountToKwartaPadala("100");
						enterOTP(prop.getproperty("Valid_OTP"));
						explicitWaitVisibility(SendTransferPage.objSendMoneySuccessful, 10);
						if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
							verifyElementPresent(SendTransferPage.objPHPAmount, getTextVal(SendTransferPage.objPHPAmount, "Amount"));
							verifyElementPresent(SendTransferPage.objDate, getTextVal(SendTransferPage.objDate, "Date"));
							verifyElementPresent(SendTransferPage.objReferenceNumber, getTextVal(SendTransferPage.objReferenceNumber, "Reference Number"));
							verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Header"));
							verifyElementPresent(SendTransferPage.objReceiverName, getTextVal(SendTransferPage.objReceiverName, "Receiver's Name"));
							verifyElementPresent(SendTransferPage.objReceiverMobileNo, getTextVal(SendTransferPage.objReceiverMobileNo, "Receiver's Mobile Number"));
							verifyElementPresent(SendTransferPage.objPaymentMethod, getTextVal(SendTransferPage.objPaymentMethod, "Payment Method"));
							verifyElementPresent(SendTransferPage.objAmount, getTextVal(SendTransferPage.objAmount, "Amount"));
							verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"));
							verifyElementPresent(SendTransferPage.objTotalAmount, getTextVal(SendTransferPage.objTotalAmount, "Total Amount"));
							Swipe("UP", 1);
							verifyElementPresent(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
							verifyElementPresent(SendTransferPage.objNewTransaction, getTextVal(SendTransferPage.objNewTransaction, "Button"));
							click(SendTransferPage.objBackToHomeBtn, "Back To Home Button");
							logger.info("STB_TC_16, Send Money To Branch Success page UI Validated");
							extentLoggerPass("STB_TC_16", "STB_TC_16, Send Money To Branch Success page UI Validated");
							System.out.println("-----------------------------------------------------------");
						}
					}
					
					mlWalletLogout();
				}

				    public void sendMoneyToBranchConfirmDetailsPageUIValidation_STB_TC_17(String nAmount) throws Exception {
					HeaderChildNode("Send Money To Branch Confirm Details Page UI Validation");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToAnyMLBranch();
					enterMLBranchDetails();
					explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
					verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
					type(SendTransferPage.objAmountTxtField, nAmount, "Amount text Field");
					click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
					click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
					waitTime(3000);
					verifyElementPresent(SendTransferPage.objSelectPaymentMethod, getTextVal(SendTransferPage.objSelectPaymentMethod, "Page"));
					waitTime(3000);
					click(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objConfirmDetails, getTextVal(SendTransferPage.objConfirmDetails, "Page"))) {
						verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Header"));
						verifyElementPresent(SendTransferPage.objReceiverName, getTextVal(SendTransferPage.objReceiverName, "Receiver's Name"));
						verifyElementPresent(SendTransferPage.objReceiverMobileNo, getTextVal(SendTransferPage.objReceiverMobileNo, "Receiver's Mobile Number"));
						verifyElementPresent(SendTransferPage.objPaymentMethod, getTextVal(SendTransferPage.objPaymentMethod, "Payment Method"));
						verifyElementPresent(SendTransferPage.objAmount, getTextVal(SendTransferPage.objAmount, "Amount"));
						verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"));
						verifyElementPresent(SendTransferPage.objTotalAmount, getTextVal(SendTransferPage.objTotalAmount, "Total Amount"));
						verifyElementPresent(SendTransferPage.objConfirmBtn, getTextVal(SendTransferPage.objConfirmBtn, "Button"));
						logger.info("STB_TC_17, Send Money To Branch Confirm Details Page UI Validated");
						extentLoggerPass("STB_TC_17", "STB_TC_17, Send Money To Branch Confirm Details Page UI Validated");
						System.out.println("-----------------------------------------------------------");
					}
					backArrowBtn(5);
					mlWalletLogout();
				}

				public void sendMoneyToBranchSelectPaymentMethodPageUIValidation_STB_TC_18(String nAmount) throws Exception {
					HeaderChildNode("Send Money To Branch Select Payment Method Page UI Validation");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToAnyMLBranch();
					enterMLBranchDetails();
					explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
					verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"));
					type(SendTransferPage.objAmountTxtField, nAmount, "Amount text Field");
					click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
					click(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
					explicitWaitVisibility(SendTransferPage.objSelectPaymentMethod, 10);
					if (verifyElementPresent(SendTransferPage.objSelectPaymentMethod, getTextVal(SendTransferPage.objSelectPaymentMethod, "Page"))) {
						verifyElementPresent(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
						//verifyElementPresent(SendTransferPage.objAvailableBalance, getTextVal(SendTransferPage.objAvailableBalance, "Available PHP"));
						logger.info("STB_TC_18, Send Money To Branch Select Payment Method Page UI Validated");
						extentLoggerPass("STB_TC_18", "STB_TC_18, Send Money To Branch Select Payment Method Page UI Validated");
						System.out.println("-----------------------------------------------------------");
					}
					backArrowBtn(4);
					mlWalletLogout();
				}

				public void sendMoneyToBranchEnterAmountPageUIValidation_STB_TC_19() throws Exception {
					HeaderChildNode("Send Money To Branch Enter Amount Page UI Validation");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToAnyMLBranch();
					enterMLBranchDetails();
					explicitWaitVisible(SendTransferPage.objKwartaPadala, 5);
					if (verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Page"))) {
						verifyElementPresent(SendTransferPage.objAmountToSend, getTextVal(SendTransferPage.objAmountToSend, "Header"));
						verifyElementPresent(SendTransferPage.objAmountTxtField, "Amount Input Field");
						verifyElementPresent(SendTransferPage.objNextBtn2, getTextVal(SendTransferPage.objNextBtn2, "Button"));
						logger.info("STB_TC_19, Send Money To Branch Enter Amount Page UI Validated");
						extentLoggerPass("STB_TC_19", "STB_TC_19, Send Money To Branch Enter Amount Page UI Validated");
						System.out.println("-----------------------------------------------------------");
					}
					backArrowBtn(3);
					mlWalletLogout();
				}

					public void kwartaPadalaTransactionDetailsUIValidation_STB_TC_20() throws Exception {
					HeaderChildNode("Kwarta Padala Transaction Details UI Validation");
					sendMoneyToMLBranch_STB_TC_01("false");
					explicitWaitVisibility(SendTransferPage.objTransactionDetails, 10);
					if (verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Header"))) {
						verifyElementPresent(SendTransferPage.objKwartaPadala, getTextVal(SendTransferPage.objKwartaPadala, "Text"));
						verifyElementPresent(SendTransferPage.objKwartaPadalaDate, getTextVal(SendTransferPage.objKwartaPadalaDate, "Kwarta Padala Date"));
						verifyElementPresent(SendTransferPage.objReceiverName, getTextVal(SendTransferPage.objReceiverName, "Receiver's Name"));
						verifyElementPresent(SendTransferPage.objReceiverMobileNo, getTextVal(SendTransferPage.objReceiverMobileNo, "Receiver's Mobile Number"));
						verifyElementPresent(SendTransferPage.objPaymentMethod, getTextVal(SendTransferPage.objPaymentMethod, "Payment Method"));
						verifyElementPresent(SendTransferPage.objAmount, getTextVal(SendTransferPage.objAmount, "Amount"));
						verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"));
						verifyElementPresent(SendTransferPage.objTotalAmount, getTextVal(SendTransferPage.objTotalAmount, "Total Amount"));
						logger.info("STB_TC_20, Kwarta Padala Transaction Details page UI Validated");
						extentLoggerPass("STB_TC_20", "STB_TC_20, Kwarta Padala Transaction Details page UI Validated");
						System.out.println("-----------------------------------------------------------");
					}
					backArrowBtn(1);
					mlWalletLogout();
				}

				public void sendMoneyToBranchAddRecipientPageUIValidation_STB_TC_21() throws Exception {
					HeaderChildNode("Send Money To Branch Add Recipient Page UI Validation");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToAnyMLBranch();
					explicitWaitVisibility(SendTransferPage.objSavedRecipients, 10);
					if (verifyElementPresent(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"))) {
						explicitWaitVisibility(SendTransferPage.objSavedRecipients, 10);
						click(SendTransferPage.objSavedRecipients, getTextVal(SendTransferPage.objSavedRecipients, "Button"));
						waitTime(3000);
						click(SendTransferPage.objAddRecipient, getTextVal(SendTransferPage.objAddRecipient, "Button"));
						explicitWaitVisible(SendTransferPage.objAddRecipient, 10);
						if (verifyElementPresent(SendTransferPage.objAddRecipient, getTextVal(SendTransferPage.objAddRecipient, "Page"))) {
							verifyElementPresent(SendTransferPage.objFirstname, "First Name Text Field");
							verifyElementPresent(SendTransferPage.objMiddleName, "Middle Name Text Field");
							verifyElementPresent(SendTransferPage.objCheckBox, "Check Box");
							verifyElementPresent(SendTransferPage.objLastName, "Last Name Text Field");
							verifyElementPresent(SendTransferPage.objMobileNumber, "Last Name Text Field");
							verifyElementPresent(SendTransferPage.objNickName, "Nick Name Text Field");
							verifyElementPresent(SendTransferPage.ObjSaveRecipient, getTextVal(SendTransferPage.ObjSaveRecipient, "Button"));
							logger.info("STB_TC_21, Send Money To Branch Add Recipient Page UI Validated");
							extentLoggerPass("STB_TC_21", "STB_TC_21, Send Money To Branch Add Recipient Page UI Validated");
							System.out.println("-----------------------------------------------------------");
						}
					}
					backArrowBtn(4);
					mlWalletLogout();
				}
				
				
				public void sendMoneyToMLBranchBuyerTierAccount_STB_TC_22() throws Exception {
					HeaderChildNode("Send Money To ML Branch, Buyer Tier Account");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToAnyMLBranch(prop.getproperty("Buyer_Tier"));
					enterMLBranchDetails();
					enterAmountToKwartaPadala("100");
					waitTime(2000);
					if (verifyElementPresent(SendTransferPage.objErrorMsg, getTextVal(SendTransferPage.objErrorMsg, "Error Message"))) {
						String sMaximumLimitErrorMsg = getText(SendTransferPage.objErrorMsg);
						String sExpectedErrorMsg = "Send Money is not allowed for customers at this verification level. Please upgrade your account to use this service.";
						assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);				
						verifyElementPresent(SendTransferPage.objUpgradeNowBtn, getTextVal(SendTransferPage.objUpgradeNowBtn, "Button"));
						click(SendTransferPage.objUpgradeNowBtn, getTextVal(SendTransferPage.objUpgradeNowBtn, "Button"));
						logger.info("STB_TC_22, Send Money is not allowed for customers at this Buyer tier - Error Message is validated");
						extentLoggerPass("STB_TC_22", "STB_TC_22, Send Money is not allowed for customers at this Buyer tier  - Error Message is validated");
						System.out.println("-----------------------------------------------------------");
					}
//					backArrowBtn(6);
//					Getdriver
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchSemiVerifiedTierAccount_STB_TC_23() throws Exception {
					HeaderChildNode("Send Money To ML Branch, Semi verified Tier Account");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToAnyMLBranch(prop.getproperty("Semi_Verified_One"));
					enterMLBranchDetails();
					enterAmountToKwartaPadala("100");
					enterOTP(prop.getproperty("Valid_OTP"));
					waitTime(7000);
					if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
						verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Header"));
						verifyElementPresent(SendTransferPage.objReferenceNumber, getTextVal(SendTransferPage.objReferenceNumber, "Reference Number"));
						logger.info("STB_TC_23, Send Money To ML Branch, Semi verified Tier Account transaction validated");
						extentLoggerPass("STB_TC_23", "STB_TC_23, Send Money To ML Branch, Semi verified Tier Account transaction validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchBranchVerifiedAccount_STB_TC_24() throws Exception {
					HeaderChildNode("Send Money To ML Branch, Branch verified Tier Account");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
					enterMLBranchDetails();
					enterAmountToKwartaPadala("100");
					enterOTP(prop.getproperty("Valid_OTP"));
					waitTime(10000);
					if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
						verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Header"));
						verifyElementPresent(SendTransferPage.objReferenceNumber, getTextVal(SendTransferPage.objReferenceNumber, "Reference Number"));
						logger.info("STB_TC_24, Send Money To ML Branch, Branch verified Tier Account transaction validated");
						extentLoggerPass("STB_TC_24", "STB_TC_24, Send Money To ML Branch, Branch verified Tier Account transaction validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}


				public void sendMoneyToMLBranchFullyVerifiedAccount_STB_TC_25() throws Exception {
					HeaderChildNode("Send Money To ML Branch, Branch verified Tier Account");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToAnyMLBranch(prop.getproperty("Fully_verified"));
					enterMLBranchDetails();
					enterAmountToKwartaPadala("100");
					enterOTP(prop.getproperty("Valid_OTP"));
					waitTime(10000);
					if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
						verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Header"));
						verifyElementPresent(SendTransferPage.objReferenceNumber, getTextVal(SendTransferPage.objReferenceNumber, "Reference Number"));
						logger.info("STB_TC_25, Send Money To ML Branch, Branch verified Tier Account transaction validated");
						extentLoggerPass("STB_TC_25", "STB_TC_25, Send Money To ML Branch, Branch verified Tier Account transaction validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchSemiVerifiedTierAccountMaxAmount_STB_TC_26() throws Exception {
					HeaderChildNode("Send Money To ML Branch, Semi verified Tier Account");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToAnyMLBranch(prop.getproperty("Semi_Verified"));
					enterMLBranchDetails();
					enterAmountToKwartaPadala("20000");
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objSemiMaxLimit, getTextVal(SendTransferPage.objSemiMaxLimit, "Error Message"))) {
						String sMaximumLimitErrorMsg = getText(SendTransferPage.objSemiMaxLimit);
						String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P10,000.00. Please try again.";
						assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
						verifyElementPresent(SendTransferPage.objUpgradeNowBtn, getTextVal(SendTransferPage.objUpgradeNowBtn, "Button"));
						logger.info("STB_TC_26, Send Money To ML Branch, Semi verified Tier Account Maximum Transaction - Error Message is validated");
						extentLoggerPass("STB_TC_26", "STB_TC_26, Send Money To ML Branch, Semi verified Tier Account Maximum Transaction  - Error Message is validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}


				public void sendMoneyToMLBranchBranchVerifiedTierAccountMaxAmount_STB_TC_29() throws Exception {
					HeaderChildNode("Send Money To ML Branch, Branch verified Tier Account");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
					enterMLBranchDetails();
					enterAmountToKwartaPadala("60000");
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objMaxLimitErrorMsg, getTextVal(SendTransferPage.objMaxLimitErrorMsg, "Error Message"))) {
						String sMaximumLimitErrorMsg = getText(SendTransferPage.objMaxLimitErrorMsg);
						String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
						assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
						verifyElementPresent(SendTransferPage.objOkBtn, getTextVal(SendTransferPage.objOkBtn, "Button"));
						logger.info("STB_TC_29, Send Money To ML Branch, Branch verified Tier Account Maximum Transaction - Error Message is validated");
						extentLoggerPass("STB_TC_29", "STB_TC_29, Send Money To ML Branch, Branch verified Tier Account Maximum Transaction  - Error Message is validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchFullyVerifiedTierAccountMaxAmount_STB_TC_32() throws Exception {
					HeaderChildNode("Send Money To ML Branch, Fully verified Tier Account");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToAnyMLBranch(prop.getproperty("Fully_verified"));
					enterMLBranchDetails();
					enterAmountToKwartaPadala("60000");
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objMaxLimitErrorMsg, getTextVal(SendTransferPage.objMaxLimitErrorMsg, "Error Message"))) {
						String sMaximumLimitErrorMsg = getText(SendTransferPage.objMaxLimitErrorMsg);
						String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
						assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
						verifyElementPresent(SendTransferPage.objOkBtn, getTextVal(SendTransferPage.objOkBtn, "Button"));
						logger.info("STB_TC_32, Send Money To ML Branch, Fully verified Tier Account Maximum Transaction - Error Message is validated");
						extentLoggerPass("STB_TC_32", "STB_TC_32, Send Money To ML Branch, Fully verified Tier Account Maximum Transaction  - Error Message is validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void kwartaPadalaRates_STB_TC_35() throws Exception {
					HeaderChildNode("Kwarta Padala Rates");
					waitTime(2000);
					changeNumberPage();
					clearField();
					mlWalletLogin(prop.getproperty("Branch_Verified"));
					verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
					verifyElementPresentAndClick(MLWalletHomePage.objKwartaPadalaRatesBtn, "Kwarta Padala Rates");
					waitTime(3000);
					if (verifyElementPresent(MLWalletHomePage.objKwartaPadalaRates, "Kwarta Padala Rates")) {
						List<WebElement> values = findElements(MLWalletHomePage.objKwartaPadalaRates);
						for (int i = 0; i < values.size(); i++) {
							if (i % 2 != 0) {
								String sRates = values.get(i).getText();
								logger.info("Rates : " + sRates + " is displayed");
								extentLogger(" ", "Rates : " + sRates + " is displayed");
							}
							if (i % 2 == 0) {
								String sAmountRange = values.get(i).getText();
								logger.info("Amount Range : " + sAmountRange + " is displayed");
								extentLogger(" ", "Amount Range : " + sAmountRange + " is displayed");
							}
						}
						logger.info("STB_TC_35, Kwarta Padala Rates validated");
						extentLoggerPass("STB_TC_35", "STB_TC_35, Kwarta Padala Rates validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}


				public void sendMoneyToMLBranchRatesValidationScenarioOne_STB_TC_36(String sAmount) throws Exception {
					HeaderChildNode("Send Money To ML Branch charged PHP0.50 for PHP0.01 to PHP50.00");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToMLBranchRatesValidation(sAmount);
					if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
						String sServiceFee = getText(SendTransferPage.objServiceFee);
						String sExceptedServiceFee = "Php 0.50";
						assertionValidation(sServiceFee, sExceptedServiceFee);
						logger.info("STB_TC_36, Send Money To ML Branch charged PHP0.50 for 0.01 to PHP50.00 validated");
						extentLoggerPass("STB_TC_36", "STB_TC_36, Send Money To ML Branch charged PHP0.50 for PHP0.01 to PHP50.00 validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchRatesValidationScenarioTwo_STB_TC_37(String sAmount) throws Exception {
					HeaderChildNode("Send Money To ML Branch charged PHP1.00 for PHP50.01 to PHP100.00");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToMLBranchRatesValidation(sAmount);
					waitTime(2000);
					if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
						String sServiceFee = getText(SendTransferPage.objServiceFee);
						String sExceptedServiceFee = "Php 1.00";
						assertionValidation(sServiceFee, sExceptedServiceFee);
						logger.info("STB_TC_36, Send Money To ML Branch charged PHP1.00 for PHP50.01 to PHP100.00 validated");
						extentLoggerPass("STB_TC_36", "STB_TC_36, Send Money To ML Branch charged PHP1.00 for PHP50.01 to PHP100.00 validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchRatesValidationScenarioThree_STB_TC_38(String sAmount) throws Exception {
					HeaderChildNode("Send Money To ML Branch charged PHP2.00 for PHP100.01 to PHP300.00");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToMLBranchRatesValidation(sAmount);
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
						String sServiceFee = getText(SendTransferPage.objServiceFee);
						String sExceptedServiceFee = "Php 2.00";
						assertionValidation(sServiceFee, sExceptedServiceFee);
						logger.info("STB_TC_38, Send Money To ML Branch charged PHP2.00 for PHP100.01 to PHP300.00 validated");
						extentLoggerPass("STB_TC_38", "STB_TC_38, Send Money To ML Branch charged PHP2.00 for PHP100.01 to PHP300.00 validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchRatesValidationScenarioFour_STB_TC_39(String sAmount) throws Exception {
					HeaderChildNode("Send Money To ML Branch charged PHP3.00 for PHP300.01 to PHP400.00");
					waitTime(2000);
					changeNumberPage();
					clearField();	
					sendMoneyToMLBranchRatesValidation(sAmount);
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
						String sServiceFee = getText(SendTransferPage.objServiceFee);
						String sExceptedServiceFee = "Php 3.00";
						assertionValidation(sServiceFee, sExceptedServiceFee);
						logger.info("STB_TC_39, Send Money To ML Branch charged PHP3.00 for PHP300.01 to PHP400.00 validated");
						extentLoggerPass("STB_TC_39", "STB_TC_39, Send Money To ML Branch charged PHP3.00 for PHP300.01 to PHP400.00 validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}


				public void sendMoneyToMLBranchRatesValidationScenarioFive_STB_TC_40(String sAmount) throws Exception {
					HeaderChildNode("Send Money To ML Branch charged PHP5.00 for PHP400.01 to PHP500.00");
					waitTime(2000);
					changeNumberPage();
					clearField();	
					sendMoneyToMLBranchRatesValidation(sAmount);
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
						String sServiceFee = getText(SendTransferPage.objServiceFee);
						String sExceptedServiceFee = "Php 5.00";
						assertionValidation(sServiceFee, sExceptedServiceFee);
						logger.info("STB_TC_40, Send Money To ML Branch charged PHP5.00 for PHP400.01 to PHP500.00 validated");
						extentLoggerPass("STB_TC_40", "STB_TC_40, Send Money To ML Branch charged PHP5.00 for PHP400.01 to PHP500.00 validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}


				public void sendMoneyToMLBranchRatesValidationScenarioSix_STB_TC_41(String sAmount) throws Exception {
					HeaderChildNode("Send Money To ML Branch charged PHP8.00 for PHP500.01 to PHP600.00");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToMLBranchRatesValidation(sAmount);
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
						String sServiceFee = getText(SendTransferPage.objServiceFee);
						String sExceptedServiceFee = "Php 8.00";
						assertionValidation(sServiceFee, sExceptedServiceFee);
						logger.info("STB_TC_41, Send Money To ML Branch charged PHP5.00 for PHP8.00 to PHP600.00 validated");
						extentLoggerPass("STB_TC_41", "STB_TC_41, Send Money To ML Branch charged PHP8.00 for PHP500.01 to PHP600.00 validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchRatesValidationScenarioSeven_STB_TC_42(String sAmount) throws Exception {
					HeaderChildNode("Send Money To ML Branch charged PHP10.00 for PHP600.01 to PHP700.00");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToMLBranchRatesValidation(sAmount);
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
						String sServiceFee = getText(SendTransferPage.objServiceFee);
						String sExceptedServiceFee = "Php 10.00";
						assertionValidation(sServiceFee, sExceptedServiceFee);
						logger.info("STB_TC_42, Send Money To ML Branch charged PHP10.00 for PHP600.01 to PHP700.00 validated");
						extentLoggerPass("STB_TC_42", "STB_TC_42, Send Money To ML Branch charged PHP10.00 for PHP600.01 to PHP700.00 validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}


				public void sendMoneyToMLBranchRatesValidationScenarioEight_STB_TC_43(String sAmount) throws Exception {
					HeaderChildNode("Send Money To ML Branch charged PHP12.00 for PHP700.01 to PHP900.00");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToMLBranchRatesValidation(sAmount);
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
						String sServiceFee = getText(SendTransferPage.objServiceFee);
						String sExceptedServiceFee = "Php 12.00";
						assertionValidation(sServiceFee, sExceptedServiceFee);
						logger.info("STB_TC_43, Send Money To ML Branch charged PHP12.00 for PHP700.01 to PHP900.00 validated");
						extentLoggerPass("STB_TC_43", "STB_TC_43 , Send Money To ML Branch charged PHP12.00 for PHP700.01 to PHP900.00 validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}


				public void sendMoneyToMLBranchRatesValidationScenarioNine_STB_TC_44(String sAmount) throws Exception {
					HeaderChildNode("Send Money To ML Branch charged PHP15.00 for PHP900.01 to PHP1000.00");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToMLBranchRatesValidation(sAmount);
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
						String sServiceFee = getText(SendTransferPage.objServiceFee);
						String sExceptedServiceFee = "Php 15.00";
						assertionValidation(sServiceFee, sExceptedServiceFee);
						logger.info("STB_TC_44, Send Money To ML Branch charged PHP15.00 for PHP900.01 to PHP1000.00 validated");
						extentLoggerPass("STB_TC_44", "STB_TC_44 , Send Money To ML Branch charged PHP15.00 for PHP900.01 to PHP1000.00 validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchRatesValidationScenarioTen_STB_TC_45(String sAmount) throws Exception {
					HeaderChildNode("Send Money To ML Branch charged PHP20.00 for PHP1000.01 to PHP1500.00");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToMLBranchRatesValidation(sAmount);
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
						String sServiceFee = getText(SendTransferPage.objServiceFee);
						String sExceptedServiceFee = "Php 20.00";
						assertionValidation(sServiceFee, sExceptedServiceFee);
						logger.info("STB_TC_45, Send Money To ML Branch charged PHP20.00 for PHP1000.01 to PHP1500.00 validated");
						extentLoggerPass("STB_TC_45", "STB_TC_45 , Send Money To ML Branch charged PHP20.00 for PHP1000.01 to PHP1500.00 validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchRatesValidationScenarioEleven_STB_TC_46(String sAmount) throws Exception {
					HeaderChildNode("Send Money To ML Branch charged PHP30.00 for PHP1500.01 to PHP2000.00");
					waitTime(2000);
					changeNumberPage();
					clearField();
					sendMoneyToMLBranchRatesValidation(sAmount);
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
						String sServiceFee = getText(SendTransferPage.objServiceFee);
						String sExceptedServiceFee = "Php 30.00";
						assertionValidation(sServiceFee, sExceptedServiceFee);
						logger.info("STB_TC_46, Send Money To ML Branch charged PHP30.00 for PHP1500.01 to PHP2000.00 validated");
						extentLoggerPass("STB_TC_46", "STB_TC_46 , Send Money To ML Branch charged PHP30.00 for PHP1500.01 to PHP2000.00 validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchRatesValidationScenarioTwelve_STB_TC_47(String sAmount) throws Exception {
					HeaderChildNode("Send Money To ML Branch charged PHP40.00 for PHP2000.01 to PHP2500.00");
					waitTime(2000);
					changeNumberPage();
					clearField();			
					sendMoneyToMLBranchRatesValidation(sAmount);
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
						String sServiceFee = getText(SendTransferPage.objServiceFee);
						String sExceptedServiceFee = "Php 40.00";
						assertionValidation(sServiceFee, sExceptedServiceFee);
						logger.info("STB_TC_47, Send Money To ML Branch charged PHP40.00 for PHP2000.01 to PHP2500.00 validated");
						extentLoggerPass("STB_TC_47", "STB_TC_47 , Send Money To ML Branch charged PHP40.00 for PHP2000.01 to PHP2500.00 validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}


				public void sendMoneyToMLBranchRatesValidationScenarioThirteen_STB_TC_48(String sAmount) throws Exception {
					HeaderChildNode("Send Money To ML Branch charged PHP500.00 for PHP2500.01 to PHP50000.00");
					waitTime(2000);
					changeNumberPage();
					clearField();
					waitTime(3000);
					sendMoneyToMLBranchRatesValidation(sAmount);
					if (verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"))) {
						String sServiceFee = getText(SendTransferPage.objServiceFee);
						String sExceptedServiceFee = "Php 500.00";
						assertionValidation(sServiceFee, sExceptedServiceFee);
						logger.info("STB_TC_48, Send Money To ML Branch charged PHP500.00 for PHP2500.01 to PHP50000.00 validated");
						extentLoggerPass("STB_TC_48", "STB_TC_48 , Send Money To ML Branch charged PHP500.00 for PHP2500.01 to PHP50000.00 validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

			

				public void sendMoneyToMLBranchTransactionValidationAfterMinimizingApp_STB_TC_59() throws Exception {
					HeaderChildNode("Send Money To ML Branch Transaction Validation After Minimizing App");
					waitTime(2000);
					changeNumberPage();
					clearField();
					waitTime(3000);
					sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
					enterMLBranchDetails();
					enterAmountToKwartaPadala("100");
					enterOTP(prop.getproperty("Valid_OTP"));
					getDriver().runAppInBackground(Duration.ofSeconds(5));
					waitTime(5000);
					logger.info("Application relaunched after 5 Seconds");
					if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
						logger.info("STB_TC_58, Send Money To ML Branch Transaction Validation After Minimizing App Validated");
						extentLoggerPass("STB_TC_58", "STB_TC_58, Send Money To ML Branch Transaction Validation After Minimizing App Validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchTransactionWithValidMLPin_STB_TC_65() throws Exception {
					HeaderChildNode("Send Money to any ML Branch Transaction With Valid ML Pin");
					waitTime(2000);
					changeNumberPage();
					clearField();
					waitTime(3000);
					sendMoneyToAnyMLBranch("9999999999");
					enterMLBranchDetails();
					enterAmountToKwartaPadala("100");
					handleMpin("1111", "Entered Mpin");
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
						verifyElementPresent(SendTransferPage.objPHPAmount, getTextVal(SendTransferPage.objPHPAmount, "Amount"));
						verifyElementPresent(SendTransferPage.objDate, getTextVal(SendTransferPage.objDate, "Date"));
						verifyElementPresent(SendTransferPage.objReferenceNumber, getTextVal(SendTransferPage.objReferenceNumber, "Reference Number"));
						String sReference = getText(SendTransferPage.objReferenceNumber);
						System.out.println(sReference);
						String sReferenceNumber = sReference.substring(9, 20);
						System.out.println(sReferenceNumber);
						Swipe("UP", 2);
						click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
						Thread.sleep(3000);
						Swipe("DOWN", 2);
						Swipe("UP", 1);
						verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
						verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
						click(MLWalletHomePage.objKwartaPadala, getTextVal(MLWalletHomePage.objKwartaPadala, "Text"));
						if (verifyElementPresent(SendTransferPage.objReferenceNumberInTransactionDetails, getTextVal(SendTransferPage.objReferenceNumberInTransactionDetails, "Page"))) {
							String sReferenceNumberInCashOut = getText(SendTransferPage.objReferenceNumberInTransactionDetails);
							System.out.println(sReferenceNumberInCashOut);
							assertionValidation(sReferenceNumberInCashOut, sReferenceNumber);
							logger.info("STB_TC_64, Send Money to any ML Branch Transaction With Valid ML Pin validated");
							extentLoggerPass("STB_TC_64", "STB_TC_64, Send Money to any ML Branch Transaction With Valid ML Pin validated");
							System.out.println("-----------------------------------------------------------");
						}
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchTransactionWithInValidMLPin_STB_TC_66() throws Exception {
					HeaderChildNode("Send Money to any ML Branch Transaction With Invalid ML Pin");
					waitTime(2000);
					changeNumberPage();
					clearField();
					waitTime(3000);
					sendMoneyToAnyMLBranch("9999999999");
					enterMLBranchDetails();
					enterAmountToKwartaPadala("100");
					waitTime(3000);
					handleMpin("1111", "Entered Mpin");
					waitTime(3000);
					if (verifyElementPresent(SendTransferPage.objInvalidPINMsg, getTextVal(SendTransferPage.objInvalidPINMsg, "Message"))) {
						String sActualErrorMsg = getText(SendTransferPage.objInvalidPINMsg);
						String sExceptedErrorMsg = "You have entered an invalid PIN. Please try again.";
						assertionValidation(sActualErrorMsg,sExceptedErrorMsg);
						logger.info("STB_TC_65, Send Money to any ML Branch Transaction With Invalid ML Pin validated");
						extentLoggerPass("STB_TC_65", "STB_TC_65, Send Money to any ML Branch Transaction With Invalid ML Pin validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}
				
				
				public void sendMoneyToMLBranchInOTPPopupValidation_STB_TC_72() throws Exception {
					HeaderChildNode("Send Money To ML Branch InApp OTP popup Validation");
					waitTime(2000);
					changeNumberPage();
					clearField();
					waitTime(3000);
					sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
					enterMLBranchDetails();
					enterAmountToKwartaPadala("100");
					explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
					if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
						logger.info("STB_TC_71, Send Money To ML Branch, InApp OTP popup validated");
						extentLoggerPass("STB_TC_71", "STB_TC_71, Send Money To ML Branch, InApp OTP popup validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchTransactionInAppOTPPopupUIValidation_STB_TC_73() throws Exception {
					HeaderChildNode("Send Money To ML Branch Transaction InApp OTP popup UI Validation");
					waitTime(2000);
					changeNumberPage();
					clearField();
					waitTime(3000);
					sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
					enterMLBranchDetails();
					enterAmountToKwartaPadala("100");
					explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
					if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
						verifyElementPresent(MLWalletLoginPage.objOtpContineBtn,getTextVal(MLWalletLoginPage.objOtpContineBtn,"Button"));
						verifyElementPresent(MLWalletLoginPage.objCancelBtn,getTextVal(MLWalletLoginPage.objCancelBtn,"Button"));
						logger.info("STB_TC_72, Send Money To ML Branch Transaction InApp OTP popup UI validated");
						extentLoggerPass("STB_TC_72", "STB_TC_72, Send Money To ML Branch Transaction InApp OTP popup UI validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchTransactionNewOTPAfterSixtySecondsValidation_STB_TC_74() throws Exception {
					HeaderChildNode("Send Money To ML Branch New OTP got generated After Sixty Seconds validation");
					waitTime(2000);
					changeNumberPage();
					clearField();
					waitTime(3000);
					sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
					enterMLBranchDetails();
					enterAmountToKwartaPadala("100");
					explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
					verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"));
					if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,getTextVal(MLWalletLoginPage.objOneTimePin,"One Time Pin"))){
						String sGeneratedOTP = getText(MLWalletLoginPage.objOTP);
						waitTime(70000);
						String sNewlyGeneratedOTPAfterSixtySeconds = getText(MLWalletLoginPage.objOTP);
						assertNotEquals(sGeneratedOTP,sNewlyGeneratedOTPAfterSixtySeconds);
						logger.info("STB_TC_73, Send Money To ML Branch Transaction New OTP got generated After Sixty Seconds is validated");
						extentLoggerPass("STB_TC_73", "STB_TC_73, Send Money To ML Branch Transaction New OTP got generated After Sixty Seconds is validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				public void sendMoneyToMLBranchTransactionOTPCancelBtnFunctionality_STB_TC_75() throws Exception {
					HeaderChildNode("Send Money To ML Branch Transaction OTP Cancel Button Functionality");
					waitTime(2000);
					changeNumberPage();
					clearField();
					waitTime(3000);
					sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
					enterMLBranchDetails();
					enterAmountToKwartaPadala("100");
					explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
					verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"));
					verifyElementPresentAndClick(MLWalletLoginPage.objCancelBtn,getTextVal(MLWalletLoginPage.objCancelBtn,"Button"));
					if(verifyElementPresent(SendTransferPage.objConfirmDetails,getTextVal(SendTransferPage.objConfirmDetails,"Page"))){
						logger.info("STB_TC_74, Send Money To ML Branch Transaction, After clicking on Cancel in One time pin popup App navigates back to Confirm details Page is validated");
						extentLoggerPass("STB_TC_74", "STB_TC_74, Send Money To ML Branch Transaction, After clicking on Cancel in One time pin popup App navigates back to Confirm details Page is validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}


				public void sendMoneyToMLBranchTransactionOTPContinueBtnFunctionality_STB_TC_76() throws Exception {
					HeaderChildNode("Send Money To ML Branch Transaction OTP Continue Button Functionality");
					waitTime(2000);
					changeNumberPage();
					clearField();
					waitTime(3000);
					sendMoneyToAnyMLBranch(prop.getproperty("Branch_Verified"));
					enterMLBranchDetails();
					enterAmountToKwartaPadala("100");
					explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 5);
					verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"));
					verifyElementPresentAndClick(MLWalletLoginPage.objOtpContineBtn, getTextVal(MLWalletLoginPage.objOtpContineBtn, "Button"));
					if (verifyElementPresent(SendTransferPage.objSendMoneySuccessful, getTextVal(SendTransferPage.objSendMoneySuccessful, "Message"))) {
						logger.info("STB_TC_75, Send Money To ML Branch Transaction, After clicking on Continue in One time pin popup App navigates to Transaction Success Page is validated");
						extentLoggerPass("STB_TC_75", "STB_TC_75, Send Money To ML Branch Transaction, After clicking on Continue in One time pin popup App navigates to Transaction Success page is validated");
						System.out.println("-----------------------------------------------------------");
					}
					getDriver().resetApp();
				}

				
				
				
				
				//===============================================Send/Transfer To a ML Wallet User=============================//
				//========================== Generalized methods for Send/Transfer To a ML Wallet User========================//

					public void sendMoneyMLWallet(String sTier) throws Exception {
						mlWalletLogin(sTier);
						click(SendTransferPage.objSendTransferBtn, getTextVal(SendTransferPage.objSendTransferBtn, "Button"));
						waitTime(2000);
						verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
						verifyElementPresentAndClick(SendTransferPage.objToAMLWalletUser, getTextVal(SendTransferPage.objToAMLWalletUser, "Button"));
					}


					public void enterMobileNumberMLWallet(String nMobileNumber) throws Exception {
							explicitWaitVisible(SendTransferPage.objSendMoney, 10);
							verifyElementPresent(SendTransferPage.objSendMoney,getTextVal(SendTransferPage.objSendMoney, "Page"));
							waitTime(2000);
							type(SendTransferPage.objMobileNumberField, nMobileNumber, "Mobile Number Text Field");
							Swipe("DOWN", 1);
							click(SendTransferPage.objNextBtn1, getTextVal(SendTransferPage.objNextBtn1, "Button"));
					}

					public void enterAmountAndSendToMLWallet(String nAmount) throws Exception {
						explicitWaitVisible(SendTransferPage.objAmountTxtField, 10);
						if (verifyElementPresent(SendTransferPage.objToMLWalletUser, getTextVal(SendTransferPage.objToMLWalletUser, "Page"))) {
							explicitWaitVisibility(SendTransferPage.objAmountTxtField, 10);
							type(SendTransferPage.objAmountTxtField, nAmount, "Amount Text Field");
							Swipe("DOWN", 1);
							waitTime(2000);
							click(SendTransferPage.objNextBtn1, getTextVal(SendTransferPage.objNextBtn1, "Button"));	
							waitTime(2000);
							click(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
							waitTime(2000);
							verifyElementPresent(SendTransferPage.objConfirmDetails, getTextVal(SendTransferPage.objConfirmDetails, "Page"));
							Swipe("UP", 1);
							waitTime(2000);
							click(SendTransferPage.objSendPHPBtn, getTextVal(SendTransferPage.objSendPHPBtn, "Button"));
						}
					}





				//======================================================================================================================//
					public void sendToMLWalletUser_STW_TC_01() throws Exception {
						HeaderChildNode("Send Money to any ML Wallet");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_Verified_ios"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						enterOTP(prop.getproperty("Valid_OTP"));
						waitTime(10000);
						if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
							verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
							verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
							verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
							String sReferenceNumber = getText(SendTransferPage.objMLWalletReferenceNumber);
							System.out.println(sReferenceNumber);
							Swipe("UP", 1);
							click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
							Thread.sleep(3000);
							verifyRecentTransaction1();
							verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
							verifyElementPresent(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
							waitTime(3000);
							click(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
							explicitWaitVisibility(SendTransferPage.objReferenceNumberInTransactionDetails, 10);
							if (verifyElementPresent(SendTransferPage.objReferenceNumberInTransactionDetails, getTextVal(SendTransferPage.objReferenceNumberInTransactionDetails, "Page"))) {
								String sReferenceNumberInWalletToWallet = getText(SendTransferPage.objReferenceNumberInTransactionDetails);
								System.out.println(sReferenceNumberInWalletToWallet);
								assertionValidation(sReferenceNumberInWalletToWallet, sReferenceNumber);
								logger.info("STW_TC_01, Successfully Amount sent from Wallet to Wallet and Transaction Details is validated");
								extentLoggerPass("STW_TC_01", "STW_TC_01, Successfully Amount sent from Wallet to Wallet and Transaction Details is validated");
								System.out.println("-----------------------------------------------------------");
							}
						}
						backArrowBtn(1);
						mlWalletLogout1();
					}


					public void sendMoneyAddToFavorites_STW_TC_12() throws Exception {
						HeaderChildNode("Send Money Add To Favorites");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						enterOTP(prop.getproperty("Valid_OTP"));
						waitTime(8000);
						if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
							verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
							verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
							verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
							String sReferenceNumber =getText(SendTransferPage.objMLWalletReferenceNumber);
							System.out.println(sReferenceNumber);
							Swipe("UP", 2);
							click(SendTransferPage.objSaveToMyFavorite, getTextVal(SendTransferPage.objSaveToMyFavorite, "Button"));
							waitTime(2000);
							if(verifyElementPresent(SendTransferPage.objAddedToFavoritesMsg,"Successfully Added to Favorites")) {
								click(SendTransferPage.objOkBtn, getTextVal(SendTransferPage.objOkBtn, "Button"));
								waitTime(2000);
								verifyElementPresent(SendTransferPage.objSendMoney, "Send Money Page") ;
								verifyElementPresent(SendTransferPage.objFavoriteReceiver,"Added Favorites");
								logger.info("STW_TC_12, Added to favorites and displayed in Recent Favorites");
								extentLoggerPass("STW_TC_12", "STW_TC_12, Added to favorites and displayed in Recent Favorites");
								System.out.println("-----------------------------------------------------------");
								backArrowBtn(2);
							}
							else
							{
								logger.info("Recipient already exists");
								click(SendTransferPage.objOkBtn, getTextVal(SendTransferPage.objOkBtn, "Button"));
								click(SendTransferPage.objBackToHomeBtn, "Back To Home Button");
							}

						}	
						
						mlWalletLogout1();
					}


					public void sendMoneyMLWalletToExistingReceiver_STW_TC_02() throws Exception {
						HeaderChildNode("Send Money ML Wallet To Existing Receiver");
//						sendMoneyAddToFavorites();
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						waitTime(5000);
						verifyElementPresent(SendTransferPage.objSelectFavorite, getTextVal(SendTransferPage.objSelectFavorite, "Text"));
						click(SendTransferPage.objSelectFavorite, getTextVal(SendTransferPage.objSelectFavorite, "Text"));
						click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
						enterAmountAndSendToMLWallet("10");
						enterOTP(prop.getproperty("Valid_OTP"));
						waitTime(5000);
						if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
							verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
							verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
							verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
							String sReferenceNumber = getText(SendTransferPage.objMLWalletReferenceNumber);
							System.out.println(sReferenceNumber);
							Swipe("UP", 2);
							click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
							waitTime(3000);
							verifyRecentTransaction1();
							verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
							verifyElementPresent(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
							waitTime(3000);
							click(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
							explicitWaitVisibility(SendTransferPage.objReferenceNumberInTransactionDetails, 20);
							if (verifyElementPresent(SendTransferPage.objReferenceNumberInTransactionDetails, getTextVal(SendTransferPage.objReferenceNumberInTransactionDetails, "Page"))) {
								String sReferenceNumberInWalletToWallet = getText(SendTransferPage.objReferenceNumberInTransactionDetails);
								System.out.println(sReferenceNumberInWalletToWallet);
								assertionValidation(sReferenceNumberInWalletToWallet, sReferenceNumber);
								logger.info("STW_TC_02, Successfully Amount sent from Wallet to Wallet to Recently added favorite and Transaction Details is validated");
								extentLoggerPass("STW_TC_02", "STW_TC_02, Successfully Amount sent from Wallet to Wallet to Recently added favorite and Transaction Details is validated");
								System.out.println("-----------------------------------------------------------");
							}
						}
						backArrowBtn(1);
						mlWalletLogout1();

					}


					public void sendToMLWalletInvalidMobNumber_STW_TC_03() throws Exception {
						HeaderChildNode("Send To ML Wallet to Invalid Mobile Number");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Invalid_MobileNumber"));
						explicitWaitVisible(SendTransferPage.objMobileNumberErrorMsg,20);
						if (verifyElementPresent(SendTransferPage.objMobileNumberErrorMsg, getTextVal(SendTransferPage.objMobileNumberErrorMsg, "Error Message"))) {
							String sFirstNameErrorMsg = getText(SendTransferPage.objMobileNumberErrorMsg);
							String sExpectedMsg = "Mobile number is invalid";
							assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
							logger.info("STW_TC_03, Mobile number is invalid - Error Message is validated");
							extentLoggerPass("STW_TC_03", "STW_TC_03, Mobile number is invalid - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(2);
						mlWalletLogout1();
					}


					public void sendToMLWalletUnRegisteredNumber_STW_TC_04() throws Exception {
						HeaderChildNode("Send To ML Wallet to Invalid Mobile Number");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Unregistered_MobileNumber"));
						explicitWaitVisible(SendTransferPage.objUnRegisteredMobNumber,10);
						explicitWaitVisibility(SendTransferPage.objUnRegisteredMobNumber, 10);
						if (verifyElementPresent(SendTransferPage.objUnRegisteredMobNumber, getTextVal(SendTransferPage.objUnRegisteredMobNumber, "Error Message"))) {
							String sFirstNameErrorMsg = getText(SendTransferPage.objUnRegisteredMobNumber);
							String sExpectedMsg = "Receiver not Found!";
							assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
							click(SendTransferPage.objOkBtn, "Ok Button");
							logger.info("STW_TC_04, Receiver not Found - Error Message is validated");
							extentLoggerPass("STW_TC_04", "STW_TC_04, Receiver not Found - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(2);
						mlWalletLogout1();
					}


					public void sendToMLWalletInvalidAmount_STW_TC_05(String Amount) throws Exception {
						HeaderChildNode("Send Money to any ML Branch");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						explicitWaitVisible(SendTransferPage.objAmountTxtField, 10);
						type(SendTransferPage.objAmountTxtField, Amount, "Amount Text Field");
						Swipe("DOWN",1);
						click(SendTransferPage.objNextBtn, getTextVal(SendTransferPage.objNextBtn, "Button"));
						explicitWaitVisibility(SendTransferPage.objInvalidAmountMsg, 10);
						if (verifyElementPresent(SendTransferPage.objInvalidAmountMsg, getTextVal(SendTransferPage.objInvalidAmountMsg, "Error Message"))) {
							String sInvalidAmountErrorMsg = getText(SendTransferPage.objInvalidAmountMsg);
							String sExpectedErrorMsg = "The amount should not be less than 1";
							assertionValidation(sInvalidAmountErrorMsg, sExpectedErrorMsg);
							logger.info("STW_TC_05, The amount should not be less than 1 - Error Message is validated");
							extentLoggerPass("STW_TC_05", "STW_TC_05, The amount should not be less than 1 - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(3);
						mlWalletLogout1();
					}


					public void sendToMLWalletInsufficientAmount_STW_TC_06() throws Exception {
						HeaderChildNode("Send Money to any ML Branch");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_Verified"));
						enterMobileNumberMLWallet(prop.getproperty("ios_BranchVerifiedTier"));
						enterAmountAndSendToMLWallet("35000");
						waitTime(4000);
						if (verifyElementPresent(SendTransferPage.objInsufficientAmountMsg, getTextVal(SendTransferPage.objInsufficientAmountMsg, "Error Message"))) {
							String sInsufficientBalanceErrorMsg = getText(SendTransferPage.objInsufficientAmountMsg);
							String sExpectedErrorMsg = "There is insufficient balance to proceed with this transaction. Please try again.";
							assertionValidation(sInsufficientBalanceErrorMsg, sExpectedErrorMsg);
							click(SendTransferPage.objOkBtn, "OK Button");
							logger.info("STW_TC_06, Insufficient Balance - Error Message is validated");
							extentLoggerPass("STW_TC_06", "STW_TC_06, Insufficient Balance - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(4);
						mlWalletLogout1();
					}


					public void sendMoneyMLWalletMaximumAmount_STW_TC_07() throws Exception {
						HeaderChildNode("Send Money ML Wallet Maximum Amount");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("100000");
						waitTime(3000);
						if (verifyElementPresent(SendTransferPage.objMaxLimitErrorMsg, getTextVal(SendTransferPage.objMaxLimitErrorMsg, "Error Message"))) {
							String sMaximumLimitErrorMsg = getText(SendTransferPage.objMaxLimitErrorMsg);
							String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
							assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
							click(MLWalletCashOutPage.objOKBtnOne, "OK Button");
							logger.info("STW_TC_07, The maximum send money per transaction - Error Message is validated");
							extentLoggerPass("STW_TC_07", "STW_TC_07, The maximum send money per transaction - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(6);
						mlWalletLogout1();
					}

					public void sendMoneyDeleteFromFavorites_STW_TC_09() throws Exception {
						HeaderChildNode("Send Money Delete From Favorites");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						explicitWaitVisible(SendTransferPage.objSendMoney, 10);
						verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"));
						click(SendTransferPage.objViewAllBtn, getTextVal(SendTransferPage.objViewAllBtn, "Text"));
						waitTime(5000);
						click(SendTransferPage.objEllipsisBtn, "Ellipsis Button");
						waitTime(2000);
						click(SendTransferPage.objDeleteBtn, getTextVal(SendTransferPage.objDeleteBtn, "Button"));
						waitTime(2000);
						click(SendTransferPage.objConfirmBtn1, getTextVal(SendTransferPage.objConfirmBtn1, "Button"));
						explicitWaitVisible(SendTransferPage.objFavRemovedMsg, 10);
						if(verifyElementPresent(SendTransferPage.objFavRemovedMsg, getTextVal(SendTransferPage.objFavRemovedMsg, "Pop up Message"))) {
							String sRemovedSuccessfulMsg = getText(SendTransferPage.objFavRemovedMsg);
							String sExpectedMsg = "Successfully Removed";
							assertionValidation(sRemovedSuccessfulMsg, sExpectedMsg);
							explicitWaitVisibility(MLWalletCashOutPage.objOKBtnOne, 10);
							click(MLWalletCashOutPage.objOKBtnOne, getTextVal(MLWalletCashOutPage.objOKBtnOne, "Button"));
							logger.info("STW_TC_09, Successfully removed Favorite Contact from favorites list is validated");
							extentLoggerPass("STW_TC_09", "STW_TC_09, Successfully removed Favorite Contact from favorites list is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn1(1);
						backArrowBtn(2);
						mlWalletLogout1();
					}


					public void sendMoneyMLWalletUIValidation_STW_TC_10() throws Exception {
						HeaderChildNode("Send Money ML Wallet Page UI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						explicitWaitVisible(SendTransferPage.objSendMoney, 10);
						if(verifyElementPresent(SendTransferPage.objSendMoney, getTextVal(SendTransferPage.objSendMoney, "Page"))){
							verifyElementPresent(SendTransferPage.objViewAllBtn,getTextVal(SendTransferPage.objViewAllBtn,"Button"));
							verifyElementPresent(SendTransferPage.objRecentFavorites,getTextVal(SendTransferPage.objRecentFavorites,"Header"));
							verifyElementPresent(SendTransferPage.objReceiver,getTextVal(SendTransferPage.objReceiver,"Header"));
							verifyElementPresent(SendTransferPage.objMobileNumberField,"Mobile number input text field");
							verifyElementPresent(SendTransferPage.objNextBtn1,getTextVal(SendTransferPage.objNextBtn1,"Button"));
							logger.info("STW_TC_10, Send Money ML Wallet Page UI validated");
							extentLoggerPass("STW_TC_10", "STW_TC_10, Send Money ML Wallet Page UI validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(2);
						mlWalletLogout1();
					}

					public void sendMoneyFavoritesUIValidation_STW_TC_11() throws Exception {
						HeaderChildNode("Send Money Saved Favorites UI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						explicitWaitVisible(SendTransferPage.objSendMoney, 10);
						verifyElementPresentAndClick(SendTransferPage.objViewAllBtn,getTextVal(SendTransferPage.objViewAllBtn,"Button"));
					    waitTime(2000);
						if(verifyElementPresent(SendTransferPage.ObjFavorites,getTextVal(SendTransferPage.ObjFavorites,"Page"))){
							verifyElementPresent(SendTransferPage.objSearchFavoriteField,"Search Favorites Field");
							logger.info("STW_TC_11, Send Money ML Wallet Page UI validated");
							extentLoggerPass("STW_TC_11", "STW_TC_11, Send Money ML Wallet Page UI validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn1(1);
						backArrowBtn(2);
						mlWalletLogout1();
					}

					public void sendMoneyMLWalletCancelTransaction_STW_TC_13(String nAmount) throws Exception {
						HeaderChildNode("Send Money ML Wallet Cancel Transaction");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						explicitWaitVisible(SendTransferPage.objAmountTxtField, 10);
						if (verifyElementPresent(SendTransferPage.objToMLWalletUser, getTextVal(SendTransferPage.objToMLWalletUser, "Page"))) {
							type(SendTransferPage.objAmountTxtField, nAmount, "Amount Text Field");
							Swipe("DOWN",1);
							click(SendTransferPage.objNextBtn1, getTextVal(SendTransferPage.objNextBtn1, "Button"));
						//	click(SendTransferPage.objNextBtn1, getTextVal(SendTransferPage.objNextBtn1, "Button"));
							waitTime(2000);
							click(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
							explicitWaitVisibility(SendTransferPage.objConfirmDetails, 10);
							verifyElementPresent(SendTransferPage.objConfirmDetails, getTextVal(SendTransferPage.objConfirmDetails, "Page"));
							Swipe("UP", 1);
							explicitWaitVisibility(SendTransferPage.objCancelTransacttion1, 10);
							verifyElementPresentAndClick(SendTransferPage.objCancelTransacttion1,getTextVal(SendTransferPage.objCancelTransacttion1,"Button"));
							waitTime(2000);
							if(verifyElementPresent(SendTransferPage.objSendMoney,getTextVal(SendTransferPage.objSendMoney,"Page"))){
								logger.info("STW_TC_13, Cancelled the current Transaction");
								extentLoggerPass("STW_TC_13", "STW_TC_13, Cancelled the current Transaction");
								System.out.println("-----------------------------------------------------------");
							}
						}
						backArrowBtn(2);
						mlWalletLogout1();
					}


					public void sendMoneyMLWalletSearchUnFavoriteUser_STW_TC_14() throws Exception {
						HeaderChildNode("Send Money ML Wallet Search UnFavorite User");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						explicitWaitVisible(SendTransferPage.objSendMoney, 5);
						verifyElementPresentAndClick(SendTransferPage.objViewAllBtn,getTextVal(SendTransferPage.objViewAllBtn,"Button"));
						waitTime(2000);
						verifyElementPresent(SendTransferPage.ObjFavorites,getTextVal(SendTransferPage.ObjFavorites,"Page"));
						type(SendTransferPage.objSearchFavoriteField,"ABCD","Search Favorite Field");
						explicitWaitVisibility(SendTransferPage.objNoRecentTransactionTxt, 10);
						if(verifyElementPresent(SendTransferPage.objNoRecentTransactionTxt,getTextVal(SendTransferPage.objNoRecentTransactionTxt,"Added Favorite"))){
							logger.info("STW_TC_14, Send Money ML Wallet Search UnFavorite User Validated");
							extentLoggerPass("STW_TC_14", "STW_TC_14, Send Money ML Wallet Search UnFavorite User Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn1(1);
						backArrowBtn(2);
						mlWalletLogout1();
					}

					public void sendMoneyMLWalletSearchFavoriteUser_STW_TC_15() throws Exception {
						HeaderChildNode("Send Money ML Wallet Search Favorite User");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						explicitWaitVisible(SendTransferPage.objSendMoney, 10);
						verifyElementPresentAndClick(SendTransferPage.objViewAllBtn,getTextVal(SendTransferPage.objViewAllBtn,"Button"));
						waitTime(2000);
						verifyElementPresent(SendTransferPage.ObjFavorites,getTextVal(SendTransferPage.ObjFavorites,"Page"));
						type(SendTransferPage.objSearchFavoriteField,prop.getproperty("Last_Name"),"Search Favorite Field");
						if(verifyElementPresent(SendTransferPage.objAddedFavorite,getTextVal(SendTransferPage.objAddedFavorite,"Added Favorite"))){
							logger.info("STW_TC_15, Send Money ML Wallet Search Favorite User Validated");
							extentLoggerPass("STW_TC_15", "STW_TC_15, Send Money ML Wallet Search Favorite User Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn1(1);
						backArrowBtn(2);
						mlWalletLogout1();
					}

					public void sendMoneyMLWalletSuccessPageUIValidation_STW_TC_16() throws Exception {
						HeaderChildNode("Send Money ML Wallet Success Page UI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						waitTime(2000);
						enterOTP(prop.getproperty("Valid_OTP"));
						waitTime(8000);
						if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
							verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
							verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
							verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
							verifyElementPresent(SendTransferPage.objReceiverName,getTextVal(SendTransferPage.objReceiverName,"Receiver Name"));
							verifyElementPresent(SendTransferPage.objReceiverMobileNo, getTextVal(SendTransferPage.objReceiverMobileNo, "Receiver's Mobile Number"));
							verifyElementPresent(SendTransferPage.objPaymentMethod, getTextVal(SendTransferPage.objPaymentMethod, "Payment Method"));
							verifyElementPresent(SendTransferPage.objAmount, getTextVal(SendTransferPage.objAmount, "Amount"));
							verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"));
							verifyElementPresent(SendTransferPage.objTotalAmount, getTextVal(SendTransferPage.objTotalAmount, "Total Amount"));
							Swipe("UP",2);
							verifyElementPresent(SendTransferPage.objSaveToMyFavorite,getTextVal(SendTransferPage.objSaveToMyFavorite,"Button"));
							verifyElementPresent(SendTransferPage.objBackToHomeBtn,getTextVal(SendTransferPage.objBackToHomeBtn,"Button"));
							verifyElementPresent(SendTransferPage.objNewTransaction,getTextVal(SendTransferPage.objNewTransaction,"Button"));
							click(SendTransferPage.objBackToHomeBtn,getTextVal(SendTransferPage.objBackToHomeBtn,"Button"));
							logger.info("STW_TC_16,Send Money ML Wallet Success Page UI Validated");
							extentLoggerPass("STW_TC_16", "STW_TC_16, Send Money ML Wallet Success Page UI Validated");
							System.out.println("-----------------------------------------------------------");
						}
						mlWalletLogout1();
					}

					public void sendMoneyMLWalletOTPPageUIValidation_STW_TC_17() throws Exception {
						HeaderChildNode("Send Money ML Wallet OTP PageUI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						waitTime(5000);
						if (verifyElementPresent(MLWalletLoginPage.objOneTimePin1, "OTP Page")) {
							verifyElementPresent(MLWalletLoginPage.objOneTimePin1, "OTP text Field");
							verifyElementPresent(MLWalletLoginPage.objResendCode, getTextVal(MLWalletLoginPage.objResendCode, "Seconds"));
							logger.info("STW_TC_17, One Time Pin page UI Validated");
							extentLoggerPass("STW_TC_17", "STW_TC_16, One Time Pin page UI Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(6);
						mlWalletLogout1();
					}

					public void sendMoneyMLWalletConfirmDetailsPageUIValidation_STW_TC_18(String nAmount) throws Exception {
						HeaderChildNode("Send Money ML Wallet Confirm Details Page UI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						explicitWaitVisible(SendTransferPage.objAmountTxtField, 10);
						verifyElementPresent(SendTransferPage.objToMLWalletUser, getTextVal(SendTransferPage.objToMLWalletUser, "Page"));
						type(SendTransferPage.objAmountTxtField, nAmount, "Amount Text Field");
						Swipe("DOWN", 1);
						click(SendTransferPage.objNextBtn1, getTextVal(SendTransferPage.objNextBtn1, "Button"));
						click(SendTransferPage.objNextBtn1, getTextVal(SendTransferPage.objNextBtn1, "Button"));
						waitTime(2000);
						click(SendTransferPage.objMLWalletBalance, getTextVal(SendTransferPage.objMLWalletBalance, "Button"));
						explicitWaitVisibility(SendTransferPage.objConfirmDetails, 20);
						if(verifyElementPresent(SendTransferPage.objConfirmDetails, getTextVal(SendTransferPage.objConfirmDetails, "Page"))){
							verifyElementPresent(SendTransferPage.objReceiverName,getTextVal(SendTransferPage.objReceiverName,"Receiver Name"));
							verifyElementPresent(SendTransferPage.objReceiverMobileNo, getTextVal(SendTransferPage.objReceiverMobileNo, "Receiver's Mobile Number"));
							verifyElementPresent(SendTransferPage.objPaymentMethod, getTextVal(SendTransferPage.objPaymentMethod, "Payment Method"));
							verifyElementPresent(SendTransferPage.objAmount, getTextVal(SendTransferPage.objAmount, "Amount"));
							verifyElementPresent(SendTransferPage.objServiceFee, getTextVal(SendTransferPage.objServiceFee, "Service Fee"));
							verifyElementPresent(SendTransferPage.objTotalAmount, getTextVal(SendTransferPage.objTotalAmount, "Total Amount"));
							verifyElementPresent(SendTransferPage.objCancelTransacttion1,getTextVal(SendTransferPage.objCancelTransacttion1,"Button"));
							Swipe("UP", 1);
							verifyElementPresent(SendTransferPage.objSendPHPBtn,getTextVal(SendTransferPage.objSendPHPBtn,"Button"));
							logger.info("STW_TC_18, Send Money ML Wallet Confirm Details Page UI Validated");
							extentLoggerPass("STW_TC_18", "STW_TC_18, Send Money ML Wallet Confirm Details Page UI Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(5);
						mlWalletLogout1();
					}

					public void sendMoneyToMlWalletEnterAmountPageUIValidation_STW_TC_19() throws Exception {
						HeaderChildNode("Send Money To Ml Wallet Enter Amount Page UI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						explicitWaitVisible(SendTransferPage.objAmountTxtField, 10);
						if(verifyElementPresent(SendTransferPage.objToMLWalletUser, getTextVal(SendTransferPage.objToMLWalletUser, "Page"))) {
							verifyElementPresent(SendTransferPage.objAmountToSend, getTextVal(SendTransferPage.objAmountToSend, "Header"));
							verifyElementPresent(SendTransferPage.objAmountTxtField, "Amount Text Field");
							verifyElementPresent(SendTransferPage.objNextBtn1, getTextVal(SendTransferPage.objNextBtn1, "Button"));
							logger.info("STW_TC_19, Send Money To Ml Wallet Enter Amount Page UI Validated");
							extentLoggerPass("STW_TC_19", "STW_TC_19, Send Money To Ml Wallet Enter Amount Page UI Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(3);
						mlWalletLogout1();
					}

					public void sendMoneyToMLWalletPageUIValidation_STW_TC_20() throws Exception {
						HeaderChildNode("Send Money To ML Wallet Page UI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						explicitWaitVisible(SendTransferPage.objSendMoney, 10);
						if(verifyElementPresent(SendTransferPage.objSendMoney,getTextVal(SendTransferPage.objSendMoney, "Page"))) {
							verifyElementPresent(SendTransferPage.objRecentFavorites,getTextVal(SendTransferPage.objRecentFavorites,"Header"));
							verifyElementPresent(SendTransferPage.objViewAllBtn,getTextVal(SendTransferPage.objViewAllBtn,"Button"));
							verifyElementPresent(SendTransferPage.objReceiver,getTextVal(SendTransferPage.objReceiver,"Header"));
							verifyElementPresent(SendTransferPage.objMobileNumberField, "Mobile Number Text Field");
							verifyElementPresent(SendTransferPage.objNextBtn1, getTextVal(SendTransferPage.objNextBtn1, "Button"));
							logger.info("STW_TC_20, Send Money To ML Wallet Page UI Validated");
							extentLoggerPass("STW_TC_20", "STW_TC_20, Send Money To ML Wallet Page UI Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(2);
						mlWalletLogout1();
					}

					public void sendMoneyMlWalletTransactionDetailsUIValidation_STW_TC_21() throws Exception {
						HeaderChildNode("Transaction Details of Wallet To Wallet Page UI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						enterOTP(prop.getproperty("Valid_OTP"));
						waitTime(8000);
						if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
							verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
							verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
							verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
							Swipe("UP", 2);
							click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
							waitTime(3000);
							verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
							verifyElementPresent(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
							click(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
							explicitWaitVisibility(MLWalletTransactionHistoryPage.objTransactionDetails, 10);
							if(verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
								verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
								verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
								verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
								verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
								verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
								verifyElementPresent(MLWalletTransactionHistoryPage.objSenderName, getTextVal(MLWalletTransactionHistoryPage.objSenderName, "Sender Name"));
								verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
								verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
								verifyElementPresent(MLWalletTransactionHistoryPage.objTotalAmount, getTextVal(MLWalletTransactionHistoryPage.objTotalAmount, "Total Amount"));
								logger.info("STW_TC_21, Transaction Details of Wallet To Wallet Page UI Validation Validated");
								extentLoggerPass("STW_TC_21", "STW_TC_21, Transaction Details of Wallet To Wallet Page UI Validation Validated");
								System.out.println("-----------------------------------------------------------");
							}
						}
						backArrowBtn(1);
						mlWalletLogout1();
					}
					
					public void sendMoneyMLWalletBuyerTierAccountUser_STW_TC_22() throws Exception {
						HeaderChildNode("Send Money ML Wallet Buyer Tier Account User Transaction");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Buyer_Tier"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						waitTime(3000);
						if (verifyElementPresent(SendTransferPage.objErrorMsg, getTextVal(SendTransferPage.objErrorMsg, "Error Message"))) {
							String sMaximumLimitErrorMsg = getText(SendTransferPage.objErrorMsg);
							String sExpectedErrorMsg = "Send Money is not allowed for customers at this verification level. Please upgrade your account to use this service.";
							assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
							verifyElementPresentAndClick(SendTransferPage.objUpgradeNowBtn, getTextVal(SendTransferPage.objUpgradeNowBtn, "Button"));
							logger.info("STW_TC_22, Send Money is not allowed for customers at this Buyer tier - Error Message is validated");
							extentLoggerPass("STW_TC_22", "STW_TC_22, Send Money is not allowed for customers at this Buyer tier  - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(6);
						mlWalletLogout();
					}

					public void sendMoneyMLWalletSemiVerifiedAccountUser_STW_TC_23() throws Exception {
						HeaderChildNode("Send Money ML Wallet Semi-Verified Account User Transaction");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Semi_Verified_One"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						enterOTP(prop.getproperty("Valid_OTP"));
						waitTime(8000);
						if (verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"))) {
							verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
							click(SendTransferPage.objBackToHomeBtn, "Back To Home Button");
							logger.info("STW_TC_23, Send Money ML Wallet Semi-Verified Account User Transaction validated");
							extentLoggerPass("STW_TC_23", "STW_TC_23, Send Money ML Wallet Semi-Verified Account User Transaction validated");
							System.out.println("-----------------------------------------------------------");
						}
						mlWalletLogout();
					}

					public void sendMoneyMLWalletBranchVerifiedAccountUser_STW_TC_24() throws Exception {
						HeaderChildNode("Send Money ML Wallet Branch-Verified Account User Transaction");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Branch_Verified"));
						enterMobileNumberMLWallet(prop.getproperty("Fully_verified"));
						enterAmountAndSendToMLWallet("10");
						enableLocation_PopUp();
						enterOTP(prop.getproperty("Valid_OTP"));
						waitTime(5000);
						if (verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"))) {
							verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
							click(SendTransferPage.objBackToHomeBtn, "Back To Home Button");
							logger.info("STW_TC_24, Send Money ML Wallet Branch-Verified Account User Transaction validated");
							extentLoggerPass("STW_TC_24", "STW_TC_24, Send Money ML Wallet Branch-Verified Account User Transaction validated");
							System.out.println("-----------------------------------------------------------");
						}
						mlWalletLogout();
					}


					public void sendMoneyMLWalletFullyVerifiedAccountUser_STW_TC_25() throws Exception {
						HeaderChildNode("Send Money ML Wallet Fully-Verified Account User Transaction");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						enableLocation_PopUp();
						enterOTP(prop.getproperty("Valid_OTP"));
						waitTime(5000);
						if (verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"))) {
							verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
							Swipe("UP", 1);
							click(SendTransferPage.objBackToHomeBtn, "Back To Home Button");
							logger.info("STW_TC_25, Send Money ML Wallet Fully-Verified Account User Transaction validated");
							extentLoggerPass("STW_TC_25", "STW_TC_25, Send Money ML Wallet Fully-Verified Account User Transaction validated");
							System.out.println("-----------------------------------------------------------");
						}
						mlWalletLogout1();
					}

					public void sendMoneyMLWalletSemiVerifiedAccountMaxLimit_STW_TC_26() throws Exception {
						HeaderChildNode("Send Money To ML Wallet Semi-Verified Account Maximum Limit Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Semi_Verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("60000");
						waitTime(3000);
						if (verifyElementPresent(SendTransferPage.objMaxLimitPopUp, getTextVal(SendTransferPage.objMaxLimitPopUp, "Error Message"))) {
							String sMaximumLimitErrorMsg = getText(SendTransferPage.objMaxLimitPopUp);
							String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
							assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
							verifyElementPresentAndClick(SendTransferPage.objUpgradeNowBtn, getTextVal(SendTransferPage.objUpgradeNowBtn, "Button"));
							logger.info("STB_TC_26, Send Money To ML Wallet Semi-Verified Account Maximum Limit - Error Message is validated");
							extentLoggerPass("STB_TC_26", "STB_TC_26, Send Money To ML Wallet Semi-Verified Account Maximum Limit  - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(6);
						mlWalletLogout();
					}


					public void sendMoneyMLWalletBranchVerifiedAccountMaxLimit_STW_TC_29() throws Exception {
						HeaderChildNode("Send Money To ML Wallet Branch-Verified Account Maximum Limit Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Branch_Verified"));
						enterMobileNumberMLWallet(prop.getproperty("Fully_verified"));
						enterAmountAndSendToMLWallet("60000");
						waitTime(5000);
						if (verifyElementPresent(SendTransferPage.objMaxLimitPopUp, getTextVal(SendTransferPage.objMaxLimitPopUp, "Error Message"))) {
							String sMaximumLimitErrorMsg = getText(SendTransferPage.objMaxLimitPopUp);
							String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
							assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
							verifyElementPresentAndClick(SendTransferPage.objOkBtn, getTextVal(SendTransferPage.objOkBtn, "Button"));
							logger.info("STW_TC_29, Send Money To ML Wallet Branch-Verified Account Maximum Limit - Error Message is validated");
							extentLoggerPass("STW_TC_29", "STW_TC_29, Send Money To ML Wallet Branch-Verified Account Maximum Limit  - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(4);
						mlWalletLogout();
					}


					public void sendMoneyMLWalletFullyVerifiedAccountMaxLimit_STW_TC_32() throws Exception {
						HeaderChildNode("Send Money To ML Wallet Fully-Verified Account Maximum Limit Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("60000");
						waitTime(5000);
						if (verifyElementPresent(SendTransferPage.objMaxLimitPopUp, getTextVal(SendTransferPage.objMaxLimitPopUp, "Error Message"))) {
							String sMaximumLimitErrorMsg = getText(SendTransferPage.objMaxLimitPopUp);
							String sExpectedErrorMsg = "The maximum Send Money per transaction set for your verification level is P50,000.00. Please try again.";
							assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
							verifyElementPresentAndClick(SendTransferPage.objOkBtn, getTextVal(SendTransferPage.objOkBtn, "Button"));
							logger.info("STW_TC_32, Send Money To ML Wallet Fully-Verified Account Maximum Limit - Error Message is validated");
							extentLoggerPass("STW_TC_32", "STW_TC_32, Send Money To ML Wallet Fully-Verified Account Maximum Limit  - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(4);
						mlWalletLogout1();
					}

					public void sendMoneyToMLWalletSuccessMsgValidation_STW_TC_35() throws Exception {
						HeaderChildNode("Send Money To ML Wallet Success Message Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
//						enableLocation_PopUp();
						enterOTP(prop.getproperty("Valid_OTP"));
						waitTime(5000);
						verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"));
						verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
						Swipe("UP", 2);
						click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
						waitTime(3000);
						verifyRecentTransaction1();
						verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
						verifyElementPresent(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
//						if (verifyElementPresent(SendTransferPage.objWalletToWalletStatus, getTextVal(SendTransferPage.objWalletToWalletStatus, "Status"))) {
//							String sSuccessStatus = getText(SendTransferPage.objWalletToWalletStatus);
//							String sExpectedStatus = "Success";
//							assertionValidation(sSuccessStatus, sExpectedStatus);
//							logger.info("STW_TC_35, Send Money To ML Wallet Success Message validated");
//							extentLoggerPass("STW_TC_35", "STW_TC_35, Send Money To ML Wallet Success Message validated");
//							System.out.println("-----------------------------------------------------------");
//						}
						logger.info("STW_TC_35, Send Money To ML Wallet Success Message validated");
						extentLoggerPass("STW_TC_35", "STW_TC_35, Send Money To ML Wallet Success Message validated");
						System.out.println("-----------------------------------------------------------");
						mlWalletLogout1();
					}


					public void sendMoneyToMLWalletMaxTransactionReceivingLimitSemiVerifiedTier_STW_TC_36(String sAmount) throws Exception {
						HeaderChildNode("Send Money To ML Wallet Maximum Transaction Receiving Limit for Semi-Verified Tier User");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("SemiVerified_MaxTransactionSender"));
						enterMobileNumberMLWallet(prop.getproperty("SemiVerified_MaxTransactionReceiver"));

						enterAmountAndSendToMLWallet(sAmount);
//						enableLocation_PopUp();
						enterOTP(prop.getproperty("Valid_OTP"));
						verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"));
						verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
						String sReferenceNumberSender = getText(SendTransferPage.objMLWalletReferenceNumber);
						Swipe("UP", 2);
						click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
						Thread.sleep(3000);
						mlWalletLogout();
						verifyElementPresentAndClick(MLWalletLogOutPage.objChangeNumber, "Changer Number");
						mlWalletLogin(prop.getproperty("SemiVerified_MaxTransactionReceiver"));
						Swipe("DOWN", 2);
						Swipe("UP", 1);
						verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
						verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objReceiveMoneyTab, getTextVal(MLWalletTransactionHistoryPage.objReceiveMoneyTab, "Tab"));
						if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
							verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
							String sReferenceNumberReceiver = getText(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails);
							assertionValidation(sReferenceNumberSender, sReferenceNumberReceiver);
							verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objSenderName, getTextVal(MLWalletTransactionHistoryPage.objSenderName, "Sender Name"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objAmountReceived, getTextVal(MLWalletTransactionHistoryPage.objAmountReceived, "Total Amount"));
							String sReceivedAmount = getText(MLWalletTransactionHistoryPage.objAmountReceived);
							String sActualReceivedAmount = sReceivedAmount.substring(4);
							assertionValidation(sActualReceivedAmount, "50,000.00");
							logger.info("STW_TC_36, Send Money To ML Wallet Maximum Transaction Receiving Limit for Semi-Verified Tier User Validated");
							extentLoggerPass("STW_TC_36", "STW_TC_36, Send Money To ML Wallet Maximum Transaction Receiving Limit for Semi-Verified Tier User Validated");
							System.out.println("-----------------------------------------------------------");
						}
					}


					public void sendMoneyToMLWalletMaxTransactionReceivingLimitBranchVerifiedTier_STW_TC_38(String sAmount) throws Exception {
						HeaderChildNode("Send Money To ML Wallet Maximum Transaction Receiving Limit for Branch-Verified Tier User");
						sendMoneyMLWallet(prop.getproperty("BranchVerified_MaxTransactionSender"));
						enterMobileNumberMLWallet(prop.getproperty("BranchVerified_MaxTransactionReceiver"));

						enterAmountAndSendToMLWallet(sAmount);
						enableLocation_PopUp();
						enterOTP(prop.getproperty("Valid_OTP"));
						verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"));
						verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
						String sReferenceNumberSender = getText(SendTransferPage.objMLWalletReferenceNumber);
						Swipe("UP", 2);
						click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
						Thread.sleep(3000);
						mlWalletLogout();
						verifyElementPresentAndClick(MLWalletLogOutPage.objChangeNumber, "Changer Number");
						mlWalletLogin(prop.getproperty("BranchVerified_MaxTransactionReceiver"));
						Swipe("DOWN", 2);
						Swipe("UP", 1);
						verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
						verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objReceiveMoneyTab, getTextVal(MLWalletTransactionHistoryPage.objReceiveMoneyTab, "Tab"));
						if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
							verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
							String sReferenceNumberReceiver = getText(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails);
							assertionValidation(sReferenceNumberSender, sReferenceNumberReceiver);
							verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objSenderName, getTextVal(MLWalletTransactionHistoryPage.objSenderName, "Sender Name"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objAmountReceived, getTextVal(MLWalletTransactionHistoryPage.objAmountReceived, "Total Amount"));
							String sReceivedAmount = getText(MLWalletTransactionHistoryPage.objAmountReceived);
							String sActualReceivedAmount = sReceivedAmount.substring(4);
							assertionValidation(sActualReceivedAmount, "50,000.00");
							logger.info("STW_TC_38, Send Money To ML Wallet Maximum Transaction Receiving Limit for Branch-Verified Tier User Validated");
							extentLoggerPass("STW_TC_38", "STW_TC_38, Send Money To ML Wallet Maximum Transaction Receiving Limit for Branch-Verified Tier User Validated");
							System.out.println("-----------------------------------------------------------");
						}
					}


					public void sendMoneyToMLWalletMaxTransactionReceivingLimitFullyVerifiedTier_STW_TC_40(String sAmount) throws Exception {
						HeaderChildNode("Send Money To ML Wallet Maximum Transaction Receiving Limit for Fully-Verified Tier User");
						sendMoneyMLWallet(prop.getproperty("FullyVerified_MaxTransactionSender"));
						enterMobileNumberMLWallet(prop.getproperty("FullyVerified_MaxTransactionReceiver"));

						enterAmountAndSendToMLWallet(sAmount);
						enableLocation_PopUp();
						enterOTP(prop.getproperty("Valid_OTP"));
						verifyElementPresent(SendTransferPage.objTransactionDetails, getTextVal(SendTransferPage.objTransactionDetails, "Page"));
						verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Transaction Number"));
						String sReferenceNumberSender = getText(SendTransferPage.objMLWalletReferenceNumber);
						Swipe("UP", 2);
						click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
						Thread.sleep(3000);
						mlWalletLogout();
						verifyElementPresentAndClick(MLWalletLogOutPage.objChangeNumber, "Changer Number");
						mlWalletLogin(prop.getproperty("FullyVerified_MaxTransactionReceiver"));
						Swipe("DOWN", 2);
						Swipe("UP", 1);
						verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
						verifyElementPresentAndClick(MLWalletTransactionHistoryPage.objReceiveMoneyTab, getTextVal(MLWalletTransactionHistoryPage.objReceiveMoneyTab, "Tab"));
						if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
							verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
							String sReferenceNumberReceiver = getText(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails);
							assertionValidation(sReferenceNumberSender, sReferenceNumberReceiver);
							verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverName, getTextVal(MLWalletTransactionHistoryPage.objReceiverName, "Receiver Name"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objSenderName, getTextVal(MLWalletTransactionHistoryPage.objSenderName, "Sender Name"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objAmountReceived, getTextVal(MLWalletTransactionHistoryPage.objAmountReceived, "Total Amount"));
							String sReceivedAmount = getText(MLWalletTransactionHistoryPage.objAmountReceived);
							String sActualReceivedAmount = sReceivedAmount.substring(4);
							assertionValidation(sActualReceivedAmount, "50,000.00");
							logger.info("STW_TC_40, Send Money To ML Wallet Maximum Transaction Receiving Limit for Fully-Verified Tier User Validated");
							extentLoggerPass("STW_TC_40", "STW_TC_40, Send Money To ML Wallet Maximum Transaction Receiving Limit for Fully-Verified Tier User Validated");
							System.out.println("-----------------------------------------------------------");
						}
					}



					public void sendMoneyToMLWalletLocationPopupValidation_STW_TC_42() throws Exception {
						HeaderChildNode("Send Money To ML Wallet Location popup Validation");
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
							locationPopUpValidation();
							logger.info("STW_TC_42, Send Money To ML Wallet Location popup Validated");
							extentLoggerPass("STW_TC_42", "STW_TC_42, Send Money To ML Wallet Location popup Validated");
							System.out.println("-----------------------------------------------------------");
						}
					}


					public void sendMoneyToMLWalletLocationDenyFunctionality_STW_TC_43() throws Exception {
						HeaderChildNode("Send Money To ML Wallet Location Deny Functionality Validation");
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
							permissionDenyPopUp();
							logger.info("STW_TC_43, Send Money To ML Wallet Location Deny Functionality Validated");
							extentLoggerPass("STW_TC_43", "STW_TC_43, Send Money To ML Wallet Location Deny Functionality Validated");
							System.out.println("-----------------------------------------------------------");
						}
					}


					public void sendMoneyToMLWalletLocationPermissionDenyCloseBtnFunctionality_STW_TC_44() throws Exception {
						HeaderChildNode("Send Money To ML Wallet Location Permission Deny Close Button Functionality Validation");
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
							permissionDenyCloseBtnFunctionality();
							if(verifyElementPresent(SendTransferPage.objMLWalletBalance,getTextVal(SendTransferPage.objMLWalletBalance,"Page"))){
								logger.info("STW_TC_44, Send Money To ML Wallet Location Permission Deny Close Button Functionality Validated");
								extentLoggerPass("STW_TC_44", "STW_TC_44, Send Money To ML Wallet Location Permission Deny Close Button Functionality Validated");
								System.out.println("-----------------------------------------------------------");
							}
						}
					}


					public void sendMoneyToMLWalletLocationPermissionDenyOpenSettingsBtnFunctionality_STW_TC_45() throws Exception {
						HeaderChildNode("Send Money To ML Wallet Location Permission Deny open Settings Button Functionality Validation");
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
							permissionDenyOpenSettingsBtnFunctionality();
							if (verifyElementPresent(SendTransferPage.objAppInfo, getTextVal(SendTransferPage.objAppInfo, "Page"))) {
								logger.info("STW_TC_45, Send Money To ML Wallet Location Permission Deny Open Settings Button Functionality Validated");
								extentLoggerPass("STW_TC_45", "STW_TC_45, Send Money To ML Wallet Location Permission Deny Open Settings Button Functionality Validated");
								System.out.println("-----------------------------------------------------------");
							}
						}
					}


					public void sendMoneyToMLWalletLocationPopUpAllowFunctionality_STW_TC_46() throws Exception {
						HeaderChildNode("Send Money To ML Wallet Location popup Allow Button Functionality Validation");
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
							locationPopUpAllowFunctionality();
							if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,getTextVal(MLWalletLoginPage.objOneTimePin,"Page"))){
								logger.info("STW_TC_46, Send Money To ML Wallet Location popup Allow Button Functionality Validated");
								extentLoggerPass("STW_TC_46", "STW_TC_46, Send Money To ML Wallet Location popup Allow Button Functionality Validated");
								System.out.println("-----------------------------------------------------------");
							}
						}
					}


					public void sendMoneyToMLWalletInternetInterruptionWhileEnteringOTP_STW_TC_47() throws Exception {
						HeaderChildNode("Send Money To ML Wallet Internet Interruption While Entering OTP Validation");
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						enableLocation_PopUp();
						waitTime(15000);
						setWifiConnectionToONOFF("OFF");
						enterOTP(prop.getproperty("Valid_OTP"));
						if(verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"))){
							internetConnectionError();
							logger.info("STW_TC_47, Send Money To ML Wallet Internet Interruption While Entering OTP Validated");
							extentLoggerPass("STW_TC_47", "STW_TC_47, Send Money To ML Wallet Internet Interruption While Entering OTP Validated");
							System.out.println("-----------------------------------------------------------");
						}
					}


					public void sendMoneyToMLWalletTransactionValidationAfterMinimizingApp_STW_TC_51() throws Exception {
						HeaderChildNode("Send Money To ML Wallet Transaction Validation After Minimizing App");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						enterOTP(prop.getproperty("Valid_OTP"));
						getDriver().runAppInBackground(Duration.ofSeconds(5));
						waitTime(5000);
						logger.info("Application relaunched after 5 Seconds");
						click(SendTransferPage.objOkBtn, "OK Button");
						enterOTP(prop.getproperty("Valid_OTP"));
						waitTime(5000);
						if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
							Swipe("UP",1);
							click(SendTransferPage.objBackToHomeBtn, "Back To Home Buttton");
							logger.info("STW_TC_50, Send Money To ML Wallet Transaction Validation After Minimizing App Validated");
							extentLoggerPass("STW_TC_50", "STW_TC_50, Send Money To ML Wallet Transaction Validation After Minimizing App Validated");
							System.out.println("-----------------------------------------------------------");
						}
						mlWalletLogout1();
					}
					
					
					public void sendMoneyToMLWalletTransactionWithValidMLPin_STW_TC_57() throws Exception {
						HeaderChildNode("Send Money to any ML Wallet Transaction With Valid ML Pin");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
						handleMpin("1111", "Entered Mpin");
						waitTime(3000);
						if (verifyElementPresent(SendTransferPage.objSendMoneyMLWallet, getTextVal(SendTransferPage.objSendMoneyMLWallet, "Message"))) {
							verifyElementPresent(SendTransferPage.objSendMoneyMLWalletPHP, getTextVal(SendTransferPage.objSendMoneyMLWalletPHP, "Amount"));
							verifyElementPresent(SendTransferPage.objSendMoneyMLWalletDate, getTextVal(SendTransferPage.objSendMoneyMLWalletDate, "Date"));
							verifyElementPresent(SendTransferPage.objMLWalletReferenceNumber, getTextVal(SendTransferPage.objMLWalletReferenceNumber, "Reference Number"));
							String sReferenceNumber = getText(SendTransferPage.objMLWalletReferenceNumber);
							System.out.println(sReferenceNumber);
							Swipe("UP", 2);
							click(SendTransferPage.objBackToHomeBtn, getTextVal(SendTransferPage.objBackToHomeBtn, "Button"));
							Thread.sleep(3000);
							Swipe("DOWN", 2);
							Swipe("UP", 1);
							verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
							verifyElementPresent(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
							click(MLWalletHomePage.objWalletToWallet, getTextVal(MLWalletHomePage.objWalletToWallet, "Text"));
							if (verifyElementPresent(SendTransferPage.objReferenceNumberInTransactionDetails, getTextVal(SendTransferPage.objReferenceNumberInTransactionDetails, "Page"))) {
								String sReferenceNumberInWalletToWallet = getText(SendTransferPage.objReferenceNumberInTransactionDetails);
								System.out.println(sReferenceNumberInWalletToWallet);
								assertionValidation(sReferenceNumberInWalletToWallet, sReferenceNumber);
								logger.info("STW_TC_56, Send Money to any ML Wallet Transaction With Valid ML Pin validated");
								extentLoggerPass("STW_TC_56", "STW_TC_56, Send Money to any ML Wallet Transaction With Valid ML Pin validated");
								System.out.println("-----------------------------------------------------------");
							}
						}
						getDriver().resetApp();
					}

					public void sendMoneyToMLWalletTransactionWithInValidMLPin_STW_TC_58() throws Exception {
						HeaderChildNode("Send Money to any ML Wallet Transaction With Invalid ML Pin");
						waitTime(2000);
						changeNumberPage();
						clearField();
						sendMoneyMLWallet(prop.getproperty("Fully_verified"));
						enterMobileNumberMLWallet(prop.getproperty("Branch_Verified"));
						enterAmountAndSendToMLWallet("10");
	                    waitTime(3000);
						handleMpin("1234", "Entered Mpin");
						if (verifyElementPresent(SendTransferPage.objInvalidPINMsg, getTextVal(SendTransferPage.objInvalidPINMsg, "Message"))) {
							String sActualErrorMsg = getText(SendTransferPage.objInvalidPINMsg);
							String sExceptedErrorMsg = "You have entered an invalid PIN. Please try again.";
							assertionValidation(sActualErrorMsg,sExceptedErrorMsg);
							logger.info("STW_TC_57, Send Money to any ML Wallet Transaction With Invalid ML Pin validated");
							extentLoggerPass("STW_TC_57", "STW_TC_57, Send Money to any ML Wallet Transaction With Invalid ML Pin validated");
							System.out.println("-----------------------------------------------------------");
						}
						getDriver().resetApp();
					}
					
					
					//=========================================== Cash In Via Bank ================================================//
					//======================= Generalized methods for Cash In Via Bank ===========================================//

						public void selectBankAndInputAmount(String sAmount) throws Exception {
							explicitWaitVisibility(MLWalletCashInBank.objCashIn, 20);
							if (verifyElementPresent(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Icon"))) {
								click(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Icon"));
								explicitWaitVisibility(MLWalletCashInBank.objMyBankAccount, 20);
								click(MLWalletCashInBank.objMyBankAccount, getTextVal(MLWalletCashInBank.objMyBankAccount, "Button"));
								waitTime(2000);
								verifyElementPresent(MLWalletCashInBank.objSelectABank, getTextVal(MLWalletCashInBank.objSelectABank, "Page"));
								click(MLWalletCashInBank.objTestBankOnline, getTextVal(MLWalletCashInBank.objTestBankOnline, "Bank"));
								waitTime(2000);
								verifyElementPresent(MLWalletCashInBank.objDragonPay, getTextVal(MLWalletCashInBank.objDragonPay, "Page"));
								verifyElementPresent(MLWalletCashInBank.objBankCashIn, getTextVal(MLWalletCashInBank.objBankCashIn, "Text"));
								type(MLWalletCashInBank.objAmountEditField, sAmount, "Amount Text Field");
								click(MLWalletCashInBank.objNextBtn1, getTextVal(MLWalletCashInBank.objNextBtn1, "Button"));
								click(MLWalletCashInBank.objNextBtn1, getTextVal(MLWalletCashInBank.objNextBtn1, "Button"));
								click(MLWalletCashInBank.objNextBtn1, getTextVal(MLWalletCashInBank.objNextBtn1, "Button"));
								waitTime(3000);
							}
						}
						public void dragonPayChargesMsgValidation() throws Exception {
							if (verifyElementPresent(MLWalletCashInBank.objDragonPayChargesMsg, getTextVal(MLWalletCashInBank.objDragonPayChargesMsg, "Message"))) {
								String sDragonPayChargesMsg = getText(MLWalletCashInBank.objDragonPayChargesMsg);
								String sExpectedDragonPayChargesMsg = "Dragon Pay charges a fee of 30 pesos for this transaction. Do you wish to continue with your transaction?";
								assertionValidation(sDragonPayChargesMsg, sExpectedDragonPayChargesMsg);
								click(MLWalletCashInBank.objContinueBtn, getTextVal(MLWalletCashInBank.objContinueBtn, "Button"));
								waitTime(3000);
							}
						}
						public void reviewTransactionValidation() throws Exception {
							explicitWaitVisibility(MLWalletCashInBank.objReviewTransaction, 20);
							verifyElementPresent(MLWalletCashInBank.objReviewTransaction,getTextVal(MLWalletCashInBank.objReviewTransaction,"Page"));
							Swipe("UP",1);
							if(verifyElementPresent(MLWalletCashInBank.objBankTransferCutOffTime,getTextVal(MLWalletCashInBank.objBankTransferCutOffTime,"Message"))){
								String sBankTransferTime = getText(MLWalletCashInBank.objBankTransferCutOffTime);
								String sExpectedBankTransferTime ="Bank transfers after 1:00PM are posted on the next banking day.";
								assertionValidation(sBankTransferTime,sExpectedBankTransferTime);
							}
							click(MLWalletCashInBank.objNextBtn1,getTextVal(MLWalletCashInBank.objNextBtn1,"Button"));
							click(MLWalletCashInBank.objNextBtn1,getTextVal(MLWalletCashInBank.objNextBtn1,"Button"));
						}


						public void bankUserLogin(String sLoginId,String sPassword) throws Exception {
							explicitWaitVisible(MLWalletCashInBank.objReferenceNumberMsg,5);
							if(verifyElementPresent(MLWalletCashInBank.objReferenceNumberMsg,getTextVal(MLWalletCashInBank.objReferenceNumberMsg,"Reference Information"))){
								type(MLWalletCashInBank.objLoginIdTxtField,sLoginId,"Login Id Text Field");
								type(MLWalletCashInBank.objPasswordTxtField,sPassword,"Password Text Field");

							}
						}

					
					
					
					//=================================cashInViaBank===================================================//

					public void cashInViaBank_CIBA_TC_01(String status) throws Exception {
						HeaderChildNode("Cash In Via Bank");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						selectBankAndInputAmount("100");
						dragonPayChargesMsgValidation();
						reviewTransactionValidation();
						enterOTP(prop.getproperty("Valid_OTP"));
						waitTime(2000);
						bankUserLogin(prop.getproperty("Valid_LoginId"), prop.getproperty("Valid_Password"));
						click(MLWalletCashInBank.objContinueBtn,"Continue Button");
						explicitWaitVisibility(MLWalletCashInBank.objPayBtn, 20);
						click(MLWalletCashInBank.objPayBtn,getTextVal(MLWalletCashInBank.objPayBtn,"Button"));
						explicitWaitVisibility(MLWalletCashInBank.objBankReferenceNumber, 10);
						verifyElementPresent(MLWalletCashInBank.objBankReferenceNumber,getTextVal(MLWalletCashInBank.objBankReferenceNumber,"Reference Number"));
						verifyElementPresent(MLWalletCashInBank.objStatus,getTextVal(MLWalletCashInBank.objStatus,"Status"));
						verifyElementPresent(MLWalletCashInBank.objMessage,getTextVal(MLWalletCashInBank.objMessage,"Message"));
						if(verifyElementPresent(MLWalletCashInBank.objSuccessMsg,getTextVal(MLWalletCashInBank.objSuccessMsg,"Message"))){
							click(MLWalletCashInBank.objCompleteTransactionBtn, "Complete Transaction Button");
							logger.info("CIBA_TC_01, Cash In Via Bank validated");
							extentLoggerPass("CIBA_TC_01", "CIBA_TC_01, Cash In Via Bank validated");
							System.out.println("-----------------------------------------------------------");
						}
						if(status.equals("true"))
						{
						mlWalletLogout();
						}
					}

//					public void cashInViaBankInvalidBankDetails() throws Exception {
//						HeaderChildNode("Cash In Via Bank Invalid Bank Details");
//						mlWalletLogin(prop.getproperty("Branch_Verified"));
//						selectBankAndInputAmount("100");
//						dragonPayChargesMsgValidation();
//						reviewTransactionValidation();
//						enterMLPin();
//						enableLocation_PopUp();
//						bankUserLogin(prop.getproperty("InValid_LoginId"), prop.getproperty("Invalid_Password"));
//					}

					public void cashInViaBankMinimumTransactionLimit_CIBA_TC_03() throws Exception {
						HeaderChildNode("Cash In Via Bank Minimum Transaction Limit");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						selectBankAndInputAmount("20");
						explicitWaitVisibility(MLWalletCashInBank.objMinimumTransactionPopupMsg, 10);
						if(verifyElementPresent(MLWalletCashInBank.objMinimumTransactionPopupMsg,getTextVal(MLWalletCashInBank.objMinimumTransactionPopupMsg,"Pop Message"))){
							String sMinimumTransactionPopupMsg = getText(MLWalletCashInBank.objMinimumTransactionPopupMsg);
							String sExpectedPopupMsg = "The supplied amount is less than the required minimum transaction limit";
							assertionValidation(sMinimumTransactionPopupMsg,sExpectedPopupMsg);
							click(MLWalletCashInBank.objOkBtn, "OK Button");
							logger.info("CIBA_TC_03, Minimum transaction limit pop up message is validated");
							extentLoggerPass("CIBA_TC_03", "CIBA_TC_03, Minimum transaction limit pop up message is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(3);
						mlWalletLogout();
					}

					public void cashInViaBankMaximumTransaction_CIBA_TC_04() throws Exception {
						HeaderChildNode("Cash In Via Bank Maximum Transaction");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						selectBankAndInputAmount("60000");
						dragonPayChargesMsgValidation();
						reviewTransactionValidation();
						explicitWaitVisibility(MLWalletCashInBank.objMaxLimitErrorMsg, 10);
						if (verifyElementPresent(MLWalletCashInBank.objMaxLimitErrorMsg, getTextVal(MLWalletCashInBank.objMaxLimitErrorMsg, "Error Message"))) {
							String sMaximumLimitErrorMsg = getText(MLWalletCashInBank.objMaxLimitErrorMsg);
							String sExpectedErrorMsg = "The maximum Bank Cash-in per transaction set for your verification level is P50,000.00. Please try again.";
							assertionValidation(sMaximumLimitErrorMsg, sExpectedErrorMsg);
							click(MLWalletCashInBank.objOkBtnOne, "Upgrade Now Button");
							logger.info("CIBA_TC_04, The maximum send money per transaction - Error Message is validated");
							extentLoggerPass("CIBA_TC_04", "CIBA_TC_04, The maximum send money per transaction - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(5);
						mlWalletLogout();
					}


					public void cashInViaBankInvalidAmount_STW_TC_05() throws Exception {
						HeaderChildNode("Cash In Via Bank Invalid Amount");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						selectBankAndInputAmount("0");
						explicitWaitVisibility(MLWalletCashInBank.objInvalidAmountMsg, 10);
						if (verifyElementPresent(MLWalletCashInBank.objInvalidAmountMsg, getTextVal(MLWalletCashInBank.objInvalidAmountMsg, "Error Message"))) {
							String sInvalidAmountErrorMsg = getText(MLWalletCashInBank.objInvalidAmountMsg);
							String sExpectedErrorMsg = "The amount should not be less than 1";
							assertionValidation(sInvalidAmountErrorMsg, sExpectedErrorMsg);
							logger.info("STW_TC_05, The amount should not be less than 1 - Error Message is validated");
							extentLoggerPass("STW_TC_05", "STW_TC_05, The amount should not be less than 1 - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(3);
						mlWalletLogout();
					}

					public void cashInViaBankNavigation_STW_TC_06() throws Exception {
						HeaderChildNode("Cash In Via Bank Navigation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						verifyElementPresentAndClick(MLWalletCashInBank.objCashIn,getTextVal(MLWalletCashInBank.objCashIn,"Icon"));
						explicitWaitVisibility(MLWalletCashInBank.objCashInPage, 10);
						if(verifyElementPresent(MLWalletCashInBank.objCashInPage,getTextVal(MLWalletCashInBank.objCashInPage,"Page"))){
							logger.info("STW_TC_06, Navigated to Cash In Page Validated");
							extentLoggerPass("STW_TC_06", "STW_TC_06, Navigated to Cash In Page Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout();
					}

					public void cashInUIValidation_STW_TC_07() throws Exception {
						HeaderChildNode("Cash In Page UI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						verifyElementPresentAndClick(MLWalletCashInBank.objCashIn,getTextVal(MLWalletCashInBank.objCashIn,"Icon"));
						explicitWaitVisibility(MLWalletCashInBank.objCashInPage, 10);
						if(verifyElementPresent(MLWalletCashInBank.objCashInPage,getTextVal(MLWalletCashInBank.objCashInPage,"Page"))){
							verifyElementPresent(MLWalletCashInBank.objCashInOption,getTextVal(MLWalletCashInBank.objCashInOption,"Header"));
							verifyElementPresent(MLWalletCashInBank.objMyBankAccount,getTextVal(MLWalletCashInBank.objMyBankAccount,"Option"));
							verifyElementPresent(MLWalletCashInBank.objBranchName,getTextVal(MLWalletCashInBank.objBranchName,"Option"));
							logger.info("STW_TC_07, Cash In Page UI validated");
							extentLoggerPass("STW_TC_07", "STW_TC_07, Cash In Page UI validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout();
					}

					public void cashInPageBackArrowBtnValidation_STW_TC_08() throws Exception {
						HeaderChildNode("Cash In Page Back Button Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						verifyElementPresentAndClick(MLWalletCashInBank.objCashIn,getTextVal(MLWalletCashInBank.objCashIn,"Icon"));
						explicitWaitVisibility(MLWalletCashInBank.objCashInPage, 10);
						if(verifyElementPresent(MLWalletCashInBank.objCashInPage,getTextVal(MLWalletCashInBank.objCashInPage,"Page"))){
						waitTime(2000);
						verifyElementPresentAndClick(SendTransferPage.objBackArrow,"Cash In Back Button");
						}
						explicitWaitVisible(MLWalletLoginPage.objAvailableBalance, 10);
						if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
							logger.info("STW_TC_08, Cash In Page Back Button validated");
							extentLoggerPass("STW_TC_08", "STW_TC_08, Cash In Page Back Button validated");
							System.out.println("-----------------------------------------------------------");
						}
						mlWalletLogout();
					}

					public void cashInSelectBankPageUIValidation_STW_TC_09() throws Exception {
						HeaderChildNode("Cash In Select Bank Page UI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						verifyElementPresentAndClick(MLWalletCashInBank.objCashIn,getTextVal(MLWalletCashInBank.objCashIn,"Icon"));
						explicitWaitVisibility(MLWalletCashInBank.objMyBankAccount, 10);
						verifyElementPresentAndClick(MLWalletCashInBank.objMyBankAccount,getTextVal(MLWalletCashInBank.objMyBankAccount,"Option"));
						explicitWaitVisibility(MLWalletCashInBank.objSelectABank, 10);
						if(verifyElementPresent(MLWalletCashInBank.objSelectABank,getTextVal(MLWalletCashInBank.objSelectABank,"Header"))){
							verifyElementPresent(MLWalletCashInBank.objSearchBank,"Search Bank Input Field");
							if (verifyElementDisplayed(MLWalletCashInBank.objBanks)) {
								List<WebElement> values = findElements(MLWalletCashInBank.objBanks);
								for (int i = 0; i < values.size(); i++) {
									String savedRecipientName = values.get(i).getText();
									logger.info("Bank : " + savedRecipientName + " is displayed");
									extentLogger(" ", "Bank : " + savedRecipientName + " is displayed");
								}
							}
							logger.info("STW_TC_09, Cash In Select Bank Page UI validated");
							extentLoggerPass("STW_TC_09", "STW_TC_09, Cash In Select Bank Page UI validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(2);
						mlWalletLogout();
					}


					public void cashInViaBankSearchInvalidBank_STW_TC_10(String status) throws Exception {
						HeaderChildNode("Cash In Via Bank Invalid Bank");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						verifyElementPresentAndClick(MLWalletCashInBank.objCashIn,getTextVal(MLWalletCashInBank.objCashIn,"Icon"));
						explicitWaitVisibility(MLWalletCashInBank.objMyBankAccount, 10);
						verifyElementPresentAndClick(MLWalletCashInBank.objMyBankAccount,getTextVal(MLWalletCashInBank.objMyBankAccount,"Option"));
						explicitWaitVisibility(MLWalletCashInBank.objSelectABank, 10);
						if(verifyElementPresent(MLWalletCashInBank.objSelectABank,getTextVal(MLWalletCashInBank.objSelectABank,"Header"))) {
							type(MLWalletCashInBank.objSearchBank,prop.getproperty("Invalid_BankName"), "Search Bank Input Field");
							if(verifyElementPresent(MLWalletCashInBank.objNoRecentTransactionTxt,getTextVal(MLWalletCashInBank.objNoRecentTransactionTxt,"Text"))){
								logger.info("STW_TC_10, Cash In Select Bank Page UI validated");
								extentLoggerPass("STW_TC_10", "STW_TC_10, Cash In Select Bank Page UI validated");
								System.out.println("-----------------------------------------------------------");
							}
						}
						if(status.equals("true"))
						{
						backArrowBtn(2);
						mlWalletLogout();
						}
					}

					public void cashInViaBankSelectBankPageBackBtnValidation_STW_TC_11() throws Exception {
						HeaderChildNode("Cash In Via Bank Select Bank Page BackArrow Button Validation");
						cashInViaBankSearchInvalidBank_STW_TC_10("false");
						verifyElementPresentAndClick(MLWalletCashInBank.objSelectBankBackBtn,"Select A Bank Page Back Button");
						backArrowBtn(1);
						if(verifyElementPresent(MLWalletCashInBank.objCashIn,getTextVal(MLWalletCashInBank.objCashIn,"Page"))){
							logger.info("STW_TC_11, Select Bank Page Back Button validated");
							extentLoggerPass("STW_TC_11", "STW_TC_11, Select Bank Page Back Button validated");
							System.out.println("-----------------------------------------------------------");
						}
						mlWalletLogout();
					}

					public void cashInViaBankDragonPayPageUIValidation_STW_TC_12(String status) throws Exception {
						HeaderChildNode("Cash In Via Bank Dragon Pay Page UI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						verifyElementPresent(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Icon"));
						click(MLWalletCashInBank.objCashIn, getTextVal(MLWalletCashInBank.objCashIn, "Icon"));
						click(MLWalletCashInBank.objMyBankAccount, getTextVal(MLWalletCashInBank.objMyBankAccount, "Button"));
						explicitWaitVisibility(MLWalletCashInBank.objSelectABank, 10);
						verifyElementPresent(MLWalletCashInBank.objSelectABank, getTextVal(MLWalletCashInBank.objSelectABank, "Page"));
						click(MLWalletCashInBank.objTestBankOnline, getTextVal(MLWalletCashInBank.objTestBankOnline, "Bank"));
						explicitWaitVisibility(MLWalletCashInBank.objDragonPay, 10);
						if(verifyElementPresent(MLWalletCashInBank.objDragonPay, getTextVal(MLWalletCashInBank.objDragonPay, "Page"))) {
							verifyElementPresent(MLWalletCashInBank.objBankCashIn, getTextVal(MLWalletCashInBank.objBankCashIn, "Text"));
							verifyElementPresent(MLWalletCashInBank.objAmountEditField, "Amount Text Field");
							verifyElementPresent(MLWalletCashInBank.objNextBtn1, getTextVal(MLWalletCashInBank.objNextBtn1, "Button"));
							logger.info("STW_TC_12, Cash In Via Bank Dragon Pay Page UI validated");
							extentLoggerPass("STW_TC_12", "STW_TC_12, Cash In Via Bank Dragon Pay Page UI validated");
							System.out.println("-----------------------------------------------------------");
						}
						if(status.equals("true"))
						{
						backArrowBtn(3);
						mlWalletLogout();
						}
					}

					public void cashInViaBankDragonPayBackBtnValidation_STW_TC_13() throws Exception {
						HeaderChildNode("Cash In Via Bank Dragon Pay Back Button Validation");
						cashInViaBankDragonPayPageUIValidation_STW_TC_12("false");
						verifyElementPresentAndClick(MLWalletCashInBank.objDragonPayBackBtn,"Dragon Pay Back Button");
						waitTime(2000);
						if(verifyElementPresent(MLWalletCashInBank.objSelectABank, getTextVal(MLWalletCashInBank.objSelectABank, "Page"))){
							logger.info("STW_TC_13, Cash In Via Bank Dragon Pay Back Button validated");
							extentLoggerPass("STW_TC_13", "STW_TC_13, Cash In Via Bank Dragon Pay Back Button validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(2);
						mlWalletLogout();
					}


					public void cashInViaBankReviewTransactionPageUIValidation_STW_TC_14(String status) throws Exception {
						HeaderChildNode("Cash In Via Bank Review Transaction Page UI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("New_Branch_Verified"));
						selectBankAndInputAmount("100");
						dragonPayChargesMsgValidation();
						if(verifyElementPresent(MLWalletCashInBank.objReviewTransaction, getTextVal(MLWalletCashInBank.objReviewTransaction,"Page"))) {
							verifyElementPresent(MLWalletCashInBank.objReceiverName, getTextVal(MLWalletCashInBank.objReceiverName,"Receiver's Name"));
							verifyElementPresent(MLWalletCashInBank.objBankName, getTextVal(MLWalletCashInBank.objBankName,"Bank Name"));
							verifyElementPresent(MLWalletCashInBank.objPrincipalAmount, getTextVal(MLWalletCashInBank.objPrincipalAmount,"Principal Amount"));
							verifyElementPresent(MLWalletCashInBank.objServiceFee, getTextVal(MLWalletCashInBank.objServiceFee,"Service Fee"));
							verifyElementPresent(MLWalletCashInBank.objEmail, getTextVal(MLWalletCashInBank.objEmail,"Email"));
							Swipe("UP", 1);
							verifyElementPresent(MLWalletCashInBank.objBankTransferCutOffTime, getTextVal(MLWalletCashInBank.objBankTransferCutOffTime, "Message"));
							verifyElementPresent(MLWalletCashInBank.objNextBtn1, getTextVal(MLWalletCashInBank.objNextBtn1, "Button"));
							logger.info("STW_TC_14, Cash In Via Bank Review Transaction Page UI validated");
							extentLoggerPass("STW_TC_14", "STW_TC_14, Cash In Via Bank Review Transaction Page UI validated");
							System.out.println("-----------------------------------------------------------");
						}
						if(status.equals("true"))
						{
						backArrowBtn(4);
						mlWalletLogout();
						}
					}

					public void cashInViaBankReviewTransactionBackBtnValidation_STW_TC_15() throws Exception {
						HeaderChildNode("Cash In Via Bank Review Transaction Back Button Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						cashInViaBankReviewTransactionPageUIValidation_STW_TC_14("false");
						verifyElementPresentAndClick(MLWalletCashInBank.objReviewTransactionBackBtn,"Review Transaction Back Button");
						explicitWaitVisibility(MLWalletCashInBank.objDragonPay, 10);
						if(verifyElementPresent(MLWalletCashInBank.objDragonPay,getTextVal(MLWalletCashInBank.objDragonPay,"Page"))){
							logger.info("STW_TC_15, Cash In Via Bank Review Transaction Back Button validated");
							extentLoggerPass("STW_TC_15", "STW_TC_15, Cash In Via Bank Review Transaction Back Button validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(3);
						mlWalletLogout();
					}

					public void cashInViaBankPendingTransaction_CIBA_TC_17() throws Exception {
						HeaderChildNode("Cash In Via Bank Pending Transaction");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						selectBankAndInputAmount("108");
						dragonPayChargesMsgValidation();
						reviewTransactionValidation();
						enterOTP(prop.getproperty("Valid_OTP"));
						bankUserLogin(prop.getproperty("Valid_LoginId"), prop.getproperty("Valid_Password"));
						click(MLWalletCashInBank.objContinueBtn,"Continue Button");
						explicitWaitVisibility(MLWalletCashInBank.objPayBtn, 10);
						click(MLWalletCashInBank.objPayBtn,getTextVal(MLWalletCashInBank.objPayBtn,"Button"));
						explicitWaitVisibility(MLWalletCashInBank.objBankReferenceNumber, 10);
						verifyElementPresent(MLWalletCashInBank.objBankReferenceNumber,getTextVal(MLWalletCashInBank.objBankReferenceNumber,"Reference Number"));
						verifyElementPresent(MLWalletCashInBank.objStatus,getTextVal(MLWalletCashInBank.objStatus,"Status"));
						verifyElementPresent(MLWalletCashInBank.objMessage,getTextVal(MLWalletCashInBank.objMessage,"Message"));
						verifyElementPresentAndClick(MLWalletCashInBank.objCompleteTransactionBtn,getTextVal(MLWalletCashInBank.objCompleteTransactionBtn,"Button"));
						verifyRecentTransaction();
						explicitWaitVisibility(MLWalletCashInBank.objCashIn, 10);
						if(verifyElementPresent(MLWalletCashInBank.objCashIn,getTextVal(MLWalletCashInBank.objCashIn,"Transaction"))){
							verifyElementPresent(MLWalletCashInBank.objPendingStatus,getTextVal(MLWalletCashInBank.objPendingStatus,"Status"));
							String sStatus = getText(MLWalletCashInBank.objPendingStatus);
							String pendingStatus=sStatus.substring(2, 9);	
							String sExpectedStatus = "Pending";
							assertionValidation(pendingStatus,sExpectedStatus);
							logger.info("CIBA_TC_17, An entry in recent transaction for current Cash In should be present with status pending validated");
							extentLoggerPass("CIBA_TC_17", "CIBA_TC_17, An entry in recent transaction for current Cash In should be present with status pending validated");
							System.out.println("-----------------------------------------------------------");
						}
						mlWalletLogout();
						
					}

					public void cashInViaBankWithExistingPendingTransaction_CIBA_TC_20() throws Exception {
						HeaderChildNode("Cash In Via Bank With Existing Pending Transaction");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("New_Branch_Verified"));
						verifyElementPresent(MLWalletTransactionHistoryPage.objRecentTransaction, getText(MLWalletTransactionHistoryPage.objRecentTransaction));
						Swipe("UP", 2);
						waitTime(2000);
						click(MLWalletTransactionHistoryPage.objSeeMoreBtn, "See More Button");
						waitTime(10000);
						Swipe("right", 2);	
						click(MLWalletTransactionHistoryPage.objCashInTab, "Cash In");
						Thread.sleep(3000);
						scrollToVertical("Pending");
						if(verifyElementPresent(MLWalletCashInBank.objPending,getTextVal(MLWalletCashInBank.objPending,"Status"))){
							click(MLWalletCashInBank.objTransactionHistoryBackBtn,"Transaction History Back Button");
							Swipe("DOWN",1);
							selectBankAndInputAmount("100");
							dragonPayChargesMsgValidation();
							reviewTransactionValidation();
							enterOTP(prop.getproperty("Valid_OTP"));
							bankUserLogin(prop.getproperty("Valid_LoginId"), prop.getproperty("Valid_Password"));
							click(MLWalletCashInBank.objWebContinueBtn,"Continue Button");
							click(MLWalletCashInBank.objPayBtn,getTextVal(MLWalletCashInBank.objPayBtn,"Button"));
							verifyElementPresent(MLWalletCashInBank.objBankReferenceNumber,getTextVal(MLWalletCashInBank.objBankReferenceNumber,"Reference Number"));
							verifyElementPresent(MLWalletCashInBank.objStatus,getTextVal(MLWalletCashInBank.objStatus,"Status"));
							verifyElementPresent(MLWalletCashInBank.objMessage,getTextVal(MLWalletCashInBank.objMessage,"Message"));
							verifyElementPresentAndClick(MLWalletCashInBank.objCompleteTransactionBtn,getTextVal(MLWalletCashInBank.objCompleteTransactionBtn,"Button"));
							Swipe("DOWN",2);
							Swipe("UP",1);
							if(verifyElementPresent(MLWalletCashInBank.objCashIn,getTextVal(MLWalletCashInBank.objCashIn,"Transaction"))){
								verifyElementPresent(MLWalletCashInBank.objPending,getTextVal(MLWalletCashInBank.objPending,"Status"));
								logger.info("CIBA_TC_20, Cash In Via Bank With Existing Pending Transaction Validated");
								extentLoggerPass("CIBA_TC_20", "CIBA_TC_20, Cash In Via Bank With Existing Pending Transaction validated");
								System.out.println("-----------------------------------------------------------");
							}
						}
					}

					public void cancelButtonValidationInDragonPayPopUp_CIBA_TC_21() throws Exception {
						HeaderChildNode("Cancel Button Validation In Dragon Pay PopUp");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						selectBankAndInputAmount("100");
						verifyElementPresent(MLWalletCashInBank.objDragonPayChargesMsg, getTextVal(MLWalletCashInBank.objDragonPayChargesMsg, "Message"));
						String sDragonPayChargesMsg = getText(MLWalletCashInBank.objDragonPayChargesMsg);
						String sExpectedDragonPayChargesMsg = "Dragon Pay charges a fee of 30 pesos for this transaction. Do you wish to continue with your transaction?";
						assertionValidation(sDragonPayChargesMsg, sExpectedDragonPayChargesMsg);
						click(MLWalletCashInBank.objCancelBtn, getTextVal(MLWalletCashInBank.objCancelBtn, "Button"));
						if(verifyElementPresent(MLWalletCashInBank.objDragonPay, getTextVal(MLWalletCashInBank.objDragonPay, "Page"))){
							logger.info("CIBA_TC_21, Cancel Button Validated In Dragon Pay PopUp");
							extentLoggerPass("CIBA_TC_21", "CIBA_TC_21, Cancel Button Validated In Dragon Pay PopUp");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(3);
						mlWalletLogout();
					}

					public void cashInViaBankInvalidAmountFieldValidation_CIBA_TC_23() throws Exception {
						HeaderChildNode("Cash In Via Bank Invalid Amount Field Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						selectBankAndInputAmount("");
						if (verifyElementPresent(MLWalletCashInBank.objInvalidAmountMsg1, getTextVal(MLWalletCashInBank.objInvalidAmountMsg1, "Error Message"))) {
							String sInvalidAmountErrorMsg = getText(MLWalletCashInBank.objInvalidAmountMsg1);
							String sExpectedErrorMsg = "Amount is required";
							assertionValidation(sInvalidAmountErrorMsg, sExpectedErrorMsg);
							logger.info("CIBA_TC_23, Amount is required - Error Message is validated");
							extentLoggerPass("CIBA_TC_23", "CIBA_TC_23, Amount is required - Error Message is validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(3);
						mlWalletLogout();
					}
					
					
					public void cashInViaBankTappingOutsideTheDragonPayPopupValidation_CIBA_TC_22() throws Exception {
						HeaderChildNode("Cash In Via Bank Tapping Outside the Dragon Pay Popup Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						selectBankAndInputAmount("100");
						verifyElementPresent(MLWalletCashInBank.objDragonPayChargesMsg, getTextVal(MLWalletCashInBank.objDragonPayChargesMsg, "Message"));
						String sDragonPayChargesMsg = getText(MLWalletCashInBank.objDragonPayChargesMsg);
						String sExpectedDragonPayChargesMsg = "Dragon Pay charges a fee of 30 pesos for this transaction. Do you wish to continue with your transaction?";
						assertionValidation(sDragonPayChargesMsg, sExpectedDragonPayChargesMsg);
						tapUsingCoordinates(500,1000);
						logger.info("Clicked OutSide the Dragon Pay Popup");
						if (verifyElementPresent(MLWalletCashInBank.objDragonPayChargesMsg, getTextVal(MLWalletCashInBank.objDragonPayChargesMsg, "Popup Message"))) {
							logger.info("CIBA_TC_22, Cash In Via Bank, After Tapping Outside the Dragon Pay Popup, Popup doesn't closed");
							extentLoggerPass("CIBA_TC_22", "CIBA_TC_22, Cash In Via Bank, After Tapping Outside the Dragon Pay Popup, Popup doesn't closed");
							System.out.println("-----------------------------------------------------------");
						}
						getDriver().resetApp();
					}

					public void cashInViaBankBuyerTierLevel_CIBA_TC_24() throws Exception {
						HeaderChildNode("Cash In Via Bank Buyer Tier Level");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Buyer_Tier"));
						selectBankAndInputAmount("100");
						dragonPayChargesMsgValidation();
						reviewTransactionValidation();
						waitTime(2000);
						if (verifyElementPresent(MLWalletCashInBank.objMaxLimitTxt, getTextVal(MLWalletCashInBank.objMaxLimitTxt, "Text Message"))) {
							String sErrorMessage = getText(MLWalletCashInBank.objMaxLimitTxt);
							String ExpectedTxt = "Bank Cash-in is not allowed for customers at this verification level. Please upgrade your account to use this service.";
							assertionValidation(sErrorMessage, ExpectedTxt);
							verifyElementPresentAndClick(MLWalletCashInBank.objUpgradeNowBtn,getTextVal(MLWalletCashInBank.objUpgradeNowBtn,"Button"));
							logger.info("CIBA_TC_24, Branch Cash-In is not allowed for customers at this verification level. Error Message is Validated");
							extentLoggerPass("CIBA_TC_24", "CIBA_TC_24, Branch Cash-In is not allowed for customers at this verification level. Error Message is Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(5);
						mlWalletLogout();
					}

					public void cashInViaBankSemiVerifiedUserMaxLimit_CIBA_TC_27() throws Exception {
						HeaderChildNode("Cash In Via Bank Maximum Limit");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Semi_Verified"));
						selectBankAndInputAmount("60000");
						dragonPayChargesMsgValidation();
						reviewTransactionValidation();
						waitTime(5000);
						if(verifyElementPresent(MLWalletCashInBank.objBankMaxLimitTxt,getTextVal(MLWalletCashInBank.objBankMaxLimitTxt,"Error Message"))) {
							String sErrorMsg = getText(MLWalletCashInBank.objBankMaxLimitTxt);
							String sExpectedErrorMsg = "The maximum Bank Cash-in per transaction set for your verification level is P50,000.00. Please try again.";
							assertionValidation(sErrorMsg, sExpectedErrorMsg);
							verifyElementPresentAndClick(MLWalletCashInBank.objUpgradeNowBtn,getTextVal(MLWalletCashInBank.objUpgradeNowBtn,"Button"));
							logger.info("CIBA_TC_27, To validate Maximum Limit of transaction");
							extentLoggerPass("CIBA_TC_27", "CIBA_TC_27, To validate Maximum Limit of transaction");
							waitTime(3000);
						}
						backArrowBtn(5);
						mlWalletLogout();
					}

					public void cashInViaBankFullyVerifiedUserMaxLimit_CIBA_TC_28() throws Exception {
						HeaderChildNode("Cash In Via Bank Maximum Limit");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						selectBankAndInputAmount("60000");
						dragonPayChargesMsgValidation();
						reviewTransactionValidation();
						waitTime(5000);
						if(verifyElementPresent(MLWalletCashInBank.objBankMaxLimitTxt,getTextVal(MLWalletCashInBank.objBankMaxLimitTxt,"Error Message"))) {
							String sErrorMsg = getText(MLWalletCashInBank.objBankMaxLimitTxt);
							String sExpectedErrorMsg = "The maximum Bank Cash-in per transaction set for your verification level is P50,000.00. Please try again.";
							assertionValidation(sErrorMsg, sExpectedErrorMsg);
							verifyElementPresentAndClick(MLWalletCashInBank.objOkBtnOne,getTextVal(MLWalletCashInBank.objOkBtnOne,"Button"));
							logger.info("CIBA_TC_28, To validate Maximum Limit of transaction");
							extentLoggerPass("CIBA_TC_28", "CIBA_TC_28, To validate Maximum Limit of transaction");
						}
						backArrowBtn(3);
						mlWalletLogout1();
					}

					public void cashInViaBankTransactionDetailsPageUIValidation_CIBA_TC_29() throws Exception {
						HeaderChildNode("Cash In Via Bank Transaction Details Page UI Validation");
						cashInViaBank_CIBA_TC_01("false");
//						verifyElementPresentAndClick(MLWalletCashInBank.objCompleteTransactionBtn,getTextVal(MLWalletCashInBank.objCompleteTransactionBtn,"Button"));
						verifyRecentTransaction();
						verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
						click(MLWalletCashInBank.objCashInTransaction, getTextVal(MLWalletCashInBank.objCashInTransaction, "Transaction"));
						explicitWaitVisibility(MLWalletCashOutPage.objTransactionDetails, 10);
						if (verifyElementPresent(MLWalletCashOutPage.objTransactionDetails, getTextVal(MLWalletCashOutPage.objTransactionDetails, "Page"))) {
							verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objDate,getTextVal(MLWalletTransactionHistoryPage.objDate,"Date"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionType, getTextVal(MLWalletTransactionHistoryPage.objTransactionType, "Payment Method"));
						//	verifyElementPresent(MLWalletTransactionHistoryPage.objBank,getTextVal(MLWalletTransactionHistoryPage.objBank,"Bank"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
							verifyElementPresent(MLWalletTransactionHistoryPage.objTotalCashIn, getTextVal(MLWalletTransactionHistoryPage.objTotalCashIn, "Total Cash In"));
							logger.info("CIBA_TC_29, Cash In Via Bank Transaction Details Page UI Validated");
							extentLoggerPass("CIBA_TC_29", "CIBA_TC_29, Cash In Via Bank Transaction Details Page UI Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout();
					}

					public void cashInViaBankDragonPayChagresPopUpValidation_CIBA_TC_32() throws Exception {
						HeaderChildNode("Cash In Via Bank Dragon Pay charges popup Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						selectBankAndInputAmount("100");
						explicitWaitVisibility(MLWalletCashInBank.objDragonPayChargesMsg, 10);
						if(verifyElementPresent(MLWalletCashInBank.objDragonPayChargesMsg, getTextVal(MLWalletCashInBank.objDragonPayChargesMsg, "Message"))) {
							String sDragonPayChargesMsg = getText(MLWalletCashInBank.objDragonPayChargesMsg);
							String sExpectedDragonPayChargesMsg = "Dragon Pay charges a fee of 30 pesos for this transaction. Do you wish to continue with your transaction?";
							assertionValidation(sDragonPayChargesMsg, sExpectedDragonPayChargesMsg);
							click(MLWalletCashInBank.objCancelBtn, "Cancel Button");
							logger.info("CIBA_TC_32, Cash In Via Bank Dragon Pay charges popup Validated");
							extentLoggerPass("CIBA_TC_32", "CIBA_TC_32, Cash In Via Bank Dragon Pay charges popup Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(3);
						mlWalletLogout1();
					}
					
					

					public void cashInViaBankAmountFieldValidation_CIBA_TC_49() throws Exception {
						HeaderChildNode("Cash In Via Bank, Amount Field with more than 2 decimals Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						selectBankAndInputAmount("1000.123");
						waitTime(2000);
						if (verifyElementPresent(MLWalletCashInBank.objInvalidDecimalAmountMsg, getTextVal(MLWalletCashInBank.objInvalidDecimalAmountMsg, "Pop Message"))) {
							String sMinimumTransactionPopupMsg = getText(MLWalletCashInBank.objInvalidDecimalAmountMsg);
							String sExpectedPopupMsg = "The amount must be limited to 2 decimal places";
							assertionValidation(sMinimumTransactionPopupMsg, sExpectedPopupMsg);
							logger.info("CIBA_TC_49, Cash In Via Bank, Amount Field with more than 2 decimals Error Msg validated");
							extentLoggerPass("CIBA_TC_49", "CIBA_TC_49, Cash In Via Bank, Amount Field with more than 2 decimals Error Msg validated");
							System.out.println("-----------------------------------------------------------");
						}
						getDriver().resetApp();
					}

					public void cashInViaBankTransactionWithValidMLPin_CIBA_TC_50() throws Exception {
						HeaderChildNode("Cash In Via Bank Transaction With Valid ML Pin");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						selectBankAndInputAmount("100");
						dragonPayChargesMsgValidation();
						reviewTransactionValidation();
						handleMpin("1111", "Entered MPIN");
						bankUserLogin(prop.getproperty("Valid_LoginId"), prop.getproperty("Valid_Password"));
						click(MLWalletCashInBank.objWebContinueBtn, "Continue Button");
						click(MLWalletCashInBank.objPayBtn, getTextVal(MLWalletCashInBank.objPayBtn, "Button"));
						verifyElementPresent(MLWalletCashInBank.objBankReferenceNumber, getTextVal(MLWalletCashInBank.objBankReferenceNumber, "Reference Number"));
						verifyElementPresent(MLWalletCashInBank.objStatus, getTextVal(MLWalletCashInBank.objStatus, "Status"));
						verifyElementPresent(MLWalletCashInBank.objMessage, getTextVal(MLWalletCashInBank.objMessage, "Message"));
						waitTime(3000);
						if (verifyElementPresent(MLWalletCashInBank.objSuccessMsg, getTextVal(MLWalletCashInBank.objSuccessMsg, "Message"))) {
							logger.info("CIBA_TC_50, Cash In Via Bank Transaction With Valid ML Pin validated");
							extentLoggerPass("CIBA_TC_50", "CIBA_TC_50, Cash In Via Bank Transaction With Valid ML Pin validated");
							System.out.println("-----------------------------------------------------------");
						}
						getDriver().resetApp();
					}


					public void cashInViaBankTransactionWithInValidMLPin_CIBA_TC_51() throws Exception {
						HeaderChildNode("Cash In Via Bank Transaction With Invalid ML Pin");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						selectBankAndInputAmount("100");
						dragonPayChargesMsgValidation();
						reviewTransactionValidation();
						handleMpin("1234", "Entered MPIN");
						waitTime(2000);
						if (verifyElementPresent(MLWalletCashInBank.objInvalidPINMsg, getTextVal(MLWalletCashInBank.objInvalidPINMsg, "Message"))) {
							String sActualErrorMsg = getText(MLWalletCashInBank.objInvalidPINMsg);
							String sExceptedErrorMsg = "You have entered an invalid PIN. Please try again.";
							assertionValidation(sActualErrorMsg,sExceptedErrorMsg);
							logger.info("CIBA_TC_51, Cash In Via Bank Transaction With Invalid ML Pin validated");
							extentLoggerPass("CIBA_TC_51", "CIBA_TC_51, Cash In Via Bank Transaction With Invalid ML Pin validated");
							System.out.println("-----------------------------------------------------------");
						}
						getDriver().resetApp();
					}


					public void cashInViaBankOTPPopupValidation_CIBA_TC_57() throws Exception {
						HeaderChildNode("Cash In Via Bank OTP Popup validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						selectBankAndInputAmount("100");
						dragonPayChargesMsgValidation();
						reviewTransactionValidation();
						waitTime(3000);
						if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
							verifyElementPresent(MLWalletLoginPage.objOTP,getTextVal(MLWalletLoginPage.objOTP,"One Time Pin"));
							logger.info("CIBA_TC_57, Cash In Via Bank, OTP popup validated");
							extentLoggerPass("CIBA_TC_57", "CIBA_TC_57, Cash In Via Bank, OTP popup validated");
							System.out.println("-----------------------------------------------------------");
						}
						getDriver().resetApp();
					}
		

					
					
					//============================================== Branch Locator ===================================//

					public void branchLocatorNavigation() throws Exception {
						Swipe("DOWN",2);
						if(verifyElementPresentAndClick(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Button"))){
							waitTime(5000);
							verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Page"));
							logger.info("Navigated to Branch Locator page");
						}else{
							logger.info("Not Navigated to Branch Locator Page");
						}
					}

					public void branchLocatorPageValidation() throws Exception {
						explicitWaitVisibility(MLWalletBranchLocatorPage.objSearchBranch, 10);
						if(verifyElementPresent(MLWalletBranchLocatorPage.objSearchBranch,getTextVal(MLWalletBranchLocatorPage.objSearchBranch,"Header"))) {
							verifyElementPresent(MLWalletBranchLocatorPage.obj24HoursOnly, getTextVal(MLWalletBranchLocatorPage.obj24HoursOnly, "Option"));
							verifyElementPresent(MLWalletBranchLocatorPage.objSearchBranchField, "Search Branch Input Field");
							verifyElementPresent(MLWalletBranchLocatorPage.objLuzon, getTextVal(MLWalletBranchLocatorPage.objLuzon, "Button"));
							click(MLWalletBranchLocatorPage.objLuzon, getTextVal(MLWalletBranchLocatorPage.objLuzon, "Button"));
							verifyElementPresent(MLWalletBranchLocatorPage.objVisayas, getTextVal(MLWalletBranchLocatorPage.objVisayas, "Button"));
							verifyElementPresent(MLWalletBranchLocatorPage.objMindanao, getTextVal(MLWalletBranchLocatorPage.objMindanao, "Button"));
							verifyElementPresent(MLWalletBranchLocatorPage.objMLUS, getTextVal(MLWalletBranchLocatorPage.objMLUS, "Button"));
							Swipe("UP",1);
							verifyElementPresent(MLWalletBranchLocatorPage.objBranchesNearYou, getTextVal(MLWalletBranchLocatorPage.objBranchesNearYou, "Map Header"));
						}
					}

				//==========================================================================================================//

					public void branchLocatorNavigationFromMPinPage_BL_TC_01() throws Exception {
						HeaderChildNode("Branch Locator Page Navigation from MPin Page and UI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						mlWalletLogout();
						branchLocatorNavigation();
						if(verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Page"))){
							branchLocatorPageValidation();
							logger.info("BL_TC_01, Branch Locator Page Navigation from MPin Page and UI Validated");
							extentLoggerPass("BL_TC_01","BL_TC_01, Branch Locator Page Navigation from MPin Page and UI Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout();
					}

					public void branchLocatorNavigationFromLogInPage_BL_TC_02() throws Exception {
						HeaderChildNode("Branch Locator page Navigation From Login Page and UI validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						mlWalletLogout();
						branchLocatorNavigation();
						if(verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Page"))){
							branchLocatorPageValidation();
							logger.info("BL_TC_02, Branch Locator Page Navigation from Login Page and UI Validated");
							extentLoggerPass("BL_TC_02","BL_TC_02, Branch Locator Page Navigation from Login Page and UI Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout();
					}

					public void branchLocatorHamburgerFunctionality_BL_TC_05() throws Exception {
						HeaderChildNode("Branch Locator Hamburger Button Functionality");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						mlWalletLogout();
						branchLocatorNavigation();
						verifyElementPresentAndClick(MLWalletBranchLocatorPage.objBranchLocatorHamburgerMenu,"Hamburger Menu Button");
						waitTime(3000);
						if(verifyElementPresent(MLWalletBranchLocatorPage.objHome,getTextVal(MLWalletBranchLocatorPage.objHome,"Option"))){
							verifyElementPresent(MLWalletBranchLocatorPage.objBranches,getTextVal(MLWalletBranchLocatorPage.objBranches,"Option"));
							verifyElementPresent(MLWalletBranchLocatorPage.objProductAndServices,getTextVal(MLWalletBranchLocatorPage.objProductAndServices,"Option"));
							verifyElementPresent(MLWalletBranchLocatorPage.objPromos,getTextVal(MLWalletBranchLocatorPage.objPromos,"Option"));
							verifyElementPresent(MLWalletBranchLocatorPage.objBlog,getTextVal(MLWalletBranchLocatorPage.objBlog,"Option"));
							verifyElementPresent(MLWalletBranchLocatorPage.objNewsLetters,getTextVal(MLWalletBranchLocatorPage.objNewsLetters,"Option"));
							verifyElementPresent(MLWalletBranchLocatorPage.objMLUSBtn,getTextVal(MLWalletBranchLocatorPage.objMLUSBtn,"Option"));
							verifyElementPresent(MLWalletBranchLocatorPage.objFAQ,getTextVal(MLWalletBranchLocatorPage.objFAQ,"Option"));
							verifyElementPresent(MLWalletBranchLocatorPage.objCareers,getTextVal(MLWalletBranchLocatorPage.objCareers,"Option"));
							verifyElementPresent(MLWalletBranchLocatorPage.objLogin,getTextVal(MLWalletBranchLocatorPage.objLogin,"Option"));
							verifyElementPresent(MLWalletBranchLocatorPage.objDownloadApp,getTextVal(MLWalletBranchLocatorPage.objDownloadApp,"Option"));
							logger.info("BL_TC_05, Branch Locator Hamburger Button Functionality Validated");
							extentLoggerPass("BL_TC_05","BL_TC_05, Branch Locator Hamburger Button Functionality Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout();
					}

					public void branchLocatorBranchesButtonFunctionality_BL_TC_07() throws Exception {
						HeaderChildNode("Branch Locator Branches Button Functionality");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						mlWalletLogout();
						branchLocatorNavigation();
						verifyElementPresentAndClick(MLWalletBranchLocatorPage.objBranchLocatorHamburgerMenu,"Hamburger Menu Button");
						waitTime(2000);
						verifyElementPresentAndClick(MLWalletBranchLocatorPage.objBranches,getTextVal(MLWalletBranchLocatorPage.objBranches,"Option"));
						waitTime(2000);
						if(verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Page"))){
							branchLocatorPageValidation();
							logger.info("BL_TC_07, Branch Locator Branches Button Functionality validated");
							extentLoggerPass("BL_TC_07","BL_TC_07, Branch Locator Branches Button Functionality validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout();
					}

					public void branchLocatorPromosFunctionality_BL_TC_08() throws Exception {
						HeaderChildNode("Branch Locator Promos Button Functionality");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						mlWalletLogout();
						branchLocatorNavigation();
						waitTime(2000);
						verifyElementPresentAndClick(MLWalletBranchLocatorPage.objBranchLocatorHamburgerMenu,"Hamburger Menu Button");
						waitTime(2000);
						verifyElementPresentAndClick(MLWalletBranchLocatorPage.objPromos,getTextVal(MLWalletBranchLocatorPage.objPromos,"Option"));
						waitTime(10000);
						if(verifyElementPresent(MLWalletBranchLocatorPage.objPromos,getTextVal(MLWalletBranchLocatorPage.objPromos,"Page"))){
							logger.info("BL_TC_08, Branch Locator Promos Button Functionality validated and App Navigated to Promos Page");
							extentLoggerPass("BL_TC_08","BL_TC_08, Branch Locator Promos Button Functionality validated and App Navigated to Promos Page");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout();
					}

					public void branchLocatorBlogFunctionality_BL_TC_09() throws Exception {
						HeaderChildNode("Branch Locator Blog Functionality");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						mlWalletLogout();
						branchLocatorNavigation();
						waitTime(2000);
						verifyElementPresentAndClick(MLWalletBranchLocatorPage.objBranchLocatorHamburgerMenu,"Hamburger Menu Button");
						waitTime(2000);
						verifyElementPresentAndClick(MLWalletBranchLocatorPage.objBlog,getTextVal(MLWalletBranchLocatorPage.objBlog,"Option"));
						waitTime(10000);
						if(verifyElementPresent(MLWalletBranchLocatorPage.objBlog,getTextVal(MLWalletBranchLocatorPage.objBlog,"Page"))){
							logger.info("BL_TC_09, Branch Locator Blog Button Functionality validated and App Navigated to Blog Page");
							extentLoggerPass("BL_TC_09","BL_TC_09, Branch Locator Blog Button Functionality validated and App Navigated to Blog Page");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout();
					}

					public void branchLocatorNewsLettersFunctionality_BL_TC_10() throws Exception {
						HeaderChildNode("Branch Locator NewsLetter Functionality");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						mlWalletLogout();
						branchLocatorNavigation();
						waitTime(2000);
						verifyElementPresentAndClick(MLWalletBranchLocatorPage.objBranchLocatorHamburgerMenu,"Hamburger Menu Button");
						waitTime(2000);
						verifyElementPresentAndClick(MLWalletBranchLocatorPage.objNewsLetters,getTextVal(MLWalletBranchLocatorPage.objNewsLetters,"Option"));
						waitTime(10000);
						if(verifyElementPresent(MLWalletBranchLocatorPage.objNewsLetters,getTextVal(MLWalletBranchLocatorPage.objNewsLetters,"Page"))) {
							logger.info("BL_TC_10, Branch Locator NewsLetter Button Functionality validated and App Navigated to NewsLetter Page");
							extentLoggerPass("BL_TC_10", "BL_TC_10, Branch Locator NewsLetter Button Functionality validated and App Navigated to NewsLetter Page");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout();
					}

					public void branchLocatorFAQFunctionality_BL_TC_11() throws Exception {
						HeaderChildNode("Branch Locator FAQ Functionality");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						mlWalletLogout();
						branchLocatorNavigation();
						waitTime(2000);
						verifyElementPresentAndClick(MLWalletBranchLocatorPage.objBranchLocatorHamburgerMenu,"Hamburger Menu Button");
						waitTime(2000);
						verifyElementPresentAndClick(MLWalletBranchLocatorPage.objFAQ,getTextVal(MLWalletBranchLocatorPage.objFAQ,"Option"));
						waitTime(10000);
						if(verifyElementPresent(MLWalletBranchLocatorPage.objFrequentlyAskedQuestions,getTextVal(MLWalletBranchLocatorPage.objFrequentlyAskedQuestions,"Page"))) {
							logger.info("BL_TC_11, Branch Locator FAQ Button Functionality validated and App Navigated to Frequently Asked Questions Page");
							extentLoggerPass("BL_TC_11", "BL_TC_11, Branch Locator FAQ Button Functionality validated and App Navigated to Frequently Asked Questions Page");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout();
					}
					
					
					
					
					//============================== Log Out  ===============================================//


					public void logOutMinimizeAndRelaunch_Lout_TC_03() throws Exception {
						HeaderChildNode("Log Out Minimize and relaunch the application");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						logger.info("Application Minimized for 5 Seconds");
						getDriver().runAppInBackground(Duration.ofSeconds(5));
						logger.info("Applicaton relaunched after 5 Seconds");
						if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
							logger.info("User should not be able to logout from the app");
							logger.info("Lout_TC_03, Log Out Minimize and relaunch the application validated");
							extentLoggerPass("Lout_TC_03", "Lout_TC_03, Log Out Minimize and relaunch the application validated");
							System.out.println("-----------------------------------------------------------");
						}
						mlWalletLogout();
					}

					public void logOutAppKillAndRelaunch_Lout_TC_04() throws Exception {
						HeaderChildNode("Kill Application and Relaunch");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						getDriver().closeApp();
						logger.info("Killed the Application");
						waitTime(3000);
						getDriver().launchApp();
						logger.info("Relaunch the Application");
						changeNumberPage();
						explicitWaitVisibility(MLWalletLoginPage.objLoginBtn, 10);
						if(verifyElementPresent(MLWalletLoginPage.objLoginBtn,getTextVal(MLWalletLoginPage.objLoginBtn,"page"))){
							logger.info("Lout_TC_04, After Killing and relaunch the Application, Application got logged off");
							extentLoggerPass("Lout_TC_04", "Lout_TC_04, After Killing and relaunch the Application, Application got logged off");
							System.out.println("-----------------------------------------------------------");
						}
						mlWalletLogout();
					}

					public void logOUtPopUpValidation_Lout_TC_05(String status) throws Exception {
						HeaderChildNode("LogOut Popup Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
						click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
						waitTime(3000);
						if(verifyElementPresent(MLWalletLogOutPage.objLogoOutPopupMsg,getTextVal(MLWalletLogOutPage.objLogoOutPopupMsg,"Pop up Message"))){
							String sLogOutPopupMsg = getText(MLWalletLogOutPage.objLogoOutPopupMsg);
							String sExpectedErrorMsg = "Are you sure you would like to logout?";
							assertionValidation(sLogOutPopupMsg,sExpectedErrorMsg);
							verifyElementPresent(MLWalletLogOutPage.objLogoutBtn,getTextVal(MLWalletLogOutPage.objLogoutBtn,"Button"));
							verifyElementPresent(MLWalletLogOutPage.objCancelBtn,getTextVal(MLWalletLogOutPage.objCancelBtn,"Button"));
							//click(MLWalletLogOutPage.objLogoutBtn,getTextVal(MLWalletLogOutPage.objLogoutBtn,"Button"));
							if(status.equals("true"))
							{
							click(MLWalletLogOutPage.objPopUpLogoutBtn,getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn,"Button"));
							}
							logger.info("Lout_TC_05, LogOut Popup validated");
							extentLoggerPass("Lout_TC_05", "Lout_TC_05, LogOut Popup validated");
							System.out.println("-----------------------------------------------------------");
						}
					}

					public void logOutPopUpCancelBtnValidation_Lout_TC_06() throws Exception {
						HeaderChildNode("LogOut PopUp Cancel Button Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						logOUtPopUpValidation_Lout_TC_05("false");
						click(MLWalletLogOutPage.objCancelBtn,getTextVal(MLWalletLogOutPage.objCancelBtn,"Button"));
						if(verifyElementPresent(MLWalletLogOutPage.objHome,getTextVal(MLWalletLogOutPage.objHome,"Button"))){
							getDriver().launchApp();
							logger.info("Lout_TC_06, Popup disappeared after clicking on Cancel Button, LogOut PopUp Cancel Button validated");
							extentLoggerPass("Lout_TC_06", "Lout_TC_06, Popup disappeared after clicking on Cancel Button, LogOut PopUp Cancel Button validated");
							System.out.println("-----------------------------------------------------------");
						}
					}

					public void logOutPopUpLogOutBtnValidation_Lout_TC_07() throws Exception {
						HeaderChildNode("LogOut PopUp LogOut Btn Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						logOUtPopUpValidation_Lout_TC_05("false");
						click(MLWalletLogOutPage.objPopUpLogoutBtn,getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn,"Button"));
						explicitWaitVisibility(MLWalletLogOutPage.objChangeNumber, 10);
						if(verifyElementPresent(MLWalletLogOutPage.objChangeNumber,getTextVal(MLWalletLogOutPage.objChangeNumber,"Page"))){
							logger.info("Lout_TC_07, Popup disappeared after clicking on Cancel Button, LogOut PopUp Cancel Button validated");
							extentLoggerPass("Lout_TC_07", "Lout_TC_07, Popup disappeared after clicking on Cancel Button, LogOut PopUp Cancel Button validated");
							System.out.println("-----------------------------------------------------------");
						}
					}

					public void logoutChangeNumberUIValidation_Lout_TC_08() throws Exception {
						HeaderChildNode("Log Out Change Number UI page Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						logOUtPopUpValidation_Lout_TC_05("false");
						click(MLWalletLogOutPage.objPopUpLogoutBtn,getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn,"Button"));
						explicitWaitVisibility(MLWalletLogOutPage.objChangeNumber, 10);
						if(verifyElementPresent(MLWalletLogOutPage.objChangeNumber,getTextVal(MLWalletLogOutPage.objChangeNumber,"Page"))){
							verifyElementPresent(MLWalletLoginPage.objTroubleSigningIn,getTextVal(MLWalletLoginPage.objTroubleSigningIn,"Link"));
							verifyElementPresent(MLWalletLoginPage.objBranchLocator,getTextVal(MLWalletLoginPage.objBranchLocator,"Link"));
							explicitWaitVisibility(MLWalletLoginPage.objAppVersionChangeNumber, 10);
							verifyElementPresent(MLWalletLoginPage.objAppVersionChangeNumber,getTextVal(MLWalletLoginPage.objAppVersionChangeNumber,"App Version"));
							logger.info("Lout_TC_08, Log Out Change Number UI page validated");
							extentLoggerPass("Lout_TC_08", "Lout_TC_08, Log Out Change Number UI page validated");
							System.out.println("-----------------------------------------------------------");
						}
					}

					public void logInWithChangeNumber_Lout_TC_09() throws Exception {
						HeaderChildNode("Log In With Change Number");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						mlWalletLogout();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
							logger.info("Lout_TC_09,Application Logged In Successfully with Change Number");
							extentLoggerPass("Lout_TC_09","Lout_TC_09, Application Logged In Successfully with Change Number");
							System.out.println("-----------------------------------------------------------");
						}
						mlWalletLogout1();
					}
					
					
					
					
					//============================ Home & DashBoard ========================================================//

					public void verificationTierPerksPageValidation() throws Exception {
						verifyElementPresent(MLWalletHomePage.objMaxBalanceText,getTextVal(MLWalletHomePage.objMaxBalanceText,"Header"));
						verifyElementPresent(MLWalletHomePage.objMaxBalanceAmount,getTextVal(MLWalletHomePage.objMaxBalanceAmount,"Max Balance"));
						verifyElementPresent(MLWalletHomePage.objSendingLimitsCashOut,getTextVal(MLWalletHomePage.objSendingLimitsCashOut,"Header"));
						List<WebElement> values = findElements(MLWalletHomePage.objSendingLimitTransactionTypeAndAmount);
						for (int i = 0; i < values.size(); i++) {
							if (i % 2 == 0) {
								String sTransactionType = values.get(i).getText();
								logger.info("Transaction Type : " + sTransactionType + " is displayed");
								extentLogger(" ", "Transaction Type : " + sTransactionType + " is displayed");
							}
							if (i % 2 != 0) {
								String sAmountRange = values.get(i).getText();
								logger.info("Amount Range : " + sAmountRange + " is displayed");
								extentLogger(" ", "Amount Range : " + sAmountRange + " is displayed");
							}
						}
						Swipe("UP",1);
						verifyElementPresent(MLWalletHomePage.objReceivingLimitsCashIn,getTextVal(MLWalletHomePage.objReceivingLimitsCashIn,"Header"));
						List<WebElement> values1 = findElements(MLWalletHomePage.objReceivingLimitsTransactionTypeAndAmount);
						for (int i = 0; i < values1.size(); i++) {
							if (i % 2 == 0) {
								String sTransactionType = values1.get(i).getText();
								logger.info("Transaction Type : " + sTransactionType + " is displayed");
								extentLogger(" ", "Transaction Type : " + sTransactionType + " is displayed");
							}
							if (i % 2 != 0) {
								String sAmountRange = values1.get(i).getText();
								logger.info("Amount Range : " + sAmountRange + " is displayed");
								extentLogger(" ", "Amount Range : " + sAmountRange + " is displayed");
							}
						}
						Swipe("UP",1);
						verifyElementPresent(MLWalletHomePage.objPurchaseLimits,getTextVal(MLWalletHomePage.objPurchaseLimits,"Header"));
						List<WebElement> values2 = findElements(MLWalletHomePage.objPurchaseLimitsTransactionTypeAndAmount);
						for (int i = 0; i < values2.size(); i++) {
							if (i % 2 == 0) {
								String sTransactionType = values2.get(i).getText();
								logger.info("Transaction Type : " + sTransactionType + " is displayed");
								extentLogger(" ", "Transaction Type : " + sTransactionType + " is displayed");
							}
							if (i % 2 != 0) {
								String sAmountRange = values2.get(i).getText();
								logger.info("Amount Range : " + sAmountRange + " is displayed");
								extentLogger(" ", "Amount Range : " + sAmountRange + " is displayed");
							}
						}
					}






				//=================================================================================================================//

					public void mlWalletHomePageUIValidation_HD_TC_01() throws Exception {
						HeaderChildNode("ML Wallet Home Page UI Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						if (verifyElementPresent(MLWalletHomePage.objAvailableBalanceHeader, getTextVal(MLWalletHomePage.objAvailableBalanceHeader, "Header"))) {
							verifyElementPresentAndClick(MLWalletHomePage.objEyeIcon,"Eye Icon");
							verifyElementPresent(MLWalletHomePage.objAvailableBalance,getTextVal(MLWalletHomePage.objAvailableBalance,"Available Balance"));
							verifyElementPresent(MLWalletHomePage.objCashInIcon,getTextVal(MLWalletHomePage.objCashInIcon,"Options"));
							verifyElementPresent(MLWalletHomePage.objSendTransferIcon,getTextVal(MLWalletHomePage.objSendTransferIcon,"Options"));
							verifyElementPresent(MLWalletHomePage.objCashOutIcon,getTextVal(MLWalletHomePage.objCashOutIcon,"Options"));
							verifyElementPresent(MLWalletHomePage.objPayBillsIcon,getTextVal(MLWalletHomePage.objPayBillsIcon,"Options"));
							verifyElementPresent(MLWalletHomePage.objBuyELoadIcon,getTextVal(MLWalletHomePage.objBuyELoadIcon,"Options"));
							verifyElementPresent(MLWalletHomePage.objShopItemsIcon,getTextVal(MLWalletHomePage.objShopItemsIcon,"Options"));
							verifyElementPresent(MLWalletHomePage.objMLLoans,getTextVal(MLWalletHomePage.objMLLoans,"Options"));
							verifyElementPresent(MLWalletHomePage.objUseQR,getTextVal(MLWalletHomePage.objUseQR,"Options"));
							verifyElementPresent(MLWalletHomePage.objRecentTransactions,getTextVal(MLWalletHomePage.objRecentTransactions,"Header"));
							Swipe("UP",1);
							List<WebElement> values = findElements(MLWalletHomePage.objTransactions);

							for(int i=4 ; i<values.size(); i+=4){
								String sTransactionType = values.get(i).getText();
								logger.info("Transaction Type : " + sTransactionType + " is displayed");
								extentLogger(" ", "Transaction Type : " + sTransactionType + " is displayed");
							}
							for(int i=2 ; i<values.size(); i+=4){
								String sAmount = values.get(i).getText();
								logger.info("Amount : " + sAmount + " is displayed");
								extentLogger(" ", "Amount : " + sAmount + " is displayed");
							}
							Swipe("UP",1);
							verifyElementPresent(MLWalletHomePage.objSeeMore,getTextVal(MLWalletHomePage.objSeeMore,"Button"));
							logger.info("HD_TC_01, ML Wallet Home Page UI Validated");
							extentLoggerPass("HD_TC_01", "HD_TC_01, ML Wallet Home Page UI Validated");
							System.out.println("-----------------------------------------------------------");
						}
						mlWalletLogout1();
					}

					public void mlWalletLogOutFromHomePage_HD_TC_02() throws Exception {
						HeaderChildNode("ML Wallet Logout from Home Page Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						verifyElementPresentAndClick(MLWalletHomePage.objHamburgerMenu,"Hamburger Menu");
						verifyElementPresentAndClick(MLWalletLogOutPage.objLogoutBtn,getTextVal(MLWalletLogOutPage.objLogoutBtn,"Button"));
						waitTime(3000);
						verifyElementPresentAndClick(MLWalletLogOutPage.objPopUpLogoutBtn,getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn,"Button on Popup"));
						explicitWaitVisibility(MLWalletLogOutPage.objChangeNumber, 10);
						if(verifyElementPresent(MLWalletLogOutPage.objChangeNumber,getTextVal(MLWalletLogOutPage.objChangeNumber,"Page"))){
							logger.info("HD_TC_02, ML Wallet Application Logged out from Home Page Validated");
							extentLoggerPass("HD_TC_02", "HD_TC_02, ML Wallet Application Logged out from Home Page Validated");
							System.out.println("-----------------------------------------------------------");
						}
						
					}

					public void mlWalletSettingsNavigationFromHomePage_HD_TC_03() throws Exception {
						HeaderChildNode("ML Wallet Settings Navigation from Hom Page");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						waitTime(2000);
						verifyElementPresentAndClick(MLWalletHomePage.objProfileIcon1,"Profile Icon");
						explicitWaitVisibility(MLWalletHomePage.objSettings, 10);
						if(verifyElementPresent(MLWalletHomePage.objSettings,getTextVal(MLWalletHomePage.objSettings,"Page"))){
							verifyElementPresent(MLWalletHomePage.objAccountDetails,getTextVal(MLWalletHomePage.objAccountDetails,"Option"));
							verifyElementPresent(MLWalletHomePage.objChangeMLPin,getTextVal(MLWalletHomePage.objChangeMLPin,"Option"));
//							verifyElementPresent(MLWalletHomePage.objBiometricsLogin,getTextVal(MLWalletHomePage.objBiometricsLogin,"Option"));
							verifyElementPresent(MLWalletHomePage.objAccountRecovery,getTextVal(MLWalletHomePage.objAccountRecovery,"Option"));
							verifyElementPresent(MLWalletHomePage.objNotification,getTextVal(MLWalletHomePage.objNotification,"Option"));
							verifyElementPresent(MLWalletHomePage.objDeleteAccount,getTextVal(MLWalletHomePage.objDeleteAccount,"Option"));
							logger.info("HD_TC_03, ML Wallet Settings Navigation from Hom Page Validated");
							extentLoggerPass("HD_TC_03", "HD_TC_03, ML Wallet Settings Navigation from Hom Page Validated");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout1();
						
					}

					public void mlWalletHomePageEyeIconValidation_HD_TC_04() throws Exception {
						HeaderChildNode("ML Wallet Home Page Eye Icon Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						verifyElementPresentAndClick(MLWalletHomePage.objEyeIcon,"Eye Icon");
						verifyElementPresent(MLWalletHomePage.objAvailableBalance,getTextVal(MLWalletHomePage.objAvailableBalance,"Available Balance"));
						verifyElementPresentAndClick(MLWalletHomePage.objEyeIcon,"Eye Icon");
						verifyElementPresent(MLWalletHomePage.objHiddenAvailableBalance,getTextVal(MLWalletHomePage.objHiddenAvailableBalance,"Available Balance"));
						logger.info("HD_TC_04, ML Wallet Home Page Eye Icon Validated");
						extentLoggerPass("HD_TC_04", "HD_TC_04, ML Wallet Home Page Eye Icon Validated");
						System.out.println("-----------------------------------------------------------");
						mlWalletLogout1();
					}

					public void mlWalletAccountTierLevelVerification_HD_TC_05() throws Exception {
						HeaderChildNode("ML Wallet Account Tier Level Verification");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						verifyElementPresentAndClick(MLWalletHomePage.objIIcon,"i Icon");
						waitTime(4000);
						if(verifyElementPresent(MLWalletHomePage.objVerificationTierPerks,getTextVal(MLWalletHomePage.objVerificationTierPerks,"Page"))){
							verificationTierPerksPageValidation();
							logger.info("HD_TC_05, ML Wallet Account Tier Level Verification");
							extentLoggerPass("HD_TC_05", "HD_TC_05, ML Wallet Account Tier Level Verification");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout();
					}

					public void mlWalletHomePageRecentTransaction_HD_TC_06() throws Exception {
						HeaderChildNode("ML Wallet Home Page Recent Transaction validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						verifyElementPresent(MLWalletHomePage.objRecentTransactions,getTextVal(MLWalletHomePage.objRecentTransactions,"Header"));
						Swipe("UP",1);
						List<WebElement> values = findElements(MLWalletHomePage.objTransactions);

						for(int i=4 ; i<values.size(); i+=4){
							String sTransactionType = values.get(i).getText();
							logger.info("Transaction Type : " + sTransactionType + " is displayed");
							extentLogger(" ", "Transaction Type : " + sTransactionType + " is displayed");
						}
						for(int i=2 ; i<values.size(); i+=4){
							String sAmount = values.get(i).getText();
							logger.info("Amount : " + sAmount + " is displayed");
							extentLogger(" ", "Amount : " + sAmount + " is displayed");
						}
						Swipe("UP",1);
						verifyElementPresent(MLWalletHomePage.objSeeMore,getTextVal(MLWalletHomePage.objSeeMore,"Button"));
						logger.info("HD_TC_06, ML Wallet Home Page Recent Transaction validated");
						extentLoggerPass("HD_TC_06", "HD_TC_06, ML Wallet Home Page Recent Transaction validated");
						System.out.println("-----------------------------------------------------------");
						mlWalletLogout1();
					}

					public void mlWalletHomePageWithdrawCash_HD_TC_07() throws Exception {
						HeaderChildNode("ML Wallet Home Page WithDraw Cash");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						verifyElementPresentAndClick(MLWalletHomePage.objHamburgerMenu,"Hamburger Menu");
						explicitWaitVisibility(MLWalletHomePage.objTransaction, 10);
						verifyElementPresentAndClick(MLWalletHomePage.objTransaction,getTextVal(MLWalletHomePage.objTransaction,"Option"));
						verifyElementPresentAndClick(MLWalletHomePage.objWithdrawCash,getTextVal(MLWalletHomePage.objWithdrawCash,"Option"));
						waitTime(3000);
						if(verifyElementPresent(MLWalletCashOutPage.objCashOutPage,getTextVal(MLWalletCashOutPage.objCashOutPage,"Page"))){
							logger.info("HD_TC_07, After clicking on Withdraw cash from Hamburger menu of Home Page, Application Navigated directly to CashOut Page is validated");
							extentLoggerPass("HD_TC_07", "HD_TC_07, After clicking on Withdraw cash from Hamburger menu of Home Page, Application Navigated directly to CashOut Page is validated");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(1);
							click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
							waitTime(4000);
							click(MLWalletLogOutPage.objPopUpLogoutBtn, getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn, "Button"));
							waitTime(3000);
							click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
						}
						
						
						
					}


					public void mlWalletHomePageTopUpMLWallet_HD_TC_08() throws Exception {
						HeaderChildNode("ML Wallet Home Page Top Up ML Wallet");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						verifyElementPresentAndClick(MLWalletHomePage.objHamburgerMenu,"Hamburger Menu");
						verifyElementPresentAndClick(MLWalletHomePage.objTransaction,getTextVal(MLWalletHomePage.objTransaction,"Option"));
						verifyElementPresentAndClick(MLWalletHomePage.objTopUpMLWallet,getTextVal(MLWalletHomePage.objTopUpMLWallet,"Option"));
						waitTime(3000);
						if(verifyElementPresent(MLWalletHomePage.objBranchCashIn,getTextVal(MLWalletHomePage.objBranchCashIn,"Page"))){
							logger.info("HD_TC_08, After clicking on Top Up ML Wallet from Hamburger menu of Home Page, Application Navigated directly to CashIn ML Branch Page is validated");
							extentLoggerPass("HD_TC_08", "HD_TC_08, After clicking on Top Up ML Wallet from Hamburger menu of Home Page, Application Navigated directly to CashIn ML Branch Page is validated");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(1);
							click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
							waitTime(4000);
							click(MLWalletLogOutPage.objPopUpLogoutBtn, getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn, "Button"));
							waitTime(3000);
							click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
						}
					}

					public void mlWalletHomePageShopHD_TC_09() throws Exception {
						HeaderChildNode("ML Wallet Home Page Shop");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						verifyElementPresentAndClick(MLWalletHomePage.objHamburgerMenu,"Hamburger Menu");
						verifyElementPresentAndClick(MLWalletHomePage.objShop,getTextVal(MLWalletHomePage.objShop,"Option"));
						waitTime(10000);
						if(verifyElementPresent(MLWalletHomePage.objMLShopPage,getTextVal(MLWalletHomePage.objMLShopPage,"Page"))){
							logger.info("HD_TC_09, After clicking on Shop from Hamburger menu of Home Page, Application Navigated directly ML Shop Page is validated");
							extentLoggerPass("HD_TC_09", "HD_TC_09, After clicking on Shop from Hamburger menu of Home Page, Application Navigated directly to ML Shop Page is validated");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(1);
							click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
							waitTime(4000);
							click(MLWalletLogOutPage.objPopUpLogoutBtn, getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn, "Button"));
							waitTime(3000);
							click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
						}
					}

					public void mlWalletHomePageKwartaPadalaRatesValidation_HD_TC_10() throws Exception {
						HeaderChildNode("ML Wallet Home Page Kwarta Padala Rates Validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu1,"Hamburger Menu");
						explicitWaitVisibility(MLWalletHomePage.objKwartaPadalaRatesBtn, 10);
						verifyElementPresentAndClick(MLWalletHomePage.objKwartaPadalaRatesBtn,"Kwarta Padala Rates");
						waitTime(2000);
						if (verifyElementPresent(MLWalletHomePage.objKwartaPadalaRates,"Kwarta Padala Rates")) {
							List<WebElement> values = findElements(MLWalletHomePage.objKwartaPadalaRates);
							for (int i = 0; i < values.size(); i++) {
								if (i % 2 != 0) {
									String sRates = values.get(i).getText();
									logger.info("Rates : " + sRates + " is displayed");
									extentLogger(" ", "Rates : " + sRates + " is displayed");
								}
								if (i % 2 == 0) {
									String sAmountRange = values.get(i).getText();
									logger.info("Amount Range : " + sAmountRange + " is displayed");
									extentLogger(" ", "Amount Range : " + sAmountRange + " is displayed");
								}
							}
							logger.info("HD_TC_10, ML Wallet Home Page Kwarta Padala Rates validated");
							extentLoggerPass("HD_TC_10", "HD_TC_10, ML Wallet Home Page Kwarta Padala Rates validated");
							System.out.println("-----------------------------------------------------------");
							waitTime(2000);
							backArrowBtn(1);
							click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
							waitTime(4000);
							click(MLWalletLogOutPage.objPopUpLogoutBtn, getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn, "Button"));
							waitTime(3000);
							click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
						}
					}

					public void mlWalletVerificationTierPerksNavigationFromHomePageHamburgerMenu_HD_TC_11() throws Exception {
						HeaderChildNode("ML Wallet Verification Tier Perks Navigation From Home Page Hamburger Menu");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu1,"Hamburger Menu");
						explicitWaitVisibility(MLWalletHomePage.objViewTier, 10);
						verifyElementPresentAndClick(MLWalletHomePage.objViewTier,getTextVal(MLWalletHomePage.objViewTier,"Option"));
						explicitWaitVisible(MLWalletHomePage.objVerificationTierPerks,10);
						if(verifyElementPresent(MLWalletHomePage.objVerificationTierPerks,getTextVal(MLWalletHomePage.objVerificationTierPerks,"Page"))){
							verificationTierPerksPageValidation();
							logger.info("HD_TC_11, ML Wallet Verification Tier Perks Navigation From Home Page Hamburger Menu Validated");
							extentLoggerPass("HD_TC_11", "HD_TC_11, ML Wallet Verification Tier Perks Navigation From Home Page Hamburger Menu Validated");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(1);
							click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
							waitTime(4000);
							click(MLWalletLogOutPage.objPopUpLogoutBtn, getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn, "Button"));
							waitTime(3000);
							click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
						}
					}

					public void mlWalletSupportPageNavigation_HD_TC_12() throws Exception {
						HeaderChildNode("ML Wallet Support page Navigation From Home Page Hamburger Menu");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu1,"Hamburger Menu");
						verifyElementPresentAndClick(MLWalletHomePage.objSupport,getTextVal(MLWalletHomePage.objSupport,"Option"));
						explicitWaitVisible(MLWalletHomePage.objTroubleSingingInPAge,20);
						if(verifyElementPresent(MLWalletHomePage.objTroubleSingingInPAge,getTextVal(MLWalletHomePage.objTroubleSingingInPAge,"Page"))){
							logger.info("HD_TC_12, After clicking on support Button on Hamburger menu, Application Navigated to Trouble Signing In Page");
							extentLoggerPass("HD_TC_12", "HD_TC_12, After clicking on support Button on Hamburger menu, Application Navigated to Trouble Signing In Page");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(1);
							click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
							waitTime(4000);
							click(MLWalletLogOutPage.objPopUpLogoutBtn, getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn, "Button"));
							waitTime(3000);
							click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
						}
					}

					public void mlWalletAboutPageNavigation_HD_TC_13() throws Exception {
						HeaderChildNode("ML Wallet About page Navigation From Home Page Hamburger Menu");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu1,"Hamburger Menu");
						verifyElementPresentAndClick(MLWalletHomePage.objAbout,getTextVal(MLWalletHomePage.objAbout,"Option"));
						explicitWaitVisible(MLWalletHomePage.objAbout,20);
						if(verifyElementPresent(MLWalletHomePage.objAbout,getTextVal(MLWalletHomePage.objAbout,"Page"))){
							logger.info("HD_TC_13, After clicking on About Button on Hamburger menu, Application Navigated to About Page");
							extentLoggerPass("HD_TC_13", "HD_TC_13, After clicking on About Button on Hamburger menu, Application Navigated to About Page");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(1);
							click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
							waitTime(4000);
							click(MLWalletLogOutPage.objPopUpLogoutBtn, getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn, "Button"));
							waitTime(3000);
							click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
						}
					}

					public void mlWalletVerificationTierPerksAsSemiVerifiedUser_HD_TC_14() throws Exception {
						HeaderChildNode("ML Wallet Verification Tier Perks Validation By logging as Semi-verified User");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Semi_Verified"));
						verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
						waitTime(2000);
						verifyElementPresentAndClick(MLWalletHomePage.objViewTierSemiVerified, getTextVal(MLWalletHomePage.objViewTierSemiVerified, "Option"));
						waitTime(2000);
						if (verifyElementPresent(MLWalletHomePage.objVerificationTierPerks, getTextVal(MLWalletHomePage.objVerificationTierPerks, "Page"))) {
							verifyElementPresentAndClick(MLWalletHomePage.objSemiVerified,getTextVal(MLWalletHomePage.objSemiVerified,"Tier Button"));
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();

							verifyElementPresentAndClick(MLWalletHomePage.objBranchVerified,getTextVal(MLWalletHomePage.objBranchVerified,"Tier Button"));
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();

							verifyElementPresentAndClick(MLWalletHomePage.objFullyVerified,getTextVal(MLWalletHomePage.objFullyVerified,"Tier Button"));
							explicitWaitVisibility(MLWalletHomePage.objTier, 10);
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();
							logger.info("HD_TC_14, ML Wallet Verification Tier Perks Validation By logging as Semi-verified User Validated");
							extentLoggerPass("HD_TC_14", "HD_TC_14, ML Wallet Verification Tier Perks Validation By logging as Semi-verified User Validated");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(1);
							click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
							waitTime(4000);
							click(MLWalletLogOutPage.objPopUpLogoutBtn, getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn, "Button"));
							waitTime(3000);
							click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
						}
					}

					public void mlWalletVerificationTierPerksAsFullyVerifiedUser_HD_TC_15() throws Exception {
						HeaderChildNode("ML Wallet Verification Tier Perks Validation By logging as Fully-verified User");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu1, "Hamburger Menu");
						waitTime(2000);
						verifyElementPresentAndClick(MLWalletHomePage.objViewTierFullyVerified, getTextVal(MLWalletHomePage.objViewTierFullyVerified, "Option"));
						explicitWaitVisible(MLWalletHomePage.objVerificationTierPerks,10);
						if (verifyElementPresent(MLWalletHomePage.objVerificationTierPerks, getTextVal(MLWalletHomePage.objVerificationTierPerks, "Page"))) {
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();
							
							Swipe("Left",1);
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();

							Swipe("Left",1);
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();
							logger.info("HD_TC_15, ML Wallet Verification Tier Perks Validation By logging as Fully-verified User Validated");
							extentLoggerPass("HD_TC_15", "HD_TC_15, ML Wallet Verification Tier Perks Validation By logging as Fully-verified User Validated");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(1);
							click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
							waitTime(4000);
							click(MLWalletLogOutPage.objPopUpLogoutBtn, getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn, "Button"));
							waitTime(3000);
							click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
						}
					}

					public void mlWalletVerificationTierPerksAsBranchVerifiedUser_HD_TC_17() throws Exception {
						HeaderChildNode("ML Wallet Verification Tier Perks Validation By logging as Branch-verified User");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
						verifyElementPresentAndClick(MLWalletHomePage.objViewTierBranchVerified, getTextVal(MLWalletHomePage.objViewTierBranchVerified, "Option"));
						explicitWaitVisible(MLWalletHomePage.objVerificationTierPerks,10);
						if (verifyElementPresent(MLWalletHomePage.objVerificationTierPerks, getTextVal(MLWalletHomePage.objVerificationTierPerks, "Page"))) {
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();
							logger.info("HD_TC_17, ML Wallet Verification Tier Perks Validation By logging as Branch-verified User Validated");
							extentLoggerPass("HD_TC_17", "HD_TC_17, ML Wallet Verification Tier Perks Validation By logging as Branch-verified User Validated");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(1);
							click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
							waitTime(4000);
							click(MLWalletLogOutPage.objPopUpLogoutBtn, getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn, "Button"));
							waitTime(3000);
							click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
						}
					}

					public void mlWalletVerificationTierPerksAsBuyerTierUser_HD_TC_18() throws Exception {
						HeaderChildNode("ML Wallet Verification Tier Perks Validation By logging as Buyer Tier User");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Buyer_Tier"));
						verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
						verifyElementPresentAndClick(MLWalletHomePage.objViewTierBuyierTierVerified, getTextVal(MLWalletHomePage.objViewTierBuyierTierVerified, "Option"));
						explicitWaitVisible(MLWalletHomePage.objVerificationTierPerks,10);
						if (verifyElementPresent(MLWalletHomePage.objVerificationTierPerks, getTextVal(MLWalletHomePage.objVerificationTierPerks, "Page"))) {
							verifyElementPresentAndClick(MLWalletHomePage.objSemiVerified,getTextVal(MLWalletHomePage.objSemiVerified,"Tier Button"));
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();
							verifyElementPresentAndClick(MLWalletHomePage.objBranchVerified,getTextVal(MLWalletHomePage.objBranchVerified,"Tier Button"));
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();

							verifyElementPresentAndClick(MLWalletHomePage.objFullyVerified,getTextVal(MLWalletHomePage.objFullyVerified,"Tier Button"));
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();
							logger.info("HD_TC_18, ML Wallet Verification Tier Perks Validation By logging as Buyer Tier User Validated");
							extentLoggerPass("HD_TC_18", "HD_TC_18, ML Wallet Verification Tier Perks Validation By logging as Buyer Tier  User Validated");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(1);
							click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
							waitTime(4000);
							click(MLWalletLogOutPage.objPopUpLogoutBtn, getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn, "Button"));
							waitTime(3000);
							click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
						}
					}

					public void mlWalletHamburgerMenuTransactionBtnValidation_HD_TC_19() throws Exception {
						HeaderChildNode("ML Wallet Hamburger Menu Transaction button validation");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Branch_Verified"));
						verifyElementPresentAndClick(MLWalletLogOutPage.objHamburgerMenu, "Hamburger Menu");
						verifyElementPresentAndClick(MLWalletHomePage.objTransaction,getTextVal(MLWalletHomePage.objTransaction,"Option"));
						explicitWaitVisibility(MLWalletHomePage.objWithdrawCash, 10);
						if(verifyElementPresent(MLWalletHomePage.objWithdrawCash,getTextVal(MLWalletHomePage.objWithdrawCash,"Option"))){
							verifyElementPresent(MLWalletHomePage.objTopUpMLWallet,getTextVal(MLWalletHomePage.objTopUpMLWallet,"Option"));
							logger.info("HD_TC_19, After clicking on Transactions option, Withdraw Cash and TopUp Ml Wallet options are displayed");
							extentLoggerPass("HD_TC_19", "HD_TC_19, After clicking on Transactions option, Withdraw Cash and TopUp Ml Wallet options are displayed");
							System.out.println("-----------------------------------------------------------");
							//backArrowBtn(1);
							click(MLWalletLogOutPage.objLogoutBtn, getTextVal(MLWalletLogOutPage.objLogoutBtn, "Button"));
							waitTime(4000);
							click(MLWalletLogOutPage.objPopUpLogoutBtn, getTextVal(MLWalletLogOutPage.objPopUpLogoutBtn, "Button"));
							waitTime(3000);
							click(MLWalletLogOutPage.objChangeNumber, getTextVal(MLWalletLogOutPage.objChangeNumber, "Link"));
						}
					}

					public void mlWalletHomePageIIconValidationAsBranchVerifiedTierUser_HD_TC_20() throws Exception {
						HeaderChildNode("ML Wallet Home Page I Icon Validation as Branch verified User");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("New_Branch_Verified"));
						verifyElementPresentAndClick(MLWalletHomePage.objIIcon,"i Icon");
						waitTime(2000);
						if(verifyElementPresent(MLWalletHomePage.objVerificationTierPerks,getTextVal(MLWalletHomePage.objVerificationTierPerks,"Page"))){
							verificationTierPerksPageValidation();
							logger.info("HD_TC_20, ML Wallet Home Page I Icon Validated as Branch verified User");
							extentLoggerPass("HD_TC_20", "HD_TC_20, ML Wallet Home Page I Icon Validated as Branch verified User");
							System.out.println("-----------------------------------------------------------");
							
						}
						backArrowBtn(1);
						mlWalletLogout();
					}

					public void mlWalletHomePageIIconValidationAsBuyerTierUser_HD_TC_21() throws Exception {
						HeaderChildNode("ML Wallet Home Page I Icon Validation as BuyerTier verified User");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Buyer_Tier"));
						verifyElementPresentAndClick(MLWalletHomePage.objIIcon, "i Icon");
						waitTime(3000);
						if (verifyElementPresent(MLWalletHomePage.objVerificationTierPerks, getTextVal(MLWalletHomePage.objVerificationTierPerks, "Page"))) {
							verifyElementPresentAndClick(MLWalletHomePage.objSemiVerified,getTextVal(MLWalletHomePage.objSemiVerified,"Tier Button"));
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();
							verifyElementPresentAndClick(MLWalletHomePage.objBranchVerified,getTextVal(MLWalletHomePage.objBranchVerified,"Tier Button"));
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();

							verifyElementPresentAndClick(MLWalletHomePage.objFullyVerified,getTextVal(MLWalletHomePage.objFullyVerified,"Tier Button"));
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();
							logger.info("HD_TC_21,ML Wallet Home Page I Icon Validated as BuyerTier verified User");
							extentLoggerPass("HD_TC_21", "HD_TC_21, ML Wallet Home Page I Icon Validated as BuyerTier verified User");
							System.out.println("-----------------------------------------------------------");						
						}
						backArrowBtn(1);
						mlWalletLogout();
					}


					public void mlWalletHomePageIIconValidationAsSemiVerifiedTierUser_HD_TC_22() throws Exception {
						HeaderChildNode("ML Wallet Home Page I Icon Validation as Semi verified Tier User");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Semi_Verified"));
						verifyElementPresentAndClick(MLWalletHomePage.objIIcon, "i Icon");
						waitTime(3000);
						if (verifyElementPresent(MLWalletHomePage.objVerificationTierPerks, getTextVal(MLWalletHomePage.objVerificationTierPerks, "Page"))) {
							verifyElementPresentAndClick(MLWalletHomePage.objSemiVerified,getTextVal(MLWalletHomePage.objSemiVerified,"Tier Button"));
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();

							verifyElementPresentAndClick(MLWalletHomePage.objBranchVerified,getTextVal(MLWalletHomePage.objBranchVerified,"Tier Button"));
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();

							verifyElementPresentAndClick(MLWalletHomePage.objFullyVerified,getTextVal(MLWalletHomePage.objFullyVerified,"Tier Button"));
							verifyElementPresent(MLWalletHomePage.objTier,getTextVal(MLWalletHomePage.objTier,"Header"));
							verificationTierPerksPageValidation();

							logger.info("HD_TC_22, ML Wallet Home Page I Icon Validated as Semi verified Tier User");
							extentLoggerPass("HD_TC_22", "HD_TC_22, ML Wallet Home Page I Icon Validated as Semi verified Tier User");
							System.out.println("-----------------------------------------------------------");
						}
						backArrowBtn(1);
						mlWalletLogout();
					}


					public void mlWalletHomePageIIconValidationAsFullyVerifiedTierUser_HD_TC_23() throws Exception {
						HeaderChildNode("ML Wallet Home Page I Icon Validation as Fully verified Tier User");
						waitTime(2000);
						changeNumberPage();
						clearField();
						mlWalletLogin(prop.getproperty("Fully_verified"));
						if(verifyElementNotPresent(MLWalletHomePage.objIIcon,5)){
							logger.info("HD_TC_23, ML Wallet Home Page I Icon not displayed For Fully-verified Tier user");
							extentLoggerPass("HD_TC_23", "HD_TC_23, ML Wallet Home Page I Icon not displayed For Fully-verified Tier user");
							System.out.println("-----------------------------------------------------------");
						}
						mlWalletLogout1();
					}
					
					
					
					//=============================================Pay Bills===============================================================//
					//=============================Generalized Methods=====================================================//

						public void getBillers(By sWebElement){
							List<WebElement> list = findElements(sWebElement);

							for(int i=0 ;i<list.size(); i++){
								if(i%2==1)
								{
								String billers= list.get(i).getText();
								logger.info(billers+ " Biller is displayed");
								}
							}
						}

						public void payBillsNavigation() throws Exception {
							verifyElementPresent(MLWalletPayBillsPage.objPayBills,getTextVal(MLWalletPayBillsPage.objPayBills,"Icon"));
							click(MLWalletPayBillsPage.objPayBills,getTextVal(MLWalletPayBillsPage.objPayBills,"Icon"));
						}


						public void searchBiller() throws Exception {
							type(MLWalletPayBillsPage.objSearchBiller,prop.getproperty("Biller_Name"),"Search Biller");
							verifyElementPresent(MLWalletPayBillsPage.objMisBillsPayBiller,getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller,"Biller"));
							click(MLWalletPayBillsPage.objMisBillsPayBiller,getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller,"Biller"));
							click(MLWalletPayBillsPage.objMisBillsPayBiller,getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller,"Biller"));
						}

						public void billerDetails(String sFirstName,String sLastName,String sMiddleName, String sAmount) throws Exception {
							explicitWaitVisibility(MLWalletPayBillsPage.objBillsPayInformation, 10);
							if(verifyElementPresent(MLWalletPayBillsPage.objBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillsPayInformation,"Page"))){
								type(MLWalletPayBillsPage.objAccountNumberField, prop.getproperty("AccountNumber"),"Account Number Text Field");
								type(MLWalletPayBillsPage.objFirstNameField,sFirstName ,"First Name Text Field");
								type(MLWalletPayBillsPage.objMiddleNameField,sMiddleName ,"Middle Name Text Field");
								type(MLWalletPayBillsPage.objLastnameField,sLastName ,"Last Name Text Field");
								Swipe("UP",1);
								type(MLWalletPayBillsPage.objAmountField, sAmount,"Amount to Send Text Field");
								click(MLWalletPayBillsPage.objConfirmBtn,getTextVal(MLWalletPayBillsPage.objConfirmBtn,"Button"));
							}
						}

						public void addSelectedBiller() throws Exception {
							if (verifyElementPresent(MLWalletPayBillsPage.objAddSeectedBiller, "Edit Biller")) {
								click(MLWalletPayBillsPage.objAddSeectedBiller, "Edit Biller");
								click(MLWalletPayBillsPage.objBillerListSearchBiller,"Biller List Search Biller");
								type(MLWalletPayBillsPage.objBillerListSearchBiller, prop.getproperty("Biller_Name"), "Biller List Search Biller");
								explicitWaitVisible(MLWalletPayBillsPage.objMisBillsPayBiller,10);
								click(MLWalletPayBillsPage.objMisBillsPayBiller, getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller, "Biller"));
								click(MLWalletPayBillsPage.objMisBillsPayBiller, getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller, "Biller"));
							}
						}

						public void addBiller() throws Exception {
							waitTime(2000);
							if(verifyElementPresent(MLWalletPayBillsPage.objSavedBiller, getTextVal(MLWalletPayBillsPage.objSavedBiller, "Button"))) {
								explicitWaitVisibility(MLWalletPayBillsPage.objSavedBiller, 10);
								click(MLWalletPayBillsPage.objSavedBiller, getTextVal(MLWalletPayBillsPage.objSavedBiller, "Button"));
								explicitWaitVisible(MLWalletPayBillsPage.objAddBiller,10);
								click(MLWalletPayBillsPage.objAddBiller, getTextVal(MLWalletPayBillsPage.objAddBiller, "Button"));
								addSelectedBiller();
								waitTime(2000);
								if (verifyElementPresent(MLWalletPayBillsPage.objAddBillers, getTextVal(MLWalletPayBillsPage.objAddBillers, "Page"))) {
									type(MLWalletPayBillsPage.objAddAccountNumber, prop.getproperty("AccountNumber"), "Account Number in Add Biller");
									type(MLWalletPayBillsPage.objAddFirstName, prop.getproperty("First_Name"), "First Name in Add Biller");
									type(MLWalletPayBillsPage.objAddMiddleName, prop.getproperty("Middle_Name"), "Middle Name in Add Biller");
									type(MLWalletPayBillsPage.objAddLastName, prop.getproperty("Last_Name"), "Last Name in Add Biller");
									type(MLWalletPayBillsPage.objAddNickName, prop.getproperty("Nick_Name"), "Nick Name in Add Biller");
									click(MLWalletPayBillsPage.objProceedBtn, getTextVal(MLWalletPayBillsPage.objProceedBtn, "button"));
									click(MLWalletPayBillsPage.objProceedBtn, getTextVal(MLWalletPayBillsPage.objProceedBtn, "button"));
								}
							}
						}

						public void selectAddedBiler() throws Exception {
							waitTime(2000);
							verifyElementPresentAndClick(MLWalletPayBillsPage.objSavedBiller,getTextVal(MLWalletPayBillsPage.objSavedBiller,"Button"));
							waitTime(2000);
							if(verifyElementPresent(MLWalletPayBillsPage.objSavedBillers,getTextVal(MLWalletPayBillsPage.objSavedBillers,"Page"))) {
								type(MLWalletPayBillsPage.objSearchSavedBiller, prop.getproperty("Last_Name"), "Search Recipient Text Field");
								verifyElementPresent(MLWalletPayBillsPage.objSelectBillerInnSavedBiller, getTextVal(MLWalletPayBillsPage.objSelectBillerInnSavedBiller, "Recipient"));
								click(MLWalletPayBillsPage.objSelectBillerInnSavedBiller, getTextVal(MLWalletPayBillsPage.objSelectBillerInnSavedBiller, "Recipient"));
								click(MLWalletPayBillsPage.objSelectBillerInnSavedBiller, getTextVal(MLWalletPayBillsPage.objSelectBillerInnSavedBiller, "Recipient"));
								waitTime(2000);
							}
						}
						

					//================================================================================================================//


						public void payBillsPageValidation_PB_TC_01() throws Exception {
							HeaderChildNode("Pay Bills Page Validation");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Branch_Verified"));
							payBillsNavigation();
							waitTime(3000);
							if(verifyElementPresent(MLWalletPayBillsPage.objSelectBiller,getTextVal(MLWalletPayBillsPage.objSelectBiller,"Page"))){
								verifyElementPresent(MLWalletPayBillsPage.objRecentTransactions,getTextVal(MLWalletPayBillsPage.objRecentTransactions,"Button"));
								verifyElementExist(MLWalletPayBillsPage.objBillers,getTextVal(MLWalletPayBillsPage.objBillers,"Text"));
								logger.info("PB_TC_01, Pay Bills Page validated");
								extentLoggerPass("PB_TC_01", "PB_TC_01, Pay Bills Page validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(1);
							mlWalletLogout();
						}

						public void billerCategories_PB_TC_02() throws Exception {
							HeaderChildNode("Biller Categories");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Branch_Verified"));
							payBillsNavigation();
							waitTime(2000);
							click(MLWalletPayBillsPage.objCategories,getTextVal(MLWalletPayBillsPage.objCategories,"Button"));
							if(verifyElementPresent(MLWalletPayBillsPage.objBankingAndFinance,getTextVal(MLWalletPayBillsPage.objBankingAndFinance,"Button"))){
								click(MLWalletPayBillsPage.objBankingAndFinance,getTextVal(MLWalletPayBillsPage.objBankingAndFinance,"Biller Category"));
							}
							
							waitTime(2000);
							if(verifyElementPresent(MLWalletPayBillsPage.objCharityAndReligious,getTextVal(MLWalletPayBillsPage.objCharityAndReligious,"Biller Category"))){
								click(MLWalletPayBillsPage.objCharityAndReligious,getTextVal(MLWalletPayBillsPage.objCharityAndReligious,"Biller Category"));
								getBillers(MLWalletPayBillsPage.objCharityAndReligiousBillers);
								waitTime(2000);
								//click(MLWalletPayBillsPage.objSearchBiller,getTextVal(MLWalletPayBillsPage.objSearchBiller,"Search Biller Field"));
								Swipe("Down",1);
								click(MLWalletPayBillsPage.objCharityAndReligious1,getTextVal(MLWalletPayBillsPage.objCharityAndReligious1,"Biller Category"));
							}
							waitTime(2000);
							Swipe("UP",1);
							if(verifyElementPresent(MLWalletPayBillsPage.objUtilities,getTextVal(MLWalletPayBillsPage.objUtilities,"Biller Category"))) {
								click(MLWalletPayBillsPage.objUtilities, getTextVal(MLWalletPayBillsPage.objUtilities, "Biller Category"));
								Swipe("UP", 1);
								getBillers(MLWalletPayBillsPage.objUtilitiesBillers);
								waitTime(2000);
								click(MLWalletPayBillsPage.objUtilities1, getTextVal(MLWalletPayBillsPage.objUtilities1, "Biller Category"));
							}
							if(verifyElementPresent(MLWalletPayBillsPage.objOthers,getTextVal(MLWalletPayBillsPage.objOthers,"Biller Category"))){
								click(MLWalletPayBillsPage.objOthers,getTextVal(MLWalletPayBillsPage.objOthers,"Biller Category"));
								Swipe("UP",1);
								getBillers(MLWalletPayBillsPage.objOthersBillers);
								click(MLWalletPayBillsPage.objOthers1,getTextVal(MLWalletPayBillsPage.objOthers1,"Biller Category"));
							}
							logger.info("PB_TC_02, Biller Categories validated");
							extentLoggerPass("PB_TC_02", "PB_TC_02, Biller Categories validated");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(1);
							mlWalletLogout();
						}

						public void billersInAlphabeticalOrder_PB_TC_03() throws Exception {
							HeaderChildNode("Billers In Alphabetical Order");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							explicitWaitVisibility(MLWalletPayBillsPage.objAlphabetical, 10);
							click(MLWalletPayBillsPage.objAlphabetical,getTextVal(MLWalletPayBillsPage.objAlphabetical,"Button"));
//							swipeDownUntilElementVisible("SAGIP KAPAMILYA");
//							swipeDownUntilElementVisible("ZYBITECH");
							
							
//							 List<WebElement>lists=getDriver().findElements(MLWalletPayBillsPage.objZelement);
//							 for(WebElement e : lists)
//							 {
//								 System.out.println(e.getAttribute("name"));
//								 if(e.getAttribute("name").equalsIgnoreCase("ZOE BROADCASTING"))
//								 {									 
//									 System.out.println("Found the value == : ");
//								 }
//								 
//							 }
							scroll_To_Text(MLWalletPayBillsPage.objZelement, "name", "FDSAFAWSEF");
							scroll_To_Text(MLWalletPayBillsPage.objZelement, "name", "ZOE BROADCASTING");
							logger.info("PB_TC_03, Billers swiped until Z Alphabet, and all the Billers are displayed in alphabetical order");
							extentLoggerPass("PB_TC_03", "PB_TC_03, Billers swiped until Z Alphabet, and all the Billers are displayed in alphabetical order");
							System.out.println("-----------------------------------------------------------");
						}

						public void selectBiller_PB_TC_04() throws Exception {
							HeaderChildNode("Select Biller");
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							click(MLWalletPayBillsPage.objAlphabetical,getTextVal(MLWalletPayBillsPage.objAlphabetical,"Button"));
//							swipeDownUntilElementVisible("AIR ASIA BILLSPAYMENT");
							if(verifyElementPresent(MLWalletPayBillsPage.objAirAsia,getTextVal(MLWalletPayBillsPage.objAirAsia,"Biller"))){
								String sAirAsiaBillsPayment = getText(MLWalletPayBillsPage.objAirAsia);
								click(MLWalletPayBillsPage.objAirAsia,getTextVal(MLWalletPayBillsPage.objAirAsia,"Biller"));
								verifyElementPresent(MLWalletPayBillsPage.objBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillsPayInformation,"Page"));
								if(verifyElementPresent(MLWalletPayBillsPage.objBillerNameInBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillerNameInBillsPayInformation,"Biller Name"))){
									String sBillerInBillsPayInformation = getText(MLWalletPayBillsPage.objBillerNameInBillsPayInformation);
									assertionValidation(sAirAsiaBillsPayment,sBillerInBillsPayInformation);
									logger.info("PB_TC_04, Bills Pay Information page is displayed and Biller name is matched");
									extentLoggerPass("PB_TC_04", "PB_TC_04, Bills Pay Information page is displayed and Biller name is matched");
									System.out.println("-----------------------------------------------------------");
								}
							}
						}

						public void searchBiller_PB_TC_05() throws Exception {
							HeaderChildNode("Search Biller");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							waitTime(5000);
							type(MLWalletPayBillsPage.objSearchBiller,prop.getproperty("Biller_Name"),"Search Biller");
							if(verifyElementPresent(MLWalletPayBillsPage.objMisBillsPayBiller,getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller,"Biller"))){
								String sMisBillsPayBiller = getText(MLWalletPayBillsPage.objMisBillsPayBiller);
								click(MLWalletPayBillsPage.objMisBillsPayBiller,getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller,"Biller"));
								click(MLWalletPayBillsPage.objMisBillsPayBiller,getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller,"Biller"));
								explicitWaitVisibility(MLWalletPayBillsPage.objBillsPayInformation, 10);
								verifyElementPresent(MLWalletPayBillsPage.objBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillsPayInformation,"Page"));
								if(verifyElementPresent(MLWalletPayBillsPage.objBillerNameInBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillerNameInBillsPayInformation,"Biller Name"))){
									String sBillerInBillsPayInformation = getText(MLWalletPayBillsPage.objBillerNameInBillsPayInformation);
									assertionValidation(sMisBillsPayBiller,sBillerInBillsPayInformation);
									logger.info("PB_TC_05, Bills Pay Information page is displayed and Biller name is MIS BILLSPAY123456");
									extentLoggerPass("PB_TC_05", "PB_TC_05, Bills Pay Information page is displayed and Biller name is MIS BILLSPAY123456");
									System.out.println("-----------------------------------------------------------");
								}
							}
							backArrowBtn(2);
							mlWalletLogout1();
						}


						public void billingInformationInput_PB_TC_06() throws Exception {
							HeaderChildNode("Biller Information Input");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"),prop.getproperty("Middle_Name"),prop.getproperty("Last_Name"),"10");
							waitTime(5000);
							if(verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails,getTextVal(MLWalletPayBillsPage.objConfirmDetails,"Page"))){
								verifyElementPresent(MLWalletPayBillsPage.objBillerName,getTextVal(MLWalletPayBillsPage.objBillerName,"Biller Name"));
								verifyElementPresent(MLWalletPayBillsPage.objAccountName,getTextVal(MLWalletPayBillsPage.objAccountName,"Account holder Name"));
								verifyElementPresent(MLWalletPayBillsPage.objAccountNumber,getTextVal(MLWalletPayBillsPage.objAccountNumber,"Account Number"));
								verifyElementPresent(MLWalletPayBillsPage.objPaymentMethod,getTextVal(MLWalletPayBillsPage.objPaymentMethod,"Payment Method"));
								logger.info("PB_TC_06, Confirm Details page displayed with transaction details is validated");
								extentLoggerPass("PB_TC_06", "PB_TC_06, Confirm Details page displayed with transaction details is validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(3);
							mlWalletLogout1();
						}

						public void payBillsWithValidInputs_PB_TC_07() throws Exception {
							HeaderChildNode("Pay Bills With Valid Inputs");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
							waitTime(7000);
							if(verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"))) {
								Swipe("UP",1);
								click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
							}
							enterOTP(prop.getproperty("Valid_OTP"));
							waitTime(20000);
							if(verifyElementPresent(MLWalletPayBillsPage.objSuccessPillPaymentMsg,getTextVal(MLWalletPayBillsPage.objSuccessPillPaymentMsg,"Message"))) {
								verifyElementPresent(MLWalletPayBillsPage.objAmountPaid, getTextVal(MLWalletPayBillsPage.objAmountPaid, "Amount"));
								verifyElementPresent(MLWalletPayBillsPage.objPaidDate, getTextVal(MLWalletPayBillsPage.objPaidDate, "Date"));
								verifyElementPresent(MLWalletPayBillsPage.objTransactionDetails, getTextVal(MLWalletPayBillsPage.objTransactionDetails, "Header"));
								verifyElementPresent(MLWalletPayBillsPage.objTransactionNumber, getTextVal(MLWalletPayBillsPage.objTransactionNumber, "Transaction Number"));
								String sTransactionNumber = getText(MLWalletPayBillsPage.objTransactionNumber);
								verifyElementPresent(MLWalletPayBillsPage.objBillerName, getTextVal(MLWalletPayBillsPage.objBillerName, "Biller Name"));
								verifyElementPresent(MLWalletPayBillsPage.objAccountName, getTextVal(MLWalletPayBillsPage.objAccountName, "Account Name"));
								verifyElementPresent(MLWalletPayBillsPage.objPaymentMethod, getTextVal(MLWalletPayBillsPage.objPaymentMethod, "Payment Method"));
								verifyElementPresent(MLWalletPayBillsPage.objAmountToPay, getTextVal(MLWalletPayBillsPage.objAmountToPay, "Amount"));
								verifyElementPresent(MLWalletPayBillsPage.objServiceFee, getTextVal(MLWalletPayBillsPage.objServiceFee, "Service Fee"));
								verifyElementPresent(MLWalletPayBillsPage.objTotalAmount, getTextVal(MLWalletPayBillsPage.objTotalAmount, "Total Amount"));
								verifyElementPresentAndClick(MLWalletPayBillsPage.objBackToHomeBtn, getTextVal(MLWalletPayBillsPage.objBackToHomeBtn, "Button"));
								verifyRecentTransaction1();
								verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
								click(MLWalletHomePage.objPayBills, getTextVal(MLWalletHomePage.objPayBills, "Text"));
								explicitWaitVisibility(MLWalletCashOutPage.objTransactionDetails, 20);
								if (verifyElementPresent(MLWalletCashOutPage.objTransactionDetails, getTextVal(MLWalletCashOutPage.objTransactionDetails, "Page"))) {
									String sReferenceNumberInCashOut = getText(MLWalletCashOutPage.objReferenceNumberInCashOut);
									System.out.println(sReferenceNumberInCashOut);
									assertionValidation(sReferenceNumberInCashOut, sTransactionNumber);
									logger.info("PB_TC_07, Bills Payment Successful and validated with Recent Transaction");
									extentLoggerPass("PB_TC_07", "PB_TC_07, Bills Payment Successful and validated with Recent Transaction");
									System.out.println("-----------------------------------------------------------");
								}
							}
							backArrowBtn(1);
							mlWalletLogout1();
						}

						public void payBillsInRecentTransactions_PB_TC_08() throws Exception {
							HeaderChildNode("Pay Bills In Recent Transactions");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							verifyElementPresent(MLWalletHomePage.objRecentTransactions, getTextVal(MLWalletHomePage.objRecentTransactions, "Text"));
							click(MLWalletHomePage.objPayBills, getTextVal(MLWalletHomePage.objPayBills, "Text"));
							waitTime(5000);
							if(verifyElementPresent(MLWalletPayBillsPage.objTransactionDetails,getTextVal(MLWalletPayBillsPage.objTransactionDetails,"Page"))){
								verifyElementPresent(MLWalletPayBillsPage.objReferenceNumber,getTextVal(MLWalletPayBillsPage.objReferenceNumber,"Reference Number"));
								verifyElementPresent(MLWalletPayBillsPage.objReceiverName,getTextVal(MLWalletPayBillsPage.objReceiverName,"Receiver Name"));
								verifyElementPresent(MLWalletPayBillsPage.objBillerName, getTextVal(MLWalletPayBillsPage.objBillerName, "Biller Name"));
								verifyElementPresent(MLWalletPayBillsPage.objReceiverMobNo, getTextVal(MLWalletPayBillsPage.objReceiverMobNo, "Receiver Mobile Number"));
								verifyElementPresent(MLWalletPayBillsPage.objPaymentMethod, getTextVal(MLWalletPayBillsPage.objPaymentMethod, "Payment Method"));
								verifyElementPresent(MLWalletPayBillsPage.objAmountToSend, getTextVal(MLWalletPayBillsPage.objAmountToSend, "Amount"));
								verifyElementPresent(MLWalletPayBillsPage.objServiceFee, getTextVal(MLWalletPayBillsPage.objServiceFee, "Service Fee"));
								verifyElementPresent(MLWalletPayBillsPage.objTotalAmount, getTextVal(MLWalletPayBillsPage.objTotalAmount, "Total Amount"));
								logger.info("PB_TC_08, Pay Bills In Recent Transactions Validated");
								extentLoggerPass("PB_TC_08", "PB_TC_08, Pay Bills In Recent Transactions Validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(1);
							mlWalletLogout1();
						}

						public void payBillsInsufficientBalance_PB_TC_09() throws Exception {
							HeaderChildNode("Pay Bills Insufficient Balance");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "39000");
							waitTime(5000);
							if(verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"))) {
								Swipe("UP",1);
								click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
								explicitWaitVisibility(SendTransferPage.objInsufficientAmountMsg,20);
								if (verifyElementPresent(SendTransferPage.objInsufficientAmountMsg, getTextVal(SendTransferPage.objInsufficientAmountMsg, "Error Message"))) {
									String sInsufficientBalanceErrorMsg = getText(SendTransferPage.objInsufficientAmountMsg);
									String sExpectedErrorMsg = "There is insufficient balance to proceed with this transaction. Please try again.";
									assertionValidation(sInsufficientBalanceErrorMsg, sExpectedErrorMsg);
									click(MLWalletCashOutPage.objOKBtnOne,"OK Button");
									logger.info("PB_TC_09, Insufficient Balance - Error Message is validated");
									extentLoggerPass("PB_TC_09", "PB_TC_09, Insufficient Balance - Error Message is validated");
									System.out.println("-----------------------------------------------------------");
								}
							}
							backArrowBtn(2);
							mlWalletLogout1();
						}


						public void billingInformationInvalidInput_PB_TC_10() throws Exception {
							HeaderChildNode("Biller Information Invalid Input");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Branch_Verified"));
							payBillsNavigation();
							searchBiller();
							explicitWaitVisibility(MLWalletPayBillsPage.objConfirmBtn, 10);
							click(MLWalletPayBillsPage.objConfirmBtn,getTextVal(MLWalletPayBillsPage.objConfirmBtn,"Button"));
							explicitWaitVisibility(MLWalletPayBillsPage.objAccountNumberRequiredMsg, 10);
							if(verifyElementPresent(MLWalletPayBillsPage.objAccountNumberRequiredMsg,getTextVal(MLWalletPayBillsPage.objAccountNumberRequiredMsg,"Error Message"))){
								String sAccountNumberRequiredErrorMsg = getText(MLWalletPayBillsPage.objAccountNumberRequiredMsg);
								String sExceptedAccountNumberRequiredErrorMsg = "Account Number is required";
								assertionValidation(sAccountNumberRequiredErrorMsg,sExceptedAccountNumberRequiredErrorMsg);
							}
							if(verifyElementPresent(MLWalletPayBillsPage.objFirstNameRequiredMsg,getTextVal(MLWalletPayBillsPage.objFirstNameRequiredMsg,"Error Message"))){
								String sFirstNameRequiredErrorMsg = getText(MLWalletPayBillsPage.objFirstNameRequiredMsg);
								String sExceptedFirstNameRequiredErrorMsg = "First name is required";
								assertionValidation(sFirstNameRequiredErrorMsg,sExceptedFirstNameRequiredErrorMsg);
							}

							if(verifyElementPresent(MLWalletPayBillsPage.objLastNameRequiredMsg,getTextVal(MLWalletPayBillsPage.objLastNameRequiredMsg,"Error Message"))){
								String sLastNameRequiredErrorMsg = getText(MLWalletPayBillsPage.objLastNameRequiredMsg);
								String sExceptedLastNameRequiredErrorMsg = "Last name is required";
								assertionValidation(sLastNameRequiredErrorMsg,sExceptedLastNameRequiredErrorMsg);
							}

							billerDetails(prop.getproperty("Invalid_First_Name"),prop.getproperty("Invalid_Middle_Name"),prop.getproperty("Invalid_Last_Name"),"0.99");
							if(verifyElementPresent(MLWalletPayBillsPage.objInvalidFirstNameMsg,getTextVal(MLWalletPayBillsPage.objInvalidFirstNameMsg,"Error Message"))){
								String sInvalidFirstNameErrorMsg = getText(MLWalletPayBillsPage.objInvalidFirstNameMsg);
								String sExceptedFirstNameErrorMsg = "First name must only contain letters and spaces";
								assertionValidation(sInvalidFirstNameErrorMsg,sExceptedFirstNameErrorMsg);
							}
							if(verifyElementPresent(MLWalletPayBillsPage.objInvalidSecondNameMsg,getTextVal(MLWalletPayBillsPage.objInvalidSecondNameMsg,"Error Message"))){
								String sInvalidSecondNameErrorMsg = getText(MLWalletPayBillsPage.objInvalidSecondNameMsg);
								String sExceptedSecondNameErrorMsg = "Middle name must only contain letters and spaces";
								assertionValidation(sInvalidSecondNameErrorMsg,sExceptedSecondNameErrorMsg);
							}
							if(verifyElementPresent(MLWalletPayBillsPage.objInvalidLastName,getTextVal(MLWalletPayBillsPage.objInvalidLastName,"Error Message"))){
								String sInvalidThirdNameErrorMsg = getText(MLWalletPayBillsPage.objInvalidLastName);
								String sExceptedThirdNameErrorMsg = "Last name must only contain letters and spaces";
								assertionValidation(sInvalidThirdNameErrorMsg,sExceptedThirdNameErrorMsg);
							}
							Swipe("Down",1);
							
							click(MLWalletPayBillsPage.objFirstNameField1, "First Name Text Field");
							clearField(MLWalletPayBillsPage.objFirstNameField1, "First Name Text Field");
							type(MLWalletPayBillsPage.objFirstNameField,prop.getproperty("First_Name") ,"First Name Text Field");
							
							click(MLWalletPayBillsPage.objMiddleNameField1, "Middle Name Text Field");
							clearField(MLWalletPayBillsPage.objMiddleNameField1, "Middle Name Text Field");
							type(MLWalletPayBillsPage.objMiddleNameField,prop.getproperty("Middle_Name") ,"Middle Name Text Field");
							
							click(MLWalletPayBillsPage.objLastnameField1, "Last Name Text Field");
							clearField(MLWalletPayBillsPage.objLastnameField1, "Last Name Text Field");
							type(MLWalletPayBillsPage.objLastnameField,prop.getproperty("Last_Name") ,"Last Name Text Field");
							Swipe("UP",1);
//							type(MLWalletPayBillsPage.objAmountField, "0.99","Amount to Send Text Field");
							click(MLWalletPayBillsPage.objConfirmBtn,getTextVal(MLWalletPayBillsPage.objConfirmBtn,"Button"));
							click(MLWalletPayBillsPage.objConfirmBtn,getTextVal(MLWalletPayBillsPage.objConfirmBtn,"Button"));
							waitTime(2000);	
							if(verifyElementPresent(MLWalletPayBillsPage.objInvalidAmount,getTextVal(MLWalletPayBillsPage.objInvalidAmount,"Error Message"))){
								String sInvalidAmountErrorMsg = getText(MLWalletPayBillsPage.objInvalidAmount);
								String sExceptedAmountErrorMsg = "The amount should not be less than 1";
								assertionValidation(sInvalidAmountErrorMsg,sExceptedAmountErrorMsg);
							}

							logger.info("PB_TC_10, Invalid biller Information Error messages validated");
							extentLoggerPass("PB_TC_10", "PB_TC_10, Invalid biller Information Error messages validated");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(2);
							mlWalletLogout();
						}

						public void selectBiller()
						{
							
						}
						public void addBillerToPayBills_PB_TC_12() throws Exception {
							HeaderChildNode("Add Biller To Pay Bills");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Branch_Verified"));
							payBillsNavigation();
							addBiller();
							explicitWaitVisibility(MLWalletPayBillsPage.objOKBtn, 10);
							click(MLWalletPayBillsPage.objOKBtn,getTextVal(MLWalletPayBillsPage.objOKBtn,"Button"));
							waitTime(2000);
							type(MLWalletPayBillsPage.objSearchSavedBiller, prop.getproperty("Last_Name"), "Search Biller Text Field");
							if (verifyElementPresent(MLWalletPayBillsPage.objSelectBillerInnSavedBiller, getTextVal(MLWalletPayBillsPage.objSelectBillerInnSavedBiller, "Recipient"))) {
								logger.info("PB_TC_12, The Added Biller is displayed in Saved Biller Page");
								extentLoggerPass("PB_TC_12", "PB_TC_12, The Added Biller is displayed in Saved Biller Page");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(2);
							mlWalletLogout();
						}
					//
//						public void addBillerInvalidInputs_PB_TC_13() throws Exception {
//							HeaderChildNode("Add Biller Invalid Inputs");
//							mlWalletLogin(prop.getproperty("Branch_Verified"));
//							verifyElementPresent(MLWalletPayBillsPage.objPayBills,getTextVal(MLWalletPayBillsPage.objPayBills,"Icon"));
//							click(MLWalletPayBillsPage.objPayBills,getTextVal(MLWalletPayBillsPage.objPayBills,"Icon"));
//							click(MLWalletPayBillsPage.objSavedBiller, getTextVal(MLWalletPayBillsPage.objSavedBiller, "Button"));
//							explicitWaitVisible(MLWalletPayBillsPage.objAddBiller,5);
//							click(MLWalletPayBillsPage.objAddBiller, getTextVal(MLWalletPayBillsPage.objAddBiller, "Button"));
//							addSelectedBiller();
					//
//							type(MLWalletPayBillsPage.objAddAccountNumber,"ABC","Account Number Input Field");
//							click(MLWalletPayBillsPage.objProceedBtn,getTextVal(MLWalletPayBillsPage.objProceedBtn,"Button"));
//							if(verifyElementPresent(MLWalletPayBillsPage.objAddAccountNumber,getTextVal(MLWalletPayBillsPage.objAddAccountNumber,"Error Message"))){
//								String sAccountNumberRequiredErrorMsg = getText(MLWalletPayBillsPage.objAccountNumberRequiredMsg);
//								String sExceptedAccountNumberRequiredErrorMsg = "Account Number is required";
//								assertionValidation(sAccountNumberRequiredErrorMsg,sExceptedAccountNumberRequiredErrorMsg);
//							}
					//
//							click(MLWalletPayBillsPage.objProceedBtn,getTextVal(MLWalletPayBillsPage.objProceedBtn,"Button"));
//							type(MLWalletPayBillsPage.objAddAccountNumber,prop.getproperty("AccountNumber"),"Account Number Input Field");
//							if(verifyElementPresent(MLWalletPayBillsPage.objFirstNameRequiredMsg,getTextVal(MLWalletPayBillsPage.objFirstNameRequiredMsg,"Error Message"))){
//								String sFirstNameRequiredErrorMsg = getText(MLWalletPayBillsPage.objFirstNameRequiredMsg);
//								String sExceptedFirstNameRequiredErrorMsg = "First name is required";
//								assertionValidation(sFirstNameRequiredErrorMsg,sExceptedFirstNameRequiredErrorMsg);
//							}
					//
//							click(MLWalletPayBillsPage.objProceedBtn,getTextVal(MLWalletPayBillsPage.objProceedBtn,"Button"));
//							type(MLWalletPayBillsPage.objAddFirstName,prop.getproperty("Invalid_First_Name"),"First Name Input Field");
//							if(verifyElementPresent(MLWalletPayBillsPage.objLastNameRequiredMsg,getTextVal(MLWalletPayBillsPage.objLastNameRequiredMsg,"Error Message"))){
//								String sLastNameRequiredErrorMsg = getText(MLWalletPayBillsPage.objLastNameRequiredMsg);
//								String sExceptedLastNameRequiredErrorMsg = "Last name is required";
//								assertionValidation(sLastNameRequiredErrorMsg,sExceptedLastNameRequiredErrorMsg);
//							}
					//
//							click(MLWalletPayBillsPage.objProceedBtn,getTextVal(MLWalletPayBillsPage.objProceedBtn,"Button"));
//							type(MLWalletPayBillsPage.objAddLastName,prop.getproperty("Invalid_Last_Name"),"Last Name Input Field");
////							billerDetails(prop.getproperty("Invalid_First_Name"),prop.getproperty("Invalid_Middle_Name"),prop.getproperty("Invalid_Last_Name"),"0.99");
					//
					//
//							if(verifyElementPresent(MLWalletPayBillsPage.objInvalidFirstNameMsg,getTextVal(MLWalletPayBillsPage.objInvalidFirstNameMsg,"Error Message"))){
//								String sInvalidFirstNameErrorMsg = getText(MLWalletPayBillsPage.objInvalidFirstNameMsg);
//								String sExceptedFirstNameErrorMsg = "First name must only contain letters and spaces";
//								assertionValidation(sInvalidFirstNameErrorMsg,sExceptedFirstNameErrorMsg);
//							}
//							if(verifyElementPresent(MLWalletPayBillsPage.objInvalidSecondNameMsg,getTextVal(MLWalletPayBillsPage.objInvalidSecondNameMsg,"Error Message"))){
//								String sInvalidSecondNameErrorMsg = getText(MLWalletPayBillsPage.objInvalidSecondNameMsg);
//								String sExceptedSecondNameErrorMsg = "Middle name must only contain letters and spaces";
//								assertionValidation(sInvalidSecondNameErrorMsg,sExceptedSecondNameErrorMsg);
//							}
//							if(verifyElementPresent(MLWalletPayBillsPage.objInvalidLastName,getTextVal(MLWalletPayBillsPage.objInvalidLastName,"Error Message"))){
//								String sInvalidThirdNameErrorMsg = getText(MLWalletPayBillsPage.objInvalidLastName);
//								String sExceptedThirdNameErrorMsg = "Last name must only contain letters and spaces";
//								assertionValidation(sInvalidThirdNameErrorMsg,sExceptedThirdNameErrorMsg);
//							}

						public void editAddedBillerToPayBills_PB_TC_14() throws Exception {
							HeaderChildNode("Edit Added Biller to Pay bIlls");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Branch_Verified"));
							payBillsNavigation();
							selectAddedBiler();
							waitTime(2000);
							click(MLWalletPayBillsPage.objEditBtn,getTextVal(MLWalletPayBillsPage.objEditBtn,"Button"));
//objEditRecipientLastName
							clearField(MLWalletPayBillsPage.objEditRecipientLastName,"Last Name Input Field");
							click(MLWalletPayBillsPage.objLastNameClr,"Last Name Input Field");
							type(MLWalletPayBillsPage.objLastNameClr, prop.getproperty("Edited_Last_name"), "Last Name Text Field");
							click(MLWalletPayBillsPage.ObjSaveBtn, getTextVal(MLWalletPayBillsPage.ObjSaveBtn, "Button"));
							click(MLWalletPayBillsPage.ObjSaveBtn, getTextVal(MLWalletPayBillsPage.ObjSaveBtn, "Button"));
							waitTime(2000);
							click(MLWalletPayBillsPage.objOKBtn,getTextVal(MLWalletPayBillsPage.objOKBtn,"Button"));
							click(MLWalletPayBillsPage.objClearSearchBar, "Search Bar");
							clearField(MLWalletPayBillsPage.objClearSearchBar, "Search Bar");
							type(MLWalletPayBillsPage.objSearchSavedBiller, prop.getproperty("Edited_Last_name"), "Search Recipient Text Field");
							waitTime(2000);
							if (verifyElementPresent(MLWalletPayBillsPage.objSelectBillerInnSavedBiller, "Recipient")) {
								logger.info("PB_TC_14, Successfully edited the Added Biller");
								extentLoggerPass("PB_TC_14", "PB_TC_14, Successfully edited the Added Biller");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(2);
							mlWalletLogout();

						}

						public void deleteAddedBillerPayBills_PB_TC_15() throws Exception {
							HeaderChildNode("Delete Added Biller Pay Bills");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Branch_Verified"));
							payBillsNavigation();
							selectAddedBiler();
							click(MLWalletPayBillsPage.objRemoveBtn,getTextVal(MLWalletPayBillsPage.objRemoveBtn,"Button"));
							explicitWaitVisibility(MLWalletPayBillsPage.objConfirmPopup, 10);
							if(verifyElementPresent(MLWalletPayBillsPage.objConfirmPopup,getTextVal(MLWalletPayBillsPage.objConfirmPopup,"Pop up"))){
								verifyElementPresentAndClick(MLWalletPayBillsPage.objModalConfirmBtn,getTextVal(MLWalletPayBillsPage.objModalConfirmBtn,"Button"));
							}
							waitTime(2000);
							if(verifyElementPresent(MLWalletPayBillsPage.objSavedBillers,getTextVal(MLWalletPayBillsPage.objSavedBillers,"Page"))){
								click(MLWalletPayBillsPage.objClearSearchBar, "Search Bar");
								clearField(MLWalletPayBillsPage.objClearSearchBar, "Search Bar");
								type(MLWalletPayBillsPage.objSearchSavedBiller, prop.getproperty("Edited_Last_name"), "Search Recipient Text Field");
								waitTime(2000);;
								logger.info("PB_TC_15, Successfully deleted the Added Biller");
								extentLoggerPass("PB_TC_15", "PB_TC_15, Successfully deleted the Added Biller");
								System.out.println("-----------------------------------------------------------");
								
							}
							backArrowBtn(2);
							mlWalletLogout();
						}

					//================================ Phase 2=============================================================//

						public void payBillsUIValidation_PB_TC_16() throws Exception {
							HeaderChildNode("Pay Bills UI Validation");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Branch_Verified"));
							payBillsNavigation();
							waitTime(4000);
							if(verifyElementPresent(MLWalletPayBillsPage.objSelectBiller,getTextVal(MLWalletPayBillsPage.objSelectBiller,"Page"))){
								verifyElementPresent(MLWalletPayBillsPage.objRecentTransactions,getTextVal(MLWalletPayBillsPage.objRecentTransactions,"Header"));
								verifyElementPresent(MLWalletPayBillsPage.objSavedBiller,getTextVal(MLWalletPayBillsPage.objSavedBiller,"Button"));
								verifyElementPresent(MLWalletPayBillsPage.objBillers,getTextVal(MLWalletPayBillsPage.objBillers,"Header"));
								verifyElementPresent(MLWalletPayBillsPage.objCategories,getTextVal(MLWalletPayBillsPage.objCategories,"Button"));
								verifyElementPresent(MLWalletPayBillsPage.objAlphabetical,getTextVal(MLWalletPayBillsPage.objAlphabetical,"Button"));
								verifyElementPresent(MLWalletPayBillsPage.objSearchBiller,"Search Biller Input Field");
								logger.info("PB_TC_16, Pay Bills UI Page Validated");
								extentLoggerPass("PB_TC_16", "PB_TC_16, Pay Bills UI Page Validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(1);
							mlWalletLogout();
						}


						public void payBillsAddBillerPageUIValidation_PB_TC_18() throws Exception {
							HeaderChildNode("PayBills Add Biller Page UI Validation");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Branch_Verified"));
							payBillsNavigation();
							waitTime(2000);
							verifyElementPresentAndClick(MLWalletPayBillsPage.objSavedBiller,getTextVal(MLWalletPayBillsPage.objSavedBiller,"Button"));
							waitTime(2000);
							verifyElementPresentAndClick(MLWalletPayBillsPage.objAddBiller,getTextVal(MLWalletPayBillsPage.objAddBiller,"Button"));
							waitTime(2000);
							if(verifyElementPresent(MLWalletPayBillsPage.objAddBillers,getTextVal(MLWalletPayBillsPage.objAddBillers,"Page"))){
								waitTime(2000);
								verifyElementPresent(MLWalletPayBillsPage.objBillerInformation,getTextVal(MLWalletPayBillsPage.objBillerInformation,"Header"));
								verifyElementPresent(MLWalletPayBillsPage.objBiller,getTextVal(MLWalletPayBillsPage.objBiller,"Edit Field"));
								verifyElementPresent(MLWalletPayBillsPage.objAddAccountNumber,"Account Number Input Field");
								verifyElementPresent(MLWalletPayBillsPage.objAddFirstName,"Account Holder First Name Input Field");
								verifyElementPresent(MLWalletPayBillsPage.objAddMiddleName,"Account Holder Middle Name Input Field");
								verifyElementPresent(MLWalletPayBillsPage.objAddLastName,"Account Holder Last Name Input Field");
								verifyElementPresent(MLWalletPayBillsPage.objAddNickName,"Nick Name Input Field");
								verifyElementPresent(MLWalletPayBillsPage.objProceedBtn,getTextVal(MLWalletPayBillsPage.objProceedBtn,"Button"));
								logger.info("PB_TC_18, PayBills Add Biller Page UI Validated");
								extentLoggerPass("PB_TC_18", "PB_TC_16, PayBills Add Biller Page UI Validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(3);
							mlWalletLogout();
						}

						public void paybillsRecentTransaction_PB_TC_19() throws Exception {
							HeaderChildNode("Pay Bills Recent Transaction validation");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
							waitTime(10000);
							verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
							Swipe("UP",1);
							click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
							waitTime(2000);
							enterOTP(prop.getproperty("Valid_OTP"));
							waitTime(15000);
							verifyElementPresent(MLWalletPayBillsPage.objTransactionDetails,getTextVal(MLWalletPayBillsPage.objTransactionDetails,"Page"));
							Swipe("UP",1);
							verifyElementPresentAndClick(MLWalletPayBillsPage.objNewTransactionBtn,getTextVal(MLWalletPayBillsPage.objNewTransactionBtn,"Button"));
//							backArrowBtn(1);
//							verifyRecentTransaction1();
							waitTime(2000);
							verifyElementPresentAndClick(MLWalletPayBillsPage.objRecentTransactionOne,"Recent Transaction");
							waitTime(2000);
							if(verifyElementPresent(MLWalletPayBillsPage.objBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillsPayInformation,"Page"))){
								waitTime(2000);
								verifyElementPresent(MLWalletPayBillsPage.objMisBillsPayBiller1,getTextVal(MLWalletPayBillsPage.objMisBillsPayBiller1,"Biller"));
								logger.info("PB_TC_19, Pay Bills Recent Transaction Validated");
								extentLoggerPass("PB_TC_19", "PB_TC_19, Pay Bills Recent Transaction validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(2);
							mlWalletLogout1();
						}

						public void payBillsSavedBilerUIValidation_PB_TC_20() throws Exception {
							HeaderChildNode("Pay Bills Saved Biler UI Validation");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							selectAddedBiler();
							waitTime(2000);
							if(verifyElementPresent(MLWalletPayBillsPage.objAccountInfo,getTextVal(MLWalletPayBillsPage.objAccountInfo,"Page"))){
//								verifyElementPresent(MLWalletPayBillsPage.objAddAccountNumber,getTextVal(MLWalletPayBillsPage.objAddAccountNumber,"Account Number"));
//								verifyElementPresent(MLWalletPayBillsPage.objAddFirstName,getTextVal(MLWalletPayBillsPage.objAddFirstName,"First Name"));
//								verifyElementPresent(MLWalletPayBillsPage.objAddMiddleName,getTextVal(MLWalletPayBillsPage.objAddMiddleName,"Middle Name"));
//								verifyElementPresent(MLWalletPayBillsPage.objAddLastName,getTextVal(MLWalletPayBillsPage.objAddLastName,"Last Name"));
//								verifyElementPresent(MLWalletPayBillsPage.objAddNickName,getTextVal(MLWalletPayBillsPage.objAddNickName,"Nick Name"));
								verifyElementPresent(MLWalletPayBillsPage.objEditBtn,getTextVal(MLWalletPayBillsPage.objEditBtn,"Button"));
								verifyElementPresent(MLWalletPayBillsPage.objRemoveBtn,getTextVal(MLWalletPayBillsPage.objRemoveBtn,"Button"));
								verifyElementPresent(MLWalletPayBillsPage.objSelectBiller,getTextVal(MLWalletPayBillsPage.objSelectBiller,"Button"));
								logger.info("PB_TC_20, Pay Bills Saved Biler UI Validated");
								extentLoggerPass("PB_TC_20", "PB_TC_20, Pay Bills Saved Biler UI validated");
								System.out.println("-----------------------------------------------------------");
							}
						}
						
						
						public void payBillsMaxBillsPaymentPerTransactionBuyTierUser_PB_TC_22() throws Exception {
							HeaderChildNode("Pay Bills Maximum Bills Payment Per Transaction for Buyer Tier Account");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Buyer_Tier"));
							payBillsNavigation();
							click(MLWalletPayBillsPage.objSearchBiller,"Search Biller");
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "20000");
							waitTime(5000);
							verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
							Swipe("UP",1);
							click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
							waitTime(2000);
							if(verifyElementPresent(MLWalletPayBillsPage.objMaxLimitErrorMessage,getTextVal(MLWalletPayBillsPage.objMaxLimitErrorMessage,"Error Message"))){
								String sMaxLimitErrorMessage = getText(MLWalletPayBillsPage.objMaxLimitErrorMessage);
								String sExpectedErrorMessage = "The maximum Bills Pay per transaction set for your verification level is P10,000.00. Please try again.";
								assertionValidation(sMaxLimitErrorMessage,sExpectedErrorMessage);
					            verifyElementPresentAndClick(MLWalletPayBillsPage.objUpgradeNowBtn,getTextVal(MLWalletPayBillsPage.objUpgradeNowBtn,"Button"));
								logger.info("PB_TC_22, Pay Bills Maximum Bills Payment Per Transaction for Buyer Tier Account Validated");
								extentLoggerPass("PB_TC_22", "PB_TC_22, Pay Bills Maximum Bills Payment Per Transaction for Buyer Tier Account validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(4);
							mlWalletLogout();
						}


						public void payBillsMaxBillsPaymentPerTransactionSemiVerifiedTier_PB_TC_25() throws Exception {
							HeaderChildNode("Pay Bills Maximum Bills Payment Per Transaction for Semi Verified Tier Account");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Semi_Verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "60000");
							waitTime(7000);
							verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
							Swipe("UP",1);
							click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
							waitTime(3000);
							if(verifyElementPresent(MLWalletPayBillsPage.objMaxLimitErrorMessage1,getTextVal(MLWalletPayBillsPage.objMaxLimitErrorMessage1,"Error Message"))){
								String sMaxLimitErrorMessage = getText(MLWalletPayBillsPage.objMaxLimitErrorMessage1);
								String sExpectedErrorMessage = "The maximum Bills Pay per transaction set for your verification level is P50,000.00. Please try again.";
								assertionValidation(sMaxLimitErrorMessage,sExpectedErrorMessage);
								verifyElementPresentAndClick(MLWalletPayBillsPage.objUpgradeNowBtn,getTextVal(MLWalletPayBillsPage.objUpgradeNowBtn,"Button"));
								logger.info("PB_TC_25, Pay Bills Maximum Bills Payment Per Transaction for Semi Verified Tier Account Validated");
								extentLoggerPass("PB_TC_25", "PB_TC_25, Pay Bills Maximum Bills Payment Per Transaction for Semi Verified Tier Account validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(4);
							mlWalletLogout();
						}


						public void payBillsMaxBillsPaymentPerTransactionBranchVerifiedTier_PB_TC_28() throws Exception {
							HeaderChildNode("Pay Bills Maximum Bills Payment Per Transaction for Branch Verified Tier Account");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Branch_Verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "60000");
							waitTime(7000);
							verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
							Swipe("UP",1);
							click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
							waitTime(3000);
							if(verifyElementPresent(MLWalletPayBillsPage.objMaxLimitErrorMessage2,getTextVal(MLWalletPayBillsPage.objMaxLimitErrorMessage2,"Error Message"))){
								String sMaxLimitErrorMessage = getText(MLWalletPayBillsPage.objMaxLimitErrorMessage2);
								String sExpectedErrorMessage = "The maximum Bills Pay per transaction set for your verification level is P25,000.00. Please try again.";
								assertionValidation(sMaxLimitErrorMessage,sExpectedErrorMessage);
								verifyElementPresentAndClick(MLWalletPayBillsPage.objOKBtn,getTextVal(MLWalletPayBillsPage.objOKBtn,"Button"));
								logger.info("PB_TC_26, Pay Bills Maximum Bills Payment Per Transaction for Branch Verified Tier Account Validated");
								extentLoggerPass("PB_TC_26", "PB_TC_26, Pay Bills Maximum Bills Payment Per Transaction for Branch Verified Tier Account validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(4);
							mlWalletLogout();
						}

						public void payBillsMaxBillsPaymentPerTransactionFullyVerifiedTier_PB_TC_31() throws Exception {
							HeaderChildNode("Pay Bills Maximum Bills Payment Per Transaction for Fully Verified Tier Account");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "60000");
							waitTime(7000);
							verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
							Swipe("UP",1);
							click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
							waitTime(3000);
							if(verifyElementPresent(MLWalletPayBillsPage.objMaxLimitErrorMessage1,getTextVal(MLWalletPayBillsPage.objMaxLimitErrorMessage1,"Error Message"))){
								String sMaxLimitErrorMessage = getText(MLWalletPayBillsPage.objMaxLimitErrorMessage1);
								String sExpectedErrorMessage = "The maximum Bills Pay per transaction set for your verification level is P50,000.00. Please try again.";
								assertionValidation(sMaxLimitErrorMessage,sExpectedErrorMessage);
								verifyElementPresentAndClick(MLWalletPayBillsPage.objOKBtn,getTextVal(MLWalletPayBillsPage.objOKBtn,"Button"));
								logger.info("PB_TC_31, Pay Bills Maximum Bills Payment Per Transaction for Fully Verified Tier Account Validated");
								extentLoggerPass("PB_TC_31", "PB_TC_31, Pay Bills Maximum Bills Payment Per Transaction for Fully Verified Tier Account validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(2);
							mlWalletLogout1();
						}

						public void payBillsRecentTransaction_PB_TC_21() throws Exception {
							HeaderChildNode("PayBills Recent Transactions");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Branch_Verified"));
							payBillsNavigation();
							waitTime(5000);
							verifyElementPresent(MLWalletPayBillsPage.objRecentTransactions,getTextVal(MLWalletPayBillsPage.objRecentTransactions,"Header"));
							horizontalSwipeByPercentages(0.8,0.2,0.25);
							horizontalSwipeByPercentages(0.8,0.2,0.25);
							logger.info("PB_TC_21, PayBills Recent Transactions Swiped until Last Transaction");
							extentLoggerPass("PB_TC_21", "PB_TC_21, PayBills Recent Transactions Swiped until Last Transaction");
							System.out.println("-----------------------------------------------------------");
						}

						public void locationPopUpValidation() throws Exception {
							try {
							if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "PopUp Msg"))) {
								verifyElementPresent(MLWalletHomePage.obPopupAllowBtn, getTextVal(MLWalletHomePage.obPopupAllowBtn, "Button"));
								verifyElementPresent(MLWalletHomePage.objLocationPopUpAllowOnceBtn, getTextVal(MLWalletHomePage.objLocationPopUpAllowOnceBtn, "Button"));
								verifyElementPresent(MLWalletHomePage.objPopUpDenyBtn, getTextVal(MLWalletHomePage.objPopUpDenyBtn, "Button"));
							}
							}catch(Exception e)
							{
								logger.info("Location Pop Up not Displayed");
								extent.extentLogger("loc", "Location Pop Up not Displayed");
							}
						}

						public void payBillsLocationPopupValidation_PB_TC_34() throws Exception {
							HeaderChildNode("Pay Bills Location popup Validation");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
							waitTime(10000);
							verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
							Swipe("UP",1);
							click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
							waitTime(2000);
					        locationPopUpValidation();
					        waitTime(10000);
					        click(MLWalletLoginPage.objOtpContineBtn, "Clicked On OTP Continue Button");
							logger.info("PB_TC_34, Pay Bills Location popup Validated");
						    extentLoggerPass("PB_TC_34", "PB_TC_34, Pay Bills Location popup Validated");
						    System.out.println("------------------------------------------------");
						    waitTime(15000);
							click(MLWalletPayBillsPage.objBackToHomeBtn, "Back To Home Button");
							mlWalletLogout1();
						}

						
						public void permissionDenyPopUp() throws Exception {
							locationPopUpValidation();
							click(MLWalletHomePage.objPopUpDenyBtn, getTextVal(MLWalletHomePage.objPopUpDenyBtn, "Button"));
							if (verifyElementPresent(MLWalletHomePage.objPermissionDeniedPopUp, getTextVal(MLWalletHomePage.objPermissionDeniedPopUp, "PopUp"))) {
								verifyElementPresent(MLWalletHomePage.objOpenSettingBtn, getTextVal(MLWalletHomePage.objOpenSettingBtn, "Button"));
								verifyElementPresent(MLWalletHomePage.objCloseBtn, getTextVal(MLWalletHomePage.objCloseBtn, "Button"));
							}
						}

						public void payBillsLocationDenyFunctionality_PB_TC_35() throws Exception {
							HeaderChildNode("Pay Bills Location Deny Functionality Validation");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
							waitTime(10000);
							verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
							Swipe("UP",1);
							click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
							waitTime(2000);
//							if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
//								permissionDenyPopUp();
//								logger.info("PB_TC_35, Pay Bills Location Deny Functionality Validated");
//								extentLoggerPass("PB_TC_35", "PB_TC_35, Pay Bills Location Deny Functionality Validated");
//								System.out.println("-----------------------------------------------------------");
//							}
							locationPopUpValidation();
							waitTime(10000);
							if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,"Otp Page"))
							{
								click(MLWalletLoginPage.objOtpContineBtn, "Otp Continue Button");
							    logger.info("PB_TC_35, Pay Bills Location Deny Functionality Validated");
							    extentLoggerPass("PB_TC_35", "PB_TC_35, Pay Bills Location Deny Functionality Validated");
							    System.out.println("-----------------------------------------------------------");
							}
							waitTime(15000);
							click(MLWalletPayBillsPage.objBackToHomeBtn, "Back To Home Button");
							mlWalletLogout1();
						}


						public void payBillsLocationPermissionDenyCloseBtnFunctionality_PB_TC_36() throws Exception {
							HeaderChildNode("Pay Bills Location Permission Deny Close Button Functionality Validation");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
							waitTime(10000);
							verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
							Swipe("UP",1);
							waitTime(2000);
							click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
//							if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
//								permissionDenyCloseBtnFunctionality();
//								if(verifyElementPresent(MLWalletPayBillsPage.objBillsPayInformation,getTextVal(MLWalletPayBillsPage.objBillsPayInformation,"Page"))){
//									logger.info("PB_TC_36, Pay Bills Location Permission Deny Close Button Functionality Validated");
//									extentLoggerPass("PB_TC_36", "PB_TC_36, Pay Bills Location Permission Deny Close Button Functionality Validated");
//									System.out.println("-----------------------------------------------------------");
//								}
//							}
							locationPopUpValidation();
							waitTime(10000);
							if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,"Otp Page"))
							{
								click(MLWalletLoginPage.objOtpContineBtn, "Otp Continue Button");
							    logger.info("PB_TC_35, Pay Bills Location Deny Functionality Validated");
							    extentLoggerPass("PB_TC_35", "PB_TC_35, Pay Bills Location Deny Functionality Validated");
							System.out.println("-----------------------------------------------------------");
							}
							waitTime(15000);
							click(MLWalletPayBillsPage.objBackToHomeBtn, "Back To Home Button");
							mlWalletLogout1();
						}


						public void payBillsLocationPermissionDenyOpenSettingsBtnFunctionality_PB_TC_37() throws Exception {
							HeaderChildNode("Pay Bills Location Permission Deny open Settings Button Functionality Validation");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
							waitTime(10000);
							verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
							Swipe("UP",1);
							click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
//							if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
//								permissionDenyOpenSettingsBtnFunctionality();
//								if (verifyElementPresent(SendTransferPage.objAppInfo, getTextVal(SendTransferPage.objAppInfo, "Page"))) {
//									logger.info("PB_TC_37, Pay Bills Location Permission Deny Open Settings Button Functionality Validated");
//									extentLoggerPass("PB_TC_37", "PB_TC_37, Pay Bills Location Permission Deny Open Settings Button Functionality Validated");
//									System.out.println("-----------------------------------------------------------");
//								}
//							}
							locationPopUpValidation();
							waitTime(10000);
							if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,"Otp Page"))
							{
								click(MLWalletLoginPage.objOtpContineBtn, "Otp Continue Button");
							    logger.info("PB_TC_35, Pay Bills Location Deny Functionality Validated");
							    extentLoggerPass("PB_TC_35", "PB_TC_35, Pay Bills Location Deny Functionality Validated");
							    System.out.println("-----------------------------------------------------------");
							}
							waitTime(15000);
							click(MLWalletPayBillsPage.objBackToHomeBtn, "Back To Home Button");
							mlWalletLogout1();
						}


						public void payBillsLocationPopUpAllowFunctionality_PB_TC_38() throws Exception {
							HeaderChildNode("Pay Bills Location popup Allow Button Functionality Validation");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
							waitTime(10000);
							verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
							Swipe("UP",1);
							click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
//							if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
//								locationPopUpAllowFunctionality();
//								if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,getTextVal(MLWalletLoginPage.objOneTimePin,"Page"))){
//									logger.info("PB_TC_38, Pay Bills Location popup Allow Button Functionality Validated");
//									extentLoggerPass("PB_TC_38", "PB_TC_38, Pay Bills Location popup Allow Button Functionality Validated");
//									System.out.println("-----------------------------------------------------------");
//								}
//							}
							locationPopUpValidation();
							waitTime(10000);
							if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,"Otp Page"))
							{
								click(MLWalletLoginPage.objOtpContineBtn, "Otp Continue Button");
							logger.info("PB_TC_35, Pay Bills Location Deny Functionality Validated");
							extentLoggerPass("PB_TC_35", "PB_TC_35, Pay Bills Location Deny Functionality Validated");
							System.out.println("-----------------------------------------------------------");
							}
							waitTime(15000);
							click(MLWalletPayBillsPage.objBackToHomeBtn, "Back To Home Button");
							mlWalletLogout1();
						}


						public void payBillsInternetInterruptionWhileEnteringOTP_PB_TC_39() throws Exception {
							HeaderChildNode("Pay Bills Internet Interruption While Entering OTP Validation");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
							verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
							Swipe("UP",1);
							click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
							enableLocation_PopUp();
							waitTime(15000);
							setWifiConnectionToONOFF("OFF");
							enterOTP(prop.getproperty("Valid_OTP"));
							if(verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"))){
								//internetConnectionError();
								logger.info("PB_TC_39, Pay Bills Internet Interruption While Entering OTP Validated");
								extentLoggerPass("PB_TC_39", "PB_TC_39, Pay Bills Internet Interruption While Entering OTP Validated");
								System.out.println("-----------------------------------------------------------");
							}
						}


						public void payBillsTransactionValidationAfterMinimizingApp_PB_TC_43() throws Exception {
							HeaderChildNode("Pay Bills Transaction Validation After Minimizing App");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Fully_verified"));
							payBillsNavigation();
							searchBiller();
							billerDetails(prop.getproperty("First_Name"), prop.getproperty("Middle_Name"), prop.getproperty("Last_Name"), "10");
							waitTime(5000);
							verifyElementPresent(MLWalletPayBillsPage.objConfirmDetails, getTextVal(MLWalletPayBillsPage.objConfirmDetails, "Page"));
							Swipe("UP",1);
							click(MLWalletPayBillsPage.objPayBtn,getTextVal(MLWalletPayBillsPage.objPayBtn,"Button"));
							waitTime(2000);
//							enableLocation_PopUp();
							enterOTP(prop.getproperty("Valid_OTP"));
							getDriver().runAppInBackground(Duration.ofSeconds(5));
							logger.info("Application relaunched after 5 Seconds");
							waitTime(5000);
							if(verifyElementPresent(MLWalletPayBillsPage.objSomethingWentWrongPopUp,getTextVal(MLWalletPayBillsPage.objSomethingWentWrongPopUp,"Message"))) {
								click(MLWalletPayBillsPage.objOKBtn, "OK Button");
								logger.info("PB_TC_40, Pay Bills Transaction Validation After Minimizing App Validated");
								extentLoggerPass("PB_TC_40", "PB_TC_40, Pay Bills Transaction Validation After Minimizing App Validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(3);
							mlWalletLogout1();
						}
						
						
						
						//========================== Trouble signing In =====================================================================//


						public void troubleSigningInPageNavigationFromMpinScreen_TS_TC_01() throws Exception {
							HeaderChildNode("Trouble Signing In Page Navigation From Mpin Screen");
							waitTime(2000);
							changeNumberPage();
							clearField();
							mlWalletLogin(prop.getproperty("Branch_Verified"));
							mlWalletLogout();
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
							waitTime(2000);
							if(verifyElementPresent(MLWalletTroubleSigningInPage.objTroubleSingingInPAge,getTextVal(MLWalletTroubleSigningInPage.objTroubleSingingInPAge,"Page"))){
								logger.info("TS_TC_01, Trouble Signing In Page Navigation From Mpin Screen Validated");
								extentLoggerPass("TS_TC_01", "TS_TC_01, Trouble Signing In Page Navigation From Mpin Screen Validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(1);
						}

						public void troubleSigningInPageNavigationFromLoginScreen_TS_TC_10() throws Exception {
							HeaderChildNode("Trouble Signing In Page Navigation From Login Screen");
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
							waitTime(2000);
							if(verifyElementPresent(MLWalletTroubleSigningInPage.objTroubleSingingInPAge,getTextVal(MLWalletTroubleSigningInPage.objTroubleSingingInPAge,"Page"))){
								logger.info("TS_TC_10, Trouble Signing In Page Navigation From Login Screen Validated");
								extentLoggerPass("TS_TC_10", "TS_TC_10, Trouble Signing In Page Navigation From Login Screen Validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(1);
						}

						public void troubleSigningInPageBackArrowBtnFunctionality_TS_TC_11() throws Exception {
							HeaderChildNode("Trouble Signing In Page Back Arrow Button Functionality");
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
							waitTime(5000);
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningInBackArrowBtn,"Back Arrow Button");
							waitTime(2000);
							if(verifyElementPresent(MLWalletLoginPage.objLoginBtn,getTextVal(MLWalletLoginPage.objLoginBtn,"Button"))){
								logger.info("TS_TC_11, Trouble Signing In Page Back Arrow Button Functionality Validated");
								extentLoggerPass("TS_TC_11", "TS_TC_11, Trouble Signing In Page Back Arrow Button Functionality Validated");
								System.out.println("-----------------------------------------------------------");
							}
						}

						public void troubleSigningInClearFormFunctionality_TS_TC_12() throws Exception {
							HeaderChildNode("Trouble Signing In Clear form functionality");
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
							waitTime(2000);
							click(MLWalletTroubleSigningInPage.objFullNameField, "First Name Field");
							type(MLWalletTroubleSigningInPage.objFullNameField, prop.getproperty("First_Name") ,"First Name Field");
//							String sFulName = getText(MLWalletTroubleSigningInPage.objFullNameField);
							click(MLWalletTroubleSigningInPage.objRegisteredEmail, "Registered Email Field");
							type(MLWalletTroubleSigningInPage.objRegisteredEmail, prop.getproperty("Email") ,"Registered Email Field");
//							String sRegisteredEmail = getText(MLWalletTroubleSigningInPage.objRegisteredEmail);
							Swipe("UP",1);
							click(MLWalletTroubleSigningInPage.objMobileNumber, "Mobile Number Field");
							type(MLWalletTroubleSigningInPage.objMobileNumber, prop.getproperty("Branch_Verified") ,"Mobile Number Field");
//							String sMobileNumber = getText(MLWalletTroubleSigningInPage.objMobileNumber);
							Swipe("UP",2);
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objNatureOfRequest,getTextVal(MLWalletTroubleSigningInPage.objNatureOfRequest,"Nature of Request"));
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objClearFormButton,getTextVal(MLWalletTroubleSigningInPage.objClearFormButton,"Button"));
							if(verifyElementPresent(MLWalletTroubleSigningInPage.objClearFormPopup,getTextVal(MLWalletTroubleSigningInPage.objClearFormPopup,"Popup"))){
								verifyElementPresent(MLWalletTroubleSigningInPage.objClearFormPopupMsg,getTextVal(MLWalletTroubleSigningInPage.objClearFormPopupMsg,"Popup Msg"));
								verifyElementPresent(MLWalletTroubleSigningInPage.objClearFormButton,getTextVal(MLWalletTroubleSigningInPage.objClearFormButton,"Button"));
								verifyElementPresent(MLWalletTroubleSigningInPage.objCancelBtn,getTextVal(MLWalletTroubleSigningInPage.objCancelBtn,"Button"));
								click(MLWalletTroubleSigningInPage.objCancelBtn, "Cancel Button");
								backArrowBtn(1);
								logger.info("TS_TC_12, Trouble Signing In Clear form functionality Validated");
								extentLoggerPass("TS_TC_12", "TS_TC_12, Trouble Signing In Clear form functionality Validated");
								System.out.println("-----------------------------------------------------------");
							}
						}


						public void troubleSigningInClearFormButtonOnClearFormPopupFunctionality_TS_TC_13() throws Exception {
							HeaderChildNode("Trouble Signing In Clear form Btn on Clear form popup functionality");
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
							waitTime(2000);
							click(MLWalletTroubleSigningInPage.objFullNameField, "First Name Field");
							type(MLWalletTroubleSigningInPage.objFullNameField, prop.getproperty("First_Name") ,"First Name Field");
							String sFullName = getText(MLWalletTroubleSigningInPage.objFullNameField);
							
							click(MLWalletTroubleSigningInPage.objRegisteredEmail, "Registered Email Field");
							type(MLWalletTroubleSigningInPage.objRegisteredEmail, prop.getproperty("Email") ,"Registered Email Field");
							String sRegisteredEmail = getText(MLWalletTroubleSigningInPage.objRegisteredEmail);
							Swipe("UP",1);
							
							click(MLWalletTroubleSigningInPage.objMobileNumber, "Mobile Number Field");
							type(MLWalletTroubleSigningInPage.objMobileNumber, prop.getproperty("Branch_Verified") ,"Mobile Number Field");
							String sMobileNumber = getText(MLWalletTroubleSigningInPage.objMobileNumber);
							Swipe("UP",2);
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objNatureOfRequest,getTextVal(MLWalletTroubleSigningInPage.objNatureOfRequest,"Nature of Request"));
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objClearFormButton,getTextVal(MLWalletTroubleSigningInPage.objClearFormButton,"Button"));
							verifyElementPresent(MLWalletTroubleSigningInPage.objClearFormPopup,getTextVal(MLWalletTroubleSigningInPage.objClearFormPopup,"Popup"));
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objClearFormButton,getTextVal(MLWalletTroubleSigningInPage.objClearFormButton,"Button"));
							Swipe("DOWN",3);
							String sActualFullName = getText(MLWalletTroubleSigningInPage.objFullNameField);
							String sActualRegisteredEmail = getText(MLWalletTroubleSigningInPage.objRegisteredEmail);
							Swipe("UP",1);
							String sActualMobileNumber = getText(MLWalletTroubleSigningInPage.objMobileNumber);
							assertNotEquals(sActualFullName,sFullName);
							assertNotEquals(sActualRegisteredEmail,sRegisteredEmail);
							assertNotEquals(sActualMobileNumber,sMobileNumber);
							logger.info("TS_TC_13, Trouble Signing In Clear form Btn on Clear form popup functionality Validated");
							extentLoggerPass("TS_TC_13", "TS_TC_13, Trouble Signing In Clear form Btn on Clear form popup functionality Validated");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(1);
						}


						public void troubleSigningInCancelButtonOnClearFormPopupFunctionality_TS_TC_14() throws Exception {
							HeaderChildNode("Trouble Signing In Cancel form Btn on Clear form popup functionality");
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
							waitTime(2000);
							click(MLWalletTroubleSigningInPage.objFullNameField, "First Name Field");
							type(MLWalletTroubleSigningInPage.objFullNameField, prop.getproperty("First_Name") ,"First Name Field");
							String sFullName = getText(MLWalletTroubleSigningInPage.objFullNameField);
							
							click(MLWalletTroubleSigningInPage.objRegisteredEmail, "Registered Email Field");
							type(MLWalletTroubleSigningInPage.objRegisteredEmail, prop.getproperty("Email") ,"Registered Email Field");
							String sRegisteredEmail = getText(MLWalletTroubleSigningInPage.objRegisteredEmail);
							Swipe("UP",1);
							
							click(MLWalletTroubleSigningInPage.objMobileNumber, "Mobile Number Field");
							type(MLWalletTroubleSigningInPage.objMobileNumber, prop.getproperty("Branch_Verified") ,"Mobile Number Field");
							String sMobileNumber = getText(MLWalletTroubleSigningInPage.objMobileNumber);
							Swipe("UP",2);
							
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objNatureOfRequest,getTextVal(MLWalletTroubleSigningInPage.objNatureOfRequest,"Nature of Request"));
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objClearFormButton,getTextVal(MLWalletTroubleSigningInPage.objClearFormButton,"Button"));
							verifyElementPresent(MLWalletTroubleSigningInPage.objClearFormPopup,getTextVal(MLWalletTroubleSigningInPage.objClearFormPopup,"Popup"));
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objCancelBtn,getTextVal(MLWalletTroubleSigningInPage.objCancelBtn,"Button"));
							Swipe("DOWN",3);
							String sActualFullName = getText(MLWalletTroubleSigningInPage.objFullNameField);
							String sActualRegisteredEmail = getText(MLWalletTroubleSigningInPage.objRegisteredEmail);
							Swipe("UP",1);
							String sActualMobileNumber = getText(MLWalletTroubleSigningInPage.objMobileNumber);
							assertionValidation(sActualFullName,sFullName);
							assertionValidation(sActualRegisteredEmail,sRegisteredEmail);
							assertionValidation(sActualMobileNumber,sMobileNumber);
							logger.info("TS_TC_14, Trouble Signing In Cancel Btn on Clear form popup functionality Validated");
							extentLoggerPass("TS_TC_14", "TS_TC_14, Trouble Signing In Cancel Btn on Clear form popup functionality Validated");
							System.out.println("-----------------------------------------------------------");
							backArrowBtn(1);
						}


						public void troubleSigningInEmptyFullNameFunctionality_TS_TC_15() throws Exception {
							HeaderChildNode("Trouble Signing In Empty Full Name Functionality");
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
							waitTime(2000);
							Swipe("UP",4);
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objNextBtn,getTextVal(MLWalletTroubleSigningInPage.objNextBtn,"Button"));
							waitTime(2000);
							if(verifyElementPresent(MLWalletTroubleSigningInPage.objRequiredQuestion,getTextVal(MLWalletTroubleSigningInPage.objRequiredQuestion,"Error msg"))){
								String sActualErrorMsg = getText(MLWalletTroubleSigningInPage.objRequiredQuestion);
								String sExceptedErrorMsg = "This is a required question";
								assertionValidation(sActualErrorMsg,sExceptedErrorMsg);
								logger.info("TS_TC_15, Trouble Signing In Empty Full Name Functionality Validated");
								extentLoggerPass("TS_TC_15", "TS_TC_15, Trouble Signing In Empty Full Name Functionality Validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(1);
						}

						public void troubleSigningInEmptyRegisteredEmailFunctionality_TS_TC_16() throws Exception {
							HeaderChildNode("Trouble Signing In Empty Registered Email Functionality");
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
							waitTime(2000);
							type(MLWalletTroubleSigningInPage.objFullNameField, prop.getproperty("First_Name") ,"First Name Field");
							Swipe("UP",4);
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objNextBtn,getTextVal(MLWalletTroubleSigningInPage.objNextBtn,"Button"));
							if(verifyElementPresent(MLWalletTroubleSigningInPage.objRequiredQuestion,getTextVal(MLWalletTroubleSigningInPage.objRequiredQuestion,"Error msg"))){
								String sActualErrorMsg = getText(MLWalletTroubleSigningInPage.objRequiredQuestion);
								String sExceptedErrorMsg = "This is a required question";
								assertionValidation(sActualErrorMsg,sExceptedErrorMsg);
								logger.info("TS_TC_16, Trouble Signing In Empty Registered Email Functionality Validated");
								extentLoggerPass("TS_TC_16", "TS_TC_16, Trouble Signing In Empty Registered Email Functionality Validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(1);
						}


						public void troubleSigningInEmptyRegisteredMobileNumberFunctionality_TS_TC_17() throws Exception {
							HeaderChildNode("Trouble Signing In Empty Registered Mobile Number Functionality");
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objTroubleSigningIn,getTextVal(MLWalletTroubleSigningInPage.objTroubleSigningIn,"Button"));
							waitTime(2000);
							type(MLWalletTroubleSigningInPage.objFullNameField, prop.getproperty("First_Name") ,"First Name Field");
							type(MLWalletTroubleSigningInPage.objRegisteredEmail, prop.getproperty("Email") ,"Registered Email Field");
							Swipe("UP",4);
							verifyElementPresentAndClick(MLWalletTroubleSigningInPage.objNextBtn,getTextVal(MLWalletTroubleSigningInPage.objNextBtn,"Button"));
							if(verifyElementPresent(MLWalletTroubleSigningInPage.objRequiredQuestion,getTextVal(MLWalletTroubleSigningInPage.objRequiredQuestion,"Error msg"))){
								String sActualErrorMsg = getText(MLWalletTroubleSigningInPage.objRequiredQuestion);
								String sExceptedErrorMsg = "This is a required question";
								assertionValidation(sActualErrorMsg,sExceptedErrorMsg);
								logger.info("TS_TC_17, Trouble Signing In Empty Mobile Number Functionality Validated");
								extentLoggerPass("TS_TC_17", "TS_TC_17, Trouble Signing In Empty Mobile Number Functionality Validated");
								System.out.println("-----------------------------------------------------------");
							}
							backArrowBtn(1);
						}
						
						

						//=================================== Buy e - load ======================================================//
						//==================================== Generalized methods ============================================//


							public void eLoad_generic(String sTier,String mobileNo, String status, int telcoOption) throws Exception
							{
								mlWalletLogin(sTier);
								click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
								waitTime(5000);
								if(status.equals("true")) {
									verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage, "eLoad Transaction Page");
									click(MLWalletEloadPage.telcoOptions(telcoOption),"Telco");
								}
								click(MLWalletEloadPage.objMobileNoField, "Mobile Number Field");
								type(MLWalletEloadPage.objMobileNoField, mobileNo, "Mobile Number Field");
								explicitWaitVisible(MLWalletEloadPage.objNextBtn,10);
								click(MLWalletEloadPage.objNextBtn, "Next Button");
								click(MLWalletEloadPage.objNextBtn, "Next Button");
							}
						//===================================================================================================//



							public void buyELoadTransactionDetails_BE_TC_01(String sTier,int promotab) throws Exception
							{
								HeaderChildNode("Transaction Details Validation after Buying eLoad");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								explicitWaitVisibility(MLWalletEloadPage.objLoadSelectionPage, 10);
								verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
								click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
								waitTime(5000);
								click(MLWalletEloadPage.objTransaction, getTextVal(MLWalletEloadPage.objTransaction, "Promo"));
								explicitWaitVisibility(MLWalletEloadPage.objContinuePromoPopUp, 10);
								verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
								waitTime(5000);
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								waitTime(3000);
								verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								enterOTP(prop.getproperty("Valid_OTP"));
								waitTime(40000);
								if(verifyElementPresent(MLWalletEloadPage.objTransactionDetailsPage,getTextVal(MLWalletEloadPage.objTransactionDetailsPage,"Page"))){
									verifyElementPresent(MLWalletEloadPage.objMobileNumberInTransactionDetails,getTextVal(MLWalletEloadPage.objMobileNumberInTransactionDetails,"Mobile Number"));
									verifyElementPresent(MLWalletEloadPage.objBuyELoadTime,getTextVal(MLWalletEloadPage.objBuyELoadTime,"Date and Time"));
									verifyElementPresent(MLWalletEloadPage.objTypeOfPromoUsed, getTextVal(MLWalletEloadPage.objTypeOfPromoUsed, "Promo"));
									verifyElementPresent(MLWalletEloadPage.objAmountToSend, getTextVal(MLWalletEloadPage.objAmountToSend, "Amount to Send"));
									verifyElementPresent(MLWalletEloadPage.objServiceFee, getTextVal(MLWalletEloadPage.objServiceFee, "Service Fee"));
									verifyElementPresent(MLWalletEloadPage.objTotalAmount, getTextVal(MLWalletEloadPage.objTotalAmount, "Total Amount"));
									click(MLWalletCashOutPage.objBackToHomeBtn, "Back To Home Buttton");
									logger.info("BE_TC_01, Transaction Details Validated after Buying eLoad");
									extentLoggerPass("BE_TC_01", "BE_TC_01, Transaction Details Validated after Buying eLoad");
									System.out.println("-----------------------------------------------------------");
								}
								mlWalletLogout();

							}


							public void buyELoadInvalidMobileNumber_BE_TC_02() throws Exception
							{
								HeaderChildNode("Buying eLoad using invalid mobile number");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(prop.getproperty("Branch_Verified"),prop.getproperty("inValidMobNumber"),"true", 4);
								waitTime(2000);
								if(verifyElementPresent(MLWalletEloadPage.objErrorMsg, getTextVal(MLWalletEloadPage.objErrorMsg, "Pop Up Message"))){
									String sActualErrorMsg = getText(MLWalletEloadPage.objErrorMsg);
									String sExceptedErrorMsg = "Network and Mobile number does not match";
									assertionValidation(sActualErrorMsg,sExceptedErrorMsg);
									logger.info("BE_TC_02, Buy ELoad Using Invalid Mobile Number, Network and Mobile number does not match Error Message Validated");
									extentLoggerPass("BE_TC_02", "BE_TC_02, Buy ELoad Using Invalid Mobile Number, Network and Mobile number does not match Error Message Validated");
									System.out.println("-----------------------------------------------------------");
								}
								backArrowBtn(1);
								mlWalletLogout();
							}

							public void buyELoadWithoutInputMobNumber_BE_TC_03() throws Exception
							{
								HeaderChildNode("Buying eLoad without mobile number input");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(prop.getproperty("Branch_Verified"),"", "true", 4);
								waitTime(2000);
								if(verifyElementPresent(MLWalletEloadPage.objErrorMsgOne, getTextVal(MLWalletEloadPage.objErrorMsgOne, "Pop Up Message"))) {
									String sActualErrorMsg = getText(MLWalletEloadPage.objErrorMsgOne);
									String sExceptedErrorMsg = "Mobile number is required";
									assertionValidation(sActualErrorMsg, sExceptedErrorMsg);
									logger.info("BE_TC_03, Buy ELoad without mobile number input, Mobile number is required Error Message is validated");
									extentLoggerPass("BE_TC_03", "BE_TC_03, Buy ELoad without mobile number input, Mobile number is required Error Message is validated");
								}
								backArrowBtn(1);
								mlWalletLogout();
							}

							public void buyELoadWithoutTelecommunicationSelected_BE_TC_04() throws Exception
							{
								HeaderChildNode("Buying eLoad without telecommunication selected");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(prop.getproperty("Branch_Verified"),prop.getproperty("sunMobileNumber"),"false", 4);
								waitTime(2000);
								if(verifyElementPresent(MLWalletEloadPage.objErrorMsg, getTextVal(MLWalletEloadPage.objErrorMsg, "Pop Up Message"))) {
									String sActualErrorMsg = getText(MLWalletEloadPage.objErrorMsg);
									String sExceptedErrorMsg = "Network and Mobile number does not match";
									assertionValidation(sActualErrorMsg, sExceptedErrorMsg);
									logger.info("BE_TC_04, Buying eLoad without selecting telecommunication, Network and Mobile number does not match Error Msg Validated");
									extentLoggerPass("BE_TC_04", "BE_TC_04, Buying eLoad without selecting telecommunication, Network and Mobile number does not match Error Msg Validated");
								}
								backArrowBtn(1);
								mlWalletLogout();
							}

						/*	public void buyELoadInsufficientBalance_BE_TC_05(String sTier,int promotab) throws Exception
							{
								HeaderChildNode("Buying eLoad with insufficient balance");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
								click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
								waitTime(5000);
								swipeDownUntilElementVisible("Smart Regular Load 3000");
								verifyElementPresentAndClick(MLWalletEloadPage.obj2000RegularLoad,getTextVal(MLWalletEloadPage.obj2000RegularLoad,"Load"));
								verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
								waitTime(5000);
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								waitTime(5000);
								verifyElementPresent(MLWalletEloadPage.objInsufficientBalPopup, getTextVal(MLWalletEloadPage.objInsufficientBalPopup, "Pop up"));
								logger.info("BE_TC_05, Insufficient Balance Error Message Validated");
								extentLoggerPass("BE_TC_05", "BE_TC_05, Insufficient Balance Error Message Validated");
							}


							public void buyELoadMaxLimitPerTransaction_BE_TC_09(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Maximum Limit per Transaction");
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								enableLocation_PopUp();
								verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
								click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
								waitTime(5000);
								swipeDownUntilElementVisible("Smart Regular Load 3000");
								verifyElementPresentAndClick(MLWalletEloadPage.obj3000RegularLoad,getTextVal(MLWalletEloadPage.obj3000RegularLoad,"Load"));
								verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
								waitTime(5000);
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								waitTime(5000);
								if(verifyElementPresent(MLWalletEloadPage.objMaxLimitPopupMsg,getTextVal(MLWalletEloadPage.objMaxLimitPopupMsg,"Error Popup Msg"))){
									String sActualPopupMsg = getText(MLWalletEloadPage.objMaxLimitPopupMsg);
									String sExceptedPopupMsg = "The maximum E-load per transaction set for your verification level is P2,000.00. Please try again.";
									assertionValidation(sActualPopupMsg,sExceptedPopupMsg);
									logger.info("BE_TC_09, Buy ELoad Maximum Limit per Transaction Validated");
									extentLoggerPass("BE_TC_09", "BE_TC_09, Buy ELoad Maximum Limit per Transaction Validated");
									System.out.println("-----------------------------------------------------------");
								}
							} */

							public void buyELoadTransactionPageUIValidation_BE_TC_10(String sTier) throws Exception {
								HeaderChildNode("Buy ELoad Transaction Page UI Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								mlWalletLogin(sTier);
								verifyElementPresentAndClick(MLWalletEloadPage.objEloadTab, "Buy eLoad");
								waitTime(2000);
								if(verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage,getTextVal(MLWalletEloadPage.objEloadtransactionPage,"Page"))){
									verifyElementPresent(MLWalletEloadPage.objSelectTelco,getTextVal(MLWalletEloadPage.objSelectTelco,"Header"));
									verifyElementPresent(MLWalletEloadPage.objPhoneToLoad,getTextVal(MLWalletEloadPage.objPhoneToLoad,"Header"));
									verifyElementPresent(MLWalletEloadPage.objMobileNoField,"Mobile Number Input Field");
									verifyElementPresent(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
									verifyElementPresent(MLWalletEloadPage.objNextBtn,getTextVal(MLWalletEloadPage.objNextBtn,"Button"));
									logger.info("BE_TC_10, Buy ELoad Transaction Page UI Validated");
									extentLoggerPass("BE_TC_10", "BE_TC_10, Buy ELoad Transaction Page UI Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadNextButtonFunctionalityOnELoadTransactionPage_BE_TC_11(String sTier,String mobileNo,int telcoOption,String status) throws Exception {
								HeaderChildNode("Buy ELoad Next Button Functionality On ELoad Transaction Page");
								waitTime(2000);
								changeNumberPage();
								clearField();
								mlWalletLogin(sTier);
								click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
								waitTime(3000);
								verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage, "eLoad Transaction Page");
								click(MLWalletEloadPage.objMobileNoField, "Mobile Number Field");
								type(MLWalletEloadPage.objMobileNoField, mobileNo, "Mobile Number Field");
								click(MLWalletEloadPage.telcoOptions(telcoOption),"Telco");
								click(MLWalletEloadPage.telcoOptions(telcoOption),"Telco");
								click(MLWalletEloadPage.objNextBtn, "Next Button");
								click(MLWalletEloadPage.objNextBtn, "Next Button");
								explicitWaitVisibility(MLWalletEloadPage.objLoadSelectionPage, 10);
								if(verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page")){
									logger.info("BE_TC_11, Buy ELoad Next Button Functionality On ELoad Transaction Page Validated");
									extentLoggerPass("BE_TC_11", "BE_TC_11, Buy ELoad Next Button Functionality On ELoad Transaction Page Validated");
									System.out.println("-----------------------------------------------------------");
								}
								if(status.equals("true"))
								{
								backArrowBtn(2);
								mlWalletLogout();
								}
							}


							public void buyELoadLoadSelectionPageBackBtnValidation_BE_TC_12() throws Exception {
								HeaderChildNode("Buy ELoad Load Selection Back Arrow Button Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								buyELoadNextButtonFunctionalityOnELoadTransactionPage_BE_TC_11(prop.getproperty("Branch_Verified"),prop.getproperty("sunMobileNumber"),4,"false");
								verifyElementPresentAndClick(MLWalletEloadPage.objLoadSelectionBackArrowBtn,"Load Selection Back Arrow Button");
								explicitWaitVisibility(MLWalletEloadPage.objEloadtransactionPage, 10);
								if(verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage,getTextVal(MLWalletEloadPage.objEloadtransactionPage,"Page"))){
									logger.info("BE_TC_12, After clicking on Back Arrow Btn on Load Selection Page, Application Navigates to eLoad Transaction Page is Validated");
									extentLoggerPass("BE_TC_12", "BE_TC_12,  After clicking on Back Arrow Btn on Load Selection Page, Application Navigates to eLoad Transaction Page is Validated");
									System.out.println("-----------------------------------------------------------");
								}
								backArrowBtn(1);
								mlWalletLogout();
							}

							public void buyELoadLoadSelectionPageUIValidation_BE_TC_13(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Load Selection Page UI Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								waitTime(3000);
								if(verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage,getTextVal(MLWalletEloadPage.objLoadSelectionPage,"Page"))){
									verifyElementPresent(MLWalletEloadPage.objMobileNumberInLoadSelection,getTextVal(MLWalletEloadPage.objMobileNumberInLoadSelection,"Number"));
									verifyElementPresent(MLWalletEloadPage.objChange,getTextVal(MLWalletEloadPage.objChange,"button"));
									verifyElementPresent(MLWalletEloadPage.objWalletBalanceInLoadSeletion,getTextVal(MLWalletEloadPage.objWalletBalanceInLoadSeletion,"Balance"));
									verifyElementPresent(MLWalletEloadPage.objPromoLoadTab,getTextVal(MLWalletEloadPage.objPromoLoadTab,"Tab"));
									verifyElementPresent(MLWalletEloadPage.objRegularLoadTab,getTextVal(MLWalletEloadPage.objRegularLoadTab,"Tab"));
									verifyElementPresent(MLWalletEloadPage.objPromoLoads,getTextVal(MLWalletEloadPage.objPromoLoads,"Promo Load"));
//									List<WebElement> values = findElements(MLWalletEloadPage.objPromoLoads);
//									for (int i = 0; i < values.size(); i++) {
//										String sPromoLoads = values.get(i).getText();
//										logger.info("Promo Load : " + sPromoLoads + " is displayed");
//										extentLogger(" ", "Promo Load : " + sPromoLoads + " is displayed");
//									}
									logger.info("BE_TC_13, Buy ELoad Load Selection Page UI Validated");
									extentLoggerPass("BE_TC_13", "BE_TC_13,  Buy ELoad Load Selection Page UI Validated");
									System.out.println("-----------------------------------------------------------");
								}
								backArrowBtn(2);
								mlWalletLogout();
							}

							public void buyELoadLoadSelectionChangeBtnFunctionality_BE_TC_14(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Load Selection Change Button Functionality Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								explicitWaitVisibility(MLWalletEloadPage.objChange, 10);
								verifyElementPresentAndClick(MLWalletEloadPage.objChange,getTextVal(MLWalletEloadPage.objChange,"Button"));
								explicitWaitVisibility(MLWalletEloadPage.objEloadtransactionPage, 10);
								if(verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage,getTextVal(MLWalletEloadPage.objEloadtransactionPage,"Page"))){
									logger.info("BE_TC_14, After clicking on Change Btn in Load Selection Page, Application navigates to eLoad Transaction page is Validated");
									extentLoggerPass("BE_TC_14", "BE_TC_14, After clicking on Change Btn in Load Selection Page, Application navigates to eLoad Transaction page is Validated");
									System.out.println("-----------------------------------------------------------");
								}
								backArrowBtn(1);
								mlWalletLogout();
							}

							public void buyELoadTransactionDetailsPageUIValidation_BE_TC_15(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Transaction Details Page UI Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								explicitWaitVisibility(MLWalletEloadPage.objLoadSelectionPage, 10);
								verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
								click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
								waitTime(5000);
								click(MLWalletEloadPage.objTransaction, getTextVal(MLWalletEloadPage.objTransaction, "Promo"));
								explicitWaitVisible(MLWalletEloadPage.objContinuePromoPopUp, 10);
								verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
								waitTime(5000);
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								explicitWaitVisibility(MLWalletEloadPage.objBuyLoad, 15);
								if(verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"))){
									verifyElementPresent(MLWalletEloadPage.objLoadWithAmount,getTextVal(MLWalletEloadPage.objLoadWithAmount,"Load With Amount"));
									verifyElementPresent(MLWalletEloadPage.objTransactionDetailsPage,getTextVal(MLWalletEloadPage.objTransactionDetailsPage,"Header"));
									verifyElementPresent(MLWalletEloadPage.objMobileNumber,getTextVal(MLWalletEloadPage.objMobileNumber,"Mobile Number"));
									verifyElementPresent(MLWalletEloadPage.objTypeOfPromoUsed, getTextVal(MLWalletEloadPage.objTypeOfPromoUsed, "Promo"));
									verifyElementPresent(MLWalletEloadPage.objAmountToSend, getTextVal(MLWalletEloadPage.objAmountToSend, "Amount to Send"));
									verifyElementPresent(MLWalletEloadPage.objServiceFee, getTextVal(MLWalletEloadPage.objServiceFee, "Service Fee"));
									verifyElementPresent(MLWalletEloadPage.objTotalAmount, getTextVal(MLWalletEloadPage.objTotalAmount, "Total Amount"));
									verifyElementPresent(MLWalletEloadPage.objConfirmBtn,getTextVal(MLWalletEloadPage.objConfirmBtn,"Button"));
									logger.info("BE_TC_15, Buy ELoad Transaction Details Page UI Validated");
									extentLoggerPass("BE_TC_15", "BE_TC_15, Buy ELoad Transaction Details Page UI Validated");
									System.out.println("-----------------------------------------------------------");
								}
								backArrowBtn(3);
								mlWalletLogout();
							}

							public void buyELoadSelectFromContactsBtnFunctionality_BE_TC_16(String sTier, String status) throws Exception {
								HeaderChildNode("Buy ELoad Select From Contacts Functionality");
								waitTime(2000);
								changeNumberPage();
								clearField();
								mlWalletLogin(sTier);
								click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
								waitTime(3000);
								verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
								waitTime(2000);
								if(verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"))){
									logger.info("BE_TC_16, Buy ELoad, After clicking on Select From Contacts Application Navigates to Contacts Page");
									extentLoggerPass("BE_TC_16", "BE_TC_16, Buy ELoad, After clicking on Select From Contacts Application Navigates to Contacts Page");
									System.out.println("-----------------------------------------------------------");
								}
								if(status.equals("true"))
								{
								backArrowBtn(2);
								mlWalletLogout();
								}
							}

							public void buyELoadContactsPageUIValidation_BE_TC_17(String sTier) throws Exception {
								HeaderChildNode("Buy ELoad Contacts Page UI Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								mlWalletLogin(sTier);
								click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
								waitTime(2000);
								verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
								waitTime(2000);
								verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"));
								verifyElementPresent(MLWalletEloadPage.objFavorites,getTextVal(MLWalletEloadPage.objFavorites,"Page"));
								
//								if(verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"))) {
//									for (int i = 1; i <= 7; i++) {
//										for (int j = 1; j <= 2; j++) {
//											if (j == 1) {
//												String sContactName = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
//												logger.info("Contact Name : " + sContactName + " is displayed");
//												extentLogger(" ", "Contact Name : " + sContactName + " is displayed");
//											}
//											if (j == 2) {
//												String sContactNumber = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
//												logger.info("Contact Number : " + sContactNumber + " is displayed");
//												extentLogger(" ", "Contact Number : " + sContactNumber + " is displayed");
//											}
//										}
//									}
									logger.info("BE_TC_17, Buy ELoad Contacts Page UI Validated");
									extentLoggerPass("BE_TC_17", "BE_TC_17, Buy ELoad Contacts Page UI Validated");
									System.out.println("-----------------------------------------------------------");
									backArrowBtn(2);
									mlWalletLogout();
								}
							
							
							

							public void buyELoadContactsPageBackBtnValidation_BE_TC_18(String sTier) throws Exception {
								HeaderChildNode("Buy ELoad Contacts Page Back Arrow Button Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								buyELoadSelectFromContactsBtnFunctionality_BE_TC_16(sTier,"false");
								explicitWaitVisibility(MLWalletEloadPage.objContactsPageBackBtn, 10);
								verifyElementPresentAndClick(MLWalletEloadPage.objContactsPageBackBtn,"Contacts Page back arrow Button");
								explicitWaitVisibility(MLWalletEloadPage.objEloadtransactionPage, 10);
								if(verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage,getTextVal(MLWalletEloadPage.objEloadtransactionPage,"Page"))){
									logger.info("BE_TC_18, Buy ELoad Contacts Page Back Arrow Button Validated");
									extentLoggerPass("BE_TC_18", "BE_TC_18, Buy ELoad Contacts Page Back Arrow Button Validated");
									System.out.println("-----------------------------------------------------------");
								}
								backArrowBtn(1);
								mlWalletLogout();
							}

							/*	public void buyELoadSearchFieldValidation_BE_TC_19(String sTier) throws Exception {
								HeaderChildNode("Buy ELoad Search Field Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								mlWalletLogin(sTier);
								click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
								waitTime(5000);
								verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
								explicitWaitVisibility(MLWalletEloadPage.objContacts, 10);
								verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"));
								explicitWaitVisibility(MLWalletEloadPage.objSearch, 10);
								verifyElementPresent(MLWalletEloadPage.objSearch,"Contact Search");
								type(MLWalletEloadPage.objSearch, prop.getproperty("contactName"), "Contact Search");
//								if(verifyElementPresent(MLWalletEloadPage.objSearchedContactName,getTextVal(MLWalletEloadPage.objSearchedContactName,"Contact name")))
//								{
//									verifyElementPresent(MLWalletEloadPage.objSearchedContactNumber,getTextVal(MLWalletEloadPage.objSearchedContactNumber,"Contact Number"));		
//								}else
//								{
//									logger.info("No Contacts found");
//									extent.extentLogger("", "No Contacts found");
//								}
//								logger.info("BE_TC_19, Buy ELoad Search Field Validated");
								extentLoggerPass("BE_TC_19", "BE_TC_19, Buy ELoad Search Field Validated");
								System.out.println("-----------------------------------------------------------");
								backArrowBtn(2);
								mlWalletLogout();
							}

							public void buyELoadAddContactToFavorites_BE_TC_20(String sTier) throws Exception {
								HeaderChildNode("Buy ELoad Add Contact To Favorites");
								mlWalletLogin(sTier);
								click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
								verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
								verifyElementPresentAndClick(MLWalletEloadPage.objAllowBtn,getTextVal(MLWalletEloadPage.objAllowBtn,"Button"));
								verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"));
								type(MLWalletEloadPage.objSearch, prop.getproperty("contactName"), "Contact Search");
								verifyElementPresent(MLWalletEloadPage.objSearchedContactName,getTextVal(MLWalletEloadPage.objSearchedContactName,"Contact name"));
								String sContactName = getText(MLWalletEloadPage.objSearchedContactName);
								verifyElementPresent(MLWalletEloadPage.objSearchedContactNumber,getTextVal(MLWalletEloadPage.objSearchedContactNumber,"Contact Number"));
								String sContactNumber = getText(MLWalletEloadPage.objSearchedContactNumber);
								hideKeyboard();
								verifyElementPresentAndClick(MLWalletEloadPage.objAddToFavoriteIcon,"Add To Favorite Icon");
								verticalSwipeByPercentages(0.5,0.9,0.5);
								verifyElementPresentAndClick(MLWalletEloadPage.objFavorites,getTextVal(MLWalletEloadPage.objFavorites,"Button"));
								if(verifyElementPresent(MLWalletEloadPage.objSearchedContactName,getTextVal(MLWalletEloadPage.objSearchedContactName,"Contact name"))){
									String sContactNameInFavorites = getText(MLWalletEloadPage.objSearchedContactName);
									assertionValidation(sContactName,sContactNameInFavorites);
									String sContactNumberInFavorites = getText(MLWalletEloadPage.objSearchedContactNumber);
									assertionValidation(sContactNumber,sContactNumberInFavorites);
									logger.info("BE_TC_20, Buy ELoad Add Contact To Favorites Validated");
									extentLoggerPass("BE_TC_20", "BE_TC_20, Buy ELoad Add Contact To Favorites Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadAddedContactToFavoritesValidation_BE_TC_21(String sTier) throws Exception {
								HeaderChildNode("Buy ELoad Added Contacts To Favorites validation");
								mlWalletLogin(sTier);
								click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
								verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
								verifyElementPresentAndClick(MLWalletEloadPage.objAllowBtn,getTextVal(MLWalletEloadPage.objAllowBtn,"Button"));
								verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"));
								type(MLWalletEloadPage.objSearch, prop.getproperty("contactNumber"), "Contact Search");
								verifyElementPresent(MLWalletEloadPage.objSearchedContactName,getTextVal(MLWalletEloadPage.objSearchedContactName,"Contact name"));
								verifyElementPresent(MLWalletEloadPage.objSearchedContactNumber,getTextVal(MLWalletEloadPage.objSearchedContactNumber,"Contact Number"));
								hideKeyboard();
								verifyElementPresentAndClick(MLWalletEloadPage.objAddToFavoriteIcon,"Add To Favorite Icon");
								verticalSwipeByPercentages(0.5,0.9,0.5);
								clearField(MLWalletEloadPage.objSearch,"Search field");
								if(verifyElementPresentAndClick(MLWalletEloadPage.objFavorites,getTextVal(MLWalletEloadPage.objFavorites,"Button"))){
									waitTime(3000);
									for (int i = 1; i <= 2; i++) {
										for (int j = 1; j <= 2; j++) {
											if (j == 1) {
												String sContactName = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
												logger.info("Contact Name : " + sContactName + " is displayed");
												extentLogger(" ", "Contact Name : " + sContactName + " is displayed");
											}
											if (j == 2) {
												String sContactNumber = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
												logger.info("Contact Number : " + sContactNumber + " is displayed");
												extentLogger(" ", "Contact Number : " + sContactNumber + " is displayed");
											}
										}
									}
									logger.info("BE_TC_21, Buy ELoad Added Contacts To Favorites Validated");
									extentLoggerPass("BE_TC_21", "BE_TC_21, Buy ELoad Added Contacts To Favorites Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadSearchAddedFavoriteContact_BE_TC_22(String sTier) throws Exception {
								HeaderChildNode("Buy ELoad Search Added Favorite Contact");
								mlWalletLogin(sTier);
								click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
								verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
								verifyElementPresentAndClick(MLWalletEloadPage.objAllowBtn,getTextVal(MLWalletEloadPage.objAllowBtn,"Button"));
								verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"));
								type(MLWalletEloadPage.objSearch, prop.getproperty("favoriteContact"), "Contact Search");
								verifyElementPresent(MLWalletEloadPage.objSearchedContactName,getTextVal(MLWalletEloadPage.objSearchedContactName,"Contact name"));
								verifyElementPresent(MLWalletEloadPage.objSearchedContactNumber,getTextVal(MLWalletEloadPage.objSearchedContactNumber,"Contact Number"));
								hideKeyboard();
								verifyElementPresentAndClick(MLWalletEloadPage.objAddToFavoriteIcon,"Add To Favorite Icon");
								verticalSwipeByPercentages(0.5,0.9,0.5);
								clearField(MLWalletEloadPage.objSearch,"Search field");
								verifyElementPresentAndClick(MLWalletEloadPage.objFavorites,getTextVal(MLWalletEloadPage.objFavorites,"Button"));
								if(verifyElementPresent(MLWalletEloadPage.objSearch,"Contact Search")) {
									type(MLWalletEloadPage.objSearch, prop.getproperty("favoriteContactNumber"), "Contact Search");
									verifyElementPresent(MLWalletEloadPage.objSearchedContactName,getTextVal(MLWalletEloadPage.objSearchedContactName,"Contact name"));
									verifyElementPresent(MLWalletEloadPage.objSearchedContactNumber,getTextVal(MLWalletEloadPage.objSearchedContactNumber,"Contact Number"));
									logger.info("BE_TC_22, Buy ELoad Search Added Favorite Contact Validated");
									extentLoggerPass("BE_TC_22", "BE_TC_22, Buy ELoad Search Added Favorite Contact Validated");
									System.out.println("-----------------------------------------------------------");
								}
							} 

							public void buyELoadSearchUnFavoriteContact_BE_TC_23(String sTier) throws Exception {
								HeaderChildNode("Buy ELoad Search UnFavorite Contact");
								mlWalletLogin(sTier);
								click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
								verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
								verifyElementPresentAndClick(MLWalletEloadPage.objAllowBtn,getTextVal(MLWalletEloadPage.objAllowBtn,"Button"));
								verifyElementPresent(MLWalletEloadPage.objContacts,getTextVal(MLWalletEloadPage.objContacts,"Page"));
								verifyElementPresentAndClick(MLWalletEloadPage.objFavorites,getTextVal(MLWalletEloadPage.objFavorites,"Button"));
								if(verifyElementPresent(MLWalletEloadPage.objSearch,"Contact Search")) {
									type(MLWalletEloadPage.objSearch, prop.getproperty("unFavoriteNumber"), "Contact Search");
									verifyElementPresent(MLWalletEloadPage.objNoFavoritesFoundMsg,getTextVal(MLWalletEloadPage.objNoFavoritesFoundMsg,"Message"));
									logger.info("BE_TC_23, Buy ELoad Search UnFavorite Contact Validated");
									extentLoggerPass("BE_TC_23", "BE_TC_23, Buy ELoad Search UnFavorite Contact Validated");
									System.out.println("-----------------------------------------------------------");
								}
							} */

							public void buyELoadPromoConfirmationPopupValidation_BE_TC_24(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Promo Confirmation Popup Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
								click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
								waitTime(5000);
								click(MLWalletEloadPage.objTransaction, "Promo");
								waitTime(2000);
								if(verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp,getTextVal(MLWalletEloadPage.objContinuePromoPopUp,"Popup Msg"))){
									verifyElementPresent(MLWalletEloadPage.objConfirmBtn,getTextVal(MLWalletEloadPage.objConfirmBtn,"Button"));
									verifyElementPresentAndClick(MLWalletEloadPage.objCancelBtn,getTextVal(MLWalletEloadPage.objCancelBtn,"Button"));
									logger.info("BE_TC_24, Buy ELoad Promo Confirmation Popup Validated");
									extentLoggerPass("BE_TC_24", "BE_TC_24, Buy ELoad Promo Confirmation Popup Validated");
									System.out.println("-----------------------------------------------------------");
								}
								backArrowBtn(2);
								mlWalletLogout();
							}

							public void buyELoadOTPPageUIValidation_BE_TC_25(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad OTP page UI Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);			
								waitTime(2000);
								verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
								waitTime(5000);
								click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
								waitTime(5000);
								click(MLWalletEloadPage.objTransaction,  "Promo");
								explicitWaitVisibility(MLWalletEloadPage.objContinuePromoPopUp, 10);
								verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								waitTime(2000);
								verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								waitTime(5000);
								if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
									verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
									//click(SendTransferPage.objBackArrow, "Back Arrow Button");
									click(MLWalletEloadPage.objOTPCancelBtn, "OTP Cancel Button");
									logger.info("BE_TC_25, Buy ELoad One Time Pin page UI Validated");
									extentLoggerPass("BE_TC_25", "BE_TC_25, Buy ELoad One Time Pin page UI Validated");
									System.out.println("-----------------------------------------------------------");
								}
								backArrowBtn(3);
								mlWalletLogout();
							}

							public void buyELoadSuccessfulTransactionUIValidation_BE_TC_26(String sTier, int promotab, String status) throws Exception {
								HeaderChildNode("Buy ELoad Successful Transaction UI Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								enableLocation_PopUp();
								verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
								waitTime(5000);
								click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
								waitTime(5000);
								click(MLWalletEloadPage.objTransaction,  "Promo");
								explicitWaitVisibility(MLWalletEloadPage.objContinuePromoPopUp, 10);
								verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
								waitTime(2000);
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								waitTime(2000);
								verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								enterOTP(prop.getproperty("Valid_OTP"));
								waitTime(20000);
								verifyElementPresent(MLWalletEloadPage.objTransactionDetailsPage,getTextVal(MLWalletEloadPage.objTransactionDetailsPage,"Page")); 
								verifyElementPresent(MLWalletEloadPage.objMobileNumberInTransactionDetails, getTextVal(MLWalletEloadPage.objMobileNumberInTransactionDetails, "Mobile Number"));
								verifyElementPresent(MLWalletEloadPage.objBuyELoadTime, getTextVal(MLWalletEloadPage.objBuyELoadTime, "Date and Time"));
								verifyElementPresent(MLWalletEloadPage.objTypeOfPromoUsed, getTextVal(MLWalletEloadPage.objTypeOfPromoUsed, "Promo"));
								verifyElementPresent(MLWalletEloadPage.objAmountToSend, getTextVal(MLWalletEloadPage.objAmountToSend, "Amount to Send"));
								verifyElementPresent(MLWalletEloadPage.objServiceFee, getTextVal(MLWalletEloadPage.objServiceFee, "Service Fee"));
								verifyElementPresent(MLWalletEloadPage.objTotalAmount, getTextVal(MLWalletEloadPage.objTotalAmount, "Total Amount"));
								click(MLWalletCashOutPage.objBackToHomeBtn, "Back To Home Button");
								logger.info("BE_TC_26, Buy ELoad Successful Transaction UI validated");
								extentLoggerPass("BE_TC_26", "BE_TC_26, Buy ELoad Successful Transaction UI validated");
								System.out.println("-----------------------------------------------------------");
								if(status.equals("true"))
								{
									mlWalletLogout();
								}
							}
								
							


							public void buyELoadRecentTransactionUIValidation_BE_TC_27(String status) throws Exception {
								HeaderChildNode("Buy ELoad Recent Transaction UI Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								buyELoadSuccessfulTransactionUIValidation_BE_TC_26(prop.getproperty("Branch_Verified"),4,"false");
								verifyRecentTransaction();
								if(verifyElementPresent(MLWalletHomePage.objRecentTransactions,getTextVal(MLWalletHomePage.objRecentTransactions,"Header"))){
									verifyElementPresent(MLWalletHomePage.objBuyELoadTransaction,getTextVal(MLWalletHomePage.objBuyELoadTransaction,"Transaction"));
									String sStatus = getText(MLWalletHomePage.objBuyELoadTransaction);
									String postedStatus=sStatus.substring(2, 8);	
									System.out.println(postedStatus);
									String sExpectedStatus = "Posted";
									assertionValidation(postedStatus,sExpectedStatus);
									logger.info("BE_TC_27, Buy ELoad Recent Transaction UI validated");
									extentLoggerPass("BE_TC_27", "BE_TC_27, Buy ELoad Recent Transaction UI validated");
									System.out.println("-----------------------------------------------------------");
								}
								if(status.equals("true"))
								{
								mlWalletLogout();
								}
							}



							public void buyELoadTransactionDetailsUIValidation_BE_TC_28() throws Exception {
								HeaderChildNode("Buy ELoad Transaction Details UI Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								buyELoadRecentTransactionUIValidation_BE_TC_27("false");
								click(MLWalletHomePage.objBuyELoadTransaction,getTextVal(MLWalletHomePage.objBuyELoadTransaction,"Transaction"));
								waitTime(15000);
								if(verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
									verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
									verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
									verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
									verifyElementPresent(MLWalletTransactionHistoryPage.objLoadType, getTextVal(MLWalletTransactionHistoryPage.objLoadType, "Load Type"));
									verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
									verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
									verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
									verifyElementPresent(MLWalletTransactionHistoryPage.objTotalAmount, getTextVal(MLWalletTransactionHistoryPage.objTotalAmount, "Total Amount"));
									click(SendTransferPage.objBackToHomeBtn, "Back To Home Button");
									logger.info("BE_TC_28, Buy ELoad Transaction Details UI validated");
									extentLoggerPass("BE_TC_28", "BE_TC_28, Buy ELoad Transaction Details UI validated");
									System.out.println("-----------------------------------------------------------");
								}
								mlWalletLogout();
							}

						/*	public void buyELoadLocationPopupValidation_BE_TC_51(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Location Popup Validation");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
									locationPopUpValidation();
									logger.info("BE_TC_51, Buy ELoad Location Popup Validated");
									extentLoggerPass("BE_TC_51", "BE_TC_51, Buy ELoad Location Popup Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadLocationDenyFunctionality_BE_TC_52(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Location Deny Functionality");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
									permissionDenyPopUp();
									logger.info("BE_TC_52, Buy ELoad Location Deny Functionality Validated");
									extentLoggerPass("BE_TC_52", "BE_TC_52, Buy ELoad Location Deny Functionality Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadLocationPermissionDenyCloseBtnFunctionality_BE_TC_53(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Location Permission Deny Close Button Functionality");
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
									permissionDenyCloseBtnFunctionality();
									if(verifyElementPresent(MLWalletLoginPage.objAvailableBalance,getTextVal(MLWalletLoginPage.objAvailableBalance,"Page"))){
										logger.info("BE_TC_53, Buy ELoad Location Permission Deny Close Button Functionality Validated");
										extentLoggerPass("BE_TC_53", "BE_TC_53, Buy ELoad Location Permission Deny Close Button Functionality Validated");
										System.out.println("-----------------------------------------------------------");
									}
								}
							}


							public void buyELoadLocationPermissionDenyOpenSettingsBtnFunctionality_BE_TC_54(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Location Permission Deny open Settings Button Functionality Validation");
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
									permissionDenyOpenSettingsBtnFunctionality();
									if (verifyElementPresent(MLWalletEloadPage.objAppInfo, getTextVal(MLWalletEloadPage.objAppInfo, "Page"))) {
										logger.info("BE_TC_54, Buy ELoad Location Permission Deny Open Settings Button Functionality Validated");
										extentLoggerPass("BE_TC_54", "BE_TC_54, Buy ELoad Location Permission Deny Open Settings Button Functionality Validated");
										System.out.println("-----------------------------------------------------------");
									}
								}
							}


							public void buyELoadLocationPopUpAllowFunctionality_BE_TC_55(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Location popup Allow Button Functionality Validation");
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
									locationPopUpAllowFunctionality();
									if(verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage,getTextVal(MLWalletEloadPage.objLoadSelectionPage,"Page"))){
										logger.info("BE_TC_55, Buy ELoad Location popup Allow Button Functionality Validated");
										extentLoggerPass("BE_TC_55", "BE_TC_55, Buy ELoad Location popup Allow Button Functionality Validated");
										System.out.println("-----------------------------------------------------------");
									}
								}
							}

							public void buyELoadContactsPermissionPopup_BE_TC_56(String sTier) throws Exception {
								HeaderChildNode("Buy ELoad Contacts Permission Popup");
								mlWalletLogin(sTier);
								click(MLWalletEloadPage.objEloadTab, "Buy eLoad");
								verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts, getTextVal(MLWalletEloadPage.objSelectFromContacts, "Button"));
								if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
									verifyElementPresent(MLWalletHomePage.objContactPopUpAllowBtn, getTextVal(MLWalletHomePage.objContactPopUpAllowBtn, "Button"));
									verifyElementPresent(MLWalletHomePage.objPopUpDenyBtn, getTextVal(MLWalletHomePage.objPopUpDenyBtn, "Button"));
									logger.info("BE_TC_56, Buy ELoad Contacts Permission Popup Validated");
									extentLoggerPass("BE_TC_56", "BE_TC_56, Buy ELoad Location popup Allow Button Functionality Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadContactPermissionPopupAllowBtnFunctionality_BE_TC_57() throws Exception {
								HeaderChildNode("Buy ELoad Contacts Permission Popup Allow Button Functionality");
								buyELoadContactsPermissionPopup_BE_TC_56(prop.getproperty("Fully_Verified"));
								click(MLWalletHomePage.objContactPopUpAllowBtn, getTextVal(MLWalletHomePage.objContactPopUpAllowBtn, "Button"));
								if (verifyElementPresent(MLWalletEloadPage.objContacts, getTextVal(MLWalletEloadPage.objContacts, "Page"))) {
									for (int i = 1; i <= 4; i++) {
										for (int j = 1; j <= 2; j++) {
											if (j == 1) {
												String sContactName = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
												logger.info("Contact Name : " + sContactName + " is displayed");
												extentLogger(" ", "Contact Name : " + sContactName + " is displayed");
											}
											if (j == 2) {
												String sContactNumber = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
												logger.info("Contact Number : " + sContactNumber + " is displayed");
												extentLogger(" ", "Contact Number : " + sContactNumber + " is displayed");
											}
										}
									}
									logger.info("BE_TC_57, Buy ELoad Contacts Permission Popup Allow Button Functionality Validated");
									extentLoggerPass("BE_TC_57", "BE_TC_57, Buy ELoad Contacts Permission Popup Allow Button Functionality Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_58() throws Exception {
								HeaderChildNode("Buy ELoad Contacts Permission Popup Deny Button Functionality");
								buyELoadContactsPermissionPopup_BE_TC_56(prop.getproperty("Fully_Verified"));
								click(MLWalletHomePage.objPopUpDenyBtn, getTextVal(MLWalletHomePage.objPopUpDenyBtn, "Button"));
								verifyElementPresentAndClick(MLWalletEloadPage.objContactsPageBackBtn,"Contact Page Back Arrow Button");
								verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
								if(verifyElementPresent(MLWalletEloadPage.objContactPopupMsg,getTextVal(MLWalletEloadPage.objContactPopupMsg,"Popup"))){
									verifyElementPresent(MLWalletEloadPage.objAllowAccess,getTextVal(MLWalletEloadPage.objAllowAccess,"Button"));
									verifyElementPresent(MLWalletEloadPage.objAskMeLater,getTextVal(MLWalletEloadPage.objAskMeLater,"Button"));
									logger.info("BE_TC_58, Buy ELoad Contacts Permission Popup Deny Button Functionality Validated");
									extentLoggerPass("BE_TC_58", "BE_TC_58, Buy ELoad Contacts Permission Popup Deny Button Functionality Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadContactPermissionTwoDenyBtnFunctionality_BE_TC_59() throws Exception {
								HeaderChildNode("Buy ELoad Contact PermissionTwo Deny Button Functionality");
								buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_58();
								click(MLWalletEloadPage.objAllowAccess,getTextVal(MLWalletEloadPage.objAllowAccess,"Button"));
								verifyElementPresentAndClick(MLWalletHomePage.objContactPopupDenyBtn, getTextVal(MLWalletHomePage.objContactPopupDenyBtn, "Button"));
								if(verifyElementPresent(MLWalletEloadPage.objNoContactsFoundMsg,getTextVal(MLWalletEloadPage.objNoContactsFoundMsg,"Message"))){
									logger.info("BE_TC_59, Buy ELoad Contact PermissionTwo Deny Button Functionality validated");
									extentLoggerPass("BE_TC_59", "BE_TC_59, Buy ELoad Contact PermissionTwo Deny Button Functionality Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadContactPermissionTwoAllowBtnFunctionality_BE_TC_60() throws Exception {
								HeaderChildNode("Buy ELoad Contact PermissionTwo Allow Button Functionality");
								buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_58();
								click(MLWalletEloadPage.objAllowAccess, getTextVal(MLWalletEloadPage.objAllowAccess, "Button"));
								verifyElementPresentAndClick(MLWalletHomePage.objContactPopUpAllowBtn, getTextVal(MLWalletHomePage.objContactPopUpAllowBtn, "Button"));
								if (verifyElementPresent(MLWalletEloadPage.objContacts, getTextVal(MLWalletEloadPage.objContacts, "Page"))) {
									for (int i = 1; i <= 4; i++) {
										for (int j = 1; j <= 2; j++) {
											if (j == 1) {
												String sContactName = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
												logger.info("Contact Name : " + sContactName + " is displayed");
												extentLogger(" ", "Contact Name : " + sContactName + " is displayed");
											}
											if (j == 2) {
												String sContactNumber = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
												logger.info("Contact Number : " + sContactNumber + " is displayed");
												extentLogger(" ", "Contact Number : " + sContactNumber + " is displayed");
											}
										}
									}
									logger.info("BE_TC_60, Buy ELoad Contact PermissionTwo Allow Button Functionality Validated");
									extentLoggerPass("BE_TC_60", "BE_TC_60, Buy ELoad Contact PermissionTwo Allow Button Functionality Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void buyELoadInternetInterruptionWhileEnteringOTP_BE_TC_61(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Internet Interruption While Entering OTP Validation");
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								enableLocation_PopUp();
								verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
								click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
								waitTime(5000);
								click(MLWalletEloadPage.objTransaction, getTextVal(MLWalletEloadPage.objTransaction, "Promo"));
								verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
								waitTime(5000);
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								explicitWaitVisible(MLWalletLoginPage.objOneTimePin, 20);
								setWifiConnectionToONOFF("OFF");
								enterOTP(prop.getproperty("Valid_OTP"));
								if(verifyElementPresent(MLWalletHomePage.objInternetConnectionPopUp, getTextVal(MLWalletHomePage.objInternetConnectionPopUp, "PopUp"))){
									internetConnectionError();
									logger.info("BE_TC_61, Buy ELoad Internet Interruption While Entering OTP Validated");
									extentLoggerPass("BE_TC_61", "BE_TC_61, Buy ELoad Internet Interruption While Entering OTP Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadLocationPermissionAskMeLaterButtonFunctionality_BE_TC_62() throws Exception {
								HeaderChildNode("Buy ELoad Location Permission Ask Me Later Button Functionality");
								buyELoadContactPermissionPopupDenyBtnFunctionality_BE_TC_58();
								verifyElementPresentAndClick(MLWalletEloadPage.objAskMeLater,getTextVal(MLWalletEloadPage.objAskMeLater,"Button"));
								if (verifyElementPresent(MLWalletHomePage.objPopUpMsg, getTextVal(MLWalletHomePage.objPopUpMsg, "Popup Msg"))) {
									verifyElementPresent(MLWalletHomePage.objContactPopUpAllowBtn, getTextVal(MLWalletHomePage.objContactPopUpAllowBtn, "Button"));
									verifyElementPresent(MLWalletHomePage.objContactPopupDenyBtn, getTextVal(MLWalletHomePage.objContactPopupDenyBtn, "Button"));
									logger.info("BE_TC_62, Buy ELoad Location Permission Ask Me Later Button Functionality Validated");
									extentLoggerPass("BE_TC_62", "BE_TC_62, Buy ELoad Location Permission Ask Me Later Button Functionality Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadLocationPermissionTwoDenyBtnFunctionality_BE_TC_63() throws Exception {
								HeaderChildNode("Buy ELoad Location PermissionTwo Deny Button Functionality");
								buyELoadLocationPermissionAskMeLaterButtonFunctionality_BE_TC_62();
								click(MLWalletHomePage.objContactPopupDenyBtn, getTextVal(MLWalletHomePage.objContactPopupDenyBtn, "Button"));
								if(verifyElementPresent(MLWalletEloadPage.objNoContactsFoundMsg,getTextVal(MLWalletEloadPage.objNoContactsFoundMsg,"Message"))){
									logger.info("BE_TC_63, Buy ELoad Location PermissionTwo Deny Button Functionality validated");
									extentLoggerPass("BE_TC_63", "BE_TC_63, Buy ELoad Location PermissionTwo Deny Button Functionality Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadLocationPermissionTwoAllowBtnFunctionality_BE_TC_64() throws Exception {
								HeaderChildNode("Buy ELoad Location PermissionTwo Allow Button Functionality");
								buyELoadLocationPermissionAskMeLaterButtonFunctionality_BE_TC_62();
								click(MLWalletHomePage.objContactPopUpAllowBtn, getTextVal(MLWalletHomePage.objContactPopUpAllowBtn, "Button"));
								if (verifyElementPresent(MLWalletEloadPage.objContacts, getTextVal(MLWalletEloadPage.objContacts, "Page"))) {
									for (int i = 1; i <= 4; i++) {
										for (int j = 1; j <= 2; j++) {
											if (j == 1) {
												String sContactName = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
												logger.info("Contact Name : " + sContactName + " is displayed");
												extentLogger(" ", "Contact Name : " + sContactName + " is displayed");
											}
											if (j == 2) {
												String sContactNumber = getText(MLWalletEloadPage.objContactsAndNumber(i, j));
												logger.info("Contact Number : " + sContactNumber + " is displayed");
												extentLogger(" ", "Contact Number : " + sContactNumber + " is displayed");
											}
										}
									}
									logger.info("BE_TC_64, Buy ELoad Location PermissionTwo Allow Button Functionality Validated");
									extentLoggerPass("BE_TC_64", "BE_TC_64, Buy ELoad Location PermissionTwo Allow Button Functionality Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadContactPopupNotDisplayedAfterClickingODenyButtonValidation_BE_TC_65() throws Exception {
								HeaderChildNode("Buy ELoad Contact Popup Not displayed After Clicking On Deny Button Validation");
								buyELoadLocationPermissionAskMeLaterButtonFunctionality_BE_TC_62();
								click(MLWalletHomePage.objContactPopupDenyBtn, getTextVal(MLWalletHomePage.objContactPopupDenyBtn, "Button"));
								verifyElementPresentAndClick(MLWalletEloadPage.objContactsPageBackBtn,"Contacts Page Back Btn");
								verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage,getTextVal(MLWalletEloadPage.objEloadtransactionPage,"Page"));
								verifyElementPresentAndClick(MLWalletEloadPage.objSelectFromContacts,getTextVal(MLWalletEloadPage.objSelectFromContacts,"Button"));
								if(verifyElementNotPresent(MLWalletEloadPage.objContactPopupMsg,"Contacts Popup",5)){
									logger.info("BE_TC_65, Buy ELoad Contact Popup Not displayed After Clicking On Deny Button is Validated");
									extentLoggerPass("BE_TC_65", "BE_TC_65, Buy ELoad Contact Popup Not displayed After Clicking On Deny Button is Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadNewTransactionBtnFunctionality_BE_TC_66() throws Exception {
								HeaderChildNode("Buy ELoad New Transaction Button Functionality");
								buyELoadTransactionDetails_BE_TC_01(prop.getproperty("Fully_Verified"),3);
								verifyElementPresentAndClick(MLWalletCashOutPage.objNewTransaction, getTextVal(MLWalletCashOutPage.objNewTransaction, "Button"));
								if(verifyElementPresent(MLWalletEloadPage.objEloadtransactionPage,getTextVal(MLWalletEloadPage.objEloadtransactionPage,"Page"))){
									logger.info("BE_TC_66, Buy ELoad New Transaction Button Functionality Validated");
									extentLoggerPass("BE_TC_66", "BE_TC_66, Buy ELoad New Transaction Button Functionality Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void buyELoadTransactionValidationAfterMinimizingApp_BE_TC_069(String sTier, int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Transaction Validation After Minimizing App");
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								enableLocation_PopUp();
								verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
								click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
								waitTime(5000);
								click(MLWalletEloadPage.objTransaction, getTextVal(MLWalletEloadPage.objTransaction, "Promo"));
								verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
								waitTime(5000);
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								enterOTP(prop.getproperty("Valid_OTP"));
								DriverManager.getAppiumDriver().runAppInBackground(Duration.ofSeconds(5));
								logger.info("Application relaunched after 5 Seconds");
								if(verifyElementPresent(MLWalletEloadPage.objTransactionDetailsPage,getTextVal(MLWalletEloadPage.objTransactionDetailsPage,"Page"))){
									logger.info("BE_TC_069, Buy ELoad Transaction Validation After Minimizing App Validated");
									extentLoggerPass("BE_TC_069", "BE_TC_069, Buy ELoad Transaction Validation After Minimizing App Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}  */

							
							public void buyELoadTransactionWithValidMLPin_BE_TC_73(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Transaction With Valid MLPin");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(sTier,prop.getproperty("sunMobileNumber"), "true", promotab);
								enableLocation_PopUp();
								verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
								click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
								waitTime(5000);
								click(MLWalletEloadPage.objTransaction, getTextVal(MLWalletEloadPage.objTransaction, "Promo"));
								verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
								waitTime(5000);
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								verifyElementPresent(MLWalletEloadPage.objBuyLoad,getTextVal(MLWalletEloadPage.objBuyLoad,"Page"));
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								handleMpin("1111", "Entered Mpin");
								waitTime(30000);
								if(verifyElementPresent(MLWalletEloadPage.objTransactionDetailsPage,getTextVal(MLWalletEloadPage.objTransactionDetailsPage,"Page"))){
									verifyElementPresent(MLWalletEloadPage.objMobileNumberInTransactionDetails,getTextVal(MLWalletEloadPage.objMobileNumberInTransactionDetails,"Mobile Number"));
									verifyElementPresent(MLWalletEloadPage.objBuyELoadTime,getTextVal(MLWalletEloadPage.objBuyELoadTime,"Date and Time"));
									verifyElementPresent(MLWalletEloadPage.objTypeOfPromoUsed, getTextVal(MLWalletEloadPage.objTypeOfPromoUsed, "Promo"));
									verifyElementPresent(MLWalletEloadPage.objAmountToSend, getTextVal(MLWalletEloadPage.objAmountToSend, "Amount to Send"));
									verifyElementPresent(MLWalletEloadPage.objServiceFee, getTextVal(MLWalletEloadPage.objServiceFee, "Service Fee"));
									verifyElementPresent(MLWalletEloadPage.objTotalAmount, getTextVal(MLWalletEloadPage.objTotalAmount, "Total Amount"));
									logger.info("BE_TC_78, Buy ELoad Transaction With Valid MLPin is Validated");
									extentLoggerPass("BE_TC_78", "BE_TC_78, Buy ELoad Transaction With Valid MLPin is Validated");
									System.out.println("-----------------------------------------------------------");
								}
								getDriver().resetApp();
							}


							public void buyELoadTransactionWithInValidMLPin_BE_TC_74(String sTier,int promotab) throws Exception {
								HeaderChildNode("Buy ELoad Transaction With InValid MLPin");
								waitTime(2000);
								changeNumberPage();
								clearField();
								eLoad_generic(sTier, prop.getproperty("sunMobileNumber"), "true", promotab);
								enableLocation_PopUp();
								verifyElementPresent(MLWalletEloadPage.objLoadSelectionPage, "Load Selection Page");
								click(MLWalletEloadPage.objPromoLoadTab, "Promo Load Tab");
								waitTime(5000);
								click(MLWalletEloadPage.objTransaction, getTextVal(MLWalletEloadPage.objTransaction, "Promo"));
								verifyElementPresent(MLWalletEloadPage.objContinuePromoPopUp, getTextVal(MLWalletEloadPage.objContinuePromoPopUp, "Pop Up"));
								waitTime(5000);
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								verifyElementPresent(MLWalletEloadPage.objBuyLoad, getTextVal(MLWalletEloadPage.objBuyLoad, "Page"));
								click(MLWalletEloadPage.objConfirmBtn, "Confirm Button");
								handleMpin("1234", "Entered Mpin");
								waitTime(3000);
								if (verifyElementPresent(MLWalletEloadPage.objInvalidPINMsg, getTextVal(MLWalletEloadPage.objInvalidPINMsg, "Message"))) {
									String sActualErrorMsg = getText(MLWalletEloadPage.objInvalidPINMsg);
									String sExceptedErrorMsg = "You have entered an invalid PIN. Please try again.";
									assertionValidation(sActualErrorMsg,sExceptedErrorMsg);
									logger.info("BE_TC_79, Buy ELoad Transaction With Invalid ML Pin validated");
									extentLoggerPass("BE_TC_79", "BE_TC_79, Buy ELoad Transaction With Invalid ML Pin validated");
									System.out.println("-----------------------------------------------------------");
								}
								getDriver().resetApp();
							}
							
							//==================================== Registration =====================================================//


						/*	public void registrationPageNavigation() throws Exception {
								verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
								explicitWaitVisibility(MLWalletRegistration.objMobileNumberField, 10);
								type(MLWalletRegistration.objMobileNumberField, prop.getproperty("Registration_MobileNumber"), "Mobile Number Text Field");
								click(MLWalletRegistration.objConfirm, getTextVal(MLWalletRegistration.objConfirm,"Button"));
								click(MLWalletRegistration.objConfirm, getTextVal(MLWalletRegistration.objConfirm,"Button"));
								enterOTP(prop.getproperty("Valid_OTP"));
							}

							public void registrationInputName() throws Exception {
								verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"));
								verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
								type(MLWalletRegistration.objFirstName,prop.getproperty("First_Name"),"First Name Input Field");
								type(MLWalletRegistration.objMiddleName,prop.getproperty("Middle_Name"),"Middle Name Input Field");
								type(MLWalletRegistration.objLastName,prop.getproperty("Last_Name"),"Last Name Input Field");
							}

							public void selectDate() throws Exception {
								verifyElementPresentAndClick(MLWalletRegistration.objBirthDate,"Birth Date Input Field");
								verifyElementPresentAndClick(MLWalletRegistration.objDatePickerYear,getTextVal(MLWalletRegistration.objDatePickerYear,"Year Section"));
								DateFormat dateFormat = new SimpleDateFormat("yyyy");
								Date date = new Date();
								String year= dateFormat.format(date);
								int selectYear = Integer.parseInt(year)-18;
								verticalSwipeByPercentages(0.4,0.8,0.5);
								verticalSwipeByPercentages(0.4,0.8,0.5);
								verifyElementPresentAndClick(MLWalletRegistration.objYearSelector(Integer.toString(selectYear)),"Selected Year");
								verifyElementPresentAndClick(MLWalletRegistration.objOkBtn,getTextVal(MLWalletRegistration.objOkBtn,"Button"));
							}

							public void emailAndPlaceOfBirth() throws Exception {
								type(MLWalletRegistration.objEmailAddress, prop.getproperty("Email"), "Email Address Field");
								type(MLWalletRegistration.objReEnterEmailAddress, prop.getproperty("Email"), "Re-Enter Email Address Field");
								type(MLWalletRegistration.objPlaceOfBirth,prop.getproperty("Valid_PlaceOfBirth"),"Place of Birth Field");
							}

							public void swipeAndConfirm() throws Exception {
								swipeDownUntilElementVisible("Confirm");
								verifyElementPresentAndClick(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
							}

							public void selectNationality() throws Exception {
								verifyElementPresentAndClick(MLWalletRegistration.objNationality,"Nationality Field");
								waitTime(5000);
								type(MLWalletRegistration.objNationalitySearchField,"filipino","Nationality search field");
								verifyElementPresentAndClick(MLWalletRegistration.objFilipino,getTextVal(MLWalletRegistration.objFilipino,"Nationality"));
							}

							public void civilAndGenderSelection() throws Exception {
								verifyElementPresentAndClick(MLWalletRegistration.objCivilStatus,"Civil Status");
								verifyElementPresentAndClick(MLWalletRegistration.objSingleCivilStatus,getTextVal(MLWalletRegistration.objSingleCivilStatus,"Civil Status"));
								verifyElementPresentAndClick(MLWalletRegistration.objGender,"Gender Field");
								verifyElementPresentAndClick(MLWalletRegistration.objMaleGender,getTextVal(MLWalletRegistration.objMaleGender,"Gender"));
							}

							public void registrationAddressPageNavigation() throws Exception {
								registrationPageNavigation();
								registrationInputName();
								selectDate();
								emailAndPlaceOfBirth();
								selectNationality();
								swipeDownUntilElementVisible("Confirm");
								civilAndGenderSelection();
								click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
							}


							public void registrationInvalidMobileNumber_RG_TC_02() throws Exception {
								HeaderChildNode("Registration With Invalid Number");
								verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
								explicitWaitVisibility(MLWalletRegistration.objMobileNumberField, 10);
								type(MLWalletRegistration.objMobileNumberField, prop.getproperty("Invalid_MobileNumber"), "Mobile Number Text Field");
								click(MLWalletRegistration.objConfirm, getTextVal(MLWalletRegistration.objConfirm,"Button"));
								if (verifyElementPresent(MLWalletLoginPage.objInvalidMobNumberTxt, getTextVal(MLWalletLoginPage.objInvalidMobNumberTxt, "Error Message"))) {
									logger.info("RG_TC_02, Mobile number is Invalid Error Message is Displayed");
									extentLoggerPass("RG_TC_02", "RG_TC_02, Mobile number is Invalid Error Message is Displayed");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void registrationFirstNameInputFieldValidation_RG_TC_04() throws Exception {
								HeaderChildNode("Registration First Name Input Field Validation");
								registrationPageNavigation();
								verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"));
								verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
								type(MLWalletRegistration.objFirstName,prop.getproperty("Invalid_First_Name"),"First Name Input Field");
								swipeAndConfirm();
								Swipe("DOWN",2);
								if(verifyElementPresent(MLWalletRegistration.objFirstNameErrorMsg,getTextVal(MLWalletRegistration.objFirstNameErrorMsg,"Error Message"))){
									String sInvalidFirstNameErrorMsg = getText(MLWalletRegistration.objFirstNameErrorMsg);
									String sExceptedFirstNameErrorMsg = "First name must only contain letters and spaces";
									assertionValidation(sInvalidFirstNameErrorMsg,sExceptedFirstNameErrorMsg);
									logger.info("RG_TC_04, Registration First Name Input Field Error message Validated");
									extentLoggerPass("RG_TC_04", "RG_TC_04, Registration First Name Input Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationMiddleNameInputFieldValidation_RG_TC_05() throws Exception {
								HeaderChildNode("Registration Middle Name Input Field Validation");
								registrationPageNavigation();
								verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"));
								verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
								type(MLWalletRegistration.objFirstName,prop.getproperty("First_Name"),"First Name Input Field");
								type(MLWalletRegistration.objMiddleName,prop.getproperty("Invalid_Middle_Name"),"Middle Name Input Field");
								swipeAndConfirm();
								Swipe("DOWN",2);
								if(verifyElementPresent(MLWalletRegistration.objMiddleNameErrorMsg,getTextVal(MLWalletRegistration.objMiddleNameErrorMsg,"Error Message"))){
									String sInvalidMiddleNameErrorMsg = getText(MLWalletRegistration.objMiddleNameErrorMsg);
									String sExceptedMiddleNameErrorMsg = "Middle name must only contain letters and spaces";
									assertionValidation(sInvalidMiddleNameErrorMsg,sExceptedMiddleNameErrorMsg);
									logger.info("RG_TC_05, Registration Middle Name Input Field Error message Validated");
									extentLoggerPass("RG_TC_05", "RG_TC_05, Registration Middle Name Input Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationLastNameInputFieldValidation_RG_TC_06() throws Exception {
								HeaderChildNode("Registration Last Name Input Field Validation");
								registrationPageNavigation();
								verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"));
								verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
								type(MLWalletRegistration.objFirstName,prop.getproperty("First_Name"),"First Name Input Field");
								type(MLWalletRegistration.objMiddleName,prop.getproperty("Middle_Name"),"Middle Name Input Field");
								type(MLWalletRegistration.objLastName,prop.getproperty("Invalid_Last_Name"),"Last Name Input Field");
								swipeAndConfirm();
								if(verifyElementPresent(MLWalletRegistration.objLastNameErrorMsg,getTextVal(MLWalletRegistration.objLastNameErrorMsg,"Error Message"))){
									String sInvalidLastNameErrorMsg = getText(MLWalletRegistration.objLastNameErrorMsg);
									String sExceptedLastNameErrorMsg = "Last name must only contain letters and spaces";
									assertionValidation(sInvalidLastNameErrorMsg,sExceptedLastNameErrorMsg);
									logger.info("RG_TC_06, Registration Last Name Input Field Error message Validated");
									extentLoggerPass("RG_TC_06", "RG_TC_06, Registration Last Name Input Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void registrationInvalidBirthDate_RG_TC_07() throws Exception {
								HeaderChildNode("Registration Invalid Birth Date");
								registrationPageNavigation();
								registrationInputName();
								verifyElementPresentAndClick(MLWalletRegistration.objBirthDate,"Birth Date Input Field");
								verifyElementPresentAndClick(MLWalletRegistration.objOkBtn,getTextVal(MLWalletRegistration.objOkBtn,"Button"));
								swipeAndConfirm();
								if(verifyElementPresent(MLWalletRegistration.objBirthDateErrorMsg,getTextVal(MLWalletRegistration.objBirthDateErrorMsg,"Error Message"))){
									String sInvalidBirthDateNameErrorMsg = getText(MLWalletRegistration.objBirthDateErrorMsg);
									String sExceptedBirthDateNameErrorMsg = "You must be 18 years old and above to register";
									assertionValidation(sInvalidBirthDateNameErrorMsg,sExceptedBirthDateNameErrorMsg);
									logger.info("RG_TC_07, Registration Invalid Birth Date Error message Validated");
									extentLoggerPass("RG_TC_07", "RG_TC_07, Registration Invalid Birth Date Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationInvalidEmailAddress_RG_TC_08() throws Exception {
								HeaderChildNode("Registration Invalid Email Address");
								registrationPageNavigation();
								registrationInputName();
								selectDate();
								type(MLWalletRegistration.objEmailAddress,prop.getproperty("Invalid_Email_Address"),"Email Address Field");
								swipeAndConfirm();
								if(verifyElementPresent(MLWalletRegistration.objEmailAddressErrorMsg,getTextVal(MLWalletRegistration.objEmailAddressErrorMsg,"Error Message"))){
									String sInvalidEmailAddressNameErrorMsg = getText(MLWalletRegistration.objEmailAddressErrorMsg);
									String sExceptedEmailAddressNameErrorMsg = "Email address is invalid";
									assertionValidation(sInvalidEmailAddressNameErrorMsg,sExceptedEmailAddressNameErrorMsg);
									logger.info("RG_TC_08, Registration Invalid Email Address Error message Validated");
									extentLoggerPass("RG_TC_08", "RG_TC_08, Registration Invalid Email Address Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void registrationInvalidReEnterEmailAddress_RG_TC_09() throws Exception {
								HeaderChildNode("Registration Invalid Re-Enter Email Address");
								registrationPageNavigation();
								registrationInputName();
								selectDate();
								type(MLWalletRegistration.objEmailAddress, prop.getproperty("Email"), "Email Address Field");
								type(MLWalletRegistration.objReEnterEmailAddress, prop.getproperty("eMailAddress"), "Re-Enter Email Address Field");
								swipeAndConfirm();
								if(verifyElementPresent(MLWalletRegistration.objReEnterEmailAddressErrorMsg,getTextVal(MLWalletRegistration.objReEnterEmailAddressErrorMsg,"Error Message"))){
									String sInvalidEmailAddressNameErrorMsg = getText(MLWalletRegistration.objReEnterEmailAddressErrorMsg);
									String sExceptedEmailAddressNameErrorMsg = "Email address did not match.";
									assertionValidation(sInvalidEmailAddressNameErrorMsg,sExceptedEmailAddressNameErrorMsg);
									logger.info("RG_TC_09, Registration Invalid Re-Enter Email Address Error message Validated");
									extentLoggerPass("RG_TC_09", "RG_TC_09, Registration Invalid Re-Enter Email Address Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationInvalidPlaceOfBirthValidation_RG_TC_10() throws Exception {
								HeaderChildNode("Registration Invalid Place of Birth");
								registrationPageNavigation();
								registrationInputName();
								selectDate();
								type(MLWalletRegistration.objEmailAddress, prop.getproperty("Email"), "Email Address Field");
								type(MLWalletRegistration.objReEnterEmailAddress, prop.getproperty("Email"), "Re-Enter Email Address Field");
								type(MLWalletRegistration.objPlaceOfBirth,prop.getproperty("Invalid_PlaceOfBirth"),"Place of Birth Field");
								swipeAndConfirm();
								if(verifyElementPresent(MLWalletRegistration.objPlaceOfBirthErrorMsg,getTextVal(MLWalletRegistration.objPlaceOfBirthErrorMsg,"Error Message"))){
									String sInvalidPlaceOfBirthErrorMsg = getText(MLWalletRegistration.objPlaceOfBirthErrorMsg);
									String sExceptedPlaceOfBirthErrorMsg = "Place of Birth must only contain letters and spaces";
									assertionValidation(sInvalidPlaceOfBirthErrorMsg,sExceptedPlaceOfBirthErrorMsg);
									logger.info("RG_TC_10, Registration Invalid Place of Birth Error message Validated");
									extentLoggerPass("RG_TC_10", "RG_TC_10, Registration Invalid Place of Birth Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationEmptyNationalityFieldValidation_RG_TC_11() throws Exception {
								HeaderChildNode("Registration Empty Nationality Field Validation");
								registrationPageNavigation();
								registrationInputName();
								selectDate();
								emailAndPlaceOfBirth();
								swipeAndConfirm();
								if(verifyElementPresent(MLWalletRegistration.objNationalityErrorMsg,getTextVal(MLWalletRegistration.objNationalityErrorMsg,"Error Message"))){
									String sInvalidNationalityErrorMsg = getText(MLWalletRegistration.objNationalityErrorMsg);
									String sExceptedNationalityErrorMsg = "Nationality is required";
									assertionValidation(sInvalidNationalityErrorMsg,sExceptedNationalityErrorMsg);
									logger.info("RG_TC_11, Registration Invalid Empty Nationality Field Error message Validated");
									extentLoggerPass("RG_TC_11", "RG_TC_11, Registration Empty Nationality Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationEmptyCivilStatusFieldValidation_RG_TC_12() throws Exception {
								HeaderChildNode("Registration Empty Civil status Field Validation");
								registrationPageNavigation();
								registrationInputName();
								selectDate();
								emailAndPlaceOfBirth();
								selectNationality();
								swipeAndConfirm();
								if(verifyElementPresent(MLWalletRegistration.objCivilStatusErrorMsg,getTextVal(MLWalletRegistration.objCivilStatusErrorMsg,"Error Message"))){
									String sInvalidCivilStatusErrorMsg = getText(MLWalletRegistration.objCivilStatusErrorMsg);
									String sExceptedCivilStatusErrorMsg = "Civil Status is required";
									assertionValidation(sInvalidCivilStatusErrorMsg,sExceptedCivilStatusErrorMsg);
									logger.info("RG_TC_12, Registration Empty Civil status Field Error message Validated");
									extentLoggerPass("RG_TC_12", "RG_TC_12, Registration Empty Civil status Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void registrationEmptyGenderFieldValidation_RG_TC_13() throws Exception {
								HeaderChildNode("Registration Empty Gender Field Validation");
								registrationPageNavigation();
								registrationInputName();
								selectDate();
								emailAndPlaceOfBirth();
								selectNationality();
								swipeDownUntilElementVisible("Confirm");
								verifyElementPresentAndClick(MLWalletRegistration.objCivilStatus,"Civil Status");
								verifyElementPresentAndClick(MLWalletRegistration.objSingleCivilStatus,getTextVal(MLWalletRegistration.objSingleCivilStatus,"Civil Status"));
								swipeAndConfirm();
								click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
								if(verifyElementPresent(MLWalletRegistration.objGenderErrorMsg,getTextVal(MLWalletRegistration.objGenderErrorMsg,"Error Message"))){
									String sActualGenderErrorMsg = getText(MLWalletRegistration.objGenderErrorMsg);
									String sExceptedGenderErrorMsg = "Gender is required";
									assertionValidation(sActualGenderErrorMsg,sExceptedGenderErrorMsg);
									logger.info("RG_TC_13, Registration Empty Gender Field Error message Validated");
									extentLoggerPass("RG_TC_13", "RG_TC_13, Registration Empty Gender Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationEmptyProvinceFieldValidation_RG_TC_14() throws Exception {
								HeaderChildNode("Registration Empty Province Field Validation");
								registrationAddressPageNavigation();
								verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"));
								click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
								if(verifyElementPresent(MLWalletRegistration.ObjProvinceErrorMsg,getTextVal(MLWalletRegistration.ObjProvinceErrorMsg,"Error Message"))){
									String sInvalidProvinceNameErrorMsg = getText(MLWalletRegistration.ObjProvinceErrorMsg);
									String sExceptedProvinceNameErrorMsg = "Province / State is required";
									assertionValidation(sInvalidProvinceNameErrorMsg,sExceptedProvinceNameErrorMsg);
									logger.info("RG_TC_14, Registration Empty Province Field Error message Validated");
									extentLoggerPass("RG_TC_14", "RG_TC_14, Registration Empty Province Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void registrationEmptyCityFieldValidation_RG_TC_15() throws Exception {
								HeaderChildNode("Registration Empty City Field Validation");
								registrationAddressPageNavigation();
								verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"));
								verifyElementPresentAndClick(MLWalletRegistration.objProvince,"Province Field");
								type(MLWalletRegistration.objProvinceSearchField,"Aurora","Province Search Field");
								verifyElementPresentAndClick(MLWalletRegistration.objAuroraProvince,getTextVal(MLWalletRegistration.objAuroraProvince,"Province"));
								click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
								if(verifyElementPresent(MLWalletRegistration.objCityErrorMsg,getTextVal(MLWalletRegistration.objCityErrorMsg,"Error Message"))){
									String sInvalidCityNameErrorMsg = getText(MLWalletRegistration.objCityErrorMsg);
									String sExceptedCityNameErrorMsg = "City / Town is required";
									assertionValidation(sInvalidCityNameErrorMsg,sExceptedCityNameErrorMsg);
									logger.info("RG_TC_15, Registration Empty City Field Error message Validated");
									extentLoggerPass("RG_TC_15", "RG_TC_15, Registration Empty City Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationEmptyHouseNoFieldValidation_RG_TC_16() throws Exception {
								HeaderChildNode("Registration Empty House No Field Validation");
								registrationAddressPageNavigation();
								explicitWaitVisible(MLWalletRegistration.objRegistrationAddress,5);
								verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"));
								verifyElementPresentAndClick(MLWalletRegistration.objProvince,"Province Field");
								type(MLWalletRegistration.objProvinceSearchField,"Aurora","Province Search Field");
								verifyElementPresentAndClick(MLWalletRegistration.objAuroraProvince,getTextVal(MLWalletRegistration.objAuroraProvince,"Province"));
								verifyElementPresentAndClick(MLWalletRegistration.objCity,"City Field");
								verifyElementPresentAndClick(MLWalletRegistration.objMariaAurora,getTextVal(MLWalletRegistration.objMariaAurora,"City"));
								click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
								if(verifyElementPresent(MLWalletRegistration.objHouseNoErrorMsg,getTextVal(MLWalletRegistration.objHouseNoErrorMsg,"Error Message"))){
									String sInvalidHouseNoNameErrorMsg = getText(MLWalletRegistration.objHouseNoErrorMsg);
									String sExceptedHouseNoNameErrorMsg = "Unit, House No., St. is required";
									assertionValidation(sInvalidHouseNoNameErrorMsg,sExceptedHouseNoNameErrorMsg);
									logger.info("RG_TC_16, Registration Empty House No Field Error message Validated");
									extentLoggerPass("RG_TC_16", "RG_TC_16, Registration Empty House No Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationEmptyFirstNameFieldValidation_RG_TC_18() throws Exception {
								HeaderChildNode("Registration Empty First Name Field Validation");
								registrationPageNavigation();
								swipeAndConfirm();
								Swipe("DOWN",2);
								if (verifyElementPresent(MLWalletRegistration.objFirstNameErrorMsg, getTextVal(MLWalletRegistration.objFirstNameErrorMsg, "Error Message"))) {
									String sFirstNameErrorMsg = getText(MLWalletRegistration.objFirstNameErrorMsg);
									String sExpectedMsg = "First name is required";
									assertionValidation(sFirstNameErrorMsg, sExpectedMsg);
									logger.info("RG_TC_18, Registration Empty First Name Field Error message Validated");
									extentLoggerPass("RG_TC_18", "RG_TC_18, Registration Empty First Name Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void registrationEmptyMiddleNameFieldValidation_RG_TC_19() throws Exception {
								HeaderChildNode("Registration Empty Middle Name Input Validation");
								registrationPageNavigation();
								verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"));
								verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
								type(MLWalletRegistration.objFirstName,prop.getproperty("First_Name"),"First Name Input Field");
								swipeAndConfirm();
								Swipe("DOWN",2);
								if(verifyElementPresent(MLWalletRegistration.objMiddleNameErrorMsg,getTextVal(MLWalletRegistration.objMiddleNameErrorMsg,"Error Message"))){
									String sInvalidMiddleNameErrorMsg = getText(MLWalletRegistration.objMiddleNameErrorMsg);
									String sExceptedMiddleNameErrorMsg = "Middle name should be at least 2 characters long";
									assertionValidation(sInvalidMiddleNameErrorMsg,sExceptedMiddleNameErrorMsg);
									logger.info("RG_TC_19, Registration Empty Middle Name Field Error message Validated");
									extentLoggerPass("RG_TC_19", "RG_TC_19, Registration Empty Middle Name Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void registrationEmptyLastNameFieldValidation_RG_TC_20() throws Exception {
								HeaderChildNode("Registration Empty Last Name Field Validation");
								registrationPageNavigation();
								verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"));
								verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
								type(MLWalletRegistration.objFirstName,prop.getproperty("First_Name"),"First Name Input Field");
								type(MLWalletRegistration.objMiddleName,prop.getproperty("Middle_Name"),"Middle Name Input Field");
								swipeAndConfirm();
								if(verifyElementPresent(MLWalletRegistration.objLastNameErrorMsg,getTextVal(MLWalletRegistration.objLastNameErrorMsg,"Error Message"))){
									String sInvalidLastNameErrorMsg = getText(MLWalletRegistration.objLastNameErrorMsg);
									String sExceptedLastNameErrorMsg = "Last name is required";
									assertionValidation(sInvalidLastNameErrorMsg,sExceptedLastNameErrorMsg);
									logger.info("RG_TC_20, Registration Empty Last Name Field Error message Validated");
									extentLoggerPass("RG_TC_20", "RG_TC_06, Registration Empty Last Name Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationEmptyEmailAddress_RG_TC_22() throws Exception {
								HeaderChildNode("Registration Empty Email Address Field Validation");
								registrationPageNavigation();
								registrationInputName();
								selectDate();
								swipeAndConfirm();
								if(verifyElementPresent(MLWalletRegistration.objEmailAddressErrorMsg,getTextVal(MLWalletRegistration.objEmailAddressErrorMsg,"Error Message"))){
									String sInvalidEmailAddressNameErrorMsg = getText(MLWalletRegistration.objEmailAddressErrorMsg);
									String sExceptedEmailAddressNameErrorMsg = "Email address is required";
									assertionValidation(sInvalidEmailAddressNameErrorMsg,sExceptedEmailAddressNameErrorMsg);
									logger.info("RG_TC_22, Registration Empty Email Address Field Error message Validated");
									extentLoggerPass("RG_TC_22", "RG_TC_22, Registration Empty Email Address Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationEmptyPlaceOfBirthValidation_RG_TC_23() throws Exception {
								HeaderChildNode("Registration Empty Place of Birth Field Validation");
								registrationPageNavigation();
								registrationInputName();
								selectDate();
								type(MLWalletRegistration.objEmailAddress, prop.getproperty("Email"), "Email Address Field");
								type(MLWalletRegistration.objReEnterEmailAddress, prop.getproperty("Email"), "Re-Enter Email Address Field");
								swipeAndConfirm();
								if(verifyElementPresent(MLWalletRegistration.objPlaceOfBirthErrorMsg,getTextVal(MLWalletRegistration.objPlaceOfBirthErrorMsg,"Error Message"))){
									String sInvalidPlaceOfBirthNameErrorMsg = getText(MLWalletRegistration.objPlaceOfBirthErrorMsg);
									String sExceptedPlaceOfBirthNameErrorMsg = "Place of Birth is required";
									assertionValidation(sInvalidPlaceOfBirthNameErrorMsg,sExceptedPlaceOfBirthNameErrorMsg);
									logger.info("RG_TC_23, Registration Empty Place of Birth Field Error message Validated");
									extentLoggerPass("RG_TC_23", "RG_TC_23, Registration Empty Place of Birth Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationInvalidHouseNoFieldValidation_RG_TC_24() throws Exception {
								HeaderChildNode("Registration Invalid House No Field Validation");
								registrationAddressPageNavigation();
								explicitWaitVisible(MLWalletRegistration.objRegistrationAddress,5);
								verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"));
								verifyElementPresentAndClick(MLWalletRegistration.objProvince,"Province Field");
								type(MLWalletRegistration.objProvinceSearchField,"Aurora","Province Search Field");
								verifyElementPresentAndClick(MLWalletRegistration.objAuroraProvince,getTextVal(MLWalletRegistration.objAuroraProvince,"Province"));
								verifyElementPresentAndClick(MLWalletRegistration.objCity,"City Field");
								verifyElementPresentAndClick(MLWalletRegistration.objMariaAurora,getTextVal(MLWalletRegistration.objMariaAurora,"City"));
								type(MLWalletRegistration.objHouseNo,prop.getproperty("Invalid_HouseNo"),"House No Field");
								click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
								if(verifyElementPresent(MLWalletRegistration.objHouseNoErrorMsg,getTextVal(MLWalletRegistration.objHouseNoErrorMsg,"Error Message"))){
									String sInvalidEmailAddressNameErrorMsg = getText(MLWalletRegistration.objHouseNoErrorMsg);
									String sExceptedEmailAddressNameErrorMsg = "Address must contain letters and numbers only";
									assertionValidation(sInvalidEmailAddressNameErrorMsg,sExceptedEmailAddressNameErrorMsg);
									logger.info("RG_TC_24, Registration Invalid House No Field Error message Validated");
									extentLoggerPass("RG_TC_24", "RG_TC_24, Registration Invalid House No Field Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}
							
							public void registrationCreatingAccountWithExistingMLWalletAccount_RG_TC_25() throws Exception {
								HeaderChildNode("Registration Creating Account With Existing ML Wallet Account");
								verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
								explicitWaitVisibility(MLWalletRegistration.objMobileNumberField, 10);
								type(MLWalletRegistration.objMobileNumberField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
								click(MLWalletRegistration.objConfirm, getTextVal(MLWalletRegistration.objConfirm,"Button"));
								enterOTP(prop.getproperty("Valid_OTP"));
								if(verifyElementPresent(MLWalletRegistration.objExistingAccErrorMsg,getTextVal(MLWalletRegistration.objExistingAccErrorMsg,"Error message"))) {
									String sActualErrorMsg = getText(MLWalletRegistration.objExistingAccErrorMsg);
									String sExpectedErrorMsg = "As an existing M.Lhuillier Customer, you can directly log-in using this mobile number.";
									assertionValidation(sActualErrorMsg,sExpectedErrorMsg);
									verifyElementPresent(MLWalletRegistration.objGOBackToLoginScreenBtn,getTextVal(MLWalletRegistration.objGOBackToLoginScreenBtn,"Button"));
									logger.info("RG_TC_25, Registration Creating Account With Existing ML Wallet Account Error message Validated");
									extentLoggerPass("RG_TC_25", "RG_TC_25, Registration Creating Account With Existing ML Wallet Account Error message Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void registrationNumberPageUIValidation_RG_TC_27() throws Exception {
								HeaderChildNode("Registration Number Page UI Validation");
								verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
								if(verifyElementPresent(MLWalletRegistration.objRegistration,getTextVal(MLWalletRegistration.objRegistration,"page"))){
									verifyElementPresent(MLWalletRegistration.objMobileNumberField,"Mobile Number Input Field");
									verifyElementPresent(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
									logger.info("RG_TC_27, Registration Number Page UI Validated");
									extentLoggerPass("RG_TC_27", "RG_TC_27, Registration Number Page UI Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void registrationOTPPageUIValidation_RG_TC_28() throws Exception {
								HeaderChildNode("Registration OTP Page UI Validation");
								verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
								type(MLWalletRegistration.objMobileNumberField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
								click(MLWalletRegistration.objConfirm, getTextVal(MLWalletRegistration.objConfirm,"Button"));
								if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
									verifyElementPresent(MLWalletLoginPage.objOtpTextField, "OTP text Field");
									verifyElementPresent(MLWalletCashOutPage.objResendCode, getTextVal(MLWalletCashOutPage.objResendCode, "Seconds"));
									logger.info("RG_TC_28, Registration One Time Pin page UI Validated");
									extentLoggerPass("RG_TC_28", "RG_TC_28, Registration One Time Pin page UI Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationPersonalInfoPageUIValidation_RG_TC_30() throws Exception {
								HeaderChildNode("Registration Personal info Page UI Validation");
								registrationPageNavigation();
								waitTime(5000);
								if(verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"))){
									verifyElementPresent(MLWalletRegistration.objPersonalInfo,getTextVal(MLWalletRegistration.objPersonalInfo,"Header"));
									verifyElementPresent(MLWalletRegistration.objFirstName,"First Name Input Field");
									verifyElementPresent(MLWalletRegistration.objMiddleName,"Middle Name Input Field");
									verifyElementPresent(MLWalletRegistration.objLastName,"Last Name Input Field");
									verifyElementPresent(MLWalletRegistration.objBirthDate,"Birth date Input Field");
									verifyElementPresent(MLWalletRegistration.objEmailAddress,"Email Address Input Field");
									verifyElementPresent(MLWalletRegistration.objReEnterEmailAddress,"Re-Email Address Input Field");
									verifyElementPresent(MLWalletRegistration.objPlaceOfBirth,"Place of Birth Input Field");
									Swipe("UP",1);
									verifyElementPresent(MLWalletRegistration.objNationality,"Choose Nationality Field");
									verifyElementPresent(MLWalletRegistration.objCivilStatus,"Choose Civil Status Field");
									verifyElementPresent(MLWalletRegistration.objGender,"Choose Gender Field");
									verifyElementPresent(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
									logger.info("RG_TC_30, Registration Personal info Page UI Validated");
									extentLoggerPass("RG_TC_30", "RG_TC_30, Registration Personal info Page UI Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void registrationNumberPageBackBtnValidation_RG_TC_31() throws Exception {
								HeaderChildNode("Registration Number Page Back Arrow Button Validation");
								verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
								verifyElementPresentAndClick(MLWalletRegistration.objRegistrationBackBtn,"Registration Page Back Arrow Btn");
								if(verifyElementPresent(MLWalletLoginPage.objLoginBtn,getTextVal(MLWalletLoginPage.objLoginBtn,"Button"))){
									logger.info("RG_TC_31, Registration Number Page Back Arrow Button Validated");
									extentLoggerPass("RG_TC_31", "RG_TC_31, Registration Number Page Back Arrow Button Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void registrationOTPPageBackBtnValidation_RG_TC_32() throws Exception {
								HeaderChildNode("Registration OTP Page Back Arrow Button Validation");
								verifyElementPresentAndClick(MLWalletRegistration.objCreateOne,getTextVal(MLWalletRegistration.objCreateOne,"Button"));
								type(MLWalletRegistration.objMobileNumberField, prop.getproperty("Branch_Verified"), "Mobile Number Text Field");
								click(MLWalletRegistration.objConfirm, getTextVal(MLWalletRegistration.objConfirm,"Button"));
								verifyElementPresentAndClick(MLWalletRegistration.objOTPPageBackBtn,"OTP Page Back arrow Btn");
								if(verifyElementPresent(MLWalletRegistration.objRegistration,getTextVal(MLWalletRegistration.objRegistration,"Page"))){
									logger.info("RG_TC_32, Registration OTP Page Back Arrow Button Validated");
									extentLoggerPass("RG_TC_32", "RG_TC_32, Registration OTP Page Back Arrow Button Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void registrationPersonalInfoPageBackBtnValidationRG_TC_33() throws Exception {
								HeaderChildNode("Registration Personal Info Page Back Btn Validation");
								registrationPageNavigation();
								waitTime(5000);
								verifyElementPresentAndClick(MLWalletRegistration.objRegistrationPersonalInfoBackBtn,"OTP Page Back arrow Btn");
								if(verifyElementPresent(MLWalletLoginPage.objOneTimePin,getTextVal(MLWalletLoginPage.objOneTimePin,"Page"))){
									logger.info("RG_TC_33, Registration Personal Info Page Back Btn Validated");
									extentLoggerPass("RG_TC_33", "RG_TC_33, Registration Personal Info Page Back Btn Validated");
									System.out.println("-----------------------------------------------------------");
								}

							}

							public void registrationAddressPageUIValidation_RG_TC_34() throws Exception {
								HeaderChildNode("Registration Address Page UI Validation");
								registrationAddressPageNavigation();
								if(verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"))){
									verifyElementPresent(MLWalletRegistration.objProvince,"Select State Or Province Field");
									verifyElementPresent(MLWalletRegistration.objCity,"Select City or Town");
									verifyElementPresent(MLWalletRegistration.objHouseNo,"Unit, House No., St.");
									verifyElementPresent(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
									logger.info("RG_TC_34, Registration Address Page UI Validated");
									extentLoggerPass("RG_TC_34", "RG_TC_34, Registration Address Page UI Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void registrationAddressPageBackBtnValidation_RG_TC_35() throws Exception {
								HeaderChildNode("Registration Address Page Back btn Validation");
								registrationAddressPageNavigation();
								verifyElementPresent(MLWalletRegistration.objRegistrationAddress, getTextVal(MLWalletRegistration.objRegistrationAddress, "Page"));
								verifyElementPresentAndClick(MLWalletRegistration.objRegistrationAddressBackBtn,"Registration Address Back Btn");
								if(verifyElementPresent(MLWalletRegistration.objRegistrationPersonalInfo,getTextVal(MLWalletRegistration.objRegistrationPersonalInfo,"Page"))){
									logger.info("RG_TC_35, Registration Address Page Back btn Validated");
									extentLoggerPass("RG_TC_35", "RG_TC_35, Registration Address Page Back btn Validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void registrationTermsAndConditionPageBackBtnValidation_RG_TC_37() throws Exception {
								HeaderChildNode("Registration Terms and Condition Page Back Btn Validation");
								registrationAddressPageNavigation();
								explicitWaitVisible(MLWalletRegistration.objRegistrationAddress,5);
								verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"));
								verifyElementPresentAndClick(MLWalletRegistration.objProvince,"Province Field");
								type(MLWalletRegistration.objProvinceSearchField,"Aurora","Province Search Field");
								verifyElementPresentAndClick(MLWalletRegistration.objAuroraProvince,getTextVal(MLWalletRegistration.objAuroraProvince,"Province"));
								verifyElementPresentAndClick(MLWalletRegistration.objCity,"City Field");
								verifyElementPresentAndClick(MLWalletRegistration.objMariaAurora,getTextVal(MLWalletRegistration.objMariaAurora,"City"));
								type(MLWalletRegistration.objHouseNo,prop.getproperty("HouseNo"),"House No Field");
								click(MLWalletRegistration.objConfirm,getTextVal(MLWalletRegistration.objConfirm,"Button"));
								verifyElementPresent(MLWalletRegistration.objTermsAndCondition,getTextVal(MLWalletRegistration.objTermsAndCondition,"Page"));
								verifyElementPresentAndClick(MLWalletRegistration.objTermsAndConditionBackBtn,"Terms And Condition back Btn");
								if(verifyElementPresent(MLWalletRegistration.objRegistrationAddress,getTextVal(MLWalletRegistration.objRegistrationAddress,"Page"))){
									logger.info("RG_TC_37, Registration Terms and Condition Page Back Btn Validated");
									extentLoggerPass("RG_TC_37", "RG_TC_37, Registration Terms and Condition Page Back Btn Validated");
									System.out.println("-----------------------------------------------------------");
								}
							} */
							
							
							//=================================== ML Wallet Shop Items ==========================================================//


					/*		public void shopItemsNavigation() throws Exception {
								click(MLWalletShopItemsPage.objShopItemsTab, "Shop Items Icon");
								Thread.sleep(10000);
								verifyElementPresentAndClick(MLWalletShopItemsPage.objMLShopPage, "ML Shop Page");
								Thread.sleep(10000);
							}
							public void selectItemAndAddToCart() throws Exception {
								Swipe("UP", 2);
								click(MLWalletShopItemsPage.objItemMenu, "Rings Item");
								click(MLWalletShopItemsPage.objSelectItem, getTextVal(MLWalletShopItemsPage.objSelectItem, "Item"));
								Swipe("up", 2);
								click(MLWalletShopItemsPage.objAddToCartBtn, "Add to cart Button");
							}
							public void navigationToCart() throws Exception {
								click(MLWalletShopItemsPage.objHambergerMenu, "Hamburger Menu");
								click(MLWalletShopItemsPage.objCart, "Cart");
							}

							public void editAddressAndPlaceTheOrder() throws Exception {
								click(MLWalletShopItemsPage.objCheckBox, "Check Box");
								click(MLWalletShopItemsPage.objCheckOutBtn, "Checkout Button");
								click(MLWalletShopItemsPage.objEditAddress, "Edit Address Tab");
								verifyElementPresent(MLWalletShopItemsPage.objSelectBranchPage, getTextVal(MLWalletShopItemsPage.objSelectBranchPage, "Page"));
//								click(MLWalletShopItemsPage.objInputFieldOne, "Select Branch Field 1");
//								click(MLWalletShopItemsPage.objBranchName, getTextVal(MLWalletShopItemsPage.objBranchName, "Branch Name"));
//								click(MLWalletShopItemsPage.objInputFieldTwo, "Select Branch Field 2");
//								click(MLWalletShopItemsPage.objSubBranchName, getTextVal(MLWalletShopItemsPage.objSubBranchName, "Branch Name"));
//								click(MLWalletShopItemsPage.objInputFieldThree, "Select Branch Field 3");
//								click(MLWalletShopItemsPage.objSubBranchNameTwo, getTextVal(MLWalletShopItemsPage.objSubBranchNameTwo, "Branch Name"));
								click(MLWalletShopItemsPage.objSaveBtn, "Save Button");
								verifyElementPresent(MLWalletShopItemsPage.objAddressSuccessfulMsg, getTextVal(MLWalletShopItemsPage.objAddressSuccessfulMsg, "Message"));
								click(MLWalletShopItemsPage.objOkBtn, "OK Button");
								Swipe("UP",2);
								verifyElementPresent(MLWalletShopItemsPage.objSelectPaymentMethod,getTextVal(MLWalletShopItemsPage.objSelectPaymentMethod,"Header"));
								verifyElementPresentAndClick(MLWalletShopItemsPage.objMLWallet,getTextVal(MLWalletShopItemsPage.objMLWallet,"Option"));
								Swipe("UP",2);
								click(MLWalletShopItemsPage.objPlaceOrderBtn, "Place Order Button");
							}



							public void mlWallet_ShopItems_Successful_Purchase() throws Exception {
								HeaderChildNode("mlWalletShopItems_Successful_Purchase");
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								editAddressAndPlaceTheOrder();
								verifyElementPresent(MLWalletShopItemsPage.objOtpPage, getTextVal(MLWalletShopItemsPage.objOtpPage, "Pop up"));
								Thread.sleep(2000);
								click(MLWalletShopItemsPage.objOtpTextField, "Otp Text Field");
								handleOtp(prop.getproperty("otp"));
								click(MLWalletShopItemsPage.objValidateBtn, "Validate Button");
								// code for successful purchase message validation
							}

							public void mlWallet_ShopItems_with_Insufficient_Balance() throws Exception {
								HeaderChildNode("mlWallet_ShopItems_with_Insufficient_Balance");
								mlWalletLogin(prop.getproperty("Buyer_Tier"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								editAddressAndPlaceTheOrder();
								verifyElementPresent(MLWalletShopItemsPage.objOtpPage, getTextVal(MLWalletShopItemsPage.objOtpPage, "Pop up"));
								Thread.sleep(2000);
								click(MLWalletShopItemsPage.objOtpTextField, "Otp Text Field");
								handleOtp(prop.getproperty("OTP"));
								click(MLWalletShopItemsPage.objValidateBtn, "Validate Button");
								String oOpsMsg = getText(MLWalletShopItemsPage.objInvalidOtpPopUp);
								String supplyFieldsMsg = getText(MLWalletShopItemsPage.objInvalidOtpPopUpMsg);
								logger.info(oOpsMsg + " " + supplyFieldsMsg + " Pop Up Message is displayed");
								extentLogger("", oOpsMsg + " " + supplyFieldsMsg + " Pop Up Message is displayed");
								logger.info("MLS_TC_02, Oops... Insufficient Balance. - Error message is validated ");
								extentLoggerPass("MLS_TC_02", "MLS_TC_02, Oops... Insufficient Balance. - Error message is validated");
								System.out.println("-----------------------------------------------------------");
							}

							public void mlWallet_ShopItems_with_Incorrect_Otp() throws Exception {
								HeaderChildNode("mlWallet_ShopItems_with_Incorrect_Otp");
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								editAddressAndPlaceTheOrder();
								verifyElementPresent(MLWalletShopItemsPage.objOtpPage, getTextVal(MLWalletShopItemsPage.objOtpPage, "Pop up"));
								Thread.sleep(2000);
								click(MLWalletShopItemsPage.objOtpTextField, "Otp Text Field");
								handleOtp(prop.getproperty("incorrectOtp"));
								click(MLWalletShopItemsPage.objValidateBtn, "Validate Button");
								// Code to be written to validate incorrect otp msg
							}

							public void shopItemsWithoutInputOtp_MLS_TC_04() throws Exception {
								HeaderChildNode("mlWallet_ShopItems_without_Input_Otp");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								editAddressAndPlaceTheOrder();
								verifyElementPresent(MLWalletShopItemsPage.objOtpPage, getTextVal(MLWalletShopItemsPage.objOtpPage, "Pop up"));
								Thread.sleep(2000);
								click(MLWalletShopItemsPage.objValidateBtn, "Validate Button");
								String oOpsMsg = getText(MLWalletShopItemsPage.objInvalidOtpPopUp);
								String supplyFieldsMsg = getText(MLWalletShopItemsPage.objInvalidOtpPopUpMsg);
								logger.info(oOpsMsg + " " + supplyFieldsMsg + " Pop Up Message is displayed");
								extentLogger("", oOpsMsg + " " + supplyFieldsMsg + " Pop Up Message is displayed");
								logger.info("MLS_TC_04, Oops... Please supply all fields. - Error message is validated");
								extentLoggerPass("MLS_TC_04", "MLS_TC_04, Oops... Please supply all fields. - Error message is validated");
								System.out.println("-----------------------------------------------------------");
							}

							public void shopItemsHamburgerMenuNavigation_MLS_TC_12() throws Exception {
								HeaderChildNode("Shop Items Hamburger Menu Navigation");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								verifyElementPresentAndClick(MLWalletShopItemsPage.objHambergerMenu, "Hamburger Menu");
								if(verifyElementPresent(MLWalletShopItemsPage.objSearch,getTextVal(MLWalletShopItemsPage.objSearch,"Button"))){
									verifyElementPresent(MLWalletShopItemsPage.objProfile,getTextVal(MLWalletShopItemsPage.objProfile,"Button"));
									verifyElementPresent(MLWalletShopItemsPage.objCart,getTextVal(MLWalletShopItemsPage.objCart,"Button"));
									verifyElementPresent(MLWalletShopItemsPage.objShop,getTextVal(MLWalletShopItemsPage.objShop,"Button"));
									verifyElementPresent(MLWalletShopItemsPage.objAbout,getTextVal(MLWalletShopItemsPage.objAbout,"Button"));
									verifyElementPresent(MLWalletShopItemsPage.objContact,getTextVal(MLWalletShopItemsPage.objContact,"Button"));
									logger.info("MLS_TC_12, Shop Items Hamburger Menu Navigation validated");
									extentLoggerPass("MLS_TC_12", "MLS_TC_12, Shop Items Hamburger Menu Navigation validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void shopItemsRespectivePageNavigationAfterSelectingAnCategory_MLS_TC_15() throws Exception {
								HeaderChildNode("Shop Items Respective Page Navigation After Selecting An Category");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								Swipe("UP",1);
								click(MLWalletShopItemsPage.objItemMenu, "Rings Item");
								if (verifyElementDisplayed(MLWalletShopItemsPage.objItems)) {
									List<WebElement> values = findElements(MLWalletShopItemsPage.objItems);
									for (int i = 0; i < values.size(); i++) {
										String displayedItem = values.get(i).getText();
										logger.info("Item : " + displayedItem + " is displayed");
										extentLogger(" ", "Item : " + displayedItem + " is displayed");
									}
								}
								verifyElementPresent(MLWalletShopItemsPage.objGenderDropdown,getTextVal(MLWalletShopItemsPage.objGenderDropdown,"Dropdown"));
								verifyElementPresent(MLWalletShopItemsPage.objColorDropdown,getTextVal(MLWalletShopItemsPage.objColorDropdown,"Dropdown"));
								verifyElementPresent(MLWalletShopItemsPage.objKaratDropdown,getTextVal(MLWalletShopItemsPage.objKaratDropdown,"Dropdown"));
								verifyElementPresent(MLWalletShopItemsPage.objPriceDropdown,getTextVal(MLWalletShopItemsPage.objPriceDropdown,"Dropdown"));
								logger.info("MLS_TC_15, Shop Items Respective Page Navigation After Selecting An Category validated");
								extentLoggerPass("MLS_TC_15", "MLS_TC_15, Shop Items Respective Page Navigation After Selecting An Category validated");
								System.out.println("-----------------------------------------------------------");
							}

							public void shopItemsSelectedItemScreenUIValidation_MLS_TC_18() throws Exception {
								HeaderChildNode("Shop Items Selected Item Screen UI Validation");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								Swipe("UP",1);
								click(MLWalletShopItemsPage.objItemMenu, "Rings Item");
								click(MLWalletShopItemsPage.objSelectItem, getTextVal(MLWalletShopItemsPage.objSelectItem, "Item"));
								Swipe("UP",2);
								if(verifyElementPresent(MLWalletShopItemsPage.objProductImage,"Selected Product Image")){
									verifyElementPresent(MLWalletShopItemsPage.objProductPrice,getTextVal(MLWalletShopItemsPage.objProductPrice,"Product Price"));
									verifyElementPresent(MLWalletShopItemsPage.objShippingTo,getTextVal(MLWalletShopItemsPage.objShippingTo,"Shipping To"));
									verifyElementPresent(MLWalletShopItemsPage.objShippingFee,getTextVal(MLWalletShopItemsPage.objShippingFee,"Shipping Fee"));
									verifyElementPresent(MLWalletShopItemsPage.objAddToCartBtn,getTextVal(MLWalletShopItemsPage.objAddToCartBtn,"Button"));
									verifyElementPresent(MLWalletShopItemsPage.objViewShop,getTextVal(MLWalletShopItemsPage.objViewShop,"Button"));
									logger.info("MLS_TC_18, Shop Items Selected Item Screen UI validated");
									extentLoggerPass("MLS_TC_18", "MLS_TC_18, Shop Items Selected Item Screen UI validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void shopItemsCartPageUIValidation_MLS_TC_19() throws Exception {
								HeaderChildNode("Shop Items Cart Page UI Validation");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								if(verifyElementPresent(MLWalletShopItemsPage.objCartPageHeader,getTextVal(MLWalletShopItemsPage.objCartPageHeader,"Header"))){
									verifyElementPresent(MLWalletShopItemsPage.objCheckBox,"Item Check Box");
									verifyElementPresent(MLWalletShopItemsPage.objProductNameInCartPage,getTextVal(MLWalletShopItemsPage.objProductNameInCartPage,"Product Name in Cart Page"));
									verifyElementPresent(MLWalletShopItemsPage.objPriceInCartPage,getTextVal(MLWalletShopItemsPage.objPriceInCartPage,"Price"));
									verifyElementPresent(MLWalletShopItemsPage.objDeleteIcon,"Delete Icon");
									verifyElementPresent(MLWalletShopItemsPage.objCheckOutBtn,getTextVal(MLWalletShopItemsPage.objCheckOutBtn,"Button"));
									logger.info("MLS_TC_19, Shop Items Cart Page UI validated");
									extentLoggerPass("MLS_TC_19", "MLS_TC_19, Shop Items Cart Page UI validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void shopItemsShippingDetailsPageUIValidation_MLS_TC_20() throws Exception {
								HeaderChildNode("Shop Items Shipping Details Page UI Validation");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								click(MLWalletShopItemsPage.objCheckBox, "Check Box");
								click(MLWalletShopItemsPage.objCheckOutBtn, "Checkout Button");
								if(verifyElementPresent(MLWalletShopItemsPage.objShippingDetails,getTextVal(MLWalletShopItemsPage.objShippingDetails,"Page"))){
									verifyElementPresent(MLWalletShopItemsPage.objAddAddress,getTextVal(MLWalletShopItemsPage.objAddAddress,"Button"));
									verifyElementPresent(MLWalletShopItemsPage.objEditAddress,"Edit address Icon");
									verifyElementPresent(MLWalletShopItemsPage.objHeader,getTextVal(MLWalletShopItemsPage.objHeader,"Header"));
									verifyElementPresent(MLWalletShopItemsPage.objProductNameInShippingDetails,getTextVal(MLWalletShopItemsPage.objProductNameInShippingDetails,"Product Name in Shipping Details"));
									verifyElementPresent(MLWalletShopItemsPage.objProductQuantity,getTextVal(MLWalletShopItemsPage.objProductQuantity,"Product Quantity"));
									verifyElementPresent(MLWalletShopItemsPage.objItemSubTotal,getTextVal(MLWalletShopItemsPage.objItemSubTotal,"Item SubTotal"));
									verifyElementPresent(MLWalletShopItemsPage.objTotalOrder,getTextVal(MLWalletShopItemsPage.objTotalOrder,"Total Order"));
									verifyElementPresent(MLWalletShopItemsPage.objServiceFee,getTextVal(MLWalletShopItemsPage.objServiceFee,"Service Fee"));
									verifyElementPresent(MLWalletShopItemsPage.objShippingFeeInShippingDetails,getTextVal(MLWalletShopItemsPage.objShippingFeeInShippingDetails,"Shipping Fee in Shipping Details"));
									Swipe("UP",2);
									verifyElementPresent(MLWalletShopItemsPage.objSelectPaymentMethod,getTextVal(MLWalletShopItemsPage.objSelectPaymentMethod,"Header"));
									verifyElementPresent(MLWalletShopItemsPage.objMLWallet,getTextVal(MLWalletShopItemsPage.objMLWallet,"Payment Method"));
									verifyElementPresent(MLWalletShopItemsPage.objOnlineBanking,getTextVal(MLWalletShopItemsPage.objOnlineBanking,"Payment Method"));
									Swipe("UP",1);
									verifyElementPresent(MLWalletShopItemsPage.objMerchandiseSubTotal,getTextVal(MLWalletShopItemsPage.objMerchandiseSubTotal,"Merchandise Subtotal"));
									verifyElementPresent(MLWalletShopItemsPage.objServiceTotal,getTextVal(MLWalletShopItemsPage.objServiceTotal,"Service Total"));
									verifyElementPresent(MLWalletShopItemsPage.objShippingTotal,getTextVal(MLWalletShopItemsPage.objShippingTotal,"Shipping Total"));
									verifyElementPresent(MLWalletShopItemsPage.objPaymentTotal,getTextVal(MLWalletShopItemsPage.objPaymentTotal,"Total Payment"));
									verifyElementPresent(MLWalletShopItemsPage.objPlaceOrderBtn,getTextVal(MLWalletShopItemsPage.objPlaceOrderBtn,"Button"));
									logger.info("MLS_TC_20, Shop Items Shipping Details Page UI validated");
									extentLoggerPass("MLS_TC_20", "MLS_TC_20, Shop Items Shipping Details Page UI validated");
									System.out.println("-----------------------------------------------------------");

								}
							}

							public void shopItemsSelectBranchAddressPageUIValidation_MLS_TC_21() throws Exception {
								HeaderChildNode("Shop Items Select Branch Address Page UI Validation");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								click(MLWalletShopItemsPage.objCheckBox, "Check Box");
								click(MLWalletShopItemsPage.objCheckOutBtn, "Checkout Button");
								verifyElementPresentAndClick(MLWalletShopItemsPage.objEditAddress,"Edit address Icon");
								if(verifyElementPresent(MLWalletShopItemsPage.objInputFieldOne,getTextVal(MLWalletShopItemsPage.objInputFieldOne, "Select Branch Address Field 1"))) {
									verifyElementPresent(MLWalletShopItemsPage.objInputFieldTwo, getTextVal(MLWalletShopItemsPage.objInputFieldTwo,"Select Branch Address Field 2"));
									verifyElementPresent(MLWalletShopItemsPage.objInputFieldThree, getTextVal(MLWalletShopItemsPage.objInputFieldThree,"Select Branch Address Field 3"));
									verifyElementPresent(MLWalletShopItemsPage.objSaveBtn, getTextVal(MLWalletShopItemsPage.objSaveBtn, "Button"));
									verifyElementPresent(MLWalletShopItemsPage.objCancel, getTextVal(MLWalletShopItemsPage.objCancel, "Button"));
									logger.info("MLS_TC_21, Shop Items Select Branch Address Page UI validated");
									extentLoggerPass("MLS_TC_21", "MLS_TC_21, Shop Items Select Branch Address Page UI validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void shopItemsSaveBtnFunctionalityOnSelectBranchScreen_MLS_TC_22() throws Exception {
								HeaderChildNode("Shop Items Save Button Functionality On Select Branch Screen");
								shopItemsSelectBranchAddressPageUIValidation_MLS_TC_21();
								click(MLWalletShopItemsPage.objSaveBtn, getTextVal(MLWalletShopItemsPage.objSaveBtn, "Button"));
								if(verifyElementPresent(MLWalletShopItemsPage.objAddressUpdateMsg,getTextVal(MLWalletShopItemsPage.objAddressUpdateMsg,"Message"))){
									verifyElementPresent(MLWalletShopItemsPage.objOkBtn,getTextVal(MLWalletShopItemsPage.objOkBtn,"Button"));
									logger.info("MLS_TC_22, Shop Items Select Branch Address Page UI validated");
									extentLoggerPass("MLS_TC_22", "MLS_TC_22, Shop Items Select Branch Address Page UI validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void shopItemsCancelBtnFunctionalityOnSelectBranchScreen_MLS_TC_23() throws Exception {
								HeaderChildNode("Shop Items Cancel Button Functionality On Select Branch Screen");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								click(MLWalletShopItemsPage.objCheckBox, "Check Box");
								click(MLWalletShopItemsPage.objCheckOutBtn, "Checkout Button");
								verifyElementPresentAndClick(MLWalletShopItemsPage.objEditAddress,"Edit address Icon");
								String sSelectAddressOne = getText(MLWalletShopItemsPage.objInputFieldOne);
								String sSelectAddressTwo = getText(MLWalletShopItemsPage.objInputFieldTwo);
								String sSelectAddressThree = getText(MLWalletShopItemsPage.objInputFieldThree);
								verifyElementPresentAndClick(MLWalletShopItemsPage.objCancel,getTextVal(MLWalletShopItemsPage.objCancel,"Button"));
								verifyElementPresentAndClick(MLWalletShopItemsPage.objEditAddress,"Edit address Icon");
								String sActualSelectAddressOne = getText(MLWalletShopItemsPage.objInputFieldOne);
								String sActualSelectAddressTwo = getText(MLWalletShopItemsPage.objInputFieldTwo);
								String sActualSelectAddressThree = getText(MLWalletShopItemsPage.objInputFieldThree);
								assertionValidation(sActualSelectAddressOne,sSelectAddressOne);
								assertionValidation(sActualSelectAddressTwo,sSelectAddressTwo);
								assertionValidation(sActualSelectAddressThree,sSelectAddressThree);
								logger.info("MLS_TC_23, Shop Items Cancel Button Functionality On Select Branch Screen validated");
								extentLoggerPass("MLS_TC_23", "MLS_TC_23, Shop Items Cancel Button Functionality On Select Branch Screen validated");
								System.out.println("-----------------------------------------------------------");
							}


							public void shopItemsPlaceOrderBtnFunctionalityOnShippingDetailsScreen_MLS_TC_24() throws Exception {
								HeaderChildNode("Shop Items Place Order Button Functionality On Shipping Details Screen");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								editAddressAndPlaceTheOrder();
								if(verifyElementPresent(MLWalletShopItemsPage.objOtpPage, getTextVal(MLWalletShopItemsPage.objOtpPage, "Pop up"))){
									logger.info("MLS_TC_24, Shop Items Place Order Button Functionality On Shipping Details Screen validated");
									extentLoggerPass("MLS_TC_24", "MLS_TC_24, Shop Items Place Order Button Functionality On Shipping Details Screen validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void shopItemsOneTimePinPageUIValidation_MLS_TC_25() throws Exception {
								HeaderChildNode("Shop Items One Time Pin Page UI Validation");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								editAddressAndPlaceTheOrder();
								if(verifyElementPresent(MLWalletShopItemsPage.objOtpPage, getTextVal(MLWalletShopItemsPage.objOtpPage, "Pop up"))){
									verifyElementPresent(MLWalletShopItemsPage.objOTPMsg,getTextVal(MLWalletShopItemsPage.objOTPMsg,"Message"));
									verifyElementPresent(MLWalletShopItemsPage.objValidateBtn,getTextVal(MLWalletShopItemsPage.objValidateBtn,"Button"));
									verifyElementPresent(MLWalletShopItemsPage.objCancel,getTextVal(MLWalletShopItemsPage.objCancel,"Buttn"));
									logger.info("MLS_TC_25, Shop Items One Time Pin Page UI validated");
									extentLoggerPass("MLS_TC_25", "MLS_TC_25, Shop Items One Time Pin Page UI validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void shopItemsCancelBtnFunctionalityOnOTPScreen_MLS_TC_26() throws Exception {
								HeaderChildNode("Shop Items Cancel Button Functionality on OTP Screen");
								shopItemsOneTimePinPageUIValidation_MLS_TC_25();
								click(MLWalletShopItemsPage.objCancel,getTextVal(MLWalletShopItemsPage.objCancel,"Button"));
								if(verifyElementPresent(MLWalletShopItemsPage.objPlaceOrderBtn,getTextVal(MLWalletShopItemsPage.objPlaceOrderBtn,"Button"))){
									logger.info("MLS_TC_26, Shop Items Cancel Button Functionality on OTP Screen validated");
									extentLoggerPass("MLS_TC_26", "MLS_TC_26, Shop Items Cancel Button Functionality on OTP Screen validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void shopItemsPlacingAnOrderWithOutSelectingPaymentMethod_MLS_TC_33() throws Exception {
								HeaderChildNode("Shop Items Placing An Order without Selecting payment method");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								click(MLWalletShopItemsPage.objCheckBox, "Check Box");
								click(MLWalletShopItemsPage.objCheckOutBtn, "Checkout Button");
								click(MLWalletShopItemsPage.objEditAddress, "Edit Address Tab");
								verifyElementPresent(MLWalletShopItemsPage.objSelectBranchPage, getTextVal(MLWalletShopItemsPage.objSelectBranchPage, "Page"));
								click(MLWalletShopItemsPage.objSaveBtn, "Save Button");
								verifyElementPresent(MLWalletShopItemsPage.objAddressSuccessfulMsg, getTextVal(MLWalletShopItemsPage.objAddressSuccessfulMsg, "Message"));
								click(MLWalletShopItemsPage.objOkBtn, "OK Button");
								Swipe("UP",3);
								click(MLWalletShopItemsPage.objPlaceOrderBtn, "Place Order Button");
								if(verifyElementPresent(MLWalletShopItemsPage.objErrorPopup,getTextVal(MLWalletShopItemsPage.objErrorPopup,"Error Popup"))){
									String sActualErrorPopup = getText(MLWalletShopItemsPage.objErrorPopup);
									String sExpectedErrorPopup = "Please select payment method.";
									assertionValidation(sActualErrorPopup,sExpectedErrorPopup);
									verifyElementPresent(MLWalletShopItemsPage.objOkBtn,getTextVal(MLWalletShopItemsPage.objOkBtn,"Button"));
									logger.info("MLS_TC_33, Please select payment method. Error message validated");
									extentLoggerPass("MLS_TC_33", "MLS_TC_33, Please select payment method. Error message validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void shopItemsOkBtnFunctionalityOnErrorPopup_MLS_TC_34() throws Exception {
								HeaderChildNode("Shop Items Ok Button Functionality On Error popup");
								shopItemsPlacingAnOrderWithOutSelectingPaymentMethod_MLS_TC_33();
								click(MLWalletShopItemsPage.objOkBtn,getTextVal(MLWalletShopItemsPage.objOkBtn,"Button"));
								if(verifyElementNotPresent(MLWalletShopItemsPage.objErrorPopup,"Error popup",5)){
									logger.info("MLS_TC_34, Error popup disappeared after clicking on Ok Button is validated");
									extentLoggerPass("MLS_TC_34", "MLS_TC_34, Error popup disappeared after clicking on Ok Button is validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void shopItemsCartPageNavigation_MLS_TC_42() throws Exception {
								HeaderChildNode("Shop Items Cart Page Navigation");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								if(verifyElementPresent(MLWalletShopItemsPage.objCartPageHeader,getTextVal(MLWalletShopItemsPage.objCartPageHeader,"Header"))){
									verifyElementPresent(MLWalletShopItemsPage.objCheckBox,"Item Check Box");
									verifyElementPresent(MLWalletShopItemsPage.objProductNameInCartPage,getTextVal(MLWalletShopItemsPage.objProductNameInCartPage,"Product Name in Cart Page"));
									verifyElementPresent(MLWalletShopItemsPage.objPriceInCartPage,getTextVal(MLWalletShopItemsPage.objPriceInCartPage,"Price"));
									verifyElementPresent(MLWalletShopItemsPage.objDeleteIcon,"Delete Icon");
									verifyElementPresent(MLWalletShopItemsPage.objCheckOutBtn,getTextVal(MLWalletShopItemsPage.objCheckOutBtn,"Button"));
									logger.info("MLS_TC_42, Shop Items Cart Page Navigation validated");
									extentLoggerPass("MLS_TC_42", "MLS_TC_42, Shop Items Cart Page Navigation validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void shopItemsCancelBtnFunctionalityOnRemoveItemPopup_MLS_TC_43() throws Exception {
								HeaderChildNode("Shop Items Cancel Button Functionality On Remove Item Popup");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								verifyElementPresentAndClick(MLWalletShopItemsPage.objDeleteIcon,"Delete Icon");
								verifyElementPresentAndClick(MLWalletShopItemsPage.objCancel,getTextVal(MLWalletShopItemsPage.objCancel,"Button"));
								if(verifyElementPresent(MLWalletShopItemsPage.objCheckOutBtn,"Cart Page")){
									logger.info("MLS_TC_43, Shop Items Cancel Button Functionality On Remove Item Popup validated");
									extentLoggerPass("MLS_TC_43", "MLS_TC_43, Shop Items Cancel Button Functionality On Remove Item Popup validated");
									System.out.println("-----------------------------------------------------------");
								}
							}


							public void shopItemsOkBtnFunctionalityOnRemoveItemPopup_MLS_TC_44() throws Exception {
								HeaderChildNode("Shop Items Ok Button Functionality On Remove Item Popup");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								verifyElementPresentAndClick(MLWalletShopItemsPage.objDeleteIcon,"Delete Icon");
								verifyElementPresentAndClick(MLWalletShopItemsPage.objYesBtn,getTextVal(MLWalletShopItemsPage.objYesBtn,"Button"));
								if(verifyElementNotPresent(MLWalletShopItemsPage.objProductNameInCartPage,"Cart Page",5)){
									logger.info("MLS_TC_44, Shop Items Ok Button Functionality On Remove Item Popup validated");
									extentLoggerPass("MLS_TC_44", "MLS_TC_44, Shop Items Ok Button Functionality On Remove Item Popup validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void shopItemsNavigationToProfileOption_MLS_TC_46() throws Exception {
								HeaderChildNode("Shop Items Navigation To Profile Option");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								click(MLWalletShopItemsPage.objHambergerMenu, "Hamburger Menu");
								click(MLWalletShopItemsPage.objProfile,getTextVal(MLWalletShopItemsPage.objProfile,"Option"));
								if(verifyElementPresent(MLWalletShopItemsPage.objMyAccount,getTextVal(MLWalletShopItemsPage.objMyAccount,"Page"))){
									logger.info("MLS_TC_46, Shop Items Navigation To Profile Option validated");
									extentLoggerPass("MLS_TC_46", "MLS_TC_46, Shop Items Navigation To Profile Option validated");
									System.out.println("-----------------------------------------------------------");
								}
						 	}

							public void shopItemsProfileScreenNavigation_MLS_TC_47() throws Exception {
								HeaderChildNode("Shop items Profile Screen Navigation");
								shopItemsNavigationToProfileOption_MLS_TC_46();
								verifyElementPresentAndClick(MLWalletShopItemsPage.objMyAccountDropdown,getTextVal(MLWalletShopItemsPage.objMyAccountDropdown,"Dropdown"));
								verifyElementPresentAndClick(MLWalletShopItemsPage.objProfile,getTextVal(MLWalletShopItemsPage.objProfile,"Dropdown Element"));
								verifyElementPresentAndClick(MLWalletShopItemsPage.objMyAccountPageCrossBtn,"Cross Button");
								if(verifyElementPresent(MLWalletShopItemsPage.objMyProfileHeader,getTextVal(MLWalletShopItemsPage.objMyProfileHeader,"Header"))){
									logger.info("MLS_TC_47, Shop items Profile Screen Navigation validated");
									extentLoggerPass("MLS_TC_47", "MLS_TC_47, Shop items Profile Screen Navigation validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void shopItemsMyAccountPopupCrossBtnFunctionality_MLS_TC_48() throws Exception {
								HeaderChildNode("Shop items My Account Popup Cross Btn Functionality");
								shopItemsNavigationToProfileOption_MLS_TC_46();
								verifyElementPresentAndClick(MLWalletShopItemsPage.objMyAccountDropdown,getTextVal(MLWalletShopItemsPage.objMyAccountDropdown,"Dropdown"));
								verifyElementPresentAndClick(MLWalletShopItemsPage.objProfile,getTextVal(MLWalletShopItemsPage.objProfile,"Dropdown Element"));
								verifyElementPresentAndClick(MLWalletShopItemsPage.objMyAccountPageCrossBtn,"Cross Button");
								if(verifyElementPresent(MLWalletShopItemsPage.objMyProfileHeader,getTextVal(MLWalletShopItemsPage.objMyProfileHeader,"Header"))){
									logger.info("MLS_TC_48, Shop items My Account Popup Cross Btn Functionality validated");
									extentLoggerPass("MLS_TC_48", "MLS_TC_48, Shop items My Account Popup Cross Btn Functionality validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void shopItemsMyPurchasePageNavigation_MLS_TC_49() throws Exception {
								HeaderChildNode("Shop items My Purchase Page Navigation");
								shopItemsNavigationToProfileOption_MLS_TC_46();
								verifyElementPresentAndClick(MLWalletShopItemsPage.objMyPurchase,getTextVal(MLWalletShopItemsPage.objMyPurchase,"Option"));
								verifyElementPresentAndClick(MLWalletShopItemsPage.objMyAccountPageCrossBtn,"Cross Button");
								if(verifyElementPresent(MLWalletShopItemsPage.objOrderDetails,getTextVal(MLWalletShopItemsPage.objOrderDetails,"Button"))){
									logger.info("MLS_TC_49, Shop items My Purchase Page Navigation validated");
									extentLoggerPass("MLS_TC_49", "MLS_TC_49, Shop items My Purchase Page Navigation validated");
									System.out.println("-----------------------------------------------------------");
								}
							}

							public void shopItemsSubTotalVerificationWithOutSelectingTheItemsInCart_MLS_TC_59() throws Exception {
								HeaderChildNode("Shop Items Total subTotal Verification without selecting the items in the Cart");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								if(verifyElementPresent(MLWalletShopItemsPage.objSubtotalAmount,getTextVal(MLWalletShopItemsPage.objSubtotalAmount,"SubTotal Items"))){
									String sActualSubtotalItems = getText(MLWalletShopItemsPage.objSubtotalAmount);
									String sExceptedSubtotalItems = "P0.00";
									assertionValidation(sActualSubtotalItems,sExceptedSubtotalItems);
									logger.info("MLS_TC_59, Shop Items Total subTotal Verification without selecting the items in the Cart validated");
									extentLoggerPass("MLS_TC_59", "MLS_TC_59, Shop Items Total subTotal Verification without selecting the items in the Cart validated");
									System.out.println("-----------------------------------------------------------");
								}
							}



							public void shopItemsSubTotalVerificationWithSelectingTheItemsInCart_MLS_TC_60() throws Exception {
								HeaderChildNode("Shop Items Total subTotal Verification with selecting the items in the Cart");
								mlWalletLogin(prop.getproperty("Branch_Verified"));
								shopItemsNavigation();
								selectItemAndAddToCart();
								Swipe("down", 4);
								navigationToCart();
								verifyElementPresentAndClick(MLWalletShopItemsPage.objCheckBox, "Check Box");
								waitTime(5000);
								if(verifyElementPresent(MLWalletShopItemsPage.objSubtotalAmount,getTextVal(MLWalletShopItemsPage.objSubtotalAmount,"SubTotal Items"))){
									String sActualSubtotalItems = getText(MLWalletShopItemsPage.objSubtotalAmount);
									String sExceptedSubtotalItems = "P500.00";
									assertionValidation(sActualSubtotalItems,sExceptedSubtotalItems);
									logger.info("MLS_TC_60, Shop Items Total subTotal Verification with selecting the items in the Cart validated");
									extentLoggerPass("MLS_TC_60", "MLS_TC_60, Shop Items Total subTotal Verification with selecting the items in the Cart validated");
									System.out.println("-----------------------------------------------------------");
								}
							} */
							
							
							
							//============================ Settings Module ============================================================//
							//================================Generalized methods=======================================================//


								public void navigateToProfile() throws Exception {
									click(MLWalletSettingsPage.objProfileIcon1, "Profile Icon");
									waitTime(2000);
									if (verifyElementPresent(MLWalletSettingsPage.objAccountDetails, "Account Details Page")) {
										logger.info("Navigated to settings");
									}
									waitTime(2000);
								}

								public void handleMpin(String mPin, String validationText) throws Exception {
									for (int i = 0; i < mPin.length(); i++) {
										char ch = mPin.charAt(i);
										String ch1 = String.valueOf(ch);
										click(MLWalletSettingsPage.objEnterMpinVal(ch1),
												getTextVal(MLWalletSettingsPage.objEnterMpinVal(ch1), "MPIN"));
									}
									logger.info(validationText + " MPIN " + mPin + " Successfully");
									extentLogger("Enter MPIN", "" + validationText + " MPIN " + mPin + " Successfully");
								}
							//===========================================================================================================//
								public void settingsAccountDetailsValidation_SS_TC_01() throws Exception {
									HeaderChildNode("Settings Account Details validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("Branch_Verified"));
									navigateToProfile();
									waitTime(3000);
									click(MLWalletSettingsPage.objAccountDetails, "Account Details");		
									verifyElementPresent(MLWalletSettingsPage.objAccountDetails, getTextVal(MLWalletSettingsPage.objAccountDetails,"Name"));
									verifyElementPresent(MLWalletSettingsPage.objNameOfAccountHolder,getTextVal(MLWalletSettingsPage.objNameOfAccountHolder, "Name"));
									verifyElementPresent(MLWalletSettingsPage.objMailAddressOfAccountHolder,getTextVal(MLWalletSettingsPage.objMailAddressOfAccountHolder, "Mail Address"));
									verifyElementPresent(MLWalletSettingsPage.objMobileNoOfAccountHolder,getTextVal(MLWalletSettingsPage.objMobileNoOfAccountHolder, "Mobile Number"));
									logger.info("SS_TC_01, Account Details validated");
									extentLoggerPass("SS_TC_01", "SS_TC_01, Account Details validated");
									System.out.println("-----------------------------------------------------------");
									backArrowBtn(2);
									mlWalletLogout();
								}
								
								public void settingsInvalidMLPinValidation_SS_TC_03() throws Exception {
									HeaderChildNode("Settings Invalid ML Pin Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("New_Branch_Verified"));
									navigateToProfile();
									click(MLWalletSettingsPage.objChangeMLPinTab, "Change ML Pin");
									explicitWaitVisibility(MLWalletSettingsPage.objChangeMLPinPage, 10);
									verifyElementPresent(MLWalletSettingsPage.objChangeMLPinPage, "Change ML Pin");
									handleMpin(prop.getproperty("wrongMpin"), "Entered Invalid ML PIN");
									waitTime(2000);
									if(verifyElementPresent(MLWalletSettingsPage.objInvalaidMpinPopUp,getTextVal(MLWalletSettingsPage.objInvalaidMpinPopUp,"Error Popup Message"))){
										String sInvalidMPinPopUp = getText(MLWalletSettingsPage.objInvalaidMpinPopUp);
										String sExceptedErrorPOpUp = "You have entered an invalid PIN. Please try again.";
										assertionValidation(sInvalidMPinPopUp,sExceptedErrorPOpUp);
										click(MLWalletCashOutPage.objOkBtn,"OK Button");
										logger.info("SS_TC_03, Invalid ML PIN validated");
										extentLoggerPass("SS_TC_03", "SS_TC_03, Invalid ML PIN validated");
										System.out.println("-----------------------------------------------------------");
									}
									backArrowBtn(2);
									mlWalletLogout();
								}


								public void settingsValidMLPinValidation_SS_TC_02() throws Exception {
									HeaderChildNode("Settings Valid ML Pin Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("New_Branch_Verified"));
									navigateToProfile();
									click(MLWalletSettingsPage.objChangeMLPinTab, "Change ML Pin");
									verifyElementPresent(MLWalletSettingsPage.objChangeMLPinTab, "Change ML Pin");
									waitTime(2000);
									handleMpin(prop.getproperty("mPin"), "Entered");
									waitTime(3000);
									if (verifyElementPresent(MLWalletSettingsPage.objEnterNewMMLpinText,
											getTextVal(MLWalletSettingsPage.objEnterNewMMLpinText, "Page"))) {
										waitTime(2000);
										handleMpin(prop.getproperty("newMpin"), "New ML PIN");
										waitTime(2000);
										handleMpin(prop.getproperty("newMpin"), "Confirmed New ML ");
										waitTime(2000);
										if(verifyElementPresent(MLWalletSettingsPage.objMLPinChangedPopup,
												getTextVal(MLWalletSettingsPage.objMLPinChangedPopup,"Success Popup"))){
											String sActualSuccessPopUp = getText(MLWalletSettingsPage.objMLPinChangedPopup);
											String sExpectedSuccessPopUp = "ML Pin Successfully Changed";
											assertionValidation(sActualSuccessPopUp,sExpectedSuccessPopUp);
											click(MLWalletSettingsPage.objGotItBtn, "Got It Button");
											logger.info("SS_TC_02 To validate ML PIN validated");
											extentLoggerPass("SS_TC_02", "'SS_TC_02' To validate ML PIN validated");
											System.out.println("-----------------------------------------------------------");
										}
									}
									backArrowBtn(2);
									mlWalletLogout();
								}


								public void settingsBiometricsLogin_SS_TC_04() throws Exception {
									HeaderChildNode("Settings Biometrics Login");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("Branch_Verified"));
									navigateToProfile();
									verifyElementPresentAndClick(MLWalletSettingsPage.objBiometricsLogin,getTextVal(MLWalletSettingsPage.objBiometricsLogin,"Option"));
									verifyElementPresentAndClick(MLWalletSettingsPage.objBiometricsSwitch,"Biometrics Switch");
									enterOTP(prop.getproperty("Valid_OTP"));
									if(verifyElementPresent(MLWalletSettingsPage.objActivateBiometricsLogin,getTextVal(MLWalletSettingsPage.objActivateBiometricsLogin,"Header"))){
										String sActualActiveMsg = getText(MLWalletSettingsPage.objActivateBiometricsLogin);
										String sExceptedActiveMsg = "Activate Biometrics Login";
										assertionValidation(sActualActiveMsg,sExceptedActiveMsg);
										logger.info("SS_TC_04, Settings Biometrics Login Validated");
										extentLoggerPass("SS_TC_04", "'SS_TC_04' Settings Biometrics Login Validated");
										System.out.println("-----------------------------------------------------------");
									}


								}


								public void settingAccRecovery_SS_TC_05() throws Exception {
									HeaderChildNode("Invalid ML Pin Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("Branch_Verified"));
									navigateToProfile();
									click(MLWalletSettingsPage.objAccountRecovery, "Account Recovery");
								    explicitWaitVisibility(MLWalletSettingsPage.objMlWalletSupportPage, 10);
									verifyElementPresent(MLWalletSettingsPage.objMlWalletSupportPage,
											getTextVal(MLWalletSettingsPage.objMlWalletSupportPage, "Page"));
									click(MLWalletSettingsPage.objFullNameField, "First Name Field");
									type(MLWalletSettingsPage.objFullNameField, prop.getproperty("firstName"), "First Name Field");
									Swipe("UP",1);
									click(MLWalletSettingsPage.objRegisteredEmail, "Registered Email Field");
									type(MLWalletSettingsPage.objRegisteredEmail, prop.getproperty("eMailAddress"),
											"Registered Email Field");
									Swipe("UP",1);
									click(MLWalletSettingsPage.objMobileNumber, "Mobile Number Field");
									type(MLWalletSettingsPage.objMobileNumber, prop.getproperty("Branch_Verified"), "Mobile Number Field");
									
									click(MLWalletSettingsPage.objNatureOfReqRadioBtn,
											getTextVal(MLWalletSettingsPage.objNatureOfReqRadioBtn, "Text"));
									Swipe("UP",1);
									click(MLWalletSettingsPage.objNextBtn, "Next Button");
									click(MLWalletSettingsPage.objNextBtn, "Next Button");
									explicitWaitVisibility(MLWalletSettingsPage.objStolenPage, 10);
									verifyElementPresent(MLWalletSettingsPage.objStolenPage,
											getTextVal(MLWalletSettingsPage.objStolenPage, "Page"));
									click(MLWalletSettingsPage.objYourAnswer, "Lost/Stolen Mobile Number Field");
									type(MLWalletSettingsPage.objYourAnswer, prop.getproperty("Branch_Verified"),
											"Lost/Stolen Mobile Number Field");
									click(MLWalletSettingsPage.objNewMobNo, "New Mobile Number Field");
									type(MLWalletSettingsPage.objNewMobNo, prop.getproperty("newMobileNumber"), "New Mobile Number Field");
                                    
									Swipe("UP",1);
									click(MLWalletSettingsPage.objFacebookMessangerName, "Facebook Messenger Name Field");
									type(MLWalletSettingsPage.objFacebookMessangerName, prop.getproperty("messangerName"),
											"Facebook Messenger Name Field");
								    
									click(MLWalletSettingsPage.objSumbitBtn, "Submit Button");
									waitTime(2000);
									verifyElementPresent(MLWalletSettingsPage.objReviewPage, "Review Page");
									String actualText = "Please allow us some time to review the details of your request. A customer service representative will contact you for updates and/or to get additional information.";
									String reviewExpectedText = getText(MLWalletSettingsPage.objReviewPage);
									assertionValidation(actualText, reviewExpectedText);
									logger.info("'SS_TC_05', To verify account recovery validated");
									extentLoggerPass("Account Recovery", "'SS_TC_05', To verify account recovery validated");
									System.out.println("-----------------------------------------------------------");
									backArrowBtn(2);
									mlWalletLogout();
								}
								

								public void settingsBackBtnValidation_SS_TC_07() throws Exception {
									HeaderChildNode("Settings Back Button Button Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("Branch_Verified"));
									navigateToProfile();
									verifyElementPresentAndClick(MLWalletSettingsPage.objSettingsBackBtn,"Settings Back Button");
									explicitWaitVisibility(MLWalletLoginPage.objAvailableBalance, 10);
									if (verifyElementPresent(MLWalletLoginPage.objAvailableBalance, getTextVal(MLWalletLoginPage.objAvailableBalance, "Text"))) {
										logger.info("SS_TC_07, Settings Back Button Button validated");
										extentLoggerPass("SS_TC_07", "SS_TC_07, Settings Back Button Button validated");
										System.out.println("-----------------------------------------------------------");
									}
									mlWalletLogout();
								}

								public void settingsDeleteAccountPopUpUIValidation_SS_TC_10() throws Exception {
									HeaderChildNode("Settings Delete Account PopUp UI Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("Branch_Verified"));
									navigateToProfile();
									verifyElementPresentAndClick(MLWalletSettingsPage.objDeleteAccount,getTextVal(MLWalletSettingsPage.objDeleteAccount,"Option"));
									waitTime(2000);
									if(verifyElementPresent(MLWalletSettingsPage.objDeleteConfirmationPopUp,getTextVal(MLWalletSettingsPage.objDeleteConfirmationPopUp,"Confirmation Popup"))){
										String sActualConfirmationPopupMsg = getText(MLWalletSettingsPage.objDeleteConfirmationPopUp);
										String sExpectedConfirmationPopupMsg = "Are you sure you would like to delete your account?";
										assertionValidation(sActualConfirmationPopupMsg,sExpectedConfirmationPopupMsg);
										verifyElementPresent(MLWalletSettingsPage.objProceedBtn,getTextVal(MLWalletSettingsPage.objProceedBtn,"Button"));
										verifyElementPresent(MLWalletSettingsPage.objCancelBtn,getTextVal(MLWalletSettingsPage.objCancelBtn,"Button"));
										click(MLWalletSettingsPage.objCancelBtn, "Cancel Button");
										logger.info("SS_TC_10, Settings, Settings Delete Account PopUp UI validated");
										extentLoggerPass("SS_TC_10", "SS_TC_10, Settings Delete Account PopUp UI validated");
										System.out.println("-----------------------------------------------------------");
									}
									backArrowBtn(1);
									mlWalletLogout();
								}
								public void settingsChangeMLPinNavigation_SS_TC_12(String status) throws Exception {
									HeaderChildNode("Settings Change ML Pin Navigation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("Branch_Verified"));
									navigateToProfile();
									verifyElementPresentAndClick(MLWalletSettingsPage.objChangeMLPinTab,getTextVal(MLWalletSettingsPage.objChangeMLPinTab,"option"));
									Thread.sleep(5000);
									if(verifyElementPresent(MLWalletSettingsPage.objChangeMLPinPage,getTextVal(MLWalletSettingsPage.objChangeMLPinPage,"Page"))){
										verifyElementPresent(MLWalletSettingsPage.objEnterCurrentMLPin,getTextVal(MLWalletSettingsPage.objEnterCurrentMLPin,"Header"));
										verifyElementPresent(MLWalletSettingsPage.objMLPinEditField,"ML Pin Edit Field");
										logger.info("SS_TC_12, Settings Change ML Pin Navigation validated");
										extentLoggerPass("SS_TC_12", "SS_TC_12, Settings Change ML Pin Navigation validated");
										System.out.println("-----------------------------------------------------------");
									}
									if(status.equals("true"))
									{
									backArrowBtn(2);
									mlWalletLogout();
									}
								}

								public void settingsChangeMlPinBackBtnValidation_SS_TC_13() throws Exception {
									HeaderChildNode("Settings Change ML Pin Back Button Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									settingsChangeMLPinNavigation_SS_TC_12("false");
									verifyElementPresentAndClick(MLWalletSettingsPage.objChangeMLPinBackBtn,"Back Button");
									waitTime(2000);
									if(verifyElementPresent(MLWalletSettingsPage.objSettings,getTextVal(MLWalletSettingsPage.objSettings,"Page"))){
										logger.info("SS_TC_13, Settings, After clicking on  Change ML Pin Back Button App Navigated to Settings Page validated");
										extentLoggerPass("SS_TC_13", "SS_TC_13, Settings, After clicking on  Change ML Pin Back Button App Navigated to Settings Page validated");
										System.out.println("-----------------------------------------------------------");
									}
									backArrowBtn(1);
									mlWalletLogout();
								}

								public void settingsPageUIValidation_SS_TC_06() throws Exception {
									HeaderChildNode("Settings Page UI Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("Branch_Verified"));
									navigateToProfile();
									if(verifyElementPresent(MLWalletSettingsPage.objSettings,getTextVal(MLWalletSettingsPage.objSettings,"Page"))){
										verifyElementPresent(MLWalletSettingsPage.objAccountDetails,getTextVal(MLWalletSettingsPage.objAccountDetails,"Option"));
										verifyElementPresent(MLWalletSettingsPage.objChangeMLPinTab,getTextVal(MLWalletSettingsPage.objChangeMLPinTab,"Option"));
									//	verifyElementPresent(MLWalletSettingsPage.objBiometricsLogin,getTextVal(MLWalletSettingsPage.objBiometricsLogin,"Option"));
										verifyElementPresent(MLWalletSettingsPage.objAccountRecovery,getTextVal(MLWalletSettingsPage.objAccountRecovery,"Option"));
										verifyElementPresent(MLWalletSettingsPage.objNotification,getTextVal(MLWalletSettingsPage.objNotification,"Option"));
										verifyElementPresent(MLWalletSettingsPage.objDeleteAccount,getTextVal(MLWalletSettingsPage.objDeleteAccount,"Option"));
										logger.info("SS_TC_06, Settings Page UI validated");
										extentLoggerPass("SS_TC_06", "SS_TC_06, Settings Page UI validated");
										System.out.println("-----------------------------------------------------------");
									}
									backArrowBtn(1);
									mlWalletLogout();
								}

								public void settingsAccountDetailsNavigation_SS_TC_14(String status) throws Exception {
									HeaderChildNode("Settings Account Details Navigation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("Branch_Verified"));
									navigateToProfile();
									verifyElementPresentAndClick(MLWalletSettingsPage.objAccountDetails,getTextVal(MLWalletSettingsPage.objAccountDetails,"Option"));
									waitTime(2000);
									if(verifyElementPresent(MLWalletSettingsPage.objAccountDetailsPage, getTextVal(MLWalletSettingsPage.objAccountDetailsPage,"Page"))){
										logger.info("SS_TC_14, Settings Account Details Navigation validated");
										extentLoggerPass("SS_TC_14", "SS_TC_14, Settings Account Details Navigation validated");
										System.out.println("-----------------------------------------------------------");
									}
									if(status.equals("true"))
									{
									backArrowBtn(2);
									mlWalletLogout();
									}
								}

								public void settingsAccountDetailsBackBtnValidation_SS_TC_15() throws Exception {
									HeaderChildNode("Settings Account details Back Button Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									settingsAccountDetailsNavigation_SS_TC_14("false");
									verifyElementPresentAndClick(MLWalletSettingsPage.objAccountDetailsBackBtn,"Back Button");
									if(verifyElementPresent(MLWalletSettingsPage.objSettings, getTextVal(MLWalletSettingsPage.objSettings,"Page"))){
										logger.info("SS_TC_15, Settings Account Details Back Button Navigation validated");
										extentLoggerPass("SS_TC_15", "SS_TC_15, Settings Account Details Back Button Navigation validated");
										System.out.println("-----------------------------------------------------------");
									}
									backArrowBtn(1);
									mlWalletLogout();
								}

								public void settingChangeMLPinUIValidation_SS_TC_16() throws Exception {
									HeaderChildNode("Settings Change ML Pin UI Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("New_Branch_Verified"));
									navigateToProfile();
									verifyElementPresentAndClick(MLWalletSettingsPage.objChangeMLPinTab,getTextVal(MLWalletSettingsPage.objChangeMLPinTab,"option"));
									waitTime(2000);
									if(verifyElementPresent(MLWalletSettingsPage.objChangeMLPinPage,getTextVal(MLWalletSettingsPage.objChangeMLPinPage,"Page"))){
										verifyElementPresent(MLWalletSettingsPage.objEnterCurrentMLPin,getTextVal(MLWalletSettingsPage.objEnterCurrentMLPin,"Header"));
										//verifyElementPresent(MLWalletSettingsPage.objKeyword,"Keyword to Enter MLPin");
										verifyElementPresent(MLWalletSettingsPage.objMLPinEditField,"ML Pin Edit Field");
										logger.info("SS_TC_16, Settings Change ML Pin UI validated");
										extentLoggerPass("SS_TC_16", "SS_TC_16, Settings Change ML Pin UI validated");
										System.out.println("-----------------------------------------------------------");
									}
									backArrowBtn(2);
									mlWalletLogout();
								}

								public void settingsAccountRecoveryUIValidation_SS_TC_17() throws Exception {
									HeaderChildNode("Settings Account Recovery UI Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("New_Branch_Verified"));
									navigateToProfile();
									verifyElementPresentAndClick(MLWalletSettingsPage.objAccountRecovery,getTextVal(MLWalletSettingsPage.objAccountRecovery,"Option"));
									waitTime(2000);
									if(verifyElementPresent(MLWalletSettingsPage.objTroubleSigningIn,getTextVal(MLWalletSettingsPage.objTroubleSigningIn,"Page"))){
										verifyElementPresent(MLWalletSettingsPage.objMLWalletSupport,getTextVal(MLWalletSettingsPage.objMLWalletSupport,"Header"));
										verifyElementPresent(MLWalletSettingsPage.objFullNameField, "First Name Field");
										verifyElementPresent(MLWalletSettingsPage.objRegisteredEmail, "Registered Email Field");
										Swipe("UP",1);
										verifyElementPresent(MLWalletSettingsPage.objMobileNumber, "Mobile Number Field");
										Swipe("UP",1);
										if (verifyElementDisplayed(MLWalletSettingsPage.objNatureOfRequests)) {
											List<WebElement> values = findElements(MLWalletSettingsPage.objNatureOfRequests);
											for (int i = 0; i < values.size(); i++) {	
											
												String savedRecipientName = values.get(i).getText();
												logger.info("Nature of Your Request : " + savedRecipientName + " is displayed");
												extentLogger(" ", "Nature of Your Request : " + savedRecipientName + " is displayed");											
											
										}
										logger.info("SS_TC_17, Settings Account Recovery UI validated");
										extentLoggerPass("SS_TC_17", "SS_TC_17, Settings Account Recovery UI validated");
										System.out.println("-----------------------------------------------------------");
									}
									backArrowBtn(2);
									mlWalletLogout();
								}
								}

								public void settingsDeleteAccountCancelBtnValidation_SS_TC_18() throws Exception {
									HeaderChildNode("Settings Delete Account Cancel Button Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("Branch_Verified"));
									navigateToProfile();
									verifyElementPresentAndClick(MLWalletSettingsPage.objDeleteAccount,getTextVal(MLWalletSettingsPage.objDeleteAccount,"Option"));
									waitTime(2000);
									verifyElementPresent(MLWalletSettingsPage.objDeleteConfirmationPopUp,getTextVal(MLWalletSettingsPage.objDeleteConfirmationPopUp,"Delete Confirmation Popup"));
									verifyElementPresentAndClick(MLWalletSettingsPage.objCancelBtn,getTextVal(MLWalletSettingsPage.objCancelBtn,"Button"));
									waitTime(2000);
									if(verifyElementPresent(MLWalletSettingsPage.objSettings,getTextVal(MLWalletSettingsPage.objSettings,"Page"))){
										logger.info("SS_TC_18, Settings, After clicking on Cancel button on Delete Confirmation Popup App should navigate to Settings Page is validated");
										extentLoggerPass("SS_TC_18", "SS_TC_18, Settings, After clicking on Cancel button on Delete Confirmation Popup App should navigate to Settings Page is validated");
										System.out.println("-----------------------------------------------------------");
									}
									backArrowBtn(1);
									mlWalletLogout();
								}

								public void settingsBiometricsLoginUIValidation_SS_TC_20() throws Exception {
									HeaderChildNode("Settings Biometrics Login UI Validation");
									mlWalletLogin(prop.getproperty("Branch_Verified"));
									navigateToProfile();
									verifyElementPresentAndClick(MLWalletSettingsPage.objBiometricsLogin,getTextVal(MLWalletSettingsPage.objBiometricsLogin,"Option"));
									waitTime(3000);
									if(verifyElementPresent(MLWalletSettingsPage.objBiometricsLogin,getTextVal(MLWalletSettingsPage.objBiometricsLogin,"Page"))){
										verifyElementPresent(MLWalletSettingsPage.objBiometrics,getTextVal(MLWalletSettingsPage.objBiometrics,"Header"));
										verifyElementPresent(MLWalletSettingsPage.objBiometricsSwitch,"Biometrics Switch");
										verifyElementPresent(MLWalletSettingsPage.objActivateBiometricsLogin,getTextVal(MLWalletSettingsPage.objActivateBiometricsLogin,"Header"));
										logger.info("SS_TC_20, Settings, Settings Biometrics Login UI validated");
										extentLoggerPass("SS_TC_20", "SS_TC_20, Settings Biometrics Login UI validated");
										System.out.println("-----------------------------------------------------------");
									}
								}

								public void settingsAccountRecoveryNavigation_SS_TC_21(String status) throws Exception {
									HeaderChildNode("Settings Account Recovery Navigation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("Branch_Verified"));
									navigateToProfile();
									verifyElementPresentAndClick(MLWalletSettingsPage.objAccountRecovery,getTextVal(MLWalletSettingsPage.objAccountRecovery,"Option"));
									waitTime(2000);
									if(verifyElementPresent(MLWalletSettingsPage.objTroubleSigningIn,getTextVal(MLWalletSettingsPage.objTroubleSigningIn,"Page"))){
										logger.info("SS_TC_21, Settings, After clicking on Account recovery, application navigated to Trouble Signing in page is validated");
										extentLoggerPass("SS_TC_21", "SS_TC_21, After clicking on Account recovery, application navigated to Trouble Signing in page is validated");
										System.out.println("-----------------------------------------------------------");
									}
									if(status.equals("true"))
									{
										backArrowBtn(2);
										mlWalletLogout();
									}
									
								}

								public void settingsTroubleSigningInBackBtnValidation_SS_TC_22() throws Exception {
									HeaderChildNode("Settings Trouble Signing In Bak Btn Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									settingsAccountRecoveryNavigation_SS_TC_21("false");
									verifyElementPresentAndClick(MLWalletSettingsPage.objTroubleSigningInBackBtn,"Trouble Signing In Page Back Button");
									explicitWaitVisibility(MLWalletSettingsPage.objSettings, 10);
									if(verifyElementPresent(MLWalletSettingsPage.objSettings, getTextVal(MLWalletSettingsPage.objSettings,"Page"))){
										logger.info("SS_TC_22, Settings Account Details Back Button Navigation validated");
										extentLoggerPass("SS_TC_22", "SS_TC_22, Settings Account Details Back Button Navigation validated");
										System.out.println("-----------------------------------------------------------");
									}
									backArrowBtn(1);
									mlWalletLogout();
								}

								public void settingsBiometricsLoginNavigation_SS_TC_23() throws Exception {
									HeaderChildNode("Settings Biometrics Login Navigation");
									mlWalletLogin(prop.getproperty("Branch_Verified"));
									navigateToProfile();
									verifyElementPresentAndClick(MLWalletSettingsPage.objBiometricsLogin,getTextVal(MLWalletSettingsPage.objBiometricsLogin,"Option"));
									waitTime(3000);
									if(verifyElementPresent(MLWalletSettingsPage.objBiometricsLogin,getTextVal(MLWalletSettingsPage.objBiometricsLogin,"Page"))){
										logger.info("SS_TC_23, Settings, After clicking on Account recovery, application navigated to Biometrics Login page is validated");
										extentLoggerPass("SS_TC_23", "SS_TC_23, After clicking on Account recovery, application navigated to Biometrics Login page is validated");
										System.out.println("-----------------------------------------------------------");
									}
								}

								public void settingsBiometricsLoginBackBtnValidation_SS_TC_24() throws Exception {
									HeaderChildNode("Settings Biometrics Login Back Button validation");
									settingsBiometricsLoginNavigation_SS_TC_23();
									verifyElementPresentAndClick(MLWalletSettingsPage.objBiometricsLoginBackBtn,"Biometrics Login Page Back Button");
									if(verifyElementPresent(MLWalletSettingsPage.objSettings, getTextVal(MLWalletSettingsPage.objSettings,"Page"))){
										logger.info("SS_TC_24, Settings Biometrics Login Back Button validated");
										extentLoggerPass("SS_TC_24", "SS_TC_24, Settings Biometrics Login Back Button validated validated");
										System.out.println("-----------------------------------------------------------");
									}
								}

							
								
								//=========================================== Cash In  Via Branch ================================//


								public void cashInViaBranchNavigation(String sTier) throws Exception {
									mlWalletLogin(sTier);
									click(MLWalletCashInViaBranch.objCashInMenu, "Cash In");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objBranchName, "Cash In Options Page");
									click(MLWalletCashInViaBranch.objBranchName, "ML Branch");
									waitTime(2000);
								}
									public void cashInViaBranchEnterAmount(String sAmount) throws Exception {
									type(MLWalletCashInViaBranch.objAmountTextField, sAmount, "Amount Text Field");
									Swipe("up", 1);
									click(MLWalletCashInViaBranch.objNextButton, "Next Button");
									click(MLWalletCashInViaBranch.objNextButton, "Next Button");
									waitTime(2000);
								}

								public void maxTransactionLimitValidation(String sTier) throws Exception {
									cashInViaBranchNavigation(sTier);
									cashInViaBranchEnterAmount("50001");
									waitTime(2000);
									click(MLWalletCashInViaBranch.objPopUpContinueButton, getTextVal(MLWalletCashInViaBranch.objPopUpContinueButton, "Button"));
									waitTime(2000);
							
								}
								
								
								public void cancelPreviousTransactionAndContinue() throws Exception {
									waitTime(2000);
									if (verifyElementPresent(MLWalletCashInViaBranch.objPopUpCancelTransactionBtn,"Cancel Button")) {
										click(MLWalletCashInViaBranch.objPopUpCancelTransactionBtn, getTextVal(MLWalletCashInViaBranch.objCancelTransactionBtn, "button"));
										waitTime(3000);
//										click(MLWalletCashInViaBranch.objCancelBtn1, getTextVal(MLWalletCashInViaBranch.objCancelBtn1, "Button"));
										verifyElementPresentAndClick(MLWalletCashInViaBranch.objBackToHomeBtn, getTextVal(MLWalletCashInViaBranch.objBackToHomeBtn, "Button"));
										click(MLWalletCashInViaBranch.objCashInPage, "Cash In");
										waitTime(2000);
										verifyElementPresent(MLWalletCashInViaBranch.objBranchName, "Cash In Options Page");
										click(MLWalletCashInViaBranch.objBranchName, "ML Branch");
										waitTime(2000);
									}
								}


								public void cashInviaBranch_ValidAmount_Scenario_CIBR_TC_01() throws Exception {
									HeaderChildNode("ML_Wallet_Cash_In_Via_Barnch_ValidAmount_Scenario");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("New_Branch_Verified"));
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									enterOTP(prop.getproperty("Valid_OTP"));
									waitTime(5000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"))){
										verifyElementPresent(MLWalletCashInViaBranch.objPHP,getTextVal(MLWalletCashInViaBranch.objPHP,"PHP"));
										verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate,getTextVal(MLWalletCashInViaBranch.objCreatedDate,"Date"));
										verifyElementPresent(MLWalletCashInViaBranch.objStatus,getTextVal(MLWalletCashInViaBranch.objStatus,"Status"));
										verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo,getTextVal(MLWalletCashInViaBranch.objTransactionNo,"Transaction Number"));
										verifyElementPresentAndClick(MLWalletCashInViaBranch.objCrossBtn,"Cash In Branch Cross Button");
										verifyRecentTransaction2(prop.getproperty("New_Branch_Verified"));
										if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {	
											verifyElementPresent(MLWalletCashInBank.objPendingStatus,getTextVal(MLWalletCashInBank.objPendingStatus,"Status"));
											String sStatus = getText(MLWalletCashInBank.objPendingStatus);
											String pendingStatus=sStatus.substring(2, 9);	
											String sExpectedStatus = "Pending";
											assertionValidation(pendingStatus,sExpectedStatus);	
//											verifyElementPresent(MLWalletCashInBank.objPending, getTextVal(MLWalletCashInBank.objPending, "Status"));
											logger.info("'CIBR_TC_01', To validate valid Amount in Cash In ML Branch ");
											extentLoggerPass("'CIBR_TC_01", "'CIBR_TC_01', To validate valid Amount in Cash In ML Branch ");
											System.out.println("-----------------------------------------------------------");
										}
									}
									mlWalletLogout();
								}

								public void cashInViaBranchCancelTransactionScenario_CIBR_TC_02() throws Exception {
									HeaderChildNode("ML Wallet Cash In Via Branch Cancel Transaction Scenario");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									waitTime(4000);
									verifyElementPresentAndClick(MLWalletCashInViaBranch.objCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objCancelTransactionBtn,"Button"));
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objCancelTransactionPopup,getTextVal(MLWalletCashInViaBranch.objCancelTransactionPopup,"PopUp"));
									click(MLWalletCashInViaBranch.objPopUpCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objPopUpCancelTransactionBtn,"Button"));
									waitTime(2000);
									click(MLWalletCashInViaBranch.objBackToHomeBtn, getTextVal(MLWalletCashInViaBranch.objBackToHomeBtn, "Button"));
									verifyRecentTransaction2(prop.getproperty("Branch_Verified"));
									if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
										verifyElementPresent(MLWalletCashInBank.objCancelStatus,getTextVal(MLWalletCashInBank.objCancelStatus,"Status"));
										String sStatus = getText(MLWalletCashInBank.objCancelStatus);
										String pendingStatus=sStatus.substring(2, 10);	
										String sExpectedStatus = "Cancelled";
										System.out.println(sExpectedStatus);
										assertionValidation(pendingStatus,sExpectedStatus);	
										logger.info("'CIBR_TC_02', To validate Cancel Transaction in Cash In ML Branch");
										extentLoggerPass("'CIBR_TC_02","'CIBR_TC_02', To validate Cancel Transaction in Cash In ML Branch");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}

								public void cashInviaBranch_Invalid_Amount_CIBR_TC_03() throws Exception {
									HeaderChildNode("ML Wallet Cash In via Branch Invalid Amount");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cashInViaBranchEnterAmount("0");
									if (verifyElementPresent(MLWalletCashInViaBranch.objInvalidAmountMsg, getTextVal(MLWalletCashInViaBranch.objInvalidAmountMsg, "Error Message"))) {
										String sInvalidAmountErrorMsg = getText(MLWalletCashInViaBranch.objInvalidAmountMsg);
										String sExpectedErrorMsg = "The amount should not be less than 1";
										assertionValidation(sInvalidAmountErrorMsg, sExpectedErrorMsg);
										logger.info("'CIBR_TC_03', 'CIBR_TC_03' To validate Invalid Amount");
										extentLoggerPass("CIBR_TC_03", "'CIBR_TC_03', To validate Invalid Amount");
									}
									getDriver().resetApp();
								}


								public void cashInViaBranch_Maximum_Limit_Amount_CIBR_TC_04() throws Exception {
									HeaderChildNode("ML Wallet Cash In via Branch Maximum Limit Amount");
									waitTime(2000);
									changeNumberPage();
									clearField();
									maxTransactionLimitValidation(prop.getproperty("Branch_Verified"));
									waitTime(3000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objBankMaxLimitTxt,getTextVal(MLWalletCashInViaBranch.objBankMaxLimitTxt,"Error Message"))) {
										String sErrorMsg = getText(MLWalletCashInViaBranch.objBankMaxLimitTxt);
										String sExpectedErrorMsg = "The maximum Branch Cash-in per transaction set for your verification level is P50,000.00. Please try again.";
										assertionValidation(sErrorMsg, sExpectedErrorMsg);
										logger.info("'CIBR_TC_04', ML Wallet Cash In via Branch Maximum Limit Amount Validated");
										extentLoggerPass("CIBR_TC_04", "'CIBR_TC_04', ML Wallet Cash In via Branch Maximum Limit Amount Validated");
									}
									getDriver().resetApp();
								}


								public void cashInViaBRanchInvalidAmount_CIBR_TC_05() throws Exception {
									HeaderChildNode("ML Wallet Cash In via Branch Invalid Amount");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cashInViaBranchEnterAmount("");
									if (verifyElementPresent(MLWalletCashInViaBranch.objAmountRErrMsg, getTextVal(MLWalletCashInViaBranch.objAmountRErrMsg, "Error Message"))) {
										String sInvalidAmountErrorMsg = getText(MLWalletCashInViaBranch.objAmountRErrMsg);
										String sExpectedErrorMsg = "Amount is required";
										assertionValidation(sInvalidAmountErrorMsg, sExpectedErrorMsg);
										logger.info("CIBR_TC_05, Amount is required - Error Message is validated");
										extentLoggerPass("CIBR_TC_05", "CIBR_TC_05, Amount is required - Error Message is validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchUIValidation_CIBR_TC_06(String status) throws Exception {
									HeaderChildNode("Cash In Via Branch UI Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									waitTime(2000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objCashInPage,getTextVal(MLWalletCashInViaBranch.objCashInPage,"Page"))){
										verifyElementPresent(MLWalletCashInViaBranch.objBranchCashIn,getTextVal(MLWalletCashInViaBranch.objBranchCashIn,"Header"));
										verifyElementPresent(MLWalletCashInViaBranch.objUserName,getTextVal(MLWalletCashInViaBranch.objUserName,"User Name"));
										verifyElementPresent(MLWalletCashInViaBranch.objUserNumber,getTextVal(MLWalletCashInViaBranch.objUserNumber,"User Number"));
										verifyElementPresent(MLWalletCashInViaBranch.objAmountTextField,"Amount Input Field");
										verifyElementPresent(MLWalletCashInViaBranch.objNextButton, "Next Button");
										logger.info("CIBR_TC_06, Cash In Via Branch UI validated");
										extentLoggerPass("CIBR_TC_06", "CIBR_TC_06, Cash In Via Branch UI validated");
										System.out.println("-----------------------------------------------------------");
									}
									if(status.equals("true"))
									{
									getDriver().resetApp();
									}
								}

								public void cashInViaBranchBackBtnValidation_CIBR_TC_07() throws Exception {
									HeaderChildNode("Cash In Via Branch Back Btn Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchUIValidation_CIBR_TC_06("false");	
									verifyElementPresentAndClick(MLWalletCashInViaBranch.objCashInBranchBackBtn,"Cash In Branch Back Button");
									waitTime(2000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objCashInPage,getTextVal(MLWalletCashInViaBranch.objCashInPage,"Page"))){
										logger.info("CIBR_TC_07, Cash In Via Branch Back Btn validated");
										extentLoggerPass("CIBR_TC_07", "CIBR_TC_07, Cash In Via Branch Back Btn validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}


								public void cashInViaBranchNavigationToHomePageFromQRPage_CIBR_TC_08() throws Exception {
									HeaderChildNode("Cash In Via Branch, Navigation to Home Page After clicking on cross button on QR Code Page");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									enterOTP(prop.getproperty("Valid_OTP"));
									waitTime(6000);
									verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"));
									verifyElementPresentAndClick(MLWalletCashInViaBranch.objCrossBtn,"Cash In Branch Cross Button");
									waitTime(2000);
									if(verifyElementPresent(MLWalletLoginPage.objAvailableBalance,getTextVal(MLWalletLoginPage.objAvailableBalance,"Header"))){
										logger.info("CIBR_TC_08, Cash In Via Branch, Navigation to Home Page After clicking on cross button on QR Code Page validated");
										extentLoggerPass("CIBR_TC_08", "CIBR_TC_08, Cash In Via Branch, Navigation to Home Page After clicking on cross button on QR Code Page validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchQRCodePageUIValidation_CIBR_TC_09() throws Exception {
									HeaderChildNode("Cash In Via Branch QR Code UI Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									waitTime(3000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"))) {
										verifyElementPresent(MLWalletCashInViaBranch.objQRCode,"QR Code");
										verifyElementPresent(MLWalletCashInViaBranch.objPHP, getTextVal(MLWalletCashInViaBranch.objPHP, "PHP"));
										verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate, getTextVal(MLWalletCashInViaBranch.objCreatedDate, "Date"));
										verifyElementPresent(MLWalletCashInViaBranch.objStatus, getTextVal(MLWalletCashInViaBranch.objStatus, "Status"));
										verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo, getTextVal(MLWalletCashInViaBranch.objTransactionNo, "Transaction Number"));
										verifyElementPresent(MLWalletCashInViaBranch.objCrossBtn, "Cash In Branch Cross Button");
										logger.info("CIBR_TC_09, Cash In Via Branch QR Code UI validated");
										extentLoggerPass("CIBR_TC_09", "CIBR_TC_09, Cash In Via Branch QR Code UI validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchPendingTransaction_CIBR_TC_11() throws Exception {
									HeaderChildNode("Cash In Via Branch, If pending transaction Exists, Application directly navigates to previous transaction QR Code");
									waitTime(2000);
									changeNumberPage();
									clearField();
									mlWalletLogin(prop.getproperty("Branch_Verified"));
									waitTime(2000);
									if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
										verifyElementPresent(MLWalletCashInBank.objPendingStatus,getTextVal(MLWalletCashInBank.objPendingStatus,"Status"));
										String sStatus = getText(MLWalletCashInBank.objPendingStatus);
										String pendingStatus=sStatus.substring(2, 9);	
										String sExpectedStatus = "Pending";
										click(MLWalletCashInViaBranch.objCashInMenu, "Cash In");
										assertionValidation(pendingStatus,sExpectedStatus);	
										verifyElementPresent(MLWalletCashInViaBranch.objBranchName, "Cash In Options Page");
										click(MLWalletCashInViaBranch.objBranchName, "ML Branch");
										waitTime(4000);
										if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"))) {
											verifyElementPresent(MLWalletCashInViaBranch.objPHP, getTextVal(MLWalletCashInViaBranch.objPHP, "PHP"));
											verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate, getTextVal(MLWalletCashInViaBranch.objCreatedDate, "Date"));
											verifyElementPresent(MLWalletCashInViaBranch.objStatus, getTextVal(MLWalletCashInViaBranch.objStatus, "Status"));
											verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo, getTextVal(MLWalletCashInViaBranch.objTransactionNo, "Transaction Number"));
											verifyElementPresent(MLWalletCashInViaBranch.objCrossBtn, "Cash In Branch Cross Button");
											logger.info("CIBR_TC_11, Cash In Via Branch, If pending transaction Exists, Application directly navigates to previous transaction QR Code validated");
											extentLoggerPass("CIBR_TC_11", "CIBR_TC_11, Cash In Via Branch, If pending transaction Exists, Application directly navigates to previous transaction QR Code validated");
											System.out.println("-----------------------------------------------------------");
										}
									}
									getDriver().resetApp();
									
								}

								public void cashInViaBranchCancelBtnValidationOnCashInConfirmPopUp_CIBR_TC_12() throws Exception {
									HeaderChildNode("Cash In Via Branch Cancel Button validation on CashIn Confirm Popup");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch, getTextVal(MLWalletCashInViaBranch.objCashInToBranch, "Header"));
									verifyElementPresentAndClick(MLWalletCashInViaBranch.objCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objCancelTransactionBtn,"Button"));
									waitTime(2000);
									verifyElementPresentAndClick(MLWalletCashInViaBranch.objPopUpCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objPopUpCancelTransactionBtn,"Button"));
									waitTime(2000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch, getTextVal(MLWalletCashInViaBranch.objCashInToBranch, "Header"))){
										logger.info("CIBR_TC_12, Cash In Via Branch, On clicking Cancel Button on CashIn Confirm Popup Application Navigates to CashIn Page with Cancelled Status");
										extentLoggerPass("CIBR_TC_12", "CIBR_TC_12, Cash In Via Branch, On clicking Cancel Button on CashIn Confirm Popup Application Navigates to CashIn Page with Cancelled Status");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}
								
								
								public void cashInViaBranchTappingOutsideTheCashInConfirmationPopUp_CIBR_TC_13(String sAmount) throws Exception {
									ExtentReporter.HeaderChildNode("Cash In Via Branch, On tapping Outside the CashIn Confirmation Popup");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cancelPreviousTransactionAndContinue();
									cashInViaBranchEnterAmount(sAmount);
									waitTime(4000);
									verifyElementPresent(MLWalletCashInViaBranch.objCashInConfirmationPopup,getTextVal(MLWalletCashInViaBranch.objCashInConfirmationPopup,"Popup"));
									tapUsingCoordinates(500,1000);
									logger.info("Clicked OutSide the Dragon Pay Popup");
									waitTime(2000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objCashInConfirmationPopup,getTextVal(MLWalletCashInViaBranch.objCashInConfirmationPopup,"Popup"))){
										logger.info("CIBR_TC_13, Cash In Via Branch, On tapping Outside the CashIn Confirmation Popup, Popup doesn't closed");
										extentLoggerPass("CIBR_TC_13", "CIBR_TC_13, Cash In Via Branch, On tapping Outside the CashIn Confirmation Popup, Popup doesn't closed");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchGoBackBtnValidationOnCashInConfirmPopUp_CIBR_TC_14() throws Exception {
									HeaderChildNode("Cash In Via Branch Go Back Button validation on CashIn Confirm Popup");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cashInViaBranchEnterAmount("100");
									verifyElementPresentAndClick(MLWalletCashInViaBranch.objPopUpContinueButton,getTextVal(MLWalletCashInViaBranch.objPopUpContinueButton,"button"));
									waitTime(4000);
									enterOTP(prop.getproperty("Valid_OTP"));	
									waitTime(7000);
									verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch, getTextVal(MLWalletCashInViaBranch.objCashInToBranch, "Header"));
									verifyElementPresentAndClick(MLWalletCashInViaBranch.objCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objCancelTransactionBtn,"Button"));
									waitTime(3000);
									verifyElementPresentAndClick(MLWalletCashInViaBranch.objGoBackBtn,getTextVal(MLWalletCashInViaBranch.objGoBackBtn,"Button"));
									waitTime(2000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch, getTextVal(MLWalletCashInViaBranch.objCashInToBranch, "Header"))){
										logger.info("CIBR_TC_14, Cash In Via Branch, On clicking Go Back Button on CashIn Confirm Popup Application Navigates to CashIn Page with pending Status");
										extentLoggerPass("CIBR_TC_14", "CIBR_TC_14, Cash In Via Branch, On clicking Go Back Button on CashIn Confirm Popup Application Navigates to CashIn Page with pending Status");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}
								
								
								public void cashInViaBankTappingOutsideTheCancelTransactionConfirmationPopup_CIBR_TC_15() throws Exception {
								    HeaderChildNode("Cash In Via Branch, On tapping Outside the Cancel Transaction Confirmation Popup");
								    waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));	
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									enterOTP(prop.getproperty("Valid_OTP"));
									waitTime(5000);
									click(MLWalletCashInViaBranch.objCancelTransactionBtn, "Cancel Transaction");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objCancelTransactionPopup, getTextVal(MLWalletCashInViaBranch.objCancelTransactionPopup, "Pop Up"));
									tapUsingCoordinates(500,1000);
									logger.info("Clicked OutSide the Dragon Pay Popup");
									if(verifyElementPresent(MLWalletCashInViaBranch.objCancelTransactionPopup,getTextVal(MLWalletCashInViaBranch.objCancelTransactionPopup,"PopUp"))){
										logger.info("CIBR_TC_15, Cash In Via Branch, On tapping Outside the Cancel Transaction Confirmation Popup, Popup doesn't closed");
										extentLoggerPass("CIBR_TC_15", "CIBR_TC_15, Cash In Via Branch, On tapping Outside the Cancel Transaction Confirmation Popup, Popup doesn't closed");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchBuyerTierUser_CIBR_TC_16() throws Exception {
									HeaderChildNode("Cash In Via Branch Buyer Tier User");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Buyer_Tier"));
					                cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									waitTime(4000);
									enterOTP(prop.getproperty("Valid_OTP"));
									waitTime(7000);
									if (verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch, getTextVal(MLWalletCashInViaBranch.objCashInToBranch, "QR Page"))) {
										verifyElementPresent(MLWalletCashInViaBranch.objPHP, getTextVal(MLWalletCashInViaBranch.objPHP, "Amount"));
										verifyElementPresent(MLWalletCashInViaBranch.objTwentyFourHrsNoteMsg, getTextVal(MLWalletCashInViaBranch.objTwentyFourHrsNoteMsg, "Note Message"));
										
//										String sErrorMessage = getText(MLWalletCashInViaBranch.objMaxLimitTxt);
//										String ExpectedTxt = "Branch Cash-in not allowed. Please upgrade to a higher verification level to add more balance.";
//										assertionValidation(sErrorMessage, ExpectedTxt);
//										verifyElementPresent(MLWalletCashInViaBranch.objUpgradeNowBtn,getTextVal(MLWalletCashInViaBranch.objUpgradeNowBtn,"Button"));
										logger.info("CIBR_TC_16, Cash In Via Branch Buyer Tier User, Branch CashIn Not Allowed-Error message Validated");
										extentLoggerPass("CIBR_TC_16", "CIBR_TC_16, Cash In Via Branch Buyer Tier User, Branch CashIn Not Allowed-Error message Validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchSemiVerifiedTierUser_CIBR_TC_17() throws Exception {
									HeaderChildNode("Cash In Via Branch Semi verified Tier User");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Semi_Verified"));
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									enterOTP(prop.getproperty("Valid_OTP"));
									waitTime(10000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"))){
										verifyElementPresent(MLWalletCashInViaBranch.objPHP,getTextVal(MLWalletCashInViaBranch.objPHP,"PHP"));
										verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate,getTextVal(MLWalletCashInViaBranch.objCreatedDate,"Date"));
										verifyElementPresent(MLWalletCashInViaBranch.objStatus,getTextVal(MLWalletCashInViaBranch.objStatus,"Status"));
										verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo,getTextVal(MLWalletCashInViaBranch.objTransactionNo,"Transaction Number"));
										verifyElementPresentAndClick(MLWalletCashInViaBranch.objCrossBtn,"Cash In Branch Cross Button");
										verifyRecentTransaction2(prop.getproperty("Semi_Verified"));
										if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
											verifyElementPresent(MLWalletCashInBank.objPendingStatus,getTextVal(MLWalletCashInBank.objPendingStatus,"Status"));
											String sStatus = getText(MLWalletCashInBank.objPendingStatus);
											String pendingStatus=sStatus.substring(2, 9);	
											String sExpectedStatus = "Pending";
											assertionValidation(pendingStatus,sExpectedStatus);	
											logger.info("CIBR_TC_17, Cash In Via Branch Semi verified Tier User Validated");
											extentLoggerPass("CIBR_TC_17", "CIBR_TC_17, Cash In Via Branch Semi verified Tier User Validated");
											System.out.println("-----------------------------------------------------------");
										}
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchFullyVerifiedTierUser_CIBR_TC_18() throws Exception {
									HeaderChildNode("Cash In Via Branch Fully verified Tier User");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Fully_Verified"));
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
											getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									enterOTP(prop.getproperty("Valid_OTP"));
									waitTime(10000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"))){
										verifyElementPresent(MLWalletCashInViaBranch.objPHP,getTextVal(MLWalletCashInViaBranch.objPHP,"PHP"));
										verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate,getTextVal(MLWalletCashInViaBranch.objCreatedDate,"Date"));
										verifyElementPresent(MLWalletCashInViaBranch.objStatus,getTextVal(MLWalletCashInViaBranch.objStatus,"Status"));
										verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo,getTextVal(MLWalletCashInViaBranch.objTransactionNo,"Transaction Number"));
										verifyElementPresentAndClick(MLWalletCashInViaBranch.objCrossBtn,"Cash In Branch Cross Button");
										verifyRecentTransaction3(prop.getproperty("Fully_Verified"));
										if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
											verifyElementPresent(MLWalletCashInBank.objPendingStatus,getTextVal(MLWalletCashInBank.objPendingStatus,"Status"));
											String sStatus = getText(MLWalletCashInBank.objPendingStatus);
											String pendingStatus=sStatus.substring(2, 9);	
											String sExpectedStatus = "Pending";
											assertionValidation(pendingStatus,sExpectedStatus);	
											System.out.println("-----------------------------------------------------------");
											logger.info("CIBR_TC_18, Cash In Via Branch Fully verified Tier User Validated");
											extentLoggerPass("CIBR_TC_18", "CIBR_TC_18, Cash In Via Branch Fully verified Tier User Validated");
											System.out.println("-----------------------------------------------------------");
										}
									}
									getDriver().resetApp();
								}


								public void cashInViaBranchMaxTransactionBuyerTierUser_CIBR_TC_19() throws Exception {
									HeaderChildNode("Cash In Via Branch Max Transaction Buyer Tier User");
									waitTime(2000);
									changeNumberPage();
									clearField();
									maxTransactionLimitValidation(prop.getproperty("Buyer_Tier"));
									waitTime(5000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objBankMaxLimitTxt1,getTextVal(MLWalletCashInViaBranch.objBankMaxLimitTxt1,"Error Message"))) {
										String sErrorMsg = getText(MLWalletCashInViaBranch.objBankMaxLimitTxt1);
										String sExpectedErrorMsg = "The maximum Branch Cash-in per transaction set for your verification level is P20,000.00. Please try again.";
										assertionValidation(sErrorMsg, sExpectedErrorMsg);
										logger.info("CIBR_TC_19, Cash In Via Branch Max Transaction Buyer Tier User,Branch CashIn Not Allowed-Error message Validated");
										extentLoggerPass("CIBR_TC_19", "CIBR_TC_19, Cash In Via Branch Max Transaction Buyer Tier User,Branch CashIn Not Allowed-Error message Validated");
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchMaxTransactionSemiVerifiedTierUser_CIBR_TC_20() throws Exception {
									HeaderChildNode("Cash In Via Branch Max Transaction Limit Semi-verified Tier User");
									waitTime(2000);
									changeNumberPage();
									clearField();
									maxTransactionLimitValidation(prop.getproperty("Semi_Verified"));
									waitTime(5000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objBankMaxLimitTxt,getTextVal(MLWalletCashInViaBranch.objBankMaxLimitTxt,"Error Message"))) {
										String sErrorMsg = getText(MLWalletCashInViaBranch.objBankMaxLimitTxt);
										String sExpectedErrorMsg = "The maximum Branch Cash-in per transaction set for your verification level is P50,000.00. Please try again.";
										assertionValidation(sErrorMsg, sExpectedErrorMsg);
										logger.info("CIBR_TC_20, Cash In Via Branch Max Transaction Limit Semi-verified Tier User Validated");
										extentLoggerPass("CIBR_TC_20", "CIBR_TC_20, Cash In Via Branch Max Transaction Limit Semi-verified Tier User Validated");
									}
									getDriver().resetApp();
								}


								public void cashInViaBranchMaxTransactionFullyVerifiedTierUser_CIBR_TC_21() throws Exception {
									HeaderChildNode("Cash In Via Branch Max Transaction Limit Fully-verified Tier User");
									waitTime(2000);
									changeNumberPage();
									clearField();
									maxTransactionLimitValidation(prop.getproperty("Fully_verified"));
									waitTime(5000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objBankMaxLimitTxt,getTextVal(MLWalletCashInViaBranch.objBankMaxLimitTxt,"Error Message"))) {
										String sErrorMsg = getText(MLWalletCashInViaBranch.objBankMaxLimitTxt);
										String sExpectedErrorMsg = "The maximum Branch Cash-in per transaction set for your verification level is P50,000.00. Please try again.";
										assertionValidation(sErrorMsg, sExpectedErrorMsg);
										logger.info("CIBR_TC_21, Cash In Via Branch Max Transaction Limit Fully-verified Tier User Validated");
										extentLoggerPass("CIBR_TC_21", "CIBR_TC_21, Cash In Via Branch Max Transaction Limit Fully-verified Tier User Validated");
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchTransactionDetailsUIValidation_CIBR_TC_22() throws Exception {
									HeaderChildNode("Cash In Via Branch Transaction Details UI Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									waitTime(2000);
									enterOTP(prop.getproperty("Valid_OTP"));
									waitTime(10000);
									verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"));
									verifyElementPresent(MLWalletCashInViaBranch.objPHP, getTextVal(MLWalletCashInViaBranch.objPHP, "PHP"));
									verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate, getTextVal(MLWalletCashInViaBranch.objCreatedDate, "Date"));
									verifyElementPresent(MLWalletCashInViaBranch.objStatus, getTextVal(MLWalletCashInViaBranch.objStatus, "Status"));
									verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo, getTextVal(MLWalletCashInViaBranch.objTransactionNo, "Transaction Number"));
									verifyElementPresentAndClick(MLWalletCashInViaBranch.objCrossBtn, "Cash In Branch Cross Button");
									verifyRecentTransaction2(prop.getproperty("Branch_Verified"));
									verifyElementPresentAndClick(MLWalletCashInBank.objCashInTransaction, getTextVal(MLWalletCashInBank.objCashInTransaction, "Transaction"));
									waitTime(2000);
									if (verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objTransactionDetails, "Page"))) {
										verifyElementPresent(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, getTextVal(MLWalletTransactionHistoryPage.objReferenceNumberInTransactionDetails, "Reference Number"));
										verifyElementPresent(MLWalletTransactionHistoryPage.objDate, getTextVal(MLWalletTransactionHistoryPage.objDate, "Date"));
										verifyElementPresent(MLWalletTransactionHistoryPage.objReceiverMobileNo, getTextVal(MLWalletTransactionHistoryPage.objReceiverMobileNo, "Receiver's Mobile Number"));
										verifyElementPresent(MLWalletTransactionHistoryPage.objPaymentMethod, getTextVal(MLWalletTransactionHistoryPage.objPaymentMethod, "Payment Method"));
										verifyElementPresent(MLWalletTransactionHistoryPage.objTransactionType, getTextVal(MLWalletTransactionHistoryPage.objTransactionType, "Payment Method"));
										verifyElementPresent(MLWalletTransactionHistoryPage.objAmount, getTextVal(MLWalletTransactionHistoryPage.objAmount, "Amount"));
										verifyElementPresent(MLWalletTransactionHistoryPage.objServiceFee, getTextVal(MLWalletTransactionHistoryPage.objServiceFee, "Service Fee"));
										verifyElementPresent(MLWalletTransactionHistoryPage.objTotalCashIn, getTextVal(MLWalletTransactionHistoryPage.objTotalCashIn, "Total Cash In"));
										logger.info("CIBR_TC_22,Cash In Via Branch Transaction Details UI Validated");
										extentLoggerPass("CIBR_TC_22", "'CIBR_TC_22',Cash In Via Branch Transaction Details UI Validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchTransactionPendingStatusValidation_CIBR_TC_23() throws Exception {
									HeaderChildNode("Cash In Via Branch Transaction Pending status Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									waitTime(2000);
									enterOTP(prop.getproperty("Valid_OTP"));
									waitTime(10000);
									verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"));
									verifyElementPresent(MLWalletCashInViaBranch.objPHP,getTextVal(MLWalletCashInViaBranch.objPHP,"PHP"));
									verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate,getTextVal(MLWalletCashInViaBranch.objCreatedDate,"Date"));
									verifyElementPresent(MLWalletCashInViaBranch.objStatus,getTextVal(MLWalletCashInViaBranch.objStatus,"Status"));
									verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo,getTextVal(MLWalletCashInViaBranch.objTransactionNo,"Transaction Number"));
									verifyElementPresentAndClick(MLWalletCashInViaBranch.objCrossBtn,"Cash In Branch Cross Button");
									verifyRecentTransaction2(prop.getproperty("Branch_Verified"));
									if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
										verifyElementPresent(MLWalletCashInBank.objPendingStatus,getTextVal(MLWalletCashInBank.objPendingStatus,"Status"));
										String sStatus = getText(MLWalletCashInBank.objPendingStatus);
										String pendingStatus=sStatus.substring(2, 9);	
										String sExpectedStatus = "Pending";
										assertionValidation(pendingStatus,sExpectedStatus);	
										logger.info("CIBR_TC_23,Cash In Via Branch Transaction Pending status Validated");
										extentLoggerPass("CIBR_TC_23", "'CIBR_TC_23',Cash In Via Branch Transaction Pending status Validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchTransactionCancelledStatusValidation_CIBR_TC_26() throws Exception {
									HeaderChildNode("Cash In Via Branch Transaction Cancelled Status Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cashInViaBranchEnterAmount("100");
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									waitTime(2000);
									enterOTP(prop.getproperty("Valid_OTP"));
									waitTime(10000);
									verifyElementPresentAndClick(MLWalletCashInViaBranch.objCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objCancelTransactionBtn,"Button"));
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objCancelTransactionPopup,getTextVal(MLWalletCashInViaBranch.objCancelTransactionPopup,"PopUp"));
									verifyElementPresentAndClick(MLWalletCashInViaBranch.objPopUpCancelTransactionBtn,getTextVal(MLWalletCashInViaBranch.objPopUpCancelTransactionBtn,"Button"));
									waitTime(2000);
									verifyElementPresentAndClick(MLWalletCashInViaBranch.objBackToHomeBtn, getTextVal(MLWalletCashInViaBranch.objBackToHomeBtn, "Button"));
									waitTime(2000);
									verifyRecentTransaction2(prop.getproperty("Branch_Verified"));
									if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
										verifyElementPresent(MLWalletCashInBank.objCancelStatus,getTextVal(MLWalletCashInBank.objCancelStatus,"Status"));
										String sStatus = getText(MLWalletCashInBank.objCancelStatus);
										String pendingStatus=sStatus.substring(2, 10);	
										String sExpectedStatus = "Cancelled";
										System.out.println(sExpectedStatus);
										assertionValidation(pendingStatus,sExpectedStatus);	
										logger.info("'CIBR_TC_26',Cash In Via Branch Transaction Cancelled Status Validated");
										extentLoggerPass("'CIBR_TC_26","'CIBR_TC_26', Cash In Via Branch Transaction Cancelled Status Validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchTransactionValidationAfterMinimizingApp_CIBR_TC_36() throws Exception {
									HeaderChildNode("Cash In Via Branch Transaction Validation After Minimizing App");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
											getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									enterOTP(prop.getproperty("Valid_OTP"));
									getDriver().runAppInBackground(Duration.ofSeconds(5));
									logger.info("Application relaunched after 5 Seconds");
									waitTime(5000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Bank Page"))){
										logger.info("CIBR_TC_36, Cash In Via Branch Transaction Validation After Minimizing App Validated");
										extentLoggerPass("CIBR_TC_36", "CIBR_TC_36, Cash In Via Branch Transaction Validation After Minimizing App Validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								} 
								
								public void cashInViaBranchAmountFieldValidation_CIBR_TC_42() throws Exception {
									HeaderChildNode("Cash In Via Branch, Amount Field with more than 2 decimals Validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cancelPreviousTransactionAndContinue();
									cashInViaBranchEnterAmount("100.123");
									waitTime(2000);
									if (verifyElementPresent(MLWalletCashInViaBranch.objDecimalAmountErrMsg, getTextVal(MLWalletCashInViaBranch.objDecimalAmountErrMsg, "Pop Message"))) {
										String sMinimumTransactionPopupMsg = getText(MLWalletCashInViaBranch.objDecimalAmountErrMsg);
										String sExpectedPopupMsg = "The amount must be limited to 2 decimal places";
										assertionValidation(sMinimumTransactionPopupMsg, sExpectedPopupMsg);
										logger.info("CIBR_TC_42, Cash In Via Branch, Amount Field with more than 2 decimals Error Msg validated");
										extentLoggerPass("CIBR_TC_42", "CIBR_TC_42, Cash In Via Branch, Amount Field with more than 2 decimals Error Msg validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}


								public void cashInViaBranchTransactionWithValidMLPin_CIBR_TC_43() throws Exception {
									HeaderChildNode("Cash In Via Branch Transaction With Valid ML Pin");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cancelPreviousTransactionAndContinue();
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									waitTime(2000);
									enterOTP(prop.getproperty("Valid_OTP"));
									waitTime(10000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch,getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Header"))){
										verifyElementPresent(MLWalletCashInViaBranch.objPHP,getTextVal(MLWalletCashInViaBranch.objPHP,"PHP"));
										verifyElementPresent(MLWalletCashInViaBranch.objCreatedDate,getTextVal(MLWalletCashInViaBranch.objCreatedDate,"Date"));
										verifyElementPresent(MLWalletCashInViaBranch.objStatus,getTextVal(MLWalletCashInViaBranch.objStatus,"Status"));
										verifyElementPresent(MLWalletCashInViaBranch.objTransactionNo,getTextVal(MLWalletCashInViaBranch.objTransactionNo,"Transaction Number"));
										verifyElementPresentAndClick(MLWalletCashInViaBranch.objCrossBtn,"Cash In Branch Cross Button");
										verifyRecentTransaction2(prop.getproperty("Branch_Verified"));
										if(verifyElementPresent(MLWalletCashInBank.objCashInTransaction,getTextVal(MLWalletCashInBank.objCashInTransaction,"Transaction"))) {
											verifyElementPresent(MLWalletCashInBank.objPendingStatus,getTextVal(MLWalletCashInBank.objPendingStatus,"Status"));
											String sStatus = getText(MLWalletCashInBank.objPendingStatus);
											String pendingStatus=sStatus.substring(2, 9);	
											String sExpectedStatus = "Pending";
											assertionValidation(pendingStatus,sExpectedStatus);	
											logger.info("'CIBR_TC_43', Cash In Via Branch Transaction With Valid ML Pin validated");
											extentLoggerPass("'CIBR_TC_43", "'CIBR_TC_43', Cash In Via Branch Transaction With Valid ML Pin validated");
											System.out.println("-----------------------------------------------------------");
										}
									}
									getDriver().resetApp();
								}


								public void cashInViaBranchTransactionWithInValidMLPin_CIBR_TC_44() throws Exception {
									HeaderChildNode("Cash In Via Branch Transaction With InValid ML Pin");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cancelPreviousTransactionAndContinue();
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
											getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									waitTime(5000);
									handleMpin("1234", "Entered Invalid Mpin");
									waitTime(2000);
									if (verifyElementPresent(MLWalletCashInViaBranch.objInvalidPINMsg, getTextVal(MLWalletCashInViaBranch.objInvalidPINMsg, "Message"))) {
										String sActualErrorMsg = getText(MLWalletCashInViaBranch.objInvalidPINMsg);
										String sExceptedErrorMsg = "You have entered an invalid PIN. Please try again.";
										assertionValidation(sActualErrorMsg,sExceptedErrorMsg);
										logger.info("CIBR_TC_44, Cash In Via Branch Transaction With Invalid ML Pin validated");
										extentLoggerPass("CIBR_TC_44", "CIBR_TC_44, Cash In Via Branch Transaction With Invalid ML Pin validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}
								
								
								public void cashInViaBranchOTPPopupValidation_CIBR_TC_50() throws Exception {
									HeaderChildNode("Cash In Via Branch OTP Popup validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cancelPreviousTransactionAndContinue();
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
											getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									waitTime(3000);
									if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
										verifyElementPresent(MLWalletLoginPage.objOTP,getTextVal(MLWalletLoginPage.objOTP,"One Time Pin"));
										logger.info("CIBR_TC_50, Cash In Via Branch, InApp OTP popup validated");
										extentLoggerPass("CIBR_TC_50", "CIBR_TC_50, Cash In Via Branch, InApp OTP popup validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchTransactionInAppOTPPopupUIValidation_CIBR_TC_51() throws Exception {
									HeaderChildNode("Cash In Via Branch Transaction InApp OTP Popup validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cancelPreviousTransactionAndContinue();
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
											getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									waitTime(5000);
									if (verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"))) {
										verifyElementPresent(MLWalletLoginPage.objOTP,getTextVal(MLWalletLoginPage.objOTP,"One Time Pin"));	
										verifyElementPresent(MLWalletLoginPage.objOtpContineBtn,getTextVal(MLWalletLoginPage.objOtpContineBtn,"Button"));
										verifyElementPresent(MLWalletLoginPage.objCancelBtn,getTextVal(MLWalletLoginPage.objCancelBtn,"Button"));
										logger.info("CIBR_TC_51, Cash In Via Branch Transaction InApp OTP Popup validated");
										extentLoggerPass("CIBR_TC_51", "CIBR_TC_51, Cash In Via Branch Transaction InApp OTP Popup validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}


								public void cashInViaBranchTransactionNewOTPAfterSixtySecondsValidation_CIBR_TC_52() throws Exception {
									HeaderChildNode("Cash In Via Branch Transaction New OTP got generated After Sixty Seconds validation");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cancelPreviousTransactionAndContinue();
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
											getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									waitTime(5000);
									verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"));
									waitTime(2000);
									if(verifyElementPresent(MLWalletLoginPage.objOTP,getTextVal(MLWalletLoginPage.objOTP,"One Time Pin"))){
										String sGeneratedOTP = getText(MLWalletLoginPage.objOTP);
										waitTime(70000);
										String sNewlyGeneratedOTPAfterSixtySeconds = getText(MLWalletLoginPage.objOTP);
										assertNotEquals(sGeneratedOTP,sNewlyGeneratedOTPAfterSixtySeconds);
										logger.info("CIBR_TC_52, Cash In Via Branch Transaction New OTP got generated After Sixty Seconds is validated");
										extentLoggerPass("CIBR_TC_52", "CIBR_TC_52, Cash In Via Branch Transaction New OTP got generated After Sixty Seconds is validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}


								public void cashInViaBranchTransactionOTPCancelBtnFunctionality_CIBR_TC_53() throws Exception {
									HeaderChildNode("Cash In Via Branch Transaction OTP Cancel Button Functionality");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cancelPreviousTransactionAndContinue();
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
											getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									waitTime(5000);
									verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"));
									verifyElementPresentAndClick(MLWalletLoginPage.objCancelBtn,getTextVal(MLWalletLoginPage.objCancelBtn,"Button"));
									waitTime(2000);
									if(verifyElementPresent(MLWalletCashInViaBranch.objBranchCashIn,getTextVal(MLWalletCashInViaBranch.objBranchCashIn,"Page"))){
										logger.info("CIBR_TC_53, Cash In Via Branch Transaction, After clicking on Cancel in One time pin popup App navigates back to Branch cash In Page is validated");
										extentLoggerPass("CIBR_TC_53", "CIBR_TC_53, Cash In Via Branch Transaction, After clicking on Cancel in One time pin popup App navigates back to Branch cash In Page is validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}

								public void cashInViaBranchOTPContinueBtnFunctionality_CIBR_TC_54() throws Exception {
									HeaderChildNode("Cash In Via Branch Transaction OTP Continue Button Functionality");
									waitTime(2000);
									changeNumberPage();
									clearField();
									cashInViaBranchNavigation(prop.getproperty("Branch_Verified"));
									cancelPreviousTransactionAndContinue();
									cashInViaBranchEnterAmount("100");
									waitTime(2000);
									verifyElementPresent(MLWalletCashInViaBranch.objWarningPopup,
											getTextVal(MLWalletCashInViaBranch.objWarningPopup, "Pop Up"));
									click(MLWalletCashInViaBranch.objContinueButton, "Continue Button");
									waitTime(5000);
									verifyElementPresent(MLWalletLoginPage.objOneTimePin, getTextVal(MLWalletLoginPage.objOneTimePin, "Page"));
									verifyElementPresentAndClick(MLWalletLoginPage.objOtpContineBtn, getTextVal(MLWalletLoginPage.objOtpContineBtn, "Button"));
									waitTime(2000);
									if (verifyElementPresent(MLWalletCashInViaBranch.objCashInToBranch, getTextVal(MLWalletCashInViaBranch.objCashInToBranch,"Page"))) {
										logger.info("CIBR_TC_54, Cash In Via Branch Transaction, After clicking on Continue in One time pin popup App navigates to Cash In To Branch Page is validated");
										extentLoggerPass("CIBR_TC_54", "CIBR_TC_54, Cash In Via Branch Transaction, After clicking on Continue in One time pin popup App navigates to Cash In To Branch page is validated");
										System.out.println("-----------------------------------------------------------");
									}
									getDriver().resetApp();
								}



							
							


						

					
					
}