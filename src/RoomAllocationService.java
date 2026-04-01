import java.util.*;

/**
 * RoomAllocationService - Handles booking confirmation and room allocation
 * 
 * @version 6.0
 */
public class RoomAllocationService {

    private RoomInventory inventory;
    private Map<RoomType, Set<String>> allocatedRooms;

    public RoomAllocationService(RoomInventory inventory) {
        this.inventory = inventory;
        this.allocatedRooms = new HashMap<>();

        for (RoomType type : RoomType.values()) {
            allocatedRooms.put(type, new HashSet<>());
        }
    }

    public void processRequests(BookingRequestQueue queue) {

        System.out.println("\n---- Processing Booking Requests ----\n");

        while (true) {

            Reservation request = queue.pollRequest();

            if (request == null) break;

            RoomType type = request.getRoomType();

            int available = inventory.getAvailability(type.name());

            if (available > 0) {

                String roomId = generateRoomId(type);

                allocatedRooms.get(type).add(roomId);

                inventory.updateAvailability(type.name(), available - 1);

                System.out.println("Confirmed: " + request.getGuestName()
                        + " → Room ID: " + roomId);

            } else {
                System.out.println("Rejected (No availability): "
                        + request.getGuestName() + " → " + type);
            }
        }
    }

    private String generateRoomId(RoomType type) {
        return type.name() + "-" + UUID.randomUUID().toString().substring(0, 5);
    }

    public void displayAllocations() {
        System.out.println("\n---- Allocated Rooms ----\n");

        for (RoomType type : allocatedRooms.keySet()) {
            System.out.println(type + " → " + allocatedRooms.get(type));
        }
    }
}