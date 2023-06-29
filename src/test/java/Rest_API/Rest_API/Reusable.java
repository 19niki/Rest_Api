package Rest_API.Rest_API;

import io.restassured.path.json.JsonPath;

public class Reusable {
	
	public static JsonPath rawtojson(String a)
	{
		JsonPath js = new JsonPath(a);
		return js;
		
	}

}
