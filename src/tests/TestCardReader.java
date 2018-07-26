package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.JukeBox;
import model.CardReader;
import model.SongCollection;
import model.User;
import model.UserCollection;

public class TestCardReader {

	@Test
	public void testHasEnough() {
		User testUser = new User("b","1");
		UserCollection uc = UserCollection.getInstance();
		SongCollection sc = SongCollection.getInstance();
		CardReader cr = new CardReader();
		uc.add(testUser);
		
		assertTrue(cr.hasEnough(testUser,sc.getSong(0)));
		testUser.seconds = 0.0;
		assertFalse(cr.hasEnough(testUser, sc.getSong(0)));
	}
	
	@Test
	public void testValidate() {
		CardReader cr = new CardReader();		
		assertTrue(cr.validate("Chris","1"));
		assertFalse(cr.validate("Chris","11"));
	}
	
	@Test
	public void testGetCurrentUser() {
		CardReader cr = new CardReader();		
		assertTrue(cr.getCurrentUser() == null);
	}
	
	@Test
	public void testGetTime() {
		CardReader cr = new CardReader();		
		assertTrue(cr.validate("Chris","1"));
		assertTrue(cr.getTime().equals("1500.0"));
	}
	
	

}
