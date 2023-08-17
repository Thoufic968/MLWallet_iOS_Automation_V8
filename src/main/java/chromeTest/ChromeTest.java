package chromeTest;



import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.driverInstance.DriverInstance;
import com.propertyfilereader.PropertyFileReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeTest {
	
	public static ThreadLocal<WebDriver> tlWebDriver = new ThreadLocal<>();

	 public static WebDriver doBrowserSetup(String browserName){

	        WebDriver driver = null;
	        if (browserName.equalsIgnoreCase("chrome")){
	            //steup chrome browser
	            WebDriverManager.chromedriver().setup();


	            //Add options for --headed or --headless browser launch
	            ChromeOptions chromeOptions = new ChromeOptions();
	            chromeOptions.addArguments("-headed");
	            
	            //initialize driver for chrome
	            driver = new ChromeDriver(chromeOptions);

	            //maximize window
	            driver.manage().window().maximize();

	            //add implicit timeout
	            driver.manage()
	           .timeouts()
	           .implicitlyWait(10, TimeUnit.SECONDS);
	            driver.get("https://www.google.com/");

	        }
	   return driver;
	}
	 public static WebDriver getWebDriver() {
			return tlWebDriver.get();	
		}
	 
//	 public static WebDriver getWebDriver(){
//	        return tlWebDriver.get();
//	    }
	 
	 public static void LaunchBrowser(String browserName,String url) throws InterruptedException {
		//	setHandler(new PropertyFileReader("properties/AppPackageActivity.properties"));
			if (browserName.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().version("0.26.0").setup();
				
				tlWebDriver.set(new FirefoxDriver());
			} else if (browserName.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");
				options.addArguments("enable-automation");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-infobars");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--disable-browser-side-navigation");
				options.addArguments("--disable-gpu");
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);
				tlWebDriver.set(new ChromeDriver(options));
			}
			else if (browserName.equalsIgnoreCase("IE")) {
				tlWebDriver.set(new InternetExplorerDriver());
			}
			else if (browserName.equalsIgnoreCase("MSEdge")) {
				tlWebDriver.set(new EdgeDriver());
			}
			tlWebDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			tlWebDriver.get().get(url);
			tlWebDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			Thread.sleep(3000);
			
			
		}
	 
	 public static void tearDown(){
	        getWebDriver().quit();
	 }
	 
//	 public static void main(String[] args) throws InterruptedException {
//		//doBrowserSetup("chrome");
//		LaunchBrowser("Chrome");
//		tearDown();
//	}
}
