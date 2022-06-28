package Utils;

import java.util.ArrayList;
import java.util.List;
import POJO_Classes.AddPlace;
import POJO_Classes.Location;

public class AddPayload {
	AddPlace a = new AddPlace();
	public AddPlace Addplacepayload(String name, String language, String address) {
	a.setName(name);
	a.setAddress(address);
	a.setPhone_number("22222222");
	a.setLanguage(language);
	List<String> s= new ArrayList();
	s.add("shoe park");
	s.add("shop");
	Location l = new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	a.setLocation(l);
	return a;
	}
	public String deletePlacePayload(String placeId){
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
	public AddPlace upadtePlacePayload(String phone_number,String website){
		a.setPhone_number(phone_number);
		a.setWebsite(website);
		return a;
	}
}
