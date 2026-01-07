package google.test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;



public class SecondTest 
{
	
	
public static void main(String []args) throws IOException
{
	SecondTest fra = new SecondTest();
	fra.firstTest();
}
	
	public void firstTest() throws IOException
	{
	RestAssured.baseURI ="https://rahulshettyacademy.com/";

	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\52404445\\JsonPath\\json.txt")))).
	when().post("/maps/api/place/add/json").
	then().log().all().assertThat().statusCode(200).
	body("scope",equalTo("APP"))
	.header("server", "Apache/2.4.52 (Ubuntu)");
	
	
	}
	public void firstTest2() throws IOException
	{
	RestAssured.baseURI ="https://rahulshettyacademy.com/";

	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\52404445\\JsonPath\\json.txt")))).
	when().post("/maps/api/place/add/json").
	then().log().all().assertThat().statusCode(200).
	body("scope",equalTo("APP"))
	.header("server", "Apache/2.4.52 (Ubuntu)");
	
	
	}
}
