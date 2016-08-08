package Bank;

import org.junit.Test;

import User.UserAccount;
import junit.framework.TestCase;

public class BankTest extends TestCase {

	@Test
	public void test() {
		Bank bank = new Bank();
		//bank.createNewUserAccount();
		UserAccount userAccount = bank.login("test", "test");
	}
}
