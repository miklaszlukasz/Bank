package Account;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonalAccountTest {
	private double epsilon = 0.0;

	@Test
	public void test() {
		Account account = new PersonalAccount();
		double moneyOnAccount = 0.0;
		assertEquals(moneyOnAccount, account.getMoney(), epsilon);

		double moneyToDeposit = 1000.63;
		moneyOnAccount += moneyToDeposit;
		account.depositMoney(moneyToDeposit);
		assertEquals(moneyOnAccount, account.getMoney(), epsilon);

		double monetToWithdraw = 666.66;
		moneyOnAccount -= monetToWithdraw;
		account.withdrawMoney(monetToWithdraw);
		assertEquals(moneyOnAccount, account.getMoney(), epsilon);
	}
}
