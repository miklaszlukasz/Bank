package User;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import junit.framework.TestCase;

public class UserAccountTest extends TestCase {
	private UserAccount user;

	public void setUp() {
		String randomPersonIdNumber = "76041019253";
		String password = "test";
		user = new UserAccount.Builder(randomPersonIdNumber, password).firstName("Jan").lastName("Kowalski")
				.generateDateOfBirthFromIdNumber().build();
	}

	@Test
	public void testConstructor() throws ParseException {
		assertEquals("76041019253", user.getIdNumber());
		assertEquals("test", user.getPassword());
		assertEquals("Jan", user.getFirstName());
		assertEquals("Kowalski", user.getLastName());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(formatter.parse("10/04/1976"), user.getDateOfBirth());
	}
}
