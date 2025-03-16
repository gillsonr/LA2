package view;

import java.util.*;

import model.LibraryModel;
import model.User;
import model.UserDatabase;

public class MusicApp {
    private Scanner scanner;
    private User currentUser;
    private UserDatabase dataBase;

    public MusicApp() {
        scanner = new Scanner(System.in);
        dataBase = new UserDatabase();
    }

    public void run() {
        while (true) {
            System.out.println("Welcome to the Music Library!");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
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
            //TODO Edit searchability
            System.out.println("5. Search for a Song");
            System.out.println("6. Shuffle Library");
            System.out.println("7. View Playlists");
            System.out.println("8. Remove Song or Album");
            System.out.println("9. Logout");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    viewLibrary();
                    break;
                case 2:
                    playSong();
                    break;
                case 3:
                    viewRecentlyPlayed();
                    break;
                case 4:
                    viewFrequentlyPlayed();
                    break;
                case 5:
                    searchSong();
                    break;
                case 6:
                    shuffleLibrary();
                    break;
                case 7:
                    viewPlaylists();
                    break;
                case 8:
                    removeSongOrAlbum();
                    break;
                case 9:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
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
        currentUser.playSong(currentUser, title);
    }

    private void viewRecentlyPlayed() {
    	currentUser.displayRecentlyPlayed(currentUser);
    }

    private void viewFrequentlyPlayed() {
    	currentUser.displayFrequentlyPlayed(currentUser);
    }

    private void searchSong() {
    	//TODO fix the searches
        System.out.print("Search for song(s) by: 1. Title 2. Artist 3. Genre");
        String searchType = scanner.nextLine();
        //library.searchSong(title);
        System.out.print("Would you like to view album details? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            //library.displayAlbumInfo(title);
        }
    }

    private void shuffleLibrary() {
    	currentUser.shuffleSongs(currentUser);
    }

    private void viewPlaylists() {
    	currentUser.displayPlaylists(currentUser);
    }

    private void removeSongOrAlbum() {
        System.out.print("Enter title of song or album to remove: ");
        String title = scanner.nextLine();
        currentUser.removeFromLibrary(currentUser, title);
    }

    public static void main(String[] args) {
        new MusicApp().run();
    }
}
