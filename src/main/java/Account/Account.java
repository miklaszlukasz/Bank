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
	private String idNumber;
	private User owner;
	private Date creationDate;
	private double money;
	private List<Transaction> transactionsHistory;

	public Account(User owner) {
		super();
		this.owner = owner;
		transactionsHistory = new ArrayList<Transaction>();
		creationDate = Calendar.getInstance().getTime();
	}

	public void depositMoney(double amount) {
		Transaction transaction = new Deposit(this, amount);
		transactionsHistory.add(transaction);
		this.money += amount;
	}

	public void withdrawMoney(double amount) {
		if (money >= amount) {
			Transaction transaction = new Withdraw(this, amount);
			transactionsHistory.add(transaction);
			this.money -= amount;
		} else
			System.out.println("Not enough money to make transaction!");
	}

	public void transferMoney(Account recipient, double amount, String comment) {
		if (money >= amount) {
			pourMoney(recipient, amount);
			Transfer transaction = new Transfer(this, recipient, amount, comment);
			transactionsHistory.add(transaction);
		} else
			System.out.println("Not enough money to make transaction!");
	}

	private void pourMoney(Account recipient, double amount) {
		this.withdrawMoney(amount);
		recipient.depositMoney(amount);
	}

	public String getIdNumber() {
		return idNumber;
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