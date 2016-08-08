package Transaction;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import junit.framework.TestCase;

public class TransferTest extends TestCase {
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
	public void testTransaction() {
		String adresseeUser = "76041019253";
		long adresseeIdNumber = 1234;
		long recipientIdNumber = 4321;
		BigDecimal amount = new BigDecimal(2000.35);
		String comment = "test";

		Transfer transaction = new Transfer(adresseeUser, adresseeIdNumber, recipientIdNumber, amount, comment);
		inputTransactionToDatabase(transaction);
		checkTransaction(transaction, recipientIdNumber, amount, comment);
		transaction = (Transfer) getTransactionFromDatabase();
		checkTransaction(transaction, recipientIdNumber, amount, comment);
	}

	private void inputTransactionToDatabase(Transaction transaction) {
		entityManager.getTransaction().begin();
		entityManager.persist(transaction);
		entityManager.getTransaction().commit();
	}

	private void checkTransaction(Transfer transaction, long recipientIdNumber, BigDecimal amount, String comment) {
		assertEquals(recipientIdNumber, transaction.getRecipientIdNumber());
		assertEquals(amount, transaction.getAmount());
		assertEquals(comment, transaction.getComment());
	}

	private Transaction getTransactionFromDatabase() {
		return entityManager.find(Transaction.class, 1);
	}
}
