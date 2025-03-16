package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class UserDatabase {

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
	
	private ArrayList<User> users;
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
			User curUser = new User(accountInfo[userNum], accountInfo[userNum]);
			users.add(curUser);
			
			// TODO add load songs
			
			userNum ++;
		}
		
		scanner.close();
		
	}
	
	// add user to the database, returns true if successfully creates, false otherwise
	public boolean addUser(String userName, String password) {
		// check if the user name is taken 
		for (User u: users) {
			if (u.getUserName().equals(userName)) {
				return false;
			}
		}
		// add the user name to the list of users
		User newUser = new User(userName, password);
		users.add(newUser);
		return true;
	}
	
	// TODO add information to a users account
	
	// current user login
	public User login(String username, String password) {
		// scan through a list of all of the users
		for (User u: users) {
			// check if the username and password are correct
			if (u.getUserName().equals(username) && u.getPassword().equals(password)) {
				// TODO check for escaping references
				return u;
			}
		}
		// if the user does not exist return null
		// TODO handle the null in the view
		return null;
	}

	
	
}