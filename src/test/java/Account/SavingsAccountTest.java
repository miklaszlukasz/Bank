package Account;

import org.junit.Test;

import Transaction.Transaction;
import Transaction.TransactionType;
import Transaction.Transfer;
import junit.framework.TestCase;

public class SavingsAccountTest extends TestCase {
	private final double epsilon = 0.0;
	private User owner;
	private SavingsAccount savingsAccount;
	private double moneyOnAccount;
	private Transaction transcaction;

	public void setUp() {
		owner = new User.Builder().build();
		savingsAccount = new SavingsAccount(owner);
		assertEquals(moneyOnAccount, savingsAccount.getMoney(), epsilon);
	}

	@Test
	public void testDeposit() {
		final double moneyToDeposit = 1000.63;
		moneyOnAccount += moneyToDeposit;
		savingsAccount.depositMoney(moneyToDeposit);
		assertEquals(moneyOnAccount, savingsAccount.getMoney(), epsilon);
		assertEquals(1, savingsAccount.getTransactionsHistory().size());

		transcaction = savingsAccount.getTransactionsHistory().get(0);
		assertEquals(TransactionType.DEPOSIT, transcaction.getType());
		assertEquals(savingsAccount, transcaction.getPerformer());
		assertEquals(moneyToDeposit, transcaction.getAmount(), epsilon);

	}

	@Test
	public void testWithdraw() {
		final double monetToWithdraw = 666.66;
		moneyOnAccount -= monetToWithdraw;
		savingsAccount.withdrawMoney(monetToWithdraw);
		assertEquals(moneyOnAccount, savingsAccount.getMoney(), epsilon);
		assertEquals(1, savingsAccount.getTransactionsHistory().size());

		transcaction = savingsAccount.getTransactionsHistory().get(0);
		assertEquals(TransactionType.WITHDRAW, transcaction.getType());
		assertEquals(savingsAccount, transcaction.getPerformer());
		assertEquals(monetToWithdraw, transcaction.getAmount(), epsilon);
	}

	@Test
	public void testTransfer() {
		final double moneyToTransfer = 333.33;

		moneyOnAccount = 452.73;
		savingsAccount.depositMoney(moneyOnAccount);
		moneyOnAccount -= moneyToTransfer;

		Account recipient = new PersonalAccount(owner);
		double moneyOnRecipient = 23.13;
		recipient.depositMoney(moneyOnRecipient);
		moneyOnRecipient += moneyToTransfer;

		savingsAccount.transferMoney(recipient, moneyToTransfer, "test");
		assertEquals(moneyOnAccount, savingsAccount.getMoney(), epsilon);
		assertEquals(moneyOnRecipient, recipient.getMoney(), epsilon);

		Transfer transfer = (Transfer) savingsAccount.getTransactionsHistory().get(1);
		assertEquals(TransactionType.TRANSFER, transfer.getType());
		assertEquals(savingsAccount, transfer.getPerformer());
		assertEquals(moneyToTransfer, transfer.getAmount(), epsilon);
		assertEquals(recipient, transfer.getRecipient());

		Transfer recipientTranscaction = (Transfer) recipient.getTransactionsHistory().get(1);
		assertEquals(TransactionType.TRANSFER, recipientTranscaction.getType());
		assertEquals(savingsAccount, recipientTranscaction.getPerformer());
		assertEquals(moneyToTransfer, recipientTranscaction.getAmount(), epsilon);
		assertEquals(recipient, recipientTranscaction.getRecipient());

		assertEquals(transfer, recipientTranscaction);
	}

	@Test
	public void testFreezMoney() {
		final double moneyToFreez = 213.43;
		moneyOnAccount = 666.66;
		savingsAccount.depositMoney(moneyOnAccount);
		moneyOnAccount -= moneyToFreez;
		savingsAccount.freezMoney(moneyToFreez);
		assertEquals(moneyOnAccount, savingsAccount.getMoney());
	}
}
