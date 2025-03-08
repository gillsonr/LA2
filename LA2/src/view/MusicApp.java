package view; 

import model.MusicStore;
import model.LibraryModel;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MusicApp {
    private LibraryModel libraryModel;
    private Scanner scanner;

    public MusicApp() {
        //MusicStore = new MusicStore(); // Load data from files
        libraryModel = new LibraryModel(); // Initialize user library
        scanner = new Scanner(System.in);
    }

    // this method takes in user input and prefrms the requested tasks
    public void start() {
        while (true) {
        	// display options
            System.out.println("\n🎵 Welcome to the Music Store 🎵");
            System.out.println("==== Search By Store =====");
            System.out.println("1. Search for a song by title");
            System.out.println("2. Search for a song by artist");
            System.out.println("3. Search for an album by title");
            System.out.println("4. Search for an album by artist");
            System.out.println("==== Search By Library =====");
            System.out.println("5. Search for a song by title");
            System.out.println("6. Search for a song by artist");
            System.out.println("7. Search for an album by title");
            System.out.println("8. Search for an album by artist");
            System.out.println("9. Search for a playlist by name");
            System.out.println("10. Create a playlist");
            System.out.println("11. Add a song to a playlist");
            System.out.println("12. Add an album to a playlist");
            System.out.println("13. Remove a song from a playlist");
            System.out.println("14. Add a song to the library");
            System.out.println("15. Add album to library");
            System.out.println("16. List all songs");
            System.out.println("17. List all artists");
            System.out.println("18. List all albums");
            System.out.println("19. List all playlists");
            System.out.println("20. List all favorites");
            System.out.println("21. Rate a song");
            System.out.println("22. Exit");
            System.out.print("Enter your choice: ");

            // get the users choice
            String choice = scanner.nextLine();
            switch (choice) {
            	//perform the users choice
                case "1": searchSongByTitleStore(); break;
                case "2": searchSongByArtistStore(); break;
                case "3": searchAlbumByTitleStore(); break;
                case "4": searchAlbumByArtistStore(); break;
                case "5": searchSongByTitleLibrary(); break;
                case "6": searchSongByArtistLibrary(); break;
                case "7": searchAlbumByTitleLibrary(); break;
                case "8": searchAlbumByArtistLibrary(); break;
                case "9": searchPlaylistByName(); break;
                case "10": createPlaylist(); break;
                case "11": addSongToPlaylist(); break;
                case "12": addAlbumToPlaylist(); break;
                case "13": removeSongFromPlaylist(); break;
                case "14": songToLibrary(); break;
                case "15": albumToLibrary(); break;
                case "16": listAllSongs(); break;
                case "17": listAllArtists(); break;
                case "18": listAllAlbums(); break;
                case "19": listAllPlaylists(); break;
                case "20": listAllFavorites(); break;
                case "21": rateSong(); break;
                case "22":
                    System.out.println("Goodbye! 🎶");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void searchSongByTitleStore() {
        System.out.print("Enter the song title: ");
        String title = scanner.nextLine();
        String result = MusicStore.getSongsByTitle(title);
        System.out.println(result);
    }

    private void searchSongByArtistStore() {
        System.out.print("Enter the artist's name: ");
        String artist = scanner.nextLine();
        String result = MusicStore.getSongsByArtist(artist);
        System.out.println(result.isEmpty() ? "No songs found by artist: " + artist : result);
    }

    private void searchAlbumByTitleStore() {
        System.out.print("Enter the album title: ");
        String title = scanner.nextLine();
        String result = MusicStore.getAlbumsByTitle(title);
        System.out.println(result);
    }

    private void searchAlbumByArtistStore() {
        System.out.print("Enter the artist's name: ");
        String artist = scanner.nextLine();
        String result = MusicStore.getAlbumsByArtist(artist);
        System.out.println(result.isEmpty() ? "No albums found by artist: " + artist : result);
    }

    private void searchSongByTitleLibrary() {
        System.out.print("Enter the song title: ");
        String title = scanner.nextLine();
        String result = libraryModel.getSongsByTitle(title);
        System.out.println(result);
    }

    private void searchSongByArtistLibrary() {
        System.out.print("Enter the artist's name: ");
        String artist = scanner.nextLine();
        String result = libraryModel.getSongsByArtist(artist);
        System.out.println(result.isEmpty() ? "No songs found by artist: " + artist : result);
    }

    private void searchAlbumByTitleLibrary() {
        System.out.print("Enter the album title: ");
        String title = scanner.nextLine();
        String result = libraryModel.getAlbumsByTitle(title);
        System.out.println(result);
    }

    private void searchAlbumByArtistLibrary() {
        System.out.print("Enter the artist's name: ");
        String artist = scanner.nextLine();
        String result = libraryModel.getAlbumsByArtist(artist);
        System.out.println(result.isEmpty() ? "No albums found by artist: " + artist : result);
    }

    private void searchPlaylistByName() {
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();
        String result = libraryModel.getPlaylistByName(name);
        System.out.println(result);
    }

    private void createPlaylist() {
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();
        libraryModel.createPlaylist(name);
        System.out.println("Playlist '" + name + "' created successfully.");
    }

    private void addSongToPlaylist() {
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();
        System.out.print("Enter song title: ");
        String songTitle = scanner.nextLine();
        System.out.print("Enter artist name: ");
        String artistName = scanner.nextLine();

        String result = libraryModel.addSongToPlaylist(playlistName, songTitle, artistName);
        System.out.println(result);
    }

    private void addAlbumToPlaylist() {
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();
        System.out.print("Enter album title: ");
        String albumTitle = scanner.nextLine();
        System.out.print("Enter artist name: ");
        String artistName = scanner.nextLine();

        String result = libraryModel.addAlbumToPlaylist(playlistName, albumTitle, artistName);
        System.out.println(result);
    }

    private void removeSongFromPlaylist() {
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();
        System.out.print("Enter song title: ");
        String songTitle = scanner.nextLine();
        System.out.print("Ender song artist: ");
        String songArtist = scanner.nextLine();
        String result = libraryModel.removeSongFromPlaylist(playlistName, songTitle, songArtist);
        System.out.println(result);
    }

    private void songToLibrary() {
        System.out.print("Enter song title: ");
        String songTitle = scanner.nextLine();
        System.out.print("Enter artist name: ");
        String artistName = scanner.nextLine();
        String result = libraryModel.addSongToLibrary(songTitle, artistName);
        System.out.println(result);
    }
    
    private void albumToLibrary() {
        System.out.print("Enter song album title: ");
        String album = scanner.nextLine();
        System.out.print("Enter artist name: ");
        String artistName = scanner.nextLine();
        String result = libraryModel.addSongToLibrary(album, artistName);
        System.out.println(result);
    }
    
    private void listAllSongs() {
    	System.out.print(libraryModel.allSongs());
    }
    
    private void listAllArtists() {
    	System.out.print(libraryModel.allArtists());
    }
    
    private void listAllAlbums() {
    	System.out.print(libraryModel.allAlbums());
    }
    
    private void listAllPlaylists() {
    	System.out.print(libraryModel.allPlaylists());
    }
    
    private void listAllFavorites() {
    	System.out.print(libraryModel.favoriteSongs());
    }
    
    private void rateSong() {
        System.out.print("Enter song title: ");
        String songTitle = scanner.nextLine();
        System.out.print("Enter artist name: ");
        String artistName = scanner.nextLine();
        
        int rating;
        while (true) {
            System.out.print("Enter rating (1-5): ");
            try {
                rating = Integer.parseInt(scanner.nextLine());
                if (rating < 1 || rating > 5) {
                    System.out.println("Invalid rating. Please enter a number between 1 and 5.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number between 1 and 5.");
            }
        }
        
        String result = libraryModel.rateSong(songTitle, artistName, rating);
        System.out.println(result);
    }


    public static void main(String[] args) {
        MusicApp view = new MusicApp();
        view.start();
    }
}