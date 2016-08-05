package Transaction;

import java.math.BigDecimal;

import org.junit.Test;

import junit.framework.TestCase;

public class TransferTest extends TestCase {
	private String adresseeUser;


	public void setUp() {
		adresseeUser = "76041019253";
	}

	@Test
	public void testTransaction() {		
		long adresseeIdNumber = 1234;
		long recipientIdNumber = 4321;
		BigDecimal amount = new BigDecimal(2000.35);
		String comment = "test";
		
		Transfer transaction = new Transfer(adresseeUser, adresseeIdNumber, recipientIdNumber, amount, comment);
		assertEquals(recipientIdNumber, transaction.getRecipientIdNumber());
		assertEquals(amount, transaction.getAmount());
		assertEquals(comment, transaction.getComment());		
	}
}
