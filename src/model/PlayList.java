package model;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import java.util.Queue;

import Views.SongListUI;
import songPlayerDemo.Play1Song;

public class PlayList implements Serializable
{
	private Queue<Song> songs;
	private Play1Song songPlayer;
	public PlayList(SongListUI songList)
	{
		songs = new LinkedList<Song>();
		
		songPlayer = new Play1Song();
		songPlayer.addObserver(songList);
	}
	/*
	 * This will both pop the next song, and in theory play it.
	 */
	public void playNextSong()
	{
		Song temp = songs.poll();
		songPlayer.addSong(temp);
		temp.playSong();
	}
	public void addObserver(Observer o)
	{
		songPlayer.addObserver(o);
	}
	public List<String> getCurrentPlaylist()
	{
		List<String> retVal;
		retVal = songPlayer.getPlaylist();
		return retVal;
	}
	public void addSong(Song newSong, User user)
	{
		songs.add(newSong);
		user.seconds = user.seconds-newSong.getLength();
	}
	public void start()
	{
		if(!songPlayer.getSongs().isEmpty())songPlayer.playSong(songPlayer.getSongs().peek());
	}
}
