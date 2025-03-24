package model;

import java.util.ArrayList;

public class Album {
	// Instance Variables
	private ArrayList<Song> songList;
	private String artist;
	private String genre;
	private String title;
	private int year;

	// Constructor
	public Album (String title, String artist, ArrayList<Song> songList, String genre, int year) {
		this.songList = songList;
		this.artist = artist;
		this.genre = genre;
		this.title = title;
		this.year = year;
	}
	
	// Methods
	public String getTitle() {
		return title;
	}
	
	// adds song if not already in playlist
	public void addSong(Song song) {
		// check if song already in playlist	
		for (Song s: songList) {
			if (s.equals(song)) {
				return;
			}
		}
	    songList.add(song);
	}

	// returns a deep copy of the songs in the album
	public ArrayList<Song> getSongs(){
		ArrayList<Song> copySongs = new ArrayList<Song>();
		for (Song s: this.songList){
			// create copy of individual songs
			copySongs.add(s.createCopy());
		}
		return copySongs;
	}

	public String getArtist() {
		return artist;
	}

	public String getGenre() {
		return genre;
	}

	public int getYear() {
		return year;
	}
	
	// as a note, there will be an extra line after the last song
	public String toString() {
		String str = "";
		str += title + " by " + artist + ", " 
				+ year + ", " + genre + "\nSongs:\n";
		for (Song s: songList) {
			str += s.getTitle() + "\n";
		}
		return str;
	}
}
