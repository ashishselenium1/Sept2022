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

public class DeleteLeadTest {
	ExtentReports reports;
	ExtentTest test;
	
	@BeforeMethod
	public void init() {
		reports = ExtentManager.getReport(System.getProperty("user.dir")+"//reports//");
		test = reports.createTest("Delete Lead Test");
	}
	
	@AfterMethod
	public void quit() {
		reports.flush();
	}
	
	@Test(dataProvider = "getData")
	public void deleteLeadTest(JSONObject data) {
		// prop initialized
		ApplicationKeywords app = new ApplicationKeywords(test);
		app.log(data.toString());
		if(((String)data.get("runmode")).equals("N"))
			app.skip("Skipping the test as runmode is N");
		
		app.openBrowser("Chrome");
		app.navigate("app_url");
	    app.defaultLogin();
		app.assertAll();
		app.moveMouseToElement("leads_xpath");
		app.click("view_lead_xpath");
		app.gotoLead((String)data.get("lead_name"));
		app.click("actions_xpath");
		app.click("delete_lead_option_xpath");
		// verify
	}
	
	@DataProvider
	public Object[][] getData() {
		return new DataUtil().getData(System.getProperty("user.dir")+"//data//delete_lead_test.json");
	}
}

//jenkins, github - team
// grid
