package com.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import com.driverInstance.DriverInstance;
import com.extent.ExtentReporter;
import com.google.common.collect.Ordering;
import org.openqa.selenium.Point;
import com.propertyfilereader.PropertyFileReader;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import java.time.Duration;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class Utilities extends ExtentReporter {

	/** Time out */
	private int timeout;

	/** Retry Count */
	private int retryCount;

	public static ExtentReporter extent = new ExtentReporter();

	@SuppressWarnings("rawtypes")
	public TouchAction touchAction;

	private  SoftAssert softAssert = new SoftAssert();

	public static boolean relaunch = false;

	public static String setPlatform = "";

	/** The Constant logger. */
	static LoggingUtils logger = new LoggingUtils();

	/** The Android driver. */
	public AndroidDriver<AndroidElement> androidDriver;

	/** window handler */
	static ArrayList<String> win = new ArrayList<>();

	/** The Android driver. */
	public IOSDriver<WebElement> iOSDriver;

	public int getTimeout() {
		return this.timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public static AppiumDriver<WebElement> getDriver() {
		return DriverInstance.tlDriver.get();
	}

	protected static WebDriver getWebDriver() {
		return DriverInstance.tlWebDriver.get();
	}

	public String getPlatform() {
		return DriverInstance.getPlatform();
	}

	public void setPlatform(String Platform) {
		DriverInstance.setPlatfrom(Platform);
	}
	
//	public String buildversion() {
//		return BuildVersion;
//	}

	public void init() {
		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
	}

	static WebDriverWait wait;

	public static JavascriptExecutor js;

	public void initDriver() {
		if (getPlatform().equals("Web")) {
			wait = new WebDriverWait(getWebDriver(), getTimeout());
			js = (JavascriptExecutor) getWebDriver();
		} else if (getPlatform().equals("Android") || getPlatform().equals("MLWallet_iOS")) {
		//	System.out.println("::::::::::::::::::::::::::::Driver : "+getDriver());
			wait = new WebDriverWait(getDriver(), getTimeout());
			js = (JavascriptExecutor) getDriver();
		}
	}

//	String RequiredBuild=getParameterFromXML("BuildVersion");
//	String Release=getParameterFromXML("release");
//	String BuildVersion=RequiredBuild + "( "+Release+" )";
	
	
	
	public boolean JSClick(By byLocator, String text) {
		try {
			js.executeScript("arguments[0].click();", findElement(byLocator));
			logger.info("" + text + " " + " is clicked");
			extent.extentLoggerPass("checkElementPresent", "" + text + " is clicked");
			return true;
		} catch (Exception e) {
			logger.error(text + " " + " is not clicked");
			extent.extentLoggerFail("checkElementNotPresent", "" + text + " is not clicked");
			screencapture();
			return false;
		}
	}

	public WebElement findElement(By byLocator) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(),5);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		return element;
	}

	public void setWifiConnectionToONOFF(String Value) {
		try {
			if (Value.equalsIgnoreCase("On")) {
				System.out.println("Switching On Wifi");
				String cmd = "adb shell svc wifi enable";
				Runtime.getRuntime().exec(cmd);
				waitTime(5000);
				logger.info("Wifi Data toggle is Switched On");
				extent.extentLoggerPass("Wifi Toggle", "Wifi Data toggle is Switched On");
			} else if (Value.equalsIgnoreCase("Off")) {
				System.out.println("Switching Off Wifi");
				String cmd = "adb shell svc wifi disable";
				Runtime.getRuntime().exec(cmd);
				waitTime(3000);
				logger.info("Wifi Data toggle is Switched Off");
				extent.extentLoggerPass("Wifi Toggle", "Wifi Data toggle is Switched Off");
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * wait until element is displayed.
	 *
	 * @param webElement the by locator
	 * @return true, if successful
	 */
	public static boolean waitForElementDisplayed(By byLocator, int iTimeOut) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	
	public void waitForElementNotDisplayed(By byLocator) {
		for(int i=0;i<=100;i++){
			if(verifyIsElementDisplayed(byLocator, "Object")){
				System.out.println("Object is displayed");
			}else{
				System.out.println("Object not displayed");
				break;				
			}
		}
	}
	
	
	/**
	 * Check element not present.
	 *
	 * @param byLocator the by locator
	 * @return true, if successful
	 */
	public boolean verifyElementNotPresent(By byLocator, int iTimeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), iTimeOut);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
			return false;
		} catch (NoSuchElementException e) {
			return true;
		}
	}

	/**
	 * Check element present.
	 *
	 * @param byLocator the by locator
	 * @return true, if successful
	 */
	public boolean verifyElementPresent(By byLocator, String validationtext) throws Exception {

		try {
			WebElement element = findElement(byLocator);
			softAssert.assertEquals(element.isDisplayed(), true, "" + validationtext + " " + " is displayed");
			logger.info(validationtext + " is displayed");
			extent.extentLoggerPass("checkElementPresent", validationtext + " is displayed");
			return true;
		} catch (Exception e) {
//			softAssert.assertEquals(false, true, validationtext + " " + " is not displayed");
//			softAssert.assertAll();
			logger.info(validationtext + " is not displayed");
			extent.extentLogger("checkElementPresent", validationtext + " is not displayed");
			return false;
		}
	}
	
	
