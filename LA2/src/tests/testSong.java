package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Rating;
import model.Song;



class testSong {

	@Test
	void testSongToString() {
		Song s = new Song("Sticks", "Mother Mother", "Sticks");
		assertEquals("Sticks by Mother Mother from album: Sticks\n", s.toString());
	}
	
	@Test
	void testgetTitle() {
		Song s = new Song("Sticks", "Mother Mother", "Sticks");
		assertEquals("Sticks", s.getTitle());
	}
	
	@Test
	void testGetAlbum() {
		Song s = new Song("Little Pistol", "Mother Mother", "Sticks");
		assertEquals("Sticks", s.getAlbum());
	}
	
	@Test
	void testgetArtist() {
		Song s = new Song("Sticks", "Mother Mother", "Sticks");
		assertEquals("Mother Mother", s.getArtist());
	}
	
	@Test
	void testCreateCopy() {
		Song s = new Song("Sticks", "Mother Mother", "Sticks");
		// tests if they are NOT the same object
		Song copy = s.createCopy();
		assertNotEquals(s, copy);
		// tests if title and artist are same
		assertEquals(s.getArtist(), copy.getArtist());
		assertEquals(s.getTitle(), copy.getTitle());
	}
	
	@Test
	void testEquals() {
		Song s1 = new Song("Sticks", "Mother Mother", "Sticks");
		Song s2 = new Song("Verbatim", "Mother Mother", "Touch Up");
		// tests if they are NOT the same object
		Song copy = s1.createCopy();
		assertNotEquals(s1, copy);
		// tests if each instance variable is same
		assertTrue(s1.equals(copy));
		// tests if songs are not equal
		assertFalse(s2.equals(s1));
	}
	
	//several tests for rating
		@Test
		void testRatingFiveSong() {
			Song s = new Song("LIVIDI SUI GOMITI", "Maneskin", "RUSH!");
			s.setRating(5);
			assertEquals(Rating.FIVE,s.getRating());
		}
		
		@Test
		void testRatingFourSong() {
			Song s = new Song("LIVIDI SUI GOMITI", "Maneskin", "RUSH!");
			s.setRating(4);
			assertEquals(Rating.FOUR,s.getRating());
		}
		
		@Test
		void testRatingThreeSong() {
			Song s = new Song("LIVIDI SUI GOMITI", "Maneskin", "RUSH!");
			s.setRating(3);
			assertEquals(Rating.THREE,s.getRating());
		}
		
		@Test
		void testRatingTwoSong() {
			Song s = new Song("LIVIDI SUI GOMITI", "Maneskin", "RUSH!");
			s.setRating(2);
			assertEquals(Rating.TWO,s.getRating());
		}
		
		@Test
		void testRatingOneSong() {
			Song s = new Song("LIVIDI SUI GOMITI", "Maneskin", "RUSH!");
			s.setRating(1);
			assertEquals(Rating.ONE,s.getRating());
		}
		
		@Test
		void testGetNullRating() {
			Song s = new Song("LIVIDI SUI GOMITI", "Maneskin", "RUSH!");
			assertEquals(null,s.getRating());
		}
		
	@Test
	void testGetGenre() {
		Song s = new Song("Tired", "Adele", "19");
		s.setGenre("pop");
		assertEquals(s.getGenre(), "pop");
	}

}
