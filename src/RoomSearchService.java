/**
 * RoomSearchService - Handles read-only room search operations
 * 
 * @version 4.0
 */
public class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // Search and display available rooms
    public void searchAvailableRooms() {

        System.out.println("---- Available Rooms ----\n");

        // Create room objects
        Room single = new SingleRoom();
        Room doub = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Check availability and display only if > 0
        if (inventory.getAvailability("Single Room") > 0) {
            single.displayDetails();
            System.out.println("Available: " + inventory.getAvailability("Single Room") + "\n");
        }

        if (inventory.getAvailability("Double Room") > 0) {
            doub.displayDetails();
            System.out.println("Available: " + inventory.getAvailability("Double Room") + "\n");
        }

        if (inventory.getAvailability("Suite Room") > 0) {
            suite.displayDetails();
            System.out.println("Available: " + inventory.getAvailability("Suite Room") + "\n");
        }
    }
}