package model;

import java.util.ArrayList;

public class User {
	private String userName;
	private String password;
	private byte[] salt;
	private LibraryModel library = new LibraryModel();
	private ArrayList<Song> songs = new ArrayList<Song>();
	
	
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

	//@pre must be int 1,2,or3
	public String displayLibrary(int sortChoice) {
		// TODO Auto-generated method stub
		switch (sortChoice) {
			case 1:
				return library.sortedByTitle();
			case 2:
				return library.sortedByArtist();
			case 3:
				return library.sortedByRating();
		}
		return null;
	}

	public String playSong(String title, String artist) {
		return library.playSong(title, artist);
		
	}

	public String displayRecentlyPlayed() {
		return library.displayRecentlyPlayed();
		
	}

	public String displayFrequentlyPlayed() {
		return library.displayFrequentlyPlayed();
		
	}

	public void shuffleSongs() {
		library.shuffleSongs();
	}

	public String displayPlaylists() {
		return library.allPlaylists();
		
	}

	public String removeAlbumFromLibrary(String title, String artist) {
		return library.removeAlbumFromLibrary(title, artist);
		
	}

	public String removeSongFromLibrary(String title, String artist) {
		return library.removeSongFromLibrary(title, artist);
		
	}

	public String searchAlbum(String title) {
		return library.getAlbumsByTitle(title);
	}

	public String searchSongsByArtist(String artist) {
		return library.getSongsByArtist(artist);
	}
	
	public String searchAlbumsByArtist(String artist) {
		return library.getAlbumsByArtist(artist);
	}

	// TODO this wont work rn
	public String searchGenre(String genre) {
		return library.getPlaylistByName(genre);
	}

	public String addSongToLibrary(String title, String artist) {
		return library.addSongToLibrary(title, artist);
	}
	
	public String searchSong(String title) {
		return library.getSongsByTitle(title);
	}

	public String getPlaylist(String title) {
		return library.getPlaylistByName(title);
	}

	public String createPlaylist(String title) {
		return library.createPlaylist(title);
	}

	public String removeSongFromPlaylist(String playlistName, String title, String artist) {
		return library.removeSongFromPlaylist(playlistName, title, artist);
	}

	public String viewAlbums() {
		return library.allAlbums();
	}

	public String addAlbumToLibrary(String title, String artist) {
		return library.addAlbumToLibrary(title, artist);
	}
	

	public String rateSong(String title, String artist, int rating) {
		return library.rateSong(title, artist, rating);
	}

	public String addSongToPlaylist(String playlistName, String title, String artist) {
		return library.addSongToPlaylist(playlistName, title, artist);
	}
	
	public String addAlbumToPlaylist(String playlistName, String title, String artist) {
		return library.addAlbumToPlaylist(playlistName, title, artist);
	}
	
	// TODO fill
	public String toString() {
		return "";
	}

	


	
}