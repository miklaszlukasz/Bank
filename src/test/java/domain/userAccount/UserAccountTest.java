package domain.userAccount;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import domain.account.Account;
import domain.transaction.Transaction;
import domain.transaction.Transfer;
import domain.userAccount.UserAccount;
import junit.framework.TestCase;

public class UserAccountTest extends TestCase {
	private UserAccount userAccount;
	private String personalIdNumber;
	private String password;
	
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
		Account account = new Account(userAccount);
		BigDecimal moneyInTransaction = new BigDecimal(1000.63);
		userAccount.depositMoney(account, moneyInTransaction);
		checkAccount(account, moneyInTransaction);		
		checkTransaction(account, account.getTransactionsHistory().iterator().next(), moneyInTransaction);
	}
	
	@Test
	public void testWithdraw() {
		Account account = new Account(userAccount);
		BigDecimal moneyInTransaction = new BigDecimal(666.66);
		userAccount.withdrawMoney(account, moneyInTransaction);
		checkAccount(account, moneyInTransaction.negate());
		checkTransaction(account, account.getTransactionsHistory().iterator().next(), moneyInTransaction);
	}
	
	private void checkAccount(Account account, BigDecimal moneyInTransaction) {
		assertEquals(moneyInTransaction, account.getMoney());
	}
	
	private void checkTransaction(Account account, Transaction transaction, BigDecimal moneyInTransaction) {
		assertEquals(account, transaction.getPerformingAccount());
		assertEquals(moneyInTransaction, transaction.getAmount());
	}
	
	@Test
	public void testTransfer() {
		Account addresse = new Account(userAccount);
		Account recipient = new Account(userAccount);
		
		BigDecimal moneyToTransfer = new BigDecimal(333.33);
		userAccount.transferMoney(addresse, recipient, moneyToTransfer, "test");
		checkAccount(addresse, moneyToTransfer.negate());
		checkAccount(recipient, moneyToTransfer);
		checkTransfer((Transfer) addresse.getTransactionsHistory().iterator().next(), addresse, recipient, moneyToTransfer);
		checkTransfer((Transfer) recipient.getTransactionsHistory().iterator().next(), addresse, recipient, moneyToTransfer);
		assertEquals(addresse.getTransactionsHistory().iterator().next(), recipient.getTransactionsHistory().iterator().next());
		}
	
	private void checkTransfer(Transfer transfer, Account performer, Account recipient, BigDecimal amount) {
		assertEquals(performer, transfer.getPerformingAccount());
		assertEquals(recipient, transfer.getRecipientAccount());
		assertEquals(amount, transfer.getAmount());
	}
}