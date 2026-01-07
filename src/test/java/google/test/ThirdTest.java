package google.test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;



public class ThirdTest {

	public static void main(String[] args) {
		ThirdTest fra = new ThirdTest();
		fra.firstTest();
	}

	public void firstTest() {
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		RestAssured.useRelaxedHTTPSValidation();
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(google.base.Payload.addPlace()).when().post("/maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract()
				.response().asString();

		// System.out.println(response);
		JsonPath js = google.base.ReUseAbleMethod.rawToJson(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);

		String newAddress = "Run Inida Run Today";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeId + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Get Place
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract()
				.response().asString();

		//JsonPath js1 = new JsonPath(getPlaceResponse);
		JsonPath js1 = google.base.ReUseAbleMethod.rawToJson(getPlaceResponse);
		String address = js1.getString("address");
		Assert.assertEquals(newAddress, address);
	}
}
