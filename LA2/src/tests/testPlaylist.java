package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Playlist;
import model.Song;

class testPlaylist {
	
	@Test 
	void testGetPlayList() {
		Playlist playlist = new Playlist("2025");
		assertEquals(playlist.getName(), "2025");
	}
	
	@Test
	void testGetSongs() {
		Playlist playlist = new Playlist("test");
		Song s = new Song("Big Sur", "Jack Johnson", "All The Light Above It Too");

		// tests empty playlist
		assertEquals(playlist.getSongs(), "test is empty\n");
		playlist.addSong(s);
		
		// tests playlist w/ song
		assertEquals(playlist.getSongs(), "Here is a list of songs in test\n" +s.toString());
	}
	
	@Test
	void testDuplicateAndRemove() {
		Playlist playlist = new Playlist("test");
		Song s = new Song("Big Sur", "Jack Johnson", "All The Light Above It Too");
		playlist.addSong(s);
		playlist.addSong(s);
		String str = playlist.removeSong("Big Sur", "Jack Johnson");
		
		assertEquals(str, "Big Sur has been removed from: test\n");
	}
	
	@Test
	void testNotFoundRemove() {
		Playlist playlist = new Playlist("test");
		
		String str = playlist.removeSong("Big Sur", "Jack Johnson");
		
		assertEquals(str, "Big Sur was not found\n");
	}
	
	

}