package model;

import java.util.ArrayList;
import java.util.Collections;

public class Playlist {

	private String playlistName;
	private ArrayList<Song> songs;

	public Playlist(String name) {
	    this.playlistName = name;
	    this.songs = new ArrayList<Song>();
	}
	
	public String getName() {
	    return playlistName;
	}
	
	public int getSize() {
		return songs.size();
	}
	
	public ArrayList<Song> deepCopy(){
		ArrayList<Song> newList = new ArrayList<Song>();
		for (Song s: songs) {
			newList.add(s.createCopy());
		}
		return newList;
	}
	
	// for Recently Played
	public void insertSong(Song s) {
		if (songs.size() > 9) {
			songs.remove(9);
		} 
		// if the song exist in the list move it to the front
		removeSong(s.getTitle(), s.getArtist());
		
		songs.addFirst(s);
	}
	

	// adds song if not already in playlist, 
	//returns true if added, false otherwise
	public String addSong(Song song) {
		// check if song already in playlist
		for (Song s: songs) {
			if (s.equals(song)) {
				return "Song already in playlist";
			}
		}
	    songs.add(song);
	    return song.getTitle() + " has been added to playlist " + playlistName;
	    
	}
	
	public String removeSong(String title, String artist) {
	    for(Song s: songs) {
	    	if(s.getTitle().equals(title) && s.getArtist().equals(artist)) {
	    		songs.remove(s);
	    		return title + " has been removed from: " + playlistName + "\n";
	    	}
	    }
	    return title + " was not found\n"; 
	}
	
	public String getSongs() {
		String str = "";
		if (songs.isEmpty()) {
			return playlistName + " is empty\n";
		} else {
			str += "Here is a list of songs in " + playlistName + "\n";
			for(Song s: songs) {
				str += s.toString();
			}
		}
		return str;
	}
	
	// shuffles the collection of songs
	public void shuffle() {
		Collections.shuffle(songs);
	}

	// add the song at the given index
	public void insertSong(Song song, int index) {
		songs.add(index, song);
	}

	public void removeLastSong() {
		songs.remove(songs.size()-1);
	}
}
