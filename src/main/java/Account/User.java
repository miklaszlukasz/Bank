package Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private String firstName;
	private String lastName;
	private String idNumber;
	private Date dateOfBirth;
	
	public User(String firstName, String lastName, String idNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		dateOfBirth = generateDateOfBirthFromIdNumber();
	}
	
	private Date generateDateOfBirthFromIdNumber() {
		int yearOfBirth = Integer.valueOf(idNumber.substring(0, 2)) + 1900;
		int monthOfBirth = Integer.valueOf(idNumber.substring(2, 4));
		int dayOfBirth = Integer.valueOf(idNumber.substring(4, 6));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return formatter.parse(dayOfBirth + "/" + monthOfBirth + "/" + yearOfBirth);
		} catch (ParseException e) {
			return null;
		}
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}
}
