package domain.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import domain.account.interfaces.Depositable;
import domain.account.interfaces.Transferable;
import domain.account.interfaces.Withdrawable;
import domain.transaction.Transaction;
import domain.transaction.TransactionType;
import domain.transaction.Transfer;

@Entity
public class Account implements Depositable, Withdrawable, Transferable {
	@Id
	@GeneratedValue
	private long accountIdNumber;
	@ManyToOne
	private String ownerIdNumber;
	private Date creationDate;
	protected BigDecimal money;

	@OneToMany(mappedBy = "account")
	private List<Transaction> transactionsHistory;

	public Account(String ownerIdNumber) {
		this.ownerIdNumber = ownerIdNumber;
		transactionsHistory = new ArrayList<Transaction>();
		creationDate = Calendar.getInstance().getTime();
		money = new BigDecimal(0);
	}

	public void depositMoney(BigDecimal amount) {
		depositMoney(new Transaction(TransactionType.DEPOSIT, ownerIdNumber, accountIdNumber, amount));
	}

	private void depositMoney(Transaction transaction) {
		money = money.add(transaction.getAmount());
		transactionsHistory.add(transaction);
	}

	public void withdrawMoney(BigDecimal amount) {
		withdrawMoney(new Transaction(TransactionType.WITHDRAW, ownerIdNumber, accountIdNumber, amount));
	}

	private void withdrawMoney(Transaction transaction) {
		money = money.subtract(transaction.getAmount());
		transactionsHistory.add(transaction);
	}

	public void transferMoney(Account recipient, BigDecimal amount, String comment) {
		Transaction transfer = new Transfer(ownerIdNumber, accountIdNumber, recipient.getIdNumber(), amount, comment);
		withdrawMoney(transfer);
		recipient.depositMoney(transfer);
	}

	public void transferMoney(Account recipient, BigDecimal amount) {
		Transaction transfer = new Transfer(ownerIdNumber, accountIdNumber, recipient.getIdNumber(), amount);
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
	
	@Override
	public String toString() {
		return "Account number: " + accountIdNumber + "\t Money on account: " + money;
	}
}