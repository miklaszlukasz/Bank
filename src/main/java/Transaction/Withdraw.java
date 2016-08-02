package Transaction;

import Account.Account;
import Account.User;

public class Withdraw extends Transaction {
	
	public Withdraw(User performingPerson, Account performer, double amount) {
		super(TransactionType.WITHDRAW, performingPerson, performer, amount);
		comment = generateComment(); 
	}

	protected String generateComment() {
		return "Withdraw from account: " + amount;
	}
}