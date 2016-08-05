package Transaction;

import java.math.BigDecimal;

public class Withdraw extends Transaction {
	
	public Withdraw(String performingPersonId, long performingAccountId, BigDecimal amount) {
		super(TransactionType.WITHDRAW, performingPersonId, performingAccountId, amount);
		comment = generateComment(); 
	}

	protected String generateComment() {
		return "Withdraw from account: " + amount;
	}
}