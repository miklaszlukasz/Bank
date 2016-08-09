package Bank;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Account.Account;
import User.UserAccount;

public class Bank {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private UserAccount actuallyUsingUserAccount;

	public Bank() {

	}
	
	private void connect() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BankDatabase");
		entityManager = entityManagerFactory.createEntityManager();
	}

	private void disconnect() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	public void createNewUserAccount() {
		UserAccount userAccount = getDataToUserAccount();
		inputUserAccountToDatabase(userAccount);
	}
	
	private UserAccount getDataToUserAccount() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input your id number:");
		final String idNumber = scanner.nextLine();
		System.out.println("Input password:");
		final String password = scanner.nextLine();
		System.out.println("Input your first name: ");
		final String firstName = scanner.nextLine();
		System.out.println("Input your last name: ");
		final String lastName = scanner.nextLine();
		scanner.close();
		return new UserAccount.Builder(idNumber, password).firstName(firstName).lastName(lastName).generateDateOfBirthFromIdNumber().build();
	}
	
	private void inputUserAccountToDatabase(UserAccount userAccount) {
		connect();
		entityManager.getTransaction().begin();
		entityManager.persist(userAccount);
		entityManager.getTransaction().commit();
		disconnect();
	}

	public void login(String personalIdNumber, String password) {
		connect();
		Query query = getUserAccountFromDatabase(personalIdNumber, password);
		disconnect();
		actuallyUsingUserAccount = (UserAccount) query.getSingleResult();
	}

	private Query getUserAccountFromDatabase(String personalIdNumber, String password) {
		return entityManager.createQuery("FROM UserAccount as u WHERE u.personalIdNumber='" + personalIdNumber
				+ "' and WHERE u.passwd='" + password + "'");
	}

	public void logout() {
		actuallyUsingUserAccount = null;
	}

	public void printAllAccounts() {
		connect();
		TypedQuery<Account> query = getAccounts();
		disconnect();
		for (Account account : query.getResultList())
			System.out.println(account);
	}

	private TypedQuery<Account> getAccounts() {
		return entityManager.createQuery(
				"FROM Account as a WHERE a.personalIdNumber='" + actuallyUsingUserAccount.getIdNumber() + "'",
				Account.class);
	}
}
