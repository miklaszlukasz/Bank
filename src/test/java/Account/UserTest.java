package Account;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class UserTest {

	@Test
	public void testUser() throws ParseException {
		String firstName= "Jan";
		String lastName= "Kowalski";
		String idNumber= "76041019253";
		User user= new User(firstName, lastName, idNumber);
		assertEquals(firstName, user.getFirstName());
		assertEquals(lastName, user.getLastName());
		assertEquals(idNumber, user.getIdNumber());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(formatter.parse("10/04/1976"), user.getDateOfBirth());
	}
}
