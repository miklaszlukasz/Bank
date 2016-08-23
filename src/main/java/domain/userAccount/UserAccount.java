package domain.userAccount;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import domain.account.Account;
import domain.userAccount.interfaces.Depositable;
import domain.userAccount.interfaces.Transferable;
import domain.userAccount.interfaces.Verifable;
import domain.userAccount.interfaces.Withdrawable;

public class UserAccount implements Verifable, Depositable, Withdrawable, Transferable {
	private String idNumber;
	private String password;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private List<Account> accounts;

	public UserAccount(Builder builder) {
		idNumber = builder.IdNumber;
		password = builder.password;
		firstName = builder.firstName;
		lastName = builder.lastName;
		dateOfBirth = builder.dateOfBirth;
		accounts = new ArrayList<Account>();
	}

	public boolean verifyIdNumberAndPassword(String idNumber, String password) {
		return idNumber.equals(this.idNumber) && password.equals(this.password);
	}

	public void depositMoney(Account account, BigDecimal amount) {
		account.depositMoney(amount);
	}

	public void withdrawMoney(Account account, BigDecimal amount) {
		account.withdrawMoney(amount);
	}

	public void transferMoney(Account account, Account recipient, BigDecimal amount, String comment) {
		account.transferMoney(recipient, amount, comment);
	}

	public void transferMoney(Account account, Account recipient, BigDecimal amount) {
		account.transferMoney(recipient, amount);
	}

	public String getIdNumber() {
		return idNumber;
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

	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		final UserAccount userAccount = (UserAccount) object;
		if (!idNumber.equals(userAccount.idNumber))
			return false;
		if (!password.equals(userAccount.password))
			return false;
		if (!firstName.equals(userAccount.firstName))
			return false;
		if (!lastName.equals(userAccount.lastName))
			return false;
		if (!dateOfBirth.equals(userAccount.dateOfBirth))
			return false;
		return true;
	}

	public static class Builder {
		private String IdNumber;
		private String password;
		private String firstName;
		private String lastName;
		private Date dateOfBirth;

		public Builder(String personalIdNumber, String password) {
			this.IdNumber = personalIdNumber;
			this.password = password;
			firstName = "";
			lastName = "";
			dateOfBirth = new Date(0);
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
			dateOfBirth = generateDateOfBirth(IdNumber);
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
			final String date = yearOfBirth + "-" + monthOfBirth + "-" + dayOfBirth;
			return Date.valueOf(date);
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