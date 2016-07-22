package Account;

import static org.junit.Assert.*;

import org.junit.Test;

import Transaction.Transfer;

public class TransactionTest {
	private double epsilon = 0.0;

	@Test
	public void testTransaction() {
		Account adressee = new Account();
		Account recipient = new Account();
		double amount = 2000.35;
		String comment = "test";
		
		Transfer transaction = new Transfer(adressee, recipient, amount, comment);
		//assertEquals(adressee, transaction.getAdressee());
		assertEquals(recipient, transaction.getRecipient());
		assertEquals(amount, transaction.getAmount(), epsilon);
		assertEquals(comment, transaction.getComment());		
	}

}
