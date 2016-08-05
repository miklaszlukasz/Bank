package Transaction;

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
	
//	@Override
//	public boolean equals(Object object) {
//		if (object == null)
//			return false;
//	    if (!Transaction.class.isAssignableFrom(object.getClass()))
//	        return false;
//	    final Transfer transaction= (Transfer) object;
////	    if (idNumber =! transaction.idNumber)
////	    	return false;
//	    if (!type.equals(transaction.type))
//	    	return false;
//	    if (!performingAccount.equals(transaction.performingAccount))
//	    	return false;
//	    if (!executionDate.equals(transaction.executionDate))
//	    	return false;
//	    if (amount != transaction.amount)
//	    	return false;
//	    if (!comment.equals(transaction.comment))
//	    	return false;
//	    if (recipient.equals(transaction.performingAccount))
//	    	return false;
//	    return true;
//	}
}