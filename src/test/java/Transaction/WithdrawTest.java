package Transaction;

import java.math.BigDecimal;

import org.junit.Test;

import Account.Account;
import Account.User;
import junit.framework.TestCase;

public class WithdrawTest extends TestCase {
	private User user;

	public void setUp() {
		String randomPersonIdNumber = "76041019253";
		String password = "test";
		user = new User.Builder(randomPersonIdNumber, password).firstName("Jan").lastName("Kowalski")
				.generateDateOfBirthFromIdNumber().build();
	}

	@Test
	public void test() {
		BigDecimal amount = new BigDecimal(2000.35);
		Account account = new Account(user);
		Transaction withdraw = new Withdraw(user.getIdNumber(), account.getIdNumber(), amount);
		assertEquals(amount, withdraw.getAmount());
	}
}
