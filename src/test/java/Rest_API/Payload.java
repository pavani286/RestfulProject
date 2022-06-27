package Rest_API;

public class Payload {

	public static String AddPlace() {
		return "{\n"
				+ "  \"location\": {\n"
				+ "    \"lat\": -38.383494,\n"
				+ "    \"lng\": 33.427362\n"
				+ "  },\n"
				+ "  \"accuracy\": 50,\n"
				+ "  \"name\": \"Frontline house\",\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\n"
				+ "  \"types\": [\n"
				+ "    \"shoe park\",\n"
				+ "    \"shop\"\n"
				+ "  ],\n"
				+ "  \"website\": \"http://google.com\",\n"
				+ "  \"language\": \"French-IN\"\n"
				+ "}\n"
				+ "\n"
				+ "";
	}
	public static String AddBook(String isbn,String aisle) {
		String s="{\n"
				+ "\n"
				+ "\"name\":\"Selenium webdriver\",\n"
				+ "\"isbn\":\""+isbn+"\",\n"
				+ "\"aisle\":\""+aisle+"\",\n"
				+ "\"author\":\"shanthi\"\n"
				+ "}";
		return s;
	}
	public static String Deletebook(String id) {
		String s1="{\n"
				+ " \n"
				+ "\"ID\" : \""+id+"\"\n"
				+ " \n"
				+ "} \n"
				+ "";
		return s1;
		
	}
}
