import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Validator Class
class BookingValidator {

    private static final Set<String> VALID_ROOM_TYPES =
            new HashSet<>(Arrays.asList("STANDARD", "DELUXE", "SUITE"));

    public static void validate(String roomType, int roomsRequested, Map<String, Integer> inventory)
            throws InvalidBookingException {

        if (roomType == null || roomType.trim().isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty");
        }

        roomType = roomType.toUpperCase();

        if (!VALID_ROOM_TYPES.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (roomsRequested <= 0) {
            throw new InvalidBookingException("Rooms must be greater than zero");
        }

        int available = inventory.getOrDefault(roomType, 0);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for " + roomType);
        }

        if (roomsRequested > available) {
            throw new InvalidBookingException("Requested rooms exceed available rooms");
        }
    }
}

// Main Class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("STANDARD", 5);
        inventory.put("DELUXE", 3);
        inventory.put("SUITE", 2);

        try {
            System.out.print("Enter room type: ");
            String roomType = sc.nextLine();

            System.out.print("Enter number of rooms: ");
            int rooms = sc.nextInt();

            // Validate first (Fail-Fast)
            BookingValidator.validate(roomType, rooms, inventory);

            // Update inventory safely
            roomType = roomType.toUpperCase();
            inventory.put(roomType, inventory.get(roomType) - rooms);

            System.out.println("Booking successful");
            System.out.println("Remaining rooms: " + inventory.get(roomType));

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        } catch (Exception e) {
            System.out.println("Unexpected error occurred");
        } finally {
            sc.close();
        }
    }
}