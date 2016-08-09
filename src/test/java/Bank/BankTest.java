package Bank;

import org.junit.Test;

import junit.framework.TestCase;

public class BankTest extends TestCase {

	@Test
	public void test() {
		Bank bank = new Bank();
		bank.login("test", "test");
		bank.printAllAccounts();
		bank.logout();
	}
}
