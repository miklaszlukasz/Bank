package Transaction;

import static org.junit.Assert.*;

import org.junit.Test;

import Account.Account;
import Account.PersonalAccount;

public class WithdrawTest {
	private double epsilon = 0.0;

	@Test
	public void test() {
		double amount = 2000.35;
		Account account = new PersonalAccount();
		Transaction withdraw = new Withdraw(account, amount);

		assertEquals(amount, withdraw.getAmount(), epsilon);
	}
}
