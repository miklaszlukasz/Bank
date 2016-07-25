package Transaction;

import static org.junit.Assert.*;

import org.junit.Test;

import Account.Account;
import Account.PersonalAccount;

public class DepositTest {
	private double epsilon = 0.0;

	@Test
	public void test() {
		double amount = 2000.35;
		Account account = new PersonalAccount();
		Transaction deposit = new Deposit(account, amount);

		assertEquals(amount, deposit.getAmount(), epsilon);
	}

}
