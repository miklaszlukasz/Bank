package Account;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest {
	private double epsilon = 0.0;

	@Test
	public void testDepositAndWithdrawMoney() {
		Account account = new Account();
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

	@Test
	public void testMakeTransaction() {
		double moneyToTransaction = 333.33;

		Account adressee = new Account();
		double moneyOnAdressee = 452.73;
		adressee.depositMoney(moneyOnAdressee);
		moneyOnAdressee -= moneyToTransaction;

		Account recipient = new Account();
		double moneyOnRecipient = 23.13;
		recipient.depositMoney(moneyOnRecipient);
		moneyOnRecipient += moneyToTransaction;
		
		adressee.transferMoney(recipient, moneyToTransaction, "test");
		assertEquals(moneyOnAdressee, adressee.getMoney(), epsilon);
		assertEquals(moneyOnRecipient, recipient.getMoney(), epsilon);
	}
}
