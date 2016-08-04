package Transaction;

public class Withdraw extends Transaction {
	
	public Withdraw(String performingPersonId, long performingAccountId, double amount) {
		super(TransactionType.WITHDRAW, performingPersonId, performingAccountId, amount);
		comment = generateComment(); 
	}

	protected String generateComment() {
		return "Withdraw from account: " + amount;
	}
}