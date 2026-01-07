package google.base;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class ReUseAbleMethod 

{
	
	
	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js = new JsonPath(response);
		return js;
	}
	
	@Test
	public void runone1()
	{
		System.out.println("learn git");
	}

}
