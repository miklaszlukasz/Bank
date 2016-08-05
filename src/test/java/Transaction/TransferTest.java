package Transaction;

import java.math.BigDecimal;

import org.junit.Test;

import Account.Account;
import Account.User;
import junit.framework.TestCase;

public class TransferTest extends TestCase {
	private User user1;
	private User user2;

	public void setUp() {
		String randomPersonIdNumber1 = "76041019253";
		String password1 = "test";
		user1 = new User.Builder(randomPersonIdNumber1, password1).firstName("Jan").lastName("Kowalski")
				.generateDateOfBirthFromIdNumber().build();
		
		String password2 = "tset";
		String randomPersonIdNumber2 = "78020715770";
		user2 = new User.Builder(randomPersonIdNumber2, password2).firstName("Witold").lastName("Stonoga")
				.generateDateOfBirthFromIdNumber().build();
	}

	@Test
	public void testTransaction() {		
		Account adressee = new Account(user1);
		Account recipient = new Account(user2);
		BigDecimal amount = new BigDecimal(2000.35);
		String comment = "test";
		
		Transfer transaction = new Transfer(user1.getIdNumber(), adressee.getIdNumber(), recipient.getIdNumber(), amount, comment);
		assertEquals(recipient.getIdNumber(), transaction.getRecipientIdNumber());
		assertEquals(amount, transaction.getAmount());
		assertEquals(comment, transaction.getComment());		
	}
}
