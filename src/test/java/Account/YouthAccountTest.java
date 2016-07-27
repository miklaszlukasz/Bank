package Account;

import static org.junit.Assert.*;

import org.junit.Test;

import Transaction.Transaction;
import Transaction.TransactionType;
import Transaction.Transfer;

public class YouthAccountTest {
	private final double epsilon = 0.0;
	private User owner;
	private User supervisor;
	private Account personalAccount;
	private double moneyOnAccount;
	private Transaction transcaction;

	public void setUp() {
		owner = new User.Builder().build();
		personalAccount = new YouthAccount(owner, supervisor);
		assertEquals(moneyOnAccount, personalAccount.getMoney(), epsilon);
	}
	
	@Test
	public void testDeposit() {
		double moneyToDeposit = 1000.63;
		moneyOnAccount += moneyToDeposit;
		personalAccount.depositMoney(moneyToDeposit);
		assertEquals(moneyOnAccount, personalAccount.getMoney(), epsilon);
		assertEquals(1, personalAccount.getTransactionsHistory().size());
		
		transcaction = personalAccount.getTransactionsHistory().get(0);
		assertEquals(TransactionType.DEPOSIT, transcaction.getType());
		assertEquals(personalAccount, transcaction.getPerformer());
		assertEquals(moneyToDeposit, transcaction.getAmount(), epsilon);
		
	}
	
	@Test
	public void testWithdraw() {
		double monetToWithdraw = 666.66;
		moneyOnAccount -= monetToWithdraw;
		personalAccount.withdrawMoney(monetToWithdraw);
		assertEquals(moneyOnAccount, personalAccount.getMoney(), epsilon);
		assertEquals(1, personalAccount.getTransactionsHistory().size());
		
		transcaction = personalAccount.getTransactionsHistory().get(0);
		assertEquals(TransactionType.WITHDRAW, transcaction.getType());
		assertEquals(personalAccount, transcaction.getPerformer());
		assertEquals(monetToWithdraw, transcaction.getAmount(), epsilon);
	}
	
	@Test
	public void testTransfer() {
		double moneyToTransfer = 333.33;

		moneyOnAccount = 452.73;
		personalAccount.depositMoney(moneyOnAccount);
		moneyOnAccount -= moneyToTransfer;

		Account recipient = new PersonalAccount(owner);
		double moneyOnRecipient = 23.13;
		recipient.depositMoney(moneyOnRecipient);
		moneyOnRecipient += moneyToTransfer;
		
		personalAccount.transferMoney(recipient, moneyToTransfer, "test");
		assertEquals(moneyOnAccount, personalAccount.getMoney(), epsilon);
		assertEquals(moneyOnRecipient, recipient.getMoney(), epsilon);
		
		Transfer transfer = (Transfer) personalAccount.getTransactionsHistory().get(1);
		assertEquals(TransactionType.TRANSFER, transfer.getType());
		assertEquals(personalAccount, transfer.getPerformer());
		assertEquals(moneyToTransfer, transfer.getAmount(), epsilon);
		assertEquals(recipient, transfer.getRecipient());
		
		Transfer recipientTranscaction = (Transfer) recipient.getTransactionsHistory().get(1);
		assertEquals(TransactionType.TRANSFER, recipientTranscaction.getType());
		assertEquals(personalAccount, recipientTranscaction.getPerformer());
		assertEquals(moneyToTransfer, recipientTranscaction.getAmount(), epsilon);
		assertEquals(recipient, recipientTranscaction.getRecipient());
		
		assertEquals(transfer, recipientTranscaction);
	}
}
