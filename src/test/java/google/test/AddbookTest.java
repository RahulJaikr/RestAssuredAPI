package google.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import google.base.Payload;
import google.base.ReUseAbleMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class AddbookTest 
{
	
	
	
	@Test(dataProvider="bookData")
	public void addBook(String aisle, String isbn) throws InterruptedException
	{
		 RestAssured.baseURI="http://216.10.245.166";
		RestAssured.useRelaxedHTTPSValidation();
		String responseAdd =given().log().all().
		header("Content-Type","application/json").
		body(Payload.addBookD(isbn,aisle))
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

	
	@DataProvider(name = "bookData")
	public Object[][] runData()
	{
		return new Object[][]{{"LABDK2","998112"}, {"kABA88","14481"}};
	}
}
