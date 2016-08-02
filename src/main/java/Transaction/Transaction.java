package Transaction;

import java.util.Calendar;
import java.util.Date;

import Account.Account;
import Account.User;

public abstract class Transaction {
	TransactionType type;
	protected long idNumber;
	protected User performingPerson;
	protected Account performer;
	protected Date executionDate;
	protected double amount;
	protected String comment;

	public Transaction(TransactionType type, User performingPerson, Account performer, double amount) {
		this.performingPerson = performingPerson;
		this.type = type;
		this.performer = performer;
		this.amount = amount;
		executionDate = Calendar.getInstance().getTime();
	}

	public TransactionType getType() {
		return type;
	}
	
	public long getIdNumber() {
		return idNumber;
	}

	public User getPerformingPerson() {
		return performingPerson;
	}

	public Account getPerformer() {
		return performer;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public double getAmount() {
		return amount;
	}
	
	public String getComment() {
		return comment;
	}
	
	abstract String generateComment();
	
	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
	    if (!Transaction.class.isAssignableFrom(object.getClass()))
	        return false;
	    final Transaction transaction= (Transaction) object;
//	    if (idNumber =! transaction.idNumber)
//	    	return false;
	    if (!type.equals(transaction.type))
	    	return false;
	    if (!performingPerson.equals(transaction.performingPerson))
	    	return false;
	    if (!performer.equals(transaction.performer))
	    	return false;
	    if (!executionDate.equals(transaction.executionDate))
	    	return false;
	    if (amount != transaction.amount)
	    	return false;
	    if (!comment.equals(transaction.comment))
	    	return false;
	    return true;
	}
}
