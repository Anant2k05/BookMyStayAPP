import java.util.*;

// Custom Exception
class CancellationException extends Exception {
    public CancellationException(String message) {
        super(message);
    }
}

// Booking Class
class Booking {
    String bookingId;
    String roomType;
    String roomId;
    boolean isActive;

    public Booking(String bookingId, String roomType, String roomId) {
        this.bookingId = bookingId;
        this.roomType = roomType;
        this.roomId = roomId;
        this.isActive = true;
    }
}

// Cancellation Service
class CancellationService {

    public static void cancelBooking(
            String bookingId,
            Map<String, Booking> bookings,
            Map<String, Integer> inventory,
            Stack<String> rollbackStack
    ) throws CancellationException {

        // 1. Validate booking existence
        if (!bookings.containsKey(bookingId)) {
            throw new CancellationException("Booking does not exist");
        }

        Booking booking = bookings.get(bookingId);

        // 2. Prevent double cancellation
        if (!booking.isActive) {
            throw new CancellationException("Booking already cancelled");
        }

        // 3. Controlled rollback
        booking.isActive = false;

        // Push released room to rollback stack
        rollbackStack.push(booking.roomId);

        // Restore inventory
        inventory.put(
                booking.roomType,
                inventory.getOrDefault(booking.roomType, 0) + 1
        );

        // 4. Update booking history (implicit via isActive flag)
    }
}

// Main Class
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Inventory setup
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("STANDARD", 5);
        inventory.put("DELUXE", 3);
        inventory.put("SUITE", 2);

        // Booking storage
        Map<String, Booking> bookings = new HashMap<>();

        // Rollback stack
        Stack<String> rollbackStack = new Stack<>();

        // Sample booking (simulate existing booking)
        Booking b1 = new Booking("B101", "DELUXE", "D1");
        bookings.put("B101", b1);

        // Reduce inventory as if booking was made earlier
        inventory.put("DELUXE", inventory.get("DELUXE") - 1);

        try {
            System.out.print("Enter booking ID to cancel: ");
            String bookingId = sc.nextLine();

            CancellationService.cancelBooking(
                    bookingId,
                    bookings,
                    inventory,
                    rollbackStack
            );

            System.out.println("Cancellation successful");

            System.out.println("Updated Inventory: " + inventory);

            System.out.println("Rollback Stack: " + rollbackStack);

        } catch (CancellationException e) {
            System.out.println("Cancellation Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error occurred");
        } finally {
            sc.close();
        }
    }
}
