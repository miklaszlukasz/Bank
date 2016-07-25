package Transaction;

import Account.Account;

public class Withdraw extends Transaction {
	
	public Withdraw(Account performer, double amount) {
		super(TransactionType.WITHDRAW, performer, amount);
		generateComment(); 
	}

	public void generateComment() {
		comment = "Withdraw from account: " + amount;
	}
}