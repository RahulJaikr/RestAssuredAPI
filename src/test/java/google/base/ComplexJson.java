package google.base;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(Payload.coursePrice());
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//print purchase amount
		
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
	String title=	js.getString("courses[0].title");
	//System.out.println(title);
	int []arr = new int [count];
	int sum =0;
	int a=0;
	for(int i =0;i<count;i++)
	{
		sum += js.getInt("courses["+i+"].price");
		sum *= js.getInt("courses["+i+"].copies");
		arr[i]=sum;
		sum=0;
	}
	for(int a1 : arr)
	{
		a= a+a1;
	}
	System.out.println(a);
	
	//print no of copies:
	int cop =0;
	for(int i =0;i<count;i++)
	{
		if( js.getString("courses["+i+"].title").contains("Selenium"))
		{
			cop= js.getInt("courses["+i+"].copies");
			break;
		}
		
	}
	//System.out.println(cop);
	Assert.assertEquals(a, totalAmount);
	}

}
