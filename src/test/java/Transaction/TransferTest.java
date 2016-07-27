package Transaction;

import static org.junit.Assert.*;

import org.junit.Test;

import Account.Account;
import Account.PersonalAccount;
import Account.User;

public class TransferTest {
	private double epsilon = 0.0;

	@Test
	public void testTransaction() {		
		User adresseeOwner = new User.Builder().build();
		User recipientOwner = new User.Builder().build();
		Account adressee = new PersonalAccount(adresseeOwner);
		Account recipient = new PersonalAccount(recipientOwner);
		double amount = 2000.35;
		String comment = "test";
		
		Transfer transaction = new Transfer(adresseeOwner, adressee, recipient, amount, comment);
		assertEquals(recipient, transaction.getRecipient());
		assertEquals(amount, transaction.getAmount(), epsilon);
		assertEquals(comment, transaction.getComment());		
	}
}
