package Transaction;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public abstract class Transaction {
	private TransactionType type;
	private long idNumber;
	private String performingPersonId;
	private long performingAccountId;
	private Date executionDate;
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
}
