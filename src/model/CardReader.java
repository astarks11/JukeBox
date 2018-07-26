package model;

import java.io.Serializable;

/*--------------------------------------------------------------
 * Class: CardReader
 * Author: Alex Starks
 * 
 * Purpose: This class is the cardReader object that can determine information
 * about the User's Card
 * ------------------------------------------------------------*/
public class CardReader implements Serializable
{
	
	UserCollection userCollection; // the list of users
	User currentUser; // current user
	public CardReader()
	{
		userCollection = UserCollection.getInstance();
		
		// -------------- hard code users -----------------
		User user1 = new User("Chris","1");
		User user2 = new User("Devon","22");
		User user3 = new User("River","333");
		User user4 = new User("Ryan","4444");
		userCollection.add(user1);
		userCollection.add(user2);
		userCollection.add(user3);
		userCollection.add(user4);
		// -------------- hard code users -----------------
		
		currentUser = null;

	}
	
	/*-------------------------------------------
	 * Method: hasEnough
	 * Purpose: returns true if the usr's secondsLeft
	 * is greater than the song length
	 * -----------------------------------------*/
	public boolean hasEnough(User usr, Song song)
	{
		if (usr.secondsLeft() > song.getLength())
			return true;
		return false;
	}	
	
	/* ---------------------------------------------
	 * Method: validate()
	 * Purpose: returns true if the user is in the
	 * userCollection
	 * -----------------------------------------*/
	public boolean validate(String name, String pass) {
		// check if user is in collection
		if (userCollection.containsUser(name)) {
			// check password
			User usr = userCollection.getUser(name);
			if (usr.passwordIs(pass)) {
				currentUser = usr;
				return true;
			}
		}
		currentUser = null;
		return false;
	}
	
	// getter
	public User getCurrentUser() { return currentUser; }
	public void setCurrentUser(User newUser) { currentUser = newUser; }
	public String getTime() { return currentUser.getMinutes(); }
}
