package domain.bank;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import domain.userAccount.UserAccount;

public class Bank {
	@OneToMany
	private List<UserAccount> userAccounts;

	public Bank() {
		userAccounts = new ArrayList<UserAccount>();
	}

	public void addNewUserAccount(UserAccount userAccount) {
		userAccounts.add(userAccount);
	}

	public UserAccount getUserAccount(String personalIdNumber, String password) {
		for (UserAccount userAccount : userAccounts)
			if (userAccount.verify(personalIdNumber, password))
				return userAccount;
		return null;
	}
	// Scanner scanner = new Scanner(System.in);
	// System.out.println("Input your id number:");
	// final String idNumber = scanner.nextLine();
	// System.out.println("Input password:");
	// final String password = scanner.nextLine();
	// System.out.println("Input your first name: ");
	// final String firstName = scanner.nextLine();
	// System.out.println("Input your last name: ");
	// final String lastName = scanner.nextLine();
	// scanner.close();
	// return new UserAccount.Builder(idNumber,
	// password).firstName(firstName).lastName(lastName).generateDateOfBirthFromIdNumber().build();
}
