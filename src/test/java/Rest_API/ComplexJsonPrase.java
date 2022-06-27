package Rest_API;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJsonPrase {

	public static void main(String[] args) {
		 int sum=0;
		JsonPath js = new JsonPath(ReuseableMethods.NestedJson());
		System.out.println("******size of the dashboard******"); 
		int dashboardsize = js.get("dashboard.size()");
		System.out.println(dashboardsize);
		
		System.out.println("*****size of the courses*******"); 
		int coursessize = js.get("courses.size()");
		System.out.println(coursessize);
		
	    System.out.println("*****Print All course titles and their respective Prices*****");
			for(int i=0;i<coursessize;i++) {
				String coursetitle = js.get("courses["+i+"].title");
				if(i==0) {
				String sitename = js.get("courses["+i+"].details.site");
				System.out.println(sitename);
				       }
			System.out.println(coursetitle);
			       }
	 /*   System.out.println("******** Verify if Sum of all Course prices matches with Purchase Amount*******");
		 for(int j=0;j<coursessize;j++) {
			  int price = js.getInt("courses["+j+"].price");
			  int copies =js.getInt("courses["+j+"].copies");
			  int product = price * copies;
			  sum =  product + sum ;
			  
			           }
		 System.out.println(sum);
			int purchaseAmount =js.getInt("dashboard.purchaseAmount");
			Assert.assertEquals(sum, purchaseAmount);
			*/
	}
}


