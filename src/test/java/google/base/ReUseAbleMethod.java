package google.base;

import io.restassured.path.json.JsonPath;

public class ReUseAbleMethod 

{
	
	
	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js = new JsonPath(response);
		return js;
	}

}
