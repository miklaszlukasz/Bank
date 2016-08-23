package domain.transactions;

import java.math.BigDecimal;

import org.junit.Test;

import domain.account.Account;
import domain.transaction.Transaction;
import domain.transaction.Withdraw;
import domain.userAccount.UserAccount;
import junit.framework.TestCase;

public class WithdrawTest extends TestCase {

	@Test
	public void testWithdraw() {
		String ownerId = "76041019253";
		String password = "test";
		UserAccount userAccount = new UserAccount.Builder(ownerId, password).build();
		Account account = new Account(userAccount);
		BigDecimal amount = new BigDecimal(2000.35);
		Transaction withdraw = new Withdraw(account, amount);
		checkTransaction(withdraw, account, amount);
	}
	
	private void checkTransaction(Transaction transaction, Account performer, BigDecimal amount) {
		assertEquals(performer, transaction.getPerformingAccount());
		assertEquals(amount, transaction.getAmount());
	}
}
