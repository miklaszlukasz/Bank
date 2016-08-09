package Transaction;

import java.math.BigDecimal;

import org.junit.Test;

import junit.framework.TestCase;

public class WithdrawTest extends TestCase {

	@Test
	public void testWithdraw() {
		String ownerId = "76041019253";
		long accountNumerId = 1234;
		BigDecimal amount = new BigDecimal(2000.35);
		Transaction withdraw = new Transaction(TransactionType.WITHDRAW, ownerId, accountNumerId, amount);
		assertEquals(amount, withdraw.getAmount());
	}
}
