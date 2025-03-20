package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
			
			byte[] salt = new byte[64];
			
			salt = hexToByte(accountInfo[2]);
			
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
		
		// write the new user to User.txt
		try(FileWriter fw = new FileWriter("User.txt", true)){
			//TODO check salt
			fw.write(username+","+encodedPassword+","+ byteToHex(salt)+"\n");
		} catch (IOException e) {
			System.out.println("Error writing to file");
		}
		
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
	
	// this method updates the UserDatabase with all of the new information
	public void updateDatabase() {
		try(FileWriter fw = new FileWriter("User", false)){
			//write the information to the file
			for (User user: users.values()) {
				// TODO convert the user to a string
				fw.write("");
			}
		} catch (IOException e) {
			System.out.println("Error writing to file");
		}
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
				encodedPassword += String.format("%02x", b);
			}
			return encodedPassword;

		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error with encode");
			return null;
		}
	}
	
	// converts the hexadecimal values into bytes
	public byte[] hexToByte(String hex) {
		byte[] bytes = new byte[hex.length()/2];
		for (int i = 0; i < bytes.length; i ++) {
			bytes[i] = (byte)(Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16));
		}
		
		return bytes;
	}
	
	// convert a byte are to a hexadecimal string
	public String byteToHex(byte[] bytes) {
		String hex = "";
		for (int i = 0; i < bytes.length; i ++) {
			hex = hex + String.format("%02x", bytes[i]);
		}
		return hex;
	}

}