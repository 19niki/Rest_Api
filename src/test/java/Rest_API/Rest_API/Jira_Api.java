package Rest_API.Rest_API;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
public class Jira_Api {

	public static void main(String[] args) {
		
		RestAssured.baseURI="http://localhost:8085/";
		
		//login scenario
		SessionFilter session = new SessionFilter();
		given().relaxedHTTPSValidation().log().all().header("Content-Type", "application/json")
		 .body("{ \"username\": \"nikhilravi9847\", \"password\": \"admin\" }\r\n"
		 		+ "")
		.filter(session)
		.when().post("rest/auth/1/session")
		.then().log().all().statusCode(200);
		
		//Add the comment
		String expcom = "This is nikhil";
		String resp = given().log().all().pathParam("id", "10002")
		       .header("Content-Type", "application/json")
		       .body(Complex_Payload.addcomment(expcom)).filter(session)
		       .when().post("rest/api/2/issue/{id}/comment")
		       .then().log().all().assertThat().statusCode(201).extract().asString();
		
		JsonPath js = Reusable.rawtojson(resp);
		String commentid = js.getString("id");
		
          		       
		//Add attchment
		/*
		given().log().all().filter(session).pathParam("id", "10002").header("Atlassian-Token", "no-check")
		.header("Content-Type", "multiPart/form-data").multiPart("file", new File("json.txt"))
		.when().post("rest/api/2/issue/{id}/attachments")
		.then().log().all().assertThat().statusCode(200);
		*/
		
		//get request
		String issuedetails= given().filter(session).log().all().pathParam("id", "10002").queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{id}")
		.then().log().all().extract().response().asString();
		
		JsonPath j=Reusable.rawtojson(issuedetails);
		int comcount = j.get("fields.comment.comments.size()");
		for(int i=0; i<comcount;i++)
		{
			String id = j.get("fields.comment.comments["+i+"].id").toString();
			if(commentid.equals(id))
			{
				String actcom=j.get("fields.comment.comments["+i+"].body");
				System.out.println(actcom);
				Assert.assertEquals(actcom, expcom);
			}
			System.out.println(id);
		}
		
		System.out.println(issuedetails);

	}

}
