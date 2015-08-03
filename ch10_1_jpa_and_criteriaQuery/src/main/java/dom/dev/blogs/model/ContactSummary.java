package dom.dev.blogs.model;

public class ContactSummary {
	private String firstName;
	private String lastName;
	private String homeTelNumber;
	
	public ContactSummary(String firstName, String lastName, String homeTelNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.homeTelNumber = homeTelNumber;		
	}
	
	public String toString() {
		return "First name: " + this.firstName + " Last Name: " + this.lastName + " Home Phone: " + this.homeTelNumber;
	}
}