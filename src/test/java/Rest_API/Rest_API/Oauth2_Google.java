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


public class Oauth2_Google {

	public static void main(String[] args) throws InterruptedException
	{
		/*
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("srinath19830");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("srinath19830");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		*/
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AZEOvhXDxxvvlAkJa3nDiEsWeOBdM9vL1GaEd6y4kf6ykhNXEr9wE30HswA4WS9CWknoLg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";
		String partial_code = url.split("code=")[1];
		String code = partial_code.split("&scope")[0];
	     System.out.println(code);
	     
		
		
		
		    String access_tokenrep=given().urlEncodingEnabled(false).log().all().queryParam("code", code).
		            queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		            queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
		            queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").
		            queryParam("grant_type", "authorization_code").
		            when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		    
		    JsonPath js = new JsonPath(access_tokenrep);
		    String access_token = js.getString("access_token");
		            
		
		    Get_Course rep = given().queryParam("access_token", access_token).expect().defaultParser(Parser.JSON)
            .when().get("https://rahulshettyacademy.com/getCourse.php").as(Get_Course.class);
          
          System.out.println(rep.getLinkedin());
	}

}
