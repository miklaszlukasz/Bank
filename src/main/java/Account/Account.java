package Account;

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
	protected User owner;
	private Date creationDate;
	protected double money;
	private List<Transaction> transactionsHistory;

	public Account(User owner) {
		this.owner = owner;
		transactionsHistory = new ArrayList<Transaction>();
		creationDate = Calendar.getInstance().getTime();
	}
	
	public Account() {
		transactionsHistory = new ArrayList<Transaction>();
		creationDate = Calendar.getInstance().getTime();
	}

	public void depositMoney(double amount) {
		Transaction deposit = new Deposit(owner.getIdNumber(), this.getIdNumber(), amount);
		transactionsHistory.add(deposit);
		money += amount;
	}

	public void withdrawMoney(double amount) {
			Transaction withdraw = new Withdraw(owner.getIdNumber(), this.getIdNumber(), amount);
			transactionsHistory.add(withdraw);
			money -= amount;
	}

	public void transferMoney(Account recipient, double amount, String comment) {
		Transaction transfer = new Transfer(owner.getIdNumber(), this.getIdNumber(), recipient.getIdNumber(), amount, comment);
		withdrawMoney(amount, transfer);
		recipient.depositMoney(amount, transfer);
	}
	
	protected void depositMoney(double amount, Transaction transaction) {
		transactionsHistory.add(transaction);
		money += amount;
	}

	protected void withdrawMoney(double amount, Transaction transaction) {
		transactionsHistory.add(transaction);
		money -= amount;
}
	
	public long getIdNumber() {
		return accountIdNumber;
	}
	
	public User getOwner() {
		return owner;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public double getMoney() {
		return money;
	}

	public List<Transaction> getTransactionsHistory() {
		return transactionsHistory;
	}
}