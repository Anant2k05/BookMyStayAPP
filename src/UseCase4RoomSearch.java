/**
 * UseCase4RoomSearch - Demonstrates room search with read-only access
 * 
 * @version 4.1
 */
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("Welcome to BookMyStay Application");
        System.out.println("Version: 4.1\n");

        // Create inventory
        RoomInventory inventory = new RoomInventory();

        // Create search service
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Perform search (read-only)
        searchService.searchAvailableRooms();
    }
}