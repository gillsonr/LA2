package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDatabase {

	// TODO no constructor
	
	// TODO change users to hashmap with username as key and (salted and hashed password, userdata) as value (tuple)
	// or something like that, makes searching easier
	
	
	/*
	 * I dont understand what this is working towards. Is it the data of users that have already 
	 * been made and are being accessed from a text file that we made? The accountInfo[userNum] doesnt make sense
	 * with your outline of the file being "username|password|songs", in this format each new line should be a new user.
	 * User number as a index in a single line isn't clicking in my head.
	 * 
	 * RN it seems like this is creating users from a text file. But the users should already be made right?
	 * If it is correct ^, should we be remaking everything each time? that seems inefficient but not sure 
	 * how else to do it
	 * 
	 * If it is the user data, should we have separate files for each user or just one big file
	 */
	
	private HashMap<String, User> users = new HashMap<String, User>();
	// load the current users in the file Users.txt
	// this file will be formated "username|password|song,song"
	public void loadDatabase() throws FileNotFoundException {
		
		// loads album file
		File fr = new File("Users.txt");
		Scanner scanner = new Scanner(fr);
		int userNum = 0;
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] accountInfo = line.split("|");
			// create a user an add it to the user list
			
			// TODO this uses the same exact things for both parameters
			User curUser = new User(accountInfo[userNum], accountInfo[userNum]);
			users.put(accountInfo[userNum],curUser);
			
			// TODO add load songs
			
			userNum ++;
		}
		
		scanner.close();
		
	}
	
	// add user to the database, returns true if successfully creates, false otherwise
	public boolean addUser(String username, String password) {
		if (users.containsKey(username)) {
			return false;
		}
		// add the user name to the list of users
		User newUser = new User(username, password);
		users.put(username,newUser);
		return true;
	}
	
	// TODO add information to a users account
	
	// current user login
	public User login(String username, String pass) {
		if (users.containsKey(username)) {
			String password = users.get(username).getPassword();
			if (password.equals(pass)) {
				//TODO escaping reference
				return users.get(username);
			}
		}
		// if the user does not exist return null
		// TODO handle the null in the view
		return null;
	}

	
	
}