package model;

import java.io.Serializable;
import java.time.LocalDate;

/*-----------------------------------------------
 * Class: User
 * Author: Alex Starks
 * 
 * Purpose: This class is a user object that contains the
 * information of a JukeBox user 
 * -----------------------------------------------*/
public class User implements Serializable {

	// instance variables
	public String name; // name of user
	private String password; // password of user
	public Double seconds; // seconds of play left
	private int songsPlayed;//amount of songs played in a day
	private LocalDate lastDatePlayed;//date of the last time a song was played by this student

	/*------------------------------
	 * Constructor: User()
	 * Purpose: This initializes the
	 * instance variables 
	 * ---------------------------*/
	public User(String name, String pass) {
		this.name = name;
		this.password = pass;
		this.seconds = 1500.0 * 60.0;//default value
		songsPlayed = 0;
		lastDatePlayed = LocalDate.now();
	}

	/*----------------------------------
	 * Method: passwordIs(String)
	 * Purpose: This method returns true if the
	 * string passed into the method is equal to 
	 * instance variable password
	 * ----------------------------------*/
	public boolean passwordIs(String pass) {
		if (pass.equals(this.password))
			return true;
		return false;
	}
	// getters
	public String getName() { return name; }
	public String getMinutes() { 
		Double min = seconds/60;
		return min.toString(); }
	public Double secondsLeft() { return seconds; }



	/*
	 * This method returns whether a student is allowed to play anymore songs
	 * today. It takes no arguments, and returns a boolean value.
	 */
	public boolean isAllowedToPlaySong()
	{ 
		if(lastDatePlayed.getDayOfYear() != LocalDate.now().getDayOfYear())
		{
			resetDay();
		}
		return songsPlayed < 3; 
	}
	/*
	 * This class increments how many songs have been played. If the last time a student
	 * played a song was not the same day, this resets his counter. Thing
	 */
	public void playedSong()
	{
		if(lastDatePlayed.getDayOfYear() != LocalDate.now().getDayOfYear())
		{
			resetDay();
			lastDatePlayed = LocalDate.now();
		}
		songsPlayed++;
	}
	/*
	 * When the student plays on a different day, they get to start over
	 */
	private void resetDay(){ songsPlayed = 0; }
	// getter
	public int getSongsPlayed() { return songsPlayed; }
	// setter
	public void setLastDatePlayed(LocalDate ld) { lastDatePlayed = ld; }
}
