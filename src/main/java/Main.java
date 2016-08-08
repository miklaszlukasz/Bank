import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BankDatabase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.close();
		entityManagerFactory.close();
	}

}
