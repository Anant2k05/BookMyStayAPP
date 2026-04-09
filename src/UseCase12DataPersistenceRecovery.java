import java.io.*;
import java.util.*;

// Serializable Booking Class
class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    String bookingId;
    String roomType;

    public Booking(String bookingId, String roomType) {
        this.bookingId = bookingId;
        this.roomType = roomType;
    }

    public String toString() {
        return bookingId + " - " + roomType;
    }
}

// Wrapper class for persistence
class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;

    Map<String, Integer> inventory;
    Map<String, Booking> bookings;

    public SystemState(Map<String, Integer> inventory, Map<String, Booking> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.dat";

    // Save state
    public static void save(SystemState state) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("State saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    // Load state
    public static SystemState load() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No saved state found. Starting fresh.");
            return null;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (SystemState) ois.readObject();

        } catch (Exception e) {
            System.out.println("Error loading state. Starting fresh.");
            return null;
        }
    }
}

// Main Class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        Map<String, Integer> inventory;
        Map<String, Booking> bookings;

        // Attempt recovery
        SystemState state = PersistenceService.load();

        if (state != null) {
            inventory = state.inventory;
            bookings = state.bookings;

            System.out.println("System recovered successfully.");
        } else {
            // Fresh start
            inventory = new HashMap<>();
            inventory.put("STANDARD", 5);
            inventory.put("DELUXE", 3);

            bookings = new HashMap<>();
        }

        // Simulate new booking
        Booking b1 = new Booking("B201", "STANDARD");
        bookings.put(b1.bookingId, b1);
        inventory.put("STANDARD", inventory.get("STANDARD") - 1);

        System.out.println("\nCurrent Inventory: " + inventory);
        System.out.println("Bookings: " + bookings.values());

        // Save before exit
        SystemState newState = new SystemState(inventory, bookings);
        PersistenceService.save(newState);

        System.out.println("Shutdown complete.");
    }
}