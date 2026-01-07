package google.test;

import org.testng.annotations.Test;

import google.parsejson.AddPlace;
import google.parsejson.Locations;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.LinkedList;
import java.util.List;
public class SerializationTest 
{
	
	
	
	@Test
	public void serializationTest1()
	{
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("Raja Road Delhi 235332");
		ap.setLanguage("Hindi-English");
		
		ap.setName("Josh Angston");
		ap.setPhone_number("76849995933");
		List<String>ml = new LinkedList();
		ml.add("shoe park");
		ml.add("Salon");
		ap.setTypes(ml);
		Locations l = new Locations();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setWebsite("https://rahulshettyacademy.com");
		ap.setLocation( l);
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all()
		.queryParam("key","qaclick123")
		.body(ap)
		.when().post("/maps/api/place/add/json")
		.then().assertThat()
		.statusCode(200)
		.extract()
		.response().asString();
		
	}

}
