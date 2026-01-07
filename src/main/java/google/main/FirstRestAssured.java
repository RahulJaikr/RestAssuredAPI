package google.main;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;


public class FirstRestAssured 
{
	
	
public static void main(String []args)
{
	FirstRestAssured fra = new FirstRestAssured();
	fra.firstTest();
}
	
	public void firstTest()
	{
	RestAssured.baseURI ="https://rahulshettyacademy.com/";

	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body("{\r\n"
			+ " \r\n"
			+ "	\"location\":{\r\n"
			+ " \r\n"
			+ "    	\"lat\" : -38.383494,\r\n"
			+ " \r\n"
			+ "    	\"lng\" : 33.427362\r\n"
			+ " \r\n"
			+ "	},\r\n"
			+ " \r\n"
			+ "	\"accuracy\":50,\r\n"
			+ " \r\n"
			+ "	\"name\":\"Frontline house\",\r\n"
			+ " \r\n"
			+ "	\"phone_number\":\"(+91) 983 893 3937\",\r\n"
			+ " \r\n"
			+ "	\"address\" : \"29, side layout, cohen 09\",\r\n"
			+ " \r\n"
			+ "	\"types\": [\"shoe park\",\"shop\"],\r\n"
			+ " \r\n"
			+ "	\"website\" : \"http://google.com\",\r\n"
			+ " \r\n"
			+ "	\"language\" : \"French-IN\"\r\n"
			+ " \r\n"
			+ "}\r\n"
			+ " \r\n"
			+ "").
	when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200);
	
	
	}
}
