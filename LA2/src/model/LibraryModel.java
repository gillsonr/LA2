package model;

import java.util.ArrayList;
import java.util.HashMap;

// the LibraryModel class stores user information about what they have saved from the music store
public class LibraryModel {
	
	/* TODO maybe we change these to hashmaps, ex <title, arrayList of songs>
	 * <album title, ArrayList<album>
	 * <playlist title, playlist> (we dont allow duplicate playlistNames)
	 * 
	 */
	
	// <title, songs with that title>
	private HashMap<String,ArrayList<Song>> songs;
	// <artist, songs by artist>
	private HashMap<String,ArrayList<Song>> artists;
	private ArrayList<Album> albums;
	private ArrayList<Playlist> playlists;
	private ArrayList<Song> favoriteSongs;
	// timesPlayed, Song
	private HashMap<Integer, Song> freqPlayed;

	public LibraryModel(){
		songs = new HashMap<String,ArrayList<Song>>();
		artists = new HashMap<String,ArrayList<Song>>();
		albums = new ArrayList<>();
		playlists = new ArrayList<>(); 
		favoriteSongs = new ArrayList<>();
		freqPlayed = new HashMap<Integer, Song>();
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
		
	}
	
	public String addAlbumToLibrary(String albumName, String artist) {

		for (Album a: albums) {
			if (a.getTitle().equals(albumName) && a.getArtist().equals(artist)) {
				return "Album already in library";
			}
		}
		Album a = MusicStore.getAlbumByTitleAndArtist(albumName, artist);
		albums.add(a);
		for(Song s: a.getSongs()) {
			addSong(s);
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
	public void createPlaylist(String name) {
		playlists.add(new Playlist(name));
	}
	
	// this method adds a song to a specific playlist in the playlist arraylist
	public String addSongToPlaylist(String playlistName, String songTitle, String artist) {
		boolean songFound = false;
		for (Playlist p: playlists) {
			if (p.getName().equals(playlistName)) {
				Song song = createSong(songTitle, artist);
				
				//check if song is in playlist
				if (p.getSongs().contains(songTitle) && p.getSongs().contains(artist)) {
					return "Song already in playlist";
				}
				
				//check if song is in the music library	
				for (Song s: songs.get(songTitle)) {
					if (s.getArtist().equals(artist)) {
						songFound = true;
					}
				}
				
				if (!songFound) {
					return "Song is not in library";
				}
				
				p.addSong(song);
				return songTitle + " has been added to playlist" + playlistName + "\n";
			}
		}
		// if the playlist does not exist
		return playlistName + " has not been created " + "\n"; 
	}
	
	public String addAlbumToPlaylist(String playlistName, String albumTitle, String artist) {
		boolean foundAlbum = false;
		for (Playlist p: playlists) {
			if (p.getName().equals(playlistName)) {
				Album album = MusicStore.getAlbumByTitleAndArtist(albumTitle, artist);
				if (album == null) {
					return "Album was not found in music store";
				}
				//check if album is in album list
				for (Album a: albums) {
					if (a.getArtist().equals(artist) && a.getTitle().equals(albumTitle)) {
						foundAlbum = true;
					}
				}
				if (foundAlbum == false) {return "Album not in library";}
				
				// add each song in the album to the playlist
				ArrayList<Song> albumSongs = album.getSongs();
				
				for (Song s: albumSongs) {
					p.addSong(s);
				}
				
				
				return albumTitle + " has been added to playlist" + playlistName + "\n";
			}
		}
		// if the playlist does not exist
		return playlistName + " has not been created\n"; 
	}
	
	public String getPlaylistByName(String name) {
		for (Playlist p : playlists) {
			if (p.getName().equals(name)) {
				return p.getName() + "\n" + p.getSongs();
			}
		}
		// if the playlist does not exist
		return name + " has not been created\n"; 
	}
	
	public String removeSongFromPlaylist(String playlistName, String songTitle, String artist) {
		// loop through the playlists
		for (Playlist p: playlists) {
			// if the playlist is found remove the song from the playlist
			if (p.getName().equals(playlistName)) {
				p.removeSong(songTitle, artist);
				return songTitle + " has been removed from: " + playlistName + "\n";
			}
		}
		
		// if the playlist does not exist
		return playlistName + " does not exist\n"; 
	}
	
	// list all songs by title
	public String allSongs() {
		String str = "";
		//search through all of the songs in library
		for (String songTitle: songs.keySet()) {
			for(Song s: songs.get(songTitle)) {
				str += s.toString();
			}
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
		for (Playlist p: playlists) {
			str += p.getName() + "\n";
		}
		if (str.equals("")) {
			return "No playlists in library\n";
		}
		return "Here is a list of all playlists in your library\n" + str;
	}
	
	public String favoriteSongs() {
		String str = "";
		//search through all of the songs in library
		for (Song s: favoriteSongs) {
			str += s.toString();
		}
		if (str.equals("")) {
			return "No favorites in library\n";
		}
		return "Here is a list of all favorites in your library\n" + str;
	}
	
	public String rateSong(String title, String artist, int rating) {
		// find the song
		for (Song s: songs.get(title)) {
			if (s.getArtist().equals(artist)) {
				//rate the song and tell the user it has been rated
				s.setRating(rating);
				//add the song to the list of favorite songs
				if (rating == 5) {
					favoriteSongs.add(s);
				}
				return title + "has been rated\n";
			}
		}
		
		// if the song was not found dont rate it
		return "Song was not found";
	}


}
