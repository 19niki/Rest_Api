package Rest_API.Rest_API;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;

public class LibraryApi_Test {
	
	@Test(dataProvider = "getdata")
	//@Test(enabled = false)
	public void addbook(String isbn, String aisle ) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		String res=given().header("Content-Type", "application/json")
		       .body(Complex_Payload.addbookpayload(isbn, aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = Reusable.rawtojson(res);
		String id = js.getString("ID");
		System.out.println(id);
	}	
		@Test(enabled = false)
		public void deletebook()
		{
		//delete book
		given().header("Content-Type","application/json")
		       .body(Complex_Payload.deletebook("Liverpool3455"))
		     .when().delete("Library/DeleteBook.php")
		     .then().log().all().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted"));
		       
		}
	
		@Test
		public void getbook() {
			String getrep = given().queryParam("ID", "Juve678")
			.when().get("Library/GetBook.php")
			.then().log().all().assertThat().statusCode(200).extract().response().asString();
			JsonPath js =  Reusable.rawtojson(getrep);
			String bookid = js.get("aisle");
			System.out.println(bookid);
			Assert.assertEquals(bookid, "Juve678");
			
			
			
		}
	
	@DataProvider
	public Object[][] getdata() {
		
		Object[][] data =  new Object[3][2];
		data[0][0] = "Juve";
		data[0][1] = "678";
		data[1][0] = "Liverpool";
		data[1][1] = "3455";
		data[2][0] = "MUFC";
		data[2][1] = "656";
		
		
		return data;
	}

}
//BXIT-KML9-RUAN-QMDQ