package ex04;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONExample {

	public static void main(String[] args) throws ParseException {
		
		// JSONObject
		JSONObject json1 = new JSONObject();
		json1.put("key1", 1);
		json1.put("key2", 2);
		
		JSONObject json2 = new JSONObject();
		json2.put("key3", 3);
		
		// JSONArray 
		JSONArray jsonArray = new JSONArray();
		
		jsonArray.add(json1);
		jsonArray.add(json2);
		
		System.out.println(jsonArray.toString());

	}
}
