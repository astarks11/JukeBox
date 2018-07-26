package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import model.CardReader;

public class TestUser {

	@Test
	public void testIsAllowedToPlaySong() {
		CardReader cr = new CardReader();
		cr.validate("Chris","1");
		assertTrue(cr.getCurrentUser().passwordIs("1"));
		assertFalse(cr.getCurrentUser().passwordIs("2"));
		assertTrue(cr.getCurrentUser().secondsLeft() == 90000);
		assertTrue(cr.getCurrentUser().isAllowedToPlaySong());
		assertTrue(cr.getCurrentUser().getMinutes().equals("1500.0"));
		cr.getCurrentUser().playedSong();
		cr.getCurrentUser().playedSong();
		cr.getCurrentUser().playedSong();
		cr.getCurrentUser().playedSong();
		assertFalse(cr.getCurrentUser().isAllowedToPlaySong());
	}
	@Test
	public void testSongsPlayed() {
		CardReader cr = new CardReader();
		cr.validate("Devon","22");
		LocalDate ld = LocalDate.of(2017,6,2);
		cr.getCurrentUser().playedSong();
		cr.getCurrentUser().playedSong();
		cr.getCurrentUser().playedSong();
		cr.getCurrentUser().playedSong();
		cr.getCurrentUser().setLastDatePlayed(ld);
		cr.getCurrentUser().playedSong();
		assertTrue(cr.getCurrentUser().getSongsPlayed() == 1);
	}

}
