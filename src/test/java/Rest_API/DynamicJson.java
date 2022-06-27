package Rest_API;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.DataProvider;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
    static String id;
	@Test(priority = 1,dataProvider = "BooksData")
	public void addbook(String isbn,String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String res = given().header("Content-Type","application/json")
		.body(Payload.AddBook(isbn,aisle))
		.when()
		.post("Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = ReuseableMethods.rawToJson(res);
		id = js.getString("ID");
		System.out.println(id);
	
	}
		//delete book 
	@Test(priority = 2,dataProvider = "BooksData")
	public void DeleteBook(String isbn,String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		given().header("Content-Type","application/json")
		.body(Payload.Deletebook(id))
		.when()
		.post("Library/DeleteBook.php")
		.then().assertThat().statusCode(200)
		.body("msg", equalTo("book is successfully deleted"));
	}
//*********Parameterization of APITests With Multiple SetOf data *******
	//using TestNG @DataProvider Annotaions ************ 
	
	@DataProvider(name="BooksData")
	public Object[][] GetData(){
       return new Object[][] {{"corn","6457"},
                                {"xiz","070"}};
	}



	
}
