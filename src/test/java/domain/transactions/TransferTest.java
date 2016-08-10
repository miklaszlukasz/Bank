package domain.transactions;

import java.math.BigDecimal;

import org.junit.Test;

import domain.transaction.Transfer;
import junit.framework.TestCase;

public class TransferTest extends TestCase {

	@Test
	public void testTransaction() {
		String adresseeUser = "76041019253";
		long adresseeIdNumber = 1234;
		long recipientIdNumber = 4321;
		BigDecimal amount = new BigDecimal(2000.35);
		String comment = "test";

		Transfer transaction = new Transfer(adresseeUser, adresseeIdNumber, recipientIdNumber, amount, comment);
		checkTransaction(transaction, recipientIdNumber, amount, comment);
	}

	private void checkTransaction(Transfer transaction, long recipientIdNumber, BigDecimal amount, String comment) {
		assertEquals(recipientIdNumber, transaction.getRecipientIdNumber());
		assertEquals(amount, transaction.getAmount());
		assertEquals(comment, transaction.getComment());
	}

}
