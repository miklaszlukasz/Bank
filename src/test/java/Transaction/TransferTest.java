package Transaction;

import static org.junit.Assert.*;

import org.junit.Test;

import Account.Account;
import Account.PersonalAccount;

public class TransferTest {
	private double epsilon = 0.0;

	@Test
	public void testTransaction() {
		Account adressee = new PersonalAccount();
		Account recipient = new PersonalAccount();
		double amount = 2000.35;
		String comment = "test";
		
		Transfer transaction = new Transfer(adressee, recipient, amount, comment);
		assertEquals(recipient, transaction.getRecipient());
		assertEquals(amount, transaction.getAmount(), epsilon);
		assertEquals(comment, transaction.getComment());		
	}
}
