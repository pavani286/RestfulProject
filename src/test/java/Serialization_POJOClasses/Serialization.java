package Serialization_POJOClasses;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Serialization {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		AddPlace b = new AddPlace();
		b.setName("v.pavani");
		b.setAddress("10367,omegalane,frisco,tx,75035");
		b.setPhone_number("214-718-4104");
		b.setLanguage("English");
		List<String> s= new ArrayList();
		s.add("shoe park");
		s.add("shop");
		location l = new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		b.setLocation(l);
		Response res = given().log().all().queryParam("key","qaclick123")
				      .body(b)
				      .when().post("/maps/api/place/add/json")
				      .then().log().all().statusCode(200).extract().response();

	    String responseString=res.asString();
		System.out.println(responseString);

 
	}

}
