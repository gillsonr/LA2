package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// the LibraryModel class stores user information about what they have saved from the music store
public class LibraryModel {
	
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
		// TODO dont think we should hardcode genres, what if a new genre is added to the albums text file
		masterSongList = new ArrayList<Song>();
		collection.put("Favorites", new Playlist("Favorites"));
		collection.put("Recently Played", new Playlist("Recently Played"));
		collection.put("Frequently Played", new Playlist("Frequently Played"));
		collection.put("Top Rated", new Playlist("Top Rated"));
		// playlists only appear when they have more than 10 songs
		collection.put("ROCK GENRE", new Playlist("ROCK GENRE"));
		collection.put("COUNTRY GENRE", new Playlist("COUNTRY GENRE"));
		collection.put("TRADITIONAL COUNTRY GENRE", new Playlist("TRADITIONAL COUNTRY GENRE"));
		collection.put("CLASSICAL GENRE", new Playlist("CLASSICAL GENRE"));
		collection.put("ROCK GENRE", new Playlist("ROCK GENRE"));
		collection.put("POP GENRE", new Playlist("POP GENRE"));
		collection.put("ALTERNATIVE GENRE", new Playlist("ALTERNATIVE GENRE"));
		collection.put("LATIN GENRE", new Playlist("LATIN GENRE"));
		collection.put("SINGER/SONGWRITE GENRE", new Playlist("SINGER/SONGWRITER GENRE"));
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
		
