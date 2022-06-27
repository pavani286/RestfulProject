package SpecBuilders;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import Serialization_POJOClasses.AddPlace;
import Serialization_POJOClasses.location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.response.Response;

public class SpecBuildersClass {
	static RequestSpecification req;
	static ResponseSpecification resspec;
	static RequestSpecification res;
	static Response response; 
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
		req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				                      .addQueryParam("key", "qaclick123")
				                      .setContentType(ContentType.JSON).build();
				 		 
		resspec = new ResponseSpecBuilder().expectStatusCode(200)
				                           .expectContentType(ContentType.JSON).build();

		res=  given().log().all().spec(req)
				     .body(b);
		
        response =res.when().post("/maps/api/place/add/json").
				then().log().all().spec(resspec).extract().response();

		String responseString=response.asString();
		System.out.println(responseString);

	}
}
