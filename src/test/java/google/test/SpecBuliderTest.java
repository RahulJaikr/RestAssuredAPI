package google.test;

import org.testng.annotations.Test;

import google.parsejson.AddPlace;
import google.parsejson.Locations;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.LinkedList;
import java.util.List;

public class SpecBuliderTest {

	@Test
	public void serializationTest1() {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("Raja Road Delhi 235332");
		ap.setLanguage("Hindi-English");

		ap.setName("Josh Angston");
		ap.setPhone_number("76849995933");
		List<String> ml = new LinkedList();
		ml.add("shoe park");
		ml.add("Salon");
		ap.setTypes(ml);
		Locations l = new Locations();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setWebsite("https://rahulshettyacademy.com");
		ap.setLocation(l);

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		ResponseSpecification respp = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		// RestAssured.baseURI = "https://rahulshettyacademy.com";

		RequestSpecification response = given().spec(req).body(ap);
		Response ress = response.post("/maps/api/place/add/json").then().spec(respp).extract().response();
				
String res = ress.asString();
System.out.println(res);
	}

	
	
	
	
	
	
	
	
	
}
