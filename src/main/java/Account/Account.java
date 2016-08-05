package Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Account.Interfaces.Depositable;
import Account.Interfaces.Transferable;
import Account.Interfaces.Withdrawable;
import Transaction.Deposit;
import Transaction.Transaction;
import Transaction.Transfer;
import Transaction.Withdraw;

public class Account implements Depositable, Withdrawable, Transferable {
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

	public void depositMoney(BigDecimal amount) {
		depositMoney(new Deposit(ownerIdNumber, this.getIdNumber(), amount));
	}

	private void depositMoney(Transaction transaction) {
		money = money.add(transaction.getAmount());
		transactionsHistory.add(transaction);
	}

	public void withdrawMoney(BigDecimal amount) {
		withdrawMoney(new Withdraw(ownerIdNumber, this.getIdNumber(), amount));
	}

	private void withdrawMoney(Transaction transaction) {
		money = money.subtract(transaction.getAmount());
		transactionsHistory.add(transaction);
	}

	public void transferMoney(Account recipient, BigDecimal amount, String comment) {
		Transaction transfer = new Transfer(ownerIdNumber, this.getIdNumber(), recipient.getIdNumber(), amount,
				comment);
		withdrawMoney(transfer);
		recipient.depositMoney(transfer);
	}
	
	public void transferMoney(Account recipient, BigDecimal amount) {
		Transaction transfer = new Transfer(ownerIdNumber, this.getIdNumber(), recipient.getIdNumber(), amount);
		withdrawMoney(transfer);
		recipient.depositMoney(transfer);
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