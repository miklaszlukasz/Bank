package Transaction;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import junit.framework.TestCase;

public class DepositTest extends TestCase {
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
	public void testDepositAndAddToDatabase() {
		String ownerId = "76041019253";
		long accountNumerId = 1234;
		BigDecimal amount = new BigDecimal(2000.35);
		Transaction deposit = new Transaction(TransactionType.DEPOSIT, ownerId, accountNumerId, amount);
		inputDepostiToDatabase(deposit);
		assertEquals(amount, deposit.getAmount());

		deposit = getDepositFromDatabase();
		assertEquals(amount, deposit.getAmount());
	}
	
	private void inputDepostiToDatabase(Transaction deposit) {
		entityManager.getTransaction().begin();
		entityManager.persist(deposit);
		entityManager.getTransaction().commit();
	}
	
	private Transaction getDepositFromDatabase() {
		return entityManager.find(Transaction.class, 1);
	}
}