package Bank;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;

import User.UserAccount;

public class Bank {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	@OneToMany()
	private List<UserAccount> userAccounts;

	public Bank() {

		userAccounts = new ArrayList<UserAccount>();
	}

	public void createNewUserAccount() {
		// TODO Auto-generated method stub
	}

	public UserAccount login(String personalIdNumber, String password) {
		connect();
		Query query = getUserAccountFromDatabase(personalIdNumber, password);
		disconnect();
		return (UserAccount) query.getSingleResult();
	}

	private Query getUserAccountFromDatabase(String personalIdNumber, String password) {
		return entityManager.createQuery("FROM UserAccount as u WHERE u.personalIdNumber='" + personalIdNumber
				+ "' and WHERE u.passwd='" + password + "'");
	}
	
	
	private void connect() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BankDatabase");
		entityManager = entityManagerFactory.createEntityManager();
	}

	private void disconnect() {
		entityManager.close();
		entityManagerFactory.close();
	}
}
