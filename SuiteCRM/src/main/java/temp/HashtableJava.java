package temp;

import java.util.Hashtable;

public class HashtableJava {

	public static void main(String[] args) {
		Hashtable<String,String> table = new Hashtable<String,String>();
		table.put("key", "value");
		table.put("name", "ashish");
		
		System.out.println(table.get("name"));
		

	}

}
