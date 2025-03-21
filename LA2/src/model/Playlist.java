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
	
	public void shuffle() {
		Collections.shuffle(songs);
	}
}
