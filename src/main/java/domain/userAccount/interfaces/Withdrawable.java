package domain.userAccount.interfaces;

import java.math.BigDecimal;

import domain.account.Account;

public interface Withdrawable {
	public void withdrawMoney(Account account, BigDecimal amount);
}
