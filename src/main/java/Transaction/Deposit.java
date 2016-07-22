package Transaction;

import java.util.Calendar;

import Account.Account;

public class Deposit extends Transaction {

	public Deposit(Account performer, double amount) {
		super();
		type = TransactionType.DEPOSIT;
		this.performer = performer;
		this.amount = amount;
		comment = "Deposit to account: " + amount;
		executionDate = Calendar.getInstance().getTime();
	}
}