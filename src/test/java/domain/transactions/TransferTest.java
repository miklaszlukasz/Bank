package domain.transactions;

import java.math.BigDecimal;

import org.junit.Test;

import domain.account.Account;
import domain.transaction.Transfer;
import domain.userAccount.UserAccount;
import junit.framework.TestCase;

public class TransferTest extends TestCase {

	@Test
	public void testTransaction() {
		String ownerId = "76041019253";
		String password = "test";
		UserAccount userAccount = new UserAccount.Builder(ownerId, password).build();
		Account adressee = new Account(userAccount);
		Account recipient = new Account(userAccount);
		BigDecimal amount = new BigDecimal(2000.35);
		String comment = "test";

		Transfer transaction = new Transfer(adressee, recipient, amount, comment);
		checkTransaction(transaction, adressee, recipient, amount, comment);
	}

	private void checkTransaction(Transfer transaction, Account performer, Account recipient, BigDecimal amount, String comment) {
		assertEquals(performer, transaction.getPerformingAccount());
		assertEquals(recipient, transaction.getRecipientAccount());
		assertEquals(amount, transaction.getAmount());
		assertEquals(comment, transaction.getComment());
	}
}