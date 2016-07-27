package Transaction;

import Account.Account;
import Account.User;

public class Withdraw extends Transaction {
	
	public Withdraw(User performingPerson, Account performer, double amount) {
		super(TransactionType.WITHDRAW, performingPerson, performer, amount);
		generateComment(); 
	}

	public void generateComment() {
		comment = "Withdraw from account: " + amount;
	}
}