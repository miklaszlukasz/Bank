package Transaction;

import java.util.Calendar;

import Account.Account;
import Account.User;

public class Transfer extends Transaction {
	private Account recipient;

	public Transfer(User performingPerson, Account performer, Account recipient, double amount) {
		super(TransactionType.TRANSFER, performingPerson, performer, amount);
		this.recipient = recipient;
		comment = generateComment();
	}

	protected String generateComment() {
		return "Transfer " + amount + " to " + recipient;
	}
	
	public Transfer(User performingPerson, Account performer, Account recipient, double amount, String comment) {
		super(TransactionType.TRANSFER, performingPerson, performer, amount);
		this.recipient = recipient;
		this.comment = comment;
		executionDate = Calendar.getInstance().getTime();
	}

	public Account getRecipient() {
		return recipient;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
	    if (!Transaction.class.isAssignableFrom(object.getClass()))
	        return false;
	    final Transfer transaction= (Transfer) object;
//	    if (idNumber =! transaction.idNumber)
//	    	return false;
	    if (!type.equals(transaction.type))
	    	return false;
	    if (!performer.equals(transaction.performer))
	    	return false;
	    if (!executionDate.equals(transaction.executionDate))
	    	return false;
	    if (amount != transaction.amount)
	    	return false;
	    if (!comment.equals(transaction.comment))
	    	return false;
	    if (recipient.equals(transaction.performer))
	    	return false;
	    return true;
	}
}