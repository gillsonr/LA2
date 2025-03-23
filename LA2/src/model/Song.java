package model;

public class Song {
	// Instance Variables
	private String title;
	private String artist;
	private Rating rating;
	private String album;
	private int timesPlayed = 0;
	private String genre;
	
	
	// Constructor
	public Song (String title, String artist, String album) {
		this.title = title;
		this.artist = artist;
		this.rating = null;
		this.album = album;
	}
	
	public Song (String title, String artist, String album, int timesPlayed, String genre) {
		this.title = title;
		this.artist = artist;
		this.rating = null;
		this.album = album;
		this.timesPlayed = timesPlayed;
		this.genre = genre;
	}
	/* we don't want setters because a song's variables 
	   don't change (other then rating) */
	
	// Methods 
	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}
	
	// need to do something about this if hasn't been assigned
	public Rating getRating() {
		return rating;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public int getPlays() {
		return timesPlayed;
	}
	
	public String playSong() {
		timesPlayed ++;
		return "Now playing " + title + " by " + artist + "\n";
	}

	// @pre rating >= 1, rating <= 5
	public void setRating(int rating) {
		this.rating = Rating.getRating(rating);
	}
	
	public Song createCopy() {
		return new Song(this.title, this.artist, this.album, this.timesPlayed, this.genre);
	}
	
	public String toString() {
		return title + " by " + artist + " from album: " + album + "\n";
	}
	
	public boolean equals(Song s) {
		if	(this.title.equals(s.getTitle()) &&
			this.artist.equals(s.getArtist()) &&
			this.album.equals(s.getAlbum()) ) 
		{
			return true;
		}
		return false;
	}
	
	public void setGenre(String gen) {
		this.genre = gen;
	}

	public String getGenre() {
		return genre;
	}
}