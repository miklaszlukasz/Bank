package domain.account;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import domain.account.interfaces.Depositable;
import domain.account.interfaces.Transferable;
import domain.account.interfaces.Withdrawable;
import domain.transaction.Deposit;
import domain.transaction.Transaction;
import domain.transaction.Transfer;
import domain.transaction.Withdraw;
import domain.userAccount.UserAccount;

public class Account implements Depositable, Withdrawable, Transferable {
	private long idNumber;
	private UserAccount owner;
	private Date creationDate;
	protected BigDecimal money;

	private Set<Transaction> transactionsHistory;

	public Account(UserAccount owner) {
		this.owner = owner;
		transactionsHistory = new HashSet<Transaction>();
		creationDate = new Date(Calendar.getInstance().getTimeInMillis());
		money = new BigDecimal(0);
	}

	public void depositMoney(BigDecimal amount) {
		depositMoney(new Deposit(this, amount));
	}
	
	public void withdrawMoney(BigDecimal amount) {
		withdrawMoney(new Withdraw(this, amount));
	}

	public void transferMoney(Account recipient, BigDecimal amount, String comment) {
		Transaction transfer = new Transfer(this, recipient, amount, comment);
		withdrawMoney(transfer);
		recipient.depositMoney(transfer);
	}

	public void transferMoney(Account recipient, BigDecimal amount) {
		Transaction transfer = new Transfer(this, recipient, amount);
		withdrawMoney(transfer);
		recipient.depositMoney(transfer);
	}
	
	private void depositMoney(Transaction transaction) {
		money = money.add(transaction.getAmount());
		transactionsHistory.add(transaction);
	}

	private void withdrawMoney(Transaction transaction) {
		money = money.subtract(transaction.getAmount());
		transactionsHistory.add(transaction);
	}

	public long getIdNumber() {
		return idNumber;
	}

	public UserAccount getOwner() {
		return owner;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public Set<Transaction> getTransactionsHistory() {
		return transactionsHistory;
	}

	public boolean isGoodAccountIdNumber(long idNumber) {
		return this.idNumber == idNumber;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		final Account account = (Account) object;
		if (idNumber != account.idNumber)
			return false;
		if (!owner.equals(account.owner))
			return false;
		if (!creationDate.equals(account.creationDate))
			return false;
		if (!money.equals(account.money))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account number: " + idNumber + "\t Money on account: " + money;
	}
}