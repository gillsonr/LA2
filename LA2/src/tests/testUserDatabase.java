package tests;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import model.UserDatabase;

public class testUserDatabase {
	
	private UserDatabase base;
	
	@Test 
	public void testDatabase() throws FileNotFoundException{
		base = new UserDatabase();
		String test = "user1,pw1,a2cb00\nuser2,pw2,adbc034\n";
		
		
		try(FileWriter fw = new FileWriter("Users.txt", false)){
			//write the information to the file
			fw.write(test);
		} catch (IOException e) {
			System.out.println("Error writing to file");
		}
		
		base.updateDatabase();
		
		base.loadDatabase();
		
		assertFalse(!base.addUser("user1" , "pw1"));
		
		assertNotNull(base.login("user1", "pw1"));
	}
	
	@Test
	public void testHexByteConversions() {
		base = new UserDatabase();
		String val = "ff01";
		assertEquals(base.byteToHex(base.hexToByte(val)), val);
	}
}
