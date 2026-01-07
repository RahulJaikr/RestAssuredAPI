package google.test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class Auth2Test 
{
	
	
	@Test
	public void getCode()
	{
		WebDriver  driver = new ChromeDriver();
		
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?auth_url=https://accounts.google.com/o/oauth2/"
				+ "v2/auth&scope=https://www.googleapis.com/auth/userinfo.email&client_id=692183103107-p0m7ent2hk7suguv4vq"
				+ "22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_"
				+ "uri=https://rahulshettyacademy.com/getCourse.php&state=jaikrRahul");
		
		//driver.findElement(By.xpath("//input[contains(@class,'yAlK0b')]")).click();
		driver.findElement(By.xpath("//input[contains(@class,'whsOnd')]")).sendKeys("jain.rahulakhil1");
		
		driver.findElement(By.xpath("(//span[contains(@class,'VfPpkd-vQzf8d')])[2]")).click();
		
	String scode  =	driver.getTitle();
		
		
		
		String response1  = given().queryParams("code","")
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(response1);
	String accessToken = 	js.getString("access_token");
		
	String response =	given().queryParam("access_token", accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php").asString();
	System.out.println(response);
	}

}
