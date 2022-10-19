package temp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BuildingData {

	public static void main(String[] args) throws Exception {
	  JSONParser parser = new JSONParser();
	  Reader reader = new FileReader(System.getProperty("user.dir")+"//data//login_test.json");
	  JSONObject testDetails = (JSONObject)  parser.parse(reader);
	  
	  System.out.println(testDetails);
	  JSONArray testData = (JSONArray)testDetails.get("test_data");
	  System.out.println(testData.size());
	  Object[][] data = new Object[testData.size()][1];
	  
	  for(int i=0;i<testData.size();i++) {
		 JSONObject testDataDetails = (JSONObject) testData.get(i);
		 System.out.println("testDataDetails "+testDataDetails);
		 data[i][0]=testDataDetails;
	  }
	  	// here
			
	}

}
