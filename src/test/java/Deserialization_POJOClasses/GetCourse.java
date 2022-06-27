package Deserialization_POJOClasses;

public class GetCourse {
private String instructor;
private String url;
private String services;
private String expertise;
private Courses courses;
private String linkedIn;

public String getInstructor() {
	return instructor;
}
public void setInstructor(String instructor) {
	this.instructor = instructor;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getServices() {
	return services;
}
public void setServices(String services) {
	this.services = services;
}
public String getExpertise() {
	return expertise;
}
public void setExpertise(String expertise) {
	this.expertise = expertise;
}
public Deserialization_POJOClasses.Courses getCourses() {
	return courses;
}
public void setCourses(Deserialization_POJOClasses.Courses courses) {
	this.courses = courses;
}
public String getLinkedIn() {
	return linkedIn;
}
public void setLinkedIn(String linkedIn) {
	this.linkedIn = linkedIn;
}

}
