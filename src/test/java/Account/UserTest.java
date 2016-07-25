package Account;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class UserTest {

	@Test
	public void testUser0() throws ParseException {
		String firstName= "Jan";
		String lastName= "Kowalski";
		String idNumber= "76041019253";
		//User user= new User(firstName, lastName, idNumber);
		User user = new User.Builder()
				.firstName(firstName)
				.lastName(lastName)
				.idNumber(idNumber)
				.generateDateOfBirthFromIdNumber(idNumber)
				.build();
		assertEquals(firstName, user.getFirstName());
		assertEquals(lastName, user.getLastName());
		assertEquals(idNumber, user.getIdNumber());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(formatter.parse("10/04/1976"), user.getDateOfBirth());
	}
	
	@Test
	public void testUser1() throws ParseException {
		String firstName= "Wiola";
		String lastName= "Nowak";
		String idNumber= "90920606886";
		//User user= new User(firstName, lastName, idNumber);
		User user = new User.Builder()
				.firstName(firstName)
				.lastName(lastName)
				.idNumber(idNumber)
				.generateDateOfBirthFromIdNumber(idNumber)
				.build();
		assertEquals(firstName, user.getFirstName());
		assertEquals(lastName, user.getLastName());
		assertEquals(idNumber, user.getIdNumber());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(formatter.parse("06/12/1890"), user.getDateOfBirth());
	}
	
	@Test
	public void testUser2() throws ParseException {
		String firstName= "Ewa";
		String lastName= "Rumcajs";
		String idNumber= "90320606886";
		//User user= new User(firstName, lastName, idNumber);
		User user = new User.Builder()
				.firstName(firstName)
				.lastName(lastName)
				.idNumber(idNumber)
				.generateDateOfBirthFromIdNumber(idNumber)
				.build();
		assertEquals(firstName, user.getFirstName());
		assertEquals(lastName, user.getLastName());
		assertEquals(idNumber, user.getIdNumber());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(formatter.parse("06/12/2090"), user.getDateOfBirth());
	}
}
