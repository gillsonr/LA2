package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.LibraryModel;
import model.MusicStore;

class testLibraryModel {
	
	@BeforeEach
	void setUp() {
		MusicStore.processFile();
	}
	@AfterEach
	void tearDown() {
		MusicStore.empty();
	}
	
	@Test
	void testAddValidSongToLibrary() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		String str = "Green Eyes was added to Library";
		String returnString = library.addSongToLibrary("Green Eyes", "Coldplay");
		assertEquals(str,returnString);
	}
	
	@Test
	void testAddNONValidSongToLibrary() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		String returnString = library.addSongToLibrary("Green", "Coldplay");
		String str = "Song could not be added; not found in MusicStore";
		assertEquals(str,returnString);
	}
	
	@Test
	void testAddDuplicateSongToLibrary() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		String str = "Song could not be added; already in Library";
		
		// attempts to add song twice, storing returnString on second attempt
		library.addSongToLibrary("Green Eyes", "Coldplay");
		String returnString = library.addSongToLibrary("Green Eyes", "Coldplay");
		assertEquals(str,returnString);
	}
	
	@Test
	void testNonValidAlbumToLibrary() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		String str = "Song could not be added; already in Library";
		
		// attempts to add song twice, storing returnString on second attempt
		library.addSongToLibrary("Green Eyes", "Coldplay");
		String returnString = library.addSongToLibrary("Green Eyes", "Coldplay");
		assertEquals(str,returnString);
	}
	
	@Test
	void testAddAlbumToLibrary() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		String returnStr = library.addAlbumToLibrary("19", "Adele");
		String str = "19 successfully added\n";
		assertEquals(returnStr, str);
		returnStr = library.addAlbumToLibrary("19", "Adele");
		str = "Album already in library";
		assertEquals(returnStr, str);
		returnStr = library.addAlbumToLibrary("21", "Adele");
		str = "21 successfully added\n";
		assertEquals(returnStr, str);
	}
	
	@Test
	void testAllSongs() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		
		String str = "No songs in library\n";
		assertEquals(library.allSongs(),str);
		
		library.addSongToLibrary("Tired", "Adele");
		library.addSongToLibrary("Green Eyes", "Coldplay");
		str = "Here is a list of all songs in your library\n" +
				"Tired by Adele from album: 19\n" +
				"Green Eyes by Coldplay from album: A Rush of Blood to the Head\n";
						
		String returnStr = library.allSongs();
		assertEquals(returnStr,str);
	}
	
	@Test
	void testAllArtists() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addSongToLibrary("Tired", "Adele");
		library.addSongToLibrary("Green Eyes", "Coldplay");
		String str = "Here is a list of all artists in your library\n" +
						"Adele\n"+
						"Coldplay\n";
		String returnStr = library.allArtists();
		assertEquals(returnStr,str);
	}
	
	
	@Test
	void testAllAlbums() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addAlbumToLibrary("21", "Adele");
		library.addAlbumToLibrary("A Rush of Blood to the Head", "Coldplay");
		String str = "Here is a list of all albums in your library\n" +
						"21\n"+
						"A Rush of Blood to the Head\n";
		String returnStr = library.allAlbums();
		assertEquals(returnStr,str);
	}
	
	
	@Test
	void testSongsByArtistFROMALBUM() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		String str = "No songs by Coldplay";
		assertEquals(library.getSongsByArtist("Coldplay"),str);
		library.addAlbumToLibrary("21", "Adele");
		library.addAlbumToLibrary("A Rush of Blood to the Head", "Coldplay");
		str = "Songs by Coldplay\n" +
				"Politik from album: A Rush of Blood to the Head\n" +
				"In My Place from album: A Rush of Blood to the Head\n" +
				"God Put a Smile Upon Your Face from album: A Rush of Blood to the Head\n" +
				"The Scientist from album: A Rush of Blood to the Head\n" +
				"Clocks from album: A Rush of Blood to the Head\n" +
				"Daylight from album: A Rush of Blood to the Head\n" +
				"Green Eyes from album: A Rush of Blood to the Head\n" +
				"Warning Sign from album: A Rush of Blood to the Head\n" +
				"A Whisper from album: A Rush of Blood to the Head\n" +
				"A Rush of Blood to the Head from album: A Rush of Blood to the Head\n" +
				"Amsterdam from album: A Rush of Blood to the Head\n";
		String returnStr = library.getSongsByArtist("Coldplay");
		assertEquals(str,returnStr);
	}
	
	@Test
	void testSongsByArtist() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		String str = "No songs by Coldplay";
		assertEquals(library.getSongsByArtist("Coldplay"),str);
		library.addSongToLibrary("Tired", "Adele");
		str = "Songs by Adele\nTired from album: 19\n";
		String returnStr = library.getSongsByArtist("Adele");
		assertEquals(str,returnStr);
	}
	
	@Test
	void testSongsByATitle() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		String str = "No songs with title Green Eyes";
		assertEquals(library.getSongsByTitle("Green Eyes"),str);
		library.addAlbumToLibrary("A Rush of Blood to the Head", "Coldplay");
		str = "Songs named Green Eyes\n" + 
				"Green Eyes by Coldplay from album: A Rush of Blood to the Head\n";
		String returnStr = library.getSongsByTitle("Green Eyes");
		assertEquals(str,returnStr);
	}
	
	@Test
	void testGetAlbumsByTitle() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		String str = "No albums with title A Rush of Blood to the Head";
		assertEquals(library.getAlbumsByTitle("A Rush of Blood to the Head"),str);
		library.addAlbumToLibrary("A Rush of Blood to the Head", "Coldplay");
		str = "Albums named A Rush of Blood to the Head\n" + 
				"A Rush of Blood to the Head by Coldplay, 2002, Alternative\n" +
				"Songs:\n" + 
				"Politik\n" +
				"In My Place\n" +
				"God Put a Smile Upon Your Face\n" +
				"The Scientist\n" +
				"Clocks\n" +
				"Daylight\n" +
				"Green Eyes\n" +
				"Warning Sign\n" +
				"A Whisper\n" +
				"A Rush of Blood to the Head\n" +
				"Amsterdam\n";
		String returnStr = library.getAlbumsByTitle("A Rush of Blood to the Head");
		assertEquals(str,returnStr);
	}
	
	@Test
	void testGetAlbumsByArtist() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		String str = "No albums by Coldplay";
		assertEquals(library.getAlbumsByArtist("Coldplay"),str);
		library.addAlbumToLibrary("A Rush of Blood to the Head", "Coldplay");
		str = "Albums by Coldplay\n" + 
				"A Rush of Blood to the Head by Coldplay, 2002, Alternative\n" +
				"Songs:\n" + 
				"Politik\n" +
				"In My Place\n" +
				"God Put a Smile Upon Your Face\n" +
				"The Scientist\n" +
				"Clocks\n" +
				"Daylight\n" +
				"Green Eyes\n" +
				"Warning Sign\n" +
				"A Whisper\n" +
				"A Rush of Blood to the Head\n" +
				"Amsterdam\n";
		String returnStr = library.getAlbumsByArtist("Coldplay");
		assertEquals(str,returnStr);
	}
	@Test
	void testGetPlaylistByName() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.createPlaylist("ALT");
		String returnStr = library.getPlaylistByName("ALT");
		String str = "ALT\nThis playlist is empty\n";
		assertEquals(returnStr, str);
		
		// test non existent playlist
		str = "playlist has not been created\n";
		assertEquals(str, library.getPlaylistByName("playlist"));
	}
	
	@Test
	void testAddSongToPlaylist() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.createPlaylist("ALT");
		library.addAlbumToLibrary("A Rush of Blood to the Head", "Coldplay");
		library.addSongToPlaylist("ALT", "A Whisper", "Coldplay");
		String returnStr = library.getPlaylistByName("ALT");
		String str = "ALT\nA Whisper by Coldplay from album: A Rush of Blood to the Head\n";
		assertEquals(returnStr, str);
	}
	
	@Test
	void testAddAlbumToPlaylist() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.createPlaylist("ALT");
		library.addAlbumToLibrary("A Rush of Blood to the Head", "Coldplay");
		library.addAlbumToPlaylist("ALT", "A Rush of Blood to the Head", "Coldplay");
		String returnStr = library.getPlaylistByName("ALT");
		String str = "ALT\n"
				+ "Politik by Coldplay from album: A Rush of Blood to the Head\n"
				+ "In My Place by Coldplay from album: A Rush of Blood to the Head\n"
				+ "God Put a Smile Upon Your Face by Coldplay from album: A Rush of Blood to the Head\n"
				+ "The Scientist by Coldplay from album: A Rush of Blood to the Head\n"
				+ "Clocks by Coldplay from album: A Rush of Blood to the Head\n"
				+ "Daylight by Coldplay from album: A Rush of Blood to the Head\n"
				+ "Green Eyes by Coldplay from album: A Rush of Blood to the Head\n"
				+ "Warning Sign by Coldplay from album: A Rush of Blood to the Head\n"
				+ "A Whisper by Coldplay from album: A Rush of Blood to the Head\n"
				+ "A Rush of Blood to the Head by Coldplay from album: A Rush of Blood to the Head\n"
				+ "Amsterdam by Coldplay from album: A Rush of Blood to the Head\n";
		assertEquals(returnStr, str);
	}
	
	@Test
	void testRemoveSongFromPlaylist() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.createPlaylist("ALT");
		library.addAlbumToLibrary("A Rush of Blood to the Head", "Coldplay");
		library.addAlbumToPlaylist("ALT", "A Rush of Blood to the Head", "Coldplay");
		String returnStr = library.removeSongFromPlaylist("ALT", "Green Eyes", "Coldplay");
		String str = "Green Eyes has been removed from: ALT\n";
		assertEquals(returnStr, str);
	}
	
	@Test
	void testAllPlaylists() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		assertEquals("No playlists in library\n", library.allPlaylists());
		library.createPlaylist("ALT");
		library.createPlaylist("2025");
		String returnStr = library.allPlaylists();
		String str = "Here is a list of all playlists in your library\n" +
					"ALT\n" +
					"2025\n";
		assertEquals(str, returnStr);
	}
	
	@Test
	void testFavoriteSongs() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		assertEquals(library.favoriteSongs(), "No favorites in library\n");
		library.addAlbumToLibrary("A Rush of Blood to the Head", "Coldplay");
		library.rateSong("Green Eyes", "Coldplay", 5);
		String returnStr = library.favoriteSongs();
		String str = "Here is a list of all favorites in your library\n"+ 
						"Green Eyes by Coldplay from album: A Rush of Blood to the Head\n";
		assertEquals(str, returnStr);
	}

}
