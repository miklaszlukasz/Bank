package Transaction;

import java.util.Calendar;
import java.util.Date;

import Account.Account;

public abstract class Transaction implements CommentGenerable {
	TransactionType type;
	protected long idNumber;
	protected Account performer;
	protected Date executionDate;
	protected double amount;
	protected String comment;

	public Transaction(TransactionType type, Account performer, double amount) {
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
