package Account.rubbish;

import java.util.ArrayList;
import java.util.List;

import Account.Account;
import Account.User;

public class CompanyAccount extends Account {
	private List<User> users;

	public CompanyAccount(User owner) {
		super(owner, AccountType.COMPANY_ACCOUNT);
		users = new ArrayList<User>();
	}

	public void addUser(User user) {
		users.add(user);
	}

	public void removeUser(User user) {
		for (int i = 0; i < users.size(); i++)
			if (users.get(i).equals(user))
				users.remove(i);
	}

	public void removeAllUsers() {
		users.clear();
	}
}