//	public boolean verifyElementPresent(By byLocator, String validationtext) throws Exception {
//		try {
//			List<WebElement> element = getDriver().findElements(byLocator);
//			if(element.size()>0)
//			{
//				softAssert.assertEquals(element,validationtext);
//				logger.info(validationtext + " is displayed");
//				extent.extentLoggerPass("checkElementPresent", validationtext + " is displayed");	
//			}			
//			return true;
//		} catch (Exception e) {
////			softAssert.assertEquals(false, true, validationtext + " " + " is not displayed");
////			softAssert.assertAll();
//			logger.info(validationtext + " is not displayed");
//			extent.extentLogger("checkElementPresent", validationtext + " is not displayed");
//			return false;
//		}
//	}
	
	

	public boolean verifyElementExist(By byLocator, String str) throws Exception {
		try {
			WebElement element = findElement(byLocator);
			if (element.isDisplayed()) {
				extent.extentLogger("checkElementPresent", "" + str + " is displayed");
				logger.info("" + str + " is displayed");
				return true;
			}
		} catch (Exception e) {
			extent.extentLogger("checkElementPresent", "" + str + " is not displayed");
			logger.info(str + " is not displayed");
			return false;
		}
		return false;
	}

	/**
	 * boolean return type for conditions
	 * 
	 * @param byLocator
	 * @return
	 * @throws Exception
	 */
	public boolean verifyElementDisplayed(By byLocator) throws Exception {
		try {
			WebElement element = findElement(byLocator);
			if (element.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public boolean checkElementExist(By byLocator, String str) throws Exception {

		try {
			getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			List<WebElement> list = getDriver().findElements(byLocator);
			getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (list.size() == 0) {
				extent.extentLogger("checkElementPresent", "" + str + " is not displayed");
				logger.info("" + str + " is not displayed");
				return false;
			} else {
				extent.extentLogger("checkElementPresent", "" + str + " is displayed");
				logger.info("" + str + " is displayed");
				return list.get(0).isDisplayed();
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Check element present and click.
	 *
	 * @param byLocator the by locator
	 * @return true, if successful
	 */
	public boolean verifyElementPresentAndClick(By byLocator, String validationtext) throws Exception {
	
		try {
			WebElement element = getDriver().findElement(byLocator);
			softAssert.assertEquals(element.isDisplayed(), true, "" + validationtext + " " + " is displayed");
			logger.info("" + validationtext + " " + "is displayed");
			extent.extentLogger("checkElementPresent", "" + validationtext + " is displayed");
			findElement(byLocator).click();
			logger.info("Clicked on " + validationtext);
			extent.extentLoggerPass("click", "Clicked on " + validationtext);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			softAssert.assertEquals(false, true, "Element" + validationtext + " " + " is not visible");
			logger.info("Element " + validationtext + " " + " is not visible");
			extent.extentLoggerFail("checkElementPresent", "" + validationtext + " is not displayed");
			screencapture();
//			softAssert.assertAll();
			return false;
		}
	}

	public boolean clickElementWithWebLocator(By locator) throws Exception {
		try {
			getWebDriver().findElement(locator).click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param byLocator
	 * @return true or false
	 */
	public boolean checkcondition(By byLocator) throws Exception {
		boolean iselementPresent = false;
		try {
			iselementPresent = getDriver().findElement(byLocator).isDisplayed();
			iselementPresent = true;
		} catch (Exception e) {
			iselementPresent = false;
		}
		return iselementPresent;
	}

	/**
	 * Click on a web element.
	 * 
	 * @param byLocator the by locator
	 * 
	 */
	public void click(By byLocator, String validationtext) throws Exception {
		try {
			WebElement element =  getDriver().findElement(byLocator);
			element.click();
			logger.info("Clicked on " + validationtext);
			extent.extentLoggerPass("click", "Clicked on " + validationtext);
		} catch (Exception e) {
			logger.info("Failed to Click on " + validationtext);
			extent.extentLoggerFail("click", "Failed to click on " + validationtext);
		}
	}

	/**
	 * clear the text field
	 * 
	 * @param byLocator
	 */
	public void clearField(By byLocator, String text) {
		try {
			getDriver().findElement(byLocator).clear();
			logger.info("Cleared the text in : " + text);
			extent.extentLogger("clear text", "Cleared the text in : " + text);
		} catch (Exception e) {
			logger.info("Failed to Clear the text in : " + text);
			extent.extentLogger("clear", "Failed to clear text : " + text);
		}
	}
//webelement emailplaceholder=getdriver().findelment(by.xpath("//*[@placeholder='  Email address']"))
	/**
	 * Get Text from an object
	 * 
	 * @param byLocator
	 * @return
	 * @throws Exception
	 */
	public String getText(By byLocator) throws Exception {
		try {
		String Value = null;
		WebElement element = findElement(byLocator);
		Value = element.getText();
		return Value;
		
		} catch (Exception e) {
			//logger.error(e);
			return "";
			
		}
		
	}

	@SuppressWarnings({ "rawtypes" })
	public String OTPNotification() {
		HeaderChildNode("Fetching Otp From Notification");
		waitTime(2000);
		getDriver().context("NATIVE_APP");
//		touchAction = new TouchAction(getDriver());
//		touchAction.press(PointOption.point(500, 0))
//		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(4000)))
//		.moveTo(PointOption.point(1500, 500)).release().perform();
//		waitTime(8000);
		AndroidDriver driver = (AndroidDriver) getDriver();
		driver.openNotifications();
		waitTime(3000);
		List<WebElement> allnotifications = getDriver()
				.findElements(By.xpath("(//*[@resource-id='android:id/message_text'])[1]"));
		System.out.println("Size : " + allnotifications.size());
		String Otp = null;
		for (WebElement webElement : allnotifications) {
			Otp = webElement.getText();
			System.out.println("Get Text : " + webElement.getText());
			if (webElement.getText().contains("something")) {
				System.out.println("Notification found");
				break;
			}
		}
		Back(1);
		getDriver().context("WEBVIEW_1");
		return Otp;
	}

	public boolean verifyElementIsNotDisplayed(By by) {
		try {
			getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			return getDriver().findElements(by).isEmpty();
		} catch (Exception e) {
			getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("CATCH - FALSE");
			return false;
		}
	}

	public static boolean verifyIsElementDisplayed(By by) {
		getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		List<WebElement> list = getDriver().findElements(by);
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (list.size() == 0) {
			return false;
		} else {
			return list.get(0).isDisplayed();
		}
	}

	public String GeneratingRandomString(int n) {
		{

			String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
			StringBuilder sb = new StringBuilder(n);
			for (int i = 0; i < n; i++) {
				int index = (int) (AlphaNumericString.length() * Math.random());

				sb.append(AlphaNumericString.charAt(index));
			}
			return sb.toString();
		}
	}
	
	
	public static boolean verifyIsElementDisplayed(By by, String validationtext) {
		getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		List<WebElement> list = getDriver().findElements(by);
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if (list.size() == 0) {
			logger.info("Element " + validationtext + " " + " is not displayed");
			extent.extentLogger("checkElementPresent", "" + validationtext + " is not displayed");
			return false;
		} else {
			logger.info("" + validationtext + " " + "is displayed");
			extent.extentLogger("checkElementPresent", "" + validationtext + " is displayed");
			return list.get(0).isDisplayed();
		}
	}

	public boolean checkElementExist(By byLocator) throws Exception {
		try {
			WebElement element = findElement(byLocator);
			if (element.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * Kill or start an application using activity
	 * 
	 * @param command  to START or KILL an application
	 * @param activity Start an application by passing the activity
	 */
	public void adbStartKill(String command, String activity) {
		String cmd;
		try {
			if (command.equalsIgnoreCase("START")) {
				cmd = "adb shell am start -n" + " " + activity;
				Runtime.getRuntime().exec(cmd);
				logger.info("Started the activity" + cmd);
				extent.extentLogger("adbStart", "Started the activity" + cmd);
			} else if (command.equalsIgnoreCase("KILL")) {
				cmd = "adb shell am force-stop" + " " + activity;
				Runtime.getRuntime().exec(cmd);
				logger.info("Executed the App switch");
				extent.extentLogger("adbKill", "Executed the App switch");
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * @return true if keyboard is displayed
	 * @throws IOException
	 */
	public boolean checkKeyboardDisplayed() throws IOException {
		boolean mInputShown = false;
		try {
			String cmd = "adb shell dumpsys input_method | grep mInputShown";
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String outputText = "";
			while ((outputText = br.readLine()) != null) {
				if (!outputText.trim().equals("")) {
					String[] output = outputText.split(" ");
					String[] value = output[output.length - 1].split("=");
					String keyFlag = value[1];
					if (keyFlag.equalsIgnoreCase("True")) {
						mInputShown = true;
					}
				}
			}
			br.close();
			p.waitFor();
		} catch (Exception e) {
			System.out.println(e);
		}
		return mInputShown;
	}

	/**
	 * Closes the Keyboard
	 */
	public void hideKeyboard() {
		try {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * Type on a web element.
	 * 
	 * @param byLocator the by locator
	 * @param text      the text
	 */
//	public void type(By byLocator, String text, String FieldName) {
//
//		try {
//			if (getPlatform().equals("HIPI")) {
//				Actions a = new Actions(getDriver());
//				a.sendKeys(text);
//				a.perform();
//			} else {
//				WebElement element = findElement(byLocator);
//				element.sendKeys(text);
//			}
//			text = text.split("\n")[0];
//			logger.info("Typed the value " + text + " into " + FieldName);
//			extent.extentLogger("", "Typed the value " + text + " into " + FieldName);
//		} catch (Exception e) {
//			logger.error(e);
//		}
//	}

	
	
	public void type(By byLocator, String text, String FieldName) {

		try {
			WebElement element = findElement(byLocator);
			element.sendKeys(text);
			text = text.split("\n")[0];
			logger.info("Typed the value " + text + " into " + FieldName);
			extent.extentLogger("", "Typed the value " + text + " into " + FieldName);
		} catch (Exception e) {
			logger.info("Failed to type the value " + text + " into " + FieldName);
			extent.extentLogger("", "Failed to type the value " + text + " into " + FieldName);
		}
	}
	
	
	/**
	 * Wait .
	 *
	 * @param x seconds to lock
	 */
	public void Wait(int x) {

		try {
			getDriver().manage().timeouts().implicitlyWait(x, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void waitTime(int x) {

		try {
			Thread.sleep(x);
		} catch (Exception e) {
			logger.info("Element taking longer than the actual time : "+x);
			extentLogger("", "Element taking longer than the actual time : "+x);
		}
	}

	/**
	 * @param keyevent pass the android key event value to perform specific action
	 * 
	 */
	public void adbKeyevents(int keyevent) {

		try {
			String cmd = "adb shell input keyevent" + " " + keyevent;
			Runtime.getRuntime().exec(cmd);
			logger.info("Performed the Keyevent" + keyevent);
			extent.extentLogger("adbKeyevent", "Performed the Keyevent" + keyevent);
		} catch (Exception e) {
			logger.error(e);
		}

	}

	/**
	 * @param byLocator
	 * @returns the list count of the element
	 */
	public int getCount(By byLocator) {

		int count = 0;
		try {
			count = getDriver().findElements(byLocator).size();
			logger.info("List count for" + " " + byLocator + " " + "is" + " " + count);
			extent.extentLogger("getCount", "List count for" + " " + byLocator + " " + "is" + " " + count);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}

	public List<WebElement> findElements(By byLocator) {
		if (getPlatform().equals("Android") || getPlatform().equals("MPWA") ||getPlatform().equals("MLWallet_iOS")) {
			return getDriver().findElements(byLocator);
		} else {
			return getWebDriver().findElements(byLocator);
		}
	}

	/**
	 * @param i
	 * @param byLocator
	 * @returns the By locator
	 */
	public String iterativeXpathtoStringGenerator(int temp, By byLocator, String property) {

		WebElement element = null;
		String drug = null;
		try {

			String xpath = byLocator.toString();
			String var = "'" + temp + "'";
			xpath = xpath.replaceAll("__placeholder", var);
			String[] test = xpath.split(": ");
			xpath = test[1];
			element = getDriver().findElement(By.xpath(xpath));
			drug = element.getAttribute(property);
		} catch (Exception e) {
			System.out.println(e);
		}
		return drug;
	}

	/**
	 * Back
	 */
	public void Back(int x) {

		try {
			if (getPlatform().equals("Web")) {
				for (int i = 0; i < x; i++) {
					getWebDriver().navigate().back();
					logger.info("Back button is tapped");
					extent.extentLogger("Back", "Back button is tapped");
				}
			} else if (getPlatform().equals("Android") || getPlatform().equals("HIPI")) {
				for (int i = 0; i < x; i++) {
					getDriver().navigate().back();
					logger.info("Back button is tapped");
					extent.extentLogger("Back", "Back button is tapped");
					waitTime(2000);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			screencapture();
		}
	}

	/**
	 * Finding the duplicate elements in the list
	 * 
	 * @param mono
	 * @param content
	 * @param dosechang
	 * @param enteral
	 */
	public List<String> findDuplicateElements(List<String> mono) {

		List<String> duplicate = new ArrayList<String>();
		Set<String> s = new HashSet<String>();
		try {
			if (mono.size() > 0) {
				for (String content : mono) {
					if (s.add(content) == false) {
						int i = 1;
						duplicate.add(content);
						System.out.println("List of duplicate elements is" + i + content);
						i++;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return duplicate;
	}

	/**
	 * @param contents
	 * @return the list without duplicate elements
	 */
	public List<String> removeDuplicateElements(List<String> contents) {

		LinkedHashSet<String> set = new LinkedHashSet<String>(contents);
		ArrayList<String> listWithoutDuplicateElements = new ArrayList<String>();
		try {

			if (contents.size() > 0) {
				listWithoutDuplicateElements = new ArrayList<String>(set);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return listWithoutDuplicateElements;
	}

	/**
	 * @param i
	 * @param byLocator
	 */
	public void iteratorClick(int temp, By byLocator) {

		try {
			String xpath = byLocator.toString();
			String var = "'" + temp + "'";
			xpath = xpath.replaceAll("__placeholder", var);
			String[] test = xpath.split(": ");
			xpath = test[1];
			getDriver().findElement(By.xpath(xpath)).click();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * get specific property value of a web element and stores to string variable.
	 * 
	 * @param property  the property of the element.
	 * @param byLocator the by locator
	 * @return value of the property.
	 */
	public String getElementPropertyToString(String property, By byLocator, String object) {
		String propertyValue = null;
		try {
			WebElement element = findElement(byLocator);
			propertyValue = element.getAttribute(property);
		} catch (Exception e) {
			logger.error(e);
		}
		return propertyValue;
	}

	/**
	 * @param sorted
	 * @return true if the list is sorted
	 * @return false if the list is not sorted
	 */
	public boolean checkListIsSorted(List<String> ListToSort) {

		boolean isSorted = false;

		if (ListToSort.size() > 0) {
			try {
				if (Ordering.natural().isOrdered(ListToSort)) {
					extent.extentLogger("Check sorting", "List is sorted");
					logger.info("List is sorted");
					isSorted = true;
					return isSorted;
				} else {
					extent.extentLogger("Check sorting", ListToSort + " " + "List is not sorted");
					logger.info(ListToSort + "List is notsorted");
					isSorted = false;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			logger.info("The size of the list is zero");
			extent.extentLogger("", ListToSort + " " + "There are no elements in the list to check the sort order");
		}
		return isSorted;
	}

	/**
	 * @param byLocator
	 * @returns the list count of the element
	 */
	public int iterativeGetCount(int temp, By byLocator) {

		int count = 0;
		try {

			String xpath = byLocator.toString();
			String var = "'" + temp + "'";
			xpath = xpath.replaceAll("__placeholder", var);
			String[] test = xpath.split(": ");
			xpath = test[1];
			count = getDriver().findElements(By.xpath(xpath)).size();
			logger.info("List count for" + " " + xpath + " " + "is" + " " + count);
			extent.extentLogger("getCount", "List count for" + " " + xpath + " " + "is" + " " + count);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}

	/**
	 * @param temp
	 * @param byLocator
	 * @return
	 */
	public By iterativeXpathText(String temp, By byLocator) {

		By searchResultList = null;

		try {

			String xpath = byLocator.toString();
			String var = "'" + temp + "'";
			xpath = xpath.replaceAll("__placeholder", var);
			String[] test = xpath.split(": ");
			xpath = test[1];
			searchResultList = By.xpath(xpath);
		} catch (Exception e) {
			System.out.println(e);
		}
		return searchResultList;
	}

	/**
	 * @param byLocator Checks whether element is not displayed
	 */
	public void checkElementNotPresent(By byLocator) {
		boolean isElementNotPresent = true;
		try {
			isElementNotPresent = checkcondition(byLocator);
			softAssert.assertEquals(isElementNotPresent, false);
			logger.info("" + byLocator + " " + "is not displayed");
			extent.extentLogger("checkElementNotPresent", "" + byLocator + "is not displayed");
		} catch (Exception e) {
			softAssert.assertEquals(isElementNotPresent, true, "Element" + byLocator + " " + "is visible");
//			softAssert.assertAll();
			logger.error(byLocator + " " + "is visible");
			extent.extentLogger("checkElementNotPresent", "" + byLocator + "is displayed");
			screencapture();
		}
	}

	/**
	 * Swipes the screen in left or right or Up or Down or direction
	 * 
	 * @param direction to swipe Left or Right or Up or Down
	 * @param count     to swipe
	 */
	@SuppressWarnings("rawtypes")
	public void Swipe(String direction, int count) {
		touchAction = new TouchAction(getDriver());
		String dire = direction;
		try {
			if (dire.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {
					Dimension size = getDriver().manage().window().getSize();
					int startx = (int) (size.width * 0.8);
					int endx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
					extent.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
					waitTime(5000);
				}
				
			} else if (dire.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int endx = (int) (size.width * 0.8);
					int startx = (int) (size.width * 0.3);
					if (size.height > 2000) {
						int starty = (int) (size.height / 2);
						touchAction.press(PointOption.point(startx, starty))
								.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
								.moveTo(PointOption.point(endx, starty)).release().perform();
						System.out.println("HI");
					} else {
						int starty = (int) (size.height / 0.5);//1.5)
						touchAction.press(PointOption.point(startx, starty))
								.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
								.moveTo(PointOption.point(endx, starty)).release().perform();
						System.out.println("Hello");
					}

					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeRight",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				
					waitTime(5000);
					}
				
			} else if (dire.equalsIgnoreCase("UP")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					//System.out.println("size : " + size);
					int starty = (int) (size.height * 0.75);// 0.8
					int endy = (int) (size.height * 0.09);// 0.2
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
							.moveTo(PointOption.point(startx, endy)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeUp",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					waitTime(5000);;
				}
				
			} else if (dire.equalsIgnoreCase("DOWN")) {
				
				for (int j = 0; j < count; j++) {

					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 770.0); //
					int endy = (int) (size.height * 0.2);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, endy))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeDown",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					waitTime(5000);
				}
			
			}

		} catch (Exception e) {
			logger.error(e);

		}

	}

	
	
	
	
	@SuppressWarnings("rawtypes")
	public void SwipeHashtagRail(By From) throws Exception {

		WebElement element = findElement(From);
		String eleX = element.getAttribute("x");
		String eleY = element.getAttribute("y");
		int currentPosX = Integer.parseInt(eleX);
		int currentPosY = Integer.parseInt(eleY);
		touchAction = new TouchAction(getDriver());
		touchAction.press(PointOption.point(currentPosX, currentPosY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(100, currentPosY))
				.release().perform();
	}
	

	
	
	
	public void PartialCommentsSwipe(String direction, int count) {
		touchAction = new TouchAction(getDriver());
		String dire = direction;

		try {

			if (dire.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {
					Dimension size = getDriver().manage().window().getSize();
					int startx = (int) (size.width * 0.4);
					int endx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					// getDriver().swipe(startx, starty, endx, starty, 1000);
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
					extent.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int endx = (int) (size.width * 0.4);
					int startx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeRight",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("UP")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.6);
					int endy = (int) (size.height * 0.1);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, endy)).release().perform();
					logger.info("Swiping screen in " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeUp",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

				}
			} else if (dire.equalsIgnoreCase("DOWN")) {
				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.4);
					int endy = (int) (size.height * 0.1);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, endy))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeDown",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

				}
			}

		} catch (Exception e) {
			logger.error(e);

		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Swipes the screen in left or right or Up or Down or direction
	 * 
	 * @param direction to swipe Left or Right or Up or Down
	 * @param count     to swipe
	 */
	@SuppressWarnings("rawtypes")
	public void PartialSwipe(String direction, int count) {
		touchAction = new TouchAction(getDriver());
		String dire = direction;

		try {

			if (dire.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {
					Dimension size = getDriver().manage().window().getSize();
					int startx = (int) (size.width * 0.4);
					int endx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					// getDriver().swipe(startx, starty, endx, starty, 1000);
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
					extent.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int endx = (int) (size.width * 0.4);
					int startx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeRight",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("UP")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.75);
					int endy = (int) (size.height * 0.05);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, endy)).release().perform();
					logger.info("Swiping screen in " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeUp",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

				}
			}else if (dire.equalsIgnoreCase("DOWN")) {
				
				for (int j = 0; j < count; j++) {

					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.8);
					int endy = (int) (size.height * 0.5);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, endy))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeDown",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					waitTime(5000);
				}
			}

		} catch (Exception e) {
			logger.error(e);

		}
	}
	@SuppressWarnings("rawtypes")
	public void PartialSwipe_ios(String direction, int count) {
		touchAction = new TouchAction(getDriver());
		String dire = direction;
		
		if (dire.equalsIgnoreCase("Up")) {
			
			for (int j = 0; j < count; j++) {

				Dimension size = getDriver().manage().window().getSize();
				int starty = (int) (size.height * 0.8);
				int endy = (int) (size.height * 0.5);
				int startx = size.width / 2;
				touchAction.press(PointOption.point(startx, starty))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(startx, endy)).release().perform();
				logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				extent.extentLogger("SwipeDown",
						"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				waitTime(5000);
			}
		}
			if (dire.equalsIgnoreCase("Down")) {
				
				for (int j = 0; j < count; j++) {

					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.8);
					int endy = (int) (size.height * 0.5);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, endy))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeDown",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					waitTime(5000);
				}
		}
		
	}
	
	
	
	

	@SuppressWarnings("rawtypes")
	public void SwipeRailContentCards(By From) throws Exception {

		Dimension size = getDriver().manage().window().getSize();
		int screenWidth = (int) (size.width * 0.8);

		WebElement element = findElement(From);
		String eleX = element.getAttribute("x");
		String eleY = element.getAttribute("y");
		int currentPosX = Integer.parseInt(eleX);
		int currentPosY = Integer.parseInt(eleY);

		currentPosX = currentPosX + screenWidth;
		currentPosY = currentPosY + 150;

		touchAction = new TouchAction(getDriver());
		touchAction.press(PointOption.point(currentPosX, currentPosY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(0, currentPosY))
				.release().perform();
	}

	/**
	 * Drag window
	 * 
	 * @param byLocator, byTimer
	 */
	@SuppressWarnings("rawtypes")
	public void DragWindow(By byLocator, String direction) throws Exception {
		WebElement element = getDriver().findElement(byLocator);
		touchAction = new TouchAction(getDriver());
		if (direction.equalsIgnoreCase("LEFT")) {
			Dimension size = element.getSize();
			int startx = (int) (size.width * 0.4);
			int endx = (int) (size.width * 0.1);
			int starty = size.height / 2;
			touchAction.longPress(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(endx, starty)).release().perform();
			logger.info("Swiping " + " " + direction + " direction");
			extent.extentLogger("SwipeLeft", "Swiping " + " " + direction + " direction");
		} else if (direction.equalsIgnoreCase("DOWN")) {
			Dimension size = getDriver().manage().window().getSize();
			int starty = (int) (size.height * 0.80);
			int endy = (int) (size.height * 0.20);
			int startx = size.width / 2;
			touchAction.longPress(PointOption.point(startx, endy))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point((int) startx, (int) starty)).release().perform();
			logger.info("Swiping " + " " + direction + " direction");
			extent.extentLogger("SwipeLeft", "Swiping " + " " + direction + " direction");
		}
	}

	/**
	 * Seek by dimensions
	 * 
	 * @param byLocator
	 */

	@SuppressWarnings({ "rawtypes", "unused" })
	public void TapToSeekChromecast(By byLocator) throws Exception {
		WebElement element = getDriver().findElement(byLocator);
		Dimension size = element.getSize();
		int startx = (int) (size.width);
		TouchAction action = new TouchAction(getDriver());
		SwipeAnElement(element, startx, 0);
		logger.info("Scrolling the seek bar");
	}
	@SuppressWarnings("rawtypes")
	public void SwipeRailIOS(By byLocator,String Direction) throws Exception {
		WebElement element = getDriver().findElement(byLocator);
		
		//WebElement element = getDriver().findElement(byLocator);
		if(Direction.equalsIgnoreCase("Left")) {
			
		int sizex= element.getLocation().getX();
		System.out.println("X " +sizex);
		int sizeY= element.getLocation().getY();
		System.out.println("Y " +sizeY);
		//Dimension size=element.getSize();
		System.out.println(sizex);
		int sizex2=sizex+400;
		int sizey2=sizeY+120;
		
		
		touchAction = new TouchAction(getDriver());
		touchAction.press(PointOption.point(sizex2, sizey2))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(sizex, sizey2))
				.release().perform();
		
		
		
		
		}else {
			
			
			
				
			int sizex= element.getLocation().getX();
			System.out.println("X " +sizex);
			int sizeY= element.getLocation().getY();
			System.out.println("Y " +sizeY);
			//Dimension size=element.getSize();
			System.out.println(sizex);
			int sizex2=sizex+500;
			int sizey2=sizeY+120;
			
			touchAction = new TouchAction(getDriver());
			touchAction.press(PointOption.point(sizex, sizey2))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(sizex2, sizey2))
					.release().perform();
			
			
			}
		}

	/**
	 * @param bundleID
	 */
	public void launchiOSApp(String bundleID) {

		try {
//			iOSDriver = (IOSDriver<WebElement>) getDriver();
			logger.info("Started the bundle id" + " " + bundleID);
			extent.extentLogger("Started the bundle id" + " " + bundleID, "Started the bundle id" + " " + bundleID);
		} catch (Exception e) {
			logger.info("Unable to Start the bundle id" + " " + bundleID);
			extent.extentLogger("Unable to Start the bundle id" + " " + bundleID,
					"Unable to Start the bundle id" + " " + bundleID);
		}
	}

	/**
	 * Get the Mobile Orientation
	 */
	public void GetAndVerifyOrientation(String Value) {
		ScreenOrientation orientation = getDriver().getOrientation();
		String ScreenOrientation = orientation.toString();
		try {
			softAssert.assertEquals(ScreenOrientation.equalsIgnoreCase(Value), true,
					"The screen Orientation is " + ScreenOrientation);
			logger.info("The screen Orientation is " + ScreenOrientation);
			extent.extentLogger("Screen Orientation", "The screen Orientation is " + ScreenOrientation);
		} catch (Exception e) {
			softAssert.assertEquals(false, true, "The screen Orientation is not " + ScreenOrientation);
//			softAssert.assertAll();
			logger.error("The screen Orientation is not " + ScreenOrientation);
			extent.extentLogger("Screen Orientation", "The screen Orientation is not " + ScreenOrientation);
			screencapture();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void SwipeRail(By From) throws Exception {

		WebElement element = findElement(From);
		String eleX = element.getAttribute("x");
		String eleY = element.getAttribute("y");
		int currentPosX = Integer.parseInt(eleX);
		int currentPosY = Integer.parseInt(eleY);
		touchAction = new TouchAction(getDriver());
		touchAction.press(PointOption.point(currentPosX, currentPosY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(0, currentPosY))
				.release().perform();
	}
	
	
	
	
	
	
	public void MusicTrim(By byLocator1) throws Exception {

		WebElement element = getDriver().findElement(byLocator1);
		Dimension size = element.getSize();
		int startx = (int) (size.width);

		Dimension size1 = getDriver().manage().window().getSize();
		int endx = (int) (size1.width * 0.5);

		AndroidTouchAction touch = new AndroidTouchAction(getDriver());
		touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(endx, startx))
				.release().perform();
	}
	

	/**
	 * Closes the iOS keyboard
	 */
	public void closeIosKeyboard() {

		try {
//			iOSDriver = (IOSDriver<WebElement>) getDriver();
			extent.extentLogger("Hiding keyboard successful", "Hiding keyboard successful");
		} catch (Exception e) {
			extent.extentLogger("Hiding keyboard not successful", "Hiding keyboard not successful");
		}
	}

	/**
	 * closes the application
	 */
	public void closeiOSApp() {
		try {
//			iOSDriver = (IOSDriver<WebElement>) getDriver();
			iOSDriver.closeApp();
			extent.extentLogger("Killed the appliaction successfully", "Killed the appliaction successfully");
		} catch (Exception e) {
			extent.extentLogger("Unable to Kill the application", "Unable to Kill the application");

		}
	}

	/**
	 * closes the Android application
	 */
	public void closeAndroidApp() {
		try {
			getDriver().resetApp();
			extent.extentLogger("Killed the application successfully", "Killed the application successfully");
		} catch (Exception e) {
			extent.extentLogger("Unable to Kill the application", "Unable to Kill the application");

		}
	}

	/**
	 * Verifies if a new page or app is open
	 */

	public boolean newPageOrNt() {
		boolean app = false;
		try {
			String cmd = "adb shell \"dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'\"";
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String outputText = "";
			while ((outputText = br.readLine()) != null) {
				if (!outputText.trim().contains("com.tv.v18.viola")) {
					app = true;
					logger.info("The app is navigated to ad page");
					extent.extentLogger("Navigated to ad page or not", "The app is navigated to ad page");
				} else {
					logger.error("The app is not navigated to ad page");
					extent.extentLogger("Navigated to ad page or not", "The app is not navigated to ad page");
				}
			}
			br.close();
			p.waitFor();
		} catch (Exception e) {
			System.out.println(e);
		}
		return app;
	}

	public void IosDragWindow(By byLocator, String direction) throws Exception {
		WebElement element = getDriver().findElement(byLocator);
		@SuppressWarnings({ "rawtypes", "unused" })
		TouchAction action = new TouchAction(getDriver());
		if (direction.equalsIgnoreCase("LEFT")) {
			Dimension size = element.getSize();
			int startx = (int) (size.width * 0.4);
			System.out.println(startx);
			int endx = 0;
			System.out.println(endx);
			int starty = 1250;
			System.out.println(starty);
			SwipeAnElement(element, startx, starty);
			logger.info("Swiping " + " " + direction + " direction");
			extent.extentLogger("SwipeLeft", "Swiping " + " " + direction + " direction");
		}
	}

	public String getAttributValue(String property, By byLocator) throws Exception {
		String Value = null;
		WebElement element = findElement(byLocator);
		Value = element.getAttribute(property);
		return Value;
	}

	/*
	 * public void captureScreenshotAndCompare(String SSName) throws
	 * InterruptedException { Thread.sleep(10000); File src =
	 * getDriver().getScreenshotAs(OutputType.FILE); String dir =
	 * System.getProperty("user.dir"); String fileName = dir +
	 * "/Applitool/baseLine/" + SSName + ".png"; System.out.println(fileName); try {
	 * FileUtils.copyFile(src, new File(fileName)); } catch (IOException e) {
	 * System.out.println(e.getMessage()); } BufferedImage img; try { img =
	 * ImageIO.read(new File(fileName)); getEye().checkImage(img, SSName);
	 * extent.extentLogger("UI Validation", "UI for " + SSName + " is validated"); }
	 * catch (IOException e) { System.out.println(e.getMessage()); } }
	 */

	public void SwipeAnElement(WebElement element, int posx, int posy) {
		AndroidTouchAction touch = new AndroidTouchAction(getDriver());
		touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(posx, posy))
				.release().perform();
	}
	public void SwipeAnElementiOS(WebElement element, int posx, int posy) {
		@SuppressWarnings("rawtypes")
		TouchAction touchAction=new TouchAction(getDriver());
		touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(600))).moveTo(PointOption.point(posx, posy))
				.release().perform();
	}

	public void longPressContent(By element) throws Exception {
		AndroidTouchAction touch = new AndroidTouchAction(getDriver());
		touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(findElement(element))))
				.release().perform();

		TouchActions action = new TouchActions(getDriver());
		action.singleTap(findElement(element));
		action.click();

	}
	
	


	public boolean verifyElementExist1(WebElement ele, String str) throws Exception {
		try {
			WebElement element = ele;
			if (element.isDisplayed()) {
				extent.extentLogger("checkElementPresent", "<b>" + str + "</b> is displayed");
				logger.info("" + str + " is displayed");
				return true;
			}
		} catch (Exception e) {
			extent.extentLogger("checkElementPresent", "<b>" + str + "</b> is not displayed");
			logger.info(str + " is not displayed");
			return false;
		}
		return false;
	}

	public boolean checkcondition(String s) throws Exception {
		boolean iselementPresent = false;
		try {
			String element = "//*[@text='[" + s + "]']";
			iselementPresent = ((WebElement) getDriver().findElementsByXPath(element)).isDisplayed();
		} catch (Exception e) {
			iselementPresent = false;
		}
		return iselementPresent;
	}

	public void switchtoLandscapeMode() throws IOException {
		Runtime.getRuntime().exec(
				"adb shell content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:1");
	}

	public void switchtoPortraitMode() throws IOException {
		Runtime.getRuntime().exec(
				"adb shell content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:0");
	}

//====================================================================================================================================
	/** ::::::::::::::::Web Utilities:::::::::::: */

	/**
	 * Function to ExplicitWait Visibility
	 * 
	 * @param element
	 * @param time
	 * @throws Exception
	 */
	public void explicitWaitVisibility(By element, int time) throws Exception {
		wait.until(ExpectedConditions.visibilityOf(findElement(element)));
	}

	
	
	/**
	 * Function to ExplicitWait for Clickable
	 * 
	 * @param element
	 * @param time
	 * @throws Exception
	 */
	public void explicitWaitClickable(By element, int time) throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(findElement(element)));
	}

	/**
	 * Function to ExplicitWait for windows
	 * 
	 * @param noOfWindows
	 */
	public static void explicitWaitForWindows(int noOfWindows) {
		wait.until(ExpectedConditions.numberOfWindowsToBe(noOfWindows));
	}

	/**
	 * Function for ExplicitWait of Element Refresh
	 * 
	 * @throws Exception
	 */
	public void explicitWaitForElementRefresh(By element) throws Exception {
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(findElement(element))));
	}

	/**
	 * Function for implicitWait
	 */
	public void implicitWait() {
		getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * Function to select by visible text from drop down
	 * 
	 * @param element
	 * @param value
	 * @throws Exception
	 */
	public void selectByVisibleTextFromDD(By element, String value) throws Exception {
		explicitWaitVisibility(element, 20);
		Select select = new Select(findElement(element));
		select.selectByVisibleText(value);
	}

	/**
	 * Function to select by value from drop down
	 * 
	 * @param element
	 * @param value
	 * @throws Exception
	 */
	public void selectByValueFromDD(By element, String value) throws Exception {
		explicitWaitVisibility(element, 20);
		Select select = new Select(findElement(element));
		select.selectByValue(value);
	}

	/**
	 * Function to select By index From Drop down
	 * 
	 * @param element
	 * @param value
	 * @throws Exception
	 */
	public void selectByIndexFromDD(By element, String value) throws Exception {
		explicitWaitVisibility(element, 20);
		Select select = new Select(findElement(element));
		select.selectByValue(value);
	}

	/**
	 * Function to get First Element from Drop down
	 * 
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public String getFirstElementFromDD(By element) throws Exception {
		Select select = new Select(findElement(element));
		return select.getFirstSelectedOption().getText();
	}

	/**
	 * Function to scroll down
	 */
	public static void scrollDownWEB() {
		js.executeScript("window.scrollBy(0,250)", "");
	}

	/**
	 * Function to Scroll By
	 */
	public static void scrollByWEB() {
		js.executeScript("window.scrollBy(0,250)", "");
	}

	/**
	 * Function to scroll bottom of page
	 */
	public static void scrollToBottomOfPageWEB() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void scrollToBottomOfPage() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * Function to scroll to top of the page
	 */
	public static void scrollToTopOfPageWEB() {
		js.executeScript("window.scrollBy(0,-250)", "");
	}

	public static void scrollToTopOfPage() {
		js.executeScript("window.scrollBy(0,-250)", "");
	}

	/**
	 * Function Scroll to Element
	 * 
	 * @param element
	 * @throws Exception
	 */
	public void ScrollToTheElement(By element) throws Exception {
		js.executeScript("arguments[0].scrollIntoView(true);", findElement(element));
		js.executeScript("window.scrollBy(0,-50)", "");
	}

	/**
	 * Function to switch to window
	 * 
	 * @param noOfWindows
	 */
	public void switchToWindow(int noOfWindows) {
		try {
			wait.until(ExpectedConditions.numberOfWindowsToBe(noOfWindows));
			for (String winHandle : getWebDriver().getWindowHandles()) {
				win.add(winHandle);
				getWebDriver().switchTo().window(winHandle);
				getWebDriver().manage().window().maximize();
			}
		} catch (Exception e) {
			System.out.println("\n No window is displayed!");
		}
	}

	/**
	 * Function to switch to parent Window
	 */
	public void switchToParentWindow() {
		try {
			getWebDriver().switchTo().window(win.get(0));
		} catch (Exception e) {
			System.out.println("\n No window is displayed!");
		}
	}

	/**
	 * Function for hard sleep
	 * 
	 * @param seconds
	 */
	public void sleep(int seconds) {
		try {
			int ms = 1000 * seconds;
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Function to switch the tab
	 * 
	 * @param tab
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void switchTab(int tab) {
		ArrayList<String> window = new ArrayList(getWebDriver().getWindowHandles());
		getWebDriver().switchTo().window(window.get(tab));
	}

	/**
	 * Function to generate random integer of specified maxValue
	 * 
	 * @param maxValue
	 * @return
	 */
	public String generateRandomInt(int maxValue) {
		Random rand = new Random();
		int x = rand.nextInt(maxValue);
		String randomInt = Integer.toString(x);
		return randomInt;
	}

	/**
	 * Function to generate Random String of length 4
	 * 
	 * @return
	 */
	public String generateRandomString(int size) {
		String strNumbers = "abcdefghijklmnopqrstuvwxyzacvbe";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(9);
		strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		String s1 = strRandomNumber.toString().toUpperCase();
		for (int i = 1; i < size; i++) {
			strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		}
		return s1 + strRandomNumber.toString();
	}

	/**
	 * Function to generate Random characters of specified length
	 * 
	 * @param candidateChars
	 * @param length
	 * @return
	 */
	public String generateRandomChars(String candidateChars, int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}
		return sb.toString();
	}

	/**
	 * Function to generate Random Integer between range
	 * 
	 * @param maxValue
	 * @param minValue
	 * @return
	 * @throws InterruptedException
	 */
	public String generateRandomIntwithrange(int maxValue, int minValue) throws InterruptedException {
		Thread.sleep(2000);
		Random rand = new Random();
		int x = rand.nextInt(maxValue - minValue) + 1;
		String randomInt = Integer.toString(x);
		System.out.println("Auto generate of number from generic method : " + randomInt);
		return randomInt;
	}

	/**
	 * Function to drag and drop an object
	 * 
	 * @param From
	 * @param To
	 * @throws Exception
	 */
	public void dragnddrop(By From, By To) throws Exception {
		WebElement Drag = getDriver().findElement(From);
		WebElement Drop = getDriver().findElement(To);
		Thread.sleep(1000);
		Actions builder = new Actions(getDriver());
		builder.clickAndHold(Drag).moveToElement(Drop).release().build().perform();
		//builder.clickAndHold(Drag).moveToElement(Drop).release(Drop).build().perform();
	}

	/**
	 * Function Convert from String to Integer @param(String)
	 */
	public int convertToInt(String string) {
		int i = Integer.parseInt(string);
		return i;
	}

	/**
	 * Function Convert from Integer to String @param(integer)
	 */
	public String convertToString(int i) {
		String string = Integer.toString(i);
		return string;
	}

	/**
	 * Click On element without waiting or verifying
	 *
	 * @param byLocator the by locator
	 *
	 */
	public void clickDirectly(By byLocator, String validationtext) throws Exception {
		try {
			getDriver().findElement(byLocator).click();
			logger.info("Clicked on the " + validationtext);
			extent.extentLogger("click", "Clicked on the <b> " + validationtext);
		} catch (Exception e) {
			logger.error(e);
			screencapture();
		}
	}

	public void verifyAlert() {
		try {
			getWebDriver().switchTo().alert().dismiss();
			logger.info("Dismissed the alert Pop Up");
			extent.extentLogger("Alert PopUp", "Dismissed the alert Pop Up");
		} catch (Exception e) {

		}
	}

	public void acceptAlert() {
		try {
			getWebDriver().switchTo().alert().accept();
			logger.info("Dismissed the alert Pop Up");
			extent.extentLogger("Alert PopUp", "Dismissed the alert Pop Up");
		} catch (Exception e) {

		}
	}

	public boolean clickElementWithLocator(By locator) throws Exception {
		try {
			getDriver().findElement(locator).click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickElementWithWebElement(WebElement element) throws Exception {
		try {
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	

	
	
	public static int timeToSec(String s) {
		String[] t = s.split(":");
		int num = 0;
		System.out.println(t.length);

		if (t.length == 2) {
			num = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]); // minutes since 00:00
		}
		if (t.length == 3) {
			num = ((Integer.parseInt(t[0]) * 60) * 60) + Integer.parseInt(t[1]) * 60 + Integer.parseInt(t[2]);
		}

		return num;
	}

	
	
	public float getLikeCount(String likeCount) throws Exception{
	      
	      String[] splitString = likeCount.split("K");
	      System.out.println("SPLIT :"+splitString[0]);

	    int i;
	    float f;
	    
	    if(splitString[0].contains(".")){
	    	System.out.println("its float");
	    	 f = Float.parseFloat(splitString[0]); 
	    	 System.out.println(f);
	    	 return f;
	    }else{
	    	System.out.println("its int");
	    	 i = Integer.parseInt(splitString[0]);
	    	 System.out.println(i);
	    	 return i;
	    }

	}
	
	
	


	public void clickByElement(WebElement ele, String validationtext) throws Exception {
		try {
			WebElement element = ele;
			element.click();
			logger.info("Clicked on the " + validationtext);
			extent.extentLogger("click", "Clicked on the <b> " + validationtext);
		} catch (Exception e) {
			logger.error(e);
			screencapture();
		}
	}

	public boolean verifyElementEnabled(By byLocator, String str) throws Exception {

		try {
			WebElement element = findElement(byLocator);
			if (element.isEnabled()) {
				extent.extentLogger("checkElementPresent", "" + str + " is displayed");
				logger.info("" + str + " is displayed");
				return true;
			}
		} catch (Exception e) {
			extent.extentLogger("checkElementPresent", "" + str + " is not displayed");
			logger.info(str + " is not displayed");
			return false;
		}
		return false;
	}

	public int getCountweb(By byLocator) {

		int count = 0;
		try {
			count = getWebDriver().findElements(byLocator).size();
			logger.info("List count for" + " " + byLocator + " " + "is" + " " + count);
			extent.extentLogger("getCount", "List count for" + " " + byLocator + " " + "is" + " " + count);
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}

	public boolean waitForElementAndClickIfPresent(By locator, int seconds, String message)
			throws InterruptedException {
		try {
			if (getPlatform().equals("Web")) {
				for (int time = 0; time <= seconds; time++) {
					try {
						getWebDriver().findElement(locator).click();
						logger.info("Clicked element " + message);
						extent.extentLogger("clickedElement", "Clicked element " + message);
						return true;
					} catch (Exception e) {
						Thread.sleep(1000);
					}
				}
			} else if (getPlatform().equals("Android") || getPlatform().equals("MPWA") || getPlatform().equals("HIPI")) {
				for (int time = 0; time <= seconds; time++) {
					try {
						getDriver().findElement(locator).click();
						logger.info("Clicked on " + message);
						extent.extentLogger("clickedElement", "Clicked on " + message);
						return true;
					} catch (Exception e) {
						Thread.sleep(1000);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
			screencapture();
		}
		return false;
	}

	public String RandomStringGenerator(int n) {
		{

			String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
			StringBuilder sb = new StringBuilder(n);
			for (int i = 0; i < n; i++) {
				int index = (int) (AlphaNumericString.length() * Math.random());

				sb.append(AlphaNumericString.charAt(index));
			}
			return sb.toString();
		}
	}

	public void swipeToBottomOfPage() throws Exception {
		for (int i = 0; i < 5; i++) {
			scrollToBottomOfPage();
			waitTime(4000);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void androidSwitchTab() {
		ArrayList<String> window = new ArrayList(getDriver().getWindowHandles());
		getDriver().switchTo().window(window.get(window.size() - 1));
	}

	/**
	 * Function to switch to parent Window
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void AndroidSwitchToParentWindow() {
		try {
			ArrayList<String> window = new ArrayList(getDriver().getWindowHandles());
			getDriver().switchTo().window(window.get(0));
		} catch (Exception e) {
			System.out.println("\n No window is displayed!");
		}
	}

	public static String getTheOSVersion() {
		String version = null;
		try {
			String cmd1 = "adb shell getprop ro.build.version.release";
			Process p1 = Runtime.getRuntime().exec(cmd1);
			BufferedReader br = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			// outputText1 ="";
			while ((version = br.readLine()) != null) {
				logger.info("Version :: " + version.toString());
				Thread.sleep(3000);
				break;
			}

		} catch (Exception e) {
			// logger.error(e);
		}
		return version;
	}

	public void TurnOFFWifi() throws IOException {
		String Deviceversion = getTheOSVersion();
		System.out.println("Turn off wifi");
		if (Deviceversion.contains("6")) {
			Runtime.getRuntime().exec("adb shell am broadcast -a io.appium.settings.wifi --es setstatus disable");
			logger.info("Turning off wifi");
			extent.extentLoggerPass("Turning off wifi", "Turning off wifi");
		} else {
			Runtime.getRuntime().exec("adb shell svc wifi disable");
			logger.info("Turning off wifi");
			extent.extentLoggerPass("Turning off wifi", "Turning off wifi");
		}
		waitTime(5000);
	}

	public void TurnONWifi() throws IOException {
		String Deviceversion = getTheOSVersion();
		System.out.println("Turn on wifi");
		if (Deviceversion.contains("6")) {
			Runtime.getRuntime().exec("adb shell am broadcast -a io.appium.settings.wifi --es setstatus enable");
			logger.info("Turning ON wifi");
			extent.extentLoggerPass("Turning ON wifi", "Turning ON wifi");
		} else {
			Runtime.getRuntime().exec("adb shell svc wifi enable");
			logger.info("Turning ON wifi");
			extent.extentLoggerPass("Turning ON wifi", "Turning ON wifi");
		}
		waitTime(5000);
	}

	@SuppressWarnings("rawtypes")
	public void carouselSwipe(String direction, int count) {
		touchAction = new TouchAction(getDriver());
		String dire = direction;
		try {
			if (dire.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {
					Dimension size = getDriver().manage().window().getSize();

					int startx = (int) (size.width * 0.9);
					int endx = (int) (size.width * 0.20);
					int starty = size.height / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
					extent.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int endx = (int) (size.width * 0.9);
					int startx = (int) (size.width * 0.20);
					if (size.height > 2000) {
						int starty = (int) (size.height / 2);
						touchAction.press(PointOption.point(startx, starty))
								.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
								.moveTo(PointOption.point(endx, starty)).release().perform();
					} else {
						int starty = (int) (size.height / 1.5);
						touchAction.press(PointOption.point(startx, starty))
								.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
								.moveTo(PointOption.point(endx, starty)).release().perform();
					}

					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeRight",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			}
		} catch (Exception e) {
			logger.error(e);

		}
	}

	public void ScrollToTheElementWEB(By element) throws Exception {
		js.executeScript("arguments[0].scrollIntoView(true);", findElement(element));
		js.executeScript("window.scrollBy(0,-250)", "");
	}

	/**
	 * Function to Initialize mandatoryRegistrationPopUp count to one
	 * 
	 * @param userType
	 */
	public void mandatoryRegistrationPopUp(String userType) {
		if (userType.contains("Guest")) {
			js.executeScript("window.localStorage.setItem('mandatoryRegistrationPopupCount','1')");
		}
	}

	public boolean checkElementDisplayed(By byLocator, String str) throws Exception {

		try {
			WebElement element = findElement(byLocator);
			if (element.isDisplayed()) {
				extent.extentLogger("checkElementPresent", "" + str + " is displayed");
				logger.info("" + str + " is displayed");
				return true;
			}
		} catch (Exception e) {
			extent.extentLogger("checkElementPresent", "" + str + " is not displayed");
			logger.info("" + str + " is not displayed");
			return false;
		}
		return false;
	}

	public static String getParameterFromXML(String param) {
		return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter(param);
	}

//	====================================================TV=================================================
	public boolean verifyElementExistTv(By byLocator, String str) throws Exception {

		try {

			if (getDriver().findElement(byLocator).isDisplayed()) {
				extent.extentLoggerPass("checkElementPresent", str + " is displayed");
				logger.info("" + str + " is displayed");
				return true;
			}
		} catch (Exception e) {
			extent.extentLogger("checkElementPresent", str + " is not displayed");
			logger.info(str + " is not displayed");
			return false;
		}
		return false;
	}

	public void TVclick(By byLocator, String validationtext) throws Exception {

		try {
			getDriver().findElement(byLocator).click();
			logger.info("Clicked on " + validationtext);
			extent.extentLogger("click", "Clicked on " + validationtext);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public String TVgetText(By byLocator) throws Exception {
		String Value = null;
		Value = getDriver().findElement(byLocator).getText();
		return Value;
	}

	public void TVRemoteEvent(int value) throws Exception {

		String cmd = "adb shell input keyevent " + value + "";
		System.out.println(cmd);
		Runtime.getRuntime().exec(cmd);

	}

	public boolean TVVerifyElementNotPresent(By byLocator, int waitTime) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), waitTime);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public String TVgetAttributValue(String property, By byLocator) throws Exception {
		String Value = null;
		Value = getDriver().findElement(byLocator).getAttribute(property);
		return Value;
	}
	
	
	public String RandomIntegerGenerator(int n){
		String number = "0123456789";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (number.length() * Math.random());

			sb.append(number.charAt(index));
		}
		return sb.toString();
	}
	
	public boolean SwipeUntilFindElement(By locator, String direction) throws Exception {

		boolean checkLocator, eletFound = false;
		if (direction.equalsIgnoreCase("UP")) {
			for (int i = 1; i < 25; i++) {
				PartialSwipe("UP", 1);
				checkLocator = verifyIsElementDisplayed(locator);
				if (checkLocator) {
					eletFound = true;
					break;
				}
			}
		}

		if (direction.equalsIgnoreCase("DOWN")) {
			for (int i = 1; i < 25; i++) {
				PartialSwipe("DOWN", 1);
				checkLocator = verifyIsElementDisplayed(locator);
				if (checkLocator) {
					eletFound = true;
					break;
				}
			}
		}
		return eletFound;
	}
//---------------------------mahesh iOS-----------------------------------------------------------------------
	public boolean waitForElementDisplayediOS(By byLocator, int seconds, String message) throws InterruptedException {
		for (int time = 0; time <= seconds; time++) {
			try {
				Thread.sleep(500);
				softAssert.assertEquals(getDriver().findElement(byLocator).isDisplayed(), true,
						"" + message + " " + " is displayed");
				logger.info(message + " is displayed");
				extent.extentLogger("checkElementPresent", message + " is displayed");
				return true;
			} catch (Exception e) {
				Thread.sleep(500);
			}
		}
		return false;
	}
	
	
	public boolean waitForElementDisplayedFastPolling(By byLocator, int seconds, String message) throws InterruptedException {
		for (int time = 0; time <= seconds; time++) {
			try {
				
				softAssert.assertEquals(getDriver().findElement(byLocator).isDisplayed(), true,
						"" + message + " " + " is displayed");
				logger.info(message + " is displayed");
				extent.extentLogger("checkElementPresent", message + " is displayed");
				return true;
			} catch (Exception e) {
				
			}
		}
		return false;
	}
	
	
	
	
	
	/*
	 * tap on element
	 */
	public void tapiOS(WebElement element,int k) {
		@SuppressWarnings("rawtypes")
		TouchAction touchAction=new TouchAction(getDriver());
		for(int i=1;i<=k;i++) {
		touchAction.tap(new TapOptions().withElement(ElementOption.element(element))).perform();
		logger.info("Tapped on element for "+ i +" time");
		extent.extentLogger("", "Tapped on element for "+ i +" time");
		}
	}
	
	/*
	 * Long Press on element
	 */
	public void LongPressOnElementiOS(WebElement element,int k) {
		@SuppressWarnings("rawtypes")
		TouchAction touchAction=new TouchAction(getDriver());
		for(int i=1;i<=k;i++) {
			touchAction.longPress(ElementOption.element(element)).perform();
		//touchAction.tap(new TapOptions().withElement(ElementOption.element(element))).perform();
		logger.info("Tapped on element for "+ i +" time");
		extent.extentLogger("", "Tapped on element for "+ i +" time"); 
		}
	}
	
	
	
	/*
	 * tap on middle point
	 */
	public void tapiOS_Middlepoint(int k) {
		@SuppressWarnings("rawtypes")
		TouchAction touchAction=new TouchAction(getDriver());
		
		for(int i=1;i<=k;i++) {
			Dimension size = getDriver().manage().window().getSize();
			int startx =  size.width/2;
			
			int starty = size.height / 2;
			
			//300, 750
			touchAction.longPress(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).perform().release();

			logger.info("Tapped on element for "+ i +" time");
		extent.extentLogger("", "Tapped on element for "+ i +" time");
		}
	}
	
	public void tapiOSAtCenterOfScrreen(int k) {
		@SuppressWarnings("rawtypes")
		TouchAction touchAction=new TouchAction(getDriver());
		
		for(int i=1;i<=k;i++) {
			Dimension size = getDriver().manage().window().getSize();
			int startx =  size.width/2;
			
			int starty = size.height / 2;
		
			touchAction.press(PointOption.point(startx, starty)).perform().release();


			logger.info("Tapped on Screen for "+ i +" time");
		extent.extentLogger("", "Tapped on Screen for "+ i +" time");
		}
	}
	
	
	
	
	
	/**
	 * Swipes the screen in left or right or Up or Down or direction
	 * 
	 * @param direction to swipe Left or Right or Up or Down
	 * @param count     to swipe
	 */
	@SuppressWarnings("rawtypes")
	public void PartialSwipeiOS(String direction, int count) {
		touchAction = new TouchAction(getDriver());
		String dire = direction;

		try {

			if (dire.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {
					Dimension size = getDriver().manage().window().getSize();
					int startx = (int) (size.width * 0.4);
					int endx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					// getDriver().swipe(startx, starty, endx, starty, 1000);
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
					extent.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int endx = (int) (size.width * 0.4);
					int startx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeRight",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("UP")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.85);
					int endy = (int) (size.height * 0.09);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
							.moveTo(PointOption.point(startx, endy)).release().perform();
					logger.info("Swiping screen in " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeUp",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

				}
			}else if (dire.equalsIgnoreCase("DOWN")) {
				
				for (int j = 0; j < count; j++) {

					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.85);
					int endy = (int) (size.height * 0.2);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, endy))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
							.moveTo(PointOption.point(startx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeDown",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					
				}
			}

		} catch (Exception e) {
			logger.error(e);

		}
	}
	
	public void PartialCommentsSwipeiOS(String direction, int count) {
		touchAction = new TouchAction(getDriver());
		String dire = direction;

		try {

		
			 if (dire.equalsIgnoreCase("DOWN")) {
				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.9);
					int endy = (int) (size.height * 0.7);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, endy))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeDown",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

				}
			}else if(dire.equalsIgnoreCase("Up")) {
				for (int j = 0; j < count; j++) {
				Dimension size = getDriver().manage().window().getSize();
				int starty = (int) (size.height * 0.7);
				int endy = (int) (size.height * 0.9);
				int startx = size.width / 2;
				touchAction.press(PointOption.point(startx, endy))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
						.moveTo(PointOption.point(startx, starty)).release().perform();
				logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				extent.extentLogger("SwipeDown",
						"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			}

		} catch (Exception e) {
			logger.error(e);

		}
	
	}
	
	public boolean waitUntilElementDisplayed (By locator, int countWait) throws Exception {
		int iterator=0;
		while (verifyElementDisplayed(locator)== false) {
			if (iterator <= countWait) {
				waitTime(1000);
				iterator++;
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	public void DecreaseMusicVolume(By byLocator) {
		   System.out.println("Decreasing Music volume in Adjust Volume popup");
		   extent.extentLogger("", "Decreasing Music volume in Adjust Volume popup");
		    TouchAction action = new TouchAction(getDriver());
			 waitTime(5000);

			 Dimension size = getDriver().findElement(byLocator).getSize();
			 
			 WebElement ele = getDriver().findElement(byLocator);
			 Point point = (Point) ele.getLocation();
			 
			 System.out.println(((org.openqa.selenium.Point) point).getX());
			 System.out.println(((org.openqa.selenium.Point) point).getY());
			 System.out.println(size.height);
			 System.out.println(size.width);
			 
			 int X = ((org.openqa.selenium.Point) point).getX();
			 int Y = ((org.openqa.selenium.Point) point).getY();    			 
			 int height = size.height;
			 int width = size.width;   
			 
			 float height1 = height / 2;
			 
			 float width1 = width / 2;
			 
			 float pointY = Y+height1;
			 float pointX = width1;
			 
			 int pY = (int)pointY;
			 System.out.println(pY);
			 int pX = (int)pointX;
			 System.out.println(pX);			 
			 action.press(PointOption.point(pX, pY)).release().perform();
	}
	
	
	
	public void IncreasingMusicVolume(By byLocator) {
	    System.out.println("Increasing Music volume in Adjust Volume popup");
	    extent.extentLogger("", "Increasing Music volume in Adjust Volume popup");
	    TouchAction action = new TouchAction(getDriver());
		 waitTime(5000);

		 Dimension size = getDriver().findElement(byLocator).getSize();
		 
		 WebElement ele = getDriver().findElement(byLocator);
		 Point point = (Point) ele.getLocation();
		 
		 System.out.println(((org.openqa.selenium.Point) point).getX());
		 System.out.println(((org.openqa.selenium.Point) point).getY());
		 System.out.println(size.height);
		 System.out.println(size.width);
		 
		 int X = ((org.openqa.selenium.Point) point).getX();
		 int Y = ((org.openqa.selenium.Point) point).getY();    			 
		 int height = size.height;
		 int width = size.width;   
		 
		 float height1 = height / 2;
		 
		 float width1 = width / 2;
		 
		 float pointY = Y+height1;
		 float pointX = X+width1;
		 
		 int pY = (int)pointY;
		 System.out.println(pY);
		 int pX = (int)pointX;
		 System.out.println(pX);			 
		 action.press(PointOption.point(pX, pY)).release().perform();
}	
	
	public void carouselSwipe(String direction, int count, String width, String height) throws Exception {
		touchAction = new TouchAction(getDriver());

		try {
			if (direction.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {

					int startx = (Integer.valueOf(width)) - 200;
					int endx = 100;
					int starty = (Integer.valueOf(height)) + 300;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");
					extent.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");
				}
			} else if (direction.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					int startx = 100;
					int endx = (Integer.valueOf(width)) - 200;
					int starty = (Integer.valueOf(height)) + 300;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
							.moveTo(PointOption.point(endx, starty)).release().perform();

					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeRight",
							"Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");
				}
			}
		} catch (Exception e) {
			logger.error(e);

		}
	}
	
//	public void carouselCardsSwipe(String direction, int count, String width, String height) throws Exception {
//		touchAction = new TouchAction(getDriver());
//
//		try {
//
//			int yCordinate;
//			if (verifyElementIsNotDisplayed(HipiDiscoverPage.obj5thProfile)) {
//				yCordinate = (int) ((Integer.valueOf(height)) * 0.4);
//			} else {
//				yCordinate = (int) ((Integer.valueOf(height)) * 0.5);
//			}
//
//			if (direction.equalsIgnoreCase("LEFT")) {
//
//				for (int i = 0; i < count; i++) {
//
//					int startx = (Integer.valueOf(width));
//					startx = (int) (startx * 0.8);
//					int endx = (int) (startx * 0.1);
//
//					int starty = (Integer.valueOf(height)) + yCordinate;
//					touchAction.press(PointOption.point(startx, starty))
//							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
//							.moveTo(PointOption.point(endx, starty)).release().perform();
//					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");
//					extent.extentLoggerPass("SwipeLeft",
//							"Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");
//
//					System.out.println("\n<<< Swipe <<<");
//					System.out.println("[X,Y]: [" + startx + "," + starty + "] ===> [" + endx + "," + starty + "]");
//				}
//			} else if (direction.equalsIgnoreCase("RIGHT")) {
//
//				for (int j = 0; j < count; j++) {
//					int startx = (int) ((Integer.valueOf(width)) * 0.1);
//					int endx = (int) ((Integer.valueOf(width)) * 0.8);
//					int starty = (Integer.valueOf(height)) + yCordinate;
//					touchAction.press(PointOption.point(startx, starty))
//							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
//							.moveTo(PointOption.point(endx, starty)).release().perform();
//
//					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");
//					extent.extentLoggerPass("SwipeRight",
//							"Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");
//
//					System.out.println("\n>>> Swipe >>>");
//					System.out.println("[X,Y]: [" + startx + "," + starty + "] ===> [" + endx + "," + starty + "]");
//				}
//			}
//		} catch (Exception e) {
//			logger.error(e);
//		}
//	}
//
//	
	public void VerifyScrollOfDatePickerDown() {
		WebElement month1=getDriver().findElement(By.xpath("(//*[@class='android.widget.NumberPicker'])[1]//*[@id='numberpicker_input']//preceding-sibling::*"));
		int month1x=month1.getLocation().getX();
		int month1y=month1.getLocation().getY();
		int month1Width=month1.getSize().getWidth();
		int month1Height=month1.getSize().getHeight();
		int month1MidX=month1x+(month1Width/2);
		int month1MidY=month1y+(month1Height/2);
		
		/*
		WebElement month3=getDriver().findElement(By.xpath("(//*[@class='android.widget.NumberPicker'])[1]//*[@id='numberpicker_input']//following-sibling::*"));
		int month3x=month3.getLocation().getX();
		int month3y=month3.getLocation().getY();
		int month3Width=month3.getSize().getWidth();
		int month3Height=month3.getSize().getHeight();
		int month3MidX=month3x+(month3Width/2);
		int month3MidY=month3y+(month3Height/2);*/
		
		int startx=month1MidX;
		int starty=month1MidY;
		int endx=month1MidX;
		int endy=month1MidY+100;
		
		TouchAction act=new TouchAction(getDriver());
		act.longPress(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy)).release().perform();
						
	}
	
	public void VerifyScrollOfDatePickerUp() {
		WebElement month1=getDriver().findElement(By.xpath("(//*[@class='android.widget.NumberPicker'])[1]//*[@id='numberpicker_input']//following-sibling::*"));
		int month1x=month1.getLocation().getX();
		int month1y=month1.getLocation().getY();
		int month1Width=month1.getSize().getWidth();
		int month1Height=month1.getSize().getHeight();
		int month1MidX=month1x+(month1Width/2);
		int month1MidY=month1y+(month1Height/2);
		
		/*
		WebElement month3=getDriver().findElement(By.xpath("(//*[@class='android.widget.NumberPicker'])[1]//*[@id='numberpicker_input']//following-sibling::*"));
		int month3x=month3.getLocation().getX();
		int month3y=month3.getLocation().getY();
		int month3Width=month3.getSize().getWidth();
		int month3Height=month3.getSize().getHeight();
		int month3MidX=month3x+(month3Width/2);
		int month3MidY=month3y+(month3Height/2);*/
		
		int startx=month1MidX;
		int starty=month1MidY;
		int endx=month1MidX;
		int endy=month1MidY+100;
		
		TouchAction act=new TouchAction(getDriver());
		act.longPress(PointOption.point(startx, starty)).moveTo(PointOption.point(endy, endx)).release().perform();
						
	}

	public void VerifyScrollOfMonthPickerDown() {
		WebElement month1=getDriver().findElement(By.xpath("(//*[@class='android.widget.NumberPicker'])[2]//*[@id='numberpicker_input']//preceding-sibling::*"));
		int month1x=month1.getLocation().getX();
		int month1y=month1.getLocation().getY();
		int month1Width=month1.getSize().getWidth();
		int month1Height=month1.getSize().getHeight();
		int month1MidX=month1x+(month1Width/2);
		int month1MidY=month1y+(month1Height/2);
		
		/*
		WebElement month3=getDriver().findElement(By.xpath("(//*[@class='android.widget.NumberPicker'])[1]//*[@id='numberpicker_input']//following-sibling::*"));
		int month3x=month3.getLocation().getX();
		int month3y=month3.getLocation().getY();
		int month3Width=month3.getSize().getWidth();
		int month3Height=month3.getSize().getHeight();
		int month3MidX=month3x+(month3Width/2);
		int month3MidY=month3y+(month3Height/2);*/
		
		int startx=month1MidX;
		int starty=month1MidY;
		int endx=month1MidX;
		int endy=month1MidY+100;
		
		TouchAction act=new TouchAction(getDriver());
		act.longPress(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy)).release().perform();
						
	}
	
	public void VerifyScrollOfMonthPickerUp() {
		WebElement month1=getDriver().findElement(By.xpath("(//*[@class='android.widget.NumberPicker'])[2]//*[@id='numberpicker_input']//following-sibling::*"));
		int month1x=month1.getLocation().getX();
		int month1y=month1.getLocation().getY();
		int month1Width=month1.getSize().getWidth();
		int month1Height=month1.getSize().getHeight();
		int month1MidX=month1x+(month1Width/2);
		int month1MidY=month1y+(month1Height/2);
		
		/*
		WebElement month3=getDriver().findElement(By.xpath("(//*[@class='android.widget.NumberPicker'])[1]//*[@id='numberpicker_input']//following-sibling::*"));
		int month3x=month3.getLocation().getX();
		int month3y=month3.getLocation().getY();
		int month3Width=month3.getSize().getWidth();
		int month3Height=month3.getSize().getHeight();
		int month3MidX=month3x+(month3Width/2);
		int month3MidY=month3y+(month3Height/2);*/
		
		int startx=month1MidX;
		int starty=month1MidY;
		int endx=month1MidX;
		int endy=month1MidY+100;
		
		TouchAction act=new TouchAction(getDriver());
		act.longPress(PointOption.point(startx, starty)).moveTo(PointOption.point(endy, endx)).release().perform();
						
	}
	public void VerifyScrollOfYearPickerDown() {
		WebElement month1=getDriver().findElement(By.xpath("(//*[@class='android.widget.NumberPicker'])[3]//*[@id='numberpicker_input']//preceding-sibling::*"));
		int month1x=month1.getLocation().getX();
		int month1y=month1.getLocation().getY();
		int month1Width=month1.getSize().getWidth();
		int month1Height=month1.getSize().getHeight();
		int month1MidX=month1x+(month1Width/2);
		int month1MidY=month1y+(month1Height/2);
		
		/*
		WebElement month3=getDriver().findElement(By.xpath("(//*[@class='android.widget.NumberPicker'])[1]//*[@id='numberpicker_input']//following-sibling::*"));
		int month3x=month3.getLocation().getX();
		int month3y=month3.getLocation().getY();
		int month3Width=month3.getSize().getWidth();
		int month3Height=month3.getSize().getHeight();
		int month3MidX=month3x+(month3Width/2);
		int month3MidY=month3y+(month3Height/2);*/
		
		int startx=month1MidX;
		int starty=month1MidY;
		int endx=month1MidX;
		int endy=month1MidY+100;
		
		TouchAction act=new TouchAction(getDriver());
		act.longPress(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy)).release().perform();
						
	}
	
	public void VerifyScrollOfYearPickerUp() {
		WebElement month1=getDriver().findElement(By.xpath("(//*[@class='android.widget.NumberPicker'])[3]//*[@id='numberpicker_input']//following-sibling::*"));
		int month1x=month1.getLocation().getX();
		int month1y=month1.getLocation().getY();
		int month1Width=month1.getSize().getWidth();
		int month1Height=month1.getSize().getHeight();
		int month1MidX=month1x+(month1Width/2);
		int month1MidY=month1y+(month1Height/2);
		
		/*
		WebElement month3=getDriver().findElement(By.xpath("(//*[@class='android.widget.NumberPicker'])[1]//*[@id='numberpicker_input']//following-sibling::*"));
		int month3x=month3.getLocation().getX();
		int month3y=month3.getLocation().getY();
		int month3Width=month3.getSize().getWidth();
		int month3Height=month3.getSize().getHeight();
		int month3MidX=month3x+(month3Width/2);
		int month3MidY=month3y+(month3Height/2);*/
		
		int startx=month1MidX;
		int starty=month1MidY;
		int endx=month1MidX;
		int endy=month1MidY+100;
		
		TouchAction act=new TouchAction(getDriver());
		act.longPress(PointOption.point(startx, starty)).moveTo(PointOption.point(endy, endx)).release().perform();
						
	}
	public void SwipeDownSvedMomentTab() {
		WebElement month1=getDriver().findElement(By.xpath("//*[@resource-id='com.zee5.hipi:id/view']"));
		int month1x=month1.getLocation().getX();
		int month1y=month1.getLocation().getY();
		int month1Width=month1.getSize().getWidth();
		int month1Height=month1.getSize().getHeight();
		int month1MidX=month1x+(month1Width/2);
		int month1MidY=month1y+(month1Height/2);
		
		/*
		WebElement month3=getDriver().findElement(By.xpath("(//*[@class='android.widget.NumberPicker'])[1]//*[@id='numberpicker_input']//following-sibling::*"));
		int month3x=month3.getLocation().getX();
		int month3y=month3.getLocation().getY();
		int month3Width=month3.getSize().getWidth();
		int month3Height=month3.getSize().getHeight();
		int month3MidX=month3x+(month3Width/2);
		int month3MidY=month3y+(month3Height/2);*/
		
		int startx=month1MidX;
		int starty=month1MidY;
		int endx=month1MidX;
		int endy=month1MidY+100;
		
		TouchAction act=new TouchAction(getDriver());
		act.longPress(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy)).release().perform();
						
	}
	public void swipeRail(By byLocator, String direction,int k) {
		TouchAction action = new TouchAction(getDriver());
		waitTime(1000);
		WebElement ele = getDriver().findElement(byLocator);
		int x = ele.getLocation().getX();
		int y = ele.getLocation().getY();
		int width = ele.getSize().getWidth();
		int height = ele.getSize().getHeight();
		for(int i=1;i<=k;i++) {
			if (direction.equalsIgnoreCase("Left")) {
				int startx=x+((width*3)/4);
				int endx=x+(width/4);
				int requiredy= y+(height/2);
				action.press(PointOption.point(startx, requiredy)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				.moveTo(PointOption.point(endx, requiredy)).release().perform();
			}
			if (direction.equalsIgnoreCase("Right")) {
				int startx=x+(width/4);
				int endx=x+((width*3)/4);
				int requiredy= y+(height/2);
				action.press(PointOption.point(startx, requiredy)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				.moveTo(PointOption.point(endx, requiredy)).release().perform();
				
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	public void SwipeAnElementAndroid(WebElement element, int posx, int posy) {
		@SuppressWarnings("rawtypes")
		AndroidTouchAction touchAction=new AndroidTouchAction(getDriver());
		touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(posx, posy))
				.release().perform();
	
	
	
	
	}
	
	//-------TimeStamPMethods------------------------------------------	
	
	public void TimeStampScrollToElementiOS_Up(By Locator, String validationText) throws Exception {
		// Swipe("DOWN", 2);
		for (int i = 1; i <= 40; i++) {

			if (TimeStampverifyElementExist(Locator, validationText) == true) {

				String text = getText(Locator);

				logger.info("Scrolled till " + text);
				//extent.extentLogger("Scroll", "Scrolled till " + text);
				screencapture();
				break;
			}
			waitTime(2000);
			TimeStampPartialSwipe("UP", 1);
		}
	}

	
	/**
	 * Click on a web element.
	 * 
	 * @param byLocator the by locator
	 * 
	 */
	public void TimeStampclick(By byLocator, String validationtext) throws Exception {
		try {
			WebElement element = findElement(byLocator);
			element.click();
			logger.info("Clicked on " + validationtext);
			//extent.extentLogger("click", "Clicked on " + validationtext);
		} catch (Exception e) {
			screencapture();
		}
	}	
	
	public boolean TimeStampwaitForElementDisplayedFastPolling(By byLocator, int seconds, String message) throws InterruptedException {
		for (int time = 0; time <= seconds; time++) {
			try {
				
				softAssert.assertEquals(getDriver().findElement(byLocator).isDisplayed(), true,
						"" + message + " " + " is displayed");
				logger.info(message + " is displayed");
				//extent.extentLogger("checkElementPresent", message + " is displayed");
				return true;
			} catch (Exception e) {
				
			}
		}
		return false;
	}
	
	
	public boolean TimeStampverifyElementExist(By byLocator, String str) throws Exception {
		try {
			WebElement element = findElement(byLocator);
			if (element.isDisplayed()) {
				//extent.extentLogger("checkElementPresent", "" + str + " is displayed");
				logger.info("" + str + " is displayed");
				return true;
			}
		} catch (Exception e) {
			//extent.extentLogger("checkElementPresent", "" + str + " is not displayed");
			logger.info(str + " is not displayed");
			return false;
		}
		return false;
	}	
	
	public boolean TimeStampwaitForElementDisplayediOS(By byLocator, int seconds, String message) throws InterruptedException {
		for (int time = 0; time <= seconds; time++) {
			try {
				Thread.sleep(500);
				softAssert.assertEquals(getDriver().findElement(byLocator).isDisplayed(), true,
						"" + message + " " + " is displayed");
				logger.info(message + " is displayed");
				//extent.extentLogger("checkElementPresent", message + " is displayed");
				return true;
			} catch (Exception e) {
				Thread.sleep(500);
			}
		}
		return false;
	}
	
	public void TimeStamptype(By byLocator, String text, String FieldName) {

		try {
			WebElement element = findElement(byLocator);
			element.sendKeys(text);
			text = text.split("\n")[0];
			logger.info("Typed the value " + text + " into " + FieldName);
			//extent.extentLogger("", "Typed the value " + text + " into " + FieldName);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	/**
	 * Check element present.
	 *
	 * @param byLocator the by locator
	 * @return true, if successful
	 */
	public boolean TimeStampverifyElementPresent(By byLocator, String validationtext) throws Exception {

		try {
			WebElement element = findElement(byLocator);
			softAssert.assertEquals(element.isDisplayed(), true, "" + validationtext + " " + " is displayed");
			logger.info(validationtext + " is displayed");
			//extent.extentLoggerPass("checkElementPresent", validationtext + " is displayed");
			return true;
		} catch (Exception e) {
			softAssert.assertEquals(false, true, validationtext + " " + " is not displayed");
//			softAssert.assertAll();
			logger.error(validationtext + " is not displayed");
			//extent.extentLoggerFail("checkElementPresent", validationtext + " is not displayed");
			return false;
		}
	}
	
	
	/**
	 * Swipes the screen in left or right or Up or Down or direction
	 * 
	 * @param direction to swipe Left or Right or Up or Down
	 * @param count     to swipe
	 */
	@SuppressWarnings("rawtypes")
	public void TimeStampPartialSwipeiOS(String direction, int count) {
		touchAction = new TouchAction(getDriver());
		String dire = direction;

		try {

			if (dire.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {
					Dimension size = getDriver().manage().window().getSize();
					int startx = (int) (size.width * 0.4);
					int endx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					// getDriver().swipe(startx, starty, endx, starty, 1000);
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
					extent.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int endx = (int) (size.width * 0.4);
					int startx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeRight",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("UP")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.85);
					int endy = (int) (size.height * 0.09);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
							.moveTo(PointOption.point(startx, endy)).release().perform();
					logger.info("Swiping screen in " + dire + " direction" + " " + (j + 1) + " times");
//					extent.extentLogger("SwipeUp",
//							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

				}
			}else if (dire.equalsIgnoreCase("DOWN")) {
				
				for (int j = 0; j < count; j++) {

					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.85);
					int endy = (int) (size.height * 0.2);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, endy))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
							.moveTo(PointOption.point(startx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeDown",
							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					
				}
			}

		} catch (Exception e) {
			logger.error(e);

		}
	}
	/**
	 * Swipes the screen in left or right or Up or Down or direction
	 * 
	 * @param direction to swipe Left or Right or Up or Down
	 * @param count     to swipe
	 */
	@SuppressWarnings("rawtypes")
	public void TimeStampPartialSwipe(String direction, int count) {
		touchAction = new TouchAction(getDriver());
		String dire = direction;

		try {

			if (dire.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {
					Dimension size = getDriver().manage().window().getSize();
					int startx = (int) (size.width * 0.4);
					int endx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					// getDriver().swipe(startx, starty, endx, starty, 1000);
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
//					extent.extentLogger("SwipeLeft",
//							"Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int endx = (int) (size.width * 0.4);
					int startx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
//					extent.extentLogger("SwipeRight",
//							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("UP")) {

				for (int j = 0; j < count; j++) {
					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.75);
					int endy = (int) (size.height * 0.05);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, endy)).release().perform();
					logger.info("Swiping screen in " + dire + " direction" + " " + (j + 1) + " times");
//					extent.extentLogger("SwipeUp",
//							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

				}
			}else if (dire.equalsIgnoreCase("DOWN")) {
				
				for (int j = 0; j < count; j++) {

					Dimension size = getDriver().manage().window().getSize();
					int starty = (int) (size.height * 0.8);
					int endy = (int) (size.height * 0.5);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, endy))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
							.moveTo(PointOption.point(startx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
//					extent.extentLogger("SwipeDown",
//							"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					waitTime(5000);
				}
			}

		} catch (Exception e) {
			logger.error(e);

		}
	}
	
	/**
	 * Method to scroll to element-UP
	 */
	public void TimeStampScrollToElementiOS_Down(By Locator, String validationText) throws Exception {

		for (int i = 1; i <= 40; i++) {
			if (TimeStampverifyElementExist(Locator, validationText) == true) {

				String text = getText(Locator);

				logger.info("Scrolled till " + text);
				//extent.extentLogger("Scroll", "Scrolled till " + text);
				screencapture();
				break;
			}
			waitTime(2000);
			 TimeStampPartialSwipe("DOWN", 1);
		}
	}	
	
	
	
	/**
	 * Closes the Keyboard
	 */
	public void TimeStamphideKeyboard() {
		try {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void TimeStamptapiOSAtCenterOfScrreen(int k) {
		@SuppressWarnings("rawtypes")
		TouchAction touchAction=new TouchAction(getDriver());
		
		for(int i=1;i<=k;i++) {
			Dimension size = getDriver().manage().window().getSize();
			int startx =  size.width/2;
			
			int starty = size.height / 2;
		
			touchAction.press(PointOption.point(startx, starty)).perform().release();


			logger.info("Tapped on Screen for "+ i +" time");
		//extent.extentLogger("", "Tapped on Screen for "+ i +" time");
		}
	}
	
	/**
	 * @tap on the center of center for tour dashboard
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public int tapOnFirstSuggestion(By locator) throws Exception {
		WebElement element = getDriver().findElement(locator);
		String tourText = getText(locator);
		Point point = element.getLocation();
		// capturing height & length of the element
		int length = element.getSize().getWidth();
		int height = element.getSize().getHeight();
		// capturing the stating Y co-ordinate
		int getY = point.getY();
		// moving to the middle of the suggestion from the Y co-ordinate
		int middleY = (int) (getY + height * 1.5);
		TouchAction ta = new TouchAction(getDriver());
		
		ta.tap(PointOption.point(length / 2, middleY)).perform();
		logger.info(tourText + " dashboard tour is displayed Tapped at the center of Screen");
		extent.extentLoggerPass("Tour", tourText + " dashboard tour is displayed Tapped at the center of Screen");
		return middleY;
	}
		
	public void suggestionTapMidScreen(By locator) throws Exception {
		int guideSuggestion = 1;
		for (int i = 0; i < guideSuggestion; i++) {
			tapOnFirstSuggestion(locator);
			
			if (verifyElementDisplayed(locator)) {
				guideSuggestion++;
			} else {
				break;
			}
		}
	}

	

	/*
	 * tap on element
	 */
	public void tapiOS1(WebElement element,int k) {
		@SuppressWarnings("rawtypes")
		TouchAction touchAction=new TouchAction(getDriver());
		for(int i=1;i<=k;i++) {
		touchAction.tap(new TapOptions().withElement(ElementOption.element(element))).perform();
		logger.info("Tapped on element for "+ i +" time");
		extent.extentLogger("", "Tapped on element for "+ i +" time");
		}
	}
	
	
//	 public static void explicitWaitVisibility(By element, int time) throws Exception {
//	        WebDriverWait wait = new WebDriverWait(getDriver(), time);
//	        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(element)));
//	    }
	
	public static void explicitWaitVisible(By byLocator, int time) throws Exception {
		try
		{
        WebDriverWait wait = new WebDriverWait(getDriver(), time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		}catch(Exception e)
		{
			logger.info("Failed to wait until element visibility of : "+String.valueOf(byLocator));
			extent.extentLogger("", "Failed to wait until element visibility of : "+String.valueOf(byLocator));
		}
    }
	
	 public static String getTextVal(By byLocator, String concatValue) throws Exception {
	        String Value = null;
	        WebElement element = getDriver().findElement(byLocator);
	        Value = element.getText();
	        String finalValue = Value + " " + concatValue;
	        return finalValue;
	    }
	 
	 public  void assertionValidation(String actual, String expected) throws Exception {

		 softAssert.assertEquals(actual,expected);
         if(actual.equals(expected))
         {
             logger.info(actual+" and "+expected+" are matched");
             extentLoggerPass("Assertion",actual+" and "+expected+" are matched");
         }else {
             logger.info(actual+" and "+expected+" are not matched");
             extentLoggerFail("Assertion",actual+" and "+expected+" are not matched");
         }
   }
	 
//	 public static void setScreenshotSource() {
//	        if (getPlatformFromtools().equalsIgnoreCase("Web")) {
//	            src = ((TakesScreenshot) getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
//	        } else if (getPlatformFromtools().equals("Android") || getPlatformFromtools().equals("PWA") || getPlatformFromtools().equals("TV")) {
//	            src = ((TakesScreenshot) getAppiumDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
//	        } else if (getPlatformFromtools().equalsIgnoreCase("MPWA")) {
//	            src = ((TakesScreenshot) getAppiumDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
//	        }
//	    }
	 
	 public static void pullToRefresh(){

		    int deviceWidth = getDriver().manage().window().getSize().getWidth();
		    int deviceHeight = getDriver().manage().window().getSize().getHeight();
		    int midX = (deviceWidth / 2);
		    int midY = (deviceHeight / 2);
		    int bottomEdge = (int) (deviceHeight * 0.85f);
		    new TouchAction(getDriver())
		            .press(PointOption.point(midY, midY))
		          //  .press(point(midX,midY))
		           // .waitAction(waitOptions(ofMillis(1000)))
		              .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		          //  .moveTo(point(midX, bottomEdge))
		              .moveTo(PointOption.point(midY, bottomEdge))
		            .release().perform();
		}
	 
	 public void scrollToVertical(String elementName)
	 {
		 WebElement element = getDriver().findElementByName(elementName);

	        JavascriptExecutor js = (JavascriptExecutor) getDriver();
	        HashMap<String, String> scrollObjects = new HashMap<String, String>();
	        scrollObjects.put("element", ((RemoteWebElement) element).getId());
	        js.executeScript("mobile: scrollTo", scrollObjects);

	 }
	 
	 public static void scrollToFirstHorizontalScrollableElement(String textToSearch) {
		 getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setAsHorizontalList().scrollIntoView(new UiSelector().text(\""+textToSearch+"\"))"));
		 }
	 
	 
	 public  void assertNotEquals(String actual, String expected) throws Exception {
	       softAssert.assertNotEquals(actual,expected);
	        if(actual!=expected)
	        {
	            logger.info(actual+" and "+expected+" are not matched");
	            extentLoggerPass("Assertion",actual+" and "+expected+" are not matched");
	        }else {
	            logger.info(actual+" and "+expected+" are matched");
	            extentLoggerFail("Assertion",actual+" and "+expected+" are matched");
	        }
	    }
	 
	 public void newScrollMethodVertical(double start_xd,double start_yd, double end_xd, double end_yd)
	 {
		 Dimension dimension=getDriver().manage().window().getSize();
		 int start_x =  (int) (dimension.getWidth()*start_xd); //0.5
		 int start_y =  (int) (dimension.getHeight()*start_yd); //0.8
		 
		 int end_x = (int) (dimension.getWidth()*end_xd);//0.5
		 int end_y = (int) (dimension.getHeight()*end_yd);//0.2
		 
		 TouchAction touch=new TouchAction(getDriver());
		 touch.press(PointOption.point(start_x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		 .moveTo(PointOption.point(end_x, end_y)).release().perform();
	 }
	 
	 public boolean scroll_To_Text(By by,String attribute,String text)
	 {
		 int a=0;
		 boolean flag=false;
		 do {
			 List<WebElement>lists=getDriver().findElements(by);
			 for(WebElement e : lists)
			 {
				 System.out.println(e.getAttribute(attribute));
				 if(e.getAttribute(attribute).equalsIgnoreCase(text))
				 {
					 flag=true;
					 System.out.println("Found the value == : "+text);
					 break;
				 }
				 
			 }
			 if(!flag)
			 newScrollMethodVertical(0.5, 0.8, 0.5, 0.2);
			 a++;
			break;
		 }while(!flag || a==5);
		return flag;
		 
	 }
	 
	 public void newScrollMethodHorizontal(double start_xd,double start_yd, double end_xd, double end_yd)
	 {
		 Dimension dimension=getDriver().manage().window().getSize();
		 int start_x =  (int) (dimension.getWidth()*start_xd); //0.5
		 int start_y =  (int) (dimension.getHeight()*start_yd); //0.8
		 
		 int end_x = (int) (dimension.getWidth()*end_xd);//0.5
		 int end_y = (int) (dimension.getHeight()*end_yd);//0.2
		 
		 TouchAction touch=new TouchAction(getDriver());
		 touch.press(PointOption.point(start_x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		 .moveTo(PointOption.point(end_x, end_y)).release().perform();
	 }
	 
	 
	 public void tapUsingCoordinates(int x, int y) throws Exception {
	        TouchAction t=new  TouchAction(getDriver());
	        try
	        {
	            t.tap(PointOption.point(x, y)).perform().release();
	            logger.info("Tapped on "+x+","+y+" co-ordinates");
	            extentLoggerPass("Tap", "Tapped on "+x+" , "+y+" co-ordinates");
	        }catch(Exception e)
	        {
	            logger.info("Failed to tap on"+x+","+y+" co-ordinates");
	            extentLoggerFail("Tap", "Failed to Tap on"+x+","+y+" co-ordinates");
	        }
	    }
	 
	
}
