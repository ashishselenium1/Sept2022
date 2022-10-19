package keywords;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ApplicationKeywords extends ValidationKeywords{
	
	public ApplicationKeywords(ExtentTest test) {
		System.out.println("ApplicationKeywords constructor");
		// init prop file object
		prop = new Properties();
		String path = System.getProperty("user.dir")+"//src//test//resources//project.properties";
		try {
			FileInputStream fd = new FileInputStream(path);
			prop.load(fd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.test=test;
		softAssert = new SoftAssert();
		System.out.println(prop.getProperty("app_url"));
		System.out.println(getProperty("app_url"));
		System.out.println(prop.getProperty("my_portfolio"));
	}
	
	
	public boolean isLoggedIn() {
			return isElementPresent("search_field_xpath");
	}


	public void defaultLogin() {
		type("username_name", getProperty("login_username"));
	    type("password_name", getProperty("login_password"));
	    click("login_button_id");
	}


	public void gotoLead(String leadName) {
		driver.findElement(By.linkText(leadName)).click();
	}
}
