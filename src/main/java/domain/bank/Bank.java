package domain.bank;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import domain.bank.interfaces.UsersAccountsControlable;
import domain.userAccount.UserAccount;

public class Bank implements UsersAccountsControlable {
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
			if (userAccount.verifyIdNumberAndPassword(personalIdNumber, password))
				return userAccount;
		return null;
	}
}
