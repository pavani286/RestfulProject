package Jira_RestAPI;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Get_IssueDetails{
	
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
    
    String expectedMessage ="Hi How are you?";
    //add comment
  String  addCommentResponse = given().log().all().pathParam("key","10025")
	.header("Content-Type","application/json")
	.header("Cookie",session)
	.body("{\n"
			+ "    \"body\": \" this is my third restapi call for  automating the jira \",\n"
			+ "    \"visibility\": {\n"
			+ "        \"type\": \"role\",\n"
			+ "        \"value\": \"Administrators\"\n"
			+ "    }\n"
			+ "}")
	.when()
	.post("/rest/api/2/issue/{key}/comment")
	.then().log().all().assertThat().statusCode(201)
	.extract().response().asString();
     JsonPath js1=new JsonPath(addCommentResponse);
     String commentId= js1.getString("id");
    
	
	//get command
    String issueDetails = given().pathParam("key", "10025")
    .queryParam("fields", "comment")
    .header("Cookie",session)
   	.log().all().when().get("/rest/api/2/issue/{key}").then()
    .log().all().extract().response().asString();
     System.out.println(issueDetails);
 	 JsonPath js2 =new JsonPath(issueDetails);

 	int commentsCount=js2.getInt("fields.comment.comments.size()");

 	for(int i=0;i<commentsCount;i++){

 	     String commentIdIssue =js2.get("fields.comment.comments["+i+"].id").toString();

 	   if (commentIdIssue.equalsIgnoreCase(commentId)){

 	   String message= js2.get("fields.comment.comments["+i+"].body").toString();
       System.out.println(message);
       Assert.assertEquals(message, expectedMessage);

 	                            }

 	                }
 	}
}
