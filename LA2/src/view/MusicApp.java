package view; 

import java.util.*;
import model.MusicStore;
import model.User;
import model.UserDatabase;

public class MusicApp {
    private Scanner scanner;
    private User currentUser;
    private UserDatabase dataBase;

    public MusicApp() {
        scanner = new Scanner(System.in);
        dataBase = new UserDatabase();
        MusicStore.processFile();
    }

    public void run() {
        while (true) {
            System.out.println("Welcome to the Music Library!");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            System.out.println();
            
            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    createAccount();
                    break;
                case "3":
                    System.out.println("Goodbye!🎵");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void login() {
        while(true) {
        	System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            
            currentUser = dataBase.login(username, password);
            if (currentUser != null) {
                System.out.println("Login successful!\n");
                break;
            } else {
                System.out.println("Invalid credentials, please try again.");
            }
        }
        mainMenu();
    }

    private void createAccount() {
        while(true) {
        	System.out.print("Enter new username: ");
            String username = scanner.nextLine();
            System.out.print("Enter new password: ");
            String password = scanner.nextLine();
        	if (dataBase.addUser(username, password)) {
                System.out.println("Account created successfully.");
                currentUser = dataBase.login(username, password);
                break;
            } 
        	else {
                System.out.println("Username already exists, please try again.");
            }
        }
        mainMenu();
    }

    private void mainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. View Library");
            System.out.println("2. Play Song");
            System.out.println("3. View Recently Played Songs");
            System.out.println("4. View Frequently Played Songs");
            System.out.println("5. View albums in Library");
            System.out.println("6. Search Library");
            System.out.println("7. Search MusicStore");
            System.out.println("8. Add song to Library");
            System.out.println("9. Add album to Library");
            System.out.println("10. Rate a song");
            System.out.println("11. Favorite a song");
            System.out.println("12. Shuffle Library");
            System.out.println("13. Playlist options (view, create and add/remove songs)"); 
            System.out.println("14. Remove Song from Library");
            System.out.println("15. Remove Album from Library");
            System.out.println("16. Logout");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    viewLibrary();
                    break;
                case "2":
                    playSong();
                    break;
                case "3":
                    viewRecentlyPlayed();
                    break;
                case "4":
                    viewFrequentlyPlayed();
                    break;
                case "5":
                	viewAlbums();
                	break;
                case "6":
                    searchLibrary();
                    break;
                case "7":
                    searchMusicStore();
                    break;
                case "8":
                	addSongToLibrary();
                	break;
                case "9":
                	addAlbumToLibrary();
                	break;
                case "10":
                	rateSong();
                	break;
                case "11":
                	favoriteSong();
                	break;
                case "12":
                    shuffleLibrary();
                    break;
                case "13":
                	playListOptions();
                    break;
                case "14":
                    removeSong();
                    break;
                case "15":
                    removeAlbum();
                    return;
                case "16":
                    System.out.println("\nLogging out...\n");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void favoriteSong() {
		System.out.print("Enter song to favorite: ");
		String title = scanner.nextLine();
		System.out.print("Enter artist: ");
		String artist = scanner.nextLine();
		System.out.print("\n" + currentUser.rateSong(title, artist, 5));
	}

	// view all, view specific, create, add/remove
    private void playListOptions() {
		System.out.println("Playlist Options: 1. view all 2. search for playlist "
				+ "3. create playlist 4. add song 5. add album 6. remove song");
		System.out.print("Enter choice: ");
		String choice = scanner.nextLine();
		switch (choice) {
			case "1":
				System.out.print("\n" + currentUser.displayPlaylists());
				break;
			case "2":
				System.out.print("Enter playlist title: ");
				String title = scanner.nextLine();
				System.out.print("\n" + currentUser.getPlaylist(title));
				break;
			case "3":
				System.out.print("Enter playlist title to create: ");
				String playlistName = scanner.nextLine();
				System.out.print("\n" + currentUser.createPlaylist(playlistName));
				break;
			case "4":
				System.out.print("Which playlist would you like to add a song to: ");
				playlistName = scanner.nextLine();
				System.out.print("Enter song title: ");
				title = scanner.nextLine();
				System.out.print("Enter artist: ");
				String artist = scanner.nextLine();
				System.out.print("\n" + currentUser.addSongToPlaylist(playlistName, title, artist));
				break;
			case "5":
				System.out.print("Which playlist would you like to add an album to: ");
				playlistName = scanner.nextLine();
				System.out.print("Enter album title: ");
				title = scanner.nextLine();
				System.out.print("Enter artist: ");
				artist = scanner.nextLine();
				System.out.print("\n" + currentUser.addAlbumToPlaylist(playlistName, title, artist));
				break;
			case "6":
				System.out.print("Which playlist would you like to remove a song from: ");
				playlistName = scanner.nextLine();
				System.out.print("Enter song title: ");
				title = scanner.nextLine();
				System.out.print("Enter artist: ");
				artist = scanner.nextLine();
				System.out.print("\n" + currentUser.removeSongFromPlaylist(playlistName, title, artist));
				break;
		}
			
	}

	private void addAlbumToLibrary() {
		System.out.print("Enter album title to add: ");
		String title = scanner.nextLine();
		System.out.print("Enter artist: ");
		String artist = scanner.nextLine();
		System.out.print("\n" + currentUser.addAlbumToLibrary(title, artist));
	}

	private void rateSong() {
		System.out.print("Enter song title to rate: ");
		String title = scanner.nextLine();
		System.out.print("Enter artist: ");
		String artist = scanner.nextLine();
		System.out.print("Enter rating (1 through 5): ");
		String rating = scanner.nextLine();
		// while rating is invalid, request new rating
		while (!("12345".contains(rating))) {
			System.out.println("Invalid rating. Try again: ");
			rating = scanner.nextLine();
		}
		int intRating = Integer.parseInt(rating);
		System.out.print("\n" + currentUser.rateSong(title, artist, intRating));
			
			
		
	}

	private void addSongToLibrary() {
		System.out.print("Enter song title to add: ");
		String title = scanner.nextLine();
		System.out.print("Enter artist: ");
		String artist = scanner.nextLine();
		System.out.print("\n" + currentUser.addSongToLibrary(title, artist));
	}

	private void viewAlbums() {
		System.out.print("\n" + currentUser.viewAlbums());
	}

    private void searchMusicStore() {
    	System.out.println("Search for 1. song(s) or 2. album(s) in MusicStore?");
    	System.out.print("Enter choice: ");
    	String songOrAlbum = scanner.nextLine();
    	System.out.println("Search by 1. Title 2. Artist");
    	System.out.print("Enter choice: ");
    	String titleOrArtist = scanner.nextLine();
        switch(songOrAlbum) {
	        case "1":
	            if(titleOrArtist.equals("1")) {
	            	System.out.print("Enter title: ");
	            	String title = scanner.nextLine();
	            	System.out.print("\nSongs in MusicStore with title: " + title +
	            					"\n" + MusicStore.getSongsByTitle(title));
		        	break;
	            }
	            else if (songOrAlbum.equals("2")){
	            	System.out.print("Enter artist: ");
	            	String artist = scanner.nextLine();
	            	System.out.print("\nSongs in MusicStore with artist: " + artist + 
	            					"\n" + MusicStore.getSongsByArtist(artist));
		        	break;
	            }
	            else {
	            	System.out.println("Invalid choice. Try again.");
		        	searchMusicStore();
		        	break;
	            }
	        case "2":
	        	if(titleOrArtist.equals("1")) {
	            	System.out.print("Enter title: ");
	            	String title = scanner.nextLine();
	            	System.out.print("\nAlbums in MusicStore with title: " + title + 
        					"\n" + MusicStore.getAlbumsByTitle(title));
		        	break;
	            }
	            else if (songOrAlbum.equals("2")){
	            	System.out.print("Enter artist: ");
	            	String artist = scanner.nextLine();
	            	System.out.print("\nAlbums in MusicStore with artist: " + artist + 
        					"\n" + MusicStore.getAlbumsByArtist(artist));
		        	break;
	            }
	            else {
	            	System.out.println("Invalid choice. Try again.");
		        	searchMusicStore();
		        	break;
	            }
	        default:
	        	System.out.println("Invalid choice. Try again.");
	        	searchMusicStore();
        }
	}
    
	private void searchLibrary() {
		System.out.print("Would you like to search for 1. Songs or 2. Albums: ");
		String SongAlbum = scanner.nextLine();
		if(SongAlbum.equals("1")) {
			searchSongsLibrary();
			return;
		}
		else if (SongAlbum.equals("2")) {
			searchAlbumsLibrary();
			return;
		}
		else {
			System.out.println("\nInvalid Choice. Try again");
			searchLibrary();
		}
    }

	private void searchSongsLibrary() {
		System.out.println("Search for song(s) in library by: "
    			+ "1. Title 2. Album 3. Artist 4. Genre");
    	System.out.print("Enter choice: ");
    	String searchType = scanner.nextLine();
    	System.out.println();
        switch(searchType) {
	        case "1":
	        	System.out.print("Enter title of Song to search: ");
	            String song = scanner.nextLine();
	            System.out.print("\n" + currentUser.searchSong(song) + "\n");
	        	break;
	        case "2":
	        	System.out.print("Enter title of Album to search: ");
	            String album = scanner.nextLine();
	            System.out.print("\n" + currentUser.searchAlbum(album) + "\n");
	        	break;
	        case "3":
	        	System.out.print("Enter Artist to search: ");
	            String artist = scanner.nextLine();
	            System.out.print("\n" + currentUser.searchSongsByArtist(artist) + "\n");
	        	break;
	        case "4":
	        	System.out.print("Enter Genre to search: ");
	            String genre = scanner.nextLine();
	            System.out.print("\n" + currentUser.searchGenre(genre) + "\n");
	        	break;
	        default:
	        	System.out.println("Invalid choice. Try again.");
	        	searchLibrary();
	        	break;
        }
	}

	private void searchAlbumsLibrary() {
		System.out.println("Search for album(s) in library by: "
    					+ "1. Title 2. Artist");
    	System.out.print("Enter choice: ");
    	String searchType = scanner.nextLine();
    	System.out.println();
        switch(searchType) {
	        case "1":
	        	System.out.print("Enter title of Album to search: ");
	            String song = scanner.nextLine();
	            System.out.print(currentUser.searchAlbum(song) + "\n");
	        	break;
	        case "3":
	        	System.out.print("Enter Artist to search: ");
	            String artist = scanner.nextLine();
	            System.out.print("\n" + currentUser.searchAlbumsByArtist(artist) + "\n");
	        	break;
	        default:
	        	System.out.println("Invalid choice. Try again.");
	        	searchLibrary();
	        	break;
        }
	}

	private void removeSong() {
    	System.out.print("Enter title of song to remove: ");
        String title = scanner.nextLine();
        System.out.print("Enter artist: ");
        String artist = scanner.nextLine();
        System.out.println("\n" + currentUser.removeSongFromLibrary(title, artist));
	}

	private void removeAlbum() {
		System.out.print("Enter title of album to remove: ");
        String title = scanner.nextLine();
        System.out.print("Enter artist: ");
        String artist = scanner.nextLine();
        System.out.println("\n" + currentUser.removeAlbumFromLibrary(title, artist));
	}

	private void viewLibrary() {
        System.out.print("Sort library by: 1. Title 2. Artist 3. Rating\nEnter choice: ");
        String sortChoice = scanner.nextLine();
        int choice = Integer.parseInt(sortChoice);
        if (choice >= 1 && choice<=3) {
        	System.out.println("\n" + currentUser.displayLibrary(choice));
        }
        else { 
        	System.out.println("\nInvalid choice. Try again");
        	viewLibrary();
        }
    }

    private void playSong() {
        System.out.print("Enter song title to play: ");
        String title = scanner.nextLine();
        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine();
        System.out.print("\n" + currentUser.playSong(title, artist));
    }

    private void viewRecentlyPlayed() {
    	System.out.print("\n" + currentUser.displayRecentlyPlayed());
    }

    private void viewFrequentlyPlayed() {
    	System.out.print("\n" + currentUser.displayFrequentlyPlayed());
    }

    private void shuffleLibrary() {
    	System.out.println("\nSongs have been shuffled\n");
    	currentUser.shuffleSongs();
    }

    public static void main(String[] args) {
        new MusicApp().run();
    }
}
