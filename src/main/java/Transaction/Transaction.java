package Transaction;

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
	protected String comment;

	public Transaction(TransactionType transactionType, String performingPersonId, long performingAccountId,
			BigDecimal amount) {
		this.performingUserAccountId = performingPersonId;
		this.type = transactionType;
		this.performingAccountId = performingAccountId;
		this.amount = amount;
		executionDate = Calendar.getInstance().getTime();
		comment = generateComment(transactionType);
	}

	private String generateComment(TransactionType transactionType) {
		switch (transactionType) {
		case DEPOSIT:
			return "Deposit to account: " + amount;
		case WITHDRAW:
			return "Withdraw from account: " + amount;
		default:
			return "";
		}
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

	public String getComment() {
		return comment;
	}
}
