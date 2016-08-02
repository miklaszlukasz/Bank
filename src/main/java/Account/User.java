package Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private String firstName;
	private String lastName;
	private String idNumber;
	private Date dateOfBirth;

	public User(Builder builder) {
		firstName = builder.firstName;
		lastName = builder.lastName;
		idNumber = builder.idNumber;
		dateOfBirth = builder.dateOfBirth;
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

	public static class Builder {
		private String firstName;
		private String lastName;
		private String idNumber;
		private Date dateOfBirth;

		public Builder() {
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder idNumber(String idNumber) {
			this.idNumber = idNumber;
			return this;
		}

		public Builder dateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}

		public Builder generateDateOfBirthFromIdNumber(String idNumber) {
			dateOfBirth = generateDateOfBirth(idNumber);
			return this;
		}

		private Date generateDateOfBirth(String idNumber) {
			int yearOfBirth = getYearOfBirthFromIdNumber(idNumber);
			int monthOfBirth = getMonthOfBirthFromIdNumber(idNumber);
			int dayOfBirth = getDayOfBirthFromIdNumber(idNumber);
			return generateDateOfBirth(yearOfBirth, monthOfBirth, dayOfBirth);
		}

		private int getYearOfBirthFromIdNumber(String idNumber) {
			return Integer.valueOf(idNumber.substring(0, 2));
		}

		private int getMonthOfBirthFromIdNumber(String idNumber) {
			return Integer.valueOf(idNumber.substring(2, 4));
		}

		private int getDayOfBirthFromIdNumber(String idNumber) {
			return Integer.valueOf(idNumber.substring(4, 6));
		}

		private Date generateDateOfBirth(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
			final int helper = monthOfBirth / 20;
			monthOfBirth %= 20;
			yearOfBirth += countYears(helper);

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			try {
				return formatter.parse(dayOfBirth + "/" + monthOfBirth + "/" + yearOfBirth);
			} catch (ParseException e) {
				return null;
			}
		}

		private int countYears(int helper) {
			if (helper == 4)
				return 1800;
			else if (helper == 3)
				return 2200;
			else if (helper == 2)
				return 2100;
			else if (helper == 1)
				return 2000;
			else
				return 1900;
		}

		public User build() {
			return new User(this);
		}
	}
}