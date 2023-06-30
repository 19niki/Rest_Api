package Rest_API.Rest_API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class Base_API1 {

	public static void main(String[] args) {

       // Add place api is working
		RestAssured.baseURI = "https://rahulshettyacademy.com";
	String rep=	given().log().all().queryParam("Key", "qaclick123")
               .header("Content-Type","application/json")
               .body(Complex_Payload.jsonpayload())
        .when().post("maps/api/place/add/json")
        .then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.52 (Ubuntu)"))
              .extract().response().asString();
	
	System.out.println(rep);
	// to extart the the palce id from the JSON respone body
	JsonPath js =  new JsonPath(rep);
	String place = js.getString("place_id");
	System.out.println("Place id:"+place);
	
	//Update place with new address
	
	given().queryParam("key", "qaclick123")
	       .header("Content-Type","application/json")
	       .body(
	       		"{\r\n"
	       		+ "\"place_id\":\""+place+"\",\r\n"
	       		+ "\"address\":\"80 Summer walk, USA\",\r\n"
	       		+ "\"key\":\"qaclick123\"\r\n"
	       		+ "}")
	       .when().put("/maps/api/place/update/json")
	       .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	
	String address= "80 Summer walk, USA";
	// validting the address using get
	String placres = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", "5f221f069b51e221063a9b75f5b814c0")
	   .when().get("maps/api/place/get/json")
	   .then().log().all().assertThat().statusCode(200).extract().body().asString();
	
	JsonPath js1 = Reusable.rawtojson(placres);
	String addnew = js1.getString("address");
	System.out.println("New Address:"+addnew);
	
	assertEquals(address, addnew);
	   
	//delete the resource
	given().log().all().queryParam("key", "qaclick123")
	.header("Content-Type","application/json")
	.body("{\r\n"
			+ "    \"place_id\":\""+place+"\"\r\n"
			+ "}\r\n"
			+ "")
	.when().delete("/maps/api/place/delete/json")
	.then().log().all().assertThat().statusCode(200).body("status", equalTo("OK"));
	       
	
		
	}

}
