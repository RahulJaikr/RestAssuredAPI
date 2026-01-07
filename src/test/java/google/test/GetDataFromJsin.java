package google.test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;



public class GetDataFromJsin 
{
	
	
public static void main(String []args)
{
	GetDataFromJsin fra = new GetDataFromJsin();
	fra.firstTest();
}
	
	public void firstTest()
	{
	RestAssured.baseURI ="https://rahulshettyacademy.com/";

	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
	.body(google.base.Payload.addPlace()).
	when().post("/maps/api/place/add/json").
	then().log().all().assertThat().statusCode(200).
	body("scope",equalTo("App"))
	.header("server", "Apache/2.4.52 (Ubuntu)");
	
	
	}
}
