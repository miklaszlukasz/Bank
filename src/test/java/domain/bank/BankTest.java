package domain.bank;

import org.junit.Test;

import domain.bank.Bank;
import domain.userAccount.UserAccount;
import junit.framework.TestCase;

public class BankTest extends TestCase {

	@Test
	public void testAddAndGetUserAccount() {
		Bank bank = new Bank();
		String personalIdNumber = "76041019253";
		String password = "test";
		UserAccount userAccount = new UserAccount.Builder(personalIdNumber, password).firstName("Jan").lastName("Kowalski")
				.generateDateOfBirthFromIdNumber().build();
		bank.addNewUserAccount(userAccount);
		assertEquals(userAccount, bank.getUserAccount(personalIdNumber, password));
	}
}