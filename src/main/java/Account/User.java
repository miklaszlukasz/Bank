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
		dateOfBirth = generateDateOfBirth();
	}
	
	private Date generateDateOfBirth() {
		int yearOfBirth= getYearOfBirthFromIdNumber();
		int monthOfBirth= getMonthOfBirthFromIdNumber();
		int dayOfBirth= getDayOfBirthFromIdNumber();
		return generateDateOfBirth(yearOfBirth, monthOfBirth, dayOfBirth);
	}
	
	private int getYearOfBirthFromIdNumber() {
		return Integer.valueOf(idNumber.substring(0, 2));
	}
	
	private int getMonthOfBirthFromIdNumber() {
		return Integer.valueOf(idNumber.substring(2, 4));
	}
	
	private int getDayOfBirthFromIdNumber() {
		return Integer.valueOf(idNumber.substring(4, 6));
	}
	
	private Date generateDateOfBirth(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
		if (monthOfBirth > 80) {
			monthOfBirth-= 80;
			yearOfBirth+= 1800;
		} else if (monthOfBirth > 60) {
			yearOfBirth+= 2200;
			monthOfBirth-= 60;
		} else if (monthOfBirth > 40) {
			yearOfBirth+= 2100;
			monthOfBirth-= 40;
		} else if (monthOfBirth > 20) {
			monthOfBirth-= 20;
			yearOfBirth+= 2000;
		} else if (monthOfBirth > 0)
			yearOfBirth+= 1900;
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