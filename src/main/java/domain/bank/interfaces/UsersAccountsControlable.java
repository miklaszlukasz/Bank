package domain.bank.interfaces;

import domain.userAccount.UserAccount;

public interface UsersAccountsControlable {
	public void addNewUserAccount(UserAccount userAccount);
	public UserAccount getUserAccount(String personalIdNumber, String password);
}
