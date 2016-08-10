package domain.transaction;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

	private TransactionType type;
	@Id
	@GeneratedValue
	private long idNumber;
	@ManyToOne
	private String performingUserAccountId;
	@ManyToOne
	private long performingAccountId;
	private Date executionDate;
	protected BigDecimal amount;

	public Transaction(TransactionType transactionType, String performingPersonId, long performingAccountId,
			BigDecimal amount) {
		this.performingUserAccountId = performingPersonId;
		this.type = transactionType;
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
		return performingUserAccountId;
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
}