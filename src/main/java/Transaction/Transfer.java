package Transaction;

import java.util.Calendar;

import Account.Account;

public class Transfer extends Transaction {
	private Account recipient;

	public Transfer(Account performer, Account recipient, double amount, String comment) {
		super();
		type = TransactionType.TRANSFER;
		this.performer = performer;
		this.recipient = recipient;
		this.amount = amount;
		this.comment = comment;
		executionDate = Calendar.getInstance().getTime();
	}

	public Account getRecipient() {
		return recipient;
	}
}