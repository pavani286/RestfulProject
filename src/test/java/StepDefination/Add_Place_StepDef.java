package StepDefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import Utils.AddPayload;
import Utils.ApiResource;
import Utils.Datautil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Add_Place_StepDef extends Datautil {
	
    ResponseSpecification resspec;
	RequestSpecification res;
	Response response; 
	AddPayload b = new AddPayload();
	static String placeId;
	@Given("Add Place Payload {string}  {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		res=  given().spec(requestSpecification())
			     .body(b.Addplacepayload(name,language,address));	
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource,String method) {
		
		ApiResource resourceAPI = ApiResource.valueOf(resource);
		System.out.println(resourceAPI.getresource());
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		 response =res.when().post(resourceAPI.getresource());

		else if(method.equalsIgnoreCase("GET"))
			 response =res.when().get(resourceAPI.getresource());
		
		else if(method.equalsIgnoreCase("PUT"))
			 response =res.when().put(resourceAPI.getresource());
		
		else if(method.equalsIgnoreCase("DELETE"))
		response =res.when().delete(resourceAPI.getresource());
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String Exceptedvalue) {
		
		assertEquals(getJsonPath(response,keyvalue),Exceptedvalue);
	}
	@And("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedaddress, String resource) throws IOException {
		placeId=getJsonPath(response,"place_id");
		res = given().spec(requestSpecification())
		   		     .queryParam("place_id",placeId);
		   		               
	    user_calls_with_post_http_request(resource,"GET");
		String actualAddress = getJsonPath(response,"address"); 
		assertEquals(actualAddress,expectedaddress);
		
	}
	@Given("UpdatePlace Payload {string} {string} {string}")
	public void update_place_payload(String phone_number, String website, String resource) throws IOException {
	    res=  given().spec(requestSpecification())
	    		     .queryParam("place_id",placeId)
	                 .body(b.upadtePlacePayload(phone_number,website));
	  	                   
	}
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	  	res =given().spec(requestSpecification()).body(b.deletePlacePayload(placeId));

	}
	
}