package Transaction;

public class Deposit extends Transaction {

	public Deposit(String performingPersonId, long performingAccountId, double amount) {
		super(TransactionType.DEPOSIT, performingPersonId, performingAccountId, amount);
		comment = generateComment();
	}

	protected String generateComment() {
		return "Deposit to account: " + amount;
	}
}