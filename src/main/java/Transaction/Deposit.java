package Transaction;

import Account.Account;

public class Deposit extends Transaction {

	public Deposit(Account performer, double amount) {
		super(TransactionType.DEPOSIT, performer, amount);
		generateComment();
	}

	public void generateComment() {
		comment = "Deposit to account: " + amount;
	}
}