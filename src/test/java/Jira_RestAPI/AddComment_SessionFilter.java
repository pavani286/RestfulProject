package Jira_RestAPI;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class AddComment_SessionFilter {
    
	public static void main(String[] args) {
	RestAssured.baseURI = "http://localhost:8080";
	SessionFilter session = new SessionFilter();
	 given().header("Content-Type","application/json")
	.body("{ \"username\": \"vpavani.v\", \"password\": \"Aditya@135\" }")
	.log().all().filter(session)
	.when()
	.post("rest/auth/1/session")
	.then().log().all()
	.extract().response().asString();
	
	
	//add comment
	String addCommentResponse = given().log().all().pathParam("key","10025")
	.header("Content-Type","application/json")
	.body("{\n"
			+ "    \"body\": \" this is my second restapi call for  automating the jira \",\n"
			+ "    \"visibility\": {\n"
			+ "        \"type\": \"role\",\n"
			+ "        \"value\": \"Administrators\"\n"
			+ "    }\n"
			+ "}")
	.filter(session)
	.when()
	.post("/rest/api/2/issue/{key}/comment")
	.then().log().all().assertThat().statusCode(201)
	.extract().response().asString();
	 JsonPath js=new JsonPath(addCommentResponse);
     String commentId= js.getString("id");
	
       }
}
