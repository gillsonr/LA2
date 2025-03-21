package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// the LibraryModel class stores user information about what they have saved from the music store
public class LibraryModel {
	
	/* TODO maybe we change these to hashmaps, ex <title, arrayList of songs>
	 * <album title, ArrayList<album>
	 * <playlist title, playlist> (we dont allow duplicate playlistNames)
	 * 
	 */
	
	// <title, songs with that title>
	private HashMap<String,ArrayList<Song>> songs = new HashMap<String,ArrayList<Song>>();
	// <artist, songs by artist>
	private HashMap<String,ArrayList<Song>> artists = new HashMap<String,ArrayList<Song>>();
	private ArrayList<Album> albums  = new ArrayList<>();
	
	// Collection of playlists
	//(includes playlists for each genre, Favorites, frequently played, recently played)
	private HashMap<String,Playlist> collection = new HashMap<String, Playlist>();
	private ArrayList<Song> masterSongList;

	public LibraryModel(){
		masterSongList = new ArrayList<Song>();
		collection.put("Favorites", new Playlist("Favorites"));
		// TODO should this be different then a playlist to make the max 10
		collection.put("Recently Played", new Playlist("Recently Played"));
		collection.put("Frequently Played", new Playlist("Frequently Played"));
	}
	// finds song from songName and artistName in music store and creates copy
	// if song doesn't exist, song = null
	public Song createSong(String songName, String artistName) {
		if (songName == null || artistName == null || songName.isEmpty() || artistName.isEmpty()) {
			return null;
		}
		return MusicStore.getSongByTitleArtist(songName, artistName);
	}

	// searches through songs to see if a song is already in library
	public boolean duplicate(Song s) {
		if (songs.containsKey(s.getTitle())) {
			for (Song currSong: songs.get(s.getTitle())){
				if (currSong.equals(s)) {
					return true;
				}
			}
		}
		return false;
	}
	// adds song to HashMap, if no other songs share title, 
	// will add <title, ArrayList<Song>> to HashMap
	public void addSong(Song s) {
		// if HashMap does not contain song title
		if (!songs.containsKey(s.getTitle())) {
			songs.put(s.getTitle(), new ArrayList<Song>());
		}
		// if HashMap doesn't contain songs by this artist
		if (!artists.containsKey(s.getArtist())) {
			artists.put(s.getArtist(), new ArrayList<Song>());
		}
		
		songs.get(s.getTitle()).add(s);
		artists.get(s.getArtist()).add(s);
		masterSongList.add(s);
	}
	
	public String addAlbumToLibrary(String albumName, String artist) {

		for (Album a: albums) {
			if (a.getTitle().equals(albumName) && a.getArtist().equals(artist)) {
				return "Album already in library";
			}
		}
		Album a = MusicStore.getAlbumByTitleAndArtist(albumName, artist);
		albums.add(a);
		// TODO test add song to genre playlists
		collection.put(a.getGenre(), new Playlist(a.getGenre()));
		for(Song s: a.getSongs()) {
			addSong(s);
			collection.get(a.getGenre()).addSong(s);
			
		}
		return albumName + " successfully added\n";
	}
	
	public String addSongToLibrary(String songName, String artist) {
		Song s = createSong(songName, artist);
		if (s == null) {
			return "Song could not be added; not found in MusicStore";
		}
		if (duplicate(s)) {
			return "Song could not be added; already in Library";
		}
		addSong(s);
		return songName + " was added to Library";
	}

	// this method gets all of the songs in the library by artist
	public String getSongsByArtist(String artist) {
		String result = "";
		if (artists.get(artist) == null) {
			return "No songs by " + artist; 
		}
		for(Song s: artists.get(artist)) {
			result += s.getTitle() + " from album: " + s.getAlbum()+ "\n";
		}
		return "Songs by " + artist + "\n" + result;
	}
	
	
	// this method gets all of the songs in the library by title
	public String getSongsByTitle(String title) {
		String result = "";
		if (!songs.containsKey(title)) {return "No songs with title " + title;}
		for(Song song: songs.get(title)) {
			result += song.toString();
		}
		return "Songs named " + title + "\n" + result;
	}
	
	public String getAlbumsByTitle(String title) {
		String result = "";
		
		// loop through all the albums in the library
		for (Album album: albums) {
			if (album.getTitle().equals(title)) {
				result += album.toString();
			}
		}
		
		if (result.equals("")) {
			return "No albums with title " + title; 
		} else {
			return "Albums named " + title + "\n" + result;
		}
	}
	
	public String getAlbumsByArtist(String artist) {
		String result = "";
		
		// loop through all the albums in the library
		for (Album album: albums) {
			if (album.getArtist().equals(artist)) {
				result += album.toString();
			}
		}
		
		if (result.equals("")) {
			return "No albums by " + artist; 
		} else {
			return "Albums by " + artist + "\n" + result;
		}
	}
	
	// this method creates a new playlist and adds it to the arraylist of playlists
	public String createPlaylist(String name) {
		if (!collection.containsKey(name)) {
			collection.put(name, new Playlist(name));
			return "Playlist '" + name + "' was created successfully\n";
		}
		else {
			return "Playlist name is taken\n";
		}
		
	}
	
	// this method adds a song to a specific playlist in the playlist arraylist
	public String addSongToPlaylist(String playlistName, String songTitle, String artist) {
		// if the playlist exists
		if (collection.containsKey(playlistName)) {
			// search for song
			for (Song s: songs.get(songTitle)) {
				// if song is found
				if (s.getArtist().equals(artist)) {
					return collection.get(playlistName).addSong(s);
				}
			}
			return "Song is not in library";
		}
		// if the playlist does not exist
		return playlistName + " has not been created\n"; 
	}
	
