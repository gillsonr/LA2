package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.User;



class testUser {

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
}