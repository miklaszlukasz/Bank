package domain.transaction;

import java.math.BigDecimal;

import domain.account.Account;

public class Withdraw extends Transaction {
	public Withdraw(Account performingAccound, BigDecimal amount) {
		super(performingAccound, amount);
	}
}
