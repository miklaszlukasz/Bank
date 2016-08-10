package domain.transaction;

import java.math.BigDecimal;

public class Transfer extends Transaction {
	private long recipientAccountId;
	private String comment;

	public Transfer(String performingPersonId, long performingAccountId, long recipientAccountId, BigDecimal amount) {
		super(TransactionType.TRANSFER, performingPersonId, performingAccountId, amount);
		this.recipientAccountId = recipientAccountId;
		comment = generateComment();
	}

	private String generateComment() {
		return "Transfer " + amount + " to " + recipientAccountId;
	}
	
	public Transfer(String performingPersonId, long performingAccountId, long recipientAccountId, BigDecimal amount, String comment) {
		super(TransactionType.TRANSFER, performingPersonId, performingAccountId, amount);
		this.recipientAccountId = recipientAccountId;
		this.comment = comment;
	}

	public long getRecipientIdNumber() {
		return recipientAccountId;
	}

	public String getComment() {
		return comment;
	}
}