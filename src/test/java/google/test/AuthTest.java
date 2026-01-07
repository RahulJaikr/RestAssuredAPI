package google.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import google.parsejson.GetCourse;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
public class AuthTest
{
	
	
	@Test
	public void getToken()
	{
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
		
	String response=	given()
		.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.log().all()
		.when()
		.log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
	
	//System.out.println("==============="+response);
	JsonPath js = google.base.ReUseAbleMethod.rawToJson(response);
	String access_token = js.getString("access_token");
	
	GetCourse gc   = given().queryParam("access_token", access_token)
	.log().all()
	.when()
	.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
	.as(GetCourse.class);
	
	System.out.println(gc.getLinkedIn());
	System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
	getCoursenames( gc);
	}

	
	@Test(dependsOnMethods = {"getToken"})
	public void getCoursenames(GetCourse gc)
	{
		List<String> expWebCourseTitle= new LinkedList();
		expWebCourseTitle.addAll(
			    Arrays.asList("Selenium Webdriver Java", "Cypress", "Protractor")
			);
		
		List<String> getWebCourseTitle= new LinkedList();
		int lenth = gc.getCourses().getWebAutomation().size();
		for( int i =0; i <lenth;i++)
		{
		String cn =	gc.getCourses().getWebAutomation().get(i).getCourseTitle();
		getWebCourseTitle.add(cn);
		System.out.println(cn);
		}
		Assert.assertEquals(expWebCourseTitle, getWebCourseTitle);
		
	}
}
