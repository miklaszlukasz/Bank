package Account;

import java.math.BigDecimal;

import org.junit.Test;

import Transaction.Transaction;
import Transaction.TransactionType;
import Transaction.Transfer;
import junit.framework.TestCase;

public class AccountTest extends TestCase {
	private Account account;

	public void setUp() {
		String ownerIdNumber = "22021100178";
		account = new Account(ownerIdNumber);
		checkAccount(account, new BigDecimal(0));
	}
	
	@Test
	public void testDeposit() {
		BigDecimal moneyInTransaction = new BigDecimal(1000.63);
		account.depositMoney(moneyInTransaction);
		checkAccount(account, moneyInTransaction);		
		checkTransaction(account.getTransactionsHistory().get(0), TransactionType.DEPOSIT, moneyInTransaction);
	}
	
	@Test
	public void testWithdraw() {
		BigDecimal moneyInTransaction = new BigDecimal(666.66);
		account.withdrawMoney(moneyInTransaction);
		checkAccount(account, moneyInTransaction.negate());
		checkTransaction(account.getTransactionsHistory().get(0), TransactionType.WITHDRAW, moneyInTransaction);

	}
	
	private void checkAccount(Account account, BigDecimal moneyInTransaction) {
		assertEquals(moneyInTransaction, account.getMoney());
	}
	
	private void checkTransaction(Transaction transaction, TransactionType transactionType, BigDecimal moneyInTransaction) {
		assertEquals(transactionType, transaction.getType());
		assertEquals(account.getIdNumber(), transaction.getPerformer());
		assertEquals(moneyInTransaction, transaction.getAmount());
	}
	
	@Test
	public void testTransfer() {
		BigDecimal moneyOnAccount = new BigDecimal(452.73);
		account.depositMoney(moneyOnAccount);
		checkAccount(account, moneyOnAccount);		
		
		String recipientOwnerId = "22021100178";
		Account recipient = new Account(recipientOwnerId);
		BigDecimal moneyOnRecipient = new BigDecimal(23.13);
		recipient.depositMoney(moneyOnRecipient);
		checkAccount(recipient, moneyOnRecipient);
		
		doTransfer(account, moneyOnAccount, recipient, moneyOnRecipient);
		}
	
	private void doTransfer(Account performer, BigDecimal monetOnPerformer, Account recipient, BigDecimal moneyOnRecipient) {
		BigDecimal moneyToTransfer = new BigDecimal(333.33);
		account.transferMoney(recipient, moneyToTransfer, "test");
		checkAccount(account, monetOnPerformer.subtract(moneyToTransfer));
		checkAccount(recipient, moneyOnRecipient.add(moneyToTransfer));
		checkTransfer((Transfer) account.getTransactionsHistory().get(1), moneyToTransfer);
		checkTransfer((Transfer) recipient.getTransactionsHistory().get(1), moneyToTransfer);
	}
	
	private void checkTransfer(Transaction transaction, BigDecimal moneyOnTransaction) {
		assertEquals(TransactionType.TRANSFER, transaction.getType());
		assertEquals(account.getIdNumber(), transaction.getPerformer());
		assertEquals(moneyOnTransaction, transaction.getAmount());
		assertEquals(transaction.getIdNumber(), ((Transfer) transaction).getRecipientIdNumber());
	}
}
