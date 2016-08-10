package domain.userAccount;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import domain.account.Account;
import domain.transaction.Transaction;
import domain.transaction.TransactionType;
import domain.transaction.Transfer;
import domain.userAccount.UserAccount;
import junit.framework.TestCase;

public class UserAccountTest extends TestCase {
	private UserAccount userAccount;
	String personalIdNumber;
	String password;
	public void setUp() {
		personalIdNumber = "76041019253";
		password = "test";
		userAccount = new UserAccount.Builder(personalIdNumber, password).firstName("Jan").lastName("Kowalski")
				.generateDateOfBirthFromIdNumber().build();
	}
	
	@Test
	public void testVerify() {
		assertTrue(userAccount.verifyIdNumberAndPassword(personalIdNumber, password));
	}
	
	@Test
	public void testConstructor() throws ParseException {
		assertEquals("76041019253", userAccount.getIdNumber());
		assertEquals("test", userAccount.getPassword());
		assertEquals("Jan", userAccount.getFirstName());
		assertEquals("Kowalski", userAccount.getLastName());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(formatter.parse("10/04/1976"), userAccount.getDateOfBirth());
	}
	
	@Test
	public void testDeposit() {
		Account account = new Account(userAccount.getIdNumber());
		BigDecimal moneyInTransaction = new BigDecimal(1000.63);
		userAccount.depositMoney(account, moneyInTransaction);
		checkAccount(account, moneyInTransaction);		
		checkTransaction(account.getIdNumber(), account.getTransactionsHistory().get(0), TransactionType.DEPOSIT, moneyInTransaction);
	}
	
	@Test
	public void testWithdraw() {
		Account account = new Account(userAccount.getIdNumber());
		BigDecimal moneyInTransaction = new BigDecimal(666.66);
		userAccount.withdrawMoney(account, moneyInTransaction);
		checkAccount(account, moneyInTransaction.negate());
		checkTransaction(account.getIdNumber(), account.getTransactionsHistory().get(0), TransactionType.WITHDRAW, moneyInTransaction);

	}
	
	private void checkAccount(Account account, BigDecimal moneyInTransaction) {
		assertEquals(moneyInTransaction, account.getMoney());
	}
	
	private void checkTransaction(long accountIdNumber, Transaction transaction, TransactionType transactionType, BigDecimal moneyInTransaction) {
		assertEquals(transactionType, transaction.getType());
		assertEquals(accountIdNumber, transaction.getPerformer());
		assertEquals(moneyInTransaction, transaction.getAmount());
	}
	
	@Test
	public void testTransfer() {
		Account account = new Account(userAccount.getIdNumber());
		BigDecimal moneyOnAccount = new BigDecimal(452.73);
		userAccount.depositMoney(account, moneyOnAccount);
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
		userAccount.transferMoney(performer, recipient, moneyToTransfer, "test");
		checkAccount(performer, monetOnPerformer.subtract(moneyToTransfer));
		checkAccount(recipient, moneyOnRecipient.add(moneyToTransfer));
		checkTransfer(performer.getIdNumber(), (Transfer) performer.getTransactionsHistory().get(1), moneyToTransfer);
		checkTransfer(performer.getIdNumber(), (Transfer) recipient.getTransactionsHistory().get(1), moneyToTransfer);
	}
	
	private void checkTransfer(long accountIdNumber, Transaction transaction, BigDecimal moneyOnTransaction) {
		assertEquals(TransactionType.TRANSFER, transaction.getType());
		assertEquals(accountIdNumber, transaction.getPerformer());
		assertEquals(moneyOnTransaction, transaction.getAmount());
		assertEquals(transaction.getIdNumber(), ((Transfer) transaction).getRecipientIdNumber());
	}
}