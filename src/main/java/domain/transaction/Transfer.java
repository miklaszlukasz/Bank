package domain.transaction;

import java.math.BigDecimal;

import domain.account.Account;

public class Transfer extends Transaction {
	private Account recipientAccount;
	private String comment;

	public Transfer(Account performingAccount, Account recipientAccount, BigDecimal amount) {
		super(performingAccount, amount);
		this.recipientAccount = recipientAccount;
		comment = generateComment();
	}

	private String generateComment() {
		return "Transfer " + amount + " to " + recipientAccount;
	}

	public Transfer(Account performingAccount, Account recipientAccount, BigDecimal amount, String comment) {
		super(performingAccount, amount);
		this.recipientAccount = recipientAccount;
		this.comment = comment;
	}

	public Account getRecipientAccount() {
		return recipientAccount;
	}

	public String getComment() {
		return comment;
	}

	@Override
	public boolean equals(Object object) {
		if (!super.equals(object))
			return false;
		final Transfer transfer = (Transfer) object;
		if (!recipientAccount.equals(transfer.getRecipientAccount()))
			return false;
		if (!comment.equals(transfer.getComment()))
			return false;
		return true;
	}
}