package domain.transaction;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import domain.account.Account;

public abstract class Transaction {

	private long idNumber;
	private Account performingAccount;
	private Date executionDate;
	protected BigDecimal amount;

	public Transaction(Account performingAccount,
			BigDecimal amount) {
		this.performingAccount = performingAccount;
		this.amount = amount;
		executionDate = Calendar.getInstance().getTime();
	}

	public long getIdNumber() {
		return idNumber;
	}

	public Account getPerformingAccount() {
		return performingAccount;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
	    if (!Transaction.class.isAssignableFrom(object.getClass()))
	        return false;
	    final Transaction transaction = (Transaction) object;
	    if (this.idNumber != transaction.idNumber)
	    	return false;
	    if (!this.performingAccount.equals(transaction.performingAccount))
	    	return false;
	    if (!this.executionDate.equals(transaction.executionDate))
	    	return false;
	    if (!this.amount.equals(transaction.amount))
	    	return false;
		return true;
	}
}