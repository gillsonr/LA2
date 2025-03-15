package model;

import java.util.ArrayList;

public class User {
	private String userName;
	private String password;
	private ArrayList<Song> songs;
	
	// Constructor
	public User(String name, String pw) {
		userName = name;
		password = pw;
	}
	
	// add songs to the list of songs
	public void addSong(Song s) {
		// TODO check if the song is already in songs
		songs.add(s);
	}
	
	// get the list of songs
	public ArrayList<Song> getSongs() {
		// TODO FIX ENCAPSULATION
		return songs;
	}
	
	// get the userName
	public String getUserName() {
		return userName;
	}
	
	// get the password
	public String getPassword() {
		return password;
	}
	
}
