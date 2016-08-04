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
		double moneyToDeposit = 1000.63;
		moneyOnAccount += moneyToDeposit;
		account.depositMoney(moneyToDeposit);
		assertEquals(moneyOnAccount, account.getMoney(), epsilon);
		assertEquals(1, account.getTransactionsHistory().size());
		
		transcaction = account.getTransactionsHistory().get(0);
		assertEquals(TransactionType.DEPOSIT, transcaction.getType());
		assertEquals(account.getIdNumber(), transcaction.getPerformer());
		assertEquals(moneyToDeposit, transcaction.getAmount(), epsilon);	
	}
	
	@Test
	public void testWithdraw() {
		double monetToWithdraw = 666.66;
		moneyOnAccount -= monetToWithdraw;
		account.withdrawMoney(monetToWithdraw);
		assertEquals(moneyOnAccount, account.getMoney(), epsilon);
		assertEquals(1, account.getTransactionsHistory().size());
		
		transcaction = account.getTransactionsHistory().get(0);
		assertEquals(TransactionType.WITHDRAW, transcaction.getType());
		assertEquals(account.getIdNumber(), transcaction.getPerformer());
		assertEquals(monetToWithdraw, transcaction.getAmount(), epsilon);
	}
	
	@Test
	public void testTransfer() {
		double moneyToTransfer = 333.33;

		moneyOnAccount = 452.73;
		account.depositMoney(moneyOnAccount);
		moneyOnAccount -= moneyToTransfer;

		Account recipient = new Account();
		double moneyOnRecipient = 23.13;
		recipient.depositMoney(moneyOnRecipient);
		moneyOnRecipient += moneyToTransfer;
		
		account.transferMoney(recipient, moneyToTransfer, "test");
		assertEquals(moneyOnAccount, account.getMoney(), epsilon);
		assertEquals(moneyOnRecipient, recipient.getMoney(), epsilon);
		
		Transfer transfer = (Transfer) account.getTransactionsHistory().get(1);
		assertEquals(TransactionType.TRANSFER, transfer.getType());
		assertEquals(account.getIdNumber(), transfer.getPerformer());
		assertEquals(moneyToTransfer, transfer.getAmount(), epsilon);
		assertEquals(recipient.getIdNumber(), transfer.getRecipientIdNumber());
		
		Transfer recipientTranscaction = (Transfer) recipient.getTransactionsHistory().get(1);
		assertEquals(TransactionType.TRANSFER, recipientTranscaction.getType());
		assertEquals(account.getIdNumber(), recipientTranscaction.getPerformer());
		assertEquals(moneyToTransfer, recipientTranscaction.getAmount(), epsilon);
		assertEquals(recipient.getIdNumber(), recipientTranscaction.getRecipientIdNumber());
		
		assertEquals(transfer, recipientTranscaction);
	}
}
