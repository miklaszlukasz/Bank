package domain.userAccount.interfaces;

import java.math.BigDecimal;

import domain.account.Account;

public interface Depositable {
	public void depositMoney(Account account, BigDecimal amount);
}
