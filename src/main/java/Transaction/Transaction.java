package Transaction;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public abstract class Transaction {
	private TransactionType type;
	protected long idNumber;
	protected String performingPersonId;
	protected long performingAccountId;
	protected Date executionDate;
	protected BigDecimal amount;
	protected String comment;

	public Transaction(TransactionType type, String performingPersonId, long performingAccountId, BigDecimal amount) {
		this.performingPersonId = performingPersonId;
		this.type = type;
		this.performingAccountId = performingAccountId;
		this.amount = amount;
		executionDate = Calendar.getInstance().getTime();
	}

	public TransactionType getType() {
		return type;
	}
	
	public long getIdNumber() {
		return idNumber;
	}

	public String getPerformingPerson() {
		return performingPersonId;
	}

	public long getPerformer() {
		return performingAccountId;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	public String getComment() {
		return comment;
	}
	
	abstract String generateComment();
	
//	@Override
//	public boolean equals(Object object) {
//		if (object == null)
//			return false;
//	    if (!Transaction.class.isAssignableFrom(object.getClass()))
//	        return false;
//	    final Transaction transaction= (Transaction) object;
////	    if (idNumber =! transaction.idNumber)
////	    	return false;
//	    if (!type.equals(transaction.type))
//	    	return false;
//	    if (!performingPerson.equals(transaction.performingPerson))
//	    	return false;
//	    if (!performingAccount.equals(transaction.performingAccount))
//	    	return false;
//	    if (!executionDate.equals(transaction.executionDate))
//	    	return false;
//	    if (amount != transaction.amount)
//	    	return false;
//	    if (!comment.equals(transaction.comment))
//	    	return false;
//	    return true;
//	}
}
