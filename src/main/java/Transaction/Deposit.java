package Transaction;

import java.math.BigDecimal;

public class Deposit extends Transaction {

	public Deposit(String performingPersonId, long performingAccountId, BigDecimal amount) {
		super(TransactionType.DEPOSIT, performingPersonId, performingAccountId, amount);
		comment = generateComment();
	}

	protected String generateComment() {
		return "Deposit to account: " + amount;
	}
}