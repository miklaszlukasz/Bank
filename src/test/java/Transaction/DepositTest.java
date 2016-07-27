package Transaction;

import static org.junit.Assert.*;

import org.junit.Test;

import Account.Account;
import Account.PersonalAccount;
import Account.User;

public class DepositTest {
	private double epsilon = 0.0;

	@Test
	public void test() {
		double amount = 2000.35;
		User owner = new User.Builder().build();
		Account account = new PersonalAccount(owner);
		Transaction deposit = new Deposit(owner, account, amount);

		assertEquals(amount, deposit.getAmount(), epsilon);
	}
}
