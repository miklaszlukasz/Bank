package Transaction;

import org.junit.Test;

import Account.Account;
import Account.User;
import junit.framework.TestCase;

public class WithdrawTest extends TestCase {
	private final double epsilon = 0.0;
	private User user;

	public void setUp() {
		String randomPersonIdNumber = "76041019253";
		String password = "test";
		user = new User.Builder(randomPersonIdNumber, password).firstName("Jan").lastName("Kowalski")
				.generateDateOfBirthFromIdNumber().build();
	}

	@Test
	public void test() {
		double amount = 2000.35;
		Account account = new Account(user);
		Transaction withdraw = new Withdraw(user.getIdNumber(), account.getIdNumber(), amount);
		assertEquals(amount, withdraw.getAmount(), epsilon);
	}
}
