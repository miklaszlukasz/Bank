package Transaction;

import java.util.Date;

import Account.Account;

public abstract class Transaction {
	TransactionType type;
	protected long idNumber;
	protected Account performer;
	protected Date executionDate;
	protected double amount;
	protected String comment;

	public TransactionType getType() {
		return type;
	}
	
	public long getIdNumber() {
		return idNumber;
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
}
