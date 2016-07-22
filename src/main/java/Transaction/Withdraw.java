package Transaction;

import java.util.Calendar;

import Account.Account;

public class Withdraw extends Transaction {
	
	public Withdraw(Account performer, double amount) {
		super();
		type = TransactionType.WITHDRAW;
		this.performer = performer;
		this.amount = amount;
		comment = "Withdraw from account: " + amount;
		executionDate = Calendar.getInstance().getTime();
	}
}