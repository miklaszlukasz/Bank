package Transaction;

import static org.junit.Assert.*;

import org.junit.Test;

import Account.Account;
import Account.PersonalAccount;
import Account.User;

public class WithdrawTest {
	private double epsilon = 0.0;

	@Test
	public void test() {
		User owner = new User.Builder().build();
		double amount = 2000.35;
		Account account = new PersonalAccount(owner);
		Transaction withdraw = new Withdraw(owner, account, amount);

		assertEquals(amount, withdraw.getAmount(), epsilon);
	}
}
