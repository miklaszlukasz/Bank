package Transaction;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import junit.framework.TestCase;

public class WithdrawTest extends TestCase {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("TestBankDatabase");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public void tearDown() throws Exception {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void testWithdrawAndAddToDatabase() {
		String ownerId = "76041019253";
		long accountNumerId = 1234;
		BigDecimal amount = new BigDecimal(2000.35);
		Transaction withdraw = new Transaction(TransactionType.WITHDRAW, ownerId, accountNumerId, amount);
		inputWithdrawToDatabase(withdraw);
		assertEquals(amount, withdraw.getAmount());
		withdraw = getWithdrawFromDatabase();
		assertEquals(amount, withdraw.getAmount());
	}
	
	private void inputWithdrawToDatabase(Transaction withdraw) {
		entityManager.getTransaction().begin();
		entityManager.persist(withdraw);
		entityManager.getTransaction().commit();
	}
	
	private Transaction getWithdrawFromDatabase() {
		return entityManager.find(Transaction.class, 1);
	}
}
