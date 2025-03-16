package model;

import java.util.ArrayList;

public class User {
	private String userName;
	private String password;
	private LibraryModel library;
	// I think this needs to be the library, users don't have just an open list of songs
	// it should be an entire library just like we were doing before we had users
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

	public void displayLibrary(int sortChoice) {
		// TODO Auto-generated method stub
		
	}

	public void playSong(User currentUser, String title) {
		// TODO Auto-generated method stub
		
	}

	public void displayRecentlyPlayed(User currentUser) {
		// TODO Auto-generated method stub
		
	}

	public void displayFrequentlyPlayed(User currentUser) {
		// TODO Auto-generated method stub
		
	}

	public void shuffleSongs(User currentUser) {
		// TODO Auto-generated method stub
		
	}

	public void displayPlaylists(User currentUser) {
		// TODO Auto-generated method stub
		
	}

	public void removeFromLibrary(User currentUser, String title) {
		// TODO Auto-generated method stub
		
	}
	
}