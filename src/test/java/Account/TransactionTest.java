package Account;

import static org.junit.Assert.*;

import org.junit.Test;

public class TransactionTest {
	private double epsilon = 0.0;

	@Test
	public void testTransaction() {
		Account adressee = new Account();
		Account recipient = new Account();
		double amount = 2000.35;
		String comment = "test";
		
		Transaction transaction = new Transaction(adressee, recipient, amount, comment);
		assertEquals(adressee, transaction.getAdressee());
		assertEquals(recipient, transaction.getRecipient());
		assertEquals(amount, transaction.getAmount(), epsilon);
		assertEquals(comment, transaction.getComment());		
	}

}
