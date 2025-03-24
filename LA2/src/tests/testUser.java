package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.MusicStore;
import model.LibraryModel;
import model.User;



class testUser {

	@BeforeEach
	void setUp() {
		MusicStore.processFile();
	}
	@AfterEach
	void tearDown() {
		MusicStore.empty();
	}
	@Test
	void testGetUserNamePassword() {
		User user = new User("Ryan", "password");
		String returnStr = user.getUserName();
		assertEquals(returnStr, "Ryan");
		returnStr = user.getPassword();
		assertEquals(returnStr, "password");
	}
	
	@Test
	void testGetSalt() {
		User user = new User("Ryan", "password");
		byte[] salt = user.getSalt();
		byte[] test = null;
		assertEquals(salt, test);
	}
	
	@Test 
	void testdisplayLibraryEMPTY() {
		User user = new User("Ryan", "password");
		String returnStr = user.displayLibrary(1);
		String str = "No songs are in library\n";
		assertEquals(returnStr, str);
		returnStr = user.displayLibrary(2);
		assertEquals(returnStr, str);
		returnStr = user.displayLibrary(3);
		assertEquals(returnStr, str);
	}
	
	@Test 
	void testdisplayRecFrePlayedEMPTY() {
		User user = new User("Ryan", "password");
		String returnStr = user.displayRecentlyPlayed();
		String str = "Recently Played is empty\n";
		assertEquals(returnStr, str);
		returnStr = user.displayFrequentlyPlayed();
		str = "Frequently Played is empty\n";
		assertEquals(returnStr, str);
	}
	
	@Test
	void testAddRemoveSongFromLibrary() {
		User user = new User("Ryan", "password");
		String returnStr = user.addSongToLibrary("Tired", "Adele");
		String str = "Tired was added to Library\n";
		assertEquals(returnStr, str);
		returnStr = user.removeSongFromLibrary("Tired", "Adele");
		str = "Song Removed\n";
		assertEquals(returnStr,str);
	}
	
	@Test void testAddRemoveAlbumFromLibrary() {
		User user = new User("Ryan", "password");
		String returnStr = user.addAlbumToLibrary("19", "Adele");
		String str = "19 successfully added\n";
		assertEquals(returnStr, str);
		returnStr = user.removeAlbumFromLibrary("19", "Adele");
		str = "Album Removed\n";
		assertEquals(returnStr,str);
	}
	
}