package model;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * Class: Song
 * Author: Matthew Drake
 * 
 * This class reprents a song, with useful information
 * about each song object including the location where
 * the music file is, the artist and song name, as well
 * as the length of the song. It also handles the date, 
 * and gives a method for whether it can be played or not
 * anymore on this day. 
 */
public class Song implements Serializable
{
	private int timesPlayed;
	private int length;
	private String artistName, songTitle, songFile;
	private LocalDate lastDatePlayed;
	
	public Song(String string, int length, String artistName, String songTitle)
	{
		songFile = string;
		this.length = length;
		this.artistName = artistName;
		this.songTitle = songTitle;
		timesPlayed = 0;
		lastDatePlayed = LocalDate.now();
		
	}
	/*
	 * This method is used when the song is called on a different day
	 * When that happens, it sets the amount of times played on this current
	 * day to 0.
	 */
	private void resetDay()
	{
		timesPlayed = 0;
	}
	/*
	 * If the song has not been played 3 times already, then this
	 * method will return true. If the song has already been played 3
	 * or more (impossible) times, then it will return false.
	 */
	public boolean canPlay()
	{
		if(lastDatePlayed.getDayOfYear() != LocalDate.now().getDayOfYear())
		{
			resetDay();
		}
		return timesPlayed < 3;
	}
	/*
	 * This method is used only for testing, where it sets the previous
	 * date that this song has been played. It is used to test the reset 
	 * day method. Thing
	 */
	public void setDate(LocalDate newDate)
	{
		lastDatePlayed = newDate;
	}
	/*
	 * This method is also only used for testing, it tells you how many times
	 * a particular song has been played. In this way, once the day reset has happen
	 * it can tell you how many times a song has been played.
	 */
	public int getTimesPlayed()
	{
		return timesPlayed;
	}
	/*
	 * This method lets the current song object know that
	 * this song has been added to the playlist. It updates 
	 * the amount of times played, and if the day has changed
	 * since the last time this song has been played, it calls
	 * reset day and resets the day, as well as the lastDate.
	 */
	public void playSong()
	{
		if(lastDatePlayed.getDayOfYear() != LocalDate.now().getDayOfYear())
		{
			resetDay();
			lastDatePlayed = LocalDate.now();
		}
		timesPlayed++;
	}
	/*
	 * This method is a simple getter, it returns
	 * the length of the current song object in seconds
	 * as a double.
	 */
	public int getLength()
	{
		return length;
	}
	/*
	 * This method is a simple getter, it returns
	 * the location of the current song object 
	 * as a String.
	 */
	public String getSong()
	{
		return songFile;
	}
	/*
	 * This method is a simple getter, it returns
	 * the artist name of the current song object
	 * as a String.
	 */
	public String getArtistName()
	{
		return artistName;
	}
	/*
	 * This method is a simple getter, it returns
	 * the song title of the current song object
	 * as a String.
	 */
	public String getSongTitle()
	{
		return songTitle;
	}
	public String getFormattedLength()
	{
		int minutes = length/60;
		int seconds = length%60;
		
		String strSeconds = "";
		if(seconds/10 == 0)strSeconds = "0" + seconds;
		else strSeconds = "" + seconds;
		
		
		String retVal = minutes + ":" + strSeconds;
		return retVal;
	}
	
}
