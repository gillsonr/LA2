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

	public void playSong(String title) {
		// TODO Auto-generated method stub
		
	}

	public void displayRecentlyPlayed() {
		// TODO Auto-generated method stub
		
	}

	public void displayFrequentlyPlayed() {
		// TODO Auto-generated method stub
		
	}

	public void shuffleSongs() {
		// TODO Auto-generated method stub
		
	}

	public void displayPlaylists() {
		library.allPlaylists();
		
	}

	public void removeAlbumFromLibrary(String title) {
		// TODO Auto-generated method stub
		
	}

	public void removeSongFromLibrary(String title) {
		// TODO Auto-generated method stub
		
	}

	public void searchAlbum(String title) {
		// TODO Auto-generated method stub
		
	}

	public void searchArtist(String artist) {
		// TODO Auto-generated method stub
		
	}

	public void searchGenre(String genre) {
		// TODO Auto-generated method stub
		
	}


	
}