package OAuth;
import static io.restassured.RestAssured.given;

import Deserialization_POJOClasses.GetCourse;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
public class OAuth_Authorization {

	public static void main(String[] args) throws InterruptedException {
		
	    String url = "https://rahulshettyacademy.com/getCourse.php?state=abcdefgh&"
	    		+ "code=4%2F0AX4XfWg-pk15llSMlEIMpynRSAad6UEDwpDw1vaYmgj7wIZLYqMBBnCw-bjCrp7Fi0paeg&"
	    		+ "scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		System.out.println(code);
		
		//GetAccessToken Request 
	    String Response = given().urlEncodingEnabled(false)
		.queryParam("code",code)
		.queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
 		 System.out.println(Response);
 		JsonPath js = new JsonPath(Response);
	    String accessToken = js.getString("access_token");
	    System.out.println(accessToken);
	    
	    String Response1 = given().queryParam("access_token", accessToken)
		.when().log().all()
        .get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(Response1);

	
	}
}


