package com.mlwallet.ios_scripts;

import org.testng.annotations.Test;
import com.business.mlwallet.MLWalletiOSBusinessLogic;
import com.gmail.GmailQuickstart;
import com.utility.Utilities;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

public class MLWalletLoginScripts {

	private MLWalletiOSBusinessLogic MLWalletBusinessLogic;
    public static String link;
    GmailQuickstart quick=new GmailQuickstart();

//	@BeforeTest
   @BeforeSuite
   // @Test
	public void init() throws InterruptedException {
		MLWalletBusinessLogic = new MLWalletiOSBusinessLogic("mlwallet");
//		HashMap<String, String> hm =quick.getGmailData("subject:Fwd: Version 11.0.62 (282) of QA - ML Money iOS for iOS Available");
//		link=(hm.get("link").trim());
//		System.out.println(link);
		//Thread.sleep(5000);
		//        DriverManager.getAppiumDriver().findElement(By.xpath("//*[@text='Continue as collabera']")).click();
		//        DriverManager.getAppiumDriver().findElement(By.xpath("//*[@text='No, thanks']")).click();
//
//		 Thread.sleep(2000);
//		 WebElement ele=Utilities.getDriver().findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"Search or type URL\"])[2]"));
//		 ele.click();
//		 ele.sendKeys(link);
//		 Thread.sleep(2000);
//		 ele.sendKeys(Keys.ENTER);
//
////		String enter="adb shell input keyevent KEYCODE_ENTER";
////		 Runtime.getRuntime().exec(enter);
//
//		 Utilities.getDriver().findElement(By.xpath("//*[@name='Google']")).click();
//		Thread.sleep(3000);
//		Utilities.getDriver().findElement(By.xpath("//*[@name='collabera ml']")).click();
//		Utilities.getDriver().findElement(By.xpath("//*[@name='collabera ml']")).click();
//
//		Thread.sleep(7000);
//		Utilities.getDriver().findElement(By.xpath("//*[@name='DOWNLOAD']")).click();

	}

	
	@Test(priority = 1)
	public void LogInScenarioWithValidMobNumber() throws Exception { // MLWalletBusinessLogic.gmail();
		MLWalletBusinessLogic.LogInScenarioWithValidMobNumber_Lgn_TC_01();
	}

	@Test(priority = 2)
	public void LogInScenarioWithInvalidMobNumber() throws Exception {
		MLWalletBusinessLogic.LogInScenarioWithInvalidMobNumber_Lgn_TC_02();
	}

	@Test(priority = 3)
	public void LogInScenarioWithValidOTP_Lgn_TC_03() throws Exception {
		MLWalletBusinessLogic.LogInScenarioWithValidOTP_Lgn_TC_03();
	}

	@Test(priority = 4)
	public void appLaunch_Lgn_TC_05() throws Exception {
		MLWalletBusinessLogic.appLaunch_Lgn_TC_05();
	}

	@Test(priority = 5)
	public void loginPageUIValidation_Lgn_TC_06() throws Exception {
		MLWalletBusinessLogic.loginPageUIValidation_Lgn_TC_06();
	}

	@Test(priority = 6)
	public void loginTroubleSigningIn_Lgn_TC_07() throws Exception {
		MLWalletBusinessLogic.loginTroubleSigningIn_Lgn_TC_07();
	}

	@Test(priority = 7)
	public void loginCreateOne_Lgn_TC_08() throws Exception {
		MLWalletBusinessLogic.loginCreateOne_Lgn_TC_08();
	}

	@Test(priority = 8)
	public void loginBranchLocator_Lgn_TC_09() throws Exception {
		MLWalletBusinessLogic.loginBranchLocator_Lgn_TC_09();
	}

	@Test(priority = 9)
	public void loginOTPPageUIValidation_Lgn_TC_10() throws Exception {
		MLWalletBusinessLogic.loginOTPPageUIValidation_Lgn_TC_10();
	}
	 

//====================================================================================================//

	@Test(priority = 10)
	public void loginWithExistingMobileNumber_Lgn_TC_17() throws Exception {
		MLWalletBusinessLogic.loginWithExistingMobileNumber_Lgn_TC_17();
	}

	@Test(priority = 11)
	public void loginMPinPageUIValidation_Lgn_TC_18() throws Exception {
		MLWalletBusinessLogic.loginMPinPageUIValidation_Lgn_TC_18();
	}

	@Test(priority = 12)
	public void loginInAppOTPNavigation_Lgn_TC_22() throws Exception {
		MLWalletBusinessLogic.loginInAppOTPNavigation_Lgn_TC_22();
	}

	@Test(priority = 13)
	public void loginInAppOTPPageUIValidation_Lgn_TC_23() throws Exception {
		MLWalletBusinessLogic.loginInAppOTPPageUIValidation_Lgn_TC_23();
	}

	@Test(priority = 14)
	public void loginNewOTPAfterSixtySecondsValidation_Lgn_TC_24() throws Exception {
		MLWalletBusinessLogic.loginNewOTPAfterSixtySecondsValidation_Lgn_TC_24();
	}

	@Test(priority = 15)
	public void loginOTPCancelBtnFunctionality_Lgn_TC_25() throws Exception {
		MLWalletBusinessLogic.loginOTPCancelBtnFunctionality_Lgn_TC_25();
	}

	@Test(priority = 16)
	public void loginOTPContinueBtnFunctionality_Lgn_TC_26() throws Exception {
		MLWalletBusinessLogic.loginOTPContinueBtnFunctionality_Lgn_TC_26();
	}
	
}
