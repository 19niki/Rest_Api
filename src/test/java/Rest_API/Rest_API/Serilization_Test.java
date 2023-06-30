package Rest_API.Rest_API;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
public class Serilization_Test {
	
	public static void main(String[] args)
	{
		AddMap_Payload add = new AddMap_Payload();
		add.setAccuracy(199);
		add.setAddress("721 Ragnatha Nilaya Bnaglore");
		add.setLanguage("English");
		Map_Loc l = new Map_Loc();
		l.setLat(-48.383494);
		l.setLng(44.427362);
		add.setLocation(l);
		add.setName("nikhil");
		add.setPhone_number("9742488737");
		List<String> mylist = new ArrayList<>();
		mylist.add("Test park");
		mylist.add("test");
		add.setTypes(mylist);
		add.setWebsite(DEFAULT_BODY_ROOT_PATH);
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String rep =given().log().all().queryParam("Key", "qaclick123")
        .header("Content-Type","application/json")
        .body(add)
        .when().post("maps/api/place/add/json")
        .then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.52 (Ubuntu)")).extract().response().asString();
        System.out.println(rep);
	}

}
