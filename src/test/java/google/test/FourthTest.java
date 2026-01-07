package google.test;

import org.testng.annotations.Test;

import google.base.Payload;
import io.restassured.path.json.JsonPath;

public class FourthTest 
{

	
	@Test
	public void sum()
	{
		JsonPath js = new JsonPath(Payload.coursePrice());
		int sum =0;
		int a=0;
		int count = js.getInt("courses.size()");
		for(int i =0;i<count;i++)
		{
			sum += js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies");	
		}
		System.out.println(sum);
	}
}
