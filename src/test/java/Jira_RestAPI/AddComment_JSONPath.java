package Jira_RestAPI;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class AddComment_JSONPath {

	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:8080";
		String response = given().header("Content-Type","application/json")
		.body("{ \"username\": \"vpavani.v\", \"password\": \"Aditya@135\" }")
		.log().all()
		.when()
		.post("rest/auth/1/session")
		.then().log().all()
		.extract().response().asString();
		JsonPath js = new JsonPath(response); //for parsing Json
		String  sessionname = js.getString("session.name");
		String sessionvalue = js.getString("session.value");
		System.out.println(sessionname);
	    System.out.println(sessionvalue);
	    String session = sessionname+"="+sessionvalue;
	    System.out.println(session);
	    
	    //add comment
	    
	   given().log().all().pathParam("key","10025")
		.header("Content-Type","application/json")
		.header("Cookie",session)
		.body("{\n"
				+ "    \"body\": \" this is my second restapi call for  automating the jira \",\n"
				+ "    \"visibility\": {\n"
				+ "        \"type\": \"role\",\n"
				+ "        \"value\": \"Administrators\"\n"
				+ "    }\n"
				+ "}")
		.when()
		.post("/rest/api/2/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201);
		
	}

}
