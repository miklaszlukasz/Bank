package Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Transaction.Deposit;
import Transaction.Transaction;
import Transaction.Transfer;
import Transaction.Withdraw;

public class Account {
	private long accountIdNumber;
	protected String ownerIdNumber;
	private Date creationDate;
	protected BigDecimal money;
	private List<Transaction> transactionsHistory;

	public Account(String ownerIdNumber) {
		this.ownerIdNumber = ownerIdNumber;
		transactionsHistory = new ArrayList<Transaction>();
		creationDate = Calendar.getInstance().getTime();
		money = new BigDecimal(0);
	}
	
	public Account() {
		transactionsHistory = new ArrayList<Transaction>();
		creationDate = Calendar.getInstance().getTime();
	}

	public void depositMoney(BigDecimal amount) {
		Transaction deposit = new Deposit(ownerIdNumber, this.getIdNumber(), amount);
		transactionsHistory.add(deposit);
		money = money.add(amount);
	}

	public void withdrawMoney(BigDecimal amount) {
			Transaction withdraw = new Withdraw(ownerIdNumber, this.getIdNumber(), amount);
			transactionsHistory.add(withdraw);
			money = money.subtract(amount);
	}

	public void transferMoney(Account recipient, BigDecimal amount, String comment) {
		Transaction transfer = new Transfer(ownerIdNumber, this.getIdNumber(), recipient.getIdNumber(), amount, comment);
		withdrawMoney(amount, transfer);
		recipient.depositMoney(amount, transfer);
	}
	
	protected void depositMoney(BigDecimal amount, Transaction transaction) {
		transactionsHistory.add(transaction);
		money = money.add(amount);
	}

	protected void withdrawMoney(BigDecimal amount, Transaction transaction) {
		transactionsHistory.add(transaction);
		money = money.subtract(amount);
}
	
	public long getIdNumber() {
		return accountIdNumber;
	}
	
	public String getOwnerIdNumber() {
		return ownerIdNumber;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public List<Transaction> getTransactionsHistory() {
		return transactionsHistory;
	}
}