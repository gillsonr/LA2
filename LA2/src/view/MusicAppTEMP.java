import java.util.Scanner;

public class MusicAppTEMP {
    private Scanner scanner;
    private MusicController controller;
    
    public MusicApp(MusicController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }
    
    public void run() {
        System.out.println("Welcome to the Music Library App!");
        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Log In");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    logIn();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    
    private void logIn() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        if (controller.authenticateUser(username, password)) {
            System.out.println("Login successful!");
            userMenu(username);
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }
    
    private void register() {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        
        if (controller.registerUser(username, password)) {
            System.out.println("Registration successful! You can now log in.");
        } else {
            System.out.println("Username already taken. Try a different one.");
        }
    }
    
    private void userMenu(String username) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View Library");
            System.out.println("2. Search Songs");
            System.out.println("3. Play Song");
            System.out.println("4. View Recently Played");
            System.out.println("5. View Most Played");
            System.out.println("6. Shuffle Library");
            System.out.println("7. Manage Playlists");
            System.out.println("8. Sort Library");
            System.out.println("9. Remove Song/Album");
            System.out.println("10. Log Out");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    viewLibrary();
                    break;
                case 2:
                    searchSongs();
                    break;
                case 3:
                    playSong(username);
                    break;
                case 4:
                    viewRecentlyPlayed();
                    break;
                case 5:
                    viewMostPlayed();
                    break;
                case 6:
                    shuffleLibrary();
                    break;
                case 7:
                    managePlaylists();
                    break;
                case 8:
                    sortLibrary();
                    break;
                case 9:
                    removeSongOrAlbum();
                    break;
                case 10:
                    loggedIn = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    
    private void viewLibrary() {
        System.out.println("Your library:");
        controller.displayLibrary();
    }
    
    private void searchSongs() {
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        controller.searchSong(title);
    }
    
    private void playSong(String username) {
        System.out.print("Enter song title to play: ");
        String title = scanner.nextLine();
        controller.playSong(username, title);
    }
    
    private void viewRecentlyPlayed() {
        System.out.println("Recently Played Songs:");
        controller.displayRecentlyPlayed();
    }
    
    private void viewMostPlayed() {
        System.out.println("Most Played Songs:");
        controller.displayMostPlayed();
    }
    
    private void shuffleLibrary() {
        System.out.println("Shuffled Library:");
        controller.shuffleLibrary();
    }
    
    private void managePlaylists() {
        System.out.println("Manage Playlists:");
        controller.displayPlaylists();
    }
    
    private void sortLibrary() {
        System.out.println("Sort Library:");
        System.out.println("1. By Title");
        System.out.println("2. By Artist");
        System.out.println("3. By Rating");
        System.out.print("Choose sorting method: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        controller.sortLibrary(choice);
    }
    
    private void removeSongOrAlbum() {
        System.out.print("Enter song or album title to remove: ");
        String title = scanner.nextLine();
        controller.removeSongOrAlbum(title);
    }
    
    public static void main(String[] args) {
        MusicController controller = new MusicController();
        MusicApp app = new MusicApp(controller);
        app.run();
    }
}
