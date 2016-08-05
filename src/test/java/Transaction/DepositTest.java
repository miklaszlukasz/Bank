package Transaction;

import java.math.BigDecimal;

import org.junit.Test;

import junit.framework.TestCase;

public class DepositTest extends TestCase {
	private String ownerId;

	public void setUp() {
		ownerId = "76041019253";
	}

	@Test
	public void test() {
		BigDecimal amount = new BigDecimal(2000.35);
		long accountNumerId = 1234;
		Transaction deposit = new Deposit(ownerId, accountNumerId, amount);
		assertEquals(amount, deposit.getAmount());
	}
}