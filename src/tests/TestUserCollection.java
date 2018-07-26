package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.User;
import model.UserCollection;

public class TestUserCollection {

	@Test
	public void testContainsUser() {
		UserCollection uc = UserCollection.getInstance();
		User bob = new User("Bob","1");
		assertFalse(uc.containsUser("Bob"));
		uc.add(bob);
		assertTrue(uc.containsUser("Bob"));
	}
	@Test
	public void testGetUser() {
		UserCollection uc = UserCollection.getInstance();
		User bob = new User("Bob","1");
		uc.add(bob);
		//assertTrue(uc.getUser("Bob") == bob);
		assertTrue(uc.getUser("bill") == null);
	}

}
