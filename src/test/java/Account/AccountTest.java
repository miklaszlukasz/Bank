package Account;

import org.junit.Test;

import Transaction.Transaction;
import Transaction.TransactionType;
import Transaction.Transfer;
import junit.framework.TestCase;

public class AccountTest extends TestCase {
	private final double epsilon = 0.0;
	private User owner;
	private Account account;

	public void setUp() {
		String randomPersonIdNumber = "22021100178";
		String password = "test";
		owner = new User.Builder(randomPersonIdNumber, password).build();
		account = new Account(owner);
		owner.addAccount(account);
		checkAccount(account, 0);
	}
	
	@Test
	public void testDeposit() {
		double moneyInTransaction = 1000.63;
		account.depositMoney(moneyInTransaction);
		checkAccount(account, moneyInTransaction);		
		checkTransaction(account.getTransactionsHistory().get(0), TransactionType.DEPOSIT, moneyInTransaction);
	}
	
	@Test
	public void testWithdraw() {
		double moneyInTransaction = 666.66;
		account.withdrawMoney(moneyInTransaction);
		checkAccount(account, -moneyInTransaction);
		checkTransaction(account.getTransactionsHistory().get(0), TransactionType.WITHDRAW, moneyInTransaction);

	}
	
	private void checkAccount(Account account, double moneyInTransaction) {
		assertEquals(moneyInTransaction, account.getMoney(), epsilon);
	}
	
	private void checkTransaction(Transaction transaction, TransactionType transactionType, double moneyInTransaction) {
		assertEquals(transactionType, transaction.getType());
		assertEquals(account.getIdNumber(), transaction.getPerformer());
		assertEquals(moneyInTransaction, transaction.getAmount(), epsilon);
	}
	
	@Test
	public void testTransfer() {
		double moneyOnAccount = 452.73;
		account.depositMoney(moneyOnAccount);
		checkAccount(account, moneyOnAccount);		
		
		User recipientOwner = new User.Builder("22021100178", "testowy").build();
		Account recipient = new Account(recipientOwner);
		double moneyOnRecipient = 23.13;
		recipient.depositMoney(moneyOnRecipient);
		checkAccount(recipient, moneyOnRecipient);
		
		double moneyToTransfer = 333.33;
		account.transferMoney(recipient, moneyToTransfer, "test");
		checkAccount(account, moneyOnAccount-moneyToTransfer);
		checkAccount(recipient, moneyOnRecipient+moneyToTransfer);
		
		checkTransfer((Transfer) account.getTransactionsHistory().get(1), moneyToTransfer);
		checkTransfer((Transfer) recipient.getTransactionsHistory().get(1), moneyToTransfer);
		}
	
	private void checkTransfer(Transaction transaction, double moneyOnTransaction) {
		assertEquals(TransactionType.TRANSFER, transaction.getType());
		assertEquals(account.getIdNumber(), transaction.getPerformer());
		assertEquals(moneyOnTransaction, transaction.getAmount(), epsilon);
		assertEquals(transaction.getIdNumber(), ((Transfer) transaction).getRecipientIdNumber());
	}
}
