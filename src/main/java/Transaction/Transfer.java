package Transaction;

import java.util.Calendar;

import Account.Account;

public class Transfer extends Transaction {
	private Account recipient;

	public Transfer(Account performer, Account recipient, double amount) {
		super(TransactionType.TRANSFER, performer, amount);
		this.recipient = recipient;
		generateComment();
	}

	public void generateComment() {
		comment = "Transfer " + amount + " to " + recipient;
	}
	
	public Transfer(Account performer, Account recipient, double amount, String comment) {
		super(TransactionType.TRANSFER, performer, amount);
		this.recipient = recipient;
		this.comment = comment;
		executionDate = Calendar.getInstance().getTime();
	}

	public Account getRecipient() {
		return recipient;
	}
}