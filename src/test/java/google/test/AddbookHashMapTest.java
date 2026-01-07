package google.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import google.base.Payload;
import google.base.ReUseAbleMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class AddbookHashMapTest 
{
	
	
	
	@Test
	public void addBook() throws InterruptedException
	{
		 Map<String, Object> payload = new HashMap<>();

	        payload.put("name", "Learn Appium Automation with Java");
	        payload.put("isbn", "ABCDA");
	        payload.put("aisle", "17632");
	        payload.put("author", "Smith");
	        
	        
		 RestAssured.baseURI="http://216.10.245.166";
		RestAssured.useRelaxedHTTPSValidation();
		String responseAdd =given().log().all().
		header("Content-Type","application/json").
		body(payload)
		.when()
		.post("/Library/Addbook.php")
		.then().log().all().
		assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println(responseAdd);
		JsonPath js = ReUseAbleMethod.rawToJson(responseAdd);
		String id = js.get("ID");
		System.out.println(id);
		
		Thread.sleep(3000);
		//Get Book
	String getBookRespoonse =	given().log().all().queryParam("ID", id)
		.when().get("/Library/GetBook.php")
		.then().assertThat().log().all()
		//.statusCode(200)
		.extract().response().asString();
		
		System.out.println("Book is getting SucesssFully "+getBookRespoonse);
		Thread.sleep(3000);
		//Delete book
		String deleteResponse = given().log().all().header("Content-Type","application/json")
		.body("{\"ID\":\"" + id + "\"}")
		.when().delete("/Library/DeleteBook.php")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	}

	@Test
	public void runTest1()
	{
		System.out.println("chnages has done");
	}
	
}
