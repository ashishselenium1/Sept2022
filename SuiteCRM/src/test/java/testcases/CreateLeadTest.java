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

public class CreateLeadTest {
	ExtentReports reports;
	ExtentTest test;
	
	@BeforeMethod
	public void init() {
		reports = ExtentManager.getReport(System.getProperty("user.dir")+"//reports//");
		test = reports.createTest("Create Lead Test");
	}
	
	@AfterMethod
	public void quit() {
		reports.flush();
	}
	
	@Test(dataProvider = "getData")
	public void createLeadTest(JSONObject data) {
		// prop initialized
		ApplicationKeywords app = new ApplicationKeywords(test);
		app.log(data.toString());
		if(((String)data.get("runmode")).equals("N"))
			app.skip("Skipping the test as runmode is N");
		
		app.openBrowser("Chrome");
		app.navigate("app_url");
	    app.defaultLogin();
		app.moveMouseToElement("leads_xpath");
		app.click("create_lead_xpath");
		app.type("first_name_xpath", (String)data.get("first_name"));
		app.type("last_name_xpath", (String)data.get("last_name"));
		app.click("save_lead_button_xpath");
		// you - verify if lead is created
		app.assertAll();
	}
	
	@DataProvider
	public Object[][] getData() {
		return new DataUtil().getData(System.getProperty("user.dir")+"//data//create_lead_test.json");
	}
}
