package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Views.SongListUI;
import model.PlayList;
import model.SongCollection;
import model.User;

public class TestPlayList {

	@Test
	public void test() 
	{
		SongCollection collection = SongCollection.getInstance();
		SongListUI sL = new SongListUI(1,1);
		PlayList playlist = new PlayList(sL);
		
		User newUser = new User("Test","password123");
		
		playlist.addSong(collection.getSong(1), newUser);
		playlist.playNextSong();
		
		playlist.addSong(collection.getSong(2), newUser);
		playlist.playNextSong();
		
		playlist.addSong(collection.getSong(3), newUser);
		playlist.playNextSong();
		
		playlist.addSong(collection.getSong(4), newUser);
		playlist.playNextSong();
		
		playlist.addSong(collection.getSong(5), newUser);
		playlist.playNextSong();
		
		playlist.addSong(collection.getSong(8), newUser);
		playlist.playNextSong();
		
		List<String> tempList = playlist.getCurrentPlaylist();
		
		for(int i = 0; i < tempList.size(); i++)
		{
			System.out.println(tempList.get(i));
		}
		playlist.start();
	}

}
