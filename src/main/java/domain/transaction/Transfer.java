package domain.transaction;

import java.math.BigDecimal;

public class Transfer extends Transaction {
	private long recipientAccountId;

	public Transfer(String performingPersonId, long performingAccountId, long recipientAccountId, BigDecimal amount) {
		super(TransactionType.TRANSFER, performingPersonId, performingAccountId, amount);
		this.recipientAccountId = recipientAccountId;
		comment = generateComment();
	}

	protected String generateComment() {
		return "Transfer " + amount + " to " + recipientAccountId;
	}
	
	public Transfer(String performingPerson, long performer, long recipientAccountId, BigDecimal amount, String comment) {
		super(TransactionType.TRANSFER, performingPerson, performer, amount);
		this.recipientAccountId = recipientAccountId;
		this.comment = comment;
	}

	public long getRecipientIdNumber() {
		return recipientAccountId;
	}
}