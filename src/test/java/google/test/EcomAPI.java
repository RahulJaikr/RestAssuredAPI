package google.test;

import org.testng.annotations.Test;

import google.parsejson.LoginOrderDeatils;
import google.parsejson.LoginOrderPlace;
import google.parsejson.LoginOrders;
import google.parsejson.LoginProduct;
import google.parsejson.LoginRequest;
import google.parsejson.LoginResponse;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class EcomAPI {

	public LoginResponse lrsp;
	public LoginProduct lpp;
	public LoginOrderPlace lopp;

	@Test
	public void createAddDeleteOrder() {
		LoginRequest lr = new LoginRequest();
		lr.setUserEmail("Aroan@Gmail.com");
		lr.setUserPassword("Jaikr7566");

		RequestSpecification res = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification resLogin = given().log().all().spec(res).body(lr);
		lrsp = resLogin.when().post("/api/ecom/auth/login").then().extract().response().as(LoginResponse.class);
		System.out.println(lrsp.gettoken());
	}

	@Test(dependsOnMethods = { "createAddDeleteOrder" })
	public void addProduct() {
		RequestSpecification res = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")

				.addHeader("authorization", lrsp.gettoken()).build();

		RequestSpecification prAdd = given().log().all().spec(res).param("productName", "laptop")
				.param("productAddedBy", lrsp.getUserId()).param("productCategory", "fashion")
				.param("productSubCategory", "T-Shirt").param("productPrice", "1200")
				.param("productDescription", "Addias Originals").param("productFor", "men")
				.multiPart("productImage", new File("C:\\Users\\52404445\\JsonPath\\a.png"));

		 lpp = prAdd.when().log().all().post("/api/ecom/product/add-product").then().extract().response()
				.as(LoginProduct.class);

		String productId = lpp.getProductId();
	}
	
	@Test(dependsOnMethods = {"addProduct"})
	public void placeOrder()
	{
		RequestSpecification res = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON)
				.addHeader("authorization", lrsp.gettoken())
				.build();

		LoginOrderDeatils lod11 = new LoginOrderDeatils();
		lod11.setCountry("India");
		lod11.setproductOrderedId(lpp.getProductId());
		
		ArrayList<LoginOrderDeatils> lod1 =  new ArrayList<LoginOrderDeatils>();
		
		lod1.add(lod11);
		LoginOrders orders = new LoginOrders();
		orders.setOrders(lod1);
		System.out.println(lod1);
		
		RequestSpecification createorder = given().log().all().spec(res).body(orders);
		
		 lopp =	createorder.post("/api/ecom/order/create-order")
		.then().log().all().extract().response().as(LoginOrderPlace.class);
	
	System.out.println(lopp.getProductOrderId() + "  "+ lopp.getMessage());
		
	}
	
	@Test(dependsOnMethods= {"placeOrder"})
	public void deleteTheProduct()
	{
		RequestSpecification res = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).addHeader("authorization", lrsp.gettoken()).build();
		
		RequestSpecification resp =given().log().all().spec(res).pathParam("prodcutId",lpp.getProductId());
		
	String Response=	resp.when().delete("/api/ecom/product/delete-product/{prodcutId}")
		.then().log().all().extract().response().asString();
		
	}
}
