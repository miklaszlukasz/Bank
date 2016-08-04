package Account.rubbish;

import org.junit.Test;

import Account.Account;
import Account.User;
import Account.User.Builder;
import Account.rubbish.PersonalAccount;
import Account.rubbish.YouthAccount;
import Transaction.Transaction;
import Transaction.TransactionType;
import Transaction.Transfer;
import junit.framework.TestCase;

public class YouthAccountTest extends TestCase {
	private final double epsilon = 0.0;
	private User owner;
	private User supervisor;
	private Account youthAccount;
	private double moneyOnAccount;
	private Transaction transcaction;

	public void setUp() {
		owner = new User.Builder().build();
		youthAccount = new YouthAccount(owner, supervisor);
		assertEquals(moneyOnAccount, youthAccount.getMoney(), epsilon);
	}
	
	@Test
	public void testDepositOnOwner() {
		double moneyToDeposit = 1000.63;
		moneyOnAccount += moneyToDeposit;
		youthAccount.depositMoney(moneyToDeposit);
		assertEquals(moneyOnAccount, youthAccount.getMoney(), epsilon);
		assertEquals(1, youthAccount.getTransactionsHistory().size());
		
		transcaction = youthAccount.getTransactionsHistory().get(0);
		assertEquals(TransactionType.DEPOSIT, transcaction.getType());
		assertEquals(youthAccount, transcaction.getPerformer());
		assertEquals(moneyToDeposit, transcaction.getAmount(), epsilon);
	}
	
	@Test
	public void testWithdrawOnOwner() {
		double monetToWithdraw = 666.66;
		moneyOnAccount -= monetToWithdraw;
		youthAccount.withdrawMoney(monetToWithdraw);
		assertEquals(moneyOnAccount, youthAccount.getMoney(), epsilon);
		assertEquals(1, youthAccount.getTransactionsHistory().size());
		
		transcaction = youthAccount.getTransactionsHistory().get(0);
		assertEquals(TransactionType.WITHDRAW, transcaction.getType());
		assertEquals(youthAccount, transcaction.getPerformer());
		assertEquals(monetToWithdraw, transcaction.getAmount(), epsilon);
	}
	
	@Test
	public void testTransferOnOwner() {
		double moneyToTransfer = 333.33;

		moneyOnAccount = 452.73;
		youthAccount.depositMoney(moneyOnAccount);
		moneyOnAccount -= moneyToTransfer;

		Account recipient = new PersonalAccount(owner);
		double moneyOnRecipient = 23.13;
		recipient.depositMoney(moneyOnRecipient);
		moneyOnRecipient += moneyToTransfer;
		
		youthAccount.transferMoney(recipient, moneyToTransfer, "test");
		assertEquals(moneyOnAccount, youthAccount.getMoney(), epsilon);
		assertEquals(moneyOnRecipient, recipient.getMoney(), epsilon);
		
		Transfer transfer = (Transfer) youthAccount.getTransactionsHistory().get(1);
		assertEquals(TransactionType.TRANSFER, transfer.getType());
		assertEquals(youthAccount, transfer.getPerformer());
		assertEquals(moneyToTransfer, transfer.getAmount(), epsilon);
		assertEquals(recipient, transfer.getRecipientIdNumber());
		
		Transfer recipientTranscaction = (Transfer) recipient.getTransactionsHistory().get(1);
		assertEquals(TransactionType.TRANSFER, recipientTranscaction.getType());
		assertEquals(youthAccount, recipientTranscaction.getPerformer());
		assertEquals(moneyToTransfer, recipientTranscaction.getAmount(), epsilon);
		assertEquals(recipient, recipientTranscaction.getRecipientIdNumber());
		
		assertEquals(transfer, recipientTranscaction);
	}
	
	@Test
	public void testDepositOnSupervisor() {
		double moneyToDeposit = 1000.63;
		moneyOnAccount += moneyToDeposit;
		youthAccount.depositMoney(moneyToDeposit);
		assertEquals(moneyOnAccount, youthAccount.getMoney(), epsilon);
		assertEquals(1, youthAccount.getTransactionsHistory().size());
		
		transcaction = youthAccount.getTransactionsHistory().get(0);
		assertEquals(TransactionType.DEPOSIT, transcaction.getType());
		assertEquals(youthAccount, transcaction.getPerformer());
		assertEquals(moneyToDeposit, transcaction.getAmount(), epsilon);
	}
}