	public String addAlbumToPlaylist(String playlistName, String albumTitle, String artist) {
		// if the playlist exists
		if (collection.containsKey(playlistName)) {
			Album album = MusicStore.getAlbumByTitleAndArtist(albumTitle, artist);
			if (album == null) {return "Album was not found in music store";}
			//check if album is in album list
			boolean foundAlbum = false;
			for (Album a: albums) {
				if (a.getArtist().equals(artist) && a.getTitle().equals(albumTitle)) {
					foundAlbum = true;
				}
			}
			if (foundAlbum == false) {return "Album not in library";}
			// TODO escaping references
			for (Song s: album.getSongs()) {
				collection.get(playlistName).addSong(s);
			}
			return albumTitle + " has been added to playlist" + playlistName + "\n";
		}
		// if the playlist does not exist
		return playlistName + " has not been created\n"; 
	}
	
	public String getPlaylistByName(String name) {
		if(collection.containsKey(name)) {
			return collection.get(name).getSongs();
		}
		// if the playlist does not exist
		return name + " has not been created\n"; 
	}
	
	public String removeSongFromPlaylist(String playlistName, String songTitle, String artist) {
		
		if (!collection.containsKey(playlistName))	{
			// if the playlist does not exist
			return playlistName + " does not exist\n"; 
		}
		Playlist p = collection.get(playlistName);
		return p.removeSong(songTitle, artist);
	}
	
	// list all songs by title
	public String allSongs() {
		String str = "";
		//search through all of the songs in library
		for(Song s: masterSongList) {
			str += s.toString();
		}
		if (str.equals("")) {
			return "No songs in library\n";
		}
		return "Here is a list of all songs in your library\n" + str;
	}
	
	public String allArtists() {
		String str = "";
		if (artists.keySet() == null) {
			return "No artists in library\n";
		}
		for(String s: artists.keySet()) {
			str += s + "\n";
		}
		return "Here is a list of all artists in your library\n" + str;
	}
	
	public String allAlbums() {
		String str = "";
		//search through all of the songs in library
		for (Album a: albums) {
			str += a.getTitle() + "\n";
		}
		if (str.equals("")) {
			return "No albums in library\n";
		}
		return "Here is a list of all albums in your library\n" + str;
	}
	
	public String allPlaylists() {
		String str = "";
		//search through all of the songs in library
		for (Playlist p: collection.values()) {
			str += p.getName() + "\n";
		}
		if (str.equals("")) {
			return "No playlists in library\n";
		}
		return "Here is a list of all playlists in your library\n" + str;
	}
	
	public String favoriteSongs() {
		return collection.get("Favorites").getSongs();
		
	}
	
	public String rateSong(String title, String artist, int rating) {
		// find the song
		for (Song s: songs.get(title)) {
			if (s.getArtist().equals(artist)) {
				//rate the song and tell the user it has been rated
				s.setRating(rating);
				//add the song to the list of favorites
				// TODO test that this still works with rating == 4
				if (rating == 5 || rating == 4) {
					collection.get("Favorites").addSong(s);
				}
				return title + "has been rated\n";
			}
		}
		
		// if the song was not found dont rate it
		return "Song was not found";
	}
	public void shuffleSongs() {
		Collections.shuffle(masterSongList);
	}
	
	public String sortedByTitle() {
		if(songs.isEmpty()) {
			return "No songs are in library\n";
		}
		// creates sorted list of titles
		ArrayList<String> titles = new ArrayList<>(songs.keySet());
	    Collections.sort(titles); 
	    String str = "Here is your library, sorted by title\n";
	    // adds all songs in hashmap to str in sorted order
	    for (String t: titles) {
	    	for(Song s: songs.get(t)) {
	    		str += s.toString();
	    	}
	    }
		return str;
		
	}
	public String sortedByArtist() {
		if(artists.isEmpty()) {
			return "No songs are in library\n";
		}
		// creates sorted list of artists
		ArrayList<String> artistNames = new ArrayList<>(artists.keySet());
	    Collections.sort(artistNames); 
	    String str = "Here is your library, sorted by artist\n";
	    // adds all songs in hashmap to str in sorted order
	    for (String name: artistNames) {
	    	for(Song s: artists.get(name)) {
	    		str += s.toString();
	    	}
	    }
		return str;
	}
	public String sortedByRating() {
		// 6 rowed 2D array list, rows correspond with rating
		ArrayList<ArrayList<Song>> ratings = new ArrayList<ArrayList<Song>>();
		ratings.add(new ArrayList<Song>());
		ratings.add(new ArrayList<Song>());
		ratings.add(new ArrayList<Song>());
		ratings.add(new ArrayList<Song>());
		ratings.add(new ArrayList<Song>());
		ratings.add(new ArrayList<Song>());
		for(Song s: masterSongList) {
			// adds song to their corresponding ArrayList
			if( s.getRating() == null) {
				ratings.get(0).add(s);
			}
			else {
				switch(s.getRating()) {
					case ONE:   ratings.get(1).add(s); break;
					case TWO:   ratings.get(2).add(s); break;
					case THREE: ratings.get(3).add(s); break;
					case FOUR:  ratings.get(4).add(s); break;
					case FIVE:  ratings.get(5).add(s); break;
				}
			}
		}
		String str = "Here is your library, sorted by rating\n";
		// 2D for loop through ratings
		for (int row = 0; row < ratings.size(); row ++){
			if (row == 0) { str += "Unrated songs\n";}
			else { str += "\nSongs rated " + row + "\n";}
			for (Song s: ratings.get(row)) {
				str += s.toString();
			}
		}
		return str;
	}
	
	//public String search
	

}
