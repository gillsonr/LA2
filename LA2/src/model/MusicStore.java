package model; 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public final class MusicStore {
	
	private static ArrayList<Album> albumList = new ArrayList<Album>();
	private static ArrayList<Song> songList = new ArrayList<Song>();
	
	public static void processFile() {
		File fr = new File("albums/albums.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(fr);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				line = line.replace(",", "_");
				line += ".txt";
				line = "albums/" + line;
				
				
				processAlbum(line);
			}
		scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// creates album, songs, and artist objects
	private static void processAlbum(String line){
		File fr = new File(line);
		Scanner scanner;
		try {
			scanner = new Scanner(fr);
			// first line = album, artist, genre, year
			String firstLine = scanner.nextLine();
			String[] firstLineArray = firstLine.split(",");
			String albumTitle = firstLineArray[0];
			String artistName = firstLineArray[1];
			String genre = firstLineArray[2];
			int year = Integer.parseInt(firstLineArray[3]);
			// each line has song title
			ArrayList<Song> songs = new ArrayList<Song>();
			while(scanner.hasNext()) {
				// adds new song from line
				Song s = new Song(scanner.nextLine(), artistName, albumTitle);
				songs.add(s);
				songList.add(s);
			}
			Album album = new Album(albumTitle, artistName, songs, genre, year);
			albumList.add(album);
			
			
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// searches through artists until finding artist with desired name
	// adds all albums by artist to return String
	public static String getAlbumsByArtist(String artist) {
		ArrayList<Album> albums = new ArrayList<Album>();
		for (Album a: albumList) {
			if (a.getArtist().equals(artist)) {
				albums.add(a);
			}
		}
		String str = "";
		for (Album a: albums) {
			str += a.toString();
		}
		return str;
	}
	
	// this method returns a single album by searching for it with both the name and artist
	public static Album getAlbumByTitleAndArtist(String title, String artist) {
		for (Album a: albumList) {
			if (a.getTitle().equals(title) && a.getArtist().equals(artist)) {
				// if the album does exist return it
				return a;
			}
		}
		// if the album does not exist return null
		return null;
	}

	
	public static String getAlbumsByTitle(String title) {
		ArrayList<Album> albums = new ArrayList<Album>();
		for (Album a: albumList) {
			if (a.getTitle().equals(title)) {
				albums.add(a);
			}
		}
		String str = "";
		for (Album a: albums) {
			str += a.toString();
		}
		return str;
	}

	public static String getSongsByTitle(String title) {
		String str = "";
		for(Song s: songList) {
			if(s.getTitle().equals(title)) {
				str += s.toString();
			}
		}
		if(str == "") {
			return "No songs found with title " + title;
		}
		return str;
	}
	
	// returns copy of song from title and artist if found, otherwise return null
	public static Song getSongByTitleArtist(String title, String artist) {
		for(Song s: songList) {
			if(s.getTitle().equals(title) && (s.getArtist().equals(artist))) {
				return s.createCopy();
			}
		}
		return null;
	}
	
	// returns a string of songs by artist if artist is found, 
	// otherwise returns empty string
	public static String getSongsByArtist(String artist) {
		ArrayList<Song> songs = new ArrayList<Song>();
		for (Song s: songList) {
			if (s.getArtist().equals(artist)) {
				songs.add(s);
			}
		}
		String str = "";
		for (Song s: songs) {
			str += s.toString();
		}
		return str;
	}
	
	public static String getSongsByGenre(String genre) {
		ArrayList<Album> albums = new ArrayList<Album>();
		for (Album a: albumList) {
			if (a.getGenre().equalsIgnoreCase(genre)) {
				albums.add(a);
			}
		}
		String str = "";
		for(Album a: albums) {
			for (Song s: a.getSongs()) {
				str += s.toString();
			}
		}
		if(str == "") {
			return "No songs found\n";
		}
		return str;
	}
}
