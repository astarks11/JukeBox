package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/*------------------------------------------------
 * Class: UserCollection
 * Author: Alex Starks
 * 
 * Purpose: This class utilizes a List of User objects. 
 * -----------------------------------------------*/
public class UserCollection implements Serializable {
	
	private List<User> userList; // collection of users
	private static UserCollection userCollection;
	
	
	/*---------------------------------
	 * Constructor: UserCollection()
	 * Purpose: To initialize the userList
	 * ------------------------------*/
	private UserCollection() {
		userList = new LinkedList<User>();
	}
	
	public static UserCollection getInstance()
	{
		if(userCollection == null)
		{
			userCollection = new UserCollection();
		}
		return userCollection;
	}
	/*-----------------------------------
	 * Method: containsUser()
	 * Purpose: returns true if the list contains a user
	 * with the name that is passed as an argument. Otherwise
	 * false is returned
	 * ---------------------------------*/
	public boolean containsUser(String name) {
		for (User usr : userList){
			if (usr.getName().equals(name))
				return true;
		}
		return false;
	}
	
	
	
	// setters
	public void add(User user) { userList.add(user); }
	
	// getters
	public User getUser(String name) {
		for (User usr : userList){
			if (usr.getName().equals(name))
				return usr;
		}
		return null;
	}
	
	
}
