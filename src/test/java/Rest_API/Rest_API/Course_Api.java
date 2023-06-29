package Rest_API.Rest_API;

import org.junit.Assert;

import io.restassured.path.json.JsonPath;

public class Course_Api {

	public static void main(String[] args) {
		
		JsonPath js= new JsonPath(mock());
		int count = js.getInt("courses.size()");
	//	int num = Integer.parseInt(count);
		
		
		//1. Print No of courses returned by API
		System.out.println(count);
		
		
		//2.Print Purchase Amount
		int amount = js.getInt("dashboard.purchaseamount");
		System.out.println(amount);
		
		//3. Print Title of the first course
		  String title = js.getString("courses[0].title");
		  System.out.println(title);
		
		// 4. Print All course titles and their respective Prices
		  for(int i=0; i<count; i++)
		  {
			  String course_title=js.getString("courses["+i+"].title");  
			  //price
			  int price=js.getInt("courses["+i+"].price");
			  System.out.println("Course:"+course_title+"price:"+price);
		  }
		  
		 //5. Print no of copies sold by RPA Course 
		  
		  for(int i=0; i<count; i++)
		  {
			  String course_title=js.getString("courses["+i+"].title");  
			  //price
			  if(course_title.equalsIgnoreCase("RPA"))
			  {
			  int copies=js.getInt("courses["+i+"].copies");
			  System.out.println("Course:"+course_title+"copies:"+copies);
			  break;
			  }
		  }
		  
		  
		  //6. Verify if Sum of all Course prices matches with Purchase Amount
		 int sum=0;  
		  for(int i=0; i<count; i++)
		  {
			  
			  int copies=js.getInt("courses["+i+"].copies");
			  int price=js.getInt("courses["+i+"].price");
			  int total  = copies*price;
			  sum=sum+total;
			  
			  
		  }
		  System.out.println(sum);
		  Assert.assertEquals(sum, amount);
		  
	}
	
	
	
	
	
	//Mock payload
	public static String mock() {
		return " {\r\n"
				+ "   \"dashboard\": {\r\n"
				+ "      \"purchaseamount\": 910,\r\n"
				+ "        \"website\": \"ABC\"\r\n"
				+ "},\r\n"
				+ "\"courses\": [\r\n"
				+ "{\r\n"
				+ "\"title\": \"Selenium\",\r\n"
				+ "\"price\": 50,\r\n"
				+ "\"copies\": 6\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"Cyprees\",\r\n"
				+ "\"price\": 40,\r\n"
				+ "\"copies\": 4\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\"price\": 45,\r\n"
				+ "\"copies\": 10\r\n"
				+ "}\r\n"
				+ "]\r\n"
				+ "}\r\n"
				+ "";
		
	}

}
