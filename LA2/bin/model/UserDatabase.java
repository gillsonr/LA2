package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.util.HashMap;

public class UserDatabase {
	
	// hashmap with user name as the key and User as the value
	private HashMap<String, User> users = new HashMap<String, User>();
	
	// load the current users in the file Users.txt

	public void loadDatabase() throws FileNotFoundException {
		
		// this file will be formated "userName,password,salt"
		File fr = new File("Users.txt");
		Scanner scanner = new Scanner(fr);
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] accountInfo = line.split(",");
			// create a user an add it to the user map
			User curUser = new User(accountInfo[0], accountInfo[1]);
			
			//TODO convert the salt (hex) into byte[]
			byte[] salt = new byte[64];
			
			curUser.setSalt(salt);
			
			// add the user to the map of users
			users.put(accountInfo[0], curUser);
			
			// TODO add load songs
		}
		
		scanner.close();
		
	}
	
	// add user to the database, returns true if successfully creates, false otherwise
	public boolean addUser(String username, String password) {
		if (users.containsKey(username)) {
			return false;
		}

		//generate a salt for the password
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[64];
		// salt = 64 random bytes
		random.nextBytes(salt);
		
		// encrypt the password
		String encodedPassword = encode(password, salt);
		
		//
		User newUser = new User(username, encodedPassword);
		// add the salt to the user
		newUser.setSalt(salt);
		// add the user to the hashMap of users
		users.put(username,newUser);
		
		return true;
	}
	
	// determine if the entered user name and password match the expected information
	public User login(String username, String pass) {
		
		if (users.containsKey(username)) {
			// get the users encoded password and salt
			String password = users.get(username).getPassword();
			byte[] salt = users.get(username).getSalt();
			//encode the entered password
			String encodedPW = encode(pass, salt);
			// check if the passwords are the same
			if (password.equals(encodedPW)) {
				// if they are the same then return the 
				return users.get(username);
			}
		}
		return null;
	}
	
	// encodes the given string
	public String encode(String password, byte[] salt) {
		//
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			String encodedPassword = "";
			//add the salt and password to sha256
			sha256.update(password.getBytes());
			sha256.update(salt);
			//hash the password
			byte[] hash = sha256.digest();
			//convert the hash into a string
			for (byte b : hash) {
				encodedPassword += String.format("%02X", b);
			}
			return encodedPassword;

		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error with encode");
			return null;
		}
	}

}