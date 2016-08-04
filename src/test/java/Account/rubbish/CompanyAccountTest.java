package Account.rubbish;

import org.junit.Test;

import Account.Account;
import Account.User;
import Account.User.Builder;
import Account.rubbish.CompanyAccount;
import Account.rubbish.PersonalAccount;
import Transaction.Transaction;
import Transaction.TransactionType;
import Transaction.Transfer;
import junit.framework.TestCase;

public class CompanyAccountTest extends TestCase {
	private final double epsilon = 0.0;
	private User owner;
	private CompanyAccount companyAccount;
	private double moneyOnAccount;
	private Transaction transcaction;

	public void setUp() {
		owner = new User.Builder().build();
		User user1 = new User.Builder().build();
		User user2 = new User.Builder().build();
		companyAccount = new CompanyAccount(owner);
		companyAccount.addUser(user1);
		companyAccount.addUser(user2);
		assertEquals(moneyOnAccount, companyAccount.getMoney(), epsilon);
	}

	@Test
	public void testDeposit() {
		double moneyToDeposit = 1000.63;
		moneyOnAccount += moneyToDeposit;
		companyAccount.depositMoney(moneyToDeposit);
		assertEquals(moneyOnAccount, companyAccount.getMoney(), epsilon);
		assertEquals(1, companyAccount.getTransactionsHistory().size());

		transcaction = companyAccount.getTransactionsHistory().get(0);
		assertEquals(TransactionType.DEPOSIT, transcaction.getType());
		assertEquals(companyAccount, transcaction.getPerformer());
		assertEquals(moneyToDeposit, transcaction.getAmount(), epsilon);

	}

	@Test
	public void testWithdraw() {
		double monetToWithdraw = 666.66;
		moneyOnAccount -= monetToWithdraw;
		companyAccount.withdrawMoney(monetToWithdraw);
		assertEquals(moneyOnAccount, companyAccount.getMoney(), epsilon);
		assertEquals(1, companyAccount.getTransactionsHistory().size());

		transcaction = companyAccount.getTransactionsHistory().get(0);
		assertEquals(TransactionType.WITHDRAW, transcaction.getType());
		assertEquals(companyAccount, transcaction.getPerformer());
		assertEquals(monetToWithdraw, transcaction.getAmount(), epsilon);
	}

	@Test
	public void testTransfer() {
		double moneyToTransfer = 333.33;

		moneyOnAccount = 452.73;
		companyAccount.depositMoney(moneyOnAccount);
		moneyOnAccount -= moneyToTransfer;

		Account recipient = new PersonalAccount(owner);
		double moneyOnRecipient = 23.13;
		recipient.depositMoney(moneyOnRecipient);
		moneyOnRecipient += moneyToTransfer;

		companyAccount.transferMoney(recipient, moneyToTransfer, "test");
		assertEquals(moneyOnAccount, companyAccount.getMoney(), epsilon);
		assertEquals(moneyOnRecipient, recipient.getMoney(), epsilon);

		Transfer transfer = (Transfer) companyAccount.getTransactionsHistory().get(1);
		assertEquals(TransactionType.TRANSFER, transfer.getType());
		assertEquals(companyAccount, transfer.getPerformer());
		assertEquals(moneyToTransfer, transfer.getAmount(), epsilon);
		assertEquals(recipient, transfer.getRecipientIdNumber());

		Transfer recipientTranscaction = (Transfer) recipient.getTransactionsHistory().get(1);
		assertEquals(TransactionType.TRANSFER, recipientTranscaction.getType());
		assertEquals(companyAccount, recipientTranscaction.getPerformer());
		assertEquals(moneyToTransfer, recipientTranscaction.getAmount(), epsilon);
		assertEquals(recipient, recipientTranscaction.getRecipientIdNumber());

		assertEquals(transfer, recipientTranscaction);
	}
}
