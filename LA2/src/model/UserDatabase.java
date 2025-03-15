package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class UserDatabase {

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
	
	// add user to the database
	public String addUser(String userName, String password) {
		// check if the user name is taken 
		for (User u: users) {
			if (u.getUserName().equals(userName)) {
				return "User name is already taken";
			}
		}
		// add the user name to the list of users
		User newUser = new User(userName, password);
		users.add(newUser);
		return "New account successfully created";
	}
	
	// TODO add information to a users account
	
	// current user login
	public User login(String username, String password) {
		// scan through a list of all of the users
		for (User u: users) {
			// check if the username and password are correct
			if (u.getUserName().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		// if the user does not exist return null
		// TODO handle the null in the view
		return null;
	}
	
}
