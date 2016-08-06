package Transaction;

import java.math.BigDecimal;

import org.junit.Test;

import junit.framework.TestCase;

public class WithdrawTest extends TestCase {
	private String ownerId;

	public void setUp() {
		ownerId = "76041019253";
	}

	@Test
	public void test() {
		BigDecimal amount = new BigDecimal(2000.35);
		long accountNumerId = 1234;
		Transaction withdraw = new Transaction(TransactionType.WITHDRAW, ownerId, accountNumerId, amount);
		assertEquals(amount, withdraw.getAmount());
	}
}
