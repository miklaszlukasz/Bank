package Transaction;

import java.math.BigDecimal;

import org.junit.Test;

import junit.framework.TestCase;

public class DepositTest extends TestCase {

	@Test
	public void testDepositAndAddToDatabase() {
		String ownerId = "76041019253";
		long accountNumerId = 1234;
		BigDecimal amount = new BigDecimal(2000.35);
		Transaction deposit = new Transaction(TransactionType.DEPOSIT, ownerId, accountNumerId, amount);
		assertEquals(amount, deposit.getAmount());
	}
}