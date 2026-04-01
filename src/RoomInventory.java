import java.util.HashMap;

/**
 * RoomInventory - Manages centralized room availability
 * 
 * @version 6.0
 */
public class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        // 🔥 Limited inventory (for testing UC6 rejection logic)
        inventory.put("SINGLE", 2);
        inventory.put("DOUBLE", 1);
        inventory.put("SUITE", 1);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Display inventory (needed for UC3)
    public void displayInventory() {
        System.out.println("---- Room Inventory ----\n");

        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " Available: " + inventory.get(roomType));
        }
    }
}