package domain.account;

import java.math.BigDecimal;

import org.junit.Test;

import domain.account.Account;
import domain.transaction.Transaction;
import domain.transaction.Transfer;
import domain.userAccount.UserAccount;
import junit.framework.TestCase;

public class AccountTest extends TestCase {
	private UserAccount owner;
	private Account account;


		public void setUp() {
			String ownerIdNumber = "22021100178";
			String password = "test";
			owner = new UserAccount.Builder(ownerIdNumber, password).build();
			account = new Account(owner);
			checkAccount(account, new BigDecimal(0));
		}
		
		@Test
		public void testIsGoodAccountIdNumber() {
			assertTrue(account.isGoodAccountIdNumber(account.getIdNumber()));
		}
		
		@Test
		public void testDeposit() {
			BigDecimal moneyInTransaction = new BigDecimal(1000.63);
			account.depositMoney(moneyInTransaction);
			checkAccount(account, moneyInTransaction);		
			checkTransaction(account.getTransactionsHistory().iterator().next(), moneyInTransaction);
		}
		
		@Test
		public void testWithdraw() {
			BigDecimal moneyInTransaction = new BigDecimal(666.66);
			account.withdrawMoney(moneyInTransaction);
			checkAccount(account, moneyInTransaction.negate());
			checkTransaction(account.getTransactionsHistory().iterator().next(), moneyInTransaction);
		}
		
		private void checkAccount(Account account, BigDecimal moneyInTransaction) {
			assertEquals(moneyInTransaction, account.getMoney());
		}
		
		private void checkTransaction(Transaction transaction, BigDecimal moneyInTransaction) {
			assertEquals(account, transaction.getPerformingAccount());
			assertEquals(moneyInTransaction, transaction.getAmount());
		}
		
		@Test
		public void testTransfer() {		
			Account recipient = new Account(owner);
			
			BigDecimal moneyToTransfer = new BigDecimal(333.33);
			account.transferMoney(recipient, moneyToTransfer, "test");
			checkAccount(account, moneyToTransfer.negate());
			checkAccount(recipient, moneyToTransfer);
			checkTransfer(recipient, (Transfer) account.getTransactionsHistory().iterator().next(), moneyToTransfer);
			checkTransfer(recipient, (Transfer) recipient.getTransactionsHistory().iterator().next(), moneyToTransfer);
			}
		
		private void checkTransfer(Account recipient, Transaction transaction, BigDecimal moneyOnTransaction) {
			assertEquals(account, transaction.getPerformingAccount());
			assertEquals(moneyOnTransaction, transaction.getAmount());
			assertEquals(recipient, ((Transfer) transaction).getRecipientAccount());
		}
}
