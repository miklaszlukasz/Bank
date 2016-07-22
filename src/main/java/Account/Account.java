package Account;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Account {
	private String idNumber;
	private Date creationDate;
	private double money;
	private List<Transaction> transactionsHistory;

	public Account() {
		super();
		this.transactionsHistory = new ArrayList<Transaction>();
		creationDate = Calendar.getInstance().getTime();
	}

	public void makeTransaction(Account recipient, double amount, String comment) {
		if (money >= amount) {
			pourMoney(recipient, amount);
			addTransactionToHistory(recipient, amount, comment);
		} else
			System.out.println("Not enough money to make transaction!");
	}

	private void pourMoney(Account recipient, double amount) {
		this.withdrawMoney(amount);
		recipient.depositMoney(amount);
	}
	
	private void addTransactionToHistory(Account recipient, double amount, String comment) {
		Transaction transaction = new Transaction(this, recipient, amount, comment);
		transactionsHistory.add(transaction);
	}
	
	public void depositMoney(double amount) {
		this.money += amount;
	}

	public void withdrawMoney(double amount) {
		this.money -= amount;
	}

	public String getIdNumber() {
		return idNumber;
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