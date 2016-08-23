package domain.transactions;

import java.math.BigDecimal;

import org.junit.Test;

import domain.account.Account;
import domain.transaction.Deposit;
import domain.transaction.Transaction;
import domain.userAccount.UserAccount;
import junit.framework.TestCase;

public class DepositTest extends TestCase {

	@Test
	public void testDeposit() {
		String ownerId = "76041019253";
		String password = "test";
		UserAccount userAccount = new UserAccount.Builder(ownerId, password).build();
		Account account = new Account(userAccount);
		BigDecimal amount = new BigDecimal(2000.35);
		Transaction deposit = new Deposit(account, amount);
		checkTransaction(deposit, account, amount);
	}
	
	private void checkTransaction(Transaction transaction, Account performer, BigDecimal amount) {
		assertEquals(performer, transaction.getPerformingAccount());
		assertEquals(amount, transaction.getAmount());
	}
}