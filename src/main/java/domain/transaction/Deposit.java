package domain.transaction;

import java.math.BigDecimal;

import domain.account.Account;

public class Deposit extends Transaction {

	public Deposit(Account performingAccound, BigDecimal amount) {
		super(performingAccound, amount);
	}
}