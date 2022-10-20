package keywords;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reports.ExtentManager;

public class GenericKeywords {
	
	public WebDriver driver;
	public Properties prop;
	public ExtentTest test;
	public SoftAssert softAssert;
	
	public void openBrowser(String browserName) {
		log("Opening the browser "+browserName);
		System.setProperty("webdriver.chrome.driver", "D:\\Ashish\\driverexes\\chromedriver.exe");
		
		if(browserName.equals("Chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equals("Mozilla")) {
			driver = new FirefoxDriver();
		}else if(browserName.equals("Edge")) {
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));//present
	}
	
	public void navigate(String urlKey) {
		log("Navigating to URL "+ urlKey);
		driver.get(getProperty(urlKey));
	}
	
	public void click(String locatorKey) {
		log("Clicking on "+locatorKey);
		getElement(locatorKey).click();
	}
	
	public void type(String locatorKey, String data) {
		log("Typing in  "+locatorKey);
		getElement(locatorKey).sendKeys(data);
	}
	
	public void select(String locator, String data) {
		WebElement e = getElement(locator);
		Select s = new Select(e);
		s.selectByVisibleText(data);
	}
	
	public void moveMouseToElement(String locator) {
		WebElement e = getElement(locator);
		Actions act = new Actions(driver);
		act.moveToElement(e).build().perform();
	}
	
	/**********************Utility Functions**************************/
	// central function to extract objects
	// has to ensure - present and visible
	public WebElement getElement(String locatorKey) {
		//String locator = getProperty(locatorKey);
		By locator = getLocator(locatorKey);
		WebElement e = null;
		try {	
			if(!isElementPresent(locatorKey)) {
				// report a critical failure - stopping the test
				fail("Element not present "+locatorKey,true);
			}
			e = driver.findElement(locator);
		}catch(Exception ex) {
			// report a critical failure - stopping the test
			fail("Element not present "+locatorKey,true);
		}		
		return e;
	}
	// presence and visibility
	public boolean isElementPresent(String locatorKey) {
		By locator = getLocator(locatorKey);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));// presence
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));// visibility
		}catch(Exception ex) {
			return false;
		}
		return true;
	}
	
	public String getProperty(String key) {
		return prop.getProperty(key);
	}
	
	public By getLocator(String locatorKey) {
		By locator = null;
		if(locatorKey.endsWith("_xpath"))
			locator = By.xpath(getProperty(locatorKey));
		else if(locatorKey.endsWith("_id"))
			locator = By.id(getProperty(locatorKey));
		else if(locatorKey.endsWith("_name"))
			locator = By.name(getProperty(locatorKey));
		else if(locatorKey.endsWith("_css"))
			locator = By.cssSelector(getProperty(locatorKey));
		
		return locator;
	}
	
	/***********************Reporting Functions*************************/
	public void log(String message) {
		test.log(Status.INFO, message);
	}
	
	public void fail(String failureMessage,boolean stopTest) {
		// fail the test in extent reports
		test.log(Status.FAIL, failureMessage);
		// take screenshot of the page
		takeScreenshot();
		// fail the test in testNG
		softAssert.fail(failureMessage);
		if(stopTest)
			assertAll();
	}
	
	public void skip(String message) {
		test.log(Status.SKIP, message);
		throw new SkipException(message);
	}
	
	public void assertAll() {
		softAssert.assertAll();
	}
	public void takeScreenshot() {
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			//put the screenshot in the file
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+"//"+screenshotFile));
			//embed screenshot file in reports
			test.log(Status.INFO,"Screenshot-> "+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+"//"+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
