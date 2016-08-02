package Account;

import Transaction.Freez;
import Transaction.Transaction;

public class SavingsAccount extends Account {

	public SavingsAccount(User owner) {
		super(owner, AccountType.SAVINGS_ACCOUNT);
	}

	public void freezMoney(double moneyToFreez) {
		Transaction freez = new Freez(owner, this, moneyToFreez);
		withdrawMoney(moneyToFreez, freez);	
	}

}
