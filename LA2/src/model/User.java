package model;

import java.util.ArrayList;

public class User {
	private String userName;
	private String password;
	private byte[] salt;
	private LibraryModel library;
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
	
	// store the salt for the password
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	public byte[] getSalt() {
		return salt;
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
		library.shuffleSongs();
	}

	public String displayPlaylists() {
		return library.allPlaylists();
		
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