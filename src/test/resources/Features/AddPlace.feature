
Feature: Performing the CRUD Operations using Rest Assured 
 
  Scenario Outline: Performing Post & Get Request using AddPlace,GetPlace API 
    Given Add Place Payload "<name>"  "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call got success with status code 200
	  And "status" in response body is "OK"
	  And "scope" in response body is "APP"
	  And verify place_Id created maps to "<address>" using "getPlaceAPI"
	  
	  Examples:
	|name 	 | language |address		   |
	|AAhouse |  English |6219 love drive |
	|BBhouse | Spanish  |10367 omega lane  |
	
	Scenario Outline: Performing UpdatePlace Request Using Update API
	
	Given UpdatePlace Payload "<phone_number>" "<website>" "updatePlaceAPI"
	When user calls "updatePlaceAPI" with "PUT" http request
	
	Examples:
	|phone_number 	 | website | 
	|214-678-9990 |  aa@yahoo.com |
	
	Scenario: Performing DeletePlace Request Using Delete API

	Given DeletePlace Payload 
	When user calls "deletePlaceAPI" with "DELETE" http request
	
