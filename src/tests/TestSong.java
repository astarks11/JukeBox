package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import Views.SongListUI;
import model.PlayList;
import model.Song;
import model.SongCollection;
import model.User;

public class TestSong {

	@Test
	public void testSongs() {
		SongCollection collection = SongCollection.getInstance();
		SongListUI sL = new SongListUI(1,1);
		PlayList playlist = new PlayList(sL);
		
		User newUser = new User("Test","password123");
		
		assertTrue(collection.getSong(1).canPlay());
		if(collection.getSong(1).canPlay())
		{
			playlist.addSong(collection.getSong(1), newUser);
			playlist.playNextSong();
		}
		assertTrue(collection.getSong(1).canPlay());
		if(collection.getSong(1).canPlay())
		{
			playlist.addSong(collection.getSong(1), newUser);
			playlist.playNextSong();
		}
		assertTrue(collection.getSong(1).canPlay());
		if(collection.getSong(1).canPlay())
		{
			playlist.addSong(collection.getSong(1), newUser);
			playlist.playNextSong();
		}
		assertFalse(collection.getSong(1).canPlay());
		
		assertEquals(collection.getSong(0).getSongTitle(), "Flute");
		assertEquals(collection.getSong(0).getArtistName(), "Sun Microsystems");
		assertFalse(collection.getColumnName(0).equals(""));
		assertEquals(collection.getColumnClass(0),String.class);
		assertEquals(collection.isCellEditable(0, 0), false);
		assertEquals(collection.getValueAt(0,0).getClass(),String.class);
	}
	@Test
	public void testResetDay() {
		SongCollection collection = SongCollection.getInstance();
		SongListUI sl = new SongListUI(1,1);
		PlayList playlist = new PlayList(sl);
		Song dayTester = collection.getSong(0);
		User newUser = new User("Test","password123");
		
		
		
		if(collection.getSong(0).canPlay())
		{
			playlist.addSong(collection.getSong(0), newUser);
			playlist.playNextSong();
		}
		
		assertEquals(dayTester.getTimesPlayed(),1);
		LocalDate date = LocalDate.now().minusDays(1);
		dayTester.setDate(date);
		if(collection.getSong(0).canPlay())
		{
			playlist.addSong(collection.getSong(0), newUser);
			playlist.playNextSong();
		}
		assertEquals(dayTester.getTimesPlayed(),1);
	}
}