		if (collection.containsKey(s.getGenre())) {
			collection.get(s.getGenre() + "GENRE").addSong(s);
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
		
		// if genre isn't in collection, add it
		if (!collection.containsKey(a.getGenre() + " GENRE")) {
			collection.put(a.getGenre(), new Playlist(a.getGenre()));
		}
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
		if (artists.containsKey(artist)) {
			for(Song s: artists.get(artist)) {
				result += s.getTitle() + " from album: " + s.getAlbum()+ "\n";
			}
			// if no songs were added to result, Library doesn't contain any songs by them
			if (result.equals("")) {
				return "No songs by " + artist; 
			}
			return "Songs by " + artist + "\n" + result;
		}
		return "No songs by " + artist; 
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
			if(songs.containsKey(songTitle)) {
				for (Song s: songs.get(songTitle)) {
					// if song is found
					if (s.getArtist().equals(artist)) {
						return collection.get(playlistName).addSong(s);
					}
				}
			}
			return "Song is not in library\n";
		}
		// if the playlist does not exist
		return playlistName + " has not been created\n"; 
	}
	
	public String addAlbumToPlaylist(String playlistName, String albumTitle, String artist) {
		// if the playlist exists
		if (collection.containsKey(playlistName)) {
			//check if album is in album list
			for (Album a: albums) {
				if (a.getArtist().equals(artist) && a.getTitle().equals(albumTitle)) {
					for (Song s: a.getSongs()) {
						collection.get(playlistName).addSong(s);
					}
					return albumTitle + " has been added to playlist" + playlistName + "\n";
				}
			}
			return "Album not in library\n";
			
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
			// if the playlist is not a genre playlist or has more then 10 chars print
			if (p.getSize() >= 10 || !p.getName().contains("GENRE")) {
				str += p.getName() + "\n";
			}
		}
		return "Here is a list of all playlists in your library\n" + str;
	}
	
	public String favoriteSongs() {
		return collection.get("Favorites").getSongs();
		
	}
	
	public String topRated() {
		return collection.get("Top Rated").getSongs();
	}
	
	public String rateSong(String title, String artist, int rating) {
		if (!songs.containsKey(title)) {
			// if the song was not found
			return "Song was not found\n";
		}
		// find the song
		for (Song s: songs.get(title)) {
			if (s.getArtist().equals(artist)) {
				//rate the song and tell the user it has been rated
				s.setRating(rating);
				//add the song to the list of favorites
				if (rating == 5 || rating == 4) {
					collection.get("Top Rated").addSong(s);
				}
				if (rating == 5) {
					collection.get("Favorites").addSong(s);
				}
				return title + "has been rated\n";
			}
		}
		
		// if the song was not found dont rate it
		return "Song was not found\n";
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
		if (masterSongList.size() == 0) {
			return "No songs are in library\n";
		}
		
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
	
	public String playSong(String title, String artist) {
		if(!songs.containsKey(title)) {
			return "Song is not in library\n";
		}
		for (Song s : songs.get(title)){
			if (s.getArtist().equals(artist)) {
				// inserts song to Recently Played playlist
				collection.get("Recently Played").insertSong(s);
				freqPlayed(s);
				
				return s.playSong();
			}
		}
		return "Song is not in library\n";
		
	}
	
	// adds song to freqPlayed if applicable 
	public void freqPlayed(Song song) {
		// if freq played < 10 songs, add to song 
		ArrayList<Song> freqPlayed = collection.get("Frequently Played").deepCopy();
		
		boolean found = false;
		int index = 0;
		while (index < freqPlayed.size()) {
			// if already in freqPlayed, exit method (no action needed)
			if(freqPlayed.get(index).getTitle().equals(song.getTitle()) &&
				freqPlayed.get(index).getArtist().equals(song.getArtist())	) {
				return;
			}
			else if(freqPlayed.get(index).getPlays() <= song.getPlays()) {
				collection.get("Frequently Played").insertSong(song, index);
				found = true;
				break;
			}
			index ++;
		}
		
		// song was not added to frequently played,
		//so if it is full, freqPlayed will still be 9
		if (freqPlayed.size()>9) {
			collection.get("Frequently Played").removeLastSong();
		}
		// if found == false
		if (!found) {
			collection.get("Frequently Played").addSong(song);
		}
		
	}
	
	
	public String displayRecentlyPlayed() {
		return collection.get("Recently Played").getSongs();
	}
	
	public String displayFrequentlyPlayed() {
		return collection.get("Frequently Played").getSongs();
	}
	
	public String removeSongFromLibrary(String title, String artist) {
		for (Song s: masterSongList) {
			// if song is found, remove from hashmap of songs and masterSongList
			if (s.getTitle().equals(title) && s.getArtist().equals(artist)) {
				masterSongList.remove(s);
				removeSongFromArtistList(title,artist);
				// returns "Song Removed\n" in this case, because we know song will be found
				return removeSongFromHashMap(title, artist);
			}
		} return "Song not found\n";
		
	}
	
	public String removeSongFromHashMap(String title, String artist) {
		if (songs.containsKey(title)) {
			for (Song s: songs.get(title)) {
				if (s.getArtist().equals(artist)) {
					songs.get(title).remove(s);
					return "Song Removed\n";
				}
			}
		}
		return "Song not found\n";
	}
	public String removeSongFromArtistList(String title, String artist) {
		if (artists.containsKey(artist)) {
			for (Song s: artists.get(artist)) {
				if (s.getTitle().equals(title)) {
					artists.get(artist).remove(s);
					return "Song Removed\n";
				}
			}
		}
		return "Song not found\n";
	}
	
	public String removeAlbumFromLibrary(String album, String artist) {
		boolean found = false;
		ArrayList<Song> songsFound = new ArrayList<Song>();
		for (Song s: masterSongList) {
			if (s.getAlbum().equals(album) && s.getArtist().equals(artist)) {
				removeSongFromHashMap(s.getTitle(), s.getArtist());
				removeSongFromArtistList(s.getTitle(), s.getArtist());
				songsFound.add(s);
				found = true;
			}
		}
		if (found) {
			// remove any songs from album
			for(Song s: songsFound) {
				masterSongList.remove(s);
			}
			// remove album from list of albums
			for(Album a: albums) {
				if (a.getArtist().equals(artist) && a.getTitle().equals(album)) {
					albums.remove(a);
					break;
				}
			}
			return "Album Removed\n";
		} 
		return "Album not found\n"; 
	}
	
	public String searchByGenre(String genre) {
		if (collection.containsKey(genre + " GENRE")){
			return collection.get(genre+" GENRE").getSongs();
		}
		return "Genre not found";
	}

}