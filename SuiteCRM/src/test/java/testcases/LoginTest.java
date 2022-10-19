package testcases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import keywords.ApplicationKeywords;
import reports.ExtentManager;
import util.DataUtil;

public class LoginTest {

	ExtentReports reports;
	ExtentTest test;
	
	@BeforeMethod
	public void init() {
		reports = ExtentManager.getReport(System.getProperty("user.dir")+"//reports//");
		test = reports.createTest("Login Test");
	}
	
	@AfterMethod
	public void quit() {
		reports.flush();
	}
	
	@Test(dataProvider = "getData")
	public void doLogin(JSONObject data) {
		// prop initialized
		ApplicationKeywords app = new ApplicationKeywords(test);
		app.log(data.toString());
		if(((String)data.get("runmode")).equals("N"))
			app.skip("Skipping the test as runmode is N");
		
		app.openBrowser("Chrome");
		app.navigate("app_url");
	    app.type("username_name", ((String)data.get("username")));
	    app.type("password_name", ((String)data.get("password")));
	    app.click("login_button_id");
		//if(!app.isLoggedIn())
			app.fail("User could not login", true);
		
		app.assertAll();
	}
	
	@DataProvider
	public Object[][] getData() {
		return new DataUtil().getData(System.getProperty("user.dir")+"//data//login_test.json");
	}
	
	
	

}
