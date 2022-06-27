package Deserialization_POJOClasses;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;



import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;


public class CoursesMainClass {

	public static void main(String[] args) {
		 String url = "https://rahulshettyacademy.com/getCourse.php?state=abcdefgh&"
		 		+ "code=4%2F0AX4XfWi1CzvLDNabx0vTsPqeg-vJtQOKFQghVNkvOb9Hd2fjqg4ti3qMDNCnjrwCKpYKPw&"
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
		
		String[] ActualCoursesList = {"Selenium Webdriver Java","Cypress","Protractor"}; 
		
	    GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when()
	    .get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		System.out.println(gc.getExpertise());
		System.out.println(gc.getInstructor());
	//	System.out.println(gc.getLinkedIn());
	//	System.out.println(gc.getServices());
		ArrayList<String> a= new ArrayList<String>(); // arraylist size increases dynamically where as list can't
		
	    List<WebAutomation>	web = gc.getCourses().getWebAutomation();
        for(int i=0;i<web.size();i++) {
        	a.add(web.get(i).getCourseTitle());   
        	//System.out.println(a.add(web.get(i).getPrice())); 
        	}
        List<String> Exceptedlist = Arrays.asList(ActualCoursesList);
        Assert.assertTrue(a.equals(Exceptedlist));
		
	}

}
