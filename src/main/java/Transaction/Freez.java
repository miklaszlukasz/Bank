package Transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Account.Account;
import Account.User;

public class Freez extends Transaction {
	private final double interest = 3;
	private Date ratesDate;
	public Freez(User performingPerson, Account performer, double amount) {
		super(TransactionType.FREEZ, performingPerson, performer, amount);
		ratesDate = generateRatesDate();
		comment = generateComment();
	}
	
	private Date generateRatesDate() {
		Calendar ratesDate = Calendar.getInstance();
		ratesDate.setTime(executionDate);
		ratesDate.add(Calendar.MONTH, 3);
		return ratesDate.getTime();
	}

	protected String generateComment() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
		return "Freez " + amount + " to " + simpleDateFormat.format(ratesDate) + " on interest " + interest +"%";
	}
}
