package Transaction;

import Account.Account;
import Account.User;

public class Deposit extends Transaction {

	public Deposit(User performingPerson, Account performer, double amount) {
		super(TransactionType.DEPOSIT, performingPerson, performer, amount);
		comment = generateComment();
	}

	protected String generateComment() {
		return "Deposit to account: " + amount;
	}
}