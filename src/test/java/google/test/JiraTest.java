package google.test;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JiraTest
{
	
	
	
	@Test
	public void jiraApiTest() throws IOException
	{
		RestAssured.baseURI = "https://rahuljain-1766721291066.atlassian.net";
		
		String resp = given().log().all().header("Content-Type", "application/json")
				.header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic cmFodWxfamFpbl9Ab3V0bG9vay5jb206QVRBVFQzeEZmR0YwenpCeFFwYm5zaThwa2pkZFdsYnh4eWZjMjlxa1BuUnR4azNQUXFNaUU1cnNPYUFsNDcxTW9JelhZT0ZMeWdNQnk5WTBwN0ZyRzJCWEdEMkQzcUpXM3pxdnpjWmxfVzZLSDNLOTFQZmxhOHQ4R003RzhNMUdDcmJXbnZrR0FtUGVYbnNCWUVQR2Rkak9yWkM1TkpuVG9LdUwwb2hWd25NQ3NiaEJWM3J3c09VPTNFMjNEM0NC")
		.body(Files.readAllBytes(Paths.get("C:\\Users\\52404445\\JsonPath\\json2.txt")))
		.log().all().when().post("/rest/api/3/issue")
		.then().log().all()
		.assertThat()
		.statusCode(201)
		.extract().response().asString();
		
		JsonPath js = google.base.ReUseAbleMethod.rawToJson(resp);
		String key  = js.getString("key");
		String id  = js.getString("id");
		
		//Post Attachment 
		given().log().all().pathParam("key", id)
		.header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic cmFodWxfamFpbl9Ab3V0bG9vay5jb206QVRBVFQzeEZmR0YwenpCeFFwYm5zaThwa2pkZFdsYnh4eWZjMjlxa1BuUnR4azNQUXFNaUU1cnNPYUFsNDcxTW9JelhZT0ZMeWdNQnk5WTBwN0ZyRzJCWEdEMkQzcUpXM3pxdnpjWmxfVzZLSDNLOTFQZmxhOHQ4R003RzhNMUdDcmJXbnZrR0FtUGVYbnNCWUVQR2Rkak9yWkM1TkpuVG9LdUwwb2hWd25NQ3NiaEJWM3J3c09VPTNFMjNEM0NC")
		.multiPart("file",new File("C:\\Users\\52404445\\JsonPath\\image (42).png"))
		//.body(Files.readAllBytes(Paths.get("C:\\Users\\52404445\\JsonPath\\image (42).png")))
		.log().all()
		.when().post("/rest/api/3/issue/{key}/attachments").then()
		.log().all()
		.assertThat()
		.statusCode(200)
		.extract().response().asString();
	}

}
