package Account;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
	private long idNumber;
	private double amount;
	private Account adressee;
	private Account recipient;
	private String comment;
	private Date executionDate;

	public Transaction(Account adressee, Account recipient, double amount, String comment) {
		super();
		this.amount = amount;
		this.adressee = adressee;
		this.recipient = recipient;
		this.comment = comment;
		executionDate = Calendar.getInstance().getTime();
	}

	public long getIdNumber() {
		return idNumber;
	}

	public double getAmount() {
		return amount;
	}

	public Account getAdressee() {
		return adressee;
	}

	public Account getRecipient() {
		return recipient;
	}

	public String getComment() {
		return comment;
	}

	public Date getExecutionDate() {
		return executionDate;
	}
}
