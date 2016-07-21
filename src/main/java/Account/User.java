package Account;

public class User {
	private String firstName;
	private String lastName;
	private String idNumber;
	
	public User(String firstName, String lastName, String idNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getIdNumber() {
		return idNumber;
	}
}
