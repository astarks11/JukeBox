package songPlayerDemo;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Class: Play1Song
 * Author: Rick Mercer (adapted for this project by Matthew Drake)
 * 
 * This class used just play one song, now it handles the entire playing
 * of a playlist. Once a song finishes, it will play the next
 * song if it is possible for that to occur. 
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import Views.SongListUI;
import model.Song;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
/**
 * Play one audio file from the songfiles directory.
 * There is no listener for the end of song event.
 * 
 * @author Rick Mercer
 */
import songplayer.SongPlayer;

public class Play1Song extends Observable implements Serializable, ListModel
{

	/**
	 * Play one audio file with a listener for the end of song event. A println
	 * is included to indicate the song is playing in a separate Thread.
	 */
	private boolean songPlaying;
	private List<String> playList;
	private Queue<String> songs;
	private EndOfSongListener waitForSongEnd;

	public Play1Song() 
	{
		songs = new LinkedList<String>();
		songPlaying = false;
		waitForSongEnd = new WaitingForSongToEnd();
		playList = new ArrayList<String>();
	}
	public Queue<String> getSongs()
	{
		return songs;
	}
	/**
	 * This inner class allows us to have an callback function that receive a
	 * songFinishedPlaying message whenever an audio file has been played.
	 */
	private class WaitingForSongToEnd implements EndOfSongListener, Serializable
	{

		public void songFinishedPlaying(EndOfSongEvent eosEvent) 
		{
			songs.poll();
			playList.remove(0);
			setChanged();
			notifyObservers(getPlaylist());
			if(!songs.isEmpty())
			{
				String temp = songs.peek();
				playSong(temp);
				songPlaying = true;
			}
			else
			{
				songPlaying = false;
			}


		}
	}

	public void playSong(String songFile)
	{
		SongPlayer.playFile(waitForSongEnd, songFile);
		songPlaying = true;

	}
	public boolean isSongPlaying()
	{
		return songPlaying;
	}
	
	public void addSong(Song song)
	{
		songs.add(song.getSong());
		playList.add(song.getFormattedLength() + " " + song.getSongTitle() + " by " + song.getArtistName());
		setChanged();
		notifyObservers(getPlaylist());
		
		if(songs.size() == 1 &&  !songPlaying)
		{
			playSong(songs.peek());
			songPlaying = true;
		}

	}
	public List<String> getPlaylist()
	{
		return playList;
	}
	@Override
	public int getSize()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Object getElementAt(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addListDataListener(ListDataListener l)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeListDataListener(ListDataListener l)
	{
		// TODO Auto-generated method stub
		
	}
}