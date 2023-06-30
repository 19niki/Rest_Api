package Rest_API.Rest_API;

import io.restassured.path.json.JsonPath;

public class Complex_Payload
{
   public static String jsonpayload()

{
	 return "{\r\n"
	 		+ "  \"location\": {\r\n"
	 		+ "    \"lat\": -38.383494,\r\n"
	 		+ "    \"lng\": 33.427362\r\n"
	 		+ "  },\r\n"
	 		+ "  \"accuracy\": 50,\r\n"
	 		+ "  \"name\": \"lake house\",\r\n"
	 		+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
	 		+ "  \"address\": \"39, side layout, cohen 09\",\r\n"
	 		+ "  \"types\": [\r\n"
	 		+ "    \"shoe park\",\r\n"
	 		+ "    \"shop\"\r\n"
	 		+ "  ],\r\n"
	 		+ "  \"website\": \"http://google.com\",\r\n"
	 		+ "  \"language\": \"French-IN\"\r\n"
	 		+ "}\r\n"
	 		+ "";
}
   
   public static String addbookpayload(String isbn, String aisle)
   {
	   String payload = "{\r\n"
	   		+ "\r\n"
	   		+ "\"name\":\"Learn Appium Automation with Java selenium\",\r\n"
	   		+ "\"isbn\":\""+isbn+"\",\r\n"
	   		+ "\"aisle\":\""+aisle+"\",\r\n"
	   		+ "\"author\":\"John doe\"\r\n"
	   		+ "}";
	   
	   return payload;
   }
   
   public static String deletebook(String id)
   {
	 String delete = "{\r\n"
	 		+ " \r\n"
	 		+ "\"ID\" : \""+id+"\"\r\n"
	 		+ " \r\n"
	 		+ "} \r\n"
	 		+ " "   ;
	 
	 return delete;
	 
   }
   
   public static String addcomment(String comm)
   {
	   String comment = "{\r\n"
	   		+ "    \"body\": \""+comm+"\",\r\n"
	   		+ "    \"visibility\": {\r\n"
	   		+ "        \"type\": \"role\",\r\n"
	   		+ "        \"value\": \"Administrators\"\r\n"
	   		+ "    }\r\n"
	   		+ "}";
	   
	   return comment;
   }
   
   
}