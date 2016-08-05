package Account.Interfaces;

import java.math.BigDecimal;

import Account.Account;

public interface Transferable {
	public void transferMoney(Account recipient, BigDecimal amount, String comment);
	public void transferMoney(Account recipient, BigDecimal amount);
}
