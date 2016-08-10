package domain.account.interfaces;

import java.math.BigDecimal;

import domain.account.Account;

public interface Transferable {
	public void transferMoney(Account recipient, BigDecimal amount, String comment);
	public void transferMoney(Account recipient, BigDecimal amount);
}
