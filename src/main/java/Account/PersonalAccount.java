package Account;

public class PersonalAccount extends Account {

	public PersonalAccount(User owner) {
		super(owner, AccountType.PERSONAL_ACCOUNT);
	}	
}
