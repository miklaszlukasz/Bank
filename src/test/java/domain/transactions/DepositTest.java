package domain.transactions;

import java.math.BigDecimal;

import org.junit.Test;

import domain.transaction.Transaction;
import domain.transaction.TransactionType;
import junit.framework.TestCase;

public class DepositTest extends TestCase {

	@Test
	public void testDeposit() {
		String ownerId = "76041019253";
		long accountNumerId = 1234;
		BigDecimal amount = new BigDecimal(2000.35);
		Transaction deposit = new Transaction(TransactionType.DEPOSIT, ownerId, accountNumerId, amount);
		assertEquals(amount, deposit.getAmount());
	}
}