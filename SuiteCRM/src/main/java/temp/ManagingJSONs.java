package temp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ManagingJSONs {

	public static void main(String[] args) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
		Reader reader = new FileReader(System.getProperty("user.dir")+"//data//sample.json");
		JSONObject londonDetails = (JSONObject)  parser.parse(reader);
		System.out.println(londonDetails);
		String name = (String) londonDetails.get("name");
		System.out.println(name);
		JSONArray temperatures = (JSONArray) londonDetails.get("temperatures");
		System.out.println(temperatures);
		for(int i=0;i<temperatures.size();i++) {
			JSONObject details = (JSONObject)temperatures.get(i);
			System.out.println(details);
			String date = (String)details.get("date");
			String value = (String)details.get("value");
			System.out.println(date+" - "+value);
			
		}
		JSONObject cityDetails = (JSONObject)londonDetails.get("details");
		System.out.println(cityDetails.get("population"));


	}

}
