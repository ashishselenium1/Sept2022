package util;

import java.io.FileReader;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataUtil {
	
	public Object[][] getData(String path){
		JSONParser parser = new JSONParser();
	    Reader reader;
		JSONObject testDetails=null;
		try {
			reader = new FileReader(path);
			testDetails = (JSONObject)  parser.parse(reader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  System.out.println(testDetails);
		  JSONArray testData = (JSONArray)testDetails.get("test_data");
		  System.out.println(testData.size());
		  Object[][] data = new Object[testData.size()][1];
		  
		  for(int i=0;i<testData.size();i++) {
			 JSONObject testDataDetails = (JSONObject) testData.get(i);
			// System.out.println("testDataDetails "+testDataDetails);
			 data[i][0]=testDataDetails;
		  }
		  
		  return data;

	}

}
