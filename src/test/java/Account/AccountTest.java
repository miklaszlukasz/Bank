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
	private double moneyOnAccount;
	private Transaction transcaction;

	public void setUp() {
		String randomPersonIdNumber = "22021100178";
		String password = "test";
		owner = new User.Builder(randomPersonIdNumber, password).build();
		account = new Account(owner);
		owner.addAccount(account);
		assertEquals(moneyOnAccount, account.getMoney(), epsilon);
	}
	
	@Test
	public void testDeposit() {
		double moneyInTransaction = 1000.63;
		account.depositMoney(moneyInTransaction);
		checkAccount(account, moneyInTransaction);
		
		transcaction = account.getTransactionsHistory().get(0);
		checkTransaction(transcaction, TransactionType.DEPOSIT, moneyInTransaction);
	}
	
	@Test
	public void testWithdraw() {
		double moneyInTransaction = 666.66;
		account.withdrawMoney(moneyInTransaction);
		checkAccount(account, -moneyInTransaction);
		
		transcaction = account.getTransactionsHistory().get(0);
		checkTransaction(transcaction, TransactionType.WITHDRAW, moneyInTransaction);

	}
	
	private void checkAccount(Account account, double moneyInTransaction) {
		assertEquals(moneyInTransaction, account.getMoney(), epsilon);
		assertEquals(1, account.getTransactionsHistory().size());
	}
	
	private void checkTransaction(Transaction transaction, TransactionType transactionType, double moneyInTransaction) {
		assertEquals(transactionType, transcaction.getType());
		assertEquals(account.getIdNumber(), transcaction.getPerformer());
		assertEquals(moneyInTransaction, transcaction.getAmount(), epsilon);
	}
	
	@Test
	public void testTransfer() {
		double moneyToTransfer = 333.33;

		moneyOnAccount = 452.73;
		account.depositMoney(moneyOnAccount);
		moneyOnAccount -= moneyToTransfer;
		User recipientOwner = new User.Builder("22021100178", "testowy").build();
		Account recipient = new Account(recipientOwner);
		double moneyOnRecipient = 23.13;
		recipient.depositMoney(moneyOnRecipient);
		moneyOnRecipient += moneyToTransfer;
		
		account.transferMoney(recipient, moneyToTransfer, "test");
		assertEquals(moneyOnAccount, account.getMoney(), epsilon);
		assertEquals(moneyOnRecipient, recipient.getMoney(), epsilon);
		
		Transfer transfer = (Transfer) account.getTransactionsHistory().get(1);		
		checkTransfer(transfer, moneyToTransfer);
		Transfer recipientTranscaction = (Transfer) recipient.getTransactionsHistory().get(1);
		checkTransfer(recipientTranscaction, moneyToTransfer);
		
		assertEquals(transfer, recipientTranscaction);
	}
	
	private void checkTransfer(Transaction transaction, double moneyOnTransaction) {
		assertEquals(TransactionType.TRANSFER, transaction.getType());
		assertEquals(account.getIdNumber(), transaction.getPerformer());
		assertEquals(moneyOnTransaction, transaction.getAmount(), epsilon);
		assertEquals(transaction.getIdNumber(), ((Transfer) transaction).getRecipientIdNumber());
	}
}
