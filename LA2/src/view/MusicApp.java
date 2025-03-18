package view;

import java.io.FileNotFoundException;
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
            System.out.println("5. Search Library");
            System.out.println("6. Search MusicStore");
            System.out.println("7. Shuffle Library");
            System.out.println("8. View Playlists");
            System.out.println("9. Remove Song");
            System.out.println("10. Remove Album");
            System.out.println("11. Logout");
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
                    searchLibrary();
                    break;
                case "6":
                    searchMusicStore();
                    break;
                case "7":
                    shuffleLibrary();
                    break;
                case "8":
                	System.out.print(currentUser.displayPlaylists());;
                    break;
                case "9":
                    removeSong();
                    break;
                case "10":
                    removeAlbum();
                    return;
                case "11":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }


    private void searchMusicStore() {
    	System.out.println("Search for song(s) in MusicStore by: "
    			+ "1. Title 2. Album 3. Artist 4. Genre");
    	System.out.print("Enter choice: ");
    	String searchType = scanner.nextLine();
    	System.out.println();
        switch(searchType) {
	        case "1":
	        	System.out.print("Enter Song Title to search: ");
	            String title = scanner.nextLine();
	            System.out.print(MusicStore.getSongsByTitle(title));
	            System.out.print("Would you like to view album details? (yes/no): ");
	            if (scanner.nextLine().equalsIgnoreCase("yes")) {
	                //MusicStore.displayAlbumInfo(title);
	            }
	        	break;
	        case "2":
	        	System.out.print("Enter Album to search: ");
	            String album = scanner.nextLine();
	            System.out.print(MusicStore.getAlbumsByTitle(album));
	        	break;
	        case "3":
	        	System.out.print("Enter Artist to search: ");
	            String artist = scanner.nextLine();
	            System.out.print(MusicStore.getSongsByArtist(artist));
	        	break;
	        case "4":
	        	System.out.print("Enter Genre to search: ");
	            String genre = scanner.nextLine();
	            System.out.print(MusicStore.getSongsByGenre(genre));
	        	break;
	        default:
	        	System.out.println("Invalid choice. Try again.");
	        	searchMusicStore();
        }
	}
    
	private void searchLibrary() {
    	System.out.println("Search for song(s) in library by: "
    			+ "1. Title 2. Album 3. Artist 4. Genre");
    	System.out.print("Enter choice: ");
    	String searchType = scanner.nextLine();
    	System.out.println();
        switch(searchType) {
	        case "1":
	        	searchSong();
	        	break;
	        case "2":
	        	System.out.print("Enter title of Album to search: ");
	            String album = scanner.nextLine();
	            currentUser.searchAlbum(album);
	        	break;
	        case "3":
	        	System.out.print("Enter Artist to search: ");
	            String artist = scanner.nextLine();
	            currentUser.searchArtist(artist);
	        	break;
	        case "4":
	        	System.out.print("Enter Genre to search: ");
	            String genre = scanner.nextLine();
	            currentUser.searchGenre(genre);
	        	break;
	        default:
	        	System.out.println("Invalid choice. Try again.");
	        	searchLibrary();
	        	break;
        }
    }

	private void searchSong() {
		// TODO Auto-generated method stub
		System.out.print("Would you like to view album details? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            //library.displayAlbumInfo(title);
        }
	}

	private void removeSong() {
    	System.out.print("Enter title of song to remove: ");
        String title = scanner.nextLine();
        currentUser.removeSongFromLibrary(title);
	}

	private void removeAlbum() {
		System.out.print("Enter title of album to remove: ");
        String title = scanner.nextLine();
        currentUser.removeAlbumFromLibrary(title);
	}

	private void viewLibrary() {
        System.out.println("Sort library by: 1. Title 2. Artist 3. Rating");
        int sortChoice = scanner.nextInt();
        scanner.nextLine();
        currentUser.displayLibrary(sortChoice);
    }

    private void playSong() {
        System.out.print("Enter song title to play: ");
        String title = scanner.nextLine();
        currentUser.playSong(title);
    }

    private void viewRecentlyPlayed() {
    	currentUser.displayRecentlyPlayed();
    }

    private void viewFrequentlyPlayed() {
    	currentUser.displayFrequentlyPlayed();
    }

    private void shuffleLibrary() {
    	currentUser.shuffleSongs();
    }

    public static void main(String[] args) {
        new MusicApp().run();
    }
}
