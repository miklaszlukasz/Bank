package User;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import Account.Account;

@Entity
public class UserAccount {
	@Id
	private String personalIdNumber;
	private String password;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	@OneToMany(mappedBy = "userAccount")
	private List<Account> accounts;

	public UserAccount(Builder builder) {
		personalIdNumber = builder.personalIdNumber;
		password = builder.password;
		firstName = builder.firstName;
		lastName = builder.lastName;
		dateOfBirth = builder.dateOfBirth;
		accounts = new ArrayList<Account>();
	}	

	public void depositMoney(Account account, BigDecimal amount) {
		account.depositMoney(amount);		
	}

	public void withdrawMoney(Account account, BigDecimal amount) {
		account.withdrawMoney(amount);
	}
	
	public void transferMoney(Account performer, Account recipient, BigDecimal amount, String comment) {
		performer.transferMoney(recipient, amount, comment);
	}
	
	public String getIdNumber() {
		return personalIdNumber;
	}

	public String getPassword() {
		return password;
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public static class Builder {
		private String personalIdNumber;
		private String password;
		private String firstName;
		private String lastName;
		private Date dateOfBirth;

		public Builder(String personalIdNumber, String password) {
			this.personalIdNumber = personalIdNumber;
			this.password = password;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder dateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}

		public Builder generateDateOfBirthFromIdNumber() {
			dateOfBirth = generateDateOfBirth(personalIdNumber);
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
			final int twentysAddedToMount = 20;
			final int numbersTwenty = monthOfBirth / twentysAddedToMount;
			monthOfBirth %= twentysAddedToMount;
			yearOfBirth += countYears(numbersTwenty);

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			try {
				return formatter.parse(dayOfBirth + "/" + monthOfBirth + "/" + yearOfBirth);
			} catch (ParseException e) {
				return null;
			}
		}

		private int countYears(int twentysAddedToMount) {
			switch (twentysAddedToMount) {
			case 4:
				return 1800;
			case 3:
				return 2200;
			case 2:
				return 2100;
			case 1:
				return 2000;
			default:
				return 1900;
			}
		}

		public UserAccount build() {
			return new UserAccount(this);
		}
	}
}